package com.tiecloud.crawler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.tiecloud.util.LogUtil;
import com.tiecloud.util.PropertyUtil;

public class Crawler extends Thread{

	public static final int GENERIC_SEARCH_MODE = 0;
	
	public static final int FETCH_USR_INFO_MODE = 1;
	
	public static final int FETCH_TIEBA_INFO_MODE = 2;
	
	public static final String FETCHED_DATA_LOC = "res";
	
	public static final int DEFAULT_TIEBA_START_INDEX = 0;
	
	public static final int DEFAULT_TIEBA_ENDING_INDEX = 0;
	
	private int startTiebaIndex = DEFAULT_TIEBA_START_INDEX;
	
	private int endTiebaIndex = DEFAULT_TIEBA_ENDING_INDEX;
	
	public String searchText = null;
	
	private LogUtil log = new LogUtil(LogUtil.WRITE_TO_CONSOLE_MODE);
	
	private PropertyUtil prop = new PropertyUtil();
	
	public int searchMode = GENERIC_SEARCH_MODE;
	
	private String crawledString = null;
	
	public String crawlerName = null;
	
	private boolean writeTiebaFile = false;
	
	public String pathLocation = prop.getCurrentLoc() 
			+ prop.getPathSep()  + FETCHED_DATA_LOC + prop.getPathSep() + 
			searchText + prop.getPathSep();
	
	public Crawler(String crawlerName){
		this.crawlerName = crawlerName;
		log.setPrefix(crawlerName);
	}
	
	public void run(){
		if(searchText != null){
			switch(searchMode){
				case GENERIC_SEARCH_MODE:
					crawledString =  genericSearch(searchText);
				case FETCH_USR_INFO_MODE:
					FetchData.getInstance().setInfoName(getTiebaUserInfoName(searchText).split("\n"));
					String[] infoLevelString = getTiebaUserInfoLevel(searchText).split("\n");
					int temp = infoLevelString.length;
					FetchData.getInstance().setInfoLevel(new int[temp]);
					for(int i = 0; i < temp; i++){
						FetchData.getInstance().getinfoLevel()[i] = (Integer.parseInt(infoLevelString[i]) + (temp - i));
					}
					crawledString = "User info searched, see array for detail";
				case FETCH_TIEBA_INFO_MODE:
					crawledString = crawlTiebaTitle(this.startTiebaIndex, this.endTiebaIndex 
							, searchText, writeTiebaFile);
			}
		}else{
			log.write("No input");
			crawledString = "searchText input is null";
		}
	}
	
	public void setFilePath(String path){
		this.pathLocation = path;
	}
	
	public String getFilePath(){
		return this.pathLocation;
	}
	
	public void setWriteTiebaFile(boolean write){
		this.writeTiebaFile = write;
	}
	
	public void setStartTiebaIndex(int start){
		this.startTiebaIndex = start;
	}
	
	public void setEndingTiebaIndex(int end){
		this.endTiebaIndex = end;
	}
	
	public boolean getWriteTiebaFile(){
		return this.writeTiebaFile;
	}
	
	public String getCrawledString(){
		return this.crawledString;
	}
	
	public String genericSearch(String text){
		return getPage("https://www.baidu.com/s?wd=" + text);
	}
	
	public String getTiebaUserInfoName(String ID){
		String userHome = "http://tieba.baidu.com/home/main?un=" + ID + "&fr=home";
		return catchText(userHome, "<div class=\"clearfix u-f-wrap\" id=\"forum_group_wrap\">", "<div class=\"right_aside\">"
				, "<span>", "</span>");
	}
	
	public String getTiebaUserInfoLevel(String ID){
		String userHome = "http://tieba.baidu.com/home/main?un=" + ID + "&fr=home";
		return catchText(userHome, "class=\"clearfix u-f-wrap\"", "<div class=\"right_aside\">"
				, "<span class=\"forum_level lv", "\"></span>");
	}
	
	public String crawlTiebaTitle(int minPageNum, int maxPageNumEx, String tiebaName, boolean writeToFile){
		int max = maxPageNumEx*50;
		int min = minPageNum*50;
		if(writeToFile){
			try {
				File output = new File(pathLocation
						+ "titles_" + minPageNum + "-" + maxPageNumEx + "_" + tiebaName + ".txt");
				FileWriter writer = new FileWriter(output, false);
				for(int i = min; i < max; i += 50){
					writer.write(
							catchText("https://tieba.baidu.com/f?kw=" + tiebaName + "&ie=utf-8&pn=" + i, 
									"threadlist_bright", 
							"thread_list_bottom", "class=\"j_th_tit \">", "</a>"));
				}
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
				log.write(e.getMessage());
			}
			return null;
		}else{
			String output = "";
			for(int i = min; i < max; i += 50){
				output +=
						catchText("https://tieba.baidu.com/f?kw=" + tiebaName + "&ie=utf-8&pn=" + i, 
								"threadlist_bright", 
						"thread_list_bottom", "class=\"j_th_tit \">", "</a>");
			}
			return output;
		}
	}
	
	public String getPage(URL url){
		return getPage(url.toString());
	}
	
	public String getPage(String url){
		URLConnection connection;
		String page = null;
		try {
			connection = new URL(url).openConnection();
			InputStream in = connection.getInputStream();  
		    
		    StringBuilder build = new StringBuilder();
		    BufferedReader br = new BufferedReader(new InputStreamReader(in));
		    String line = "";
		    while ((line = br.readLine()) != null) {
		    	build.append(line);  
		    }
		    page = build.toString();
		    in.close();
		} catch (MalformedURLException e) {
			log.write(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			log.write(e.getMessage());
			e.printStackTrace();
		}
	    return page;
	}
	
	public String catchText(String url, String sTag, String eTag, String eleS, String eleE){
		String page = getPage(url);
		if(page.indexOf(sTag) == -1){
			String withHttp = "http://" + url.substring(8);
			String pageHttp = getPage(withHttp);
			if(pageHttp.indexOf(sTag) == -1){
				log.write("Unable to fetch page info, check if input is valid");
			 	log.write(withHttp + "-" + sTag);
			}
		}
		page = page.substring(page.indexOf(sTag), page.indexOf(eTag));
		int currentIndex = 0;
		String output = null;
		while((currentIndex = page.indexOf(eleS, currentIndex + 1)) > 0){
			output += (page.substring(currentIndex + eleS.length(), page.indexOf(eleE, currentIndex)) + "\n");
		}
		return output;
	}
	
	public int getTiebaMaxPage(String tiebaName){
		return Integer.parseInt(catchText("https://tieba.baidu.com/f?kw=" + tiebaName + "&ie=utf-8&pn=0",
				"next pagination-item", "last pagination-item", "&pn=", " class="));
	}
	
}
	
