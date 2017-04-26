package com.yam.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * 图片上传服务
 * @author Yampery
 * @date 2017年3月5日 上午9:53:33
 */
public interface PictureService {
	
	Map uploadPicture(MultipartFile uploadeFile);
}
