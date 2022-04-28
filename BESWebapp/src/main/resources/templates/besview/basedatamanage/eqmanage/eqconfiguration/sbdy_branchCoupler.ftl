   <div class="attrInfo">
                <div class="frist_attr" >
                  	    <div class="has-success">
                     	  <label class="col-sm-2 control-label" style="font-size:15px;">当前位置</label>
                          <label style="font-size: 15px;" id="f_allpath">${besSbPzStruct.f_allpath}</label>
                        </div>
                        <div class="vertical-timeline-block eqTreeAttrLineWidth">
                            <div class="has-success">
                                <label class="col-sm-2 control-label">名   称:</label>
                                <div class="col-sm-4">
                                    <input id="f_guid" value="${returnObject.data.F_GUID}" TYPE="hidden">
                                    <#--Start  add by wanghongjie at 20200116 for 增加设备树的f_id,传到后台查询该条的详细信息-->
                                    <input id="f_id" value="${besSbPzStruct.f_id}" TYPE="hidden">
                                    <input id="f_sys_name_old" value="${besSbPzStruct.f_sys_name_old}" TYPE="hidden">
                                    <input id="f_sys_name" name="f_sys_name" value="${returnObject.data.F_SYS_NAME_OLD}" type="text" class="form-control" readonly="readonly" onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()">
                                </div>
                            </div>
                            <div class="has-success">
                                <label class="col-sm-2 control-label">别  名:</label>
                                <div class="col-sm-4">
                                    <input type="text" id="f_nick_name" name="f_nick_name" value="${returnObject.data.F_NICK_NAME}" class="form-control"
                                           onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()">
                                </div>
                            </div>
                        </div>
                        <div  class="vertical-timeline-block eqTreeAttrLineWidth">
                            <div class="has-success">
                                <label class="col-sm-2 control-label">安装位置:</label>
                                <div class="col-sm-4">
                                    <input type="text" id="f_azwz"  value="${returnObject.data.F_AZWZ}" class="form-control"
                                           onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()">
                                </div>
                            </div>
                             <div class="has-success">
                                <label class="col-sm-2 control-label">通信地址:</label>
                                <div class="col-sm-4">
                                    <input type="hidden" id="f_addr_selected"  value="${returnObject.data.F_ADDR}" class="form-control">
                                    <span>
                                     <span id="branch_left"></span>
                                     <select id="f_addr_group" class="selector" style="width: 12.7em;height: 2em;border:1px solid green;" onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()"></select>
<#--                                     <span id="branch_right">.0</span></span>-->
                                    </span>
                                </div>
                            </div>
                         </div>
                         <div  class="vertical-timeline-block eqTreeAttrLineWidth">
                            <div class="has-success">
                                <label class="col-sm-2 control-label">描  述:</label>
                                <div class="col-sm-4">
                                    <input type="text" id="f_description"  value="${returnObject.data.F_DESCRIPTION}" class="form-control"
                                           onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()">
                                </div>
                            </div>
                            <div class="has-success">
                                <label class="col-sm-2 control-label">归属系统:</label>
                                <div class="col-sm-4">
                                    <input type="text" id="f_belong_iprouter"  value="${returnObject.data.F_BELONG_IPROUTER}" class="form-control" onchange="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.saveBtnIsEffective()">
                                </div>
                            </div>
                         </div>
                               <br/> <br/>
                                <div style="margin-left:66%;margin-top: 5em;">
                                    <button id="saveBtn" class="btn btn-primary" type="submit" onclick="basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo.infoChanged()">保存</button>
                                </div>
              <input type="hidden" id="sel_f_type"><!-- 定义一个隐藏节点类型 -->
              <input type="hidden" id="sel_img_url"><!-- 定义一个隐藏图片url -->
              <input type="hidden" id="ch_f_sys_name"/>
              <input type="hidden" id="ch_f_nick_name"/>
              <input type="hidden" id="ch_f_allpath"/>
              <input type="hidden" id="ch_f_type"/>
              <input type="hidden" id="ch_f_psys_name"/>
              <input type="hidden" id="hidden_f_sbid" value="${besSbPzStruct.f_sbid}">
                 </div>
     </div>
