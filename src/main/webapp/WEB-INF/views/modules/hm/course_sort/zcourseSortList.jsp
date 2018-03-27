<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>专业分类管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<script type="text/javascript">
		$(document).ready(function () {
			$("#treeTable").treeTable({ expandLevel: 3 }).show();
		}); 
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/hm/course_sort/zcourseSort/">专业分类列表</a></li>
		<shiro:hasPermission name="hm:course_sort:zcourseSort:edit"><li><a href="${ctx}/hm/course_sort/zcourseSort/form?parentId=0">专业分类添加</a></li></shiro:hasPermission>
	</ul>
	 
	<sys:message content="${message}"/>
	<table id="treeTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>名称</th>
				<th>价格</th>
				<th>有效期</th>
				<th>排序</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="hm:course_sort:zcourseSort:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>

		<c:forEach items="${list}" var="zcourseSort">
		
			<tr id="${zcourseSort.id}" pId="${zcourseSort.parentId ne '0'?zcourseSort.parentId:'0'}">
				<td><a href="${ctx}/hm/course_sort/zcourseSort/form?id=${zcourseSort.id}">
					${zcourseSort.name}
				</a></td>
				<td>
					${zcourseSort.price}
				</td>
				<td>
					${zcourseSort.validity}
				</td>
				<td>
					${zcourseSort.sort}
				</td>
				<td>
					<fmt:formatDate value="${zcourseSort.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${zcourseSort.remarks}
				</td>
				<shiro:hasPermission name="hm:course_sort:zcourseSort:edit"><td>
    				<a href="${ctx}/hm/course_sort/zcourseSort/form?id=${zcourseSort.id}">修改</a>
					<a href="${ctx}/hm/course_sort/zcourseSort/delete?id=${zcourseSort.id}" onclick="return confirmx('确认要删除该专业分类吗？', this.href)">删除</a>
					<a href="${ctx}/hm/course_sort/zcourseSort/form?parentId=${zcourseSort.id}">添加下级菜单</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>