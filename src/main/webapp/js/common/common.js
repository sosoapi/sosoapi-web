/**
 * 数值格式化
 * @param num 需要格式化的数值
 * @param len 保留的小数位数
 * @return
 */
function formatNum(num, len) {
	return Math.abs(num).toFixed(len);
}

/**
 * 验证是否是数字
 * @param strNumber
 * @return
 */
function isNumber(strNumber) {
	var pattern = /^\d*(?:\.\d{0,8})?$/;
	var m = strNumber.match(pattern);
	if (m == null) {
		return false;
	}
	return true;
}

/**
 * 验证是否是手机号码
 * @param strPhone
 * @return
 */
function isMobile(strPhone) {
	var pattern = /^1[3,5,8][0-9]{9}$/;
	var m = strPhone.match(pattern);
	if (m == null) {
		return false;
	}
	return true;
}

/**
 * 验证是否HH:mm:ss格式的时间
 * @param strDate
 * @return
 */
function isTime(strDate) {
	var pattern = /^(\d{1,2}):(\d{1,2}):(\d{1,2})$/;
	var m = strDate.match(pattern);
	if (m == null) {
		return false;
	}
	return true;
}

/**
 * 验证是否yyyy-MM-dd格式的日期
 * @param strDate
 * @return
 */
function isDate(strDate) {
	var pattern = /^(\d{4})-(\d{2})-(\d{2})$/;
	var m = strDate.match(pattern);
	if (m == null) {
		return false;
	}
	return true;
}

/**
 * 验证是否yyyy-MM-dd HH:mm:ss格式的日期
 * @param strDate
 * @return
 */
