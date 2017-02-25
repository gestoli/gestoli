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
<title><fmt:message key="consulta.oliElaborat.titol" /></title>

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

<!--  
    <c:if test="${not empty llistat}">
    	<script type="text/javascript" src="js/displaytag.js"></script>    	
    </c:if>
   	 -->
</head>
<body>
<form id="formulario" action="ConsultaOliElaboratLlistat.html"
	method="post" class="extended seguit">
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

<c:if test="${llistat != null && empty llistat}">
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

<c:if test="${llistat != null && not empty llistat}">
	<div id="listado"><%-- Tabla de resultados --%>
	<div class="layoutSombraTabla ancho">
	<div class="rellenoInf"></div>
	<div class="rellenoIzq"></div>
	<div class="rellenoDer"></div>
	<div class="supDer"></div>
	<div class="supIzq"></div>
	<div class="infIzq"></div>
	<div class="infDer"></div>


	<display:table name="llistat" requestURI="" id="procesElaboracioOliCommand" export="true" sort="list" cellpadding="0" cellspacing="0" class="listadoAncho selectable noEnlace">
		<display:column titleKey="consulta.oliElaborat.camp.data" headerClass="ancho75" sortable="true" sortProperty="data">
			<c:out value="${procesElaboracioOliCommand.dataFormat}" />
		</display:column>
		<display:column titleKey="consulta.oliElaborat.camp.numeroElaboracion" headerClass="ancho20">
			<c:out value="${procesElaboracioOliCommand.numeroElaboracio}" />
		</display:column>
		<display:column titleKey="consulta.oliElaborat.camp.responsable">
			<c:out value="${procesElaboracioOliCommand.responsable}" escapeXml="false" />
		</display:column>
		<display:column titleKey="consulta.oliElaborat.camp.olivicultor"  headerClass="ancho100">
			<c:out value="${procesElaboracioOliCommand.olivicultors}" />
		</display:column>
		<display:column titleKey="consulta.oliElaborat.camp.varietatOliva" headerClass="ancho100">
			<c:if test="${procesElaboracioOliCommand.nomVarietat != 'mezcla'}">
				<c:out value="${procesElaboracioOliCommand.nomVarietat}"
					escapeXml="false" />
			</c:if>
			<c:if test="${procesElaboracioOliCommand.nomVarietat == 'mezcla'}">
				<fmt:message key="consulta.camp.mezcla" />
			</c:if>
		</display:column>
		<display:column property="totalKilos" format="{0,number,#0} kg." titleKey="consulta.oliElaborat.camp.kilos" headerClass="ancho75"></display:column>
