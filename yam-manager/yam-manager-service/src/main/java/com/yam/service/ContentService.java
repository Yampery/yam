package com.yam.service;
import com.yam.common.pojo.EUDataGridResult;
import com.yam.common.pojo.YamResult;
import com.yam.pojo.TbContent;

/**
 * 
 * 内容管理服务
 * @author Yampery
 * @date 2017年3月7日 下午10:28:53
 */
public interface ContentService {
	
	/**
	 * 获取内容列表
	 * @param parentId 该内容列表父节点
	 * @return
	 */
	EUDataGridResult getContentList(int page, int rows, long id);

	/**
	 * 插入一条内容
	 * @param content 内容pojo
	 * @return
	 */
	YamResult insertContent(TbContent content);
}
