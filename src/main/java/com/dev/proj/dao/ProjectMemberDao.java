package com.dev.proj.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.dev.base.enums.Role;
import com.dev.base.mybatis.dao.BaseMybatisDao;
import com.dev.base.util.Pager;
import com.dev.proj.entity.ProjectMember;

/**
 * 
		* <p>Title: 项目成员信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:28:53</p>
 */
public interface ProjectMemberDao extends BaseMybatisDao<ProjectMember,Long> {
	/**
	 * 
			*@name 根据用户id和项目id查询相关记录
			*@Description  
			*@CreateDate 2015年8月7日下午2:21:37
	 */
	int countRecord(@Param("userId")Long userId,@Param("projId")Long projId);
	
	/**
	 * 
			*@name 查询项目相关成员
			*@Description  
			*@CreateDate 2015年8月7日下午6:06:44
	 */
	List<Map> listByProjId(@Param("projId")Long projId,@Param("role")Role role,
							@Param("nickName")String nickName,@Param("email")String email,
							@Param("pager")Pager pager);
	
	/**
	 * 
			*@name 查询项目相关成员列表
			*@Description  
			*@CreateDate 2015年8月7日下午6:06:53
	 */
	int countByProjId(@Param("projId")Long projId,@Param("role")Role role,
						@Param("nickName")String nickName,@Param("email")String email);
	
	/**
	 * 
			*@name 删除项目成员
			*@Description  
			*@CreateDate 2015年8月22日下午2:28:11
	 */
	void delByUserIdAndProjId(@Param("userId")Long userId,@Param("projId")Long projId);
	
	/**
	 * 
			*@name 获取用户有权限的项目列表
			*@Description  
			*@CreateDate 2015年8月29日下午3:11:35
	 */
	List<Map> listAuthProjectInfo(Long userId);
	
	/**
	 * 
			*@name 编辑用户角色
			*@Description  
			*@CreateDate 2015年9月8日下午10:59:00
	 */
	void updateRole(@Param("projId")Long projId,@Param("userId")Long userId,@Param("role")Role role);
	
	/**
	 * 
			*@name 获取成员信息
			*@Description  
			*@CreateDate 2015年9月8日下午11:16:44
	 */
	Map getByUserIdAndProjId(@Param("userId")Long userId,@Param("projId")Long projId);
	
	/**
	 * 
			*@name 获取项目角色
			*@Description  
			*@CreateDate 2015年10月8日上午12:43:57
	 */
	String getRole(@Param("userId")Long userId,@Param("projId")Long projId);
	
	/**
	 * 
			*@name 查询具有编辑权限的用户id列表
			*@Description  
			*@CreateDate 2015年9月28日下午9:55:01
	 */
	List<Long> listAdmin(Long projId);
	
	/**
	 * 
			*@name 获取成员邮件列表
			*@Description  
			*@CreateDate 2015年10月21日下午10:03:51
	 */
	List<String> listEmail(@Param("projId")Long projId,@Param("role")Role role);
	
	/**
	 * 
			*@name 获取可添加到当前项目中的历史成员列表
			*@Description  
			*@CreateDate 2015年11月28日下午5:04:42
	 */
	List<Map> listForAdd(@Param("userId")Long userId,@Param("projId")Long projId,@Param("pager")Pager pager);
	
	/**
	 * 
			*@name 获取可添加到当前项目中的历史成员总数
			*@Description  
			*@CreateDate 2015年11月28日下午5:05:28
	 */
	int countForAdd(@Param("userId")Long userId,@Param("projId")Long projId);
}
