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
																	
	                    <!-- <div class="price">	      
	                    	<c:if test="${zcourse.type == 1}">
								免费
							</c:if>
							<c:if test="${zcourse.type != 1}">
								<span class="price-info"><span>
									<span class="qgbg">抢购</span><font>¥</font>${zcourse.price}</span>
								</span>
								<span class="money">¥${zcourse.sale}</span>	   
							</c:if>		                                        	
	                    </div> -->
						<div class="button">	                    
							<c:if test="${ispay == 'nopay'}">
	                    		<input type="button" onclick="gotopaycourse()" class="hb-ui-btn1" value="购买" />	
	                    	</c:if>     
	                    	<c:if test="${ispay == 'yespay'}">
	                    		<a href="${ctxF}/gotolookcourse?id=${zcourse.id}"  target="_blank">
	                    			<input type="button" class="hb-ui-btn1" value="观看" />	
	                    		</a>
	                    	</c:if>    
	                    </div>
	                </div>
	                <div class="clear"></div>
	            </div>	    		    
			</div>	        
        </div>
    </div> 
    <p id="lookid">
    
	</p>
	<jsp:include page="include/footer.jsp" /> 

    <jsp:include page="include/aside.jsp" />  
    <script src="${ctxStatic}/jianzhi/js/imgload.js"></script>
    <script src="${ctxStatic}/jianzhi/js/cs.assembly.js"></script>
    <!--执行页面加载js-->
    <script src="${ctxStatic}/jianzhi/js/page.index.js"></script>
    <script src="${ctxStatic}/jianzhi/js/page.load.js"></script>
    <script src="${ctxStatic}/jianzhi/js/page.main.js"></script>
    
	<script type="text/javascript">
		function gotopaycourse(){
			window.location.href="${ctxF}/gotopaycourse?id=${zcourse.id}";
		}
		
	</script>	

</body>
</html>
