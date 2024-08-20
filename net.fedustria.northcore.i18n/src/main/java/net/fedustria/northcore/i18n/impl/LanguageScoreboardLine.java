package net.fedustria.northcore.i18n.impl;

import net.fedustria.northcore.i18n.Language;
import net.fedustria.northcore.i18n.LanguageMessage;
import net.fedustria.northcore.i18n.types.ELanguageType;

/**
 * Class for handling language scoreboard lines.
 */
public class LanguageScoreboardLine extends LanguageMessage {

    /**
     * Creates a new LanguageScoreboardLine instance with the specified key.
     *
     * @param key The key for the language scoreboard line
     * @return A new LanguageScoreboardLine instance
     */
    public static LanguageScoreboardLine builder(String key) {
        return (LanguageScoreboardLine) new LanguageScoreboardLine().key(key);
    }

    /**
     * Translates the language scoreboard line by replacing placeholders.
     *
     * @return The translated language scoreboard line
     */
    @Override
    public String translate() {
        return replacePlaceholders(
                Language.getCachedItems().computeIfAbsent(key, k -> getMessageFromDatabase(ELanguageType.ITEM, k))
        );
    }

}