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