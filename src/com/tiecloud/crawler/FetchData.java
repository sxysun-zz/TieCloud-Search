package com.tiecloud.crawler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class FetchData {
	
	public static final int DEFAULT_TIEBA_OFFSET = 50;
	
	public int tiebaOffset = DEFAULT_TIEBA_OFFSET;
	
	public String[] infoName = null;
	
	public int[] infoLevel = null;
	
	private static FetchData singleton = new FetchData();
	
	private FetchData(){
		/*
		 * Singleton class keep multiple crawler cooperate 
		 */
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
	private void crawlInfo(String _ID){
		Name = _ID;
		Crawler crawler1 = new Crawler();
		crawler1.start();
	}
	
	
}
