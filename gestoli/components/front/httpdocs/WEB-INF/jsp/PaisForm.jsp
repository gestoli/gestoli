<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>
<%@ page buffer="64kb"%>
<%@ page autoFlush="true"%>

<html>
	<head>
		<title><fmt:message key="configuracio.pais.title" /></title>
	</head>

	<body>
		<div>
			<form id="formulario" action="PaisForm.html" method="post" class="seguit">
				<display:table name="paisos" id="pais" requestURI="" export="true" sort="list" cellpadding="0" cellspacing="0" class="listadoEstrecho selectable">
					<display:column titleKey="configuracio.pais.nom" sortable="true" sortProperty="nomcat">
						<c:out value="${pais.nomcat}" />
					</display:column>
					<display:column titleKey="configuracio.pais.ue">
						<c:import url="comu/CampFormulari.jsp">
							<c:param name="tipus" value="hidden" />
							<c:param name="path" value="formData.paisos[${pais_rowNum - 1}].id" />
							<c:param name="camp" value="paisos[${pais_rowNum - 1}].id" />
						</c:import>
						<c:import url="comu/CampFormulari.jsp">
							<c:param name="tipus" value="checkbox" />
							<c:param name="path" value="formData.paisos[${pais_rowNum - 1}].ue" />
							<c:param name="camp" value="paisos[${pais_rowNum - 1}].ue" />
						</c:import>
					</display:column>
					<display:setProperty name="export.xml" value="false" />
					<display:setProperty name="export.csv" value="false" />
					<display:setProperty name="export.rtf" value="false" />
					<display:setProperty name="export.pdf" value="false" />
					<display:setProperty name="export.excel.include_header" value="true" />
					<display:setProperty name="export.excel.filename" value="paisos.xls" />
					<display:setProperty name="export.decorated" value="true" />
				</display:table>
				<div class="separadorH"></div>

				<div class="botonesForm">
					<div id="guardarForm" class="btnCorto"
							onclick="if(confirm('<fmt:message key="manteniment.confirmar"/>')){submitForm('formulario')}"
							onmouseover="underline('enlaceGuardarForm')"
							onmouseout="underline('enlaceGuardarForm')">
						<a id="enlaceGuardarForm" href="javascript:void(0);">
							<fmt:message key="configuracio.pais.acceptar" />
						</a>
					</div>
				</div>
			</form>
		</div>
	</body>
</html>
