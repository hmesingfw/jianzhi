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
	<link href="${ctxStatic}/jianzhi/layui/css/layui.css" rel="stylesheet">
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
							<c:choose>
								<c:when test="${fn:length(fns:getCourse(info.courseid).parentid)>0}">
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
												<div class="userxian1"><span class="userxian2" style="width: ${info.width}%"></span></div>	
												<label>已学习${info.width}%</label> 
											</div>
										</div>
										<!-- <a href="" class="usercuo">×</a> -->
										<a href="${ctxF}/coursedetail?id=${info.courseid}&parentid=${fns:getCourse(info.courseid).parentid}"  target="_blank" class="usercuo" style="margin-top: 22px;">
											<button class="layui-btn layui-btn-normal" type="button">观看</button>
										</a>
									</li>

								</c:when>
								<c:otherwise>   

								
							   </c:otherwise>
							</c:choose>
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

     <jsp:include page="include/aside.jsp" />  
    <script src="${ctxStatic}/jianzhi/js/imgload.js"></script>
    <script src="${ctxStatic}/jianzhi/js/cs.assembly.js"></script>
    <!--执行页面加载js-->
    <script src="${ctxStatic}/jianzhi/js/page.index.js"></script>
    <script src="${ctxStatic}/jianzhi/js/page.load.js"></script>
    <script src="${ctxStatic}/jianzhi/js/page.main.js"></script>
    
</body>
</html>
