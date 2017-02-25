<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>


<html>
<head>
<title><fmt:message key="qualitat.plaManteniment.control.llistitol" /></title>
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
		<display:table name="llistat" requestURI="" id="qualitatPlaNetejaRealitzacio" export="true"
		sort="list" cellpadding="0" cellspacing="0"	class="listadoAncho selectable" defaultsort="1">
			<display:column titleKey="qualitat.plaNeteja.camp.equip" sortable="true">
				<c:out value="${qualitatPlaNetejaRealitzacio.neteja.equip.nom}"/>
			</display:column>
			<display:column property="neteja.accio" titleKey="qualitat.plaNeteja.camp.accio" sortable="true" url="/QualitatPlaNetejaRealitzacioForm.html?idNeteja=${idNeteja}" paramId="id" paramProperty="id"/>
			<display:column property="dataAnterior" format="{0,date,dd/MM/yyyy}" titleKey="qualitat.plaManteniment.control.camp.dataAnterior" sortable="true"/>
			<display:column property="dataRealitzacio" format="{0,date,dd/MM/yyyy}" titleKey="qualitat.plaManteniment.control.camp.dataRealitzacio" sortable="true"/>
			<display:column titleKey="qualitat.plaNeteja.camp.responsable" headerClass="ancho210" sortable="true">
				<c:out value="${qualitatPlaNetejaRealitzacio.responsable.nom} ${qualitatPlaNetejaRealitzacio.responsable.llinatges}"/>
			</display:column>
			<display:setProperty name="export.xml" value="false" />
			<display:setProperty name="export.csv" value="false" />
			<display:setProperty name="export.rtf" value="false" />
			<display:setProperty name="export.pdf" value="false" />
			<display:setProperty name="export.excel.include_header" value="true" />
			<display:setProperty name="export.excel.filename" value="qualitatPlaNetejaRealitzacio.xls" />
			<display:setProperty name="export.decorated" value="true" />
		</display:table>
	</div>
	
	<div class="separadorH"></div>
	
	<form name="formulario" action="QualitatPlaNetejaRealitzacioForm.html">
		<input id="idNeteja" name="idNeteja" value="<c:out value="${idNeteja}"/>" type="hidden" /> 
	</form>
	
	<div class="btnCorto" onmouseover="underline('enlaceCrearForm')"
		onmouseout="underline('enlaceCrearForm')"
		onclick="document.formulario.submit();">
		<a id="enlaceCrearForm"	href="javascript:void(0);"><fmt:message key="manteniment.crearnou" /></a>
	</div>
	
	<div class="btnCorto" 
		onmouseover="underline('enlaceTornarForm')"
		onmouseout="underline('enlaceTornarForm')" 
		onclick="document.location ='QualitatPlaNeteja.html';"><a
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