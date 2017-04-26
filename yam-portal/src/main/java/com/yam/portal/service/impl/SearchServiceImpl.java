package com.yam.portal.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.yam.common.pojo.YamResult;
import com.yam.common.utils.HttpClientUtil;
import com.yam.portal.pojo.SearchResult;
import com.yam.portal.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService {

	/*@Value("${SEARCH_BASE_URL}")
	private String SEARCH_BASE_URL;*/
	
	@Override
	public SearchResult search(String queryString, int page) {
		
		// 查询参数
		Map<String, String> param = new HashMap<>();
		param.put("q", queryString);
		param.put("page", page + "");
				
		try {
			// 模拟浏览器发送http请求，调用yam-search的服务
			String jsonResult = HttpClientUtil.doGet("http://localhost:8091/search/query", param);
			
			// 转换成java对象
			YamResult yamResult = YamResult.formatToPojo(jsonResult, SearchResult.class);
			if (200 == yamResult.getStatus()) {
				SearchResult result = (SearchResult) yamResult.getData();
				return result;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
