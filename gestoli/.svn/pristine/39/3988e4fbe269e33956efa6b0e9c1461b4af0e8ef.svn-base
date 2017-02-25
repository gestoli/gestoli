<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ page import="es.caib.gestoli.front.util.*"%>
<%@ page import="java.util.ResourceBundle"%>
<%@ page import="java.util.Locale"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>
<%

	String lang = Idioma.getLang(request);
	request.setAttribute("lang",lang);
%>

<html>
<head>


<title>
	<c:choose>
		<c:when test="${not empty formData.id}">
			<fmt:message key="manteniment.modificacio" />
		</c:when>
		<c:otherwise>
			<fmt:message key="manteniment.alta" />
		</c:otherwise>
	</c:choose> 
	<fmt:message key="analiticaParametreTipus.tipusdemant" />
</title>


	<script type="text/javascript" src="js/calendar/calendar.js"></script>
	<script type="text/javascript" src="js/calendar/calendar-setup.js"></script>
	<script type="text/javascript" src="js/displaytag.js"></script>

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


</head>
<body>

<form id="formulario" name="QualitatAiguaControlAnaliticForm" action="QualitatAiguaControlAnaliticForm.html" method="post" class="extended seguit" onsubmit="">
	
	<c:set var="disabled" value="" />
	<c:if test="${empty esEstAdministrador && empty esEstEncarregat && empty esEstTreballador}">
		<c:set var="disabled" value="true" />
	</c:if>
		
	<div id="responsableVerificacioX">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="select" />
			<c:param name="path" value="formData.responsableId" />
			<c:param name="title">
				<fmt:message key="qualitat.plaAigua.camp.responsable" />
			</c:param>
			<c:param name="camp" value="campo_idRresponsableVerificacio" />
			<c:param name="name" value="responsableId" />
			<c:param name="required" value="required" />
			<c:param name="selectItems" value="responsable" />
			<c:param name="selectItemsId" value="id" />
			<c:param name="selectItemsValue" value="nom" />
			<c:param name="selectSelectedValue" value="${formData.responsableId}" />
			<c:param name="clase" value="campoFormGrande conMargen" />
			<c:param name="disabled" value="${disabled}" />
		</c:import>
	</div>
	
	<div class="separadorH"></div><br/>
	
	<div id="frecuenciaX">
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="select" />
		<c:param name="path" value="formData.frequencia" />
		<c:param name="title">
			<fmt:message key="qualitat.plaNeteja.camp.frequencia" />
		</c:param>
		<c:param name="camp" value="campo_frequencia" />
		<c:param name="name" value="frequencia" />
		<c:param name="required" value="required" />
		<c:param name="selectItems" value="frequencia" />
		<c:param name="selectItemsId" value="id" />
		<c:param name="selectItemsValue" value="nom" />
		<c:param name="selectSelectedValue" value="${formData.frequencia}" />
		<c:param name="clase" value="campoFormGenerico80" />
		<c:param name="disabled" value="${disabled}" />
	</c:import>
	</div>
	
	<div class="separadorH"></div><br/>
	
	
		<label><fmt:message key="qualitat.plaAigua.controlAnalitic.llistitol" /></label> 
		<div class="separadorH"></div>
		<div id="listado"><%-- Tabla de resultados --%>
			<div class="layoutSombraTabla ancho">
				<c:if
					test="${not empty resultats}">
					<div class="rellenoInf"></div>
					<div class="rellenoIzq"></div>
					<div class="rellenoDer"></div>
					<div class="supDer"></div>
					<div class="supIzq"></div>
					<div class="infIzq"></div>
					<div class="infDer"></div>
				</c:if> 
				<display:table name="resultats" id="aiguaControlAnaliticVerificacio" requestURI="" export="true" sort="list" cellpadding="0" cellspacing="0" class="listadoEstrecho" defaultsort="1" defaultorder="descending">
					<display:column property="dataVerificacio" format="{0,date,dd/MM/yyyy}" titleKey="qualitat.plaAigua.controlAnalitic.camp.dataMostreig" sortable="true" url="/QualitatAiguaControlAnaliticVerificacioForm.html" paramId="id" paramProperty="id"/>
					<display:column property="dataAnalisi" format="{0,date,dd/MM/yyyy}" titleKey="qualitat.plaAigua.controlAnalitic.camp.dataAnalisi" sortable="true" url="/QualitatAiguaControlAnaliticVerificacioForm.html" paramId="id" paramProperty="id"/>
					<display:column property="puntMostreig.ubicacio" headerClass="ancho160" titleKey="qualitat.plaAigua.control.camp.puntMostreig" sortable="true" url="/QualitatAiguaControlAnaliticVerificacioForm.html" paramId="id" paramProperty="id"/>
					<display:column property="satisfactoriString" titleKey="qualitat.appcc.control.verificacio.camp.satisfactori" headerClass="ancho75 final" sortable="true" url="/QualitatAiguaControlAnaliticVerificacioForm.html" paramId="id" paramProperty="id"/>
					<display:setProperty name="export.xml" value="false" />
					<display:setProperty name="export.csv" value="false" />
					<display:setProperty name="export.rtf" value="false" />
					<display:setProperty name="export.pdf" value="false" />
					<display:setProperty name="export.excel.include_header" value="true" />
					<display:setProperty name="export.excel.filename" value="qualitatAiguaControlAnalitic.xls" />
					<display:setProperty name="export.decorated" value="true" />
				</display:table>
			</div>
			
			<c:if test="${not empty esEstAdministrador || not empty esEstEncarregat}">
				<c:if test="${not empty formData.id}">
					<br /><br />
					<div class="btnCorto" onmouseover="underline('enlaceCrearForm')"
						onmouseout="underline('enlaceCrearForm')"
						onclick="document.verificacioForm.submit();">
						<a id="enlaceCrearForm"	href="javascript:void(0);"><fmt:message key="manteniment.crearnou" /></a>
					</div>
				</c:if>
			</c:if>
		</div>
		<br/>

	<br/>
	
	
	<div class="separadorH"></div>
	<br></br>
	
	

	<div class="botonesForm">
		<c:if test="${not empty esEstAdministrador || not empty esEstEncarregat || not empty esEstTreballador}">
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
			onclick="document.location ='Inici.html';"><a
			id="enlaceTornarForm" href="javascript:void(0);"><fmt:message
			key="proces.tornar" /></a></div>

		<c:if test="${not empty esEstAdministrador || not empty esEstEncarregat || not empty esEstTreballador}">
			<div id="cancelarForm" class="btnCorto"
				onmouseover="underline('enlaceCancelarForm')"
				onmouseout="underline('enlaceCancelarForm')"
				onclick="document.location ='Inici.html';"><a
				id="enlaceCancelarForm" href="javascript:void(0);"><fmt:message
				key="proces.cancelar" /></a></div>
		</c:if>
	
	</div>

	</form>
	
	<form name="verificacioForm" action="QualitatAiguaControlAnaliticVerificacioForm.html">
		<input id="idControl" name="idControl" value="<c:out value="${formData.id}"/>" type="hidden" />
	</form>
	
	
	<c:if test="${not empty resultats}">
		<%-- Colores en tablas --%>
		<script type="text/javascript">
			$(document).ready(function(){
				setEstilosTabla();
			})
		</script>
	</c:if>

	<script type="text/javascript" language="javascript">
	// <![CDATA[
		
		function submitFormulario(id) {
			submitForm('formulario'); 
			redirect(id);
		}
				
	// ]]>
	</script>
	
</body>
</html>