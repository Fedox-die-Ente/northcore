package net.fedustria.northcore.database;

import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.Getter;
import net.fedustria.northcore.config.data.DatabaseConfig;

public class DBClient {
    private final MongoClient client;
    @Getter
    private final MongoDB     defaultDb;

    /**
     * Constructs a new DBClient instance with the specified connection parameters.
     *
     * @param ip       The IP address of the MongoDB server.
     * @param port     The port number of the MongoDB server.
     * @param user     The username for authentication.
     * @param password The password for authentication.
     * @param database The name of the default database.
     * @param authDb   The authentication database.
     */
    public DBClient(final String ip, final int port, final String user, final String password, final String database, final String authDb) {
        final MongoCredential credential = MongoCredential.createCredential(user, authDb, password.toCharArray());

        final String url = String.format("mongodb://%s:%s@%s:%d/%s", user, password, ip, port, authDb);
        this.client = MongoClients.create(url);

        this.defaultDb = getDatabase(database);
    }

    /**
     * Constructs a new DBClient instance using the provided DatabaseConfig object.
     *
     * @param dbConfig The DatabaseConfig object containing the connection parameters.
     */
    public DBClient(final DatabaseConfig dbConfig) {
        this(dbConfig.getHost(), dbConfig.getPort(), dbConfig.getUsername(), dbConfig.getPassword(), dbConfig.getDatabase(), dbConfig.getAuthDatabase());
    }

    /**
     * Retrieves a MongoDB instance for the specified database name.
     *
     * @param dbName The name of the database to retrieve.
     * @return A MongoDB object representing the specified database.
     */
    public MongoDB getDatabase(final String dbName) {
        final var db = client.getDatabase(dbName);
        return new MongoDB(db);
    }
}
