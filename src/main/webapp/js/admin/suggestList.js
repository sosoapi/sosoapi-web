$(function() {
	//初始化反馈详情
	initSuggestContent();
});

//初始化反馈详情
function initSuggestContent(){
	$(".suggest-content-btn").click(function(){
		$("#suggestContentModalBody").html($(this).siblings("div").html());
	});
}

//初始化处理操作
function dealOper(id){
	var param = new Object();
	param.suggestId = id;
	doGet("admin/suggest/json/deal.htm",param,function(data){
		notice("处理成功",function(){
			location.reload();
		});
	});
}

//初始化删除操作
function delOper(id){
	bootbox.confirm("确定执行删除操作？",function(){
		var param = new Object();
		param.suggestId = id;
		doGet("admin/suggest/json/del.htm",param,function(data){
			notice("删除成功",function(){
				location.reload();
			});
		});
	});
}