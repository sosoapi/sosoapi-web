$(function() {
	//初始化表格右键菜单
	initContextMenu("#contentTable tbody tr");
	
	$("#contentTable").treegrid({
		onSelected:function(row){
//			bootbox.alert($(row).treegrid("getNodeId"));
		},
		onAdded:function(row){
			initContextMenu($(row));
		},
		onRemoved:function(row){
			bootbox.alert(row);
		}
	});
	
	$("#addRootNodeBtn").click(function(){
		$("#contentTable").treegrid("addRootNode",getTmpl("#rowTmpl"));
		/*$("#contentTable").treegrid("addRootNode",getTmpl("#rowTmpl"),"custId");*/
	});
	
	$("#getDataBtn").click(function(){
		getData();
	});
	
	
	var rowList = [{"code":"name","name":"姓名","type":"sys_string","moduleId":"1","nodeId":"10","parentId":null},
	               {"code":"age","name":"年龄","type":"sys_integer_int32","moduleId":"1","nodeId":"11","parentId":null},
	               {"code":"address","name":"地址","type":"sys_object","moduleId":"1","nodeId":"12","parentId":null},
	               {"code":"province","name":"省份","type":"sys_string","moduleId":"1","nodeId":"1210","parentId":"12"},
	               {"code":"city","name":"城市","type":"sys_string","moduleId":"1","nodeId":"1211","parentId":"12"}];
	$("#loadDataBtn").click(function(){
		/*alert(JSON.stringify(rowList, null, 4));*/
		$("#contentTable").treegrid("clear");
		
		var rowData;
		var nodeObj;
		var nodeObjCache = new Object();
		for(var i = 0;i < rowList.length; i ++){
			nodeObj = getTmpl("#rowTmpl")
			rowData = rowList[i];
			if(isNull(rowData.parentId)){
				$("#contentTable").treegrid("addRootNode",nodeObj,rowData.nodeId);
			}
			else{
				nodeObjCache[rowData.parentId].treegrid("addChildNode",nodeObj,rowData.nodeId,rowData.parentId);
			}
			nodeObj.find("*").setFieldsValue(rowData);
			
			nodeObjCache[rowData.nodeId] = nodeObj;
		}
	});
});

//初始化右键菜单
function initContextMenu(selector){
	$(selector).contextPopup({
        items: [
			{
				label:'新增',     
				icon:'',             
				action:function(event,obj) { 
					$(obj).treegrid("addChildNode",getTmpl("#rowTmpl"));
					/*$(obj).treegrid("addChildNode",getTmpl("#rowTmpl"),1010,10);*/
				}
			},
            {
            	label:'删除',     
            	icon:'',             
            	action:function(event,obj) { 
            		$(obj).treegrid("removeNodes");
            	}
            },
            {
            	label:'展开',     
            	icon:'',             
            	action:function(event,obj) { 
            		$(obj).treegrid("expand");
            	}
            },
            {
            	label:'收缩',     
            	icon:'',             
            	action:function(event,obj) { 
            		$(obj).treegrid("collapse");
            	}
            }
        ]
    });
}

//获取表格数据
function getData(){
	var nodeArray = new Array();
	$("#contentTable").treegrid("getAllNodes").each(function(index,element){
		var $element = $(element);
		var rowData = $element.find("*").getFieldsValue();
		rowData.nodeId = $element.treegrid("getNodeId");
		rowData.parentId = $element.treegrid("getParentNodeId");
		
		nodeArray[index] = rowData;
	});
	
	$("#dataArea").val(JSON.stringify(nodeArray));
}
