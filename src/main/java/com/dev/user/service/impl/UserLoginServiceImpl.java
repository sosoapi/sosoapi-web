package com.dev.user.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.dev.base.mybatis.service.impl.BaseMybatisServiceImpl;
import com.dev.base.utils.DateUtil;
import com.dev.user.dao.UserLoginDao;
import com.dev.user.entity.UserLogin;
import com.dev.user.service.UserLoginService;

/**
 * 
		* <p>Title: 用户登陆信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:40:48</p>
 */
@Service
public class UserLoginServiceImpl extends BaseMybatisServiceImpl<UserLogin,Long,UserLoginDao>
										implements UserLoginService{
	@Override
	public int countDayLogin(Date date) {
		return getMybatisDao().countDayLogin(DateUtil.getDayStart(date), DateUtil.getDayEnd(date));
	}

	@Override
	public int countDayOldLogin(Date date) {
		Date dayStart = DateUtil.getDayStart(date);
		return getMybatisDao().countDayOldLogin(dayStart, DateUtil.getDayEnd(date), dayStart);
	}

	@Override
	public int countOnline() {
		return getMybatisDao().countOnline();
	}

	@Override
	public UserLogin getByUserId(Long userId) {
		return getMybatisDao().getByUserId(userId);
	}

	@Override
	public void resetLoginFailCount() {
		getMybatisDao().resetLoginFailCount();
	}
}
