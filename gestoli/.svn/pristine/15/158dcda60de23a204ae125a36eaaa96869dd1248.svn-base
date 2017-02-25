<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
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
<title>
	<fmt:message key="manteniment.alta" />
	
	<fmt:message key="importacioProcesSortida.tipusdemant" />
</title>

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

<link href="js/calendar/calendar-viti.css" rel="stylesheet" type="text/css" />


<script type="text/javascript" src="js/form.js"></script>

<script type="text/javascript">
	function changeRadio(){
		if (document.getElementById("tipusSortidaSi").checked == true){
			document.getElementById("tipusSortida").value = "S";
		} else if (document.getElementById("tipusSortidaNo").checked == true){
			document.getElementById("tipusSortida").value = "N";
		}
	}
</script>

</head>
<body>

<div>

<form id="formulario" name="ImportacioProcesSortidaForm" action="ImportacioProcesSortidaForm.html" method="post" class="extended seguit zona" enctype="multipart/form-data" onsubmit="">
	
	<div class="separadorH"></div>
<!-- 
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="radioDecisio"/>
   		<c:param name="path" value="formData.tipusSortida"/>
   		<c:param name="title"><fmt:message key="importacioProcesSortida.camp.tipusSortida"/></c:param>
       	<c:param name="camp" value="tipusSortida"/>
		<c:param name="value" value="${formData.tipusSortida}"/>
	    <c:param name="onchange" value="changeRadio();"/>
	</c:import>

	<div class="separadorH"></div>
-->	
	<c:set var="arxiu" value="${formData.arxiuObject}" scope="request" /> 
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="file" />
		<c:param name="path" value="formData.fitxer" />
		<c:param name="required" value="required" />
		<c:param name="title">
			<fmt:message key="document.camp.fitxer" />
		</c:param>
		<c:param name="camp" value="fitxer" />
		<c:param name="arxiu" value="arxiu" />
		<c:param name="width" value="150" />
		<c:param name="height" value="100" />
		<c:param name="document" value="true" />
	</c:import>
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="hidden" />
		<c:param name="path" value="formData.arxiu" />
		<c:param name="camp" value="arxiu" />
	</c:import>
	
	<div class="separadorH"></div>
	
	<div class="botonesForm">
	
		<div id="guardarForm" class="btnCorto"
			onclick="submitForm('formulario')"
			onmouseover="underline('enlaceGuardarForm')"
			onmouseout="underline('enlaceGuardarForm')"><a
			id="enlaceGuardarForm" href="javascript:void(0);"><fmt:message
			key="manteniment.importar" /></a>
		</div>

	</div>

</form>

<div class="separadorH"></div>
 
<c:if test="${llistatOK != null && not empty llistatOK}">
	<label style="margin-left: 40px;"><fmt:message key="importacioProcesSortida.llistitol.OK" /></label>
	<div class="separadorH"></div>

	<div id="listadoOK"><%-- Tabla de resultados --%>
		<div class="layoutSombraTabla ancho">
			<div class="rellenoInf"></div>
			<div class="rellenoIzq"></div>
			<div class="rellenoDer"></div>
			<div class="supDer"></div>
			<div class="supIzq"></div>
			<div class="infIzq"></div>
			<div class="infDer"></div>
			<display:table name="llistatOK" requestURI="" id="importacioProcesSortidaOK" export="true" sort="list" cellpadding="0" cellspacing="0" class="listadoAncho">
				<display:column property="pais" titleKey="importacioProcesSortida.camp.pais" headerClass="ancho75" sortable="true" />
				<display:column property="provincia" titleKey="importacioProcesSortida.camp.provincia" headerClass="ancho75" sortable="true"/>
				<display:column property="municipi" titleKey="importacioProcesSortida.camp.municipi" headerClass="ancho75" sortable="true"/>
				<display:column property="lot" titleKey="importacioProcesSortida.camp.lot" headerClass="ancho75" sortable="true"/>
				<display:column property="producte" titleKey="importacioProcesSortida.camp.producte" headerClass="ancho50" sortable="true"/>
				<display:column property="vendaData" titleKey="importacioProcesSortida.camp.vendaData" headerClass="ancho75" sortable="true"/>
				<display:column property="vendaNumeroBotelles" titleKey="importacioProcesSortida.camp.vendaNumeroBotelles" headerClass="ancho50" sortable="true"/>
				<display:column property="vendaMotiu" titleKey="importacioProcesSortida.camp.vendaMotiu" class="hidden" headerClass="hidden" sortable="true"/>
				<display:column property="vendaObservacions" titleKey="importacioProcesSortida.camp.vendaObservacions" sortable="true" class="hidden" headerClass="hidden" />
				<display:column property="vendaDestinatari" titleKey="importacioProcesSortida.camp.vendaDestinatari" headerClass="ancho75 final" sortable="true"/>
				<display:column property="vendaNumeroDocument" titleKey="importacioProcesSortida.camp.vendaNumeroDocument" class="hidden" headerClass="hidden" sortable="true" />
				<display:column property="vendaTipusDocument" titleKey="importacioProcesSortida.camp.vendaTipusDocument" class="hidden" headerClass="hidden" sortable="true" />
				<display:column property="idSortida" titleKey="importacioProcesSortida.camp.idSortida" headerClass="ancho75 final" sortable="true" style="color:green" />
				<display:setProperty name="export.xml" value="false" />
		   		<display:setProperty name="export.csv" value="false" />
		   		<display:setProperty name="export.rtf" value="false" />
		   		<display:setProperty name="export.pdf" value="false" />
		   		<display:setProperty name="export.excel.include_header" value="true" />
		   		<display:setProperty name="export.excel.filename" value="ImportacioProcesSortidaOK_${importacioProcesSortidaOK.idImportacio}.xls" />
		   		<display:setProperty name="export.decorated" value="true" />
			</display:table>
		</div>
	</div>
	
