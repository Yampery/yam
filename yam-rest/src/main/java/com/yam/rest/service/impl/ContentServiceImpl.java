package com.yam.rest.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.yam.common.utils.JsonUtils;
import com.yam.mapper.TbContentMapper;
import com.yam.pojo.TbContent;
import com.yam.pojo.TbContentExample;
import com.yam.pojo.TbContentExample.Criteria;
import com.yam.rest.dao.JedisClient;
import com.yam.rest.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService {

	@Resource
	private TbContentMapper mapper;
	
	@Resource
	private JedisClient jedisClient;
	@Value("${INDEX_CONTENT_REDIS_KEY}")
	private String INDEX_CONTENT_REDIS_KEY;
	
	@Override
	public List<TbContent> getContentList(long contentCid) {
		
		// 从缓存中取内容
		try {
			String cacheResult = jedisClient.hget(INDEX_CONTENT_REDIS_KEY, contentCid + "");
			if (!StringUtils.isEmpty(cacheResult)) {
				// 转换为list
				List<TbContent> resultList = JsonUtils.jsonToList(cacheResult, TbContent.class);
				return resultList;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 1. 创建查询条件
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(contentCid);
		
		// 2. 执行查询
		List<TbContent> result = mapper.selectByExample(example);
		
		// 添加缓存
		try {
			// 把result转换为字符串
			String cacheString  = JsonUtils.objectToJson(result);
			
			jedisClient.hset(INDEX_CONTENT_REDIS_KEY, contentCid + "", cacheString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	

}
