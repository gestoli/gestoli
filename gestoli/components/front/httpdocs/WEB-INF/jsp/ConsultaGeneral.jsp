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
<script type="text/javascript">
	function cerca(){
		document.getElementById("carregant").style.display = "";
		submitForm('formulario');
	}
</script>
</head>
<body>
<form id="formulario" action="ConsultaGeneral.html" method="post"
	class="extended seguit">
<fieldset><c:import url="comu/CampFormulari.jsp">
	<c:param name="tipus" value="select" />
	<c:param name="path" value="formData.temporadaId" />
	<c:param name="title">
		<fmt:message key="consulta.general.temporada" />
	</c:param>
	<c:param name="camp" value="temporadaId" />
	<c:param name="required" value="required" />
	<c:param name="selectItems" value="temporadas" />
	<c:param name="selectItemsId" value="id" />
	<c:param name="selectItemsValue" value="nom" />
	<c:param name="selectSelectedValue" value="${formData.temporadaId}" />
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
	onclick="cerca()"
	onmouseover="underline('enlaceGuardarForm')"
	onmouseout="underline('enlaceGuardarForm')">
	<a id="enlaceGuardarForm" href="javascript:void(0);"><fmt:message key="manteniment.cercar" /></a>
</div>
</div>

<div class="separadorH"></div>
<br/>
<div id="carregant" style="display:none;">
	<fmt:message key="manteniment.carregant"/>
<div>

<c:import url="comu/CampFormulari.jsp">
	<c:param name="tipus" value="hidden" />
	<c:param name="path" value="formData.buscar" />
	<c:param name="camp" value="buscar" />
</c:import></fieldset>

</form>

<div class="separadorH"></div>


<c:if test="${olivesEntrades != null && not empty olivesEntrades}">
	<div class="resultadosGeneral"><!-- 
			<c:if test="${not empty existenciaOliDOTemporadaAnterior}">
				<p><span><fmt:message key="consulta.general.camp.existenciaOliDOTemporadaAnterior"/></span> <fmt:formatNumber value="${existenciaOliDOTemporadaAnterior}" maxFractionDigits ="2" /> l. </p>
			</c:if>
			<p><span><fmt:message key="consulta.general.camp.existenciaOliDao"/></span> <fmt:formatNumber value="${existenciaOliDO}" maxFractionDigits ="2" /> l. </p>
			<p><span><fmt:message key="consulta.general.camp.olivesEntrades"/></span> <fmt:formatNumber value="${olivesEntrades}" maxFractionDigits ="2" /> kg </p>
			<p><span><fmt:message key="consulta.general.camp.oliElaborat"/></span> <fmt:formatNumber value="${oliElaborat}" maxFractionDigits ="2" /> l. </p>
			<p><span><fmt:message key="consulta.general.camp.oliQualificat"/></span> <fmt:formatNumber value="${oliQualificat}" maxFractionDigits ="2" /> l. </p>
			<p><span><fmt:message key="consulta.general.camp.oliDesqualificat"/></span> <fmt:formatNumber value="${oliDesqualificat}" maxFractionDigits ="2" /> l. </p>
			<p><span><fmt:message key="consulta.general.camp.oliPendentQualificar"/></span> <fmt:formatNumber value="${oliPendentQualificar}" maxFractionDigits ="2" /> l. </p>
			<p><span><fmt:message key="consulta.general.camp.oliComercialitzatDO"/></span> <fmt:formatNumber value="${oliComercialitzatDO}" maxFractionDigits ="2" /> l. </p>
			<p><span><fmt:message key="consulta.general.camp.oliDOaEnvasadores"/></span> <fmt:formatNumber value="${oliDOaEnvasadores}" maxFractionDigits ="2" /> l. </p>
			<p><span><fmt:message key="consulta.general.camp.oliDOdeEnvasadores"/></span> <fmt:formatNumber value="${oliDOdeEnvasadores}" maxFractionDigits ="2" /> l. </p>
			
		    -->

	<div class="layoutSombraTabla">
	<div class="rellenoInf"></div>
	<div class="rellenoIzq"></div>
	<div class="rellenoDer"></div>
	<div class="supDer"></div>
	<div class="supIzq"></div>
	<div class="infIzq"></div>
	<div class="infDer"></div>
	<table cellpadding=0 cellspacing=0 class="noRoll">
		<c:if test="${not empty existenciaOliDOTemporadaAnterior}">
			<tr>
				<td class="ancho340"><fmt:message key="consulta.general.camp.existenciaOliDOTemporadaAnterior" /></td>
				<td class="ancho110 boldtd"><fmt:formatNumber value="${existenciaOliDOTemporadaAnterior}" maxFractionDigits="2" /> l.</td>
			</tr>
			<tr>
				<td class="ancho340"><fmt:message key="consulta.general.camp.existenciaOliPendentTemporadaAnterior" /></td>
				<td class="ancho110 boldtd"><fmt:formatNumber value="${existenciaOliPendentTemporadaAnterior}" maxFractionDigits="2" /> l.</td>
			</tr>
			<tr>
				<td class="ancho340"><fmt:message key="consulta.general.camp.existenciaOliNoDOTemporadaAnterior" /></td>
				<td class="ancho110 boldtd"><fmt:formatNumber value="${existenciaOliNoDOTemporadaAnterior}" maxFractionDigits="2" /> l.</td>
			</tr>
		</c:if>
		<tr>
			<td class="ancho340"><fmt:message key="consulta.general.camp.existenciaOliDao" /></td>
			<td class="ancho110 boldtd"><fmt:formatNumber value="${existenciaOliDO}" maxFractionDigits="2" /> l.</td>
		</tr>
		<tr>
			<td class="ancho340"><fmt:message key="consulta.general.camp.existenciaOliPendent" /></td>
			<td class="ancho110 boldtd"><fmt:formatNumber value="${existenciaOliPendent}" maxFractionDigits="2" /> l.</td>
		</tr>
		<tr>
			<td class="ancho340"><fmt:message key="consulta.general.camp.existenciaOliNoDO" /></td>
			<td class="ancho110 boldtd"><fmt:formatNumber value="${existenciaOliNoDO}" maxFractionDigits="2" /> l.</td>
		</tr>
		<tr>
			<td><fmt:message key="consulta.general.camp.olivesEntrades" /></td>
			<td class="boldtd"><fmt:formatNumber value="${olivesEntrades}" maxFractionDigits="2" /> kgs.</td>
		</tr>
		<tr>
			<td><fmt:message key="consulta.general.camp.oliElaborat" /></td>
			<td class="boldtd"><fmt:formatNumber value="${oliElaborat}" maxFractionDigits="2" /> l.</td>
		</tr>
		<tr>
			<td><fmt:message key="consulta.general.camp.oliQualificat" /></td>
			<td class="boldtd"><fmt:formatNumber value="${oliQualificat}" maxFractionDigits="2" /> l.</td>
		</tr>
