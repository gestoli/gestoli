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
<title><fmt:message key="proces.trasbals.titol" /></title>
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

<script>
	var avis = avis || {};
	avis = {
		noDO: "<fmt:message key="proces.trasbals.avis.noDo"/>",
		pendent: "<fmt:message key="proces.trasbals.avis.pendent"/>",
		DO: "<fmt:message key="proces.trasbals.avis.Do"/>"
	};
    var msg = msg || {};
    msg = {
		noDO: "<fmt:message key="consulta.general.camp.categoriaOli.1"/>",
		pendent: "<fmt:message key="consulta.general.camp.categoriaOli.2"/>",
		DO: "<fmt:message key="consulta.general.camp.categoriaOli.3"/>"
    };
</script>

<script type="text/javascript" language="javascript">
// <![CDATA[
    var winClose;
    
	function litrosKilos(num) {
		var litros = document.getElementById('litres_'+num).value;
		var kilos = litros * 0.916;
		document.getElementById('kilos_'+num).value = kilos.toFixed(3);
		document.getElementById('litrosAfegits').value = sumatoria('litres_');
		document.getElementById('kilosAfegits').value = sumatoria('kilos_').toFixed(3);
	}
	
	function kilosLitros(num) {
		var kilos = document.getElementById('kilos_'+num).value;
		var litros = kilos / 0.916;
		document.getElementById('litres_'+num).value = litros.toFixed(3);
		document.getElementById('kilosAfegits').value = sumatoria('kilos_');
		document.getElementById('litrosAfegits').value = sumatoria('litres_').toFixed(3);
	}

	function sumatoria(nom) {
		var inputs = document.getElementsByTagName('input');
		var suma = 0;
		for (var i = 0; i < inputs.length; i++) {
			if (inputs[i].type == "text" && inputs[i].id.indexOf(nom) != -1) {
				suma += isNaN(parseFloat(inputs[i].value))?0:parseFloat(inputs[i].value);
			}
		}
		return isNaN(parseFloat(suma))?0:parseFloat(suma);
	}

	function actualitzarPartidesOli(win) {
		var regex = new RegExp("[\\?&]establimentId=([^&#]*)");
		var results = regex.exec( window.location.href );
		var establiment = results[1];
	    processosService.partidesOliByEstabliment(establiment, carregarPartidesOli);
	    winClose = win;
	    setTimeout("winClose.close()", 1000);
	}
	
	function carregarPartidesOli(datos) {
		var value = document.getElementById("idPartidaOli").value;
		DWRUtil.removeAllOptions("idPartidaOli");
		DWRUtil.addOptions("idPartidaOli", [{id:"",nom:"- - - - -"}], 'id', 'nom');
    	DWRUtil.addOptions("idPartidaOli", datos, 'id', 'nom');
    	document.getElementById("idPartidaOli").value = value;
	}

	function ompleCategoriaOli() {
		var partidaOli = document.getElementById("idPartidaOli");
		var partidaDO = document.getElementById("partidaDO");
		if (partidaOli.value != ""){
			processosService.tipusPartidaOlis(partidaOli.value, partidaDO.value, nomCategoria);
		} else {
			document.getElementById('nomCategoriaOli').value = "";
		}
	}
	
	function nomCategoria(value){
		var categoria = document.getElementById('nomCategoriaOli');
		if (value[0] == "No DO"){
			categoria.value = msg.noDO;
		} else if (value[0] == "Pendent"){
			categoria.value = msg.pendent;
		} else {
			categoria.value = msg.DO;
		}

		if(value[1] != "" && value[0] != "No DO"){
			if (document.getElementById('categoriaDesti').value == 'noDO'){
				alert(avis.noDO);
			}
	
			if (document.getElementById('categoriaDesti').value == 'pendent'){
				alert(avis.pendent);
			}
		}
	}
// ]]>
</script>

