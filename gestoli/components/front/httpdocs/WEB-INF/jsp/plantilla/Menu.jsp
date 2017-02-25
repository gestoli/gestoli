<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="decorator" uri="/WEB-INF/sitemesh-decorator.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ page import="java.util.Enumeration"%>
<%@ page import="es.caib.gestoli.front.util.*"%>


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
		form = document.getElementById('formCambioIdioma');
		form.action = path;
		form.lang.value = idiomaNuevo;
		form.siteLanguage.value = idiomaNuevo;		
		submitForm('formCambioIdioma');
		
	}

	function generarCartilla(idOlivicultor) {
		path = "<%=request.getContextPath()%>/GenerarPdf.html";
		form = document.getElementById('formGenerarCartilla');
		form.action = path;
		form.idCartilla.value = idOlivicultor;
		submitForm('formGenerarCartilla');
	}

	function olivicultorConsultaFactura(idOlivicultor) {
		path = "<%=request.getContextPath()%>/GenerarPdf.html";
		form = document.getElementById('formOlivicultorConsultaFactura');
		form.action = path;
		form.idFactura.value = idOlivicultor;
		submitForm('formOlivicultorConsultaFactura');
	}

</script>



<%
	String lang = Idioma.getLang(request);
	request.setAttribute("lang",lang);
%>

<form id="formCambioIdioma" action="" method="get"><input
	type="hidden" name="cambioIdioma" value="true" /> <input type="hidden"
	name="siteLanguage" value="<%=lang%>" /> <%
	Enumeration paramNames = request.getParameterNames();
	while (paramNames.hasMoreElements ()) {
		String codigo = (String)paramNames.nextElement();
		if(!codigo.equalsIgnoreCase("cambioIdioma") && !codigo.equalsIgnoreCase("siteLanguage")){
	%> <input type="hidden" name="<%=codigo%>"
	value="<%=request.getParameter(codigo)%>" /> <%
		}
	}
	%>
</form>
<div style="display: none">
<form id="formGenerarCartilla" action="" method="post"><input
	type="hidden" name="tipus" id="tipusCartilla" value="3" /> <input
	type="hidden" name="id" id="idCartilla" value="" /></form>

<form id="formOlivicultorConsultaFactura" action="" method="post">
<input type="hidden" name="tipus" id="tipusFactura" value="5" /> <input
	type="hidden" name="id" id="idFactura" value="" /></form>

</div>


<!-- Barra de menu principal -->
<div class="menuBar"><!-- Establiments --> <c:choose>
	<c:when
		test="${not empty esDoGestor || not empty esAdministracio || not empty esDoControlador}">
		<a class="menuButton"
			href="<%=request.getContextPath()%>/Establiment.html?vista=t"><fmt:message
			key="menu.vista_establecimientos" /></a>
	</c:when>
	<c:when
		test="${empty esDoGestor && empty esAdministracio && empty esDoControlador && ((not empty esProductor || not empty esEnvasador) && not empty establiment)}">
		<a class="menuButton"
			href="<%=request.getContextPath()%>/ProcesInici.html"><fmt:message
			key="menu.vista_establecimiento" /></a>
	</c:when>
</c:choose> 

<!-- Informacions -->
<c:choose>
	<c:when
		test="${not empty esDoGestor || not empty esAdministracio || not empty esDoControlador}">
		<a class="menuButton" href=""
			onclick="return buttonClick(event, 'informacionMenu');"
			onmouseover="buttonMouseover(event, 'informacionMenu');"><fmt:message
			key="menu.informacion" /></a>
	</c:when>
	<c:when
		test="${empty esDoGestor && empty esAdministracio && empty esDoControlador && (not empty esOlivicultor || not empty esProductor || not empty esEnvasador)}">
		<c:if test="${empty noExistenInformaciones}">
			<c:choose>
				<c:when test="${not empty existenInformacionesPendientes}">
					<a class="menuButton" id="botonMenuInformacionPendiente"
						href="<%=request.getContextPath()%>/LlegirInformacio.html"
						title="<fmt:message key="menu.informacion.alert"/>">
						<fmt:message key="menu.informacion" /></a>
				</c:when>
				<c:otherwise>
					<a class="menuButton"
						href="<%=request.getContextPath()%>/LlegirInformacio.html"><fmt:message
						key="menu.informacion" /></a>
				</c:otherwise>
			</c:choose>
		</c:if>
	</c:when>
</c:choose> 

<!-- Accions --> 
<c:choose>
	<c:when test="${not empty esDoGestor  || not empty esDoControlador}">
		<a class="menuButton" href=""
			onclick="return buttonClick(event, 'accionMenu');"
			onmouseover="buttonMouseover(event, 'accionMenu');"><fmt:message
			key="menu.acciones" /></a>
	</c:when>
	<c:when
		test="${empty esDoGestor && empty esAdministracio && ((not empty esProductor || not empty esEnvasador) && not empty establiment)}">
		<a class="menuButton" href=""
			onclick="return buttonClick(event, 'accionMenu');"
			onmouseover="buttonMouseover(event, 'accionMenu');"><fmt:message
			key="menu.acciones" /></a>
	</c:when>
	<c:when
		test="${((not empty esTafona || not empty esEnvasador) && not empty establiment) || not empty esDoControlador}">
		<a class="menuButton" href=""
			onclick="return buttonClick(event, 'accionMenu');"
			onmouseover="buttonMouseover(event, 'accionMenu');"><fmt:message
			key="menu.acciones" /></a>
	</c:when>
	<c:when test="${not empty esOlivicultor}">
		<a class="menuButton" href=""
			onclick="return buttonClick(event, 'accionMenu');"
			onmouseover="buttonMouseover(event, 'accionMenu');"><fmt:message
			key="menu.acciones" /></a>
	</c:when>
</c:choose> 

<!-- Consultes --> 
<c:choose>
	<c:when
		test="${not empty esDoGestor || not empty esAdministracio || not empty esDoControlador}">
		<a class="menuButton" href=""
			onclick="return buttonClick(event, 'consultasMenu');"
			onmouseover="buttonMouseover(event, 'consultasMenu');"><fmt:message
			key="menu.consultas" /></a>
	</c:when>
	<c:when
		test="${(not empty esProductor || not empty esEnvasador) && not empty establiment}">
		<a class="menuButton" href=""
			onclick="return buttonClick(event, 'consultasMenu');"
			onmouseover="buttonMouseover(event, 'consultasMenu');"><fmt:message
			key="menu.consultas" /></a>
	</c:when>
	<c:when
		test="${not empty esOlivicultor && not empty oliv && (oliConsultaCartilla || oliConsultaFactura)}">
		<a class="menuButton" href=""
			onclick="return buttonClick(event, 'consultasMenu');"
			onmouseover="buttonMouseover(event, 'consultasMenu');"><fmt:message
			key="menu.consultas" /></a>
	</c:when>
</c:choose> 

<!-- Tramits --> 
<c:choose>
	<c:when test="${not empty esTafona || not empty esEnvasador}">
		<a class="menuButton" href=""
			onclick="return buttonClick(event, 'tramitsMenu');"
			onmouseover="buttonMouseover(event, 'tramitsMenu');"><fmt:message
			key="menu.tramites" /></a>
	</c:when>
</c:choose> 

<!-- Qualitat --> 
<c:choose>
	<c:when test="${not empty esTafona || not empty esEnvasador}">
		<a class="menuButton" href=""
			onclick="return buttonClick(event, 'qualitatMenu');"
			onmouseover="buttonMouseover(event, 'qualitatMenu');"><fmt:message
			key="menu.qualitat" /></a>
		</c:when>
</c:choose> 


<c:if
	test="${not empty esDoGestor 
					|| 
				 (empty esDoGestor && (not empty esOlivicultor || not empty esProductor || not empty esEnvasador))
					||
				 (empty esDoGestor && (not empty esProductor || not empty esEnvasador) && not empty establiment)
					||
				 (empty esDoGestor && not empty esOlivicultor && not empty oliv)
					||
				 ((not empty esProductor || not empty esEnvasador) && not empty establiment)
					||
				 ((not empty esProductor || not empty esEnvasador) && not empty establiment && not empty zonasActivas)
					||
				 (not empty lang)
				 }">
	<a class="menuButton" href="" onclick="return buttonClick(event, 'configuracionMenu');" onmouseover="buttonMouseover(event, 'configuracionMenu');"><fmt:message key="menu.configuracion" /></a>
