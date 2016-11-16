<%@ page language="java" contentType="text/html; charset=Windows-1250"
	pageEncoding="Windows-1250"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<body>
	<c:set var="context" value="${pageContext.request.contextPath}" />
	<ul class="nav nav-tabs">
		<li class="active"><a data-toggle="tab" href="#city"><spring:message
					code="admin.label.city" /></a></li>
		<li><a data-toggle="tab" href="#state"><spring:message
					code="admin.label.state" /></a></li>
		<li><a data-toggle="tab" href="#mountain"><spring:message
					code="admin.label.mountain" /></a></li>
		<li><a data-toggle="tab" href="#lake"><spring:message
					code="admin.label.lake" /></a></li>
		<li><a data-toggle="tab" href="#river"><spring:message
					code="admin.label.river" /></a></li>
		<li><a data-toggle="tab" href="#plant"><spring:message
					code="admin.label.plant" /></a></li>
		<li><a data-toggle="tab" href="#animal"><spring:message
					code="admin.label.animal" /></a></li>
	</ul>
	<div class="tab-content">
		<div id='city' class="tab-pane fade in active">
			<c:if test="${not empty cityDatas}">
				<c:forEach var="city" items="${cityDatas}">
					<div class="row" id="city${city.id}">
						<div class="col-md-4">${city.name}</div>
						<div class="col-md-2">${city.active}</div>
						<div class="col-md-2">
							<div class="btn btn-primary"
								onClick="acceptNode('${context}', 'city', ${city.id})">
								<spring:message code="admin.label.accept" />
							</div>
						</div>
						<div class="col-md-2">
							<div class="btn btn-primary"
								onClick="rejectNode('${context}', 'city', ${city.id})">
								<spring:message code="admin.label.reject" />
							</div>
						</div>
						<div class="col-md-2">
							<a class="btn btn-primary"
								href='<c:url value="/admin/edit/city/${city.id}"/>'> <spring:message
									code="admin.label.edit" />
							</a>
						</div>
					</div>
				</c:forEach>

			</c:if>
		</div>
		<div id='state' class="tab-pane fade">
			<c:if test="${not empty stateDatas}">
				<c:forEach var="state" items="${stateDatas}">
					<div class="row" id="state${state.id}">
						<div class="col-md-4">${state.name}</div>
						<div class="col-md-2">${state.active}</div>
						<div class="col-md-2">
							<div class="btn btn-primary"
								onClick="acceptNode('${context}', 'state', ${state.id})">
								<spring:message code="admin.label.accept" />
							</div>
						</div>
						<div class="col-md-2">
							<div class="btn btn-primary"
								onClick="rejectNode('${context}', 'state', ${state.id})">
								<spring:message code="admin.label.reject" />
							</div>
						</div>
						<div class="col-md-2">
							<a class="btn btn-primary"
								href='<c:url value="/admin/edit/state/${state.id}"/>'> <spring:message
									code="admin.label.edit" />
							</a>
						</div>
					</div>
				</c:forEach>

			</c:if>
		</div>
		<div id='mountain' class="tab-pane fade">
			<c:if test="${not empty mountainDatas}">
				<c:forEach var="mountain" items="${mountainDatas}">
					<div class="row" id="mountain${mountain.id}">
						<div class="col-md-4">${mountain.name}</div>
						<div class="col-md-2">${mountain.active}</div>
						<div class="col-md-2">
							<div class="btn btn-primary"
								onClick="acceptNode('${context}', 'mountain', ${mountain.id})">
								<spring:message code="admin.label.accept" />
							</div>
						</div>
						<div class="col-md-2">
							<div class="btn btn-primary"
								onClick="rejectNode('${context}', 'mountain', ${mountain.id})">
								<spring:message code="admin.label.reject" />
							</div>
						</div>
						<div class="col-md-2">
							<a class="btn btn-primary"
								href='<c:url value="/admin/edit/mountain/${mountain.id}"/>'>
								<spring:message code="admin.label.edit" />
							</a>
						</div>
					</div>
				</c:forEach>
			</c:if>
		</div>
		<div id='lake' class="tab-pane fade">
			<c:if test="${not empty lakeDatas}">
				<c:forEach var="lake" items="${lakeDatas}">
					<div class="row" id="lake${lake.id}">
						<div class="col-md-4">${lake.name}</div>
						<div class="col-md-2">${lake.active}</div>
						<div class="col-md-2">
							<div class="btn btn-primary"
								onClick="acceptNode('${context}', 'lake', ${lake.id})">
								<spring:message code="admin.label.accept" />
							</div>
						</div>
						<div class="col-md-2">
							<div class="btn btn-primary"
								onClick="rejectNode('${context}', 'lake', ${lake.id})">
								<spring:message code="admin.label.reject" />
							</div>
						</div>
						<div class="col-md-2">
							<a class="btn btn-primary"
								href='<c:url value="/admin/edit/lake/${lake.id}"/>'> <spring:message
									code="admin.label.edit" />
							</a>
						</div>
					</div>
				</c:forEach>

			</c:if>
		</div>
		<div id='river' class="tab-pane fade">
			<c:if test="${not empty riverDatas}">
				<c:forEach var="river" items="${riverDatas}">
					<div class="row" id="river${river.id}">
						<div class="col-md-4">${river.name}</div>
						<div class="col-md-2">${river.active}</div>
						<div class="col-md-2">
							<div class="btn btn-primary"
								onClick="acceptNode('${context}', 'river', ${river.id})">
								<spring:message code="admin.label.accept" />
							</div>
						</div>
						<div class="col-md-2">
							<div class="btn btn-primary"
								onClick="rejectNode('${context}', 'river', ${river.id})">
								<spring:message code="admin.label.reject" />
							</div>
						</div>
						<div class="col-md-2">
							<a class="btn btn-primary"
								href='<c:url value="/admin/edit/river/${river.id}"/>'> <spring:message
									code="admin.label.edit" />
							</a>
						</div>
					</div>
				</c:forEach>

			</c:if>
		</div>
		<div id='plant' class="tab-pane fade">
			<c:if test="${not empty plantDatas}">

				<c:forEach var="plant" items="${plantDatas}">
					<div class="row" id="plant${plant.id}">
						<div class="col-md-4">${plant.name}</div>
						<div class="col-md-2">${plant.active}</div>
						<div class="col-md-2">
							<div class="btn btn-primary"
								onClick="acceptNode('${context}', 'plant', ${plant.id})">
								<spring:message code="admin.label.accept" />
							</div>
						</div>
						<div class="col-md-2">
							<div class="btn btn-primary"
								onClick="rejectNode('${context}', 'plant', ${plant.id})">
								<spring:message code="admin.label.reject" />
							</div>
						</div>
						<div class="col-md-2">
							<a class="btn btn-primary"
								href='<c:url value="/admin/edit/plant/${plant.id}"/>'> <spring:message
									code="admin.label.edit" />
							</a>
						</div>
					</div>
				</c:forEach>

			</c:if>
		</div>
		<div id='animal' class="tab-pane fade">
			<c:if test="${not empty animalDatas}">

				<c:forEach var="animal" items="${animalDatas}">
					<div class="row" id="animal${animal.id}">
						<div class="col-md-4">${animal.name}</div>
						<div class="col-md-2">${animal.active}</div>
						<div class="col-md-2">
							<div class="btn btn-primary"
								onClick="acceptNode('${context}', 'animal', ${animal.id})">
								<spring:message code="admin.label.accept" />
							</div>
						</div>
						<div class="col-md-2">
							<div class="btn btn-primary"
								onClick="rejectNode('${context}', 'animal', ${animal.id})">
								<spring:message code="admin.label.reject" />
							</div>
						</div>
						<div class="col-md-2">
							<a class="btn btn-primary"
								href='<c:url value="/admin/edit/animal/${animal.id}"/>'> <spring:message
									code="admin.label.edit" />
							</a>
						</div>
					</div>
				</c:forEach>

			</c:if>
		</div>
	</div>

</body>