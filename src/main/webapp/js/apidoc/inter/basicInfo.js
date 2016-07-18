$(function() {
	//初始化模块下拉框
	initModuleSelect();
	
	//初始化保存操作
	initSaveBasicInfoBtn();
});

//初始化模块下拉框
function initModuleSelect(){
	var docId = $("#docId").val();
	initSelectRemote("auth/doc/module/json/list.htm","docId=" + docId,"#moduleSelectId");
}

//加载基本信息
function loadBasicInfo(){
	var param = new Object();
	param.interId = $("#interId").val();
	param.docId = $("#docId").val();
	doGet("auth/doc/inter/json/info.htm",param,function(interInfo){
		$("#basicInfoFrom").find("*").setFieldsValue(interInfo);
	});
}

//初始化保存操作
function initSaveBasicInfoBtn(){
	$("#basicInfoFrom").bootstrapValidator({
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
			}
		}
	});
	
	$("#saveBasicInfoBtn").click(function(){
		if(isFormValid("basicInfoFrom")){
			var param = $("#basicInfoFrom").find("*").getFieldsValue();
			param.interId = $("#interId").val();
			param.docId = $("#docId").val();
			doPost("auth/doc/inter/json/update.htm",param,function(data){
				notice("保存成功");
			});
		}
	});
}
