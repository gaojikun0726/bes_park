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

    .ztree li a.curSelectedNode {
        padding-top: 0px;
        background-color: rgba(31, 255, 8, 0);
        color: black;
        height: 16px;
        border: 0px #FFB951 solid;
        opacity: 0.8;
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
        padding: 5px;
        font-size: 1vw;
        font-weight: 600;
    }

    .left_div {
        padding: 5px 0 0 0;
        width: 56%;
        float: left;
        text-align: right;
    }

    .div_bottom_left_div {
        padding: 10px 0 0 0;
        margin-left: -2px;
        width: 58%;
        float: left;
        text-align: right;
    }


    .center_div {
        padding: 5px 0 0 2px;
        width: 10%;
        float: left;
        text-align: right;
    }

    .center_divyeardata{
        padding: 5px 0 0 2px;
        float: left;
        text-align: right;
    }



    .right_div {
        padding: 5px 0 0 2px;
        width: 20%;
        float: left;
        text-align: left;
    }

    .right_divdataUnit{
        padding: 5px 0 0 2px;
        float: left;
        text-align: left;
    }

    .left_div_span {
        display: block;
        margin: 0 0 -12px 0;
        font-weight: 700;
    }
    .left_div_span_nd{
        display: block;
        margin: 0 0 -7px 0;

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

  .div-border {
    padding: 10px !important;
  }

  #zd_float {
    position: absolute;
    top: 1px;
    right: 0px;
    width: 68px;
  }

  #zdReturn2JtBtn {
    height: 3.6vh;
  }


    .black_overlay{
        display: none;
        position: absolute;
        top: 0%;
        left: 0%;
        width: 100%;
        height: 100%;
        background-color: black;
        z-index:1001;
        -moz-opacity: 0.8;
        opacity:.80;
        filter: alpha(opacity=88);
    }

    .white_content{
        display: none;
        position: absolute;
        top: 5%;
        left: 25%;
        width: 40%;
        height: 90%;
        border: 1px solid black;
        background-color:#114965;
        z-index:1002;
        overflow: auto;
    }

    .header_close{
        height: 34px;
        top: 0%;
        width: 100%;
        z-index:1003;
        width: 100%;
        background-color:#108CA7;
    }

    .buttonstyle{
        font-size: 23px;
        float: right;
    }

    .textstyle{
        font-size: 20px;
    }

    .fxpzSetUp_green{
        position: absolute;
        /*top: 0px;*/
        height: 5.8%;
        /*right: 96px;*/
        left: 20px;
        width: 2%;
        margin-right: 30px;
        background: url(${ctx}/static/images/top/logo.png);
        /*animation:fade 3000ms infinite;*/
        /*-webkit-animation:fade 1000ms infinite;*/
    }
    .right_div .font_color{
        /*color: greenyellow; */
        /*color: #15d58a;*/
        color:#E0D674;
    }
    #gzt_fxpz .text_style{
        font-weight: 700;
    }
    #gzt_ndznh{
        height: 25vh;
        background-color: #ffffff;
        position: absolute;
        top: 5vh; right: 1vh;
        width: 35vh;
        border: 1px solid #366c90;
    <#--background: url(${ctx}/static/images/top/L1.png);-->
        /*box-shadow: -5px 7px 8px black;*/
    }
</style>

