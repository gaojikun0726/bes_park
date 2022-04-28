<style type="text/css">
  .leftTree_plan {
    float: left;
    width: 20% !important;
    /* margin-left: -10px; */
    position: relative;
    box-sizing: border-box;
    height: 100%;
    border-right: solid 1px rgb(54, 108, 144);
    display: block;
  }

  /*.planTop{*/
  /*    height: 10%;*/
  /*    display:block;*/
  /*    border-bottom: solid 1px rgb(54, 108, 144);*/
  /*    display: block;*/
  /*}*/
  .centerContent_plan {
    float: left;
    width: 80%;
    position: relative;
    /*margin-top: 4%;*/
    height: 100%;
    display: none;
  }

  .inputWidth {
    width: 70%;
    float: left;
  }

  .checkboxWidth {
    width: 5%;
    float: right;
  }
  /*样式前加上前缀，避免影响其他页面样式*/
  #planConfig_content .layui-laydate-main {
    width: 626px;
    height: 270px;
  }

  #planConfig_content .layui-laydate-content table {
    border-collapse: collapse;
    border-spacing: 0;
    width: 100%;
    height: 233px;
  }

  #layDate .layui-laydate-main {
    width: 500px;
  }

  #layDate .layui-laydate-content td, #test-n1 .layui-laydate-content th {
    width: 87px;
    height: 50px;
  }

  #planConfig_content .layui-laydate, .layui-laydate-hint {
    border: 1px solid #d2d2d2;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12);
    background-color: #fff;
    color: #666;
    width: 630px;
  }

  .planTableContant {
    width: 100%;
    /*margin-top: 8px;*/
    position: relative;
  }

  .line {
    height: 25px;
    line-height: 25px;
    margin: 3px;
  }

  .imp {
    padding-left: 25px;
  }

  .col {
    width: 95px;
  }

  /*ul {*/
  /*    list-style:none;*/
  /*    padding-left:10px;*/
  /*}*/
  /*li {*/
  /*    height:20px;*/
  /*}*/
</style>

<#--左侧树-->
<div class="leftarea information_left">
  <div class="information-model">
		<span class="tree_Subtitle">
			<i class="fa fa-share-alt" aria-hidden="true"></i>&nbsp;计划配置树>>>
		</span>
  </div>
  <div id="planTree" class="ztree"></div>
</div>

<#--计划配置列表-->
<div id="planConfig_content" class="information_right">
  <div class="information-model">
			<span class="Subtitle">
				<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;计划配置列表>>>
			</span>
    <!-- 增加按钮 -->
    <div hidden id="showPlanBtn">
      <a id="Emtadd" onclick="basedatamanage_eqmanage_planConfig.insertPlanInfomation()" style="width: 8vw;" class="btn btn-primary toLeft">
        <i class="fa fa-plus" style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>新增计划信息
      </a>
    </div>
    <div hidden id="showTaskBtn">
      <a id="Emtadd" onclick="basedatamanage_eqmanage_planConfig.insertTaskInfomation()" style="width: 8vw;" class="btn btn-primary toLeft">
        <i class="fa fa-plus" style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>添加定时任务
      </a>
    </div>
  </div>

  <div class="planTableContant" id="planTableParam" hidden>
    <table class="layui-hide" id="planTable" lay-filter="demoPlanEvent" style="display: none"></table>
    <script type="text/html" id="rowPlanToolBar">
        <#--<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="system">同步</a>
            <a class="layui-btn layui-btn-xs" style="width: 50px" lay-event="system" id="isynchro" hidden>已同步</a> -->
        <a class="layui-btn layui-btn-xs layui-btn-warm" style="width: 50px" lay-event="system" id="notynchro">同步</a>
        <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="compare">对比</a>
        <a class="layui-btn layui-btn-xs" lay-event="update">修改</a>
        <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="delete">删除</a>
    </script>
  </div>

  <div class="planTableContant" id="planCollectTableParam" hidden>
    <table class="layui-hide" id="planCollectTable" lay-filter="demoCollectPlanEvent" style="display: none"></table>
    <script type="text/html" id="rowPlanCollectToolBar">
        <#--<a class="layui-btn layui-btn-xs" lay-event="collectExecute" id="collectExecuteBtn">执行</a>
            <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="collectStop" id="collectStopBtn">停止</a> -->
        <a class="layui-btn layui-btn-xs" lay-event="collectUpdate">修改</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="collectDelete">删除</a>
    </script>
  </div>
</div>

<#--新增计划-->
<div class="modal fade" style="width: 67%;margin-left:9%;margin-top: -50px" id="addPlanConfig" tabindex="-1"
     role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
  <div class="modal-dialog">
    <div class="modal-content" style="height: 690px;width: 120%;margin-top: 22%;">
      <div class="modal-header bg-primary">
        <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
        <h4 class="modal-title addIcon">添加计划信息</h4>
      </div>

      <div class="modal-body">
        <form role="form" id="formAddModeType" name="formAddModeType" class="form-horizontal">
          <div style="width:100%;float: left;height: 600px">
            <div>
              <div class="form-group">
                <label class="col-sm-3 control-label" style="width: auto;">计划名称<span class="text-danger"></span></label>
                <div class="inputWidth">
                  <input type="text" id="f_planName" name="f_planName" placeholder="请输入计划名称" class="form-control">
                </div>

                <label style="padding-left: 34px">使 能</label>
                <div class="checkboxWidth">
                  <input type="checkbox" name="f_active" id="f_active" value="1" checked
                         onchange="basedatamanage_eqmanage_planConfig.changeVal(this)">
                </div>
              </div>

              <div class="form-group">
                <label class="col-sm-3 control-label" style="width: auto;">计划别名<span class="text-danger"></span></label>
                <div class="inputWidth">
                  <input type="text" id="f_planNickName" name="f_planNickName" placeholder="请输入计划别名"
                         class="form-control">
                </div>

                <label style="padding-left: 34px">替代日</label>
                <div class="checkboxWidth">
                  <input type="checkbox" name="f_planType" id="f_planType" value="0"
                         onchange="basedatamanage_eqmanage_planConfig.changeVal(this)">
                </div>
              </div>

              <div class="form-group">
                <label class="col-sm-3 control-label" style="width: auto;">开始日期<span class="text-danger"></span></label>
                <div class="inputWidth">
                  <div class="layui-input-inline" style="width: 100%;">
                    <input type="text" name="f_startDate" id="f_startDate" lay-verify="date"
                           placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input"
                           style="width: 100%;color: black">
                  </div>
                    <#--                                    <input id="f_startDate" name="f_startDate" type="date" placeholder="请点击右侧的图标选择日期" style="width: 100%;color: black">-->
                </div>
              </div>


              <div class="form-group">
                <label class="col-sm-3 control-label" style="width: auto;">开始时间<span class="text-danger"></span></label>
                <div class="inputWidth">
                  <input id="f_starTime" name="f_starTime" class="layui-input time" style="color: black">
                </div>
              </div>

              <div class="form-group">
                <label class="col-sm-3 control-label" style="width: auto;">执行方式<span class="text-danger"></span></label>
                <label><input type="radio" name="f_executionWay" id="f_executionWay1" checked="checked"
                              value="0">按天执行</label>
                <label><input type="radio" name="f_executionWay" id="f_executionWay2" value="1">持续执行</label>
              </div>

              <div class="form-group" style="margin-top: 18px;">
                <label class="col-sm-3 control-label" style="width: auto;">结束日期<span class="text-danger"></span></label>
                <div class="inputWidth">
                  <div class="layui-input-inline" style="width: 100%;">
                    <input type="text" name="f_endDate" id="f_endDate" lay-verify="date"
                           placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input"
                           style="width: 100%;color: black">
                  </div>
                    <#--                                    <input id="f_endDate" name="f_endDate" type="date" style="width: 100%;color: black">-->
                </div>
              </div>

              <div class="form-group">
                <label class="col-sm-3 control-label" style="width: auto;">结束时间<span class="text-danger"></span></label>
                <div class="inputWidth">
                  <input id="f_endTime" name="f_endTime" class="layui-input time" style="color: black">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label" style="width: auto;">执行频率<span class="text-danger"></span></label>
                <div style="width: 72%;margin-left: 30px;">
                  <input type="checkbox" id="f_weekmask_1" value="0"
                         onchange="basedatamanage_eqmanage_planConfig.changeVal(this)"/>周一
                  <input type="checkbox" id="f_weekmask_2" value="0"
                         onchange="basedatamanage_eqmanage_planConfig.changeVal(this)"/>周二
                  <input type="checkbox" id="f_weekmask_3" value="0"
                         onchange="basedatamanage_eqmanage_planConfig.changeVal(this)"/>周三
                  <input type="checkbox" id="f_weekmask_4" value="0"
                         onchange="basedatamanage_eqmanage_planConfig.changeVal(this)"/>周四
                  <input type="checkbox" id="f_weekmask_5" value="0"
                         onchange="basedatamanage_eqmanage_planConfig.changeVal(this)"/>周五
                  <input type="checkbox" id="f_weekmask_6" value="0"
                         onchange="basedatamanage_eqmanage_planConfig.changeVal(this)"/>周六
                  <input type="checkbox" id="f_weekmask_7" value="0"
                         onchange="basedatamanage_eqmanage_planConfig.changeVal(this)"/>周日
                </div>
              </div>
            </div>

            <div style="width: 100%;height: 115px;margin-left:30px">
              <label style="font-size: 18px;color: black">场景选择</label>
              <div>
                <label style="font-size: 16px;color: black;margin-left: 30px;margin-top: 14px">选择场景:</label>
                <select id="f_sceneInfo" name="f_sceneInfo" style="width: 75%;margin-left: 30px;margin-top:5px;"
                        class="form-control"
                        onchange="basedatamanage_eqmanage_planConfig.getModeValue(this.value)">
                  <option disabled selected>请选择场景</option>
                </select>
              </div>

              <div>
                <label style="font-size: 16px;color: black;margin-left: 30px;margin-top: 14px">选择模式:</label>
                <select id="f_modeInfo" name="f_modeInfo" style="width: 75%;margin-left: 30px;margin-top:5px;"
                        class="form-control">
                </select>
              </div>

              <div class="form-group m-t-sm">
                <div class="col-sm-6 col-sm-push-3 display_flex" style="left: 110px;">
                  <button class="btn btn-md btn-primary" type="button"
                          onclick="basedatamanage_eqmanage_planConfig.submitPlanInfo()"><strong>确定</strong></button>
                  <button class="btn btn-white m-l-sm" type="button" data-dismiss="modal">&nbsp;取消</button>
                </div>
              </div>
            </div>
          </div>

            <#--                    <div style="width:60%;float: left;height: 300px">-->
            <#--                        <div class="layui-inline" id="calendar"></div>-->
            <#--                    </div>-->

        </form>
      </div>
    </div>
  </div>
</div>

