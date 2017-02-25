<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ page buffer = "16kb" %>
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

<form id="formulario" name="QualitatDescripcioSubministreAiguaForm" action="QualitatDescripcioSubministreAiguaForm.html" method="post" class="extended seguit" onsubmit="">
	
	<c:set var="disabled" value="" />
	<c:if test="${empty esEstAdministrador}">
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
	
	<div><label><fmt:message key="qualitat.plaAigua.camp.us" /></label> </div>
	
	<div class="separadorH"></div><br/>
	
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="checkbox" />
			<c:param name="path" value="formData.netejaInstalacio" />
			<c:param name="title">
				<fmt:message key="qualitat.plaAigua.camp.us.netejaInstalacio" />
			</c:param>
			<c:param name="camp" value="netejaInstalacio" />
			<c:param name="name" value="netejaInstalacio" />
			<c:param name="required" value="required" />
			<c:param name="disabled" value="${disabled}" />
		</c:import>
	
	<div class="separadorH"></div>
	
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="checkbox" />
			<c:param name="path" value="formData.higienePersonal" />
			<c:param name="title">
				<fmt:message key="qualitat.plaAigua.camp.us.higienePersonal" />
			</c:param>
			<c:param name="camp" value="higienePersonal" />
			<c:param name="name" value="higienePersonal" />
			<c:param name="required" value="required" />
			<c:param name="disabled" value="${disabled}" />
		</c:import>
	
	<div class="separadorH"></div>
	
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="checkbox" />
			<c:param name="path" value="formData.netejaRoba" />
			<c:param name="title">
				<fmt:message key="qualitat.plaAigua.camp.us.netejaRoba" />
			</c:param>
			<c:param name="camp" value="netejaRoba" />
			<c:param name="name" value="netejaRoba" />
			<c:param name="required" value="required" />
			<c:param name="disabled" value="${disabled}" />
		</c:import>
	
	<div class="separadorH"></div>
	
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="checkbox" />
			<c:param name="path" value="formData.elaboracioProductes" />
			<c:param name="title">
				<fmt:message key="qualitat.plaAigua.camp.us.elaboracioProductes" />
			</c:param>
			<c:param name="camp" value="elaboracioProductes" />
			<c:param name="name" value="elaboracioProductes" />
			<c:param name="required" value="required" />
			<c:param name="disabled" value="${disabled}" />
		</c:import>
		
	<div class="separadorH"></div><br/>
	
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.altresUsos" />
		<c:param name="title">
			<fmt:message key="qualitat.plaAigua.camp.us.altresUsos" />
		</c:param>
		<c:param name="camp" value="campo_altresUsos" />
		<c:param name="name" value="altresUsos" />
		<c:param name="required" value="required" />
		<c:param name="maxlength" value="128" />
		<c:param name="clase" value="campoFormCompleto conMargen" />
		<c:param name="disabled" value="${disabled}" />
	</c:import>
	
	<div class="separadorH"></div><br/>
	
	<div><label><fmt:message key="qualitat.plaAigua.camp.font" /></label> </div>
	
	<div class="separadorH"></div><br/>
	
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="checkbox" />
			<c:param name="path" value="formData.xarxaPublica" />
			<c:param name="title">
				<fmt:message key="qualitat.plaAigua.camp.font.xarxaPublica" />
			</c:param>
			<c:param name="camp" value="xarxaPublica" />
			<c:param name="name" value="xarxaPublica" />
			<c:param name="required" value="required" />
			<c:param name="disabled" value="${disabled}" />
		</c:import>
	
	<div class="separadorH"></div>
	
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="checkbox" />
			<c:param name="path" value="formData.abastamentPropi" />
			<c:param name="title">
				<fmt:message key="qualitat.plaAigua.camp.font.abastamentPropi" />
			</c:param>
			<c:param name="camp" value="abastamentPropi" />
			<c:param name="name" value="abastamentPropi" />
			<c:param name="required" value="required" />
			<c:param name="disabled" value="${disabled}" />
		</c:import>

	<div class="separadorH"></div><br/>
	
	<div><label><fmt:message key="qualitat.plaAigua.camp.tractament" /></label> </div>
	
	<div class="separadorH"></div><br/>
	
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="checkbox" />
			<c:param name="path" value="formData.cloracio" />
			<c:param name="title">
				<fmt:message key="qualitat.plaAigua.camp.tractament.cloracio" />
			</c:param>
			<c:param name="camp" value="cloracio" />
			<c:param name="name" value="cloracio" />
			<c:param name="required" value="required" />
			<c:param name="disabled" value="${disabled}" />
		</c:import>
	
	<div class="separadorH"></div>
	
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="checkbox" />
			<c:param name="path" value="formData.ozonitzacio" />
			<c:param name="title">
				<fmt:message key="qualitat.plaAigua.camp.tractament.ozonitzacio" />
			</c:param>
			<c:param name="camp" value="ozonitzacio" />
			<c:param name="name" value="ozonitzacio" />
			<c:param name="required" value="required" />
			<c:param name="disabled" value="${disabled}" />
		</c:import>
	
	<div class="separadorH"></div>
	
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="checkbox" />
			<c:param name="path" value="formData.filtracio" />
			<c:param name="title">
				<fmt:message key="qualitat.plaAigua.camp.tractament.filtracio" />
			</c:param>
			<c:param name="camp" value="filtracio" />
			<c:param name="name" value="filtracio" />
			<c:param name="required" value="required" />
			<c:param name="disabled" value="${disabled}" />
		</c:import>

	<div class="separadorH"></div>
	
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="checkbox" />
			<c:param name="path" value="formData.descalcificacio" />
			<c:param name="title">
				<fmt:message key="qualitat.plaAigua.camp.tractament.descalcificacio" />
			</c:param>
			<c:param name="camp" value="descalcificacio" />
			<c:param name="name" value="descalcificacio" />
			<c:param name="required" value="required" />
			<c:param name="disabled" value="${disabled}" />
		</c:import>
	
	<div class="separadorH"></div><br/>
	
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.altresTractaments" />
		<c:param name="title">
			<fmt:message key="qualitat.plaAigua.camp.tractament.altresTractaments" />
		</c:param>
		<c:param name="camp" value="campo_altresTractaments" />
		<c:param name="name" value="altresTractaments" />
		<c:param name="required" value="required" />
		<c:param name="maxlength" value="128" />
		<c:param name="clase" value="campoFormCompleto conMargen" />
		<c:param name="disabled" value="${disabled}" />
	</c:import>
	
	<div class="separadorH"></div><br/>
	
	<c:if test="${not empty formData.id}">
		<label><fmt:message key="qualitat.plaAigua.sortides.llistitol" /></label> 
		<div class="separadorH"></div>
		<div id="listado"><%-- Tabla de resultados --%>
			<div class="layoutSombraTabla ancho">
				<c:if
					test="${not empty sortides}">
					<div class="rellenoInf"></div>
					<div class="rellenoIzq"></div>
					<div class="rellenoDer"></div>
					<div class="supDer"></div>
					<div class="supIzq"></div>
					<div class="infIzq"></div>
					<div class="infDer"></div>
				</c:if> 
				<display:table name="sortides" id="aiguaPuntSortida" requestURI="" export="true" sort="list" cellpadding="0" cellspacing="0" class="listadoEstrecho" defaultsort="1" defaultorder="descending">
					<display:column property="id" titleKey="qualitat.plaAigua.sortides.camp.codi" headerClass="ancho75" sortable="true" url="/QualitatPuntSortidaAiguaForm.html" paramId="id" paramProperty="id"/>
					<display:column property="ubicacio" titleKey="qualitat.plaAigua.sortides.camp.ubicacio" headerClass="ancho210 final" sortable="true" url="/QualitatPuntSortidaAiguaForm.html" paramId="id" paramProperty="id"/>
					<display:setProperty name="export.xml" value="false" />
					<display:setProperty name="export.csv" value="false" />
					<display:setProperty name="export.rtf" value="false" />
					<display:setProperty name="export.pdf" value="false" />
					<display:setProperty name="export.excel.include_header" value="true" />
					<display:setProperty name="export.excel.filename" value="qualitatDescripcioSubministreAigua.xls" />
					<display:setProperty name="export.decorated" value="true" />
				</display:table>
			</div>
			<br /><br />
			
			<c:if test="${not empty esEstAdministrador}">
				<div class="btnCorto" onmouseover="underline('enlaceCrearForm')"
					onmouseout="underline('enlaceCrearForm')"
					onclick="document.sortidaForm.submit();">
					<a id="enlaceCrearForm"	href="javascript:void(0);"><fmt:message key="manteniment.crearnou" /></a>
				</div>
			</c:if>
		</div>
		<br/>
	</c:if>
	<br/>
	
	
	<div class="separadorH"></div>
	<br></br>
	
	

	<div class="botonesForm">
		<c:if test="${not empty esEstAdministrador}">
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

		<c:if test="${not empty esEstAdministrador}">
			<div id="cancelarForm" class="btnCorto"
				onmouseover="underline('enlaceCancelarForm')"
				onmouseout="underline('enlaceCancelarForm')"
				onclick="document.location ='Inici.html';"><a
				id="enlaceCancelarForm" href="javascript:void(0);"><fmt:message
				key="proces.cancelar" /></a></div>
		</c:if>
	
	</div>

	</form>
	
	<form name="sortidaForm" action="QualitatPuntSortidaAiguaForm.html">
		<input id="idEstabliment" name="idEstabliment" value="<c:out value="${formData.establiment.id}"/>" type="hidden" />
	</form>
	
	
	<c:if test="${not empty sortides}">
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