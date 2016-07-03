package com.nd.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class propertiesFileUtils {
	private static Logger logger = LoggerFactory.getLogger(propertiesFileUtils.class);
	/**
	 * 加载资源文件
	 * @param path
	 * @param chartset
	 * @param properties
	 */
	private static void loadFile(String path, String chartset, Properties properties){
		InputStream in = null;
		Reader reader = null;
		try {
			in = propertiesFileUtils.class.getClassLoader().getResourceAsStream(path);
			reader = new InputStreamReader(in, chartset);
			properties.load(reader);
		} catch (UnsupportedEncodingException e) {
			logger.error("不支持{}编码！", chartset);
		} catch (IOException e) {
			logger.error("资源文件加载失败！");
		}finally{
			IOUtils.closeQuietly(in);
			IOUtils.closeQuietly(reader);
		}
	}
	//根据路径和编码获取资源文件
	public static Properties getPropertiesByPath(String path, String chartset){
		Properties properties = new Properties();
		loadFile(path, chartset, properties);
		return properties;
	}
	//根据路径获取资源文件，默认为UTF-8编码
	public static Properties getPropertiesByPath(String path){
		return getPropertiesByPath(path, "UTF-8");
	}
	
}
