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

	<form id="formulario" name="NoConformitatForm" action="QualitatNoConformitatAccioForm.html" method="post" class="extended seguit" onsubmit="">
	
	
		<input id="paramURL" name="paramURL" value="${url}" type="hidden" />
		<input id="paramParams" name="paramParams" value="${params}" type="hidden" />
	
		<c:set var="disabled" value="" />
		<c:if test="${empty esEstAdministrador && empty esEstEncarregat && empty esEstTreballador}">
			<c:set var="disabled" value="true" />
		</c:if>
	
		<c:if test="${not empty formData.id}">
			<c:import url="comu/CampFormulari.jsp">
				<c:param name="tipus" value="hidden" />
				<c:param name="path" value="formData.id" />
				<c:param name="camp" value="id" />
			</c:import>
		</c:if> 
		<c:if test="${not empty formData.idNoConformitat}">
			<c:import url="comu/CampFormulari.jsp">
				<c:param name="tipus" value="hidden" />
				<c:param name="path" value="formData.idNoConformitat" />
				<c:param name="camp" value="idNoConformitat" />
				<c:param name="value" value="${formData.idNoConformitat}" />
			</c:import> 
		</c:if>
		
		<div class="separadorH"></div>
		
		<c:if test="${not empty formData.id}">
			<c:import url="comu/CampFormulari.jsp">
				<c:param name="tipus" value="text" />
				<c:param name="path" value="formData.codi" />
				<c:param name="title">
					<fmt:message key="qualitat.noConformitat.accio.camp.id" />
				</c:param>
				<c:param name="camp" value="campo_codi" />
				<c:param name="name" value="codi" />
				<c:param name="clase" value="campoFormNormal" />
				<c:param name="disabled" value="true" />
			</c:import>
		</c:if> 
			
		<div class="separadorH"></div>
		
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="text" />
			<c:param name="path" value="formData.accio" />
			<c:param name="title">
				<fmt:message key="qualitat.noConformitat.accio.camp.accio" />
			</c:param>
			<c:param name="camp" value="campo_accio" />
			<c:param name="name" value="accio" />
			<c:param name="required" value="required" />
			<c:param name="maxlength" value="255" />
			<c:param name="clase" value="campoFormCompleto" />
			<c:param name="disabled" value="${disabled}" />
		</c:import>
	
		<div class="separadorH"></div>
			
		<br></br>
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="select" />
			<c:param name="path" value="formData.idResponsableAccio" />
			<c:param name="title">
				<fmt:message key="qualitat.noConformitat.accio.camp.responsableAccio" />
			</c:param>
			<c:param name="camp" value="campo_idRresponsableAccio" />
			<c:param name="name" value="idResponsableAccio" />
			<c:param name="required" value="required" />
			<c:param name="selectItems" value="personal" />
			<c:param name="selectItemsId" value="id" />
			<c:param name="selectItemsValue" value="nom" />
			<c:param name="selectSelectedValue" value="${formData.idResponsableAccio}" />
			<c:param name="clase" value="campoFormGrande conMargen" />
			<c:param name="disabled" value="${disabled}" />
		</c:import>
	
		<div class="separadorH"></div>
	
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="calendar" />
			<c:param name="path" value="formData.dataFiPrevista" />
			<c:param name="title">
				<fmt:message key="qualitat.noConformitat.accio.camp.dataFiPrevista" />
			</c:param>
			<c:param name="camp" value="campo_dataFiPrevista" />
			<c:param name="name" value="dataFiPrevista" />
			<c:param name="required" value="required" />
			<c:param name="maxlength" value="10" />
			<c:param name="aclaracio">
				<fmt:message key="proces.aclaracio.formatdata" />
			</c:param>
			<c:param name="clase" value="campoForm165 conMargen" />
			<c:param name="disabled" value="${disabled}" />
		</c:import>
		
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="calendar" />
			<c:param name="path" value="formData.dataTancament" />
			<c:param name="title">
				<fmt:message key="qualitat.noConformitat.accio.camp.dataTancament" />
			</c:param>
			<c:param name="camp" value="campo_dataTancament" />
			<c:param name="name" value="dataTancament" />
			<c:param name="required" value="required" />
			<c:param name="maxlength" value="10" />
			<c:param name="aclaracio">
				<fmt:message key="proces.aclaracio.formatdata" />
			</c:param>
			<c:param name="clase" value="campoForm165" />
			<c:param name="disabled" value="${disabled}" />
		</c:import>
		
		<div class="separadorH"></div>
						
		<br></br>
	
		<div class="botonesForm">
			<c:if test="${not empty esEstAdministrador || not empty esEstEncarregat || not empty esEstTreballador}">
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
				onclick="tornar();"><a
				id="enlaceTornarForm" href="javascript:void(0);"><fmt:message
				key="proces.tornar" /></a>
			</div>
		
			<c:if test="${not empty esEstAdministrador || not empty esEstEncarregat || not empty esEstTreballador}">
				<c:if test="${empty formData.id}">
					<div id="cancelarForm" class="btnCorto"
						onmouseover="underline('enlaceCancelarForm')"
						onmouseout="underline('enlaceCancelarForm')"
						onclick="tornar();"><a
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
	
	<form id="deleteForm" action="QualitatNoConformitatAccio.html" method="post"
		class="seguit"
		onsubmit="return confirm('<fmt:message key="manteniment.estasegur"/>')">
		<input id="id" name="id" value="<c:out value="${formData.id}"/>" type="hidden" /> 
		<input id="idNoConformitat" name="idNoConformitat" value="<c:out value="${formData.idNoConformitat}"/>" type="hidden" />
		<input id="action" name="action" value="delete" type="hidden" />
		<input id="url" name="url" value="${url}" type="hidden" /> 
		<input id="params" name="params" value="${params}" type="hidden" />
	</form>
	
	<form id="tornarForm" action="QualitatNoConformitatForm.html" method="get" class="mini">
		<input id="idNoConformitat" name="id" value="<c:out value="${formData.idNoConformitat}"/>" type="hidden" />
		<input id="url" name="url" value="${url}" type="hidden" /> 
		<input id="params" name="params" value="${params}" type="hidden" />
	</form>
	
	<script type="text/javascript" language="javascript">
	// <![CDATA[

		function tornar() {
			//alert(window.history.length);
			var url = document.getElementById("paramURL").value;
			var params = document.getElementById("paramParams").value;
			var idNoConformitat = document.getElementById("idNoConformitat").value;


			document.getElementById("tornarForm").submit();

			//document.location ='QualitatNoConformitatForm.html?id='+idNoConformitat+'&url='+url+'&params='+params;
			
			/*if (url != ""){
				if (url == "plaManteniment"){
					document.location ='QualitatPlaMantenimentControlVerificacioForm.html?'+params;
				}
			}*/
/*			var ref = document.referrer.split("?");
			var url = document.URL.split("?");
			if (ref[0] != url[0]){
				window.history.go(-1);
			} else {
				window.history.go(-2);
			}*/
		}
				
	// ]]>
	</script>
	
</body>
</html>