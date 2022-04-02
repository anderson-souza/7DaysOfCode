import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyManager {

    private PropertyManager() {
    }

    public static String getPropertyValueByKey(String key) {
        try (InputStream input = Main.class.getClassLoader().getResourceAsStream("application.properties")) {

            Properties properties = new Properties();

            properties.load(input);

            return properties.getProperty(key);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
