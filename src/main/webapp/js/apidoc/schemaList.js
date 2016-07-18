$(function() {
	//初始化数据结构类型下拉框
	initSchemaTypeSelect();
	
	//初始化自定义结构表格
	initCustSchemaTable();
	
	//初始化新增操作
	initAddOper();
	
	//初始化保存操作
	initSaveOper();
});

//初始化数据结构类型下拉框
function initSchemaTypeSelect(){
	//已有结构下拉框
	$("#refSchemaSelectId").hide();
	
	//自定义结构初始化的时候隐藏
	$("#custSchemaTable").hide();
	$("#addCustSchemaRootNodeBtn").hide();
	
	$("#schemaTypeSelect").change(function(){
		var type = $("#schemaTypeSelect").val();
		//处理自定义结构
		if(type == "sys_object" || type == "sys_array"){
			$("#custSchemaTable").show();
			$("#addCustSchemaRootNodeBtn").show();
		}
		else{
			$("#custSchemaTable").hide();
			$("#addCustSchemaRootNodeBtn").hide();
		}
		
		//处理选择已有结构
		if(type == 'sys_ref'){
			$("#refSchemaSelectId").show();
		}
		else{
			$("#refSchemaSelectId").hide();
		}
	});
}

//初始化自定义结构表格
function initCustSchemaTable(){
	$("#custSchemaTable").treegrid({
		onAdded:function(row){
			var $row = $(row);
			//初始化右键菜单
			initContextMenu($row);
			
			//初始化引用的schema下拉框
			var refSchema = $row.find(".cust-ref-schema");
			$row.find(".cust-schema-type").change(function(){
				if($(this).val() == "sys_ref"){
					refSchema.show();
				}
				else{
					refSchema.hide();
				}
			});
			
			//初始化引用下拉框
			$row.find(".cust-schema-type").trigger("change");
		}
	});
	
	//初始化表格右键菜单
	initContextMenu("#custSchemaTable tbody tr");
	
	//新增根节点
	$("#addCustSchemaRootNodeBtn").click(function(){
		var nodeObj = getTmpl("#custSchemaTmpl");
		$("#custSchemaTable").treegrid("addRootNode",nodeObj);
	});
}

//初始化自定义结构表格右键菜单
function initContextMenu(selector){
	$(selector).contextPopup({
        items: [
            {
            	label:'新增',     
            	clazz:'fa fa-plus context-color',
            	action:function(event,row) { 
            		$(row).treegrid("addChildNode",getTmpl("#custSchemaTmpl"));
            	},
            	isEnabled:function(row){
            		var custSchemaTypeSelect = $(row).find(".cust-schema-type");
            		var type = custSchemaTypeSelect.val();
            		return (type == "sys_object" || type == "sys_array");
            	}
            },
            {
            	label:'删除',     
            	clazz:'fa fa-remove context-color',
            	action:function(event,row) { 
            		$(row).treegrid("removeNodes");
            	}
            }
        ]
    });
}

//初始化新增操作
function initAddOper(){
	$("#addBtn").click(function(){
		$("#custSchemaTable").treegrid("clear");
		$("#schemaTypeSelect").trigger("change");
		
		$("#operTypeId").val("add");
	});
}

//初始化编辑操作
function initUpdateOper(id){
	$("#operTypeId").val("update");
	
	$("#schemaId").val(id);
	
	var param = new Object();
	param.schemaId = id;
	param.docId = $("#docId").val();
	doGet("auth/doc/schema/json/info.htm",param,function(schemaInfo){
		//加载之前先清空数据
		$("#custSchemaTable").treegrid("clear");
		
		//填充表单数据
		$("#custSchemaForm").find("*").setFieldsValue(schemaInfo);
		
		//只有自定义结构才需要填充表格数据
		var schemaType = schemaInfo.type;
		if(schemaType == "sys_object" || schemaType == "sys_array"){
			var rowList = JSON.parse(schemaInfo.custSchema);
			var rowData;
			var nodeObj;
			var nodeObjCache = new Object();
			for(var i = 0;i < rowList.length; i ++){
				nodeObj = getTmpl("#custSchemaTmpl");
				rowData = rowList[i];
				if(isNull(rowData.parentId)){
					$("#custSchemaTable").treegrid("addRootNode",nodeObj,rowData.nodeId);
				}
				else{
					nodeObjCache[rowData.parentId].treegrid("addChildNode",nodeObj,rowData.nodeId,rowData.parentId);
				}
				nodeObj.find("*").setFieldsValue(rowData);
				//填充值后初始化引用下拉框
				nodeObj.find(".cust-schema-type").trigger("change");
				
				nodeObjCache[rowData.nodeId] = nodeObj;
			}
		}
		
		$("#schemaTypeSelect").trigger("change");
	});
}

//组装相关的表单信息
function serializeCustSchemaForm(){
	var param = $("#custSchemaForm").find("*").getFieldsValue();
	param.valid = true;
	
	//只有自定义结构才需要序列化表格数据
	var schemaType = $("#schemaTypeSelect").val();
	if(schemaType == "sys_object" || schemaType == "sys_array"){
		var nodeArray = new Array();
		$("#custSchemaTable").treegrid("getAllNodes").each(function(index,element){
			var $element = $(element);
			var rowData = $element.find("*").getFieldsValue();
			if(isNull(rowData.code)){
				param.valid = false;
			}
			
			rowData.nodeId = $element.treegrid("getNodeId");
			rowData.parentId = $element.treegrid("getParentNodeId");
			
			nodeArray[index] = rowData;
		});
		
		param.custSchema = JSON.stringify(nodeArray);
	}
	
	return param;
}

//新增操作
function addOper(){
	var param = serializeCustSchemaForm();
	if(!param.valid){
		notice("编码不能为空");
		return;
	}
	
	doPost("auth/doc/schema/json/add.htm",param,function(data){
		notice("保存成功",function(){
			$("#schemaFormModal").modal("hide");
			location.reload();
		});
	});
}

//初始化删除操作
function initDelOper(id){
	bootbox.confirm("确定执行删除操作？",function(){
		var param = new Object();
		param.schemaId = id;
		param.docId = $("#docId").val();
		doGet("auth/doc/schema/json/del.htm",param,function(){
			location.reload();
		});
	});
}

//编辑操作
function updateOper(){
	var param = serializeCustSchemaForm();
	if(!param.valid){
		notice("编码不能为空");
		return;
	}
	
	doPost("auth/doc/schema/json/update.htm",param,function(data){
		notice("保存成功",function(){
			$('#schemaFormModal').modal('hide');
			location.reload();
		});
	});
}

//初始化保存操作
function initSaveOper(){
	$("#custSchemaForm").bootstrapValidator({
		fields:{
			code:{
                validators: {
                    notEmpty: {
                        message: '编码不能为空'
                    }
                }
			}
		}
	});
	
	$("#saveSchemaBtn").click(function(){
		if(isFormValid("custSchemaForm")){
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
