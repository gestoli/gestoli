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
</c:choose> <fmt:message key="diposit.tipusdemant" /></title>

</head>
<body>
<div><c:choose>
	<c:when test="${(not empty esProductor || not empty esEnvasador)}">
		<c:set var="mostrar" value="true" scope="request" />
	</c:when>
	<c:otherwise>
		<c:set var="mostrar" value="false" scope="request" />
	</c:otherwise>
</c:choose> 

<c:if test="${mostrar==true}">
	<form id="formulario" action="DipositForm.html" method="post" class="seguit">
	<c:if test="${not empty formData.id}">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="hidden" />
			<c:param name="path" value="formData.id" />
			<c:param name="camp" value="id" />
		</c:import>
	</c:if> 
	<c:if test="${not empty formData.idOriginal}">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="hidden" />
			<c:param name="path" value="formData.idOriginal" />
			<c:param name="camp" value="idOriginal" />
		</c:import>
	</c:if> 
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="hidden" />
		<c:param name="path" value="formData.posicioX" />
		<c:param name="camp" value="posicioX" />
	</c:import> 
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="hidden" />
		<c:param name="path" value="formData.posicioY" />
		<c:param name="camp" value="posicioY" />
	</c:import> 
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="hidden" />
		<c:param name="path" value="formData.idEstabliment" />
		<c:param name="camp" value="idEstabliment" />
		<c:param name="value" value="${idEstabliment}" />
	</c:import> 
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.codiAssignat" />
		<c:param name="required" value="required" />
		<c:param name="maxlength" value="64" />
		<c:param name="title">
			<fmt:message key="diposit.camp.codi" />
		</c:param>
		<c:param name="camp" value="codiAssignat" />
		<c:param name="clase" value="campoFormMediano" />
	</c:import>

	<div class="separadorH"></div>

	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.capacitat" />
		<c:param name="required" value="required" />
		<c:param name="title">
			<fmt:message key="diposit.camp.capacitat" />
		</c:param>
		<c:param name="camp" value="capacitat" />
		<c:param name="maxlength" value="10" />
		<c:param name="clase" value="campoFormPequeno" />
	</c:import>

	<div class="separadorH"></div>


	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="select" />
		<c:param name="path" value="formData.idMaterialDiposit" />
		<c:param name="title">
			<fmt:message key="diposit.camp.material" />
		</c:param>
		<c:param name="camp" value="idMaterialDiposit" />
		<c:param name="clase" value="campoFormGrande" />
		<c:param name="required" value="required" />
		<c:param name="selectItems" value="materiales" />
		<c:param name="selectItemsId" value="id" />
		<c:param name="selectItemsValue" value="nom" />
		<c:param name="selectSelectedValue"
			value="${formData.materialDiposit.id}" />
	</c:import>

	<div class="separadorH"></div>

	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="select" />
		<c:param name="path" value="formData.idZona" />
		<c:param name="title">
			<fmt:message key="diposit.camp.zona" />
		</c:param>
		<c:param name="camp" value="idZona" />
		<c:param name="clase" value="campoFormGrande" />
		<c:param name="required" value="required" />
		<c:param name="selectItems" value="zonas" />
		<c:param name="selectItemsId" value="id" />
		<c:param name="selectItemsValue" value="nom" />
		<c:param name="selectSelectedValue" value="${formData.zona.id}" />
	</c:import>

	<div class="separadorH"></div>

	<c:if test="${tafenv == true}">
		<div class="separadorH"></div>
		<div class="etiqueta <c:out value="${param.required}"/><c:if test="${not empty status.errorMessage}"> error</c:if>">
			<c:import url="comu/CampFormulari.jsp">
				<c:param name="tipus" value="checkbox" />
				<c:param name="path" value="formData.deEnvasadora" />
				<c:param name="title"><fmt:message key="diposit.camp.envasadora"/></c:param>
				<c:param name="camp" value="deEnvasadora" />
			</c:import>
		</div>
	</c:if>
	
	<c:if test="${not empty formData.id && (not empty esProductor || not empty esEnvasador)}">
		<div class="separadorH"></div>
		<div
			class="etiqueta <c:out value="${param.required}"/><c:if test="${not empty status.errorMessage}"> error</c:if>">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="checkbox" />
			<c:param name="path" value="formData.actiu" />
			<c:param name="title"><fmt:message key="diposit.camp.actiu" /></c:param>
			<c:param name="camp" value="actiu" />
		</c:import></div>
	</c:if>


	<div class="separadorH"></div>

	<div id="observacionesForm"
		class="campoForm <c:out value="${param.required}"/><c:if test="${not empty status.errorMessage}"> error</c:if>">
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="textarea" />
		<c:param name="path" value="formData.observacions" />
		<c:param name="title"><fmt:message key="diposit.camp.observacions" /></c:param>
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
			onclick="document.location ='Diposit.html';"><a
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

	<form id="deleteForm" action="Diposit.html" method="post"
		class="seguit"
		onsubmit="return confirm('<fmt:message key="manteniment.estasegur"/>')">
	<input id="id" name="id" value="<c:out value="${formData.id}"/>"
		type="hidden" /> <input id="action" name="action" value="delete"
		type="hidden" /></form>
	</c:if> 
	<c:if test="${mostrar==false}">
		<fmt:message key="manteniment.noDatos" />
	</c:if>
	</div>
</body>
</html>
