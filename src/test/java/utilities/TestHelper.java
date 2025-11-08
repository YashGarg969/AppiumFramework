package utilities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.IOException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestHelper {

    private static final ObjectMapper mapper= new ObjectMapper();

    /**
     * This method is intended to parse json arrays and extract particular json object based on index
     * @param jsonArrayFile
     * @param index
     * @return JsonNode
     */
    public static JsonNode extractJsonFromJsonArray(File jsonArrayFile, int index) throws IOException {
        JsonNode jsonNode = mapper.readTree(jsonArrayFile);
        return jsonNode.get(index);
    }
}
