;(function($, window, document, undefined) {
	
	var ajax = function(ele, opts){
		this.type         = opts.type || "get";
        this.url          = opts.url;
        this.param        = opts.data || {};
        this.isShowLoader = opts.isShowLoader == undefined || false;
        this.dataType     = opts.dataType ;
        this.contentType  = opts.contentType ;
        this.callBack     = opts.success;
//        this.error        = opts.error ;
	}

	ajax.prototype = {
            //初始化
            init: function(){
                this.sendRequest();
            },
            //渲染loader
            showLoader: function(){
                if(this.isShowLoader){
                	showLoad();
                }
            },
            //隐藏loader
            hideLoader: function(){
                if(this.isShowLoader){
                	hiddenLoad();
                }
            },
            //发送请求
            sendRequest: function(){
                var self = this;
                $.ajax({
                    type: this.type,
                    url: this.url,
                    data: this.param,
                    contentType : this.contentType,
                    dataType: this.dataType,                    
                    success: function(res){
                    	self.hideLoader();//关闭等待动画
                        if (res != null && res != "") {        	
                            if(self.callBack){
                                if (Object.prototype.toString.call(self.callBack) === "[object Function]") {   //Object.prototype.toString.call方法--精确判断对象的类型
                                	if(res.status == '1'){                                		 
                                		self.callBack(res); 
                                	}else if(res.status == '0'){
                                		swal({
            							    title : "系统提示",// 展示的标题
            							   	text : res.msg, // 内容
            							   	type: "error",
            							   	showCloseButton : false, // 展示关闭按钮
            							   	allowOutsideClick : false,
            							   	showConfirmButton : false,
            							   	timer: 1000
            							});
                                		self.callBack(res); 
                                	}else{
                                		self.callBack(res);
                                	}                                    
                                }else{
                                    console.log("callBack is not a function");
                                }
                                
                            }
                        }
                    },
                    beforeSend: this.showLoader(),
//                    complete: this.hideLoader(),
                })
            }
        };
	$.issp_ajax = function(options) {
		var myajax = new ajax(this,options);
		myajax.init(options);
	}	
})(jQuery, window, document);