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
    

    <!--// indexCourseList-->
	
	<div class="myuser">
		<div class="myuser-top">
			
		</div>
		<div class="myuser-center">
			<div class="myuser-lt">
				<div class="myuser1">
					<img src="img/116815.jpg"/>
					<p>王晓伟</p>
				</div>
				<div class="myuser2">
					<a href="">个人资料</a>
					<a href="">我的课程</a>
					<a href="">学习记录</a>
					<a href="">文档下载</a>
				</div>
			</div>
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
						<input type="text" placeholder="报名专业"/>
					</div>
					<div class="user1-input user1-input1">
						<input type="text" placeholder="姓名"/>
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

<script src="js/imgload.js"></script>
<script src="js/cs.assembly.js"></script>
<!--执行页面加载js-->
<script src="js/page.index.js"></script>
<script src="js/page.load.js"></script>
<script src="js/page.main.js"></script>
<script>
	//开启分类
	setNavFlag(0);

  var userUrl = {
      index:'http://me.cs.com/user.aspx',//个人中心
      loginout:'http://me.cs.com/loginout.aspx',//退出登录
      editinfo:'/user.aspx?a-basic,f-editinfo',//修改资料
      message:'/user.aspx?a-message,f-Receivedmessage',//站内消息
      myCourse:'http://me.cs.com/user.aspx',//我的课程
      courseOrder:'/user.aspx?a-course,f-MyCourseOrder',//我的订单
      userLogin:'http://me.cs.com/login.aspx',//登录
      courseUrl:'/user.aspx?a-basic,f-main,courseid-'
  	  
  }

  //获取用户登录信息
  getUserInfo(userUrl,'main','index');

//轮播图
$('#carousel').carousel({
	  object:'li',
	  scrollType:'slide',
	  width:'100%',
	  height:'500',
	  arrow:true,
	  autoPlay:true,
	  dots:1,
	  speed:400,
	  interval:4000
});

//评分计算
coursePfCount('.index-cos-star');

var subClassNum = 8;
$(".index-title-subclass a").each(function() {
	$(this).attr("data-index", $(this).index() + 1)
});
$(".index-title-subclass a").each(function() {
	if ($(this).attr("data-index") > subClassNum) {
		$(this).remove()
	}
});
$(".con02").find(".jx2").hover(function() {
	$(this).find(".qg-info").fadeIn(300)
}, function() {
	$(this).find(".qg-info").fadeOut(300)
});
$(".indexCourseList").find(".box").each(function() {
	var courseLength = $(this).find("li").length;
	if (courseLength == 0) {
		$(this).remove()
	}
});
//懒加载
imgLoad('m-img','/tp/PC/skin055/images/common/none.png');
function batbox(obj){
	if($("#batbox").hasClass("batbox1")){
		$(obj).removeClass("batbox1")
	}
	
	else{
		$(obj).addClass("batbox1")
	}
}
</script>
</body>
</html>
