
<div class="information_size">
    <div class="information-model">
		<span class="Subtitle">
			<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;系统登录日志列表>>>
		</span>
        <!-- 增加按钮 -->
        <!-- <a id="addPRODUCER" href="javascript:void(-1);" onclick="isspview_besProducterlistManage.prodcer_show_addmodal()" class="btn btn-primary toLeft"> 增加 <i class="fa fa-plus"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>
        </a>  -->
        <!-- 搜索框 -->
        <div class="zc_search find">
            <input type="text" class="find-style"  id="sysLoginlogkeywords" name="sysLoginlogkeywords" placeholder="请输入用户名、角色名">
            <button id="searchsysLoginlog"><i class="fa fa-search" aria-hidden="true"></i></button>
        </div>
    </div>

    <!---分页列表----->
    <div class="ibox" id="Systemloginlog_ibox1" style="height:92%">
         
    </div>
</div>

<script type="text/javascript">
;var view_sysmanage_logmanage_sysLogin_log = (function($, window, document, undefined) {
	//设置格式
	var _curRow = null;
	var _ctx = '${ctx}';
	var keywords='';  //存放搜索内容
	
	//触发搜索的回车事件
	$("#sysLoginlogkeywords").focus(function(){
		$(this).keydown(function (e){
			if(e.which == "13"){
				view_sysmanage_logmanage_sysLogin_log.loadsysLoginlog();
			} 
		})
	});
	//搜索按钮
	$("#searchsysLoginlog").click(function() {
		view_sysmanage_logmanage_sysLogin_log.loadsysLoginlog();
	});
	
    return{
    	//分页查询
    	loadsysLoginlog:function(){

    		$.ajax({
				url : _ctx + '/view/sysmanage/logmanage/loadsysLoginloglist1',
				type : "post",
				data : {
					keywords:$("#sysLoginlogkeywords").val(),
					 bars:$("#sysLogin_log_pageSize").val(),
				},
				  beforeSend: function () { 
	 		        	showLoad();	             
	 		        },
				success : function(data) {
					$('#Systemloginlog_ibox1').html(data);
					
				},
				complete: function () {
 	            	hiddenLoad();
 	            },
				error : function(XMLHttpRequest,textStatus, errorThrown) {
					toastr.error('', '查询失败');
				}
			});
    	},
    	
    	
    	pageInit : function(){
		view_sysmanage_logmanage_sysLogin_log.loadsysLoginlog();
	}
    }
})(jQuery, window, document);
view_sysmanage_logmanage_sysLogin_log.pageInit();
</script>