package com.yam.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yam.common.utils.FtpUtil;
import com.yam.common.utils.IDUtils;
import com.yam.service.PictureService;

@Service
public class PictureServiceImpl implements PictureService {

	@Value("${FTP_ADDRESS}")
	private String FTP_ADDRESS;
	@Value("${FTP_PORT}")
	private Integer FTP_PORT;
	@Value("${FTP_USERNAME}")
	private String FTP_USERNAME;
	@Value("${FTP_PASSWORD}")
	private String FTP_PASSWORD;
	@Value("${FTP_BASEPATH}")
	private String FTP_BASEPATH;
	@Value("${IMAGE_BASEURL}")
	private String IMAGE_BASEURL;
	
	final org.apache.log4j.Logger logger = org.apache.log4j.LogManager.getLogger(this.getClass());
	@Override
	public Map uploadPicture(MultipartFile uploadeFile) {
		Map resultMap = new HashMap<>();
		try {
			// 1. 生成一个文件名
			// 取原始文件名
			String oldName = uploadeFile.getOriginalFilename();
			// 生成文件名，使用UUID保证不重复
			// UUID.randomUUID();
			String newName = IDUtils.genImageName();
			newName = newName + oldName.substring(oldName.lastIndexOf('.'));
			String imagePath = new DateTime().toString("/yyyy/MM/dd");
			
			// 2. 图片上传
			boolean result = FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD,
					FTP_BASEPATH, imagePath , newName, uploadeFile.getInputStream());
			// 3. 返回结果
			if (!result) { // 上传失败
				resultMap.put("error", 1);
				resultMap.put("message", "文件上传失败");
				logger.error("result 文件上传失败！");
				return resultMap;
			}
			resultMap.put("error", 0);
			resultMap.put("url", IMAGE_BASEURL + imagePath + "/" + newName);
			logger.info("文件上传成功：" + resultMap.get("url"));
			return resultMap;
			
		} catch (IOException e) {
			logger.error("文件上传失败！异常" + e);
			resultMap.put("error", 1);
			resultMap.put("message", "文件上传失败");
			return resultMap;
		}
		
	}

}














