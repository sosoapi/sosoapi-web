package com.dev.user.controller;

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

import com.dev.base.controller.BaseController;
import com.dev.base.enums.MsgType;
import com.dev.base.exception.code.ErrorCode;
import com.dev.base.json.JsonUtils;
import com.dev.base.util.Pager;
import com.dev.base.util.WebPaginate;
import com.dev.base.utils.ValidateUtils;
import com.dev.user.entity.UserMsg;
import com.dev.user.service.UserMsgService;
import com.dev.user.vo.UserInfo;

/**
 * 
		* <p>Title: 用户消息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月22日下午3:53:04</p>
 */
@Controller
@RequestMapping("/auth/msg")
public class MsgController extends BaseController{
	@Autowired
	private UserMsgService userMsgService;
	
	/**
	 * 
			*@name 消息列表
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/list.htm")
	public String listMsg(HttpServletRequest request,Model model,String title,String msgType,
			String isSys,String isDeal,Integer pageNumber,Integer pageSize){
		MsgType msgTypeInfo = null;
		if (!StringUtils.isEmpty(msgType)) {
			msgTypeInfo = MsgType.valueOf(msgType);
		}
		
		Boolean sys = null;
		if (!StringUtils.isEmpty(isSys)) {
			sys = Boolean.valueOf(isSys);
		}
		
		Boolean deal = null;
		if (!StringUtils.isEmpty(isDeal)) {
			deal = Boolean.valueOf(isDeal);
		}
		
		Long userId = getUserId(request);
		
		Pager pager = new Pager(pageNumber, pageSize);
		List<UserMsg> list = userMsgService.listByUserId(userId,title, msgTypeInfo, sys, deal, pager);
		int count = userMsgService.countByUserId(userId,title, msgTypeInfo, sys, deal);
		pager.setTotalCount(count);
		pager.setList(list);
		
		model.addAttribute("pager", pager);
		model.addAttribute("paginate",WebPaginate.build(request, pageNumber, pageSize, count));
		
		return "user/msgList";
	}
	
	/**
	 * 
			*@name 发送消息
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/add.htm",method = RequestMethod.POST)
	public @ResponseBody Map add(HttpServletRequest request,UserMsg userMsg){
		ValidateUtils.notNull(userMsg.getTitle(), ErrorCode.SYS_001,"消息标题不能为空");
		ValidateUtils.notNull(userMsg.getReceiverId(), ErrorCode.SYS_001,"接收者用户id不能为空");
		
		Long userId = getUserId(request);
		userMsg.setSenderId(userId);
		userMsgService.add(userMsg);
		
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 设置消息为已读
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/json/setRead.htm")
	public @ResponseBody Map setRead(HttpServletRequest request,Long msgId){
		ValidateUtils.notNull(msgId, ErrorCode.SYS_001,"消息id不能为空");
		
		Long userId = getUserId(request);
		userMsgService.setReadByUserId(userId, msgId);
		
		updateNewMsgCount(request,userId);
		
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name删除消息
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/json/del.htm")
	public @ResponseBody Map delMsg(HttpServletRequest request,Long msgId){
		ValidateUtils.notNull(msgId, ErrorCode.SYS_001,"消息id不能为空");
		
		Long userId = getUserId(request);
		userMsgService.delByUserId(userId, msgId);
		
		updateNewMsgCount(request,userId);
		
		return JsonUtils.createSuccess();
	}
	
	//更新session中的用户新消息数
	private void updateNewMsgCount(HttpServletRequest request,Long userId){
		UserInfo userInfo = getUserInfo(request);
		userInfo.setNewMsgCount(userMsgService.countUnread(userId));
		saveUserInfo(request, userInfo);
	}
}
