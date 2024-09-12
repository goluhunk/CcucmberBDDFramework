package org.gaurav.utils;

import java.io.File;

import java.io.IOException;



import com.jayway.jsonpath.JsonPath;


public class ReadJSONData {


	public static String readData(String user,String key) throws IOException {
		File file=new File("./Test-Data/"+user+".json");
		return JsonPath.read(file,key);
	}
}
