<!----
@description 数据权限配置
@author gongfanfei
@createData:2018/04/25
@editdate:2018/09/06
---->

<!-----内容区域---->
   <div class="information_size">
   		<div class="information-model">
   			<span class="Subtitle">
				<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;数据权限配置信息列表>>>
			</span>
   			<!-- 增加按钮 -->
			<a   href="javascript:void(-1);" onclick="dataauthoritymanage_sjqxzd.show_addsjqxzd()" class="btn btn-primary toLeft"> <i class="fa fa-plus"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>&nbsp;增加 
			</a> 
			<!-- 搜索框 -->
			<div class="zc_search find">
				  <input type="text" class="find-style"  id="sjqx_keywords" name="sjqx_keywords" placeholder="数据权限编号、名称">
				  <button id="queryBmzdBtn"onclick="dataauthoritymanage_sjqxzd.search_sjqx()"><i class="fa fa-search" aria-hidden="true"></i></button>
			</div>
   		</div>
		<div id="esjqxTable" class="Information_area">	</div>
   </div>

<!---添加数据权限信息-----> 
<div class="modal fade" id="modal-form-addsjqx"  tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content" style="width:750px;height:50vh;">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp;添加数据权限信息</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="addEsSjqx" name="addEsSjqx" class="form-horizontal">
                    <div class="form-group">
                    	<label class="col-sm-2 control-label">权限标识<span class="text-danger">*</span></label>
                        <div class="col-sm-4" id="addsjqxqxbhId">
                            <input type="text" id="addsjqx_f_qxbh" name="addsjqx_f_qxbh"  autocomplete="off" placeholder="请输入权限标识"  required class="form-control">
                        </div>
                        <label class="col-sm-2 control-label">权限名称<span class="text-danger">*</span></label>
                        <div class="col-sm-4">
                            <input type="text" id="addsjqx_f_qxmc" name="addsjqx_f_qxmc" autocomplete="off" placeholder="请输入权限名称"  required class="form-control">
                        </div>
                        <!--  <label class="col-sm-2 control-label">权限标志<span class="text-danger">*</span></label>
                        <div class="col-sm-4"> -->
                            <input type="hidden" id="addsjqx_f_qxbz" onclick="dataauthoritymanage_sjqxzd.show_addQxpz()" name="addsjqx_f_qxbz" placeholder="1/1/1/..."  required class="form-control">
                        <!-- </div> --><!-- dataauthoritymanage_sjqxzd.show_addQxpz() -->
                        
                    </div>
                    <div class="form-group">
                    <label class="col-sm-2 control-label">权限说明<span class="text-danger">*</span></label>
                        <div class="col-sm-4">
                            <input type="text" id="addsjqx_f_qxsm" onclick="dataauthoritymanage_sjqxzd.show_addQxpz()" name="addsjqx_f_qxsm" placeholder="/查询/编辑/添加/..." readonly="readonly"  required class="form-control">
                        </div>
                        <label class="col-sm-2 control-label">数据表名<span class="text-danger">*</span></label>
                        <div class="col-sm-4">
                            <input type="text" id="addsjqx_f_tabn" name="addsjqx_f_tabn" autocomplete="off" placeholder="请输入数据表名"   class="form-control">
                        </div>
                        
                    </div>
                    <div class="form-group">
                     <label class="col-sm-2 control-label">编号字段<span class="text-danger">*</span></label>
                        <div class="col-sm-4">
                            <input type="text" id="addsjqx_f_bhzd" name="addsjqx_f_bhzd" autocomplete="off" placeholder="请输入编号字段"   class="form-control">
                        </div>
                        <label class="col-sm-2 control-label">名称字段<span class="text-danger">&nbsp;&nbsp;</span></label>
                        <div class="col-sm-4">
                            <input type="text" id="addsjqx_f_mczd" name="addsjqx_f_mczd" autocomplete="off" placeholder="请输入名称字段"   class="form-control">
                        </div>
                        
                    </div>
                    <div class="form-group">
                    <label class="col-sm-2 control-label">编码结构<span class="text-danger">&nbsp;&nbsp;</span></label>
                        <div class="col-sm-4">
                            <input type="text" id="addsjqx_f_bmjg" name="addsjqx_f_bmjg" autocomplete="off" placeholder="请输入编码结构"   class="form-control">
                        </div>
                        <label class="col-sm-2 control-label">级数字段<span class="text-danger">&nbsp;&nbsp;</span></label>
                        <div class="col-sm-4">
                            <input type="text" id="addsjqx_f_jszd" name="addsjqx_f_jszd" autocomplete="off" placeholder="请输入级数字段"   class="form-control">
                        </div>
                        
                    </div>
                    <div class="form-group">
                    <label class="col-sm-2 control-label">明细字段<span class="text-danger">&nbsp;&nbsp;</span></label>
                        <div class="col-sm-4">
                            <input type="text" id="addsjqx_f_mxzd" name="addsjqx_f_mxzd" autocomplete="off" placeholder="请输入明细字段"   class="form-control">
                        </div>
                        <label class="col-sm-2 control-label">条件<span class="text-danger">&nbsp;&nbsp;</span></label>
                        <div class="col-sm-4">
                            <input type="text" id="addsjqx_f_wher" name="addsjqx_f_wher" autocomplete="off" placeholder="请输入条件"   class="form-control">
                        </div>
                        
                    </div>
                    <div class="form-group">
                    <label class="col-sm-2 control-label">用户权限表<span class="text-danger">&nbsp;&nbsp;</span></label>
                        <div class="col-sm-4">
                            <input type="text" id="addsjqx_f_user_qxb" name="addsjqx_f_user_qxb" autocomplete="off"   class="form-control">
                        </div>
                        <label class="col-sm-2 control-label" >角色权限表<span class="text-danger">&nbsp;&nbsp;</span></label>
                        <div class="col-sm-4">
                            <input type="text" id="addsjqx_f_role_qxb" name="addsjqx_f_role_qxb"  autocomplete="off"  class="form-control">
                        </div>
                        
                    </div>
                    
                    <div class="form-group">
                      <label class="col-sm-2 control-label">是否使用<span class="text-danger">&nbsp;&nbsp;</span></label>
                        <div class="col-sm-4" style="margin-top: 6px;">
	                        <input type="radio" name="add_sjqx_usemark" value="1" id="add_yes_sjqx" checked="checked">
						    <label class="radio-label" for="add_yes_sjqx"> 是 </label>
						    <input type="radio" name="add_sjqx_usemark" value="0"  id="add_not_sjqx" style="margin-left:30px;">
						    <label class="radio-label" for="add_not_sjqx"> 否 </label>
                        </div>
                    </div>
                    
                    
                  
                    <div class="form-group m-t-sm" >
                        <div class="col-sm-6 col-sm-push-3 display_flex">
                            <button class="btn btn-md btn-primary" type="submit"><strong><i class="fa fa-check" aria-hidden="true"></i>&nbsp;确定</strong></button>
                            <button class="btn btn-white m-l-sm"  type="button" data-dismiss="modal"><i class="fa fa-times" aria-hidden="true"></i>&nbsp;取消</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<!---权限配置模态框-----> 
