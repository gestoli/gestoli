<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>


<html>
<head>
<title><fmt:message key="consulta.oliComercialitzat.titol" /></title>
<script type="text/javascript" src="js/displaytag.js"></script>
</head>
<body>

<div id="listado"><!-- Tabla de resultados -->
<div class="layoutSombraTabla ancho">
<c:if test="${not empty llistat}">
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

<display:table name="llistat" requestURI="" id="establiment"  sort="list" cellpadding="0" cellspacing="0" class="listadoAncho selectable" decorator="subtotaler">
	<display:column property="[3]" titleKey="consulta.oliComercialitzat.camp.dipositDO" sortable="true" sortProperty="[3]" total="true" format="{0,number,#,##0.00}" />
	<display:column property="[4]" titleKey="consulta.oliComercialitzat.camp.dipositPendent" sortable="true" sortProperty="[4]" total="true" format="{0,number,#,##0.00}"/>
	<display:column property="[5]" titleKey="consulta.oliComercialitzat.camp.dipositNoDO" sortable="true" sortProperty="[5]" total="true" format="{0,number,#,##0.00}"/>
	<display:column property="[6]" titleKey="consulta.oliComercialitzat.camp.lotDO" sortable="true" sortProperty="[6]" total="true" />
	<display:column property="[7]" titleKey="consulta.oliComercialitzat.camp.lotNoDO" sortable="true" sortProperty="[7]" total="true" />
	<%--display:column property="[8]" titleKey="consulta.oliComercialitzat.camp.total" sortable="true" sortProperty="[8]" total="true" format="{0,number,#,##0.00}" headerClass="final" class="total" /--%>
</display:table></div>

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


















