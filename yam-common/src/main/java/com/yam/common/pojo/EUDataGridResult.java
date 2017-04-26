package com.yam.common.pojo;

import java.util.List;

/**
 * 
 * 返回给EasyUI DataGrid的结果
 * @author Yampery
 * @date 2017年3月1日 下午11:07:40
 */
public class EUDataGridResult {
	
	// 模型总数
	private long total;
	// 列表对象
	private List<?> rows;
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
}
