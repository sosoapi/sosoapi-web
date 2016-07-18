package com.dev.user.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.dev.base.enums.SuggestStatus;
import com.dev.base.enums.SuggestType;
import com.dev.base.mybatis.service.impl.BaseMybatisServiceImpl;
import com.dev.base.util.Pager;
import com.dev.base.utils.DateUtil;
import com.dev.user.dao.SuggestDao;
import com.dev.user.entity.Suggest;
import com.dev.user.service.SuggestService;
import com.dev.user.vo.SuggestInfo;

/**
 * 
		* <p>Title: 用户建议意见相关服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月29日下午10:21:28</p>
 */
@Service
public class SuggestServiceImpl extends BaseMybatisServiceImpl<Suggest,Long,SuggestDao>
										implements SuggestService{

	@Override
	public void add(Long userId,String title,SuggestType type,String content) {
		Suggest suggest = new Suggest();
		suggest.setUserId(userId);
		suggest.setTitle(title);
		suggest.setContent(content);
		suggest.setStatus(SuggestStatus.dealing);
		suggest.setType(type);
		
		add(suggest);
	}

	@Override
	public List<SuggestInfo> listAll(String email, String nickName,
										SuggestStatus status,SuggestType type, Pager pager) {
		email = getLikeExpr(email);
		nickName = getLikeExpr(nickName);
		List<Map> infoList = getMybatisDao().listAll(email, nickName, status,type, pager);
		
		List<SuggestInfo> result = new ArrayList<SuggestInfo>();
		for (Map info : infoList) {
			result.add(parseDetailInfo(info));
		}
		
		return result;
	}

	@Override
	public SuggestInfo getDetailById(Long id) {
		Map info = getMybatisDao().getDetailById(id);
		return parseDetailInfo(info);
	}
	
	//组装详情
	private SuggestInfo parseDetailInfo(Map info){
		SuggestInfo suggestInfo = new SuggestInfo();
		suggestInfo.setSuggestId((Long)info.get("suggestId"));
		suggestInfo.setTitle((String)info.get("title"));
		suggestInfo.setContent((String)info.get("content"));
		suggestInfo.setCreateDate((Date)info.get("createDate"));
		suggestInfo.setStatus(SuggestStatus.valueOf((String)info.get("status")));
		suggestInfo.setEmail((String)info.get("email"));
		suggestInfo.setNickName((String)info.get("nickName"));
		suggestInfo.setUserId((Long)info.get("userId"));
		suggestInfo.setType(SuggestType.valueOf((String)info.get("type")));
		
		return suggestInfo;
	}
	
	@Override
	public int countAll(String email, String nickName, SuggestStatus status,SuggestType type) {
		email = getLikeExpr(email);
		nickName = getLikeExpr(nickName);
		
		return getMybatisDao().countAll(email, nickName, status,type);
	}

	@Override
	public void dealSuggest(Long id) {
		Suggest suggest = new Suggest();
		suggest.setId(id);
		suggest.setModifyDate(DateUtil.getNow());
		suggest.setStatus(SuggestStatus.dealed);
		suggest.setDealDate(DateUtil.getNow());
		update(suggest);
	}
}
