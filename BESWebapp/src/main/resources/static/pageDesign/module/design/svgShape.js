/**
 * 画线  Snap.svg
 * @type {{x1: string, y1: string, x2: string, y2: string, kind: string, isDraw: boolean}}
 */

var svgShape = {
    //起点坐标
    x1:"",
    y1:"",
    //终点坐标
    x2:"",
    y2:"",
    //绘制类型
    kind:"",
    //是否是可绘制状态
    isDraw:false
};
layui.use('layer', function(){
    var layer = layui.layer;

//鼠标拖动控件的坐标记录，起点坐标，终点坐标
    var move_x1,move_y1,move_x2,move_y2;
//被拖动的图形的id
    var moveShapeId;
//尺寸变化的图形id
    var resizeShapeId;
//尺寸变化的图形dom
    var resizeRect;
//鼠标每拖动多长距离触发一次mousemove事件
    var moveInterval = 20;
//mousemove事件记录每次挪动的起始点
    var m_x1,m_y1;

//变化尺寸的坐标记录，起点坐标，终点坐标
    var resize_x1,resize_y1,resize_x2,resize_y2;
    $(function(){
        svgShape.initEvent();
        contextMenu();
    });

//右键菜单
    function contextMenu(){
        $.contextMenu({
            selector: '#design_svg .design_shape', //右键选择器
            callback: function(key, options) {//点击回调处理
                var $dom = options.$trigger;
                if(key === "delete"){
                    //删除
                    layer.confirm('确定删除该图形吗?', {title:'删除提示'}, function(index){
                        $dom.remove();
                        $("#design_shape_container").hide();
                        layer.close(index);
                    });

                }
            },
            items: {//菜单列表配置
                "delete":{name:"删除",icon:'fa-trash-o'}
            }
        });
    }

    /**
     * 画线
     */
    svgShape.line_plugin = function(){
        var svg = Snap("#design_svg");
        var lineId = new Date().getTime();
        var object = svg.line(svgShape.x1, svgShape.y1, svgShape.x2, svgShape.y2).attr({  fill: '#1e609d',
            stroke:"#1D7DB1", stroke_width:"1",id:lineId,"class":"design_shape"});
        var container = $("#design_shape_container");
        svgShape.moveContainer(svgShape.x1,svgShape.y1,svgShape.x2,svgShape.y2,lineId);
        container.show();
        svgShape.clearData();
        svgShape.lineClick();
    }

    /**
     * 画圆
     */
    svgShape.circle_plugin = function(){
        var svg = Snap("#design_svg");
        var circleId = new Date().getTime();
        var width = Math.abs(svgShape.x2 - svgShape.x1);
        var height = Math.abs(svgShape.y2 - svgShape.y1);
        var cx = (svgShape.x1 + svgShape.x2) / 2;
        var cy = (svgShape.y1 + svgShape.y2) / 2;
        var r = Math.sqrt(width*width + height*height) / 2;
        //
        var x1 = cx - r;
        var y1 = cy - r;
        var x2 = cx + r;
        var y2 = cy + r;

        var object = svg.circle(cx, cy, r).attr({ fill: 'none',
            stroke:"#1D7DB1", stroke_width:"1",id:circleId,"class":"design_shape",
            x1:x1,y1:y1,x2:x2,y2:y2});
        var container = $("#design_shape_container");

        svgShape.moveContainer(x1,y1,x2,y2,circleId);
        container.show();
        svgShape.clearData();
        svgShape.circleClick();
    }

    /**
     * 画矩形
     */
    svgShape.rect_plugin = function(){
        var svg = Snap("#design_svg");
        var rectId = new Date().getTime();
        var x = Math.min(svgShape.x1,svgShape.x2);
        var y = Math.min(svgShape.y1,svgShape.y2);
        var w = Math.abs(svgShape.x2 - svgShape.x1);
        var h = Math.abs(svgShape.y2 - svgShape.y1);
        var object = svg.rect(x, y, w, h).attr({  fill: 'none',
            stroke:"#1D7DB1", stroke_width:"1",id:rectId,"class":"design_shape"});
        var container = $("#design_shape_container");
        svgShape.moveContainer(svgShape.x1,svgShape.y1,svgShape.x2,svgShape.y2,rectId);
        container.show();
        svgShape.clearData();
        svgShape.rectClick();

    }

    /**
     * 是否拖动
     * 如果鼠标在拖动控件之内，可以拖动
     * @param x 鼠标当前位置x
     * @param y 鼠标当前位置y
     */
    svgShape.dragJudge = function(x,y){
        var container = $("#design_shape_container");
        var rect_w = container.find("rect#design_shape_rect_w");
        var left = Number(rect_w.attr("x"));
        var rect_n = container.find("rect#design_shape_rect_n");
        var top = Number(rect_n.attr("y"));
        var rect_s = container.find("rect#design_shape_rect_s");
        var bottom = Number(rect_s.attr("y"));
        var rect_e = container.find("rect#design_shape_rect_e");
        var right = Number(rect_e.attr("x"));
        var min_x = Math.min(left,right);
        var max_x = Math.max(left,right);
        var min_y = Math.min(top,bottom);
        var max_y = Math.max(top,bottom);
        var d = 10;
        if(x > min_x + d && x < max_x - d && y > min_y + d && y < max_y - d){
            return true;
        }
        return false;
    }

    /**
     * 拖动控件挪动位置
     * 参数是拖动控件控制的主体的坐标
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param lineId 主体id
     */
    svgShape.moveContainer = function(x1,y1,x2,y2,lineId){
        var container = $("#design_shape_container");
        var path_left = container.find("path#design_shape_path_left");
        // var p1 = "M"+svgShape.x1+" "+svgShape.y1+"L"+svgShape.x1+" "+svgShape.y2;
        var p1 = "M"+x1+" "+y1+"L"+x1+" "+y2;
        path_left.attr("d",p1);
        //$("path#design_shape_path_left")[0].attributes.d.value

        var path_bottom = container.find("path#design_shape_path_bottom");
        // var p2 = "M"+svgShape.x1+" "+svgShape.y2+"L"+svgShape.x2+" "+svgShape.y2;
        var p2 = "M"+x1+" "+y2+"L"+x2+" "+y2;
        path_bottom.attr("d",p2);

        var path_top = container.find("path#design_shape_path_top");
        var p3 = "M"+x1+" "+y1+"L"+x2+" "+y1;
        //var p3 = "M"+svgShape.x1+" "+svgShape.y1+"L"+svgShape.x2+" "+svgShape.y1;
        path_top.attr("d",p3);
        var path_right = container.find("path#design_shape_path_right");
        var p4 = "M"+x2+" "+y1+"L"+x2+" "+y2;
        // var p4 = "M"+svgShape.x2+" "+svgShape.y1+"L"+svgShape.x2+" "+svgShape.y2;
        path_right.attr("d",p4);
        var rect_w = container.find("rect#design_shape_rect_w");
        rect_w.attr("x",x1-4).attr("y",(y1+y2)/2-4).attr({"shape-id":lineId});
        var rect_n = container.find("rect#design_shape_rect_n");
        rect_n.attr("x",(x1+x2)/2-4).attr("y",y1-4).attr({"shape-id":lineId});
        var rect_s = container.find("rect#design_shape_rect_s");
        rect_s.attr("x",(x1+x2)/2-4).attr("y",y2-4).attr({"shape-id":lineId});
        var rect_e = container.find("rect#design_shape_rect_e");
        rect_e.attr("x",x2-4).attr("y",(y1+y2)/2-4).attr({"shape-id":lineId});

    }




    /**
     * 线段的点击事件
     */
    svgShape.lineClick = function(){
        //点击线段切换拖动控件是否显示
        $("#design_svg line").unbind("click").click(function(){
            // $("#design_shape_container").toggle();
            var x1 = Number($(this).attr("x1"));
            var x2 = Number($(this).attr("x2"));
            var y1 = Number($(this).attr("y1"));
            var y2 = Number($(this).attr("y2"));
            var shapeId = $(this).attr("id");
            svgShape.moveContainer(x1,y1,x2,y2,shapeId);
            $("#design_shape_container").show();
        });
    }

    /**
     * 圆形的点击事件
     */
    svgShape.circleClick = function(){
        //点击圆形切换拖动控件是否显示
        $("#design_svg circle").unbind("click").click(function(){
            // $("#design_shape_container").toggle();
            var cx = Number($(this).attr("cx"));
            var cy = Number($(this).attr("cy"));
            var r = Number($(this).attr("r"));
            // var y2 = Number($(this).attr("y2"));
            var shapeId = $(this).attr("id");
            svgShape.moveContainer(cx - r,cy - r, cx + r,cy + r,shapeId);
            $("#design_shape_container").show();
        });
    }

    /**
     * 矩形边框点击事件
     */
    svgShape.rectClick = function(){
        $("#design_svg rect.design_shape").unbind("click").click(function(){
            // $("#design_shape_container").toggle();
            var x = Number($(this).attr("x"));
            var y = Number($(this).attr("y"));
            var w = Number($(this).attr("width"));
            var h = Number($(this).attr("height"));
            var shapeId = $(this).attr("id");
            svgShape.moveContainer(x,y, x+w,y+h,shapeId);
            $("#design_shape_container").show();
        });
    }
    /**
     * 监听事件
     */
    svgShape.initEvent = function(){

        /**
         * 画线按钮点击事件
         */
        $("#lineDraw").click(function(){
            svgShape.isDraw = true;
            svgShape.kind = 1;
        });

        /**
         * 画圆按钮点击事件
         */
        $("#circleDraw").click(function(){
            svgShape.isDraw = true;
            svgShape.kind = 2;
        });
        
        $("#rectDraw").click(function(){
            svgShape.isDraw = true;
            svgShape.kind = 3;
        });

        /**
         * 画线按钮控制
         */
        $('#designDiv').mousedown(function (e) {
            if(svgShape.isDraw){
                svgShape.x1 = e.offsetX; // 鼠标落下时的X
                svgShape.y1 = e.offsetY; // 鼠标落下时的Y
            }

        }).mouseup(function (e) {
            if(svgShape.isDraw){
                svgShape.x2 = e.offsetX;
                svgShape.y2 = e.offsetY;
                if(svgShape.kind === 1){
                    svgShape.line_plugin(e);
                }else if(svgShape.kind === 2){
                    svgShape.circle_plugin();
                }else if(svgShape.kind === 3){
                    svgShape.rect_plugin();
                }
               
            }

        }).mousemove(function (e) {
        });

        /**
         * svg标签的鼠标监听事件
         */
        $("#design_svg").unbind("mousedown").unbind("mouseup").mousedown(function(e){
            if(e.target.tagName === "line" || e.target.tagName === "circle" || (e.target.tagName === "rect" && e.target.classList.contains("design_shape"))){
                move_x1 = e.offsetX;
                move_y1 = e.offsetY;
                moveShapeId = e.target.id;

                m_x1 = e.offsetX;
                m_y1 = e.offsetY;
                return;
            }

            var container = $("#design_shape_container");
            if(!(container.css("display") === "none")){
                //拖动控件显示，获取拖动控件内部坐标范围，判断鼠标位置是否在拖动控件之内
                var isDrag = svgShape.dragJudge(e.offsetX,e.offsetY);

                if(isDrag){
                    move_x1 = e.offsetX;
                    move_y1 = e.offsetY;
                    var rect_w = container.find("rect#design_shape_rect_w");
                    moveShapeId = rect_w.attr("shape-id");
                    m_x1 = e.offsetX;
                    m_y1 = e.offsetY;
                    return;
                }
            }

            if(e.target.tagName === "rect" && !e.target.classList.contains("design_shape")){
                var id = e.target.attributes["shape-id"].value;
                    resizeRect = e.target;
                    resizeShapeId = id;
                    resize_x1 = e.offsetX;
                    resize_y1 = e.offsetY;
                    m_x1 = e.offsetX;
                    m_y1 = e.offsetY;
            }

        }).mouseup(function (e) {
            svgShape.cleartMoveData();
            svgShape.clearResizeData();
            m_x1 = "";
            m_y1 = "";
        }).mousemove(function(e) {

                if(m_x1 && m_y1){
                    if(move_x1 && move_y1){
                        var $moveShape = $("#"+moveShapeId);
                        var tagName = $moveShape[0].tagName;
                        var classList = $moveShape[0].classList;
                    }
                    if(resize_x1 && resize_y1){
                        var $shape = $("#"+resizeShapeId);
                        tagName = $shape[0].tagName;
                        classList = $shape[0].classList;
                    }
                    if( tagName === "line"){
                        svgShape.lineMoveMethod(e);
                    }else if(tagName === "circle"){
                        svgShape.circleMoveMethod(e);
                    }else if(tagName === "rect" && classList.contains("design_shape")){
                        svgShape.rectMoveMethod(e);
                    }
                    m_x1 = e.offsetX;
                    m_y1 = e.offsetY;
                }
                

            }
        );
    }

    /**
     * 线段：鼠标拖动mousemove事件
     * 让图形变化有连贯性
     */
    svgShape.lineMoveMethod = function(e){
        if(move_x1 && move_y1){
            var x2 = e.offsetX;
            var y2 = e.offsetY;
            var d_x = x2 - m_x1;
            var d_y = y2 - m_y1;

            var $shape = $("#"+moveShapeId);
            var old_x1 = $shape.attr("x1");
            var old_x2 = $shape.attr("x2");
            var old_y1 = $shape.attr("y1");
            var old_y2 = $shape.attr("y2");
            var new_x1 = Number(old_x1)+d_x;
            var new_y1 = Number(old_y1)+d_y;
            var new_x2 = Number(old_x2)+d_x;
            var new_y2 = Number(old_y2)+d_y;
            $shape.attr("x1",new_x1);
            $shape.attr("y1",new_y1);
            $shape.attr("x2",new_x2);
            $shape.attr("y2",new_y2);
            svgShape.moveContainer(new_x1,new_y1,new_x2,new_y2,moveShapeId);
            // svgShape.cleartMoveData();
            return;
        }
        /**
         * 挪动 rect_w rect_n  起点动，挪动 rect_s rect_e 终点动
         */
        if(resize_x1 && resize_y1) {
            x2 = e.offsetX;
            y2 = e.offsetY;
            var r_x = x2 - m_x1;
            var r_y = y2 - m_y1;
            var className = resizeRect.attributes.class.value;
            var $resizeShape = $("#"+resizeShapeId);
            var origin_x1 = Number($resizeShape.attr("x1"));
            var origin_y1 = Number($resizeShape.attr("y1"));
            var origin_x2 = Number($resizeShape.attr("x2"));
            var origin_y2 = Number($resizeShape.attr("y2"));
            if (className === "rect_w" || className === "rect_n") {
                //挪动起点
                var target_x1 = origin_x1 + r_x;
                var target_y1 = origin_y1 + r_y;
                $resizeShape.attr("x1", target_x1);
                $resizeShape.attr("y1", target_y1);
                svgShape.moveContainer(target_x1, target_y1, origin_x2, origin_y2, resizeShapeId);
                // svgShape.clearResizeData();
            }else if(className === "rect_s" || className === "rect_e"){
                //挪动终点

                var target_x2 = origin_x2+r_x;
                var target_y2 = origin_y2+r_y;
                $resizeShape.attr("x2",target_x2);
                $resizeShape.attr("y2",target_y2);
                svgShape.moveContainer(origin_x1, origin_y1, target_x2, target_y2, resizeShapeId);
                // svgShape.clearResizeData();
            }
        }
    }

    /**
     * 圆形鼠标拖动事件
     * @param e
     */
    svgShape.circleMoveMethod = function(e){
        if(move_x1 && move_y1){

            var d_x = e.offsetX - m_x1;
            var d_y = e.offsetY - m_y1;

            var $shape = $("#"+moveShapeId);
            var old_cx = Number($shape.attr("cx"));
            var old_cy = Number($shape.attr("cy"));
            var r = Number($shape.attr("r"));
            var new_cx = old_cx + d_x;
            var new_cy = old_cy + d_y;
            $shape.attr("cx",new_cx);
            $shape.attr("cy",new_cy);
            var x1 = new_cx - r;
            var y1 = new_cy - r;
            var x2 = new_cx + r;
            var y2 = new_cy + r;
            $shape.attr("x1",x1);
            $shape.attr("y1",y1);
            $shape.attr("x2",x2);
            $shape.attr("y2",y2);
            svgShape.moveContainer(x1,y1,x2,y2,moveShapeId);
            return;
        }
        /**
         * 挪动 rect_w rect_n  起点动，挪动 rect_s rect_e 终点动
         */
        if(resize_x1 && resize_y1) {
            svgShape.resizeShape(e);
        }
    }


    /**
     * 圆形鼠标拖动变化尺寸
     * @param e
     */
    svgShape.resizeShape = function(e){

        var d_x = e.offsetX - m_x1;
        var d_y = e.offsetY - m_y1;
        var className = resizeRect.attributes.class.value;
        var $shape = $("#"+resizeShapeId);
        var x1 = Number($shape.attr("x1"));
        var x2 = Number($shape.attr("x2"));
        var y1 = Number($shape.attr("y1"));
        var y2 = Number($shape.attr("y2"));
        var cx = Number($shape.attr("cx"));
        var cy = Number($shape.attr("cy"));
        var r = Number($shape.attr("r"));
        if(className === "rect_e"){
            // 向东
            cx = cx + (d_x / 2);
            r = Math.abs(r + (d_x / 2));
        }else if(className === "rect_n"){
            //向北
            cy = cy + (d_y / 2);
            r = Math.abs(r - (d_y / 2));
        }else if(className === "rect_w"){
            //向西
            cx = cx + (d_x / 2);
            r = Math.abs(r - (d_x / 2));
        }else if(className === "rect_s"){
            //向南
            cy = cy + (d_y / 2);
            r = Math.abs(r + (d_y / 2));
        }
        $shape.attr("cx",cx);
        $shape.attr("cy",cy);
        $shape.attr("r",r);
        svgShape.moveContainer(cx - r,cy - r,cx + r,cy + r,resizeShapeId);
    }


    /**
     * 矩形鼠标拖动事件mouse move
     */
    svgShape.rectMoveMethod = function(e){
        if(move_x1 && move_y1){
            var d_x = e.offsetX - m_x1;
            var d_y = e.offsetY - m_y1;

            var $shape = $("#"+moveShapeId);
            var old_x = Number($shape.attr("x"));
            var old_y = Number($shape.attr("y"));
            var w = Number($shape.attr("width"));
            var h = Number($shape.attr("height"));
            var new_x = old_x+d_x;
            var new_y = old_y+d_y;
            var new_x2 = new_x + w;
            var new_y2 = new_y+h;
            $shape.attr("x",new_x);
            $shape.attr("y",new_y);
            svgShape.moveContainer(new_x,new_y,new_x2,new_y2,moveShapeId);
            return;
        }
        /**
         * 挪动 rect_w rect_n  起点动，挪动 rect_s rect_e 终点动
         */
        if(resize_x1 && resize_y1) {
            var r_x = e.offsetX - m_x1;
            var r_y = e.offsetY - m_y1;
            var className = resizeRect.attributes.class.value;
            var $resizeShape = $("#"+resizeShapeId);
            var x = Number($resizeShape.attr("x"));
            var y = Number($resizeShape.attr("y"));
            var width = Number($resizeShape.attr("width"));
            var height = Number($resizeShape.attr("height"));
            var x2 = x + width,y2 = y + height;
            if (className === "rect_w") {
                x = x + r_x;
                width = Math.abs(width - r_x);

            }else if(className === "rect_n"){
                y = y + r_y;
                height = Math.abs(height - r_y);
            }else if(className === "rect_s"){
                height = Math.abs(height + r_y);
                y2 = y2 + r_y;
            }else if(className === "rect_e"){
                width = Math.abs(width + r_x);
                x2 = x2 + r_x;
            }
            $resizeShape.attr("x", x);
            $resizeShape.attr("y", y);
            $resizeShape.attr("width", width);
            $resizeShape.attr("height", height);
            svgShape.moveContainer(x,y,x2,y2,resizeShapeId);
        }


    }

    /**
     * 清空尺寸变化的数据记录
     */
    svgShape.clearResizeData = function(){
        resizeRect = "";
        resizeShapeId = "";
        resize_y1 = "";
        resize_y1 = "";
        resize_x2 = "";
        resize_y2 = "";
    }

    /**
     * 清空拖动的数据记录
     */
    svgShape.cleartMoveData = function(){
        move_x1 = "";
        move_y1 = "";
        move_x2 = "";
        move_y2 = "";
        moveShapeId = "";
    }

    svgShape.clearData = function(){
        svgShape.isDraw = false;
        svgShape.kind = "";
        svgShape.x1 = "";
        svgShape.y1 = "";
        svgShape.x2 = "";
        svgShape.y2 = "";

    }

});



