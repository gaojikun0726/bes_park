/**
 * 一体化页面配置通用js
 * @returns
 */

var pointValueConfig = {}; // 点值设置

!function getSettingsInfo() {

    $.ajax({
        type    : "POST",
        url     : _ctx + "/btnEventController/getSettingsInfo",
        dataType: "json",
        success: function (result) {

            var list = result && result.list;

            if (!Array.isArray(list))
            {
                return;
            }

            for (let i = 0; i < list.length; i++)
            {

                var f_sys_name = list[i].f_sys_name;
                var f_value = list[i].f_value;
                var f_desc = list[i].f_desc;

                if (pointValueConfig[f_sys_name])
                {
                    pointValueConfig[f_sys_name][f_value] = f_desc;

                }else
                {
                    pointValueConfig[f_sys_name] = {
                        [f_value] : f_desc
                    }
                }
            }
        }
    });
}();

    	function searchIntegrationPageDivConfigNum(f_equipmentId,f_divClassNameCommon,f_type_id){
    		//f_type_id : 1:冷冻水,2:冷却水,3:冷热机组,4:冷却塔,5:电表
    		$.ajax({//查询页面配置的所需展示div的数目
    				type : "get",			
    	        url :_ctx + '/view/strongAndWeakElectricityIntegration/integrationCommonConfig/searchIntegrationPageDivConfigNum',
    	        dataType : "json",
    	        data : {f_equipment_id:f_equipmentId,
    	        		f_type_id     :f_type_id},//TODO   此处需要得到进入页面后的设备id
    	        success : function(result) {
    	           	if(result.code == '0'){//取得所需展示的div数目，将div显示
    	           		pageConfigShowNum=result.data;//将查出的div展示数目赋值
    					//将所需要展示的div显示出来
    					for(var i=1; i<=result.data;i++){
    						$(f_divClassNameCommon+i).css('display','');
    					}
    	           		//var divNum = result.data.length;//取得依据传入的设备id
    	  	        	}else{
    	  	        		layer.msg(result.msg);
    	  	        	}
    	         },
    	           error : function() {
    	           	   layer.msg("查询配置展示div数目失败");
    	           }
    	       });
    	}
