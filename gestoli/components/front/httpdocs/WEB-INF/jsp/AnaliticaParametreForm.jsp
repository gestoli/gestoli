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

<form id="formulario" name="analiticaForm" action="AnaliticaParametreForm.html" method="post" class="extended seguit" onsubmit="">
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="hidden" />
		<c:param name="path" value="formData.id" />
		<c:param name="camp" value="id" />
	</c:import>
	<div>

	<!-- PARAMETRE -->
	<c:if test="${not empty lang && lang == 'ca'}">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="select" />
			<c:param name="path" value="formData.idAnaliticaParametreTipus" />
			<c:param name="required" value="required" />
			<c:param name="title">
				<fmt:message key="analiticaParametre.camp.parametre" />
			</c:param>
			<c:param name="camp" value="campo_idAnaliticaParametreTipus" />
			<c:param name="name" value="idAnaliticaParametreTipus" />
			<c:param name="selectItems" value="analiticaParametreTipus" />
			<c:param name="selectItemsId" value="id" />
			<c:param name="selectItemsValue" value="nom" />
			<c:param name="selectSelectedValue" value="${formData.analiticaParametreTipus.id}" />
			<c:param name="clase" value="campoFormMediano conMargen" />
		</c:import>
	</c:if>
	
	<c:if test="${not empty lang && lang == 'es'}">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="select" />
			<c:param name="path" value="formData.idAnaliticaParametreTipus" />
			<c:param name="required" value="required" />
			<c:param name="title">
				<fmt:message key="analiticaParametre.camp.parametre" />
			</c:param>
			<c:param name="camp" value="campo_idAnaliticaParametreTipus" />
			<c:param name="name" value="idAnaliticaParametreTipus" />
			<c:param name="selectItems" value="analiticaParametreTipus" />
			<c:param name="selectItemsId" value="id" />
			<c:param name="selectItemsValue" value="nomCast" />
			<c:param name="selectSelectedValue" value="${formData.analiticaParametreTipus.id}" />
			<c:param name="clase" value="campoFormMediano conMargen" />
		</c:import>
	</c:if>
	
	<!-- OPERADOR -->
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="select" />
		<c:param name="path" value="formData.operador" />
		<c:param name="required" value="required" />
		<c:param name="title">
			<fmt:message key="analiticaParametre.camp.operador" />
		</c:param>
		<c:param name="camp" value="campo_operador" />
		<c:param name="name" value="operador" />
		<c:param name="selectItems" value="operador" />
		<c:param name="selectItemsId" value="id" />
		<c:param name="selectItemsValue" value="nom" />
		<c:param name="selectSelectedValue" value="${formData.operador}" />
		<c:param name="clase" value="campoFormPequeno conMargen" />
	</c:import>
	
	<!-- VALOR -->
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.valor" />
		<c:param name="title">
			<fmt:message key="analiticaParametre.camp.valor" />
		</c:param>
		<c:param name="camp" value="campo_valor" />
		<c:param name="name" value="valor" />
		<c:param name="required" value="required" />
		<c:param name="maxlength" value="128" />
		<c:param name="clase" value="campoFormMediano conMargen" />
	</c:import>
	
	<div class="separadorH"></div>
	
	<!-- OBLIGATORI -->
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="checkbox" />
		<c:param name="path" value="formData.obligatori" />
		<c:param name="title">
			<fmt:message key="analiticaParametre.camp.obligatori" />
		</c:param>
		<c:param name="camp" value="obligatori" />
	</c:import>
	
	<div class="separadorH"></div>
	
	<!-- VARIETAT -->
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="select" />
		<c:param name="path" value="formData.idVarietatOli" />
		<c:param name="required" value="required" />
		<c:param name="title">
			<fmt:message key="analiticaParametre.camp.varietat" />
		</c:param>
		<c:param name="camp" value="campo_idVarietatOli" />
		<c:param name="name" value="idVarietatOli" />
		<c:param name="selectItems" value="varietats" />
		<c:param name="selectItemsId" value="id" />
		<c:param name="selectItemsValue" value="nom" />
		<c:param name="selectSelectedValue" value="${formData.varietatOli.id}" />
		<c:param name="clase" value="campoFormMediano conMargen" />
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
		onclick="document.location ='AnaliticaParametre.html';"><a
		id="enlaceTornarForm" href="javascript:void(0);"><fmt:message
		key="proces.tornar" /></a></div>

	<c:if test="${empty formData.id}">
		<div id="cancelarForm" class="btnCorto"
			onmouseover="underline('enlaceCancelarForm')"
			onmouseout="underline('enlaceCancelarForm')"
			onclick="document.location ='AnaliticaParametre.html';"><a
			id="enlaceCancelarForm" href="javascript:void(0);"><fmt:message
			key="proces.cancelar" /></a></div>
	</c:if>
	
	</div>

	</form>

</body>
</html>
