/** 
 * author huangxianbo
 * 
* *********  操作实例  ************** 
*   页面上定义div,id自定义 例： <div id="brandid_group"></div>（同一页面多个下拉列表时，id不可相同）
*   $("#brandid_group").ISSPSpinnerBox({
*		width:'100%',//下拉列表宽度
*		height: '10%',//下拉列表高度
*		margLeft:'0%',//margin-left属性
*		dataId:'ID',// 数据源id名
*		dataTxt:'F_BRANDMC',// 数据源显示列名
*		dataTabName:'BES_EQUIPMENT_BRAND',// 数据源显示表名
*		conColumn:'',//下拉列表检索条件(表列名、多列用逗号隔开)
		conValue:'',//条件对应的值(列对应的值,一列对应多个值时,传数组格式的值)
*		selId:$("#hidden_f_brandid").val(),// 下拉列表默认选中的id
*		callBack: spinnerChange,  //自定义事件
*	});
*	
*	页面自定义函数 (例如函数名:spinnerChange)
*		下拉列表更改时，返回值是对象obj
*	自定义函数
*		function spinnerChange(obj){
*			取选中id : var currId = obj.id;
*			取选中value : var currTxt = obj.txt;
*		}
*/
;(function($, window, document, undefined) {
	var pluginName = 'ISSPSpinnerBox';
	var webroot = getRootPath();
	var issp_spinnerbox_UUID = null;
	var currId ='';//自定义div的id
	var currSelId ='';//
	var currWid ='';//
	var currHe ='';//
	var currMarLe ='';//
	var callBackMap = new Map();
	var spinnerMap = new Map();
	var divIdMap = new Map();
	var uuidMap = new Map();
	var currSelIdMap = new Map();
	var currWidMap = new Map();
	var currHeMap = new Map();
	var currWidMap = new Map();
	var currMarLeMap = new Map();
	var selFirstMap = new Map();
	var isNoSelectedTextMap = new Map();
	var noSelectedTextMap = new Map();
	var liveSearchMap = new Map();
	var callBack = '';//自定义函数
	
	var SpinnerBox = function(ele, opt) {
		spbox = this;
		this.$element = ele, 
		this.defaults = {
			'dataId':'', // 数据源id名
			'dataTxt':'',// 数据源显示列名
			'dataTabName':'',// 数据源显示表名
			'conColumn':'',//下拉列表检索条件(表列名、多列用逗号隔开)
			'conValue':'',//条件对应的值(列对应的值,一列对应多个值时,传数组格式的值)
			'selId' :'',// 默认选中id
			'width':'100%',//下拉列表的宽度
			'height':'3vh',//下拉列表的高度
			'margLeft':'0%',//margin-left属性
			'selFirst':false, //是否默认选中第一个值
			'isHasData':false,// 默认没有数据源,需要请求后台
			'idArray':'',//id数组(已有现成数据源)
			'valArray':'',//值数组(已有现成数据源)
			'isNoSelectedText':false, //是否设置未选中提示文本
			'noSelectedText':'--请选择--', //未选中提示文本内容
			'liveSearch': true,//下拉列表的检索框是否有效(true:或不传值:有效,false:无效)
			'callBack':'',//下拉列表变更时执行回调函数，返回所选id和显示文本
		}, this.options = $.extend({}, this.defaults, opt)
	};
	
	/**
	 * 创建下拉列表
	 */
	SpinnerBox.prototype.bulidSpinnerBox = function(options) {
		issp_spinnerbox_UUID = guid.newGUID();
		currId = this.$element[0].id;
		divIdMap.put(currId, this.$element[0].id);
		uuidMap.put(currId, issp_spinnerbox_UUID);
		currSelIdMap.put(currId, this.options.selId);
		currWidMap.put(currId, this.options.width);
		currHeMap.put(currId, this.options.height);
		currWidMap.put(currId, this.options.width);
		currMarLeMap.put(currId, this.options.margLeft);
		selFirstMap.put(currId, this.options.selFirst);
		isNoSelectedTextMap.put(currId, this.options.isNoSelectedText);
		noSelectedTextMap.put(currId, this.options.noSelectedText);
		liveSearchMap.put(currId, this.options.liveSearch);
		callBackMap.put(issp_spinnerbox_UUID, this.options.callBack);
		if (this.options.isHasData!= true && this.options.dataId != '' && this.options.dataTxt != '' && this.options.dataTabName != '') {
			var cColumns = this.options.conColumn;
			if(cColumns instanceof Array){
				cColumns = "'"+cColumns.join("','")+"'";
			}
			var cValues = this.options.conValue;
			if(cValues instanceof Array){
				cValues = "'"+cValues.join("','")+"'";
			}
			$.ajax({
				url : webroot + '/view/isspplugins/spinnerboxData',
				type : "post",
				data : {
					dataId : this.options.dataId,
					dataTxt : this.options.dataTxt,
					dataTabName : this.options.dataTabName,
					conColumn : cColumns,
					conValue : cValues,
					currId:this.$element[0].id,
				},
				success : function(data) {
					var resStatus = data.status;
					if (resStatus == '1'||resStatus == '2') { //resStatus(1:根据条件查询到数据2:根据条件未查询到数据)
						// 数据处理
						spbox._processDataHandler(data.map['ids'], data.map['names'], data.map['currId'], resStatus);
					}
				}
			});
		} else if(this.options.isHasData== true){
			var idArr = this.options.idArray;
			var valArr = this.options.valArray;
			if(!(idArr instanceof Array && valArr instanceof Array && idArr.length==valArr.length)){
				alert('数据源数组格式不正确');
			}
			// 数据处理
			spbox._processDataHandler(idArr, valArr, currId, 3);
		} else{
			alert('缺少参数');
		}
	}
	
	/**
	 * 数据处理(获取数据源后对数据处理)
	 * ids(对应下拉列表value)
	 * names(对应下拉列表text)
	 * cuId(当前下拉列表所在DIV的ID)
	 * optStatus(获取数据源后的几种不同处理)
	 */
	SpinnerBox.prototype._processDataHandler = function (ids, names, cuId, optStatus) {
		$('#' + divIdMap.get(cuId)).empty();
		var spinner = $('<select id="' + uuidMap.get(cuId) + '_group" class="selectpicker bla bla bli" data-live-search="true" style="border:1px solid green;" onchange="spbox._spinneChangeHandler(this)"></select>');
		var spIdBak = $('<input id="spinerInfoBak_' + uuidMap.get(cuId) + '" value="'+cuId+'~'+uuidMap.get(cuId)+'" type="hidden" />');
		$('#' + divIdMap.get(cuId)).append(spinner).after(spIdBak);
		$('#' + uuidMap.get(cuId) + '_group').empty();
		var select = $('#'+ uuidMap.get(cuId) + '_group');
		var isText = isNoSelectedTextMap.get(cuId);
		if(isText){ //设置提示文本时
			var selFirst = selFirstMap.get(cuId);
			if(selFirst){ //设置默认选中第一个
				select.append("<option value="+"default" + uuidMap.get(cuId) +" selected = 'selected'>"+noSelectedTextMap.get(cuId)+"</option>");
			}else{
				select.append("<option value="+"default" + uuidMap.get(cuId) +">"+noSelectedTextMap.get(cuId)+"</option>");
			}
		}
		if (!(optStatus == 2)){
			if(isText && selFirst== true && currSelIdMap.get(cuId)!=null && currSelIdMap.get(cuId)!=ids[0]){
				alert('设置选中第一个值时，请勿传参selId(默认选中ID)');
				return;
			}
			for (var i = 0; i < ids.length; i++) {
				if(ids[i]!= currSelIdMap.get(cuId)){
					select.append('<option value="'+ids[i]+'">'+names[i]+'</option>');
				}else{
					select.append('<option value="'+ids[i]+'" selected = "selected">'+names[i]+'</option>');
				}
			}
		}
		var currWidth = currWidMap.get(cuId)== null ? '100%': currWidMap.get(cuId);
		$('#'+uuidMap.get(cuId) + '_group').selectpicker({
			'selectedText': 'cat',
			'width':currWidth, //设置下拉列表的宽度
			'liveSearch':liveSearchMap.get(cuId)== false ? false : true,
		});
		
		var selectBtn = $("#" + uuidMap.get(cuId) + '_group').next().children("button:first-child");
		selectBtn[0].style.marginLeft = currMarLeMap.get(cuId); //设置下拉样式(margin-left)
		selectBtn[0].style.height = currHeMap.get(cuId); //设置下拉样式(height)
		selectBtn.siblings().css('width',currWidth)
		$('#'+uuidMap.get(cuId) + '_group').selectpicker('refresh');
		spinnerMap.put(divIdMap.get(cuId), uuidMap.get(cuId) + '_group');
		var currSpinId = '';
		var currSpinTxt ='';
		if(isText){//含有默认未选中提示文本时
			if(selFirst){
				if (select[0].length > 0) {
					currSpinId = select[0][1].value;
					for (var j = 0; j < select[0].length; j++) {
						if (select[0][j].value == currSpinId) {
							currSpinTxt = select[0][j].text;
							break;
						}
					}
				}
			}else{
				currSpinId = '';
				currSpinTxt ='';
			}
		} else {
			if (select[0].length > 0) {
				currSpinId = select[0].value;
				for (var j = 0; j < select[0].length; j++) {
					if (select[0][j].value == currSpinId) {
						currSpinTxt = select[0][j].text;
						break;
					}
				}
			}
		}
		var sp = { id : currSpinId, txt : currSpinTxt };
		var UUID = uuidMap.get(cuId);
		if (callBackMap.get(UUID) != null && callBackMap.get(UUID) != "") {
			callBackMap.get(UUID)(sp);
		}
	}
	
	/**
	 * 下拉列表变化事件
	 */
	SpinnerBox.prototype._spinneChangeHandler = function (event) {
		var currUUID = event.id.split("_")[0];
		var currSpinId = event.value;
		var currSpinTxt = '';
		if (event.length > 0 && currSpinId !='default'+currUUID) {
			for (var i = 0; i < event.length; i++) {
				if (event[i].value == currSpinId) {
					currSpinTxt = event[i].text;
					break;
				}
			}
		} else {
			currSpinId = '';
			currSpinTxt = '';
		}
		var sp = { id : currSpinId, txt : currSpinTxt };
		if (callBackMap.get(currUUID) != null && callBackMap.get(currUUID) != "") {
			callBackMap.get(currUUID)(sp);
		}
	};
	
	/**
	 * 设置列表回复默认未选中状态
	 */
	SpinnerBox.prototype._initSpinner = function (id) {
		var idBak = $("input[id^=spinerInfoBak_]");
		var idStr='';
		for(var i = 0;i<idBak.length;i++){
			if(idBak[i].value!=''){
				idStr=idStr+idBak[i].value;
			}
		}
		var index = idStr.lastIndexOf(id);
		//根据传的id获取对应的uuid
		var currUUID =  idStr.substring(index, index + id.length+37).split('~')[1];
		var select = $('#'+ currUUID + '_group');
		select.selectpicker('val', 'default' + currUUID);
		select.selectpicker('refresh'); //刷新下拉列表状态,并触发下拉列表事件
		return select;
	};
	
	/**
	 * 创建下拉列表
	 */
	$.fn[pluginName] = function (options, args) {
		var spinner = new SpinnerBox(this, options);
		return spinner.bulidSpinnerBox(options);
	}
	
	/**
	 * 初始化下拉列表(只限下拉列表设置未选中提示文本时)
	 */
	$.fn.initSpinnerBox = function(id) {
		return spbox._initSpinner(id);
	}
	
})(jQuery, window, document);