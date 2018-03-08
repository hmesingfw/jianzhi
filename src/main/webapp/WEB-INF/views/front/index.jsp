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
	    <div class="index-title"><h4>热门班级</h4><p>class recommendation</p><img src="${ctxStatic}/jianzhi/img/xian.png"/></div>
	    <div class="clearfix">
	    	<ul>	        	  
				<li>
				    <div class="li-box">
				        <div class="class-img">
				            <a href="http://me.cs.com/rv-43.aspx" target="_blank">
				                <img src="${ctxStatic}/jianzhi/img/L131502920419855.jpg" alt="高级设计班" class="m-img" />
				            </a>
				        </div>
				        <div class="name"><span class="price">￥56.00</span><a href="http://me.cs.com/rv-43.aspx" target="_blank">高级设计班</a></div>
				        
				    </div>
				</li>
				
				<li>
				    <div class="li-box">
				        <div class="class-img">
				            <a href="http://me.cs.com/rv-41.aspx" target="_blank">
				                <img src="${ctxStatic}/jianzhi/img/A131502869117541.png" alt="旅游班" class="m-img" />
				            </a>
				        </div>
				        <div class="name"><span class="price">￥0.00</span><a href="http://me.cs.com/rv-41.aspx" target="_blank">旅游班</a></div>
				        
				    </div>
				</li>
				
				<li>
				    <div class="li-box">
				        <div class="class-img">
				            <a href="http://me.cs.com/rv-40.aspx" target="_blank">
				                <img src="${ctxStatic}/jianzhi/img/D131502620854801.jpg" alt="后期修图班班" class="m-img" />
				            </a>
				        </div>
				        <div class="name"><span class="price">￥0.00</span><a href="http://me.cs.com/rv-40.aspx" target="_blank">后期修图班班</a></div>
				        
				    </div>
				</li>
				
				<li>
				    <div class="li-box">
				        <div class="class-img">
				            <a href="http://me.cs.com/rv-37.aspx" target="_blank">
				                <img src="${ctxStatic}/jianzhi/img/U131502621568402.jpg" alt="特效后期班" class="m-img" />
				            </a>
				        </div>
				        <div class="name"><span class="price">￥56.00</span><a href="http://me.cs.com/rv-37.aspx" target="_blank">特效后期班</a></div>
				        
				    </div>
				</li>
				<li>
				    <div class="li-box">
				        <div class="class-img">
				            <a href="http://me.cs.com/rv-43.aspx" target="_blank">
				                <img src="${ctxStatic}/jianzhi/img/L131502920419855.jpg" alt="高级设计班" class="m-img" />
				            </a>
				        </div>
				        <div class="name"><span class="price">￥56.00</span><a href="http://me.cs.com/rv-43.aspx" target="_blank">高级设计班</a></div>
				       
				    </div>
				</li>
				
				<li>
				    <div class="li-box">
				        <div class="class-img">
				            <a href="http://me.cs.com/rv-41.aspx" target="_blank">
				                <img src="${ctxStatic}/jianzhi/img/A131502869117541.png" alt="旅游班" class="m-img" />
				            </a>
				        </div>
				        <div class="name"><span class="price">￥0.00</span><a href="http://me.cs.com/rv-41.aspx" target="_blank">旅游班</a></div>
				        
				    </div>
				</li>
				
				<li>
				    <div class="li-box">
				        <div class="class-img">
				            <a href="http://me.cs.com/rv-40.aspx" target="_blank">
				                <img src="${ctxStatic}/jianzhi/img/D131502620854801.jpg" alt="后期修图班班" class="m-img" />
				            </a>
				        </div>
				        <div class="name"><span class="price">￥0.00</span><a href="http://me.cs.com/rv-40.aspx" target="_blank">后期修图班班</a></div>
				        
				    </div>
				</li>
				
				<li>
				    <div class="li-box">
				        <div class="class-img">
				            <a href="http://me.cs.com/rv-37.aspx" target="_blank">
				                <img src="${ctxStatic}/jianzhi/img/U131502621568402.jpg" alt="特效后期班" class="m-img" />
				            </a>
				        </div>
				        <div class="name"><span class="price">￥56.00</span><a href="http://me.cs.com/rv-37.aspx" target="_blank">特效后期班</a></div>
				       
				    </div>
				</li>

  
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
						<%-- <li class="Hnew">
						   	<div class="hot-img Hhide">
								<img src="${info.img}" class="m-img">
						  	</div>
						    <div class="hot-img-info bat1">
						    	<div class="bat">
								   <h3 class="live1"><a href="javascript:;">${fns:abbr(info.title, 60)}</a></h3>
								   <p><fmt:formatDate value="${info.updateDate}" pattern="yyyy-MM-dd"/></p>
							   	</div>
							    <div class="author-info clearfix Hhide">
				                   	<span class="ep_name">${fns:abbr(info.introduce, 240)}</span>			                   
						        </div>
						    </div>
						</li> --%>
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
			  		<a href="${ship.url}" target="_blank"><img src="${ship.filepath}"/></a>
			  	</c:forEach>
		  	</div>
	  	</div>


	    </section>
    </div>
	
	
	
	<jsp:include page="include/footer.jsp" /> 

    <aside class="aside-operate">
    	<ul>    		 
    		<li onclick="goTop()"><span>返回<br />头部</span><i class="edufont e-icon-top"></i></li>
    	</ul>
    </aside>  


<script src="${ctxStatic}/jianzhi/js/imgload.js"></script>
<script src="${ctxStatic}/jianzhi/js/cs.assembly.js"></script>
<!--执行页面加载js-->
<script src="${ctxStatic}/jianzhi/js/page.index.js"></script>
<script src="${ctxStatic}/jianzhi/js/page.load.js"></script>
<script src="${ctxStatic}/jianzhi/js/page.main.js"></script>
<script>
	//开启分类
	setNavFlag(0);

  var userUrl = {
      index:'http://me.cs.com/user.aspx',//个人中心
      loginout:'http://me.cs.com/loginout.aspx',//退出登录
      editinfo:'/user.aspx?a-basic,f-editinfo',//修改资料
      message:'/user.aspx?a-message,f-Receivedmessage',//站内消息
      myCourse:'http://me.cs.com/user.aspx',//我的课程
      courseOrder:'/user.aspx?a-course,f-MyCourseOrder',//我的订单
      userLogin:'http://me.cs.com/login.aspx',//登录
      courseUrl:'/user.aspx?a-basic,f-main,courseid-'
  	  
  }

  //获取用户登录信息
  getUserInfo(userUrl,'main','index');

//轮播图
$('#carousel').carousel({
	  object:'li',
	  scrollType:'slide',
	  width:'100%',
	  height:'500',
	  arrow:true,
	  autoPlay:true,
	  dots:1,
	  speed:400,
	  interval:4000
});

//评分计算
coursePfCount('.index-cos-star');

var subClassNum = 8;
$(".index-title-subclass a").each(function() {
	$(this).attr("data-index", $(this).index() + 1)
});
$(".index-title-subclass a").each(function() {
	if ($(this).attr("data-index") > subClassNum) {
		$(this).remove()
	}
});
$(".con02").find(".jx2").hover(function() {
	$(this).find(".qg-info").fadeIn(300)
}, function() {
	$(this).find(".qg-info").fadeOut(300)
});
$(".indexCourseList").find(".box").each(function() {
	var courseLength = $(this).find("li").length;
	if (courseLength == 0) {
		$(this).remove()
	}
}); 

</script>
</body>
</html>
