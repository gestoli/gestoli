<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>
<%@ page import="es.caib.gestoli.front.util.*"%>
<%@ page import="java.util.ResourceBundle"%>
<%@ page import="java.util.Locale"%>
<%
	String lang = Idioma.getLang(request);
	request.setAttribute("lang",lang);
%>

<html>
<head>
<title><fmt:message key="consulta.general.title" /></title>

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
<form id="formulario" action="InformeProduccio.html" method="post" class="extended seguit">
<fieldset>

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

<div class="separadorH"></div>

<c:import url="comu/CampFormulari.jsp">
	<c:param name="tipus" value="select" />
	<c:param name="path" value="formData.idEstabliment" />
	<c:param name="title">
		<fmt:message key="consulta.partidaOliva.camp.establiment" />
	</c:param>
	<c:param name="camp" value="idEstabliment" />
	<c:param name="required" value="required" />
	<c:param name="selectItems" value="establecimientos" />
	<c:param name="selectItemsId" value="idOriginal" />
	<c:param name="selectItemsValue" value="nom" />
	<c:param name="selectSelectedValue" value="${formData.idEstabliment}" />
	<c:param name="clase" value="campoFormTresCuartos" />
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


<div class="separadorH"></div>


<div class="campoForm"><label><fmt:message
	key="consulta.informeProduccio.tabla.quantitatOlivaMolturada" /></label>
<div class="separadorH"></div>
<div class="layoutSombraTabla">
<div class="rellenoInf"></div>
<div class="rellenoIzq"></div>
<div class="rellenoDer"></div>
<div class="supDer"></div>
<div class="supIzq"></div>
<div class="infIzq"></div>
<div class="infDer"></div>
<table cellpadding=0 cellspacing=0 class="noRoll">
	<tr>
		<td class="ancho340"><fmt:message
			key="consulta.informeProduccio.camp.quantitatOlivaDo" /></td>
		<td class="ancho110 boldtd"><fmt:formatNumber
			value="${quantitatOlivaDo}" maxFractionDigits="2" /> kgs.</td>
	</tr>
	<tr>
		<td class="ancho340"><fmt:message
			key="consulta.informeProduccio.camp.quantitatOlivaNoDo" /></td>
		<td class="ancho110 boldtd"><fmt:formatNumber
			value="${quantitatOlivaNoDo}" maxFractionDigits="2" /> kgs.</td>
	</tr>
</table>
</div>
</div>

<div class="separadorH"></div>


<div class="campoForm"><label><fmt:message
	key="consulta.informeProduccio.tabla.quantitatOliTipus" /></label>
<div class="separadorH"></div>
<div class="layoutSombraTabla">
<div class="rellenoInf"></div>
<div class="rellenoIzq"></div>
<div class="rellenoDer"></div>
<div class="supDer"></div>
<div class="supIzq"></div>
<div class="infIzq"></div>
<div class="infDer"></div>
<table cellpadding=0 cellspacing=0 class="noRoll">
	<tr>
		<td class="ancho340"><fmt:message
			key="consulta.informeProduccio.camp.quantitatOliDoVenut" /></td>
		<td class="ancho110 boldtd"><fmt:formatNumber
			value="${quantitatOliDoVenut}" maxFractionDigits="2" /> l.</td>
	</tr>
	<tr>
		<td class="ancho340"><fmt:message
			key="consulta.informeProduccio.camp.quantitatOliDoExistencies" /></td>
		<td class="ancho110 boldtd"><fmt:formatNumber
			value="${quantitatOliDoExistencies}" maxFractionDigits="2" /> l.</td>
	</tr>

	<tr>
		<td class="ancho340"><fmt:message
			key="consulta.informeProduccio.camp.quantitatOliNoDoElaborat" /></td>
		<td class="ancho110 boldtd"><fmt:formatNumber
			value="${quantitatOliNoDoElaborat}" maxFractionDigits="2" /> l.</td>
	</tr>
	<tr>
		<td class="ancho340"><fmt:message
			key="consulta.informeProduccio.camp.quantitatOliNoDoVenut" /></td>
		<td class="ancho110 boldtd"><fmt:formatNumber
			value="${quantitatOliNoDoVenut}" maxFractionDigits="2" /> l.</td>
	</tr>
	<tr>
		<td class="ancho340"><fmt:message
			key="consulta.informeProduccio.camp.quantitatOliNoDoExistencies" /></td>
		<td class="ancho110 boldtd"><fmt:formatNumber
			value="${quantitatOliNoDoExistencies}" maxFractionDigits="2" /> l.</td>
	</tr>

	<tr>
		<td class="ancho340"><fmt:message
			key="consulta.informeProduccio.camp.quantitatOliPendentElaborat" /></td>
		<td class="ancho110 boldtd"><fmt:formatNumber
			value="${quantitatOliPendentElaborat}" maxFractionDigits="2" /> l.</td>
	</tr>
	<tr>
		<td class="ancho340"><fmt:message
			key="consulta.informeProduccio.camp.quantitatOliPendentVenut" /></td>
		<td class="ancho110 boldtd"><fmt:formatNumber
			value="${quantitatOliPendentVenut}" maxFractionDigits="2" /> l.</td>
	</tr>
	<tr>
		<td class="ancho340"><fmt:message
			key="consulta.informeProduccio.camp.quantitatOliPendentExistencies" /></td>
		<td class="ancho110 boldtd"><fmt:formatNumber
			value="${quantitatOliPendentExistencies}" maxFractionDigits="2" />
		l.</td>
	</tr>

</table>
</div>
</div>

<div class="separadorH"></div>

<div class="campoForm"><label><fmt:message
	key="consulta.informeProduccio.tabla.canvisQualificacio" /></label>
<div class="separadorH"></div>
<div class="layoutSombraTabla">
<div class="rellenoInf"></div>
<div class="rellenoIzq"></div>
<div class="rellenoDer"></div>
<div class="supDer"></div>
<div class="supIzq"></div>
<div class="infIzq"></div>
<div class="infDer"></div>
<table cellpadding=0 cellspacing=0 class="noRoll">
	<tr>
		<td class="ancho340"><fmt:message
			key="consulta.informeProduccio.camp.qualificatToDesqualificat" /></td>
		<td class="ancho110 boldtd"><fmt:formatNumber
			value="${qualificatToDesqualificat}" maxFractionDigits="2" /> l.</td>
	</tr>
	<tr>
		<td class="ancho340"><fmt:message
			key="consulta.informeProduccio.camp.desqualificatToQualificat" /></td>
		<td class="ancho110 boldtd"><fmt:formatNumber
			value="${desqualificatToQualificat}" maxFractionDigits="2" /> l.</td>
	</tr>
</table>
</div>


</div>

</fieldset>
</form>

<!-- Colores en tablas -->
<script type="text/javascript">
		$(document).ready(function(){
			setEstilosTabla();
		})
	</script>


</body>
</html>
