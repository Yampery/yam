package com.yam.search.service;

import com.yam.search.pojo.SearchResult;

/**
 * 
 * 商品搜索服务层
 * @author Yampery
 * @date 2017年3月15日 上午1:26:39
 */
public interface SearchService {

	/**
	 * 商品搜索分页展示
	 * @param queryString 查询条件(solr中的查询字串)
	 * @param page 当前页码
	 * @param rows 行数
	 * @return
	 * @throws Exception
	 */
	SearchResult search(String queryString, int page, int rows) throws Exception;
}
