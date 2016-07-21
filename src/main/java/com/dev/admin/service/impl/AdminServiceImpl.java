package com.dev.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.admin.service.AdminService;
import com.dev.base.enums.UserRole;
import com.dev.user.entity.UserBasic;
import com.dev.user.entity.UserDetail;
import com.dev.user.entity.UserLogin;
import com.dev.user.service.UserBasicService;
import com.dev.user.service.UserDetailService;
import com.dev.user.service.UserLoginService;
import com.dev.user.vo.UserInfo;

/**
 * 
		* <p>Title: 管理员相关服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年9月6日下午5:07:28</p>
 */
@Service
public class AdminServiceImpl implements AdminService{
	@Autowired
	private UserBasicService userBasicService;
	
	@Autowired
	private UserDetailService userDetailService;
	
	@Autowired
	private UserLoginService userLoginService;
	
	@Transactional
	@Override
	public void regist(String nickName, String email,String passwd) {
		//处理用户基本信息
		UserBasic userBasic = new UserBasic();
		userBasic.setEmail(email);
		userBasic.setLocked(false);
		userBasic.setValid(true);
		userBasic.setRole(UserRole.admin);
		userBasicService.add(userBasic);
		
		Long userId = userBasic.getId();
		//设置密码
		String passwordEncry = userBasicService.encryPasswd(userId,passwd);
		userBasic.setPassword(passwordEncry);
		userBasicService.update(userBasic);
		
		//处理用户详情信息
		UserDetail userDetail = new UserDetail();
		userDetail.setNickName(nickName);
		userDetail.setUserId(userId);
		userDetailService.add(userDetail);
		
		//处理用户登陆信息
		UserLogin userLogin = new UserLogin();
		userLogin.setUserId(userId);
		userLoginService.add(userLogin);
	}

	@Override
	public void updateUser(UserInfo userInfo) {
		UserBasic basic = new UserBasic();
		basic.setId(userInfo.getUserId());
		basic.setValid(userInfo.isValid());
		basic.setLocked(userInfo.isLocked());
		
		userBasicService.update(basic);
	}
}
