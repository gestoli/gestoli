<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/fn-rt.tld"%>

<%@ page import="es.caib.gestoli.front.util.*"%>
<%@ page import="java.util.ResourceBundle"%>
<%@ page import="java.util.Locale"%>
<%@ page import="java.util.Collection"%>

<%
	String lang = Idioma.getLang(request);
	request.setAttribute("lang",lang);
%>

<html>
	<head>
		<title><fmt:message key="proces.entradaFonoll.titol" /></title>
		
		<link href="css/jquery.autocomplete.css" rel="stylesheet" type="text/css" />
		<link href="js/calendar/calendar-viti.css" rel="stylesheet" type="text/css" />
		<style type="text/css">
			.campoFormAncho { margin: 0 !important; width: 100% !important; }
		</style>

		<script type="text/javascript" src="/gestoli/dwr/engine.js"></script>
		<script type="text/javascript" src="/gestoli/dwr/util.js"> </script>
		<script type="text/javascript" src="/gestoli/dwr/interface/processosService.js"></script>
		<script type="text/javascript" src="/gestoli/dwr/interface/varietatOlivaService.js"></script>
		
		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript" src="js/jquery.autocomplete.js"></script>

		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/calendar/calendar-setup.js"></script>

<%		if (lang.equalsIgnoreCase("ca")) { %>
			<script type="text/javascript" src="js/calendar/lang/calendar-ca.js"></script>
			<script type="text/javascript" src="js/calendar/lang/calendar-es.js"></script>
<%		} else { %>
			<script type="text/javascript" src="js/calendar/lang/calendar-es.js"></script>
<%		} %>

		<script type="text/javascript" src="js/form.js"></script>
		<script type="text/javascript" src="js/funciones.js"></script>

		
	</head>

	<body>
			<form id="formulario" name="procesEntradaOlivaForm" action="ProcesEntradaFonollForm.html" method="post" class="extended seguit">
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="calendar" />
					<c:param name="path" value="formData.dataExecucio" />
					<c:param name="required" value="required" />
					<c:param name="title"><fmt:message key="proces.entradaFonoll.camp.data" /></c:param>
					<c:param name="camp" value="campo_dataExecucio" />
					<c:param name="name" value="dataExecucio" />
					<c:param name="maxlength" value="10" />
					<c:param name="aclaracio"><fmt:message key="proces.aclaracio.formatdata" /></c:param>
					<c:param name="clase" value="conMargen campoForm165" />
				</c:import>
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="text" />
					<c:param name="path" value="formData.hora" />
					<c:param name="required" value="required" />
					<c:param name="title"><fmt:message key="proces.entradaFonoll.camp.hora" /></c:param>
					<c:param name="camp" value="campo_hora" />
					<c:param name="name" value="hora" />
					<c:param name="maxlength" value="5" />
					<c:param name="aclaracio"><fmt:message key="proces.aclaracio.hora" /></c:param>
					<c:param name="clase" value="campoFormMediano" />
				</c:import>
				
				<div id="mensajeError" class="mensajeErrorOff"></div>
				<div class="separadorH"></div>
				
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="text" />
					<c:param name="path" value="formData.codi" />
					<c:param name="required" value="required" />
					<c:param name="title"><fmt:message key="proces.entradaFonoll.camp.codi" /></c:param>
					<c:param name="camp" value="campo_codi" />
					<c:param name="name" value="codi" />
					<c:param name="clase" value="campoFormGrande conMargen" />
				</c:import>
				
				<div class="separadorH"></div>
				
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="text" />
					<c:param name="path" value="formData.titular" />
					<c:param name="required" value="required" />
					<c:param name="title"><fmt:message key="proces.entradaFonoll.camp.titular" /></c:param>
					<c:param name="camp" value="campo_titular" />
					<c:param name="name" value="titular" />
					<c:param name="clase" value="campoFormCompleto conMargen" />
				</c:import>

				<div class="separadorH"></div>

				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="select" />
					<c:param name="path" value="formData.municipiId" />
					<c:param name="required" value="required" />
					<c:param name="title"><fmt:message key="proces.entradaFonoll.camp.municipi" /></c:param>
					<c:param name="camp" value="campo_municipiId" />
					<c:param name="name" value="municipiId" />
					<c:param name="selectItems" value="municipis" />
					<c:param name="selectItemsId" value="id" />
					<c:param name="selectItemsValue" value="nom" />
					<c:param name="selectSelectedValue" value="${formData.municipiId}" />
					<c:param name="clase" value="campoFormGrande conMargen" />
				</c:import>
				

				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="text" />
					<c:param name="path" value="formData.poligon" />
					<c:param name="required" value="required" />
					<c:param name="title"><fmt:message key="proces.entradaFonoll.camp.poligon" /></c:param>
					<c:param name="camp" value="campo_poligon" />
					<c:param name="name" value="poligon" />
					<c:param name="clase" value="campoFormPequeno" />
				</c:import>
				

				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="text" />
					<c:param name="path" value="formData.parcela" />
					<c:param name="required" value="required" />
					<c:param name="title"><fmt:message key="proces.entradaFonoll.camp.parcela" /></c:param>
					<c:param name="camp" value="campo_parcela" />
					<c:param name="name" value="parcela" />
					<c:param name="clase" value="campoFormPequeno" />
				</c:import>
				
				<div class="separadorH"></div>

				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="text" />
					<c:param name="path" value="formData.kgInici" />
					<c:param name="required" value="required" />
					<c:param name="title"><fmt:message key="proces.entradaFonoll.camp.kg" /></c:param>
					<c:param name="camp" value="campo_kgInici" />
					<c:param name="name" value="kgInici" />
					<c:param name="clase" value="campoFormGrande conMargen" />
				</c:import>
				
				<div class="separadorH"></div>
				<div class="botonesForm">
				<div id="guardarForm" class="btnCorto"
						onclick="if(confirm('<fmt:message key="manteniment.confirmar"/>')){submitForm('formulario');}"
						onmouseover="underline('enlaceGuardarForm')"
						onmouseout="underline('enlaceGuardarForm')">
					<a id="enlaceGuardarForm" href="javascript:void(0);">
						<fmt:message key="manteniment.aceptar" />
					</a>
				</div>
			</div>
		</form>
		<div class="separacio"></div>

	</body>
</html>
