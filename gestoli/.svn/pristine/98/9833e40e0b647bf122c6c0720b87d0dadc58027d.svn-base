<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
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
	
	window.onload = function() {
			init('p2', 'respuesta2');
			init('p3', 'respuesta3');
			init('p4', 'respuesta4');
			init('p5', 'respuesta5');
		}
		
		
	function init(pregunta, respuesta){
		var campSi = document.getElementById(pregunta+"Si");
		var campNo = document.getElementById(pregunta+"No");
		if (campSi.checked || campNo.checked){
			document.getElementById(respuesta).style.display = "block";
		} else {
			document.getElementById(respuesta).style.display = "none";
		}
	}
	
	function reset(pregunta){
		document.getElementById(pregunta).value = "";
		document.getElementById(pregunta+"Si").checked = false;
		document.getElementById(pregunta+"No").checked = false;
	}
	
	<%-- RADIO --%>
	function responPregunta(pregunta) {
		document.getElementById("guardarForm").style.display = "none";
		document.getElementById("solucio").style.display = "none";
		document.getElementById("campo_puntControl").value = "";
		document.getElementById("puntControlMsg").value = "";
		document.getElementById("campo_perillControlat").value = "";
		document.getElementById("perillControlatMsg").value = "";
		
		var campSi = document.getElementById(pregunta+"Si");
		var campNo = document.getElementById(pregunta+"No");
		
		if (campSi.checked == true) {
			document.getElementById(pregunta).value = "S";
		} else if (campNo.checked == true) {
			document.getElementById(pregunta).value = "N";
		}
		
		if (pregunta == "p1") {
			if (campSi.checked == true) {
				document.getElementById("respuesta2").style.display = "none";
				document.getElementById("respuesta3").style.display = "none";
				document.getElementById("respuesta4").style.display = "none";
				document.getElementById("respuesta5").style.display = "block";
				reset("p2");
				reset("p3");
				reset("p4");
				reset("p5");
			} else if (campNo.checked == true) {
				document.getElementById("respuesta2").style.display = "block";
				document.getElementById("respuesta3").style.display = "none";
				document.getElementById("respuesta4").style.display = "none";
				document.getElementById("respuesta5").style.display = "none";
				reset("p2");
				reset("p3");
				reset("p4");
				reset("p5");
			}
		} else if (pregunta == "p2"){
			if (campSi.checked == true) {
				document.getElementById("respuesta3").style.display = "none";
				document.getElementById("respuesta4").style.display = "block";
				document.getElementById("respuesta5").style.display = "none";
				reset("p3");
				reset("p4");
				reset("p5");
			} else if (campNo.checked == true){
				document.getElementById("respuesta3").style.display = "block";
				document.getElementById("respuesta4").style.display = "none";
				document.getElementById("respuesta5").style.display = "none";
				reset("p3");
				reset("p4");
				reset("p5");
			}
		} else if (pregunta == "p3"){
			if (campSi.checked == true) {
				document.getElementById("respuesta4").style.display = "block";
				document.getElementById("respuesta5").style.display = "none";
				reset("p4");
				reset("p5");
			} else if (campNo.checked == true){
				document.getElementById("respuesta4").style.display = "none";
				document.getElementById("respuesta5").style.display = "none";
				reset("p4");
				reset("p5");
				solucio();
			}
		} else if (pregunta == "p4"){
			if (campSi.checked == true) {
				document.getElementById("respuesta5").style.display = "block";
				reset("p5");
			} else if (campNo.checked == true){
				document.getElementById("respuesta5").style.display = "block";
				reset("p5");
			}
		} else if (pregunta == "p5"){
			solucio();
		}
	}
	
	function solucio(){
		if (document.getElementById("p3").value == "N"){
			document.getElementById("campo_puntControl").value = "no";
			document.getElementById("puntControlMsg").value = '<fmt:message key="qualitat.appcc.control.puntControl.no"/>';
			document.getElementById("campo_perillControlat").value = "si";
			document.getElementById("perillControlatMsg").value = '<fmt:message key="qualitat.appcc.control.perillControlat.si"/>';
		} else if (document.getElementById("p5").value == "S"){
			if ( (document.getElementById("p1").value == "S") || (document.getElementById("p4").value == "S") ){
				document.getElementById("campo_puntControl").value = "pc";
				document.getElementById("puntControlMsg").value = '<fmt:message key="qualitat.appcc.control.puntControl.pc"/>';
				document.getElementById("campo_perillControlat").value = "si";
				document.getElementById("perillControlatMsg").value = '<fmt:message key="qualitat.appcc.control.perillControlat.si"/>';
			} else if (document.getElementById("p4").value == "N"){
				document.getElementById("campo_puntControl").value = "no";
				document.getElementById("puntControlMsg").value = '<fmt:message key="qualitat.appcc.control.puntControl.no"/>';
				document.getElementById("campo_perillControlat").value = "pendent";
				document.getElementById("perillControlatMsg").value = '<fmt:message key="qualitat.appcc.control.perillControlat.pendent"/>';
			}
		} else if (document.getElementById("p5").value == "N"){
			if ( (document.getElementById("p1").value == "S") || (document.getElementById("p4").value == "S") ){
				document.getElementById("campo_puntControl").value = "pcc";
				document.getElementById("puntControlMsg").value = '<fmt:message key="qualitat.appcc.control.puntControl.pcc"/>';
				document.getElementById("campo_perillControlat").value = "si";
				document.getElementById("perillControlatMsg").value = '<fmt:message key="qualitat.appcc.control.perillControlat.si"/>';
			} else if (document.getElementById("p4").value == "N"){
				document.getElementById("campo_puntControl").value = "implantar";
				document.getElementById("puntControlMsg").value = '<fmt:message key="qualitat.appcc.control.puntControl.implantar"/>';
				document.getElementById("campo_perillControlat").value = "pendent";
				document.getElementById("perillControlatMsg").value = '<fmt:message key="qualitat.appcc.control.perillControlat.pendent"/>';
			}
		}
		
		document.getElementById("solucio").style.display = "block";
		document.getElementById("guardarForm").style.display = "block";
	}
	
	// ]]>