//wanghongjie 查询左侧配置的div数量
function searchIntegrationPageDivConfigNumByLeft(f_equipmentId,f_divClassNameCommon,f_type_id){
	$.ajax({//查询页面配置的所需展示div的数目
		type : "get",
		url :_ctx + '/view/strongAndWeakElectricityIntegration/integrationCommonConfig/searchIntegrationPageDivConfigNumByLeft',
		dataType : "json",
		data : {f_equipment_id:f_equipmentId,
				f_type_id : f_type_id},//TODO   此处需要得到进入页面后的设备id
		success : function(result) {
			if(result.code == '0'){//取得所需展示的div数目，将div显示
				pageConfigShowNum=result.data;//将查出的div展示数目赋值
				//将所需要展示的div显示出来
				for(var i=1; i<=result.data;i++){
					$(f_divClassNameCommon+i).css('display','');
				}
				//var divNum = result.data.length;//取得依据传入的设备id
			}else{
				layer.msg(result.msg);
			}
		},
		error : function() {
			layer.msg("查询配置展示div数目失败");
		}
	});
}

    	/* 查询页面配置信息并将信息展示 */
    	function loadPageData(f_equipmentId,f_divClassNameCommon,f_type_id, callback){

    		$.ajax({//查询页面配置的展示div所应展示的点位信息
    				type : "get",			
    	        url :_ctx + '/view/strongAndWeakElectricityIntegration/integrationCommonConfig/searchPageConfigData',
    	        dataType : "json",
    	        data : {f_equipment_id : f_equipmentId,//进入页面后的设备id
    	        		f_type_id      : f_type_id},//f_type_id : 1:冷冻水,2:冷却水,3:冷热机组,4:冷却塔,5:电表 
    	        success : function(result) {
    	           	if(result.code == '0'){
    	           		//数据查询成功，根据页面配置信息在页面展示相应的数据TODO
    	           		//console.log("loadPageData SUCCESS");
    	           		var sysNameList = new Array();
    	           		for(var i=1;i<=result.count;i++){  //将所配置的div相应的的描述展示
    						var seqConfigNum=result.data[i-1].f_seq;

    						$(f_divClassNameCommon+seqConfigNum+"1").text(result.data[i-1].f_desc);

							if (typeof (result.data[i-1].f_formula) =="undefined") {
								$(f_divClassNameCommon+seqConfigNum+"4").text("");
							}else {
								$(f_divClassNameCommon+seqConfigNum+"4").text(result.data[i-1].f_formula);
							}

							// if (typeof (result.data[i-1].f_formula1) =="undefined") {
							// 	$(f_divClassNameCommon+"19"+"4").text("");
							// }else {
							// 	$(f_divClassNameCommon+"19"+"4").text(result.data[i-1].f_formula1);
							// }

							if (typeof (result.data[i-1].f_formula22) =="undefined") {
								$(f_divClassNameCommon+seqConfigNum+"5").text("");
							}else {
								$(f_divClassNameCommon+seqConfigNum+"5").text(result.data[i-1].f_formula22);
							}

							if (typeof (result.data[i-1].f_formula42) =="undefined") {
								$(f_divClassNameCommon+seqConfigNum+"5").text("");
							}else {
								$(f_divClassNameCommon+seqConfigNum+"5").text(result.data[i-1].f_formula42);
							}

							if (typeof (result.data[i-1].f_formula43) =="undefined") {
								$(f_divClassNameCommon+seqConfigNum+"6").text("");
							}else {
								$(f_divClassNameCommon+seqConfigNum+"6").text(result.data[i-1].f_formula43);
							}

							if (typeof (result.data[i-1].f_formula44) =="undefined") {
								$(f_divClassNameCommon+seqConfigNum+"7").text("");
							}else {
								$(f_divClassNameCommon+seqConfigNum+"7").text(result.data[i-1].f_formula44);
							}
    						$(f_divClassNameCommon+seqConfigNum).attr("fddcsysname",result.data[i-1].f_ddc_sys_name);//将配置所处的ddc名称设置成对应属性
    						$(f_divClassNameCommon+seqConfigNum).attr("fSysName",result.data[i-1].f_sys_name);//将其配置的系统名设置给对应 的名称属性
							$(f_divClassNameCommon+seqConfigNum).attr("formula",result.data[i-1].f_formula);//将其配置的公式给对应 的名称属性

							$(f_divClassNameCommon+seqConfigNum).attr("fddcsysname1",result.data[i-1].f_ddc_sys_name1);//将配置所处的ddc名称设置成对应属性
							$(f_divClassNameCommon+seqConfigNum).attr("fSysName1",result.data[i-1].f_sys_name1);//将其配置的系统名设置给对应 的名称属性
							$(f_divClassNameCommon+seqConfigNum).attr("formula1",result.data[i-1].f_formula1);//将其配置的公式给对应 的名称属性


							$(f_divClassNameCommon+seqConfigNum).attr("fddcsysname21",result.data[i-1].f_ddc_sys_name21);//将配置所处的ddc名称设置成对应属性
							$(f_divClassNameCommon+seqConfigNum).attr("fSysName21",result.data[i-1].f_sys_name21);//将其配置的系统名设置给对应 的名称属性
							$(f_divClassNameCommon+seqConfigNum).attr("formula21",result.data[i-1].f_formula21);//将其配置的公式给对应 的名称属性


							$(f_divClassNameCommon+seqConfigNum).attr("fddcsysname22",result.data[i-1].f_ddc_sys_name22);//将配置所处的ddc名称设置成对应属性
							$(f_divClassNameCommon+seqConfigNum).attr("fSysName22",result.data[i-1].f_sys_name22);//将其配置的系统名设置给对应 的名称属性
							$(f_divClassNameCommon+seqConfigNum).attr("formula22",result.data[i-1].f_formula22);//将其配置的公式给对应 的名称属性


							$(f_divClassNameCommon+seqConfigNum).attr("fddcsysname41",result.data[i-1].f_ddc_sys_name41);//将配置所处的ddc名称设置成对应属性
							$(f_divClassNameCommon+seqConfigNum).attr("fSysName41",result.data[i-1].f_sys_name41);//将其配置的系统名设置给对应 的名称属性
							$(f_divClassNameCommon+seqConfigNum).attr("formula41",result.data[i-1].f_formula41);//将其配置的公式给对应 的名称属性


							$(f_divClassNameCommon+seqConfigNum).attr("fddcsysname42",result.data[i-1].f_ddc_sys_name42);//将配置所处的ddc名称设置成对应属性
							$(f_divClassNameCommon+seqConfigNum).attr("fSysName42",result.data[i-1].f_sys_name42);//将其配置的系统名设置给对应 的名称属性
							$(f_divClassNameCommon+seqConfigNum).attr("formula42",result.data[i-1].f_formula42);//将其配置的公式给对应 的名称属性


							$(f_divClassNameCommon+seqConfigNum).attr("fddcsysname43",result.data[i-1].f_ddc_sys_name43);//将配置所处的ddc名称设置成对应属性
							$(f_divClassNameCommon+seqConfigNum).attr("fSysName43",result.data[i-1].f_sys_name43);//将其配置的系统名设置给对应 的名称属性
							$(f_divClassNameCommon+seqConfigNum).attr("formula43",result.data[i-1].f_formula43);//将其配置的公式给对应 的名称属性


							$(f_divClassNameCommon+seqConfigNum).attr("fddcsysname44",result.data[i-1].f_ddc_sys_name44);//将配置所处的ddc名称设置成对应属性
							$(f_divClassNameCommon+seqConfigNum).attr("fSysName44",result.data[i-1].f_sys_name44);//将其配置的系统名设置给对应 的名称属性
							$(f_divClassNameCommon+seqConfigNum).attr("formula44",result.data[i-1].f_formula44);//将其配置的公式给对应 的名称属性

							$(f_divClassNameCommon+seqConfigNum).attr("fddcsysname51",result.data[i-1].f_ddc_sys_name);//将配置所处的ddc名称设置成对应属性
							$(f_divClassNameCommon+seqConfigNum).attr("fSysName51",result.data[i-1].f_sys_name);//将其配置的系统名设置给对应 的名称属性
							$(f_divClassNameCommon+seqConfigNum).attr("formula51",result.data[i-1].f_formula);//将其配置的公式给对应 的名称属性

							$(f_divClassNameCommon+seqConfigNum).attr("fddcsysname52",result.data[i-1].f_ddc_sys_name);//将配置所处的ddc名称设置成对应属性
							$(f_divClassNameCommon+seqConfigNum).attr("fSysName52",result.data[i-1].f_sys_name);//将其配置的系统名设置给对应 的名称属性
							$(f_divClassNameCommon+seqConfigNum).attr("formula52",result.data[i-1].f_formula);//将其配置的公式给对应 的名称属性

							$(f_divClassNameCommon+seqConfigNum).attr("fddcsysname53",result.data[i-1].f_ddc_sys_name);//将配置所处的ddc名称设置成对应属性
							$(f_divClassNameCommon+seqConfigNum).attr("fSysName53",result.data[i-1].f_sys_name);//将其配置的系统名设置给对应 的名称属性
							$(f_divClassNameCommon+seqConfigNum).attr("formula53",result.data[i-1].f_formula);//将其配置的公式给对应 的名称属性

    						$(f_divClassNameCommon+seqConfigNum).attr("fdescid",f_divClassNameCommon.replace(".","")+seqConfigNum+"1");//设置描述DIV的id
							$(f_divClassNameCommon+seqConfigNum).attr("formula",f_divClassNameCommon.replace(".","")+seqConfigNum+"4");//设置描述公式的id
    						$(f_divClassNameCommon+seqConfigNum+"1").attr("id",f_divClassNameCommon.replace(".","")+seqConfigNum+"1");//给描述DIV设置id
							$(f_divClassNameCommon+seqConfigNum+"4").attr("id",f_divClassNameCommon.replace(".","")+seqConfigNum+"4");//给描述DIV设置id
    	           			//$(".integrationPageDivShow"+seqConfigNum+"1").text(result.data[i-1].f_desc);
    	           			//$(".integrationPageDivShow"+seqConfigNum).attr("fSysName",result.data[i-1].f_sys_name);//将其配置的系统名设置给对应 的名称属性
    	           			sysNameList.push(result.data[i-1].f_sys_name);
							sysNameList.push(result.data[i-1].f_sys_name1);
							sysNameList.push(result.data[i-1].f_sys_name21);
							sysNameList.push(result.data[i-1].f_sys_name22);
							sysNameList.push(result.data[i-1].f_sys_name41);
							sysNameList.push(result.data[i-1].f_sys_name42);
							sysNameList.push(result.data[i-1].f_sys_name43);
							sysNameList.push(result.data[i-1].f_sys_name44);

    	           		}
    	           		$(f_divClassNameCommon+"1").attr("fsysnamelist",sysNameList);//Add by yangjx at 20200110 for 给第一个div上设置记录全部系统名称，以备回显用
    	        		//将所需展示的数据信息和单位填入div展示
    	        		if(sysNameList.length!=0){
    	            		loadInitValAndEngineerUnit(sysNameList,f_divClassNameCommon, f_type_id);
    	        		}

    	        		if (typeof callback === 'function')
						{
                            callback();
						}
    	  	        }else{
    	  	        		layer.msg(result.msg);
    	  	        }
    	       	},
    	        error : function() {
    	           	layer.msg("失败");
    	        }
    	       });
    	}

