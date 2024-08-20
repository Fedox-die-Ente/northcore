package net.fedustria.northcore.i18n.impl;

import net.fedustria.northcore.i18n.Language;
import net.fedustria.northcore.i18n.LanguageMessage;
import net.fedustria.northcore.i18n.types.ELanguageType;

/**
 * Class for handling language text values.
 */
public class LanguageTextValue extends LanguageMessage {

    /**
     * Creates a new LanguageTextValue instance with the specified key.
     *
     * @param key The key for the language text value
     * @return A new LanguageTextValue instance
     */
    public static LanguageTextValue builder(String key) {
        return (LanguageTextValue) new LanguageTextValue().key(key);
    }

    /**
     * Translates the language text value by replacing placeholders.
     *
     * @return The translated language text value
     */
    @Override
    public String translate() {
        return replacePlaceholders(
                Language.getCachedTextValues().computeIfAbsent(key, k -> getMessageFromDatabase(ELanguageType.TEXT_VALUE, k))
        );
    }

}