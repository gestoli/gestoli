<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/fn-rt.tld"%>

<%@ page import="es.caib.gestoli.front.util.*"%>
<%@ page import="java.util.ResourceBundle"%>
<%@ page import="java.util.Locale"%>
<%@ page import="java.util.Collection"%>
<%@ page import="es.caib.gestoli.logic.model.Plantacio"%>

<%
	String lang = Idioma.getLang(request);
	request.setAttribute("lang",lang);
%>

<html>
	<head>
		<title><fmt:message key="proces.entradaOliva.titol" /></title>
		
		<link href="css/jquery.autocomplete.css" rel="stylesheet" type="text/css" />
		<link href="js/calendar/calendar-viti.css" rel="stylesheet" type="text/css" />
		<style type="text/css">
			.campoFormAncho { margin: 0 !important; width: 100% !important; }
		</style>

		<script type="text/javascript" src="/gestoli/dwr/engine.js"></script>
		<script type="text/javascript" src="/gestoli/dwr/util.js"> </script>
		<script type="text/javascript" src="/gestoli/dwr/interface/processosService.js"></script>
		<script type="text/javascript" src="/gestoli/dwr/interface/varietatOlivaService.js"></script>
		
		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript" src="js/jquery.autocomplete.js"></script>

		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/calendar/calendar-setup.js"></script>

