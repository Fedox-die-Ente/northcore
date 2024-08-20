package net.fedustria.northcore.i18n.types;

import lombok.Getter;

public enum ELanguageType {

    CHAT_MESSAGE("messages"),
    ACTIONBAR("actionbars"),
    TITLE("titles"),
    SCOREBOARD_LINE("scoreboardLines"),
    TEXT_VALUE("textValues"),
    ITEM("items"),
    UNDEFINED("undefined");

    @Getter
    private final String category;

    ELanguageType(String category) {
        this.category = category;
    }
}
