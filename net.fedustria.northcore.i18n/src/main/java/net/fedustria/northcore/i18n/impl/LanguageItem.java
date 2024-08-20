package net.fedustria.northcore.i18n.impl;

import net.fedustria.northcore.i18n.Language;
import net.fedustria.northcore.i18n.LanguageMessage;
import net.fedustria.northcore.i18n.types.ELanguageType;

public class LanguageItem extends LanguageMessage {

    public static LanguageItem builder(String key) {
        return (LanguageItem) new LanguageItem().key(key);
    }

    @Override
    public String translate() {
        return replacePlaceholders(
                Language.getCachedItems().computeIfAbsent(key, k -> getMessageFromDatabase(ELanguageType.ITEM, k))
        );
    }

}
