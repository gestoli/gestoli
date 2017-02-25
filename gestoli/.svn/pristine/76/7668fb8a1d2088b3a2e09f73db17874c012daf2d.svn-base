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
<title><fmt:message key="menu.consultas.aceite_embotellado" /></title>

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
<link href="js/calendar/calendar-viti.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/form.js"></script>

<!--  
    <c:if test="${not empty llistat}">
    	<script type="text/javascript" src="js/displaytag.js"></script>    	
    </c:if>
   	 -->
</head>
<body>
<form id="formulario" action="ConsultaOliEmbotellatLlistat.html" method="post" class="extended seguit">
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
		<c:param name="clase" value="conMargen campoForm165" />
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

<div class="botonesForm">
<div id="guardarForm" class="btnCorto"
	onclick="submitForm('formulario')"
	onmouseover="underline('enlaceGuardarForm')"
	onmouseout="underline('enlaceGuardarForm')"><a
	id="enlaceGuardarForm" href="javascript:void(0);"><fmt:message
	key="manteniment.cercar" /></a></div>
</div>

<c:if test="${llista != null && empty llista && llistat2 != null && empty llistat2}">
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

<c:if test="${llista != null && not empty llista}">
	<div id="listado"><%-- Tabla de resultados --%>
	<div class="lotes" style="width: 745px;"><h1><fmt:message key="consulta.oliEmbotellat.titol.llistat" /></h1></div>
	<div class="layoutSombraTabla ancho">
	<div class="rellenoInf"></div>
	<div class="rellenoIzq"></div>
	<div class="rellenoDer"></div>
	<div class="supDer"></div>
	<div class="supIzq"></div>
	<div class="infIzq"></div>
	<div class="infDer"></div>


	<jsp:scriptlet>
       org.displaytag.decorator.MultilevelTotalTableDecorator subtotals = new org.displaytag.decorator.MultilevelTotalTableDecorator();
       subtotals.setGrandTotalDescription("Total");    // optional, defaults to Grand Total
       subtotals.setSubtotalLabel("{0} Subtotal", request.getLocale());      // optional, defaults to "{0} Total"
       pageContext.setAttribute("subtotaler", subtotals);
    </jsp:scriptlet>
	<display:table name="llista" requestURI="" id="embotellatOli" export="true" sort="list" cellpadding="0" cellspacing="0" class="listadoAncho selectable noEnlace" decorator="subtotaler" defaultsort="1">
		<display:column property="partidaOli.categoriaOli.nom" titleKey="consulta.oliComercialitzat.camp.categoria" headerClass="ancho75" sortable="true" group="1"/>
		<display:column property="dataFormat" titleKey="consulta.oliEmbotellat.camp.data" headerClass="ancho75" sortable="true"/>
		<display:column property="codiAssignat" titleKey="consulta.oliEmbotellat.camp.numeroLot" headerClass="ancho20"/>
		<display:column property="dataConsumFormat" titleKey="consulta.oliEmbotellat.camp.dataConsum" headerClass="ancho75" sortable="true"/>
		<display:column property="partidaOli.nom" titleKey="consulta.oliEmbotellat.camp.partidaOli" headerClass="ancho75"/>
		<display:column property="nomProducte" titleKey="lot.camp.producte" headerClass="ancho20"/>
		<c:choose>
			<c:when test="${formData.mesura =='l'}">
				<display:column property="volum" titleKey="consulta.oliEmbotellat.camp.quantitat" headerClass="ancho75 final" total="true" format="{0,number,#,##0.00} l."/>
			</c:when>
			<c:when test="${formData.mesura =='kg'}">
				<display:column property="volum" titleKey="consulta.oliEmbotellat.camp.quantitat" headerClass="ancho75 final" total="true" format="{0,number,#,##0.00} k."/>
			</c:when>
		</c:choose>
	<display:setProperty name="export.xml" value="false" />
   	<display:setProperty name="export.csv" value="false" />
   	<display:setProperty name="export.rtf" value="false" />
   	<display:setProperty name="export.pdf" value="false" />
   	<display:setProperty name="export.excel.include_header" value="true" />
   	<display:setProperty name="export.excel.filename" value="Oli_Embotellat.xls" />
   	<display:setProperty name="export.decorated" value="true" />
	</display:table></div>
	</div>

	<%-- Colores en tablas --%>
	<script type="text/javascript">
			$(document).ready(function(){
				setEstilosTabla();
			})
		</script>
