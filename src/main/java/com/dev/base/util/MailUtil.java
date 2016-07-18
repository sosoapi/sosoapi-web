package com.dev.base.util;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.util.CollectionUtils;

import com.dev.base.concurrent.TaskUtils;
import com.dev.base.constant.CfgConstants;
import com.dev.base.mail.common.MailCfg;
import com.dev.base.mail.entity.MailMsg;
import com.dev.base.mail.enums.MailMsgType;
import com.dev.base.mail.service.MailService;
import com.dev.base.utils.DateUtil;
import com.dev.base.utils.MapUtils;
import com.dev.base.utils.PropertiesUtils;
import com.dev.base.utils.SpringContextUtils;
import com.dev.base.utils.TemplateUtils;

/**
 * 
		* <p>Title: 邮件发送工具类</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月22日下午3:12:40</p>
 */
public class MailUtil {
	private static MailService mailService = SpringContextUtils.getBean(MailService.class);
	
	//邮件配置信息
	private final static Properties mailCfg= PropertiesUtils.getProperties("mail-cfg.properties");
	
	/**
	 * 通知发送者邮箱
	 */
	public final static String NOTICE_FROM_EMAIL = mailCfg.getProperty("notice.from.email");
	
	/**
	 * 通知发送者密码
	 */
	public final static String NOTICE_FROM_PASSWD = mailCfg.getProperty("notice.from.passwd");
	
	/**
	 * 通知发送者名称
	 */
	public final static String NOTICE_FROM_NAME = mailCfg.getProperty("notice.from.name");
	
	
	/**
	 * 
			*@name 发送邮件，使用service账号
			*@Description  
			*@CreateDate 2015年8月22日下午3:14:03
	 */
	public static void send(final String toEmail,String subject,String content){
		final MailMsg mailMsg = new MailMsg();
		mailMsg.setSubject(subject);
		mailMsg.setType(MailMsgType.html);
		mailMsg.setContent(content);
		
		TaskUtils.execAsyn(new Runnable() {
			@Override
			public void run() {
				mailService.sendMail(toEmail, mailMsg);
			}
		});
	}
	
	/**
	 * 
			*@name 发送邮件，使用service账号
			*@Description  
			*@CreateDate 2015年8月22日下午3:14:03
	 */
	public static void send(final String toEmail,String subject,String tmpl,Map<String,Object> model){
		if (model == null) {
			model = MapUtils.newMap();
		}
		model.put("webName", CfgConstants.WEB_NAME);
		model.put("webSite", CfgConstants.WEB_SITE);
		model.put("webEmail", CfgConstants.WEB_SERVICE_EMAIL);
		model.put("webTel", CfgConstants.WEB_SERVICE_TEL);
		model.put("sendDate", DateUtil.formatNowByLong());
		
		String content = TemplateUtils.process(tmpl,model);
		final MailMsg mailMsg = new MailMsg();
		mailMsg.setSubject(subject);
		mailMsg.setType(MailMsgType.html);
		mailMsg.setContent(content);
		
		TaskUtils.execAsyn(new Runnable() {
			@Override
			public void run() {
				mailService.sendMail(toEmail, mailMsg);
			}
		});
	}
	
	/**
	 * 
			*@name 发送变更通知，使用notice账号
			*@Description  
			*@CreateDate 2015年10月24日下午4:33:09
	 */
	public static void sendNotice(final List<String> emailList,String subject,String content) {
		if (CollectionUtils.isEmpty(emailList)) {
			return ;
		}
		
		final MailMsg mailMsg = new MailMsg();
		mailMsg.setSubject(subject);
		mailMsg.setContent(content);
		mailMsg.setType(MailMsgType.multi);
		
		TaskUtils.execAsyn(new Runnable() {
			@Override
			public void run() {
				mailService.sendMail(NOTICE_FROM_EMAIL, NOTICE_FROM_PASSWD, NOTICE_FROM_NAME, 
										MailCfg.HOST, emailList, mailMsg);
			}
		});
	}
}
