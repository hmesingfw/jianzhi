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
	<link href="${ctxStatic}/jianzhi/css/course.css" rel="stylesheet" />
	<script src="https://cdn.bootcss.com/layer/3.1.0/layer.js"></script>
	
	<script type="text/javascript">
		$(document).ready(function() {
			var msg = "${message}";
			if(msg!='') {
				layer.msg(msg);
			}

			var ispay = "${ispay}";
			if('nopay' == ispay){
				randomtest();
			}
		});
		
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
        function sortclick(id, type){
        	$("#currentclick").val(id);

        	if(type == '1'){
        		$("#classflyone").val(id);
        		$("#classflytwo").val('');
        		$("#classflythr").val('');
        	}
        	if(type == '2'){
        		$("#classflytwo").val(id);
        		$("#classflythr").val('');
        	}
        	if(type == '3'){
        		$("#classflythr").val(id);
        	}
        	$("#searchForm").submit();
        }


        function randomtest(){
            

            // var type = $("#classflythr").val() || $("#classflytwo").val() || $("#classflyone").val();
            
            // layer.prompt({
            //     title: '当前专业为购买，是否购买',
            // }, function(value, index, elem){      
            //     console.log(value)
            //     window.location.href='${ctxF}/gotoquestionRandom?type='+type+'&value='+value;             

            //     layer.close(index);
            // });

            layer.confirm('当前专业为购买，是否购买', {
			  	btn: ['确定', '取消'] //可以无限个按钮
			  	,btn3: function(index, layero){
			    //按钮【按钮三】的回调
			  	}
			}, function(index, layero){
			  	//按钮【按钮一】的回调 
			  	var id = '${currentclick}';
			  	window.location.href='${ctxF}/gotopaycourse?type=2&id='+id;

			}, function(index){
			  	//按钮【按钮二】的回调
			
			});
                     
        }
        function moveSort(obj){
        	var type = $(obj).attr('date-type');
        	if('1'==type){
        		$(obj).parent().find('a').show();
        		$(obj).attr('date-type','2');
        	}else{
        		var alist = $(obj).parent().find('a');
        		$.each(alist, function(i){
        			if(i<5){
        				$(this).show();
        			}else{
        				$(this).hide();
        			}
        		})
        		$(obj).attr('date-type','1');
        	}
        }

	</script>
	<style type="text/css">
		.myteam2 a{
			 white-space: nowrap;
			 margin-top: 5px;
			 line-height: 36px;
		}
	</style>
<body>
	<jsp:include page="include/ifie.jsp" />
	
	<jsp:include page="include/header.jsp" />
	<!--// header-->
	
	<section class="i-banner" id="banner">
		<ul>
			<c:forEach items="${aboutBanner}" var="banner" end="4">
	       		<li style="background:url(${banner.filepath}) no-repeat 50% 50%; " onclick="window.location.href='${banner.url}'"></li>
			</c:forEach>
		</ul>
	</section>

	<div class="clear blank20"></div>
   
    <div class="page-width">

	<div class="myteam">
  		<div class="myteam1">
  			<div class="myteam2">
  				<span>分类</span>
	  			<c:forEach items="${sortlist1}" var="info" varStatus="list">	  					  				 
		  			<a href="javascript:;" onclick="sortclick('${info.id}','1')" style="${list.index > 4 ? 'display: none;':''}" ${info.id == classflyone?'class="myteamon"':'' } >${info.name}</a>
		  		</c:forEach>
		  		<button onclick="moveSort(this)" type="button" date-type="1">▼</button>
  			</div>
  		</div>

  		<div class="myteam1">
  			<div class="myteam2">
  				<span>分类</span>
	  			<c:forEach items="${sortlist2}" var="info" varStatus="list">	  				 
		  			<a href="javascript:;" onclick="sortclick('${info.id}','2')" style="${list.index > 4 ? 'display: none;':''}" ${info.id == classflytwo?'class="myteamon"':'' } >${info.name}</a>
		  		</c:forEach>
		  		<button onclick="moveSort(this)" type="button" date-type="1">▼</button>
  			</div>
  		</div>
  		<div class="myteam1">
  			<div class="myteam2">
  				<span>分类</span>
	  			<c:forEach items="${sortlist3}" var="info" varStatus="list">	  				 
		  			<a href="javascript:;" onclick="sortclick('${info.id}','3')" style="${list.index > 4 ? 'display: none;':''}" ${info.id == classflythr?'class="myteamon"':'' } >${info.name}</a>
		  		</c:forEach>
		  		<button onclick="moveSort(this)" type="button" date-type="1">▼</button>
  			</div>
  		</div>
  		
  	</div>
  	<div class="myreg">
  		<form id="searchForm" action="${ctxF}/courseList" method="post" >
	  		<div class="myreg-serch">

	  			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
				<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>

				<input type="hidden" name="classflyone" id="classflyone" value="${classflyone}">
				<input type="hidden" name="classflytwo" id="classflytwo" value="${classflytwo}">
				<input type="hidden" name="classflythr" id="classflythr" value="${classflythr}">

				<input type="hidden" name="currentclick" id="currentclick" value="">

	  			<input type="text" placeholder="输入课程名称" name="title" value="${zcourse.title}" />
	  			<button>搜索</button>
	  		</div>
	  	</form>

  	</div>		
		
		<section class="course-list-wrap">
            

			<div class="course-list bd-all">
				<ul>
					<c:forEach items="${page.list}" var="info">
						<li>
							<div class="course-list-img"><img src="${info.img}" class="m-img imgLoad" /></div>

							<div class="course-list-title"><a href="${ctxF}/coursedetail?id=${info.id}">${info.title}</a></div>
							<div class="course-list-info" style="display: none;">${info.teacher}</div> 
							<div class="reg3"><p>${fns:abbr(info.introduce,250)}	</p></div>
							<div class="course-list-price">
								<span>
									<c:if test="${info.type == 1}">
										免费
									</c:if>
									<c:if test="${info.type != 1}">
										<font>¥</font>${info.price}
									</c:if>									
								</span>
							</div>
							<div class="course-list-button"><a href="${ctxF}/coursedetail?id=${info.id}">马上学习</a></div>
							
						</li>
					</c:forEach>
				</ul>
			</div>
		</section>		
		<div class="pages" style="margin-bottom: 30px">
        	${page.frontToString() }
        </div>	
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
    <script>

    //轮播图
    $('#banner').carousel({
          object:'li',
          scrollType:'slide',
          width:'100%',
          height:'350',
          arrow:true,
          autoPlay:true,
          dots:1,
          speed:400,
          interval:4000,
          

    });
    //懒加载
    imgLoad('m-img','/tp/PC/skin055/images/common/none.png');


    </script>
</body>
</html>
