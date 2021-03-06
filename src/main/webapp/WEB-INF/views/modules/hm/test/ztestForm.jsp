<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>试卷管理管理</title>
	<meta name="decorator" content="default"/>	
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});

			$("#type").on('change',function(){
				var option = $(this).find('option:selected').val();
				if(option==2){
					$('.coursetype').hide();
				}else{
					$('.coursetype').show();
				}
			})
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/hm/test/ztest/">试卷列表</a></li>
		<li class="active"><a href="${ctx}/hm/test/ztest/form?id=${ztest.id}">试卷<shiro:hasPermission name="hm:test:ztest:edit">${not empty ztest.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="hm:test:ztest:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="ztest" action="${ctx}/hm/test/ztest/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">试题卷名称：</label>
			<div class="controls">
				<form:input path="title" htmlEscape="false" maxlength="3000" class="input-xlarge "/>

				
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所属专业：</label>
			<div class="controls">
				<%--
				<form:select path="parentid" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getCourseSortList()}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
				--%>
				<sys:treeselect id="office" name="parentid" value="${ztest.parentid}" labelName="${fns:getCourseSort(ztest.parentid).name}" labelValue="${fns:getCourseSort(ztest.parentid).name}"
					title="课程分类" url="/hm/course_sort/zcourseSort/treeData" cssClass="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">试题卷类型：</label>
			<div class="controls">
				<form:select path="type" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('test_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">测试时长：</label>
			<div class="controls">
				<form:input path="testtime" htmlEscape="false" maxlength="20" class="required digits"/>
				<span style="margin-left: 30px;color: red;">单位（分）</span>
			</div>
		</div>

		<div class="control-group" style="display: none;">
			<label class="control-label">题目数量：</label>
			<div class="controls">
				<form:input path="sum" htmlEscape="false" maxlength="20" class="input-xlarge "/>
				<span style="margin-left: 30px;color: red;">试题卷类型为自动时，请输入题目数量</span>
			</div>
		</div>
		<div class="control-group coursetype">
			<label class="control-label">测试题目类型：</label>
			<div class="controls"> 
				<c:forEach items="${fns:getDictList('question_type')}" var="info"> 
					<input type="checkbox" name="testtype" value="${info.value}"  ${fn:indexOf(ztest.testtype, info.value) != -1? 'checked':''  }    >${info.label}
					<input type="text" name="typeval" placeholder="题目数量" class="required digits" maxlength="20" style="margin-left: 20px;"><br>
				</c:forEach>
				 
				<span style="margin-left: 30px;color: red;">试题卷类型为自动时，请选择题目类型，不勾选时，默认为全选</span>
			</div>
		</div>
		<div class="control-group" style="display: none;">
			<label class="control-label">总分数：</label>
			<div class="controls">
				<form:input path="fraction" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="hm:test:ztest:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>