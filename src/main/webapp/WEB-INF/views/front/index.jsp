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
<body>
	<jsp:include page="include/ifie.jsp" />
	
	<jsp:include page="include/header.jsp" />
	<!--// header-->
    	
    <jsp:include page="include/banner.jsp" />
      <!--头部广告1-->   	
    
  	
	<section class="class-list page-width page-animate">
	    <div class="index-title"><h4>热门课程</h4><p>course recommendation</p><img src="${ctxStatic}/jianzhi/img/xian.png"/></div>
	    <div class="clearfix">
	    	<ul>	
		    	<c:forEach items="${zcoursepage.list}" var="info" varStatus="list">        	  
					<li onclick="window.location.href='${ctxF}/coursedetail?id=${info.id}'">
					    <div class="li-box">
					        <div class="class-img">
					            <a href="javascript:;">
					                <img src="${info.img}" class="m-img" />
					            </a>
					        </div>
					        <div class="name"><span class="price">￥${info.price}</span><a href="javascript:;">${info.title}</a></div>
					        
					    </div>
					</li>
				</c:forEach>	
	        </ul>
	    </div>
	</section>
  

	<div class="bg-gray">
	    <section class="indexCourseList">
	    	 <div class="box page-animate">
	<div class="index-title">
		<div class="index-title"><h4>新闻资讯</h4><p>video recommendation</p><img src="${ctxStatic}/jianzhi/img/xian.png"/></div>
	</div>
    <div class="box-inner">
		<ul class="clearfix">
			<c:forEach items="${pageNewList.list}" var="info" varStatus="list">

				<c:choose>
					<c:when test="${list.index == 0}"> 
						<li onclick="window.location.href='${ctxF}/newdetail?id=${info.id}'">
						   	<div class="hot-img">
								<img src="${info.img}" class="m-img">
						  	</div>
						    <div class="hot-img-info">
						    	<div class="bat">
								   <h3 class="live1"><a href="javascript:;">${fns:abbr(info.title, 60)}</a></h3>
								   <p><fmt:formatDate value="${info.updateDate}" pattern="yyyy-MM-dd"/></p>
								</div>
							    <div class="author-info clearfix">
				                   	<span class="ep_name">${fns:abbr(info.introduce, 240)}</span>			                   
						        </div>
						    </div>
						</li>						
					</c:when>		    
				   	<c:otherwise> 						
						<li onclick="window.location.href='${ctxF}/newdetail?id=${info.id}'">
							<div class="hot-img-info bat1">
						    	<div class="bat">
								   <h3 class="live1"><a href="javascript:;">${fns:abbr(info.title, 80)}</a></h3>
								   <p><fmt:formatDate value="${info.updateDate}" pattern="yyyy-MM-dd"/></p>
							    </div>						    
					   		</div>
						</li>
				   	</c:otherwise>
				</c:choose>			
			</c:forEach>
	    </ul>
	    <ul class="bat2">
	    	<h1><span></span>文档下载<label>TOP8↑</label></h1>
	    	
	    	<c:forEach items="${zoctoplist}" var="info">
	    		<li><a href=""><img src="${ctxStatic}/jianzhi/icon/lianjie.png" />${info.title}</a></li>
	    	</c:forEach>    	
	    	
	    </ul>
    </div>
</div>  
  
  
  		<div class="bat3">
		  	<h1>友情链接：</h1>
		  	<div class="bat3-div">
		  		<c:forEach items="${aboutFriendship}" var="ship" end="3">
			  		<a href="${ship.url}" target="_blank" style="font-size: 1.5em;margin-left: 10px">${ship.name}<!-- <img src="${ship.filepath}"/> --></a>
			  	</c:forEach>
		  	</div>
	  	</div>


	    </section>
    </div>
	
	
	
	<jsp:include page="include/footer.jsp" /> 

    <jsp:include page="include/aside.jsp" />  



<script src="${ctxStatic}/jianzhi/js/imgload.js"></script>
<script src="${ctxStatic}/jianzhi/js/cs.assembly.js"></script>
<!--执行页面加载js-->
<script src="${ctxStatic}/jianzhi/js/page.index.js"></script>
<script src="${ctxStatic}/jianzhi/js/page.load.js"></script>
<script src="${ctxStatic}/jianzhi/js/page.main.js"></script>
<script>

//轮播图
$('#carousel').carousel({
	object:'li',
	scrollType:'slide',
	width:'100%',
	height:'350',
	arrow:true,
	autoPlay:true,
	dots:1,
	speed:400,
	interval:4000
});
//懒加载
imgLoad('m-img','/tp/PC/skin055/images/common/none.png');


</script>
</body>
</html>
