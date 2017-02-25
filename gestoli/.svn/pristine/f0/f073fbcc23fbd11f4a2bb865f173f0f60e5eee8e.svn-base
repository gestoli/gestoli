<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>


<html>
<head>
<title><fmt:message key="qualitat.noConformitat.accio.llistitol" /></title>
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
		<display:table name="llistat" requestURI="" id="qualitatNoConformitatAccio" export="true"
		sort="list" cellpadding="0" cellspacing="0"	class="listadoAncho selectable" defaultsort="1">
			<display:column property="id" titleKey="qualitat.noConformitat.accio.camp.id" headerClass="ancho100" sortable="true"  url="/QualitatNoConformitatAccioForm.html" paramId="id" paramProperty="id"/>
			<display:column property="accio" titleKey="qualitat.noConformitat.accio.camp.accio" headerClass="ancho100" sortable="true"/>
			<display:column titleKey="qualitat.noConformitat.accio.camp.responsableAccio" headerClass="ancho100" sortable="true">
				<c:out value="${qualitatNoConformitatAccio.responsableAccio.nom} ${qualitatNoConformitatAccio.responsableAccio.llinatges}"/>
			</display:column>
			<display:column property="dataFiPrevista" titleKey="qualitat.noConformitat.accio.camp.dataFiPrevista" headerClass="ancho100" sortable="true"/>
			<display:column property="dataTancament" titleKey="qualitat.noConformitat.accio.camp.dataTancament" headerClass="ancho100" sortable="true"/>
			<display:setProperty name="export.xml" value="false" />
			<display:setProperty name="export.csv" value="false" />
			<display:setProperty name="export.rtf" value="false" />
			<display:setProperty name="export.pdf" value="false" />
			<display:setProperty name="export.excel.include_header" value="true" />
			<display:setProperty name="export.excel.filename" value="qualitatNoConformitatAccions.xls" />
			<display:setProperty name="export.decorated" value="true" />
		</display:table>
	</div>
	
	<div class="separadorH"></div>
	
	<form name="formulario" action="QualitatNoConformitatAccioForm.html">
		<input id="idNoConformitat" name="idNoConformitat" value="<c:out value="${idNoConformitat}"/>" type="hidden" />
	</form>
	
	<div class="btnCorto" onmouseover="underline('enlaceCrearForm')"
		onmouseout="underline('enlaceCrearForm')"
		onclick="document.formulario.submit();">
		<a id="enlaceCrearForm"	href="javascript:void(0);"><fmt:message key="manteniment.crearnou" /></a>
	</div>
					
	<div class="btnCorto" 
		onmouseover="underline('enlaceTornarForm')"
		onmouseout="underline('enlaceTornarForm')" 
		onclick="document.location ='QualitatNoConformitat.html';">
		<a id="enlaceTornarForm" href="javascript:void(0);">
			<fmt:message key="proces.tornar" />
		</a>
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