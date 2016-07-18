$(function() {
    var table = $('#contentTable').dataTable({
        initComplete:function(){

        }
    });
    
    var tableApi = table.api();
    
    $('#addRowBtn').on('click', function () {
    	//第一种方式:直接添加内容
    	/*tableApi.row.add([
            '',
            '<input type="text" value="" class="form-control">',
            '<input type="text" value="" class="form-control">',
            '<select class="form-control"><option value="">全部</option></select>',
            '',
            '<button class="btn btn-sm btn-primary" data-toggle="modal" data-target="#formModal"><i class="icon-pencil"></i> 编辑</button> <button class="btn btn-sm btn-danger oper-del"><i class="icon-trash"></i> 删除</button>'
            ]).draw();
 
    	$(".oper-del").closest('td').addClass('actions');*/
    	
    	//第二种方式:添加自定义html
    	tableApi.row.add($('<tr>' + 
    			'<td></td>' +
    			'<td><input type="text" value="" class="form-control"></td>' +
    			'<td><input type="text" value="" class="form-control"></td>' + 
    			'<td><select class="form-control"><option value="">全部</option></select></td>' +
    			'<td></td>' +
    			'<td class="actions"><button class="btn btn-sm btn-primary" data-toggle="modal" data-target="#formModal"><i class="icon-pencil"></i> 编辑</button> <button class="btn btn-sm btn-danger oper-del"><i class="icon-trash"></i> 删除</button></td>' +
    			'</tr>')[0]).draw();
    	
    	//第三种方式:通过table对象添加（推荐第二种替代）
    	/*table.custAddRow($('<tr>' + 
    			'<td></td>' +
    			'<td><input type="text" value="" class="form-control"></td>' +
    			'<td><input type="text" value="" class="form-control"></td>' + 
    			'<td><select class="form-control"><option value="">全部</option></select></td>' +
    			'<td></td>' +
    			'<td class="actions"><button class="btn btn-sm btn-primary" data-toggle="modal" data-target="#formModal"><i class="icon-pencil"></i> 编辑</button> <button class="btn btn-sm btn-danger oper-del"><i class="icon-trash"></i> 删除</button></td>' +
    			'</tr>')[0],true);*/
    	
    	//给新增数据的删除按钮绑定删除事件
    	tableApi.custInitRowDel(".oper-del",true);
    	
    	//初始化序号
    	tableApi.custInitRowIndex();
    });
    
    //给新增数据进行删除按钮初始化
    tableApi.custInitRowDel(".oper-del",true);
    
    $("#getDataBtn").click(function(){
    	var nodeArray = new Array();
    	$(tableApi.table().body()).find("tr").each(function(index,element){
    		var $element = $(element);
    		var rowData = $element.find("*").getFieldsValue();
    		
    		nodeArray[index] = rowData;
    	});
    	
    	bootbox.alert(JSON.stringify(nodeArray));
    });
});
