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
	<!--// header-->
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
	</script>
    
	<div class="page-width" style="margin-top: 30px;">			
		
		<section class="newslist clearfix">
			<div class="news-newslistl fl">
				
                <ul class="newslistl_ul">
					<c:forEach items="${page.list}" var="info">
					  	<li class="article-item">
							<div class="media">
								<div class="media-left1">
								 	<a href="${ctxF}/newdetail?id=${info.id}" >
										<img src="${info.img}" class="m-img" />
								 	</a>
								</div>
								<div class="media-body">
								    <h2 class="title">
									  <a class="link-dark" href="${ctxF}/newdetail?id=${info.id}">${fns:abbr(info.title, 60)}</a>
									</h2>
								    <div class="media-bodyp">${fns:abbr(info.introduce, 160)}</div>
									<div class="media_info clearfix">
									   <span class="media_info_right fl"><fmt:formatDate value="${info.updateDate}" pattern="yyyy-MM-dd"/></span>
									</div>
								</div>
							</div>
					 	</li> 
					</c:forEach>
				</ul>
				<div class="pages">
	            	${page.frontToString() }
	            </div>	  

				
			</div>
			<div class="news-newslistr fr">	 
				
				<section class="newslistrn1">
					<form id="searchForm" action="${ctxF}/news" method="post" >
						<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
						<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
						<div style="overflow: hidden;margin-top: 10px;"><input type="text" name="title" value="${znew.title}" style="height: 30px;padding: 0px 10px;width: 60%;float: left;"><button style="height: 34px;float: right;width: 25%;background: #4076D4;color: #fff;border: none;">搜索</button></div>
					</form>

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
		</section>	
    </div>
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
	//懒加载
	imgLoad('m-img','img/none.png');
	   var userUrl = {
      index:'http://me.cs.com/user.aspx',//个人中心
      loginout:'http://me.cs.com/loginout.aspx',//退出登录
      editinfo:'/user.aspx?a-basic,f-editinfo',//修改资料
      message:'/user.aspx?a-message,f-Receivedmessage',//站内消息
      myCourse:'http://me.cs.com/user.aspx'//我的课程
  	}
</script>

</body>
</html>
