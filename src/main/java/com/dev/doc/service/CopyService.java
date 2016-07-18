package com.dev.doc.service;

import com.dev.proj.vo.ProjectInfo;


/**
 * 
		* <p>Title: 复制服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:37:33</p>
 */
public interface CopyService {
	/**
	 * 
			*@name 复制项目 
			*@Description  
			*@CreateDate 2016年5月14日上午9:26:12
	 */
	ProjectInfo copyProj(Long userId,Long projId);
	
	/**
	 * 
			*@name 复制接口
			*@Description  
			*@CreateDate 2016年5月15日上午10:31:51
	 */
	void copyInter(Long userId,Long docId,Long interId);
}
