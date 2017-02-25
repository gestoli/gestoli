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
<title><fmt:message key="consulta.etiquetes.titol" /></title>

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
<form id="formulario" action="ConsultaEtiquetesLlistat.html" method="post" class="extended seguit">
	<fieldset>
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="hidden" />
			<c:param name="path" value="formData.idEstabliment" />
			<c:param name="camp" value="idEstabliment" />
		</c:import>
	</fieldset>
</form>

<c:if test="${llistaEtiquetesAssignades != null && not empty llistaEtiquetesAssignades}">
	<div id="listado"><%-- Tabla de resultados --%>
	<div class="lotes" style="width: 745px; margin: 30px 0pt 0pt 40px;">
	<h1><fmt:message key="consulta.etiquetes.assignades" /></h1>
	</div>
	<div class="layoutSombraTabla ancho">
	<div class="rellenoInf"></div>
	<div class="rellenoIzq"></div>
	<div class="rellenoDer"></div>
	<div class="supDer"></div>
	<div class="supIzq"></div>
	<div class="infIzq"></div>
	<div class="infDer"></div>

	<display:table name="llistaEtiquetesAssignades" requestURI="" id="etiquetesAssignades" sort="list" cellpadding="0" cellspacing="0" class="listadoAncho selectable noEnlace" export="true">
		<%-- display:column property="lot.diposit.id" titleKey="consulta.etiquetes.diposit" headerClass="ancho150" sortable="true" sortProperty="lot.id" group="1"/>
		<display:column property="lot.codiAssignat" titleKey="consulta.etiquetes.lot" headerClass="ancho150" sortable="true" sortProperty="lot.id" group="2"/>
		<display:column property="etiquetaLletra" titleKey="consulta.etiquetes.camp.lletra" headerClass="ancho50" sortable="true" sortProperty="data"/>
		<display:column property="etiquetaInici" titleKey="consulta.etiquetes.camp.inici" headerClass="ancho100" sortable="true"/>
		<display:column property="etiquetaFi" titleKey="consulta.etiquetes.camp.fi" headerClass="ancho100" sortable="true"/>
		<display:column property="capacitat" titleKey="consulta.etiquetes.camp.capacitat" headerClass="ancho100 final" sortable="true"/--%>
		<display:column property="[0]" titleKey="consulta.etiquetes.diposit" headerClass="ancho150" sortable="true"/>
		<display:column property="[1]" titleKey="consulta.etiquetes.lot" headerClass="ancho150" sortable="true"/>
		<display:column property="[2]" titleKey="consulta.etiquetes.camp.lletra" headerClass="ancho50" sortable="true"/>
		<display:column property="[3]" titleKey="consulta.etiquetes.camp.inici" headerClass="ancho100" sortable="true"/>
		<display:column property="[4]" titleKey="consulta.etiquetes.camp.fi" headerClass="ancho100" sortable="true"/>
		<display:column property="[5]" titleKey="consulta.etiquetes.camp.capacitat" headerClass="ancho100 final" sortable="true"/>
		<display:setProperty name="export.xml" value="false" />
	   	<display:setProperty name="export.csv" value="false" />
	   	<display:setProperty name="export.rtf" value="false" />
	   	<display:setProperty name="export.pdf" value="false" />
	   	<display:setProperty name="export.excel.include_header" value="true" />
	   	<display:setProperty name="export.excel.filename" value="Etiquetes_emprades.xls" />
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

<div class="separadorH"></div>

<c:if test="${llistaEtiquetesNoAssignades != null && not empty llistaEtiquetesNoAssignades}">
	<div id="listado"><%-- Tabla de resultados --%>
	<div class="lotes" style="width: 745px; margin: 30px 0pt 0pt 40px;">
	<h1><fmt:message key="consulta.etiquetes.disponibles" /></h1>
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
