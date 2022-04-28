<link href="${ctx}/static/css/jsk_jk.css" rel="stylesheet">
<!-- 左侧设备树start -->
<div id="jsk_jk_div" class="leftarea information_left">
	<div class="information-model">
		<span class="tree_Subtitle"> <i class="fa fa-share-alt"
										aria-hidden="true"></i>&nbsp;请选择监控位置>>>
		</span>
	</div>
	<div id="jskjk_sbdy_tree" class="Information_area"></div>
</div>
<!-- 左侧设备树end -->
<!-- 右侧监控界面Start -->
<div class="information_right">
	<div class="information_size">
		<div class="information-model">
			<span class="Subtitle"> <i class="fa fa-th-list"
									   aria-hidden="true"></i>&nbsp;监控界面>>>
			</span>
		</div>
		<div id="jsk_jk_page" style="height:96%;width:100%"></div>

	</div>
</div>
<!-- 右侧监控界面End -->


<!-- 模式 模态框start -->
<div class="modal fade" id="modal-form-jsk_jk" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">       
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp;集水坑调试</h4>
            </div>
            
            <div class="modal-body">
                <form role="form" id="debugms" name="debugms" class="form-horizontal">
                    <div class="form-group">
                        <label style="width:35%" class="col-sm-3 control-label"><span>泵1启停</span>
                            <div class="col-sm-8" style="float: right;">
                                <select id="jsk_f_kgj" onchange="dataAnalysis_jsk_jk.setPointSelect(this)">
                                    <option value="255">开机</option>
                                    <option value="0">关机</option>
                                </select>
                            </div>
                        </label>
                        <label style="width:33%" class="col-sm-3 control-label"><span>运行状态:</span>
                            <span id="jsk_yxzt"></span>
                        </label>
                    </div>
                    <div class="form-group">
                        <label style="width:35%" class="col-sm-3 control-label"><span>泵2启停</span>
                            <div class="col-sm-8" style="float: right;">
                                <select id="jsk_f_ms" onchange="dataAnalysis_jsk_jk.setPointSelect(this)">
                                    <option value="255">开机</option>
                                    <option value="0">关机</option>
                                </select>
                            </div>
                        </label>
                        <label style="width:33%" class="col-sm-3 control-label"><span>故障状态:</span>
                            <span id="jsk_gzzt"></span>
                        </label>
                    </div>
                   <div class="form-group m-t-sm" >
                        <div class="col-sm-6 col-sm-push-4 display_flex">
                            <button class="btn btn-white m-l-sm"  type="button" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </form>
            </div>
            
        </div>
    </div>
</div>
<!-- 模式 模态框end -->

