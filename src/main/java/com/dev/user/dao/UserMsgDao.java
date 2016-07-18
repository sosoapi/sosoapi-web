package com.dev.user.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dev.base.enums.MsgType;
import com.dev.base.enums.UserRole;
import com.dev.base.mybatis.dao.BaseMybatisDao;
import com.dev.base.util.Pager;
import com.dev.user.entity.UserMsg;

/**
 * 
		* <p>Title: 用户消息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年9月30日下午3:30:20</p>
 */
public interface UserMsgDao extends BaseMybatisDao<UserMsg,Long> {
	/**
	 * 
			*@name 查询用户消息列表
			*@Description  
			*@CreateDate 2015年9月12日上午11:33:40
	 */
	List<UserMsg> listByUserId(@Param("userId")Long userId,@Param("title")String title,@Param("msgType")MsgType msgType,
			@Param("sys")Boolean sys,@Param("deal")Boolean deal,@Param("pager")Pager pager);
	
	/**
	 * 
			*@name 查询用户消息总数
			*@Description  
			*@CreateDate 2015年9月12日上午11:41:56
	 */
	int countByUserId(@Param("userId")Long userId,@Param("title")String title,@Param("msgType")MsgType msgType,
			@Param("sys")Boolean sys,@Param("deal")Boolean deal);
	
	/**
	 * 
			*@name 获取最新的系统消息插入到用户消息中
			*@Description  
			*@CreateDate 2015年10月19日下午4:33:16
	 */
	void fetchSysMsg(@Param("userId")Long userId,@Param("userRole")UserRole userRole,
						@Param("lastFetchDate")Date lastFetchDate);
	
	/**
	 * 
			*@name 获取未读消息数目
			*@Description  
			*@CreateDate 2015年10月19日下午5:48:35
	 */
	int countUnread(@Param("userId")Long userId);
	
	/**
	 * 
			*@name 设置消息已读
			*@Description  
			*@CreateDate 2015年10月19日下午5:49:36
	 */
	void setReadByUserId(@Param("userId")Long userId,@Param("msgId")Long msgId);
	
	/**
	 * 
			*@name 删除消息
			*@Description  
			*@CreateDate 2015年10月19日下午5:50:04
	 */
	void delByUserId(@Param("userId")Long userId,@Param("msgId")Long msgId);
}
