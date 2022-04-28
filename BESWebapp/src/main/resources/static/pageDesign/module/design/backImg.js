/**
 * 背景图片操作
 */
var BackImg = {};

layui.use(['upload','layer','form'], function(){
    var layer = layui.layer;
    var upload = layui.upload;
    //上传文件的最大值,单位是B
    var maxImgSize = 1048576;

    var uploadInstance;

    var index;

    $(function(){
        // backImgExist();
        initUpload();
    });

    //判断背景图片是否存在
     BackImg.backImgExist = function(){
         var image = $("#design_back_div").css("backgroundImage");
        if((!image) || image === "none"){
            //背景图片为空
            // uploadInstance.upload();
            // $("#design_back_img_win").click();
            // $("#design_back_img_judge").hide();
            $("#design_add_back_img").click();
                // .trigger("click");
            return true;
        }else{

            index = layer.open({
                type: 1,
                title:"编辑背景图片",
                area:['300px','200px'],
                content: $('#design_back_img_win'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
                cancel: function(index, layero){
                    BackImg.closeWin();
                    return false;
                }
            });
            return false;
        }
    }


    //图片上传方法
    function initUpload(){
        //执行实例
        uploadInstance = upload.render({
            elem: '#design_add_back_img' //绑定元素
            ,accept: 'images'//允许上传的文件类型
            ,size:1024  //单位kb
            ,url: _ctx + '/fileInfo/uploadFile' //上传接口
            ,done: function(res){
                //上传完毕回调
                if(res && res.fileUrl){
                    $("#design_back_div")
                        .css("background","url("+res.fileUrl+") no-repeat 100% 100% " )
                        .css("background-size","cover")
                        .css("-webkit-background-size","cover")
                        .css("-o-background-size","cover")
                        .css("background-position","center 0");
                }
            }
            // //不好用，obje.files是undefined
            // ,choose:function (obj) {
            //     if(obj && obj.files && obj.files.length > 0){
            //         var size = obj.files[0].size;
            //         if(size > maxImgSize){
            //             layer.msg('图片大小不超过1M');
            //             return false;
            //         }
            //     }
            // }
            // //图片过大时阻止不了文件上传
            // ,before:function (obj) {
            //     //将每次选择的文件追加到文件队列
            //     var files = obj.pushFile();
            //
            //     //预读本地文件，如果是多文件，则会遍历。(不支持ie8/9)
            //     obj.preview(function(index, file, result){
            //         console.log(index); //得到文件索引
            //         console.log(file); //得到文件对象
            //         console.log(result); //得到文件base64编码，比如图片
            //         if(file.size > maxImgSize){
            //             layer.msg('图片大小不超过1M');
            //             return false;
            //         }
            //
            //         //obj.resetFile(index, file, '123.jpg'); //重命名文件名，layui 2.3.0 开始新增
            //
            //         //这里还可以做一些 append 文件列表 DOM 的操作
            //
            //         //obj.upload(index, file); //对上传失败的单个文件重新上传，一般在某个事件中使用
            //         delete files[index]; //删除列表中对应的文件，一般在某个事件中使用
            //     });
            // }
            ,error: function(){
                //请求异常回调
            }
        });
    }

    //删除背景图片
    BackImg.delete = function(){
        $("#design_back_div").css("backgroundImage","");
        BackImg.closeWin();
    }

    //保留背景图片
    BackImg.cancel = function () {
       BackImg.closeWin();
    }

    //更换背景图片
    BackImg.change = function () {
        // $("#design_back_img_win").trigger("click");
        $("#design_add_back_img").click();
        // uploadInstance.upload();
        BackImg.closeWin();
    }

    //关闭弹窗
    BackImg.closeWin = function () {
        layer.close(index);
    }

});