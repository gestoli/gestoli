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
			modificarVisibilidadNoConformidad();
		}
	
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


	<script type="text/javascript">
	
		function generatePdf(id) {
			document.getElementById("idNoConformitatPDF").value = id;
			document.getElementById("generatePdfForm").submit();
		}

		function crearNoConformitat(id) {
			document.getElementById("idNoConformitat").value = "";
			document.getElementById("idControl").value = id;
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

<form id="formulario" name="APPCCControlVerificacioForm" action="QualitatAPPCCControlVerificacioForm.html" method="post" class="extended seguit" onsubmit="">
	<c:if test="${not empty formData.id}">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="hidden" />
			<c:param name="path" value="formData.id" />
			<c:param name="camp" value="id" />
		</c:import>
	</c:if> 
	
	<div class="separadorH"></div>
	
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="calendar" />
		<c:param name="path" value="formData.dataVerificacio" />
		<c:param name="title">
			<fmt:message key="qualitat.appcc.control.verificacio.camp.dataVerificacio" />
		</c:param>
		<c:param name="camp" value="campo_dataVerificacio" />
		<c:param name="name" value="dataVerificacio" />
		<c:param name="required" value="required" />
		<c:param name="maxlength" value="10" />
		<c:param name="aclaracio">
			<fmt:message key="proces.aclaracio.formatdata" />
		</c:param>
		<c:param name="clase" value="campoForm165" />
	</c:import>
	
	<div class="separadorH"></div>
	
	<div id="responsableVerificacioX">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="select" />
			<c:param name="path" value="formData.idResponsableVerificacio" />
			<c:param name="title">
				<fmt:message key="qualitat.appcc.control.verificacio.camp.responsable" />
			</c:param>
			<c:param name="camp" value="campo_idRresponsableVerificacio" />
			<c:param name="name" value="idResponsableVerificacio" />
			<c:param name="required" value="required" />
			<c:param name="selectItems" value="personal" />
			<c:param name="selectItemsId" value="id" />
			<c:param name="selectItemsValue" value="nom" />
			<c:param name="selectSelectedValue" value="${formData.idResponsableVerificacio}" />
			<c:param name="clase" value="campoFormGrande conMargen" />
		</c:import>
	</div>
	
	<div class="separadorH"></div>
		
	<div class="off" id="satisfactoriX">
		<div class="etiqueta conMargen <c:if test="${not empty status.errorMessage}"> error</c:if>">
			<c:import url="comu/CampFormulari.jsp">
				<c:param name="tipus" value="checkbox" />
				<c:param name="path" value="formData.satisfactori" />
				<c:param name="title">
					<fmt:message key="qualitat.appcc.control.verificacio.camp.satisfactori" />
				</c:param>
				<c:param name="camp" value="satisfactori" />
				<c:param name="name" value="satisfactori" />
				<c:param name="required" value="required" />
			</c:import>
		</div>
	</div>
	
	<div id="botonNoConformitat">
		<c:if test="${not empty formData.id}">
			<div class="btnCorto" 
			onmouseover="underline('enlaceNoConformitatForm')"
			onmouseout="underline('enlaceNoConformitatForm')" 
			onclick="if(confirm('<fmt:message key="manteniment.modificar.confirm"/>')){crearNoConformitat(${formData.id})}">
				<a id="enlaceNoConformitatForm" href="javascript:void(0);">
					<fmt:message key="qualitat.proces.noConformitat" />
				</a>
			</div>
		</c:if>
	</div>
	
	<div class="separadorH"></div>
	
	<c:if test="${not empty formData.noConformitats}">
		<div id="listado"><%-- Tabla de resultados --%>
			<div class="layoutSombraTabla ancho">
				<c:if
					test="${not empty formData.noConformitats}">
					<div class="rellenoInf"></div>
					<div class="rellenoIzq"></div>
					<div class="rellenoDer"></div>
					<div class="supDer"></div>
					<div class="supIzq"></div>
					<div class="infIzq"></div>
					<div class="infDer"></div>
				</c:if> 
				<display:table name="formData.noConformitats" id="registre" requestURI="" export="true" sort="list" cellpadding="0" cellspacing="0" class="listadoEstrecho" defaultsort="1" defaultorder="descending">
					<display:column titleKey="qualitat.noConformitat.camp.dataNoConformitat" headerClass="ancho100" sortable="true">
						<div id="visualitzarNoConformitat1_${registre.id}" onclick="visualitzarNoConformitat(${registre.id})">
							<fmt:formatDate value="${registre.dataNoConformitat}" pattern="dd/MM/yyyy"/>
						</div>
					</display:column>					
					<display:column titleKey="qualitat.noConformitat.camp.responsableDeteccio" headerClass="ancho100" sortable="true">
						<div id="visualitzarNoConformitat2_${registre.id}" onclick="visualitzarNoConformitat(${registre.id})">
							<c:out value="${registre.responsableDeteccio.nom} ${registre.responsableDeteccio.llinatges}"/>
						</div>
					</display:column>
					<display:column titleKey="qualitat.noConformitat.camp.descripcio" headerClass="ancho100" sortable="true">
						<div id="visualitzarNoConformitat3_${registre.id}" onclick="visualitzarNoConformitat(${registre.id})">
							<c:out value="${registre.descripcio}"/>
						</div>
					</display:column>
					<display:column titleKey="qualitat.noConformitat.camp.dataTancament" headerClass="ancho100" sortable="true"> 
						<div id="visualitzarNoConformitat4_${registre.id}" onclick="visualitzarNoConformitat(${registre.id})">
							<fmt:formatDate value="${registre.dataTancament}" pattern="dd/MM/yyyy"/>
						</div>
					</display:column>
					<display:column headerClass="ancho32" sortable="false" titleKey="manteniment.pdf" >
						<div id="generatePdfForm_${registre.id}" class="iconoVer" title="<fmt:message key="manteniment.pdf"/>"
							onclick="generatePdf(${registre.id})">
						</div>
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
		</div>
		<br/>
	</c:if>
	
	
	<div class="separadorH"></div>
	<br></br>

	<div class="botonesForm"><c:choose>
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
				onclick="submitForm('formulario')"
				onmouseover="underline('enlaceGuardarForm')"
				onmouseout="underline('enlaceGuardarForm')"><a
				id="enlaceGuardarForm" href="javascript:void(0);"><fmt:message
				key="manteniment.guardar" /></a></div>
		</c:otherwise>
	</c:choose>

	<div class="btnCorto" 
		onmouseover="underline('enlaceTornarForm')"
		onmouseout="underline('enlaceTornarForm')" 
		onclick="document.location ='QualitatAPPCCFitxaControl.html';"><a
		id="enlaceTornarForm" href="javascript:void(0);"><fmt:message
		key="proces.tornar" /></a></div>

	<c:if test="${empty formData.id}">
		<div id="cancelarForm" class="btnCorto"
			onmouseover="underline('enlaceCancelarForm')"
			onmouseout="underline('enlaceCancelarForm')"
			onclick="document.location ='QualitatAPPCCFitxaControl.html';"><a
			id="enlaceCancelarForm" href="javascript:void(0);"><fmt:message
			key="proces.cancelar" /></a></div>
	</c:if>
	</div>

	</form>


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
	
	<c:if test="${not empty formData.noConformitats}">
		<%-- Colores en tablas --%>
		<script type="text/javascript">
			$(document).ready(function(){
				setEstilosTabla();
			})
		</script>
	</c:if>

	<script type="text/javascript" language="javascript">
	// <![CDATA[
				
		function modificarVisibilidadNoConformidad() {
			var isSatisfactori = document.getElementById("satisfactori");
			if (isSatisfactori != null && isSatisfactori.checked){
				document.getElementById("botonNoConformitat").style.display = "none";
			} else {
				document.getElementById("botonNoConformitat").style.display = "block";
			}
		}

		function noConformitat(id) {
			submitForm('formulario'); 
			document.location ='QualitatNoConformitatForm.html?idControl='+ id;
		}
		
		
		function noConformitatb(id) {
			submitFormulario(id);
		}
		function submitFormulario(id) {
			submitForm('formulario'); 
			redirect(id);
		}
		function redirect(id) {
			document.location ='QualitatNoConformitatForm.html?idControl='+ id;
		}
				
	// ]]>
	</script>
	
</body>
</html>