//*wanghongjie 查询页面配置信息并将信息展示 top */
function loadPageDatatop(f_equipmentId,f_divClassNameCommon,f_type_id){
	$.ajax({//查询页面配置的展示div所应展示的点位信息
		type : "get",
		url :_ctx + '/view/strongAndWeakElectricityIntegration/integrationCommonConfig/searchPageConfigDatatop',
		dataType : "json",
		data : {f_equipment_id:f_equipmentId,
				f_type_id : f_type_id
				},//TODO   此处需要得到进入页面后的设备id
		success : function(result) {
			if(result.code == '0'){

				//数据查询成功，根据页面配置信息在页面展示相应的数据TODO
				//console.log("loadPageData SUCCESS");
				var sysNameList = new Array();
				for(var i=1;i<=result.count;i++){//将所配置的div相应的的描述展示
					var seqConfigNum=result.data[i-1].f_seq;
					$(f_divClassNameCommon+seqConfigNum+"1").text(result.data[i-1].f_desc);

				}
				//$(f_divClassNameCommon+"1").attr("fsysnamelist",sysNameList);//Add by yangjx at 20200110 for 给第一个div上设置记录全部系统名称，以备回显用
				//将所需展示的数据信息和单位填入div展示
				/*if(sysNameList.length!=0){
					loadInitValAndEngineerUnit(sysNameList,f_divClassNameCommon);
				}*/
			}else{
				layer.msg(result.msg);
			}
		},
		error : function() {
			layer.msg("失败");
		}
	});
}

