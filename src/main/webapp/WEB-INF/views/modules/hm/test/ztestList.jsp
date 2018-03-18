<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>试卷管理管理</title>
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
		<li class="active"><a href="${ctx}/hm/test/ztest/">试卷列表</a></li>
		<shiro:hasPermission name="hm:test:ztest:edit"><li><a href="${ctx}/hm/test/ztest/form">试卷添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="ztest" action="${ctx}/hm/test/ztest/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>试题卷名称：</label>
				<form:input path="title" htmlEscape="false" maxlength="3000" class="input-medium"/>
			</li>
			<li><label>类型：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('test_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>试题卷名称</th>
				<th>试题类型</th>
				<th>所属分类</th>
				<th>更新时间</th>
				<th>备注</th>
				<shiro:hasPermission name="hm:test:ztest:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ztest">
			<tr>
				<td><a href="${ctx}/hm/test/ztest/form?id=${ztest.id}">
					${ztest.title}
				</a></td>
				<td>
					${fns:getDictLabel(ztest.type, 'test_type', '')}
				</td>
				<td>
					${fns:getCourseSort(ztest.parentid).name}
				</td>
				<td>
					<fmt:formatDate value="${ztest.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${ztest.remarks}
				</td>
				<shiro:hasPermission name="hm:test:ztest:edit"><td>
    				<a href="${ctx}/hm/test/ztest/form?id=${ztest.id}">修改</a>
    				<a href="${ctx}/hm/test/ztest/questionlist?id=${ztest.id}">试题列表</a>
					<a href="${ctx}/hm/test/ztest/delete?id=${ztest.id}" onclick="return confirmx('确认要删除该试卷管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>