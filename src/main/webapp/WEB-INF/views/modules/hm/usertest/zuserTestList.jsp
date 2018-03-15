<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户答题记录管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/hm/usertest/zuserTest/">用户答题记录列表</a></li>
		<shiro:hasPermission name="hm:usertest:zuserTest:edit"><li><a href="${ctx}/hm/usertest/zuserTest/form">用户答题记录添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="zuserTest" action="${ctx}/hm/usertest/zuserTest/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>试卷编号：</label>
				<form:input path="testid" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>当前题目编号：</label>
				<form:input path="questionid" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>选择答案项：</label>
				<form:input path="answerid" htmlEscape="false" maxlength="640" class="input-medium"/>
			</li>
			<li><label>是否已选：</label>
				<form:select path="isselected" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('test_isselected')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>是否答对：</label>
				<form:select path="istrue" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('test_istrue')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>答题时间</th>
				<shiro:hasPermission name="hm:usertest:zuserTest:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="zuserTest">
			<tr>
				<td><a href="${ctx}/hm/usertest/zuserTest/form?id=${zuserTest.id}">
					<fmt:formatDate value="${zuserTest.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<shiro:hasPermission name="hm:usertest:zuserTest:edit"><td>
    				<a href="${ctx}/hm/usertest/zuserTest/form?id=${zuserTest.id}">修改</a>
					<a href="${ctx}/hm/usertest/zuserTest/delete?id=${zuserTest.id}" onclick="return confirmx('确认要删除该用户答题记录吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>