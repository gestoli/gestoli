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
	request.setAttribute("lang", lang);
%>
<% String establiment = request.getParameter("establimentId"); %>
<html>
<head>
<title><fmt:message key="proces.elaboracioOli.titol" /><if test="${partida.esEcologic}"> <fmt:message key="proces.elaboracioOliva.camp.ecologic" /></if></title>
<script type="text/javascript" src="dwr/interface/contenidorService.js"></script>
<script type="text/javascript" src="dwr/interface/processosService.js"></script>
<script type="text/javascript" src="dwr/engine.js"></script>
<script type="text/javascript" src="dwr/util.js"> </script>

<script type="text/javascript" src="js/calendar/calendar.js"></script>
<script type="text/javascript" src="js/calendar/calendar-setup.js"></script>
<%
	if (lang.equalsIgnoreCase("ca")) {
%>
<script type="text/javascript" src="js/calendar/lang/calendar-ca.js"></script>
<script type="text/javascript" src="js/calendar/lang/calendar-es.js"></script>
<%
	} else {
%>
<script type="text/javascript" src="js/calendar/lang/calendar-es.js"></script>
<%
	}
%>
<link href="js/calendar/calendar-viti.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/form.js"></script>
<script type="text/javascript" src="js/funciones.js"></script>

<script>
    var avis = avis || {}; // play well with other jsps on the page
    avis = {
        noDO: "<fmt:message key="proces.elaboracioOli.avis.noDo"/>",
        noPendent: "<fmt:message key="proces.elaboracioOli.avis.noPendent"/>"
    };
    var msg = msg || {};
    msg = {
		noDO: "<fmt:message key="consulta.general.camp.categoriaOli.1"/>",
		pendent: "<fmt:message key="consulta.general.camp.categoriaOli.2"/>",
		DO: "<fmt:message key="consulta.general.camp.categoriaOli.3"/>"
    };

    function guardarFormulari(){
   		$("#campo_kgBota").removeAttr("disabled");
    	var form = document.getElementById("formulario");
    	form.submit();
    }
	
	function campsVisibles() {
		$("#campo_tipusOlivaTaula option[value='']").remove();
		var tipus = $("#campo_tipusOlivaTaula").val();
		if (tipus==2) {
			$("#campo_tipusOlivaTaula option[value=0]").remove();
			$("#campo_tipusOlivaTaula option[value=1]").remove();
		} else $("#campo_tipusOlivaTaula option[value=2]").remove();

		if (tipus==1) {
			$("#additiusOlivaTrencada").show(); 
		} else $("#additiusOlivaTrencada").hide(); 
	};
	
	function mostrarCamp(propi) {
		if (propi) {
			$("#partidaFonoll").show();
			$("#lotFonoll").hide(); 
		} else {
			$("#lotFonoll").show();
			$("#partidaFonoll").hide(); 
		}
	};

	function checkKilos(pos) {
		var imaxKilos = $("#max_" + pos)
		var iKilos = $("#campo_kgOliva_" + pos)
		var iCriba = $("#campo_kgCriba_" + pos)
		var qmaxKilos = parseFloat(imaxKilos.val());
		var qKilos = parseFloat(iKilos.val());
		var qCriba = parseFloat(iCriba.val());
		var esKilos = !isNaN(qKilos);
		var esCriba = !isNaN(qCriba);
		
		if (!esKilos && !esCriba) {
			iKilos.val(0.0);
			iCriba.val(0.0);
		} else if (!esKilos) {
			iKilos.val(qCriba);
		} else if (qKilos > qmaxKilos) {
			iKilos.val(qmaxKilos);
		} else if (esCriba && qKilos < qCriba) {
			iCriba.val(qKilos);
		}
		var total = 0.0;
		$('form input[name^=kgCriba]').each(function() {
			var q = parseFloat($(this).val());
			if (!isNaN(q)) {
				total += q;
			}
		});
		$("#campo_kgBota").val(total);
	}

	function checkCriba(pos) {
		var imaxKilos = $("#max_" + pos)
		var iKilos = $("#campo_kgOliva_" + pos)
		var iCriba = $("#campo_kgCriba_" + pos)
		var qmaxKilos = parseFloat(imaxKilos.val());
		var qKilos = parseFloat(iKilos.val());
		var qCriba = parseFloat(iCriba.val());
		var esKilos = !isNaN(qKilos);
		var esCriba = !isNaN(qCriba);
		
		if (!esCriba && !esKilos) {
			iKilos.val(0.0);
			iCriba.val(0.0);
		} else if (!esCriba) {
			iCriba.val(qKilos);
		} else if (!esKilos) {
			if (qCriba > qmaxKilos) {
				iCriba.val(qmaxKilos);
				iKilos.val(qmaxKilos);
			} else {
				iKilos.val(qCriba);
			}
		} else if (qCriba > qKilos && qCriba <= qmaxKilos) {
			iKilos.val(qCriba);
		} else if (qCriba > qKilos && qCriba > qmaxKilos) {
			iKilos.val(qmaxKilos);
			iCriba.val(qmaxKilos);
		}

		var total = 0.0;
		$('form input[name^=kgCriba]').each(function() {
			var q = parseFloat($(this).val());
			if (!isNaN(q)) {
				total += q;
			}
		});
		$("#campo_kgBota").val(total);
	}
	
	window.onload=function(){
		if (document.getElementById("fonollPropi").checked) {
			$("#partidaFonoll").show();
			$("#lotFonoll").hide(); 
		}
		//comprovarPh();
	};
