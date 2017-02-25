<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>
<%@ page import="es.caib.gestoli.front.util.*"%>
<%@ page import="java.util.ResourceBundle"%>
<%@ page import="java.util.Locale"%>
<%@ page buffer="64kb"%>
<%@ page autoFlush="true"%>
<%
	String lang = Idioma.getLang(request);
	request.setAttribute("lang",lang);
%>

<html>
<head>
<title><fmt:message key="consulta.entradaOlivaTaula.titol" /></title>

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

<c:if test="${not empty llistat}">
	<script type="text/javascript" src="js/displaytag.js"></script>
	<%--<link href="css/displaytag.css" rel="stylesheet" type="text/css"/> --%>
</c:if>


</head>
<body>
<form id="formulario" action="ConsultaLlibreEntradaOlivaTaula.html" method="post" class="extended seguit">
	<fieldset>
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="hidden" />
			<c:param name="path" value="formData.fromEstabliment" />
			<c:param name="camp" value="fromEstabliment" />
		</c:import> 
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="hidden" />
			<c:param name="path" value="formData.idEstabliment" />
			<c:param name="camp" value="idEstabliment" />
		</c:import> 
		
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="select" />
			<c:param name="path" value="formData.idCampanya" />
			<c:param name="title">
				<fmt:message key="consulta.general.temporada" />
			</c:param>
			<c:param name="camp" value="idCampanya" />
			<c:param name="required" value="required" />
			<c:param name="selectItems" value="campanyes" />
			<c:param name="selectItemsId" value="id" />
			<c:param name="selectItemsValue" value="nom" />
			<c:param name="selectSelectedValue" value="${formData.idCampanya}" />
			<c:param name="clase" value="campoFormMediano" />
		</c:import>
		
		<div class="separadorH"></div>
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="calendar" />
			<c:param name="path" value="formData.dataInici" />
			<c:param name="required" value="required" />
			<c:param name="title">
				<fmt:message key="consulta.camp.dataInici" />
			</c:param>
			<c:param name="camp" value="dataInici" />
			<c:param name="maxlength" value="10" />
			<c:param name="aclaracio">
				<fmt:message key="consulta.aclaracio.formatData" />
			</c:param>
			<c:param name="clase" value="conMargen campoForm165" />
		</c:import> 
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="calendar" />
			<c:param name="path" value="formData.dataFi" />
			<c:param name="required" value="required" />
			<c:param name="title">
				<fmt:message key="consulta.camp.dataFi" />
			</c:param>
			<c:param name="camp" value="dataFi" />
			<c:param name="maxlength" value="10" />
			<c:param name="aclaracio">
				<fmt:message key="consulta.aclaracio.formatData" />
			</c:param>
			<c:param name="clase" value="campoForm165" />
		</c:import>
		
		<div class="separadorH"></div>
		
		<div class="botonesForm">
		<div id="guardarForm" class="btnCorto"
			onclick="submitForm('formulario')"
			onmouseover="underline('enlaceGuardarForm')"
			onmouseout="underline('enlaceGuardarForm')"><a
			id="enlaceGuardarForm" href="javascript:void(0);"><fmt:message
			key="manteniment.cercar" /></a></div>
		</div>
		
		<c:if test="${llistat != null && empty llistat}">
			<div class="separadorH"></div>
		
			<c:import url="comu/MissatgesIErrors.jsp">
				<c:param name="listError">
					<fmt:message key="consulta.entradaOliva.noRegs" />
				</c:param>
			</c:import>
		</c:if>
	</fieldset>
</form>

<div class="separadorH"></div>

<c:if test="${llistatVarietats != null && not empty llistatVarietats}">


<div class="separadorH"></div><div class="separadorH"></div><div class="separadorH"></div>
<div class="separadorH"></div><div class="separadorH"></div><div class="separadorH"></div>
<div class="separadorH"></div><div class="separadorH"></div><div class="separadorH"></div>

</c:if>

<div class="separadorH"></div>

<c:if test="${llistat != null && not empty llistat}">
	<div id="listado"><%-- Tabla de resultados --%>
	<div class="lotes" style="width: 745px; margin: 30px 0pt 0pt 40px;">
		<h3><fmt:message key="consulta.entradaOliva.titol.llistat" /></h3>
	</div>
	<div class="layoutSombraTabla ancho">
	<div class="rellenoInf"></div>
	<div class="rellenoIzq"></div>
	<div class="rellenoDer"></div>
	<div class="supDer"></div>
	<div class="supIzq"></div>
	<div class="infIzq"></div>
	<div class="infDer"></div>

