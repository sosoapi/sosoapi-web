package com.dev.user.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.base.constant.AppConstants;
import com.dev.base.constant.CfgConstants;
import com.dev.base.constant.MailConstants;
import com.dev.base.exception.code.ErrorCode;
import com.dev.base.json.JsonUtils;
import com.dev.base.mybatis.service.impl.BaseMybatisServiceImpl;
import com.dev.base.util.MailUtil;
import com.dev.base.utils.CryptUtil;
import com.dev.base.utils.DateUtil;
import com.dev.base.utils.MapUtils;
import com.dev.base.utils.RegexUtil;
import com.dev.base.utils.Sha1Utils;
import com.dev.base.utils.ValidateUtils;
import com.dev.user.dao.UserBasicDao;
import com.dev.user.entity.UserBasic;
import com.dev.user.entity.UserDetail;
import com.dev.user.service.UserBasicService;
import com.dev.user.service.UserDetailService;

/**
 * 
		* <p>Title: 用户基本信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:40:48</p>
 */
@Service
public class UserBasicServiceImpl extends BaseMybatisServiceImpl<UserBasic,Long,UserBasicDao>
										implements UserBasicService{
	@Autowired
	private UserDetailService userDetailService;
	
	@Override
	public UserBasic getByEmail(String email) {
		return getMybatisDao().getByEmail(email);
	}

	@Override
	public String encryPasswd(Long userId, String password) {
		return Sha1Utils.sha1Crypt(password + userId);
	}

	@Override
	public int countTotalRegist() {
		return getMybatisDao().countTotalRegist();
	}

	@Override
	public void updatePasswd(Long userId, String oldPasswd, String newPasswd) {
		UserBasic basic = getById(userId);
		ValidateUtils.isTrue(encryPasswd(userId, oldPasswd).equals(basic.getPassword()), ErrorCode.LOGIN_008);		
		
		basic.setPassword(encryPasswd(userId, newPasswd));
		update(basic);
	}

	@Override
	public int countDayRegist(Date date) {
		return getMybatisDao().countDayRegist(DateUtil.getDayStart(date), DateUtil.getDayEnd(date));
	}

	@Override
	public void unlockJob() {
		getMybatisDao().unlockJob();
	}

	@Override
	public void sendUpdateEmailCode(Long userId,String passwd, String email) {
		UserBasic basic = getById(userId);
		ValidateUtils.isTrue(basic.getPassword().equals(encryPasswd(userId, passwd)), ErrorCode.LOGIN_008,"当前密码错误");
		
		Map<String, Object> info = MapUtils.newMap();
		info.put("userId", userId);
		info.put("email", email);
		String code = CryptUtil.encryptAES(JsonUtils.toJson(info),AppConstants.DEFAULT_SECRET_KEY);
		
		UserDetail detail = userDetailService.getByUserId(userId);
		
		Map<String,Object> model = MapUtils.newMap();
		model.put("nickName", detail.getNickName());
		model.put("oldEmail", basic.getEmail());
		model.put("newEmail", email);
		model.put("updateEmailUrl", CfgConstants.WEB_BASE_URL + "pass/user/updateEmail.htm?code=" + code);
		
		MailUtil.send(email, MailConstants.SUB_EMAIL_UPDATE, MailConstants.TMPL_EMAIL_UPDATE, model);
	}

	@Override
	public void updateEmail(String code) {
		Map<String, Object> updateInfo = JsonUtils.toObject(CryptUtil.decryptAES(code,AppConstants.DEFAULT_SECRET_KEY), HashMap.class);
		Long userId = ((Number)updateInfo.get("userId")).longValue();
		String email = (String)updateInfo.get("email");
		ValidateUtils.isTrue(RegexUtil.isEmail(email), ErrorCode.SYS_001);
		ValidateUtils.isTrue(!isEmailExist(email, null), ErrorCode.LOGIN_002);
		
		UserBasic basic = getById(userId);
		if (basic != null) {
			basic.setEmail(email);
			update(basic);
		}
	}

	@Override
	public boolean isEmailExist(String email, Long exceptId) {
		return getMybatisDao().countByEmail(email, exceptId) > 0;
	}
}
