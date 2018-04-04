<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户信息管理</title>
	<meta name="decorator" content="default"/>
	<script src="https://cdn.bootcss.com/layer/3.1.0/layer.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnImport").click(function(){
				$.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true}, 
					bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
			});


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
		   		layer.confirm('确认要删除该用户吗？', {
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
	<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/hm/user/zuser/import" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctxF}/docDownUsermodel">下载模板</a>
		</form>
	</div>

	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/hm/user/zuser/">用户信息列表</a></li>
		<shiro:hasPermission name="hm:user:zuser:edit"><li><a href="${ctx}/hm/user/zuser/form">用户信息添加</a></li></shiro:hasPermission>
	</ul>

	<form action="${ctx}/hm/user/zuser/deleteAll" id="deleteform" method="post" style="display: none;">
		<input type="text" name="deleteValue" id="deleteValue" value="">
	</form>

	<form:form id="searchForm" modelAttribute="zuser" action="${ctx}/hm/user/zuser/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">		
			<li><input id="alldelete" class="btn btn-primary" type="button" value="批量删除" /></li> </li>	
			<li><label>身份证：</label>
				<form:input path="idcode" htmlEscape="false" maxlength="18" class="input-medium"/>
			</li>
			<li><label>真实姓名：</label>
				<form:input path="truename" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>联系电话：</label>
				<form:input path="phone" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li>
				<label>用户状态：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('user_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li>
				<label>用户类型：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('user_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li> 
			<li class="btns">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
				<input id="btnImport" class="btn btn-primary" type="button" value="导入"/></li>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>		
				<th><input type="checkbox" name="all" id="all"/></th>		
				<th>身份证</th>
				<th>真实姓名</th>
				<th>联系电话</th>
				<th>创建时间</th>
				<th>备注</th>
				<shiro:hasPermission name="hm:user:zuser:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="zuser">
			<tr>
				<td><input type="checkbox" name="ids" value="${zuser.id }" /></td>
				<td><a href="${ctx}/hm/user/zuser/form?id=${zuser.id}">
					${zuser.idcode}
				</td></a>
				<td>
					${zuser.truename}
				</td>
				<td>
					${zuser.phone}
				</td>
				<td>
					<fmt:formatDate value="${zuser.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${zuser.remarks}
				</td>
				<shiro:hasPermission name="hm:user:zuser:edit"><td>
    				<a href="${ctx}/hm/user/zuser/form?id=${zuser.id}">修改</a>
					<a href="${ctx}/hm/user/zuser/delete?id=${zuser.id}" onclick="return confirmx('确认要删除该用户信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>