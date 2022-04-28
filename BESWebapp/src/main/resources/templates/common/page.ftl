<div class="pages border-top">
	<#if page ??>
	<div class="">
		<div class="col-md-4">
			<div class="m-t-md">当前显示 ${page.startRow?c } 到 ${page.endRow?c } 条，共 ${page.pages?c } 页 ${page.total?c } 条</div>
		</div>
		<div class=" footable-visible">
			<ul class="pagination pull-right">
				<li id="issp_goFirstPage"><a data-page="1" href="javascript:void(0);" onclick="goPage(this,'${formId }','${showPageId }');">«</a></li>
				<li id="issp_goPrePage"><a data-page="${page.prePage?c }" href="javascript:void(0);" onclick="goPage(this,'${formId }','${showPageId }');">‹</a></li>
				<#if page?? && page.navigatepageNums?? && (page.navigatepageNums?size > 0)>
					<#list page.navigatepageNums as pgnum>
						<#if pgnum==page.pageNum>
							<li class="footable-page active"><a data-page="${pgnum?c }" href="javascript:void(0);" onclick="goPage(this,'${formId }','${showPageId }');">${pgnum?c }</a></li>
						<#else>
							<li class="footable-page"><a data-page="${pgnum?c }" href="javascript:void(0);" onclick="goPage(this,'${formId }','${showPageId }');">${pgnum?c }</a></li>
						</#if>
					</#list>
				</#if>
				<li id="issp_goNextPage"><a data-page="${page.nextPage?c }" href="javascript:void(0);" onclick="goPage(this,'${formId }','${showPageId }');">›</a></li>
				<li id="issp_goLastPage"><a data-page="${page.pages?c }" href="javascript:void(0);" onclick="goPage(this,'${formId }','${showPageId }');">»</a></li>
			</ul>
		</div>
		<input type="hidden" name="pageNum" />
		<input type="hidden" id="issp_page_maxPages" value="${page.pages?c }" />
		<input type="hidden" id="issp_page_currPage" value="${page.pageNum?c }" />
	</div>
	</#if>
</div>

<script type="text/javascript">
	var issp_commonPage_UUID_synchronized;
	$(function() {
		//获取一个UUID
		issp_commonPage_UUID_synchronized = guid.newGUID();
		//通过id修改当前id的值，解决多个界面打开时id重复的问题
		$('#issp_page_maxPages').attr('id', 'issp_page_maxPages_' + issp_commonPage_UUID_synchronized);
		$('#issp_page_currPage').attr('id', 'issp_page_currPage_' + issp_commonPage_UUID_synchronized);

		$('#issp_goFirstPage').attr('id', 'issp_goFirstPage_' + issp_commonPage_UUID_synchronized);
		$('#issp_goPrePage').attr('id', 'issp_goPrePage_' + issp_commonPage_UUID_synchronized);
		$('#issp_goNextPage').attr('id', 'issp_goNextPage_' + issp_commonPage_UUID_synchronized);
		$('#issp_goLastPage').attr('id', 'issp_goLastPage_' + issp_commonPage_UUID_synchronized);
		
		var curPage = $('#issp_page_currPage_'+ issp_commonPage_UUID_synchronized).val();
		var maxPage = $('#issp_page_maxPages_'+ issp_commonPage_UUID_synchronized).val();

		issp_commonPage_btn_disabled(curPage, maxPage);
	});

	//根据条件给按钮添加不可点击的样式；	isFirstPage是否是第一页
	function issp_commonPage_btn_disabled(curPage, maxPage) {
		if(curPage == null || maxPage == null){
			return;
		}
		if (curPage == 1) {
			$("#issp_goFirstPage_" + issp_commonPage_UUID_synchronized).addClass("disabled");
			$("#issp_goPrePage_" + issp_commonPage_UUID_synchronized).addClass("disabled");
		} else if(curPage == maxPage){
			$("#issp_goNextPage_" + issp_commonPage_UUID_synchronized).addClass("disabled");
			$("#issp_goLastPage_" + issp_commonPage_UUID_synchronized).addClass("disabled");
		}
	}

	function goPage(objA, formId, showPageId) {
		var pageClass = $(objA).parent().attr("class");
		if(pageClass == "disabled"){
			return;
		}
		var page = Number($(objA).attr("data-page"));
		var maxPages = Number($("#issp_page_maxPages_" + issp_commonPage_UUID_synchronized).val());
		if((page < 1) || (page > maxPages)){
			return;
		}
		if (page >= 1 && page <= maxPages) {
			$('#' + formId + " input[name='pageNum']").val(page);
			$.ajax({
				cache : true,
				type : "POST",
				url : $("#" + formId).attr("action"),
				data : $('#' + formId).serialize(),// 序列化的form
				async : false,
				error : function(data) {
					toastr.error('', '分页查询失败');
				},
				success : function(data) {
					$("#" + showPageId).html(data);
				}
			});
			//移除样式
			$("#issp_goFirstPage_" + issp_commonPage_UUID_synchronized).removeClass("disabled");
			$("#issp_goPrePage_" + issp_commonPage_UUID_synchronized).removeClass("disabled");
			$("#issp_goNextPage_" + issp_commonPage_UUID_synchronized).removeClass("disabled");
			$("#issp_goLastPage_" + issp_commonPage_UUID_synchronized).removeClass("disabled");
		}
		//增加按钮样式
		issp_commonPage_btn_disabled(page, maxPages);
	}
</script>
