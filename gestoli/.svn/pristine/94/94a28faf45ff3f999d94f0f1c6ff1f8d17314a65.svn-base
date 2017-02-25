<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>
<%@ page import="es.caib.gestoli.front.util.*"%>
<%@ page import="java.util.ResourceBundle"%>
<%@ page import="java.util.Locale"%>
<%@ page import="org.displaytag.*"%>
<%@ page buffer="64kb"%>
<%@ page autoFlush="true"%>
<%
	String lang = Idioma.getLang(request);
	request.setAttribute("lang",lang);
%>

<html>
<head>
<title><fmt:message key="consulta.oliDisponible.titol" /></title>

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

<c:if test="${not empty llistat}">
	<script type="text/javascript" src="js/displaytag.js"></script>
</c:if>

</head>
<body>
<form id="formulario" action="ConsultaOliDisponibleLlistat.html"
	method="post" class="extended seguit">
	<fieldset>
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="hidden" />
			<c:param name="path" value="formData.fromEstabliment" />
			<c:param name="camp" value="fromEstabliment" />
		</c:import> 
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="hidden" />
			<c:param name="path" value="formData.idEstabliment" />
			<c:param name="camp" value="idEstabliment" />
		</c:import> 
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="select" />
			<c:param name="path" value="formData.categoriaOli" />
			<c:param name="title">
				<fmt:message key="consulta.oliDisponible.camp.categoria" />
			</c:param>
			<c:param name="camp" value="categoriaOli" />
			<c:param name="required" value="required" />
			<c:param name="selectItems" value="categoriasOli" />
			<c:param name="selectItemsId" value="id" />
			<c:param name="selectItemsValue" value="nom" />
			<c:param name="selectSelectedValue" value="${formData.categoriaOli}" />
			<%-- <c:param name="ambOpcioBuida" value="false"/> --%>
			<c:param name="clase" value="campoFormTresCuartos" />
		</c:import>
		
		<div class="separadorH"></div>

		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="radio" />
			<c:param name="path" value="formData.mesura" />
			<c:param name="title">
				<fmt:message key="consulta.llibres.mesura" />
			</c:param>
			<c:param name="camp" value="mesura" />
			<c:param name="required" value="required" />
			<c:param name="selectItems" value="tipusMesura" />
			<c:param name="selectItemsId" value="val" />
			<c:param name="selectItemsValue" value="nom" />
			<c:param name="selectSelectedValue" value="${formData.mesura}" />
		</c:import>
		
		<div class="separadorH"></div>

		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="calendar" />
			<c:param name="path" value="formData.data" />
			<c:param name="title">
				<fmt:message key="consulta.camp.data" />
			</c:param>
			<c:param name="camp" value="campo_dataElaboracio" />
			<c:param name="name" value="data" />
			<c:param name="maxlength" value="10" />
			<c:param name="required" value="required" />
			<c:param name="aclaracio">
				<fmt:message key="proces.aclaracio.formatdata" />
			</c:param>
			<c:param name="clase" value="conMargen campoForm165" />
		</c:import>

		<div class="separadorH"></div>
		
		<div class="botonesForm">
			<div id="guardarForm" class="btnCorto"
				onclick="submitForm('formulario')"
				onmouseover="underline('enlaceGuardarForm')"
				onmouseout="underline('enlaceGuardarForm')">
				<a id="enlaceGuardarForm" href="javascript:void(0);">
					<fmt:message key="manteniment.cercar" />
				</a>
			</div>
		</div>

		<c:if test="${llistatDepositos != null && empty llistatDepositos && llistatLotes != null && empty llistatLotes}">
			<div class="separadorH"></div>
			<div class="mensajeErrorConsulta">
				<c:import url="comu/MissatgesIErrors.jsp">
					<c:param name="listError">
						<fmt:message key="consulta.entradaOliva.noRegs" />
					</c:param>
				</c:import>
			</div>
		</c:if>
	</fieldset>
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

