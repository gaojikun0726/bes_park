var map;

/**
 * 获取园区所有基本信息并回调
 * @param callback
 */
function getAllBESPark( callback ) {

    var webroot = getRootPath();

    $.issp_ajax({
        type: 'get',
        url: webroot + '/view/park/findAllBESPark',
        dataType: "json",
        async: true,
        success: function( result ) {
            if( typeof callback === 'function' ){
                callback( result );
            }else {
            	console.warn( '不是 “function” 类型！' );
			}
        }
    });
}

function initBaiduMap(){
	// 百度地图API功能
  map = new BMap.Map("allmap", {enableMapClick: false,mapType:BMAP_NORMAL_MAP});    // 创建Map实例
  var point = new BMap.Point(117.212726,36.651814);//智能交通产业园
  map.centerAndZoom(point, 18);  // 初始化地图,设置中心点坐标和地图级别
  //添加地图类型控件
  map.addControl(new BMap.MapTypeControl({
    mapTypes: [
      BMAP_NORMAL_MAP,
      BMAP_HYBRID_MAP
    ]
  }));
  map.setCurrentCity("济南");          // 设置地图显示的城市 此项是必须设置的
  map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
  map.addControl(new BMap.NavigationControl());
	map.addControl(new BMap.MapTypeControl({mapTypes: [BMAP_NORMAL_MAP,BMAP_HYBRID_MAP]}));
  // map.setMapStyle({style: 'midnight'});  //dark  midnight //设置地图风格
	// map.setMapStyle({style:'midnight'});  //dark  midnight //设置地图风格
 getBoundary("山东省");
// map.setMapType
    getAllBESPark( function ( result ) {

    	var list = result.list;

    	if( !Array.isArray( list ) ){
    		console.warn( '没有有效数据！' );
    		return false;
		}

        var marker = '';
        //循环建立标注点
        for ( var i = 0; i < list.length; i++ ) {

        	var lon = list[i].f_longitude;
        	var lat = list[i].f_latitude;

        	if( lon == undefined || lat == undefined ){
        		continue;
			}

            var point = new BMap.Point( lon, lat ); //将标注点转化成地图上的点
            marker = new BMap.Marker( point ); //将点转化成标注点
            marker.id = list[i].f_yqbh;
						marker.mc = list[i].f_yqmc;
            // marker.setTitle( list[i].f_yqmc );


			map.addOverlay(marker); //将标注点添加到地图上
			//添加鼠标滑过时打开自定义信息窗口事件
			marker.addEventListener("mouseover",function (e) {
					this.openInfoWindow(setInfoWindow(this.mc));
				}
			);

			//添加鼠标离开时关闭自定义信息窗口事件
			marker.addEventListener("mouseout",function (e) {
					this.closeInfoWindow();
				}
			);
            marker.addEventListener( 'click', function( e ) {
                newmain_index.zzjg_tree( this.id );
            });
        }
        });
  function getBoundary(name) {
    var bdary = new BMap.Boundary();
    bdary.get(name, function (rs) {       //获取行政区域
      //map.clearOverlays();        //清除地图覆盖物
      var count = rs.boundaries.length; //行政区域的点有多少个
      for (var i = 0; i < count; i++) {
        var ply = new BMap.Polygon(rs.boundaries[i], {strokeWeight: 2, strokeColor: "#EFF355", fillColor: ""}); //建立多边形覆盖物
        map.addOverlay(ply);  //添加覆盖物
        //map.setViewport(ply.getPath());    //调整视野
      }
    });
  }
//	map.addOverlay(marker);               // 将标注添加到地图中
//	var local = new BMap.LocalSearch(map, {
//		renderOptions:{map: map}
//	});
////	local.search("天辰路2177号齐鲁交通产业园2号楼1908室");
//	var sContent = "<div><h4 style='margin:0 0 5px 0;padding:0.2em 0'>联合财富广场</h4>" + "<img style='float:right;margin:4px' id='imgDemo' src='static/images/lhcwgc.jpg' width='139' height='104' title='联合财富广场'/>" + "<p style='margin:0;line-height:1.5;font-size:13px;text-indent:2em'>济南市舜华路与天辰大街交叉口西（高新区行政审批中心西南）...</p>" + "</div>";
//	var opts1 = {
//	    width: 400,
//	    height:100
//	};
//	var infoWindow = new BMap.InfoWindow(sContent, opts1); // 创建信息窗口对象
//	marker.addEventListener("click",function() {
//	    this.openInfoWindow(infoWindow);
//	    //图片加载完毕重绘infowindow
//	    document.getElementById('imgDemo').onload = function() {
//	        infoWindow.redraw(); //防止在网速较慢，图片未加载时，生成的信息框高度比图片的总高度小，导致图片部分被隐藏
//	    }
//	});
//	marker.openInfoWindow(infoWindow);
	//marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画

}
//设置悬浮框
function setInfoWindow(yqmc){
	var steelContent = '<div></div>';
	var steelOpts = {
		width : 0,     //信息窗口宽度
		height: 0,     //信息窗口高度
		title : yqmc , //信息窗口标题
		enableMessage:true	//设置允许信息窗发送短息
	};
	return infoWindow = new BMap.InfoWindow("", steelOpts); // 创建信息窗口对象
}
/** *****************************eCharts数据源******************************************* */

