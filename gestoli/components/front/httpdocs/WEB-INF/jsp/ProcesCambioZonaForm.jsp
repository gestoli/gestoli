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
<title><fmt:message key="proces.sortidaOli.titol" /></title>
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
	
	function canviMotiu() {
		var venta = '${motiuVenta}';
		if (document.getElementById('campo_motiu') != null) {
			var select = document.getElementById('campo_motiu');
			// Comparamos con motivoVenta.
			if (select.value == venta) {
				document.getElementById('dadesMotiu').style.display = '';
			} else {
				document.getElementById('dadesMotiu').style.display = 'none';
			}
		}
	}
	
// ]]>
</script>

</head>
<body>

<form id="formulario" name="procesCambioZonaForm" action="ProcesCambioZonaForm.html?accio=${canviZona}" method="post" class="extended seguit" onsubmit="">
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="calendar" />
		<c:param name="path" value="formData.canviZonaData" />
		<c:param name="title">
			<fmt:message key="sortidaLot.camp.data" />
		</c:param>
		<c:param name="camp" value="campo_data" />
		<c:param name="name" value="canviZonaData" />
		<c:param name="maxlength" value="10" />
		<c:param name="required" value="required" />
		<c:param name="aclaracio">
			<fmt:message key="proces.aclaracio.formatdata" />
		</c:param>
		<c:param name="clase" value="conMargen" />
	</c:import> 
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="hidden" />
		<c:param name="path" value="formData.lot[0].id" />
		<c:param name="camp" value="lot[0].id" />
		<c:param name="name" value="lot[0].id" />
		<c:param name="value" value="${lots[0].id}" />
	</c:import> 
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="hidden" />
		<c:param name="path" value="formData.tipusSortida" />
		<c:param name="camp" value="tipusSortida" />
		<c:param name="name" value="tipusSortida" />
		<c:param name="value" value="l" />
	</c:import> 
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="hidden" />
		<c:param name="path" value="formData.accion" />
		<c:param name="camp" value="accion" />
		<c:param name="name" value="accion" />
		<c:param name="value" value="${canviZona}" />
	</c:import>

	<div class="separadorH"></div>
	
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="select" />
		<c:param name="path" value="formData.idZona" />
		<c:param name="required" value="required" />
		<c:param name="title">
			<fmt:message key="sortidaLot.camp.zona" />
		</c:param>
		<c:param name="camp" value="campo_zona" />
		<c:param name="name" value="idZona" />
		<c:param name="selectItems" value="zonas" />
		<c:param name="selectItemsId" value="id" />
		<c:param name="selectItemsValue" value="nom" />
		<c:param name="selectSelectedValue" value="${formData.idZona}" />
		<c:param name="clase" value="campoFormMediano conMargen" />
	</c:import>

	<div class="separadorH"></div>

	<div id="observacionesForm" class="campoForm <c:out value="${param.required}"/><c:if test="${not empty status.errorMessage}"> error</c:if>">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="textarea" />
			<c:param name="path" value="formData.canviZonaObservacions" />
			<c:param name="title">
				<fmt:message key="sortidaLot.camp.observacions" />
			</c:param>
			<c:param name="camp" value="canviZonaObservacions" />
		</c:import>
	</div>

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
	type="hidden" id="tipusProces" name="tipusProces" value="9"> <input
	type="hidden" id="pas" name="pas" value="0"></form>

	<!-- Colores en tablas -->
	<script type="text/javascript">
		$(document).ready(function(){
			canviMotiu();
			setEstilosTabla(true);
		})
	</script>

</body>
</html>