<#--<div style="width: 100%; height: 100%; background-image: url(static/images/backImg5.jpg); background-size: 100% 100%;">-->
<div style="width: 100%; height: 100%; ">
    <div class="left_content_div" style="width: 100%; height: 100%; padding-right: 20px">
        <!-----左侧区域---->
        <div style="width: 80%; height: 100%; float: left;">
            <!-----左侧上半部区域---->
            <div class="left_top_div" style="width: 100%; height: 70%; float: left;">
                <div class="left_top_div_inner" style="width: 20%; height: calc(100% - 20px); float: left; padding-right: 20px;">
                    <div class="div-border" id="leftTop1" style="width: 100%; height: 40%;">
                        <div class="div_top" style="width: 100%; height: 70%; /*border-bottom: 1px solid #02dabc80;*/">
                            <li class="first_li " id="yqmc"></li>
                            <div class="left_div" style="width:50%;">
                                <span class="left_div_span">节点编码 : </span></br> <span class="left_div_span">建筑物年代 : </span></br> <span class="left_div_span">建筑物人口 : </span></br> <span class="left_div_span">建筑物面积
                                    : </span></br> <span class="left_div_span" style="display: block;">系统已运行 : </span></br>
                            </div>
                            <div class="right_div" style="width:50%;">
                                <span id="F_NODE_CODE" class="left_div_span font_color" > </span></br>
                                <span id="F_BUILD_TIME" class="left_div_span font_color">  </span></br>
                                <span id="F_PERSON_NUMS" class="left_div_span font_color">  </span></br>
                                <span id="F_ALL_AREA" class="left_div_span font_color" >  </span></br>
                                <span class="left_div_span" style="display: block; color: #E0D674;" id="gzt_runingTime"> ${day}<span style="color: #8fe3f7">天</span>
                                    <span class="font_color">${hour} <span
                                                style="color: #8fe3f7">小时</span>
                                    </span>
                                </span>
                            </div>
                        </div>
                    </div>

        <div class="div-border" id="gzt_fxpz" style="width: 100%; height: 60%;">
            <div class="div_top" style="width: 100%;">
                <ul>
                    <li>
                        <p class="first_li">分项年度总耗能</p>
                        <img id="gzt_fxpz_image" src="${ctx }/static/images/top/setUp.png" onclick="newmain_index.openDialog()">
                    </li>
                </ul>
            </div>

            <div style="display: flex;width: 100%" class="text_style">
                <div class="left_div" style="padding: 3px 0 0 5px;">

                    <ul id="gzt_year_dataName">

                    </ul>

                </div>

                <div style="width: 40%;display: flex">
                <div class="center_divyeardata" style="padding: 3px 0 0 5px;color:  #15d58a">

                    <ul id="gzt_year_data">

                    </ul>

                </div>

                <div class="right_divdataUnit" style="padding: 3px 0 0 10px;">

                    <ul id="gzt_year_dataUnit">

                    </ul>

                </div>
                </div>
            </div>


        </div>
      </div>
                <div class="map-border">
                    <!--左侧树结构 -->
                    <div class="monitoringhome_zzjgtree">
                        <span>详</span><span>细</span><span>结</span><span>构</span>
                    </div>
                    <div class="left_tree">
              <div class="form-group">
                <input type="input" class="form-control" placeholder="请输入分户名称..." id="input-search_index" value="" onkeyup="searchUnitName()">
              </div>
                        <div id="tree_zzjg_CM" class="tree_zzjg_CM"></div>
                        <div id="zd_float" style="display:none;">
                            <button class="btn btn-primary" id="zdReturn2JtBtn">返回</button>
                        </div>
                    </div>

                    <div id="allmap" style="width: 100%; height: 100%; padding-left: 20px;"></div>
                    <div id="gzt_ndznh">
                        <div class="div-border1" style="width: 100%; height: 50%;">
                            <div class="div_top" style="width: 100%;">
                                <ul>
                                    <li class="first_li" id="gzt_nhfx">能耗分析</li>
                                    <div class="left_div" style="width:43%;">
                                        <span class="left_div_span">今日能耗（日）：</span></br>
                                        <span class="left_div_span">峰值能耗（日）：</span></br>
                                        <span class="left_div_span">本月能耗（月）：</span></br>
                                        <span class="left_div_span">峰值能耗（月）：</span></br>
                                        <span class="left_div_span">本年能耗（年）：</span></br>
                                        <span class="left_div_span">峰值能耗（年）：</span></br>
                                    </div>
                                    <div class="right_div" style="width:57%;">
                                        <span class="left_div_span" style="color: #E0D674;" id="gzt_jrhn"></span></br>
                                        <span class="left_div_span" style="color: #E0D674;" id="gzt_peak"></span></br>
                                        <span class="left_div_span" style="color: #E0D674;" id="gzt_water"></span></br>
                                        <span class="left_div_span" style="color: #E0D674;" id="gzt_natgas"></span></br>
                                        <span class="left_div_span" style="color: #E0D674;" id="gzt_energy"></span></br>
                                        <span class="left_div_span" style="color: #E0D674;" id="gzt_energyPeak"></span></br>
                                    </div>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-----左侧下半部区域---->
            <div class="left_bottom_div" style="width: 100%; height: calc(30% - 20px); float: left;">
                <div class="div-border" id="gzt_alarm" style="width: calc(20% - 20px); height: 100%; float: left; padding-left: 10px;">
                </div>
                <div class="div-border" id="gzt_bottomTwo" style="width: calc(20% - 20px); height: 100%; float: left;"></div>
                <div class="div-border" id="gzt_bottomOne" style="width: calc(20% - 20px); height: 100%; float: left;"></div>
                <div class="div-border" id="gzt_bottomThree" style="width: calc(20% - 20px); height: 100%; float: left;"></div>
                <div class="div-border" id="gzt_bottomFour" style="width: calc(20% - 20px); height: 100%; float: left;"></div>
            </div>

        </div>

        <!-----右侧区域---->
        <div class="right_content_div" style="">
            <div id="gzt_rightSecond" class="div-border" style="width: 100%; height: 25%;"></div>
            <div id="today_tendency" class="div-border" style="width: 100%; height: 25%;"></div>
            <div id="gzt_rightThird" class="div-border" style="width: 100%; height: 25%;"></div>
            <div id="gzt_rightFour" class="div-border" style="width: 100%; height: 25%;"></div>
        </div>


<#--        点击分项配置图标模态框出现-->
        <div class="modal fade" id="modal-form-addFxpz" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
            <div class="modal-dialog">
                <div class="modal-content" style="height: 100%;width: 75%;">
                    <div class="modal-header bg-primary">
                        <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                        <h4 class="modal-title addIcon">添加分项设置配置</h4>
                    </div>


                    <div class="modal-body">
                        <div id="addFxpzTree" class="ztree"></div>
                    </div>

                    <div class="design_win_btn_div" style="margin-top: 450px;margin-left:302px;position: fixed;bottom: 24px;">
              <button class="btn btn-white m-l-sm" type="button"
                      style="float: right;margin-right: 5%;width: 40%;height: 37%;"
                      onclick="newmain_index.closeModelData()">取消
              </button>
              <button class="btn btn-md btn-primary" aria-hidden="true" data-dismiss="modal" type="button"
                      onclick="newmain_index.insertYearData()" style="margin-right: 5%;width: 40%;height: 37%;">确定
              </button>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
