$(function() {
	//初始化请求参数表
	initReqParamTable();
	
	//格式化json操作
	initReqFormat();
	
	//初始化弹出框的确认btn
	initReqConfirmBtn();
	
	//初始化弹出框的确认btn
	initSaveMoreBtn();
});

//初始化请求数据类型下拉框
function initReqTypeSelect(row){
	var reqParamType = $(row).find(".req-param-type");
	//类型为"自定义"下拉框值
	var custJsonOpt = reqParamType.find("option[value='cust_json']");
	//类型为"引用"下拉框值
	var refOpt = reqParamType.find("option[value='sys_ref']");
	
	var reqPositionType = $(row).find(".req-param-position");
	reqPositionType.change(function(){
		var reqParamTypeValue = reqParamType.val();
		if($(this).val() == "body"){
			custJsonOpt.removeAttr("disabled");
			refOpt.removeAttr("disabled");
		}
		else{
			if(reqParamTypeValue == "cust_json" || reqParamTypeValue == "sys_ref"){
				reqParamType.val("sys_string");
				reqParamType.trigger("change");
			}
			custJsonOpt.attr("disabled","disabled");
			refOpt.attr("disabled","disabled");
		}
	});
	
	reqPositionType.trigger("change");
}

//格式化json操作
function initReqFormat(){
	$("#reqFormatSchemaBtn").click(function(){
		var extSchema = $("#reqExtSchemaArea").val();
		try{
			var jqObj = JSON.parse(extSchema);
			$("#reqExtSchemaArea").val(formatJson(jqObj));
		}
		catch(e){
			notice(e.message);
		}
	});
}

//初始化自定义扩展结构btn
function initExtSchema(row){
	var extSchemaBtn = $(row).find(".ext-schema-btn");
	var custRefSchema = $(row).find(".cust-ref-schema");
	
	extSchemaBtn.bind("click",row,function(e){
		//保存当前选中的行
		$("#reqExtSchemaModal").data("row",$(row));
		
		//填充
		var extSchema = $(row).find("[name='extSchema']").val();
		if(extSchema.length == 0){
			extSchema = '{\n\t"example": "type,description"\n}';
		}
		$("#reqExtSchemaArea").val(extSchema);
		
		$("#reqExtSchemaModal").modal("show");
	});
	
	//设置自定义扩展结构btn可用性
	var typeSelect = $(row).find(".req-param-type");
	$(row).find(".req-param-type").change(function(){
		var reqParamTypeValue = $(this).val();
		if(reqParamTypeValue == "cust_json"){
			extSchemaBtn.removeAttr("disabled");
		}
		else{
			extSchemaBtn.attr("disabled","disabled");
		}
		
		if(reqParamTypeValue == "sys_ref"){
			custRefSchema.removeAttr("disabled");
		}
		else{
			custRefSchema.attr("disabled","disabled");
		}
	});
	
	typeSelect.trigger("change");
}

//初始化更多btn
function initMoreBtn(row){
	var moreBtn = $(row).find(".more-btn");
	moreBtn.bind("click",row,function(e){
		$("#moreForm").resetForm();
		//保存当前选中的行
		$("#moreModal").data("row",$(row));
		
		//填充数据
		var rowData = $(row).find("*").getFieldsValue();
		$("#moreForm").find("*").setFieldsValue(rowData);
		
		$("#moreModal").modal("show");
	});
}

//初始化弹出框的确认btn
function initSaveMoreBtn(){
	$("#saveMoreBtn").click(function(){
		var $row = $("#moreModal").data("row");
		var moreData = $("#moreForm").find("*").getFieldsValue();
		$row.find("*").setFieldsValue(moreData);
		
		//清空
		$("#moreModal").data("row",null);
		$("#moreModal").modal("hide");
	});
}

//初始化弹出框的确认btn
function initReqConfirmBtn(){
	$("#reqConfirmBtn").click(function(){
		if(!validJson($("#reqExtSchemaArea").val())){
			notice("自定义json格式错误!");
			return false;
		}
		
		$("#reqExtSchemaModal").data("row").find("[name='extSchema']").val($("#reqExtSchemaArea").val());
		//清空
		$("#reqExtSchemaModal").data("row",null);
		
		$("#reqExtSchemaModal").modal("hide");
	});
}