function getRandomArr(s, e, len) {
	var rand;
	var chartArr = new Array(len)
	for (var i = 0; i < len; i++) {
		rand = parseInt(Math.random() * (s - e + 1) + e);
		chartArr[i] = rand;
	}
	return chartArr;
}

function today_tendency(nymc,nydw,fCjsj,fData) {
	var dom = document.getElementById("today_tendency");
	var myChartTop = echarts.init(dom, 'light');
	var option = {
		color:['#5ed5fd'],
		    title: {
		        text: "今日用"+nymc,
		        subtext: "",
		        textStyle: {
		        	fontSize: '15',
					color : chartFont.color || '#fff',
		        }
		    },
		    tooltip: {
		        trigger: "axis"
		    },
		    legend: {
		    	textStyle : {
					fontSize : 11,
					color : chartFont.color || '#fff',
				},
		        data: [nymc],
				x:"right"
		    },
		    toolbox: {
		        show: false,
		        feature: {
		            mark: {
		                show: true
		            },
		            dataView: {
		                show: true,
		                readOnly: true
		            },
		            magicType: {
		                show: false,
		                type: ["line", "bar", "stack", "tiled"]
		            },
		            restore: {
		                show: true
		            },
		            saveAsImage: {
		                show: true
		            }
		        }
		    },
		    calculable: true,
		    grid : {
				height: '60%',
				left:30,
				right:"4%",
				// left : '13%',
				// right : '9%',
				bottom : '3%',
				containLabel : true
			},
		    xAxis: [
		        {
		            type: "category",
		            boundaryGap: false,
		            axisLine : {
						lineStyle : {
							color : '#0177d4'
						}
					},
			        axisLabel: {
	                    show: true,
	                    textStyle: {
							color : chartFont.color || '#fff',
							fontSize:11
	                    }
	                },
		            data : fCjsj,
					nameLocation: "end"
		        }
		    ],
		    yAxis: [
		        {
		            type: "value",
		            name: "单位："+nydw,
		            nameTextStyle : {
						color : chartFont.color || '#fff',
			        },
		            axisLine : {
						lineStyle : {
							color : '#0177d4'
						}
					},
			        axisLabel: {
	                    show: true,
	                    textStyle: {
							fontSize:10,
							color : chartFont.color || '#fff',
	                    }
	                },
					splitLine: {
					    lineStyle: {
					        //分割线颜色
					        color: ['rgba(40, 76, 117, 0.9)']
					    }
					},
		            nameLocation: "end"
		        }
		    ],
		    series: [
		        {
		            type: "line",
		            itemStyle: {
		                normal: {
		                    areaStyle: {
		                        type: "default"
		                    }
		                }
		            },
		            name: nymc,
		            data : fData,
		            smooth: true
		        }
		    ]
		};
	if (option && typeof option === "object") {
		myChartTop.setOption(option, true);
	}
	$(window).resize(function(){
		myChartTop.resize();
	});
}

