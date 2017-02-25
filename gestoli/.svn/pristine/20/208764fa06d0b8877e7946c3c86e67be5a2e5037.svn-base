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
<title><fmt:message key="proces.perdues.titol" /></title>
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




<script type="text/javascript" language="javascript">
// <![CDATA[
	function litrosKilos() {
		var litros = document.getElementById('litres').value;
		var kilos = litros * 0.916;
		document.getElementById('kilos').value = kilos.toFixed(3);		
	}
	
	function kilosLitros() {
		var kilos = document.getElementById('kilos').value;
		var litros = kilos / 0.916;
		document.getElementById('litres').value = litros.toFixed(3);		
	}
	
	
// ]]>
</script>



</head>
<body>

<form id="formulario" name="procesPerduesForm"
	action="ProcesPerduesForm.html" method="post" class="extended seguit"
	onsubmit=""><c:import url="comu/CampFormulari.jsp">
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
</c:import> <c:import url="comu/CampFormulari.jsp">
	<c:param name="tipus" value="hidden" />
	<c:param name="path" value="formData.diposit.id" />
	<c:param name="camp" value="diposit.id" />
	<c:param name="name" value="diposit.id" />
	<c:param name="value" value="${deposito.id}" />
</c:import>

<div class="separadorH"></div>

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
<table cellpadding=0 cellspacing=0 class="listadoEstrecho noRoll">
	<thead>
		<tr>
			<th colspan="2" class="unicoHead"><c:out
				value=" ${deposito.codiAssignat}" /></th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td class="ancho318"><fmt:message
				key="proces.trasbals.camp.capacitat" /></td>
			<td class="boldtd"><fmt:formatNumber
				value="${deposito.capacitat}" maxFractionDigits="2" /> l.</td>
		</tr>
		<tr>
			<td><fmt:message key="proces.perdues.camp.contenido" /></td>
			<td class="boldtd"><c:choose>
				<c:when
					test="${deposito.volumActual != null && deposito.establiment.unitatMesura == 'l'}">
					<fmt:formatNumber value="${deposito.volumActual}"
						maxFractionDigits="2" /> l</c:when>
				<c:when
					test="${deposito.volumActual != null && deposito.establiment.unitatMesura == 'k'}">
					<fmt:formatNumber value="${deposito.disponibleOliQuilos}"
						maxFractionDigits="2" /> kg.</c:when>
				<c:otherwise>
					<fmt:message key="proces.trasbals.camp.dipositBuit" />
				</c:otherwise>
			</c:choose></td>
		</tr>
		<tr>
			<td><fmt:message key="proces.trasbals.camp.categoriaOli" /></td>
			<td class="boldtd"><fmt:message key="consulta.general.camp.categoriaOli.${deposito.partidaOli.categoriaOli.id}" /></td>
		</tr>

		<tr>
			<td class="conversor">

			<fieldset><c:if
				test="${deposito.establiment.unitatMesura == 'l'}">
				<div class="conv"><c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="text" />
					<c:param name="path" value="formData.litros" />
					<c:param name="required" value="required" />
					<c:param name="title">
						<fmt:message key="proces.perdues.camp.litrosPerdidos" />
					</c:param>
					<c:param name="camp" value="litres" />
					<c:param name="name" value="litros" />
					<c:param name="clase" value="campoFormMediano conMargen" />
					<c:param name="onkeyup" value="litrosKilos()" />
				</c:import></div>
				<div class="conv"><c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="text" />
					<c:param name="path" value="formData.kilos" />
					<c:param name="title">
						<fmt:message key="proces.perdues.camp.kilosPerdidos" />
					</c:param>
					<c:param name="camp" value="kilos" />
					<c:param name="name" value="kilos" />
					<c:param name="clase" value="campoFormMediano readOnly" />
					<c:param name="readonly" value="true" />
				</c:import></div>
			</c:if> <c:if test="${deposito.establiment.unitatMesura == 'k'}">
				<div class="conv"><c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="text" />
					<c:param name="path" value="formData.kilos" />
					<c:param name="required" value="required" />
					<c:param name="title">
						<fmt:message key="proces.perdues.camp.kilosPerdidos" />
					</c:param>
					<c:param name="camp" value="kilos" />
					<c:param name="name" value="kilos" />
					<c:param name="clase" value="campoFormMediano conMargen" />
					<c:param name="onkeyup" value="kilosLitros()" />
				</c:import></div>
				<div class="conv"><c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="text" />
					<c:param name="path" value="formData.litros" />
					<c:param name="title">
						<fmt:message key="proces.perdues.camp.litrosPerdidos" />
					</c:param>
					<c:param name="camp" value="litres" />
					<c:param name="name" value="litros" />
					<c:param name="clase" value="campoFormMediano readOnly" />
					<c:param name="readonly" value="true" />
				</c:import></div>
			</c:if></fieldset>
			</td>
			<td class="conversor"></td>
		</tr>
	</tbody>
</table>
</div>
</div>
</div>


<div class="separadorH"></div>

<div id="observacionesForm"
	class="campoForm <c:out value="${param.required}"/><c:if test="${not empty status.errorMessage}"> error</c:if>">
<c:import url="comu/CampFormulari.jsp">
	<c:param name="tipus" value="textarea" />
	<c:param name="path" value="formData.descripcion" />
	<c:param name="title">
		<fmt:message key="proces.perdues.camp.descripcion" />
	</c:param>
	<c:param name="camp" value="descripcion" />
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
	type="hidden" id="tipusProces" name="tipusProces" value="4"> <input
	type="hidden" id="pas" name="pas" value="0"></form>

<!-- Colores en tablas -->
<script type="text/javascript">
		jQuery(document).ready(function(){
			setEstilosTabla(true);
			redibujarError();			
		})
	</script>

</body>
</html>