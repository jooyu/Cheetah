package org.yujoo.baas.service.imp;

import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.yujoo.baas.service.ElasticsearchService;
import org.yujoo.baas.util.HttpRequest;

import ch.qos.logback.classic.Logger;
@Service
public class ElasticsearchServiceImp  implements ElasticsearchService{

/**
 * 
 * 根据查询内容显示全局查询结果
 */
	
	@Value("${elasticsearch.server.url}")
	private String serverUrl;
	
	CloseableHttpAsyncClient httpClient;
	
	
	private static Logger logger = (Logger) LoggerFactory
			.getLogger(ElasticsearchServiceImp.class);
	
	public ElasticsearchServiceImp() {
		httpClient = HttpAsyncClients.createDefault();
		httpClient.start();
	}




	@Override
	public List<String> getMemberSeq(String queryString) {
		String result=null;
		ArrayList<String> strs=null;
		HttpGet httpGet = new HttpGet(serverUrl);
		logger.info("serverUrl");
		logger.info(serverUrl);
		result=HttpRequest.sendGet(serverUrl, "q="+queryString);
		System.out.println("**************");
		System.out.println(result);
		System.out.println("**************");	 
			//正则匹配
			//,"member_seq:1",  ,(?(member_seq)*),
			Pattern p = Pattern.compile("(.*?),");
		     Matcher m = p.matcher(result);
		     strs = new ArrayList<String>();
		     while (m.find()) {
		    	System.out.println(m.group(1));
		    	 if(m.group(1).indexOf("member_seq")>0)
		    	 {
		    		 strs.add(m.group(1));  
		    		 logger.info(m.group(1));
		    	 }
		                    
		     } 
		 
			
	
		return strs;
		
	}

}
