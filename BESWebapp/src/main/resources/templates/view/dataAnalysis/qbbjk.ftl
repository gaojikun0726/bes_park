<!-----情报板监控---->
<div class="map-border" style="width:calc(100% - 20px); height: 50%;">
	<div id="qbbMap" style="width: 100%; height: 100%; padding-left:20px;"></div>
</div>
<div class="animated map-border" style="height:50%;animation-duration: 1s;z-index: 100;animation-name: fadeInRight;">
	<div class="input-group"  style="height:40px;width:100%;padding-top:5px;">

		<div class="form-horizontal" style="width: 15%;float: left;padding-left:5px;">
	        <select class="form-control" style="height: 30px; width:100%">
	            <option>青临高速运营中心</option>
	            <option>青临高速收费站</option>
	            <option>青临高速隧道</option>
	        </select>
		</div>
        
        <div style="width: 40%;float: left;padding-left: 5px;">
            <input style="width: 90%;" type="text" class="input-sm form-control" id="keywords" name="keywords" placeholder="地点"/> 
			<span class="input-group-btn" style="width: 60px;"><i class="fa fa-search"></i> <button class="btn btn-primary btn-sm m-b-none" id="queryBmzdBtn"><i class="fa fa-search"></i> 搜索</button></span>
       	</div>
        
        <div style="float:right;padding-right: 30px;">
            <a id="exportqbbjkTable" class="btn btn-primary" data-toggle="modal" href="#modal-form-adduser">导出 <i class="fa fa-plus"></i></a>
        </div>
	</div>
	
	<div style="height:calc(100% - 90px)">
		<div id="qbbjkTable"></div>
	</div>
	<div style="height:50px">
		<!-- 分页表单 -->
		<form action="${ctx }/view/bmzd/bmzd_page" id="bmzdPageForm" >
			<!-- 查询条件，用隐藏表单域 -->
			<input type="hidden" value="${keywords! }" name="keywords" />
			<#assign formId = "bmzdPageForm"><!-- formId: 分页控件表单ID -->
			<#assign showPageId = "qbbjkTable"><!-- showPageId: ajax异步分页获取的数据需要加载到指定的位置 -->
			<#include "/common/issp_page.ftl"/><!-- 分页控键 -->
		</form>
	</div>
</div>

