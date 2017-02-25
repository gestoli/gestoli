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
</c:choose> <fmt:message key="tipusEnvas.tipusdemant" /></title>

</head>
<body>
<div><c:choose>
	<c:when test="${not empty esDoGestor}">
		<c:set var="mostrar" value="true" scope="request" />
	</c:when>
	<c:otherwise>
		<c:set var="mostrar" value="false" scope="request" />
	</c:otherwise>
</c:choose> <c:if test="${mostrar==true}">

	<form id="formulario" action="TipusEnvasForm.html" method="post"
		class="seguit"><c:if test="${not empty formData.id}">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="hidden" />
			<c:param name="path" value="formData.id" />
			<c:param name="camp" value="id" />
		</c:import>
	</c:if> <c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.volum" />
		<c:param name="required" value="required" />
		<c:param name="title">
			<fmt:message key="tipusEnvas.camp.volum" />
		</c:param>
		<c:param name="camp" value="volum" />
		<c:param name="maxlength" value="10" />
		<c:param name="clase" value="campoFormPequeno" />
	</c:import>

	<div class="separadorH"></div>

	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="select" />
		<c:param name="path" value="formData.materialTipusEnvasId" />
		<c:param name="title">
			<fmt:message key="tipusEnvas.camp.materialTipusEnvas" />
		</c:param>
		<c:param name="camp" value="materialTipusEnvasId" />
		<c:param name="required" value="required" />
		<c:param name="selectItems" value="materialesTipusEnvas" />
		<c:param name="selectItemsId" value="id" />
		<c:param name="selectItemsValue" value="nom" />
		<c:param name="selectSelectedValue"
			value="${formData.materialTipusEnvas.id}" />
		<c:param name="clase" value="campoFormGrande conMargen" />
	</c:import> <c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="select" />
		<c:param name="path" value="formData.colorId" />
		<c:param name="title">
			<fmt:message key="tipusEnvas.camp.color" />
		</c:param>
		<c:param name="camp" value="colorId" />
		<c:param name="required" value="required" />
		<c:param name="selectItems" value="colores" />
		<c:param name="selectItemsId" value="id" />
		<c:param name="selectItemsValue" value="nom" />
		<c:param name="selectSelectedValue" value="${formData.color.id}" />
		<c:param name="clase" value="campoFormGrande" />
	</c:import> <c:if test="${not empty formData.id}">
		<div class="separadorH"></div>
		<div
			class="etiqueta <c:out value="${param.required}"/><c:if test="${not empty status.errorMessage}"> error</c:if>">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="checkbox" />
			<c:param name="path" value="formData.actiu" />
			<c:param name="title">
				<fmt:message key="tipusEnvas.camp.actiu" />
			</c:param>
			<c:param name="camp" value="actiu" />
		</c:import></div>
	</c:if>

	<div class="separadorH"></div>

	<div id="observacionesForm"
		class="campoForm <c:out value="${param.required}"/><c:if test="${not empty status.errorMessage}"> error</c:if>">
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="textarea" />
		<c:param name="path" value="formData.observacions" />
		<c:param name="title">
			<fmt:message key="tipusEnvas.camp.observacions" />
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
	</c:if> <c:if test="${empty formData.id}">
		<div id="guardarForm" class="btnCorto"
			onclick="submitForm('formulario')"
			onmouseover="underline('enlaceGuardarForm')"
			onmouseout="underline('enlaceGuardarForm')"><a
			id="enlaceGuardarForm" href="javascript:void(0);"><fmt:message
			key="manteniment.guardar" /></a></div>
	</c:if>

	<div id="cancelarForm" class="btnCorto"
		onmouseover="underline('enlaceCancelarForm')"
		onmouseout="underline('enlaceCancelarForm')"
		onclick="document.location ='TipusEnvas.html';"><a
		id="enlaceCancelarForm" href="javascript:void(0);"><fmt:message
		key="manteniment.tornar" /></a></div>

	<c:if test="${not empty formData.id}">
		<input id="action" name="action" value="delete" type="hidden" />
		<div id="eliminarForm" class="btnCorto"
			onmouseover="underline('enlaceBorrarForm')"
			onmouseout="underline('enlaceBorrarForm')"
			onclick="submitFormConfirm('deleteForm','<fmt:message key="manteniment.esborrar.confirm"/>');">
		<a id="enlaceBorrarForm" href="javascript:void(0);"><fmt:message
			key="manteniment.esborrar" /></a></div>
	</c:if></div>

	</form>

	<form id="deleteForm" action="TipusEnvas.html" method="post"
		class="seguit"
		onsubmit="return confirm('<fmt:message key="manteniment.estasegur"/>')">
	<input id="id" name="id" value="<c:out value="${formData.id}"/>"
		type="hidden" /> <input id="action" name="action" value="delete"
		type="hidden" /></form>

</c:if> <c:if test="${mostrar==false}">
	<fmt:message key="manteniment.noDatos" />
</c:if></div>
</body>
</html>