<script type="text/javascript">
	;
	var dataAnalysis_jsk_jk = (function($, window, document, undefined) {
		var _ctx = '${ctx}';
		var selected_treeview = null;//treeview上选中的节点
		var nodePage = "";//节点对应的页面
		var editId = "";
        var clock = window.setInterval("dataAnalysis_jsk_jk.refresh()",4000);
        var pageIsHide = false; // 记录页面是否是隐藏状态
        var ddcNames = "";
        var runState = true;
        var ddcList = [];
        var checkedDevices = "";

		$(function() {
			//配置楼宇tree
			var treeList = new Array();
			var treechildList1 = new Array();
			treechildList1.push(
                {nodeTreeId:"jskjk_01",ddc: "DDC_B1_1_JSK1", pid:"jskjk",text:"设备间分区1",id:"DDC_B1_1_JSK1_M100,DDC_B1_1_JSK1_M101,DDC_B1_1_JSK1_M109,DDC_B1_1_JSK1_M1010",level:"1",leaf:true,nodePage:"sbjfq1",},
                {nodeTreeId:"jskjk_02",ddc: "DDC_B1_2_JSK3", pid:"jskjk",text:"设备间分区2",id:"DDC_B1_2_JSK3_M100,DDC_B1_2_JSK3_M101,DDC_B1_2_JSK3_M109,DDC_B1_2_JSK3_M1010",level:"1",leaf:true,nodePage:"sbjfq2",},
                {nodeTreeId:"jskjk_03",ddc: "DDC_B1_3_JSK2", pid:"jskjk",text:"设备间分区3",id:"DDC_B1_3_JSK2_M100,DDC_B1_3_JSK2_M101,DDC_B1_3_JSK2_M109,DDC_B1_3_JSK2_M1010",level:"1",leaf:true,nodePage:"sbjfq3",},
                {nodeTreeId:"jskjk_04",ddc: "DDC_B1_4_JSK2", pid:"jskjk",text:"设备间分区4",id:"DDC_B1_4_JSK2_M100,DDC_B1_4_JSK2_M101,DDC_B1_4_JSK2_M109,DDC_B1_4_JSK2_M1010",level:"1",leaf:true,nodePage:"sbjfq4",},
                {nodeTreeId:"jskjk_05",ddc: "", pid:"jskjk",text:"设备间分区5",id:"jskjk_05",level:"1",leaf:true,nodePage:"sbjfq5",},
                {nodeTreeId:"jskjk_06",ddc: "DDC_B1_6_JSK3,DDC_B1_6_JSK4", pid:"jskjk",text:"设备间分区6",id:"DDC_B1_6_JSK3_M100,DDC_B1_6_JSK3_M101,DDC_B1_6_JSK3_M109,DDC_B1_6_JSK3_M1010,DDC_B1_6_JSK4_M100,DDC_B1_6_JSK4_M101,DDC_B1_6_JSK4_M109,DDC_B1_6_JSK4_M1010",level:"1",leaf:true,nodePage:"sbjfq6",},
				{nodeTreeId:"jskjk_07",ddc: "DDC_B1_7_JSK1,DDC_B1_7_JSK2,DDC_B1_7_JSK3", pid:"jskjk",text:"设备间分区7",id:"DDC_B1_7_JSK1_M100,DDC_B1_7_JSK1_M101,DDC_B1_7_JSK1_M109,DDC_B1_7_JSK1_M1010,DDC_B1_7_JSK2_M100,DDC_B1_7_JSK2_M101,DDC_B1_7_JSK2_M109,DDC_B1_7_JSK2_M1010,DDC_B1_7_JSK3_M100,DDC_B1_7_JSK3_M101,DDC_B1_7_JSK3_M109,DDC_B1_7_JSK3_M1010",level:"1",leaf:true,nodePage:"sbjfq7",},
                {nodeTreeId:"jskjk_08",ddc: "DDC_B1_8_JSK1,DDC_B1_8_JSK2,DDC_B1_8_JSK3,DDC_B1_8_JSK4", pid:"jskjk",text:"设备间分区8",id:"DDC_B1_8_JSK1_M100,DDC_B1_8_JSK1_M101,DDC_B1_8_JSK1_M109,DDC_B1_8_JSK1_M1010,DDC_B1_8_JSK2_M100,DDC_B1_8_JSK2_M101,DDC_B1_8_JSK2_M109,DDC_B1_8_JSK2_M1010,DDC_B1_8_JSK3_M100,DDC_B1_8_JSK3_M101,DDC_B1_8_JSK3_M109,DDC_B1_8_JSK3_M1010,DDC_B1_8_JSK4_M100,DDC_B1_8_JSK4_M101,DDC_B1_8_JSK4_M109,DDC_B1_8_JSK4_M1010",level:"1",leaf:true,nodePage:"sbjfq8",},
                {nodeTreeId:"jskjk_09",ddc: "DDC_B1_9_JSK1,DDC_B1_9_JSK2,DDC_B1_9_JSK3,DDC_B1_9_JSK4,DDC_B1_9_JSK5,DDC_B1_9_JSK6", pid:"jskjk",text:"设备间分区9",id:"DDC_B1_9_JSK1_M100,DDC_B1_9_JSK1_M101,DDC_B1_9_JSK1_M109,DDC_B1_9_JSK1_M1010,DDC_B1_9_JSK2_M100,DDC_B1_9_JSK2_M101,DDC_B1_9_JSK2_M109,DDC_B1_9_JSK2_M1010,DDC_B1_9_JSK3_M100,DDC_B1_9_JSK3_M101,DDC_B1_9_JSK3_M109,DDC_B1_9_JSK3_M1010,DDC_B1_9_JSK4_M100,DDC_B1_9_JSK4_M101,DDC_B1_9_JSK4_M109,DDC_B1_9_JSK4_M1010,DDC_B1_9_JSK5_M100,DDC_B1_9_JSK5_M101,DDC_B1_9_JSK5_M109,DDC_B1_9_JSK5_M1010,DDC_B1_9_JSK6_M100,DDC_B1_9_JSK6_M101,DDC_B1_9_JSK6_M109,DDC_B1_9_JSK6_M1010",level:"1",leaf:true,nodePage:"sbjfq9",},
                {nodeTreeId:"jskjk_10",ddc: "DDC_B1_10_JSK1,DDC_B1_10_JSK2,DDC_B1_10_JSK3", pid:"jskjk",text:"设备间分区10",id:"DDC_B1_10_JSK1_M100,DDC_B1_10_JSK1_M101,DDC_B1_10_JSK1_M109,DDC_B1_10_JSK1_M1010,DDC_B1_10_JSK2_M100,DDC_B1_10_JSK2_M101,DDC_B1_10_JSK2_M109,DDC_B1_10_JSK2_M1010,DDC_B1_10_JSK3_M100,DDC_B1_10_JSK3_M101,DDC_B1_10_JSK3_M109,DDC_B1_10_JSK3_M1010",level:"1",leaf:true,nodePage:"sbjfq10",},
                {nodeTreeId:"jskjk_11",ddc: "DDC_B1_11_JSK1", pid:"jskjk",text:"设备间分区11",id:"DDC_B1_11_JSK1_M100,DDC_B1_11_JSK1_M101,DDC_B1_11_JSK1_M109,DDC_B1_11_JSK1_M1010",level:"1",leaf:true,nodePage:"sbjfq11",},
                {nodeTreeId:"jskjk_12",ddc: "DDC_B1_12_JSK1,DDC_B1_12_JSK2", pid:"jskjk",text:"设备间分区12",id:"DDC_B1_12_JSK1_M100,DDC_B1_12_JSK1_M101,DDC_B1_12_JSK1_M109,DDC_B1_12_JSK1_M1010,DDC_B1_12_JSK2_M100,DDC_B1_12_JSK2_M101,DDC_B1_12_JSK2_M109,DDC_B1_12_JSK2_M1010",level:"1",leaf:true,nodePage:"sbjfq12",},
                {nodeTreeId:"jskjk_13",ddc: "DDC_B1_13_JSK1,DDC_B1_13_JSK2,DDC_B1_13_JSK3,DDC_B1_13_JSK4,DDC_B1_13_JSK5,DDC_B1_13_JSK6,DDC_B1_13_JSK7,DDC_B1_13_JSK8,DDC_B1_13_JSK9", pid:"jskjk",text:"设备间分区13",id:"DDC_B1_13_JSK1_M100,DDC_B1_13_JSK1_M101,DDC_B1_13_JSK1_M109,DDC_B1_13_JSK1_M1010,DDC_B1_13_JSK2_M100,DDC_B1_13_JSK2_M101,DDC_B1_13_JSK2_M109,DDC_B1_13_JSK2_M1010,DDC_B1_13_JSK3_M100,DDC_B1_13_JSK3_M101,DDC_B1_13_JSK3_M109,DDC_B1_13_JSK3_M1010,DDC_B1_13_JSK4_M100,DDC_B1_13_JSK4_M101,DDC_B1_13_JSK4_M109,DDC_B1_13_JSK4_M1010,DDC_B1_13_JSK5_M100,DDC_B1_13_JSK5_M101,DDC_B1_13_JSK5_M109,DDC_B1_13_JSK5_M1010,DDC_B1_13_JSK6_M100,DDC_B1_13_JSK6_M101,DDC_B1_13_JSK6_M109,DDC_B1_13_JSK6_M1010,DDC_B1_13_JSK7_M100,DDC_B1_13_JSK7_M101,DDC_B1_13_JSK7_M109,DDC_B1_13_JSK7_M1010,DDC_B1_13_JSK8_M100,DDC_B1_13_JSK8_M101,DDC_B1_13_JSK8_M109,DDC_B1_13_JSK8_M1010,DDC_B1_13_JSK9_M100,DDC_B1_13_JSK9_M101,DDC_B1_13_JSK9_M109,DDC_B1_13_JSK9_M1010",level:"1",leaf:true,nodePage:"sbjfq13",},
                {nodeTreeId:"jskjk_14",ddc: "DDC_B1_14_JSK1,DDC_B1_14_JSK2", pid:"jskjk",text:"设备间分区14",id:"DDC_B1_14_JSK1_M100,DDC_B1_14_JSK1_M101,DDC_B1_14_JSK1_M109,DDC_B1_14_JSK1_M1010,DDC_B1_14_JSK2_M100,DDC_B1_14_JSK2_M101,DDC_B1_14_JSK2_M109,DDC_B1_14_JSK2_M1010",level:"1",leaf:true,nodePage:"sbjfq14",}

			);

			treeList.push(
			    {nodeTreeId:"jskjk",pid:"",text:"集水坑监控",id:"jskjk",rootId:"jskjk",leaf:false,nodes:treechildList1}
			);

			$('#jskjk_sbdy_tree').treeview({
				data : treeList, // 数据源
				highlightSelected : true, //是否高亮选中
				levels : 2,
				enableLinks : true,//必须在节点属性给出href属性
				wrapNodeText : true,
				showImage : false,
				color : "#4a4747",
				onNodeSelected : function(event, nodeData) {//节点选中事件
                    ddcNames = nodeData.ddc;
					nodePage = nodeData.nodePage;
					ids = nodeData.id;
					//加载节点对应的监控界面
					loadJkPage(nodePage, nodeData.id);
					selected_treeview = $('#jskjk_sbdy_tree').treeview('getSelected');
				}
			});
			if(selected_treeview == null){
				var firstNode = $("#jskjk_sbdy_tree").treeview('findNodes',[treeList[0].nodes[0].id,'id']);
				$("#jskjk_sbdy_tree").treeview("selectNode", firstNode);//将第一个节点设置为选中状态
                ddcNames = treeList[0].nodes[0].ddc;
                //getRvscanData();
			}
		});

		function loadJkPage(nodePage, id){

		    if(!nodePage){
                return;
            }

			var variableUrl = nodePage;
			$.issp_ajax({//加载树节点信息
				isShowLoader : false,
				type : "post",
				url : _ctx + "/view/realtimemonitoring/BESJskjk/" + variableUrl,
				success : function(returnObject) {
					$("#jsk_jk_page").html(returnObject);
                    dataAnalysis_jsk_jk.refreshIcon(id);
				},
				error : function(returnObject) {
					swal(returnObject.msg, "", "error");
				}
			});
		}
		
		
		  //温控监控模态框 
        $("#modal-form-jsk_jk").on('show.bs.modal', function(event) {
            //垂直居中显示
            $(this).css('display', 'block');
            var modalHeight=($(window).height()/2) - ($('#modal-form-jsk_jk .modal-dialog').height()/2);
            $(this).find('.modal-dialog').css('margin-top', modalHeight);
            //模态拖动
            $(this).draggable({
                handle: ".modal-header"     	// 只能点击头部拖动
            });
            $(this).css("overflow", "hidden"); // 防止出现滚动条，出现的话，你会把滚动条一起拖着走的
            
        });
		  
        //关闭编辑模态框清空表单值
	    $("#modal-form-jsk_jk").on('hidden.bs.modal', function (event) {
	        $(this).find("input").val("");
	        $('#jsk_f_kgj').val('255');
	        $('#jsk_f_ms').val('255');
	        $('#jsk_yxzt').html('');
	        $('#jsk_gzzt').html('');
	        setjsk_jkInfoFormValidator.resetForm();
	    });
        
	    var setjsk_jkInfoFormValidator = $("#modal-form-jsk_jk").validate({
            submitHandler: function (form) {
            	//setjsk_jkData();
            }
        });


     /*   var timer = setInterval(function () {

            var el = $('#jsk_jk_page');

            if(el.length <= 0){
                pageIsHide = true;
                clearInterval(timer);
                return;
            }

            var current = el.is(":hidden");

            if(current === false && pageIsHide === true && runState === false){
                pageIsHide = current;
                getRvscanData();
            }

            pageIsHide = current;

        }, 2000);

        function getRvscanData () {

            runState = true;

            if(pageIsHide){
                runState = false;
                return;
            }

            if(!ddcNames || ddcNames.trim() === ''){
                setTimeout(function () {
                    getRvscanData();
                }, 10000)

                return;
            }

            $.ajax({//加载树节点信息
                type: "post",
                url: _ctx + "/view/basedatamanage/eqmanage/getRvscanData",
                data: {
                    sysName: ddcNames
                },
                success: function (result) {

                    if(result.status === '1'){
                        getRvscanData();

                        var data = result.data;

                        if(Array.isArray(data)){
                            ddcList = data;
                            if(!$('#modal-form-jsk_jk').is(':hidden')){
                                formAssig();
                            }
                        }
                    }else {
                        setTimeout(function () {
                            getRvscanData();
                        }, 10000)
                    }
                }
            })

        }*/

        function formAssig() {

            if(ddcList.length <= 0 || checkedDevices === ''){
                return;
            }

            for(var i =0; i < ddcList.length; i++){

                var item = ddcList[i];

                if(item.f_sys_name === checkedDevices + '00'){
                    $('#jsk_f_kgj').val(item.f_init_val);
                }else if(item.f_sys_name === checkedDevices + '01'){
                    $('#jsk_f_ms').val(item.f_init_val);
                }else if(item.f_sys_name === checkedDevices + '09'){
                    $('#jsk_yxzt').text((item.f_init_val==0)?'正常':'异常');
                }else if(item.f_sys_name === checkedDevices + '010'){
                    $('#jsk_gzzt').text((item.f_init_val==0)?'正常':'异常');
                }

            }
        }


        return {
			
			//加载模态框
			loadjsk_jkInfo:function(obj){

				editId =obj.id;
                checkedDevices = editId;
                formAssig();

                $("#jsk_f_kgj").attr("ids",editId+"00");// 开关机赋id
                $("#jsk_f_ms").attr("ids",editId+"01");// 模式赋id
				$("#modal-form-jsk_jk").modal('show');

				/*// 查询 回显
                $.issp_ajax({//加载树节点信息
                    type : "post",
                    data:{
                        sysName: editId
                    },
                    url : _ctx + "/view/basedatamanage/eqmanage/getWkjkPointInfoBySysName",
                    success : function(returnObject) {
                        if(returnObject.status == '1'){
                            var updateInfo = returnObject.data;
                            if(updateInfo != null && (updateInfo.length != null && updateInfo.length != "")){
                                for (var i = 0; i < updateInfo.length; i++) {
                                    if(updateInfo[i].F_NICK_NAME == "B1QT"){//开关机
                                        $('#jsk_f_kgj').val(updateInfo[i].F_INIT_VAL);
                                    }else if(updateInfo[i].F_NICK_NAME == "B2QT"){//模式
                                        $('#jsk_f_ms').val(updateInfo[i].F_INIT_VAL);
                                    }else if(updateInfo[i].F_NICK_NAME == "YXZT"){//运行状态
                                        $('#jsk_yxzt').text((updateInfo[i].F_INIT_VAL==0)?'正常':'异常');
                                    }else if(updateInfo[i].F_NICK_NAME == "GZZT"){//故障状态
                                        $('#jsk_gzzt').text((updateInfo[i].F_INIT_VAL==0)?'正常':'异常');
                                    }
                                    /!*else if(updateInfo[i].F_NICK_NAME == "SNWD"){//室内温度
                                        $('#ms_f_snwd').val(updateInfo[i].F_INIT_VAL);
                                    }else if(updateInfo[i].F_NICK_NAME == "SDWD"){//设定温度
                                        $('#ms_f_sdwd').val(updateInfo[i].F_INIT_VAL);
                                    }*!/
                                }
                            }
                        }

                    },
                    error : function(returnObject) {
                        swal(returnObject.msg, "", "error");
                    },
                });*/
				
			},
            // 温控监控select事件
            setPointSelect: function(obj){
                var f_init_val = "";// 值
                var f_work_mode =  "1";// 手动1  自动0
                var f_sys_name = obj.attributes[2].nodeValue;
                $.ajax({
                    url : _ctx + "/api/debugPointInfo",
                    type : "post",
                    contentType : "application/json; charset=utf-8",
                    dataType : "json",
                    data : JSON.stringify({
                        f_sys_name : f_sys_name,
                        f_work_mode : f_work_mode,
                        f_init_val : f_init_val,
                        f_node_attribution : "1",
                    }),
                    success : function(result) {
                        swal("操作成功", "", "success");
                    },
                    error : function(result) {
                        swal("操作失败", "", "error");
                    }
                });
            },
            refreshIcon: function(id){
                $.issp_ajax({//加载树节点信息
                    type : "post",
                    data:{
                        sysName: id
                    },
                    url : _ctx + "/view/basedatamanage/eqmanage/sbdy_treeIconForJKById",
                    success : function(returnObject) {
                        // if(returnObject.status == '1'){
                        // 	var updateInfo = returnObject.data;
                        ddcList = returnObject.list || [];


                    },
                    error : function(returnObject) {
                        swal(returnObject.msg, "", "error");
                    },
                });
            },
            //刷新开关状态
            refresh : function() {
                var el = $('#jsk_jk_page');
                if(el.length <= 0){
                    clearInterval(clock);
                    return;
                }
                    //加载树节点信息
                $.issp_ajax({
                    type : "post",
                    url : _ctx + "/view/basedatamanage/eqmanage/sbdy_treeIconForJKById",
                    data:{
                        sysName: ids
                    },
                    isShowLoader : false,
                    success : function(returnObject) {
                        if(returnObject.hasOwnProperty('list')){
                            ddcList = returnObject.list || [];
                        }
                    },
                    error : function(returnObject) {
                        swal(returnObject.msg, "", "error");
                    }
                });

            }
		}
	})(jQuery, window, document);
	//dataAnalysis_jsk_jk.pageInit();
</script>