</script>

</head>
<body>

<form id="formulario" name="APPCCControlForm" action="QualitatAPPCCControlForm.html" method="post" class="extended seguit" onsubmit="">
	<c:if test="${not empty formData.id}">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="hidden" />
			<c:param name="path" value="formData.id" />
			<c:param name="camp" value="id" />
		</c:import>
	</c:if> 
	<c:if test="${not empty formData.idEtapa}">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="hidden" />
			<c:param name="path" value="formData.idEtapa" />
			<c:param name="camp" value="idEtapa" />
		<c:param name="value" value="${idEtapa}" />
		</c:import>
	</c:if> 
	<c:if test="${not empty formData.idPerill}">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="hidden" />
			<c:param name="path" value="formData.idPerill" />
			<c:param name="camp" value="idPerill" />
		<c:param name="value" value="${idPerill}" />
		</c:import>
	</c:if> 
	
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="hidden" />
		<c:param name="path" value="formData.puntControl" />
		<c:param name="title">
			<fmt:message key="qualitat.appcc.control.camp.puntControl" />
		</c:param>
		<c:param name="camp" value="campo_puntControl" />
		<c:param name="name" value="puntControl" />
		<c:param name="clase" value="campoFormGrande" />
		<c:param name="required" value="required" />
	</c:import>
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="hidden" />
		<c:param name="path" value="formData.perillControlat" />
		<c:param name="title">
			<fmt:message key="qualitat.appcc.control.camp.perillControlat" />
		</c:param>
		<c:param name="camp" value="campo_perillControlat" />
		<c:param name="name" value="perillControlat" />
		<c:param name="required" value="required" />
		<c:param name="clase" value="campoFormGrande" />
	</c:import>
	
	<div class="separadorH"></div>
	
	<div id="preguntas">
		<div id="respuesta1">
	        	<c:import url="comu/CampFormulari.jsp">
				<c:param name="tipus" value="radioDecisio"/>
		    		<c:param name="path" value="formData.p1"/>
		    		<c:param name="title"><fmt:message key="qualitat.appcc.control.pregunta1"/></c:param>
		        	<c:param name="camp" value="p1"/>
		        	<c:param name="value" value="${formData.p1}"/>
		        	<c:param name="onchange" value="responPregunta('p1');"/>
		        </c:import>
			<br />
		</div>
		<c:if test="${empty formData.p2}">
			<c:set var="resp2" value="display:none"/>
		</c:if>
		<div id="respuesta2" style="${resp2}">
	        	<c:import url="comu/CampFormulari.jsp">
				<c:param name="tipus" value="radioDecisio"/>
		    		<c:param name="path" value="formData.p2"/>
		    		<c:param name="title"><fmt:message key="qualitat.appcc.control.pregunta2"/></c:param>
		        	<c:param name="camp" value="p2"/>
		        	<c:param name="value" value="${formData.p2}"/>
		        	<c:param name="onchange" value="responPregunta('p2');"/>
		        </c:import>
			<br />
		</div>
		<c:if test="${empty formData.p3}">
			<c:set var="resp3" value="display:none"/>
		</c:if>
		<div id="respuesta3" style="${resp3}">
	        	<c:import url="comu/CampFormulari.jsp">
				<c:param name="tipus" value="radioDecisio"/>
		    		<c:param name="path" value="formData.p3"/>
		    		<c:param name="title"><fmt:message key="qualitat.appcc.control.pregunta3"/></c:param>
		        	<c:param name="camp" value="p3"/>
		        	<c:param name="value" value="${formData.p3}"/>
		        	<c:param name="onchange" value="responPregunta('p3');"/>
		        </c:import>
			<br />
		</div>
		<c:if test="${empty formData.p4}">
			<c:set var="resp4" value="display:none"/>
		</c:if>
		<div id="respuesta4" style="${resp4}">
	        	<c:import url="comu/CampFormulari.jsp">
				<c:param name="tipus" value="radioDecisio"/>
		    		<c:param name="path" value="formData.p4"/>
		    		<c:param name="title"><fmt:message key="qualitat.appcc.control.pregunta4"/></c:param>
		        	<c:param name="camp" value="p4"/>
		        	<c:param name="value" value="${formData.p4}"/>
		        	<c:param name="onchange" value="responPregunta('p4');"/>
		        </c:import>
			<br />
		</div>
		<c:if test="${empty formData.p5}">
			<c:set var="resp5" value="display:none"/>
		</c:if>
		<div id="respuesta5" style="${resp5}">
	        	<c:import url="comu/CampFormulari.jsp">
				<c:param name="tipus" value="radioDecisio"/>
		    		<c:param name="path" value="formData.p5"/>
		    		<c:param name="title"><fmt:message key="qualitat.appcc.control.pregunta5"/></c:param>
		        	<c:param name="camp" value="p5"/>
		        	<c:param name="value" value="${formData.p5}"/>
		        	<c:param name="onchange" value="responPregunta('p5');"/>
		        </c:import>
		        <br />
		</div>
	</div>
	
	<div class="separadorH"></div>
	
	
	<c:if test="${empty formData.puntControl}">
		<c:set var="solucio" value="display:none"/>
	</c:if>
	<div id="solucio" style="${solucio}">
		<div class=" campoFormCompleto">
			<label><fmt:message key="qualitat.appcc.control.camp.puntControl" /></label>
			<div class="borderInput bordeFoco">
				<input type="text" id="puntControlMsg" class="inputText" disabled="true" value="<fmt:message key="qualitat.appcc.control.puntControl.${formData.puntControl}"/>"/>
			</div>
		</div>
		<div class=" campoFormCompleto">
			<label><fmt:message key="qualitat.appcc.control.camp.perillControlat" /></label>
			<div class="borderInput bordeFoco">
				<input type="text" id="perillControlatMsg" class="inputText campoFormGrande" disabled="true" value="<fmt:message key="qualitat.appcc.control.perillControlat.${formData.perillControlat}"/>"/>
			</div>	
		</div>
		
	</div>

	<div class="separadorH"></div>
	<br></br>

	<div class="botonesForm">
	
		<c:choose>
			<c:when test="${not empty formData.id}">
				<div id="guardarForm" class="btnCorto"
					onclick="if(confirm('<fmt:message key="manteniment.modificar.confirm"/>')){submitForm('formulario')}"
					onmouseover="underline('enlaceGuardarForm')"
					onmouseout="underline('enlaceGuardarForm')" style="display:none"><a
					id="enlaceGuardarForm" href="javascript:void(0);"><fmt:message
					key="manteniment.guardar" /></a></div>
			</c:when>
			<c:otherwise>
				<div id="guardarForm" class="btnCorto"
					onclick="submitForm('formulario')"
					onmouseover="underline('enlaceGuardarForm')"
					onmouseout="underline('enlaceGuardarForm')" style="display:none"><a
					id="enlaceGuardarForm" href="javascript:void(0);"><fmt:message
					key="manteniment.guardar" /></a></div>
			</c:otherwise>
		</c:choose>
		
		<div class="btnCorto" 
			onmouseover="underline('enlaceTornarForm')"
			onmouseout="underline('enlaceTornarForm')" 
			onclick="document.location ='QualitatAPPCCControl.html?id=${formData.idPerill}';"><a
			id="enlaceTornarForm" href="javascript:void(0);"><fmt:message
			key="proces.tornar" /></a></div>

		<c:if test="${empty formData.id}">
			<div id="cancelarForm" class="btnCorto"
				onmouseover="underline('enlaceCancelarForm')"
				onmouseout="underline('enlaceCancelarForm')"
				onclick="document.location ='QualitatAPPCCControl.html?id=${formData.idPerill}';"><a
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
	
	</div>

	</form>
	<form id="deleteForm" action="QualitatAPPCCControl.html" method="post"
		class="seguit"
		onsubmit="return confirm('<fmt:message key="manteniment.estasegur"/>')">
		<input id="id" name="id" value="<c:out value="${formData.id}"/>" type="hidden" /> 
		<input id="idPerill" name="idPerill" value="<c:out value="${formData.idPerill}"/>" type="hidden" />
		<input id="action" name="action" value="delete" type="hidden" />
	</form>

	
</body>
</html>