//*wanghongjie 查询页面配置信息并将信息展示 */
function loadPageDataUnder(f_equipmentId,f_divClassNameCommon,f_type_id){

	$.ajax({//查询页面配置的展示div所应展示的点位信息
		type : "get",
		url :_ctx + '/view/strongAndWeakElectricityIntegration/integrationCommonConfig/searchPageConfigData',
		dataType : "json",
		data : {f_equipment_id:f_equipmentId,
				f_type_id : f_type_id
				},//TODO   此处需要得到进入页面后的设备id
		success : function(result) {
			if(result.code == '0'){
				//数据查询成功，根据页面配置信息在页面展示相应的数据TODO
				//console.log("loadPageData SUCCESS");
				var sysNameList = new Array();
				for(var i=1;i<=result.count;i++){//将所配置的div相应的的描述展示
					var seqConfigNum=result.data[i-1].f_seq;

					$(f_divClassNameCommon+seqConfigNum+"1").text(result.data[i-1].f_desc);
					if (typeof (result.data[i-1].f_formula) =="undefined") {
						$(f_divClassNameCommon+seqConfigNum+"4").text("");
					}else {
						$(f_divClassNameCommon+seqConfigNum+"4").text(result.data[i-1].f_formula);
					}

					$(f_divClassNameCommon+seqConfigNum).attr("fddcsysname",result.data[i-1].f_ddc_sys_name);//将配置所处的ddc名称设置成对应属性
					$(f_divClassNameCommon+seqConfigNum).attr("fSysName",result.data[i-1].f_sys_name);//将其配置的系统名设置给对应 的名称属性

					$(f_divClassNameCommon+seqConfigNum).attr("fdescid",f_divClassNameCommon.replace(".","")+seqConfigNum+"1");//设置描述DIV的id
					$(f_divClassNameCommon+seqConfigNum).attr("formula",f_divClassNameCommon.replace(".","")+seqConfigNum+"4");//设置描述公式的id
					$(f_divClassNameCommon+seqConfigNum+"1").attr("id",f_divClassNameCommon.replace(".","")+seqConfigNum+"1");//给描述DIV设置id
					$(f_divClassNameCommon+seqConfigNum+"4").attr("id",f_divClassNameCommon.replace(".","")+seqConfigNum+"4");//给描述DIV设置id
					//$(".integrationPageDivShow"+seqConfigNum+"1").text(result.data[i-1].f_desc);
					//$(".integrationPageDivShow"+seqConfigNum).attr("fSysName",result.data[i-1].f_sys_name);//将其配置的系统名设置给对应 的名称属性
					sysNameList.push(result.data[i-1].f_sys_name);
				}
				$(f_divClassNameCommon+"1").attr("fsysnamelistbyleft",sysNameList);//Add by yangjx at 20200110 for 给第一个div上设置记录全部系统名称，以备回显用
				//将所需展示的数据信息和单位填入div展示
				if(sysNameList.length!=0){
					loadInitValAndEngineerUnitUnder(sysNameList,f_divClassNameCommon, f_type_id);
				}
			}else{
				layer.msg(result.msg);
			}
		},
		error : function() {
			layer.msg("失败");
		}
	});
}

    	/* 根据传入的系统名称，获取对应的数据加载至页面展示 */
    	function loadInitValAndEngineerUnit(sysNameList,f_divClassNameCommon, f_type_id){
    		$.ajax({
    			        type: "post",
    			        url: _ctx+'/view/strongAndWeakElectricityIntegration/integrationCommonConfig/loadInitValAndEngineerUnit',
    			        contentType:'application/json;charset=UTF-8',
    			        traditional:true,
    			        data:JSON.stringify({
                            f_sysName_list : sysNameList
    			        }),
    			        success: function (result) {

                            if(result.code == '0'){
    			        		for(var i=1;i<=result.count;i++){ //3


                                    var f_sys_name = result.data[i-1].f_sys_name || '';
                                    var f_sys_name_old = result.data[i-1].f_sys_name_old || '';
                                    var f_init_val = result.data[i-1].f_init_val || '';
                                    var f_engineer_unit = result.data[i-1].f_engineer_unit || '';
                                    var f_accuracy = result.data[i-1].f_accuracy;
									var initByFour;
									var initByFourReplace;
									var reg = new RegExp("[\\u4E00-\\u9FFF]+","g");

                                    if (pointValueConfig[f_sys_name] && pointValueConfig[f_sys_name][f_init_val])
                                    {
                                        f_init_val = pointValueConfig[f_sys_name][f_init_val];
                                    }


                                    switch (f_type_id)
                                    {
                                        case "1":
                                            PubSub.subscribe(f_sys_name, integration_divColdWarmWater.coldWarmWaterPageDivShowCallback);
                                            break;
                                        case "2":
                                            PubSub.subscribe(f_sys_name, integration_divCoolingWater.coolingWaterPageDivShowCallback);
                                            break;
                                        case "3":
                                            PubSub.subscribe(f_sys_name, divColdHeatSource.coldHeatSourcePageDivShowCallback);
                                            break;
                                        case "4":
                                            PubSub.subscribe(f_sys_name, integration_coolingTower.coolingTowerPageDivShowCallback);
                                            break;
                                    }


                                    if($(f_divClassNameCommon+"1").attr("fSysName") == f_sys_name){

										if (!reg.test(f_init_val)) {

											initByFour = $("#" + (f_divClassNameCommon+"14").replace(".","")).html();
											if (initByFour != "") {
												initByFourReplace = initByFour.replace("$1",f_init_val);

												f_init_val = eval(initByFourReplace);
											}
											f_init_val = Math.round(f_init_val*100)/100;
										}

    			        				$(f_divClassNameCommon+"12").text(f_init_val);
    			        				$(f_divClassNameCommon+"13").text(f_engineer_unit);
    			        				continue;
    			        			}
									if($(f_divClassNameCommon+"2").attr("fSysName") == f_sys_name){

										if (!reg.test(f_init_val)) {

											initByFour = $("#" + (f_divClassNameCommon+"24").replace(".","")).html();
											if (initByFour != "") {
												initByFourReplace = initByFour.replace("$1",f_init_val);

												f_init_val = eval(initByFourReplace);
											}
											f_init_val = Math.round(f_init_val*100)/100;
										}

										$(f_divClassNameCommon+"22").text(f_init_val);
											$(f_divClassNameCommon+"23").text(f_engineer_unit);
										continue;
									}
									if($(f_divClassNameCommon+"3").attr("fSysName") == f_sys_name){

										if (!reg.test(f_init_val)) {

											initByFour = $("#" + (f_divClassNameCommon+"34").replace(".","")).html();
											if (initByFour != "") {
												initByFourReplace = initByFour.replace("$1",f_init_val);

												f_init_val = eval(initByFourReplace);
											}
											f_init_val = Math.round(f_init_val*100)/100;
										}

										$(f_divClassNameCommon+"32").text(f_init_val);
											$(f_divClassNameCommon+"33").text(f_engineer_unit);
										continue;
									}
									if($(f_divClassNameCommon+"4").attr("fSysName") == f_sys_name){

										if (!reg.test(f_init_val)) {

											initByFour = $("#" + (f_divClassNameCommon+"44").replace(".","")).html();
											if (initByFour != "") {
												initByFourReplace = initByFour.replace("$1",f_init_val);

												f_init_val = eval(initByFourReplace);
											}
											f_init_val = Math.round(f_init_val*100)/100;
										}

										$(f_divClassNameCommon+"42").text(f_init_val);
											$(f_divClassNameCommon+"43").text(f_engineer_unit);
										continue;
									}
									if($(f_divClassNameCommon+"5").attr("fSysName") == f_sys_name){

										if (!reg.test(f_init_val)) {

											initByFour = $("#" + (f_divClassNameCommon+"54").replace(".","")).html();
											if (initByFour != "") {
												initByFourReplace = initByFour.replace("$1",f_init_val);

												f_init_val = eval(initByFourReplace);
											}
											f_init_val = Math.round(f_init_val*100)/100;
										}

										$(f_divClassNameCommon+"52").text(f_init_val);
											$(f_divClassNameCommon+"53").text(f_engineer_unit);
										continue;
									}
									if($(f_divClassNameCommon+"6").attr("fSysName") == f_sys_name){

										if (!reg.test(f_init_val)) {

											initByFour = $("#" + (f_divClassNameCommon+"64").replace(".","")).html();
											if (initByFour != "") {
												initByFourReplace = initByFour.replace("$1",f_init_val);

												f_init_val = eval(initByFourReplace);
											}
											f_init_val = Math.round(f_init_val*100)/100;

										}

										$(f_divClassNameCommon+"62").text(f_init_val);
											$(f_divClassNameCommon+"63").text(f_engineer_unit);
										continue;
									}
									/*if($(f_divClassNameCommon+"7").attr("fSysName21") == f_sys_name){
										$(f_divClassNameCommon+"7").attr("val21",f_init_val);
										continue;
									}
									if($(f_divClassNameCommon+"7").attr("fSysName22") == f_sys_name){
										$(f_divClassNameCommon+"7").attr("val22",f_init_val);
										continue;
									}
									if($(f_divClassNameCommon+"8").attr("fSysName1") == f_sys_name){
										$(f_divClassNameCommon+"8").attr("val1",f_init_val);
										continue;
									}
									if($(f_divClassNameCommon+"9").attr("fSysName41") == f_sys_name){
										$(f_divClassNameCommon+"9").attr("val41",f_init_val);
										continue;
									}
									if($(f_divClassNameCommon+"9").attr("fSysName42") == f_sys_name){
										$(f_divClassNameCommon+"9").attr("val42",f_init_val);
										continue;
									}
									if($(f_divClassNameCommon+"9").attr("fSysName43") == f_sys_name){
										$(f_divClassNameCommon+"9").attr("val43",f_init_val);
										continue;
									}
									if($(f_divClassNameCommon+"9").attr("fSysName44") == f_sys_name){
										$(f_divClassNameCommon+"9").attr("val44",f_init_val);
									}*/

    			        		}
    			        	}else{
    			        		layer.msg(result.msg);
    			        	}
    			        },
    			        error: function (result) {
    			        	layer.msg(result.msg);
    			        }
    				});
    	}

