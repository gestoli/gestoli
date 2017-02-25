<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>


<html>
<head>

<title><fmt:message key="consulta.agrupacioConsultes.title" /></title>
<%--
    <script type="text/javascript" src="js/form.js"></script>
    <script type="text/javascript" src="js/jscalendar/calendar_stripped.js"></script>
	<script type="text/javascript" src="js/jscalendar/calendar-setup_stripped.js"></script>
	<script type="text/javascript" src="js/jscalendar/lang/<fmt:message key="webapp.calendar"/>"></script>
	<script type="text/javascript" src="js/selectbox.js"></script>
	<link type="text/css" href="js/jscalendar/calendar-viti.css" rel="stylesheet"/>
--%>

<script type="text/javascript" src="js/calendar/calendar.js"></script>
<script type="text/javascript" src="js/calendar/calendar-setup.js"></script>
<script type="text/javascript" src="js/calendar/lang/calendar-es.js"></script>
<link href="js/calendar/calendar-viti.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/form.js"></script>

<script type="text/javascript">
/* Canvi establiment*/
function canviEstabliment(idEstabliment){
	window.location = "ConsultaAgrupacioConsultes.html?idEst="+idEstabliment;
}

/* Camps formulari */

function amagaTot(){
	document.getElementById("divDataInici").style.display = "none";
	document.getElementById("divDataFi").style.display = "none";
	document.getElementById("divData").style.display = "none";
	document.getElementById("divLlibres").style.display = "none";
	document.getElementById("divMesura").style.display = "none";
	document.getElementById("divTipus").style.display = "none";
	document.getElementById("divOlivicultors").style.display = "none";
}

function mostraDataInici(){
	document.getElementById("divDataInici").style.display = "block";
}

function mostraDataFi(){
	document.getElementById("divDataFi").style.display = "block";
}

function mostraData(){
	document.getElementById("divData").style.display = "block";
}

function mostraLlibres(){
	document.getElementById("divLlibres").style.display = "block";
}

function mostraMesura(){
	document.getElementById("divMesura").style.display = "block";
}

function mostraTipus(){
	document.getElementById("divTipus").style.display = "block";
}

function mostraOlivicultors(){
	document.getElementById("divOlivicultors").style.display = "block";
}

/*
   Valor de tipus:
	 - 6: llibres de control
	 -15: declaració mensual
 */
function canviTipusPdf(tipus){
	document.getElementById("tipus").value=tipus;
}

/* Consultes */
function getParametresConsulta(consulta){
	if(consulta=="0") capConsulta();
	else if(consulta=="1") consultaLlibres();
	else if(consulta=="2") consultaEntradesOliva();
	else if(consulta=="3") consultaDeclaracioMensual();
	else if(consulta=="4") consultaOliElaborat();
	else if(consulta=="5") consultaDocRendiment();
	else if(consulta=="6") consultaEmbotellat();
	else if(consulta=="7") consultaOliDisponible();
	else if(consulta=="8") consultaMovimentsEntreEstabliments();
	else if(consulta=="9") consultaEtiquetatgesMarques();
	else if(consulta=="10") consultaEtiquetes();
	else if(consulta=="11") consultaTrasabilitatOlivicultor();
	else if(consulta=="12") consultaOliComercialitzat();
	else if(consulta=="13") consultaAnalitiques();
}
	
function capConsulta(){
	amagaTot();	
}
	
function consultaLlibres(){
	amagaTot();
	mostraDataInici();
	mostraDataFi();
	mostraLlibres();
	mostraMesura();
	canviTipusPdf("6");
	document.getElementById("formulario").method="post";
	document.getElementById("formulario").action="<%=request.getContextPath()%>/GenerarPdf.html";
}

function consultaEntradesOliva(){
	amagaTot();
	mostraDataInici();
	mostraDataFi();
	document.getElementById("formulario").method="post";
	document.getElementById("formulario").action="ConsultaLlistat.html";
}

function consultaDeclaracioMensual(){
	amagaTot();
	mostraDataInici();
	mostraDataFi();
	canviTipusPdf("15");
	document.getElementById("formulario").method="post";
	document.getElementById("formulario").action="<%=request.getContextPath()%>/GenerarPdf.html";
}

function consultaOliElaborat(){
	amagaTot();
	mostraDataInici();
	mostraDataFi();
	mostraMesura();
	document.getElementById("formulario").method="post";
	document.getElementById("formulario").action="ConsultaOliElaboratLlistat.html";
}

function consultaDocRendiment(){
	amagaTot();
	mostraDataInici();
	mostraDataFi();
	mostraMesura();
	document.getElementById("formulario").method="post";
	document.getElementById("formulario").action="ConsultaDocRendimentLlistat.html";
}

