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
<title><fmt:message
	key="tancamentLlibres.introduccioExistencies" /></title>

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

	function mostrarEtiquetar(qualificat) {
		if (document.getElementById('dadesEtiquetar') != null) {
			var etiquetar = document.getElementById('dadesEtiquetar');
			var check = document.getElementById('campo_etiquetar');
			if (document.getElementById('campo_categoriaOli').value == qualificat) {
				etiquetar.style.display = 'block';
			} else {
				etiquetar.style.display = 'none';
				check.checked = false;
				mostrarRang();
			}
		}
	}
	
	function canviMarca(idioma) {
    	var inputMarca = document.getElementById("campo_marca");
		processosService.etiquetajesConMarca(inputMarca.value, idioma, carregarEtiquetajes);
		if(document.getElementById("campo_volum")!= null){
			document.getElementById("campo_volum").value = "";
		}
		if(document.getElementById("litresEnvassats") != null){
			document.getElementById("litresEnvassats").value = 0;
		}
		if(document.getElementById("kilosEnvassats") != null){
			document.getElementById("kilosEnvassats").value = 0;
		}
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
		if (document.getElementById("numeroBotellesInicials") != null && document.getElementById("campo_volum") != null) {
			var numBotelles = document.getElementById("numeroBotellesInicials").value;
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
	}

	function actualitzarPartidesOli(win) {
		var establiment = document.getElementById("establimentId").value;
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
		if (partidaOli.value != ""){
			processosService.tipusPartidaOli(partidaOli.value, nomCategoria);
		} else {
			document.getElementById('nomCategoriaOli').value = "";
		}
	}
	
	function nomCategoria(value){
		var categoria = document.getElementById('nomCategoriaOli');
		if (value == "No DO"){
			categoria.value = msg.noDO;
		} else if (value == "Pendent"){
			categoria.value = msg.pendent;
		} else {
			categoria.value = msg.DO;
		}
	}
	
// ]]>
</script>

</head>
<body>

	<form id="formulario" name="lotForm" action="TancamentLlibresLotForm.html" method="post" class="extended seguit" onsubmit="">
		<input type="hidden" id="establimentId" name="establimentId" value="${establiment.id}"/>

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
		<c:if test="${not empty formData.id}">
			<c:import url="comu/CampFormulari.jsp">
				<c:param name="tipus" value="hidden" />
				<c:param name="path" value="formData.id" />
				<c:param name="camp" value="id" />
				<c:param name="name" value="id" />
				<c:param name="value" value="${formData.id}" />
			</c:import>
		</c:if>
	
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
			<c:param name="clase" value="campoFormCompleto" />
		</c:import>
		
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
			<c:param name="clase" value="campoFormGrande conMargen" />
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
			<c:param name="value" value="${formData.volum}" />
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
		
		<c:set var="varietats" value="${formData.varietatsArray}" scope="request" /> 
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="selectMultiple" />
			<c:param name="path" value="formData.varietatsArray" />
			<c:param name="titleIzquierda"
				value="lot.camp.varietatsOliva.disponibles" />
			<c:param name="titleDerecha"
				value="lot.camp.varietatsOliva.seleccionats" />
			<c:param name="camp" value="varietatsArray" />
			<c:param name="clase" value="campoForm" />
			<c:param name="selectTamany" value="5" />
			<c:param name="selectItems" value="varietatsArray" />
			<c:param name="selectItemsId" value="id" />
			<c:param name="selectItemsValue" value="nom" />
			<c:param name="selectSelectedItems" value="varietats" />
		</c:import>
		
		<div class="separadorH"></div>
		
		<%--c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="select" />
			<c:param name="path" value="formData.idCategoriaOli" />
			<c:param name="required" value="required" />
			<c:param name="title">
				<fmt:message key="lot.camp.categoriaOli" />
			</c:param>
			<c:param name="camp" value="campo_categoriaOli" />
			<c:param name="name" value="idCategoriaOli" />
			<c:param name="selectItems" value="categoriasOli" />
			<c:param name="selectItemsId" value="id" />
			<c:param name="selectItemsValue" value="nom" />
			<c:param name="selectSelectedValue" value="${formData.idCategoriaOli}" />
			<c:param name="clase" value="campoFormMediano conMargen" />
			<c:param name="onchange" value="mostrarEtiquetar(${qualificat});" />
		</c:import--%>
		<c:set var="crearPartida">
			<a href="PartidaOliPopupForm.html?catDO=true" rel="external(720,380)"> 
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
		
		<c:if test="${not empty formData.id}">
		
			<c:import url="comu/CampFormulari.jsp">
				<c:param name="tipus" value="text" />
				<c:param name="path" value="formData.acidesa" />
				<c:param name="title">
					<fmt:message key="lot.camp.acidesa" />
				</c:param>
				<c:param name="camp" value="campo_acidesa" />
				<c:param name="name" value="acidesa" />
				<c:param name="required" value="required" />
				<%--c:param name="maxlength" value="10"/--%>
				<c:param name="clase" value="campoFormMediano conMargen" />
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
									<fmt:message key="lot.camp.envassarInfo" />
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td colspan="2">
									<span> 
										<c:import url="comu/CampFormulari.jsp">
											<c:param name="tipus" value="text" />
											<c:param name="path" value="formData.numeroBotellesInicials" />
											<c:param name="title">
												<fmt:message key="lot.camp.numeroBotelles" />
											</c:param>
											<c:param name="camp" value="numeroBotellesInicials" />
											<c:param name="name" value="numeroBotellesInicials" />
											<c:param name="clase" value="campoFormGrande" />
											<c:param name="onkeyup" value="quantitatEmbotellada()" />
										</c:import> 
									</span>
								</td>
							</tr>
							<tr>
								<td class="conversor">
									<fieldset>
										<c:if test="${establiment.unitatMesura == 'l'}">
											<span> 
												<c:import url="comu/CampFormulari.jsp">
													<c:param name="tipus" value="text" />
													<c:param name="path" value="formData.litresEnvassats" />
													<c:param name="title">
														<fmt:message key="lot.camp.litrosEnvasar" />
													</c:param>
													<c:param name="camp" value="litresEnvassats" />
													<c:param name="name" value="litresEnvassats" />
													<c:param name="clase" value="campoFormMediano readOnly conMargen" />
													<c:param name="readonly" value="true" />
												</c:import> 
											</span>
											<span> 
												<c:import url="comu/CampFormulari.jsp">
													<c:param name="tipus" value="text" />
													<c:param name="path" value="formData.kilosEnvassats" />
													<c:param name="title">
														<fmt:message key="lot.camp.kilosEnvasar" />
													</c:param>
													<c:param name="camp" value="kilosEnvassats" />
													<c:param name="name" value="kilosEnvassats" />
													<c:param name="clase" value="campoFormMediano readOnly" />
													<c:param name="readonly" value="true" />
												</c:import> 
											</span>
										</c:if> 
										<c:if test="${establiment.unitatMesura == 'k'}">
											<span> 
												<c:import url="comu/CampFormulari.jsp">
													<c:param name="tipus" value="text" />
													<c:param name="path" value="formData.kilosEnvassats" />
													<c:param name="title">
														<fmt:message key="lot.camp.kilosEnvasar" />
													</c:param>
													<c:param name="camp" value="kilosEnvassats" />
													<c:param name="name" value="kilosEnvassats" />
													<c:param name="clase" value="campoFormGrande readOnly conMargen" />
													<c:param name="readonly" value="true" />
												</c:import> 
											</span>
											<span> 
												<c:import url="comu/CampFormulari.jsp">
													<c:param name="tipus" value="text" />
													<c:param name="path" value="formData.litresEnvassats" />
													<c:param name="title">
														<fmt:message key="lot.camp.litrosEnvasar" />
													</c:param>
													<c:param name="camp" value="litresEnvassats" />
													<c:param name="name" value="litresEnvassats" />
													<c:param name="clase" value="campoFormGrande readOnly" />
													<c:param name="readonly" value="true" />
												</c:import> 
											</span>
										</c:if>
									</fieldset>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		
			<div class="separadorH"></div>
		
		
		
			<div id="observacionesForm" class="campoForm <c:out value="${param.required}"/><c:if test="${not empty status.errorMessage}"> error</c:if>">
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="textarea" />
					<c:param name="path" value="formData.observacions" />
					<c:param name="title">
						<fmt:message key="lot.camp.observacions" />
					</c:param>
					<c:param name="camp" value="observacions" />
				</c:import>
			</div>
		
		</c:if>
		
		<div class="separadorH"></div>
		
		<div class="botonesForm">
			<c:if test="${not empty formData.id}">
				<div id="guardarForm" class="btnCorto"
					onclick="if(confirm('<fmt:message key="manteniment.modificar.confirm"/>')){onSubmitMultiple_varietatsArray();submitForm('formulario')}"
					onmouseover="underline('enlaceGuardarForm')"
					onmouseout="underline('enlaceGuardarForm')">
					<a id="enlaceGuardarForm" href="javascript:void(0);">
						<fmt:message key="manteniment.guardar" />
					</a>
				</div>
			</c:if> 
			<c:if test="${empty formData.id}">
				<div id="guardarForm" class="btnCorto"
					onclick="onSubmitMultiple_varietatsArray();submitForm('formulario')"
					onmouseover="underline('enlaceGuardarForm')"
					onmouseout="underline('enlaceGuardarForm')">
					<a id="enlaceGuardarForm" href="javascript:void(0);">
						<fmt:message key="manteniment.guardar" />
					</a>
				</div>
			</c:if> 
			<c:if test="${not empty formData.id}">
				<div class="btnCorto" onclick="submitForm('tornarForm')"
					onmouseover="underline('enlaceTornarForm')"
					onmouseout="underline('enlaceTornarForm')">
					<a id="enlaceTornarForm" href="javascript:void(0);">
						<fmt:message key="proces.tornar" />
					</a>
				</div>
			</c:if>
			<div id="cancelarForm" class="btnCorto"
				onmouseover="underline('enlaceCancelarForm')"
				onmouseout="underline('enlaceCancelarForm')"
				onclick="document.location ='ProcesInici.html';">
				<a id="enlaceCancelarForm" href="javascript:void(0);">
					<fmt:message key="proces.cancelar" />
				</a>
			</div>
		</div>
	
	</form>
	
	<form id="tornarForm" action="TancamentLlibres.html" class="seguit"></form>
	
	<!-- Colores en tablas -->
	<script type="text/javascript">
		jQuery(document).ready(function(){
			mostrarRang();
			setEstilosTabla(true);
			redibujarError();
		})
	</script>

</body>
</html>