</head>
<body>
	<input type="hidden" id="categoriaDesti" name="categoriaDesti" value="${catDesti}"/>
	<input type="hidden" id="partidaDO" name="partidaDO" value="${partidaDO}"/>
	<c:if test="${catDesti == 'noDO'}">
		<div id="avis_NoDO">
			<div class="contenedorMensaje">
			<div class="mensajeAvis">
			<div class="capa1"></div>
			<div class="capa2"></div>
			<div class="capa3"></div>
			<div class="capa4"></div>
			<div class="capa5">
				<div class="capa6">
					<p><fmt:message key="proces.trasbals.missatge.noDo" /></p>
				</div>
			</div>
			</div>
			</div>
			<div class="separadorH"></div>
		</div>
	</c:if>
<form id="formulario" name="procesTrasbalsForm"
	action="ProcesTrasbalsForm.html" method="post" class="extended seguit"
	onsubmit="">
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="calendar" />
		<c:param name="path" value="formData.data" />
		<c:param name="title">
			<fmt:message key="proces.trasbals.camp.data" />
		</c:param>
		<c:param name="camp" value="campo_dataElaboracio" />
		<c:param name="name" value="data" />
		<c:param name="maxlength" value="10" />
		<c:param name="required" value="required" />
		<c:param name="aclaracio">
			<fmt:message key="proces.aclaracio.formatdata" />
		</c:param>
		<c:param name="clase" value="conMargen campoForm165" />
	</c:import>

	<div class="separadorH"></div>

	<c:set var="crearPartida">
		<a href="PartidaOliPopupForm.html" rel="external(720,380)"> 
			<fmt:message key="proces.elaboracioOli.camp.crearPartidaNova" /> 
		</a>
	</c:set> 
		
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="select" />
		<c:param name="path" value="formData.idPartidaOli" />
		<c:param name="required" value="required" />
		<c:param name="title">
			<fmt:message key="proces.elaboracioOli.camp.nomPartida" />
		</c:param>
		<c:param name="camp" value="idPartidaOli" />
		<c:param name="name" value="idPartidaOli" />
		<c:param name="selectItems" value="partidesOli" />
		<c:param name="selectItemsId" value="id" />
		<c:param name="selectItemsValue" value="nom" />
		<c:param name="selectSelectedValue" value="${formData.idPartidaOli}"/>
		<c:param name="clase" value="campoFormGrande conMargen" />
		<c:param name="onchange" value="ompleCategoriaOli();" />
		<c:param name="aclaracio" value="${crearPartida}"/>
		<c:param name="value" value="${formData.idPartidaOli}"/>
	</c:import>
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.nomCategoriaOli" />
		<c:param name="title">
			<fmt:message key="proces.elaboracioOli.camp.categoriaOli" />
		</c:param>
		<c:param name="camp" value="nomCategoriaOli" />
		<c:param name="name" value="nomCategoriaOli" />
		<c:param name="required" value="required" />
		<c:param name="maxlength" value="128" />
		<c:param name="clase" value="campoFormGrande readOnly" />
		<c:param name="readonly" value="true" />
	</c:import>
	
	<div class="separadorH"></div>
		
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.acidesa" />
		<c:param name="title">
			<fmt:message key="proces.trasbals.camp.acidesa" />
		</c:param>
		<c:param name="camp" value="campo_acidesa" />
		<c:param name="name" value="acidesa" />
		<c:param name="required" value="required" />
		<%--c:param name="maxlength" value="10"/--%>
		<c:param name="clase" value="campoFormMediano conMargen" />
	</c:import>
	
	<div class="separadorH"></div>
	
	<div class="titolBloque"><label for="origen"><b><fmt:message
		key="proces.trasbals.camp.dipositOrigen" />: </b></label></div>
	<div class=" bloqueTablas"><c:forEach var="dipositOrigen"
		items="${origen}" varStatus="status">
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
					<th colspan="2" class="unicoHead"><c:out
						value=" ${dipositOrigen.codiAssignat}" /></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td class="ancho318"><fmt:message
						key="proces.trasbals.camp.capacitat" /></td>
					<td class="boldtd"><fmt:formatNumber
						value="${dipositOrigen.capacitat}" maxFractionDigits="2" /> l.</td>
				</tr>
				<tr>
					<td><fmt:message key="proces.trasbals.camp.volumActual" /></td>
					<td class="boldtd"><c:choose>
						<c:when
							test="${dipositOrigen.volumActual != null && dipositOrigen.volumActual > 0 && dipositOrigen.establiment.unitatMesura == 'l'}">
							<fmt:formatNumber value="${dipositOrigen.volumActual}"
								maxFractionDigits="2" /> l</c:when>
						<c:when
							test="${dipositOrigen.volumActual != null && dipositOrigen.volumActual > 0 && dipositOrigen.establiment.unitatMesura == 'k'}">
							<fmt:formatNumber value="${dipositOrigen.disponibleOliQuilos}"
								maxFractionDigits="2" /> kg.</c:when>
						<c:otherwise>
							<fmt:message key="proces.trasbals.camp.dipositBuit" />
						</c:otherwise>
					</c:choose></td>
				</tr>
				<tr>
					<td><fmt:message key="proces.trasbals.camp.categoriaOli" /></td>
					<td class="boldtd">
						<fmt:message key="consulta.general.camp.categoriaOli.${dipositOrigen.partidaOli.categoriaOli.id}" />
					</td>
				</tr>
				<tr>
					<td><fmt:message key="proces.trasbals.camp.acidesa" /></td>
					<td class="boldtd"><fmt:formatNumber
						value="${dipositOrigen.acidesa}" maxFractionDigits="2" /></td>
				</tr>
	
				<tr>
					<td class="conversor">
	
					<fieldset><c:if
						test="${dipositOrigen.establiment.unitatMesura == 'l'}">
						<div class="conv"><c:import url="comu/CampFormulari.jsp">
							<c:param name="tipus" value="text" />
							<c:param name="path" value="formData.litros[${status.count - 1}]" />
							<c:param name="required" value="required" />
							<%--c:param name="maxlength" value="10"/--%>
							<c:param name="title">
								<fmt:message key="proces.trasbals.camp.litresExtrets" />
							</c:param>
							<c:param name="camp" value="litres_${dipositOrigen.id}" />
							<c:param name="name" value="litros[${status.count - 1}]" />
							<%--c:param name="aclaracio"><fmt:message key="proces.aclaracio.litres"/></c:param--%>
							<c:param name="clase" value="campoFormMediano conMargen" />
							<c:param name="onkeyup" value="litrosKilos(${dipositOrigen.id})" />
						</c:import></div>
						<div class="conv"><c:import url="comu/CampFormulari.jsp">
							<c:param name="tipus" value="text" />
							<c:param name="path" value="formData.kilos[${status.count - 1}]" />
							<%--c:param name="required" value="required"/--%>
							<%--c:param name="maxlength" value="10"/--%>
							<c:param name="title">
								<fmt:message key="proces.trasbals.camp.kilosExtrets" />
							</c:param>
							<c:param name="camp" value="kilos_${dipositOrigen.id}" />
							<c:param name="name" value="kilos[${status.count - 1}]" />
							<%--c:param name="aclaracio"><fmt:message key="proces.aclaracio.kilos"/></c:param--%>
							<c:param name="clase" value="campoFormMediano readOnly" />
							<c:param name="readonly" value="true" />
						</c:import></div>
					</c:if> <c:if test="${dipositOrigen.establiment.unitatMesura == 'k'}">
						<div class="conv"><c:import url="comu/CampFormulari.jsp">
							<c:param name="tipus" value="text" />
							<c:param name="path" value="formData.kilos[${status.count - 1}]" />
							<c:param name="required" value="required" />
							<%--c:param name="maxlength" value="10"/--%>
							<c:param name="title">
								<fmt:message key="proces.trasbals.camp.kilosExtrets" />
							</c:param>
							<c:param name="camp" value="kilos_${dipositOrigen.id}" />
							<c:param name="name" value="kilos[${status.count - 1}]" />
							<%--c:param name="aclaracio"><fmt:message key="proces.aclaracio.kilos"/></c:param--%>
							<c:param name="clase" value="campoFormMediano conMargen" />
							<c:param name="onkeyup" value="kilosLitros(${dipositOrigen.id})" />
						</c:import></div>
						<div class="conv"><c:import url="comu/CampFormulari.jsp">
							<c:param name="tipus" value="text" />
							<c:param name="path" value="formData.litros[${status.count - 1}]" />
							<%--c:param name="required" value="required"/--%>
							<%--c:param name="maxlength" value="10"/--%>
							<c:param name="title">
								<fmt:message key="proces.trasbals.camp.litresExtrets" />
							</c:param>
							<c:param name="camp" value="litres_${dipositOrigen.id}" />
							<c:param name="name" value="litros[${status.count - 1}]" />
							<%--c:param name="aclaracio"><fmt:message key="proces.aclaracio.litres"/></c:param--%>
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
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="hidden" />
			<c:param name="path"
				value="formData.dipositsOrigen[${status.count - 1}].id" />
			<c:param name="camp" value="diposit_${dipositOrigen.id}" />
			<c:param name="name" value="dipositsOrigen[${status.count - 1}].id" />
			<c:param name="value" value="${dipositOrigen.id}" />
		</c:import>
	</c:forEach></div>
	
	<div class="titolBloque"><label for="desti"><b><fmt:message
		key="proces.trasbals.camp.dipositDesti" />: </b></label></div>
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
				<th colspan="2" class="unicoHead"><c:out
					value=" ${dipositDesti.codiAssignat}" /></th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td class="ancho318"><fmt:message
					key="proces.trasbals.camp.capacitat" /></td>
				<td class="boldtd"><fmt:formatNumber
					value="${dipositDesti.capacitat}" maxFractionDigits="2" /> l.</td>
			</tr>
			<tr>
				<td><fmt:message key="proces.trasbals.camp.volumActual" /></td>
				<td class="boldtd"><c:choose>
					<c:when
						test="${dipositDesti.volumActual != null && dipositDesti.establiment.unitatMesura == 'l'}">
						<fmt:formatNumber value="${dipositDesti.volumActual}"
							maxFractionDigits="2" /> l</c:when>
					<c:when
						test="${dipositDesti.volumActual != null && dipositDesti.establiment.unitatMesura == 'k'}">
						<fmt:formatNumber value="${dipositDesti.disponibleOliQuilos}"
							maxFractionDigits="2" /> kg.</c:when>
					<c:otherwise>
						<fmt:message key="proces.trasbals.camp.dipositBuit" />
					</c:otherwise>
				</c:choose></td>
			</tr>
			<tr>
				<td><fmt:message key="proces.trasbals.camp.categoriaOliResultant" /></td>
				<td class="boldtd">
					<c:choose>
						<c:when test="${catDesti == 'noDO'}">
							<fmt:message key="consulta.general.camp.categoriaOli.1" />
						</c:when>
						<c:when test="${catDesti == 'DO'}">
							<fmt:message key="consulta.general.camp.categoriaOli.3" />
						</c:when>
						<c:otherwise>
							<fmt:message key="consulta.general.camp.categoriaOli.2" />
						</c:otherwise>
						<%--c:when test="${dipositDesti.volumActual != null && dipositDesti.volumActual > 0}">
							<fmt:message key="consulta.general.camp.oliDesqualificat" />
						</c:when>
						<c:otherwise>
							<fmt:message key="consulta.general.camp.categoriaOli.${origen[0].partidaOli.categoriaOli.id}" />
						</c:otherwise--%>
					</c:choose>
				</td>
			</tr>
	
	
			<tr>
				<td class="conversor">
	
				<fieldset><c:if
					test="${dipositDesti.establiment.unitatMesura == 'l'}">
					<div class="conv"><c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="text" />
						<c:param name="path" value="formData.litrosAfegits" />
						<c:param name="required" value="required" />
						<%--c:param name="maxlength" value="10"/--%>
						<c:param name="title">
							<fmt:message key="proces.trasbals.camp.litresAfegits" />
						</c:param>
						<c:param name="camp" value="litrosAfegits" />
						<c:param name="name" value="litrosAfegits" />
						<%--c:param name="aclaracio"><fmt:message key="proces.aclaracio.litres"/></c:param--%>
						<c:param name="clase" value="campoFormMediano conMargen readOnly" />
						<c:param name="readonly" value="true" />
					</c:import></div>
					<div class="conv"><c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="text" />
						<c:param name="path" value="formData.kilosAfegits" />
						<%--c:param name="required" value="required"/--%>
						<%--c:param name="maxlength" value="10"/--%>
						<c:param name="title">
							<fmt:message key="proces.trasbals.camp.kilosAfegits" />
						</c:param>
						<c:param name="camp" value="kilosAfegits" />
						<c:param name="name" value="kilosAfegits" />
						<%--c:param name="aclaracio"><fmt:message key="proces.aclaracio.kilos"/></c:param--%>
						<c:param name="clase" value="campoFormMediano readOnly" />
						<c:param name="readonly" value="true" />
					</c:import></div>
				</c:if> <c:if test="${dipositDesti.establiment.unitatMesura == 'k'}">
					<div class="conv"><c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="text" />
						<c:param name="path" value="formData.kilosAfegits" />
						<c:param name="required" value="required" />
						<%--c:param name="maxlength" value="10"/--%>
						<c:param name="title">
							<fmt:message key="proces.trasbals.camp.kilosAfegits" />
						</c:param>
						<c:param name="camp" value="kilosAfegits" />
						<c:param name="name" value="kilosAfegits" />
						<%--c:param name="aclaracio"><fmt:message key="proces.aclaracio.kilos"/></c:param--%>
						<c:param name="clase" value="campoFormMediano conMargen readOnly" />
						<c:param name="readonly" value="true" />
					</c:import></div>
					<div class="conv"><c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="text" />
						<c:param name="path" value="formData.litrosAfegits" />
						<%--c:param name="required" value="required"/--%>
						<%--c:param name="maxlength" value="10"/--%>
						<c:param name="title">
							<fmt:message key="proces.trasbals.camp.litresAfegits" />
						</c:param>
						<c:param name="camp" value="litrosAfegits" />
						<c:param name="name" value="litrosAfegits" />
						<%--c:param name="aclaracio"><fmt:message key="proces.aclaracio.litres"/></c:param--%>
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
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="hidden" />
		<c:param name="path" value="formData.dipositDesti.id" />
		<c:param name="camp" value="diposit_${dipositDesti.id}" />
		<c:param name="name" value="dipositDesti.id" />
		<c:param name="value" value="${dipositDesti.id}" />
	</c:import></div>
	</div>
	
	<div class="separadorH"></div>
	
	<div class="botonesForm">
	<div id="guardarForm" class="btnCorto"
		onclick="if(confirm('<fmt:message key="manteniment.confirmar"/>')){submitForm('formulario')}"
		onmouseover="underline('enlaceGuardarForm')"
		onmouseout="underline('enlaceGuardarForm')"><a
		id="enlaceGuardarForm" href="javascript:void(0);"><fmt:message
		key="manteniment.guardar" /></a></div>
	
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
	type="hidden" id="tipusProces" name="tipusProces" value="3" /> <input
	type="hidden" id="pas" name="pas" value="1" /></form>

<!-- Colores en tablas -->
<script type="text/javascript">
		jQuery(document).ready(function(){
			setEstilosTabla(true);
			redibujarError();
		})
	</script>

</body>
</html>