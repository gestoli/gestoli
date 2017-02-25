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

<form id="formulario" name="APPCCEtapaForm" action="QualitatAPPCCEtapaForm.html" method="post" class="extended seguit" onsubmit="">
	<c:if test="${not empty formData.id}">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="hidden" />
			<c:param name="path" value="formData.id" />
			<c:param name="camp" value="id" />
		</c:import>
	</c:if> 
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="hidden" />
		<c:param name="path" value="formData.idEstabliment" />
		<c:param name="camp" value="idEstabliment" />
		<c:param name="value" value="${idEstabliment}" />
	</c:import> 
	
	<div class="separadorH"></div>
	
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.nom" />
		<c:param name="title">
			<fmt:message key="qualitat.appcc.etapa.camp.nom" />
		</c:param>
		<c:param name="camp" value="campo_nom" />
		<c:param name="name" value="nom" />
		<c:param name="required" value="required" />
		<c:param name="maxlength" value="50" />
		<c:param name="clase" value="campoFormGrande" />
	</c:import>
	
	
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="select" />
		<c:param name="path" value="formData.order" />
		<c:param name="title">
			<fmt:message key="qualitat.appcc.etapa.camp.order" />
		</c:param>
		<c:param name="camp" value="campo_order" />
		<c:param name="name" value="order" />
		<c:param name="required" value="required" />
		<c:param name="selectItems" value="order" />
		<c:param name="selectItemsId" value="id" />
		<c:param name="selectItemsValue" value="nom" />
		<c:param name="selectSelectedValue" value="${formData.order}" />
		<c:param name="clase" value="campoForm165" />
	</c:import>
	
	<div class="separadorH"></div>
	
	
	<div class="separadorH"></div>
	
	<c:if test="${not empty formData.perills}">
		<div id="listado"><%-- Tabla de resultados --%>
			<div class="layoutSombraTabla ancho">
				<c:if
					test="${not empty formData.perills}">
					<div class="rellenoInf"></div>
					<div class="rellenoIzq"></div>
					<div class="rellenoDer"></div>
					<div class="supDer"></div>
					<div class="supIzq"></div>
					<div class="infIzq"></div>
					<div class="infDer"></div>
				</c:if> 
				<display:table name="formData.perills" id="registre" requestURI="" export="true" sort="list" cellpadding="0" cellspacing="0" class="listadoEstrecho selectable" defaultsort="1">
					<display:column titleKey="qualitat.appcc.etapa.perill.camp.tipus" headerClass="ancho100" sortable="true" url="/QualitatAPPCCEtapaPerillForm.html?idEtapa=${formData.id}" paramId="id" paramProperty="id">
						<fmt:message key="qualitat.appcc.etapa.perill.tipus.${registre.tipus}"/>
					</display:column>
					<display:column property="detall" titleKey="qualitat.appcc.etapa.perill.camp.detall" headerClass="ancho100" sortable="true" />
					<display:column titleKey="qualitat.appcc.etapa.perill.camp.prevencio" headerClass="ancho100" sortable="true" >
						<fmt:message key="qualitat.appcc.etapa.perill.prevencio.${registre.prevencio}"/>
					</display:column>
					<display:column titleKey="qualitat.appcc.etapa.perill.camp.probabilitat" headerClass="ancho100" sortable="true" >
						<fmt:message key="qualitat.appcc.etapa.perill.probabilitat.${registre.probabilitat}"/>
					</display:column>
					<display:column titleKey="qualitat.appcc.etapa.perill.camp.gravetat" headerClass="ancho100 final" sortable="true" >
						<fmt:message key="qualitat.appcc.etapa.perill.gravetat.${registre.gravetat}"/>
					</display:column>
					<display:setProperty name="export.xml" value="false" />
					<display:setProperty name="export.csv" value="false" />
					<display:setProperty name="export.rtf" value="false" />
					<display:setProperty name="export.pdf" value="false" />
					<display:setProperty name="export.excel.include_header" value="true" />
					<display:setProperty name="export.excel.filename" value="qualitatAPPCCEtapaPerill.xls" />
					<display:setProperty name="export.decorated" value="true" />
				</display:table>
			</div>
		</div>
		<br/><br/><br/>
	</c:if>
	<div id="botonPerill">
		<c:if test="${not empty formData.id}">
			<div class="btnCorto" 
			onmouseover="underline('enlacePerillForm')"
			onmouseout="underline('enlacePerillForm')" 
			onclick="if(confirm('<fmt:message key="manteniment.modificar.confirm"/>')){perill(${formData.id})}">
				<a id="enlacePerillForm" href="javascript:void(0);">
					<fmt:message key="qualitat.appcc.etapa.perill" />
				</a>
			</div>
		</c:if>
	</div>
	
	
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
		onclick="document.location ='QualitatAPPCCEtapa.html';"><a
		id="enlaceTornarForm" href="javascript:void(0);"><fmt:message
		key="proces.tornar" /></a></div>

	<c:if test="${empty formData.id}">
		<div id="cancelarForm" class="btnCorto"
			onmouseover="underline('enlaceCancelarForm')"
			onmouseout="underline('enlaceCancelarForm')"
			onclick="document.location ='QualitatAPPCCEtapa.html';"><a
			id="enlaceCancelarForm" href="javascript:void(0);"><fmt:message
			key="proces.cancelar" /></a></div>
	</c:if>
	<c:if test="${not empty formData.id}">
		<input id="action" name="action" value="delete" type="hidden" />
		<div id="eliminarForm" class="btnCorto"
			onmouseover="underline('enlaceBorrarForm')"
			onmouseout="underline('enlaceBorrarForm')"
			onclick="submitFormConfirm('deleteForm','<fmt:message key="manteniment.esborrar.confirm"/>');">
		<a id="enlaceBorrarForm" href="javascript:void(0);"><fmt:message
			key="manteniment.esborrar" /></a></div>
	</c:if>
	
	</div>

	</form>
	<form id="deleteForm" action="QualitatAPPCCEtapa.html" method="post"
		class="seguit"
		onsubmit="return confirm('<fmt:message key="manteniment.estasegur"/>')">
		<input id="id" name="id" value="<c:out value="${formData.id}"/>" type="hidden" /> 
		<input id="action" name="action" value="delete" type="hidden" />
	</form>


	<script type="text/javascript" language="javascript">
	// <![CDATA[

		function perill(id) {
			document.location ='QualitatAPPCCEtapaPerillForm.html?idEtapa='+ id;
		}
				
	// ]]>
	</script>
	
	
	<c:if test="${not empty formData.perills}">
		<%-- Colores en tablas --%>
		<script type="text/javascript">
			$(document).ready(function(){
				setEstilosTabla();
			})
		</script>
	</c:if>

</body>
</html>