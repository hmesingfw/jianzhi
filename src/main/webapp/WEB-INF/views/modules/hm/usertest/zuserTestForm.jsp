<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户答题记录管理</title>
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
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/hm/usertest/zuserTest/">用户答题记录列表</a></li>
		<li class="active"><a href="${ctx}/hm/usertest/zuserTest/form?id=${zuserTest.id}">用户答题记录<shiro:hasPermission name="hm:usertest:zuserTest:edit">${not empty zuserTest.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="hm:usertest:zuserTest:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="zuserTest" action="${ctx}/hm/usertest/zuserTest/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">试卷编号：</label>
			<div class="controls">
				<form:input path="testid" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">当前题目编号：</label>
			<div class="controls">
				<form:input path="questionid" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">选择答案项：</label>
			<div class="controls">
				<form:input path="answerid" htmlEscape="false" maxlength="640" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否已选：</label>
			<div class="controls">
				<form:select path="isselected" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('test_isselected')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否答对：</label>
			<div class="controls">
				<form:select path="istrue" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('test_istrue')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">waile：</label>
			<div class="controls">
				<form:input path="isclick" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="hm:usertest:zuserTest:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>