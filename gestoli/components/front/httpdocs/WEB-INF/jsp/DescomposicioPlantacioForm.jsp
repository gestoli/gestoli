<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/fn-rt.tld"%>

<html>
	<head>
		<title>
			<c:choose>
				<c:when test="${not empty formData.id}"><fmt:message key="manteniment.modificacio" /></c:when>
				<c:otherwise><fmt:message key="manteniment.alta" /></c:otherwise>
			</c:choose>
			<fmt:message key="plantacio.tipusdemant" />
		</title>
		
		<script type="text/javascript" src="dwr/interface/varietatOlivaService.js"></script>
		<script type="text/javascript" src="dwr/engine.js"></script>
		<script type="text/javascript" src="dwr/util.js"> </script>
		
		<script type="text/javascript" language="javascript">
			// <![CDATA[
			var posicioItem = 0;
			
			function calculaProduccioMaxima(varietat, posicio) {
				posicioItem = posicio;
				var inputOliveres = document.getElementById("variedades[" + posicio + "].numeroOliveres");
				var inputSuperficie = document.getElementById("variedades[" + posicio + "].superficie");
				var inputProduccio = document.getElementById("variedades[" + posicio + "].maxProduccio");
				varietatOlivaService.produccioMaximaVarietat(
						varietat,
						null,
						inputSuperficie.value,
						inputOliveres.value,
						actualitzarProduccionMaxima);
			}

			function actualitzarProduccionMaxima(dades) {
				var inputOliveres = document.getElementById("variedades[" + posicioItem + "].numeroOliveres");
				var inputSuperficie = document.getElementById("variedades[" + posicioItem + "].superficie");
				var inputProduccio = document.getElementById("variedades[" + posicioItem + "].maxProduccio");
				if (inputOliveres.value != "" && inputSuperficie.value != ""){
					inputProduccio.value = dades;
				} else {
					inputProduccio.value = "";
				}
			}
			// ]]>
		</script>
	</head>

	<body>
		<div>
			<form id="formulario" action="PlantacioForm.html?_target0&idOlivicultor=<c:out value='${idOlivicultor}'/>" method="post" class="seguit">
				<input type="hidden" name="_page" value="1" />
				<input type="hidden" id="idCampanya" name="idCampanya" value="${idCampanya}" />
				<h2><fmt:message key="descomposicioPlantacio.desti.oliDo" /></h2>
				<div class="separadorH"></div>
				<c:set var="descomposicions"><c:out value="${fn:length(descomposiciones)}"/></c:set>
				<c:set var="varietatsExp"><c:out value="${fn:length(experimentals)}"/></c:set>
				<c:forEach var="descomposicion" items="${descomposiciones}" varStatus="status">
					<fieldset class="descomposicion">
						<legend><c:out value="${descomposicion.nom}" /></legend>
						<div class="separadorH"></div>

						<c:choose>
							<%-- ROL DE GESTOR --%>
							<c:when test="${not empty esDoGestor}">
								<c:set var="disabled" value="" />
							</c:when>
							<%-- ROL DE OLIVICULTOR --%>
							<c:when test="${not empty esOlivicultor || not empty esDoControlador}">
								<c:set var="disabled" value="disabled"/>
							</c:when>
						</c:choose>
						
						<c:import url="comu/CampFormulari.jsp">
							<c:param name="tipus" value="text" />
							<c:param name="path" value="formData.variedades[${status.count - 1}].numeroOliveres" />
							<c:param name="required" value="required" />
							<c:param name="title"><fmt:message key="descomposicioPlantacio.camp.numeroOliveres" /></c:param>
							<c:param name="camp" value="variedades[${status.count - 1}].numeroOliveres" />
							<c:param name="clase" value="campoFormMediano conMargen" />
							<c:param name="disabled" value="${disabled}" />
							<c:param name="onblur" value="calculaProduccioMaxima(${descomposicion.id}, ${status.count - 1});" />
						</c:import>
						<c:import url="comu/CampFormulari.jsp">
							<c:param name="tipus" value="text" />
							<c:param name="path" value="formData.variedades[${status.count - 1}].superficie" />
							<c:param name="required" value="required" />
							<c:param name="title"><fmt:message key="descomposicioPlantacio.camp.superficie" /></c:param>
							<c:param name="camp" value="variedades[${status.count - 1}].superficie" />
							<c:param name="clase" value="campoFormMediano conMargen" />
							<c:param name="disabled" value="${disabled}" />
							<c:param name="onblur" value="calculaProduccioMaxima(${descomposicion.id}, ${status.count - 1});" />
						</c:import>
						<c:import url="comu/CampFormulari.jsp">
							<c:param name="tipus" value="text" />
							<c:param name="path" value="formData.variedades[${status.count - 1}].maxProduccio" />
							<c:param name="required" value="required" />
							<c:param name="title"><fmt:message key="descomposicioPlantacio.camp.maxProduccio" /></c:param>
							<c:param name="camp" value="variedades[${status.count - 1}].maxProduccio" />
							<c:param name="clase" value="campoFormMediano conMargen" />
							<c:param name="disabled" value="${disabled}" />
						</c:import>
						<div class="separadorH"></div>

						<c:import url="comu/CampFormulari.jsp">
							<c:param name="tipus" value="hidden" />
							<c:param name="path" value="formData.variedades[${status.count - 1}].varietatOliva.id" />
							<c:param name="camp" value='<c:out value="${descomposicion.id}"/>' />
						</c:import>
					</fieldset>
				</c:forEach>
				<div class="separadorH"></div>
				
				<c:if test="${not empty experimentals}">
				<h2><fmt:message key="descomposicioPlantacio.desti.oliExperimental" /></h2>
					<div class="separadorH"></div>
					<display:table name="experimentals" id="registre" requestURI="" sort="list" cellpadding="0" cellspacing="0" class="listadoEstrecho selectable">
						<display:column titleKey="varietats.experimentals.camp.nom" sortable="true" sortProperty="nomVarietat">
							<c:out value="${registre.nomVarietat}" />
						</display:column>
						<display:column titleKey="descomposicioPlantacio.camp.numeroOliveres">
							<c:import url="comu/CampFormulari.jsp">
								<c:param name="tipus" value="text" />
								<c:param name="path" value="formData.variedades[${descomposicions + registre_rowNum - 1}].numeroOliveres" />
								<c:param name="camp" value="variedades[${descomposicions + registre_rowNum - 1}].numeroOliveres" />
								<c:param name="disabled" value="${disabled}" />
							<c:param name="onblur" value="calculaProduccioMaxima(${registre.id}, ${descomposicions + registre_rowNum - 1});" />
							</c:import>
						</display:column>
						<display:column titleKey="descomposicioPlantacio.camp.superficie">
							<c:import url="comu/CampFormulari.jsp">
								<c:param name="tipus" value="text" />
								<c:param name="path" value="formData.variedades[${descomposicions + registre_rowNum - 1}].superficie" />
								<c:param name="camp" value="variedades[${descomposicions + registre_rowNum - 1}].superficie" />
								<c:param name="disabled" value="${disabled}" />
							<c:param name="onblur" value="calculaProduccioMaxima(${registre.id}, ${descomposicions + registre_rowNum - 1});" />
							</c:import>
						</display:column>
						<display:column titleKey="descomposicioPlantacio.camp.maxProduccio" headerClass="final">
							<c:import url="comu/CampFormulari.jsp">
								<c:param name="tipus" value="text" />
								<c:param name="path" value="formData.variedades[${descomposicions + registre_rowNum - 1}].maxProduccio" />
								<c:param name="camp" value="variedades[${descomposicions + registre_rowNum - 1}].maxProduccio" />
								<c:param name="disabled" value="${disabled}" />
							</c:import>
						</display:column>
					</display:table>
				</c:if>
				<br/>
				<!-- Oliva de taula ocults -->
				<div style="display:none">
					<div class="separadorH"></div>
					<c:set var="ot"><c:out value="${fn:length(olivaTaula)}"/></c:set>
					<h2><fmt:message key="descomposicioPlantacio.desti.oliva" /></h2>
					<div class="separadorH"></div>
					<c:forEach var="varietatTaula" items="${olivaTaula}" varStatus="status">
						<fieldset class="descomposicion">
							<legend><c:out value="${varietatTaula.nom}" /></legend>
							<div class="separadorH"></div>
	
					
							<c:import url="comu/CampFormulari.jsp">
								<c:param name="tipus" value="text" />
								<c:param name="path" value="formData.variedades[${descomposicions + varietatsExp + status.count - 1}].numeroOliveres" />
								<c:param name="required" value="required" />
								<c:param name="title"><fmt:message key="descomposicioPlantacio.camp.numeroOliveres" /></c:param>
								<c:param name="camp" value="variedades[${descomposicions + varietatsExp + status.count - 1}].numeroOliveres" />
								<c:param name="clase" value="campoFormMediano conMargen" />
								<c:param name="disabled" value="${disabled}" />
								<c:param name="onblur" value="calculaProduccioMaxima(${varietatTaula.id}, ${descomposicions + varietatsExp + status.count - 1});" />
							</c:import>
							<c:import url="comu/CampFormulari.jsp">
								<c:param name="tipus" value="text" />
								<c:param name="path" value="formData.variedades[${descomposicions + varietatsExp + status.count - 1}].superficie" />
								<c:param name="required" value="required" />
								<c:param name="title"><fmt:message key="descomposicioPlantacio.camp.superficie" /></c:param>
								<c:param name="camp" value="variedades[${descomposicions + varietatsExp + status.count - 1}].superficie" />
								<c:param name="clase" value="campoFormMediano conMargen" />
								<c:param name="disabled" value="${disabled}" />
								<c:param name="onblur" value="calculaProduccioMaxima(${varietatTaula.id}, ${descomposicions + varietatsExp + status.count - 1});" />
							</c:import>
							<c:import url="comu/CampFormulari.jsp">
								<c:param name="tipus" value="text" />
								<c:param name="path" value="formData.variedades[${descomposicions + varietatsExp + status.count - 1}].maxProduccio" />
								<c:param name="required" value="required" />
								<c:param name="title"><fmt:message key="descomposicioPlantacio.camp.maxProduccio" /></c:param>
								<c:param name="camp" value="variedades[${descomposicions + varietatsExp + status.count - 1}].maxProduccio" />
								<c:param name="clase" value="campoFormMediano conMargen" />
								<c:param name="disabled" value="${disabled}" />
							</c:import>
							<div class="separadorH"></div>
	
							<c:import url="comu/CampFormulari.jsp">
								<c:param name="tipus" value="hidden" />
								<c:param name="path" value="formData.variedades[${descomposicions + varietatsExp + status.count - 1}].varietatOliva.id" />
								<c:param name="camp" value='<c:out value="${varietatTaula.id}"/>' />
							</c:import>
						</fieldset>
					</c:forEach>
				</div>
				<div class="botonesForm">
					<c:choose>
						<c:when test="${not empty esDoGestor}">
							<c:if test="${not empty formData.fromFinca}">
								<div id="descomposicio" class="btnCorto"
										onclick="submitFormAction('formulario','PlantacioForm.html?_target0&idOlivicultor=<c:out value='${idOlivicultor}'/>&fromFinca=<c:out value='${formData.idFinca}'/>')"
										onmouseover="underline('enlaceDescomposicio')"
										onmouseout="underline('enlaceDescomposicio')">
									<a id="enlaceDescomposicio" href="javascript:void(0);">
										<fmt:message key="manteniment.aceptar" />
									</a>
								</div>
							</c:if>
							<c:if test="${empty formData.fromFinca}">
								<div id="descomposicio" class="btnCorto"
										onclick="submitForm('formulario')"
										onmouseover="underline('enlaceDescomposicio')"
										onmouseout="underline('enlaceDescomposicio')">
									<a id="enlaceDescomposicio" href="javascript:void(0);">
										<fmt:message key="manteniment.aceptar" />
									</a>
								</div>
							</c:if>
							
							<div id="cancelarForm" class="btnCorto"
									onclick="document.location ='PlantacioForm.html?_cancel';"
									onmouseover="underline('enlaceCancelarForm')"
									onmouseout="underline('enlaceCancelarForm')">
								<a id="enlaceCancelarForm" href="javascript:void(0);">
									<fmt:message key="manteniment.tornar" />
								</a>
							</div>
						</c:when>

						<c:when test="${not empty esOlivicultor || not empty esDoControlador}">
							<div id="cancelarForm" class="btnCorto"
									onclick="document.location ='PlantacioForm.html?_cancel';"
									onmouseover="underline('enlaceCancelarForm')"
									onmouseout="underline('enlaceCancelarForm')">
								<a id="enlaceCancelarForm" href="javascript:void(0);">
									<fmt:message key="manteniment.tornar" />
								</a>
							</div>
						</c:when>
					</c:choose>
				</div>
			</form>

			<form id="deleteForm" action="FincasForm.html" method="post" class="seguit" onsubmit="return confirm('<fmt:message key="manteniment.estasegur"/>')">
				<input id="id" name="id" value='<c:out value="${formData.id}"/>' type="hidden" />
				<input id="action" name="action" value="delete" type="hidden" />
			</form>
		</div>

		<c:if test="${not empty llistat}">
			<!-- Colores en tablas -->
			<script type="text/javascript">
				$(document).ready(function(){
					setEstilosTabla();
				})
			</script>
		</c:if>
	</body>
</html>
