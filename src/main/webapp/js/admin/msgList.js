$(function(){
	$(".msg-content-btn").click(function(){
		$("#msgContentModalBody").html($(this).siblings("div").html());
	});
});

//初始化删除操作
function delOper(id){
	bootbox.confirm("确定执行删除操作？",function(){
		var param = new Object();
		param.msgId = id;
		doGet("admin/msg/json/del.htm",param,function(data){
			notice("删除成功",function(){
				location.reload();
			});
		});
	});
}