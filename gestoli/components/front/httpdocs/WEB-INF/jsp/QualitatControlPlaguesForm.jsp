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

<form id="formulario" name="ControlPlaguesForm" action="QualitatControlPlaguesForm.html" method="post" class="extended seguit" onsubmit="">
	<c:set var="disabled" value="" />
	<c:if test="${empty esEstAdministrador}">
		<c:set var="disabled" value="true" />
	</c:if>

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
	
	<div class="separadorH"></div>
		
	<div class="off" id="isResponsableInternX">
	<div class="separadorH"></div>
	<div class="etiqueta conMargen <c:if test="${not empty status.errorMessage}"> error</c:if>">
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="checkbox" />
		<c:param name="path" value="formData.isResponsableIntern" />
		<c:param name="title">
			<fmt:message key="qualitat.controlPlagues.camp.isResponsableIntern" />
		</c:param>
		<c:param name="camp" value="isResponsableIntern" />
		<c:param name="name" value="isResponsableIntern" />
		<c:param name="required" value="required" />
		<c:param name="onclick" value="modificarVisibilidad()" />
		<c:param name="disabled" value="${disabled}" />
	</c:import></div>
	</div>
	
	<div class="separadorH"></div>
	
	<div id="responsableInternX">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="text" />
			<c:param name="path" value="formData.ubicacio" />
			<c:param name="title">
				<fmt:message key="qualitat.controlPlagues.camp.ubicacio" />
			</c:param>
			<c:param name="camp" value="campo_ubicacio" />
			<c:param name="name" value="ubicacio" />
			<c:param name="required" value="required" />
			<c:param name="maxlength" value="50" />
			<c:param name="clase" value="campoFormGrande" />
			<c:param name="disabled" value="${disabled}" />
		</c:import>
		
		<div class="separadorH"></div>
		
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="text" />
			<c:param name="path" value="formData.elementControl" />
			<c:param name="title">
				<fmt:message key="qualitat.controlPlagues.camp.elementControl" />
			</c:param>
			<c:param name="camp" value="campo_elementControl" />
			<c:param name="name" value="elementControl" />
			<c:param name="required" value="required" />
			<c:param name="maxlength" value="50" />
			<c:param name="clase" value="campoFormCompleto" />
			<c:param name="disabled" value="${disabled}" />
		</c:import>
		
		<div class="separadorH"></div>
		
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="select" />
			<c:param name="path" value="formData.frecuencia" />
			<c:param name="title">
				<fmt:message key="qualitat.controlPlagues.camp.frecuencia" />
			</c:param>
			<c:param name="camp" value="campo_frecuencia" />
			<c:param name="name" value="frecuencia" />
			<c:param name="required" value="required" />
			<c:param name="selectItems" value="frecuencia" />
			<c:param name="selectItemsId" value="id" />
			<c:param name="selectItemsValue" value="nom" />
			<c:param name="selectSelectedValue" value="${formData.frecuencia}" />
			<c:param name="clase" value="campoForm165" />
			<c:param name="disabled" value="${disabled}" />
		</c:import>
		
		<div class="separadorH"></div>
		
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="select" />
			<c:param name="path" value="formData.idResponsableIntern" />
			<c:param name="title">
				<fmt:message key="qualitat.controlPlagues.camp.responsableIntern" />
			</c:param>
			<c:param name="camp" value="campo_idRresponsableIntern" />
			<c:param name="name" value="idResponsableIntern" />
			<c:param name="required" value="required" />
			<c:param name="selectItems" value="responsableIntern" />
			<c:param name="selectItemsId" value="id" />
			<c:param name="selectItemsValue" value="nom" />
			<c:param name="selectSelectedValue" value="${formData.idResponsableIntern}" />
			<c:param name="clase" value="campoFormGrande conMargen" />
			<c:param name="disabled" value="${disabled}" />
		</c:import>
	</div>
	
	<div class="separadorH"></div>
	
	<div id="empresaExternaX">
		<h3><fmt:message key="qualitat.controlPlagues.camp.empresaExterna.titol" /></h3>
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="text" />
			<c:param name="path" value="formData.empresaExterna" />
			<c:param name="title">
				<fmt:message key="qualitat.controlPlagues.camp.empresaExterna" />
			</c:param>
			<c:param name="camp" value="campo_empresaExterna" />
			<c:param name="name" value="empresaExterna" />
			<c:param name="required" value="required" />
			<c:param name="maxlength" value="50" />
			<c:param name="clase" value="campoFormGrande" />
			<c:param name="disabled" value="${disabled}" />
		</c:import> 
		
		<div class="separadorH"></div>
	
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="calendar" />
			<c:param name="path" value="formData.iniciContracte" />
			<c:param name="title">
				<fmt:message key="qualitat.controlPlagues.camp.iniciContracte" />
			</c:param>
			<c:param name="camp" value="campo_iniciContracte" />
			<c:param name="name" value="iniciContracte" />
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
			<c:param name="path" value="formData.fiContracte" />
			<c:param name="title">
				<fmt:message key="qualitat.controlPlagues.camp.fiContracte" />
			</c:param>
			<c:param name="camp" value="campo_fiContracte" />
			<c:param name="name" value="fiContracte" />
			<c:param name="required" value="required" />
			<c:param name="maxlength" value="10" />
			<c:param name="aclaracio">
				<fmt:message key="proces.aclaracio.formatdata" />
			</c:param>
			<c:param name="clase" value="campoForm165" />
			<c:param name="disabled" value="${disabled}" />
		</c:import>
		
		<div class="separadorH"></div>
		
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="select" />
			<c:param name="path" value="formData.plagaObjectiu" />
			<c:param name="title">
				<fmt:message key="qualitat.controlPlagues.camp.plagaObjectiu" />
			</c:param>
			<c:param name="camp" value="campo_plagaObjectiu" />
			<c:param name="name" value="plagaObjectiu" />
			<c:param name="required" value="required" />
			<c:param name="selectItems" value="plagaObjectiu" />
			<c:param name="selectItemsId" value="id" />
			<c:param name="selectItemsValue" value="nom" />
			<c:param name="selectSelectedValue" value="${formData.plagaObjectiu}" />
			<c:param name="clase" value="campoForm165 conMargen" />
			<c:param name="disabled" value="${disabled}" />
		</c:import>
		
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="select" />
			<c:param name="path" value="formData.frecSeguiment" />
			<c:param name="title">
				<fmt:message key="qualitat.controlPlagues.camp.frecSeguiment" />
			</c:param>
			<c:param name="camp" value="campo_frecSeguiment" />
			<c:param name="name" value="frecSeguiment" />
			<c:param name="required" value="required" />
			<c:param name="selectItems" value="frecSeguiment" />
			<c:param name="selectItemsId" value="id" />
			<c:param name="selectItemsValue" value="nom" />
			<c:param name="selectSelectedValue" value="${formData.frecSeguiment}" />
			<c:param name="clase" value="campoForm165" />
			<c:param name="disabled" value="${disabled}" />
		</c:import>
	
	</div>
	
	
	<div class="separadorH"></div>
	
		
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
			onclick="document.location ='QualitatControlPlagues.html';"><a
			id="enlaceTornarForm" href="javascript:void(0);"><fmt:message
			key="proces.tornar" /></a></div>
	
		<c:if test="${not empty esEstAdministrador}">
			<c:if test="${empty formData.id}">
				<div id="cancelarForm" class="btnCorto"
					onmouseover="underline('enlaceCancelarForm')"
					onmouseout="underline('enlaceCancelarForm')"
					onclick="document.location ='QualitatControlPlagues.html';"><a
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
		</c:if>
	
	</div>

	</form>
	<form id="deleteForm" action="QualitatControlPlagues.html" method="post"
		class="seguit"
		onsubmit="return confirm('<fmt:message key="manteniment.estasegur"/>')">
		<input id="id" name="id" value="<c:out value="${formData.id}"/>" type="hidden" /> 
		<input id="action" name="action" value="delete" type="hidden" />
	</form>

	<script type="text/javascript" language="javascript">
	// <![CDATA[
				
		function modificarVisibilidad() {
			var isResponsableIntern = document.getElementById("isResponsableIntern");
			if (isResponsableIntern != null && isResponsableIntern.checked){
				document.getElementById("responsableInternX").style.display = "block";
				document.getElementById("empresaExternaX").style.display = "none";
				//document.getElementById("campo_responsableExtern").value = "";
			} else {
				document.getElementById("responsableInternX").style.display = "none";
				document.getElementById("empresaExternaX").style.display = "block";
				//document.getElementById("campo_responsableInternId").value = "";
			}
						
		}
				
	// ]]>
	</script>
</body>
</html>