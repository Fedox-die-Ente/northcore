package net.fedustria.northcore.setup;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import net.fedustria.northcore.config.data.DatabaseConfig;
import net.fedustria.northcore.setup.fields.password.PasswordField;
import net.fedustria.northcore.setup.types.EEnvironment;
import net.fedustria.northcore.setup.utils.OperatingSystemCheck;
import net.fedustria.northcore.setup.utils.StringUtils;
import net.fedustria.northcore.setup.utils.SystemUtil;
import net.fedustria.northcore.setup.utils.os.OSType;
import org.bson.BsonDocument;
import org.bson.BsonInt64;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static net.fedustria.northcore.config.NCConfig.writeConfig;
import static net.fedustria.northcore.setup.utils.StringUtils.errorText;
import static net.fedustria.northcore.setup.utils.StringUtils.prefixText;

/**
 * Main class for the NorthCore setup application.
 */
public class NorthSetup {

    /**
     * MongoDB client instance.
     */
    static MongoClient client;

    /**
     * MongoDB database instance.
     */
    static MongoDatabase mongoDatabase;

    /**
     * MongoDB collection instance.
     */
    MongoCollection collection;

    /**
     * Main method to start the NorthCore setup application.
     *
     * @param args Command line arguments
     * @throws IOException If an I/O error occurs
     */
    public static void main(final String[] args) throws IOException {
        try (final Scanner scanner = new Scanner(System.in)) {

            // Check if the operating system is Windows
            if (OperatingSystemCheck.getOperatingSystemType() == OSType.Windows) {
                errorText("Windows is not supported! Please use a Linux distribution.");
                errorText("Or create a NORTHCORE_WORKING_DIRECTORY environment variable with the path to the working directory. By yourself.");
                errorText("We can't promise that it will work.");
                errorText("Who tf uses Windows for a server anyway?\n\n");

                errorText("Press enter to exit...");
                scanner.nextLine();
                System.exit(64);
                return;
            }

            // Display the top text from the credits file
            System.out.println(StringUtils.getTopText());

            prefixText("Welcome to the NorthCore setup! Let's get started with some basic stuff.");
            prefixText("Which directory should be the working directory? (e.g. /home/northcore)");

            final String workingDirectory = scanner.nextLine();
            handleWorkDirectory(workingDirectory);

            prefixText("Alright, let's get started with the database stuff. (We use MongoDB btw)\n\n");
            prefixText("You know the drill. What's the host?");
            final String host = scanner.nextLine();

            prefixText("What's the port?");
            final String port = scanner.nextLine();

            prefixText("What's the user name?");
            final String user = scanner.nextLine();

            prefixText("I dont like the username.. Anyways what's the password?");
            final String password = PasswordField.readPassword("");

            prefixText("Thank you for that nice information. What's the database name?");
            final String database = scanner.nextLine();

            prefixText("And what's the authentication database? (e.g. admin)");
            final String authDatabase = scanner.nextLine();

            if (handleDatabase(host, port, user, password, database, authDatabase)) {
                createConfigFile(host, port, user, password, database, authDatabase);
                prefixText("Database connection successful!");
            }

            prefixText("Alright, we're done here, let's move on to the next step.\n\n");

            prefixText("Should animations be automatically activated for a player when they first join? (true/false)");
            final boolean autoActivateAnimations = scanner.nextLine().equalsIgnoreCase("true");

            prefixText("Should sounds be automatically activated for a player when they first join? (true/false)");
            final boolean autoActivateSounds = scanner.nextLine().equalsIgnoreCase("true");

            prefixText("What should be the primary color of the network? (e.g. #2f80cc)");
            final String primaryColor = scanner.nextLine();

            prefixText("What should be the secondary color of the network? (We recommend a lighter color) (e.g. #ffffff)");
            final String secondaryColor = scanner.nextLine();

            prefixText("What is the COLORED name of the network? (e.g. %c1%Fedustria%c2%NET)");
            prefixText("%c1% : Primary color, %c2% : Secondary color");
            final String coloredName = scanner.nextLine();

            prefixText("Which symbols should be in front of the CHAT prefix? (e.g. §7•§8●)");
            final String chatPrefix = scanner.nextLine();

            prefixText("Which symbols should be in front of the ITEM prefix? (e.g. §7•§8●)");
            final String itemPrefix = scanner.nextLine();

            prefixText("Which symbols should be in front of the INVENTORY prefix? (e.g. §7•§8●)");
            final String inventoryPrefix = scanner.nextLine();

            prefixText("What should the standard inventories be?");
            prefixText("1. GUI Inventories");
            prefixText("2. Floating Items");
            final int standardInventories = Integer.parseInt(scanner.nextLine());

            prefixText("Should the player have placeholder items in their inventory by default? (e.g. Glass panes) (true/false)");
            final boolean placeholderItems = scanner.nextLine().equalsIgnoreCase("true");

            prefixText("\n\nPlease wait a moment while we create the configuration file...\n\n");

            if (handleDefaultValues(autoActivateAnimations, autoActivateSounds, primaryColor, secondaryColor, coloredName, chatPrefix, itemPrefix, inventoryPrefix, standardInventories, placeholderItems)) {
                prefixText("Setup is done! Configuration file created successfully!");
                prefixText("I hope you have a great time with NorthCore! :D");

                System.exit(0);
            } else {
                errorText("Configuration file could not be created!");
                System.exit(1);
            }
        }

    }

