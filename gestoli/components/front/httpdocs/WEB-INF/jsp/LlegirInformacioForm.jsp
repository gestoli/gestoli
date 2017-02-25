<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>
<%@ page import="es.caib.gestoli.front.util.*"%>
<%@ page import="java.util.ResourceBundle"%>
<%@ page import="java.util.Locale"%>
<%
	String lang = Idioma.getLang(request);
	request.setAttribute("lang",lang);
%>

<html>
<head>
<title><fmt:message key="manteniment.consulta" /> <fmt:message
	key="llegirInformacio.tipusdemant" /></title>
<script type="text/javascript" src="dwr/interface/contenidorService.js"></script>
<script type="text/javascript" src="dwr/interface/processosService.js"></script>
<script type="text/javascript" src="dwr/engine.js"></script>
<script type="text/javascript" src="dwr/util.js"> </script>
<script type="text/javascript" src="js/displaytag.js"></script>

<!--script type="text/javascript" src="js/form.js"></script-->

</head>
<body>

<form id="tornarForm" action="LlegirInformacio.html"
	class="extended seguit"><c:import url="comu/CampFormulari.jsp">
	<c:param name="tipus" value="text" />
	<c:param name="path" value="formData.dataFormat" />
	<c:param name="title">
		<fmt:message key="llegirInformacio.camp.data" />
	</c:param>
	<c:param name="camp" value="campo_data" />
	<c:param name="name" value="data" />
	<c:param name="maxlength" value="10" />
	<c:param name="clase" value="campoFormMediano readOnly conMargen" />
	<c:param name="readonly" value="true" />
</c:import> <c:import url="comu/CampFormulari.jsp">
	<c:param name="tipus" value="text" />
	<c:param name="path" value="formData.nomCategoria" />
	<c:param name="title">
		<fmt:message key="llegirInformacio.camp.categoria" />
	</c:param>
	<c:param name="camp" value="campo_categoria" />
	<c:param name="name" value="categoria" />
	<c:param name="maxlength" value="128" />
	<c:param name="clase" value="campoFormGrande readOnly" />
	<c:param name="readonly" value="true" />
</c:import>

<div class="separadorH"></div>

<c:import url="comu/CampFormulari.jsp">
	<c:param name="tipus" value="text" />
	<c:param name="path" value="formData.titol" />
	<c:param name="title">
		<fmt:message key="llegirInformacio.camp.titol" />
	</c:param>
	<c:param name="camp" value="campo_titol" />
	<c:param name="name" value="titol" />
	<c:param name="maxlength" value="128" />
	<c:param name="clase" value="campoFormCompleto readOnly" />
	<c:param name="readonly" value="true" />
</c:import>

<div class="separadorH"></div>

<div id="observacionesForm"
	class="campoForm <c:out value="${param.required}"/><c:if test="${not empty status.errorMessage}"> error</c:if>">
<c:import url="comu/CampFormulari.jsp">
	<c:param name="tipus" value="textarea" />
	<c:param name="path" value="formData.texte" />
	<c:param name="title">
		<fmt:message key="llegirInformacio.camp.texte" />
	</c:param>
	<c:param name="camp" value="texte" />
	<c:param name="clase" value="readOnly" />
	<c:param name="readonly" value="true" />
</c:import></div>

<div class="separadorH"></div>

<c:if test="${not empty documentos}">

	<div class="campoForm"><label><fmt:message
		key="informacio.camp.documents" /></label>
	<div class="separadorH"></div>
	<div id="listadoEstrecho"><%-- Tabla de resultados --%>
	<div class="layoutSombraTabla ancho">
	<div class="rellenoInf"></div>
	<div class="rellenoIzq"></div>
	<div class="rellenoDer"></div>
	<div class="supDer"></div>
	<div class="supIzq"></div>
	<div class="infIzq"></div>
	<div class="infDer"></div>

	<display:table name="documentos" requestURI="" id="document" sort="list" cellpadding="0" cellspacing="0" class="listadoEstrecho noEnlace">
		<display:column titleKey="document.camp.titol" sortable="true">
			<c:out value="${document.titol}" />
		</display:column>
		<display:column titleKey="document.camp.tamany" headerClass="ancho100"
			sortable="true">
			<c:out value="${document.arxiuObject.normalizeSize}" />
		</display:column>
		<display:column headerClass="ancho32 final">
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

<div class="botonesForm">

<div class="btnCorto" onclick="window.location='LlegirInformacio.html';"
	onmouseover="underline('enlaceTornarForm')"
	onmouseout="underline('enlaceTornarForm')"><a
	id="enlaceTornarForm" href="javascript:void(0);"><fmt:message
	key="proces.tornar" /></a></div>

</div>

</form>

<!-- Colores en tablas -->
<script type="text/javascript">
		jQuery(document).ready(function(){
			setEstilosTabla();
		})
	</script>


</body>
</html>