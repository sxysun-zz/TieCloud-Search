package com.tiecloud.nlp;

import com.tiecloud.crawler.Crawler;

public class AnalyzeKumo {
	
	public static final String FILE_FOR_NLP = 
			System.getProperties().getProperty("user.dir") 
			+ java.io.File.pathSeparator
			+ Crawler.FETCHED_DATA_LOC
			+ java.io.File.pathSeparator
			+ "readyForNLP.txt";
}