<script type="text/javascript">

	var qqbmap = new BMap.Map("qbbMap");    // 创建Map实例
	var point = new BMap.Point(117.024967, 36.682785);
	qqbmap.centerAndZoom(point, 11);  // 初始化地图,设置中心点坐标和地图级别
	//添加地图类型控件
	qqbmap.addControl(new BMap.MapTypeControl({
		mapTypes:[
            BMAP_NORMAL_MAP,
            BMAP_HYBRID_MAP
        ]}));	  
	qqbmap.setCurrentCity("济南");          // 设置地图显示的城市 此项是必须设置的
	qqbmap.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
	qqbmap.addControl(new BMap.NavigationControl());
	qqbmap.addControl(new BMap.MapTypeControl({mapTypes: [BMAP_NORMAL_MAP,BMAP_HYBRID_MAP]}));
	//qqbmap.setMapStyle({style:'midnight'});  //dark  midnight
	
	
	var qbbjkTabledata = [
		{xh:1, F_SSDW:"青临高速运营中心",F_DD:"青临高速", F_YXZT:"正常运行", F_CLZT:"处理中",F_XSXX:"限速120",F_NH:"30kW·h"},
		{xh:1, F_SSDW:"青临高速运营中心",F_DD:"青临高速", F_YXZT:"正常运行", F_CLZT:"处理中",F_XSXX:"限速120",F_NH:"30kW·h"},
		{xh:1, F_SSDW:"青临高速运营中心",F_DD:"青临高速", F_YXZT:"正常运行", F_CLZT:"处理中",F_XSXX:"限速120",F_NH:"30kW·h"},
		{xh:1, F_SSDW:"青临高速运营中心",F_DD:"青临高速", F_YXZT:"正常运行", F_CLZT:"处理中",F_XSXX:"限速120",F_NH:"30kW·h"},
		{xh:1, F_SSDW:"青临高速运营中心",F_DD:"青临高速", F_YXZT:"正常运行", F_CLZT:"处理中",F_XSXX:"限速120",F_NH:"30kW·h"},
		{xh:1, F_SSDW:"青临高速运营中心",F_DD:"青临高速", F_YXZT:"正常运行", F_CLZT:"处理中",F_XSXX:"限速120",F_NH:"30kW·h"},
		{xh:1, F_SSDW:"青临高速运营中心",F_DD:"青临高速", F_YXZT:"正常运行", F_CLZT:"处理中",F_XSXX:"限速120",F_NH:"30kW·h"},
		{xh:1, F_SSDW:"青临高速运营中心",F_DD:"青临高速", F_YXZT:"正常运行", F_CLZT:"处理中",F_XSXX:"限速120",F_NH:"30kW·h"},
		{xh:1, F_SSDW:"青临高速运营中心",F_DD:"青临高速", F_YXZT:"正常运行", F_CLZT:"处理中",F_XSXX:"限速120",F_NH:"30kW·h"},
	];
		
	//单元格点击事件	
	var cellClickFunction = function(e, cell){
		console.log("cell click - "+cell.getValue(), cell)
	};
	
	var genderEditor = function(cell, onRendered, success, cancel){
	    var editor = $("<select><option value=''></option><option value='male'>male</option><option value='female'>female</option></select>");
	    editor.css({
	        "padding":"3px",
	        "width":"100%",
	        "box-sizing":"border-box",
	    });
	
	    //Set value of editor to the current value of the cell
	    editor.val(cell.getValue());
	
	    //set focus on the select box when the editor is selected (timeout allows for editor to be added to DOM)
	    onRendered(function(){
	      editor.focus();
	      editor.css("height","100%");
	    });
	
	    //when the value has been set, trigger the cell to update
	    editor.on("change blur", function(e){
	        success(editor.val());
	    });
	
	    //return the editor element
	    return editor;
	};
	
	//创建并设置table属性
	$("#qbbjkTable").tabulator({
		height:"100%",
		layout:"fitColumns",//fitColumns  fitDataFill
		//responsiveLayout:true,
		//selectable:true, //make rows selectable
		//selectablePersistence:false,
		columnVertAlign:"bottom", //align header contents to bottom of cell
		tooltips:true,
		addRowPos:"bottom",//bottom top
		//history:true,
		//pagination:"local",
		//paginationSize:7,
		movableColumns:true,
		rowFormatter:function(row){

        	if(row.getData().F_BMBH == "00"){
            	row.getElement().css({"background-color":"#A6A6DF"});
        	}
    	},
		columns:[
		{title:"序号",field:"xh",width:50,formatter:"rownum",frozen:false,headerSort:false}, //frozen column
		{title:"所属单位", field:"F_SSDW",sorter:"string",align:"center",editor:false,frozen:false}, //hide this column first 
		{title:"地点", field:"F_DD",sorter:"string",align:"center",editor:false,frozen:false}, //hide this column first 
		{title:"运行状态", field:"F_YXZT",sorter:"string",align:"center",editor:false,frozen:false}, //hide this column first 
		{title:"显示信息", field:"F_XSXX", sorter:"string",align:"center",editor:false,headerSort:false},
		{title:"能耗", field:"F_NH", sorter:"string",align:"center",editor:false,headerSort:false},
		],
		rowClick:function(e, row){
        	qqbmap.clearOverlays();
			addMarker(117.114146,36.756908,0,"");
        	$("#qbbjkTable").tabulator("selectRow", 1);
    	},
		cellEdited:function(cell){
        //This callback is called any time a cell is edidted
    	},
    	rowAdded:function(row){
        	//row - row component

        	row.scrollTo(); //row.select();
    	},
    
	    rowDeleted:function(row){
	        //row - row component
	    },
    	//footerElement:$("<div class='tabulator-footer'><button>Custom Button</button></div>"), //set custom table footer with custom button
	});

	//填充数据
	$("#qbbjkTable").tabulator("setData", qbbjkTabledata);

	$(window).resize(function(){
		$("#qbbjkTable").tabulator("redraw");
	});
	//Add row on "Add Row" button click 
	$("#addBm").click(function(){
	    $("#qbbjkTable").tabulator("addRow", { F_BMBH:"0001"});
	});
	
	//Delete row on "Delete Row" button click
	$("#delBm").click(function(){
		var selectRows = $("#qbbjkTable").tabulator("getSelectedRows");

		for(var i=0;i<selectRows.length;i++){
	    	$("#qbbjkTable").tabulator("deleteRow", selectRows[i]);
	    }
	});
	//Clear table on "Empty the table" button click
	$("#clearBm").click(function(){
	    $("#qbbjkTable").tabulator("clearData");
	});	
  $("#exportqbbjkTable").click(function(){
	$("#qbbjkTable").tabulator("download", "csv", "data.csv");
  });
		
				
 function addMarker(x, y, index,content){
	if(content == undefined){
		content="";
	}
	var point = new BMap.Point(x, y);// 创建图标对象   
	qqbmap.centerAndZoom(point, 12);
    var myIcon = new BMap.Icon("static/images/flag_mark.gif", new BMap.Size(25,25), {    
        // 指定定位位置。   
        // 当标注显示在地图上时，其所指向的地理位置距离图标左上 角各偏移10像素和25像素。
        anchor: new BMap.Size(10, 25),    
        // 设置图片偏移。   
        // 当您需要从一幅较大的图片中截取某部分作为标注图标时，您   
        // 需要指定大图的偏移位置，此做法与css sprites技术类似。    
        imageOffset: new BMap.Size(0, 0 - index * 25)   // 设置图片偏移    
    });      
    // 创建标注对象并添加到地图   
    var marker = new BMap.Marker(point, {icon: myIcon});   
    //marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
    qqbmap.addOverlay(marker);
    
	var label = new BMap.Label(content,{offset:new BMap.Size(-20,-20)});
	label.setStyle({ 
		color : "red", 
		fontSize : "13px", 
		backgroundColor :"0.05",
		border :"0"
	});
	marker.setLabel(label);
   } 								
</script>