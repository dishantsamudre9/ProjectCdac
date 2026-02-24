package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	private static Properties prop;
	
	static {
		try {
			prop= new Properties();
			
			String path=System.getProperty("user.dir")
					   + "/src/main/resources/config.properties";
		
		FileInputStream fis=new FileInputStream(path);
		
		prop.load(fis);
		
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
    public static String getProperty(String key) {

        return prop.getProperty(key);
    }

}
