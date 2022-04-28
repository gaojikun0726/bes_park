<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>综合能源管控平台</title>
    <meta name="keyword" content="">
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta name="Author" content="wujf">
    <meta name="copyright" content="山东正晨科技有限公司 All Rights Reserved">

	<script>
		var _ctx = '${ctx}';
	</script>
    <link rel="shortcut icon" href="${ctx}/favicon.ico"/>
    <#--<link rel="icon" href="${ctx}/static/images/top/logo.jpg" type="image/x-icon" />-->
    
    <!---首先加载  下面JS、CSS需要使用--->
    <script src="${ctx}/static/js/jquery/jquery-2.1.1.min.js"></script>
	<script src="${ctx}/static/js/jquery/jquery-ui-11.4/jquery-ui.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="${ctx}/static/js/plugins/bootstrap_select/bootstrap-select.js"></script>
	<link rel="stylesheet" href="${ctx}/static/css/plugins/checkbox/css/build.css"/><!-- 复选框用css -->
	<link rel="stylesheet" type="text/css" href="${ctx}/static/css/plugins/checkbox/css/default.css"><!-- 复选框用css -->
	
	<!-- Start add by yangjx at 21091021 for layUi-->
	<link href="${ctx}/static/layui/css/layui.css" rel="stylesheet">
	<!-- End add by yangjx at 21091021 -->

	<link href="${ctx}/static/cron/icon.css" rel="stylesheet" type="text/css" />
	<#--    <script src="${ctx}/static/js/cron/jquery-2.0.3.min.js"></script>-->
	<link href="${ctx}/static/cron/easyui.min.css" rel="stylesheet" type="text/css" />
<#--	<script src="${ctx}/static/layui/lay/modules/layer.js" type="text/javascript"></script>-->


	<link href="${ctx}/static/css/plugins/bootstrap_select/bootstrap-select.css" rel="stylesheet">
    <link href="${ctx}/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ctx}/static/fonts/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="${ctx}/static/css/plugins/toastr/toastr.min.css" rel="stylesheet">
    <link href="${ctx}/static/css/plugins/gritter/jquery.gritter.css" rel="stylesheet">
    <link href="${ctx}/static/css/plugins/multiselect/tree-multiselect.min.css" rel="stylesheet">
    <link href="${ctx}/static/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
    <link href="${ctx}/static/css/animate.css" rel="stylesheet">
    <link href="${ctx}/static/css/style.css" rel="stylesheet">
    <link href="${ctx}/static/css/ie_style.css" rel="stylesheet" >
    <link href="${ctx}/static/css/skinColour_white.css" rel="stylesheet" id="skinColour"><!--换肤-->
    <link href="${ctx}/static/css/tab_style.css" rel="stylesheet"><!---功能界面的多标签插件--->
    <link href="${ctx}/static/css/issp.css" rel="stylesheet">
    <link href="${ctx}/static/css/nth.tabs.min.css" rel="stylesheet"><!---多标签页加载页面的Tabs插件--->
    <link href="${ctx}/static/css/nav.css" rel="stylesheet">
    <link href="${ctx}/static/css/iconfont.css" rel="stylesheet">
    <link href="${ctx}/static/css/bootstrap-treeview.css" rel="stylesheet">
    <link href="${ctx}/static/css/plugins/datapicker/datepicker3.css" rel="stylesheet">
    <!--
    <link href="${ctx}/static/css/jquery-ui-11.4/jquery-ui.min.css" rel="stylesheet">-->
   	<link href="${ctx}/static/css/tabulator/tabulator_issp.css" rel="stylesheet">
   	<link href="${ctx}/static/css/baidumap.css" rel="stylesheet">
   	<link href="${ctx}/static/css/liMarquee.css" rel="stylesheet">
   	<link href="${ctx}/static/css/fixedThead/fixedThead.css" rel="stylesheet"><!--固定table表头和列 -->
   	<link href="${ctx}/static/css/plugins/fileinput/fileinput.min.css" rel="stylesheet"><!-- 上传插件 fileinput -->
    <link href="${ctx}/static/ztree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet">
    <script type="text/javascript" src="https://api.map.baidu.com/api?v=3.0&ak=OUkjXhrQaxMvQ0G1VlSMCXgIl2KXHZ0G&s=1"></script>

	<#--根据园区统一标准修改页面：样式css-->
    <link href="${ctx}/static/uniform/top.css" rel="stylesheet">

</head>

