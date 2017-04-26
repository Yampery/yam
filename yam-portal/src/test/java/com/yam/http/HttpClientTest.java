package com.yam.http;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yam.common.pojo.YamResult;

/**
 * 
 * 测试模拟浏览器发送http请求
 * @author Yampery
 * @date 2017年3月8日 上午12:20:38
 */
public class HttpClientTest {

	/**
	 * 测试get请求
	 * @throws Exception
	 */
	@Test
	public void doGet() throws Exception {
		
		// 1. 创建HttpClient对象(可关闭的)
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		// 2. 创建一个GET对象
		HttpGet get = new HttpGet("http://www.baidu.com");

		// 3. 执行请求
		CloseableHttpResponse response = httpClient.execute(get);
		
		// 4. 判断响应结果
		int statusCode = response.getStatusLine().getStatusCode();
		HttpEntity entity = response.getEntity();
		String string = EntityUtils.toString(entity, "utf-8");
		System.out.println(string);
		
		// 5. 关闭HttpClient
		response.close();
		httpClient.close();
	}
	
	/**
	 * 测试带参数的请求
	 * @throws Exception
	 */
	@Test
	public void doGetWithParam() throws Exception {
		
		// 1. 创建HttpClient对象(可关闭的)
		CloseableHttpClient httpClient = HttpClients.createDefault();
	
		// 2. 创建一个uri对象
		URIBuilder uri = new URIBuilder("http://localhost:8089/rest/content/list/89");
		uri.addParameter("wd", "锤子手机");
		
		// 创建一个含参get对象
		HttpGet get = new HttpGet(uri.build());
		
		// 3. 执行请求
		CloseableHttpResponse response = httpClient.execute(get);
		
		// 4. 判断响应结果
		int statusCode = response.getStatusLine().getStatusCode();
		HttpEntity entity = response.getEntity();
		String string = EntityUtils.toString(entity, "utf-8");
		System.out.println(string);
		
		// 5. 关闭HttpClient
		response.close();
		httpClient.close();
	}
	
	/**
	 * 测试post请求
	 * @throws Exception
	 */
	@Test
	public void doPost() throws Exception {
		
		// 1. 创建一个HttpClient
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		// 2. 创建一个post对象
		HttpPost post = new HttpPost("http://localhost:8090/httpclient/post.html");
		
		// 3. 执行post请求
		CloseableHttpResponse response = httpClient.execute(post);
		
		// 4. 查询请求结果
		String string = EntityUtils.toString(response.getEntity());
		
		System.out.println(string);
		
		// 5. 关闭HttpClient
		response.close();
		httpClient.close();
	}
	
	@Test
	public void doPostWithParam() throws Exception {
		
		// 1. 创建一个HttpClient
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		// 2. 创建一个post对象
		HttpPost post = new HttpPost("http://localhost:8090/httpclient/post.action");
		// 创建一个Entity，用来模拟表单提交
		List<NameValuePair> kvList = new ArrayList<>();
		kvList.add(new BasicNameValuePair("username", "杨鹏锐"));
		kvList.add(new BasicNameValuePair("password", "Yampery"));
		// 包装为Entity对象
		StringEntity entity = new UrlEncodedFormEntity(kvList, "utf-8");		
		// 设置post请求
		post.setEntity(entity);
		
		// 3. 执行post请求
		CloseableHttpResponse response = httpClient.execute(post);
		
		// 4. 查询请求结果
		String string = EntityUtils.toString(response.getEntity());
		
		System.out.println(string);
		
		// 5. 关闭HttpClient
		response.close();
		httpClient.close();
	}
	
}
























