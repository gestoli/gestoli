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
<title><fmt:message key="proces.crearLote.titol" /></title>
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
		var litros = document.getElementById('litresPerduts').value;
		var kilos = litros * 0.916;
		document.getElementById('kilosPerduts').value = kilos.toFixed(3);		
	}
	
	function kilosLitros() {
		var kilos = document.getElementById('kilosPerduts').value;
		var litros = kilos / 0.916;
		document.getElementById('litresPerduts').value = litros.toFixed(3);		
	}
	
	function mostrarPerdues() {
		var check = document.getElementById('campo_perdua');
		if (check.checked == true) {
			document.getElementById('dadesPerdues').style.display = 'block';
		} else {
			document.getElementById('dadesPerdues').style.display = 'none';
		}
	}

	function mostrarRang() {
		if (document.getElementById('campo_etiquetar') != null) {
			var check = document.getElementById('campo_etiquetar');
			if (check.checked) {
				document.getElementById('dadesRang').style.display = 'block';
			} else {
				document.getElementById('dadesRang').style.display = 'none';
			}
		}
	}

	function canviMarca(idioma) {
    	var inputMarca = document.getElementById("campo_marca");
		processosService.etiquetajesConMarca(inputMarca.value, idioma, carregarEtiquetajes);
		document.getElementById("campo_volum").value = "";
		document.getElementById("litresEnvassats").value = 0;
		document.getElementById("kilosEnvassats").value = 0;
	}

	function carregarEtiquetajes(dades) {
		DWRUtil.removeAllOptions("campo_idEtiquetatge");
    	DWRUtil.addOptions("campo_idEtiquetatge", dades, 'id', 'nom');
	}

	function setVolum() {
		var inputEtiquetatge = document.getElementById("campo_idEtiquetatge");
		if (inputEtiquetatge.value != "") {
			processosService.getVolumEtiquetatge(inputEtiquetatge.value, carregaVolum);
		} else {
			document.getElementById("campo_volum").value = "";
			quantitatEmbotellada();
		}
	}

	function carregaVolum(dades) {
		var inputVolum = document.getElementById("campo_volum");
		inputVolum.value = dades;
		
		quantitatEmbotellada();
	}

	function quantitatEmbotellada() {
		var numBotelles = document.getElementById("numeroBotellesInicials").value
		var volum = document.getElementById("campo_volum").value;
		if (numBotelles != "" && volum != "") {
			var quantitat = numBotelles * volum;
			document.getElementById("litresEnvassats").value = parseFloat(quantitat).toFixed(2);
			document.getElementById("kilosEnvassats").value = parseFloat(quantitat * 0.916).toFixed(2);
		} else {
			document.getElementById("litresEnvassats").value = 0;
			document.getElementById("kilosEnvassats").value = 0;
		}
	}

	
// ]]>
</script>

