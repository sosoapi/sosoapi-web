$(function() {
	//初始化保存操作
	initSaveDocBtn();
});

//初始化保存操作
function initSaveDocBtn(){
	$("#docInfoFrom").bootstrapValidator({
		fields:{
			title:{
                validators: {
                    notEmpty: {
                        message: '标题不能为空'
                    }
                }
			},
			host:{
                validators: {
                    notEmpty: {
                        message: '访问主机不能为空'
                    }
                }
			},
			basePath:{
                validators: {
                    notEmpty: {
                        message: '接口基路径不能为空'
                    }
                }
			}
		}
	});
	
	$("#saveDocBtn").click(function(){
		if(isFormValid("docInfoFrom")){
			var param = $("#docInfoFrom").find("*").getFieldsValue();
			doPost("auth/doc/json/update.htm",param,function(data){
				notice("保存成功");
			});
		}
	});
}