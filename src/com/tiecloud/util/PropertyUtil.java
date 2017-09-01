package com.tiecloud.util;

import java.io.File;

public class PropertyUtil {

	public String getPathSep(){
		return File.separator;
	}
	
	public String getLineSep(){
		return System.getProperties().getProperty("line.separator");
	}
	
	public String getCurrentLoc(){
		return System.getProperties().getProperty("user.dir");
	}
}
