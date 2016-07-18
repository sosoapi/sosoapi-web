package com.dev.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dev.admin.entity.UserCube;
import com.dev.admin.service.UserCubeService;
import com.dev.base.controller.BaseController;
import com.dev.base.util.Pager;
import com.dev.base.util.WebPaginate;

/**
 * 
		* <p>Title: 数据统计</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年9月6日下午4:19:22</p>
 */
@Controller
@RequestMapping("/admin/cube")
public class CubeController extends BaseController{
	@Autowired
	private UserCubeService userCubeService;
	
	/**
	 * 
			*@name 用户统计
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/user.htm")
	public String listUserCube(HttpServletRequest request,Model model,Integer pageNumber,Integer pageSize){
		Pager pager = new Pager(pageNumber, pageSize);
		List<UserCube> list = userCubeService.list(pager);
		int count = userCubeService.count();
		pager.setTotalCount(count);
		pager.setList(list);
		
		model.addAttribute("pager", pager);
		model.addAttribute("paginate",WebPaginate.build(request, pageNumber, pageSize, count));
		model.addAttribute("currentCube", userCubeService.cubeCurrent());
		
		return "admin/userCubeList";
	}
}