</script>


</head>
<body>

	<form id="formulario" name="procesElaboracioOliForm" action="ProcesElaboracioOlivaForm.html" method="post" class="extended seguit" onsubmit="">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="calendar" />
			<c:param name="path" value="formData.data" />
			<c:param name="title"><fmt:message key="proces.elaboracioOliva.camp.dataElaboracio" /></c:param>
			<c:param name="camp" value="campo_dataElaboracio" />
			<c:param name="name" value="data" />
			<c:param name="maxlength" value="10" />
			<c:param name="required" value="required" />
			<c:param name="aclaracio"><fmt:message key="proces.aclaracio.formatdata" /></c:param>
			<c:param name="clase" value="conMargen campoForm165" />
		</c:import>
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="select" />
			<c:param name="path" value="formData.tipusOlivaTaula" />
			<c:param name="required" value="required" />
			<c:param name="title"><fmt:message key="proces.entradaOlivaTaula.camp.tipus" /></c:param>
			<c:param name="camp" value="campo_tipusOlivaTaula" />
			<c:param name="name" value="tipusOlivaTaula" />
			<c:param name="selectItems" value="tipusOlivaTaula" />
			<c:param name="selectItemsId" value="id" />
			<c:param name="selectItemsValue" value="nom" />
			<c:param name="selectSelectedValue" value="${formData.tipusOlivaTaula}" />
			<c:param name="clase" value="campoFormGrande" />
			<c:param name="onchange" value="campsVisibles()" />
		</c:import>
		
		<div class="separadorH"></div>
		
		<div class=" bloqueTablas">
			<c:forEach var="partida" items="${origen}" varStatus="status">
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
										<fmt:message key="proces.elaboracioOli.partida"/><c:out value="${status.count}: ${partida.olivicultor.nom}"/>
									</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td class="ancho200"><fmt:message
										key="proces.elaboracioOli.camp.dataPartida" /></td>
									<td class="boldtd"><c:out value="${partida.dataFormat}" /></td>
								</tr>
								<tr>
									<td><fmt:message key="proces.elaboracioOli.camp.hora" /></td>
									<td class="boldtd"><c:out value="${partida.hora}" /></td>
								</tr>
								<tr>
									<td><fmt:message key="proces.elaboracioOli.camp.numeroEntrada" /></td>
									<td class="boldtd"><c:out value="${partida.numeroEntrada}" /></td>
								</tr>
								<tr>
									<td><fmt:message key="proces.elaboracioOli.camp.quantitatTotal" /></td>
									<td class="boldtd"><c:out value="${partida.quantitat}" /> Kgs.</td>
								</tr>
								<tr>
									<td><fmt:message key="proces.elaboracioOli.camp.descomposicio" /></td>
									<td class="boldtd"><c:out value="${partida.varietatsQuilos}" escapeXml="false" /><if test="${partida.esEcologic}"> <fmt:message key="proces.elaboracioOliva.camp.ecologic" /></if></td>
								</tr>
					
							</tbody>
						</table>
						<c:import url="comu/CampFormulari.jsp">
							<c:param name="tipus" value="hidden" />
							<c:param name="path" value="formData.partides[${status.count - 1}].id" />
							<c:param name="camp" value="partida_${partides.id}" />
							<c:param name="name" value="partides[${status.count - 1}].id" />
							<c:param name="value" value="${partida.id}" />
						</c:import>
						<input type="hidden" id="max_${status.count - 1}" name="max_${status.count - 1}" value="${partida.quantitat}">
					</div>
				</div>
				
				<div class="separadorH"></div>
				
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="text" />
					<c:param name="path" value="formData.kgOliva[${status.count - 1}]" />
					<c:param name="title"><fmt:message key="proces.elaboracioOliva.camp.kgOliva"/></c:param>
					<c:param name="camp" value="campo_kgOliva_${status.count - 1}" />
					<c:param name="name" value="kgOliva[${status.count - 1}]" />
					<c:param name="required" value="required" />
					<c:param name="maxlength" value="128" />
					<c:param name="clase" value="campoFormMediano" />
					<c:param name="onchange" value="checkKilos(${status.count - 1})" />
				</c:import>
				
				<div class="separadorV90"></div>

				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="text" />
					<c:param name="path" value="formData.kgCriba[${status.count - 1}]" />
					<c:param name="title"><fmt:message key="proces.elaboracioOliva.camp.kgCriba"/></c:param>
					<c:param name="camp" value="campo_kgCriba_${status.count - 1}" />
					<c:param name="name" value="kgCriba[${status.count - 1}]" />
					<c:param name="required" value="required" />
					<c:param name="maxlength" value="128" />
					<c:param name="clase" value="campoFormMediano" />
					<c:param name="onchange" value="checkCriba(${status.count - 1})" />
				</c:import>
				
				<div class="separadorH"></div>
				
			</c:forEach>
	
		</div>
	
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="hidden" />
			<c:param name="path" value="formData.kgMaxim" />
			<c:param name="camp" value="campo_kgMaxim" />
			<c:param name="name" value="kgMaxim" />
		</c:import>
		
		<div class="separadorH"></div>

		<h3>Bota
		</h3>
		<div id="botes">
			<div id="bota">
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="text" />
					<c:param name="path" value="formData.idBota" />
					<c:param name="title">
						<fmt:message key="proces.elaboracioOliva.camp.identificadorBota"/>
					</c:param>
					<c:param name="camp" value="campo_idBota" />
					<c:param name="name" value="idBota" />
					<c:param name="required" value="required" />
					<%--c:param name="maxlength" value="10"/--%>
					<c:param name="clase" value="campoFormMediano conMargen" />
				</c:import> 
				
				<div class="separadorH"></div>
			
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="text" />
					<c:param name="path" value="formData.kgBota" />
					<c:param name="title"><fmt:message key="proces.elaboracioOliva.camp.kgBota"/></c:param>
					<c:param name="camp" value="campo_kgBota" />
					<c:param name="name" value="kgBota" />
					<c:param name="required" value="required" />
					<c:param name="maxlength" value="128" />
					<c:param name="clase" value="campoFormMediano" />
					<c:param name="disabled" value="disabled" />
				</c:import>
				
				<div class="separadorH"></div>
				
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="text" />
					<c:param name="path" value="formData.concentracioSalmorra" />
					<c:param name="title">
						<fmt:message key="proces.elaboracioOliva.camp.salmorra" />
					</c:param>
					<c:param name="camp" value="campo_concentracioSalmorra" />
					<c:param name="name" value="concentracioSalmorra" />
					<c:param name="required" value="required" />
					<%--c:param name="maxlength" value="10"/--%>
					<c:param name="clase" value="campoFormMediano conMargen" />
				</c:import> 
				
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="text" />
					<c:param name="path" value="formData.lotSal" />
					<c:param name="title">
						<fmt:message key="proces.elaboracioOliva.camp.salLot" />
					</c:param>
					<c:param name="camp" value="campo_lotSal" />
					<c:param name="name" value="lotSal" />
					<c:param name="required" value="required" />
					<c:param name="maxlength" value="128" />
					<c:param name="clase" value="campoFormGrande" />
				</c:import>
		
				<div class="separadorH"></div>
				
				<div id="additiusOlivaTrencada">
				
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="checkbox" />
						<c:param name="path" value="formData.fonollPropi" />
						<c:param name="title">
							<fmt:message key="proces.elaboracioOliva.camp.fonollPropi" />
						</c:param>
						<c:param name="camp" value="fonollPropi" />
						<c:param name="onclick" value="mostrarCamp(this.checked)"/>
					</c:import>
					
					<div class="separadorH"></div>
					
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="text" />
						<c:param name="path" value="formData.gFonoll" />
						<c:param name="title">
							<fmt:message key="proces.elaboracioOliva.camp.gFonoll" />
						</c:param>
						<c:param name="camp" value="campo_gFonoll" />
						<c:param name="name" value="gFonoll" />
						<c:param name="required" value="required" />
						<c:param name="maxlength" value="128" />
						<c:param name="clase" value="campoFormMediano conMargen" />
					</c:import>
					
					
					
					<div id="partidaFonoll" style="display:none">
						<c:import url="comu/CampFormulari.jsp">
							<c:param name="tipus" value="select" />
							<c:param name="path" value="formData.partidaFonollId" />
							<c:param name="required" value="required" />
							<c:param name="title">
								<fmt:message key="proces.elaboracioOliva.camp.partidaFonoll" />
							</c:param>
							<c:param name="camp" value="campo_partidaFonollId" />
							<c:param name="name" value="partidaFonollId" />
							<c:param name="selectItems" value="partidesFonoll" />
							<c:param name="selectItemsId" value="id" />
							<c:param name="selectItemsValue" value="codi" />
							<c:param name="selectSelectedValue" value="${formData.partidaFonollId}" />
							<c:param name="clase" value="campoFormGrande" />
						</c:import>
					</div>
					<div id="lotFonoll">
						<c:import url="comu/CampFormulari.jsp">
							<c:param name="tipus" value="text" />
							<c:param name="path" value="formData.lotFonoll" />
							<c:param name="title">
								<fmt:message key="proces.elaboracioOliva.camp.lotFonoll" />
							</c:param>
							<c:param name="camp" value="campo_lotFonoll" />
							<c:param name="name" value="lotFonoll" />
							<c:param name="required" value="required" />
							<c:param name="maxlength" value="128" />
							<c:param name="clase" value="campoFormGrande" />
						</c:import>
					</div>
					
					
					<div class="separadorH"></div>
					
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="text" />
						<c:param name="path" value="formData.gPebre" />
						<c:param name="title">
							<fmt:message key="proces.elaboracioOliva.camp.gPebre" />
						</c:param>
						<c:param name="camp" value="campo_gPebre" />
						<c:param name="name" value="gPebre" />
						<c:param name="required" value="required" />
						<c:param name="maxlength" value="128" />
						<c:param name="clase" value="campoFormMediano conMargen" />
					</c:import>
					
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="text" />
						<c:param name="path" value="formData.lotPebre" />
						<c:param name="title">
							<fmt:message key="proces.elaboracioOliva.camp.lotPebre" />
						</c:param>
						<c:param name="camp" value="campo_lotPebre" />
						<c:param name="name" value="lotPebre" />
						<c:param name="required" value="required" />
						<c:param name="maxlength" value="128" />
						<c:param name="clase" value="campoFormGrande" />
					</c:import>
					
					<div class="separadorH"></div>
				</div>
			</div>
		</div>
		

		<div id="observacionesForm" class="campoForm">
			<c:import
				url="comu/CampFormulari.jsp">
				<c:param name="tipus" value="textarea" />
				<c:param name="path" value="formData.observacions" />
				<c:param name="title">
					<fmt:message key="proces.elaboracioOliva.camp.observacions" />
				</c:param>
				<c:param name="camp" value="observacions" />
			</c:import>
		</div>
		
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="hidden" />
			<c:param name="path" value="formData.errorObservaciones" />
			<c:param name="camp" value="errorObservaciones" />
			<c:param name="estilo" value="clear:left;overflow:hidden;" />
		</c:import>
		
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="hidden" />
			<c:param name="path" value="formData.esEcologic" />
			<c:param name="camp" value="esEcologic" />
			<c:param name="estilo" value="clear:left;overflow:hidden;" />
			<c:param name="value" value="${esEco}" />
		</c:import>
		
		
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
				onclick="if(confirm('<fmt:message key="manteniment.confirmar"/>')){guardarFormulari()}"
				onmouseover="underline('enlaceGuardarForm')"
				onmouseout="underline('enlaceGuardarForm')">
				<a id="enlaceGuardarForm" href="javascript:void(0);">
					<fmt:message key="manteniment.aceptar" />
				</a>
			</div>
			<div class="btnCorto" onclick="submitForm('tornarForm')"
				onmouseover="underline('enlaceTornarForm')"
				onmouseout="underline('enlaceTornarForm')">
				<a id="enlaceTornarForm" href="javascript:void(0);">
					<fmt:message key="proces.tornar" />
				</a>
			</div>
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

	<form id="tornarForm" action="ProcesInici.html" class="seguit">
		<input type="hidden" id="tipusProces" name="tipusProces" value="17"> 
		<input type="hidden" id="pas" name="pas" value="1">
		<input type="hidden" id="accio" name="accio" value="ORIGEN">
	</form>

	<!-- Colores en tablas -->
	<script type="text/javascript">
			jQuery(document).ready(function(){
				setEstilosTabla(true);
				redibujarError();
				campsVisibles();
			})
	</script>

</body>
</html>