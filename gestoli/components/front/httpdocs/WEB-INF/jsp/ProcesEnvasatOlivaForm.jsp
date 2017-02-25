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
<title><fmt:message key="proces.crearLote.olivaTaula.titol" /> <c:if test="${bota.elaboracio.esEcologic}"><fmt:message key="proces.crearLote.olivaTaula.titol" /></c:if></title>
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
		} else {
			document.getElementById("litresEnvassats").value = 0;
		}
	}
	
	function comprovarPh() {
		var ph = $("#campo_pH1").val();
		if (ph>4.3) {
			$("#acid").show(); 
		} else $("#acid").hide(); 
		setEstilosTabla(true);
	};

	
// ]]>
</script>

</head>
<body>

	<form id="formulario" name="procesEnvasatOlivaForm" action="ProcesEnvasatOlivaForm.html" method="post" class="extended seguit" onsubmit="">
		
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
			<c:param name="path" value="formData.bota.id" />
			<c:param name="camp" value="bota.id" />
			<c:param name="name" value="bota.id" />
			<c:param name="value" value="${bota.id}" />
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
								<c:out value=" ${bota.identificador}" />
							</th>
						</tr>
					</thead>
					<tbody>
						<%-- <tr>
							<td class="ancho200"><fmt:message key="lot.camp.acidesa" /></td>
							<td class="boldtd">
								<fmt:formatNumber value="${deposito.acidesa}" maxFractionDigits="2" /> %
							</td>
						</tr> --%>
						<tr>
							<td><fmt:message key="proces.envasatOliva.camp.tipusOliva" /></td>
							<td class="boldtd">
								<c:if test="${bota.tipusOlivaTaula==0}">
									<fmt:message key="proces.envasatOliva.camp.tipusOliva.verda" />
									<c:if test="${bota.elaboracio.esEcologic}">
										<fmt:message key="proces.envasatOliva.camp.tipusOliva.ecologic" />
									</c:if>
								</c:if>
								
								<c:if test="${bota.tipusOlivaTaula==1}">
									<fmt:message key="proces.envasatOliva.camp.tipusOliva.trencada" />
									<c:if test="${bota.elaboracio.esEcologic}">
										<fmt:message key="proces.envasatOliva.camp.tipusOliva.ecologic" />
									</c:if>
								</c:if>
								
								<c:if test="${bota.tipusOlivaTaula==2}">
									<fmt:message key="proces.envasatOliva.camp.tipusOliva.negra" />
									<c:if test="${bota.elaboracio.esEcologic}">
										<fmt:message key="proces.envasatOliva.camp.tipusOliva.ecologic" />
									</c:if>
								</c:if>
								
							</td>
						</tr>
						<tr>
							<td><fmt:message key="proces.envasatOliva.camp.kgInicials" /></td>
							<td class="boldtd">
								${bota.kgOliva}
							</td>
						</tr>
						<tr>
							<td><fmt:message key="proces.envasatOliva.camp.kgRestants" /></td>
							<td class="boldtd">
								${bota.kgOlivaRestant}
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		
		<div class="separadorH"></div>
	
		<c:if test="${bota.tipusOlivaTaula==2}">
			<c:if test="${not empty deposito.id}">
				<div class="cajaTabla">
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="hidden" />
						<c:param name="path" value="formData.diposit.id" />
						<c:param name="camp" value="diposit.id" />
						<c:param name="name" value="diposit.id" />
						<c:param name="value" value="${deposito.id}" />
					</c:import>
			
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
		 </c:if>
		 <c:if test="${empty deposito.id}">
		 	<c:import url="comu/CampFormulari.jsp">
				<c:param name="tipus" value="text" />
				<c:param name="path" value="formData.lotOliAfegit" />
				<c:param name="title">
					<fmt:message key="proces.envasatOliva.camp.lotOli" />
				</c:param>
				<c:param name="camp" value="campo_lotOliAfegit" />
				<c:param name="name" value="lotOliAfegit" />
				<c:param name="maxlength" value="256" />
				<c:param name="clase" value="campoFormCompleto" />
			</c:import>
		 </c:if>
	</c:if>
	
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
	
