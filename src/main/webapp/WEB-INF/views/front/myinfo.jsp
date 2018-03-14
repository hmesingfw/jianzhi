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
    </script>
    <!--// indexCourseList-->
	
	<div class="myuser">
		<div class="myuser-top">
			
		</div>
		<div class="myuser-center">

			<jsp:include page="include/myleft.jsp" />
			

			<div class="myuser-rt">
				<div class="user1">
					<div class="user1-img">
						<h1>+</h1>
						<p>头像上传</p>
						<input type="file" />
					</div>
					<div class="user1-input">
						<input type="text" placeholder="修改登陆密码"/>
					</div>
					<div class="user1-input">
						<input type="text" placeholder="报名专业" value="${sessionMyinfo.xmajor}" />
					</div>
					<div class="user1-input user1-input1">
						<input type="text" placeholder="姓名" value="${sessionMyinfo.name}" />
						<select>
							<option>学历</option>
							<option>学历</option>
							<option>学历</option>
							<option>学历</option>
						</select>
					</div>
					<div class="user1-input user1-input1">
						<input type="text" placeholder="工作单位"/>
						<select>
							<option>工作经验</option>
							<option>工作经验</option>
							<option>工作经验</option>
							<option>工作经验</option>
						</select>
					</div>
				</div>
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
