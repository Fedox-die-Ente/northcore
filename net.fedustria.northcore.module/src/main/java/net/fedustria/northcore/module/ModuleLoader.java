package net.fedustria.northcore.module;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.fedustria.northcore.api.component.Component;
import net.fedustria.northcore.api.component.ComponentLoader;
import net.fedustria.northcore.api.component.ComponentScope;
import net.fedustria.northcore.api.component.Construct;

import java.io.IOException;

import static net.fedustria.northcore.api.component.ComponentScope.All;

@Component(scope = All)
public class ModuleLoader {
    private final ObjectMapper     objectMapper = new ObjectMapper();
    private final ModuleProperties moduleProperties;

    public ModuleLoader() throws IOException {
        moduleProperties = objectMapper.readValue(ComponentLoader.class.getClassLoader().getResource("module.json"), ModuleProperties.class);
    }

    @Construct
    public void init(final ComponentScope scope) {
        
    }
}
