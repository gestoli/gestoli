<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>

<html>
<head>
<title><c:choose>
	<c:when test="${not empty procsel}">
		<fmt:message key="${procsel.nomProces}" />
	</c:when>
	<c:otherwise>
		<fmt:message key="establiment.principal.titol" />
		<c:if test="${not empty establecimientNom}"> - <c:out
				value="${establecimientNom}" />
		</c:if>
	</c:otherwise>
</c:choose></title>

<script type="text/javascript" src="js/yahoo/yahoo.js"></script>
<script type="text/javascript" src="js/yahoo/dom.js"></script>
<script type="text/javascript" src="js/yahoo/event.js"></script>

<script type="text/javascript" src="dwr/engine.js"></script>
<script type="text/javascript" src="dwr/interface/contenidorService.js"></script>

<c:choose>
	<c:when test="${not zonaFicticia}">
		<link href="css/contextmenu.css" rel="stylesheet" type="text/css" />
		<link href="css/container.css" rel="stylesheet" type="text/css" />
	</c:when>
	<c:otherwise>
		<script type="text/javascript" src="js/displaytag.js"></script>
		<%-- <link href="css/displaytag.css" rel="stylesheet" type="text/css"/> --%>
	</c:otherwise>
</c:choose>
<%--	
    
	<c:if test="${not empty procsel}">
		<script type="text/javascript" src="js/form.js"></script>
		<script type="text/javascript" src="js/jscalendar/calendar_stripped.js"></script>
		<script type="text/javascript" src="js/jscalendar/calendar-setup_stripped.js"></script>
		<script type="text/javascript" src="js/jscalendar/lang/<fmt:message key="webapp.calendar"/>"></script>
		<link href="js/jscalendar/calendar-viti.css" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" src="js/selectbox.js"></script>
	</c:if>
--%>


<c:if test="${not zonaFicticia}">
	<style type="text/css">
div.contdr {
	float: left;
	padding: 2px;
	border: 2px solid #ccc;
	max-width: 75px;
}

div.contdr-sel {
	border: 2px solid red;
}

div.contdr div.contdr-img {
	float: left;
	background-repeat: no-repeat;
}

div.contdr img.contdr-img {
	float: left;
	background-repeat: no-repeat;
}

div.contdr div.contdr-per {
	width: 4px;
	border: 1px solid #666;
	margin: 0 0 0 1px;
	float: left;
}

div.contdr div.contdr-peri {
	background-color: white;
}
</style>
</c:if>

