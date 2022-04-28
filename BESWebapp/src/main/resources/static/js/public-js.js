/* 
 * ---公共js方法维护
 * --author : Bes研发小组共同维护
 * --version: 1.0
 */

/**
 * js无刷新下载文件方法
 * @param _url 导出执行的controller路径（可能带有参数，比如有单位树的列表，需要带左侧单位树的条件）
 * @param filename 导出Excel的文件名 如：单位信息，需要带.xls
 * @param filepath ${pageContext.request.contextPath}
 * @version 1.0
 */
function FileDownload(url,filename,filepath){
    var thelement = {};//声明内部对象
    thelement.url = url;
    thelement._ifbody = null;
    thelement._iframe = document.createElement("iframe");
    thelement._form = document.createElement("form");//form表单元素
    thelement._body = document.createElement("div");//form表单容器不显示
    thelement._input = document.createElement("input");//元素
    thelement._input2 = document.createElement("input");//元素
    thelement._init = function(){//初始类函数
        thelement._body.style.display = "none";
        thelement._body.appendChild(thelement._iframe);
        document.body.appendChild(thelement._body);
        thelement._form.action = [thelement.url,"?time=",(new Date()).getTime()].join("");
        thelement._form.method = "post";
        thelement._input.type = "hidden";
        thelement._input.name = "fileName";
        thelement._input2.type = "hidden";
        thelement._input2.name = "filePath";
        if(!filename){
        	filename = "";
    	}
        thelement._input.value = filename;
        thelement._input2.value = filepath;
    	thelement._form.appendChild(thelement._input);
    	thelement._form.appendChild(thelement._input2);
        if (thelement._iframe.contentDocument)
        	thelement._ifbody = thelement._iframe.contentDocument.body;
        else if (test.document)
        	thelement._ifbody = thelement._iframe.document.body;
        else
        	thelement._ifbody = thelement._iframe.contentWindow.document.body;
        thelement._ifbody.appendChild(thelement._form);
        thelement._form.submit();
    };
    thelement._init();//初始类函数
};
/*编程的下载*/
function FileDownloadbc(url,filename,filepath,f_id){
	var thelement = {};//声明内部对象
	thelement.url = url;
	thelement._ifbody = null;
	thelement._iframe = document.createElement("iframe");
	thelement._form = document.createElement("form");//form表单元素
	thelement._body = document.createElement("div");//form表单容器不显示
	thelement._input = document.createElement("input");//元素
	thelement._input2 = document.createElement("input");//元素
	thelement._input3 = document.createElement("input");//元素
	thelement._init = function(){//初始类函数
		thelement._body.style.display = "none";
		thelement._body.appendChild(thelement._iframe);
		document.body.appendChild(thelement._body);
		thelement._form.action = [thelement.url,"?time=",(new Date()).getTime()].join("");
		thelement._form.method = "post";
		thelement._input.type = "hidden";
		thelement._input.name = "fileName";
		thelement._input2.type = "hidden";
		thelement._input2.name = "filePath";
		thelement._input3.name = "f_id";
		if(!filename){
			filename = "";
		}
		thelement._input.value = filename;
		thelement._input2.value = filepath;
		thelement._input3.value = f_id;
		thelement._form.appendChild(thelement._input);
		thelement._form.appendChild(thelement._input2);
		thelement._form.appendChild(thelement._input3);
		if (thelement._iframe.contentDocument)
			thelement._ifbody = thelement._iframe.contentDocument.body;
		else if (test.document)
			thelement._ifbody = thelement._iframe.document.body;
		else
			thelement._ifbody = thelement._iframe.contentWindow.document.body;
		thelement._ifbody.appendChild(thelement._form);
		thelement._form.submit();
	};
	thelement._init();//初始类函数
};