<jsp:scriptlet>
       org.displaytag.decorator.MultilevelTotalTableDecorator subtotals = new org.displaytag.decorator.MultilevelTotalTableDecorator();
       subtotals.setGrandTotalDescription("Total");    // optional, defaults to Grand Total
       subtotals.setSubtotalLabel("{0} Subtotal", request.getLocale());      // optional, defaults to "{0} Total"
       pageContext.setAttribute("subtotaler", subtotals);
    </jsp:scriptlet>
    
	<display:table name="llistat" requestURI="" id="dp"  export="true" sort="list" defaultsort="1" cellpadding="0" cellspacing="0" class="listadoAncho selectable noEnlace" decorator="subtotaler">
		<display:footer></display:footer>
		<display:footer></display:footer>
		<display:column titleKey="consulta.registre.entrades.numPartida" headerClass="ancho32" sortable="true" sortProperty="partidaOliva.id" style="${color}">
				<c:out value="${dp.partidaOliva.numeroDocument}" />
		</display:column>
		<display:column titleKey="consulta.registre.entrades.data" headerClass="ancho75" sortable="true" sortProperty="partidaOliva.data" style="${color}">
				<c:out value="${dp.partidaOliva.data}" />
		</display:column>
		<display:column titleKey="consulta.registre.entrades.numDocument" headerClass="ancho32" sortable="true" sortProperty="descomposicioPlantacio.plantacio.finca.olivicultor.codiDOPOliva" style="${color}">
				<c:out value="${dp.partidaOliva.numeroEntrada}" />
		</display:column>
		<display:column titleKey="consulta.registre.entrades.numInscrit" headerClass="ancho100" sortable="true" sortProperty="descomposicioPlantacio.plantacio.finca.olivicultor.codiDOPOliva" style="${color}">
				RT-<c:out value="${dp.descomposicioPlantacio.plantacio.finca.olivicultor.codiDOPOliva}" />
		</display:column>
		<display:column titleKey="consulta.registre.entrades.municipi" headerClass="ancho100" style="${color}">
				<c:out value="${dp.descomposicioPlantacio.plantacio.municipi.nom}" />
		</display:column>
		<display:column titleKey="consulta.registre.entrades.poligon" headerClass="ancho50" style="${color}">
				<c:out value="${dp.descomposicioPlantacio.plantacio.poligon}" />
		</display:column>
		<display:column titleKey="consulta.registre.entrades.parcela" headerClass="ancho50" style="${color}">
				<c:out value="${dp.descomposicioPlantacio.plantacio.parcela}" />
		</display:column>
		<display:column titleKey="consulta.registre.entrades.envasPetit" headerClass="ancho20" sortable="true" sortProperty="partidaOliva.envasPetit" style="${color}">
			<c:choose>	
				<c:when test="${dp.partidaOliva.envasPetit==true}"><fmt:message key="consulta.registre.entrades.si" /></c:when>
				<c:when test="${dp.partidaOliva.envasPetit==false}"><fmt:message key="consulta.registre.entrades.no" /></c:when>
			</c:choose>
		</display:column>
		<display:column titleKey="consulta.registre.entrades.envasRigid" headerClass="ancho20" sortable="true" sortProperty="partidaOliva.envasRigid" style="${color}">
			<c:choose>
				<c:when test="${dp.partidaOliva.envasRigid==true}"><fmt:message key="consulta.registre.entrades.si" /></c:when>
				<c:when test="${dp.partidaOliva.envasRigid==false}"><fmt:message key="consulta.registre.entrades.no" /></c:when>
			</c:choose>
		</display:column>
		<display:column titleKey="consulta.registre.entrades.envasVentilat" headerClass="ancho20" sortable="true" sortProperty="partidaOliva.envasVentilat" style="${color}">
			<c:choose>
				<c:when test="${dp.partidaOliva.envasVentilat==true}"><fmt:message key="consulta.registre.entrades.si" /></c:when>
				<c:when test="${dp.partidaOliva.envasVentilat==false}"><fmt:message key="consulta.registre.entrades.no" /></c:when>
			</c:choose>
		</display:column>
		<display:column titleKey="consulta.registre.entrades.kg.verda" headerClass="ancho20" sortable="true" sortProperty="kilos" style="${color}">
			<c:if test="${dp.partidaOliva.tipusOlivaTaula==0}"><c:out value="${dp.kilos}" /></c:if>
		</display:column>
		<display:column titleKey="consulta.registre.entrades.kg.negra" headerClass="ancho20" sortable="true" sortProperty="kilos" style="${color}">
			<c:if test="${dp.partidaOliva.tipusOlivaTaula==2}"><c:out value="${dp.kilos}" /></c:if>
		</display:column>
		<display:setProperty name="export.xml" value="false" />
   		<display:setProperty name="export.csv" value="false" />
   		<display:setProperty name="export.rtf" value="false" />
   		<display:setProperty name="export.pdf" value="false" />
   		<display:setProperty name="export.excel.filename" value="LlibreRegistreEntrades.xls" />
	</display:table>
	
	</div>
	</div>


</c:if>

	<%-- Colores en tablas --%>
	<script type="text/javascript">
			$(document).ready(function(){
				$('#dp > thead').prepend( 
						'<tr>'
						+	'<th colspan="2"></th><th colspan="5" style="text-align: center;"><fmt:message key="consulta.registre.entrades.origen" /></th><th colspan="3" style="text-align: center;"><fmt:message key="consulta.registre.entrades.condicionsTransport" /></th><th colspan="2" style="text-align: center;"><fmt:message key="consulta.registre.entrades.kg" /></th>'
						+'</tr>'
						+'<tr>'
						+	'<th></th><th></th><th></th><th></th><th colspan="3" style="text-align: center;"><fmt:message key="consulta.registre.entrades.finca" /></th><th></th><th></th><th></th><th></th><th></th>'
						+'</tr>'
				);
				setEstilosTabla();
				
			})
		</script>

</body>
</html>
