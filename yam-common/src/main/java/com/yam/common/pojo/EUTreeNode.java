package com.yam.common.pojo;

/**
 * 
 * easyUI树形控件节点类
 * @author Yampery
 * @date 2017年3月2日 上午12:49:34
 */
public class EUTreeNode {

	/**
	 * 节点id
	 */
	private long id;
	/**
	 * 节点名称（内容）
	 */
	private String text;
	/**
	 * 节点状态
	 */
	private String state;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
}
