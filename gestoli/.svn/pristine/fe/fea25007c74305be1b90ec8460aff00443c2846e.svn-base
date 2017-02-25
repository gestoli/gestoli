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
<title><fmt:message key="consulta.movimentsEntreEstabliments.titol" /></title>

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

<%-- 
    <c:if test="${not empty llistat}">
    	<script type="text/javascript" src="js/displaytag.js"></script>    	
    </c:if>
    --%>

</head>
<body>
<form id="formulario" action="ConsultaMovimentsEntreEstablimentsLlistat.html" method="post" class="extended seguit">
<fieldset><c:import url="comu/CampFormulari.jsp">
	<c:param name="tipus" value="hidden" />
	<c:param name="path" value="formData.fromEstabliment" />
	<c:param name="camp" value="fromEstabliment" />
</c:import> <c:import url="comu/CampFormulari.jsp">
	<c:param name="tipus" value="hidden" />
	<c:param name="path" value="formData.idEstabliment" />
	<c:param name="camp" value="idEstabliment" />
</c:import> <c:import url="comu/CampFormulari.jsp">
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
</c:import> <c:import url="comu/CampFormulari.jsp">
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

<div class="botonesForm">
<div id="guardarForm" class="btnCorto"
	onclick="submitForm('formulario')"
	onmouseover="underline('enlaceGuardarForm')"
	onmouseout="underline('enlaceGuardarForm')"><a
	id="enlaceGuardarForm" href="javascript:void(0);"><fmt:message
	key="manteniment.cercar" /></a></div>
</div>

<c:if test="${(llistatSortides != null && empty llistatSortides) && (llistatEntrades != null && empty llistatEntrades) && (llistatSortidesPendents != null && empty llistatSortidesPendents)}">
	<div class="separadorH"></div>
	<div class="mensajeErrorConsulta"><c:import
		url="comu/MissatgesIErrors.jsp">
		<c:param name="listError">
			<fmt:message key="consulta.entradaOliva.noRegs" />
		</c:param>
	</c:import></div>
</c:if></fieldset>
</form>

<div class="separadorH"></div>

<jsp:scriptlet>
       org.displaytag.decorator.MultilevelTotalTableDecorator subtotals = new org.displaytag.decorator.MultilevelTotalTableDecorator();
       subtotals.setGrandTotalDescription("Total");    // optional, defaults to Grand Total
       subtotals.setSubtotalLabel("{0} Subtotal", request.getLocale());      // optional, defaults to "{0} Total"
       pageContext.setAttribute("subtotaler", subtotals);
    </jsp:scriptlet>

<jsp:scriptlet>
       org.displaytag.decorator.MultilevelTotalTableDecorator subtotals2 = new org.displaytag.decorator.MultilevelTotalTableDecorator();
       subtotals2.setGrandTotalDescription("Total");    // optional, defaults to Grand Total
       subtotals2.setSubtotalLabel("{0} Subtotal", request.getLocale());      // optional, defaults to "{0} Total"
       pageContext.setAttribute("subtotaler2", subtotals2);
    </jsp:scriptlet>
    
