<style type="text/css">

</style>

<div class="Information_area"
	 style="height: calc(100% - 0px) !important; background: url('static/images/pfjk/pfjk.jpg'); position: relative;background-size: 100% 100%; background-repeat: no-repeat;">
    <div class="pfjk_row" >
    <!--开关 -->
        <div class="pfjk_top_left">
            <div class="pfjk_top_left_all">
                <ul>
                    <li>
                        <span>机组启停1</span>
                    </li>
                    <li>
                        <select id="PFWW_DDC11_M100" onchange="dataAnalysis_pfjk.setLampPoint(this)"> -->
		                   <option value="255">开</option>
		                   <option value="0">关</option>
                        </select>
                    </li>
                    <li>
                        <span>机组启停2</span>
                    </li>
                    <li>
                        <select id="PFWW_DDC11_M101" onchange="dataAnalysis_pfjk.setLampPoint(this)"> -->
                            <option value="255">开</option>
                            <option value="0">关</option>
                        </select>
                    </li>
                    <li>
                        <span>机组启停3</span>
                    </li>
                    <li>
                        <select id="PFWW_DDC11_M102" onchange="dataAnalysis_pfjk.setLampPoint(this)"> -->
                            <option value="255">开</option>
                            <option value="0">关</option>
                        </select>
                    </li>
                </ul>
            </div>
        </div>
    <!--状态 -->
        <div class="pfjk_top_right">
            <div class="pfjk_top_right_all">
                <ul>
                    <li>
                        <span>机组运行状态1</span>
                    </li>
                    <li>
                        <span id="PFWW_DDC11_M104"></span>
                    </li> 
                    <li>
                        <span>机组故障状态1</span>
                    </li>
                    <li>
                        <span id="PFWW_DDC11_M105"></span>
                    </li>
                    <li>
                        <span>机组就地远程状态1</span>
                    </li>
                    <li>
                        <span id="PFWW_DDC11_M103"></span>
                    </li>
                </ul>
                <ul>
                    <li>
                        <span>机组运行状态2</span>
                    </li>
                    <li>
                        <span id="PFWW_DDC11_M1011"></span>
                    </li> 
                    <li>
                        <span>机组故障状态2</span>
                    </li>
                    <li>
                        <span id="PFWW_DDC11_M1012"></span>
                    </li>
                    <li>
                        <span>机组就地远程状态2</span>
                    </li>
                    <li>
                        <span id="PFWW_DDC11_M106"></span>
                    </li>
                </ul>
                <ul>
                   <li>
                        <span>机组运行状态3</span>
                    </li>
                    <li>
                        <span id="PFWW_DDC11_M1014"></span>
                    </li> 
                    <li>
                        <span>机组故障状态3</span>
                    </li>
                    <li>
                        <span id="PFWW_DDC11_M1015"></span>
                    </li>
                    <li>
                        <span>机组就地远程状态3</span>
                    </li>
                    <li>
                        <span id="PFWW_DDC11_M1013"></span>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
