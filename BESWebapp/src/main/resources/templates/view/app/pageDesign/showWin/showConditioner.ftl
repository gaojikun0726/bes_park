<#--低档温控器界面html内容-->
<div id="low_conditioner_content" style="display: none">
    <img class="bg_img grayBg" src="${ctx}/static/pageDesign/images/air_conditioner/low/gray_bg.png" style="display: none">
    <img class="bg_img blueBg" src="${ctx}/static/pageDesign/images/air_conditioner/low/blue_bg.png">
    <div class="dataDiv">
        <input class="switchSysname" type="hidden">
        <input class="speedSysname" type="hidden">
        <input class="modeSysname" type="hidden">
        <input class="lockSysname" type="hidden">
        <input class="indoorTemperatureSysname" type="hidden">
        <input class="setTemperatureSysname" type="hidden">
        <input class="open_time" type="hidden">
        <input class="close_time" type="hidden">
    </div>
    <div class="screenDiv">
        <div class="topDiv">
            <div class="topItem high_speed">
                <img src="${ctx}/static/pageDesign/images/air_conditioner/low/high_speed.png">
                <span>高速</span>
            </div>
            <div class="topItem middle_speed">
                <img src="${ctx}/static/pageDesign/images/air_conditioner/low/middle_speed.png">
                <span>中速</span>
            </div>
            <div class="topItem low_speed">
                <img src="${ctx}/static/pageDesign/images/air_conditioner/low/low_speed.png">
                <span>低速</span>
            </div>
            <div class="topItem cooling">
                <img src="${ctx}/static/pageDesign/images/air_conditioner/low/cooling.png">
                <span>制冷</span>
            </div>
            <div class="topItem heating">
                <img src="${ctx}/static/pageDesign/images/air_conditioner/low/heating.png">
                <span>制热</span>
            </div>
            <div class="topItem energy">
                <img src="${ctx}/static/pageDesign/images/air_conditioner/low/energy.png">
                <span>节能</span>
            </div>
        </div>
        <div class="middleDiv">
            <div class="indoor_temperature">
                <div class="indoor_text">室内温度</div>
                <div class="indoor_num">
                    <#--00.0-->
                </div>
                <div class="indoor_unit">℃</div>
            </div>
            <div class="set_remperature">
                <div class="set_text">设定温度</div>
                <div class="set_num">
                    <#--00.0-->
                </div>
                <div class="set_unit">℃</div>
                <div class="logo">
                    <img src="${ctx}/static/pageDesign/images/air_conditioner/low/logo.png">
                </div>
            </div>
        </div>

        <div class="bottomDiv">
            <div class="topItem auto">
                <img src="${ctx}/static/pageDesign/images/air_conditioner/low/auto.png">
                <span>自动</span>
            </div>

            <div class="topItem network">
                <img src="${ctx}/static/pageDesign/images/air_conditioner/low/network.png">
                <span>网络</span>
            </div>
            <#--<div class="topItem lock"  onclick="javascript:showConditioner.changeSdStatus();">-->
            <#--&lt;#&ndash; <img src="${ctx}/static/pageDesign/images/air_conditioner/middle/sd.png">-->
                <#--<span>锁定</span>&ndash;&gt;-->
            <#--</div>-->
            <div class="topItem lock">
                <img src="${ctx}/static/pageDesign/images/air_conditioner/low/lock.png">
                <span>锁定</span>
            </div>
            <div class="topItem unlock">
                <img src="${ctx}/static/pageDesign/images/air_conditioner/low/unlock.png">
                <span>未锁定</span>
            </div>
        <#--定时时间-->
            <div class="timingDiv">
                <div class="timing_num">
                <#--00:00-->
                </div>
                <div class="timing_open_num" style="display: none">
                    <#--00:00-->
                </div>
                <div class="timing_close_num" style="display: none">
                <#--00:00-->
                </div>
                <div class="timing_open">定时开</div>
                <div class="timing_close">定时关</div>
            </div>
        </div>
    </div>
    <div class="buttonDiv">
        <div class="btnItem modeBtn"></div>
        <div class="btnItem speedBtn"></div>
        <div class="btnItem upBtn"></div>
        <div class="btnItem downBtn"></div>
        <div class="btnItem switchBtn"></div>
    </div>

</div>

<#--图标形式温控器弹窗-->
<div id="conditioner_win" style="display: none" >
    <div class="design_low_conditioner icon_win_img design_initial_position" style = "top: 0vw;left: 0vh;"></div>
