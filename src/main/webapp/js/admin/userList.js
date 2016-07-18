$(function() {
	//初始化保存操作
	$("#saveUserBtn").click(function(){
		var param = $("#userForm").find("*").getFieldsValue();
		doPost("admin/user/json/update.htm",param,function(data){
			notice("保存成功",function(){
				$('#userFormModal').modal('hide');
				location.reload();
			});
		});
	});
});

//初始化编辑操作
function initUpdateOper(id){
	$("#userForm").resetForm();
	$("#userId").val(id);
	
	doGet("admin/user/json/info.htm","userId=" + id,function(userInfo){
		$("#userForm").find("*").setFieldsValue(userInfo);
	});
}
