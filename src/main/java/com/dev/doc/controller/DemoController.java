package com.dev.doc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dev.base.controller.BaseController;
import com.dev.base.json.JsonUtils;
import com.dev.base.utils.MapUtils;

/**
 * 
		* <p>Title: 接口事例相关</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年7月11日下午5:39:29</p>
 */
@Controller
@RequestMapping("/demo")
public class DemoController extends BaseController{
	/**
	 * 
			*@name 新增用户
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/user/simple/add.htm")
	public @ResponseBody Map simpleAddUser(HttpServletRequest request,
			String email,String nickName,Integer age){
		Map<String, Object> result = MapUtils.newMap();
		result.put("userId", 123);
		
		return result;
	}
	
	/**
	 * 
			*@name 查询用户列表
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/user/simple/list.htm")
	public @ResponseBody List<Map> simpleListUser(HttpServletRequest request){
		List<Map> result = new ArrayList<Map>();
		for (int i = 1; i < 3; i++) {
			Map<String, Object> info = MapUtils.newMap();
			info.put("email", "邮箱" + i);
			info.put("nickName", "昵称" + i);
			info.put("userId", i);
			
			result.add(info);
		}
		
		return result;
	}
	
	/**
	 * 
			*@name 删除用户
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/user/simple/{userId}/del.htm")
	public @ResponseBody ErrorInfo simpleDelUser(HttpServletRequest request,@PathVariable("userId")Long userId){
		return new ErrorInfo("200", "删除成功");
	}
	
	/**
	 * 
			*@name 查询用户
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/user/simple/{userId}/info.htm")
	public @ResponseBody SimpleUserInfo simpleGetUser(HttpServletRequest request,@PathVariable("userId")Long userId){
		SimpleUserInfo userInfo = new SimpleUserInfo();
		userInfo.setAge(12);
		userInfo.setEmail("demo");
		userInfo.setNickName("demo");
		
		return userInfo;
	}
	
	/**
	 * 
			*@name 更新用户
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/user/simple/{userId}/update.htm")
	public @ResponseBody ErrorInfo simpleUpdateUser(HttpServletRequest request,@PathVariable("userId")Long userId,String nickName){
		return new ErrorInfo("200", "更新成功");
	}
	
	/**
	 * 
			*@throws IOException 
	 * @name 新增用户
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/user/complex/add.htm")
	public @ResponseBody Map complexAddUser(HttpServletRequest request,@RequestBody SimpleUserInfo info) throws IOException{
//		String userInfo = IOUtils.toString(request.getInputStream(), "UTF-8");
		
		Map<String, Object> result = MapUtils.newMap();
		result.put("userId", 123);
		
		return result;
	}
	
	/**
	 * 
			*@name 查询用户列表
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/user/complex/list.htm")
	public @ResponseBody Map complexListUser(HttpServletRequest request){
		List<SimpleUserInfo> list = new ArrayList<SimpleUserInfo>();
		for (int i = 1; i < 3; i++) {
			SimpleUserInfo info = new SimpleUserInfo();
			info.setAge(i);
			info.setEmail("email" + i);
			info.setNickName("nickName" + i);
			list.add(info);
		}
		
		Map<String,Object> data = MapUtils.newMap();
		data.put("totalCount", list.size());
		data.put("list", list);
		
		Map<String,Object> result = MapUtils.newMap();
		result.put("data", data);
		result.put("errorCode", "200");
		
		return result;
	}
	
	/**
	 * 
			*@name 查询用户
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/user/complex/{userId}/info.htm")
	public @ResponseBody Map complexGetUser(HttpServletRequest request,@PathVariable("userId")Long userId){
		List<Map> addressList = new ArrayList<Map>();
		for (int i = 1; i < 3; i++) {
			Map<String, Object> info = MapUtils.newMap();
			info.put("street", "street" + i);
			info.put("city", "city" + i);
			addressList.add(info);
		}
		
		Map<String,Object> result = MapUtils.newMap();
		result.put("userId", 1024);
		result.put("age", 10);
		result.put("nickName", "demo");
		result.put("addressList",addressList);
		
		return result;
	}
	
	/**
	 * 
			*@name 上传接口
			*@Description  
			*@CreateDate 2015年10月15日下午11:03:06
	 */
	@RequestMapping(value = "/user/simple/{userId}/upload.htm", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> uploadImg(@RequestParam(value = "img") MultipartFile file) throws Exception {
		System.out.println(file.getName() + "," + file.getContentType());
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 
			*@Description  
			*@CreateDate 2015年10月15日下午11:03:06
	 */
	@RequestMapping(value = "/test.htm", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> uploadImg(HttpServletRequest request) throws Exception {
		String apiDoc = request.getParameter("apiDoc");
		String fileName = request.getParameter("fileName");
		return JsonUtils.createSuccess();
	}
}

class SimpleUserInfo {
	private String nickName;
	private String email;
	
	private int age;

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}

class ErrorInfo {
	private String errorCode;
	private String errorMsg;
	
	public ErrorInfo(String errorCode,String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}
	
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
