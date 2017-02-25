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
</c:choose> <fmt:message key="document.tipusdemant" /></title>

</head>
<body>
<div>

<form id="formulario" name="gestionarInformacioForm"
	action="GestorDocumentalGestionarDocumentForm.html" method="post"
	class="extended seguit zona" enctype="multipart/form-data"><c:if
	test="${not empty formData.id}">
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="hidden" />
		<c:param name="path" value="formData.id" />
		<c:param name="camp" value="id" />
	</c:import>
</c:if> <c:import url="comu/CampFormulari.jsp">
	<c:param name="tipus" value="hidden" />
	<c:param name="path" value="formData.gestorDocumentalInformacio.id" />
	<c:param name="camp" value="gestorDocumentalInformacio.id" />
</c:import>

<div class="separadorH"></div>

<c:import url="comu/CampFormulari.jsp">
	<c:param name="tipus" value="text" />
	<c:param name="path" value="formData.titol" />
	<c:param name="title">
		<fmt:message key="document.camp.titol" />
	</c:param>
	<c:param name="camp" value="campo_titol" />
	<c:param name="name" value="titol" />
	<c:param name="required" value="required" />
	<c:param name="maxlength" value="128" />
	<c:param name="clase" value="campoFormGrande" />
</c:import>


<div class="separadorH"></div>

<c:set var="arxiu" value="${formData.arxiuObject}" scope="request" /> <c:import
	url="comu/CampFormulari.jsp">
	<c:param name="tipus" value="file" />
	<c:param name="path" value="formData.fitxer" />
	<c:param name="required" value="required" />
	<c:param name="title">
		<fmt:message key="document.camp.fitxer" />
	</c:param>
	<c:param name="camp" value="fitxer" />
	<c:param name="arxiu" value="arxiu" />
	<c:param name="width" value="150" />
	<c:param name="height" value="100" />
	<c:param name="document" value="true" />
</c:import> <c:import url="comu/CampFormulari.jsp">
	<c:param name="tipus" value="hidden" />
	<c:param name="path" value="formData.arxiu" />
	<c:param name="camp" value="arxiu" />
</c:import>

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
	onclick="document.location ='GestorDocumentalGestionarInformacioForm.html?id=<c:out value='${formData.gestorDocumentalInformacio.id}'/>';">
<a id="enlaceCancelarForm" href="javascript:void(0);"><fmt:message
	key="manteniment.tornar" /></a></div>

<c:if test="${not empty formData.id}">
	<div id="eliminarForm" class="btnCorto"
		onmouseover="underline('enlaceBorrarForm')"
		onmouseout="underline('enlaceBorrarForm')"
		onclick="submitFormConfirm('deleteForm','<fmt:message key="manteniment.esborrar.confirm"/>');">
	<a id="enlaceBorrarForm" href="javascript:void(0);"><fmt:message
		key="manteniment.esborrar" /></a></div>
</c:if></div>


</form>

<form id="deleteForm" action="GestorDocumentalGestionarDocument.html" method="post"
	class="seguit"
	onsubmit="return confirm('<fmt:message key="manteniment.estasegur"/>')">
<input id="id" name="id" value="<c:out value="${formData.id}"/>"
	type="hidden" /> <input id="idInformacio" name="idInformacio"
	value="<c:out value="${formData.gestorDocumentalInformacio.id}"/>" type="hidden" /> <input
	id="action" name="action" value="delete" type="hidden" /></form>

</div>
</body>
</html>
