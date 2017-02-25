<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>

<%@ page import="es.caib.gestoli.front.util.*"%>
<%@ page import="java.util.ResourceBundle"%>
<%@ page import="java.util.Locale"%>

<%
	String lang = Idioma.getLang(request);
	request.setAttribute("lang", lang);
%>

<html>
	<head>
		<title>
			<c:choose>
				<c:when test="${not empty formData.id}"><fmt:message key="manteniment.modificacio" /></c:when>
				<c:otherwise><fmt:message key="manteniment.alta" /></c:otherwise>
			</c:choose>
			<fmt:message key="sortida.orujo.tipusdemant" />
		</title>
		
		<script type="text/javascript" src="js/form.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/calendar/calendar-setup.js"></script>
<%		if (lang.equalsIgnoreCase("ca")) { %>
			<script type="text/javascript" src="js/calendar/lang/calendar-ca.js"></script>
			<script type="text/javascript" src="js/calendar/lang/calendar-es.js"></script>
<%		} else { %>
			<script type="text/javascript" src="js/calendar/lang/calendar-es.js"></script>
<%		} %>
		<link href="js/calendar/calendar-viti.css" rel="stylesheet"	type="text/css"/>
	</head>

	<body>
		<div>
			<form id="formulario" action="SortidaOrujoForm.html" method="post" class="extended seguit">
				<c:if test="${not empty formData.id}">
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="hidden" />
						<c:param name="path" value="formData.id" />
						<c:param name="camp" value="id" />
					</c:import>
				</c:if>
				
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="calendar" />
					<c:param name="path" value="formData.data" />
					<c:param name="title"><fmt:message key="sortida.orujo.camp.data" /></c:param>
					<c:param name="camp" value="campo_data" />
					<c:param name="name" value="data" />
					<c:param name="maxlength" value="10" />
					<c:param name="required" value="required" />
					<c:param name="aclaracio"><fmt:message key="proces.aclaracio.formatdata" /></c:param>
					<c:param name="clase" value="conMargen campoForm165" />
				</c:import>
				
				<div class="separadorH"></div>
				
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="text" />
					<c:param name="path" value="formData.kilos" />
					<c:param name="maxlength" value="128" />
					<c:param name="title"><fmt:message key="sortida.orujo.camp.kilos" /></c:param>
					<c:param name="camp" value="kilos" />
					<c:param name="clase" value="campoFormGrande conMargen" />
				</c:import>
				<div class="separadorH"></div>
				
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="text" />
					<c:param name="path" value="formData.albara" />
					<c:param name="maxlength" value="128" />
					<c:param name="title"><fmt:message key="sortida.orujo.camp.albara" /></c:param>
					<c:param name="camp" value="albara" />
					<c:param name="clase" value="campoFormGrande conMargen" />
				</c:import>
				<div class="separadorH"></div>
				
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="text" />
					<c:param name="path" value="formData.desti" />
					<c:param name="maxlength" value="128" />
					<c:param name="title"><fmt:message key="sortida.orujo.camp.desti" /></c:param>
					<c:param name="camp" value="desti" />
					<c:param name="clase" value="campoFormGrande conMargen" />
				</c:import>
				<div class="separadorH"></div>
				
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="text" />
					<c:param name="path" value="formData.document" />
					<c:param name="maxlength" value="128" />
					<c:param name="title"><fmt:message key="sortida.orujo.camp.document" /></c:param>
					<c:param name="camp" value="document" />
					<c:param name="clase" value="campoFormGrande conMargen" />
				</c:import>
				<div class="separadorH"></div>

				<div class="botonesForm">
					<c:if test="${empty formData.id}">
						<div id="guardarForm" class="btnCorto"
								onclick="submitForm('formulario')"
								onmouseover="underline('enlaceGuardarForm')"
								onmouseout="underline('enlaceGuardarForm')">
							<a id="enlaceGuardarForm" href="javascript:void(0);">
								<fmt:message key="manteniment.aceptar" />
							</a>
						</div>
					</c:if>
					<div id="cancelarForm" class="btnCorto"
							onmouseover="underline('enlaceCancelarForm')"
							onmouseout="underline('enlaceCancelarForm')"
							onclick="document.location ='SortidaOrujo.html';">
						<a id="enlaceCancelarForm" href="javascript:void(0);">
							<fmt:message key="manteniment.tornar" />
						</a>
					</div>
				</div>
			</form>
		</div>
	</body>
</html>
