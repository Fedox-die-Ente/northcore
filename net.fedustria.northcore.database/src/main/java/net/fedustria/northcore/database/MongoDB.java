package net.fedustria.northcore.database;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.HashMap;
import java.util.Map;

public class MongoDB {
    private final MongoDatabase db;

    public MongoDB(final MongoDatabase db) {
        this.db = db;
    }

    /**
     * Retrieves a MongoModel based on the provided FinderContent criteria.
     *
     * @param finders Varargs of Finder.FinderContent used to specify the search criteria.
     * @return A MongoModel object containing the first document that matches the criteria, or an empty MongoModel if no
     * match is found.
     */
    public MongoModel getModel(final Finder.FinderContent... finders) {
        for (final String collectionName : db.listCollectionNames()) {
            final var collection = db.getCollection(collectionName);
            final Map<String, Object> finderMap = new HashMap<>();
            for (final Finder.FinderContent finder : finders) {
                finderMap.put(finder.field(), finder.value());
            }

            final var document = collection.find(new Document(finderMap)).first();
            if (document != null) {
                return new MongoModel(document);
            }
        }
        return new MongoModel(null);
    }

    /**
     * Retrieves a MongoCollection for the specified collection name.
     *
     * @param collectionName The name of the collection to retrieve.
     * @return The MongoCollection object for the specified collection name.
     */
    public MongoCollection<Document> getCollection(final String collectionName) {
        return db.getCollection(collectionName);
    }

    /**
     * Creates a new collection in the database with the specified name.
     *
     * @param collectionName The name of the collection to create.
     */
    public void createCollection(final String collectionName) {
        db.createCollection(collectionName);
    }

    /**
     * Provides direct access to the underlying MongoDatabase object.
     *
     * @return The raw MongoDatabase object.
     */
    public MongoDatabase raw() {
        return db;
    }
}
