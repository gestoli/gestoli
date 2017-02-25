<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>


<html>
<head>
<title><fmt:message key="qualitat.plaManteniment.llistitol" /></title>
<script type="text/javascript" src="js/displaytag.js"></script>
<%-- <link href="css/displaytag.css" rel="stylesheet" type="text/css"/> --%>
</head>
<body>

<div id="listado"><%-- Tabla de resultados --%>

	<c:set var="control" value="" />
	<c:if test="${empty esEstAdministrador && empty esEstEncarregat && empty esEstTreballador}">
		<c:set var="control" value="hidden" />
	</c:if>
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
		<display:table name="llistat" requestURI="" id="qualitatPlaNeteja" export="true"
		sort="list" cellpadding="0" cellspacing="0"	class="listadoAncho selectable" defaultsort="1">
			<display:column titleKey="qualitat.plaNeteja.camp.equipElement" headerClass="ancho100" sortable="true" url="/QualitatPlaNetejaForm.html" paramId="codi" paramProperty="codi">
				<c:out value="${qualitatPlaNeteja.equip.nom} ${qualitatPlaNeteja.elementPlanta}"/>
			</display:column>
			<display:column property="accio" titleKey="qualitat.plaNeteja.camp.accio" headerClass="ancho210" sortable="false"/>
			<display:column property="producte.tipusSubministre" titleKey="qualitat.plaNeteja.camp.producte" sortable="false"/>
			<display:column property="dosis" titleKey="qualitat.plaNeteja.camp.dosis" sortable="false"/>
			<display:column property="frequencia" titleKey="qualitat.plaNeteja.camp.frequencia" sortable="true"/>
			<display:column titleKey="qualitat.plaNeteja.camp.responsable" headerClass="ancho100" sortable="true">
				<c:out value="${qualitatPlaNeteja.responsable.nom} ${qualitatPlaNeteja.responsable.llinatges}"/>
			</display:column>
			<display:column titleKey="qualitat.plaNeteja.camp.responsableVerificacio" headerClass="ancho100" sortable="true">
				<c:out value="${qualitatPlaNeteja.respVerificacio.nom} ${qualitatPlaNeteja.respVerificacio.llinatges}"/>
			</display:column>
			<display:column headerClass="ancho32 ${control}" class="${control}" sortable="false" titleKey="qualitat.plaNeteja.camp.realitzacio" url="/QualitatPlaNetejaRealitzacio.html" paramId="idNeteja" paramProperty="codi">
				<form id="realitzacioForm_${qualitatPlaNeteja.codi}" action="QualitatPlaNetejaRealitzacio.html" method="post" class="mini">
					<input id="idManteniment" name="idManteniment" value="<c:out value="${qualitatPlaNeteja.codi}"/>" type="hidden" /> 
					<input id="action" name="action" value="realitzacio" type="hidden" />
					<div id="realitzacioForm" class="iconoSituar" title="<fmt:message key="qualitat.plaNeteja.camp.realitzacio"/>">
					</div>
				</form>
			</display:column>
			<display:column headerClass="ancho32 ${verificacio} final" class="${verificacio}" sortable="false" titleKey="qualitat.plaNeteja.camp.verificacio" url="/QualitatPlaNetejaVerificacio.html" paramId="idNeteja" paramProperty="codi">
				<form id="verificacioForm_${qualitatPlaNeteja.codi}" action="QualitatPlaNetejaVerificacio.html" method="post" class="mini">
					<input id="idManteniment" name="idManteniment" value="<c:out value="${qualitatPlaNeteja.codi}"/>" type="hidden" /> 
					<input id="action" name="action" value="realitzacio" type="hidden" />
					<div id="verificacioForm" class="iconoVer" title="<fmt:message key="qualitat.plaNeteja.camp.verificacio"/>">
					</div>
				</form>
			</display:column>
			<display:setProperty name="export.xml" value="false" />
			<display:setProperty name="export.csv" value="false" />
			<display:setProperty name="export.rtf" value="false" />
			<display:setProperty name="export.pdf" value="false" />
			<display:setProperty name="export.excel.include_header" value="true" />
			<display:setProperty name="export.excel.filename" value="qualitatPlaNeteja.xls" />
			<display:setProperty name="export.decorated" value="true" />
		</display:table>
	</div>
	
	<div class="separadorH"></div>
	
	<form name="formulario" action="QualitatPlaNetejaForm.html"></form>
	
	<c:if test="${not empty esEstAdministrador}">
		<div class="btnCorto" onmouseover="underline('enlaceCrearForm')"
			onmouseout="underline('enlaceCrearForm')"
			onclick="document.formulario.submit();">
			<a id="enlaceCrearForm"	href="javascript:void(0);"><fmt:message key="manteniment.crearnou" /></a>
		</div>
	</c:if>
	

	<div class="btnCorto" onmouseover="underline('generarPdf')"
		onmouseout="underline('generarPdf')"
		onclick="document.formularioPDF.submit();">
		<a id="generarPdf"	href="javascript:void(0);"><fmt:message key="manteniment.pdf" /></a>
	</div>
	<form name="formularioPDF" action="GenerarPdf.html" method="post">
		<input id="tipus" name="tipus" value="19" type="hidden" />
	</form>
	
	
			
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