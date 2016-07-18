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

import com.dev.admin.entity.SysMsg;
import com.dev.admin.service.SysMsgService;
import com.dev.base.controller.BaseController;
import com.dev.base.enums.MsgType;
import com.dev.base.enums.UserRole;
import com.dev.base.exception.code.ErrorCode;
import com.dev.base.json.JsonUtils;
import com.dev.base.util.Pager;
import com.dev.base.util.WebPaginate;
import com.dev.base.util.WebUtil;
import com.dev.base.utils.DateUtil;
import com.dev.base.utils.ValidateUtils;

/**
 * 
		* <p>Title: 系统消息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年9月14日下午5:39:07</p>
 */
@Controller
@RequestMapping("/admin/msg")
public class SysMsgController extends BaseController{
	@Autowired
	private SysMsgService sysMsgService;
	
	/**
	 * 
			*@name 已发送消息列表
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/list.htm")
	public String listMsg(HttpServletRequest request,Model model,String title,String msgType,
						String userRole,Integer pageNumber,Integer pageSize){
		MsgType msgTypeInfo = null;
		if (!StringUtils.isEmpty(msgType)) {
			msgTypeInfo = MsgType.valueOf(msgType);
		}
		
		UserRole userRoleInfo = null;
		if (!StringUtils.isEmpty(userRole)) {
			userRoleInfo = UserRole.valueOf(userRole);
		}
		
		Pager pager = new Pager(pageNumber, pageSize);
		List<SysMsg> list = sysMsgService.listAll(title, msgTypeInfo, userRoleInfo, pager);
		int count = sysMsgService.countAll(title, msgTypeInfo, userRoleInfo);
		pager.setTotalCount(count);
		pager.setList(list);
		
		model.addAttribute("pager", pager);
		model.addAttribute("paginate",WebPaginate.build(request, pageNumber, pageSize, count));
		
		return "admin/msgList";
	}
	
	/**
	 * 
			*@name 新增系统信息
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/forwardAdd.htm",method = RequestMethod.GET)
	public String forwardAdd(HttpServletRequest request,Model model){
		model.addAttribute("action", "admin/msg/add.htm");
		
		return "admin/msgInfo";
	}
	
	/**
	 * 
			*@name 调整到更新系统信息页面
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/forwardUpdate.htm",method = RequestMethod.GET)
	public String forwardUpdate(HttpServletRequest request,Model model,Long msgId){
		ValidateUtils.notNull(msgId, ErrorCode.SYS_001,"消息id不能为空");
		
		SysMsg sysMsg = sysMsgService.getById(msgId);
		model.addAttribute("msgInfo", sysMsg);
		model.addAttribute("action", "admin/msg/update.htm");
		
		return "admin/msgInfo";
	}
	
	/**
	 * 
			*@name 更新系统信息
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/update.htm",method = RequestMethod.POST)
	public String update(HttpServletRequest request,SysMsg sysMsg,Long msgId){
		ValidateUtils.notNull(msgId, ErrorCode.SYS_001,"消息id不能为空");
		ValidateUtils.notNull(sysMsg.getTitle(), ErrorCode.SYS_001,"消息标题不能为空");
		
		SysMsg msgInfo = sysMsgService.getById(msgId);
		msgInfo.setModifyDate(DateUtil.getNow());
		msgInfo.setTitle(sysMsg.getTitle());
		msgInfo.setContent(sysMsg.getContent());
		msgInfo.setMsgType(sysMsg.getMsgType());
		msgInfo.setMsgStatus(sysMsg.getMsgStatus());
		msgInfo.setUserRole(sysMsg.getUserRole());
		msgInfo.setPubDate(DateUtil.getNow());
		sysMsgService.update(msgInfo);
		
		return WebUtil.getRedirectUrl("/admin/msg/list.htm");
	}
	
	/**
	 * 
			*@name 新增系统信息
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/add.htm",method = RequestMethod.POST)
	public String add(HttpServletRequest request,SysMsg sysMsg){
		ValidateUtils.notNull(sysMsg.getTitle(), ErrorCode.SYS_001,"消息标题不能为空");
		
		Long userId = getUserId(request);
		sysMsg.setUserId(userId);
		sysMsg.setPubDate(DateUtil.getNow());
		sysMsgService.add(sysMsg);
		
		return WebUtil.getRedirectUrl("/admin/msg/list.htm");
	}
	
	/**
	 * 
			*@name 删除系统消息
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/json/del.htm")
	public @ResponseBody Map delMsg(Long msgId){
		ValidateUtils.notNull(msgId, ErrorCode.SYS_001,"系统消息id不能为空");
		sysMsgService.deleteById(msgId);
		
		return JsonUtils.createSuccess();
	}
}
