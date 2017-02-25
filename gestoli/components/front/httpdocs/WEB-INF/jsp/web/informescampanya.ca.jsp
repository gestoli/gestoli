<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ page import="es.caib.gestoli.front.util.*"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>
<%@ page import="java.util.ResourceBundle"%>
<%@ page import="java.util.Locale"%>
<%@ page import="java.util.List"%>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es" lang="es">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />

<title>Gest-OLI - Informes Campanyes</title>

<script type="text/javascript" src="../js/web/onload.js"></script>
<script type="text/javascript" src="../js/web/form.js"></script>
<link rel="stylesheet" type="text/css" href="../css/web/tablas.css" />
<link rel="stylesheet" type="text/css" href="../css/general.css" />
<script type="text/javascript" src="../js/displaytag.js"></script>

</head>

<body>

<form id="formulario" name="informescampanya" action="informescampanya.ca.html" method="post" class="extended seguit ancho888" onsubmit="">
<div id="cercaIdMicroweb">
	<div class="cercaId">

		<h2 class="microweb">Informes Producció</h2>

		<div class="separadorH"></div>
		 
		<c:choose>
		<c:when test="${informesProduccio != null && not empty informesProduccio}">
			<div id="listado"><%-- Tabla de resultados --%>
				<div class="layoutSombraTabla ancho">
					<div class="rellenoInf"></div>
					<div class="rellenoIzq"></div>
					<div class="rellenoDer"></div>
					<div class="supDer"></div>
					<div class="supIzq"></div>
					<div class="infIzq"></div>
					<div class="infDer"></div>
					<display:table name="informesProduccio" requestURI="" id="registre" sort="list" cellpadding="0" cellspacing="0" class="listadoEstrecho ancho600">
						<display:column property="campanya" title="Campanya" headerClass="ancho150" sortable="false" />
						<display:column sortable="false" title="Document" headerClass="ancho32 final">
							<a href="../web/ArxiuMostrarWeb.html?id=<c:out value="${registre.document}"/>&download=true">
								<div class="exportPDFWidth16" title="Document"></div>
							</a>
						</display:column>
					</display:table>
			
					<%-- Colores en tablas --%>
					<script type="text/javascript">
						$(document).ready(function(){
							setEstilosTabla();
						})
					</script>
				</div>
			</div>
		</c:when>
		<c:otherwise>
			En aquests moments no hi ha cap informe de Producció disponible
		</c:otherwise>
		</c:choose>
		
		<br /><br /><br />
		
		<h2 class="microweb">Informes Comercialització</h2>

		<div class="separadorH"></div>
		<c:choose>
		<c:when test="${informesComercialitzacio != null && not empty informesComercialitzacio}">
			<div id="listado"><%-- Tabla de resultados --%>
			<div class="layoutSombraTabla ancho">
				<c:if test="${informesComercialitzacio != null && not empty informesComercialitzacio}">
					<div class="rellenoInf"></div>
					<div class="rellenoIzq"></div>
					<div class="rellenoDer"></div>
					<div class="supDer"></div>
					<div class="supIzq"></div>
					<div class="infIzq"></div>
					<div class="infDer"></div>
					<display:table name="informesComercialitzacio" requestURI="" id="registre" sort="list" cellpadding="0" cellspacing="0" class="listadoEstrecho ancho600">
						<display:column property="any" title="Any" headerClass="ancho150" sortable="false" />
						<display:column sortable="false" title="Document" headerClass="ancho32 final">
							<a href="../web/ArxiuMostrarWeb.html?id=<c:out value="${registre.document}"/>&download=true">
								<div class="exportPDFWidth16" title="Document"></div>
							</a>
						</display:column>
					</display:table>
			
					<%-- Colores en tablas --%>
					<script type="text/javascript">
						$(document).ready(function(){
							setEstilosTabla();
						})
					</script>
				</c:if>
			</div>
			</div>
		</c:when>
		<c:otherwise>
			En aquests moments no hi ha cap informe de Comercialització disponible
		</c:otherwise>
		</c:choose>

	</div>

</div>
</form>
	

</body>
</html>