<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ page import="es.caib.gestoli.front.util.*"%>
<%@ page import="java.util.ResourceBundle"%>
<%@ page import="java.util.Locale"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>
<%

	String lang = Idioma.getLang(request);
	request.setAttribute("lang",lang);
%>

<html>
<head>

	<script type="text/javascript">
	
		window.onload = function() {
		}

		var posicioItem = 0;
		
		function modificarVisibilidad(id) {
			
			var name = "evaluacions["+id+"].noConformitat";
			if (!document.getElementById("evaluacions["+id+"].satisfactori").checked){
				document.getElementById(name).disabled = false;
			} else {
				document.getElementById(name).disabled = true;
			}
		
		}
		
		function generatePdf(id) {
			document.getElementById("idEvaluacio").value = id;
			document.getElementById("generatePdfForm").submit();
		}

		function noConformitat(registreId) {
			var idNoConformitat = document.getElementById("evaluacions["+registreId+"].idNoConformitat").value;
			var idControl = document.getElementById("evaluacions["+registreId+"].id").value;

			document.getElementById("idNoConformitat").value = idNoConformitat;
			document.getElementById("idControl").value = idControl;
			document.getElementById("qualitatNoConformitatForm").submit();
			//document.location ='QualitatNoConformitatForm.html?id='+idNoConformitat+'&idControl='+ idControl;
		}

/*		function crearNoConformitat(id) {
			document.getElementById("idNoConformitat").value = "";
			document.getElementById("idControl").value = id;
			document.getElementById("qualitatNoConformitatForm").submit();
		}

		function visualitzarNoConformitat(id) {
			document.getElementById("idNoConformitat").value = id;
			document.getElementById("idControl").value = "";
			document.getElementById("qualitatNoConformitatForm").submit();
		}
*/		
	</script>

<title><c:choose>
	<c:when test="${not empty formData.id}">
		<fmt:message key="manteniment.modificacio" />
	</c:when>
	<c:otherwise>
		<fmt:message key="manteniment.alta" />
	</c:otherwise>
</c:choose> <fmt:message key="analiticaParametreTipus.tipusdemant" /></title>


<script type="text/javascript" src="js/calendar/calendar.js"></script>
<script type="text/javascript" src="js/calendar/calendar-setup.js"></script>
<script type="text/javascript" src="js/displaytag.js"></script>
<%
	if(lang.equalsIgnoreCase("ca")){
%>
		<script type="text/javascript" src="js/calendar/lang/calendar-ca.js"></script>
		<script type="text/javascript" src="js/calendar/lang/calendar-es.js"></script>
<%		
	}else{
%>
		<script type="text/javascript" src="js/calendar/lang/calendar-es.js"></script>
<%
	}
%>

<link href="js/calendar/calendar-viti.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="js/form.js"></script>


</head>
<body>

