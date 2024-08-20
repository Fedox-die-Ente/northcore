package net.fedustria.northcore.i18n;

import net.fedustria.northcore.api.player.impl.APIPlayer;
import net.fedustria.northcore.i18n.impl.LanguageActionbar;

public class LanguageTest {

    public static void main(String[] args) {
        APIPlayer apiPlayer = new APIPlayer();

        Language.loadI18n();

        String message = LanguageActionbar.builder("test_time")
                .player(apiPlayer)
                .addPlaceholder("0", "12")
                .translate();

        System.out.println(message);

        // Wait 5 seconds
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String message2 = LanguageActionbar.builder("test_time")
                .player(apiPlayer)
                .addPlaceholder("0", "12")
                .translate();

        System.out.println(message2);

    }

}
