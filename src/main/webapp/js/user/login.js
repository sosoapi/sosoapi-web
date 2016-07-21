$(function () {
	//初始化选项卡
	initTab();
	
	//初始化登陆
	initLogin();

	//初始化注册
	initRegist();
	
	//初始化发送激活授权码
	initSendActiveCode();
	
	//初始化发送重置密码授权码
	initSendResetCode();
});

//初始化选项卡
function initTab(){
	$('.list-inline li > a').click(function () {
        var activeForm = $(this).attr('href') + ' > form';
        $(activeForm).addClass('magictime swap');
        setTimeout(function () {
            $(activeForm).removeClass('magictime swap');
        }, 1000);
    });
}

//初始化发送激活授权码
function initSendActiveCode(){
	$("#activeForm").bootstrapValidator({
		fields:{
			email:{
				trigger:"blur",
                validators: {
                    notEmpty: {
                        message: '邮箱不能为空'
                    },
                    emailAddress:{
                    	message: '邮箱格式错误'
                    }
                }
			}
		}
	});
	
	$("#sendActiveCodeBtn").click(function(){
		if(isFormValid("activeForm")){
			var param = new Object();
			param.email = $("#activeCodeEmail").val();
			doPost("regist/json/sendActiveCode.htm",param,function(data){
				notice("发送成功");
			});
		}
	});
}

//初始化发送重置密码授权码
function initSendResetCode(){
	$("#forgotForm").bootstrapValidator({
		fields:{
			email:{
				trigger:"blur",
                validators: {
                    notEmpty: {
                        message: '邮箱不能为空'
                    },
                    emailAddress:{
                    	message: '邮箱格式错误'
                    }
                }
			}
		}
	});
	
	$("#sendResetCodeBtn").click(function(){
		if(isFormValid("forgotForm")){
			var param = new Object();
			param.email = $("#resetCodeEmail").val();
			doPost("json/sendResetCode.htm",param,function(data){
				notice("发送成功");
			});
		}
	});
}

//初始化登陆
function initLogin(){
	var loginValidCodeImgSrc = $("#loginValidCodeImg").attr("src");
	//初始化验证码
	$("#loginValidCodeImg").click(function(){
    	this.src = loginValidCodeImgSrc + "&rand=" + Math.random();;
    });
	
	//登陆时处理自动登陆
	$("#loginBtn").click(function(){
		$.cookie("autoLogin", "", { expires: -1 });
		$.cookie("loginName", "", { expires: -1 });
		$.cookie("loginPasswd", "", { expires: -1 });
	});
	
	$("#loginForm").bootstrapValidator({
		fields:{
			loginName:{
				trigger:"blur",
                validators: {
                    notEmpty: {
                        message: '登陆邮箱不能为空'
                    },
                    emailAddress:{
                    	message: '邮箱格式错误'
                    }
                }
			},
			passwd:{
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    }
                }
			},
			validCode:{
                validators: {
                    notEmpty: {
                        message: '验证码不能为空'
                    }
                }
			}
		}
	});
}

//初始化注册
function initRegist(){
	var registValidCodeImgSrc = $("#registValidCodeImg").attr("src");
	//初始化验证码
	$("#registValidCodeImg").click(function(){
    	this.src = registValidCodeImgSrc + "&rand=" + Math.random();;
    });
	
	$("#registForm").bootstrapValidator({
		fields:{
			nickName:{
                validators: {
                    notEmpty: {
                        message: '昵称不能为空'
                    }
                }
			},
			loginName:{
				trigger:"blur",
                validators: {
                    notEmpty: {
                        message: '邮箱不能为空'
                    },
                    emailAddress:{
                    	message: '邮箱格式错误'
                    },
                    remote:{
                    	message: '该邮箱已注册',
                    	type:'GET',
                    	url:'regist/validEmail.htm',
                    	name:'email'
                    }
                }
			},
			passwd:{
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    }
                }
			},
            confirmPasswd: {
            	trigger:"blur",
                validators: {
                	notEmpty: {
                        message: '密码确认不能为空'
                    },
                    identical: {
                        field: 'passwd',
                        message: '输入的密码不一致'
                    }
                }
            },
            validCode:{
                validators: {
                    notEmpty: {
                        message: '验证码不能为空'
                    }
                }
			}
		}
	});
}
