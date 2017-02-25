<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>


<html>
<head>
<title><fmt:message key="qualitat.appcc.plaVerificacio.llistitol" /></title>
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
		<display:table name="llistat" requestURI="" id="qualitatAPPCCPlaVerificacio" export="true"
		sort="list" cellpadding="0" cellspacing="0"	class="listadoAncho selectable" >
			<display:column titleKey="qualitat.appcc.plaVerificacio.camp.dataRealitzacio" headerClass="ancho100" sortable="true" url="/QualitatAPPCCPlaVerificacioForm.html" paramId="id" paramProperty="id">
				<fmt:formatDate value="${qualitatAPPCCPlaVerificacio.dataRealitzacio}" pattern="dd/MM/yyyy"/>
			</display:column>
			<display:column titleKey="qualitat.appcc.plaVerificacio.camp.responsable" headerClass="ancho100" sortable="true">
				<c:if test="${not empty qualitatAPPCCPlaVerificacio.responsable}">
					<c:out value="${qualitatAPPCCPlaVerificacio.responsable.nom} ${qualitatAPPCCPlaVerificacio.responsable.llinatges}" />
				</c:if>			
			</display:column>
			<display:setProperty name="export.xml" value="false" />
			<display:setProperty name="export.csv" value="false" />
			<display:setProperty name="export.rtf" value="false" />
			<display:setProperty name="export.pdf" value="false" />
			<display:setProperty name="export.excel.include_header" value="true" />
			<display:setProperty name="export.excel.filename" value="qualitatAPPCCPlaVerificacio.xls" />
			<display:setProperty name="export.decorated" value="true" />
		</display:table>
	</div>
	<br /><br />
	<div class="separadorH"></div>
	
	<c:if test="${not empty esEstAdministrador || not empty esEstEncarregat}">
		<div class="btnCorto" onmouseover="underline('enlaceCrearForm')"
			onmouseout="underline('enlaceCrearForm')"
			onclick="document.formulario.submit();">
			<a id="enlaceCrearForm"	href="javascript:void(0);"><fmt:message key="manteniment.crearnou" /></a>
		</div>
	</c:if>
			
</div>
<br />
	
<form name="formulario" action="QualitatAPPCCPlaVerificacioForm.html">
</form>


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