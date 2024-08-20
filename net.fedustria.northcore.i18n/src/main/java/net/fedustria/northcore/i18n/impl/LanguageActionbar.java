package net.fedustria.northcore.i18n.impl;

import net.fedustria.northcore.i18n.Language;
import net.fedustria.northcore.i18n.LanguageMessage;
import net.fedustria.northcore.i18n.types.ELanguageType;

/**
 * Class for handling language action bars.
 */
public class LanguageActionbar extends LanguageMessage {

    /**
     * Creates a new LanguageActionbar instance with the specified key.
     *
     * @param key The key for the language action bar
     * @return A new LanguageActionbar instance
     */
    public static LanguageActionbar builder(String key) {
        return (LanguageActionbar) new LanguageActionbar().key(key);
    }

    /**
     * Translates the language action bar by replacing placeholders.
     *
     * @return The translated language action bar
     */
    @Override
    public String translate() {
        return replacePlaceholders(
                Language.getCachedActionBars().computeIfAbsent(key, k -> getMessageFromDatabase(ELanguageType.ACTIONBAR, k))
        );
    }

}