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
<title><fmt:message key="consulta.analiticas.titol" /></title>

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

</head>
<body>
<form id="formulario" action="ConsultaOliElaboratLlistat.html"
	method="post" class="extended seguit">
	<fieldset>
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="hidden" />
			<c:param name="path" value="formData.idEstabliment" />
			<c:param name="camp" value="idEstabliment" />
		</c:import>
		
		<c:if test="${llistat != null && empty llistat}">
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

	<display:table name="llistat" requestURI="" id="analitica" export="true" sort="list" cellpadding="0" cellspacing="0" class="listadoAncho selectable noEnlace">
		<display:column property="dataFormat" titleKey="consulta.analitica.camp.data" url="/AnaliticaForm.html?consulta=t&establimentId=${formData.idEstabliment}" paramId="id" paramProperty="id" headerClass="ancho75" sortable="true" sortProperty="data"/>
		<display:column property="partidaOli.nom" titleKey="consulta.analitica.camp.partida" headerClass="ancho100" sortable="true"/>
		<display:column property="diposit.codiAssignat" titleKey="consulta.analitica.camp.diposit" headerClass="ancho100" sortable="true"/>
		<display:column property="analisiFisicoQuimicAcidesa" titleKey="consulta.analitica.camp.acidesa" headerClass="ancho100" sortable="true"/>
		<display:column property="analisiFisicoQuimicNomLaboratori" titleKey="consulta.analitica.camp.laboratori" headerClass="ancho100" sortable="true"/>
		<display:column property="varietatOli.nom" titleKey="consulta.analitica.camp.varietatOli" headerClass="ancho100" sortable="true"/>
		<display:column property="litresAnalitzats" titleKey="consulta.analitica.camp.litres" headerClass="ancho100" sortable="true"/>
		<display:setProperty name="export.xml" value="false" />
		<display:setProperty name="export.csv" value="false" />
		<display:setProperty name="export.rtf" value="false" />
		<display:setProperty name="export.pdf" value="false" />
		<display:setProperty name="export.excel.include_header" value="true" />
		<display:setProperty name="export.excel.filename" value="Analitiques.xls" />
		<display:setProperty name="export.decorated" value="true" />
	</display:table>
	</div>
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