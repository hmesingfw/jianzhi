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
	</style>
<body>
	<jsp:include page="include/ifie.jsp" />
	
	<jsp:include page="include/header.jsp" />

	<div class="clear blank20"></div>
    <div class="page-width" style="width: 800px;">
		<div class="pay1">
			<label class="pay1-la2">专业名</label>
			<label class="pay1-la3">有效期</label>
			<label class="pay1-la4">实付</label>
		</div>


		<div class="pay1">
			<label class="pay1-la2">${fns:getCourseSort(zcourse.parentid).name}</label>
			<label class="pay1-la3">${fns:getCourseSort(zcourse.parentid).validity}(天)</label>
			<label class="pay1-la4">￥${fns:getCourseSort(zcourse.parentid).price}</label>
		</div>

		 
  		<form action="${ctxF }/payCourse" id="submitForm" method="post">
  			<input type="hidden" name="id" value="${zcourse.id}">
	  		<div style="position: relative;height: 60px;">
	  			<button style="float: right" class="layui-btn layui-btn-normal">结算</button>
	  		</div>
  		</form>
	</div>

<jsp:include page="include/footer.jsp" /> 
<!--//footer-->

    <aside class="aside-operate">
    	<ul>
    		<li style="border-top:0" onclick="window.open('http://me.cs.com/message.aspx')"><span>留言<br />建议</span><i class="edufont e-icon-yijianfankui"></i></li>
    		<li><span>手机<br />app</span><i class="edufont e-icon-phone"></i><div class="code-box"><em></em><img src="img/wechat-code.png" /><p>下载移动app</p></div></li>
    		<li><span>关注<br />微信</span><i class="edufont e-icon-wechat"></i><div class="code-box"><em></em><img src="img/wechat-code.png" /><p>关注微信公众号</p></div></li>
    		<li onclick="goTop()"><span>返回<br />头部</span><i class="edufont e-icon-top"></i></li>
    	</ul>
    </aside>  


 
</body>
</html>
