$(function(){
	//更新用户信息
	initSaveBasicInfo();
	
	//更改密码
	initUpdatePasswd();
	
	//更改邮箱
	initUpdateEmail();
});

//更新用户信息
function initSaveBasicInfo(){
	$("#userInfoFrom").bootstrapValidator({
		fields:{
			nickName:{
                validators: {
                    notEmpty: {
                        message: '昵称不能为空'
                    }
                }
			}
		}
	});
	
	$("#updateInfoBtn").click(function(){
		if(isFormValid("userInfoFrom")){
			var param = $("#userInfoFrom").find("*").getFieldsValue();
			doPost("auth/user/json/update.htm",param,function(data){
				notice("用户信息更改成功");
			});
		}
		
		//防止表单提交
		return false;
	});
}

//更改密码
function initUpdatePasswd(){
	$("#updatePasswdFrom").bootstrapValidator({
		fields:{
			oldPasswd:{
                validators: {
                    notEmpty: {
                        message: '旧密码不能为空'
                    }
                }
			},
			newPasswd:{
                validators: {
                    notEmpty: {
                        message: '新密码不能为空'
                    }
                }
			},
            confirmPasswd: {
                validators: {
                	trigger:"blur",
                	notEmpty: {
                        message: '密码确认不能为空'
                    },
                    identical: {
                        field: 'newPasswd',
                        message: '输入的密码不一致'
                    }
                }
            }
		}
	});
	
	$("#updatePasswdBtn").click(function(){
		if(isFormValid("updatePasswdFrom")){
			var param = $("#updatePasswdFrom").find("*").getFieldsValue();
			doPost("auth/user/json/updatePasswd.htm",param,function(data){
				notice("密码更改成功");
				
				resetValidForm("updatePasswdFrom");
			});
		}
		
		//防止表单提交
		return false;
	});
}

//换绑邮箱
function initUpdateEmail(){
	$("#updateEmailFrom").bootstrapValidator({
		fields:{
			passwd:{
                validators: {
                    notEmpty: {
                        message: '当前密码不能为空'
                    }
                }
			},
			email:{
				trigger:"blur",
                validators: {
                    notEmpty: {
                        message: '邮箱不能为空'
                    },
                    emailAddress:{
                    	message: '邮箱格式错误'
                    },
                    remote:{
                    	message: '该邮箱已被使用',
                    	type:'GET',
                    	url:'regist/validEmail.htm',
                    	name:'email'
                    }
                }
			}
		}
	});
	
	$("#sendEmailBtn").click(function(){
		if(isFormValid("updateEmailFrom")){
			var param = $("#updateEmailFrom").find("*").getFieldsValue();
			doPost("auth/user/json/sendUpdateEmailCode.htm",param,function(data){
				notice("邮件发送成功，请按提示执行换绑操作");
				
				resetValidForm("updateEmailFrom");
			});
		}
		
		//防止表单提交
		return false;
	});
}