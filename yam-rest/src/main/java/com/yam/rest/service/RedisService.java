package com.yam.rest.service;

import com.yam.common.pojo.YamResult;

/**
 * 
 * Redis数据操作服务
 * @author Yampery
 * @date 2017年3月9日 下午11:39:57
 */
public interface RedisService {

	/**
	 * 按照内容分类id同步
	 * @param contentCid 内容分类id
	 * @return
	 */
	YamResult syncContent(long contentCid);
}
