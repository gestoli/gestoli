<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ page import="es.caib.gestoli.front.util.*"%>
<%@ page import="java.util.ResourceBundle"%>
<%@ page import="java.util.Locale"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>
<%

	String lang = Idioma.getLang(request);
	request.setAttribute("lang",lang);
%>

<html>
<head>

	<script type="text/javascript">
	
		window.onload = function() {
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
<script type="text/javascript" src="js/displaytag.js"></script>

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

	<form id="formulario" name="ProducteForm" action="QualitatAPPCCProducteForm.html" method="post" class="extended seguit" onsubmit="">
		<c:set var="disabled" value="" />
		<c:if test="${empty esEstAdministrador}">
			<c:set var="disabled" value="true" />
		</c:if>
	
		<c:if test="${not empty idEstabliment}">
			<c:import url="comu/CampFormulari.jsp">
				<c:param name="tipus" value="hidden" />
				<c:param name="path" value="idEstabliment" />
				<c:param name="camp" value="idEstabliment" />
				<c:param name="value" value="${idEstabliment}" />
			</c:import>
		</c:if> 
		<c:if test="${not empty formData.id}">
			<c:import url="comu/CampFormulari.jsp">
				<c:param name="tipus" value="hidden" />
				<c:param name="path" value="formData.id" />
				<c:param name="camp" value="id" />
			</c:import>
		</c:if> 
		<c:if test="${not empty formData.idAPPCC}">
			<c:import url="comu/CampFormulari.jsp">
				<c:param name="tipus" value="hidden" />
				<c:param name="path" value="formData.idAPPCC" />
				<c:param name="camp" value="idAPPCC" />
				<c:param name="value" value="${formData.idAPPCC}" />
			</c:import> 
		</c:if>
		
		<div class="separadorH"></div>
			
		<div id="observacionesForm" class="campoForm">	
			<c:import url="comu/CampFormulari.jsp">
				<c:param name="tipus" value="textareaMedio" />
				<c:param name="path" value="formData.descripcio" />
				<c:param name="title">
					<fmt:message key="qualitat.appcc.producte.camp.descripcio" />
				</c:param>
				<c:param name="camp" value="campo_descripcio" />
				<c:param name="name" value="descripcio" />
				<c:param name="required" value="required" />
				<c:param name="maxlength" value="500" />
				<c:param name="disabled" value="${disabled}" />
			</c:import>
		</div> 
			
		<div class="separadorH"></div>
			
		<div id="observacionesForm" class="campoForm">	
			<c:import url="comu/CampFormulari.jsp">
				<c:param name="tipus" value="textareaMedio" />
				<c:param name="path" value="formData.us" />
				<c:param name="title">
					<fmt:message key="qualitat.appcc.producte.camp.us" />
				</c:param>
				<c:param name="camp" value="campo_us" />
				<c:param name="name" value="us" />
				<c:param name="required" value="required" />
				<c:param name="maxlength" value="500" />
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
				onclick="document.location ='QualitatAPPCCForm.html';"><a
				id="enlaceTornarForm" href="javascript:void(0);"><fmt:message
				key="proces.tornar" /></a></div>
		
			<c:if test="${not empty esEstAdministrador}">
				<c:if test="${empty formData.id}">
					<div id="cancelarForm" class="btnCorto"
						onmouseover="underline('enlaceCancelarForm')"
						onmouseout="underline('enlaceCancelarForm')"
						onclick="document.location ='QualitatAPPCCForm.html';"><a
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
	
	<form id="deleteForm" action="QualitatAPPCCProducte.html" method="post"
		class="seguit"
		onsubmit="return confirm('<fmt:message key="manteniment.estasegur"/>')">
		<input id="id" name="id" value="<c:out value="${formData.id}"/>" type="hidden" /> 
		<input id="idAPPCC" name="idAPPCC" value="<c:out value="${formData.idAPPCC}"/>" type="hidden" />
		<input id="action" name="action" value="delete" type="hidden" />
	</form>
	
</body>
</html>