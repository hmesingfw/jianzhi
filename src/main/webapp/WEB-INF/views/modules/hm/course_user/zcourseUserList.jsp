<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户课程学习记录管理</title>
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
		<li class="active"><a href="${ctx}/hm/course_user/zcourseUser/">用户课程学习记录列表</a></li>
		<shiro:hasPermission name="hm:course_user:zcourseUser:edit"><li><a href="${ctx}/hm/course_user/zcourseUser/form">用户课程学习记录添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="zcourseUser" action="${ctx}/hm/course_user/zcourseUser/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>课程编号：</label>
				<form:input path="courseid" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>用户编号：</label>
				<form:input path="userid" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>最近学习时间</th>
				<th>备注</th>
				<shiro:hasPermission name="hm:course_user:zcourseUser:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="zcourseUser">
			<tr>
				<td><a href="${ctx}/hm/course_user/zcourseUser/form?id=${zcourseUser.id}">
					<fmt:formatDate value="${zcourseUser.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					${zcourseUser.remarks}
				</td>
				<shiro:hasPermission name="hm:course_user:zcourseUser:edit"><td>
    				<a href="${ctx}/hm/course_user/zcourseUser/form?id=${zcourseUser.id}">修改</a>
					<a href="${ctx}/hm/course_user/zcourseUser/delete?id=${zcourseUser.id}" onclick="return confirmx('确认要删除该用户课程学习记录吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>