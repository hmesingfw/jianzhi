<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>课程信息管理管理</title>
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
		<li class="active"><a href="${ctx}/hm/course/zcourse/">课程信息管理列表</a></li>
		<shiro:hasPermission name="hm:course:zcourse:edit"><li><a href="${ctx}/hm/course/zcourse/form">课程信息管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="zcourse" action="${ctx}/hm/course/zcourse/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li style="display: none;"><label>老师名称：</label>
				<form:input path="teacher" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>类型：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('course_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>是否推荐：</label>
				<form:select path="iscommend" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('course_iscommend')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>标题</th>
				<th style="display: none;">老师名称</th>
				<th>类型</th>
				<th>特价</th>
				<th>价格</th>
				<th>权重</th>
				<th>推荐</th>
				<th>更新时间</th>
				<th>备注</th>
				<shiro:hasPermission name="hm:course:zcourse:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="zcourse">
			<tr>
				<td><a href="${ctx}/hm/course/zcourse/form?id=${zcourse.id}">
					${zcourse.title}
				</a></td>
				<td style="display: none;">
					${zcourse.teacher}
				</td>
				<td>
					${fns:getDictLabel(zcourse.type, 'course_type', '')}
				</td>
				<td>
					${zcourse.sale}
				</td>
				<td>
					${zcourse.price}
				</td>
				<td>
					${zcourse.weight}
				</td>

				<td>
					${fns:getDictLabel(zcourse.iscommend, 'course_iscommend', '')}
				</td>

				<td>
					<fmt:formatDate value="${zcourse.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${zcourse.remarks}
				</td>
				<shiro:hasPermission name="hm:course:zcourse:edit"><td>
    				<a href="${ctx}/hm/course/zcourse/form?id=${zcourse.id}">修改</a>
					<a href="${ctx}/hm/course/zcourse/delete?id=${zcourse.id}" onclick="return confirmx('确认要删除该课程信息管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>