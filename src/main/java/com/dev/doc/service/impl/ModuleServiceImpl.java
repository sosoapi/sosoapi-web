package com.dev.doc.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dev.base.mybatis.service.impl.BaseMybatisServiceImpl;
import com.dev.base.util.Pager;
import com.dev.doc.dao.ModuleDao;
import com.dev.doc.entity.Module;
import com.dev.doc.service.ModuleService;

/**
 * 
		* <p>Title: 模块相关服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:38:27</p>
 */
@Service
public class ModuleServiceImpl extends BaseMybatisServiceImpl<Module,Long,ModuleDao>
										implements ModuleService{
	@Override
	public List<Module> listByDocId(Long docId,String code,String name,String description,Pager pager) {
		code = getLikeExpr(code);
		name = getLikeExpr(name);
		description = getLikeExpr(description);
		
		return getMybatisDao().listByDocId(docId,code,name,description,pager);
	}

	@Override
	public int countByDocId(Long docId,String code,String name,String description) {
		code = getLikeExpr(code);
		name = getLikeExpr(name);
		return getMybatisDao().countByDocId(docId,code,name,description);
	}

	@Override
	public void updateByDocId(Module module) {
		getMybatisDao().updateByDocId(module);
	}

	@Override
	public void deleteByDocId(Long docId, Long moduleId) {
		getMybatisDao().deleteByDocId(docId, moduleId);
	}

	@Override
	public List<Module> listAllByDocId(Long docId) {
		return listByDocId(docId, null, null, null, null);
	}
}
