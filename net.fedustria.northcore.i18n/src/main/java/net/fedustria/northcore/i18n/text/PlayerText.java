package net.fedustria.northcore.i18n.text;

import lombok.Data;
import net.fedustria.northcore.api.player.impl.APIPlayer;

@Data
public class PlayerText {

    private StringBuilder builder = new StringBuilder();

    public PlayerText text(String text) {
        builder.append(text);
        return this;
    }

    public PlayerText nextLine() {
        builder.append("§7\n");
        return this;
    }

    public PlayerText coins() {
        builder.append("%coins%");
        return this;
    }

    public PlayerText messageSplitter() {
        builder.append("%splitter%");
        return this;
    }

    public PlayerText serverName() {
        builder.append("%servername%");
        return this;
    }

    public PlayerText arrow() {
        builder.append("%arrow%");
        return this;
    }

    public PlayerText motto() {
        builder.append("%motto%");
        return this;
    }

    public PlayerText prefix() {
        builder.append("%prefix%");
        return this;
    }

    public PlayerText networkNameWithColors() {
        builder.append("%nnwc%");
        return this;
    }

    public PlayerText nn() {
        builder.append("%nn%");
        return this;
    }

    public PlayerText playerName() {
        builder.append("%playerName%");
        return this;
    }

    public PlayerText clanTag() {
        builder.append("%clan%");
        return this;
    }

    public PlayerText prefixSplitter() {
        builder.append("%prefixSplitter%");
        return this;
    }

    public PlayerText space() {
        builder.append(" ");
        return this;
    }

    public PlayerText firstColor() {
        builder.append("%c1%");
        return this;
    }

    public PlayerText secondColor() {
        builder.append("%c2%");
        return this;
    }

    public String result() {
        return builder.toString();
    }

    public String result(APIPlayer apiPlayer) {
        return apiPlayer.replacePlaceholder(builder.toString());
    }
}
