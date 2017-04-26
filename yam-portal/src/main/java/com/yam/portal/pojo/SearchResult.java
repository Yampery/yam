package com.yam.portal.pojo;

import java.util.List;

/**
 * 
 * 首页搜索商品返回结果
 * @author Yampery
 * @date 2017年3月17日 上午1:20:32
 */
public class SearchResult {

	// 商品列表
	private List<Item> itemList;
	// 总记录
	private long recordCount;
	// 总页数
	private long pageCount;
	// 当前页码
	private long curPage;
	public List<Item> getItemList() {
		return itemList;
	}
	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}
	public long getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(long recordCount) {
		this.recordCount = recordCount;
	}
	public long getPageCount() {
		return pageCount;
	}
	public void setPageCount(long pageCount) {
		this.pageCount = pageCount;
	}
	public long getCurPage() {
		return curPage;
	}
	public void setCurPage(long curPage) {
		this.curPage = curPage;
	}
	
}
