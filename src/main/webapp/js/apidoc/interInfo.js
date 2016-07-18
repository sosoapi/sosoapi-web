$(function() {
	//初始化tab点击事件
	initTabBtn();
	
	//初始化基本信息
	$("#basicInfoTab").click();
});

//初始化tab按钮事件
function initTabBtn(){
	$("#basicInfoTab").click(function(){
		loadBasicInfo();
	});
	
	$("#reqInfoTab").click(function(){
		loadReqInfo();
	});

	$("#respInfoTab").click(function(){
		loadRespInfo();
	});
}