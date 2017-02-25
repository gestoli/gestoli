<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix='fn' uri="/WEB-INF/fn-rt.tld"%>
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
<title><fmt:message key="tancamentLlibres.introduccioExistencies"/></title>

<style type="text/css">
	.noPadding {padding-left: 0!important;}
</style>

<script type="text/javascript" src="dwr/interface/contenidorService.js"></script>
<script type="text/javascript" src="dwr/interface/processosService.js"></script>
<script type="text/javascript" src="dwr/engine.js"></script>
<script type="text/javascript" src="dwr/util.js"> </script>

<script type="text/javascript" src="js/calendar/calendar.js"></script>
<script type="text/javascript" src="js/calendar/calendar-setup.js"></script>
<%	if(lang.equalsIgnoreCase("ca")){ %>
<script type="text/javascript" src="js/calendar/lang/calendar-ca.js"></script>
<script type="text/javascript" src="js/calendar/lang/calendar-es.js"></script>
<%	}else{ 	%>
<script type="text/javascript" src="js/calendar/lang/calendar-es.js"></script>
<% 	} %>
<link href="js/calendar/calendar-viti.css" rel="stylesheet" type="text/css"/>

<script type="text/javascript" src="js/form.js"></script>

<script>
    var avis = avis || {}; // play well with other jsps on the page
    var msg = msg || {};
    msg = {
		noDO: "<fmt:message key="consulta.general.camp.categoriaOli.1"/>",
		pendent: "<fmt:message key="consulta.general.camp.categoriaOli.2"/>",
		DO: "<fmt:message key="consulta.general.camp.categoriaOli.3"/>"
    };
</script>

