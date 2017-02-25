<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>
<%@ page import="es.caib.gestoli.front.util.*"%>
<%@ page import="java.util.ResourceBundle"%>
<%@ page import="java.util.Locale"%>
<%
	String lang = Idioma.getLang(request);
	request.setAttribute("lang",lang);
%>

<html>
<head>
<title><fmt:message key="accio.tancamentLlibres.title" /></title>

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

<c:if test="${not empty llistatDepositos || not empty llistatLotes}">
	<script type="text/javascript" src="js/displaytag.js"></script>
</c:if>

</head>
<body>
<%--
	<form id="formulario" action="TancamentLlibresLlistat.html" method="post" class="extended seguit">	
		<fieldset>
	    	
			<c:if test="${llistatDepositos != null && empty llistatDepositos && llistatLotes != null && empty llistatLotes}">
				<div class="separadorH"></div>
				<div class="mensajeErrorConsulta">				
					<c:import url="comu/MissatgesIErrors.jsp">
						<c:param name="listError"><fmt:message key="accio.tancamentLlibres.noRegs"/></c:param>
					</c:import>
				</div>
			</c:if>
		</fieldset>
	</form>
--%>
<form name="formulario" action="TancamentLlibres.html" method="post" class="ancho888">
	<c:if test="${(llistatDepositos != null && not empty llistatDepositos)}">
		<div class="depositosEstrecho">
			<h1><fmt:message key="establiment.vista.listadoDepositos" /></h1>
			<div class="listado"><%-- Tabla de resultados --%>
				<div class="etiqueta <c:out value="${param.required}"/><c:if test="${not empty status.errorMessage}"> error</c:if>" style="position: relative; left: 105px;">
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="checkbox" />
						<c:param name="path" value="formData.totsDiposits" />
						<c:param name="title"><fmt:message key="accio.tancamentLlibres.camp.totsDiposits" /></c:param>
						<c:param name="camp" value="totsDiposits" />
					</c:import>
				</div>
				<div class="separadorH"></div>
				<div class="layoutSombraTabla ancho">
					<div class="rellenoInf"></div>
					<div class="rellenoIzq"></div>
					<div class="rellenoDer"></div>
					<div class="supDer"></div>
					<div class="supIzq"></div>
					<div class="infIzq"></div>
					<div class="infDer"></div>
	
					<display:table 	name="llistatDepositos" requestURI="" id="diposit" pagesize="" sort="list" cellpadding="0" cellspacing="0" class="listado665 selectable">
						<display:column titleKey="accio.tancamentLlibres.seleccionar" sortable="no" headerClass="ancho50">
							<div class="etiqueta <c:out value="${param.required}"/><c:if test="${not empty status.errorMessage}"> error</c:if>">
								<c:import url="comu/CampFormulari.jsp">
									<c:param name="tipus" value="checkbox" />
									<c:param name="path" value="formData.dipositsSeleccionats" />
									<c:param name="camp" value="dipositsSeleccionats_${diposit.id}" />
									<c:param name="value" value="${diposit.id}" />
								</c:import>
							</div>
						</display:column>
						<display:column titleKey="accio.tancamentLlibres.contenedor" property="codiAssignat" sortable="true" sortProperty="codiAssignat" headerClass="ancho100"/>
						<display:column titleKey="accio.tancamentLlibres.partidaOli" sortable="true" sortProperty="partidaOli" headerClass="ancho200">
							<c:if test="${not empty diposit.partidaOli}"><c:out value="${diposit.partidaOli.nom}"/></c:if>
						</display:column>
						<display:column titleKey="accio.tancamentLlibres.capacidad" sortable="true" sortProperty="capacitat" headerClass="ancho100">
							<fmt:formatNumber value="${diposit.capacitat}" maxFractionDigits="2" /> l.
						</display:column>
						<display:column titleKey="accio.tancamentLlibres.contingut" sortable="true" sortProperty="volumActual" headerClass="final ancho100">
							<c:if test="${not empty diposit.volumActual}">
								<fmt:formatNumber value="${diposit.volumActual}" maxFractionDigits="2" /> l.
							</c:if>
							<c:if test="${empty diposit.volumActual}">0 l.</c:if>
						</display:column>
					</display:table>
				</div>
			</div>
		</div>
	</c:if>
	<div class="separadorH"></div>
	<c:if test="${(llistatLotes != null && not empty llistatLotes)}">
		<div class="lotesEstrecho">
			<h1 class="tituloListado"><fmt:message key="establiment.vista.listadoLotes" /></h1>
			<div class="listado"><%-- Tabla de resultados --%>
				<div class="etiqueta <c:out value="${param.required}"/><c:if test="${not empty status.errorMessage}"> error</c:if>" style="position: relative; left: 105px;">
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="checkbox" />
						<c:param name="path" value="formData.totsLots" />
						<c:param name="title"><fmt:message key="accio.tancamentLlibres.camp.totsLots" /></c:param>
						<c:param name="camp" value="totsLots" />
					</c:import>
				</div>
				<div class="separadorH"></div>
				<div class="layoutSombraTabla ancho">
					<div class="rellenoInf"></div>
					<div class="rellenoIzq"></div>
					<div class="rellenoDer"></div>
					<div class="supDer"></div>
					<div class="supIzq"></div>
					<div class="infIzq"></div>
					<div class="infDer"></div>
		
					<display:table 	name="llistatLotes" requestURI="" id="lot" pagesize="" sort="list" cellpadding="0" cellspacing="0" class="listado665 selectable">
						<display:column titleKey="accio.tancamentLlibres.seleccionar" sortable="no" headerClass="ancho50">
							<div class="etiqueta <c:out value="${param.required}"/><c:if test="${not empty status.errorMessage}"> error</c:if>">
								<c:import url="comu/CampFormulari.jsp">
									<c:param name="tipus" value="checkbox" />
									<c:param name="path" value="formData.lotsSeleccionats" />
									<c:param name="camp" value="lotsSeleccionats_${lot.id}" />
									<c:param name="value" value="${lot.id}" />
								</c:import>
							</div>
						</display:column>
						<display:column titleKey="lot.camp.nomLot" property="codiAssignat" sortable="true" sortProperty="codiAssignat" headerClass="ancho100"/>
						<display:column titleKey="lot.camp.marca" property="etiquetatge.marca.nom" headerClass="ancho100" sortable="true"/>
						<display:column titleKey="lot.camp.etiquetatge" sortable="true">
							<c:out value="${lot.etiquetatge.tipusEnvas.volum} - ${lot.etiquetatge.tipusEnvas.color.nom} - ${lot.etiquetatge.tipusEnvas.materialTipusEnvas.nom}"/>
						</display:column>
						<display:column titleKey="lot.camp.partidaOli" sortable="true" sortProperty="partidaOli.nom" headerClass="final ancho100">
							<c:if test="${not empty lot.partidaOli}"><c:out value="${lot.partidaOli.nom}"/></c:if>
						</display:column>
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
	
	<div class="separadorH"></div>

	<div class="btnLargo156" onmouseover="underline('enlaceCrearForm')"
		onmouseout="underline('enlaceCrearForm')"
		onclick="document.formulario.submit();">
		<a id="enlaceCrearForm" href="javascript:void(0);">
			<fmt:message key="manteniment.aceptar" />
		</a>
	</div>
				
</form>
</body>
</html>
			