</c:if>

<%-- 
<div class="separadorH"></div>

<c:if test="${llistat != null && not empty llistat}">
	<div id="listado" style="padding: 60px 0 0 0;">
	<div class="lotes" style="width: 745px;"><h1><fmt:message key="consulta.oliEmbotellat.titol.llistat1" /></h1></div>
	<div class="layoutSombraTabla ancho">
	<div class="rellenoInf"></div>
	<div class="rellenoIzq"></div>
	<div class="rellenoDer"></div>
	<div class="supDer"></div>
	<div class="supIzq"></div>
	<div class="infIzq"></div>
	<div class="infDer"></div>


	<display:table name="llistat" requestURI="" id="procesEmbotellatOliCommand" export="true" sort="list" cellpadding="0" cellspacing="0" class="listadoAncho selectable noEnlace">
		<display:column titleKey="consulta.oliEmbotellat.camp.dataConsum" headerClass="ancho75" sortable="true" sortProperty="dataConsum">
			<c:out value="${procesEmbotellatOliCommand.dataConsumFormat}" />
		</display:column>
		<display:column titleKey="consulta.oliEmbotellat.camp.partidaOli" headerClass="ancho20">
			<c:out value="${procesEmbotellatOliCommand.partidaOli.nom}" />
		</display:column>
		<display:column titleKey="consulta.oliEmbotellat.camp.numeroLot" headerClass="ancho20">
			<c:out value="${procesEmbotellatOliCommand.codiAssignat}" />
		</display:column>
		<display:column titleKey="consulta.oliEmbotellat.camp.data" headerClass="ancho75" sortable="true" sortProperty="data">
			<c:out value="${procesEmbotellatOliCommand.dataFormat}" />
		</display:column>
		
		<c:choose>
			<c:when test="${formData.mesura =='l'}">
				<display:column titleKey="consulta.oliEmbotellat.camp.quantitat" headerClass="ancho75">
					<fmt:formatNumber type="number" maxFractionDigits="2" value="${procesEmbotellatOliCommand.numeroBotellesActuals * procesEmbotellatOliCommand.etiquetatge.tipusEnvas.volum}" /> l.
				</display:column>
			</c:when>
			<c:when test="${formData.mesura =='kg'}">
				<display:column titleKey="consulta.oliEmbotellat.camp.quantitat" headerClass="ancho75">
					<fmt:formatNumber type="number" maxFractionDigits="2" value="${procesEmbotellatOliCommand.numeroBotellesActuals * procesEmbotellatOliCommand.etiquetatge.tipusEnvas.volum * 0.916}" /> kg.
				</display:column>
			</c:when>
		</c:choose>
	<display:setProperty name="export.xml" value="false" />
   	<display:setProperty name="export.csv" value="false" />
   	<display:setProperty name="export.rtf" value="false" />
   	<display:setProperty name="export.pdf" value="false" />
   	<display:setProperty name="export.excel.include_header" value="true" />
   	<display:setProperty name="export.excel.filename" value="Oli_Embotellat_Existencies.xls" />
   	<display:setProperty name="export.decorated" value="true" />
	</display:table></div>
	</div>

	<script type="text/javascript">
			$(document).ready(function(){
				setEstilosTabla();
			})
		</script>
</c:if>
--%>
<div class="separadorH"></div>

