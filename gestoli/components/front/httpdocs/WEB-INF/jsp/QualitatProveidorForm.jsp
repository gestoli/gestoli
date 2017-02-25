<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>
<%@ page import="es.caib.gestoli.front.util.*"%>
<%@ page import="java.util.ResourceBundle"%>
<%@ page buffer = "16kb" %>
<%@ page import="java.util.Locale"%>
<%

	String lang = Idioma.getLang(request);
	request.setAttribute("lang",lang);
%>

<html>
<head>
<title><c:choose>
	<c:when test="${not empty formData.codi}">
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
<link href="js/calendar/calendar-viti.css" rel="stylesheet"
	type="text/css" />

<script type="text/javascript" src="js/form.js"></script>


</head>
<body>

<form id="formulario" name="QualitatProveidorForm" action="QualitatProveidorForm.html" method="post" class="extended seguit" onsubmit="">
	
	<c:set var="disabled" value="" />
	<c:if test="${empty esEstAdministrador && empty esEstEncarregat}">
		<c:set var="disabled" value="true" />
	</c:if>
	
	<c:if test="${not empty formData.codi}">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="hidden" />
			<c:param name="path" value="formData.codi" />
			<c:param name="camp" value="codi" />
		</c:import>
	</c:if> 
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="hidden" />
		<c:param name="path" value="formData.idEstabliment" />
		<c:param name="camp" value="idEstabliment" />
		<c:param name="value" value="${idEstabliment}" />
	</c:import> 
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.nom" />
		<c:param name="title">
			<fmt:message key="qualitat.proveidor.camp.nom" />
		</c:param>
		<c:param name="camp" value="campo_nom" />
		<c:param name="name" value="nom" />
		<c:param name="required" value="required" />
		<c:param name="maxlength" value="128" />
		<c:param name="clase" value="campoFormGrande conMargen" />
		<c:param name="disabled" value="${disabled}" />
	</c:import>
	
	<div class="separadorH"></div>
	
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.direccio" />
		<c:param name="title">
			<fmt:message key="qualitat.proveidor.camp.direccio" />
		</c:param>
		<c:param name="camp" value="campo_direccio" />
		<c:param name="name" value="direccio" />
		<c:param name="required" value="required" />
		<c:param name="maxlength" value="255" />
		<c:param name="clase" value="campoFormGrande" />
		<c:param name="disabled" value="${disabled}" />
	</c:import>

	<div class="separadorH"></div>
	
	
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.telefon" />
		<c:param name="title">
			<fmt:message key="qualitat.proveidor.camp.telefon" />
		</c:param>
		<c:param name="camp" value="campo_telefon" />
		<c:param name="name" value="telefon" />
		<c:param name="required" value="required" />
		<c:param name="maxlength" value="128" />
		<c:param name="clase" value="campoFormMediano" />
		<c:param name="disabled" value="${disabled}" />
	</c:import>
	
		<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.personaContacte" />
		<c:param name="title">
			<fmt:message key="qualitat.proveidor.camp.personaContacte" />
		</c:param>
		<c:param name="camp" value="campo_personaContacte" />
		<c:param name="name" value="personaContacte" />
		<c:param name="required" value="required" />
		<c:param name="maxlength" value="128" />
		<c:param name="clase" value="campoFormMediano" />
		<c:param name="disabled" value="${disabled}" />
	</c:import>

	
	<div class="separadorH"></div>	

<c:if test="${not empty formData.codi}">
	<label><fmt:message key="qualitat.proveidorRGSA.titol" /></label> 
	<div class="separadorH"></div>
	<div id="listado"><%-- Tabla de resultados --%>
	
		<div class="layoutSombraTabla ancho">
		<c:if test="${not empty formData.RGSAs}">
			<div class="rellenoInf"></div>
			<div class="rellenoIzq"></div>
			<div class="rellenoDer"></div>
			<div class="supDer"></div>
			<div class="supIzq"></div>
			<div class="infIzq"></div>
			<div class="infDer"></div>
		</c:if>
		<display:table name="formData.RGSAs" requestURI="" export="true" id="rgsa" sort="list" cellpadding="0" cellspacing="0" class="listadoEstrecho selectable">
			<display:column property="nom" titleKey="qualitat.proveidorRGSA.camp.nom" url="/QualitatProveidorRGSAForm.html?idProveidor=${formData.codi}" paramId="id" paramProperty="id" sortable="true" headerClass="ancho120"/>
			<display:column property="caducitatRGSA" format="{0,date,dd/MM/yyyy}"  titleKey="qualitat.proveidorRGSA.camp.caducitatRGSA"  sortable="true" headerClass="ancho75 final"/>
			<display:setProperty name="export.xml" value="false" />
			<display:setProperty name="export.csv" value="false" />
			<display:setProperty name="export.rtf" value="false" />
			<display:setProperty name="export.pdf" value="false" />
			<display:setProperty name="export.excel.include_header" value="true" />
			<display:setProperty name="export.excel.filename" value="qualitatProveidorRGSAs.xls" />
			<display:setProperty name="export.decorated" value="true" />
		</display:table>
		</div> 
	
		<div class="separadorH"></div>
		<br /><br />
		
		<c:if test="${not empty esEstAdministrador || not empty esEstEncarregat}">
			<div class="btnCorto" onmouseover="underline('enlaceCrearForm')"
				onmouseout="underline('enlaceCrearForm')"
				onclick="document.rgsaForm.submit();">
				<a id="enlaceCrearForm"	href="javascript:void(0);"><fmt:message key="manteniment.crearnou" /></a>
			</div>
		</c:if>
		
	</div>
