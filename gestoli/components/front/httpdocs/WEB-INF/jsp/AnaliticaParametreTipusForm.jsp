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


<script type="text/javascript" src="js/form.js"></script>


</head>
<body>

<form id="formulario" name="analiticaForm" action="AnaliticaParametreTipusForm.html" method="post" class="extended seguit" onsubmit="">
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="hidden" />
		<c:param name="path" value="formData.id" />
		<c:param name="camp" value="id" />
	</c:import>
	<div>

	<!-- NOM CATALÀ-->
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.nom" />
		<c:param name="title">
			<fmt:message key="analiticaParametreTipus.camp.nomCatala" />
		</c:param>
		<c:param name="camp" value="campo_nom" />
		<c:param name="name" value="nom" />
		<c:param name="required" value="required" />
		<c:param name="maxlength" value="128" />
		<c:param name="clase" value="campoFormGrande conMargen" />
	</c:import>
	
	<div class="separadorH"></div>
	
	<!-- NOM CASTELLÀ -->
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.nomCast" />
		<c:param name="title">
			<fmt:message key="analiticaParametreTipus.camp.nomCastella" />
		</c:param>
		<c:param name="camp" value="campo_nomCast" />
		<c:param name="name" value="nomCast" />
		<c:param name="required" value="required" />
		<c:param name="maxlength" value="128" />
		<c:param name="clase" value="campoFormGrande conMargen" />
	</c:import>
	
	<div class="separadorH"></div>
	
	<!-- ORDRE -->
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.ordre" />
		<c:param name="title">
			<fmt:message key="analiticaParametreTipus.camp.ordre" />
		</c:param>
		<c:param name="camp" value="campo_ordre" />
		<c:param name="name" value="ordre" />
		<c:param name="required" value="required" />
		<c:param name="maxlength" value="128" />
		<c:param name="clase" value="campoFormGrande conMargen" />
	</c:import>
	
	<div class="separadorH"></div>
	
	<!-- TIPUS -->
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="select" />
		<c:param name="path" value="formData.tipus" />
		<c:param name="required" value="required" />
		<c:param name="title">
			<fmt:message key="analiticaParametreTipus.camp.tipus" />
		</c:param>
		<c:param name="camp" value="campo_tipus" />
		<c:param name="name" value="tipus" />
		<c:param name="selectItems" value="tipus" />
		<c:param name="selectItemsId" value="id" />
		<c:param name="selectItemsValue" value="nom" />
		<c:param name="selectSelectedValue" value="${formData.tipus}" />
		<c:param name="clase" value="campoFormMediano conMargen" />
	</c:import>
	
	<!-- TIPUS CONTROL-->
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="select" />
		<c:param name="path" value="formData.tipusControl" />
		<c:param name="required" value="required" />
		<c:param name="title">
			<fmt:message key="analiticaParametreTipus.camp.tipusControl" />
		</c:param>
		<c:param name="camp" value="campo_tipusControl" />
		<c:param name="name" value="tipusControl" />
		<c:param name="selectItems" value="tipusControl" />
		<c:param name="selectItemsId" value="id" />
		<c:param name="selectItemsValue" value="nom" />
		<c:param name="selectSelectedValue" value="${formData.tipusControl}" />
		<c:param name="clase" value="campoFormMediano conMargen" />
	</c:import>
	
	<div class="separadorH"></div>
	
	<!-- ACTIU -->
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="checkbox" />
		<c:param name="path" value="formData.actiu" />
		<c:param name="title">
			<fmt:message key="analiticaParametreTipus.camp.actiu" />
		</c:param>
		<c:param name="camp" value="actiu" />
	</c:import>
	
	<div class="separadorH"></div>

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
		onclick="document.location ='AnaliticaParametreTipus.html';"><a
		id="enlaceTornarForm" href="javascript:void(0);"><fmt:message
		key="proces.tornar" /></a></div>

	<c:if test="${empty formData.id}">
		<div id="cancelarForm" class="btnCorto"
			onmouseover="underline('enlaceCancelarForm')"
			onmouseout="underline('enlaceCancelarForm')"
			onclick="document.location ='AnaliticaParametreTipus.html';"><a
			id="enlaceCancelarForm" href="javascript:void(0);"><fmt:message
			key="proces.cancelar" /></a></div>
	</c:if>
	
	</div>

	</form>

</body>
</html>
