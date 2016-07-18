$(function() {
	//初始化数据结构类型下拉框
	initSchemaTypeSelect();
	
	//初始化自定义结构表格
	initCustSchemaTable();
	
	//初始化新增操作
	initAddOper();
	
	//初始化保存操作
	initSaveOper();
	
	//格式化json操作
	initRespFormat();
});

//格式化json操作
function initRespFormat(){
	$("#respFormatSchemaBtn").click(function(){
		var extSchema = $("#respExtSchemaArea").val();
		try{
			var jqObj = JSON.parse(extSchema);
			$("#respExtSchemaArea").val(formatJson(jqObj));
		}
		catch(e){
			notice(e.message);
		}
	});
}

//初始化数据结构类型下拉框
function initSchemaTypeSelect(){
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
			$("#refSchemaDiv").show();
		}
		else{
			$("#refSchemaDiv").hide();
		}
		
		//处理扩展结构
		if(type == "cust_json"){
			$("#respExtSchemaDiv").show();
			$("#respFormatSchemaBtn").show();
		}
		else{
			$("#respExtSchemaDiv").hide();
			$("#respFormatSchemaBtn").hide();
		}
	});
}

//初始化自定义结构表格
function initCustSchemaTable(){
	$("#custSchemaTable").treegrid({
		onAdded:function(row){
			var $row = $(row);
			
			//初始化右键菜单
			initRespContextMenu($row);
			
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
	initRespContextMenu("#custSchemaTable tbody tr");
	
	//新增根节点
	$("#addCustSchemaRootNodeBtn").click(function(){
		var nodeObje = getTmpl("#custSchemaTmpl");
		$("#custSchemaTable").treegrid("addRootNode",nodeObje);
	});
}

//初始化自定义结构表格右键菜单
function initRespContextMenu(selector){
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
	$("#addRespBtn").click(function(){
		$("#custSchemaTable").treegrid("clear");
		$("#schemaTypeSelect").trigger("change");
		
		$("#operTypeId").val("add");
		
		//事例
		var example = '{\n\t"example": "type,description"\n}';
		$("#respExtSchemaArea").val(example);
	});
}

//初始化编辑操作
function initUpdateOper(id){
	$("#operTypeId").val("update");
	
	$("#respId").val(id);
	
	var param = new Object();
	param.respId = id;
	param.docId = $("#docId").val();
	doGet("auth/doc/inter/resp/json/info.htm",param,function(interResp){
		//加载之前先清空数据
		$("#custSchemaTable").treegrid("clear");
		
		//填充表单数据
		$("#respCustSchemaForm").find("*").setFieldsValue(interResp);
		
		//只有自定义结构才需要填充表格数据
		var schemaType = interResp.type;
		if(schemaType == "sys_object" || schemaType == "sys_array"){
			var rowList = JSON.parse(interResp.custSchema);
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
	var param = $("#respCustSchemaForm").find("*").getFieldsValue();
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
	
	doPost("auth/doc/inter/resp/json/add.htm",param,function(data){
		notice("保存成功",function(){
			$("#respSchemaFormModal").modal("hide");
			$("#respInfoTab").click();
		});
	});
}

//初始化删除操作
function initDelOper(id){
	bootbox.confirm("确定执行删除操作？",function(){
		var param = new Object();
		param.respId = id;
		param.docId = $("#docId").val();
		doGet("auth/doc/inter/resp/json/del.htm",param,function(){
			$("#respInfoTab").click();
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
	
	doPost("auth/doc/inter/resp/json/update.htm",param,function(data){
		notice("保存成功",function(){
			$('#respSchemaFormModal').modal('hide');
			$("#respInfoTab").click();
		});
	});
}

//初始化保存操作
function initSaveOper(){
	$("#respCustSchemaForm").bootstrapValidator({
		fields:{
			code:{
                validators: {
                    notEmpty: {
                        message: '编码不能为空'
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
	
	$("#saveBtn").click(function(){
		if(isFormValid("respCustSchemaForm")){
			if($("#schemaTypeSelect").val() == "cust_json" && !validJson($("#respExtSchemaArea").val())){
				notice("自定义json格式错误!");
				return false;
			}
			
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

//加载响应信息
function loadRespInfo(){
	var param = new Object();
	param.interId = $("#interId").val();
	param.docId = $("#docId").val();
	doGet("auth/doc/inter/resp/json/list.htm",param,function(paramList){
		var tableBody = $("#respInfoTable tbody");
		//先清空再加载新数据
		tableBody.empty();
		
		var row;
		var $row;
		var rowData;
		for(var i=0;i < paramList.length; i++){
			rowData = paramList[i];
			row = getTmpl("#respInfoTmpl")[0];
			$row = $(row);
			
			//设置序号
			$row.find("td:first-child").html(i + 1);
			tableBody.append(row);
			
			$row.find("*").setFieldsValue(rowData);
			
			//初始化更新操作
			$row.find(".oper-update").bind("click",rowData.id,function(e){
				initUpdateOper(e.data);
			});
			
			//初始化删除操作
			$row.find(".oper-del").bind("click",rowData.id,function(e){
				initDelOper(e.data);
			});
		}
	});
}