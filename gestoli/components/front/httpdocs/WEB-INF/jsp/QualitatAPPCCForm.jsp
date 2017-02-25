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

<form id="formulario" name="APPCCForm" action="QualitatAPPCCForm.html" method="post" class="extended seguit" onsubmit="">
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
	
	<c:set var="carrec" value="${formData.carrecsArray}" scope="request" /> 
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="selectMultiple" />
		<c:param name="path" value="formData.carrecsArray" />
		<c:param name="titleIzquierda"
			value="qualitat.appcc.camp.carrecs.disponibles" />
		<c:param name="titleDerecha"
			value="qualitat.appcc.camp.carrecs.seleccionats" />
		<c:param name="camp" value="carrecsArray" />
		<c:param name="clase" value="campoForm" />
		<c:param name="selectTamany" value="5" />
		<c:param name="selectItems" value="carrecsArray" />
		<c:param name="selectItemsId" value="id" />
		<c:param name="selectItemsValue" value="nom" />
		<c:param name="selectSelectedItems" value="carrec" />
		<c:param name="disabled" value="${disabled}" />
	</c:import>
	
	<div class="separadorH"></div>
	
	<c:if test="${not empty formData.id}">
		<div id="productes">
	
			<c:if test="${not empty formData.productes}">
				<div id="listado"><%-- Tabla de resultados --%>
					<div class="layoutSombraTabla ancho">
						<c:if
							test="${not empty formData.productes}">
							<div class="rellenoInf"></div>
							<div class="rellenoIzq"></div>
							<div class="rellenoDer"></div>
							<div class="supDer"></div>
							<div class="supIzq"></div>
							<div class="infIzq"></div>
							<div class="infDer"></div>
						</c:if> 
						<display:table name="formData.productes" id="registre" requestURI="" export="true" sort="list" cellpadding="0" cellspacing="0" class="listadoEstrecho selectable">
							<display:column property="descripcio" titleKey="qualitat.appcc.producte.camp.descripcio" headerClass="ancho100" sortable="true" url="/QualitatAPPCCProducteForm.html" paramId="id" paramProperty="id"/>
							<display:column property="us" titleKey="qualitat.appcc.producte.camp.us" headerClass="ancho100 final" sortable="true"/>
							<display:setProperty name="export.xml" value="false" />
							<display:setProperty name="export.csv" value="false" />
							<display:setProperty name="export.rtf" value="false" />
							<display:setProperty name="export.pdf" value="false" />
							<display:setProperty name="export.excel.include_header" value="true" />
							<display:setProperty name="export.excel.filename" value="qualitatAPPCCProductes.xls" />
							<display:setProperty name="export.decorated" value="true" />
						</display:table>
					</div>
				</div>
				<br/><br/><br/>
			</c:if>

			<div class="separadorH"></div>	
					
			<c:if test="${not empty esEstAdministrador}">
				<div class="btnCorto" onmouseover="underline('enlaceCrearProducteForm')"
					onmouseout="underline('enlaceCrearProducteForm')"
					onclick="document.producteForm.submit();">
					<a id="enlaceCrearProducteForm"	href="javascript:void(0);"><fmt:message key="qualitat.appcc.crearProducte" /></a>
				</div>
			</c:if>
		</div>
	</c:if>
	
	<div class="separadorH"></div>
	<br></br>

	<div class="botonesForm">
		<c:if test="${not empty esEstAdministrador}">
			<c:choose>
				<c:when test="${not empty formData.id}">
					<div id="guardarForm" class="btnCorto"
						onclick="if(confirm('<fmt:message key="manteniment.modificar.confirm"/>')){onSubmitMultiple_carrecsArray();submitForm('formulario')}"
						onmouseover="underline('enlaceGuardarForm')"
						onmouseout="underline('enlaceGuardarForm')"><a
						id="enlaceGuardarForm" href="javascript:void(0);"><fmt:message
						key="manteniment.guardar" /></a></div>
				</c:when>
				<c:otherwise>
					<div id="guardarForm" class="btnCorto"
						onclick="onSubmitMultiple_carrecsArray();submitForm('formulario')"
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
			onclick="document.location ='Inici.html';"><a
			id="enlaceTornarForm" href="javascript:void(0);"><fmt:message
			key="proces.tornar" /></a></div>
			
		<c:if test="${not empty esEstAdministrador}">
			<c:if test="${empty formData.id}">
				<div id="cancelarForm" class="btnCorto"
					onmouseover="underline('enlaceCancelarForm')"
					onmouseout="underline('enlaceCancelarForm')"
					onclick="document.location ='Inici.html';"><a
					id="enlaceCancelarForm" href="javascript:void(0);"><fmt:message
					key="proces.cancelar" /></a></div>
			</c:if>
		</c:if>
	</div>
	
	</form>
	<form name="producteForm" action="QualitatAPPCCProducteForm.html">
		<input id="idAPPCC" name="idAPPCC" value="<c:out value="${formData.id}"/>" type="hidden" />
	</form> 

	<c:if test="${not empty formData.productes}">
		<%-- Colores en tablas --%>
		<script type="text/javascript">
			$(document).ready(function(){
				setEstilosTabla();
			})
		</script>
	</c:if>

</body>
</html>