package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileUtility {
	public String getDataFrompropertiesFile(String key) throws IOException {
		FileInputStream fis=new FileInputStream("./src/main/resources/commondata.properties");
		Properties pr=new Properties();
		pr.load(fis);
		String data=pr.getProperty(key);
		return data;
	}

}
