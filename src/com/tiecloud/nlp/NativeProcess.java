package com.tiecloud.nlp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class NativeProcess {
		
	//take in a split String and give out a sorted one
	public String calculateTimes(String str){
		HashMap<String, Integer> has = new HashMap<String, Integer>();
		String[] slist = str.split("/");
		int l = slist.length;
		for(int i = 0; i < l; i++){
			if(!has.containsKey(slist[i])){
				has.put(slist[i], 1);
			}else{
				has.put(slist[i], has.get(slist[i])+1);
			}
		}
		return sort(has);
	}
 
	private static String sort(HashMap<String,Integer> map){
		StringBuffer sb = new StringBuffer();
	    List<HashMap.Entry<String, Integer>> infoIds = new ArrayList<HashMap.Entry<String, Integer>>(map.entrySet());   
	    Collections.sort(infoIds, new Comparator<HashMap.Entry<String, Integer>>() {     
	        public int compare(HashMap.Entry<String, Integer> o1, HashMap.Entry<String, Integer> o2) {     
	            return (o2.getValue() - o1.getValue());     
	        }
	    });
		for (int i = 0; i < infoIds.size(); i++) {
		   	Entry<String, Integer> id = infoIds.get(i);
//		   	sb.append(id.getKey()).append(" --- ").append(id.getValue()).append("\n");
		   	sb.append(id.getValue()).append("\n");
		}
		return sb.toString();
	} 
	
}
