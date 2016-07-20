package com.dev.proj.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.dev.base.constant.AppConstants;
import com.dev.base.constant.CfgConstants;
import com.dev.base.constant.MailConstants;
import com.dev.base.enums.Role;
import com.dev.base.exception.AuthException;
import com.dev.base.exception.code.ErrorCode;
import com.dev.base.json.JsonUtils;
import com.dev.base.mail.service.MailService;
import com.dev.base.mybatis.service.impl.BaseMybatisServiceImpl;
import com.dev.base.util.MailUtil;
import com.dev.base.util.Pager;
import com.dev.base.utils.CryptUtil;
import com.dev.base.utils.FormatUtils;
import com.dev.base.utils.MapUtils;
import com.dev.base.utils.RegexUtil;
import com.dev.base.utils.ValidateUtils;
import com.dev.proj.dao.ProjectMemberDao;
import com.dev.proj.entity.Project;
import com.dev.proj.entity.ProjectMember;
import com.dev.proj.service.ProjectMemberService;
import com.dev.proj.service.ProjectService;
import com.dev.proj.vo.ProjectInfo;
import com.dev.proj.vo.ProjectMemberInfo;
import com.dev.user.entity.UserBasic;
import com.dev.user.entity.UserDetail;
import com.dev.user.service.UserBasicService;
import com.dev.user.service.UserDetailService;
import com.dev.user.vo.UserInfo;

/**
 * 
		* <p>Title: 项目成员相关服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:38:27</p>
 */
