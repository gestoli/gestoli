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
<title><fmt:message key="proces.desqualificar.titol" /></title>
<script type="text/javascript" src="dwr/interface/contenidorService.js"></script>
<script type="text/javascript" src="dwr/interface/processosService.js"></script>
<script type="text/javascript" src="dwr/engine.js"></script>
<script type="text/javascript" src="dwr/util.js"> </script>

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


</head>
<body>

<form id="formulario" name="procesDesqualificarForm" action="ProcesDesqualificarForm.html" method="post" class="extended seguit" onsubmit="">
<c:import url="comu/CampFormulari.jsp">
	<c:param name="tipus" value="calendar" />
	<c:param name="path" value="formData.data" />
	<c:param name="title">
		<fmt:message key="proces.trasbals.camp.data" />
	</c:param>
	<c:param name="camp" value="campo_data" />
	<c:param name="name" value="data" />
	<c:param name="maxlength" value="10" />
	<c:param name="required" value="required" />
	<c:param name="aclaracio">
		<fmt:message key="proces.aclaracio.formatdata" />
	</c:param>
	<c:param name="clase" value="conMargen campoForm165" />
</c:import>

<div class="separadorH"></div>


<c:import url="comu/CampFormulari.jsp">
	<c:param name="tipus" value="hidden" />
	<c:param name="path" value="formData.diposit.id" />
	<c:param name="camp" value="diposit.id" />
	<c:param name="name" value="diposit.id" />
	<c:param name="value" value="${deposito.id}" />
</c:import>


<div class="separadorH"></div>

<div id="observacionesForm"
	class="campoForm <c:out value="${param.required}"/><c:if test="${not empty status.errorMessage}"> error</c:if>">
<c:import url="comu/CampFormulari.jsp">
	<c:param name="tipus" value="textarea" />
	<c:param name="path" value="formData.observaciones" />
	<c:param name="title">
		<fmt:message key="proces.desqualificar.camp.observaciones" />
	</c:param>
	<c:param name="camp" value="observaciones" />
</c:import></div>

<div class="separadorH"></div>

<div class="botonesForm">
<div id="guardarForm" class="btnCorto"
	onclick="if(confirm('<fmt:message key="manteniment.confirmar"/>')){submitForm('formulario')}"
	onmouseover="underline('enlaceGuardarForm')"
	onmouseout="underline('enlaceGuardarForm')"><a
	id="enlaceGuardarForm" href="javascript:void(0);"><fmt:message
	key="manteniment.aceptar" /></a></div>

<div class="btnCorto" onclick="submitForm('tornarForm')"
	onmouseover="underline('enlaceTornarForm')"
	onmouseout="underline('enlaceTornarForm')"><a
	id="enlaceTornarForm" href="javascript:void(0);"><fmt:message
	key="proces.tornar" /></a></div>

<div id="cancelarForm" class="btnCorto"
	onmouseover="underline('enlaceCancelarForm')"
	onmouseout="underline('enlaceCancelarForm')"
	onclick="document.location ='ProcesInici.html';"><a
	id="enlaceCancelarForm" href="javascript:void(0);"><fmt:message
	key="proces.cancelar" /></a></div>
</div>

</form>

<form id="tornarForm" action="ProcesInici.html" class="seguit"><input
	type="hidden" id="tipusProces" name="tipusProces" value="6"> <input
	type="hidden" id="pas" name="pas" value="0"></form>

</body>
</html>