package net.fedustria.northcore.i18n;

import net.fedustria.northcore.api.player.impl.APIPlayer;
import net.fedustria.northcore.database.Finder;
import net.fedustria.northcore.database.MongoModel;
import net.fedustria.northcore.i18n.types.ELanguageType;
import org.bson.Document;

import java.util.HashMap;
import java.util.Map;

public abstract class LanguageMessage {

    protected String key;
    protected APIPlayer player;
    protected Map<String, String> placeholders;

    public LanguageMessage() {
        this.placeholders = new HashMap<>();
    }

    public LanguageMessage key(String key) {
        this.key = key;
        return this;
    }

    public LanguageMessage player(APIPlayer player) {
        this.player = player;
        return this;
    }

    public LanguageMessage addPlaceholder(String key, String value) {
        this.placeholders.put(key, value);
        return this;
    }

    public abstract String translate();

    protected String replacePlaceholders(String message) {
        for (Map.Entry<String, String> entry : placeholders.entrySet()) {
            message = message.replace("{" + entry.getKey() + "}", entry.getValue());
        }

        return message;
    }

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