<div class="modal fade" id="modal-form-addQxpz"  tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content" style="width:38vh;height:50vh;">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp;权限配置</h4>
            </div>
            <div class="modal-body" style="">
			    <div style="width:100%;">        
			       <div style=""><label>权限一：</label><input id="sjqxzd_fg1" style="margin-left: 1.5vw;width: 140px;display: inline;" type="text" placeholder="请填写权限说明..."   class="form-control"></div> 
			       <div style="margin-top:5px;"><label>权限二：</label><input id="sjqxzd_fg2" style="margin-left: 1.5vw;width: 140px;display: inline;" type="text"   placeholder="请填写权限说明..."   class="form-control"></div> 
			       <div style="margin-top:5px;"><label> 权限三：</label><input id="sjqxzd_fg3" style="margin-left: 1.5vw;width: 140px;display: inline;" type="text" placeholder="请填写权限说明..."   class="form-control"></div> 
			       <div style="margin-top:5px;"><label> 权限四：</label><input id="sjqxzd_fg4" style="margin-left: 1.5vw;width: 140px;display: inline;" type="text" placeholder="请填写权限说明..."   class="form-control"></div> 
			       <div style="margin-top:5px;"><label>权限五：</label><input id="sjqxzd_fg5" style="margin-left: 1.5vw;width: 140px;display: inline;" type="text"  placeholder="请填写权限说明..."   class="form-control"></div> 
			       <div style="margin-top:5px;"><label>权限六：</label><input id="sjqxzd_fg6" style="margin-left: 1.5vw;width: 140px;display: inline;" type="text"  placeholder="请填写权限说明..."   class="form-control"></div> 
			       <div style="margin-top:5px;"><label>权限七：</label><input id="sjqxzd_fg7" style="margin-left: 1.5vw;width: 140px;display: inline;" type="text"  placeholder="请填写权限说明..."   class="form-control"></div> 
			       <div style="margin-top:5px;"><label>权限八：</label><input id="sjqxzd_fg8" style="margin-left: 1.5vw;width: 140px;display: inline;" type="text"  placeholder="请填写权限说明..."   class="form-control"></div> 
			    <div class="form-group m-t-sm" style="margin-top:4vh;" >
                        <div class="col-sm-6 col-sm-push-3 display_flex">
                            <button onclick="dataauthoritymanage_sjqxzd.addeightqxpz()" class="btn btn-md btn-primary" type="submit"><strong>确定</strong></button>
                        </div>
                </div>
			    </div>
            </div>
        </div>
    </div>
