   <div class="attrInfo">
                <div class="frist_attr" >
                  	    <div class="has-success">
                     	  <label class="col-sm-2 control-label" style="font-size:15px;">当前位置</label>
                          <label style="font-size: 15px;" id="f_allpath">${besSbPzStruct.f_allpath}</label>
                        </div>
                        <div class="vertical-timeline-block eqTreeAttrLineWidth">
                            <div class="has-success">
                                <label class="col-sm-2 control-label">名   称</label>
                                <div class="col-sm-4">
                                    <input id="f_sys_name" name="f_sys_name" value="${besSbPzStruct.f_nick_name}" type="text" class="form-control" readonly="readonly"
                                           onkeyup="value=value.replace(/[\W]/g,'') "onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"
                                           onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()">
                                </div>
                            </div>
                            <#--<div class="has-success">
                                <label class="col-sm-2 control-label">别  名</label>
                                <div class="col-sm-4">
                                    <input type="text" id="f_nick_name" name="f_nick_name" value="${returnObject.data.f_nick_name}" class="form-control" onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()">
                                </div>
                            </div>-->
                        </div>
                       
                               <br/> <br/>
<!--                                 <div style="margin-left: 66%;margin-top: 5em;"> -->
<!--                                     <button id="saveBtn" class="btn btn-primary" type="submit" onclick="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.infoChanged()">保存</button> -->
<!--                                 </div> -->
              <input type="hidden" id="sel_f_type"><!-- 定义一个隐藏节点类型 -->
              <input type="hidden" id="sel_img_url"><!-- 定义一个隐藏图片url -->
              <input type="hidden" id="ch_f_sys_name"/>
              <input type="hidden" id="ch_f_nick_name"/>
              <input type="hidden" id="ch_f_allpath"/>
              <input type="hidden" id="ch_f_type"/>
              <input type="hidden" id="ch_f_psys_name"/>
                 </div>
     </div>
<script type="text/javascript">
;
var basedatamanage_eqmanage_eqconfiguration_sbdybusInfocross = (function($,window, document, undefined) {
	var _ctx = '${ctx}';
	
	return {
		addDifferSet : function(){
			if(basedatamanage_eqmanage_eqconfiguration_sbdy.getIsAdd()){
				$("#trunk_right").text(".0.0");
			}
		},
		//信息发生变更时
		infoChanged : function() {
			if (eval($("#saveBtn").attr("name"))) {
				$.ajax({
					url : _ctx + "/view/basedatamanage/eqmanage/sbdy_Info_editBySelNode",
					contentType : "application/json; charset=utf-8",
					dataType : "json",
					type : "post",
					data : JSON.stringify({
						tabName : "BES_BUS",//总线表
						f_sys_name : $("#f_sys_name").val(),//名称
						f_nick_name : $("#f_nick_name").val(),//别名
						f_port : $("#f_port").val(),//端口
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
			} else {
				$("#ch_f_sys_name").val($("#f_sys_name").val());
				$("#ch_f_nick_name").val($("#f_nick_name").val());
				$("#ch_f_allpath").val($("#f_allpath").text());
				$("#ch_f_type").val($("#sel_f_type").val());
				$("#ch_f_psys_name").val($("#pre_f_sys_name").val());
				$.ajax({//新增属性节点时
					url : _ctx + "/view/basedatamanage/eqmanage/sbdy_Info_addByTreeBtn",
					contentType : "application/json; charset=utf-8",
					dataType : "json",
					type : "post",
					data : JSON.stringify({
						attrTabName : "BES_BUS",
						attr_f_sys_name : $("#f_sys_name").val(),//名称
						attr_f_nick_name : $("#f_nick_name").val(),//别名
						attr_f_port : $("#f_port").val(),//端口
						tabName : "BES_SBPZ_STRUCT",//表名
						f_sys_name : $("#f_sys_name").val(),//系统名称
						f_nick_name : $("#f_nick_name").val(),//别 名
						f_allpath : $("#f_allpath").text(),//全路径
						f_type : $("#sel_f_type").val(),//节点类型
						f_psys_name : $("#pre_f_sys_name").val() //父系统名称
					}),
					beforeSend: function () {
						showLoad();
					},
					success : function(returnObject) {
						if(returnObject.status == '2'){
							basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.inputIdNotEffective();
							return;
						}else {
							basedatamanage_eqmanage_eqconfiguration_sbdy.setAddStaus(1);
						}
						if (returnObject.status == '1') {//保存成功时
							var changeNode = $('#tree_sbdy').treeview('search', [ basedatamanage_eqmanage_eqconfiguration_sbdy.getAddNodeTextCon(), { exactMatch : true } ]);//获取刚修改属性的节点
							addeNodeId = $("#ch_f_sys_name").val();
							addedNodeText = $("#ch_f_nick_name").val();
							//更新新保存的节点
							$('#tree_sbdy').treeview('updateNode', [ changeNode, {
								nodeTreeId : addeNodeId,
								id : addeNodeId,
								text : addedNodeText,
								nodeType : changeNode[0].nodeType,
								pid : changeNode[0].pid,
								image : changeNode[0].image
							} ]);
                            basedatamanage_eqmanage_eqconfiguration_sbdy.refreshIcon(); // 刷新节点状态
							basedatamanage_eqmanage_eqconfiguration_sbdy.setIdMap(addeNodeId, addeNodeId);
							$("#saveBtn").attr("disabled", true); //【新增】设置为无效
							$("#f_sys_name").attr("readonly", "readonly");
							basedatamanage_eqmanage_eqconfiguration_sbdy.setIsAdd(false);
						} else {//保存失败时
							basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.nodeAddFalse(returnObject);
						}
						$("#saveBtn").text("保存");
						basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.refreshSubmitFlg();
						basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.setIsAddInfo(false);
						basedatamanage_eqmanage_eqconfiguration_sbdy.setWebCanClose();
					},
					complete: function () {
						hiddenLoad();
					},
					error : function(returnObject) {
						swal(returnObject.msg, "", "error");
					}
				});
			}
		},
	}
})(jQuery, window, document);
</script>
<script src="${ctx}/static/js/equipmentTree/equipmentTreeCommon.js"></script><!-- 引入共通Js -->