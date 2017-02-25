<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>
<%@ page import="es.caib.gestoli.front.util.*"%>
<%@ page import="java.util.ResourceBundle"%>
<%@ page import="java.util.Locale"%>
<%@ page buffer="64kb"%>
<%@ page autoFlush="true"%>
<%
	String lang = Idioma.getLang(request);
	request.setAttribute("lang",lang);
%>

<html>
<head>
<title><fmt:message key="proces.retiraretiquetes.titol" /></title>

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
<form id="formulario" action="ProcesRetirarEtiquetesLlistat.html" method="post" class="extended seguit">
	<fieldset>
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="hidden" />
			<c:param name="path" value="formData.idEstabliment" />
			<c:param name="camp" value="idEstabliment" />
		</c:import>
	</fieldset>
</form>

<c:if test="${llistaEtiquetesNoAssignades != null && not empty llistaEtiquetesNoAssignades}">
	<div id="listado"><%-- Tabla de resultados --%>
	<div class="lotes" style="width: 745px; margin: 0;">
	<h1><fmt:message key="consulta.etiquetes.disponibles" /> de ${establimentNom}</h1>
	</div>
	<div class="layoutSombraTabla ancho">
	<div class="rellenoInf"></div>
	<div class="rellenoIzq"></div>
	<div class="rellenoDer"></div>
	<div class="supDer"></div>
	<div class="supIzq"></div>
	<div class="infIzq"></div>
	<div class="infDer"></div>

	<display:table name="llistaEtiquetesNoAssignades" requestURI="" id="etiquetesDisponibles" sort="list" cellpadding="0" cellspacing="0" class="listadoAncho selectable noEnlace"  export="true">
		<display:column property="etiquetaLletra" titleKey="consulta.etiquetes.camp.lletra" headerClass="ancho50" sortable="true"/>
		<display:column property="etiquetaInici" titleKey="consulta.etiquetes.camp.inici" headerClass="ancho100" sortable="true" sortProperty="etiquetaInici"/>
		<display:column property="etiquetaFi" titleKey="consulta.etiquetes.camp.fi" headerClass="ancho100" sortable="true"/>
		<display:column property="capacitat" titleKey="consulta.etiquetes.camp.capacitat" headerClass="ancho100 final" sortable="true"/>
		
		<display:column sortable="false" headerClass="ancho32 final">
			<form id="deleteForm_${etiquetesDisponibles.id}" action="ProcesRetirarEtiquetesEstablimentLlistat.html" method="post" class="mini" onsubmit="return confirm('<fmt:message key="lot.borrar.segur"/>')">
				<input id="id" name="id" value="${etiquetesDisponibles.id}" type="hidden" /> 
				<input id="idEstabliment" name="idEstabliment" value="${param.idEstabliment}" type="hidden" /> 
				<input id="action" name="action" value="delete"	type="hidden" />
				<div id="eliminarForm" class="iconoBorrar" title="<fmt:message key="lot.borrar"/>"
					onclick="submitFormConfirm('deleteForm_${etiquetesDisponibles.id}','<fmt:message key="manteniment.esborrar.confirm"/>');">
				</div>
			</form>
		</display:column>
		
		<display:setProperty name="export.xml" value="false" />
	   	<display:setProperty name="export.csv" value="false" />
	   	<display:setProperty name="export.rtf" value="false" />
	   	<display:setProperty name="export.pdf" value="false" />
	   	<display:setProperty name="export.excel.include_header" value="true" />
	   	<display:setProperty name="export.excel.filename" value="Etiquetes_disponibles.xls" />
	   	<display:setProperty name="export.decorated" value="true" />
	</display:table>
	</div>
	</div>

	<%-- Colores en tablas --%>
	<script type="text/javascript">
			$(document).ready(function(){
				setEstilosTabla();
			})
		</script>
</c:if>

</body>
</html>
