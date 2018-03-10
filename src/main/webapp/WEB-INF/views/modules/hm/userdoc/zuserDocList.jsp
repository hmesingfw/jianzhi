<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户文档下载记录管理</title>
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
		<li class="active"><a href="${ctx}/hm/userdoc/zuserDoc/">用户文档下载记录列表</a></li> 
	</ul>
	<form:form id="searchForm" modelAttribute="zuserDoc" action="${ctx}/hm/userdoc/zuserDoc/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>用户：</label>
			 
				<form:select path="userid" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getZuserList()}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>文档：</label> 

				<form:select path="docid" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDocList()}" itemLabel="title" itemValue="id" htmlEscape="false"/>
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
				<th>用户编号</th>
				<th>文档编号</th>
				<th>下载时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="hm:userdoc:zuserDoc:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="zuserDoc">
			<tr>
				<td><a href="${ctx}/hm/userdoc/zuserDoc/form?id=${zuserDoc.id}">				 

					${fns:getZuser(zuserDoc.userid).name}
				</a></td>
				<td>
					${fns:getDoc(zuserDoc.docid).title}
				</td>
				<td>
					<fmt:formatDate value="${zuserDoc.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${zuserDoc.remarks}
				</td>
				<shiro:hasPermission name="hm:userdoc:zuserDoc:edit"><td>
    				<a href="${ctx}/hm/userdoc/zuserDoc/form?id=${zuserDoc.id}">修改</a>
					<a href="${ctx}/hm/userdoc/zuserDoc/delete?id=${zuserDoc.id}" onclick="return confirmx('确认要删除该用户文档下载记录吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>