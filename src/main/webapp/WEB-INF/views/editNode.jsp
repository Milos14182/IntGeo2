<%@ page language="java" contentType="text/html; charset=Windows-1250"
	pageEncoding="Windows-1250"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<body>
	<div class="container" style="width:50%">
		<c:url value="/admin/edit/${code}" var="edit"/>
		<form:form method="POST" modelAttribute="type" action="${edit}">
			<form:hidden path="id"/>
			<div class="form-group">
				<form:label path="name">
					<spring:message code="admin.edit.label.name" />
				</form:label>
				<form:input path="name" class="form-control" />
				<form:errors path="name" cssClass="error">
					<spring:message code="admin.edit.error.name" />
				</form:errors>
			</div>
			<div class="form-group">
				<form:label path="description">
					<spring:message code="admin.edit.label.description" />
				</form:label>
				<form:textarea path="description" class="form-control" />
			</div>
			<div class="form-inline">
				<form:label path="active">
					<spring:message code="admin.edit.label.active" />
				</form:label>
				<form:checkbox path="active" class="form-control" />
			</div>
			<c:if test="${code == 'city'}">
				<div class="form-group">
					<form:label path="stateData">
						<spring:message code="admin.edit.label.state" />
					</form:label>
					<form:select path="stateData.id">
						<form:option value=""></form:option>
						<c:forEach var="state" items="${states}">
							<form:option value="${state.id}" label="${state.name}" />
						</c:forEach>
					</form:select>
				</div>
			</c:if>	
			<c:if test="${code == 'mountain'}">
				<div class="form-group">
					<form:label path="stateData">
						<spring:message code="admin.edit.label.state" />
					</form:label>
					<form:select path="stateData.id" class="form-control">
						<form:option value=""></form:option>
						<c:forEach var="state" items="${states}">
							<form:option value="${state.id}" label="${state.name}" />
						</c:forEach>
					</form:select>
				</div>
			</c:if>	
			<c:if test="${code == 'river'}">
				<div class="form-group">
					<form:label path="stateDatas">
						<spring:message code="admin.edit.label.state" />
					</form:label>
					<form:select path="stateIds" class="form-control" multiple="multiple">
						<form:option value=""></form:option>
						<c:forEach var="state" items="${states}">
							<form:option value="${state.id}" label="${state.name}" />
						</c:forEach>
					</form:select>
				</div>
			</c:if>		
			<div class="row">
				<div class="col-md-6">
					<button class="btn btn-primary btn-block" type="submit">
						<spring:message code="admin.edit.button.save" />
					</button>
				</div>
				<div class="col-md-6">
					<a href='<c:url value="/admin"/>' class="btn btn-primary btn-block">
						<spring:message code="admin.edit.button.cancel" />
					</a>
				</div>
			</div>
		</form:form>
	</div>
</body>