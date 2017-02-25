<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>
<%@ page import="es.caib.gestoli.front.util.*"%>
<%@ page import="java.util.ResourceBundle"%>
<%@ page import="java.util.Locale"%>
<%@ page import="java.util.List"%>
<%
	String lang = Idioma.getLang(request);
	request.setAttribute("lang",lang);
%>

<html>
<head>
<title><c:choose>
	<c:when test="${not empty formData.id}">
		<fmt:message key="manteniment.modificacio" />
	</c:when>
	<c:otherwise>
		<fmt:message key="manteniment.alta" />
	</c:otherwise>
</c:choose> <fmt:message key="informacio.tipusdemant" /></title>
<script type="text/javascript" src="dwr/interface/contenidorService.js"></script>
<script type="text/javascript" src="dwr/interface/processosService.js"></script>
<script type="text/javascript" src="dwr/engine.js"></script>
<script type="text/javascript" src="dwr/util.js"> </script>
<script type="text/javascript" src="js/displaytag.js"></script>
<script type="text/javascript" src="js/calendar/calendar.js"></script>
<script type="text/javascript" src="js/calendar/calendar-setup.js"></script>
<%
		if(lang.equalsIgnoreCase("ca")){
	%>
<script type="text/javascript" src="js/calendar/lang/calendar-ca.js"></script>
<script type="text/javascript" src="js/calendar/lang/calendar-es.js"></script>
<%		
		}else{
	%>
<script type="text/javascript" src="js/calendar/lang/calendar-es.js"></script>
<%
		}
	%>
<link href="js/calendar/calendar-viti.css" rel="stylesheet"
	type="text/css" />

<script type="text/javascript" src="js/form.js"></script>

</head>
<body>

<form id="formulario" name="gestionarInformacioForm"
	action="GestionarInformacioForm.html" method="post"
	class="extended seguit" onsubmit=""><c:import
	url="comu/CampFormulari.jsp">
	<c:param name="tipus" value="calendar" />
	<c:param name="path" value="formData.data" />
	<c:param name="title">
		<fmt:message key="informacio.camp.data" />
	</c:param>
	<c:param name="camp" value="campo_data" />
	<c:param name="name" value="data" />
	<c:param name="maxlength" value="10" />
	<c:param name="required" value="required" />
	<c:param name="aclaracio">
		<fmt:message key="proces.aclaracio.formatdata" />
	</c:param>
	<c:param name="clase" value="conMargen campoForm165" />
</c:import> <c:if test="${not empty formData.id}">
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="hidden" />
		<c:param name="path" value="formData.id" />
		<c:param name="camp" value="id" />
		<c:param name="name" value="id" />
		<c:param name="value" value="${formData.id}" />
	</c:import>
</c:if> <c:import url="comu/CampFormulari.jsp">
	<c:param name="tipus" value="select" />
	<c:param name="path" value="formData.idCategoria" />
	<c:param name="required" value="required" />
	<c:param name="title">
		<fmt:message key="informacio.camp.categoria" />
	</c:param>
	<c:param name="camp" value="campo_categoria" />
	<c:param name="name" value="idCategoria" />
	<c:param name="selectItems" value="categorias" />
	<c:param name="selectItemsId" value="id" />
	<c:param name="selectItemsValue" value="nom" />
	<c:param name="selectSelectedValue"
		value="${formData.categoriaInformacio.id}" />
	<c:param name="clase" value="campoFormGrande" />
</c:import>

<div class="separadorH"></div>

<c:import url="comu/CampFormulari.jsp">
	<c:param name="tipus" value="text" />
	<c:param name="path" value="formData.titol" />
	<c:param name="title">
		<fmt:message key="informacio.camp.titol" />
	</c:param>
	<c:param name="camp" value="campo_titol" />
	<c:param name="name" value="titol" />
	<c:param name="required" value="required" />
	<c:param name="maxlength" value="128" />
	<c:param name="clase" value="campoFormCompleto" />
</c:import>

<div class="separadorH"></div>

<div id="observacionesForm"
	class="campoForm <c:out value="${param.required}"/><c:if test="${not empty status.errorMessage}"> error</c:if>">
<c:import url="comu/CampFormulari.jsp">
	<c:param name="tipus" value="textarea" />
	<c:param name="path" value="formData.texte" />
	<c:param name="title">
		<fmt:message key="informacio.camp.texte" />
	</c:param>
	<c:param name="camp" value="texte" />
</c:import></div>

<div class="separadorH"></div>