//-----------------------------------------以下代码废弃（效果不理想代码）------------------------------------------

/**
 * 鼠标抬起的事件（mouseup）
 */
svgShape.shapeChangeMethod = function(e){
    if(move_x1 && move_y1){
        move_x2 = e.offsetX;
        move_y2 = e.offsetY;
        var d_x = move_x2 - move_x1;
        var d_y = move_y2 - move_y1;

        var $shape = $("#"+moveShapeId);
        var old_x1 = $shape.attr("x1");
        var old_x2 = $shape.attr("x2");
        var old_y1 = $shape.attr("y1");
        var old_y2 = $shape.attr("y2");
        var new_x1 = Number(old_x1)+d_x;
        var new_y1 = Number(old_y1)+d_y;
        var new_x2 = Number(old_x2)+d_x;
        var new_y2 = Number(old_y2)+d_y;
        $shape.attr("x1",new_x1);
        $shape.attr("y1",new_y1);
        $shape.attr("x2",new_x2);
        $shape.attr("y2",new_y2);
        svgShape.moveContainer(new_x1,new_y1,new_x2,new_y2,moveShapeId);
        // svgShape.cleartMoveData();
        return;
    }
    /**
     * 挪动 rect_w rect_n  起点动，挪动 rect_s rect_e 终点动
     */
    if(resize_x1 && resize_y1) {
        resize_x2 = e.offsetX;
        resize_y2 = e.offsetY;
        var r_x = resize_x2 - resize_x1;
        var r_y = resize_y2 - resize_y1;
        var className = resizeRect.attributes.class.value;
        var $resizeShape = $("#"+resizeShapeId);
        var origin_x1 = Number($resizeShape.attr("x1"));
        var origin_y1 = Number($resizeShape.attr("y1"));
        var origin_x2 = Number($resizeShape.attr("x2"));
        var origin_y2 = Number($resizeShape.attr("y2"));
        if (className === "rect_w" || className === "rect_n") {
            //挪动起点
            var target_x1 = origin_x1 + r_x;
            var target_y1 = origin_y1 + r_y;
            $resizeShape.attr("x1", target_x1);
            $resizeShape.attr("y1", target_y1);
            svgShape.moveContainer(target_x1, target_y1, origin_x2, origin_y2, resizeShapeId);
            // svgShape.clearResizeData();
        }else if(className === "rect_s" || className === "rect_e"){
            //挪动终点

            var target_x2 = origin_x2+r_x;
            var target_y2 = origin_y2+r_y;
            $resizeShape.attr("x2",target_x2);
            $resizeShape.attr("y2",target_y2);
            svgShape.moveContainer(origin_x1, origin_y1, target_x2, target_y2, resizeShapeId);
            // svgShape.clearResizeData();
        }
    }
}
/**
 * 模拟拖动插件
 */