</div>
<!----编辑数据权限--->
<div class="modal fade" id="editSjqxForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content" style="width:750px;height:50vh;">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title editIcon">&nbsp;编辑数据权限信息</h4>
            </div>
            <div class="modal-body">
            	<form id="edit_SjqxForm" name="edit_SjqxForm" class="form-horizontal">
					<div class="form-group">
						<label class="col-sm-2 control-label" for="edit_f_qxmc">权限标识<span class="text-danger">*</span></label>
						<div class="col-sm-4">	
							<input type="text" id="edit_f_qxbh" name="edit_f_qxbh" autocomplete="off" required class="form-control" readonly="readonly">
						</div>
						<label class="col-sm-2 control-label" for="edit_f_qxmc">权限名称<span class="text-danger">*</span></label>
						<div class="col-sm-4">
							<input type="text" id="edit_f_qxmc" name="edit_f_qxmc" autocomplete="off" required class="form-control" >
						</div>
							<input type="hidden" id="edit_f_qxbz"  name="edit_f_qxbz"  required class="form-control">
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="edit_f_qxsm">权限说明<span class="text-danger">*</span></label>
						<div class="col-sm-4" style="font-size:10px;">
							<input type="text" id="edit_f_qxsm" name="edit_f_qxsm" autocomplete="off" onclick="dataauthoritymanage_sjqxzd.show_editQxpz()" readonly="readonly"  required class="form-control">
						</div>
						<label class="col-sm-2 control-label" for="edit_f_tabn">数据表名<span class="text-danger">*</span></label>
						<div class="col-sm-4">
							<input type="text" id="edit_f_tabn" name="edit_f_tabn" autocomplete="off"  class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="edit_f_bhzd">编码字段<span class="text-danger">*</span></label>
						<div class="col-sm-4">
							<input type="text" id="edit_f_bhzd" name="edit_f_bhzd" autocomplete="off"  class="form-control">
						</div>
						<label class="col-sm-2 control-label" for="edit_f_mczd">名称字段</label>
						<div class="col-sm-4">
							<input type="text" id="edit_f_mczd" name="edit_f_mczd" autocomplete="off"  class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="edit_f_bmjg">编码结构</label>
						<div class="col-sm-4">
							<input type="text" id="edit_f_bmjg" name="edit_f_bmjg" autocomplete="off"  class="form-control">
						</div>
						<label class="col-sm-2 control-label" for="edit_f_jszd">级数字段</label>
						<div class="col-sm-4">
							<input type="text" id="edit_f_jszd" name="edit_f_jszd" autocomplete="off"  class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="edit_f_mxzd">明细字段</label>
						<div class="col-sm-4">
							<input type="text" id="edit_f_mxzd" name="edit_f_mxzd" autocomplete="off"  class="form-control">
						</div>
						<label class="col-sm-2 control-label" for="edit_f_wher">条件</label>
						<div class="col-sm-4">
							<input type="text" id="edit_f_wher" name="edit_f_wher" autocomplete="off"  class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="edit_f_user_qxb">用户权限表</label>
						<div class="col-sm-4">
							<input type="text" id="edit_f_user_qxb" name="edit_f_user_qxb" autocomplete="off"  class="form-control">
						</div>
						<label class="col-sm-2 control-label" for="edit_f_role_qxb">角色权限表</label>
						<div class="col-sm-4">
							<input type="text" id="edit_f_role_qxb" name="edit_f_role_qxb" autocomplete="off"  class="form-control">
						</div>
					</div>
					<div class="form-group">
					    <label class="col-sm-2 control-label">是否使用</label>
					    <div class="col-sm-4" style="margin-top: 6px;">
					         <input type="radio" class="edit_radio" name="edit_sjqx_usemark" value="1" id="edit_yes_sjqx" checked="checked">
							 <label class="radio-label" for="edit_yes_sjqx"> 是 </label>
							 <input type="radio" class="edit_radio" name="edit_sjqx_usemark" value="0"  id="edit_not_sjqx" style="margin-left:30px;">
							 <label class="radio-label" for="edit_not_sjqx"> 否 </label>
					     </div>
					</div>
					<div class="form-group m-t-sm">
						<div class="col-sm-6 col-sm-push-3 display_flex">
							<button class="btn btn-md btn-primary " type="submit">
								<strong><i class="fa fa-check" aria-hidden="true"></i>&nbsp;确定</strong>
							</button>
							<button type="button" class="btn btn-white m-l-sm" data-dismiss="modal"><i class="fa fa-times" aria-hidden="true"></i>&nbsp;取消</button>
						</div>
					</div>
            	</form>
            </div>
        </div>
    </div>
