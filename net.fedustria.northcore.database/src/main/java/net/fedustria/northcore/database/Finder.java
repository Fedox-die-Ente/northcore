package net.fedustria.northcore.database;

public class Finder {
    /**
     * Creates a new FinderContent instance with the specified field and value.
     *
     * @param field The field name to search for.
     * @param value The value to match against the specified field.
     * @return A new FinderContent instance containing the specified field and value.
     */
    public static FinderContent find(final String field, final Object value) {
        return new FinderContent(field, value);
    }

    /**
     * A record that holds a field name and its corresponding value for search criteria.
     *
     * @param field The field name to search for.
     * @param value The value to match against the specified field.
     */
    public record FinderContent(String field, Object value) {}
}
