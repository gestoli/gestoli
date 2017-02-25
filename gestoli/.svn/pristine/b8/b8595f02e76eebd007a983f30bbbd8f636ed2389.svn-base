<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>


<html>
<head>
<title><fmt:message key="qualitat.descripcioPersonal.llistitol" /></title>
<script type="text/javascript" src="js/displaytag.js"></script>
<%-- <link href="css/displaytag.css" rel="stylesheet" type="text/css"/> --%>
</head>
<body>

<div id="listado"><%-- Tabla de resultados --%>
	<c:set var="hidden" value="" />
	<c:if test="${empty esEstAdministrador && empty esEstEncarregat}">
		<c:set var="hidden" value="hidden" />
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
		<display:table name="llistat" requestURI="" id="qualitatDescripcioPersonal" export="true"
		sort="list" cellpadding="0" cellspacing="0"	class="listadoAncho selectable" defaultsort="6">
			<display:column property="nom" titleKey="qualitat.descripcioPersonal.camp.nom" headerClass="ancho100" url="/QualitatDescripcioPersonalForm.html" paramId="codi" paramProperty="codi" sortable="true"/>
			<display:column property="llinatges" titleKey="qualitat.descripcioPersonal.camp.llinatges" sortable="true"/>
			<display:column property="dataIncorporacio" titleKey="qualitat.descripcioPersonal.camp.dataIncorporacio" headerClass="ancho100" sortable="true"/>
			<display:column property="carrec.carrec" titleKey="qualitat.descripcioPersonal.camp.carrec" headerClass="ancho160" sortable="true"/>
			<display:column property="dataCarrec" titleKey="qualitat.descripcioPersonal.camp.dataCarrec" headerClass="ancho100" sortable="true"/>
			<display:column property="carrec.nivellJerarquic" titleKey="qualitat.descripcioPersonal.camp.nivellJerarquic" headerClass="ancho100" sortable="true"/>
			<display:column headerClass="ancho32 ${hidden} final" class="${hidden}" sortable="false" titleKey="qualitat.descripcioPersonal.camp.CV" url="/QualitatDescripcioPersonalCVForm.html" paramId="codi" paramProperty="codi">
				<form id="qualitatDescripcioPersonalCVForm_${qualitatDescripcioPersonal.codi}" action="QualitatDescripcioPersonalCVForm.html" method="post" class="mini">
					<input id="codi" name="codi" value="<c:out value="${qualitatDescripcioPersonal.codi}"/>" type="hidden" /> 
					<input id="action" name="action" value="CV" type="hidden" />
					<div id="CVForm" class="iconoVer" title="<fmt:message key="qualitat.descripcioPersonal.camp.CV"/>">
					</div>
				</form>
			</display:column>
			<display:setProperty name="export.xml" value="false" />
			<display:setProperty name="export.csv" value="false" />
			<display:setProperty name="export.rtf" value="false" />
			<display:setProperty name="export.pdf" value="false" />
			<display:setProperty name="export.excel.include_header" value="true" />
			<display:setProperty name="export.excel.filename" value="qualitatPersonal.xls" />
			<display:setProperty name="export.decorated" value="true" />
		</display:table>
	</div>
	
	<div class="separadorH"></div>
	
	<form name="formulario" action="QualitatDescripcioPersonalForm.html"></form>
	
	<c:if test="${not empty esEstAdministrador}">
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