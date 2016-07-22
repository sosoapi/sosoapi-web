package com.dev.doc.controller;

import io.swagger.models.Swagger;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev.base.constant.CfgConstants;
import com.dev.base.controller.BaseController;
import com.dev.base.exception.code.ErrorCode;
import com.dev.base.json.JsonUtils;
import com.dev.base.util.FreeMarkerUtil;
import com.dev.base.utils.ValidateUtils;
import com.dev.doc.entity.ApiDoc;
import com.dev.doc.service.ApiDocService;
import com.dev.doc.service.SwaggerService;

/**
 * 
		* <p>Title: swagger api文档</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月13日下午2:20:08</p>
 */
@Controller
public class SwaggerController extends BaseController{
	@Autowired
	private SwaggerService swaggerService;
	
	@Autowired
	private ApiDocService apiDocService;
	
	/** demo对应的资源url*/
	private final static String DEMO_DOC_URL = CfgConstants.WEB_BASE_URL + "swagger/json/sosoapi_demo.json";
	
	/**
	 * 
			*@name 生成swagger相关api文档
			*@Description  
			*@CreateDate 2015年8月13日下午2:24:29
	 */
	@RequestMapping("/auth/apidoc/json/build.htm")
	public @ResponseBody Swagger buildApiDoc(HttpServletRequest request,Long docId){
		ValidateUtils.notNull(docId, ErrorCode.SYS_001,"文档id不能为空");
		
		Long userId = getUserId(request);
		return swaggerService.buildApiDoc(userId, docId);
	}
	
	/**
	 * 
			*@name api doc文档预览
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/auth/apidoc/preview.htm")
	public String preview(HttpServletRequest request,Model model,Long docId){
		ValidateUtils.notNull(docId, ErrorCode.SYS_001,"文档id不能为空");
		String docUrl = CfgConstants.WEB_BASE_URL + "auth/apidoc/json/build.htm?docId=" + docId;
		model.addAttribute("docUrl", docUrl);
		
		return "forward:/swagger/index.jsp";
	}
	
	/**
	 * 
			*@name 预览demo
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/pass/apidoc/demo.htm")
	public String previewDemo(HttpServletRequest request,Model model){
		model.addAttribute("docUrl", DEMO_DOC_URL);
		
		return "forward:/swagger/index.jsp";
	}
	
	/**
	 * 
	 * 		 @name 导出文档
			*@Description  
			*@CreateDate 2016年1月14日下午4:22:38
	 */
	@RequestMapping("/auth/apidoc/export.htm")
	public void export(HttpServletRequest request,HttpServletResponse response,Long docId,String docFormat) throws Exception{
		ValidateUtils.notNull(docId, ErrorCode.SYS_001,"文档id不能为空");
		ValidateUtils.notNull(docFormat, ErrorCode.SYS_001,"文档格式不能为空");
		
		Long userId = getUserId(request);
		
		//下载文件名
		ApiDoc apiDoc = apiDocService.getById(docId);
		
		String title = StringUtils.isEmpty(apiDoc.getTitle()) ? "apiDoc" : apiDoc.getTitle();
		StringBuilder fileNameBuilder = new StringBuilder(title);
		if (!StringUtils.isEmpty(apiDoc.getVersion())) {
			fileNameBuilder.append("_")
						   .append(apiDoc.getVersion());
		}
		fileNameBuilder.append("." + docFormat);
		
		response.setCharacterEncoding("UTF-8");
        response.setContentType("multipart/form-data;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileNameBuilder.toString(), "UTF-8"));
        
        if ("json".equals(docFormat)) {
			dealJsonFormat(response, userId, docId);
		}
        else {
			dealOtherFormat(response, userId, docId);
		}
	}
	
	//导出json格式
	void dealJsonFormat(HttpServletResponse response,Long userId,Long docId){
		//文档信息
		Swagger swagger = swaggerService.buildApiDoc(userId, docId);
		try {
            OutputStream outputStream = response.getOutputStream();
            byte[] content = JsonUtils.toJson(swagger).getBytes();
            outputStream.write(content);
            
            outputStream.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }	
	}
	
	//导出非json格式
	void dealOtherFormat(HttpServletResponse response,Long userId,Long docId){
		Map<String, Object> result = swaggerService.buildDocTmplData(userId, docId);
        try {
            Writer writer = new OutputStreamWriter(response.getOutputStream());
            FreeMarkerUtil.processApiDoc(result, writer);
            
            writer.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
	}
}
