/**
 * 添加表格
 */
var AddTable = {
    selectedTable:""
};

layui.use(['layer','form'], function(){
    //新增、修改弹窗,关联弹窗
    var addIndex,editIndex;
    //修改的标签Jquery
    var editJquery;
    //合并单元格所用数据
    var r_min,r_max,c_min,c_max;

    $(function(){
        AddTable.initEvent();
        contextMenu();
    });

    //右键菜单
    function contextMenu(){
        $.contextMenu({
            selector: '.design_table_div ', //右键选择器
            callback: function(key, options) {//点击回调处理
                var $dom = options.$trigger;
                if(key === "update"){
                    //修改
                    editJquery = $dom.children("table");
                    var name = editJquery.attr("data-name");
                    // var rowsNum = editJquery[0].rows.length;
                    // var columnNum = editJquery[0].rows[0].cells.length;
                    // var columnWidth = editJquery[0].rows[0].cells[0].width;
                    // columnWidth = columnWidth.replace("px","");
                    $("#design_edit_table_name").val(name);
                    // $("#design_edit_table_rows_num").val(rowsNum);
                    // $("#design_edit_table_columns_num").val(columnNum);
                    // $("#design_edit_table_columns_width").val(columnWidth);
                    AddTable.openEditWin();
                }
                if(key === "delete"){
                    //删除
                    layer.confirm('确定删除该表格吗?', {title:'删除提示'}, function(index){
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
                    $dom.draggable({ disabled: false })
                        .resizable({ disabled: false,handles:"n,e" });
                }
                // var m = "clicked: " + key;
                // window.console && console.log(m) || alert(m);
            },
            items: {//菜单列表配置
                "update": {name: "修改", icon: "fa-edit",disabled: false},
                // "relative": {name: "关联点", icon: "fa-paperclip",disabled:false},
                "delete":{name:"删除",icon:'fa-trash-o'}
                ,"fix":{name:"固定",icon:'fa-map-marker'}
                ,"drag":{name:"拖动",icon:'fa-arrows'}
            }
        });


        $.contextMenu({
            //添加列
            selector:".design_table.table-bordered > tbody > tr > td",
            // selector: '.design_table.table-bordered > tbody > tr:nth-child(1) td,.design_table.table-bordered > tbody > tr > td:nth-child(1)', //右键选择器
            callback: function(key, options) {//点击回调处理
                var $dom = options.$trigger;

                if(key === "add_column_left"){
                    //在左侧插入列
                    var cellIndex = $dom[0].cellIndex;
                    var table = $dom[0].parentNode.parentNode.parentNode;
                    var rowsNum = table.rows.length;
                    var td = '<td class="design_table_td"></td>';
                    for(var i = 0; i < rowsNum; i++){
                        $(table.rows[i].cells[cellIndex]).before(td);
                    }
                    AddTable.selectMoreCells();
                }
                if(key === "add_column_right"){
                    //在右侧插入列
                    cellIndex = $dom[0].cellIndex;
                    table = $dom[0].parentNode.parentNode.parentNode;
                    rowsNum = table.rows.length;
                    td = '<td class="design_table_td"></td>';
                    for(i = 0; i < rowsNum; i++){
                        $(table.rows[i].cells[cellIndex]).after(td);
                    }
                    AddTable.selectMoreCells();
                }
                if(key === "add_row_top"){
                    //在上方插入行
                    var rowIndex = $dom[0].parentNode.rowIndex;
                    table = $dom[0].parentNode.parentNode.parentNode;
                    // var columnNum = table.rows[0].cells.length;
                    var parentRow = $dom[0].parentNode;
                    var cells = parentRow.cells;
                    var columnNum = cells.length;
                    var plus = 0;
                    for(var k = 0; k < columnNum; k++){
                        var c = $(cells[k]).attr("colspan");
                        if(c && c > 1){
                            plus += Number(c) - 1;
                        }
                    }
                    var row = "<tr>";
                    td = '<td class="design_table_td"></td>';
                    for(i = 0; i < columnNum+plus; i++){
                        row += td;
                    }
                    row+="</tr>";
                    $(table.rows[rowIndex]).before(row);
                    AddTable.selectMoreCells();
                }
                if(key === "add_row_bottom"){
                    //在下方插入行
                    //$dom[0].parentNode.rowIndex
                    rowIndex = $dom[0].parentNode.rowIndex;
                    table = $dom[0].parentNode.parentNode.parentNode;
                    columnNum = table.rows[0].cells.length;
                    row = "<tr>";
                    td = '<td class="design_table_td"></td>';
                    for(i = 0; i < columnNum; i++){
                        row += td;
                    }
                    row+="</tr>";
                    var r = $dom.attr("rowspan");
                    var add = 0;
                    if(r && r > 1){
                        add = Number(r) - 1;
                    }
                    // row = table.rows[rowIndex].outerHTML;
                    $(table.rows[rowIndex+add]).after(row);
                    AddTable.selectMoreCells();
                }

                if(key === "delete_row"){
                    //删除行
                    row = $dom[0].parentNode;
                    layer.confirm('确定删除该行吗?', {title:'删除提示'}, function(index){
                        $(row).remove();
                        layer.close(index);
                    });
                }
                if(key === "delete_column"){
                    //删除列
                    cellIndex = $dom[0].cellIndex;
                    table = $dom[0].parentNode.parentNode.parentNode;
                    rowsNum = table.rows.length;
                    layer.confirm('确定删除该列吗?', {title:'删除提示'}, function(index){
                        for(i = 0; i < rowsNum; i++){
                            $(table.rows[i].cells[cellIndex]).remove();
                        }
                        layer.close(index);
                    });
                }
                if(key === "merge_cells"){
                    //合并单元格
                    if(!r_min && !r_max && !c_min && !c_max){
                        return;
                    }
                    table = $dom[0].parentNode.parentNode.parentNode;
                    // $dom.find("td.td_bg_color")
                    for(i = r_min; i <= r_max; i++){
                        for(var j = c_min; j <= c_max; j++){
                            if(i === r_min && j === c_min){
                                //保留左上角的单元格，删除其他单元格
                                var colspan = c_max - c_min;
                                var rowspan = r_max - r_min;
                                if(colspan > 0){
                                    // var width = $(table.rows[i].cells[j]).attr("width");
                                    // width = Number(width.replace("px",""));
                                    // var w = width * (colspan+1);
                                    $(table.rows[i].cells[j]).attr("colspan",colspan+1);
                                        // .attr("width",w+"px");
                                }
                                if(rowspan > 0){
                                    $(table.rows[i].cells[j]).attr("rowspan",rowspan+1);
                                }
                            }else{
                                $(table.rows[i].cells[j]).remove();
                            }
                        }
                    }
                    $(".design_table").find("td.td_bg_color").removeClass("td_bg_color");
                    AddTable.selectMoreCells();
                    r_min = "";
                    r_max = "";
                    c_min = "";
                    c_max = "";
                }
                if(key === "split_cells"){
                    //拆分单元格
                    var cn = $dom.attr("colspan");
                    var rn = $dom.attr("rowspan");
                    if(cn && cn > 1){
                        cn = Number(cn) - 1;
                        var append = "";
                        td = '<td class="design_table_td"></td>';
                        for(i = 0; i < cn; i++){
                            append += td;
                        }
                        $dom.after(append);
                        $dom.removeAttr("colspan");
                    }
                    if(rn && rn > 1){
                        rn = Number(rn) - 1;
                        cellIndex = $dom[0].cellIndex;
                        rowIndex = $dom[0].parentNode.rowIndex;
                        table = $dom[0].parentNode.parentNode.parentNode;
                        rowsNum = table.rows.length;
                        td = '<td class="design_table_td"></td>';
                        for(k = rowIndex + 1; k <= rowIndex + rn; k++){
                            var cell = table.rows[k].cells[cellIndex];
                            $(cell).after(td);
                        }
                        $dom.removeAttr("rowspan");
                    }
                    AddTable.selectMoreCells();
                }
            },
            items: {//菜单列表配置
                "add_column_left": {name: "在左侧插入列", icon: "add-column-left",disabled: false},
                "add_column_right": {name: "在右侧插入列", icon: "add-column-right",disabled: false},
                "add_row_top": {name: "在上方插入行", icon: "add-row-top",disabled: false},
                "add_row_bottom": {name: "在下方插入行", icon: "add-row-bottom",disabled: false},
                "merge_cells":{name:"合并单元格",icon:"merge-cells",disabled:false},
                "split_cells":{name:"拆分单元格",icon:"split-cells",disabled:false},
                // "update": {name: "修改表格", icon: "fa-edit",disabled: false},
                // "delete_column":{name:"删除列",icon:'fa-trash-o'},
                // "delete_row":{name:"删除行",icon:'fa-trash-o'}
                "delete_column":{name:"删除列",icon:'delete-column'},
                "delete_row":{name:"删除行",icon:'delete-row'}
            }
        });
    }

    /**
     * 打开新增表格弹窗
     */
    AddTable.openAddWin = function(){
        addIndex = layer.open({
            type: 1,
            title:"添加表格",
            area:['34vw','60vh'],
            // area:['300px','400px'],
            content: $('#design_add_table_win'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
            cancel: function(index, layero){
                AddTable.closeAddWin();
                return false;
            }
        });
    }


    /**
     * 新增确定按钮
     */
    AddTable.addConfirm = function(){
        var name = $("#design_add_table_name").val();
        var rowsNum = $("#design_add_table_rows_num").val();
        rowsNum = Number(rowsNum);
        var columnsNum = $("#design_add_table_columns_num").val();
        columnsNum = Number(columnsNum);
        var columnWidth = $("#design_add_table_columns_width").val();
        var rowHeight = $("#design_add_table_rows_width").val();

        var table = '<table class=" design_table table-bordered" data-name="'+name+'">';

        var tbody = "";
        for(var i = 0; i < rowsNum; i++){
            tbody += '<tr>';
            for(var j = 0; j < columnsNum; j++){
                tbody += '<td class="design_table_td" width="'+columnWidth+'px" height="'+rowHeight+'px"></td>';
            }
            tbody += '</tr>';
        }
        table = table + tbody + '</table>';
        var tableDiv = '<div class="design_table_div">';
        var moveDiv = '<img class="table_move_img" src="'+_ctx+'/static/pageDesign/images/table/move_img2.png">';
        tableDiv += moveDiv;
        tableDiv+='<div class="table_name_div">'+name+'</div>';
        tableDiv += table+'</div>';
        $("#design_area_demo").append(tableDiv);

        $( ".design_area .design_table_div" ).draggable().resizable({handles:"n,e"});

        AddTable.closeAddWin();
        AddTable.selectMoreCells();
    }


    /**
     * 关闭新增弹窗
     */
    AddTable.closeAddWin = function () {
        layer.close(addIndex);
        $("#design_add_table_name").val("");
        $("#design_add_table_rows_num").val(2);
        $("#design_add_table_columns_num").val(2);
        $("#design_add_table_columns_width").val(200);
    }


    /**
     * 打开修改表格弹窗
     */
    AddTable.openEditWin = function(){
        editIndex = layer.open({
            type: 1,
            title:"修改表格",
            area:['32vw','24vh'],
            // area:['300px','240px'],
            content: $('#design_edit_table_win'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
            cancel: function(index, layero){
                AddTable.closeEditWin();
                return false;
            }
        });
    }

    /**
     * 修改弹窗--确定按钮
     */
    AddTable.editConfirm = function(){
        var name = $("#design_edit_table_name").val();
        editJquery.attr("data-name",name);
        AddTable.closeEditWin();
    }

    /**
     * 关闭修改弹窗
     */
    AddTable.closeEditWin = function () {
        layer.close(editIndex);
        $("#design_edit_table_name").val("");
        // $("#design_edit_table_rows_num").val("");
        // $("#design_edit_table_columns_num").val("");
        // $("#design_edit_table_columns_width").val("");
    }

    /**
     * 单元格点击事件
     */
    AddTable.tdClickEvent =function(){
        $(".design_table.table-bordered > tbody > tr > td").unbind("click").click(function(){
            $(this).addClass("selected");
        });
    }

    /**
     * 单元格点击事件
     */
    AddTable.initEvent = function(){
        $("#designDiv").click(function(e) {
            $(".design_table td.selected").removeClass("selected");
            if(e.target.classList.contains("design_table_td")){
                $(e.target).addClass("selected");
                AddTable.selectedTable = $(e.target);
            }else{
                AddTable.selectedTable = "";
            }
        });
    }

    /**
     * 表格的合并单元格鼠标滑动选中事件
     */
    AddTable.selectMoreCells = function(){
        var x1,y1,rowIndex1,columnIndex1;
        var x2,y2,rowIndex2,columnIndex2;

        $(".design_table td.design_table_td").unbind("mousedown").unbind("mouseup").mousedown(function(e){
            if(e.which === 1){
                $(".design_table").find("td.td_bg_color").removeClass("td_bg_color");
                x1 = e.offsetX;
                y1 = e.offsetY;
                // $(e.target)
                var dom = e.target;
                rowIndex1 = dom.parentNode.rowIndex;
                columnIndex1 = dom.cellIndex;
            }
        }).mouseup(function(e){
            if(e.which === 1){
                //鼠标左键
                x2 = e.offsetX;
                y2 = e.offsetY;
                var dom = e.target;
                rowIndex2 = dom.parentNode.rowIndex;
                columnIndex2 = dom.cellIndex;
                if(rowIndex1 === rowIndex2 && columnIndex1 === columnIndex2){
                    return;
                }
                if(rowIndex1 !== rowIndex2 && columnIndex1 !== columnIndex2){
                    //选中多行和多列
                    return;
                }
                var table = dom.parentNode.parentNode.parentNode;
                r_min = Math.min(rowIndex1,rowIndex2);
                r_max = Math.max(rowIndex1,rowIndex2);
                c_min = Math.min(columnIndex1,columnIndex2);
                c_max = Math.max(columnIndex1,columnIndex2);
                for(var i = r_min; i <= r_max; i++){
                    for(var j = c_min; j <= c_max; j++){
                        var colspan = $(table.rows[i].cells[j]).attr("colspan");
                        var rowspan = $(table.rows[i].cells[j]).attr("rowspan");
                        if(colspan || rowspan){
                            return;
                        }
                    }
                }
                for(i = r_min; i <= r_max; i++){
                    for(j = c_min; j <= c_max; j++){
                        $(table.rows[i].cells[j]).addClass("td_bg_color");
                    }
                }
                x1 = "";
                y1 = "";
                rowIndex1 = "";
                columnIndex1 = "";
                x2 = "";
                y2 = "";
                rowIndex2 = "";
                columnIndex2 = "";
            }
        });
    }
});