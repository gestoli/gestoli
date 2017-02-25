<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>
<%@ page buffer="64kb"%>
<%@ page autoFlush="true"%>

<html>
	<head>
		<title>
			<c:choose>
				<c:when test="${not empty formData.id}"><fmt:message key="manteniment.modificacio" /></c:when>
				<c:otherwise><fmt:message key="manteniment.alta" /></c:otherwise>
			</c:choose>
			<fmt:message key="fincas.tipusdemant" />
		</title>
		<script type="text/javascript" src="js/displaytag.js"></script>
	</head>

	<body>
		<div>
			<form id="formulario" action="FincasForm.html" method="post" class="seguit">
				<c:if test="${not empty formData.id}">
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="hidden" />
						<c:param name="path" value="formData.id" />
						<c:param name="camp" value="id" />
					</c:import>
				</c:if>
				<c:if test="${not empty formData.idOriginal}">
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="hidden" />
						<c:param name="path" value="formData.idOriginal" />
						<c:param name="camp" value="idOriginal" />
					</c:import>
				</c:if>
				<c:if test="${not empty formData.idOlivicultor}">
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="hidden" />
						<c:param name="path" value="formData.idOlivicultor" />
						<c:param name="camp" value="idOlivicultor" />
					</c:import>
				</c:if>
				
				<c:if test="${empty formData.id}">
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="select" />
						<c:param name="path" value="formData.fincaBaixaId" />
						<c:param name="title"><fmt:message key="finques.camp.finca.baixa" /></c:param>
						<c:param name="camp" value="fincaBaixaId" />
						<c:param name="clase" value="campoFormGrande conMargen" />
						<c:param name="opcioBuida"><fmt:message key="finques.camp.finca.tria.baixa" /></c:param>
						<c:param name="selectItems" value="finquesBaixa" />
						<c:param name="selectItemsId" value="id" />
						<c:param name="selectItemsValue" value="nom" />
						<c:param name="selectSelectedValue" value="${formData.fincaBaixaId}" />
					</c:import> 
				</c:if>
				
				<c:choose>
					<c:when test="${not empty esDoGestor}">
						<c:import url="comu/CampFormulari.jsp">
							<c:param name="tipus" value="text" />
							<c:param name="path" value="formData.nom" />
							<c:param name="maxlength" value="128" />
							<c:param name="title"><fmt:message key="fincas.camp.nom" /></c:param>
							<c:param name="camp" value="nom" />
							<c:param name="clase" value="campoFormGrande conMargen" />
						</c:import>
					</c:when>
					<c:when test="${not empty esOlivicultor}">
						<c:import url="comu/CampFormulari.jsp">
							<c:param name="tipus" value="text" />
							<c:param name="path" value="formData.nom" />
							<c:param name="maxlength" value="128" />
							<c:param name="title"><fmt:message key="fincas.camp.nom" /></c:param>
							<c:param name="camp" value="nom" />
							<c:param name="clase" value="campoFormGrande conMargen" />
							<c:param name="disabled" value="disabled" />
						</c:import>
					</c:when>
				</c:choose>
				
				<c:if test="${not empty formData.id && not empty variedades}">
					<div class="separadorH"></div>
					
					<div class="campoForm">
						<label><fmt:message key="fincas.variedades" /></label>
						<div class="separadorH"></div>

						<div id="listadoEstrecho"><%-- Tabla de resultados --%>
							<div class="layoutSombraTabla">
								<div class="rellenoInf"></div>
								<div class="rellenoIzq"></div>
								<div class="rellenoDer"></div>
								<div class="supDer"></div>
								<div class="supIzq"></div>
								<div class="infIzq"></div>
								<div class="infDer"></div>

								<display:table name="variedades" requestURI="" id="variedad" export="true" sort="list" cellpadding="0" cellspacing="0" class="listadoEstrecho noEnlace">
									<display:column property="nomVariedad" titleKey="fincas.camp.variedad" headerClass=""/>
									<display:column property="superficie" titleKey="fincas.camp.superficie" headerClass="ancho100" format="{0,number,#,##0.0000}"/>
									<display:column property="produccion" titleKey="fincas.camp.produccion" headerClass="ancho110 final" format="{0,number,#,##0.00}"/>
									<display:setProperty name="export.xml" value="false" />
									<display:setProperty name="export.csv" value="false" />
									<display:setProperty name="export.rtf" value="false" />
									<display:setProperty name="export.pdf" value="false" />
									<display:setProperty name="export.excel.include_header" value="true" />
									<display:setProperty name="export.excel.filename" value="varietatsFinca.xls" />
									<display:setProperty name="export.decorated" value="true" />
								</display:table>
							</div>
						</div>
					</div>
				</c:if>
				
				<c:if test="${not empty formData.id && not empty plantaciones}">
					<div class="separadorH"></div>
					
					<div class="campoForm">
						<label for="plantacios"><fmt:message key="fincas.plantaciones" /></label>
						<div class="separadorH"></div>

						<div id="listadoEstrecho"><%-- Tabla de resultados --%>
							<div class="layoutSombraTabla">
								<div class="rellenoInf"></div>
								<div class="rellenoIzq"></div>
								<div class="rellenoDer"></div>
								<div class="supDer"></div>
								<div class="supIzq"></div>
								<div class="infIzq"></div>
								<div class="infDer"></div>

								<display:table name="plantaciones" requestURI="" id="plantacio" export="true" sort="list" cellpadding="0" cellspacing="0" class="listadoEstrecho selectable">
									<display:column titleKey="fincas.camp.municipio" headerClass="" sortable="true" sortProperty="municipi.nom">
										<a href="PlantacioForm.html?id=<c:out value="${plantacio.id}"/>&amp;idOlivicultor=<c:out value="${formData.idOlivicultor}"/>&amp;fromFinca=<c:out value="${formData.id}"/>">
											<c:out value="${plantacio.municipi.nom}" />
										</a>
									</display:column>
									<display:column titleKey="fincas.camp.poligono" headerClass="ancho100" sortable="true">
										<c:out value="${plantacio.poligon}" />
									</display:column>
									<display:column titleKey="fincas.camp.parcela" headerClass="ancho100" sortable="true">
										<c:out value="${plantacio.parcela}" />
									</display:column>
									<display:column titleKey="fincas.camp.activo" headerClass="ancho75 final" sortable="true">
										<c:choose>
											<c:when test="${plantacio.actiu}"><fmt:message key="manteniment.si" /></c:when>
											<c:otherwise><fmt:message key="manteniment.no" /></c:otherwise>
										</c:choose>
									</display:column>
									<display:setProperty name="export.xml" value="false" />
									<display:setProperty name="export.csv" value="false" />
									<display:setProperty name="export.rtf" value="false" />
									<display:setProperty name="export.pdf" value="false" />
									<display:setProperty name="export.excel.include_header" value="true" />
									<display:setProperty name="export.excel.filename" value="PlantacionsFinca.xls" />
									<display:setProperty name="export.decorated" value="true" />
								</display:table>
							</div>
						</div>
					</div>
				</c:if>
				
				<c:if test="${not empty formData.id}">
					<div class="separadorH"></div>
					
					<div class="etiqueta <c:out value="${param.required}"/><c:if test="${not empty status.errorMessage}"> error</c:if>">
						<c:choose>
							<c:when test="${not empty esDoGestor}">
								<c:import url="comu/CampFormulari.jsp">
									<c:param name="tipus" value="checkbox" />
									<c:param name="path" value="formData.actiu" />
									<c:param name="title"><fmt:message key="fincas.camp.activa" /></c:param>
									<c:param name="camp" value="actiu" />
									<c:param name="onchange" value="cambioEstado()" />
								</c:import>
							</c:when>
							<c:when test="${not empty esOlivicultor}">
								<c:import url="comu/CampFormulari.jsp">
									<c:param name="tipus" value="checkbox" />
									<c:param name="path" value="formData.actiu" />
									<c:param name="title"><fmt:message key="fincas.camp.activa" /></c:param>
									<c:param name="camp" value="actiu" />
									<c:param name="onchange" value="cambioEstado()" />
									<c:param name="disabled" value="disabled" />
								</c:import>
							</c:when>
						</c:choose>
					</div>
					
					<input type="hidden" id="campoCambioEstado" name="cambioEstadoActivo" value="false" />
				</c:if>
				<div class="separadorH"></div>

				<div id="observacionesForm" class="campoForm <c:out value="${param.required}"/><c:if test="${not empty status.errorMessage}"> error</c:if>">
					<c:choose>
						<c:when test="${not empty esDoGestor}">
							<c:import url="comu/CampFormulari.jsp">
								<c:param name="tipus" value="textarea" />
								<c:param name="path" value="formData.observacions" />
								<c:param name="title"><fmt:message key="fincas.camp.observacions" /></c:param>
								<c:param name="camp" value="observacions" />
							</c:import>
						</c:when>
						<c:when test="${not empty esOlivicultor}">
							<c:import url="comu/CampFormulari.jsp">
								<c:param name="tipus" value="textarea" />
								<c:param name="path" value="formData.observacions" />
								<c:param name="title"><fmt:message key="fincas.camp.observacions" /></c:param>
								<c:param name="camp" value="observacions" />
								<c:param name="disabled" value="disabled" />
							</c:import>
						</c:when>
					</c:choose>
				</div>
				<div class="separadorH"></div>

				<div class="botonesForm">
					<c:choose>
						<c:when test="${not empty esDoControlador}">
							<div id="botoPlantacionsForm" class="btnCorto"
								onclick="submitForm('plantacionsForm')"
								onmouseover="underline('enlacePlantacionsForm')"
								onmouseout="underline('enlacePlantacionsForm')">
								<a id="enlaceFinquesForm" href="javascript:void(0);">
									<fmt:message key="manteniment.finques.plantacions" />
								</a>
							</div>
							<div id="cancelarForm" class="btnCorto"
								onmouseover="underline('enlaceCancelarForm')"
								onmouseout="underline('enlaceCancelarForm')"
								onclick="document.location ='Fincas.html?idOlivicultor=<c:out value='${formData.idOlivicultor}'/>';">
								<a id="enlaceCancelarForm" href="javascript:void(0);">
									<fmt:message key="manteniment.tornar" />
								</a>
							</div>
						</c:when>
						<c:when test="${not empty esDoGestor}">
							<c:if test="${not empty formData.id}">
								<div id="guardarForm" class="btnCorto"
										onclick="if(confirm('<fmt:message key="manteniment.modificar.confirm"/>')){submitForm('formulario')}"
										onmouseover="underline('enlaceGuardarForm')"
										onmouseout="underline('enlaceGuardarForm')">
									<a id="enlaceGuardarForm" href="javascript:void(0);">
										<fmt:message key="manteniment.guardar" />
									</a>
								</div>
								<div id="botoPlantacionsForm" class="btnCorto"
										onclick="submitForm('plantacionsForm')"
										onmouseover="underline('enlacePlantacionsForm')"
										onmouseout="underline('enlacePlantacionsForm')">
									<a id="enlaceFinquesForm" href="javascript:void(0);">
										<fmt:message key="manteniment.finques.plantacions" />
									</a>
								</div>
							</c:if>
							<c:if test="${empty formData.id}">
								<div id="guardarForm" class="btnCorto"
										onclick="submitForm('formulario')"
										onmouseover="underline('enlaceGuardarForm')"
										onmouseout="underline('enlaceGuardarForm')">
									<a id="enlaceGuardarForm" href="javascript:void(0);">
										<fmt:message key="manteniment.aceptar" />
									</a>
								</div>
							</c:if>
							<div id="cancelarForm" class="btnCorto"
									onmouseover="underline('enlaceCancelarForm')"
									onmouseout="underline('enlaceCancelarForm')"
									onclick="document.location ='Fincas.html?idOlivicultor=<c:out value='${formData.idOlivicultor}'/>';">
								<a id="enlaceCancelarForm" href="javascript:void(0);">
									<fmt:message key="manteniment.tornar" />
								</a>
							</div>
							<c:if test="${not empty formData.id}">
								<input id="action" name="action" value="delete" type="hidden" />
								<div id="eliminarForm" class="btnCorto"
										onmouseover="underline('enlaceBorrarForm')"
										onmouseout="underline('enlaceBorrarForm')"
										onclick="submitFormConfirm('deleteForm','<fmt:message key="manteniment.esborrar.confirm"/>');">
									<a id="enlaceBorrarForm" href="javascript:void(0);">
										<fmt:message key="manteniment.esborrar" />
									</a>
								</div>
							</c:if>
						</c:when>

						<c:when test="${not empty esOlivicultor}">
							<div id="cancelarForm" class="btnCorto"
									onmouseover="underline('enlaceCancelarForm')"
									onmouseout="underline('enlaceCancelarForm')"
									onclick="document.location ='Fincas.html?idOlivicultor=<c:out value='${formData.idOlivicultor}'/>';">
								<a id="enlaceCancelarForm" href="javascript:void(0);">
									<fmt:message key="manteniment.aceptar" />
								</a>
							</div>
						</c:when>
					</c:choose>
				</div>
			</form>

			<form id="deleteForm" action="Fincas.html" method="post" class="seguit" onsubmit="return confirm('<fmt:message key="manteniment.estasegur"/>')">
				<input id="id" name="id" value="<c:out value="${formData.id}"/>" type="hidden" />
				<input id="idOlivicultor" name="idOlivicultor" value="<c:out value="${formData.olivicultor.id}"/>" type="hidden" />
				<input id="action" name="action" value="delete" type="hidden" />
			</form>
					
			<form id="plantacionsForm" action="Plantacio.html" method="post" class="seguit">
				<input id="idOlivicultor" name="idOlivicultor" value="<c:out value="${formData.olivicultor.id}"/>" type="hidden" />
				<input id="idFinca" name="idFinca" value="<c:out value="${formData.id}"/>" type="hidden" />
			</form>
		</div>

		<c:if test="${not empty formData.id && (not empty variedades ||  not empty plantaciones)}">
			<%-- Colores en tablas --%>
			<script type="text/javascript">
				$(document).ready(function(){
					setEstilosTabla(false);
				});
			</script>
		</c:if>
	</body>
</html>