<!-- 		<tr> -->
<%-- 			<td><fmt:message key="consulta.general.camp.oliPendentQualificar" /></td> --%>
<%-- 			<td class="boldtd"><fmt:formatNumber value="${oliPendentQualificar}" maxFractionDigits="2" /> l.</td> --%>
<!-- 		</tr> -->
		<tr>
			<td><fmt:message key="consulta.general.camp.oliDesqualificat" /></td>
			<td class="boldtd"><fmt:formatNumber value="${oliDesqualificat}" maxFractionDigits="2" /> l.</td>
		</tr>
		<tr>
			<td><fmt:message key="consulta.general.camp.oliComercialitzatDO" /></td>
			<td class="boldtd"><fmt:formatNumber value="${oliComercialitzatDO}" maxFractionDigits="2" /> l.</td>
		</tr>
		<tr>
			<td><fmt:message key="consulta.general.camp.oliDOaEnvasadores" /></td>
			<td class="boldtd"><fmt:formatNumber value="${oliDOaEnvasadores}" maxFractionDigits="2" /> l.</td>
		</tr>
		<tr>
			<td><fmt:message key="consulta.general.camp.sortidesQualificat" /></td>
			<td class="boldtd"><fmt:formatNumber value="${oliSortidesQualificat}" maxFractionDigits="2" /> l.</td>
		</tr>
		<tr>
			<td><fmt:message key="consulta.general.camp.sortidesDesqualificat" /></td>
			<td class="boldtd"><fmt:formatNumber value="${oliSortidesDesqualificat}" maxFractionDigits="2" /> l.</td>
		</tr>
		<tr>
			<td><fmt:message key="consulta.general.camp.sortidesPendentQualificar" /></td>
			<td class="boldtd"><fmt:formatNumber value="${oliSortidesPendentQualificar}" maxFractionDigits="2" /> l.</td>
		</tr>
	</table>
	</div>


	</div>

	<!-- Colores en tablas -->
	<script type="text/javascript">
			$(document).ready(function(){
				setEstilosTabla(true);
			})
		</script>


</c:if>


</body>
</html>