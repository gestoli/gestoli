<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
		
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/calendar/calendar-setup.js"></script>
<%		if(lang.equalsIgnoreCase("ca")) { %>
			<script type="text/javascript" src="js/calendar/lang/calendar-ca.js"></script>
			<script type="text/javascript" src="js/calendar/lang/calendar-es.js"></script>
<%		} else { %>
			<script type="text/javascript" src="js/calendar/lang/calendar-es.js"></script>
<%		} %>
		<link href="js/calendar/calendar-viti.css" rel="stylesheet" type="text/css" />

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
					} 
				}
			}

			function litrosKilos() {
				var litros = document.getElementById('litros').value;
				var kilos = litros * 0.916;
				document.getElementById('kilos').value = kilos.toFixed(3);		
			}
	
			function kilosLitros() {
				var kilos = document.getElementById('kilos').value;
				var litros = kilos / 0.916;
				document.getElementById('litros').value = litros.toFixed(3);
			}

			function mostraProvincies(pais) {
				if (pais == 73) {
					$("#provincies").show();
				} else {
					$("#provincies").hide();
					$("#municipis").hide();
				}
			}

			function mostraMunicipis(provincia) {
				if (provincia == 7) {
					$("#municipis").show();
				} else {
					$("#municipis").hide();
				}
			}
			
			// ]]>
		</script>
	</head>

	<body>
		<form id="formulario" name="modificarSortidaLot" action="ModificarSortidaLotVenda.html?slId=<c:out value="${slId}" />" method="post" class="extended seguit">
			<input type="hidden" value="${slId}" name="slId" />
			<input type="hidden" value="${formData.vendaNumeroBotelles[0]}" name="venudesInicialment" />
			<input type="hidden" value="${formData.vendaNumeroBotelles[1]}" name="campXValidator" />
			<c:import url="comu/CampFormulari.jsp">
				<c:param name="tipus" value="calendar" />
				<c:param name="path" value="formData.vendaData" />
				<c:param name="title"><fmt:message key="sortidaLot.camp.data" /></c:param>
				<c:param name="camp" value="campo_data" />
				<c:param name="name" value="vendaData" />
				<c:param name="value" value="${formData.vendaData}" />
				<c:param name="maxlength" value="10" />
				<c:param name="required" value="required" />
				<c:param name="aclaracio"><fmt:message key="proces.aclaracio.formatdata" /></c:param>
				<c:param name="clase" value="conMargen" />
			</c:import> 
			<c:import url="comu/CampFormulari.jsp">
				<c:param name="tipus" value="hidden" />
				<c:param name="path" value="formData.tipusSortida" />
				<c:param name="camp" value="tipusSortida" />
				<c:param name="name" value="tipusSortida" />
				<c:param name="value" value="${tipusSortida}" />
			</c:import>
			
			 

			<c:if test="${tipusSortida == 'l'}">
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="hidden" />
					<c:param name="path" value="formData.accion" />
					<c:param name="camp" value="accion" />
					<c:param name="name" value="accion" />
					<c:param name="value" value="${venda}" />
				</c:import>
								
				<c:forEach var="lot" items="${lots}" varStatus="status">
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="hidden" />
						<c:param name="path" value="formData.lot[${status.count - 1}].id" />
						<c:param name="camp" value="lot[${status.count - 1}].id" />
						<c:param name="name" value="lot[${status.count - 1}].id" />
						<c:param name="value" value="${lot.id}" />
					</c:import>
					
					<div class="separadorH"></div>

					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="text" />
						<c:param name="path" value="formData.vendaNumeroBotelles[${status.count - 1}]" />
						<c:param name="title"><fmt:message key="sortidaLot.camp.numeroEnvasos" /></c:param>
						<c:param name="camp" value="campo_vendaNumeroBotelles[${status.count - 1}]" />
						<c:param name="name" value="vendaNumeroBotelles[${status.count - 1}]" />
						<c:param name="required" value="required" />
						<c:param name="maxlength" value="10" />
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
										<th colspan="2" class="unicoHead"><c:out value=" ${lot.codiAssignat}" /></th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td class="ancho200"><fmt:message key="sortidaLot.camp.tipusEnvas" /></td>
										<td class="boldtd"><c:out value="${lot.etiquetatge.tipusEnvas.materialTipusEnvas.nom} ${lot.etiquetatge.tipusEnvas.volum} l" /></td>
									</tr>
									<tr>
										<td><fmt:message key="sortidaLot.camp.numeroEnvasosExistents" /></td>
										<td class="boldtd"><c:out value="${lot.numeroBotellesActuals}" /></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					
					<div class="separadorH"></div>
					
				</c:forEach>
	
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="select" />
					<c:param name="path" value="formData.vendaMotiu" />
					<c:param name="required" value="required" />
					<c:param name="title"><fmt:message key="sortidaLot.camp.motiu" /></c:param>
					<c:param name="camp" value="campo_motiu" />
					<c:param name="name" value="vendaMotiu" />
					<c:param name="selectItems" value="motius" />
					<c:param name="selectItemsId" value="id" />
					<c:param name="selectItemsValue" value="nom" />
					<c:param name="selectSelectedValue" value="${formData.vendaMotiu}" />
					<c:param name="clase" value="campoFormMediano conMargen" />
					<c:param name="onchange" value="canviMotiu()" />
				</c:import>
				<div class="separadorH"></div>
	
				<div id="dadesMotiu" class="cajaTabla">
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="text" />
						<c:param name="path" value="formData.vendaDestinatari" />
						<c:param name="title"><fmt:message key="sortidaLot.camp.destinatari" /></c:param>
						<c:param name="camp" value="campo_destinatari" />
						<c:param name="name" value="vendaDestinatari" />
						<c:param name="value" value="${formData.vendaDestinatari}" />
						<c:param name="required" value="required" />
						<c:param name="maxlength" value="128" />
						<c:param name="clase" value="campoFormGrande" />
					</c:import>
					<div class="separadorH"></div>
	
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="text" />
						<c:param name="path" value="formData.vendaTipusDocument" />
						<c:param name="title"><fmt:message key="sortidaLot.camp.tipusDocument" /></c:param>
						<c:param name="camp" value="campo_tipusDocument" />
						<c:param name="name" value="vendaTipusDocument" />
						<c:param name="maxlength" value="64" />
						<c:param name="clase" value="campoFormMediano margen120" />
					</c:import>
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="text" />
						<c:param name="path" value="formData.vendaNumeroDocument" />
						<c:param name="title"><fmt:message key="sortidaLot.camp.numeroDocument" /></c:param>
						<c:param name="camp" value="campo_numeroDocument" />
						<c:param name="name" value="vendaNumeroDocument" />
						<c:param name="value" value="${formData.vendaNumeroDocument}" />
						<c:param name="maxlength" value="64" />
						<c:param name="clase" value="campoFormMediano" />
					</c:import>
					<div class="separadorH"></div>
					
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="select" />
						<c:param name="path" value="formData.paisId" />
						<c:param name="title"><fmt:message key="sortidaLot.camp.pais" /></c:param>
						<c:param name="camp" value="campo_pais" />
						<c:param name="name" value="paisId" />
						<c:param name="selectItems" value="paisos" />
						<c:param name="selectItemsId" value="id" />
						<c:param name="selectItemsValue" value="nomcat" />
						<c:param name="selectSelectedValue" value="${formData.paisId}" />
						<c:param name="clase" value="campoFormMediano conMargen" />
						<c:param name="onchange" value="mostraProvincies(this.value);" />
					</c:import>
					<div id="provincies"<c:if test="${formData.paisId != 73}"> style="display: none;"</c:if>>
						<c:import url="comu/CampFormulari.jsp">
							<c:param name="tipus" value="select" />
							<c:param name="path" value="formData.provinciaId" />
							<c:param name="title"><fmt:message key="sortidaLot.camp.provincia" /></c:param>
							<c:param name="camp" value="campo_provincia" />
							<c:param name="name" value="provinciaId" />
							<c:param name="selectItems" value="provincies" />
							<c:param name="selectItemsId" value="id" />
							<c:param name="selectItemsValue" value="nom" />
							<c:param name="selectSelectedValue" value="${formData.provinciaId}" />
							<c:param name="clase" value="campoFormMediano conMargen" />
							<c:param name="onchange" value="mostraMunicipis(this.value);" />
						</c:import>
					</div>
					<div id="municipis"<c:if test="${formData.provinciaId != 7}"> style="display: none;"</c:if>>
						<c:import url="comu/CampFormulari.jsp">
							<c:param name="tipus" value="select" />
							<c:param name="path" value="formData.municipiId" />
							<c:param name="title"><fmt:message key="sortidaLot.camp.municipi" /></c:param>
							<c:param name="camp" value="campo_municipi" />
							<c:param name="name" value="municipiId" />
							<c:param name="selectItems" value="municipis" />
							<c:param name="selectItemsId" value="id" />
							<c:param name="selectItemsValue" value="nom" />
							<c:param name="selectSelectedValue" value="${formData.municipiId}" />
							<c:param name="clase" value="campoFormMediano conMargen" />
						</c:import>
					</div>
				</div>
			</c:if>
			
			<div class="botonesForm">
				<input type="hidden" value="" name="mesData" />
				<c:if test="${mateixMes}">
					<div id="guardarForm" class="btnCorto" 
							onclick="if(confirm('<fmt:message key="manteniment.confirmar"/>')){submitForm('formulario')}"
							onmouseover="underline('enlaceGuardarForm')"
							onmouseout="underline('enlaceGuardarForm')">
						<a id="enlaceGuardarForm" href="javascript:void(0);">
							<fmt:message key="manteniment.aceptar" />
						</a>
					</div>
				</c:if>
				<c:if test="${not mateixMes}">
					<div id="guardarForm" class="btnCorto" style="color:red;" 
							onclick="if(confirm('<fmt:message key="manteniment.confirmar.modificar"/>')){submitForm('formulario')}"
							onmouseover="underline('enlaceGuardarForm')"
							onmouseout="underline('enlaceGuardarForm')">
						<a id="enlaceGuardarForm" href="javascript:void(0);">
							<fmt:message key="manteniment.aceptar" />
						</a>
					</div>
				</c:if>
				
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

		<form id="tornarForm" action="ModificarOliComercialitzatLlistat.html" class="seguit">
		</form>

		<%-- Colores en tablas --%>
		<script type="text/javascript">
			jQuery(document).ready(function(){
				canviMotiu();
				setEstilosTabla(true);
			});
		</script>
	</body>
</html>