/**
 * 导出Excel方法
 * @param _url 导出执行的controller路径（可能带有参数，比如有单位树的列表，需要带左侧单位树的条件）
 * @param _fileName 导出Excel的文件名 如：单位信息，不用带.xls了
 * @param _path ${pageContext.request.contextPath}
 * @param _data json数据 如：查询条件等
 * @version 1.0
 */
function doExp(_url,_fileName,_path,_data){
	$.issp_ajax({
		type:"post",
		data:_data,
		url:_url,
		dataType:"json",
		success:function(val){
			if(val.map.status=='1'){//导出excel成功
				var fname = _fileName+".xls";
	            var path = val.map.file;
				FileDownload(_path+"/file/fileDownload",fname,path);
			}else{
				swal({title: "导出Excel失败,请联系管理员",timer: 1500,type:"error"});//swalAlert警告弹窗
			}
		},
		error:function(){
			swal({title: "系统繁忙,请稍后重试",timer: 1500,type:"error"});//swalAlert警告弹窗
		}
	});
}
/**
 * 逻辑点调试(灯控单通道开关)
 * @param _fId 
 * @param obj
 * @returns
 */
function setLampPoint(_fId,obj){
	var f_init_val = "";
	var pointID = obj.id;
	if(obj.value == "0"){
		f_init_val = "255";
	}else if(obj.value == "255"){
		f_init_val = "0";
	}
	$.issp_ajax({
		url : _ctx + "/view/basedatamanage/eqmanage/BESSbpzController/debugPointInfo",
		type : "post",
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		data : JSON.stringify({
			f_sys_name : "",
			f_id : _fId,
			f_work_mode : "0",//模式“0”自动
			f_init_val : f_init_val,
			tabname : "bes_digit_ouput",
			nodeLevel : "5",//用来判定属于哪个系统(楼控系统为7，照明系统为5)
			f_node_attribution : "2",
		}),
				success : function(result) {
						if(obj.value == "0"){
							$("#"+ pointID ).val("255");
							$("#"+ pointID +".switch-button").removeClass("offline");
							$("#"+ pointID +".switch-button").addClass("online");
							$("#"+ pointID +".circle-button").removeClass("circleoffline");
							$("#"+ pointID +".circle-button").addClass("circleonline");
							
						}else if(obj.value == "255"){
							$("#"+ pointID ).val("0");
							$("#"+ pointID +".switch-button").removeClass("online");
							$("#"+ pointID +".circle-button").removeClass("circleonline");
							$("#"+ pointID +".circle-button").addClass("circleoffline");
							$("#"+ pointID +".switch-button").addClass("offline");
						}
				},
				error : function(result) {
					swal(result.msg, "", "error");
				}
			});
}
/**
 * 灯控多通道开关
 * @param _fId
 * @param obj
 * @returns
 */
function setLampPointList(_fId,obj){
	var f_init_val = "";
	var pointID = obj.id;
	if(obj.value == "0"){
		f_init_val = "255";
	}else if(obj.value == "255"){
		f_init_val = "0";
	}
	$.issp_ajax({
		url : _ctx + "/view/basedatamanage/eqmanage/BESSbpzController/debugPointList",
		type : "post",
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		data : JSON.stringify({
			f_sys_name : "",
			f_id : _fId,
			f_work_mode : "0",//模式“0”自动
			f_init_val : f_init_val,
			tabname : "bes_digit_ouput",
			nodeLevel : "",//选中节点在树上的级数(楼控系统为7，照明系统为5)
			f_node_attribution : "2",
		}),
		success : function(result) {
			if(obj.value == "0"){
				$("#"+ pointID ).val("255");
				$("#"+ pointID ).removeClass("offline");
				$("#"+ pointID ).addClass("online");
			}else if(obj.value == "255"){
				$("#"+ pointID ).val("0");
				$("#"+ pointID ).removeClass("online");
				$("#"+ pointID ).addClass("offline");										
			}
		},
		error : function(result) {
				swal(result.msg, "", "error");
		}
	});
}
