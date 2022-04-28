;(function($, window, document, undefined) {
	var webroot = getRootPath();

	var gaugeChart = function(ele, opt) {
		this.$element = ele, 
		this.defaults = {
			'themeStyle' : 'light', //内置两种主题颜色，默认dark，可选为：'dark'¦'light',整体风格设置后，也可单独对 backgroundColor等属性修改
			'ajaxUrl' : null,
			'title' : '',// 标题
			'title_show' : '',//标题是否显示
			'title_x' : 'center',// 标题水平安放位置，默认为左对齐，可选为：center' ¦ 'left' ¦ 'right'¦ {number}（x坐标，单位px）
			'title_y' : 130,
			'title_textAlign' : null,// 水平对齐方式，默认根据x设置自动调整
			'title_backgroundColor' : 'rgba(0,0,0,0)',
			'title_borderColor' : '#ccc',// 标题边框颜色
			'title_borderWidth' : 0,// 标题边框线宽，单位px，默认为0（无边框）
			'title_padding' : 5,// 标题内边距，单位px，默认各方向内边距为5， 接受数组分别设定上右下左边距，同css
			'title_itemGap' : 10,// 主副标题纵向间隔，单位px，默认为10，
			'title_fontSize' : 12,
			'title_fontWeight' : 'normal',
			'title_color' : '#8fe3f7',
			'subtext' : '',// 副标题
			'subtext_color' : '#aaa',// 副标题文字颜色
			'sublink' : '',// 副标题链接

			'legend_show' : '',//图例是否显示
			'legend_data' : '',//图例数据
			'legend_orient' : 'horizontal', // 布局方式，默认为水平布局，可选为：'horizontal' ¦ 'vertical' 'right' ¦ {number}（x坐标，单位px）
			'legend_x' : 'center', //  水平安放位置，默认为全图居中，可选为：'center' ¦ 'left' ¦ 'right'¦ {number}（x坐标，单位px）
			'legend_y' : 'top', // 垂直安放位置，默认为全图顶端，可选为： 'top' ¦ 'bottom' ¦ 'center' ¦ {number}（y坐标，单位px）
			'legend_backgroundColor' : 'rgba(0,0,0,0)',
			'legend_borderColor' : '#ccc', // 图例边框颜色
			'legend_borderWidth' : 0, // 图例边框线宽，单位px，默认为0（无边框）
			'legend_padding' : 5, // 图例内边距，单位px，默认各方向内边距为5，  接受数组分别设定上右下左边距，同css
			'legend_itemGap' : 10, // 各个item之间的间隔，单位px，默认为10，  横向布局时为水平间隔，纵向布局时为纵向间隔
			'legend_itemWidth' : 20, // 图例图形宽度
			'legend_itemHeight' : 14, // 图例图形高度
			'legend_color' : '#000', // 图例文字颜色
			
			'toolbox_show' : '',//工具箱是否显示
			'toolbox_orient' : 'horizontal', // 布局方式，默认为水平布局，可选为： 'horizontal' ¦ 'vertical'
			'toolbox_x' : 'right', // 水平安放位置，默认为全图右对齐，可选为： 'center' ¦ 'left' ¦ 'right' ¦ {number}（x坐标，单位px）
			'toolbox_y' : 'top', // 垂直安放位置，默认为全图顶端，可选为： 'top' ¦ 'bottom' ¦ 'center' ¦ {number}（y坐标，单位px）
			'toolbox_color' : [ '#1e90ff', '#22bb22', '#4b0082', '#d2691e' ],
			'toolbox_backgroundColor' : 'rgba(0,0,0,0)', // 工具箱背景颜色
			'toolbox_borderColor' : '#ccc', // 工具箱边框颜色
			'toolbox_borderWidth' : 0, // 工具箱边框线宽，单位px，默认为0（无边框）
			'toolbox_padding' : 5, // 工具箱内边距，单位px，默认各方向内边距为5，  接受数组分别设定上右下左边距，同css
			'toolbox_itemGap' : 10, // 各个item之间的间隔，单位px，默认为10，  横向布局时为水平间隔，纵向布局时为纵向间隔
			'toolbox_itemSize' : 16, // 工具箱图形宽度
			'toolbox_featureImageIcon' : {}, // 自定义图片icon

			'xName' : '',// X轴名称
			'xType' : 'category',// X轴类型
			'xData' : '',// X轴显示数据列
			'yName' : '',// Y轴名称
			'yType' : 'value',// Y轴类型
			'gridLeft' : '3%',//
			'gridRight' : '4%',//
			'gridBottom' : '3%',//
			'gridContainLabel' : true,//
			'showSeriesLable' : true,// 是否显示seriesItemLable
			'seriesLablePosition' : 'top',// seriesItemLable位置
			'series_Lable_formatter' : '{b}\n{d}%',
			'trigger' : 'item', // 当trigger为’item’时只会显示该点的数据，为’axis’时显示该列下所有坐标轴所对应的数据。
			'axisPointer' : 'line', // 坐标轴指示器，坐标轴触发有效, 默认为直线，可选为：'line' | 'shadow'
			'formatter' : '',//
			'legendMap' : null,
			'keyVal' : null, //查询关键字的值
			'keyWords' : null, //查询的关键字
			'center' : '',//圆心坐标
			'radius' : '',//半径，第一项内半径，第二项外半径
			'radiusInner' : '',//内环的半径
			'seriesItemInner' : '',//环形时内部小环
		// 图例显示列Map key显示名称 value显示数据列名称
			'callBacks' : '', //传递自定义的图形点击事件
			'series_color' : '#fa4936',//仪表盘内标题颜色
			'series_fontSize' : 12,//仪表盘内标题字号
			'series_fontWeight' : 400,
			'series_offsetCenter' : [0, '-50%'],//
			'series_titleshow' : false,
			
			'startAngle' : 180,//仪表盘起始角度
			'endAngle' : 0,//仪表盘结束角度
			'min' : 0,//仪表盘最小数据值
			'max' : 100,//仪表盘最大数据值
			'splitNumber' : 10,//仪表盘刻度分割段数
			
			'axisLine_width' : 26,//轴线宽度
			'shadowBlur' : 0,//轴线阴影
			'axisLine_color' : [[0.3, '#67e0e3'],[0.7, '#37a2da'],[1, '#fd666d']],
			
			'axisTick_show' : true,//是否显示刻度 
			'axisTick_color' : '#eee',//刻度颜色
			'axisTick_width' : 1,//刻度宽度
			'axisTick_length' : 8,//刻度线长
			'axisTick_splitNumber' : 5,//刻度之间分割的刻度数
			'splitLine_show' : true,//是否显示分隔线
			'splitLine_length' : 30,//分隔线线长,支持相对半径的百分比
			'splitLine_color' : '#eee',//线的颜
			'axisLabel_distance' : '',//标签与刻度的距离
			'axisLabel_color' : '',//标签的颜色
			'axisLabel_fontSize' : 12,//标签字体
			
			'pointer_width' : 5,//指针宽度
			'pointer_length' : '50%',//指针长度
			'pointer_color' : '',

            'detail_fontSize':16,//仪表盘下方字体大小
			'detail_formatter' : '{value}',//仪表盘下方数据源
			'detail_offsetCenter' : [0, '30%'] //仪表盘下方数据与仪表盘距离
			
		}, 
		this.options = $.extend({}, this.defaults, opt)
	}

	// 定义方法
	gaugeChart.prototype.initGauge = function(callBack) {
		$.ajax({
			url : webroot + this.options.ajaxUrl,
			type : "post",
			success : function(data) {
				callBack(data);
			}
		});
	}
	
	gaugeChart.prototype.buildGaugeChart = function buildGaugeChart(data) {
		if (data == null)
			return false;
		if (data.status == "-1") {
			alert(data.msg);
			return false;
		}
//		if (this.options.xData == null || this.options.xData == "") {
//			alert("X轴显示列未指定！");
//			return false;
//		}
		if (this.options.legendMap == null || this.options.legendMap == "") {
			alert("显示数据列未指定！");
			return false;
		}
		//var xArray = data.data[this.options.xData];
		var legendArray = new Array();
		var seriesInfo = new Array();
		var seriesData =new Array();
		// 遍历json对象的每个key/value对
		var keySet = this.options.legendMap.keySet();
		var values = this.options.legendMap.values();
		for ( var i in keySet) {
			legendArray.push(keySet[i]);
			
			for (var d=0;d<data.data.length;d++){
				var arr = {};
				arr = data.data[d];
				for (var m in arr){
					if (this.options.keyWords == m){
					if (this.options.keyVal == arr[m]){
						seriesData.push({
							name:keySet[i],
							value:arr[values[i]]
							});
					}
					}
				}
			}
		}
			var seriesItem = {
				name : this.options.keyVal,
				type : 'gauge',
				radius : this.options.radius,
				startAngle : this.options.startAngle,
				endAngle :this.options.endAngle,
				min : this.options.min,
				max : this.options.max,
				splitNumber : this.options.splitNumber,
		        axisLine: {
		            show: true,
		            lineStyle: {
		                width: this.options.axisLine_width,
		                shadowBlur: this.options.shadowBlur,
		                color: this.options.axisLine_color,
		            }
		        },
	            axisTick: {
	                show: this.options.axisTick_show,
	                lineStyle: {
	                    color: this.options.axisTick_color,
	                    width: this.options.axisTick_width,
	                },
	                length: this.options.axisTick_length,
	                splitNumber: this.options.axisTick_splitNumber,
	            },
	            splitLine: {
	                show: this.options.splitLine_show,
	                length: this.options.splitLine_length,
	                lineStyle: {
	                    color: this.options.splitLine_color,
	                }
	            },
	            title: {
	            	
	            	show : this.options.series_titleshow,
	                color: this.options.series_color,
	                fontSize: this.options.series_fontSize,
	                fontWeight: this.options.series_fontWeight,
	                offsetCenter: this.options.series_offsetCenter,
	            },
	            axisLabel: {
	            	distance : this.options.axisLabel_distance,
	            	color : this.options.axisLabel_color,
	            	fontSize : this.options.axisLabel_fontSize,
	            },
	            detail: {
	                formatter : this.options.detail_formatter,
	                fontSize: this.options.detail_fontSize,
	                offsetCenter : this.options.detail_offsetCenter,
	            },
	            pointer: {
	            	show : true,
	            	length: this.options.pointer_length,
	            	width: this.options.pointer_width,
	            	itemStyle: {
		            	color : this.options.pointer_color,
		            }
	            },
	            
				data : seriesData,
			};

			seriesInfo.push(seriesItem);
		
		var option = {
			title : {
				show : this.options.title_show,
				text : this.options.title,
				x : this.options.title_x,
				y : this.options.title_y,
//				textAlign : this.options.title_textAlign,
//				backgroundColor : this.options.title_backgroundColor,
//				borderColor : this.options.title_borderColor,
//				borderWidth : this.options.title_borderWidth,
//				padding : this.options.title_padding,
//				itemGap : this.options.title_itemGap,
				textStyle : {
					fontSize : this.options.title_fontSize,
					fontWeight : this.options.title_fontWeight,
					color : this.options.title_color,// 主标题文字颜色
				},
//				subtextStyle : {
//					color : this.options.subtext_color
//				},
//				subtext : this.options.subtext,
//				sublink : this.options.sublink
			},
			tooltip : {
				formatter : this.options.formatter
			},
//			grid : {
//				left : this.options.gridLeft,
//				right : this.options.gridRight,
//				bottom : this.options.gridBottom,
//				containLabel : this.options.gridContainLabel
//			},
			toolbox : {
				show : this.options.toolbox_show,
				feature : {
//					mark : {
//						show : true
//					},
//					dataView : {
//						show : true,
//						readOnly : false
//					},
					restore : {
						show : true
					},
					saveAsImage : {
						show : true
					}
				},
				orient : this.options.toolbox_orient, // 布局方式，默认为水平布局，可选为：
				x : this.options.toolbox_x, // 水平安放位置，默认为全图右对齐，可选为：
				y : this.options.toolbox_y, // 垂直安放位置，默认为全图顶端，可选为：
				color : this.options.toolbox_color,
				backgroundColor : this.options.toolbox_backgroundColor, // 工具箱背景颜色
				borderColor : this.options.toolbox_borderColor, // 工具箱边框颜色
				borderWidth : this.options.toolbox_borderWidth, // 工具箱边框线宽，单位px，默认为0（无边框）
				padding : this.options.toolbox_padding, // 工具箱内边距，单位px，默认各方向内边距为5，
				itemGap : this.options.toolbox_itemGap, // 各个item之间的间隔，单位px，默认为10，
				itemSize : this.options.toolbox_itemSize, // 工具箱图形宽度
				featureImageIcon : this.options.toolbox_featureImageIcon, // 自定义图片icon
				featureTitle : {
					mark : '辅助线开关',
					markUndo : '删除辅助线',
					markClear : '清空辅助线',
					dataZoom : '区域缩放',
					dataZoomReset : '区域缩放后退',
					dataView : '数据视图',
					lineChart : '折线图切换',
					barChart : '柱形图切换',
					gaugeChart : '饼状图切换',
					restore : '还原',
					saveAsImage : '保存为图片'
				}
			},
			calculable : true,
			series : seriesInfo,
			color : this.options.gauge_color //传递颜色数组，自定义颜色
		}
		var isspGaugeChart = echarts.init(this.$element[0], this.options.themeStyle);
		isspGaugeChart.setOption(option);
		$(window).resize(function() {
			isspGaugeChart.resize();
		});
		
		//点击图形触发
		var callBacks = this.options.callBacks;
		isspGaugeChart.on('click', function (params) {
			var aa = callBacks(params);
		});
	}

	$.fn.isspGauge = function(options) {
		var gauge = new gaugeChart(this, options);
		gauge.initGauge(function(data) {
			gauge.buildGaugeChart(data);
		});
	}
	
	$.fn.setGaugeData = function(options,data) {
		var gauge = new gaugeChart(this, options);
		gauge.buildGaugeChart(data);
	}

})(jQuery, window, document);