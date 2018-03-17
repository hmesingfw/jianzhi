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
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
<body>
	<jsp:include page="include/ifie.jsp" />
	
	<jsp:include page="include/header.jsp" />
	<!--// header-->
    <form id="searchForm" action="${ctxF}/myCourseUser" method="post" style="display: none;">  		

		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>  			
  	</form>

    <!--// indexCourseList-->
	
	<div class="myuser">
		<div class="myuser-top">
			
		</div>
		<div class="myuser-center">
			<jsp:include page="include/myleft.jsp" />
			<div class="myuser-rt">
				<div class="user2">
					<ul class="user2-ul1">
										


						<c:forEach items="${page.list}" var="info">
							<li>
								<div class="user2-lt">
									<img src="${fns:getCourse(info.courseid).img}" />
								</div>
								<div class="user2-rt">
									<h1>${fns:getCourse(info.courseid).title}</h1>
									<p>最新学习时间：
										<fmt:formatDate value="${info.updateDate}" pattern="yyyy-MM-dd"/>
									</p>
									<div class="userxian">
										<div class="userxian1"><span class="userxian2" style="width: ${fns:getCourseSchedule(info.usertime, info.coursetime)}%"></span></div>
										<label>已学习${fns:getCourseSchedule(info.usertime, info.coursetime)}%</label>
									</div>
								</div>
								<a href="" class="usercuo">×</a>
							</li>
						</c:forEach>	
					</ul>
					<div class="pages">
		            	${page.frontToString() }
		            </div>	
				</div>
			</div>
		</div>
	</div>
	
	

 	<jsp:include page="include/footer.jsp" /> 
<!--//footer-->

    <aside class="aside-operate">
    	<ul>
    		<li style="border-top:0" onclick="window.open('http://me.cs.com/message.aspx')"><span>留言<br />建议</span><i class="edufont e-icon-yijianfankui"></i></li>
    		<li><span>手机<br />app</span><i class="edufont e-icon-phone"></i><div class="code-box"><em></em><img src="/tp/PC/skin055/images/common/wechat-code.png" /><p>下载移动app</p></div></li>
    		<li><span>关注<br />微信</span><i class="edufont e-icon-wechat"></i><div class="code-box"><em></em><img src="/tp/PC/skin055/images/common/wechat-code.png" /><p>关注微信公众号</p></div></li>
    		<li onclick="goTop()"><span>返回<br />头部</span><i class="edufont e-icon-top"></i></li>
    	</ul>
    </aside>  
</body>
</html>