</div>
<!---编辑数据权限——权限配置-----> 
<div class="modal fade" id="modal-form-editQxpz"  tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content" style="width:38vh;height:50vh;">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp;权限配置</h4>
            </div>
            <div class="modal-body">
			    <div class="sjqxzd_divClass" style="width:100%;">        
			       <div class="display_flex" style=""><label>权限一：</label><input id="edit_sjqxzd_fg1" style="margin-left: 1.5vw;width: 140px;display: inline;" type="text" placeholder="请填写权限说明..."   class="form-control"></div> 
			       <div class="display_flex" style="margin-top:5px;"><label>权限二：</label><input id="edit_sjqxzd_fg2" style="margin-left: 1.5vw;width: 140px;display: inline;" type="text"   placeholder="请填写权限说明..."   class="form-control"></div> 
			       <div class="display_flex" style="margin-top:5px;"><label> 权限三：</label><input id="edit_sjqxzd_fg3" style="margin-left: 1.5vw;width: 140px;display: inline;" type="text" placeholder="请填写权限说明..."   class="form-control"></div> 
			       <div class="display_flex" style="margin-top:5px;"><label> 权限四：</label><input id="edit_sjqxzd_fg4" style="margin-left: 1.5vw;width: 140px;display: inline;" type="text" placeholder="请填写权限说明..."   class="form-control"></div> 
			       <div class="display_flex" style="margin-top:5px;"><label>权限五：</label><input id="edit_sjqxzd_fg5" style="margin-left: 1.5vw;width: 140px;display: inline;" type="text"  placeholder="请填写权限说明..."   class="form-control"></div> 
			       <div class="display_flex" style="margin-top:5px;"><label>权限六：</label><input id="edit_sjqxzd_fg6" style="margin-left: 1.5vw;width: 140px;display: inline;" type="text"  placeholder="请填写权限说明..."   class="form-control"></div> 
			       <div class="display_flex" style="margin-top:5px;"><label>权限七：</label><input id="edit_sjqxzd_fg7" style="margin-left: 1.5vw;width: 140px;display: inline;" type="text"  placeholder="请填写权限说明..."   class="form-control"></div> 
			       <div class="display_flex" style="margin-top:5px;"><label>权限八：</label><input id="edit_sjqxzd_fg8" style="margin-left: 1.5vw;width: 140px;display: inline;" type="text"  placeholder="请填写权限说明..."   class="form-control"></div> 
			    </div>
			    <div class="form-group m-t-sm" style="margin-top:4vh;" >
                     <div class="col-sm-6 col-sm-push-3 display_flex">
                         <button onclick="dataauthoritymanage_sjqxzd.editeightqxpz()" class="btn btn-md btn-primary" type="submit"><strong>确定</strong></button>
                     </div>
                </div>
            </div>
        </div>
    </div>
