<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>


<html>
<head>
<title><c:choose>
	<c:when test="${not empty formData.id}">
		<fmt:message key="manteniment.modificacio" />
	</c:when>
	<c:otherwise>
		<fmt:message key="manteniment.alta" />
	</c:otherwise>
</c:choose> <fmt:message key="taxa.tipusdemant" /></title>
</head>
<body>
<div class="taxa">
<form id="formulario" action="TaxaForm.html" method="post"
	class="seguit"><c:if test="${not empty formData.id}">
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="hidden" />
		<c:param name="path" value="formData.id" />
		<c:param name="camp" value="id" />
	</c:import>
</c:if> <c:import url="comu/CampFormulari.jsp">
	<c:param name="tipus" value="text" />
	<c:param name="path" value="formData.taxaPlantacioMajorIgual75" />
	<c:param name="required" value="required" />
	<c:param name="title">
		<fmt:message key="taxa.camp.taxamayor" />
	</c:param>
	<c:param name="camp" value="taxaPlantacioMajorIgual75" />
	<c:param name="clase" value="campoFormGenerico80" />
</c:import>

<div class="separadorH"></div>

<c:import url="comu/CampFormulari.jsp">
	<c:param name="tipus" value="text" />
	<c:param name="path" value="formData.taxaPlantacioMenor75" />
	<c:param name="required" value="required" />
	<c:param name="title">
		<fmt:message key="taxa.camp.taxamenor" />
	</c:param>
	<c:param name="camp" value="taxaPlantacioMenor75" />
	<c:param name="clase" value="campoFormGenerico80" />
</c:import>

<div class="separadorH"></div>

<c:import url="comu/CampFormulari.jsp">
	<c:param name="tipus" value="text" />
	<c:param name="path" value="formData.taxaVolumEnvasar" />
	<c:param name="required" value="required" />
	<c:param name="title">
		<fmt:message key="taxa.camp.volumen" />
	</c:param>
	<c:param name="camp" value="taxaVolumEnvasar" />
	<c:param name="clase" value="campoFormGenerico80" />
</c:import>

<div class="separadorH"></div>

<c:import url="comu/CampFormulari.jsp">
	<c:param name="tipus" value="text" />
	<c:param name="path" value="formData.taxaContraEtiqueta" />
	<c:param name="required" value="required" />
	<c:param name="title">
		<fmt:message key="taxa.camp.etiqueta" />
	</c:param>
	<c:param name="camp" value="taxaContraEtiqueta" />
	<c:param name="clase" value="campoFormGenerico80" />
</c:import>

<div class="separadorH"></div>

<div class="botonesForm">
<div id="guardarForm" class="btnCorto"
	onclick="if(confirm('<fmt:message key="manteniment.modificar.confirm"/>')){submitForm('formulario')}"
	onmouseover="underline('enlaceGuardarForm')"
	onmouseout="underline('enlaceGuardarForm')"><a
	id="enlaceGuardarForm" href="javascript:void(0);"><fmt:message
	key="manteniment.guardar" /></a></div>

<div id="cancelarForm" class="btnCorto"
	onmouseover="underline('enlaceCancelarForm')"
	onmouseout="underline('enlaceCancelarForm')"
	onclick="document.location ='TaxaForm.html';"><a
	id="enlaceCancelarForm" href="javascript:void(0);"><fmt:message
	key="manteniment.tornar" /></a></div>
</div>


</form>
</div>
</body>
</html>
