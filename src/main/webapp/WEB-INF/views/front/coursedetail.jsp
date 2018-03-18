<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!doctype html>
<html>
	<jsp:include page="include/head.jsp" />
	<link rel="stylesheet" href="${ctxStatic}/jianzhi/css/courseview.css"  />
	<style type="text/css">
		.Hhide{
			display: none !important;
		}
	</style>
<body>
	<jsp:include page="include/ifie.jsp" />
	
	<jsp:include page="include/header.jsp" />
	<!--// header-->
      
   	<div class="courseView" style="margin-bottom: 50px;">
   		<div class="page-width">
	   		<div class="posnav">
			<a href="${ctxF}/courseList">全部课程</a><em>&gt;</em>${zcourse.title}
			</div>
	    
	    	<div class="box bd-all">
	        	<div class="course-pro1"></div>
	            <div class="courseInfo">
	                <div class="image" id="courseImage">
	            		<img src="${zcourse.img}">    
	                </div>
	                <div class="courseInfo-right">
	                	<div class="name">		                	
	                		${zcourse.title}
	                	</div> 					 
						${zcourse.introduce}	
																	
	                    <div class="price">	      
	                    	<c:if test="${zcourse.type == 1}">
								免费
							</c:if>
							<c:if test="${zcourse.type != 1}">
								<span class="price-info"><span>
									<span class="qgbg">抢购</span><font>¥</font>${zcourse.price}</span>
								</span>
								<span class="money">¥${zcourse.sale}</span>	   
							</c:if>		                                        	
	                    </div>
						<div class="button">	                    
							<c:if test="${ispay == 'nopay'}">
	                    		<input type="button" onclick="gotopaycourse()" class="hb-ui-btn1" value="购买" />	
	                    	</c:if>     
	                    	<c:if test="${ispay == 'yespay'}">
	                    		<input type="button" onclick="gotolookcourse()" class="hb-ui-btn1" value="观看" />	
	                    	</c:if>    
	                    </div>
	                </div>
	                <div class="clear"></div>
	            </div>	    		    
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
	<script type="text/javascript">
		function gotopaycourse(){
			window.location.href="${ctxF}/gotopaycourse?id=${zcourse.id}";
		}
		function gotolookcourse(){
			window.location.href="${ctxF}/gotolookcourse?id=${zcourse.id}";
		}
	</script>	

</body>
</html>
