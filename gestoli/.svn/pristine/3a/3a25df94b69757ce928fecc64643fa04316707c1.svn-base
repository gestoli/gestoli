<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>
<%@ page import="es.caib.gestoli.front.util.*"%>
<%@ page import="java.util.ResourceBundle"%>
<%@ page import="java.util.Locale"%>
<%@ page buffer="64kb"%>
<%@ page autoFlush="true"%>
<%
	String lang = Idioma.getLang(request);
	request.setAttribute("lang",lang);
%>

<html>
<head>
<title><fmt:message key="informacioUtil.llistitol" /></title>
<script type="text/javascript" src="js/displaytag.js"></script>
<%-- <link href="css/displaytag.css" rel="stylesheet" type="text/css"/> --%>
</head>
<body>

<div id="listado"><%-- Tabla de resultados --%>
	<div class="layoutSombraTabla ancho">
		<c:if
			test="${not empty llistat}">
			<div class="rellenoInf"></div>
			<div class="rellenoIzq"></div>
			<div class="rellenoDer"></div>
			<div class="supDer"></div>
			<div class="supIzq"></div>
			<div class="infIzq"></div>
			<div class="infDer"></div>
		</c:if> 
		<display:table name="llistat" requestURI="" id="informacioUtil" export="true" sort="list" cellpadding="0" cellspacing="0"	class="listadoAncho selectable"> 
			<display:column property="dataAlta" titleKey="informacioUtil.camp.dataAlta" format="{0,date,dd/MM/yyyy}" headerClass="ancho75" sortable="true" url="/InformacioUtilForm.html" paramId="id" paramProperty="id"/>
			<display:column titleKey="informacioUtil.camp.nom" headerClass="ancho100" sortable="true">
				<c:choose>
					<c:when test="${lang == 'es'}">
						<c:out value="${informacioUtil.nomEs}" />
					</c:when>
					<c:otherwise>
						<c:out value="${informacioUtil.nom}" />
					</c:otherwise>
				</c:choose>
			</display:column>
			<display:column property="dataInici" titleKey="informacioUtil.camp.dataInici" format="{0,date,dd/MM/yyyy}" headerClass="ancho75" sortable="true"/>
			<display:column property="dataFinal" titleKey="informacioUtil.camp.dataFinal" format="{0,date,dd/MM/yyyy}" headerClass="ancho75" sortable="true"/>
			<display:column property="actiu" titleKey="informacioUtil.camp.actiu" headerClass="ancho75 final" sortable="true"/>
			<display:setProperty name="export.xml" value="false" />
			<display:setProperty name="export.csv" value="false" />
			<display:setProperty name="export.rtf" value="false" />
			<display:setProperty name="export.pdf" value="false" />
			<display:setProperty name="export.excel.include_header" value="true" />
			<display:setProperty name="export.excel.filename" value="InformacionsUtils.xls" />
			<display:setProperty name="export.decorated" value="true" />
		</display:table>
	</div>
	
	<div class="separadorH"></div>
	
	<form name="formulario" action="InformacioUtilForm.html"></form>
	
	<div class="btnCorto" onmouseover="underline('enlaceCrearForm')"
		onmouseout="underline('enlaceCrearForm')"
		onclick="document.formulario.submit();">
		<a id="enlaceCrearForm"	href="javascript:void(0);"><fmt:message key="manteniment.crearnou" /></a>
	</div>
	

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