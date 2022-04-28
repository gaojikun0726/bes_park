<style type="text/css">
    .leftTree {
        float: left;
        width: 20% !important;
        /* margin-left: -10px; */
        position: relative;
        box-sizing: border-box;
        height: 96%;
        border-right: solid 1px rgb(54, 108, 144);
        display: block;
    }

    .sceneTop {
        height: 4%;
        display: block;
        border-bottom: solid 1px rgb(54, 108, 144);
        display: block;
    }

    .centerContent {
        float: left;
        width: 80%;
        position: relative;
        /*margin-top: 4%;*/
        height: 93%;
        display: none;
    }

    .contentTop1 {
        white-space: nowrap;
        overflow: hidden;
        width: 100%;
        height: 25%;
        border-bottom: solid 1px rgb(54, 108, 144);
    }

    .contentBottom {
        white-space: nowrap;
        overflow: hidden;
        width: 100%;
        height: 518px;
    }

    .tableSceneContent {
        margin-top: 25px;
        width: 90%;
        height: 92%;
        margin-left: 60px;
        float: left;
        /*background-color: #00264d;*/
        overflow-y: auto;
        overflow-x: hidden;
        position: relative;
    }

    .tableRight {
        margin-left: 40px;
        width: 10%;
        height: 160px;
        float: left;
        background-color: #00264d;
        border: solid 0px #ffffff;
    }
    /*样式前加上前缀，避免影响其他页面样式*/
    #sceneConfig_content .layui-table-cell {
        min-height: 30px;
        height: initial;
    }

    #sceneConfig_content .layui-table-cell,#sceneConfig_content .layui-table-tool-panel li {
        white-space: initial;
        /*为了让字母和数字也换行*/
        word-break: break-word;
    }

    #sceneConfig_content .layui-form-select dl {
        max-height: 200px;
    }

    .valueAndUnit {
        width: 100%;
        height: 200px;
    }

    .valueAndUnitClass {
        width: 100%;
        height: 200px;
        font-size: 20px;
        color: black;
    }

    #sceneConfig_content .layui-table-cell {
        overflow: visible;
    }

    #sceneConfig_content .layui-table-box {
        overflow: visible;
    }

    #sceneConfig_content .layui-table-body {
        overflow: visible;
    }
    /* 设置下拉框的高度与表格单元相同 */
    #sceneConfig_content td .layui-form-select{
        margin-left: -15px;
        margin-right: -15px;
    }
    .tab-pane active{
        height: 96%;
    }

    .ztree li a.curSelectedNode {
        background-color: #0d8ddb;
    }
</style>

<#--左侧树-->
<div class="leftarea information_left">
    <div class="information-model">
		<span class="tree_Subtitle">
			<i class="fa fa-share-alt" aria-hidden="true"></i>&nbsp;场景配置树>>>
		</span>
    </div>
    <div id="sceneTree" class="ztree"></div>
</div>

<#--点位树-->
<div class="modal fade" style="width:40%;margin-left:44.5%;margin-top: 4.4%" id="selectPointTree" tabindex="-1"
     role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
    <div class="modal-dialog" style="margin-top:6px;margin-right: 40px">
        <div class="modal-content" style="width: 350px;">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">请选择点位</h4>
            </div>
            <div class="modal-body" style="height: 500px">
                <#--设备类型树-->
                <div id="tree_selectPoinTree" class="Information_area ztree"></div>
            </div>
        </div>
    </div>
</div>

