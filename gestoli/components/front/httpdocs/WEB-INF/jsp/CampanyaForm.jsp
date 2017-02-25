<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>

<html>
	<head>
		<title><fmt:message key="accio.campanya.title" /></title>
	</head>

	<body>
		<div>
			<%-- PANTALLA FORMULARIO --%>
			<c:if test="${empty formData.id}">
				<form id="formulario" action="Campanya.html" method="post" class="seguit">
					<fieldset>
						<c:if test="${not empty formData.id}">
							<c:import url="comu/CampFormulari.jsp">
								<c:param name="tipus" value="hidden" />
								<c:param name="path" value="formData.id" />
								<c:param name="camp" value="id" />
							</c:import>
						</c:if>
						<c:import url="comu/CampFormulari.jsp">
							<c:param name="tipus" value="text" />
							<c:param name="path" value="formData.nom" />
							<c:param name="required" value="required" />
							<c:param name="title"><fmt:message key="accio.campanya.camp.nom" /></c:param>
							<c:param name="camp" value="nom" />
							<c:param name="clase" value="campoFormGrande" />
						</c:import>
						<div class="separadorH"></div>

						<div class="etiqueta <c:out value="${param.required}"/><c:if test="${not empty status.errorMessage}"> error</c:if>">
							<c:import url="comu/CampFormulari.jsp">
								<c:param name="tipus" value="checkbox" />
								<c:param name="path" value="formData.generarPrefactures" />
								<c:param name="title"><fmt:message key="accio.campanya.camp.generarPrefactures" /></c:param>
								<c:param name="camp" value="generarPrefactures" />
							</c:import>
						</div>

						<div id="observacionesForm" class="campoForm <c:out value="${param.required}"/><c:if test="${not empty status.errorMessage}"> error</c:if>">
							<c:import url="comu/CampFormulari.jsp">
								<c:param name="tipus" value="textarea" />
								<c:param name="path" value="formData.observacions" />
								<c:param name="title"><fmt:message key="accio.campanya.camp.observacions" /></c:param>
								<c:param name="camp" value="observacions" />
							</c:import>
						</div>
						<div class="separadorH"></div>

						<div class="botonesForm">
							<div id="guardarForm" class="btnCorto"
									onclick="if(confirm('<fmt:message key="manteniment.novaCampanya.confirm"/>')){submitForm('formulario')}"
									onmouseover="underline('enlaceGuardarForm')"
									onmouseout="underline('enlaceGuardarForm')">
								<a id="enlaceGuardarForm" href="javascript:void(0);">
									<fmt:message key="accio.campanya.acceptar" />
								</a>
							</div>
						</div>
					</fieldset>
				</form>
			</c:if>
			
			<%-- PANTALLA DE IMPRIMIR PREFACTURAS --%>
			<c:if test="${not empty formData.id}">
				<c:if test="${formData.generarPrefactures == true}">
					<form id="formulario" action="GenerarPdf.html" method="post" class="seguit">
						<fieldset>
							<input type="hidden" id="tipus" name="tipus" value="1" />
							<input type="hidden" id="prefactura" name="prefactura" value="1" />
							<div class="botonesForm">
								<div id="guardarForm" class="btnLargo156"
										onclick="if(confirm('<fmt:message key="manteniment.confirmar"/>')){submitForm('formulario')}"
										onmouseover="underline('enlaceGuardarForm')"
										onmouseout="underline('enlaceGuardarForm')">
									<a id="enlaceGuardarForm" href="javascript:void(0);">
										<fmt:message key="accio.campanya.generarPrefactures" />
									</a>
								</div>
							</div>
						</fieldset>
					</form>
				</c:if>
				<form id="formulariRend" action="RendimentVarietatForm.html" method="get" class="seguit">
					<input type="hidden" id="idCampanya" name="idCampanya" value="${formData.id}" />
					<fieldset>
						<div class="botonesForm">
							<div id="guardarFormRend" class="btnLargo"
									onclick="if(confirm('<fmt:message key="manteniment.confirmar"/>')){submitForm('formulariRend')}"
									onmouseover="underline('enlaceGuardarFormRend')"
									onmouseout="underline('enlaceGuardarFormRend')">
								<a id="enlaceGuardarFormRend" href="javascript:void(0);">
									<fmt:message key="accio.campanya.rendiment" />
								</a>
							</div>
						</div>
					</fieldset>
				</form>
			</c:if>
		</div>
	</body>
</html>