</c:if>
<br /><br /><br />
<c:if test="${llistatKO != null && not empty llistatKO}">
	<label style="margin-left: 40px;"><fmt:message key="importacioProcesSortida.llistitol.KO" /></label>
	<div class="separadorH"></div>

	<div id="listadoKO"><%-- Tabla de resultados --%>
		<div class="layoutSombraTabla ancho">
			<div class="rellenoInf"></div>
			<div class="rellenoIzq"></div>
			<div class="rellenoDer"></div>
			<div class="supDer"></div>
			<div class="supIzq"></div>
			<div class="infIzq"></div>
			<div class="infDer"></div>
			<display:table name="llistatKO" requestURI="" id="importacioProcesSortidaKO" export="true" sort="list" cellpadding="0" cellspacing="0" class="listadoAncho">
				<display:column property="pais" titleKey="importacioProcesSortida.camp.pais" headerClass="ancho75" sortable="true" />
				<display:column property="provincia" titleKey="importacioProcesSortida.camp.provincia" headerClass="ancho75" sortable="true"/>
				<display:column property="municipi" titleKey="importacioProcesSortida.camp.municipi" headerClass="ancho75" sortable="true"/>
				<display:column property="lot" titleKey="importacioProcesSortida.camp.lot" headerClass="ancho75" sortable="true"/>
				<display:column property="producte" titleKey="importacioProcesSortida.camp.producte" headerClass="ancho50" sortable="true"/>
				<display:column property="vendaData" titleKey="importacioProcesSortida.camp.vendaData" headerClass="ancho50" sortable="true"/>
				<display:column property="vendaNumeroBotelles" titleKey="importacioProcesSortida.camp.vendaNumeroBotelles" headerClass="ancho50" sortable="true"/>
				<display:column property="vendaMotiu" titleKey="importacioProcesSortida.camp.vendaMotiu" class="hidden" headerClass="hidden" sortable="true"/>
				<display:column property="vendaObservacions" titleKey="importacioProcesSortida.camp.vendaObservacions" class="hidden" headerClass="hidden" sortable="true"/>
				<display:column property="vendaDestinatari" titleKey="importacioProcesSortida.camp.vendaDestinatari" headerClass="ancho50" sortable="true"/>
				<display:column property="vendaNumeroDocument" titleKey="importacioProcesSortida.camp.vendaNumeroDocument" class="hidden" headerClass="hidden" sortable="true"/>
				<display:column property="vendaTipusDocument" titleKey="importacioProcesSortida.camp.vendaTipusDocument" class="hidden" headerClass="hidden" sortable="true"/>
				<display:column property="error" titleKey="importacioProcesSortida.camp.error" headerClass="ancho150 final" sortable="true" style="color:red"/>
				<display:setProperty name="export.xml" value="false" />
		   		<display:setProperty name="export.csv" value="false" />
		   		<display:setProperty name="export.rtf" value="false" />
		   		<display:setProperty name="export.pdf" value="false" />
		   		<display:setProperty name="export.excel.include_header" value="true" />
		   		<display:setProperty name="export.excel.filename" value="ImportacioProcesSortidaKO_${importacioProcesSortidaKO.idImportacio}.xls" />
		   		<display:setProperty name="export.decorated" value="true" />
			</display:table>
		</div>
	</div>
	
</c:if>

<c:if test="${(llistatOK != null && not empty llistatOK) || (llistatKO != null && not empty llistatKO)}">
	<%-- Colores en tablas --%>
	<script type="text/javascript">
		$(document).ready(function(){
			setEstilosTabla();
		})
	</script>
</c:if>

</body>
</html>