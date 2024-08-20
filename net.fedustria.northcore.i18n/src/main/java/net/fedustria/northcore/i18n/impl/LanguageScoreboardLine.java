package net.fedustria.northcore.i18n.impl;

import net.fedustria.northcore.i18n.LanguageMessage;
import net.fedustria.northcore.i18n.types.ELanguageType;

public class LanguageScoreboardLine extends LanguageMessage {

    public static LanguageScoreboardLine builder(String key) {
        return (LanguageScoreboardLine) new LanguageScoreboardLine().key(key);
    }

    @Override
    public String translate() {
        // TODO: Implement cache

        String message = getMessageFromDatabase(ELanguageType.SCOREBOARD_LINE, key);

        return replacePlaceholders(message);
    }

}
