package com.yam.search.service.impl;

import javax.annotation.Resource;

import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.stereotype.Service;

import com.yam.search.dao.SearchDao;
import com.yam.search.pojo.SearchResult;
import com.yam.search.service.SearchService;

/**
 * 
 * @see SearchService
 * @author Yampery
 * @date 2017年3月15日 上午1:44:32
 */
@Service
public class SearchServiceImpl implements SearchService {

	@Resource private SearchDao dao;
	
	@Override
	public SearchResult search(String queryString, int page, int rows)
			throws Exception {
		
		// 1. 创建查询对象
		SolrQuery query = new SolrQuery();
		
		// 2. 设置查询条件
		query.setQuery(queryString);
		
		// 3. 设置分页条件
		query.setStart((page - 1) * rows);
		query.setRows(rows);
		
		// 4. 设置默认搜索域--根据solr服务主页
			// 高亮显示
		query.setHighlight(true);
		query.addHighlightField("item_title");
		query.setHighlightSimplePre("<em style=\"color:red\">");
		query.setHighlightSimplePost("</em>");
		
		// 5. 执行查询
		SearchResult searchResult = dao.search(query);
		
		// 6. 结果
			// 总数
		long recordCount = searchResult.getRecordCount();
			// 页数
		long pageCount = (recordCount + rows - 1) / rows;
		
		// 7. 设置结果对象并返回
		searchResult.setRecordCount(recordCount);
		searchResult.setCurPage(page);
		searchResult.setPageCount(pageCount);
		
		return searchResult;
	}

}
