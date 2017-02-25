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
<title><fmt:message key="consulta.etiquetatgeMarca.titol" /></title>

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
<form id="formulario" action="ConsultaEtiquetatgeMarcaLlistat.html" method="post" class="extended seguit">
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
			<c:param name="clase" value="campoForm165" />
		</c:import>

		<div class="separadorH"></div>

		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="select" />
			<c:param name="path" value="formData.idMarca" />
			<c:param name="required" value="required" />
			<c:param name="title">
				<fmt:message key="lot.camp.marca" />
			</c:param>
			<c:param name="camp" value="campo_marca" />
			<c:param name="name" value="idMarca" />
			<c:param name="selectItems" value="marcas" />
			<c:param name="selectItemsId" value="id" />
			<c:param name="selectItemsValue" value="nom" />
			<c:param name="selectSelectedValue" value="${formData.idMarca}" />
			<c:param name="clase" value="campoFormMediano conMargen" />
			<c:param name="onchange" value="canviMarca('${lang}')" />
		</c:import>
	
		<div class="separadorH"></div>

		<div class="botonesForm">
			<div id="guardarForm" class="btnCorto"
				onclick="submitForm('formulario')"
				onmouseover="underline('enlaceGuardarForm')"
				onmouseout="underline('enlaceGuardarForm')">
					<a id="enlaceGuardarForm" href="javascript:void(0);"><fmt:message key="manteniment.cercar" /></a>
			</div>
		</div>

		<c:if test="${listaEbotellat != null && empty listaEbotellat}">
			<div class="separadorH"></div>
			<div class="mensajeErrorConsulta">
				<c:import url="comu/MissatgesIErrors.jsp">
					<c:param name="listError"><fmt:message key="consulta.entradaOliva.noRegs"/></c:param>
				</c:import>
			</div>
		</c:if>
	</fieldset>
</form>

<div class="separadorH"></div>


<c:if test="${listaEmbotellat != null && not empty listaEmbotellat}">
    
	<div id="listado">
	<div class="lotes" style="width: 745px; margin: 30px 0pt 0pt 40px;">
	<h1><fmt:message key="consulta.etiquetatge.marca.titol.embotellat" /></h1></div>
	<div class="layoutSombraTabla ancho" style="margin-left: 30px;">
	<div class="rellenoInf"></div>
	<div class="rellenoIzq"></div>
	<div class="rellenoDer"></div>
	<div class="supDer"></div>
	<div class="supIzq"></div>
	<div class="infIzq"></div>
	<div class="infDer"></div>

		<div style="overflow-y: auto; width: 888px;">
			<display:table name="listaEmbotellat" requestURI="" id="embotellat" export ="true" sort="list" cellpadding="0" cellspacing="0" class="selectable noEnlace" style="width:100%;">
				<display:column titleKey="consulta.movimentsEntreEstabliments.camp.data" sortable="false" headerClass="alto80">
					<c:out value="${embotellat[0]}" />
				</display:column>
				<c:forEach var="eti" items="${etiquetatges}" varStatus="status">
					<c:set var="style" value="" />
					<c:if test="${status.index == 2}">
						<c:set var="style" value="background-color:#CCCCCC;" />
					</c:if>
					<display:column title="${eti.marca.nom} ${eti.observacions} - ${eti.tipusEnvas.volum} l." sortable="false" headerClass="ancho100  alto80">
						<c:out value="${embotellat[1][status.index]}" />
					</display:column>
				</c:forEach>
				<display:setProperty name="export.xml" value="false" />
  					<display:setProperty name="export.csv" value="false" />
 						<display:setProperty name="export.rtf" value="false" />
 						<display:setProperty name="export.pdf" value="false" />
  					<display:setProperty name="export.excel.include_header" value="true" />
 						<display:setProperty name="export.excel.filename" value="Embotellat.xls" />
 						<display:setProperty name="export.decorated" value="true" />
			</display:table>
		</div>

	</div>
	</div>
</c:if>

<div class="separadorH"></div>

<c:if test="${listaSortides != null && not empty listaSortides}">
    
	<div id="listado">
	<div class="lotes" style="width: 745px; margin: 30px 0pt 0pt 40px;">
	<h1><fmt:message key="consulta.etiquetatge.marca.titol.sortides" /></h1></div>
	<div class="layoutSombraTabla ancho" style="margin-left: 30px;">
	<div class="rellenoInf"></div>
	<div class="rellenoIzq"></div>
	<div class="rellenoDer"></div>
	<div class="supDer"></div>
	<div class="supIzq"></div>
	<div class="infIzq"></div>
	<div class="infDer"></div>
			
	
		<div style="overflow-y: auto; width: 888px;">
		
			<display:table name="listaSortides" requestURI="" id="sortida" export ="true" sort="list" cellpadding="0" cellspacing="0" class="selectable noEnlace" style="width:100%;">
				<display:column titleKey="consulta.movimentsEntreEstabliments.camp.data" sortable="false" headerClass="alto80">
					<c:out value="${sortida[0]}" />
				</display:column>
				<c:forEach var="eti" items="${etiquetatges}" varStatus="status">
					<c:set var="style" value="" />
					<c:if test="${status.index == 2}">
						<c:set var="style" value="background-color:#CCCCCC;" />
					</c:if>
					<display:column title="${eti.marca.nom} ${eti.observacions} - ${eti.tipusEnvas.volum} l." sortable="false" headerClass="ancho100  alto80">
						<c:out value="${sortida[1][status.index]}" />
					</display:column>
				</c:forEach>
				<display:setProperty name="export.xml" value="false" />
  					<display:setProperty name="export.csv" value="false" />
 						<display:setProperty name="export.rtf" value="false" />
 						<display:setProperty name="export.pdf" value="false" />
  					<display:setProperty name="export.excel.include_header" value="true" />
 						<display:setProperty name="export.excel.filename" value="Sortides.xls" />
 						<display:setProperty name="export.decorated" value="true" />
			</display:table>
		</div>
	</div>
	</div>
</c:if>

<script type="text/javascript">
	$(document).ready(function(){
		setEstilosTabla();
	})
</script>
</body>
</html>
	