package com.dev.base.util;

import java.io.IOException;
import java.io.Writer;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.dev.base.utils.PropertiesUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 
		* <p>Title: 模板工具类</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年10月25日下午10:21:16</p>
 */
public class FreeMarkerUtil {
	// 配置信息
	private final static Properties cfgProperties = PropertiesUtils.getProperties("freemarker.properties");
	
	//配置信息
	private static Configuration config = initFtlCfg();
	
	//模板
	private static Template apiDocTemplate = null;
	
	static{
		try {
			apiDocTemplate = config.getTemplate("doc.ftl");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//初始化配置信息
	private static Configuration initFtlCfg(){
		//编码
		String encoding = cfgProperties.getProperty("encoding");
		
		//模板读取目录
		String ftlDir = cfgProperties.getProperty("ftl.dir");
		Resource resource = new ClassPathResource(ftlDir);
		
		config = new Configuration(Configuration.VERSION_2_3_22);
		config.setDefaultEncoding(encoding);
		config.setOutputEncoding(encoding);
		config.setClassicCompatible(true);
		try {
			config.setDirectoryForTemplateLoading(resource.getFile());
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return config;
	}
	
	/**
	 * 
			*@name 渲染模板 
			*@Description  
			*@CreateDate 2015年10月25日下午10:50:06
	 */
	public static void process(String tmplName,Object dataModel,Writer output){
		Template template = null;
		try {
			template = config.getTemplate(tmplName);
			template.process(dataModel, output);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
			*@name 渲染api文档
			*@Description  
			*@CreateDate 2016年1月14日下午8:44:22
	 */
	public static void processApiDoc(Object dataModel,Writer output){
		try {
			apiDocTemplate.process(dataModel, output);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
