package com.dev.proj.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev.base.controller.BaseController;
import com.dev.base.exception.code.ErrorCode;
import com.dev.base.json.JsonUtils;
import com.dev.base.util.Pager;
import com.dev.base.util.WebPaginate;
import com.dev.base.utils.DateUtil;
import com.dev.base.utils.ValidateUtils;
import com.dev.proj.entity.ProjectLog;
import com.dev.proj.service.ProjectLogService;
import com.dev.proj.vo.ProjectLogInfo;

/**
 * 
		* <p>Title: 项目日志相关</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年7月11日下午5:39:29</p>
 */
@Controller
@RequestMapping("/auth/proj/log")
public class ProjLogController extends BaseController{
	@Autowired
	private ProjectLogService projectLogService;
	
	/**
	 * 
			*@name 项目日志列表
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24

	 */
	@RequestMapping("/list.htm")
	public String list(HttpServletRequest request,Long projId,
						Integer pageNumber,Integer pageSize,Model model){
		ValidateUtils.notNull(projId, ErrorCode.SYS_001,"项目id不能为空");
		
		Pager pager = new Pager(pageNumber, pageSize);
		List<ProjectLogInfo> list = projectLogService.listByProjId(projId,pager);
		int count = projectLogService.countByProjId(projId);
		pager.setTotalCount(count);
		pager.setList(list);
		
		model.addAttribute("pager", pager);
		model.addAttribute("paginate",WebPaginate.build(request, pageNumber, pageSize, count));
		
		return "project/logList";
	}
	
	/**
	 * 
			*@name 新增项目日志
			*@Description  
			*@CreateDate 2015年11月28日下午5:30:24
	 */
	@RequestMapping("/json/add.htm")
	public @ResponseBody Map add(HttpServletRequest request,Long projId,String title,String content){
		ValidateUtils.notNull(projId, ErrorCode.SYS_001,"项目id不能为空");
		ValidateUtils.notNull(title, ErrorCode.SYS_001,"日志标题不能为空");
		
		Long currentId = getUserId(request);
		projectLogService.addLog(currentId, projId, title, content);
		
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 移除项目日志
			*@Description  
			*@CreateDate 2015年8月22日下午2:07:39
	 */
	@RequestMapping("/json/del.htm")
	public @ResponseBody Map delete(HttpServletRequest request,Long projId,Long logId){
		ValidateUtils.notNull(projId, ErrorCode.SYS_001,"项目id不能为空");
		ValidateUtils.notNull(logId, ErrorCode.SYS_001,"日志id不能为空");
		
		projectLogService.delByLogId(projId, logId);
		
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 进入新增日志页面
			*@Description  
			*@CreateDate 2015年8月22日下午2:07:39
	 */
	@RequestMapping("/forwardAddLog.htm")
	public String forwardAddLog(HttpServletRequest request){
		return "project/logInfo";
	}
}
