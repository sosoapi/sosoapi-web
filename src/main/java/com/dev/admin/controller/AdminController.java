package com.dev.admin.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev.admin.service.AdminService;
import com.dev.base.controller.BaseController;
import com.dev.base.enums.ProjectStatus;
import com.dev.base.enums.SuggestStatus;
import com.dev.base.enums.SuggestType;
import com.dev.base.exception.code.ErrorCode;
import com.dev.base.json.JsonUtils;
import com.dev.base.util.MailUtil;
import com.dev.base.util.Pager;
import com.dev.base.util.WebPaginate;
import com.dev.base.utils.ValidateUtils;
import com.dev.proj.service.ProjectService;
import com.dev.proj.vo.ProjectInfo;
import com.dev.user.service.SuggestService;
import com.dev.user.service.UserDetailService;
import com.dev.user.vo.SuggestInfo;
import com.dev.user.vo.UserInfo;

/**
 * 
		* <p>Title: 管理员首页</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年9月6日下午4:19:22</p>
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController{
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private UserDetailService userDetailService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private SuggestService suggestService;
	
	/**
	 * 
			*@name 登陆后跳转到主页面
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24

	 */
	@RequestMapping("/home.htm")
	public String forwardHome(Model model){
		return "forward:/admin/cube/user.htm";
	}
	
	/**
	 * 
			*@name 项目列表
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/proj/list.htm")
	public String listProj(HttpServletRequest request,Model model,String code,String nickName,
						String status,Integer pageNumber,Integer pageSize){
		ProjectStatus projectStatus = null;
		if (!StringUtils.isEmpty(status)) {
			projectStatus = ProjectStatus.valueOf(status);
		}
		
		Pager pager = new Pager(pageNumber, pageSize);
		List<ProjectInfo> list = projectService.listAll(code, nickName, projectStatus, pager);
		int count = projectService.countAll(code, nickName, projectStatus);
		pager.setTotalCount(count);
		pager.setList(list);
		
		model.addAttribute("pager", pager);
		model.addAttribute("paginate",WebPaginate.build(request, pageNumber, pageSize, count));
		
		return "admin/projList";
	}
	
	/**
	 * 
			*@name 用户列表
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/user/list.htm")
	public String listUser(HttpServletRequest request,Model model,String email,String nickName,
						String valid,Integer pageNumber,Integer pageSize){
		Boolean isValid = StringUtils.isEmpty(valid) ? null : Boolean.valueOf(valid);
		
		Pager pager = new Pager(pageNumber, pageSize);
		List<UserInfo> list = userDetailService.listAll(email, nickName, isValid, pager);
		int count = userDetailService.countAll(email, nickName, isValid);
		pager.setTotalCount(count);
		pager.setList(list);
		
		model.addAttribute("pager", pager);
		model.addAttribute("paginate",WebPaginate.build(request, pageNumber, pageSize, count));
		
		return "admin/userList";
	}
	
	/**
	 * 
			*@name 编辑用户信息
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/user/json/update.htm",method = RequestMethod.POST)
	public @ResponseBody Map updateUser(HttpServletRequest request,UserInfo userInfo,Long userId){
		ValidateUtils.notNull(userId, ErrorCode.SYS_001,"用户id不能为空");
		
		userInfo.setUserId(userId);
		adminService.updateUser(userInfo);
		
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 查询用户基本信息
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/user/json/info.htm")
	public @ResponseBody Map getUserInfo(Long userId){
		ValidateUtils.notNull(userId, ErrorCode.SYS_001,"用户id不能为空");
		UserInfo userInfo = userDetailService.getDetailByUserId(userId);
		return JsonUtils.createSuccess(userInfo);
	}
	
	/**
	 * 
			*@name 用户反馈列表
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/suggest/list.htm")
	public String listSuggest(HttpServletRequest request,Model model,String email,String nickName,
						String status,String type,Integer pageNumber,Integer pageSize){
		SuggestStatus suggestStatus = null;
		if (!StringUtils.isEmpty(status)) {
			suggestStatus = SuggestStatus.valueOf(status);
		}
		
		SuggestType suggestType = null;
		if (!StringUtils.isEmpty(type)) {
			suggestType = SuggestType.valueOf(type);
		}
		
		Pager pager = new Pager(pageNumber, pageSize);
		List<SuggestInfo> list = suggestService.listAll(email, nickName, suggestStatus,suggestType, pager);
		int count = suggestService.countAll(email, nickName, suggestStatus,suggestType);
		pager.setTotalCount(count);
		pager.setList(list);
		
		model.addAttribute("pager", pager);
		model.addAttribute("paginate",WebPaginate.build(request, pageNumber, pageSize, count));
		
		return "admin/suggestList";
	}
	
	/**
	 * 
			*@name 处理用户意见建议
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/suggest/json/deal.htm")
	public @ResponseBody Map dealSuggest(Long suggestId){
		ValidateUtils.notNull(suggestId, ErrorCode.SYS_001,"意见建议id不能为空");
		suggestService.dealSuggest(suggestId);
		
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 删除用户意见建议
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/suggest/json/del.htm")
	public @ResponseBody Map delSuggest(Long suggestId){
		ValidateUtils.notNull(suggestId, ErrorCode.SYS_001,"意见建议id不能为空");
		suggestService.deleteById(suggestId);
		
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 进入意见建议回复页面
			*@Description  
			*@CreateDate 2015年8月22日下午2:07:39
	 */
	@RequestMapping("/suggest/forwardReply.htm")
	public String forwardSuggestReply(HttpServletRequest request,Model model,Long suggestId){
		ValidateUtils.notNull(suggestId, ErrorCode.SYS_001,"意见建议id不能为空");
		
		SuggestInfo info = suggestService.getDetailById(suggestId);
		model.addAttribute("suggestInfo", info);
		
		return "admin/suggestReply";
	}
	
	/**
	 * 
			*@name 发送回复邮件
			*@Description  
			*@CreateDate 2015年8月22日下午2:07:39
	 */
	@RequestMapping("/suggest/json/reply.htm")
	public @ResponseBody Map replySuggest(HttpServletRequest request,Long suggestId,String toEmail,String title,String content){
		ValidateUtils.notNull(suggestId, ErrorCode.SYS_001,"意见建议id不能为空");
		ValidateUtils.notNull(toEmail, ErrorCode.SYS_001,"收件人不能为空");
		ValidateUtils.notNull(title, ErrorCode.SYS_001,"邮件标题不能为空");
		
		MailUtil.send(toEmail, title, content);
		suggestService.dealSuggest(suggestId);
		
		return JsonUtils.createSuccess();
	}
}