<c:if test="${llistatSortidesPendents != null && not empty llistatSortidesPendents}">
	<div id="listado">
	<div style="margin: 0 0 5px 30px;"><h2><fmt:message key="consulta.movimentsEntreEstabliments.titol.sortides.pendents"/></h2></div>
	<div class="layoutSombraTabla ancho">
	<div class="rellenoInf"></div>
	<div class="rellenoIzq"></div>
	<div class="rellenoDer"></div>
	<div class="supDer"></div>
	<div class="supIzq"></div>
	<div class="infIzq"></div>
	<div class="infDer"></div>

	<display:table name="llistatSortidesPendents" requestURI="" id="trasllat" export="true" sort="list" cellpadding="0" cellspacing="0" class="listadoAncho selectable noEnlace" decorator="subtotaler">
		<display:column titleKey="manteniment.categoria" sortable="true" headerClass="ancho75" group="1">
			<c:if test="${not empty trasllat.categoriaOli && not empty trasllat.categoriaOli.id}">
				<fmt:message key="consulta.general.camp.categoriaOli.${trasllat.categoriaOli.id}"/>
			</c:if>
		</display:column>
		
		<display:column titleKey="consulta.movimentsEntreEstabliments.camp.data" sortable="true" sortProperty="data" headerClass="ancho75">
			<c:out value="${trasllat.dataAcceptarEnviamentFormat}" />
		</display:column>
		
		<display:column titleKey="consulta.movimentsEntreEstabliments.camp.origen" sortable="true">
			<c:out value="${trasllat.establimentByTdiCodeor.nom}" />
		</display:column>
		<display:column titleKey="consulta.movimentsEntreEstabliments.camp.destino" headerClass="ancho160" sortable="true">
			<c:out value="${trasllat.establimentByTdiCodede.nom}" />
		</display:column>

		<display:column titleKey="consulta.movimentsEntreEstabliments.camp.deposito" headerClass="ancho75" sortable="true">
			<c:out value="${trasllat.nomDiposits}" />
		</display:column>
		<display:column titleKey="consulta.movimentsEntreEstabliments.camp.quantitat" headerClass="ancho75" sortable="true" property="quantitatMostrar" total="true" format="{0,number,#,##0.00}" />
		
		<display:column titleKey="consulta.movimentsEntreEstabliments.camp.tipus" headerClass="ancho75" sortable="true">
			<c:choose>
				<c:when test="${trasllat.esDiposit}">
					<fmt:message key="consulta.movimentsEntreEstabliments.camp.tipus.diposit"/>
				</c:when>
				<c:otherwise>
					<fmt:message key="consulta.movimentsEntreEstabliments.camp.tipus.granel"/>
				</c:otherwise>
			</c:choose>
		</display:column>
		
		<display:column titleKey="consulta.movimentsEntreEstabliments.camp.volante" headerClass="ancho75" sortable="true">
			<c:out value="${trasllat.id}" />
			<form id="formulario" action="<%=request.getContextPath()%>/GenerarPdf.html" method="post" class="ancho20">
				<input type="hidden" name="tipus" value="14" />
				<input type="hidden" name="trasllat" value="${trasllat.id}" />
				<input type="hidden" name="sentit" value="${trasllat.sentit}" />
				<input type="submit" value="" class="exportPDFWidth16" alt="<fmt:message key="consulta.movimentsEntreEstabliments.camp.volante"/>" title="<fmt:message key="consulta.movimentsEntreEstabliments.camp.volante"/>" />
			</form>
		</display:column>
		
		<display:column headerClass="ancho20 paddingCelda final">
			<a href="ConsultaTrazabilitat.html?tipus=<c:out value="${trazaTipusEntradaVolant}"/>&amp;id=<c:out value="${trasllat.id}"/>"><img
				src="img/icons/trazabilidad.gif" border="0"
				title="<fmt:message key="consulta.trazabilitat.titol"/>"
				alt="<fmt:message key="consulta.trazabilitat.titol"/>" /></a>
		</display:column>
		<display:setProperty name="export.xml" value="false" />
   		<display:setProperty name="export.csv" value="false" />
   		<display:setProperty name="export.rtf" value="false" />
   		<display:setProperty name="export.pdf" value="false" />
   		<display:setProperty name="export.excel.include_header" value="true" />
   		<display:setProperty name="export.excel.filename" value="MovimentsEntreEstabliments.xls" />
   		<display:setProperty name="export.decorated" value="true" />
	</display:table></div>
	</div>

</c:if>

<div class="separadorH"></div>

