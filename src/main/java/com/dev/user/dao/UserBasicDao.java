package com.dev.user.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.dev.base.mybatis.dao.BaseMybatisDao;
import com.dev.user.entity.UserBasic;

/**
 * 
		* <p>Title: 用户基本信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:40:48</p>
 */
public interface UserBasicDao extends BaseMybatisDao<UserBasic,Long> {
	/**
	 * 
			*@name 根据邮箱查询用户信息
			*@Description  
			*@CreateDate 2015年8月8日上午10:20:23
	 */
	UserBasic getByEmail(String email);
	
	/**
	 * 
			*@name 查询指定的邮箱数目
			*@Description  
			*@CreateDate 2016年5月21日下午10:09:01
	 */
	int countByEmail(@Param("email")String email,@Param("exceptId")Long exceptId);
	
	/**
	 * 
			*@name 查询总用户数
			*@Description  
			*@CreateDate 2015年9月11日下午6:13:32
	 */
	int countTotalRegist();
	
	/**
	 * 
			*@name 查询指定日期当天注册用户数
			*@Description  
			*@CreateDate 2015年10月10日下午5:41:51
	 */
	int countDayRegist(@Param("dayStart")Date dayStart,@Param("dayEnd")Date dayEnd);
	
	/**
	 * 
			*@name 定时任务，解锁
			*@Description  
			*@CreateDate 2015年10月11日上午11:43:47
	 */
	void unlockJob();
}
