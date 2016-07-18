package com.dev.user.service;

import java.util.List;

import com.dev.base.enums.SuggestStatus;
import com.dev.base.enums.SuggestType;
import com.dev.base.mybatis.service.BaseMybatisService;
import com.dev.base.util.Pager;
import com.dev.user.entity.Suggest;
import com.dev.user.vo.SuggestInfo;

/**
 * 
		* <p>Title: 用户建议意见服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月29日下午10:21:00</p>
 */
public interface SuggestService extends BaseMybatisService<Suggest, Long>{
	/**
	 * 
			*@name 新增用户建议意见
			*@Description  
			*@CreateDate 2015年8月29日下午10:24:54
	 */
	void add(Long userId,String title,SuggestType type,String content);
	
	/**
	 * 
			*@name 查询所有用户反馈
			*@Description  
			*@CreateDate 2015年9月12日上午11:33:40
	 */
	List<SuggestInfo> listAll(String email,String nickName,
								SuggestStatus status,SuggestType type,Pager pager);
	
	/**
	 * 
			*@name 查询用户反馈总数
			*@Description  
			*@CreateDate 2015年9月12日上午11:41:56
	 */
	int countAll(String email,String nickName,
					SuggestStatus status,SuggestType type);
	
	/**
	 * 
			*@name 查询详情
			*@Description  
			*@CreateDate 2015年11月24日下午5:10:52
	 */
	SuggestInfo getDetailById(Long id);
	
	/**
	 * 
			*@name 处理反馈
			*@Description  
			*@CreateDate 2015年11月24日下午5:39:57
	 */
	void dealSuggest(Long id);
}
