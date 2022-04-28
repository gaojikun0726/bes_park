/**
 * 添加图片操作
 */
var AddImg = {
    uploadBtn:""
};
layui.use(['upload','layer','form'], function(){
    var layer = layui.layer;
    var upload = layui.upload;
    var form = layui.form;
    //修改弹窗,关联点弹窗
    var editIndex,relativeIndex;
    //修改操作点击的图片，关联操作点击的图片
    var editJquery,relativeJquery;

    //上传文件的最大值1M,单位是B
    var maxImgSize = 1048576;

    $(function(){
        // initUpload();
        contextMenu();
        AddImg.initEvent();
    });
    // //图片上传方法
    // function initUpload(){
    //     //执行实例
    //     upload.render({
    //         elem: '#design_add_img' //绑定元素
    //         ,accept: 'images'//允许上传的文件类型
    //         ,size:1024  //单位kb
    //         ,url: _ctx + '/fileInfo/uploadFile' //上传接口
    //         ,done: function(res){
    //             //上传完毕回调
    //             if(res && res.fileUrl){
    //                 //向表格的选中单元格内添加元素
    //                 if(AddTable.selectedTable){
    //                     AddTable.selectedTable.append('<img class="design_img" src="'+ res.fileUrl +'">');
    //                     $( ".design_area .design_img" ).click(AttributeWin.clickEvent).draggable({containment: "parent"});
    //                     return;
    //                 }
    //
    //                 $("#design_area_demo").append('<img class="design_img" src="'+ res.fileUrl +'">');
    //                 $(".design_img").click(AttributeWin.clickEvent).draggable();
    //             }
    //         }
    //         ,error: function(){
    //             //请求异常回调
    //         }
    //     });
    // }


    //右键菜单
    function contextMenu(){
        $.contextMenu({
            selector: '#design_area_demo .design_img', //右键选择器
            callback: function(key, options) {//点击回调处理
                var $dom = options.$trigger;
                if(key === "update"){
                    //修改
                    editJquery = $dom;
                    var width = options.$trigger.css('width');
                    var height = options.$trigger.css('height');
                    width = width.replace("px","");
                    height = height.replace("px","");
                    $("#design_edit_img_width").val(width);
                    $("#design_edit_img_height").val(height);
                    AddImg.openEditWin();
                }
                if(key === "relative"){
                    //关联点
                    relativeJquery = $dom;
                    AddImg.openRelativeWin();

                }
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
                }
                if(key === "drag"){
                    //拖动
                    $dom.draggable({ disabled: false,})
                        .resizable({ disabled: false,handles:"n,e" });
                }

                AddImg.changeDomElementPos($dom);
            },
            items: {//菜单列表配置
                "update": {name: "修改", icon: "fa-edit",disabled: false},
                "relative": {name: "关联点", icon: "fa-paperclip",disabled:false},
                "delete":{name:"删除",icon:'fa-trash-o'}
                ,"fix":{name:"固定",icon:'fa-map-marker'}
                ,"drag":{name:"拖动",icon:'fa-arrows'}
            }
        });
    }


    AddImg.changeDomElementPos = function(dom){
        var parEle = dom.parent();
        parEle.css("overflow","visible");
    }
    //打开修改弹窗
    AddImg.openEditWin = function(){
        editIndex = layer.open({
            type: 1,
            title:"图片编辑",
            area:['34vw','38vh'],
            // area:['400px','300px'],
            content: $('#design_editImgWin'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
            cancel: function(index, layero){
                AddImg.closeEditWin();
                return false;
            }
        });
    }

    //修改弹窗--确定按钮
    AddImg.editConfirm = function (){
        var width = $("#design_edit_img_width").val();
        var height = $("#design_edit_img_height").val();
        editJquery.css("width",width+"px");
        editJquery.css("height",height+"px");
        layer.close(editIndex);
        AddImg.clearEditWin();
    }

    //清空修改弹窗
    AddImg.clearEditWin = function(){
        $("#design_edit_img_width").val("");
        $("#design_edit_img_height").val("");
    }

    // 取消按钮--关闭修改弹窗
    AddImg.closeEditWin = function(){
        layer.close(editIndex);
        DebugBtn.clearEditBtnWin();
    }

    //关联点弹窗
    AddImg.openRelativeWin = function(){
        // var dataId = relativeJquery.attr("data-id");
        relativeIndex = layer.open({
            type: 1,
            title:"图片关联",
            area:['58vw','65vh'],
            // area:['600px','500px'],
            content: $('#design_img_relative'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
            cancel: function(index, layero){
                AddImg.closeRelativeWin();
                return false;
            },
            success:function () {
                AddImg.resetConfig();
                // var $table = $("#design_img_relative_table tbody");
                //                 // $table.html($("#design_img_default_content tbody").html());
                //                 // form.render();
                if(relativeJquery){
                    var dataId = relativeJquery.attr("data-id");
                    var title = relativeJquery.attr("title");
                    if(dataId){
                        $("#design_img_relative_id").val(dataId);
                        $("#design_img_relative_name").val(title);
                        AddImg.initConfigHtml();
                    }
                }
            }
        });
    }

    /**
     * 还原关联配置初始状态
     */
    AddImg.resetConfig = function(){
        $("#design_img_relative .intervalDataDiv").html("");
        $("#design_img_relative .dataDiv").html("");
        $("#design_img_relative input[type=radio][name=imgConfigType][value='1']").prop("checked",true);
    }

    //关闭并清空关联点弹窗
    AddImg.closeRelativeWin = function(){
        layer.close(relativeIndex);
        $("#design_img_relative_id").val("");
        $("#design_img_relative_name").val("");
        // $("#design_img_relative_table").html("");
        AddImg.resetConfig();
        relativeJquery = "";
    }

    // //关联点弹窗--关联按钮点击事件
    // AddImg.relativeBtn = function () {
    //     var dataId = $("#design_img_relative_id").val();
    //     if(!dataId){
    //         layer.msg("请选择关联点",{icon:0});
    //         return;
    //     }
    //     //将关联配置保存到页面上
    //     var $table = $("#design_img_relative_table tbody");
    //     var len = $table.find("tr").length;
    //     var configData = "";
    //     for(var i = 0; i < len; i++){
    //         var state = $table.find("tr:eq("+i+") .img_point_state").val();
    //         var imgState = $table.find("tr:eq("+i+") .uploadIcon").attr("src");
    //         var item = "dataId$"+dataId+",state$"+state+",imgState$"+imgState+";";
    //         configData += item;
    //     }
    //     var imgNum = $table.find("tr .uploadIcon.relativeImg").length;
    //     if(imgNum !== len){
    //         //图片数量不足
    //         layer.msg("请上传图片",{icon:0});
    //         return;
    //     }
    //     var defaultImg = $table.find("tr:eq(0) .uploadIcon").attr("src");
    //     if(relativeJquery){
    //         //已有图片
    //         relativeJquery.attr("src",defaultImg);
    //         relativeJquery.attr("data-id",dataId).attr("title",dataId);
    //         relativeJquery.attr("data-config",configData);
    //     }else{
    //         //第一次添加图片
    //         var imgHtml = '<img class="design_img" src="'+ defaultImg +'" title="'+dataId+'" data-id="'+dataId+'" data-config="'+configData+'">';
    //        AddImg.imgAppend(imgHtml);
    //         $(".design_img").attr("tabindex",0);
    //         CopyPaste.copyImg();
    //     }
    //
    //     AddImg.closeRelativeWin();
    // }

    // /**
    //  * 查询关联点信息
    //  */
    // AddImg.getNodeInfo = function(dataId){
    //     $.ajax({
    //         url     : _ctx + '/btnEventController/getDebugInfo',
    //         type    : "post",
    //         dataType: 'json',
    //         data : {
    //             sysname : dataId
    //         },
    //         success : function(result) {
    //             if(result.data && result.data.F_NODE_TYPE){
    //                 //查询下拉框配置，类似调试按钮DO类型
    //                 AddImg.getConfigHtml(dataId);
    //             }
    //         }});
    // }
    // /**
    //  * 查询节点对应配置下拉框数据
    //  */
    // AddImg.getConfigHtml = function(sysname){
    //
    //     $.ajax({
    //         //selectNodesConfigSetting使用f_sys_name_old查询，修改为/selectDesignNodesConfig（f_sys_name）
    //         url : _ctx +"/view/basedatamanage/eqmanage/selectDesignNodesConfig",
    //         type : "post",
    //         data : {
    //             f_sys_name :sysname
    //         },
    //         success : function(result){
    //             var $table = $("#design_img_relative_table tbody");
    //             if(result.list && result.list[0]){
    //                 var len = result.list.length;
    //
    //                 //配置下拉框
    //                 var configHtml = '';
    //                 for(var i = 0; i < len; i++){
    //                     configHtml += '<option value="'+result.list[i].F_VALUE+'">'+result.list[i].F_DESC+'</option>';
    //                 }
    //                 $table.html("");
    //                 var content = "";
    //                 for(var k = 0; k < result.list.length; k++){
    //                     content+=' <tr><td><select class="img_relative_select img_point_state" >';
    //                     content += configHtml;
    //                     content += '</select></td><td>等于</td><td>';
    //                     content += '<div class="uploadDiv">'+
    //                         '<img class="uploadIcon" src="'+_ctx+'/static/pageDesign/icon/menu/upload_img6.png">'+
    //                         '<input class="uploadBtn" type="file" title="添加图片" onchange="AddImg.uploadRelativeImg(this)">'+
    //                         '</div>';
    //                     content+= '</td></tr>';
    //                 }
    //                 $table.append(content);
    //             }else{
    //                 //如果没有配置数据，显示默认数据
    //             }
    //
    //             form.render();
    //             AddImg.fillConfigData();
    //         },
    //         error:function(){
    //             layer.msg("操作失败",{icon:2});
    //         }
    //     });
    // }
    // /**
    //  * 回显配置数据
    //  */
    // AddImg.fillConfigData = function(){
    //     var $table = $("#design_img_relative_table");
    //     var configData = relativeJquery.attr("data-config");
    //     if(configData){
    //         var array = configData.split(";");
    //         for(var k = 0; k < array.length;k++){
    //             var item = array[k].split(",");
    //             if(item.length === 3){
    //                 var state = item[1].split("$")[1];
    //                 var imgState = item[2].split("$")[1];
    //                 $table.find("tr:eq("+(k+1)+") .img_point_state option[value="+state+"]").attr("selected","selected");
    //                 $table.find("tr:eq("+(k+1)+") .uploadIcon").attr("src",imgState);
    //             }
    //         }
    //         form.render();
    //     }
    // }

    /**
     * 添加图片html
     */
    AddImg.imgAppend = function(imgHtml){
        //向表格的选中单元格内或向选中的背景区域内添加元素
        if(AddTable.selectedTable || BackColor.selectedArea){
            var $parent = AddTable.selectedTable || BackColor.selectedArea;
            $parent.append(imgHtml);
            //$parent.find( ".design_img" ).unbind("click").click(AttributeWin.clickEvent).draggable({containment: "parent"});
        }else{
            $("#design_area_demo").append(imgHtml);
            //$(".design_img").unbind("click").click(AttributeWin.clickEvent).draggable();
        }
    }

    //关联弹窗--选择点按钮点击事件
    AddImg.choosePoint = function () {
        PointTree.$nodeId = $("#design_img_relative_id");
        PointTree.$nodeName = $("#design_img_relative_name");
        PointTree.openPointTreeWin();
    }

    /**
     * 关联图片之前校验节点是否满足条件(新)
     */
    AddImg.verifyImageNew = function(){
        var sysname = PointTree.$nodeId.val();
        $.ajax({
            url     : _ctx + '/btnEventController/getPointInfo',
            type    : "post",
            dataType: 'json',
            data : {
                sysname : sysname
            },
            success : function(result) {
                if(result.status === '1' && result.map){
                    var map = result.map;
                    // AddImg.nodeType = map.B_TYPE;
                    // AddImg.unit = map.F_ENGINEER_UNIT;
                    if(map.B_TYPE === 16){
                        AddImg.queryVisualType(map,sysname);
                    }else{
                        AddImg.initTreeConfigData(map,sysname);
                    }
                    PointTree.closeTreeWin();
                }else{
                    layer.msg("请先配置该点信息",{icon:2});
                }
            },
            error : function(result) {
            }
        });
    }

    /**
     * 查询虚点类型
     * @param map 关联点信息
     * @param sysname 关联点名称
     */
    AddImg.queryVisualType = function(map,sysname){
        $.ajax({
            url     : _ctx + '/btnEventController/getVisualType',
            type    : "post",
            dataType: 'json',
            data : {
                sysname : sysname
            },
            success : function(result) {
                if(result && result.data){
                    var nodeType = result.data;
                    var vType = "";
                    //4,5,6,7  AI,AO,DI,DO
                    if(nodeType === "4"){
                        vType = "VAI";
                    }
                    if(nodeType === "5"){
                        vType = "VAO";
                    }
                    if(nodeType === "6"){
                        vType = "VDI";
                    }
                    if(nodeType === "7"){
                        vType = "VDO";
                    }
                    AddImg.initTreeConfigData(map,sysname,vType);
                }
            }});
    }
    // /**
    //  * 关联图片之前校验节点是否满足条件
    //  */
    // AddImg.verifyImage = function(){
    //     var sysname = PointTree.$nodeId.val();
    //     $.ajax({
    //         url     : _ctx + '/btnEventController/getDebugInfo',
    //         type    : "post",
    //         dataType: 'json',
    //         data : {
    //             sysname : sysname
    //         },
    //         success : function(result) {
    //             //只有DI或DO表中也存在记录时，才能作为关联点使用
    //             if(result.status === "0"){
    //                 layer.msg("请先配置该点信息",{icon:2});
    //                 return false;
    //             }else{
    //                 if(result.data){
    //                     // if(result.data.F_NODE_TYPE=="9"){//温控器模块
    //                     //     $("#relativeSettingInfo").css("display","none");
    //                     // }else{
    //                         //查询关联点配置，类似调试按钮DO类型
    //                         // $("#relativeSettingInfo").css("display","block");
    //                         // AddImg.getNodeConfigData(sysname);
    //                         AddImg.initTreeConfigData(sysname,result.data.F_NODE_TYPE);
    //                         AddImg.nodeType = result.data.F_NODE_TYPE;
    //                         AddImg.unit = result.data.F_ENGINEER_UNIT;
    //                     // }
    //                 }
    //                 PointTree.closeTreeWin();
    //                 return true;
    //             }
    //         }});
    // }


    /**
     * 查询节点对应配置下拉框数据
     */
    AddImg.getNodeConfigData = function(sysname){

        $.ajax({
            //selectNodesConfigSetting使用f_sys_name_old查询，修改为/selectDesignNodesConfig（f_sys_name）
            url : _ctx +"/view/basedatamanage/eqmanage/selectDesignNodesConfig",
            type : "post",
            data : {
                f_sys_name :sysname
            },
            success : function(result){
                var $table = $("#design_img_relative_table tbody");
                if(result.list && result.list[0]){
                    var len = result.list.length;
                    //配置下拉框
                    var configHtml = '';
                    for(var i = 0; i < len; i++){
                        configHtml += '<option value="'+result.list[i].F_VALUE+'">'+result.list[i].F_DESC+'</option>';
                    }
                    $table.html("");
                    var content = "";
                    for(var k = 0; k < result.list.length; k++){
                        content+=' <tr><td><select class="img_relative_select img_point_state" >';
                        content += configHtml;
                        content += '</select></td><td>等于</td><td>';
                        content += '<div class="uploadDiv">'+
                            '<img class="uploadIcon" src="'+_ctx+'/static/pageDesign/icon/menu/upload_img6.png">'+
                            '<input class="uploadBtn" type="file" title="添加图片" onchange="AddImg.uploadRelativeImg(this)">'+
                            '</div>';
                        content+= '</td></tr>';
                    }
                    $table.append(content);
                }else{
                    //如果没有配置数据，显示默认数据
                }
                //下拉框默认选中
                var selectArray = $table.find("select.img_point_state");
                for(i = 0; i < selectArray.length; i++){
                    $(selectArray[i]).find("option:eq("+i+")").prop("selected",true);
                }
                form.render();
            },
            error:function(){
                layer.msg("操作失败",{icon:2});
            }
        });
    }


    /**
     * 点状态关联的图片的上传方法
     * @param that
     */
    AddImg.uploadRelativeImg = function(that){
        AddImg.uploadBtn = $(that);
        var file = $(that)[0].files[0];
        var fileSize = file.size;
        if(fileSize > maxImgSize){
            layer.msg("上传图片大小不能超过1M");
            return;
        }
        var formData = new FormData();
        formData.append("file",file);
        $.ajax({
            url: _ctx + '/fileInfo/uploadFile', //上传接口
            type: 'POST',
            data: formData,
            async: true,
            cache: false,
            contentType: false,
            processData: false,
            dataType:'json',
            success: function(res) {
                if(res && res.fileUrl){
                    AddImg.uploadBtn.siblings(".uploadIcon").attr("src",res.fileUrl).addClass("relativeImg");
                }
            }
        })
    }


    /**
     * 关联点弹窗---关联按钮（新）
     */
    AddImg.relativeBtnNew = function(){
        var dataId = $("#design_img_relative_id").val();
        if(!dataId){
            layer.msg("请选择关联点",{icon:0});
            return;
        }
        var imgConfigType = $("#design_img_relative input[name=imgConfigType]:checked").val();
        if(imgConfigType === "1"){
            //状态量
            AddImg.stateRelative();
        }else if(imgConfigType === "2"){
            //区间量
            AddImg.intervalRelative();
        }
    }

    /**
     * 状态量关联方法
     */
    AddImg.stateRelative = function(){
        var dataId = $("#design_img_relative_id").val();
        var dataName = $("#design_img_relative_name").val();
        var configData = "";
        var defaultImg = "";
        var rows = $("#design_img_relative .configDiv .dataDiv .configRow");
        if(rows.length === 0){
            layer.msg("至少填写一行配置数据",{icon:0});
            return;
        }
        for(var i = 0; i < rows.length; i++){
            var row = $(rows[i]);
            var text = row.find("input.text").val();
            var num = row.find("input.num").val();
            var src = row.find(".bgDiv .uploadDiv .uploadIcon").attr("src");
            var checkResult = AddImg.stateCheck(text,num,src);
            if(!checkResult){
                return;
            }
            if( i === 0){
                defaultImg = src;
            }
            var item = "dataId$"+dataId+",state$"+num+",text$"+text+",src$"+src+";";
            configData += item;
        }
        var rowNum = rows.length;
        var imgNum = $("#design_img_relative .configDiv .dataDiv .relativeImg").length;
        if(imgNum !== rowNum){
            //图片数量不足
            layer.msg("请上传图片",{icon:0});
            return;
        }
        if(relativeJquery){
            //已有图片
            relativeJquery.attr("src",defaultImg);
            relativeJquery.attr("data-id",dataId).attr("title",dataName);
            relativeJquery.attr("data-config",configData);
        }else{
            //第一次添加图片
            var imgHtml = '<img class="design_img" src="'+ defaultImg +'" title="'+dataName+'" data-id="'+dataId+'" data-config="'+configData+'">';
            AddImg.imgAppend(imgHtml);
            $(".design_img").attr("tabindex",0);
            CopyPaste.copyImg();
        }
        AddImg.closeRelativeWin();
    }

    /**
     * 区间量关联方法
     */
    AddImg.intervalRelative = function(){
        var dataId = $("#design_img_relative_id").val();
        var configData = "";
        var defaultImg = "";
        var rows = $("#design_img_relative .configDiv .intervalDataDiv .configRow");
        if(rows.length === 0){
            layer.msg("至少填写一行配置数据",{icon:0});
            return;
        }
        var intervals = [];
        for(var i = 0; i < rows.length; i++){
            var row = $(rows[i]);
            var text = row.find("input.text").val();
            var min = row.find("input.min").val();
            var max = row.find("input.max").val();
            var src = row.find(".bgDiv .uploadDiv .uploadIcon").attr("src");
            var checkResult = AddImg.intervalCheck(text,min,max,src);
            if(!checkResult){
                return;
            }
            if( i === 0){
                defaultImg = src;
            }
            intervals.push([Number(min),Number(max)]);
            var item = "dataId$"+dataId+",min$"+min +",max$"+max+",text$"+text+",src$"+src+";";
            configData += item;
        }
        //判断区间范围是否重叠
        var overlap = AddTextbox.checkOverlap(intervals);
        if(overlap){
            return;
        }
        var rowNum = rows.length;
        var imgNum = $("#design_img_relative .configDiv .intervalDataDiv .relativeImg").length;
        if(imgNum !== rowNum){
            //图片数量不足
            layer.msg("请上传图片",{icon:0});
            return;
        }
        if(relativeJquery){
            //已有图片
            relativeJquery.attr("src",defaultImg);
            relativeJquery.attr("data-id",dataId).attr("title",dataId);
            relativeJquery.attr("data-config",configData);
        }else{
            //第一次添加图片
            var imgHtml = '<img class="design_img" src="'+ defaultImg +'" title="'+dataId+'" data-id="'+dataId+'" data-config="'+configData+'">';
            AddImg.imgAppend(imgHtml);
        }
        AddImg.closeRelativeWin();
    }


    /**
     * 状态量校验
     */
    AddImg.stateCheck = function(text,num,src){
        var result = false;
        if(!text){
            layer.msg("显示文本不可为空",{icon:0});
            return result;
        }
        if(!CommonCheck.verifySpecialChar(text,"显示文本")){
            //为空或者包含特殊符号（可能影响config配置字符串解析，[$ , ; :]）
            return result;
        }
        if(!num){
            layer.msg("数值不可为空",{icon:0});
            return result;
        }
        if(!CommonCheck.verifyRealNumber(num,"数值")){
            //为空或者不是数字
            return;
        }
        if(!src){
            layer.msg("图片不可为空",{icon:0});
            return result;
        }
        return true;
    }


    /**
     * 区间量校验
     */
    AddImg.intervalCheck = function(text,min,max,src){
        var result = false;
        if(!text){
            layer.msg("显示文本不可为空",{icon:0});
            return result;
        }
        if(!CommonCheck.verifySpecialChar(text,"显示文本")){
            //为空或者包含特殊符号（可能影响config配置字符串解析，[$ , ; :]）
            return result;
        }
        if(!min){
            layer.msg("区间范围不可为空",{icon:0});
            return result;
        }
        if(!CommonCheck.verifyRealNumber(min,"区间范围")){
            //为空或者不是数字
            return;
        }
        if(!max){
            layer.msg("区间范围不可为空",{icon:0});
            return result;
        }
        if(!CommonCheck.verifyRealNumber(max,"区间范围")){
            //为空或者不是数字
            return;
        }
        if(Number(min) > Number(max)){
            layer.msg("区间范围无效",{icon:0});
            return;
        }
        if(!src){
            layer.msg("图片不可为空",{icon:0});
            return result;
        }
        return true;
    }
    /**
     * 配置弹窗--注册相关监听事件
     */
    AddImg.initEvent = function(){
        form.on('radio(imgConfigType)', function(data){
            if(data.value === "1"){
                $("#design_img_relative .configDiv .stateDiv").show();
                $("#design_img_relative .configDiv .intervalDiv").hide();
                $("#design_img_relative .configDiv .dataDiv").show();
                $("#design_img_relative .configDiv .intervalDataDiv").hide();
            }else if(data.value === "2"){
                $("#design_img_relative .configDiv .stateDiv").hide();
                $("#design_img_relative .configDiv .intervalDiv").show();
                $("#design_img_relative .configDiv .dataDiv").hide();
                $("#design_img_relative .configDiv .intervalDataDiv").show();
            }
        });

    }

    /**
     * 状态量--新增一行
     */
    AddImg.addStateRow = function(){
        var row = '<div class="configRow">\n' +
            '     <input type="text" class="form-control text">\n' +
            '     <input type="text" class="form-control num">\n' +
            '     <div class="bgDiv">\n' +
            '          <div class="uploadDiv">\n' +
            '              <img class="uploadIcon" src="'+_ctx+'/static/pageDesign/icon/menu/upload_img6.png">\n' +
            '               <input class="uploadBtn" type="file" title="添加图片" onchange="AddImg.uploadRelativeImg(this)">\n' +
            '          </div>\n' +
            '      </div>' +
            '</div>';
        $("#design_img_relative .dataDiv").append(row);
        form.render();
        AddImg.initStateEvent();
    }

    /**
     * 状态量--删除一行
     */
    AddImg.deleteStateRow = function(){
        $("#design_img_relative .configDiv .dataDiv input.selected_input").parent(".configRow").remove();
    }


    /**
     * 状态量配置数据--选中行点击事件
     */
    AddImg.initStateEvent = function(){
        $("#design_img_relative .configDiv .dataDiv input").unbind("click").click(function(){
            $("#design_img_relative .configDiv .dataDiv input").removeClass("selected_input");
            $(this).addClass("selected_input");
        });
    }


    /**
     * 区间量--新增一行
     */
    AddImg.addIntervalRow = function(){
        var dataId = $("#design_img_relative_id").val();
        var unit = "";
        if(dataId){
            //如果已有关联点，查询关联点单位
            $.ajax({
                url     : _ctx + '/btnEventController/getDebugInfo',
                type    : "post",
                dataType: 'json',
                data : {
                    sysname : dataId
                },
                success : function(result) {
                    if(result.data){
                        var f_unit = result.data.F_ENGINEER_UNIT;
                        if(f_unit){
                            unit = f_unit;
                        }
                    }
                    AddImg.getIntervalRowHtml(unit);
                }});
        }else{
            //没有关联点，单位为空
            AddImg.getIntervalRowHtml(unit);
        }

    }

    /**
     * 新增区间配置行时---拼接行html
     * @param unit
     */
    AddImg.getIntervalRowHtml = function(unit){
        var row = '<div class="configRow">\n' +
            '      <input type="text" class="form-control text">\n' +
            '      <div class="numDiv">\n' +
            '           <input type="text" class="min">\n' +
            '           <span>-</span>\n' +
            '           <input type="text" class="max">\n' +
            '            <span class="unit">'+unit+'</span>\n' +
            '       </div>\n' +
            '       <div class="bgDiv">\n' +
            '          <div class="uploadDiv">\n' +
            '              <img class="uploadIcon" src="'+_ctx+'/static/pageDesign/icon/menu/upload_img6.png">\n' +
            '               <input class="uploadBtn" type="file" title="添加图片" onchange="AddImg.uploadRelativeImg(this)">\n' +
            '          </div>\n' +
            '      </div>' +
            '</div>';
        $("#design_img_relative .intervalDataDiv").append(row);
        form.render();
        AddImg.initIntervalEvent();
    }

    /**
     * 区间量配置数据--选中行点击事件
     */
    AddImg.initIntervalEvent = function(){
        $("#design_img_relative .configDiv .intervalDataDiv input").unbind("click").click(function(){
            $("#design_img_relative .configDiv .intervalDataDiv input").removeClass("selected_input");
            $(this).addClass("selected_input");
        });
    }
    /**
     * 区间量--删除一行
     */
    AddImg.deleteIntervalRow = function(){
        $("#design_img_relative .configDiv .intervalDataDiv input.selected_input").parent(".configRow").remove();
    }

    /**
     * 初始化设备树的配置数据
     */
    AddImg.initTreeConfigData = function(map,sysname,vType){
        // AddImg.resetConfig();
        new Promise(function (resolve, reject){
            $.ajax({
                //selectNodesConfigSetting使用f_sys_name_old查询，修改为/selectDesignNodesConfig（f_sys_name）
                url : _ctx +"/view/basedatamanage/eqmanage/selectDesignNodesConfig",
                type : "post",
                data : {
                    f_sys_name :sysname
                },
                success : function(data){
                    resolve(data);
                }
            });
        }).then(function(result){
            if(result && result.list){
                //有配置数据
                AddImg.getConfigHtml(result);
            }else if(map.B_TYPE === "12" || map.B_TYPE === "13" || vType === "VDO"  || vType === "VDI"){
                if($("#design_img_relative .dataDiv").find(".configRow").length > 0){
                    //如果本来有配置数据，不再加载默认配置
                    return;
                }
                //DI/DO类型的点有默认配置，开（255）关（0）两种状态
                var content = '<div class="configRow">\n' +
                    '                            <input type="text" class="form-control text" value="开">\n' +
                    '                            <input type="text" class="form-control num" value="255">\n' +
                    '     <div class="bgDiv">\n' +
                    '          <div class="uploadDiv">\n' +
                    '              <img class="uploadIcon" src="'+_ctx+'/static/pageDesign/icon/menu/upload_img6.png">\n' +
                    '               <input class="uploadBtn" type="file" title="添加图片" onchange="AddImg.uploadRelativeImg(this)">\n' +
                    '          </div>\n' +
                    '      </div>' +
                    '                        </div>\n';
                $("#design_img_relative .dataDiv").append(content);
                form.render();
                content =  '<div class="configRow">\n' +
                    '                            <input type="text" class="form-control text" value="关">\n' +
                    '                            <input type="text" class="form-control num" value="0">\n' +
                    '     <div class="bgDiv">\n' +
                    '          <div class="uploadDiv">\n' +
                    '              <img class="uploadIcon" src="'+_ctx+'/static/pageDesign/icon/menu/upload_img6.png">\n' +
                    '               <input class="uploadBtn" type="file" title="添加图片" onchange="AddImg.uploadRelativeImg(this)">\n' +
                    '          </div>\n' +
                    '      </div>' +
                    '                        </div>';
                $("#design_img_relative .dataDiv").append(content);
                content =  '<div class="configRow">\n' +
                    '                            <input type="text" class="form-control text" value="故障">\n' +
                    '                            <input type="text" class="form-control num" value="100">\n' +
                    '     <div class="bgDiv">\n' +
                    '          <div class="uploadDiv">\n' +
                    '              <img class="uploadIcon" src="'+_ctx+'/static/pageDesign/icon/menu/upload_img6.png">\n' +
                    '               <input class="uploadBtn" type="file" title="添加图片" onchange="AddImg.uploadRelativeImg(this)">\n' +
                    '          </div>\n' +
                    '      </div>' +
                    '                        </div>';
                $("#design_img_relative .dataDiv").append(content);
                form.render();
                AddImg.stateShow();
                AddImg.initStateEvent();
            }

        });
    }


    /**
     * 拼接配置数据html
     */
    AddImg.getConfigHtml = function(result){
        var list = result.list;
        for(var i = 0; i < list.length; i++){
            var row = '<div class="configRow">\n' +
                '                        <input type="text" class="form-control text" value="'+list[i].F_DESC+'">\n' +
                '                        <input type="text" class="form-control num" value="'+list[i].F_VALUE+'">\n' +
                '     <div class="bgDiv">\n' +
                '          <div class="uploadDiv">\n' +
                '              <img class="uploadIcon" src="'+_ctx+'/static/pageDesign/icon/menu/upload_img6.png">\n' +
                '               <input class="uploadBtn" type="file" title="添加图片" onchange="AddImg.uploadRelativeImg(this)">\n' +
                '          </div>\n' +
                '      </div>' +
                '                    </div>';
            $("#design_img_relative .dataDiv").append(row);
            form.render();
        }
        AddImg.stateShow();
        AddImg.initStateEvent();
    }


    /**
     * 打开弹窗时--回显配置数据
     */
    AddImg.initConfigHtml = function(){
        var dataId = relativeJquery.attr("data-id");
        var configData = relativeJquery.attr("data-config");
        if(configData){
            var imgConfigType = "";
            //解析关联配置数据
            var configResult = [];
            var array = configData.split(";");
            //区分状态量和区间量
            if(configData.indexOf("min$") >= 0){
                //区间量
                imgConfigType = "2";
                for(var k = 0; k < array.length;k++){
                    var json = {};
                    //  item = "dataId$"+dataId+",min$"+min +",max$"+max+",text$"+text+",src$"+src+";";
                    var item = array[k].split(",");
                    if(item.length === 5){
                        var min = item[1].split("$")[1];
                        var max = item[2].split("$")[1];
                        var text = item[3].split("$")[1];
                        var src = item[4].split("$")[1];
                        json.min = min;
                        json.max = max;
                        json.text = text;
                        json.src = src;
                        configResult.push(json);
                    }
                }
                AddImg.initIntervalHtml(dataId,configResult);
            }else{
                imgConfigType = "1";
                for( k = 0; k < array.length;k++){
                    json = {};
                    item = array[k].split(",");
                    if(item.length === 4){
                        var state = item[1].split("$")[1];
                        text = item[2].split("$")[1];
                        src = item[3].split("$")[1];
                        json.state = state;
                        json.text = text;
                        json.src = src;
                        configResult.push(json);
                    }
                }
                for(var i = 0; i < configResult.length; i++){
                    var row = '<div class="configRow">\n' +
                        '                        <input type="text" class="form-control text" value="'+configResult[i].text+'">\n' +
                        '                        <input type="text" class="form-control num" value="'+configResult[i].state+'">\n' +
                        '     <div class="bgDiv">\n' +
                        '          <div class="uploadDiv">\n' +
                        '              <img class="uploadIcon relativeImg" src="'+configResult[i].src+'">\n' +
                        '               <input class="uploadBtn" type="file" title="添加图片" onchange="AddImg.uploadRelativeImg(this)">\n' +
                        '          </div>\n' +
                        '      </div>' +
                        '                    </div>';
                    $("#design_img_relative .dataDiv").append(row);
                    form.render();
                }
                AddImg.initStateEvent();
                AddImg.stateShow();
            }
        }
    }

    AddImg.initIntervalHtml = function(dataId,configResult){
        $.ajax({
            url     : _ctx + '/btnEventController/getDebugInfo',
            type    : "post",
            dataType: 'json',
            data : {
                sysname : dataId
            },
            success : function(result) {
                if(result.data){
                    var unit = result.data.F_ENGINEER_UNIT;
                    if(!unit){
                        unit = "";
                    }
                    for(var i = 0; i < configResult.length; i++){
                        var row = '<div class="configRow">\n' +
                            '                        <input type="text" class="form-control text" value="'+configResult[i].text+'">\n' +
                            '                        <div class="numDiv">\n' +
                            '                            <input type="text" class="min" value="'+configResult[i].min+'">\n' +
                            '                            <span>-</span>\n' +
                            '                            <input type="text" class="max" value="'+configResult[i].max+'">\n' +
                            '                            <span class="unit">'+unit+'</span>\n' +
                            '                        </div>\n' +
                            '     <div class="bgDiv">\n' +
                            '          <div class="uploadDiv">\n' +
                            '              <img class="uploadIcon relativeImg" src="'+configResult[i].src+'">\n' +
                            '               <input class="uploadBtn" type="file" title="添加图片" onchange="AddImg.uploadRelativeImg(this)">\n' +
                            '          </div>\n' +
                            '      </div>' +
                            '                    </div>';
                        $("#design_img_relative .intervalDataDiv").append(row);
                        form.render();
                    }
                    AddImg.initIntervalEvent();
                    AddImg.intervalShow();
                }
            }});
    }

    /**
     * 显示状态量
     */
    AddImg.stateShow = function(){
        $("#design_img_relative input[type=radio][name=imgConfigType][value='1']").prop("checked",true);
        $("#design_img_relative .configDiv .stateDiv").show();
        $("#design_img_relative .configDiv .intervalDiv").hide();
        $("#design_img_relative .configDiv .dataDiv").show();
        $("#design_img_relative .configDiv .intervalDataDiv").hide();
        form.render();
    }

    /**
     * 显示区间量
     */

    AddImg.intervalShow =function(){
        $("#design_img_relative input[type=radio][name=imgConfigType][value='2']").prop("checked",true);
        $("#design_img_relative .configDiv .stateDiv").hide();
        $("#design_img_relative .configDiv .intervalDiv").show();
        $("#design_img_relative .configDiv .dataDiv").hide();
        $("#design_img_relative .configDiv .intervalDataDiv").show();
        form.render();
    }
});
