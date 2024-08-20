package net.fedustria.northcore.i18n;

import lombok.Getter;
import net.fedustria.northcore.config.NCConfig;
import net.fedustria.northcore.config.data.DatabaseConfig;
import net.fedustria.northcore.config.exceptions.InvalidConfigException;
import net.fedustria.northcore.database.DBClient;
import net.fedustria.northcore.database.MongoDB;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static net.fedustria.northcore.common.Constants.WORKING_DIRECTORY;

public class Language {

    /**
     * Cached chat messages.
     */
    @Getter
    private static final Map<String, String> cachedChatMessages = new HashMap<>();

    /**
     * Cached titles.
     */
    @Getter
    private static final Map<String, String> cachedTitles = new HashMap<>();

    /**
     * Cached scoreboard lines.
     */
    @Getter
    private static final Map<String, String> cachedScoreboardLines = new HashMap<>();

    /**
     * Cached action bars.
     */
    @Getter
    private static final Map<String, String> cachedActionBars = new HashMap<>();

    /**
     * Cached text values.
     */
    @Getter
    private static final Map<String, String> cachedTextValues = new HashMap<>();

    /**
     * Cached items.
     */
    @Getter
    private static final Map<String, String> cachedItems = new HashMap<>();

    /**
     * MongoDB instance for settings.
     */
    @Getter
    private static MongoDB settingsDB;

    /**
     * Loads internationalization (i18n) data from the database.
     *
     * @throws InvalidConfigException if the database configuration file cannot be loaded
     */
    public static void loadI18n() {

        try {
            // Load the database configuration from the config file
            DatabaseConfig dbConfig = NCConfig.loadConfig(new File(System.getenv(WORKING_DIRECTORY), "config.json"), DatabaseConfig.class);
            DBClient dbClient = new DBClient(dbConfig);

            // Initialize the settings database
            settingsDB = dbClient.getDatabase(dbConfig.getDatabase());
        } catch (IOException e) {
            throw new InvalidConfigException("Failed to load the database configuration file.");
        }

    }

}