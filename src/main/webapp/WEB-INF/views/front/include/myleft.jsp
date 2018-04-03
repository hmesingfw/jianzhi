<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>


<div class="myuser-lt">
	<div class="myuser1">
		<c:choose>
        	<c:when test="${!empty sessionMyinfo.img}">
				<img src="${sessionMyinfo.img}"/>
			</c:when>
			<c:otherwise>
				<img src="${ctxStatic}/jianzhi/img/116815.jpg"/>
			</c:otherwise>
		</c:choose>
			<p>${sessionMyinfo.truename}</p>
	</div>
	<div class="myuser2">
		<a href="${ctxF}/myinfo">个人资料</a>
		<a href="${ctxF}/myCourseOrder">我购买的专业</a>
		<a href="${ctxF}/myCourseUser">我的课程</a>
		<a href="${ctxF}/myDoc">文档下载</a>
		<a href="${ctxF}/myTest">我的专业试题</a>
	</div>
</div>