</div>

 <script type="text/javascript">
 ; var dataauthoritymanage_sjqxzd = (function($, window, document, undefined) {
 	var _ctx = '${ctx}';
	var _curRow = null;

	//设置格式
	var optIconFunction = function(cell, formatterParams){
		var fqxbh = cell.getRow().getData().f_qxbh;
		return "<div class='btn-group '>"
		+"<button class='btn btn-white btn-sm edit' data-id="+ fqxbh + " data-toggle='modal' data-target='#editSjqxForm'><i class='fa fa-pencil' ></i> 编辑</button>"
		+"<button class='btn btn-white btn-sm delete' data-id=" + fqxbh + "><i class='fa fa-trash'></i>  删除</button></div>"
	};
	
	//创建并设置table属性
	$("#esjqxTable").tabulator({
		height:"100%",
		layout:"fitColumns",
		columnVertAlign:"bottom",
		tooltips:true,
		//selectable:true,
		movableColumns:false,
		columns:[
		{title:"序号",field:"id",width:50,formatter:"rownum",frozen:false,headerSort:false,align:"center",tooltip:false,tooltipsHeader:false},
		{title:"权限标识", field:"f_qxbh", sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"权限名称", field:"f_qxmc",sorter:"string",editor:false,headerSort:false,align:"left"},
		{title:"权限标志", field:"f_qxbz",sorter:"string",editor:false,headerSort:false,align:"left"},
		{title:"权限说明", field:"f_qxsm",sorter:"string",editor:false,headerSort:false,align:"left"},
		{title:"数据表名", field:"f_tabn",sorter:"string",editor:false,headerSort:false,align:"left"},
		{title:"编码字段", field:"f_bhzd",sorter:"string",editor:false,headerSort:false,align:"left"},
		{title:"名称字段", field:"f_mczd",sorter:"string",editor:false,headerSort:false,align:"left"},
		//{title:"编码结构", field:"f_bmjg",width:80,sorter:"string",editor:false,headerSort:false,align:"center"},
		//{title:"级数字段", field:"f_jszd",width:80,sorter:"string",editor:false,headerSort:false,align:"center"},
		//{title:"明细字段", field:"f_mxzd",width:80,sorter:"string",editor:false,headerSort:false,align:"center"},
		{title:"条件", field:"f_wher",sorter:"string",editor:false,headerSort:false,align:"left"},
		{title:"是否使用", field:"f_sfsy",sorter:"string",editor:false,headerSort:false,align:"center"},
		{title:"用户权限表", field:"f_user_qxb",sorter:"string",editor:false,headerSort:false,align:"left"},
		//{title:"用户组权限表", field:"f_yhz_qxb",width:120,sorter:"string",editor:false,headerSort:false,align:"center"},
		{title:"角色权限表", field:"f_role_qxb",sorter:"string",editor:false,headerSort:false,align:"left"},
		//{title:"岗位权限表", field:"f_gw_qxb",width:120,sorter:"string",editor:false,headerSort:false,align:"center"},
		//{title:"创建时间", field:"f_crdate",sorter:"date",align:"center",editable:false,headerSort:false},
		//{title:"修改时间", field:"f_chdate",sorter:"date",align:"center",editor:false,headerSort:false},
		{title:"操作", field:"opt",width:150,tooltip:false,tooltipsHeader:false,align:"left",formatter:optIconFunction,headerSort:false},
		],
		rowClick:function(e, row){
        	_curRow = row;
    	},
	});
	
	//填充数据
	//$("#esjqxTable").tabulator("setData", _ctx+'/view/sjqx/loadAllSjqx');

	$(window).resize(function(){
		$("#esjqxTable").tabulator("redraw");
	});
	
	//触发搜索的回车时间
	$("#sjqx_keywords").focus(function(){
					  $(this).keydown(function (e){
					  if(e.which == "13"){
						  dataauthoritymanage_sjqxzd.search_sjqx();//触发该事件
					    } 
					  })
					});
	//添加组织机构表单验证
    var sjqxValidator = $("#addEsSjqx").validate({
	     rules: {
	    	 
	    	 addsjqx_f_qxbh: {
	                required: true,
	                rangelength: [1, 50],
	                isNormal: true
	            },
	    	 addsjqx_f_qxmc: {
                required: true,
                rangelength: [1, 20],
            },
            addsjqx_f_qxsm: {
                required: true,
                rangelength: [1, 20],
            },
            addsjqx_f_tabn: {
                required: true,
                rangelength: [1, 20],
            },
            addsjqx_f_bhzd: {
                required: true,
                rangelength: [1, 20],
            },
        },
        messages: {
        	
        	addsjqx_f_qxbh: {
                required: "请输入数据标识名称",
                     rangelength: jQuery.validator.format("应为1-50位的英文字母、数字字符"),
                     remote: jQuery.validator.format("{0} is already in use")
             },
        	addsjqx_f_qxmc: {
               required: "请输入数据权限名称",
                    rangelength: jQuery.validator.format("应为1-20位的英文字母、数字字符"),
                    remote: jQuery.validator.format("{0} is already in use")
            },
            addsjqx_f_qxsm: {
               required: "请输入权限说明",
                    rangelength: jQuery.validator.format("应为1-20位的英文字母、数字字符"),
                    remote: jQuery.validator.format("{0} is already in use")
            },
            addsjqx_f_tabn: {
               required: "请输入数据表名称",
                    rangelength: jQuery.validator.format("应为1-20位的英文字母、数字字符"),
                    remote: jQuery.validator.format("{0} is already in use")
            },
            addsjqx_f_bhzd: {
               required: "请输入编号字段",
                    rangelength: jQuery.validator.format("应为1-20位的英文字母、数字字符"),
                    remote: jQuery.validator.format("{0} is already in use")
            },
        },
	     submitHandler: function (form) {
	         addform_EsSjqx(form);
	         
	     }
 	});
    //保存数据权限
	function addform_EsSjqx(form) {

		var f_sfsy = $("input[name='add_sjqx_usemark']:checked")[0].id=="add_yes_sjqx"?"1":"0";
	       $.ajax({
		      url: _ctx + "/view/sjqx/data_sjqx_add",
		      contentType: "application/json; charset=utf-8",
	          type: "post",
	          data:JSON.stringify({
	        	  	f_qxbh: $("#addsjqx_f_qxbh").val(),
		      		f_qxmc: $("#addsjqx_f_qxmc").val(),
		      		f_qxbz: $("#addsjqx_f_qxbz").val(),
		      		f_qxsm: $("#addsjqx_f_qxsm").val(),
		      		f_tabn: $("#addsjqx_f_tabn").val(),
		      		f_bhzd: $("#addsjqx_f_bhzd").val(),
		      		f_mczd: $("#addsjqx_f_mczd").val(),
		      		f_bmjg: $("#addsjqx_f_bmjg").val(),
		      		f_jszd: $("#addsjqx_f_jszd").val(),
		      		f_mxzd: $("#addsjqx_f_mxzd").val(),
		      		f_wher: $("#addsjqx_f_wher").val(),
		      		f_user_qxb: $("#addsjqx_f_user_qxb").val(),
		      		f_role_qxb: $("#addsjqx_f_role_qxb").val(),
		      		f_sfsy: f_sfsy
		      }),
		      beforeSend: function () { 
	 				showLoad();	             
	 			},
			success: function(data) {
	         if (data.status == '1') {
               $('#modal-form-addsjqx').modal('hide');//关闭编辑窗口
	           swal({
		        	title : data.msg,// 展示的标题
		   			text : "",// 内容
		   			type: "success",
		   			showCloseButton : false, // 展示关闭按钮
		   			allowOutsideClick : false,
		   			showConfirmButton : false,
		   			timer: 1000
		   		});
		            //在表格中添加数据
		            $('#esjqxTable').tabulator("addRow", {
		            	f_qxbh:data.data.f_qxbh,
		            	f_qxmc:data.data.f_qxmc, 
		            	f_qxbz:data.data.f_qxbz, 
		            	f_qxsm:data.data.f_qxsm,
		            	f_tabn:data.data.f_tabn,
		            	f_bhzd:data.data.f_bhzd,
		            	f_mczd:data.data.f_mczd,
		            	f_bmjg:data.data.f_bmjg,
		            	f_jszd:data.data.f_jszd,
		            	f_mxzd:data.data.f_mxzd,
		            	f_wher:data.data.f_wher,
		            	f_sfsy:data.data.f_sfsy,
		            	f_user_qxb:data.data.f_user_qxb,
		            	f_role_qxb:data.data.f_role_qxb,
		            	f_crdate:data.data.f_crdate,
		            	f_chdate:data.data.f_chdate
		            	});
	         } else {

	        //	$("#addsjqxqxbhId").append("<div><span style='color:red'>已存在</span></div>");
	           swal( data.msg, "", "error");
	         }
	       },
	       complete: function () {
				hiddenLoad();
			},
	       error: function(data) {
	       	 swal( data.msg,"", "error");
	       }
	     });
	     return false;
 	}
 	//添加数据权限---居中显示（添加）
 	$('#modal-form-addsjqx').on('show.bs.modal', function () {
 		// 关键代码，如没将modal设置为 block，则$modala_dialog.height() 为零  
        $(this).css('display', 'block');  
        var  modalHeight=$(window).height() / 2 - $('#modal-form-addsjqx .modal-dialog').height() / 2;  
        $(this).find('.modal-dialog').css({  
            'margin-top': modalHeight  
        }); 
        $(this).find('.modal-dialog').css({  
            'margin-left':'60vh'
        }); 
	});
 	//添加权限配置---居中显示（添加）
 	 $('#modal-form-addQxpz').on('show.bs.modal', function () {
 		// 关键代码，如没将modal设置为 block，则$modala_dialog.height() 为零  
        $(this).css('display', 'block');  
      	var  modalHeight=$(window).height() / 2 - $('#modal-form-addQxpz .modal-dialog').height() / 2;  
        $(this).find('.modal-dialog').css({  
            'margin-top': modalHeight  
        });
        $(this).find('.modal-dialog').css({  
            'margin-left':'140vh'
        }); 
	});
 	//编辑数据权限---居中显示（编辑）
 	$('#editSjqxForm').on('show.bs.modal', function () {
        $(this).css('display', 'block');  
        var modalHeight=$(window).height() / 2 - $('#editSjqxForm .modal-dialog').height() / 2;  
        $(this).find('.modal-dialog').css({  
            'margin-top': modalHeight  
        }); 
        $(this).find('.modal-dialog').css({  
            'margin-left':'60vh'
        }); 
	});
 	//编辑权限配置---居中显示（编辑）
	 $('#modal-form-editQxpz').on('show.bs.modal', function () {
		// 关键代码，如没将modal设置为 block，则$modala_dialog.height() 为零  
       $(this).css('display', 'block');  
     	var  modalHeight=$(window).height() / 2 - $('#modal-form-editQxpz .modal-dialog').height() / 2;  
       $(this).find('.modal-dialog').css({  
           'margin-top': modalHeight  
       });
       $(this).find('.modal-dialog').css({  
           'margin-left':'140vh'
       }); 
	});
	//关闭模态框清空表单值
    $("#modal-form-addsjqx").on('hidden.bs.modal', function (event) {
        $(this).find("input").val("");
        sjqxValidator.resetForm();  
    });
    //删除数据权限
    $(document).on('click','#esjqxTable button.delete', function () {
        var id=$(this).data("id");
	    swal({ 
	            title: "您确定要删除吗?",
	            text: "信息删除后将不可恢复!",
	            type: "warning",
	            showCancelButton: true,
	            confirmButtonColor: "#1ab394",
	            confirmButtonText: "确定",
	            closeOnConfirm: false
	        }, function () {
            	setTimeout(function(){
	            	$.ajax({
			          url: _ctx + "/view/sjqx/data_sjqx_del",
			          contentType: "application/json; charset=utf-8",
			          type: "post",
			          data:JSON.stringify({     
			        			f_qxbh:id
			    	  }),
			    	  beforeSend: function () { 
			 				showLoad();	             
			 			},
		          	  success: function(data) {
			              	if (data.status == '1') {
			              		swal({
						        	title : data.msg,// 展示的标题
						   			text : "",// 内容
						   			type: "success",
						   			showCloseButton : false, // 展示关闭按钮
						   			allowOutsideClick : false,
						   			showConfirmButton : false,
						   			timer: 1000
						   		});
			              		$("#esjqxTable").tabulator("deleteRow", _curRow); 
			              		var getTable = $("#esjqxTable").tabulator("getData");
			              		$("#esjqxTable").tabulator("setData", getTable);
				            } else{
				                swal(data.msg,"", "error");
				            }
			          },
			          complete: function () {
							hiddenLoad();
					  },
			          error: function(data) {
			          	 swal( data.msg,"", "error");
			          }
			        });
                },100
            )
        });
    });
    
   //编辑数据权限表单验证
  $("#edit_SjqxForm").validate({
	  rules: {
		  edit_f_qxbh: {
	             required: true,
	             rangelength: [1, 20],
	         },
		  edit_f_qxmc: {
             required: true,
             rangelength: [1, 20],
         },
         edit_f_qxsm: {
             required: true,
             rangelength: [1, 20],
         },
         edit_f_tabn: {
             required: true,
             rangelength: [1, 20],
         },
         edit_f_bhzd: {
             required: true,
             rangelength: [1, 20],
         },
     },
     messages: {
    	 edit_f_qxbh: {
             required: "数据标识名称不能为空",
                  rangelength: jQuery.validator.format("应为1-20位的英文字母、数字字符"),
                  remote: jQuery.validator.format("{0} is already in use")
          },
    	 edit_f_qxmc: {
            required: "数据权限名称不能为空",
                 rangelength: jQuery.validator.format("应为1-20位的英文字母、数字字符"),
                 remote: jQuery.validator.format("{0} is already in use")
         },
         edit_f_qxsm: {
            required: "权限说明不能为空",
                 rangelength: jQuery.validator.format("应为1-20位的英文字母、数字字符"),
                 remote: jQuery.validator.format("{0} is already in use")
         },
         edit_f_tabn: {
            required: "数据表名称不能为空",
                 rangelength: jQuery.validator.format("应为1-20位的英文字母、数字字符"),
                 remote: jQuery.validator.format("{0} is already in use")
         },
         edit_f_bhzd: {
            required: "编号字段不能为空",
                 rangelength: jQuery.validator.format("应为1-20位的英文字母、数字字符"),
                 remote: jQuery.validator.format("{0} is already in use")
         },
     },
     submitHandler: function(form) {
	      editSjqxFormFunc(form);
	    }
  });
  //编辑数据权限，提交form表单
  function editSjqxFormFunc(form) {
	  var f_sfsy = $("input[name='edit_sjqx_usemark']:checked").val();

    $.ajax({
      url: _ctx + "/view/sjqx/sjqx_edit",
      type: "post",
      data: ({
	        f_qxbh: $("#edit_f_qxbh").val(),
   			f_qxmc: $("#edit_f_qxmc").val(),
			f_qxbz:	$("#edit_f_qxbz").val(),
			f_qxsm:	$("#edit_f_qxsm").val(),
			f_tabn:	$("#edit_f_tabn").val(),
			f_bhzd:	$("#edit_f_bhzd").val(),
			f_mczd:	$("#edit_f_mczd").val(),
			f_bmjg:	$("#edit_f_bmjg").val(),
			f_jszd:	$("#edit_f_jszd").val(),
			f_mxzd:	$("#edit_f_mxzd").val(),
			f_wher:	$("#edit_f_wher").val(),
			f_user_qxb:$("#edit_f_user_qxb").val(),
			f_role_qxb:$("#edit_f_role_qxb").val(),
			f_sfsy:	f_sfsy
      }),
      beforeSend: function () { 
			showLoad();	             
	  },
      success: function(data) {
			if (data.status == '1') {
              $('#editSjqxForm').modal('hide');//关闭编辑窗口
              swal({
		        	title : data.msg,// 展示的标题
		   			text : "",// 内容
		   			type: "success",
		   			showCloseButton : false, // 展示关闭按钮
		   			allowOutsideClick : false,
		   			showConfirmButton : false,
		   			timer: 1000
		   		});
	         $('#esjqxTable').tabulator("updateRow",_curRow, {
	        	 f_qxbh:data.data.f_qxbh, 
	        	 f_qxmc:data.data.f_qxmc,
	        	 f_qxbz:data.data.f_qxbz,
	        	 f_qxsm:data.data.f_qxsm,
	        	 f_tabn:data.data.f_tabn,
	        	 f_bhzd:data.data.f_bhzd,
	        	 f_mczd:data.data.f_mczd,
	        	 f_bmjg:data.data.f_bmjg,
	        	 f_jszd:data.data.f_jszd,
	        	 f_mxzd:data.data.f_mxzd,
	        	 f_wher:data.data.f_wher,
	        	 f_user_qxb:data.data.f_user_qxb,
	        	 f_role_qxb:data.data.f_role_qxb,
	        	 f_sfsy:data.data.f_sfsy=='1'?'是':'否',
	        	 f_chdate:data.data.f_chdate});
            }else{
              swal("修改失败!", data.msg, "error");
            }
          },
          complete: function () {
				hiddenLoad();
			},
          error: function(data) {
          	 swal("修改失败!", data.msg, "error");
          }
    });
  }
  
  //验证在模态框出现前加载
  $("#editSjqxForm").on('show.bs.modal', function(event) {
    var id = _curRow.getData().f_qxbh;
    $.ajax({
	       url: _ctx + "/view/sjqx/loadeditobj",
	        type: "post",
           contentType: "application/json; charset=utf-8",
	       data:JSON.stringify({     
	 			f_qxbh:id
	 		}),
	 		beforeSend: function () { 
 				showLoad();	             
 			},
	       success: function(result) {
	    	 $(":radio[name='edit_sjqx_usemark'][value='" + result.f_sfsy + "']").prop("checked", "checked");
	         $("#edit_f_qxbh").val(result.f_qxbh);
	         $("#edit_f_qxmc").val(result.f_qxmc);
	         //$("#edit_f_xtbh").val(result.f_xtbh);
	         $("#edit_f_qxbz").val(result.f_qxbz);
	         $("#edit_f_qxsm").val(result.f_qxsm);
	         $("#edit_f_tabn").val(result.f_tabn);
	         $("#edit_f_bhzd").val(result.f_bhzd);
	         $("#edit_f_mczd").val(result.f_mczd);
	         $("#edit_f_bmjg").val(result.f_bmjg);
	         $("#edit_f_jszd").val(result.f_jszd);
	         $("#edit_f_mxzd").val(result.f_mxzd);
	         $("#edit_f_wher").val(result.f_wher);
	         $("#edit_f_user_qxb").val(result.f_user_qxb);
	         $("#edit_f_role_qxb").val(result.f_role_qxb);
	         //$("#edit_f_sfsy").val(result.f_sfsy);
	         },
	         complete: function () {
					hiddenLoad();
				},
    	});  
  	});
	//添加数据权限配置设置模态框属性
	$("#modal-form-addQxpz").on('show.bs.modal', function(event) {
		//模态拖动
		$(this).draggable({
			handle : ".modal-header" // 只能点击头部拖动
		});
		$(this).css("overflow", "hidden"); // 防止出现滚动条，出现的话，你会把滚动条一起拖着走的
		//填充权限
		var f_qxsm = $("#addsjqx_f_qxsm").val();
		var smArray = f_qxsm.split("/");
		for (var i = 0; i < smArray.length; i++) {
			if(smArray[i] !="无"){
				$("#sjqxzd_fg"+(i+1)).val(smArray[i]); 
			}else{
				$("#sjqxzd_fg"+(i+1)).val("");
			}
		}
	});
	//编辑数据权限配置设置模态框属性
	$("#modal-form-editQxpz").on('show.bs.modal', function(event) {
		$(this).draggable({
			handle : ".modal-header" 
		});
		$(this).css("overflow", "hidden");
		//填充权限
		var f_qxsm = $("#edit_f_qxsm").val();
		var smArray = f_qxsm.split("/");
		for (var i = 0; i < smArray.length; i++) {
			if(smArray[i] !="无"){
				$("#edit_sjqxzd_fg"+(i+1)).val(smArray[i]); 
			}else{
				$("#edit_sjqxzd_fg"+(i+1)).val("");
			}
		}
	});
	
	
  	return{
	  //配置权限标志
		 sjqx_qxpz_opt:function(id){
			var f_qxbz = "";//权限标志
			var f_qxsm = "";//权限说明
			for (var i = 1; i < 9; i++) {
				var sm_temp = $("#"+id+i).val();
				if(sm_temp!=""){sm_temp = sm_temp.replace(/\s+/g,"");}
				var bz_temp = "1";
				if(sm_temp == ""){
					bz_temp = "";
					f_qxsm = f_qxsm + sm_temp;
					f_qxbz = f_qxbz + bz_temp;
				}else{
					f_qxsm = f_qxsm +"/"+ sm_temp;
					f_qxbz = f_qxbz +"/" + bz_temp;
				}
			}
			 
			f_qxbz = f_qxbz.substring(1); 
			f_qxsm = f_qxsm.substring(1); 
			var array = new Array();
			array[0] = f_qxbz;
			array[1] = f_qxsm;
			return array ; 
		 },
	  //配置权限标志
		 addeightqxpz:function(){
			var array = dataauthoritymanage_sjqxzd.sjqx_qxpz_opt("sjqxzd_fg");
			$("#addsjqx_f_qxbz").val(array[0]);
			$("#addsjqx_f_qxsm").val(array[1]);
			$('#modal-form-addQxpz').modal('hide');//关闭编辑窗口 
		 },
	  	//配置权限标志
		 editeightqxpz:function(){
		 	var array = dataauthoritymanage_sjqxzd.sjqx_qxpz_opt("edit_sjqxzd_fg"); 
			$("#edit_f_qxbz").val(array[0]);
			$("#edit_f_qxsm").val(array[1]);
			$('#modal-form-editQxpz').modal('hide');//关闭编辑窗口 
		 },
		//搜索
		search_sjqx:function(){
			var sjqx_keywords = $("#sjqx_keywords").val();
			$("#esjqxTable").tabulator("setData", _ctx+'/view/sjqx/data_sjqx_search?keywords='+sjqx_keywords);
		},
		//添加数据权限 --- 权限说明/标识
		show_addQxpz:function() {
			for (var i = 1; i < 9; i++) {
				$("#sjqxzd_fg"+i).val("");
			}
			$('#modal-form-addQxpz').modal('show'); 
	    },
		//编辑数据权限----权限说明/标识
		show_editQxpz:function() {
			for (var i = 1; i < 9; i++) {
				$("#edit_sjqxzd_fg"+i).val("");
			}
			$('#modal-form-editQxpz').modal('show'); 
	    },
		//验证增加模态框是否弹出
		show_addsjqxzd:function() {
			$.ajax({
				url: _ctx + "/view/sjqx/loadqxpztable",
		        type: "post",
		        success: function(result) {
		        	$("#addsjqx_f_user_qxb").val(result.list[0]);
		        	$("#addsjqx_f_role_qxb").val(result.list[1]);
		        }
			});
			
			$('#modal-form-addsjqx').modal('show');
			for (var i = 1; i < 9; i++) {
				$("#sjqxzd_fg"+i).val("");
			}
	    },
	  	//初始加载函数
		pageInit : function(){
			$.ajax({
		        type: "get",
		        url: _ctx+'/view/sjqx/loadAllSjqx',
		        dataType: "json",
		        beforeSend: function () { 
      				showLoad();	             
      			},
		        success: function (result) {
		        	 $("#esjqxTable").tabulator("setData", result.list);
		        },
		        complete: function () {
     				hiddenLoad();
     			},
		        error: function (nodeData) {
		            swal("查询失败","", "error");
		        } 
    		});
		} 
  }
 })(jQuery, window, document);
 dataauthoritymanage_sjqxzd.pageInit();
 </script>