<script src="${ctx}/static/js/time_range.js"></script>
<script type="text/javascript">
  ;
  var newmain_index = (function ($, window, document, undefined) {
    var _ctx = '${ctx}';
    var nodeId = "";//组织结构树ID
    var workbench_yqbh = "0000";
    var nybh = "01000";
    var $searchableTree_index;
    //点击组织结构 展开/收起
    $(function(){
        var left_cols_mark = 0 ;
        zzjg_tree();

        try {
          initBaiduMap();//地图
        }catch (e) {
          console.log("地图加载不出来");
        }


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
        document.getElementById("zd_float").style.display="";//显示
        $("#zdReturn2JtBtn").click(function() {
            zzjg_tree();//返回查询所有水电气下所有园区的页面
        });
    })

    var yqbh;
    var nydw;
    var nymc;

    //首页 加载树   组织机构
    function zzjg_tree(fYqbh, fNybh) {
      $.ajax({
        type: "post",
        //url: "${ctx}/view/basedatamanage/energyinformation/NyhouseHold_treegrid",
        url: "${ctx}/view/basedatamanage/energyinformation/getHomePageTreeData",
        // url: _ctx + '/view/basedatamanage/highway/highwaySite/getHomePageTreeData',
        dataType: "json",
        async: false,
        data: ({
          fYqbh: fYqbh,
          fNybh: fNybh
        }),
        success: function (result) {

          var status = result && result.status;

          if (status !== '1') {
            return;
          }

          var data = result.data;

          if (!Array.isArray(data) || data.length === 0) {
            return;
          }

          var treeData = buildTree({
            data: data,
            id: 'F_FHBH',
            parentId: 'F_PFHBH',
            children: 'nodes',
            dataName: 'F_FHMC',
            treeName: 'text',
          });

          var treeObj = {};

          for (var i = 0; i < treeData.length; i++) {
            if (treeObj[treeData[i]['F_NYBH']] === undefined) {
              treeObj[treeData[i]['F_NYBH']] = {
                text: treeData[i]['F_NYMC'],
                F_NYBH: treeData[i]['F_NYBH'],
                F_NYMC: treeData[i]['F_NYMC'],
                F_PRICE: treeData[i]['F_PRICE'],
                F_COAL_AMOUNT: treeData[i]['F_COAL_AMOUNT'],
                F_CO2: treeData[i]['F_CO2'],
                F_UNIT: treeData[i]['F_UNIT'],
                nodes: [treeData[i]]
              };

            } else {
              treeObj[treeData[i]['F_NYBH']].nodes.push(treeData[i]);
            }
          }

          treeData = Object.values(treeObj);
          $searchableTree_index = $('#tree_zzjg_CM').treeview({
            data: treeData,
            highlightSelected: true,
            levels: 2,
            enableLinks: true,
            allowReselect: true,//允许重复点击
            // color: "#4a4747",
            searchResultColor: "red",
            onNodeSelected(event, nodeData) { //节点点击事件
              var id = nodeData.F_FHBH;
              nybh = nodeData.F_NYBH;
              yqbh = nodeData.F_YQBH;
              nydw = nodeData.F_UNIT;
              nymc = nodeData.F_NYMC;
              // TodayTendency(nodeId);//今日耗能
              // energyAnalyze(nodeId);//能耗分析
              // leftRefresh(nodeId);//左侧数据对接
              leftInfo(nodeData.F_YQBH || nodeData.nodes[0].F_YQBH);//左上信息
              //点击节点的时候,获取点击节点下所有的子节点,拼接成数组对象,传到后台
              let childList = nodeData.nodes;
              let fhbhList = [];

              if (typeof childList != 'undefined') {

                childList.forEach((e, i) => {

                  fhbhList.push(e.F_FHBH);
                });
                //今日能耗排行
                TodayRankEnergyNew(fhbhList);
                monthRanking(fhbhList); // 月排行


                if (nodeData.level == 1) {//今日能耗趋势单独判断
                  //今日耗能趋势
                  TodayTendencyNew(fhbhList);
                  // 本月用电趋势
                  monthTendency(fhbhList);
                  //今日同环比,本月同环比
                  sameRingRatio(fhbhList,0);//今日同比
                  sameRingRatio(fhbhList,1);//今日环比
                  sameRingRatio(fhbhList,2);//本月同比
                  sameRingRatio(fhbhList,3);//本月环比

                  YearEnergyValue(fhbhList);//当年某能源的数据
                  //今日环比
                  //本月同比
                  //本月环比
                  //
                } else {
                  fhbhList.length = 0;
                  fhbhList.push(nodeData.F_FHBH);
                  TodayTendencyNew(fhbhList);
                  // 本月用电趋势
                  monthTendency(fhbhList);
                  //今日同环比,本月同环比
                  sameRingRatio(fhbhList,0);//今日同比
                  sameRingRatio(fhbhList,1);//今日环比
                  sameRingRatio(fhbhList,2);//本月同比
                  sameRingRatio(fhbhList,3);//本月环比

                  YearEnergyValue(fhbhList);//当年某能源的数据
                }


              } else if (typeof childList == 'undefined') {//如果没有子节点,获取父节点下同级节点
                if (typeof (nodeData.F_FHBH) != 'undefined') {//如果(电水气)下没有子节点,则不做逻辑处理
                  fhbhList.push(nodeData.F_FHBH);
                  let brother = $('#tree_zzjg_CM').treeview('getSiblings', nodeData);
                  brother.forEach((r, j) => {
                    fhbhList.push(r.F_FHBH);
                  });
                  //今日能耗排行
                  TodayRankEnergyNew(fhbhList);
                  monthRanking(fhbhList);// 月排行

                  if (nodeData.level == 1) {//今日能耗趋势单独判断
                    //今日耗能趋势
                    TodayTendencyNew(fhbhList);
                    // 本月用电趋势
                    monthTendency(fhbhList);

                    //今日同环比,本月同环比
                    sameRingRatio(fhbhList,0);//今日同比
                    sameRingRatio(fhbhList,1);//今日环比
                    sameRingRatio(fhbhList,2);//本月同比
                    sameRingRatio(fhbhList,3);//本月环比

                    YearEnergyValue(fhbhList);//当年某能源的数据
                  } else {
                    fhbhList.length = 0;
                    fhbhList.push(nodeData.F_FHBH);
                    //今日耗能趋势
                    TodayTendencyNew(fhbhList);
                    // 本月用电趋势
                    monthTendency(fhbhList);

                    //今日同环比,本月同环比
                    sameRingRatio(fhbhList,0);//今日同比
                    sameRingRatio(fhbhList,1);//今日环比
                    sameRingRatio(fhbhList,2);//本月同比
                    sameRingRatio(fhbhList,3);//本月环比

                    YearEnergyValue(fhbhList);//当年某能源的数据
                  }
                }
              }
              if (nodeData.level === 1) {
                nybh = nodeData.F_NYBH;
                if (typeof (fYqbh) == "undefined") {
                  yqbh = nodeData.F_YQBH;
                } else {
                  yqbh = fYqbh;
                }

                // YearEnergyValue(nybh, nymc, nydw);//当年某能源的数据
              } else {
                var id = nodeData.F_FHBH;
                nybh = nodeData.F_NYBH;
                yqbh = nodeData.F_YQBH;
                // YearEnergyValuePack(nybh, yqbh, id);//当年某能源的数据
              }
            },
          });
          var firstNode = $("#tree_zzjg_CM").treeview('findNodes');

                $("#tree_zzjg_CM").treeview("selectNode", firstNode); //将第一个节点设置为选中状态

                // leftRefresh('01'); // 峰值电能
                totalEnergyConsumption('02', function (data) { // 总能耗水
                    $("#gzt_year_alldata_water").empty().text(data+" m³");//总能耗
                });
                totalEnergyConsumption('03', function (data) { // 总能耗气
                    $("#gzt_year_alldata_natgas").empty().text(data+" m³");//总能耗
                });

                getSubentryData({   // 照明总耗能
                    fType: '3',
                    fFxbh: '01A00',
                    fCjsj: getFormatDate(getCurrentDate()).substr(0,4),
                }, function (result) {
                    var data = result && result.data;
                    if(Array.isArray(data) && data[0]&& data[0].fData){

                        // var val = data[0].fData;
                        // $("#gzt_year_zmdata").text(val +" Kwh");
                    }

                });

                getSubentryData({   // 空调总耗能
                    fType: '3',
                    fFxbh: '01B00',
                    fCjsj: getFormatDate(getCurrentDate()).substr(0,4),
                }, function (result) {
                    var data = result && result.data;
                    if(Array.isArray(data) && data[0] && data[0].fData){

                        var val = data[0].fData;
                        $("#gzt_year_ktdata").text(val +" Kwh");
                    }

                });

                getSubentryData({   // 动力总耗能
                    fType: '3',
                    fFxbh: '01C00',
                    fCjsj: getFormatDate(getCurrentDate()).substr(0,4),
                }, function (result) {
                    var data = result && result.data;
                    if(Array.isArray(data) && data[0] && data[0].fData){

                        var val = data[0].fData;
                        $("#gzt_year_dldata").text(val +" Kwh");
                    }

                });

                getSubentryData({   // 特殊总耗能
                    fType: '3',
                    fFxbh: '01D00',
                    fCjsj: getFormatDate(getCurrentDate()).substr(0,4),
                }, function (result) {
                    var data = result && result.data;
                    if(Array.isArray(data) && data[0] && data[0].fData){

                        var val = data[0].fData;
                        $("#gzt_year_tsdata").text(val +" Kwh");
                    }

                });

            },
            error: function(nodeData) {
                swal(nodeData.msg, "", "error");
            }
        });
    }


    //今日能耗排行
    function TodayRankEnergyNew(data, rootNode) {
      var today = getFormatDate(getCurrentDate());//本天时间
      $.ajax({
        type: "post",
        url: "${ctx}/view/basedatamanage/workbench/GetTodayRankEnergyNew",
        dataType: "json",
        async: true,
        traditional: true,
        data: {
          "count": "5",//条数
          "fCjsj_start": today,
          "fCjsj_end": today,
          "fFhbh": data,
          "nybh": nybh,
          "rootNode": rootNode//是否为(水电气)根节点
          // "yqbh": yqbh,
        },
        success: function (result) {

          if (result.hasOwnProperty("list")) {
            var TodayRankEnergy = result.list[0].TodayRankEnergy;//日list
            var nymc = result.data.nymc;
            var nydw = result.data.nydw;
            var fhlx = result.data.fhlx;
            var Todaylegend = [];//legend
            var TodayYAxisData = [];//yAxis
            var TodaySeriesData = [];//series
            if (typeof (TodayRankEnergy) == "undefined") {//如果信息未修改，直接根据节点信息加载属性页面
              if (result.status == "1") {//当前能源类型有数据
                for (var j = 0; j < result.list.length; j++) {
                  var time0 = result.list[j].fCjsj.substr(8, 2) + "日";
                  var time = result.list[j].fFhmc;
                  var data = result.list[j].fData;
                  if (j == 0) {
                    Todaylegend.push(time0);
                  }
                  TodayYAxisData.push(time);
                  TodaySeriesData.push(data);
                  // if ((j - 4) > 0) {
                  //   TodayYAxisData.shift();
                  //   TodaySeriesData.shift();
                  // }
                }
              }

            }

            gzt_rightSecond(nymc, nydw, fhlx, Todaylegend, TodayYAxisData, TodaySeriesData);
          }
        }
      });
    }

    //今日耗能趋势
    function TodayTendencyNew(data) {
      var today = getFormatDate(getCurrentDate());
      $.ajax({
        type: "post",
        url: "${ctx}/view/basedatamanage/workbench/getHouseHoldDataNew",
        dataType: "json",
        async: true,
        traditional: true,
        data: {
          "fType": "0",//时
          "fCjsj_start": today,
          "fCjsj_end": today,
          "fFhbhs": data,
          "nybh": nybh
        },
        success: function (result) {

          let nymc = result.data.nymc;
          let nydw = result.data.nydw;
          let fCjsj = [];
          let fData = [];
          if (result.status == '0') {
            for (let i = 0; i < result.list.length; i++) {
              fCjsj.push(result.list[i].time);
              fData.push(result.list[i].data);
            }
          }
          let todayAnalyzeData = sum(fData);//获取今日能耗
          todayAnalyzeData = todayAnalyzeData.toFixed(2);//保留两位小数
          let maxTodayData = null;

          if(fData.length == 0) {
            maxTodayData = 0;
          } else {
            maxTodayData = Math.max.apply(null, fData);//获取今日峰值能耗
          }
          maxTodayData = maxTodayData.toFixed(2);//保留两位小数
          today_tendency(nymc, nydw, fCjsj, fData);//今日用电
          todayAnalyze(nymc, nydw, todayAnalyzeData,maxTodayData); // 能耗分析（今日,峰值）
        },
      });
    }
    //求和
    function sum(arr) {
      var len = arr.length;
      if(len == 0){
        return 0;
      } else if (len == 1){
        return arr[0];
      } else {
        return arr[0] + sum(arr.slice(1));
      }
    }

    //今日同比
    function sameRingRatio(data,type) {
      //同比--本月--月--月数据对比
      let time = getFormatDate(getCurrentDate());//当前时间
      let lestYearTime = getFormatDate(getPreviouDate());//去年当前时间
      let lestDayTime = getLestDayDate();//昨天的时间

      let month = getCurrentMonth();//获得本月的起止时间
      month = getFormatDate(month[0]);
      let lestMonth = getPreviousMonth();//获取上一月的起止日期
      lestMonth = getFormatDate(lestMonth[0]);
      let lestYearMonth = getFormatDate(getLestYearMonth());//获得获取去年这个月的起止时间

      let firstTime = null;
      let secondTime = null;
      if (type == 0) {//0:今日同比 1:今日环比 2:本月同比 3:本月环比
        firstTime = time;
        secondTime = lestYearTime;
      } else if (type == 1) {
        firstTime = time;
        secondTime = lestDayTime;
      } else if (type == 2) {
        firstTime = month;
        secondTime = lestYearMonth;
      } else if (type == 3) {
        firstTime = month;
        secondTime = lestMonth;
      }

      $.ajax({
        type: "post",
        url: "${ctx}/view/basedatamanage/workbench/illuminationNew",
        dataType: "json",
        async: true,
        traditional: true,
        data: ({
          "type": type,//0:今日同比 1:今日环比 2:本月同比 3:本月环比
          "fFhbhs":data,
          "firstTime": firstTime,
          "secondTime": secondTime,
          "nybh": nybh
        }),
        success: function (result) {
          let legend = result.data.legend;
          let seriesData = result.data.seriesData;
          let timeData = result.data.timeData;

          let nymc = result.data.nymc;
          let nydw = result.data.nydw;

          if (type == 0) {//0:今日同比 1:今日环比 2:本月同比 3:本月环比
            gzt_rightThird(nymc, nydw, legend, seriesData, timeData);
          } else if (type == 1) {
            gzt_rightFour(nymc, nydw, legend, seriesData, timeData);
          } else if (type == 2) {
            gzt_bottomThree(nymc, nydw, legend, seriesData, timeData);
          } else if (type == 3) {
            gzt_bottomFour(nymc, nydw, legend, seriesData, timeData);
          }

        }
      });
    }

    function monthTendency(householdSerials)
    {
      if (!Array.isArray(householdSerials))
      {
        return;
      }

      var startTime = utils.dateFormat(new Date(), 'yyyy-MM-01')
      var endTime = utils.dateFormat(new Date(), 'yyyy-MM-dd')
      var type = '1';

      queryHouseHoldData(householdSerials, type, startTime, endTime, response => {

        var status = response && response.status;

        if (status !== '1')
        {
          return;
        }

        var dataList = response.data;

        if (!Array.isArray(dataList))
        {
          return;
        }

        var dataMap = new Map();

        dataList.forEach(household => {
          var fCjsj = household.fCjsj;
          if (!dataMap.get(fCjsj))
          {
            dataMap.put(fCjsj, new Map());
          }
          dataMap.get(fCjsj).put(household.fFhbh, household);
        });

        dataMap.values().forEach(item => {

        })

        var dataX = [];
        var dataY = [];

        dataMap.keySet().forEach(time => {
          dataY.push(dataMap.get(time).values().reduce((total, obj) => total + obj.fData, 0).toFixed(2));
          dataX.push(time.slice(8, 10));
        })

        gzt_bottomOne(dataX, dataY, nydw, nymc);

        monthAnalyze(nymc, nydw, dataY);

      });

    }

    /**
     * 本月人均排行
     */
    function monthRanking(householdSerials)
    {
      if (!Array.isArray(householdSerials))
      {
        return;
      }
      var type = '2';

      var time = utils.dateFormat(new Date(), 'yyyy-MM-01')

      queryHouseHoldData(householdSerials, type, time, time, response => {

        monthRankingHandle(response);
        monthPerCapitaRankingHandle(response);

      });

    }

    function monthRankingHandle(response)
    {
      var status = response && response.status;

      if (status !== '1')
      {
        return;
      }

      var dataList = response.data;

      if (!Array.isArray(dataList))
      {
        return;
      }

      var dataMap = new Map();

      dataList.forEach(household => dataMap.put(household.fFhbh, household));

      var dataArray = dataMap.values();

      dataArray.sort((a, b) => {

        var fPersonNumsA = a.fPersonNums;
        var fPersonNumsB = b.fPersonNums;

        if(!fPersonNumsA || '0' === fPersonNumsA)
        {
          fPersonNumsA = 1;
        }

        if(!fPersonNumsB || '0' === fPersonNumsB)
        {
          fPersonNumsB = 1;
        }
        return a.fData/fPersonNumsA - b.fData/fPersonNumsB
      });

      var dataY = [];
      var dataX = [];
      var fhdw = ''; // 分户单位

      dataArray.forEach(data => {

        dataY.push(data.fFhmc);
        var fPersonNums = data.fPersonNums;

        if(!fPersonNums || '0' === fPersonNums)
        {
          dataX.push((data.fData).toFixed(2));
        }else {
          dataX.push((data.fData/fPersonNums).toFixed(2));
        }
        if(!fhdw)
        {
          fhdw = data.fFhlx
        }

      });

      monthRankingChars(nymc, nydw, fhdw, dataY, dataX) // 本月人均能耗 echars

    }

    function monthPerCapitaRankingHandle(response)
    {
      var status = response && response.status;

      if (status !== '1')
      {
        return;
      }

      var dataList = response.data;

      if (!Array.isArray(dataList))
      {
        return;
      }

      var dataMap = new Map();

      dataList.forEach(household => dataMap.put(household.fFhbh, household));

      var dataArray = dataMap.values();

      dataArray.sort((a, b) => a.fData - b.fData);

      var dataY = [];
      var dataX = [];
      var fhdw = ''; // 分户单位

      dataArray.forEach(data => {
        dataY.push(data.fFhmc);
        dataX.push((data.fData).toFixed(2));
        if(!fhdw)
        {
          fhdw = data.fFhlx
        }

      });

      monthPerCapitaRankingChars(nymc, nydw, fhdw, dataY, dataX) //右侧echarts 个人月数据
    }

    /**
     * householdSerials 分户编号-数组类型
     * type 统计类型-字符串 0-小时 1-天 2-月 3-年
     * startTime 开始时间-字符串
     * endTime 结束时间-字符串
     * callback 回调函数
     */
    function queryHouseHoldData(householdSerials, type, startTime, endTime, callback)
    {

      if(typeof callback !== 'function' || !householdSerials || !type || !startTime || !endTime)
      {
        return;
      }

      $.ajax({
        type: "get",
        url: "${ctx}/view/dataAnalysises/queryHouseHoldData",
        dataType: "json",
        traditional: true,
        data: {
          "fType": type,
          "fCjsj_start": startTime,
          "fCjsj_end": endTime,
          "fFhbhs": householdSerials
        },
        success: function (result) {
          callback(result);
        }
      });
    }

    //点击水电气获取年数据和月峰值
    function YearEnergyValue(data){
      var today = getFormatDate(getCurrentDate());//本天时间
      var year = today.substr(0,4);
      //本年第一天
      var firstDay = year + '-' + '01' + '-' + '01' + " " + '00'+':'+'00'+':'+'00';
      //本年的第二天
      var secondDay = year + '-' + '01' + '-' + '02';
      //本年的最后一天 获取本年中月份的峰值
      var endData = year + '-' + '12' + '-' + '31'  + " "  +  '00'+':'+'00'+':'+'00';
      $.ajax({
        type: "post",
        url: "${ctx}/view/basedatamanage/workbench/getEnergyYearDataNew",
        dataType: "json",
        async: true,
        traditional: true,
        data: ({
          "secondDay" : secondDay,
          "endData"   : endData,
          "firstDay"  : firstDay,
          "fhbhs"     : data,
          "nybh"      : nybh
        }),
        success: function(result) {
          let nymc = result.data.nymc;
          let nydw = result.data.nydw;
          let  energyConsumptionThisYear = result.list[0].energyConsumptionThisYear;
          energyConsumptionThisYear = Number(energyConsumptionThisYear).toFixed(2);
          let  peakEnergyConsumptionThisYear = result.list[0].peakEnergyConsumptionThisYear;
          peakEnergyConsumptionThisYear = Number(peakEnergyConsumptionThisYear).toFixed(2);
          YearEnergyData(nymc,nydw,energyConsumptionThisYear,peakEnergyConsumptionThisYear);
        }
      });
    }
    $(function () {
      selectSubYearData();
    })

    var list1 = [];

    function selectSubYearData() {

      // var year = getFormatDate(getCurrentDate()).substr(0,4);//本年
      var today = getFormatDate(getCurrentDate());//本天时间
      var year = today.substr(0, 4);
      //本年第一天
      var firstDay = year + '-' + '01' + '-' + '01' + " " + '00' + ':' + '00' + ':' + '00';
      var endDay = year + '-' + '12' + '-' + '31' + " " + '23' + ':' + '59' + ':' + '59';//年最后一天
      $.ajax({
        type: "post",
        url: "${ctx}/view/basedatamanage/workbench/selectEnergyYearData",
        dataType: "json",
        async: true,
        traditional: true,
        data: ({
          "firstDay": firstDay,
          "endDay": endDay,
        }),
        success: function (result) {

          var ydataName = [];
          var unit = [];
          var ydata = [];
          if (result.list != null && result.list != 0) {
            list1 = result.list;
            fxpz_tree();
            for (var i = 0; i < list1.length; i++) {
              ydataName.push('<li style="padding-bottom: 5px">' + list1[i].f_fxmc + '&nbsp:' + '</li>');
              ydata.push('<li style="padding-bottom: 5px">' + Number(list1[i].f_ydata).toFixed(2) + '</li>');
              unit.push('<li style="padding-bottom: 5px;float:left; width:60%;">'+list1[i].f_unit+'</li>');
            }
            $("#gzt_year_dataName").empty();
            $("#gzt_year_data").empty();
            $("#gzt_year_dataUnit").empty();

            $("#gzt_year_dataName").append(ydataName.join(''));
            $("#gzt_year_data").append(ydata.join(''));
            $("#gzt_year_dataUnit").append(unit.join(''));
          } else {
            list1 = [];
            nodeObj.length = 0;
            $("#gzt_year_dataName").empty();
            $("#gzt_year_data").empty();
            $("#gzt_year_dataUnit").empty();
            fxpz_tree();
          }
        },
        error: function (result) {
          swal(result.msg, "", "error");
        }
      })
    }

    //首页 加载树  分项树
    function fxpz_tree() {

      $.ajax({
        type: "post",
        url: "${ctx}/view/basedatamanage/energyinformation/getSubitemTreeData",
        dataType: "json",
        async: false,
        data: ({}),
        success: function (result) {


          var status = result && result.status;

          if (status !== '1') {
            return;
          }

          var data = result.data;

          if (!Array.isArray(data)) {
            return;
          }

          var tree = new Tree({
            container: 'addFxpzTree',
            idKey: 'F_FXBH',
            pIdKey: 'F_PFXBH',
            name: 'F_FXMC',
            showCheckbox: true,
            selected: false,
            setting: {
              view: {
                showIcon: false,
                // selectedMulti: true,
                txtSelectedEnable: false,
                fontCss: function (treeId, treeNode) {
                  return (treeNode.highlight)
                      ? {color: '#A60000', 'font-weight': 'bold'}
                      : {color: '#00050a', 'font-weight': 'normal'};
                }
              },
              check: {
                enable: true,
                chkboxType: {"Y": "", "N": ""}

              },
              callback: {
                setting: {
                  data: {
                    enable: false
                  }
                },
                onCheck: function (e, treeId, treeNode) {
                  var treeObj = $.fn.zTree.getZTreeObj("addFxpzTree");
                  var nodes = treeObj.getCheckedNodes();
                  if (nodes.length > 8) {
                    treeObj.cancelSelectedNode(treeNode);
                    treeNode.checked = false;
                    // treeNode.chkDisabled = true;
                  }
                  onChecked(nodes);
                },
              }
            },
          });
          tree.loadData(data);
          nodeObj = [];

          if (list1 == undefined) {
            return;
          } else {
            for (var i = 0; i < list1.length; i++) {
              var treeObj = $.fn.zTree.getZTreeObj("addFxpzTree");
              treeObj.expandAll(true);
              var node = treeObj.getNodeByParam("F_FXBH", list1[i].f_fxbh);
              if (node != null) {
                nodeObj.push(node);
                treeObj.checkNode(node, true, false);//根据该节点选中
              }
            }
          }
        }
      })
    }

    var nodeObj = [];

    //节点存储
    function onChecked(nodes) {
      nodeObj.length = 0
      if (nodes.length > 8) {
        swal({
          title: "选择的节点请在八个节点以内",
          text: "",// 内容
          type: "warning",
          showCloseButton: false, // 展示关闭按钮
          allowOutsideClick: false,
          confirmButtonColor: "#1ab394",
          showConfirmButton: false,
          timer: 2000
        });
      }
      if (nodeObj.length = 0) {
        var nodeData = {};
        nodeObj.push(nodeData);
      } else {
        for (var i = 0; i < nodes.length; i++) {
          var nodeData = {};
          nodeData.F_FXBH = nodes[i].F_FXBH;
          nodeData.F_FXMC = nodes[i].F_FXMC;
          nodeData.F_YQBH = nodes[i].F_YQBH;
          nodeData.F_ZZJGBH = nodes[i].F_ZZJGBH;
          nodeData.F_NYBH = nodes[i].F_NYBH;
          nodeObj.push(nodeData);
        }
      }
    }

    //添加到数据库
    function insertYearData() {
      var today = getFormatDate(getCurrentDate());//本天时间
      var year = today.substr(0, 4);
      //本年第一天
      var firstDay = year + '-' + '01' + '-' + '01' + " " + '00' + ':' + '00' + ':' + '00';
      //本年的最后一天 获取本年中月份的峰值
      var endDay = year + '-' + '12' + '-' + '31' + " " + '00' + ':' + '00' + ':' + '00';
      $.ajax({
        type: "post",
        url: "${ctx}/view/basedatamanage/workbench/insertSubyearData",
        dataType: "json",
        async: true,
        traditional: true,
        data: {
          "nodeObj": JSON.stringify(nodeObj),
          "firstDay": firstDay,
          "endDay": endDay
        },
        success: function (result) {
          var list1 = result.list;
          var ydataName = [];
          var unit = [];
          var ydata = [];
          if (result.list != null && result.list != 0) {
            var list1 = result.list;
            for (var i = 0; i < list1.length; i++) {
              ydataName.push('<li style="padding-bottom: 5px">' + list1[i].f_fxmc + '&nbsp:' + '</li>');
              ydata.push('<li style="padding-bottom: 5px">' + Number(list1[i].f_ydata).toFixed(2) + '</li>');
              unit.push('<li style="padding-bottom: 5px">' + list1[i].f_unit + '</li>');
            }
            $("#gzt_year_dataName").empty();
            $("#gzt_year_data").empty();
            $("#gzt_year_dataUnit").empty();
            $("#gzt_year_dataName").append(ydataName.join(''));
            $("#gzt_year_data").append(ydata.join(''));
            $("#gzt_year_dataUnit").append(unit.join(''));
          }
          if (result.status === '1') {
            swal({
              title: result.msg,
              text: "",
              type: "success",
              showCloseButton: false,
              allowOutsideClick: false,
              showConfirmButton: false,
              timer: 1000
            });
            $('#modal-form-addFxpz').modal(
                'hide');//关闭添加窗口
          } else {
            swal(result.msg, "", "error");
          }
          selectSubYearData();
        },
        error: function (result) {
          swal(result.msg, "", "error");
        },
      });
      //selectSubYearData();
      //expendAllYearData();//展开全部节点

    }

    function getSubentryData(option, callback) {

      option = option || {};

      $.ajax({
        type: "get",
        url: "${ctx}/view/getSubentryData",
        dataType: "json",
        data: {
          fType: option.fType,
          fId: option.fId,
          fFxbh: option.fFxbh,
          fCjsj: option.fCjsj
        },
        success: function (result) {

          if (typeof callback === 'function') {
            callback(result);
          }

        },
      });
    }

    //同环比
    function change_tqtime(start, end, type) {
      var res = new Array();
      if (type == '1') {//同比
        if (start != null) {
          start.setFullYear(start.getFullYear() - 1);
          res.push(getFormatDate(start));
        }
        if (end != null) {
          end.setFullYear(end.getFullYear() - 1);
          res.push(getFormatDate(end));
        }
      } else {//环比
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

    //刷新左上数据
    function leftInfo(yqbh) {
      $.ajax({
        type: "post",
        url: "${ctx}/view/basedatamanage/workbench/getleftInfo",
        dataType: "json",
        async: true,
        traditional: true,
        data: ({
          "yqbh": yqbh //园区编号
        }),
        success: function (result) {
          $("#yqmc").text(result.data.F_YQMC);
          $("#F_NODE_CODE").html(result.data.F_NODE_CODE || '&nbsp;');
          $("#F_ALL_AREA").html((result.data.F_ALL_AREA || '&nbsp;') + "<span style='color: #8fe3f7'>平米</span>");
          $("#F_PERSON_NUMS").html((result.data.F_PERSON_NUMS || '&nbsp;') + "<span style='color: #8fe3f7'>人</span>");
          $("#F_BUILD_TIME").html((result.data.F_BUILD_TIME || '&nbsp;') + "<span style='color: #8fe3f7'>年</span>");
          $("#gzt_runingTime").html((result.data.day || '&nbsp;') + "<span style='color: #8fe3f7'>天</span>");
        }
      });
    }

    function totalEnergyConsumption(nodeId, callback) {
      //上年 上月  昨日--当天--本年
      var taday = getFormatDate(getCurrentDate());//今天
      var yesterday = getFormatDate(getPreviousDate());//昨天
      var year = getFormatDate(getCurrentDate()).substr(0, 4);//本年
      var lastyear = getFormatDate(getPreviousYear()[0]).substr(0, 4);//上年
      var month = getFormatDate(getCurrentMonth()[0]).substr(0, 7);//本月
      var lastmonth = getFormatDate(getPreviousMonth()[0]).substr(0, 7);//上月
      $.ajax({
        type: "post",
        url: "${ctx}/view/basedatamanage/workbench/gztLeftRefresh",
        dataType: "json",
        async: true,
        traditional: true,//组织深度序列化
        data: {
          "fType": "1",//天
          "fCjsj_start": taday,
          "fCjsj_end": getFormatDate(getCurrentDate()).substr(0, 4),//传本年时间 ---2018
          "fId": nodeId,
          //----分割 request
          "taday": taday,
          "yesterday": yesterday,
          "year": year,
          "lastyear": lastyear,
          "month": month,
          "lastmonth": lastmonth,
          "isControl": "0",//默认开启查询瞬时功率  0关闭 1开启
          "nhbh": "0001002"//能耗编号 写死 功率
        },
        success: function (result) {
          if (result.hasOwnProperty("list")) {
            var obj = result.list[0];

            var fAlldata = obj.fAlldata;

            if (typeof callback === 'function') {
              callback(fAlldata);
            }
//
          }

        }
      });
    }


    // 能耗分析（今日）
    function todayAnalyze(nymc, nydw, fData,maxTodayData) {

      $("#gzt_nhfx").text('能耗分析' + '（' + nymc + '）');
      $("#gzt_jrhn").text(fData.toString() + nydw);
      $("#gzt_peak").text(maxTodayData.toString() + nydw);

    }
    function YearEnergyData(nymc,nydw,energyConsumptionThisYear,peakEnergyConsumptionThisYear) {
      $("#gzt_energy").text(energyConsumptionThisYear.toString() + nydw);
      $("#gzt_energyPeak").text(peakEnergyConsumptionThisYear.toString() + nydw);
    }

    //月能耗分析
    function monthAnalyze(nymc, nydw, fData) {
      $("#gzt_nhfx").text('能耗分析' + '（' + nymc + '）');

      if (!fData || fData.length <= 0) {
        $("#gzt_water").html((0).toFixed(2) + nydw);
        $("#gzt_natgas").html((0).toFixed(2) + nydw);
        return;
      }

      var total = fData.reduce((total, num) => total + Number(num), 0); // 求和

      $("#gzt_water").text(total.toFixed(2) + nydw);

      var maxValue = fData.reduce((a, b) => Number(a) > Number(b) ? a : b); // 最大值

      $("#gzt_natgas").text(Number(maxValue).toFixed(2) + nydw);
    }

    return {

      $searchableTree: function (data, id) {
        //注意,站点名字不能重复,否则出错,站点名字重复的话,将第二行第三行注掉
        $("#tree_zzjg_site").treeview('search', [data, {ignoreCase: false, exactMatch: true, revealResults: true}]);
        var firstNode = $("#tree_zzjg_site").treeview('findNodes', [data, "text"]);
        $("#tree_zzjg_site").treeview("selectNode", firstNode[0]);//将地图点击的点位节点设置为选中状态
        // $('#tree_zzjg_site').treeview('clearSearch');
      },
      //关闭树模态框
      closeModelData: function () {
        $('#modal-form-addFxpz').modal('hide');
      },
      insertYearData: function () {
        insertYearData();
      },
      zzjg_tree: function (id) {
        zzjg_tree(id);
      },
      openDialog: function () {
        selectSubYearData();
        $('#modal-form-addFxpz').modal('show');
      },
      //关闭树模态框
      closeModelData: function () {
        $('#modal-form-addFxpz').modal(
            'hide');
      },
      insertYearData: function () {
        insertYearData();
      },
    }
  })(jQuery, window, document);

  //首页--详细结构--分户名称搜索
  function searchUnitName() {
      var value = $("#input-search_index").val();
      $('#tree_zzjg_CM').treeview('search', [ value,{ ignoreCase: false, exactMatch: false },"text"]);
//       value:值，
// attr:需要查询的属性
  }

</script>
