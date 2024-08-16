package net.fedustria.northcore.i18n;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class LanguageMerger {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {

        File languagesJsonFile = new File(LanguageMerger.class.getClassLoader().getResource("languages/languages.json").getFile());
        File languageDirectory = new File(LanguageMerger.class.getClassLoader().getResource("languages/").getFile());

        ObjectNode result = (ObjectNode) objectMapper.readTree(languagesJsonFile);

        JsonNode languages = result.get("languages");
        Iterator<Map.Entry<String, JsonNode>> languageEntries = languages.fields();

        while (languageEntries.hasNext()) {
            Map.Entry<String, JsonNode> entry = languageEntries.next();
            String languageKey = entry.getValue().get("key").asText();

            mergeJsonFiles(result, languageKey, languageDirectory);
        }

        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(result));
    }

    private static void mergeJsonFiles(ObjectNode result, String languageKey, File languageDirectory) throws IOException {
        String[] filesToMerge = {"messages", "titles", "actionbars", "scoreboardLines", "textValues", "items"};

        for (String fileName : filesToMerge) {
            File jsonFile = new File(languageDirectory, languageKey + "/" + fileName + ".json");
            if (jsonFile.exists()) {
                JsonNode fileContent = objectMapper.readTree(jsonFile);
                ObjectNode targetNode = (ObjectNode) result.get(fileName);
                if (targetNode == null) {
                    targetNode = objectMapper.createObjectNode();
                    result.set(fileName, targetNode);
                }
                targetNode.set(languageKey, fileContent);
            }
        }
    }
}