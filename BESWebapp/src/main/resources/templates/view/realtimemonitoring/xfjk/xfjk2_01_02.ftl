<style type="text/css">

</style>

<div class="Information_area"
	 style="height: calc(100% - 0px) !important; background: url('static/images/xfjk/xf.jpg'); position: relative;background-size: 100% 100%; background-repeat: no-repeat;">
    <div class="xfjk_row" >
    <!--开关 -->
        <div class="xfjk_top_left">
            <div class="xfjk_top_left_all">
                <ul>
                    <li>
                        <span>机组启停</span>
                    </li>
                    <li>
                        <select id="XFE1F_DDC2_M100" onchange="dataAnalysis_xfjk.setLampPoint(this)"> 
		                   <option value="255">开</option>
		                   <option value="0">关</option>
                        </select>
                    </li>
                    <!-- <li>
                        <span>水阀开度控制</span>
                    </li>
                    <li>
                        <select id="XFE1F_DDC2_M108" onchange="dataAnalysis_xfjk.setLampPoint(this)"> 
                           <option value="255">开</option>
                           <option value="0">关</option>
                        </select>
                    </li> -->
                    <li>
                        <span>风阀开</span>
                    </li>
                    <li>
                        <select id="XFE1F_DDC2_M200" onchange="dataAnalysis_xfjk.setLampPoint(this)"> 
                           <option value="255">开</option>
                           <option value="0">关</option>
                        </select>
                    </li>
                <!-- </ul>
                <ul> -->
                    <li>
                        <span>风阀关</span>
                    </li>
                    <li>
                        <select id="XFE1F_DDC2_M201" onchange="dataAnalysis_xfjk.setLampPoint(this)"> 
                           <option value="255">开</option>
                           <option value="0">关</option>
                        </select>
                    </li>
                </ul>
                <ul>
                    <li>
                        <span>水阀控制</span>
                    </li>
                    <li>
                        <input type="number" id="XFE1F_DDC2_M108" class="form-control number" style="width:56%;display: inline-block;" >%
                        <span class="input-group-btn" style="display: inline-block;">
                        <button type="button" class="btn btn-primary" ids="XFE1F_DDC2_M108" onclick="dataAnalysis_xfjk.setWaterPoint(this)">设置</button></span>
                    </li>
                </ul>
            </div>
        </div>
    <!--状态 -->
        <div class="xfjk_top_right">
            <div class="xfjk_top_right_all">
                <ul>
                    <li>
                        <span>新风温度</span>
                    </li>
                    <li>
                        <span id="XFE1F_DDC2_M1012"></span>
                    </li>
                    <li>
                        <span>新风湿度</span>
                    </li>
                    <li>
                        <span id="XFE1F_DDC2_M1013"></span>
                    </li>
                    <li>
                        <span>送风温度</span>
                    </li>
                    <li>
                        <span id="XFE1F_DDC2_M1011"></span>
                    </li>
                    <#--     <li>
                             <span>水阀开度反馈</span>
                         </li>
                         <li>
                             <span id="XFE1F_DDC2_M1014"></span>
                         </li> -->
                </ul>
                <ul>
                    <li>
                        <span>机组就地远程状态</span>
                    </li>
                    <li>
                        <span id="XFE1F_DDC2_M103"></span>
                    </li>
                    <li>
                        <span>机组运行状态</span>
                    </li>
                    <li>
                        <span id="XFE1F_DDC2_M104"></span>
                    </li>
                    <li>
                        <span>机组故障状态</span>
                    </li>
                    <li>
                        <span id="XFE1F_DDC2_M105"></span>
                    </li>
                    
                    <li>
                        <span>风机压差</span>
                    </li>
                    <li>
                        <span id="XFE1F_DDC2_M107"></span>
                    </li>
                    <li>
                        <span>防冻报警</span>
                    </li>
                    <li>
                        <span id="XFE1F_DDC2_M1015"></span>
                    </li>
                    <li>
                        <span>过滤网压差报警</span>
                    </li>
                    <li>
                        <span id="XFE1F_DDC2_M106"></span>
                    </li>
                    <li>
                        <span>风阀关到位</span>
                    </li>
                    <li>
                        <span id="XFE1F_DDC2_M2011"></span>
                    </li>
                    <li>
                        <span>风阀开到位</span>
                    </li>
                    <li>
                        <span id="XFE1F_DDC2_M2010"></span>
                    </li>
                </ul>
            </div>
        </div>
    </div>

</div>
