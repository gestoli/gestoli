<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>


<html>
<head>
<title><fmt:message key="qualitat.appcc.control.llistitol" /></title>
<script type="text/javascript" src="js/displaytag.js"></script>
<%-- <link href="css/displaytag.css" rel="stylesheet" type="text/css"/> --%>
</head>
<body>


<div id="listado"><%-- Tabla de resultados --%>

	<div style="margin-left: 40px;">
		<fmt:message key="qualitat.appcc.control.perill" />${perill.detall}
		<div class="separadorH"></div>
		<fmt:message key="qualitat.appcc.control.tipus" /><fmt:message key="qualitat.appcc.etapa.perill.tipus.${perill.tipus}" />
	</div>

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
		sort="list" cellpadding="0" cellspacing="0"	class="listadoAncho selectable" defaultsort="1">
			<display:column title="" property="etapa.order" headerClass="ancho100" sortable="true" />
			<display:column titleKey="qualitat.appcc.control.camp.etapa" headerClass="ancho100" sortable="true" >
				<c:if test="${not empty qualitatAPPCCControl.id}">
					<a href="QualitatAPPCCControlForm.html?id=${qualitatAPPCCControl.id}&idEtapa=${qualitatAPPCCControl.etapa.id}&idPerill=${perill.id}" >
						${qualitatAPPCCControl.etapa.nom}
					</a>
				</c:if>
				<c:if test="${empty qualitatAPPCCControl.id}">
					<a href="QualitatAPPCCControlForm.html?idEtapa=${qualitatAPPCCControl.etapa.id}&idPerill=${perill.id}" >
						${qualitatAPPCCControl.etapa.nom}
					</a>
				</c:if>
			</display:column>
			<display:column property="p1" titleKey="qualitat.appcc.control.camp.p1" headerClass="ancho100" sortable="true"/>
			<display:column property="p2" titleKey="qualitat.appcc.control.camp.p2" headerClass="ancho100" sortable="true"/>
			<display:column property="p3" titleKey="qualitat.appcc.control.camp.p3" headerClass="ancho100" sortable="true"/>
			<display:column property="p4" titleKey="qualitat.appcc.control.camp.p4" headerClass="ancho100" sortable="true"/>
			<display:column property="p5" titleKey="qualitat.appcc.control.camp.p5" headerClass="ancho100" sortable="true"/>
			<display:column titleKey="qualitat.appcc.control.camp.puntControl" headerClass="ancho100" sortable="true">
					<fmt:message key="qualitat.appcc.control.puntControl.${qualitatAPPCCControl.puntControl}" />
			</display:column>
			<display:column titleKey="qualitat.appcc.control.camp.perillControlat" headerClass="ancho100 final" sortable="true">
				<c:if test="${not empty qualitatAPPCCControl.perillControlat}">
					<fmt:message key="qualitat.appcc.control.perillControlat.${qualitatAPPCCControl.perillControlat}" />
				</c:if>
			</display:column>
			<display:setProperty name="export.xml" value="false" />
			<display:setProperty name="export.csv" value="false" />
			<display:setProperty name="export.rtf" value="false" />
			<display:setProperty name="export.pdf" value="false" />
			<display:setProperty name="export.excel.include_header" value="true" />
			<display:setProperty name="export.excel.filename" value="qualitatAPPCCControl.xls" />
			<display:setProperty name="export.decorated" value="true" />
		</display:table>
	</div>

	<br /><br />
	<div class="separadorH"></div>
			
	<div class="btnCorto" 
		onmouseover="underline('enlaceTornarForm')"
		onmouseout="underline('enlaceTornarForm')" 
		onclick="document.location ='QualitatAPPCCPerill.html';"><a
		id="enlaceTornarForm" href="javascript:void(0);"><fmt:message
		key="proces.tornar" /></a></div>
				
</div>
<br />
	
<form name="formulario" action="QualitatAPPCCControlForm.html">
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