<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<section id="carousel" class="index-carousel">
  	<ul>
  		<c:forEach items="${aboutBanner}" var="banner" end="4">
       		<li style="background:url(${banner.filepath}) no-repeat 50% 50%; "  onclick="window.location.href='${banner.url}'"></li>
		</c:forEach>
  	</ul>
</section>