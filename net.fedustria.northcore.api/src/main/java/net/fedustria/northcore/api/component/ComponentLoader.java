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
 * Provides a way to execute methods from classes without implement them via gradle. Prevents circular dependencies.
 */
public class ComponentLoader {
    private static final Logger LOG = LoggerFactory.getLogger(ComponentLoader.class);

    private final ObjectMapper        objectMapper = new ObjectMapper();
    private final ComponentProperties componentProperties;
    private final ComponentScope      scope;

    private ComponentLoader(final ComponentScope scope) {
        this.scope = scope;

        try {
            componentProperties = objectMapper.readValue(ComponentLoader.class.getClassLoader().getResource("classloader.json"), ComponentProperties.class);
            constructComponents();
        } catch (
                final IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ComponentLoader create(final ComponentScope scope) {
        return new ComponentLoader(scope);
    }

    private List<Class<?>> getComponents() {
        final List<Class<?>> classes = new ArrayList<>();
        final Set<ComponentScope> scopes = Set.of(ComponentScope.All, scope);

        for (final String packageName : componentProperties.getPackages()) {
            final Reflections reflections = new Reflections(new ConfigurationBuilder()
                    .setUrls(forPackage(packageName))
                    .setScanners(TypesAnnotated));

            final Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(Component.class);

            for (final Class<?> clazz : annotatedClasses) {
                final Component component = clazz.getAnnotation(Component.class);
                if (scopes.contains(component.scope())) {
                    classes.add(clazz);
                }
            }
        }

        LOG.info("Found {} components.", classes.size());

        return classes;
    }

    private void constructComponents() {
        final List<Class<?>> components = getComponents();

        for (final Class<?> component : components) {
            final Method[] methods = component.getDeclaredMethods();
            try {
                final Object instance = component.getDeclaredConstructor().newInstance();

                for (final Method method : methods) {
                    if (!method.isAnnotationPresent(Construct.class)) {
                        return;
                    }

                    try {
                        // If the method has a single parameter of type ComponentScope, invoke it with the current scope.
                        if (method.getParameterCount() == 1 && method.getParameterTypes()[0].equals(ComponentScope.class)) {
                            method.setAccessible(true);
                            method.invoke(instance, scope);
                            continue;
                        }

                        // If the method has no parameters, invoke it.
                        method.setAccessible(true);
                        method.invoke(instance);
                    } catch (final Exception e) {
                        LOG.info("Failed to invoke method {}", method.getName(), e);
                    }
                }
            } catch (final Exception e) {
                LOG.error("Failed to construct component {}", component.getName(), e);
            }
        }
    }
}
