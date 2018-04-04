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
	<link href="${ctxStatic}/jianzhi/css/news.css" rel="stylesheet" />
<body>
	<jsp:include page="include/ifie.jsp" />
	
	<jsp:include page="include/header.jsp" />
     
	
	<div class="news_inner">
		<div class="newscontent page-width clearfix">
			<div class="news_wz fl">
			  <div class="news_wzbox">
				<section class="article_info">
					<h1 class="article_name">${newinfo.title}</h1>
					<div class="dp">
						<fmt:formatDate value="${newinfo.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>                       
					</div>
					<div class="article_line"></div>
				</section>
                
				<div class="news_article">
					<div class="news_article_box">
						${newinfo.content}
					
					</div>					 
					<div class="clear blank20"></div>				
 
					<div class="clear"></div>
				</div>
				<div class="clear blank10"></div>

				
			   </div>	
			</div>
			
			<div class="news-newslistr fr">
			    
				<section class="newslistrn1">
				  	<div class="panel-heading">
						<h3 class="panel-title">推荐新闻</h3>
				  	</div>
				  	<div class="panel-body">		
				  				   
				  	<c:forEach items="${comlist}" var="info">
  						<div class="hot_news_num clearfix">
							<div class="hot_news_n fl">
								<span class="num recommend"><img src="${info.img}"></span>
							</div>
							<div class="hot_news">
								<a href="${ctxF}/newdetail?id=${info.id}" target="_blank">${fns:abbr(info.title, 24)}</a>
								<span class="hot_news_info">${fns:abbr(info.introduce, 56)}</span>
							</div>
						</div>
					</c:forEach>

  
				  	</div>
				</section>	
			</div>	
		</div>
	</div>

   <jsp:include page="include/footer.jsp" /> 


     <jsp:include page="include/aside.jsp" />  
    <script src="${ctxStatic}/jianzhi/js/imgload.js"></script>
    <script src="${ctxStatic}/jianzhi/js/cs.assembly.js"></script>
    <!--执行页面加载js-->
    <script src="${ctxStatic}/jianzhi/js/page.index.js"></script>
    <script src="${ctxStatic}/jianzhi/js/page.load.js"></script>
    <script src="${ctxStatic}/jianzhi/js/page.main.js"></script>
     
 
</body>
</html>