<c:if test="${llistatSortides != null && not empty llistatSortides}">
	<div id="listado">
	<div style="margin: 0 0 5px 30px;"><h2><fmt:message key="consulta.movimentsEntreEstabliments.titol.sortides"/></h2></div>
	<div class="layoutSombraTabla ancho">
	<div class="rellenoInf"></div>
	<div class="rellenoIzq"></div>
	<div class="rellenoDer"></div>
	<div class="supDer"></div>
	<div class="supIzq"></div>
	<div class="infIzq"></div>
	<div class="infDer"></div>

	<display:table name="llistatSortides" requestURI="" id="trasllat" export="true" sort="list" cellpadding="0" cellspacing="0" class="listadoAncho selectable noEnlace" decorator="subtotaler">
		<display:column titleKey="manteniment.categoria" sortable="true" headerClass="ancho75" group="1">
			<c:if test="${not empty trasllat.categoriaOli && not empty trasllat.categoriaOli.id}">
				<fmt:message key="consulta.general.camp.categoriaOli.${trasllat.categoriaOli.id}"/>
			</c:if>
		</display:column>
		
		<display:column titleKey="consulta.movimentsEntreEstabliments.camp.data" sortable="true" sortProperty="data" headerClass="ancho75">
			<c:out value="${trasllat.dataAcceptarEnviamentFormat}" />
		</display:column>
		
		<display:column titleKey="consulta.movimentsEntreEstabliments.camp.origen" sortable="true">
			<c:out value="${trasllat.establimentByTdiCodeor.nom}" />
		</display:column>
		<display:column titleKey="consulta.movimentsEntreEstabliments.camp.destino" headerClass="ancho160" sortable="true">
			<c:out value="${trasllat.establimentByTdiCodede.nom}" />
		</display:column>

		<display:column titleKey="consulta.movimentsEntreEstabliments.camp.deposito" headerClass="ancho75" sortable="true">
			<c:out value="${trasllat.nomDiposits}" />
		</display:column>
		<display:column titleKey="consulta.movimentsEntreEstabliments.camp.quantitat" headerClass="ancho75" sortable="true" property="quantitatMostrar" total="true" format="{0,number,#,##0.00}" />
		
		<display:column titleKey="consulta.movimentsEntreEstabliments.camp.tipus" headerClass="ancho75" sortable="true">
			<c:choose>
				<c:when test="${trasllat.esDiposit}">
					<fmt:message key="consulta.movimentsEntreEstabliments.camp.tipus.diposit"/>
				</c:when>
				<c:otherwise>
					<fmt:message key="consulta.movimentsEntreEstabliments.camp.tipus.granel"/>
				</c:otherwise>
			</c:choose>
		</display:column>
		
		<display:column titleKey="consulta.movimentsEntreEstabliments.camp.volante" headerClass="ancho75" sortable="true">
			<c:out value="${trasllat.id}" />
			<form id="formulario" action="<%=request.getContextPath()%>/GenerarPdf.html" method="post" class="ancho20">
				<input type="hidden" name="tipus" value="14" />
				<input type="hidden" name="trasllat" value="${trasllat.id}" />
				<input type="hidden" name="sentit" value="${trasllat.sentit}" />
				<input type="submit" value="" class="exportPDFWidth16" alt="<fmt:message key="consulta.movimentsEntreEstabliments.camp.volante"/>" title="<fmt:message key="consulta.movimentsEntreEstabliments.camp.volante"/>" />
			</form>
		</display:column>
		
		<display:column headerClass="ancho20 paddingCelda final">
			<a href="ConsultaTrazabilitat.html?tipus=<c:out value="${trazaTipusEntradaVolant}"/>&amp;id=<c:out value="${trasllat.id}"/>"><img
				src="img/icons/trazabilidad.gif" border="0"
				title="<fmt:message key="consulta.trazabilitat.titol"/>"
				alt="<fmt:message key="consulta.trazabilitat.titol"/>" /></a>
		</display:column>
		<display:setProperty name="export.xml" value="false" />
   		<display:setProperty name="export.csv" value="false" />
   		<display:setProperty name="export.rtf" value="false" />
   		<display:setProperty name="export.pdf" value="false" />
   		<display:setProperty name="export.excel.include_header" value="true" />
   		<display:setProperty name="export.excel.filename" value="MovimentsEntreEstabliments.xls" />
   		<display:setProperty name="export.decorated" value="true" />
	</display:table></div>
	</div>

</c:if>

<div class="separadorH"></div>

