<#--
  描述:在线井盖设备管理
  作者:YangChao
  时间:2020-10-10 10:36:45
-->
<div class="information-model">
    <span class="Subtitle">
        <i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;在线设备管理
    </span>
    <#-- 搜索框 -->
    <div class="zc_search find">
         <input type="text" class="find-style"  id="realCoverPoint_search" placeholder="编号、名称">
         <button id="queryCoverPointBtn" onclick="zhdg_realCoverPoint_listModuleClosure.Search();"><i class="fa fa-search" aria-hidden="true"></i></button>
    </div>
</div>
<div class="ibox" id="realCoverPoint_ibox" style="height:93%">
    <div style="height:100%">
        <div id="realCoverPoint_iboxTable"></div>
    </div>
</div>

<!---设置 模态框----->
<div class="modal fade" id="realCoverPointformSzModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog" style="height: 60vh;">
        <div class="modal-content" style="height: 100%;">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title"><i class='fa fa-cog' ></i>&nbsp; 设备控制</h4>
            </div>
            <div class="modal-body">
                <#--设备编码id-->
                <input type="hidden" id="realCoverPoint_fCode">
                <div>
                    <ul id="realCoverPointSzJbxx" style="display: block;height: 46vh;border: 1px solid #366c90;margin-top: 2vh;padding: 1vh;border-radius: 7px;">
                        <li style="padding: 1vh;">
                            <div class="switch">
                                <div style="display: inline-block;font-size: 14px;">直流控制1：</div>
                                <div class="onoffswitch" style="display: inline-block;">
                                    <input type="checkbox" checked="" class="onoffswitch-checkbox" id="realCoverPoint_zlkz1" onclick="zhdg_realCoverPoint_listModuleClosure.sendClick(this,'zlkz','61','0')">
                                    <label class="onoffswitch-label" for="realCoverPoint_zlkz1">
                                        <span class="onoffswitch-inner"></span>
                                        <span class="onoffswitch-switch"></span>
                                    </label>
                                </div>
                            </div>
                        </li>
                        <li style="padding: 1vh;">
                            <div class="switch">
                                <div style="display: inline-block;font-size: 14px;">直流控制2：</div>
                                <div class="onoffswitch" style="display: inline-block;">
                                    <input type="checkbox" checked="" class="onoffswitch-checkbox" id="realCoverPoint_zlkz2" onclick="zhdg_realCoverPoint_listModuleClosure.sendClick(this,'zlkz','61','1')">
                                    <label class="onoffswitch-label" for="realCoverPoint_zlkz2">
                                        <span class="onoffswitch-inner"></span>
                                        <span class="onoffswitch-switch"></span>
                                    </label>
                                </div>
                            </div>
                        </li>
                        <li style="padding: 1vh;">
                            <div class="switch">
                                <div style="display: inline-block;font-size: 14px;">继电器1：</div>
                                <div class="onoffswitch" style="display: inline-block;">
                                    <input type="checkbox" checked="" class="onoffswitch-checkbox" id="realCoverPoint_jdq1" onclick="zhdg_realCoverPoint_listModuleClosure.sendClick(this,'jdq','63','0')">
                                    <label class="onoffswitch-label" for="realCoverPoint_jdq1">
                                        <span class="onoffswitch-inner"></span>
                                        <span class="onoffswitch-switch"></span>
                                    </label>
                                </div>
                            </div>
                        </li>
                        <li style="padding: 1vh;">
                            <div class="switch">
                                <div style="display: inline-block;font-size: 14px;">继电器2：</div>
                                <div class="onoffswitch" style="display: inline-block;">
                                    <input type="checkbox" checked="" class="onoffswitch-checkbox" id="realCoverPoint_jdq2" onclick="zhdg_realCoverPoint_listModuleClosure.sendClick(this,'jdq','63','1')">
                                    <label class="onoffswitch-label" for="realCoverPoint_jdq2">
                                        <span class="onoffswitch-inner"></span>
                                        <span class="onoffswitch-switch"></span>
                                    </label>
                                </div>
                            </div>
                        </li>
                        <li style="padding: 1vh;">
                            <div class="switch">
                                <div style="display: inline-block;font-size: 14px;">继电器3：</div>
                                <div class="onoffswitch" style="display: inline-block;">
                                    <input type="checkbox" checked="" class="onoffswitch-checkbox" id="realCoverPoint_jdq3" onclick="zhdg_realCoverPoint_listModuleClosure.sendClick(this,'jdq','63','2')">
                                    <label class="onoffswitch-label" for="realCoverPoint_jdq3">
                                        <span class="onoffswitch-inner"></span>
                                        <span class="onoffswitch-switch"></span>
                                    </label>
                                </div>
                            </div>
                        </li>
                        <li style="padding: 1vh;">
                            <div class="switch">
                                <div style="display: inline-block;font-size: 14px;">继电器4：</div>
                                <div class="onoffswitch" style="display: inline-block;">
                                    <input type="checkbox" checked="" class="onoffswitch-checkbox" id="realCoverPoint_jdq4" onclick="zhdg_realCoverPoint_listModuleClosure.sendClick(this,'jdq','63','3')">
                                    <label class="onoffswitch-label" for="realCoverPoint_jdq4">
                                        <span class="onoffswitch-inner"></span>
                                        <span class="onoffswitch-switch"></span>
                                    </label>
                                </div>
                            </div>
                        </li>
                        <li style="padding: 1vh;">
                            <div class="switch">
                                <div style="display: inline-block;font-size: 14px;">继电器5：</div>
                                <div class="onoffswitch" style="display: inline-block;">
                                    <input type="checkbox" checked="" class="onoffswitch-checkbox" id="realCoverPoint_jdq5" onclick="zhdg_realCoverPoint_listModuleClosure.sendClick(this,'jdq','63','4')">
                                    <label class="onoffswitch-label" for="realCoverPoint_jdq5">
                                        <span class="onoffswitch-inner"></span>
                                        <span class="onoffswitch-switch"></span>
                                    </label>
                                </div>
                            </div>
                        </li>
                        <li style="padding: 1vh;">
                            <div class="switch">
                                <div style="display: inline-block;font-size: 14px;">继电器6：</div>
                                <div class="onoffswitch" style="display: inline-block;">
                                    <input type="checkbox" checked="" class="onoffswitch-checkbox" id="realCoverPoint_jdq6" onclick="zhdg_realCoverPoint_listModuleClosure.sendClick(this,'jdq','63','5')">
                                    <label class="onoffswitch-label" for="realCoverPoint_jdq6">
                                        <span class="onoffswitch-inner"></span>
                                        <span class="onoffswitch-switch"></span>
                                    </label>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>

