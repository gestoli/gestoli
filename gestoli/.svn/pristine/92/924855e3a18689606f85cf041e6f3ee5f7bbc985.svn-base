<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/fn-rt.tld"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ page import="es.caib.gestoli.front.util.*"%>
<%@ page import="java.util.ResourceBundle"%>
<%@ page import="java.util.Locale"%>
<%

	String lang = Idioma.getLang(request);
	request.setAttribute("lang",lang);
%>

<html>
<head>
<title><c:choose>
	<c:when test="${not empty formData.id}">
		<fmt:message key="manteniment.modificacio" />
	</c:when>
	<c:otherwise>
		<fmt:message key="manteniment.alta" />
	</c:otherwise>
</c:choose> <fmt:message key="analitica.tipusdemant" /></title>

<script type="text/javascript" src="dwr/interface/contenidorService.js"></script>
<script type="text/javascript" src="dwr/interface/processosService.js"></script>
<script type="text/javascript" src="dwr/engine.js"></script>
<script type="text/javascript" src="dwr/util.js"> </script>
<c:if test="${(not empty esDoControlador || not empty esTafona || not empty esEnvasador)}">
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
	<link href="js/calendar/calendar-viti.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/form.js"></script>
</c:if>





<script type="text/javascript" language="javascript">
// <![CDATA[
	function calcularVarietat() {
		var indexSensorial = ${fn:length(parametresSensorial)};
		var indexFisicoQuimic = ${fn:length(parametresFisicoQuimic)};

    	var idsSensorial = new Array(indexSensorial);
    	var valorsSensorial = new Array(indexSensorial);

    	var idsFisicoQuimic = new Array(indexFisicoQuimic);
    	var valorsFisicoQuimic = new Array(indexFisicoQuimic);

    	var acidesa = document.getElementById("analisiFisicoQuimicAcidesa").value;

    	
    	for(i=0; i<indexSensorial; i++){
			idsSensorial[i] = document.getElementById("idAnaliticaParametreTipusSensorial["+i+"]").value;

			if(document.getElementById("analiticaParametreTipusSensorial["+i+"]") != null){
				valorsSensorial[i] = document.getElementById("analiticaParametreTipusSensorial["+i+"]").value;
			} else {
				valorsSensorial[i] = null;
			}
        }

    	for(i=0; i<indexFisicoQuimic; i++){
    		idsFisicoQuimic[i] = document.getElementById("idAnaliticaParametreTipusFisicoQuimic["+i+"]").value;

			if(document.getElementById("analiticaParametreTipusFisicoQuimic["+i+"]") != null){
				valorsFisicoQuimic[i] = document.getElementById("analiticaParametreTipusFisicoQuimic["+i+"]").value;
			} else {
				valorsFisicoQuimic[i] = null;
			}
        }
    	
		processosService.calcularVarietatOli( idsSensorial, valorsSensorial, idsFisicoQuimic, valorsFisicoQuimic,'${lang}', acidesa, function(resposta){
				document.getElementById("idVarietatOli2").value=resposta[0];
				document.getElementById("campo_nomVarietatOli2").value = resposta[1];

				actualitzaVarietatTotal();
			});
	}

	function actualitzaVarietatTotal(){
		var idVarietatSensorial = document.getElementById("idVarietatOli1").value;
		var idVarietatFisicoQuimic = document.getElementById("idVarietatOli2").value;
		
		processosService.varietatOliResultant( idVarietatSensorial, idVarietatFisicoQuimic,'${lang}', function(resposta){
			document.getElementById("idVarietatOli").value=resposta[0];
			document.getElementById("campo_nomVarietatOli").value = resposta[1];
		});
	}

	function actualitzarPartidesOli(win) {
		var regex = new RegExp("[\\?&]establimentId=([^&#]*)");
		var results = regex.exec( window.location.href );
		var establiment = results[1];
	    processosService.partidesOliByEstablimentExceptDO(establiment, carregarPartidesOli);
	    winClose = win;
	    setTimeout("winClose.close()", 1000);
	}

	function carregarPartidesOli(datos) {
		var value = document.getElementById("idPartidaOli").value;
		DWRUtil.removeAllOptions("idPartidaOli");
		DWRUtil.addOptions("idPartidaOli", [{id:"",nom:"- - - - -"}], 'id', 'nom');
    	DWRUtil.addOptions("idPartidaOli", datos, 'id', 'nom');
    	document.getElementById("idPartidaOli").value = value;
	}



    var msg = msg || {};
    msg = {
		noDO: "<fmt:message key="consulta.general.camp.categoriaOli.1"/>",
		pendent: "<fmt:message key="consulta.general.camp.categoriaOli.2"/>",
		DO: "<fmt:message key="consulta.general.camp.categoriaOli.3"/>"
    };

	function ompleCategoriaOli() {
		var partidaOli = document.getElementById("idPartidaOli");
		if (partidaOli.value != ""){
			processosService.tipusPartidaOli(partidaOli.value, nomCategoria);
		} else {
			document.getElementById('nomCategoriaOli').value = "";
		}
	}
    
	function nomCategoria(value){
		var categoria = document.getElementById('nomCategoriaOli');
		if (value == "No DO"){
			categoria.value = msg.noDO;
		} else if (value == "Pendent"){
			categoria.value = msg.pendent;
		} else {
			categoria.value = msg.DO;
		}

		
	}
