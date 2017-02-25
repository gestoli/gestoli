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
</c:choose> <fmt:message key="marca.tipusdemant" /></title>

</head>
<body>
<div>

<form id="formulario" class="etiquetatge" action="MarcaForm.html?_target0&id=<c:out value='${formData.id}'/>&amp;guardarEtiq=t"
	method="post" enctype="multipart/form-data">
	<c:if test="${not empty formData.id}">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="hidden" />
			<c:param name="path" value="formData.id" />
			<c:param name="camp" value="id" />
		</c:import>
	</c:if> 
	<c:if test="${not empty formData.etiPosition}">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="hidden" />
			<c:param name="path" value="formData.etiPosition" />
			<c:param name="camp" value="etiPosition" />
		</c:import>
	</c:if>

	<div class="separadorH"></div>

	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="select" />
		<c:param name="path" value="formData.tipusEnvasIdEtiquetatge" />
		<c:param name="title"><fmt:message key="etiquetatge.camp.tipusEnvas" /></c:param>
		<c:param name="camp" value="tipusEnvasIdEtiquetatge" />
		<c:param name="required" value="required" />
		<c:param name="selectItems" value="tipusEnvas" />
		<c:param name="selectItemsId" value="id" />
		<c:param name="selectItemsValue" value="nomSelect" />
		<c:param name="selectSelectedValue"
			value="${formData.tipusEnvasIdEtiquetatge}" />
		<c:param name="clase" value="campoFormGrande conMargen" />
	</c:import>

	<div class="separadorH"></div>

	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="hidden" />
		<c:param name="path" value="formData.errorImagenes" />
		<c:param name="camp" value="errorImagenes" />
	</c:import> 
	<c:set var="arxiuImatgeFrontal" value="${formData.arxiucImatgeFrontal}" scope="request" /> 
	<c:set var="imatgeFrontal" value="${formData.imatgeFrontal}" scope="request" /> 
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="fileImage" />
		<c:param name="path" value="formData.imatgeImatgeFrontal" />
		<c:param name="required" value="required" />
		<c:param name="title"><fmt:message key="etiquetatge.camp.imatgeFrontal" /></c:param>
		<c:param name="camp" value="imatgeImatgeFrontal" />
		<c:param name="arxiu" value="arxiuImatgeFrontal" />
		<c:param name="height" value="150" />
		<c:param name="arxiuId" value="imatgeFrontal" />
	</c:import> 
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="hidden" />
		<c:param name="path" value="formData.imatgeFrontal" />
		<c:param name="camp" value="imatgeFrontal" />
	</c:import>

	<div class="separadorH"></div>

	<c:set var="arxiuImatgePosterior" value="${formData.arxiucImatgePosterior}" scope="request" /> 
	<c:set var="imatgePosterior" value="${formData.imatgePosterior}" scope="request" /> 
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="fileImage" />
		<c:param name="path" value="formData.imatgeImatgePosterior" />
		<c:param name="required" value="required" />
		<c:param name="title"><fmt:message key="etiquetatge.camp.imatgePosterior" /></c:param>
		<c:param name="camp" value="imatgeImatgePosterior" />
		<c:param name="arxiu" value="arxiuImatgePosterior" />
		<c:param name="height" value="150" />
		<c:param name="arxiuId" value="imatgePosterior" />
	</c:import> 
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="hidden" />
		<c:param name="path" value="formData.imatgePosterior" />
		<c:param name="camp" value="imatgePosterior" />
	</c:import>

	<div class="separadorH"></div>
	
	<div class="etiqueta <c:out value="${param.required}"/><c:if test="${not empty status.errorMessage}"> error</c:if>">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="checkbox" />
			<c:param name="path" value="formData.actiuEtiquetatge" />
			<c:param name="title"><fmt:message key="marca.camp.actiu" /></c:param>
			<c:param name="camp" value="actiuEtiquetatge" />
		</c:import>
	</div>

	<div class="separadorH"></div>

	<div id="observacionesForm" class="campoForm <c:out value="${param.required}"/><c:if test="${not empty status.errorMessage}"> error</c:if>">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="textarea" />
			<c:param name="path" value="formData.observacionsEtiquetatge" />
			<c:param name="title"><fmt:message key="zona.camp.observacions" /></c:param>
			<c:param name="camp" value="observacionsEtiquetatge" />
		</c:import>
	</div>

	<div class="separadorH"></div>

	<div class="botonesForm">
		<c:if test="${not empty formData.id}">
			<div id="guardarForm" class="btnCorto"
				onclick="if(confirm('<fmt:message key="manteniment.modificar.confirm"/>')){submitForm('formulario')}"
				onmouseover="underline('enlaceGuardarForm')"
				onmouseout="underline('enlaceGuardarForm')">
				<a id="enlaceGuardarForm" href="javascript:void(0);">
					<fmt:message key="manteniment.guardar" />
				</a>
			</div>
		</c:if> 
		<c:if test="${empty formData.id}">
			<div id="guardarForm" class="btnCorto"
				onclick="submitForm('formulario')"
				onmouseover="underline('enlaceGuardarForm')"
				onmouseout="underline('enlaceGuardarForm')">
				<a id="enlaceGuardarForm" href="javascript:void(0);">
					<fmt:message key="manteniment.guardar" />
				</a>
			</div>
		</c:if>

		<div id="cancelarForm" class="btnCorto"
			onmouseover="underline('enlaceCancelarForm')"
			onmouseout="underline('enlaceCancelarForm')"
			onclick="submitFormAction('formulario','MarcaForm.html?_target0&amp;id=<c:out value='${formData.id}'/>&amp;cancelar=t')">
			<a id="enlaceCancelarForm" href="javascript:void(0);">
				<fmt:message key="manteniment.tornar" />
			</a>
		</div>

		<c:if test="${not empty formData.etiPosition}">
			<div id="eliminarForm" class="btnCorto"
				onmouseover="underline('enlaceBorrarForm')"
				onmouseout="underline('enlaceBorrarForm')"
				onclick="submitFormAction('formulario','MarcaForm.html?_target0&amp;id=<c:out value='${formData.id}'/>&amp;eliminarEtiq=t')">
				<a id="enlaceBorrarForm" href="javascript:void(0);">
					<fmt:message key="manteniment.esborrar" />
				</a>
			</div>
		</c:if>
	</div>
</form>

<form id="deleteForm" action="Fincas.html" method="post" class="seguit"
	onsubmit="return confirm('<fmt:message key="manteniment.estasegur"/>')">
<input id="idE" name="idE" value="<c:out value="${formData.id}"/>"
	type="hidden" /></form>

</div>
</body>
</html>
