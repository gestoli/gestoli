<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>


<html>
<head>
<title><fmt:message key="qualitat.proveidor.avaluacio.llistitol" /></title>
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
		<display:table name="llistat" requestURI="" id="qualitatProveidorAvaluacio" export="true"
		sort="list" cellpadding="0" cellspacing="0"	class="listadoAncho selectable" defaultsort="1">
			<display:column property="proveidor.nom" titleKey="qualitat.proveidor.avaluacio.camp.proveidor" headerClass="ancho100" sortable="true" url="/QualitatProveidorAvaluacioForm.html?idProveidor=${idProveidor}" paramId="id" paramProperty="id"/>
			<display:column property="dataVerificacio" format="{0,date,dd/MM/yyyy}" titleKey="qualitat.proveidor.avaluacio.camp.dataVerificacio" sortable="true"/>
			<display:column titleKey="qualitat.proveidor.avaluacio.camp.responsable" headerClass="ancho100" sortable="true">
				<c:out value="${qualitatProveidorAvaluacio.responsableVerificacio.nom} ${qualitatProveidorAvaluacio.responsableVerificacio.llinatges}"/>
			</display:column>
			<display:column property="dataRehomologacio" format="{0,date,dd/MM/yyyy}" titleKey="qualitat.proveidor.avaluacio.camp.dataRehomologacio" sortable="true"/>
			<display:column property="dataDeshomologacio" format="{0,date,dd/MM/yyyy}" titleKey="qualitat.proveidor.avaluacio.camp.dataDeshomologacio" sortable="true"/>
			<display:column property="proximaAvaluacio" format="{0,date,dd/MM/yyyy}" titleKey="qualitat.proveidor.avaluacio.camp.proximaAvaluacio" sortable="true"/>
			<display:column property="valoracio" titleKey="qualitat.proveidor.avaluacio.camp.valoracio" sortable="true"/>
			<display:setProperty name="export.xml" value="false" />
			<display:setProperty name="export.csv" value="false" />
			<display:setProperty name="export.rtf" value="false" />
			<display:setProperty name="export.pdf" value="false" />
			<display:setProperty name="export.excel.include_header" value="true" />
			<display:setProperty name="export.excel.filename" value="qualitatProveidorAvaluacions.xls" />
			<display:setProperty name="export.decorated" value="true" />
		</display:table>
	</div>
	
	<div class="separadorH"></div>
	
	<form name="formulario" action="QualitatProveidorAvaluacioForm.html">
		<input id="idProveidor" name="idProveidor" value="<c:out value="${idProveidor}"/>" type="hidden" />
	</form>
	
	<div class="btnCorto" onmouseover="underline('enlaceCrearForm')"
		onmouseout="underline('enlaceCrearForm')"
		onclick="document.formulario.submit();">
		<a id="enlaceCrearForm"	href="javascript:void(0);"><fmt:message key="manteniment.crearnou" /></a>
	</div>
	
	<div class="btnCorto" 
		onmouseover="underline('enlaceTornarForm')"
		onmouseout="underline('enlaceTornarForm')" 
		onclick="document.location ='QualitatProveidor.html';"><a
		id="enlaceTornarForm" href="javascript:void(0);"><fmt:message
		key="proces.tornar" /></a></div>
				
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