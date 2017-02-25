<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>
<%@ page buffer="64kb"%>
<%@ page autoFlush="true"%>


<html>
<head>
<title><fmt:message key="llegirInformacio.llistitol" /></title>
<script type="text/javascript" src="js/displaytag.js"></script>
<%-- <link href="css/displaytag.css" rel="stylesheet" type="text/css"/> --%>

<script type="text/javascript">

		function submitFiltre(){
			var filtre = document.getElementById("filtre");
			var url = "GestorDocumentalLlegirInformacio.html?idCat="+filtre.value;
			window.location=url;
		}

    </script>


</head>
<body>

<c:import url="comu/CampFormulari.jsp">
	<c:param name="tipus" value="select" />
	<c:param name="path" value="formData.idCategoria" />
	<c:param name="title">
		<fmt:message key="llegirInformacio.camp.categoria" />
	</c:param>
	<c:param name="camp" value="filtre" />
	<c:param name="name" value="filtre" />
	<c:param name="selectItems" value="categorias" />
	<c:param name="selectItemsId" value="id" />
	<c:param name="selectItemsValue" value="nom" />
	<c:param name="selectSelectedValue" value="${formData.idCategoria}" />
	<c:param name="clase" value="campoFormGrande margenIzq37" />
	<c:param name="onchange" value="submitFiltre()" />
</c:import>

<div class="separadorH"></div>

<div id="listadoAncho"><%-- Tabla de resultados --%>
<div class="layoutSombraTabla ancho"><c:if
	test="${not empty llistat}">
	<div class="rellenoInf"></div>
	<div class="rellenoIzq"></div>
	<div class="rellenoDer"></div>
	<div class="supDer"></div>
	<div class="supIzq"></div>
	<div class="infIzq"></div>
	<div class="infDer"></div>
</c:if> <display:table name="llistat" requestURI="" id="informacio" export="true" sort="list" cellpadding="0" cellspacing="0" class="listadoAncho selectable">
	<display:column titleKey="llegirInformacio.camp.data" sortable="true" headerClass="ancho100">
		<a href="GestorDocumentalLlegirInformacioForm.html?id=<c:out value="${informacio.id}"/>"><c:out value="${informacio.dataFormat}" /></a>
	</display:column>
	<display:column titleKey="llegirInformacio.camp.titol" sortable="true">
		<c:out value="${informacio.titol}" />
	</display:column>
	<display:column titleKey="llegirInformacio.camp.categoria" sortable="true" headerClass="ancho210">
		<c:out value="${informacio.gestorDocumentalCategoriaInformacio.nom}" />
	</display:column>
	<display:column titleKey="llegirInformacio.camp.documents" sortable="true" headerClass="ancho100">
		<c:choose>
			<c:when test="${informacio.teDocuments}">
				<fmt:message key="manteniment.si" />
			</c:when>
			<c:otherwise>
				<fmt:message key="manteniment.no" />
			</c:otherwise>
		</c:choose>
	</display:column>
	<display:column titleKey="llegirInformacio.camp.llegit" sortable="true"
		headerClass="ancho75 final">
		<c:choose>
			<c:when test="${informacio.llegit}">
				<fmt:message key="manteniment.si" />
			</c:when>
			<c:otherwise>
				<fmt:message key="manteniment.no" />
			</c:otherwise>
		</c:choose>
	</display:column>
	<display:setProperty name="export.xml" value="false" />
	<display:setProperty name="export.csv" value="false" />
	<display:setProperty name="export.rtf" value="false" />
	<display:setProperty name="export.pdf" value="false" />
	<display:setProperty name="export.excel.include_header" value="true" />
	<display:setProperty name="export.excel.filename" value="Informacions.xls" />
	<display:setProperty name="export.decorated" value="true" />
</display:table></div>

</div>

<c:if test="${not empty llistat}">
	<%-- Colores en tablas --%>
	<script type="text/javascript">
			$(document).ready(function(){
				setEstilosTabla();
			})
		</script>
</c:if>


</body>
</html>
