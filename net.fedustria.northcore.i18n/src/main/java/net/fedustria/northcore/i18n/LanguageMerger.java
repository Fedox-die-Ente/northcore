package net.fedustria.northcore.i18n;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class LanguageMerger {

    /**
     * ObjectMapper instance for JSON processing.
     */
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Main method to start the language merging process.
     *
     * @param args Command line arguments
     * @throws IOException If an I/O error occurs
     */
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

    /**
     * Merges JSON files for a specific language key into the result ObjectNode.
     *
     * @param result            The ObjectNode to merge the JSON files into
     * @param languageKey       The language key to merge JSON files for
     * @param languageDirectory The directory containing language-specific JSON files
     * @throws IOException If an I/O error occurs
     */
    private static void mergeJsonFiles(ObjectNode result, String languageKey, File languageDirectory) throws IOException {
        // List of JSON file names to merge
        String[] filesToMerge = {"messages", "titles", "actionbars", "scoreboardLines", "textValues", "items"};

        for (String fileName : filesToMerge) {
            // Construct the file path for the current JSON file
            File jsonFile = new File(languageDirectory, languageKey + "/" + fileName + ".json");
            if (jsonFile.exists()) {
                // Read the content of the JSON file
                JsonNode fileContent = objectMapper.readTree(jsonFile);
                // Get or create the target node in the result ObjectNode
                ObjectNode targetNode = (ObjectNode) result.get(fileName);
                if (targetNode == null) {
                    targetNode = objectMapper.createObjectNode();
                    result.set(fileName, targetNode);
                }
                // Set the content of the JSON file into the target node
                targetNode.set(languageKey, fileContent);
            }
        }
    }

    public static String getDefaultJSONFile() throws IOException {
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

        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(result);
    }
}