</c:if>

		<!--Exemple-->  
	<!--<a class="menuButton" href=""
		onclick="return buttonClick(event, 'ejemploMenu');"
		onmouseover="buttonMouseover(event, 'ejemploMenu');"><fmt:message
		key="menu.qualitat" /></a>-->
		
</div>





<!-- Caja tareas pendientes-->
<c:if test="${(not empty existenAccionesPendientes) || (not empty existenAvisosPendientes)}">
	<div id="tareasPendientes">
	<p><a href="TrasllatLlistat.html"><fmt:message
		key="manteniment.tareasPendientes" /></a></p>
	</div>
</c:if>


<!-- Acciones -->
<div id="accionMenu" class="menu">
<div class="contenidoDesplegable" id="layAccion">
<div class="supIzq"></div>
<div class="supDer"></div>
<div class="infDer"></div>
<div class="infIzq"></div>
<div class="bordeIzq"></div>
<div class="bordeDer"></div>
<div class="rellenoSup"></div>
<div class="rellenoInf"></div>
	<c:if test="${not empty esOlivaTaula}">
		<a class="menuItem" 
			href=""
			onclick="return false;" 
			onmouseover="menuItemMouseover(event, 'accionsOlivaTaula');"> 
			<fmt:message key="menu.acciones.oliva_taula"/>
			<span class="menuItemArrow">&#9654;</span>
		</a>
	</c:if>
	<c:if test="${not empty esDoGestor}">
		<a class="menuItem" href="<%=request.getContextPath()%>/Campanya.html"><fmt:message key="menu.acciones.nueva_campanya"/></a>
		<a class="menuItem" href="<%=request.getContextPath()%>/Prefactura.html"><fmt:message key="menu.acciones.prefactura"/></a>
		<a class="menuItem" href="<%=request.getContextPath()%>/RendimentVarietatForm.html"><fmt:message key="menu.acciones.modificar_rendiments"/></a>
		<a class="menuItem" href="<%=request.getContextPath()%>/EtiquetesOlivicultors.html"><fmt:message key="menu.acciones.etiquetes"/></a>
		<a class="menuItem" href="<%=request.getContextPath()%>/EtiquetesLotEstabliment.html"><fmt:message key="menu.acciones.etiquetesLot"/></a>
		<a class="menuItem" href="<%=request.getContextPath()%>/ProcesRetirarEtiquetesEstablimentLlistat.html"><fmt:message key="menu.acciones.retirarEtiquetes"/></a>
	</c:if> 
	<c:if test="${not empty esProductor && not empty establiment && not empty esTafona && not empty esEstAdministrador}">
		<a class="menuItem" href="<%=request.getContextPath()%>/ProcesRetirarEtiquetesEstablimentLlistat.html"><fmt:message key="menu.acciones.retirarEtiquetes"/></a>
	</c:if>
	<c:if test="${not empty esProductor && not empty establiment && not empty esTafona}">
		<a class="menuItem" href="<%=request.getContextPath()%>/ProcesInici.html?tipusProces=0&amp;idOlivicultor=app"><fmt:message key="menu.acciones.entrada_oliva" /></a>
		<a class="menuItem" href="<%=request.getContextPath()%>/ProcesInici.html?tipusProces=2"><fmt:message key="menu.acciones.elaboracion_aceite" /></a>
	</c:if> 
	<c:if test="${ (not empty esProductor || not empty esEnvasador) && not empty establiment }">
		<a class="menuItem" href="<%=request.getContextPath()%>/ProcesInici.html?tipusProces=3"><fmt:message key="menu.acciones.trasvase" /></a>
		<a class="menuItem" href="<%=request.getContextPath()%>/ProcesInici.html?tipusProces=4"><fmt:message key="menu.acciones.perdida_aceite" /></a>
		<a class="menuItem" href="<%=request.getContextPath()%>/ProcesInici.html?tipusProces=6"><fmt:message key="menu.acciones.descalificar" /></a>
	</c:if> 
	<c:if test="${ (not empty esProductor || not empty esEnvasador) && not empty establiment && not empty zonasFicticias}">
		<a class="menuItem" href="<%=request.getContextPath()%>/ProcesInici.html?tipusProces=10"><fmt:message key="menu.acciones.mover_deposito" /></a>
		<a class="menuItem" href="<%=request.getContextPath()%>/ProcesInici.html?tipusProces=12"><fmt:message key="menu.acciones.mover_aceite" /></a>
	</c:if> 
	<c:if test="${ not empty establiment && ( (not empty esProductor && not empty esEnvasadora ) || not empty esEnvasador)}">
	</c:if> 
	<c:if test="${not empty esDoControlador}">
		<a class="menuItem" href="<%=request.getContextPath()%>/Establiment.html?analitica=t"><fmt:message key="menu.acciones.introducir_analitica" /></a>
	</c:if>
	<c:if test="${ (not empty esProductor || not empty esEnvasador) && not empty establiment }">
		<a class="menuItem" href="<%=request.getContextPath()%>/ProcesInici.html?tipusProces=5"><fmt:message key="menu.acciones.introducir_analitica" /></a>
		<a class="menuItem" href="<%=request.getContextPath()%>/ProcesInici.html?tipusProces=14"><fmt:message key="menu.acciones.pendent_analitica" /></a>
		<a class="menuItem" href="<%=request.getContextPath()%>/ProcesInici.html?tipusProces=15"><fmt:message key="menu.acciones.desfer_pendent_analitica" /></a>
	</c:if> 
	<c:if test="${ not empty establiment && not empty esEnvasador}">
		<a class="menuItem" href="<%=request.getContextPath()%>/ProcesInici.html?tipusProces=7"><fmt:message key="menu.acciones.crear_lote" /></a>
		<a class="menuItem" href="<%=request.getContextPath()%>/ProcesInici.html?tipusProces=8"><fmt:message key="menu.acciones.etiquetar" /></a>
	</c:if> 
	<c:if test="${(not empty esTafona || not empty esEnvasador) && not empty establiment}">
		<a class="menuItem" href="<%=request.getContextPath()%>/ProcesInici.html?tipusProces=9"><fmt:message key="menu.acciones.salida_aceite" /></a>
		
		<a class="menuItem" href="<%=request.getContextPath()%>/TancamentLlibres.html"><fmt:message key="menu.acciones.cierre_libros" /></a>
	</c:if> 
	<c:if test="${((not empty esTafona || not empty esEnvasador) && not empty establiment) || not empty esDoControlador}">
		<a class="menuItem" href="<%=request.getContextPath()%>/EdicioProcessos.html"><fmt:message key="menu.acciones.edicion_procesos" /></a> 	
	</c:if>
	<c:if test="${not empty esTafona && not empty establiment}">
		<a class="menuItem" href="<%=request.getContextPath()%>/SortidaOrujo.html"><fmt:message key="menu.acciones.sortida.orujo" /></a>
	</c:if> 
	<c:if test="${(not empty esTafona || not empty esEnvasador)}">
		<a class="menuItem" href="<%=request.getContextPath()%>/ModificarDocumentSortida.html"><fmt:message key="menu.acciones.modificar.document.sortida" /></a>
	</c:if> 
	<c:if test="${not empty esEnvasador}">
		<a class="menuItem" href="<%=request.getContextPath()%>/Devolucio.html"><fmt:message key="menu.acciones.devolucio" /></a>
	</c:if>
	<c:if test="${not empty esDoGestor || not empty esAdministracio}">
		<a class="menuItem" href="<%=request.getContextPath()%>/InformacioUtil.html"><fmt:message key="menu.acciones.informacioUtil" /></a>
	</c:if>
	<c:if test="${not empty esDoGestor}">
		<a class="menuItem" href="<%=request.getContextPath()%>/InformeCampanya.html"><fmt:message key="menu.acciones.informeCampanya" /></a>
	</c:if>
	<c:if test="${not empty esOlivicultor}">
		<a class="menuItem" href="<%=request.getContextPath()%>/QuadernCampLlistat.html"><fmt:message key="menu.acciones.quadernCamp" /></a>
	</c:if>
	<c:if test="${(not empty esTafona || not empty esEnvasador) && not empty establiment}">
		<a class="menuItem" href="<%=request.getContextPath()%>/ImportacioProcesSortidaForm.html"><fmt:message key="menu.acciones.importacioProcesSortida" /></a>
	</c:if>
	<c:if test="${(not empty esTafona || not empty esEnvasador)}">
		<a class="menuItem" href="<%=request.getContextPath()%>/ModificarOliComercialitzatLlistat.html"><fmt:message key="menu.acciones.modificarVendesComercialitzacio" /></a>
	</c:if>
	
