<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>
<%@ page import="es.caib.gestoli.front.util.*"%>
<%@ page import="java.util.ResourceBundle"%>
<%@ page import="java.util.Locale"%>
<%@ page import="org.displaytag.*"%>
<%@ page buffer="64kb"%>
<%@ page autoFlush="true"%>
<%
	String lang = Idioma.getLang(request);
	request.setAttribute("lang",lang);
%>

<html>
<head>
<title><fmt:message key="consulta.historiaDiposit.titol" /></title>

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

<c:if test="${not empty llistat}">
	<script type="text/javascript" src="js/displaytag.js"></script>
</c:if>

</head>
<body>
<form id="formulario" action="ConsultaHistoriaDipositLlistat.html" method="post" class="extended seguit">
<fieldset><c:import url="comu/CampFormulari.jsp">
	<c:param name="tipus" value="hidden" />
	<c:param name="path" value="formData.fromEstabliment" />
	<c:param name="camp" value="fromEstabliment" />
</c:import> <c:import url="comu/CampFormulari.jsp">
	<c:param name="tipus" value="hidden" />
	<c:param name="path" value="formData.idEstabliment" />
	<c:param name="camp" value="idEstabliment" />
</c:import> <c:import url="comu/CampFormulari.jsp">
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
</c:import> <c:import url="comu/CampFormulari.jsp">
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

<c:import url="comu/CampFormulari.jsp">
	<c:param name="tipus" value="select" />
	<c:param name="path" value="formData.idDiposit" />
	<c:param name="required" value="required" />
	<c:param name="title">
		<fmt:message key="proces.diposits" />
	</c:param>
	<c:param name="camp" value="idDiposit" />
	<c:param name="name" value="idDiposit" />
	<c:param name="selectItems" value="diposits" />
	<c:param name="selectItemsId" value="id" />
	<c:param name="selectItemsValue" value="codiAssignat" />
	<c:param name="selectSelectedValue" value="${formData.idDiposit}" />
	<c:param name="clase" value="campoFormMediano conMargen" />
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

<div class="separadorH"></div>

<jsp:scriptlet>
       org.displaytag.decorator.MultilevelTotalTableDecorator subtotals = new org.displaytag.decorator.MultilevelTotalTableDecorator();
       subtotals.setGrandTotalDescription("Total");    // optional, defaults to Grand Total
       subtotals.setSubtotalLabel("{0} Subtotal", request.getLocale());      // optional, defaults to "{0} Total"
       pageContext.setAttribute("subtotaler", subtotals);
    </jsp:scriptlet>


