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
  <link href="${ctxStatic}/jianzhi/css/subfont.css" rel="stylesheet" />
  <link href="${ctxStatic}/jianzhi/css/exam.css" rel="stylesheet">

  <script src="https://cdn.bootcss.com/layer/3.1.0/layer.js"></script>
  <script type="text/javascript">
    $(document).ready(function() {
      var msg = "${message}";
      if(msg!='') {
        layer.msg(msg);
      }


        var isnottest = '${isnottest}';
        if('yes'==isnottest){
            layer.confirm('当前试卷没有测试题，请返回试卷列表重新选择', {
                btn: ['确定'] //可以无限个按钮
                ,btn3: function(index, layero){
                //按钮【按钮三】的回调
                }
            }, function(index, layero){
                //按钮【按钮一】的回调 
                window.location.href='${ctxF}/questionlist';
            });
        }
        

    });
    
    function page(n,s){
      $("#pageNo").val(n);
      $("#pageSize").val(s);
      $("#searchForm").submit();
          return false;
        }
        function sortclick(id, type){
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
            if(!$("#classflyone").val() && !$("#classflytwo").val() && !$("#classflythr").val()){
                layer.msg('请选择类型');
                return false;
            }

            var type = $("#classflythr").val() || $("#classflytwo").val() || $("#classflyone").val();
            
            layer.prompt({
                title: '请输入测试题目，输入值超过最大值时取最大题目数量',
            }, function(value, index, elem){      
                console.log(value)
                window.location.href='${ctxF}/gotoquestionRandom?type='+type+'&value='+value;             

                layer.close(index);
            });

                     
        }
  </script>
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


  	<div class="myteam">
        <div class="myteam1">
            <div class="myteam2">
                <span>分类</span>
                <c:forEach items="${sortlist1}" var="info">                  
                    <a href="javascript:;" onclick="sortclick('${info.id}','1')" ${info.id == classflyone?'class="myteamon"':'' } >${info.name}</a>
                </c:forEach>
            </div>
        </div>

        <div class="myteam1">
            <div class="myteam2">
                <span>分类</span>
                <c:forEach items="${sortlist2}" var="info">                  
                    <a href="javascript:;" onclick="sortclick('${info.id}','2')" ${info.id == classflytwo?'class="myteamon"':'' } >${info.name}</a>
                </c:forEach>
            </div>
        </div>
        <div class="myteam1">
            <div class="myteam2">
                <span>分类</span>
                <c:forEach items="${sortlist3}" var="info">                  
                    <a href="javascript:;" onclick="sortclick('${info.id}','3')" ${info.id == classflythr?'class="myteamon"':'' } >${info.name}</a>
                </c:forEach>
            </div>
        </div>
      	
    	<div class="myteam3"> 
    		<a href="javascript:;" onclick="randomtest()">开始随机测试</a>
    	</div>
  	</div>
    <form id="searchForm" action="${ctxF}/questionlist" method="post" >            
            <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
            <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>

            <input type="hidden" name="classflyone" id="classflyone" value="${classflyone}">
            <input type="hidden" name="classflytwo" id="classflytwo" value="${classflytwo}">
            <input type="hidden" name="classflythr" id="classflythr" value="${classflythr}">
    </form>

    <div class="page-width pp_30">
       
        <div class="clear"></div>
    	
        <section class="exam_lmlist bd-style">
                <ul>
                    <c:forEach items="${page.list}" var="info">                    
                        <li>
                           	<div class="btn" onclick="window.location.href='${ctxF}/gotoquestion?id=${info.id}'"><i class="iconfont icon-zhuantilianxi"></i>进入考试</div>
                            <div class="litit"><span>[${fns:getCourseSort(info.parentid).name}]</span><a href="" target="_blank">${info.title}</a></div>
                            <div class="liinfo">
                                <span>时间：${info.testtime}分钟</span>
                                <span>总分：${!empty info.fraction ? info.fraction : '0'}分</span>
                                <!-- <span>0人参与考试</span> -->
                                <span><fmt:formatDate value="${info.updateDate}" pattern="yyyy-MM-dd"/></span>
                            </div>
                        </li>           
                    </c:forEach>      
                </ul>           
        </section>
        <div class="pages" style="margin-bottom: 30px">
            ${page.frontToString() }
        </div>  		  
    </div>
	    

    <jsp:include page="include/footer.jsp" /> 


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
          height:'500',
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