<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ page import="es.caib.gestoli.front.util.*"%>
<%@ page import="java.util.ResourceBundle"%>
<%@ page import="java.util.Locale"%>
<%@ page buffer = "16kb" %>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>
<%

	String lang = Idioma.getLang(request);
	request.setAttribute("lang",lang);
%>

<html>
<head>

	<script type="text/javascript">
	
		window.onload = function() {
		}
	
	</script>

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


	<script type="text/javascript">

		function crearAccio() {
			//document.getElementById("idNoConformitat").value = "";
			document.getElementById("idAccio").value = "";
			document.getElementById("accioForm").submit();
		}

		function visualitzarAccio(id) {
			//document.getElementById("idNoConformitat").value = id;
			document.getElementById("idAccio").value = id;
			document.getElementById("accioForm").submit();
		}
	</script>
	

</head>
<body>

<form id="formulario" name="NoConformitatForm" action="QualitatNoConformitatForm.html" method="post" class="extended seguit" onsubmit="">
	<c:set var="disabled" value="" />
	<c:if test="${empty esEstAdministrador && empty esEstEncarregat && empty esEstTreballador}">
		<c:set var="disabled" value="true" />
	</c:if>
	
	<input id="paramURL" name="paramURL" value="${url}" type="hidden" />
	<input id="paramParams" name="paramParams" value="${params}" type="hidden" />
	
	<c:if test="${not empty formData.id}">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="hidden" />
			<c:param name="path" value="formData.id" />
			<c:param name="camp" value="id" />
		</c:import>
	</c:if> 
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="hidden" />
		<c:param name="path" value="formData.idEstabliment" />
		<c:param name="camp" value="idEstabliment" />
		<c:param name="value" value="${idEstabliment}" />
	</c:import> 
	<c:if test="${not empty formData.idControl}">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="hidden" />
			<c:param name="path" value="formData.idControl" />
			<c:param name="camp" value="idControl" />
			<c:param name="value" value="${formData.idControl}" />
		</c:import> 
	</c:if>
	
	<div id="faseI">
		<h3><fmt:message key="qualitat.noConformitat.titol.faseI" /></h3>
	
		<div class="separadorH"></div>
		
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="calendar" />
			<c:param name="path" value="formData.dataNoConformitat" />
			<c:param name="title">
				<fmt:message key="qualitat.noConformitat.camp.dataNoConformitat" />
			</c:param>
			<c:param name="camp" value="campo_dataNoConformitat" />
			<c:param name="name" value="dataNoConformitat" />
			<c:param name="required" value="required" />
			<c:param name="maxlength" value="10" />
			<c:param name="aclaracio">
				<fmt:message key="proces.aclaracio.formatdata" />
			</c:param>
			<c:param name="clase" value="campoForm165 conMargen" />
			<c:param name="disabled" value="${disabled}" />
		</c:import>
		
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="select" />
			<c:param name="path" value="formData.idResponsableDeteccio" />
			<c:param name="title">
				<fmt:message key="qualitat.noConformitat.camp.responsableDeteccio" />
			</c:param>
			<c:param name="camp" value="campo_idResponsableDeteccio" />
			<c:param name="name" value="idResponsableDeteccio" />
			<c:param name="required" value="required" />
			<c:param name="selectItems" value="personal" />
			<c:param name="selectItemsId" value="id" />
			<c:param name="selectItemsValue" value="nom" />
			<c:param name="selectSelectedValue" value="${formData.idResponsableDeteccio}" />
			<c:param name="clase" value="campoFormGrande" />
			<c:param name="disabled" value="${disabled}" />
		</c:import>
		
		<div class="separadorH"></div>
		
		<c:if test="${empty formData.idControl}">
			<c:set var="disabledDep" value=""/>
		</c:if>
		<c:if test="${not empty formData.idControl}">
			<c:set var="disabledDep" value="disabled"/>
		</c:if>
			<c:import url="comu/CampFormulari.jsp">
				<c:param name="tipus" value="select" />
				<c:param name="path" value="formData.idDepartament" />
				<c:param name="title">
					<fmt:message key="qualitat.noConformitat.camp.departament" />
				</c:param>
				<c:param name="camp" value="campo_idDepartament" />
				<c:param name="name" value="idDepartament" />
				<c:param name="required" value="required" />
				<c:param name="selectItems" value="departaments" />
				<c:param name="selectItemsId" value="id" />
				<c:param name="selectItemsValue" value="nom" />
				<c:param name="selectSelectedValue" value="${formData.idDepartament}" />
				<c:param name="clase" value="campoFormGrande conMargen" />
				<c:param name="disabled" value="${disabledDep}${disabled}"/>
			</c:import>
		
		<div class="separadorH"></div>
			
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="text" />
			<c:param name="path" value="formData.descripcio" />
			<c:param name="title">
				<fmt:message key="qualitat.noConformitat.camp.descripcio" />
			</c:param>
			<c:param name="camp" value="campo_descripcio" />
			<c:param name="name" value="descripcio" />
			<c:param name="required" value="required" />
			<c:param name="maxlength" value="255" />
			<c:param name="clase" value="campoFormCompleto" />
			<c:param name="disabled" value="${disabled}" />
		</c:import>
	
		<div class="separadorH"></div>
	
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="text" />
			<c:param name="path" value="formData.causa" />
			<c:param name="title">
				<fmt:message key="qualitat.noConformitat.camp.causa" />
			</c:param>
			<c:param name="camp" value="campo_causa" />
			<c:param name="name" value="causa" />
			<c:param name="required" value="required" />
			<c:param name="maxlength" value="255" />
			<c:param name="clase" value="campoFormCompleto" />
			<c:param name="disabled" value="${disabled}" />
		</c:import>
	
	</div>
	
	<div class="separadorH"></div>
	
	
	<c:if test="${not empty formData.id}">
		<div id="faseII">
			<h3><fmt:message key="qualitat.noConformitat.titol.faseII" /></h3>
	
			<div class="separadorH"></div>
		
			<c:if test="${not empty formData.accions}">
				<div id="listado"><%-- Tabla de resultados --%>
					<div class="layoutSombraTabla ancho">
						<c:if
							test="${not empty formData.accions}">
							<div class="rellenoInf"></div>
							<div class="rellenoIzq"></div>
							<div class="rellenoDer"></div>
							<div class="supDer"></div>
							<div class="supIzq"></div>
							<div class="infIzq"></div>
							<div class="infDer"></div>
						</c:if> 
						<display:table name="formData.accions" id="registre" requestURI="" export="true" sort="list" defaultsort="1" cellpadding="0" cellspacing="0" class="listadoEstrecho selectable">
							<display:column titleKey="qualitat.noConformitat.accio.camp.id" headerClass="ancho100" sortable="true">
								<div id="visualitzarAccio1_${registre.id}" onclick="visualitzarAccio(${registre.id})">
									<c:out value="${registre.codi}"/>
								</div>
							</display:column>
							<display:column titleKey="qualitat.noConformitat.accio.camp.accio" headerClass="ancho100" sortable="true">
								<div id="visualitzarAccio2_${registre.id}" onclick="visualitzarAccio(${registre.id})">
									<c:out value="${registre.accio}"/>
								</div>
							</display:column>
							<display:column titleKey="qualitat.noConformitat.accio.camp.responsableAccio" headerClass="ancho100" sortable="true">
								<div id="visualitzarAccio3_${registre.id}" onclick="visualitzarAccio(${registre.id})">
									<c:out value="${registre.responsableAccio.nom} ${registre.responsableAccio.llinatges}"/>
								</div>
							</display:column>
							<display:column titleKey="qualitat.noConformitat.accio.camp.dataFiPrevista" headerClass="ancho100" sortable="true">
								<div id="visualitzarAccio4_${registre.id}" onclick="visualitzarAccio(${registre.id})">
									<fmt:formatDate value="${registre.dataFiPrevista}" pattern="dd/MM/yyyy"/>
								</div>
							</display:column>
							<display:column titleKey="qualitat.noConformitat.accio.camp.dataTancament" headerClass="ancho100" sortable="true">
								<div id="visualitzarAccio4_${registre.id}" onclick="visualitzarAccio(${registre.id})">
									<fmt:formatDate value="${registre.dataTancament}" pattern="dd/MM/yyyy"/>
								</div>
							</display:column>
							<display:setProperty name="export.xml" value="false" />
							<display:setProperty name="export.csv" value="false" />
							<display:setProperty name="export.rtf" value="false" />
							<display:setProperty name="export.pdf" value="false" />
							<display:setProperty name="export.excel.include_header" value="true" />
							<display:setProperty name="export.excel.filename" value="qualitatNoConformitatAccions.xls" />
							<display:setProperty name="export.decorated" value="true" />
						</display:table>
					</div>
				</div>
				<br/><br/><br/>
			</c:if>

			<div class="separadorH"></div>		
				
			<c:if test="${not empty esEstAdministrador || not empty esEstEncarregat || not empty esEstTreballador}">
				<div class="btnCorto" onmouseover="underline('enlaceCrearAccionForm')"
					onmouseout="underline('enlaceCrearAccionForm')"
					onclick="crearAccio();">
					<a id="enlaceCrearAccionForm"	href="javascript:void(0);"><fmt:message key="qualitat.noConformitat.crearAccio" /></a>
				</div>
			</c:if>
					
			<div class="separadorH"></div>
			<br></br>

			<c:import url="comu/CampFormulari.jsp">
				<c:param name="tipus" value="calendar" />
				<c:param name="path" value="formData.dataTancament" />
				<c:param name="title">
					<fmt:message key="qualitat.noConformitat.camp.dataTancament" />
				</c:param>
				<c:param name="camp" value="campo_dataTancament" />
				<c:param name="name" value="dataTancament" />
				<c:param name="required" value="required" />
				<c:param name="maxlength" value="10" />
				<c:param name="aclaracio">
					<fmt:message key="proces.aclaracio.formatdata" />
				</c:param>
				<c:param name="clase" value="campoForm165" />
				<c:param name="disabled" value="${disabled}" />
			</c:import>
		
			<div class="separadorH"></div>
				
		</div>
	</c:if>

		
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
			onclick="tornar();"><a
			id="enlaceTornarForm" href="javascript:void(0);"><fmt:message
			key="proces.tornar" /></a></div>

		<c:if test="${not empty esEstAdministrador || not empty esEstEncarregat || not empty esEstTreballador}">
			<c:if test="${empty formData.id}">
				<div id="cancelarForm" class="btnCorto"
					onmouseover="underline('enlaceCancelarForm')"
					onmouseout="underline('enlaceCancelarForm')"
					onclick="tornar();"><a
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
	<form id="deleteForm" action="QualitatNoConformitat.html" method="post"
		class="seguit"
		onsubmit="return confirm('<fmt:message key="manteniment.estasegur"/>')">
		<input id="id" name="id" value="<c:out value="${formData.id}"/>" type="hidden" /> 
		<input id="action" name="action" value="delete" type="hidden" />
		<input id="url" name="url" value="${url}" type="hidden" /> 
		<input id="params" name="params" value="${params}" type="hidden" />
	</form>


	<form id="accioForm" action="QualitatNoConformitatAccioForm.html" method="get" class="mini">
		<input id="idNoConformitat" name="idNoConformitat" value="<c:out value="${formData.id}"/>" type="hidden" />
		<input id="idAccio" name="id" value="" type="hidden" />
		<input id="url" name="url" value="${url}" type="hidden" /> 
		<input id="params" name="params" value="${params}" type="hidden" />
	</form>
	
	<c:if test="${not empty formData.accions}">
		<%-- Colores en tablas --%>
		<script type="text/javascript">
			$(document).ready(function(){
				setEstilosTabla();
			})
		</script>
	</c:if>

	<script type="text/javascript" language="javascript">
	// <![CDATA[

		function tornar() {
			//alert(window.history.length);
			var url = document.getElementById("paramURL").value;
			var params = document.getElementById("paramParams").value;
			if (url != ""){
				if (url == "plaManteniment"){
					document.location ='QualitatPlaMantenimentControlVerificacioForm.html?'+params;
				} else if (url == "plaNeteja"){
					document.location ='QualitatPlaNetejaVerificacioForm.html?'+params;
				} else if (url == "plaFormacio"){
					document.location ='QualitatFormacioEvaluacioForm.html?'+params;
				} else if (url == "plaControlPlagues"){
					document.location ='QualitatControlPlaguesVerificacioForm.html?'+params;
				} else if (url == "proveidor"){
					document.location ='QualitatProveidorAvaluacioForm.html?'+params;
				} else if (url == "aiguaOrganoleptic"){
					document.location ='QualitatAiguaControlOrganolepticVerificacioForm.html?'+params;
				} else if (url == "aiguaAnalitic"){
					document.location ='QualitatAiguaControlAnaliticVerificacioForm.html?'+params;
				} else if (url == "plaControlTransport"){
					document.location ='QualitatPlaControlTransportForm.html?'+params;
				} else if (url == "appcc"){
					document.location ='QualitatAPPCCControlVerificacioForm.html?'+params;
				} else if (url == "noConformitat"){
					document.location ='QualitatNoConformitat.html';
				}
			}
/*			var ref = document.referrer.split("?");
			var url = document.URL.split("?");
			if (ref[0] != url[0]){
				window.history.go(-1);
			} else {
				window.history.go(-2);
			}*/
		}
				
	// ]]>
	</script>
	
</body>
</html>