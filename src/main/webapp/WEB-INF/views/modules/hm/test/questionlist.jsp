<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>试卷管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});

		
	</script>
	<style type="text/css">
		.marginleft20{
			margin-left: 20px;
		}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/hm/test/ztest/">试卷列表</a></li>
		<li class="active"><a href="${ctx}/hm/test/ztest/form?id=${ztest.id}">试卷<shiro:hasPermission name="hm:test:ztest:edit">${not empty ztest.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="hm:test:ztest:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="ztest" action="${ctx}/hm/test/ztest/questlistsave" method="post" class="form-horizontal">
	
		<input type="hidden" name="testid" value="${testid}">
		<sys:message content="${message}"/>		



		<table id="contentTable" class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>序号</th>
					<th>试题标题</th>
					<th>类型</th>
					<th>默认分数</th>		
					<th>操作</th>		
				</tr>
			</thead>
			<tbody id="quesContent">
			<c:forEach items="${questlist}" var="info" varStatus="list">				
				<tr>
					<input type="hidden" name="id" value="${info.question}">
					<input type="hidden" name="queid" value="${info.question}">
					<td>
						${list.index+1}
					</td>
					<td>
						${fns:getQuestion(info.question).title}
					</td>
					<td>
						${fns:getDictLabel(fns:getQuestion(info.question).type, 'question_type', '')}  
					</td>
					<td >
 						<input type="text" name="fractions" value="${info.fraction}" maxlength="10" class="required digits">
					</td>	
					<td>
						<a href="javascript:;" onclick="delQues(this)">删除</a>
					</td>			
				</tr>
			</c:forEach>
			</tbody>
		</table>	


		<div id="addaddress">
			
		</div>
		<div class="form-actions">
			<input onclick="selQues()" class="btn btn-primary" type="button" value="添加题目"/>&nbsp;
			<shiro:hasPermission name="hm:test:ztest:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>

		<script type="text/javascript">

			function selQues() {
	            var url = "${ctx}/hm/question/zquestion/quesel";
	            windowOpen(url, "选择试题", 1024, 700);
	        }



	        

	        // 删除试题信息
	        function delQues(e) {
	            $(e).parent().parent().remove();
	            // seq('1');
	            // var score = 0;
	            // $("input[name='sco']").each(function () {
	            //     score += parseFloat($(this).val());
	            // })
	            // $("#score").val(score);
	        }
			
			function addQue(ids, title, type, score) {
            // 1. 判断试题是否已经存在
	            var idArr = ids.split("~");
	            var tiArr = title.split("~");
	            var type = type.split("~");
	            var sc = score.split("~");

	            var idlist = '';
	            $("input[name='queid']").each(function () {
	            	idlist += $(this).val()+"~!";
	            })
	            console.log(idlist)
	            var len = $("input[name='queid']").length;
 				 
	            for (var i in idArr) {
	            	console.log(idlist.indexOf(idArr[i]))
	            	if(idlist=='' || idlist.indexOf(idArr[i]) == -1){
	            		len++;

	            		var str = '<tr>';
	            		str += '<input type="hidden" name="id" value="'+idArr[i]+'">';
	            		str += '<td>'+len+'</td>';
	            		str += '<td>'+tiArr[i]+'</td>';
	            		str += '<td>'+type[i]+'</td>';
	            		str += '<td ><input type="text" name="fractions" value="'+sc[i]+'" maxlength="10" class="required digits"></td>';
	            		str += '<td><a href="javascript:;" onclick="delQues(this)">删除</a></td>';
	            		str += '</tr>';
	            		$('#quesContent').append(str);
	            	}	             
	                // if (!ist) {
	                //     // 添加试题
	                //     quesCount = parseInt(quesCount) + 1;
	                //     var va = type[i].split("_");
	                //     var inserthtml = '<tr><td>' + quesCount + '</td>' +
	                //         '<td>' + va[1] + '</td>' +
	                //         '<td>' + tiArr[i] + '</td>' +
	                //         '<td>' + sc[i] + '</td><input type="hidden" name="sco" value="' + sc[i] + '"/>' +
	                //         '<td><a href="javascript:void(0)" onclick="delQues(this)">删除</a>' +
	                //         '<input type="hidden" id="quesType_' + quesCount + '" name="quesType_' + quesCount + '" value="' + va[0] + '"/>' +
	                //         '<input type="hidden" id="quesId_' + quesCount + '" name="queIds" value="' + idArr[i] + '"/></td></tr>'
	                //     $('#quesContent').append(inserthtml);
	                // }
	            }	             
	        }
		</script>
	</form:form>
</body>
</html>