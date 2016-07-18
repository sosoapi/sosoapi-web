package com.dev.admin.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.admin.dao.UserCubeDao;
import com.dev.admin.entity.UserCube;
import com.dev.admin.service.UserCubeService;
import com.dev.base.mybatis.service.impl.BaseMybatisServiceImpl;
import com.dev.base.util.Pager;
import com.dev.base.utils.DateUtil;
import com.dev.proj.service.ProjectService;
import com.dev.user.service.UserBasicService;
import com.dev.user.service.UserLoginService;

/**
 * 
		* <p>Title: 用户统计</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年10月10日下午5:22:45</p>
 */
@Service
public class UserCubeServiceImpl extends BaseMybatisServiceImpl<UserCube,Long,UserCubeDao>
										implements UserCubeService{
	@Autowired
	private UserBasicService userBasicService;
	
	@Autowired
	private UserLoginService userLoginService;
	
	@Autowired
	private ProjectService projectService;
	
	
	@Override
	public UserCube cubeCurrent() {
		Date date = DateUtil.getNow();
		
		UserCube userCube = new UserCube();
		userCube.setDayLoginCount(userLoginService.countDayLogin(date));
		userCube.setDayOldLoginCount(userLoginService.countDayOldLogin(date));
		userCube.setDayProjCount(projectService.countDay(date));
		userCube.setTotalProjCount(projectService.countTotal());
		userCube.setDayRegistCount(userBasicService.countDayRegist(date));
		userCube.setTotalRegistCount(userBasicService.countTotalRegist());
		//统计即时在线用户数，数据库不保存
		userCube.setOnlineCount(userLoginService.countOnline());
		
		return userCube;
	}

	@Override
	public List<UserCube> list(Pager pager) {
		return getMybatisDao().list(pager);
	}

	@Override
	public int count() {
		return getMybatisDao().count();
	}

	@Override
	public void cubeDayJob() {
		UserCube current = cubeCurrent();
		add(current);
	}
}
