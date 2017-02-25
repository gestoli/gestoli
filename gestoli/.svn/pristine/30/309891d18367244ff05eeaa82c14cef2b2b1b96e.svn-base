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
<title><fmt:message key="proces.moureDiposit.titol" /></title>
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
<link href="js/calendar/calendar-viti.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/form.js"></script>



</head>
<body>

<form id="formulario" name="procesAcceptarOliGranelForm" action="ProcesAcceptarOliGranelForm.html" method="post" class="extended seguit" onsubmit="">
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="calendar" />
		<c:param name="path" value="formData.data" />
		<c:param name="title"><fmt:message key="proces.acceptarOliGranel.camp.data" /></c:param>
		<c:param name="camp" value="campo_dataElaboracio" />
		<c:param name="name" value="data" />
		<c:param name="maxlength" value="10" />
		<c:param name="required" value="required" />
		<c:param name="aclaracio"><fmt:message key="proces.aclaracio.formatdata" /></c:param>
		<c:param name="clase" value="conMargen campoForm165" />
	</c:import>
	
	<div class="separadorH"></div>
	
	
	<div class="titolBloque"><label for="origens"><b><fmt:message key="proces.trasbals.camp.dipositOrigen" />: </b></label></div>
	
	<div class=" bloqueTablas">

<c:forEach var="dipositOrigen" items="${origens}" varStatus="status">
	<div class="cajaTabla">
	<div class="layoutSombraTabla">
	<div class="rellenoInf"></div>
	<div class="rellenoIzq"></div>
	<div class="rellenoDer"></div>
	<div class="supDer"></div>
	<div class="supIzq"></div>
	<div class="infIzq"></div>
	<div class="infDer"></div>

	<table cellpadding="0" cellspacing="0" class="listadoEstrecho noRoll">
		<thead>
			<tr>
				<th colspan="2" class="unicoHead"><c:out value=" ${dipositOrigen.codiAssignat}" /></th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td class="ancho200"><fmt:message key="proces.trasbals.camp.capacitat" /></td>
				<td class="boldtd"><fmt:formatNumber value="${dipositOrigen.capacitat}" maxFractionDigits="2" /> l.</td>
			</tr>
			<tr>
				<td><fmt:message key="proces.acceptarOliGranel.camp.volumTrasllat" /></td>
				<td class="boldtd"><c:choose>
					<c:when test="${dipositOrigen.volumTrasllat != null && dipositOrigen.volumTrasllat > 0 && dipositOrigen.establiment.unitatMesura == 'l'}">
						<fmt:formatNumber value="${dipositOrigen.volumTrasllat}" maxFractionDigits="2" /> l.</c:when>
					<c:when test="${dipositOrigen.volumTrasllat != null && dipositOrigen.volumTrasllat > 0 && dipositOrigen.establiment.unitatMesura == 'k'}">
						<fmt:formatNumber value="${dipositOrigen.disponibleTrasllatOliQuilos}" maxFractionDigits="2" /> kgs.</c:when>
					<c:otherwise>
						<fmt:message key="proces.trasbals.camp.dipositBuit" />
					</c:otherwise>
				</c:choose></td>
			</tr>
			<tr>
				<td><fmt:message key="proces.trasbals.camp.categoriaOli" /></td>
				<td class="boldtd"><fmt:message key="consulta.general.camp.categoriaOli.${dipositOrigen.partidaOli.categoriaOli.id}" /></td>
			</tr>
			<tr>
				<td><fmt:message key="proces.trasbals.camp.acidesa" /></td>
				<td class="boldtd"><fmt:formatNumber value="${dipositOrigen.acidesa}" maxFractionDigits="2" /></td>
			</tr>
		</tbody>
	</table>
	</div>
	</div>
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="hidden" />
		<c:param name="path" value="formData.dipositsOrigen[${status.count - 1}].id" />
		<c:param name="camp" value="diposit_${dipositOrigen.id}" />
		<c:param name="name" value="dipositsOrigen[${status.count - 1}].id" />
		<c:param name="value" value="${dipositOrigen.id}" />
	</c:import>
</c:forEach>

</div>

<div class="separadorH"></div>

