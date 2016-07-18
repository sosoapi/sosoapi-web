package com.dev.doc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dev.base.mybatis.dao.BaseMybatisDao;
import com.dev.base.util.Pager;
import com.dev.doc.entity.Module;

/**
 * 
		* <p>Title: 模块消息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:28:32</p>
 */
public interface ModuleDao extends BaseMybatisDao<Module,Long> {
	/**
	 * 
			*@name 根据api文档id查询所有模块列表
			*@Description  
			*@CreateDate 2015年8月6日下午3:41:18
	 */
	List<Module> listByDocId(@Param("docId")Long docId,@Param("code")String code,
									@Param("name")String name,@Param("description")String description,@Param("pager")Pager pager);
	
	/**
	 * 查询api文档相关的模块总数
	 * @param docId
	 * @return
	 */
	int countByDocId(@Param("docId")Long docId,@Param("code")String code,
							@Param("name")String name,@Param("description")String description);
	
	/**
	 * 
			*@name 根据id和文档id进行更新
			*@Description  
			*@CreateDate 2015年9月9日上午10:53:49
	 */
	void updateByDocId(Module module);
	
	/**
	 * 
			*@name 根据id和文档id进行删除
			*@Description  
			*@CreateDate 2015年9月9日上午11:13:24
	 */
	void deleteByDocId(@Param("docId")Long docId,@Param("moduleId")Long moduleId);
}
