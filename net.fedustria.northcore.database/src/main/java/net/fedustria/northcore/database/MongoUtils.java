package net.fedustria.northcore.database;

import org.bson.Document;

public class MongoUtils {
    /**
     * Merges two documents into a single document.
     *
     * @param oldDocument The first document to merge.
     * @param newDocument The second document to merge.
     * @return A new document containing the merged contents of the two input documents.
     */
    public static Document mergeDocuments(final Document oldDocument, final Document newDocument) {
        final var mergedDocument = new Document();

        for (final String key : oldDocument.keySet()) {
            mergedDocument.append(key, oldDocument.get(key));
        }

        for (final String key : newDocument.keySet()) {
            if (!mergedDocument.containsKey(key)) {
                mergedDocument.append(key, newDocument.get(key));
            }
        }

        return mergedDocument;
    }
}
