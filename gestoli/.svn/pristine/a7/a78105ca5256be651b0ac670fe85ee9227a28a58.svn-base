<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>


<html>
<head>
<title><fmt:message key="qualitat.noConformitat.llistitol" /></title>
<script type="text/javascript" src="js/displaytag.js"></script>
<%-- <link href="css/displaytag.css" rel="stylesheet" type="text/css"/> --%>


	<script type="text/javascript">
	
		function generatePdf(id) {
			document.getElementById("idNoConformitatPDF").value = id;
			document.getElementById("generatePdfForm").submit();
		}

		function crearNoConformitat(id) {
			document.getElementById("idNoConformitat").value = "";
			document.getElementById("idControl").value = "";
			document.getElementById("qualitatNoConformitatForm").submit();
		}

		function visualitzarNoConformitat(id) {
			document.getElementById("idNoConformitat").value = id;
			document.getElementById("idControl").value = "";
			document.getElementById("qualitatNoConformitatForm").submit();
		}
		
	</script>
	
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
		<display:table name="llistat" requestURI="" id="qualitatNoConformitat" export="true"
		sort="list" cellpadding="0" cellspacing="0"	class="listadoAncho" defaultsort="2" defaultorder="descending">
			<display:column titleKey="qualitat.noConformitat.camp.departament" headerClass="ancho100" sortable="true">
				<c:if test="${not empty qualitatNoConformitat.departament}">
					<div id="visualitzarNoConformitat1_${qualitatNoConformitat.id}" onclick="visualitzarNoConformitat(${qualitatNoConformitat.id})">
						<fmt:message key="qualitat.departament.${qualitatNoConformitat.departament.nom}" />
					</div>
				</c:if>
			</display:column>
			<display:column titleKey="qualitat.noConformitat.camp.dataNoConformitat" headerClass="ancho100" sortable="true">
				<div id="visualitzarNoConformitat2_${qualitatNoConformitat.id}" onclick="visualitzarNoConformitat(${qualitatNoConformitat.id})">
					<fmt:formatDate value="${qualitatNoConformitat.dataNoConformitat}" pattern="dd/MM/yyyy"/>
				</div>
			</display:column>
			<display:column titleKey="qualitat.noConformitat.camp.responsableDeteccio" headerClass="ancho100" sortable="true">
				<div id="visualitzarNoConformitat3_${qualitatNoConformitat.id}" onclick="visualitzarNoConformitat(${qualitatNoConformitat.id})">
					<c:out value="${qualitatNoConformitat.responsableDeteccio.nom} ${qualitatNoConformitat.responsableDeteccio.llinatges}"/>
				</div>
			</display:column>
			<display:column titleKey="qualitat.noConformitat.camp.descripcio" headerClass="ancho100" sortable="true">
				<div id="visualitzarNoConformitat4_${qualitatNoConformitat.id}" onclick="visualitzarNoConformitat(${qualitatNoConformitat.id})">
					<c:out value="${qualitatNoConformitat.descripcio}"/>
				</div>
			</display:column>
			<display:column titleKey="qualitat.noConformitat.camp.dataTancament" headerClass="ancho100" sortable="true">
				<div id="visualitzarNoConformitat5_${qualitatNoConformitat.id}" onclick="visualitzarNoConformitat(${qualitatNoConformitat.id})">
					<fmt:formatDate value="${qualitatNoConformitat.dataTancament}" pattern="dd/MM/yyyy"/>
				</div>
			</display:column>
			<display:column headerClass="ancho32 final" sortable="false" titleKey="manteniment.pdf" >
				<div id="generatePdfForm_${qualitatNoConformitat.id}" class="iconoVer" title="<fmt:message key="manteniment.pdf"/>"
					onclick="generatePdf(${qualitatNoConformitat.id})">
				</div>
			</display:column>
			<display:setProperty name="export.xml" value="false" />
			<display:setProperty name="export.csv" value="false" />
			<display:setProperty name="export.rtf" value="false" />
			<display:setProperty name="export.pdf" value="false" />
			<display:setProperty name="export.excel.include_header" value="true" />
			<display:setProperty name="export.excel.filename" value="qualitatNoConformitats.xls" />
			<display:setProperty name="export.decorated" value="true" />
			
			
		</display:table>
	</div>
	
	<div class="separadorH"></div>
	
	<form name="formulario" action="QualitatNoConformitatForm.html"></form>
	
	<c:if test="${not empty esEstAdministrador || not empty esEstEncarregat || not empty esEstTreballador}">
		<div class="btnCorto" onmouseover="underline('enlaceCrearForm')"
			onmouseout="underline('enlaceCrearForm')"
			onclick="crearNoConformitat();">
			<a id="enlaceCrearForm"	href="javascript:void(0);"><fmt:message key="manteniment.crearnou" /></a>
		</div>
	</c:if>
	
	<form id="generatePdfForm" action="GenerarPdf.html" method="post" class="mini">
		<input id="idNoConformitatPDF" name="idNoConformitat" value="" type="hidden" /> 
		<input id="tipus" name="tipus" value="20" type="hidden" />
	</form>
		
	<form id="qualitatNoConformitatForm" action="QualitatNoConformitatForm.html" method="get" class="mini">
		<input id="idNoConformitat" name="id" value="" type="hidden" />
		<input id="idControl" name="idControl" value="" type="hidden" />
		<input id="url" name="url" value="${url}" type="hidden" /> 
		<input id="params" name="params" value="${params}" type="hidden" />
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