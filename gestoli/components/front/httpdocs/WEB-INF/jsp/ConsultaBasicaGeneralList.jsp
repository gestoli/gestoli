<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>
<%@ page buffer="64kb"%>
<%@ page autoFlush="true"%>

<html>
	<head>
	    <title><fmt:message key="consulta.basicageneral.titol"/></title>
	    <script type="text/javascript" src="js/displaytag.js"></script>
	</head>
<body>

	<form id="form" action="ConsultaBasicaGeneralFiltre.html" method="post" class="extended seguit">
	<h4><fmt:message key="consulta.basicageneral.filtre"/></h4>
		<div style="display:none">
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
		</div>
		<div class="separadorH"></div>
		<div class="separadorH"></div>
		
		<c:set var="comptador" scope="page" value="0"/>
		<div id="consultaResultat" class="well" >
			<div class="row-fluid">
					<c:if test="${formData.partidaNomC eq 'S'}">
						<c:set var="comptador" scope="page" value="${comptador + 1}"/>
						<c:if test="${comptador gt 4}">
							<c:set var="comptador" scope="page" value="1"/>
					 			<c:out value="</div>"/>
					 		<div class="row-fluid">
					 	</c:if>
					 	<div class="span3">
					        <c:import url="comu/CampFormulari.jsp">
					    	    <c:param name="tipus" value="text"/>
					    	    <c:param name="path" value="formData.partidaNom"/>
		<%-- 			            <c:param name="required" value="required"/> --%>
					            <c:param name="title"><fmt:message key="consulta.basicageneral.partida"/></c:param>
					            <c:param name="camp" value="partidaNom"/>
					            <c:param name="class" value="form-control input-md"/>
					            <c:param name="maxlength" value="40"/>
					        </c:import>
						</div>
					</c:if>
			
					<c:if test="${formData.lotNomC eq 'S'}">
						<c:set var="comptador" scope="page" value="${comptador + 1}"/>
						<c:if test="${comptador gt 4}">
							<c:set var="comptador" scope="page" value="1"/>
					 		<c:out value="</div>"/>
					 		<div class="row-fluid">
					 	</c:if>
					 	<div class="span3">
					        <c:import url="comu/CampFormulari.jsp">
					    	    <c:param name="tipus" value="text"/>
					    	    <c:param name="path" value="formData.lotNom"/>
		<%-- 			            <c:param name="required" value="required"/> --%>
					            <c:param name="title"><fmt:message key="consulta.basicageneral.lot"/></c:param>
					            <c:param name="camp" value="lotNom"/>
					            <c:param name="class" value="form-control input-md"/>
					            <c:param name="maxlength" value="40"/>
					        </c:import>
						</div>
					</c:if>
					
					<c:if test="${formData.zonaC eq 'S'}">
						<c:set var="comptador" scope="page" value="${comptador + 1}"/>
						<c:if test="${comptador gt 4}">
							<c:set var="comptador" scope="page" value="1"/>
					 		<c:out value="</div>"/>
					 		<div class="row-fluid">
					 	</c:if>
					 	<div class="span3">
					        <c:import url="comu/CampFormulari.jsp">
					    	    <c:param name="tipus" value="text"/>
					    	    <c:param name="path" value="formData.zona"/>
		<%-- 			            <c:param name="required" value="required"/> --%>
					            <c:param name="title"><fmt:message key="consulta.basicageneral.zona"/></c:param>
					            <c:param name="camp" value="zona"/>
					            <c:param name="class" value="form-control input-md"/>
					            <c:param name="maxlength" value="40"/>
					        </c:import>
						</div>
					</c:if>

					
			</div>
		</div>
		<div class="separadorH"></div>
		<div class="separadorH"></div>	
		    <div class="form-actions">
				<input type="submit" class="btn btn-green floLeft" value="<fmt:message key="manteniment.cercar"/>"/>
				<input type="button" onClick="top.location.href='ConsultaBasicaGeneral.lmt';"  class="btn floLeft" value="<fmt:message key="proces.cancelar"/>"/>
			</div>
		<div class="separadorH"></div>	
		<div class="separadorH"></div>	
		<c:if test="${not empty consultaGeneral}">
			<div id="llistat">
					<div class="layoutSombraTabla ancho"></div>
					<div class="rellenoInf"></div>
					<div class="rellenoIzq"></div>
					<div class="rellenoDer"></div>
					<div class="supDer"></div>
					<div class="supIzq"></div>
					<div class="infIzq"></div>
					<div class="infDer"></div>
				
				 <h3><fmt:message key="consulta.basicageneral.titol"/></h3>
			   	<div class="separadorH"></div>
			   	
				    <jsp:scriptlet>
						org.displaytag.decorator.MultilevelTotalTableDecorator subtotals = new org.displaytag.decorator.MultilevelTotalTableDecorator();
				       subtotals.setGrandTotalDescription("Total");    // optional, defaults to Grand Total
				       subtotals.setSubtotalLabel("{0} Subtotal", request.getLocale());      // optional, defaults to "{0} Total"
				       pageContext.setAttribute("subtotaler", subtotals);
				    </jsp:scriptlet>
					<display:table name="consultaGeneral" requestURI="" id="fila" sort="list" class="displaytag selectable"  export="true" decorator="subtotaler">
						<c:if test="${not empty formData.partidaNomC}"><display:column titleKey="consulta.basicageneral.partida" ><c:out value="${fila.partidaNom}"/></display:column></c:if>
						<c:if test="${not empty formData.lotNomC}"><display:column titleKey="consulta.basicageneral.lot" ><c:out value="${fila.lotNom}"/></display:column></c:if>
						<c:if test="${not empty formData.zonaC}"><display:column titleKey="consulta.basicageneral.zona" ><c:out value="${fila.zona}"/></display:column></c:if>
						<display:setProperty name="export.xml" value="false" />
				    	<display:setProperty name="export.csv" value="false" />
				    	<display:setProperty name="export.rtf" value="false" />
				    	<display:setProperty name="export.excel.include_header" value="true" />
				    	<display:setProperty name="export.excel.filename" value="llistat_consulta_general.xlsx" />
				    	<display:setProperty name="export.decorated" value="true" />
					</display:table>
				
			</div>
		</c:if>
		<c:if test="${not empty senseDades}">
				<fmt:message key="consulta.basicageneral.sensedades"/>
		</c:if>
		<div class="separadorH"></div>
	</form>
	<c:if test="${not empty consultaGeneral}">
			<!-- Colores en tablas -->
			<script type="text/javascript">
				$(document).ready(function(){
					setEstilosTabla();
				})
			</script>
	</c:if>
</body>
</html>