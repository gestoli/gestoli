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
$(document).ready(function() {
	
})

function guardarFormulari(){
	$('form input[name^=OK]:checkbox').each(function() {
		$(this).removeAttr("disabled");
	});
	$('#apta').removeAttr("disabled");
	var form = document.getElementById("formulario");
	form.submit();
}

function checkunic(out,camp){
	//var $chk = $('form input[name^='+ camp +']:checkbox'); 
        //$chk.click(function()
        //{
        //    $chk.removeAttr('checked');
        //    $(this).attr("checked","checked");
        //});
	verificarCorrecte(camp);
}

function verificarCorrecte(camp){
	var tipusOliva = $('#tipusOliva').val();
	var apta = false;
	if(camp=='color'){
		if (tipusOliva != 2) {
			apta = ($('#colorVerd').is(':checked') || $('#colorVerdGroc').is(':checked')) && !$('#colorGroc').is(':checked'); 
		} else {
			apta = ($('#colorMarroObscur').is(':checked') || $('#colorNegre').is(':checked')) && !$('#colorMarro').is(':checked');
		}
		apta = apta && !$('#colorAltres').val();
		$('#OKcolorCorrecte').attr("checked",apta);
	} else if(camp=='aroma'){
		if (tipusOliva == 0) {
			apta = $('#aromaVegetal').is(':checked') && $('#aromaMineral').is(':checked'); 
		} else if (tipusOliva == 1) {
			apta = $('#aromaVegetal').is(':checked') && $('#aromaFonoll').is(':checked') && $('#aromaPebre').is(':checked');
		} else if (tipusOliva == 2) {
			apta = $('#aromaTerra').is(':checked');
		}
		apta = apta && !$('#aromaAltres').val();
		$('#OKaromaCorrecte').attr("checked",apta);
	} else if(camp=='texturaFer'){
		if (tipusOliva == 0) {
			apta = ($('#texturaFermesa4').is(':checked') || $('#texturaFermesa5').is(':checked')) && !$('#texturaFermesa1').is(':checked') && !$('#texturaFermesa2').is(':checked') && !$('#texturaFermesa3').is(':checked'); 
			$('#OKtexturaFermesaCorrecte').attr("checked",apta);
		} else if (tipusOliva == 1) {
			apta = ($('#texturaFermesa2').is(':checked') || $('#texturaFermesa3').is(':checked')) && !$('#texturaFermesa1').is(':checked') && !$('#texturaFermesa4').is(':checked') && !$('#texturaFermesa5').is(':checked');
			$('#OKtexturaFermesaCorrecte').attr("checked",apta);
		}
	} else if(camp=='texturaDef'){
		if (tipusOliva == 0) {
			apta = ($('#texturaDeformabilitat1').is(':checked') || $('#texturaDeformabilitat2').is(':checked')) && !$('#texturaDeformabilitat3').is(':checked') && !$('#texturaDeformabilitat4').is(':checked') && !$('#texturaDeformabilitat5').is(':checked');
			$('#OKtexturaDeformabilitatCorrecte').attr("checked",apta);
		} else if (tipusOliva == 1) {
			apta = ($('#texturaDeformabilitat3').is(':checked') || $('#texturaDeformabilitat4').is(':checked')) && !$('#texturaDeformabilitat1').is(':checked') && !$('#texturaDeformabilitat2').is(':checked') && !$('#texturaDeformabilitat5').is(':checked');
			$('#OKtexturaDeformabilitatCorrecte').attr("checked",apta);
		}
	} else if(camp=='texturaEla'){
		if (tipusOliva == 1) {
			apta = ($('#texturaElasticitat3').is(':checked') || $('#texturaElasticitat4').is(':checked')) && !$('#texturaElasticitat1').is(':checked') && !$('#texturaElasticitat2').is(':checked') && !$('#texturaElasticitat5').is(':checked');
			$('#OKtexturaElasticitatCorrecte').attr("checked",apta);
		}
	} else if(camp=='texturaSua'){
		if (tipusOliva == 2) {
			apta = ($('#texturaSuavitat4').is(':checked') || $('#texturaSuavitat5').is(':checked')) && !$('#texturaSuavitat1').is(':checked') && !$('#texturaSuavitat2').is(':checked') && !$('#texturaSuavitat3').is(':checked');
			$('#OKtexturaSuavitatCorrecte').attr("checked",apta);
		}
	} else if(camp=='texturaboca'){
		if (tipusOliva == 0) {
			apta = ($('#texturabocaFermesaEnBoca4').is(':checked') || $('#texturabocaFermesaEnBoca5').is(':checked')) && !$('#texturabocaFermesaEnBoca1').is(':checked') && !$('#texturabocaFermesaEnBoca2').is(':checked') && !$('#texturabocaFermesaEnBoca3').is(':checked');
			$('#OKtexturaFermesaCorrecte').attr("checked",apta);
		} else if (tipusOliva != 0) {
			apta = ($('#texturabocaFermesaEnBoca2').is(':checked') || $('#texturabocaFermesaEnBoca3').is(':checked')) && !$('#texturabocaFermesaEnBoca1').is(':checked') && !$('#texturabocaFermesaEnBoca4').is(':checked') && !$('#texturabocaFermesaEnBoca5').is(':checked');
			$('#OKtexturabocaFermesaEnBocaCorrecte').attr("checked",apta);
		}
		$('#OKtexturabocaFermesaEnBocaCorrecte').attr("checked", apta);
	} else if(camp=='texturaFri'){
		if (tipusOliva == 0) {
			apta = $('#texturaFriabilitat2').is(':checked') && !$('#texturaFriabilitat1').is(':checked') && !$('#texturaFriabilitat3').is(':checked') && !$('#texturaFriabilitat4').is(':checked') && !$('#texturaFriabilitat5').is(':checked'); 
			$('#OKtexturaFriabilitatCorrecte').attr("checked", apta);
		}
	} else if(camp=='texturaCoh'){
		if (tipusOliva == 0) {
			apta = ($('#texturaCohesio4').is(':checked') || $('#texturaCohesio5').is(':checked')) && !$('#texturaCohesio1').is(':checked') && !$('#texturaCohesio2').is(':checked') && !$('#texturaCohesio3').is(':checked'); 
		} else if (tipusOliva == 1) {
			apta = ($('#texturaCohesio2').is(':checked') || $('#texturaCohesio3').is(':checked')) && !$('#texturaCohesio1').is(':checked') && !$('#texturaCohesio4').is(':checked') && !$('#texturaCohesio5').is(':checked');
		} else if (tipusOliva == 2) {
			apta = $('#texturaCohesio2').is(':checked') && !$('#texturaCohesio1').is(':checked') && !$('#texturaCohesio3').is(':checked') && !$('#texturaCohesio4').is(':checked') && !$('#texturaCohesio5').is(':checked');
		}
		$('#OKtexturaCohesioCorrecte').attr("checked", apta);
	} else if(camp=='texturaUnt'){
		if (tipusOliva != 1) {
			apta = ($('#texturaUntuositat3').is(':checked') || $('#texturaUntuositat4').is(':checked')) && !$('#texturaUntuositat1').is(':checked') && !$('#texturaUntuositat2').is(':checked') && !$('#texturaUntuositat5').is(':checked'); 
		} else if (tipusOliva == 1) {
			apta = ($('#texturaUntuositat2').is(':checked') || $('#texturaUntuositat3').is(':checked')) && !$('#texturaUntuositat1').is(':checked') && !$('#texturaUntuositat4').is(':checked') && !$('#texturaUntuositat5').is(':checked');
		}
		$('#OKtexturaUntuositatCorrecte').attr("checked", apta);
	} else if(camp=='sabor'){
		if (tipusOliva == 0) {
			apta = $('#saborAcid').is(':checked') && $('#saborSalat').is(':checked') && $('#saborAmarg').is(':checked'); 
		} else if (tipusOliva == 1) {
			apta = $('#saborSalat').is(':checked') && $('#saborAmarg').is(':checked'); 
		} else if (tipusOliva == 2) {
			apta = $('#saborAcid').is(':checked') && $('#saborSalat').is(':checked'); 
		}
		apta = apta && !$('#saborAltres').val();
		$('#OKsaborCorrecte').attr("checked",apta);
	} else if(camp=='sensacio'){
		if (tipusOliva != 2) {
			apta = $('#sensacioAstringent').is(':checked') && $('#sensacioPicant').is(':checked'); 
		} else if (tipusOliva == 2) {
			apta = $('#sensacioAstringent').is(':checked') && !$('#sensacioPicant').is(':checked');
		}
		apta = apta && !$('#aromaAltres').val();
		$('#OKsensacioCorrecte').attr("checked",apta);
	} else if(camp=='regust'){
		if (tipusOliva != 2) {
			apta = $('#regustProlongat').is(':checked') && !$('#regustBaix').is(':checked') && !$('#regustMitja').is(':checked'); 
		} else {
			apta = $('#regustMitja').is(':checked') && !$('#regustBaix').is(':checked') && !$('#regustProlongat').is(':checked');
		}
		$('#OKregustCorrecte').attr("checked", apta);
	}

	apta = $('#OKcolorCorrecte').is(':checked');
	apta = apta && $('#OKaromaCorrecte').is(':checked');
	apta = apta && $('#OKsaborCorrecte').is(':checked');
	apta = apta && $('#OKsensacioCorrecte').is(':checked');
	apta = apta && $('#OKregustCorrecte').is(':checked');
	apta = apta && $('#OKtexturabocaFermesaEnBocaCorrecte').is(':checked');
	apta = apta && $('#OKtexturaCohesioCorrecte').is(':checked');
	apta = apta && $('#OKtexturaUntuositatCorrecte').is(':checked');
	
	if (tipusOliva == 0) {
		apta = apta && $('#OKtexturaFermesaCorrecte').is(':checked');
		apta = apta && $('#OKtexturaDeformabilitatCorrecte').is(':checked');
		apta = apta && $('#OKtexturaFriabilitatCorrecte').is(':checked');
		
	} else if (tipusOliva == 1) {
		apta = apta && $('#OKtexturaFermesaCorrecte').is(':checked');
		apta = apta && $('#OKtexturaDeformabilitatCorrecte').is(':checked');
		apta = apta && $('#OKtexturaElasticitatCorrecte').is(':checked');
		
	} else if (tipusOliva == 2) {
		apta = apta && $('#OKtexturaSuavitatCorrecte').is(':checked');
		
	}

	$('#apta').attr("checked", apta);
}
// ]]>
</script>

