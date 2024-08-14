package net.fedustria.northcore.api.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.reflections.scanners.Scanners.TypesAnnotated;
import static org.reflections.util.ClasspathHelper.forPackage;

/**
 * Provides a way to execute methods from classes without implement them via gradle.
 * Prevents circular dependencies.
 */
public class ClassLoaderService {
	private static final Logger LOG = LoggerFactory.getLogger(ClassLoaderService.class);

	private final ObjectMapper          objectMapper = new ObjectMapper();
	private final ClassLoaderProperties classLoaderProperties;
	private final ComponentScope        scope;

	private ClassLoaderService(ComponentScope scope) {
		this.scope = scope;

		try {
			classLoaderProperties = objectMapper.readValue(ClassLoaderService.class.getClassLoader().getResource("classloader.json"), ClassLoaderProperties.class);
			constructComponents();
		} catch (
				IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static ClassLoaderService create(ComponentScope scope) {
		return new ClassLoaderService(scope);
	}

	private List<Class<?>> getComponents() {
		List<Class<?>>      classes = new ArrayList<>();
		Set<ComponentScope> scopes  = Set.of(ComponentScope.All, scope);

		for (String packageName : classLoaderProperties.getPackages()) {
			Reflections reflections = new Reflections(new ConfigurationBuilder()
					.setUrls(forPackage(packageName))
					.setScanners(TypesAnnotated));

			Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(Component.class);

			for (Class<?> clazz : annotatedClasses) {
				Component component = clazz.getAnnotation(Component.class);
				if (scopes.contains(component.scope())) {
					classes.add(clazz);
				}
			}
		}

		LOG.info("Found {} components.", classes.size());

		return classes;
	}

	private void constructComponents() {
		List<Class<?>> components = getComponents();

		for (Class<?> component : components) {
			Method[] methods = component.getDeclaredMethods();
			try {
				Object instance = component.getDeclaredConstructor().newInstance();

				for (Method method : methods) {
					if (!method.isAnnotationPresent(Construct.class)) {
						return;
					}

					try {
						method.setAccessible(true);
						method.invoke(instance);
					} catch (Exception e) {
						LOG.info("Failed to invoke method {}", method.getName(), e);
					}
				}
			} catch (Exception e) {
				LOG.error("Failed to construct component {}", component.getName(), e);
			}
		}
	}
}
