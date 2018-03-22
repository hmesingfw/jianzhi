<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@page import="com.thinkgem.jeesite.common.utils.sms.tool.SmsAliTool"%>
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
	<link href="${ctxStatic}/jianzhi/layui/css/layui.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/layer/3.0.3/layer.min.js"></script>
    <script type="text/javascript">
    	var msg = '${msg}';
    	$(function(){
    		if(msg!=null &&  msg.length>0){
    			layer.msg(msg);
    		}
    	})
    	var validate = "";	
    	var wait=60; 

    	function update(){    		

    		var password1 = $("#password1").val();
			var password2 = $("#password2").val();

    		if(!password1){
				layer.msg("请输入密码"); 
				return false; 
			} 
			if(password1!=password2){
				layer.msg("两次密码不匹配"); 
				return false; 
			}

			console.log(validate)
			console.log($("#validate").val())

    		if(validate.trim() != $("#validate").val().trim()){
    			layer.msg('手机验证码错误');
    			return false;
    		}


    	}
    	
    	// 获取验证码
	   	function clickphone() {
			var phone = jQuery("#phone").val();
			if(!phone){
	         	layer.msg('请输入手机号.');
	         	return false;
	      	}
	      	var url = "${ctxF }/getValidate?phone="+phone+"&templateid=<%=SmsAliTool.ZHMM_MOULD_ID %>&dayt="+Math.random();
	     	$.post(url,function(msg){
	          	if(msg.trim()=='ERROR'){
	          		layer.msg('发送失败，请输入正确的手机号码.');
	          	} else if(msg.trim()=='NOEXIST'){
	          		layer.msg('该手机号未注册，请输入正确的手机号码.');
	          	} else {
          			validate = msg;
		      		wait = 60;
		      		time();
	          	}
	   		});

	   		
		}
		
		// 发送验证码倒计时		 
		function time() {  
	        if (wait == 0) {  
	            $('#validateButton').attr("disabled",false);   
	            $("#validateButton").html("获取验证码");
	            wait = 60;  
	        } else {  
	            $('#validateButton').attr("disabled",true);    
	            $("#validateButton").html(wait + "秒重发");
	            wait--;  
	            setTimeout(function() {  
	                time()
	            },  
	            1000)
	        }  
	    }
    </script>

    <!--// indexCourseList-->
	
	<div class="batlogin">
		<div class="batlogin1">
			<div class="batlogin1-top">
				<a href="javascript:;">找回密码</a>
				<a href="${ctxF}/gotologin">登陆</a>
			</div>
			<form action="${ctxF}/forgetPwd" id="inputForm" method="post" onsubmit="return update()">
				<div class="batlogin1-center">
					<input type="text" name="phone" id="phone" placeholder="手机号"/>
					<input type="text" name="" id="validate" placeholder="验证码" style="width: 60%;" />
					<button type="button" class="layui-btn layui-btn-normal" id="validateButton" onclick="clickphone()">获取验证码</button>
					<input type="password" name="password" id="password1" placeholder="密码"/>
					<input type="password" name="" id="password2" placeholder="再次输入密码"/>

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
