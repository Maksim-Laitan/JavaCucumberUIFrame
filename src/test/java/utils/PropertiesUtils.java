package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {

    public static String getPropertyValueByKey(String key){
        Properties properties = new Properties();
        String value = "value not found";
        try(InputStream stream = new FileInputStream("config.properties")){
            properties.load(stream);
            value = properties.getProperty(key);
            return value;
        } catch (IOException ioe){
            System.out.println("File config.properties is not found.");
        }
        return value;
    }
}
