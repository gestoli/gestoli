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
<title><fmt:message key="consulta.elaboracioOlivaTaula.titol" /></title>

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
<form id="formulario" action="ConsultaLlibreElaboracioOlivaTaula.html" method="post" class="extended seguit">
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
	
	<div id="divScrollable" style="overflow-y: auto;">
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
    
	<display:table name="llistat" requestURI="" id="partidaRegEla"  export="true" sort="list" defaultsort="1" cellpadding="0" cellspacing="0" class="selectable noEnlace" decorator="subtotaler">
		<display:footer></display:footer>
		<display:footer></display:footer>
		<display:column titleKey="consulta.registre.elaboracio.num_bota" headerClass="ancho75" sortable="true" sortProperty="elaboracio.bota.identificador" style="${color}">
				<c:out value="${partidaRegEla.elaboracio.bota.identificador}" />
		</display:column>
		<display:column titleKey="consulta.registre.elaboracio.num_partida" headerClass="ancho75" sortable="true" sortProperty="id" style="${color}">
				<c:out value="${partidaRegEla.partida.numeroDocument}" />
		</display:column>
		<display:column titleKey="consulta.registre.elaboracio.kg" headerClass="ancho50" style="${color}">
				${partidaRegEla.quantitat}
		</display:column>
		<display:column titleKey="consulta.registre.elaboracio.kg.criba" headerClass="ancho50" style="${color}">
				${partidaRegEla.quantitatCriba}
		</display:column>
		<display:column titleKey="consulta.registre.elaboracio.tipus.verda" headerClass="ancho20" sortable="true" sortProperty="elaboracio.bota.tipusOlivaTaula" style="${color}">
				<c:if test="${partidaRegEla.elaboracio.bota.tipusOlivaTaula==0}"> X </c:if>
		</display:column>
		<display:column titleKey="consulta.registre.elaboracio.tipus.trencada" headerClass="ancho20" sortable="true" sortProperty="elaboracio.bota.tipusOlivaTaula" style="${color}">
				<c:if test="${partidaRegEla.elaboracio.bota.tipusOlivaTaula==1}"> X </c:if>
		</display:column>
		<display:column titleKey="consulta.registre.elaboracio.tipus.negra" headerClass="ancho20" sortable="true" sortProperty="elaboracio.bota.tipusOlivaTaula" style="${color}">
				<c:if test="${partidaRegEla.elaboracio.bota.tipusOlivaTaula==2}"> X </c:if>
		</display:column>
		<display:column titleKey="consulta.registre.elaboracio.data_inici" headerClass="ancho75" sortable="true" sortProperty="elaboracio.data" style="${color}">
				<c:out value="${partidaRegEla.elaboracio.data}" />
		</display:column>
		<display:column titleKey="consulta.registre.elaboracio.lot_sal" headerClass="ancho50" sortable="true" sortProperty="elaboracio.bota.lotSal" style="${color}">
				<c:out value="${partidaRegEla.elaboracio.bota.lotSal}" />
		</display:column>
		<display:column titleKey="consulta.registre.elaboracio.concentracio" headerClass="ancho50" sortable="true" sortProperty="elaboracio.bota.concentracioSalmorra" style="${color}">
				<c:out value="${partidaRegEla.elaboracio.bota.concentracioSalmorra}" />
		</display:column>
		<display:column titleKey="consulta.registre.elaboracio.lot_fonoll" headerClass="ancho50" sortable="true" sortProperty="elaboracio.bota.lotFonoll" style="${color}">
				<c:if test="${not empty partidaRegEla.elaboracio.bota.partidaFonoll}">
					<c:out value="${partidaRegEla.elaboracio.bota.partidaFonoll.numeroDocument}" />
				</c:if>
				<c:if test="${not empty partidaRegEla.elaboracio.bota.lotFonoll}">
					<c:out value="${partidaRegEla.elaboracio.bota.lotFonoll}" />
				</c:if>
		</display:column>
		<display:column titleKey="consulta.registre.elaboracio.lot_pebre" headerClass="ancho50" sortable="true" sortProperty="elaboracio.bota.lotPebre" style="${color}">
				<c:out value="${partidaRegEla.elaboracio.bota.lotPebre}" />
		</display:column>
		<display:column titleKey="consulta.registre.elaboracio.data_fi" headerClass="ancho50 final" style="${color}">
				
		</display:column>
<%-- 		<display:column titleKey="consulta.registre.elaboracio.ph" headerClass="ancho50" sortable="true" sortProperty="elaboracioOlivaTaula.bota.pH1" style="${color}">
				<c:out value="${partidaRegEla.elaboracioOlivaTaula.bota.pH1}" />
		</display:column>
		<display:column titleKey="consulta.registre.elaboracio.acid" headerClass="ancho50" sortable="true" sortProperty="elaboracioOlivaTaula.bota.quantitatAcidCitric" style="${color}">
				<c:out value="${partidaRegEla.elaboracioOlivaTaula.bota.quantitatAcidCitric}" />
		</display:column>
		<display:column titleKey="consulta.registre.elaboracio.lot_acid" headerClass="ancho50" sortable="true" sortProperty="elaboracioOlivaTaula.bota.lotAcidCitric" style="${color}">
				<c:out value="${partidaRegEla.elaboracioOlivaTaula.bota.lotAcidCitric}" />
		</display:column>
		<display:column titleKey="consulta.registre.elaboracio.ph2" headerClass="ancho50" sortable="true" sortProperty="elaboracioOlivaTaula.bota.pH2" style="${color}">
				<c:out value="${partidaRegEla.elaboracioOlivaTaula.bota.pH2}" />
		</display:column> --%>
		
		
		<display:setProperty name="export.xml" value="false" />
   		<display:setProperty name="export.csv" value="false" />
   		<display:setProperty name="export.rtf" value="false" />
   		<display:setProperty name="export.pdf" value="false" />
   		<display:setProperty name="export.excel.filename" value="LlibreRegistreElaboracions.xls" />
	</display:table>
	</div>
	</div>
	</div>


</c:if>

	<%-- Colores en tablas --%>
	<script type="text/javascript">
			$(document).ready(function(){
				$('#partidaRegEla > thead').prepend( 
						'<tr>'
						+	'<th colspan="4"></th>'
						+	'<th colspan="3" style="text-align: center;"><fmt:message key="consulta.registre.elaboracio.tipus" /></th>'
						+	'<th colspan="6" style="text-align: center; background-image: none;"><fmt:message key="consulta.registre.elaboracio.condimentacio" /></th>'
/* 						+	'<th colspan="4" style="text-align: center;"><fmt:message key="consulta.registre.elaboracio.comprovacio_ph" /></th>' */
						+'</tr>'
				);
				setEstilosTabla();
				
			})
		</script>

</body>
</html>