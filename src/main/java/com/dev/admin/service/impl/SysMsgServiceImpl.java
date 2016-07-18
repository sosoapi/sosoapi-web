package com.dev.admin.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dev.admin.dao.SysMsgDao;
import com.dev.admin.entity.SysMsg;
import com.dev.admin.service.SysMsgService;
import com.dev.base.enums.MsgType;
import com.dev.base.enums.UserRole;
import com.dev.base.mybatis.service.impl.BaseMybatisServiceImpl;
import com.dev.base.util.Pager;

/**
 * 
		* <p>Title: 系统消息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年9月30日下午3:29:59</p>
 */
@Service
public class SysMsgServiceImpl extends BaseMybatisServiceImpl<SysMsg,Long,SysMsgDao>
										implements SysMsgService{

	@Override
	public List<SysMsg> listAll(String title, MsgType msgType,
			UserRole userRole, Pager pager) {
		title = getLikeExpr(title);
		
		return getMybatisDao().listAll(title, msgType, userRole, pager);
	}

	@Override
	public int countAll(String title, MsgType msgType,UserRole userRole) {
		return getMybatisDao().countAll(title, msgType, userRole);
	}
}
