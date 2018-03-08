<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>文档分类管理</title>
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
		<li class="active"><a href="${ctx}/hm/docsort/zdocSort/">文档分类列表</a></li>
		<shiro:hasPermission name="hm:docsort:zdocSort:edit"><li><a href="${ctx}/hm/docsort/zdocSort/form?parent=0">文档分类添加</a></li></shiro:hasPermission>
	</ul>	
	<sys:message content="${message}"/>
	<table id="treeTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>名称</th>
				<th>排序</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="hm:docsort:zdocSort:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>		

			<c:forEach items="${list}" var="menu">
				<tr id="${menu.id}" pId="${menu.parent ne '0'?menu.parent:'0'}">

					<td nowrap><a href="javascript:;">${menu.name}</a></td>		
					<td>${menu.sort}</td>
					<td>
						<fmt:formatDate value="${menu.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td>${menu.remarks}</td>	
					<shiro:hasPermission name="hm:docsort:zdocSort:edit">
						<td nowrap>
							<a href="${ctx}/hm/docsort/zdocSort/form?id=${menu.id}">修改</a>
							<a href="${ctx}/hm/docsort/zdocSort/delete?id=${menu.id}" onclick="return confirmx('要删除该菜单及所有子菜单项吗？', this.href)">删除</a>
							<a href="${ctx}/hm/docsort/zdocSort/form?parent=${menu.id}">添加下级菜单</a>
						</td>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>