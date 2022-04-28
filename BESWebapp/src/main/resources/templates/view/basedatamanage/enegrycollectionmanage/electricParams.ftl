<!-----内容区域---->
<!-- 组织机构树模块 -->
<div class="leftarea information_left" id="leftElectricPconf">
	<div class="information-model">
		<span class="tree_Subtitle">
			<i class="fa fa-share-alt" aria-hidden="true"></i>&nbsp;请选择能源类型>>>
		</span>
	</div>
	<div id="tree_ElectricP" class="Information_area"></div>
</div>
<!-- 组织机构树模块end -->

<!-- 信息表格模块 -->
<div class="information_right">
	<div class="information_size">
		<div class="information-model">
			<span class="Subtitle">
				<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;采集参数定义列表>>>
			</span>
			<!-- 增加按钮 -->
			<a id="addelElectricP" href="javascript:void(-1);" onclick="basedatamanage_enegrycollectionmanage_electricParams.show_addElectricP();" class="btn btn-primary toLeft"> 
			<i class="fa fa-plus"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>增加 
			</a> 
			<!-- 搜索框 -->
			<div class="zc_search find">
				  <input type="text" class="find-style"  id="ElectricPInfo" name="ElectricPInfo" placeholder="能耗名称、编号">
				  <button id="queryElectricP" onclick="basedatamanage_enegrycollectionmanage_electricParams.searchElectricP()"><i class="fa fa-search" aria-hidden="true"></i></button>
			</div>
		</div>
		<div id="ElectricPTable" class="Information_area"></div>
	</div>
</div>
<!-- 信息表格模块end -->

<!---添加采集参数开始-----> 
<div class="modal fade" id="modal-form-addElectricP" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp;添加采集参数</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="addElectricPform" name="addElectricPform" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">能耗名称<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="fDnmc" name="fDnmc" placeholder="请输入能耗名称"  required class="form-control">
                        </div>
                    </div>      
                    <div class="form-group">
                        <label class="col-sm-3 control-label">偏移地址<span class="text-danger">*</span><span class="text-danger">&nbsp;</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="fPydz" name="fPydz" placeholder="请输入十六进制的偏移地址" class="form-control">
                        </div>
                    </div> 
                    <div class="form-group">
                        <label class="col-sm-3 control-label">编码规则<span class="text-danger">*</span><span class="text-danger">&nbsp;</span></label>
                        <div class="col-sm-8">
							<select id="fBmgz" name="fBmgz" required class="form-control">

							</select>
                        </div>
                    </div> 
                    <div class="form-group">
                        <label class="col-sm-3 control-label">数据类型<span class="text-danger">*</span><span class="text-danger">&nbsp;</span></label>
                        <div class="col-sm-8">
							<select id="fDataType" name="fDataType" required class="form-control">
								<option value="0">int</option>
								<option value="1">float</option>
								<option value="2">double</option>
							</select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">寄存器数量<span class="text-danger">*</span><span class="text-danger">&nbsp;</span></label>
                        <div class="col-sm-8">
                            <select id="fRegisterNumber"  name="fRegisterNumber" required class="form-control">
                                <option value="0">1</option>
                                <option value="1">2</option>
                                <option value="2">3</option>
                                <option value="3">4</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">解码顺序<span class="text-danger">*</span><span class="text-danger">&nbsp;</span></label>
                        <div class="col-sm-8" id="codeSeqSelect">
							<select id="fCodeSeq" name="fCodeSeq" required class="form-control">
								<option value="0">12</option>
								<option value="1">21</option>
							</select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">单位<span class="text-danger">*</span><span class="text-danger">&nbsp;</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="fUnit" name="fUnit" class="form-control">
                        </div>
                    </div> 
                    <div class="form-group">
                        <label class="col-sm-3 control-label">小数点位置<span class="text-danger">*</span><span class="text-danger">&nbsp;</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="fScalingPosition" name="fScalingPosition" class="form-control">
                        </div>
                    </div> 
                    <div class="form-group">
                        <label class="col-sm-3 control-label">数据长度<span class="text-danger">*</span><span class="text-danger">&nbsp;</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="fDataLength" name="fDataLength" class="form-control">
                        </div>
                    </div> 
                    <div class="form-group">
                        <label class="col-sm-3 control-label">园区<span class="text-danger">&nbsp;</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="fYqbh" name="fYqbh" class="form-control">
                        </div>
                    </div> 
                    <div class="form-group">
                        <label class="col-sm-3 control-label">备注<span class="text-danger">&nbsp;</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="fRemark" name="fRemark" class="form-control">
                        </div>
                    </div>                                                                                                                                       
                    <div class="form-group m-t-sm" >
                        <div class="col-sm-6 col-sm-push-4 display_flex">
                            <button class="btn btn-md btn-primary" type="submit"><strong>确定</strong></button>
                            <button class="btn btn-white m-l-sm"  type="button" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<!----编辑采集参数开始--->
