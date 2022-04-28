<#--
描述： 编程页面
作者： liuzhen
-->
<style>
    #ddcbc-import-form-control-btn .form-control{
        height: 28px!important;
        line-height: 1.8;
    }
    #ddcbc-import-form-control-btn .btn-primary{
        width:6.5vw!important;
        height: 3.85vh!important;
    }

</style>

<div class="attrInfo">
    <div class="frist_attr" >
            <div class="has-success">
              <label class="col-sm-2 control-label" style="font-size:15px;">当前位置</label>
              <label style="font-size: 15px;" id="ddcinfobc_allpath">${besSbPzStruct.f_allpath}</label>
            </div>
            <div class="vertical-timeline-block eqTreeAttrLineWidth">
                <form  id="ddcbc_form" enctype="multipart/form-data" style="text-align: center">
                    <textarea id="ddcbcInfoText" value="${besSbPzStruct.f_txt}" class='form-control' name="ddcbcInfoText" placeholder="请输入文本" style='width:80%;height:40vh;margin: 0 auto'>${besSbPzStruct.f_txt}</textarea>
                    <input type="text" value="${besSbPzStruct.f_sys_name}" id="ddcbc_sys_name" name="ddcbc_sys_name" style="display: none;" />
                    <input style="display: none;"  type="file" name="file"  class="file-loading">
                    <input type="text" value="${besSbPzStruct.f_type}" id="ddcbc_f_type" name="ddcbc_f_type" style="display: none;" />
                    <input type="text" id="f_id" name="f_id" value="${besSbPzStruct.f_id}" TYPE="hidden" style="display: none;" />
                    <div style="margin-top: 10px">
                        <button type="button" onclick="basedatamanage_eqmanage_eqconfiguration_ddcbc.impReport()" class="btn btn-default" style="height: 30px;width: 80px;" data-dismiss="modal">上传文件</button>
                        <button type="button" onclick="basedatamanage_eqmanage_eqconfiguration_ddcbc.ddcbcUpload('#ddcbc_form')" class="btn btn-default" style="height: 30px;width: 80px;" data-dismiss="modal">提交文本</button>
                        <a href="javascript:void(0);" style="padding-top: 1.25vh;" class="btn btn-default" onclick="basedatamanage_eqmanage_eqconfiguration_ddcbc.btn_exp()" data-dismiss="modal">下载程序</a>
                    </div>

                </form>
            </div>
    </div>
</div>
<!-- 上传模态框 -->
<div class="modal fade import-Model" id="ddcbc_import_Model" tabindex="-1" role="dialog" data-backdrop="false" aria-labelledby="ImportmyModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 41%;margin: 0 auto;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="ImportmyModalLabel">导入文件</h4>
            </div>
            <div class="modal-body">
                <div class="row" id="ddcbc-import-form-control-btn">
                    <div class="col-md-12" style="padding-top: 2vh;">
                        <form id="ddcbc_reportImpExcel" >
                            <input id="ddcbc_exportInputFile" type="file" name="file"  class="file-loading">
                            <textarea  class='form-control' name="ddcbcInfoText" placeholder="请输入文本" style='display: none;'></textarea>
                            <input type="text" value="${besSbPzStruct.f_sys_name}" id="ddcbc_sys_name" name="ddcbc_sys_name" style="display: none;" />
                            <input type="text" value="${besSbPzStruct.f_node_attribution}" id="ddcbc_f_type" name="ddcbc_f_type" style="display: none;" />
                            <input type="text" id="f_id" name="f_id" value="${besSbPzStruct.f_id}" TYPE="hidden" style="display: none;" />
                        </form>
                    </div>
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" onclick="basedatamanage_eqmanage_eqconfiguration_ddcbc.ddcbcUpload('#ddcbc_reportImpExcel')">上传文件</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<script src="${ctx}/static/js/public-js.js"></script>
<script type="text/javascript">

