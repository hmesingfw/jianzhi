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
	<link href="${ctxStatic}/jianzhi/css/download.css" rel="stylesheet" />
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
     
<%--
	<section class="i-banner" id="banner">
		<ul>
			<c:forEach items="${aboutBanner}" var="banner" end="4">
	       		<li style="background:url(${banner.filepath}) no-repeat 50% 50%; " onclick="window.location.href='${banner.url}'"></li>
			</c:forEach>
		</ul>
	</section>
--%>
	<div class="myteam">
  		<div class="myteam1">
  			<div class="myteam2">
  				<span>分类</span>
	  			<c:forEach items="${docsort}" var="info">
	  				 
		  			<a href="javascript:;" onclick="sortclick('${info.id}','1')" ${info.id == classflyone?'class="myteamon"':'' } >${info.name}</a>
		  		</c:forEach>
  			</div>
  		</div>

  		<div class="myteam1">
  			<div class="myteam2">
  				<span>分类</span>
  				<c:forEach items="${docsort2}" var="info">
		  			<a href="javascript:;" onclick="sortclick('${info.id}','2')" ${info.id == classflytwo?'class="myteamon"':'' } >${info.name}</a>
		  		</c:forEach>	  		
  			</div>
  		</div>

  		<div class="myteam1">
  			<div class="myteam2">
  				<span>分类</span>
  				<c:forEach items="${docsort3}" var="info">
		  			<a href="javascript:;" onclick="sortclick('${info.id}','3')" ${info.id == classflythr?'class="myteamon"':'' } >${info.name}</a>
		  		</c:forEach>	  			
  			</div>
  		</div>	  		
  	</div>



  	<div class="myreg">
  		<p>*仅TXT,WORD,PDF支持在线预览</p>
		<form id="searchForm" action="${ctxF}/sortlist" method="post" >
  			<div class="myreg-serch">
				<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>

				<input type="hidden" name="classflyone" id="classflyone" value="${classflyone}">
				<input type="hidden" name="classflytwo" id="classflytwo" value="${classflytwo}">
				<input type="hidden" name="classflythr" id="classflythr" value="${classflythr}">

	  			<input type="text" name="title" value="${zdoc.title}" placeholder="输入标题查询"/>
	  			<button>搜索</button>
  			</div>
	  	</form>
  	</div>
    <section class="downmain page-width">
		<div class="clearfix">
			<div class="downleft"  style="width: 1180px;">
				

				<section class="downlist bd-all">
					<div class="downlist-title">资料列表</div>
									
					<ul class="myreg-ul">
						<c:forEach items="${page.list}" var="info">
							<li>
								<div class="litit">								
										
									<c:choose>
										<c:when test="${info.filetype == 1}"> 
											<a href="${ctxF}/doclook?id=${info.id}" target="_blank">
												<img src="${ctxStatic}/jianzhi/icon/txt.png" />
													${info.title}
											</a>
										</c:when>
	    								<c:when test="${info.filetype == 2}"> 
	    									<a href="${ctxF}/doclook?id=${info.id}" target="_blank">
												<img src="${ctxStatic}/jianzhi/img/doc.gif" />
													${info.title}
											</a>
										</c:when>		
										<c:when test="${info.filetype == 3}"> 
	    									<a href="${ctxF}/doclook?id=${info.id}" target="_blank">
												<img src="${ctxStatic}/jianzhi/icon/pdf.png" />
													${info.title}
											</a>
										</c:when>									
			   							<c:otherwise> 
			   								<a href="javascript:;" >
												<img src="${ctxStatic}/jianzhi/icon/all.png" />
													${info.title}
											</a>
			   							</c:otherwise>
		   							</c:choose>		
										
								</div>								
								<a href="${ctxF}/docDown?docId=${info.id}" target="_blank"><button class="myreg-button" type="button">点击下载</button></a>
							</li>
						</c:forEach>		

						<c:if test="${page.list == null || fn:length(page.list) == 0}">
							<li>
								<div class="litit">
									<a href="javascript:;" >
										暂无数据
									</a>
								</div>
							</li>
						</c:if>				 
					</ul>
					<div class="pages">
		            	${page.frontToString() }
		            </div>	  					
				</section>
			</div>				
		</div>	
    </section>

    <jsp:include page="include/footer.jsp" /> 
<!--//footer-->

    <jsp:include page="include/aside.jsp" />  
    


    <script src="${ctxStatic}/jianzhi/js/imgload.js"></script>
    <script src="${ctxStatic}/jianzhi/js/cs.assembly.js"></script>
    <!--执行页面加载js-->
    <script src="${ctxStatic}/jianzhi/js/page.index.js"></script>
    <script src="${ctxStatic}/jianzhi/js/page.load.js"></script>
    <script src="${ctxStatic}/jianzhi/js/page.main.js"></script>
    <script>

    //轮播图
    $('#banner').carousel({
          object:'li',
          scrollType:'slide',
          width:'100%',
          height:'500',
          arrow:true,
          autoPlay:true,
          dots:1,
          speed:400,
          interval:4000,
          

    });
    //懒加载
    imgLoad('m-img','/tp/PC/skin055/images/common/none.png');


    </script>
</body>
</html>
