<!-----内容区域---->
<!-- <div class="ibox-content m-b-sm border-bottom" style="width:100%;height:7%">
	<div class="input-group"  style="height:40px;width:100%">
        <div style="width: 50%;float: left;position: relative;padding-right: 15px;padding-left: 0px;">
            <a id="moduletypeadd" class="btn btn-primary" data-toggle="modal"  href="#modal-form-addmoduletype">
                	增加 <i class="fa fa-plus"></i>
            </a>
            <a id="importmoduletype" class="btn btn-primary" data-toggle="modal"  href="#modal-form-importmoduletype">
                	导入 <i class="fa fa-mail-reply"></i>
            </a>
            <a id="exportmoduletype" class="btn btn-primary" data-toggle="modal"  href="#modal-form-exportmoduletype">
                	导出 <i class="fa fa-mail-forward"></i>
            </a>
        </div>
		<div class="zc_search find">
			<div class="zc_search_form">
				<input id="moduletypekeywords" name="moduletypekeywords"
					value="" placeholder="模块型号、模块描述">
				<button id="queryModuleTypeBtn"></button>
			</div>
		</div>
	</div>
</div> -->



	<div class="information-model">
		<span class="Subtitle">
			<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;模块类型列表>>>
		</span>
        <!-- 增加按钮 -->
        <a id="moduletypeadd" data-toggle="modal" href="#modal-form-addmoduletype" class="btn btn-primary toLeft">
            <i class="fa fa-plus" style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>增加
        </a>
        <!-- 搜索框 -->
        <div class="zc_search find">
            <input type="text" class="find-style" id="moduletypekeywords" name="moduletypekeywords"
                   placeholder="模块型号、模块描述">
            <button id="queryModuleTypeBtn"><i class="fa fa-search" aria-hidden="true"></i></button>
        </div>
    </div>
<!---分页列表----->
    <div class="ibox" id="moduleType_ibox" style="height:92%">
         	<#include "besview/basedatamanage/eqmanage/moduleType_page.ftl"/>
    </div>

<!---添加设备型号信息开始----->
<div class="modal fade" id="modal-form-addmoduletype" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">

            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp;添加模块型号信息</h4>
            </div>

            <div class="modal-body">
                <form role="form" id="addModuletype" name="addModuletype" class="form-horizontal">
                    <div style="width:65%;float:left">
                        <div class="form-group" style="height:50px;">
                            <label class="col-sm-3 control-label">模块型号<span class="text-danger">*</span></label>
                            <div class="col-sm-8">
                                <input type="text" id="fModuleType" name="fModuleType" placeholder="请输入模块型号" required
                                       class="form-control">
                            </div>
                        </div>
                        <div class="form-group" style="height:50px;">
                            <label class="col-sm-3 control-label">类型代码<span class="text-danger">*</span></label>
                            <div class="col-sm-8">
                                <input type="text" id="fTypeCode" name="fTypeCode" placeholder="请输入模块类型代码" required
                                       class="form-control">
                            </div>
                        </div>
                        <div class="form-group" style="height:50px;">
                            <label class="col-sm-3 control-label">模块点数<span class="text-danger">*</span></label>
                            <div class="col-sm-8">
                                <input type="text" id="fPointAmount" name="fPointAmount" value="" placeholder="请输入模块点数"
                                       required class="form-control"
                                       onchange="basedatamanage_eqmanage_moduleType.createTable(this.value)">
                            </div>
                        </div>
                        <div class="form-group" style="height:50px;">
                            <label class="col-sm-3 control-label">模块描述</label>
                            <div class="col-sm-8">
                                <input type="text" id="fDescription" name="fDescription" placeholder="请输入模块描述"
                                       class="form-control">
                            </div>
                        </div>
                    </div>

                    <div style="width:35%;float:right">
                        <div class="form-group" id="modulePointTable">
                            <table class='table' style=" height: 80vh">
                                <tr>
                                    <th id="num" style="width: 80px;height: 4vh" align="center">模块点数</th>
                                    <th id="type" align="center">模块点类型</th>
                                </tr>
                                <tr>
                                    <td colspan="2" align="center">
                                        <div id="list"
                                             style="overflow-x: hidden; width: 100%; height: 73vh;margin:0px;">
                                            <!-- 这个是核心 -->
                                            <table id="modulePointTbody"
                                                   style="margin: -2px -2px -2px -2px; width: 100%; border: 0px;"
                                                   class='table'>
                                            </table>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <input type="hidden" id="fPointTypeCl" name="fPointTypeCl" value="" placeholder="请输入1-12位中文字符"
                               class="form-control">
                    </div>

                    <div class="form-group m-t-sm" align="center">
                        <div class="col-sm-6 col-sm-push-3 display_flex" style="left: 23px">
                            <button class="btn btn-md btn-primary" type="submit"><strong>确定</strong></button>
                            <button class="btn btn-white m-l-sm" type="button" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </form>
            </div>

        </div>
    </div>
