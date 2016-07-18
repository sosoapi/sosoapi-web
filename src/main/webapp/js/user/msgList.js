$(function() {
	//初始化消息详情
	initMsgContent();
});

//初始化消息详情
function initMsgContent(){
	$(".msg-content-btn").click(function(){
		$("#msgContentModalBody").html($(this).siblings("div").html());
	});
}

//初始化处理操作
function dealOper(id){
	var param = new Object();
	param.msgId = id;
	doGet("auth/msg/json/setRead.htm",param,function(data){
		notice("处理成功",function(){
			location.reload();
		});
	});
}

//初始化删除操作
function delOper(id){
	bootbox.confirm("确定执行删除操作？",function(){
		var param = new Object();
		param.msgId = id;
		doGet("auth/msg/json/del.htm",param,function(data){
			notice("删除成功",function(){
				location.reload();
			});
		});
	});
}