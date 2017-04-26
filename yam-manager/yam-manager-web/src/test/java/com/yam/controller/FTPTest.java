package com.yam.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

/**
 * 
 * 测试ftp服务
 * @author Yampery
 * @date 2017年3月4日 上午2:18:48
 */
public class FTPTest {
	
	private FTPClient client;
	private FileInputStream in;

	@Test
	public void testFtpClient() {
		
		// 1. 创建一个FtpClient对象
		
		
		try {
			client = new FTPClient();
			
			// 2. 创建ftp连接
			client.connect("192.168.1.128", 21);
			
			// 3. 登录ftp服务器
			client.login("yampery", "1");
			/**
			 * 需要参数：服务器文档名
			 * 		  上传文档的io流
			 */
			in = new FileInputStream(new File("C:\\Users\\YangPengrui\\Desktop\\1.png"));
			// 4. 上传文件
			// 设置上传路径
			client.changeWorkingDirectory("/home/yampery/www/images");
			// 需要设置文件格式，要不然乱码
			client.setFileType(FTP.BINARY_FILE_TYPE);
			
			client.storeFile("ypr.jpg", in);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				// 5. 关闭连接
				if (null != in)	in.close();				
				if (null != client) client.logout();
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		}	
					
	}
}
