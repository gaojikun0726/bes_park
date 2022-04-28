<#-----
描述：监控台-摄像机
作者：杨超-YangChao
---->

<link href="${ctx}/static/rtmp/video-js.css" rel="stylesheet" type="text/css">
<script src="${ctx}/static/rtmp/video.js"></script>
<script>videojs.options.flash.swf = "${ctx}/static/rtmp/VideoJS.swf";</script>
<!-- 组织机构树模块 -->
<div class="leftarea information_left" style="width:24.4%;">
    <div class="information-model">
		<span class="tree_Subtitle">
			<i class="fa fa-share-alt" aria-hidden="true"></i>&nbsp;请选择组织机构>>>
		</span>
    </div>
    <div id="vidicon_tree" class="Information_area"></div>
</div>
<!-- 右侧监控页面 -->
<div class="information_right" style="width: 75.5%;">
    <div class="information_size" style="height: 100%;">
        <div id="vidicon_right" class="Information_area">
            <div class="vidicon_top" style="height: 20%;width:100%;border-bottom: 1px solid #696464">
                <div style="width: 100%;height: 50%;padding:20px 0px 0px 50px;;">
                    <div style="width: 24%;display: inline-block;">
                        设备编号:<span id="vidicon_right_sbbh" class="vidicon_right_span">
                        </span>
                    </div>
                    <div style="width: 24%;display: inline-block;">
                        设备名称:<span id="vidicon_right_sbmc" class="vidicon_right_span"></span>
                    </div>
                    <div style="width: 24%;display: inline-block;">
                        位置:<span id="vidicon_right_wz" class="vidicon_right_span"></span>
                    </div>
                    <div style="width: 24%;display: inline-block;">
                        设备类型名称:<span id="vidicon_right_sblxmc" class="vidicon_right_span"></span>
                    </div>
                </div>
                <div style="width: 100%;height: 50%;padding: 10px 0px 0px 50px;;">
                    <div style="width: 24%;display: inline-block;">
                        设备型号名称:<span id="vidicon_right_sbxhmc" class="vidicon_right_span"></span>
                    </div>
                    <div style="width: 24%;display: inline-block;">
                        生产商:<span id="vidicon_right_scs" class="vidicon_right_span"></span>
                    </div>
                    <div style="width: 24%;display: inline-block;">
                        经度:<span id="vidicon_right_jd" class="vidicon_right_span"></span>
                    </div>
                    <div style="width: 24%;display: inline-block;">
                        维度:<span id="vidicon_right_wd" class="vidicon_right_span"></span>
                    </div>
                </div>
            </div>
            <div class="vidicon_botton" style="height: 80%;width:100%;">
                <div class="vidicon_botton_top" style="height: 100%;width: 100%;">
                    <div class="vidicon_botton_top_div" id="vidicon_botton_top_div"
                         style="height:100%;width: 100%;display: inline-block;">
                        <video id="vidicon_video_1" class="video-js vjs-default-skin" controls preload="none"
                               width="100%" height="100%" autoplay data-setup="{}" >
                            <source id="vidicon_video_2" src="" type="rtmp/flv"/>
                        </video>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 右侧监控页面end -->