</div>
</div>

<!-- Consultas -->
<div id="consultasMenu" class="menu">
<div class="contenidoDesplegable" id="layConsultas">
<div class="supIzq"></div>
<div class="supDer"></div>
<div class="infDer"></div>
<div class="infIzq"></div>
<div class="bordeIzq"></div>
<div class="bordeDer"></div>
<div class="rellenoSup"></div>
<div class="rellenoInf"></div>
<c:if test="${not empty esOlivaTaula}">
		<a class="menuItem" 
			href=""
			onclick="return false;" 
			onmouseover="menuItemMouseover(event, 'consultesOlivaTaula');"> 
			<fmt:message key="menu.acciones.oliva_taula"/>
			<span class="menuItemArrow">&#9654;</span>
		</a>
</c:if>
<c:if test="${not empty esOlivicultor && not empty oliv && oliConsultaCartilla}">
	<a class="menuItem" href="javascript:void(0);" onclick="generarCartilla(<c:out value='${oliv.id}'/>); return false;"><fmt:message key="menu.consultas.cartilla_produccion" /></a>
</c:if> 
<c:if test="${not empty esOlivicultor && not empty oliv && oliConsultaFactura}">
	<a class="menuItem" href="javascript:void(0);" onclick="olivicultorConsultaFactura(<c:out value='${oliv.id}'/>); return false;"><fmt:message key="menu.consultas.factura_pago_cartilla" /></a>
</c:if> 
<c:if test="${not empty esDoGestor || not empty esAdministracio || not empty esDoControlador}">
	<a class="menuItem" href="<%=request.getContextPath()%>/Cartilla.html"><fmt:message key="menu.consultas.cartilla_produccion" /></a>
</c:if>
<c:if test="${not empty esDoGestor}">
	<a class="menuItem" href="<%=request.getContextPath()%>/Factura.html"><fmt:message key="menu.consultas.factura_pago_cartilla" /></a>
	<c:choose>
		<c:when test="${not empty lang && lang == 'ca'}">
			<a class="menuItem" href="web/cercageneral.ca.html?idioma=${lang}" target="_blank">Cerca informació</a> 
		</c:when>
		<c:when test="${not empty lang && lang == 'es'}">
			<a class="menuItem" href="web/cercageneral.es.html?idioma=${lang}" target="_blank">Busca información</a> 
		</c:when>
	</c:choose>
</c:if>
	<a class="menuItem" href="<%=request.getContextPath()%>/ConsultaBasicaGeneral.html">Consulta bàsica general</a>
<c:if test="${not empty esDoGestor || not empty esAdministracio || not empty esDoControlador}">
	<a class="menuItem" href="<%=request.getContextPath()%>/ConsultaAgrupacioConsultes.html"><fmt:message key="menu.consultas.agrupacioConsultes" /></a>
	<a class="menuItem" href="<%=request.getContextPath()%>/ConsultaLlibres.html"><fmt:message key="menu.consultas.llibres" /></a>
	<a class="menuItem" href="<%=request.getContextPath()%>/Establiment.html?vista=t&consulta=c"><fmt:message key="menu.consultas.entrada_oliva" /></a>
	<a class="menuItem" href="<%=request.getContextPath()%>/ConsultaDeclaracioMensual.html"><fmt:message key="menu.consultas.declaracioMensual" /></a>
	<a class="menuItem" href="<%=request.getContextPath()%>/ConsultaOliElaboratEstablimentLlistat.html"><fmt:message key="menu.consultas.aceite_elaborado" /></a>
	<a class="menuItem" href="<%=request.getContextPath()%>/ConsultaDocRendimentEstablimentLlistat.html"><fmt:message key="menu.consultas.docRendiment" /></a>
	<a class="menuItem" href="<%=request.getContextPath()%>/ConsultaSortidaOrujoLlistat.html"><fmt:message key="menu.consultas.sortida_orujo" /></a>
	
	<a class="menuItem" href="<%=request.getContextPath()%>/ConsultaOliEmbotellatEstablimentLlistat.html"><fmt:message key="menu.consultas.aceite_embotellado" /></a>
	<a class="menuItem" href="<%=request.getContextPath()%>/ConsultaOliDisponibleEstablimentLlistat.html"><fmt:message key="menu.consultas.aceite_disponible" /></a>
	<%--a class="menuItem" href="<%=request.getContextPath()%>/ConsultaOliSalidaTrasladoEstablimentLlistat.html"><fmt:message key="menu.consultas.aceite_salida_traslado" /></a>
	<a class="menuItem" href="<%=request.getContextPath()%>/ConsultaOliEntradaTrasladoEstablimentLlistat.html"><fmt:message key="menu.consultas.aceite_entrada_traslado" /></a--%>
	<a class="menuItem" href="<%=request.getContextPath()%>/ConsultaMovimentsEntreEstablimentsEstablimentLlistat.html"><fmt:message	key="menu.consultas.moviments_entre_establiments" /></a>
	<a class="menuItem" href="<%=request.getContextPath()%>/ConsultaEtiquetatgeMarcaEstablimentLlistat.html"><fmt:message key="menu.consultas.etiquetatge_marca" /></a>
	<a class="menuItem" href="<%=request.getContextPath()%>/ConsultaEtiquetesEstablimentLlistat.html"><fmt:message key="menu.consultas.etiquetes" /></a>
	<a class="menuItem" href="<%=request.getContextPath()%>/ConsultaTrasabilitatOlivicultorEstablimentLlistat.html"><fmt:message key="menu.consultas.trasabilitat_olivicultor" /></a>
	<a class="menuItem" href="<%=request.getContextPath()%>/ConsultaOliComercialitzatEstablimentLlistat.html"><fmt:message key="menu.consultas.aceite_comercializado" /></a>
	<%--a class="menuItem" href="<%=request.getContextPath()%>/ConsultaHistoriaDipositEstablimentLlistat.html"><fmt:message key="menu.consultas.historia_diposit" /></a--%>
	<%--a class="menuItem" href="<%=request.getContextPath()%>/ConsultaAnaliticasEstablimentLlistat.html"><fmt:message key="menu.consultas.analiticas" /></a--%>
	<a class="menuItem" href="<%=request.getContextPath()%>/ConsultaHistoriaDipositLlistatEstabliment.html"><fmt:message key="menu.consultas.historia_diposit" /></a>
	<a class="menuItem" href="<%=request.getContextPath()%>/ConsultaAnaliticasSimplificatEstablimentLlistat.html"><fmt:message key="menu.consultas.analiticas" /></a>
	<a class="menuItem" href="<%=request.getContextPath()%>/ConsultaGeneral.html"><fmt:message key="menu.consultas.general" /></a>
	<a class="menuItem" 
			href=""
			onclick="return false;" 
			onmouseover="menuItemMouseover(event, 'consultesOlivaTaulaAdministracio');"> 
			<fmt:message key="menu.acciones.oliva_taula"/>
			<span class="menuItemArrow">&#9654;</span>
	</a>
	<c:if test="${not empty esDoGestor || not empty esDoControlador}">
		<a class="menuItem" href="<%=request.getContextPath()%>/QuadernCampLlistat.html"><fmt:message key="menu.acciones.quadernCamp" /></a>
	</c:if>
	<div class="separadorMenuItems"></div>
	<a class="menuItem" href="<%=request.getContextPath()%>/InformeProduccio.html"><fmt:message key="menu.consultas.altresConsultes.informeProduccio" /></a>
	<a class="menuItem" href="<%=request.getContextPath()%>/AltresConsultesTrazabilidadPartida.html"><fmt:message key="menu.consultas.altresConsultes.trazabilidadPartida" /></a>
	<%--a class="menuItem" href="<%=request.getContextPath()%>/AltresConsultesTrazabilidad.html"><fmt:message key="menu.consultas.altresConsultes.trazabilidad" /></a--%>
	<%--a class="menuItem" href="<%=request.getContextPath()%>/AltresConsultesTrazabilidadResumida.html"><fmt:message key="menu.consultas.altresConsultes.trazabilidad.resumida" /></a --%>
	<a class="menuItem" href="<%=request.getContextPath()%>/InformeProductors.html"><fmt:message key="menu.consultas.altresConsultes.informesProductors" /></a>
	<a class="menuItem" href="<%=request.getContextPath()%>/InformeProductorsRA.html"><fmt:message key="menu.consultas.altresConsultes.informesProductorsRA" /></a>
	<a class="menuItem" href="<%=request.getContextPath()%>/InformeProductorsRE.html"><fmt:message key="menu.consultas.altresConsultes.informesProductorsRE" /></a>
	<a class="menuItem" href="<%=request.getContextPath()%>/InformeProductorsRT.html"><fmt:message key="menu.consultas.altresConsultes.informesProductorsRT" /></a>
	<%--a class="menuItem" href="<%=request.getContextPath()%>/ConsultaGeneralOliComercialitzat.html"><fmt:message key="menu.consultas.altresConsultes.oliComercialitzat" /></a--%>
	<%--a class="menuItem" href="<%=request.getContextPath()%>/AltresConsultesGrafics.html"><fmt:message key="menu.consultas.altresConsultes.grafics" /></a--%>
	<c:if test="${not empty esDoGestor}">
		<div class="separadorMenuItems"></div>
		<a class="menuItem" href="<%=request.getContextPath()%>/HistoricEstabliment.html"><fmt:message key="menu.consultas.historic.establiment" /></a>
		<a class="menuItem" href="<%=request.getContextPath()%>/HistoricOlivicultor.html"><fmt:message key="menu.consultas.historic.olivicultor" /></a>
		<a class="menuItem" href="<%=request.getContextPath()%>/HistoricLlistatFactura.html"><fmt:message key="menu.consultas.historic.llistat.factura" /></a>
	</c:if>
	<div class="separadorMenuItems"></div>
	<c:if test="${not empty esDoGestor}">
		<a class="menuItem" href="<%=request.getContextPath()%>/web/cercageneral.<%=lang%>.html?idioma=<%=lang%>" target="_blank"><fmt:message key="menu.consultas.cerca.tots" /></a>
	</c:if>
	<a class="menuItem" href="<%=request.getContextPath()%>/CercaTrazabilitatEstablimentLlistat.html"><fmt:message key="menu.consultas.cerca.trazabilitat" /></a>
	<a class="menuItem" href="<%=request.getContextPath()%>/ConsultaHaMunicipiLlistat.html"><fmt:message key="menu.consultas.haMunicipi" /></a>
