<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>
<%@ page import="es.caib.gestoli.front.util.*"%>
<%@ page import="java.util.ResourceBundle"%>
<%@ page import="java.util.Locale"%>
<%@ page buffer="256kb"%>
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

</head>
<body>
<form id="formulario" action="ConsultaOlivaTaulaComercialitzadaLlistat.html" method="post" class="extended seguit">
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
       org.displaytag.decorator.MultilevelTotalTableDecorator subtotals2 = new org.displaytag.decorator.MultilevelTotalTableDecorator();
       subtotals2.setGrandTotalDescription("Total");    // optional, defaults to Grand Total
       subtotals2.setSubtotalLabel("{0} Subtotal", request.getLocale());      // optional, defaults to "{0} Total"
       pageContext.setAttribute("subtotaler2", subtotals2);
    </jsp:scriptlet>



<c:if test="${llistat != null && not empty llistat}">
	<div class="lotes">
	<h1 class="tituloListadoLotes"><fmt:message	key="consulta.olivaComercialitzada.titol.envasat" /></h1>
	<div id="listado">
	<div class="layoutSombraTabla ancho">
	<div class="rellenoInf"></div>
	<div class="rellenoIzq"></div>
	<div class="rellenoDer"></div>
	<div class="supDer"></div>
	<div class="supIzq"></div>
	<div class="infIzq"></div>
	<div class="infDer"></div>

	<display:table name="llistat" requestURI="" id="sortidaLot" export = "true" sort="list" cellpadding="0" cellspacing="0" class="listadoAncho selectable noEnlace" decorator="subtotaler2" defaultsort="1">
		<display:column property="lot.codiAssignat" sortProperty="lot.codiAssignat" titleKey="consulta.oliComercialitzat.camp.lot" headerClass="ancho100" sortable="true" group="1" />
		<display:column property="lot.etiquetatge.nomEnvas" sortProperty="lot.etiquetatge.nomEnvas" titleKey="consulta.oliComercialitzat.camp.etiquetatge" sortable="true" group="2" />
		<display:column titleKey="lot.camp.producte" headerClass="ancho75" sortable="true">
			<c:if test="${not empty sortidaLot.lot.producte.id}">
				<c:out value="${sortidaLot.lot.producte.nom}" />
			</c:if>
		</display:column>
		<display:column titleKey="consulta.oliComercialitzat.camp.data" sortable="true" headerClass="ancho75">
			<c:out value="${sortidaLot.vendaDataFormat}" />
		</display:column>
		<display:column titleKey="consulta.oliComercialitzat.camp.marca" headerClass="ancho100" sortable="true">
			<c:out value="${sortidaLot.lot.etiquetatge.marca.nom}" />
		</display:column>
		<display:column property="vendaBotelles" titleKey="consulta.oliComercialitzat.camp.envases" headerClass="ancho100" sortable="true" total="true" format="{0,number,#,##0}" />
		<display:column property="vendaLitresAmbDevolucio" titleKey="consulta.oliComercialitzat.camp.litros" headerClass="ancho100" sortable="true" total="true" format="{0,number,#,##0.00} l." />
		<display:column titleKey="consulta.oliComercialitzat.camp.document" headerClass="ancho160" sortable="true">
			<c:out value="${sortidaLot.vendaTipusDocument}" />
			<c:out value="${sortidaLot.vendaNumeroDocument}" />
		</display:column>
		<display:column titleKey="consulta.oliComercialitzat.camp.destinatari" sortable="true">
			<c:out value="${sortidaLot.vendaDestinatari}" />
		</display:column>
		<display:column titleKey="consulta.oliComercialitzat.camp.destinacio" sortable="true">
			<c:if test="${lang == 'es'}">
				<c:out value="${sortidaLot.destinacio_es}" />
			</c:if>
			<c:if test="${lang == 'ca'}">
				<c:out value="${sortidaLot.destinacio_ca}" />
			</c:if>
		</display:column>
		<display:column titleKey="consulta.oliComercialitzat.camp.observacions"
			sortable="true">
			<c:out value="${sortidaLot.vendaObservacions}" />
		</display:column>
		<display:column headerClass="ancho20 paddingCelda final">
			<a href="ConsultaTrazabilitatResumida.html?tipus=<c:out value="${trazaTipusOlivaTaulaComercialitzada}"/>&amp;id=<c:out value="${sortidaLot.id}"/>">
				<img src="img/icons/trazabilidad.gif" border="0" title="<fmt:message key="consulta.trazabilitat.titol"/>" alt="<fmt:message key="consulta.trazabilitat.titol"/>" />
			</a>
		</display:column>
		<display:setProperty name="export.xml" value="false" />
   		<display:setProperty name="export.csv" value="false" />
   		<display:setProperty name="export.rtf" value="false" />
   		<display:setProperty name="export.pdf" value="false" />
   		<display:setProperty name="export.excel.include_header" value="true" />
   		<display:setProperty name="export.excel.filename" value="OlivaTaulaComercialitzadaEmbotellat.xls" />
   		<display:setProperty name="export.decorated" value="true" />
	</display:table></div>
	</div>
	</div>

</c:if>

<c:if test="${(llistat != null && not empty llistat)}">
	<%-- Colores en tablas --%>
	<script type="text/javascript">
			$(document).ready(function(){
				setEstilosTabla();
			})
		</script>
</c:if>

</body>
</html>
