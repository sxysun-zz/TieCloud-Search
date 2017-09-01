package com.tiecloud.crawler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class FetchData {
	
	public static final int DEFAULT_TIEBA_OFFSET = 50;
	
	public static final String GENERIC_SEARCH_MODE = "general";
	
	public static final String TIEBA_SEARCH_MODE = "tieba";
	
	public static final String USER_SEARCH_MODE = "user";
	
	public String[] infoName = null;
	
	public int[] infoLevel = null;
	
	public int tiebaOffset = DEFAULT_TIEBA_OFFSET;
	
	private static FetchData singleton = new FetchData();
	
	private FetchData(){
		/*
		 * Singleton class keep multiple crawler cooperate 
		 */
	}
	
	private void crawlInfo(String searchText, String searchMode){
		
		switch(searchMode){
			case GENERIC_SEARCH_MODE:
				Crawler genericCrawler = new Crawler();
				genericCrawler.searchMode = Crawler.GENERIC_SEARCH_MODE;
				genericCrawler.start();
			case TIEBA_SEARCH_MODE:
				Crawler tiebaCrawler = new Crawler();
				tiebaCrawler.searchMode = Crawler.FETCH_TIEBA_INFO_MODE;
				tiebaCrawler.start();
			case USER_SEARCH_MODE:
				
		}
		
		for(int i = 0; i < infoName.length; i++){
			try{
				System.out.println(i + "-" + infoName[i] + "-" + infoLevel[i]);
				crawlTiebaTitle(0, infoLevel[i] * tiebaOffset, infoName[i]);
			}catch(Exception e){
				
			}
		}
	}
	
	public static FetchData getInstance(){
		return singleton;
	}
	
	public void setTiebaOffset(int neo){
		tiebaOffset = neo;
	}
	
	public void setInfoName(String[] infoName){
		this.infoName = infoName;
	}
	
	public void setInfoLevel(int[] infoLevel){
		this.infoLevel = infoLevel;
	}
	
	public int getTiebaOffset(){
		return tiebaOffset;
	}
	
	public String[] getInfoName(){
		return infoName;
	}
	
	public int[] getinfoLevel(){
		return infoLevel;
	}
	
/*
	private void ASyncCrawlText(int _pages, String _name){
//		_t.setText(catchText(_in.getText(), "<div id=\"middlew8\">", "<div id=\"bottomw8\">", "title=\"", "\" >"));
		Name = _name;
		CrawlerIns crawler1 = new CrawlerIns();
		CrawlerIns crawler2 = new CrawlerIns();
		CrawlerIns crawler3 = new CrawlerIns();
		CrawlerIns crawler4 = new CrawlerIns();
		CrawlerIns crawler5 = new CrawlerIns();
		CrawlerIns crawler6= new CrawlerIns();
		CrawlerIns crawler7 = new CrawlerIns();
		
		//add the number of pages to download
		
		crawler1.start();
		crawler2.start();
		crawler3.start();
		crawler4.start();
		crawler5.start();
		crawler6.start();
		crawler7.start();
	}
*/	
	
}
