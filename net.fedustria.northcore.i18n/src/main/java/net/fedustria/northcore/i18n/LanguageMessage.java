package net.fedustria.northcore.i18n;

import net.fedustria.northcore.api.player.impl.APIPlayer;
import net.fedustria.northcore.database.Finder;
import net.fedustria.northcore.database.MongoModel;
import net.fedustria.northcore.i18n.types.ELanguageType;
import org.bson.Document;

import java.util.HashMap;
import java.util.Map;

public abstract class LanguageMessage {

    /**
     * The key for the language message.
     */
    protected String key;

    /**
     * The player associated with the language message.
     */
    protected APIPlayer player;

    /**
     * Placeholders to be replaced in the message.
     */
    protected Map<String, String> placeholders;

    /**
     * Constructs a new LanguageMessage with an empty placeholders map.
     */
    public LanguageMessage() {
        this.placeholders = new HashMap<>();
    }

    /**
     * Sets the key for the language message.
     *
     * @param key The key to set
     * @return The current LanguageMessage instance
     */
    public LanguageMessage key(String key) {
        this.key = key;
        return this;
    }

    /**
     * Sets the player for the language message.
     *
     * @param player The player to set
     * @return The current LanguageMessage instance
     */
    public LanguageMessage player(APIPlayer player) {
        this.player = player;
        return this;
    }

    /**
     * Adds a placeholder to the language message.
     *
     * @param key   The placeholder key
     * @param value The placeholder value
     * @return The current LanguageMessage instance
     */
    public LanguageMessage addPlaceholder(String key, String value) {
        this.placeholders.put(key, value);
        return this;
    }

    /**
     * Translates the language message.
     *
     * @return The translated message
     */
    public abstract String translate();

    /**
     * Replaces placeholders in the given message.
     *
     * @param message The message with placeholders
     * @return The message with placeholders replaced
     */
    protected String replacePlaceholders(String message) {
        for (Map.Entry<String, String> entry : placeholders.entrySet()) {
            message = message.replace("{" + entry.getKey() + "}", entry.getValue());
        }
        return message;
    }

    /**
     * Retrieves a message from the database based on the language type and key.
     *
     * @param type The language type
     * @param key  The key for the message
     * @return The message from the database, or "Not found: {key}" if not found
     */
    protected String getMessageFromDatabase(ELanguageType type, String key) {
        String language = player.getLanguage();
        MongoModel document = Language.getSettingsDB().getModel(Finder.find("name", "language"));

        if (document != null && document.getDocument() != null) {
            Document root = document.getDocument();
            Document categoryDoc = root.get(type.getCategory(), Document.class);
            if (categoryDoc != null) {
                Document languageDoc = categoryDoc.get(language, Document.class);
                if (languageDoc != null) {
                    String message = languageDoc.getString(key);
                    if (message != null) {
                        return message;
                    }
                }
            }
        }
        return "Not found: " + key;
    }
}