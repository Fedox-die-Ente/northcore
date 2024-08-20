package net.fedustria.northcore.i18n.impl;

import net.fedustria.northcore.i18n.Language;
import net.fedustria.northcore.i18n.LanguageMessage;
import net.fedustria.northcore.i18n.types.ELanguageType;

public class LanguageScoreboardLine extends LanguageMessage {

    public static LanguageScoreboardLine builder(String key) {
        return (LanguageScoreboardLine) new LanguageScoreboardLine().key(key);
    }

    @Override
    public String translate() {
        return replacePlaceholders(
                Language.getCachedItems().computeIfAbsent(key, k -> getMessageFromDatabase(ELanguageType.ITEM, k))
        );
    }

}
