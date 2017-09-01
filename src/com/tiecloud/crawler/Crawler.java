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
	
	public String searchText = null;
	
	private LogUtil log = new LogUtil(LogUtil.DEFAULT_MODE);
	
	private PropertyUtil prop = new PropertyUtil();
	
	public int searchMode = GENERIC_SEARCH_MODE;
	
	public String crawledString = null;
	
	public void run(){
		if(searchText != null){
			switch(searchMode){
			case GENERIC_SEARCH_MODE:
				crawledString =  genericSearch(searchText);
			case FETCH_USR_INFO_MODE:
				FetchData.getInstance().setInfoName(getTiebaUserInfoName(searchText).split("\n"));
				String[] infoLevelString = getTiebaUserInfoLevel(searchText).split("\n");
				int temp = infoLevelString.length;
				infoLevel = new int[temp];
				for(int i = 0; i < temp; i++){
					infoLevel[i] = (Integer.parseInt(infoLevelString[i]) + (temp - i));
				}
			case FETCH_TIEBA_INFO_MODE:
				
			default:
				
			}
		}else{
			log.write("No input");
		}
			
			
			for(int i = 0; i < infoName.length; i++){
				try{
					System.out.println(i + "-" + infoName[i] + "-" + infoLevel[i]);
					crawlTiebaTitle(0, infoLevel[i] * FetchData.tiebaOffset, infoName[i]);
				}catch(Exception e){
					
				}
			}
	}
	
	public String genericSearch(String text){
		return getPage("https://www.baidu.com/s?wd=" + text);
	}
	
	public void fetchUsrInfo(){
		
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
	
	public void crawlTiebaTitle(int minPageNum, int maxPageNumEx, String tiebaName){
		try {
			File output = new File(prop.getCurrentLoc() 
					+ prop.getPathSep()  + FETCHED_DATA_LOC + prop.getPathSep()
					+ "titles_" + minPageNum + "-" + maxPageNumEx + "_" + tiebaName + ".txt");
			FileWriter writer = new FileWriter(output, false);
			int max = maxPageNumEx*50;
			int min = minPageNum*50;
			
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
				log.write("This Tieba user has locked his private info");
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
	
}
	
