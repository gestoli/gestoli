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
<title><fmt:message key="consulta.oliComercialitzat.titol" /></title>
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
<script type="text/javascript" src="js/displaytag.js"></script>
</head>
<body>


<form id="formulario" action="ConsultaOlivaTaulaComercialitzadaEstablimentLlistat.html" method="post" class="extended seguit">
<fieldset>
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="calendar" />
		<c:param name="path" value="formData.dataInici" />
		<c:param name="required" value="required" />
		<c:param name="title">
			<fmt:message key="consulta.camp.dataInici" />
		</c:param>
		<c:param name="camp" value="dataInici" />
		<c:param name="maxlength" value="10" />
		<c:param name="aclaracio">
			<fmt:message key="consulta.aclaracio.formatData" />
		</c:param>
		<c:param name="clase" value="conMargen campoForm165" />
	</c:import> 
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="calendar" />
		<c:param name="path" value="formData.dataFi" />
		<c:param name="required" value="required" />
		<c:param name="title">
			<fmt:message key="consulta.camp.dataFi" />
		</c:param>
		<c:param name="camp" value="dataFi" />
		<c:param name="maxlength" value="10" />
		<c:param name="aclaracio">
			<fmt:message key="consulta.aclaracio.formatData" />
		</c:param>
		<c:param name="clase" value="campoForm165" />
	</c:import>

<div class="separadorH"></div>

<div class="botonesForm">
<div id="guardarForm" class="btnCorto"
	onclick="submitForm('formulario')"
	onmouseover="underline('enlaceGuardarForm')"
	onmouseout="underline('enlaceGuardarForm')"><a
	id="enlaceGuardarForm" href="javascript:void(0);"><fmt:message
	key="manteniment.cercar" /></a></div>
</div>

<c:if test="${llistat != null && empty llistat}">
	<div class="separadorH"></div>
	<div class="mensajeErrorConsulta"><c:import
		url="comu/MissatgesIErrors.jsp">
		<c:param name="listError">
			<fmt:message key="consulta.entradaOliva.noRegs" />
		</c:param>
	</c:import></div>
</c:if></fieldset>
</form>




<div id="listado"><!-- Tabla de resultados -->
<div class="layoutSombraTabla ancho">
<c:if test="${not empty llistat}">
	<%--h2><fmt:message key="consulta.oliComercialitzat.missatgeData" /></h2--%>
	<div class="rellenoInf"></div>
	<div class="rellenoIzq"></div>
	<div class="rellenoDer"></div>
	<div class="supDer"></div>
	<div class="supIzq"></div>
	<div class="infIzq"></div>
	<div class="infDer"></div>
</c:if> 

<jsp:scriptlet>
   org.displaytag.decorator.MultilevelTotalTableDecorator subtotals = new org.displaytag.decorator.MultilevelTotalTableDecorator();
   subtotals.setGrandTotalDescription("Total");    // optional, defaults to Grand Total
   subtotals.setSubtotalLabel("{0} Subtotal", request.getLocale());      // optional, defaults to "{0} Total"
   pageContext.setAttribute("subtotaler", subtotals);
</jsp:scriptlet>

<fmt:formatDate var="di" pattern="dd/MM/yyyy" value="${formData.dataInici}"/>
<fmt:formatDate var="df" pattern="dd/MM/yyyy" value="${formData.dataFi}"/>
		
<display:table name="llistat" requestURI="" id="establiment" export="true" sort="list" cellpadding="0" cellspacing="0" class="listadoAncho selectable" decorator="subtotaler">
	<display:column titleKey="establiment.camp.nom" sortable="true" sortProperty="nom">
		<a href="ConsultaOlivaTaulaComercialitzadaLlistat.html?idEstabliment=<c:out value="${establiment[0]}"/>&amp;fromEstabliment=true&amp;dataInici=${di}&amp;dataFi=${df}"><c:out value="${establiment[1]}"/></a>
	</display:column>
	<display:column titleKey="establiment.camp.cif" sortable="true" headerClass="ancho160 final">
		<c:out value="${establiment[2]}" />
	</display:column>
	<display:column property="[3]" titleKey="consulta.olivaComercialitzada.camp.verda" sortable="true" sortProperty="[3]" total="true" format="{0,number,#,##0.00}" />
	<display:column property="[4]" titleKey="consulta.olivaComercialitzada.camp.trencada" sortable="true" sortProperty="[4]" total="true" format="{0,number,#,##0.00}"/>
	<display:column property="[5]" titleKey="consulta.olivaComercialitzada.camp.negra" sortable="true" sortProperty="[5]" total="true" format="{0,number,#,##0.00}"/>
	<%--display:column property="[8]" titleKey="consulta.oliComercialitzat.camp.total" sortable="true" sortProperty="[8]" total="true" format="{0,number,#,##0.00}" headerClass="final" class="total" /--%>
	<display:setProperty name="export.xml" value="false" />
 	<display:setProperty name="export.csv" value="false" />
 	<display:setProperty name="export.rtf" value="false" />
 	<display:setProperty name="export.pdf" value="false" />
 	<display:setProperty name="export.excel.include_header" value="true" />
 	<display:setProperty name="export.excel.filename" value="OliConercialitzatEstabliments.xls" />
 	<display:setProperty name="export.decorated" value="true" />
</display:table></div>

<div class="separadorH"></div>

<form name="formulario" action="EstablimentForm.html"></form>

</div>

<c:if test="${not empty llistat}">
	<!-- Colores en tablas -->
	<script type="text/javascript">
			$(document).ready(function(){
				setEstilosTabla();
			})
		</script>
</c:if>




</body>
</html>


















