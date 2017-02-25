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
		<fmt:message key="gestioInfografia.tipusdemant" />
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

<form id="formulario" name="GestioInfografiaForm" action="GestioInfografiaForm.html" method="post" class="extended seguit" onsubmit="" enctype="multipart/form-data">
	<c:if test="${not empty formData.id}">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="hidden" />
			<c:param name="path" value="formData.id" />
			<c:param name="camp" value="id" />
		</c:import>
	</c:if> 
	
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.nom" />
		<c:param name="title">
			<fmt:message key="gestioInfografia.camp.nom.ca" />
		</c:param>
		<c:param name="camp" value="campo_nom" />
		<c:param name="name" value="nom" />
		<c:param name="required" value="required" />
		<c:param name="maxlength" value="128" />
		<c:param name="clase" value="campoFormGrande" />
	</c:import>


	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.nomEs" />
		<c:param name="title">
			<fmt:message key="gestioInfografia.camp.nom.es" />
		</c:param>
		<c:param name="camp" value="campo_nomEs" />
		<c:param name="name" value="nomEs" />
		<c:param name="required" value="required" />
		<c:param name="maxlength" value="128" />
		<c:param name="clase" value="campoFormGrande" />
	</c:import>
	
	<div class="separadorH"></div>
	
	<div id="observacionesForm" class="campoForm">	
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="textarea" />
			<c:param name="path" value="formData.descripcio" />
			<c:param name="title">
				<fmt:message key="gestioInfografia.camp.descripcio.ca" />
			</c:param>
			<c:param name="camp" value="campo_descripcio" />
			<c:param name="name" value="descripcio" />
			<c:param name="required" value="required" />
			<c:param name="maxlength" value="5000" />
		</c:import>
	</div>
	
	<div class="separadorH"></div>
	
	<div id="observacionesForm" class="campoForm">	
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="textarea" />
			<c:param name="path" value="formData.descripcioEs" />
			<c:param name="title">
				<fmt:message key="gestioInfografia.camp.descripcio.es" />
			</c:param>
			<c:param name="camp" value="campo_descripcioEs" />
			<c:param name="name" value="descripcioEs" />
			<c:param name="required" value="required" />
			<c:param name="maxlength" value="5000" />
		</c:import>
	</div>
	
	<div class="separadorH"></div>

	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="hidden" />
		<c:param name="path" value="formData.errorImatges" />
		<c:param name="camp" value="errorImatges" />
	</c:import> 
	<c:set var="arxiuArxiu" value="${formData.arxiuArxiu}" scope="request" /> 
	<c:set var="idArxiu" value="${formData.idArxiu}" scope="request" /> 
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="fileImage" />
		<c:param name="path" value="formData.contingutArxiu" />
		<c:param name="required" value="required" />
		<c:param name="title"><fmt:message key="gestioInfografia.camp.arxiu" /></c:param>
		<c:param name="camp" value="contingutArxiu" />
		<c:param name="arxiu" value="arxiuArxiu" />
		<c:param name="height" value="150" />
		<c:param name="arxiuId" value="idArxiu" />
	</c:import> 
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="hidden" />
		<c:param name="path" value="formData.idArxiu" />
		<c:param name="camp" value="idArxiu" />
	</c:import>


	<c:set var="arxiuImatge" value="${formData.arxiuImatge}" scope="request" /> 
	<c:set var="idImatge" value="${formData.idImatge}" scope="request" /> 
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="fileImage" />
		<c:param name="path" value="formData.contingutImatge" />
		<c:param name="required" value="required" />
		<c:param name="title"><fmt:message key="gestioInfografia.camp.imatge" /></c:param>
		<c:param name="camp" value="contingutImatge" />
		<c:param name="arxiu" value="arxiuImatge" />
		<c:param name="height" value="150" />
		<c:param name="arxiuId" value="idImatge" />
	</c:import> 
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="hidden" />
		<c:param name="path" value="formData.idImatge" />
		<c:param name="camp" value="idImatge" />
	</c:import>

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
			onclick="document.location ='GestioInfografia.html';"><a
			id="enlaceTornarForm" href="javascript:void(0);"><fmt:message
			key="proces.tornar" /></a></div>
	
		<c:if test="${empty formData.id}">
			<div id="cancelarForm" class="btnCorto"
				onmouseover="underline('enlaceCancelarForm')"
				onmouseout="underline('enlaceCancelarForm')"
				onclick="document.location ='GestioInfografia.html';"><a
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

<form id="deleteForm" action="GestioInfografia.html" method="post"
	class="seguit"
	onsubmit="return confirm('<fmt:message key="manteniment.estasegur"/>')">
	<input id="id" name="id" value="<c:out value="${formData.id}"/>" type="hidden" /> 
	<input id="action" name="action" value="delete" type="hidden" />
</form>


</body>
</html>