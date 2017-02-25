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

<form id="formulario" name="QuadernCampForm" action="QuadernCampForm.html" method="post" class="extended seguit" onsubmit="">
	<c:if test="${not empty formData.id}">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="hidden" />
			<c:param name="path" value="formData.id" />
			<c:param name="camp" value="id" />
		</c:import>
	</c:if> 
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="hidden" />
		<c:param name="path" value="formData.idOlivicultor" />
		<c:param name="camp" value="idOlivicultor" />
		<c:param name="value" value="${idOlivicultor}" />
	</c:import> 
	
	<div class="separadorH"></div>
	
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="calendar" />
		<c:param name="path" value="formData.data" />
		<c:param name="title">
			<fmt:message key="quadernCamp.camp.data" />
		</c:param>
		<c:param name="camp" value="campo_data" />
		<c:param name="name" value="data" />
		<c:param name="required" value="required" />
		<c:param name="maxlength" value="10" />
		<c:param name="aclaracio">
			<fmt:message key="proces.aclaracio.formatdata" />
		</c:param>
		<c:param name="clase" value="campoFormNormal conMargen" />
	</c:import>
	
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="select" />
		<c:param name="path" value="formData.idPlantacio" />
		<c:param name="title">
			<fmt:message key="quadernCamp.camp.plantacio" />
		</c:param>
		<c:param name="camp" value="campo_idPlantacio" />
		<c:param name="name" value="idPlantacio" />
		<c:param name="required" value="required" />
		<c:param name="selectItems" value="plantacions" />
		<c:param name="selectItemsId" value="id" />
		<c:param name="selectItemsValue" value="nom" />
		<c:param name="selectSelectedValue" value="${formData.idPlantacio}" />
		<c:param name="clase" value="campoFormGrande" />
	</c:import>

	<div class="separadorH"></div>
	
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.tractament" />
		<c:param name="title">
			<fmt:message key="quadernCamp.camp.tractament" />
		</c:param>
		<c:param name="camp" value="campo_tractament" />
		<c:param name="name" value="tractament" />
		<c:param name="required" value="required" />
		<c:param name="maxlength" value="128" />
		<c:param name="clase" value="campoFormGrande " />
	</c:import>

	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.materiaActiva" />
		<c:param name="title">
			<fmt:message key="quadernCamp.camp.materiaActiva" />
		</c:param>
		<c:param name="camp" value="campo_materiaActiva" />
		<c:param name="name" value="materiaActiva" />
		<c:param name="required" value="required" />
		<c:param name="maxlength" value="128" />
		<c:param name="clase" value="campoFormGrande " />
	</c:import>

	<div class="separadorH"></div>
	
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.marca" />
		<c:param name="title">
			<fmt:message key="quadernCamp.camp.marca" />
		</c:param>
		<c:param name="camp" value="campo_marca" />
		<c:param name="name" value="marca" />
		<c:param name="required" value="required" />
		<c:param name="maxlength" value="128" />
		<c:param name="clase" value="campoFormGrande " />
	</c:import>

	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.dosi" />
		<c:param name="title">
			<fmt:message key="quadernCamp.camp.dosi" />
		</c:param>
		<c:param name="camp" value="campo_dosi" />
		<c:param name="name" value="dosi" />
		<c:param name="required" value="required" />
		<c:param name="maxlength" value="128" />
		<c:param name="clase" value="campoFormGrande " />
	</c:import>

	<div class="separadorH"></div>

	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.litresBrou" />
		<c:param name="title">
			<fmt:message key="quadernCamp.camp.litresBrou" />
		</c:param>
		<c:param name="camp" value="campo_litresBrou" />
		<c:param name="name" value="litresBrou" />
		<c:param name="required" value="required" />
		<c:param name="maxlength" value="128" />
		<c:param name="clase" value="campoFormGrande " />
	</c:import>

	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.terminiSeguretat" />
		<c:param name="title">
			<fmt:message key="quadernCamp.camp.terminiSeguretat" />
		</c:param>
		<c:param name="camp" value="campo_terminiSeguretat" />
		<c:param name="name" value="terminiSeguretat" />
		<c:param name="required" value="required" />
		<c:param name="maxlength" value="128" />
		<c:param name="clase" value="campoFormGrande " />
	</c:import>

	<div class="separadorH"></div>

	<div id="observacionesForm" class="campoForm">	
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="textarea" />
			<c:param name="path" value="formData.observacions" />
			<c:param name="title">
				<fmt:message key="quadernCamp.camp.observacions" />
			</c:param>
			<c:param name="camp" value="campo_observacions" />
			<c:param name="name" value="observacions" />
			<c:param name="required" value="required" />
			<c:param name="maxlength" value="5000" />
		</c:import>
	</div>
	
	<div class="separadorH"></div>
	<br></br>

	<div class="botonesForm">
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
	
		<div class="btnCorto" 
			onmouseover="underline('enlaceTornarForm')"
			onmouseout="underline('enlaceTornarForm')" 
			onclick="document.location ='QuadernCampLlistat.html';"><a
			id="enlaceTornarForm" href="javascript:void(0);"><fmt:message
			key="proces.tornar" /></a></div>
	
		<c:if test="${empty formData.id}">
			<div id="cancelarForm" class="btnCorto"
				onmouseover="underline('enlaceCancelarForm')"
				onmouseout="underline('enlaceCancelarForm')"
				onclick="document.location ='QuadernCampLlistat.html';"><a
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
	<form id="deleteForm" action="QuadernCamp.html" method="post"
		class="seguit"
		onsubmit="return confirm('<fmt:message key="manteniment.estasegur"/>')">
		<input id="id" name="id" value="<c:out value="${formData.id}"/>" type="hidden" /> 
		<input id="action" name="action" value="delete" type="hidden" />
	</form>

</body>
</html>