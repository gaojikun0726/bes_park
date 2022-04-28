<link href="${ctx}/static/css/zmjk.css"  rel="stylesheet" >
<style>
/* 左侧树结构 */
.left_tree {
	position: fixed;
	width: 15%;
	z-index: 980;
	transition: all .5s ease;
	user-select: none;
	transition: all .3s ease;
	overflow-y: auto;
	overflow-x: auto;
	height: 61.5%;
	border-bottom: 1px solid #366c90;
}

.monitoringhome_zzjgtree {
	width: 30px;
	height: 8em;
	padding-top: 17px;
	cursor: pointer;
	position: absolute;
	top: 43%;
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
	padding: 10px 0 0 0;
	width: 50%;
	float: left;
	text-align: right;
}

.div_bottom_left_div {
	padding: 10px 0 0 0;
	margin-left: -2px;
	width: 51%;
	float: left;
	text-align: right;
}

.right_div {
	padding: 10px 0 0 2px;
	width: 50%;
	float: left;
	text-align: left;
}

.left_div_span {
	display: block;
	margin: 0 0 -12px 0;
}
</style>

<div style="width: 100%; height: 100%; background-integration_image: url(static/integration_images/backImg.jpg); background-size: 100% 100%;">
	<div style="width: 100%; height: 100%; padding-right: 20px">
		<!-----左侧区域---->
		<div style="width: 80%; height: 100%; float: left;">
			<!-----左侧上半部区域---->
			<div style="width: 100%; height: 70%; float: left;">
				<div style="width: 20%; height: calc(100% - 20px); float: left; padding-right: 20px;">
					<div class="div-border" id="leftTop1" style="width: 100%; height: 55%;">
						<div class="div_top" style="width: 100%; height: 70%; border-bottom: 1px solid #02dabc80;">
							<li class="first_li ">实验用例</li>
							<div class="left_div">
								<span class="left_div_span">节点编码 : </span></br> <span class="left_div_span">建筑物年代 : </span></br> <span class="left_div_span">建筑物人口 : </span></br> <span class="left_div_span">建筑物面积
									: </span></br> <span style="display: block;">系统已运行 : </span></br>
							</div>
							<div class="right_div">
								<span id="" class="left_div_span" style="color: #E0D674"> 370103B016</span></br> <span id="" class="left_div_span" style="color: #E0D674"> 2016 <span style="color: #8fe3f7">年</span></span></br>
								<span id="" class="left_div_span" style="color: #E0D674"> 1500 <span style="color: #8fe3f7">人</span></span></br> <span id="" class="left_div_span" style="color: #E0D674">
									22万 <span style="color: #8fe3f7">平米</span>
								</span></br> <span style="display: block; color: #E0D674;"> ${day} <span style="color: #8fe3f7">天</span> <span style="color: #E0D674;">${hour} <span
										style="color: #8fe3f7">小时</span></span>
								</span>
							</div>
						</div>
						<div class="div_bottom" style="width: 100%;">
							<div class="div_bottom_left_div">
								<span class="left_div_span">今日单位面积能耗 : </span></br> <span class="left_div_span">今日人均能耗 : </span>
							</div>
							<div class="right_div" style="width: 49%">
                                <span class="left_div_span" style="color: #E0D674;" id="gzt_fUnitareaData"><span
                                    style="color: #8fe3f7"></span></span></br> <span class="left_div_span"
                                    style="color: #E0D674;" id="gzt_fPermanData"><span style="color: #8fe3f7"></span></span>
                            </div>
						</div>
					</div>
					<!-- 填充能耗 -->
					<div class="div-border" style="width: 100%; height: 45%;">
						<div class="div_top" style="width: 100%;">
							<ul>
								<li class="first_li">能耗分析</li>
								<div class="left_div">
									<span class="left_div_span">今日耗能 : </span></br> <span class="left_div_span">瞬时功率 : </span></br> <span class="left_div_span">峰值能耗 : </span></br> <span class="left_div_span">上年能耗对比
										: </span></br> <span class="left_div_span">上月能耗对比 : </span></br> <span class="left_div_span">昨日能耗对比 : </span></br>
								</div>
								<div class="right_div">
                                    <span class="left_div_span" style="color: #39E6C9;" id="gzt_jrhn"> 
                                    </span> </br> <span class="left_div_span" style="color: #37a2da;" id="gzt_instantaneous">
                                    </span> </br> <span class="left_div_span" style="color: #E0D674;" id="gzt_peak"> 
                                    </span></br> <span class="left_div_span" style="color: #39E6C9;" id="gzt_LastYearData"> </span> </br> <span class="left_div_span" style="color: #E0D674;" id="gzt_LastMonthData"> </span> </br> <span
                                        class="left_div_span" style="color: #37a2da;" id="gzt_yesterdayData"> </span> </br>
                                </div>
							</ul>
						</div>
					</div>
				</div>

				<div class="map-border" style="width: calc(80% - 20px); height: calc(100% - 10px); float: left;">
					<!--左侧树结构 -->
					<div class="monitoringhome_zzjgtree">
						<span>组</span><span>织</span><span>结</span><span>构</span>
					</div>
					<div class="left_tree">
						<div id="tree_zzjg_CM" class="tree_zzjg_CM"></div>
					</div>
					<div id="allmap" class="cross_url" style="width: 100%; height: 100%; background: url(static/images/zzjg/cross.jpg) no-repeat -0px 0; background-size: 100% 100%;">
						<div id="allmap_layout" style="height: 100%; display: none;">
							<div class="allmap_top" style="height: 70%; ">
								<div class="top_Warning" style="width: 30%; margin: 0 auto; padding-top: 6%; height: 20%;">
									<i class="fa fa-lightbulb-o fa-3x" aria-hidden="true" style="color: red;"></i> <span style="color: yellow;">该楼层灯管线路老化</span>
								</div>
							</div>
							<div class="allmap_botton" style="height: 30%;">
								<div class="allmap_botton_cneter" style="width: 90%; margin: 0 auto; background-color: #ddd8d233; height: 85%;">
									<table class="table table-bordered" style="width:40%;float:left;">
										<thead>
											<tr>
												<th style="width:6%;text-align: center;background-color: #f5f5f500;border-bottom: 1px solid #f5f5f5e0 !important;">设备/状态</th>
												<th style="width:6%;text-align: center;background-color: #f5f5f500;border-bottom: 1px solid #f5f5f5e0 !important;">正常</th>
												<th style="width:6%;text-align: center;background-color: #f5f5f500;border-bottom: 1px solid #f5f5f5e0 !important;">异常</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<th scope="row" style="text-align: center;">桌椅</th>
												<td style="text-align: center;">正常</td>
												<td style="text-align: center;">正常</td>
											</tr>
											<tr>
												<th scope="row" style="text-align: center;">橱柜</th>
												<td style="text-align: center;">正常</td>
												<td style="text-align: center;">正常</td>
											</tr>
										</tbody>
									</table>
									<table class="table table-bordered" style="width:40%;float:right;">
                                        <thead>
                                            <tr>
                                                <th style="width:6%;text-align: center;background-color: #f5f5f500;border-bottom: 1px solid #f5f5f5e0 !important;">设备/状态</th>
                                                <th style="width:6%;text-align: center;background-color: #f5f5f500;border-bottom: 1px solid #f5f5f5e0 !important;">正常</th>
                                                <th style="width:6%;text-align: center;background-color: #f5f5f500;border-bottom: 1px solid #f5f5f5e0 !important;">异常</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <th scope="row" style="text-align: center;">电脑</th>
                                                <td style="text-align: center;">正常</td>
                                                <td style="text-align: center;">正常</td>
                                            </tr>
                                            <tr>
                                                <th scope="row" style="text-align: center;">空调</th>
                                                <td style="text-align: center;">正常</td>
                                                <td style="text-align: center;">正常</td>
                                            </tr>
                                        </tbody>
                                    </table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-----左侧下半部区域---->
			<div style="width: 100%; height: calc(30% - 20px); float: left;">
				<div class="div-border" id="leftTop" style="width: calc(20% - 20px); height: 100%; float: left; padding-left: 10px;"></div>
				<div class="div-border" id="chart1" style="width: calc(20% - 20px); height: 100%; float: left;"></div>
				<div class="div-border" id="chart2" style="width: calc(20% - 20px); height: 100%; float: left;"></div>
				<div class="div-border" id="chart3" style="width: calc(20% - 20px); height: 100%; float: left;"></div>
				<div class="div-border" id="chart4" style="width: calc(20% - 20px); height: 100%; float: left;"></div>
			</div>

		</div>

		<!-----右侧区域---->
		<div style="width: 20%; height: calc(100% - 50px); float: left;">
			<div id="sbzt1" class="div-border" style="width: 100%; height: 25%;"></div>
			<div id="sbzt2" class="div-border" style="width: 100%; height: 25%;"></div>
			<div id="sbzt3" class="div-border" style="width: 100%; height: 25%;"></div>
			<div id="sbzt4" class="div-border" style="width: 100%; height: 25%;"></div>
		</div>

	</div>
