package net.fedustria.northcore.i18n.impl;

import net.fedustria.northcore.i18n.Language;
import net.fedustria.northcore.i18n.LanguageMessage;
import net.fedustria.northcore.i18n.types.ELanguageType;

/**
 * Class for handling language items.
 */
public class LanguageItem extends LanguageMessage {

    /**
     * Creates a new LanguageItem instance with the specified key.
     *
     * @param key The key for the language item
     * @return A new LanguageItem instance
     */
    public static LanguageItem builder(String key) {
        return (LanguageItem) new LanguageItem().key(key);
    }

    /**
     * Translates the language item by replacing placeholders.
     *
     * @return The translated language item
     */
    @Override
    public String translate() {
        return replacePlaceholders(
                Language.getCachedItems().computeIfAbsent(key, k -> getMessageFromDatabase(ELanguageType.ITEM, k))
        );
    }

}