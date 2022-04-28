<!-----内容区域---->
			
<div class="information-model">
	<span class="Subtitle">
		<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;建筑基本信息列表>>>
	</span>
	<!-- 增加按钮 -->
	<a id="addbudingType" data-toggle="modal"  href="#modal-form-addbudingType" class="btn btn-primary toLeft">
        <i class="fa fa-plus"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>增加
    </a>
	<!-- 搜索框 -->
	<div class="zc_search find">
			 <input type="text" class="find-style"  id="budinginfo" name="budinginfo" placeholder="建筑代码、名称">
			 <button id="queryBudingType"><i class="fa fa-search" aria-hidden="true"></i></button>
	</div>
	<!-- 导入 -->
	<a data-toggle="modal" href="#import-addbudingType" class="btn btn-primary toLeft"> <i class="fa fa-upload"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>&nbsp;导入</a>
	<!-- 导出 -->
	<a class="btn btn-primary toLeft" onclick="energydataReport_basebudingmanager_budingType.exportReport()"> <i class="fa fa-download"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>&nbsp;导出</a>
	
</div>
<!---分页列表----->	        
<div class="ibox" id="Buding_ibox" style="height:92%">
</div>


<!---添加开始-----> 
<div class="modal fade" id="modal-form-addbudingType" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog" style="width:62%;">
        <div class="modal-content" >
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp;添加建筑基本信息</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="addBuding" name="addBuding" class="form-horizontal" style="height:70vh;overflow-y:auto;overflow-x:hidden;padding-right: 14vh;">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">节点代码<span class="text-danger">*</span></label>
                        <div class="col-sm-3">
                          <input type="text" id="fBudingCode" name="fBudingCode"  required class="form-control">
                        </div>

                        <label class="col-sm-2 control-label">数据中心名称<span class="text-danger">*</span></label>
                        <div class="col-sm-3">
                           <select id="fCenterId" name="fCenterId"  class="form-control selectpicker" >
                           </select>
                        </div>

                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">建筑名称<span class="text-danger">*</span></label>
                        <div class="col-sm-3">
                          <input type="text" id="fBudingName" name="fBudingName"  required class="form-control">
                        </div>
                        <label class="col-sm-2 control-label">建筑物别名<span class="text-danger">*</span> </label>
                        <div class="col-sm-3">
                          <input type="text" id="fBudingLetter" name="fBudingLetter"  class="form-control">
                        </div>

                    </div>
                    
                    <div class="form-group">
                      <label class="col-sm-2 control-label">建筑物业主<span class="text-danger">*</span></label>
                      <div class="col-sm-3">
                        <input type="text" id="fBudingOwner" name="fBudingOwner"   required class="form-control">
                      </div>
                      <label class="col-sm-2 control-label">建筑检测状态<span class="text-danger">*</span></label>
                      <div class="col-sm-3">
                        <select id="fBudingDetection" name="fBudingDetection"  class="form-control selectpicker" >
                          <option value="1">启用检测</option>
                          <option value="0">停用检测</option>
                        </select>
                      </div>

                    </div>

                  <div class="form-group">
                    <label class="col-sm-2 control-label">建筑物行政区代码<span class="text-danger">*</span> </label>
                    <div class="col-sm-3">
                      <input type="text" id="fBudingNode" name="fBudingNode"   class="form-control">
                    </div>
                    <label class="col-sm-2 control-label">建筑地址<span class="text-danger">*</span></label>
                    <div class="col-sm-3">
                      <input type="text" id="fBudingAddress" name="fBudingAddress" required  class="form-control">
                    </div>

                  </div>

                  <div class="form-group">
                    <label class="col-sm-2 control-label">建筑坐标经度<span class="text-danger">*</span></label>
                    <div class="col-sm-3">
                      <input type="text" id="fBudingLongitude" name="fBudingLongitude"  class="form-control number">
                    </div>
                    <label class="col-sm-2 control-label">建筑坐标纬度<span class="text-danger">*</span></label>
                    <div class="col-sm-3">
                      <input type="text" id="fBudingLatitude" name="fBudingLatitude"  class="form-control number">
                    </div>

                    <#--<label class="col-sm-2 control-label">竣工年份<span class="text-danger">*</span></label>
                    <div class="col-sm-3">
                      <input type="text" id="fDateCompletion" name="fDateCompletion" onfocus="WdatePicker({dateFmt: 'yyyy'})"  class="form-control">
                    </div>-->
                  </div>


                  <div class="form-group">

                    <label class="col-sm-2 control-label">地上建筑层数<span class="text-danger">*</span></label>
                    <div class="col-sm-3">
                      <input type="text" id="fBudingLayerson" name="fBudingLayerson" class="form-control number">
                    </div>
                    <label class="col-sm-2 control-label">地下建筑层数<span class="text-danger">*</span></label>
                    <div class="col-sm-3">
                      <input type="text" id="fBudingLayersunder" name="fBudingLayersunder"  class="form-control number">
                    </div>
                  </div>

                  <div class="form-group">
                    <label class="col-sm-2 control-label">建筑物类型<span class="text-danger">*</span></label>
                    <div class="col-sm-3">
                      <select id="fBudingFunction" name="fBudingFunction"  class="form-control selectpicker" >
                        <option value="">请选择</option>
                        <option value="A">办公建筑</option>
                        <option value="B">商场建筑</option>
                        <option value="C">宾馆饭店建筑</option>
                        <option value="D">文化教育建筑</option>
                        <option value="E">医疗卫生建筑</option>
                        <option value="F">体育建筑</option>
                        <option value="G">综合建筑</option>
                        <option value="Z">其他建筑</option>
                      </select>
                    </div>
                    <label class="col-sm-2 control-label">建筑物总面积<span class="text-danger">*</span></label>
                    <div class="col-sm-3">
                      <input type="text" id="fBudingArea" name="fBudingArea"  required class="form-control number">
                    </div>
                  </div>

                  <div class="form-group">
                    <label class="col-sm-2 control-label">空调面积㎡<span class="text-danger">*</span></label>
                    <div class="col-sm-3">
                      <input type="text" id="fAirconditionerArea" name="fAirconditionerArea" class="form-control number">
                    </div>
                    <label class="col-sm-2 control-label">采暖面积㎡<span class="text-danger">*</span></label>
                    <div class="col-sm-3">
                      <input type="text" id="fHeatingArea" name="fHeatingArea"  class="form-control number">
                    </div>
                  </div>

                  <div class="form-group">
                    <label class="col-sm-2 control-label">空调系统形式<span class="text-danger">*</span></label>
                    <div class="col-sm-3">
                      <select id="fAirconditionerSystem" name="fAirconditionerSystem"  class="form-control selectpicker" >
                        <option value="">请选择</option>
                        <option value="A">集中式全空气系统</option>
                        <option value="B">风机盘管＋新风系统</option>
                        <option value="C">分体式空调或VRV的局部式机组系统</option>
                        <option value="Z">其他</option>
                      </select>
                    </div>
                    <label class="col-sm-2 control-label">采暖系统形式<span class="text-danger">*</span></label>
                    <div class="col-sm-3">
                      <select id="fHeatingSystem" name="fHeatingSystem"  class="form-control selectpicker" >
                        <option value="">请选择</option>
                        <option value="A">散热器采暖</option>
                        <option value="B">地板辐射采暖</option>
                        <option value="C">电辐射采暖</option>
                        <option value="D">空调系统集中供暖</option>
                        <option value="Z">其它</option>
                      </select>
                    </div>
                  </div>

                  <div class="form-group">
                    <label class="col-sm-2 control-label">建筑物建设时间<span class="text-danger">*</span></label>
                    <div class="col-sm-3">
                      <input type="text" id="fBudingDate" name="fBudingDate" onfocus="WdatePicker({dateFmt: 'yyyy'})"  class="form-control">
                    </div>
                    <label class="col-sm-2 control-label">建筑结构形式<span class="text-danger">*</span></label>
                    <div class="col-sm-3">
                      <select id="fArchitecturalForm" name="fArchitecturalForm"  class="form-control selectpicker" >
                        <option value="">请选择</option>
                        <option value="A">框架结构</option>
                        <option value="B">框-剪结构</option>
                        <option value="C">剪力墙结构</option>
                        <option value="D">砖混结构</option>
                        <option value="E">钢结构</option>
                        <option value="F">筒体结构</option>
                        <option value="G">木结构</option>
                        <option value="Z">其它</option>
                      </select>
                    </div>
                  </div>

                  <div class="form-group">

                    <label class="col-sm-2 control-label">外墙材料形式<span class="text-danger">*</span></label>
                    <div class="col-sm-3">
                      <select id="fExteriorwallForm" name="fExteriorwallForm"  class="form-control selectpicker" >
                        <option value="">请选择</option>
                        <option value="A">砖</option>
                        <option value="B">建筑砌块</option>
                        <option value="C">板材墙体</option>
                        <option value="D">复合墙板和墙体</option>
                        <option value="E">玻璃幕墙</option>
                        <option value="Z">其它</option>
                      </select>
                    </div>
                    <label class="col-sm-2 control-label">外墙保温形式<span class="text-danger">*</span></label>
                    <div class="col-sm-3">
                      <select id="fExteriorwallInsulationForm" name="fExteriorwallInsulationForm"  class="form-control selectpicker" >
                        <option value="">请选择</option>
                        <option value="A">内保温</option>
                        <option value="B">外保温</option>
                        <option value="C">夹芯保温</option>
                        <option value="Z">其它</option>
                      </select>
                    </div>
                  </div>

                  <div class="form-group">
                    <label class="col-sm-2 control-label">建筑外窗类型<span class="text-danger">*</span></label>
                    <div class="col-sm-3">
                      <select id="fBudingExteriorType" name="fBudingExteriorType"  class="form-control selectpicker" >
                        <option value="">请选择</option>
                        <option value="A">单玻单层窗</option>
                        <option value="B">单玻双层窗</option>
                        <option value="C">单玻单层窗+单玻双层窗</option>
                        <option value="D">中空双层玻璃窗</option>
                        <option value="E">中空三层玻璃窗</option>
                        <option value="F">中空充惰性气体</option>
                        <option value="Z">其它</option>
                      </select>
                    </div>
                    <label class="col-sm-2 control-label">建筑玻璃类型<span class="text-danger">*</span></label>
                    <div class="col-sm-3">
                      <select id="fBudingGlassType" name="fBudingGlassType"  class="form-control selectpicker" >
                        <option value="">请选择</option>
                        <option value="A">普通玻璃</option>
                        <option value="B">镀膜玻璃</option>
                        <option value="C">Low-e玻璃</option>
                        <option value="Z">其它</option>
                      </select>
                    </div>
                  </div>

                  <div class="form-group">
                    <label class="col-sm-2 control-label">窗框材料类型<span class="text-danger">*</span></label>
                    <div class="col-sm-3">
                      <select id="fMaterialType" name="fMaterialType"  class="form-control selectpicker" >
                        <option value="">请选择</option>
                        <option value="A">钢窗</option>
                        <option value="B">铝合金</option>
                        <option value="C">木窗</option>
                        <option value="D">断热窗框</option>
                        <option value="E">塑钢</option>
                        <option value="Z">其它</option>
                      </select>
                    </div>
                    <label class="col-sm-2 control-label">是否标杆建筑<span class="text-danger">*</span></label>
                    <div class="col-sm-3">
                      <select id="fBenchmarking" name="fBenchmarking"  class="form-control selectpicker" >
                        <option value="">请选择</option>
                        <option value="true">是</option>
                        <option value="false">否</option>
                      </select>
                    </div>
                  </div>

                  <div class="form-group">
                    <label class="col-sm-2 control-label">监测方案设计单位<span class="text-danger">*</span></label>
                    <div class="col-sm-3">
                      <input type="text" id="fSchemeDesign" name="fSchemeDesign"  class="form-control">
                    </div>
                    <label class="col-sm-2 control-label">监测工程实施单位<span class="text-danger">*</span></label>
                    <div class="col-sm-3">
                      <input type="text" id="fEngineeringConstruction" name="fEngineeringConstruction"  class="form-control">
                    </div>
                  </div>

                  <div class="form-group">
                    <label class="col-sm-2 control-label">录入时间<span class="text-danger">*</span></label>
                    <div class="col-sm-3">
                      <input type="text" id="fEntryTime" name="fEntryTime" required class="form-control Wdate" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss'})">
                    </div>
                    <label class="col-sm-2 control-label">开始检测日期<span class="text-danger">*</span></label>
                    <div class="col-sm-3">
                      <input type="text" id="fDetectionTime" name="fDetectionTime" required  class="form-control Wdate" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss'})">
                    </div>

                  </div>
                  <div class="form-group">

                    <label class="col-sm-2 control-label">建筑体形系数</label>
                    <div class="col-sm-3">
                      <input type="text" id="fBudingCoefficient" name="fBudingCoefficient"  class="form-control">
                    </div>

                    <label class="col-sm-2 control-label">设计单位</label>
                    <div class="col-sm-3">
                      <input type="text" id="fDesignOrganization" name="fDesignOrganization"  class="form-control">
                    </div>
                  </div>
                    
                    <div class="form-group">
                        <label class="col-sm-2 control-label">业主单位</label>
                        <div class="col-sm-3">
                            <input type="text" id="fBudingOwnerCompany" name="fBudingOwnerCompany"  class="form-control">
                        </div>
                      <label class="col-sm-2 control-label">建筑总人口</label>
                      <div class="col-sm-3">
                        <input type="text" id="fBudingPopulation" name="fBudingPopulation"  class="form-control number">
                      </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-sm-2 control-label">建筑联系人</label>
                        <div class="col-sm-3">
                            <input type="text" id="fBudingContact" name="fBudingContact"  class="form-control">
                        </div>
                        <label class="col-sm-2 control-label">联系方式</label>
                        <div class="col-sm-3">
                            <input type="text" id="fContactInformation" name="fContactInformation"  class="form-control isTel">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">用能人数</label>
                        <div class="col-sm-3">
                            <input type="text" id="fBudingYnrs" name="fBudingYnrs"   class="form-control number">
                        </div>
                        <label class="col-sm-2 control-label">供热方式</label>
                        <div class="col-sm-3">
                           <select id="fHeatingMode" name="fHeatingMode"  class="form-control selectpicker" >
                             	<option value="">请选择</option>
                             	<option value="1">集中供热</option>
                             	<option value="2">分户采暖</option>
                             	<option value="3">无供热</option>
                          </select>
                        </div>
                    </div>
                    
                    <div class="form-group">
                      <label class="col-sm-2 control-label">建筑高度m</label>
                      <div class="col-sm-3">
                        <input type="text" id="fBudingHeight" name="fBudingHeight"  class="form-control number">
                      </div>
                        <label class="col-sm-2 control-label">供冷方式</label>
                        <div class="col-sm-3">
                            <select id="fCoolingMode" name="fCoolingMode"  class="form-control selectpicker" >
                             	<option value="">请选择</option>
                             	<option value="1">集中供冷</option>
                             	<option value="2">分户采冷</option>
                             	<option value="3">无供冷</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">执行节能标准</label>
                        <div class="col-sm-3">
                           <select id="fSavingStandard" name="fSavingStandard"  class="form-control selectpicker" >
                             	<option value="">请选择</option>
                             	<option value="1">未执行节能标准</option>
                             	<option value="2">节能50%标准</option>
                             	<option value="3">节能60%标准</option>
                             	<option value="4">节能65%以上标准</option>
                           </select>
                        </div>
                        <label class="col-sm-2 control-label">是否节能改造</label>
                        <div class="col-sm-3">
                             <select id="fSavingTransformation" name="fSavingTransformation"  class="form-control selectpicker" >
                             	<option value="">请选择</option>
                             	<option value="1">是</option>
                             	<option value="2">否</option>
                             </select>
                        </div>
                    </div>
                    
                    <div class="form-group">
                      <label class="col-sm-2 control-label">建筑状态</label>
                      <div class="col-sm-3">
                        <select id="fBudingState" name="fBudingState"  class="form-control selectpicker" >
                          <option value="">请选择</option>
                          <option value="1">建成</option>
                          <option value="2">封顶</option>
                          <option value="3">主体结构施工</option>
                          <option value="4">基础施工</option>
                          <option value="5">基坑施工</option>
                        </select>
                      </div>
                        <label class="col-sm-2 control-label">是否政府办公楼</label>
                        <div class="col-sm-3">
                            <select id="fBudingGovernment" name="fBudingGovernment"  class="form-control selectpicker" >
                             	<option value="">请选择</option>
                             	<option value="1">是</option>
                             	<option value="2">否</option>
                            </select>
                        </div>

                    </div>

