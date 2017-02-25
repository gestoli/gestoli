<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ page import="es.caib.gestoli.front.util.*"%>
<%@ page import="java.util.ResourceBundle"%>
<%@ page import="java.util.Locale"%>
<%@ page import="java.util.List"%>
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
</c:choose> <fmt:message key="analiticaParametreTipus.tipusdemant" /></title>


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

<script type="text/javascript">
	// <![CDATA[
	$(document).ready(function(){
		responPregunta('p1');
		responPregunta('p12');
		responPregunta('p13');
		responPregunta('p2');
		responPregunta('p22');
		responPregunta('p31');
		responPregunta('p32');			
		responPregunta('p321');	
		responPregunta('p33');
		responPregunta('p34');
		responPregunta('p35');
		responPregunta('p5');
	});
	
	
/*	window.onload = function() {
			responPregunta('p1');
			responPregunta('p12');
			responPregunta('p13');
			responPregunta('p2');
			responPregunta('p22');
			responPregunta('p31');
			responPregunta('p32');			
			responPregunta('p321');	
			responPregunta('p33');
			responPregunta('p34');
			responPregunta('p35');
			responPregunta('p5');
			alert("entra");
		}
		*/
	function responPregunta(pregunta) {
		var campSi = document.getElementById(pregunta+"Si");
		var campNo = document.getElementById(pregunta+"No");
		if (campSi.checked == true) {
			document.getElementById(pregunta).value = "S";
		} else if (campNo.checked == true) {
			document.getElementById(pregunta).value = "N";
		}
		
		if (pregunta == "p1"){
			if (campSi.checked == true) {
				document.getElementById("punto1").style.display = "block";
			} else {
				document.getElementById("punto1").style.display = "none";
			}
		} else if (pregunta == "p12"){
			if (campSi.checked == true || campNo.checked == true){
				document.getElementById("punto12").style.display = "block";
			} else {
				document.getElementById("punto12").style.display = "none";
			}
		} else if (pregunta == "p13"){
			if (campSi.checked == true || campNo.checked == true){
				document.getElementById("punto13").style.display = "block";
			} else {
				document.getElementById("punto13").style.display = "none";
			}
		} else if (pregunta == "p2"){
			if (campSi.checked == true) {
				document.getElementById("punto2").style.display = "block";
			} else {
				document.getElementById("punto2").style.display = "none";
			}
		} else if (pregunta == "p22"){
			if (campSi.checked == true || campNo.checked == true){
				document.getElementById("punto22").style.display = "block";
			} else {
				document.getElementById("punto22").style.display = "none";
			}
		} else if (pregunta == "p31"){
			if (campSi.checked == true || campNo.checked == true){
				document.getElementById("punto31").style.display = "block";
			} else {
				document.getElementById("punto31").style.display = "none";
			}
		} else if (pregunta == "p32"){
			if (campSi.checked == true){
				document.getElementById("punto32").style.display = "block";
			} else {
				document.getElementById("punto32").style.display = "none";
			}
		} else if (pregunta == "p321"){
			if (campSi.checked == true || campNo.checked == true){
				document.getElementById("punto321").style.display = "block";
			} else {
				document.getElementById("punto321").style.display = "none";
			}
		} else if (pregunta == "p33"){
			if (campSi.checked == true){
				document.getElementById("punto33").style.display = "block";
			} else {
				document.getElementById("punto33").style.display = "none";
			}
		} else if (pregunta == "p34"){
			if (campSi.checked == true){
				document.getElementById("punto34").style.display = "block";
			} else {
				document.getElementById("punto34").style.display = "none";
			}
		} else if (pregunta == "p35"){
			if (campSi.checked == true){
				document.getElementById("punto35").style.display = "block";
			} else {
				document.getElementById("punto35").style.display = "none";
			}
		} else if (pregunta == "p5"){
			if (campSi.checked == true || campNo.checked == true){
				document.getElementById("punto5").style.display = "block";
			} else {
				document.getElementById("punto5").style.display = "none";
			}
		} 
	
	}
	
	// ]]>
</script>

</head>
<body>