</head>
<body>

	<form id="formulario" name="procesLotForm" action="ProcesLotForm.html" method="post" class="extended seguit" onsubmit="">
		
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="calendar" />
			<c:param name="path" value="formData.data" />
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
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="hidden" />
			<c:param name="path" value="formData.diposit.id" />
			<c:param name="camp" value="diposit.id" />
			<c:param name="name" value="diposit.id" />
			<c:param name="value" value="${deposito.id}" />
		</c:import>

		<div class="separadorH"></div>
	
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
							<th colspan="2" class="unicoHead">
								<c:out value=" ${deposito.codiAssignat}" />
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td class="ancho200"><fmt:message key="lot.camp.acidesa" /></td>
							<td class="boldtd">
								<fmt:formatNumber value="${deposito.acidesa}" maxFractionDigits="2" /> %
							</td>
						</tr>
						<tr>
							<td><fmt:message key="proces.elaboracioOli.camp.nomPartida" /></td>
							<td class="boldtd">${deposito.partidaOli.nom}</td>
						</tr>
						<tr>
							<td><fmt:message key="lot.camp.categoriaOli" /></td>
							<td class="boldtd">
								<fmt:message key="consulta.general.camp.categoriaOli.${deposito.partidaOli.categoriaOli.id}" />
							</td>
						</tr>
						<tr>
							<td><fmt:message key="lot.camp.volumActual" /></td>
							<td class="boldtd">
								<c:choose>
									<c:when test="${deposito.volumActual != null && deposito.volumActual > 0 && deposito.establiment.unitatMesura == 'l'}">
										<fmt:formatNumber value="${deposito.volumActual}" maxFractionDigits="2" /> l
									</c:when>
									<c:when test="${deposito.volumActual != null && deposito.volumActual > 0 && deposito.establiment.unitatMesura == 'k'}">
										<fmt:formatNumber value="${deposito.disponibleOliQuilos}" maxFractionDigits="2" /> kg.
									</c:when>
									<c:otherwise>
										<fmt:message key="lot.camp.dipositBuit" />
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	
	<div class="separadorH"></div>
	
	<div class="etiqueta <c:out value="${param.required}"/><c:if test="${not empty status.errorMessage}"> error</c:if>">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="checkboxName" />
			<c:param name="path" value="formData.perdua" />
			<c:param name="title">
				<fmt:message key="lot.camp.perdua" />
			</c:param>
			<c:param name="camp" value="campo_perdua" />
			<c:param name="name" value="perdua" />
			<c:param name="onclick" value="mostrarPerdues(); setSombraTabla();" />
		</c:import></div>
		
		<div class="separadorH"></div>
		
		<div id="dadesPerdues" style="display: none" class="cajaTabla">
		
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
							<th class="unicoHead"><fmt:message key="lot.camp.perduesInfo" />
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td class="conversor">
								<fieldset>
									<c:if test="${deposito.establiment.unitatMesura == 'l'}">
										<span> 
											<c:import url="comu/CampFormulari.jsp">
												<c:param name="tipus" value="text" />
												<c:param name="path" value="formData.litresPerduts" />
												<c:param name="required" value="required" />
												<%--c:param name="maxlength" value="10"/--%>
												<c:param name="title">
													<fmt:message key="lot.camp.litrosPerdidos" />
												</c:param>
												<c:param name="camp" value="litresPerduts" />
												<c:param name="name" value="litresPerduts" />
												<c:param name="clase" value="campoFormMediano conMargen" />
												<c:param name="onkeyup" value="litrosKilos()" />
											</c:import> 
										</span>
										<span> 
											<c:import url="comu/CampFormulari.jsp">
												<c:param name="tipus" value="text" />
												<c:param name="path" value="formData.kilosPerduts" />
												<c:param name="title">
													<fmt:message key="lot.camp.kilosPerdidos" />
												</c:param>
												<c:param name="camp" value="kilosPerduts" />
												<c:param name="name" value="kilosPerduts" />
												<c:param name="clase" value="campoFormMediano readOnly" />
												<c:param name="readonly" value="true" />
											</c:import> 
										</span>
									</c:if> 
									<c:if test="${deposito.establiment.unitatMesura == 'k'}">
										<span> 
											<c:import url="comu/CampFormulari.jsp">
												<c:param name="tipus" value="text" />
												<c:param name="path" value="formData.kilosPerduts" />
												<c:param name="required" value="required" />
												<c:param name="title">
													<fmt:message key="lot.camp.kilosPerdidos" />
												</c:param>
												<c:param name="camp" value="kilosPerduts" />
												<c:param name="name" value="kilosPerduts" />
												<c:param name="clase" value="campoFormMediano conMargen" />
												<c:param name="onkeyup" value="kilosLitros()" />
											</c:import> 
										</span>
										<span> 
											<c:import url="comu/CampFormulari.jsp">
												<c:param name="tipus" value="text" />
												<c:param name="path" value="formData.litresPerduts" />
												<c:param name="title">
													<fmt:message key="lot.camp.litrosPerdidos" />
												</c:param>
												<c:param name="camp" value="litresPerduts" />
												<c:param name="name" value="litresPerduts" />
												<c:param name="clase" value="campoFormMediano readOnly" />
												<c:param name="readonly" value="true" />
											</c:import> 
										</span>
									</c:if>
								</fieldset>
							</td>
						</tr>
						<tr>
							<td><c:import url="comu/CampFormulari.jsp">
								<c:param name="tipus" value="hidden" />
								<c:param name="path" value="formData.errorObservaciones" />
								<c:param name="camp" value="errorObservaciones" />
							</c:import>
							<fieldset>
							<div id="motiuForm" class="campoForm"><c:import
								url="comu/CampFormulari.jsp">
								<c:param name="tipus" value="textarea" />
								<c:param name="path" value="formData.motiuPerdua" />
								<c:param name="title">
									<fmt:message key="lot.camp.motiuPerdua" />
								</c:param>
								<c:param name="camp" value="motiuPerdua" />
								<c:param name="cols" value="50" />
							</c:import></div>
							</fieldset>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	
	<div class="separadorH"></div>
	
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="select" />
		<c:param name="path" value="formData.idMarca" />
		<c:param name="required" value="required" />
		<c:param name="title">
			<fmt:message key="lot.camp.marca" />
		</c:param>
		<c:param name="camp" value="campo_marca" />
		<c:param name="name" value="idMarca" />
		<c:param name="selectItems" value="marcas" />
		<c:param name="selectItemsId" value="id" />
		<c:param name="selectItemsValue" value="nom" />
		<c:param name="selectSelectedValue" value="${formData.idMarca}" />
		<c:param name="clase" value="campoFormMediano conMargen" />
		<c:param name="onchange" value="canviMarca('${lang}')" />
	</c:import> 
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="select" />
		<c:param name="path" value="formData.idEtiquetatge" />
		<c:param name="required" value="required" />
		<c:param name="title">
			<fmt:message key="lot.camp.etiquetatge" />
		</c:param>
		<c:param name="camp" value="campo_idEtiquetatge" />
		<c:param name="name" value="idEtiquetatge" />
		<c:param name="selectItems" value="etiquetatges" />
		<c:param name="selectItemsId" value="id" />
		<c:param name="selectItemsValue" value="nom" />
		<c:param name="selectSelectedValue" value="${formData.idEtiquetatge}" />
		<c:param name="clase" value="campoFormGrande" />
		<c:param name="onchange" value="setVolum();" />
	</c:import> 
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="hidden" />
		<c:param name="path" value="formData.volum" />
		<c:param name="camp" value="campo_volum" />
		<c:param name="name" value="volum" />
		<c:param name="value" value="" />
	</c:import>
	
	<div class="separadorH"></div>
	
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="select" />
		<c:param name="path" value="formData.idProducte" />
		<c:param name="title">
			<fmt:message key="lot.camp.producte" />
		</c:param>
		<c:param name="camp" value="campo_producte" />
		<c:param name="name" value="idProducte" />
		<c:param name="selectItems" value="productes" />
		<c:param name="selectItemsId" value="id" />
		<c:param name="selectItemsValue" value="nom" />
		<c:param name="selectSelectedValue" value="${formData.idProducte}" />
		<c:param name="clase" value="campoFormMediano conMargen" />
	</c:import>
	
	<div class="separadorH"></div>
	
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.numeroLot" />
		<c:param name="title">
			<fmt:message key="lot.camp.numeroLot" />
		</c:param>
		<c:param name="camp" value="campo_numeroLot" />
		<c:param name="name" value="numeroLot" />
		<c:param name="maxlength" value="256" />
		<c:param name="clase" value="campoFormCompleto" />
	</c:import>
		
	<div class="separadorH"></div>
	
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.lotTap" />
		<c:param name="title">
			<fmt:message key="lot.camp.lotTap" />
		</c:param>
		<c:param name="camp" value="lotTap" />
		<c:param name="name" value="lotTap" />
		<c:param name="maxlength" value="128" />
		<c:param name="clase" value="campoFormGrande" />
	</c:import>
	
	<div class="separadorH"></div>
	
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="calendar" />
		<c:param name="path" value="formData.dataConsum" />
		<c:param name="title">
			<fmt:message key="lot.camp.dataConsum" />
		</c:param>
		<c:param name="camp" value="campo_dataConsum" />
		<c:param name="name" value="dataConsum" />
		<c:param name="maxlength" value="10" />
		<%--c:param name="required" value="required" /--%>
		<c:param name="aclaracio">
			<fmt:message key="proces.aclaracio.formatdata" />
		</c:param>
		<c:param name="clase" value="conMargen campoForm165" />
	</c:import> 
		
	<div class="separadorH"></div>
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
				<th colspan="2" class="unicoHead"><fmt:message
					key="lot.camp.envassarInfo" /></th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td colspan="2"><span> <c:import
					url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="text" />
					<c:param name="path" value="formData.numeroBotellesInicials" />
					<c:param name="title">
						<fmt:message key="lot.camp.numeroBotelles" />
					</c:param>
					<c:param name="camp" value="numeroBotellesInicials" />
					<c:param name="name" value="numeroBotellesInicials" />
					<c:param name="clase" value="campoFormGrande" />
					<c:param name="onkeyup" value="quantitatEmbotellada()" />
				</c:import> </span></td>
			</tr>
			<tr>
				<td class="conversor">
				<fieldset><c:if
					test="${deposito.establiment.unitatMesura == 'l'}">
					<span> <c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="text" />
						<c:param name="path" value="formData.litresEnvassats" />
						<c:param name="title">
							<fmt:message key="lot.camp.litrosEnvasar" />
						</c:param>
						<c:param name="camp" value="litresEnvassats" />
						<c:param name="name" value="litresEnvassats" />
						<c:param name="clase" value="campoFormMediano readOnly conMargen" />
						<c:param name="readonly" value="true" />
					</c:import> </span>
					<span> <c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="text" />
						<c:param name="path" value="formData.kilosEnvassats" />
						<c:param name="title">
							<fmt:message key="lot.camp.kilosEnvasar" />
						</c:param>
						<c:param name="camp" value="kilosEnvassats" />
						<c:param name="name" value="kilosEnvassats" />
						<c:param name="clase" value="campoFormMediano readOnly" />
						<c:param name="readonly" value="true" />
					</c:import> </span>
				</c:if> <c:if test="${deposito.establiment.unitatMesura == 'k'}">
					<span> <c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="text" />
						<c:param name="path" value="formData.kilosEnvassats" />
						<c:param name="title">
							<fmt:message key="lot.camp.kilosEnvasar" />
						</c:param>
						<c:param name="camp" value="kilosEnvassats" />
						<c:param name="name" value="kilosEnvassats" />
						<c:param name="clase" value="campoFormGrande readOnly conMargen" />
						<c:param name="readonly" value="true" />
					</c:import> </span>
					<span> <c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="text" />
						<c:param name="path" value="formData.litresEnvassats" />
						<c:param name="title">
							<fmt:message key="lot.camp.litrosEnvasar" />
						</c:param>
						<c:param name="camp" value="litresEnvassats" />
						<c:param name="name" value="litresEnvassats" />
						<c:param name="clase" value="campoFormGrande readOnly" />
						<c:param name="readonly" value="true" />
					</c:import> </span>
				</c:if></fieldset>
				</td>
			</tr>
		</tbody>
	</table>
	</div>
	</div>
	
	
	<div class="separadorH"></div>
	
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="select" />
		<c:param name="path" value="formData.idZona" />
		<c:param name="required" value="required" />
		<c:param name="title">
			<fmt:message key="lot.camp.zona" />
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
	
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.codiAssignat" />
		<c:param name="title">
			<fmt:message key="lot.camp.nomLot" />
		</c:param>
		<c:param name="camp" value="campo_codiAssignat" />
		<c:param name="name" value="codiAssignat" />
		<c:param name="required" value="required" />
		<c:param name="maxlength" value="128" />
		<c:param name="clase" value="campoFormGrande" />
	</c:import>
	
	<div class="separadorH"></div>
	
	<div id="observacionesForm"
		class="campoForm <c:out value="${param.required}"/><c:if test="${not empty status.errorMessage}"> error</c:if>">
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="textarea" />
		<c:param name="path" value="formData.observacions" />
		<c:param name="title">
			<fmt:message key="lot.camp.observacions" />
		</c:param>
		<c:param name="camp" value="observacions" />
	</c:import></div>
	
	<div class="separadorH"></div>
			
			<div class="etiqueta <c:out value="${param.required}"/><c:if test="${not empty status.errorMessage}"> error</c:if>">
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="checkboxName" />
					<c:param name="path" value="formData.ferEtiquetat" />
					<c:param name="title"><fmt:message key="lot.camp.ferEtiquetat" /></c:param>
					<c:param name="camp" value="campo_ferEtiquetat" />
					<c:param name="name" value="ferEtiquetat" />
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
		type="hidden" id="tipusProces" name="tipusProces" value="7"> <input
		type="hidden" id="pas" name="pas" value="0"></form>
	
	<!-- Colores en tablas -->
	<script type="text/javascript">
		jQuery(document).ready(function(){
			mostrarRang();
			mostrarPerdues();
			setEstilosTabla(true);
			redibujarError();					
		})
	</script>

</body>
</html>