<#--                    <div class="form-group">
                      <label class="col-sm-2 control-label">创建操作人名称</label>
                      <div class="col-sm-3">
                        <input type="text" id="fOperatorName" name="fOperatorName"  readonly="readonly"  class="form-control">
                      </div>
                        <label class="col-sm-2 control-label">所属县区<span class="text-danger">*</span></label>
                        <div class="col-sm-3">
                             <select id="fCountyArea" name="fCountyArea"  class="form-control selectpicker" >
                             	<option value="">请选择</option>
                             	<option value="1">历下区</option>
                             	<option value="2">市中区</option>
                             	<option value="3">槐荫区</option>
                             	<option value="4">天桥区</option>
                             	<option value="5">历城区</option>
                             	<option value="6">长清区</option>
                             	<option value="7">平阴县</option>
                             	<option value="8">济阳县</option>
                             	<option value="9">商河县</option>
                             	<option value="10">章丘区</option>
                             </select>
                        </div>
                  </div>-->
                    
                    <div class="form-group">
                      <label class="col-sm-2 control-label">工程联系人</label>
                      <div class="col-sm-3">
                        <input type="text" id="fEngineeringContacts" name="fEngineeringContacts" class="form-control">
                      </div>
                        <label class="col-sm-2 control-label">工程联系人联系方式</label>
                        <div class="col-sm-3">
                            <input type="text" id="fEngineeringContactsBgdh" name="fEngineeringContactsBgdh"  class="form-control isTel">
                        </div>

                    </div>
                    
                    <div class="form-group">
                        <label class="col-sm-2 control-label">监测面积</label>
                        <div class="col-sm-3">
                            <input type="text" id="fMonitoringArea" name="fMonitoringArea"  class="form-control number">
                        </div>
                        <#--<label class="col-sm-2 control-label">系统软件</label>
                        <div class="col-sm-3">
                            <input type="text" id="fSystemSoftware" name="fSystemSoftware"  class="form-control">
                        </div>-->
                      <label class="col-sm-2 control-label">工程验收日期</label>
                      <div class="col-sm-3">
                        <input type="text" id="fAcceptanceTime" name="fAcceptanceTime"  class="form-control Wdate" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss'})">
                      </div>
                    </div>

                    <#-- <div class="form-group">
                         <label class="col-sm-2 control-label">采集器型号</label>
                         <div class="col-sm-3">
                             <input type="text" id="fCollectorType" name="fCollectorType"  class="form-control">
                         </div>
                       <label class="col-sm-2 control-label">工程验收日期</label>
                       <div class="col-sm-3">
                         <input type="text" id="fAcceptanceTime" name="fAcceptanceTime"  class="form-control Wdate" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss'})">
                       </div>
                    </div>-->
                    

                    <div class="form-group">

                        <label class="col-sm-2 control-label">描述</label>
                        <div class="col-sm-3">
                            <input type="text" id="fDescribe" name="fDescribe"  class="form-control">
                        </div>
                    </div>
                    <div class="form-group m-t-sm" >
                        <div class="col-sm-6 col-sm-push-5 display_flex">
                            <button class="btn btn-md btn-primary" type="submit"><strong>确定</strong></button>
                            <button class="btn btn-white m-l-sm"  type="button" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- 添加类型结束 -->


