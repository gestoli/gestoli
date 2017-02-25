<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>
<%@ page buffer="64kb"%>
<%@ page autoFlush="true"%>

<html>
<head>
<title><fmt:message key="consulta.trazabilitat" /></title>
<script type="text/javascript" src="js/calendar/calendar.js"></script>
<script type="text/javascript" src="js/calendar/calendar-setup.js"></script>
<script type="text/javascript" src="js/calendar/lang/calendar-es.js"></script>
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

<form id="formulario"
	action="<%=request.getContextPath()%>/AltresConsultesTrazabilidadPartida.html"
	method="post" class="extended seguit"><input type="hidden"
	id="idEst" name="idEst" value="<c:out value="${idEstabliment}"/>" /> <c:import
	url="comu/CampFormulari.jsp">
	<c:param name="tipus" value="select" />
	<c:param name="path" value="formData.idEstabliment" />
	<c:param name="title">
		<fmt:message key="consulta.partidaOliva.camp.establiment" />
	</c:param>
	<c:param name="camp" value="idEstabliment" />
	<c:param name="required" value="required" />
	<c:param name="selectItems" value="establecimientos" />
	<c:param name="selectItemsId" value="id" />
	<c:param name="selectItemsValue" value="nom" />
	<c:param name="selectSelectedValue" value="${formData.idEstabliment}" />
	<c:param name="clase" value="campoFormTresCuartos" />
</c:import>

<div class="separadorH"></div>

<div class="botonesForm">
<div id="aceptarForm" class="btnCorto"
	onclick="submitForm('formulario');"
	onmouseover="underline('enlaceAceptarForm')"
	onmouseout="underline('enlaceAceptarForm')"><a
	id="enlaceAceptarForm" href="javascript:void(0);"><fmt:message
	key="manteniment.aceptar" /></a></div>
</div>

<c:if test="${empty llistat}">
	<div class="separadorH"></div>
	<div class="mensajeErrorConsulta"><c:import
		url="comu/MissatgesIErrors.jsp">
		<c:param name="listError">
			<fmt:message key="consulta.entradaOliva.noRegs" />
		</c:param>
	</c:import></div>
</c:if></form>

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
	<display:table name="llistat" requestURI="" id="diposit" export="true"
		sort="list" cellpadding="0" cellspacing="0"
		class="listadoEstrecho selectable ">
		<display:column titleKey="consulta.oliDisponible.camp.contenedor"
			sortable="true" sortProperty="codiAssignat">
			<a href="ConsultaTrazabilitatResumida.html?tipus=2&id=${diposit.id}">
				<c:out value="${diposit.codiAssignat}" />
			</a>
		</display:column>
		<c:choose>
			<c:when test="${diposit.establiment.unitatMesura =='l'}">
				<display:column
					titleKey="consulta.oliDisponible.camp.quantitatOliDisponible"
					headerClass="ancho100">
					<a href="ConsultaTrazabilitatResumida.html?tipus=2&id=${diposit.id}">
						<fmt:formatNumber value="${diposit.volumActual}"
							maxFractionDigits="2" />
						<c:if test="${not empty diposit.volumActual}"> l.</c:if>
					</a>
				</display:column>
			</c:when>
			<c:when test="${diposit.establiment.unitatMesura =='k'}">
				<display:column
					titleKey="consulta.oliDisponible.camp.quantitatOliDisponible"
					headerClass="ancho100">
					<a href="ConsultaTrazabilitatResumida.html?tipus=2&id=${diposit.id}">
						<fmt:formatNumber value="${diposit.disponibleOliQuilos}"
							maxFractionDigits="2" />
						<c:if test="${not empty diposit.disponibleOliQuilos}"> kg.</c:if>
					</a>
				</display:column>
			</c:when>
		</c:choose>
		<display:column headerClass="ancho20 paddingCelda final">
			<%--a href="AltresConsultesTrazabilidadPartidaInforme.html?id=<c:out value="${diposit.id}"/>"--%>
			<a href="ConsultaTrazabilitatResumida.html?tipus=2&id=<c:out value="${diposit.id}"/>">
			<img src="img/icons/trazabilidadRes.gif" border="0"
				title="<fmt:message key="consulta.trazabilitat.resumida.titol"/>"
				alt="<fmt:message key="consulta.trazabilitat.titol"/>" 
			/>
			</a>
		</display:column>
		<display:setProperty name="export.xml" value="false" />
		<display:setProperty name="export.csv" value="false" />
		<display:setProperty name="export.rtf" value="false" />
		<display:setProperty name="export.pdf" value="false" />
		<display:setProperty name="export.excel.include_header" value="true" />
		<display:setProperty name="export.excel.filename" value="llistatDiposits.xls" />
		<display:setProperty name="export.decorated" value="true" />
	</display:table></div>
	</div>

	<%-- Colores en tablas --%>
	<script type="text/javascript">
			$(document).ready(function(){
				setEstilosTabla();
			})
		</script>
<br /><br /><br />
</c:if>


<c:if test="${llistatLot != null && not empty llistatLot}">
	<div id="listado"><%-- Tabla de resultados --%>
	<div class="layoutSombraTabla ancho">
	<div class="rellenoInf"></div>
	<div class="rellenoIzq"></div>
	<div class="rellenoDer"></div>
	<div class="supDer"></div>
	<div class="supIzq"></div>
	<div class="infIzq"></div>
	<div class="infDer"></div>
	<display:table name="llistatLot" requestURI="" id="lot" export="true"
		sort="list" cellpadding="0" cellspacing="0"
		class="listadoEstrecho selectable ">
		<display:column titleKey="consulta.oliDisponible.camp.lot"
			sortable="true" sortProperty="codiAssignat">
			<a href="ConsultaTrazabilitatResumida.html?tipus=3&id=${lot.id}">
				<c:out value="${lot.codiAssignat}" />
			</a>
		</display:column>
		<display:column titleKey="consulta.oliEmbotellat.camp.marca"
			sortable="true" sortProperty="etiquetatge.marca.nom">
			<a href="ConsultaTrazabilitatResumida.html?tipus=3&id=${lot.id}">
				<c:out value="${lot.etiquetatge.marca.nom}" />
			</a>
		</display:column>
		<display:column titleKey="consulta.oliEmbotellat.camp.envas"
			sortable="true" sortProperty="etiquetatge.tipusEnvas.nomSelect">
			<a href="ConsultaTrazabilitatResumida.html?tipus=3&id=${lot.id}">
				<c:out value="${lot.etiquetatge.tipusEnvas.nomSelect}" />
			</a>
		</display:column>
		<display:column	titleKey="consulta.oliDisponible.camp.botelles"	headerClass="ancho100">
			<a href="ConsultaTrazabilitatResumida.html?tipus=3&id=${lot.id}">
				<c:out value="${lot.numeroBotellesActuals}" />
			</a>
		</display:column>
		<display:column headerClass="ancho20 paddingCelda final">
			<a
				href="ConsultaTrazabilitatResumida.html?tipus=3&id=<c:out value="${lot.id}"/>"><img
				src="img/icons/trazabilidad.gif" border="0"
				title="<fmt:message key="consulta.trazabilitat.titol"/>"
				alt="<fmt:message key="consulta.trazabilitat.titol"/>" /></a>
		</display:column>
		<display:setProperty name="export.xml" value="false" />
		<display:setProperty name="export.csv" value="false" />
		<display:setProperty name="export.rtf" value="false" />
		<display:setProperty name="export.pdf" value="false" />
		<display:setProperty name="export.excel.include_header" value="true" />
		<display:setProperty name="export.excel.filename" value="llistatLots.xls" />
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