<form id="formulario" name="FormacioEvaluacioForm" action="QualitatFormacioEvaluacioForm.html" method="post" class="extended seguit" onsubmit="">
	<c:if test="${not empty formData.id}">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="hidden" />
			<c:param name="path" value="formData.id" />
			<c:param name="camp" value="id" />
		</c:import>
	</c:if> 
	
	<div id="personalX">
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="select" />
		<c:param name="path" value="formData.supervisorId" />
		<c:param name="title">
			<fmt:message key="qualitat.plaFormacio.camp.supervisorFormacio" />
		</c:param>
		<c:param name="camp" value="campo_supervisorId" />
		<c:param name="name" value="supervisorId" />
		<c:param name="required" value="required" />
		<c:param name="selectItems" value="personal" />
		<c:param name="selectItemsId" value="id" />
		<c:param name="selectItemsValue" value="nom" />
		<c:param name="selectSelectedValue" value="${formData.supervisorId}" />
		<c:param name="clase" value="campoFormGrande conMargen" />
	</c:import>
	</div>
	
	
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="calendar" />
		<c:param name="path" value="formData.dataSupervisio" />
		<c:param name="title">
			<fmt:message key="qualitat.plaFormacio.camp.dataSupervisio" />
		</c:param>
		<c:param name="camp" value="campo_dataSupervisio" />
		<c:param name="name" value="dataSupervisio" />
		<c:param name="required" value="required" />
		<c:param name="maxlength" value="10" />
		<c:param name="aclaracio">
			<fmt:message key="proces.aclaracio.formatdata" />
		</c:param>
		<c:param name="clase" value="campoForm165" />
	</c:import>
	
	<div class="separadorH"></div>
	
	<div id="observacionesForm" class="campoForm">	
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="textarea" />
			<c:param name="path" value="formData.activitatSupervisio" />
			<c:param name="title">
				<fmt:message key="qualitat.plaFormacio.camp.activitatSupervisio" />
			</c:param>
			<c:param name="camp" value="campo_activitatSupervisio" />
			<c:param name="name" value="activitatSupervisio" />
			<c:param name="required" value="required" />
			<c:param name="maxlength" value="200" />
		</c:import>
	</div>
	
	<div class="separadorH"></div>
	
	<c:if test="${not empty evaluacions}">
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
			<display:table name="evaluacions" id="registre" requestURI="" export="true" sort="list" cellpadding="0" cellspacing="0" class="listadoEstrecho">
				
				<display:column titleKey="qualitat.plaFormacio.evaluacio.nomTreballador" sortable="true" sortProperty="treballador.nom">
					<c:out value="${registre.treballador.nom}" />&nbsp;<c:out value="${registre.treballador.llinatges}" />
				</display:column>
				<display:column titleKey="qualitat.plaFormacio.evaluacio.efectiva" sortable="true" >
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="hidden" />
						<c:param name="path" value="formData.evaluacions[${registre_rowNum - 1}].id" />
						<c:param name="camp" value="evaluacions[${registre_rowNum - 1}].id" />
					</c:import>
					<div id="efectiva_${registre.id}">
						<c:import url="comu/CampFormulari.jsp">
							<c:param name="tipus" value="checkbox" />
							<c:param name="path" value="formData.evaluacions[${registre_rowNum - 1}].satisfactori" />
							<c:param name="camp" value="evaluacions[${registre_rowNum - 1}].satisfactori" />
							<c:param name="onclick" value="modificarVisibilidad(${registre_rowNum - 1})" />
						</c:import>
					</div>
				</display:column>
				
				<display:column titleKey="qualitat.plaFormacio.evaluacio.noConformitat" sortable="true" >
					<c:choose>
						<c:when test="${registre.satisfactori}">
							<c:set var="disabled" value="disabled"/>
						</c:when>
						<c:otherwise>
							<c:set var="disabled" value=""/>
						</c:otherwise>
					</c:choose>
					<c:set var="noConformitat" value="No definida"/>
					<c:set var="idNoConformitat" value="" />
					<c:if test="${not empty registre.noConformitats}" >
						<c:forEach items="${registre.noConformitats}" var="noConf">
							<c:set var="noConformitat" value="${noConf.descripcio}"/>
							<c:set var="idNoConformitat" value="${noConf.id}"/>
						</c:forEach>
					</c:if>
					<input id="evaluacions[${registre_rowNum - 1}].idNoConformitat" name="idNoConformitat" value="<c:out value="${idNoConformitat}"/>" type="hidden" />			

					<div id="noConformitatForm_${registre.id}" title="<fmt:message key="manteniment.pdf"/>"
						onclick="noConformitat(${registre_rowNum - 1})">
						<input id="evaluacions[${registre_rowNum - 1}].noConformitat" name="noConformitat" value="<c:out value="${noConformitat}"/>" type="button"  ${disabled} />
					</div>
				</display:column>
				
				<display:column headerClass="ancho32" sortable="false" titleKey="manteniment.pdf">
						<div id="generatePdfForm_${registre.id}" class="iconoVer" title="<fmt:message key="manteniment.pdf"/>"
							onclick="generatePdf(${registre.id})">
						</div>
				</display:column>
				<display:setProperty name="export.xml" value="false" />
				<display:setProperty name="export.csv" value="false" />
				<display:setProperty name="export.rtf" value="false" />
				<display:setProperty name="export.pdf" value="false" />
				<display:setProperty name="export.excel.include_header" value="true" />
				<display:setProperty name="export.excel.filename" value="qualitatFormacioEvaluacions.xls" />
				<display:setProperty name="export.decorated" value="true" />
			</display:table>
		</div>
	</c:if>
	
	<div class="separadorH"></div>
	
	<br></br></br>

	<div class="botonesForm">
		<c:choose>
			<c:when test="${not empty formData.id}">
				<div id="guardarForm" class="btnCorto"
					onclick="if(confirm('<fmt:message key="manteniment.modificar.confirm"/>')){submitForm('formulario')}"
					onmouseover="underline('enlaceGuardarForm')"
					onmouseout="underline('enlaceGuardarForm')"><a
					id="enlaceGuardarForm" href="javascript:void(0);"><fmt:message
					key="manteniment.guardar" /></a></div>
			</c:when>
			<c:otherwise>
				<div id="guardarForm" class="btnCorto"
					onclick="onSubmitMultiple_personalArray();submitForm('formulario')"
					onmouseover="underline('enlaceGuardarForm')"
					onmouseout="underline('enlaceGuardarForm')"><a
					id="enlaceGuardarForm" href="javascript:void(0);"><fmt:message
					key="manteniment.guardar" /></a></div>
			</c:otherwise>
		</c:choose>

		<div class="btnCorto" 
			onmouseover="underline('enlaceTornarForm')"
			onmouseout="underline('enlaceTornarForm')" 
			onclick="document.location ='QualitatPlaFormacio.html';"><a
			id="enlaceTornarForm" href="javascript:void(0);"><fmt:message
			key="proces.tornar" /></a></div>
	
		<c:if test="${empty formData.id}">
			<div id="cancelarForm" class="btnCorto"
				onmouseover="underline('enlaceCancelarForm')"
				onmouseout="underline('enlaceCancelarForm')"
				onclick="document.location ='QualitatPlaFormacio.html';"><a
				id="enlaceCancelarForm" href="javascript:void(0);"><fmt:message
				key="proces.cancelar" /></a></div>
		</c:if>
		
		<div class="btnCorto" onmouseover="underline('generarPdf')"
			onmouseout="underline('generarPdf')"
			onclick="document.formularioPDF.submit();">
			<a id="generarPdf"	href="javascript:void(0);"><fmt:message key="manteniment.pdf" /></a>
		</div>
	
		
	</div>

</form>
<form name="formularioPDF" action="GenerarPdf.html" method="post">
	<input id="tipus" name="tipus" value="18" type="hidden" />
	<input id="idFormacio" name="idFormacio" value="${formData.id}" type="hidden" />
</form>
<form id="generatePdfForm" action="GenerarPdf.html" method="post" class="mini">
	<input id="idFormacio" name="idFormacio" value="<c:out value="${formData.id}"/>" type="hidden" /> 
	<input id="idEvaluacio" name="idEvaluacio" value="" type="hidden" />
	<input id="tipus" name="tipus" value="18" type="hidden" />
</form>

<form id="qualitatNoConformitatForm" action="QualitatNoConformitatForm.html" method="get" class="mini">
	<input id="idNoConformitat" name="id" value="" type="hidden" />
	<input id="idControl" name="idControl" value="" type="hidden" />
	<input id="url" name="url" value="${url}" type="hidden" /> 
	<input id="params" name="params" value="${params}" type="hidden" />
</form>

<!-- Colores en tablas -->
<script type="text/javascript">
	jQuery(document).ready(function(){
		setEstilosTabla();
	})
</script>
	
</body>
</html>