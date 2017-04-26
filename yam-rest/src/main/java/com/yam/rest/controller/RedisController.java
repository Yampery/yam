package com.yam.rest.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yam.common.pojo.YamResult;
import com.yam.rest.service.RedisService;

/**
 * 
 * 缓存同步Controller
 * @author Yampery
 * @date 2017年3月9日 下午11:47:07
 */
@RestController
@RequestMapping("/cache/sync")
public class RedisController {

	@Resource private RedisService service;
	
	/**
	 * 缓存同步
	 * @param contentCid
	 * @return
	 */
	@RequestMapping("/content/{contentCid}")
	public YamResult contentCacheSync(@PathVariable Long contentCid) {
		
		return service.syncContent(contentCid);
	}
}
