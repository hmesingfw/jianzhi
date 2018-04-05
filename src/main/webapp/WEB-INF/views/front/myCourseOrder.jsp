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

    <form id="searchForm" action="${ctxF}/myCourseOrder" method="post" style="display: none;">  		

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
								<c:when test="${info.shoptype == 2}"> 
									<li>
										<div class="user2-lt">
											<img src="${fns:getCourseSort(info.courseid).img}" />
										</div>
										<div class="user2-rt">
											<h1>${fns:getCourse(info.courseid).title}</h1>
											<p>创建时间：
												<fmt:formatDate value="${info.createDate}" pattern="yyyy-MM-dd"/>
											</p>
											<div class="userxian">
												购买状态：${fns:getDictLabel(info.paystatus, 'pay_status', '')}
											</div>
										</div>
										<c:if test="${info.paystatus == 1}">
											<a href="${ctxF}/gotopaycourse?id=${info.courseid}" class="usercuo" style="margin-top: 22px;">
												<button class="layui-btn layui-btn-normal" type="button">购买</button>
											</a>								
										</c:if>
									
									</li>
								</c:when>
								<c:otherwise>    
									<li>
										<div class="user2-lt">
											<%-- <img src="${fns:getCourseSort(info.courseid).img}" /> --%>
										</div>
										<div class="user2-rt">
											<h1>${fns:getCourseSort(info.courseid).name}</h1>
											<p>创建时间：
												<fmt:formatDate value="${info.createDate}" pattern="yyyy-MM-dd"/>
											</p>
											<div class="userxian">
												购买状态：${fns:getDictLabel(info.paystatus, 'pay_status', '')}
											</div>
										</div>
										<c:if test="${info.paystatus == 1}">
											<a href="${ctxF}/gotopaycourse?type=2&id=${info.courseid}" class="usercuo" style="margin-top: 22px;">
												<button class="layui-btn layui-btn-normal" type="button">购买</button>
											</a>								
										</c:if>
									
									</li>
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
	
<!--//footer-->
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