svgShape.line_draw =function(){
    var svg = Snap("#design_svg");
    var lineId = new Date().getTime();
    var object = svg.line(svgShape.x1, svgShape.y1, svgShape.x2, svgShape.y2).attr({  fill: '#1e609d',
        stroke:"#1D7DB1", stroke_width:"1",cursor:"move",id:lineId});
    // path("M10 10L90 90")

    //左
    var p1 = "M"+svgShape.x1+" "+svgShape.y1+"L"+svgShape.x1+" "+svgShape.y2;
    var p_attr = {stroke:"#1e609d", "stroke-dasharray":"4,3",opacity:0.5,cursor:"move"};
    svg.path(p1).attr(p_attr);
    // svg.line(svgShape.x1,svgShape.y1,svgShape.x1,svgShape.y2).attr({ stroke:"#1D7DB1", stroke_width:"1","stroke-dasharray":"5,5"});

    var rect_attr = {fill:"#4F80FF","pointer-events":"all","shape-id":lineId};
    // var c = paper.rect(10, 10, 50, 50);
    svg.rect(svgShape.x1-4,(svgShape.y1+svgShape.y2)/2-4,8,8).attr(rect_attr).attr({cursor:"w-resize","class":"rect_w"});
    //下
    var p2 = "M"+svgShape.x1+" "+svgShape.y2+"L"+svgShape.x2+" "+svgShape.y2;
    svg.path(p2).attr(p_attr);
    svg.rect((svgShape.x1+svgShape.x2)/2-4,svgShape.y2-4,8,8).attr(rect_attr).attr({cursor:"s-resize","class":"rect_s"});
    //上-北
    var p3 = "M"+svgShape.x1+" "+svgShape.y1+"L"+svgShape.x2+" "+svgShape.y1;
    svg.path(p3).attr(p_attr).attr({"cursor":"n-resize","pointer-events":"all"});
    svg.rect((svgShape.x1+svgShape.x2)/2-4,svgShape.y1-4,8,8).attr(rect_attr).attr({cursor:"n-resize","class":"rect_n"});
    //右-东
    var p4 = "M"+svgShape.x2+" "+svgShape.y1+"L"+svgShape.x2+" "+svgShape.y2;
    svg.path(p4).attr(p_attr).attr({"cursor":"e-resize"});

    svg.rect(svgShape.x2-4,(svgShape.y1+svgShape.y2)/2-4,8,8).attr(rect_attr).attr({cursor:"e-resize","class":"rect_e"});
    svgShape.clearData();

    $("#design_svg line").unbind("click").click(function(){
        $("#design_svg rect").toggle();
        $("#design_svg path").toggle();
    });




}


