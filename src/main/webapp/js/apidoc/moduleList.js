$(function() {
	//初始化新增操作
	initAddOper();

	//初始化保存操作
	initSaveOper();
});

//初始化新增操作
function initAddOper(){
	$("#addBtn").click(function(){
		$("#operTypeId").val("add");
	});
}

//初始化编辑操作
function initUpdateOper(id){
	$("#operTypeId").val("update");
	
	$("#moduleId").val(id);
	
	doGet("auth/doc/module/json/info.htm","moduleId=" + id,function(moduleInfo){
		$("#moduleForm").find("*").setFieldsValue(moduleInfo);
	});
}

//初始化删除操作
function initDelOper(id){
	bootbox.confirm("确定执行删除操作？",function(){
		var param = new Object();
		param.moduleId = id;
		param.docId = $("#docId").val();
		doGet("auth/doc/module/json/del.htm",param,function(){
			location.reload();
		});
	});
}

//新增操作
function addOper(){
	var param = $("#moduleForm").find("*").getFieldsValue();
	doPost("auth/doc/module/json/add.htm",param,function(data){
		notice("保存成功",function(){
			$('#moduleFormModal').modal('hide');
			location.reload();
		});
	});
}

//编辑操作
function updateOper(){
	var param = $("#moduleForm").find("*").getFieldsValue();
	doPost("auth/doc/module/json/update.htm",param,function(data){
		notice("保存成功",function(){
			$('#moduleFormModal').modal('hide');
			location.reload();
		});
	});
}

//初始化保存操作
function initSaveOper(){
	$("#moduleForm").bootstrapValidator({
		fields:{
			/*code:{
                validators: {
                    notEmpty: {
                        message: '编码不能为空'
                    }
                }
			},*/
			name:{
                validators: {
                    notEmpty: {
                        message: '名称不能为空'
                    }
                }
			},
			sortWeight:{
                validators: {
                	integer: {
                        message: '权重值只能为整数'
                    },
                    notEmpty: {
                        message: '权重值不能为空'
                    }
                }
			}
		}
	});
	
	$("#saveModuleBtn").click(function(){
		if(isFormValid("moduleForm")){
			var operType = $("#operTypeId").val();
			if(operType == 'add'){
				addOper();
			}
			else if(operType == 'update'){
				updateOper();
			}
		}
	});
}