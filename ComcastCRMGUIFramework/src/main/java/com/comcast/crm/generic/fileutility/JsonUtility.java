package com.comcast.crm.generic.fileutility;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonUtility {
	public String getDataFromJsonFile(String key) throws IOException, ParseException {
		FileReader fr=new FileReader("./src/main/resources/coomonData.json");
		
		JSONParser parser=new JSONParser();
		Object obj=parser.parse(fr);
		//hashmap is key and value pair we have to convert obj into json object
		//for conversion we have to do downcast becoz object class is superclass
		JSONObject map=(JSONObject)obj;
		String data=(String)map.get(key);
		return data;
		
	}

}
