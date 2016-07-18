package com.dev.user.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.dev.base.enums.UserRole;
import com.dev.base.mybatis.service.impl.BaseMybatisServiceImpl;
import com.dev.base.util.Pager;
import com.dev.user.dao.UserDetailDao;
import com.dev.user.entity.UserDetail;
import com.dev.user.service.UserDetailService;
import com.dev.user.vo.UserInfo;

/**
 * 
		* <p>Title: 用户详细信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:40:48</p>
 */
@Service
public class UserDetailServiceImpl extends BaseMybatisServiceImpl<UserDetail,Long,UserDetailDao>
										implements UserDetailService{

	@Override
	public UserDetail getByUserId(Long userId) {
		return getMybatisDao().getByUserId(userId);
	}

	@Override
	public UserInfo getDetailByUserId(Long userId) {
		Map<String, Object> info = getMybatisDao().getDetailByUserId(userId);
		return parseDetailInfo(info);
	}
	
	//组装用户详情
	private UserInfo parseDetailInfo(Map<String, Object> info){
		UserInfo userInfo = new UserInfo();
		userInfo.setUserId((Long)info.get("userId"));
		userInfo.setEmail((String)info.get("email"));
		userInfo.setNickName((String)info.get("nickName"));
		userInfo.setHeadUrl((String)info.get("headUrl"));
		userInfo.setRole(UserRole.valueOf((String)info.get("role")));
		userInfo.setValid((Boolean)info.get("valid"));
		userInfo.setLocked((Boolean)info.get("locked"));
		userInfo.setRegistDate((Date)info.get("registDate"));
		
		return userInfo;
	}

	@Override
	public List<UserInfo> listAll(String email, String nickName, Boolean valid,Pager pager) {
		email = getLikeExpr(email);
		nickName = getLikeExpr(nickName);
		
		List<UserInfo> result = new ArrayList<UserInfo>();
		List<Map> infoList = getMybatisDao().listAll(email, nickName, valid, pager);
		for (Map info : infoList) {
			result.add(parseDetailInfo(info));
		}
		
		return result;
	}

	@Override
	public int countAll(String email, String nickName, Boolean valid) {
		email = getLikeExpr(email);
		nickName = getLikeExpr(nickName);
		
		return getMybatisDao().countAll(email, nickName, valid);
	}
}
