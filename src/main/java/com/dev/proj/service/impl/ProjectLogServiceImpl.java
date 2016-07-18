package com.dev.proj.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.dev.base.mybatis.service.impl.BaseMybatisServiceImpl;
import com.dev.base.util.Pager;
import com.dev.base.utils.DateUtil;
import com.dev.proj.dao.ProjectLogDao;
import com.dev.proj.entity.ProjectLog;
import com.dev.proj.service.ProjectLogService;
import com.dev.proj.vo.ProjectLogInfo;

/**
 * 
		* <p>Title: 项目日志</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2016年3月8日上午11:04:21</p>
 */
@Service
public class ProjectLogServiceImpl extends BaseMybatisServiceImpl<ProjectLog,Long,ProjectLogDao>
										implements ProjectLogService{

	@Override
	public List<ProjectLogInfo> listByProjId(Long projId, Pager pager) {
		List<Map> list = getMybatisDao().listByProjId(projId, pager);
		List<ProjectLogInfo> result = new ArrayList<ProjectLogInfo>();
		ProjectLogInfo logInfo = null;
		for (Map map : list) {
			logInfo = new ProjectLogInfo();
			logInfo.setContent((String)map.get("content"));
			logInfo.setTitle((String)map.get("title"));
			logInfo.setProjId((Long)map.get("projId"));
			logInfo.setLogId((Long)map.get("logId"));
			logInfo.setPubNickName((String)map.get("nickName"));
			logInfo.setPubDate((Date)map.get("pubDate"));
			
			result.add(logInfo);
		}
		
		return result;
	}

	@Override
	public int countByProjId(Long projId) {
		return getMybatisDao().countByProjId(projId);
	}

	@Override
	public void delByLogId(Long projId, Long logId) {
		getMybatisDao().delByLogId(projId, logId);
	}

	@Override
	public void addLog(Long userId, Long projId, String title, String content) {
		ProjectLog projectLog = new ProjectLog();
		projectLog.setProjId(projId);
		projectLog.setTitle(title);
		projectLog.setContent(content);
		projectLog.setPubDate(DateUtil.getNow());
		projectLog.setUserId(userId);
		add(projectLog);
	}
}