<%		if (lang.equalsIgnoreCase("ca")) { %>
			<script type="text/javascript" src="js/calendar/lang/calendar-ca.js"></script>
			<script type="text/javascript" src="js/calendar/lang/calendar-es.js"></script>
<%		} else { %>
			<script type="text/javascript" src="js/calendar/lang/calendar-es.js"></script>
<%		} %>

		<script type="text/javascript" src="js/form.js"></script>
		<script type="text/javascript" src="js/funciones.js"></script>

		<script type="text/javascript" language="javascript">
			// <![CDATA[
			<c:if test="${empty appletRecOli}">
		    	function canviFinca(idioma) {
					processosService.plantacionesConFinca(
							document.getElementById("campo_fincaId").value,
							document.getElementById("campo_olivicultorId").value,
							idioma,
							carregarPlantaciones);
				
					/* var inputMezcla = document.getElementById("campo_mezcla");
					if (!inputMezcla.checked) {
						processosService.variedadesConFinca(
								document.getElementById("campo_fincaId").value,
								document.getElementById("campo_olivicultorId").value,
								carregarVariedades);
					} */
				}
				
				function carregarPlantaciones(dades) {
					DWRUtil.removeAllOptions("campo_plantacioId");
		    		DWRUtil.addOptions("campo_plantacioId", dades, 'id', 'nom');
				}
			
				/* function canviPlantacio() {
					var inputMezcla = document.getElementById("campo_mezcla");
					if (!inputMezcla.checked) {
						processosService.variedadesConPlantacion(
								document.getElementById("campo_fincaId").value,
								document.getElementById("campo_plantacioId").value,
								document.getElementById("campo_olivicultorId").value,
								carregarVariedades);	
					}
				} */
				
				function carregarVariedades(dades) {
					// Primer ocultam totes les varietats per evitar problemes.
					$("fieldset[id^='variedad_']").hide();
					variedadDisponible = dades[0];
					variedadNoDisponible = dades[1];
					for (var i = 0; i < variedadDisponible.length; i++) {
						variedad = document.getElementById("variedad_"+variedadDisponible[i]);
						variedad.style.display = "block";
					}
					for (var i = 0; i < variedadNoDisponible.length; i++) {
						variedad = document.getElementById("variedad_"+variedadNoDisponible[i]);
						variedad.style.display = "none";
					}
				}

				function carregarVarietats() {
					$("div.varietats").hide();
					var $checks = $("input[type='checkbox'][id^='plantacio_']:checked");
					var plantacions = new Array($checks.length);
					var plantacionsIVarietats = new Array($checks.length);
					var i = 0;
					$checks.each(function(){
						var id = $(this).attr("id").replace(/plantacio_/g, "");
						plantacions[i] = id;
						$("#varietats_" + id).show();

						plantacionsIVarietats = id;
						
						i++;
					});
					$("#idsPlantacions").val(plantacions);
					/* if (i > 0) {
						$("div.etiqueta").show();
					} else {
						$("div.etiqueta").hide();
					} */
				}

				
				function autocalcul() {
					var total = 0;
					$("input[id$='produccion']").each( 
					    	function() { 
					    		if (!isNaN(this.value) && this.value!="" && this.value!=null)
					    	  	 	total += parseFloat(this.value); 	        
					    	}
						);
					
					$("#campo_quantitat").val(total)
				}
				
				function calculaPercentatgeMescla() {
					/* 	var quantitat = Number($("#campo_quantitat").val());
					if ($("#campo_mezcla").is(":checked")) {
						if ((quantitat != "") && (quantitat != "0")) {
							var plantacions = $("#idsPlantacions").val();
							varietatOlivaService.calculaPercentatgeMescla(
									quantitat,
									plantacions,
									actualitzaPercentatgeMescla);
						} else {
							$("#campo_mezcla:checked").removeAttr("checked");
							alert("<fmt:message key="error.quantitat.buit" />");
						}
					} else {
						$("input").removeAttr("readonly");
						$("#campo_mezcla:checked").removeAttr("checked");
					}
				}

				function actualitzaPercentatgeMescla(dades) {
					var $checks = $("input[type='checkbox'][id^='plantacio_']");
					var pos = 0;
					var $plantacions = $("#idsPlantacions").val().split(',');
					var index = 0;
					for (var i = 0; i < $plantacions.length; i++) {
						var $inputs = $("#varietats_" + $plantacions[i]).find(".inputText");
						$inputs.each(function(){
							var mescla = dades[index];

							if(mescla[2] == $plantacions[i]){
								$(this).val(mescla[1]);
								index++;
							}
						});
					}
					/*for(var i=0; i<plantacions.length; i++){
						idCheck = $("#idsPlantacions")[i].value;
						//idCheck = $checks[i].value;
						
						for (var j = 0; j < dades.length; j++) {
							var mescla = dades[j];
							
							if(idCheck == mescla[2]){
								var restant = $("#produccioRestant_" + j).val();
								//var $input = $("input[name$='produccion']:eq(" + mescla[0] + ")");
								var $input = $("input[name$='produccion']:eq(" + pos + ")");
								var valor = mescla[1];
								$input.val(valor).attr("readonly", "readonly");
								pos++;
							}	else {
								//pos += mescla[3];
								//break;
							}
						}
					}*/
				}
			</c:if>

			<c:if test="${not empty appletRecOli}">
				function irEntradaOlivicultor() {
					var ent = document.getElementById("entradaSelectId");
					if (ent.value != ""){
						document.location = "ProcesEntradaOlivaTaulaForm.html?tipusProces=0&nif=" + ent.value;
					}
				}
			</c:if>

			<c:if test="${not empty idsPlantacions}">
				$(document).ready(function(){
					$("#idsPlantacions").val("<c:out value="${idsPlantacions}"/>");
					var plantacions = $("#idsPlantacions").val().split(",");
					for (int i = 0; i < plantacions.length; i++) {
						$("#plantacio_" + plantacions[i]).click();
					}
					carregarVarietats();
				});
			</c:if>
			// ]]>
		</script>
		
	</head>

	<body>
		<c:if test="${empty appletRecOli}">
			<div style="width: 495px; margin: auto;">
			    <p><strong><fmt:message key="consulta.entradaOliva.camp.olivicultor" />:</strong> <c:out value="${olivicultor.nom}" /> (RT-<c:out value="${olivicultor.codiDOPOliva}" />)</p>
			    <p><strong><fmt:message key="proces.entradaOliva.camp.produccio.restant"/>:</strong> <c:out value="${totalProduccioRestant}" /></p>
			</div>
			<form id="formulario" name="procesEntradaOlivaForm" action="ProcesEntradaOlivaTaulaForm.html" method="post" class="extended seguit">
				<input type="hidden" value="${olivicultor.id}" name="idOlivicultor" />
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="hidden" />
					<c:param name="path" value="formData.olivicultorId" />
					<c:param name="camp" value="olivicultorSelectId" />
				</c:import>
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="hidden" />
					<c:param name="path" value="formData.olivicultorId" />
					<c:param name="camp" value="campo_olivicultorId" />
					<c:param name="name" value="olivicultorId" />
				</c:import>
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="calendar" />
					<c:param name="path" value="formData.dataExecucio" />
					<c:param name="required" value="required" />
					<c:param name="title"><fmt:message key="proces.entradaOliva.camp.dataexec" /></c:param>
					<c:param name="camp" value="campo_dataExecucio" />
					<c:param name="name" value="dataExecucio" />
					<c:param name="maxlength" value="10" />
					<c:param name="aclaracio"><fmt:message key="proces.aclaracio.formatdata" /></c:param>
					<c:param name="clase" value="conMargen campoForm165" />
				</c:import>
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="text" />
					<c:param name="path" value="formData.hora" />
					<c:param name="required" value="required" />
					<c:param name="title"><fmt:message key="proces.entradaOliva.camp.hora" /></c:param>
					<c:param name="camp" value="campo_hora" />
					<c:param name="name" value="hora" />
					<c:param name="maxlength" value="5" />
					<c:param name="aclaracio"><fmt:message key="proces.aclaracio.hora" /></c:param>
					<c:param name="clase" value="campoFormMediano" />
				</c:import>
				<div class="separadorH"></div>

				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="select" />
					<c:param name="path" value="formData.tipusOlivaTaula" />
					<c:param name="required" value="required" />
					<c:param name="title"><fmt:message key="proces.entradaOlivaTaula.camp.tipus" /></c:param>
					<c:param name="camp" value="campo_estatOliva" />
					<c:param name="name" value="tipusOlivaTaula" />
					<c:param name="selectItems" value="tipusOlivaTaula" />
					<c:param name="selectItemsId" value="id" />
					<c:param name="selectItemsValue" value="nom" />
					<c:param name="selectSelectedValue" value="${formData.tipusOlivaTaula}" />
					<c:param name="clase" value="campoFormGrande" />
				</c:import>
				
				<div class="separadorH"></div>
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="checkbox" />
						<c:param name="path" value="formData.esEcologic" />
						<c:param name="title">
							<fmt:message key="proces.entradaOlivaTaula.camp.ecologic" />
						</c:param>
						<c:param name="camp" value="esEcologic" />
					</c:import>
				
				<div class="separadorH"></div>
				
				<div class="etiqueta conMargen">
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="checkbox" />
						<c:param name="path" value="formData.envasPetit" />
						<c:param name="title"><fmt:message key="proces.entradaOlivaTaula.camp.envas.petit" /></c:param>
						<c:param name="camp" value="envasPetit" />
					</c:import>
				</div>

				<div class="etiqueta conMargen">
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="checkbox" />
						<c:param name="path" value="formData.envasRigid" />
						<c:param name="title"><fmt:message key="proces.entradaOlivaTaula.camp.envas.rigid" /></c:param>
						<c:param name="camp" value="envasRigid" />
					</c:import>
				</div>

				<div class="etiqueta">
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="checkbox" />
						<c:param name="path" value="formData.envasVentilat" />
						<c:param name="title"><fmt:message key="proces.entradaOlivaTaula.camp.envas.ventilat" /></c:param>
						<c:param name="camp" value="envasVentilat" />
					</c:import>
				</div>
				<div class="separadorH"></div>
				
				<div id="mensajeError" class="mensajeErrorOff"></div>
				<div class="separadorH"></div>

				<display:table name="plantacions" id="plantacio" requestURI="replaceURI" sort="list" cellpadding="0" cellspacing="0" class="listadoEstrecho selectable">
					<display:column>
						<c:set var="trobat" value="${false}" />
						
						<c:if test="${not empty plantacionsSeleccionades}">
							<c:forEach items="${plantacionsSeleccionades}" var="plantacioSeleccionada">
								<c:if test="${plantacio.id == plantacioSeleccionada}">
									<input type="checkbox" value="${plantacio.id}" name="plantacio_${plantacio.id}" id="plantacio_${plantacio.id}" checked="checked" onclick="carregarVarietats();" />
									<c:set var="trobat" value="${true}" />
								</c:if>
							</c:forEach>
						</c:if>
						
						<c:if test="${not trobat}">
							<input type="checkbox" value="${plantacio.id}" name="plantacio_${plantacio.id}" id="plantacio_${plantacio.id}" onclick="carregarVarietats();" />
						</c:if>
						
					</display:column>
					<display:column titleKey="proces.entradaOliva.camp.fincas" sortProperty="finca.nom">
						<c:out value="${plantacio.finca.nom}" />
					</display:column>
					<display:column titleKey="proces.entradaOliva.camp.plantacio">
						<c:out value="${plantacio.nomComplet}" />
					</display:column>
				</display:table>
				
				
				
				<div class="separadorH"></div>
				
				<!--  Ocultem el botó de mostrar varietats ja que ho refrescarem a l'onclick del checkbox -->
				<!-- 
				<br/>
				<div id="formVarietats" class="btnLargo"
						onclick="carregarVarietats();"
						onmouseover="underline('enlaceformVarietats')"
						onmouseout="underline('enlaceformVarietats')">
					<a id="enlaceformVarietats" href="javascript:void(0);">
						<fmt:message key="proces.entradaOliva.mostrar.varietats" />
					</a>
				</div>
				<div class="separadorH"></div>
				 -->
				 
				<br/>
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="hidden" />
					<c:param name="path" value="formData.idsPlantacions" />
					<c:param name="camp" value="idsPlantacions" />
					<c:param name="name" value="idsPlantacions" />
				</c:import>
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="hidden" />
					<c:param name="path" value="formData.idsPlantacionsIVarietats" />
					<c:param name="camp" value="idsPlantacionsIVarietats" />
					<c:param name="name" value="idsPlantacionsIVarietats" />
				</c:import>
				<c:set var="total" value="0" />
				<c:forEach items="${plantacions}" var="item" varStatus="status">
					<div class="varietats" id="varietats_${item.id}" style="display: none;">
						<br/>
						<p class="variedadesOliva"><fmt:message key="proces.entradaOliva.camp.plantacio"/> <c:out value="${item.nomComplet}" /></p>
						<display:table name="varietatsPlantacions_${status.count - 1}" id="varietat" requestURI="" sort="list" cellpadding="0" cellspacing="0" class="listadoEstrecho selectable taulaPlantacio${item.id}">
								<display:column titleKey="proces.entradaOliva.camp.variedades" sortable="false" sortProperty="registre.nomVarietat">
									<c:choose>
										<c:when test="${not empty varietat[0].nomVarietat}"><c:out value="${varietat[0].nomVarietat}" /></c:when>
										<c:otherwise><c:out value="${varietat[0].nom}" /></c:otherwise>
									</c:choose>
								</display:column>
								<display:column titleKey="proces.entradaOliva.camp.kilos" sortable="false">
									<input type="hidden" name="produccioRestant_${total + varietat_rowNum - 1}" id="produccioRestant_${total + varietat_rowNum - 1}" value="${varietat[1]}" />
									<c:import url="comu/CampFormulari.jsp">
										<c:param name="tipus" value="hidden" />
										<c:param name="path" value="formData.variedades[${total + varietat_rowNum - 1}].id" />
										<c:param name="value" value="${varietat[0].id}" />
										<c:param name="camp" value="variedades[${total + varietat_rowNum - 1}].id" />
									</c:import>
									<c:import url="comu/CampFormulari.jsp">
										<c:param name="tipus" value="hidden" />
										<c:param name="path" value="formData.variedades[${total + varietat_rowNum - 1}].olivaTaula" />
										<c:param name="value" value="${varietat[0].olivaTaula}" />
										<c:param name="camp" value="variedades[${total + varietat_rowNum - 1}].olivaTaula" />
									</c:import>
									<c:import url="comu/CampFormulari.jsp">
										<c:param name="tipus" value="hidden" />
										<c:param name="path" value="formData.variedades[${total + varietat_rowNum - 1}].idPlantacio" />
										<c:param name="value" value="${item.id}" />
										<c:param name="camp" value="variedades[${total + varietat_rowNum - 1}].idPlantacio" />
									</c:import>
									<c:import url="comu/CampFormulari.jsp">
										<c:param name="tipus" value="text" />
										<c:param name="path" value="formData.variedades[${total + varietat_rowNum - 1}].produccion" />
										<c:param name="required" value="required" />
										<c:param name="clase" value="campoFormAncho campoFormGrande conMargen" />
										<c:param name="camp" value="variedades[${total + varietat_rowNum - 1}].produccion" />
										<c:param name="onchange" value="autocalcul()"/>
										<c:param name="aclaracio">
											<fmt:message key="proces.entradaOliva.camp.produccio.restant"/> <c:out value="${varietat[1]}" />
										</c:param>
									</c:import>
								</display:column>
						</display:table>