<c:if test="${(llistatDepositos != null && not empty llistatDepositos)}">
	<div class="depositosEstrecho">
	<div class="lotes" style="width: 745px; margin: 30px 0pt 0pt 40px;">
		<h1><fmt:message key="consulta.oliDisponible.titol.granel" /></h1>
	</div>
	<div class="listado"><%-- Tabla de resultados --%>
	<div class="layoutSombraTabla ancho">
	<div class="rellenoInf"></div>
	<div class="rellenoIzq"></div>
	<div class="rellenoDer"></div>
	<div class="supDer"></div>
	<div class="supIzq"></div>
	<div class="infIzq"></div>
	<div class="infDer"></div>


	<display:table name="llistatDepositos" requestURI="" id="diposit" export="true" sort="list" cellpadding="0" cellspacing="0" class="listadoAncho selectable noEnlace"  decorator="subtotaler" defaultsort="1">
		<display:column titleKey="consulta.oliDisponible.camp.tipus" headerClass="ancho75" group="1" sortable="true"><c:out value="${diposit[4].nom}" /></display:column>
		<display:column property="[1]" titleKey="consulta.oliDisponible.camp.contenedor" sortable="true" sortProperty="[1]" />
		<c:choose>
			<c:when test="${formData.mesura =='l'}">
				<display:column property="[2]" titleKey="consulta.oliDisponible.camp.quantitatOliDisponible" headerClass="ancho100" total="true" format="{0,number,#,##0.00} l." />
			</c:when>
			<c:when test="${formData.mesura =='kg'}">
				<display:column property="[3]" titleKey="consulta.oliDisponible.camp.quantitatOliDisponible" headerClass="ancho100" total="true" format="{0,number,#,##0.00} k." />
			</c:when>
		</c:choose>
		<display:column headerClass="ancho20 paddingCelda final" sortable="true">
			<a href="ConsultaTrazabilitatResumida.html?tipus=<c:out value="${trazaTipusOliDisponibleDiposit}"/>&amp;id=<c:out value="${diposit[0]}"/>"><img src="img/icons/trazabilidad.gif" border="0" title="<fmt:message key="consulta.trazabilitat.titol"/>" alt="<fmt:message key="consulta.trazabilitat.titol"/>" /></a>
		</display:column>
		<display:setProperty name="export.xml" value="false" />
   		<display:setProperty name="export.csv" value="false" />
   		<display:setProperty name="export.rtf" value="false" />
   		<display:setProperty name="export.pdf" value="false" />
   		<display:setProperty name="export.excel.include_header" value="true" />
   		<display:setProperty name="export.excel.filename" value="OliDisponibleGranel.xls" />
   		<display:setProperty name="export.decorated" value="true" />
	</display:table></div>
	</div>
	</div>
</c:if>
<br/>
<c:if test="${(llistatLotes != null && not empty llistatLotes)}">
	<div class="lotesEstrecho">
	<div class="lotes" style="width: 745px; margin: 30px 0pt 0pt 40px;">
		<h1 class="tituloListado"><fmt:message key="consulta.oliDisponible.titol.embotellat" /></h1>
	</div>
	<div class="listado"><%-- Tabla de resultados --%>
	<div class="layoutSombraTabla ancho">
	<div class="rellenoInf"></div>
	<div class="rellenoIzq"></div>
	<div class="rellenoDer"></div>
	<div class="supDer"></div>
	<div class="supIzq"></div>
	<div class="infIzq"></div>
	<div class="infDer"></div>

	<display:table name="llistatLotes" requestURI="" id="lot" export="true" sort="list" cellpadding="0" cellspacing="0" class="listadoAncho selectable noEnlace" decorator="subtotaler2" defaultsort="1">
		<display:column titleKey="consulta.oliDisponible.camp.tipus" headerClass="ancho75" group="1">
			<c:out value="${lot[4].nom}" /></display:column>
				<display:column property="[5]" titleKey="consulta.oliDisponible.camp.marca" sortable="true" sortProperty="[5]" />
		<display:column property="[6]" titleKey="consulta.oliDisponible.camp.etiquetatge" sortable="true" sortProperty="[6]"/>
		<display:column property="[9]" titleKey="lot.camp.producte" headerClass="ancho75" sortable="true"/>
		<display:column property="[1]" titleKey="consulta.oliDisponible.camp.lot" sortable="true" sortProperty="[1]"/>
		<display:column property="[7]" titleKey="consulta.oliDisponible.camp.botelles" sortable="true" sortProperty="[7]"/>
		<c:choose>
			<c:when test="${formData.mesura =='l'}">
				<display:column property="[2]" titleKey="consulta.oliDisponible.camp.quantitatOliDisponible" headerClass="ancho100" total="true" format="{0,number,#,##0.00} l." class="dreta" />
			</c:when>
			<c:when test="${formData.mesura =='kg'}">
				<display:column property="[3]" titleKey="consulta.oliDisponible.camp.quantitatOliDisponible" headerClass="ancho100" total="true" format="{0,number,#,##0.00} k." class="dreta"  />
			</c:when>
		</c:choose>
		<display:column headerClass="ancho20 paddingCelda final">
			<a href="ConsultaTrazabilitat.html?tipus=<c:out value="${trazaTipusOliDisponibleLote}"/>&amp;id=<c:out value="${lot[0]}"/>"><img src="img/icons/trazabilidad.gif" border="0" title="<fmt:message key="consulta.trazabilitat.titol"/>" alt="<fmt:message key="consulta.trazabilitat.titol"/>" /></a>
		</display:column>
		<display:setProperty name="export.xml" value="false" />
   		<display:setProperty name="export.csv" value="false" />
   		<display:setProperty name="export.rtf" value="false" />
   		<display:setProperty name="export.pdf" value="false" />
   		<display:setProperty name="export.excel.include_header" value="true" />
   		<display:setProperty name="export.excel.filename" value="OliDisponibleEmbotellat.xls" />
 	  	<display:setProperty name="export.decorated" value="true" />
	</display:table>
	</div>
	</div>
	</div>
</c:if>
<c:if test="${(llistatDepositos != null && not empty llistatDepositos) || (llistatLotes != null && not empty llistatLotes)}">
	<%-- Colores en tablas --%>
	<script type="text/javascript">
			$(document).ready(function(){
				setEstilosTabla();
			})
		</script>
</c:if>

</body>
</html>
