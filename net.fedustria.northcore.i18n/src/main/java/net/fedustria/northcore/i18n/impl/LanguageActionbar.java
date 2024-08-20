package net.fedustria.northcore.i18n.impl;

import net.fedustria.northcore.i18n.LanguageMessage;
import net.fedustria.northcore.i18n.types.ELanguageType;

public class LanguageActionbar extends LanguageMessage {

    public static LanguageActionbar builder(String key) {
        return (LanguageActionbar) new LanguageActionbar().key(key);
    }

    @Override
    public String translate() {
        // TODO: Implement cache

        String message = getMessageFromDatabase(ELanguageType.ACTIONBAR, key);

        return replacePlaceholders(message);
    }

}
