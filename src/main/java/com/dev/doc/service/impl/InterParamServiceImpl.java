package com.dev.doc.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.base.mybatis.service.impl.BaseMybatisServiceImpl;
import com.dev.doc.dao.InterParamDao;
import com.dev.doc.entity.InterParam;
import com.dev.doc.service.InterParamService;

/**
 * 
		* <p>Title: 接口参数相关服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:38:02</p>
 */
@Service
public class InterParamServiceImpl extends BaseMybatisServiceImpl<InterParam,Long,InterParamDao>
										implements InterParamService{

	@Override
	public List<InterParam> listAllByInterId(Long docId,Long interId) {
		return getMybatisDao().listAllByInterId(docId,interId);
	}

	@Override
	public void deleteByDocIdAndInterId(Long docId,Long interId) {
		getMybatisDao().deleteByDocIdAndInterId(docId,interId);
	}
	
	@Override
	@Transactional
	public void batchAdd(Long docId,Long interId,List<InterParam> list){
		deleteByDocIdAndInterId(docId,interId);
		batchAdd(list);
	}

	@Override
	public List<InterParam> listAllByDocId(Long docId) {
		return getMybatisDao().listAllByDocId(docId);
	}
}
