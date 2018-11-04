package com.xyz.testengine.util;

public interface CommonUtils {
	
	
	static String getFileName(String path) {
		int index = path.lastIndexOf("\\");
		return path.substring(index+1);
		}

	
}