function consultaEmbotellat(){
	amagaTot();
	mostraDataInici();
	mostraMesura();
	document.getElementById("formulario").method="post";
	document.getElementById("formulario").action="ConsultaOliEmbotellatLlistat.html";
}

function consultaOliDisponible(){
	amagaTot();
	mostraData();
	mostraMesura();
	mostraTipus();
	document.getElementById("formulario").method="post";
	document.getElementById("formulario").action="ConsultaOliDisponibleLlistat.html";
}

function consultaMovimentsEntreEstabliments(){
	amagaTot();
	mostraDataInici();
	mostraDataFi();
	document.getElementById("formulario").method="post";
	document.getElementById("formulario").action="ConsultaMovimentsEntreEstablimentsLlistat.html";
}

function consultaEtiquetatgesMarques(){
	amagaTot();
	mostraDataInici();
	mostraDataFi();
	document.getElementById("formulario").method="post";
	document.getElementById("formulario").action="ConsultaEtiquetatgeMarcaLlistat.html";
}

function consultaEtiquetes(){
	amagaTot();
	document.getElementById("formulario").method="get";
	document.getElementById("formulario").action="ConsultaEtiquetesLlistat.html?idEstabliment=<c:out value="${param.idEst}"/>&fromEstabliment=true";
}

function consultaTrasabilitatOlivicultor(){
	amagaTot();
	mostraDataInici();
	mostraDataFi();
	mostraOlivicultors();
	document.getElementById("formulario").method="post";
	document.getElementById("formulario").action="ConsultaTrasabilitatOlivicultorLlistat.html";
}

function consultaOliComercialitzat(){
	amagaTot();
	mostraDataInici();
	mostraDataFi();
	document.getElementById("formulario").method="post";
	document.getElementById("formulario").action="ConsultaOliComercialitzatLlistat.html";
}

function consultaAnalitiques(){
	amagaTot();
	document.getElementById("formulario").method="get";
	document.getElementById("formulario").action="ConsultaAnaliticasSimplificatLlistat.html?idEstabliment=<c:out value="${param.idEst}"/>&fromEstabliment=true";
}

</script>

</head>
<body>

<form id="formulario" action="" method="post" class="extended seguit" target="_blank">
	<input type="hidden" id="fromEstabliment" name="fromEstabliment" value="true" /> 
	<input type="hidden" id="tipus" name="tipus" value="" /> 
	<input type="hidden" id="idEst" name="idEst" value="${param.idEst}" />
	

	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="select" />
		<c:param name="path" value="formData.idEstabliment" />
		<c:param name="title"><fmt:message key="consulta.agrupacioConsultes.establiment" /></c:param>
		<c:param name="camp" value="idEstabliment" />
		<c:param name="required" value="required" />
		<c:param name="selectItems" value="establiments" />
		<c:param name="selectItemsId" value="id" />
		<c:param name="selectItemsValue" value="nom" />
		<c:param name="selectSelectedValue" value="${param.idEst}" />
		<c:param name="clase" value="campoFormTresCuartos" />
		<c:param name="onchange" value="canviEstabliment(this.value);" />
	</c:import>

