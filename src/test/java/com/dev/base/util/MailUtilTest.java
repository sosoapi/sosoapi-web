package com.dev.base.util;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

import com.dev.base.constant.MailConstants;
import com.dev.base.test.BaseTest;
import com.dev.base.utils.MapUtils;

public class MailUtilTest extends BaseTest{

	@Test
	public void testSend() {
		Map<String, Object> model = MapUtils.newMap();
		model.put("name", "good");
		
		MailUtil.send("632526474@qq.com", "test", MailConstants.TMPL_REGIST, model);
	}

	@Test
	public void testSendWithTmpl() {
		fail("Not yet implemented");
	}

}