<%-- 	<div class="separadorH"></div>
	
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
	</c:import> --%>
	
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
						<fmt:message key="proces.envasatOliva.camp.pots" />
					</c:param>
					<c:param name="camp" value="numeroBotellesInicials" />
					<c:param name="name" value="numeroBotellesInicials" />
					<c:param name="clase" value="campoFormGrande" />
					<c:param name="onkeyup" value="quantitatEmbotellada()" />
				</c:import> </span></td>
			</tr>
			<tr>
				<td class="conversor">
					<fieldset>
						<span> <c:import url="comu/CampFormulari.jsp">
							<c:param name="tipus" value="text" />
							<c:param name="path" value="formData.litresEnvassats" />
							<c:param name="title">
								<fmt:message key="proces.envasatOliva.camp.kilos.nets" />
							</c:param>
							<c:param name="camp" value="litresEnvassats" />
							<c:param name="name" value="litresEnvassats" />
							<c:param name="clase" value="campoFormMediano readOnly conMargen" />
							<c:param name="readonly" value="true" />
						</c:import> </span>
						<span> <c:import url="comu/CampFormulari.jsp">
							<c:param name="tipus" value="text" />
							<c:param name="path" value="formData.kgOlivaTaula" />
							<c:param name="title">
								<fmt:message key="proces.envasatOliva.camp.kilos.oliva" />
							</c:param>
							<c:param name="camp" value="kgOlivaTaula" />
							<c:param name="name" value="kgOlivaTaula" />
							<c:param name="clase" value="campoFormMediano" />
						</c:import> </span>
						<c:if test="${bota.tipusOlivaTaula==2}"><span> <c:import url="comu/CampFormulari.jsp">
							<c:param name="tipus" value="text" />
							<c:param name="path" value="formData.totalOliAfegit" />
							<c:param name="title">
								<fmt:message key="proces.envasatOliva.camp.litresOli"/>
							</c:param>
							<c:param name="camp" value="totalOliAfegit" />
							<c:param name="name" value="totalOliAfegit" />
							<c:param name="clase" value="campoFormMediano" />
						</c:import> </span></c:if>
					</fieldset>
				</td>
			</tr>
			<tr>
				<td>
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="text" />
						<c:param name="path" value="formData.pH1" />
						<c:param name="title">
							Comprovació pH
						</c:param>
						<c:param name="camp" value="campo_pH1" />
						<c:param name="name" value="pH1" />
						<c:param name="required" value="required" />
						<c:param name="maxlength" value="128" />
						<c:param name="clase" value="campoFormMediano" />
						<c:param name="onchange" value="comprovarPh();"/>
					</c:import>
					
					<div class="separadorH"></div>
					
					<div id="acid" style="display: none">
						<c:import url="comu/CampFormulari.jsp">
							<c:param name="tipus" value="text" />
							<c:param name="path" value="formData.quantitatAcidCitric" />
							<c:param name="title">
								Àcid cítric (ml)
							</c:param>
							<c:param name="camp" value="campo_quantitatAcidCitric" />
							<c:param name="name" value="quantitatAcidCitric" />
							<c:param name="required" value="required" />
							<c:param name="maxlength" value="128" />
							<c:param name="clase" value="campoFormMediano" />
						</c:import>
						
						<c:import url="comu/CampFormulari.jsp">
							<c:param name="tipus" value="text" />
							<c:param name="path" value="formData.lotAcidCitric" />
							<c:param name="title">
								Lot àcid cítric
							</c:param>
							<c:param name="camp" value="campo_lotAcidCitric" />
							<c:param name="name" value="lotAcidCitric" />
							<c:param name="required" value="required" />
							<c:param name="maxlength" value="128" />
							<c:param name="clase" value="campoFormMediano" />
						</c:import>
						
						<c:import url="comu/CampFormulari.jsp">
							<c:param name="tipus" value="text" />
							<c:param name="path" value="formData.pH2" />
							<c:param name="title">
								Comprovació final pH
							</c:param>
							<c:param name="camp" value="campo_pH2" />
							<c:param name="name" value="pH2" />
							<c:param name="required" value="required" />
							<c:param name="maxlength" value="128" />
							<c:param name="clase" value="campoFormMediano" />
						</c:import>
					</div>
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
			
			<%--div class="etiqueta <c:out value="${param.required}"/><c:if test="${not empty status.errorMessage}"> error</c:if>">
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="checkboxName" />
					<c:param name="path" value="formData.ferEtiquetat" />
					<c:param name="title"><fmt:message key="lot.camp.ferEtiquetat" /></c:param>
					<c:param name="camp" value="campo_ferEtiquetat" />
					<c:param name="name" value="ferEtiquetat" />
				</c:import>
			</div--%>
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
		type="hidden" id="tipusProces" name="tipusProces" value="19"> <input
		type="hidden" id="pas" name="pas" value="1"></form>
	
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