</div>
<#--中档温控器界面html内容-->
  <div id="middle_conditioner_content" style="display: none;">
      <img class="bg_img" src="${ctx}/static/pageDesign/images/air_conditioner/middle/temp_box.jpg">
      <div class="dataDiv">
          <input class="switchSysname" type="hidden">
          <input class="speedSysname" type="hidden">
          <input class="modeSysname" type="hidden">
          <input class="lockSysname" type="hidden">
          <input class="indoorTemperatureSysname" type="hidden">
          <input class="setTemperatureSysname" type="hidden">
          <input class="energySysname" type="hidden">
          <input class="open_time" type="hidden">
          <input class="close_time" type="hidden">
      </div>
      <div class="screenDiv">
          <div class="topDiv">
          <#--顶部图标-->
              <div class="topItem network">
                  <img src="${ctx}/static/pageDesign/images/air_conditioner/middle/communicate.png">
                  <span>通讯</span>
              </div>
              <div class="topItem cooling">
                  <img src="${ctx}/static/pageDesign/images/air_conditioner/middle/cool.png">
                  <span>制冷</span>
              </div>
              <div class="topItem heating">
                  <img src="${ctx}/static/pageDesign/images/air_conditioner/middle/warm111.png">
                  <span>制热</span>
              </div>
              <div class="topItem energy">
                  <img src="${ctx}/static/pageDesign/images/air_conditioner/middle/selffModule.png">
                  <span>节能</span>
              </div>
              <div class="topItem manual">
                  <img src="${ctx}/static/pageDesign/images/air_conditioner/middle/handModule.png">
                  <span>手动</span>
              </div>
              <div class="topItem lock">
                  <img src="${ctx}/static/pageDesign/images/air_conditioner/middle/sd.png">
                  <span>锁定</span>
              </div>
              <div class="topItem unlock">
                  <img src="${ctx}/static/pageDesign/images/air_conditioner/middle/unsd.png">
                  <span>未锁定</span>
              </div>
              <#--<div class="topItem lock" onclick="javascript:showMiddleConditioner.changeSdStatus();">-->
              <#--&lt;#&ndash;<img src="${ctx}/static/pageDesign/images/air_conditioner/middle/sd.png">-->
                  <#--<span>锁定</span>&ndash;&gt;-->
              <#--</div>-->
          </div>
          <div class="middleDiv">
              <div class="leftDiv">
                  <div class="indoorDiv">
                      <div class="temperature_text indoor_text">室内温度</div>
                      <div class="indoor_icon">
                          <img src="${ctx}/static/pageDesign/images/air_conditioner/middle/snwd.png">
                      </div>
                      <div class="indoor_integer">00</div>
                      <div class="num_right indoor_decimal">.0</div>
                  </div>
                  <div class="setDiv">
                      <div class="temperature_text set_text">设定温度</div>
                      <div class="set_icon">
                          <img src="${ctx}/static/pageDesign/images/air_conditioner/middle/thermometer.png">
                      </div>
                      <div class="set_integer">00</div>
                      <div class="num_right set_decimal">.0</div>
                  </div>
                  <div class="num_right temperature_unit">℃</div>
              </div>

              <div class="rightDiv">
                  <div class="speed_text">
                      <div class="auto">自动</div>
                      <div class="low_speed">低速</div>
                      <div class="middle_speed">中速</div>
                      <div class="high_speed">高速</div>
                  </div>
                  <div class="speed_icon">
                  <#--风速图标-->
                      <div class="high_icon">
                          <img src="${ctx}/static/pageDesign/images/air_conditioner/middle/high.gif">
                      </div>
                      <div class="middle_icon">
                          <img src="${ctx}/static/pageDesign/images/air_conditioner/middle/middle.gif">
                      </div>
                      <div class="low_icon">
                          <img src="${ctx}/static/pageDesign/images/air_conditioner/middle/slow2.gif">
                      </div>
                  </div>
                  <div class="timeDiv">
                  <#--时间-->
                      <div class="clock">
                          <img src="${ctx}/static/pageDesign/images/air_conditioner/middle/clock4.png">
                      </div>
                      <div class="timing_num current_time">
                          <#--20:29-->
                      </div>
                      <div class="timing_num timing_open_num" style="display: none">
                          <div class="hour">00</div>
                          <div class="separator">:</div>
                          <div class="minute">00</div>
                      <#--00:00-->
                      </div>
                      <div class="timing_num timing_close_num" style="display: none">
                          <div class="hour">00</div>
                          <div class="separator">:</div>
                          <div class="minute">00</div>
                      <#--00:00-->
                      </div>
                      <div class="timing_text on">ON</div>
                      <div class="timing_text off">OFF</div>
                  </div>
                  <div class="energyDiv">
                  <#--能耗-->
                      <div class="energy_text">能耗</div>
                      <div class="energy_num ahead">0000</div>
                      <#--<div class="energy_num behind">00</div>-->
                      <div class="energy_unit">kWh</div>
                  </div>
              </div>
          </div>
          <div class="bottomDiv">
          <#--底部按键-->
              <div class="bottomItem modeBtn">
                  <img src="${ctx}/static/pageDesign/images/air_conditioner/middle/menu.png">
              </div>
              <div class="bottomItem speedBtn">
                  <img src="${ctx}/static/pageDesign/images/air_conditioner/middle/moduleSel.png">
              </div>
              <div class="bottomItem switchBtn">
                  <img src="${ctx}/static/pageDesign/images/air_conditioner/middle/on-off.png">
              </div>
              <div class="bottomItem upBtn">
                  <img src="${ctx}/static/pageDesign/images/air_conditioner/middle/high.png">
              </div>
              <div class="bottomItem downBtn">
                  <img src="${ctx}/static/pageDesign/images/air_conditioner/middle/low.png">
              </div>
          </div>
      </div>
  </div>
<#--图标形式温控器弹窗-->
<div id="middle_conditioner_win" style="display: none" class="designWin">
    <div class="design_middle_conditioner icon_win_img design_initial_position"  style ="top: 0vw;left: 0vh;"></div>
</div>