//初始化请求参数表
function initReqParamTable(){
	var tableBody = $("#reqParamTable tbody");
    
	//新增请求参数
    $("#addReqParamBtn").on("click", function () {
    	var row = getTmpl("#reqParamTmpl")[0];
		tableBody.append(row);
    	
		initRowAfterAdded(row,true);
    });
    
    //保存请求参数
    $("#saveReqParamBtn").on("click",function(){
    	var rowArray = new Array();
    	tableBody.find("tr").each(function(index,element){
    		var $element = $(element);
    		var rowData = $element.find("*").getFieldsValue();
    		
    		rowArray[index] = rowData;
    	});
    	
    	var param = new Object();
    	param.interId = $("#interId").val();
    	param.docId = $("#docId").val();
    	param.reqParam = JSON.stringify(rowArray);
    	doPost("auth/doc/inter/param/json/add.htm",param,function(data){
    		notice("保存成功");
    		
    		//重新加载保存后的参数
    		loadReqInfo();
    	});
    });
}

//加载请求参数
function loadReqInfo(){
	var param = new Object();
	param.interId = $("#interId").val();
	param.docId = $("#docId").val();
	doGet("auth/doc/inter/param/json/list.htm",param,function(paramList){
		var tableBody = $("#reqParamTable tbody");
		tableBody.empty();
		
		var row;
		for(var i=0;i < paramList.length; i++){
			row = getTmpl("#reqParamTmpl")[0];
			
			tableBody.append(row);
			$(row).find("*").setFieldsValue(paramList[i]);
			
			initRowAfterAdded(row,false);
		}
		
		initRowIndex("#reqParamTable tbody");
	});
}

//初始化表格右键菜单
function initReqContextMenu(selector){
	$(selector).contextPopup({
        items: [
			{
				label:'上移',     
				clazz:'fa fa-angle-up context-color',
				action:function(event,row) { 
					var $row = $(row);
					var preRow = $row.prev();
					$row.after(preRow);
					initRowIndex("#reqParamTable tbody");
				}
			},
			{
				label:'下移',     
				clazz:'fa fa-angle-down context-color',
				action:function(event,row) { 
					var $row = $(row);
					var nextRow = $row.next();
					nextRow.after($row);
					initRowIndex("#reqParamTable tbody");
				}
			},
			null,
            {
            	label:'置顶',     
            	clazz:'fa fa-angle-double-up context-color',
            	action:function(event,row) { 
            		$("#reqParamTable tbody").prepend($(row));
            		initRowIndex("#reqParamTable tbody");
            	}
            },
            {
            	label:'置底',     
            	clazz:'fa fa-angle-double-down context-color',
            	action:function(event,row) { 
            		$("#reqParamTable tbody").append($(row));
            		initRowIndex("#reqParamTable tbody");
            	}
            },
            null,
            {
            	label:'插入',     
            	clazz:'fa fa-plus context-color',
            	action:function(event,row) { 
            		var rowAdded = getTmpl("#reqParamTmpl")[0];
            		$(row).after(rowAdded);
            		initRowAfterAdded(rowAdded,true);
            	},
            	isEnabled:function(row){
            		return true;
            	}
            },
            {
            	label:'删除',     
            	clazz:'fa fa-remove context-color',
            	action:function(event,row) { 
            		$(row).remove();
            		initRowIndex("#reqParamTable tbody");
            	}
            }
        ]
    });
}

//重新计算序号
function initRowIndex(selector){
	$(selector).find("tr td:first-child").each(function(index,element){
		$(element).html(index + 1);
	});
}

//初始化新增的row
function initRowAfterAdded(row,initIndex){
	//初始化自定义扩展结构btn
	initExtSchema(row);
	
	//初始化更多btn
	initMoreBtn(row);
	
	//初始化请求数据类型下拉框
	initReqTypeSelect(row);
	
	//初始化右键菜单
	initReqContextMenu(row);
	
	//重新初始化序号
	if(initIndex){
		//初始化序号
		initRowIndex("#reqParamTable tbody");
	}
}