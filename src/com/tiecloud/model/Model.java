package com.tiecloud.model;

import com.tiecloud.crawler.FetchData;
import com.tiecloud.nlp.AnalyzeKumo;

public class Model{

	public String getCloud(String name, String type){
		
		FetchData.getInstance().crawlInfo(name, type);
		
		return getPath();
	}
	
	public String getPath(){
		return AnalyzeKumo.OUTPUT_IMG_PATH;
	}
	
//	public List getKeyWords(){
//		
//		return null;
//	}
	
}
