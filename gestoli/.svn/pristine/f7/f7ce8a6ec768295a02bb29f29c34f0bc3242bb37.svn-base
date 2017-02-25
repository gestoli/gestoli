<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>

<html>
	<head>
		<title><fmt:message key="modificar.document.sortida.title" /></title>
	</head>

	<body>
		<div>
			${infoSortida}<br/>
			
			<form id="formulario" action="ModificarDocumentSortidaForm.html" method="post" class="seguit">
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="hidden" />
					<c:param name="path" value="formData.idSortida" />
					<c:param name="camp" value="idSortida" />
				</c:import>
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="hidden" />
					<c:param name="path" value="formData.tipusSortida" />
					<c:param name="camp" value="tipusSortida" />
				</c:import>
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="text" />
					<c:param name="path" value="formData.tipusDocument" />
					<c:param name="title"><fmt:message key="sortidaLot.camp.tipusDocument" /></c:param>
					<c:param name="camp" value="tipusDocument" />
					<c:param name="name" value="tipusDocument" />
					<c:param name="maxlength" value="64" />
					<c:param name="clase" value="campoFormMediano margen120" />
				</c:import>
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="text" />
					<c:param name="path" value="formData.document" />
					<c:param name="title"><fmt:message key="sortidaLot.camp.numeroDocument" /></c:param>
					<c:param name="camp" value="document" />
					<c:param name="name" value="document" />
					<c:param name="maxlength" value="64" />
					<c:param name="clase" value="campoFormMediano" />
				</c:import>
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
				</div>
			</form>
		</div>
	</body>
</html>