<!----编辑--->
<div class="modal fade" id="modal-form-editBudingType" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog" style="width:62%;">
        <div class="modal-content">        
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title editIcon">&nbsp;编辑</h4>
            </div>
            <div class="modal-body">
                <form id="editBuding" name="editBuding" class="form-horizontal" style="height:70vh;overflow-y:auto;overflow-x:hidden;padding-right: 14vh;">
                    <input type="hidden" id="edit_fBudingId" name="fBudingId"  class="form-control">
                  <div class="form-group">
                    <label class="col-sm-2 control-label">节点代码<span class="text-danger">*</span></label>
                    <div class="col-sm-3">
                      <input type="text" id="edit_fBudingCode" name="fBudingCode"  required class="form-control">
                    </div>

                    <label class="col-sm-2 control-label">数据中心名称<span class="text-danger">*</span></label>
                    <div class="col-sm-3">
                      <select id="edit_fCenterId" name="fCenterId"  class="form-control selectpicker" >
                      </select>
                    </div>

                  </div>

                  <div class="form-group">
                    <label class="col-sm-2 control-label">建筑名称<span class="text-danger">*</span></label>
                    <div class="col-sm-3">
                      <input type="text" id="edit_fBudingName" name="fBudingName"  required class="form-control">
                    </div>
                    <label class="col-sm-2 control-label">建筑物别名<span class="text-danger">*</span> </label>
                    <div class="col-sm-3">
                      <input type="text" id="edit_fBudingLetter" name="fBudingLetter"  class="form-control">
                    </div>

                  </div>

                  <div class="form-group">
                    <label class="col-sm-2 control-label">建筑物业主<span class="text-danger">*</span></label>
                    <div class="col-sm-3">
                      <input type="text" id="edit_fBudingOwner" name="fBudingOwner"   required class="form-control">
                    </div>
                    <label class="col-sm-2 control-label">建筑检测状态<span class="text-danger">*</span></label>
                    <div class="col-sm-3">
                      <select id="edit_fBudingDetection" name="fBudingDetection"  class="form-control selectpicker" >
                        <option value="1">启用检测</option>
                        <option value="0">停用检测</option>
                      </select>
                    </div>

                  </div>

                  <div class="form-group">
                    <label class="col-sm-2 control-label">建筑物行政区代码<span class="text-danger">*</span> </label>
                    <div class="col-sm-3">
                      <input type="text" id="edit_fBudingNode" name="fBudingNode"   class="form-control">
                    </div>
                    <label class="col-sm-2 control-label">建筑地址<span class="text-danger">*</span></label>
                    <div class="col-sm-3">
                      <input type="text" id="edit_fBudingAddress" name="fBudingAddress" required  class="form-control">
                    </div>

                  </div>

                  <div class="form-group">
                    <label class="col-sm-2 control-label">建筑坐标经度<span class="text-danger">*</span></label>
                    <div class="col-sm-3">
                      <input type="text" id="edit_fBudingLongitude" name="fBudingLongitude"  class="form-control number">
                    </div>
                    <label class="col-sm-2 control-label">建筑坐标纬度<span class="text-danger">*</span></label>
                    <div class="col-sm-3">
                      <input type="text" id="edit_fBudingLatitude" name="fBudingLatitude"  class="form-control number">
                    </div>

                      <#--<label class="col-sm-2 control-label">竣工年份<span class="text-danger">*</span></label>
                      <div class="col-sm-3">
                        <input type="text" id="fDateCompletion" name="fDateCompletion" onfocus="WdatePicker({dateFmt: 'yyyy'})"  class="form-control">
                      </div>-->
                  </div>


                  <div class="form-group">

                    <label class="col-sm-2 control-label">地上建筑层数<span class="text-danger">*</span></label>
                    <div class="col-sm-3">
                      <input type="text" id="edit_fBudingLayerson" name="fBudingLayerson" class="form-control number">
                    </div>
                    <label class="col-sm-2 control-label">地下建筑层数<span class="text-danger">*</span></label>
                    <div class="col-sm-3">
                      <input type="text" id="edit_fBudingLayersunder" name="fBudingLayersunder"  class="form-control number">
                    </div>
                  </div>

                  <div class="form-group">
                    <label class="col-sm-2 control-label">建筑物类型<span class="text-danger">*</span></label>
                    <div class="col-sm-3">
                      <select id="edit_fBudingFunction" name="fBudingFunction"  class="form-control selectpicker" >
                        <option value="">请选择</option>
                        <option value="A">办公建筑</option>
                        <option value="B">商场建筑</option>
                        <option value="C">宾馆饭店建筑</option>
                        <option value="D">文化教育建筑</option>
                        <option value="E">医疗卫生建筑</option>
                        <option value="F">体育建筑</option>
                        <option value="G">综合建筑</option>
                        <option value="Z">其他建筑</option>
                      </select>
                    </div>
                    <label class="col-sm-2 control-label">建筑物总面积<span class="text-danger">*</span></label>
                    <div class="col-sm-3">
                      <input type="text" id="edit_fBudingArea" name="fBudingArea"  required class="form-control number">
                    </div>
                  </div>

                  <div class="form-group">
                    <label class="col-sm-2 control-label">空调面积㎡<span class="text-danger">*</span></label>
                    <div class="col-sm-3">
                      <input type="text" id="edit_fAirconditionerArea" name="fAirconditionerArea" class="form-control number">
                    </div>
                    <label class="col-sm-2 control-label">采暖面积㎡<span class="text-danger">*</span></label>
                    <div class="col-sm-3">
                      <input type="text" id="edit_fHeatingArea" name="fHeatingArea"  class="form-control number">
                    </div>
                  </div>

                  <div class="form-group">
                    <label class="col-sm-2 control-label">空调系统形式<span class="text-danger">*</span></label>
                    <div class="col-sm-3">
                      <select id="edit_fAirconditionerSystem" name="fAirconditionerSystem"  class="form-control selectpicker" >
                        <option value="">请选择</option>
                        <option value="A">集中式全空气系统</option>
                        <option value="B">风机盘管＋新风系统</option>
                        <option value="C">分体式空调或VRV的局部式机组系统</option>
                        <option value="Z">其他</option>
                      </select>
                    </div>
                    <label class="col-sm-2 control-label">采暖系统形式<span class="text-danger">*</span></label>
                    <div class="col-sm-3">
                      <select id="edit_fHeatingSystem" name="fHeatingSystem"  class="form-control selectpicker" >
                        <option value="">请选择</option>
                        <option value="A">散热器采暖</option>
                        <option value="B">地板辐射采暖</option>
                        <option value="C">电辐射采暖</option>
                        <option value="D">空调系统集中供暖</option>
                        <option value="Z">其它</option>
                      </select>
                    </div>
                  </div>

                  <div class="form-group">

                    <label class="col-sm-2 control-label">建筑物建设时间<span class="text-danger">*</span></label>
                    <div class="col-sm-3">
                      <input type="text" id="edit_fBudingDate" name="fBudingDate" onfocus="WdatePicker({dateFmt: 'yyyy'})"  class="form-control">
                    </div>
                    <label class="col-sm-2 control-label">建筑结构形式<span class="text-danger">*</span></label>
                    <div class="col-sm-3">
                      <select id="edit_fArchitecturalForm" name="fArchitecturalForm"  class="form-control selectpicker" >
                        <option value="">请选择</option>
                        <option value="A">框架结构</option>
                        <option value="B">框-剪结构</option>
                        <option value="C">剪力墙结构</option>
                        <option value="D">砖混结构</option>
                        <option value="E">钢结构</option>
                        <option value="F">筒体结构</option>
                        <option value="G">木结构</option>
                        <option value="Z">其它</option>
                      </select>
                    </div>
                  </div>

                  <div class="form-group">

                    <label class="col-sm-2 control-label">外墙材料形式<span class="text-danger">*</span></label>
                    <div class="col-sm-3">
                      <select id="edit_fExteriorwallForm" name="fExteriorwallForm"  class="form-control selectpicker" >
                        <option value="">请选择</option>
                        <option value="A">砖</option>
                        <option value="B">建筑砌块</option>
                        <option value="C">板材墙体</option>
                        <option value="D">复合墙板和墙体</option>
                        <option value="E">玻璃幕墙</option>
                        <option value="Z">其它</option>
                      </select>
                    </div>
                    <label class="col-sm-2 control-label">外墙保温形式<span class="text-danger">*</span></label>
                    <div class="col-sm-3">
                      <select id="edit_fExteriorwallInsulationForm" name="fExteriorwallInsulationForm"  class="form-control selectpicker" >
                        <option value="">请选择</option>
                        <option value="A">内保温</option>
                        <option value="B">外保温</option>
                        <option value="C">夹芯保温</option>
                        <option value="Z">其它</option>
                      </select>
                    </div>
                  </div>

                  <div class="form-group">
                    <label class="col-sm-2 control-label">建筑外窗类型<span class="text-danger">*</span></label>
                    <div class="col-sm-3">
                      <select id="edit_fBudingExteriorType" name="fBudingExteriorType"  class="form-control selectpicker" >
                        <option value="">请选择</option>
                        <option value="A">单玻单层窗</option>
                        <option value="B">单玻双层窗</option>
                        <option value="C">单玻单层窗+单玻双层窗</option>
                        <option value="D">中空双层玻璃窗</option>
                        <option value="E">中空三层玻璃窗</option>
                        <option value="F">中空充惰性气体</option>
                        <option value="Z">其它</option>
                      </select>
                    </div>
                    <label class="col-sm-2 control-label">建筑玻璃类型<span class="text-danger">*</span></label>
                    <div class="col-sm-3">
                      <select id="edit_fBudingGlassType" name="fBudingGlassType"  class="form-control selectpicker" >
                        <option value="">请选择</option>
                        <option value="A">普通玻璃</option>
                        <option value="B">镀膜玻璃</option>
                        <option value="C">Low-e玻璃</option>
                        <option value="Z">其它</option>
                      </select>
                    </div>
                  </div>

                  <div class="form-group">
                    <label class="col-sm-2 control-label">窗框材料类型<span class="text-danger">*</span></label>
                    <div class="col-sm-3">
                      <select id="edit_fMaterialType" name="fMaterialType"  class="form-control selectpicker" >
                        <option value="">请选择</option>
                        <option value="A">钢窗</option>
                        <option value="B">铝合金</option>
                        <option value="C">木窗</option>
                        <option value="D">断热窗框</option>
                        <option value="E">塑钢</option>
                        <option value="Z">其它</option>
                      </select>
                    </div>
                    <label class="col-sm-2 control-label">是否标杆建筑<span class="text-danger">*</span></label>
                    <div class="col-sm-3">
                      <select id="edit_fBenchmarking" name="fBenchmarking"  class="form-control selectpicker" >
                        <option value="">请选择</option>
                        <option value="true">是</option>
                        <option value="false">否</option>
                      </select>
                    </div>
                  </div>

                  <div class="form-group">
                    <label class="col-sm-2 control-label">监测方案设计单位<span class="text-danger">*</span></label>
                    <div class="col-sm-3">
                      <input type="text" id="edit_fSchemeDesign" name="fSchemeDesign"  class="form-control">
                    </div>
                    <label class="col-sm-2 control-label">监测工程实施单位<span class="text-danger">*</span></label>
                    <div class="col-sm-3">
                      <input type="text" id="edit_fEngineeringConstruction" name="fEngineeringConstruction"  class="form-control">
                    </div>
                  </div>

                  <div class="form-group">
                    <label class="col-sm-2 control-label">录入时间<span class="text-danger">*</span></label>
                    <div class="col-sm-3">
                      <input type="text" id="edit_fEntryTime" name="fEntryTime" required class="form-control Wdate" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss'})">
                    </div>
                    <label class="col-sm-2 control-label">开始检测日期<span class="text-danger">*</span></label>
                    <div class="col-sm-3">
                      <input type="text" id="edit_fDetectionTime" name="fDetectionTime" required  class="form-control Wdate" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss'})">
                    </div>

                  </div>
                  <div class="form-group">

                    <label class="col-sm-2 control-label">建筑体形系数</label>
                    <div class="col-sm-3">
                      <input type="text" id="edit_fBudingCoefficient" name="fBudingCoefficient"  class="form-control">
                    </div>
                    <label class="col-sm-2 control-label">设计单位</label>
                    <div class="col-sm-3">
                      <input type="text" id="edit_fDesignOrganization" name="fDesignOrganization"  class="form-control">
                    </div>
                  </div>

                  <div class="form-group">
                    <label class="col-sm-2 control-label">业主单位</label>
                    <div class="col-sm-3">
                      <input type="text" id="edit_fBudingOwnerCompany" name="fBudingOwnerCompany"  class="form-control">
                    </div>
                    <label class="col-sm-2 control-label">工程联系人</label>
                    <div class="col-sm-3">
                      <input type="text" id="edit_fEngineeringContacts" name="fEngineeringContacts" class="form-control">
                    </div>
                  </div>

                  <div class="form-group">
                    <label class="col-sm-2 control-label">建筑联系人</label>
                    <div class="col-sm-3">
                      <input type="text" id="edit_fBudingContact" name="fBudingContact"  class="form-control">
                    </div>
                    <label class="col-sm-2 control-label">联系方式</label>
                    <div class="col-sm-3">
                      <input type="text" id="edit_fContactInformation" name="fContactInformation"  class="form-control isTel">
                    </div>
                  </div>

                  <div class="form-group">
                    <label class="col-sm-2 control-label">用能人数</label>
                    <div class="col-sm-3">
                      <input type="text" id="edit_fBudingYnrs" name="fBudingYnrs"   class="form-control number">
                    </div>
                    <label class="col-sm-2 control-label">供热方式</label>
                    <div class="col-sm-3">
                      <select id="edit_fHeatingMode" name="fHeatingMode"  class="form-control selectpicker" >
                        <option value="">请选择</option>
                        <option value="1">集中供热</option>
                        <option value="2">分户采暖</option>
                        <option value="3">无供热</option>
                      </select>
                    </div>
                  </div>

                  <div class="form-group">
                    <label class="col-sm-2 control-label">建筑高度m</label>
                    <div class="col-sm-3">
                      <input type="text" id="edit_fBudingHeight" name="fBudingHeight"  class="form-control number">
                    </div>
                    <label class="col-sm-2 control-label">供冷方式</label>
                    <div class="col-sm-3">
                      <select id="edit_fCoolingMode" name="fCoolingMode"  class="form-control selectpicker" >
                        <option value="">请选择</option>
                        <option value="1">集中供冷</option>
                        <option value="2">分户采冷</option>
                        <option value="3">无供冷</option>
                      </select>
                    </div>
                  </div>

                  <div class="form-group">
                    <label class="col-sm-2 control-label">执行节能标准</label>
                    <div class="col-sm-3">
                      <select id="edit_fSavingStandard" name="fSavingStandard"  class="form-control selectpicker" >
                        <option value="">请选择</option>
                        <option value="1">未执行节能标准</option>
                        <option value="2">节能50%标准</option>
                        <option value="3">节能60%标准</option>
                        <option value="4">节能65%以上标准</option>
                      </select>
                    </div>
                    <label class="col-sm-2 control-label">是否节能改造</label>
                    <div class="col-sm-3">
                      <select id="edit_fSavingTransformation" name="fSavingTransformation"  class="form-control selectpicker" >
                        <option value="">请选择</option>
                        <option value="1">是</option>
                        <option value="2">否</option>
                      </select>
                    </div>
                  </div>

                  <div class="form-group">
                    <label class="col-sm-2 control-label">建筑状态</label>
                    <div class="col-sm-3">
                      <select id="edit_fBudingState" name="fBudingState"  class="form-control selectpicker" >
                        <option value="">请选择</option>
                        <option value="1">建成</option>
                        <option value="2">封顶</option>
                        <option value="3">主体结构施工</option>
                        <option value="4">基础施工</option>
                        <option value="5">基坑施工</option>
                      </select>
                    </div>
                    <label class="col-sm-2 control-label">是否政府办公楼</label>
                    <div class="col-sm-3">
                      <select id="edit_fBudingGovernment" name="fBudingGovernment"  class="form-control selectpicker" >
                        <option value="">请选择</option>
                        <option value="1">是</option>
                        <option value="2">否</option>
                      </select>
                    </div>

                  </div>

                    <#--                    <div class="form-group">
                                          <label class="col-sm-2 control-label">创建操作人名称</label>
                                          <div class="col-sm-3">
                                            <input type="text" id="fOperatorName" name="fOperatorName"  readonly="readonly"  class="form-control">
                                          </div>
                                            <label class="col-sm-2 control-label">所属县区<span class="text-danger">*</span></label>
                                            <div class="col-sm-3">
                                                 <select id="fCountyArea" name="fCountyArea"  class="form-control selectpicker" >
                                                   <option value="">请选择</option>
                                                   <option value="1">历下区</option>
                                                   <option value="2">市中区</option>
                                                   <option value="3">槐荫区</option>
                                                   <option value="4">天桥区</option>
                                                   <option value="5">历城区</option>
                                                   <option value="6">长清区</option>
                                                   <option value="7">平阴县</option>
                                                   <option value="8">济阳县</option>
                                                   <option value="9">商河县</option>
                                                   <option value="10">章丘区</option>
                                                 </select>
                                            </div>
                                      </div>-->

                  <div class="form-group">
                    <label class="col-sm-2 control-label">联系人联系方式</label>
                    <div class="col-sm-3">
                      <input type="text" id="edit_fEngineeringContactsBgdh" name="fEngineeringContactsBgdh"  class="form-control isTel">
                    </div>
                    <label class="col-sm-2 control-label">建筑总人口</label>
                    <div class="col-sm-3">
                      <input type="text" id="edit_fBudingPopulation" name="fBudingPopulation"  class="form-control number">
                    </div>
                  </div>

                  <div class="form-group">
                    <label class="col-sm-2 control-label">监测面积</label>
                    <div class="col-sm-3">
                      <input type="text" id="edit_fMonitoringArea" name="fMonitoringArea"  class="form-control number">
                    </div>
                      <#--<label class="col-sm-2 control-label">系统软件</label>
                      <div class="col-sm-3">
                          <input type="text" id="fSystemSoftware" name="fSystemSoftware"  class="form-control">
                      </div>-->
                    <label class="col-sm-2 control-label">工程验收日期</label>
                    <div class="col-sm-3">
                      <input type="text" id="edit_fAcceptanceTime" name="fAcceptanceTime"  class="form-control Wdate" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss'})">
                    </div>
                  </div>

                    <#-- <div class="form-group">
                         <label class="col-sm-2 control-label">采集器型号</label>
                         <div class="col-sm-3">
                             <input type="text" id="fCollectorType" name="fCollectorType"  class="form-control">
                         </div>
                       <label class="col-sm-2 control-label">工程验收日期</label>
                       <div class="col-sm-3">
                         <input type="text" id="fAcceptanceTime" name="fAcceptanceTime"  class="form-control Wdate" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss'})">
                       </div>
                    </div>-->


                  <div class="form-group">

                    <label class="col-sm-2 control-label">描述</label>
                    <div class="col-sm-3">
                      <input type="text" id="edit_fDescribe" name="fDescribe"  class="form-control">
                    </div>
                  </div>

                    <div class="form-group m-t-sm">
                        <div class="col-sm-6 col-sm-push-5 display_flex">
                            <button class="btn btn-md btn-primary " type="submit">
                                <strong>确定</strong>
                            </button>
                            <button type="button" class="btn btn-white m-l-sm" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </form>
            </div>
            
        </div>
    </div>
