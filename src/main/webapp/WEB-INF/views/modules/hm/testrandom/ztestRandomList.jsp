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
	<table id="treeTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>名称</th>
				<th>单选题</th>   
				<th>多选题</th>   
				<th>判断题</th>   
				<th>更新时间</th> 
				<shiro:hasPermission name="hm:testrandom:ztestRandom:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>

		<c:forEach items="${list}" var="zcourseSort">
		
			<tr id="${zcourseSort.id}" pId="${zcourseSort.parentId ne '0'?zcourseSort.parentId:'0'}">
				<td><a href="${ctx}/hm/course_sort/zcourseSort/form?id=${zcourseSort.id}">
					${zcourseSort.name}
				</a></td>
			 	
			 	<td>${fns:getZtestRandom(zcourseSort.id).radio}</td>
			 	<td>${fns:getZtestRandom(zcourseSort.id).checkbox}</td>
			 	<td>${fns:getZtestRandom(zcourseSort.id).judge}</td>
			 
			 
				<td>
					<fmt:formatDate value="${zcourseSort.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				 
				<shiro:hasPermission name="hm:testrandom:ztestRandom:edit">
					<td>
	    				<a href="${ctx}/hm/testrandom/ztestRandom/form?id=${zcourseSort.id}">选题配置</a>
					</td>
				</shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>