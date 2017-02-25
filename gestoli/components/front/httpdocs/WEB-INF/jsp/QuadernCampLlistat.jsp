<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>
<%@ page import="es.caib.gestoli.front.util.*"%>
<%@ page import="java.util.ResourceBundle"%>
<%@ page import="java.util.Locale"%>
<%@ page buffer="64kb"%>
<%@ page autoFlush="true"%>
<%
	String lang = Idioma.getLang(request);
	request.setAttribute("lang",lang);
%>

<html>
<head>
<title><fmt:message key="consulta.docRendiment.titol" /></title>

<script type="text/javascript" src="js/displaytag.js"></script>
<script type="text/javascript" src="js/calendar/calendar.js"></script>
<script type="text/javascript" src="js/calendar/calendar-setup.js"></script>
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
<link href="js/calendar/calendar-viti.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="js/form.js"></script>

<link type="text/css" href="css/form.css" rel="stylesheet" />

<!--  
    <c:if test="${not empty llistat}">
    	<script type="text/javascript" src="js/displaytag.js"></script>    	
    </c:if>
   	 -->
</head>
<body>
<form id="formulario" action="QuadernCampLlistat.html"	method="post" class="extended seguit">
<fieldset>

	<c:if test="${not empty idOlivicultor}">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="hidden" />
			<c:param name="path" value="formData.idOlivicultor" />
			<c:param name="camp" value="idOlivicultor" />
		</c:import>
	</c:if>
	
	<c:choose>
		<c:when test="${not empty esOlivicultor}">
			<c:set var="olivicultor" value="display:none" />
		</c:when>
		<c:otherwise>
			<c:set var="olivicultor" value="display:block" />
		</c:otherwise>
	</c:choose>
	
	<div id="olivicultor" style="${olivicultor}">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="select" />
			<c:param name="path" value="formData.idOlivicultor" />
			<c:param name="title">
				<fmt:message key="quadernCamp.camp.olivicultor" />
			</c:param>
			<c:param name="camp" value="campo_idOlivicultor" />
			<c:param name="name" value="idOlivicultor" />
			<c:param name="required" value="required" />
			<c:param name="selectItems" value="llistatOlivicultors" />
			<c:param name="selectItemsId" value="id" />
			<c:param name="selectItemsValue" value="nom" />
			<c:param name="selectSelectedValue" value="${formData.idOlivicultor}" />
			<c:param name="aclaracio">
				<fmt:message key="quadernCamp.aclaracio.olivicultors" />
			</c:param>
			<c:param name="clase" value="campoFormGrande conMargen" />
		</c:import>
	</div>
	
	<div class="separadorH"></div>
	
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="calendar" />
		<c:param name="path" value="formData.dataInici" />
		<c:param name="required" value="required" />
		<c:param name="title">
			<fmt:message key="consulta.camp.dataInici" />
		</c:param>
		<c:param name="camp" value="dataInici" />
		<c:param name="maxlength" value="10" />
		<c:param name="aclaracio">
			<fmt:message key="consulta.aclaracio.formatData" />
		</c:param>
		<c:param name="clase" value="conMargen campoForm165" />
	</c:import> 
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="calendar" />
		<c:param name="path" value="formData.dataFi" />
		<c:param name="required" value="required" />
		<c:param name="title">
			<fmt:message key="consulta.camp.dataFi" />
		</c:param>
		<c:param name="camp" value="dataFi" />
		<c:param name="maxlength" value="10" />
		<c:param name="aclaracio">
			<fmt:message key="consulta.aclaracio.formatData" />
		</c:param>
		<c:param name="clase" value="campoForm165" />
	</c:import>
	
	
	
	

	<div class="separadorH"></div>
	
	<div class="separadorH"></div>
	
	<div class="botonesForm">
		<div id="guardarForm" class="btnCorto"
			onclick="submitForm('formulario')"
			onmouseover="underline('enlaceGuardarForm')"
			onmouseout="underline('enlaceGuardarForm')"><a
			id="enlaceGuardarForm" href="javascript:void(0);"><fmt:message
			key="manteniment.cercar" /></a></div>
	</div>

	<c:if test="${llistat != null && empty llistat}">
		<div class="separadorH"></div>
		<div class="mensajeErrorConsulta"><c:import
			url="comu/MissatgesIErrors.jsp">
			<c:param name="listError">
				<fmt:message key="consulta.entradaOliva.noRegs" />
			</c:param>
		</c:import></div>
	</c:if>