</div>
<!---添加模块型号信息结束----->

<!----编辑模块型号信息--->
<div class="modal fade" id="modal-form-editmoduletype" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">

            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title editIcon">&nbsp;编辑模块型号信息</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="editMtForm" name="editMtForm" class="form-horizontal">
                    <div style="width:65%;float:left">
                        <div class="form-group" style="height:50px;">
                            <label class="col-sm-3 control-label">模块型号<span class="text-danger">*</span></label>
                            <div class="col-sm-8">
                                <input type="text" id="edit_fModuleType" name="edit_fModuleType" readonly="readonly"
                                       required class="form-control">
                            </div>
                        </div>

                        <div class="form-group" style="height:50px;">
                            <label class="col-sm-3 control-label">类型代码<span class="text-danger">*</span></label>
                            <div class="col-sm-8">
                                <input type="text" id="edit_fTypeCode" name="edit_fTypeCode" placeholder="请输入模块类型代码" required
                                       class="form-control">
                            </div>
                        </div>

                        <div class="form-group" style="height:50px;">
                            <label class="col-sm-3 control-label">模块点数<span class="text-danger">*</span></label>
                            <div class="col-sm-8">
                                <input type="text" id="edit_fPointAmount" name="edit_fPointAmount" required
                                       class="form-control"
                                       onchange="basedatamanage_eqmanage_moduleType.createEditTable(this.value)">
                            </div>
                        </div>
                        <div class="form-group" style="height:50px;">
                            <label class="col-sm-3 control-label">模块描述</label>
                            <div class="col-sm-8">
                                <input type="text" id="edit_fDescription" name="edit_fDescription" class="form-control">
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div id="edit_modulePointTable" style="width:35%;float:right">
                            <table class='table' style="height: 80vh">
                                <tr>
                                    <th id="num" style="width: 60px;height: 4vh" align="center">点数</th>
                                    <th id="type" align="center">点类型</th>
                                </tr>
                                <tr>
                                    <td colspan="2" align="center">
                                        <div id="list" style="overflow-x: hidden; width: 100%; height: 73vh;">
                                            <!-- 这个是核心 -->
                                            <table id="edit_modulePointTbody"
                                                   style="margin: -2px -2px -2px -2px; width: 100%; border: 0px;"
                                                   class='table'>
                                            </table>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <input type="hidden" id="edit_fPointTypeCl" name="edit_fPointTypeCl" value=""
                               placeholder="请输入1-12位中文字符" class="form-control">
                    </div>

                    <div class="form-group m-t-sm" align="center">
                        <div class="col-sm-6 col-sm-push-3 display_flex" style="left: 23px;top: -370px">
                            <button class="btn btn-md btn-primary" type="submit"><strong>确定 </strong></button>
                            <button class="btn btn-white m-l-sm" type="button" data-dismiss="modal">取消</button>
                        </div>
                    </div>

                </form>
            </div>
        </div>
    </div>
</div>
<!----编辑模块信息结束--->

