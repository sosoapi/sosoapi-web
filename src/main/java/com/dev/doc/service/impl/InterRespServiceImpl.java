package com.dev.doc.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dev.base.mybatis.service.impl.BaseMybatisServiceImpl;
import com.dev.doc.dao.InterRespDao;
import com.dev.doc.entity.InterResp;
import com.dev.doc.service.InterRespService;

/**
 * 
		* <p>Title: 接口响应相关服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:38:27</p>
 */
@Service
public class InterRespServiceImpl extends BaseMybatisServiceImpl<InterResp,Long,InterRespDao>
										implements InterRespService{

	@Override
	public List<InterResp> listAllByInterId(Long docId,Long interId) {
		return getMybatisDao().listAllByInterId(docId,interId);
	}

	@Override
	public void updateByDocId(InterResp interResp) {
		getMybatisDao().updateByDocId(interResp);
	}

	@Override
	public void deleteByDocId(Long docId, Long respId) {
		getMybatisDao().deleteByDocId(docId, respId);
	}

	@Override
	public List<InterResp> listAllByDocId(Long docId) {
		return getMybatisDao().listAllByDocId(docId);
	}

	@Override
	public InterResp getByDocId(Long docId, Long respId) {
		return getMybatisDao().getByDocId(docId, respId);
	}

	@Override
	public void deleteByDocIdAndInterId(Long docId, Long interId) {
		getMybatisDao().deleteByDocIdAndInterId(docId, interId);
	}
}
