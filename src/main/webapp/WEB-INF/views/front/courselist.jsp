<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!doctype html>
<html>
	<jsp:include page="include/head.jsp" />
	<style type="text/css">
		.Hhide{
			display: none !important;
		}
	</style>
	<link href="${ctxStatic}/jianzhi/css/course.css" rel="stylesheet" />
	<script type="text/javascript">
		$(document).ready(function() {
			var msg = "${message}";
			if(msg!='') {
				layer.msg(msg);
			}
		});
		
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
        function sortclick(id, type){
        	if(type == '1'){
        		$("#classflyone").val(id);
        		$("#classflytwo").val('');
        		$("#classflythr").val('');
        	}
        	if(type == '2'){
        		$("#classflytwo").val(id);
        		$("#classflythr").val('');
        	}
        	if(type == '3'){
        		$("#classflythr").val(id);
        	}
        	$("#searchForm").submit();
        }
	</script>
<body>
	<jsp:include page="include/ifie.jsp" />
	
	<jsp:include page="include/header.jsp" />
	<!--// header-->
	
	<section class="i-banner" id="banner">
		<ul>
			<c:forEach items="${aboutBanner}" var="banner" end="4">
	       		<a href="${banner.url}" target="_blank"><li style="background:url(${banner.filepath}) no-repeat 50% 50%; "></li> </a>
			</c:forEach>
		</ul>
	</section>

	<div class="clear blank20"></div>
   
    <div class="page-width">

	<div class="myteam">
  		<div class="myteam1">
  			<div class="myteam2">
  				<span>分类</span>
	  			<c:forEach items="${sortlist1}" var="info">	  				 
		  			<a href="javascript:;" onclick="sortclick('${info.id}','1')" ${info.id == classflyone?'class="myteamon"':'' } >${info.name}</a>
		  		</c:forEach>
  			</div>
  		</div>

  		<div class="myteam1">
  			<div class="myteam2">
  				<span>分类</span>
	  			<c:forEach items="${sortlist2}" var="info">	  				 
		  			<a href="javascript:;" onclick="sortclick('${info.id}','2')" ${info.id == classflytwo?'class="myteamon"':'' } >${info.name}</a>
		  		</c:forEach>
  			</div>
  		</div>
  		<div class="myteam1">
  			<div class="myteam2">
  				<span>分类</span>
	  			<c:forEach items="${sortlist3}" var="info">	  				 
		  			<a href="javascript:;" onclick="sortclick('${info.id}','3')" ${info.id == classflythr?'class="myteamon"':'' } >${info.name}</a>
		  		</c:forEach>
  			</div>
  		</div>
  		
  	</div>
  	<div class="myreg">
  		<form id="searchForm" action="${ctxF}/courseList" method="post" >
	  		<div class="myreg-serch">

	  			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>

				<input type="hidden" name="classflyone" id="classflyone" value="${classflyone}">
				<input type="hidden" name="classflytwo" id="classflytwo" value="${classflytwo}">
				<input type="hidden" name="classflythr" id="classflythr" value="${classflythr}">

	  			<input type="text" placeholder="输入课程名称" name="title" value="${zcourse.title}" />
	  			<button>搜索</button>
	  		</div>
	  	</form>

  	</div>		
		
		<section class="course-list-wrap">
            

			<div class="course-list bd-all">
				<ul>
					<c:forEach items="${page.list}" var="info">
						<li>
							<div class="course-list-img"><img src="${info.img}" class="m-img imgLoad" /></div>

							<div class="course-list-title"><a href="${ctxF}/coursedetail?id=${info.id}">${info.title}</a></div>
							<div class="course-list-info">${info.teacher}</div> 
							<div class="reg3"><p>1200名学生正在学习...</p></div>
							<div class="course-list-price"><span><font>¥</font>${info.price}</span></div>
							<div class="course-list-button"><a href="${ctxF}/coursedetail?id=${info.id}">马上学习</a></div>
							
						</li>
					</c:forEach>
				</ul>
			</div>
		</section>		
		<div class="pages" style="margin-bottom: 30px">
        	${page.frontToString() }
        </div>	
	</div> 
	<jsp:include page="include/footer.jsp" /> 
<!--//footer-->

    <aside class="aside-operate">
    	<ul>
    		<li style="border-top:0" onclick="window.open('http://me.cs.com/message.aspx')"><span>留言<br />建议</span><i class="edufont e-icon-yijianfankui"></i></li>
    		<li><span>手机<br />app</span><i class="edufont e-icon-phone"></i><div class="code-box"><em></em><img src="img/wechat-code.png" /><p>下载移动app</p></div></li>
    		<li><span>关注<br />微信</span><i class="edufont e-icon-wechat"></i><div class="code-box"><em></em><img src="img/wechat-code.png" /><p>关注微信公众号</p></div></li>
    		<li onclick="goTop()"><span>返回<br />头部</span><i class="edufont e-icon-top"></i></li>
    	</ul>
    </aside>  

</body>
</html>