<c:if test="${llistat != null && not empty llistat}">
	<div class="lotes">
	<div id="listado">
	<div class="layoutSombraTabla ancho">
	<%-->div class="rellenoInf"></div>
	<div class="rellenoIzq"></div>
	<div class="rellenoDer"></div>
	<div class="supDer"></div>
	<div class="supIzq"></div>
	<div class="infIzq"></div>
	<div class="infDer"></div--%>

	<c:set var="subtotal" value="${existenciesInicials}" />
	<div style="overflow-y: auto; width: 888px; margin-left: 30px;">
		<display:table name="llistat" requestURI="" id="obj" sort="list" export="true" cellpadding="0" cellspacing="0" class="selectable noEnlace" decorator="subtotaler" defaultsort="1" style="width:2150px;">
			<c:set var="color" value="color:black;" />
			<c:if test="${obj[2] == 'proces.sortida'}">
				<c:set var="color" value="color:red;" />
			</c:if>
			<display:column property="[0]"  titleKey="consulta.historiaDiposit.camp.data" headerClass="ancho100" format="{0,date,dd/MM/yyyy}" style="${color}"/>
			<display:column titleKey="consulta.historiaDiposit.camp.tipus" headerClass="ancho100" style="${color}">
				<fmt:message key="${obj[2]}" />
			</display:column>
			<display:column titleKey="consulta.historiaDiposit.camp.accio" sortable="true" headerClass="ancho100" style="${color}">
				<c:choose>
					<c:when test="${obj[5] == 1}"><fmt:message key="consulta.historiaDiposit.camp.accio.partida" /></c:when>
					<c:when test="${obj[5] == 2}"><fmt:message key="consulta.historiaDiposit.camp.accio.elaboracio" /></c:when>
					<c:when test="${obj[5] == 3}"><fmt:message key="consulta.historiaDiposit.camp.accio.sortidaLot" /></c:when>
					<c:when test="${obj[5] == 4}"><fmt:message key="consulta.historiaDiposit.camp.accio.entradaDiposit" /></c:when>
					<c:when test="${obj[5] == 5}"><fmt:message key="consulta.historiaDiposit.camp.accio.perdua" /></c:when>
					<c:when test="${obj[5] == 6}"><fmt:message key="consulta.historiaDiposit.camp.accio.embotellat" /></c:when>
					<c:when test="${obj[5] == 7}"><fmt:message key="consulta.historiaDiposit.camp.accio.sortidaGranel" /></c:when>
					<c:when test="${obj[5] == 8}"><fmt:message key="consulta.historiaDiposit.camp.accio.trasllat" /></c:when>
					<c:when test="${obj[5] == 9}"><fmt:message key="consulta.historiaDiposit.camp.accio.tancament" /></c:when>
					<c:when test="${obj[5] == 10}"><fmt:message key="consulta.historiaDiposit.camp.accio.desqualificat" /></c:when>
					<c:when test="${obj[5] == 11}"><fmt:message key="consulta.historiaDiposit.camp.accio.trasbals" /></c:when>
					<c:when test="${obj[5] == 12}"><fmt:message key="consulta.historiaDiposit.camp.accio.analitica" /></c:when>
					<c:when test="${obj[5] == 13}"><fmt:message key="consulta.historiaDiposit.camp.accio.envasatOliva" /></c:when>
				</c:choose>
			</display:column>
			<display:column property="[1]"  titleKey="consulta.historiaDiposit.camp.quantitat" headerClass="ancho100" format="{0,number,#,##0.00} l." total="true" style="${color}"/>
			<display:column property="[4]" titleKey="consulta.historiaDiposit.camp.parcialdiposit" headerClass="ancho100" format="{0,number,#,##0.00} l." style="${color}"/>
			<display:column property="[9]" titleKey="consulta.historiaDiposit.camp.acidesa" sortable="true" headerClass="ancho75" style="${color}"/>
			<display:column property="[7]" titleKey="consulta.historiaDiposit.camp.nomPartidaOli" sortable="true" headerClass="ancho175" style="${color}"/>
			<display:column property="[8]" titleKey="consulta.historiaDiposit.camp.nomCategoriaOli" sortable="true" headerClass="ancho100" style="${color}"/>
			<display:column property="[16]" titleKey="consulta.historiaDiposit.camp.marca" sortable="true" headerClass="ancho310 final" style="${color}"/>
			<display:column property="[6]" titleKey="consulta.historiaDiposit.camp.dipDesti" sortable="true" headerClass="ancho160" style="${color}"/>
			<display:column property="[13]" titleKey="consulta.historiaDiposit.camp.lotDesti" sortable="true" headerClass="ancho160" style="${color}"/>
			<display:column property="[14]" titleKey="consulta.historiaDiposit.camp.etiquetatge" sortable="true" headerClass="ancho310" style="${color}"/>
			<display:column property="[10]" titleKey="consulta.historiaDiposit.camp.kilos.oliva" sortable="true" headerClass="ancho100" style="${color}"/>
			<display:column property="[11]" titleKey="consulta.historiaDiposit.camp.olivicultor" sortable="true" headerClass="ancho100" style="${color}"/>
			<display:column property="[12]" titleKey="consulta.historiaDiposit.camp.tipus.oliva" sortable="true" headerClass="ancho160" style="${color}"/>
			<display:column property="[15]" titleKey="consulta.historiaDiposit.camp.contraetiquetes" sortable="true" headerClass="ancho310 final" style="${color}"/>
			
			<display:setProperty name="export.xml" value="false" />
	   		<display:setProperty name="export.csv" value="false" />
	   		<display:setProperty name="export.rtf" value="false" />
	   		<display:setProperty name="export.pdf" value="false" />
	   		<display:setProperty name="export.excel.include_header" value="true" />
	   		<display:setProperty name="export.excel.filename" value="HistoricDiposit.xls" />
	 	  	<display:setProperty name="export.decorated" value="true" />
		</display:table>
	</div>
	</div>
	</div>
	</div>

</c:if>

<c:if test="${(llistat != null && not empty llistat) || (llistatGranel != null && not empty llistatGranel)}">
	<%-- Colores en tablas --%>
	<script type="text/javascript">
			$(document).ready(function(){
				setEstilosTabla();
			})
		</script>
</c:if>

</body>
</html>
