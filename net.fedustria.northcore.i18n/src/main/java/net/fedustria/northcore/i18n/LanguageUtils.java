package net.fedustria.northcore.i18n;

import net.fedustria.northcore.config.NCConfig;
import net.fedustria.northcore.config.data.DatabaseConfig;
import net.fedustria.northcore.database.DBClient;
import net.fedustria.northcore.database.MongoDB;

import java.io.File;
import java.io.IOException;

import static net.fedustria.northcore.common.Constants.WORKING_DIRECTORY;

public class LanguageUtils {

    public static void main(String[] args) throws IOException {
        DatabaseConfig dbConfig = NCConfig.loadConfig(new File(System.getenv(WORKING_DIRECTORY), "config.json"), DatabaseConfig.class);
        DBClient dbClient = new DBClient(dbConfig);
        generateNewLanguageStrings(dbClient, dbConfig.getDatabase());
    }

    public static void generateNewLanguageStrings(DBClient dbClient, String database) throws IOException {

        MongoDB settingsDB = dbClient.getDatabase(database);

        String jsonFile = LanguageMerger.getDefaultJSONFile();

        System.out.println(jsonFile);

//        Document doc = Document.parse(jsonFile);
//
//        settingsDB.insertNewFields("settings", doc, "name", "language");
    }

}
