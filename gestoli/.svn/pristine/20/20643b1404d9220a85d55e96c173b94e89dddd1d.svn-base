<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="decorator" uri="/WEB-INF/sitemesh-decorator.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>


<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><decorator:title /></title>

<link rel="stylesheet" type="text/css" href="../css/web/general.css" media="screen" />
<link rel="stylesheet" type="text/css" href="../css/web/menu.css" media="screen" />

<script type="text/javascript" src="../js/web/menu.js"></script>
<script type="text/javascript" src="../js/web/jquery.js"></script>
<script type="text/javascript" src="../js/web/sombras.js"></script>
<script type="text/javascript" src="../js/web/funciones.js"></script>

<link rel="Shortcut Icon" href="../gesticon.ico" />

<decorator:head />
</head>



<body>

<script type="text/javascript">
	function cambioIdioma(idiomaNuevo) {		
		
		/*
		// Modificamos el parametro lang en la Cookie
		cookieName = "org.springframework.web.servlet.i18n.CookieLocaleResolver.LOCALE";		
		cookiePath = "/";
		cookieExpires = new Date("January 1, 3000");
		setCookie(cookieName, idiomaNuevo, cookiePath, cookieExpires);		
		*/
		
		// Modificamos la url, sustituyendo el idioma actual por el nuevo
		path = window.location.pathname;
		
		urlParam = path.split("?");
		
		urlArray = urlParam[0].split(".");
		url = urlArray[0] + "." + idiomaNuevo + "." + urlArray[2] + "?idioma=" + idiomaNuevo;
		
		window.location = url;
		
		/*form = document.getElementById('formCambioIdioma');
		form.action = path;
		form.lang.value = idiomaNuevo;
		form.siteLanguage.value = idiomaNuevo;		
		submitForm('formCambioIdioma');*/
		
	}
</script>

<%
	//String path = window.location.pathname;
	//String lang = (path.split("."))[1];
	String lang = request.getParameter("idioma");
	if (lang != null){
		request.setAttribute("lang",lang);
	} else {
		request.setAttribute("lang","ca");
	}
	
%>


<!-- Estructura principal -->
<div id="layoutPrincipal"><!-- Layout Página -->
<div id="layoutPagina"><!-- Menu superior -->
<div id="menuSuperior"><!-- Barra de menu principal -->
<div class="menuBar">
	<c:choose>
		<c:when test="${not empty lang && lang == 'ca'}">
			<a class="menuButton" href="informacio.ca.html?idioma=${lang}" onclick="return buttonClick(event, 'informacioOliMenu');" onmouseover="buttonMouseover(event, 'informacionOliMenu');">Informació oli</a> 
			<a class="menuButton" href="dadescampanya.ca.html?idioma=${lang}" onclick="return buttonClick(event, 'dadesCampanyaMenu');" onmouseover="buttonMouseover(event, 'dadesCampanyaMenu');">Campanyes</a>
			<a class="menuButton" href="informacionsutils.ca.html?idioma=${lang}">Informacions Útils</a>
			<a class="menuButton" href="gestioInfografies.ca.html?idioma=${lang}">Infografies</a>
			<a class="menuButton" href="introduccion.ca.html?idioma=${lang}" onclick="return buttonClick(event, 'informacionsProjecteMenu');" onmouseover="buttonMouseover(event, 'informacionsProjecteMenu');">Informacions projecte</a> 
			<a class="menuButton" href="http://gestoli.cat/gestoli/Inici.html">Accés Aplicació</a> 
			<a class="menuButton" id="botonMenuIdioma_ca" href="" onclick="return buttonClick(event, 'idiomaMenu');" onmouseover="buttonMouseover(event, 'idiomaMenu');">Idioma</a>
		</c:when>
		<c:when test="${not empty lang && lang == 'es'}">
			<a class="menuButton" href="informacio.es.html?idioma=${lang}" onclick="return buttonClick(event, 'informacioOliMenu');" onmouseover="buttonMouseover(event, 'informacionOliMenu');">Información aceite</a> 
			<a class="menuButton" href="dadescampanya.es.html?idioma=${lang}" onclick="return buttonClick(event, 'dadesCampanyaMenu');" onmouseover="buttonMouseover(event, 'dadesCampanyaMenu');">Campañas</a>
			<a class="menuButton" href="informacionsutils.es.html?idioma=${lang}">Informaciones Útiles</a>
			<a class="menuButton" href="gestioInfografies.es.html?idioma=${lang}">Infografías</a>
			<a class="menuButton" href="introduccion.es.html?idioma=${lang}" onclick="return buttonClick(event, 'informacionsProjecteMenu');" onmouseover="buttonMouseover(event, 'informacionsProjecteMenu');">Informaciones proyecto</a> 
			<a class="menuButton" href="http://gestoli.cat/gestoli/Inici.html">Acceso Aplicación</a> 
			<a class="menuButton" id="botonMenuIdioma_es" href="" onclick="return buttonClick(event, 'idiomaMenu');" onmouseover="buttonMouseover(event, 'idiomaMenu');">Idioma</a>
		</c:when>
	</c:choose>