<#--修改计划-->
<div class="modal fade" style="width: 67%;margin-left:9%;margin-top: -50px" id="updatePlanConfig" tabindex="-1"
     role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
  <div class="modal-dialog">
    <div class="modal-content" style="height: 690px;width: 120%;margin-top: 22%;">
      <div class="modal-header bg-primary">
        <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
        <h4 class="modal-title addIcon">修改计划信息</h4>
      </div>

      <div class="modal-body">
        <form role="form" id="formUpdateModeType" name="formUpdateModeType" class="form-horizontal">
          <div style="width:100%;float: left;height: 600px">
            <div>
              <div class="form-group">
                <label class="col-sm-3 control-label" style="width: auto;">计划名称<span class="text-danger"></span></label>
                <div class="inputWidth">
                  <input type="text" id="update_f_planName" name="update_f_planName" placeholder="请输入计划名称"
                         class="form-control">
                </div>

                <label style="padding-left: 34px">使 能</label>
                <div class="checkboxWidth">
                  <input type="checkbox" name="update_f_active" id="update_f_active" value="1"
                         onclick="basedatamanage_eqmanage_planConfig.changeVal(this)">
                </div>
              </div>

              <div class="form-group">
                <label class="col-sm-3 control-label" style="width: auto;">计划别名<span class="text-danger"></span></label>
                <div class="inputWidth">
                  <input type="text" id="update_f_planNickName" name="update_f_planNickName" placeholder="请输入计划别名"
                         class="form-control">
                </div>

                <label style="padding-left: 34px">替代日</label>
                <div class="checkboxWidth">
                  <input type="checkbox" name="update_f_planType" id="update_f_planType" value="0"
                         onclick="basedatamanage_eqmanage_planConfig.changeVal(this)">
                </div>
              </div>

              <div class="form-group">
                <label class="col-sm-3 control-label" style="width: auto;">开始日期<span class="text-danger"></span></label>
                <div class="inputWidth">
                  <div class="layui-input-inline" style="width: 100%;">
                    <input type="text" name="update_f_startDate" id="update_f_startDate" lay-verify="date"
                           placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input"
                           style="width: 100%;color: black">
                  </div>
                    <#--                                    <input id="update_f_startDate" name="update_f_startDate" placeholder="请点击右侧的图标选择日期" type="date" style="width: 100%;color: black">-->
                </div>
              </div>


              <div class="form-group">
                <label class="col-sm-3 control-label" style="width: auto;">开始时间<span class="text-danger"></span></label>
                <div class="inputWidth">
                  <input id="update_f_starTime" name="update_f_starTime" class="layui-input time" style="color: black">
                </div>
              </div>

              <div class="form-group" style="margin-left: 30px">
                <label class="col-sm-3 control-label" style="width: auto;">执行方式<span class="text-danger"></span></label>
                <label><input type="radio" name="update_f_executionWay" id="update_f_executionWay1" checked value="0">按天执行</label>
                <label><input type="radio" name="update_f_executionWay" id="update_f_executionWay2"
                              value="1">持续执行</label>
              </div>

              <div class="form-group" style="margin-top: 18px;">
                <label class="col-sm-3 control-label" style="width: auto;">结束日期<span class="text-danger"></span></label>
                <div class="inputWidth">
                  <div class="layui-input-inline" style="width: 100%;">
                    <input type="text" name="update_f_endDate" id="update_f_endDate" lay-verify="date"
                           placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input"
                           style="width: 100%;color: black">
                  </div>
                    <#--                                    <input id="update_f_endDate" name="update_f_endDate" type="date" style="width: 100%;color: black">-->
                </div>
              </div>

              <div class="form-group">
                <label class="col-sm-3 control-label" style="width: auto;">结束时间<span class="text-danger"></span></label>
                <div class="inputWidth">
                  <input id="update_f_endTime" name="update_f_endTime" class="layui-input time" style="color: black">
                </div>
              </div>

              <div class="form-group">
                <label class="col-sm-3 control-label" style="width: auto;">执行频率<span class="text-danger"></span></label>
                <div style="width: 72%;margin-left: 30px;">
                  <input type="checkbox" id="update_f_weekmask_1" value="0"
                         onchange="basedatamanage_eqmanage_planConfig.changeVal(this)"/>周一
                  <input type="checkbox" id="update_f_weekmask_2" value="0"
                         onchange="basedatamanage_eqmanage_planConfig.changeVal(this)"/>周二
                  <input type="checkbox" id="update_f_weekmask_3" value="0"
                         onchange="basedatamanage_eqmanage_planConfig.changeVal(this)"/>周三
                  <input type="checkbox" id="update_f_weekmask_4" value="0"
                         onchange="basedatamanage_eqmanage_planConfig.changeVal(this)"/>周四
                  <input type="checkbox" id="update_f_weekmask_5" value="0"
                         onchange="basedatamanage_eqmanage_planConfig.changeVal(this)"/>周五
                  <input type="checkbox" id="update_f_weekmask_6" value="0"
                         onchange="basedatamanage_eqmanage_planConfig.changeVal(this)"/>周六
                  <input type="checkbox" id="update_f_weekmask_7" value="0"
                         onchange="basedatamanage_eqmanage_planConfig.changeVal(this)"/>周日
                </div>
              </div>
            </div>
              <#-- <div style="width: 100%;height: 80px;margin-left:30px">
                            <label style="font-size: 18px;color: whitesmoke">执行频率</label>
                            <label class="col-sm-3 control-label" style="width: auto;">执行频率<span class="text-danger"></span></label>
                            <div style="width: 72%;margin-top: 14px;margin-left: 30px;">
                                <input type="checkbox" id="update_f_weekmask_1" value="0" onchange="basedatamanage_eqmanage_planConfig.changeVal(this)"/>周一
                                <input type="checkbox" id="update_f_weekmask_2" value="0" onchange="basedatamanage_eqmanage_planConfig.changeVal(this)"/>周二
                                <input type="checkbox" id="update_f_weekmask_3" value="0" onchange="basedatamanage_eqmanage_planConfig.changeVal(this)"/>周三
                                <input type="checkbox" id="update_f_weekmask_4" value="0" onchange="basedatamanage_eqmanage_planConfig.changeVal(this)"/>周四
                                <input type="checkbox" id="update_f_weekmask_5" value="0" onchange="basedatamanage_eqmanage_planConfig.changeVal(this)"/>周五
                                <input type="checkbox" id="update_f_weekmask_6" value="0" onchange="basedatamanage_eqmanage_planConfig.changeVal(this)"/>周六
                                <input type="checkbox" id="update_f_weekmask_7" value="0" onchange="basedatamanage_eqmanage_planConfig.changeVal(this)"/>周日
                            </div>
                        </div>-->


            <div style="width: 100%;height: 115px;margin-left:30px">
              <label style="font-size: 18px;color: black">场景选择</label>
              <div>
                <label style="font-size: 16px;color: black;margin-left: 30px;margin-top: 14px">选择场景:</label>
                <select id="update_f_sceneInfo" name="update_f_sceneInfo"
                        style="width: 75%;margin-left: 30px;margin-top:5px;" class="form-control"
                        onchange="basedatamanage_eqmanage_planConfig.getModeValue(this.value)">
                  <option disabled selected>请选择场景</option>
                </select>
              </div>

              <div>
                <label style="font-size: 16px;color: black;margin-left: 30px;margin-top: 14px">选择模式:</label>
                <select id="update_f_modeInfo" name="update_f_modeInfo"
                        style="width: 75%;margin-left: 30px;margin-top:5px;" class="form-control">
                </select>
              </div>

              <div class="form-group m-t-sm">
                <div class="col-sm-6 col-sm-push-3 display_flex" style="left: 110px;">
                  <button class="btn btn-md btn-primary" type="button"
                          onclick="basedatamanage_eqmanage_planConfig.update_submitPlanInfo()"><strong>确定</strong>
                  </button>
                  <button class="btn btn-white m-l-sm" type="button" data-dismiss="modal">&nbsp;取消</button>
                </div>
              </div>
            </div>
          </div>

            <#--                    <div style="width:60%;float: left;height: 300px">-->
            <#--                        <div class="layui-inline" id="update_calendar"></div>-->
            <#--                    </div>-->

        </form>
      </div>
    </div>
  </div>
</div>

<!----计划详情信息对比--->
<div class="modal fade" id="contrastPlanValue" style="margin-left:-16%" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
  <div class="modal-dialog">
    <div class="modal-content" style="width:900px;">
      <div class="modal-header bg-primary">
        <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
        <h4 class="modal-title">上/下位机计划详情信息比对</h4>
      </div>

      <div class="modal-body" style="height:550px;margin-button:10px;">
        <div style="float:left;width:52%">
          <button class="btn btn-md" style="cursor:default"><span>上位机</span></button>
        </div>
        <div style="float:left;width:40%">
          <button class="btn btn-md" style="cursor:default"><span>下位机</span></button>
        </div>


        <!----上位机信息开始--->
        <div class="notIncludeCss" style="width:400px;height:450px;overflow-y:auto;overflow-x: hidden;">
          <form class="form-horizontal">

            <div class="form-group">
              <label class="col-sm-3 control-label" style="width:40%" for="">计划ID<span class="text-comparecoll">:</span></label>
              <div class="col-sm-8" style="width:50%">
                <input type="text" id="contrast_planInfoId" name="contrast_planInfoId" class="form-control"
                       readonly="readonly">
              </div>
            </div>

            <div class="form-group">
              <label class="col-sm-3 control-label" style="width:40%" for="">计划名称<span class="text-comparecoll">:</span></label>
              <div class="col-sm-8" style="width:50%">
                <input type="text" id="contrast_planName" name="contrast_planName" class="form-control"
                       readonly="readonly">
              </div>
            </div>

            <div class="form-group">
              <label class="col-sm-3 control-label" style="width:40%" for="">计划别名<span class="text-comparecoll">:</span></label>
              <div class="col-sm-8" style="width:50%">
                <input type="text" id="contrast_planNickname" name="contrast_planNickname" class="form-control"
                       readonly="readonly">
              </div>
            </div>

            <div class="form-group">
              <label class="col-sm-3 control-label" style="width:40%" for="">是否启用<span class="text-comparecoll">:</span></label>
              <div class="col-sm-8" style="width:50%">
                <input type="text" id="contrast_planEnable" name="contrast_planEnable" class="form-control"
                       readonly="readonly">
              </div>
            </div>

            <div class="form-group">
              <label class="col-sm-3 control-label" style="width:40%" for="">场景ID<span class="text-comparecoll">:</span></label>
              <div class="col-sm-8" style="width:50%">
                <input type="text" id="contrast_sceneId" name="contrast_sceneId" class="form-control"
                       readonly="readonly">
              </div>
            </div>

            <div class="form-group">
              <label class="col-sm-3 control-label" style="width:40%" for="">场景名称<span class="text-comparecoll">:</span></label>
              <div class="col-sm-8" style="width:50%">
                <input type="text" id="planContrast_sceneName" name="planContrast_sceneName" class="form-control"
                       readonly="readonly">
              </div>
            </div>

            <div class="form-group">
              <label class="col-sm-3 control-label" style="width:40%" for="">模式ID<span class="text-comparecoll">:</span></label>
              <div class="col-sm-8" style="width:50%">
                <input type="text" id="planContrast_modeId" name="planContrast_modeId" class="form-control"
                       readonly="readonly">
              </div>
            </div>

            <div class="form-group">
              <label class="col-sm-3 control-label" style="width:40%" for="">模式名称<span class="text-comparecoll">:</span></label>
              <div class="col-sm-8" style="width:50%">
                <input type="text" id="planContrast_modeName" name="planContrast_modeName" class="form-control"
                       readonly="readonly">
              </div>
            </div>

            <div class="form-group">
              <label class="col-sm-3 control-label" style="width:40%" for="">计划开始日期<span
                        class="text-comparecoll">:</span></label>
              <div class="col-sm-8" style="width:50%">
                <input type="text" id="contrast_planStartDate" name="contrast_planStartDate" class="form-control"
                       readonly="readonly">
              </div>
            </div>

            <div class="form-group">
              <label class="col-sm-3 control-label" style="width:40%" for="">计划开始时间<span
                        class="text-comparecoll">:</span></label>
              <div class="col-sm-8" style="width:50%">
                <input type="text" id="contrast_planStartTime" name="contrast_planStartTime" class="form-control"
                       readonly="readonly">
              </div>
            </div>

            <div class="form-group">
              <label class="col-sm-3 control-label" style="width:40%" for="">计划结束日期<span
                        class="text-comparecoll">:</span></label>
              <div class="col-sm-8" style="width:50%">
                <input type="text" id="contrast_planEndDate" name="contrast_planEndDate" class="form-control"
                       readonly="readonly">
              </div>
            </div>

            <div class="form-group">
              <label class="col-sm-3 control-label" style="width:40%" for="">计划结束时间<span
                        class="text-comparecoll">:</span></label>
              <div class="col-sm-8" style="width:50%">
                <input type="text" id="contrast_planEndTime" name="contrast_planEndTime" class="form-control"
                       readonly="readonly">
              </div>
            </div>
          </form>
        </div>
        <!----上位机信息结束--->


        <!----下位机信息开始--->
        <div class="includeCss"
             style="width:400px;height:450px;margin:5px 0 0 36px; overflow-y:auto;overflow-x: hidden;">
          <form class="form-horizontal">

            <div class="form-group">
              <label class="col-sm-3 control-label" style="width:40%" for="">计划ID<span class="text-comparecoll">:</span></label>
              <div class="col-sm-8" style="width:50%">
                <input type="text" id="contrast_planUnderInfoId" name="contrast_planUnderInfoId" class="form-control"
                       readonly="readonly">
              </div>
            </div>

            <div class="form-group">
              <label class="col-sm-3 control-label" style="width:40%" for="">计划名称<span class="text-comparecoll">:</span></label>
              <div class="col-sm-8" style="width:50%">
                <input type="text" id="contrast_planUnderName" name="contrast_planUnderName" class="form-control"
                       readonly="readonly">
              </div>
            </div>

            <div class="form-group">
              <label class="col-sm-3 control-label" style="width:40%" for="">计划别名<span class="text-comparecoll">:</span></label>
              <div class="col-sm-8" style="width:50%">
                <input type="text" id="contrast_planUnderNickname" name="contrast_planUnderNickname"
                       class="form-control" readonly="readonly">
              </div>
            </div>

            <div class="form-group">
              <label class="col-sm-3 control-label" style="width:40%" for="">是否启用<span class="text-comparecoll">:</span></label>
              <div class="col-sm-8" style="width:50%">
                <input type="text" id="contrast_planUnderEnable" name="contrast_planUnderEnable" class="form-control"
                       readonly="readonly">
              </div>
            </div>

            <div class="form-group">
              <label class="col-sm-3 control-label" style="width:40%" for="">场景ID<span class="text-comparecoll">:</span></label>
              <div class="col-sm-8" style="width:50%">
                <input type="text" id="contrast_sceneUnderId" name="contrast_sceneUnderId" class="form-control"
                       readonly="readonly">
              </div>
            </div>

            <div class="form-group">
              <label class="col-sm-3 control-label" style="width:40%" for="">场景名称<span class="text-comparecoll">:</span></label>
              <div class="col-sm-8" style="width:50%">
                <input type="text" id="contrast_sceneUnderName" name="contrast_sceneUnderName" class="form-control"
                       readonly="readonly">
              </div>
            </div>

            <div class="form-group">
              <label class="col-sm-3 control-label" style="width:40%" for="">模式ID<span class="text-comparecoll">:</span></label>
              <div class="col-sm-8" style="width:50%">
                <input type="text" id="contrast_modeUnderId" name="contrast_modeUnderId" class="form-control"
                       readonly="readonly">
              </div>
            </div>

            <div class="form-group">
              <label class="col-sm-3 control-label" style="width:40%" for="">模式名称<span class="text-comparecoll">:</span></label>
              <div class="col-sm-8" style="width:50%">
                <input type="text" id="contrast_modeUnderName" name="contrast_modeUnderName" class="form-control"
                       readonly="readonly">
              </div>
            </div>

            <div class="form-group">
              <label class="col-sm-3 control-label" style="width:40%" for="">计划开始日期<span
                        class="text-comparecoll">:</span></label>
              <div class="col-sm-8" style="width:50%">
                <input type="text" id="contrast_planUnderStartDate" name="contrast_planUnderStartDate"
                       class="form-control" readonly="readonly">
              </div>
            </div>

            <div class="form-group">
              <label class="col-sm-3 control-label" style="width:40%" for="">计划开始日期<span
                        class="text-comparecoll">:</span></label>
              <div class="col-sm-8" style="width:50%">
                <input type="text" id="contrast_planUnderStartTime" name="contrast_planUnderStartTime"
                       class="form-control" readonly="readonly">
              </div>
            </div>

            <div class="form-group">
              <label class="col-sm-3 control-label" style="width:40%" for="">计划结束日期<span
                        class="text-comparecoll">:</span></label>
              <div class="col-sm-8" style="width:50%">
                <input type="text" id="contrast_planUnderEndDate" name="contrast_planUnderEndDate" class="form-control"
                       readonly="readonly">
              </div>
            </div>

            <div class="form-group">
              <label class="col-sm-3 control-label" style="width:40%" for="">计划结束日期<span
                        class="text-comparecoll">:</span></label>
              <div class="col-sm-8" style="width:50%">
                <input type="text" id="contrast_planUnderEndTime" name="contrast_planUnderEndTime" class="form-control"
                       readonly="readonly">
              </div>
            </div>
          </form>
        </div>
        <!----下位机信息结束--->
      </div>

      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
      </div>
    </div>
  </div>
</div>