<%
						int total = Integer.parseInt("" + pageContext.getAttribute("total"));
						Plantacio p = (Plantacio)pageContext.getAttribute("item");
						int tamany = p.getDescomposicioPlantacios().size();
						pageContext.setAttribute("final", "" + (total + tamany));
%>
						<c:set var="total" value="${final}" />
						<div class="separadorH"></div>
					</div>
				</c:forEach>
				
				<div id="caixaVarietats" style="margin-top: 25px;">
					<table id="taulaVarietats" style="display: none;" class="listadoEstrecho" cellpadding="0" cellspacing="0">
						<tr>
							<th><fmt:message key="proces.entradaOliva.camp.variedades" /></th>
							<th><fmt:message key="proces.entradaOliva.camp.kilos" /></th>
						</tr>
					</table>
					<div class="separadorH"></div>
				</div>
				
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="text" />
					<c:param name="path" value="formData.quantitat" />
					<c:param name="required" value="required" />
					<c:param name="title"><fmt:message key="proces.entradaOliva.camp.quantitatTotal" /></c:param>
					<c:param name="camp" value="campo_quantitat" />
					<c:param name="name" value="quantitat" />
					<c:param name="readonly" value="true" />
					<c:param name="clase" value="campoFormGrande conMargen" />
				</c:import>
				
				<div class="separadorH"></div>
				
				<div id="mensajeReparto" class="mensajeRepartoOff">
					<p><fmt:message key="proces.entradaOliva.mensajeReparto" /></p>
				</div>
				<div class="separadorH"></div>

				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="select" />
					<c:param name="path" value="formData.zonaId" />
					<c:param name="required" value="required" />
					<c:param name="title"><fmt:message key="proces.entradaOliva.camp.zona" /></c:param>
					<c:param name="camp" value="campo_zonaId" />
					<c:param name="name" value="zonaId" />
					<c:param name="selectItems" value="zonasActivasEst" />
					<c:param name="selectItemsId" value="id" />
					<c:param name="selectItemsValue" value="nom" />
					<c:param name="selectSelectedValue" value="${formData.zonaId}" />
					<c:param name="clase" value="campoFormGenerico80" />
				</c:import>
				<div class="separadorH"></div>

				<div class="botonesForm">
				<div id="guardarForm" class="btnCorto"
						onclick="if(confirm('<fmt:message key="manteniment.confirmar"/>')){submitForm('formulario');}"
						onmouseover="underline('enlaceGuardarForm')"
						onmouseout="underline('enlaceGuardarForm')">
					<a id="enlaceGuardarForm" href="javascript:void(0);">
						<fmt:message key="manteniment.aceptar" />
					</a>
				</div>
			</div>
		</form>
		<div class="separacio"></div>
	</c:if>

	<c:if test="${not empty appletRecOli}">
		<div id="apprfid">
			<c:if test="${empty MSIE}">
				<object type="application/x-java-applet" classid="java:es.caib.gestoli.front.applet.rfid.RfidEntradaOliva" height="200" width="200">
					<param name="codebase" value="./applet/" />
					<param name="idioma" value="<c:out value="${lang}"/>" valuetype="data" />
					<param name="verbose" value="1" valuetype="data" />
				</object>
			</c:if>
			<c:if test="${not empty MSIE}">
				<OBJECT classid="clsid:CAFEEFAC-0016-0000-FFFF-ABCDEFFEDCBA" width="200" height="200">
					<PARAM name="code" value="es.caib.gestoli.front.applet.rfid.RfidEntradaOliva" />
					<PARAM name="codebase" value="./applet/" />
					<PARAM name="idioma" value="<c:out value="${lang}"/>" valuetype="data"/>
					<PARAM name="verbose" value="1" valuetype="data"/>
				</OBJECT>
			</c:if>

			<form id="formulario" name="procesEntradaOlivaForm" action="ProcesEntradaOlivaTaulaForm.html" method="post" class="extended seguit" onsubmit="irEntradaOlivicultor(); return false;">
				<div class="separadorH"></div>
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="hidden" />
						<c:param name="path" value="formData.olivicultorId" />
						<c:param name="camp" value="olivicultorSelectId" />
					</c:import>
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="text" />
						<c:param name="path" value="formData.entrada" />
						<c:param name="required" value="required" />
						<c:param name="title"><fmt:message key="consulta.entradaOliva.camp.olivicultor" /></c:param>
						<c:param name="camp" value="entradaSelectId" />
						<c:param name="clase" value="campoFormGrande conMargen" />
					</c:import>
					<div class="separadorH"></div>

					<div class="botonesForm">
						<div id="enviarFormApp" class="btnCorto"
								onclick="irEntradaOlivicultor();"
								onmouseover="underline('enlaceEnviarFormApp')"
								onmouseout="underline('enlaceEnviarFormApp')">
							<a id="enlaceEnviarFormApp" href="javascript:void(0);">
								<fmt:message key="manteniment.aceptar" />
							</a>
						</div>
					</div>
				</form>
			</div>
		</c:if>
		<%-- Colores en tablas --%>
		<script type="text/javascript">
			$(document).ready(function(){
				carregarVarietats();
			})
		</script>
	</body>
</html>
