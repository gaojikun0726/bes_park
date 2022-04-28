   <div class="attrInfo" style="overflow:auto; width:100%;">
                <div class="frist_attr" >
                  	    <div class="has-success">
                     	  <label class="col-sm-2 control-label" style="font-size:15px;">当前位置</label>
                          <label style="font-size: 15px;" id="f_allpath">${besSbPzStruct.f_allpath}</label>
                        </div>
                        <div class="vertical-timeline-block  eqTreeAttrLineWidth" >
                            <div class="has-success">
                                <label class="col-sm-2 control-label">标题内容:</label>
                                <div class="col-sm-4">
                                    <input id="title_f_sys_name"  value="${besSbPzStruct.f_sys_name}" type="text" class="form-control" readonly="readonly" onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()">
                                    <input id="f_id" value="${besSbPzStruct.f_id}" TYPE="hidden">
                                </div>
                            </div>
                            <div class="has-success">
                                <label class="col-sm-2 control-label">工程名称:</label>
                                <div class="col-sm-4">
                                    <input id="f_sys_name"  value="${besSbPzStruct.f_sys_name}" type="text" class="form-control" readonly="readonly" onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()">
                                </div>
                            </div>
                        </div>
                        <div  class="vertical-timeline-block eqTreeAttrLineWidth">
                             <div class="has-success">
                                <label class="col-sm-2 control-label">工程别名:</label>
                                <div class="col-sm-4">
                                    <input type="text" id="f_nick_name"  value="${besSbPzStruct.f_nick_name}" class="form-control"
                                           onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()">
                                </div>
                            </div>
                            <div class="has-success">
                                <label class="col-sm-2 control-label">工程描述:</label>
                                <div class="col-sm-4">
                                    <input type="text" id="f_description"  value="${besSbPzStruct.f_description}" class="form-control"
                                           onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()">
                                </div>
                            </div>
                         </div>
                               <br/> <br/>
                                <div style="margin-left: 66%;margin-top: 5em;">
                                    <button id="saveBtn" class="btn btn-primary" type="submit"
                                            onclick="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.infoChanged()">保存</button>
                                </div>
                 </div>
     </div>
<script src="${ctx}/static/js/equipmentTree/equipmentTreeCommon.js"></script><!-- 引入共通Js -->
<script type="text/javascript">
;
var basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo = (function($,window, document, undefined) {
	var _ctx = '${ctx}';
	return {
		//信息发生变更时
		infoChanged : function() {
			$.ajax({
				url : _ctx + "/view/basedatamanage/eqmanage/sbdy_Info_editBySelNode",
				contentType : "application/json; charset=utf-8",
				dataType : "json",
				type : "post",
				data : JSON.stringify({
          f_id : $("#f_id").val(),
          myInput_f_sys_name :$("#f_id").val(),//手动输入系统名称
					tabName : "BES_SBPZ_STRUCT",//表名
          old_f_sys_name : $("#f_sys_name").val(),//工程名称
          f_sys_name : $("#f_sys_name").val(),//系统名称
					f_nick_name : $("#f_nick_name").val(),//工程别 名
					f_description : $("#f_description").val()//工程描述
				}),
				beforeSend: function () {
					showLoad();
				},
				success : function(result) {
					$("#saveBtn").attr("disabled", true); //【保存】设置为无效
					basedatamanage_eqmanage_eqconfiguration_sbdy.setWebCanClose();
				},
				complete: function () {
					hiddenLoad();
				},
				error : function(result) {
					swal(result.msg, "", "error");
				}
			});
		},
	}
	
})(jQuery, window, document);
</script>