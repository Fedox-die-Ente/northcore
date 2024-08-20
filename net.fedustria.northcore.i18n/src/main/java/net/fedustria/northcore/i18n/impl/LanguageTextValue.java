package net.fedustria.northcore.i18n.impl;

import net.fedustria.northcore.i18n.LanguageMessage;
import net.fedustria.northcore.i18n.types.ELanguageType;

public class LanguageTextValue extends LanguageMessage {

    public static LanguageTextValue builder(String key) {
        return (LanguageTextValue) new LanguageTextValue().key(key);
    }

    @Override
    public String translate() {
        // TODO: Implement cache

        String message = getMessageFromDatabase(ELanguageType.TEXT_VALUE, key);

        return replacePlaceholders(message);
    }

}