function addMarker(x, y, index,content){
	if(content == undefined){
		content="";
	}
	var point = new BMap.Point(x, y);// 创建图标对象
	map.centerAndZoom(point, 12);
	//map.panTo(point);
    var myIcon = new BMap.Icon("static/images/flag_mark.gif", new BMap.Size(25,25), {
        // 指定定位位置。
        // 当标注显示在地图上时，其所指向的地理位置距离图标左上 角各偏移10像素和25像素。
        anchor: new BMap.Size(10, 25),
        // 设置图片偏移。
        // 当您需要从一幅较大的图片中截取某部分作为标注图标时，您
        // 需要指定大图的偏移位置，此做法与css sprites技术类似。
        imageOffset: new BMap.Size(0, 0 - index * 25)   // 设置图片偏移
    });
    // 创建标注对象并添加到地图
    var marker = new BMap.Marker(point, {icon: myIcon});
    //marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
    map.addOverlay(marker);

	var label = new BMap.Label(content,{offset:new BMap.Size(-20,-20)});
	label.setStyle({
		color : "red",
		fontSize : "13px",
		backgroundColor :"0.05",
		border :"0"
	});
	marker.setLabel(label);

    addClickHandler("",marker); //增加点击事件

	/*var point = new BMap.Point(116.404, 39.915);
	map.centerAndZoom(point, 15);
	var marker = new BMap.Marker(point);  // 创建标注
	map.addOverlay(marker);               // 将标注添加到地图中
	marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
*/}

function addClickHandler(sContent,marker){
    marker.addEventListener("click",function(e){
        alert("报警信息！！");
    });
}

function openInfo(sContent,e){
    var p = e.target;
    var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
    var infoWindow = new BMap.InfoWindow(sContent);  // 创建信息窗口对象
    map.openInfoWindow(infoWindow,point); //开启信息窗口
}

function getDate(days){
	var date = new Date();
	date.setTime(date.getTime()-days*24*60*60*1000);
	var nowDay = date.getDate().toString();
	return nowDay;
}

//newmain-index
function gzt_rightSecond(nymc,nydw,fhlx,Todaylegend,TodayYAxisData,TodaySeriesData) {
	var dom = document.getElementById("gzt_rightSecond");
	var myChartTwo = echarts.init(dom, 'light');
	var option_inter = {
		color:['#4fca6a'],
			 title: {
			        text: '今日用'+nymc+'排行',
			        textStyle : {
						color : chartFont.color || '#fff',
		    			fontSize: '15'
		    		},
			    },
			    tooltip: {
			        trigger: 'axis',
			        /*axisPointer: {
			        	type: 'cross',
		                label: {
		                    backgroundColor: '#283b56'
		                }
			        }*/
			    },
			    legend: {
			        data: Todaylegend,
			        x : 'right',
		    		textStyle : {
						color : chartFont.color || '#fff',
						fontSize:11
		    		}
			    },
			    grid: {
					left: 30,
					right: '4%',
			        /*left: '-10%',
			        right: '15%',*/
			        bottom: '3%',
			        containLabel: true
			    },
			    dataZoom: {
	                show: false,
	                start: 0,
	                end: 100
	            },
			    xAxis: {
			        type: 'value',
                    name: nydw,
			        axisLine : {
						lineStyle : {
							color : '#0177d4'
						}
					},
					splitLine: {
					    lineStyle: {
					        //分割线颜色
					        color: ['rgba(40, 76, 117, 0.9)']
					    }
					},
			        axisLabel: {
	                    show: true,
	                    textStyle: {
							color: chartFont.color || '#fff',
	                        fontSize : 11
	                    }
	                }
			    },
			    yAxis: {
			        type: 'category',
			        data: TodayYAxisData,
			        minInterval:'0',
			        scale: true,
                    name: '单位：'+fhlx,
                    nameTextStyle : {
						color: chartFont.color ||  '#fff'
			        },
                    axisLine : {
						lineStyle : {
							color : '#0177d4'
						}
					},
					splitLine: {
					    lineStyle: {
					        //分割线颜色
					        color: ['rgba(40, 76, 117, 0.9)']
					    }
					},
			        axisLabel: {
	                    show: true,
	                    interval:'auto',
	                    textStyle: {
							color: chartFont.color || '#fff'
	                    },
						formatter: function(TodayYAxisData) {
							if (TodayYAxisData.length > 5) {
								return TodayYAxisData.substring(0, 5) + "...";
							} else {
								return TodayYAxisData;
							}
						}
	                },
                    min: 0,
			    },
			    series: [
			        {
			            name: Todaylegend,
			            type: 'bar',
			            data: TodaySeriesData,
						// barGap:'10',//柱图间距
			        }
			    ]
        }
	//先展示 后填充数据
	myChartTwo.setOption(option_inter);
	$(window).resize(function(){
		myChartTwo.resize();
	});
	};