<script type="text/javascript" language="javascript">
// <![CDATA[
	
	function litrosKilos(num) {
		var litros = document.getElementById('litros_'+num).value;
		var kilos = litros * 0.916;
		document.getElementById('kilos_'+num).value = kilos.toFixed(3);
	}
	
	function kilosLitros(num) {
		var kilos = document.getElementById('kilos_'+num).value;
		var litros = kilos / 0.916;
		document.getElementById('litros_'+num).value = litros.toFixed(3);
	}

	var idElement;

	function carregarCategoria(id) {
		var partidaOli = document.getElementById("campo_partidaOli_" + id);
		var categoriaOli = document.getElementById("nomCategoriaOli_" + id);
		idElement = id;
		if (partidaOli.value != ""){
			processosService.tipusPartidaOli(partidaOli.value, nomCategoria);
		} else {
			categoriaOli.value = "";
		}
	}

	function nomCategoria(value){
		var categoria = document.getElementById("nomCategoriaOli_" + idElement);
		if (value == "No DO"){
			categoria.value = msg.noDO;
		} else if (value == "Pendent"){
			categoria.value = msg.pendent;
		} else if (value == "DO"){
			categoria.value = msg.DO;
		} else {
			categoria.value = "";
		}
	}

	function carregarCategoriaLot(id) {
		var partidaOli = document.getElementById("campo_partidaOliLots_" + id);
		var categoriaOli = document.getElementById("nomCategoriaOliLots_" + id);
		idElement = id;
		if (partidaOli.value != ""){
			processosService.tipusPartidaOli(partidaOli.value, nomCategoriaLot);
		} else {
			categoriaOli.value = "";
		}
	}

	function nomCategoriaLot(value){
		var categoria = document.getElementById("nomCategoriaOliLots_" + idElement);
		if (value == "No DO"){
			categoria.value = msg.noDO;
		} else if (value == "Pendent"){
			categoria.value = msg.pendent;
		} else if (value == "DO"){
			categoria.value = msg.DO;
		} else {
			categoria.value = "";
		}
		if (document.getElementById('dadesEtiquetar_' + idElement) != null) {
			var etiquetar = document.getElementById('dadesEtiquetar_' + idElement);
			var check = document.getElementById('campo_etiquetar_' + idElement);
			if (value == "DO") {
				etiquetar.style.display = '';
				jQuery(document).ready(function(){
					setEstilosTabla(true);
					redibujarError();
				})
			} else {
				etiquetar.style.display = 'none';
				check.checked = false;
				mostrarRang();
			}
		}
	}

	function mostrarRang() {
		if (document.getElementById('campo_etiquetar_' + idElement) != null) {
			var check = document.getElementById('campo_etiquetar_' + idElement);
			if (check.checked) {
				document.getElementById('dadesRang_' + idElement).style.display = '';
			} else {
				document.getElementById('dadesRang_' + idElement).style.display = 'none';
			}
		}
		jQuery(document).ready(function(){
			setEstilosTabla(true);
			redibujarError();
		})
	}

	function canviMarca(idioma, id) {
    	var inputMarca = document.getElementById("campo_marca_" + id);
    	idElement = id;
		processosService.etiquetajesConMarca(inputMarca.value, idioma, carregarEtiquetajes);
		if(document.getElementById("campo_volum_" + id)!= null){
			document.getElementById("campo_volum_" + id).value = "";
		}
		if(document.getElementById("litrosInicialsLots_" + id) != null){
			document.getElementById("litrosInicialsLots_" + id).value = 0;
		}
		if(document.getElementById("litrosActualsLots_" + id) != null){
			document.getElementById("litrosActualsLots_" + id).value = 0;
		}
		if(document.getElementById("kilosInicialsLots_" + id) != null){
			document.getElementById("kilosInicialsLots_" + id).value = 0;
		}
		if(document.getElementById("kilosActualsLots_" + id) != null){
			document.getElementById("kilosActualsLots_" + id).value = 0;
		}
	}

	function carregarEtiquetajes(dades) {
		DWRUtil.removeAllOptions("campo_etiquetatgeId_" + idElement);
    	DWRUtil.addOptions("campo_etiquetatgeId_" + idElement, dades, 'id', 'nom');
	}

	function setVolum(id) {
		var inputEtiquetatge = document.getElementById("campo_etiquetatgeId_" + id);
		idElement = id;
		if (inputEtiquetatge.value != "") {
			processosService.getVolumEtiquetatge(inputEtiquetatge.value, carregaVolum);
		} else {
			document.getElementById("campo_volum_" + id).value = "";
			quantitatEmbotellada(id);
		}
	}

	function carregaVolum(dades) {
		var inputVolum = document.getElementById("campo_volum_" + idElement);
		inputVolum.value = dades;
		
		quantitatEmbotellada(idElement);
	}

	function quantitatEmbotellada(id) {
		if (document.getElementById("campo_volum_" + id) != null) {
			var volum = document.getElementById("campo_volum_" + id).value;
			if (document.getElementById("numeroBotellesInicials_" + id) != null) {
				var numBotellesInicials = document.getElementById("numeroBotellesInicials_" + id).value;
				if (numBotellesInicials != "" && volum != "") {
					var quantitat = numBotellesInicials * volum;
					document.getElementById("litrosInicialsLots_" + id).value = parseFloat(quantitat).toFixed(2);
					document.getElementById("kilosInicialsLots_" + id).value = parseFloat(quantitat * 0.916).toFixed(2);
				} else {
					document.getElementById("litrosInicialsLots_" + id).value = 0;
					document.getElementById("kilosInicialsLots_" + id).value = 0;
				}
			}
			if (document.getElementById("numeroBotellesActuals_" + id) != null) {
				var numBotellesActuals = document.getElementById("numeroBotellesActuals_" + id).value;
				if (numBotellesActuals != "" && volum != "") {
					var quantitat = numBotellesActuals * volum;
					document.getElementById("litrosActualsLots_" + id).value = parseFloat(quantitat).toFixed(2);
					document.getElementById("kilosActualsLots_" + id).value = parseFloat(quantitat * 0.916).toFixed(2);
				} else {
					document.getElementById("litrosActualsLots_" + id).value = 0;
					document.getElementById("kilosActualsLots_" + id).value = 0;
				}
			}
		}
	}

	function onsubmitMultiple() {
		var idStart = "id_varietatsId_";
		var idEnd = "_bis";
		var elements = document.getElementsByTagName("select");
		for(var i=0;i<elements.length;i++)
		{
		     var id = elements[i].id;
		     if((id.length >= idStart.length)&&(id.substring(0,idStart.length) == idStart) && (id.substring(id.length - idEnd.length) != idEnd))
		     {
		    	var msel = document.getElementById(id);
		 		var msel_bis = document.getElementById(id + '_bis');
		 		moveSelectedOptions(msel,msel_bis,true);
	 			removeAllOptions(msel);
		 		copyAllOptions(msel_bis, msel);
		 		selectAllOptions(msel);
		     }
		}
	}

	function actualitzaLitres(pos) {
		var litres = parseInt($("input[id^='quantitat']:eq("+pos+")").val()) * parseFloat($("select[id^='etiquetaCapacitat']:eq("+pos+")").val());
		if (!isNaN(litres)) {
			$("input[id^='litres']").val(litres);
		}
	}
	
	function actualitzaEtiqueta(pos) {
		var etiquetaFi = parseInt($("input[id^='etiquetaInici']:eq("+pos+")").val()) + parseInt($("input[id^='quantitat']:eq("+pos+")").val()) - 1;
		if (!isNaN(etiquetaFi)) {
			$("input[id^='etiquetaFi']:eq("+pos+")").val(etiquetaFi);
		}
	}
// ]]>
</script>