</fieldset>
</form>

<div class="separadorH"></div>
 
	<div id="listado"><%-- Tabla de resultados --%>
	<div class="layoutSombraTabla ancho">
		<c:if test="${llistat != null && not empty llistat}">
			<div class="rellenoInf"></div>
			<div class="rellenoIzq"></div>
			<div class="rellenoDer"></div>
			<div class="supDer"></div>
			<div class="supIzq"></div>
			<div class="infIzq"></div>
			<div class="infDer"></div>
		
		
			<display:table name="llistat" requestURI="" id="quadernCamp" export="true" sort="list" cellpadding="0" cellspacing="0" class="listadoAncho selectable ">
				<display:column sortProperty="data" titleKey="quadernCamp.camp.data" headerClass="ancho50" sortable="true" >
				<c:choose>
					<c:when test="${not empty esOlivicultor}">
						<a href="QuadernCampForm.html?id=${quadernCamp.id}">				
							<fmt:formatDate value="${quadernCamp.data}" pattern="dd/MM/yyyy"/>
						</a>
					</c:when>
					<c:otherwise>
						<fmt:formatDate value="${quadernCamp.data}" pattern="dd/MM/yyyy"/>
					</c:otherwise>
				</c:choose>
				</display:column>
				<display:column titleKey="quadernCamp.camp.plantacio" headerClass="ancho100" sortable="true">
					${quadernCamp.plantacio.finca.nom} - ${quadernCamp.plantacio.nomComplet} 
				</display:column>
				<display:column property="tractament" titleKey="quadernCamp.camp.tractament" headerClass="ancho75" sortable="true"/>
				<display:column property="materiaActiva" titleKey="quadernCamp.camp.materiaActiva" headerClass="ancho75" sortable="true"/>
				<display:column property="marca" titleKey="quadernCamp.camp.marca" headerClass="ancho75" sortable="true"/>
				<display:column property="dosi" titleKey="quadernCamp.camp.dosi" headerClass="ancho50" sortable="true"/>
				<display:column property="litresBrou" titleKey="quadernCamp.camp.litresBrou" headerClass="ancho50" sortable="true"/>
				<display:column property="terminiSeguretat" titleKey="quadernCamp.camp.terminiSeguretat" headerClass="ancho75" sortable="true"/>
				<display:column property="observacions" titleKey="quadernCamp.camp.observacions" headerClass="ancho75 final" sortable="true"/>
				<display:setProperty name="export.xml" value="false" />
		   		<display:setProperty name="export.csv" value="false" />
		   		<display:setProperty name="export.rtf" value="false" />
		   		<display:setProperty name="export.pdf" value="false" />
		   		<display:setProperty name="export.excel.include_header" value="true" />
		   		<display:setProperty name="export.excel.filename" value="QuadernDeCamp.xls" />
		   		<display:setProperty name="export.decorated" value="true" />
				</display:table>
		
			<%-- Colores en tablas --%>
			<script type="text/javascript">
					$(document).ready(function(){
						setEstilosTabla();
					})
				</script>
		</c:if>
	</div>
	
	<c:if test="${not empty esOlivicultor}">
		<form name="formulario" action="QuadernCampForm.html"></form>
		
		<form class="extended seguit">
			<div class="botonesForm">
				<div class="btnCorto" onmouseover="underline('enlaceCrearForm')"
					onmouseout="underline('enlaceCrearForm')"
					onclick="document.formulario.submit();">
					<a id="enlaceCrearForm"	href="javascript:void(0);"><fmt:message key="manteniment.crearnou" /></a>
				</div>
			</div>
		</form>
	</c:if>

</div>

</body>
</html>