</c:if> 
<c:if test="${not empty esEnvasador || not empty esProductor}">
	<a class="menuItem" href="<%=request.getContextPath()%>/ConsultaLlibres.html"><fmt:message key="menu.consultas.llibres" /></a>
	<a class="menuItem" href="<%=request.getContextPath()%>/ConsultaDeclaracioMensual.html"><fmt:message key="menu.consultas.declaracioMensual" /></a>
	<a class="menuItem" href="<%=request.getContextPath()%>/ConsultaHistoriaDipositTrasllatLlistat.html"><fmt:message key="menu.consultas.historia_diposit_trasllat" /></a>
</c:if> 
<c:if test="${not empty esProductor}">
	<a class="menuItem" href="<%=request.getContextPath()%>/ConsultaLlistat.html"><fmt:message key="menu.consultas.entrada_oliva" /></a>
	<a class="menuItem" href="<%=request.getContextPath()%>/ConsultaOliElaboratLlistat.html"><fmt:message key="menu.consultas.aceite_elaborado" /></a>
	<a class="menuItem" href="<%=request.getContextPath()%>/ConsultaDocRendimentLlistat.html"><fmt:message key="menu.consultas.docRendiment" /></a>
	<a class="menuItem" href="<%=request.getContextPath()%>/ConsultaSortidaOrujoLlistat.html"><fmt:message key="menu.consultas.sortida_orujo" /></a>
</c:if> 

<c:if test="${not empty esEnvasador}">
	<a class="menuItem" href="<%=request.getContextPath()%>/ConsultaOliEmbotellatLlistat.html"><fmt:message key="menu.consultas.aceite_embotellado" /></a>
</c:if>
<c:if test="${ not empty establiment && ( (not empty esProductor && not empty esEnvasadora ) || not empty esEnvasador)}">
	<a class="menuItem" href="<%=request.getContextPath()%>/ConsultaOliComercialitzatLlistat.html"><fmt:message key="menu.consultas.aceite_comercializado" /></a>
	<a class="menuItem" href="<%=request.getContextPath()%>/ConsultaHistoriaDipositLlistat.html"><fmt:message key="menu.consultas.historia_diposit" /></a>
	<a class="menuItem" href="<%=request.getContextPath()%>/ConsultaHistoriaDipositTrasllatLlistat.html"><fmt:message key="menu.consultas.historia_diposit_trasllat" /></a>
	
</c:if> 
<c:if test="${not empty esEnvasador || not empty esProductor}">
	<a class="menuItem" href="<%=request.getContextPath()%>/ConsultaOliDisponibleLlistat.html"><fmt:message key="menu.consultas.aceite_disponible" /></a>
	<%--a class="menuItem" href="<%=request.getContextPath()%>/ConsultaOliSalidaTrasladoLlistat.html"><fmt:message key="menu.consultas.aceite_salida_traslado" /></a>
	<a class="menuItem" href="<%=request.getContextPath()%>/ConsultaOliEntradaTrasladoLlistat.html"><fmt:message key="menu.consultas.aceite_entrada_traslado" /></a--%>
	<a class="menuItem" href="<%=request.getContextPath()%>/ConsultaMovimentsEntreEstablimentsLlistat.html"><fmt:message key="menu.consultas.moviments_entre_establiments" /></a>
	<a class="menuItem" href="<%=request.getContextPath()%>/ConsultaEtiquetatgeMarcaLlistat.html"><fmt:message key="menu.consultas.etiquetatge_marca" /></a>
	<a class="menuItem" href="<%=request.getContextPath()%>/ConsultaTrasabilitatOlivicultorLlistat.html"><fmt:message key="menu.consultas.trasabilitat_olivicultor" /></a>
	<a class="menuItem" href="<%=request.getContextPath()%>/ProcesInici.html?tipusProces=11"><fmt:message key="menu.consultas.analiticas" /></a>
	<a class="menuItem" href="<%=request.getContextPath()%>/CercaTrazabilitatLlistat.html"><fmt:message key="menu.consultas.cerca.trazabilitat" /></a>
	<a class="menuItem" href="<%=request.getContextPath()%>/cercaid.ca.html"><fmt:message key="menu.informacion.percodi"/></a>
	
</c:if>
</div>
</div>


<!-- Información -->
<div id="informacionMenu" class="menu">
<div class="contenidoDesplegable" id="layInformacion">
<div class="supIzq"></div>
<div class="supDer"></div>
<div class="infDer"></div>
<div class="infIzq"></div>
<div class="bordeIzq"></div>
<div class="bordeDer"></div>
<div class="rellenoSup"></div>
<div class="rellenoInf"></div>
<c:if
	test="${not empty esDoGestor || not empty esAdministracio || not empty esDoControlador }">
	<a class="menuItem"
		href="<%=request.getContextPath()%>/CategoriaInformacio.html"><fmt:message
		key="menu.informacion.categoria" /></a>
	<a class="menuItem"
		href="<%=request.getContextPath()%>/GestionarInformacio.html"><fmt:message
		key="menu.informacion.informacion" /></a>
