<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<section id="carousel" class="index-carousel">
  	<ul>
  		<c:forEach items="${aboutBanner}" var="banner" end="4">
       		<a href="${banner.url}" target="_blank"><li style="background:url(${banner.filepath}) no-repeat 50% 50%; "></li> </a>
		</c:forEach>
  	</ul>
</section>