//wanghongjie 根据传入的系统名称，获取对应的数据加载至页面展示
function loadInitValAndEngineerUnitUnder(sysNameList,f_divClassNameCommon, f_type_id){
	$.ajax({
		type: "post",
		url: _ctx+'/view/strongAndWeakElectricityIntegration/integrationCommonConfig/loadInitValAndEngineerUnit',
		contentType:'application/json;charset=UTF-8',
		traditional:true,
		data:JSON.stringify({
			f_sysName_list : sysNameList
		}),
		success: function (result) {
			if(result.code == '0'){
				for(let i=1;i<=result.count;i++){ //3

                    var f_sys_name = result.data[i-1].f_sys_name || '';
                    var f_sys_name_old = result.data[i-1].f_sys_name_old || '';
                    var f_init_val = result.data[i-1].f_init_val || '';
                    var f_engineer_unit = result.data[i-1].f_engineer_unit || '';
                    var f_accuracy = result.data[i-1].f_accuracy;
                    var initByFour;
                    var initByFourReplace;
					var reg = new RegExp("[\\u4E00-\\u9FFF]+","g");

                    if (pointValueConfig[f_sys_name] && pointValueConfig[f_sys_name][f_init_val])
                    {
                        f_init_val = pointValueConfig[f_sys_name][f_init_val];
                    }

                    switch (f_type_id)
					{
						case "1":
                            PubSub.subscribe(f_sys_name, integration_divColdWarmWater.commonChangeColorWarmWater);
							break;
						case "2":
                            PubSub.subscribe(f_sys_name, integration_divCoolingWater.commonChangeColorCoolingWater);
							break;
						case "3":
                            PubSub.subscribe(f_sys_name, divColdHeatSource.commonChangeColorColdHeatSource);
							break;
						case "4":
                            PubSub.subscribe(f_sys_name, integration_coolingTower.commonChangeColorCoolingTower);
							break;
					}

					if($(f_divClassNameCommon+"8").attr("fSysName")==f_sys_name){

						if (!reg.test(f_init_val)) {

							initByFour = $("#" + (f_divClassNameCommon+"84").replace(".","")).html();
							if (initByFour != "") {
								initByFourReplace = initByFour.replace("$1",f_init_val);

								f_init_val = eval(initByFourReplace);
							}
							f_init_val = Math.round(f_init_val*100)/100;
						}

						$(f_divClassNameCommon+"82").text(f_init_val);
						$(f_divClassNameCommon+"83").text(f_engineer_unit);
						continue;
					}
					if($(f_divClassNameCommon+"9").attr("fSysName")==f_sys_name){

						if (!reg.test(f_init_val)) {

							initByFour = $("#" + (f_divClassNameCommon+"94").replace(".","")).html();
							if (initByFour != "") {
								initByFourReplace = initByFour.replace("$1",f_init_val);

								f_init_val = eval(initByFourReplace);
							}
							f_init_val = Math.round(f_init_val*100)/100;
						}

						$(f_divClassNameCommon+"92").text(f_init_val);
						$(f_divClassNameCommon+"93").text(f_engineer_unit);
						continue;
					}
					if($(f_divClassNameCommon+"30").attr("fSysName")==f_sys_name){

						if (!reg.test(f_init_val)) {

							initByFour = $("#" + (f_divClassNameCommon+"304").replace(".","")).html();
							if (initByFour != "") {
								initByFourReplace = initByFour.replace("$1",f_init_val);

								f_init_val = eval(initByFourReplace);
							}
							f_init_val = Math.round(f_init_val*100)/100;
						}

						$(f_divClassNameCommon+"302").text(f_init_val);
						$(f_divClassNameCommon+"303").text(f_engineer_unit);
						continue;
					}
					if($(f_divClassNameCommon+"15").attr("fSysName")==f_sys_name){

						if (!reg.test(f_init_val)) {

							initByFour = $("#" + (f_divClassNameCommon+"154").replace(".","")).html();
							if (initByFour != "") {
								initByFourReplace = initByFour.replace("$1",f_init_val);

								f_init_val = eval(initByFourReplace);
							}
							f_init_val = Math.round(f_init_val*100)/100;
						}

						$(f_divClassNameCommon+"152").text(f_init_val);
						$(f_divClassNameCommon+"153").text(f_engineer_unit);
						continue;
					}
					if($(f_divClassNameCommon+"16").attr("fSysName")==f_sys_name){

						if (!reg.test(f_init_val)) {

							initByFour = $("#" + (f_divClassNameCommon+"164").replace(".","")).html();
							if (initByFour != "") {
								initByFourReplace = initByFour.replace("$1",f_init_val);

								f_init_val = eval(initByFourReplace);
							}
							f_init_val = Math.round(f_init_val*100)/100;
						}

						$(f_divClassNameCommon+"162").text(f_init_val);
						$(f_divClassNameCommon+"163").text(f_engineer_unit);
						continue;
					}
					if($(f_divClassNameCommon+"17").attr("fSysName")==f_sys_name){

						if (!reg.test(f_init_val)) {

							initByFour = $("#" + (f_divClassNameCommon+"174").replace(".","")).html();
							if (initByFour != "") {
								initByFourReplace = initByFour.replace("$1",f_init_val);

								f_init_val = eval(initByFourReplace);
							}
							f_init_val = Math.round(f_init_val*100)/100;
						}

						$(f_divClassNameCommon+"172").text(f_init_val);
						$(f_divClassNameCommon+"173").text(f_engineer_unit);
					}
				}
			}else{
				layer.msg(result.msg);
			}
		},
		error: function (result) {
			layer.msg(result.msg);
		}
	});
}
    	
    	//Add by yangjx at 20200110 for 定时页面刷新
    	function integrationCommonDataTimedRefresh(sysNameList,f_divClassNameCommon){

			if(sysNameList!=""&&sysNameList!=null){
				$.ajax({
					type    : "POST",
					url     : _ctx + "/view/basedatamanage/eqmanage/sbdy_treeIconForJKById",
					dataType: "json",
					data:{
						sysName: sysNameList
					},
					success: function (result) {
						if(result.list!='undifined'&&result.list!=null){//有传过来的值
							//TODO  将获取的数据添加到相应的DIV进行展示
							for(var i=1;i<=9;i++){
								for(var m=0;m<result.list.length;m++){
									if($(f_divClassNameCommon+i).attr("fsysname")==result.list[m].f_sys_name){
										$(f_divClassNameCommon+i+"2").text(result.list[m].f_init_val);
										$(f_divClassNameCommon+i).attr("val_now",result.list[m].f_init_val);
									}
									if($(f_divClassNameCommon+i).attr("fsysname1")==result.list[m].f_sys_name){
										$(f_divClassNameCommon+i).attr("val1_now",result.list[m].f_init_val);
									}
									if($(f_divClassNameCommon+i).attr("fsysname21")==result.list[m].f_sys_name){
										$(f_divClassNameCommon+i).attr("val21_now",result.list[m].f_init_val);
									}
									if($(f_divClassNameCommon+i).attr("fsysname22")==result.list[m].f_sys_name){
										$(f_divClassNameCommon+i).attr("val22_now",result.list[m].f_init_val);
									}
									if($(f_divClassNameCommon+i).attr("fsysname41")==result.list[m].f_sys_name){
										$(f_divClassNameCommon+i).attr("val41_now",result.list[m].f_init_val);
									}
									if($(f_divClassNameCommon+i).attr("fsysname42")==result.list[m].f_sys_name){
										$(f_divClassNameCommon+i).attr("val42_now",result.list[m].f_init_val);
									}
									if($(f_divClassNameCommon+i).attr("fsysname43")==result.list[m].f_sys_name){
										$(f_divClassNameCommon+i).attr("val43_now",result.list[m].f_init_val);
									}
									if($(f_divClassNameCommon+i).attr("fsysname44")==result.list[m].f_sys_name){
										$(f_divClassNameCommon+i).attr("val44_now",result.list[m].f_init_val);
									}
								}
							}
						}
					},
					error: function (result) {
						layer.msg("错误:"+result.msg);
					}
				});
			}
        }

