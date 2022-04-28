;(function($, window, document, undefined) {
	var webroot = getRootPath();

	var pieChart = function(ele, opt) {
		this.$element = ele, 
		this.defaults = {
			'themeStyle' : 'light', //内置两种主题颜色，默认dark，可选为：'dark'¦'light',整体风格设置后，也可单独对 backgroundColor等属性修改
			'ajaxUrl' : null,
			'title' : '',// 标题
			'title_show' : '',//标题是否显示
			'title_x' : 'x',// 标题水平安放位置，默认为左对齐，可选为：center' ¦ 'left' ¦ 'right'¦ {number}（x坐标，单位px）
			'title_y' : 'top',// 垂直安放位置，默认为全图顶端，可选为：垂直安放位置，默认为全图顶端，可选为：¦ {number}（y坐标，单位px）
			'title_textAlign' : null,// 水平对齐方式，默认根据x设置自动调整
			'title_backgroundColor' : 'rgba(0,0,0,0)',
			'title_borderColor' : '#ccc',// 标题边框颜色
			'title_borderWidth' : 0,// 标题边框线宽，单位px，默认为0（无边框）
			'title_padding' : 5,// 标题内边距，单位px，默认各方向内边距为5， 接受数组分别设定上右下左边距，同css
			'title_itemGap' : 10,// 主副标题纵向间隔，单位px，默认为10，
			'title_fontSize' : 18,
			'title_fontWeight' : 'bolder',
			'title_color' : '#000',
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
			'seriesname' : '',//鼠标放在饼图上时悬浮框的标题 {a}
			'seriesLablePosition' : 'top',// seriesItemLable位置
			'series_Lable_formatter' : '{b}\n{d}%',
			'trigger' : 'item', // 当trigger为’item’时只会显示该点的数据，为’axis’时显示该列下所有坐标轴所对应的数据。
			'axisPointer' : 'line', // 坐标轴指示器，坐标轴触发有效, 默认为直线，可选为：'line' | 'shadow'
			'formatter' : '{a}</br>{b} : {c}',//
			'legendMap' : null,
			'keyVal' : null, //查询关键字的值
			'keyWords' : null, //查询的关键字
			'center' : '',//圆心坐标
			'radius' : '',//半径，第一项内半径，第二项外半径
			'radiusInner' : '',//内环的半径
			'series_center' : ['50%', '60%'],
			'seriesItemInner' : '',//环形时内部小环
		// 图例显示列Map key显示名称 value显示数据列名称
			'callBacks' : '', //传递自定义的图形点击事件

			'pie_color' : null,//饼图颜色
			'pie_colorInner' : null,//内环颜色
			'pie_startAngle' : 100 //饼图起始角度
		}, 
		this.options = $.extend({}, this.defaults, opt)
	}

	// 定义方法
	pieChart.prototype.initPie = function(callBack) {
		$.ajax({
			url : webroot + this.options.ajaxUrl,
			type : "post",
			success : function(data) {
				callBack(data);
			}
		});
	}
	
	pieChart.prototype.buildPieChart = function buildPieChart(data) {
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
		var seriesInfo = new Array();
		var seriesData =new Array();
		var seriesDataInner =new Array();
		// 遍历json对象的每个key/value对
		var keySet = this.options.legendMap.keySet();
		var values = this.options.legendMap.values();
		for ( var i in keySet) {			
			for (var d=0;d<data.data.length;d++){
				var arr = {};
				arr = data.data[d];
						//外环数据
						seriesData.push({
							name : arr[keySet[i]],
							value : arr[values[i]]
							});
						//内环数据，只需与外环value对应
						seriesDataInner.push({
							name : '',
							value : arr[values[i]]
						})

			}
		}
			var seriesItem = {
				name : this.options.seriesname,
				type : 'pie',
				radius : this.options.radius,
				center: this.options.series_center,
		        itemStyle: { //图形样式
		            normal: {
		                borderColor: '#1e2239',
		                borderWidth: 0,
		            },
		        },
				label : {
					normal : {
						show : this.options.showSeriesLable,
						position : this.options.seriesLablePosition,
						formatter: this.options.series_Lable_formatter,
					}
				},		
				color : this.options.pie_color, //传递颜色数组，自定义颜色
				startAngle : this.options.pie_startAngle,
				data : seriesData
			};
			//环形时，定义内环
			var seriesItemInner = {
				name : this.options.seriesname,
				type : 'pie',
				radius : this.options.radiusInner,
				center: this.options.series_center,
		            label: {
		                normal: {
		                    show: false
		                },
		                emphasis: {
		                    show: false
		                }
		            },
		            labelLine: {
		                normal: {
		                    show: false
		                },
		                emphasis: {
		                    show: false
		                }
		            },
		            animation: false,
		            tooltip: {
		                show: false
		            },		
		            color: this.options.pie_colorInner,
		            startAngle : this.options.pie_startAngle,
					data : seriesDataInner,
				}
			seriesInfo.push(seriesItem);
			seriesInfo.push(seriesItemInner)
		
		var option = {
			title : {
				show : this.options.title_show,
				text : this.options.title,
				x : this.options.title_x,
				y : this.options.title_y,
				textAlign : this.options.title_textAlign,
				backgroundColor : this.options.title_backgroundColor,
				borderColor : this.options.title_borderColor,
				borderWidth : this.options.title_borderWidth,
				padding : this.options.title_padding,
				itemGap : this.options.title_itemGap,
				textStyle : {
					fontSize : this.options.title_fontSize,
					fontWeight : this.options.title_fontWeight,
					color : this.options.title_color,// 主标题文字颜色
				},
				subtextStyle : {
					color : this.options.subtext_color
				},
				subtext : this.options.subtext,
				sublink : this.options.sublink
			},
			tooltip : {
				trigger : this.options.trigger,
				axisPointer : {
					type : this.options.axisPointer
				},
				formatter : this.options.formatter
			},
			legend : {
		        orient: 'vertical',
		        //selected: data.selected		        
				data : this.options.legend_data, 
				show : this.options.legend_show, 
				orient : this.options.legend_orient,
				x : this.options.legend_x,
				y : this.options.legend_y,
				backgroundColor : this.options.legend_backgroundColor,
				borderColor : this.options.legend_borderColor,
				borderWidth : this.options.legend_borderWidth,
				padding : this.options.legend_padding,
				itemGap : this.options.legend_itemGap,
				itemWidth : this.options.legend_itemWidth,
				itemHeight : this.options.legend_itemHeight,
				textStyle : {
					color : this.options.legend_color
				// 图例文字颜色
				}
			},
			grid : {
				left : this.options.gridLeft,
				right : this.options.gridRight,
				bottom : this.options.gridBottom,
				containLabel : this.options.gridContainLabel
			},
			toolbox : {
				show : this.options.toolbox_show,
				feature : {
					mark : {
						show : true
					},
					dataView : {
						show : true,
						readOnly : false
					},
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
					pieChart : '饼状图切换',
					restore : '还原',
					saveAsImage : '保存为图片'
				}
			},
			calculable : true,
			series : seriesInfo
		}
		var isspPieChart = echarts.init(this.$element[0], this.options.themeStyle);
		isspPieChart.setOption(option);
		$(window).resize(function() {
			isspPieChart.resize();
		});
		
		//点击图形触发
		var callBacks = this.options.callBacks;
		isspPieChart.on('click', function (params) {
			var aa = callBacks(params);
		});
		return isspPieChart;
	}

	$.fn.isspPie = function(options) {
		var pie = new pieChart(this, options);
		pie.initPie(function(data) {
			pie.buildPieChart(data);
		});
	}
	
	$.fn.setPieData = function(options,data) {
		var pie = new pieChart(this, options);
		return pie.buildPieChart(data);
	}

})(jQuery, window, document);