</div>
<!----编辑结束--->
<!-- 上传模态框 -->
<div class="modal fade" id="import-addbudingType" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog" style="width: 35%;margin: 0 auto;">
        <div class="modal-content">        
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title editIcon">&nbsp;导入</h4>
            </div>
            <div class="modal-body">
            	<div class="row">
		            <div class="col-md-12">
		                <div class="alert alert-danger" style="font-size:13px;" role="alert">
		                <strong>注 意：</strong><br>
		                &emsp;&emsp;为保证您的数据正确导入，请先下载模板并在模板上输入所需导入数据（示例数据可删除）
		                </div>
		            </div>
	            </div>
				<div class="row"> 
		             <div class="col-xs-12">               
		                 <div class="input-group">
		                    <span class="input-group-addon">模板下载</span>
		                     <div class="input-radius" style="border: none;box-shadow: inherit;">
		                         <span class="input-group-addon">
		                         <a href="javascript:void(0);" id="btn_exp" onclick="energydataReport_basebudingmanager_budingType.btn_exp()">下载模板</a>
		                         </span>
		                     </div>
		                </div>
		             </div>                    
		        </div>				
				<div class="row" id="import-form-control-btn">
                    <div class="col-md-12" style="padding-top: 2vh;">
                        <form id="reportImpExcel" >
                            <input id="buildingInputfile"  type="file" name="file"  class="file-loading">
                        </form>
                    </div>
                </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" onclick="energydataReport_basebudingmanager_budingType.btn_import()">导入数据</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
            </div>            
        </div>
    </div>
