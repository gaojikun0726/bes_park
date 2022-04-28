<style type="text/css">
.cj_zlb {
    margin-left: 12px;
	border: 1px solid #8fe3f7;
	width: 77%;
	display: inline-block;
	padding: 0;
	height: 300px;
	overflow: auto;
}

.cj_zlb>table>thead>tr>th {
	white-space: nowrap;
}

.cj_xzms {
	min-width: 90px;
}

.cj_xzd {
	min-width: 90px;
}
</style>

<!-----内容区域---->
<div id="eqconfiguration_sbdy_div" class="information-model">
	<span class="Subtitle">
		<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;场景配置列表&gt;&gt;&gt;
	</span>
	<!-- 搜索框 -->
	<div class="zc_search find">
	  <input type="text" class="find-style" id="park_keywords" name="park_keywords" placeholder="联网设备">
	  <button id="querySbdyBtn" onclick="searchqydy()"><i class="fa fa-search" aria-hidden="true"></i></button>
	</div>
</div>
<!-- 左侧树 -->
<div class="leftarea" id="leftElectricPconf" style="height: 93%; width: 40%!important;">
	<div id="tree_cjpz"></div>
</div>

<div style="float: right; height: 93%; width: 60%; position: relative; padding: 10px 5px 0px 5px; margin-top: -10px;">
    <div style="height: calc(100%)">
        <div class="attrInfo" style="overflow: auto;">
        <div class="frist_attr">
        <div class="has-success">
        </div>
        <form role="form" id="add_Scence" name="add_Scence" class="form-horizontal" >
			<input type="hidden" id="add_cjtype" name="fType" value=""><!-- 2 控制场景 3采集场景 -->
			<input type="hidden" id="add_Scence_nodeId" name="fParentid" value=""><!-- 场景id -->
			<input type="hidden" id="add_d_IPAddr" name="IPAddr">
			<input type="hidden" id="add_d_Port"   name="Port">
			<input type="hidden" id="add_d_parentid" name="parentid">
			<input type="hidden" name="pin_tabletd">
			<input type="hidden" name="pin_tabletrtd">
			<input type="hidden" name="mode_mslx"><!-- bes_zonemode 模式类型 -->
			<input type="hidden" name="operateType"><!-- 操作类型 -->
			<input type="hidden" id="add_cjlx"><!-- 场景类型 -->
        <div class="vertical-timeline-block eqTreeAttrLineWidth">
            <div class="form-group col-sm-6">
                <label class="col-sm-2 control-label">系统名称:</label>
                <div class="col-sm-8">
                      <input type="text" id="add_xtmc" name="fSysName" required class="form-control">
                </div>
            </div>
            <div class="form-group col-sm-6">
                <label class="col-sm-2 control-label">系统别名:</label>
                <div class="col-sm-8">
                    <input type="text" id="add_xtbm" name="fNickName" required class="form-control">
                </div>
            </div>
            
            <div class="form-group col-sm-6">
                <label class="col-sm-2 control-label">场景说明:</label>
                <div class="col-sm-8">
                      <input type="text" id="add_cjsm" name="fDescription" required class="form-control">
                </div>
            </div>
            <div class="form-group col-sm-6">
                <label class="col-sm-2 control-label">场景模式:</label>
                <div class="col-sm-8">
                    <input type="text" name="cjms_name" required class="form-control">
                </div>
            </div>
            
            <div class="form-group col-sm-6">
                <label class="col-sm-2 control-label">使能:</label>
                <div class="col-sm-8">
                      <input type="radio" name="fEnabled" id="add_fEnabled_y" checked value="1">是
                      <input type="radio" name="fEnabled" id="add_fEnabled_f" value="0">否
                </div>
            </div>
            <div class="form-group col-sm-12">
                    <div style="display: inline-grid;float: left;width: 11.6%;">
	                  <label class="col-sm-1 control-label"style="width:100%;">指令表:<span class="text-danger">&nbsp;</span></label>
		              <div style="display:inline-grid;padding-left: 1vh;">
		                <button type="button" class="btn btn-default cj_xzms right_disabled">新增模式</button>
		                <button type="button" class="btn btn-default cj_xzd right_disabled">新增点</button>
		              </div>
	               </div>
	              <div class="col-sm-6 cj_zlb" id="add_cjzlb" style="">
	                  <input type="hidden" name="start">
	                  <table class="table ">
	                      <thead>
	                          <tr>
	                              <th style='width:8vw;'>点</th>
	                              <th style='width:8vw;'>默认模式</th>
	                              <th>值</th>
	                          </tr>
	                      </thead>
	
	                      <tbody>
	                      </tbody>
	                  </table>
	                  <input type="hidden" name="end">
	              </div>
            </div>
                 
        </div>
           <div style="margin: 0 auto;width: 36%;">
              <span style="margin-left: 16vw;">
				  <button  class="btn sbtreeNodeBtn" id="right_save" type="submit"><strong>保存</strong></button>
				  <button id="cjpz_tbsj" class="btn sbtreeNodeBtn" onclick="basedatamanage_eqmanage_cjpz.btnTbsj(this);return false;">同步数据</button>
				  <button id="cjpz_sjdb" class="btn sbtreeNodeBtn" onclick="basedatamanage_eqmanage_cjpz.btnSjdb(this);return false;">数据对比</button>
              </span>
           </div>
         </form>
         </div>
     </div>
    </div>