<!---详情 模态框----->
<div class="modal fade" id="realCoverPointformXqModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" keyboard="true">
    <div class="modal-dialog" style="height: 60vh;">
        <div class="modal-content" style="height: 100%;">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title"><i class='fa fa-info-circle'></i>&nbsp; 设备详情</h4>
            </div>
            <div class="modal-body">
                <#--璐总提议-->
                <ol class="breadcrumb" style="background-color: inherit;">
                    <li class="active"><a href="javascript:void(0);" idnm="realCoverPointXqJbxx" onclick="zhdg_realCoverPoint_listModuleClosure.navClick(this)">基本信息</a></li>
                    <li><a href="javascript:void(0);" idnm="realCoverPointXqSsdsj" onclick="zhdg_realCoverPoint_listModuleClosure.navClick(this)">实时电数据</a></li>
<#--                    <li><a href="javascript:void(0);" idnm="realCoverPointXqSbzt" onclick="zhdg_realCoverPoint_listModuleClosure.navClick(this)">设备状态</a></li>-->
                </ol>
                <div>
                    <ul id="realCoverPointXqJbxx" style="display: block;height: 40vh;border: 1px solid #366c90;margin-top: 2vh;padding: 1vh;border-radius: 7px;">
                        <li style="padding: 1vh;">
                            <div style="display: inline-block;">编码：</div>
                            <div style="display: inline-block;" id="realCoverPointXqJbxx_fCode"></div>
                        </li>
                        <li style="padding: 1vh;">
                            <div style="display: inline-block;">名称：</div>
                            <div style="display: inline-block;" id="realCoverPointXqJbxx_fCoverPointName"></div>
                        </li>
                        <li style="padding: 1vh;">
                            <div style="display: inline-block;">设备ip：</div>
                            <div style="display: inline-block;" id="realCoverPointXqJbxx_fCoverPointIp"></div>
                        </li>
                        <li style="padding: 1vh;">
                            <div style="display: inline-block;">经度：</div>
                            <div style="display: inline-block;" id="realCoverPointXqJbxx_fLongitude"></div>
                        </li>
                        <li style="padding: 1vh;">
                            <div style="display: inline-block;">维度：</div>
                            <div style="display: inline-block;" id="realCoverPointXqJbxx_fLatitude"></div>
                        </li>
                        <li style="padding: 1vh;">
                            <div style="display: inline-block;">所属区域：</div>
                            <div style="display: inline-block;" id="realCoverPointXqJbxx_fRegionName"></div>
                        </li>
                        <li style="padding: 1vh;">
                            <div style="display: inline-block;">详细地址：</div>
                            <div style="display: inline-block;" id="realCoverPointXqJbxx_fRemark"></div>
                        </li>
                    </ul>
                    <ul id="realCoverPointXqSsdsj" style="display: none;height: 40vh;border: 1px solid #366c90;margin-top: 2vh;padding: 1vh;border-radius: 7px;">
                        <li style="padding: 1vh;">
                            <div style="display: inline-block;">实时电压：</div>
                            <div style="display: inline-block;" id="realCoverPointXqSsdsj_Voltage"></div>
                        </li>
                        <li style="padding: 1vh;">
                            <div style="display: inline-block;">有效电流：</div>
                            <div style="display: inline-block;" id="realCoverPointXqSsdsj_electric"></div>
                        </li>
                        <li style="padding: 1vh;">
                            <div style="display: inline-block;">有效功率：</div>
                            <div style="display: inline-block;" id="realCoverPointXqSsdsj_PowerUnit"></div>
                        </li>
                        <li style="padding: 1vh;">
                            <div style="display: inline-block;">交流高压：</div>
                            <div style="display: inline-block;" id="realCoverPointXqSsdsj_rateOfVoltageChnage"></div>
                        </li>
                        <li style="padding: 1vh;">
                            <div style="display: inline-block;">直流低压：</div>
                            <div style="display: inline-block;" id="realCoverPointXqSsdsj_ZlDyqByz"></div>
                        </li>
                        <li style="padding: 1vh;">
                            <div style="display: inline-block;">核心电压：</div>
                            <div style="display: inline-block;" id="realCoverPointXqSsdsj_NbDyCjkZlDyz"></div>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
