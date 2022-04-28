<#--<!DOCTYPE html>-->
<#--<html lang="en">-->
<script>
    var _ctx = '${ctx}';
</script>

<#--<link rel="icon" href="${ctx}/static/images/logo.png" type="image/x-icon" />-->

<!---首先加载  下面JS、CSS需要使用--->
<script src="${ctx}/static/js/jquery/jquery-2.1.1.min.js"></script>
<link href="${ctx}/static/css/jquery-ui-11.4/jquery-ui.min.css" rel="stylesheet">
<script src="${ctx}/static/js/jquery/jquery-ui-11.4/jquery-ui.min.js" type="text/javascript"></script>
<#--<script type="text/javascript" src="${ctx}/static/js/plugins/bootstrap_select/bootstrap-select.js"></script>-->
<#--<link rel="stylesheet" href="${ctx}/static/css/plugins/checkbox/css/build.css"/><!-- 复选框用css &ndash;&gt;-->
<#--<link rel="stylesheet" type="text/css" href="${ctx}/static/css/plugins/checkbox/css/default.css"><!-- 复选框用css &ndash;&gt;-->

<!-- Start add by yangjx at 21091021 for layUi-->
<link href="${ctx}/static/layui/css/layui.css" rel="stylesheet">
<script src="${ctx}/static/layui/layui.all.js"></script><!-- Add by yangjx at 21091021 for layUI -->
<!-- End add by yangjx at 21091021 -->

<#--<link href="${ctx}/static/css/plugins/bootstrap_select/bootstrap-select.css" rel="stylesheet">-->
<link href="${ctx}/static/css/bootstrap.min.css" rel="stylesheet">
<script src="${ctx}/static/js/Bootstrap/bootstrap.min.js"></script>
<link href="${ctx}/static/fonts/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<#--<link href="${ctx}/static/css/plugins/toastr/toastr.min.css" rel="stylesheet">-->
<#--<link href="${ctx}/static/css/plugins/gritter/jquery.gritter.css" rel="stylesheet">-->
<#--<link href="${ctx}/static/css/plugins/multiselect/tree-multiselect.min.css" rel="stylesheet">-->
<#--<link href="${ctx}/static/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">-->
<link href="${ctx}/static/css/animate.css" rel="stylesheet">
<link href="${ctx}/static/css/style.css" rel="stylesheet">
<link href="${ctx}/static/css/ie_style.css" rel="stylesheet" >
<link href="${ctx}/static/css/skinColour_${Session.skinColor!'white'}.css" rel="stylesheet" id="skinColour"><!--换肤-->
<#--tab_style.css影响ztree checkbox复选框模式下的样式(造成节点重叠)-->
<#--<link href="${ctx}/static/css/tab_style.css" rel="stylesheet"><!---功能界面的多标签插件-&ndash;&gt;-->
<link href="${ctx}/static/css/issp.css" rel="stylesheet">
<#--<link href="${ctx}/static/css/nth.tabs.min.css" rel="stylesheet"><!---多标签页加载页面的Tabs插件-&ndash;&gt;-->
<#--<link href="${ctx}/static/css/nav.css" rel="stylesheet">-->
<link href="${ctx}/static/css/iconfont.css" rel="stylesheet">
<#--<link href="${ctx}/static/css/bootstrap-treeview.css" rel="stylesheet">-->
<#--<link href="${ctx}/static/css/plugins/datapicker/datepicker3.css" rel="stylesheet">-->


<#--<link href="${ctx}/static/css/tabulator/tabulator_issp.css" rel="stylesheet">-->
<#--<link href="${ctx}/static/css/baidumap.css" rel="stylesheet">-->
<#--<link href="${ctx}/static/css/liMarquee.css" rel="stylesheet">-->
<#--<link href="${ctx}/static/css/fixedThead/fixedThead.css" rel="stylesheet"><!--固定table表头和列 &ndash;&gt;-->
<#--<link href="${ctx}/static/css/plugins/fileinput/fileinput.min.css" rel="stylesheet"><!-- 上传插件 fileinput &ndash;&gt;-->
<link href="${ctx}/static/ztree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet">
<#--<script type="text/javascript" src="https://api.map.baidu.com/api?v=3.0&ak=OUkjXhrQaxMvQ0G1VlSMCXgIl2KXHZ0G&s=1"></script>-->



<#--<script src="${ctx}/static/js/jquery/jquery.scrollbar.min.js"></script>-->
<#--<script src="${ctx}/static/js/Bootstrap/bootstrap-table.js"></script>-->
<#--<script src="${ctx}/static/js/Bootstrap/bootstraptable-treegrid.js"></script>-->
<script src="${ctx}/static/js/Bootstrap/bootstrap-treeview.js"></script>
<script src="${ctx}/static/js/changeStyle.js"></script>   <!-- 换肤js -->
<#--<script src="${ctx}/static/js/jquery/jquery.cookie.js"></script>  <!--  &ndash;&gt;-->