</div>
<!-- 模态框 -->
<div class="modal fade" id="modal-form-addFolder" tabindex="-1"
        role="dialog" aria-labelledby="myModalLabel" data-backdrop="static"
        data-keyboard="false">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header bg-primary">
                    <button aria-hidden="true" data-dismiss="modal" class="close"
                        type="button">×</button>
                    <h4 class="modal-title addIcon">&nbsp;新增文件夹</h4>
                </div>
                <div class="modal-body">
                    <form role="form" id="addFolder" name="addFolder"
                        class="form-horizontal">
                        <input type="hidden" id="addFolder_nodeId" name="fParentid"
                            value="">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">文件夹名称<span
                                class="text-danger">*</span></label>
                            <div class="col-sm-8">
                                <input type="text" id="fName" name="fName" required
                                    class="form-control">
                            </div>
                        </div>
                        <div class="form-group m-t-sm">
                            <div class="col-sm-6 col-sm-push-4" style="display: inline-flex;">
                                <button class="btn btn-md btn-primary" type="submit">
                                    <strong>确定</strong>
                                </button>
                                <button class="btn btn-white m-l-sm" type="button"
                                    data-dismiss="modal">取消</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <!-- --新增场景开始- -->
    <div class="modal fade" id="modal-form-addScene" tabindex="-1"
        role="dialog" aria-labelledby="myModalLabel" data-backdrop="static"
        data-keyboard="false">
        <div class="modal-dialog">
            <div class="modal-content" style="width: 950px;">
                <div class="modal-header bg-primary">
                    <button aria-hidden="true" data-dismiss="modal" class="close"
                        type="button">×</button>
                    <h4 class="modal-title editIcon" id="scene_cjbt">&nbsp;</h4>
                </div>
                <div class="modal-body">
                    
                </div>
            </div>
        </div>
    </div>

    <!-- 设备类型树 -->
    <div class="modal fade" id="modal-equipment-type-cjs" tabindex="-1"
        role="dialog" aria-labelledby="myModalLabel" data-backdrop="static"
        data-keyboard="false">
        <div class="modal-dialog">
            <div class="modal-content" style="width: 380px">
                <div class="modal-header bg-primary">
                    <button aria-hidden="true" data-dismiss="modal" class="close"
                        type="button">×</button>
                    <h4 class="modal-title">设备类型</h4>
                </div>
                <div class="modal-body" style="height: 600px; overflow-y: auto;">
                    <input type="hidden" id="zlb_id" value="">
                    <input type="hidden" id="tree_id">
                    <div id="tree_equipment_cjs"></div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal"
                        onclick="basedatamanage_eqmanage_cjpz.saveBtnIsCjs()">确定</button>
                </div>
            </div>
        </div>
    </div>
	<script type="text/javascript">
