package com.dev.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.base.enums.MsgType;
import com.dev.base.enums.UserRole;
import com.dev.base.mybatis.service.impl.BaseMybatisServiceImpl;
import com.dev.base.util.Pager;
import com.dev.base.utils.DateUtil;
import com.dev.user.dao.UserMsgDao;
import com.dev.user.entity.UserExt;
import com.dev.user.entity.UserMsg;
import com.dev.user.service.UserExtService;
import com.dev.user.service.UserMsgService;

/**
 * 
		* <p>Title: 用户消息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年9月30日下午3:32:32</p>
 */
@Service
public class UserMsgServiceImpl extends BaseMybatisServiceImpl<UserMsg,Long,UserMsgDao>
										implements UserMsgService{
	@Autowired
	private UserExtService userExtService;
	
	@Override
	public List<UserMsg> listByUserId(Long userId,String title, MsgType msgType, Boolean sys,
			Boolean deal, Pager pager) {
		title = getLikeExpr(title);
		
		return getMybatisDao().listByUserId(userId,title, msgType, sys, deal, pager);
	}

	@Override
	public int countByUserId(Long userId,String title, MsgType msgType, Boolean sys, Boolean deal) {
		return getMybatisDao().countByUserId(userId,title, msgType, sys, deal);
	}

	@Override
	public void fetchSysMsg(Long userId, UserRole userRole) {
		UserExt ext = userExtService.getByUserId(userId);
		getMybatisDao().fetchSysMsg(userId, userRole, ext.getLastFetchSysMsgDate());
		
		ext.setLastFetchSysMsgDate(DateUtil.getNow());
		userExtService.update(ext);
	}

	@Override
	public int countUnread(Long userId) {
		return getMybatisDao().countUnread(userId);
	}

	@Override
	public void setReadByUserId(Long userId, Long msgId) {
		getMybatisDao().setReadByUserId(userId, msgId);
	}

	@Override
	public void delByUserId(Long userId, Long msgId) {
		getMybatisDao().delByUserId(userId, msgId);
	}
}
