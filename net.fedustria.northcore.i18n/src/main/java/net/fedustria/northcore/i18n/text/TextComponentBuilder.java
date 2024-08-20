package net.fedustria.northcore.i18n.text;

import lombok.Data;
import net.fedustria.northcore.api.player.impl.APIPlayer;

@Data
public class TextComponentBuilder {

    private StringBuilder builder = new StringBuilder();

    public TextComponentBuilder text(String text) {
        builder.append(text);
        return this;
    }

    public TextComponentBuilder nextLine() {
        builder.append("§7\n");
        return this;
    }

    public TextComponentBuilder coins() {
        builder.append("%coins%");
        return this;
    }

    public TextComponentBuilder messageSplitter() {
        builder.append("%splitter%");
        return this;
    }

    public TextComponentBuilder serverName() {
        builder.append("%servername%");
        return this;
    }

    public TextComponentBuilder arrow() {
        builder.append("%arrow%");
        return this;
    }

    public TextComponentBuilder motto() {
        builder.append("%motto%");
        return this;
    }

    public TextComponentBuilder prefix() {
        builder.append("%prefix%");
        return this;
    }

    public TextComponentBuilder networkNameWithColors() {
        builder.append("%nnwc%");
        return this;
    }

    public TextComponentBuilder nn() {
        builder.append("%nn%");
        return this;
    }

    public TextComponentBuilder playerName() {
        builder.append("%playerName%");
        return this;
    }

    public TextComponentBuilder clanTag() {
        builder.append("%clan%");
        return this;
    }

    public TextComponentBuilder prefixSplitter() {
        builder.append("%prefixSplitter%");
        return this;
    }

    public TextComponentBuilder space() {
        builder.append(" ");
        return this;
    }

    public TextComponentBuilder firstColor() {
        builder.append("%c1%");
        return this;
    }

    public TextComponentBuilder secondColor() {
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
