package net.fedustria.northcore.i18n.impl;

import net.fedustria.northcore.i18n.LanguageMessage;
import net.fedustria.northcore.i18n.types.ELanguageType;

public class LanguageChatMessage extends LanguageMessage {

    public static LanguageChatMessage builder(String key) {
        return (LanguageChatMessage) new LanguageChatMessage().key(key);
    }

    @Override
    public String translate() {
        // TODO: Implement cache

        String message = getMessageFromDatabase(ELanguageType.CHAT_MESSAGE, key);

        return replacePlaceholders(message);
    }

}
