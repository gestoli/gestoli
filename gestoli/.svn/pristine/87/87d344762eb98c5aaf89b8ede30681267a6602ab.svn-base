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
<title><fmt:message key="consulta.olivaElaborada.titol" /></title>

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
<form id="formulario" action="ConsultaOlivaElaboradaLlistat.html"
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


	<display:table name="llistat" requestURI="" id="elaboracio" export="true" sort="list" cellpadding="0" cellspacing="0" class="listadoAncho selectable noEnlace">
		<display:column titleKey="consulta.oliElaborat.camp.data" headerClass="ancho75" sortable="true" sortProperty="data">
			<c:out value="${elaboracio.dataFormat}" />
		</display:column>
		<display:column titleKey="consulta.oliElaborat.camp.numeroElaboracion" headerClass="ancho20">
			<c:out value="${elaboracio.numeroElaboracio}" />
		</display:column>
		<display:column titleKey="consulta.oliElaborat.camp.kilos" headerClass="ancho20">
			<fmt:formatNumber value="${elaboracio.totalKilos}" /> Kg
		</display:column>
		<display:column titleKey="consulta.oliElaborat.camp.kilos.criba" headerClass="ancho20">
			<fmt:formatNumber value="${elaboracio.bota.kgOliva}" /> Kg
		</display:column>
		<display:column titleKey="consulta.oliElaborat.camp.varietatOliva" headerClass="ancho20">
			<c:if test="${elaboracio.bota.tipusOlivaTaula==0}">
				<fmt:message key="proces.entradaOlivaTaula.camp.tipus.verda" />
			</c:if>
			<c:if test="${elaboracio.bota.tipusOlivaTaula==1}">
				<fmt:message key="proces.entradaOlivaTaula.camp.tipus.trencada" />
			</c:if>
			<c:if test="${elaboracio.bota.tipusOlivaTaula==2}">
				<fmt:message key="proces.entradaOlivaTaula.camp.tipus.negra" />
			</c:if>
		</display:column>
		<display:column titleKey="consulta.oliElaborat.camp.numeroElaboracion" headerClass="ancho20">
			<c:out value="${elaboracio.bota.identificador}" />
		</display:column>
<%--		
		<display:column headerClass="ancho20 paddingCelda">
			<a 	href="ConsultaTrazabilitat.html?tipus=<c:out value="${trazaTipusOlivaElaborada}"/>&amp;id=<c:out value="${elaboracio.id}"/>">
				<img src="img/icons/trazabilidad.gif" border="0" title="<fmt:message key="consulta.trazabilitat.titol"/>" alt="<fmt:message key="consulta.trazabilitat.titol"/>" />
			</a>
		</display:column>
--%>
		<display:column headerClass="ancho20 paddingCelda final">
			<a 	href="ConsultaTrazabilitatResumida.html?tipus=<c:out value="${trazaTipusOlivaElaborada}"/>&amp;id=<c:out value="${elaboracio.id}"/>">
				<img src="img/icons/trazabilidadRes.gif" border="0" title="<fmt:message key="consulta.trazabilitat.resumida.titol"/>" alt="<fmt:message key="consulta.trazabilitat.resumida.titol"/>" />
			</a>
		</display:column>
		<display:setProperty name="export.xml" value="false" />
   		<display:setProperty name="export.csv" value="false" />
   		<display:setProperty name="export.rtf" value="false" />
   		<display:setProperty name="export.pdf" value="false" />
   		<display:setProperty name="export.excel.include_header" value="true" />
   		<display:setProperty name="export.excel.filename" value="OlivaElaborada.xls" />
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
