;(function($, window, document, undefined) {
	var webroot = getRootPath();

	var lineChart = function(ele, opt) {
		this.$element = ele, 
		this.defaults = {
			'themeStyle' : 'light', //内置两种主题颜色，默认dark，可选为：'dark'¦'light',整体风格设置后，也可单独对 backgroundColor等属性修改
			'ajaxUrl' : null,
            'splitNumber' : '',
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
			'subtext_fontSize' : '14',//副标题文字大小

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
			'legend_type' : 'scroll',//图例类型，'plain'：普通图例。缺省就是普通图例。'scroll'：可滚动翻页的图例。
			'legend_align' : 'left',
			'legend_left' : '10%',
			'legend_right' : '',


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
			'stack' : '',// 堆叠显示
			'symbol' : 'roundRect', //拐点与legend图例的 图形样式 'circle', 'rect', 'roundRect', 'triangle', 'diamond', 'pin', 'arrow', 'none'
			'xName' : '',// X轴名称
			'xType' : 'category',// X轴类型
			'xData' : '',// X轴显示数据列
			'xAxis_fontcolor' : '#fefefe',//x轴字体颜色
			'xaxisLine_color' : 'rgb(58,123,204,0.2)',//x轴线条颜色
			'xsplitLine_color' : 'rgb(58,123,204,0.2)',//x轴网格线颜色
			'xAxis_interval' : '',//强制设置坐标轴分割间隔
			'showMinLabel' : true,//是否显示最小 tick 的 label
			'showMaxLabel' : true,//是否显示最大 tick 的 label


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
			'showSeriesLable' : true,// 是否显示seriesItemLable
			'seriesLablePosition' : 'top',// seriesItemLable位置
			'trigger' : 'axis', // 当trigger为’item’时只会显示该点的数据，为’axis’时显示该列下所有坐标轴所对应的数据。
			'axisPointer' : 'line', // 坐标轴指示器，坐标轴触发有效, 默认为直线，可选为：'line' | 'shadow'
			'legendMap' : null,
			'areaStyle' : '{}', //分隔区域的样式设置，即折线下的阴影区域。
			'boundaryGap' : '',  //坐标轴两边留白策略，类目轴和非类目轴的设置和表现不一样。类目轴中 boundaryGap 可以配置为 true 和 false。默认为 true
		// 图例显示列Map key显示名称 value显示数据列名称
            'minInterval':'',//坐标轴分割刻度显示控制
			
			'markLine_data' : '', //标线数据，格式：[{yAxis: '0.7'},{yAxis: '0.5'}],
			'markLine_formatter' : null,//标线的标签内容，为空时默认写入markLine_data
			'markLine_position' : null,//标线的标签位置，默认线的end，可选：'start' 线的起始点，	'middle' 线的中点。		
			'markLine_type' : 'dashed',//标线类型，default: solid,可选'dashed''dotted'
			'markLine_color' : '',//标线颜色
			
			
			'callBacks' : '', //传递自定义的图形点击事件
			'line_color' : null, //折线图线条颜色 ,['','','','']
			'smooth' : true,
			'foldpoint_Color' : null //折线拐点颜色
		}, 
		this.options = $.extend({}, this.defaults, opt)
	}

	// 定义方法
	lineChart.prototype.initLine = function(callBack) {
		$.ajax({
			url : webroot + this.options.ajaxUrl,
			type : "post",
			success : function(data) {
				callBack(data);
			}
		});
	}
	
	lineChart.prototype.buildLineChart = function buildLineChart(data) {
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
						type : 'line',
						symbol: this.options.symbol,
						stack : this.options.stack,
						smooth : this.options.smooth,
						barWidth : this.options.barWidth,

						label : {
							normal : {
								show : this.options.showSeriesLable,
								position : this.options.seriesLablePosition
							}
						},
						itemStyle : {  
	                        normal : {  
	                            color:this.options.foldpoint_Color 
	                        }  
	                    },  
						data : data.list[j].data,
						areaStyle : this.options.areaStyle,
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
				dataArray.push(val);
				xArray.push(data.data[d][this.options.xData]);
			}
			var seriesItem = {
					name : keySet[i],
					type : 'line',
					symbol: this.options.symbol,
					stack : this.options.stack,
					smooth : this.options.smooth,
					barWidth : this.options.barWidth,
					label : {
						normal : {
							show : this.options.showSeriesLable,
							position : this.options.seriesLablePosition
						}
					},
					itemStyle : {  
                        normal : {  
                            color:this.options.foldpoint_Color 
                        }  
                    },  
					data : dataArray,
					areaStyle : this.options.areaStyle,
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
					color : this.options.subtext_color,
					fontSize : this.options.subtext_fontSize,
				},
				subtext : this.options.subtext,
				sublink : this.options.sublink
			},
			tooltip : {
				trigger : this.options.trigger,
				axisPointer : {
					type : this.options.axisPointer
				},
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
                    boundaryGap : this.options.boundaryGap,
                    splitNumber:this.options.splitNumber,
                    axisLabel: {
                        show: true,
                        interval: this.options.xAxis_interval,
                        showMinLabel: this.options.showMinLabel,
                        showMaxLabel: this.options.showMaxLabel,
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
                minInterval:this.options.minInterval,
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
                },

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
			},
			calculable : true,
			series : seriesInfo,
			color : this.options.line_color  //传递颜色数组，自定义颜色
		}
		var isspLineChart = echarts.init(this.$element[0], this.options.themeStyle);
		isspLineChart.setOption(option);
		$(window).resize(function() {
			isspLineChart.resize();
		});

		//点击图形触发
		var callBacks = this.options.callBacks;
		isspLineChart.on('click', function (params) {
			var aa = callBacks(params);
		});
		return isspLineChart;
	}

	$.fn.isspLine = function(options) {
		var line = new lineChart(this, options);
		line.initLine(function(data) {
			return	line.buildLineChart(data);
		});
	}
	
	$.fn.setLineData = function(options,data) {
		var line = new lineChart(this, options);
		return line.buildLineChart(data); 
	}

})(jQuery, window, document);