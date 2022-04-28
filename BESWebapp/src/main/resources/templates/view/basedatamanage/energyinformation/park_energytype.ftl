<!-----内容区域---->
<!-- <div class="ibox-content m-b-sm border-bottom" style="width:100%;height:7%"> 
   <div class="input-group"  style="height:30px;width:100%;">
        <div style="width: 50%;float: left;position: relative;padding-right: 15px;" >
    	</div>
        <div style="width: 5%;float: right;position: relative;padding-right: 0px;padding-left: 15px;">
            <input type="text" class="input-sm form-control" style="width: calc(100% - 60px);" id="sjqx_keywords" name="sjqx_keywords" value="" placeholder="数据权限编号、名称"> 
			<span class="input-group-btn"  style="width: 60px;">
				 <a id="addEuser_user" href="javascript:void(-1);" onclick="energyinformation_park_energytype.baocun_park_energytype()" class="btn btn-primary" >
                	保存 <i class="fa fa-floppy-o"></i>
            </a>
            </span>
       	</div>
       	
   </div>
 </div>

园区列表 
<div class="leftarea" id="leftarea" style="margin-top:-10px;height:93%;width:21%;margin-left: 1%;border-radius:25px;-moz-border-radius:25px;padding-left:0;overflow:auto">
	<div style="padding:2px 100px 2px 10px;height:6%;">
		     <div style="float:left;">
		     <input type="text" class="input-sm form-control" style="width: calc(130%);padding: 4px 15px;margin-left:5px;" id="search_park_Keywords" name="search_park_Keywords" value=""  placeholder="园区编号、名称"> 
		     </div>
		     <div style="float:right;">
		     <span class="input-group-btn"  style="width: 50px;">
				<button class="btn btn-primary btn-sm m-b-none" id="query_parkBtn" onclick="energyinformation_park_energytype.search_park()">
                	<i class="fa fa-search"></i> 搜索
                </button>
            </span>
		     </div>
       	</div>	
		<div id="bespark_showtable" >
	</div>
</div>

    
园区能耗类型列表
<div style=" float: right;height:93%;width:66%;position:relative;padding: 5px 5px 0px 5px;margin-top:30px;">
   
   <div style="height:calc(100%)">
		<div id="bespark_energytypeTable">	</div>
   </div>
</div> -->
<!-- ------------------------------------------------------------------------------------ -->
<!-- 园区列表  -->
<div class="leftarea information_left"style="width: 18.4%;">
	<div class="information-model">
		<span class="tree_Subtitle">
			<i class="fa fa-share-alt" aria-hidden="true"></i>&nbsp;园区列表 >>>
		</span>
	</div>
	<div class="information-model">
		<!-- 搜索框 -->
		<div class="zc_search find"style="left: 0%;margin-left: 1%;">
			<input type="text" class="find-style"  id="search_park_Keywords" name="search_park_Keywords" placeholder="园区编号、名称">
			<button id="query_parkBtn" onclick="energyinformation_park_energytype.search_park()"><i class="fa fa-search" aria-hidden="true"></i></button>
		</div>
	</div>
	<div id="bespark_showtable" style="height:calc(100% - 7vh)" >
	</div>