;
var basedatamanage_eqmanage_cjpz = (function($, window, document, undefined) {
	var treeId="";//树节点ID
 	var _ctx = '${ctx}';
 	var pid ;//当前点击节点的父节点id
 	var nodeLevel;//当前选中节点在树上的级数
 	var d_name;//选中节点名称
	var _ElectricPJs = "0";//用户组对应的级数
	var treeSelNode;//声明一个全局选中设备树设备树选中节点
	var fNodeId = "";// 每次选择点都会附一个点位id

	   //居中显示（添加）
    $('#modal-form-addFolder').on('show.bs.modal', function () {
        // 关键代码，如没将modal设置为 block，则$modala_dialog.height() 为零  
        $(this).css('display', 'block');  
        var modalHeight=$(window).height() / 2 - $('#modal-form-addFolder .modal-dialog').height() / 2;  
            $(this).find('.modal-dialog').css({'margin-top': modalHeight});
    })
    
    //控制disabled
    function RightDisabled(){
		$("#add_Scence").find("input").attr("disabled","disabled");
		$("#right_save").attr("disabled","disabled");
		$("#add_cjms").attr("disabled","disabled");
		$(".right_disabled").attr("disabled","disabled");
		$("#cjpz_tbsj").attr("disabled","disabled");
		$("#cjpz_sjdb").attr("disabled","disabled");
	}
	
	function RightDisabledCancel(){
		$("#add_Scence").find("input").removeAttr("disabled");
        $("#right_save").removeAttr("disabled");
        $("#add_cjms").removeAttr("disabled");
        $(".right_disabled").removeAttr("disabled");
        $("#cjpz_tbsj").removeAttr("disabled");
        $("#cjpz_sjdb").removeAttr("disabled");
        // 给check赋值
		$("#add_fEnabled_y").val("1");//使能
        $("#add_fEnabled_f").val("0");// 否
    }
	

	
	//新增模式点击事件
	$(document).on("click",".cj_xzms",function(){
		if($("#add_cjtype").val()=='2'){//控制场景
			$("#add_cjzlb").find("thead>tr").append("<th style='width: 8vw;'><input value='值' style='width: 5vw;color: #555;' type='text'></th>")
			var trsum=$("#add_cjzlb").find("tbody>tr");//tr
			$(trsum).each(function(i){//循环tr
				var thsum=$("#add_cjzlb").find("thead>tr>th").length;//表头th个数
				$("#add_cjzlb").find("tbody").find("#a"+i).append("<th><input  class='appendval change_ys' style='width:5vw;' type='text' value='' </th>");
			});
		}
	});
	
	//新增点 点击事件
	$(document).on("click",".cj_xzd",function(){
		var trsum=$("#add_cjzlb").find("tbody>tr").length;//tr个数
		var thsum=$("#add_cjzlb").find("thead>tr>th");//th个数
		$("#add_cjzlb").find("tbody").append("<tr id=a"+trsum+"></tr>");
		//判断是控制场景还是采集场景
		if($("#add_cjtype").val()=='2'){//控制场景
			$(thsum).each(function(i){
				$("#add_cjzlb").find("tbody").find("#a"+trsum).append("<th><input class='appendval change_ys' id=zlb"+trsum+i+" style='width:5vw;' type='text' value='' ></th>");
				if(i==0){
				    $("#add_cjzlb").find("tbody").find("#a"+trsum).find("input").attr("onclick","basedatamanage_eqmanage_cjpz.btnZldj(this)")
				}
				if(i==1){$("#add_cjzlb").find("tbody").find("#a"+trsum).find("input").eq(1).val('0');$("#add_cjzlb").find("tbody").find("#a"+trsum).find("input").eq(1).attr("readonly","readonly")}

			});
		}
	});

	//设备型号树
    function get_equipmentTypetree() {
        $.issp_ajax({
            type : "post",
            url : _ctx + "/view/basedatamanage/eqmanage/sbdy_tree",
            data:{},
            dataType : "json",
            success : function(result) {

                var treeData = buildTree({
                    data: result.list,
                    parentId: 'pid',
                    children: 'nodes'
                });

                $('#tree_equipment_cjs').treeview({
                    data : treeData, // 数据源
                    highlightSelected : true, //是否高亮选中
                    levels : 4,
                    enableLinks : true,//必须在节点属性给出href属性
                    color : "#4a4747",
                    onNodeSelected : function(event,nodeData) {
                        $('#tree_equipment_cjs').treeview('clearSearch');//清除搜索选中高亮
                        $("#tree_id").val(nodeData.id);
                        pid=nodeData.pid;
                        nodeLevel = nodeData.level;//当前选中节点在树上的级数
                        d_name = nodeData.text;
                        fNodeId = nodeData.id;
                    }
                });
            }
        });
    };
    get_equipmentTypetree();//生成设备型号树



	//添加场景表单验证
    var groupValidator = $("#add_Scence").validate({
	     rules: {
	    	 cjms_name: {
	             required: true,
	         }
	     },
	     messages: {
	    	 cjms_name: {
	             required: "场景模式不能为空",
	         }
	     },
	     submitHandler: function (form) {
	    	 add_Scence(form);
	     }
 	});

  	//新建场景保存
	function add_Scence(form) {
		//获取表头信息 #分开	1.
		var tabletd="";
		var le=$("#add_cjzlb").find("thead>tr>th");
		$(le).each(function(i){
			var th=$(this).text();
		    if(th == ''){
                th = $(this).find("input").val();
			}
			tabletd +=th+"#";
		});
		$("[name='pin_tabletd']").val(tabletd);
		//获取table内部信息	先循环tr 然后循环th
		var tr=$("#add_cjzlb").find("tbody>tr");
		var array=[];//将所有trtd信息放入数组	2.
		$(tr).each(function(i){
			var val_="";//每一行tr的值
			var th=$(this).find("th");
			$(th).each(function(i){
				var a=$(this).find(".appendval").val();
				if($(this).find(".appendval").is("input")){
                    a=$(this).find(".appendval").val();
                    if(i == 0){ // 第一次
                        // 先拼上 模块点的id 然后再拼后边的
                        var _aId = $(this).find(".appendval").attr("fsbid");
                        a = _aId + "#" + a ;
                    }
					if(a=='undefined'||a==''||a==null){
						a=0
					}
				}else if($(this).find(".appendval").is("select")){
					a=$(this).find(".appendval").find("option:selected").text();
				}
					val_ +=a+"#";
			});
			array.push(val_);
		});
		$("[name='pin_tabletrtd']").val(array);
		swal({
			title : "是否保存为公共模式",
			text : "",
			type : "warning",
			showCancelButton : true,
			confirmButtonColor : "#1ab394",
			confirmButtonText : "是(Y)",
			cancelButtonText : "否(N)",
			closeOnConfirm : false
		}, function(isConfirm) {
			if(isConfirm){//是	公有
				//先判断是控制场景还是采集场景
				if($("#add_cjtype").val()=='2'){//控制场景
					$("[name='mode_mslx']").val("1");//1 控制共有
				}else{
					$("[name='mode_mslx']").val("2");//2 采集共有
				}
				SaceScene(form);
            }else{//否
            	if($("#add_cjtype").val()=='2'){//控制场景
					$("[name='mode_mslx']").val("0");//1 控制私有
				}else{
					$("[name='mode_mslx']").val("3");//2 采集私有
				}
            	SaceScene(form);
            }
		});
	     return false;
 	}
	function SaceScene(form){
		$.issp_ajax({
		       url: _ctx + "/view/basedatamanage/eqmanage/addScene",
		       type: "post",
		       data:$(form).serialize(),
		       success: function(data) {
		         if (data.status == '1') {
		        	 swal(data.msg,"","success");
		        	 $('#modal-form-addScene').modal('hide');//关闭编辑窗口
		        	 //刷新静态电表树
		             basedatamanage_eqmanage_cjpz.Cjpz_tree(treeId);
		         } else{
		           swal( data.msg, "", "error");
		         }
		       }
		     });
	}
	
	//添加静态电表表单验证
    var groupValidator = $("#addFolder").validate({
	     rules: {
	    	 fName: {
	             required: true,
	         }
	     },
	     messages: {
	    	 fName: {
	             required: "文件夹名称不能为空",
	         }
	     },
	     submitHandler: function (form) {
	         addFolder(form);
	     }
 	});
	
	
 	//新建文件夹保存
	function addFolder(form) {
 		$.ajax({
	       url: _ctx + "/view/basedatamanage/eqmanage/addFolder",
	       type: "post",
	       data:$(form).serialize(),
	 		beforeSend: function () { 
	 			showLoad();	             
	 		},
	       success: function(data) {
	         if (data.status == '1') {
	        	 swal(data.msg,"","success");
	        	 $('#modal-form-addFolder').modal('hide');//关闭编辑窗口
	        	 //刷新静态电表树
	             basedatamanage_eqmanage_cjpz.Cjpz_tree(treeId);
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
	     return false;
 	}

 	//删除操作
	function delFolder(nodeId, f_type){
		//先判断后台判断f_type 进行删除操作 nodeId-场景id 直接删除场景
		$.issp_ajax({
			url : _ctx + "/view/basedatamanage/eqmanage/del_tree",
			type : "post",
			data : {"nodeId":nodeId,"f_type":f_type},
			success : function(returnObject) {
				if (returnObject.status == '1') {
                    basedatamanage_eqmanage_cjpz.Cjpz_tree(treeId);
                    swal({
			        	title : returnObject.msg,// 展示的标题
			   			type: "success",
			   			showCloseButton : false, // 展示关闭按钮
			   			allowOutsideClick : false,
			   			showConfirmButton : false,
			   			timer: 1000
			   		});					
				} else {
					swal(returnObject.msg, "", "error");
				}
			},
			error : function(returnObject) {
				swal(returnObject.msg, "", "error");
			}
		});
	}

 	return {
 		//数据对比
 		btnSjdb : function(){
 			var cjid=$("#add_Scence_nodeId").val();//场景id
 			$.ajax({
				url : _ctx + "/view/basedatamanage/eqmanage/CjDbsj",
				type : "post",
				data : {
					cjid : cjid
				},
				beforeSend: function () {
					showLoad();
				},
				success : function(result) {
					if(result.status=='1'){
						swal(result.msg, "", "success");
					}else{
						swal(result.msg, "", "error");
					}
				},
				complete: function () {
					hiddenLoad();
				},
				error : function(result) {
					swal(result.msg, "", "error");
				}
			});
 		},
 		//同步数据
 		btnTbsj : function(){
 			var cjid=$("#add_Scence_nodeId").val();
 			$.ajax({
				url : _ctx + "/view/basedatamanage/eqmanage/CjTbsj",
				type : "post",
				data : {
					cjid : cjid
				},
				beforeSend: function () {
					showLoad();
				},
				success : function(result) {
					if(result.status=='1'){
						swal(result.msg, "", "success");
					}else{
						swal(result.msg, "", "error");
					}
				},
				complete: function () {
					hiddenLoad();
				},
				error : function(result) {
					swal(result.msg, "", "error");
				}
			});
 		},
 		//弹窗树_保存	查询ip地址
 		saveBtnIsCjs : function(){
 			$.ajax({
				url : _ctx + "/view/basedatamanage/eqmanage/GetIPAddr",
				type : "post",
				data : {
					f_sys_name : $("#tree_id").val(),
					f_psys_name : pid,//父系统名称 
					nodeLevel : nodeLevel//选中节点在树上的级数(楼控系统为7，照明系统为5)
				},
				beforeSend: function () {
					showLoad();
				},
				success : function(result) {
					var td_id=$("#zlb_id").val();
					$("#"+td_id).val(d_name);
					$("#"+td_id).attr("fSbid",fNodeId);
					//根绝选择的点 判断是否合理
					if(result.hasOwnProperty("map")){

                        var map = result.map;
                        $("#add_d_IPAddr").val(map.F_IP_ADDR);
                        $("#add_d_Port").val(map.F_PORT);
                        $("#add_d_parentid").val(map.F_GUID);
					}
					$('#tree_equipment_cjs').treeview('clearSearch');//清除搜索选中高亮
				},
				complete: function () {
					hiddenLoad();
				},
				error : function(result) {
					swal(result.msg, "", "error");
				}
			});
 		},
 		//根据所选节点，加载拼装树
		childPageLoad : function(event, nodeData){//nodeData：所选择信息
 			$(".apdspan").empty();
 			$('#tree_cjpz').treeview('clearSearch');//清除搜索选中高亮 
 			_type = nodeData.nodeType;//所选节点类型
 			var id = nodeData.id;//节点id
 			var _text = nodeData.text;
 			var type = nodeData.nodeTreeId;//场景类型
 			
 			if(type=='0'){//代表根节点		权限 1.新建文件夹 2.新建控制场景 3.新建采集场景
 				$("#leftElectricPconf").find(".node-selected").append(
 						"<span  class='apdspan'>"+
 						// "<button class='btn sbtreeNodeBtn' value='新建文件夹' nodetype='1' onclick='basedatamanage_eqmanage_cjpz.btnMenuClick(this)'>新建文件夹</button>"+
 						// "<button class='btn sbtreeNodeBtn' value='新建控制场景' nodetype='2' onclick='basedatamanage_eqmanage_cjpz.btnMenuClick(this)'>新建控制场景</button>"+
 						// "<button class='btn sbtreeNodeBtn' value='新建采集场景' nodetype='3' onclick='basedatamanage_eqmanage_cjpz.btnMenuClick(this)'>新建采集场景</button>"+
 						"</span>"
 						);
 			}else if(type=='1'){//'1'代表文件夹  权限 1.新建文件夹 2.删除文件夹 3.新建控制场景 4.新建采集场景
 				$("#leftElectricPconf").find(".node-selected").append(
 						"<span  class='apdspan'>"+
 						// "<button class='btn sbtreeNodeBtn' value='新建文件夹' nodetype='1' onclick='basedatamanage_eqmanage_cjpz.btnMenuClick(this)'>新建文件夹</button>"+
 						// "<button class='btn sbtreeNodeBtn' value='删除文件夹' nodetype='4' onclick='basedatamanage_eqmanage_cjpz.btnMenuClick(this)'>删除文件夹</button>"+
 						"<button class='btn sbtreeNodeBtn' value='新建控制场景' nodetype='2' onclick='basedatamanage_eqmanage_cjpz.btnMenuClick(this)'>新建控制场景</button>"+
 						// "<button class='btn sbtreeNodeBtn' value='新建采集场景' nodetype='3' onclick='basedatamanage_eqmanage_cjpz.btnMenuClick(this)'>新建采集场景</button>"+
 						"</span>"
 						);
 			}else if (type=='2'||type=='3'){//'2'||'3' 代表控制场景||采集场景 	权限 1.删除
 				$("#leftElectricPconf").find(".node-selected").append("<span  class='apdspan'><button class='btn sbtreeNodeBtn' value='删除' nodetype='4' onclick='basedatamanage_eqmanage_cjpz.btnMenuClick(this)'>删除</button><span>");
 			}
 		},
 		//根据所选节点，加载不同页面
		RightPage : function(event, nodeData){//nodeData：所选择信息
 			var id = nodeData.id;//节点id
 			$.issp_ajax({
 		        type: "post",
 		        url: _ctx + "/view/basedatamanage/eqmanage/select_zonexq",
 		        data:{"id":id},
 		        success: function (result) {
 		        	//先清空
 		        	$("#add_Scence").find("input").val("");
 		        	$("#add_cjms").trigger("change",{ relatedTarget: "00" });
 		        	$("#add_cjzlb").find("thead").empty().append("<tr><th style='width:8vw;padding-left: 2.5vw;: center;'>点</th><th style='width:8vw;'>默认模式</th><th>值</th></tr>");
 	        		$("#add_cjzlb").find("tbody").empty();
 		        	if(result.hasOwnProperty("list")){
	 		        	for(var i=0;i<result.list.length;i++){
	 					 	var obj=result.list[i];
	 					 	RightDisabledCancel();//取消disabled
	 	 	        		$("#add_Scence_nodeId").val(obj.ID);//将指令id保存到隐藏域
	 					 	$("#add_xtmc").val(obj.NAME);
	 					 	$("#add_xtbm").val(obj.NICKNAME);
	 					 	$("#add_cjsm").val(obj.DESCRIPTION);
	 					 	$("#add_cjms").val(obj.F_ZONEMODE_ID);
	 					 	$("[name='fType']").val(obj.F_DYPE);
	 					 	$("[name='operateType']").val("U");//修改 U
	 					 	$("[name='cjms_name']").val(obj.MODENAME);
	 					 	var cjmsid = obj.F_ZONEMODE_ID;
// 	 					 	$("#add_cjms").trigger("change",{ relatedTarget: cjmsid });
	 					 	if(obj.ENABLED=='0'){//否
	 					 		$("#add_fEnabled_f").prop("checked",true);
	 					 	}else{//是
	 					 		$("#add_fEnabled_y").prop("checked",true);
	 					 	}
	 					 	$.issp_ajax({
	 				            type: "POST", 
	 				            url: '${ctx}/view/basedatamanage/eqmanage/select_cjmsxq', 
	 				            data:{"id": obj.F_ZONEMODE_ID},
	 				            success: function(data){
	 				                 if(data.hasOwnProperty("list")){
	 				                     for(var i=0;i<data.list.length;i++){//=后台根据#分割开 
	 				                        var obj=data.list[i];
	 				                        var tr=obj.tr;
	 				                        var nr=obj.theadtr;
	 				                        $("#add_cjzlb").find("thead").empty().append(tr);
	 				                        $("#add_cjzlb").find("tbody").empty().append(nr);
	 				                    }
	 				                 }
	 				            }
	 				        });
                            RightDisabled();// 附disabled
                            $("#cjpz_tbsj").removeAttr("disabled");
                            $("#cjpz_sjdb").removeAttr("disabled");
	 				 	}
 		        	}else{
 		        		RightDisabled();//附disabled
 		        	}
 		        }
 		    });
 		},
 		//拼装按钮点击事件
 		btnMenuClick : function(object) {
 		    // 先清空form表单
            $('#add_Scence')[0].reset();
            var nodeId = object.parentNode.parentNode.getAttribute("id");//根节点id
			var funName = object.getAttribute("value");	//名字	
			var f_type = object.getAttribute("nodeType");//f_type '0 代表根 1代表文件夹 2 代表 控制场景 3 代表采集场景'
			if (f_type == '4' ) {//删除操作
				if (treeSelNode[0].hasOwnProperty('nodes')) {//判断该节点是否含有子节点
					swal({
						title : "该节点下含有子节点，您确定要一起删除吗?",
						text : "信息删除后将不可恢复!",
						type : "warning",
						showCancelButton : true,
						confirmButtonColor : "#1ab394",
						confirmButtonText : "确定",
						closeOnConfirm : false
					}, function() {
						delFolder(nodeId, f_type);//删除节点和子节点
					});
				} else {
					swal({
						title : "您确定要删除吗?",
						text : "信息删除后将不可恢复!",
						type : "warning",
						showCancelButton : true,
						confirmButtonColor : "#1ab394",
						confirmButtonText : "确定",
						closeOnConfirm : false
					}, function() {
						delFolder(nodeId, f_type);//删除该节点
					});
				}
				return;
			};
			//新增	 判断 文件夹 和 场景
			if (f_type=='1') {//新增文件夹
				$("#addFolder_nodeId").val(nodeId);
				$("#modal-form-addFolder").modal('show');
			}else if(f_type=='2'||f_type=='3'){//新增场景
			    $("#add_Scence").find("input").val("");
				$("#add_Scence_nodeId").val(nodeId);
                $("#add_cjms").trigger("change",{ relatedTarget: "00" });
			    $("[name='operateType']").val("C");
                RightDisabledCancel();//取消disabled
                $("#cjpz_tbsj").attr("disabled","disabled");
                $("#cjpz_sjdb").attr("disabled","disabled");
				if(f_type=='2'){
					$("#add_cjlx").val("控制场景");
					$("#add_cjtype").val("2");//控制场景
					$("#add_cjzlb").find("thead").empty().append("<tr><th style='width: 5vw;padding-left: 2.5vw;: center;'>点</th><th style='width:8vw;'><input value='默认模式' style='width: 5vw;color: #555;' type='text'></th><th style='width: 8vw;'><input value='运行' style='width: 5vw;color: #555;' type='text'></th></tr>");
				}
			};
		},
		//指令表点击事件
		btnZldj	: function(object) {
			var id = object.getAttribute("id");	//名字
			$("#modal-equipment-type-cjs").modal("show");
			$("#zlb_id").val(id);
		},
 		//加载树
 		Cjpz_tree : function (selectId) {
 		    $.ajax({
 		        type: "post",
 		        url: _ctx + "/view/basedatamanage/eqmanage/cjpz_tree",
 		        dataType: "json",
 		        beforeSend: function () { 
 		    		showLoad();	             
 		    	},
 		        success: function (result) {
 		            //初始加载根节点
 		            if(result.hasOwnProperty("list")){//判断result返回结果是否包含list
 		            	if(result.list.length >0){//如果包含判断是否长度大于0
	 		            $('#tree_cjpz').treeview({
	 		                data: result.list,         // 数据源
	 		                highlightSelected: true,    //是否高亮选中
	 		                levels : 4,
	 		                enableLinks : true,//必须在节点属性给出href属性
	 		                color: "#4a4747",
	 		               onNodeSelected: function (event, nodeData) {
		            	   treeSelNode = $("#tree_cjpz").treeview("getSelected");//每次选中后改变全局选中设备树选中节点
	 		               $('#tree_cjpz').treeview('clearSearch');//清除搜索选中高亮
	 		               		treeId=nodeData.id;//每次加载附给treeId节点ID
	 		               		_ElectricPJs = nodeData.level;
	 		               		basedatamanage_eqmanage_cjpz.childPageLoad(event, nodeData);//默认加载拼装树
	 		               		//刷新右侧页面	获取树节点id获取数据
	 		               		basedatamanage_eqmanage_cjpz.RightPage(event, nodeData);//默认加载拼装树
	 		                }
	 		            });
			            var firstNode = $("#tree_cjpz").treeview('findNodes',[result.list[0].id,'id']);
			        	$("#tree_cjpz").treeview("selectNode", firstNode);//将第一个节点设置为选中状态
 		            	}
 		            }
 		        },
 		       complete: function () {
 		    		hiddenLoad();
 		    	},
 		        error: function (nodeData) {
 		            swal( nodeData.msg,"", "error");
 		        }
 		    });
 		},
 		pageInit : function(){
 			basedatamanage_eqmanage_cjpz.Cjpz_tree();
 		}
 		}

})(jQuery, window, document);
	basedatamanage_eqmanage_cjpz.pageInit();
</script>