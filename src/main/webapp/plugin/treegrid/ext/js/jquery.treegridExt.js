/*
 * jQuery treegrid Plugin 0.3.0
 * https://github.com/maxazan/jquery-treegrid
 *
 * Copyright 2013, Pomazan Max
 * Licensed under the MIT licenses.
 */
(function($) {
	/**
	 * 判断对象是否存在
	 */
	function isExist(obj){
		return !(obj === null || obj.length == 0);
	}
	
	/**
	 * 递归删除节点
	 */
	function removeNodeRecursive(obj){
    	var $this = $(obj);
    	var childNodes = $this.treegrid('getChildNodes');
        if (childNodes === null || childNodes.length == 0) {
            $this.remove();
        }
        else{
        	$.each(childNodes,function(i,node){
        		removeNodeRecursive(node);
        	});
        	$this.remove();
        }
    }
	
	/**
	 * 递归获取最后一个节点
	 */
	function getLastNodeRecursive(obj){
    	var $this = $(obj);
    	var lastChildNode = $this.treegrid('getLastChildNode');
    	if(!isExist(lastChildNode)){
    		return $this;
    	}
    	else{
    		return getLastNodeRecursive(lastChildNode[0]);
    	}
    }
	
	/**
	 * 获取新增的下一个root节点id
	 * @param nodeId 自定义id
	 */
	function getAddingRootNodeClass(gridObj,nodeId){
		if(nodeId != undefined){
			return createNodeClass(gridObj,nodeId);
		}
		else{
			var $this = $(gridObj);
	    	var rootNodes = $this.treegrid('getRootNodes');
	    	var maxId = $this.treegrid('getSetting','levelFirstNodeId') - 1;
	    	$.each(rootNodes,function(i,node){
	    		var currentId = parseInt($(node).treegrid('getNodeId'));
	    		if(currentId > maxId){
	    			maxId = currentId;
	    		}
	    	});
	    	
	    	return createNodeClass(gridObj,maxId + 1);
		}
    }
	
	/**
     * 获取新增的下一个子节点id
     * @param nodeId 自定义id
     */
    function getAddingChildNodeClass(nodeObj,nodeId){
    	if(nodeId != undefined){
    		return createNodeClass(nodeObj,nodeId);
    	}
    	else{
    		var $this = $(nodeObj);
        	var rootNodes = $this.treegrid('getChildNodes');
        	//当前节点id
        	var currentId = parseInt($this.treegrid('getNodeId'));
        	var levelFirstNodeId = parseInt($this.treegrid('getSetting','levelFirstNodeId'));
        	var levelStepNodeId = parseInt($this.treegrid('getSetting','levelStepNodeId'));
        	
        	var maxChildId = currentId * levelStepNodeId + levelFirstNodeId - 1;
        		
        	$.each(rootNodes,function(i,node){
        		var childId = parseInt($(node).treegrid('getNodeId'));
        		if(childId > maxChildId){
        			maxChildId = childId;
        		}
        	});
        	
        	return createNodeClass(nodeObj,maxChildId + 1);
    	}
    }
    
    /**
     * 创建节点class
     */
    function createNodeClass(nodeObj,nodeId){
    	return $(nodeObj).treegrid('getSetting', 'createNodeClass').apply(nodeObj, [nodeId]);
    }
    
    /**
     * 创建父节点class
     */
    function createParentNodeClass(nodeObj,nodeId){
    	return $(nodeObj).treegrid('getSetting', 'createParentNodeClass').apply(nodeObj, [nodeId]);
    }
    
    /**
     * Initialize tree
     *
     * @param {Object} options
     * @returns {Object[]}
     */
    function initTree(jqGridObjArray,options) {
        var settings = $.extend({}, $.fn.treegrid.defaults, options);
        return jqGridObjArray.each(function() {
            var $this = $(this);
            $this.treegrid('setTreeContainer', $(this));
            $this.treegrid('setSettings', settings);
            settings.getRootNodes.apply(this, [$(this)]).treegrid('initNode', settings);
            $this.treegrid('getRootNodes').treegrid('render');
            
            //创建行号
            $this.treegrid('createRowNumber');
        });
    }
    
    /**
     * Initialize node events
     *
     * @returns {Node}
     */
    function initEvents(nodeObj) {
        var $nodeObj = $(nodeObj);
        //Default behavior on collapse
        $nodeObj.on("collapse", function() {
            var $this = $(this);
            $this.removeClass('treegrid-expanded');
            $this.addClass('treegrid-collapsed');
        });
        //Default behavior on expand
        $nodeObj.on("expand", function() {
            var $this = $(this);
            $this.removeClass('treegrid-collapsed');
            $this.addClass('treegrid-expanded');
        });

        //Default behavior on click
        $nodeObj.on("click",function(){
        	$(this).trigger("selected");
        });
        
        return $nodeObj;
    }
    
    /**
     * 在initState之后执行
     */
    function initChangeEvent(nodeObj) {
    	var $nodeObj = $(nodeObj);
        //Save state on change
    	$nodeObj.on("change", function() {
            var $this = $(this);
            $this.treegrid('render');
            if ($this.treegrid('getSetting', 'saveState')) {
                saveState(this);
            }
        });
    	
        return $nodeObj;
    }
    
    /**
     * Initialize events from settings
     *
     * @returns {Node}
     */
    function initSettingsEvents(nodeObj) {
    	var $nodeObj = $(nodeObj);
        //Save state on change
    	$nodeObj.on("change", function() {
            var $this = $(this);
            if (typeof($this.treegrid('getSetting', 'onChange')) === "function") {
                $this.treegrid('getSetting', 'onChange').apply($this);
            }
        });
        //Default behavior on collapse
    	$nodeObj.on("collapse", function() {
            var $this = $(this);
            if (typeof($this.treegrid('getSetting', 'onCollapse')) === "function") {
                $this.treegrid('getSetting', 'onCollapse').apply($this);
            }
        });
        //Default behavior on expand
    	$nodeObj.on("expand", function() {
            var $this = $(this);
            if (typeof($this.treegrid('getSetting', 'onExpand')) === "function") {
                $this.treegrid('getSetting', 'onExpand').apply($this);
            }

        });
        //Default behavior on selected
    	$nodeObj.on("selected", function() {
            var $this = $(this);
            if (typeof($this.treegrid('getSetting', 'onSelected')) === "function") {
                $this.treegrid('getSetting', 'onSelected').apply($this,[$this]);
            }
        });
    	
    	//绑定添加
    	$nodeObj.on('added',function(event,trObj){
    		var $this = $(this);
    		//重新计算行号
    		$this.treegrid('createRowNumber');
    		
    		if (typeof($this.treegrid('getSetting', 'onAdded')) === "function") {
                $this.treegrid('getSetting', 'onAdded').apply($this,[trObj]);
            }
    	});
    	
        return $nodeObj;
    }
    
    /**
     * Initialize expander for node
     *
     * @returns {Node}
     */
    function initExpander(nodeObj) {
        var $this = $(nodeObj);
        var cell = $this.find('td').get($this.treegrid('getSetting', 'treeColumn'));
        var tpl = $this.treegrid('getSetting', 'expanderTemplate');
        var expander = $this.treegrid('getSetting', 'getExpander').apply(this);
        if (expander) {
            expander.remove();
        }
        $(tpl).prependTo(cell).click(function() {
        	toggle($(this).closest('tr'));
        });
        
        return $this;
    }
    
    /**
     * Initialize indent for node
     *
     * @returns {Node}
     */
    function initIndent(nodeObj) {
        var $this = $(nodeObj);
        $this.find('.treegrid-indent').remove();
        var tpl = $this.treegrid('getSetting', 'indentTemplate');
        var expander = $this.find('.treegrid-expander');
        var depth = $this.treegrid('getDepth');
        for (var i = 0; i < depth; i++) {
            $(tpl).insertBefore(expander);
        }
        return $this;
    }
    
    /**
     * Initialise state of node
     *
     * @returns {Node}
     */
    function initState(nodeObj) {
        var $this = $(nodeObj);
        if ($this.treegrid('getSetting', 'saveState') && !isFirstInit(nodeObj)) {
        	restoreState(nodeObj);
        } else {
            if ($this.treegrid('getSetting', 'initialState') === "expanded") {
                $this.treegrid('expand');
            } else {
                $this.treegrid('collapse');
            }
        }
        
        return $this;
    }
    
    /**
     * 初始化行号
     */
    function initRowNumber(nodeObj) {
        var $this = $(nodeObj);
        var expander = $this.find('.treegrid-expander');
        var tpl = $this.treegrid('getSetting', 'rowNumberTemplate');
        
        var rowNumber = $this.treegrid('getSetting', 'getRowNumber').apply(this);
        if(rowNumber){
        	rowNumber.remove();
        }
        $(tpl).insertAfter(expander);

        return $this;
    }
    
    /**
     * Return true if this tree was never been initialised
     *
     * @returns {Boolean}
     */
    function isFirstInit(nodeObj) {
        var tree = $(nodeObj).treegrid('getTreeContainer');
        if (tree.data('first_init') === undefined) {
            tree.data('first_init', $.cookie(tree.treegrid('getSetting', 'saveStateName')) === undefined);
        }
        return tree.data('first_init');
    }
    
    /**
     * Expand if collapsed, Collapse if expanded
     *
     * @returns {Node}
     */
    function toggle(nodeObj) {
        var $this = $(nodeObj);
        if ($this.treegrid('isExpanded')) {
            $this.treegrid('collapse');
        } else {
            $this.treegrid('expand');
        }
        return $this;
    }
    
    /**
     * Save state of current node
     *
     * @returns {Node}
     */
   function saveState(nodeObj) {
        var $this = $(nodeObj);
        if ($this.treegrid('getSetting', 'saveStateMethod') === 'cookie') {

            var stateArrayString = $.cookie($this.treegrid('getSetting', 'saveStateName')) || '';
            var stateArray = (stateArrayString === '' ? [] : stateArrayString.split(','));
            var nodeId = $this.treegrid('getNodeId');

            if ($this.treegrid('isExpanded')) {
                if ($.inArray(nodeId, stateArray) === -1) {
                    stateArray.push(nodeId);
                }
            } else if ($this.treegrid('isCollapsed')) {
                if ($.inArray(nodeId, stateArray) !== -1) {
                    stateArray.splice($.inArray(nodeId, stateArray), 1);
                }
            }
            $.cookie($this.treegrid('getSetting', 'saveStateName'), stateArray.join(','));
        }
        return $this;
    }
    
   /**
    * Restore state of current node.
    *
    * @returns {Node}
    */
   function restoreState(nodeObj) {
       var $this = $(nodeObj);
       if ($this.treegrid('getSetting', 'saveStateMethod') === 'cookie') {
           var stateArray = $.cookie($this.treegrid('getSetting', 'saveStateName')).split(',');
           if ($.inArray($this.treegrid('getNodeId'), stateArray) !== -1) {
               $this.treegrid('expand');
           } else {
               $this.treegrid('collapse');
           }

       }
       return $this;
   }
   
   /**
    * Collapse current node and all child nodes begin from current
    *
    * @returns {Node}
    */
   function collapseRecursive(jqNodeObjArray) {
       return jqNodeObjArray.each(function() {
           var $this = $(this);
           $this.treegrid('collapse');
           if (!$this.treegrid('isLeaf')) {
        	   collapseRecursive($this.treegrid('getChildNodes'));
           }
       });
   }
   
   /**
    * Return true if at least one of parent node is collapsed
    *
    * @returns {Boolean}
    */
   function isOneOfParentsCollapsed(jqNodeObj) {
       var $this = jqNodeObj;
       if ($this.treegrid('isRoot')) {
           return false;
       } 
       else {
           if ($this.treegrid('getParentNode').treegrid('isCollapsed')) {
               return true;
           } 
           else {
        	   return isOneOfParentsCollapsed($this.treegrid('getParentNode'));
           }
       }
   }
   
   /**
    * Expand current node and all child nodes begin from current
    *
    * @returns {Node}
    */
   function expandRecursive(jqNodeObjArray) {
       return jqNodeObjArray.each(function() {
           var $this = $(this);
           $this.treegrid('expand');
           if (!$this.treegrid('isLeaf')) {
        	   expandRecursive($this.treegrid('getChildNodes'));
           }
       });
   }
   
   /**
    * Rendering expander depends on node state
    *
    * @returns {Node}
    */
   function renderExpander(nodeObj) {
       return $(nodeObj).each(function() {
           var $this = $(this);
           var expander = $this.treegrid('getSetting', 'getExpander').apply(this);
           if (expander) {
               if (!$this.treegrid('isCollapsed')) {
                   expander.removeClass($this.treegrid('getSetting', 'expanderCollapsedClass'));
                   expander.addClass($this.treegrid('getSetting', 'expanderExpandedClass'));
               } else {
                   expander.removeClass($this.treegrid('getSetting', 'expanderExpandedClass'));
                   expander.addClass($this.treegrid('getSetting', 'expanderCollapsedClass'));
               }
           } else {
               initExpander(this);
               renderExpander(this);
           }
       });
   }
   
   var methods = {
        /**
         * Initialize node
         *
         * @param {Object} settings
         * @returns {Object[]}
         */
        initNode: function(settings) {
            return this.each(function() {
                var $this = $(this);
                $this.treegrid('setTreeContainer', settings.getTreeGridContainer.apply(this));
                $this.treegrid('getChildNodes').treegrid('initNode', settings);
                initExpander(this);
                initIndent(this);
                initEvents(this);
                initState(this);
                initChangeEvent(this);
                initSettingsEvents(this);
                initRowNumber(this);
            });
        },
        
        /**
         * Method return setting by name
         *
         * @param {type} name
         * @returns {unresolved}
         */
        getSetting: function(name) {
            if (!$(this).treegrid('getTreeContainer')) {
                return null;
            }
            
            if(typeof name != 'undefined'){
            	return $(this).treegrid('getTreeContainer').data('settings')[name];
            }
            else{
            	return $(this).treegrid('getTreeContainer').data('settings');
            }
        },
        
        /**
         * Add new settings
         *
         * @param {Object} settings
         */
        setSettings: function(settings) {
            $(this).treegrid('getTreeContainer').data('settings', settings);
        },
        
        /**
         * Return tree container
         *
         * @returns {HtmlElement}
         */
        getTreeContainer: function() {
            return $(this).data('treegrid');
        },
        
        /**
         * Set tree container
         *
         * @param {HtmlE;ement} container
         */
        setTreeContainer: function(container) {
            return $(this).data('treegrid', container);
        },
        
        /**
         * 创建序号
         */
        createRowNumber: function(){
        	var $this = $(this);
        	if($this.treegrid('getSetting','showRowNumber')){
        		$this.treegrid('getTreeContainer').find(".treegrid-row-number").each(function(index,element){
        			$(element).html(index + 1);
            	});
        	}
        },
        
        /**
         * Method return all root nodes of tree.
         *
         * Start init all child nodes from it.
         *
         * @returns {Array}
         */
        getRootNodes: function() {
            return $(this).treegrid('getSetting', 'getRootNodes').apply(this, [$(this).treegrid('getTreeContainer')]);
        },
        
        /**
    	 * 获取新增的下一个root节点id
    	 * @param nodeId 如果指定id则用指定的id
    	 */
        getAddingRootNodeClass: function(nodeId){
        	return getAddingRootNodeClass(this,nodeId);
        },
        
        /**
         * 获取新增的下一个子节点id
         * @param nodeId 自定义id
         */
        getAddingChildNodeClass: function(nodeId){
        	return getAddingChildNodeClass(this,nodeId);
        },
        
        /**
         * 添加根节点
         * @param nodeId 如果指定id则用指定的id
         */
        addRootNode: function($trObj,nodeId){
        	var $this = $(this);
        	$trObj.addClass(getAddingRootNodeClass(this,nodeId));
        	$this.treegrid('getTreeContainer').append($trObj);
        	$trObj.treegrid('initNode',$this.treegrid('getSetting'));
        	$trObj.treegrid('render');
        	
        	$trObj.trigger("added",$trObj[0]);
        },
        
        /**
         * 添加子节点
         * @param nodeId		指定当前id
         * @param parentNodeId	指定父id
         */
        addChildNode: function($trObj,nodeId,parentNodeId){
        	var $this = $(this);
        	if(parentNodeId == undefined){
        		parentNodeId = $this.treegrid('getNodeId');
        	}
        	var parentClass = createParentNodeClass(this,parentNodeId);
        	$trObj.addClass(getAddingChildNodeClass(this,nodeId));
        	$trObj.addClass(parentClass);
        	
        	var lastNode = $this.treegrid('getLastNode');
        	if(!isExist(lastNode)){
        		$trObj.insertAfter($this);
        	}
        	else{
        		$trObj.insertAfter(lastNode);
        	}
        	
        	$trObj.treegrid('initNode',$this.treegrid('getSetting'));
        	$trObj.treegrid('render');

        	$this.treegrid('render');
        	
        	$trObj.trigger('added',$trObj[0]);
        },
        
        /**
         * 删除子节点，包括当前节点
         */
        removeNodes: function(){
        	var $this = $(this);
        	var tree = $this.treegrid('getTreeContainer');
        	var parentNode = $this.treegrid('getParentNode');
        	//删除子节点
        	removeNodeRecursive(this);
        	//移除自身
        	$this.remove();
        	
        	if(parentNode){
        		//重新计算行号
            	parentNode.treegrid('createRowNumber');
        		
            	//父节点重新渲染
            	parentNode.treegrid('render');
        	}
        	else{
        		tree.treegrid('createRowNumber');
        	}
        },
        
        /**
         * 清空表
         */
        clear: function(){
        	$(this).treegrid('getAllNodes').remove();
        },
        
        /**
         * Method return all nodes of tree.
         *
         * @returns {Array}
         */
        getAllNodes: function() {
            return $(this).treegrid('getSetting', 'getAllNodes').apply(this, [$(this).treegrid('getTreeContainer')]);
        },
        
        /**
         * Mthod return id of node
         *
         * @returns {String}
         */
        getNodeId: function() {
            if ($(this).treegrid('getSetting', 'getNodeId') === null) {
                return null;
            } else {
                return $(this).treegrid('getSetting', 'getNodeId').apply(this);
            }
        },
        
        /**
         * Method return parent id of node or null if root node
         *
         * @returns {String}
         */
        getParentNodeId: function() {
            return $(this).treegrid('getSetting', 'getParentNodeId').apply(this);
        },
        
        /**
         * Method return parent node or null if root node
         *
         * @returns {Object[]}
         */
        getParentNode: function() {
            if ($(this).treegrid('getParentNodeId') === null) {
                return null;
            } else {
                return $(this).treegrid('getSetting', 'getNodeById').apply(this, [$(this).treegrid('getParentNodeId'), $(this).treegrid('getTreeContainer')]);
            }
        },
        
        /**
         * Method return array of child nodes or null if node is leaf
         *
         * @returns {Object[]}
         */
        getChildNodes: function() {
            return $(this).treegrid('getSetting', 'getChildNodes').apply(this, [$(this).treegrid('getNodeId'), $(this).treegrid('getTreeContainer')]);
        },
        
        /**
         * 获取最后一个子节点
         */
        getLastChildNode: function(){
        	return $(this).treegrid('getSetting', 'getLastChildNode').apply(this, [$(this).treegrid('getNodeId'), $(this).treegrid('getTreeContainer')]);
        },
        
        /**
         * 当前节点下最后一个后代节点(无限层次)
         */
        getLastNode: function(){
        	var $this = $(this);
        	var lastNode = getLastNodeRecursive(this);
        	
        	return lastNode == $this ? null : lastNode;
        },
        
        /**
         * Method return depth of tree.
         *
         * This method is needs for calculate indent
         *
         * @returns {Number}
         */
        getDepth: function() {
            if ($(this).treegrid('getParentNode') === null) {
                return 0;
            }
            return $(this).treegrid('getParentNode').treegrid('getDepth') + 1;
        },
        
        /**
         * Mthod return true if element is Node
         *
         * @returns {String}
         */
        isNode: function() {
            return $(this).treegrid('getNodeId') !== null;
        },
        
        /**
         * Method return true if node is root
         *
         * @returns {Boolean}
         */
        isRoot: function() {
            return $(this).treegrid('getDepth') === 0;
        },
        
        /**
         * Method return true if node has no child nodes
         *
         * @returns {Boolean}
         */
        isLeaf: function() {
            return $(this).treegrid('getChildNodes').length === 0;
        },
        
        /**
         * Method return true if node last in branch
         *
         * @returns {Boolean}
         */
        isLast: function() {
            if ($(this).treegrid('isNode')) {
                var parentNode = $(this).treegrid('getParentNode');
                if (parentNode === null) {
                    if ($(this).treegrid('getNodeId') === $(this).treegrid('getRootNodes').last().treegrid('getNodeId')) {
                        return true;
                    }
                } else {
                    if ($(this).treegrid('getNodeId') === parentNode.treegrid('getChildNodes').last().treegrid('getNodeId')) {
                        return true;
                    }
                }
            }
            return false;
        },
        
        /**
         * Method return true if node first in branch
         *
         * @returns {Boolean}
         */
        isFirst: function() {
            if ($(this).treegrid('isNode')) {
                var parentNode = $(this).treegrid('getParentNode');
                if (parentNode === null) {
                    if ($(this).treegrid('getNodeId') === $(this).treegrid('getRootNodes').first().treegrid('getNodeId')) {
                        return true;
                    }
                } else {
                    if ($(this).treegrid('getNodeId') === parentNode.treegrid('getChildNodes').first().treegrid('getNodeId')) {
                        return true;
                    }
                }
            }
            return false;
        },
        
        /**
         * Return true if node expanded
         *
         * @returns {Boolean}
         */
        isExpanded: function() {
            return $(this).hasClass('treegrid-expanded');
        },
        
        /**
         * Return true if node collapsed
         *
         * @returns {Boolean}
         */
        isCollapsed: function() {
            return $(this).hasClass('treegrid-collapsed');
        },
        
        /**
         * Expand node
         *
         * @returns {Node}
         */
        expand: function() {
            if (!this.treegrid('isLeaf') && !this.treegrid("isExpanded")) {
                this.trigger("expand");
                this.trigger("change");
                return this;
            }
            return this;
        },
        
        /**
         * Expand all nodes
         *
         * @returns {Node}
         */
        expandAll: function() {
            var $this = $(this);
            expandRecursive($this.treegrid('getRootNodes'));
            return $this;
        },
        
        /**
         * Collapse node
         *
         * @returns {Node}
         */
        collapse: function() {
            return $(this).each(function() {
                var $this = $(this);
                if (!$this.treegrid('isLeaf') && !$this.treegrid("isCollapsed")) {
                    $this.trigger("collapse");
                    $this.trigger("change");
                }
            });
        },
        
        /**
         * Collapse all nodes
         *
         * @returns {Node}
         */
        collapseAll: function() {
            var $this = $(this);
            collapseRecursive($this.treegrid('getRootNodes'));
            return $this;
        },
        
        /**
         * Rendering node
         *
         * @returns {Node}
         */
        render: function() {
            return $(this).each(function() {
                var $this = $(this);
                //if parent colapsed we hidden
                if (isOneOfParentsCollapsed($this)) {
                    $this.hide();
                } 
                else {
                    $this.show();
                }
                
                if (!$this.treegrid('isLeaf')) {
                	renderExpander(this);
                    $this.treegrid('getChildNodes').treegrid('render');
                }
                else{//处理空节点
                	var expander = $this.treegrid('getSetting', 'getExpander').apply(this);
                	if(isExist(expander)){
                		expander.removeClass($this.treegrid('getSetting', 'expanderCollapsedClass'));
                        expander.removeClass($this.treegrid('getSetting', 'expanderExpandedClass'));
                	}
                }
            });
        }
    };
    
    $.fn.treegrid = function(method) {
        if (methods[method]) {
            return methods[method].apply(this, Array.prototype.slice.call(arguments, 1));
        } 
        else if (typeof method === 'object' || !method) {
        	return initTree(this,arguments[0]);
        } 
        else {
            $.error('Method with name ' + method + ' does not exists for jQuery.treegrid');
        }
    };
    
    /**
     *  Plugin's default options
     */
    $.fn.treegrid.defaults = {
        initialState: 'expanded',
        saveState: false,
        saveStateMethod: 'cookie',
        saveStateName: 'tree-grid-state',
        expanderTemplate: '<span class="treegrid-expander"></span>',
        indentTemplate: '<span class="treegrid-indent"></span>',
        expanderExpandedClass: 'treegrid-expander-expanded',
        expanderCollapsedClass: 'treegrid-expander-collapsed',
        treeColumn: 0,
        
        rowNumberTemplate: '<span class="treegrid-row-number"></span>',
        
        //事例：
        //levelFirstNodeId:10
        //levelStepNodeId:100
        //10
        //	1010(10*100 + 10)
        //  1011(10*100 + 10 + 1)
        //  1012(10*100 + 10 + 2)
        //11
        //  1110(11*100 + 10)
        //  1111(11*100 + 10 + 1)
        //12
        //13
        
        //第一级节点开始编号，+1递增
        levelFirstNodeId: 10,
        //每级节点步长倍数
        levelStepNodeId: 100,
        
        //是否展示行号
        showRowNumber: false,
        
        getRowNumber: function() {
            return $(this).find('.treegrid-row-number');
        },
        
        getExpander: function() {
            return $(this).find('.treegrid-expander');
        },
        
        //创建节点id
        createNodeClass: function(code){
        	return "treegrid-" + code;
        },
        
        //创建父节点id
        createParentNodeClass: function(code){
        	return "treegrid-parent-" + code;
        },
        
        getNodeId: function() {
            var template = /treegrid-([A-Za-z0-9_-]+)/;
            if (template.test($(this).attr('class'))) {
                return template.exec($(this).attr('class'))[1];
            }
            return null;
        },
        
        getParentNodeId: function() {
            var template = /treegrid-parent-([A-Za-z0-9_-]+)/;
            if (template.test($(this).attr('class'))) {
                return template.exec($(this).attr('class'))[1];
            }
            return null;
        },
        
        getNodeById: function(id, treegridContainer) {
            var templateClass = "treegrid-" + id;
            return treegridContainer.find('tr.' + templateClass);
        },
        
        getChildNodes: function(id, treegridContainer) {
            var templateClass = "treegrid-parent-" + id;
            return treegridContainer.find('tr.' + templateClass);
        },
        
        /**
         * 获取最后一个子节点
         */
        getLastChildNode: function(id, treegridContainer){
        	 var templateClass = "treegrid-parent-" + id;
             return treegridContainer.find('tr.' + templateClass + ":last");
        },
        
        getTreeGridContainer: function() {
            return $(this).closest('table');
        },
        
        getRootNodes: function(treegridContainer) {
            var result = $.grep(treegridContainer.find('tr'), function(element) {
                var classNames = $(element).attr('class');
                var templateClass = /treegrid-([A-Za-z0-9_-]+)/;
                var templateParentClass = /treegrid-parent-([A-Za-z0-9_-]+)/;
                return templateClass.test(classNames) && !templateParentClass.test(classNames);
            });
            return $(result);
        },
        
        getAllNodes: function(treegridContainer) {
            var result = $.grep(treegridContainer.find('tr'), function(element) {
                var classNames = $(element).attr('class');
                var templateClass = /treegrid-([A-Za-z0-9_-]+)/;
                return templateClass.test(classNames);
            });
            return $(result);
        },
        
        //Events
        onCollapse: null,
        onExpand: null,
        onChange: null,
        onSelected: function(row){
        	
        },
        onAdded: function(row){
        	
        }
    };
})(jQuery);
