package com.yam.rest.solrJ;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

/**
 * 
 * solrJ API测试
 * @author Yampery
 * @date 2017年3月14日 下午10:14:44
 */
public class SolrJTest {

	/**
	 * 测试添加文档
	 * @throws Exception
	 */
	@Test
	public void addDocument() throws Exception {
		// 1. 创建连接
		SolrServer solrServer = new HttpSolrServer("http://192.168.1.128:8080/solr");
		// 2. 创建一个文档对象
		SolrInputDocument document = new SolrInputDocument();
		document.addField("id", "test01");
		document.addField("item_title", "测试商品");
		document.addField("item_price", 1000);
		// 3. 把文档对象写入索引库
		solrServer.add(document);
		// 4. 提交
		solrServer.commit();
	}
	
	/**
	 * 测试文档删除
	 * @throws Exception
	 */
	@Test
	public void deleteDocument() throws Exception {
		
		// 1. 创建一个连接
		SolrServer solrServer = new HttpSolrServer("http://192.168.1.128:8080/solr");
		// 2. 添加删除条件
		solrServer.deleteByQuery("*:*");
		// 3. 提交
		solrServer.commit();
	}
	
	@Test
	public void queryDocument() throws Exception {
		// 1. 创建连接
		SolrServer solrServer = new HttpSolrServer("http://192.168.1.128:8080/solr");
		// 2. 创建查询条件
		SolrQuery query = new SolrQuery();		
		query.setQuery("*:*");
		// 3. 执行查询
		QueryResponse response = solrServer.query(query);
		// 4. 取响应结果数据
		SolrDocumentList solrDocumentList = response.getResults();
		for (SolrDocument d : solrDocumentList) {
			System.out.println(d.get("id"));
			System.out.println(d.get("item_title"));
			System.out.println(d.get("item_price"));
			System.out.println(d.get("item_image"));
		}
	}
	
}