<div class="titolBloque"><label for="desti"><b><fmt:message key="proces.trasbals.camp.dipositDesti" />: </b></label></div>

<div class=" bloqueTablas">

	<div class="cajaTabla">
	<div class="layoutSombraTabla">
	<div class="rellenoInf"></div>
	<div class="rellenoIzq"></div>
	<div class="rellenoDer"></div>
	<div class="supDer"></div>
	<div class="supIzq"></div>
	<div class="infIzq"></div>
	<div class="infDer"></div>

	<table cellpadding="0" cellspacing="0" class="listadoEstrecho noRoll">
		<thead>
			<tr>
				<th colspan="2" class="unicoHead"><c:out value=" ${desti.codiAssignat}" /></th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td class="ancho200"><fmt:message key="proces.trasbals.camp.capacitat" /></td>
				<td class="boldtd"><fmt:formatNumber value="${desti.capacitat}" maxFractionDigits="2" /> l.</td>
			</tr>
			<tr>
				<td><fmt:message key="proces.trasbals.camp.volumActual" /></td>
				<td class="boldtd"><c:choose>
					<c:when test="${desti.volumActual != null && desti.volumActual > 0 && desti.establiment.unitatMesura == 'l'}">
						<fmt:formatNumber value="${desti.volumActual}" maxFractionDigits="2" /> l.</c:when>
					<c:when test="${desti.volumActual != null && desti.volumActual > 0 && desti.establiment.unitatMesura == 'k'}">
						<fmt:formatNumber value="${desti.disponibleOliQuilos}" maxFractionDigits="2" /> kgs.</c:when>
					<c:otherwise>
						<fmt:message key="proces.trasbals.camp.dipositBuit" />
					</c:otherwise>
				</c:choose></td>
			</tr>
			<tr>
				<td><fmt:message key="proces.trasbals.camp.categoriaOli" /></td>
				<td class="boldtd">
					<c:if test="${not empty desti.partidaOli.categoriaOli.id}">
						<fmt:message key="consulta.general.camp.categoriaOli.${desti.partidaOli.categoriaOli.id}" />
					</c:if>
				</td>
			</tr>
			<tr>
				<td><fmt:message key="proces.trasbals.camp.acidesa" /></td>
				<td class="boldtd"><fmt:formatNumber value="${desti.acidesa}" maxFractionDigits="2" /></td>
			</tr>
		</tbody>
	</table>
	</div>
	</div>
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="hidden" />
		<c:param name="path" value="formData.dipositDesti.id" />
		<c:param name="camp" value="diposit_${desti.id}" />
		<c:param name="name" value="dipositDesti.id" />
		<c:param name="value" value="${desti.id}" />
	</c:import>

</div>


<div class="separadorH"></div>

<div class="botonesForm">
<div id="guardarForm" class="btnCorto"
	onclick="if(confirm('<fmt:message key="manteniment.confirmar"/>')){submitForm('formulario')}"
	onmouseover="underline('enlaceGuardarForm')"
	onmouseout="underline('enlaceGuardarForm')">
	<a id="enlaceGuardarForm" href="javascript:void(0);"><fmt:message key="manteniment.aceptar" /></a>
	</div>

<div class="btnCorto" onclick="submitForm('tornarForm')"
	onmouseover="underline('enlaceTornarForm')"
	onmouseout="underline('enlaceTornarForm')"><a
	id="enlaceTornarForm" href="javascript:void(0);">
	<fmt:message key="proces.tornar" /></a></div>

<div id="cancelarForm" class="btnCorto"
	onmouseover="underline('enlaceCancelarForm')"
	onmouseout="underline('enlaceCancelarForm')"
	onclick="document.location ='ProcesInici.html';">
	<a id="enlaceCancelarForm" href="javascript:void(0);"><fmt:message key="proces.cancelar" /></a>
</div>
</div>

</form>

<form id="tornarForm" action="ProcesInici.html" class="seguit">
	<input type="hidden" id="tipusProces" name="tipusProces" value="13" /> 
	<input type="hidden" id="pas" name="pas" value="0" />
</form>

<!-- Colores en tablas -->
<script type="text/javascript">
		jQuery(document).ready(function(){
			setEstilosTabla(true);
		})
	</script>

</body>
</html>