<div class="modal fade" id="modal-form-editElectricP" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title editIcon">&nbsp;编辑采集参数</h4>
            </div>
            <div class="modal-body">
            	<form id="editElectricPForm" name="editElectricPForm" class="form-horizontal">
            	<div class="form-group">
					<label class="col-sm-3 control-label" for="edit_fDnbh">能耗编号  <span class="text-danger">*</span></label>
					<div class="col-sm-8">
						<input type="text" id="edit_fDnbh" name="edit_fDnbh"  required class="form-control" readonly="readonly">
					</div>
				</div>
            	<div class="form-group">
					<label class="col-sm-3 control-label" for="edit_fDnmc">能耗名称  <span class="text-danger">*</span></label>
					<div class="col-sm-8">
						<input type="text" id="edit_fDnmc" name="edit_fDnmc"  required class="form-control">
					</div>
				</div>				
            	<div class="form-group">
					<label class="col-sm-3 control-label" for="edit_fNybh_ElectricP">能源类型  <span class="text-danger">*</span></label>
					<div class="col-sm-8">
						<input type="text" id="edit_fNybh_ElectricP" name="edit_fNybh_ElectricP"  required class="form-control" readonly="readonly">
					</div>
				</div>
            	<div class="form-group">
					<label class="col-sm-3 control-label" for="edit_fPydz">偏移地址  <span class="text-danger">*</span> <span class="text-danger">&nbsp;</span></label>
					<div class="col-sm-8">
						<input type="text" id="edit_fPydz" name="edit_fPydz" placeholder="请输入十六进制的偏移地址" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" for="edit_fBmgz">编码规则   <span class="text-danger">*</span><span class="text-danger">&nbsp;</span></label>
					<div class="col-sm-8">
						<select id="edit_fBmgz" name="edit_fBmgz" required class="form-control">

						</select>
					</div>
				</div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label">数据类型 <span class="text-danger">*</span><span class="text-danger">&nbsp;</span></label>
                        <div class="col-sm-8">
                            <select id="edit_fDataType" name="edit_fDataType" required class="form-control">
                                <option value="0">int</option>
                                <option value="1">float</option>
                                <option value="2">double</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">寄存器数量 <span class="text-danger">*</span><span class="text-danger">&nbsp;</span></label>
                        <div class="col-sm-8">
                            <select id="edit_fRegisterNumber" name="edit_fRegisterNumber" required class="form-control">
                                <option value="0">1</option>
                                <option value="1">2</option>
                                <option value="2">3</option>
                                <option value="3">4</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">解码顺序 <span class="text-danger">*</span><span class="text-danger">&nbsp;</span></label>
                        <div class="col-sm-8" id="edit_codeSeqSelect">
                            <select id="edit_fCodeSeq" name="edit_fCodeSeq" required class="form-control">
                                <option value="0">12</option>
                                <option value="1">21</option>
                            </select>
                        </div>
                    </div>

				<div class="form-group">
					<label class="col-sm-3 control-label" for="edit_fUnit">单位  <span class="text-danger">*</span> <span class="text-danger">&nbsp;</span></label>
					<div class="col-sm-8">
						<input type="text" id="edit_fUnit" name="edit_fUnit" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" for="edit_fScalingPosition">小数点位置  <span class="text-danger">*</span> <span class="text-danger">&nbsp;</span></label>
					<div class="col-sm-8">
						<input type="text" id="edit_fScalingPosition" name="edit_fScalingPosition" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" for="edit_fDataLength">数据长度 <span class="text-danger">*</span> <span class="text-danger">&nbsp;</span></label>
					<div class="col-sm-8">
						<input type="text" id="edit_fDataLength" name="edit_fDataLength" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" for="edit_fYqbh">园区 <span class="text-danger">&nbsp;</span></label>
					<div class="col-sm-8">
						<input type="text" id="edit_fYqbh" name="edit_fYqbh" class="form-control">
					</div>
				</div>								
				<div class="form-group">
					<label class="col-sm-3 control-label" for="edit_fRemark">备注<span class="text-danger">&nbsp;</span></label>
					<div class="col-sm-8">
						<input type="text" id="edit_fRemark" name="edit_fRemark" class="form-control">
					</div>
				</div>				
				<div class="form-group m-t-sm">
					<div class="col-sm-6 col-sm-push-4 display_flex">
						<button class="btn btn-md btn-primary " type="submit">
						<strong>确定</strong>
						</button>
						<button type="button" class="btn btn-white m-l-sm" data-dismiss="modal">取消</button>
					</div>
				</div>
				</form>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
