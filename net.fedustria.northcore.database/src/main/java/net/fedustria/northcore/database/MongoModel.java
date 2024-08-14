package net.fedustria.northcore.database;

import java.util.HashMap;
import java.util.Map;

public class MongoModel {
	private final Map<String, Object> objects = new HashMap<>();

	public String getString(String field) {
		return (String) objects.get(field);
	}

	public Integer getInt(String field) {
		return (Integer) objects.get(field);
	}

	public Float getFloat(String field) {
		return (Float) objects.get(field);
	}

	public Double getDouble(String field) {
		return (Double) objects.get(field);
	}
}
