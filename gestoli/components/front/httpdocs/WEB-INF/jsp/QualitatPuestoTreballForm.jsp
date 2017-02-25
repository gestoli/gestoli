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
		</c:choose> <fmt:message key="analiticaParametreTipus.tipusdemant" />
	</title>

	<script type="text/javascript" src="/gestoli/dwr/engine.js"></script>
	<script type="text/javascript" src="/gestoli/dwr/util.js"> </script>
	<script type="text/javascript" src="/gestoli/dwr/interface/qualitatService.js"></script>
		
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

	<script type="text/javascript" language="javascript">
		// <![CDATA[
		function carregarSuperiors(superiors){
			DWRUtil.removeAllOptions("campo_idCarrecSuperior");
			DWRUtil.addOptions("campo_idCarrecSuperior", superiors, 'id', 'nom');
		}

		function canviNivell() {
			qualitatService.getCarrecsSuperiors(
					document.getElementById("idEstabliment").value,
					document.getElementById("campo_nivellJerarquic").value,
					carregarSuperiors);
		}

					
				
		// ]]>
	</script>

</head>
<body>

<form id="formulario" name="PuestoTreballForm" action="QualitatPuestoTreballForm.html" method="post" class="extended seguit" onsubmit="">
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
	
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.carrec" />
		<c:param name="title">
			<fmt:message key="qualitat.puestoTreball.camp.carrec" />
		</c:param>
		<c:param name="camp" value="campo_carrec" />
		<c:param name="name" value="carrec" />
		<c:param name="required" value="required" />
		<c:param name="maxlength" value="128" />
		<c:param name="clase" value="campoFormGrande conMargen" />
		<c:param name="disabled" value="${disabled}" />
	</c:import>
	
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="select" />
		<c:param name="path" value="formData.nivellJerarquic" />
		<c:param name="title">
			<fmt:message key="qualitat.puestoTreball.camp.nivellJerarquic" />
		</c:param>
		<c:param name="camp" value="campo_nivellJerarquic" />
		<c:param name="name" value="nivellJerarquic" />
		<c:param name="required" value="required" />
		<c:param name="selectItems" value="nivells" />
		<c:param name="selectItemsId" value="id" />
		<c:param name="selectItemsValue" value="nom" />
		<c:param name="selectSelectedValue" value="${formData.nivellJerarquic}" />
		<c:param name="clase" value="campoFormGenerico80 conMargen" />
		<c:param name="onchange" value="canviNivell()" />
		<c:param name="disabled" value="${disabled}" />
	</c:import>
	
	<div class="separadorH"></div>
	
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="select" />
		<c:param name="path" value="formData.idCarrecSuperior" />
		<c:param name="title">
			<fmt:message key="qualitat.puestoTreball.camp.carrecSuperior" />
		</c:param>
		<c:param name="camp" value="campo_idCarrecSuperior" />
		<c:param name="name" value="idCarrecSuperior" />
		<c:param name="selectItems" value="carrecSuperior" />
		<c:param name="selectItemsId" value="id" />
		<c:param name="selectItemsValue" value="nom" />
		<c:param name="selectSelectedValue" value="${formData.idCarrecSuperior}" />
		<c:param name="clase" value="campoFormGenerico80 conMargen" />
		<c:param name="disabled" value="${disabled}" />
	</c:import>
	
	
	<div class="separadorH"></div>

	<div id="observacionesForm" class="campoForm">	
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="textarea" />
			<c:param name="path" value="formData.funcions" />
			<c:param name="title">
				<fmt:message key="qualitat.puestoTreball.camp.funcions" />
			</c:param>
			<c:param name="camp" value="campo_funcions" />
			<c:param name="name" value="funcions" />
			<c:param name="required" value="required" />
			<c:param name="maxlength" value="128" />
			<c:param name="disabled" value="${disabled}" />
		</c:import>
	</div>

	<div class="separadorH"></div>

	<div id="observacionesForm" class="campoForm">	
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="textarea" />
			<c:param name="path" value="formData.formacio" />
			<c:param name="title">
				<fmt:message key="qualitat.puestoTreball.camp.formacio" />
			</c:param>
			<c:param name="camp" value="campo_formacio" />
			<c:param name="name" value="formacio" />
			<c:param name="required" value="required" />
			<c:param name="maxlength" value="128" />
			<c:param name="disabled" value="${disabled}" />
		</c:import>
	</div>

	<div class="separadorH"></div>

	<div id="observacionesForm" class="campoForm">	
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="textarea" />
			<c:param name="path" value="formData.experiencia" />
			<c:param name="title">
				<fmt:message key="qualitat.puestoTreball.camp.experiencia" />
			</c:param>
			<c:param name="camp" value="campo_experiencia" />
			<c:param name="name" value="experiencia" />
			<c:param name="required" value="required" />
			<c:param name="maxlength" value="128" />
			<c:param name="disabled" value="${disabled}" />
		</c:import>
	</div>

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
			onclick="document.location ='QualitatPuestoTreball.html';"><a
			id="enlaceTornarForm" href="javascript:void(0);"><fmt:message
			key="proces.tornar" /></a></div>

		<c:if test="${not empty esEstAdministrador}">
			<c:if test="${empty formData.id}">
				<div id="cancelarForm" class="btnCorto"
					onmouseover="underline('enlaceCancelarForm')"
					onmouseout="underline('enlaceCancelarForm')"
					onclick="document.location ='QualitatPuestoTreball.html';"><a
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
	<form id="deleteForm" action="QualitatPuestoTreball.html" method="post"
		class="seguit"
		onsubmit="return confirm('<fmt:message key="manteniment.estasegur"/>')">
		<input id="id" name="id" value="<c:out value="${formData.id}"/>" type="hidden" /> 
		<input id="action" name="action" value="delete" type="hidden" />
	</form>
	

</body>
</html>