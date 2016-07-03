package com.nd.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileDownLoadUtils {
	private static Logger logger = LoggerFactory.getLogger(FileDownLoadUtils.class);

//	public static void downLoadFile(HttpServletRequest request,
//			HttpServletResponse response, String attachFile, String fileName)
//			throws UnsupportedEncodingException, IOException {
//		File file = new File(attachFile);
//		String contentType = "application/octet-stream";
//		downLoadFile(request, response, file, fileName, contentType);
//	}

	public static void downLoadFile(HttpServletResponse response,
			String attachFile, String fileName)
			throws UnsupportedEncodingException, IOException {
		response.setStatus(200);
		File file = new File(attachFile);
		//fileName = safeFileName(fileName);
		String contentType = "application/octet-stream";
		response.setHeader("Accept-Ranges", "bytes");
		response.setHeader("Accept-Charset", "ISO8859-1");
		response.setHeader("Content-type", contentType);
		response.setHeader("Content-Disposition", "attachment; filename="
				+ fileName);
		response.setHeader("Content-Length", String.valueOf(file.length()));
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		if ((!(file.exists())) || (file.length() == 0L)) {
			response.sendError(404);
			return;
		}
		try {
			bis = new BufferedInputStream(new FileInputStream(file));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[8192];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length)))
				bos.write(buff, 0, bytesRead);
		} catch (IOException localIOException) {
			logger.error("文件下载失败！");
		} finally { 
			IOUtils.closeQuietly(bis);
			IOUtils.closeQuietly(bos);
		}
	}
}