<div id="sceneConfig_content" class="information_right" style="display: none;">
    <div class="information-model">
			<span class="Subtitle">
				<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;场景配置列表>>>
			</span>
    </div>

    <div style="height: calc(95%);">
        <form role="form" class="form-horizontal" style="height: 100%">
            <div class="vertical-timeline-block contentTop1" style="height: 28%">
                <div class="form-group col-sm-6" style="margin-top: 20px">
                    <label class="col-sm-3 control-label">场景名称:</label>
                    <div class="col-sm-8">
                        <input type="text" id="f_sceneName" name="f_sceneName"
                               class="form-control" style="width: 300px">
                    </div>
                </div>

                <div class="form-group col-sm-6" style="margin-top: 20px">
                    <label class="col-sm-3 control-label">场景别名:</label>
                    <div class="col-sm-8">
                        <input type="text" id="f_sceneNickName" name="f_sceneNickName"
                               class="form-control" style="width: 300px">
                    </div>
                </div>

                <div class="form-group col-sm-6" style="margin-top: 20px">
                    <label class="col-sm-3 control-label">场景说明:</label>
                    <div class="col-sm-8">
                        <input type="text" id="f_sceneDiscrip" name="f_sceneDiscrip"
                               class="form-control" style="width: 300px" value="无">
                    </div>
                </div>

                <div class="form-group col-sm-6" style="margin-top: 20px">
                    <label class="col-sm-3 control-label">是否使能:</label>
                    <div class="col-sm-8">
                        <select id="f_sceneActive" name="f_sceneActive" style="width: 300px" class="form-control">
                            <option value="1">是</option>
                            <option value="0">否</option>
                        </select>
                    </div>
                </div>

                <div style="margin-top: 156px;width: 12%;height: 20px;font-size: 15px;margin-left: 40%">
                    <button class="layui-btn"  type="button" id=""
                            onclick="basedatamanage_eqmanage_sceneConfig.saveSceneInfomation()">保存场景信息
                    </button>
                </div>
            </div>


            <div class="vertical-timeline-block contentBottom" id="underTable">

                <div style="margin-top: 0px;width: 12%;height: 20px;font-size: 15px;margin-left: 60px;">
                    <button class="layui-btn" lay-event="add" type="button" id="addInstruction"
                            onclick="basedatamanage_eqmanage_sceneConfig.getAddInstruction()">添加指令
                    </button>
                    <button class="layui-btn" lay-event="" type="button" id="controlBtn"
                            onclick="basedatamanage_eqmanage_sceneConfig.insertInstruction()">添加模式
                    </button>

                    <button class="layui-btn" lay-event="" type="button" id="collectBtn"
                            onclick="basedatamanage_eqmanage_sceneConfig.insertCollectInstruction()">添加模式
                    </button>
                </div>


                <div class="tableSceneContent" id="insertTableParam">
                    <table class="layui-hide" id="sceneTable" lay-filter="save"></table>
                    <script type="text/html" id="barDemo">
                        <a class="layui-btn layui-btn-xs layui-btn-primary" style="width: 50px" lay-event="saveValue">保存</a>
                        <#-- <a class="layui-btn layui-btn-xs" style="width: 50px" lay-event="synchroValue" id="isynchro" hidden>已同步</a>-->
                        <a class="layui-btn layui-btn-xs layui-btn-warm" style="width: 50px" lay-event="synchroValue" id="sceneNotynchro">同步</a>
                        <a class="layui-btn layui-btn-normal layui-btn-xs" style="width: 50px" lay-event="contrastValue">对比</a>
                        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="deleteValue" style="width: 50px">删除</a>
                    </script>
                </div>


                <div class="tableSceneContent" id="insertCollectTableParam">
                    <table class="layui-hide" id="collectSceneTable" lay-filter="collectSave"></table>
                    <script type="text/html" id="collectBarDemo">
                        <a class="layui-btn layui-btn-xs layui-btn-primary" style="width: 50px" lay-event="saveValue">保存</a>
                        {{#  if(d.pointCount == null){ }}
                        <a class="layui-btn layui-btn-normal" style="width: 100px" lay-event="addMode" type="">添加指令</a>
                        {{#  } }}
                        {{#  if(d.pointCount > 0){ }}
                        <a class="layui-btn layui-btn-xs" style="width: 100px" lay-event="updateMode" type="">修改指令</a>
                        {{#  } }}

                        <#-- <a class="layui-btn layui-btn-xs layui-btn-warm" style="width: 50px" lay-event="synchroValue" id="collectnotynchro">未同步</a>-->
                        <#-- <a class="layui-btn layui-btn-primary layui-btn-xs" style="width: 50px" lay-event="contrastValue">对比</a>-->
                        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="deleteValue" style="width: 50px">删除</a>
                    </script>
                </div>

            </div>
        </form>
    </div>

</div>

<#--控制场景添加指令点位-->
<div class="modal fade" style="width: 600px;margin-left: 20%" id="addTableData" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content" style="height: 100px;width: 600px;margin-top: 15%">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button" id="clearPoint">×</button>
                <h4 class="modal-title addIcon">添加指令点位</h4>
            </div>

            <div class="modal-body">

                <form role="form" id="formAddTableData" name="formAddTableData" class="form-horizontal">

                    <div class="form-group" style="margin-top: 20px;">
                        <label class="col-sm-2 control-label">点位<span class="text-danger"></span></label>
                        <div class="col-sm-8">

                            <textarea  class='form-control' id="f_newPointName" name="f_newPointName" placeholder="请选择点位" />

                        </div>
                    </div>

                    <div class="form-group m-t-sm" style="margin-top: 50px;margin-left: 110px;">
                        <div class="col-sm-6 col-sm-push-2 display_flex">
                            <button class="btn btn-md btn-warning" style="height: 31px;margin-right: 12px;" type="button" id="submitAddTableData_reduce">
                                <strong>回退</strong></button>
                            <button class="btn btn-md btn-primary" type="button" id="submitAddTableData">
                                <strong>确定</strong></button>
                            <button class="btn btn-white m-l-sm" type="button" id="cancelPoint" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<#--采集场景添加指令点位-->
<div class="modal fade" style="width: 600px;margin-left: 20%" id="addCollectionTableData" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content" style="height: 100px;width: 600px;margin-top: 15%">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button" id="AddCollectionclearPoint">×</button>
                <h4 class="modal-title addIcon">添加指令点位</h4>
            </div>

            <div class="modal-body">

                <form role="form" id="formAddCollectionTableData" name="formAddCollectionTableData" class="form-horizontal">

                    <div class="form-group" style="margin-top: 20px;">
                        <label class="col-sm-2 control-label">点位<span class="text-danger"></span></label>
                        <div class="col-sm-8">
                            <textarea  class='form-control' id="addCollectionPointName" name="addCollectionPointName" placeholder="请选择点位" />
                        </div>
                    </div>

                    <div class="form-group m-t-sm" style="margin-top: 50px;margin-left: 110px;">
                        <div class="col-sm-6 col-sm-push-2 display_flex">
                            <button class="btn btn-md btn-warning" style="height: 31px;margin-right: 12px;" type="button" id="submitAddCollectionTableData_reduce">
                                <strong>回退</strong></button>
                            <button class="btn btn-md btn-primary" type="button" id="submitAddCollectionTableData">
                                <strong>确定</strong></button>
                            <button class="btn btn-white m-l-sm" type="button" id="addCancelPoint" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<#--修改采集场景table内数据的模态框-->
<div class="modal fade" style="width: 600px;margin-left: 20%" id="updateCollectionTableData" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content" style="height: 100px;width: 600px;margin-top: 15%">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button" id="updateCollectionClearPoint">×</button>
                <h4 class="modal-title addIcon">修改指令点位</h4>
            </div>

            <div class="modal-body">

                <form role="form" id="formupdateCollectionTableData" name="formupdateCollectionTableData" class="form-horizontal">

                    <div class="form-group" style="margin-top: 20px;">
                        <label class="col-sm-2 control-label">点位<span class="text-danger"></span></label>
                        <div class="col-sm-8">
                            <textarea  class='form-control' id="updateCollectionPointName" name="updateCollectionPointName" placeholder="请选择点位" />
                        </div>
                    </div>

                    <div class="form-group m-t-sm" style="margin-top: 50px;margin-left: 110px;">
                        <div class="col-sm-6 col-sm-push-2 display_flex">
                            <button class="btn btn-md btn-warning" style="height: 31px;margin-right: 12px;" type="button" id="submitUpdateCollectionTableData_reduce">
                                <strong>回退</strong></button>
                            <button class="btn btn-md btn-primary" type="button" id="submitUpdateCollectionTableData">
                                <strong>确定</strong></button>
                            <button class="btn btn-white m-l-sm" type="button" id="updateCancelPoint" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<link href="${ctx}/static/ztree/css/metroStyle/metroStyle.css" rel="stylesheet">

<script src="${ctx}/static/layui/lay/modules/layer.js" type="text/javascript"></script>

<script type="text/html" id="serial">
    {{d.LAY_TABLE_INDEX+1}}
</script>


<!----场景详情信息对比--->
<div class="modal fade" id="contrastModeValue" style="margin-left:-16%" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content"  style="width:900px;">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title">上/下位机场景详情信息比对</h4>
            </div>

            <div class="modal-body" style="height:550px;margin-button:10px;">
                <div style="float:left;width:52%"><button class="btn btn-md" style="cursor:default"><span>上位机</span></button></div>
                <div style="float:left;width:40%"><button class="btn btn-md" style="cursor:default"><span>下位机</span></button></div>


                <!----上位机信息开始--->
                <div class="notIncludeCss" style="width:400px;height:450px;overflow-y:auto;overflow-x: hidden;">
                    <form class="form-horizontal">

                        <div class="form-group">
                            <label class="col-sm-3 control-label" style="width:40%" for="">场景ID<span class="text-comparecoll">:</span></label>
                            <div class="col-sm-8" style="width:50%">
                                <input type="text" id="contrast_sceneInfoId" name="contrast_sceneInfoId" class="form-control" readonly="readonly">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label" style="width:40%" for="">场景名称<span class="text-comparecoll">:</span></label>
                            <div class="col-sm-8" style="width:50%">
                                <input type="text" id="contrast_sceneName" name="contrast_sceneName" class="form-control" readonly="readonly">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label" style="width:40%" for="">场景别名<span class="text-comparecoll">:</span></label>
                            <div class="col-sm-8" style="width:50%">
                                <input type="text" id="contrast_sceneNickname" name="contrast_sceneNickname" class="form-control" readonly="readonly">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label" style="width:40%" for="">是否使能<span class="text-comparecoll">:</span></label>
                            <div class="col-sm-8" style="width:50%">
                                <input type="text" id="contrast_modeEnable" name="contrast_modeEnable" class="form-control" readonly="readonly">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label" style="width:40%" for="">模式ID<span class="text-comparecoll">:</span></label>
                            <div class="col-sm-8" style="width:50%">
                                <input type="text" id="contrast_modeId" name="contrast_modeId" class="form-control" readonly="readonly">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label" style="width:40%" for="">模式名称<span class="text-comparecoll">:</span></label>
                            <div class="col-sm-8" style="width:50%">
                                <input type="text" id="contrast_modeName" name="contrast_modeName" class="form-control" readonly="readonly">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label" style="width:40%" for="">点位ID<span class="text-comparecoll">:</span></label>
                            <div class="col-sm-8" style="width:50%">
                                <input type="text" id="contrast_pointId" name="contrast_pointId" class="form-control" readonly="readonly">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label" style="width:40%" for="">值<span class="text-comparecoll">:</span></label>
                            <div class="col-sm-8" style="width:50%">
                                <input type="text" id="contrast_pointValue" name="contrast_pointValue" class="form-control" readonly="readonly">
                            </div>
                        </div>
                    </form>
                </div>
                <!----上位机信息结束--->


                <!----下位机信息开始--->
                <div class="includeCss" style="width:400px;height:450px;margin:5px 0 0 36px; overflow-y:auto;overflow-x: hidden;">
                    <form class="form-horizontal">

                        <div class="form-group">
                            <label class="col-sm-3 control-label" style="width:40%" for="">场景ID<span class="text-comparecoll">:</span></label>
                            <div class="col-sm-8" style="width:50%">
                                <input type="text" id="contrast_underSceneInfoId" name="contrast_underSceneInfoId" class="form-control" readonly="readonly">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label" style="width:40%" for="">场景名称<span class="text-comparecoll">:</span></label>
                            <div class="col-sm-8" style="width:50%">
                                <input type="text" id="contrast_underSceneName" name="contrast_underSceneName" class="form-control" readonly="readonly">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label" style="width:40%" for="">场景别名<span class="text-comparecoll">:</span></label>
                            <div class="col-sm-8" style="width:50%">
                                <input type="text" id="contrast_underNickName" name="contrast_underNickName" class="form-control" readonly="readonly">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label" style="width:40%" for="">是否使能<span class="text-comparecoll">:</span></label>
                            <div class="col-sm-8" style="width:50%">
                                <input type="text" id="contrast_underActive" name="contrast_underActive" class="form-control" readonly="readonly">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label" style="width:40%" for="">模式ID<span class="text-comparecoll">:</span></label>
                            <div class="col-sm-8" style="width:50%">
                                <input type="text" id="contrast_underModeId" name="contrast_underModeId" class="form-control" readonly="readonly">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label" style="width:40%" for="">模式名称<span class="text-comparecoll">:</span></label>
                            <div class="col-sm-8" style="width:50%">
                                <input type="text" id="contrast_underModeName" name="contrast_underModeName" class="form-control" readonly="readonly">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label" style="width:40%" for="">点位ID<span class="text-comparecoll">:</span></label>
                            <div class="col-sm-8" style="width:50%">
                                <input type="text" id="contrast_underPointId" name="contrast_underPointId" class="form-control" readonly="readonly">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label" style="width:40%" for="">值<span class="text-comparecoll">:</span></label>
                            <div class="col-sm-8" style="width:50%">
                                <input type="text" id="contrast_underPointValue" name="contrast_underPointValue" class="form-control" readonly="readonly">
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

<script type="text/javascript">
    ;
    var basedatamanage_eqmanage_sceneConfig = (function ($, window, document, undefined) {
        var _ctx = '${ctx}';
        var sceneOpTable;
        var tabCollectAcademeNotice;
        var sceneInfoId;
        var type;
        var arrName = []; //存储当前 选择的集合
        var arrNameAndNickName = [];//点位文本框中保存的点位集合,带上别名的
        var pointId = []; //点位ID
        var sceneList = []; //场景信息的List
        var modeList = []; //模式信息的List
        var valueUnitData = []; //值/单位信息
        var taskInfoValue = []; //选择完的定时任务数据

        var scenemodeId;//模式id
        var controlPointModel = false;//控制场景修改模式点位模态框是否展示
        var collectionAddPointModel = false;//采集场景模式添加模态框是否展示
        var collectionUpdatePointModel = false;//采集场景模式添加模态框是否展示

        var modeId = null;//模块id
        var DDC_LDCId = [];
        var BooPagination = false;//判断是否为初始页
        var BooAdd_or_Edit = false;//判断是否是添加或修改操作，如果是，则加载当前页面
        var cr = "1";//当前页数

        var sceneTableId = null;//控制场景展开的表格的id

        Array.prototype.push2 =function(){

            for(var i=0; i<arguments.length; i++){
                var ele = arguments[i];
                if(this.indexOf(ele) == -1){
                    this.push(ele);
                }
            }
        };

        $("#addTableData,#addCollectionTableData,#updateCollectionTableData").on('show.bs.modal', function(event) {

            //垂直居中显示
            $(this).css('display', 'block');
            $(this).css("overflow", "hidden"); // 防止出现滚动条，出现的话，你会把滚动条一起拖着走的
            document.getElementById("f_newPointName").style.height = 40 + "px";
            document.getElementById("addCollectionPointName").style.height = 40 + "px";
            document.getElementById("updateCollectionPointName").style.height = 40 + "px";
            document.getElementById("f_newPointName").style.width = 100 + "%";
            document.getElementById("addCollectionPointName").style.height = 100 + "%";
            document.getElementById("updateCollectionPointName").style.height = 100 + "%";
        });
        //关闭模态框清空表单值
        $("#addTableData").on('hidden.bs.modal',function(event) {
            $(this).find("input").val("");
            $(this).find("textarea").val("");
            controlPointModel = false;
            pointId.length = 0;//点位id
            arrName.length = 0;//点位名称
            DDC_LDCId.length = 0;
            arrNameAndNickName.length = 0;
        });

        $("#addCollectionTableData").on('hidden.bs.modal',function(event) {
            $(this).find("input").val("");
            $(this).find("textarea").val("");
            collectionAddPointModel = false;
            pointId.length = 0;//点位id
            arrName.length = 0;//点位名称
            arrNameAndNickName.length = 0;
        });

        $("#updateCollectionTableData").on('hidden.bs.modal',function(event) {
            $(this).find("input").val("");
            $(this).find("textarea").val("");
            collectionUpdatePointModel = false;
            pointId.length = 0;//点位id
            arrName.length = 0;//点位名称
            arrNameAndNickName.length = 0;
        });

        //加载树形结构
        $(function () {
            scene_tree();//初始化场景树

            let treeObj = $.fn.zTree.getZTreeObj("sceneTree");
            treeObj.expandAll(true);
            let node = treeObj.getNodeByParam("f_id", "2");
            treeObj.selectNode(node);

            // setTimeout(equip_tree,20000);
            equip_tree();//点位树
        })
        //左侧树
        function scene_tree() {
            $.ajax({
                type: "post",
                url: "${ctx}/view/basedatamanage/eqmanage/getSceneTree",
                dataType: "json",
                async: false,
                success: function (result) {

                    var status = result && result.status;

                    if (status !== '1') {
                        return;
                    }

                    var data = result.data;

                    if (!Array.isArray(data)) {
                        return;
                    }

                    var sceneTree = new Tree({
                        container: 'sceneTree',
                        idKey: 'f_id',
                        pIdKey: 'f_pId',
                        name: 'f_name',
                        f_type: 'f_type',
                        showCheckbox: true,
                        selected: false,
                        noRemoveBtn: true,
                        noEditBtn: true,
                        setting: {
                            view: {
                                showIcon: false,
                                // selectedMulti: true,
                                txtSelectedEnable: false,
                                fontCss: function (treeId, treeNode) {
                                    return (treeNode.highlight)
                                        ? {color: '#A60000', 'font-weight': 'bold'}
                                        : {color: '#8FE3F7',"font-family":"none","li.line-height":"30px", 'font-weight': 'normal'};
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
                                //beforeRename: beforeRename,
                                // onRemove: zTreeOnRemove,
                                onRename: onRename,
                                //onCheck:zTreeOnCheck(nodes)
                                setting: {
                                    data: {
                                        enable: false
                                    }
                                }
                            }
                        }
                    })

                    sceneTree.loadData(data);
                    // let controlNode =  sceneTree.tree.getNodesByParam("f_id",'2', null)[0];
                    // let collectionNode =  sceneTree.tree.getNodesByParam("f_id",'3', null)[0];
                    // $("#"+controlNode.tId+"_span").css("color",'red');
                    // $("#"+collectionNode.tId+"_span").css("color",'red');


                },
                error: function (nodeData) {
                    swal(nodeData.msg, "", "error");
                }
            })
        }

        //点位树初次加载
        function equip_tree() {
            $.ajax({
                type: "post",
                url: "${ctx}/view/basedatamanage/eqmanage/austere_devices_tree",
                dataType: "json",
                async: true,
                success: function (result) {
                    var status = result && result.status;

                    if (status !== '1') {
                        return;
                    }

                    var treeList = result.list || [];

                    var tree = new Tree({
                        container: 'tree_selectPoinTree',
                        idKey: 'f_sys_name',
                        pIdKey: 'f_psys_name',
                        name: 'f_nick_name',
                        setting: {
                            view: {
                                showIcon: false,
                                fontCss: function (treeId, treeNode) {
                                    return (treeNode.highlight)
                                        ? {color: '#A60000', 'font-weight': 'bold'}
                                        : {color: '#000000', 'font-weight': 'normal'};
                                }
                            },
                        },
                        callback: function (node) {
                            let DDCMassage = null;

                            if (node.f_type == "10" || node.f_type == "11" || node.f_type == "12" || node.f_type == "13" || node.f_type == "16") {


                                if (node.f_node_attribution == "1") {//楼控

                                    if (node.f_type == "16") {//虚点
                                        DDCMassage = (node.getParentNode()).getParentNode();
                                        if (DDCMassage.f_type == "2") {//DDC节点
                                            DDC_LDCId.push2(DDCMassage.f_id);
                                        }
                                    } else {//实点
                                        DDCMassage = (((node.getParentNode()).getParentNode()).getParentNode()).getParentNode();
                                        if (DDCMassage.f_type == "2") {//DDC节点
                                            DDC_LDCId.push2(DDCMassage.f_id);
                                        }
                                    }


                                } else {//照明
                                    if (node.f_type == "16") {//虚点
                                        DDCMassage = (node.getParentNode()).getParentNode();
                                        if (DDCMassage.f_type == "2") {//DDC节点
                                            DDC_LDCId.push2(DDCMassage.f_id);
                                        }
                                    } else {//实点
                                        DDCMassage = (node.getParentNode()).getParentNode();

                                        if (DDCMassage.f_type == "6") {//支线耦合器节点

                                            DDCMassage = DDCMassage.getParentNode();
                                            if (DDCMassage.f_type == "5") {//干线耦合器节点
                                                DDCMassage = DDCMassage.getParentNode();
                                                DDC_LDCId.push2(DDCMassage.f_id);
                                            } else {//Ldc节点
                                                DDC_LDCId.push2(DDCMassage.f_id);
                                            }

                                        }  else if (DDCMassage.f_type == "3") {//Ldc节点
                                            DDC_LDCId.push2(DDCMassage.f_id);
                                        }
                                    }
                                }

                                let i = pointId.indexOf(node.f_id);
                                if (i > -1) {//已添加
                                    return;
                                }

                                if (DDC_LDCId.length > 1) {
                                    swal({
                                        title: '请选择同一个控制器下的点位',// 展示的标题
                                        type: "warning",
                                        showCloseButton: false, // 展示关闭按钮
                                        allowOutsideClick: false,
                                        showConfirmButton: false,
                                        timer: 1000
                                    })
                                    DDC_LDCId.pop();
                                    return;
                                }

                                pointId.push(node.f_id);
                                arrName.push(node.f_sys_name_old);
                                arrNameAndNickName.push(node.f_sys_name_old + "(" + node.f_nick_name + ")");
                                if (collectionUpdatePointModel) {

                                    $('#updateCollectionPointName').val(arrNameAndNickName.join(' \n '));
                                    autoTextarea(document.getElementById("updateCollectionPointName"));

                                } else if (collectionAddPointModel){

                                    $('#addCollectionPointName').val(arrNameAndNickName.join(' \n '));
                                    autoTextarea(document.getElementById("addCollectionPointName"));

                                } else if (controlPointModel){

                                    $('#f_newPointName').val(arrNameAndNickName.join(' \n '));
                                    autoTextarea(document.getElementById("f_newPointName"));
                                }
                            } else {
                                swal({
                                    title: '请选择正确的点位',// 展示的标题
                                    type: "warning",
                                    showCloseButton: false, // 展示关闭按钮
                                    allowOutsideClick: false,
                                    showConfirmButton: false,
                                    timer: 1000
                                })
                                return;
                            }
                        }
                    })
                    tree.loadData(treeList);
                }
            })
        }

        //节点点击事件 将Id放全局变量里面
        function pointDosth(e, treeId, treeNode) {

            if(treeNode.level === 0) {
                $('#sceneConfig_content').hide();
            }else {
                $('#sceneConfig_content').show();
            }
            modeList.length = 0;
            sceneInfoId = treeNode.f_id;
            type = treeNode.f_type;
            BooPagination = true;
            queryTableParam(sceneInfoId);
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

        function filter(event, treeId, parentNode, childNodes) {
            if (!childNodes) return null;
            return childNodes;
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

        //新增节点
        function insertNode(treeId, treeNode) {
            var zTree = $.fn.zTree.getZTreeObj("sceneTree"); //获取树形图里面的数据
            var sObj = $("#" + treeNode.tId + "_span"); //获取节点信息
            if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0) return;
            var addStr = "<span class='button add' id='addBtn_" + treeNode.tId + "' title='新增一个场景' οnfοcus='this.blur();'></span>"; //定义添加按钮
            sObj.after(addStr); //加载添加按钮
            var btn = $("#addBtn_" + treeNode.tId);
            if (treeNode.f_type === "1") {
                // var newCount = 1;
                //绑定添加事件，并定义添加操作
                if (btn) btn.bind("click", function () {

                    const fname = "新建控制场景"; //新增节点的名字
                    const pId = "2";
                    $.ajax({
                        type: "post",
                        dataType: "json",
                        async: false,
                        url: "${ctx}/view/basedatamanage/eqmanage/insertContScene",
                        data: {
                            f_name: fname,
                            f_type: "1",
                            f_pId: pId
                        },
                        success: function (result) {
                            if (result.status === '1') {
                                var id = result.data;
                                zTree.addNodes(treeNode, {f_id: id, f_pId: pId, f_name: fname, f_type:"1"})
                            } else {
                                swal({
                                    title: "控制场景添加失败",
                                    text: "",// 内容
                                    type: "warning",
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
                                title: "控制场景节点添加失败",
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
                });
            } else if (treeNode.f_type === "2") {
                //绑定添加事件，并定义添加操作
                if (btn) btn.bind("click", function () {
                    const fname = "新建采集场景"; //新增节点的名字
                    const pId = "3";
                    $.ajax({
                        type: "post",
                        dataType: "json",
                        async: false,
                        url: "${ctx}/view/basedatamanage/eqmanage/insertCollScene",
                        data: {
                            f_name: fname,
                            f_type: "2",
                            f_pId: pId
                        },
                        success: function (result) {
                            if (result.status === '1') {
                                var id = result.data;
                                zTree.addNodes(treeNode, {f_id: id, f_pId: pId, f_name: fname, f_type:"2"})
                            } else {
                                swal({
                                    title: "采集场景添加失败",
                                    text: "",// 内容
                                    type: "warning",
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
                                title: "采集场景节点添加失败",
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
                });
            }
        }

        //修改名称
        function onRename(e, treeId, treeNode) {
            var zTree = $.fn.zTree.getZTreeObj("sceneTree");
            nodes = zTree.getSelectedNodes(),
                treeNode = nodes[0];
            var id = treeNode.f_id;
            var name = treeNode.f_name;
            $("#f_sceneName").val(name);//自动填充场景名称
            $("#f_sceneNickName").val(name);//自动填充场景别名

            $.ajax({
                type: "post",
                dataType: "json",
                async: false,
                url: "${ctx}/view/basedatamanage/eqmanage/editSceneInfo",
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
                            title: "场景修改失败",
                            text: "",// 内容
                            type: "warning",
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
                        title: "场景修改失败",
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

        //ztree删除节点之前判断是否允许删除节点（这个方法做了一些修改，因为ztree自带的弹窗不是很美观好用）
        function beforeRemove(treeId, treeNode) {
            swal({
                title: "确认删除" + treeNode.f_name + "吗?删除此场景将会删除该场景下的所有模式,点位，值单位信息!",
                text: "",// 内容
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#1ab394",
                confirmButtonText: "确定",
                closeOnConfirm: false
            }, function () {
                zTreeOnRemove(treeNode);


            });
            return false;
        }

        //删除此节点
        function zTreeOnRemove(treeNode) {

            var id = treeNode.f_id;
            $.ajax({
                type: "post",
                dataType: "json",
                async: false,
                url: "${ctx}/view/basedatamanage/eqmanage/deleteSceneInfo",
                data: {
                    id: id,
                },
                success: function (result) {
                    if (result.status === '1') {
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
                        let zTree = $.fn.zTree.getZTreeObj("sceneTree");
                        zTree.removeNode(treeNode);
                        $('#sceneConfig_content').hide();
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
                        title: "删除失败",
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
        }

        //销毁
        function removeHoverDom(treeId, treeNode) {
            $("#addBtn_" + treeNode.tId).unbind().remove();
        }

        //根据id查询table内数据
        function queryTableParam(id) {

            $.ajax({
                type: "post",
                url: "${ctx}/view/basedatamanage/eqmanage/queryTableParam",
                dataType: "json",
                async: false,
                data: {
                    "id": id
                },
                success: function (result) {

                    var data = result.map;
                    sceneList = data;
                    var status = result && result.status;

                    $("#f_sceneName").val(data.f_scenename);//场景名称
                    $("#f_sceneNickName").val(data.f_scenenickname);//场景别名
                    $("#f_sceneDiscrip").val(data.f_discription);//场景说明

                    if (data.f_scenename == null) {//场景未配置的时候，下方的模式模态框不显示
                        $("#underTable").attr("style","display:none");//添加指令按钮是否显示
                    } else {
                        $("#underTable").attr("style","display:block");//添加指令按钮是否显示
                        if (data.f_type == "1"){
                            $("#addInstruction").attr("style","display:unset");//添加指令按钮是否显示
                            $("#insertTableParam").attr("style", "display:block");
                            $("#insertCollectTableParam").attr("style", "display:none");
                            $("#controlBtn").show();//显示
                            $("#collectBtn").hide();//隐藏
                            $("#timeTaskBtn").hide();//隐藏
                            if (status == '0') {
                                const list = [];
                                data.f_id = undefined;
                                list.push(data);

                                initSceneTable(list);
                            } else if (status == '1') {
                                var modedata = result.list;
                                modeList.length = 0;
                                modeList.push(modedata);
                                initSceneTable(modedata);
                            }
                        }else {
                            $("#addInstruction").attr("style","display:none");//添加指令按钮是否显示
                            $("#insertTableParam").attr("style", "display:none");
                            $("#insertCollectTableParam").attr("style", "display:block");
                            $("#collectBtn").show();//显示
                            $("#timeTaskBtn").show();//隐藏
                            $("#controlBtn").hide();//隐藏
                            if (status == '0') {
                                const list = [];
                                list.push(data);
                                initCollectSceneTable(list);
                            } else if (status == '1') {
                                var modedata = result.list;
                                modeList.length = 0;
                                modeList.push(modedata);
                                initCollectSceneTable(modedata);
                            }
                        }
                    }
                },
                complete: function () {
                    hiddenLoad();
                },
                error: function () {
                    swal({
                        title: '根据id查询数据有错！',// 展示的标题
                        type: "success",
                        showCloseButton: false, // 展示关闭按钮
                        allowOutsideClick: false,
                        showConfirmButton: false,
                        timer: 1000
                    });
                }
            })
        }

        //控制场景table初始化
        function initSceneTable(data) {
            var resultData = [];

            valueUnitData = [];
            // let cr = "1";
            // let cr= $(".layui-laypage-em").next().html();
            /*let layui_laypage_em_Length = $("em.layui-laypage-em").next();
            if (typeof layui_laypage_em_Length != 'undefined') {
                if (layui_laypage_em_Length.length >= 2) {
                    layui_laypage_em_Length.splice(0,layui_laypage_em_Length.length - 1);
                }
            }
            if (typeof $("em.layui-laypage-em").next()[0] == 'undefined') {
                cr= $(".layui-laypage-em").next().html();
            } else {
                cr = layui_laypage_em_Length[0].innerText;
            }*/


            if (!BooAdd_or_Edit) {
                if ( typeof cr == 'undefined' || data.length <= 10 ) {
                    cr = 1;
                } else if (data.length > 10 && data.length <= 20) {
                    cr = 2;
                } else if (data.length > 20 && data.length <= 30) {
                    cr = 3;
                } else if (data.length > 30 && data.length <= 40) {
                    cr = 4;
                } else if (data.length > 40 && data.length <= 50) {
                    cr = 5;
                }else if (data.length > 50 && data.length <= 60) {
                    cr = 6;
                }else if (data.length > 60 && data.length <= 70) {
                    cr = 7;
                }else if (data.length > 70 && data.length <= 80) {
                    cr = 8;
                }else if (data.length > 80 && data.length <= 90) {
                    cr = 9;
                }else if (data.length > 90 && data.length <= 100) {
                    cr = 10;
                }
            }

            if (cr*10 > data.length) {
                if (data.length <= 10 ) {
                    cr = 1;
                } else if (data.length > 10 && data.length <= 20) {
                    cr = 2;
                } else if (data.length > 20 && data.length <= 30) {
                    cr = 3;
                } else if (data.length > 30 && data.length <= 40) {
                    cr = 4;
                } else if (data.length > 40 && data.length <= 50) {
                    cr = 5;
                }else if (data.length > 50 && data.length <= 60) {
                    cr = 6;
                }else if (data.length > 60 && data.length <= 70) {
                    cr = 7;
                }else if (data.length > 70 && data.length <= 80) {
                    cr = 8;
                }else if (data.length > 80 && data.length <= 90) {
                    cr = 9;
                }else if (data.length > 90 && data.length <= 100) {
                    cr = 10;
                }
            }

            if (BooPagination) {
                cr = 1;
            }

            BooPagination = false;
            BooAdd_or_Edit = false;

            layui.use(['opTable', 'form'], function () {
                sceneOpTable = layui.opTable.render({
                    elem: '#sceneTable'
                    ,id:'sceneTable'
                    , data: data
                    // , toolbar: '#rowSceneToolBar'
                    // , defaultToolbar: ['print']
                    // , height: 510
                    // , limit: Number.MAX_VALUE // 数据表格默认全部显示
                    , page: {
                        curr: cr
                    }//开启分页
                    , cols: [[
                        {field: 'id', align: 'center', title: '序号', sort: true, templet: '#serial', width: 189}
                        // , {field: 'f_id', align: 'center', title: '模式ID'}
                        , {field: 'f_modename', align: 'center', title: '模式名称', width: 440, edit: true}
                        , { title: '操作', width: 395, align: 'center', toolbar: '#barDemo'}
                        , {align: 'center', title: '场景类型', width: 100,templet:function (d) {
                                return "控制场景"
                            }}
                    ]]
                    ,done:function(res,curr,count){ // 隐藏列
                        cr = curr
                        // $(".layui-table-box").find("[data-field='f_id']").css("display","none");
                    }
                    , openTable: function (itemData) {//
                        if (typeof itemData.f_id != 'undefined') {

                            let booOpen = false;

                          sceneTableId =  'child_1_' + itemData.LAY_INDEX;

                            modeId = itemData.f_id.toString();
                            getPointMassageBySceneInfoIdAndModeId((modeId),function (object) {
                                resultData = object;
                            })
                            //编辑完之后放进返回数据内
                          layui.table.on('edit(child_1_' + itemData.LAY_INDEX + ')', function (obj) {
                                // data = obj.data;
                                // var field = obj.field;
                                // var value = obj.value;
                                // if (field == 'f_value'){
                                //     data.f_value = value
                                // }else if (field == 'f_unit'){
                                //     data.f_unit = value;
                                // }
                              //两种方式，1：全部刷新的方式
                              if (!booOpen) {
                                  let boo = false;
                                  for (let i = 0; i < resultData.length; i++) {
                                      if (!boo){
                                          if (obj.data.f_pointId == resultData[i].f_pointId) {
                                              boo = true;
                                              resultData[i].f_value = obj.data.f_value;

                                          }
                                      }else {
                                          resultData[i].f_value = obj.data.f_value;

                                      }
                                  }


                              } else {
                                  for (let i = 0; i < resultData.length; i++) {
                                      if (obj.data.f_pointId == resultData[i].f_pointId) {
                                          resultData[i].f_value = obj.data.f_value;

                                      }
                                  }
                              }
                              for (let j = 0; j < resultData.length; j++)  {
                                  layui.table.reload(sceneTableId).config.data[j] = resultData[j]
                              }
                              layui.table.reload(sceneTableId);
                              // booOpen = true;

                              //2.只刷新一次的方式
                              // if (!booOpen) {
                              //     let boo = false;
                              //     for (let i = 0; i < resultData.length; i++) {
                              //         if (!boo){
                              //             if (obj.data.f_pointId == resultData[i].f_pointId) {
                              //                 boo = true;
                              //                 resultData[i].f_value = obj.data.f_value;
                              //                 booOpen = true;
                              //             }
                              //         }else {
                              //             resultData[i].f_value = obj.data.f_value;
                              //         }
                              //     }
                              //
                              // } else {
                              //     for (let i = 0; i < resultData.length; i++) {
                              //         if (obj.data.f_pointId == resultData[i].f_pointId) {
                              //             resultData[i].f_value = obj.data.f_value;
                              //
                              //         }
                              //     }
                              // }
                              // layui.table.reload(sceneTableId);


                              valueUnitData = resultData;
                            });
                            return {
                                elem: '#child_1_' + itemData.LAY_INDEX
                                , id: 'child_1_' + itemData.LAY_INDEX
                                , data: resultData
                                , page: false//开启分页
                                // , openVisible: false
                                // ,skin: 'line' //表格风格
                                // ,page: {
                                //     curr: 14
                                // }
                                ,limit: 10000
                                , cols: [[
                                    {field: 'f_pointname', title: '点', width: 870}
                                    , {field: 'f_value', title: '值', width: 128, edit: true}
                                    , {field: 'f_unit', title: '单位', width: 128, edit: true}
                                ]]
                            }
                        }
                    }
                });
            });
        }

        //采集场景table初始化
        function initCollectSceneTable(data) {
            var table = layui.table,
                form = layui.form;
            let resultData = [];
            valueUnitData = [];

            /*let cr = "1";
            // let cr= $(".layui-laypage-em").next().html();
            let layui_laypage_em_Length = $("em.layui-laypage-em").next();
            if (typeof layui_laypage_em_Length != 'undefined') {
                if (layui_laypage_em_Length.length >= 2) {
                    layui_laypage_em_Length.splice(0,layui_laypage_em_Length.length - 1);
                }
            }
            if (typeof $("em.layui-laypage-em").next()[0] == 'undefined') {
                cr= $(".layui-laypage-em").next().html();
            } else {
                cr = layui_laypage_em_Length[0].innerText;
            }*/

            if (!BooAdd_or_Edit) {
                if ( typeof cr == 'undefined' || data.length <= 10 ) {
                    cr = 1;
                } else if (data.length > 10 && data.length <= 20) {
                    cr = 2;
                } else if (data.length > 20 && data.length <= 30) {
                    cr = 3;
                } else if (data.length > 30 && data.length <= 40) {
                    cr = 4;
                } else if (data.length > 40 && data.length <= 50) {
                    cr = 5;
                }else if (data.length > 50 && data.length <= 60) {
                    cr = 6;
                }else if (data.length > 60 && data.length <= 70) {
                    cr = 7;
                }else if (data.length > 70 && data.length <= 80) {
                    cr = 8;
                }else if (data.length > 80 && data.length <= 90) {
                    cr = 9;
                }else if (data.length > 90 && data.length <= 100) {
                    cr = 10;
                }
            }
            if (cr * 10 > data.length) {
                if (data.length <= 10 ) {
                    cr = 1;
                } else if (data.length > 10 && data.length <= 20) {
                    cr = 2;
                } else if (data.length > 20 && data.length <= 30) {
                    cr = 3;
                } else if (data.length > 30 && data.length <= 40) {
                    cr = 4;
                } else if (data.length > 40 && data.length <= 50) {
                    cr = 5;
                }else if (data.length > 50 && data.length <= 60) {
                    cr = 6;
                }else if (data.length > 60 && data.length <= 70) {
                    cr = 7;
                }else if (data.length > 70 && data.length <= 80) {
                    cr = 8;
                }else if (data.length > 80 && data.length <= 90) {
                    cr = 9;
                }else if (data.length > 90 && data.length <= 100) {
                    cr = 10;
                }
            }

            if (BooPagination) {
                cr = 1;
            }

            BooPagination = false;
            BooAdd_or_Edit = false;

            layui.use(['opTable', 'form'], function () {
                tabCollectAcademeNotice = layui.opTable.render({
                    elem: '#collectSceneTable'
                    ,id:'collectSceneTable'
                    , data: data
                    // , toolbar: '#rowCollectSceneToolBar'
                    // , defaultToolbar: ['print']
                    // , limit: Number.MAX_VALUE // 数据表格默认全部显示
                    // , height: 510
                    , page: {
                        curr: cr
                        }//开启分页
                    , cols: [[
                        {field: 'id', align: 'center', title: '序号', sort: true, templet: '#serial', width: 189}
                        // , {field: 'f_id', align: 'center', title: '模式ID'}
                        , {field: 'f_modename', align: 'center', title: '模式名称', width: 440, edit: true}
                        , {title: '操作', width: 395, align: 'center', toolbar: '#collectBarDemo'}
                        , {align: 'center', title: '场景类型', width: 100,templet:function (d) {
                                return "采集场景"
                            }}
                    ]]
                    ,done:function(res,curr,count){ // 隐藏列
                        cr = curr
                        // $(".layui-table-box").find("[data-field='f_id']").css("display","none");
                    }
                    , openTable: function (itemData) {
                        if (typeof itemData.f_id != 'undefined') {
                            let modeId = itemData.f_id.toString();
                            getPointMassageBySceneInfoIdAndModeId((modeId),function (object) {
                                resultData = object;
                            })

                            return {
                                elem: '#child_2_' + itemData.LAY_INDEX
                                , id: 'child_2_' + itemData.LAY_INDEX
                                , data: resultData
                                , openVisible: false
                                , cols: [[
                                    {field: 'f_pointname', title: '点', width: 1241}
                                ]]
                                ,done:function () {
                                }
                            }
                        }
                    },
                });
            })
        }

        //根据模式id和场景id获取点位信息
        function getPointMassageBySceneInfoIdAndModeId (modeId,callback) {
            let resultData = [];
            $.ajax({
                url: "${ctx}/view/basedatamanage/eqmanage/getValueAndUnitById",
                type: "post",
                dataType: "json",
                async: false,
                contentType: 'application/json; charset=UTF-8',
                data: JSON.stringify({
                    sceneId: sceneInfoId,
                    modeId: modeId
                }),
                success: function (result) {
                    if (result.status == '0') {
                        resultData = []
                    } else {
                        resultData = result.list;

                    }
                    callback(resultData);
                },
            })
        }

        //采集场景
        layui.table.on('tool(collectSave)',function (obj) {

            var data = obj.data //获得当前行数据
                , layEvent = obj.event; //获得 lay-event 对应的值

            if (typeof data.f_sceneInfoId == 'undefined') {
                scenemodeId = null;//模式id
            } else {
                scenemodeId = data.f_id;
            }

            switch (layEvent) {
                case 'saveValue':
                    $.ajax({
                        url: "${ctx}/view/basedatamanage/eqmanage/insertCollectPointValueUnitAndIssue",
                        type: "post",
                        dataType: "json",
                        async: false,
                        contentType: 'application/json; charset=UTF-8',
                        data: JSON.stringify({
                            sceneInfoType:type,//场景模式类型( 0 表示控制私有 1 表示控制公有 2采集公有 3 采集私有)
                            f_modename: data.f_modename,
                            sceneInfoId: sceneInfoId,
                            scenemodeId : scenemodeId
                        }),
                        beforeSend: function () {
                            showLoad();
                        },
                        success: function (result) {

                            if (result.status == '1'){//已同步
                                swal({
                                    title: result.msg,// 展示的标题
                                    type: "success",
                                    showCloseButton: false, // 展示关闭按钮
                                    allowOutsideClick: false,
                                    showConfirmButton: false,
                                    timer: 1000
                                });
                                // $("#collectisynchro").removeAttr("hidden");
                                // $("#collectnotynchro").attr("hidden");
                                BooAdd_or_Edit = true;
                                reloadTable();
                                if (typeof basedatamanage_eqmanage_planConfig != 'undefined') {
                                    basedatamanage_eqmanage_planConfig.getSceneInfoAndModeInfo(type);
                                }
                            }else if (result.status == '0'){
                                swal({
                                    title: result.msg,// 展示的标题
                                    type: "warning",
                                    showCloseButton: false, // 展示关闭按钮
                                    allowOutsideClick: false,
                                    showConfirmButton: false,
                                    timer: 1000
                                });
                                // $("#collectisynchro").attr("hidden");
                                // $("collect#notynchro").removeAttr("hidden");
                                reloadTable();
                            }
                        },
                        error: function () {
                            swal({
                                title: result.msg,// 展示的标题
                                type: "warning",
                                showCloseButton: false, // 展示关闭按钮
                                allowOutsideClick: false,
                                showConfirmButton: false,
                                timer: 1000
                            });
                        }
                    })
                    break;
                case 'addMode':

                    if (data.f_modename == null) {//场景名称,根据场景名称是否添加来判断当前模式是否添加,如果场景名称没有添加,则说明当前模式没有添加,弹出提示框
                        swal({
                            title: "请先配置当前场景信息",// 展示的标题
                            type: "warning",
                            showCloseButton: false, // 展示关闭按钮
                            allowOutsideClick: false,
                            showConfirmButton: false,
                            timer: 1500
                        });
                        return;
                    }

                    if (scenemodeId == null || sceneInfoId == null) {
                        swal({
                            title: '请先保存!',// 展示的标题
                            type: "error",
                            showCloseButton: false, // 展示关闭按钮
                            allowOutsideClick: false,
                            showConfirmButton: false,
                            timer: 1000
                        });
                        return
                    }

                    $('#addCollectionTableData').modal('show');
                    collectionAddPointModel = true;//控制场景添加指令模态框是否显示
                    break;
                case 'updateMode':

                    if (data.f_modename == null) {//场景名称,根据场景名称是否添加来判断当前模式是否添加,如果场景名称没有添加,则说明当前模式没有添加,弹出提示框
                        swal({
                            title: "请先配置当前场景信息",// 展示的标题
                            type: "warning",
                            showCloseButton: false, // 展示关闭按钮
                            allowOutsideClick: false,
                            showConfirmButton: false,
                            timer: 1500
                        });
                        return;
                    }

                    if (scenemodeId == null || sceneInfoId == null) {
                        swal({
                            title: '参数错误!',// 展示的标题
                            type: "error",
                            showCloseButton: false, // 展示关闭按钮
                            allowOutsideClick: false,
                            showConfirmButton: false,
                            timer: 1000
                        });
                        return
                    }

                    $.ajax({
                        <#--url: "${ctx}/view/basedatamanage/eqmanage/synchroPointValueUnitAndIssue",-->
                        url: "${ctx}/view/basedatamanage/eqmanage/updateCollectMode",
                        type: "post",
                        dataType: "json",
                        async: false,
                        contentType :'application/json;charset=UTF-8',// 核心-->
                        data : JSON.stringify({
                            f_pointname: arrName, //点位名称
                            f_pointId: pointId,
                            f_type: type, //点位类型( 0 表示控制私有 1 表示控制公有 2采集公有 3 采集私有)
                            modeId: scenemodeId,//模式id
                            sceneId: sceneInfoId,//场景id
                            // sceneType:type//场景模式类型( 0 表示控制私有 1 表示控制公有 2采集公有 3 采集私有)
                        }),
                        success: function (result) {
                            if (result.status == '1'){

                                $('#updateCollectionTableData').modal('show');

                                collectionUpdatePointModel = true;

                            }else if (result.status == '0'){
                                swal({
                                    title: result.msg,// 展示的标题
                                    type: "error",
                                    showCloseButton: false, // 展示关闭按钮
                                    allowOutsideClick: false,
                                    showConfirmButton: false,
                                    timer: 1500
                                });
                            }
                        },
                        error: function () {
                            swal({
                                title: "同步失败！",// 展示的标题
                                type: "error",
                                showCloseButton: false, // 展示关闭按钮
                                allowOutsideClick: false,
                                showConfirmButton: false,
                                timer: 1500
                            });
                        }
                    })
                    break;
                case "deleteValue":
                    if (scenemodeId == null) {
                        swal({
                            title: '当前模式未保存,无需删除!',// 展示的标题
                            type: "error",
                            showCloseButton: false, // 展示关闭按钮
                            allowOutsideClick: false,
                            showConfirmButton: false,
                            timer: 1000
                        });
                        return
                    }
                    swal({
                        title : "确定要删除吗？",
                        text : "删除此模式将会删除该模式下的点位!",
                        type : "warning",
                        showCancelButton : true,
                        confirmButtonColor : "#1ab394",
                        confirmButtonText : "确定",
                        closeOnConfirm : true
                    },function () {

                        $.ajax({
                            url: "${ctx}/view/basedatamanage/eqmanage/deletCollectModeInfomation",
                            type: "post",
                            dataType: "json",
                            async:false,
                            data:{
                                modeId : scenemodeId,
                                sceneId : sceneInfoId
                            },
                            success: function (result) {
                                if(result.status == "1"){
                                    setTimeout(function () {
                                        swal({
                                            title: result.msg,// 展示的标题
                                            type: "success",
                                            showCloseButton: false, // 展示关闭按钮
                                            allowOutsideClick: false,
                                            showConfirmButton: false,
                                            timer: 1500
                                        });
                                    },300)
                                    BooAdd_or_Edit = true;
                                    queryTableParam(sceneInfoId);
                                    if (typeof basedatamanage_eqmanage_planConfig != 'undefined') {
                                        basedatamanage_eqmanage_planConfig.getSceneInfoAndModeInfo(type);
                                    }
                                }else {
                                    setTimeout(function () {
                                        swal({
                                            title: result.msg,// 展示的标题
                                            type: "error",
                                            showCloseButton: false, // 展示关闭按钮
                                            allowOutsideClick: false,
                                            showConfirmButton: false,
                                            timer: 1500
                                        });
                                    },300)
                                }
                            },
                            error:function(result){

                            }
                        })
                    })
                    break;

            }
        })

        //控制场景
        layui.table.on('tool(save)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"


            var data = obj.data //获得当前行数据
                , layEvent = obj.event; //获得 lay-event 对应的值

            if(data.f_scenename == "" || data.f_scenenickname == "" || data.f_discription == "") {
                swal({
                    title : '请确定场景信息是否保存!',// 展示的标题
                    text : "",// 内容
                    type : "warning",
                    showCloseButton : false, // 展示关闭按钮
                    allowOutsideClick : false,
                    showConfirmButton : false,
                    timer : 1000
                });
                return;
            }
            if (layEvent === 'saveValue') {
                if (data.f_modename == "") {//模式名称未填写
                    swal({
                        title : '请填写模式名称!',// 展示的标题
                        text : "",// 内容
                        type : "warning",
                        showCloseButton : false, // 展示关闭按钮
                        allowOutsideClick : false,
                        showConfirmButton : false,
                        timer : 1000
                    });
                    return;
                }
                    $.ajax({
                        url: "${ctx}/view/basedatamanage/eqmanage/insertPointValueUnitAndIssue",
                        type: "post",
                        dataType: "json",
                        async: false,
                        contentType: 'application/json; charset=UTF-8',
                        data: JSON.stringify({
                            sceneData: sceneList,
                            pointData: valueUnitData,
                            modeData: data,
                            sceneInfoId: sceneInfoId
                        }),
                        beforeSend: function () {
                            showLoad();
                        },
                        success: function (result) {

                            valueUnitData.length = 0;
                            if (result.status == '1'){//已同步
                                swal({
                                    title: result.msg,// 展示的标题
                                    type: "success",
                                    showCloseButton: false, // 展示关闭按钮
                                    allowOutsideClick: false,
                                    showConfirmButton: false,
                                    timer: 1000
                                });
                                // $("#isynchro").removeAttr("hidden");
                                // $("#notynchro").attr("hidden");
                                BooAdd_or_Edit = true;
                                reloadTable();
                                if (typeof basedatamanage_eqmanage_planConfig != 'undefined') {
                                    basedatamanage_eqmanage_planConfig.getSceneInfoAndModeInfo(type);
                                }
                            }else if (result.status == '0'){
                                swal({
                                    title: result.msg,// 展示的标题
                                    type: "success",
                                    showCloseButton: false, // 展示关闭按钮
                                    allowOutsideClick: false,
                                    showConfirmButton: false,
                                    timer: 2000
                                });
                                // reloadTable();
                                // $("#isynchro").attr("hidden");
                                // $("#notynchro").removeAttr("hidden");
                                // reloadTable();
                            }
                        },
                        error: function () {
                            swal({
                                title: result.msg,// 展示的标题
                                type: "warning",
                                showCloseButton: false, // 展示关闭按钮
                                allowOutsideClick: false,
                                showConfirmButton: false,
                                timer: 1000
                            });
                        }
                    })
                }else if (layEvent === 'deleteValue'){

                if (typeof obj.data.f_id == 'undefined'){
                    swal({
                        title : '请确定当前模式是否保存!',// 展示的标题
                        text : "",// 内容
                        type : "warning",
                        showCloseButton : false, // 展示关闭按钮
                        allowOutsideClick : false,
                        showConfirmButton : false,
                        timer : 1000
                    });
                    return;
                }
                if (modeList.length == 0) {
                    swal({
                        title: '默认模式未保存,请保存后再操作！',// 展示的标题
                        type: "warning",
                        showCloseButton: false, // 展示关闭按钮
                        allowOutsideClick: false,
                        showConfirmButton: false,
                        timer: 1000
                    });
                    return;
                }
                swal({
                    title : "确定要删除吗？",
                    text : "删除此模式将会删除该模式下的点位,值单位!",
                    type : "warning",
                    showCancelButton : true,
                    confirmButtonColor : "#1ab394",
                    confirmButtonText : "确定",
                    closeOnConfirm : true
                }, function (){
                        $.ajax({
                            url: "${ctx}/view/basedatamanage/eqmanage/deletModeInfomation",
                            type: "post",
                            dataType: "json",
                            async:false,
                            data:{
                                modeId : obj.data.f_id,
                                sceneId : sceneInfoId
                            },
                            success:function(result){

                                if(result.status == "1"){
                                    setTimeout(function () {
                                        swal({
                                            title: result.msg,// 展示的标题
                                            type: "success",
                                            showCloseButton: false, // 展示关闭按钮
                                            allowOutsideClick: false,
                                            showConfirmButton: false,
                                            timer: 1500
                                        });
                                    },300)
                                    BooAdd_or_Edit = true;
                                    queryTableParam(sceneInfoId);
                                    if (typeof basedatamanage_eqmanage_planConfig != 'undefined') {
                                        basedatamanage_eqmanage_planConfig.getSceneInfoAndModeInfo(type);
                                    }
                                }else {

                                    setTimeout(function () {
                                        swal({
                                            title: result.msg,// 展示的标题
                                            type: "warning",
                                            showCloseButton: false, // 展示关闭按钮
                                            allowOutsideClick: false,
                                            showConfirmButton: false,
                                            timer: 2000
                                        });
                                    },300)
                                }
                            },
                            error:function(){
                                setTimeout(function () {
                                    swal({
                                        title: result.msg,// 展示的标题
                                        type: "success",
                                        showCloseButton: false, // 展示关闭按钮
                                        allowOutsideClick: false,
                                        showConfirmButton: false,
                                        timer: 1500
                                    });
                                },300)
                            }
                        })
                 })
                }else if (layEvent === 'contrastValue'){

                if (typeof obj.data.f_id == 'undefined'){
                    swal({
                        title : '请确定当前模式是否保存!',// 展示的标题
                        text : "",// 内容
                        type : "warning",
                        showCloseButton : false, // 展示关闭按钮
                        allowOutsideClick : false,
                        showConfirmButton : false,
                        timer : 1000
                    });
                    return;
                }

                $("#contrast_modeId").val(data.f_id); //模式Id

                if (data.f_enable == "1"){
                    $("#contrast_modeEnable").val("使能"); //是否使能
                }else {
                    $("#contrast_modeEnable").val("不使能"); //是否使能
                }

                $("#contrast_sceneInfoId").val(data.f_sceneInfoId); //场景Id

                $("#contrast_modeName").val(data.f_modename); //模式名称

                $("#contrast_sceneName").val(sceneList.f_scenename); //场景名称

                $("#contrast_sceneNickname").val(sceneList.f_scenenickname); //场景别名

                $.ajax({
                    type: "post",
                    url: "${ctx}/view/basedatamanage/eqmanage/queryContrastLocalInfo",
                    dataType: "json",
                    async: false,
                    data: {
                        "modeId": data.f_id
                    },
                    success:function (result) {
                        if (result.status == "1"){
                            var map = result.map;
                            $("#contrast_pointId").val(map.pointId); //点位Id
                            //$("#contrast_pointName").val(map.pointname); //点位名称
                            $("#contrast_pointValue").val(map.value); //点位值
                            $("#contrast_pointUnit").val(map.unit); //点位单位
                            $('#contrastModeValue').modal('show');
                        }else {
                            swal({
                                title: result.msg,// 展示的标题
                                type: "success",
                                showCloseButton: false, // 展示关闭按钮
                                allowOutsideClick: false,
                                showConfirmButton: false,
                                timer: 1500
                            });
                            return;
                        }
                    },
                    error:function(){
                        swal({
                            title: "请先添加数据再同步!",// 展示的标题
                            type: "error",
                            showCloseButton: false, // 展示关闭按钮
                            allowOutsideClick: false,
                            showConfirmButton: false,
                            timer: 1500
                        });
                    }
                })

                $.ajax({
                    type: "post",
                    url: "${ctx}/view/basedatamanage/eqmanage/queryContrastUnderInfo",
                    dataType: "json",
                    async: false,
                    data: {
                        "modeId": data.f_id,
                        "sceneId": data.f_sceneInfoId,
                        "type":type
                    },
                    success:function (result) {

                        if (result.status == "1"){
                            swal({
                                title: "下位机数据获取成功",// 展示的标题
                                type: "success",
                                showCloseButton: false, // 展示关闭按钮
                                allowOutsideClick: false,
                                showConfirmButton: false,
                                timer: 1500
                            });
                        }else {
                            swal({
                                title: result.msg,// 展示的标题
                                type: "warning",
                                showCloseButton: false, // 展示关闭按钮
                                allowOutsideClick: false,
                                showConfirmButton: false,
                                timer: 1500
                            });
                        }
                    },
                    error:function () {
                        swal({
                            title: "下位机无当前数据!",// 展示的标题
                            type: "warning",
                            showCloseButton: false, // 展示关闭按钮
                            allowOutsideClick: false,
                            showConfirmButton: false,
                            timer: 1500
                        });
                    }
                })

            }else if (layEvent === 'synchroValue'){
                if (typeof obj.data.f_id == 'undefined'){
                    swal({
                        title : '请确定当前模式是否保存!',// 展示的标题
                        text : "",// 内容
                        type : "warning",
                        showCloseButton : false, // 展示关闭按钮
                        allowOutsideClick : false,
                        showConfirmButton : false,
                        timer : 1000
                    });
                    return;
                }
                $.ajax({
                    url: "${ctx}/view/basedatamanage/eqmanage/synchroPointValueUnitAndIssue",
                    type: "post",
                    dataType: "json",
                    async: false,
                    contentType: 'application/json; charset=UTF-8',
                    data: JSON.stringify({
                        modeId : obj.data.f_id,
                        sceneId : sceneInfoId,
                        sceneType:type
                    }),
                    success: function (result) {

                        if (result.status == '1'){
                            swal({
                                title: result.msg,// 展示的标题
                                type: "success",
                                showCloseButton: false, // 展示关闭按钮
                                allowOutsideClick: false,
                                showConfirmButton: false,
                                timer: 1500
                            });

                            // $("#isynchro").removeAttr("hidden");
                            // $("#notynchro").attr("hidden");
                        }else if (result.status == '0'){
                            swal({
                                title: result.msg,// 展示的标题
                                type: "error",
                                showCloseButton: false, // 展示关闭按钮
                                allowOutsideClick: false,
                                showConfirmButton: false,
                                timer: 1500
                            });
                            // $("#isynchro").attr("hidden");
                            // $("#notynchro").removeAttr("hidden");
                        }
                    },
                    error: function () {
                        swal({
                            title: "同步失败！",// 展示的标题
                            type: "error",
                            showCloseButton: false, // 展示关闭按钮
                            allowOutsideClick: false,
                            showConfirmButton: false,
                            timer: 1500
                        });
                    }
                })

            }
         });

        //下位机数据填充
        function contrastModeValue(data){

            $("#contrast_underSceneInfoId").val(data.id); //场景Id

            $("#contrast_underSceneName").val(data.name); //场景Id

            $("#contrast_underNickName").val(data.alias); //场景Id

            if (data.active == 1){
                $("#contrast_underActive").val("使能"); //场景Id
            }else {
                $("#contrast_underActive").val("不使能"); //场景Id
            }

            var  mode;

            if (data.sceneType == 0){
                mode = data.controlMode;
            }else {
                mode = data.controlMode;
            }

            $("#contrast_underModeId").val(mode.id); //场景Id

            $("#contrast_underModeName").val(mode.name); //场景Id

            var  point = [];

            var pointId = [];

            var pointValue = [];

            var pointUnit = [];

            if (data.sceneType == 0){
                point = mode.controlPoint;
                for (var i =0 ;i<point.length;i++){
                    pointId.push(point[i].pointID)
                    pointValue.push(point[i].runValue)
                    var unit = point[i].timeUnit;
                    if (unit == undefined){
                        pointUnit.push("无")
                    }else {
                        pointUnit.push(point[i].timeUnit)
                    }
                }
            }

            $("#contrast_underPointId").val(pointId); //点位Id

            $("#contrast_underPointValue").val(pointValue); //点位值
        }

        //添加指令
        function addInstruction() {

            var name = $("#f_sceneName").val();
            var nickname = $("#f_sceneNickName").val();
            var sceneDiscrip = $("#f_sceneDiscrip").val();
            var sceneMode = $("#f_sceneMode").val();
            if (name == "" || nickname == "" || sceneDiscrip == "" || sceneMode == "") {
                swal({
                    title: '请将系统名称等添加后再添加指令！',// 展示的标题
                    type: "warning",
                    showCloseButton: false, // 展示关闭按钮
                    allowOutsideClick: false,
                    showConfirmButton: false,
                    timer: 1000
                });
                return;
            } else if (modeList.length == 0){
                swal({
                    title: '请添加模式后再添加指令！',// 展示的标题
                    type: "warning",
                    showCloseButton: false, // 展示关闭按钮
                    allowOutsideClick: false,
                    showConfirmButton: false,
                    timer: 1000
                });
                return;
            } else {
                $('#addTableData').modal('show');
                controlPointModel = true;
            }
        }

        //控制场景添加指令模态框
        $("#clearPoint,#cancelPoint").click(function(event){
            pointId.length = 0;
            arrName.length = 0;
            arrNameAndNickName.length = 0;
            $('#f_newPointName').val("");
            $('#selectPointTree').modal('hide');
        })
        //采集场景修改指令模态框
        $("#updateCollectionClearPoint,#updateCancelPoint").click(function(event){
            pointId.length = 0;
            arrName.length = 0;
            arrNameAndNickName.length = 0;
            $('#updateCollectionPointName').val("");
            $('#selectPointTree').modal('hide');
        })
        //采集场景添加指令模态框
        $("#AddCollectionclearPoint,#addCancelPoint").click(function(event){
            pointId.length = 0;
            arrName.length = 0;
            arrNameAndNickName.length = 0;
            $('#addCollectionPointName').val("");
            $('#selectPointTree').modal('hide');
        })

        //控制场景模态框内添加新增的数据
        $("#submitAddTableData").click(function () {
            if (pointId.length == 0) {
                swal({
                    title: '未选择点位，请重新选择！',// 展示的标题
                    type: "warning",
                    showCloseButton: false, // 展示关闭按钮
                    allowOutsideClick: false,
                    showConfirmButton: false,
                    timer: 1000
                });
                return
            }
            $.ajax({
                url: "${ctx}/view/basedatamanage/eqmanage/insertSceneModeAndScene",
                type: "post",
                contentType: 'application/json;charset=UTF-8',// 核心
                dataType: "json",
                data: JSON.stringify({

                    f_pointname: arrName, //点位名称

                    f_sceneInfoId: sceneInfoId,//场景Id

                    f_pointId: pointId,

                    f_type: "1", //点位类型

                    modeData : modeList
                }),
                success: function (result) {
                    //
                    var status = result && result.status;
                    if (status == '0') {
                        swal("配置失败",result.msg, "error");
                    } else if (status == '1') {
                        swal({
                            title: result.msg,// 展示的标题
                            type: "success",
                            showCloseButton: false, // 展示关闭按钮
                            allowOutsideClick: false,
                            showConfirmButton: false,
                            timer: 1000
                        });
                        BooAdd_or_Edit = true;
                        queryTableParam(sceneInfoId);
                        $('#addTableData').modal('hide');
                        $('#selectPointTree').modal('hide');

                    }
                },
                error: function () {
                    swal({
                        title: result.msg,// 展示的标题
                        type: "error",
                        showCloseButton: false, // 展示关闭按钮
                        allowOutsideClick: false,
                        showConfirmButton: false,
                        timer: 1000
                    });
                    $('#addTableData').modal('hide');
                }
            })
        });

        //控制场景添加指令模态框的回退逻辑
        $("#submitAddTableData_reduce").click(function () {
            pointId.pop();
            arrName.pop();
            arrNameAndNickName.pop();
            if (pointId.length == 0) {
                DDC_LDCId.length = 0;
            }
            $('#f_newPointName').val(arrNameAndNickName.join(' \n '));
            autoTextarea(document.getElementById("f_newPointName"));
        });

        //采集场景添加指令模态框提交逻辑
        $("#submitAddCollectionTableData").click(function () {

            if (scenemodeId == null || sceneInfoId == null || arrName == null || pointId == null || type == null) {
                swal({
                    title: '参数错误!',// 展示的标题
                    type: "error",
                    showCloseButton: false, // 展示关闭按钮
                    allowOutsideClick: false,
                    showConfirmButton: false,
                    timer: 1000
                });
                return
            }
            $.ajax({
                url: "${ctx}/view/basedatamanage/eqmanage/addCollectMode",
                type: "post",
                dataType: "json",
                async: false,
                contentType :'application/json;charset=UTF-8',// 核心-->
                data : JSON.stringify({
                    modeId: scenemodeId,//模式id

                    sceneId: sceneInfoId,//场景id

                    f_pointname: arrName, //点位名称

                    f_pointId: pointId,

                    f_type: type, //点位类型( 0 表示控制私有 1 表示控制公有 2采集公有 3 采集私有)

                }),
                success: function (result) {
                    //
                    var status = result && result.status;
                    if (status == '0') {
                        swal({
                            title: '配置点位失败!',// 展示的标题
                            type: "error",
                            showCloseButton: false, // 展示关闭按钮
                            allowOutsideClick: false,
                            showConfirmButton: false,
                            timer: 1000
                        });
                    } else if (status == '1') {
                        swal({
                            title: '配置点位成功',// 展示的标题
                            type: "success",
                            showCloseButton: false, // 展示关闭按钮
                            allowOutsideClick: false,
                            showConfirmButton: false,
                            timer: 1000
                        });
                    }
                    BooAdd_or_Edit = true;
                    queryTableParam(sceneInfoId);
                    $('#addCollectionTableData').modal('hide');//隐藏指令模态框
                    $('#selectPointTree').modal('hide');//隐藏设备树选择模态框
                },
                error: function () {
                    swal({
                        title: result.msg,// 展示的标题
                        type: "error",
                        showCloseButton: false, // 展示关闭按钮
                        allowOutsideClick: false,
                        showConfirmButton: false,
                        timer: 1000
                    });
                    $('#addCollectionTableData').modal('hide');
                }
            })
        })
        $("#submitAddCollectionTableData_reduce").click(function () {
            pointId.pop();
            arrName.pop();
            arrNameAndNickName.pop();
            $('#addCollectionPointName').val(arrNameAndNickName.join(' \n '));
            autoTextarea(document.getElementById("addCollectionPointName"));
        });
        //采集场景修改指令模态框提交逻辑
        $("#submitUpdateCollectionTableData").click(function () {

            if (scenemodeId == null || sceneInfoId == null || arrName == null || pointId == null || type == null) {
                swal({
                    title: '参数错误!',// 展示的标题
                    type: "error",
                    showCloseButton: false, // 展示关闭按钮
                    allowOutsideClick: false,
                    showConfirmButton: false,
                    timer: 1000
                });
                return
            }
            $.ajax({
                url: "${ctx}/view/basedatamanage/eqmanage/updateCollectMode",
                type: "post",
                dataType: "json",
                async: false,
                contentType :'application/json;charset=UTF-8',// 核心-->
                data : JSON.stringify({
                    modeId: scenemodeId,//模式id

                    sceneId: sceneInfoId,//场景id

                    f_pointname: arrName, //点位名称

                    f_pointId: pointId,

                    f_type: type, //点位类型( 0 表示控制私有 1 表示控制公有 2采集公有 3 采集私有)

                }),
                success: function (result) {
                    //
                    var status = result && result.status;
                    if (status == '0') {
                        swal({
                            title: '配置点位失败!',// 展示的标题
                            type: "error",
                            showCloseButton: false, // 展示关闭按钮
                            allowOutsideClick: false,
                            showConfirmButton: false,
                            timer: 1000
                        });
                    } else if (status == '1') {
                        swal({
                            title: '配置点位成功',// 展示的标题
                            type: "success",
                            showCloseButton: false, // 展示关闭按钮
                            allowOutsideClick: false,
                            showConfirmButton: false,
                            timer: 1000
                        });
                    }
                    BooAdd_or_Edit = true;
                    queryTableParam(sceneInfoId);
                    $('#updateCollectionTableData').modal('hide');//隐藏指令模态框
                    $('#selectPointTree').modal('hide');//隐藏设备树选择模态框
                },
                error: function () {
                    swal({
                        title: result.msg,// 展示的标题
                        type: "error",
                        showCloseButton: false, // 展示关闭按钮
                        allowOutsideClick: false,
                        showConfirmButton: false,
                        timer: 1000
                    });
                    $('#updateCollectionTableData').modal('hide');
                }
            })
        })
        $("#submitUpdateCollectionTableData_reduce").click(function () {
            pointId.pop();
            arrName.pop();
            arrNameAndNickName.pop();
            $('#updateCollectionPointName').val(arrNameAndNickName.join(' \n '));
            autoTextarea(document.getElementById("updateCollectionPointName"));
        });

        //点位树展示
        $('#f_newPointName,#updateCollectionPointName,#addCollectionPointName').focus(function () {
            if (document.querySelectorAll("#tree_selectPoinTree li").length == 0) {
                swal({
                    title: '正在初始化设备树，请稍后点击！',// 展示的标题
                    type: "warning",
                    showCloseButton: false, // 展示关闭按钮
                    allowOutsideClick: false,
                    showConfirmButton: false,
                    timer: 2000
                });
                $('#f_newPointName,#updateCollectionPointName,#addCollectionPointName').blur();
                return
            }
            $('#selectPointTree').modal('show');
            document.getElementById("f_newPointName").style.height = 40 + "px";
        });

        //新增模式
        function insertPattern() {
            var oldData = layui.table.cache["sceneTable"];

            var modeData = modeList;

            if (modeData.length == 0) {
                swal({
                    title: '请先添加默认模式！',// 展示的标题
                    type: "warning",
                    showCloseButton: false, // 展示关闭按钮
                    allowOutsideClick: false,
                    showConfirmButton: false,
                    timer: 1000
                });
            } else {
                var f_modeType = modeData[0][0].f_modetype;

                var f_sceneInfoId = modeData[0][0].f_sceneInfoId;

                var f_enable = modeData[0][0].f_enable;

                var data = {
                    "f_modename": "",
                    "f_modeType": f_modeType,
                    "f_sceneInfoId": f_sceneInfoId,
                    "f_enable": f_enable
                };

                modeData[0].push(data);
                initSceneTable(modeData[0]);
                $("#controlBtn").hide();//隐藏控制场景下"添加模式"按钮
            }
        }

        //新增采集模式
        function insertCollectPattern() {
            var oldData = layui.table.cache["collectSceneTable"];
            var modeData = modeList;

            if (modeData.length == 0) {
                swal({
                    title: '请保存第一个模式！',// 展示的标题
                    type: "warning",
                    showCloseButton: false, // 展示关闭按钮
                    allowOutsideClick: false,
                    showConfirmButton: false,
                    timer: 1000
                });
            } else {
                var f_modeType = modeData[0][0].f_modetype;

                var f_sceneInfoId = modeData[0][0].f_sceneInfoId;

                var f_enable = modeData[0][0].f_enable;

                var data = {
                    "f_modename": "",
                    "f_modeType": f_modeType,
                    "f_sceneInfoId": f_sceneInfoId,
                    "f_enable": f_enable
                };
                modeData[0].push(data);
                initCollectSceneTable(modeData[0]);
                $("#collectBtn").hide();//隐藏采集场景下"添加模式"按钮
            }
        }

        //保存场景信息
        function saveSceneInfo(){
            let f_scenename = $("#f_sceneName").val();
            let f_scenenickname = $("#f_sceneNickName").val();
            let f_discription = $("#f_sceneDiscrip").val();
            let f_active = $("#f_sceneActive").val();
            if (f_scenename == null || f_scenename == '') {
                swal({
                    title : '场景名称不能为空!',// 展示的标题
                    text : "",// 内容
                    type : "warning",
                    showCloseButton : false, // 展示关闭按钮
                    allowOutsideClick : false,
                    showConfirmButton : false,
                    timer : 1000
                });
                return;
            }
            if (f_scenenickname == null || f_scenenickname == '') {
                swal({
                    title : '场景别名不能为空!',// 展示的标题
                    text : "",// 内容
                    type : "warning",
                    showCloseButton : false, // 展示关闭按钮
                    allowOutsideClick : false,
                    showConfirmButton : false,
                    timer : 1000
                });
                return;
            }
            if (f_discription == null || f_discription == '') {
                swal({
                    title : '场景说明不能为空!',// 展示的标题
                    text : "",// 内容
                    type : "warning",
                    showCloseButton : false, // 展示关闭按钮
                    allowOutsideClick : false,
                    showConfirmButton : false,
                    timer : 1000
                });
                return;
            }
            if (f_active == null || f_active == '') {
                swal({
                    title : '是否使能不能为空!',// 展示的标题
                    text : "",// 内容
                    type : "warning",
                    showCloseButton : false, // 展示关闭按钮
                    allowOutsideClick : false,
                    showConfirmButton : false,
                    timer : 1000
                });
                return;
            }
            $.ajax({
                url: "${ctx}/view/basedatamanage/eqmanage/saveSceneInfo",
                type:"post",
                dataType: "json",
                async: false,
                contentType :'application/json;charset=UTF-8',// 核心-->
                data : JSON.stringify({
                    f_scenename     :  $("#f_sceneName").val(),
                    f_scenenickname :  $("#f_sceneNickName").val(),
                    f_discription   :  $("#f_sceneDiscrip").val(),
                    f_active        :  $("#f_sceneActive").val(),
                    f_id            :  sceneInfoId,
                }),
                success:function (result) {
                    if (result.status == "1"){
                        swal({
                            title: '保存成功',// 展示的标题
                            type: "success",
                            showCloseButton: false, // 展示关闭按钮
                            allowOutsideClick: false,
                            showConfirmButton: false,
                            timer: 1000
                        });
                    }
                    queryTableParam(sceneInfoId);
                    if (typeof basedatamanage_eqmanage_planConfig != 'undefined') {
                        basedatamanage_eqmanage_planConfig.getSceneInfoAndModeInfo(type);
                    }
                },
                error(){
                    swal({
                        title: '保存失败',// 展示的标题
                        type: "error",
                        showCloseButton: false, // 展示关闭按钮
                        allowOutsideClick: false,
                        showConfirmButton: false,
                        timer: 1000
                    });
                }
            })
        }

        //保存成功后刷新table
        function reloadTable(){
            queryTableParam(sceneInfoId);
        }

        //根据文本框内容自适应高度
        function autoTextarea(elem, extra, maxHeight) {
            extra = extra || 0;
            var isFirefox = !!document.getBoxObjectFor || 'mozInnerScreenX' in window,
                    isOpera = !!window.opera && !!window.opera.toString().indexOf('Opera'),
                    addEvent = function (type, callback) {
                        elem.addEventListener ?
                                elem.addEventListener(type, callback, false) :
                                elem.attachEvent('on' + type, callback);
                    },
                    getStyle = elem.currentStyle ? function (name) {
                        var val = elem.currentStyle[name];
                        if (name === 'height' && val.search(/px/i) !== 1) {
                            var rect = elem.getBoundingClientRect();
                            return rect.bottom - rect.top -
                                    parseFloat(getStyle('paddingTop')) -
                                    parseFloat(getStyle('paddingBottom')) + 'px';
                        };
                        return val;
                    } : function (name) {
                        return getComputedStyle(elem, null)[name];
                    },
                    minHeight = parseFloat(getStyle('height'));
            elem.style.resize = 'none';
            var change = function () {
                var scrollTop, height,
                        padding = 0,
                        style = elem.style;
                if (elem._length === elem.value.length) return;
                elem._length = elem.value.length;
                if (!isFirefox && !isOpera) {
                    padding = parseInt(getStyle('paddingTop')) + parseInt(getStyle('paddingBottom'));
                };
                scrollTop = document.body.scrollTop || document.documentElement.scrollTop;
                elem.style.height = minHeight + 'px';
                if (elem.scrollHeight > minHeight) {
                    if (maxHeight && elem.scrollHeight > maxHeight) {
                        height = maxHeight;
                        style.overflowY = 'auto';
                    } else {
                        height = elem.scrollHeight;
                        style.overflowY = 'hidden';
                    };
                    style.height = height + extra + 'px';
                    // scrollTop += parseInt(style.height) - elem.currHeight;
                    // document.body.scrollTop = scrollTop;
                    // document.documentElement.scrollTop = scrollTop;
                    elem.currHeight = parseInt(style.height);
                };
            };
            addEvent('propertychange', change);
            addEvent('input', change);
            addEvent('focus', change);
            change();
        };

        return {
            getAddInstruction: function () {
                addInstruction();
            },
            insertInstruction: function () {
                insertPattern();
            },
            insertCollectInstruction:function(){
                insertCollectPattern();
            },
            saveSceneInfomation:function () {
                saveSceneInfo();
            },
            contrastModeValue : function (data) {
                contrastModeValue(data);
            }
        }
    })(jQuery, window, document);
</script>
