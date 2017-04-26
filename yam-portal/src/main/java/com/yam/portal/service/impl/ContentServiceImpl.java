package com.yam.portal.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.yam.common.pojo.YamResult;
import com.yam.common.utils.HttpClientUtil;
import com.yam.common.utils.JsonUtils;
import com.yam.pojo.TbContent;
import com.yam.portal.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService {

	@Value("$REST_BASE_URL")
	private String REST_BASE_URL;
	@Value("$REST_INDEX_AD_URL")
	private String REST_INDEX_AD_URL;
	public String getContentList() {
		
		// 1. 调用服务层
		String result = HttpClientUtil.doGet("http://localhost:8089/rest/content/list/89");
		
		//2. 转换字符串位YamResult
		try {
			YamResult yamResult = YamResult.formatToList(result, TbContent.class);
			// 区内容列表
			List<TbContent> list = (List<TbContent>) yamResult.getData();
			List<Map> resultList = new ArrayList<>();
			
			// 3. 创建一个jsp页面要求的pojo以此填入resultList
			for (TbContent content : list) {
				Map map = new HashMap<>();
				map.put("src", content.getPic());
				map.put("height", 240);
				map.put("width", 670);
				map.put("srcB", content.getPic2());
				map.put("widthB", 550);
				map.put("heightB", 240);
				map.put("href", content.getUrl());
				map.put("alt", content.getSubTitle());
				resultList.add(map);
				
				resultList.add(map);
			}
			return JsonUtils.objectToJson(resultList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
