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
<title><fmt:message key="agenciaOli.sortidaOrujo.titol" /></title>

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
	<%--<link href="css/displaytag.css" rel="stylesheet" type="text/css"/> --%>
</c:if>

</head>
<body>
<form id="formulario" action="AgenciaOliSortidaOrujo.html" method="post" class="extended seguit">
	<fieldset>
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="hidden" />
			<c:param name="path" value="formData.fromEstabliment" />
			<c:param name="camp" value="fromEstabliment" />
		</c:import> 
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="hidden" />
			<c:param name="path" value="formData.idEstabliment" />
			<c:param name="camp" value="idEstabliment" />
		</c:import> 
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
		
			<c:import url="comu/MissatgesIErrors.jsp">
				<c:param name="listError">
					<fmt:message key="consulta.entradaOliva.noRegs" />
				</c:param>
			</c:import>
		</c:if>
	</fieldset>
</form>

<div class="separadorH"></div>

<c:if test="${llistat != null && not empty llistat}">
	<div id="listado"><%-- Tabla de resultados --%>
	<div class="layoutSombraTabla ancho">
	<div class="rellenoInf"></div>
	<div class="rellenoIzq"></div>
	<div class="rellenoDer"></div>
	<div class="supDer"></div>
	<div class="supIzq"></div>
	<div class="infIzq"></div>
	<div class="infDer"></div>

<jsp:scriptlet>
       org.displaytag.decorator.MultilevelTotalTableDecorator subtotals = new org.displaytag.decorator.MultilevelTotalTableDecorator();
       subtotals.setGrandTotalDescription("Total");    // optional, defaults to Grand Total
       subtotals.setSubtotalLabel("{0} Subtotal", request.getLocale());      // optional, defaults to "{0} Total"
       pageContext.setAttribute("subtotaler", subtotals);
    </jsp:scriptlet>

	<display:table name="llistat" requestURI="" id="partidaOliva"  export="true" sort="list" cellpadding="0" cellspacing="0" class="listadoAncho noEnlace" decorator="subtotaler" defaultsort="1">
		<display:column titleKey="agenciaOli.sortidaOrujo.camp.data" headerClass="ancho75" sortable="true" property="data" format="{0,date,dd/MM/yyyy}" />
		<display:column titleKey="agenciaOli.sortidaOrujo.camp.document" sortable="true"  property="albara" />
		<display:column titleKey="agenciaOli.sortidaOrujo.camp.kg" sortable="true" property="kilos" format="{0,number,#,##0.00}" />
		<display:column titleKey="agenciaOli.sortidaOrujo.camp.desti" sortable="true" property="desti" />
		<display:column titleKey="agenciaOli.sortidaOrujo.camp.nif" sortable="true" property="document" />

		<display:setProperty name="export.xml" value="false" />
   		<display:setProperty name="export.csv" value="false" />
   		<display:setProperty name="export.rtf" value="false" />
   		<display:setProperty name="export.pdf" value="false" />
   		<display:setProperty name="export.excel.include_header" value="true" />
   		<display:setProperty name="export.excel.filename" value="Agencia_aceite_sortida_orujo.xls" />
   		<display:setProperty name="export.decorated" value="true" />
	</display:table>
	
	</div>
</div>


</c:if>

	<%-- Colores en tablas --%>
	<script type="text/javascript">
			$(document).ready(function(){
				setEstilosTabla();
			})
		</script>

</body>
</html>
