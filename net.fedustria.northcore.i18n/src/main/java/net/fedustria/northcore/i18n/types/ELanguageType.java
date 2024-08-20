package net.fedustria.northcore.i18n.types;

import lombok.Getter;

/**
 * Enum representing different types of language categories.
 */
public enum ELanguageType {

    /**
     * Represents chat messages.
     */
    CHAT_MESSAGE("messages"),

    /**
     * Represents action bars.
     */
    ACTIONBAR("actionbars"),

    /**
     * Represents titles.
     */
    TITLE("titles"),

    /**
     * Represents scoreboard lines.
     */
    SCOREBOARD_LINE("scoreboardLines"),

    /**
     * Represents text values.
     */
    TEXT_VALUE("textValues"),

    /**
     * Represents items.
     */
    ITEM("items"),

    /**
     * Represents an undefined category.
     */
    UNDEFINED("undefined");

    /**
     * The category associated with the language type.
     */
    @Getter
    private final String category;

    /**
     * Constructor for ELanguageType.
     *
     * @param category The category associated with the language type
     */
    ELanguageType(String category) {
        this.category = category;
    }
}