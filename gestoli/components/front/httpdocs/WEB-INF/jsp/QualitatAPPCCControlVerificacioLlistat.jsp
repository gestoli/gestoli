<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>


<html>
<head>
<title><fmt:message key="qualitat.appcc.control.verificacio.llistitol" /></title>
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
		<display:table name="llistat" requestURI="" id="qualitatAPPCCControl" export="true"
		sort="list" cellpadding="0" cellspacing="0"	class="listadoAncho selectable" >
			<display:column property="etapa.nom" titleKey="qualitat.appcc.etapa.camp.nom" headerClass="ancho100" sortable="true" url="/QualitatAPPCCControlVerificacioForm.html" paramId="id" paramProperty="id"/>
			<display:column property="perill.detall" titleKey="qualitat.appcc.etapa.perill.camp.detall" headerClass="ancho100" sortable="true"/>
			<display:column titleKey="qualitat.appcc.control.camp.puntControl" headerClass="ancho100" sortable="true" >
				<fmt:message key="qualitat.appcc.control.puntControl.${qualitatAPPCCControl.puntControl}"/>
			</display:column>
			<display:setProperty name="export.xml" value="false" />
			<display:setProperty name="export.csv" value="false" />
			<display:setProperty name="export.rtf" value="false" />
			<display:setProperty name="export.pdf" value="false" />
			<display:setProperty name="export.excel.include_header" value="true" />
			<display:setProperty name="export.excel.filename" value="qualitatAPPCCControlVerificacio.xls" />
			<display:setProperty name="export.decorated" value="true" />
		</display:table>
	</div>

	<div class="separadorH"></div>
			
</div>
<br />
	


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