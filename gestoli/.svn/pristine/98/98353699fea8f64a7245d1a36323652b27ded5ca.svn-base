<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/fn-rt.tld"%>
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
<title><fmt:message key="pendentAnalitica.titol" /></title>

<script type="text/javascript" src="dwr/interface/contenidorService.js"></script>
<script type="text/javascript" src="dwr/interface/processosService.js"></script>
<script type="text/javascript" src="dwr/engine.js"></script>
<script type="text/javascript" src="dwr/util.js"> </script>
	<script type="text/javascript" src="js/form.js"></script>

</head>
<body>


<form id="formulario" name="PendentAnaliticaForm" action="PendentAnaliticaForm.html" method="post" class="extended seguit" onsubmit="">
	
	<input type="hidden" id="idEstabliment" name="idEstabliment" value="<c:out value="${establiment.id}"/>" /> 
	<input type="hidden" id="idDiposit" name="idDiposit" value="<c:out value="${diposit.id}"/>" />
	
	<!-- INFORMACIÓ GENERAL - NOM ESTABLIMENT -->
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="static" />
		<c:param name="path" value="formData.nomEstabliment" />
		<c:param name="title">
			<fmt:message key="analitica.camp.nomEstabliment" />:
		</c:param>
		<c:param name="value" value="${establiment.nom}" />
		<c:param name="clase" value="campoFormGrande" />
	</c:import>
	
	<div class="separadorH"></div>
	
	<!-- INFORMACIÓ GENERAL - NOM DIPOSIT -->
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="static" />
		<c:param name="path" value="formData.nomDiposit" />
		<c:param name="title">
			<fmt:message key="analitica.camp.nomDiposit" />:
		</c:param>
		<c:param name="value" value="${diposit.codiAssignat}" />
		<c:param name="clase" value="campoFormGrande" />
	</c:import>
	
	<div class="separadorH"></div>

	<!-- INFORMACIÓ GENERAL - NOM PARTIDA -->
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="static" />
		<c:param name="path" value="formData.nomPartidaOli" />
		<c:param name="title">
			<fmt:message key="analitica.camp.nomPartida" />:
		</c:param>
		<c:param name="value" value="${diposit.partidaOli.nom}" />
		<c:param name="clase" value="campoFormGrande" />
	</c:import>
	
	<div class="separadorH"></div>
	

	<div class="botonesForm">
		<div id="guardarForm" class="btnLargo"
			onclick="submitForm('formulario')"
			onmouseover="underline('enlaceGuardarForm')"
			onmouseout="underline('enlaceGuardarForm')"><a
			id="enlaceGuardarForm" href="javascript:void(0);"><fmt:message
			key="pendentAnalitica.precintar" /></a></div>
	</div>

	</form>

</body>
</html>