</c:if>
</div>

</div>


<!-- Tramits -->
<div id="tramitsMenu" class="menu">
<div class="contenidoDesplegable" id="layTramits">
<div class="supIzq"></div>
<div class="supDer"></div>
<div class="infDer"></div>
<div class="infIzq"></div>
<div class="bordeIzq"></div>
<div class="bordeDer"></div>
<div class="rellenoSup"></div>
<div class="rellenoInf"></div>
	<a class="menuItem" href=""
	onclick="return false;" onmouseover="menuItemMouseover(event, 'tramitesSistraMenu');"> 
	<fmt:message key="menu.tramites.DO"/>
	<span class="menuItemArrow">&#9654;</span>
	</a>
	<a class="menuItem" href=""
	onclick="return false;" onmouseover="menuItemMouseover(event, 'tramitesAgenciaOliMenu');"> 
	<fmt:message key="menu.tramites.agenciaOli"/>
	<span class="menuItemArrow">&#9654;</span>
	</a>
</div>
</div>



<!-- Qualitat -->
<div id="qualitatMenu" class="menu">
<div class="contenidoDesplegable" id="layQualitat">
<div class="supIzq"></div>
<div class="supDer"></div>
<div class="infDer"></div>
<div class="infIzq"></div>
<div class="bordeIzq"></div>
<div class="bordeDer"></div>
<div class="rellenoSup"></div>
<div class="rellenoInf"></div>

	<c:choose>
		<c:when test="${not empty esEstAdministrador}">
			<a class="menuItem" href="" onclick="return false;" onmouseover="menuItemMouseover(event, 'gestorDocumentalSubMenu');"> 
				<fmt:message key="qualitat.menu.gestorDocumental"/>
				<span class="menuItemArrow">&#9654;</span>
			</a>
		</c:when>
		<c:when test="${empty esEstAdministrador && (not empty esEstEncarregat || not empty esEstTreballador || not empty esEstConsulta)}">
			<c:if test="${empty noExistenInformaciones}">
				<c:choose>
					<c:when test="${not empty existenInformacionesPendientes}">
						<a class="menuItem" id="botonMenuInformacionPendiente" href="<%=request.getContextPath()%>/GestorDocumentalLlegirInformacio.html" title="<fmt:message key="menu.informacion.alert"/>"><fmt:message key="qualitat.menu.gestorDocumental" /></a>
					</c:when>
					<c:otherwise>
						<a class="menuItem" href="<%=request.getContextPath()%>/GestorDocumentalLlegirInformacio.html"><fmt:message key="qualitat.menu.gestorDocumental" /></a>
					</c:otherwise>
				</c:choose>
			</c:if>
		</c:when>
	</c:choose> 
	<c:if test="${not empty esEstAdministrador || not empty esEstEncarregat || not empty esEstTreballador || not empty esEstConsulta}">
		<a class="menuItem" href="" onclick="return false;" onmouseover="menuItemMouseover(event, 'descripcioInstalacioSubMenu');"><fmt:message key="qualitat.menu.instalacio"/><span class="menuItemArrow">&#9654;</span></a>
		<a class="menuItem" href="<%=request.getContextPath()%>/QualitatPlaNeteja.html"><fmt:message key="qualitat.menu.plaNetetjaDesinfeccio"/></a>
		<a class="menuItem" href="<%=request.getContextPath()%>/QualitatPlaManteniment.html"><fmt:message key="qualitat.menu.plaManteniment"/></a>
		<a class="menuItem" href=""	onclick="return false;" onmouseover="menuItemMouseover(event, 'plaFormacioSubMenu');"><fmt:message key="qualitat.menu.plaFormacio"/><span class="menuItemArrow">&#9654;</span></a>
		<a class="menuItem" href="<%=request.getContextPath()%>/QualitatControlPlagues.html"><fmt:message key="qualitat.menu.plaControlPlagues"/></a>
	</c:if>
	<c:if test="${not empty esEstAdministrador || not empty esEstEncarregat || not empty esEstTreballador}">
		<a class="menuItem" href="<%=request.getContextPath()%>/QualitatProveidor.html"><fmt:message key="qualitat.menu.plaControlProveidors"/></a>
	</c:if>
	<c:if test="${not empty esEstAdministrador || not empty esEstEncarregat || not empty esEstTreballador || not empty esEstConsulta}">
		<a class="menuItem" href=""	onclick="return false;" onmouseover="menuItemMouseover(event, 'plaAbastamentAiguaSubMenu');"><fmt:message key="qualitat.menu.plaAbastamentAigua"/><span class="menuItemArrow">&#9654;</span></a>
<!-- 	<a class="menuItem" href=""><fmt:message key="qualitat.menu.plaControlTrazabilidad"/></a> -->
		<a class="menuItem" href="<%=request.getContextPath()%>/QualitatPlaControlTransport.html"><fmt:message key="qualitat.menu.plaControlTransport"/></a>
		<a class="menuItem" href=""	onclick="return false;" onmouseover="menuItemMouseover(event, 'aPPCCSubMenu');"><fmt:message key="qualitat.menu.APPCC"/><span class="menuItemArrow">&#9654;</span></a>
		<a class="menuItem" href="<%=request.getContextPath()%>/QualitatNoConformitat.html"><fmt:message key="qualitat.menu.procedimentNoConformitats"/></a>
	</c:if>
	<c:if test="${not empty esEstAdministrador || not empty esEstEncarregat || not empty esEstTreballador}">
		<a class="menuItem" href="<%=request.getContextPath()%>/QualitatAvisos.html"><fmt:message key="qualitat.menu.avisos"/></a>
	</c:if>
</div>
</div>

