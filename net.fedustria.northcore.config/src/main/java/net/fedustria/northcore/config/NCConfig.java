package net.fedustria.northcore.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class NCConfig {
    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * Loads a configuration from the specified file and maps it to an instance of the specified class.
     *
     * @param file  The file from which to load the configuration.
     * @param clazz The class to which the configuration should be mapped.
     * @param <T>   The type of the class.
     * @return An instance of the specified class containing the configuration data.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public static <T> T loadConfig(final File file, final Class<T> clazz) throws IOException {
        return mapper.readValue(file, clazz);
    }

    /**
     * Writes the specified object as a configuration to the specified file.
     *
     * @param file   The file to which the configuration should be written.
     * @param object The object containing the configuration data to write.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public static void writeConfig(final File file, final Object object) throws IOException {
        mapper.writeValue(file, object);
    }
}
