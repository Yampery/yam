package com.yam.search.dao;

import org.apache.solr.client.solrj.SolrQuery;

import com.yam.search.pojo.SearchResult;

/**
 * 
 * 商品查询DAO层
 * @author Yampery
 * @date 2017年3月15日 上午12:58:25
 */
public interface SearchDao {
	
	/**
	 * 按条件查询
	 * @param query SolrQuery条件
	 * @return
	 * @throws Exception 
	 */
	SearchResult search(SolrQuery query) throws Exception;
}
