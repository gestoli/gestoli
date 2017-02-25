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
					} else {
						document.getElementById('dadesMotiu').style.display = 'none';
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
		<form id="formulario" name="procesSortidaForm" action="ProcesSortidaForm.html?accio=${venda}" method="post" class="extended seguit">
			<c:import url="comu/CampFormulari.jsp">
				<c:param name="tipus" value="calendar" />
				<c:param name="path" value="formData.vendaData" />
				<c:param name="title"><fmt:message key="sortidaLot.camp.data" /></c:param>
				<c:param name="camp" value="campo_data" />
				<c:param name="name" value="vendaData" />
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
	
				<div id="dadesMotiu" style="display: none" class="cajaTabla">
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="text" />
						<c:param name="path" value="formData.vendaDestinatari" />
						<c:param name="title"><fmt:message key="sortidaLot.camp.destinatari" /></c:param>
						<c:param name="camp" value="campo_destinatari" />
						<c:param name="name" value="vendaDestinatari" />
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
			
			<c:if test="${tipusSortida == 'd'}">
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="hidden" />
					<c:param name="path" value="formData.diposit.id" />
					<c:param name="camp" value="diposit.id" />
					<c:param name="name" value="diposit.id" />
					<c:param name="value" value="${diposit.id}" />
				</c:import>
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="hidden" />
					<c:param name="path" value="formData.accion" />
					<c:param name="camp" value="accion" />
					<c:param name="name" value="accion" />
					<c:param name="value" value="${venda}" />
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
									<th colspan="2" class="unicoHead"><c:out value=" ${diposit.codiAssignat}" /></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td class="ancho200"><fmt:message key="lot.camp.acidesa" /></td>
									<td class="boldtd"><fmt:formatNumber value="${diposit.acidesa}" maxFractionDigits="2" /> %</td>
								</tr>
								<tr>
									<td><fmt:message key="lot.camp.partidaOli" /></td>
									<td class="boldtd">${diposit.partidaOli.nom}</td>
								</tr>
								<tr>
									<td><fmt:message key="lot.camp.volumActual" /></td>
									<td class="boldtd">
										<c:choose>
											<c:when test="${diposit.volumActual != null && diposit.volumActual > 0 && diposit.establiment.unitatMesura == 'l'}">
												<fmt:formatNumber value="${diposit.volumActual}" maxFractionDigits="2" /> l
											</c:when>
											<c:when test="${diposit.volumActual != null && diposit.volumActual > 0 && diposit.establiment.unitatMesura == 'k'}">
												<fmt:formatNumber value="${diposit.disponibleOliQuilos}" maxFractionDigits="2" /> kg.
											</c:when>
											<c:otherwise><fmt:message key="lot.camp.dipositBuit" /></c:otherwise>
										</c:choose>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
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
									<th class="unicoHead"><fmt:message key="diposit.camp.ventaInfo" /></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td class="conversor">
										<fieldset>
											<c:if test="${diposit.establiment.unitatMesura == 'l'}">
												<span>
													<c:import url="comu/CampFormulari.jsp">
														<c:param name="tipus" value="text" />
														<c:param name="path" value="formData.litros" />
														<c:param name="required" value="required" />
														<c:param name="title"><fmt:message key="diposit.camp.litres" /></c:param>
														<c:param name="camp" value="litros" />
														<c:param name="name" value="litros" />
														<c:param name="clase" value="campoFormMediano conMargen" />
														<c:param name="onkeyup" value="litrosKilos()" />
													</c:import>
												</span>
												<span>
													<c:import url="comu/CampFormulari.jsp">
														<c:param name="tipus" value="text" />
														<c:param name="path" value="formData.kilos" />
														<c:param name="title"><fmt:message key="diposit.camp.kilos" /></c:param>
														<c:param name="camp" value="kilos" />
														<c:param name="name" value="kilos" />
														<c:param name="clase" value="campoFormMediano readOnly" />
														<c:param name="readonly" value="true" />
													</c:import>
												</span>
											</c:if>
											
											<c:if test="${diposit.establiment.unitatMesura == 'k'}">
												<span>
													<c:import url="comu/CampFormulari.jsp">
														<c:param name="tipus" value="text" />
														<c:param name="path" value="formData.kilos" />
														<c:param name="required" value="required" />
														<c:param name="title"><fmt:message key="diposit.camp.kilos" /></c:param>
														<c:param name="camp" value="kilos" />
														<c:param name="name" value="kilos" />
														<c:param name="clase" value="campoFormMediano conMargen" />
														<c:param name="onkeyup" value="kilosLitros()" />
													</c:import>
												</span>
												<span>
													<c:import url="comu/CampFormulari.jsp">
														<c:param name="tipus" value="text" />
														<c:param name="path" value="formData.litros" />
														<c:param name="title"><fmt:message key="diposit.camp.litres" /></c:param>
														<c:param name="camp" value="litros" />
														<c:param name="name" value="litros" />
														<c:param name="clase" value="campoFormMediano readOnly" />
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
				
				<c:if test="${not empty diposit.partidaOli and diposit.partidaOli.categoriaOli.id > 1}">
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="checkbox" />
						<c:param name="path" value="formData.desqualificar" />
						<c:param name="title"><fmt:message key="sortidaLot.camp.desqualificar" /></c:param>
						<c:param name="camp" value="desqualificar" />
					</c:import>
			
					<div class="separadorH"></div>
				</c:if>
		
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="text" />
					<c:param name="path" value="formData.vendaDestinatari" />
					<c:param name="title"><fmt:message key="sortidaLot.camp.destinatari" /></c:param>
					<c:param name="camp" value="campo_destinatari" />
					<c:param name="name" value="vendaDestinatari" />
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
					<c:param name="required" value="required" />
					<c:param name="maxlength" value="64" />
					<c:param name="clase" value="campoFormMediano margen120" />
				</c:import>
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="text" />
					<c:param name="path" value="formData.vendaNumeroDocument" />
					<c:param name="title"><fmt:message key="sortidaLot.camp.numeroDocument" /></c:param>
					<c:param name="camp" value="campo_numeroDocument" />
					<c:param name="name" value="vendaNumeroDocument" />
					<c:param name="required" value="required" />
					<c:param name="maxlength" value="64" />
					<c:param name="clase" value="campoFormMediano" />
				</c:import>
			</c:if>
			<div class="separadorH"></div>
	
			<div id="observacionesForm" class="campoForm <c:out value="${param.required}"/><c:if test="${not empty status.errorMessage}"> error</c:if>">
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="textarea" />
					<c:param name="path" value="formData.vendaObservacions" />
					<c:param name="title"><fmt:message key="sortidaLot.camp.observacions" /></c:param>
					<c:param name="camp" value="vendaObservacions" />
				</c:import>
			</div>
			<div class="separadorH"></div>
	
			<div class="botonesForm">
				<div id="guardarForm" class="btnCorto"
						onclick="if(confirm('<fmt:message key="manteniment.confirmar"/>')){submitForm('formulario')}"
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
			<input type="hidden" id="tipusProces" name="tipusProces" value="9" />
			<input type="hidden" id="pas" name="pas" value="0" />
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
