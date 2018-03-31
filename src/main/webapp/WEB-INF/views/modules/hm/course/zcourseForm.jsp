<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>课程信息管理管理</title>
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
				if(option==1){
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
		<li><a href="${ctx}/hm/course/zcourse/">课程信息管理列表</a></li>
		<li class="active"><a href="${ctx}/hm/course/zcourse/form?id=${zcourse.id}">课程信息管理<shiro:hasPermission name="hm:course:zcourse:edit">${not empty zcourse.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="hm:course:zcourse:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="zcourse" action="${ctx}/hm/course/zcourse/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">标题：</label>
			<div class="controls">
				<form:input path="title" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">介绍：</label>
			<div class="controls">
				<form:input path="introduce" htmlEscape="false" maxlength="2000" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所属分类：</label>
			<div class="controls">
				<%--
				<form:select path="parentid" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getCourseSortList()}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
				--%>
				<sys:treeselect id="office" name="parentid" value="${zcourse.parentid}" labelName="${fns:getCourseSort(zcourse.parentid).name}" labelValue="${fns:getCourseSort(zcourse.parentid).name}"
					title="课程分类" url="/hm/course_sort/zcourseSort/treeData" cssClass="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">课程封面：</label>
			<div class="controls">
				<form:hidden id="img" path="img" htmlEscape="false" maxlength="400" class="input-xlarge"/>
				<sys:ckfinder input="img" type="images" uploadPath="/hm/course/zcourse" selectMultiple="false"/>

				<span style="margin-left: 40px;color: red">图片推荐尺寸 450*300</span>
			</div>
		</div>
		<div class="control-group" style="display: none;">
			<label class="control-label">老师名称：</label>
			<div class="controls">
				<form:input path="teacher" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group" style="display: none;">
			<label class="control-label">类型：</label>
			<div class="controls">
				<form:select path="type" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('course_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group coursetype" style="display: none;">
			<label class="control-label">特价：</label>
			<div class="controls">
				<form:input path="sale" htmlEscape="false" maxlength="20" class="input-xlarge "/>

				<span style="margin-left: 40px;color: red">价格推荐格式 100.00</span>
			</div>
		</div>
		<div class="control-group coursetype" style="display: none;">
			<label class="control-label">价格：</label>
			<div class="controls">
				<form:input path="price" htmlEscape="false" maxlength="20" class="input-xlarge "/>

				<span style="margin-left: 40px;color: red">价格推荐格式 100.00</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">播放地址：</label>
			<div class="controls">
				<form:input path="files" htmlEscape="false" maxlength="500" class="input-xlarge " />

				<span style="margin-left: 40px;color: red">视频地址为阿里云播放地址			视频格式为mp4</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">权重：</label>
			<div class="controls">
				<form:input path="weight" htmlEscape="false" maxlength="11" class="input-xlarge "/>

				<span style="margin-left: 40px;color: red">权重值输入范围（0-99）</span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">是否推荐：</label>
			<div class="controls">
				<form:select path="iscommend" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('course_iscommend')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="hm:course:zcourse:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>