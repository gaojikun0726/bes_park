

<div class="information-model">
		<span class="Subtitle">
			<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;管理记录日志列表>>>
		</span>
	<!-- 增加按钮 -->
	<!-- <a id="addPRODUCER" href="javascript:void(-1);" onclick="isspview_besProducterlistManage.prodcer_show_addmodal()" class="btn btn-primary toLeft"> 增加 <i class="fa fa-plus"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>
    </a>  -->
	<!-- 搜索框 -->
	<div class="zc_search find">
		<input type="text" class="find-style"  id="gljllogkeywords" name="gljllogkeywords" placeholder="请输入用户名、角色名">
		<button id="searchgljllog"><i class="fa fa-search" aria-hidden="true"></i></button>
	</div>
</div>

<!---分页列表----->
<div class="ibox" id="gljlxx_ibox" style="height:92%">

</div>
<script type="text/javascript">
	;var gljl_log = (function($, window, document, undefined) {
		//设置格式
		var _curRow = null;
		var _ctx = '${ctx}';
		var keywords='';  //存放搜索内容

		//触发搜索的回车事件
		$("#gljllogkeywords").focus(function(){
			$(this).keydown(function (e){
				if(e.which == "13"){
					gljl_log.loadGljlxx();
				}
			})
		});
		//搜索按钮
		$("#searchgljllog").click(function() {
			gljl_log.loadGljlxx();
		});

		return{
			//分页查询
			loadGljlxx:function(){

				$.ajax({
					url : _ctx + '/view/strongAndWeakElectricityIntegration/gljl/loadGljlxx',
					type : "post",
					data : {
						keywords:$("#gljllogkeywords").val(),
						bars:$("#gljl_log_pageSize").val(),
					},
					beforeSend: function () {
						showLoad();
					},
					success : function(data) {
						$('#gljlxx_ibox').html(data);

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
				gljl_log.loadGljlxx();
			}
		}
	})(jQuery, window, document);
	gljl_log.pageInit();
</script>