//wanghongjie 定时页面刷新 左侧配置页面
function integrationCommonDataTimedRefreshByLeft(sysNameList,f_divClassNameCommon){

	if(sysNameList!=""&&sysNameList!=null){
		$.ajax({
			type    : "POST",
			url     : _ctx + "/view/basedatamanage/eqmanage/sbdy_treeIconForJKById",
			dataType: "json",
			data:{
				sysName: sysNameList
			},
			success: function (result) {
				if(result.list!='undifined'&&result.list!=null){//有传过来的值
					//TODO  将获取的数据添加到相应的DIV进行展示
					for(var i=1;i<=18;i++){
						for(var m=0;m<result.list.length;m++){
							if($(f_divClassNameCommon+i).attr("fsysname")==result.list[m].f_sys_name){
								$(f_divClassNameCommon+i+"2").text(result.list[m].f_init_val);
								$(f_divClassNameCommon+i).attr("val_now",result.list[m].f_init_val);
							}
							if($(f_divClassNameCommon+i).attr("fsysname1")==result.list[m].f_sys_name){
								$(f_divClassNameCommon+i).attr("val1_now",result.list[m].f_init_val);
							}
							if($(f_divClassNameCommon+i).attr("fsysname21")==result.list[m].f_sys_name){
								$(f_divClassNameCommon+i).attr("val21_now",result.list[m].f_init_val);
							}
							if($(f_divClassNameCommon+i).attr("fsysname22")==result.list[m].f_sys_name){
								$(f_divClassNameCommon+i).attr("val22_now",result.list[m].f_init_val);
							}
							if($(f_divClassNameCommon+i).attr("fsysname41")==result.list[m].f_sys_name){
								$(f_divClassNameCommon+i).attr("val41_now",result.list[m].f_init_val);
							}
							if($(f_divClassNameCommon+i).attr("fsysname42")==result.list[m].f_sys_name){
								$(f_divClassNameCommon+i).attr("val42_now",result.list[m].f_init_val);
							}
							if($(f_divClassNameCommon+i).attr("fsysname43")==result.list[m].f_sys_name){
								$(f_divClassNameCommon+i).attr("val43_now",result.list[m].f_init_val);
							}
							if($(f_divClassNameCommon+i).attr("fsysname44")==result.list[m].f_sys_name){
								$(f_divClassNameCommon+i).attr("val44_now",result.list[m].f_init_val);
							}
						}
					}
				}
			},
			error: function (result) {
				layer.msg("错误:"+result.msg);
			}
		});
	}
}
function dataConverter(obj, callback) {//从缓存中取出相应字段的属性值

	if (typeof callback !== 'function') {
		return;
	}
	var list = obj && obj.data;

	if (!Array.isArray(list)){
		return;
	}

	if (list.length <= 0) {
		return;
	}

	var dataMap = {};

	for(var i = 0; i < list.length; i++){

		var item = list[i];

		dataMap[item.f_SFK] = null;     //水阀开
		dataMap[item.f_SFG] = null;		//水阀关
		dataMap[item.f_SBQD] = null;	//水泵启动
		dataMap[item.f_SFKDW] = null;	//水阀开到位
		dataMap[item.f_SFGDW] = null;	//水阀关到位
		dataMap[item.f_BPYXZT] = null;	//变频器运行状态
		dataMap[item.f_BPGZZT] = null;	//变频器故障状态
		dataMap[item.f_BPQPLSD] = null;	//变频器频率设定
		dataMap[item.f_BPQPLFK] = null;	//变频器频率反馈
		dataMap[item.f_SLZT] = null;	//水流状态
		dataMap[item.f_SFGZZT] = null;	//水阀故障状态
		dataMap[item.f_SBYXZT] = null;	//水泵运行状态
		dataMap[item.f_SBTZ] = null;	//水泵停止
		dataMap[item.f_YXZSD] = null;	//运行指示灯
		dataMap[item.f_GZZSD] = null;	//故障指示灯
		dataMap[item.f_QDXH] = null;	//启动信号
		dataMap[item.f_TZXH] = null;	//停止信号

		dataMap[item.fanSwitch] = null;               //风机启停
		dataMap[item.waterValveOpen] = null;          //水阀开
		dataMap[item.waterValveClose] = null;	      //水阀关
		dataMap[item.inletValveOpenArrives] = null;   //进水阀开到位
		dataMap[item.inletValveCloseArrives] = null;  //进水阀关到位
		dataMap[item.outletValveOpenArrives] = null;  //出水阀开到位
		dataMap[item.outletValveCloseArrives] = null; //出水阀关到位
		dataMap[item.inletValveFault] = null;		  //进水阀故障
		dataMap[item.outletValveFault] = null;		  //出水阀故障
		dataMap[item.fanFault] = null;				  //风机故障
		dataMap[item.runningStatus] = null;			  //运行状态

		dataMap[item.currentState] =null;					//冷冻机组启停状态
		dataMap[item.freezeSupplyWaterTemperature] =null;	//冷冻水供水温度
		dataMap[item.freezeReturnWaterTemperature] =null;	//冷冻水回水温度
		dataMap[item.coolingSupplyWaterTemperature] =null;	//冷却水供水温度
		dataMap[item.coolingReturnWaterTemperature] =null;	//冷却水回水温度
		dataMap[item.currentFlow] =null;					//冷冻机组当前流量

		dataMap[item.instant_energy] = null;	//瞬时能耗
		dataMap[item.total_energy] = null;	    //累计能耗
		dataMap[item.a_phase_voltage] = null;	//A相电流
		dataMap[item.b_phase_voltage] = null;	//B相电流
		dataMap[item.c_phase_voltage] = null;	//C相电流
		dataMap[item.a_phase_current] = null;	//A相电压
		dataMap[item.b_phase_current] = null;	//B相电压
		dataMap[item.c_phase_current] = null;	//C相电压

	}

	$.ajax({
		type    : "POST",
		url     : _ctx + "/view/basedatamanage/eqmanage/sbdy_treeIconForJK",
		dataType: "json",
		data:{
			f_sys_name: Object.keys(dataMap).join(',')
		},
		success: function (result) {

			var ddcList = result.list || [];

			for(var j = 0; j < ddcList.length; j++) {

				var fSysName = ddcList[j].f_sys_name;

				dataMap[fSysName] = ddcList[j].f_init_val;

			}

			for(var k = 0; k < list.length; k++) {

				list[k].f_SFK	 = dataMap[list[k].f_SFK];   //水阀开
				list[k].f_SFG 	 = dataMap[list[k].f_SFG];   //水阀关
				list[k].f_SBQD 	 = dataMap[list[k].f_SBQD];  //水泵启动
				list[k].f_SFKDW  = dataMap[list[k].f_SFKDW]; //水阀开到位
				list[k].f_SFGDW  = dataMap[list[k].f_SFGDW]; //水阀关到位
				list[k].f_BPYXZT = dataMap[list[k].f_BPYXZT];//变频器运行状态
				list[k].f_BPGZZT = dataMap[list[k].f_BPGZZT];//变频器故障状态
				list[k].f_BPQPLSD= dataMap[list[k].f_BPQPLSD];//变频器频率设定;
				list[k].f_BPQPLFK= dataMap[list[k].f_BPQPLFK];//变频器频率反馈;
				list[k].f_SLZT   = dataMap[list[k].f_SLZT];  //水流状态
				list[k].f_SFGZZT = dataMap[list[k].f_SFGZZT];//水阀故障状态
				list[k].f_SBYXZT = dataMap[list[k].f_SBYXZT];//水泵运行状态
				list[k].f_SBTZ   = dataMap[list[k].f_SBTZ];  //水泵停止
				list[k].f_YXZSD  = dataMap[list[k].f_YXZSD]; //运行指示灯
				list[k].f_GZZSD  = dataMap[list[k].f_GZZSD]; //故障指示灯
				list[k].f_QDXH   = dataMap[list[k].f_QDXH];  //启动信号
				list[k].f_TZXH   = dataMap[list[k].f_TZXH];  //停止信号

				list[k].fanSwitch   = dataMap[list[k].fanSwitch];                            //风机启停
				list[k].waterValveOpen   = dataMap[list[k].waterValveOpen];                  //水阀开
				list[k].waterValveClose   = dataMap[list[k].waterValveClose];                //水阀关
				list[k].inletValveOpenArrives   = dataMap[list[k].inletValveOpenArrives];    //进水阀开到位
				list[k].inletValveCloseArrives   = dataMap[list[k].inletValveCloseArrives];  //进水阀关到位
				list[k].outletValveOpenArrives   = dataMap[list[k].outletValveOpenArrives];  //出水阀开到位
				list[k].outletValveCloseArrives   = dataMap[list[k].outletValveCloseArrives];//出水阀关到位
				list[k].inletValveFault   = dataMap[list[k].inletValveFault];                //进水阀故障
				list[k].outletValveFault   = dataMap[list[k].outletValveFault];              //出水阀故障
				list[k].fanFault   = dataMap[list[k].fanFault];                              //风机故障
				list[k].runningStatus   = dataMap[list[k].runningStatus];                    //运行状态

				list[k].currentState   = dataMap[list[k].currentState];										//冷冻机组启停状态
				list[k].freezeSupplyWaterTemperature   = dataMap[list[k].freezeSupplyWaterTemperature];		//冷冻水供水温度
				list[k].freezeReturnWaterTemperature   = dataMap[list[k].freezeReturnWaterTemperature];		//冷冻水回水温度
				list[k].coolingSupplyWaterTemperature   = dataMap[list[k].coolingSupplyWaterTemperature];	//冷却水供水温度
				list[k].coolingReturnWaterTemperature   = dataMap[list[k].coolingReturnWaterTemperature];	//冷却水回水温度
				list[k].currentFlow   = dataMap[list[k].currentFlow];										//冷冻机组当前流量

				list[k].instant_energy    = dataMap[list[k].instant_energy];   //瞬时能耗
				list[k].a_phase_voltage   = dataMap[list[k].a_phase_voltage];  //A相电流
				list[k].b_phase_voltage   = dataMap[list[k].b_phase_voltage];  //B相电流
				list[k].c_phase_voltage   = dataMap[list[k].c_phase_voltage];  //C相电流
				list[k].a_phase_current   = dataMap[list[k].a_phase_current];  //A相电压
				list[k].total_energy      = dataMap[list[k].total_energy];     //累计能耗
				list[k].b_phase_current   = dataMap[list[k].b_phase_current];  //B相电压
				list[k].c_phase_current   = dataMap[list[k].c_phase_current];  //C相电压

			}

			callback({data: list,DDCLIST:ddcList});

		},
		error: function (result) {

			console.warn(result)
		}
	});

}

function startStopControl(sysName, val) {

	$.ajax({
		url : _ctx + "/api/debugPointInfo",
		type : "post",
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		data : JSON.stringify({
			f_sys_name : sysName,
			f_init_val : val,
			tabname : "bes_sbpz_struct",
			nodeLevel : "7",//用来判定属于哪个系统(楼控系统为7，照明系统为5)
			f_node_attribution : "1",
			f_work_mode: "1"
		}),
		success : function(result) {
			if(result.status === '1'){
				layer.msg('操作成功');
			}else{
				layer.msg('操作失败');
			}
		},
		error : function(result) {
			layer.msg('操作失败');
		}
	});
}

    	