<script src="${ctx}/static/js/common.js"></script>
<#--<script src="${ctx}/static/js/plugins/pace/pace.min.js"></script>-->
<#--<script src="${ctx}/static/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>-->
<#--<script src="${ctx}/static/js/plugins/metisMenu/jquery.metisMenu.js"></script>-->
<#--<script src="${ctx}/static/js/plugins/multiselect/tree-multiselect.min.js"></script>-->
<#--<script src="${ctx}/static/js/utility/GUID.js"></script>-->
<#--<script src="${ctx}/static/js/tabulator/bluebird.js"></script>-->
<#--<script src="${ctx}/static/js/tabulator/tabulator.js"></script>-->
<#--<script src="${ctx}/static/js/tabulator/v4.1/tabulator.js"></script>-->
<!-- 插件 scripts -->
<#--<script src="${ctx}/static/js/plugins/chosen/chosen.jquery.js"></script>-->
<#--<script src="${ctx}/static/js/plugins/toastr/toastr.min.js"></script><!---顶部弹出提示-&ndash;&gt;-->
<#--<script src="${ctx}/static/js/plugins/sweetalert/sweetalert.min.js" ></script><!---对话框 alert-&ndash;&gt;-->
<#--<script src="${ctx}/static/js/plugins/validate/jquery.validate.min.js"></script>  <!---表单验证-&ndash;&gt;-->
<#--<script src="${ctx}/static/js/plugins/validate/validate-cn.js" ></script> <!---validate 自定义方法-&ndash;&gt;-->
<#--<script src="${ctx}/static/js/plugins/echarts/echarts.min.js"></script>-->
<#--<script src="${ctx}/static/js/plugins/echarts/chart/issp-bar.js"></script>-->
<#--<script src="${ctx}/static/js/plugins/echarts/chart/issp-line.js"></script>-->
<#--<script src="${ctx}/static/js/plugins/echarts/chart/issp-pie.js"></script>-->
<#--<script src="${ctx}/static/js/plugins/echarts/chart/issp-gauge.js"></script>-->
<#--<script src="${ctx}/static/js/plugins/echarts/chart/issp-tempgauge.js"></script>-->
<#--<script src="${ctx}/static/js/plugins/datapicker/bootstrap-datepicker.js"></script>-->
<#--<script src="${ctx}/static/js/nth.tabs.min.js"></script><!---多标签页加载页面的Tabs插件-&ndash;&gt;-->
<#--<script src="${ctx}/static/js/ghee_new.js"></script>-->
<#--<script src="${ctx}/static/js/issp/issp.js"></script>-->
<#--<script src="${ctx}/static/js/jquery/jquery.liMarquee.js"></script>-->
<#--<script src="${ctx}/static/js/issp/issp-map.js"></script>-->
<#--<script src="${ctx}/static/js/issp/issp-helpcombobox.js"></script><!-- 检索帮助框 &ndash;&gt;-->
<#--<script src="${ctx}/static/js/issp/issp-spinnerbox.js"></script><!-- 下拉列表 &ndash;&gt;-->
<#--<script src="${ctx}/static/js/My97DatePicker/WdatePicker.js"></script><!-- 时间选择框 &ndash;&gt;-->
<#--<script src="${ctx}/static/js/fixedThead/fixedThead.js"></script><!-- 固定table表头和列  &ndash;&gt;-->
<#--<script src="${ctx}/static/js/Bootstrap/bootstrap-collapse.js"></script><!-- Bootstrap折叠插件  &ndash;&gt;-->
<#--<script src="${ctx}/static/js/plugins/ajax/issp-ajax.js"></script><!-- ajax封装  &ndash;&gt;-->
<#--<script src="${ctx}/static/js/public-js.js"></script><!-- 公共方法封装js&ndash;&gt;-->
<#--<script src="${ctx}/static/js/plugins/fileinput/fileinput.js"></script><!--上传插件fileinput&ndash;&gt;-->
<#--<script src="${ctx}/static/js/plugins/fileinput/fileinput_locale_zh.js"></script><!--上传插件fileinput中文化&ndash;&gt;-->
<#--<script src="${ctx}/static/js/printThis/printThis.js"></script><!--打印插件&ndash;&gt;-->


<script src="${ctx}/static/ztree/js/jquery.ztree.all.js"></script>
<script src="${ctx}/static/js/utility/tree.js"></script><#--ztree 封装-->
<#--<script src="${ctx}/static/js/utility/form2js.js"></script>&lt;#&ndash; 表单结构化插件 &ndash;&gt;-->
<#--<script src="${ctx}/static/js/websocket/webSocket.js"></script>&lt;#&ndash; webSocket &ndash;&gt;-->
<#--<script src="${ctx}/static/js/websocket/cmd.js"></script>&lt;#&ndash; 下位机指令代码 &ndash;&gt;-->
<#--<script src="${ctx}/static/js/websocket/webSocketHandle.js"></script>&lt;#&ndash; webSocket handle &ndash;&gt;-->
<#--</html>-->