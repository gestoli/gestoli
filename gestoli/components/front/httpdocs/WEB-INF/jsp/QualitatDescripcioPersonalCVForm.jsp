<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>
<%@ page import="es.caib.gestoli.front.util.*"%>
<%@ page import="java.util.ResourceBundle"%>
<%@ page import="java.util.Locale"%>
<%@ page import="java.util.List"%>
<%

	String lang = Idioma.getLang(request);
	request.setAttribute("lang",lang);
%>

<html>
<head>
<title><c:choose>
	<c:when test="${not empty formData.codi}">
		<fmt:message key="manteniment.modificacio" />
	</c:when>
	<c:otherwise>
		<fmt:message key="manteniment.alta" />
	</c:otherwise>
</c:choose> <fmt:message key="analiticaParametreTipus.tipusdemant" /></title>


<script type="text/javascript" src="js/displaytag.js"></script>
<script type="text/javascript" src="js/calendar/calendar.js"></script>
<script type="text/javascript" src="js/calendar/calendar-setup.js"></script>
<%
		if(lang.equalsIgnoreCase("ca")){
	%>
<script type="text/javascript" src="js/calendar/lang/calendar-ca.js"></script>
<script type="text/javascript" src="js/calendar/lang/calendar-es.js"></script>
<%		
		}else{
	%>
<script type="text/javascript" src="js/calendar/lang/calendar-es.js"></script>
<%
		}
	%>
<link href="js/calendar/calendar-viti.css" rel="stylesheet"
	type="text/css" />

<script type="text/javascript" src="js/form.js"></script>


</head>
<body>