function gzt_rightThird(nymc,nydw,legend,seriesData,timeData) {
	var dom = document.getElementById("gzt_rightThird");
	var myChartThree = echarts.init(dom, 'light');

	var option_inter = {
		color:['#04e2be','#ffc44e'],
			 title : {
		    		text : "今日同比("+nymc+")",
		    		textStyle : {
						color : chartFont.color || '#fff',
		    			fontSize: '15'
		    		},
		    	},
			tooltip : {
		        trigger: 'axis',
		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
		        }
		    },
		    legend: {
		    	data:legend,
			    x : 'right',
	    		textStyle : {
					color : chartFont.color || '#fff',
					fontSize:11
	    		}
		    },
		    grid: {
				height: '60%',
			 	left:30,
		        // left: '13%',
		        right: '4%',
		        bottom: '0',
		        containLabel: true
		    },
		    xAxis : [
		        {
		            type : 'category',
		            data:timeData,
		            axisLine : {
						lineStyle : {
							color : '#0177d4'
						}
					},
			        axisLabel: {
	                    show: true,
	                    textStyle: {
							color : chartFont.color || '#fff',
							fontSize:11
	                    }
	                }
		        }
		    ],
		    yAxis : [
		        {
		        	name: '单位：'+nydw,
		        	nameTextStyle : {
						color : chartFont.color || '#fff',
			        },
		            type : 'value',
		            axisLine : {
						lineStyle : {
							color : '#0177d4'
						}
					},
					splitLine: {
					    lineStyle: {
					        //分割线颜色
					        color: ['rgba(40, 76, 117, 0.9)']
					    }
					},
			        axisLabel: {
	                    show: true,
	                    textStyle: {
							fontSize:10,
							color : chartFont.color || '#fff',
	                    }
	                }
		        }
		    ],
		    series : seriesData
	        }
	//先展示 后填充数据
	myChartThree.setOption(option_inter);
	$(window).resize(function(){
		myChartThree.resize();
	});
	};

function gzt_rightFour(nymc,nydw,legend,seriesData,timeData) {
	var dom = document.getElementById("gzt_rightFour");
	var myChartFour = echarts.init(dom, 'light');
	var option_inter = {
		color:['#5bd375','#feb45c'],
			 title : {
		    		text : '今日环比('+nymc+')',
		    		textStyle : {
						color : chartFont.color || '#fff',
		    			fontSize: '15'
		    		},
		    	},
			tooltip : {
		        trigger: 'axis',
		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
		        }
		    },
		    legend: {
		    	data:legend,
			    x : 'right',
	    		textStyle : {
					color : chartFont.color || '#fff',
					fontSize:11
	    		}
		    },
		    grid: {
				height: '60%',
				left:30,
		        // left: '13%',
		        right: '4%',
		        bottom: '0',
		        containLabel: true
		    },
		    xAxis : [
		        {
		            type : 'category',
		            data:timeData,
		            axisLine : {
						lineStyle : {
							color : '#0177d4'
						}
					},
			        axisLabel: {
	                    show: true,
	                    textStyle: {
							color : chartFont.color || '#fff',
	                    }
	                }
		        }
		    ],
		    yAxis : [
		        {
		        	name: '单位：'+nydw,
		        	nameTextStyle : {
						color : chartFont.color || '#fff',
						fontSize:11
			        },
		            type : 'value',
		            axisLine : {
						lineStyle : {
							color : '#0177d4'
						}
					},
					splitLine: {
					    lineStyle: {
					        //分割线颜色
					        color: ['rgba(40, 76, 117, 0.9)']
					    }
					},
			        axisLabel: {
	                    show: true,
	                    textStyle: {
							fontSize:10,
							color : chartFont.color || '#fff',
	                    }
	                }
		        }
		    ],
		    series : seriesData
	        }
	//先展示 后填充数据
	myChartFour.setOption(option_inter);
	$(window).resize(function(){
		myChartFour.resize();
	});
	};