<body>

	<div id="main_index" style="height:100%" onbeforeunload="checkLeave()">
	 	<!---顶部状态栏 star-->
		<#include "/issp_top.ftl"/>
	    <!---顶部状态栏 end-->
    		 
	    <!----左侧导航开始----->
	    <div class="navissp" style="float:left;" id="leftMenu">
			<#include "/issp_left.ftl"/>
		</div>
	    <!----左侧导航结束----->
    	
	    <!---右侧内容区开始<div class="navissp" style="float:left" id="leftMenu"></div>---->
		<#include "/issp_content.ftl"/>
	    <!---右侧内容结束---->

		<#--wanghongjie 首页右上角报警图标点击弹框页面-->
		<#include "/view/basedatamanage/warningAndAlarm/waininginfo_top.ftl"/>
	</div>

	<div id="alarm_audio"></div>


	<!-- 全局 scripts -->
    <script src="${ctx}/static/js/Bootstrap/bootstrap.min.js"></script>
	<script src="${ctx}/static/js/jquery/jquery.scrollbar.min.js"></script>
	<script src="${ctx}/static/js/Bootstrap/bootstrap-table.js"></script>
	<script src="${ctx}/static/js/Bootstrap/bootstraptable-treegrid.js"></script>
	<script src="${ctx}/static/js/Bootstrap/bootstrap-treeview.js"></script>
	<script src="${ctx}/static/js/changeStyle.js"></script>   <!-- 换肤js -->
	<script src="${ctx}/static/js/jquery/jquery.cookie.js"></script>  <!--  -->
	    
	<script src="${ctx}/static/js/common.js"></script>
	<script src="${ctx}/static/js/plugins/pace/pace.min.js"></script>
	<script src="${ctx}/static/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
	<script src="${ctx}/static/js/plugins/metisMenu/jquery.metisMenu.js"></script>
	<script src="${ctx}/static/js/plugins/multiselect/tree-multiselect.min.js"></script>
	<script src="${ctx}/static/js/utility/GUID.js"></script>
	<script src="${ctx}/static/js/tabulator/bluebird.js"></script>
	<script src="${ctx}/static/js/tabulator/tabulator.js"></script>
    <script src="${ctx}/static/js/tabulator/v4.1/tabulator.js"></script>
	<!-- 插件 scripts -->
	<script src="${ctx}/static/js/plugins/chosen/chosen.jquery.js"></script>
	<script src="${ctx}/static/js/plugins/toastr/toastr.min.js"></script><!---顶部弹出提示--->
	<script src="${ctx}/static/js/plugins/sweetalert/sweetalert.min.js" ></script><!---对话框 alert--->
	<script src="${ctx}/static/js/plugins/validate/jquery.validate.min.js"></script>  <!---表单验证--->
	<script src="${ctx}/static/js/plugins/validate/validate-cn.js" ></script> <!---validate 自定义方法--->
	<script src="${ctx}/static/js/plugins/echarts/echarts.min.js"></script>
	<script src="${ctx}/static/js/plugins/echarts/chart/issp-bar.js"></script>
	<script src="${ctx}/static/js/plugins/echarts/chart/issp-line.js"></script>
	<script src="${ctx}/static/js/plugins/echarts/chart/issp-pie.js"></script>		
	<script src="${ctx}/static/js/plugins/echarts/chart/issp-gauge.js"></script>	
	<script src="${ctx}/static/js/plugins/echarts/chart/issp-tempgauge.js"></script>
	<script src="${ctx}/static/js/plugins/datapicker/bootstrap-datepicker.js"></script>
	<script src="${ctx}/static/js/nth.tabs.min.js"></script><!---多标签页加载页面的Tabs插件--->
	<#--不同皮肤下更换图标字体颜色-->
    <script src="${ctx}/static/js/chartFontColor.js"></script>
	<script src="${ctx}/static/js/ghee_new.js"></script>
	<script src="${ctx}/static/js/issp/issp.js"></script>
	<script src="${ctx}/static/js/jquery/jquery.liMarquee.js"></script>
	<script src="${ctx}/static/js/issp/issp-map.js"></script>
	<script src="${ctx}/static/js/issp/issp-helpcombobox.js"></script><!-- 检索帮助框 -->
	<script src="${ctx}/static/js/issp/issp-spinnerbox.js"></script><!-- 下拉列表 -->
	<script src="${ctx}/static/js/My97DatePicker/WdatePicker.js"></script><!-- 时间选择框 -->
	<script src="${ctx}/static/js/fixedThead/fixedThead.js"></script><!-- 固定table表头和列  -->
	<script src="${ctx}/static/js/Bootstrap/bootstrap-collapse.js"></script><!-- Bootstrap折叠插件  -->
	<script src="${ctx}/static/js/plugins/ajax/issp-ajax.js"></script><!-- ajax封装  -->
	<script src="${ctx}/static/js/public-js.js"></script><!-- 公共方法封装js-->
	<script src="${ctx}/static/js/plugins/fileinput/fileinput.js"></script><!--上传插件fileinput-->
    <script src="${ctx}/static/js/plugins/fileinput/fileinput_locale_zh.js"></script><!--上传插件fileinput中文化-->
    <script src="${ctx}/static/js/printThis/printThis.js"></script><!--打印插件-->
    <script src="${ctx}/static/layui/layui.all.js"></script><!-- Add by yangjx at 21091021 for layUI -->

    <script src="${ctx}/static/ztree/js/jquery.ztree.all.js"></script>
    <script src="${ctx}/static/js/utility/tree.js"></script><#--ztree 封装-->
    <script src="${ctx}/static/js/utility/form2js.js"></script><#-- 表单结构化插件 -->
    <script src="${ctx}/static/js/utility/utils.js"></script>
    <script src="${ctx}/static/js/websocket/webSocket.js"></script><#-- webSocket -->
    <script src="${ctx}/static/js/websocket/cmd.js"></script><#-- 下位机指令代码 -->
    <script src="${ctx}/static/js/websocket/webSocketHandle.js"></script><#-- webSocket handle -->
    <script src="${ctx}/static/js/buildTree.js"></script><#--树对象构建-->
    <script src="${ctx}/static/js/pubsub.js"></script><#--发布订阅-->
	<script src="${ctx}/static/js/FilePath/filePath.js"></script><#--文件下载路径-->

	<script src="${ctx}/static/js/flv/flv.js"></script>

	<#--关闭tab页之前保存设计模式-->
	<script src="${ctx}/static/pageDesign/module/tabClose.js"></script>
	<#--换肤时改变设计器的皮肤-->
    <script src="${ctx}/static/pageDesign/module/changeSkin.js"></script>
	<#--根据园区统一标准修改页面: 操作js-->
	<script src="${ctx}/static/uniform/top.js"></script>
	<script>

		/*layui.define(function(exports){ //提示：模块也可以依赖其它模块，如：layui.define('layer', callback);
			var obj = {
				hello: function(str){
					alert('Hello '+ (str||'opTable'));
				}
			};

			//输出test接口
			exports('opTable', obj);
		});*/
		layui.config({
			//  配置 layui 第三方扩展组件存放的基础目录
			base: 'static/opTable'
		}).extend({
			opTable: '/opTable'
		});

		$(document).ready(function () {
			//清空sessionStorage
	    	sessionStorage.clear();
	    	//加载左侧菜单
	    	isspGlobal.loadMenusFromIndex();
	    	//处理顶部模块菜单，当模块太多显示不开时增加左右移动按钮
	    	processTopModel();
	    	//登录或刷新页面时在前台获取缓存换肤信息
	    	js_changeStyle.cssStyle_skin();
	    	sessionStorage.setItem("EngineeringName","综合能源管控平台");
	    });
	    
		//刷新或退出网页时执行  
		function checkLeave(){
			//清空sessionStorage
			sessionStorage.clear();
		       //event.returnValue="确定离开当前页面吗？";
		}
		
		
		//var maxWidth=window.screen.width;
		 //var maxHeight=window.screen.height;
		 //根据页面的高度进行缩放
		//$("body").css({"height":maxHeight}); 
		//$("body").css({"width":maxWidth});
        var switchState = true;

        /*getData();
        setInterval(function () {
            getData()
        }, 5 * 1000);*/


        /*function getData(){
            $.ajax({
                url: "${ctx}/alarm/real",
                type: "post",
                success: function(result) {
                    if(result.status === "1"){

                        var list = result.list || [];

                        if($("#besWainingRealTable").length === 1){

                            if(list.length <= 0){
                                $("#besWainingRealTable").tabulator("clearData");
                                $("#besWainingRealTable").tabulator("addRow",{fInitVal:"暂无数据"});
                                return;
                            }

                            $("#besWainingRealTable").tabulator("setData", list);
						}

						if(switchState === true && list.length > 0){

                            if($('#alarm_audio').html()){
								return;
							}

							$('#alarm_audio').html(
                                                   '      <audio id="alarm_audio" autoplay="autoplay" loop="loop">\n' +
                                                   '            <source src="static/audio/alarm.mp3" type="audio/mpeg">\n' +
                                                   '        </audio>')
						}
                    }
                },
                error: function(data) {
                    swal("", data.msg, "error");
                }
            });
        }*/


	</script>	
	
</body>
</html>