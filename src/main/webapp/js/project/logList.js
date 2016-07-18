$(function(){
	$(".msg-content-btn").click(function(){
		$("#msgContentModalBody").html($(this).siblings("div").html());
	});
});

//初始化删除操作
function delOper(projId,logId){
	bootbox.confirm("确定执行删除操作？",function(){
		var param = new Object();
		param.logId = logId;
		param.projId = projId;
		doGet("auth/proj/log/json/del.htm",param,function(data){
			notice("删除成功",function(){
				location.reload();
			});
		});
	});
}