<c:if test="${llistatEntrades != null && not empty llistatEntrades}">

	<div id="listado">
	<div style="margin: 30px 0 5px 30px;"><h2><fmt:message key="consulta.movimentsEntreEstabliments.titol.entrades"/></h2></div>
	<div class="layoutSombraTabla ancho">
	<div class="rellenoInf"></div>
	<div class="rellenoIzq"></div>
	<div class="rellenoDer"></div>
	<div class="supDer"></div>
	<div class="supIzq"></div>
	<div class="infIzq"></div>
	<div class="infDer"></div>

	<display:table name="llistatEntrades" requestURI="" id="trasllat" export="true" sort="list" cellpadding="0" cellspacing="0" class="listadoAncho selectable noEnlace" decorator="subtotaler2">
		<display:column titleKey="manteniment.categoria" sortable="true" headerClass="ancho75" group="1">
			<c:if test="${not empty trasllat.categoriaOli && not empty trasllat.categoriaOli.id}">
				<fmt:message key="consulta.general.camp.categoriaOli.${trasllat.categoriaOli.id}"/>
			</c:if>
		</display:column>
		<c:choose>
			<c:when test="${trasllat.esDiposit}">
				<display:column titleKey="consulta.movimentsEntreEstabliments.camp.data" sortable="true" sortProperty="data" headerClass="ancho75">
					<c:out value="${trasllat.dataAcceptarRetornFormat}" />
				</display:column>
				<display:column titleKey="consulta.movimentsEntreEstabliments.camp.origen" sortable="true">
					<c:out value="${trasllat.establimentByTdiCodede.nom}" />
				</display:column>
				<display:column titleKey="consulta.movimentsEntreEstabliments.camp.destino" headerClass="ancho160" sortable="true">
					<c:out value="${trasllat.establimentByTdiCodeor.nom}" />
				</display:column>
			</c:when>
			<c:otherwise>
				<display:column titleKey="consulta.movimentsEntreEstabliments.camp.data" sortable="true" sortProperty="data" headerClass="ancho75">
					<c:out value="${trasllat.dataAcceptarEnviamentFormat}" />
				</display:column>
				<display:column titleKey="consulta.movimentsEntreEstabliments.camp.origen" sortable="true" >
					<c:out value="${trasllat.establimentByTdiCodeor.nom}" />
				</display:column>
				<display:column titleKey="consulta.movimentsEntreEstabliments.camp.destino" headerClass="ancho160" sortable="true">
					<c:out value="${trasllat.establimentByTdiCodede.nom}" />
				</display:column>
			</c:otherwise>
		</c:choose>
		
		<display:column titleKey="consulta.movimentsEntreEstabliments.camp.deposito" headerClass="ancho75" sortable="true">
			<c:out value="${trasllat.nomDiposits}" />
		</display:column>
		
		
		<display:column titleKey="consulta.movimentsEntreEstabliments.camp.quantitat" headerClass="ancho75" sortable="true" property="quantitatMostrar" total="true" format="{0,number,#,##0.00}" />

		
		<display:column titleKey="consulta.movimentsEntreEstabliments.camp.tipus" headerClass="ancho75" sortable="true">
			<c:choose>
				<c:when test="${trasllat.esDiposit}">
					<fmt:message key="consulta.movimentsEntreEstabliments.camp.tipus.diposit"/>
				</c:when>
				<c:otherwise>
					<fmt:message key="consulta.movimentsEntreEstabliments.camp.tipus.granel"/>
				</c:otherwise>
			</c:choose>
		</display:column>
		
		<display:column titleKey="consulta.movimentsEntreEstabliments.camp.volante" headerClass="ancho75" sortable="true">
			<c:out value="${trasllat.id}" />
			<form id="formulario" action="<%=request.getContextPath()%>/GenerarPdf.html" method="post" class="ancho20">
				<input type="hidden" name="tipus" value="14" />
				<input type="hidden" name="trasllat" value="${trasllat.id}" />
				<c:choose>
					<c:when test="${trasllat.sentit == 1}"><input type="hidden" name="sentit" value="1" /></c:when>
					<c:otherwise><input type="hidden" name="sentit" value="0" /></c:otherwise>
				</c:choose>
				<input type="submit" value="" class="exportPDFWidth16" alt="<fmt:message key="consulta.movimentsEntreEstabliments.camp.volante"/>" title="<fmt:message key="consulta.movimentsEntreEstabliments.camp.volante"/>" />
			</form>
		</display:column>
		<display:column headerClass="ancho20 paddingCelda final">
			<a href="ConsultaTrazabilitat.html?tipus=<c:out value="${trazaTipusEntradaVolant}"/>&amp;id=<c:out value="${trasllat.id}"/>"><img
				src="img/icons/trazabilidad.gif" border="0"
				title="<fmt:message key="consulta.trazabilitat.titol"/>"
				alt="<fmt:message key="consulta.trazabilitat.titol"/>" /></a>
		</display:column>
		<display:setProperty name="export.xml" value="false" />
   		<display:setProperty name="export.csv" value="false" />
   		<display:setProperty name="export.rtf" value="false" />
   		<display:setProperty name="export.pdf" value="false" />
   		<display:setProperty name="export.excel.include_header" value="true" />
   		<display:setProperty name="export.excel.filename" value="MovimentsEntreEstabliments.xls" />
   		<display:setProperty name="export.decorated" value="true" />
	</display:table></div>
	</div>


</c:if>

<c:if test="${(llistatSortides != null && not empty llistatSortides) || (llistatEntrades != null && not empty llistatEntrades)}">
	<script type="text/javascript">
			$(document).ready(function(){
				setEstilosTabla();
			})
		</script>
</c:if>

</body>
</html>
