package com.wmz.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.stream.FileImageInputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.apache.tomcat.util.http.fileupload.FileItemStream;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.util.Streams;

import com.wmz.common.entity.ImgFileInfo;


public class FileUploadUtil{

	public static byte[] getImgByPath(String path) {
		return image2byte(path);
	}
	
	/**
	 * 将图片文件转换成byte数组
	 * @param path 文件路径
	 * @return
	 */
	private static byte[] image2byte(String path) {
		byte[] data = null;
		FileImageInputStream input = null;
		try {
			input = new FileImageInputStream(new File(path));
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			int numBytesRead = 0;
			while ((numBytesRead = input.read(buf)) != -1) {
				output.write(buf, 0, numBytesRead);
			}
			data = output.toByteArray();
			output.close();
			input.close();
		} catch (FileNotFoundException ex1) {
			//ex1.printStackTrace();
		} catch (IOException ex1) {
			//ex1.printStackTrace();
		}
		return data;
	}
	
	
	/**
	 * 上传图片文件
	 * 
	 * @param request
	 * @param upUrl
	 *            上传的路径
	 * @return 上成功后的图片文件信息
	 */
	public static ImgFileInfo fileUpload(HttpServletRequest request, String upUrl) {
		ImgFileInfo imgFileInfo = new ImgFileInfo();
		String contextpath = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + contextpath + "/";
		try {
			// 设置上传的路径
			String realPath = upUrl;

			// 判断路径是否存在，不存在则创建
			File dir = new File(realPath);
			if (!dir.isDirectory()) {
				dir.mkdir();
			}

			if (ServletFileUpload.isMultipartContent(request)) {

				DiskFileItemFactory dff = new DiskFileItemFactory();
				dff.setRepository(dir);
				dff.setSizeThreshold(1024000);
				ServletFileUpload sfu = new ServletFileUpload(dff);
				FileItemIterator fii = null;
				fii = sfu.getItemIterator(request);
				String title = ""; // 图片标题
				String url = ""; // 服务器图片地址
				String fileName = "";
				String realFileName = "";
				while (fii.hasNext()) {
					FileItemStream fis = fii.next();
					try {
						if (!fis.isFormField() && fis.getName().length() > 0) {
							fileName = fis.getName();
							// 保存 图片原始名字
							imgFileInfo.setImgName(fileName);
							// 获取文件扩展名
							String imgType = fileName.substring(
									fileName.lastIndexOf("."),
									fileName.length());
							// 验证文件扩展名
							Pattern reg = Pattern
									.compile("[.]jpg|png|jpeg|gif$");
							Matcher matcher = reg.matcher(imgType);
							if (!matcher.find()) {
								imgFileInfo.setSuccessful(false);
								imgFileInfo.setErrInfo("文件类型不允许！");
								imgFileInfo.setSuccessful(false);
								break;
							}
							imgFileInfo.setSuccessful(true);
							//保存文件扩展名
							
							imgFileInfo.setImgType(imgType);
							// 生成服务器文件名
							realFileName = new Date().getTime()
									+ fileName.substring(
											fileName.lastIndexOf("."),
											fileName.length());
							imgFileInfo.setStoreImgName(realFileName);
							// 上传的图片 存储的路径
							url = realPath  + realFileName;
							// 保存 服务器图片地址
							imgFileInfo.setStoreImgAddr(url);
							BufferedInputStream in = new BufferedInputStream(
									fis.openStream());
							FileOutputStream a = new FileOutputStream(new File(
									url));
							BufferedOutputStream output = new BufferedOutputStream(
									a);
							Streams.copy(in, output, true);// 开始把文件写到你指定的上传文件夹
						} else {
							String fname = fis.getFieldName();
							if (fname.indexOf("pictitle") != -1) {
								BufferedInputStream in = new BufferedInputStream(
										fis.openStream());
								byte c[] = new byte[10];
								int n = 0;
								while ((n = in.read(c)) != -1) {
									title = new String(c, 0, n);
									break;
								}
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return imgFileInfo;
	}
	
	/**
	 * 从config.properties中获取某个字段的值
	 * 
	 * @param request
	 * @param file
	 *            字段名称
	 * @return
	 */
	public static String getFile(HttpServletRequest request, String file) {
		String File = "D:/cpc_up";
		
		return File;
	}

}
