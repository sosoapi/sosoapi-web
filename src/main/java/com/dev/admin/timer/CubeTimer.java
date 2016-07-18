package com.dev.admin.timer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.admin.service.UserCubeService;

/**
 * 
		* <p>Title: 统计定时器</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年10月10日下午7:04:02</p>
 */
@Service
public class CubeTimer {
	@Autowired
	private UserCubeService userCubeService;
	
	/**
	 * 
			*@name 统计用户信息
			*@Description  
			*@CreateDate 2015年10月11日上午11:39:27
	 */
	public void cubeUser(){
		userCubeService.cubeDayJob();
	}
}