<form id="formulario" name="APPCCPlaVerificacioForm" action="QualitatAPPCCPlaVerificacioForm.html" method="post" class="extended seguit" onsubmit="">
	<c:set var="disabled" value="" />
	<c:if test="${empty esEstAdministrador && empty esEstEncarregat}">
		<c:set var="disabled" value="true" />
	</c:if>
	
	<c:if test="${not empty formData.id}">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="hidden" />
			<c:param name="path" value="formData.id" />
			<c:param name="camp" value="id" />
		</c:import>
	</c:if> 

	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="select" />
		<c:param name="path" value="formData.idResponsable" />
		<c:param name="title">
			<fmt:message key="qualitat.appcc.plaVerificacio.camp.responsable" />
		</c:param>
		<c:param name="camp" value="campo_idResponsable" />
		<c:param name="name" value="idResponsable" />
		<c:param name="required" value="required" />
		<c:param name="selectItems" value="personal" />
		<c:param name="selectItemsId" value="id" />
		<c:param name="selectItemsValue" value="nom" />
		<c:param name="selectSelectedValue" value="${formData.idResponsable}" />
		<c:param name="clase" value="campoFormGrande conMargen" />
		<c:param name="disabled" value="${disabled}" />
	</c:import>

	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="calendar" />
		<c:param name="path" value="formData.dataRealitzacio" />
		<c:param name="title">
			<fmt:message key="qualitat.appcc.plaVerificacio.camp.dataRealitzacio" />
		</c:param>
		<c:param name="camp" value="campo_dataRealitzacio" />
		<c:param name="name" value="dataRealitzacio" />
		<c:param name="required" value="required" />
		<c:param name="maxlength" value="10" />
		<c:param name="aclaracio">
			<fmt:message key="proces.aclaracio.formatdata" />
		</c:param>
		<c:param name="clase" value="campoForm165" />
		<c:param name="disabled" value="${disabled}" />
	</c:import>

	<div class="separadorH"></div>
	<br />
		
	<h3><fmt:message key="qualitat.appcc.plaVerificacio.titol.formulari" /></h3>
	<div id="preguntas">
		<div class="separadorH"></div>
		<br />
        	<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="radioDecisio"/>
	    		<c:param name="path" value="formData.p1"/>
	    		<c:param name="title"><fmt:message key="qualitat.appcc.plaVerificacio.camp.p1"/></c:param>
	        	<c:param name="camp" value="p1"/>
	        	<c:param name="value" value="${formData.p1}"/>
	        	<c:param name="onchange" value="responPregunta('p1');"/>
			<c:param name="disabled" value="${disabled}" />
	        </c:import>
	        
		<div id="punto1" style="padding-left: 15px;">
			<div class="separadorH"></div>
		        <br />
			<c:import url="comu/CampFormulari.jsp">
				<c:param name="tipus" value="text" />
				<c:param name="path" value="formData.p11" />
				<c:param name="title">
					<fmt:message key="qualitat.appcc.plaVerificacio.camp.p11" />
				</c:param>
				<c:param name="camp" value="campo_p11" />
				<c:param name="name" value="p11" />
				<c:param name="required" value="required" />
				<c:param name="maxlength" value="5" />
				<c:param name="clase" value="campoFormPequeno" />
				<c:param name="disabled" value="${disabled}" />
			</c:import> 	
			<div class="separadorH"></div>
			<c:import url="comu/CampFormulari.jsp">
				<c:param name="tipus" value="radioDecisio"/>
		    		<c:param name="path" value="formData.p12"/>
		    		<c:param name="title"><fmt:message key="qualitat.appcc.plaVerificacio.camp.p12"/></c:param>
				<c:param name="camp" value="p12"/>
				<c:param name="value" value="${formData.p12}"/>
				<c:param name="onchange" value="responPregunta('p12');"/>
				<c:param name="disabled" value="${disabled}" />
			</c:import>
			<br />
			<div id="punto12" style="padding-left: 15px;">
				<div class="separadorH"></div>
				<div id="observacionesForm" class="campoForm">	
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="textareaMedio" />
						<c:param name="path" value="formData.p12_comments" />
						<c:param name="title">
							<fmt:message key="qualitat.appcc.plaVerificacio.camp.comentaris" />
						</c:param>
						<c:param name="camp" value="campo_p12_comments" />
						<c:param name="name" value="p12_comments" />
						<c:param name="required" value="required" />
						<c:param name="maxlength" value="500" />
						<c:param name="disabled" value="${disabled}" />
					</c:import>
				</div> 
			</div>
			<div class="separadorH"></div>
			<c:import url="comu/CampFormulari.jsp">
				<c:param name="tipus" value="radioDecisio"/>
		    		<c:param name="path" value="formData.p13"/>
		    		<c:param name="title"><fmt:message key="qualitat.appcc.plaVerificacio.camp.p13"/></c:param>
				<c:param name="camp" value="p13"/>
				<c:param name="value" value="${formData.p13}"/>
				<c:param name="onchange" value="responPregunta('p13');"/>
				<c:param name="disabled" value="${disabled}" />
			</c:import>
			<br />
			<div id="punto13" style="padding-left: 15px;">
				<div class="separadorH"></div>
				<div id="observacionesForm" class="campoForm">	
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="textareaMedio" />
						<c:param name="path" value="formData.p13_comments" />
						<c:param name="title">
							<fmt:message key="qualitat.appcc.plaVerificacio.camp.comentaris" />
						</c:param>
						<c:param name="camp" value="campo_p13_comments" />
						<c:param name="name" value="p13_comments" />
						<c:param name="required" value="required" />
						<c:param name="maxlength" value="500" />
						<c:param name="disabled" value="${disabled}" />
					</c:import>
				</div> 	
			</div>
			<div class="separadorH"></div>
			<div id="observacionesForm" class="campoForm">	
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="textareaMedio" />
					<c:param name="path" value="formData.p14" />
					<c:param name="title">
						<fmt:message key="qualitat.appcc.plaVerificacio.camp.p14" />
					</c:param>
					<c:param name="camp" value="campo_p14" />
					<c:param name="name" value="p14" />
					<c:param name="required" value="required" />
					<c:param name="maxlength" value="500" />
					<c:param name="disabled" value="${disabled}" />
				</c:import>
			</div> 
			<div id="observacionesForm" class="campoForm">	
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="textareaMedio" />
					<c:param name="path" value="formData.p15" />
					<c:param name="title">
						<fmt:message key="qualitat.appcc.plaVerificacio.camp.p15" />
					</c:param>
					<c:param name="camp" value="campo_p15" />
					<c:param name="name" value="p15" />
					<c:param name="required" value="required" />
					<c:param name="maxlength" value="500" />
					<c:param name="disabled" value="${disabled}" />
				</c:import>
			</div> 
		</div>
			
			
		<div class="separadorH"></div>
		<br /><br />
        	<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="radioDecisio"/>
	    		<c:param name="path" value="formData.p2"/>
	    		<c:param name="title"><fmt:message key="qualitat.appcc.plaVerificacio.camp.p2"/></c:param>
	        	<c:param name="camp" value="p2"/>
	        	<c:param name="value" value="${formData.p2}"/>
	        	<c:param name="onchange" value="responPregunta('p2');"/>
			<c:param name="disabled" value="${disabled}" />
	        </c:import>
		<div id="punto2" style="padding-left: 15px;">
			<div class="separadorH"></div>
		        <br />
			<div id="observacionesForm" class="campoForm">	
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="textareaMedio" />
					<c:param name="path" value="formData.p21" />
					<c:param name="title">
						<fmt:message key="qualitat.appcc.plaVerificacio.camp.p21" />
					</c:param>
					<c:param name="camp" value="campo_p21" />
					<c:param name="name" value="p21" />
					<c:param name="required" value="required" />
					<c:param name="maxlength" value="500" />
					<c:param name="disabled" value="${disabled}" />
				</c:import>
			</div> 
			<div class="separadorH"></div>
			<c:import url="comu/CampFormulari.jsp">
				<c:param name="tipus" value="radioDecisio"/>
		    		<c:param name="path" value="formData.p22"/>
		    		<c:param name="title"><fmt:message key="qualitat.appcc.plaVerificacio.camp.p22"/></c:param>
				<c:param name="camp" value="p22"/>
				<c:param name="value" value="${formData.p22}"/>
				<c:param name="onchange" value="responPregunta('p22');"/>
				<c:param name="disabled" value="${disabled}" />
			</c:import>
			<div id="punto22" style="padding-left: 15px;">
				<div class="separadorH"></div>
				<div id="observacionesForm" class="campoForm">	
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="textareaMedio" />
						<c:param name="path" value="formData.p22_comments" />
						<c:param name="title">
							<fmt:message key="qualitat.appcc.plaVerificacio.camp.comentaris" />
						</c:param>
						<c:param name="camp" value="campo_p22_comments" />
						<c:param name="name" value="p22_comments" />
						<c:param name="required" value="required" />
						<c:param name="maxlength" value="500" />
						<c:param name="disabled" value="${disabled}" />
					</c:import>
				</div> 	
			</div>
		</div>
		
		
		<div class="separadorH"></div>
		<br /><br />
		<fmt:message key="qualitat.appcc.plaVerificacio.titol.p3" />
		<div id="punto3" style="padding-left: 15px;">
			<div class="separadorH"></div>
		        <br />
			<c:import url="comu/CampFormulari.jsp">
				<c:param name="tipus" value="radioDecisio"/>
		    		<c:param name="path" value="formData.p31"/>
		    		<c:param name="title"><fmt:message key="qualitat.appcc.plaVerificacio.camp.p31"/></c:param>
				<c:param name="camp" value="p31"/>
				<c:param name="value" value="${formData.p31}"/>
				<c:param name="onchange" value="responPregunta('p31');"/>
				<c:param name="disabled" value="${disabled}" />
			</c:import>
			<div class="separadorH"></div>
			<br />
			<div id="punto31" style="padding-left: 15px;">
				<div id="observacionesForm" class="campoForm">	
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="textareaMedio" />
						<c:param name="path" value="formData.p31_comments" />
						<c:param name="title">
							<fmt:message key="qualitat.appcc.plaVerificacio.camp.comentaris" />
						</c:param>
						<c:param name="camp" value="campo_p31_comments" />
						<c:param name="name" value="p31_comments" />
						<c:param name="required" value="required" />
						<c:param name="maxlength" value="500" />
						<c:param name="disabled" value="${disabled}" />
					</c:import>
				</div> 	
			</div>
			<c:import url="comu/CampFormulari.jsp">
				<c:param name="tipus" value="radioDecisio"/>
		    		<c:param name="path" value="formData.p32"/>
		    		<c:param name="title"><fmt:message key="qualitat.appcc.plaVerificacio.camp.p32"/></c:param>
				<c:param name="camp" value="p32"/>
				<c:param name="value" value="${formData.p32}"/>
				<c:param name="onchange" value="responPregunta('p32');"/>
				<c:param name="disabled" value="${disabled}" />
			</c:import>
			<div class="separadorH"></div>
			<br />
			<div id="punto32" style="padding-left: 15px;">
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="radioDecisio"/>
			    		<c:param name="path" value="formData.p321"/>
			    		<c:param name="title"><fmt:message key="qualitat.appcc.plaVerificacio.camp.p321"/></c:param>
					<c:param name="camp" value="p321"/>
					<c:param name="value" value="${formData.p321}"/>
					<c:param name="onchange" value="responPregunta('p321');"/>
					<c:param name="disabled" value="${disabled}" />
				</c:import>
				<div class="separadorH"></div>
				<br />
				<div id="punto321" style="padding-left: 15px;">
					<div id="observacionesForm" class="campoForm">	
						<c:import url="comu/CampFormulari.jsp">
							<c:param name="tipus" value="textareaMedio" />
							<c:param name="path" value="formData.p321_comments" />
							<c:param name="title">
								<fmt:message key="qualitat.appcc.plaVerificacio.camp.comentaris" />
							</c:param>
							<c:param name="camp" value="campo_p321_comments" />
							<c:param name="name" value="p321_comments" />
							<c:param name="required" value="required" />
							<c:param name="maxlength" value="500" />
							<c:param name="disabled" value="${disabled}" />
						</c:import>
					</div> 	
				</div>
			</div>
			<c:import url="comu/CampFormulari.jsp">
				<c:param name="tipus" value="radioDecisio"/>
		    		<c:param name="path" value="formData.p33"/>
		    		<c:param name="title"><fmt:message key="qualitat.appcc.plaVerificacio.camp.p33"/></c:param>
				<c:param name="camp" value="p33"/>
				<c:param name="value" value="${formData.p33}"/>
				<c:param name="onchange" value="responPregunta('p33');"/>
				<c:param name="disabled" value="${disabled}" />
			</c:import>
			<div class="separadorH"></div>
			<br />
			<div id="punto33" style="padding-left: 15px;">
				<div id="observacionesForm" class="campoForm">	
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="textareaMedio" />
						<c:param name="path" value="formData.p331" />
						<c:param name="title">
							<fmt:message key="qualitat.appcc.plaVerificacio.camp.p331" />
						</c:param>
						<c:param name="camp" value="campo_p331" />
						<c:param name="name" value="p331" />
						<c:param name="required" value="required" />
						<c:param name="maxlength" value="500" />
						<c:param name="disabled" value="${disabled}" />
					</c:import>
				</div> 	
			</div>
			<c:import url="comu/CampFormulari.jsp">
				<c:param name="tipus" value="radioDecisio"/>
		    		<c:param name="path" value="formData.p34"/>
		    		<c:param name="title"><fmt:message key="qualitat.appcc.plaVerificacio.camp.p34"/></c:param>
				<c:param name="camp" value="p34"/>
				<c:param name="value" value="${formData.p34}"/>
				<c:param name="onchange" value="responPregunta('p34');"/>
				<c:param name="disabled" value="${disabled}" />
			</c:import>
			<div class="separadorH"></div>
			<br />
			<div id="punto34" style="padding-left: 15px;">
				<div id="observacionesForm" class="campoForm">	
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="textareaMedio" />
						<c:param name="path" value="formData.p341" />
						<c:param name="title">
							<fmt:message key="qualitat.appcc.plaVerificacio.camp.p341" />
						</c:param>
						<c:param name="camp" value="campo_p341" />
						<c:param name="name" value="p341" />
						<c:param name="required" value="required" />
						<c:param name="maxlength" value="500" />
						<c:param name="disabled" value="${disabled}" />
					</c:import>
				</div> 	
			</div>
			<c:import url="comu/CampFormulari.jsp">
				<c:param name="tipus" value="radioDecisio"/>
		    		<c:param name="path" value="formData.p35"/>
		    		<c:param name="title"><fmt:message key="qualitat.appcc.plaVerificacio.camp.p35"/></c:param>
				<c:param name="camp" value="p35"/>
				<c:param name="value" value="${formData.p35}"/>
				<c:param name="onchange" value="responPregunta('p35');"/>
				<c:param name="disabled" value="${disabled}" />
			</c:import>
			<div id="punto35" style="padding-left: 15px;">
				<div class="separadorH"></div>
				<br />
				<div id="observacionesForm" class="campoForm">	
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="textareaMedio" />
						<c:param name="path" value="formData.p351" />
						<c:param name="title">
							<fmt:message key="qualitat.appcc.plaVerificacio.camp.p351" />
						</c:param>
						<c:param name="camp" value="campo_p351" />
						<c:param name="name" value="p351" />
						<c:param name="required" value="required" />
						<c:param name="maxlength" value="500" />
						<c:param name="disabled" value="${disabled}" />
					</c:import>
				</div> 	
			</div>
		</div>
		
		
		<div class="separadorH"></div>
		<br /><br />
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="text" />
			<c:param name="path" value="formData.p4" />
			<c:param name="title">
				<fmt:message key="qualitat.appcc.plaVerificacio.camp.p4" />
			</c:param>
			<c:param name="camp" value="campo_p4" />
			<c:param name="name" value="p4" />
			<c:param name="required" value="required" />
			<c:param name="maxlength" value="10" />
			<c:param name="clase" value="campoFormPequeno" />
			<c:param name="disabled" value="${disabled}" />
		</c:import> 
		<div id="punto4" style="padding-left: 15px;">
			<div class="separadorH"></div>
			<div id="observacionesForm" class="campoForm">	
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="textareaMedio" />
					<c:param name="path" value="formData.p4_comments" />
					<c:param name="title">
						<fmt:message key="qualitat.appcc.plaVerificacio.camp.comentaris" />
					</c:param>
					<c:param name="camp" value="campo_p4_comments" />
					<c:param name="name" value="p4_comments" />
					<c:param name="required" value="required" />
					<c:param name="maxlength" value="500" />
					<c:param name="disabled" value="${disabled}" />
				</c:import>
			</div> 	
		</div>

		
		<div class="separadorH"></div>
		<br /><br />
        	<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="radioDecisio"/>
	    		<c:param name="path" value="formData.p5"/>
	    		<c:param name="title"><fmt:message key="qualitat.appcc.plaVerificacio.camp.p5"/></c:param>
	        	<c:param name="camp" value="p5"/>
	        	<c:param name="value" value="${formData.p5}"/>
	        	<c:param name="onchange" value="responPregunta('p5');"/>
			<c:param name="disabled" value="${disabled}" />
	        </c:import>
		<div id="punto5" style="padding-left: 15px;">
			<div class="separadorH"></div>
		        <br />
			<div id="observacionesForm" class="campoForm">	
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="textareaMedio" />
					<c:param name="path" value="formData.p5_comments" />
					<c:param name="title">
						<fmt:message key="qualitat.appcc.plaVerificacio.camp.comentaris" />
					</c:param>
					<c:param name="camp" value="campo_p5_comments" />
					<c:param name="name" value="p5_comments" />
					<c:param name="required" value="required" />
					<c:param name="maxlength" value="500" />
					<c:param name="disabled" value="${disabled}" />
				</c:import>
			</div> 	
		</div>
		
		<div class="separadorH"></div>
		<br /><br />
		<div id="observacionesForm" class="campoForm">	
			<c:import url="comu/CampFormulari.jsp">
				<c:param name="tipus" value="textareaMedio" />
				<c:param name="path" value="formData.p6" />
				<c:param name="title">
					<fmt:message key="qualitat.appcc.plaVerificacio.camp.p6" />
				</c:param>
				<c:param name="camp" value="campo_p6" />
				<c:param name="name" value="p6" />
				<c:param name="required" value="required" />
				<c:param name="maxlength" value="500" />
				<c:param name="disabled" value="${disabled}" />
			</c:import>
		</div> 	
	
	
	</div>
	
	<div class="separadorH"></div>
	<br></br>

	<div class="botonesForm">
		<c:if test="${not empty esEstAdministrador || not empty esEstEncarregat}">
			<c:choose>
				<c:when test="${not empty formData.id}">
					<div id="guardarForm" class="btnCorto"
						onclick="if(confirm('<fmt:message key="manteniment.modificar.confirm"/>')){submitForm('formulario')}"
						onmouseover="underline('enlaceGuardarForm')"
						onmouseout="underline('enlaceGuardarForm')"><a
						id="enlaceGuardarForm" href="javascript:void(0);"><fmt:message
						key="manteniment.guardar" /></a></div>
				</c:when>
				<c:otherwise>
					<div id="guardarForm" class="btnCorto"
						onclick="submitForm('formulario')"
						onmouseover="underline('enlaceGuardarForm')"
						onmouseout="underline('enlaceGuardarForm')"><a
						id="enlaceGuardarForm" href="javascript:void(0);"><fmt:message
						key="manteniment.guardar" /></a></div>
				</c:otherwise>
			</c:choose>
		</c:if>
		
		<div class="btnCorto" 
			onmouseover="underline('enlaceTornarForm')"
			onmouseout="underline('enlaceTornarForm')" 
			onclick="document.location ='QualitatAPPCCPlaVerificacio.html';"><a
			id="enlaceTornarForm" href="javascript:void(0);"><fmt:message
			key="proces.tornar" /></a></div>

		<c:if test="${not empty esEstAdministrador || not empty esEstEncarregat}">
			<c:if test="${empty formData.id}">
				<div id="cancelarForm" class="btnCorto"
					onmouseover="underline('enlaceCancelarForm')"
					onmouseout="underline('enlaceCancelarForm')"
					onclick="document.location ='QualitatAPPCCPlaVerificacio.html';"><a
					id="enlaceCancelarForm" href="javascript:void(0);"><fmt:message
					key="proces.cancelar" /></a></div>
			</c:if>
			<c:if test="${not empty formData.id}">
				<input id="action" name="action" value="delete" type="hidden" />
				<div id="eliminarForm" class="btnCorto"
					onmouseover="underline('enlaceBorrarForm')"
					onmouseout="underline('enlaceBorrarForm')"
					onclick="submitFormConfirm('deleteForm','<fmt:message key="manteniment.esborrar.confirm"/>');">
				<a id="enlaceBorrarForm" href="javascript:void(0);"><fmt:message
					key="manteniment.esborrar" /></a></div>
			</c:if>
		</c:if>
	
	</div>

	</form>
	<form id="deleteForm" action="QualitatAPPCCPlaVerificacio.html" method="post"
		class="seguit"
		onsubmit="return confirm('<fmt:message key="manteniment.estasegur"/>')">
		<input id="id" name="id" value="<c:out value="${formData.id}"/>" type="hidden" /> 
	</form>

	
</body>
</html>