<#--新增定时任务-->
<div class="modal fade" style="width: 67%;margin-left:9%;margin-top: -10px; padding-left: 36%" id="addTimeTaskConfig"
     tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
  <div class="modal-dialog">
    <div class="modal-content" style="height: 360px;width: 100%;margin-top:5%;margin-left: 22%;">
      <div class="modal-header bg-primary">
        <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
        <h4 class="modal-title addIcon">添加定时任务</h4>
      </div>

      <div class="modal-body">
        <form role="form" id="formAddTimeTask" name="formAddTimeTask" class="form-horizontal">

          <div class="form-group" style="margin-top: 15px">
            <label class="col-sm-3 control-label">计划名称<span class="text-danger"></span></label>
            <div class="col-sm-8">
              <input type="text" id="planTaskName" name="planTaskName" class="form-control" placeholder="请输入计划名称">
            </div>
          </div>

          <div class="form-group" style="margin-top: 20px">
            <label class="col-sm-3 control-label">计划别名<span class="text-danger"></span></label>
            <div class="col-sm-8">
              <input type="text" id="planTaskNickname" name="planTaskNickname" class="form-control"
                     placeholder="请输入计划别名">
            </div>
          </div>

          <div class="form-group" style="margin-top: 25px">
            <label class="col-sm-3 control-label">定时任务名称<span class="text-danger"></span></label>
            <div class="col-sm-8">
              <input type="text" id="f_timename" name="f_timename" placeholder="请输入任务名称" class="form-control">
            </div>
          </div>

          <div class="form-group" style="margin-top: 25px">
            <label class="col-sm-3 control-label">任务类型<span class="text-danger"></span></label>
            <div class="col-sm-8">
              <select id="f_tasktype" name="f_tasktype" class="form-control">
                <option>请选择任务类型</option>
                <option value="1">时间</option>
                <option value="0">变化量</option>
              </select>
            </div>
          </div>

          <div class="form-group" style="margin-top: 20px" id="planTimeConfigKZ" hidden>
            <label class="col-sm-3 control-label">配置cron表达式<span class="text-danger"></span></label>
            <div class="col-sm-8">
              <input type="text" id="planTimeConfig" name="planTimeConfig" class="form-control" placeholder="配置定时策略">
            </div>
          </div>

          <div class="form-group" id="specificvalueKZ" hidden>
            <label class="col-sm-3 control-label">具体值<span class="text-danger"></span></label>
            <div class="col-sm-8">
              <input type="text" id="f_specificvalue" name="f_specificvalue" placeholder="请输入具体值" class="form-control">
            </div>
          </div>

          <div class="form-group">
            <label class="col-sm-3 control-label">选择场景:<span class="text-danger"></span></label>
            <div class="col-sm-8">
              <select id="f_sceneTaskInfo" name="f_sceneTaskInfo" class="form-control"
                      onchange="basedatamanage_eqmanage_planConfig.getModeValue(this.value)">
                <option disabled selected>请选择场景</option>
              </select>
            </div>
          </div>

          <div class="form-group">
            <label class="col-sm-3 control-label">选择模式:<span class="text-danger"></span></label>
            <div class="col-sm-8">
              <select id="f_modeTaskInfo" name="f_modeTaskInfo" class="form-control">
              </select>
            </div>
          </div>

          <div class="form-group m-t-sm" style="margin-left: 235px;margin-top: 25px;">
            <div class="col-sm-6 col-sm-push-3 display_flex">
              <button class="btn btn-md btn-primary" type="button"
                      onclick="basedatamanage_eqmanage_planConfig.submitTimeTaskInfo()"><strong>立即执行</strong></button>
              <button class="btn btn-white m-l-sm" type="button" data-dismiss="modal">取消</button>
            </div>
          </div>

        </form>
      </div>
    </div>
  </div>
</div>

<#--编辑定时任务-->
<div class="modal fade" style="width: 67%;margin-left:9%;margin-top: -10px; padding-left: 36%" id="updateTimeTaskConfig"
     tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
  <div class="modal-dialog">
    <div class="modal-content" style="height: 360px;width: 100%;margin-top:5%;margin-left: 22%;">
      <div class="modal-header bg-primary">
        <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
        <h4 class="modal-title addIcon">修改定时任务</h4>
      </div>

      <div class="modal-body">
        <form role="form" id="updateformAddTimeTask" name="updateformAddTimeTask" class="form-horizontal">

          <div class="form-group" style="margin-top: 15px">
            <label class="col-sm-3 control-label">计划名称<span class="text-danger"></span></label>
            <div class="col-sm-8">
              <input type="text" id="updateplanTaskName" name="updateplanTaskName" class="form-control"
                     placeholder="请输入计划名称">
            </div>
          </div>

          <div class="form-group" style="margin-top: 20px">
            <label class="col-sm-3 control-label">计划别名<span class="text-danger"></span></label>
            <div class="col-sm-8">
              <input type="text" id="updateplanTaskNickname" name="updateplanTaskNickname" class="form-control"
                     placeholder="请输入计划别名">
            </div>
          </div>

          <div class="form-group" style="margin-top: 25px">
            <label class="col-sm-3 control-label">定时任务名称<span class="text-danger"></span></label>
            <div class="col-sm-8">
              <input type="text" id="updatef_timename" name="updatef_timename" placeholder="请输入任务名称"
                     class="form-control">
            </div>
          </div>

          <div class="form-group" style="margin-top: 25px">
            <label class="col-sm-3 control-label">任务类型<span class="text-danger"></span></label>
            <div class="col-sm-8">
              <select id="updatef_tasktype" name="updatef_tasktype" class="form-control">
                <option>请选择任务类型</option>
                <option value="1">时间</option>
                <option value="0">变化量</option>
              </select>
            </div>
          </div>

          <div class="form-group" style="margin-top: 20px" id="updateplanTimeConfigKZ" hidden>
            <label class="col-sm-3 control-label">配置cron表达式<span class="text-danger"></span></label>
            <div class="col-sm-8">
              <input type="text" id="updateplanTimeConfig" name="updateplanTimeConfig" class="form-control"
                     placeholder="配置定时策略">
            </div>
          </div>

          <div class="form-group" id="updatespecificvalueKZ" hidden>
            <label class="col-sm-3 control-label">具体值<span class="text-danger"></span></label>
            <div class="col-sm-8">
              <input type="text" id="updatef_specificvalue" name="updatef_specificvalue" placeholder="请输入具体值"
                     class="form-control">
            </div>
          </div>

          <div class="form-group">
            <label class="col-sm-3 control-label">选择场景:<span class="text-danger"></span></label>
            <div class="col-sm-8">
              <select id="updatef_sceneTaskInfo" name="updatef_sceneTaskInfo" class="form-control"
                      onchange="basedatamanage_eqmanage_planConfig.getModeValue(this.value)">
                <option disabled selected>请选择场景</option>
              </select>
            </div>
          </div>

          <div class="form-group">
            <label class="col-sm-3 control-label">选择模式:<span class="text-danger"></span></label>
            <div class="col-sm-8">
              <select id="updatef_modeTaskInfo" name="updatef_modeTaskInfo" class="form-control">
              </select>
            </div>
          </div>

          <div class="form-group m-t-sm" style="margin-left: 235px;margin-top: 25px;">
            <div class="col-sm-6 col-sm-push-3 display_flex">
              <button class="btn btn-md btn-primary" type="button"
                      onclick="basedatamanage_eqmanage_planConfig.submitUpdateTimeTaskInfo()"><strong>立即执行</strong>
              </button>
              <button class="btn btn-white m-l-sm" type="button" data-dismiss="modal">取消</button>
            </div>
          </div>

        </form>
      </div>
    </div>
  </div>
</div>

<div class="modal fade" id="cronModal" tabindex="-1" role="dialog" data-backdrop="static">
  <div class="modal-dialog" style="width: 46vw;margin-top: 0px;">
    <div class="modal-content">
      <div class="modal-header bg-primary">
        <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
        <h4 class="modal-title editIcon">&nbsp; cron表达式生成</h4>
      </div>
      <div class="modal-body" style="height: 100%;overflow-y: auto;">
        <div id="dataMonitor_realMonitor">
          <div id="cron_page" style="width: 100%;height: 96%"></div>

        </div>
      </div>
    </div><!-- end modal-content -->
  </div>
</div>

<link href="${ctx}/static/ztree/css/metroStyle/metroStyle.css" rel="stylesheet">
<script src="${ctx}/static/layui/lay/modules/layer.js" type="text/javascript"></script>
<script src="${ctx}/static/layui/lay/modules/laydate.js" type="text/javascript"></script>
<script type="text/html" id="switchTpl">
    <#--    执行停止-->
    <input type="checkbox" name="f_invoke" value={{d.f_id}} {{d.f_invoke=="1"?"checked":""}} lay-skin="switch"
           lay-text="执行|停止" lay-filter="executeStop">

</script>

