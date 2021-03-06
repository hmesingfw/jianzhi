<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>文档管理</title>
	<meta name="decorator" content="default"/>
	<script src="https://cdn.bootcss.com/layer/3.1.0/layer.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#all").on("click",function(){	
				var b = $(this).attr("checked");
		      	if(b){
		        	// checkboxAll("ids");
		        	$("input[name='ids']").attr("checked","true");
		     	}else{
			        // clearSelect("ids"); 
			        $("input[name='ids']").removeAttr("checked");
		      	}
		   	});


			$("#alldelete").click(function(){
		   		layer.confirm('确认要删除该文档吗？', {
				  	btn: ['确定', '取消'] //可以无限个按钮
				  	,btn3: function(index, layero){
				    //按钮【按钮三】的回调
				  	}
				}, function(index, layero){
				  //按钮【按钮一】的回调
				  	var ids = "";
				 	$("input[name='ids']:checked").each(function () {
						ids += $(this).val()+"~";
					});
					console.log(ids)
					$("#deleteValue").val(ids);
					$("#deleteform").submit();
				});

		   		    		 	
			});
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
		<li class="active"><a href="${ctx}/hm/doc/zdoc/">文档列表</a></li>
		<shiro:hasPermission name="hm:doc:zdoc:edit"><li><a href="${ctx}/hm/doc/zdoc/form">文档添加</a></li></shiro:hasPermission>
	</ul>
	
	<form action="${ctx}/hm/doc/zdoc/deleteAll" id="deleteform" method="post" style="display: none;">
		<input type="text" name="deleteValue" id="deleteValue" value="">
	</form>

	<form:form id="searchForm" modelAttribute="zdoc" action="${ctx}/hm/doc/zdoc/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><input id="alldelete" class="btn btn-primary" type="button" value="批量删除" /></li> </li>
			<li><label>标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>文件类型：</label>
				<form:select path="filetype" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('doc_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li>
				<label>是否推荐：</label>
				<form:select path="iscom" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('doc_commend')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li>
				<label>文档分类：</label>
				<form:select path="sortid" class="input-medium ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDocSortList()}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li>
				<label>用户类型：</label>
				<form:select path="usertype" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('user_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th><input type="checkbox" name="all" id="all"/></th>
				<th>标题</th>
				<th>文件类型</th>
				<th>浏览量</th>
				<th>下载量</th>
				<th>文档分类</th>
				<th>用户类型</th>
				<th>更新时间</th>
				<shiro:hasPermission name="hm:doc:zdoc:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="zdoc">
			<tr>
				<td><input type="checkbox" name="ids" value="${zdoc.id }" /></td>

				<td><a href="${ctx}/hm/doc/zdoc/form?id=${zdoc.id}">
					${zdoc.title}
				</a></td>
				<td>
					${fns:getDictLabel(zdoc.filetype, 'doc_type', '')}
				</td>
				<td>${zdoc.look}</td>
				<td>${zdoc.down}</td>
				<td>
					${fns:getDocSort(zdoc.sortid).name}
				</td>
				<td>
					${fns:getDictLabel(zdoc.usertype, 'user_type', '')}
				</td>
				
				<td>
					<fmt:formatDate value="${zdoc.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="hm:doc:zdoc:edit"><td>
    				<a href="${ctx}/hm/doc/zdoc/form?id=${zdoc.id}">修改</a>
					<a href="${ctx}/hm/doc/zdoc/delete?id=${zdoc.id}" onclick="return confirmx('确认要删除该文档中心吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>