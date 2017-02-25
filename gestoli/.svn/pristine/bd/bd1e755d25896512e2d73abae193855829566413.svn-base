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
<title><fmt:message key="consulta.trasabilitatOlivicultor.titol" /></title>

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
<form id="formulario" action="ConsultaTrasabilitatOlivicultorLlistat.html"
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


	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="select" />
		<c:param name="path" value="formData.idOlivicultor" />
		<c:param name="required" value="required" />
		<c:param name="title">
			<fmt:message key="proces.olivicultors" />
		</c:param>
		<c:param name="camp" value="idOlivicultor" />
		<c:param name="name" value="idOlivicultor" />
		<c:param name="selectItems" value="olivicultors" />
		<c:param name="selectItemsId" value="id" />
		<c:param name="selectItemsValue" value="nom" />
		<c:param name="selectSelectedValue" value="${formData.idOlivicultor}" />
		<c:param name="clase" value="campoFormMediano conMargen" />
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
	</c:if>

</fieldset>
</form>

<div class="separadorH"></div>

<c:if test="${llistat != null && not empty llistat}">
    
<div id="listado">
	<div class="lotes" style="width: 745px;"><h1><%--fmt:message key="consulta.oliEmbotellat.titol.llistat1" /--%></h1></div>
	<div class="layoutSombraTabla ancho">
		<div class="rellenoInf"></div>
		<div class="rellenoIzq"></div>
		<div class="rellenoDer"></div>
		<div class="supDer"></div>
		<div class="supIzq"></div>
		<div class="infIzq"></div>
		<div class="infDer"></div>
			<display:table name="llistat" requestURI="" id="obj" export="true" sort="list" cellpadding="0" cellspacing="0" class="selectable noEnlace" >
				<display:column property="olivicultor.nom" titleKey="consulta.trasabilitatOlivicultor.camp.olivicultor" sortable="true" headerClass="ancho100" group="1" />
				<display:column property="partidaOliva.codiAssignat" titleKey="consulta.trasabilitatOlivicultor.camp.codiPartidaOliva" sortable="true" headerClass="ancho100" group="2" />
				<display:column property="partidaOliva.varietatsQuilos" titleKey="consulta.trasabilitatOlivicultor.camp.quantitatPartidaOliva" sortable="true" headerClass="ancho100"  group="3"/>
				<display:column property="data" titleKey="consulta.trasabilitatOlivicultor.camp.data" sortable="true" headerClass="ancho75" format="{0,date,dd/MM/yyyy}" />
				<display:column titleKey="consulta.trasabilitatOlivicultor.camp.accio" sortable="true" headerClass="ancho100" >
					<c:choose>
						<c:when test="${obj.accio == 1}"><fmt:message key="consulta.trasabilitatOlivicultor.camp.accio.partida" /></c:when>
						<c:when test="${obj.accio == 2}"><fmt:message key="consulta.trasabilitatOlivicultor.camp.accio.elaboracio" /></c:when>
						<c:when test="${obj.accio == 3}"><fmt:message key="consulta.trasabilitatOlivicultor.camp.accio.sortidaLot" /></c:when>
						<c:when test="${obj.accio == 4}"><fmt:message key="consulta.trasabilitatOlivicultor.camp.accio.entradaDiposit" /></c:when>
						<c:when test="${obj.accio == 5}"><fmt:message key="consulta.trasabilitatOlivicultor.camp.accio.perdua" /></c:when>
						<c:when test="${obj.accio == 6}"><fmt:message key="consulta.trasabilitatOlivicultor.camp.accio.embotellat" /></c:when>
						<c:when test="${obj.accio == 7}"><fmt:message key="consulta.trasabilitatOlivicultor.camp.accio.sortidaGranel" /></c:when>
						<c:when test="${obj.accio == 8}"><fmt:message key="consulta.trasabilitatOlivicultor.camp.accio.trasllat" /></c:when>
						<c:when test="${obj.accio == 9}"><fmt:message key="consulta.trasabilitatOlivicultor.camp.accio.tancament" /></c:when>
						<c:when test="${obj.accio == 10}"><fmt:message key="consulta.trasabilitatOlivicultor.camp.accio.desqualificat" /></c:when>
						<c:when test="${obj.accio == 11}"><fmt:message key="consulta.trasabilitatOlivicultor.camp.accio.trasbals" /></c:when>
						<c:when test="${obj.accio == 12}"><fmt:message key="consulta.trasabilitatOlivicultor.camp.accio.analitica" /></c:when>
					</c:choose>
				</display:column>
				<display:column property="elaboracio.partidaOli.nom" titleKey="consulta.trasabilitatOlivicultor.camp.nomPartidaOli" sortable="true" headerClass="ancho100" />
				<display:column property="dipositOrigen.codiAssignat" titleKey="consulta.trasabilitatOlivicultor.camp.dipOrigen" sortable="true" headerClass="ancho100" />
				<display:column titleKey="consulta.trasabilitatOlivicultor.camp.dipDesti" sortable="true" headerClass="ancho100">
					<c:choose>
						<c:when test="${empty obj.dipositDesti && not empty obj.sortidaDiposit}">
							<c:out value="${obj.sortidaDiposit.observacions}"/>
						</c:when>
						<c:otherwise>
							<c:out value="${obj.dipositDesti.codiAssignat}"/>
						</c:otherwise>
					</c:choose>
				</display:column>
				<display:column property="lot.codiAssignat" titleKey="consulta.trasabilitatOlivicultor.camp.lot" sortable="true" headerClass="ancho100" />
				<display:column property="quantitat" titleKey="consulta.trasabilitatOlivicultor.camp.quantitat" sortable="true" headerClass="ancho75 final" />
				<display:setProperty name="export.xml" value="false" />
				<display:setProperty name="export.csv" value="false" />
				<display:setProperty name="export.rtf" value="false" />
				<display:setProperty name="export.pdf" value="false" />
				<display:setProperty name="export.excel.include_header" value="true" />
				<display:setProperty name="export.excel.filename" value="tracabilitatOlivicultor.xls" />
				<display:setProperty name="export.decorated" value="true" />
			</display:table>
	</div>
</div>


	<script type="text/javascript">
			$(document).ready(function(){
				setEstilosTabla();
			})
		</script>
</c:if>


</body>
</html>
