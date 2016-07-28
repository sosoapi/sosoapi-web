package com.dev.base.constant;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * 
		* <p>Title: 邮件模板</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月23日下午10:11:28</p>
 */
public class MailConstants {
	/**
	 * 注册邮件标题
	 */
	public final static String SUB_REGIST = "欢迎注册" + CfgConstants.WEB_NAME;
	
	/**
	 * 注册邮件模板
	 */
	public final static String TMPL_REGIST = loadTmpl("mail-tmpl/regist.ftl");

	/**
	 * 密码重置邮件标题
	 */
	public final static String SUB_PASSWD_RESET = "密码重置";
	
	/**
	 * 密码重置邮件模板
	 */
	public final static String TMPL_PASSWD_RESET = loadTmpl("mail-tmpl/passwd-reset.ftl");
	
	/**
	 * 邀请项目成员邮件标题
	 */
	public final static String SUB_PROJ_MEM_INVITE = "邀请加入项目";
	
	/**
	 * 邀请项目成员邮件模板
	 */
	public final static String TMPL_PROJ_MEM_INVITE = loadTmpl("mail-tmpl/proj-mem-invite.ftl");
	
	/**
	 * 更改邮箱邮件标题
	 */
	public final static String SUB_EMAIL_UPDATE = "换绑邮箱";
	
	/**
	 * 更改邮箱邮件模板
	 */
	public final static String TMPL_EMAIL_UPDATE = loadTmpl("mail-tmpl/email-update.ftl");
	
	
	//加载模板内容
	private static String loadTmpl(String fileName){
		Resource fileRource = new ClassPathResource(fileName);
		try {
			return IOUtils.toString(fileRource.getInputStream(),"UTF-8");
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
