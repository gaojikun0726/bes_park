<#--
 设备配置
王红杰
-->
<style>
  .information_left_deviceExceptionLog{
    /* margin-top: -10px; */
    height: 95% !important;
    border-bottom: solid 1px #366c90;
    border-top: solid 1px #366c90;
    border-right: solid 1px #366c90;
  }
  .leftarea_deviceExceptionLog{
    float: left;
    width: 100%;
    /* margin-left: -10px; */
    position: relative;
    box-sizing: border-box;
    height: auto;
    border-right: solid 1px rgb(54, 108, 144);
    /* padding: 0px 0px 0px 10px; */
    /* background-color: #ffffff; */
  }

  .tabulator .tabulator-row.tabulator-selected {
    background: #cee3f7 !important;
  }
</style>
<style type="text/css">
  .treeSelect .layui-select-title span {
    height: 25px;
  }
  .layui-form-select dl{
    padding: 0;
    top:30px;

  }
  .layui-tree-entry {
    height: 30px;
  }


</style>

<div class="information-model">
		<span class="Subtitle">
			<i class="fa fa-th-list" aria-hidden="true"></i> 设备异常列表>>>
		</span>

  <div  style = "display:inline-block ;margin-left: 15px;">设备类型 :</div>
  <div id='deviceExceptionLog_sblx' style = "display:inline-block ;margin-left: 15px;"></div>

  <#--区域-->
  <div style = "display:inline-block ;margin-left: 15px;">区域:</div>
  <div class="layui-unselect layui-form-select treeSelect" style = "display:inline-block ;margin-left: 15px;">
    <div class="layui-select-title">
      <span class="layui-input layui-unselect" id="positionTree">选择区域</span>
      <input type="hidden" id="exceptionLogPositionId" name="exceptionLogPositionId">
      <i class="layui-edge"></i>
    </div>
    <dl class="layui-anim layui-anim-upbit">
      <dd style="padding-bottom: 10px;line-height: 50px">
        <ul id="treePosition"></ul>
      </dd>
    </dl>
  </div>

  <button class='btn btn-white btn-sm delete' onclick="deviceExceptionLog.deleteAll()" style = "display:inline-block ;margin-left: 15px;">
    <i class='fa fa-trash'></i>  批量删除
  </button>

  <!-- 搜索框 -->
  <#--<div class="zc_search find">
    <input type="text" class="find-style" id="DeviceExceptionLogKeywords" name="DeviceExceptionLogKeywords"
           placeholder="设备名称">
    <button id="querydeviceExceptionLog" onclick="deviceExceptionLog.search()">
      <i class="fa fa-search" aria-hidden="true"></i>
    </button>
  </div>-->
</div>


<!---分页列表----->
<div class="ibox leftarea_deviceExceptionLog information_left_deviceExceptionLog" id="deviceExceptionLogPageContainer"   ></div>




