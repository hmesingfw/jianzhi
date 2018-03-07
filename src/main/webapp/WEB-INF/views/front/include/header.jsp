<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>


<header class="header">
	<div class="top-bar">
		<div class="page-width">
			<nav class="top-bar-menu">
				<div class="index-logo" onclick="window.location.href='${ctxF}'">
					<img  src="${ctxStatic}/jianzhi/img/logo.jpg"/>
					<span>您好，欢迎来到辽宁建执在线教育！</span>
					<label><img src="${ctxStatic}/jianzhi/icon/phone.png" class="bat4"/>资讯热线：${aboutPhone.content}</label>
				</div>
			</nav>
			<div class="top-bar-user" id="nologin">
				<a href="http://me.cs.com/login.aspx" target="_blank" class="bat5"><img src="${ctxStatic}/jianzhi/icon/user.png" />登录</a>
	            <a href="http://me.cs.com/reg.aspx" target="_blank">注册</a>
			</div>
		</div>
	</div>
	<div class="index-nav">
		<div class="index-nav1">
			<a href="${ctxF}"  ${mendId=='1'?'class="index-click"':'' }>首页</a>
			<a href=""  ${mendId=='2'?'class="index-click"':'' }>全部课程</a>
			<a href=""  ${mendId=='3'?'class="index-click"':'' }>在线测试</a>
			<a href=""  ${mendId=='4'?'class="index-click"':'' }>在线文库</a>
			<a href="${ctxF}/news" ${mendId=='5'?'class="index-click"':'' }>新闻资讯</a>
		</div>
	</div>		
</header>