<c:if test="${not empty esOlivaTaula}">
	<!-- Submenu Accions oliva de taula-->
	<div id="accionsOlivaTaula" class="menu">
		<div class="contenidoDesplegable" id="layAccionsOlivaTaula">
			<div class="supIzq"></div>
			<div class="supDer"></div>
			<div class="infDer"></div>
			<div class="infIzq"></div>
			<div class="bordeIzq"></div>
			<div class="bordeDer"></div>
			<div class="rellenoSup"></div>
			<div class="rellenoInf"></div>
			<a class="menuItem" 
				href="<%=request.getContextPath()%>/ProcesInici.html?tipusProces=16&amp;idOlivicultor=app">
				<fmt:message key="menu.acciones.entrada_oliva"/></a>
			<a class="menuItem" 
				href="<%=request.getContextPath()%>/ProcesInici.html?tipusProces=18">
				<fmt:message key="menu.acciones.entrada_fonoll"/></a>
			<a class="menuItem"
				href="<%=request.getContextPath()%>/ProcesInici.html?tipusProces=17">
				<fmt:message key="menu.acciones.elaboracio_oliva" /></a>
			<a class="menuItem"
				href="<%=request.getContextPath()%>/ProcesInici.html?tipusProces=19">
				<fmt:message key="menu.acciones.envasat_oliva" /></a>
			<a class="menuItem"
				href="<%=request.getContextPath()%>/ProcesInici.html?tipusProces=20">
				<fmt:message key="menu.acciones.etiquetat_oliva" /></a>
			<a class="menuItem"
				href="<%=request.getContextPath()%>/ProcesInici.html?tipusProces=22">
				Autocontrol oliva</a>
			<a class="menuItem"
				href="<%=request.getContextPath()%>/ProcesInici.html?tipusProces=21">
				<fmt:message key="menu.acciones.sortida_oliva" /></a>
			<a class="menuItem" 
				href="<%=request.getContextPath()%>/ProcesInici.html?tipusProces=24">
				<fmt:message key="menu.acciones.sortida_oliva_crua_granel" /></a>
			<a class="menuItem" 
				href="<%=request.getContextPath()%>/ProcesInici.html?tipusProces=25">
				<fmt:message key="menu.acciones.sortida_oliva_bota_granel" /></a>
				
				
				
				
		</div>
	</div>
	
	<!-- Submenu Consultes oliva de taula-->
	<div id="consultesOlivaTaula" class="menu">
		<div class="contenidoDesplegable" id="layConsultesOlivaTaula">
			<div class="supIzq"></div>
			<div class="supDer"></div>
			<div class="infDer"></div>
			<div class="infIzq"></div>
			<div class="bordeIzq"></div>
			<div class="bordeDer"></div>
			<div class="rellenoSup"></div>
			<div class="rellenoInf"></div>
			<a class="menuItem" href="<%=request.getContextPath()%>/ConsultaEntradaOlivaTaula.html"><fmt:message key="menu.consultas.entrada_oliva"/></a>
			<a class="menuItem" href="<%=request.getContextPath()%>/ConsultaEntradaFonoll.html"><fmt:message key="menu.consultas.entrada_fonoll"/></a>
			<a class="menuItem" href="<%=request.getContextPath()%>/ConsultaOlivaElaboradaLlistat.html"><fmt:message key="menu.consultas.oliva_elaborada" /></a>
			<a class="menuItem" href="<%=request.getContextPath()%>/ConsultaOlivaEnvasadaLlistat.html">Oliva envasada</a>
			<a class="menuItem" href="<%=request.getContextPath()%>/ConsultaOlivaTaulaDisponibleLlistat.html">Existències d'oliva de taula</a>
			<a class="menuItem" href="<%=request.getContextPath()%>/ConsultaOlivaTaulaComercialitzadaLlistat.html">Oliva de taula comercialitzada</a>
			<a class="menuItem" href="<%=request.getContextPath()%>/ProcesInici.html?tipusProces=23"><fmt:message key="menu.consultas.autocontrols" /></a>
			<a class="menuItem" href="<%=request.getContextPath()%>/ConsultaDeclaracioMensualOlivaTaula.html"><fmt:message key="menu.consultas.declaracioMensual" /></a>
			<a class="menuItem" 
				href=""
				onclick="return false;" 
				onmouseover="menuItemMouseover(event, 'consultesLlibresOlivaTaula');"> 
				<fmt:message key="menu.consultas.llibres"/>
				<span class="menuItemArrow">&#9654;</span>
			</a>
		</div>
	</div>
	
	<!-- Submenu Consultes-LlibresRegistre oliva de taula-->
	<div id="consultesLlibresOlivaTaula" class="menu">
		<div class="contenidoDesplegable" id="layConsultesLlibresOlivaTaula">
			<div class="supIzq"></div>
			<div class="supDer"></div>
			<div class="infDer"></div>
			<div class="infIzq"></div>
			<div class="bordeIzq"></div>
			<div class="bordeDer"></div>
			<div class="rellenoSup"></div>
			<div class="rellenoInf"></div>
			<a class="menuItem" href="<%=request.getContextPath()%>/ConsultaLlibreEntradaOlivaTaula.html"><fmt:message key="menu.consultas.llibres.entrada"/></a>
			<a class="menuItem" href="<%=request.getContextPath()%>/ConsultaLlibreElaboracioOlivaTaula.html"><fmt:message key="menu.consultas.llibres.elaboracio"/></a>
			<a class="menuItem" href="<%=request.getContextPath()%>/ConsultaLlibreSortidesOlivaTaula.html"><fmt:message key="menu.consultas.llibres.sortides"/></a>
		</div>
	</div>
</c:if>

<!-- Submenu Consultes oliva de taula consell regulador-->
<div id="consultesOlivaTaulaAdministracio" class="menu">
	<div class="contenidoDesplegable" id="layConsultesOlivaTaulaAdministracio">
		<div class="supIzq"></div>
		<div class="supDer"></div>
		<div class="infDer"></div>
		<div class="infIzq"></div>
		<div class="bordeIzq"></div>
		<div class="bordeDer"></div>
		<div class="rellenoSup"></div>
		<div class="rellenoInf"></div>
		<a class="menuItem" href="<%=request.getContextPath()%>/ConsultaEntradaOlivaTaulaEstablimentLlistat.html"><fmt:message key="menu.consultas.entrada_oliva"/></a>
		<a class="menuItem" href="<%=request.getContextPath()%>/ConsultaEntradaFonollEstablimentLlistat.html"><fmt:message key="menu.consultas.entrada_fonoll"/></a>
		<a class="menuItem" href="<%=request.getContextPath()%>/ConsultaOlivaElaboradaEstablimentLlistat.html"><fmt:message key="menu.consultas.oliva_elaborada" /></a>
		<a class="menuItem" href="<%=request.getContextPath()%>/ConsultaOlivaEnvasadaEstablimentLlistat.html">Oliva envasada</a>
		<a class="menuItem" href="<%=request.getContextPath()%>/ConsultaOlivaTaulaDisponibleEstablimentLlistat.html">Existències d'oliva de taula</a>
		<a class="menuItem" href="<%=request.getContextPath()%>/ConsultaOlivaTaulaComercialitzadaEstablimentLlistat.html">Oliva de taula comercialitzada</a>
		<a class="menuItem" href="<%=request.getContextPath()%>/ConsultaDeclaracioMensualOlivaTaula.html"><fmt:message key="menu.consultas.declaracioMensual" /></a>
		<a class="menuItem" 
				href=""
				onclick="return false;" 
				onmouseover="menuItemMouseover(event, 'consultesLlibresOlivaTaulaAdministracio');"> 
				<fmt:message key="menu.consultas.llibres"/>
				<span class="menuItemArrow">&#9654;</span>
		</a>
	</div>
</div>

<!-- Submenu Consultes-LlibresRegistre oliva de taula-->
	<div id="consultesLlibresOlivaTaulaAdministracio" class="menu">
		<div class="contenidoDesplegable" id="layConsultesLlibresOlivaTaula">
			<div class="supIzq"></div>
			<div class="supDer"></div>
			<div class="infDer"></div>
			<div class="infIzq"></div>
			<div class="bordeIzq"></div>
			<div class="bordeDer"></div>
			<div class="rellenoSup"></div>
			<div class="rellenoInf"></div>
			<a class="menuItem" href="<%=request.getContextPath()%>/ConsultaLlibreEntradaOlivaTaulaEstablimentLlistat.html"><fmt:message key="menu.consultas.llibres.entrada"/></a>
			<a class="menuItem" href="<%=request.getContextPath()%>/ConsultaLlibreElaboracioOlivaTaulaEstablimentLlistat.html"><fmt:message key="menu.consultas.llibres.elaboracio"/></a>
			<a class="menuItem" href="<%=request.getContextPath()%>/ConsultaLlibreSortidesOlivaTaulaEstablimentLlistat.html"><fmt:message key="menu.consultas.llibres.sortides"/></a>
		</div>
	</div>


<!-- Submenu Tramites Sistra-->
<div id="tramitesSistraMenu" class="menu">
	<div class="contenidoDesplegable" id="layTramitesSistra">
		<div class="supIzq"></div>
		<div class="supDer"></div>
		<div class="infDer"></div>
		<div class="infIzq"></div>
		<div class="bordeIzq"></div>
		<div class="bordeDer"></div>
		<div class="rellenoSup"></div>
		<div class="rellenoInf"></div>
		<a class="menuItem" href="http://oficina.limit.es:8088/sistrafront/protected/init.do?language=ca&modelo=DecMensual&version=0" target="_blank"><fmt:message key="menu.tramites.declaracion_mensual"/></a>
		<a class="menuItem" href="http://oficina.limit.es:8088/sistrafront/protected/init.do?language=ca&modelo=VolantCirc&version=0" target="_blank"><fmt:message key="menu.tramites.volant_circulacio"/></a>
		<a class="menuItem" href="http://oficina.limit.es:8088/sistrafront/protected/init.do?language=ca&modelo=DecAnual&version=0" target="_blank"><fmt:message key="menu.tramites.declaracio_anual"/></a>
	  	<a class="menuItem" href="http://oficina.limit.es:8088/sistrafront/protected/init.do?language=ca&modelo=AutEtiquet&version=0" target="_blank"><fmt:message key="menu.tramites.etiquetas"/></a>
		<a class="menuItem" href="http://oficina.limit.es:8088/sistrafront/protected/init.do?language=ca&modelo=ComInici&version=0" target="_blank"><fmt:message key="menu.tramites.inici_activitat"/></a>
		<a class="menuItem" href="http://oficina.limit.es:8088/sistrafront/protected/init.do?language=ca&modelo=Contraetiq&version=0" target="_blank"><fmt:message key="menu.tramites.contraetiquetes"/></a>
	</div>
