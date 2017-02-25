<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>
<%@ page import="es.caib.gestoli.front.util.*"%>
<%@ page import="java.util.ResourceBundle"%>
<%@ page import="java.util.Locale"%>
<%
	String lang = Idioma.getLang(request);
	request.setAttribute("lang",lang);
%>

<html>
<head>
<title><fmt:message key="consulta.oliComercialitzat.titol" /></title>

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
<form id="formulario" action="ConsultaGeneralOliComercialitzat.html"
	method="post" class="extended seguit">
<fieldset><c:import url="comu/CampFormulari.jsp">
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
	<c:param name="clase" value="conMargen campoForm165" />
</c:import> <c:import url="comu/CampFormulari.jsp">
	<c:param name="tipus" value="calendar" />
	<c:param name="path" value="formData.dataFi" />
	<c:param name="required" value="required" />
	<c:param name="title">
		<fmt:message key="consulta.camp.dataFi" />
	</c:param>
	<c:param name="camp" value="dataFi" />
	<c:param name="maxlength" value="10" />
	<c:param name="aclaracio">
		<fmt:message key="consulta.aclaracio.formatData" />
	</c:param>
	<c:param name="clase" value="campoForm165" />
</c:import>

<div class="separadorH"></div>

<div class="botonesForm">
<div id="guardarForm" class="btnCorto"
	onclick="submitForm('formulario')"
	onmouseover="underline('enlaceGuardarForm')"
	onmouseout="underline('enlaceGuardarForm')"><a
	id="enlaceGuardarForm" href="javascript:void(0);"><fmt:message
	key="manteniment.cercar" /></a></div>
</div>

<c:import url="comu/CampFormulari.jsp">
	<c:param name="tipus" value="hidden" />
	<c:param name="path" value="formData.buscar" />
	<c:param name="camp" value="buscar" />
</c:import></fieldset>
</form>

<div class="separadorH"></div>



<div class="resultadosGeneral"><c:if
	test="${establecimientos != null && not empty establecimientos}">
	<div class="cajaTabla">
	<div class="layoutSombraTabla">
	<div class="rellenoInf"></div>
	<div class="rellenoIzq"></div>
	<div class="rellenoDer"></div>
	<div class="supDer"></div>
	<div class="supIzq"></div>
	<div class="infIzq"></div>
	<div class="infDer"></div>
	<table cellpadding=0 cellspacing=0 class="noRoll">
		<thead>
			<tr>
				<th class="ancho340"><fmt:message
					key="consulta.oliComercialitzat.camp.establiment" /></th>
				<th class="final"><fmt:message
					key="consulta.oliComercialitzat.camp.litros" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="establecimiento" items="${establecimientos}">
				<tr>
					<td class="ancho340"><c:out value="${establecimiento.nom}" /></td>
					<td class="ancho110 boldtd"><fmt:formatNumber
						value="${oliComercialitzatMap[establecimiento.id]}"
						maxFractionDigits="2" /> l.</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
	</div>
</c:if>
<div class="separadorH"></div>
<c:if test="${not empty param.buscar && not empty total}">
	<div class="cajaTabla">
	<div class="layoutSombraTabla">
	<div class="rellenoInf"></div>
	<div class="rellenoIzq"></div>
	<div class="rellenoDer"></div>
	<div class="supDer"></div>
	<div class="supIzq"></div>
	<div class="infIzq"></div>
	<div class="infDer"></div>
	<table cellpadding=0 cellspacing=0 class="noRoll">
		<tr>
			<td class="ancho340 total"><fmt:message
				key="consulta.oliComercialitzat.camp.total" /></td>
			<td class="ancho110 boldtd"><fmt:formatNumber value="${total}"
				maxFractionDigits="2" /> l.</td>
		</tr>
	</table>
	</div>
	</div>
</c:if></div>

<!-- Colores en tablas -->
<script type="text/javascript">
			$(document).ready(function(){
				setEstilosTabla(true);
			})
		</script>





</body>
</html>
