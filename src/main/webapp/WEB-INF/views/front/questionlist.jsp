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
  <script type="text/javascript">
    $(document).ready(function() {
      var msg = "${message}";
      if(msg!='') {
        layer.msg(msg);
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
  </script>
<body>
  <jsp:include page="include/ifie.jsp" />
  
  <jsp:include page="include/header.jsp" />
  <!--// header-->
  
  <section class="i-banner" id="banner">
    <ul>
      <c:forEach items="${aboutBanner}" var="banner" end="4">
            <a href="${banner.url}" target="_blank"><li style="background:url(${banner.filepath}) no-repeat 50% 50%; "></li> </a>
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
    		<h1>随机组题<span>▶</span></h1>
    		<a href="">开始测试</a>
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
                    
                    
                    <li>
                         	<div class="btn" onClick="window.open('http://me.cs.com/exam.aspx?do-confirm,id-6529')"> <i class="iconfont icon-zhuantilianxi"></i>进入考试</div>
                          <div class="litit"><span>[中级会计师]</span><a href="http://me.cs.com/exam/paper-6529.aspx" title="会计考试题库" target="_blank">会计考试题库</a></div>
                          <div class="liinfo"><span>时间：120分钟</span><span>总分：20分</span><span>0人参与考试</span><span>2017-09-02</span></div>
                    </li>           
                    
                    
                    <li>
                         	<div class="btn" onClick="window.open('http://me.cs.com/exam.aspx?do-confirm,id-6174')"> <i class="iconfont icon-zhuantilianxi"></i>进入考试</div>
                          <div class="litit"><span>[中级会计实务]</span><a href="http://me.cs.com/exam/paper-6174.aspx" title=" 中级会计实务第一套试卷" target="_blank"> 中级会计实务第一套试卷</a></div>
                          <div class="liinfo"><span>时间：120分钟</span><span>总分：9分</span><span>1人参与考试</span><span>2017-07-17</span></div>
                    </li>
                    
                </ul>           
        </section>
        <div class="paging">
            <div class="paging-box">
                <a href="javascript:;"><i class="icon-prev"></i></a>
                <a href="#" class="curr">1</a>
                <a class="num" href="/examlist/list.aspx?p-2">2</a>
                <a href="/examlist/list.aspx?p-2"><i class="icon-next"></i></a>
            </div>
        </div>		  
    </div>
	    

    <jsp:include page="include/footer.jsp" /> 
</body>
</html>