</div>
<script src="${ctx}/static/js/time_range.js"></script>
<script type="text/javascript">
	var integration_image = "static/images/zzjg/sqsj.jpg";//默认图片
	var layout = "";//布局类型
	var left_cols_mark = 0;//左侧树是否展开
	var gzt_fhbh = "";
	
	//定时刷新
	setInterval(function (){
		refreshControl();
    },60000);
    
	//定时刷新监控页面
	function refreshControl(){
		$.ajax({//加载树节点信息
            type : "post",
            url : "${ctx}/view/basedatamanage/eqmanage/sbdy_treeIconForJK",
            success : function(returnObject) {
                control(returnObject);
            }
        });
	}
	//点击树刷新监控页面
	function monitoringRefresh(){
		$.ajax({//加载树节点信息
            type : "post",
            url :"${ctx}/view/basedatamanage/eqmanage/getAllDOInfo",
            success : function(returnObject) {
                //每个开关控制的逻辑点
            	control(returnObject);
            },
            error : function(returnObject) {
                swal(returnObject.msg, "", "error");
            }
        });
	}
	//控制开关逻辑点
	function control(returnObject){
		var mainswitch03dn = new Array("F3M12104","F3M12105");
        var mainswitch04xjc = new Array("F4M8100","F4M8101","F4M8102","F4M8103","F4M8104","F4M8105","F4M8106","F4M8107");
        var mainswitch04djc = new Array("F4M8200","F4M8201","F4M8202","F4M8203","F4M8204","F4M8205");
        var mainswitch1901 = new Array("M8190119DNJ00","M8190119DNJ01");
        var mainswitch1902 = new Array("M4190200","M4190201","M4190202","M4190203");
        var mainswitch1906 = new Array("M8190601","M8190602","M8190603","M8190604","M8190605","M8190606","M8190607");
        var mainswitch1908 = new Array("M8190800","M8190801","M8190802","M8190803","M8190804","M8190805","M8190806","M8190807");
        var mainswitch1909 = new Array("M4190900","M4190901","M4190902");
        var mainswitch1910 = new Array("M4191000","M4191001","M4191002","M4191003");
        var mainswitch1911 = new Array("M4191100","M4191101","M4191102","M4191103");
        var mainswitch1916 = new Array("M81916DBJ00","M81916DBJ01","M81916DBJ02","M81916DBJ03","M81916DBJ04");
        var mainswitch1919 = new Array("M8190119DNJ02","M8190119DNJ03");
        var mainswitch2600 = new Array("F26M8200","F26M8201","F26M8202","F26M8203","F26M8204","F26M8207");
        
        var pointList = [{"mainswitch03dn": mainswitch03dn},{"mainswitch04xjc": mainswitch04xjc},{"mainswitch04djc": mainswitch04djc},
            {"mainswitch1901": mainswitch1901},{"mainswitch1902": mainswitch1902},{"mainswitch1906": mainswitch1906},
                     {"mainswitch1908": mainswitch1908},{"mainswitch1909": mainswitch1909},{"mainswitch1910": mainswitch1910},
                     {"mainswitch1911": mainswitch1911},{"mainswitch1916": mainswitch1916},{"mainswitch1919": mainswitch1919},{"mainswitch2600": mainswitch2600}];
        if(returnObject.hasOwnProperty('list')){
        var updateInfo = returnObject.list;
        if(updateInfo.length != null && updateInfo.length != ""){                   
        for (var i = 0; i < updateInfo.length; i++) {
            if (!updateInfo[i] == '' || !updateInfo[i] == null) {
                if (updateInfo[i].F_INIT_VAL == 0){//关闭
                    $("#" + updateInfo[i].F_SYS_NAME +".switch-button").removeClass("online");
                    $("#" + updateInfo[i].F_SYS_NAME +".switch-button").addClass("offline");    
                    $("#" + updateInfo[i].F_SYS_NAME +".circle-button").removeClass("circleonline");
                    $("#" + updateInfo[i].F_SYS_NAME +".circle-button").addClass("circleoffline");
                    $("#" + updateInfo[i].F_SYS_NAME).val("0");         
                }else{
                    $("#" + updateInfo[i].F_SYS_NAME +".switch-button").removeClass("offline");
                    $("#" + updateInfo[i].F_SYS_NAME +".switch-button").addClass("online");                                 
                    $("#" + updateInfo[i].F_SYS_NAME +".circle-button").addClass("circleonline");
                    $("#" + updateInfo[i].F_SYS_NAME +".circle-button").removeClass("circleoffline");
                    $("#" + updateInfo[i].F_SYS_NAME).val("255");
                }
                //遍历所有开关按钮
                for(var x=0;x<pointList.length;x++){
                    //取开关关联的所有点
                    for(var key in pointList[x]){
                        var keyname = key;
                        var points = pointList[x][key];
                        //遍历该开关的所有点
                        for(var j=0;j<pointList[x][key].length;j++){
                            if(updateInfo[i].F_SYS_NAME == points[j]){
                                pointList[x][key][j] = updateInfo[i].F_INIT_VAL;                                                
                            }                                   
                        }
                    }                                   
                }                               
            }
        }
        //判断开关按钮的状态
        for(var m=0;m<pointList.length;m++){
            for(var key in pointList[m]){
                var keyname = key;
                var points = pointList[m][key];
                for(var n=0;n<points.length;n++){
                    if(Math.max.apply(null, points) == Math.min.apply(null, points) && Math.min.apply(null, points)== 255){
                        $("#" + keyname).val("255");
                        $("#" + keyname+".switch-button").removeClass("offline");
                        $("#" + keyname+".switch-button").removeClass("partonline");
                        $("#" + keyname+".switch-button").addClass("online");       
                        $("#" + keyname+".circle-button").removeClass("circleoffline");
                        $("#" + keyname+".circle-button").removeClass("circlepartonline");
                        $("#" + keyname+".circle-button").addClass("circleonline");
                    }else if(Math.max.apply(null, points) == Math.min.apply(null, points) && Math.min.apply(null, points)== 0){
                        $("#" + keyname).val("0");
                        $("#" + keyname+".switch-button").removeClass("online");
                        $("#" + keyname+".switch-button").removeClass("partonline");
                        $("#" + keyname+".switch-button").addClass("offline");
                        $("#" + keyname+".circle-button").removeClass("circleonline");
                        $("#" + keyname+".circle-button").removeClass("circlepartonline");
                        $("#" + keyname+".circle-button").addClass("circleoffline");
                    }else{
                        $("#" + keyname).val("0");
                        $("#" + keyname+".switch-button").removeClass("online");
                        $("#" + keyname+".switch-button").removeClass("offline");
                        $("#" + keyname+".switch-button").addClass("partonline");
                        $("#" + keyname+".circle-button").removeClass("circleonline");
                        $("#" + keyname+".circle-button").removeClass("circleoffline");
                        $("#" + keyname+".circle-button").addClass("circlepartonline");
                    }
                }
                
            }
            
        }
        
        }
        }
	}
	//点击组织结构 展开/收起
	$(function() {
	    zzjg_tree();
	    $(".left_tree").hide();
	    $(".monitoringhome_zzjgtree").click(function() {
	        if (left_cols_mark == 1) {
	            left_cols_mark = 0;
	            $(".left_tree").hide();
	            $(".monitoringhome_zzjgtree").css("margin-left", "");
	            $(".cross_url").css({
	                "background": "url(" + integration_image + ") no-repeat 0 0",
	                "background-size": "100% 100%"
	            });
	            $("#allmap").css("width", "100%");
	            $("#allmap").removeAttr("float");
	        } else {
	            left_cols_mark = 1;
	            $(".left_tree").show();
	            $(".monitoringhome_zzjgtree").css("margin-left", "15%");
	            $(".cross_url").css({
	                "background": "url(" + integration_image + ") no-repeat 0 0",
	                "background-size": "100% 100%"
	            });
	            $("#allmap").css({
	                "width": "75.5%",
	                "float": "right"
	            });
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
	                            layout = nodeData.nodeType; //布局类型
	                            gzt_fhbh = nodeData.nodeTreeId;//分户编号 本以趋于平淡,为何又要掀起波澜
	                            onNodeSelected(); //点击事件
	                            SearchData(gzt_fhbh);//总能耗
                                illumination(gzt_fhbh,"");//照明环比
                                illuminationReal(gzt_fhbh);//照明实时数据
                                energyAnalyze(gzt_fhbh);//能耗分析
                                leftRefresh(gzt_fhbh);//左侧数据对接
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
	        integration_image = "url(static/images/zzjg/cross.jpg)";
	    }
	    $(".cross_url").css({
	        "background": "url(" + integration_image + ") no-repeat 0 0",
	        "background-size": "100% 100%"
	    });
	    if (left_cols_mark == 1) { //开
	        $("#allmap").css({
	            "width": "75.5%",
	            "float": "right"
	        });
	    } else {
	        $("#allmap").css("width", "100%");
	        $("#allmap").removeAttr("float");
	    }
	    loadJkPage(integration_image);
	}
	//刷新灯光监控
	function loadJkPage(nodePage){
            var variableUrl = nodePage;
            if(layout=='0'){//没有配置监控的
            	return false;
            }
            $.issp_ajax({//加载树节点信息
                isShowLoader : false,
                type : "post",
                url : "${ctx}/view/realtimemonitoring/BESZmjk/" + variableUrl,
                success : function(returnObject) {
                    $("#allmap").html(returnObject);
                    monitoringRefresh();//点击树刷新监控
                },
                error : function(returnObject) {
                    swal(returnObject.msg, "", "error");
                }
            });
        }

    //根据组织机构选中节点查询能耗数据
    function SearchData(nodeId){
        $.issp_ajax({
            type: "post",
            url: "${ctx}/view/dataAnalysises/getHouseHoldData",
            dataType: "json",
            async: true,
            traditional: true,//组织深度序列化 
            data: {
                "fType": "2",//月
                "fCjsj_start":getFormatDate(getCurrentYear()[0]),
                "fCjsj_end": getFormatDate(getCurrentYear()[1]),
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
                    var sj = arr[a].fCjsj.substr(5,2);
                    fCjsj.push(sj+"月");
                    fData.push(arr[a].fData || 0);
                }
                leftTop(fCjsj,fData);//总耗能
            }
        });
    }
    function change_tqtime(start,end){
        if (start != null && end != null) {
            var t = 2 * start.getTime() - end.getTime();
            if (start.getTime() == end.getTime()) {
                start.setTime(start.getTime() - 24 * 60 * 60 * 1000);
                end.setTime(start.getTime());
            } else {
                end.setTime(start.getTime());
                start.setTime(t);
            }
            ftbhb_sametime_start=getFormatDate(start);
            ftbhb_sametime_end=getFormatDate(end);
        }
    }
    //照明 环比
    function  illumination(nodeId,nodeText) {
        //环比--本月--月--月数据对比
        var time = getCurrentMonth();
        var fCjsj_start = getFormatDate(time[0]);
        var fCjsj_end = getFormatDate(time[1]);
        var ftbhb_sametime_start="";
        var ftbhb_sametime_end = "";
        change_tqtime(time[0],time[1]);
        $.issp_ajax({
            type: "post",
            url: "${ctx}/view/basedatamanage/workbench/illumination",
            dataType: "json",
            async: true,
            traditional: true,
            data: ({
                "fType": "2",//月
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
                nxfx1(legend,seriesData,timeData);
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
        var month = getFormatDate(getCurrentMonth()[0]).substr(5,2);//本月
        var lastmonth = getFormatDate(getPreviousMonth()[0]).substr(5,2);//上月
        $.issp_ajax({
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
                "lastmonth":lastmonth
            },
            success: function(result) {
                if(result.hasOwnProperty("list")){
                    var obj = result.list[0];
                    var fUnitareaData=obj.fUnitareaData;
                    var fPermanData=obj.fPermanData;
                    var fAlldata = obj.fAlldata;
                    $("#gzt_fUnitareaData").empty().text(fUnitareaData+" Kw/平");
                    $("#gzt_fPermanData").empty().text(fPermanData+" Kw/人");
//                     $("#gzt_year_alldata").empty().text(fAlldata+" Kw");//总能耗
//                     $("#gzt_year_zmdata").empty().text(fAlldata+" Kw");//照明
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
                    var InstantPower = data.InstantPower;
                    $("#gzt_instantaneous").empty().append("<span style='color: #E0D674;'>"+InstantPower+" Kw</span>");//瞬时功率
                    $("#gzt_peak").empty().append("<span style='color: #E0D674;'>"+peakEnergy+" Kw</span>");//峰值
                    $("#gzt_LastYearData").empty().append("<span style='color: #E0D674;'>"+LastYearData+" Kwh</span>");//上月
                    $("#gzt_LastMonthData").empty().append("<span style='color: #E0D674;'>"+LastMonthData+" Kwh</span>");//上月
                    $("#gzt_yesterdayData").empty().append("<span style='color: #E0D674;'>"+yesterdayData+" Kwh</span>");//昨天
                }
            }
        });
    }
    //照明实时数据
    function illuminationReal(nodeId){
        var today = getFormatDate(getCurrentDate());
        $.issp_ajax({
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
                    fData.push(arr[a].fData || 0);
                }
                sbzt1(fCjsj,fData);//总耗能
            }
        });
    }
    //能耗分析
    function energyAnalyze(nodeId){
        var today = getFormatDate(getCurrentDate());
        $.issp_ajax({
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
                $("#gzt_jrhn").text(data+"Kwh");
            }
        });
    }

	nxfx1();
	nxfx2();
	nxfx3();
	nxfx4();

	sbzt1();
	sbzt2();
	sbzt3();
	sbzt4();

	leftTop();
</script>