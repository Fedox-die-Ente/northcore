package net.fedustria.northcore.i18n.impl;

import net.fedustria.northcore.i18n.Language;
import net.fedustria.northcore.i18n.LanguageMessage;
import net.fedustria.northcore.i18n.types.ELanguageType;

/**
 * Class for handling language titles.
 */
public class LanguageTitles extends LanguageMessage {

    /**
     * Creates a new LanguageTitles instance with the specified key.
     *
     * @param key The key for the language title
     * @return A new LanguageTitles instance
     */
    public static LanguageTitles builder(String key) {
        return (LanguageTitles) new LanguageTitles().key(key);
    }

    /**
     * Translates the language title by replacing placeholders.
     *
     * @return The translated language title
     */
    @Override
    public String translate() {
        return replacePlaceholders(
                Language.getCachedTitles().computeIfAbsent(key, k -> getMessageFromDatabase(ELanguageType.TITLE, k))
        );
    }
}