</div>


<!-- Submenu Tramites Agencia Oli-->
<div id="tramitesAgenciaOliMenu" class="menu">
	<div class="contenidoDesplegable" id="layTramitesAgenciaOli">
		<div class="supIzq"></div>
		<div class="supDer"></div>
		<div class="infDer"></div>
		<div class="infIzq"></div>
		<div class="bordeIzq"></div>
		<div class="bordeDer"></div>
		<div class="rellenoSup"></div>
		<div class="rellenoInf"></div>
		<a class="menuItem" href="<%=request.getContextPath()%>/AgenciaOliEntradaOliva.html"><fmt:message key="menu.tramites.entradaOlives"/></a>
		<a class="menuItem" href="<%=request.getContextPath()%>/AgenciaOliSortidaOli.html"><fmt:message key="menu.tramites.sortidesOli"/></a>
		<a class="menuItem" href="<%=request.getContextPath()%>/AgenciaOliSortidaOrujo.html"><fmt:message key="menu.tramites.sortidesOrujo"/></a>
		<a class="menuItem" href="<%=request.getContextPath()%>/AgenciaOliResum.html"><fmt:message key="menu.tramites.resumMensual"/></a>
		<a class="menuItem" href="<%=request.getContextPath()%>/AgenciaOliDeclaracioMensual.html"><fmt:message key="menu.tramites.declaracioMensual"/></a>
	</div>
</div>


<!-- Submenu GestorDocumental -->
<div id="gestorDocumentalSubMenu" class="menu">
<div class="contenidoDesplegable" id="laySubGestorDocumental">
<div class="supIzq"></div>
<div class="supDer"></div>
<div class="infDer"></div>
<div class="infIzq"></div>
<div class="bordeIzq"></div>
<div class="bordeDer"></div>
<div class="rellenoSup"></div>
<div class="rellenoInf"></div>
	<c:if test="${not empty esEstAdministrador }">
		<a class="menuItem" href="<%=request.getContextPath()%>/GestorDocumentalCategoriaInformacio.html"><fmt:message key="menu.informacion.categoria" /></a>
		<a class="menuItem" href="<%=request.getContextPath()%>/GestorDocumentalGestionarInformacio.html"><fmt:message key="menu.informacion.informacion" /></a>
	</c:if>
</div>
</div>



<!-- Submenu Descripcio Instal·lacio -->
<div id="descripcioInstalacioSubMenu" class="menu">
<div class="contenidoDesplegable" id="laySubDescripcioInstalacio">
<div class="supIzq"></div>
<div class="supDer"></div>
<div class="infDer"></div>
<div class="infIzq"></div>
<div class="bordeIzq"></div>
<div class="bordeDer"></div>
<div class="rellenoSup"></div>
<div class="rellenoInf"></div>
	<a class="menuItem" href="<%=request.getContextPath()%>/QualitatDescripcioInstalacioForm.html">
		<fmt:message key="qualitat.submenu.descripcioInstalacio"/>
	</a>
	<a class="menuItem" href="<%=request.getContextPath()%>/QualitatDescripcioEquips.html">
		<fmt:message key="qualitat.submenu.descripcioEquips"/>
	</a>
	<a class="menuItem" href="<%=request.getContextPath()%>/QualitatDescripcioPersonal.html">
		<fmt:message key="qualitat.submenu.relacioPersonal"/>
	</a>
</div>
</div>

<!-- Submenu Pla de Formacio -->
<div id="plaFormacioSubMenu" class="menu">
<div class="contenidoDesplegable" id="laySubPlaFormacio">
<div class="supIzq"></div>
<div class="supDer"></div>
<div class="infDer"></div>
<div class="infIzq"></div>
<div class="bordeIzq"></div>
<div class="bordeDer"></div>
<div class="rellenoSup"></div>
<div class="rellenoInf"></div>
	<c:if test="${not empty esEstAdministrador || not empty esEstEncarregat || not empty esEstTreballador || not empty esEstConsulta}">
		<a class="menuItem" href="<%=request.getContextPath()%>/QualitatOrganigramaEmpresa.html"><fmt:message key="qualitat.submenu.organigramaEmpresa"/></a>
		<a class="menuItem"  href="<%=request.getContextPath()%>/QualitatPuestoTreball.html"><fmt:message key="qualitat.submenu.puestosTreball"/></a>
	</c:if>
	<c:if test="${not empty esEstAdministrador || not empty esEstEncarregat || not empty esEstTreballador}">
		<a class="menuItem" href="<%=request.getContextPath()%>/QualitatPlaFormacio.html"><fmt:message key="qualitat.submenu.plaFormacio"/></a>
	</c:if>
</div>
</div>


<!-- Submenu Pla Abastament Aigua -->
<div id="plaAbastamentAiguaSubMenu" class="menu">
<div class="contenidoDesplegable" id="laySubAbastamentAigua">
<div class="supIzq"></div>
<div class="supDer"></div>
<div class="infDer"></div>
<div class="infIzq"></div>
<div class="bordeIzq"></div>
<div class="bordeDer"></div>
<div class="rellenoSup"></div>
<div class="rellenoInf"></div>
	<a class="menuItem" href="<%=request.getContextPath()%>/QualitatDescripcioSubministreAiguaForm.html">
		<fmt:message key="qualitat.submenu.plaAbastamentAigua.descripcioSubministreAigua"/>
	</a>
	<a class="menuItem"  href="<%=request.getContextPath()%>/QualitatAiguaControlOrganolepticForm.html">
		<fmt:message key="qualitat.submenu.plaAbastamentAigua.controlClorOrganoleptic"/>
	</a>
	<a class="menuItem"  href="<%=request.getContextPath()%>/QualitatAiguaControlAnaliticForm.html">
		<fmt:message key="qualitat.submenu.plaAbastamentAigua.controlAnalitic"/>
	</a>
</div>
</div>


<!-- Submenu APPCC -->
<div id="aPPCCSubMenu" class="menu">
<div class="contenidoDesplegable" id="laySubAPPCC">
<div class="supIzq"></div>
<div class="supDer"></div>
<div class="infDer"></div>
<div class="infIzq"></div>
<div class="bordeIzq"></div>
<div class="bordeDer"></div>
<div class="rellenoSup"></div>
<div class="rellenoInf"></div>
	<c:if test="${not empty esEstAdministrador || not empty esEstEncarregat || not empty esEstTreballador || not empty esEstConsulta}">
		<a class="menuItem" href="<%=request.getContextPath()%>/QualitatAPPCCForm.html"><fmt:message key="qualitat.submenu.appcc"/></a>
	</c:if>
	<c:if test="${not empty esEstAdministrador}">
		<a class="menuItem" href="<%=request.getContextPath()%>/QualitatAPPCCEtapa.html"><fmt:message key="qualitat.submenu.appcc.etapes"/></a>
	</c:if>
	<c:if test="${not empty esEstAdministrador || not empty esEstEncarregat || not empty esEstTreballador}">
		<a class="menuItem"  href="<%=request.getContextPath()%>/QualitatAPPCCPerill.html"><fmt:message key="qualitat.submenu.appcc.puntsControl"/></a>
	</c:if>
	<c:if test="${not empty esEstAdministrador || not empty esEstEncarregat || not empty esEstTreballador || not empty esEstConsulta}">
		<a class="menuItem"  href="<%=request.getContextPath()%>/QualitatAPPCCFitxaControl.html"><fmt:message key="qualitat.submenu.appcc.fitxesControl"/></a>
	</c:if>
	<c:if test="${not empty esEstAdministrador || not empty esEstEncarregat || not empty esEstTreballador || not empty esEstConsulta}">
		<a class="menuItem"  href="<%=request.getContextPath()%>/QualitatAPPCCPlaVerificacio.html"><fmt:message key="qualitat.submenu.appcc.plaVerificacio"/></a>
	</c:if>
