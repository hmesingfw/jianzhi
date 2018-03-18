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
			
		<c:forEach items="${questlist}" var="info">
			<div class="control-group">
				<label class="control-label">试题标题：</label>
				<div class="controls"> 
					<form:select path="id" class="input-medium">
						<form:option value="${fns:getQuestion(info.question).id}" label="${fns:getQuestion(info.question).title}"/>
						<form:options items="${fns:getQuestionList()}" itemLabel="title" itemValue="id" htmlEscape="false"/>
					</form:select>
					<span class="marginleft20">设定分数：</span>
					<input type="text" name="fractions" value="${info.fraction}" maxlength="10" class="required digits">
				</div>
			</div>
		</c:forEach>
		<c:if test="${fn:length(questlist) == 0}">
			<div class="control-group">
				<label class="control-label">试题标题：</label>
				<div class="controls"> 
					<form:select path="id" class="input-medium">
						<form:option value="" label=""/>
						<form:options items="${fns:getQuestionList()}" itemLabel="title" itemValue="id" htmlEscape="false"/>
					</form:select>
					<span class="marginleft20">设定分数：</span>
					<input type="text" name="fractions" value="" maxlength="10" class="required digits">
				</div>
			</div>			 
		</c:if>
		<div id="addaddress">
			
		</div>
		<div class="form-actions">
			<input onclick="addtest()" class="btn btn-primary" type="button" value="添加题目"/>&nbsp;
			<shiro:hasPermission name="hm:test:ztest:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>

		<script type="text/javascript">
			function addtest(){
				var str = '';
				str += '<div class="control-group">';
				str += '	<label class="control-label">试题标题：</label>';
				str += '	<div class="controls"> ';
				str += '		<form:select path="id" class="input-medium">';
				str += '			<form:option value="" label=""/>';
				str += '			<form:options items="${fns:getQuestionList()}" itemLabel="title" itemValue="id" htmlEscape="false"/>';
				str += '		</form:select>';
				str += '		<span class="marginleft20">设定分数：</span>';
				str += '		<input type="text" name="fractions" value="" maxlength="10" class="required digits">';
				str += '	</div>';
				str += '</div>	';
				$("#addaddress").append(str);
			}
		</script>
	</form:form>
</body>
</html>