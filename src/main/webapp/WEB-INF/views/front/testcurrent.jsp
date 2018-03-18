<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!doctype html>
<html>
<jsp:include page="include/head.jsp" />
<style type="text/css">
    .Hhide {
        display: none !important;
    }

    .border{
        border: 1px solid red;
    }
    input[type="radio"]{
        background-image: url(${ctxStatic}/jianzhi/img/radio.png);
    }
</style>
<link href="${ctxStatic}/jianzhi/css/exam.css" rel="stylesheet">
<script src="https://cdn.bootcss.com/layer/3.1.0/layer.js"></script>

<body>
    <jsp:include page="include/ifie.jsp" />

    <jsp:include page="include/header.jsp" />

    <div class="gray" style="min-height: 770px;">
        <div class="page-width pp_30">
        </div>     

        <div class="container f-pt15 bg-default">
            <div class="content pd_30">
                <!-- 内容 部分 -->
                <div class="exam-report  page_container">              
               

                    <!--文章标题-->
                    <div class="m-examHead f-bg">
                        <!--试卷头信息-->
                        <h1 class="h1">${test.title}</h1>
                        <!--工具区域-->
                    </div>
                    <form id="myform" name="myform" action="${ctxF}/answer" method="post">
                        <input type="hidden" name="currentanswer" value="" id="currentanswer"> 

                        <input type="hidden" name="myusertestid" value="${myusertestid}">

                        <input type="hidden" name="testid" value="${test.id}">

                        <input type="hidden" name="nextusertestid" id="nextusertestid" value="">
                    </form>

                        <div style="width:100%; overflow:hidden"></div>
                     

                        <div id="PanExam">                           
                            <div id="NavFixHeight" style="clear:both;"></div>
                            <div class="exam-report-cont-blk f-f14" id="page-body" style="width: 1180px">
                                <div id="content">    
                                    <div class="blank10 clear" id="reclear"></div>
                                    <div class="sjlist" id="xt151921">
                                        <div class="ex-text"> 
                                            <div class="xtcontentclass">
                                                <p>本次考试共答对：${iscorrect}题，总得分：${fraction}</p>
                                            </div>                               
                                        </div>
                                    </div>




                                    <c:forEach items="${mytestlist}" var="info" varStatus="list">
                                        <div class="blank10 clear" id="reclear"></div>
                                        <div class="sjlist">
                                            <div class="ex-text">
                                                <div class="xttitleclass">
                                                    <strong>第<span class="xihao">${list.index + 1}</span> 题：</strong>
                                                </div>
                                                <div class="xtcontentclass">
                                                    <p>${fns:getQuestion(info.questionid).title}</p>
                                                </div>
                                                <div class="blank10 clear"></div>
                                                <div class="ta">
                                                    <div class="ex-text-option ex-option-radio">
                                                        <c:forEach items="${fns:getQuestionAnswerByQuestion(info.questionid)}" var="answer">
                                                            <label class="dx_button">
                                                                <input type="${fns:getQuestion(info.questionid).type == 2?'checkbox':'radio'}"  name="${info.questionid}"${fn:indexOf(info.answerid, answer.id) != -1?'checked':''} >
                                                                <span style="${answer.isCorrect == 1?'color:#5aff5a':''}">${answer.answer}</span>
                                                            </label>
                                                        </c:forEach>
                                                    </div>                                               
                                                </div>
                                                <div class="sttj">
                                                    <label class="ctb">
                                                        解析：${fns:getQuestion(info.questionid).analytical}
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>


                                </div>
                            </div>
                        </div>                    
                    <!-- // END -->
                    <div class="clear"></div>
                </div>
            </div>
        </div>
    </div>
   
    <jsp:include page="include/footer.jsp" />
</body>
</html>