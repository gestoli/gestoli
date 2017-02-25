<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>

<html>
	<head>
		<title><fmt:message key="accio.rendiment.varietat.title" /></title>
		<style type="text/css">
			.campoFormAncho { margin: 0 !important; width: 100% !important; }
		</style>
	</head>

	<body>
		<div>
			<form id="formulario" action="RendimentVarietatForm.html" method="post" class="seguit">
				<input type="hidden" id="idCampanya" name="idCampanya" value="${idCampanya}" />
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="radio" />
					<c:param name="path" value="formData.tipusRendimentCampanya" />
					<c:param name="title"><fmt:message key="accio.rendiment.varietat.tipus.rendiment" /></c:param>
					<c:param name="camp" value="tipusRendimentCampanya" />
					<c:param name="required" value="required" />
					<c:param name="selectItems" value="tipusRendiment" />
					<c:param name="selectItemsId" value="value" />
					<c:param name="selectItemsValue" value="label" />
					<c:param name="selectSelectedValue" value="${formData.tipusRendimentCampanya}" />
				</c:import>
				<display:table name="varietats" id="registre" requestURI="" sort="list" cellpadding="0" cellspacing="0" class="listadoEstrecho selectable">
					<display:column titleKey="accio.rendiment.varietat.varietat.nom" sortable="true" sortProperty="nomVarietat">
						<c:choose>
							<c:when test="${not empty registre.varietatOliva.nomVarietat}"><c:out value="${registre.varietatOliva.nomVarietat}" /></c:when>
							<c:otherwise><c:out value="${registre.varietatOliva.nom}" /></c:otherwise>
						</c:choose>
					</display:column>
					<display:column titleKey="accio.rendiment.varietat.varietat.rendiment">
						<c:import url="comu/CampFormulari.jsp">
							<c:param name="tipus" value="hidden" />
							<c:param name="path" value="formData.rendiments[${registre_rowNum - 1}].idVarietatOliva" />
							<c:param name="camp" value="rendiments[${registre_rowNum - 1}].idVarietatOliva" />
						</c:import>
						<c:import url="comu/CampFormulari.jsp">
							<c:param name="tipus" value="text" />
							<c:param name="path" value="formData.rendiments[${registre_rowNum - 1}].rendiment" />
							<c:param name="camp" value="rendiments[${registre_rowNum - 1}].rendiment" />
							<c:param name="clase" value="campoFormAncho campoFormGrande conMargen" />
							<c:param name="required" value="required" />
						</c:import>
					</display:column>
				</display:table>
				<div class="separadorH"></div>

				<div class="botonesForm">
					<div id="guardarForm" class="btnCorto"
							onclick="if(confirm('<fmt:message key="manteniment.confirmar"/>')){submitForm('formulario')}"
							onmouseover="underline('enlaceGuardarForm')"
							onmouseout="underline('enlaceGuardarForm')">
						<a id="enlaceGuardarForm" href="javascript:void(0);">
							<fmt:message key="accio.rendiment.varietat.acceptar" />
						</a>
					</div>
				</div>
			</form>
		</div>
	</body>
</html>