<script type="text/javascript">
     ;
     var view_vidicon = (function ($, window, document, undefined) {
         var _ctx = '${ctx}';
         // 加载树
         $(function () {
            $.issp_ajax({
                 type: "post",
                 url: _ctx + "/vidicon/leftTree",
                 dataType: "json",
                 success: function (result) {
                     if (result.hasOwnProperty("list")) {//判断result返回结果是否包含list
                         if (result.list.length > 0) {//如果包含判断是否长度大于0
                             $('#vidicon_tree').treeview({
                                 data: result.list,         // 数据源
                                 highlightSelected: true,    //是否高亮选中
                                 levels: 2,
                                 enableLinks: true,//必须在节点属性给出href属性
                                 color: "#4a4747",
                                 onNodeSelected: function (event, nodeData) {
                                     // nodeSelected(nodeData);
                                 }
                             });
                         }
                     }
                 },
             });
         });

         function nodeSelected(nodeData) {
             var nodeId = nodeData.id;
             var sbbid = nodeData.nodeTreeId;//重写 子节点 技术 传 sbb 设备号
             // 道路监控摄像头
             setData(sbbid);
             // 动态拼接 source
             setVidicon(sbbid);
         }

         // 赋值
         function setData(nodeId) {
             $.issp_ajax({
                 url: _ctx + "/vidicon/getData",
                 type: "post",
                 data: {
                     nodeId: nodeId,
                 },
                 success: function (result) {
                     if (result.hasOwnProperty("list")) {
                         var res = result.list[0];
                         $("#vidicon_right_sbbh").html(res.SBBH);
                         $("#vidicon_right_sbmc").html(res.SBMC);
                         $("#vidicon_right_wz").html(res.XLWZ);
                         $("#vidicon_right_sblxmc").html(res.F_ZH);
                         $("#vidicon_right_sbxhmc").html(res.F_ROAD_DIRECTION);
                         $("#vidicon_right_scs").html(res.SCS);
                         $("#vidicon_right_jd").html(res.JD);
                         $("#vidicon_right_wd").html(res.WD);
                     } else {
                         $("#vidicon_right_sbbh").html("");
                         $("#vidicon_right_sbmc").html("");
                         $("#vidicon_right_wz").html("");
                         $("#vidicon_right_sblxmc").html("");
                         $("#vidicon_right_sbxhmc").html("");
                         $("#vidicon_right_scs").html("");
                         $("#vidicon_right_jd").html("");
                         $("#vidicon_right_wd").html("");
                     }
                 },
             });
         }

         $("#test_botton1,#test_botton2").click(function(){
        	 var sbid = $(this).attr("sbid");
        	 setVidicon(sbid);
         });
         // 视频rtmp url填充
         function setVidicon(nodeId){
        	 $.issp_ajax({
                 url: _ctx + "/issp/v1.0/getRtmpVideoStreamInfo",
                 type: "Get",
                 data: {
                     deviceId: nodeId,
                 },
                 success: function (result) {

                     var status = result.status;
                     if(status == 'success'){

                    	 //获取视频路径
                         var rtmpUrl = "rtmp://60.208.23.59:1935/live/pag/10.50.13.248/7302/18061402271310003106/0/MAIN/TCP\n";
                         var player = videojs('vidicon_video_1');
                         player.dispose();
                         $("#vidicon_botton_top_div").html("<video id='vidicon_video_1' class='video-js vjs-default-skin' controls preload='none' width='100%' height='100%' autoplay data-setup='{}'><source id='vidicon_video_2' src='' type='rtmp/flv'></video>");
                         //设置资源路径
                         $("#vidicon_video_2").attr("src",rtmpUrl);
                         videojs('vidicon_video_1', {}, function(){//自动播放
                             var myPlayer = videojs('vidicon_video_1');
                             videojs('vidicon_video_1').ready(function(){
                             var myPlayer = this;
                             myPlayer.play();
                             });
                         });
                     }else{
                         swal({
                             title : "系统提示",// 展示的标题
                             text : res.msg, // 内容
                             type: "error",
                             showCloseButton : false, // 展示关闭按钮
                             allowOutsideClick : false,
                             showConfirmButton : false,
                             timer: 1000
                         });
                     }
                 },
             });
         }
         function rtmpSet(){
             //获取视频路径
             var rtmpUrl = "rtmp://127.0.0.1/live/play";
             var player = videojs('vidicon_video_1');
             player.dispose();
             $("#vidicon_botton_top_div").html("<video id='vidicon_video_1' class='video-js vjs-default-skin' controls preload='none' width='100%' height='100%' autoplay data-setup='{}'><source id='vidicon_video_2' src='' type='rtmp/flv'></video>");
             //设置资源路径
             $("#vidicon_video_2").attr("src",rtmpUrl);
             videojs('vidicon_video_1', {}, function(){//自动播放
                 var myPlayer = videojs('vidicon_video_1');
                 videojs('vidicon_video_1').ready(function(){
                     var myPlayer = this;
                     myPlayer.play();
                 });
             });
         }
         return {
             pageInit:function(){
                 rtmpSet();
             },
         }
     })(jQuery, window, document);
     view_vidicon.pageInit();
 </script>
