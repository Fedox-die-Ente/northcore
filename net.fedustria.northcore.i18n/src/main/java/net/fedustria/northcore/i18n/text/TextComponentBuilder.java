package net.fedustria.northcore.i18n.text;

import lombok.Data;
import net.fedustria.northcore.api.player.impl.APIPlayer;

/**
 * Builder class for creating text components with various placeholders.
 */
@Data
public class TextComponentBuilder {

    /**
     * StringBuilder instance to build the text component.
     */
    private StringBuilder builder = new StringBuilder();

    /**
     * Appends the given text to the text component.
     *
     * @param text The text to append
     * @return The current TextComponentBuilder instance
     */
    public TextComponentBuilder text(String text) {
        builder.append(text);
        return this;
    }

    /**
     * Appends a new line character to the text component.
     *
     * @return The current TextComponentBuilder instance
     */
    public TextComponentBuilder nextLine() {
        builder.append("§7\n");
        return this;
    }

    /**
     * Appends the placeholder for coins to the text component.
     *
     * @return The current TextComponentBuilder instance
     */
    public TextComponentBuilder coins() {
        builder.append("%coins%");
        return this;
    }

    /**
     * Appends the placeholder for message splitter to the text component.
     *
     * @return The current TextComponentBuilder instance
     */
    public TextComponentBuilder messageSplitter() {
        builder.append("%splitter%");
        return this;
    }

    /**
     * Appends the placeholder for server name to the text component.
     *
     * @return The current TextComponentBuilder instance
     */
    public TextComponentBuilder serverName() {
        builder.append("%servername%");
        return this;
    }

    /**
     * Appends the placeholder for arrow to the text component.
     *
     * @return The current TextComponentBuilder instance
     */
    public TextComponentBuilder arrow() {
        builder.append("%arrow%");
        return this;
    }

    /**
     * Appends the placeholder for motto to the text component.
     *
     * @return The current TextComponentBuilder instance
     */
    public TextComponentBuilder motto() {
        builder.append("%motto%");
        return this;
    }

    /**
     * Appends the placeholder for prefix to the text component.
     *
     * @return The current TextComponentBuilder instance
     */
    public TextComponentBuilder prefix() {
        builder.append("%prefix%");
        return this;
    }

    /**
     * Appends the placeholder for network name with colors to the text component.
     *
     * @return The current TextComponentBuilder instance
     */
    public TextComponentBuilder networkNameWithColors() {
        builder.append("%nnwc%");
        return this;
    }

    /**
     * Appends the placeholder for network name to the text component.
     *
     * @return The current TextComponentBuilder instance
     */
    public TextComponentBuilder nn() {
        builder.append("%nn%");
        return this;
    }

    /**
     * Appends the placeholder for player name to the text component.
     *
     * @return The current TextComponentBuilder instance
     */
    public TextComponentBuilder playerName() {
        builder.append("%playerName%");
        return this;
    }

    /**
     * Appends the placeholder for clan tag to the text component.
     *
     * @return The current TextComponentBuilder instance
     */
    public TextComponentBuilder clanTag() {
        builder.append("%clan%");
        return this;
    }

    /**
     * Appends the placeholder for prefix splitter to the text component.
     *
     * @return The current TextComponentBuilder instance
     */
    public TextComponentBuilder prefixSplitter() {
        builder.append("%prefixSplitter%");
        return this;
    }

    /**
     * Appends a space character to the text component.
     *
     * @return The current TextComponentBuilder instance
     */
    public TextComponentBuilder space() {
        builder.append(" ");
        return this;
    }

    /**
     * Appends the placeholder for the first color to the text component.
     *
     * @return The current TextComponentBuilder instance
     */
    public TextComponentBuilder firstColor() {
        builder.append("%c1%");
        return this;
    }

    /**
     * Appends the placeholder for the second color to the text component.
     *
     * @return The current TextComponentBuilder instance
     */
    public TextComponentBuilder secondColor() {
        builder.append("%c2%");
        return this;
    }

    /**
     * Builds the text component as a string.
     *
     * @return The built text component
     */
    public String build() {
        return builder.toString();
    }

    /**
     * Builds the text component as a string with placeholders replaced by the given APIPlayer.
     *
     * @param apiPlayer The APIPlayer instance to replace placeholders
     * @return The built text component with placeholders replaced
     */
    public String build(APIPlayer apiPlayer) {
        return apiPlayer.replacePlaceholder(builder.toString());
    }
}