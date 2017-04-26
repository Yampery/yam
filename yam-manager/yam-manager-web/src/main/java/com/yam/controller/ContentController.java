package com.yam.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yam.common.pojo.EUDataGridResult;
import com.yam.common.pojo.YamResult;
import com.yam.pojo.TbContent;
import com.yam.service.ContentService;

/**
 * 
 * 内容管理控制器
 * @author Yampery
 * @date 2017年3月7日 下午10:33:57
 */
@RestController
@RequestMapping("/content")
public class ContentController {

	@Resource
	private ContentService service;
	
	@RequestMapping("/query/list")
	public EUDataGridResult getContentList(int page, int rows, long categoryId) {
		
		return service.getContentList(page, rows, categoryId);
	}
	
	/**
	 * 添加内容
	 * @param content
	 * @return
	 */
	@RequestMapping("/save")
	public YamResult insertContent(TbContent content) {
		
		return service.insertContent(content);
	}
}





