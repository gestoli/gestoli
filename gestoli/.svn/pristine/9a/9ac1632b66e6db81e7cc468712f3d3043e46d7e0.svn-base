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
</c:choose> <fmt:message key="avis.tipusdemant" /></title>


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

<form id="formulario" name="AvisForm" action="AvisForm.html" method="post" class="extended seguit" onsubmit="">
	<c:if test="${not empty formData.id}">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="hidden" />
			<c:param name="path" value="formData.id" />
			<c:param name="camp" value="id" />
		</c:import>
	</c:if> 
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="hidden" />
		<c:param name="path" value="formData.idEstabliment" />
		<c:param name="camp" value="idEstabliment" />
		<c:param name="value" value="${idEstabliment}" />
	</c:import> 
	
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="select" />
		<c:param name="path" value="formData.tipus" />
		<c:param name="title">
			<fmt:message key="avis.camp.tipus" />
		</c:param>
		<c:param name="camp" value="campo_tipus" />
		<c:param name="name" value="tipus" />
		<c:param name="required" value="required" />
		<c:param name="selectItems" value="tipus" />
		<c:param name="selectItemsId" value="id" />
		<c:param name="selectItemsValue" value="nom" />
		<c:param name="selectSelectedValue" value="${formData.tipus}" />
		<c:param name="clase" value="campoFormNormal conMargen" />
	</c:import>
	
	<div class="separadorH"></div>
	
	<div id="observacionesForm" class="campoForm">	
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="textareaMedio" />
			<c:param name="path" value="formData.descripcio" />
			<c:param name="title">
				<fmt:message key="avis.camp.descripcio" />
			</c:param>
			<c:param name="camp" value="campo_descripcio" />
			<c:param name="name" value="descripcio" />
			<c:param name="required" value="required" />
			<c:param name="maxlength" value="500" />
			<c:param name="clase" value="campoFormCompleto conMargen" />
		</c:import>
	</div>

	<div class="separadorH"></div>

	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="select" />
		<c:param name="path" value="formData.frecuencia" />
		<c:param name="title">
			<fmt:message key="avis.camp.frecuencia" />
		</c:param>
		<c:param name="camp" value="campo_frecuencia" />
		<c:param name="name" value="frecuencia" />
		<c:param name="required" value="required" />
		<c:param name="selectItems" value="frecuencia" />
		<c:param name="selectItemsId" value="id" />
		<c:param name="selectItemsValue" value="nom" />
		<c:param name="selectSelectedValue" value="${formData.frecuencia}" />
		<c:param name="clase" value="campoFormGenerico80 conMargen" />
	</c:import>

	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="calendar" />
		<c:param name="path" value="formData.dataSeguent" />
		<c:param name="title">
			<fmt:message key="avis.camp.dataSeguent" />
		</c:param>
		<c:param name="camp" value="campo_dataSeguent" />
		<c:param name="name" value="dataSeguent" />
		<c:param name="required" value="required" />
		<c:param name="maxlength" value="10" />
		<c:param name="aclaracio">
			<fmt:message key="proces.aclaracio.formatdata" />
		</c:param>
		<c:param name="clase" value="campoForm165" />
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
		onclick="document.location ='Avis.html';"><a
		id="enlaceTornarForm" href="javascript:void(0);"><fmt:message
		key="proces.tornar" /></a></div>

	<c:if test="${empty formData.id}">
		<div id="cancelarForm" class="btnCorto"
			onmouseover="underline('enlaceCancelarForm')"
			onmouseout="underline('enlaceCancelarForm')"
			onclick="document.location ='Avis.html';"><a
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
	<form id="deleteForm" action="Avis.html" method="post"
		class="seguit"
		onsubmit="return confirm('<fmt:message key="manteniment.estasegur"/>')">
		<input id="id" name="id" value="<c:out value="${formData.id}"/>" type="hidden" /> 
		<input id="action" name="action" value="delete" type="hidden" />
	</form>


</body>
</html>