<%-- 		<display:column titleKey="consulta.oliElaborat.camp.quantitatOliElavorat" headerClass="ancho75"> --%>
<%-- 			<fmt:formatNumber value="${procesElaboracioOliCommand.litres}" format="{0,number,#0}" /> --%>
<%-- 			<c:if test="${not empty procesElaboracioOliCommand.litres}"><c:out value="${formData.mesura}" />.</c:if> --%>
<%-- 		</display:column> --%>
		
		<display:column titleKey="consulta.oliElaborat.camp.quantitatOliElavorat" headerClass="ancho75">
			<c:out value="${procesElaboracioOliCommand.litres}" />
		</display:column>
		
		<display:column titleKey="consulta.oliElaborat.camp.acidesa" headerClass="ancho50">
			<c:out value="${procesElaboracioOliCommand.acidesa}" />
		</display:column>
		<display:column titleKey="consulta.oliElaborat.camp.categoriaOli" headerClass="ancho75" sortable="true">
			<fmt:message key="consulta.general.camp.categoriaOli.${procesElaboracioOliCommand.categoriaOli.id}" />
		</display:column>
		<c:choose>
			<c:when test="${formData.mesura =='l'}">
				<display:column titleKey="consulta.oliElaborat.camp.dipositsDesti" headerClass="ancho160">
					<c:forEach var="dip" items="${procesElaboracioOliCommand.diposits}" varStatus="status">
						<c:out value="${dip.codiAssignat}" escapeXml="false" /> (<c:out value="${procesElaboracioOliCommand.litros[status.index]}" escapeXml="false" /> l.)<br/>
					</c:forEach>
				</display:column>
			</c:when>
			<c:when test="${formData.mesura =='kg'}">
				<display:column titleKey="consulta.oliElaborat.camp.dipositsDesti" headerClass="ancho160">
					<c:forEach var="dip" items="${procesElaboracioOliCommand.diposits}" varStatus="status">
						<c:out value="${dip.codiAssignat}" escapeXml="false" /> (<c:out value="${procesElaboracioOliCommand.kilos[status.index]}" escapeXml="false" /> kg.)<br/>
					</c:forEach>
				</display:column>
			</c:when>
		</c:choose>
		
		<c:choose>
			<c:when test="${formData.mesura =='l'}">
				<display:column titleKey="consulta.oliElaborat.camp.totalElaboracioLitres" headerClass="ancho160">
					<c:set var="sumaelaboracio" value="0" />
					<c:forEach var="dip" items="${procesElaboracioOliCommand.diposits}" varStatus="status">
						<c:set var="sumaelaboracio" value="${sumaelaboracio + procesElaboracioOliCommand.litros[status.index]}" />
					</c:forEach>
					${sumalitres}
				</display:column>
			</c:when>
			<c:when test="${formData.mesura =='kg'}">
				<display:column titleKey="consulta.oliElaborat.camp.dipositsDestiKilos" headerClass="ancho160">
					<c:set var="sumaelaboracio" value="0" />
					<c:forEach var="dip" items="${procesElaboracioOliCommand.diposits}" varStatus="status">
						<c:set var="sumaelaboracio" value="${sumaelaboracio + procesElaboracioOliCommand.litros[status.index]}" />
					</c:forEach>
					${sumalitres}
				</display:column>
			</c:when>
		</c:choose>

		<display:column titleKey="consulta.oliElaborat.camp.cantidadRetiradaPropietario" headerClass="ancho75">
			<fmt:formatNumber value="${procesElaboracioOliCommand.totalLitresRetirats}" maxFractionDigits="2" />
			<c:if test="${not empty procesElaboracioOliCommand.totalLitresRetirats}"><c:out value="${formData.mesura}" />.</c:if>
		</display:column>
		<%--display:column headerClass="ancho20 paddingCelda">
			<a 	href="ConsultaTrazabilitat.html?tipus=<c:out value="${trazaTipusOliElaborat}"/>&amp;id=<c:out value="${procesElaboracioOliCommand.id}"/>"><img
				src="img/icons/trazabilidad.gif" border="0"
				title="<fmt:message key="consulta.trazabilitat.titol"/>"
				alt="<fmt:message key="consulta.trazabilitat.titol"/>" /></a>
		</display:column--%>
		<display:column headerClass="ancho20 paddingCelda final">
			<a 	href="ConsultaTrazabilitatResumida.html?tipus=<c:out value="${trazaTipusOliElaborat}"/>&amp;id=<c:out value="${procesElaboracioOliCommand.id}"/>"><img
				src="img/icons/trazabilidadRes.gif" border="0"
				title="<fmt:message key="consulta.trazabilitat.resumida.titol"/>"
				alt="<fmt:message key="consulta.trazabilitat.resumida.titol"/>" /></a>
		</display:column>
		<display:setProperty name="export.xml" value="false" />
   		<display:setProperty name="export.csv" value="false" />
   		<display:setProperty name="export.rtf" value="false" />
   		<display:setProperty name="export.pdf" value="false" />
   		<display:setProperty name="export.excel.include_header" value="true" />
   		<display:setProperty name="export.excel.filename" value="OliElaborat.xls" />
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

</body>
</html>