@Service
public class ProjectMemberServiceImpl extends BaseMybatisServiceImpl<ProjectMember,Long,ProjectMemberDao>
										implements ProjectMemberService{
	@Autowired
	private MailService mailService;
	
	@Autowired
	private UserBasicService userBasicService;
	
	@Autowired
	private UserDetailService userDetailService;
	
	@Autowired
	private ProjectService projectService;
	
	@Override
	public List<ProjectMemberInfo> listByProjId(Long projId, Role role,
									String nickName,String email,Pager pager) {
		nickName = getLikeExpr(nickName);
		email = getLikeExpr(email);
		
		List<Map> list = getMybatisDao().listByProjId(projId, role,nickName,email, pager);
		
		List<ProjectMemberInfo> result = new ArrayList<ProjectMemberInfo>();
		for (Map info : list) {
			result.add(parseMemberInfo(info));
		}
		
		return result;
	}

	//组装成员信息
	private ProjectMemberInfo parseMemberInfo(Map info){
		ProjectMemberInfo result = new ProjectMemberInfo();
		result.setCreateDate((Date)info.get("createDate"));
		result.setEmail((String)info.get("email"));
		result.setNickName((String)info.get("nickName"));
		result.setUserId((Long)info.get("userId"));
		result.setRole(Role.valueOf((String)info.get("role")));
		
		return result;
	}
	
	@Override
	public int countByProjId(Long projId, Role role,String nickName,String email) {
		nickName = getLikeExpr(nickName);
		email = getLikeExpr(email);
		
		return getMybatisDao().countByProjId(projId, role,nickName,email);
	}

	@Override
	public void invite(Long userId, Long projId, String invitedEmail) {
		validAdminRole(userId,projId);
		
		Map<String, Object> inviteInfo = MapUtils.newMap();
		inviteInfo.put("inviterId", userId);
		inviteInfo.put("projId", projId);
		inviteInfo.put("invitedEmail", invitedEmail);
		
		String code = CryptUtil.encryptAES(JsonUtils.toJson(inviteInfo),AppConstants.DEFAULT_SECRET_KEY);
		UserDetail detail = userDetailService.getByUserId(userId);
		Project project = projectService.getById(projId);
		Map<String, Object> model = MapUtils.newMap();
		model.put("email", invitedEmail);
		model.put("friendNickName", detail.getNickName());
		model.put("projName", project.getName());
		model.put("projAcceptUrl", CfgConstants.WEB_BASE_URL + "auth/proj/mem/accept.htm?code=" + code);
		
		MailUtil.send(invitedEmail, MailConstants.SUB_PROJ_MEM_INVITE, 
				MailConstants.TMPL_PROJ_MEM_INVITE, model);
	}

	@Override
	public Long accept(Long userId, String code) {
		Map<String, Object> inviteInfo = JsonUtils.toObject(CryptUtil.decryptAES(code,AppConstants.DEFAULT_SECRET_KEY), HashMap.class);
		UserBasic userBasic = userBasicService.getById(userId);
		
		String invitedEmail = (String)inviteInfo.get("invitedEmail");
		ValidateUtils.isTrue(RegexUtil.isEmail(invitedEmail), ErrorCode.SYS_001);
		
		Long projId = FormatUtils.parseLong(inviteInfo.get("projId"));
		//当前用户与被邀请的不是同一个
		if (!userBasic.getEmail().equals(invitedEmail)) {
			throw new AuthException();
		}
		
		accept(userId, projId, Role.guest);
		
		return projId;
	}

	@Override
	public void accept(Long userId, Long projId,Role role) {
		boolean isAccept = getMybatisDao().countRecord(userId, projId) > 0;
		if (!isAccept) {//未加入过，则加入到当前项目中
			ProjectMember projectMember = new ProjectMember();
			projectMember.setProjId(projId);
			projectMember.setUserId(userId);
			projectMember.setRole(role);
			add(projectMember);
		}
	}
	
	@Override
	public void remove(Long currentId,Long userId, Long projId) {
		validAdminRole(currentId,projId);
		if (FormatUtils.isEqual(currentId, userId)) {//只有移除自己时才判断是否还有其他管理员
			validExistAdmin(userId,projId);
		}
		
		getMybatisDao().delByUserIdAndProjId(userId, projId);
	}


	@Override
	public void quit(Long userId, Long projId) {
		validExistAdmin(userId,projId);
		getMybatisDao().delByUserIdAndProjId(userId, projId);
	}
	
	@Override
	public List<ProjectInfo> listAuthProjectInfo(Long userId) {
		List<ProjectInfo> result = new ArrayList<ProjectInfo>();
		
		List<Map> infoList = getMybatisDao().listAuthProjectInfo(userId);
		ProjectInfo projectInfo = null;
		for (Map info : infoList) {
			projectInfo = new ProjectInfo();
			projectInfo.setProjId((Long)info.get("projId"));
			projectInfo.setDocId((Long)info.get("docId"));
			projectInfo.setRole(Role.valueOf((String)info.get("role")));
			
			result.add(projectInfo);
		}
		
		return result;
	}

	@Override
	public void updateRole(Long currentId,Long projId, Long userId, Role role) {
		validAdminRole(currentId, projId);
		if (role != Role.admin && FormatUtils.isEqual(currentId, userId)) {
			validExistAdmin(userId,projId);
		}
		
		getMybatisDao().updateRole(projId, userId, role);
	}

	@Override
	public ProjectMemberInfo getByUserIdAndProjId(Long userId, Long projId) {
		ProjectMemberInfo result = new ProjectMemberInfo();
		Map info = getMybatisDao().getByUserIdAndProjId(userId, projId);
		if (!CollectionUtils.isEmpty(info)) {
			result = parseMemberInfo(info);
		}
		
		return result;
	}

	@Override
	public List<Long> listAdmin(Long projId) {
		return getMybatisDao().listAdmin(projId);
	}
	
	@Override
	public Role getRole(Long userId, Long projId) {
		String role = getMybatisDao().getRole(userId, projId);
		return Role.valueOf(role);
	}

	@Override
	public List<String> listEmail(Long projId, Role role) {
		return getMybatisDao().listEmail(projId, role);
	}

	@Override
	public void sendNotice(UserInfo userInfo, Long projId, 
							String receiverRole,String otherReceivers,
								String title, String content) {
		validAdminRole(userInfo.getUserId(),projId);
		
		List<String> emailList = parseReceiver(projId, receiverRole,otherReceivers);
		MailUtil.sendNotice(emailList, title, content);
	}
	
	//组装通知的用户列表
	private List<String> parseReceiver(Long projId,String receiverRole,String otherReceivers){
		Set<String> receiverSet = new HashSet<String>();
		if (!"none".equals(receiverRole)) {
			Role role = StringUtils.isEmpty(receiverRole) ? null : Role.valueOf(receiverRole);
			receiverSet.addAll(listEmail(projId, role));
		}
		
		if (!StringUtils.isEmpty(otherReceivers)) {
			String[] receiverArray = otherReceivers.split(";");
			for (String receiver : receiverArray) {
				if (RegexUtil.isEmail(receiver)) {
					receiverSet.add(receiver);
				}
			}
		}
		
		return new ArrayList<String>(receiverSet);
	}
	
	//保证至少有一个管理员
	private void validExistAdmin(Long userId,Long projId){
		List<Long> adminList = listAdmin(projId);
		adminList.remove(userId);
		ValidateUtils.isTrue(adminList.size() > 0, ErrorCode.PROJ_001);
	}
	
	//判断用户在当前项目中是否是管理员
	private void validAdminRole(Long userId,Long projId){
		Role currentRole = getRole(userId, projId);
		ValidateUtils.isTrue(currentRole == Role.admin, ErrorCode.PROJ_002);
	}

	@Override
	public List<ProjectMemberInfo> listForAdd(Long userId, Long projId, Pager pager) {
		List<ProjectMemberInfo> result = new ArrayList<ProjectMemberInfo>();

		List<Map> list = getMybatisDao().listForAdd(userId, projId, pager);
		ProjectMemberInfo memberInfo = null;
		for (Map info : list) {
			memberInfo = new ProjectMemberInfo();
			memberInfo.setNickName((String)info.get("nickName"));
			memberInfo.setEmail((String)info.get("email"));
			memberInfo.setUserId((Long)info.get("userId"));
			
			result.add(memberInfo);
		}
		
		return result;
	}

	@Override
	public int countForAdd(Long userId, Long projId) {
		return getMybatisDao().countForAdd(userId, projId);
	}

	@Override
	public void add(Long inviterId, Long userId, Long projId, Role role) {
		validAdminRole(inviterId, projId);
		
		accept(userId, projId, role);
	}
}