// 本平均能耗 echars
function monthRankingChars(nymc, nydw, fhlx, MonthYAxisData, MonthSeriesData) {
	var dom = document.getElementById("gzt_alarm");
	var myChartalarm = echarts.init(dom, 'light');
	var lightBlue = '#56b5ff';
	var option = {
		color:[lightBlue],
		title: {
			text: '本月人均用' + nymc + '排行',
			textStyle: {
				color : chartFont.color || '#fff',
				fontSize: '15'
			},
		},
		tooltip: {
			trigger: 'axis',
			/*axisPointer: {
				type: 'cross',
				label: {
					backgroundColor: '#283b56'
				}
			}*/
		},
		legend: {
			show:false,
			data: "",
			// x : 'right',
			right: "7",
			textStyle: {
				color : chartFont.color || '#fff',
			}
		},
		grid: {
			left: 10,
			right: 40,
			bottom: '5%',
			containLabel: true
		},
		dataZoom: {
			show: false,
			start: 0,
			end: 100
		},
		xAxis: {
			type: 'value',
			name: nydw,
			interval: 'auto',
			axisLine: {
				/*lineStyle: {
					color: '#0177d4'
				}*/
			},
			splitLine: {
				lineStyle: {
					//分割线颜色
					color: ['rgba(40, 76, 117, 0.9)']
				}
			},
			axisLabel: {
				show: true,
				textStyle: {
					color : chartFont.color || '#fff',
					fontSize: 11
				},
				formatter: function (MonthSeriesData) {
					if (MonthSeriesData.length > 5) {
						return MonthSeriesData.substring(0, 5) + "...";
					} else {
						return MonthSeriesData;
					}
				},
			},
		},
		yAxis: {
			type: 'category',
			data: MonthYAxisData,
			scale: true,
			interval: '0',
			name: fhlx,
			nameTextStyle: {
				color : chartFont.color || '#fff',
			},
			axisLine: {
				lineStyle: {
					color: '#0177d4'
				}
			},
			axisLabel: {
				show: true,
				textStyle: {
					color : chartFont.color || '#fff',
					fontSize: 11
				},
				formatter: function (MonthYAxisData) {
					if (MonthYAxisData.length > 5) {
						return MonthYAxisData.substring(0, 5) + "...";
					} else {
						return MonthYAxisData;
					}
				},
			},
			min: 0,
		},
		series: [
			{
				barGap: 18,
				// name: "Monthlegend",
				type: 'bar',
				data: MonthSeriesData
			}
		]
	};

	if (option && typeof option === "object") {
		myChartalarm.setOption(option, true);
	}

	$(window).resize(function () {
		myChartalarm.resize();
	});
}

// 本月人均能耗 echars
function monthPerCapitaRankingChars(nymc, nydw, fhlx, MonthYAxisData, MonthSeriesData) {
	var dom = document.getElementById("gzt_bottomTwo");
	var myChartalarm = echarts.init(dom, 'light');
	var option = {
		color:['#3e7deb'],
		title: {
			text: '本月用' + nymc + '排行',
			textStyle: {
				color : chartFont.color || '#fff',
				fontSize: '15'
			},
		},
		tooltip: {
			trigger: 'axis',
			/*axisPointer: {
				type: 'cross',
				label: {
					backgroundColor: '#283b56'
				}
			}*/
		},
		legend: {
			show:false,
			data: "",
			// x : 'right',
			right: "7",
			textStyle: {
				color : chartFont.color || '#fff',
			}
		},
		grid: {
			left: 10,
			right: 40,
			bottom: '5%',
			containLabel: true
		},
		dataZoom: {
			show: false,
			start: 0,
			end: 100
		},
		xAxis: {
			type: 'value',
			name: nydw,
			interval: 'auto',
			axisLine: {
				/*lineStyle: {
					color: '#0177d4'
				}*/
			},
			splitLine: {
				lineStyle: {
					//分割线颜色
					color: ['rgba(40, 76, 117, 0.9)']
				}
			},
			axisLabel: {
				show: true,
				textStyle: {
					color : chartFont.color || '#fff',
					fontSize: 11
				},
				formatter: function (MonthSeriesData) {
					if (MonthSeriesData.length > 5) {
						return MonthSeriesData.substring(0, 5) + "...";
					} else {
						return MonthSeriesData;
					}
				},
			},
		},
		yAxis: {
			type: 'category',
			data: MonthYAxisData,
			scale: true,
			interval: '0',
			name: fhlx,
			nameTextStyle: {
				color : chartFont.color || '#fff',
			},
			axisLine: {
				lineStyle: {
					color: '#0177d4'
				}
			},
			axisLabel: {
				show: true,
				textStyle: {
					color : chartFont.color || '#fff',
					fontSize: 11
				},
				formatter: function (MonthYAxisData) {
					if (MonthYAxisData.length > 5) {
						return MonthYAxisData.substring(0, 5) + "...";
					} else {
						return MonthYAxisData;
					}
				},
			},
			min: 0,
		},
		series: [
			{
				barGap: 18,
				// name: "Monthlegend",
				type: 'bar',
				data: MonthSeriesData
			}
		]
	};

	if (option && typeof option === "object") {
		myChartalarm.setOption(option, true);
	}

	$(window).resize(function () {
		myChartalarm.resize();
	});
}

	//工作台下方echars（未使用）
