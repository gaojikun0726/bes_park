    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>齐鲁交通能耗监管平台</title>
    <meta name="keyword" content="">
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta name="Author" content="wujf">
    <meta name="copyright" content="山东正晨科技有限公司 All Rights Reserved">
    <link rel="icon" href="${ctx}/static/images/logo.png" type="image/x-icon" />
    <!---首先加载  下面JS、CSS需要使用--->
    <script src="${ctx}/static/js/jquery/jquery-2.1.1.min.js"></script>
    <script src="${ctx}/static/js/jquery/jquery-ui-11.4/jquery-ui.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${ctx}/static/js/plugins/bootstrap_select/bootstrap-select.js"></script>
    <link rel="stylesheet" href="${ctx}/static/css/plugins/checkbox/css/build.css"/><!-- 复选框用css -->
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/plugins/checkbox/css/default.css"><!-- 复选框用css -->
    
    <!-- Start add by yangjx at 21091021 for layUi-->
    <link href="${ctx}/static/layui/css/layui.css" rel="stylesheet">
    <!-- End add by yangjx at 21091021 -->
    
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
    <link href="${ctx}/static/css/skinColour_blue.css" rel="stylesheet" id="skinColour"><!--换肤-->
    <link href="${ctx}/static/css/tab_style.css" rel="stylesheet"><!---功能界面的多标签插件--->
    <link href="${ctx}/static/css/issp.css" rel="stylesheet">
    <link href="${ctx}/static/css/nth.tabs.min.css" rel="stylesheet"><!---多标签页加载页面的Tabs插件--->
    <link href="${ctx}/static/css/nav.css" rel="stylesheet">
    <link href="${ctx}/static/css/iconfont.css" rel="stylesheet">
    <link href="${ctx}/static/css/bootstrap-treeview.css" rel="stylesheet">
    <link href="${ctx}/static/css/plugins/datapicker/datepicker3.css" rel="stylesheet">
    <link href="${ctx}/static/css/tabulator/tabulator_issp.css" rel="stylesheet">
    <link href="${ctx}/static/css/baidumap.css" rel="stylesheet">
    <link href="${ctx}/static/css/liMarquee.css" rel="stylesheet">
    <link href="${ctx}/static/css/fixedThead/fixedThead.css" rel="stylesheet"><!--固定table表头和列 -->
    <link href="${ctx}/static/css/plugins/fileinput/fileinput.min.css" rel="stylesheet"><!-- 上传插件 fileinput -->
    <link href="${ctx}/static/ztree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet">
    <script type="text/javascript" src="https://api.map.baidu.com/api?v=3.0&ak=OUkjXhrQaxMvQ0G1VlSMCXgIl2KXHZ0G&s=1"></script>
    <!-- 全局 scripts -->
    <script src="${ctx}/static/js/Bootstrap/bootstrap.min.js"></script>
    <script src="${ctx}/static/js/jquery/jquery.scrollbar.min.js"></script>
    <script src="${ctx}/static/js/bootstrap-table.js"></script>
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
<style>
/* 左侧树结构 */
.left_tree {
    position: absolute;
    width: 35%;
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
    top: 52%;
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
/* li */
.first_li {
    text-align: center;
    padding: 10px;
    font-size: 1vw;
    font-weight: 600;
}

.left_div {
    padding: 5px 0 0 0;
    width: 25%;
    float: left;
    text-align: right;
}

.div_bottom_left_div {
    padding: 5px 0 0 0;
    width: 30%;
    float: left;
    text-align: right;
}

.right_div {
    padding: 5px 0 0 2px;
    width: 50%;
    float: left;
    text-align: left;
}

.left_div_span {
    display: block;
    margin: 0 0 -12px 0;
}
.left_div_span_nd{
    display: block;
    margin: 0 0 -10px 0;
    
}

.main-index-back {
    position: absolute;
    right: 2vh;
    top: 1vh;
    background: #337ab7;
    border: 1px solid #b19223;
    color: white;
    height: 3.8vh;
    width: 13vh;
    box-shadow: -3px 4px 10px #000000;
}

.gzt_backmapcharacters {
    font-size: 14px;
    font-family: "Inkfree";
    font-weight: bold;
}
.div-borderimg{
    background-image: url(../static/images/screen/item.jpg);
    background-repeat: no-repeat;
    background-size: 100% 100%;
}
.div-border{
    margin:10px 0px 10px 10px!important;
}
.map-border{
    margin: 10px 0px 10px 10px!important;
}
.caption {
    width: 1004px;
    height: 75px;
    position: absolute;
    top: 0;
    left: 50%;
    margin-left: -502px;
    background-image: url(../static/images/screen/caption_bg.png);
}
.caption:before {
    content: "";
    position: absolute;
    top: 0;
    left: -36px;
    width: 86px;
    height: 75px;
    background-image: url(../static/images/screen/caption_l1.png);
    animation: caption_left 2s infinite linear;
    animation-delay: .5s;
}
.caption:after {
    content: "";
    position: absolute;
    top: 0;
    right: -36px;
    width: 86px;
    height: 75px;
    background-image: url(../static/images/screen/caption_r1.png);
    animation: caption_right 2s infinite linear;
    animation-delay: .5s;
}
.caption_copy{
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
}
.caption_copy:before {
    content: "";
    position: absolute;
    top: 0;
    left: -48px;
    width: 75px;
    height: 75px;
    background-image: url(../static/images/screen/caption_l2.png);
    animation: caption_left 2s infinite linear;
}
.caption_copy:after {
    content: "";
    position: absolute;
    top: 0;
    right: -48px;
    width: 75px;
    height: 75px;
    background-image: url(../static/images/screen/caption_r2.png);
    animation: caption_right 2s infinite linear;
}
.caption_text{
    font-size: 48px;
    color: #fff;
    text-align: center;
    line-height: 35px;
    letter-spacing: 3px;
}
.title{
    width: 36rem!important;
    font-size: 1.33rem!important;
    font-family: MicrosoftYaHei-Bold!important;
    font-weight: bold!important;
/*     color: #7AFAFF!important; */
    color: #fff!important;
    padding-left: 1rem!important;
    display: block!important;
}
.content{
    width: 100%;
    height: 85%;
    margin: 2rem;
}
@keyframes caption_left{
    0%{opacity: 0; transform: translateX(40px);}
    20%{opacity: 1; transform: translateX(20px);}
    60%{opacity: 1; transform: translateX(5px);}
    100%{opacity: 0; transform: translateX(0);}
}
@keyframes caption_right{
    0%{opacity: 0; transform: translateX(-40px);}
    20%{opacity: 1; transform: translateX(-20px);}
    60%{opacity: 1; transform: translateX(0);}
    100%{opacity: 0; transform: translateX(5px);}
}
</style>

<div style="width: 100%; height: 100%; background-image: url(../static/images/screen/backImg7.jpg); background-size: 100% 100%;">
    <div style="width: 100%; height: 100%; padding-right: 20px">
        <div style="width: 100%; height: 9.4%; float: left;">
<!--             <h1 style="text-align:center;line-height: 1rem;">齐鲁交通能耗监管平台</h1> -->
            <div class="caption">
	            <div class="caption_copy">
	            </div>
                <h1 class="caption_text">齐鲁交通能耗监管平台</h1>
            </div>
        </div>
        <!-----左侧区域---->
        <div style="width: 20%; height: 89%; float: left;">
            <div id="" class="div-border div-borderimg" style="width: calc(100% - 10px); height: 23.5%;">
                <div class="title">齐鲁交通信息产业园</div>
                <div class="content">
                    <div class="left_div">
                        <span class="left_div_span">建筑物年代 : </span></br>
                        <span class="left_div_span">建筑物人口 : </span></br>
                        <span class="left_div_span">建筑物面积 : </span></br>
                        <span style="display: block;">系统已运行 : </span></br>
                    </div>
                    <div class="right_div">
                        <span id="F_BUILD_TIME" class="left_div_span" style="color: #E0D674">  </span></br>
                        <span id="F_PERSON_NUMS" class="left_div_span" style="color: #E0D674">  </span></br> 
                        <span id="F_ALL_AREA" class="left_div_span" style="color: #E0D674">  </span></br> 
                        <span style="display: block; color: #E0D674;" id="gzt_runingTime"> ${day}<span style="color: #8fe3f7">天</span>
                        <span style="color: #E0D674;">${hour} <span style="color: #8fe3f7">小时</span></span>
                        </span>
                    </div>
                </div>
            </div>
            <div id=""  class="div-border div-borderimg" style="width: calc(100% - 10px); height: 23.5%;">
                <div class="title">年度总耗能</div>
                <div class="content">
                    <div class="left_div" style="padding: 3px 0 0 10px;width:34%!important;">
                        <span class="left_div_span_nd">今年总耗能 : </span></br>
                        <span class="left_div_span_nd">上年能耗对比 : </span></br>
                    </div>
                    <div class="right_div" style="padding: 3px 0 0 10px;">
                         <span class="left_div_span_nd" style="color: #39E6C9;" id="gzt_year_alldata">0.00 Kw </span></br>
                         <span class="left_div_span_nd" style="color: #39E6C9;" id="gzt_LastYearData"> </span></br>
                    </div>
                </div>
            </div>
            <div id=""  class="div-border div-borderimg" style="width: calc(100% - 10px); height: 23.5%;">
                <div class="title">能耗分析</div>
                <div class="content">
                    <div class="div_bottom_left_div">
                        <span class="left_div_span">今日耗能 : </span></br>
                        <span class="left_div_span">峰值能耗 : </span></br>
                        <span class="left_div_span">上月能耗对比 : </span></br>
                        <span class="left_div_span">昨日能耗对比 : </span></br>
                    </div>
					<div class="right_div" style="width:57%;">
                         <span class="left_div_span" style="color: #39E6C9;" id="gzt_jrhn"></span></br>
                         <span class="left_div_span" style="color: #37a2da;" id="gzt_peak"></span></br>
                         <span class="left_div_span" style="color: #E0D674;" id="gzt_LastMonthData"></span></br>
                         <span class="left_div_span" style="color: #37a2da;" id="gzt_yesterdayData"></span></br>
                    </div>
                </div>
            </div>
            <div id="gzt_alarm"    class="div-border div-borderimg" style="width: calc(100% - 10px); height: 23.5%;">
            
            </div>
        </div>
        <!--中间区域 -->
        <div style="width: 60%; height: 89%; float: left;">
            <!-----左侧上半部区域---->
            <div style="width: 100%;height: calc(70.5% + 30px); float: left;">
                <div class="map-border" style="width: calc(100% - 12px); height: calc(100% - 10px); float: left; position: relative;">
                    <!--左侧树结构 -->
                    <div class="monitoringhome_zzjgtree">
                        <span>楼</span><span>宇</span><span>结</span><span>构</span>
                    </div>
                    <div class="left_tree">
                        <div id="tree_zzjg_CM" class="tree_zzjg_CM"></div>
                    </div>

                    <div id="allmap" style="width: 100%; height: 100%; padding-left: 20px;"></div>
                </div>
            </div>

            <!-----左侧下半部区域---->
            <div style="width: 100%;height: calc(24.5% - 9px);; float: left;">
                <div class="div-border div-borderimg" id="gzt_bottomTwo" style="width: calc(25% - 10px); height: 100%; float: left;"></div>
                <div class="div-border div-borderimg" id="gzt_bottomOne" style="width: calc(25% - 10px); height: 100%; float: left;"></div>
                <div class="div-border div-borderimg" id="gzt_bottomThree" style="width: calc(25% - 10px); height: 100%; float: left;"></div>
                <div class="div-border div-borderimg" id="gzt_bottomFour" style="width: calc(25% - 10px); height: 100%; float: left;"></div>
            </div>

        </div>

        <!-----右侧区域---->
        <div style="width: 20%; height: 89%; float: left;">
            <div id="gzt_rightSecond" class="div-border div-borderimg" style="width: 100%; height: 23.5%;"></div>
            <div id="today_tendency" class="div-border div-borderimg" style="width: 100%; height: 23.5%;"></div>
            <div id="gzt_rightThird" class="div-border div-borderimg" style="width: 100%; height: 23.5%;"></div>
            <div id="gzt_rightFour" class="div-border div-borderimg" style="width: 100%; height: 23.5%;"></div>
        </div>


    </div>
</div>
<script src="${ctx}/static/js/time_range.js"></script>
<script type="text/javascript">

var _ctx = '${ctx}';
var nodeId = "";//组织结构树ID
var workbench_yqbh = "0000";
var nybh = "01000";
//点击组织结构 展开/收起
$(function(){
    var left_cols_mark = 0 ;
    zzjg_tree(workbench_yqbh);
    $(".left_tree").hide();
    $(".monitoringhome_zzjgtree").click(function(){
        if(left_cols_mark == 1){
            left_cols_mark = 0;
            $(".left_tree").hide();
            $(".monitoringhome_zzjgtree").css("margin-left","");
        }else{
            left_cols_mark = 1;
            $(".left_tree").show();
            $(".monitoringhome_zzjgtree").css("margin-left","35%");

        }
    });
})
//加载树   组织机构
function zzjg_tree(yqbh){
    workbench_yqbh = yqbh;
    //电的分户 id="0001"
    $.ajax({
        type: "post",
        url: "${ctx}/view/basedatamanage/energyinformation/houseHold_treegrid",
        dataType: "json",
        async: false,
        data: ({
            fNybh: "01000",
            fYqbh: yqbh
        }),
        success: function(result) {
            //初始加载根节点
            if (result.hasOwnProperty("list")) {
                if (result.list.length > 0) {
                    $('#tree_zzjg_CM').treeview({
                        data: result.list,
                        highlightSelected: true,
                        levels: 4,
                        enableLinks: true,
                        allowReselect : true,//允许重复点击
                        color: "#4a4747",
                        onNodeSelected(event, nodeData) { //节点点击事件
                            nodeId = nodeData.id;//节点id
                            nodeText = nodeData.text;//节点名称
                            TodayTendency(nodeId);//今日耗能
                            MonthTendency(nodeId);//本月耗能
                            MonthTbillumination(nodeId,nodeText);//本月耗能同比
                            Monthillumination(nodeId,nodeText);//本月耗能环比
                            TodayTbillumination(nodeId,nodeText);//今日耗能同比
                            Todayillumination(nodeId,nodeText);//今日耗能环比
                            energyAnalyze(nodeId);//能耗分析
                            leftRefresh(nodeId);//左侧数据对接
                            leftInfo(yqbh);//左上信息
                        },
                    });
                    var firstNode = $("#tree_zzjg_CM").treeview('findNodes', [result.list[0].id, 'id']);
                    $("#tree_zzjg_CM").treeview("selectNode", firstNode); //将第一个节点设置为选中状态
                    var firstNodeId = result.list[0].id;
                    TodayRankEnergy(firstNodeId);//今日能耗排行
                    RankEnergy(firstNodeId);//能耗排行
                }
            } else {
                $('#tree_zzjg_CM').treeview({
                    data: "[]",
                    // 数据源
                });
            }
        },
        error: function(nodeData) {
            swal(nodeData.msg, "", "error");
        }
    });
    }
    
    
    //今日耗能
    function TodayTendency(nodeId){
        var today = getFormatDate(getCurrentDate());
        $.ajax({
            type: "post",
            url: "${ctx}/view/dataAnalysises/getHouseHoldData",
            dataType: "json",
            async: true,
            traditional: true,
            data: {
                "fType": "0",//时
                "fCjsj_start": today,
                "fCjsj_end": today,
                "fFhbhs": nodeId
            },
            success: function(result) {
                var res = result.map;
                var arr = res.time;
                //echars所需数据
                var fCjsj=[];
                var fData=[];
                //填充左侧展示数据
                var todayData = "";
                //循环
                for(var a=0;a<arr.length;a++){
                    var sj = arr[a].fCjsj.substr(11,2);
                    fCjsj.push(sj);
                    fData.push(arr[a].fData || '0.0');
                }
                today_tendency(fCjsj,fData);//当天耗能
            },
        });
    }
    //本月耗能
    function MonthTendency(nodeId){
        var time = getCurrentMonth();
        $.ajax({
            type: "post",
            url: "${ctx}/view/dataAnalysises/getHouseHoldData",
            dataType: "json",
            async: true,
            traditional: true,
            data: {
                "fType": "1",//天
                "fCjsj_start": getFormatDate(time[0]),
                "fCjsj_end": getFormatDate(time[1]),
                "fFhbhs": nodeId
            },
            success: function(result) {
                var res = result.map;
                var arr = res.time;
                //echars所需数据
                var fCjsj=[];
                var fData=[];
                //填充左侧展示数据
                var todayData = "";
                //循环
                for(var a=0;a<arr.length;a++){
                    var sj = arr[a].fCjsj.substr(8,2);
                    fCjsj.push(sj);
                    fData.push(arr[a].fData || '0.0');
                }
                gzt_bottomOne(fCjsj,fData);//本月耗能
            },
        });
    }
    //同环比
    function change_tqtime(start,end,type){
        var res = new Array();
        if(type=='1'){//同比
            if (start != null) {
                start.setFullYear(start.getFullYear() - 1);
                res.push(getFormatDate(start));
            }
            if (end != null) {
                end.setFullYear(end.getFullYear() - 1);
                res.push(getFormatDate(end));
            }
        }else{//环比
            if (start != null && end != null) {
                var t = 2 * start.getTime() - end.getTime();
                if (start.getTime() == end.getTime()) {
                    start.setTime(start.getTime() - 24 * 60 * 60 * 1000);
                    end.setTime(start.getTime());
                } else {
                    end.setTime(start.getTime());
                    start.setTime(t);
                }
                res.push(getFormatDate(start));
                res.push(getFormatDate(end));
            }
        }
        
        return res;
    }
    //本月能耗同比
    function  MonthTbillumination(nodeId,nodeText) {
        //同比--本月--月--月数据对比
        var time = getCurrentMonth();
        var fCjsj_start = getFormatDate(time[0]);
        var fCjsj_end = getFormatDate(time[1]);
        var ftbhb_sametime_start= "";
        var ftbhb_sametime_end = "";
        var res = change_tqtime(time[0],time[1],'1');
        if(res.lenght!=0){
            ftbhb_sametime_start = res[0];
            ftbhb_sametime_end = res[1];
        }
        $.ajax({
            type: "post",
            url: "${ctx}/view/basedatamanage/workbench/illumination",
            dataType: "json",
            async: true,
            traditional: true,
            data: ({
                "fType": "2",//月
                "fCjsj_start": fCjsj_start.substr(0,7),
                "fCjsj_end": fCjsj_end.substr(0,7),
                "ftbhb_sametime_start": ftbhb_sametime_start.substr(0,7),
                "ftbhb_sametime_end": ftbhb_sametime_end.substr(0,7),
                "fId": nodeId,
                "fFhbhmc":nodeText
            }),
            success: function(result) {
                var legend = result.data[0].legend;
                var seriesData = result.data[0].seriesData;
                var timeData = result.data[0].timeData;
                gzt_bottomThree(legend,seriesData,timeData);
            }
        });
    }
    //本月能耗环比
    function  Monthillumination(nodeId,nodeText) {
        //环比--本月--月--月数据对比
        var time = getCurrentMonth();
        var fCjsj_start = getFormatDate(time[0]);
        var fCjsj_end = getFormatDate(time[1]);
        var ftbhb_sametime_start= "";
        var ftbhb_sametime_end = "";
        var res = change_tqtime(time[0],time[1],'2');
        if(res.lenght!=0){
            ftbhb_sametime_start = res[0];
            ftbhb_sametime_end = res[1];
        }
        $.ajax({
            type: "post",
            url: "${ctx}/view/basedatamanage/workbench/illumination",
            dataType: "json",
            async: true,
            traditional: true,
            data: ({
                "fType": "2",//月
                "fCjsj_start": fCjsj_start.substr(0,7),
                "fCjsj_end": fCjsj_end.substr(0,7),
                "ftbhb_sametime_start": ftbhb_sametime_start.substr(0,7),
                "ftbhb_sametime_end": ftbhb_sametime_start.substr(0,7),
                "fId": nodeId,
                "fFhbhmc":nodeText
            }),
            success: function(result) {
                var legend = result.data[0].legend;
                var seriesData = result.data[0].seriesData;
                var timeData = result.data[0].timeData;
                gzt_bottomFour(legend,seriesData,timeData);
            }
        });
    }
  //今日能耗同比
    function  TodayTbillumination(nodeId,nodeText) {
        //同比--本月--月--月数据对比
        var time = getCurrentDate();
        var fCjsj_start = getFormatDate(time);
        var fCjsj_end = getFormatDate(time);
        var ftbhb_sametime_start= "";
        var ftbhb_sametime_end = "";
        var res = change_tqtime(time,time,'1');
        if(res.lenght!=0){
            ftbhb_sametime_start = res[0];
            ftbhb_sametime_end = res[1];
        }
        $.ajax({
            type: "post",
            url: "${ctx}/view/basedatamanage/workbench/illumination",
            dataType: "json",
            async: true,
            traditional: true,
            data: ({
                "fType": "1",//天
                "fCjsj_start": fCjsj_start,
                "fCjsj_end": fCjsj_end,
                "ftbhb_sametime_start": ftbhb_sametime_start,
                "ftbhb_sametime_end": ftbhb_sametime_end,
                "fId": nodeId,
                "fFhbhmc":nodeText
            }),
            success: function(result) {
                var legend = result.data[0].legend;
                var seriesData = result.data[0].seriesData;
                var timeData = result.data[0].timeData;
                gzt_rightThird(legend,seriesData,timeData);
            }
        });
    }
    //今日能耗环比
    function  Todayillumination(nodeId,nodeText) {
        //环比--本月--月--月数据对比
        var time = getCurrentDate();
        var fCjsj_start = getFormatDate(time);
        var fCjsj_end = getFormatDate(time);
        var ftbhb_sametime_start= "";
        var ftbhb_sametime_end = "";
        var res = change_tqtime(time,time,'2');
        if(res.lenght!=0){
            ftbhb_sametime_start = res[0];
            ftbhb_sametime_end = res[1];
        }
        $.ajax({
            type: "post",
            url: "${ctx}/view/basedatamanage/workbench/illumination",
            dataType: "json",
            async: true,
            traditional: true,
            data: ({
                "fType": "1",//天
                "fCjsj_start": fCjsj_start,
                "fCjsj_end": fCjsj_end,
                "ftbhb_sametime_start": ftbhb_sametime_start,
                "ftbhb_sametime_end": ftbhb_sametime_end,
                "fId": nodeId,
                "fFhbhmc":nodeText
            }),
            success: function(result) {
                var legend = result.data[0].legend;
                var seriesData = result.data[0].seriesData;
                var timeData = result.data[0].timeData;
                gzt_rightFour(legend,seriesData,timeData);
            }
        });
    }
    //刷新左上数据
    function leftInfo(yqbh){
        $.ajax({
            type: "post",
            url: "${ctx}/view/basedatamanage/workbench/getleftInfo",
            dataType: "json",
            async: true,
            traditional: true,
            data: ({
                "yqbh": yqbh //园区编号
            }),
            success: function(result) {
                $("#yqmc").text(result.data.F_YQMC);                
                if(workbench_yqbh!='0000'){
                     $("#F_ALL_AREA").html( "<span style='color: #8fe3f7'>&nbsp;&nbsp;平米</span>");
                     $("#F_PERSON_NUMS").html( "<span style='color: #8fe3f7'>&nbsp;&nbsp;人</span>");
                     $("#F_BUILD_TIME").html( "<span style='color: #8fe3f7'>&nbsp;&nbsp;年</span>");
                     $("#gzt_runingTime").html(result.data.day + "<span style='color: #8fe3f7'>天</span>");
                }else{
                     $("#F_ALL_AREA").html(result.data.F_ALL_AREA + "<span style='color: #8fe3f7'>平米</span>");
                     $("#F_PERSON_NUMS").html(result.data.F_PERSON_NUMS + "<span style='color: #8fe3f7'>人</span>");
                     $("#F_BUILD_TIME").html(result.data.F_BUILD_TIME + "<span style='color: #8fe3f7'>年</span>");
                     $("#gzt_runingTime").html(result.data.day + "<span style='color: #8fe3f7'>天</span>");
                }
            }
        });
    }
    //刷新左侧数据--和地图中间数据
    function leftRefresh(nodeId){
        //上年 上月  昨日--当天--本年
        var taday = getFormatDate(getCurrentDate());//今天
        var yesterday = getFormatDate(getPreviousDate());//昨天
        var year = getFormatDate(getCurrentDate()).substr(0,4);//本年
        var lastyear = getFormatDate(getPreviousYear()[0]).substr(0,4);//上年
        var month = getFormatDate(getCurrentMonth()[0]).substr(0,7);//本月
        var lastmonth = getFormatDate(getPreviousMonth()[0]).substr(0,7);//上月
        $.ajax({
            type: "post",
            url: "${ctx}/view/basedatamanage/workbench/gztLeftRefresh",
            dataType: "json",
            async: true,
            traditional: true,//组织深度序列化 
            data: {
                "fType": "1",//天
                "fCjsj_start":taday,
                "fCjsj_end": getFormatDate(getCurrentDate()).substr(0,4),//传本年时间 ---2018 
                "fId": nodeId,
                //----分割 request
                "taday":taday,
                "yesterday":yesterday,
                "year":year,
                "lastyear":lastyear,
                "month":month,
                "lastmonth":lastmonth,
                "isControl":"0",//默认开启查询瞬时功率  0关闭 1开启
                "nhbh":"0001002"//能耗编号 写死 功率
            },
            success: function(result) {
                if(result.hasOwnProperty("list")){
                    var obj = result.list[0];
                    var fUnitareaData=obj.fUnitareaData;
                    var fPermanData=obj.fPermanData;
                    var fAlldata = obj.fAlldata;
                    if(workbench_yqbh!='0000'){
                        $("#gzt_fUnitareaData").empty().html("&nbsp;&nbsp;Kw/平");
                        $("#gzt_fPermanData").empty().html("&nbsp;&nbsp;Kw/人");
                    }else{
                        $("#gzt_fUnitareaData").empty().text(fUnitareaData+" Kw/平");
                        $("#gzt_fPermanData").empty().text(fPermanData+" Kw/人");
                    }
                    $("#gzt_year_alldata").empty().text(fAlldata+" Kwh");//总能耗
//                     $("#gzt_year_zmdata").empty().text(fAlldata+" Kwh");//照明
                }
                if(result.hasOwnProperty("data")){
                    var data = result.data[0];
                    var LastMonthData = data.LastMonthData;
                    var lastMonthContrast = data.lastMonthContrast;
                    var LastYearData = data.LastYearData;
                    var LastYearContrast = data.LastYearContrast;
                    var yesterdayData = data.yesterdayData;
                    var yesterdayDataContrast = data.yesterdayDataContrast;
                    var peakEnergy = data.peakEnergy;
                    if(peakEnergy == null){
                    	peakEnergy = "0.00";
                    }
                    var InstantPower = data.InstantPower;
                    $("#gzt_instantaneous").empty().append("<span style='color: #E0D674;'>"+InstantPower+" Kw</span>");//瞬时功率
                    $("#gzt_peak").empty().append("<span style='color: #E0D674;'>"+peakEnergy+" Kwh</span>");//峰值
                    $("#gzt_LastYearData").empty().append("<span style='color: #E0D674;'>"+LastYearData+"  Kwh "+LastYearContrast+"</span>");//上月
                    $("#gzt_LastMonthData").empty().append("<span style='color: #E0D674;'>"+LastMonthData+"  Kwh "+lastMonthContrast+"</span>");//上月
                    $("#gzt_yesterdayData").empty().append("<span style='color: #E0D674;'>"+yesterdayData+"  Kwh "+yesterdayDataContrast+"</span>");//昨天
                }
            }
        });
    }
    //能耗分析
    function energyAnalyze(nodeId){
        var today = getFormatDate(getCurrentDate());
        $.ajax({
            type: "post",
            url: "${ctx}/view/basedatamanage/workbench/energyAnalyze",
            dataType: "json",
            async: true,
            traditional: true,
            data: {
                "fType": "1",//天
                "fCjsj_start": today,
                "fCjsj_end": today,
                "fId": nodeId
            },
            success: function(result) {
                var obj = result.list[0];
                var data = obj.data;
                $("#gzt_jrhn").text(data+" Kwh");
            }
        });
    }
    // 今日耗能排行单独
    function TodayRankEnergy(nodeId){
        var today = getFormatDate(getCurrentDate());//本天时间
        $.ajax({
            type: "post",
            url: "${ctx}/view/basedatamanage/workbench/GetTodayRankEnergy",
            dataType: "json",
            async: true,
            traditional: true,
            data: {
                "count":"5",//条数
                "fCjsj_start": today,
                "fCjsj_end": today,
                "fFhbh": nodeId,
                "nybh":nybh
            },
            success: function(result) {
                if(result.hasOwnProperty("list")){
                    var TodayRankEnergy = result.list[0].TodayRankEnergy;//日list
                    var Todaylegend = [];//legend
                    var TodayYAxisData = [];//yAxis
                    var TodaySeriesData = [];//series
                    for(var i=0;i<TodayRankEnergy.length;i++){
                        var time0 = TodayRankEnergy[i].fCjsj.substr(8,2)+"日";
                        var time = TodayRankEnergy[i].fFhmc;
                        if(workbench_yqbh=='0000'){
                            time = time.substring(6,TodayRankEnergy[i].fFhmc.length-3);
                        }
                        var data = TodayRankEnergy[i].fData;
                        if(i==0){
                            Todaylegend.push(time0);
                        }
                        TodayYAxisData.push(time);
                        TodaySeriesData.push(data);
                    }
                    gzt_rightSecond(Todaylegend,TodayYAxisData,TodaySeriesData);

                }
            }
        });
    }
    
    //能耗排行
    function RankEnergy(nodeId){
        var today = getFormatDate(getCurrentDate());//本天时间
        var month = getCurrentMonth();//本月时间数组
        $.ajax({
            type: "post",
            url: "${ctx}/view/basedatamanage/workbench/GetRankEnergy",
            dataType: "json",
            async: true,
            traditional: true,
            data: {
                "count":"5",//条数
                "fCjsj_start": today,
                "fCjsj_end": today,
                "month_start":getFormatDate(month[0]),
                "month_end":getFormatDate(month[1]),
                "fFhbh": nodeId,
                "nybh":nybh
            },
            success: function(result) {
                if(result.hasOwnProperty("list")){
                    var MonthRankEnergy = result.list[0].MonthRankEnergy;//月list
                    var Monthlegend = [];//legend
                    var MonthYAxisData = [];//yAxis
                    var MonthSeriesData = [];//series
                    for(var i=0;i<MonthRankEnergy.length;i++){
                        var time0 = MonthRankEnergy[i].fCjsj.substr(5,2)+"月";
                        var time = MonthRankEnergy[i].fFhmc;
                        if(workbench_yqbh=='0000'){
                            time = time.substring(6,MonthRankEnergy[i].fFhmc.length-3);
                        }
                        var data = MonthRankEnergy[i].fData;
                        if(i==0){
                            Monthlegend.push(time0);
                        }
                        MonthYAxisData.push(time);
                        MonthSeriesData.push(data);
                    }
                    gzt_bottomTwo(Monthlegend,MonthYAxisData,MonthSeriesData);
                    
                    var YqRankEnergy = result.list[0].YqRankEnergy;//月list
                    var Yqlegend = [];//legend
                    var YqYAxisData = [];//yAxis
                    var YqSeriesData = [];//series
                    for(var i=0;i<YqRankEnergy.length;i++){
                        var time0 = YqRankEnergy[i].fCjsj.substr(5,2)+"月";
                        var time = YqRankEnergy[i].fFhmc;
//                         if(workbench_yqbh=='0000'){
//                             time = time.substring(6,YqRankEnergy[i].fFhmc.length-3);
//                         }
                        if(time.indexOf("齐鲁交通")>-1){
                            time = time.replace('齐鲁交通', '');
                        }
                        var data = YqRankEnergy[i].fData;
                        if(i==0){
                            Yqlegend.push(time0);
                        }
                        YqYAxisData.push(time);
                        YqSeriesData.push(data);
                    }
                    gzt_alarm(Yqlegend,YqYAxisData,YqSeriesData);
                }
            }
        });
    }

    initBaiduMap();
     
</script>