<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<style>
	.mymenu{
		position: relative;
	}
	.menu{
		display: none;
		width: 136px;
		background: rgba(255,255,255,0.8);
		position: absolute;
		z-index: 11;
		
		max-height: 300px;
		overflow-y: scroll;
		

	}
	.menu::-webkit-scrollbar{width:0px;}
	.menu a{
		font-size: 14px;
		color: #383838;
		padding: 0px;
		width: 100%;
		text-align: center;
		
	}
	.menu a:hover{
		background: #999;
	}
	.menu11{
		left: 112px;
	}
	.menu12{
		left: 248px;
	}
	.menu13{
		left: 384px;
	}
</style>
<!-- <script src="https://cdn.bootcss.com/jquery/1.8.3/jquery.min.js"></script> -->
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
				<c:choose>
		        	<c:when test="${!empty sessionMyinfo}">
						<a href="${ctxF}/myinfo" class="bat5">${sessionMyinfo.truename}</a>
						<a href="${ctxF}/logout">退出</a>
	            	</c:when>
	        		<c:otherwise>
	        			<a href="${ctxF}/gotologin" class="bat5"><img src="${ctxStatic}/jianzhi/icon/user.png" />登录</a>
			            <a href="${ctxF}/reg" >注册</a>
	        		</c:otherwise>
	        </c:choose>
			</div>
		</div>
	</div>
	<div class="index-nav">
		<div class="index-nav1">
			<a href="${ctxF}"  ${mendId=='1'?'class="index-click"':'' }>首页</a>
			<a href="${ctxF}/courseList" class="menu1"  ${mendId=='2'?'class="index-click"':'' }>全部课程<img src="${ctxStatic}/jianzhi/icon/under.png"/></a>
			<a href="${ctxF}/questionlist"  ${mendId=='3'?'class="index-click"':'' }>在线测试</a>
			<a href="${ctxF}/sortlist"  ${mendId=='4'?'class="index-click"':'' }>在线文库</a>
			<a href="${ctxF}/news" ${mendId=='5'?'class="index-click"':'' }>新闻资讯</a>

			<div class="mymenu">
				<div style="" class="menu menu11">
					<c:forEach items="${fns:getCourseSortList()}" var="info" varStatus="list">   
						<a href="${ctxF}/courseList?classflyone=${info.id}" id="${info.id}" paren="${info.parentId}" style="${info.parentId != '0'? 'display:none':''}">${info.name}</a>						 
					</c:forEach>
				</div>
				<div  class="menu menu12">
					<c:forEach items="${fns:getCourseSortList()}" var="info" varStatus="list">   
						<a href="${ctxF}/courseList?classflyone=${info.parentId}&classflytwo=${info.id}" id="${info.id}" paren="${info.parentId}" style="display: none;">${info.name}</a>						 
					</c:forEach>
				</div>
				<div  class="menu menu13">
					<c:forEach items="${fns:getCourseSortList()}" var="info" varStatus="list">   
						<a href="" id="${info.id}" paren="${info.parentId}" style="display: none;">${info.name}</a>						 
					</c:forEach>
				</div>
			</div>
		</div>
	</div>		
</header>
<script type="text/javascript">
	$(function(){
		$(".menu1").hover(function(){
		    $(".menu11").css("display","block");
		});

		var classflyone = "";

		$(".menu11 a").hover(function(){			
		    $(".menu12").css("display","block");
		    var alist = $(".menu12 a");
			var id = $(this).attr('id');	
			classflyone = id;

			$(".menu12 a").hide();
			$(".menu13 a").hide();

			$.each(alist, function(){
				if(id ==  $(this).attr('paren')){
					$(this).show();
				}				
			})

		});
		$(".menu12 a").hover(function(){
		    $(".menu13").css("display","block");

		    var alist = $(".menu13 a");
			var id = $(this).attr('id');	
			
			
			$(".menu13 a").hide();
			$.each(alist, function(){
				if(id ==  $(this).attr('paren')){
					$(this).show();
					$(this).attr('href', '${ctxF}/courseList?classflyone='+classflyone+'&classflytwo='+$(this).attr('paren')+'&classflythr='+$(this).attr('id'));
				}				
			})
		});
	})
	
</script>
