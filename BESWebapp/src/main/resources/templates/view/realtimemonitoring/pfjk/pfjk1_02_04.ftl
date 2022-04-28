<style type="text/css">

</style>

<div class="Information_area"
	 style="height: calc(100% - 0px) !important; background: url('static/images/pfjk/pfjk.jpg'); position: relative;background-size: 100% 100%; background-repeat: no-repeat;">
    <div class="pfjk_row" >
      
        <div class="pfjk_top_left">
            <div class="pfjk_top_left_all">
                <ul>
                    <li>
                        <span>机组启停</span>
                    </li>
                    <li>
                        <select id="PFY2F_DDC4_M100" onchange="dataAnalysis_pfjk.setLampPoint(this)">  
		                   <option value="255">开</option>
		                   <option value="0">关</option>
                        </select>
                    </li>
                    <li>
                        <span>水阀</span>
                    </li>
                    <li>
                        <input type="number" id="PFY2F_DDC4_M108" class="form-control number" style="width:56%;display: inline-block;" >℃
                        <span class="input-group-btn" style="display: inline-block;">
                        <button type="button" class="btn btn-primary" ids="PFY2F_DDC4_M108" onclick="dataAnalysis_pfjk.setWaterPoint(this)">设置</button></span>
                    </li>

                </ul>
            </div>
        </div>
     
        <div class="pfjk_top_right">
            <div class="pfjk_top_right_all">
                <ul>
                    <li>
                        <span>排风温度</span>
                    </li>
                    <li>
                        <span id="PFY2F_DDC4_M1012"></span>
                    </li>
                    <li>
                        <span>排风湿度</span>
                    </li>
                    <li>
                        <span id="PFY2F_DDC4_M1013"></span>
                    </li>
                    <li>
                        <span>送风温度</span>
                    </li>
                    <li>
                        <span id="PFY2F_DDC4_M1011"></span>
                    </li>
                     <li>
                        <span>水阀开度反馈</span>
                    </li>
                    <li>
                        <span id="PFY2F_DDC4_M1014"></span>
                    </li> 
                </ul>
            </div>
        </div>
    </div>
</div>
