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

	<script type="text/javascript">
	
		window.onload = function() {
			modificarVisibilidad();
		}
	
	</script>

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

<form id="formulario" name="PlaMantenimentForm" action="QualitatPlaMantenimentControlForm.html" method="post" class="extended seguit" onsubmit="">
	<c:if test="${not empty formData.id}">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="hidden" />
			<c:param name="path" value="formData.id" />
			<c:param name="camp" value="id" />
		</c:import>
	</c:if> 
	<c:if test="${not empty formData.idManteniment}">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="hidden" />
			<c:param name="path" value="formData.idManteniment" />
			<c:param name="camp" value="idManteniment" />
			<c:param name="name" value="idManteniment" />
		</c:import>
	</c:if> 
	
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="calendar" />
		<c:param name="path" value="formData.dataRealitzacio" />
		<c:param name="title">
			<fmt:message key="qualitat.plaManteniment.control.camp.dataRealitzacio" />
		</c:param>
		<c:param name="camp" value="campo_dataRealitzacio" />
		<c:param name="name" value="dataRealitzacio" />
		<c:param name="required" value="required" />
		<c:param name="maxlength" value="10" />
		<c:param name="aclaracio">
			<fmt:message key="proces.aclaracio.formatdata" />
		</c:param>
		<c:param name="clase" value="campoForm165" />
	</c:import>
	
	<div class="separadorH"></div>
	
	<div id="frecuenciaX">
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="select" />
		<c:param name="path" value="formData.accioRealitzada" />
		<c:param name="title">
			<fmt:message key="qualitat.plaManteniment.control.camp.accioRealitzada" />
		</c:param>
		<c:param name="camp" value="campo_accioRealitzada" />
		<c:param name="name" value="accioRealitzada" />
		<c:param name="required" value="required" />
		<c:param name="selectItems" value="accioRealitzada" />
		<c:param name="selectItemsId" value="id" />
		<c:param name="selectItemsValue" value="nom" />
		<c:param name="selectSelectedValue" value="${formData.accioRealitzada}" />
		<c:param name="clase" value="campoFormGenerico80" />
	</c:import>
	</div>
	
	
	
	<div class="separadorH"></div>
	
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.altresAccions" />
		<c:param name="title">
			<fmt:message key="qualitat.plaManteniment.control.camp.altresAccions" />
		</c:param>
		<c:param name="camp" value="campo_altresAccions" />
		<c:param name="name" value="altresAccions" />
		<c:param name="maxlength" value="100" />
		<c:param name="clase" value="campoFormCompleto" />
	</c:import>
	
	<div class="separadorH"></div>
		
	<div class="off" id="isResponsableInternX">
	<div class="separadorH"></div>
	<div class="etiqueta conMargen <c:if test="${not empty status.errorMessage}"> error</c:if>">
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="checkbox" />
		<c:param name="path" value="formData.isResponsableIntern" />
		<c:param name="title">
			<fmt:message key="qualitat.plaManteniment.control.camp.isResponsableIntern" />
		</c:param>
		<c:param name="camp" value="isResponsableIntern" />
		<c:param name="name" value="isResponsableIntern" />
		<c:param name="required" value="required" />
		<c:param name="onclick" value="modificarVisibilidad()" />
	</c:import></div>
	</div>
	
	<div class="separadorH"></div>
	
	<div id="responsableInternX">
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="select" />
		<c:param name="path" value="formData.idResponsableIntern" />
		<c:param name="title">
			<fmt:message key="qualitat.plaManteniment.control.camp.responsableIntern" />
		</c:param>
		<c:param name="camp" value="campo_idResponsableIntern" />
		<c:param name="name" value="idResponsableIntern" />
		<c:param name="required" value="required" />
		<c:param name="selectItems" value="responsableIntern" />
		<c:param name="selectItemsId" value="id" />
		<c:param name="selectItemsValue" value="nom" />
		<c:param name="selectSelectedValue" value="${formData.idResponsableIntern}" />
		<c:param name="clase" value="campoFormGrande conMargen" />
	</c:import>
	</div>
	
	<div class="separadorH"></div>
	
	<div id="responsableExternX">
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.responsableExtern" />
		<c:param name="title">
			<fmt:message key="qualitat.plaManteniment.control.camp.responsableExtern" />
		</c:param>
		<c:param name="camp" value="campo_responsableExtern" />
		<c:param name="name" value="responsableExtern" />
		<c:param name="required" value="required" />
		<c:param name="maxlength" value="15" />
		<c:param name="clase" value="campoFormGrande" />
	</c:import>
	</div>
	
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
		onclick="document.location ='QualitatPlaMantenimentControl.html?idManteniment=${idManteniment}';"><a
		id="enlaceTornarForm" href="javascript:void(0);"><fmt:message
		key="proces.tornar" /></a></div>

	<c:if test="${empty formData.id}">
		<div id="cancelarForm" class="btnCorto"
			onmouseover="underline('enlaceCancelarForm')"
			onmouseout="underline('enlaceCancelarForm')"
			onclick="document.location ='QualitatPlaMantenimentControl.html?idManteniment=${idManteniment}';"><a
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
	<form id="deleteForm" action="QualitatPlaMantenimentControl.html" method="post"
		class="seguit"
		onsubmit="return confirm('<fmt:message key="manteniment.estasegur"/>')">
		<input id="id" name="id" value="<c:out value="${formData.id}"/>" type="hidden" /> 
		<input id="idManteniment" name="idManteniment" value="<c:out value="${idManteniment}"/>" type="hidden" /> 
		<input id="action" name="action" value="delete" type="hidden" />
	</form>

	<script type="text/javascript" language="javascript">
	// <![CDATA[
				
		function modificarVisibilidad() {
			var isResponsableIntern = document.getElementById("isResponsableIntern");
			if (isResponsableIntern != null && isResponsableIntern.checked){
				document.getElementById("responsableInternX").style.display = "block";
				document.getElementById("responsableExternX").style.display = "none";
				document.getElementById("campo_responsableExtern").value = "";
			} else {
				document.getElementById("responsableInternX").style.display = "none";
				document.getElementById("responsableExternX").style.display = "block";
				document.getElementById("campo_idResponsableIntern").value = "";
			}
						
		}
				
	// ]]>
	</script>
</body>
</html>