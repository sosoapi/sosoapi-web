package com.dev.base.tag;

import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.dev.base.constant.AppConstants;
import com.dev.base.enums.Role;
import com.dev.user.vo.UserInfo;

/**
 * 
		* <p>Title: 项目权限验证</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月30日下午12:00:14</p>
 */
public class ProjAuthTag extends TagSupport{
	private static final long serialVersionUID = 1L;

	/** 项目id*/
	private Long projId;
	
	/** 文档id*/
	private Long docId;
	
	@Override
	public int doStartTag() throws JspException {
		int result = SKIP_BODY;
		HttpSession session = pageContext.getSession();  
        UserInfo userInfo = (UserInfo)session.getAttribute(AppConstants.SESSION_KEY_USER);
        if (userInfo != null) {
        	Role role = null;
        	if (projId != null) {//验证项目权限
    			Map<Long, Role> projMap = userInfo.getProjMap();
    			role = projMap.get(projId);
    		}
    		
    		if (docId != null) {//验证文档权限
    			Map<Long, Role> docMap = userInfo.getDocMap();
    			role = docMap.get(docId);
    		}
    		
    		if (Role.admin == role || Role.viewer == role) {
				result = EVAL_PAGE;
			}
		}
		
		return result;
	}

	public Long getProjId() {
		return projId;
	}

	public void setProjId(Long projId) {
		this.projId = projId;
	}

	public Long getDocId() {
		return docId;
	}

	public void setDocId(Long docId) {
		this.docId = docId;
	}
}
