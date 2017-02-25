<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>


<html>
<head>
<title><fmt:message key="qualitat.appcc.etapa.llistitol" /></title>
<script type="text/javascript" src="js/displaytag.js"></script>
<%-- <link href="css/displaytag.css" rel="stylesheet" type="text/css"/> --%>
</head>
<body>

<form name="formulario" action="QualitatAPPCCEtapaForm.html">
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
			<display:table name="llistat" requestURI="" id="qualitatAPPCCEtapa" export="true"
			sort="list" cellpadding="0" cellspacing="0"	class="listadoEstrecho selectable" defaultsort="1">
				<display:column property="order" titleKey="qualitat.appcc.etapa.camp.order" headerClass="ancho100" sortable="true" url="/QualitatAPPCCEtapaForm.html" paramId="id" paramProperty="id"/>
				<display:column property="nom" titleKey="qualitat.appcc.etapa.camp.nom" headerClass="ancho100 final" sortable="true"/>
				<display:setProperty name="export.xml" value="false" />
				<display:setProperty name="export.csv" value="false" />
				<display:setProperty name="export.rtf" value="false" />
				<display:setProperty name="export.pdf" value="false" />
				<display:setProperty name="export.excel.include_header" value="true" />
				<display:setProperty name="export.excel.filename" value="qualitatAPPCCEtapa.xls" />
				<display:setProperty name="export.decorated" value="true" />
			</display:table>
		</div>
	
		<div class="separadorH"></div>
				
	</div>
	<br /><br/><br/>
	
	<div class="btnCorto" onmouseover="underline('enlaceCrearForm')"
		onmouseout="underline('enlaceCrearForm')"
		onclick="document.formulario.submit();">
		<a id="enlaceCrearForm"	href="javascript:void(0);"><fmt:message key="manteniment.crearnou" /></a>
	</div>
	
	<div class="btnCorto" onmouseover="underline('generarPdf')"
		onmouseout="underline('generarPdf')"
		onclick="document.formularioPDF.submit();">
		<a id="generarPdf"	href="javascript:void(0);"><fmt:message key="manteniment.pdf" /></a>
	</div>
	
	<div class="btnCorto" onmouseover="underline('verificacio')"
		onmouseout="underline('verificacio')">
		<a id="verificacio"	href="QualitatAPPCCVerificacioForm.html"><fmt:message key="manteniment.verificar" /></a>
	</div>
	
</form>

<form name="formularioPDF" action="GenerarPdf.html" method="post">
	<input id="tipus" name="tipus" value="21" type="hidden" />
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