;
var basedatamanage_eqmanage_eqconfiguration_ddcbc = (function($,window, document, undefined) {

    //fileinput 上传插件初始化
    function initFileinput(){
        $("#ddcbc_exportInputFile").fileinput({
            language: 'zh', //设置语言
            allowedFileExtensions : ['txt'],//接收的文件后缀,
            showUpload: true, //是否显示上传按钮
            showCaption: true,//是否显示标题
            browseClass: "btn btn-primary", //按钮样式
            maxFileSize:3072,//最大单个文件上传大小
            maxFileCount:1,//最大上传个数
            showUpload:false,//是否显示上传按钮
            showBrowse:true,//是否显示浏览按钮
            showPreview:false,//是否显示预览区域
            autoReplace:true,//是否自动替换
            showRemove:true,//是否显示移除按钮
            uploadExtraData:function(id,index){
                var importExcel = $("#ddcbc_exportInputFile").val();
                return {"fold":importExcel};
            },//区分不同的模块：fold：文件夹
            //uploasExtraData:是把页面你想要往后退传的东西放(return)到域里面然后去后台去取
        }).on("fileuploaded",function(){
            clearForm();//清空form表单
        }).on("filebatchuploadsuccess",function(){
            clearForm();//清空form表单
        });
    }

    //清空上传文件表单form 关闭模态框 并提示
    function clearForm(){
        $("#ddcbc_exportInputFile").fileinput("destroy");//销毁fileinput删除上传文件缓存
        $("#ddcbc_import_Model").modal("hide");//关闭模态框
    }
    return{
        //下载模板-- 提供两种思路 1.下载固定模板 可放到工作空间 2.生成模板下载
        btn_exp : function(){
            //1.下载固定模板
            var fname = "program.txt";
            var path = "WEB-INF\\file\\DDCprgram\\program.txt";
            var f_id = $("#f_id").val();
            FileDownloadbc("${ctx}/file/ExpfileDownloadbc",fname,path,f_id);

        },
        ddcbcUpload:function (formId)
        {
            var form =new FormData($(formId)[0] );     //通过id获取表单的数据
            if(formId == "#ddcbc_form")
            {
                if($("#ddcbcInfoText").val() == "")
                {
                    swal( "请输入文本","", "warning");
                    return;
                }
            }
            else
            {
                if($("#ddcbc_exportInputFile").val() == "")
                {
                    swal( "请选择文件","", "warning");
                    return;
                }
            }

            $.ajax({
                type:"POST",//请求的类型
                url:"${ctx}/view/basedatamanage/eqmanage/ddcbc_upload",//请求的路径
                data: form, //请求的参数
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success: function (data) { //成功返回触发的方法
                    /*var txt =  $("#ddcbcInfoText").val();
                    $("#ddcbc_exportInputFile").val("");
                    $("#ddcbcInfoText").val(txt);*/
                   if (data.data.status == "1")
                   {
                       swal( data.data.msg,"", "success");
                       $("#ddcbcInfoText").val(data.data.data);
                   }
                   else if(data.data.status == "0"){
                       $("#ddcbcInfoText").val(data.data.data);
                       swal( data.data.msg,"", "error");
                   }else {
                       swal( data.status,"", "error");
                   }
                   $("#ddcbc_import_Model").modal("hide");

                },
                //请求失败触发的方法
                error:function(){
                    console.log("ajax请求失败");
                }
            });
        },
        impReport :function(){
            //1.先弹出上传文件模态框--2.再讲文件进行上传操作--前端工作完成--先下载导入模板
            $("#ddcbc_import_Model").modal("show");
        },
        pageInit:function () {
            initFileinput();
        }/*,

        ddcdownload : function () {
            $.ajax({
                url : _ctx + "/view/basedatamanage/eqmanage/ddcdownload",
                type : "post",
                data :{},
                success : function (result) {
                    if (result.status == "1"){
                        $("#f_sys_name").attr("readonly", "readonly");//系统名称
                    }else {
                        return
                    }
                }
            });
        }*/
   }


})(jQuery, window, document);
basedatamanage_eqmanage_eqconfiguration_ddcbc.pageInit();
</script>
<script src="${ctx}/static/js/equipmentTree/equipmentTreeCommon.js"></script><!-- 引入共通Js -->
