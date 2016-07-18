package com.dev.admin.service;

import java.util.List;

import com.dev.admin.entity.SysMsg;
import com.dev.base.enums.MsgType;
import com.dev.base.enums.UserRole;
import com.dev.base.mybatis.service.BaseMybatisService;
import com.dev.base.util.Pager;

/**
 * 
		* <p>Title: 系统消息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年9月30日下午3:29:41</p>
 */
public interface SysMsgService extends BaseMybatisService<SysMsg, Long>{
	/**
	 * 
			*@name 查询系统消息
			*@Description  
			*@CreateDate 2015年9月12日上午11:33:40
	 */
	List<SysMsg> listAll(String title,MsgType msgType,
							UserRole userRole,Pager pager);
	
	/**
	 * 
			*@name 查询系统消息总数
			*@Description  
			*@CreateDate 2015年9月12日上午11:41:56
	 */
	int countAll(String title,MsgType msgType,UserRole userRole);
}
