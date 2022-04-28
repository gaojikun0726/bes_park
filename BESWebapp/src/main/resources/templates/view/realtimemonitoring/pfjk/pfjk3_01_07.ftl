<style type="text/css">

</style>

<div class="Information_area"
	 style="height: calc(100% - 0px) !important; background: url('static/images/pfjk/pfjk.jpg'); position: relative;background-size: 100% 100%; background-repeat: no-repeat;">
    <div class="pfjk_row" >
       
        <div class="pfjk_top_left">
            <div class="pfjk_top_left_all">
                <ul>
                    <li>
                        <span>机组启停1</span>
                    </li>
                    <li>
                        <select id="PFSA1F_DDC11_M100" onchange="dataAnalysis_pfjk.setLampPoint(this)"> 
		                   <option value="255">开</option>
		                   <option value="0">关</option>
                        </select>
                    </li>
                </ul>
            </div>
        </div>
        
        <div class="pfjk_top_right">
            <div class="pfjk_top_right_all">
                <ul>
                    <li>
                        <span>机组运行状态1</span>
                    </li>
                    <li>
                        <span id="PFSA1F_DDC11_M1010"></span>
                    </li>   
                    <li>
                        <span>机组故障状态1</span>
                    </li>
                    <li>
                        <span id="PFSA1F_DDC11_M109"></span>
                    </li>
                    <li>
                        <span>机组就地远程状态1</span>
                    </li>
                    <li>
                        <span id="PFSA1F_DDC11_M108"></span>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
