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
        background: #3d72d1 !important;
        color: #fff;
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

                        <input type="hidden" name="time" value="" id="time">

                        <input type="hidden" name="isCorrect" value="" id="isCorrect">
                    </form>

                        <div style="width:100%; overflow:hidden"></div>
                     

                        <div id="PanExam">                           
                            <div id="NavFixHeight" style="clear:both;"></div>
                            <div class="exam-report-cont-blk f-f14" id="page-body" >
                                <div id="content">    
                                    <div class="blank10 clear" id="reclear"></div>
                                    <div class="sjlist" id="xt151921">
                                        <div class="ex-text"> 
                                            <div class="xttitleclass">
                                                <strong>第<span class="xihao">${usertest.sort}</span> 题：(${fns:getDictLabel(zquestion.type, 'question_type', '')})</strong>
                                            </div>
                                            <div class="xtcontentclass">
                                                <p>${zquestion.title}</p>
                                            </div>
                                            <div class="blank10 clear"></div>
                                            <div id="t151921" class="ta">
                                                <div class="ex-text-option ex-option-radio">
                                                    <c:forEach items="${answerList}" var="info" varStatus="list">
                                                        <label class="dx_button">
                                                            <input type="${zquestion.type == 2?'checkbox':'radio'}" iscurret="${info.isCorrect}" name="questioninfo" value="${info.id}" >${info.answer}
                                                        </label>
                                                    </c:forEach>                                     
                                                </div>                                              
                                            </div>                                          
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!-- 答题右侧操作 -->
                            <div class="exam-rightArea">
                                <div id="page-right-frame" class="">

                                    <!--功能操作区域-->

                                    <div class="rightArea-time">
                                        <i id="page-timer-icon"></i>
                                        <span>
                                            <b style=" float:left; font-weight:normal;">剩余时间</b>
                                            <strong>
                                                <span class="fen">${time}</span>                                                 
                                            </strong>
                                        </span>
                                    </div>

                                    <!--答题卡区域-->
                                    <div class="rightArea-answer">
                                        <div class="hd">
                                            <span class="title" style="text-align:left;">答题卡
                                                <span class="card-count" id="page-card-info" style="font-size: 12px;color: #F06000;margin-left: 10px"></span>
                                            </span>
                                        </div>
                                        <div class="bd" id="page_card">
                                            <ul class="idbox" id="idbox">
                                                <dl class="panel-item">
                                                    <dd class="panel-order">

                                                        <c:forEach items="${mytestlist}" var="info" varStatus="list">
                                                            <a href="javascript:;" onclick="gotoQuestion('${info.id}','${test.id}')" class="page-card-item ${info.isselected == 1 ? 'border' : ''}" >${info.sort}</a>
                                                        </c:forEach>
                                                    </dd>
                                                </dl>

                                            </ul>
                                        </div>
                                        <div class="ft">
                                            <div class="btn-1" id="page-btn-next" onclick="xiayiti('${nextusertestid}')">下一题</div>
                                            <div class="btn-2" id="page-btn-submit" onclick="succurrent('1')">交卷</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="clear"></div>

                        </div>
                    
                    <!-- // END -->
                    <div class="clear"></div>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        var questiontype = '${zquestion.type == 2?"2":"1"}';
        function gotoQuestion(mytestid, testid){
            var time = $('.fen').html();
            window.location.href='${ctxF}/gotoQuestion?mytestid='+mytestid+'&testid='+testid+'&time='+time;
        }
        var iscurret = true;        //单选 多选  节点判断
        $(function(){
            var time = '${time}';
            setInterval(function(){
                time--;
                $('.fen').html(time);
                if((time-0)<0){
                    succurrent('2');
                }
            },1000)
        })

        function xiayiti(nextusertestid){
            
            var answer = '';
            var s = $("input[name='questioninfo']");
            $.each(s,function(){
                if($(this).is(":checked")){
                    answer += $(this).val() +';';

                    if(1==questiontype){
                        if('1'==$(this).attr('iscurret')){
                            $("#isCorrect").val('1');
                        }
                    }else{
                        if('1'==$(this).attr('iscurret') && iscurret){
                            $("#isCorrect").val('1');
                        }else{
                            iscurret = false;
                            $("#isCorrect").val('');
                        }
                    }
                    
                }                
            })

            $("#currentanswer").val(answer);
            $("#nextusertestid").val(nextusertestid);


            var fen = $('.fen').text();
            $("#time").val(fen);
            //是否完成答卷
            if(nextusertestid!=''){               
                $("#myform").submit();
            }else{
                layer.confirm('是否完成答卷', {
                    btn: ['确定', '取消'] //可以无限个按钮                    
                }, function(index, layero){
                  //按钮【按钮一】的回调
                    $("#myform").submit();
                }, function(index){
                  //按钮【按钮二】的回调
                });
            }            
        }

        function succurrent(type){
            if('1'==type){
                layer.confirm('是否完成答卷', {
                    btn: ['确定', '取消'] //可以无限个按钮                    
                }, function(index, layero){
                  //按钮【按钮一】的回调
                    gotosuc();
                }, function(index){
                  //按钮【按钮二】的回调
                });
            }else{
                gotosuc();
            }
            

            
        }
        function gotosuc(){
            var answer = '';
            var s = $("input[name='questioninfo']");
            $.each(s,function(){
                if($(this).is(":checked")){
                    answer += $(this).val() +';';

                    if(1==questiontype){
                        if('1'==$(this).attr('iscurret')){
                            $("#isCorrect").val('1');
                        }
                    }else{
                        if('1'==$(this).attr('iscurret') && iscurret){
                            $("#isCorrect").val('1');
                        }else{
                            iscurret = false;
                            $("#isCorrect").val('');
                        }
                    }
                }                
            })
            $("#currentanswer").val(answer);
            $("#myform").submit();
        }


        
    </script>
    <jsp:include page="include/footer.jsp" />
</body>
</html>