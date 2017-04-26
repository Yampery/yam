package com.yam.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yam.common.utils.JsonUtils;
import com.yam.service.PictureService;

/**
 * 
 * 上传图片处理
 * @author Yampery
 * @date 2017年3月5日 上午10:25:32
 */
@RestController
public class PictureController {

	@Resource 
	private PictureService service;
	
	@RequestMapping("/pic/upload")
	public String pictureUpload(MultipartFile uploadFile) {
		@SuppressWarnings("rawtypes")
		Map result = service.uploadPicture(uploadFile);
		return JsonUtils.objectToJson(result);
	}
}























