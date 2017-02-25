<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>

<%@ page import="es.caib.gestoli.front.util.*"%>
<%@ page import="java.util.ResourceBundle"%>
<%@ page import="java.util.Locale"%>

<%
	String lang = Idioma.getLang(request);
	request.setAttribute("lang",lang);
%>

<html>
	<head>
		<title><fmt:message key="proces.etiquetar.titol" /></title>
		<script type="text/javascript" src="js/form.js"></script>
		
	</head>

	<body>
		
		<form id="formulario" name="procesEtiquetarOlivaTaulaForm" action="ProcesEtiquetarOlivaTaulaForm.html" method="post" class="extended seguit" style="width: auto;">
			<input type="hidden" name="id" value="${lot.id}" />
			
			<div style="margin: 0 0 0 30px;"><label><b><fmt:message key="etiquetesLot.camp.infoGeneral" />: </b></label></div>
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
									<c:out value=" ${lot.codiAssignat}" />
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td class="ancho200"><fmt:message key="proces.envasatOliva.camp.tipusOliva" /></td>
								<td class="boldtd">
									<c:if test="${lot.tipusOlivaTaula==0}">
										<fmt:message key="proces.envasatOliva.camp.tipusOliva.verda" />
									</c:if>
									<c:if test="${lot.tipusOlivaTaula==1}">
										<fmt:message key="proces.envasatOliva.camp.tipusOliva.trencada" />
									</c:if>
									<c:if test="${lot.tipusOlivaTaula==2}">
										<fmt:message key="proces.envasatOliva.camp.tipusOliva.negra" />
									</c:if>
								</td>
							</tr>
							<tr>
								<td><fmt:message key="proces.etiquetatOliva.camp.numeroBotelles" /></td>
								<td class="boldtd">${lot.numeroBotellesActuals}</td>
							</tr>
						</tbody>
					</table>
					
			</div>
			</div>
					
			
			
			
			<c:if test="${not empty etiquetesEmprades}">
				<div style="margin: 0 0 0 30px;"><label><b><fmt:message key="etiquetesLot.camp.etiquetesEmprades" />: </b></label></div>
				<div class="cajaTabla">
					<div class="layoutSombraTabla">
						<div class="rellenoInf"></div>
						<div class="rellenoIzq"></div>
						<div class="rellenoDer"></div>
						<div class="supDer"></div>
						<div class="supIzq"></div>
						<div class="infIzq"></div>
						<div class="infDer"></div>		
							<display:table name="etiquetesEmprades" requestURI="" id="etiqueta" sort="list" cellpadding="0" cellspacing="0" class="listadoEstrecho noRoll">
								<display:column titleKey="etiquetesLot.camp.etiquetaInicial" sortable="true">
										<c:out value="${etiqueta.etiquetaInicial}" />
								</display:column>
								<display:column titleKey="etiquetesLot.camp.etiquetaFinal" sortable="true">
										<c:out value="${etiqueta.etiquetaFinal}" />
								</display:column>
							</display:table>
							
							<div class="separadorH"></div>
						</div>
				</div>	
			</c:if>
					
					<c:if test="${not empty etiquetesDisponibles}">
						<div style="margin: 0 0 0 30px;"><label><b><fmt:message key="etiquetesLot.camp.etiquetesDisponibles" />: </b></label></div>
						<div class="cajaTabla">
							<div class="layoutSombraTabla">
								<div class="rellenoInf"></div>
								<div class="rellenoIzq"></div>
								<div class="rellenoDer"></div>
								<div class="supDer"></div>
								<div class="supIzq"></div>
								<div class="infIzq"></div>
								<div class="infDer"></div>		
						<display:table name="etiquetesDisponibles" requestURI="" id="etiqueta" sort="list" cellpadding="0" cellspacing="0" class="listadoEstrecho noRoll">
							<display:column titleKey="etiquetesLot.camp.etiquetaInicial" sortable="true">
									<c:out value="${etiqueta.etiquetaInicial}" />
							</display:column>
							<display:column titleKey="etiquetesLot.camp.etiquetaFinal" sortable="true">
									<c:out value="${etiqueta.etiquetaFinal}" />
							</display:column>
							<display:column titleKey="etiquetesLot.camp.rangEtiquetar" sortable="true">
								<c:import url="comu/CampFormulari.jsp">
									<c:param name="tipus" value="hidden" />
									<c:param name="path" value="formData.idEtiquetes[${etiqueta_rowNum - 1}]" />
									<c:param name="value"><c:out value="${etiqueta.id}" /></c:param>
									<c:param name="camp" value="idEtiquetes[${etiqueta_rowNum - 1}]" />
								</c:import>
								<div style="float:left;">${etiqueta.etiquetaLletra}</div>
								<c:import url="comu/CampFormulari.jsp">
									<c:param name="tipus" value="text" />
									<c:param name="path" value="formData.etiquetesInicials[${etiqueta_rowNum - 1}]" />
									<c:param name="required" value="required" />
									<c:param name="camp" value="etiquetesInicials[${etiqueta_rowNum - 1}]" />
								</c:import> 
								<div style="float:left;">
								${etiqueta.capacitat}
								&nbsp;-&nbsp;
								</div>
								<div style="clear:both;"></div>
								<div style="float:left;">
								${etiqueta.etiquetaLletra}
								</div>
								<c:import url="comu/CampFormulari.jsp">
									<c:param name="tipus" value="text" />
									<c:param name="path" value="formData.etiquetesFinals[${etiqueta_rowNum - 1}]" />
									<c:param name="required" value="required" />
									<c:param name="camp" value="etiquetesFinals[${etiqueta_rowNum - 1}]" />
								</c:import> 
								<div style="float:left;">${etiqueta.capacitat}</div>
							</display:column>
						</display:table>
						
						<div class="separadorH"></div>
								</div>
						</div>	
					</c:if>
		
					<div class="separadorH"></div>
		
					<div class="botonesForm">
						<c:if test="${teEtiquetes}">
							<div id="guardarForm" class="btnCorto"
									onclick="if(confirm('<fmt:message key="manteniment.confirmar"/>')){submitForm('formulario')}"
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

		<form id="tornarForm" action="ProcesInici.html" class="seguit">
			<input type="hidden" id="tipusProces" name="tipusProces" value="20" />
			<input type="hidden" id="pas" name="pas" value="0" />
		</form>
		
		<%-- Colores en tablas --%>
		<script type="text/javascript">
				$(document).ready(function(){
					setEstilosTabla();
				})
			</script>
	</body>
</html>