</head>
<body>

	<form 	id="formulario" name="dipositForm" action="TancamentLlibresForm.html" method="post" class="extended seguit ancho665" onsubmit="">
			
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="calendar"/>
			<c:param name="path" value="formData.data"/>
			<c:param name="title">
				<fmt:message key="lot.camp.data" />
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
	
		<!-- DIPÒSITS -->
		<c:if test="${not empty diposits}">
		<h3><fmt:message key="establiment.vista.listadoDepositos" /></h3>
		<div class="separadorH"></div>
		
		<c:forEach var="diposit" items="${diposits}" varStatus="status">
			<div class="cajaTabla">
				<div class="layoutSombraTabla">
					<div class="rellenoInf"></div>
					<div class="rellenoIzq"></div>
					<div class="rellenoDer"></div>
					<div class="supDer"></div>
					<div class="supIzq"></div>
					<div class="infIzq"></div>
					<div class="infDer"></div>
					<table cellpadding="0" cellspacing="0" class="listado665 noRoll">
						<thead>
							<tr>
								<th colspan="2" class="unicoHead"><c:out value=" ${diposit.codiAssignat}" />
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td class="ancho120"><fmt:message key="proces.trasbals.camp.capacitat" /></td>
								<td class="boldtd"><fmt:formatNumber value="${diposit.capacitat}" maxFractionDigits="2" /> l.</td>
							</tr>
							<tr>
								<td><fmt:message key="accio.tancamentLlibres.quantitat" /></td>
								<td class="conversor">
									<fieldset>
										<c:if test="${establiment.unitatMesura == 'l'}">
											<div class="conv">
												<c:import url="comu/CampFormulari.jsp">
													<c:param name="tipus" value="text" />
													<c:param name="path" value="formData.litros[${status.count - 1}]" />
													<c:param name="required" value="required" />
													<c:param name="title">
														<fmt:message key="proces.elaboracioOli.camp.litres" />
													</c:param>
													<c:param name="camp" value="litros_${diposit.id}" />
													<c:param name="name" value="litros[${status.count - 1}]" />
													<c:param name="clase" value="campoFormMediano conMargen" />
													<c:param name="onkeyup" value="litrosKilos(${diposit.id})" />
												</c:import>
											</div>
											<div class="conv">
												<c:import url="comu/CampFormulari.jsp">
													<c:param name="tipus" value="text" />
													<c:param name="path" value="formData.kilos[${status.count - 1}]" />
													<c:param name="title">
														<fmt:message key="proces.elaboracioOli.camp.kilos" />
													</c:param>
													<c:param name="camp" value="kilos_${diposit.id}" />
													<c:param name="name" value="kilos[${status.count - 1}]" />
													<c:param name="clase" value="campoFormMediano readOnly" />
													<c:param name="readonly" value="true" />
												</c:import>
											</div>
										</c:if> 
										<c:if test="${establiment.unitatMesura == 'k'}">
											<div class="conv">
												<c:import url="comu/CampFormulari.jsp">
													<c:param name="tipus" value="text" />
													<c:param name="path" value="formData.kilos[${status.count - 1}]" />
													<c:param name="required" value="required" />
													<c:param name="title">
														<fmt:message key="proces.elaboracioOli.camp.kilos" />
													</c:param>
													<c:param name="camp" value="kilos_${diposit.id}" />
													<c:param name="name" value="kilos[${status.count - 1}]" />
													<c:param name="clase" value="campoFormMediano conMargen" />
													<c:param name="onkeyup" value="kilosLitros(${diposit.id})" />
												</c:import>
											</div>
											<div class="conv">
												<c:import url="comu/CampFormulari.jsp">
													<c:param name="tipus" value="text" />
													<c:param name="path" value="formData.litros[${status.count - 1}]" />
													<c:param name="title">
														<fmt:message key="proces.elaboracioOli.camp.litres" />
													</c:param>
													<c:param name="camp" value="litros_${diposit.id}" />
													<c:param name="name" value="litros[${status.count - 1}]" />
													<c:param name="clase" value="campoFormMediano readOnly" />
													<c:param name="readonly" value="true" />
												</c:import>
											</div>
										</c:if>
									</fieldset>
								</td>
							</tr>
							<tr>
								<td><fmt:message key="accio.tancamentLlibres.partidaOli" /></td>
								<td>
									<c:import url="comu/CampFormulari.jsp">
										<c:param name="tipus" value="select" />
										<c:param name="path" value="formData.idPartidaOli[${status.count - 1}]" />
										<c:param name="required" value="required" />
										<c:param name="camp" value="campo_partidaOli_${diposit.id}" />
										<c:param name="name" value="idPartidaOli[${status.count - 1}]" />
										<c:param name="selectItems" value="partidesOli" />
										<c:param name="selectItemsId" value="id" />
										<c:param name="selectItemsValue" value="nom" />
										<c:param name="selectSelectedValue" value="${formData.idPartidaOli[status.count - 1]}" />
										<c:param name="clase" value="campoFormMediano conMargen" />
										<c:param name="onchange" value="carregarCategoria(${diposit.id});" />
									</c:import>
									<c:import url="comu/CampFormulari.jsp">
										<c:param name="tipus" value="text" />
										<c:param name="path" value="formData.nomCategoriaOli[${status.count - 1}]" />
										<c:param name="camp" value="nomCategoriaOli_${diposit.id}" />
										<c:param name="name" value="nomCategoriaOli[${status.count - 1}]" />
										<c:param name="required" value="required" />
										<c:param name="maxlength" value="128" />
										<c:param name="clase" value="campoFormGrande readOnly" />
										<c:param name="readonly" value="true" />
									</c:import>
								</td>
							</tr>
							<tr>
								<td><fmt:message key="lot.camp.acidesa" /></td>
								<td>
									<c:import url="comu/CampFormulari.jsp">
										<c:param name="tipus" value="text" />
										<c:param name="path" value="formData.acidesa[${status.count - 1}]" />
										<c:param name="camp" value="campo_acidesa_${diposit.id}" />
										<c:param name="name" value="acidesa[${status.count - 1}]" />
										<c:param name="required" value="required" />
										<c:param name="clase" value="campoFormMediano conMargen" />
									</c:import>
								</td>
							</tr>
							<tr>
								<td><fmt:message key="lot.camp.observacions" /></td>
								<td>
									<div id="observacionesForm" class="campoForm <c:out value="${param.required}"/>">
										<c:import url="comu/CampFormulari.jsp">
											<c:param name="tipus" value="textarea" />
											<c:param name="path" value="formData.observacions[${status.count - 1}]" />
											<c:param name="camp" value="observacions_${diposit.id}" />
											<c:param name="name" value="observacions[${status.count - 1}]" />
										</c:import>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			
			<c:import url="comu/CampFormulari.jsp">
				<c:param name="tipus" value="hidden" />
				<c:param name="path" value="formData.dipositId[${status.count - 1}]" />
				<c:param name="camp" value="diposit_${diposit.id}" />
				<c:param name="name" value="dipositId[${status.count - 1}]" />
				<c:param name="value" value="${diposit.id}" />
			</c:import>
			
			<div class="separadorH"></div>
	
		</c:forEach>
		</c:if>
		
		<!-- LOTS -->
		<c:if test="${not empty lots}">
		<h3><fmt:message key="establiment.vista.listadoLotes" /></h3>
		<div class="separadorH"></div>
		
		<c:forEach var="lot" items="${lots}" varStatus="status">
			<div class="cajaTabla">
				<div class="layoutSombraTabla">
					<div class="rellenoInf"></div>
					<div class="rellenoIzq"></div>
					<div class="rellenoDer"></div>
					<div class="supDer"></div>
					<div class="supIzq"></div>
					<div class="infIzq"></div>
					<div class="infDer"></div>
					<table cellpadding="0" cellspacing="0" class="listado665 noRoll">
						<thead>
							<tr>
								<th colspan="2" class="unicoHead"><c:out value=" ${lot.codiAssignat}" />
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><fmt:message key="lot.camp.dataConsum"/></td>
								<td>
									<c:import url="comu/CampFormulari.jsp">
										<c:param name="tipus" value="calendar"/>
										<c:param name="path" value="formData.dataConsum[${status.count - 1}]"/>
										<c:param name="camp" value="campo_dataConsum[${status.count - 1}]" />
										<c:param name="name" value="dataConsum[${status.count - 1}]" />
										<c:param name="maxlength" value="10" />
										<c:param name="required" value="required" />
										<c:param name="aclaracio">
											<fmt:message key="proces.aclaracio.formatdata" />
										</c:param>
										<c:param name="clase" value="conMargen campoForm165" />
									</c:import>
								</td>
							</tr>
							<tr>
								<td><fmt:message key="lot.camp.marca"/></td>
								<td>
									<c:import url="comu/CampFormulari.jsp">
										<c:param name="tipus" value="select" />
										<c:param name="path" value="formData.marcaId[${status.count - 1}]"/>
										<c:param name="required" value="required"/>
										<c:param name="camp" value="campo_marca_${lot.id}"/>
										<c:param name="name" value="marcaId[${status.count - 1}]"/>
										<c:param name="selectItems" value="marcas"/>
										<c:param name="selectItemsId" value="id"/>
										<c:param name="selectItemsValue" value="nom"/>
										<c:param name="selectSelectedValue" value="${formData.marcaId[status.count - 1]}"/>
										<c:param name="clase" value="campoFormGrande conMargen"/>
										<c:param name="onchange" value="canviMarca('${lang}', ${lot.id})"/>
									</c:import> 
								</td>
							</tr>
							<tr>
								<td><fmt:message key="lot.camp.etiquetatge"/></td>
								<td>
									<c:set var="etiquetes" value="${formData.etiquetatges[status.count - 1]}" scope="request" />
									<c:import url="comu/CampFormulari.jsp">
										<c:param name="tipus" value="select" />
										<c:param name="path" value="formData.etiquetatgeId[${status.count - 1}]" />
										<c:param name="required" value="required" />
										<c:param name="camp" value="campo_etiquetatgeId_${lot.id}" />
										<c:param name="name" value="etiquetatgeId[${status.count - 1}]" />
										<c:param name="selectItems" value="etiquetes" />
										<c:param name="selectItemsId" value="id" />
										<c:param name="selectItemsValue" value="nom" />
										<c:param name="selectSelectedValue" value="${formData.etiquetatgeId[status.count - 1]}" />
										<c:param name="clase" value="campoFormGrande" />
										<c:param name="onchange" value="setVolum(${lot.id});" />
									</c:import> 
									<c:import url="comu/CampFormulari.jsp">
										<c:param name="tipus" value="hidden" />
										<c:param name="path" value="formData.volum[${status.count - 1}]"/>
										<c:param name="camp" value="campo_volum_${lot.id}" />
										<c:param name="name" value="volum[${status.count - 1}]" />
										<c:param name="value" value="${formData.volum}" />
									</c:import>
								</td>
							</tr>
							<tr>
								<td><fmt:message key="lot.camp.numeroLot"/></td>
								<td>
									<c:import url="comu/CampFormulari.jsp">
										<c:param name="tipus" value="text" />
										<c:param name="path" value="formData.numeroLot[${status.count - 1}]" />
										<c:param name="camp" value="campo_numeroLot_${lot.id}" />
										<c:param name="name" value="numeroLot[${status.count - 1}]" />
										<c:param name="maxlength" value="256" />
										<c:param name="clase" value="campoFormCompleto" />
									</c:import>
								</td>
							</tr>
							<tr>
								<td><fmt:message key="lot.camp.varietatsOliva"/></td>
								<td>	
									<c:set var="varietats" value="${formData.varietatsId[status.count - 1]}" scope="request" /> 
									<c:import url="comu/CampFormulari.jsp">
										<c:param name="tipus" value="selectMultiple" />
										<c:param name="path" value="formData.varietatsId[${status.count - 1}]" />
										<c:param name="titleIzquierda"
											value="lot.camp.disponibles" />
										<c:param name="titleDerecha"
											value="lot.camp.seleccionats" />
										<c:param name="camp" value="varietatsId_${lot.id}"/>
										<c:param name="name" value="varietatsId[${status.count - 1}]"/>
										<c:param name="clase" value="campoForm" />
										<c:param name="selectTamany" value="5" />
										<c:param name="selectItems" value="varietatsArray" />
										<c:param name="selectItemsId" value="id" />
										<c:param name="selectItemsValue" value="nom" />
										<c:param name="selectSelectedItems" value="varietats"/>
									</c:import>
								</td>
							</tr>
							<tr>
								<td><fmt:message key="proces.elaboracioOli.camp.nomPartida"/></td>
								<td>
									<c:import url="comu/CampFormulari.jsp">
										<c:param name="tipus" value="select" />
										<c:param name="path" value="formData.idPartidaOliLots[${status.count - 1}]" />
										<c:param name="required" value="required" />
										<c:param name="camp" value="campo_partidaOliLots_${lot.id}" />
										<c:param name="name" value="idPartidaOliLots[${status.count - 1}]" />
										<c:param name="selectItems" value="partidesOli" />
										<c:param name="selectItemsId" value="id" />
										<c:param name="selectItemsValue" value="nom" />
										<c:param name="selectSelectedValue" value="${formData.idPartidaOliLots[status.count - 1]}" />
										<c:param name="clase" value="campoFormMediano conMargen" />
										<c:param name="onchange" value="carregarCategoriaLot(${lot.id});" />
									</c:import>
									<c:import url="comu/CampFormulari.jsp">
										<c:param name="tipus" value="text" />
										<c:param name="path" value="formData.nomCategoriaOliLots[${status.count - 1}]" />
										<c:param name="camp" value="nomCategoriaOliLots_${lot.id}" />
										<c:param name="name" value="nomCategoriaOliLots[${status.count - 1}]" />
										<c:param name="required" value="required" />
										<c:param name="maxlength" value="128" />
										<c:param name="clase" value="campoFormGrande readOnly" />
										<c:param name="readonly" value="true" />
									</c:import>
								</td>
							</tr>
							<tr>
								<td><fmt:message key="lot.camp.zona"/></td>
								<td>
									<c:import url="comu/CampFormulari.jsp">
										<c:param name="tipus" value="select" />
										<c:param name="path" value="formData.zonaId[${status.count - 1}]" />
										<c:param name="required" value="required" />
										<c:param name="camp" value="campo_zona_${lot.id}" />
										<c:param name="name" value="zonaId[${status.count - 1}]" />
										<c:param name="selectItems" value="zonas" />
										<c:param name="selectItemsId" value="id" />
										<c:param name="selectItemsValue" value="nom" />
										<c:param name="selectSelectedValue" value="${formData.zonaId[status.count - 1]}" />
										<c:param name="clase" value="campoFormMediano conMargen" />
									</c:import>
								</td>
							</tr>
							<tr>
								<td><fmt:message key="lot.camp.acidesa"/></td>
								<td>
									<c:import url="comu/CampFormulari.jsp">
										<c:param name="tipus" value="text" />
										<c:param name="path" value="formData.acidesaLots[${status.count - 1}]" />
										<c:param name="camp" value="campo_acidesaLots_${lot.id}" />
										<c:param name="name" value="acidesaLots[${status.count - 1}]" />
										<c:param name="required" value="required"/>
										<c:param name="clase" value="campoFormMediano conMargen"/>
									</c:import>
								</td>
							</tr>
							<tr>
								<td><fmt:message key="lot.camp.embotellat"/></td>
								<td class="conversor">
									<fieldset>
										<c:import url="comu/CampFormulari.jsp">
											<c:param name="tipus" value="text"/>
											<c:param name="path" value="formData.numeroBotellesInicials[${status.count - 1}]" />
											<c:param name="title">
												<fmt:message key="lot.camp.botelles.inicials" />
											</c:param>
											<c:param name="camp" value="numeroBotellesInicials_${lot.id}"/>
											<c:param name="name" value="numeroBotellesInicials[${status.count - 1}]" />
											<c:param name="clase" value="campoFormMediano conMargen" />
											<c:param name="onkeyup" value="quantitatEmbotellada(${lot.id})" />
										</c:import>
										<c:if test="${establiment.unitatMesura == 'l'}">
											<div class="conv">
												<c:import url="comu/CampFormulari.jsp">
													<c:param name="tipus" value="text" />
													<c:param name="path" value="formData.litrosInicialsLots[${status.count - 1}]" />
													<c:param name="required" value="required" />
													<c:param name="title">
														<fmt:message key="proces.elaboracioOli.camp.litres" />
													</c:param>
													<c:param name="camp" value="litrosInicialsLots_${lot.id}" />
													<c:param name="name" value="litrosInicialsLots[${status.count - 1}]" />
													<c:param name="clase" value="campoFormMediano readOnly conMargen" />
													<c:param name="readonly" value="true" />
												</c:import>
											</div>
											<div class="conv">
												<c:import url="comu/CampFormulari.jsp">
													<c:param name="tipus" value="text" />
													<c:param name="path" value="formData.kilosInicialsLots[${status.count - 1}]" />
													<c:param name="title">
														<fmt:message key="proces.elaboracioOli.camp.kilos" />
													</c:param>
													<c:param name="camp" value="kilosInicialsLots_${lot.id}" />
													<c:param name="name" value="kilosInicialsLots[${status.count - 1}]" />
													<c:param name="clase" value="campoFormMediano readOnly" />
													<c:param name="readonly" value="true" />
												</c:import>
											</div>
										</c:if> 
										<c:if test="${establiment.unitatMesura == 'k'}">
											<div class="conv">
												<c:import url="comu/CampFormulari.jsp">
													<c:param name="tipus" value="text" />
													<c:param name="path" value="formData.kilosInicialsLots[${status.count - 1}]" />
													<c:param name="required" value="required" />
													<c:param name="title">
														<fmt:message key="proces.elaboracioOli.camp.kilos" />
													</c:param>
													<c:param name="camp" value="kilosInicialsLots_${lot.id}" />
													<c:param name="name" value="kilosInicialsLots[${status.count - 1}]" />
													<c:param name="clase" value="campoFormMediano readOnly conMargen" />
													<c:param name="readonly" value="true" />
												</c:import>
											</div>
											<div class="conv">
												<c:import url="comu/CampFormulari.jsp">
													<c:param name="tipus" value="text" />
													<c:param name="path" value="formData.litrosInicialsLots[${status.count - 1}]" />
													<c:param name="title">
														<fmt:message key="proces.elaboracioOli.camp.litres" />
													</c:param>
													<c:param name="camp" value="litrosInicialsLots_${lot.id}" />
													<c:param name="name" value="litrosInicialsLots[${status.count - 1}]" />
													<c:param name="clase" value="campoFormMediano readOnly" />
													<c:param name="readonly" value="true" />
												</c:import>
											</div>
										</c:if>
									</fieldset>
								</td>
							</tr>
							<tr>
								<td><!--fmt:message key="lot.camp.embotellat"/--></td>
								<td class="conversor">
									<fieldset>
										<c:import url="comu/CampFormulari.jsp">
											<c:param name="tipus" value="text"/>
											<c:param name="path" value="formData.numeroBotellesActuals[${status.count - 1}]" />
											<c:param name="title">
												<fmt:message key="lot.camp.botelles.actuals" />
											</c:param>
											<c:param name="camp" value="numeroBotellesActuals_${lot.id}"/>
											<c:param name="name" value="numeroBotellesActuals[${status.count - 1}]" />
											<c:param name="clase" value="campoFormMediano conMargen" />
											<c:param name="onkeyup" value="quantitatEmbotellada(${lot.id})" />
										</c:import>
										<c:if test="${establiment.unitatMesura == 'l'}">
											<div class="conv">
												<c:import url="comu/CampFormulari.jsp">
													<c:param name="tipus" value="text" />
													<c:param name="path" value="formData.litrosActualsLots[${status.count - 1}]" />
													<c:param name="required" value="required" />
													<c:param name="title">
														<fmt:message key="proces.elaboracioOli.camp.litres" />
													</c:param>
													<c:param name="camp" value="litrosActualsLots_${lot.id}" />
													<c:param name="name" value="litrosActualsLots[${status.count - 1}]" />
													<c:param name="clase" value="campoFormMediano readOnly conMargen" />
													<c:param name="readonly" value="true" />
												</c:import>
											</div>
											<div class="conv">
												<c:import url="comu/CampFormulari.jsp">
													<c:param name="tipus" value="text" />
													<c:param name="path" value="formData.kilosActualsLots[${status.count - 1}]" />
													<c:param name="title">
														<fmt:message key="proces.elaboracioOli.camp.kilos" />
													</c:param>
													<c:param name="camp" value="kilosActualsLots_${lot.id}" />
													<c:param name="name" value="kilosActualsLots[${status.count - 1}]" />
													<c:param name="clase" value="campoFormMediano readOnly" />
													<c:param name="readonly" value="true" />
												</c:import>
											</div>
										</c:if> 
										<c:if test="${establiment.unitatMesura == 'k'}">
											<div class="conv">
												<c:import url="comu/CampFormulari.jsp">
													<c:param name="tipus" value="text" />
													<c:param name="path" value="formData.kilosActualsLots[${status.count - 1}]" />
													<c:param name="required" value="required" />
													<c:param name="title">
														<fmt:message key="proces.elaboracioOli.camp.kilos" />
													</c:param>
													<c:param name="camp" value="kilosActualsLots_${lot.id}" />
													<c:param name="name" value="kilosActualsLots[${status.count - 1}]" />
													<c:param name="clase" value="campoFormMediano readOnly conMargen" />
													<c:param name="readonly" value="true" />
												</c:import>
											</div>
											<div class="conv">
												<c:import url="comu/CampFormulari.jsp">
													<c:param name="tipus" value="text" />
													<c:param name="path" value="formData.litrosActualsLots[${status.count - 1}]" />
													<c:param name="title">
														<fmt:message key="proces.elaboracioOli.camp.litres" />
													</c:param>
													<c:param name="camp" value="litrosActualsLots_${lot.id}" />
													<c:param name="name" value="litrosActualsLots[${status.count - 1}]" />
													<c:param name="clase" value="campoFormMediano readOnly" />
													<c:param name="readonly" value="true" />
												</c:import>
											</div>
										</c:if>
									</fieldset>
								</td>
							</tr>
							
							<%--
							<tr id="dadesEtiquetar_${lot.id}" style="<c:if test="${formData.idCategoriaOli[status.count - 1] != qualificat}">display:none</c:if>">
								<td>
									<c:import url="comu/CampFormulari.jsp">
										<c:param name="tipus" value="checkboxName" />
										<c:param name="path" value="formData.etiquetar[${status.count - 1}]" />
										<c:param name="title">
											<fmt:message key="lot.camp.etiquetar" />
										</c:param>
										<c:param name="camp" value="campo_etiquetar_${lot.id}" />
										<c:param name="name" value="etiquetar[${status.count - 1}]" />
										<c:param name="onclick" value="mostrarRang();" />
									</c:import>
								</td>
								<td class="conversor noPadding"> 
									<div id="dadesRang_${lot.id}" style="display: none">
										<div class="cajaTabla">
											<table cellpadding="0" cellspacing="0" class="listadoEstrecho noRoll" style="width: auto;">
												<tbody>
													<tr>
														<td class="noPadding">
															<c:import url="comu/CampFormulari.jsp">
																<c:param name="tipus" value="text" />
																<c:param name="path" value="formData.etiquetaLletra[${status.count - 1}]" />
																<c:param name="maxlength" value="5" />
																<c:param name="title"><fmt:message key="etiquetar.camp.etiqueta.lletra" /></c:param>
																<c:param name="camp" value="etiquetaLletra[${status.count - 1}]" />
																<c:param name="name" value="etiquetaLletra[${status.count - 1}]" />
																<c:param name="clase" value="campoFormPequeno" />
															</c:import>
															<c:import url="comu/CampFormulari.jsp">
																<c:param name="tipus" value="text" />
																<c:param name="path" value="formData.etiquetaInici[${status.count - 1}]" />
																<c:param name="maxlength" value="10" />
																<c:param name="title"><fmt:message key="etiquetar.camp.etiqueta.inici" /></c:param>
																<c:param name="camp" value="etiquetaInici[${status.count - 1}]" />
																<c:param name="name" value="etiquetaInici[${status.count - 1}]" />
																<c:param name="clase" value="campoFormPequeno" />
															</c:import>
															<c:import url="comu/CampFormulari.jsp">
																<c:param name="tipus" value="text" />
																<c:param name="path" value="formData.quantitat[${status.count - 1}]" />
																<c:param name="maxlength" value="10" />
																<c:param name="title"><fmt:message key="etiquetar.camp.etiqueta.quantitat" /></c:param>
																<c:param name="camp" value="quantitat[${status.count - 1}]" />
																<c:param name="name" value="quantitat[${status.count - 1}]" />
																<c:param name="clase" value="campoFormPequeno" />
																<c:param name="onchange" value="actualitzaLitres(${status.count - 1}); actualitzaEtiqueta(${status.count - 1});" />
															</c:import>
															<c:import url="comu/CampFormulari.jsp">
																<c:param name="tipus" value="text" />
																<c:param name="path" value="formData.etiquetaFi[${status.count - 1}]" />
																<c:param name="maxlength" value="10" />
																<c:param name="title"><fmt:message key="etiquetar.camp.etiqueta.fi" /></c:param>
																<c:param name="camp" value="etiquetaFi[${status.count - 1}]" />
																<c:param name="name" value="etiquetaFi[${status.count - 1}]" />
																<c:param name="clase" value="campoFormPequeno" />
																<c:param name="readonly" value="readonly" />
															</c:import>
														</td>
													</tr>
													<tr>
														<td class="noPadding">
															<c:import url="comu/CampFormulari.jsp">
																<c:param name="tipus" value="select" />
																<c:param name="path" value="formData.etiquetaCapacitat[${status.count - 1}]" />
																<c:param name="title"><fmt:message key="etiquetar.camp.etiqueta.capacitat" /></c:param>
																<c:param name="camp" value="etiquetaCapacitat[${status.count - 1}]" />
																<c:param name="name" value="etiquetaCapacitat[${status.count - 1}]" />
																<c:param name="selectItems" value="capacitats" />
																<c:param name="selectItemsId" value="value" />
																<c:param name="selectItemsValue" value="label" />
																<c:param name="selectSelectedValue" value="${formData.etiquetaCapacitat}" />
																<c:param name="clase" value="campoFormMediano" />
																<c:param name="onchange" value="actualitzaLitres(${status.count - 1});" />
															</c:import>
															<c:import url="comu/CampFormulari.jsp">
																<c:param name="tipus" value="text" />
																<c:param name="path" value="formData.litres[${status.count - 1}]" />
																<c:param name="maxlength" value="10" />
																<c:param name="title"><fmt:message key="etiquetar.camp.litres" /></c:param>
																<c:param name="camp" value="litres[${status.count - 1}]" />
																<c:param name="name" value="litres[${status.count - 1}]" />
																<c:param name="clase" value="campoFormMediano" />
																<c:param name="readonly" value="readonly" />
															</c:import>
														</td>
													</tr>
												</tbody>
											</table>
										</div>
										<div class="separadorH"></div>
									</div>
								</td>
							</tr>
							--%>
							
							
							
							<tr>
								<td><fmt:message key="lot.camp.observacions" /></td>
								<td>
									<div id="observacionesForm" class="campoForm <c:out value="${param.required}"/>">
										<c:import url="comu/CampFormulari.jsp">
											<c:param name="tipus" value="textarea" />
											<c:param name="path" value="formData.observacionsLots[${status.count - 1}]" />
											<c:param name="camp" value="observacionsLots_${lot.id}"/>
											<c:param name="name" value="observacionsLots[${status.count - 1}]" />
										</c:import>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			
			<c:import url="comu/CampFormulari.jsp">
				<c:param name="tipus" value="hidden" />
				<c:param name="path" value="formData.lotId[${status.count - 1}]" />
				<c:param name="camp" value="lot_${lot.id}" />
				<c:param name="name" value="lotId[${status.count - 1}]" />
				<c:param name="value" value="${lot.id}" />
			</c:import>
			
			<div class="separadorH"></div>
	
		</c:forEach>
		</c:if>
		<div class="botonesForm">
			<div id="guardarForm" class="btnCorto"
				onclick="if(confirm('<fmt:message key="manteniment.modificar.confirm"/>')){onsubmitMultiple();submitForm('formulario')}"
				onmouseover="underline('enlaceGuardarForm')"
				onmouseout="underline('enlaceGuardarForm')">
				<a id="enlaceGuardarForm" href="javascript:void(0);">
					<fmt:message key="manteniment.guardar" />
				</a>
			</div>
			<div class="btnCorto" onclick="submitForm('tornarForm')"
				onmouseover="underline('enlaceTornarForm')"
				onmouseout="underline('enlaceTornarForm')">
				<a id="enlaceTornarForm" href="javascript:void(0);">
					<fmt:message key="proces.tornar" />
				</a>
			</div>
		</div>
	
	</form>

	<form id="tornarForm" action="TancamentLlibres.html" class="seguit"/>

	<!-- Colores en tablas -->
	<script type="text/javascript">
		jQuery(document).ready(function(){
			setEstilosTabla(true);
			redibujarError();
		})
	</script>

</body>
</html>