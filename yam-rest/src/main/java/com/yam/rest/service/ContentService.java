package com.yam.rest.service;

import java.util.List;

import com.yam.pojo.TbContent;

/**
 * 
 * 内容管理
 * @author Yampery
 * @date 2017年3月7日 下午11:40:24
 */
public interface ContentService {
	
	/**
	 * 获取内容列表
	 * @param contentCid
	 * @return
	 */
	List<TbContent> getContentList(long contentCid);
}
