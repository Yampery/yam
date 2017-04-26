package com.yam.search.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yam.common.pojo.YamResult;
import com.yam.common.utils.ExceptionUtil;
import com.yam.search.mapper.ItemMapper;
import com.yam.search.pojo.Item;
import com.yam.search.service.ItemServcice;

@Service
public class ItemServciceImpl implements ItemServcice {

	@Resource
	private ItemMapper mapper;
	
	// 注入SolrServer对象
	@Autowired
	private SolrServer solrServer;
	
	@Override
	public YamResult importAllItems() {
		try {
			// 1. 查询商品列表
			List<Item> list = mapper.getItemList();
			
			// 2. 将商品信息写入数据库
			for(Item item : list) {
				// 创建一个document对象
				SolrInputDocument document = new SolrInputDocument();
				document.setField("id", item.getId());
				document.setField("item_title", item.getTitle());
				document.setField("item_sell_point", item.getSell_point());
				document.setField("item_price", item.getPrice());
				document.setField("item_image", item.getImage());
				document.setField("item_category_name", item.getCategory_name());
				document.setField("item_desc", item.getItem_des());
				// 写入索引库
				solrServer.add(document);
			}
			
			// 3. 提交
			solrServer.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return YamResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		
		return YamResult.ok();
	}

}
