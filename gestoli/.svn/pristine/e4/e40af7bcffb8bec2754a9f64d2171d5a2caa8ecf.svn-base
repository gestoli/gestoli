<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ page import="es.caib.gestoli.front.util.*"%>
<%@ page import="java.util.ResourceBundle"%>
<%@ page import="java.util.Locale"%>
<%

	String lang = Idioma.getLang(request);
	request.setAttribute("lang",lang);
%>

<html>
<head>

<title><c:choose>
	<c:when test="${not empty formData.id}">
		<fmt:message key="manteniment.modificacio" />
	</c:when>
	<c:otherwise>
		<fmt:message key="manteniment.alta" />
	</c:otherwise>
</c:choose> <fmt:message key="analiticaParametreTipus.tipusdemant" /></title>


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


</head>
<body>

<form id="formulario" name="APPCCFitxaControlForm" action="QualitatAPPCCFitxaControlForm.html" method="post" class="extended seguit" onsubmit="">
	<c:set var="disabled" value="" />
		<c:if test="${empty esEstAdministrador}">
			<c:set var="disabled" value="true" />
		</c:if>
		
	<c:if test="${not empty formData.idControl}">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="hidden" />
			<c:param name="path" value="formData.idControl" />
			<c:param name="camp" value="idControl" />
		</c:import>
	</c:if>
	<c:if test="${not empty formData.idFitxa}">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="hidden" />
			<c:param name="path" value="formData.idFitxa" />
			<c:param name="camp" value="idFitxa" />
		</c:import> 
	</c:if> 

	<div class="separadorH"></div>
	
	<h3><fmt:message key="qualitat.appcc.fitxaControl.titol.prevencio" /></h3>
	
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.mesuresPrevencio" />
		<c:param name="title">
			<fmt:message key="qualitat.appcc.fitxaControl.camp.mesuresPrevencio" />
		</c:param>
		<c:param name="camp" value="campo_mesuresPrevencio" />
		<c:param name="name" value="mesuresPrevencio" />
		<c:param name="required" value="required" />
		<c:param name="maxlength" value="100" />
		<c:param name="clase" value="campoFormCompleto" />
		<c:param name="disabled" value="${disabled}" />
	</c:import>
	
	<div class="separadorH"></div>
	
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="select" />
		<c:param name="path" value="formData.responsablePrevencio" />
		<c:param name="title">
			<fmt:message key="qualitat.appcc.fitxaControl.camp.responsablePrevencio" />
		</c:param>
		<c:param name="camp" value="campo_responsablePrevencio" />
		<c:param name="name" value="idResponsablePrevencio" />
		<c:param name="required" value="required" />
		<c:param name="selectItems" value="personal" />
		<c:param name="selectItemsId" value="id" />
		<c:param name="selectItemsValue" value="nom" />
		<c:param name="selectSelectedValue" value="${formData.idResponsablePrevencio}" />
		<c:param name="clase" value="campoFormGrande" />
		<c:param name="disabled" value="${disabled}" />
	</c:import>
	
	
	<div class="separadorH"></div>
	
	<br /><h3><fmt:message key="qualitat.appcc.fitxaControl.titol.vigilancia" /></h3>
	
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.vigilancia" />
		<c:param name="title">
			<fmt:message key="qualitat.appcc.fitxaControl.camp.vigilancia" />
		</c:param>
		<c:param name="camp" value="campo_vigilancia" />
		<c:param name="name" value="vigilancia" />
		<c:param name="required" value="required" />
		<c:param name="maxlength" value="100" />
		<c:param name="clase" value="campoFormCompleto" />
		<c:param name="disabled" value="${disabled}" />
	</c:import>
		
	<div class="separadorH"></div>
	
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.registre" />
		<c:param name="title">
			<fmt:message key="qualitat.appcc.fitxaControl.camp.registre" />
		</c:param>
		<c:param name="camp" value="campo_registre" />
		<c:param name="name" value="registre" />
		<c:param name="required" value="required" />
		<c:param name="maxlength" value="100" />
		<c:param name="clase" value="campoFormCompleto" />
		<c:param name="disabled" value="${disabled}" />
	</c:import>
	
	<div class="separadorH"></div>
	
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.limits" />
		<c:param name="title">
			<fmt:message key="qualitat.appcc.fitxaControl.camp.limits" />
		</c:param>
		<c:param name="camp" value="campo_limits" />
		<c:param name="name" value="limits" />
		<c:param name="required" value="required" />
		<c:param name="maxlength" value="100" />
		<c:param name="clase" value="campoFormCompleto" />
		<c:param name="disabled" value="${disabled}" />
	</c:import>
	
	<div class="separadorH"></div>
	
	
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="select" />
		<c:param name="path" value="formData.responsableVigilancia" />
		<c:param name="title">
			<fmt:message key="qualitat.appcc.fitxaControl.camp.responsableVigilancia" />
		</c:param>
		<c:param name="camp" value="campo_responsableVigilancia" />
		<c:param name="name" value="idResponsableVigilancia" />
		<c:param name="required" value="required" />
		<c:param name="selectItems" value="personal" />
		<c:param name="selectItemsId" value="id" />
		<c:param name="selectItemsValue" value="nom" />
		<c:param name="selectSelectedValue" value="${formData.idResponsableVigilancia}" />
		<c:param name="clase" value="campoFormGrande" />
		<c:param name="disabled" value="${disabled}" />
	</c:import>
	
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="select" />
		<c:param name="path" value="formData.freqVigilancia" />
		<c:param name="title">
			<fmt:message key="qualitat.appcc.fitxaControl.camp.freqVigilancia" />
		</c:param>
		<c:param name="camp" value="campo_freqVigilancia" />
		<c:param name="name" value="freqVigilancia" />
		<c:param name="required" value="required" />
		<c:param name="selectItems" value="frecuencia" />
		<c:param name="selectItemsId" value="id" />
		<c:param name="selectItemsValue" value="nom" />
		<c:param name="selectSelectedValue" value="${formData.freqVigilancia}" />
		<c:param name="clase" value="campoFormGrande" />
		<c:param name="disabled" value="${disabled}" />
	</c:import>
	
	<div class="separadorH"></div>

	<br/ ><h3><fmt:message key="qualitat.appcc.fitxaControl.titol.correccio" /></h3>
	
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.mesuresCorreccio" />
		<c:param name="title">
			<fmt:message key="qualitat.appcc.fitxaControl.camp.mesuresCorreccio" />
		</c:param>
		<c:param name="camp" value="campo_mesuresCorreccio" />
		<c:param name="name" value="mesuresCorreccio" />
		<c:param name="required" value="required" />
		<c:param name="maxlength" value="100" />
		<c:param name="clase" value="campoFormCompleto" />
		<c:param name="disabled" value="${disabled}" />
	</c:import>
	
	<div class="separadorH"></div>
	
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="select" />
		<c:param name="path" value="formData.responsableCorreccio" />
		<c:param name="title">
			<fmt:message key="qualitat.appcc.fitxaControl.camp.responsableCorreccio" />
		</c:param>
		<c:param name="camp" value="campo_responsableCorreccio" />
		<c:param name="name" value="idResponsableCorreccio" />
		<c:param name="required" value="required" />
		<c:param name="selectItems" value="personal" />
		<c:param name="selectItemsId" value="id" />
		<c:param name="selectItemsValue" value="nom" />
		<c:param name="selectSelectedValue" value="${formData.idResponsableCorreccio}" />
		<c:param name="clase" value="campoFormGrande" />
		<c:param name="disabled" value="${disabled}" />
	</c:import>
	
	<div class="separadorH"></div>
	<br></br>

	<div class="botonesForm">
	
		<c:if test="${not empty esEstAdministrador}">
			<c:choose>
				<c:when test="${not empty formData.id}">
					<div id="guardarForm" class="btnCorto"
						onclick="if(confirm('<fmt:message key="manteniment.modificar.confirm"/>')){submitForm('formulario')}"
						onmouseover="underline('enlaceGuardarForm')"
						onmouseout="underline('enlaceGuardarForm')"><a
						id="enlaceGuardarForm" href="javascript:void(0);"><fmt:message
						key="manteniment.guardar" /></a></div>
				</c:when>
				<c:otherwise>
					<div id="guardarForm" class="btnCorto"
						onclick="submitForm('formulario')"
						onmouseover="underline('enlaceGuardarForm')"
						onmouseout="underline('enlaceGuardarForm')"><a
						id="enlaceGuardarForm" href="javascript:void(0);"><fmt:message
						key="manteniment.guardar" /></a></div>
				</c:otherwise>
			</c:choose>
		</c:if>

		<div class="btnCorto" 
			onmouseover="underline('enlaceTornarForm')"
			onmouseout="underline('enlaceTornarForm')" 
			onclick="document.location ='QualitatAPPCCFitxaControl.html';"><a
			id="enlaceTornarForm" href="javascript:void(0);"><fmt:message
			key="proces.tornar" /></a></div>
			
		<c:if test="${not empty esEstAdministrador}">
			<c:if test="${empty formData.id}">
				<div id="cancelarForm" class="btnCorto"
					onmouseover="underline('enlaceCancelarForm')"
					onmouseout="underline('enlaceCancelarForm')"
					onclick="document.location ='QualitatAPPCCFitxaControl.html';"><a
					id="enlaceCancelarForm" href="javascript:void(0);"><fmt:message
					key="proces.cancelar" /></a></div>
			</c:if>
		</c:if>
		
	</div>

</form>
	

</body>
</html>