</div>
<!-- 右侧 -->
<div class="information_right"style="width: 81.5%;">
		<div class="information-model">
			<span class="Subtitle">
				<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;园区能耗类型列表>>>
			</span>
			<!-- 保存按钮 -->
			<a id="addEuser_user" href="javascript:void(-1);" onclick="energyinformation_park_energytype.baocun_park_energytype()" class="btn btn-primary toLeft">
				<i class="fa fa-floppy-o"></i>&nbsp;保存
			</a> 
		</div>
		<div id="bespark_energytypeTable" class="Information_area" style="height:calc(100% - 3.5vh)">	</div>
	</div>

 <script type="text/javascript">
 ;var energyinformation_park_energytype = (function($, window, document, undefined) {
 	var _ctx = '${ctx}';
	var _curRow = null;
	var _yqbh = null;
	//设置格式
	var optIconFunction = function(cell, formatterParams){ //plain text value
		var fyqbh = cell.getRow().getData().f_yqbh;
		return "<div class='btn-group '><button>全选</button></div>"
	};
	//创建并设置table属性-园区列表
	$("#bespark_showtable").tabulator({
		height:"calc(100% - 7vh)",
		layout:"fitColumns",//fitColumns  fitDataFill
		columnVertAlign:"bottom", //align header contents to bottom of cell
		tooltips:true,
		selectable:1,
		resizableColumns:false,
		movableColumns:false,
		columns:[
		{title:"序号",field:"id",formatter:"rownum",frozen:false,headerSort:false,align:"center"},
		{title:"园区名称", field:"f_yqmc",sorter:"string",editor:false,headerSort:false,align:"center"},
		],
		rowClick:function(e, row){
        	_curRow = row;    
        	_yqbh = _curRow.getData().f_yqbh;
        	startShowPark_Energytype();
        	}	
	});
	//$("#bespark_showtable").tabulator("setData", _ctx+'/view/basedatamanage/energyinformation/park_list');
	//初始显示园区表和第一个园区的能源类型表
		$(function (){
		$.ajax({
			type:"get",
			url:_ctx+'/view/basedatamanage/energyinformation/park_list',
			dataType:"json",
			success:function(result){
				if(result.hasOwnProperty("list")){//hasOwnProperty() 方法会返回一个布尔值，指示对象自身属性中是否具有指定的属性
					if(result.list.length>0){
						_yqbh = result.list[0].f_yqbh;
						startShowPark_Energytype();
						$("#bespark_showtable").tabulator("setData",result.list);
						var firstNode = $("#bespark_showtable").tabulator("getRows");
						$("#bespark_showtable").tabulator("selectRow",firstNode[0]);
					}
				}
			}
		});
		})
   //展现园区能源类型表
	function startShowPark_Energytype (){
	$(".energytypexz[type='checkbox']").removeAttr("checked");
	if(_yqbh!=null){
		$("#bespark_energytypeTable").tabulator("setData",_ctx+'/view/basedatamanage/energyinformation/getgllist?f_yqbh='+_yqbh);
	}
	}
	//创建并设置table属性-园区能耗类型表
	$("#bespark_energytypeTable").tabulator({
		height:"calc(100% - 3.5vh)",
		layout:"fitColumns",
		columnVertAlign:"bottom",
		tooltips:true,
		selectable:false,
		tooltipsHeader: false,
		movableColumns:false,
		//pagination:"local",
		//paginationSize:7,
		columns:[
        {title:"选择 <input id=\"id_f_xz0\" type='checkbox' class='energytypexz' onclick=\"energyinformation_park_energytype.energytype_transmitField('f_xz0')\">", field:"rlgl",formatter:"tickCross",align:"center", sorter:"boolean",editor:true,headerSort:false,tooltip:false,tooltipsHeader:false},
		{title:"序号",formatter:"rownum",align:"center",frozen:false,headerSort:false},
		{title:"能源编号", field:"fnybh",align:"center", sorter:"string",editor:false,headerSort:false},
		{title:"能源名称", field:"fnymc",align:"center",sorter:"string",editor:false,headerSort:false},
		{title:"单价", field:"fPrice",align:"center",sorter:"string",editor:false,headerSort:false},
		{title:"耗煤量", field:"fCoalAmount",sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"二氧化碳", field:"fCo2",sorter:"string",editor:false,align:"center",headerSort:false},
		//{title:"创建日期", field:"fCrdate",swidth:80,sorter:"date",align:"center",visible: true,editor:false,headerSort:false},
	   //{title:"修改日期", field:"fChdate",sorter:"date",align:"center",visible: true,editor:false,headerSort:false},
		],
		rowClick:function(e, row){
        	_curRow = row; 
    	},
	});
	$("#bespark_energytypeTable").tabulator("setData", _ctx+'/view/basedatamanage/energyinformation/getgllist');
	//触发搜索的回车事件-园区
	$("#search_park_Keywords").focus(function(){
					  $(this).keydown(function (e){
					  if(e.which == "13"){
						  energyinformation_park_energytype.search_park(); //触发该事件
					    } 
					  })
					});

	return{
		
  //搜索——园区表
		search_park:function(){
			var park_Keywords = $("#search_park_Keywords").val();
			$("#bespark_showtable").tabulator("setData", _ctx+'/view/basedatamanage/energyinformation/findByKeywords?keywords='+park_Keywords);
		},
		
  //全选-打勾
	energytype_transmitField:function(fxz){
		var bool = $("#id_"+fxz).is(':checked');
		var f_xz = fxz;
		var tabulator_data = $("#bespark_energytypeTable").tabulator("getData");
		if(bool == true){
		for (var i = 0; i < tabulator_data.length; i++) {
			
				tabulator_data[i].rlgl = true;	
		}
		$("#bespark_energytypeTable").tabulator("setData", tabulator_data);
	}else{
		for (var i = 0; i < tabulator_data.length; i++) {
			
				tabulator_data[i].rlgl = false;
		}
		$("#bespark_energytypeTable").tabulator("setData", tabulator_data);
		}
	},
	//执行保存
	baocun_park_energytype:function(){
		$(".energytypexz[type='checkbox']").removeAttr("checked");
		var tabulator_data = $("#bespark_energytypeTable").tabulator("getData");
		for (var i = 0; i < tabulator_data.length; i++) {
			 var rlgldata = tabulator_data[i];
			 rlgldata.rlgl = rlgldata.rlgl == true ? '1' : '0';
				 $.ajax({
				    url: _ctx + "/view/basedatamanage/energyinformation/park_energytype_add",
				    dataType: "json",
			        type: "post",
			        data:{ 
			        		f_yqbh:_yqbh,
			        		f_nybh:rlgldata.fnybh,
			        		mark:rlgldata.rlgl
			        },
			          beforeSend: function () { 
			 				showLoad();	             
			 			},
				      success: function(data) {
					         if (data.status == '1') {
					           swal( data.msg,"", "success");
					        	 
					         } else{
					           swal( data.msg, "", "error");
					         }
					       },
					       complete: function () {
								hiddenLoad();
							},
					       error: function(data) {
					       	 swal( data.msg,"", "error");
					       }
					     });
				}
			}
		
	}
 })(jQuery, window, document);
 </script>