</c:if>

	<div class="separadorH"></div>
	
	<div id="observacionesForm" class="campoForm">	
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="textarea" />
			<c:param name="path" value="formData.observacions" />
			<c:param name="title">
				<fmt:message key="qualitat.subministre.camp.observacions" />
			</c:param>
			<c:param name="camp" value="campo_observacions" />
			<c:param name="name" value="observacions" />
			<c:param name="required" value="required" />
			<c:param name="maxlength" value="128" />
			<c:param name="disabled" value="${disabled}" />
		</c:import>
	</div>
	
	
	<div class="separadorH"></div>	
	

<c:if test="${not empty formData.codi}">
	<label><fmt:message key="qualitat.subministre.titol" /></label> 
	<div class="separadorH"></div>
	<div id="listado"><%-- Tabla de resultados --%>
	
	<div class="layoutSombraTabla ancho">
	<c:if test="${not empty subministres}">
		<div class="rellenoInf"></div>
		<div class="rellenoIzq"></div>
		<div class="rellenoDer"></div>
		<div class="supDer"></div>
		<div class="supIzq"></div>
		<div class="infIzq"></div>
		<div class="infDer"></div>
	</c:if>
	<display:table name="subministres" requestURI="" export="true" id="subministre" sort="list" cellpadding="0" cellspacing="0" class="listadoEstrecho selectable">
		<display:column property="tipusSubministre" titleKey="qualitat.subministre.camp.subministre" url="/QualitatSubministreForm.html?idProveidor=${formData.codi}" paramId="codi" paramProperty="codi" sortable="true" headerClass="ancho120"/>
		<display:column property="homologacio" format="{0,date,dd/MM/yyyy}"  titleKey="qualitat.subministre.camp.homologacio"  sortable="true" headerClass="ancho75"/>
		<display:column property="deshomologacio" format="{0,date,dd/MM/yyyy}" titleKey="qualitat.subministre.camp.deshomologacio" sortable="true" headerClass="ancho75"/>
		<display:column property="observacions" titleKey="qualitat.subministre.camp.observacions" sortable="true" headerClass="ancho75 final"/>
		<display:setProperty name="export.xml" value="false" />
		<display:setProperty name="export.csv" value="false" />
		<display:setProperty name="export.rtf" value="false" />
		<display:setProperty name="export.pdf" value="false" />
		<display:setProperty name="export.excel.include_header" value="true" />
		<display:setProperty name="export.excel.filename" value="qualitatProveidorSubministres.xls" />
		<display:setProperty name="export.decorated" value="true" />
	</display:table>
	</div> 

	<div class="separadorH"></div>
	<br /><br />
	
	<c:if test="${not empty esEstAdministrador || not empty esEstEncarregat}">
		<div class="btnCorto" onmouseover="underline('enlaceCrearForm')"
			onmouseout="underline('enlaceCrearForm')"
			onclick="document.submForm.submit();">
			<a id="enlaceCrearForm"	href="javascript:void(0);"><fmt:message key="manteniment.crearnou" /></a>
		</div>
	</c:if>
	
	</div>
</c:if>

	<div class="separadorH"></div>
	<br></br>
	
	<div class="botonesForm">
		<c:if test="${not empty esEstAdministrador || not empty esEstEncarregat}">
			<c:choose>
				<c:when test="${not empty formData.codi}">
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
			onclick="document.location ='QualitatProveidor.html';"><a
			id="enlaceTornarForm" href="javascript:void(0);"><fmt:message
			key="proces.tornar" /></a></div>

		<c:if test="${not empty esEstAdministrador || not empty esEstEncarregat}">
			<c:if test="${empty formData.codi}">
				<div id="cancelarForm" class="btnCorto"
					onmouseover="underline('enlaceCancelarForm')"
					onmouseout="underline('enlaceCancelarForm')"
					onclick="document.location ='QualitatProveidor.html';"><a
					id="enlaceCancelarForm" href="javascript:void(0);"><fmt:message
					key="proces.cancelar" /></a></div>
			</c:if>
			<c:if test="${not empty formData.codi}">
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
	<form id="deleteForm" action="QualitatProveidor.html" method="post"
		class="seguit"
		onsubmit="return confirm('<fmt:message key="manteniment.estasegur"/>')">
		<input id="codi" name="codi" value="<c:out value="${formData.codi}"/>" type="hidden" /> 
		<input id="action" name="action" value="delete" type="hidden" />
	</form>
	<form name="submForm" action="QualitatSubministreForm.html">
		<input id="idProveidor" name="idProveidor" value="<c:out value="${formData.codi}"/>" type="hidden" />
	</form>
	<form name="rgsaForm" action="QualitatProveidorRGSAForm.html">
		<input id="idProveidor" name="idProveidor" value="<c:out value="${formData.codi}"/>" type="hidden" />
	</form>
	
	<!-- Colores en tablas -->
	<c:if test="${not empty subministres || not empty formData.RGSAs}">
		<%-- Colores en tablas --%>
		<script type="text/javascript">
			$(document).ready(function(){
				setEstilosTabla();
			})
		</script>
	</c:if>
	
</body>
</html>