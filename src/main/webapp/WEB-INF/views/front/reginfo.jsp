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
  	
	<div style="position: relative;width: 60%;margin: 40px auto;">
		${aboutReg.content}
	</div>
  

	 
	
	
	
	<jsp:include page="include/footer.jsp" /> 

    <aside class="aside-operate">
    	<ul>    		 
    		<li onclick="goTop()"><span>返回<br />头部</span><i class="edufont e-icon-top"></i></li>
    	</ul>
    </aside>  

</script>
</body>
</html>
