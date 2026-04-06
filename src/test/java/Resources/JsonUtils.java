package Resources;

import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;

import java.io.FileReader;

public class JsonUtils {


        private static JSONObject jsonData;
        static {
            try {
                JSONParser parser = new JSONParser();
                jsonData = (JSONObject) parser.parse(new FileReader("src/test/java/Resources/testdata.json"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public static String get(String tc ,String key) {
            JSONObject tcData= (JSONObject) jsonData.get(tc);
            return (String) tcData.get(key);
        }


}
