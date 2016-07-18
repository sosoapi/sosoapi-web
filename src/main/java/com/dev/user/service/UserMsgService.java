package com.dev.user.service;

import java.util.List;

import com.dev.base.enums.MsgType;
import com.dev.base.enums.UserRole;
import com.dev.base.mybatis.service.BaseMybatisService;
import com.dev.base.util.Pager;
import com.dev.user.entity.UserMsg;

/**
 * 
		* <p>Title: 用户消息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年9月30日下午3:32:16</p>
 */
public interface UserMsgService extends BaseMybatisService<UserMsg, Long>{
	/**
	 * 
			*@name 查询用户消息列表
			*@Description  
			*@CreateDate 2015年9月12日上午11:33:40
	 */
	List<UserMsg> listByUserId(Long userId,String title,MsgType msgType,
			Boolean sys,Boolean deal,Pager pager);
	
	/**
	 * 
			*@name 查询用户消息总数
			*@Description  
			*@CreateDate 2015年9月12日上午11:41:56
	 */
	int countByUserId(Long userId,String title,MsgType msgType,Boolean sys,Boolean deal);
	
	/**
	 * 
			*@name 获取未读消息数目
			*@Description  
			*@CreateDate 2015年10月19日下午5:48:35
	 */
	int countUnread(Long userId);
	
	/**
	 * 
			*@name 设置消息已读
			*@Description  
			*@CreateDate 2015年10月19日下午5:49:36
	 */
	void setReadByUserId(Long userId,Long msgId);
	
	/**
	 * 
			*@name 删除消息
			*@Description  
			*@CreateDate 2015年10月19日下午5:50:04
	 */
	void delByUserId(Long userId,Long msgId);
	
	/**
	 * 
			*@name 获取最新的系统消息插入到用户消息中
			*@Description  
			*@CreateDate 2015年10月19日下午4:33:16
	 */
	void fetchSysMsg(Long userId,UserRole userRole);
}