<c:if test="${llistat2 != null && not empty llistat2}">
	<div id="listado" style="padding: 60px 0 0 0;"><%-- Tabla de resultados --%>
	<div class="lotes" style="width: 745px;"><h1><fmt:message key="consulta.oliEmbotellat.titol.llistat2" /></h1></div>
	<div class="layoutSombraTabla ancho">
	<div class="rellenoInf"></div>
	<div class="rellenoIzq"></div>
	<div class="rellenoDer"></div>
	<div class="supDer"></div>
	<div class="supIzq"></div>
	<div class="infIzq"></div>
	<div class="infDer"></div>

	<jsp:scriptlet>
       org.displaytag.decorator.MultilevelTotalTableDecorator subtotals2 = new org.displaytag.decorator.MultilevelTotalTableDecorator();
       subtotals2.setGrandTotalDescription("Total");    // optional, defaults to Grand Total
       subtotals2.setSubtotalLabel("{0} Subtotal", request.getLocale());      // optional, defaults to "{0} Total"
       pageContext.setAttribute("subtotaler2", subtotals2);
    </jsp:scriptlet>
	<display:table name="llistat2" requestURI="" id="procesEmbotellatOliCommand2" export="true" sort="list" defaultsort="1" cellpadding="0" cellspacing="0" class="listadoAncho selectable noEnlace" decorator="subtotaler2">
		<display:column titleKey="consulta.oliEmbotellat.camp.numeroLot" headerClass="ancho20" group="1" sortable="true">
			<c:out value="${procesEmbotellatOliCommand2.codiAssignat}" />
		</display:column>
		<display:column titleKey="consulta.oliEmbotellat.camp.dataConsum" headerClass="ancho75" sortable="true">
			<c:out value="${procesEmbotellatOliCommand2.dataConsumFormat}" />
		</display:column>
		<display:column property="partidaOli.categoriaOli.nom" titleKey="consulta.oliComercialitzat.camp.categoria" headerClass="ancho75" sortable="true"/>
		<display:column titleKey="consulta.oliEmbotellat.camp.partidaOli" headerClass="ancho75">
			<c:out value="${procesEmbotellatOliCommand2.partidaOli.nom}" />
		</display:column>
		<display:column property="nomProducte" titleKey="lot.camp.producte" headerClass="ancho75"/>
		<display:column titleKey="consulta.oliEmbotellat.camp.data" headerClass="ancho75" sortable="true" sortProperty="data">
			<c:out value="${procesEmbotellatOliCommand2.dataFormat}" />
		</display:column>
		
		<display:column titleKey="consulta.oliEmbotellat.camp.marca" headerClass="ancho75" sortable="true" sortProperty="data">
			<c:out value="${procesEmbotellatOliCommand2.etiquetatge.marca.nom}" />
		</display:column>
		<display:column titleKey="consulta.oliEmbotellat.camp.envas" headerClass="ancho75" sortable="true" sortProperty="data">
			<c:out value="${procesEmbotellatOliCommand2.etiquetatge.tipusEnvas.nomSelect}" />
		</display:column>
		<display:column titleKey="consulta.oliEmbotellat.camp.sortida" headerClass="ancho160" sortable="true" sortProperty="data">
			<c:forEach items="${procesEmbotellatOliCommand2.sortidaLots}" var="sortida">
				<c:if test="${sortida.vendaData ge formData.dataInici && sortida.vendaData le formData.dataFi && sortida.valid == true}">
				<c:choose>
					<c:when test="${formData.mesura =='l'}">
						<c:set var="quant" value="${procesEmbotellatOliCommand2.etiquetatge.tipusEnvas.volum * sortida.vendaNumeroBotelles}"/>
						<fmt:formatNumber type="number" maxFractionDigits="2" value="${quant}" /> l. - ${sortida.vendaDestinatari}<br/>
					</c:when>
					<c:when test="${formData.mesura =='kg'}">
						<c:set var="quant" value="${procesEmbotellatOliCommand2.etiquetatge.tipusEnvas.volum * sortida.vendaNumeroBotelles * 0.916}"/>
						<fmt:formatNumber type="number" maxFractionDigits="2" value="${quant}" /> kg. - ${sortida.vendaDestinatari}<br/>
					</c:when>
				</c:choose>
				</c:if>
			</c:forEach>
		</display:column>
		<display:column property="volum" titleKey="consulta.oliEmbotellat.camp.sortida.total" headerClass="ancho75 final" sortable="true" sortProperty="data" total="true" format="{0,number,#,##0.00}"/>
		<display:setProperty name="export.xml" value="false" />
   		<display:setProperty name="export.csv" value="false" />
   		<display:setProperty name="export.rtf" value="false" />
   		<display:setProperty name="export.pdf" value="false" />
   		<display:setProperty name="export.excel.include_header" value="true" />
   		<display:setProperty name="export.excel.filename" value="Oli_Embotellat_Sortides.xls" />
   		<display:setProperty name="export.decorated" value="true" />
	</display:table></div>
	</div>
</c:if>

<c:if test="${(llistat != null && not empty llistat) || (llistat2 != null && not empty llistat2)}">
	<%-- Colores en tablas --%>
	<script type="text/javascript">
			$(document).ready(function(){
				setEstilosTabla();
			})
		</script>
</c:if>

</body>
</html>