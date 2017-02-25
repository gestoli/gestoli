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
		<title><fmt:message key="devolucio.title" /></title>
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
		<div>
			<form id="formulario" action="DevolucioForm.html" method="post" class="extended seguit">
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="hidden" />
					<c:param name="path" value="formData.idSortida" />
					<c:param name="camp" value="idSortida" />
				</c:import>
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="hidden" />
					<c:param name="path" value="formData.idLot" />
					<c:param name="camp" value="idLot" />
				</c:import>
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="hidden" />
					<c:param name="path" value="formData.totalBotelles" />
					<c:param name="camp" value="totalBotelles" />
				</c:import>
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="hidden" />
					<c:param name="path" value="formData.botellesTornades" />
					<c:param name="camp" value="botellesTornades" />
				</c:import>
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="calendar" />
					<c:param name="path" value="formData.data" />
					<c:param name="required" value="required" />
					<c:param name="title"><fmt:message key="consulta.camp.data"/></c:param>
					<c:param name="camp" value="data" />
					<c:param name="maxlength" value="10" />
					<c:param name="aclaracio"><fmt:message key="consulta.aclaracio.formatData"/></c:param>
					<c:param name="clase" value="conMargen campoForm165" />
				</c:import>
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="text" />
					<c:param name="path" value="formData.devolucioBotelles" />
					<c:param name="title"><fmt:message key="devolucio.camp.botelles" /></c:param>
					<c:param name="camp" value="devolucioBotelles" />
					<c:param name="name" value="devolucioBotelles" />
					<c:param name="maxlength" value="64" />
					<c:param name="clase" value="campoFormMediano margen120" />
				</c:import>
				<div class="separadorH"></div>

				<div class="botonesForm">
					<div id="guardarForm" class="btnCorto"
							onclick="if(confirm('<fmt:message key="manteniment.confirmar"/>')){submitForm('formulario')}"
							onmouseover="underline('enlaceGuardarForm')"
							onmouseout="underline('enlaceGuardarForm')">
						<a id="enlaceGuardarForm" href="javascript:void(0);">
							<fmt:message key="manteniment.aceptar" />
						</a>
					</div>
				</div>
			</form>
		</div>
	</body>
</html>
