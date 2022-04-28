;(function($, window, document, undefined) {
	var webroot = getRootPath();
	var barChart = function(ele, opt) {
		this.$element = ele, 
		this.defaults = {
			'themeStyle' : 'default', //内置三种主题颜色，默认default，可选为：'dark'¦'light',整体风格设置后，也可单独对 backgroundColor等属性修改
			'ajaxUrl' : null,
			'title' : '',// 标题
			'title_x' : 'x',// 标题水平安放位置，默认为左对齐，可选为：center' ¦ 'left' ¦ 'right'¦ {number}（x坐标，单位px）
			'title_y' : 'top',// 垂直安放位置，默认为全图顶端，可选为：垂直安放位置，默认为全图顶端，可选为：¦ {number}（y坐标，单位px）
			'title_textAlign' : null,// 水平对齐方式，默认根据x设置自动调整
			'title_backgroundColor' : 'rgba(0,0,0,0)',
			'title_borderColor' : '#ccc',// 标题边框颜色
			'title_borderWidth' : 0,// 标题边框线宽，单位px，默认为0（无边框）
			'title_padding' : 10,// 标题内边距，单位px，默认各方向内边距为5， 接受数组分别设定上右下左边距，同css
			'title_itemGap' : 10,// 主副标题纵向间隔，单位px，默认为10，
			'title_fontSize' : 18,
			'title_fontWeight' : 'bolder',
			'title_color' : '#d0d0d0',
			'subtext' : '',// 副标题
			'subtext_color' : '#aaa',// 副标题文字颜色
			'sublink' : '',// 副标题链接

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
			'legend_color' : '#d0d0d0', // 图例文字颜色
			'legend_top' : 40,
			'legend_selected' : '',//图例的选中状态
			'legend_selectedMode' : true,//图例选择的模式，控制是否可以通过点击图例改变系列的显示状态。
			'legend_type' : 'scroll',//图例类型，'plain'：普通图例。缺省就是普通图例。'scroll'：可滚动翻页的图例。
			'legend_align' : 'left',
			'legend_left' : '10%',
			'legend_right' : '3%',

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
			
			'dataZoom_type' : 'inside',//内置于坐标系中，使用户可以在坐标系上通过鼠标拖拽、鼠标滚轮、手指滑动（触屏上）来缩放或漫游坐标系。
			'dataZoom_disabled' : '', //是否停止组件的功能

			'barWidth' : '',
			'barGap' : '',//柱间距离
			'stack' : null,// 堆叠显示
			'xName' : '',// X轴名称
			'xType' : 'category',// X轴类型
			'xData' : '',// X轴显示数据列
			'xAxis_fontcolor' : '#fefefe',//x轴字体颜色
			'xaxisLine_color' : 'rgb(58,123,204,0.2)',//x轴线条颜色
			'xsplitLine_color' : 'rgb(58,123,204,0.2)',//x轴网格线颜色
			'yName' : '',// Y轴名称
			'yType' : 'value',// Y轴类型
			'yAxis_fontcolor' : '#fefefe',//y轴字体颜色
			'yaxisLine_color' : 'rgb(58,123,204,0.2)',//y轴线条颜色
			'ysplitLine_color' : ['rgb(58,123,204,0.2)'],//y轴网格线颜色
			'gridTop' : 80, //距离容器上侧的距离
			'gridLeft' : '3%',//
			'gridRight' : '4%',//
			'gridBottom' : '3%',//
			'gridContainLabel' : true,//
			'showSeriesLable' : true,// 是否显示seriesItemLable 即图形上的数据
			'seriesLablePosition' : 'top',// seriesItemLable位置
			'seriesLableFormatter' : '', //图形上的数据格式  {c}%
			'trigger' : 'item', // 当trigger为’item’时只会显示该点的数据，为’axis’时显示该列下所有坐标轴所对应的数据(为axis时formatter设为'')。
			'formatter' :'',// {a} <br/>{b} : {c}
			'axisPointer' : 'line', // 坐标轴指示器，坐标轴触发有效, 默认为直线，可选为：'line' | 'shadow'
			'legendMap' : null,
			'callBacks' : '', //传递自定义的图形点击事件
		// 图例显示列Map key显示名称 value显示数据列名称
			
			'markLine_data' : '', //标线数据，格式：[{yAxis: '0.7'},{yAxis: '0.5'}],
			'markLine_formatter' : null,//标线的标签内容，为空时默认写入markLine_data
			'markLine_position' : null,//标线的标签位置，默认线的end，可选：'start' 线的起始点，	'middle' 线的中点。		
			'markLine_type' : 'dashed',//标线类型，default: solid,可选'dashed''dotted'
			'markLine_color' : '',//标线颜色
			
			'bar_color' : '', //柱形的颜色
		}, 
		this.options = $.extend({}, this.defaults, opt)
	}


	// 定义方法
	barChart.prototype.initBar = function(callBack) {
		$.ajax({
			url : webroot + this.options.ajaxUrl,
			type : "post",
			success : function(data) {
				callBack(data);
			}
		});
	}
	
	barChart.prototype.buildBarChart = function buildBarChart(data) {
		if (data == null)
			return false;
		if (data.status == "-1") {
			alert(data.msg);
			return false;
		}
		
		
		var xArray = null;
		var legendArray = new Array();
		var dataArray = null;
		var seriesInfo = new Array();
		//以legendMap判断传入的数据格式
		if (this.options.legendMap == null || this.options.legendMap == "") {
			xArray = data.data;
			for(var j=0;j<data.list.length;j++){
				var seriesItem = {
						name : data.list[j].name,
						type : 'bar',
						stack : this.options.stack,
						label : {
							normal : {
								show : this.options.showSeriesLable,
								position : this.options.seriesLablePosition,
								formatter : this.options.seriesLableFormatter,
							}
						},
						barGap : this.options.barGap, 
						data : data.list[j].data,
						markLine: {
							silent: false,
				            data: this.options.markLine_data,
				            label: {
				              normal: {
				            	position: this.options.markLine_position,
				                formatter: this.options.markLine_formatter,
				              },
				            },
				            lineStyle: {
				              normal: {
				                type: this.options.markLine_type,
				                color: this.options.markLine_color,
				              },
				            },
				        },
				};
				seriesInfo.push(seriesItem);
			}
		}else{
			if (this.options.xData == null || this.options.xData == "") {
				alert("X轴显示列未指定！");
				return false;
			}
			
			// 遍历json对象的每个key/value对
			var keySet = this.options.legendMap.keySet();
			var values = this.options.legendMap.values();
			for ( var i in keySet) {
				xArray = new Array();
				dataArray = new Array();
				legendArray.push(keySet[i]);
				for (var d=0;d<data.data.length;d++){
					var val = data.data[d][values[i]];
//					dataArray.push(val);
//					dataArray.push(data.data[d]);
					xArray.push(data.data[d][this.options.xData]);
					dataArray.push({
						name : data.data[d][this.options.xData],
						value : val,
						rowdate : data.data[d]
					});
				}
				var seriesItem = {
						name : keySet[i],
						type : 'bar',
						stack : this.options.stack,
						label : {
							normal : {
								show : this.options.showSeriesLable,
								position : this.options.seriesLablePosition,
								formatter : this.options.seriesLableFormatter,
							}
						},
						barGap : this.options.barGap,
						data : dataArray,
						markLine: {
							silent: false,
				            data: this.options.markLine_data,
				            label: {
				              normal: {
				            	position: this.options.markLine_position,
				                formatter: this.options.markLine_formatter,
				              },
				            },
				            lineStyle: {
				              normal: {
				                type: this.options.markLine_type,
				                color: this.options.markLine_color,
				              },
				            },
				        },
				};
				seriesInfo.push(seriesItem);
			}
		}
		
		
		var option = {
			title : {
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
				data : this.options.legend_data,//legendArray,
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
				top : this.options.legend_top,
				selected : this.options.legend_selected,
				selectedMode : this.options.legend_selectedMode,
				type: this.options.legend_type,
    			align: this.options.legend_align,
    			left: this.options.legend_left,
    			right: this.options.legend_right,
				textStyle : {
					color : this.options.legend_color
				// 图例文字颜色
				}
			},
			grid : {
				top : this.options.gridTop,
				left : this.options.gridLeft,
				right : this.options.gridRight,
				bottom : this.options.gridBottom,
				containLabel : this.options.gridContainLabel
			},
			xAxis : [ {
				name : this.options.xName,
				type : this.options.xType,
				axisLabel: {
                    show: true,
                    textStyle: {
                        color: this.options.xAxis_fontcolor
                    }
                },
                axisLine:{
                    lineStyle:{
                        color:this.options.xaxisLine_color
                    }
                },
                splitLine: {
                    show: false, 
                    lineStyle: {
                        color: this.options.xsplitLine_color
                    }                            
            },
				data : xArray 
			} ],
			yAxis : [ {
				name : this.options.yName,
				type : this.options.yType,
				axisLabel : {
                    textStyle: {
                        color: this.options.yAxis_fontcolor
                    }
                },
                axisLine:{
                    lineStyle:{
                        color:this.options.yaxisLine_color
                    }
                },
                splitLine: {
                	show: true, 
                	lineStyle: {
                    color: this.options.ysplitLine_color
                }                            
                }

			} ],
			dataZoom:[{
			　　　　type : this.options.dataZoom_type,       
			      disabled : this.options.dataZoom_disabled
			　　　}],
			toolbox : {
				show : true,
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
					restore : '还原',
					saveAsImage : '保存为图片'
				}
			},
			calculable : true,
			series : seriesInfo,
			color : this.options.bar_color //传递颜色数组，自定义颜色
		}
		var isspBarChart = echarts.init(this.$element[0], this.options.themeStyle);
		isspBarChart.setOption(option);
		$(window).resize(function() {
			isspBarChart.resize();
		});
		//点击图形触发
		var callBacks = this.options.callBacks;
		isspBarChart.on('click', function (params) {
			
			callBacks(params);
		});
		return isspBarChart;
	}

	$.fn.isspBar = function(options) {
		var bar = new barChart(this, options);
		bar.initBar(function(data) {
			bar.buildBarChart(data);
		});
	}
	
	$.fn.setBarData = function(options,data) {
		var bar = new barChart(this, options);
		return bar.buildBarChart(data);
	}
	


})(jQuery, window, document);