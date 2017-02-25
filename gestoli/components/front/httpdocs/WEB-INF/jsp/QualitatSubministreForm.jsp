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
	<c:when test="${not empty formData.codi}">
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
<link href="js/calendar/calendar-viti.css" rel="stylesheet"
	type="text/css" />

<script type="text/javascript" src="js/form.js"></script>


</head>
<body>

<form id="formulario" name="SubministreForm" action="QualitatSubministreForm.html" method="post" class="extended seguit" onsubmit="">
	<c:set var="disabled" value="" />
	<c:if test="${empty esEstAdministrador && empty esEstEncarregat}">
		<c:set var="disabled" value="true" />
	</c:if>
	
	<c:if test="${not empty formData.codi}">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="hidden" />
			<c:param name="path" value="formData.codi" />
			<c:param name="camp" value="codi" />
		</c:import>
	</c:if> 
	
		<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="select" />
		<c:param name="path" value="formData.proveidorId" />
		<c:param name="title">
			<fmt:message key="qualitat.subministre.camp.proveidor" />
		</c:param>
		<c:param name="camp" value="campo_proveidor" />
		<c:param name="name" value="proveidorId" />
		<c:param name="required" value="required" />
		<c:param name="selectItems" value="proveidors" />
		<c:param name="selectItemsId" value="id" />
		<c:param name="selectItemsValue" value="nom" />
		<c:param name="selectSelectedValue" value="${formData.proveidorId}" />
		<c:param name="clase" value="campoFormGrande conMargen" />
		<c:param name="disabled" value="${disabled}" />
	</c:import>
	
	<div class="separadorH"></div>
	
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.tipusSubministre" />
		<c:param name="title">
			<fmt:message key="qualitat.subministre.camp.subministre" />
		</c:param>
		<c:param name="camp" value="campo_subministre" />
		<c:param name="name" value="tipusSubministre" />
		<c:param name="required" value="required" />
		<c:param name="maxlength" value="128" />
		<c:param name="clase" value="campoFormGrande conMargen" />
		<c:param name="disabled" value="${disabled}" />
	</c:import>

	<div class="separadorH"></div>

	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="calendar" />
		<c:param name="path" value="formData.homologacio" />
		<c:param name="title">
			<fmt:message key="qualitat.subministre.camp.homologacio" />
		</c:param>
		<c:param name="camp" value="campo_homologacio" />
		<c:param name="name" value="homologacio" />
		<c:param name="required" value="required" />
		<c:param name="maxlength" value="10" />
		<c:param name="aclaracio">
			<fmt:message key="proces.aclaracio.formatdata" />
		</c:param>
		<c:param name="clase" value="campoForm165" />
		<c:param name="disabled" value="${disabled}" />
	</c:import>
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="calendar" />
		<c:param name="path" value="formData.deshomologacio" />
		<c:param name="title">
			<fmt:message key="qualitat.subministre.camp.deshomologacio" />
		</c:param>
		<c:param name="camp" value="campo_deshomologacio" />
		<c:param name="name" value="deshomologacio" />
		<c:param name="required" value="required" />
		<c:param name="maxlength" value="10" />
		<c:param name="aclaracio">
			<fmt:message key="proces.aclaracio.formatdata" />
		</c:param>
		<c:param name="clase" value="campoForm165" />
		<c:param name="disabled" value="${disabled}" />
	</c:import>
	
	<div class="separadorH"></div>
	
	<div id="observacionesForm" class="campoForm">	
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="textarea" />
			<c:param name="path" value="formData.observacions" />
			<c:param name="title">
				<fmt:message key="qualitat.subministre.camp.observacions" />
			</c:param>
			<c:param name="camp" value="campo_observacions" />
			<c:param name="name" value="observacions" />
			<c:param name="required" value="required" />
			<c:param name="maxlength" value="128" />
			<c:param name="disabled" value="${disabled}" />
		</c:import>
	</div>

	<div class="separadorH"></div>
	<br></br>

	<div class="botonesForm">
		<c:if test="${not empty esEstAdministrador || not empty esEstEncarregat}">
			<c:choose>
				<c:when test="${not empty formData.codi}">
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
			onclick="document.location ='QualitatProveidorForm.html?codi=${idProveidor}';"><a
			id="enlaceTornarForm" href="javascript:void(0);"><fmt:message
			key="proces.tornar" /></a></div>

		<c:if test="${not empty esEstAdministrador || not empty esEstEncarregat}">
			<c:if test="${empty formData.codi}">
				<div id="cancelarForm" class="btnCorto"
					onmouseover="underline('enlaceCancelarForm')"
					onmouseout="underline('enlaceCancelarForm')"
					onclick="document.location ='QualitatProveidorForm.html?codi=${idProveidor}';"><a
					id="enlaceCancelarForm" href="javascript:void(0);"><fmt:message
					key="proces.cancelar" /></a></div>
			</c:if>
			<c:if test="${not empty formData.codi}">
				<input id="action" name="action" value="delete" type="hidden" />
				<div id="eliminarForm" class="btnCorto"
					onmouseover="underline('enlaceBorrarForm')"
					onmouseout="underline('enlaceBorrarForm')"
					onclick="submitFormConfirm('deleteForm','<fmt:message key="manteniment.esborrar.confirm"/>');">
				<a id="enlaceBorrarForm" href="javascript:void(0);"><fmt:message
					key="manteniment.esborrar" /></a></div>
			</c:if>
		</c:if>
	
	</div>

	</form>
	<form id="deleteForm" action="QualitatSubministre.html" method="post"
		class="seguit"
		onsubmit="return confirm('<fmt:message key="manteniment.estasegur"/>')">
		<input id="idProveidor" name="idProveidor" value="<c:out value="${formData.proveidorId}"/>" type="hidden" /> 
		<input id="id" name="id" value="<c:out value="${formData.codi}"/>" type="hidden" /> 
		<input id="action" name="action" value="delete" type="hidden" />
	</form>
</body>
</html>