</div>
<script type="text/javascript">
;
var energydataReport_basebudingmanager_budingType = (function($, window, document, undefined) {
	var _ctx = '${ctx}';
	//获取当前登录人 并附

	var username="${username}";
	var userId = "${userId}";

	$("#fOperatorName").val("${username}");

	//触发搜索的回车事件
 	$("#queryBudingType").focus(function(){
	  $(this).keydown(function (e){
	  if(e.which == "13"){
		 energydataReport_basebudingmanager_budingType.searchEnergyType({
			"keywords" : $("#budinginfo").val()
		});
	    } 
	  })
	});
	  
	//搜索点击事件
	$("#queryBudingType").click(function() {
		energydataReport_basebudingmanager_budingType.searchEnergyType({
			"keywords" : $("#budinginfo").val()
		});
	});
	
	//下拉框列表查询  查询数据中心名称
	function fNybhCollMethod(keywords){
		$.ajax({
        	type: "POST", 
        	url: _ctx + '/view/energydataReport/basebudingmanager/selectSjzxList', 
        	data:"",
        	beforeSend: function () { 
        		showLoad();	             
        	},
        success: function(data){
		 	var ops="<option value=''>请选择类型</option>";
		 	for(var i=0;i<data.list.length;i++){
			 	var obj=data.list[i];
			 	ops+='<option value="'+obj.ID+'">';
			 	ops+=obj.NAME;
			 	ops+='</option>';
		 	}
		 	if(keywords == 'add'){
		 		$("#fCenterId").append(ops);
		 	}else{
		 		$("#edit_fCenterId").append(ops);
		 	}
        }, 
        complete: function () {
        	hiddenLoad();
        },
        error:function(msg){
        	alert( "下拉框列表查询失败!" );  
        } 
    });
 	}
	
	//居中显示(新增)
 	$('#modal-form-addbudingType').on('show.bs.modal', function () {
		$("#fCenterId").empty();
		fNybhCollMethod("add");
	 	// 关键代码，如没将modal设置为 block，则$modala_dialog.height() 为零  
	    $(this).css('display', 'block');  
	    var modalHeight=$(window).height() / 2 - $('#modal-form-addbudingType .modal-dialog').height() / 2;  
	    $(this).find('.modal-dialog').css({  
	           'margin-top': modalHeight  
	    });
    })
    //居中显示（导入）
	$('#import-addbudingType').on('show.bs.modal', function () {
        $(this).css('display', 'block');  
        var modalHeight=$(window).height() / 2 - $('#import-addbudingType .modal-dialog').height() / 2;  
        $(this).find('.modal-dialog').css({  
            'margin-top': modalHeight  
        }); 
        
        initFileinput();
	})
	//添加验证
	var energyTypeValidator =$("#addBuding").validate({
		rules : {
      fBudingCode : {
        required : true
      },
      fCenterId : {
				required : true,
			},
			fBudingFunction : {
				required : true,
			},
			fCountyArea : {
				required : true,
			},
      fBudingNode : {
				required : true,
			},
			fBudingName : {
				required : true,
			},
			fBudingOwner : {
				required : true,
			},
			fBudingAddress : {
				required : true,
			},
			fBudingArea : {
				required : true,
			},
			fEntryTime : {
				required : true,
			},
			fDetectionTime : {
				required : true,
			},
      fBudingNode : {
				required : true,
			},
      fBudingLetter : {
          required : true,
      },
      fBudingDetection : {
          required : true,
      },
      fDateCompletion : {
          required : true,
      },
      fBudingLongitude : {
          required : true,
      },
      fBudingLatitude : {
          required : true,
      },
      fBudingLayersunder : {
          required : true,
      },
      fAirconditionerArea : {
          required : true,
      },
      fHeatingArea : {
          required : true,
      },
      fAirconditionerSystem : {
          required : true,
      },
      fHeatingSystem : {
          required : true,
      },
      fArchitecturalForm : {
          required : true,
      },
      fExteriorwallForm : {
          required : true,
      },
      fExteriorwallInsulationForm : {
          required : true,
      },
      fBudingExteriorType : {
          required : true,
      },
      fBudingGlassType : {
          required : true,
      },
      fMaterialType : {
          required : true,
      },
      fEngineeringConstruction : {
          required : true,
      },
      fBudingLayerson : {
        required : true,
      },
      fBenchmarking : {
        required : true,
      },
      fSchemeDesign : {
        required : true
      }
		},
		messages : {
      fBudingCode : {
        required : "请填写节点代码"
      },
      fCenterId : {
          required : "请选择数据中心名称",
      },
			fBudingFunction : {
				required : "请选择建筑功能"
			},
			fCountyArea : {
				required : "请选择所属县区"
			},
      fBudingNode : {
				required : "请填写建筑代码"
			},
			fBudingName : {
				required : "请填写建筑名称"
			},
			fBudingOwner : {
				required : "请填写建筑业主"
			},
			fBudingAddress : {
				required : "请填写建筑地址"
			},
			fBudingArea : {
				required : "请填写建筑面积㎡"
			},
			fEntryTime : {
				required : "录入时间不可为空"
			},
			fDetectionTime : {
				required : "开始检测日期不可为空"
			},
      fBudingNode : {
          required : "请填写行政区代码",
      },
      fBudingLetter : {
          required : "请填写建筑字母别名",
      },
      fBudingDetection : {
          required : "请选择建筑检测状态",
      },
      fDateCompletion : {
          required : "请填写竣工年份",
      },
      fBudingLongitude : {
          required : "请填写建筑坐标经度",
      },
      fBudingLatitude : {
          required : "请填写建筑坐标纬度",
      },
      fBudingLayerson : {
          required : "请填写地上建筑层数",
      },
      fBudingLayersunder : {
          required : "请填写地下建筑层数",
      },
      fAirconditionerArea : {
          required : "请填写空调面积",
      },
      fHeatingArea : {
          required : "请填写采暖面积",
      },
      fAirconditionerSystem : {
          required : "请选择空调系统形式",
      },
      fHeatingSystem : {
          required : "请选择采暖系统形式",
      },
      fArchitecturalForm : {
          required : "请选择建筑结构形式",
      },
      fExteriorwallForm : {
          required : "请选择外墙材料形式",
      },
      fExteriorwallInsulationForm : {
          required : "请选择外墙保温形式",
      },
      fBudingExteriorType : {
          required : "请选择建筑外窗类型",
      },
      fBudingGlassType : {
          required : "请选择建筑玻璃类型",
      },
      fMaterialType : {
          required : "请选择窗框材料类型",
      },
      fEngineeringConstruction : {
          required : "请填写监测工程实施单位",
      },
      fBenchmarking : {
        required : "请选择是否为标杆建筑",
      },
      fSchemeDesign : {
        required : "请填写监测方案设计单位"
      }
		},
		submitHandler : function(form) {
			addformEnergyType(form);
		}
	});
    //fileinput 上传插件初始化
   	function initFileinput(){
   		$("#buildingInputfile").fileinput({
               language: 'zh', //设置语言
               uploadUrl: '${ctx}/view/energydataReport/basebudingmanager/fileUpload',//上传请求路径
               allowedFileExtensions : ['xls', 'xlsx'],//接收的文件后缀,
               showUpload: true, //是否显示上传按钮
               showCaption: true,//是否显示标题
               browseClass: "btn btn-primary", //按钮样式
               maxFileSize:3072,//最大单个文件上传大小
               maxFileCount:1,//最大上传个数
               showUpload:false,//是否显示上传按钮
               showBrowse:true,//是否显示浏览按钮
               showPreview:false,//是否显示预览区域
               autoReplace:true,//是否自动替换
               showRemove:true,//是否显示移除按钮
               uploadExtraData:function(id,index){
                   return {"fold":"importExcel"};
               },//区分不同的模块：fold：文件夹
               //uploasExtraData:是把页面你想要往后退传的东西放(return)到域里面然后去后台去取 
               deleteUrl: "${ctx}/file/fileDelete?id="//删除文件的路径
           }).on("fileuploaded",function(event, data, previewId, index){
        	   var res = data.response;
               //status=='1' 成功
               if(res.status== '1'){
                   clearForm();//清空form表单
               }else{//失败导出错误信息
					console.log("错误信息:"+JSON.stringify(res.list))
					let jsonList = JSON.stringify(res.list);
                   //关闭遮罩层
                   hiddenLoad();
                   //销毁fileinput删除上传文件缓存
                   $("#buildingInputfile").fileinput("destroy");
                   //关闭模态框
                   $("#import-addbudingType").modal("hide");
                   swal({title: res.msg,type: "error",showCloseButton: true});
                   //status恒等于2才导出错误模板
                   if(res.status!='2'){
                       return false;
                   }
                   var alias = new Array();
                   // excel的列头
                   alias.push('行数');
                   alias.push('错误信息');
                   // 数据List中的Map的key值.
                   var names = new Array();
                   names.push('row');
                   names.push('errorMsg');
                   //报表名称
                   var exportName = "建筑基本信息错误报告";
                   //表名
                   var tablename = "bes_buding_information";
                   //数据json内容
                   var data = {
                       alias : JSON.stringify(alias),
                       names : JSON.stringify(names),
                       jsonList,jsonList,
                   };
                   //统一导出excel接口
                   var _url = "${ctx}/expExcel/exportExcel";
                   doExp(_url,exportName,"${ctx}",data);//导出excel并下载
               }
           }).on("filebatchuploadsuccess",function(){
               clearForm();//清空form表单
           });
   	}
   //清空上传文件表单form 关闭模态框 并提示
    function clearForm(){
    	energydataReport_basebudingmanager_budingType.searchEnergyType();
        $("#buildingInputfile").fileinput("destroy");//销毁fileinput删除上传文件缓存
    	$("#import-addbudingType").modal("hide");//关闭模态框
    	swal({title: "文件上传成功！",type: "success",showCloseButton: true});
    }
    //新增保存
	function addformEnergyType(form) {
	     $.ajax({
	       url: _ctx + "/view/energydataReport/basebudingmanager/add_BudingType",
	       type: "post",
	       data:$(form).serialize(),
	       success: function(data) {
	         if (data.status == '1') {
	        	 swal({
			        	title : data.msg,// 展示的标题
			   			text : "",// 内容
			   			type: "success",
			   			showCloseButton : false, // 展示关闭按钮
			   			allowOutsideClick : false,
			   			showConfirmButton : false,
			   			timer: 1000
			   		});
		            $('#modal-form-addbudingType').modal('hide');//关闭编辑窗口
		            //重新加载列表及分页
		            energydataReport_basebudingmanager_budingType.searchEnergyType()
	         } else{
	           swal( data.msg, "", "error");
	         }
	       },
	       error: function(data) {
	       	 swal( data.msg,"", "error");
	       }
	     });
 	}
 
    //居中显示（编辑）
 	$('#modal-form-editBudingType').on('show.bs.modal', function () {
        $(this).css('display', 'block');  
        var modalHeight=$(window).height() / 2 - $('#modal-form-editBudingType .modal-dialog').height() / 2;  
        $(this).find('.modal-dialog').css({  
            'margin-top': modalHeight  
        }); 
	})
    
    //关闭模态框清空表单值
	$("#modal-form-addbudingType").on('hidden.bs.modal',function(event) {
		$(this).find("input").val("");
		$('select').val("");
		$("#fOperatorName").val("${username}");
		energyTypeValidator.resetForm();
	});
	
	
	//删除数据
	$(document).on('click','#BudingTable button.delete',function() {
			var row = $(this).parents("tr")[0];
			var id = $(this).data("id");
			swal(
			{
			title : "您确定要删除吗?",
			text : "信息删除后将不可恢复!",
			type : "warning",
			showCancelButton : true,
			confirmButtonColor : "#1ab394",
			confirmButtonText : "确定",
			closeOnConfirm : false
			},
		function() {
			// row.className="animated bounceOut";
			setTimeout(function() {
			$.ajax({
				url : _ctx + "/view/energydataReport/basebudingmanager/del_BudingType",
				type : "post",
				data : {"id" : id
				},
		        beforeSend: function () { 
		        	showLoad();	             
		            },
				success : function(data) {
					if (data.status == '1') {
						swal({
				        	title : data.msg,// 展示的标题
				   			text : "",// 内容
				   			type: "success",
				   			showCloseButton : false, // 展示关闭按钮
				   			allowOutsideClick : false,
				   			showConfirmButton : false,
				   			timer: 1000
				   		});
					// window.location.href="${ctx }/view/user/user"; 
					//重新加载列表及分页
		            energydataReport_basebudingmanager_budingType.searchEnergyType()
				} else {
					swal("删除失败!",data.msg,"error");
				}
				},
	            complete: function () {
	            	hiddenLoad();
	            },
					error : function(data) {
					swal("删除失败!",data.msg,"error");
				}
				});
				}, 100)
				});
		});
	
 	//表单验证
	$("#editBuding").validate({
		rules : {
      fBudingCode : {
        required : true
      },
      fCenterId : {
          required : true,
      },
			fBudingFunction : {
				required : true,
			},
			fCountyArea : {
				required : true,
			},
      fBudingNode : {
				required : true,
			},
			fBudingName : {
				required : true,
			},
			fBudingOwner : {
				required : true,
			},
			fBudingAddress : {
				required : true,
			},
			fBudingArea : {
				required : true,
			},
			fEntryTime : {
				required : true,
			},
			fDetectionTime : {
				required : true,
			},
      fBudingNode : {
          required : true,
      },
      fBudingLetter : {
          required : true,
      },
      fBudingDetection : {
          required : true,
      },
      fDateCompletion : {
          required : true,
      },
      fBudingLongitude : {
          required : true,
      },
      fBudingLatitude : {
          required : true,
      },
      fBudingLayersunder : {
          required : true,
      },
      fAirconditionerArea : {
          required : true,
      },
      fHeatingArea : {
          required : true,
      },
      fAirconditionerSystem : {
          required : true,
      },
      fHeatingSystem : {
          required : true,
      },
      fArchitecturalForm : {
          required : true,
      },
      fExteriorwallForm : {
          required : true,
      },
      fExteriorwallInsulationForm : {
          required : true,
      },
      fBudingExteriorType : {
          required : true,
      },
      fBudingGlassType : {
          required : true,
      },
      fMaterialType : {
          required : true,
      },
      fEngineeringConstruction : {
          required : true,
      },
      fBenchmarking : {
        required : true,
      },
      fSchemeDesign : {
        required : true
      }
		},
		messages : {
      fBudingCode : {
        required : "请填写节点代码"
      },
      fCenterId : {
          required : "请选择数据中心名称",
      },
			fBudingFunction : {
				required : "请选择建筑功能"
			},
			fCountyArea : {
				required : "请选择所属县区"
			},
			fBudingCode : {
				required : "请填写建筑代码"
			},
			fBudingName : {
				required : "请填写建筑名称"
			},
			fBudingOwner : {
				required : "请填写建筑业主"
			},
			fBudingAddress : {
				required : "请填写建筑地址"
			},
			fBudingArea : {
				required : "请填写建筑面积㎡"
			},
			fEntryTime : {
				required : "录入时间不可为空"
			},
			fDetectionTime : {
				required : "开始检测日期不可为空"
			},
      fBudingNode : {
          required : "请填写行政区代码",
      },
      fBudingLetter : {
          required : "请填写建筑字母别名",
      },
      fBudingDetection : {
          required : "请选择建筑检测状态",
      },
      fDateCompletion : {
          required : "请填写竣工年份",
      },
      fBudingLongitude : {
          required : "请填写建筑坐标经度",
      },
      fBudingLatitude : {
          required : "请填写建筑坐标纬度",
      },
      fBudingLayerson : {
          required : "请填写地上建筑层数",
      },
      fBudingLayersunder : {
          required : "请填写地下建筑层数",
      },
      fAirconditionerArea : {
          required : "请填写空调面积",
      },
      fHeatingArea : {
          required : "请填写采暖面积",
      },
      fAirconditionerSystem : {
          required : "请选择空调系统形式",
      },
      fHeatingSystem : {
          required : "请选择采暖系统形式",
      },
      fArchitecturalForm : {
          required : "请选择建筑结构形式",
      },
      fExteriorwallForm : {
          required : "请选择外墙材料形式",
      },
      fExteriorwallInsulationForm : {
          required : "请选择外墙保温形式",
      },
      fBudingExteriorType : {
          required : "请选择建筑外窗类型",
      },
      fBudingGlassType : {
          required : "请选择建筑玻璃类型",
      },
      fMaterialType : {
          required : "请选择窗框材料类型",
      },
      fEngineeringConstruction : {
          required : "请填写监测工程实施单位",
      },
      fMonitoringArea : {
          required : "请填写监测面积",
      },
      fBenchmarking : {
        required : "请选择是否为标杆建筑",
      },
      fSchemeDesign : {
        required : "请填写监测方案设计单位"
      }
		},
  	 submitHandler: function(form) {
  		editEnergyType(form);
  	 }
 	});
 	
	//验证码在模态框出现前加载包含用户(可拖动)
	$("#modal-form-addbudingType,#modal-form-editBudingType").on('show.bs.modal', function(event) {
	    var button = $(event.relatedTarget);
	    var id = button.data("id");			//获取用户组编号
	    $(this).draggable({
	        handle: ".modal-header"     	// 只能点击头部拖动
	    });
	    $(this).css("overflow", "hidden"); // 防止出现滚动条，出现的话，你会把滚动条一起拖着走的
	});
 	//编辑
	function editEnergyType(form) {

   		$.ajax({
     		url: _ctx + "/view/energydataReport/basebudingmanager/edit_BudingType",
     		type: "post",
     		data:$(form).serialize(),
     		success: function(data) {
				if (data.status == '1') {
					swal({
			        	title : data.msg,// 展示的标题
			   			text : "",// 内容
			   			type: "success",
			   			showCloseButton : false, // 展示关闭按钮
			   			allowOutsideClick : false,
			   			showConfirmButton : false,
			   			timer: 1000
			   		});
             	setTimeout(function(){
	              	$('#modal-form-editBudingType').modal('hide');//关闭编辑窗口
	                energydataReport_basebudingmanager_budingType.searchEnergyType();
             	},1000)
         		} else{
             		swal("修改失败!", data.msg, "error");
         		}
    		},
    		error: function(data) {
         	 	swal("修改失败!", data.msg, "error");
    		}
   		});
	}
 	
 	//验证在模态框出现前加载编辑
 	$("#modal-form-editBudingType").on('show.bs.modal', function(event) {
 		$("#edit_fCenterId").empty();
		fNybhCollMethod("edit");
   		var button = $(event.relatedTarget);
   		var id = button.data("id");//获取datecenterID
   		$.ajax({
	       url: _ctx + "/view/energydataReport/basebudingmanager/getBudingType",
	       type: "post", 
	       data:{     
	 			"bh":id
	 		},
	       success: function(result) {
	       //edit 回显
	         $("#edit_fBudingId").val(result.data.fBudingId);
	         $("#edit_fBudingCode").val(result.data.fBudingCode);
	         $("#edit_fCenterId").val(result.data.fCenterId);
	         $("#edit_fBudingName").val(result.data.fBudingName);
	         $("#edit_fBudingNode").val(result.data.fBudingNode);
	         $("#edit_fBudingLetter").val(result.data.fBudingLetter);
	         $("#edit_fBudingOwner").val(result.data.fBudingOwner);
	         $("#edit_fBudingOwnerCompany").val(result.data.fBudingOwnerCompany);
	         $("#edit_fCountyArea").val(result.data.fCountyArea);
	         $("#edit_fBudingContact").val(result.data.fBudingContact);
	         $("#edit_fContactInformation").val(result.data.fContactInformation);
	         $("#edit_fBudingDetection").val(result.data.fBudingDetection);
	         $("#edit_fBudingAddress").val(result.data.fBudingAddress);
	         $("#edit_fBudingState").val(result.data.fBudingState);
	         $("#edit_fDateCompletion").val(result.data.fDateCompletion);
	         $("#edit_fBudingDate").val(result.data.fBudingDate);
	         $("#edit_fBudingLongitude").val(result.data.fBudingLongitude);
	         $("#edit_fBudingLatitude").val(result.data.fBudingLatitude);
	         $("#edit_fBudingLayerson").val(result.data.fBudingLayerson);
	         $("#edit_fBudingLayersunder").val(result.data.fBudingLayersunder);
	         $("#edit_fBudingHeight").val(result.data.fBudingHeight);
	         $("#edit_fBudingFunction").val(result.data.fBudingFunction);
	         $("#edit_fBudingArea").val(result.data.fBudingArea);
	         $("#edit_fAirconditionerArea").val(result.data.fAirconditionerArea);
	         $("#edit_fHeatingArea").val(result.data.fHeatingArea);
	         $("#edit_fBudingYnrs").val(result.data.fBudingYnrs);
	         $("#edit_fHeatingMode").val(result.data.fHeatingMode);
	         $("#edit_fCoolingMode").val(result.data.fCoolingMode);
	         $("#edit_fAirconditionerSystem").val(result.data.fAirconditionerSystem);
	         $("#edit_fHeatingSystem").val(result.data.fHeatingSystem);
	         $("#edit_fArchitecturalForm").val(result.data.fArchitecturalForm);
	         $("#edit_fExteriorwallForm").val(result.data.fExteriorwallForm);
	         $("#edit_fExteriorwallInsulationForm").val(result.data.fExteriorwallInsulationForm);
	         $("#edit_fBudingExteriorType").val(result.data.fBudingExteriorType);
	         $("#edit_fBudingGlassType").val(result.data.fBudingGlassType);
	         $("#edit_fMaterialType").val(result.data.fMaterialType);
	         $("#edit_fSavingStandard").val(result.data.fSavingStandard);
	         $("#edit_fSavingTransformation").val(result.data.fSavingTransformation);
	         $("#edit_fBudingGovernment").val(result.data.fBudingGovernment);
	         $("#edit_fBenchmarking").val(result.data.fBenchmarking);
	         $("#edit_fSchemeDesign").val(result.data.fSchemeDesign);
	         $("#edit_fOperatorName").val(result.data.fOperatorName);
	         $("#edit_fEngineeringConstruction").val(result.data.fEngineeringConstruction);
	         $("#edit_fEngineeringContacts").val(result.data.fEngineeringContacts);
	         $("#edit_fEngineeringContactsBgdh").val(result.data.fEngineeringContactsBgdh);
	         $("#edit_fBudingPopulation").val(result.data.fBudingPopulation);
	         $("#edit_fMonitoringArea").val(result.data.fMonitoringArea);
	         $("#edit_fSystemSoftware").val(result.data.fSystemSoftware);
	         $("#edit_fCollectorType").val(result.data.fCollectorType);
	         $("#edit_fEntryTime").val(result.data.fEntryTime);
	         $("#edit_fDetectionTime").val(result.data.fDetectionTime);
	         $("#edit_fAcceptanceTime").val(result.data.fAcceptanceTime);
	         $("#edit_fDesignOrganization").val(result.data.fDesignOrganization);
	         $("#edit_fDescribe").val(result.data.fDescribe);
	         $("#edit_fBudingCoefficient").val(result.data.fBudingCoefficient);
	         }
   		});  
 	});
 	return {
 		//查询				
 		searchEnergyType : function (datas) {
 			var keywords = $("#budinginfo").val();
 			$.ajax({
 				url : _ctx + '/view/energydataReport/basebudingmanager/getBudingList',
 				type : "post",
 				data : ({     
 					"keywords":keywords
 			 		}),
 		        beforeSend: function () { 
 		        	showLoad();	             
 		            },
 				success : function(data) {
 					$('#Buding_ibox').html(data);
                    //var three_node =$("#tree_zl_zlynjc").treeview('findNodes',[node[1].id,'id'])
                },
 	            complete: function () {
 	            	hiddenLoad();
 	            },
 				error : function(XMLHttpRequest,textStatus, errorThrown) {
 					toastr.error('', '查询失败');
 				}
 				});
 		},
 		//下载模板-- 提供两种思路 1.下载固定模板 可放到工作空间 2.生成模板下载
        btn_exp : function(){
        	//生成模板
        	var _url = "${ctx}/view/energydataReport/basebudingmanager/exportExcelModel";
            doExp(_url,"建筑基本信息模板","${ctx}","");//导出excel并下载  
        },
        //导入数据按钮
        btn_import:function(){
        	var filepath = $("#buildingInputfile").val();
        	if(filepath!=""){
            	$("#buildingInputfile").fileinput("upload");//上传方法
        	}else{
        		swal({title: "请上传数据文件!",type: "warning",showCloseButton: true});
        	}
        },
        //导出excel
        exportReport:function(){        	
            var _url = "${ctx}/view/energydataReport/basebudingmanager/exportExcel";
            doExp(_url,"建筑基本信息","${ctx}","");//导出excel并下载
        },
 		pageInit : function(){
 			energydataReport_basebudingmanager_budingType.searchEnergyType();
 		}
 	}
})(jQuery, window, document);
energydataReport_basebudingmanager_budingType.pageInit();
</script>