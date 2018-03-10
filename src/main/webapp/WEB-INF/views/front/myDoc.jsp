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
