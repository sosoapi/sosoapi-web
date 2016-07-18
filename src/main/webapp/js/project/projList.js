$(function() {
	//初始化新增操作
	initAddProj();
});

//初始化删除操作
function delProj(projId){
	bootbox.confirm("确定执行删除操作？",function(){
		var param = new Object();
		param.projId = projId;
		doPost("auth/proj/json/del.htm",param,function(data){
			notice("删除成功",function(){
				location.reload();
			});
		});
	});
}

//初始化删除操作
function copyProj(projId,projName){
	bootbox.confirm("确定复制当前项目？",function(){
		var param = new Object();
		param.projId = projId;
		doPost("auth/proj/json/copy.htm",param,function(data){
			notice("复制并创建成功",function(){
				var url = window.location.href;
				window.location.href = url.replace(window.location.search, '') + $.query.set("name",projName + $("#copyFlag").val());
			});
		});
	});
}

//初始化移除成员操作
function quitProj(projId){
	bootbox.confirm("确定退出当前项目？",function(){
		var param = new Object();
		param.projId = projId;
		doPost("auth/proj/json/quit.htm",param,function(data){
			//重新加载当前页面
			location.reload();
		});
	});
}

//初始化保存操作
function initAddProj(){
	$("#projForm").bootstrapValidator({
		fields:{
			code:{
                validators: {
                    notEmpty: {
                        message: '项目编码不能为空'
                    }
                }
			},
			name:{
                validators: {
                    notEmpty: {
                        message: '项目名称不能为空'
                    }
                }
			}
		}
	});
	
	$("#saveProjBtn").click(function(){
		if(isFormValid("projForm")){
			var param = $("#projForm").find("*").getFieldsValue();
			doPost("auth/proj/json/add.htm",param,function(data){
				notice("保存成功",function(){
					$('#projFormModal').modal('hide');
					location.reload();
				});
			});
		}
	});
}