function isDateTime(strDate) {
	var pattern = /^(\d{4})-(\d{2})-(\d{2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/;
	var m = strDate.match(pattern);
	if (m == null) {
		return false;
	}
	return true;
}

/**
 * 验证是否EMAIL
 * @param email
 * @return
 */
function isEmail(email) {
	var pattern = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	var m = email.match(pattern);
	if (m == null) {
		return false;
	}
	return true;
}

/**
 * 验证是否为空
 * @param str
 * @return
 */
function isNull(str) {
	if (null == str || "" == str || undefined == str || "null" == str) {
		return true;
	}
	
	return false;
}

/**
 * 验证是否为合法用户名
 * 
 * @param userName
 * @return
 */
function isUserName(userName) {
	if (/^\d.*$/.test(userName)) {
		return 1;
	}
	if (!/^.{5,20}$/.test(userName)) {
		return 2;
	}
	if (!/^[\w_]*$/.test(userName)) {
		return 3;
	}
	return 0;
}

/**
 * 根据指定条件请求系统资源
 *1、 同步
 *2、返回格式为json
 *
 * @param url		//请求url
 * @param param		//请求参数
 * @param callback	//回调函数
 */
function doGet(url, param, callback) {
	ajaxExtendBase("GET", url, param, false, callback);
}

/**
 * 根据指定条件请求系统资源
 *1、 同步
 *2、返回格式为json
 *
 * @param url		//请求url
 * @param param		//请求参数
 * @param callback	//回调函数
 */
function doPost(url, param, callback) {
	ajaxExtendBase("POST", url, param, false, callback);
}

/**
 * ajax请求基础方法
 * @param type
 * @param url
 * @param param
 * @param async
 * @param callback
 */
function ajaxExtendBase(type, url, param, async, callback) {
	$.ajax({
		type : type,
		url : url,
		data : param,
		async : async,
		dataType : "json",
		success : function(result) {
			if (result.errorCode == '0') {
				callback(result.data); 
			} else {
				dealWithException(result.errorMsg);
			}
		},
		complete : function(xmlHttpRequest, textStatus) {
			dealWithResponse(xmlHttpRequest.status);
		},
		headers:{
			"sysReqToken":$("#sysReqToken").val()
		}
	});
}

/**
 * 处理异常信息
 * @param result
 */
function dealWithException(exception) {
	showMsg(exception);
}

/**
 * 处理ajax返回状态
 * @param result
 */
function dealWithResponse(status) {
	if (status == 401) {//无法授权，即后台session过期
		setWindowLocation("forwardLogin.htm");
	}
}

/**
 * 提示信息
 */
function showMsg(msg) {
//	bootbox.alert(msg);
	notice(msg);
}

/**
 * 倒计时
 */
function countDown(selector,secs){
  $(selector).text(secs);
  if(--secs > 0)
     setTimeout( "countDown('" + selector + "'," +secs+ ")" ,1000);
   }
	
/**
 * 通过选择器获取模板内容对应的jquery对象
 * <script id="reqParamTmpl" type="text/html"> 
 * 	<tr></tr>
 * </script>
 * 
 * @param selector
 * @returns
 */
function getTmpl(selector){
	return $($(selector).html());
}

/**
 * 获取下拉框选中的值
 * @param selector
 */
function getSelectValue(selector){
	return $(selector).val();
}

/**
 * 下拉框赋值
 * @param selector
 * @param value
 */
function setSelectValue(selector,value){
	$(selector).val(value);
}

/**
 * 初始化下拉框的值
 */
function initSelectValue(){
	$("select[data-initValue]").each(function(){
		var jqObj = $(this);
		var initValue = jqObj.attr("data-initValue");
		if(initValue){
			jqObj.val(initValue);
		}
	});
}

/**
 * ajax请求数据并初始化下拉框
 * @param url
 * @param data
 * @param selector
 */
function initSelectRemote(url,param,selector){
	doGet(url,param,function(list){
		for(var i = 0;i < list.length; i ++){
			var opt = list[i];
			$(selector).each(function(){
				$(this).append("<option value='" + opt.code + "'>" + opt.name + "</option>");
			});
		}
	});
}

/**
 * 本地数据初始化下拉框
 * @param list {code:'code',name:'name'}
 * @param selector
 */
function initSelectLocal(selector,list){
	for(var i = 0;i < list.length; i ++){
		var opt = list[i];
		$(selector).each(function(){
			$(this).append("<option value='" + opt.code + "'>" + opt.name + "</option>");
		});
	}
}

/**
 * 根据formId重置表单(bootstrapValidator.js)
 * 在重置modal时需要注意，必须在modal显示后才有效，
 * 否则表单元素hidden为true，直接过滤，
 * 可在modal的hide.bs.modal事件中处理验证信息重置操作
 * @param formId
 */
function resetValidForm(formId){
	var jqFormId = "#" + formId;
	//清空数据
	var validForm = $(jqFormId).data('bootstrapValidator');
	if(validForm){
		validForm.resetForm();
	}
	$(jqFormId).resetForm();
}

/**
 * 判断指定的form是否通过验证(bootstrapValidator.js)
 * @param formId
 */
function isFormValid(formId){
	var jqFormId = "#" + formId;
	$(jqFormId).bootstrapValidator('validate');
	return $(jqFormId).data('bootstrapValidator').isValid();
}

/**
 * 格式化json，返回字符串
 * @param jsonObj
 * @returns
 */
function formatJson(jsonObj){
	return JSON.stringify(jsonObj, null, 4);
}

/**
 * 验证是否是json对象
 */
function validJson(jsonStr){
	var result = true;
	try{
		JSON.parse(jsonStr);
	}
	catch(e){
		result = false;
	}
	
	return result;
}

/**
 * 初始化字段
 */
$.fn.setFieldsValue = function(data){
	this.each(function() {
		var $this = $(this);
		var name = this.name;
        var type = this.type;
        var tagName = this.tagName.toLowerCase();
        var value;
        if ((tagName == 'textarea') 
        		|| (tagName == 'input') 
        		|| (tagName == 'select')) {
        	value = data[name];
        	if(value != undefined){
        		$this.val("" + value);
        	}
        }
        else if (type == 'checkbox' || type == 'radio') {

        }
        else if (type == "file") {

        }
    });
};

/**
 * 获取值
 */
$.fn.getFieldsValue = function(){
	var result = new Object();
	this.each(function() {
		var $this = $(this);
		var name = this.name;
        var type = this.type;
        var tagName = this.tagName.toLowerCase();
        var value;
        if ((tagName == 'textarea') 
        		|| (tagName == 'input') 
        		|| (tagName == 'select')) {
        	value = $this.val();
        	result[name] = value;
//        	if(value != undefined && !isNull("" + value)){//boolean处理有问题
//        		result[name] = value;
//        	}
        }
        else if (type == 'checkbox' || type == 'radio') {

        }
        else if (type == "file") {

        }
    });
	
	return result;
};

//通知信息
function notice(msg,close){
	$.jGrowl(msg, {
		sticky: false,
		position:'center',
		life:500,
		close:close
	});
};

//获取url中的参数
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return decodeURI(r[2]); return null; //返回参数值
}