;var zhdg_realCoverPoint_listModuleClosure = (function($, window, document, undefined){
var _ctx = "${ctx}";


    // 页面定时器
    var timeout  = setInterval(function () {
        zhdg_realCoverPoint_listModuleClosure.Search();
    }, 5000);

    //创建并设置table属性
    $("#realCoverPoint_iboxTable").tabulator({
        height:"100%",
        layout:"fitColumns",//fitColumns，表格将调整列的大小，以使其完全适合容器的宽度
        columnVertAlign:"bottom",
        tooltips:false,
        selectable : 1,
        movableColumns:true,
        columns:[
            {title:"序号",field:"id",width:70,frozen:false, editor:false,formatter : "rownum",align : "center",headerSort:false,tooltip:false,tooltipsHeader:false},
            {title:"设备卡号",field:"sbkh",width:130,sorter:"string",editor:false,align:"center",headerSort:false},
            {title:"当前电量",field:"dqdl",sorter:"string",editor:false,align:"center",headerSort:false},
            {title:"信号强度",field:"xhqd",sorter:"string",editor:false,align:"center",headerSort:false},
            {title:"硬件温度",field:"yjwd",sorter:"string",editor:false,align:"center",headerSort:false},
            {title:"实时经度",field:"ssjd",sorter:"string",editor:false,align:"center",headerSort:false},
            {title:"实时纬度",field:"sswd",sorter:"string",editor:false,align:"center",headerSort:false},
            {title:"水平夹角",field:"spjj",sorter:"string",editor:false,align:"center",headerSort:false},
            // {title:"操作", field:"opt",width:150,align:"left",tooltip:false,tooltipsHeader:false,
            //     formatter:function(cell, formatterParams){
            //         var id = cell.getRow().getData().DeviceId;
            //         return "<div class='btn-group '><button class='btn btn-white btn-sm edit' data-id="+ id + " data-toggle='modal' data-target='#realCoverPointformSzModel'><i class='fa fa-cog' ></i> 设置</button></div>"
            //             +"<button class='btn btn-white btn-sm xq' data-id="+ id + " data-toggle='modal' data-target='#realCoverPointformXqModel'><i class='fa fa-info-circle'></i>  详情</button></div>"
            //     },headerSort:false},
        ],
        rowClick:function(e, row){
            _curRow = row;
        },
    });

    // 直流可控 0关 1开
    function resZlkk(c){
        if(c==0){
            return "<span style='color: red'>关闭</span>";
        }else{
            return "<span style='color: green'>打开</span>";
        }
    }
    // 继电器 1关 0开
    function resJdq(c){
        if(c==1){
            return "<span style='color: red'>关闭</span>";
        }else{
            return "<span style='color: green'>打开</span>";
        }
    }

    // 打开设置模态框
    $('#realCoverPointformSzModel').on('show.bs.modal', function (event) {
        // 居中显示
        $(this).css('display', 'block');
		var modalHeight=$(window).height() / 2 - $('#realCoverPointformSzModel .modal-dialog').height() / 2;
		$(this).find('.modal-dialog').css({
       		'margin-top': modalHeight
		});
        var fCode = $(event.relatedTarget).data("id");
        /** 将设备编码保存到dom中 */
        $("#realCoverPoint_fCode").val(fCode);
        /** 将控制按钮得状态值回显 */
        /**设备在线实时数据*/
        $.issp_ajax({
            url : _ctx + "/zhdg/CoverPoint/getCoverPointRealData",
            type : "post",
            data:{"deviceId" : fCode},
            success : function(data) {
                var map = data.map;
                // 直流可控 0关 1开
                map.ZlKkZt_1=='0'?$("#realCoverPoint_zlkz1").prop("checked","false"):$("#realCoverPoint_zlkz1").prop("checked","true");
                map.ZlKkZt_2=='0'?$("#realCoverPoint_zlkz2").prop("checked","false"):$("#realCoverPoint_zlkz2").prop("checked","true");
                // 继电器 1关 0开
                map.JdqJkZt_1=='0'?$("#realCoverPoint_jdq1").prop("checked","true"):$("#realCoverPoint_zlkz1").prop("checked","false");
                map.JdqJkZt_2=='0'?$("#realCoverPoint_jdq2").prop("checked","true"):$("#realCoverPoint_zlkz2").prop("checked","false");
                map.JdqJkZt_3=='0'?$("#realCoverPoint_jdq3").prop("checked","true"):$("#realCoverPoint_zlkz3").prop("checked","false");
                map.JdqJkZt_4=='0'?$("#realCoverPoint_jdq4").prop("checked","true"):$("#realCoverPoint_zlkz4").prop("checked","false");
                map.JdqJkZt_5=='0'?$("#realCoverPoint_jdq5").prop("checked","true"):$("#realCoverPoint_zlkz5").prop("checked","false");
                map.JdqJkZt_6=='0'?$("#realCoverPoint_jdq6").prop("checked","true"):$("#realCoverPoint_zlkz6").prop("checked","false");
            }
        });



    });

    //关闭模态框清空表单值
    $("#realCoverPointformSzModel").on('hidden.bs.modal', function (event) {
        $(this).find("input").val("");
     });

    // 打开详情模态框-回显数据
    $('#realCoverPointformXqModel').on('show.bs.modal', function (event) {
        // 居中显示
        $(this).css('display', 'block');
        var modalHeight=$(window).height() / 2 - $('#realCoverPointformXqModel .modal-dialog').height() / 2;
        $(this).find('.modal-dialog').css({
            'margin-top': modalHeight
        });
        var fCode = $(event.relatedTarget).data("id");
        editShow(fCode);
    });

    /** 详情回显*/
    function editShow(fCode){
        /**设备详情数据*/
        $.issp_ajax({
            // url : _ctx + "/zhdg/CoverPoint/getSearchByFcode",
            url : _ctx + "/zhdg/realPoint/getSearch",
            type : "post",
            data:{"id" : fCode},
            success : function(data) {
                var obj = eval("("+data.data+")");
                $("#realCoverPointXqJbxx_fCode").text(obj.fCode);
                $("#realCoverPointXqJbxx_fCoverPointName").text(obj.fCoverPointName);
                $("#realCoverPointXqJbxx_fCoverPointIp").text(obj.fCoverPointIp);
                $("#realCoverPointXqJbxx_fLongitude").text(obj.fLongitude);
                $("#realCoverPointXqJbxx_fLatitude").text(obj.fLatitude);
                $("#realCoverPointXqJbxx_fRegionName").text(obj.fRegionName);
                $("#realCoverPointXqJbxx_fRemark").text(obj.fRemark);
            }
        });
        /**设备在线实时数据*/
        $.issp_ajax({
            url : _ctx + "/zhdg/CoverPoint/getCoverPointRealData",
            type : "post",
            data:{"deviceId" : fCode},
            success : function(data) {
                var map = data.map;
                $("#realCoverPointXqSsdsj_Voltage").text(map.Voltage);
                $("#realCoverPointXqSsdsj_electric").text(map.electric);
                $("#realCoverPointXqSsdsj_PowerUnit").text(map.PowerUnit);
                $("#realCoverPointXqSsdsj_rateOfVoltageChnage").text(map.rateOfVoltageChnage);
                $("#realCoverPointXqSsdsj_ZlDyqByz").text(map.ZlDyqByz);
                $("#realCoverPointXqSsdsj_NbDyCjkZlDyz").text(map.NbDyCjkZlDyz);
            }
        });
    }

    //关闭模态框清空表单值
    $("#realCoverPointformXqModel").on('hidden.bs.modal', function (event) {
        $(this).find("input").val("");
    });

	// 数据查询
	function DataSearch(){
        $.ajax({
			url : _ctx + "/zhjg/realCoverPoint/getSearch",
            // url : _ctx + "/zhdg/realPoint/getSearch",
			type : "post",
			data:({keywords: $("#realCoverPoint_search").val()}),
			success : function(data) {
                var TableRealDataList = data.map.TableRealDataList;
                $("#realCoverPoint_iboxTable").tabulator("setData",TableRealDataList);
                if (document.getElementById('realCoverPoint_ibox') === null){
                    clearTimeout(timeout);
                };
			}
        });
	}

    return {
        Search:function() {
            DataSearch();
        },
        /** 模态框 nav点击事件*/
        navClick:function(e) {
            $(e.parentNode).addClass("active").siblings("li").removeClass("active");
            var idnm = $(e.parentNode).find("a").attr("idnm");
            var ul = $("#"+idnm).parent().find("ul");
            ul.each(function(i){
                $(this).css("display","none");
            })
            $("#"+idnm).css("display","block");
        },

        /**下发点击事件*/
        sendClick:function(e, type, orderCode, digits) {
            var fCode = $("#realCoverPoint_fCode").val();
            var check = $(e).prop("checked");
            var datas = "";
            if(type=='zlkz'){// 0关 1开
                datas = $(e).prop("checked")==true?'1':'0';
            }else if(type=='jdq'){// 1关 0开
                datas = $(e).prop("checked")==true?'0':'1';
            }
            /** 下发指令 */
            $.issp_ajax({
                url : _ctx + "/zhdg/CoverPoint/sendMsg",
                type : "post",
                data:{
                    "DeviceId" : fCode,
                    "datas" : datas,
                    "orderCode" : orderCode,
                    "digits" : digits
                },
                success : function(data) {
                    swal( {
                        title: data.msg,
                        text: "",
                        type: "success",
                        showCloseButton:false,
                        allowOutsideClick:false,
                        showConfirmButton: false,
                        timer:1000
                    });
                },
                error: function(data){
                    swal( {
                        title: data.msg,
                        text: "",
                        type: "warning",
                        showCloseButton:false,
                        allowOutsideClick:false,
                        showConfirmButton: false,
                        timer:1000
                    });
                }
            });
        },

        pageInit : function(){
            zhdg_realCoverPoint_listModuleClosure.Search();
        }
	 }
 })(jQuery, window, document);
 zhdg_realCoverPoint_listModuleClosure.pageInit();
 </script>
<style type="text/css">
    .breadcrumb>li+li:before {
        padding: 0 5px;
        color: inherit;
        content: "/\00a0";
    }
    .breadcrumb > .active {
        color: #0cf;
    }
</style>