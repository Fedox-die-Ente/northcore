package net.fedustria.northcore.database;

import org.bson.Document;

public class MongoModel {
    public Document document;

    public MongoModel(final Document document) {
        this.document = document;
    }

    public String getString(final String field) {
        return document.getString(field);
    }

    public Integer getInt(final String field) {
        return document.getInteger(field);
    }

    public Float getFloat(final String field) {
        return document.getDouble(field).floatValue();
    }

    public Double getDouble(final String field) {
        return document.getDouble(field);
    }
}