;
var basedatamanage_enegrycollectionmanage_electricParams = (function($, window, document, undefined) {
 	var _ctx = '${ctx}';
 	var _ElectricPFxbh = "00";//分项配置编码
	var _ElectricPJs = "0";//用户组对应的级数
	var _fzbh = "00";
	var _curRow = null;//对应行
	var _includecurRow = null;//“已选择”table对应行
	var _notincludecurRow = null;//“未选择”table对应行
    var dataEncodeTypeObject = {};
	//设置格式
	var optIconFunction = function(cell, formatterParams){ //plain text value
		var ElectricPid = cell.getRow().getData().fDnbh;
		return "<div class='btn-group '>"
				+"<button class='btn btn-white btn-sm edit' data-id="+ ElectricPid + " data-toggle='modal' data-target='#modal-form-editElectricP'><i class='fa fa-pencil' ></i> 编辑</button>"
				+"<button class='btn btn-white btn-sm delete' data-id=" + ElectricPid + "><i class='fa fa-trash'></i>  删除</button></div>"
	};


	//创建并设置table属性
	$("#ElectricPTable").tabulator({
		height:"100%",
		layout:"fitColumns",//fitColumns  fitDataFill
		columnVertAlign:"bottom", //align header contents to bottom of cell
		tooltips:false,
		//selectable:true,
		movableColumns:true,
		columns:[
		{title:"序号",field:"id",width:80,formatter:"rownum",frozen:false,sorter:"string",headerSort:false}, //frozen column
		{title:"能耗编号", field:"fDnbh", sorter:"string",editor:false,align:"center",headerSort:false}, //never hide this column
		{title:"能耗名称", field:"fDnmc",sorter:"string",editor:false,align:"center",headerSort:false}, //hide this column first
		{title:"能源类型", field:"fNybh", sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"偏移地址", field:"fPydz", sorter:"string",editor:false,align:"center",headerSort:false,
			formatter: function (row)
            {
				return Number(row.cell.value).toString(16);
            }},
		{title:"编码规则", field:"fBmgz", sorter:"string",editor:false,align:"center",headerSort:false,formatter:function (cell)
		{
			return dataEncodeTypeObject[cell.getValue()];

		}},
		{title:"数据类型", field:"fDataType", sorter:"string",editor:false,align:"center",headerSort:false,
            formatter: function (row)
            {
                var value = row.cell.value;

                switch (value)
				{
					case '0':
					    return 'int';
					case '1':
					    return 'float';
					case '2':
					    return 'double';
                }
            },
		},
		{title:"解码顺序", field:"fCodeSeq", sorter:"string",editor:false,align:"center",headerSort:false,

            formatter: function (row)
            {
                var value = row.cell.value;

                switch (value)
                {
                    case '0':
                        return '12';
                    case '1':
                        return '21';
                    case '2':
                        return '1234';
                    case '3':
                        return '4321';
                    case '4':
                        return '2143';
                    case '5':
                        return '3412';
                    case '6':
                        return '123456';
                    case '7':
                        return '12345678';
                }
            },
		},
		{title:"单位", field:"fUnit", sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"小数点位置", field:"fScalingPosition", sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"数据长度", field:"fDataLength", sorter:"string",editor:false,align:"center",headerSort:false},
		// {title:"园区", field:"fYqbh", width:100,sorter:"string",editor:false,align:"center",headerSort:false},
		// {title:"备注", field:"fRemark", width:100,sorter:"string",editor:false,align:"center",headerSort:false},
		// {title:"创建时间", field:"fCrdate",sorter:"date",align:"center",editable:false,headerSort:false},
		// {title:"修改时间", field:"fChdate",sorter:"date",align:"center",editor:false,headerSort:false},
		{title:"操作", field:"opt",tooltip:false,width:150,tooltipsHeader:false,align:"center",formatter:optIconFunction,headerSort:false},
		],
    	rowClick:function(e, row){
        	_curRow = row;
        	var id = _curRow.getData().fNybh;
        	var choiseNode = $('#tree_ElectricP').treeview('findNodes', [ _curRow.getData().fNybh, 'id']);
        	if(choiseNode.length>1){
    	    	for (var i = 0; i < choiseNode.length; i++) {
    				if(choiseNode[i].id == id){
    					$('#tree_ElectricP').treeview('searchByNode', choiseNode[i]);//搜索设置高亮
    				}
    			}
    	    }else{
        	$('#tree_ElectricP').treeview('searchByNode', choiseNode[0]);//搜索设置高亮
    	    }        	
    	},
	});
		
	$(window).resize(function(){
		$("#ElectricPTable").tabulator("redraw");
	});	
		
	//触发搜索的回车时间
	$("#ElectricPInfo").focus(function(){
		$(this).keydown(function (e){
			if(e.which == "13"){
				basedatamanage_enegrycollectionmanage_electricParams.searchElectricP();//触发该事件
			} 
		 })
	});

    $("#fRegisterNumber").change(function(obj){

        var value = obj.target.value;
        var container = $('#codeSeqSelect');

        if (value === '0')
		{

            container.html(`<select id="fCodeSeq" name="fCodeSeq" required class="form-control">
								<option value="0">12</option>
								<option value="1">21</option>
							</select>`);
            return;
		}

        if (value === '1')
		{
            container.html(`<select id="fCodeSeq" name="fCodeSeq" required class="form-control">
								<option value="2">1234</option>
								<option value="3">4321</option>
								<option value="4">2143</option>
								<option value="5">3412</option>
							</select>`);
            return;
		}
        if (value === '2')
		{
            container.html(`<select id="fCodeSeq" name="fCodeSeq" required class="form-control">
								<option value="6">123456</option>
							</select>`);
            return;
		}

        if (value === '3')
		{
            container.html(`<select id="fCodeSeq" name="fCodeSeq" required class="form-control">
								<option value="7">12345678</option>
							</select>`);
		}

    });

    $("#edit_fRegisterNumber").change(function(obj){

        var value = obj.target.value;

        loadCodeSeqEdit(value);
    });

    function loadCodeSeqEdit(value)
    {
        
        if (value == null)
		{
		    return;
		}
        
        var container = $('#edit_codeSeqSelect');

        if (value === '0')
        {

            container.html(`<select id="edit_fCodeSeq" name="edit_fCodeSeq" required class="form-control">
								<option value="0">12</option>
								<option value="1">21</option>
							</select>`);
            return;
        }

        if (value === '1')
        {
            container.html(`<select id="edit_fCodeSeq" name="edit_fCodeSeq" required class="form-control">
								<option value="2">1234</option>
								<option value="3">4321</option>
								<option value="4">2143</option>
								<option value="5">3412</option>
							</select>`);
            return;
        }
        if (value === '2')
        {
            container.html(`<select id="edit_fCodeSeq" name="edit_fCodeSeq" required class="form-control">
								<option value="6">123456</option>
							</select>`);
            return;
        }

        if (value === '3')
        {
            container.html(`<select id="edit_fCodeSeq" name="edit_fCodeSeq" required class="form-control">
								<option value="7">12345678</option>
							</select>`);
        }
    }

	//添加采集参数表单验证
    var groupValidator = $("#addElectricPform").validate({
	     rules: {
	    	 fDnmc: {
                 // isChinese:true,
	             required: true,
	             rangelength: [1, 40]
	         },
             fPydz: {
                 isHexadecimal:true,
                 required: true
	         },
             fUnit: {
                 required: true
			 },
             fScalingPosition: {
                 required: true,
                 digits:true
             },
             fDataLength: {
                 required: true,
                 digits:true
             },
             /*fYqbh:{
                 // isChinese:true,
                 required: true,
             },
             fRemark:{
                 // isChinese:true,
                 required: true,
             },*/
	     },
	     messages: {
	         
	    	 fDnmc: {
	             required: "请填写能耗名称",
	             minlength: jQuery.validator.format("Enter at least {0} characters")
	         },
             fPydz: {
	             required: "请填写偏移地址"
	         },
             fUnit: {
	             required: "请填写单位"
	         },
             fScalingPosition: {
                 required: "请填写小数点位置"
             },
             fDataLength: {
                 required: "请填写数据长度"
             },
            /* fYqbh: {
                 required: "请填写园区名称"
             },
             fRemark:{
                 required: "请填写备注"
             },*/
	     },
	     submitHandler: function (nodeData) {
	         addformElectricP(nodeData);
	     }
 	});
	
	
 	//新增保存
	function addformElectricP(nodeData) {

	    var fPydz = parseInt($("#fPydz").val(), 16); // 十六进制转换十进制

		if (isNaN(fPydz))
		{
            swal({
                title : '请输入十六进制的偏移地址',// 展示的标题
                text : "",// 内容
                type: "warning",
                showCloseButton : false, // 展示关闭按钮
                allowOutsideClick : false,
                showConfirmButton : false,
                timer: 1000
            });
            return;
		}

	     $.ajax({
	       url: _ctx + "/view/basedatamanage/enegrycollectionmanage/add_ElectricP",
	       type: "post",
	       data:({     
	    	   fDnmc:$("#fDnmc").val(),
	    	   fPydz:fPydz,
	    	   fBmgz:$("#fBmgz").val(),
	    	   fUnit:$("#fUnit").val(),
	    	   fScalingPosition:$("#fScalingPosition").val(),
	    	   fDataLength:$("#fDataLength").val(),
	    	   fYqbh:$("#fYqbh").val(),
	    	   fRemark:$("#fRemark").val(),
               fDataType:$("#fDataType").val(),
               fCodeSeq:$("#fCodeSeq").val(),
	    	   fNybh:_ElectricPFxbh,
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
		            $('#modal-form-addElectricP').modal('hide');//关闭编辑窗口
		            //在表格中添加数据
		            $('#ElectricPTable').tabulator("addRow", data.data);
	         } else{
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

	//居中显示（添加）
	$('#modal-form-addElectricP').on('show.bs.modal', function () {
		// 关键代码，如没将modal设置为 block，则$modala_dialog.height() 为零  
    	$(this).css('display', 'block');  
    	var modalHeight=$(window).height() / 2 - $('#modal-form-addElectricP .modal-dialog').height() / 2;  
    		$(this).find('.modal-dialog').css({  
        		'margin-top': modalHeight  
    		}); 
	})

	//居中显示（编辑）
 	$('#modal-form-editElectricP').on('show.bs.modal', function () {
        $(this).css('display', 'block');  
        var modalHeight=$(window).height() / 2 - $('#modal-form-editElectricP .modal-dialog').height() / 2;  
        $(this).find('.modal-dialog').css({  
            'margin-top': modalHeight  
        }); 
	})
	
	//关闭模态框清空表单值
    $("#modal-form-addElectricP").on('hidden.bs.modal', function (event) {
        $(this).find("input").val("");
        groupValidator.resetForm();  
    });
	
	//删除数据
	$(document).on('click','#ElectricPTable button.delete',function() {
			var row = $(this).parents("tr")[0];
			var id = $(this).data("id");
			swal(
			{
			title : "您确定要删除吗?",
			text : "采集参数删除后将不可恢复!",
			type : "warning",
			showCancelButton : true,
			confirmButtonColor : "#1ab394",
			confirmButtonText : "确定",
			closeOnConfirm : false
			},
		function() {
			// row.className="animated bounceOut";
			setTimeout(function() {
			$.ajax({
				url : _ctx + "/view/basedatamanage/enegrycollectionmanage/del_ElectricP",
				type : "post",
				data : {"fDnbh" : id
				},
				beforeSend: function () { 
					showLoad();	             
				},
				success : function(data) {
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
              		$("#ElectricPTable").tabulator("deleteRow", _curRow);

				} else {
					swal("删除失败!",data.msg,"error");
				}
				},
				complete: function () {
					hiddenLoad();
				},
				error : function(data) {
					swal("删除失败!",data.msg,"error");
				}
			});
			}, 100)
		});
	});
	
 	//表单验证
	$("#editElectricPForm").validate({
        rules: {
            edit_fDnmc: {
                // isChinese:true,
                required: true,
                rangelength: [1, 40]
            },
            edit_fPydz: {
                isHexadecimal:true,
                required: true
            },
            edit_fUnit: {
                required: true
            },
            edit_fScalingPosition: {
                required: true,
                digits:true
            },
            edit_fDataLength: {
                required: true,
                digits:true
            },
           /* edit_fYqbh: {
                // isChinese:true,
                required: true,
            },
            edit_fRemark: {
                // isChinese:true,
                required: true,
            },*/
        },
        messages: {

            edit_fDnmc: {
                required: "请填写能耗名称",
                minlength: jQuery.validator.format("Enter at least {0} characters")
            },
            edit_fPydz: {
                required: "请填写偏移地址"
            },
            edit_fUnit: {
                required: "请填写单位"
            },
            edit_fScalingPosition: {
                required: "请填写小数点位置"
            },
            edit_fDataLength: {
                required: "请填写数据长度"
            },
           /* edit_fYqbh: {
                required: "请填写园区名称"
            },
            edit_fRemark: {
                required: "请填写备注"
            },*/
        },
  	 submitHandler: function(form) {
    	 editElectricP(form);
  	 }
 	});
 	
 	//编辑采集参数
	function editElectricP(form) {

        var fPydz = parseInt($("#edit_fPydz").val(), 16); // 十六进制转换十进制

        if (isNaN(fPydz))
        {
            swal({
                title : '请输入十六进制的偏移地址',// 展示的标题
                text : "",// 内容
                type: "warning",
                showCloseButton : false, // 展示关闭按钮
                allowOutsideClick : false,
                showConfirmButton : false,
                timer: 1000
            });
            return;
        }

   		$.ajax({
     		url: _ctx + "/view/basedatamanage/enegrycollectionmanage/edit_ElectricP",
     		type: "post",
     		data:({
     			fDnbh: $("#edit_fDnbh").val(),
     			fDnmc: $("#edit_fDnmc").val(),
     			fNybh: $("#edit_fNybh_ElectricP").val(),
     			fPydz: fPydz,
     			fBmgz: $("#edit_fBmgz").val(),
     			fUnit: $("#edit_fUnit").val(),
     			fScalingPosition: $("#edit_fScalingPosition").val(),
     			fDataLength:$("#edit_fDataLength").val(),
     			fYqbh: $("#edit_fYqbh").val(),
     			fRemark: $("#edit_fRemark").val(),
                fDataType: $("#edit_fDataType").val(),
                fCodeSeq: $("#edit_fCodeSeq").val(),
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
	         		$('#ElectricPTable').tabulator("updateRow",_curRow,data.data);
	              	$('#modal-form-editElectricP').modal('hide');//关闭编辑窗口
         		} else{
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
 	
 	//验证在模态框出现前加载编辑
 	$("#modal-form-editElectricP").on('show.bs.modal', function(event) {
   		var button = $(event.relatedTarget);
   		var id = button.data("id");//获取能源类型编号
   		$.ajax({
	       url: _ctx + "/view/basedatamanage/enegrycollectionmanage/getElectricP",
	       type: "post", 
	       data:{     
	 			"fDnbh":id
	 		},
	 		beforeSend: function () { 
	 			showLoad();	             
	 		},
	       success: function(result) {
	         $("#edit_fDnbh").val(result.data.fDnbh);
	         $("#edit_fDnmc").val(result.data.fDnmc);
	         $("#edit_fNybh_ElectricP").val(result.data.fNybh);
	         $("#edit_fPydz").val(Number(result.data.fPydz).toString(16));
	         $("#edit_fBmgz").val(result.data.fBmgz);
	         $("#edit_fUnit").val(result.data.fUnit);
	         $("#edit_fScalingPosition").val(result.data.fScalingPosition);
	         $("#edit_fDataLength").val(result.data.fDataLength);
	         $("#edit_fYqbh").val(result.data.fYqbh);
	         $("#edit_fRemark").val(result.data.fRemark);
	         $("#edit_fDataType").val(result.data.fDataType);

	         var codeSeq = result.data.fCodeSeq;

	         var fRegisterNumber;

	         switch (codeSeq)
			 {
				 case '0':
				 case '1':
                     fRegisterNumber = '0';
                     break;
				 case '2':
				 case '3':
				 case '4':
				 case '5':
                     fRegisterNumber = '1';
                     break;
				 case '6':
                     fRegisterNumber = '2';
                     break;
				 case '7':
                     fRegisterNumber = '3';
                     break;
			 }

			   loadCodeSeqEdit(fRegisterNumber);
               $("#edit_fRegisterNumber").val(fRegisterNumber);
               $("#edit_fCodeSeq").val(result.data.fCodeSeq);
	         },
	         complete: function () {
	    			hiddenLoad();
	    		},
	    	error: function(data) {
	         	 	swal("加载失败!", data.msg, "error");
	    		}
   		});  
 	});
	//下拉框列表查询
	function getfbmgz(){
		$.ajax({
			type: "POST",
            async: false,
			url: _ctx + '/view/basedatamanage/enegrycollectionmanage/getfbmgz',
			data:"",
			success: function(data){
				var ops="<option value=''>请选择编码规则</option>";
				for(var i=0;i<data.data.length;i++){
					var obj=data.data[i];
					ops+='<option value="'+obj.F_BMGZ_ID+'">';
					ops+=obj.F_BMGZ_ID+'('+obj.F_BMGZ_MC+')';
					ops+='</option>';

                    dataEncodeTypeObject[obj.F_BMGZ_ID] = obj.F_BMGZ_MC;
				}
				$("#fBmgz").append(ops);
				$("#edit_fBmgz").append(ops);
			},
			error:function(msg){
				swal( data.msg,"", "error");
			}
		});
	}
 	return {
 		//搜索
 		searchElectricP : function (){
 			var ElectricPInfo = $("#ElectricPInfo").val();
 	        $.ajax({
 		    url: _ctx+'/view/basedatamanage/enegrycollectionmanage/getElectricPList',
 		    type: "post",
 		    data: {
 		    	keywords:ElectricPInfo,
 		    },
 		   beforeSend: function () { 
 				showLoad();	             
 			},
 			success: function(data) {
 					if(data.hasOwnProperty('list')==false){
 			            $("#ElectricPTable").tabulator("setData", []);
 					}else{
 			            $("#ElectricPTable").tabulator("setData", data.list);
 					}
 	        },
 	       complete: function () {
 	    		hiddenLoad();
 	    	},
 	        error: function(data) {
 	      	    swal( data.msg,"", "error");
 	        }
 		   });
 		},
 		//加载树
 		ElectricP_tree : function () {
 		    $.ajax({
 		        type: "post",
 		        url: _ctx + "/view/basedatamanage/enegrycollectionmanage/ElectricP_tree",
 		        dataType: "json",
 		       beforeSend: function () { 
 		    		showLoad();	             
 		    	},
 		        success: function (result) {
 		            //初始加载根节点
 		            if(result.hasOwnProperty("list")){//判断result返回结果是否包含list
 		            	if(result.list.length >0){//如果包含判断是否长度大于0
 		            $('#tree_ElectricP').treeview({
 		                data: result.list,         // 数据源
 		                highlightSelected: true,    //是否高亮选中
 		                levels : 4,
 		                enableLinks : true,//必须在节点属性给出href属性
 		                color: "#4a4747",
 		               onNodeSelected: function (event, nodeData) {
 		               $('#tree_ElectricP').treeview('clearSearch');//清除搜索选中高亮
 		               		_ElectricPFxbh = nodeData.id;
 		               		_ElectricPJs = nodeData.level;
 		                    $.ajax({
 		                	    url: _ctx + "/view/basedatamanage/enegrycollectionmanage/ElectricP_chlildtreegrid",
 		                	    type: "post",
 		                	    data: {
 		                	    	fNybh:nodeData.id,
 		                	    },
 		                	   beforeSend: function () { 
 		                			showLoad();	             
 		                		},
 								success: function(nodeData2) {
 								if(nodeData2.hasOwnProperty('list')==false){
 						            $("#ElectricPTable").tabulator("setData", []);
 								}else{
 						            $("#ElectricPTable").tabulator("setData", nodeData2.list);
 								}
 					            },
 					           complete: function () {
 					        		hiddenLoad();
 					        	},
 					            error: function(nodeData2) {
 					          	    swal( nodeData2.msg,"", "error");
 					            }
 	                	   });
 		                }
 		            });
 				            var firstNode = $("#tree_ElectricP").treeview('findNodes',[result.list[0].id,'id']);
 				        	$("#tree_ElectricP").treeview("selectNode", firstNode);//将第一个节点设置为选中状态
 		            	}
 		            }
 		        },
 		       complete: function () {
 		    		hiddenLoad();
 		    	},
 		        error: function (nodeData) {
 		            swal( nodeData.msg,"", "error");
 		        }
 		    });
 		},
 		//验证增加模态框是否弹出
 		show_addElectricP : function () {
 	      var node = $('#tree_ElectricP').treeview('getSelected');
 					if (node.length == 0) {//凡是节点，note.length都为1；无选择节点，为0；
 						swal({ 
 		            		title: "请选择节点",
 		            		text: "经检测，您要未选择能源类型!",
 		            		type: "warning",
 		            		showCancelButton: false,
 		            		confirmButtonColor: "#1ab394",
 		            		confirmButtonText: "关闭",
 		            		closeOnConfirm: false
 		        		});
 					}else{
 					$('#modal-form-addElectricP').modal('show'); 
 					}
 	   },
 		pageInit : function(){
 			//basedatamanage_enegrycollectionmanage_electricParams.searchElectricP();
 			basedatamanage_enegrycollectionmanage_electricParams.ElectricP_tree();
			getfbmgz();
 		}

 		}

})(jQuery, window, document);
	basedatamanage_enegrycollectionmanage_electricParams.pageInit();
</script>