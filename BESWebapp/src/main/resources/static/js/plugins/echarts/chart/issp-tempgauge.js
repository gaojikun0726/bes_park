;(function($, window, document, undefined) {
	var webroot = getRootPath();
	var barChart = function(ele, opt) {
		this.$element = ele, 
		this.defaults = {
			'themeStyle' : 'default', //内置三种主题颜色，默认default，可选为：'dark'¦'light',整体风格设置后，也可单独对 backgroundColor等属性修改
			'ajaxUrl' : null,
			'title' : '',// 标题
			'title_x' : 'center',// 标题水平安放位置，默认为左对齐，可选为：center' ¦ 'left' ¦ 'right'¦ {number}（x坐标，单位px）
			'title_y' : '145',// 垂直安放位置，默认为全图顶端，可选为：垂直安放位置，默认为全图顶端，可选为：¦ {number}（y坐标，单位px）
			'title_textAlign' : null,// 水平对齐方式，默认根据x设置自动调整
			'title_backgroundColor' : 'rgba(0,0,0,0)',
			'title_borderColor' : '#ccc',// 标题边框颜色
			'title_borderWidth' : 0,// 标题边框线宽，单位px，默认为0（无边框）
			'title_padding' : 10,// 标题内边距，单位px，默认各方向内边距为5， 接受数组分别设定上右下左边距，同css
			'title_itemGap' : 10,// 主副标题纵向间隔，单位px，默认为10，
			'title_fontSize' : 13,
			'title_fontWeight' : '',//bolder
			'title_color' : '#8fe3f7',
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
			'gridTop' : '5%', //距离容器上侧的距离
			'gridLeft' : '3%',//
			'gridRight' : '4%',//
			'gridBottom' : '3%',//
			'gridContainLabel' : true,//
			'showSeriesLable' : true,// 是否显示seriesItemLable 即图形上的数据
			'seriesLablePosition' : 'top',// seriesItemLable位置
			'trigger' : 'item', // 当trigger为’item’时只会显示该点的数据，为’axis’时显示该列下所有坐标轴所对应的数据(为axis时formatter设为'')。
			'formatter' :'',// {a} <br/>{b} : {c}
			'axisPointer' : 'line', // 坐标轴指示器，坐标轴触发有效, 默认为直线，可选为：'line' | 'shadow'
			'legendMap' : null,
			'callBacks' : '', //传递自定义的图形点击事件
		// 图例显示列Map key显示名称 value显示数据列名称
			
			
			'bar_barWidth' : 6,//柱形宽度
			'bar_color' : '#fd4d49',//柱形颜色
			'bar_barBorderRadius' : 0,//柱形圆角半径
			'bar_textStylecolor' : '#67e0e3',//提示字体颜色
			'bar_textStylefontSize' : '12',//提示字体大小
			'label_position' : 'insideBottomRight',//['-80%', '130%'],'right'[15 , 0]//提示标签的位置
			'label_distance' : -22, //标签距离图形元素的距离。当 position 为字符描述值（如 'top'、'insideRight'）时候有效。
			'label_offset' : [15 , -60],//是否对文字进行偏移。默认不偏移。例如：[30, 40] 表示文字在横向上偏移 30，纵向上偏移 40。
			'Wframe_barWidth' : 10,//白框宽度
			'Wframe_color' : '#ffffff',//白框颜色
			'Wframe_barBorderRadius' : 30,//白框圆角半径
			'frame_barWidth' : 18,//外框宽度
			'frame_color' : '#fd4d49',//外框颜色
			'frame_barBorderRadius' : 25,//外框圆角半径
			'outsymbolSize' : 32, //温度计外圆大小
			'outcolor' : '#fd4d49',//外圆颜色
			'whitesymbolSize' : 22,//白圆大小
			'whitecolor' : '#ffffff',//白圆颜色
			'innersymbolSize' : 12,//内圆大小
			'innercolor' : '#fd4d49',//内圆颜色
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
//		if (this.options.xData == null || this.options.xData == "") {
//			alert("X轴显示列未指定！");
//			return false;
//		}
		if (this.options.legendMap == null || this.options.legendMap == "") {
			alert("显示数据列未指定！");
			return false;
		}
		var xArray = null;
		var legendArray = new Array();
		var dataArray = null;
		var seriesInfo = new Array();
		// 遍历json对象的每个key/value对
		var keySet = this.options.legendMap.keySet();
		var values = this.options.legendMap.values();
		var kd = [];
		// 刻度使用柱状图模拟，短设置3，长的设置5；构造一个数据
		for (var i = 0, len = 130; i <= len; i++) {
		    if (i > 100 || i < 30) {
		        kd.push('0')
		    } else {
		        if (i % 5 === 0) {
		            kd.push('5');
		        } else {
		            kd.push('3');
		        }
		    }

		}
		for ( var i in keySet) {
			xArray = new Array();
			legendArray.push(keySet[i]);
			for (var d=0;d<data.data.length;d++){
				dataArray = data.data[d][values[i]];
				xArray.push(data.data[d][this.options.xData]);
			}
		}
		function getData(dataArray) {
		    return [parseInt(dataArray) + 5];
		}
		var dataInner = getData(dataArray);
			var seriesItem = {
		        name: '条',
		        type: 'bar',
		        // 对应上面XAxis的第一个对象配置
		        xAxisIndex: 0,
		        data: dataInner,
		        barWidth: this.options.bar_barWidth,
		        itemStyle: {
		            normal: {
		                color: this.options.bar_color,
		                barBorderRadius: this.options.bar_barBorderRadius,
		            }
		        },
		        label: {
		            normal: {
		                show: true,
		                position: this.options.label_position,
		                distance: this.options.label_distance,
		                offset: this.options.label_offset,
		                formatter: function(param) {
		                    // 因为柱状初始化为0，温度存在负值，所以，原本的0-100，改为0-130，0-30用于表示负值
		                    return param.value -5  + '°C';
		                },
		                textStyle: {
		                    color: this.options.bar_textStylecolor,
		                    fontSize: this.options.bar_textStylefontSize,
		                }
		            }
		        },
		        z: 2
		    };
			var seriesItem1 = {
		        name: '白框',
		        type: 'bar',
		        xAxisIndex: 1,
		        barGap: '-100%',
		        data: [129],
		        barWidth:  this.options.Wframe_barWidth,
		        itemStyle: {
		            normal: {
		                color: this.options.Wframe_color,
		                barBorderRadius: this.options.Wframe_barBorderRadius,
		            }
		        },
		        z: 1
		    };
			var seriesItem2 = {
		        name: '外框',
		        type: 'bar',
		        xAxisIndex: 2,
		        barGap: '-100%',
		        data: [130],
		        barWidth: this.options.frame_barWidth,
		        itemStyle: {
		            normal: {
		                color: this.options.frame_color,
		                barBorderRadius: this.options.frame_barBorderRadius,
		            }
		        },
		        z: 0
		    };
			var seriesItem3 = {
		        name: '圆',
		        type: 'scatter',
		        hoverAnimation: false,
		        data: [0],
		        xAxisIndex: 0,
		        symbolSize: this.options.innersymbolSize,
		        itemStyle: {
		            normal: {
		                color: this.options.innercolor,
		                opacity: 1,
		            }
		        },
		        z: 2
		    };
			var seriesItem4 = {
		        name: '白圆',
		        type: 'scatter',
		        hoverAnimation: false,
		        data: [0],
		        xAxisIndex: 1,
		        symbolSize: this.options.whitesymbolSize,
		        itemStyle: {
		            normal: {
		                color: this.options.whitecolor,
		                opacity: 1,
		            }
		        },
		        z: 1
		    };
			var seriesItem5 = {
		        name: '外圆',
		        type: 'scatter',
		        hoverAnimation: false,
		        data: [0],
		        xAxisIndex: 2,
		        symbolSize: this.options.outsymbolSize,
		        itemStyle: {
		            normal: {
		                color: this.options.outcolor,
		                opacity: 1,
		            }
		        },
		        z: 0
		    };
			var seriesItem6 = {
		        name: '刻度',
		        type: 'bar',
		        yAxisIndex: 1,
		        xAxisIndex: 3,
		        label: {
		            normal: {
		                show: true,
		                position: 'right',
		                distance: 5,
		                color: '#525252',
		                fontSize: 10,
		                formatter: function(params) {
		                    // 因为柱状初始化为0，温度存在负值，所以，原本的0-100，改为0-130，0-30用于表示负值
		                    if (params.dataIndex > 100 || params.dataIndex < 30) {
		                        return '';
		                    } else {
		                        if (params.dataIndex % 5 === 0) {
		                            return params.dataIndex - 30;
		                        } else {
		                            return '';
		                        }
		                    }
		                }
		            }
		        },
		        barGap: '-100%',
		        data: kd,
		        barWidth: 1,
		        itemStyle: {
		            normal: {
		                color: '#fd4d49',
		                barBorderRadius: 10,
		            }
		        },
		        z: 0
		    };
			seriesInfo.push(seriesItem);
			seriesInfo.push(seriesItem1);
			seriesInfo.push(seriesItem2);
			seriesInfo.push(seriesItem3);
			seriesInfo.push(seriesItem4);
			seriesInfo.push(seriesItem5);
			//seriesInfo.push(seriesItem6);
		
		var option = {
			    title: {
			        show: true,
			        text: this.options.title,
			        x : this.options.title_x,
			        y : this.options.title_y,
					textStyle : {
						fontSize : this.options.title_fontSize,
						fontWeight : this.options.title_fontWeight,
						color : this.options.title_color,// 主标题文字颜色
					},
			    },
			    yAxis: [{
			        show: false,
			        min: -30,
			        max: 130,
			    }, {
			        show: false,
			        data: [],
			        min: 0,
			        max: 130,
			    }],
			    xAxis: [{
			        show: false,
			        data: []
			    }, {
			        show: false,
			        data: []
			    }, {
			        show: false,
			        data: []
			    }, {
			        show: false,
			        min: -110,
			        max: 100,

			    }],
			    grid : {
					top : this.options.gridTop,
					left : this.options.gridLeft,
					right : this.options.gridRight,
					bottom : this.options.gridBottom,
					containLabel : this.options.gridContainLabel
				},
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
			var aa = callBacks(params);
		});
	}

	$.fn.isspBar = function(options) {
		var bar = new barChart(this, options);
		bar.initBar(function(data) {
			bar.buildBarChart(data);
		});
	}
	
	$.fn.settempgaugeData = function(options,data) {
		var bar = new barChart(this, options);
		bar.buildBarChart(data);
	}
	


})(jQuery, window, document);