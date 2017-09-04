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
	
	public static final int WRITE_TO_CONSOLE_MODE = 0;
	public static final int WRITE_TO_FILE_MODE = 1;
	public static final int MUTE_MODE = 2;
	public static final String DEFAULT_FILE_LOCATION = "output.txt";
	
	//if 0 then console, if not then file
	public int mode = WRITE_TO_CONSOLE_MODE;
	
	public String fileLoc = DEFAULT_FILE_LOCATION;
	public String prefix = null;
	
	public LogUtil(int mode){
		this.mode = mode;
	}
	
	public void setPrefix(String prefix){
		this.prefix = prefix;
	}
	
	public void setMode(int mode){
		this.mode = mode;
	}
	
	public void setFileLocation(String fileLoc){
		this.fileLoc = fileLoc;
	}
	
	public void write(String input){
		switch(mode){
			case 0:
				System.out.println(prefix + " --- " + input);
			case 1:
				write(prefix + " --- " + input, fileLoc);
			case 2:
				return;
		}
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
	
	public void stringToFileNeo(String content, String file) {
		stringToFileNeo(content, file, true);
	}
	
	public void stringToFileNeo(String content, String file, boolean append) {     
        BufferedWriter out = null;     
        try {     
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, append)));     
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
	
    public int execShell(String shellString) {  
        try {  
            Process process = Runtime.getRuntime().exec(shellString);  
            int exitValue = process.waitFor();  
            if (0 != exitValue) {  
                return exitValue;
            }  
        } catch (Throwable e) {}  
        return 0;
    }  

}
