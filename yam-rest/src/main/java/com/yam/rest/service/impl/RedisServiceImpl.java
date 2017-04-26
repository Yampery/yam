package com.yam.rest.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.yam.common.pojo.YamResult;
import com.yam.common.utils.ExceptionUtil;
import com.yam.rest.dao.JedisClient;
import com.yam.rest.service.RedisService;

@Service
public class RedisServiceImpl implements RedisService {

	@Resource private JedisClient jedisclient;
	@Value("${INDEX_CONTENT_REDIS_KEY}")
	private String INDEX_CONTENT_REDIS_KEY;
	@Override
	public YamResult syncContent(long contentCid) {
		
		try {
			jedisclient.hdel(INDEX_CONTENT_REDIS_KEY, contentCid + "");
			
		} catch (Exception e) {
			e.printStackTrace();
			return YamResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		
		return YamResult.ok();
	}

}
