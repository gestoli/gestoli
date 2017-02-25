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
<title><fmt:message key="consulta.entradaFonoll.titol" /></title>

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
<form id="formulario" action="ConsultaEntradaFonoll.html" method="post" class="extended seguit">
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
			<c:param name="tipus" value="select" />
			<c:param name="path" value="formData.idCampanya" />
			<c:param name="title">
				<fmt:message key="consulta.general.temporada" />
			</c:param>
			<c:param name="camp" value="idCampanya" />
			<c:param name="required" value="required" />
			<c:param name="selectItems" value="campanyes" />
			<c:param name="selectItemsId" value="id" />
			<c:param name="selectItemsValue" value="nom" />
			<c:param name="selectSelectedValue" value="${formData.idCampanya}" />
			<c:param name="clase" value="campoFormMediano" />
		</c:import>
		
		<div class="separadorH"></div>
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


<div class="separadorH"></div>
<c:if test="${llistat != null && not empty llistat}">
	<div id="listado"><%-- Tabla de resultados --%>
	<div class="lotes" style="width: 745px; margin: 30px 0pt 0pt 40px;">
	</div>
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
    
	<display:table name="llistat" requestURI="" id="partidaFonoll"  export="true" sort="list" cellpadding="0" cellspacing="0" class="listadoAncho selectable noEnlace" decorator="subtotaler">
		<c:set var="color" value="color:black;" />
		<display:column titleKey="consulta.entradaFonoll.camp.data" headerClass="ancho75" sortable="true" sortProperty="data" style="${color}">
			<c:out value="${partidaFonoll.dataFormat}" />
		</display:column>
		<display:column titleKey="consulta.entradaFonoll.camp.hora" sortable="true" style="${color}">
			<c:out value="${partidaFonoll.hora}" />
		</display:column>
		<display:column titleKey="consulta.entradaFonoll.camp.numeroEntrada" sortable="true" style="${color}">
			<c:out value="${partidaFonoll.codi}" escapeXml="false" />
		</display:column>
		<display:column titleKey="consulta.entradaFonoll.camp.titular" sortable="true" style="${color}">
			<c:out value="${partidaFonoll.titular}" escapeXml="false" />
		</display:column>
		<display:column titleKey="consulta.entradaFonoll.camp.finca" sortable="true" style="${color}">
			<c:out value="${partidaFonoll.municipi.nom}" escapeXml="false" />,
			 <fmt:message key="consulta.entradaFonoll.camp.poligon"/> <c:out value="${partidaFonoll.poligon}" escapeXml="false" />,
			 <fmt:message key="consulta.entradaFonoll.camp.parcela"/> <c:out value="${partidaFonoll.parcela}" escapeXml="false" />
		</display:column>
		<display:column titleKey="consulta.entradaFonoll.camp.kgInici" sortable="true" style="text-align:right; padding: 5px 10px 5px 0; ${color}">
			<c:out value="${partidaFonoll.kgInici}" escapeXml="false" />
		</display:column>
		<display:column titleKey="consulta.entradaFonoll.camp.kgRestants" sortable="true" style="text-align:right; padding: 5px 10px 5px 0; ${color}">
			<c:out value="${partidaFonoll.kgRestants}" escapeXml="false" />
		</display:column>
		<display:column titleKey="consulta.entradaFonoll.documentAcompanyament" headerClass="ancho50 final" sortable="false" style="${color}">
			<form id="pdfForm${partidaFonoll.numeroDocument}" action="GenerarPdf.html" method="post" style="width: 16px">
				<img style="cursor: pointer;" onclick="document.getElementById('pdfForm${partidaFonoll.numeroDocument}').submit();" src="img/icons/pdf.png" width="16" height="16" alt="" />
				<input type="hidden" id="tipus" name="tipus" value="25" /> 
				<input type="hidden" id="idPartida" name="idPartida" value="<c:out value="${partidaFonoll.id}"/>" /> 
			</form>
		</display:column>
		<display:setProperty name="export.xml" value="false" />
   		<display:setProperty name="export.csv" value="false" />
   		<display:setProperty name="export.rtf" value="false" />
   		<display:setProperty name="export.pdf" value="false" />
   		<display:setProperty name="export.excel.include_header" value="true" />
   		<display:setProperty name="export.excel.filename" value="EntradaFonoll.xls" />
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
