package com.dev.proj.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev.base.controller.BaseController;
import com.dev.base.enums.Role;
import com.dev.base.exception.code.ErrorCode;
import com.dev.base.json.JsonUtils;
import com.dev.base.util.Pager;
import com.dev.base.util.WebPaginate;
import com.dev.base.util.WebUtil;
import com.dev.base.utils.RegexUtil;
import com.dev.base.utils.ValidateUtils;
import com.dev.proj.service.ProjectLogService;
import com.dev.proj.service.ProjectMemberService;
import com.dev.proj.vo.ProjectMemberInfo;
import com.dev.user.vo.UserInfo;

/**
 * 
		* <p>Title: 项目成员相关</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年7月11日下午5:39:29</p>
 */
@Controller
@RequestMapping("/auth/proj/mem")
public class ProjMemController extends BaseController{
	@Autowired
	private ProjectMemberService projectMemberService;
	
	@Autowired
	private ProjectLogService projectLogService;
	
	/**
	 * 
			*@name 项目成员列表
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24

	 */
	@RequestMapping("/list.htm")
	public String list(HttpServletRequest request,Long projId,String role,
						String nickName,String email,
						Integer pageNumber,Integer pageSize,Model model){
		ValidateUtils.notNull(projId, ErrorCode.SYS_001,"项目id不能为空");
		
		Role roleInfo = null;
		if (!StringUtils.isEmpty(role)) {
			roleInfo = Role.valueOf(role);
		}
		
		Pager pager = new Pager(pageNumber, pageSize);
		List<ProjectMemberInfo> list = projectMemberService.listByProjId(projId, roleInfo,nickName,email, pager);
		int count = projectMemberService.countByProjId(projId, roleInfo,nickName,email);
		pager.setTotalCount(count);
		pager.setList(list);
		
		model.addAttribute("pager", pager);
		model.addAttribute("paginate",WebPaginate.build(request, pageNumber, pageSize, count));
		
		return "project/memList";
	}
	
	/**
	 * 
			*@name 邀请加入项目
			*@Description  
			*@CreateDate 2015年8月22日下午2:07:39
	 */
	@RequestMapping("/json/invite.htm")
	public @ResponseBody Map invite(HttpServletRequest request,Long projId,String invitedEmail){
		ValidateUtils.notNull(projId, ErrorCode.SYS_001,"项目id不能为空");
		ValidateUtils.notNull(RegexUtil.isEmail(invitedEmail), ErrorCode.SYS_001,"邮箱格式错误");
		
		Long currentId = getUserId(request);
		projectMemberService.invite(currentId, projId, invitedEmail);
		
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 加入项目
			*@Description  
			*@CreateDate 2015年8月22日下午2:07:39
	 */
	@RequestMapping("/accept.htm")
	public String accept(HttpServletRequest request,String code,Model model){
		ValidateUtils.notNull(code, ErrorCode.SYS_001,"邀请码不能为空");
		
		Long userId = getUserId(request);
		Long projId = projectMemberService.accept(userId, code);
		
		//重新加载项目权限
		reloadProjAuth(request);
				
		model.addAttribute("projId", projId);
		
		return WebUtil.getRedirectUrl("/auth/proj/info.htm");
	}
	
	/**
	 * 
			*@name 新增成员
			*@Description  
			*@CreateDate 2015年11月28日下午5:30:24
	 */
	@RequestMapping("/json/add.htm")
	public @ResponseBody Map add(HttpServletRequest request,Long projId,Long userId,String role){
		ValidateUtils.notNull(projId, ErrorCode.SYS_001,"项目id不能为空");
		ValidateUtils.notNull(userId, ErrorCode.SYS_001,"用户id不能为空");
		
		Long currentId = getUserId(request);
		Role projRole = StringUtils.isEmpty(role) ? Role.guest : Role.valueOf(role); 
		projectMemberService.add(currentId, userId, projId, projRole);
		
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 移除项目成员
			*@Description  
			*@CreateDate 2015年8月22日下午2:07:39
	 */
	@RequestMapping("/json/del.htm")
	public @ResponseBody Map delete(HttpServletRequest request,Long projId,Long userId){
		ValidateUtils.notNull(projId, ErrorCode.SYS_001,"项目id不能为空");
		ValidateUtils.notNull(userId, ErrorCode.SYS_001,"用户id不能为空");
		
		Long currentId = getUserId(request);
		projectMemberService.remove(currentId,userId, projId);
		
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 编辑项目成员
			*@Description  
			*@CreateDate 2015年8月22日下午2:07:39
	 */
	@RequestMapping("/json/update.htm")
	public @ResponseBody Map invite(HttpServletRequest request,Long projId,Long userId,Role role){
		ValidateUtils.notNull(projId, ErrorCode.SYS_001,"项目id不能为空");
		ValidateUtils.notNull(userId, ErrorCode.SYS_001,"用户id不能为空");
		
		Long currentId = getUserId(request);
		projectMemberService.updateRole(currentId,projId, userId, role);
		
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 成员基本信息
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24

	 */
	@RequestMapping("/json/info.htm")
	public @ResponseBody Map getInfo(Long projId,Long userId){
		ValidateUtils.notNull(projId, ErrorCode.SYS_001,"项目id不能为空");
		ValidateUtils.notNull(userId, ErrorCode.SYS_001,"用户id不能为空");
		
		ProjectMemberInfo info = projectMemberService.getByUserIdAndProjId(userId, projId);
		
		return JsonUtils.createSuccess(info);
	}
	
	/**
	 * 
			*@name 进入发送变更通知
			*@Description  
			*@CreateDate 2015年8月22日下午2:07:39
	 */
	@RequestMapping("/forwardSendNotice.htm")
	public String forwardSendNotice(HttpServletRequest request){
		return "project/notice";
	}
	
	/**
	 * 
			*@name 发送变更说明
			*@Description  
			*@CreateDate 2015年8月22日下午2:07:39
	 */
	@RequestMapping("/json/sendNotice.htm")
	public @ResponseBody Map sendNotice(HttpServletRequest request,Long projId,String title,
											String content,String receiverRole,String otherReceivers,
											boolean addLog){
		ValidateUtils.notNull(projId, ErrorCode.SYS_001,"项目id不能为空");
		ValidateUtils.notNull(title, ErrorCode.SYS_001,"邮件标题不能为空");
		
		UserInfo userInfo = getUserInfo(request);
		if (addLog) {//添加到项目日志中
			projectLogService.addLog(userInfo.getUserId(), projId, title, content);
		}
		
		projectMemberService.sendNotice(userInfo, projId, receiverRole,otherReceivers, title, content);
		
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 获取可添加到当前项目中的历史成员列表
			*@Description  
			*@CreateDate 2015年11月28日下午5:10:10
	 */
	@RequestMapping("/listForAdd.htm")
	public String listForAdd(HttpServletRequest request,Long projId,
								Integer pageNumber,Integer pageSize,Model model){
		ValidateUtils.notNull(projId, ErrorCode.SYS_001,"项目id不能为空");
		
		Long userId = getUserId(request);
		Pager pager = new Pager(pageNumber, pageSize);
		List<ProjectMemberInfo> list = projectMemberService.listForAdd(userId, projId, pager);
		int count = projectMemberService.countForAdd(userId, projId);
		pager.setTotalCount(count);
		pager.setList(list);
		
		model.addAttribute("pager", pager);
		model.addAttribute("paginate",WebPaginate.build(request, pageNumber, pageSize, count));
		
		return "project/invite";
	}
}
