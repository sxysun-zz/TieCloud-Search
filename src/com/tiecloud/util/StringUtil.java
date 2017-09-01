package com.tiecloud.util;

public class StringUtil {
	
	public String getMD5(String md5) {
		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
		        byte[] array = md.digest(md5.getBytes());
		        StringBuffer sb = new StringBuffer();
		        for (int i = 0; i < array.length; ++i) {
		          sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
		       }
		        return sb.toString();
		    } catch (java.security.NoSuchAlgorithmException e) {}
		return null;
	}

	public String noReturns(String _s){
		char[] schar = _s.toCharArray();
		for(int i = 0; i < schar.length; i++){
			if((schar[i] == 0x0D) || (schar[i] == 0x0A))
				schar[i] = 0x00;
		}
		String ret = "";
		for(char c: schar){
			if(c != 0x00)
				ret += c;
		}
		return ret;
	}
	
	public String noNull(String _s){
		char[] schar = _s.toCharArray();
		String ret = "";
		for(int i = 0; i < schar.length; i++){
			if((schar[i] != 0x00))
				ret += schar[i];
		}
		return ret;
	}
	
	public String stringEncrypt(String _s, int code){
		byte[] ns = _s.getBytes();
		byte[] ret = new byte[ns.length];
		for(int i = 0; i < ns.length; i++){
			ret[i] = (byte) (ns[i] + code);
		}
		return new String(ret);
	}
	
	public String stringDecrypt(String _s, int code){
		byte[] ns = _s.getBytes();
		byte[] ret = new byte[ns.length];
		for(int i = 0; i < ns.length; i++){
			ret[i] = (byte) (ns[i] - code);
		}
		return new String(ret);
	}
	
}
