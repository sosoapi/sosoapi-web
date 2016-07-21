$(function() {
	//当前文档相关的模块列表
	var moduleListData = listModuleData();
	
	//初始化模块下拉框
	initModuleSelect(moduleListData);
	
	//将列表中的moduleId翻译为moduleName
	parseModuleName(moduleListData);
	
	//初始化新增操作
	initAddOper();

	//初始化保存操作
	initSaveOper();
	
	//初始化下拉框的值,setting中的initSelectValue在获取模块下拉框列表之前，为空，无法初始化
	initSelectValue();
});

//初始化新增操作
function initAddOper(){
	$("#addBtn").click(function(){
		$("#operTypeId").val("add");
		var moduleId = $("#searchModuleId").val();
		if(!isNull(moduleId)){
			$("#addModuleId").val(moduleId);
		}
	});
}

//初始化复制操作
function initCopyOper(id,interName){
	bootbox.confirm("确定复制当前接口？",function(){
		var param = new Object();
		param.interId = id;
		param.docId = $("#docId").val();
		doPost("auth/doc/inter/json/copy.htm",param,function(data){
			notice("复制并创建成功",function(){
				var url = window.location.href;
				window.location.href = url.replace(window.location.search, '') + $.query.set("name",interName + $("#copyFlag").val());
			});
		});
	});
}

//初始化编辑操作
function initUpdateOper(id){
	$("#operTypeId").val("update");
	
	$("#interId").val(id);
	
	var param = new Object();
	param.interId = id;
	param.docId = $("#docId").val();
	doGet("auth/doc/inter/json/info.htm",param,function(moduleInfo){
		$("#interForm").find("*").setFieldsValue(moduleInfo);
	});
}

//初始化删除操作
function initDelOper(id){
	bootbox.confirm("确定执行删除操作？",function(){
		var param = new Object();
		param.interId = id;
		param.docId = $("#docId").val();
		
		doGet("auth/doc/inter/json/del.htm",param,function(){
			location.reload();
		});
	});
}

//新增操作
function addOper(){
	var param = $("#interForm").find("*").getFieldsValue();
	doPost("auth/doc/inter/json/add.htm",param,function(data){
		notice("保存成功",function(){
			$('#interForm').modal('hide');
			location.reload();
		});
	});
}

//编辑操作
function updateOper(){
	var param = $("#interForm").find("*").getFieldsValue();
	doPost("auth/doc/inter/json/update.htm",param,function(data){
		notice("保存成功",function(){
			$('#interForm').modal('hide');
			location.reload();
		});
	});
}

//初始化保存操作
function initSaveOper(){
	$("#interForm").bootstrapValidator({
		fields:{
			name:{
                validators: {
                    notEmpty: {
                        message: '接口名称不能为空'
                    }
                }
			},
			path:{
                validators: {
                    notEmpty: {
                        message: '请求url不能为空'
                    }
                }
			},
			sortWeight:{
                validators: {
                	integer: {
                        message: '权重值只能为整数'
                    },
                    notEmpty: {
                        message: '权重值不能为空'
                    }
                }
			}
		}
	});
	
	$("#saveBtn").click(function(){
		if(isFormValid("interForm")){
			var operType = $("#operTypeId").val();
			if(operType == 'add'){
				addOper();
			}
			else if(operType == 'update'){
				updateOper();
			}
		}
	});
}

//获取当前文档相关的所有模块数据
function listModuleData(){
	var docId = $("#docId").val();
	var result = new Array();
	doGet("auth/doc/module/json/list.htm","docId=" + docId,function(list){
		result = list;
	});
	
	return result;
}

//将列表中的moduleId翻译为moduleName
function parseModuleName(moduleListData){
	$(".module-name").each(function(){
		var jqObj = $(this);
		var module = new Object();
		for(var i = 0; i < moduleListData.length; i ++){
			module = moduleListData[i];
			if(module.code == jqObj.text()){
				jqObj.text(module.name);
				break;
			}
		}
	});
}

//初始化模块下拉框
function initModuleSelect(moduleListData){
	initSelectLocal(".module-select",moduleListData);
}