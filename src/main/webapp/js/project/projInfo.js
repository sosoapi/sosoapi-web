$(function() {
	$(".chzn-select").chosen();
	
	//初始化保存操作
	initSaveProjInfo();
});

//初始化保存操作
function initSaveProjInfo(){
	$("#projInfoForm").bootstrapValidator({
		fields:{
			code:{
                validators: {
                    notEmpty: {
                        message: '项目编码不能为空'
                    }
                }
			},
			name:{
                validators: {
                    notEmpty: {
                        message: '项目名称不能为空'
                    }
                }
			}
		}
	});
	
	$("#saveProjInfoBtn").click(function(){
		var param = $("#projInfoForm").find("*").getFieldsValue();
		doPost("auth/proj/json/update.htm",param,function(data){
			notice("保存成功");
		});
	});
}