// ]]>
</script>

</head>
<body>


<form id="formulario" name="analiticaForm" action="AnaliticaForm.html" method="post" class="extended seguit" onsubmit="">
	
	<input type="hidden" id="usuari" name="usuari" value="<c:out value="${usuari.usuari}"/>" /> 
	<input type="hidden" id="idEstabliment" name="idEstabliment" value="<c:out value="${establiment.id}"/>" /> 
	<input type="hidden" id="idDiposit" name="idDiposit" value="<c:out value="${diposit.id}"/>" />
	
	<c:set var="read" value="false" />
	<c:set var="readDate" value="0" />
	<c:set var="crearPartida">
		<a href="PartidaOliPopupForm.html" rel="external(720,380)"> 
			<fmt:message key="proces.elaboracioOli.camp.crearPartidaNova" /> 
		</a>
	</c:set>
	<c:if test="${not empty formData.id}">
		<c:set var="read" value="true" />
		<c:set var="readDate" value="1" />
		<c:set var="crearPartida" value=""/>
	</c:if>
	
	<!-- DATA EXECUCIÓ -->
	<div>
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="calendar" />
		<c:param name="path" value="formData.data" />
		<c:param name="title">
			<fmt:message key="analitica.camp.data" />
		</c:param>
		<c:param name="camp" value="campo_data" />
		<c:param name="name" value="data" />
		<c:param name="maxlength" value="10" />
		<c:param name="required" value="required" />
		<c:param name="aclaracio">
			<fmt:message key="proces.aclaracio.formatdata" />
		</c:param>
		<c:param name="clase" value="conMargen campoForm165" />
	</c:import>
	</div>
	
	<!-- INFORMACIÓ GENERAL -->
	<div><label for="analiticaSensorial"><b><fmt:message key="analitica.camp.infoGeneral" />: </b></label></div>
	
	
	<!-- INFORMACIÓ GENERAL - NOM ESTABLIMENT -->
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="static" />
		<c:param name="path" value="formData.nomEstabliment" />
		<c:param name="title">
			<fmt:message key="analitica.camp.nomEstabliment" />:
		</c:param>
		<c:param name="value" value="${establiment.nom}" />
		<c:param name="clase" value="campoFormCompleto" />
	</c:import>
	
	<div class="separadorH"></div>
	
	<!-- INFORMACIÓ GENERAL - NOM DIPOSIT -->
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="static" />
		<c:param name="path" value="formData.nomDiposit" />
		<c:param name="title">
			<fmt:message key="analitica.camp.nomDiposit" />:
		</c:param>
		<c:param name="value" value="${diposit.codiAssignat}" />
		<c:param name="clase" value="campoFormCompleto" />
	</c:import>
	
	<div class="separadorH"></div>

	<!-- INFORMACIÓ GENERAL - NOM PARTIDA -->
	<%-- input type="hidden" id="idPartidaOli" name="idPartidaOli" value="<c:out value="${diposit.partidaOli.id}"/>" /--%>
	<%--  c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="static" />
		<c:param name="path" value="formData.nomPartidaOli" />
		<c:param name="title">
			<fmt:message key="analitica.camp.nomPartida" />:
		</c:param>
		<c:param name="value" value="${diposit.partidaOli.nom}" />
		<c:param name="clase" value="campoFormCompleto" />
	</c:import--%>
	
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="select" />
		<c:param name="path" value="formData.idPartidaOli" />
		<c:param name="required" value="required" />
		<c:param name="title">
			<fmt:message key="proces.elaboracioOli.camp.nomPartida" />
		</c:param>
		<c:param name="camp" value="idPartidaOli" />
		<c:param name="name" value="idPartidaOli" />
		<c:param name="selectItems" value="partidesOli" />
		<c:param name="selectItemsId" value="id" />
		<c:param name="selectItemsValue" value="nom" />
		<c:param name="selectSelectedValue" value="${formData.idPartidaOli}"/>
		<c:param name="clase" value="campoFormGrande conMargen" />
		<c:param name="onchange" value="ompleCategoriaOli();" />
		<c:param name="aclaracio" value="${crearPartida}"/>
		<c:param name="value" value="${formData.idPartidaOli}"/>
	</c:import>

	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.nomCategoriaOli" />
		<c:param name="title">
			<fmt:message key="proces.elaboracioOli.camp.categoriaOli" />
		</c:param>
		<c:param name="camp" value="nomCategoriaOli" />
		<c:param name="name" value="nomCategoriaOli" />
		<c:param name="required" value="required" />
		<c:param name="maxlength" value="128" />
		<c:param name="clase" value="campoFormGrande readOnly" />
		<c:param name="readonly" value="true" />
	</c:import>
	
	<div class="separadorH"></div>
	
	
	<!-- ANALITICA SENSORIAL -->
	<div><label for="analiticaSensorial"><b><fmt:message key="analitica.camp.analiticaSensorial" />: </b></label></div>
	
	<!-- ANALITICA SENSORIAL - NOM LABORATORI -->
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.analisiSensorialNomLaboratori" />
		<c:param name="title">
			<fmt:message key="analitica.camp.nomLaboratori" />
		</c:param>
		<c:param name="camp" value="campo_analisiSensorialNomLaboratori" />
		<c:param name="name" value="analisiSensorialNomLaboratori" />
		<c:param name="required" value="required" />
		<c:param name="maxlength" value="128" />
		<c:param name="clase" value="campoFormGrande conMargen" />
		<c:param name="read" value="${read}" />
	</c:import>

	<div class="separadorH"></div>

	<!-- ANALITICA SENSORIAL - DATA RECEPCIÓ -->
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="calendar" />
		<c:param name="path" value="formData.analisiSensorialDataRecepcio" />
		<c:param name="title">
			<fmt:message key="analitica.camp.dataRecepcio" />
		</c:param>
		<c:param name="camp" value="campo_analisiSensorialDataRecepcio" />
		<c:param name="name" value="analisiSensorialDataRecepcio" />
		<c:param name="maxlength" value="10" />
		<c:param name="required" value="required" />
		<c:param name="aclaracio"><fmt:message key="proces.aclaracio.formatdata" /></c:param>
		<c:param name="clase" value="campoForm165 conMargen" />
		<c:param name="readonly" value ="${readDate}" />
	</c:import> 
	
	<!-- ANALITICA SENSORIAL - DATA TAST -->
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="calendar" />
		<c:param name="path" value="formData.analisiSensorialDataTast" />
		<c:param name="title">
			<fmt:message key="analitica.camp.dataTast" />
		</c:param>
		<c:param name="camp" value="campo_analisiSensorialDataTast" />
		<c:param name="name" value="analisiSensorialDataTast" />
		<c:param name="maxlength" value="10" />
		<c:param name="required" value="required" />
		<c:param name="aclaracio"><fmt:message key="proces.aclaracio.formatdata" /></c:param>
		<c:param name="clase" value="campoForm165 conMargen" />
		<c:param name="readonly" value ="${readDate}" />
	</c:import>

	<div class="separadorH"></div>
	
	<!-- PARÀMETRES SENSORIAL -->
	<c:forEach items="${parametresSensorial}" var="parametre" varStatus="status">
		<input type="hidden" id="idAnaliticaParametreTipusSensorial[${status.index}]" name="idAnaliticaParametreTipusSensorial[${status.index}]" value="${parametre.id}" />
			<%-- Text (1), Sencer (2), Decimal (3) --%>
				<c:if test="${parametre.tipus == 1}">
					<c:set var="aclaracio" value="analitica.camp.tipus.string" />
				</c:if>
				<c:if test="${parametre.tipus == 2}">
					<c:set var="aclaracio" value="analitica.camp.tipus.sencer" />
				</c:if>
				<c:if test="${parametre.tipus == 3}">
					<c:set var="aclaracio" value="analitica.camp.tipus.decimal" />
				</c:if>
				
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="text" />
					<c:param name="path" value="formData.analiticaParametreTipusSensorial[${status.index}]" />
					<c:param name="title">
						<c:if test="${not empty lang && lang == 'ca'}">
							<c:out value="${parametre.nom}" />
						</c:if>
						<c:if test="${not empty lang && lang == 'es'}">
							<c:out value="${parametre.nomCast}" />
						</c:if>
					</c:param>
					<c:param name="camp" value="analiticaParametreTipusSensorial[${status.index}]" />
					<c:param name="name" value="analiticaParametreTipusSensorial[${status.index}]" />
					<c:param name="maxlength" value="128" />
					<c:param name="clase" value="campoFormGrande conMargen" />
					<c:param name="aclaracio"><fmt:message key="${aclaracio}" /></c:param>
					<c:param name="read" value="${read}" />
				</c:import>

		<div class="separadorH"></div>
	</c:forEach>
	
	<div class="separadorH"></div>
	
	<!-- VARIETAT RESULTANT SENSORIAL -->
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="select" />
		<c:param name="path" value="formData.idVarietatOli1" />
		<c:param name="required" value="required" />
		<c:param name="title">
			<fmt:message key="analitica.camp.varietat1" />
		</c:param>
		<c:param name="camp" value="idVarietatOli1" />
		<c:param name="name" value="idVarietatOli1" />
		<c:param name="selectItems" value="varietats" />
		<c:param name="selectItemsId" value="id" />
		<c:param name="selectItemsValue" value="nom" />
		<c:param name="selectSelectedValue" value="${formData.idVarietatOli1}" />
		<c:param name="clase" value="campoFormMediano conMargen" />
		<c:param name="ambOpcioBuida" value="false" />
		<c:param name="onchange" value="actualitzaVarietatTotal();" />
	</c:import> 
	
	<div class="separadorH"></div>
	
	<!-- ANALITICA SENSORIAL - OBSERVACIONS -->
	<div id="observacionesForm" class="campoForm <c:out value="${param.required}"/><c:if test="${not empty status.errorMessage}"> error</c:if>">
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="textarea" />
		<c:param name="path" value="formData.analisiSensorialObservacions" />
		<c:param name="title">
			<fmt:message key="analitica.camp.observacions" />
		</c:param>
		<c:param name="camp" value="analisiSensorialObservacions" />
		<c:param name="read" value="${read}" />
	</c:import></div>
	
	<!-- ANALISI FISICO-QUIMIC -->
	<div><label for="analiticaFQ"><b><fmt:message key="analitica.camp.analiticaFQ" />: </b></label></div>
		
	<!-- ANALISI FISICO-QUIMIC - NOM LABORATORI -->
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.analisiFisicoQuimicNomLaboratori" />
		<c:param name="title">
			<fmt:message key="analitica.camp.nomLaboratori" />
		</c:param>
		<c:param name="camp" value="campo_analisiFisicoQuimicNomLaboratori" />
		<c:param name="name" value="analisiFisicoQuimicNomLaboratori" />
		<c:param name="required" value="required" />
		<c:param name="maxlength" value="128" />
		<c:param name="clase" value="campoFormGrande conMargen" />
		<c:param name="read" value="${read}" />
	</c:import>

	<div class="separadorH"></div>

	<!-- ANALISI FISICO-QUIMIC - DATA RECEPCIÓ -->
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="calendar" />
		<c:param name="path" value="formData.analisiFisicoQuimicDataRecepcio" />
		<c:param name="title">
			<fmt:message key="analitica.camp.dataRecepcio" />
		</c:param>
		<c:param name="camp" value="campo_analisiFisicoQuimicDataRecepcio" />
		<c:param name="name" value="analisiFisicoQuimicDataRecepcio" />
		<c:param name="maxlength" value="10" />
		<c:param name="required" value="required" />
		<c:param name="aclaracio">
			<fmt:message key="proces.aclaracio.formatdata" />
		</c:param>
		<c:param name="clase" value="campoForm165 conMargen" />
		<c:param name="readonly" value ="${readDate}" />
	</c:import>

	<div class="separadorH"></div>

	<!-- ANALISI FISICO-QUIMIC - DATA INICI -->
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="calendar" />
		<c:param name="path" value="formData.analisiFisicoQuimicDataInici" />
		<c:param name="title">
			<fmt:message key="analitica.camp.dataInici" />
		</c:param>
		<c:param name="camp" value="campo_analisiFisicoQuimicDataInici" />
		<c:param name="name" value="analisiFisicoQuimicDataInici" />
		<c:param name="maxlength" value="10" />
		<c:param name="required" value="required" />
		<c:param name="aclaracio">
			<fmt:message key="proces.aclaracio.formatdata" />
		</c:param>
		<c:param name="clase" value="campoForm165 conMargen" />
		<c:param name="readonly" value ="${readDate}" />
	</c:import> 
	
	<!-- ANALISI FISICO-QUIMIC - DATA FI -->
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="calendar" />
		<c:param name="path" value="formData.analisiFisicoQuimicDataFi" />
		<c:param name="title">
			<fmt:message key="analitica.camp.dataFi" />
		</c:param>
		<c:param name="camp" value="campo_analisiFisicoQuimicDataFi" />
		<c:param name="name" value="analisiFisicoQuimicDataFi" />
		<c:param name="maxlength" value="10" />
		<c:param name="required" value="required" />
		<c:param name="aclaracio">
			<fmt:message key="proces.aclaracio.formatdata" />
		</c:param>
		<c:param name="clase" value="campoForm165 conMargen" />
		<c:param name="readonly" value ="${readDate}" />
	</c:import>

	<div class="separadorH"></div>
	
	<!-- ANALISI FISICO-QUIMIC - ACIDESA -->
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.analisiFisicoQuimicAcidesa" />
		<c:param name="title">
			<fmt:message key="analitica.camp.acidesa" />
		</c:param>
		<c:param name="camp" value="analisiFisicoQuimicAcidesa" />
		<c:param name="name" value="analisiFisicoQuimicAcidesa" />
		<c:param name="required" value="required" />
		<c:param name="maxlength" value="128" />
		<c:param name="clase" value="campoFormGrande conMargen" />
		<c:param name="read" value="${read}" />
	</c:import>
	
	<div class="separadorH"></div>
	
	<!-- PARÀMETRES FISICOQUIMIC -->
	<c:forEach items="${parametresFisicoQuimic}" var="parametre" varStatus="status">
		<input type="hidden" id="idAnaliticaParametreTipusFisicoQuimic[${status.index}]" name="idAnaliticaParametreTipusFisicoQuimic[${status.index}]" value="${parametre.id}" />
			<%-- Text (1), Sencer (2), Decimal (3) --%>
			<c:if test="${parametre.tipus == 1}">
				<c:set var="aclaracio" value="analitica.camp.tipus.string" />
			</c:if>
			<c:if test="${parametre.tipus == 2}">
				<c:set var="aclaracio" value="analitica.camp.tipus.sencer" />
			</c:if>
			<c:if test="${parametre.tipus == 3}">
				<c:set var="aclaracio" value="analitica.camp.tipus.decimal" />
			</c:if>
			
			<c:import url="comu/CampFormulari.jsp">
				<c:param name="tipus" value="text" />
				<c:param name="path" value="formData.analiticaParametreTipusFisicoQuimic[${status.index}]" />
				<c:param name="title">
					<c:if test="${not empty lang && lang == 'ca'}">
						<c:out value="${parametre.nom}" />
					</c:if>
					<c:if test="${not empty lang && lang == 'es'}">
						<c:out value="${parametre.nomCast}" />
					</c:if>
				</c:param>
				<c:param name="camp" value="analiticaParametreTipusFisicoQuimic[${status.index}]" />
				<c:param name="name" value="analiticaParametreTipusFisicoQuimic[${status.index}]" />
				<c:param name="required" value="required" />
				<c:param name="maxlength" value="128" />
				<c:param name="clase" value="campoFormGrande conMargen" />
				<c:param name="onchange" value="calcularVarietat()" />
				<c:param name="aclaracio"><fmt:message key="${aclaracio}" /></c:param>
				<c:param name="read" value="${read}" />
			</c:import>
		<div class="separadorH"></div>
	</c:forEach>
	
	<!-- ANALISI FISICO-QUIMIC - VALID -->
	<%--
	<c:if test="${empty formData.id}">
		<div class="etiqueta <c:out value="${param.required}"/><c:if test="${not empty status.errorMessage}"> error</c:if>">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="checkbox" />
			<c:param name="path" value="formData.analisiFisicoQuimicValid" />
			<c:param name="title">
				<fmt:message key="analitica.camp.valid" />
			</c:param>
			<c:param name="camp" value="analisiFisicoQuimicValid" />
		</c:import></div>
	</c:if>
	<c:if test="${not empty formData.id}">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="static" />
			<c:param name="path" value="formData.analisiFisicoQuimicValid" />
			<c:param name="title">
				<fmt:message key="analitica.camp.valid" />:
			</c:param>
			<c:param name="value">
				<c:if test="${formData.analisiFisicoQuimicValid == true}"><fmt:message key="manteniment.si" /></c:if>
				<c:if test="${formData.analisiFisicoQuimicValid == false}"><fmt:message key="manteniment.no" /></c:if>
			</c:param>
			<c:param name="clase" value="campoFormGrande" />
		</c:import>
	</c:if>
	--%>

	<div class="separadorH"></div>
	
	<!-- ANALISI FISICO-QUIMIT - OBSERVACIONS -->
	<div id="observacionesForm" class="campoForm <c:out value="${param.required}"/><c:if test="${not empty status.errorMessage}"> error</c:if>">
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="textarea" />
		<c:param name="path" value="formData.analisiFisicoQuimicObservacions" />
		<c:param name="title">
			<fmt:message key="analitica.camp.observacions" />
		</c:param>
		<c:param name="camp" value="analisiFisicoQuimicObservacions" />
		<c:param name="read" value="${read}" />
	</c:import></div>

	<div class="separadorH"></div>
	
	<!-- VARIETAT RESULTANT FISICOQUIMICA -->
	<c:if test="${not empty formData.id}">
		<p>${varietat2}</p>
	</c:if>
	<c:if test="${empty formData.id}">
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.nomVarietatOli2" />
		<c:param name="title">
			<fmt:message key="analitica.camp.varietat2" />
		</c:param>
		<c:param name="camp" value="campo_nomVarietatOli2" />
		<c:param name="name" value="nomVarietatOli2" />
		<c:param name="required" value="required" />
		<c:param name="maxlength" value="128" />
		<c:param name="clase" value="campoFormGrande conMargen readOnly" />
		<c:param name="readonly" value="true" />
	</c:import>
	<input type="hidden" id="idVarietatOli2" name="idVarietatOli2" value=<c:if test="${empty formData.idVarietatOli2}">"0"</c:if><c:if test="${not empty formData.idVarietatOli2}">"${formData.idVarietatOli2}"</c:if> />
	</c:if>
	
	<div class="separadorH"></div>
	
	<!-- INFORMACIÓ GENERAL -->
	<div><label for="analiticaSensorial"><b><fmt:message key="analitica.camp.nomVarietat" />: </b></label></div>
	
	<!-- VARIETAT RESULTANT RESULTANT -->
	<c:if test="${not empty formData.id}">
		<p>${varietat}</p>
	</c:if>
	<c:if test="${empty formData.id}">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="text" />
			<c:param name="path" value="formData.nomVarietatOli" />
			<c:param name="title"></c:param>
			<c:param name="camp" value="campo_nomVarietatOli" />
			<c:param name="name" value="nomVarietatOli" />
			<c:param name="required" value="required" />
			<c:param name="maxlength" value="128" />
			<c:param name="clase" value="campoFormGrande conMargen readOnly" />
			<c:param name="readonly" value="true" />
		</c:import>
		<input type="hidden" id="idVarietatOli" name="idVarietatOli" value=<c:if test="${empty formData.idVarietatOli}">"0"</c:if><c:if test="${not empty formData.idVarietatOli}">"${formData.idVarietatOli}"</c:if> />
	</c:if>

	<div class="separadorH"></div>


	<div class="botonesForm">
		<c:if test="${(not empty esDoControlador  || not empty esTafona || not empty esEnvasador) && empty formData.id}">
			<div id="guardarForm" class="btnCorto"
				onclick="submitForm('formulario')"
				onmouseover="underline('enlaceGuardarForm')"
				onmouseout="underline('enlaceGuardarForm')"><a
				id="enlaceGuardarForm" href="javascript:void(0);"><fmt:message
				key="manteniment.guardar" /></a></div>
		</c:if>
			
		<c:choose>
			<c:when test="${not empty traza}">
				<div class="btnCorto" onclick="javascript:history.go(-1);"
					onmouseover="underline('enlaceTornarForm')"
					onmouseout="underline('enlaceTornarForm')"><a
					id="enlaceTornarForm" href="javascript:void(0);"><fmt:message
					key="proces.tornar" /></a></div>
			</c:when>
			<c:otherwise>
				<div class="btnCorto" onclick="submitForm('tornarForm')"
					onmouseover="underline('enlaceTornarForm')"
					onmouseout="underline('enlaceTornarForm')"><a
					id="enlaceTornarForm" href="javascript:void(0);"><fmt:message
					key="proces.tornar" /></a></div>
			</c:otherwise>
		</c:choose>	


		<c:if test="${not empty formData.id}">
			<div class="btnLargo" onclick="submitForm('pdfForm')"
				onmouseover="underline('enlacePdfForm')"
				onmouseout="underline('enlacePdfForm')"><a id="enlacePdfForm"
				href="javascript:void(0);"><fmt:message key="proces.exportar" /></a>
			</div>
		</c:if>
		
		<c:if test="${not empty formData.id && usuari.usuari == formData.usuari}">
			<input id="action" name="action" value="delete" type="hidden" />
			<div id="eliminarForm" class="btnCorto"
				onmouseover="underline('enlaceBorrarForm')"
				onmouseout="underline('enlaceBorrarForm')"
				onclick="submitFormConfirm('deleteForm','<fmt:message key="manteniment.esborrar.confirm"/>');">
				<a id="enlaceBorrarForm" href="javascript:void(0);">
					<fmt:message key="manteniment.esborrar" />
				</a>
			</div>
		</c:if>
	
	</div>

	</form>
	
	<%-- <c:if test="${not empty formData.id && usuari.usuari == formData.usuari}">
		<form id="deleteForm" action="ProcesInici.html" method="post" class="seguit"
			onsubmit="return confirm('<fmt:message key="manteniment.estasegur"/>')">
			<input id="id" name="id" value="<c:out value="${formData.id}"/>" type="hidden" /> 
			<input id="action" name="action" value="delete" type="hidden" />
			
			<input type="hidden" id="tipusProces" name="tipusProces" value="11" />
			<input type="hidden" id="pas" name="pas" value="1" />
			<input type="hidden" id="fromEstabliment" name="fromEstabliment" value="true" />
			<input type="hidden" id="zonaId" name="zonaId" value="<c:out value="${formData.diposit.zona.id}"/>" /> 
			<input type="hidden" id="establimentId" name="establimentId" value="<c:out value="${formData.diposit.establiment.id}"/>" />
		</form>
	</c:if> --%>
	
	<c:if test="${(empty formData.id)}">
		<form id="tornarForm" action="ProcesInici.html" class="seguit">
			<input type="hidden" id="tipusProces" name="tipusProces" value="5">
		</form>
	</c:if>
	
	<c:if test="${(not empty formData.id and empty formdata.diposit)}">
		<form id="tornarForm" action="javascript:history.go(-1);" class="seguit">
		</form>
	</c:if>
	
	<c:if test="${(not empty formData.id and not empty formdata.diposit)}">
		<form id="tornarForm" action="ProcesInici.html" class="seguit">
			<input type="hidden" id="tipusProces" name="tipusProces" value="11" />
			<input type="hidden" id="pas" name="pas" value="1" /> 
			<input type="hidden" id="zonaId" name="zonaId" value="<c:out value="${formData.diposit.zona.id}"/>" /> 
			<input type="hidden" id="establimentId" name="establimentId" value="<c:out value="${formData.diposit.establiment.id}"/>" />
		</form>
	</c:if>

	<form id="pdfForm" action="GenerarPdf.html" method="post" class="seguit">
		<input type="hidden" id="tipus" name="tipus" value="7" /> 
		<input type="hidden" id="idAna" name="idAna" value="<c:out value="${formData.id}"/>" /> 
		<input type="hidden" id="idEst" name="idEst" value="<c:out value="${formData.diposit.establiment.id}"/>" /> 
		<input type="hidden" id="idDip" name="idDip" value="<c:out value="${formData.diposit.id}"/>" />
	</form>
<script type="text/javascript">
	jQuery(document).ready(function(){
		ompleCategoriaOli();
	})
</script>

</body>
</html>