    /**
     * Handles the creation of the working directory.
     *
     * @param path The path to the working directory
     */
    private static void handleWorkDirectory(final String path) {
        final boolean workDirectory = new File(path).mkdirs();

        if (!workDirectory) {

            if (new File(path).exists()) {
                prefixText("Working directory already exists!");
            } else {
                errorText("Working directory could not be created!");
            }

            System.exit(1);
        } else {
            prefixText("Working directory created successfully!");

            SystemUtil.setEnv(EEnvironment.getEnvironment(), "NORTHCORE_WORKING_DIRECTORY", path);
        }
    }

    /**
     * Handles the connection to the MongoDB database.
     *
     * @param host         The MongoDB host
     * @param port         The MongoDB port
     * @param user         The MongoDB user
     * @param password     The MongoDB password
     * @param database     The MongoDB database name
     * @param authDatabase The MongoDB authentication database
     * @return true if the database connection is successful, false otherwise
     */
    private static boolean handleDatabase(final String host, final String port, final String user, final String password, final String database, final String authDatabase) {

        final String uri = "mongodb://" + user + ":" + password + "@" + host + ":" + port;

        client = MongoClients.create(uri);

        mongoDatabase = client.getDatabase(database);

        try {
            final Bson command = new BsonDocument("ping", new BsonInt64(1));
            final Document commandResult = mongoDatabase.runCommand(command);
            return true;
        } catch (final MongoException e) {
            errorText("Could not connect to the database!");
            errorText("Please check your credentials and try again.");
            System.exit(1);
            return false;
        }
    }

    private static void createConfigFile(final String host, final String port, final String user, final String password, final String database, final String authDatabase) {

        final File configFile = new File(System.getenv("NORTHCORE_WORKING_DIRECTORY") + "/config.json");

        if (configFile.exists()) {
            errorText("Configuration file already exists!");
            System.exit(1);
        }

        try {
            configFile.createNewFile();
        } catch (final IOException e) {
            errorText("Configuration file could not be created!");
            System.exit(1);
        }

        final ObjectMapper objectMapper = new ObjectMapper();
        
        try {
            writeConfig(configFile, new DatabaseConfig(host, Integer.parseInt(port), user, password, database, authDatabase));
        } catch (final IOException e) {
            errorText("Configuration file could not be written!");
            System.exit(1);
        }
    }

    /**
     * Handles the creation of default values in the MongoDB database.
     *
     * @param autoActivateAnimations Whether to automatically activate animations for a player when they first join
     * @param autoActivateSounds     Whether to automatically activate sounds for a player when they first join
     * @param primaryColor           The primary color of the network
     * @param secondaryColor         The secondary color of the network
     * @param coloredName            The colored name of the network
     * @param chatPrefix             The symbols in front of the chat prefix
     * @param itemPrefix             The symbols in front of the item prefix
     * @param inventoryPrefix        The symbols in front of the inventory prefix
     * @param standardInventories    The standard inventories
     * @param placeholderItems       Whether the player should have placeholder items in their inventory by default
     * @return true if the default values are successfully created, false otherwise
     */
    private static boolean handleDefaultValues(final boolean autoActivateAnimations, final boolean autoActivateSounds, final String primaryColor, final String secondaryColor, final String coloredName, final String chatPrefix, final String itemPrefix, final String inventoryPrefix, final int standardInventories, final boolean placeholderItems) {

        if (standardInventories < 1 || standardInventories > 2) {
            errorText("Invalid value for standard inventories!");
            errorText("Please enter a value between 1 and 2.");
            System.exit(1);
            return false;
        }

        mongoDatabase.createCollection("settings");

        final Document document = new Document("name", "defaultSettings")
                .append("autoActivateAnimations", autoActivateAnimations)
                .append("autoActivateSounds", autoActivateSounds)
                .append("primaryColor", primaryColor)
                .append("secondaryColor", secondaryColor)
                .append("coloredName", coloredName)
                .append("chatPrefix", chatPrefix)
                .append("itemPrefix", itemPrefix)
                .append("inventoryPrefix", inventoryPrefix)
                .append("standardInventories", standardInventories)
                .append("placeholderItems", placeholderItems);

        mongoDatabase.getCollection("settings").insertOne(document);

        client.close();

        return true;
    }

}