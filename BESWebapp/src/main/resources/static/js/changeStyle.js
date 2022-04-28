/**
 * 界面换肤用JS
 * 创建人：sunhao
 */
var js_changeStyle = (function($, window, document, undefined) {
	var cssStyle=document.getElementById('skinColour');
	return{
		changeStyle:function(color){
			event.stopPropagation();
			cssStyle.href = "static/css/skinColour_" + color + ".css";
			$.cookie('skin_color', color,{ expires: 7,path: '/' });//存入cookie  设置存储时间与根路径

			//换肤前如果在区域管理页面、设计模式下，提示是否保存
            var $AreaTree = $("#areaTree");
            if($AreaTree && typeof $AreaTree.jstree !== "undefined" && typeof $AreaTree.jstree(true).get_selected  !== "undefined"){
                ChangeSkin.changeDesignSkin(color);
			}

		},
		cssStyle_skin:function (){
			//从cookies读取主题
	        var skinColorvalue=$.cookie('skin_color');
	        if(skinColorvalue==null||skinColorvalue==""){
	        	//统一调整样式：修改为默认浅蓝色背景
	          cssStyle.href = "static/css/skinColour_white.css";
	        }else{
	        	cssStyle.href = "static/css/skinColour_"+skinColorvalue+".css";
	        }
            if(!skinColorvalue){
                skinColorvalue = "white";
            }
            //将皮肤值保存到session中
            $.ajax({
                url : _ctx+"/view/pageDesign/setSkinColor",
                type : "post",
                data : {
                    skinColor:skinColorvalue,
                },
                dataType:'json',
                // contentType : "application/json; charset=utf-8",
                success : function(result){
                }});
		}
	}
})(jQuery, window, document);