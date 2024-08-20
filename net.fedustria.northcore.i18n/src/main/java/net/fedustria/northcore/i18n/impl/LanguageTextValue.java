package net.fedustria.northcore.i18n.impl;

import net.fedustria.northcore.i18n.Language;
import net.fedustria.northcore.i18n.LanguageMessage;
import net.fedustria.northcore.i18n.types.ELanguageType;

public class LanguageTextValue extends LanguageMessage {

    public static LanguageTextValue builder(String key) {
        return (LanguageTextValue) new LanguageTextValue().key(key);
    }

    @Override
    public String translate() {
        return replacePlaceholders(
                Language.getCachedTextValues().computeIfAbsent(key, k -> getMessageFromDatabase(ELanguageType.TEXT_VALUE, k))
        );
    }

}
