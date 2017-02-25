<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title><fmt:message key="consulta.basicageneral.titol"/></title>
    <script type="text/javascript" src="js/form.js"></script>
    <script type="text/javascript" src="js/jscalendar/calendar_stripped.js"></script>
	<script type="text/javascript" src="js/jscalendar/calendar-setup_stripped.js"></script>
	<script type="text/javascript" src="js/jscalendar/lang/<fmt:message key="webapp.calendar"/>"></script>
	<link href="js/jscalendar/calendar-viti.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="js/selectbox.js"></script>
	<link href="css/form.css" rel="stylesheet" type="text/css"/>
    <c:if test="${not empty llistat}">
    	<script type="text/javascript" src="js/displaytag.js"></script>
    	<link href="css/displaytag.css" rel="stylesheet" type="text/css"/>
    </c:if>
    <script type="text/javascript" src="dwr/interface/contenidorService.js"></script>
	<script type="text/javascript" src="dwr/engine.js"></script>
	<script type="text/javascript" src="dwr/util.js"> </script>
	<%-- CampFormulari llibreries --%>
	<script type="text/javascript" src="<c:url value='js/selectbox.js'/>"></script>
	<script type="text/javascript" src="js/editablesel.js"></script>
	<link href="css/editablesel.css" rel="stylesheet" type="text/css"/>
	<%-- --- --%>
	
</head>
<body>

	<c:set var="si" value="S"/>
	<c:set var="no" value="N"/>
<%-- 	<c:if test="${empty selectedValue}"><c:set var="selectedValue" value="${formData.bodega}"/></c:if> --%>
	
	
	<form id="form" action="ConsultaBasicaGeneralFiltre.html" method="post" class="extended seguit descubado">
		<h3><fmt:message key="consulta.basicageneral.seleccio"/></h3>
		<div class="well" id="campsConsulta">
				<div class="row-fluid">
				 	<div class="span3">
					        <c:import url="comu/CampFormulari.jsp">
					    	    <c:param name="tipus" value="checkbox"/>
					    	    <c:param name="path" value="formData.partidaNomC"/>
		<%-- 			            <c:param name="required" value="required"/> --%>
					            <c:param name="title"><fmt:message key="consulta.basicageneral.partida"/></c:param>
					            <c:param name="class" value="form-control input-md"/>
					            <c:param name="camp" value="partidaNomC"/>
					            <c:param name="maxlength" value="40"/>
					        </c:import>
						</div>
				 	
				 	
				 	
				 	<div class="span3">
					        <c:import url="comu/CampFormulari.jsp">
					    	    <c:param name="tipus" value="checkbox"/>
					    	    <c:param name="path" value="formData.lotNomC"/>
		<%-- 			            <c:param name="required" value="required"/> --%>
					            <c:param name="title"><fmt:message key="consulta.basicageneral.lot"/></c:param>
					            <c:param name="class" value="form-control input-md"/>
					            <c:param name="camp" value="lotNomC"/>
					            <c:param name="maxlength" value="40"/>
					        </c:import>
					</div>
					<div class="span3">
					        <c:import url="comu/CampFormulari.jsp">
					    	    <c:param name="tipus" value="checkbox"/>
					    	    <c:param name="path" value="formData.zonaC"/>
		<%-- 			            <c:param name="required" value="required"/> --%>
					            <c:param name="title"><fmt:message key="consulta.basicageneral.zona"/></c:param>
					            <c:param name="class" value="form-control input-md"/>
					            <c:param name="camp" value="zonaC"/>
					            <c:param name="maxlength" value="40"/>
					        </c:import>
					</div>
				</div>
		</div>
				
		<div class="form-actions">
			<input type="submit" class="btn btn-green floLeft" value="<fmt:message key="manteniment.cercar"/>"/>
			<input type="button" onClick="top.location.href='ProcesInici.lmt';"  class="btn floLeft" value="<fmt:message key="proces.cancelar"/>"/>
		</div>
		<c:if test="${not empty consultaGeneral}">
			<div class="form-actions">
				<input type="submit" class="btn btn-green floLeft" value="<fmt:message key="manteniment.continuar"/>"/>
			</div>
		
				<display:table name="consultaGeneral" requestURI="" id="fila" sort="list" class="displaytag selectable"  export="true">
					<c:if test="${not empty formData.partidaNomC}"><display:column titleKey="consulta.basicageneral.partida" ><c:out value="${fila.partidaNom}"/></display:column></c:if>
					<c:if test="${not empty formData.lotNomC}"><display:column titleKey="consulta.basicageneral.lot" ><c:out value="${fila.lotNom}"/></display:column></c:if>
					<c:if test="${not empty formData.zonaC}"><display:column titleKey="consulta.basicageneral.zona" ><c:out value="${fila.zona}"/></display:column></c:if>
			        <display:setProperty name="export.xml" value="false" /> 
			    	<display:setProperty name="export.csv" value="false" />
			    	<display:setProperty name="export.rtf" value="false" />
			    	<display:setProperty name="export.excel.include_header" value="true" />
			    	<display:setProperty name="export.excel.filename" value="Consulta_general.xls" />
			    	<display:setProperty name="export.decorated" value="true" />
				</display:table>
		</c:if>
		
	</form>
	
	
	</body>
</html>