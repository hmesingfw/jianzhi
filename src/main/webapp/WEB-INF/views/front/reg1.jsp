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
	<script src="https://cdn.bootcss.com/layer/3.1.0/layer.js"></script>
	<script src="${ctxStatic}/jianzhi/layui/layui.js"></script>
	<script type="text/javascript">

		$(function(){
			$("#upload").on("change",function(){
	        	var objUrl = getObjectURL(this.files[0]) ; //获取图片的路径，该路径不是图片在本地的路径
	        	if (objUrl) {
	        		$(".user1-img").css("background", 'url('+objUrl+')');//将图片路径存入src中，显示出图片
	        		$(".user1-img").css("background-size","100% 100%");
	        	}
        	});
		})

		function update(){
			var truename = $("#truename").val();
			var phone = $("#phone").val();
			if(!truename){ 
				layer.msg("请输入真实姓名"); 
				return false; 
			}
			if(!phone){
				layer.msg("请输入联系方式"); 
				return false; 
			}
			
			var email = $("#email").val();
			var reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");
			if(!reg.test(email)){
				layer.msg("请输入正确邮箱地址"); 
				return false; 
			}

			var length = phone.length;
			if(length != 11 || !/^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/.test(phone)){
				layer.msg("请输入正确联系方式"); 
				return false; 
			} 
		}

		//建立一個可存取到該file的url
        function getObjectURL(file) {
        	var url = null ;
        	if (window.createObjectURL!=undefined) { // basic
        		url = window.createObjectURL(file) ;
        	} else if (window.URL!=undefined) { // mozilla(firefox)
        		url = window.URL.createObjectURL(file) ;
        	} else if (window.webkitURL!=undefined) { // webkit or chrome
        		url = window.webkitURL.createObjectURL(file) ;
        	}
        	return url ;
    	}

	</script>
<body>

<div class="reg1">
	<div class="reg1-top">
		<h1>注册信息填写中心</h1>
	</div>
	<form action="${ctxF}/register" id="inputForm" method="post" onsubmit="return update()" enctype="multipart/form-data">
		<div style="text-align: center;">
			<div class="user1-img" >
				<h1>+</h1>
				<p>头像上传</p>
				<input id="upload" name="file" accept="image/*" type="file"/>
			</div>
			<P style="font-size: 16px;">请上传一寸彩色证件照</P>
		</div>
		<input type="hidden" name="password" value="${zuser.password}">
		<input type="hidden" name="type" value="2">
		<input type="hidden" name="status" value="3">
		<ul class="reg2">
			<li>			
				<label><span>＊</span>报考专业：</label>
				<select name="major">
					<c:forEach items="${fns:getCourseSortList()}" var="info">
						<option value="${info.id}">${info.name}</option>
					</c:forEach>
				</select>
			</li>
			<li>
				
				<label>身份证号：</label>
				<input type="text" value="${zuser.idcode}" readonly name="idcode" />
			</li>
			<li>
				
				<label><span>＊</span>真实姓名：</label>
				<input type="text" name="truename" value="" id="truename" maxlength="200" />
			</li>
			<li>
				
				<label>性别：</label>
				<select name="sex">
					<c:forEach items="${fns:getDictList('user_sex')}" var="info">
						<option value="${info.value}">${info.label}</option>
					</c:forEach>
				</select>
			</li>
			<li>
				
				<label>出生年月：</label>
					<input type="text" name="age" value="" id="datetime" maxlength="100" />
			</li>
			<li>
				
				<label><span>＊</span>民族：</label>
				<select name="ethnic">
					<c:forEach items="${fns:getDictList('user_ethnic')}" var="info">
						<option value="${info.value}">${info.label}</option>
					</c:forEach>
				</select>
			</li>
			<li>
				
				<label><span></span>所学专业：</label>
				<input type="text" name="xmajor" value="" maxlength="300" />
			</li>
			<li>
				
				<label><span>＊</span>文化程度：</label>
				<select name="education">
					<c:forEach items="${fns:getDictList('user_education')}" var="info">
						<option value="${info.value}">${info.label}</option>
					</c:forEach>
				</select>
			</li>
			<li>
				
				<label>工作单位：</label>
				<input type="text" name="employer" placeholder="请输入单位全称，没有可以不用填写" value="" maxlength="300" />
			</li>
			<li>
				
				<label><span>＊</span>工作年限：</label>
				<select name="worklength">
					<c:forEach items="${fns:getDictList('user_worklength')}" var="info">
						<option value="${info.value}">${info.label}</option>
					</c:forEach>
				</select>
			</li>
			<li>
				
				<label>联系地址：</label>
				<input type="text" name="adress" value="" maxlength="200" />
			</li>
			<li>
				
				<label>邮政编码：</label>
				<input type="text" name="adresscode" value="" maxlength="20" />
			</li>
			<li>
				
				<label><span>＊</span>联系方式：</label>
				<input type="text" name="phone" value="" id="phone" maxlength="20" />
			</li>
			<li>
				
				<label><span>＊</span>电子邮箱：</label>
				<input type="text" name="email" id="email" value="" maxlength="50" />
			</li>
			
		</ul>
		<div class="reg-button">
			<button>完成注册</button>
		</div>
	</form>
</div>
<script type="text/javascript">
	$(function(){
		layui.use('laydate', function(){
		  	var laydate = layui.laydate;
		  
		  	//执行一个laydate实例
		  	laydate.render({
		    	elem: '#datetime' //指定元素
		  	});
		});
	})
</script>

</body>
</html>
