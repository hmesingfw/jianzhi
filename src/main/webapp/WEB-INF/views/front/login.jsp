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
    <script src="https://cdn.bootcss.com/layer/3.0.3/layer.min.js"></script>
    <script type="text/javascript">
    	var msg = '${msg}';
    	$(function(){
    		if(msg!=null &&  msg.length>0){
    			layer.msg(msg);
    		}
    	})

    	function update(){
    		if(!$("#IDcode").val()){
    			layer.msg('请输入身份证号码');
    			return false;
    		}

    		if(!$("#password").val()){
    			layer.msg('请输入密码');
    			return false;
    		}
    	}
    </script>

    <!--// indexCourseList-->
	
	<div class="batlogin">
		<div class="batlogin1">
			<div class="batlogin1-top">
				<a href="${ctxF}/gotologin">登陆</a>
				<a href="">注册</a>
			</div>
			<form action="${ctxF}/login" id="inputForm" method="post" onsubmit="return update()">
				<div class="batlogin1-center">
					<input type="text" name="IDcode" id="IDcode" placeholder="身份证号"/>
					<input type="password" name="password" id="password" placeholder="密码"/>
					<div class="batlogin1-bottom">
						<p><a href="javascript:;" id="batbox" onclick="batbox(this)"></a>记住登录<label>(初始密码为身份证后六位)</label></p>
						<a href="" class="batlogin1-a">忘记密码？</a>
					</div>
					<button class="batlogin1-button" type="submit">立即登陆</button>
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
