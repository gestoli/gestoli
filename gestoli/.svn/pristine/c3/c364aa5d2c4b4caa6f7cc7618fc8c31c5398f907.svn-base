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

<form id="formulario" name="DescripcioInstalacioForm" action="QualitatDescripcioInstalacioForm.html" method="post" class="extended seguit" onsubmit="">
	<c:set var="disabled" value="" />
	<c:if test="${empty esEstAdministrador}">
		<c:set var="disabled" value="true" />
	</c:if>
		
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.sales" />
		<c:param name="title">
			<fmt:message key="qualitat.descripcioinstalacio.camp.sales" />
		</c:param>
		<c:param name="camp" value="campo_sales" />
		<c:param name="name" value="sales" />
		<c:param name="required" value="required" />
		<c:param name="maxlength" value="128" />
		<c:param name="clase" value="campoFormMediano conMargen" />
		<c:param name="disabled" value="${disabled}" />
	</c:import>
		<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.plantes" />
		<c:param name="title">
			<fmt:message key="qualitat.descripcioinstalacio.camp.plantes" />
		</c:param>
		<c:param name="camp" value="campo_plantes" />
		<c:param name="name" value="plantes" />
		<c:param name="required" value="required" />
		<c:param name="maxlength" value="128" />
		<c:param name="clase" value="campoFormMediano conMargen" />
		<c:param name="disabled" value="${disabled}" />
	</c:import>
	
	<div class="separadorH"></div>
	
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.ample" />
		<c:param name="title">
			<fmt:message key="qualitat.descripcioinstalacio.camp.amplada" />
		</c:param>
		<c:param name="camp" value="campo_ample" />
		<c:param name="name" value="ample" />
		<c:param name="required" value="required" />
		<c:param name="maxlength" value="128" />
		<c:param name="clase" value="campoFormMediano conMargen" />
		<c:param name="disabled" value="${disabled}" />
	</c:import>
		<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.llarg" />
		<c:param name="title">
			<fmt:message key="qualitat.descripcioinstalacio.camp.llargada" />
		</c:param>
		<c:param name="camp" value="campo_llarg" />
		<c:param name="name" value="llarg" />
		<c:param name="required" value="required" />
		<c:param name="maxlength" value="128" />
		<c:param name="clase" value="campoFormMediano conMargen" />
		<c:param name="disabled" value="${disabled}" />
	</c:import>
		

	<div class="separadorH"></div>
	
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.vestuaris" />
		<c:param name="title">
			<fmt:message key="qualitat.descripcioinstalacio.camp.vestuaris" />
		</c:param>
		<c:param name="camp" value="campo_vestuaris" />
		<c:param name="name" value="vestuaris" />
		<c:param name="required" value="required" />
		<c:param name="maxlength" value="128" />
		<c:param name="clase" value="campoFormMediano conMargen" />
		<c:param name="disabled" value="${disabled}" />
	</c:import>
		<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.banys" />
		<c:param name="title">
			<fmt:message key="qualitat.descripcioinstalacio.camp.banys" />
		</c:param>
		<c:param name="camp" value="campo_banys" />
		<c:param name="name" value="banys" />
		<c:param name="required" value="required" />
		<c:param name="maxlength" value="128" />
		<c:param name="clase" value="campoFormMediano conMargen" />
		<c:param name="disabled" value="${disabled}" />
	</c:import>
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.oficines" />
		<c:param name="title">
			<fmt:message key="qualitat.descripcioinstalacio.camp.oficines" />
		</c:param>
		<c:param name="camp" value="campo_oficines" />
		<c:param name="name" value="oficines" />
		<c:param name="required" value="required" />
		<c:param name="maxlength" value="128" />
		<c:param name="clase" value="campoFormMediano conMargen" />
		<c:param name="disabled" value="${disabled}" />
	</c:import>
	
	
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

</body>
</html>
