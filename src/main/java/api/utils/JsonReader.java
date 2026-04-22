package api.utils;

import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonReader {

    private static JSONObject jsonObject;

    public static void loadData(String path) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(path)));
            jsonObject = new JSONObject(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static JSONObject getUsers() {
        return jsonObject.getJSONObject("users");
    }
}