svgShape.snapLine = function(){
    var svg = Snap("#design_svg");
    var object = svg.line(svgShape.x1, svgShape.y1, svgShape.x2, svgShape.y2).attr({  fill: '#1e609d',
        stroke:"#1D7DB1", stroke_width:"1"});
    var ft = svg.freeTransform(object, { snap: { rotate: 1 }, size: 8, draw: 'bbox'  },null,svgShape);

    // ft.attrs.rotate = 45;
    ft.attrs.scale.x = 1;
    ft.apply();

    object.data('ftStatus', 1);

    object.dblclick( function() {

        if( object.data('ftStatus' ) ) {
            ft.hideHandles();
            object.data('ftStatus',0);
        } else {
            ft.showHandles();
            object.data('ftStatus',1);
        }

    });
    svgShape.clearData();

}

// var $line = $("#"+svgId).children();
// var paper = Snap('#design_svg');
// var dom = document.getElementById(svgId);
// var svg = new Paper(dom);
svgShape.line_transform = function(){
    var svgId = new Date().getTime();
    var line ='<svg id="'+svgId+'" width="100%" height="100%"><line x1="'+svgShape.x1+'" y1="'+svgShape.y1+'" x2="'+svgShape.x2+'" y2="'+svgShape.y2+'" style="stroke:#1D7DB1;"></line></svg>';
    $("#design_svg").append(line);
    // var svg = Snap(svgId);
    //
    // var object = svg.line();
    // object.node.onclick = function () {
    //     object.attr("fill", "red");
    //     object.attr("stroke","red");
    // };
    // var ft = svg.freeTransform(object, { snap: { rotate: 1 }, size: 8, draw: 'bbox'  },null,svgShape);
    //
    // // ft.attrs.rotate = 45;
    // ft.attrs.scale.x = 1;
    // ft.apply();
    //
    // object.data('ftStatus', 1);
    //
    // object.dblclick( function() {
    //
    //     if( object.data('ftStatus' ) ) {
    //         ft.hideHandles();
    //         object.data('ftStatus',0);
    //     } else {
    //         ft.showHandles();
    //         object.data('ftStatus',1);
    //     }
    //
    // });
svgShape.clearData();
    // var ft = svg.freeTransform(object, { snap: { rotate: 1 }, size: 1, draw: 'bbox'  });
    //
    // // ft.attrs.rotate = 45;
    // ft.attrs.scale.x = 1;
    // ft.apply();
    //
    // object.data('ftStatus', 1);
    // object.dblclick( function() {
    //
    //     if( object.data('ftStatus' ) ) {
    //         ft.hideHandles();
    //         object.data('ftStatus',0);
    //     } else {
    //         ft.showHandles();
    //         object.data('ftStatus',1);
    //     }
    //
    // });
}




