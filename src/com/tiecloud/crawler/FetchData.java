package com.tiecloud.crawler;

import com.tiecloud.util.LogUtil;

public class FetchData {
	
	public static final int DEFAULT_TIEBA_OFFSET = 50;
	
	public static final String GENERIC_SEARCH_MODE = "general";
	
	public static final String TIEBA_SEARCH_MODE = "tieba";
	
	public static final String USER_SEARCH_MODE = "user";
	
	public String[] infoName = null;
	
	public int[] infoLevel = null;
	
	public int tiebaOffset = DEFAULT_TIEBA_OFFSET;
	
	private LogUtil log = new LogUtil(LogUtil.WRITE_TO_CONSOLE_MODE);
	
	private static FetchData singleton = new FetchData();
	
	private FetchData(){
		/*
		 * Singleton class keep multiple crawler cooperate 
		 */
	}
	
	private Crawler instantiateTiebaCrawler(String name, String searchText, int start, int end, boolean write){
		Crawler tiebaCrawler = new Crawler(name);
		tiebaCrawler.searchMode = Crawler.FETCH_TIEBA_INFO_MODE;
		tiebaCrawler.searchText = searchText;
		tiebaCrawler.setStartTiebaIndex(start);
		tiebaCrawler.setEndingTiebaIndex(end);
		tiebaCrawler.setWriteTiebaFile(write);
		return tiebaCrawler;
	}
	
	public void crawlInfo(String searchText, String searchMode){
		
		switch(searchMode){
			case GENERIC_SEARCH_MODE:
				Crawler genericCrawler = new Crawler("genericCrawler");
				genericCrawler.searchMode = Crawler.GENERIC_SEARCH_MODE;
				genericCrawler.searchText = searchText;
				genericCrawler.start();
//				genericCrawler.getCrawledString();
			case TIEBA_SEARCH_MODE:
				Crawler tiebaCrawler = instantiateTiebaCrawler("tiebaCrawler", searchText, 0, 
						new Crawler("getMaxPageCrawler").getTiebaMaxPage(searchText), false);
				tiebaCrawler.start();
//				tiebaCrawler.getCrawledString();
			case USER_SEARCH_MODE:
				Crawler usrCrawler = new Crawler("usrCrawler");
				usrCrawler.searchMode = Crawler.FETCH_USR_INFO_MODE;
				usrCrawler.searchText = searchText;
				usrCrawler.start();
				
				Crawler[] tiebaMultiThreadCrawler = new Crawler[infoName.length];				
				for(int i = 0; i < infoName.length; i++){
					log.write(i + "-" + infoName[i] + "-" + infoLevel[i]);
					tiebaMultiThreadCrawler[i] = instantiateTiebaCrawler("TiebaMultiThreadCrawler" + i
							, infoName[i], 0, infoLevel[i] * tiebaOffset, true);
					tiebaMultiThreadCrawler[i].start();
				}
				log.stringToFileNeo(tiebaMultiThreadCrawler[0].getFilePath(), "tempFilePath", false);
				log.execShell(System.getProperties().getProperty("user.dir") + java.io.File.pathSeparator 
						+ "tool" + java.io.File.pathSeparator + "merge.sh");
			default:
//				implement
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
	
}
