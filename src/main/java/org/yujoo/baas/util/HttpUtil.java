package org.yujoo.baas.util;


import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;



public class HttpUtil {

	
	
	@Value("${server.url}")
	private String serverUrl;
	

	
	CloseableHttpAsyncClient httpClient;
	
	public HttpUtil() {
		httpClient = HttpAsyncClients.createDefault();
		httpClient.start();
	}
	
	public void verify(Object verify) throws HttpException {
		HttpPost httpPost = new HttpPost(serverUrl);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
//		params.add(new BasicNameValuePair("game_id", verify.getGameId()));
//		System.out.println("****************game_id**********************");
//		System.out.println(verify.getGameId());
//		params.add(new BasicNameValuePair("open_id", verify.getOpenId()));
//		System.out.println("****************open_id**********************");
//		System.out.println(verify.getOpenId());
//		params.add(new BasicNameValuePair("sessionid", verify.getSessionId()));
//		System.out.println("****************sessionid**********************");
//		System.out.println(verify.getSessionId());
//		params.add(new BasicNameValuePair("sign", makeSign(verify,secret)));
//		System.out.println("****************sign**********************");
//		System.out.println(makeSign(verify,secret));
		//  设置HTTP POST请求参数  
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
			
			Future<HttpResponse> responseFuture = httpClient.execute(
					httpPost, null);
			HttpResponse response = responseFuture.get();
			HttpEntity entity = response.getEntity();

			String result = EntityUtils.toString(entity);

			if (!"ok".equals(result)) {
				throw new HttpException(result);
			}
		} catch (Exception e) {
			throw new HttpException(e.getMessage());
		}
	}

	
	public  void getMethod()
	{
		
		HttpGet httpGet = new HttpGet(serverUrl);
		StringBuffer uri = new StringBuffer();
//		uri.append("orderId=").append(alphaPayInfo.getOrderId()).append("&");
//		uri.append("openid=").append(alphaPayInfo.getOpenid()).append("&");
//		uri.append("amount=").append(alphaPayInfo.getAmount()).append("&");
//		uri.append("actualAmount=").append(alphaPayInfo.getActualAmount()).append("&");
//		uri.append("extraInfo=").append(alphaPayInfo.getExtraInfo()).append("&");
//		uri.append("success=").append(alphaPayInfo.getSuccess()).append("&");
//		uri.append("msg=").append(alphaPayInfo.getMsg()).append("&");
//		uri.append("created=").append(alphaPayInfo.getCreated()).append("&");
//		uri.append("sign=").append(alphaPayInfo.getSign());
//		System.out.println("*******************uri.toString()****************");
//		System.out.println("*******************"+uri.toString()+"****************");
		//  设置HTTP GET请求参数  
		try {
			httpGet.setURI(new URI(uri.toString()));
			Future<HttpResponse> responseFuture = httpClient.execute(
					httpGet, null);
			HttpResponse response = responseFuture.get();
			HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity);

		} catch (Exception e) {
		
		}
		
	}
	
}
