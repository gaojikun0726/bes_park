    <#--<div class="navissp-top">-->
	    <#--<div id="mini" style="border-bottom: 1px solid rgba(255,255,255,.1);height: 4.4vh;display: flex;align-items: center;"><img src="images/mini.png" style="height: 43px;" ></div>-->
	<#--</div>-->
	<ul id="side-menu" level="1">
		<#if menu_session?? && (menu_session?size > 0) >
			<#list menu_session as one>  
				<li class="navissp-item" id='left-${one.id}' menuId='${one.id}' menuTitle='${one.name}' url='${ctx}/${one.url!''}' opentype='${one.openType!''}' fullwithMenu='${one.fullScreen!''}' tabCloseable='${one.tabCloseable!''}'>
					<#if (one.url)!''>
						<a href="#">
					<#else>
						<a href="#">
					</#if>
					<#if one.children?? && (one.children?size > 0) >
						<i class="fa ${one.cssClass!''}"></i> <span class="navissp-label">${one.name!''}</span> <i class="my-icon navissp-more"></i></a>
						<ul level="2">
							<#list one.children as two>
								<li class="navissp-item" id='left-${two.id}' menuId='${two.id}' menuTitle='${two.name}' url='${ctx}/${two.url!''}' opentype='${two.openType!''}' fullwithMenu='${two.fullScreen!''}' tabCloseable='${two.tabCloseable!''}'>
									<#if two?? && two.url!''>
										<a href="#">
									<#else>
										<a href="#">
									</#if>
								<#if two.children?? && (two.children?size > 0) >
								<span class="navissp-label"><i class="fa ${two.cssClass!''}"></i>&nbsp;&nbsp;${two.name!''}</span><i class="my-icon navissp-more"></i></a>
								</li>
								<ul style="display:none;" level="3">
								<#list two.children as three>
                                <li class="navissp-item" id='left-${three.id}' menuId='${three.id}' menuTitle='${three.name}' url='${ctx}/${three.url!''}' opentype='${three.openType!''}' fullwithMenu='${three.fullScreen!''}' tabCloseable='${three.tabCloseable!''}'>
									<#if three?? && three.url!''>
										<a href="#">
									<#else>
										<a href="#">
									</#if>
								<#if three.children?? && (three.children?size > 0) >
								<span class="navissp-label"><i class="fa ${three.cssClass!''}"></i>&nbsp;&nbsp;${three.name!''}</span><i class="my-icon navissp-more"></i></a>
								</li>
									<#--<li id='left-${three.id}' menuId='${three.id}' menuTitle='${three.name}' url='${ctx}/${three.url!''}' opentype='${three.openType!''}' fullwithMenu='${three.fullScreen!''}' tabCloseable='${three.tabCloseable!''}'>-->
										<#--<a href="#"><span class="navissp-three"><i class="fa ${three.cssClass!''}"></i>&nbsp;&nbsp;${three.name!''}</span></a>-->
									<#--</li>-->
                                    <ul style="display:none;" level="4">
										<#list three.children as four>
											<li id='left-${four.id}' menuId='${four.id}' menuTitle='${four.name}' url='${ctx}/${four.url!''}' opentype='${four.openType!''}' fullwithMenu='${four.fullScreen!''}' tabCloseable='${four.tabCloseable!''}'>
												<a href="#"><span class="navissp-three"><i class="fa ${four.cssClass!''}"></i>&nbsp;&nbsp;${four.name!''}</span></a>
											</li>

										</#list>
                                    </ul>
									<#else>
									<span class="navissp-label"><i class="fa ${three.cssClass!''}"></i>&nbsp;&nbsp;${three.name!''}</span></a>
								</#if>
								</#list>
								</ul>
								<#else>
								<span class="navissp-label"><i class="fa ${two.cssClass!''}"></i>&nbsp;&nbsp;${two.name!''}</span></a>
								</#if>
							</#list>
						</ul>
					<#else>
						<i class="fa ${one.cssClass!''}"></i> <span class="navissp-label">${one.name!''}</span></a>
					</#if>
				</li>
			</#list>
		</#if>
	</ul>