/**
 * 画线
 * @param e
 */
svgShape.line3 = function(e){
    var width= Math.abs(svgShape.x2 - svgShape.x1);
    var height = Math.abs(svgShape.y2 - svgShape.y1);
    var x = Math.min(svgShape.x1,svgShape.x2);
    var y = Math.min(svgShape.y1,svgShape.y2);
    // var line ='<line x1="'+svgShape.x1+'" y1="'+svgShape.y1+'" x2="'+svgShape.x2+'" y2="'+svgShape.y2+'" style="stroke:#1D7DB1;"></line>';
    var svgId = new Date().getTime();
    var line ='<svg id="'+svgId+'" width="100%" height="100%"><line x1="'+svgShape.x1+'" y1="'+svgShape.y1+'" x2="'+svgShape.x2+'" y2="'+svgShape.y2+'" style="stroke:#1D7DB1;"></line></svg>';
    // var svg ='<svg id="'+svgId+'" ></svg>';

    //<g><rect x="'+x+'" y="'+y+'" width="'+width+'" height="'+height+'" stroke-width="1" stroke="#000000" fill="#ffffff"></rect></g>

    // $("#design_svg").append(line);
    // var design_svg = document.getElementById("design_svg");
    // design_svg.append(line);
    // $("#design_area_demo").append(line);

    // $("#design_area_demo svg line").unbind("click").click(function(){
    //     $(this).css("stroke","red");
    // });

    var paper = Snap('design_svg');


    var object = paper
            .line(svgShape.x1, svgShape.x2, svgShape.y1, svgShape.y2)
    //     // .line(50, 50, 100, 50)
            .attr({
                cursor: 'move',
                fill: '#1e609d',
                stroke:"#1D7DB1",
                // stroke_width:"1"
                // transform: 't200,200'
            });
    //
    // var ft = paper.freeTransform(object, { snap: { rotate: 1 }, size: 1, draw: 'bbox'  });
    //
    // // ft.attrs.rotate = 45;
    // ft.attrs.scale.x = 1;
    // ft.apply();
    //
    // object.data('ftStatus', 1);
    //
    // object.dblclick( function() {
    //
    //     if( object.data('ftStatus' ) ) {
    //         ft.hideHandles();
    //         object.data('ftStatus',0);
    //     } else {
    //         ft.showHandles();
    //         object.data('ftStatus',1);
    //     }
    //
    // });
  svgShape.clearData();
    // $("#design_area_demo .design_line_div").draggable().resizable();
    // new Rect({width: 100, height: 100}).addTo(svgShape.draw)
    // var line = new Polyline();
    // svgShape.draw
    //     .line(svgShape.x1,svgShape.y1, svgShape.x2, svgShape.y2) // 起点xy，终点xy
    //     .stroke({ width: 5, linecap: "round", color: "blue" }); // 线条样式
}

// // 画线
// let line = this.draw
//     .line(10, 10, 10, 150) // 起点xy，终点xy
//     .stroke({ width: 5, linecap: "round", color: "blue" }); // 线条样式
//
// // 画矩形
// let rect = this.draw
//     .rect(100, 100) // 宽高
//     .radius(10) // 圆角
//     .fill("red") //填充
//     .move(20, 20); // 位移
//
// // 画圆
// let circle = this.draw
//     .circle(100) // 圆直径
//     .fill("green")
//     .move(130, 20);


// var draw = SVG('#design_area_demo')
// var rect = draw.rect(100, 100).attr({ fill: '#f06' })

// //判断浏览器是否支持绘制SVG
// if (SVG.supported) {
//     svgShape.draw = SVG("#design_area_demo").size('100%', '100%');
//     svgShape.draw .rect(100, 100).attr({ fill: '#f06' })
// }else{
//     alert("浏览器不支持SVG！")
// }

// svgShape.draw = SVG("#design_area_demo");
//  svgShape.draw = SVG().addTo('#design_area_demo');
// var rect = draw.rect(100, 100).attr({ fill: '#f06' })