<script type="text/javascript">
    ;
    var basedatamanage_eqmanage_moduleType = (function ($, window, document, undefined)
    {
        var _ctx = '${ctx}';


        $(window).resize(function ()
        {
            $("#mtTable").tabulator("redraw");
        });

        //添加设备信息表单验证
        var moduleValidator = $("#addModuletype").validate({
            rules: {
                fModuleType: {
                    required: true,
                    rangelength: [1, 20],
                },
                fTypeCode: {
                    required: true,
                    digits: true,
                    rangelength: [1, 10],
                },

                fPointAmount: {
                    required: true,
                    digits: true,
                    rangelength: [1, 20],
                }
            },
            messages: {
                fModuleType: {
                    required: "请输入设备型号",
                    rangelength: jQuery.validator.format("密码应为3-20位的英文字母、数字字符"),
                    remote: jQuery.validator.format("{0} is already in use")
                },
                fTypeCode: {
                    required: "请输入模块类型代码",
                    digits: "请输入整数",
                    minlength: jQuery.validator.format("Enter at least {0} characters")
                },
                fPointAmount: {
                    required: "请输入模块点数",
                    digits: "请输入整数",
                    minlength: jQuery.validator.format("Enter at least {0} characters")
                },
            },
            submitHandler: function (form)
            {
                addform(form);
            }
        });


        //select标签查询
        function select_modulepoint_type(thisid)
        {
            var thisel = $("#" + thisid);
            $.ajax({
                type: "POST",
                async: false,
                url: _ctx + '/view/basedatamanage/eqmanage/selectList',
                data: "",
                beforeSend: function ()
                {
                    showLoad();
                },
                success: function (data)
                {
                    var ops = "";
                    for (var i = 0; i < data.list.length; i++)
                    {
                        var obj = data.list[i];
                        ops += '<option value="' + obj.fId + '">';
                        ops += obj.fModulepointType;
                        ops += '</option>';
                    }
                    thisel.append(ops);
                },
                complete: function ()
                {
                    hiddenLoad();
                },
                error: function (msg)
                {
                    alert("select列表查询失败!");
                }
            });
        }


        function addform(form)
        {
            //提交之前，先取出每个select标签的value，拼接后给fPointTypeCl字段
            var num = $("#fPointAmount").val();
            var j = 1;
            var cl = "";
            //取出select标签的value值
            for (var i = 0; i < num; i++)
            {
                cl += $("#pointType" + j + "").val();
                j++;
            }
            //验证模块点类型和模块点数是否相同
            if (cl.length < num)
            {
                swal("重要数据未填写", "请选择模块点类型", "error");
                return;
            }
            $("#fPointTypeCl").val(cl);

            $.ajax({
                url: _ctx + "/view/basedatamanage/eqmanage/moduleType_add",
                type: "post",
                data: $(form).serialize(),
                //async: false,
                beforeSend: function ()
                {
                    showLoad();
                },
                success: function (data)
                {
                    if (data.status == '1')
                    {
                        basedatamanage_eqmanage_moduleType.reLoadModuleType();
                        swal({
                            title: data.msg,// 展示的标题
                            text: "",// 内容
                            type: "success",
                            showCloseButton: false, // 展示关闭按钮
                            allowOutsideClick: false,
                            showConfirmButton: false,
                            timer: 1000
                        });
                        //	$("#mtTable").tabulator("addRow",data.data);
                        $("#modal-form-addmoduletype").modal('hide');//关闭编辑窗口
                    } else
                    {
                        swal("添加失败!", data.msg, "error");
                    }
                },
                complete: function ()
                {
                    hiddenLoad();
                },
                error: function (data)
                {
                    swal("添加失败!", data.msg, "error");
                }
            });
        }


        //居中显示
        $('#modal-form-addmoduletype').on('show.bs.modal', function ()
        {
            // 关键代码，如没将modal设置为 block，则$modala_dialog.height() 为零
            $(this).css('display', 'block');
            var modalHeight = $(window).height() / 2 - $('#modal-form-addmoduletype .modal-dialog').height() / 2;
            $(this).find('.modal-dialog').css({
                'margin-top': modalHeight
            });
        })

        $("#modal-form-editmoduletype").on('show.bs.modal', function (event)
        {
            // 关键代码，如没将modal设置为 block，则$modala_dialog.height() 为零
            $(this).css('display', 'block');
            var modalHeight1 = $(window).height() / 2 - $('#modal-form-editmoduletype .modal-dialog').height() / 2;
            $(this).find('.modal-dialog').css({
                'margin-top': modalHeight1
            });
        })

        //关闭模态框清空表单值
        $("#modal-form-addmoduletype").on('hidden.bs.modal', function (event)
        {
            $(this).find("input").val("");
            moduleValidator.resetForm();
            $("#modulePointTbody").html("");
        });
        //关闭模态框清空表单值
        $("#modal-form-editmoduletype").on('hidden.bs.modal', function (event)
        {
            $(this).find("input").val("");
            ediModuleValidator.resetForm();
            $("#modulePointTbody").html("");
        });

        //触发搜索的回车事件
        $("#moduletypekeywords").focus(function ()
        {
            $(this).keydown(function (e)
            {
                if (e.which == "13")
                {
                    basedatamanage_eqmanage_moduleType.reLoadModuleType({
                        "keywords": $("#moduletypekeywords").val()
                    });//触发该事件//触发该事件
                }
            })
        });

        //查询
        $("#queryModuleTypeBtn").click(function ()
        {
            basedatamanage_eqmanage_moduleType.reLoadModuleType({
                "keywords": $("#moduletypekeywords").val()
            });
        });

        //编辑设备型号信息
        //表单验证
        var ediModuleValidator = $("#editMtForm").validate({
            rules: {
                edit_fModuleType: {
                    required: true,
                    rangelength: [1, 20],
                },
                edit_fTypeCode: {
                    required: true,
                    digits: true,
                    rangelength: [1, 10],
                },

                edit_fPointAmount: {
                    required: true,
                    digits: true,
                    rangelength: [1, 20],
                }
            },
            messages: {
                edit_fModuleType: {
                    required: "请输入设备型号",
                    rangelength: jQuery.validator.format("密码应为3-20位的英文字母、数字字符"),
                    remote: jQuery.validator.format("{0} is already in use")
                },
                edit_fTypeCode: {
                    required: "请输入模块类型代码",
                    digits: "请输入整数",
                    minlength: jQuery.validator.format("Enter at least {0} characters")
                },
                edit_fPointAmount: {
                    required: "请输入模块点数",
                    digits: "请输入整数",
                    minlength: jQuery.validator.format("Enter at least {0} characters")
                },
            },
            submitHandler: function (form)
            {
                editMtForm(form);
            }
        });

        function editMtForm(form)
        {
            //提交之前，先取出每个select标签的value，拼接后给fPointTypeCl字段
            var num = $("#edit_fPointAmount").val();
            var j = 1;
            var cl = "";
            //取出select标签的value值
            for (var i = 0; i < num; i++)
            {
                cl += $("#pointType" + j + "").val();
                j++;
            }
            //验证模块点类型和模块点数是否相同
            if (cl.length < num)
            {
                swal("重要数据未填写", "请选择模块点类型", "error");
                return;
            }

            $("#edit_fPointTypeCl").val(cl);

            $.ajax({
                url: _ctx + "/view/basedatamanage/eqmanage/moduleType_update",
                type: "post",
                data: ({
                    fModuleType: $("#edit_fModuleType").val(),
                    fTypeCode: $("#edit_fTypeCode").val(),
                    fPointAmount: $("#edit_fPointAmount").val(),
                    fDescription: $("#edit_fDescription").val(),
                    fPointTypeCl: $("#edit_fPointTypeCl").val()
                }),
                beforeSend: function ()
                {
                    showLoad();
                },
                success: function (data)
                {
                    if (data.status == '1')
                    {
                        swal({
                            title: data.msg,// 展示的标题
                            text: "",// 内容
                            type: "success",
                            showCloseButton: false, // 展示关闭按钮
                            allowOutsideClick: false,
                            showConfirmButton: false,
                            timer: 1000
                        });
                        basedatamanage_eqmanage_moduleType.reLoadModuleType("");
                        $('#modal-form-editmoduletype').modal('hide');//关闭编辑窗口
                    } else
                    {
                        swal("修改失败!", data.msg, "error");
                    }
                },
                complete: function ()
                {
                    hiddenLoad();
                },
                error: function (data)
                {
                    swal("修改失败!", data.msg, "error");
                }
            });
        }


        //删除数据
        $(document).on('click', '#mtTable button.delete', function ()
        {
            var row = $(this).parents("tr")[0];
            var fModuleType = $(this).data("id");
            swal({
                        title: "您确定要删除吗?",
                        text: "设备型号信息删除后将不可恢复!",
                        type: "warning",
                        showCancelButton: true,
                        confirmButtonColor: "#1ab394",
                        confirmButtonText: "确定",
                        closeOnConfirm: false
                    },
                    function ()
                    {
                        // row.className="animated bounceOut";
                        setTimeout(function ()
                        {
                            $.ajax({
                                url: _ctx + "/view/basedatamanage/eqmanage/moduleType_del",
                                type: "post",
                                data: {
                                    "fModuleType": fModuleType
                                },
                                beforeSend: function ()
                                {
                                    showLoad();
                                },
                                success: function (data)
                                {
                                    if (data.status == '1')
                                    {
                                        $("#mtTable").tabulator("setData");
                                        swal({
                                            title: data.msg,// 展示的标题
                                            text: "",// 内容
                                            type: "success",
                                            showCloseButton: false, // 展示关闭按钮
                                            allowOutsideClick: false,
                                            showConfirmButton: false,
                                            timer: 1000
                                        });
                                        // window.location.href="${ctx }/view/user/user";
                                        basedatamanage_eqmanage_moduleType.reLoadModuleType("");
                                    } else
                                    {
                                        swal("删除失败!", data.msg, "error");
                                    }
                                },
                                complete: function ()
                                {
                                    hiddenLoad();
                                },
                                error: function (data)
                                {
                                    swal("删除失败!", data.msg, "error");
                                }
                            });
                        }, 100)
                    });
        });

        //验证码在编辑模态框出现前加载
        $("#modal-form-editmoduletype").on('show.bs.modal', function (event)
        {
            var button = $(event.relatedTarget);
            var fModuleType = button.data("id");
            //$("#editMtForm").load(_ctx + "/view/basedatamanage/eqmanage/moduleType_edit/"+ fModuleType);//加载用户信息
            $.ajax({
                type: "post",
                url: _ctx + '/view/basedatamanage/eqmanage/moduleType_edit',
                data: {"fModuleType": fModuleType},
                beforeSend: function ()
                {
                    showLoad();
                },
                success: function (result)
                {
                    $("#edit_fModuleType").val(result.data.fModuleType);
                    $("#edit_fTypeCode").val(result.data.fTypeCode);
                    $("#edit_fPointAmount").val(result.data.fPointAmount);
                    $("#edit_fDescription").val(result.data.fDescription);
                    editTable(result.data.fPointAmount, result.data.fPointTypeCl);
                },
                complete: function ()
                {
                    hiddenLoad();
                },
                error: function (data)
                {
                    swal("加载失败!", data.msg, "error");
                }
            });
        });

        function editTable(rownum, fPointTypeCl)
        {
            var str = "";
            for (var i = 1; i <= rownum; i++)
            {
                str += "<tr>";
                str += "<td id='num' style='width: 60px;' align='center'>" + i + "</td>";
                str += "<td id='type' align='center'><select id='pointType" + i + "' class='selector'>";
                str += "<option value ='' >" + "请选择" + "</option>";
                str += "</select></td></tr>";
            }

            $("#edit_modulePointTbody").html(str);
            //获取下拉框列表
            for (var i = 1; i <= rownum; i++)
            {
                select_modulepoint_type("pointType" + i + "");
            }
            //给下拉框设定默认值
            for (var i = 1; i <= rownum; i++)
            {
                var fPointTypeCl = fPointTypeCl;
                var j = fPointTypeCl[i - 1];
                $("#pointType" + i + "").val(j);

            }
        }

        return {
            // 分页查询
            reLoadModuleType: function (datas)
            {
                $.ajax({
                    url: _ctx + '/view/basedatamanage/eqmanage/moduleType_page',
                    type: "post",
                    data: datas,
                    beforeSend: function ()
                    {
                        showLoad();
                    },
                    success: function (data)
                    {
                        $('#moduleType_ibox').html(data);
                    },
                    complete: function ()
                    {
                        hiddenLoad();
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown)
                    {
                        toastr.error('', '查询失败');
                    }
                });
            },
            //根据模块点数创建table
            createTable: function (rownum)
            {
                var str = "";
                for (var i = 1; i <= rownum; i++)
                {
                    str += "<tr>";
                    str += "<td id='num' style='width: 60px;' align='center'>" + i + "</td>";
                    str += "<td id='type' align='center'><select id='pointType" + i + "' class='selector'>";
                    str += "<option value =''>" + "请选择" + "</option>";
                    str += "</select></td></tr>";
                }

                $("#modulePointTbody").html(str);
                //获取下拉框列表
                for (var i = 1; i <= rownum; i++)
                {
                    select_modulepoint_type("pointType" + i + "");
                }
            },
            //编辑时，根据模块点数创建table
            createEditTable: function (rownum)
            {
                var str = "";
                for (var i = 1; i <= rownum; i++)
                {
                    str += "<tr>";
                    str += "<td id='num' style='width: 60px;' align='center'>" + i + "</td>";
                    str += "<td id='type' align='center'><select id='pointType" + i + "' class='selector' >";
                    str += "<option value =''>" + "请选择" + "</option>";
                    str += "</select></td></tr>";
                }
                $("#edit_modulePointTbody").html(str);
                //获取下拉框列表
                for (var i = 1; i <= rownum; i++)
                {
                    select_modulepoint_type("pointType" + i + "");
                }
            },


            pageInit: function ()
            {
                basedatamanage_eqmanage_moduleType.reLoadModuleType();
            }
        }

    })(jQuery, window, document);
    basedatamanage_eqmanage_moduleType.pageInit();
</script>