<script type="text/javascript">
  ;
  var deviceExceptionLog = (function ($, window, document, undefined)
  {
    var _ctx = '${ctx}';
    var deviceType_id=[];//设备类型下拉表id
    var deviceType_val=[];//设备类型下拉表值
    var deviceTypeId = '';


    $(function () {
      initPositionTree()
    });
    function initPositionTree(){
      $.ajax({
        url: _ctx + '/view/sysmanage/interfaceconfig/deviceConfiguration/queryPosition',
        type: "post",
        success: function (result) {
          layui.config({
            base: 'static/layui/' //静态资源所在路径
          });
          layui.use(['layer', 'tree', 'form'], function () {
            var $ = layui.jquery,
                    layer = layui.layer,
                    form = layui.form,
                    positionTree = layui.tree;
            positionTree.render({
              elem: "#treePosition", //指定元素
              // showLine: false,
              data: [{
                id:'',
                title:'智能交通产业园',
                children:result.list
              }],
              click: function(node) { //点击节点回调
                var othis = $($(this)[0].elem).parents(".layui-form-select");
                othis.removeClass("layui-form-selected").find(".layui-select-title span").html(node.data.title).end().find("input:hidden[name='exceptionLogPositionId']").val(node.data.id);
                refreshTable();
              }
            });

            $(".treeSelect").on("click", ".layui-select-title", function(e) {
              $(".layui-form-select").not($(this).parents(".layui-form-select")).removeClass("layui-form-selected");
              $(this).parents(".treeSelect").toggleClass("layui-form-selected");
              layui.stope(e);
            }).on("click", "dl i", function(e) {
              layui.stope(e);
            });
            $(document).on("click", function(e) {
              $(".layui-form-select").removeClass("layui-form-selected");
            });

            // 获取选中值
            // var positionId= $("input[name='positionId']").val();
            // 默认选中
            var checkNode = {
              id: '',
              name: '智能交通产业园'
            }
            var othis = $(".layui-form-select");
            othis.removeClass("layui-form-selected").find(".layui-select-title span").html(checkNode.name).end().find("input:hidden[name='positionId']").val(checkNode.id);
            form.render();

          });
        },

        error: function (result)
        {
          console.log(result)
        }
      });

    }
    //设备类型下拉框
    function  get_deviceTypetree() {

      $.issp_ajax({
        type: "POST",
        url: _ctx + '/view/sysmanage/interfaceconfig/deviceConfiguration/getDeviceType',
        async:false,
        success: function (data) {

          if (data.hasOwnProperty("list"))
          {
            if(data.list.length > 0 )
            {
              //清空保存的值再赋值
              deviceType_id.length = 0
              deviceType_val.length = 0
              deviceType_id.push('')
              deviceType_val.push('全部')

              for (var i = 0; i < data.list.length; i++) {
                var obj = data.list[i];
                deviceType_id.push(obj.id);
                deviceType_val.push(obj.name);
              }

              // yqbh = data.list[0].f_yqbh;
              // basedatamanage_enegrycollectionmanage_collMethod.zzjg_tree(yqbh);
              deviceType_select('#deviceExceptionLog_sblx',deviceType_id,deviceType_val);
            }
          }
          else
          {
            deviceType_id.push("0");
            deviceType_val.push("全部");
            deviceType_select('#deviceExceptionLog_sblx',deviceType_id,deviceType_val);

          }


        },
        error: function (data) {
          swal( data.msg,"", "error");
        }
      });
    }
    //所属园区selected
    function deviceType_select(id,idArray,valArray){
      //选择设备类型下拉列表 空间选择
      $(id).ISSPSpinnerBox({
        width:'9vw',//下拉列表宽度
        height: '2.9vh',//下拉列表高度
        margLeft:'0px',//margin-left属性
        isHasData:true,
        idArray:idArray,//id
        valArray:valArray,//txt
        isNoSelectedText:false, //是否设置未选中提示文本
        liveSearch:false,
        callBack: refreshTable,
      });
    }


    function refreshTable(param)
    {
      if (typeof (param) != 'undefined') {
        if (param.id != null) {
          deviceTypeId = param.id
        }
      }
      let pageNum = null;
      if (typeof deviceExceptionLogPage != 'undefined') {
        pageNum = deviceExceptionLogPage.getPageNum();
      }
      let positionId = $('#exceptionLogPositionId').val();

      getPagingPage({param,deviceTypeId,pageNum,positionId}, function (page)
      {
        showPagingPage('deviceExceptionLogPageContainer', page);
      });
    }

    // 获取分页页面
    function getPagingPage(param, callback)
    {

      if (typeof callback !== 'function')
      {
        return;
      }

      param = param || {};

      $.ajax({
        url: _ctx + '/view/sysmanage/interfaceconfig/deviceExceptionLog/queryPage',
        type: "post",
        data: param,
        success: function (result)
        {
          callback(result);
        },

        error: function (result)
        {
          console.log(result)
        }
      });

    }

    // 显示分页
    function showPagingPage(pageId, page)
    {
      if (!page || !pageId)
      {
        return;
      }

      $('#' + pageId).html(page);
    }
    function deleteAll() {
      swal
      (
              {
                title: '您确定要批量删除吗?',
                text: '信息批量删除后将不可恢复!',
                type: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#1ab394',
                confirmButtonText: '确定',
                closeOnConfirm: false
              }, function ()
              {
                deleteByList(deviceTypeId,function (result)
                {
                  var status = result && result.status;

                  if(status === "1"){
                    swal({
                      title : "批量删除成功",// 展示的标题
                      text : "",// 内容
                      type: "success",
                      showCloseButton : false, // 展示关闭按钮
                      allowOutsideClick : false,
                      showConfirmButton : false,
                      timer: 1000
                    });

                    refreshTable();


                  }else {

                    swal({
                      title : "批量删除失败",// 展示的标题
                      text : result.msg,// 内容
                      type: "error",
                      showCloseButton : false, // 展示关闭按钮
                      allowOutsideClick : false,
                      showConfirmButton : false,
                      timer: 1000
                    });
                  }
                })
              }
      );
    }

    function deleteByList(deviceTypeId,callback){

      $.ajax({
        url: _ctx + '/view/sysmanage/interfaceconfig/deviceExceptionLog/deleteAll',
        type: "post",
        data: ({
          deviceTypeId : deviceTypeId,
          positionId : $('#exceptionLogPositionId').val()
        }),
        success: function (result) {

          callback(result);

        },
        error: function (result) {
          swal("批量删除失败!",null,"error");
          console.warn(result)
        }

      })
    }


    /**
     * 删除点击事件
     *
     */
    $(document).on('click', '#deviceExceptionLogTable button.delete', function ()
    {
      var id = $(this).data('id');

      swal
      (
          {
            title: '您确定要删除吗?',
            text: '信息删除后将不可恢复!',
            type: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#1ab394',
            confirmButtonText: '确定',
            closeOnConfirm: false
          }, function ()
          {
            deletes(id, function (result)
            {
              var status = result && result.status;

              if(status === "1"){
                swal({
                  title : "删除成功",// 展示的标题
                  text : "",// 内容
                  type: "success",
                  showCloseButton : false, // 展示关闭按钮
                  allowOutsideClick : false,
                  showConfirmButton : false,
                  timer: 1000
                });

                refreshTable();

                if (typeof deviceFunctionPoint != 'undefined') {
                  deviceFunctionPoint.pageInit();
                }

              }else {

                swal({
                  title : "删除失败",// 展示的标题
                  text : result.msg,// 内容
                  type: "error",
                  showCloseButton : false, // 展示关闭按钮
                  allowOutsideClick : false,
                  showConfirmButton : false,
                  timer: 1000
                });
              }
            })
          }
      );

    });



    // 删除
    function deletes(id, callback) {

      if (!id) {
        console.warn('无效参数！');
        return;
      }

      $.ajax({
        type    : "POST",
        url     : _ctx + "/view/sysmanage/interfaceconfig/deviceExceptionLog/delete",
        dataType: "json",
        data: {
          id : id,
        },
        success: function (result) {

          callback(result);

        },
        error: function (result) {
          swal("删除失败!",null,"error");
          console.warn(result)
        }
      });

    }




    // 根据条件搜索
    function search()
    {
      var keywords = $('#DeviceExceptionLogKeywords').val();

      refreshTable(keywords);
    }

    function getDeviceExceptionLogDeviceType() {
      $.ajax({
        type: "POST",
        dataType: 'json',
        url: _ctx + '/view/sysmanage/interfaceconfig/deviceExceptionLog/getDeviceType',
        success: function(result){

          var ops="<option value=''>请选择设备类型</option>";
          for(var i=0;i<result.list.length;i++){
            var obj=result.list[i];
            ops+='<option value="'+obj.id+'">';
            ops+=obj.name;
            ops+='</option>';
          }
          $("#deviceExceptionLogDeviceTypeAdd").append(ops);
          $("#deviceExceptionLogDeviceTypeEdit").append(ops);
        }
      });
    }





    return {

      search,
      deleteAll,
      pageInit: function () {
        // getDeviceConfigurationDeviceType();
        get_deviceTypetree();
      }

    }

  })(jQuery, window, document);
  deviceExceptionLog.pageInit();
</script>