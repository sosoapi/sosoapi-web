package com.dev.doc.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.dev.base.enums.ReqMethod;
import com.dev.base.enums.ReqScheme;
import com.dev.base.exception.code.ErrorCode;
import com.dev.base.mybatis.service.impl.BaseMybatisServiceImpl;
import com.dev.base.util.Pager;
import com.dev.base.utils.FormatUtils;
import com.dev.base.utils.ValidateUtils;
import com.dev.doc.dao.InterDao;
import com.dev.doc.entity.Inter;
import com.dev.doc.service.InterParamService;
import com.dev.doc.service.InterRespService;
import com.dev.doc.service.InterService;
import com.dev.doc.vo.InterInfo;

/**
 * 
		* <p>Title: 接口相关服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:38:27</p>
 */
@Service
public class InterServiceImpl extends BaseMybatisServiceImpl<Inter,Long,InterDao>
										implements InterService{
	@Autowired
	private InterParamService interParamService;
	
	@Autowired
	private InterRespService interRespService;
	
	@Override
	public List<Inter> listByDocId(Long docId, Long moduleId,String name,String description,Pager pager) {
		name = getLikeExpr(name);
		description = getLikeExpr(description);
		
		return getMybatisDao().listByDocId(docId, moduleId,name,description, pager);
	}

	@Override
	public int countByDocId(Long docId, Long moduleId,String name,String description) {
		name = getLikeExpr(name);
		description = getLikeExpr(description);
		
		return getMybatisDao().countByDocId(docId, moduleId,name,description);
	}

	@Override
	public InterInfo getDetailByDocId(Long docId,Long interId) {
		Map detailMap = getMybatisDao().getDetailByDocId(docId,interId);
		
		InterInfo result = new InterInfo();
		if (CollectionUtils.isEmpty(detailMap)) {
			return result;
		}
		
		result.setConsume((String)detailMap.get("consume"));
		result.setDeprecated((Integer)detailMap.get("deprecated") == 1);
		result.setDescription((String)detailMap.get("description"));
		result.setInterId((Long)detailMap.get("id"));
		result.setMethod(ReqMethod.valueOf((String)detailMap.get("method")));
		result.setModuleId((Long)detailMap.get("moduleId"));
		result.setModuleName((String)detailMap.get("moduleName"));
		result.setName((String)detailMap.get("name"));
		result.setPath((String)detailMap.get("path"));
		result.setProduce((String)detailMap.get("produce"));
		result.setScheme(ReqScheme.valueOf((String)detailMap.get("scheme")));
		result.setSummary((String)detailMap.get("summary"));
		result.setSortWeight(((Number)detailMap.get("sortWeight")).intValue());
		
		return result;
	}

	@Override
	public List<Inter> listAllByDocId(Long docId,Boolean deprecated) {
		return getMybatisDao().listAllByDocId(docId,deprecated);
	}

	@Override
	public Inter add(Inter inter){
		validPathAndMethod(inter,true);
		
		getMybatisDao().add(inter);
		return inter;
	}
	
	@Override
	public void updateByDocId(Inter inter) {
		validPathAndMethod(inter,false);
		
		getMybatisDao().updateByDocId(inter);
	}

	@Override
	public Inter update(Inter inter){
		validPathAndMethod(inter,false);
		
		getMybatisDao().update(inter);
		return inter;
	}
	
	@Override
	public void deleteByDocId(Long docId, Long interId) {
		getMybatisDao().deleteByDocId(docId, interId);
		
		//删除关联的请求参数
		interParamService.deleteByDocIdAndInterId(docId, interId);
		
		//删除关联的请求响应
		interRespService.deleteByDocIdAndInterId(docId, interId);
	}

	//同一个文档下的请求url和请求方式两者唯一确定一个接口
	private void validPathAndMethod(Inter inter,boolean isAdd){
		Inter temp = getMybatisDao().getByPathAndMethod(inter.getDocId(), inter.getPath(), inter.getMethod());
		if (isAdd) {//新增
			ValidateUtils.isTrue(temp == null, ErrorCode.DOC_001);
		}
		else if(temp != null){//编辑
			ValidateUtils.isTrue(FormatUtils.isEqual(inter.getId(), temp.getId()), ErrorCode.DOC_001);
		}
	}

	@Override
	public Inter getByDocId(Long docId, Long interId) {
		return getMybatisDao().getByDocId(docId, interId);
	}
}
