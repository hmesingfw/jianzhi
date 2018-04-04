<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<aside class="aside-operate">
	<ul> 
		<!-- <li><span>手机<br />app</span><i class="edufont e-icon-phone"></i><div class="code-box"><em></em><img src="img/wechat-code.png" /><p>下载移动app</p></div></li> -->
		<li onclick="window.open('http://wpa.qq.com/msgrd?v=3&amp;uin=1390648082&amp;site=qq&amp;menu=yes')"><span style="background: none;">QQ<br />咨询</span>
			<img src="${ctxStatic}/jianzhi/qq.png" style="width: 55px;">
			<!-- <i class="edufont e-icon-wechat"></i> -->
		</li>
		<li onclick="window.open('http://wpa.qq.com/msgrd?v=3&amp;uin=2196593676&amp;site=qq&amp;menu=yes')"><span style="background: none;">QQ<br />咨询</span>
			<img src="${ctxStatic}/jianzhi/qq.png" style="width: 55px;">
			<!-- <i class="edufont e-icon-wechat"></i> -->
		</li>
		<li onclick="window.open('http://wpa.qq.com/msgrd?v=3&amp;uin=3148873158&amp;site=qq&amp;menu=yes')"><span style="background: none;">QQ<br />咨询</span>
			<img src="${ctxStatic}/jianzhi/qq.png" style="width: 55px;">
			<!-- <i class="edufont e-icon-wechat"></i> -->
		</li>
		<li onclick="goTop()"><span>返回<br />头部</span><i class="edufont e-icon-top"></i></li>
	</ul>
</aside>  