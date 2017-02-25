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

<title>
	<c:choose>
		<c:when test="${not empty formData.id}">
			<fmt:message key="manteniment.modificacio" />
		</c:when>
		<c:otherwise>
			<fmt:message key="manteniment.alta" />
		</c:otherwise>
	</c:choose> 
	<fmt:message key="analiticaParametreTipus.tipusdemant" />
</title>


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

<form id="formulario" name="APPCCEtapaPerillForm" action="QualitatAPPCCEtapaPerillForm.html" method="post" class="extended seguit" onsubmit="">
	<c:if test="${not empty formData.id}">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="hidden" />
			<c:param name="path" value="formData.id" />
			<c:param name="camp" value="id" />
		</c:import>
	</c:if> 
	<c:if test="${not empty formData.idEtapa}">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="hidden" />
			<c:param name="path" value="formData.idEtapa" />
			<c:param name="camp" value="idEtapa" />
			<c:param name="name" value="idEtapa" />
		</c:import>
	</c:if> 
	
	<div class="separadorH"></div>
	
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="select" />
		<c:param name="path" value="formData.tipus" />
		<c:param name="title">
			<fmt:message key="qualitat.appcc.etapa.perill.camp.tipus" />
		</c:param>
		<c:param name="camp" value="campo_tipus" />
		<c:param name="name" value="tipus" />
		<c:param name="required" value="required" />
		<c:param name="selectItems" value="tipus" />
		<c:param name="selectItemsId" value="id" />
		<c:param name="selectItemsValue" value="nom" />
		<c:param name="selectSelectedValue" value="${formData.tipus}" />
		<c:param name="clase" value="campoFormGrande" />
	</c:import>
	
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.detall" />
		<c:param name="title">
			<fmt:message key="qualitat.appcc.etapa.perill.camp.detall" />
		</c:param>
		<c:param name="camp" value="campo_detall" />
		<c:param name="name" value="detall" />
		<c:param name="required" value="required" /> 
		<c:param name="maxlength" value="50" />
		<c:param name="clase" value="campoFormGrande conMargen" />
	</c:import>

	<div class="separadorH"></div>
	
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.causa" />
		<c:param name="title">
			<fmt:message key="qualitat.appcc.etapa.perill.camp.causa" />
		</c:param>
		<c:param name="camp" value="campo_causa" />
		<c:param name="name" value="causa" />
		<c:param name="required" value="required" /> 
		<c:param name="clase" value="campoFormCompleto conMargen" />
	</c:import>
	
	<div class="separadorH"></div>
	
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="select" />
		<c:param name="path" value="formData.prevencio" />
		<c:param name="title">
			<fmt:message key="qualitat.appcc.etapa.perill.camp.prevencio" />
		</c:param>
		<c:param name="camp" value="campo_prevencio" />
		<c:param name="name" value="prevencio" />
		<c:param name="required" value="required" />
		<c:param name="selectItems" value="prevencions" />
		<c:param name="selectItemsId" value="id" />
		<c:param name="selectItemsValue" value="nom" />
		<c:param name="selectSelectedValue" value="${formData.prevencio}" />
		<c:param name="clase" value="campoForm165 conMargen" />
	</c:import>
	
	<div class="separadorH"></div>
	
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="select" />
		<c:param name="path" value="formData.probabilitat" />
		<c:param name="title">
			<fmt:message key="qualitat.appcc.etapa.perill.camp.probabilitat" />
		</c:param>
		<c:param name="camp" value="campo_probabilitat" />
		<c:param name="name" value="probabilitat" />
		<c:param name="required" value="required" />
		<c:param name="selectItems" value="probabilitats" />
		<c:param name="selectItemsId" value="id" />
		<c:param name="selectItemsValue" value="nom" />
		<c:param name="selectSelectedValue" value="${formData.probabilitat}" />
		<c:param name="clase" value="campoForm165 conMargen" />
	</c:import>
	
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="select" />
		<c:param name="path" value="formData.gravetat" />
		<c:param name="title">
			<fmt:message key="qualitat.appcc.etapa.perill.camp.gravetat" />
		</c:param>
		<c:param name="camp" value="campo_gravetat" />
		<c:param name="name" value="gravetat" />
		<c:param name="required" value="required" />
		<c:param name="selectItems" value="gravetats" />
		<c:param name="selectItemsId" value="id" />
		<c:param name="selectItemsValue" value="nom" />
		<c:param name="selectSelectedValue" value="${formData.gravetat}" />
		<c:param name="clase" value="campoForm165 conMargen" />
	</c:import>
	
	<div class="separadorH"></div>
	<br></br>
	

	<div class="botonesForm"><c:choose>
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

	<div class="btnCorto" 
		onmouseover="underline('enlaceTornarForm')"
		onmouseout="underline('enlaceTornarForm')" 
		onclick="document.location ='QualitatAPPCCEtapaForm.html?id=${idEtapa}';"><a
		id="enlaceTornarForm" href="javascript:void(0);"><fmt:message
		key="proces.tornar" /></a></div>

	<c:if test="${empty formData.id}">
		<div id="cancelarForm" class="btnCorto"
			onmouseover="underline('enlaceCancelarForm')"
			onmouseout="underline('enlaceCancelarForm')"
			onclick="document.location ='QualitatAPPCCEtapaForm.html?id=${idEtapa}';"><a
			id="enlaceCancelarForm" href="javascript:void(0);"><fmt:message
			key="proces.cancelar" /></a></div>
	</c:if>
	<c:if test="${not empty formData.id}">
		<input id="action" name="action" value="delete" type="hidden" />
		<div id="eliminarForm" class="btnCorto"
			onmouseover="underline('enlaceBorrarForm')"
			onmouseout="underline('enlaceBorrarForm')"
			onclick="submitFormConfirm('deleteForm','<fmt:message key="manteniment.esborrar.confirm"/>');">
		<a id="enlaceBorrarForm" href="javascript:void(0);"><fmt:message
			key="manteniment.esborrar" /></a></div>
	</c:if>
	
	</div>

	</form>
	<form id="deleteForm" action="QualitatAPPCCEtapaPerill.html" method="post"
		class="seguit"
		onsubmit="return confirm('<fmt:message key="manteniment.estasegur"/>')">
		<input id="id" name="id" value="<c:out value="${formData.id}"/>" type="hidden" /> 
		<input id="idEtapa" name="idEtapa" value="<c:out value="${idEtapa}"/>" type="hidden" />
		<input id="action" name="action" value="delete" type="hidden" />
	</form>


</body>
</html>