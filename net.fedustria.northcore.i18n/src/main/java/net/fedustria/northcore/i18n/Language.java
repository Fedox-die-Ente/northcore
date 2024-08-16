package net.fedustria.northcore.i18n;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public class Language {

    public static void main(String[] args) throws IOException {


        InputStream inputStream = Language.class.getClassLoader().getResourceAsStream("languages/languages.json");

        if (inputStream == null) {
            throw new RuntimeException("Cannot find resource file languages/languages.json");
        }

        ObjectMapper objectMapper = new ObjectMapper();

        System.out.println(objectMapper.readValue(inputStream, Language.class));

    }

}