</head>
<body>


<form style="width:700px" id="formulario" name="autocontrolForm" action="AutocontrolForm.html" method="post" class="extended seguit" onsubmit="">
	
	<input type="hidden" id="idEstabliment" name="idEstabliment" value="<c:out value="${establiment.id}"/>" /> 
	<input type="hidden" id="idBota" name="idBota" value="<c:out value="${bota.id}"/>" />
	<input type="hidden" id="tipusOliva" name="tipusOliva" value="<c:out value="${bota.tipusOlivaTaula}"/>" />
	
	<!-- DATA EXECUCIÓ -->
	<div>
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="calendar" />
		<c:param name="path" value="formData.data" />
		<c:param name="title"><fmt:message key="analitica.camp.data" /></c:param>
		<c:param name="camp" value="campo_data" />
		<c:param name="name" value="data" />
		<c:param name="maxlength" value="10" />
		<c:param name="required" value="required" />
		<c:param name="aclaracio"><fmt:message key="proces.aclaracio.formatdata" /></c:param>
		<c:param name="clase" value="conMargen campoForm165" />
	</c:import>
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.hora" />
		<c:param name="required" value="required" />
		<c:param name="title"><fmt:message key="consulta.entradaFonoll.camp.hora" /></c:param>
		<c:param name="camp" value="campo_hora" />
		<c:param name="name" value="hora" />
		<c:param name="maxlength" value="5" />
		<c:param name="aclaracio"><fmt:message key="proces.aclaracio.hora" /></c:param>
		<c:param name="clase" value="campoFormMediano" />
	</c:import>
	</div>
	<div class="separadorH"></div>
				
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.responsable" />
		<c:param name="required" value="required" />
		<c:param name="title"><fmt:message key="consulta.oliElaborat.camp.responsable" /></c:param>
		<c:param name="camp" value="campo_titular" />
		<c:param name="name" value="responsable" />
		<c:param name="clase" value="campoFormCompleto conMargen" />
	</c:import>
	<div class="separadorH"></div>
	
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
	
	
	<!-- INFORMACIÓ GENERAL - NOM DIPOSIT -->
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="static" />
		<c:param name="path" value="formData.idBota" />
		<c:param name="title">
			Bota: 
		</c:param>
		<c:param name="value" value="${bota.identificador}" />
		<c:param name="clase" value="campoFormCompleto" />
	</c:import>
	
	<div class="separadorH"></div>

	
	
		<!-- COLOR -->
	<div><label for="analiticaSensorial"><b>1.-<fmt:message key="autocontrol.color"/></b></label></div>
	
	
	<div class="separadorH"></div>
	<table>
		<tr>
			<th colspan="4" style="text-align: center;"><fmt:message key="autocontrol.color"/></th>
		</tr>
		<tr>
			<c:if test="${bota.tipusOlivaTaula != 2}">
				<th class="ancho75" style="text-align: center;"><fmt:message key="autocontrol.color.groc"/></th>
				<th class="ancho75" style="text-align: center;"><fmt:message key="autocontrol.color.vergroc"/></th>
				<th class="ancho75" style="text-align: center;"><fmt:message key="autocontrol.color.verd"/></th>
			</c:if>
			<c:if test="${bota.tipusOlivaTaula == 2}">
				<th class="ancho75" style="text-align: center;"><fmt:message key="autocontrol.color.marro"/></th>
				<th class="ancho75" style="text-align: center;"><fmt:message key="autocontrol.color.marroobscur"/></th>
				<th class="ancho75" style="text-align: center;"><fmt:message key="autocontrol.color.negre"/></th>
			</c:if>
			<th class="ancho75" style="text-align: center;"><fmt:message key="autocontrol.color.altres"/></th>
			<th class="ancho75" style="text-align: center;"><fmt:message key="autocontrol.correcte"/></th>
		</tr>
		<tr>
			<div id="color" class="color">
				<c:if test="${bota.tipusOlivaTaula != 2}">	
					<td style="text-align: center;">
						<c:import url="comu/CampFormulari.jsp">
							<c:param name="tipus" value="checkbox" />
							<c:param name="path" value="formData.colorGroc" />
							<c:param name="camp" value="colorGroc" />
							<c:param name="onclick" value="checkunic(this,'color')"></c:param>
						</c:import>
				
					</td>
					<td style="text-align: center;">
						<c:import url="comu/CampFormulari.jsp">
							<c:param name="tipus" value="checkbox" />
							<c:param name="path" value="formData.colorVerdGroc" />
							<c:param name="camp" value="colorVerdGroc" />
							<c:param name="onclick" value="checkunic(this,'color')"></c:param>
						</c:import>
					</td>
					<td style="text-align: center;">
						<c:import url="comu/CampFormulari.jsp">
							<c:param name="tipus" value="checkbox" />
							<c:param name="path" value="formData.colorVerd" />
							<c:param name="camp" value="colorVerd" />
							<c:param name="onclick" value="checkunic(this,'color')"></c:param>
						</c:import>
					</td>
				</c:if>
				<c:if test="${bota.tipusOlivaTaula == 2}">
					<td style="text-align: center;">
						<c:import url="comu/CampFormulari.jsp">
							<c:param name="tipus" value="checkbox" />
							<c:param name="path" value="formData.colorMarro" />
							<c:param name="camp" value="colorMarro" />
							<c:param name="onclick" value="checkunic(this,'color')"></c:param>
						</c:import>
				
					</td>
					<td style="text-align: center;">
						<c:import url="comu/CampFormulari.jsp">
							<c:param name="tipus" value="checkbox" />
							<c:param name="path" value="formData.colorMarroObscur" />
							<c:param name="camp" value="colorMarroObscur" />
							<c:param name="onclick" value="checkunic(this,'color')"></c:param>
						</c:import>
					</td>
					<td style="text-align: center;">
						<c:import url="comu/CampFormulari.jsp">
							<c:param name="tipus" value="checkbox" />
							<c:param name="path" value="formData.colorNegre" />
							<c:param name="camp" value="colorNegre" />
							<c:param name="onclick" value="checkunic(this,'color')"></c:param>
						</c:import>
					</td>
				</c:if>
				<td  style="text-align: center;">
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="text" />
						<c:param name="path" value="formData.colorAltres" />
						<c:param name="camp" value="colorAltres" />
					</c:import>
				</td>
				<td  style="text-align: center;">
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="checkbox" />
						<c:param name="path" value="formData.OKcolorCorrecte" />
						<c:param name="camp" value="OKcolorCorrecte" />
						<c:param name="disabled" value="disabled" />
					</c:import>
				</td>
			</div>
		</tr>
	</table> 
	<br/>
	<!-- AROMA -->
	<div><label for="analiticaSensorial"><b>2.-<fmt:message key="autocontrol.aroma"/></b></label></div>
	
	
	<div class="separadorH"></div>
	<table>
		<tr>
			<th colspan="3" style="text-align: center;"><fmt:message key="autocontrol.aroma"/></th>
		</tr>
		<tr>
			<c:if test="${bota.tipusOlivaTaula != 2}">
				<th class="ancho75" style="text-align: center;"><fmt:message key="autocontrol.aroma.vegetal"/></th>
			</c:if>
			<c:if test="${bota.tipusOlivaTaula == 0}">
				<th class="ancho75" style="text-align: center;"><fmt:message key="autocontrol.aroma.mineral"/></th>
			</c:if>
			<c:if test="${bota.tipusOlivaTaula == 1}">
				<th class="ancho75" style="text-align: center;"><fmt:message key="autocontrol.aroma.fonoll"/></th>
				<th class="ancho75" style="text-align: center;"><fmt:message key="autocontrol.aroma.pebre"/></th>
			</c:if>
			<c:if test="${bota.tipusOlivaTaula == 2}">
				<th class="ancho75" style="text-align: center;"><fmt:message key="autocontrol.aroma.terra"/></th>
			</c:if>
			<th class="ancho75" style="text-align: center;"><fmt:message key="autocontrol.aroma.altres"/></th>
			<th class="ancho75" style="text-align: center;"><fmt:message key="autocontrol.correcte"/></th>
		</tr>
		<tr>
			<c:if test="${bota.tipusOlivaTaula != 2}">
				<td style="text-align: center;">
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="checkbox" />
						<c:param name="path" value="formData.aromaVegetal" />
						<c:param name="camp" value="aromaVegetal" />
						<c:param name="onclick" value="checkunic(this,'aroma')"></c:param>
						
					</c:import>
				</td>
			</c:if>
			<c:if test="${bota.tipusOlivaTaula == 0}">
				<td style="text-align: center;">
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="checkbox" />
						<c:param name="path" value="formData.aromaMineral" />
						<c:param name="camp" value="aromaMineral" />
						<c:param name="onclick" value="checkunic(this,'aroma')"></c:param>
					</c:import>
				</td>
			</c:if>
			<c:if test="${bota.tipusOlivaTaula == 1}">
				<td style="text-align: center;">
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="checkbox" />
						<c:param name="path" value="formData.aromaFonoll" />
						<c:param name="camp" value="aromaFonoll" />
						<c:param name="onclick" value="checkunic(this,'aroma')"></c:param>
					</c:import>
				</td>
				<td style="text-align: center;">
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="checkbox" />
						<c:param name="path" value="formData.aromaPebre" />
						<c:param name="camp" value="aromaPebre" />
						<c:param name="onclick" value="checkunic(this,'aroma')"></c:param>
					</c:import>
				</td>
			</c:if>
			<c:if test="${bota.tipusOlivaTaula == 2}">
				<td style="text-align: center;">
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="checkbox" />
						<c:param name="path" value="formData.aromaTerra" />
						<c:param name="camp" value="aromaTerra" />
						<c:param name="onclick" value="checkunic(this,'aroma')"></c:param>
						
					</c:import>
				</td>
			</c:if>
			<td style="text-align: center;">
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="text" />
					<c:param name="path" value="formData.aromaAltres" />
					<c:param name="camp" value="aromaAltres" />
				</c:import>
			</td>
			<td style="text-align: center;">
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="checkbox" />
					<c:param name="path" value="formData.OKaromaCorrecte" />
					<c:param name="camp" value="OKaromaCorrecte" />
					<c:param name="disabled" value="disabled" />
				</c:import>
			</td>
		</tr>
	</table> 

	<br/>
	<!-- TEXTURA -->
	<div><label for="analiticaSensorial"><b>3.-<fmt:message key="autocontrol.textura"/></b></label></div>
	
	
	<div class="separadorH"></div>
	<table>
		<tr>	
			<th class="ancho75" style="text-align: center;" colspan="2"></th>
			<th class="ancho75" style="text-align: center;">1<br/><fmt:message key="autocontrol.textura.nula"/></th>
			<th class="ancho75" style="text-align: center;">2</th>
			<th class="ancho75" style="text-align: center;">3<br/><fmt:message key="autocontrol.textura.mitja"/></th>
			<th class="ancho75" style="text-align: center;">4</th>
			<th class="ancho75" style="text-align: center;">5<br/><fmt:message key="autocontrol.textura.elevada"/></th>
			<th class="ancho75" style="text-align: center;"><fmt:message key="autocontrol.correcte"/></th>
		</tr>
		<c:if test="${bota.tipusOlivaTaula != 2}">
			<tr>
				<th class="ancho32" rowspan="<c:if test='${bota.tipusOlivaTaula == 0}'>2</c:if><c:if test='${bota.tipusOlivaTaula == 1}'>3</c:if>" style="padding-left: 10px; padding-right: 10px;"><fmt:message key="autocontrol.textura.tacte"/></th>
				<th><fmt:message key="autocontrol.textura.fermesa"/></th>
				<td style="text-align: center;"><fmt:message key="autocontrol.textura.caqui"/>
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="checkbox" />
						<c:param name="path" value="formData.texturaFermesa1" />
						<c:param name="camp" value="texturaFermesa1" />
						<c:param name="onclick" value="checkunic(this,'texturaFer')"></c:param>
					</c:import>
				</td>
				<td style="text-align: center;">&nbsp;
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="checkbox" />
						<c:param name="path" value="formData.texturaFermesa2" />
						<c:param name="camp" value="texturaFermesa2" />
						<c:param name="onclick" value="checkunic(this,'texturaFer')"></c:param>
					</c:import>
				</td>
				<td style="text-align: center;"><fmt:message key="autocontrol.textura.pera"/>
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="checkbox" />
						<c:param name="path" value="formData.texturaFermesa3" />
						<c:param name="camp" value="texturaFermesa3" />
						<c:param name="onclick" value="checkunic(this,'texturaFer')"></c:param>
					</c:import>
				</td>
				<td style="text-align: center;">&nbsp;
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="checkbox" />
						<c:param name="path" value="formData.texturaFermesa4" />
						<c:param name="camp" value="texturaFermesa4" />
						<c:param name="onclick" value="checkunic(this,'texturaFer')"></c:param>
					</c:import>
				</td>
				<td style="text-align: center;"><fmt:message key="autocontrol.textura.pastanaga"/>
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="checkbox" />
						<c:param name="path" value="formData.texturaFermesa5" />
						<c:param name="camp" value="texturaFermesa5" />
						<c:param name="onclick" value="checkunic(this,'texturaFer')"></c:param>
					</c:import>
				</td>
				<td style="text-align: center;">&nbsp;
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="checkbox" />
						<c:param name="path" value="formData.OKtexturaFermesaCorrecte" />
						<c:param name="camp" value="OKtexturaFermesaCorrecte" />
						<c:param name="disabled" value="disabled" />
					</c:import>
				</td>		
			</tr>
			<tr>
				<th><fmt:message key="autocontrol.textura.deformabilitat"/></th>
				<td style="text-align: center;"><fmt:message key="autocontrol.textura.cogombre"/>
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="checkbox" />
						<c:param name="path" value="formData.texturaDeformabilitat1" />
						<c:param name="camp" value="texturaDeformabilitat1" />
						<c:param name="onclick" value="checkunic(this,'texturaDef')"></c:param>
					</c:import>
				</td>
				<td style="text-align: center;">&nbsp;
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="checkbox" />
						<c:param name="path" value="formData.texturaDeformabilitat2" />
						<c:param name="camp" value="texturaDeformabilitat2" />
						<c:param name="onclick" value="checkunic(this,'texturaDef')"></c:param>
					</c:import>
				</td>
				<td style="text-align: center;"><c:if test="${bota.tipusOlivaTaula == 0}"><fmt:message key="autocontrol.textura.albercoc"/></c:if><c:if test="${bota.tipusOlivaTaula == 1}"><fmt:message key="autocontrol.textura.albercoc.madur"/></c:if>
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="checkbox" />
						<c:param name="path" value="formData.texturaDeformabilitat3" />
						<c:param name="camp" value="texturaDeformabilitat3" />
						<c:param name="onclick" value="checkunic(this,'texturaDef')"></c:param>
					</c:import>
				</td>
				<td style="text-align: center;">&nbsp;
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="checkbox" />
						<c:param name="path" value="formData.texturaDeformabilitat4" />
						<c:param name="camp" value="texturaDeformabilitat4" />
						<c:param name="onclick" value="checkunic(this,'texturaDef')"></c:param>
					</c:import>
				</td>
				<td style="text-align: center;"><fmt:message key="autocontrol.textura.pansa"/>
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="checkbox" />
						<c:param name="path" value="formData.texturaDeformabilitat5" />
						<c:param name="camp" value="texturaDeformabilitat5" />
						<c:param name="onclick" value="checkunic(this,'texturaDef')"></c:param>
					</c:import>
				</td>
				<td style="text-align: center;">&nbsp;
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="checkbox" />
						<c:param name="path" value="formData.OKtexturaDeformabilitatCorrecte" />
						<c:param name="camp" value="OKtexturaDeformabilitatCorrecte" />
						<c:param name="disabled" value="disabled" />
					</c:import>
				</td>		
			</tr>
		</c:if>
		<c:if test="${bota.tipusOlivaTaula == 1}">
			<tr>
				<th><fmt:message key="autocontrol.textura.elasticitat"/></th>
				<td style="text-align: center;"><fmt:message key="autocontrol.textura.ametlla"/>
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="checkbox" />
						<c:param name="path" value="formData.texturaElasticitat1" />
						<c:param name="camp" value="texturaElasticitat1" />
						<c:param name="onclick" value="checkunic(this,'texturaEla')"></c:param>
					</c:import>
				</td>
				<td style="text-align: center;">&nbsp;
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="checkbox" />
						<c:param name="path" value="formData.texturaElasticitat2" />
						<c:param name="camp" value="texturaElasticitat2" />
						<c:param name="onclick" value="checkunic(this,'texturaEla')"></c:param>
					</c:import>
				</td>
				<td style="text-align: center;"><fmt:message key="autocontrol.textura.xampinyo"/>
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="checkbox" />
						<c:param name="path" value="formData.texturaElasticitat3" />
						<c:param name="camp" value="texturaElasticitat3" />
						<c:param name="onclick" value="checkunic(this,'texturaEla')"></c:param>
					</c:import>
				</td>
				<td style="text-align: center;">&nbsp;
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="checkbox" />
						<c:param name="path" value="formData.texturaElasticitat4" />
						<c:param name="camp" value="texturaElasticitat4" />
						<c:param name="onclick" value="checkunic(this,'texturaEla')"></c:param>
					</c:import>
				</td>
				<td style="text-align: center;"><fmt:message key="autocontrol.textura.farcida"/>
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="checkbox" />
						<c:param name="path" value="formData.texturaElasticitat5" />
						<c:param name="camp" value="texturaElasticitat5" />
						<c:param name="onclick" value="checkunic(this,'texturaEla')"></c:param>
					</c:import>
				</td>
				<td style="text-align: center;">&nbsp;
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="checkbox" />
						<c:param name="path" value="formData.OKtexturaElasticitatCorrecte" />
						<c:param name="camp" value="OKtexturaElasticitatCorrecte" />
						<c:param name="disabled" value="disabled" />
					</c:import>
				</td>		
			</tr>
		</c:if>
		<c:if test="${bota.tipusOlivaTaula == 2}">
			<tr>
				<th class="ancho32" rowspan="1" style="padding-left: 10px; padding-right: 10px;"><fmt:message key="autocontrol.textura.tacte"/></th>
				<th><fmt:message key="autocontrol.textura.suavitat"/></th>
				<td style="text-align: center;"><fmt:message key="autocontrol.textura.coliflor"/>
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="checkbox" />
						<c:param name="path" value="formData.texturaSuavitat1" />
						<c:param name="camp" value="texturaSuavitat1" />
						<c:param name="onclick" value="checkunic(this,'texturaSua')"></c:param>
					</c:import>
				</td>
				<td style="text-align: center;">&nbsp;
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="checkbox" />
						<c:param name="path" value="formData.texturaSuavitat2" />
						<c:param name="camp" value="texturaSuavitat2" />
						<c:param name="onclick" value="checkunic(this,'texturaSua')"></c:param>
					</c:import>
				</td>
				<td style="text-align: center;"><fmt:message key="autocontrol.textura.alberginia"/>
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="checkbox" />
						<c:param name="path" value="formData.texturaSuavitat3" />
						<c:param name="camp" value="texturaSuavitat3" />
						<c:param name="onclick" value="checkunic(this,'texturaSua')"></c:param>
					</c:import>
				</td>
				<td style="text-align: center;">&nbsp;
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="checkbox" />
						<c:param name="path" value="formData.texturaSuavitat4" />
						<c:param name="camp" value="texturaSuavitat4" />
						<c:param name="onclick" value="checkunic(this,'texturaSua')"></c:param>
					</c:import>
				</td>
				<td style="text-align: center;"><fmt:message key="autocontrol.textura.melicoto"/>
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="checkbox" />
						<c:param name="path" value="formData.texturaSuavitat5" />
						<c:param name="camp" value="texturaSuavitat5" />
						<c:param name="onclick" value="checkunic(this,'texturaSua')"></c:param>
					</c:import>
				</td>
				<td style="text-align: center;">&nbsp;
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="checkbox" />
						<c:param name="path" value="formData.OKtexturaSuavitatCorrecte" />
						<c:param name="camp" value="OKtexturaSuavitatCorrecte" />
						<c:param name="disabled" value="disabled" />
					</c:import>
				</td>		
			</tr>
		</c:if>
		<tr>
			<th class="ancho32" rowspan="<c:if test='${bota.tipusOlivaTaula == 0}'>4</c:if><c:if test='${bota.tipusOlivaTaula != 0}'>3</c:if>" style="padding-left: 10px; padding-right: 10px;"><fmt:message key="autocontrol.textura.enboca"/></th>
			<th><fmt:message key="autocontrol.textura.fermesa"/></th>
			<td style="text-align: center;"><fmt:message key="autocontrol.textura.caqui"/>
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="checkbox" />
					<c:param name="path" value="formData.texturabocaFermesaEnBoca1" />
					<c:param name="camp" value="texturabocaFermesaEnBoca1" />
					<c:param name="onclick" value="checkunic(this,'texturaboca')"></c:param>
				</c:import>
			</td>
			<td style="text-align: center;">&nbsp;
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="checkbox" />
					<c:param name="path" value="formData.texturabocaFermesaEnBoca2" />
					<c:param name="camp" value="texturabocaFermesaEnBoca2" />
					<c:param name="onclick" value="checkunic(this,'texturaboca')"></c:param>
				</c:import>
			</td>
			<td style="text-align: center;"><c:choose><c:when test="${bota.tipusOlivaTaula == 0}"><fmt:message key="autocontrol.textura.cirera"/></c:when><c:when test="${bota.tipusOlivaTaula == 1}"><fmt:message key="autocontrol.textura.pera.madura"/></c:when><c:when test="${bota.tipusOlivaTaula == 2}"><fmt:message key="autocontrol.textura.pera"/></c:when></c:choose>
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="checkbox" />
					<c:param name="path" value="formData.texturabocaFermesaEnBoca3" />
					<c:param name="camp" value="texturabocaFermesaEnBoca3" />
					<c:param name="onclick" value="checkunic(this,'texturaboca')"></c:param>
				</c:import>
			</td>
			<td style="text-align: center;">&nbsp;
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="checkbox" />
					<c:param name="path" value="formData.texturabocaFermesaEnBoca4" />
					<c:param name="camp" value="texturabocaFermesaEnBoca4" />
					<c:param name="onclick" value="checkunic(this,'texturaboca')"></c:param>
				</c:import>
			</td>
			<td style="text-align: center;"><fmt:message key="autocontrol.textura.pastanaga"/>
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="checkbox" />
					<c:param name="path" value="formData.texturabocaFermesaEnBoca5" />
					<c:param name="camp" value="texturabocaFermesaEnBoca5" />
					<c:param name="onclick" value="checkunic(this,'texturaboca')"></c:param>
				</c:import>
			</td>
			<td style="text-align: center;">&nbsp;
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="checkbox" />
					<c:param name="path" value="formData.OKtexturabocaFermesaEnBocaCorrecte" />
					<c:param name="camp" value="OKtexturabocaFermesaEnBocaCorrecte" />
					<c:param name="disabled" value="disabled" />
				</c:import>
			</td>		
		</tr>
		<c:if test="${bota.tipusOlivaTaula == 0}">
			<tr>
				<th><fmt:message key="autocontrol.textura.friabilitat"/></th>
				<td style="text-align: center;"><fmt:message key="autocontrol.textura.platan"/>
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="checkbox" />
						<c:param name="path" value="formData.texturaFriabilitat1" />
						<c:param name="camp" value="texturaFriabilitat1" />
						<c:param name="onclick" value="checkunic(this,'texturaFri')"></c:param>
					</c:import>
				</td>
				<td style="text-align: center;">&nbsp;
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="checkbox" />
						<c:param name="path" value="formData.texturaFriabilitat2" />
						<c:param name="camp" value="texturaFriabilitat2" />
						<c:param name="onclick" value="checkunic(this,'texturaFri')"></c:param>
					</c:import>
				</td>
				<td style="text-align: center;"><fmt:message key="autocontrol.textura.poma"/>
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="checkbox" />
						<c:param name="path" value="formData.texturaFriabilitat3" />
						<c:param name="camp" value="texturaFriabilitat3" />
						<c:param name="onclick" value="checkunic(this,'texturaFri')"></c:param>
					</c:import>
				</td>
				<td style="text-align: center;">&nbsp;
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="checkbox" />
						<c:param name="path" value="formData.texturaFriabilitat4" />
						<c:param name="camp" value="texturaFriabilitat4" />
						<c:param name="onclick" value="checkunic(this,'texturaFri')"></c:param>
					</c:import>
				</td>
				<td style="text-align: center;"><fmt:message key="autocontrol.textura.mongeta.blanca"/>
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="checkbox" />
						<c:param name="path" value="formData.texturaFriabilitat5" />
						<c:param name="camp" value="texturaFriabilitat5" />
						<c:param name="onclick" value="checkunic(this,'texturaFri')"></c:param>
					</c:import>
				</td>
				<td style="text-align: center;">&nbsp;
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="checkbox" />
						<c:param name="path" value="formData.OKtexturaFriabilitatCorrecte" />
						<c:param name="camp" value="OKtexturaFriabilitatCorrecte" />
						<c:param name="disabled" value="disabled" />
					</c:import>
				</td>		
			</tr>
		</c:if>
		<tr>
			<th><fmt:message key="autocontrol.textura.cohesio"/></th>
			<td style="text-align: center;"><fmt:message key="autocontrol.textura.pera"/>
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="checkbox" />
					<c:param name="path" value="formData.texturaCohesio1" />
					<c:param name="camp" value="texturaCohesio1" />
					<c:param name="onclick" value="checkunic(this,'texturaCoh')"></c:param>
				</c:import>
			</td>
			<td style="text-align: center;">&nbsp;
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="checkbox" />
					<c:param name="path" value="formData.texturaCohesio2" />
					<c:param name="camp" value="texturaCohesio2" />
					<c:param name="onclick" value="checkunic(this,'texturaCoh')"></c:param>
				</c:import>
			</td>
			<td style="text-align: center;"><fmt:message key="autocontrol.textura.platan"/>
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="checkbox" />
					<c:param name="path" value="formData.texturaCohesio3" />
					<c:param name="camp" value="texturaCohesio3" />
					<c:param name="onclick" value="checkunic(this,'texturaCoh')"></c:param>
				</c:import>
			</td>
			<td style="text-align: center;">&nbsp;
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="checkbox" />
					<c:param name="path" value="formData.texturaCohesio4" />
					<c:param name="camp" value="texturaCohesio4" />
					<c:param name="onclick" value="checkunic(this,'texturaCoh')"></c:param>
				</c:import>
			</td>
			<td style="text-align: center;"><fmt:message key="autocontrol.textura.mongeta.bullida"/>
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="checkbox" />
					<c:param name="path" value="formData.texturaCohesio5" />
					<c:param name="camp" value="texturaCohesio5" />
					<c:param name="onclick" value="checkunic(this,'texturaCoh')"></c:param>
				</c:import>
			</td>
			<td style="text-align: center;">&nbsp;
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="checkbox" />
					<c:param name="path" value="formData.OKtexturaCohesioCorrecte" />
					<c:param name="camp" value="OKtexturaCohesioCorrecte" />
					<c:param name="disabled" value="disabled" />
				</c:import>
			</td>		
		</tr>
		<tr>
			<th><fmt:message key="autocontrol.textura.untuositat"/></th>
			<td style="text-align: center;"><fmt:message key="autocontrol.textura.taronja"/>
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="checkbox" />
					<c:param name="path" value="formData.texturaUntuositat1" />
					<c:param name="camp" value="texturaUntuositat1" />
					<c:param name="onclick" value="checkunic(this,'texturaUnt')"></c:param>
				</c:import>
			</td>
			<td style="text-align: center;">&nbsp;
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="checkbox" />
					<c:param name="path" value="formData.texturaUntuositat2" />
					<c:param name="camp" value="texturaUntuositat2" />
					<c:param name="onclick" value="checkunic(this,'texturaUnt')"></c:param>
				</c:import>
			</td>
			<td style="text-align: center;"><fmt:message key="autocontrol.textura.cacahuet"/>
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="checkbox" />
					<c:param name="path" value="formData.texturaUntuositat3" />
					<c:param name="camp" value="texturaUntuositat3" />
					<c:param name="onclick" value="checkunic(this,'texturaUnt')"></c:param>
				</c:import>
			</td>
			<td style="text-align: center;">&nbsp;
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="checkbox" />
					<c:param name="path" value="formData.texturaUntuositat4" />
					<c:param name="camp" value="texturaUntuositat4" />
					<c:param name="onclick" value="checkunic(this,'texturaUnt')"></c:param>
				</c:import>
			</td>
			<td style="text-align: center;"><fmt:message key="autocontrol.textura.oli"/>
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="checkbox" />
					<c:param name="path" value="formData.texturaUntuositat5" />
					<c:param name="camp" value="texturaUntuositat5" />
					<c:param name="onclick" value="checkunic(this,'texturaUnt')"></c:param>
				</c:import>
			</td>
			<td style="text-align: center;">&nbsp;
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="checkbox" />
					<c:param name="path" value="formData.OKtexturaUntuositatCorrecte" />
					<c:param name="camp" value="OKtexturaUntuositatCorrecte" />
					<c:param name="disabled" value="disabled" />
				</c:import>
			</td>		
		</tr>
	</table> 
	<br/>
	<!-- SABOR -->
	<div><label for="analiticaSensorial"><b>4.-<fmt:message key="autocontrol.sabor"/></b></label></div>
	
	<div class="separadorH"></div>
	<table>
		<tr>
			<th colspan="3" style="text-align: center;"><fmt:message key="autocontrol.sabor"/></th>
		</tr>
		<tr>
			<c:if test="${bota.tipusOlivaTaula != 1}">
				<th class="ancho75" style="text-align: center;"><fmt:message key="autocontrol.sabor.acid"/></th>
			</c:if>
			<th class="ancho75" style="text-align: center;"><fmt:message key="autocontrol.sabor.salat"/></th>
			<c:if test="${bota.tipusOlivaTaula != 2}">
				<th class="ancho75" style="text-align: center;"><fmt:message key="autocontrol.sabor.amarg"/></th>
			</c:if>
			<th class="ancho75" style="text-align: center;"><fmt:message key="autocontrol.sabor.altres"/></th>
			<th class="ancho75" style="text-align: center;"><fmt:message key="autocontrol.correcte"/></th>
		</tr>
		<tr>
			<c:if test="${bota.tipusOlivaTaula != 1}">
				<td style="text-align: center;">
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="checkbox" />
						<c:param name="path" value="formData.saborAcid" />
						<c:param name="camp" value="saborAcid" />
						<c:param name="onclick" value="checkunic(this,'sabor')"></c:param>
					</c:import>
				</td>
			</c:if>
			<td style="text-align: center;">
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="checkbox" />
					<c:param name="path" value="formData.saborSalat" />
					<c:param name="camp" value="saborSalat" />
					<c:param name="onclick" value="checkunic(this,'sabor')"></c:param>
				</c:import>
			</td>
			<c:if test="${bota.tipusOlivaTaula != 2}">
				<td style="text-align: center;">
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="checkbox" />
						<c:param name="path" value="formData.saborAmarg" />
						<c:param name="camp" value="saborAmarg" />
						<c:param name="onclick" value="checkunic(this,'sabor')"></c:param>
					</c:import>
				</td>
			</c:if>
			<td style="text-align: center;">
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="text" />
					<c:param name="path" value="formData.saborAltres" />
					<c:param name="camp" value="saborAltres" />
				</c:import>
			</td>
			<td style="text-align: center;">
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="checkbox" />
					<c:param name="path" value="formData.OKsaborCorrecte" />
					<c:param name="camp" value="OKsaborCorrecte" />
					<c:param name="disabled" value="disabled" />
				</c:import>
			</td>

		</tr>
	</table> 

	<br/>
	<!-- SENSACIO -->
	<div><label for="analiticaSensorial"><b>5.-<fmt:message key="autocontrol.sensacio"/></b></label></div>
	
	
	<div class="separadorH"></div>
	<table>
		<tr>
			<th colspan="3" style="text-align: center;"><fmt:message key="autocontrol.sensacio"/></th>
		</tr>
		<tr>
			<th class="ancho75" style="text-align: center;"><fmt:message key="autocontrol.sensacio.astringent"/></th>
			<th class="ancho75" style="text-align: center;"><fmt:message key="autocontrol.sensacio.picant"/></th>
			<th class="ancho75" style="text-align: center;"><fmt:message key="autocontrol.sensacio.altres"/></th>
			<th class="ancho75" style="text-align: center;"><fmt:message key="autocontrol.correcte"/></th>
		</tr>
		<tr>
			<td style="text-align: center;">
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="checkbox" />
					<c:param name="path" value="formData.sensacioAstringent" />
					<c:param name="camp" value="sensacioAstringent" />
					<c:param name="onclick" value="checkunic(this,'sensacio')"></c:param>
				</c:import>
			</td>
			<td style="text-align: center;">
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="checkbox" />
					<c:param name="path" value="formData.sensacioPicant" />
					<c:param name="camp" value="sensacioPicant" />
					<c:param name="onclick" value="checkunic(this,'sensacio')"></c:param>
				</c:import>
			</td>
			<td style="text-align: center;">
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="text" />
					<c:param name="path" value="formData.sensacioAltres" />
					<c:param name="camp" value="sensacioaltres" />
					<c:param name="onclick" value="checkunic(this,'sensacio')"></c:param>
				</c:import>
			</td>
			<td style="text-align: center;">
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="checkbox" />
					<c:param name="path" value="formData.OKsensacioCorrecte" />
					<c:param name="camp" value="OKsensacioCorrecte" />
					<c:param name="disabled" value="disabled" />
				</c:import>
			</td>

		</tr>
	</table> 

	<br/>
	<!-- REGUST -->
	<div><label for="analiticaSensorial"><b>6.-<fmt:message key="autocontrol.regust"/></b></label></div>
	
	
	<div class="separadorH"></div>
	<table>
		<tr>
			<th colspan="3" style="text-align: center;"><fmt:message key="autocontrol.regust.durada"/></th>
		</tr>
		<tr>
			<th class="ancho75" style="text-align: center;"><fmt:message key="autocontrol.regust.baixa"/></th>
			<th class="ancho75" style="text-align: center;"><fmt:message key="autocontrol.regust.mitja"/></th>
			<th class="ancho75" style="text-align: center;"><fmt:message key="autocontrol.regust.alta"/></th>
			<th class="ancho75" style="text-align: center;"><fmt:message key="autocontrol.correcte"/></th>
		</tr>
		<tr>
			<td style="text-align: center;">
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="checkbox" />
					<c:param name="path" value="formData.regustBaix" />
					<c:param name="camp" value="regustBaix" />
					<c:param name="onclick" value="checkunic(this,'regust')"></c:param>
				</c:import>
			</td>
			<td style="text-align: center;">
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="checkbox" />
					<c:param name="path" value="formData.regustMitja" />
					<c:param name="camp" value="regustMitja" />
					<c:param name="onclick" value="checkunic(this,'regust')"></c:param>
				</c:import>
			</td>
			<td style="text-align: center;">
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="checkbox" />
					<c:param name="path" value="formData.regustProlongat" />
					<c:param name="camp" value="regustProlongat" />
					<c:param name="onclick" value="checkunic(this,'regust')"></c:param>
				</c:import>
			</td>
			<td style="text-align: center;">
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="checkbox" />
					<c:param name="path" value="formData.OKregustCorrecte" />
					<c:param name="camp" value="OKregustCorrecte" />
					<c:param name="disabled" value="disabled" />
				</c:import>
			</td>

		</tr>
	</table> 

	<br/>

	<div class="etiqueta etiquetaEnLinea <c:out value="${param.required}"/><c:if test="${not empty status.errorMessage}"> error</c:if>">
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="checkbox" />
		<c:param name="path" value="formData.apta" />
		<c:param name="title"><fmt:message key="autocontrol.apta"/></c:param>
		<c:param name="camp" value="apta" />
		<c:param name="disabled" value="disabled" />
	</c:import></div>
	
	<div class="separadorH"></div>

	<div id="mesuresForm" class="campoForm <c:out value="${param.required}"/><c:if test="${not empty status.errorMessage}"> error</c:if>">
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="textarea" />
					<c:param name="path" value="formData.mesuresCorrectores" />
					<c:param name="title"><fmt:message key="autocontrol.mesures.correctores"/>:</c:param>
					<c:param name="camp" value="mesuresCorrectores" />
				</c:import>
			</div>

	<div class="separadorH"></div>
	<div class="botonesForm">
		<c:if test="${(not empty esDoControlador  || not empty esTafona || not empty esEnvasador) && empty formData.id}">
			<div id="guardarForm" class="btnCorto"
				onclick="guardarFormulari()"
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
	
	<c:if test="${(empty formData.id)}">
		<form id="tornarForm" action="ProcesInici.html" class="seguit">
			<input type="hidden" id="tipusProces" name="tipusProces" value="22">
		</form>
	</c:if>
	
	<c:if test="${(not empty formData.id and empty formdata.lot)}">
		<form id="tornarForm" action="javascript:history.go(-1);" class="seguit">
		</form>
	</c:if>
	
	<c:if test="${(not empty formData.id and not empty formdata.lot)}">
		<form id="tornarForm" action="ProcesInici.html" class="seguit">
			<input type="hidden" id="tipusProces" name="tipusProces" value="22" />
			<input type="hidden" id="pas" name="pas" value="1" /> 
			<input type="hidden" id="zonaId" name="zonaId" value="<c:out value="${formData.lot.zona.id}"/>" /> 
			<input type="hidden" id="establimentId" name="establimentId" value="<c:out value="${establiment.id}"/>" />
		</form>
	</c:if>

	<form id="pdfForm" action="GenerarPdf.html" method="post" class="seguit">
		<input type="hidden" id="tipus" name="tipus" value="7" /> 
		<input type="hidden" id="idAna" name="idAna" value="<c:out value="${formData.id}"/>" /> 
		<input type="hidden" id="idEst" name="idEst" value="<c:out value="${establiment.id}"/>" /> 
		<input type="hidden" id="idDip" name="idDip" value="<c:out value="${formData.lot.id}"/>" />
	</form>

</body>
</html>