<c:if test="${formData.id != null && not empty formData.id}">
	<c:if test="${not empty llistatDocuments}">

		<div class="campoForm"><label for="documents"><fmt:message
			key="informacio.camp.documents" /></label>
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

		<display:table name="llistatDocuments" requestURI="" id="document"
			pagesize="" sort="list" cellpadding="0" cellspacing="0"
			class="listadoEstrecho selectable">
			<display:column titleKey="document.camp.titol" headerClass=""
				sortable="true" sortProperty="titol">
				<a
					href="GestionarDocumentForm.html?id=<c:out value="${document.id}"/>"><c:out
					value="${document.titol}" /></a>
			</display:column>
			<display:column titleKey="document.camp.tamany"
				headerClass="ancho100" sortable="true">
				<c:out value="${document.arxiuObject.normalizeSize}" />
			</display:column>
			<display:column sortable="false" headerClass="ancho32 final">
				<a
					href="ArxiuMostrar.html?id=<c:out value="${document.arxiu}"/>&download=true">
				<div class="iconoVer" title="<fmt:message key="document.ver"/>"></div>
				</a>
			</display:column>
		</display:table></div>
		</div>
		</div>

	</c:if>

	<div class="separadorH"></div>


	<div class="btnLargo203" onmouseover="underline('enlaceCrearForm')"
		onmouseout="underline('enlaceCrearForm')"
		onclick="document.formularioDocument.submit();"><a
		id="enlaceCrearForm" href="javascript:void(0);"><fmt:message
		key="manteniment.creardocument" /></a></div>
</c:if>

<div class="separadorH"></div>

<c:if test="${formData.id != null && not empty formData.id}">

	<div
		class="etiqueta <c:out value="${param.required}"/><c:if test="${not empty status.errorMessage}"> error</c:if>">
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="checkbox" />
		<c:param name="path" value="formData.informar" />
		<c:param name="title">
			<fmt:message key="informacio.camp.informar" />
		</c:param>
		<c:param name="camp" value="informar" />
	</c:import></div>
</c:if>

<div class="separadorH"></div>

<div class="botonesForm">
	<c:if test="${not empty formData.id}">
		<div id="guardarForm" class="btnCorto"
			onclick="if(confirm('<fmt:message key="manteniment.modificar.confirm"/>')){submitForm('formulario')}"
			onmouseover="underline('enlaceGuardarForm')"
			onmouseout="underline('enlaceGuardarForm')"><a
			id="enlaceGuardarForm" href="javascript:void(0);"><fmt:message
			key="manteniment.guardar" /></a></div>
	</c:if> 
	<c:if test="${empty formData.id}">
		<div id="guardarForm" class="btnCorto"
			onclick="submitForm('formulario')"
			onmouseover="underline('enlaceGuardarForm')"
			onmouseout="underline('enlaceGuardarForm')"><a
			id="enlaceGuardarForm" href="javascript:void(0);"><fmt:message
			key="manteniment.guardar" /></a></div>
	</c:if>

	<div class="btnCorto" onclick="submitForm('tornarForm')"
		onmouseover="underline('enlaceTornarForm')"
		onmouseout="underline('enlaceTornarForm')"><a
		id="enlaceTornarForm" href="javascript:void(0);"><fmt:message
		key="proces.tornar" /></a></div>

	<c:if test="${not empty formData.id}">
		<div id="eliminarForm" class="btnCorto"
			onmouseover="underline('enlaceBorrarForm')"
			onmouseout="underline('enlaceBorrarForm')"
			onclick="submitFormConfirm('deleteForm','<fmt:message key="manteniment.esborrar.confirm"/>');">
		<a id="enlaceBorrarForm" href="javascript:void(0);"><fmt:message
			key="manteniment.esborrar" /></a></div>
	</c:if>
</div>

</form>

<form id="tornarForm" action="GestionarInformacio.html" class="seguit">
</form>

<form name="formularioDocument" action="GestionarDocumentForm.html"
	class="seguit"><input id="idInformacio" name="idInformacio"
	value="<c:out value="${formData.id}"/>" type="hidden" /></form>

<form id="deleteForm" action="GestionarInformacio.html" method="post"
	class="seguit"
	onsubmit="return confirm('<fmt:message key="manteniment.estasegur"/>')">
<input id="id" name="id" value="<c:out value="${formData.id}"/>"
	type="hidden" /> <input id="action" name="action" value="delete"
	type="hidden" /></form>


<!-- Colores en tablas -->
<script type="text/javascript">
		jQuery(document).ready(function(){
			setEstilosTabla();
		})
	</script>

</body>
</html>