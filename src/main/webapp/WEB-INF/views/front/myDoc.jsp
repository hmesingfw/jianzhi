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
    

    <!--// indexCourseList-->
	
	<div class="myuser">
		<div class="myuser-top">
			
		</div>
		<div class="myuser-center">

			<jsp:include page="include/myleft.jsp" />
			<div class="myuser-rt">
				<div class="user2">
					<ul class="user2-ul">
						<c:forEach items="${page.list}" var="info">
							<li>
								<div class="user2-img"> 
									<c:choose>
										<c:when test="${fns:getDoc(info.docid).filetype == '1'}"> 
											<img src="${ctxStatic}/jianzhi/img/doc.gif" />
										</c:when>
										<c:when test="${fns:getDoc(info.docid).filetype == '2'}"> 
											<img src="${ctxStatic}/jianzhi/img/doc.gif" />
										</c:when>
										<c:when test="${fns:getDoc(info.docid).filetype == '3'}"> 
											<img src="${ctxStatic}/jianzhi/img/doc.gif" />
										</c:when>
										<c:otherwise> 
											<img src="${ctxStatic}/jianzhi/img/doc.gif" />
										</c:otherwise>
									</c:choose>
									
									<div class="user2-center">
										<h1><a href="${ctxF}/doclook?id=${info.docid}" target="_blank">${fns:getDoc(info.docid).title}</a></h1>
										<p style="word-break:keep-all; white-space:nowrap;  ">下载时间:<fmt:formatDate value="${info.updateDate}" pattern="yyyy-MM-dd HH:mm"/></p>
									</div>
									<a href="${ctxF}/docDown?docId=${info.docid}" target="_blank"><button class="user2-button" type="button">下载</button></a>
								</div>
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

     <jsp:include page="include/aside.jsp" />  
    <script src="${ctxStatic}/jianzhi/js/imgload.js"></script>
    <script src="${ctxStatic}/jianzhi/js/cs.assembly.js"></script>
    <!--执行页面加载js-->
    <script src="${ctxStatic}/jianzhi/js/page.index.js"></script>
    <script src="${ctxStatic}/jianzhi/js/page.load.js"></script>
    <script src="${ctxStatic}/jianzhi/js/page.main.js"></script>
    

</body>
</html>