</div>
</div>



<!-- Configuración -->
<div id="configuracionMenu" class="menu">
<div class="contenidoDesplegable" id="layConfiguracion">
<div class="supIzq"></div>
<div class="supDer"></div>
<div class="infDer"></div>
<div class="infIzq"></div>
<div class="bordeIzq"></div>
<div class="bordeDer"></div>
<div class="rellenoSup"></div>
<div class="rellenoInf"></div>
<c:if test="${not empty esDoGestor || not empty esEstAdministrador}">
	<a class="menuItem" href="<%=request.getContextPath()%>/Usuari.html"><fmt:message
		key="menu.configuracion.usuario" /></a>
</c:if> 
<c:if test="${empty esDoGestor && empty esEstAdministrador && (not empty esAdministracio || not empty esDoControlador || not empty esProductor || not empty esEnvasador)}">
	<a class="menuItem"
		href="<%=request.getContextPath()%>/UsuariForm.html?id=<c:out value="${usuari.id}"/>"><fmt:message
		key="menu.configuracion.usuario" /></a>
</c:if> 
<c:if test="${not empty esDoGestor}">
	<a class="menuItem" href="<%=request.getContextPath()%>/TaxaForm.html"><fmt:message
		key="menu.configuracion.tasas" /></a>
</c:if> 
<c:if test="${not empty esDoGestor}">
	<a class="menuItem"
		href="<%=request.getContextPath()%>/TipusEnvas.html"><fmt:message
		key="menu.configuracion.tipo_envase" /></a>
</c:if> 
<c:if test="${not empty esDoGestor}">
	<a class="menuItem" href="<%=request.getContextPath()%>/Marca.html"><fmt:message
		key="menu.configuracion.marca_aceite" /></a>
</c:if> 
<c:if test="${not empty esDoGestor}">
	<a class="menuItem"
		href="<%=request.getContextPath()%>/Establiment.html"><fmt:message
		key="menu.configuracion.establecimiento" /></a>
</c:if> 
<c:if
	test="${empty esDoGestor && (not empty esProductor || not empty esEnvasador) && not empty establiment}">
	<a class="menuItem"
		href="<%=request.getContextPath()%>/EstablimentForm.html?id=<c:out value="${establiment.id}"/>"><fmt:message
		key="menu.configuracion.establecimiento" /></a>
</c:if> 
<c:if test="${not empty esDoGestor || not empty esDoControlador}">
	<a class="menuItem"
		href="<%=request.getContextPath()%>/Olivicultor.html"><fmt:message
		key="menu.configuracion.olivicultor" /></a>
</c:if>
<c:if test="${not empty esDoGestor}">
	<a class="menuItem"
		href="<%=request.getContextPath()%>/VarietatsExperimentals.html"><fmt:message
		key="menu.configuracion.varietats.experimentals" /></a>
</c:if>
<c:if test="${not empty esDoGestor}">
	<a class="menuItem"
		href="<%=request.getContextPath()%>/PaisForm.html"><fmt:message
		key="menu.configuracion.pais" /></a>
</c:if>
<c:if test="${empty esDoGestor && not empty esOlivicultor && not empty oliv}">
	<a class="menuItem"
		href="<%=request.getContextPath()%>/OlivicultorForm.html?id=<c:out value="${oliv.id}"/>"><fmt:message
		key="menu.configuracion.olivicultor" /></a>
</c:if> 
<c:if
	test="${(not empty esProductor || not empty esEnvasador) && not empty establiment}">
	<a class="menuItem" href="<%=request.getContextPath()%>/Zona.html"><fmt:message
		key="menu.configuracion.zona" /></a>
</c:if> 
<c:if
	test="${(not empty esProductor || not empty esEnvasador) && not empty establiment && not empty zonasActivas}">
	<a class="menuItem" href="<%=request.getContextPath()%>/Diposit.html"><fmt:message
		key="menu.configuracion.diposit" /></a>
</c:if> 
<!--c:if test="${not empty esDoGestor || not empty esAdministracio || not empty esDoControlador || not empty esProductor || not empty esEnvasador}"-->
<c:if test="${not empty esProductor || not empty esEnvasador}">
	<a class="menuItem" href="<%=request.getContextPath()%>/PartidaOli.html">
		<fmt:message key="menu.configuracion.partidaOli" />
	</a>
</c:if>
<c:if test="${(not empty esEnvasador)}">
	<a class="menuItem" href="<%=request.getContextPath()%>/Lot.html">
		<fmt:message key="menu.configuracion.lot" />
	</a>
</c:if>
<c:if test="${(not empty esProductor || not empty esEnvasador) && not empty establiment}">
	<a class="menuItem" href="<%=request.getContextPath()%>/Producte.html">
		<fmt:message key="menu.configuracion.producte" />
	</a>
</c:if>
<c:if test="${(not empty esTafona || not empty esEnvasador || not empty esOlivicultor) && not empty establiment}">	
	<a class="menuItem" href="<%=request.getContextPath()%>/Avis.html"><fmt:message key="menu.configuracion.avis" /></a>
</c:if>
<c:if test="${not empty esDoControlador || not empty esAdministracio}">
	<a class="menuItem" href="<%=request.getContextPath()%>/AnaliticaParametre.html"><fmt:message key="menu.configuracion.analiticaParametre" /></a>
</c:if> 
<c:if test="${not empty esDoGestor}">
	<a class="menuItem" href="<%=request.getContextPath()%>/GestioInfografia.html"><fmt:message key="menu.acciones.gestioInfografia" /></a>
</c:if>
<div class="separadorMenuItems"></div>
<c:if test="${not empty lang && lang == 'es'}">
	<a class="menuItem" href="javascript:void(0);"
		onclick="cambioIdioma('es'); return false;" id="idiomaActivo"><fmt:message
		key="menu.configuracion.idioma_castellano" /></a>
	<a class="menuItem" href="javascript:void(0);"
		onclick="cambioIdioma('ca'); return false;"><fmt:message
		key="menu.configuracion.idioma_catalan" /></a>
</c:if> 
<c:if test="${not empty lang && lang == 'ca'}">
	<a class="menuItem" href="javascript:void(0);"
		onclick="cambioIdioma('es'); return false;"><fmt:message
		key="menu.configuracion.idioma_castellano" /></a>
	<a class="menuItem" href="javascript:void(0);"
		onclick="cambioIdioma('ca'); return false;" id="idiomaActivo"><fmt:message
		key="menu.configuracion.idioma_catalan" /></a>
</c:if></div>
</div>

<div id="loginState">
<c:if test="${not empty usuari}">
		<div id="logout">
			<a href="<c:url value="/logout.html"/>">
				<img src="<c:url value="/img/menu/system-log-out.png"/>" border="0" alt="<fmt:message key="login.sortir"/>" title="<fmt:message key="login.sortir"/>"/>
			</a>
		</div>
	</c:if>
	<strong><fmt:message key="manteniment.usuari" /></strong>
	<span><c:out value="${usuari.usuari}" /></span>
</div>

<script type="text/javascript">
	jQuery(document).ready( function() {
		setTamanosDivs('layConfiguracion');
		setTamanosDivs('layAccion');
		setTamanosDivs('layConsultas');
		setTamanosDivs('layTramits');
		setTamanosDivs('layQualitat');
		setTamanosDivs('laySubGestorDocumental');
		setTamanosDivs('laySubDescripcioInstalacio');
		setTamanosDivs('laySubPlaFormacio');
		setTamanosDivs('laySubAbastamentAigua');
		setTamanosDivs('laySubAPPCC');
		setTamanosDivs('layTramitesSistra');
		setTamanosDivs('layAccionsOlivaTaula');
		setTamanosDivs('layConsultesOlivaTaula');
		setTamanosDivs('layConsultesOlivaTaulaAdministracio');
		setTamanosDivs('layConsultesLlibresOlivaTaula');
		setTamanosDivs('layTramitesAgenciaOli');
		<c:if test="${not empty esDoGestor || not empty esAdministracio || not empty esDoControlador }">
			setTamanosDivs('layInformacion');
		</c:if>
	}
	);
</script>





