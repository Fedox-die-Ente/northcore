package net.fedustria.northcore.database;

import org.bson.Document;

public class MongoModel {
    private final Document document;

    public MongoModel(final Document document) {
        this.document = document;
    }

    /**
     * Retrieves the value of the specified field as a String.
     *
     * @param field The name of the field to retrieve.
     * @return The value of the specified field as a String, or null if the field does not exist.
     */
    public String getString(final String field) {
        return document.getString(field);
    }

    /**
     * Retrieves the value of the specified field as an Integer.
     *
     * @param field The name of the field to retrieve.
     * @return The value of the specified field as an Integer, or null if the field does not exist.
     */
    public Integer getInt(final String field) {
        return document.getInteger(field);
    }

    /**
     * Retrieves the value of the specified field as a Float.
     *
     * @param field The name of the field to retrieve.
     * @return The value of the specified field as a Float, or null if the field does not exist.
     */
    public Float getFloat(final String field) {
        return document.getDouble(field).floatValue();
    }

    /**
     * Retrieves the value of the specified field as a Double.
     *
     * @param field The name of the field to retrieve.
     * @return The value of the specified field as a Double, or null if the field does not exist.
     */
    public Double getDouble(final String field) {
        return document.getDouble(field);
    }

    /**
     * Retrieves the underlying BSON document.
     *
     * @return The BSON document wrapped by this model.
     */
    public Document getDocument() {
        return document;
    }
}