function gzt_alarm(nymc,nydw,fhlx,Monthlegend,MonthYAxisData,MonthSeriesData) {
	var dom = document.getElementById("gzt_alarm");
	var myChartalarm = echarts.init(dom, 'light');
	var lightBlue = '#56b5ff';
	var option = {
		color:[lightBlue],
		title: {
			text: '本月人均用'+nymc+'排行',
			textStyle : {
				color : chartFont.color || '#fff',
				fontSize: '15'
			},
		},
		tooltip: {
			trigger: 'axis',
			/*axisPointer: {
				type: 'cross',
				label: {
					backgroundColor: '#283b56'
				}
			}*/
		},
		legend: {
			show:false,
			data: Monthlegend,
			// x : 'right',
			right:"7",
			textStyle : {
				color : chartFont.color || '#fff',
			}
		},
		grid: {
			left: 10,
			right: 40,
			bottom: '5%',
			containLabel: true
		},
		dataZoom: {
			show: false,
			start: 0,
			end: 100
		},
		xAxis: {
			type: 'value',
			name: nydw,
			interval : 'auto',
			axisLine : {
				lineStyle : {
					color : '#0177d4'
				}
			},
			splitLine: {
				lineStyle: {
					//分割线颜色
					color: ['rgba(40, 76, 117, 0.9)']
				}
			},
			axisLabel: {
				show: true,
				textStyle: {
					color : chartFont.color || '#fff',
					fontSize:11
				},
				formatter: function(MonthSeriesData) {
					if (MonthSeriesData.length > 5) {
						return MonthSeriesData.substring(0, 5) + "...";
					} else {
						return MonthSeriesData;
					}
				},
			},
		},
		yAxis: {
			type: 'category',
			data: MonthYAxisData,
			scale: true,
			interval:'0',
			name: fhlx,
			nameTextStyle : {
				color : chartFont.color || '#fff',
			},
			axisLine : {
				lineStyle : {
					color : '#0177d4'
				}
			},
			axisLabel: {
				show: true,
				textStyle: {
					color : chartFont.color || '#fff',
					fontSize : 11
				},
				formatter: function(MonthYAxisData) {
					if (MonthYAxisData.length > 5) {
						return MonthYAxisData.substring(0,5) + "...";
					} else {
						return MonthYAxisData;
					}
				},
			},
			min: 0,
		},
		series: [
			{
				barGap:18,
				name: Monthlegend,
				type: 'bar',
				data: MonthSeriesData
			}
		]
	};

	if (option && typeof option === "object") {
		myChartalarm.setOption(option, true);
	}

	$(window).resize(function(){
		myChartalarm.resize();
	});
}


	function gzt_bottomOne(fCjsj,fData,nydw,nymc) {
		var dom = document.getElementById("gzt_bottomOne");
		var myChartbottomOne = echarts.init(dom, "light");
		var option = {
			color:['#ff7683'],
			    title: {
					text: '本月用'+nymc,
			        subtext: "",
			        textStyle: {
			        	fontSize: '15',
						color : chartFont.color || '#fff',
			        }
			    },
			    tooltip: {
			        trigger: "axis"
			    },
			    legend: {
			    	textStyle : {
						fontSize : 11,
						color : chartFont.color || '#fff',
					},
			        data: ["电"],
					x:"right"
			    },
			    toolbox: {
			        show: false,
			        feature: {
			            mark: {
			                show: true
			            },
			            dataView: {
			                show: true,
			                readOnly: true
			            },
			            magicType: {
			                show: false,
			                type: ["line", "bar", "stack", "tiled"]
			            },
			            restore: {
			                show: true
			            },
			            saveAsImage: {
			                show: true
			            }
			        }
			    },
			    calculable: true,
			    grid : {
                    left : 30,
                    right : 10,
					// left : '18%',
					// right : '9%',
					bottom : '5%',
					containLabel : true
				},
			    xAxis: [
			        {
			            type: "category",
			            boundaryGap: true,//类目轴中 boundaryGap 可以配置为 true 和 false。
			            axisLine : {
							lineStyle : {
								color : '#0177d4'
							}
						},
				        axisLabel: {
		                    show: true,
		                    textStyle: {
								color : chartFont.color || '#fff',
								fontSize:11
		                    }
		                },
			            data : fCjsj,
						nameLocation: "end"
			        }
			    ],
			    yAxis: [
			        {
			            type: "value",
						name: '单位：'+nydw,
			            nameTextStyle : {
							color : chartFont.color || '#fff',
				        },
			            axisLine : {
							lineStyle : {
								color : '#0177d4'
							}
						},
				        axisLabel: {
		                    show: true,
		                    textStyle: {
								color : chartFont.color || '#fff',
		                    }
		                },
						splitLine: {
						    lineStyle: {
						        //分割线颜色
						        color: ['rgba(40, 76, 117, 0.9)']
						    }
						},
			            nameLocation: "end"
			        }
			    ],
			    series: [
			        {
			            type: "bar",
			            itemStyle: {
			                normal: {
			                    areaStyle: {
			                        type: "default"
			                    }
			                }
			            },
			            data : fData,
			            smooth: true
			        }
			    ]
		};

		if (option && typeof option === "object") {
			myChartbottomOne.setOption(option, true);
		}
		$(window).resize(function(){
			myChartbottomOne.resize();
		});

	}

	function gzt_bottomTwo(nymc,nydw,fhlx,Monthlegend,MonthYAxisData,MonthSeriesData) {
		var dom = document.getElementById("gzt_bottomTwo");
		var myChartbottomTwo = echarts.init(dom, 'light');
		var option = {
			color:['#3e7deb'],
			 title: {
			        text: '本月用'+nymc+'排行',
			        textStyle : {
						color : chartFont.color || '#fff',
		    			fontSize: '15'
		    		},
			    },
			    tooltip: {
			        trigger: 'axis',
			       /* axisPointer: {
			        	type: 'cross',
		                label: {
		                    backgroundColor: '#283b56'
		                }
			        }*/
			    },
			    legend: {
					show:false,
			        data: Monthlegend,
			        // x : 'right',
                    right:"7",
		    		textStyle : {
						color : chartFont.color || '#fff',
		    		}
			    },
			    grid: {
			        left: 10,
			        right: 40,
			        bottom: '5%',
			        containLabel: true
			    },
			    dataZoom: {
	                show: false,
	                start: 0,
	                end: 100
	            },
			    xAxis: {
			        type: 'value',
					name: nydw,
			        interval : 'auto',
			        axisLine : {
						lineStyle : {
							color : '#0177d4'
						}
					},
					splitLine: {
					    lineStyle: {
					        //分割线颜色
					        color: ['rgba(40, 76, 117, 0.9)']
					    }
					},
			        axisLabel: {
	                    show: true,
	                    textStyle: {
							color : chartFont.color || '#fff',
							fontSize:11
	                    },
						formatter: function(MonthSeriesData) {
							if (MonthSeriesData.length > 5) {
								return MonthSeriesData.substring(0, 5) + "...";
							} else {
								return MonthSeriesData;
							}
						},
	                },
			    },
			    yAxis: {
			        type: 'category',
			        data: MonthYAxisData,
			        scale: true,
			        interval:'0',
                    name: fhlx,
                    nameTextStyle : {
						color : chartFont.color || '#fff',
			        },
                    axisLine : {
						lineStyle : {
							color : '#0177d4'
						}
					},
			        axisLabel: {
	                    show: true,
	                    textStyle: {
							color : chartFont.color || '#fff',
	                        fontSize : 11
	                    },
						formatter: function(MonthYAxisData) {
							if (MonthYAxisData.length > 5) {
								return MonthYAxisData.substring(0,5) + "...";
							} else {
								return MonthYAxisData;
							}
						},
	                },
                    min: 0,
			    },
			    series: [
			        {
			        	barGap:18,
			            name: Monthlegend,
			            type: 'bar',
			            data: MonthSeriesData
			        }
			    ]
		};

		if (option && typeof option === "object") {
			myChartbottomTwo.setOption(option, true);
		}

		$(window).resize(function(){
			myChartbottomTwo.resize();
		});
	}

	function gzt_bottomThree(nymc,nydw,legend,seriesData,timeData) {
		var dom = document.getElementById("gzt_bottomThree");
		var myChartbottomThree = echarts.init(dom, 'light');

		var option = {
			color:['#56b5ff','#14d58a'],
				 title : {
			    		text : '本月同比('+nymc+')',
			    		textStyle : {
							color : chartFont.color || '#fff',
			    			fontSize: '15'
			    		},
			    	},
				tooltip : {
			        trigger: 'axis',
			        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
			            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			        }
			    },
			    legend: {
			    	data:legend,
				    x : 'right',
				    padding:[5,10,0,0],
		    		textStyle : {
						color : chartFont.color || '#fff',
						fontSize:11
		    		},
                    orient:"vertical"
			    },
			    grid: {
				 	left:30,
			        // left: '18%',
			        right: '4%',
			        bottom: '5%',
			        containLabel: true
			    },
			    xAxis : [
			        {
			            type : 'category',
			            data:timeData,
			            axisLine : {
							lineStyle : {
								color : '#0177d4'
							}
						},
				        axisLabel: {
		                    show: true,
		                    textStyle: {
								color : chartFont.color || '#fff',
								fontSize:11
		                    }
		                }
			        }
			    ],
			    yAxis : [
			        {
			        	name: '单位：'+nydw,
			        	nameTextStyle : {
							color : chartFont.color || '#fff',
				        },
			            type : 'value',
			            axisLine : {
							lineStyle : {
								color : '#0177d4'
							}
						},
						splitLine: {
						    lineStyle: {
						        //分割线颜色
						        color: ['rgba(40, 76, 117, 0.9)']
						    }
						},
				        axisLabel: {
		                    show: true,
		                    textStyle: {
								color : chartFont.color || '#fff',
		                    }
		                }
			        }
			    ],
			    series : seriesData
		};
		if (option && typeof option === "object") {
			myChartbottomThree.setOption(option, true);
		}
		$(window).resize(function(){
			myChartbottomThree.resize();
		});

	}

	function gzt_bottomFour(nymc,nydw,legend,seriesData,timeData) {
		var dom = document.getElementById("gzt_bottomFour");
		var myChartbottomFour = echarts.init(dom, 'light');

		var option = {
			color:['#9f99ff','#ffce5e'],
				 title : {
			    		text : '本月环比('+nymc+')',
			    		textStyle : {
							color : chartFont.color || '#fff',
			    			fontSize: '15'
			    		},
			    	},
				tooltip : {
			        trigger: 'axis',
			        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
			            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			        }
			    },
			    legend: {
			    	data:legend,
				    x : 'right',
				    padding:[5,10,0,0],
		    		textStyle : {
						color : chartFont.color || '#fff',
						fontSize:11
		    		},
					orient:"vertical"
			    },
			    grid: {
				 	left:30,
			        // left: '18%',
			        right: '4%',
			        bottom: '5%',
			        containLabel: true
			    },
			    xAxis : [
			        {
			            type : 'category',
			            data:timeData,
			            axisLine : {
							lineStyle : {
								color : '#0177d4'
							}
						},
				        axisLabel: {
		                    show: true,
		                    textStyle: {
								color : chartFont.color || '#fff',
		                    }
		                }
			        }
			    ],
			    yAxis : [
			        {
			        	name: '单位：'+nydw,
			        	nameTextStyle : {
							color : chartFont.color || '#fff',
				        },
			            type : 'value',
			            axisLine : {
							lineStyle : {
								color : '#0177d4'
							}
						},
						splitLine: {
						    lineStyle: {
						        //分割线颜色
						        color: ['rgba(40, 76, 117, 0.9)']
						    }
						},
				        axisLabel: {
		                    show: true,
		                    textStyle: {
								color : chartFont.color || '#fff',
		                    }
		                }
			        }
			    ],
			    series : seriesData
		};
		if (option && typeof option === "object") {
			myChartbottomFour.setOption(option, true);
		}
		$(window).resize(function(){
			myChartbottomFour.resize();
		});
	}
