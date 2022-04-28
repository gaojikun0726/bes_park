<style>
/* 左侧树结构 */
.left_tree {
	/*     margin: 10px 0 0 11px; */
	position: fixed;
	width: 15%;
	z-index: 980;
	transition: all .5s ease;
	user-select: none;
	transition: all .3s ease;
	overflow-y: auto;
	overflow-x: auto;
	height: 100%;
	border-bottom: 1px solid #366c90;
}

.monitoringhome_zzjgtree {
	width: 30px;
	height: 8em;
	padding-top: 17px;
	cursor: pointer;
	position: absolute;
	top: 50%;
	margin-top: -66px;
	z-index: 980;
	font-size: 14px;
	color: #fff;
	-webkit-border-radius: 15px 0px 0px 15px;
	-moz-border-radius: 15px 0px 0px 15px;
	-o-border-radius: 15px 0px 0px 15px;
	border-radius: 0px 10px 10px 0px;
}

.monitoringhome_zzjgtree>span {
	display: block;
	padding-left: 4px;
}

.map-border {
	border: 1px solid #366c90;
	border-left: 0px;
	margin: 0px;
}
</style>

<div style="width: 100%; height: 100%;">
	<!-----左侧区域---->
	<div class="cross_url" style="width: 75%; height: 100%; float: left; background: url(static/images/zzjg/sqsj.jpg) no-repeat -0px 0; background-size: 100% 100%;">
		<div class="map-border" style="width: 100%; height: 100%;">
			<!--左侧树结构 -->
			<div class="monitoringhome_zzjgtree">
				<span>组</span><span>织</span><span>结</span><span>构</span>
			</div>
			<div class="left_tree">
				<div id="tree_zzjg_CM" class="tree_zzjg_CM"></div>
			</div>
		</div>
	</div>
	<!--右侧区域 -->
	<div id="main-index-cross_right" style="width: 25%; height: 100%; float: right;"></div>
</div>

<script type="text/javascript">
//点击 展开/收起
	var integration_image = "static/images/zzjg/sqsj.jpg";//默认图片
	var left_cols_mark = 0 ;
	$(function() {
	    zzjg_tree();
	    $(".left_tree").hide();
	    $(".monitoringhome_zzjgtree").click(function() {
	        if (left_cols_mark == 1) { //隐藏
	            left_cols_mark = 0;
	            $(".left_tree").hide();
	            $(".monitoringhome_zzjgtree").css("margin-left", "");
	            $(".cross_url").css("background", "url("+integration_image+") no-repeat 0 0");
	            $(".cross_url").css("background-size", "100% 100%")
	        } else { //展开
	            left_cols_mark = 1;
	            $(".left_tree").show();
	            $(".monitoringhome_zzjgtree").css("margin-left", "15%");
	            $(".cross_url").css("background", "url("+integration_image+") no-repeat 100% 0");
	            $(".cross_url").css("background-size", "calc(100% - 15%) 100%")
	        }
	    });
	})
	//加载树   组织机构
	function zzjg_tree() {
	    $.ajax({
	        type: "post",
	        url: "${ctx}/view/basedatamanage/beseqmanage/Gztzzjg_tree",
	        dataType: "json",
	        beforeSend: function() {
	            showLoad();
	        },
	        success: function(result) {
	            //初始加载根节点
	            if (result.hasOwnProperty("list")) { //判断result返回结果是否包含list
	                if (result.list.length > 0) { //如果包含判断是否长度大于0
	                    $('#tree_zzjg_CM').treeview({
	                        data: result.list,
	                        // 数据源
	                        highlightSelected: true,
	                        //是否高亮选中
	                        levels: 4,
	                        enableLinks: true,
	                        //必须在节点属性给出href属性
	                        color: "#4a4747",
	                        onNodeSelected: function(event, nodeData) {
	                        	integration_image = nodeData.image;
                                onNodeSelected(); //点击事件
                                refreshTabulator(nodeData.id);//刷新tabulator
	                        }
	                    });
	                    var firstNode = $("#tree_zzjg_CM").treeview('findNodes', [result.list[0].id, 'id']);
	                    $("#tree_zzjg_CM").treeview("selectNode", firstNode); //将第一个节点设置为选中状态
	                }
	            }
	        },
	        complete: function() {
	            hiddenLoad();
	        },
	        error: function(nodeData) {
	            swal(nodeData.msg, "", "error");
	        }
	    });
	}
	
	//组织结构onNodeSelected事件
    function onNodeSelected() {
        //根据左侧树移动 全体右移
        if (integration_image == 'undefined' || integration_image == '' || integration_image == null) {
            integration_image = "url(static/images/zzjg/sqsj.jpg)";
        }
        $(".cross_url").css({
            "background": "url(" + integration_image + ") no-repeat 0 0",
            "background-size": "100% 100%"
        });
        if (left_cols_mark == 1) { //开
            $("").css({
                "width": "75.5%",
                "float": "right"
            });
        } else {
            $("").css("width", "100%");
            $("").removeAttr("float");
        }
    }
	//刷新右侧tabulator
	function refreshTabulator(nodeId){
		 $.ajax({
	            type: "post",
	            url: "${ctx}/view/basedatamanage/workbench/getWorkbenchTable",
	            dataType: "json",
	            data:{
	            	f_gztzzjg_id:nodeId,
	            },
	            beforeSend: function() {
	                showLoad();
	            },
	            success: function(result) {
	            	if(result.hasOwnProperty("list")){
		            	var obj = result.list;
		            	$("#main-index-cross_right").tabulator("setData",obj);
	            	}else{
	            		$("#main-index-cross_right").tabulator("setData",[]);
	            		$("#main-index-cross_right").tabulator("addRow",{f_name:"暂无数据"});            		
	            	}
	            },
	            complete: function() {
	                hiddenLoad();
	            },
	            error: function(nodeData) {
	                swal(nodeData.msg, "", "error");
	            }
	        });
	}
	//创建并设置table属性
    $("#main-index-cross_right").tabulator({
        height:"100%",
        layout:"fitColumns",//fitColumns  fitDataFill
        columnVertAlign:"bottom", //align header contents to bottom of cell
        tooltips:false,
        movableColumns:true,
        columns:[
        {title:"设备名称", field:"f_name" ,sorter:"string",editor:false,align:"center",headerSort:false}, //never hide this column
        {title:"设备状态", field:"f_type" ,sorter:"string",editor:false,align:"center",headerSort:false}, //hide this column first 
        {title:"设备描述", field:"f_description" ,sorter:"string",editor:false,align:"center",headerSort:false},
        ],
    });

</script>