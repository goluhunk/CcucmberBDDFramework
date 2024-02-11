package org.gaurav.utils;

import java.io.File;

import java.io.IOException;

import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;


public class ReadJSONData {

	@Test
	public static String readData(String key) throws IOException {
		File file=new File("./Test-Data/User.json");
		return JsonPath.read(file,key);
	}
}
