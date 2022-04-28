

var showGraph = {};
layui.use('layer', function(){
    var layer = layui.layer;

    /**
     * 运行模式，加载svg图形
     */
    showGraph.displayGraph =function(graphList){
        for(var i = 0; i < graphList.length; i++){
            var graph = graphList[i];
            var graphType = graph.graph_type;
            var id = graph.id;
            if(graphType === "1"){
                var line_x1 = graph.line_x1;
                var line_y1 = graph.line_y1;
                var line_x2 = graph.line_x2;
                var line_y2 = graph.line_y2;
                showGraph.initLine(line_x1,line_y1,line_x2,line_y2,id);

            }else if(graphType === "2"){
                var cx = graph.circle_x;
                var cy = graph.circle_y;
                var r = graph.circle_r;
                showGraph.initCircle(cx,cy,r,id);

            }else if(graphType === "3"){
                var x = graph.rect_x;
                var y = graph.rect_y;
                var width = graph.rect_width;
                var height = graph.rect_height;
                showGraph.initRect(x,y,width,height,id);
            }
        }
    }


    /**
     * 加载svg图形
     * @param graphList
     */
    showGraph.initGraph = function(graphList){
        showGraph.displayGraph(graphList);
        svgShape.lineClick();
        svgShape.circleClick();
        svgShape.rectClick();
    }


    /**
     * 回显线段
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     */
    showGraph.initLine = function(x1,y1,x2,y2,id){
        var svg = Snap("#design_svg");
        var lineId = id;
        var object = svg.line(x1, y1, x2, y2).attr({  fill: '#1e609d',
            stroke:"#1D7DB1", stroke_width:"1",cursor:"move",id:lineId,"class":"design_shape"});
    }

    /**
     * 回显圆形
     * @param cx 圆心坐标x
     * @param cy 圆心坐标y
     * @param r 半径
     */
    showGraph.initCircle = function(cx,cy,r,id){
        var svg = Snap("#design_svg");
        var circleId = id;
        var object = svg.circle(cx, cy, r).attr({ fill: 'none',
            stroke:"#1D7DB1", stroke_width:"1",id:circleId,"class":"design_shape",
            x1:cx-r,y1:cy-r,x2:cx+r,y2:cy+r});
    }

    /**
     * 回显矩形
     * @param x 左上角坐标x
     * @param y 左上角坐标y
     * @param width 宽度
     * @param height 高度
     */
    showGraph.initRect = function(x,y,width,height,id){
        var svg = Snap("#design_svg");
        var rectId = id;
        var object = svg.rect(x, y, width, height).attr({  fill: 'none',
            stroke:"#1D7DB1", stroke_width:"1",id:rectId,"class":"design_shape"});
    }


});