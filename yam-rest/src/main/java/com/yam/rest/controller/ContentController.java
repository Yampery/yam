package com.yam.rest.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yam.common.pojo.YamResult;
import com.yam.common.utils.ExceptionUtil;
import com.yam.pojo.TbContent;
import com.yam.rest.service.ContentService;

/**
 * 
 * 内容展示控制器
 * @author Yampery
 * @date 2017年3月7日 下午11:46:06
 */
@RestController
@RequestMapping("/content")
public class ContentController {

	
	@Resource
	private ContentService service;
	
	@RequestMapping("/list/{contentCategoryId}")
	public YamResult getContentList(
			@PathVariable Long contentCategoryId) {
		
		List<TbContent> list;
		try {
			list = service.getContentList(contentCategoryId);
		} catch (Exception e) {
			e.printStackTrace();
			return YamResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		
		return YamResult.ok(list);
	}
}














