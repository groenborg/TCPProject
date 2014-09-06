package utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Grønborg
 */
public class Utility {

    public static Properties initProperties(String serverProperties) {
        Properties properties = new Properties();
        try (InputStream is = new FileInputStream(serverProperties)) {
            properties.load(is);
        } catch (IOException e) {
            System.out.println("unable to load file");
            return null;
        }
        return properties;
    }

}
