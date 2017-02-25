<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>


<html>
<head>

<title><fmt:message key="consulta.declaracioMensual.title" /></title>
<%--
    <script type="text/javascript" src="js/form.js"></script>
    <script type="text/javascript" src="js/jscalendar/calendar_stripped.js"></script>
	<script type="text/javascript" src="js/jscalendar/calendar-setup_stripped.js"></script>
	<script type="text/javascript" src="js/jscalendar/lang/<fmt:message key="webapp.calendar"/>"></script>
	<script type="text/javascript" src="js/selectbox.js"></script>
	<link type="text/css" href="js/jscalendar/calendar-viti.css" rel="stylesheet"/>
--%>

<script type="text/javascript" src="js/calendar/calendar.js"></script>
<script type="text/javascript" src="js/calendar/calendar-setup.js"></script>
<script type="text/javascript" src="js/calendar/lang/calendar-es.js"></script>
<link href="js/calendar/calendar-viti.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="js/form.js"></script>
<c:if test="${not empty llistat}">
	<script type="text/javascript" src="js/displaytag.js"></script>
	<%--<link href="css/displaytag.css" rel="stylesheet" type="text/css"/> --%>
</c:if>

</head>
<body>

<c:if test="${(not empty esDoGestor || not empty esAdministracio || not empty esDoControlador ) && empty idEstabliment }">
	<div id="listado"><%-- Tabla de resultados --%>
	<div class="layoutSombraTabla ancho">
	<div class="rellenoInf"></div>
	<div class="rellenoIzq"></div>
	<div class="rellenoDer"></div>
	<div class="supDer"></div>
	<div class="supIzq"></div>
	<div class="infIzq"></div>
	<div class="infDer"></div>
	<display:table name="llistat" requestURI="" id="establiment" sort="list" cellpadding="0" cellspacing="0" class="listadoEstrecho selectable">
		<display:column titleKey="establiment.camp.nom" sortable="true" sortProperty="nom">
			<a href="ConsultaDeclaracioMensualOlivaTaula.html?idEst=<c:out value="${establiment.id}"/>&amp;fromEstabliment=true"><c:out value="${establiment.nom}" /></a>
		</display:column>
		<display:column titleKey="establiment.camp.cif" sortable="true" headerClass="ancho160 final">
			<c:out value="${establiment.cif}" />
		</display:column>
	</display:table></div>
	</div>

	<%-- Colores en tablas --%>
	<script type="text/javascript">
			$(document).ready(function(){
				setEstilosTabla();
			})
		</script>
</c:if>

<c:if test="${not empty idEstabliment }">

	<form id="formulario" action="<%=request.getContextPath()%>/GenerarPdf.html" method="post" class="extended seguit">
		<input type="hidden" id="idEst" name="idEst" value="<c:out value="${idEstabliment}"/>" /> 
		<input type="hidden" id="tipus" name="tipus" value="26" /> 
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="calendar" />
			<c:param name="path" value="formData.dataInici" />
			<c:param name="required" value="required" />
			<c:param name="title">
				<fmt:message key="consulta.camp.dataInici" />
			</c:param>
			<c:param name="camp" value="dataInici" />
			<c:param name="maxlength" value="10" />
			<c:param name="aclaracio">
				<fmt:message key="consulta.aclaracio.formatData" />
			</c:param>
			<%--c:param name="readonly" value="1" /--%>
			<c:param name="clase" value="conMargen campoForm165 " />
		</c:import> 
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="calendar" />
			<c:param name="path" value="formData.dataFi" />
			<c:param name="required" value="required" />
			<c:param name="title"><fmt:message key="consulta.camp.dataFi" /></c:param>
			<c:param name="camp" value="dataFi" />
			<c:param name="maxlength" value="10" />
			<c:param name="aclaracio"><fmt:message key="consulta.aclaracio.formatData" /></c:param>
			<%--c:param name="readonly" value="1" /--%>
			<c:param name="clase" value="campoForm165" />
		</c:import>

	<div class="separadorH"></div>

	<div class="botonesForm">
	<div id="aceptarForm" class="btnCorto" onclick="submitForm('formulario');" onmouseover="underline('enlaceAceptarForm')" onmouseout="underline('enlaceAceptarForm')">
		<a id="enlaceAceptarForm" href="javascript:void(0);"><fmt:message key="manteniment.aceptar" /></a>
	</div>
	</div>
	</form>

	<div class="separadorH"></div>

</c:if>




</body>
</html>
