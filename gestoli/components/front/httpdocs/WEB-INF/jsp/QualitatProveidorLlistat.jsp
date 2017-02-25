<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>


<html>
<head>
<title><fmt:message key="qualitat.submenu.relacioProveidors" /></title>
<script type="text/javascript" src="js/displaytag.js"></script>
<%-- <link href="css/displaytag.css" rel="stylesheet" type="text/css"/> --%>
</head>
<body>

<div id="listado"><%-- Tabla de resultados --%>

	<c:set var="control" value="" />
	<c:if test="${empty esEstAdministrador && empty esEstEncarregat}">
		<c:set var="control" value="hidden" />
	</c:if>
	
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
		<display:table name="llistat" requestURI="" id="qualitatProveidors" export="true"
		sort="list" cellpadding="0" cellspacing="0"	class="listadoAncho selectable" defaultsort="6">
			<display:column property="nom" titleKey="qualitat.proveidor.camp.nom" headerClass="ancho100" url="/QualitatProveidorForm.html" paramId="codi" paramProperty="codi" sortable="true"/>
			<display:column property="direccio" titleKey="qualitat.proveidor.camp.direccio" sortable="true"/>
			<display:column property="telefon" titleKey="qualitat.proveidor.camp.telefon" headerClass="ancho100" sortable="true"/>
			<display:column property="personaContacte" titleKey="qualitat.proveidor.camp.personaContacte" headerClass="ancho160" sortable="true"/>
			<display:column headerClass="ancho32 ${control} final" class="${control}" sortable="false" titleKey="qualitat.plaNeteja.camp.verificacio" url="/QualitatProveidorAvaluacio.html" paramId="idProveidor" paramProperty="codi">
				<form id="avaluacioForm_${proveidor.codi}" action="QualitatProveidorAvaluacio.html" method="post" class="mini">
					<input id="idProveidor" name="idProveidor" value="<c:out value="${proveidor.codi}"/>" type="hidden" /> 
					<input id="action" name="action" value="avaluacio" type="hidden" />
					<div id="avaluacioForm" class="iconoSituar" title="<fmt:message key="qualitat.plaNeteja.camp.verificacio"/>">
					</div>
				</form>
			</display:column>	
			<display:setProperty name="export.xml" value="false" />
			<display:setProperty name="export.csv" value="false" />
			<display:setProperty name="export.rtf" value="false" />
			<display:setProperty name="export.pdf" value="false" />
			<display:setProperty name="export.excel.include_header" value="true" />
			<display:setProperty name="export.excel.filename" value="qualitatProveidors.xls" />
			<display:setProperty name="export.decorated" value="true" />	
		</display:table>
	</div>
	
	<div class="separadorH"></div>
	
	<form name="formulario" action="QualitatProveidorForm.html"></form>
	
	<c:if test="${not empty esEstAdministrador || not empty esEstEncarregat}">
		<div class="btnCorto" onmouseover="underline('enlaceCrearForm')"
			onmouseout="underline('enlaceCrearForm')"
			onclick="document.formulario.submit();">
			<a id="enlaceCrearForm"	href="javascript:void(0);"><fmt:message key="manteniment.crearnou" /></a>
		</div>
	</c:if>
	
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