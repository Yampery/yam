package com.yam.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yam.common.pojo.EUTreeNode;
import com.yam.common.pojo.YamResult;
import com.yam.service.ContentCategoryService;

/**
 * 
 * 商品类目访问控制器
 * @author Yampery
 * @date 2017年3月2日 下午10:24:42
 */
@RestController
@RequestMapping("/content/category")
public class ContentCategoryController {
	
	@Resource
	private ContentCategoryService service;
	
	/**
	 * 访问商品内容管理树
	 * @param parentId 父节点
	 * @return
	 */
	@RequestMapping("/list")
	public List<EUTreeNode> getContentCatList(
			@RequestParam(value="id", defaultValue="0") long parentId) {
		
		return service.getCategoryList(parentId);
	}
	
	/**
	 * 创建节点控制器
	 * @param parentId 父节点（在哪个节点下面创建）
	 * @param name 节点名称
	 * @return
	 */
	@RequestMapping("/create")
	public YamResult insertContentCategory(long parentId, String name) {
		
		return service.insertContentCategory(parentId, name);
	}
	
	/**
	 * 删除节点
	 * @param parentId 父节点
	 * @param id 要删除的节点
	 * @return
	 */
	@RequestMapping("/delete")
	public YamResult deleteContentCategory(long parentId, long id) {
		
		return service.deleteContentCategory(parentId, id);
	}
	
	/**
	 * 更改节点名称
	 * @param id 要更改的节点id
	 * @param name 名称
	 * @return
	 */
	@RequestMapping("/update")
	public YamResult updateContentCategory(long id, String name) {
		
		return service.updateContentCategory(id, name);
	}
}


















