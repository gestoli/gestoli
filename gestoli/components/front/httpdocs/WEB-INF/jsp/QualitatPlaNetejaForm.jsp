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

<link href="js/calendar/calendar-viti.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="js/form.js"></script>


</head>
<body>

<form id="formulario" name="QualitatPlaNetejaForm" action="QualitatPlaNetejaForm.html" method="post" class="extended seguit" onsubmit="">
	<c:set var="disabled" value="" />
	<c:if test="${empty esEstAdministrador}">
		<c:set var="disabled" value="true" />
	</c:if>
	
	<c:if test="${not empty formData.codi}">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="hidden" />
			<c:param name="path" value="formData.codi" />
			<c:param name="camp" value="codi" />
		</c:import>
	</c:if> 
	
	<div class="off" id="isEquipX">
	<div class="separadorH"></div>
	<div class="etiqueta conMargen <c:if test="${not empty status.errorMessage}"> error</c:if>">
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="checkbox" />
		<c:param name="path" value="formData.esGeneral" />
		<c:param name="title">
			<fmt:message key="qualitat.plaNeteja.camp.esGeneral" />
		</c:param>
		<c:param name="camp" value="esGeneral" />
		<c:param name="name" value="esGeneral" />
		<c:param name="required" value="required" />
		<c:param name="onclick" value="modificarVisibilidad()" />
		<c:param name="disabled" value="${disabled}" />
	</c:import></div>
	</div>
	
	<div class="separadorH"></div>
	
	<div id="equipX">
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="select" />
		<c:param name="path" value="formData.equipId" />
		<c:param name="title">
			<fmt:message key="qualitat.plaManteniment.camp.equip" />
		</c:param>
		<c:param name="camp" value="campo_equipId" />
		<c:param name="name" value="equipId" />
		<c:param name="required" value="required" />
		<c:param name="selectItems" value="equips" />
		<c:param name="selectItemsId" value="id" />
		<c:param name="selectItemsValue" value="nom" />
		<c:param name="selectSelectedValue" value="${formData.equipId}" />
		<c:param name="clase" value="campoFormGrande conMargen" />
		<c:param name="disabled" value="${disabled}" />
	</c:import>
	</div>
	
	<div class="separadorH"></div>
	
	<div id="elementPlantaX">
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="select" />
		<c:param name="path" value="formData.elementPlanta" />
		<c:param name="title">
			<fmt:message key="qualitat.plaNeteja.camp.element" />
		</c:param>
		<c:param name="camp" value="campo_elementPlanta" />
		<c:param name="name" value="elementPlanta" />
		<c:param name="required" value="required" />
		<c:param name="selectItems" value="elementsPlanta" />
		<c:param name="selectItemsId" value="nom" />
		<c:param name="selectItemsValue" value="nom" />
		<c:param name="selectSelectedValue" value="${formData.elementPlanta}" />
		<c:param name="clase" value="campoFormGrande conMargen" />
		<c:param name="disabled" value="${disabled}" />
	</c:import>
	</div>
	
	
	<div class="separadorH"></div>
	
	<div id="observacionesForm" class="campoForm">	
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="textarea" />
			<c:param name="path" value="formData.accio" />
			<c:param name="title">
				<fmt:message key="qualitat.plaNeteja.camp.accio" />
			</c:param>
			<c:param name="camp" value="campo_accio" />
			<c:param name="name" value="accio" />
			<c:param name="required" value="required" />
			<c:param name="maxlength" value="5000" />
			<c:param name="disabled" value="${disabled}" />
		</c:import>
	</div>
	
	<div class="separadorH"></div>
	
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="select" />
		<c:param name="path" value="formData.producteId" />
		<c:param name="title">
			<fmt:message key="qualitat.plaNeteja.camp.producte" />
		</c:param>
		<c:param name="camp" value="campo_producteId" />
		<c:param name="name" value="producteId" />
		<c:param name="required" value="required" />
		<c:param name="selectItems" value="productes" />
		<c:param name="selectItemsId" value="codi" />
		<c:param name="selectItemsValue" value="tipusSubministre" />
		<c:param name="selectSelectedValue" value="${formData.producteId}" />
		<c:param name="clase" value="campoFormGrande conMargen" />
		<c:param name="disabled" value="${disabled}" />
	</c:import>
	
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.dosis" />
		<c:param name="title">
			<fmt:message key="qualitat.plaNeteja.camp.dosis" />
		</c:param>
		<c:param name="camp" value="campo_dosis" />
		<c:param name="name" value="dosis" />
		<c:param name="required" value="required" />
		<c:param name="maxlength" value="128" />
		<c:param name="clase" value="campoFormGrande" />
		<c:param name="disabled" value="${disabled}" />
	</c:import>
	
	<div class="separadorH"></div>
	
	<div id="frecuenciaX">
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="select" />
		<c:param name="path" value="formData.frequencia" />
		<c:param name="title">
			<fmt:message key="qualitat.plaNeteja.camp.frequencia" />
		</c:param>
		<c:param name="camp" value="campo_frequencia" />
		<c:param name="name" value="frequencia" />
		<c:param name="required" value="required" />
		<c:param name="selectItems" value="frequencia" />
		<c:param name="selectItemsId" value="id" />
		<c:param name="selectItemsValue" value="nom" />
		<c:param name="selectSelectedValue" value="${formData.frequencia}" />
		<c:param name="clase" value="campoFormGenerico80" />
		<c:param name="disabled" value="${disabled}" />
	</c:import>
	</div>
	
	<div class="separadorH"></div>
	
	<div id="responsable">
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="select" />
		<c:param name="path" value="formData.responsableId" />
		<c:param name="title">
			<fmt:message key="qualitat.plaNeteja.camp.responsable" />
		</c:param>
		<c:param name="camp" value="campo_responsableId" />
		<c:param name="name" value="responsableId" />
		<c:param name="required" value="required" />
		<c:param name="selectItems" value="personal" />
		<c:param name="selectItemsId" value="id" />
		<c:param name="selectItemsValue" value="nom" />
		<c:param name="selectSelectedValue" value="${formData.responsableId}" />
		<c:param name="clase" value="campoFormGrande conMargen" />
		<c:param name="disabled" value="${disabled}" />
	</c:import>
	</div>
	
	<div class="separadorH"></div>
	
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="select" />
		<c:param name="path" value="formData.periodicitatVerificacio" />
		<c:param name="title">
			<fmt:message key="qualitat.plaNeteja.camp.periodicitat" />
		</c:param>
		<c:param name="camp" value="campo_periodicitatVerificacio" />
		<c:param name="name" value="periodicitatVerificacio" />
		<c:param name="required" value="required" />
		<c:param name="selectItems" value="frequencia" />
		<c:param name="selectItemsId" value="id" />
		<c:param name="selectItemsValue" value="nom" />
		<c:param name="selectSelectedValue" value="${formData.periodicitatVerificacio}" />
		<c:param name="clase" value="campoFormGenerico80" />
		<c:param name="disabled" value="${disabled}" />
	</c:import>

	<div class="separadorH"></div>
		
	<div id="responsable">
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="select" />
		<c:param name="path" value="formData.respVerificacioId" />
		<c:param name="title">
			<fmt:message key="qualitat.plaNeteja.camp.responsable" />
		</c:param>
		<c:param name="camp" value="campo_respVerificacioId" />
		<c:param name="name" value="respVerificacioId" />
		<c:param name="required" value="required" />
		<c:param name="selectItems" value="personal" />
		<c:param name="selectItemsId" value="id" />
		<c:param name="selectItemsValue" value="nom" />
		<c:param name="selectSelectedValue" value="${formData.respVerificacioId}" />
		<c:param name="clase" value="campoFormGrande conMargen" />
		<c:param name="disabled" value="${disabled}" />
	</c:import>
	</div>
	
	<div class="separadorH"></div>
	

	
	<div class="separadorH"></div>
	<br></br>

	<div class="botonesForm">
	
		<c:if test="${not empty esEstAdministrador}">
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
			onclick="document.location ='QualitatPlaNeteja.html';"><a
			id="enlaceTornarForm" href="javascript:void(0);"><fmt:message
			key="proces.tornar" /></a></div>


		<c:if test="${not empty esEstAdministrador}">
			<c:if test="${empty formData.codi}">
				<div id="cancelarForm" class="btnCorto"
					onmouseover="underline('enlaceCancelarForm')"
					onmouseout="underline('enlaceCancelarForm')"
					onclick="document.location ='QualitatPlaNeteja.html';"><a
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
	<form id="deleteForm" action="QualitatPlaNeteja.html" method="post"
		class="seguit"
		onsubmit="return confirm('<fmt:message key="manteniment.estasegur"/>')">
		<input id="codi" name="codi" value="<c:out value="${formData.codi}"/>" type="hidden" /> 
		<input id="action" name="action" value="delete" type="hidden" />
	</form>

	<script type="text/javascript" language="javascript">
	// <![CDATA[
				
		function modificarVisibilidad() {
			var isEquip = document.getElementById("esGeneral");
			if (isEquip != null && isEquip.checked){
				document.getElementById("equipX").style.display = "none";
				document.getElementById("elementPlantaX").style.display = "block";
				document.getElementById("campo_equipId").value = "";
			} else {
				document.getElementById("equipX").style.display = "block";
				document.getElementById("elementPlantaX").style.display = "none";
				document.getElementById("campo_elementPlanta").value = "";
			}
						
		}
				
	// ]]>
	</script>
</body>
</html>