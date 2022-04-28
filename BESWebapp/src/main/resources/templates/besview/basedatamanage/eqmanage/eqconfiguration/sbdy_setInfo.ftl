 <!---设置时间信息开始----->
<div class="modal fade" id="modal-form-setDate" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content" >
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 id="titText" class="modal-title">设置时间</h4>
            </div>
            <div class="modal-body">
                <div  id="setDate" class="form-horizontal">
                    <div class="form-group"> <label id="dateLable" class="col-sm-3 control-label">设置日期<span class="text-danger">*</span></label>
                         <div class='input-group' id='date' style="width: 250px;">
                            <input id="selDate" type='text' class="form-control " onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',firstDayOfWeek:1,readOnly:true})" placeholder="请选择日期"/>
                             
                         </div>
                    </div>
                    <div class="form-group"> <label id="timeLable" class="col-sm-3 control-label">设置时间<span class="text-danger">*</span></label>
                         <div class='input-group' id='time' style="width: 250px;">
                            <input type='text' class="form-control" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'HH:mm:ss',firstDayOfWeek:1,readOnly:true})" placeholder="请选择时间"/>
                             
                         </div>
                    </div>
                    <div class="form-group m-t-sm" >
                        <div class="col-sm-6 col-sm-push-3">
                            <button class="btn btn-md btn-primary" onclick="basedatamanage_eqmanage_eqconfiguration_sbdy_setInfo.confirm();"><strong>确定</strong></button>
                            <button class="btn btn-white m-l-sm" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!---设置时间信息结束----->
<script src="${ctx}/static/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">	
;
	var basedatamanage_eqmanage_eqconfiguration_sbdy_setInfo = (function($,window, document, undefined) {
		 return {
			 show_date_modal : function(showFlg) {//showFlg(set:设置时间  get:获取时间)
				 if(showFlg=='set'){		 
					$('#titText').text('设置时间');
					$('#dateLable').text('设置日期 ')
					.append('<span class="text-danger">*</span>');
					$('#timeLable').text('设置时间 ')
					.append('<span class="text-danger">*</span>');
				}else{
					$('#titText').text('获取时间');
					$('#dateLable').text('日期 ')
					.append('<span class="text-danger">*</span>');
					$('#timeLable').text('时间 ')
					.append('<span class="text-danger">*</span>');
				} 
				$('#modal-form-setDate').modal('show');
			 },
			 confirm : function(){
				 $('#modal-form-setDate').modal('hide');
				 alert('确定');
			 },
		}
	})(jQuery, window, document);
</script>