<c:if test="${not empty param.idEst}">
	<div class="separadorH"></div>
	
	<div class="campoFormTresCuartos ">
		<select name="tipusconsulta" id="tipusconsulta" onchange='getParametresConsulta(document.getElementById("tipusconsulta").value);'>
			<option value="0" selected="selected">- - - - - - -</option>
			<option value="1"><fmt:message key="consulta.llibres.title" /></option>
			<option value="2"><fmt:message key="consulta.entradaOliva.titol" /></option>
			<option value="3"><fmt:message key="consulta.declaracioMensual.title" /></option>
			<option value="4"><fmt:message key="consulta.oliElaborat.titol" /></option>
			<option value="5"><fmt:message key="consulta.docRendiment.titol" /></option>
			<option value="6"><fmt:message key="menu.consultas.aceite_embotellado" /></option>
			<option value="7"><fmt:message key="consulta.oliDisponible.titol" /></option>
			<option value="8"><fmt:message key="consulta.movimentsEntreEstabliments.titol" /></option>
			<option value="9"><fmt:message key="consulta.etiquetatgeMarca.titol" /></option>
			<option value="10"><fmt:message key="consulta.etiquetes.titol" /></option>
			<option value="11"><fmt:message key="consulta.trasabilitatOlivicultor.titol" /></option>
			<option value="12"><fmt:message key="consulta.oliComercialitzat.titol" /></option>
			<option value="13"><fmt:message key="consulta.analiticas.titol" /></option>
		</select>
	</div>
	
	<div class="separadorH"></div>
	
	<div id="divTipus" style="display:none;">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="select" />
			<c:param name="path" value="formData.categoriaOli" />
			<c:param name="title">
				<fmt:message key="consulta.agrupacioConsultes.camp.categoria" />
			</c:param>
			<c:param name="camp" value="categoriaOli" />
			<c:param name="required" value="required" />
			<c:param name="selectItems" value="categoriasOli" />
			<c:param name="selectItemsId" value="id" />
			<c:param name="selectItemsValue" value="nom" />
			<%-- c:param name="selectSelectedValue" value="${formData.categoriaOli}" /--%>
			<%-- <c:param name="ambOpcioBuida" value="false"/> --%>
			<c:param name="clase" value="campoFormTresCuartos" />
		</c:import>
	</div>
		
	<div class="separadorH"></div>
	
	<div id="divDataInici" style="display:none;">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="calendar" />
			<c:param name="path" value="formData.dataInici" />
			<c:param name="required" value="required" />
			<c:param name="title"><fmt:message key="consulta.camp.dataInici" /></c:param>
			<c:param name="camp" value="dataInici" />
			<c:param name="maxlength" value="10" />
			<c:param name="aclaracio">
				<fmt:message key="consulta.aclaracio.formatData" />
			</c:param>
			<%--c:param name="readonly" value="1" /--%>
			<c:param name="clase" value="conMargen campoForm165 " />
		</c:import> 
	</div>
	
	<div id="divDataFi" style="display:none;">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="calendar" />
			<c:param name="path" value="formData.dataFi" />
			<c:param name="required" value="required" />
			<c:param name="title"><fmt:message key="consulta.camp.dataFi" /></c:param>
			<c:param name="camp" value="dataFi" />
			<c:param name="maxlength" value="10" />
			<c:param name="aclaracio"><fmt:message key="consulta.aclaracio.formatData" /></c:param>
			<%--c:param name="readonly" value="1" /--%>
			<c:param name="clase" value="campoForm165" />
		</c:import>
	</div>
	
	<div id="divData" style="display:none;">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="calendar" />
			<c:param name="path" value="formData.data" />
			<c:param name="required" value="required" />
			<c:param name="title"><fmt:message key="consulta.camp.data" /></c:param>
			<c:param name="camp" value="data" />
			<c:param name="maxlength" value="10" />
			<c:param name="aclaracio"><fmt:message key="consulta.aclaracio.formatData" /></c:param>
			<%--c:param name="readonly" value="1" /--%>
			<c:param name="clase" value="campoForm165" />
		</c:import>
	</div>

<div class="separadorH"></div>

<div id="divLlibres" style="display:none;">
<c:import url="comu/CampFormulari.jsp">
	<c:param name="tipus" value="select" />
	<c:param name="path" value="formData.tipusLlibre" />
	<c:param name="title"><fmt:message key="consulta.agrupacioConsultes.llibre" /></c:param>
	<c:param name="camp" value="tipusLlibre" />
	<c:param name="required" value="required" />
	<c:param name="selectItems" value="llibres" />
	<c:param name="selectItemsId" value="val" />
	<c:param name="selectItemsValue" value="nom" />
	<c:param name="selectSelectedValue" value="1" />
	<c:param name="ambOpcioBuida" value="false" />
	<c:param name="clase" value="campoFormTresCuartos" />
</c:import>
</div>

<div class="separadorH"></div>

<div id="divMesura" style="display:none;">
<c:import url="comu/CampFormulari.jsp">
	<c:param name="tipus" value="radio" />
	<c:param name="path" value="formData.mesura" />
	<c:param name="title"><fmt:message key="consulta.agrupacioConsultes.mesura" /></c:param>
	<c:param name="camp" value="mesura" />
	<c:param name="required" value="required" />
	<c:param name="selectItems" value="tipusMesura" />
	<c:param name="selectItemsId" value="val" />
	<c:param name="selectItemsValue" value="nom" />
	<c:param name="selectSelectedValue" value="l" />
</c:import>
</div>

<div class="separadorH"></div>

<div id="divOlivicultors" style="display:none;">
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
		<%--c:param name="selectSelectedValue" value="${formData.idOlivicultor}" /--%>
		<c:param name="clase" value="campoFormMediano conMargen" />
	</c:import>
</div>
	
<div class="separadorH"></div>


<div class="botonesForm">
<div id="aceptarForm" class="btnCorto" onclick="submitForm('formulario');" onmouseover="underline('enlaceAceptarForm')" onmouseout="underline('enlaceAceptarForm')">
	<a id="enlaceAceptarForm" href="javascript:void(0);"><fmt:message key="manteniment.aceptar" /></a>
</div>
</div>

</c:if><!-- Fi if de selecció d'establiment -->

</form>

<div class="separadorH"></div>


</body>
</html>
