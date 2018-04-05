<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!doctype html>
<html>
	<jsp:include page="include/head.jsp" />
	<link rel="stylesheet" href="${ctxStatic}/jianzhi/css/iconfont.css"  />
	<link href="${ctxStatic}/jianzhi/layui/css/layui.css" rel="stylesheet">

	<style type="text/css">
		.Hhide{
			display: none !important;
		}
		.mypay{
			background: #3c79d1;
		}
		.mypay label{
			color:#fff;
		}
		.page-width11{
		 	margin: 30px auto;
		 	box-shadow: 0px 0px 5px #ccc
		}
	</style>	
		
<body>
	<jsp:include page="include/ifie.jsp" />
	
	<jsp:include page="include/header.jsp" />

	<div class="clear blank20"></div>
    <div class="page-width page-width11" style="width: 800px;">
		<div class="pay1 mypay">
			<label class="pay1-la2">专业名</label>
			<label class="pay1-la3">有效期</label>
			<label class="pay1-la4">实付</label>
		</div>

		<c:if test="${type == 1}">
			<div class="pay1">
				<label class="pay1-la2">${zcourse.title}</label>
				<label class="pay1-la3">${fns:getCourseSort(zcourse.parentid).validity}(天)</label>
				<label class="pay1-la4">￥${zcourse.price}</label>
			</div>
		</c:if>
		
		<c:if test="${type != 1}">
			<div class="pay1">
			<label class="pay1-la2">${fns:getCourseSort(id).name}</label>
			<label class="pay1-la3">${fns:getCourseSort(id).validity}(天)</label>
			<label class="pay1-la4">￥${fns:getCourseSort(id).price}</label>
		</div>
		</c:if>

		 
  		<form action="${ctxF }/payCourse" id="submitForm" method="post">
  			<input type="hidden" name="type" value="${type}">
  			<input type="hidden" name="id" value="${id}">
	  		<div style="position: relative;height: 60px;line-height: 60px;text-align: center;">
	  			<button class="layui-btn layui-btn-normal">结算</button>
	  		</div>
  		</form>
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
