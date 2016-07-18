$(function(){
	//保存成员信息
	initSaveMemBtn();
});

//初始化编辑信息
function initUpdateMember(userId){
	var param = new Object();
	param.userId = userId;
	param.projId = $("#projId").val();
	doGet("auth/proj/mem/json/info.htm",param,function(memberInfo){
		$("#updateForm").find("*").setFieldsValue(memberInfo);
		$("#updateFormModal").modal("show");
	});
}

//保存成员信息
function initSaveMemBtn(){
	$("#saveMemBtn").click(function(){
		var param = $("#updateForm").find("*").getFieldsValue();
		doPost("auth/proj/mem/json/update.htm",param,function(data){
			notice("保存成功",function(){
				$("#updateFormModal").modal("hide");
				location.reload();
			});
		});
	});
}

//初始化移除成员操作
function removeMember(userId){
	bootbox.confirm("确定执行删除操作？",function(){
		var param = new Object();
		param.userId = userId;
		param.projId = $("#projId").val();
		doPost("auth/proj/mem/json/del.htm",param,function(data){
			notice("已删除",function(){
				//重新加载当前页面
				location.reload();
			});
		});
	});
}