</div>

<!-- InformacióOli -->
<div id="informacioOliMenu" class="menu">
<div class="contenidoDesplegable" id="layInformacioOli">
<div class="supDer"></div>
<div class="infDer"></div>
<div class="infIzq"></div>
<div class="bordeIzq"></div>
<div class="bordeDer"></div>
<div class="rellenoSup"></div>
<div class="rellenoInf"></div>
<c:choose>
	<c:when test="${not empty lang && lang == 'ca'}">
		<a class="menuItem" href="cercaid.ca.html?idioma=${lang}">Cerca per Codi</a> 
		<!--a class="menuItem" href="cercageneral.ca.html?idioma=${lang}">Cerca tots</a--> 
	</c:when>
	<c:when test="${not empty lang && lang == 'es'}">
		<a class="menuItem" href="cercaid.es.html?idioma=${lang}">Búsqueda por Código</a> 
		<!--a class="menuItem" href="cercageneral.es.html?idioma=${lang}">Busca todos</a--> 
	</c:when>
</c:choose>
</div>
</div>

<!-- DadesCampanya -->
<div id="dadesCampanyaMenu" class="menu">
<div class="contenidoDesplegable" id="layDadesCampanya">
<div class="supDer"></div>
<div class="infDer"></div>
<div class="infIzq"></div>
<div class="bordeIzq"></div>
<div class="bordeDer"></div>
<div class="rellenoSup"></div>
<div class="rellenoInf"></div>
<c:choose>
	<c:when test="${not empty lang && lang == 'ca'}">
		<a class="menuItem" href="dadescampanya.ca.html?idioma=${lang}">Dades de Campanya</a> 
		<a class="menuItem" href="informescampanya.ca.html?idioma=${lang}">Informes de Campanya</a> 
	</c:when>
	<c:when test="${not empty lang && lang == 'es'}">
		<a class="menuItem" href="dadescampanya.es.html?idioma=${lang}">Datos de Campaña</a> 
		<a class="menuItem" href="informescampanya.es.html?idioma=${lang}">Informes de Campaña</a> 
	</c:when>
</c:choose>
</div>
</div>

<!-- InformacionsProjecte -->
<div id="informacionsProjecteMenu" class="menu">
<div class="contenidoDesplegable" id="layInformacionsProjecte">
<div class="supDer"></div>
<div class="infDer"></div>
<div class="infIzq"></div>
<div class="bordeIzq"></div>
<div class="bordeDer"></div>
<div class="rellenoSup"></div>
<div class="rellenoInf"></div>
<c:choose>
	<c:when test="${not empty lang && lang == 'ca'}">
		<a class="menuItem" href="introduccion.ca.html?idioma=${lang}">Introducció</a>
		<a class="menuItem" href="quees.ca.html?idioma=${lang}">Què és Gest-OLI</a> 
		<a class="menuItem" href="procesoadhesion.ca.html?idioma=${lang}">Procés d'adhesió</a> 
		<a class="menuItem" href="" onclick="return false;" onmouseover="menuItemMouseover(event, 'informacioProjecteSubMenu');">Informació del Projecte<span class="menuItemArrow">&#9654;</span></a>
		<a class="menuItem" href="asistenciatecnica.ca.html?idioma=${lang}">Assistència tècnica</a> 
	</c:when>
	<c:when test="${not empty lang && lang == 'es'}">
		<a class="menuItem" href="introduccion.es.html?idioma=${lang}">Introducción</a>
		<a class="menuItem" href="quees.es.html?idioma=${lang}">Qué es Gest-OLI</a> 
		<a class="menuItem" href="procesoadhesion.es.html?idioma=${lang}">Proceso de adhesión</a> 
		<a class="menuItem" href="" onclick="return false;" onmouseover="menuItemMouseover(event, 'informacioProjecteSubMenu');">Información del Proyecto<span class="menuItemArrow">&#9654;</span></a>
		<a class="menuItem" href="asistenciatecnica.es.html?idioma=${lang}">Asistencia técnica</a> 
	</c:when>
</c:choose>
</div>
</div>

