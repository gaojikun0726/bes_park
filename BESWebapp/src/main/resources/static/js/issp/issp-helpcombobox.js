/** 
* *********  操作实例  ************** 
*   页面上定义div,id自定义 例： <div id="seldiv">
*   $("#seldiv").ISSPHelpComboBox({
*			title:'设备类型',
*			tabName:'',//一级时必传参数
*			disCol:'',//一级时必传参数y
*			disColId :'',//一级时必传参数
*			isMultistage:true,//true ：多级显示,false ：多级显示
*			searchDisCxt:'',//请输入设备类型
*			getDataFun:'',//请求数据需执行的方法名(多级时必要参数)
*			conColumn:'',//下拉列表检索条件(表列名、多列用逗号隔开)
			conValue:'',//条件对应的值(列对应的值,一列对应多个值时,传数组格式的值)
			isDeVal:false,//初次加载页面时是否设置默认值(true:不加载,false:加载)
*			data:list,//getDataFun执行成功后传入的数据，格式为tree-view所需格式(多级时必要参数)
*			callBacks:'',//选中弹框元素后需执行的方法名(多级时必要参数)
*	});
*	传数据时如下：
*	(位置:getDataFun请求函数success内)
*		$("#seldiv").setComboBoxData({
*			data:result.list,//自定义函数获取的数据
*		});
*	
*	页面自定义函数 (例如函数名:test)
*		callBacks传参是 test
*		自定义函数
*		function test(node){
*			//该node为点击确定按钮后返回的选中节点(node),用户未选择直接点击确定则返回的node=''
*		}
*	根据id清除文本框内容
*	var currInput = $("#seldiv").clearComboxText("seldiv");//清除该文本框的值，并返回整个文本框(currInput)
*/
var callBackMap_bak = new Map();//页面切换时,声明全局Map做备份
;(function($, window, document, undefined) {
	var pluginName = 'ISSPHelpComboBox';
	var webroot = getRootPath();
	var issp_combobox_UUID = null;
	var callBacks = '';//自定义函数
	var cachedata = '';
	var comboxWidth = '';
	var title = '';
	var comboxHeight = '';
	var searchDisCxt = '';
	var isMultistage = false;
	var subProMap = new Map();//弹框属性Map
	var callBackMap = new Map();
	var uid = '';//初始uuid
	var currComId ='';//当前选中组件的ID
	var conColumn = '';
	var conValue = '';
	var isDeVal = false;//是否设置默认值
	var dataFun ='';
	var isDeValMap = new Map();
	var dataFunMap = new Map();
	var that = '';
	var isCreatModal = true;
	var compareData = '';
	var comboxInputKeyDown = false;
	var HelpComboBox = function(ele, opt) {
		combobox = this;
		this.$element = ele, 
		this.defaults = {
			'title' : '帮助', // 弹框标题
			'searchDisCxt' : '搜索',// 检索框提示信息
			'inputWidth' : '9vw',// input文本框宽度
			'inputHeight' : '2.9vh',// input文本框高度  vh（可收缩单位）
			'inputPromMsg' : '请选择...',// input文本框提示信息
			'comboxWidth' : '19vw',// 弹框宽度
			'comboxHeight' : '75vh',// 弹框高度
			'isMultistage' : false,// 是否是多级列表(false:一级,true:多级)
			'tabName' : '',// 数据源表名(不分级显示)
			'disColId' : '',// 数据源表列对应ID(不分级必选参数)
			'disCol' : '', // 数据源表列名(不分级显示)
			'conColumn':'',//下拉列表检索条件(表列名、多列用逗号隔开)
			'conValue':'',//条件对应的值(列对应的值,一列对应多个值时,传数组格式的值)
			'isDeVal':false,//是否设置默认值
			'data' : '',// 数据源(格式和树格式一样,分级显示)
			'inputEditable' : false,// 输入框默认不可编辑
			'treelevels':2,// 组件数据默认展开的级数(默认展开两级)
			'getDataFun' : '',// 请求弹框数据需执行方法
			'callBacks' : '', // 传递自定义的确定(双击选中)按钮点击事件(自定义函数名)
		}, 
		this.options = $.extend({}, this.defaults, opt);
	};
	
	/**
	 * 初始化(在页面创建检索文本框和检索按钮)
	 */
	HelpComboBox.prototype.initComboBox = function(options) {
		var keySet = callBackMap.keySet(); 
		for(var i in keySet){ 
			callBackMap_bak.put(keySet[i], callBackMap.get(keySet[i]))
		}
		//UUID切换之前备份全局Map
		issp_combobox_UUID = guid.newGUID();
		if(uid==''){
			uid = issp_combobox_UUID;
		}
		callBacks = this.options.callBacks;
		callBackMap.put(issp_combobox_UUID, callBacks);
		comboxWidth = this.options.comboxWidth;
		subProMap.put(this.$element[0].id+'comboxWidth', comboxWidth);
		title = this.options.title;
		subProMap.put(this.$element[0].id+'title', title);
		comboxHeight = this.options.comboxHeight;
		subProMap.put(this.$element[0].id+'comboxHeight', comboxHeight);
		searchDisCxt = this.options.searchDisCxt;
		subProMap.put(this.$element[0].id+'searchDisCxt', searchDisCxt);
		isMultistage = this.options.isMultistage;
		conColumn = this.options.conColumn;
		conValue = this.options.conValue;
		isDeVal = this.options.isDeVal;
		dataFun = this.options.getDataFun;
		isDeValMap.put(issp_combobox_UUID+'isDeVal', isDeVal);
		dataFunMap.put(issp_combobox_UUID+'dataFun', dataFun);
		var comHe = this.options.inputHeight;
		var div = $('<div class="display_flex div_color" style="white-space:nowrap;overflow:hidden;width: '+ this.options.inputWidth+' ;height: '+ comHe +' ;border-radius: 4px;background: #acd9ff;"></div>');
		$('#' + this.$element[0].id).append(div);
		var m = this.options.inputEditable ? '': 'readonly = true';
		var input = $('<input type="text" id="comboxInput_'+issp_combobox_UUID+'" class="combox-input"'+m+' style="height:'+ comHe+';width:calc(100% - '+ comHe +');" placeholder="'+this.options.inputPromMsg+'">');
		var btn =$('<button type="button" id="comboxBtn_'+issp_combobox_UUID+'" class="combox-btn" style="height:'+ comHe +';width:'+ comHe +';font-size: 3vh;" data-toggle="modal" data-target="#ispp-help-combobox'+issp_combobox_UUID+'"></button>');
		var hiddeninput1 =$('<input id="leveBak_'+issp_combobox_UUID+'" value="'+this.options.treelevels+'" type="hidden">');
		var hiddeninput2 =$('<input id="inputIdBak_'+issp_combobox_UUID+'" type="hidden">');
		var hiddeninput3 =$('<input id="comProsBak_'+issp_combobox_UUID+'" type="hidden">');
		div.append(input).append(btn).after(hiddeninput1).after(hiddeninput2).after(hiddeninput3);
		$("#comboxBtn_"+issp_combobox_UUID).on('click', $.proxy(combobox._comboxBtnclickHandler, combobox));
		$("#comboxInput_"+issp_combobox_UUID).on('keydown', $.proxy(combobox._inputKeydownHandler, combobox));
		$("#comboxInput_"+issp_combobox_UUID).on('blur', $.proxy(combobox._inputMouseleaveHandler, combobox));
		$("#inputIdBak_" + uid).val($("#inputIdBak_" + uid).val()+'~'+this.$element[0].id+'~'+issp_combobox_UUID);
		$("#comProsBak_" + uid).val($("#comProsBak_" + uid).val()+'#'+this.$element[0].id+'~'+'comWid@'+comboxWidth+'@tit@'+title+'@comHe@'+comboxHeight+'@seDis@'+searchDisCxt);
		if (isDeVal) {//帮助框初次加载默认值时，加载获取值方法
			if (!this.options.getDataFun == '') {
				dataFun();
			}
		}
	}
	
	/**
	 * 创建模态框框架
	 */
	HelpComboBox.prototype._createComboBoxFrame = function(datas) {
		if ($("#ispp-help-combobox" + issp_combobox_UUID).length > 0) {
			$("#ispp-help-combobox" + issp_combobox_UUID).remove();
		}
		// 获取对应弹框的属性--start--(多个页面切换时,只将属性放入map中会有问题)
		currComId = combobox.$element[0].id;
		var comPros = $("input[id^=comProsBak_]");
		var comProsStr='';
		for(var i = 0;i<comPros.length;i++){
			if(comPros[i].value!=''){
				comProsStr = comProsStr+comPros[i].value;
			}
		}
		var comboxPros = comProsStr.substring(1, comProsStr.length).split('#');
		var comprstr = '';
		for(var i = 0;i<comboxPros.length;i++){
			if(comboxPros[i].split('~')[0]==currComId){
				comprstr = comboxPros[i].split('~')[1];
				break;
			}
		}
		var comps = comprstr.split('@');
		// 获取对应弹框的属性--end--
		var outFrame= $('<div class="modal fade" id="ispp-help-combobox'+issp_combobox_UUID+'" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false"></div>');
		var comwidth = subProMap.get(currComId+'comboxWidth') == null ? comps[1]:subProMap.get(currComId+'comboxWidth');
		var childFrame = $('<div class="modal-dialog" style="width:'+ comwidth +'"></div>');//comboxWidth
		var frameContent = $('<div id="frameContent"style="width:100%;height:100%;" class="modal-content"></div>');
		var tit = subProMap.get(currComId+'title') == null ? comps[3]:subProMap.get(currComId+'title');
		var contentHeader = '<div class="modal-header bg-primary"><button id="closeBtn_'+issp_combobox_UUID+'" aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button><h4 class="modal-title"><i class="fa fa-lightbulb-o" aria-hidden="true" ></i> &nbsp;'+tit+'</h4></div>';
		var searchCxt = subProMap.get(currComId+'searchDisCxt')==null ? comps[7]:subProMap.get(currComId+'searchDisCxt');
		var searchBody = '<span style="width:100%"><input type="text" id="searchCxt_'+issp_combobox_UUID+'" class="combox-input inputHeight" name="searchCxt" placeholder="'+ searchCxt +'" style="width:49%;height:31px;margin-left: 8%;margin-top: 2%;"><button id="queryBtn_'+issp_combobox_UUID+'" style="width: 15%;margin-left:8px" class="btn btn-default">检索</button><button type="button" id="resetBtn_'+issp_combobox_UUID+'" style="width: 15%;margin-left: 3px;" class="btn btn-default">重置</button></span>';
		var comheight = subProMap.get(currComId+'comboxHeight')==null ? comps[5]:subProMap.get(currComId+'comboxHeight');
		var contentBody = '<div id="modal-body" class="modal-body" style="overflow-y: auto;height:'+ comheight +';border:1px solid rgba(125, 153, 162, 0.37);width: 82%;margin-left: 8%;"><div id="comboboxtree_'+issp_combobox_UUID+'"></div></div>';
		var contentFooter = '<div class="modal-footer"><span style="width:100%;display: flex;align-items: center;justify-content: center;"><div style="width:4vw;margin-left:7vw;display: flex;align-items: center;justify-content: center;" id="errMsg_'+issp_combobox_UUID+'"></div><button type="button" id="confirmBtn_'+issp_combobox_UUID+'" style="width:24%;margin-left:0.5vw;" class="btn btn-default"><i class="fa fa-check" aria-hidden="true"></i>&nbsp;确定</button></span></div>';
		frameContent.append(contentHeader).append(searchBody).append(contentBody).append(contentFooter);
		childFrame.append(frameContent);
		outFrame.append(childFrame);
		$(document.body).append(outFrame);
		$("#queryBtn_"+issp_combobox_UUID).on('click', $.proxy(combobox._queryBtnclickHandler, combobox));
		$("#resetBtn_"+issp_combobox_UUID).on('click', $.proxy(combobox._resetBtnclickHandler, combobox));
		$("#confirmBtn_"+issp_combobox_UUID).on('click', $.proxy(combobox._confirmBtnclickHandler, combobox));
		$("#closeBtn_"+issp_combobox_UUID).on('click', $.proxy(combobox._colseBtnclickHandler, combobox));
		$("#comboboxtree_"+issp_combobox_UUID).on('dblclick', $.proxy(combobox._confirmBtnclickHandler, combobox));//添加双击事件监听(同确定按钮)
		$("#searchCxt_"+issp_combobox_UUID).on('keydown', $.proxy(combobox._searchInputKeydownHandler, combobox));//检索框绑定按键事件
//		$("#searchCxt_"+issp_combobox_UUID).on('input porpertychange', $.proxy(combobox._searchInputChangeHandler, combobox));//检索框内容变更事件
	}
	
	/**
	 * 创建模态框
     *  this对象不一定是当前对象 具体看一下_comboxBtnclickHandler()这个方法that的赋值
	 */
	HelpComboBox.prototype.bulidComboBox = function(datas, initLoad) {
		combobox._createComboBoxFrame(datas);
		if (initLoad) { //初始化时需设置默认选中第一个
			isDeValMap.put(issp_combobox_UUID + 'isDeVal', false);//将初始化flg设置为false
			if (datas.length > 0) {
				$("#comboxInput_" + issp_combobox_UUID).val(datas[0].text);
			}
			var lev = $("#leveBak_" + issp_combobox_UUID).val();
			var com = combobox._filterTreeData(datas,lev, false);
			var allnodes = $('#comboboxtree_' + issp_combobox_UUID).treeview('getNodes');
			var firstNode = allnodes.shift();//获取第一个节点(数组形式)
			var defaultNode = new Array();
			defaultNode.push(firstNode);
			try {
				return com; //将该组件返回
			} catch (err) {
			} finally {
				setTimeout(function(){ //执行默认选中事件(等待创建的该组件返回后执行)
					if (callBackMap.get(issp_combobox_UUID) != null && callBackMap.get(issp_combobox_UUID) != "" && typeof (firstNode) != "undefined") {
						callBackMap.get(issp_combobox_UUID)(defaultNode);
					}
				}, 1);
			}
		}
		if (that != '' && !that.options.getDataFun) {// 通过表名加载数据
			if (that.options.tabName == '' || that.options.disCol == '' || that.options.disColId == '') {
				alert('请补全参数');
				return;
			}
			var cColumns = that.options.conColumn;
			if(cColumns instanceof Array){
				cColumns = "'"+cColumns.join("','")+"'";
			}
			var cValues = that.options.conValue;
			if(cValues instanceof Array){
				cValues = "'"+cValues.join("','")+"'";
			}
			$.ajax({
				url : webroot + '/view/isspplugins/helpcomboxData',
				type : "post",
				data : {
					tabName : that.options.tabName,
					disCol : that.options.disCol,
					disColId : that.options.disColId,
					conColumn : cColumns,
					conValue : cValues,
				},
				success : function(result) {
					$("#searchCxt_" + issp_combobox_UUID).val($("#comboxInput_" + issp_combobox_UUID).val());
					var lev = $("#leveBak_" + issp_combobox_UUID).val();
					var lel = $.trim($("#comboxInput_" + issp_combobox_UUID).val())=='' ? lev : 4;
					return combobox._filterTreeData(result.list, lel, true);
				}
			});
		} else {
			// 分级列表
			if (datas != null || datas != "") {
				$("#searchCxt_" + issp_combobox_UUID).val($("#comboxInput_" + issp_combobox_UUID).val());
				var lev = $("#leveBak_" + issp_combobox_UUID).val();
				var lel = $.trim($("#comboxInput_" + issp_combobox_UUID).val())=='' ? lev : 4;
				return combobox._filterTreeData(datas, lel, true);
			}
		}
	}
	
	/**
	 * 模态框填充数据
	 * remark: 解决要填充的数据为空时，点击检索按钮会把上一个帮助框的数据加载进来
	 */
	HelpComboBox.prototype._fillData = function (currdata, lev) {
		cachedata = currdata;
		lev = (lev == null || lev == '') ? 4 : lev;
		if(cachedata != null && cachedata!='' && cachedata.length>0 && cachedata[0].hasOwnProperty('nodes')){
			combobox._fixNode(cachedata);
		}
		// 填充数据
		return $('#comboboxtree_' + issp_combobox_UUID).treeview({
			data : cachedata, // 数据源
			highlightSelected : true, // 是否高亮选中
			levels : lev,
			enableLinks : true,// 必须在节点属性给出href属性
			color : "#4a4747",
			onNodeSelected : function(event,nodeData) {
				$('#searchCxt_'+issp_combobox_UUID).val(nodeData.text);
			}
		});
	}
	
	/**
	 * 修正node填充时有时出现$el属性导致数据填充失败问题
	 */
	HelpComboBox.prototype._fixNode = function (node) {
		if(node == null || node == ""){
			return;//退出函数
		}
		if(node instanceof Array ){
			node = node[0];
		}
		var nodeChilds = node.nodes;
		if(nodeChilds != null && nodeChilds.length > 0){
			for(var i = 0; i<nodeChilds.length; i++){
				var $this = nodeChilds[i];
				if($this.$el){
					delete $this.$el;
				}
				if(nodeChilds[i].hasOwnProperty("nodes")){
					combobox._fixNode(nodeChilds[i]);
				}
			}
		}
	};
	
	/**
	 * 过滤掉不匹配节点
	 * txt 过滤文本
	 */
	HelpComboBox.prototype._nodeFilter = function (txt) {
		if ($.trim(txt) == "") {
			$('#ispp-help-combobox' + issp_combobox_UUID).modal('show');
			return;
		}
		var fNode = $('#comboboxtree_' + issp_combobox_UUID).treeview('findNodes', [ txt, 'text' ]);
		// 存储需要保留的节点(key:节点自动生成的data-nodeid,value:节点text)
		var saveMap = new Map();
		// 需要删除的节点集合
		var delArray = new Array();
		if (fNode.length >= 1) { //数据中有数据匹配
			for (var i = 0; i < fNode.length; i++) {
				saveMap.put(fNode[i].$el[0].attributes[2].nodeValue, fNode[i].text);// 保存检索出的匹配节点
				combobox._getSaveNode(fNode[i], saveMap);// 保存匹配节点的多个父节点
			}
			// 获取所有节点
			var allnodes = $('#comboboxtree_' + issp_combobox_UUID).treeview('getNodes');
			var currNode;// 当前节点
			for (var i = 0; i < allnodes.length; i++) {
				currNode = allnodes[i];
				if (saveMap.get(currNode.$el[0].attributes[2].nodeValue) == null) {
					delArray.push(currNode);
				}
			}
		}else if(fNode.length == 0 && $('body').hasClass('modal-open')){//弹框检索内容没有匹配的数据(弹出检索框)
			delArray = $('#comboboxtree_' + issp_combobox_UUID).treeview('getNodes');
		}else if(fNode.length == 0 && !$('body').hasClass('modal-open')){//弹框检索内容没有匹配的数据(未弹出检索框)
			$("#searchCxt_"+issp_combobox_UUID).val('');
		}
		// 调用删除的两种方式
		// $('#comboboxtree_').treeview(true).removeNode(delArray);
		$('#comboboxtree_' + issp_combobox_UUID).data('treeview').removeNode(delArray);
	}
	
	/**
	 * 存储需保存的所有节点
	 * map(key:节点自动生成的data-nodeid,value:节点text)
	 */
	HelpComboBox.prototype._getSaveNode = function (node,map) {
		var pNodes = $('#comboboxtree_' + issp_combobox_UUID).treeview('getParents', node);
		if(pNodes.length <= 0){
			return;
		}
		if (pNodes[0].level == 1) {
			map.put(pNodes[0].$el[0].attributes[2].nodeValue, pNodes[0].text);
		} else {
			map.put(pNodes[0].$el[0].attributes[2].nodeValue, pNodes[0].text);
			combobox._getSaveNode(pNodes, map);
		}
	}

	/**
	 * 文本框绑定键盘事件
	 */
	HelpComboBox.prototype._inputKeydownHandler = function (event,options) {
		var keySet = callBackMap.keySet(); 
		for(var i in keySet){ 
			callBackMap_bak.put(keySet[i], callBackMap.get(keySet[i]))
		}
		//UUID切换之前,备份全局Map
		issp_combobox_UUID = event.currentTarget.id.split("_")[1];
		if(event.keyCode == "13") {//按下回车键
			comboxInputKeyDown = true;
			$("#comboxBtn_"+issp_combobox_UUID).trigger("click"); //模拟点击筛选按钮
		}else
			comboxInputKeyDown = false;
	};
	
	/**
	 * 文本框输入检索条件失去焦点时
	 */
	HelpComboBox.prototype._inputMouseleaveHandler = function (event,options) {
		setTimeout(function(){ //等待一下执行，解决文本框失去焦点和点击按钮不需清除文本的冲突问题
			combobox._inputMouseleaveHandlerProcess(event,options);
		}, 300);
	}
	/**
	 * 文本框输入检索条件失去焦点时(处理)
	 */
	HelpComboBox.prototype._inputMouseleaveHandlerProcess = function (event,options) {
		if(comboxInputKeyDown || callBackMap.get(issp_combobox_UUID) == null ||$("#comboxBtn_"+ issp_combobox_UUID).attr('clicked')){
			comboxInputKeyDown = false;
			return;
		}
		if($("#comboxInput_" + issp_combobox_UUID).val().trim()=='')
			return;
		isCreatModal = false;
		var getDataFun = dataFunMap.get(issp_combobox_UUID+'dataFun');
		if (!getDataFun == ''){
			getDataFun();
		}else{
			isCreatModal = true;
		}
	};
	
	/**
	 * 文本框输入检索条件失去焦点后
	 */
	HelpComboBox.prototype._inputMouseleaveAfterHandler = function (data) {
		var arr = new Array();
		combobox._findTextMatchNode(data, $("#comboxInput_" + issp_combobox_UUID).val(),arr);
		var currSelectedNode = new Array();
		if(arr.length>0){
			var node = arr[0];
			node['clickStatus'] = 'mouseLeave';
			currSelectedNode.push(node);
		}else{
			currSelectedNode.push({id:'', text:'', clickStatus:'mouseLeave'});
			$("#comboxInput_" + issp_combobox_UUID).val('');
		}
		try {
			return combobox.returnComboBox(data); //将该组件返回
		} catch (err) {
		} finally {
			setTimeout(function(){ //执行默认选中事件(等待创建的该组件返回后执行)
				if (callBackMap.get(issp_combobox_UUID) != null && callBackMap.get(issp_combobox_UUID) != "") {
					callBackMap.get(issp_combobox_UUID)(currSelectedNode);
				}else{
					if (callBackMap_bak.get(issp_combobox_UUID) != null && callBackMap_bak.get(issp_combobox_UUID) != "")
						callBackMap_bak.get(issp_combobox_UUID)(currSelectedNode);
				}
			}, 1);
		}
	};
	
	/**
	 * 未创建弹框,有匹配节点返回时,返回当前树以便于前端页面取子节点方法的调用
	 */
	HelpComboBox.prototype.returnComboBox = function(datas) {
		combobox._createComboBoxFrame(datas);
		var lev = $("#leveBak_" + issp_combobox_UUID).val();
		return combobox._filterTreeData(datas,lev, false);
	}
	
	/**
	 * 查找文本内容相匹配的节点
	 */
	HelpComboBox.prototype._findTextMatchNode = function (node, text, arrList) {
		if(node == null || node == ""){
			return;//退出函数
		}
		if(node instanceof Array ){
			node = node[0];
		}
		if(node.text == text){
			arrList.push(node);
			return;//退出函数
		}
		var nodeChilds = node.nodes;
		if(nodeChilds != null && nodeChilds.length > 0){
			for(var i = 0; i<nodeChilds.length; i++){
				if(nodeChilds[i].text == text ){
					arrList.push(nodeChilds[i]);
					return;//结束循环,退出整个函数
				}
				if(nodeChilds[i].hasOwnProperty("nodes")){
					combobox._findTextMatchNode(nodeChilds[i], text, arrList);
				}
			}
		}
	};
	
	/**
	 * 检索文本框绑定键盘事件
	 */
	HelpComboBox.prototype._searchInputKeydownHandler = function (event,options) {
		if(event.keyCode == "13") {//按下回车键
			$("#queryBtn_"+issp_combobox_UUID).trigger("click"); //模拟点击检索按钮
		}
	};
	
	/**
	 * 帮助按钮
	 */
	HelpComboBox.prototype._comboxBtnclickHandler = function (event,options) {
		$("#"+event.currentTarget.id).attr("disabled", true);//防止按钮双击时的bug
		$("#comboxBtn_"+ issp_combobox_UUID).attr('clicked','true');
		setTimeout(function(){ 
			$("#"+event.currentTarget.id).removeAttr("disabled");
			$("#comboxBtn_"+issp_combobox_UUID).removeAttr("disabled");
			$("#comboxBtn_"+ issp_combobox_UUID).removeAttr('clicked');
		},2000);
        that = this;
		currComId = event.currentTarget.parentNode.parentNode.id; //当前选中组件id
		//删除之前的模态框
		if ($("#ispp-help-combobox" + issp_combobox_UUID).length > 0) {
			$("#ispp-help-combobox" + issp_combobox_UUID).remove();
		}
		var keySet = callBackMap.keySet(); 
		for(var i in keySet){ 
			callBackMap_bak.put(keySet[i], callBackMap.get(keySet[i]))
		}
		//UUID切换之前,备份全局Map
		issp_combobox_UUID = event.currentTarget.id.split("_")[1];
		//删除相同uuid的模态框，这里跟上面不冲突，不是多余的
		if ($("#ispp-help-combobox" + issp_combobox_UUID).length > 0) {
			$("#ispp-help-combobox" + issp_combobox_UUID).remove();
		}
		var getDataFun = this.options.getDataFun;
		if (!this.options.getDataFun == '') {
			getDataFun();
		}
		// 不分级显示
		if (!this.options.isMultistage) {
			combobox.bulidComboBox('', false);
		}
	};
	
	/**
	 * 根据id清除文本内容
	 */
	HelpComboBox.prototype._clearComboxText = function(id){
		var idBak = $("input[id^=inputIdBak_]");
		var idStr='';
		for(var i = 0;i<idBak.length;i++){
			if(idBak[i].value!=''){
				idStr=idStr+idBak[i].value;
			}
		}
		var index = idStr.lastIndexOf(id);
		//根据传的id获取对应的uuid
		var uuid = idStr.substring(index, index + id.length+37).split('~')[1];
		return $("#comboxInput_" +uuid).val('');//清空输入框值
	};
	
	/**
	 * 填充数据并根据检索框文本过滤
	 */
	HelpComboBox.prototype._filterTreeData = function (treeData, treeLev, isShow) {
		var searchText = $("#searchCxt_"+issp_combobox_UUID).val();
		var currTree = combobox._fillData(treeData, treeLev);//填充数据
		if(searchText != null && searchText != ""){
			//过滤
			combobox._nodeFilter(searchText);
		}
		if (isShow) {
			$('#ispp-help-combobox' + issp_combobox_UUID).modal('show');
		} else { //加载默认值时(文本框创建,并不显示)
			$('#ispp-help-combobox' + issp_combobox_UUID).modal('hide');
		}
		return currTree;
	}
	
	/**
	 * 检索按钮
	 */
	HelpComboBox.prototype._queryBtnclickHandler = function (event) {
		var currLev = $("#leveBak_" + issp_combobox_UUID).val();
		var lel = currLev > 4 ? currLev : 4;
		combobox._filterTreeData(cachedata, lel, true);
	};
	
	/**
	 * 重置按钮
	 */
	HelpComboBox.prototype._resetBtnclickHandler = function (event) {
		var currLev = $("#leveBak_" + issp_combobox_UUID).val();
		var lel = currLev > 4 ? currLev : 4;
		$("#searchCxt_"+issp_combobox_UUID).val("");
		$("#comboxInput_" + issp_combobox_UUID).val('');
		combobox._filterTreeData(cachedata, lel, true);
		var currSelectedNode = new Array();
		currSelectedNode.push({id:'',text:'', clickStatus:'reset'});
		if (callBackMap.get(issp_combobox_UUID) != null && callBackMap.get(issp_combobox_UUID) != "") {
			callBackMap.get(issp_combobox_UUID)(currSelectedNode);
		}else{
			if (callBackMap_bak.get(issp_combobox_UUID) != null && callBackMap_bak.get(issp_combobox_UUID) != "")
				callBackMap_bak.get(issp_combobox_UUID)(currSelectedNode);
		}
	};
	
	/**
	 * 确定按钮
	 */
	HelpComboBox.prototype._confirmBtnclickHandler = function (event) {
		$("#errMsg_" + issp_combobox_UUID).html('');//清空确定按钮前提示消息
		var currSelectedNode = $("#comboboxtree_" + issp_combobox_UUID).treeview('getSelected');
		if (currSelectedNode.length != 0) {
			$("#comboxInput_" + issp_combobox_UUID).val(currSelectedNode[0].text);
		} else {
			var mactchNode = combobox._getMactchNodeOfTree($("#comboxInput_" + issp_combobox_UUID).val());
			if(mactchNode!=null){
				$("#comboxInput_" + issp_combobox_UUID).val(mactchNode[0].text);
				currSelectedNode = mactchNode[0].id;
			}else{
				$("#errMsg_" + issp_combobox_UUID).html('<font color="red" size="2">请选择节点</font>');
				return;
			}
		}
		combobox._fillData(cachedata, 4);//重新填充树以便取子节点时保证数据完整
		$('#ispp-help-combobox' + issp_combobox_UUID).modal('hide');
		currSelectedNode['clickStatus'] = 'confirmClick';
		if (callBackMap.get(issp_combobox_UUID) != null && callBackMap.get(issp_combobox_UUID) != "") {
			callBackMap.get(issp_combobox_UUID)(currSelectedNode);
		}else{//和含有组件的其他页面切换时,callBackMap取不到对应方法时，取备份map中取值执行回调函数
			if (callBackMap_bak.get(issp_combobox_UUID) != null && callBackMap_bak.get(issp_combobox_UUID) != "")
				callBackMap_bak.get(issp_combobox_UUID)(currSelectedNode);
		}
	};
	
	/**
	 * 弹框中检索输入框变更事件
	 */
//	HelpComboBox.prototype._searchInputChangeHandler = function (event,options) {
//		$("#comboxInput_" + issp_combobox_UUID).val($("#searchCxt_"+issp_combobox_UUID).val());
//	};
	
	/**
	 * 关闭模态框按钮按下时
	 */
	HelpComboBox.prototype._colseBtnclickHandler = function (event) {
		$("#searchCxt_"+issp_combobox_UUID).val('');
	};
	
	/**
	 * 根据文本信息获取树上对应节点
	 */
	HelpComboBox.prototype._getMactchNodeOfTree = function (nodeText) {
		var matchNodes = $("#comboboxtree_" + issp_combobox_UUID).treeview('search',[ nodeText, { exactMatch : true } ]);
		if(matchNodes.length>0){
			return matchNodes
		}
		return null;
	};
	
	$.fn[pluginName] = function (options, args) {
		var helpComboBox = new HelpComboBox(this, options);
		helpComboBox.initComboBox(options);
	}
	
	$.fn.setComboBoxData = function(options) {
		if(!isCreatModal || callBackMap.get(issp_combobox_UUID)==null){
			//检索框中输入条件不为空且输入内容有匹配ID时
			isCreatModal = true;
			compareData = options.data;
			return combobox._inputMouseleaveAfterHandler(compareData);
		}
		var keySet = callBackMap.keySet(); 
		for(var i in keySet){ 
			callBackMap_bak.put(keySet[i], callBackMap.get(keySet[i]))
		}
		// UUID切换前备份至全局Map
		var helpComboBox = new HelpComboBox(this, options);
		issp_combobox_UUID = this["0"].lastChild.id.split("_")[1];
		var datas = options.data;
		var nIsDeVal = isDeValMap.get(issp_combobox_UUID + 'isDeVal');
		nIsDeVal = nIsDeVal == null ? false : nIsDeVal;
		if (nIsDeVal) { //帮助框需加载默认值时
			return helpComboBox.bulidComboBox(datas, true);
		}
		return helpComboBox.bulidComboBox(datas, false);
	}
	
	$.fn.clearComboxText = function(id) {
		return combobox._clearComboxText(id);
	}

})(jQuery, window, document);
