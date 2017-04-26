package com.yam.portal.service;

import com.yam.portal.pojo.SearchResult;

/**
 * 
 * 商品搜索服务接口
 * @author Yampery
 * @date 2017年3月17日 上午1:25:39
 */
public interface SearchService {

	SearchResult search(String queryString, int page);
}
