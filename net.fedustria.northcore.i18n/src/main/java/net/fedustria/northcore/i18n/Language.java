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

    @Getter
    private static final Map<String, String> cachedChatMessages = new HashMap<>();
    @Getter
    private static final Map<String, String> cachedTitles = new HashMap<>();
    @Getter
    private static final Map<String, String> cachedScoreboardLines = new HashMap<>();
    @Getter
    private static final Map<String, String> cachedActionBars = new HashMap<>();
    @Getter
    private static final Map<String, String> cachedTextValues = new HashMap<>();
    @Getter
    private static final Map<String, String> cachedItems = new HashMap<>();

    @Getter
    private static MongoDB settingsDB;

    public static void loadI18n() {

        try {
            DatabaseConfig dbConfig = NCConfig.loadConfig(new File(System.getenv(WORKING_DIRECTORY), "config.json"), DatabaseConfig.class);
            DBClient dbClient = new DBClient(dbConfig);

            settingsDB = dbClient.getDatabase(dbConfig.getDatabase());
        } catch (IOException e) {
            throw new InvalidConfigException("Failed to load the database configuration file.");
        }

    }

}