<!-- Submenu Descripcio Instal·lacio -->
<div id="informacioProjecteSubMenu" class="menu">
<div class="contenidoDesplegable" id="laySubInformacioProjecte">
<div class="supIzq"></div>
<div class="supDer"></div>
<div class="infDer"></div>
<div class="infIzq"></div>
<div class="bordeIzq"></div>
<div class="bordeDer"></div>
<div class="rellenoSup"></div>
<div class="rellenoInf"></div>
<c:choose>
	<c:when test="${not empty lang && lang == 'ca'}">
		<a class="menuItem" href="objetivosproyecto.ca.html?idioma=${lang}">Objectius del projecte</a>
		<a class="menuItem" href="publicoobjetivo.ca.html?idioma=${lang}">Públic objectiu</a>
		<div class="separadorMenuItems"></div>
		<a class="menuItem" href="promocion.ca.html?idioma=${lang}">Promoció</a>
		<div class="separadorMenuItems"></div>			
		<a class="menuItem" target="_blank" href="http://www.illesbalearsqualitat.cat/ibqfront/producte?idPR=337&lang=ca">Més informació</a> 
		<div class="separadorMenuItems"></div>			
		<a class="menuItem" href="avisolegal.ca.html?idioma=${lang}">Avís legal</a>
	</c:when>
	<c:when test="${not empty lang && lang == 'es'}">
		<a class="menuItem" href="objetivosproyecto.es.html?idioma=${lang}">Objetivos del proyecto</a>
		<a class="menuItem" href="publicoobjetivo.es.html?idioma=${lang}">Público objetivo</a>
		<div class="separadorMenuItems"></div>
		<a class="menuItem" href="promocion.es.html?idioma=${lang}">Promoción</a>
		<div class="separadorMenuItems"></div>			
		<a class="menuItem" target="_blank" href="http://www.illesbalearsqualitat.cat/ibqfront/producte?idPR=337&lang=es">Más información</a> 
		<div class="separadorMenuItems"></div>			
		<a class="menuItem" href="avisolegal.es.html?idioma=${lang}">Aviso legal</a>
	</c:when>
</c:choose>
</div>
</div>

<!-- idioma -->
<div id="idiomaMenu" class="menu">
<div class="contenidoDesplegable" id="layIdioma">
<div class="supDer"></div>
<div class="infDer"></div>
<div class="infIzq"></div>
<div class="bordeIzq"></div>
<div class="bordeDer"></div>
<div class="rellenoSup"></div>
<div class="rellenoInf"></div>
<a class="menuItem" href="javascript:void(0);" onclick="cambioIdioma('es'); return false;">Castellano</a> 
<a class="menuItem" href="javascript:void(0);" onclick="cambioIdioma('ca'); return false;">Català</a>
</div>
</div>

<script type="text/javascript">
	$(document).ready( function() {
		setTamanosDivs('layInformacioOli');
		setTamanosDivs('layDadesCampanya');
		setTamanosDivs('layInformacionsProjecte');
		setTamanosDivs('laySubInformacioProjecte');
		setTamanosDivs('layIdioma');
	});
</script>

 <!-- Ejemplo submenu (Por si algún día se agrega un submenu)
<div id="ejemploMenu" class="menu" onmouseover="menuMouseover(event)">
	<a class="menuItem" href="blank.html">File Menu Item 1</a>
	<a class="menuItem" href="" onclick="return false;" onmouseover="menuItemMouseover(event, 'fileMenu2');"><span class="menuItemText">File Menu Item 2</span><span class="menuItemArrow">&#9654;</span></a>
	<a class="menuItem" href="blank.html">File Menu Item 3</a>
	<a class="menuItem" href="blank.html">File Menu Item 4</a>
	<a class="menuItem" href="blank.html">File Menu Item 5</a>
	<div class="menuItemSep"></div>
	<a class="menuItem" href="blank.html">File Menu Item 6</a>
</div>
--></div>

<!-- Cabecera -->
<div id="cabeceraGeneral" class="microweb">
<h1><a href="informacionsutils.ca.html"> <span>Gest-OLI</span> </a></h1>
</div>


<!-- Línea de path -->
<div id="pathCabecera" class="microweb"></div>

<!-- Contenido Principal -->
<div class="fondoHorizontal">
<div class="fondoIzquierda">
<div class="fondoDerecha">
<div class="fondoVertical">
<div id="contenidoPrincipal" class="microweb">
	<jsp:include page="../comu/MissatgesIErrors.jsp" /> 
	<decorator:body />
</div>
</div>
</div>
</div>
</div>
<div class="fondoBottom"></div>

</div>

<!-- Pie de página -->
<div id="pieGeneral"><jsp:include page="../plantilla/PieWeb.jsp" />
</div>

</div>

</body>
</html>