<script type="text/javascript">
  ;
  var basedatamanage_eqmanage_planConfig = (function ($, window, document, undefined) {
    var _ctx = '${ctx}';
    var tabAcademeNotice;
    var tabAcademeCollectNotice;
    var nodeId;
    var sceneType;
    var f_id;//计划id
    var invokeValue;//计划是否执行
    var f_jobId;//定时任务调度表ID
    var BooaddTimeTaskConfig = true;//添加定时任务模态框是否显示

    var BooPagination = false;//判断页面是否加载第一页
    var BooAdd_or_Edit = false;//判断是否是添加或修改操作，如果是，则加载当前页面
    var cr = "1";//当前页数

    //点位树展示
    $('#planTimeConfig').focus(function () {
      $('#cronModal').modal('show');
    });
    $('#updateplanTimeConfig').focus(function (value) {
      $('#cronModal').modal('show');
    });

    //表达式生成页面加载之前,先确定页面所在的位置以及查询所加载页面的url
    $("#cronModal").on('show.bs.modal', function (event) {
      //垂直居中显示
      $(this).css('display', 'block');
      $(this).css("overflow", "hidden"); // 防止出现滚动条，出现的话，你会把滚动条一起拖着走的
      loadPageByMold();
    });

    //显示cron表达式页面
    function loadPageByMold() {

      $.ajax({
        url: _ctx + "/view/basedatamanage/eqmanage/cronPage",
        type: "post",
        success: function (result) {
          $("#cron_page").empty();
          $("#cron_page").html(result);
        },
        error: function (result) {
        }
      });
    }

    //添加,修改控制计划页面关闭的方法
    $("#addPlanConfig").on('hidden.bs.modal', function (event) {
      $(this).find("input").val("");
      $('#f_planType,#f_weekmask_1,#f_weekmask_2,#f_weekmask_3,#f_weekmask_4,#f_weekmask_5,#f_weekmask_6,#f_weekmask_7').prop('checked', false);
      $("#f_planType,#f_weekmask_1,#f_weekmask_2,#f_weekmask_3,#f_weekmask_4,#f_weekmask_5,#f_weekmask_6,#f_weekmask_7").val("0");
      $("#f_active").prop('checked', true);
      $("#f_active").val("1");
      $("#f_executionWay1").val("0");
      $("#f_executionWay2").val("1");
      $("#f_executionWay1").prop('checked', true);//radio默认选中
      $("#f_sceneInfo option:first").prop("selected", 'selected');

      let controlSceneId = $('#f_sceneInfo').val();
      getInitModeInfo(controlSceneId,function () {

      });
    });
    //添加,修改定时任务页面关闭方法
    $("#addTimeTaskConfig").on('hidden.bs.modal', function (event) {
      $(this).find("select").val("1");
      $("#f_tasktype option:first").prop("selected", 'selected');
      $("#f_sceneTaskInfo option:first").prop("selected", 'selected');
      $("#f_modeTaskInfo option:first").prop("selected", 'selected');

      $("#specificvalueKZ").hide();
      $("#planTimeConfigKZ").hide();
      $("#planTimeConfigKZ").val("");
      $("#specificvalueKZ").val("");
      $(this).find("input").val("");

      BooaddTimeTaskConfig = false;
    });
    $("#updateTimeTaskConfig").on('hidden.bs.modal', function (event) {
      $(this).find("select").val("1");
      $("#updatef_tasktype option:first").prop("selected", 'selected');
      $("#updatef_sceneTaskInfo option:first").prop("selected", 'selected');
      $("#updatef_modeTaskInfo option:first").prop("selected", 'selected');

      $("#updatespecificvalueKZ").hide();
      $("#updateplanTimeConfigKZ").hide();
      $("#updateplanTimeConfigKZ").val("");
      $("#updatespecificvalueKZ").val("");
      $(this).find("input").val("");
    });

    layui.use('laydate', function () {

      var layDate = layui.laydate;

      layDate.render({
        elem: '#f_startDate'
      });
      layDate.render({
        elem: '#f_endDate'
      });
      layDate.render({
        elem: '#update_f_startDate'
      });
      layDate.render({
        elem: '#update_f_endDate'
      });

      lay('.time').each(function () {
        layDate.render({
          elem: this
          , type: 'time'
          , format: 'HH:mm:ss'
        });
      });
    })

    $(function () {

      plan_Tree();//初始化计划树

      let treeObj = $.fn.zTree.getZTreeObj("planTree");

      treeObj.expandAll(true);

      let node = treeObj.getNodeByParam("f_id", "2");

      treeObj.selectNode(node);
    })

    //控制定时任务显示
    $("#f_tasktype").on("change", function () {
      if ($(this).val() == 1) {//时间
        $("#specificvalueKZ").hide();
        $("#planTimeConfigKZ").show();
        $("#planTimeConfigKZ").val("");
        $("#specificvalueKZ").val("");
      } else if ($(this).val() == 0) {//变化量
        $("#specificvalueKZ").show();
        $("#planTimeConfigKZ").hide();
        $("#planTimeConfigKZ").val("");
        $("#specificvalueKZ").val("");
      } else {
        $("#specificvalueKZ").hide();
        $("#planTimeConfigKZ").hide();
        $("#planTimeConfigKZ").val("");
        $("#specificvalueKZ").val("");
      }
    })
    //修改定时任务任务类型点选事件
    $("#updatef_tasktype").on("change", function () {
      if ($(this).val() == 1) {//时间
        $("#updatespecificvalueKZ").hide();
        $("#updateplanTimeConfigKZ").show();
        $("#updateplanTimeConfig").val("");
        $("#updatef_specificvalue").val("");
      } else if ($(this).val() == 0) {//变化量
        $("#updatespecificvalueKZ").show();
        $("#updateplanTimeConfigKZ").hide();
        $("#updateplanTimeConfig").val("");
        $("#updatef_specificvalue").val("");
      } else {
        $("#updatespecificvalueKZ").hide();
        $("#updateplanTimeConfigKZ").hide();
        $("#updateplanTimeConfig").val("");
        $("#updatef_specificvalue").val("");
      }
    })

    //查询场景信息
    function  getInitSceneInfo(type) {

      $.ajax({
        type: "post",
        url: "${ctx}/view/basedatamanage/eqmanage/selectOptionByScene",
        dataType: "json",
        async: false,
        data: {
          type: type
        },
        success: function (result) {

          var list = result.list;
          $("#f_sceneInfo").find('option').remove();    //新增控制计划的'选择场景'
          $("#update_f_sceneInfo").find('option').remove();    //修改控制计划的'选择场景'
          $("#f_sceneTaskInfo").find('option').remove();//新增采集计划的'选择场景'
          $("#updatef_sceneTaskInfo").find('option').remove();//修改采集计划的'选择场景'
          for (var i = 0; i < list.length; i++) {
            $("#f_sceneInfo").append("<option value='" + list[i].f_id + "'>" + list[i].f_scenename + "</option>");
            +$("#update_f_sceneInfo").append("<option value='" + list[i].f_id + "'>" + list[i].f_scenename + "</option>");
            $("#f_sceneTaskInfo").append("<option value='" + list[i].f_id + "'>" + list[i].f_scenename + "</option>");
            $("#updatef_sceneTaskInfo").append("<option value='" + list[i].f_id + "'>" + list[i].f_scenename + "</option>");
          }

          getInitModeInfo($('#f_sceneInfo').val(),function () {

          });
        },
        error: function () {
          swal({
            title: "场景查询出错!",
            text: "",// 内容
            type: "warning",
            showCloseButton: false, // 展示关闭按钮
            allowOutsideClick: false,
            confirmButtonColor: "#1ab394",
            showConfirmButton: false,
            timer: 1500
          });
        }
      })
    }

    //获取模式信息
    function getInitModeInfo(value,callback) {
      $.ajax({
        type: "post",
        url: "${ctx}/view/basedatamanage/eqmanage/selectOptionByMode",
        dataType: "json",
        async: false,
        data: {
          f_sceneInfoId: value
        },
        success: function (result) {

          sceneType = result.data;
          var list = result.list;
          $("#f_modeInfo").find('option').remove();//新增控制计划的模式下拉框
          $("#f_modeTaskInfo").find('option').remove();//新增采集计划的模式下拉框
          $("#update_f_modeInfo").find('option').remove();//修改控制计划的模式下拉框
          $("#updatef_modeTaskInfo").find('option').remove();//修改采集计划的模式下拉框
          if (list == undefined) {
            return;
          } else {
            for (var i = 0; i < list.length; i++) {
              $("#f_modeInfo").append("<option value='" + list[i].f_id + "'>" + list[i].f_modename + "</option>");
              $("#f_modeTaskInfo").append("<option value='" + list[i].f_id + "'>" + list[i].f_modename + "</option>");
              $("#update_f_modeInfo").append("<option value='" + list[i].f_id + "'>" + list[i].f_modename + "</option>");
              $("#updatef_modeTaskInfo").append("<option value='" + list[i].f_id + "'>" + list[i].f_modename + "</option>");
            }

          }
          callback(result);
        },
        error: function () {
          swal({
            title: "模式查询出错!",
            text: "",// 内容
            type: "warning",
            showCloseButton: false, // 展示关闭按钮
            allowOutsideClick: false,
            confirmButtonColor: "#1ab394",
            showConfirmButton: false,
            timer: 1500
          });
        }
      })
    }

    //计划树
    function plan_Tree() {
      $.ajax({
        type: "post",
        url: "${ctx}/view/basedatamanage/eqmanage/getPlanTree",
        dataType: "json",
        async: false,
        success: function (result) {

          var status = result && result.status;

          if (status !== '1') {
            return;
          }

          var data = result.list;

          if (!Array.isArray(data)) {
            return;
          }

          var planTree = new Tree({
            container: 'planTree',
            idKey: 'f_id',
            pIdKey: 'f_pId',
            name: 'f_name',
            f_type: 'f_type',
            showCheckbox: false,
            selected: false,
            noRemoveBtn: true,
            noEditBtn: true,
            setting: {
              view: {
                showIcon: false,
                //selectedMulti: true,
                txtSelectedEnable: false,
                fontCss: function (treeId, treeNode) {
                  return (treeNode.highlight)
                      ? {color: '#A60000', 'font-weight': 'bold'}
                      : {color: '#8FE3F7', "font-family": "none", 'font-weight': 'normal'};
                },
                addHoverDom: addHoverDom, //当鼠标移动到节点上时，显示用户自定义控件
                removeHoverDom: removeHoverDom, //离开节点时的操作
              },
              check: {
                enable: false,
                chkboxType: {"Y": "", "N": ""}   //勾选 checkbox 对于父子节点的关联关系
              },
              edit: {
                drag: {
                  isCopy: false,
                  isMove: false
                },
                enable: true,
                editNameSelectAll: false,
                showRemoveBtn: showRemoveBtn,
                showRenameBtn: showRenameBtn
              },
              async: {
                enable: false,
                dataFilter: filter
              },
              callback: {
                onClick: pointDosth,
                // beforeDrag: beforeDrag,
                // beforeEditName: beforeEditName,
                beforeRemove: beforeRemove,
                // beforeRename: beforeRename,
                onRemove: zTreeOnRemove,
                onRename: onRename,
                //onCheck:zTreeOnCheck(nodes),
                setting: {
                  data: {
                    enable: false
                  }
                }
              }
            }
          })
          planTree.loadData(data);
        },
        error: function () {

        }
      })
    }

    //节点点击事件 将Id放全局变量里面
    function pointDosth(e, treeId, treeNode) {

      if (treeNode.level === 0) {
        $('#planConfig_content').hide();
      } else {
        $('#planConfig_content').show();
      }

      var nodeType = treeNode.f_type;
      nodeId = treeNode.f_id;
      BooPagination = true;
      if (nodeType == 1) { //控制计划
        $("#planTableParam").show(); //显示控制table
        $("#planCollectTableParam").hide(); //隐藏采集table
        $("#showTaskBtn").hide(); //隐藏添加定时任务
        $("#showPlanBtn").show(); //显示新增计划信息
        getSceneInfoAndModeInfo(1);
        queryTableParam(nodeId);
      } else if (nodeType == 2) { //采集计划
        $("#planCollectTableParam").show(); //隐藏控制table
        $("#planTableParam").hide(); //显示采集table
        $('#showTaskBtn').show();//显示添加定时任务
        $("#showPlanBtn").hide();//隐藏新增计划信息
        getSceneInfoAndModeInfo(2);
        queryCollectTableParam(nodeId);
      }
    }

    /**
     * 根据计划类型加载相应的场景
     */
    function getSceneInfoAndModeInfo(data) {
      getInitSceneInfo(data);
    }

    function filter(event, treeId, parentNode, childNodes) {
      if (!childNodes) return null;
      return childNodes;
    }

    //控制根节点 主节点的增加编辑按钮是否展示
    function showRenameBtn(treeId, treeNode) {
      var id = treeNode.f_id;
      if (id === '0' || id === '1' || id === '2' || id === '3') {
        return false;
      } else {
        return true;
      }
    }

    //控制根节点 主节点的增加删除按钮是否展示
    function showRemoveBtn(treeId, treeNode) {
      var id = treeNode.f_id;
      if (id === '0' || id === '1' || id === '2' || id === '3') {
        return false;
      } else {
        return true;
      }
    }

    //控制根节点 主节点的增加按钮是否展示
    function addHoverDom(treeId, treeNode) {
      var id = treeNode.f_id;
      if (id === '2' || id === '3') {
        insertNode(treeId, treeNode);
        return false;
      } else {
        return false;
      }
    }

    //ztree删除节点之前判断是否允许删除节点（这个方法做了一些修改，因为ztree自带的弹窗不是很美观好用）
    function beforeRemove(treeId, treeNode) {
      swal({
        title: "确认删除" + treeNode.f_name + "吗?",
        text: "",// 内容
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#1ab394",
        confirmButtonText: "确定",
        closeOnConfirm: false
      }, function () {
        var zTree = $.fn.zTree.getZTreeObj("planTree");
        zTree.removeNode(treeNode);
        zTreeOnRemove(treeNode);
      });
      return false;
    }

    //点位树展示
    // $('#f_timeTask').focus(function () {
    //     $('#selectTaskTree').modal('show');
    // });

    //查询该点位控制计划数据
    function queryTableParam(nodeId) {
      $.ajax({
        type: "post",
        dataType: "json",
        async: false,
        url: "${ctx}/view/basedatamanage/eqmanage/selectPlanInfomation",
        data: {
          nodeId: nodeId
        },
        success: function (result) {

          var planData = result.list;
          if (planData == undefined) {
            const list = [];
            initPlanTable(list);
          } else {
            initPlanTable(planData);
          }
        },
        error: function () {
        }
      })
    }

    //查询该点位采集计划数据
    function queryCollectTableParam(nodeId) {

      $.ajax({
        type: "post",
        dataType: "json",
        async: false,
        url: "${ctx}/view/basedatamanage/eqmanage/selectCollectPlanInfomation",
        data: {
          nodeId: nodeId
        },
        success: function (result) {
          var planData = result.list;
          if (planData == undefined) {
            const list = [];
            initCollectTable(list);
          } else {
            initCollectTable(planData);
          }
        },
        error: function () {
        }
      })
    }

    //新增节点
    function insertNode(treeId, treeNode) {
      var zTree = $.fn.zTree.getZTreeObj("planTree"); //获取树形图里面的数据
      var sObj = $("#" + treeNode.tId + "_span"); //获取节点信息
      if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0) return;
      var addStr = "<span class='button add' id='addBtn_" + treeNode.tId + "' title='新增一个计划' οnfοcus='this.blur();'></span>"; //定义添加按钮
      sObj.after(addStr); //加载添加按钮
      var btn = $("#addBtn_" + treeNode.tId);
      if (treeNode.f_type === '1') {
        //绑定添加事件，并定义添加操作
        if (btn) btn.bind("click", function () {
          const fname = "新建控制计划"; //新增节点的名字
          const pId = "2";
          $.ajax({
            type: "post",
            dataType: "json",
            async: false,
            url: "${ctx}/view/basedatamanage/eqmanage/insertContPlan",
            data: {
              f_name: fname,
              f_type: "1",
              f_pId: pId
            },
            success: function (result) {
              if (result.status === '1') {
                var id = result.data;
                zTree.addNodes(treeNode, {f_id: id, f_pId: pId, f_name: fname, f_type: '1'})
              } else {
                swal({
                  title: "采集计划添加失败",
                  text: "",// 内容
                  type: "warning",
                  showCloseButton: false, // 展示关闭按钮
                  allowOutsideClick: false,
                  confirmButtonColor: "#1ab394",
                  showConfirmButton: false,
                  timer: 1500
                });
              }
            },
            error: function () {
              swal({
                title: "采集计划添加失败",
                text: "",// 内容
                type: "warning",
                showCloseButton: false, // 展示关闭按钮
                allowOutsideClick: false,
                confirmButtonColor: "#1ab394",
                showConfirmButton: false,
                timer: 1500
              });
            }
          })
        });
      } else if (treeNode.f_type === "2") {
        //绑定添加事件，并定义添加操作
        if (btn) btn.bind("click", function () {
          const fname = "新建采集计划"; //新增节点的名字
          const pId = "3";
          $.ajax({
            type: "post",
            dataType: "json",
            async: false,
            url: "${ctx}/view/basedatamanage/eqmanage/insertCollectPlan",
            data: {
              f_name: fname,
              f_type: "2",
              f_pId: pId
            },
            success: function (result) {
              if (result.status === '1') {
                var id = result.data;
                zTree.addNodes(treeNode, {f_id: id, f_pId: pId, f_name: fname, f_type: "2"})
              } else {
                swal({
                  title: "采集计划添加失败",
                  text: "",// 内容
                  type: "warning",
                  showCloseButton: false, // 展示关闭按钮
                  allowOutsideClick: false,
                  confirmButtonColor: "#1ab394",
                  showConfirmButton: false,
                  timer: 1500
                });
              }
            },
            error: function () {
              swal({
                title: "采集计划添加失败",
                text: "",// 内容
                type: "warning",
                showCloseButton: false, // 展示关闭按钮
                allowOutsideClick: false,
                confirmButtonColor: "#1ab394",
                showConfirmButton: false,
                timer: 1500
              });
            }
          })
        });
      }
    }

    //删除此节点
    function zTreeOnRemove(treeNode) {
      var id = treeNode.f_id;
      $.ajax({
        type: "post",
        dataType: "json",
        async: false,
        url: "${ctx}/view/basedatamanage/eqmanage/deletePlanInfo",
        data: {
          id: id,
        },
        success: function (result) {
          if (result.status === '1') {
            swal({
              title: "删除成功",
              text: "",// 内容
              type: "success",
              showCloseButton: false, // 展示关闭按钮
              allowOutsideClick: false,
              confirmButtonColor: "#1ab394",
              showConfirmButton: false,
              timer: 1500
            });

            $('#planConfig_content').hide();
            // initPlanTable([]);
          } else {
            swal({
              title: "删除失败",
              text: "",// 内容
              type: "error",
              showCloseButton: false, // 展示关闭按钮
              allowOutsideClick: false,
              confirmButtonColor: "#1ab394",
              showConfirmButton: false,
              timer: 1500
            });
          }
        },
        error: function () {
          swal({
            title: "删除失败",
            text: "",// 内容
            type: "error",
            showCloseButton: false, // 展示关闭按钮
            allowOutsideClick: false,
            confirmButtonColor: "#1ab394",
            showConfirmButton: false,
            timer: 1500
          });
        }
      })
    }

    //销毁
    function removeHoverDom(treeId, treeNode) {
      $("#addBtn_" + treeNode.tId).unbind().remove();
    }

    //显示新增数据
    function showPlanInsertInfo() {
      $('#addPlanConfig').modal('show');
    }

    //显示添加定时任务
    function showTaskInsertInfo() {
      $('#addTimeTaskConfig').modal('show');
      BooaddTimeTaskConfig = true;
    }

    //修改名称
    function onRename(e, treeId, treeNode) {
      var zTree = $.fn.zTree.getZTreeObj("planTree");
      nodes = zTree.getSelectedNodes(),
          treeNode = nodes[0];
      var id = treeNode.f_id;
      var name = treeNode.f_name;
      $.ajax({
        type: "post",
        dataType: "json",
        async: false,
        url: "${ctx}/view/basedatamanage/eqmanage/editPlanInfo",
        data: {
          f_id: id,
          f_name: name
        },
        success: function (result) {
          if (result.status === '1') {
            if (nodes.length > 0) {
              nodes.name = name
              zTree.updateNode(nodes[0]);
            }
          } else {
            swal({
              title: "计划修改失败",
              text: "",// 内容
              type: "warning",
              showCloseButton: false, // 展示关闭按钮
              allowOutsideClick: false,
              confirmButtonColor: "#1ab394",
              showConfirmButton: false,
              timer: 1500
            });
          }
        },
        error: function () {
          swal({
            title: "计划修改失败",
            text: "",// 内容
            type: "warning",
            showCloseButton: false, // 展示关闭按钮
            allowOutsideClick: false,
            confirmButtonColor: "#1ab394",
            showConfirmButton: false,
            timer: 1500
          });
        }
      })
    }

    //控制计划table初始化
    function initPlanTable(data) {

      // let cr = "1";
      // // let cr= $(".layui-laypage-em").next().html();
      // let layui_laypage_em_Length = $("em.layui-laypage-em").next();
      // if (typeof layui_laypage_em_Length != 'undefined') {
      //   if (layui_laypage_em_Length.length >= 2) {
      //     layui_laypage_em_Length.splice(0,layui_laypage_em_Length.length - 1);
      //   }
      // }
      // if (typeof $("em.layui-laypage-em").next()[0] == 'undefined') {
      //   cr= $(".layui-laypage-em").next().html();
      // } else {
      //   cr = layui_laypage_em_Length[0].innerText;
      // }

      if (!BooAdd_or_Edit) {
        if (typeof cr == 'undefined' || data.length <= 17) {
          cr = 1;
        } else if (data.length > 17 && data.length <= 34) {
          cr = 2;
        } else if (data.length > 34 && data.length <= 51) {
          cr = 3;
        } else if (data.length > 51 && data.length <= 68) {
          cr = 4;
        } else if (data.length > 68 && data.length <= 85) {
          cr = 5;
        } else if (data.length > 85 && data.length <= 102) {
          cr = 6;
        } else if (data.length > 102 && data.length <= 119) {
          cr = 7;
        } else if (data.length > 119 && data.length <= 136) {
          cr = 8;
        } else if (data.length > 136 && data.length <= 154) {
          cr = 9;
        } else if (data.length > 154 && data.length <= 171) {
          cr = 10;
        }
      }

      if (cr*17 > data.length) {
        if (data.length <= 17) {
          cr = 1;
        } else if (data.length > 17 && data.length <= 34) {
          cr = 2;
        } else if (data.length > 34 && data.length <= 51) {
          cr = 3;
        } else if (data.length > 51 && data.length <= 68) {
          cr = 4;
        } else if (data.length > 68 && data.length <= 85) {
          cr = 5;
        } else if (data.length > 85 && data.length <= 102) {
          cr = 6;
        } else if (data.length > 102 && data.length <= 119) {
          cr = 7;
        } else if (data.length > 119 && data.length <= 136) {
          cr = 8;
        } else if (data.length > 136 && data.length <= 154) {
          cr = 9;
        } else if (data.length > 154 && data.length <= 171) {
          cr = 10;
        }
      }

      if (BooPagination) {
        cr = 1;
      }

      BooPagination = false;
      BooAdd_or_Edit = false;

      layui.use(['opTable'], function () {
        tabAcademeNotice = layui.opTable.render({
          elem: '#planTable'
          , data: data
          , cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
          // 第一列显示展开
          , openColumnIndex: 0
          // 不单独占一列
          , isAloneColumn: false
          , opOrientation: 'h'
          , page: {
            limit: 17,
            curr: cr
          }
          // , limit: Number.MAX_VALUE // 数据表格默认全部显示
          , cols: [[
            {field: 'f_sysname', title: '计划名称', width: 180}
            , {field: 'f_nickname', title: '计划别名', width: 140}
            , {field: 'f_startday', title: '开始日期', width: 170}
            , {field: 'f_enday', title: '结束日期', width: 170}
            , {field: 'f_scenename', title: '场景名称', width: 129}
            , {
              field: 'f_planType', title: '是否替代日', width: 100, templet: function (res) {
                if (res.f_planType == 0) {
                  return '否'
                } else {
                  return '是'
                }
              }
            }
            , {
              field: 'f_active', title: '是否启用', width: 95, templet: function (res) {
                if (res.f_active == 1) {
                  return '启用'
                } else {
                  return '不启用'
                }
              }
            }
            , {
              field: 'f_execut', title: '执行方式', width: 91, templet: function (res) {
                if (res.f_execut == 1) {
                  return '持续执行'
                } else {
                  return '按天执行'
                }
              }
            }
            , {field: 'sign', title: '操作', align: 'center', toolbar: '#rowPlanToolBar', width: 234}
          ]]
          ,done:function(res,curr,count){ // 隐藏列
            cr = curr
            // $(".layui-table-box").find("[data-field='f_id']").css("display","none");
          }
          //  展开的列配置
          , openCols: [
            {field: 'f_scenenickname', title: '场景别名'}
            , {field: 'f_discription', title: '场景描述'}
            , {field: 'f_modename', title: '模式名称'}
            , {
              field: 'f_weekmask', title: '周掩码', onDraw: function (res) {

                if (res.f_weekmask != null) {
                  let f_weekmaskName = "";
                  let indexArray = [];
                  let index = res.f_weekmask.indexOf('1'); // 字符出现的位置
                  if (index == -1) {
                    return '未选择'
                  }

                  while (index !== -1) {
                    indexArray.push(index);
                    index = res.f_weekmask.indexOf('1', index + 1); // 从字符串出现的位置的下一位置开始继续查找
                  }
                  indexArray.sort(function (a, b) {
                    return b - a;
                  })
                  indexArray.forEach(function (item) {
                    if (item == 0) {
                      f_weekmaskName += "周日  "
                    } else if (item == 1) {
                      f_weekmaskName += "周六  "
                    } else if (item == 2) {
                      f_weekmaskName += "周五  "
                    } else if (item == 3) {
                      f_weekmaskName += "周四  "
                    } else if (item == 4) {
                      f_weekmaskName += "周三  "
                    } else if (item == 5) {
                      f_weekmaskName += "周二  "
                    } else if (item == 6) {
                      f_weekmaskName += "周一  "
                    }
                  })
                  return f_weekmaskName
                } else {
                  return '未选择'
                }
              }
            }
            , {
              field: 'f_pointname', title: '关联点位', onDraw: function (res) {
                return res.f_pointname.replace(/。|,/g, "<br>");
              }
            }
          ]
          , skin: 'line' //表格风格
          , even: false
        })
      })
    }

    //采集计划collectTable初始化
    function initCollectTable(data) {

      // let cr = "1";
      // // let cr= $(".layui-laypage-em").next().html();
      // let layui_laypage_em_Length = $("em.layui-laypage-em").next();
      // if (typeof layui_laypage_em_Length != 'undefined') {
      //   if (layui_laypage_em_Length.length >= 2) {
      //     layui_laypage_em_Length.splice(0,layui_laypage_em_Length.length - 1);
      //   }
      // }
      // if (typeof $("em.layui-laypage-em").next()[0] == 'undefined') {
      //   cr= $(".layui-laypage-em").next().html();
      // } else {
      //   cr = layui_laypage_em_Length[0].innerText;
      // }

      if (!BooAdd_or_Edit) {
        if (typeof cr == 'undefined' || data.length <= 17) {
          cr = 1;
        } else if (data.length > 17 && data.length <= 34) {
          cr = 2;
        } else if (data.length > 34 && data.length <= 51) {
          cr = 3;
        } else if (data.length > 51 && data.length <= 68) {
          cr = 4;
        } else if (data.length > 68 && data.length <= 85) {
          cr = 5;
        } else if (data.length > 85 && data.length <= 102) {
          cr = 6;
        } else if (data.length > 102 && data.length <= 119) {
          cr = 7;
        } else if (data.length > 119 && data.length <= 136) {
          cr = 8;
        } else if (data.length > 136 && data.length <= 154) {
          cr = 9;
        } else if (data.length > 154 && data.length <= 171) {
          cr = 10;
        }
      }

      if (cr*17 > data.length) {
        if (data.length <= 17) {
          cr = 1;
        } else if (data.length > 17 && data.length <= 34) {
          cr = 2;
        } else if (data.length > 34 && data.length <= 51) {
          cr = 3;
        } else if (data.length > 51 && data.length <= 68) {
          cr = 4;
        } else if (data.length > 68 && data.length <= 85) {
          cr = 5;
        } else if (data.length > 85 && data.length <= 102) {
          cr = 6;
        } else if (data.length > 102 && data.length <= 119) {
          cr = 7;
        } else if (data.length > 119 && data.length <= 136) {
          cr = 8;
        } else if (data.length > 136 && data.length <= 154) {
          cr = 9;
        } else if (data.length > 154 && data.length <= 171) {
          cr = 10;
        }
      }

      if (BooPagination) {
        cr = 1;
      }

      BooPagination = false;
      BooAdd_or_Edit = false;
      layui.use(['opTable'], function () {
        var collectForm = layui.form;
        tabAcademeCollectNotice = layui.opTable.render({
          elem: '#planCollectTable'
          , data: data
          , cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
          // 第一列显示展开
          , openColumnIndex: 0
          // 不单独占一列
          , isAloneColumn: false
          , opOrientation: 'h'
          , page: {
            limit: 17,
            curr: cr
          }
          , cols: [[
            {field: 'f_sysname', title: '计划名称'}
            , {field: 'f_nickname', title: '计划别名'}
            , {field: 'f_timename', title: '定时任务名称'}
            , {field: 'f_scenename', title: '场景名称'}
            , {field: 'f_modename', title: '模式名称'}
            , {
              field: 'f_tasktype', title: '任务类型', width: 90, templet: function (res) {
                if (res.f_tasktype == 0) {
                  return '变化量'
                } else {
                  return '时间'
                }
              }
            }
            , {
              field: 'f_cronstartexpre', title: 'cron表达式', templet: function (res) {
                if (res.f_cronstartexpre == null || res.f_cronstartexpre == "") {
                  return '无数据'
                } else {
                  return res.f_cronstartexpre
                }
              }
            }
            , {field: 'f_specificvalue', title: '具体值'}
            , {field: 'f_invoke', title: '是否执行', width: 99, templet: '#switchTpl', unresize: true}
            , {field: 'sign', title: '操作', align: 'center', toolbar: '#rowPlanCollectToolBar'}
          ]]
          ,done:function(res,curr,count){ // 隐藏列
            cr = curr
            // $(".layui-table-box").find("[data-field='f_id']").css("display","none");
          }
          //  展开的列配置
          , openCols: [
            {field: 'f_scenenickname', title: '场景别名'}
            , {field: 'f_discription', title: '场景描述'}
            , {
              field: 'f_pointname', title: '关联点位', onDraw: function (res) {

                return res.f_pointname.replace(/,|,/g, "<br/>");
              }
            }
          ]
          , skin: 'line' //表格风格
          , even: false
        })
        //监听执行停止操作
        collectForm.on('switch(executeStop)', function (obj) {

          let execute; //1 执行 2 不执行

          let planId = this.value;

          let flag = obj.elem.checked;

          if (flag) {
            execute = "1";
          } else {
            execute = "2";
          }

          $.ajax({
            type: "post",
            url: "${ctx}/view/basedatamanage/eqmanage/selectTimeTaskInfoByPlanId",
            dataType: "json",
            async: false,
            data: {
              planId: planId
            },
            success: function (result) {

              var status = result.status;
              if (status == "1") {
                var data = result.map;
                executeDifferentTimeTask(planId, data, execute);
              }
            },
            error: function () {
              swal({
                title: '查询数据有错！',// 展示的标题
                type: "error",
                showCloseButton: false, // 展示关闭按钮
                allowOutsideClick: false,
                showConfirmButton: false,
                timer: 1500
              });
            }
          })
        });
      });
    }

    //控制计划监听行工具事件 同步，对比,修改和删除
    layui.table.on('tool(demoPlanEvent)', function (obj) {
      //获取当前信息的计划id
      f_id = obj.data.f_id;
      switch (obj.event) {
        case "system"://同步

          $.ajax({
            type: "post",
            dataType: "json",
            async: false,
            contentType: 'application/json; charset=UTF-8',
            url: "${ctx}/view/basedatamanage/eqmanage/synchronization",
            data: JSON.stringify({
              data: obj.data
            }),
            success: function (result) {
              if (result.status == '1') {
                swal({
                  title: result.msg,
                  text: "",// 内容
                  type: "success",
                  showCloseButton: false, // 展示关闭按钮
                  allowOutsideClick: false,
                  confirmButtonColor: "#1ab394",
                  showConfirmButton: false,
                  timer: 1500
                });
                // $("#isynchro").removeAttr("hidden");
                // $("#notynchro").attr("hidden");
              } else {
                swal({
                  title: result.msg,
                  text: "",// 内容
                  type: "error",
                  showCloseButton: false, // 展示关闭按钮
                  allowOutsideClick: false,
                  confirmButtonColor: "#1ab394",
                  showConfirmButton: false,
                  timer: 1500
                });
              }
            },
            error: function () {
              swal({
                title: "同步失败！",
                text: "",// 内容
                type: "error",
                showCloseButton: false, // 展示关闭按钮
                allowOutsideClick: false,
                confirmButtonColor: "#1ab394",
                showConfirmButton: false,
                timer: 1500
              });
            }
          })
          break;
        case "compare"://对比

          var fId = obj.data.f_id;

          let data = obj.data;

          $("#contrast_planInfoId").val(data.f_id); //计划Id

          $("#contrast_planName").val(data.f_sysname); //计划名称

          $("#contrast_planNickname").val(data.f_nickname); //计划别名

          if (data.f_active == "1") {
            $("#contrast_planEnable").val("启用"); //是否使能
          } else {
            $("#contrast_planEnable").val("不启用"); //是否使能
          }

          $("#contrast_sceneId").val(data.f_sceneInfoId); //场景Id

          $("#planContrast_sceneName").val(data.f_scenename); //场景名称

          $("#planContrast_modeId").val(data.f_modeInfoId); //模式ID

          $("#planContrast_modeName").val(data.f_modename); //模式名称

          $("#contrast_planStartDate").val(data.f_startday.substring(0, 10)); //计划开始日期

          $("#contrast_planStartTime").val(data.f_startime); //计划开始时间

          $("#contrast_planEndDate").val(data.f_enday.substring(0, 10)); //计划结束日期

          $("#contrast_planEndTime").val(data.f_endtime); //计划结束时间
          //对比
          $.ajax({
            type: "post",
            dataType: "json",
            async: false,
            url: "${ctx}/view/basedatamanage/eqmanage/selectUnderInfomation",
            data: {
              fId: fId,
              planId: nodeId
            },
            success: function (result) {
              if (result.status == "1") {
                swal({
                  title: "下位机获取成功!",
                  text: "",// 内容
                  type: "success",
                  showCloseButton: false, // 展示关闭按钮
                  allowOutsideClick: false,
                  confirmButtonColor: "#1ab394",
                  showConfirmButton: false,
                  timer: 1500
                });
              } else {
                swal({
                  title: "下位机获取失败!",
                  text: "",// 内容
                  type: "error",
                  showCloseButton: false, // 展示关闭按钮
                  allowOutsideClick: false,
                  confirmButtonColor: "#1ab394",
                  showConfirmButton: false,
                  timer: 1500
                });
              }
            },
            error: function () {
              swal({
                title: "下位机获取失败!",
                text: "",// 内容
                type: "error",
                showCloseButton: false, // 展示关闭按钮
                allowOutsideClick: false,
                confirmButtonColor: "#1ab394",
                showConfirmButton: false,
                timer: 1500
              });
            }
          })
          $('#contrastPlanValue').modal('show');
          break;
        case "update":
          $('#updatePlanConfig').on('show.bs.modal', function () {

            //获取当前计划的详细信息
            $("#update_f_planName").val(obj.data.f_sysname);//计划名称
            $("#update_f_planNickName").val(obj.data.f_nickname);//计划昵称
            if (obj.data.f_active == "1") {//使能
              $("#update_f_active").prop('checked', true);
            } else {
              $("#update_f_active").prop('checked', false);
            }
            $("#update_f_active").val(obj.data.f_active);

            if (obj.data.f_planType == "1") {//常规计划(0)或节假日计划(1)
              $("#update_f_planType").prop('checked', true);
            } else {
              $("#update_f_planType").prop('checked', false);
            }
            $("#update_f_planType").val(obj.data.f_planType);

            if (obj.data.f_execut == "1") {//每天执行(0)、持续执行(1)）
              $("#update_f_executionWay2").prop('checked', true);//radio默认选中
            } else {
              $("#update_f_executionWay1").prop('checked', true);//radio默认选中
            }
            $("#update_f_executionWay1").val("0");
            $("#update_f_executionWay2").val("1");

            let f_weekmask = obj.data.f_weekmask;
            if (f_weekmask[0] == "1") {//周日
              $('#update_f_weekmask_7').prop('checked', true);
              $("#update_f_weekmask_7").val("1");
            } else {
              $('#update_f_weekmask_7').prop('checked', false);
              $("#update_f_weekmask_7").val("0");
            }
            if (f_weekmask[1] == "1") {//周六
              $('#update_f_weekmask_6').prop('checked', true);
              $("#update_f_weekmask_6").val("1");
            } else {
              $('#update_f_weekmask_6').prop('checked', false);
              $("#update_f_weekmask_6").val("0");
            }
            if (f_weekmask[2] == "1") {//周五
              $('#update_f_weekmask_5').prop('checked', true);
              $("#update_f_weekmask_5").val("1");
            } else {
              $('#update_f_weekmask_5').prop('checked', false);
              $("#update_f_weekmask_5").val("0");
            }
            if (f_weekmask[3] == "1") {//周四
              $('#update_f_weekmask_4').prop('checked', true);
              $("#update_f_weekmask_4").val("1");
            } else {
              $('#update_f_weekmask_4').prop('checked', false);
              $("#update_f_weekmask_4").val("0");
            }
            if (f_weekmask[4] == "1") {//周三
              $('#update_f_weekmask_3').prop('checked', true);
              $("#update_f_weekmask_3").val("1");
            } else {
              $('#update_f_weekmask_3').prop('checked', false);
              $("#update_f_weekmask_3").val("0");
            }
            if (f_weekmask[5] == "1") {//周二
              $('#update_f_weekmask_2').prop('checked', true);
              $("#update_f_weekmask_2").val("1");
            } else {
              $('#update_f_weekmask_2').prop('checked', false);
              $("#update_f_weekmask_2").val("0");
            }
            if (f_weekmask[6] == "1") {//周一
              $('#update_f_weekmask_1').prop('checked', true);
              $("#update_f_weekmask_1").val("1");
            } else {
              $('#update_f_weekmask_1').prop('checked', false);
              $("#update_f_weekmask_1").val("0");
            }

            $("#update_f_sceneInfo").val(obj.data.f_sceneInfoId);

            getInitModeInfo(obj.data.f_sceneInfoId,function () {

              $("#update_f_modeInfo").val(obj.data.f_modeInfoId);
            });
            $("#update_f_startDate").val(obj.data.f_startday.substring(0, 10));//开始日期
            $("#update_f_starTime").val(obj.data.f_startime);//开始时间
            $("#update_f_endDate").val(obj.data.f_enday.substring(0, 10));//结束日期
            $("#update_f_endTime").val(obj.data.f_endtime);//结束时间
          });
          //弹出修改模态框
          $('#updatePlanConfig').modal('show');
          break;
        case "delete"://删除
          var fId = obj.data.f_id;
          swal({
            title: "确认删除" + obj.data.f_sysname + "吗?",
            text: "",// 内容
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#1ab394",
            confirmButtonText: "确定",
            closeOnConfirm: false
          }, function () {
            $.ajax({
              type: "post",
              dataType: "json",
              async: false,
              url: "${ctx}/view/basedatamanage/eqmanage/deletePlanInfomation",
              data: {
                fId: fId
              },
              success: function (result) {
                // var status = result.status;
                if (result.status == "1") {
                  swal({
                    title: result.msg,
                    text: "",// 内容
                    type: "success",
                    showCloseButton: false, // 展示关闭按钮
                    allowOutsideClick: false,
                    confirmButtonColor: "#1ab394",
                    showConfirmButton: false,
                    timer: 1500
                  });
                  BooAdd_or_Edit = true;
                  queryTableParam(nodeId);
                } else {
                  swal({
                    title: result.msg,
                    text: "",// 内容
                    type: "success",
                    showCloseButton: false, // 展示关闭按钮
                    allowOutsideClick: false,
                    confirmButtonColor: "#1ab394",
                    showConfirmButton: false,
                    timer: 1500
                  });
                }
              },
              error: function () {
                swal({
                  title: "控制计划删除失败!",
                  text: "",// 内容
                  type: "error",
                  showCloseButton: false, // 展示关闭按钮
                  allowOutsideClick: false,
                  confirmButtonColor: "#1ab394",
                  showConfirmButton: false,
                  timer: 1500
                });
              }
            })
            // queryTableParam(nodeId);
          });
          break;
        default:
          break;
      }
    })

    //采集计划监听行工具事件 执行，停止和删除
    layui.table.on('tool(demoCollectPlanEvent)', function (obj) {

      //获取当前信息的计划id
      f_id = obj.data.f_id;
      //获取当前计划是否执行的值
      invokeValue = obj.data.f_invoke;
      f_jobId = obj.data.f_jobId;

      if (obj.event === 'collectDelete') {//删除
        swal({
          title: "确认删除" + obj.data.f_sysname + "吗?",
          text: "",// 内容
          type: "warning",
          showCancelButton: true,
          confirmButtonColor: "#1ab394",
          confirmButtonText: "确定",
          closeOnConfirm: false
        }, function () {
          $.ajax({
            type: "post",
            dataType: "json",
            async: false,
            contentType: 'application/json; charset=UTF-8',
            url: "${ctx}/view/basedatamanage/eqmanage/deleteInfomation",
            data: JSON.stringify({
              data: obj.data
            }),
            success: function (result) {
              var status = result.status;
              if (status == "1") {
                swal({
                  title: "采集计划删除成功!",
                  text: "",// 内容
                  type: "success",
                  showCloseButton: false, // 展示关闭按钮
                  allowOutsideClick: false,
                  confirmButtonColor: "#1ab394",
                  showConfirmButton: false,
                  timer: 1500
                });
                BooAdd_or_Edit = true;
                queryCollectTableParam(nodeId);
              } else {
                swal({
                  title: "采集计划删除失败!",
                  text: "",// 内容
                  type: "success",
                  showCloseButton: false, // 展示关闭按钮
                  allowOutsideClick: false,
                  confirmButtonColor: "#1ab394",
                  showConfirmButton: false,
                  timer: 1500
                });
              }
            },
            error: function () {
              swal({
                title: "采集计划删除失败!",
                text: "",// 内容
                type: "error",
                showCloseButton: false, // 展示关闭按钮
                allowOutsideClick: false,
                confirmButtonColor: "#1ab394",
                showConfirmButton: false,
                timer: 1500
              });
            }
          })
        });
      } else if (obj.event === 'collectUpdate') {//修改

        $('#updateTimeTaskConfig').on('show.bs.modal', function () {
          //获取当前计划的详细信息
          $("#updateplanTaskName").val(obj.data.f_sysname);//计划名称
          $("#updateplanTaskNickname").val(obj.data.f_nickname);//计划昵称
          $("#updatef_timename").val(obj.data.f_timename);//定时任务名称
          $("#updatef_tasktype").val(obj.data.f_tasktype);//任务类型 0:变化量  1:时间
          if (obj.data.f_tasktype == "1") {//时间
            $("#updatespecificvalueKZ").hide();
            $("#updateplanTimeConfigKZ").show();
            $("#updateplanTimeConfigKZ").val("");
            $("#updatespecificvalueKZ").val("");

            $("#updateplanTimeConfig").val(obj.data.f_cronstartexpre);//cron表达式
          } else if (obj.data.f_tasktype == "0") {//变化量
            $("#updatespecificvalueKZ").show();
            $("#updateplanTimeConfigKZ").hide();
            $("#updateplanTimeConfigKZ").val("");
            $("#updatespecificvalueKZ").val("");
            $("#updatef_specificvalue").val(obj.data.f_specificvalue);//具体值(变化量的值)
          } else {
            $("#updatespecificvalueKZ").hide();
            $("#updateplanTimeConfigKZ").hide();
            $("#updateplanTimeConfigKZ").val("");
            $("#updatespecificvalueKZ").val("");
          }

          $("#updatef_sceneTaskInfo").val(obj.data.f_sceneInfoId);//场景名称 f_sceneInfoId  f_scenename
          // $("#updatef_modeTaskInfo").val(obj.data.f_modeInfoId);//模式名称 f_modeInfoId  f_modename


          getInitModeInfo(obj.data.f_sceneInfoId,function () {

            $("#updatef_modeTaskInfo").val(obj.data.f_modeInfoId);
          });
        });
        //弹出修改模态框
        $('#updateTimeTaskConfig').modal('show');

      }

    })

    //执行时间类型/变化量类型的 执行 停止
    function executeDifferentTimeTask(planId, data, execute) {
      var tasktype = data.f_tasktype;
      var taskId = data.f_id;

      if (tasktype === '0') {//变化量
        if (execute === "1") {//执行

          $.ajax({
            type: "post",
            dataType: "json",
            async: false,
            //contentType: 'application/json; charset=UTF-8',
            url: "${ctx}/view/basedatamanage/eqmanage/executeInfomation",
            data: {
              taskId: taskId,
              planId: planId
            },
            success: function (result) {
              var status = result.status;
              if (status == "1") {
                swal({
                  title: "采集计划运行!",
                  text: "",// 内容
                  type: "success",
                  showCloseButton: false, // 展示关闭按钮
                  allowOutsideClick: false,
                  confirmButtonColor: "#1ab394",
                  showConfirmButton: false,
                  timer: 1500
                });
              } else {
                swal({
                  title: "采集计划运行失败!",
                  text: "",// 内容
                  type: "error",
                  showCloseButton: false, // 展示关闭按钮
                  allowOutsideClick: false,
                  confirmButtonColor: "#1ab394",
                  showConfirmButton: false,
                  timer: 1500
                });
              }
            },
            error: function () {
              swal({
                title: "采集计划运行失败!",
                text: "",// 内容
                type: "error",
                showCloseButton: false, // 展示关闭按钮
                allowOutsideClick: false,
                confirmButtonColor: "#1ab394",
                showConfirmButton: false,
                timer: 1500
              });
            }
          })
        } else {//不执行

          $.ajax({
            type: "post",
            dataType: "json",
            async: false,
            url: "${ctx}/view/basedatamanage/eqmanage/stopInfomation",
            data: {
              taskId: taskId,
              planId: planId
            },
            success: function (result) {
              var status = result.status;
              if (status == "1") {
                swal({
                  title: "采集计划停止!",
                  text: "",// 内容
                  type: "warning",
                  showCloseButton: false, // 展示关闭按钮
                  allowOutsideClick: false,
                  confirmButtonColor: "#1ab394",
                  showConfirmButton: false,
                  timer: 1500
                });
              } else {
                swal({
                  title: "采集计划停止失败!",
                  text: "",// 内容
                  type: "error",
                  showCloseButton: false, // 展示关闭按钮
                  allowOutsideClick: false,
                  confirmButtonColor: "#1ab394",
                  showConfirmButton: false,
                  timer: 1500
                });
              }
            },
            error: function () {
              swal({
                title: "采集计划停止失败!",
                text: "",// 内容
                type: "error",
                showCloseButton: false, // 展示关闭按钮
                allowOutsideClick: false,
                confirmButtonColor: "#1ab394",
                showConfirmButton: false,
                timer: 1500
              });
            }
          })
        }
      } else { //时间
        var jobId = data.f_jobId;
        if (execute === "1") {//执行

          $.ajax({
            type: "post",
            dataType: "json",
            async: false,
//                      contentType: 'application/json; charset=UTF-8',
            url: "${ctx}/quartz/executeInfomation",
            data: {
              jobId: jobId,
              planId: planId
            },
            success: function (result) {

              var status = result.status;
              if (status == "1") {
                swal({
                  title: "采集计划运行!",
                  text: "",// 内容
                  type: "success",
                  showCloseButton: false, // 展示关闭按钮
                  allowOutsideClick: false,
                  confirmButtonColor: "#1ab394",
                  showConfirmButton: false,
                  timer: 1500
                });
              } else {
                swal({
                  title: "采集计划运行失败!",
                  text: "",// 内容
                  type: "error",
                  showCloseButton: false, // 展示关闭按钮
                  allowOutsideClick: false,
                  confirmButtonColor: "#1ab394",
                  showConfirmButton: false,
                  timer: 1500
                });
              }
            },
            error: function () {
              swal({
                title: "采集计划运行失败!",
                text: "",// 内容
                type: "error",
                showCloseButton: false, // 展示关闭按钮
                allowOutsideClick: false,
                confirmButtonColor: "#1ab394",
                showConfirmButton: false,
                timer: 1500
              });
            }
          })
        } else {//不执行

          $.ajax({
            type: "post",
            dataType: "json",
            async: false,
//                      contentType: 'application/json; charset=UTF-8',
            url: "${ctx}/quartz/stopInfomation",
            data: {
              jobId: jobId,
              planId: planId
            },
            success: function (result) {

              var status = result.status;
              if (status == "1") {
                swal({
                  title: "采集计划停止!",
                  text: "",// 内容
                  type: "warning",
                  showCloseButton: false, // 展示关闭按钮
                  allowOutsideClick: false,
                  confirmButtonColor: "#1ab394",
                  showConfirmButton: false,
                  timer: 1500
                });
              } else {
                swal({
                  title: "采集计划停止失败!",
                  text: "",// 内容
                  type: "error",
                  showCloseButton: false, // 展示关闭按钮
                  allowOutsideClick: false,
                  confirmButtonColor: "#1ab394",
                  showConfirmButton: false,
                  timer: 1500
                });
              }
            },
            error: function () {
              swal({
                title: "采集计划停止失败!",
                text: "",// 内容
                type: "error",
                showCloseButton: false, // 展示关闭按钮
                allowOutsideClick: false,
                confirmButtonColor: "#1ab394",
                showConfirmButton: false,
                timer: 1500
              });
            },
          })
        }
      }
    }

    //添加计划
    function submitPlanInfo() {
      let f_sysname = $("#f_planName").val();//计划名称
      let f_nickname = $("#f_planNickName").val();//计划别名
      let f_planType = $("#f_planType").val();//替代日(0:是;1:否)
      let f_startday = $("#f_startDate").val();//开始日期
      let f_startime = $("#f_starTime").val();//开始时间
      let f_enday = $("#f_endDate").val();//结束日期
      let f_endtime = $("#f_endTime").val();//结束时间
      let f_active = $("#f_active").val();//是否使能(默认为是:1)
      let f_modeInfoId = $("#f_modeInfo").val();//模式
      let f_sceneInfoId = $("#f_sceneInfo").val();//场景
      let f_executionWay = $('input[name="f_executionWay"]:checked').val();//执行方式(0:按天执行;1:持续执行)
      let fWeekmask = $("#f_weekmask_7").val() + $("#f_weekmask_6").val() + $("#f_weekmask_5").val() + $("#f_weekmask_4").val() +
          $("#f_weekmask_3").val() + $("#f_weekmask_2").val() + $("#f_weekmask_1").val();

      if (f_sysname == '' || f_sysname == null) {
        swal({
          title: '名称不能为空!',// 展示的标题
          text: "",// 内容
          type: "warning",
          showCloseButton: false, // 展示关闭按钮
          allowOutsideClick: false,
          showConfirmButton: false,
          timer: 1000
        });
        return;
      }
      if (f_nickname == '' || f_nickname == null) {
        swal({
          title: '别名不能为空!',// 展示的标题
          text: "",// 内容
          type: "warning",
          showCloseButton: false, // 展示关闭按钮
          allowOutsideClick: false,
          showConfirmButton: false,
          timer: 1000
        });
        return;
      }
      if (f_startday == '' || f_startday == null) {
        swal({
          title: '开始日期不能为空!',// 展示的标题
          text: "",// 内容
          type: "warning",
          showCloseButton: false, // 展示关闭按钮
          allowOutsideClick: false,
          showConfirmButton: false,
          timer: 1000
        });
        return;
      }
      if (f_startime == '' || f_startime == null) {
        swal({
          title: '开始时间不能为空!',// 展示的标题
          text: "",// 内容
          type: "warning",
          showCloseButton: false, // 展示关闭按钮
          allowOutsideClick: false,
          showConfirmButton: false,
          timer: 1000
        });
        return;
      }
      if (f_enday == '' || f_enday == null) {
        swal({
          title: '结束日期不能为空!',// 展示的标题
          text: "",// 内容
          type: "warning",
          showCloseButton: false, // 展示关闭按钮
          allowOutsideClick: false,
          showConfirmButton: false,
          timer: 1000
        });
        return;
      }
      if (f_endtime == '' || f_endtime == null) {
        swal({
          title: '结束时间不能为空!',// 展示的标题
          text: "",// 内容
          type: "warning",
          showCloseButton: false, // 展示关闭按钮
          allowOutsideClick: false,
          showConfirmButton: false,
          timer: 1000
        });
        return;
      }
      if (f_modeInfoId == '' || f_modeInfoId == null) {
        swal({
          title: '模式不能为空!',// 展示的标题
          text: "",// 内容
          type: "warning",
          showCloseButton: false, // 展示关闭按钮
          allowOutsideClick: false,
          showConfirmButton: false,
          timer: 1000
        });
        return;
      }
      if (f_sceneInfoId == '' || f_sceneInfoId == null) {
        swal({
          title: '场景不能为空!',// 展示的标题
          text: "",// 内容
          type: "warning",
          showCloseButton: false, // 展示关闭按钮
          allowOutsideClick: false,
          showConfirmButton: false,
          timer: 1000
        });
        return;
      }
      $.ajax({
        url: "${ctx}/view/basedatamanage/eqmanage/insertPlanInfo",
        type: "post",
        dataType: "json",
        async: false,
        contentType: 'application/json; charset=UTF-8',
        data: JSON.stringify({
          f_sysname: f_sysname,
          f_nickname: f_nickname,
          f_planType: f_planType,
          f_startday: f_startday,
          f_startime: f_startime,
          f_enday: f_enday,
          f_endtime: f_endtime,
          f_weekmask: fWeekmask,
          f_planId: nodeId,
          f_active: f_active,
          f_modeInfoId: f_modeInfoId,
          f_sceneInfoId: f_sceneInfoId,
          f_scenetype: sceneType,
          f_executionWay: f_executionWay,
          //f_taskId:$("#f_timeTaskId").val(),
        }),
        success: function (result) {
          if (result.status == '1') {
            swal({
              title: result.msg,
              text: "",// 内容
              type: "success",
              showCloseButton: false, // 展示关闭按钮
              allowOutsideClick: false,
              confirmButtonColor: "#1ab394",
              showConfirmButton: false,
              timer: 2000
            });
            BooAdd_or_Edit = true;
            queryTableParam(nodeId);
            $('#addPlanConfig').modal('hide');
            // $("#isynchro").removeAttr("hidden");
            // $("#notynchro").attr("hidden");
          } else {
            swal({
              title: result.msg,
              text: "",// 内容
              type: "warning",
              showCloseButton: false, // 展示关闭按钮
              allowOutsideClick: false,
              confirmButtonColor: "#1ab394",
              showConfirmButton: false,
              timer: 2000
            });
            // $("#isynchro").attr("hidden");
            // $("#notynchro").removeAttr("hidden");
          }

        },
        error: function () {
          swal({
            title: "控制计划添加失败",
            text: "",// 内容
            type: "warning",
            showCloseButton: false, // 展示关闭按钮
            allowOutsideClick: false,
            confirmButtonColor: "#1ab394",
            showConfirmButton: false,
            timer: 2000
          });
        }
      })
    }

    //修改计划
    function update_submitPlanInfo() {
      let f_sysname = $("#update_f_planName").val();//计划名称
      let f_nickname = $("#update_f_planNickName").val();//计划别名
      let f_planType = $("#update_f_planType").val();//替代日(0:是;1:否)
      let f_startday = $("#update_f_startDate").val();//开始日期
      let f_startime = $("#update_f_starTime").val();//开始时间
      let f_enday = $("#update_f_endDate").val();//结束日期
      let f_endtime = $("#update_f_endTime").val();//结束时间
      let f_active = $("#update_f_active").val();//是否使能(默认为是:1)
      let f_modeInfoId = $("#update_f_modeInfo").val();//模式
      let f_sceneInfoId = $("#update_f_sceneInfo").val();//场景
      let f_executionWay = $('input[name="update_f_executionWay"]:checked').val();//执行方式(0:按天执行;1:持续执行)
      let fWeekmask = $("#update_f_weekmask_7").val() + $("#update_f_weekmask_6").val() + $("#update_f_weekmask_5").val() + $("#update_f_weekmask_4").val() +
          $("#update_f_weekmask_3").val() + $("#update_f_weekmask_2").val() + $("#update_f_weekmask_1").val();

      if (f_sysname == '' || f_sysname == null) {
        swal({
          title: '名称不能为空!',// 展示的标题
          text: "",// 内容
          type: "warning",
          showCloseButton: false, // 展示关闭按钮
          allowOutsideClick: false,
          showConfirmButton: false,
          timer: 1000
        });
        return;
      }
      if (f_nickname == '' || f_nickname == null) {
        swal({
          title: '别名不能为空!',// 展示的标题
          text: "",// 内容
          type: "warning",
          showCloseButton: false, // 展示关闭按钮
          allowOutsideClick: false,
          showConfirmButton: false,
          timer: 1000
        });
        return;
      }
      if (f_startday == '' || f_startday == null) {
        swal({
          title: '开始日期不能为空!',// 展示的标题
          text: "",// 内容
          type: "warning",
          showCloseButton: false, // 展示关闭按钮
          allowOutsideClick: false,
          showConfirmButton: false,
          timer: 1000
        });
        return;
      }
      if (f_startime == '' || f_startime == null) {
        swal({
          title: '开始时间不能为空!',// 展示的标题
          text: "",// 内容
          type: "warning",
          showCloseButton: false, // 展示关闭按钮
          allowOutsideClick: false,
          showConfirmButton: false,
          timer: 1000
        });
        return;
      }
      if (f_enday == '' || f_enday == null) {
        swal({
          title: '结束日期不能为空!',// 展示的标题
          text: "",// 内容
          type: "warning",
          showCloseButton: false, // 展示关闭按钮
          allowOutsideClick: false,
          showConfirmButton: false,
          timer: 1000
        });
        return;
      }
      if (f_endtime == '' || f_endtime == null) {
        swal({
          title: '结束时间不能为空!',// 展示的标题
          text: "",// 内容
          type: "warning",
          showCloseButton: false, // 展示关闭按钮
          allowOutsideClick: false,
          showConfirmButton: false,
          timer: 1000
        });
        return;
      }
      if (f_modeInfoId == '' || f_modeInfoId == null) {
        swal({
          title: '模式不能为空!',// 展示的标题
          text: "",// 内容
          type: "warning",
          showCloseButton: false, // 展示关闭按钮
          allowOutsideClick: false,
          showConfirmButton: false,
          timer: 1000
        });
        return;
      }
      if (f_sceneInfoId == '' || f_sceneInfoId == null) {
        swal({
          title: '场景不能为空!',// 展示的标题
          text: "",// 内容
          type: "warning",
          showCloseButton: false, // 展示关闭按钮
          allowOutsideClick: false,
          showConfirmButton: false,
          timer: 1000
        });
        return;
      }
      $.ajax({
        url: "${ctx}/view/basedatamanage/eqmanage/updatePlanInfo",
        type: "post",
        dataType: "json",
        async: false,
        contentType: 'application/json; charset=UTF-8',
        data: JSON.stringify({
          f_id: f_id,
          f_sysname: f_sysname,
          f_nickname: f_nickname,
          f_planType: f_planType,
          f_startday: f_startday,
          f_startime: f_startime,
          f_enday: f_enday,
          f_endtime: f_endtime,
          f_weekmask: fWeekmask,
          f_planId: nodeId,
          f_active: f_active,
          f_modeInfoId: f_modeInfoId,
          f_sceneInfoId: f_sceneInfoId,
          f_scenetype: sceneType,
          f_execut: f_executionWay,
          //f_taskId:$("#f_timeTaskId").val(),
        }),
        success: function (result) {
          if (result.status == '1') {
            swal({
              title: result.msg,
              text: "",// 内容
              type: "success",
              showCloseButton: false, // 展示关闭按钮
              allowOutsideClick: false,
              confirmButtonColor: "#1ab394",
              showConfirmButton: false,
              timer: 2000
            });
            BooAdd_or_Edit = true;
            queryTableParam(nodeId);
            $('#updatePlanConfig').modal('hide');
            // $("#isynchro").removeAttr("hidden");
            // $("#notynchro").attr("hidden");
          } else {
            swal({
              title: result.msg,
              text: "",// 内容
              type: "warning",
              showCloseButton: false, // 展示关闭按钮
              allowOutsideClick: false,
              confirmButtonColor: "#1ab394",
              showConfirmButton: false,
              timer: 2000
            });
            // $("#isynchro").attr("hidden");
            // $("#notynchro").removeAttr("hidden");
          }

        },
        error: function () {
          swal({
            title: "控制计划修改失败",
            text: "",// 内容
            type: "warning",
            showCloseButton: false, // 展示关闭按钮
            allowOutsideClick: false,
            confirmButtonColor: "#1ab394",
            showConfirmButton: false,
            timer: 2000
          });
        }
      })
    }

    //添加定时任务
    function submitTimeTaskInfo() {
      var f_tasktype = $("#f_tasktype").val();//任务类型
      let f_specificvalue = $("#f_specificvalue").val();//具体值
      let planTimeConfig = $("#planTimeConfig").val();//cron表达式
      if (f_tasktype == '' || f_tasktype == null) {
        swal({
          title: '请选择任务类型!',// 展示的标题
          text: "",// 内容
          type: "warning",
          showCloseButton: false, // 展示关闭按钮
          allowOutsideClick: false,
          showConfirmButton: false,
          timer: 1000
        });
        return;
      }
      if (f_tasktype == "0") {//变化量
        if (f_specificvalue == '' || f_specificvalue == null) {
          swal({
            title: '请填写具体值!',// 展示的标题
            text: "",// 内容
            type: "warning",
            showCloseButton: false, // 展示关闭按钮
            allowOutsideClick: false,
            showConfirmButton: false,
            timer: 1000
          });
          return;
        }
      } else {//时间
        if (planTimeConfig == '' || planTimeConfig == null) {
          swal({
            title: '请填写cron表达式!',// 展示的标题
            text: "",// 内容
            type: "warning",
            showCloseButton: false, // 展示关闭按钮
            allowOutsideClick: false,
            showConfirmButton: false,
            timer: 1000
          });
          return;
        }
      }
      let planTaskName = $("#planTaskName").val();      //计划名称
      if (planTaskName == '' || planTaskName == null) {
        swal({
          title: '请填写计划名称!',// 展示的标题
          text: "",// 内容
          type: "warning",
          showCloseButton: false, // 展示关闭按钮
          allowOutsideClick: false,
          showConfirmButton: false,
          timer: 1000
        });
        return;
      }
      let planTaskNickname = $("#planTaskNickname").val();//计划别名
      if (planTaskNickname == '' || planTaskNickname == null) {
        swal({
          title: '请填写计划别名!',// 展示的标题
          text: "",// 内容
          type: "warning",
          showCloseButton: false, // 展示关闭按钮
          allowOutsideClick: false,
          showConfirmButton: false,
          timer: 1000
        });
        return;
      }
      let f_timename = $("#f_timename").val();//定时任务名称
      if (f_timename == '' || f_timename == null) {
        swal({
          title: '请填写定时任务名称!',// 展示的标题
          text: "",// 内容
          type: "warning",
          showCloseButton: false, // 展示关闭按钮
          allowOutsideClick: false,
          showConfirmButton: false,
          timer: 1000
        });
        return;
      }

      let f_sceneTaskInfo = $("#f_sceneTaskInfo").val();//场景名称
      if (f_sceneTaskInfo == '' || f_sceneTaskInfo == null) {
        swal({
          title: '请选择场景名称!',// 展示的标题
          text: "",// 内容
          type: "warning",
          showCloseButton: false, // 展示关闭按钮
          allowOutsideClick: false,
          showConfirmButton: false,
          timer: 1000
        });
        return;
      }
      let f_modeTaskInfo = $("#f_modeTaskInfo").val();//模式名称
      if (f_modeTaskInfo == '' || f_modeTaskInfo == null) {
        swal({
          title: '请选择模式名称!',// 展示的标题
          text: "",// 内容
          type: "warning",
          showCloseButton: false, // 展示关闭按钮
          allowOutsideClick: false,
          showConfirmButton: false,
          timer: 1000
        });
        return;
      }

      $.ajax({
        url: "${ctx}/view/basedatamanage/eqmanage/insertTimeTaskInfomation",
        type: "post",
        dataType: "json",
        async: false,
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify({
          planTaskName: planTaskName,      //计划名称
          planTaskNickname: planTaskNickname,  //计划昵称
          f_timename: f_timename,        //定时任务名称
          f_tasktype: f_tasktype,        //任务类型
          f_specificvalue: f_specificvalue,   //具体值(变化量的值)
          f_sceneTaskInfo: f_sceneTaskInfo,   //场景名称
          f_modeTaskInfo: f_modeTaskInfo,    //模式名称
          planTimeConfig: planTimeConfig,    //cron表达式
          f_scenetype: sceneType,         //场景类型
          planId: nodeId,            //计划ID
          f_invoke: "1"                //立即执行
        }),
        success: function (result) {

          if (result.status == "1") {
            let map = result.map;
            let planId = map.planId;
            let taskId = map.taskId;

            if (f_tasktype == "1") {//证明是时间类型
              executeTimeTaskInfo(planId, taskId);
            }
            BooAdd_or_Edit = true;
            queryCollectTableParam(nodeId);
            $('#addTimeTaskConfig').modal('hide');
          } else {
            swal({
              title: result.msg,
              text: "",// 内容
              type: "error",
              showCloseButton: false, // 展示关闭按钮
              allowOutsideClick: false,
              confirmButtonColor: "#1ab394",
              showConfirmButton: false,
              timer: 2000
            });
          }
        },
        error: function () {
          swal({
            title: "定时任务添加失败",
            text: "",// 内容
            type: "error",
            showCloseButton: false, // 展示关闭按钮
            allowOutsideClick: false,
            confirmButtonColor: "#1ab394",
            showConfirmButton: false,
            timer: 2000
          });
          $('#addTimeTaskConfig').modal('hide');
        }
      })
    }

    //修改定时任务
    function submitUpdateTimeTaskInfo() {

      let updatef_tasktype = $("#updatef_tasktype").val();//任务类型
      let updatef_specificvalue = $("#updatef_specificvalue").val();//具体值
      let updateplanTimeConfig = $("#updateplanTimeConfig").val();//cron表达式
      if (updatef_tasktype == '' || updatef_tasktype == null) {
        swal({
          title: '请选择任务类型!',// 展示的标题
          text: "",// 内容
          type: "warning",
          showCloseButton: false, // 展示关闭按钮
          allowOutsideClick: false,
          showConfirmButton: false,
          timer: 1000
        });
        return;
      }
      if (updatef_tasktype == "0") {//变化量
        if (updatef_specificvalue == '' || updatef_specificvalue == null) {
          swal({
            title: '请填写具体值!',// 展示的标题
            text: "",// 内容
            type: "warning",
            showCloseButton: false, // 展示关闭按钮
            allowOutsideClick: false,
            showConfirmButton: false,
            timer: 1000
          });
          return;
        }
      } else {//时间
        if (updateplanTimeConfig == '' || updateplanTimeConfig == null) {
          swal({
            title: '请填写cron表达式!',// 展示的标题
            text: "",// 内容
            type: "warning",
            showCloseButton: false, // 展示关闭按钮
            allowOutsideClick: false,
            showConfirmButton: false,
            timer: 1000
          });
          return;
        }
      }
      let updateplanTaskName = $("#updateplanTaskName").val();      //计划名称
      if (updateplanTaskName == '' || updateplanTaskName == null) {
        swal({
          title: '请填写计划名称!',// 展示的标题
          text: "",// 内容
          type: "warning",
          showCloseButton: false, // 展示关闭按钮
          allowOutsideClick: false,
          showConfirmButton: false,
          timer: 1000
        });
        return;
      }
      let updateplanTaskNickname = $("#updateplanTaskNickname").val();//计划别名
      if (updateplanTaskNickname == '' || updateplanTaskNickname == null) {
        swal({
          title: '请填写计划别名!',// 展示的标题
          text: "",// 内容
          type: "warning",
          showCloseButton: false, // 展示关闭按钮
          allowOutsideClick: false,
          showConfirmButton: false,
          timer: 1000
        });
        return;
      }
      let updatef_timename = $("#updatef_timename").val();//定时任务名称
      if (updatef_timename == '' || updatef_timename == null) {
        swal({
          title: '请填写定时任务名称!',// 展示的标题
          text: "",// 内容
          type: "warning",
          showCloseButton: false, // 展示关闭按钮
          allowOutsideClick: false,
          showConfirmButton: false,
          timer: 1000
        });
        return;
      }

      let updatef_sceneTaskInfo = $("#updatef_sceneTaskInfo").val();//场景名称
      if (updatef_sceneTaskInfo == '' || updatef_sceneTaskInfo == null) {
        swal({
          title: '请选择场景名称!',// 展示的标题
          text: "",// 内容
          type: "warning",
          showCloseButton: false, // 展示关闭按钮
          allowOutsideClick: false,
          showConfirmButton: false,
          timer: 1000
        });
        return;
      }
      let updatef_modeTaskInfo = $("#updatef_modeTaskInfo").val();//模式名称
      if (updatef_modeTaskInfo == '' || updatef_modeTaskInfo == null) {
        swal({
          title: '请选择模式名称!',// 展示的标题
          text: "",// 内容
          type: "warning",
          showCloseButton: false, // 展示关闭按钮
          allowOutsideClick: false,
          showConfirmButton: false,
          timer: 1000
        });
        return;
      }
      $.ajax({
        url: "${ctx}/view/basedatamanage/eqmanage/updateTimeTaskInfomation",
        type: "post",
        dataType: "json",
        async: false,
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify({
          f_id: f_id,//计划的id
          planTaskName: updateplanTaskName,      //计划名称
          planTaskNickname: updateplanTaskNickname,  //计划昵称
          f_timename: updatef_timename,        //定时任务名称
          f_tasktype: updatef_tasktype,        //任务类型
          f_specificvalue: updatef_specificvalue,   //具体值(变化量的值)
          f_sceneTaskInfo: updatef_sceneTaskInfo,   //场景名称
          f_modeTaskInfo: updatef_modeTaskInfo,    //模式名称
          planTimeConfig: updateplanTimeConfig,    //cron表达式
          f_scenetype: sceneType,               //场景类型
          planId: nodeId,                  //计划ID
          f_jobId: f_jobId                  //定时任务调度表的任务ID
        }),
        success: function (result) {
          if (result.status == "1") {
            swal({
              title: "定时任务修改成功",
              text: "",// 内容
              type: "success",
              showCloseButton: false, // 展示关闭按钮
              allowOutsideClick: false,
              confirmButtonColor: "#1ab394",
              showConfirmButton: false,
              timer: 2000
            });
            BooAdd_or_Edit = true;
            queryCollectTableParam(nodeId);
            $('#updateTimeTaskConfig').modal('hide');
          } else {
            swal({
              title: result.msg,
              text: "",// 内容
              type: "error",
              showCloseButton: false, // 展示关闭按钮
              allowOutsideClick: false,
              confirmButtonColor: "#1ab394",
              showConfirmButton: false,
              timer: 2000
            });
          }
        },
        error: function () {
          swal({
            title: "定时任务添加失败",
            text: "",// 内容
            type: "error",
            showCloseButton: false, // 展示关闭按钮
            allowOutsideClick: false,
            confirmButtonColor: "#1ab394",
            showConfirmButton: false,
            timer: 2000
          });
          $('#updateTimeTaskConfig').modal('hide');
        }
      })
    }

    //在quartz添加时间类型的定时任务
    function executeTimeTaskInfo(planId, taskId) {

      $.ajax({
        url: "${ctx}/quartz/insertTimeTaskInfomation",
        type: "post",
        dataType: "json",
        async: false,
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify({
          planId: planId,
          taskId: taskId
        }),
        success: function (result) {

          if (result.status == "1") {
            var jobId = result.data;
            $.ajax({
              url: "${ctx}/view/basedatamanage/eqmanage/insertTimeTaskJobId",
              type: "post",
              dataType: "json",
              async: false,
              contentType: "application/json; charset=utf-8",
              data: JSON.stringify({
                jobId: jobId,
                taskId: taskId
              }),
              success: function (result) {
                swal({
                  title: "定时任务添加成功",
                  text: "",// 内容
                  type: "success",
                  showCloseButton: false, // 展示关闭按钮
                  allowOutsideClick: false,
                  confirmButtonColor: "#1ab394",
                  showConfirmButton: false,
                  timer: 2000
                });
              },
              error: function () {
                swal({
                  title: "定时任务添加失败",
                  text: "",// 内容
                  type: "error",
                  showCloseButton: false, // 展示关闭按钮
                  allowOutsideClick: false,
                  confirmButtonColor: "#1ab394",
                  showConfirmButton: false,
                  timer: 2000
                });
              }
            })
          } else {
            swal({
              title: "定时任务添加失败",
              text: "",// 内容
              type: "error",
              showCloseButton: false, // 展示关闭按钮
              allowOutsideClick: false,
              confirmButtonColor: "#1ab394",
              showConfirmButton: false,
              timer: 2000
            });
          }
          // queryCollectTableParam(nodeId);
          $('#addTimeTaskConfig').modal('hide');
        },
        error: function () {
          swal({
            title: "定时任务添加失败",
            text: "",// 内容
            type: "error",
            showCloseButton: false, // 展示关闭按钮
            allowOutsideClick: false,
            confirmButtonColor: "#1ab394",
            showConfirmButton: false,
            timer: 2000
          });
          $('#addTimeTaskConfig').modal('hide');
        }
      })
    }

    //获取下位机计划信息
    function contrastPlanValue(data) {
      $.ajax({
        type: "post",
        dataType: "json",
        async: false,
        url: "${ctx}/view/basedatamanage/eqmanage/selectPlanInfomationByPlanId",
        data: {
          sceneId: data.sceneID,
          modeID: data.modeID
        },
        success: function (result) {
          var data = result.map;
          $("#contrast_sceneUnderName").val(data.sceneName); //场景名称
          $("#contrast_modeUnderName").val(data.modeName); //模式名称
        }
      })

      $("#contrast_planUnderInfoId").val(data.id); //计划ID

      $("#contrast_planUnderName").val(data.name); //计划名称

      $("#contrast_planUnderNickname").val(data.alias); //计划名称

      if (data.active == 1) {
        $("#contrast_planUnderEnable").val("启用"); //是否使能
      } else {
        $("#contrast_planUnderEnable").val("不启用"); //是否使能
      }

      $("#contrast_sceneUnderId").val(data.sceneID); //场景ID

      $("#contrast_modeUnderId").val(data.modeID); //模式ID

      let startTimeHour;

      let startTimeMinute;

      let startTimeSecond;

      let endTimeHour;

      let endTimeMinute;

      let endTimeSecond;

      if (data.endTimeHour == "0") {
        endTimeHour = '00';
      } else {
        endTimeHour = data.endTimeHour;
        endTimeHour = addZero(endTimeHour);
      }

      if (data.endTimeMinute == "0") {
        endTimeMinute = '00';
      } else {
        endTimeMinute = data.endTimeMinute;
        endTimeMinute = addZero(endTimeMinute);
      }

      if (data.endTimeSecond == "0") {
        endTimeSecond = '00';
      } else {
        endTimeSecond = data.endTimeSecond;
        endTimeSecond = addZero(endTimeSecond);
      }


      if (data.startTimeHour == "0") {
        startTimeHour = '00';
      } else {
        startTimeHour = data.startTimeHour;
        startTimeHour = addZero(startTimeHour);
      }

      if (data.startTimeMinute == "0") {
        startTimeMinute = '00';
      } else {
        startTimeMinute = data.startTimeMinute;
        startTimeMinute = addZero(startTimeMinute);
      }

      if (data.startTimeSecond == "0") {
        startTimeSecond = '00';
      } else {
        startTimeSecond = data.startTimeSecond;
        startTimeSecond = addZero(startTimeSecond);
      }

      $("#contrast_planUnderStartDate").val("20" + data.startDateYear + "-" + addZero(data.startDateMonth) + "-" + addZero(data.startDateDay)); //下位机开始日期

      $("#contrast_planUnderStartTime").val(startTimeHour + ":" + startTimeMinute + ":" + startTimeSecond); //下位机开始时间

      $("#contrast_planUnderEndDate").val("20" + data.endDateYear + "-" + addZero(data.endDateMonth) + "-" + addZero(data.endDateDay)); //下位机结束日期

      $("#contrast_planUnderEndTime").val(endTimeHour + ":" + endTimeMinute + ":" + endTimeSecond); //下位机结束时间

    }

    function addZero(num) {
      if (parseInt(num) < 10) {
        num = '0' + num
      }
      return num
    }

    return {
      //点击复选框时改变value
      changeVal: function (event) {
        if (event.value == "0") {
          $("#" + event.id).val("1");
        } else {
          $("#" + event.id).val("0");
        }
      },
      //添加
      insertPlanInfomation: function () {
        showPlanInsertInfo();
      },
      insertTaskInfomation: function () {
        showTaskInsertInfo();
      },
      getModeValue: function (value) {
        getInitModeInfo(value,function () {

        });
      },
      contrastPlanValue: function (data) {
        contrastPlanValue(data);
      },
      //添加计划
      submitPlanInfo: function () {
        submitPlanInfo();
      },
      //修改计划
      update_submitPlanInfo: function () {
        update_submitPlanInfo();
      },
      submitTimeTaskInfo: function () {
        submitTimeTaskInfo();
      },
      submitUpdateTimeTaskInfo: function () {
        submitUpdateTimeTaskInfo();
      },
      //获取表达式
      cron: function (cron) {

        if (BooaddTimeTaskConfig) {
          $("#planTimeConfig").val(cron);
        } else {
          $("#updateplanTimeConfig").val(cron);
        }
      },
      getSceneInfoAndModeInfo:function (data) {
        getSceneInfoAndModeInfo(data);
      }
    };

  })(jQuery, window, document);

</script>
