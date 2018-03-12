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
	<script src="https://cdn.bootcss.com/layer/3.1.0/layer.js"></script>
<body>
	<jsp:include page="include/ifie.jsp" />
	
	<jsp:include page="include/header.jsp" />
	<!--// header-->
    

    <!--// indexCourseList-->
    <script type="text/javascript">
    	function isCardNo(card){ 
			  // 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X 
			var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/; 
			if(reg.test(card) === false){ 
			    layer.msg("身份证输入不合法"); 
			    console.log(2)
			    return false; 
			} 
		} 

		function update(){
			var IDcode = $("#IDcode").val();
			var password1 = $("#password1").val();
			var password2 = $("#password2").val();
			
			if(!isCardNo(IDcode)){
				return false; 
			}
			if(!password1){
				layer.msg("请输入密码"); 
				return false; 
			}
			if(password1!=password2){
				layer.msg("两次密码不匹配"); 
				return false; 
			}
		}
    </script>
	
	<div class="batlogin">
		<div class="batlogin1">
			<div class="batlogin1-top">
				<a href="${ctxF}/gotologin">登陆</a>
				<a href="javascript:;">注册</a>
			</div>
			<div class="batlogin1-center">
				<input type="text" name="IDcode" id="IDcode" placeholder="身份证号"/>
				<input type="password" name="password" id="password1" placeholder="密码"/>
				<input type="password" name="" id="password2" placeholder="再次输入密码"/>
				<div class="batlogin1-bottom">
					<p><a href="javascript:;" id="batbox" onclick="batbox(this)"></a>同意阅读相关条款</p>					
				</div>
				<button class="batlogin1-button" onclick="update()" type="button">点击注册</button>
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
