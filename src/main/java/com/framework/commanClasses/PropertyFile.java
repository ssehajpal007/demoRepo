package com.framework.commanClasses;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFile {

	String value;

	public static String read(String key) throws IOException {
		Properties properties = new Properties();
		FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "\\ExternalFiles\\config.properties");
		properties.load(ip);
		return properties.getProperty(key);
	}
}
