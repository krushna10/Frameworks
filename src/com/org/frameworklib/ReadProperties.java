package com.org.frameworklib;

import java.io.FileReader;

public class ReadProperties {

	static FileReader fileReader ;

	public static void readExternalProperties()
	{
		try{
		fileReader = new FileReader("config.properties");
		Constants.globalProperties.load(fileReader);
		
	}catch(Exception e)
		{
		e.printStackTrace();
		}
	}
}