<script type="text/javascript" language="javascript">
// <![CDATA[
  	var iconesTaula = new Array(	"img/icons/oliva_taula.gif",
  									"img/icons/oliva_taula.gif",
  		  							"img/icons/oliva_taula_negra.gif", 
									"img/icons/botes_verda.png",
									"img/icons/botes_trencada.png",
									"img/icons/botes_negra.png", 
									"img/icons/lotOlivaVerda.png",
									"img/icons/lotOlivaTrencada.png",
									"img/icons/lotOlivaNegra.png",
									"img/icons/botes_verda_eco.png"); 
	
	function canviSeleccio(id) {
		var accio = '<c:out value="${procsel.accio}"/>';
		var esDiposit = '<c:out value="${procsel.mostrarDiposit}"/>';
		var esPartida = '<c:out value="${procsel.mostrarPartida}"/>';
		var esLote = '<c:out value="${procsel.mostrarLote}"/>';
		var esBota = '<c:out value="${procsel.mostrarBotes}"/>';
		var tipoCont = "0";
				
		if(esLote == 'true' && esDiposit == 'true'){
			tipoCont = "3";
		}else if(esDiposit == 'true'){
			tipoCont = "0"; 
		}else if(esPartida == 'true'){
			tipoCont = "1";
		}else if(esLote == 'true'){
			tipoCont = "2";
		}else if(esBota == 'true'){
			tipoCont = "4";
		}
		// contenidorService.seleccionar(id, accio, esDiposit, mostraSeleccio);
		contenidorService.seleccionar(id, accio, tipoCont, mostraSeleccio);
		
	}
	function mostraSeleccio(seleccio) {
	    var aqui = document.getElementById("seleccio");
	    while(aqui.childNodes.length > 0)
	    	aqui.removeChild(aqui.childNodes[0]);
	    var tipus = new Array();
	    var ordenats = new Array();
	    for (var i = 0; i < seleccio.length; i++) {
	        
	       
		        var found = 0;
		    	for (var j = 0; j < tipus.length; j++)
		    		if (seleccio[i].tipo == tipus[j]) {
		    			found = 1;
		    			break;
		    		}
		    	var index = seleccio[i].tipo;
		    	if (!found) {
		    		tipus[tipus.length] = index;
		    		ordenats[index] = new Array();
		    		ordenats[index][0] = seleccio[i];
		    	} else {
		    		ordenats[index][ordenats[index].length] = seleccio[i];
		    	}
	    	
	    }
	    var newul = document.createElement('UL');
		for (var i = 0; i < tipus.length; i++) {
			var newli = document.createElement('LI');
			for (var j = 0; j < ordenats[tipus[i]].length; j++) {
			    var icona = 'ArxiuMostrar.html?id=' + ordenats[tipus[i]][j].tipo;
			    if (ordenats[tipus[i]][j].tipo > 99) {
					icona = iconesTaula[ordenats[tipus[i]][j].tipo - 100];
				}
				var img = new Image();
				img.src = icona;
				var newimg = document.createElement('IMG');
				newimg.style.width = img.width / 2 + "px";
				newimg.style.height = img.height / 2 + "px";
				newimg.setAttribute('width', img.width / 2);
				newimg.setAttribute('height', img.height / 2);
				newimg.src = icona;
				newimg.title = ordenats[tipus[i]][j].codiAssignat;
				newli.appendChild(newimg);
			}
			newul.appendChild(newli);
		}
		aqui.appendChild(newul);
	}
	
	function esborrarSeleccioFinestra() {
		var accio = '<c:out value="${procsel.accio}"/>';
		contenidorService.seleccioEsborrar(accio, mostraSeleccio);
		if ("mapaEstabliment" in window) mapaEstabliment.deseleccionarTot();
		var inputs = document.getElementsByTagName('INPUT');
		for (var i = 0; i < inputs.length; i++) {
			if (inputs[i].getAttribute("type") == "checkbox")
				inputs[i].checked = false;
		}
	}
	
	function seleccioInit() {		
		<c:if test="${not empty procsel}">
			var accio = '<c:out value="${procsel.accio}"/>';
			var esDiposit = '<c:out value="${procsel.mostrarDiposit}"/>';
			var esPartida = '<c:out value="${procsel.mostrarPartida}"/>';
			var esLote = '<c:out value="${procsel.mostrarLote}"/>';
			var esBota = '<c:out value="${procsel.mostrarBotes}"/>';
			var tipoCont = "0";
			
			if(esDiposit == 'true' && esLote == 'true'){
				contenidorService.seleccioLlistat(accio, 'N', 'S', 'S', 'N', mostraSeleccio);
			}else if(esDiposit == 'true'){
				contenidorService.seleccioLlistat(accio, 'N', 'S', 'N', 'N', mostraSeleccio);
			}else if(esPartida == 'true'){
				contenidorService.seleccioLlistat(accio, 'S', 'N', 'N', 'N', mostraSeleccio);
			}else if(esLote == 'true'){
				contenidorService.seleccioLlistat(accio, 'N', 'N', 'S', 'N', mostraSeleccio);
			}else if(esBota == 'true'){
				contenidorService.seleccioLlistat(accio, 'N', 'N', 'N', 'S', mostraSeleccio);
			}
					
		    
		    var eel = document.getElementById("seleccioEsborrar");
		    eel.onclick = esborrarSeleccioFinestra;
		    document.getElementById("bloc_seleccio").style.display = "";
		</c:if>
		<c:if test="${not zonaFicticia}">
		    document.getElementById("img_carregant").style.display = "none";
		    document.getElementById("img_zona_amagat").style.display = "";
		</c:if>		
	}
	
	YAHOO.util.Event.addListener(
		window,
		"load",
		seleccioInit);
// ]]>
</script>

</head>
<body>
<c:if test="${not empty establimentEx}">
	<c:set var="establimentId" value="${establimentEx}" />
