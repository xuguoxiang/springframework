package com.nd.utils;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

@Component
public class ExceptionUtils implements HandlerExceptionResolver{
	private static Logger logger = LoggerFactory.getLogger(ExceptionUtils.class);
	/**
	 * 将异常信息写入日志
	 * @param e
	 */
	public static void printExceptionToLog(Exception e){
		String msg = getExceptionMsg(e);
		logger.error(msg);
	}
	/**
	 * 获取异常信息
	 * @param e
	 * @return
	 */
	public static String getExceptionMsg(Exception e){
		ByteArrayOutputStream out = null;
		PrintStream ps = null;
		String msg = null;
		try {
			out = new ByteArrayOutputStream();
			ps = new PrintStream(out);
			e.printStackTrace(ps);
			msg = out.toString();
		} catch (Exception e2) {
			logger.error("获取异常信息失败！");
		}finally{
			IOUtils.closeQuietly(ps);
			IOUtils.closeQuietly(out);
		}
		return msg;
	} 
	
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object arg2, Exception e) {
		printExceptionToLog(e);
		return null;
	}
}
