package com.yam.search.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Repository;

import com.yam.search.dao.SearchDao;
import com.yam.search.pojo.Item;
import com.yam.search.pojo.SearchResult;

@Repository
public class SearchDaoImpl implements SearchDao {

	@Resource SolrServer solrServer;
	
	@Override
	public SearchResult search(SolrQuery query) throws Exception {
		
		// 返回结果
		SearchResult result = new SearchResult();
		
		// 根据查询条件查询索引库
		QueryResponse response = solrServer.query(query);
		
		// 获取查询结果
		SolrDocumentList documentList = response.getResults();
		
		// 取结果总数
		result.setRecordCount(documentList.getNumFound());
		
		// 商品列表
		List<Item> itemList = new ArrayList<>();
		
		// 取高亮显示
		Map<String, Map<String, List<String>>> highlighting = 
				response.getHighlighting();
		
		// 取商品列表设置属性， 并依此添加到itemList
		for (SolrDocument document : documentList) {
			// 创建一个商品，根据查询 结果   依次设置属性 根据solr服务中已设置保存的字段
			Item item = new Item();
			item.setId((String) document.get("id"));
			
			// 获取高亮显示结果
			List<String> list = highlighting.get(document.get("id")).get("item_title");
			String title = "";
			// 判断是否取到
			if (null != list && 0 < list.size()) 
				title = list.get(0);
			else  
				title = (String) document.get("item_title");
			
			item.setTitle(title);
			item.setImage((String) document.get("item_image"));
			item.setPrice((long) document.get("item_price"));
			item.setSell_point((String) document.get("item_sell_point"));
			item.setCategory_name((String) document.get("item_category_name"));
			
			itemList.add(item);
		} /// for end
		
		// 设置result的itemList
		result.setItemList(itemList);
		return result;
	}

}