<script type="text/javascript">		
;
var basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo = (function($,window, document, undefined) {
	var _ctx = '${ctx}';
    var allpath = "";

    $(function() {
        allpath = "${besSbPzStruct.f_allpath}";
        changeAddr();
    });
    //通信地址
	function changeAddr(){
		var sel = $("#f_addr_selected").val().split(".");
// 		if(sel!=null && sel!=""){
// 			$("#branch_left").text(sel.substring(0,sel.indexOf(".")+1));
// 			var selSpinner = $("#f_addr_selected").val().split(".")[1];
// 			$("#branch_right").text(sel.substring(sel.indexOf(".")+1+selSpinner.length,sel.length));
// 		}else{
// 			var selSpinner = $("#f_addr_selected").val().split(".")[1];
// 		}
        //查询父节点的通信地址
        var f_sys_name = "${returnObject.data.F_SYS_NAME_OLD}";
        if (f_sys_name == ""){//刚进入新增页面的时候,查出上级节点的通信地址
            $.ajax({
                url : _ctx + "/view/basedatamanage/eqmanage/select_pre_branchCoupler",
                contentType : "application/json; charset=utf-8",
                dataType : "json",
                type : "get",
                data : {
                    f_sys_name  : $("#pre_f_sys_name").val(), //父系统名称
                },
                beforeSend: function () {
                    showLoad();
                },
                success : function(result) {
                    if (result.status == "0") {

                        $("#branch_left").text("1.");
                    }else {

                        var trunk_left = result.data.trunk_left;
                        $("#branch_left").text(trunk_left+".");
                    }

                },
                complete: function () {
                    hiddenLoad();
                },
                error : function(result) {
                    swal(result.msg, "", "error");
                }
            });
        }else {

            $.ajax({
                url : _ctx + "/view/basedatamanage/eqmanage/select_branchCoupler",
                contentType : "application/json; charset=utf-8",
                dataType : "json",
                type : "get",
                data : {
                    f_sys_name  : "${returnObject.data.F_SYS_NAME_OLD}",//名称
                },
                beforeSend: function () {
                    showLoad();
                },
                success : function(result) {
                    var trunk_left = result.data.trunk_left;
                    $("#branch_left").text(trunk_left+".");
                },
                complete: function () {
                    hiddenLoad();
                },
                error : function(result) {
                    swal(result.msg, "", "error");
                }
            });
        }

		
		for (var i = 1; i < 256; i++) {
			var x = document.getElementById("f_addr_group");
			var obj = document.createElement("option");
			obj.value = i;
			obj.text = i;
			x.appendChild(obj);
			if (sel[0] == i) {//设置当前选中的通信地址
				obj.selected = true;
			}
		}
	}
	function getBranchAddrByTrunkId(selPid){//根据所在干线耦合器通信地址设置支线通信地址
		$.ajax({
			url : _ctx + "/view/basedatamanage/eqmanage/sbdy_getBranchAddrByTrunkId",
			contentType : "application/json; charset=utf-8",
			type : "get",
			data : {
				f_sys_name : selPid //系统名称
			},
			beforeSend: function () {
				showLoad();
			},
			success : function(result) {
				var objAddr = result.data.F_ADDR;
				if(objAddr!=null && objAddr!=""){
					$("#branch_left").text(objAddr.substring(0,objAddr.indexOf(".")+1));//三个对应主线对应通信地址
					var branch_midd= objAddr.substring(objAddr.indexOf(".")+1,objAddr.lastIndexOf("."));
					$("#branch_right").text(".0");
					//$("#f_addr_group option[value='"+ branch_midd+"']").attr("selected",true);//设置当前选中的通信地址
				}
			},
			complete: function () {
				hiddenLoad();
			},
			error : function(result) {
				swal(result.msg, "", "error");
			}
		});
	}

	return {
		addDifferSet : function(){
			if(basedatamanage_eqmanage_eqconfiguration_sbdy.getIsAdd()){

				var selPid = basedatamanage_eqmanage_eqconfiguration_sbdy.getTmp_treeSelNode()[0].pid;
				getBranchAddrByTrunkId(selPid);//根据干线节点获取对应支线节点网络地址
			}
		},
		//信息发生变更时
		infoChanged : function() {
			if (eval($("#saveBtn").attr("name"))) {
			    if (allpath == ""){
			        allpath = $("#f_allpath").text();//全路径
                }
				$.ajax({
					// url : _ctx + "/view/basedatamanage/eqmanage/sbdy_Info_editBySelNode",
                    url : _ctx + "/view/basedatamanage/eqmanage/sbdy_coupler_Update",
					contentType : "application/json; charset=utf-8",
					dataType : "json",
					type : "post",
					data : JSON.stringify({
                        f_guid              : $("#f_guid").val(),//自增的id
                        f_sbid 	            : $("#hidden_f_sbid").val(),//设备树id
						tabName             : "BES_COUPLER",//耦合器表
						f_sys_name          : $("#f_sys_name").val(),//名称
						f_nick_name         : $("#f_nick_name").val(),//别名
						f_azwz              : $("#f_azwz").val(),//安装地址
						f_addr              : $("#f_addr_group option:checked").val(),//通信地址
						f_description       : $("#f_description").val(),//描述
						f_belong_iprouter   : $("#f_belong_iprouter").val(),//所属系统
                        f_allpath           : allpath,//全路径
                        f_psys_name         : $("#pre_f_sys_name").val(), //父系统名称
					}),
					beforeSend: function () {
						showLoad();
					},
					success : function(result) {
                        if (result.status === '0')
                        {
                            swal({
                                title: result.msg,// 展示的标题
                                text: "",// 内容
                                type: "error",
                                showCloseButton: false, // 展示关闭按钮
                                allowOutsideClick: false,
                                showConfirmButton: false,
                                timer: 1500,
                            });
                        }
                        else if(result.status == '1') {
                            swal({
                                title: result.msg,// 展示的标题
                                text: "",// 内容
                                type: "success",
                                showCloseButton: false, // 展示关闭按钮
                                allowOutsideClick: false,
                                showConfirmButton: false,
                                timer: 1500
                            });
                            $("#saveBtn").attr("disabled", true); //【应用】设置为无效
                            $("#f_sys_name").attr("readonly", "readonly");
                            basedatamanage_eqmanage_eqconfiguration_sbdy.setWebCanClose();
                        }

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
					// url : _ctx + "/view/basedatamanage/eqmanage/sbdy_Info_addByTreeBtn",
                    url : _ctx + "/view/basedatamanage/eqmanage/sbdy_coupler_Insert",
					contentType : "application/json; charset=utf-8",
					dataType : "json",
					type : "post",
					data : JSON.stringify({
                        f_sbid 	            : $("#hidden_f_sbid").val(),//设备树id
                        attrTabName         : "BES_COUPLER",
                        f_sys_name          : $("#f_sys_name").val(),//名称
                        f_nick_name         : $("#f_nick_name").val(),//别名
                        f_type_coupler      : "1",// 1:支线
                        f_azwz              : $("#f_azwz").val(),//安装地址
                        f_addr              : $("#f_addr_group option:checked").val(),//通信地址
                        f_description       : $("#f_description").val(),//描述
                        f_belong_iprouter   : $("#f_belong_iprouter").val(),//所属系统
                        f_allpath           : $("#f_allpath").text(),//全路径
                        f_type              : $("#sel_f_type").val(),//节点类型
                        f_psys_name         : $("#pre_f_sys_name").val() //父系统名称
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

                            swal({
                                title : returnObject.msg,// 展示的标题
                                text : "",// 内容
                                type: "success",
                                showCloseButton : false, // 展示关闭按钮
                                allowOutsideClick : false,
                                showConfirmButton : false,
                                timer: 1000,
                            });

							var changeNode = $('#tree_sbdy').treeview('search', [ basedatamanage_eqmanage_eqconfiguration_sbdy.getAddNodeTextCon(), { exactMatch : true } ]);//获取刚修改属性的节点
							addeNodeId = $("#ch_f_sys_name").val();
							addedNodeText = $("#ch_f_nick_name").val();
                            var PNodes = $('#tree_sbdy').treeview('getParents',  changeNode);
							//更新新保存的节点
							$('#tree_sbdy').treeview('updateNode', [ changeNode, {
								nodeTreeId : addeNodeId,
								id : addeNodeId,
								text : addedNodeText,
								nodeType : changeNode[0].nodeType,
								pid : changeNode[0].pid,
								image : changeNode[0].image,
                                nodeAttribution : PNodes[0].nodeAttribution,
                                state :{selected :false}
							} ]);
                            $("#tree_sbdy").treeview("selectNode", changeNode);
							basedatamanage_eqmanage_eqconfiguration_sbdy.setIdMap(addeNodeId, addeNodeId);
							$("#saveBtn").attr("disabled", true); //【新增】设置为无效
							$("#f_sys_name").attr("readonly", "readonly");
							basedatamanage_eqmanage_eqconfiguration_sbdy.setIsAdd(false);
						} else {//保存失败时
                            // basedatamanage_eqmanage_eqconfiguration_sbdyAttrInfo_common.nodeAddFalse(returnObject);
                            if (typeof callback === "function")
                            {
                                callback(returnObject);
                            }

                            swal({
                                title: returnObject.msg,// 展示的标题
                                text: "",// 内容
                                type: "warning",
                                showCloseButton: false, // 展示关闭按钮
                                allowOutsideClick: false,
                                showConfirmButton: false,
                                timer: 1500
                            });
                            return;
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