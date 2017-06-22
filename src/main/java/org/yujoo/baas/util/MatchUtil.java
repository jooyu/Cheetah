package org.yujoo.baas.util;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatchUtil {
	

	public static void main(String[] args) {
		getStrings("\"version\":1,\"member_seq\":1,\"status\":1,\"version\":1,\"member_seq\":1,\"status\":1,\"play_dt\":1492591387254,\"time_zone_distance\":32400,\"level\":5,\"exp\":10470,\"last_used_char\":100300,\"last_used_pet\":202900,\"last_comeback_request_dt\":0,",
				",(.*?),"		
				);

	}
	
	
    private static void getStrings(String str,String patternStr) {
        
   	 Pattern p = Pattern.compile(patternStr);
     Matcher m = p.matcher(str);
     ArrayList<String> strs = new ArrayList<String>();
     while (m.find()) {
    	
    	 if(m.group(1).indexOf("member_seq")>0)
    	 {
    		 strs.add(m.group(1));  
    	 }
                    
     } 
     for (String string : strs) {
		System.out.println(string);
	}
}
    
    
  
    
}
