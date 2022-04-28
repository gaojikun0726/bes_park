/**
 * 导入CAD
 */
var ImportCad = {};
layui.use(['upload','layer','form'], function() {
    var layer = layui.layer;
    var upload = layui.upload;
    var form = layui.form;
    //修改弹窗
    var editIndex;
    //修改操作点击的图片
    var editJquery;


    $(function(){
        initUpload();
        contextMenu();
        // initEvent();
    });

    function initUpload(){
        //*.dwg   image/vnd.dwg   AutoCAD Drawing Database
        // *.dxf   image/vnd.dxf   AutoCAD Drawing Interchange Format
        //执行实例
        upload.render({
            elem: '#design_import_cad' //绑定元素
            ,accept: 'images'//允许上传的文件类型
            ,acceptMime: 'image/*'
            // ,exts:"dwg|dxf|dwt"
            // ,acceptMime:"image/vnd.dwg,image/vnd.dxf"
            ,size:1024  //单位kb
            ,url: _ctx + '/fileInfo/uploadFile' //上传接口
            ,done: function(res){
                //上传完毕回调
                if(res && res.fileUrl){
                    $("#design_area_demo").append('<div class="design_cad"><img width="100%" ' +
                        'height="100%"  src="'+ res.fileUrl +'"></div>');
                    $(".design_cad").draggable().resizable({handles:"all"});
                }
            }
            ,error: function(){
                //请求异常回调
            }
        });
    }

    //右键菜单
    function contextMenu(){
        $.contextMenu({
            selector: '#design_area_demo .design_cad', //右键选择器
            callback: function(key, options) {//点击回调处理
                var $dom = options.$trigger;
                if(key === "update"){
                    //修改
                    editJquery = $dom;
                    var width = options.$trigger.css('width');
                    var height = options.$trigger.css('height');
                    width = width.replace("px","");
                    height = height.replace("px","");
                    $("#design_edit_cad_width").val(width);
                    $("#design_edit_cad_height").val(height);
                    ImportCad.openEditWin();
                }
                // if(key === "relative"){
                //     //关联点
                //     relativeJquery = $dom;
                //     ImportCad.openRelativeWin();
                //
                // }
                if(key === "delete"){
                    //删除
                    layer.confirm('确定删除该图片吗?', {title:'删除提示'}, function(index){
                        $dom.remove();

                        layer.close(index);
                    });

                }
                if(key === "fix"){
                    //固定
                    $dom.draggable({ disabled: true })
                        .resizable({ disabled: true });
                    // $("#designDiv #design_area_demo .design_cad div").remove();
                }
                if(key === "drag"){
                    //拖动
                    $dom.draggable({ disabled: false })
                        .resizable({ disabled: false });
                        // .removeClass("ui-resizable-disabled")
                        // .resizable({handles:"all"});
                }
            },
            items: {//菜单列表配置
                "update": {name: "修改", icon: "fa-edit",disabled: false},
                // "relative": {name: "关联点", icon: "fa-paperclip",disabled:false},
                "delete":{name:"删除",icon:'fa-trash-o'}
                ,"fix":{name:"固定",icon:'fa-map-marker'}
                //fa-crosshairs  fa-arrows-alt
                // ,"fix2":{name:"拖动",icon:'fa-crosshairs'}
                ,"drag":{name:"拖动",icon:'fa-arrows'}
            }
        });
    }

    //打开修改弹窗
    ImportCad.openEditWin = function(){
        editIndex = layer.open({
            type: 1,
            title:"图片编辑",
            area:['34vw','38vh'],
            // area:['400px','300px'],
            content: $('#design_edit_cad_win'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
            cancel: function(index, layero){
                ImportCad.closeEditWin();
                return false;
            }
        });
    }

    //修改弹窗--确定按钮
    ImportCad.editConfirm = function (){
        var width = $("#design_edit_cad_width").val();
        var height = $("#design_edit_cad_height").val();
        editJquery.css("width",width+"px");
        editJquery.css("height",height+"px");
        layer.close(editIndex);
        ImportCad.clearEditWin();
    }

    //清空修改弹窗
    ImportCad.clearEditWin = function(){
        $("#design_edit_cad_width").val("");
        $("#design_edit_cad_height").val("");
    }

    // 取消按钮--关闭修改弹窗
    ImportCad.closeEditWin = function(){
        layer.close(editIndex);
        DebugBtn.clearEditBtnWin();
    }


//        function initEvent(){
//
//         $("#design_import_cad").change(function(){
//             var file = this.files[0];
//             this.value = "";
//             // this.files[0] = null;
//             // this.files = new FileList();
//             if(file){
//                 var formData = new FormData();
//                 formData.append("file", file);
//                 $.ajax({
//
//                     url: _ctx+"/fileInfo/uploadFile",
//                     type: 'POST',
//                     data: formData,  // 上传formdata封装的数据
//                     async: true,
//                     // 下面三个参数要指定，如果不指定，会报一个JQuery的错误               
//                     cache: false,         // 不缓存
// // 不设置内容类型  jQuery不要去设置Content-Type请求头
//                     contentType: false,
//                     processData: false,  // jQuery不要去处理发送的数据
//                     success: function (res) {            //成功回调
//                         // if (msg == "新增成功!") {
//                         //     layer.alert(data);
//                         // } else {
//                         //     layer.alert(data);
//                         //
//                         // }
//                         if(res && res.fileUrl){
//                             $("#design_area_demo").append('<div class="design_cad"><img width="100%" ' +
//                                 'height="100%"  src="'+ res.fileUrl +'"></div>');
//                             $(".design_cad").draggable().resizable({handles:"all"});
//                             layer.msg("上传成功",{icon:1});
//                         }else{
//                             layer.msg("上传失败",{icon:2});
//                         }
//                     },
//
//                     error: function (data) {
//
//                         alert(data);
//
//                     }
//
//                 });
//             }
//         });
//     }
});