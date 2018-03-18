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
	<link href="${ctxStatic}/jianzhi/layui/css/layui.css" rel="stylesheet">
<body>
	<jsp:include page="include/ifie.jsp" />
	
	<jsp:include page="include/header.jsp" />
	<!--// header-->
    
	<script src="https://cdn.bootcss.com/layer/3.0.3/layer.min.js"></script>
    <script type="text/javascript">
    	var msg = '${msg}';
    	$(function(){
    		if(msg!=null &&  msg.length>0){
    			layer.msg(msg);
    		}
    	})
    </script>
    <!--// indexCourseList-->
	
	<div class="myuser">
		<div class="myuser-top">
			
		</div>
		<div class="myuser-center">

			<jsp:include page="include/myleft.jsp" />
			

			<form action="${ctxF}/register" id="inputForm" method="post" onsubmit="return update()" enctype="multipart/form-data">
				<input type="hidden" name="id" value="${sessionMyinfo.id}">
				<input type="hidden" name="infotype" value="2">
				<div class="myuser-rt">
					<div class="user1">
						<div class="user1-img">
							<h1>+</h1>
							<p>头像上传</p>
							<input id="upload" name="file" accept="image/*" type="file"/>
						</div>
						<div class="user1-input">
							<input type="text" name="password" placeholder="修改登陆密码" value="${sessionMyinfo.password}" />
						</div>
						<div class="user1-input">
							<input type="text" name="xmajor" placeholder="所学专业" value="${sessionMyinfo.xmajor}" />
						</div>
						<div class="user1-input user1-input1">
							<input type="text" placeholder="姓名" name="truename" value="${sessionMyinfo.truename}" />
							<select name="education">
								<c:forEach items="${fns:getDictList('user_education')}" var="info">
									<option value="${info.value}" ${info.value == sessionMyinfo.education ? 'selected':''}>${info.label}</option>
								</c:forEach>
							</select>
						</div>
						<div class="user1-input user1-input1">
							<input type="text" placeholder="工作单位" name="employer" value="${sessionMyinfo.employer}" />
							<select name="worklength">							 
								<c:forEach items="${fns:getDictList('user_worklength')}" var="info">
									<option value="${info.value}" ${info.value == sessionMyinfo.worklength ? 'selected':''}>${info.label}</option>
								</c:forEach>
							</select>
						</div>
						<div class="user1-input">
							<button class="layui-btn layui-btn-normal">保存</button>
						</div>

					</div>
				</div>
			</form>
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
