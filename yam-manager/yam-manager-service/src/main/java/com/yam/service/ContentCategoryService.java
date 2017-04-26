package com.yam.service;

import java.util.List;

import com.yam.common.pojo.EUTreeNode;
import com.yam.common.pojo.YamResult;

/**
 * 
 * 商品分类节点内容类
 * @author Yampery
 * @date 2017年3月2日 下午10:06:15
 */
public interface ContentCategoryService {
	
	/**
	 * 获取商品内容分类列表
	 * @param parentId 父节点id
	 * @return
	 */
	List<EUTreeNode> getCategoryList(long parentId);
	
	/**
	 * 添加节点
	 * @param parentId 父节点
	 * @param name 节点名称
	 * @return
	 */
	YamResult insertContentCategory(long parentId, String name);
	
	/**
	 * 删除节点
	 * @param parentId 要删除节点的父节点id
	 * @param id 当前节点id
	 * @return
	 */
	YamResult deleteContentCategory(long parentId, long id);
	
	/**
	 * 更新节点名称
	 * @param id 当前节点id
	 * @param name 要更新的名称
	 * @return
	 */
	YamResult updateContentCategory(long id, String name);
	
}
