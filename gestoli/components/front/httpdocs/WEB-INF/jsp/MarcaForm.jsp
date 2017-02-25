<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>
<%@ page buffer="64kb"%>
<%@ page autoFlush="true"%>


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
<div><c:choose>
	<c:when test="${not empty esDoGestor}">
		<c:set var="mostrar" value="true" scope="request" />
	</c:when>
	<c:otherwise>
		<c:set var="mostrar" value="false" scope="request" />
	</c:otherwise>
</c:choose> <c:if test="${mostrar==true}">

	<form id="formulario" action="MarcaForm.html" method="post"
		class="seguit"><c:if test="${not empty formData.id}">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="hidden" />
			<c:param name="path" value="formData.id" />
			<c:param name="camp" value="id" />
		</c:import>
	</c:if> <c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.nom" />
		<c:param name="required" value="required" />
		<c:param name="title">
			<fmt:message key="marca.camp.nom" />
		</c:param>
		<c:param name="camp" value="nom" />
		<c:param name="maxlength" value="64" />
		<c:param name="clase" value="campoFormGrande conMargen" />
	</c:import>

	<div class="etiqueta etiquetaEnLinea <c:out value="${param.required}"/><c:if test="${not empty status.errorMessage}"> error</c:if>">
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="checkbox" />
		<c:param name="path" value="formData.denominacioOrigen" />
		<c:param name="title">
			<fmt:message key="marca.camp.denominacion_origen" />
		</c:param>
		<c:param name="camp" value="denominacioOrigen" />
	</c:import></div>
	
	<div class="etiqueta etiquetaEnLinea <c:out value="${param.required}"/><c:if test="${not empty status.errorMessage}"> error</c:if>">
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="checkbox" />
		<c:param name="path" value="formData.olivaTaula" />
		<c:param name="title">
			<fmt:message key="marca.camp.olivaTaula" />
		</c:param>
		<c:param name="camp" value="olivaTaula" />
	</c:import></div>
	<div class="separadorH"></div>
	<c:if test="${not empty llistatEtiquetatge}">
		<div class="separadorH"></div>

		<div id="llistatEtiquetatge" class="campoForm"><label><fmt:message
			key="marca.camp.etiquetatges" /></label>
		<div class="separadorH"></div>
		<div id="listadoEstrecho"><%-- Tabla de resultados --%>
		<div class="layoutSombraTabla ancho">
		<div class="rellenoInf"></div>
		<div class="rellenoIzq"></div>
		<div class="rellenoDer"></div>
		<div class="supDer"></div>
		<div class="supIzq"></div>
		<div class="infIzq"></div>
		<div class="infDer"></div>
		<display:table name="llistatEtiquetatge" requestURI="" id="etiquetatge" export="true" sort="list" cellpadding="0" cellspacing="0" class="listadoEstrecho selectable">
			<display:column titleKey="tipusEnvas.camp.volum">
				<a href="javascript:submitFormAction('formulario','MarcaForm.html?_target1&amp;etiPosit=<c:out value="${etiquetatge.etiPosition}"/>');"><c:out value="${etiquetatge.tipusEnvas.volum}" /></a>
			</display:column>
			<display:column titleKey="tipusEnvas.camp.color">
				<c:out value="${etiquetatge.tipusEnvas.color.nom}" />
			</display:column>
			<display:column titleKey="tipusEnvas.camp.materialTipusEnvas">
				<c:out value="${etiquetatge.tipusEnvas.materialTipusEnvas.nom}" />
			</display:column>
			<display:column titleKey="tipusEnvas.camp.actiu" headerClass="ancho75 final">
				<c:choose>
					<c:when test="${etiquetatge.actiu}">
						<fmt:message key="manteniment.si" />
					</c:when>
					<c:otherwise>
						<fmt:message key="manteniment.no" />
					</c:otherwise>
				</c:choose>
			</display:column>
			<display:setProperty name="export.xml" value="false" />
			<display:setProperty name="export.csv" value="false" />
			<display:setProperty name="export.rtf" value="false" />
			<display:setProperty name="export.pdf" value="false" />
			<display:setProperty name="export.excel.include_header" value="true" />
			<display:setProperty name="export.excel.filename" value="EtiquetatgesMarca.xls" />
			<display:setProperty name="export.decorated" value="true" />
		</display:table></div>
		</div>

		</div>

		<%-- Colores en tablas --%>
		<script type="text/javascript">
						$(document).ready(function(){
							setEstilosTabla();
							initTableEnlacesRegistros();
						})
					</script>

	</c:if> <c:if test="${not empty formData.id}">
		<div class="separadorH"></div>
		<div
			class="etiqueta <c:out value="${param.required}"/><c:if test="${not empty status.errorMessage}"> error</c:if>">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="checkbox" />
			<c:param name="path" value="formData.actiu" />
			<c:param name="title">
				<fmt:message key="marca.camp.actiu" />
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
			<fmt:message key="marca.camp.observacions" />
		</c:param>
		<c:param name="camp" value="observacions" />
	</c:import></div>

	<div class="separadorH"></div>

	<div class="botonesForm"><c:if test="${not empty formData.id}">
		<div id="guardarForm" class="btnCorto"
			onclick="if(confirm('<fmt:message key="manteniment.modificar.confirm"/>')){submitFormAction('formulario','MarcaForm.html?_finish');}"
			onmouseover="underline('enlaceGuardarForm')"
			onmouseout="underline('enlaceGuardarForm')"><a
			id="enlaceGuardarForm" href="javascript:void(0);"><fmt:message
			key="manteniment.guardar" /></a></div>
	</c:if> <c:if test="${empty formData.id}">
		<div id="guardarForm" class="btnCorto"
			onclick="submitFormAction('formulario','MarcaForm.html?_finish');"
			onmouseover="underline('enlaceGuardarForm')"
			onmouseout="underline('enlaceGuardarForm')"><a
			id="enlaceGuardarForm" href="javascript:void(0);"><fmt:message
			key="manteniment.aceptar" /></a></div>
	</c:if>


	<div id="cancelarForm" class="btnCorto"
		onmouseover="underline('enlaceCancelarForm')"
		onmouseout="underline('enlaceCancelarForm')"
		onclick="document.location ='Marca.html';"><a
		id="enlaceCancelarForm" href="javascript:void(0);"><fmt:message
		key="manteniment.tornar" /></a></div>

	<div id="crearEtiquetaForm" class="btnLargo"
		onmouseover="underline('enlaceCrearEtiquetaForm')"
		onmouseout="underline('enlaceCrearEtiquetaForm')"
		onclick="submitFormAction('formulario','MarcaForm.html?_target1')">
	<a id="enlaceCrearEtiquetaForm" href="javascript:void(0);"><fmt:message
		key="manteniment.nou.etiquetatge" /></a></div>


	<c:if test="${not empty formData.id}">
		<div id="eliminarForm" class="btnCorto"
			onmouseover="underline('enlaceBorrarForm')"
			onmouseout="underline('enlaceBorrarForm')"
			onclick="submitFormConfirm('deleteForm','<fmt:message key="manteniment.esborrar.confirm"/>');">
		<a id="enlaceBorrarForm" href="javascript:void(0);"><fmt:message
			key="manteniment.esborrar" /></a></div>
	</c:if></div>

	</form>

	<form id="deleteForm" action="Marca.html" method="post" class="seguit"
		onsubmit="return confirm('<fmt:message key="manteniment.estasegur"/>')">
	<input id="id" name="id" value="<c:out value="${formData.id}"/>"
		type="hidden" /> <input id="action" name="action" value="delete"
		type="hidden" /></form>

</c:if> <c:if test="${mostrar==false}">
	<fmt:message key="manteniment.noDatos" />
</c:if></div>




</body>
</html>