<form id="formulario" name="DescripcioPersonalCVForm" action="QualitatDescripcioPersonalCVForm.html" method="post" class="extended seguit" onsubmit="">
	<c:set var="disabled" value="" />
	<c:if test="${empty esEstAdministrador}">
		<c:set var="disabled" value="true" />
	</c:if>
	
	<c:if test="${not empty formData.codi}">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="hidden" />
			<c:param name="path" value="formData.codi" />
			<c:param name="camp" value="codi" />
		</c:import>
	</c:if> 
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="hidden" />
		<c:param name="path" value="formData.idEstabliment" />
		<c:param name="camp" value="idEstabliment" />
		<c:param name="value" value="${idEstabliment}" />
	</c:import> 
	
	
		<c:if test="${formData.codi != null && not empty formData.codi}">
			<c:if test="${not empty llistatDocuments}">
	
				<div class="campoForm">
					<label for="documents">
						<fmt:message key="informacio.camp.documents" />
					</label>
					<div class="separadorH"></div>
					<div id="listadoEstrecho"><%-- Tabla de resultados --%>
					<div class="layoutSombraTabla">
						<div class="rellenoInf"></div>
						<div class="rellenoIzq"></div>
						<div class="rellenoDer"></div>
						<div class="supDer"></div>
						<div class="supIzq"></div>
						<div class="infIzq"></div>
						<div class="infDer"></div>

						<display:table name="llistatDocuments" requestURI="" id="document"
							pagesize="" sort="list" cellpadding="0" cellspacing="0"
							class="listadoEstrecho selectable" export="true">
							<display:column titleKey="document.camp.titol" headerClass=""
								sortable="true" sortProperty="titol">
								<c:if test="${not empty esEstAdministrador}">
									<a href="GestionarDocumentForm.html?id=<c:out value="${document.id}"/>">
										<c:out value="${document.titol}" />
									</a>
								</c:if>
								<c:if test="${empty esEstAdministrador}">
									<c:out value="${document.titol}" />
								</c:if>
							</display:column>
							<display:column titleKey="document.camp.tamany"
								headerClass="ancho100" sortable="true">
								<c:out value="${document.arxiuObject.normalizeSize}" />
							</display:column>
							<display:column sortable="false" headerClass="ancho32 final">
								<a href="ArxiuMostrar.html?id=<c:out value="${document.arxiu}"/>&download=true">
									<div class="iconoVer" title="<fmt:message key="document.ver"/>"></div>
								</a>
							</display:column>
							<display:setProperty name="export.xml" value="false" />
							<display:setProperty name="export.csv" value="false" />
							<display:setProperty name="export.rtf" value="false" />
							<display:setProperty name="export.pdf" value="false" />
							<display:setProperty name="export.excel.include_header" value="true" />
							<display:setProperty name="export.excel.filename" value="qualitatDescripcioPersonalDocuments.xls" />
							<display:setProperty name="export.decorated" value="true" />
						</display:table>
					</div>
					</div>
				</div>
	
			</c:if>
	
			<div class="separadorH"></div>
			<br /><br />
	
			<c:if test="${not empty esEstAdministrador}">
				<div class="btnLargo203" onmouseover="underline('enlaceCrearForm')"
					onmouseout="underline('enlaceCrearForm')"
					onclick="document.formularioDocument.submit();"><a
					id="enlaceCrearForm" href="javascript:void(0);"><fmt:message
					key="manteniment.creardocument" /></a></div>
			</c:if>
		</c:if>

	<div class="separadorH"></div>
	
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.nom" />
		<c:param name="title">
			<fmt:message key="qualitat.descripcioPersonal.camp.nom" />
		</c:param>
		<c:param name="camp" value="campo_nom" />
		<c:param name="name" value="nom" />
		<c:param name="required" value="required" />
		<c:param name="maxlength" value="128" />
		<c:param name="clase" value="campoFormGrande conMargen" />
		<c:param name="disabled" value="${disabled}" />
	</c:import>
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.llinatges" />
		<c:param name="title">
			<fmt:message key="qualitat.descripcioPersonal.camp.llinatges" />
		</c:param>
		<c:param name="camp" value="campo_llinatges" />
		<c:param name="name" value="llinatges" />
		<c:param name="required" value="required" />
		<c:param name="maxlength" value="128" />
		<c:param name="clase" value="campoFormGrande" />
		<c:param name="disabled" value="${disabled}" />
	</c:import>

	<div class="separadorH"></div>
	
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="calendar" />
		<c:param name="path" value="formData.dataNaixement" />
		<c:param name="title">
			<fmt:message key="qualitat.descripcioPersonal.camp.dataNaixement" />
		</c:param>
		<c:param name="camp" value="campo_dataNaixement" />
		<c:param name="name" value="dataNaixement" />
		<c:param name="required" value="required" />
		<c:param name="maxlength" value="10" />
		<c:param name="aclaracio">
			<fmt:message key="proces.aclaracio.formatdata" />
		</c:param>
		<c:param name="clase" value="campoForm165" />
		<c:param name="disabled" value="${disabled}" />
	</c:import>

	<div class="separadorH"></div>
	
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.dni" />
		<c:param name="title">
			<fmt:message key="qualitat.descripcioPersonal.camp.dni" />
		</c:param>
		<c:param name="camp" value="campo_dni" />
		<c:param name="name" value="dni" />
		<c:param name="required" value="required" />
		<c:param name="maxlength" value="128" />
		<c:param name="clase" value="campoFormGrande conMargen" />
		<c:param name="disabled" value="${disabled}" />
	</c:import>
	
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.telefon" />
		<c:param name="title">
			<fmt:message key="qualitat.descripcioPersonal.camp.telefon" />
		</c:param>
		<c:param name="camp" value="campo_telefon" />
		<c:param name="name" value="telefon" />
		<c:param name="required" value="required" />
		<c:param name="maxlength" value="128" />
		<c:param name="clase" value="campoFormGrande" />
		<c:param name="disabled" value="${disabled}" />
	</c:import>

	<div class="separadorH"></div>
	
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.direccio" />
		<c:param name="title">
			<fmt:message key="qualitat.descripcioPersonal.camp.direccio" />
		</c:param>
		<c:param name="camp" value="campo_direccio" />
		<c:param name="name" value="direccio" />
		<c:param name="required" value="required" />
		<c:param name="maxlength" value="128" />
		<c:param name="clase" value="campoFormCompleto" />
		<c:param name="disabled" value="${disabled}" />
	</c:import>

	<div class="separadorH"></div>
	
	<div id="observacionesForm" class="campoForm">	
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="textarea" />
			<c:param name="path" value="formData.formacio" />
			<c:param name="title">
				<fmt:message key="qualitat.puestoTreball.camp.formacio" />
			</c:param>
			<c:param name="camp" value="campo_formacio" />
			<c:param name="name" value="formacio" />
			<c:param name="required" value="required" />
			<c:param name="maxlength" value="128" />
			<c:param name="disabled" value="${disabled}" />
		</c:import>
	</div>

	<div class="separadorH"></div>
	
	<div id="observacionesForm" class="campoForm">	
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="textarea" />
			<c:param name="path" value="formData.expLaboral" />
			<c:param name="title">
				<fmt:message key="qualitat.puestoTreball.camp.expLaboral" />
			</c:param>
			<c:param name="camp" value="campo_expLaboral" />
			<c:param name="name" value="expLaboral" />
			<c:param name="required" value="required" />
			<c:param name="maxlength" value="128" />
			<c:param name="disabled" value="${disabled}" />
		</c:import>
	</div>

	<div class="separadorH"></div>
	<br></br>

	<div class="botonesForm">
	
		<c:if test="${not empty esEstAdministrador}">
			<c:choose>
				<c:when test="${not empty formData.codi}">
					<div id="guardarForm" class="btnCorto"
						onclick="if(confirm('<fmt:message key="manteniment.modificar.confirm"/>')){submitForm('formulario')}"
						onmouseover="underline('enlaceGuardarForm')"
						onmouseout="underline('enlaceGuardarForm')"><a
						id="enlaceGuardarForm" href="javascript:void(0);"><fmt:message
						key="manteniment.guardar" /></a></div>
				</c:when>
				<c:otherwise>
					<div id="guardarForm" class="btnCorto"
						onclick="submitForm('formulario')"
						onmouseover="underline('enlaceGuardarForm')"
						onmouseout="underline('enlaceGuardarForm')"><a
						id="enlaceGuardarForm" href="javascript:void(0);"><fmt:message
						key="manteniment.guardar" /></a></div>
				</c:otherwise>
			</c:choose>
		</c:if>

		<div class="btnCorto" 
			onmouseover="underline('enlaceTornarForm')"
			onmouseout="underline('enlaceTornarForm')" 
			onclick="document.location ='QualitatDescripcioPersonal.html';"><a
			id="enlaceTornarForm" href="javascript:void(0);"><fmt:message
			key="proces.tornar" /></a></div>


		<c:if test="${not empty esEstAdministrador}">
			<c:if test="${empty formData.codi}">
				<div id="cancelarForm" class="btnCorto"
					onmouseover="underline('enlaceCancelarForm')"
					onmouseout="underline('enlaceCancelarForm')"
					onclick="document.location ='QualitatDescripcioPersonal.html';"><a
					id="enlaceCancelarForm" href="javascript:void(0);"><fmt:message
					key="proces.cancelar" /></a></div>
			</c:if>
			<c:if test="${not empty formData.codi}">
				<input id="action" name="action" value="delete" type="hidden" />
				<div id="eliminarForm" class="btnCorto"
					onmouseover="underline('enlaceBorrarForm')"
					onmouseout="underline('enlaceBorrarForm')"
					onclick="submitFormConfirm('deleteForm','<fmt:message key="manteniment.esborrar.confirm"/>');">
				<a id="enlaceBorrarForm" href="javascript:void(0);"><fmt:message
					key="manteniment.esborrar" /></a></div>
			</c:if>
		</c:if>
	
	</div>

</form>

	<form name="formularioDocument" action="GestionarDocumentForm.html"
		class="seguit"><input id="idPersonal" name="idPersonal"
		value="<c:out value="${formData.codi}"/>" type="hidden" />
	</form>
	<form id="deleteForm" action="QualitatDescripcioPersonal.html" method="post"
		class="seguit"
		onsubmit="return confirm('<fmt:message key="manteniment.estasegur"/>')">
		<input id="idPersonal" name="idPersonal" value="<c:out value="${formData.codi}"/>" type="hidden" /> 
		<input id="action" name="action" value="delete" type="hidden" />
	</form>

<!-- Colores en tablas -->
<script type="text/javascript">
	jQuery(document).ready(function(){
		setEstilosTabla();
	})
</script>
	
</body>
</html>