<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>
<%@ page buffer="64kb"%>
<%@ page autoFlush="true"%>


<html>
<head>
<title><fmt:message key="consulta.oliDisponible.titol" /></title>
<script type="text/javascript" src="js/displaytag.js"></script>
</head>
<body>

<div id="listado"><!-- Tabla de resultados -->
<div class="layoutSombraTabla ancho"><c:if
	test="${not empty llistat}">
	<div class="rellenoInf"></div>
	<div class="rellenoIzq"></div>
	<div class="rellenoDer"></div>
	<div class="supDer"></div>
	<div class="supIzq"></div>
	<div class="infIzq"></div>
	<div class="infDer"></div>
</c:if> 

<jsp:scriptlet>
   org.displaytag.decorator.MultilevelTotalTableDecorator subtotals = new org.displaytag.decorator.MultilevelTotalTableDecorator();
   subtotals.setGrandTotalDescription("Total");    // optional, defaults to Grand Total
   subtotals.setSubtotalLabel("{0} Subtotal", request.getLocale());      // optional, defaults to "{0} Total"
   pageContext.setAttribute("subtotaler", subtotals);
</jsp:scriptlet>
    
<display:table name="llistat" requestURI="" id="establiment" export="true" sort="list" cellpadding="0" cellspacing="0" class="listadoAncho selectable" decorator="subtotaler">
	<display:column titleKey="establiment.camp.nom" sortable="true" sortProperty="nom">
		<a href="ConsultaOliDisponibleLlistat.html?idEstabliment=<c:out value="${establiment[0]}"/>&amp;fromEstabliment=true"><c:out value="${establiment[1]}" /></a>
	</display:column>
	<display:column titleKey="establiment.camp.cif" sortable="true" headerClass="ancho160">
		<c:out value="${establiment[2]}" />
	</display:column>
	<display:column property="[3]" titleKey="consulta.oliDisponible.camp.dipositDO" sortable="true" sortProperty="[3]" total="true" format="{0,number,#,##0.00}" />
	<display:column property="[4]" titleKey="consulta.oliDisponible.camp.dipositPendent" sortable="true" sortProperty="[4]" total="true" format="{0,number,#,##0.00}"/>
	<display:column property="[5]" titleKey="consulta.oliDisponible.camp.dipositNoDO" sortable="true" sortProperty="[5]" total="true" format="{0,number,#,##0.00}"/>
	<display:column property="[6]" titleKey="consulta.oliDisponible.camp.lotDO" sortable="true" sortProperty="[6]" total="true" format="{0,number,#,##0.00}"/>
	<display:column property="[7]" titleKey="consulta.oliDisponible.camp.lotNoDO" sortable="true" sortProperty="[7]" total="true" format="{0,number,#,##0.00}"/>
	<display:column property="[8]" titleKey="consulta.oliDisponible.camp.total" sortable="true" sortProperty="[8]" total="true" format="{0,number,#,##0.00}" headerClass="final" class="total" />
	<display:setProperty name="export.xml" value="false" />
	<display:setProperty name="export.csv" value="false" />
	<display:setProperty name="export.rtf" value="false" />
	<display:setProperty name="export.pdf" value="false" />
	<display:setProperty name="export.excel.include_header" value="true" />
	<display:setProperty name="export.excel.filename" value="OliDisponible.xls" />
	<display:setProperty name="export.decorated" value="true" />
</display:table>
</div>

<div class="separadorH"></div>

<form name="formulario" action="EstablimentForm.html"></form>

</div>

<c:if test="${not empty llistat}">
	<!-- Colores en tablas -->
	<script type="text/javascript">
			$(document).ready(function(){
				setEstilosTabla();
			})
		</script>
</c:if>




</body>
</html>


















