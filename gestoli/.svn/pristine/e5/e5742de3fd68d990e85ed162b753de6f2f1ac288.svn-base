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

	<c:set var="verificacio" value="" />
	<c:if test="${empty esEstAdministrador && empty esEstEncarregat}">
		<c:set var="verificacio" value="hidden" />
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
		<display:table name="llistat" requestURI="" id="qualitatPlaMantenimentControl" export="true"
		sort="list" cellpadding="0" cellspacing="0"	class="listadoAncho selectable" defaultsort="1">
			<display:column property="manteniment.equip.nom" titleKey="qualitat.plaManteniment.control.camp.equip" headerClass="ancho100" sortable="true" url="/QualitatPlaMantenimentControlForm.html?idManteniment=${idManteniment}" paramId="id" paramProperty="id"/>
			<display:column property="id" titleKey="qualitat.plaManteniment.control.camp.codi" sortable="true"/>
			<display:column titleKey="qualitat.plaManteniment.control.camp.accioRealitzada" sortable="false">
				<c:out value="${qualitatPlaMantenimentControl.accioRealitzada}, ${qualitatPlaMantenimentControl.altresAccions}"/>
			</display:column>
			<display:column property="dataAnterior" titleKey="qualitat.plaManteniment.control.camp.dataAnterior" sortable="true"/>
			<display:column property="dataRealitzacio" titleKey="qualitat.plaManteniment.control.camp.dataRealitzacio" sortable="true"/>
			<display:column titleKey="qualitat.plaManteniment.control.camp.responsable" headerClass="ancho100" sortable="true">
				<c:out value="${qualitatPlaMantenimentControl.responsableIntern.nom} ${qualitatPlaMantenimentControl.responsableIntern.llinatges} ${qualitatPlaMantenimentControl.responsableExtern}"/>
			</display:column>
			<display:column property="dataVerificacio" titleKey="qualitat.plaManteniment.control.verificacio.camp.dataVerificacio" sortable="true"/>
			<display:column titleKey="qualitat.plaManteniment.control.verificacio.camp.responsable" headerClass="ancho100" sortable="true">
				<c:out value="${qualitatPlaMantenimentControl.responsableVerificacio.nom} ${qualitatPlaMantenimentControl.responsableVerificacio.llinatges}"/>
			</display:column>
			<display:column headerClass="ancho32 ${verificacio} final" class="${verificacio}" sortable="false" titleKey="qualitat.plaManteniment.control.camp.verificacio" url="/QualitatPlaMantenimentControlVerificacioForm.html?idManteniment=${idManteniment}" paramId="id" paramProperty="id">
				<form id="verificacioForm_${qualitatPlaMantenimentControl.id}" action="QualitatPlaMantenimentControlVerificacioForm.html" method="post" class="mini">
					<input id="id" name="id" value="<c:out value="${qualitatPlaMantenimentControl.id}"/>" type="hidden" /> 
					<input id="idManteniment" name="idManteniment" value="<c:out value="${idManteniment}"/>" type="hidden" />
					<input id="action" name="action" value="verificacio" type="hidden" />
					<div id="verificacioForm" class="iconoSituar" title="<fmt:message key="qualitat.plaManteniment.control.camp.verificacio"/>">
					</div>
				</form>
			</display:column>
			<display:setProperty name="export.xml" value="false" />
			<display:setProperty name="export.csv" value="false" />
			<display:setProperty name="export.rtf" value="false" />
			<display:setProperty name="export.pdf" value="false" />
			<display:setProperty name="export.excel.include_header" value="true" />
			<display:setProperty name="export.excel.filename" value="qualitatPlaMantenimentControl.xls" />
			<display:setProperty name="export.decorated" value="true" />
		</display:table>
	</div>
	
	<div class="separadorH"></div>
	
	<form name="formulario" action="QualitatPlaMantenimentControlForm.html">
		<input id="idManteniment" name="idManteniment" value="<c:out value="${idManteniment}"/>" type="hidden" /> 
	</form>
	
	<div class="btnCorto" onmouseover="underline('enlaceCrearForm')"
		onmouseout="underline('enlaceCrearForm')"
		onclick="document.formulario.submit();">
		<a id="enlaceCrearForm"	href="javascript:void(0);"><fmt:message key="manteniment.crearnou" /></a>
	</div>
	
	<div class="btnCorto" 
		onmouseover="underline('enlaceTornarForm')"
		onmouseout="underline('enlaceTornarForm')" 
		onclick="document.location ='QualitatPlaManteniment.html';"><a
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