package com.tiecloud.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class LogUtil {
	
	public static final int DEFAULT_MODE = 0;
	public static final String DEFAULT_FILE_LOCATION = "output.txt";
	
	//if 0 then console, if not then file
	public int mode = DEFAULT_MODE;
	public String fileLoc = DEFAULT_FILE_LOCATION;
	
	public LogUtil(int mode){
		this.mode = mode;
	}
	
	public void setMode(int mode){
		this.mode = mode;
	}
	
	public void setFileLocation(String fileLoc){
		this.fileLoc = fileLoc;
	}
	
	public void write(String input){
		if(mode == 0)
			System.out.println(input);
		else 
			write(input, fileLoc);
	}
	
	public void write(String input, String fileLocation){
		stringToFileNeo(input, fileLocation);
	}
	
	public String readFile(String fileName, String encoding){
		try {
			//encoding like "GBK"
			BufferedReader br= new BufferedReader(new InputStreamReader(new FileInputStream(fileName), encoding));
			StringBuilder build = new StringBuilder("");
			String line = "";
			while((line = br.readLine()) != null){
				build.append(line);
			}
			String analyzed = build.toString();
			br.close();
			return analyzed;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public void fileWrite(String input, String output){
		try {
			FileWriter writer = new FileWriter(output);
			writer.write(input);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void stringToFileOld(String content, String loc){
		byte[] tempCharRead = content.getBytes();
		try{
			FileOutputStream write = new FileOutputStream(loc);
			for(int i = 0; i < tempCharRead.length; i ++){
				write.write((tempCharRead[i]));
			}
			write.close();	
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static void stringToFileNeo(String content, String file) {     
        BufferedWriter out = null;     
        try {     
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));     
            out.write(content);
            out.write("\n");
        } catch (Exception e) {     
            e.printStackTrace();     
        } finally {     
            try {     
                if(out != null){  
                    out.close();     
                }  
            } catch (IOException e) {     
                e.printStackTrace();     
            }     
        }     
    }  

}
