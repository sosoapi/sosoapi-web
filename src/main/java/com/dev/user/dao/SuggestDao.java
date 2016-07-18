package com.dev.user.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.dev.base.enums.SuggestStatus;
import com.dev.base.enums.SuggestType;
import com.dev.base.mybatis.dao.BaseMybatisDao;
import com.dev.base.util.Pager;
import com.dev.user.entity.Suggest;

/**
 * 
		* <p>Title: 用户建议意见</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月29日下午10:20:32</p>
 */
public interface SuggestDao extends BaseMybatisDao<Suggest,Long> {
	/**
	 * 
			*@name 查询所有用户反馈
			*@Description  
			*@CreateDate 2015年9月12日上午11:33:40
	 */
	List<Map> listAll(@Param("email")String email,@Param("nickName")String nickName,
						@Param("status")SuggestStatus status,@Param("type")SuggestType type,
						@Param("pager")Pager pager);
	
	/**
	 * 
			*@name 查询用户反馈总数
			*@Description  
			*@CreateDate 2015年9月12日上午11:41:56
	 */
	int countAll(@Param("email")String email,@Param("nickName")String nickName,
					@Param("status")SuggestStatus status,@Param("type")SuggestType type);
	
	/**
	 * 
			*@name 查询详情
			*@Description  
			*@CreateDate 2015年11月24日下午5:10:52
	 */
	Map getDetailById(@Param("id")Long id);
}
