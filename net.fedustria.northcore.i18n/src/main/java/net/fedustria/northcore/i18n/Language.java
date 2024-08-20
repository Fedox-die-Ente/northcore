package net.fedustria.northcore.i18n;

import lombok.Getter;
import net.fedustria.northcore.config.NCConfig;
import net.fedustria.northcore.config.data.DatabaseConfig;
import net.fedustria.northcore.config.exceptions.InvalidConfigException;
import net.fedustria.northcore.database.DBClient;
import net.fedustria.northcore.database.MongoDB;
import org.bson.Document;

import java.io.File;
import java.io.IOException;

import static net.fedustria.northcore.common.Constants.WORKING_DIRECTORY;

public class Language {

    @Getter
    private static Document cachedChatMessages;
    @Getter
    private static Document cachedTitles;
    @Getter
    private static Document cachedScoreboardLines;
    @Getter
    private static Document cachedActionBars;
    @Getter
    private static Document cachedTextValues;
    @Getter
    private static Document cachedItems;

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
