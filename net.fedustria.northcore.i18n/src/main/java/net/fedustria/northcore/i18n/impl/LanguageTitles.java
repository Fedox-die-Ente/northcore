package net.fedustria.northcore.i18n.impl;

import net.fedustria.northcore.i18n.Language;
import net.fedustria.northcore.i18n.LanguageMessage;
import net.fedustria.northcore.i18n.types.ELanguageType;

public class LanguageTitles extends LanguageMessage {

    public static LanguageTitles builder(String key) {
        return (LanguageTitles) new LanguageTitles().key(key);
    }

    @Override
    public String translate() {
        return replacePlaceholders(
                Language.getCachedTitles().computeIfAbsent(key, k -> getMessageFromDatabase(ELanguageType.TITLE, k))
        );
    }


}