</c:if>
<c:if test="${not empty establiment}">
	<div id="fichaEstablecimiento"><!-- Menu superior ficha establecimiento -->
	<div class="menufichaEstablecimiento"><span> <c:forEach
		var="zona" items="${zones}">
		<c:if test="${zona.establiment.id == establiment.id}">
			<c:choose>
				<c:when test="${zonaId != 'NS' && zona.id == zonaId}">
					<strong><c:out value="${zona.nom}" /></strong>
				</c:when>
				<c:otherwise>
					<c:url var="urlZona" value="ProcesInici.html">
						<c:param name="establimentId" value="${establiment.id}" />
						<c:param name="zonaId" value="${zona.id}" />
						<c:param name="cambizona" value="t" />
						<c:if test="${not empty procsel}">
							<c:param name="tipusProces" value="${procsel.tipusProces}" />
							<c:if test="${not empty param.pas}">
								<c:param name="pas" value="${param.pas}" />
							</c:if>
							<c:if test="${empty param.pas}">
								<c:param name="pas" value="0" />
							</c:if>
							<c:param name="accio" value="${procsel.accio}" />
						</c:if>
						<c:if test="${not empty param.fromEstabliment}">
							<c:param name="fromEstabliment" value="${param.fromEstabliment}" />
						</c:if>
					</c:url>
					<a href="<c:out value="${urlZona}"/>"><c:out
						value="${zona.nom}" /></a>
				</c:otherwise>



			</c:choose>
		</c:if>
	</c:forEach> </span> <%--CARLOS: MAQUETACION DE LA CAJA DE SELECCION --%> <c:if
		test="${not empty procsel}">
		<div id="bloc_seleccio">
		<h4 class="seleccio">
			<span class="capa1"></span> <span class="capa2"></span> 
			<c:choose>
				<c:when test="${procsel.accio == 'ORIGEN'}">
					<fmt:message key="menu.seleccio.origen" />
				</c:when>
				<c:when test="${procsel.accio == 'DESTI'}">
					<fmt:message key="menu.seleccio.desti" />
				</c:when>
			</c:choose>
		</h4>

		<div class="capa1">
		<div class="capa2">
		<div class="capa3">
		<div id="seleccio">
		<ul></ul>
		</div>
		</div>
		</div>
		</div>
		<%-- 	ESTA LINEA NO ESTABA EN LA MAQUETACION--%>
		<div style="display: none">
		<form class="solitari" action=""><input id="seleccioEsborrar"
			type="button" value="esborrar" class="inputButtonPetit" /></form>
		</div>
		</div>
	</c:if> <c:if test="${empty procsel}">
		<div id="bloc_seleccio" class="vacio"></div>
	</c:if> 
	<%--CARLOS:FIN  MAQUETACION DE LA CAJA DE SELECCION --%> 
	<c:if test="${not empty procsel}">
		<form id="siguienteForm" action="ProcesInici.html" class="seguit">
			<input type="hidden" id="tipusProces" name="tipusProces" value="<c:out value="${procsel.tipusProces}"/>" /> 
			<input type="hidden" id="pas" name="pas" value="<c:out value="${procsel.pas}"/>" /> 
			<input type="hidden" id="zonaId" name="zonaId" value="<c:out value="${zonaId}"/>" /> 
			<input type="hidden" id="establimentId" name="establimentId" value="<c:out value="${establimentId}"/>" /> 
			<c:if test="${not empty param.fromEstabliment}">
				<input type="hidden" id="fromEstabliment" name="fromEstabliment" value="<c:out value="${param.fromEstabliment}"/>" />
			</c:if>
		</form>
		<form id="cambioZonaForm" action="ProcesInici.html" class="seguit">
			<input type="hidden" id="tipusProces" name="tipusProces" value="<c:out value="${procsel.tipusProces}"/>" /> 
			<input type="hidden" id="pas" name="pas" value="1" /> 
			<input type="hidden" id="zonaId" name="zonaId" value="<c:out value="${zonaId}"/>" /> 
			<input type="hidden" id="establimentId" name="establimentId" value="<c:out value="${establimentId}"/>" /> 
			<c:if test="${not empty param.fromEstabliment}">
				<input type="hidden" id="fromEstabliment" name="fromEstabliment" value="<c:out value="${param.fromEstabliment}"/>" />
			</c:if>
			</form>
		<form id="sortidaForm" action="ProcesInici.html" class="seguit">
			<input type="hidden" id="tipusProces" name="tipusProces" value="<c:out value="${procsel.tipusProces}"/>" /> 
			<input type="hidden" id="pas" name="pas" value="2" /> 
			<input type="hidden" id="zonaId" name="zonaId" value="<c:out value="${zonaId}"/>" /> 
			<input type="hidden" id="establimentId" name="establimentId" value="<c:out value="${establimentId}"/>" /> 
			<c:if test="${not empty param.fromEstabliment}">
				<input type="hidden" id="fromEstabliment" name="fromEstabliment" value="<c:out value="${param.fromEstabliment}"/>" />
			</c:if>
		</form>
		<c:if test="${procsel.pas > 1}">
			<form id="tornarForm" action="ProcesInici.html" class="seguit">
				<input type="hidden" id="tipusProces" name="tipusProces" value="<c:out value="${procsel.tipusProces}"/>" /> 
				<input type="hidden" id="pas" name="pas" value="<c:out value="${procsel.pas - 2}"/>" /> 
				<c:if test="${not empty param.fromEstabliment}">
					<input type="hidden" id="fromEstabliment" name="fromEstabliment" value="<c:out value="${param.fromEstabliment}"/>" />
				</c:if>
			</form>
		</c:if>
	</c:if>

	<div class="cajaBotones"><%-- 
					<form class="seguit">
						<input type="button" class="inputSubmit" value="Imprimir" onclick="window.print()"/>
					</form>
					--%> <c:if test="${not empty tipusProces && tipusProces==1}">
		<div class="btnCorto" onclick="document.location = 'Diposit.html';">
			<a href="javascript:void(0);"><fmt:message key="manteniment.aceptar" /></a>
		</div>
	</c:if> <c:if test="${not empty procsel && tipusProces != 9 && tipusProces != 21}">
		<div class="btnCorto" onclick="submitForm('siguienteForm')" onmouseover="underline('enlaceSiguienteForm')" onmouseout="underline('enlaceSiguienteForm')">
			<a id="enlaceSiguienteForm" href="javascript:void(0);"><fmt:message key="proces.seguent" /></a>
		</div>
		<c:if test="${procsel.pas > 1}">
			<div class="btnCorto" onclick="submitForm('tornarForm')" onmouseover="underline('enlaceTornarForm')" onmouseout="underline('enlaceTornarForm')">
				<a id="enlaceTornarForm" href="javascript:void(0);"><fmt:message key="proces.tornar" /></a>
			</div>
		</c:if>
		<c:choose>
			<c:when test="${not empty esDoControlador}">
				<div id="cancelarForm" class="btnCorto" onmouseover="underline('enlaceCancelarForm')" onmouseout="underline('enlaceCancelarForm')" onclick="document.location ='Establiment.html?analitica=t';">
					<a id="enlaceCancelarForm" href="javascript:void(0);"><fmt:message key="proces.cancelar" /></a>
				</div>
			</c:when>
			<c:when test="${empty esDoGestor}">
				<div id="cancelarForm" class="btnCorto" onmouseover="underline('enlaceCancelarForm')" onmouseout="underline('enlaceCancelarForm')" onclick="document.location ='ProcesInici.html';">
					<a id="enlaceCancelarForm" href="javascript:void(0);"><fmt:message key="proces.cancelar" /></a>
				</div>
			</c:when>
			<c:when test="${not empty esDoGestor && empty param.fromEstabliment }">
				<div id="cancelarForm" class="btnCorto" onmouseover="underline('enlaceCancelarForm')" onmouseout="underline('enlaceCancelarForm')" onclick="document.location ='Establiment.html?analitica=t';">
					<a id="enlaceCancelarForm" href="javascript:void(0);"><fmt:message key="proces.cancelar" /></a>
				</div>
			</c:when>
			<c:when test="${not empty param.fromEstabliment }">
				<div id="cancelarForm" class="btnCorto" onmouseover="underline('enlaceCancelarForm')" onmouseout="underline('enlaceCancelarForm')" onclick="document.location ='ConsultaAnaliticasEstablimentLlistat.html';">
					<a id="enlaceCancelarForm" href="javascript:void(0);"><fmt:message key="proces.cancelar" /></a>
				</div>
			</c:when>
		</c:choose>

	</c:if> 
	<c:if test="${not empty procsel && (tipusProces == 9 || tipusProces == 21)}">

		<c:if test="${not empty esEnvasador}">
			<div class="btnLargo" onclick="submitForm('cambioZonaForm')" onmouseover="underline('enlaceCambioZonaForm')" onmouseout="underline('enlaceCambioZonaForm')"><a id="enlaceCambioZonaForm" href="javascript:void(0);"><fmt:message key="proces.cambioZona" /></a>
			</div>
		</c:if>

		<div class="btnCorto" onclick="submitForm('sortidaForm')" onmouseover="underline('enlaceSortidaForm')" onmouseout="underline('enlaceSortidaForm')">
			<a id="enlaceSortidaForm" href="javascript:void(0);"><fmt:message key="proces.sortida" /></a>
		</div>
		<div id="cancelarForm" class="btnCorto" onmouseover="underline('enlaceCancelarForm')" onmouseout="underline('enlaceCancelarForm')" onclick="document.location ='ProcesInici.html';">
			<a id="enlaceCancelarForm" href="javascript:void(0);"><fmt:message key="proces.cancelar" /></a>
		</div>
	</c:if> 
	<c:if test="${not zonaFicticia}">
		<div class="btnCorto" onclick="window.print();return false;">
			<a href="javascript:void(0);"><fmt:message key="proces.imprimir" /></a>
		</div>
	</c:if></div>

	</div>

	<c:choose>
		<c:when test="${zonaFicticia}">
			<div class="listadepositos"><c:import url="comu/VistaEstablimentNoSituats.jsp" /></div>
		</c:when>
		<c:otherwise>
			<div id="img_carregant" style="text-align: center; margin-top: 25px">
				<img src="img/elems/carregant.gif" alt="carregant" title="carregant" />
			</div>
			<div id="img_zona_amagat" style="display: none; margin-top: 0px">
				<c:import url="comu/VistaEstabliment.jsp">
					<c:param name="seleccionable" value="true" />
				</c:import>
			</div>
		</c:otherwise>
	</c:choose></div>
	<%-- FIN DE fichaEstablecimiento --%>
	<div class="llistatPrint">
		<%-- DIPOSITS --%>
		<div>
		<div><h2><fmt:message key="establiment.principal.titol.diposits" /></h2></div>
		<display:table name="diposits" requestURI="" id="diposit"  sort="list" cellpadding="0" cellspacing="0" class="listadoAncho selectable" defaultsort="1">
			<display:column property="codiAssignat" titleKey="establiment.principal.codiAssignat" sortable="false" headerClass="ancho160" />
			<display:column property="capacitat" titleKey="establiment.principal.capacitat" sortable="false" headerClass="ancho160" />
			<display:column property="materialDiposit.nom" titleKey="establiment.principal.material" sortable="false" headerClass="ancho160" />
			<display:column property="volumActual" titleKey="establiment.principal.volumActual" sortable="false" headerClass="ancho160" />
			<display:column property="acidesa" titleKey="establiment.principal.acidesa" sortable="false" headerClass="ancho160" />
			<display:column property="partidaOli.nom" titleKey="establiment.principal.partida" sortable="false" headerClass="ancho160" />
			<display:column property="partidaOli.categoriaOli.nom" titleKey="establiment.principal.categoria" sortable="false" headerClass="ancho160" />
		</display:table>
		</div>
		<div style="clear:both;"></div>
		
		<%-- LOTS --%>
		<div>
		<div><h2><fmt:message key="establiment.principal.titol.lot" /></h2></div>
		<display:table name="lotes" requestURI="" id="lot"  sort="list" cellpadding="0" cellspacing="0" class="listadoAncho selectable" defaultsort="1">
			<display:column property="etiquetatge.marca.nom" titleKey="establiment.principal.marca" sortable="false" headerClass="ancho160" />
			<display:column property="etiquetatge.tipusEnvas.nomSelect" titleKey="establiment.principal.tipusEnvas" sortable="false" headerClass="ancho160" />
			<display:column property="partidaOli.nom" titleKey="establiment.principal.partida" sortable="false" headerClass="ancho160" />
			<display:column property="partidaOli.categoriaOli.nom" titleKey="establiment.principal.categoria" sortable="false" headerClass="ancho160" />
			<display:column property="numeroBotellesInicials" titleKey="establiment.principal.botellesInicials" sortable="false" headerClass="ancho160" />
			<display:column property="numeroBotellesActuals" titleKey="establiment.principal.botellesActuals" sortable="false" headerClass="ancho160" />
			<display:column property="dataConsumFormat" titleKey="establiment.principal.dataConsum" sortable="false" headerClass="ancho160" />
		</display:table>
		</div>
		<div style="clear:both;"></div>
		
		<%-- PARTIDES OLIVA --%>
		<div>
		<div><h2><fmt:message key="establiment.principal.titol.partides" /></h2></div>
		<display:table name="partides" requestURI="" id="partida"  sort="list" cellpadding="0" cellspacing="0" class="listadoAncho selectable" defaultsort="1">
			<display:column property="dataFormat" titleKey="establiment.principal.data" sortable="false" headerClass="ancho160" />
			<display:column property="codiAssignat" titleKey="establiment.principal.codiAssignat" sortable="false" headerClass="ancho160" />
			<display:column property="olivicultor.nom" titleKey="establiment.principal.olivicultor" sortable="false" headerClass="ancho160" />
			<display:column property="totalQuilosDesglosats" titleKey="establiment.principal.varietats" sortable="false" headerClass="ancho160" />
		</display:table>
		</div>
		
		<script type="text/javascript">
		$(document).ready(function(){
			setEstilosTabla();
		})
		</script>
	</div>
</c:if>
</body>
</html>
