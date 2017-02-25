<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>


<html>
<head>
<title><c:choose>
	<c:when test="${not empty formData.id}">
		<fmt:message key="manteniment.modificacio" />
	</c:when>
	<c:otherwise>
		<fmt:message key="manteniment.alta" />
	</c:otherwise>
</c:choose> <fmt:message key="zona.tipusdemant" /></title>

</head>
<body>
<div><c:choose>
	<c:when
		test="${(not empty esProductor || not empty esEnvasador) and not empty est}">
		<c:set var="mostrar" value="true" scope="request" />
	</c:when>
	<c:otherwise>
		<c:set var="mostrar" value="false" scope="request" />
	</c:otherwise>
</c:choose> <c:if test="${mostrar==true}">

	<form id="formulario" action="ZonaForm.html" method="post"
		class="extended seguit zona" enctype="multipart/form-data"><c:if
		test="${not empty formData.id}">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="hidden" />
			<c:param name="path" value="formData.id" />
			<c:param name="camp" value="id" />
		</c:import>
	</c:if> <c:if test="${not empty formData.idOriginal}">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="hidden" />
			<c:param name="path" value="formData.idOriginal" />
			<c:param name="camp" value="idOriginal" />
		</c:import>
	</c:if> <c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.nom" />
		<c:param name="required" value="required" />
		<c:param name="maxlength" value="128" />
		<c:param name="title">
			<fmt:message key="zona.camp.nom" />
		</c:param>
		<c:param name="camp" value="nom" />
		<c:param name="clase" value="campoFormGrande" />
	</c:import>

	<div class="separadorH"></div>


	<c:set var="arxiu" value="${formData.arxiuc}" scope="request" /> <c:import
		url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="file" />
		<c:param name="path" value="formData.imatge" />
		<c:param name="required" value="required" />
		<c:param name="title">
			<fmt:message key="zona.camp.imatgePlanol" />
		</c:param>
		<c:param name="camp" value="imatge" />
		<c:param name="arxiu" value="arxiu" />
		<c:param name="width" value="150" />
		<c:param name="height" value="100" />
		<c:param name="pantallaZona" value="pantallaZona" />
	</c:import> <c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="hidden" />
		<c:param name="path" value="formData.imatgePlanol" />
		<c:param name="camp" value="imatgePlanol" />
	</c:import>


	<div class="separadorH"></div>

	<div class="etiqueta <c:out value="${param.required}"/>
	<c:if test="${not empty status.errorMessage}"> error</c:if>">
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="checkbox" />
		<c:param name="path" value="formData.defecte" />
		<c:param name="title">
			<fmt:message key="zona.camp.defecte" />
		</c:param>
		<c:param name="camp" value="defecte" />
	</c:import></div>
	
		<div class="separadorH"></div>

	<div class="etiqueta <c:out value="${param.required}"/>
	<c:if test="${not empty status.errorMessage}"> error</c:if>">
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="checkbox" />
		<c:param name="path" value="formData.defecteTrasllat" />
		<c:param name="title">
			<fmt:message key="zona.camp.defecteTrasllat" />
		</c:param>
		<c:param name="camp" value="defecteTrasllat" />
	</c:import></div>


	<div class="separadorH"></div>


	<div class="etiqueta <c:out value="${param.required}"/><c:if test="${not empty status.errorMessage}"> error</c:if>">
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="checkbox" />
		<c:param name="path" value="formData.fictici" />
		<c:param name="title">
			<fmt:message key="zona.camp.fictici" />
		</c:param>
		<c:param name="camp" value="fictici" />
	</c:import></div>



	<div class="separadorH"></div>

	<c:if
		test="${not empty formData.id && (not empty esProductor || not empty esEnvasador)}">
		<div class="separadorH"></div>
		<div
			class="etiqueta <c:out value="${param.required}"/><c:if test="${not empty status.errorMessage}"> error</c:if>">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="checkbox" />
			<c:param name="path" value="formData.actiu" />
			<c:param name="title">
				<fmt:message key="zona.camp.actiu" />
			</c:param>
			<c:param name="camp" value="actiu" />
			<c:param name="onchange" value="cambioEstado()" />
		</c:import></div>
		<input type="hidden" id="campoCambioEstado" name="cambioEstadoActivo"
			value="false" />
	</c:if>


	<div class="separadorH"></div>

	<div id="observacionesForm"
		class="campoForm <c:out value="${param.required}"/><c:if test="${not empty status.errorMessage}"> error</c:if>">
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="textarea" />
		<c:param name="path" value="formData.observacions" />
		<c:param name="title">
			<fmt:message key="zona.camp.observacions" />
		</c:param>
		<c:param name="camp" value="observacions" />
	</c:import></div>

	<div class="separadorH"></div>

	<div class="botonesForm"><c:if test="${not empty formData.id}">
		<div id="guardarForm" class="btnCorto"
			onclick="if(confirm('<fmt:message key="manteniment.modificar.confirm"/>')){submitForm('formulario')}"
			onmouseover="underline('enlaceGuardarForm')"
			onmouseout="underline('enlaceGuardarForm')"><a
			id="enlaceGuardarForm" href="javascript:void(0);"><fmt:message
			key="manteniment.guardar" /></a></div>
	</c:if> 
	<c:if test="${empty formData.id}">
		<div id="guardarForm" class="btnCorto"
			onclick="submitForm('formulario')"
			onmouseover="underline('enlaceGuardarForm')"
			onmouseout="underline('enlaceGuardarForm')"><a
			id="enlaceGuardarForm" href="javascript:void(0);"><fmt:message
			key="manteniment.guardar" /></a></div>
	</c:if> 
	<c:if test="${(not empty esProductor || not empty esEnvasador)}">
		<div id="cancelarForm" class="btnCorto"
			onmouseover="underline('enlaceCancelarForm')"
			onmouseout="underline('enlaceCancelarForm')"
			onclick="document.location ='Zona.html';"><a
			id="enlaceCancelarForm" href="javascript:void(0);"><fmt:message
			key="manteniment.tornar" /></a></div>
	</c:if> 
	<c:if test="${not empty formData.id && (not empty esProductor || not empty esEnvasador)}">
		<input id="action" name="action" value="delete" type="hidden" />
		<div id="eliminarForm" class="btnCorto"
			onmouseover="underline('enlaceBorrarForm')"
			onmouseout="underline('enlaceBorrarForm')"
			onclick="submitFormConfirm('deleteForm','<fmt:message key="manteniment.esborrar.confirm"/>');">
		<a id="enlaceBorrarForm" href="javascript:void(0);"><fmt:message
			key="manteniment.esborrar" /></a></div>
	</c:if></div>

	</form>


	<form id="deleteForm" action="Zona.html" method="post" class="seguit"
		onsubmit="return confirm('<fmt:message key="manteniment.estasegur"/>')">
	<input id="id" name="id" value="<c:out value="${formData.id}"/>"
		type="hidden" /> <input id="action" name="action" value="delete"
		type="hidden" /></form>
</c:if> <c:if test="${mostrar==false}">
	<fmt:message key="manteniment.noDatos" />
</c:if></div>
</body>
</html>
