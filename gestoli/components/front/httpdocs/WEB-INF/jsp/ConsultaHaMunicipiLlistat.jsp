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
<title><fmt:message key="consulta.haMunicipi.llistitol" /></title>
<script type="text/javascript" src="js/displaytag.js"></script>
<script type="text/javascript" src="js/form.js"></script>

</head>
<body>

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

<c:if test="${llistat != null && not empty llistat}">
	<div id="listado">
	<div class="layoutSombraTabla ancho">
		<div class="rellenoInf"></div>
		<div class="rellenoIzq"></div>
		<div class="rellenoDer"></div>
		<div class="supDer"></div>
		<div class="supIzq"></div>
		<div class="infIzq"></div>
		<div class="infDer"></div>
	
		<display:table name="llistat" requestURI="" id="registre" export="true" sort="list" cellpadding="0" cellspacing="0" class="listadoEstrecho"  defaultsort="1">
			<display:column titleKey="consulta.haMunicipi.camp.municipi" headerClass="ancho100" sortable="true" group="1" >
				${registre[0]}
			</display:column>
			<display:column titleKey="consulta.haMunicipi.camp.varietatOliva" headerClass="ancho100" sortable="true" group="2" >
				${registre[1]}
			</display:column>
			<display:column titleKey="consulta.haMunicipi.camp.superficieDO" headerClass="ancho100" sortable="true">
				<fmt:formatNumber var="ha1" type="number" pattern="0.00" value="${registre[3]}"/>
				${ha1} HA.
			</display:column>
			<display:column titleKey="consulta.haMunicipi.camp.superficieNoDO" headerClass="ancho100" sortable="true">
				<fmt:formatNumber var="ha2" type="number" pattern="0.00" value="${registre[4]}"/>
				${ha2} HA.
			</display:column>
			<display:column titleKey="consulta.haMunicipi.camp.superficieTotal" headerClass="ancho100 final" sortable="true">
				<fmt:formatNumber var="ha3" type="number" pattern="0.00" value="${registre[2]}"/>
				${ha3} HA.
			</display:column>
			<display:setProperty name="export.xml" value="false" />
	   		<display:setProperty name="export.csv" value="false" />
	   		<display:setProperty name="export.rtf" value="false" />
	   		<display:setProperty name="export.pdf" value="false" />
	   		<display:setProperty name="export.excel.include_header" value="true" />
	   		<display:setProperty name="export.excel.filename" value="HaMunicipi.xls" />
	   		<display:setProperty name="export.decorated" value="true" />
		</display:table>
	</div>
	</div>

</c:if>

<c:if test="${(llistat != null && not empty llistat)}">
	<%-- Colores en tablas --%>
	<script type="text/javascript">
			$(document).ready(function(){
				setEstilosTabla();
			})
		</script>
</c:if>

</body>
</html>