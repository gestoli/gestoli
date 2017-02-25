<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>

<html>
	<head>
		<title>
			<c:choose>
				<c:when test="${not empty formData.id}"><fmt:message key="manteniment.modificacio" /></c:when>
				<c:otherwise><fmt:message key="manteniment.alta" /></c:otherwise>
			</c:choose>
			<fmt:message key="plantacio.tipusdemant" />
		</title>
		
		<script>
		    var edat = edat || {}; // play well with other jsps on the page
		    edat = {
		        vell: "<fmt:message key="plantacio.any.plantacio.vell"/>",
		        jove: "<fmt:message key="plantacio.any.plantacio.jove"/>"
		    };
		</script>
		
		<script type="text/javascript" language="javascript">
		// <![CDATA[
			function edatPlantacio(){
				var edatPlan = document.getElementById('edatPlantacio');
				var sAnyPlantacio = document.getElementById('anyPlantacio');
				if (sAnyPlantacio != null && sAnyPlantacio.value != ""){
					var anyPlantacio = parseInt(sAnyPlantacio.value);
					var dAnyCritic = new Date();
					var anyCritic = dAnyCritic.getFullYear() - 75;
					
					if (anyPlantacio < anyCritic) {
						edatPlan.innerHTML = "<label/><h4>" + edat.vell + "</h4>";
					} else {
						edatPlan.innerHTML = "<label/><h4>" + edat.jove + "</h4>";
					}
				} else {
					edatPlan.innerHTML = "";
				}
			}
		// ]]>
		</script>
	</head>
	
	<body>
		<div>
			<form id="formulario" action="" method="post" class="seguit">
				<input type="hidden" name="_page" value="0" />
				<c:if test="${not empty formData.id}">
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="hidden" />
						<c:param name="path" value="formData.id" />
						<c:param name="camp" value="id" />
					</c:import>
				</c:if>
				<c:choose>
					<c:when test="${not empty idFinca}"><c:set var="fincaId" value="${idFinca}" /></c:when>
					<c:otherwise><c:set var="fincaId" value="${formData.finca.id}" /></c:otherwise>
				</c:choose>
				
				<c:if test="${empty formData.id}">
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="select" />
						<c:param name="path" value="formData.plantacioBaixaId" />
						<c:param name="title"><fmt:message key="plantacions.camp.finca.baixa" /></c:param>
						<c:param name="camp" value="plantacioBaixaId" />
						<c:param name="clase" value="campoFormGrande conMargen" />
						<c:param name="opcioBuida"/>
						<c:param name="selectItems" value="plantacionsBaixa" />
						<c:param name="selectItemsId" value="id" />
						<c:param name="selectItemsValue" value="nomComplet" />
						<c:param name="selectSelectedValue" value="${formData.plantacioBaixaId}" />
					</c:import> 
				</c:if>
				
				<c:choose>
					<%-- ROL DE GESTOR --%>
					<c:when test="${not empty esDoGestor}">
						<input type="hidden" name="idFinca" id="idFinca" value="${fincaId}" />
						<c:import url="comu/CampFormulari.jsp">
							<c:param name="tipus" value="text" />
							<c:param name="path" value="formData.nomFinca" />
							<c:param name="required" value="required" />
							<c:param name="maxlength" value="128" />
							<c:param name="title"><fmt:message key="plantacio.camp.finca" /></c:param>
							<c:param name="camp" value="nomFinca" />
							<c:param name="clase" value="campoFormGrande" />
							<c:param name="readonly" value="readonly" />
							<c:param name="value" value="${formData.finca.nom}" />
						</c:import>
						<div class="separadorH"></div>

						<c:import url="comu/CampFormulari.jsp">
							<c:param name="tipus" value="select" />
							<c:param name="path" value="formData.municipiId" />
							<c:param name="title"><fmt:message key="plantacio.camp.municipi" /></c:param>
							<c:param name="camp" value="municipiId" />
							<c:param name="required" value="required" />
							<c:param name="selectItems" value="municipis" />
							<c:param name="selectItemsId" value="id" />
							<c:param name="selectItemsValue" value="nom" />
							<c:param name="selectSelectedValue" value="${formData.municipiId}" />
							<c:param name="clase" value="campoFormGrande" />
						</c:import>
						<div class="separadorH"></div>

						<c:import url="comu/CampFormulari.jsp">
							<c:param name="tipus" value="text" />
							<c:param name="path" value="formData.poligon" />
							<c:param name="required" value="required" />
							<c:param name="maxlength" value="128" />
							<c:param name="title"><fmt:message key="plantacio.camp.poligon" /></c:param>
							<c:param name="camp" value="poligon" />
							<c:param name="clase" value="campoFormGrande" />
						</c:import>
						<div class="separadorH"></div>

						<c:import url="comu/CampFormulari.jsp">
							<c:param name="tipus" value="text" />
							<c:param name="path" value="formData.parcela" />
							<c:param name="required" value="required" />
							<c:param name="maxlength" value="128" />
							<c:param name="title"><fmt:message key="plantacio.camp.parcela" /></c:param>
							<c:param name="camp" value="parcela" />
							<c:param name="clase" value="campoFormGrande" />
						</c:import>
						<div class="separadorH"></div>

						<c:import url="comu/CampFormulari.jsp">
							<c:param name="tipus" value="text" />
							<c:param name="path" value="formData.anyPlantacio" />
							<c:param name="required" value="required" />
							<c:param name="maxlength" value="4" />
							<c:param name="title"><fmt:message key="plantacio.camp.anyPlantacio" /></c:param>
							<c:param name="camp" value="anyPlantacio" />
							<c:param name="clase" value="campoFormPequeno" />
							<c:param name="onchange" value="edatPlantacio();" />
						</c:import>
						<div id="edatPlantacio" class="campoFormMediano"> </div>
						<div class="separadorH"></div>

						<div class="etiqueta <c:out value="${param.required}"/><c:if test="${not empty status.errorMessage}"> error</c:if>">
							<c:import url="comu/CampFormulari.jsp">
								<c:param name="tipus" value="checkbox" />
								<c:param name="path" value="formData.regadiu" />
								<c:param name="title"><fmt:message key="plantacio.camp.regadiu" /></c:param>
								<c:param name="camp" value="regadiu" />
							</c:import>
						</div>

						<c:if test="${not empty formData.id}">
							<div class="separadorH"></div>
							
							<div class="etiqueta <c:out value="${param.required}"/><c:if test="${not empty status.errorMessage}"> error</c:if>">
								<c:import url="comu/CampFormulari.jsp">
									<c:param name="tipus" value="checkbox" />
									<c:param name="path" value="formData.actiu" />
									<c:param name="title"><fmt:message key="plantacio.camp.activa" /></c:param>
									<c:param name="camp" value="actiu" />
								</c:import>
							</div>
						</c:if>
						<div class="separadorH"></div>

						<div id="observacionesForm" class="campoForm <c:out value="${param.required}"/><c:if test="${not empty status.errorMessage}"> error</c:if>">
							<c:import url="comu/CampFormulari.jsp">
								<c:param name="tipus" value="textarea" />
								<c:param name="path" value="formData.observacions" />
								<c:param name="title"><fmt:message key="plantacio.camp.observacions" /></c:param>
								<c:param name="camp" value="observacions" />
							</c:import>
						</div>
					</c:when>

					<%-- ROL DE OLIVICULTOR --%>
					<c:when test="${not empty esOlivicultor || not empty esDoControlador}">
						<input type="hidden" name="idFinca" id="idFinca" value="${fincaId}" />
						<c:import url="comu/CampFormulari.jsp">
							<c:param name="tipus" value="text" />
							<c:param name="path" value="formData.nomFinca" />
							<c:param name="required" value="required" />
							<c:param name="maxlength" value="128" />
							<c:param name="title"><fmt:message key="plantacio.camp.finca" /></c:param>
							<c:param name="camp" value="nomFinca" />
							<c:param name="clase" value="campoFormGrande" />
							<c:param name="readonly" value="readonly" />
							<c:param name="value" value="${formData.finca.nom}" />
						</c:import>
						<div class="separadorH"></div>

						<c:import url="comu/CampFormulari.jsp">
							<c:param name="tipus" value="select" />
							<c:param name="path" value="formData.municipiId" />
							<c:param name="title"><fmt:message key="plantacio.camp.municipi" /></c:param>
							<c:param name="camp" value="municipiId" />
							<c:param name="required" value="required" />
							<c:param name="selectItems" value="municipis" />
							<c:param name="selectItemsId" value="id" />
							<c:param name="selectItemsValue" value="nom" />
							<c:param name="selectSelectedValue" value="${formData.municipiId}" />
							<c:param name="clase" value="campoFormGrande" />
							<c:param name="disabled" value="disabled" />
						</c:import>
						<div class="separadorH"></div>
						
						<c:import url="comu/CampFormulari.jsp">
							<c:param name="tipus" value="text" />
							<c:param name="path" value="formData.poligon" />
							<c:param name="required" value="required" />
							<c:param name="maxlength" value="128" />
							<c:param name="title"><fmt:message key="plantacio.camp.poligon" /></c:param>
							<c:param name="camp" value="poligon" />
							<c:param name="clase" value="campoFormGrande" />
							<c:param name="disabled" value="disabled" />
						</c:import>
						<div class="separadorH"></div>

						<c:import url="comu/CampFormulari.jsp">
							<c:param name="tipus" value="text" />
							<c:param name="path" value="formData.parcela" />
							<c:param name="required" value="required" />
							<c:param name="maxlength" value="128" />
							<c:param name="title"><fmt:message key="plantacio.camp.parcela" /></c:param>
							<c:param name="camp" value="parcela" />
							<c:param name="clase" value="campoFormGrande" />
							<c:param name="disabled" value="disabled" />
						</c:import>
						<div class="separadorH"></div>

						<c:import url="comu/CampFormulari.jsp">
							<c:param name="tipus" value="text" />
							<c:param name="path" value="formData.anyPlantacio" />
							<c:param name="required" value="required" />
							<c:param name="maxlength" value="4" />
							<c:param name="title"><fmt:message key="plantacio.camp.anyPlantacio" /></c:param>
							<c:param name="camp" value="anyPlantacio" />
							<c:param name="clase" value="campoFormGrande" />
							<c:param name="disabled" value="disabled" />
						</c:import>
						<div class="separadorH"></div>

						<div class="etiqueta <c:out value="${param.required}"/><c:if test="${not empty status.errorMessage}"> error</c:if>">
							<c:import url="comu/CampFormulari.jsp">
								<c:param name="tipus" value="checkbox" />
								<c:param name="path" value="formData.regadiu" />
								<c:param name="title"><fmt:message key="plantacio.camp.regadiu" /></c:param>
								<c:param name="camp" value="regadiu" />
								<c:param name="disabled" value="disabled" />
							</c:import>
						</div>

						<c:if test="${not empty formData.id}">
							<div class="separadorH"></div>

							<div class="etiqueta <c:out value="${param.required}"/><c:if test="${not empty status.errorMessage}"> error</c:if>">
								<c:import url="comu/CampFormulari.jsp">
									<c:param name="tipus" value="checkbox" />
									<c:param name="path" value="formData.actiu" />
									<c:param name="title"><fmt:message key="plantacio.camp.activa" /></c:param>
									<c:param name="camp" value="actiu" />
									<c:param name="disabled" value="disabled" />
								</c:import>
							</div>
						</c:if>
						<div class="separadorH"></div>

						<div id="observacionesForm" class="campoForm <c:out value="${param.required}"/><c:if test="${not empty status.errorMessage}"> error</c:if>">
							<c:import url="comu/CampFormulari.jsp">
								<c:param name="tipus" value="textarea" />
								<c:param name="path" value="formData.observacions" />
								<c:param name="title"><fmt:message key="plantacio.camp.observacions" /></c:param>
								<c:param name="camp" value="observacions" />
								<c:param name="disabled" value="disabled" />
							</c:import>
						</div>
					</c:when>
				</c:choose>
				<div class="separadorH"></div>

				<div class="botonesForm">
					<c:choose>
						<c:when test="${not empty esDoGestor}">
							<c:if test="${not empty formData.id}">
								<div id="guardarForm" class="btnCorto"
										onclick="if(confirm('<fmt:message key="manteniment.modificar.confirm"/>')){submitFormAction('formulario','PlantacioForm.html?_finish&idOlivicultor=<c:out value='${idOlivicultor}'/>');}"
										onmouseover="underline('enlaceGuardarForm')"
										onmouseout="underline('enlaceGuardarForm')">
									<a id="enlaceGuardarForm" href="javascript:void(0);">
										<fmt:message key="manteniment.guardar" />
									</a>
								</div>
							</c:if>
							<c:if test="${empty formData.id}">
								<div id="guardarForm" class="btnCorto"
										onclick="submitFormAction('formulario','PlantacioForm.html?_finish&idOlivicultor=<c:out value='${idOlivicultor}'/>');"
										onmouseover="underline('enlaceGuardarForm')"
										onmouseout="underline('enlaceGuardarForm')">
									<a id="enlaceGuardarForm" href="javascript:void(0);">
										<fmt:message key="manteniment.aceptar" />
									</a>
								</div>
							</c:if>
							<c:choose>
								<c:when test="${not empty param.fromFinca}">
									<div id="cancelarForm" class="btnCorto"
											onmouseover="underline('enlaceCancelarForm')"
											onmouseout="underline('enlaceCancelarForm')"
											onclick="document.location ='FincasForm.html?id=<c:out value='${param.fromFinca}'/>&amp;idOlivicultor=<c:out value='${idOlivicultor}'/>&amp;idFinca=<c:out value='${fincaId}'/>';">
										<a id="enlaceCancelarForm" href="javascript:void(0);">
											<fmt:message key="manteniment.tornar" />
										</a>
									</div>
									<input id="fromFincaId" type="hidden" value="<c:out value='${param.fromFinca}'/>" name="fromFinca" />
								</c:when>
								<c:otherwise>
									<div id="cancelarForm" class="btnCorto"
											onmouseover="underline('enlaceCancelarForm')"
											onmouseout="underline('enlaceCancelarForm')"
											onclick="document.location ='Plantacio.html?idOlivicultor=<c:out value='${idOlivicultor}'/>&amp;idFinca=<c:out value='${fincaId}'/>';">
										<a id="enlaceCancelarForm" href="javascript:void(0);">
											<fmt:message key="manteniment.tornar" />
										</a>
									</div>
								</c:otherwise>
							</c:choose>
							
							<div id="descomposicio" class="btnLargo"
									onclick="submitFormAction('formulario','PlantacioForm.html?_target1&idOlivicultor=<c:out value='${idOlivicultor}'/>')"
									onmouseover="underline('enlaceDescomposicio')"
									onmouseout="underline('enlaceDescomposicio')">
								<a id="enlaceDescomposicio" href="javascript:void(0);">
									<fmt:message key="manteniment.descomposicio" />
								</a>
							</div>
							
							<c:if test="${not empty formData.id}">
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

						<c:when test="${not empty esOlivicultor || not empty esDoControlador}">
							<c:choose>
								<c:when test="${not empty param.fromFinca}">
									<div id="cancelarForm" class="btnCorto"
											onmouseover="underline('enlaceCancelarForm')"
											onmouseout="underline('enlaceCancelarForm')"
											onclick="document.location ='FincasForm.html?id=<c:out value='${param.fromFinca}'/>&amp;idOlivicultor=<c:out value='${idOlivicultor}'/>'&amp;idFinca=<c:out value='${fincaId}'/>;">
										<a id="enlaceCancelarForm" href="javascript:void(0);">
											<fmt:message key="manteniment.tornar" />
										</a>
									</div>
									<input id="fromFincaId" type="hidden" value="<c:out value='${param.fromFinca}'/>" name="fromFinca" />
								</c:when>
								<c:otherwise>
									<div id="cancelarForm" class="btnCorto"
											onmouseover="underline('enlaceCancelarForm')"
											onmouseout="underline('enlaceCancelarForm')"
											onclick="document.location ='Plantacio.html?idOlivicultor=<c:out value='${idOlivicultor}'/>&amp;idFinca=<c:out value='${fincaId}'/>';">
										<a id="enlaceCancelarForm" href="javascript:void(0);">
											<fmt:message key="manteniment.tornar" />
										</a>
									</div>
								</c:otherwise>
							</c:choose>
							
							<div id="descomposicio" class="btnLargo"
									onclick="submitFormAction('formulario','PlantacioForm.html?_target1&idOlivicultor=<c:out value='${idOlivicultor}'/>')"
									onmouseover="underline('enlaceDescomposicio')"
									onmouseout="underline('enlaceDescomposicio')">
								<a id="enlaceDescomposicio" href="javascript:void(0);">
									<fmt:message key="manteniment.descomposicio" />
								</a>
							</div>
						</c:when>
					</c:choose>
				</div>
			</form>

			<form id="deleteForm" action="Plantacio.html" method="post" class="seguit" onsubmit="return confirm('<fmt:message key="manteniment.estasegur"/>')">
				<input id="id" name="id" value="<c:out value="${formData.id}"/>" type="hidden" />
				<input id="idOlivicultor" name="idOlivicultor" value="<c:out value="${idOlivicultor}"/>" type="hidden" />
				<input type="hidden" name="idFinca" id="idFinca" value="${fincaId}" />
				<c:if test="${not empty param.fromFinca}">
					<input id="fromFincaId" type="hidden" value="<c:out value='${param.fromFinca}'/>" name="fromFinca" />
				</c:if>
				<input id="action" name="action" value="delete" type="hidden" />
			</form>
		</div>
		
		<script type="text/javascript">
			jQuery(document).ready(function(){
				edatPlantacio();
			})
		</script>
	
	</body>
</html>
