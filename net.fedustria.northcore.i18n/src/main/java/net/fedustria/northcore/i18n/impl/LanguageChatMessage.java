package net.fedustria.northcore.i18n.impl;

import net.fedustria.northcore.i18n.Language;
import net.fedustria.northcore.i18n.LanguageMessage;
import net.fedustria.northcore.i18n.types.ELanguageType;

/**
 * Class for handling language chat messages.
 */
public class LanguageChatMessage extends LanguageMessage {

    /**
     * Creates a new LanguageChatMessage instance with the specified key.
     *
     * @param key The key for the language chat message
     * @return A new LanguageChatMessage instance
     */
    public static LanguageChatMessage builder(String key) {
        return (LanguageChatMessage) new LanguageChatMessage().key(key);
    }

    /**
     * Translates the language chat message by replacing placeholders.
     *
     * @return The translated language chat message
     */
    @Override
    public String translate() {
        return replacePlaceholders(
                Language.getCachedChatMessages().computeIfAbsent(key, k -> getMessageFromDatabase(ELanguageType.CHAT_MESSAGE, k))
        );
    }

}