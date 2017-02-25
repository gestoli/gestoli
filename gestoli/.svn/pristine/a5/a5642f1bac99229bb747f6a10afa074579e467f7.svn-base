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
<title><fmt:message key="consulta.sortidesOlivaTaula.titol" /></title>

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
<form id="formulario" action="ConsultaLlibreSortidesOlivaTaula.html" method="post" class="extended seguit">
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
				onmouseout="underline('enlaceGuardarForm')">
				<a id="enlaceGuardarForm" href="javascript:void(0);"><fmt:message key="manteniment.cercar" /></a>
			</div>
		</div>
		
		<c:if test="${listaEnvassat != null && empty listaEnvassat && listaSortides != null && empty listaSortides}">
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
<div class="separadorH"></div>

<c:if test="${listaEnvassat != null && not empty listaEnvassat}">
	<div id="listado">
		<div class="lotes" style="width: 745px; margin: 30px 0pt 0pt 40px;">
			<h3><fmt:message key="consulta.olivaEnvasada.titol.llistat" /></h3>
		</div>
		<div id="divScroll" style="width: 888px; margin-left:45px; overflow: auto;">
			<div class="layoutSombraTabla ancho">
				<div class="rellenoInf"></div>
				<div class="rellenoIzq"></div>
				<div class="rellenoDer"></div>
				<div class="supDer"></div>
				<div class="supIzq"></div>
				<div class="infIzq"></div>
				<div class="infDer"></div>

				<display:table name="listaEnvassat" requestURI="" id="regEnvasat"  export="true" sort="list" defaultsort="1" cellpadding="0" cellspacing="0" class="selectable noEnlace">
					<display:column property="bota.identificador" titleKey="consulta.registre.elaboracio.num_bota" headerClass="ancho75" sortable="true" sortProperty="bota.tipusOlivaTaula" style="${color}"/>
					<display:column property="data" titleKey="consulta.olivaSortides.data" headerClass="ancho75" sortable="true" sortProperty="data" style="${color}"/>
					<display:column titleKey="consulta.registre.elaboracio.tipus.verda" headerClass="ancho20" sortProperty="bota.tipusOlivaTaula" style="${color}">
							<c:if test="${regEnvasat.bota.tipusOlivaTaula==0}"> X </c:if>
					</display:column>
					<display:column titleKey="consulta.registre.elaboracio.tipus.trencada" headerClass="ancho20" sortable="true" sortProperty="bota.tipusOlivaTaula" style="${color}">
							<c:if test="${regEnvasat.bota.tipusOlivaTaula==1}"> X </c:if>
					</display:column>
					<display:column titleKey="consulta.registre.elaboracio.tipus.negra" headerClass="ancho20" sortable="true" sortProperty="bota.tipusOlivaTaula" style="${color}">
							<c:if test="${regEnvasat.bota.tipusOlivaTaula==2}"> X </c:if>
					</display:column>
					<display:column titleKey="consulta.olivaSortides.lot.oli" headerClass="ancho75" sortable="true" style="${color}">
						<c:choose>
							<c:when test="${not empty regEnvasat.partidaOli}">Granel - Partida: <c:out value="${regEnvasat.partidaOli.nom}"/></c:when>
							<c:otherwise><c:out value="${regEnvasat.lotOliAfegit}"/></c:otherwise>
						</c:choose>
					</display:column>
					<display:column property="numeroBotellesInicials" titleKey="consulta.olivaSortides.num.envasos" headerClass="ancho75" total="true" format="{0,number,#,##0}" style="${color}"/>
					<display:column property="etiquetatge.tipusEnvas.volum" titleKey="consulta.olivaSortides.volum" headerClass="ancho75" total="true" format="{0,number,#,##0.00} l." style="${color}"/>
					<display:column property="codiAssignat" titleKey="consulta.olivaSortides.num.lot" headerClass="ancho75" style="${color}"/>
					<display:column property="pH1" titleKey="consulta.olivaEnvasada.envasat.ph.inicial" headerClass="ancho75" format="{0,number,#,##0.00}" style="${color}"/>
					<display:column property="quantitatAcidCitric" titleKey="consulta.olivaEnvasada.envasat.ph.acid" headerClass="ancho75" format="{0,number,#,##0.00}" style="${color}"/>
					<display:column property="lotAcidCitric" titleKey="consulta.olivaEnvasada.envasat.ph.acid.lot" headerClass="ancho100" style="${color}"/>
					<display:column property="pH2" titleKey="consulta.olivaEnvasada.envasat.ph.final" headerClass="ancho75 final" format="{0,number,#,##0.00}" style="${color}"/>
					<display:setProperty name="export.xml" value="false" />
			   		<display:setProperty name="export.csv" value="false" />
			   		<display:setProperty name="export.rtf" value="false" />
			   		<display:setProperty name="export.pdf" value="false" />
			   		<display:setProperty name="export.excel.filename" value="LlibreRegistreEnvasat.xls" />
				</display:table>
			</div>
		</div>
	</div>
</c:if>

<c:if test="${listaSortides != null && not empty listaSortides}">
	<div id="listado" style="padding: 20px 0 0 0;">
		<div class="lotes" style="width: 745px; margin: 30px 0pt 0pt 40px;">
			<h3><fmt:message key="consulta.olivaEnvasada.titol.llistat1" /></h3>
		</div>
		<div id="divScrollable" style="width: 888px; margin-left:45px;">
			<div class="layoutSombraTabla ancho">
				<div class="rellenoInf"></div>
				<div class="rellenoIzq"></div>
				<div class="rellenoDer"></div>
				<div class="supDer"></div>
				<div class="supIzq"></div>
				<div class="infIzq"></div>
				<div class="infDer"></div>

			    <display:table name="listaSortides" requestURI="" id="regSortides"  export="true" sort="list" cellpadding="0" cellspacing="0" class="selectable noEnlace">
			    	<display:column property="lot.codiAssignat" titleKey="consulta.olivaSortides.num.lot" headerClass="ancho75" style="${color}"/>
			    	<display:column property="vendaData" titleKey="consulta.olivaSortides.data.sortida" headerClass="ancho75" sortable="true" sortProperty="vendaData" style="${color}"/>
			    	<display:column property="vendaMotiu" titleKey="consulta.olivaSortides.nom" headerClass="ancho75" style="${color}"/>
			    	<display:column titleKey="consulta.olivaSortides.document" headerClass="ancho75" style="${color}">
			    		<c:if test="${not empty regSortides.vendaTipusDocument}">
			    			<c:out value="${regSortides.vendaTipusDocument}"/>:  
			    		</c:if>
			    		<c:out value="${regSortides.vendaNumeroDocument}"/>
			    	</display:column>
			    	<display:column property="vendaNumeroBotelles" titleKey="consulta.olivaSortides.num.envasos" headerClass="ancho75" total="true" format="{0,number,#,##0}" style="${color}"/>
				<display:column property="lot.etiquetatge.tipusEnvas.volum" titleKey="consulta.olivaSortides.volum" headerClass="ancho75" total="true" format="{0,number,#,##0.00} l." style="${color}"/>
				<display:column titleKey="consulta.olivaSortides.categoria" headerClass="ancho75 final" total="true" style="${color}">
					<c:if test="${regSortides.olivaDO == false}"><c:set var="categoria"><fmt:message key="consulta.olivaSortides.NoDO"/></c:set></c:if>
					<c:if test="${empty regSortides.olivaDO}"><c:set var="categoria"><fmt:message key="consulta.olivaSortides.Pendent"/></c:set></c:if>
					<c:if test="${regSortides.olivaDO == true}"><c:set var="categoria"><fmt:message key="consulta.olivaSortides.DO"/></c:set></c:if>
					<c:out value="${categoria}"/>
				</display:column>
				    <display:setProperty name="export.xml" value="false" />
			   		<display:setProperty name="export.csv" value="false" />
			   		<display:setProperty name="export.rtf" value="false" />
			   		<display:setProperty name="export.pdf" value="false" />
			   		<display:setProperty name="export.excel.filename" value="LlibreRegistreSortides.xls" />
				</display:table>
			</div>
		</div>
	</div>
</c:if>
<!-- llista sortides oliva crua a granel --> 
<c:if test="${sortidesOlivaCruaGranel != null && not empty sortidesOlivaCruaGranel}">
	<div id="listado" style="padding: 20px 0 0 0;">
		<div class="lotes" style="width: 745px; margin: 30px 0pt 0pt 40px;">
			<h3><fmt:message key="consulta.olivaEnvasada.titol.sortidaOlivaCruaGranel" /></h3>
		</div>
		<div id="divScrollable" style="width: 888px; margin-left:45px;">
			<div class="layoutSombraTabla ancho">
				<div class="rellenoInf"></div>
				<div class="rellenoIzq"></div>
				<div class="rellenoDer"></div>
				<div class="supDer"></div>
				<div class="supIzq"></div>
				<div class="infIzq"></div>
				<div class="infDer"></div>
			    <display:table name="sortidesOlivaCruaGranel" requestURI="" id="regOlivaCrua"  export="true" sort="list" cellpadding="0" cellspacing="0" class="selectable noEnlace">
			    	<display:column property="vendaData" titleKey="consulta.olivaSortides.data.sortida" headerClass="ancho100" sortable="true" sortProperty="vendaData" style="${color}"/>
			    	<display:column property="vendaMotiu" titleKey="consulta.olivaSortides.nom" headerClass="ancho120" style="${color}"/>
			    	<display:column property="vendaKilos" titleKey="consulta.olivaSortides.kilos" headerClass="ancho90" total="true" format="{0,number,#,##0.00}" style="${color}"/>
			    	<display:column titleKey="consulta.olivaSortides.doc" headerClass="ancho110" style="${color}">
			    		<c:if test="${not empty regOlivaCrua.vendaTipusDocument}">
			    			<c:out value="${regOlivaCrua.vendaTipusDocument}"/>:  
			    		</c:if>
			    		<c:out value="${regOlivaCrua.vendaNumeroDocument}"/>
			    	</display:column>
			    	<display:column titleKey="consulta.olivaSortides.destinatari" headerClass="ancho120" style="${color}">
				    	<c:if test="${not empty regOlivaCrua.vendaDestinatari}">
				    		<c:out value="${regOlivaCrua.vendaDestinatari}"/>
				    	</c:if>
			    	</display:column>
			    	<display:column titleKey="consulta.olivaSortides.pais" headerClass="ancho110" style="${color}">
				    	<c:if test="${not empty regOlivaCrua.pais}">
				    		<c:if test="${lang=='es'}">
				    			<c:out value="${regOlivaCrua.pais.nomcas}"/>
				    		</c:if>
				    		<c:if test="${lang=='ca'}">
				    			<c:out value="${regOlivaCrua.pais.nomcat}"/>
				    		</c:if>
				    	</c:if>
			    	</display:column>
				    <display:setProperty name="export.xml" value="false" />
			   		<display:setProperty name="export.csv" value="false" />
			   		<display:setProperty name="export.rtf" value="false" />
			   		<display:setProperty name="export.pdf" value="false" />
			   		<display:setProperty name="export.excel.filename" value="LlibreRegistreSortidesOlivaCruaGranel.xls" />
				</display:table>
			</div>
		</div>
	</div>
</c:if>
<!-- llista sortides oliva en bota a granel --> 
<c:if test="${sortidesOlivaBotaGranel != null && not empty sortidesOlivaBotaGranel}">
	<div id="listado" style="padding: 20px 0 0 0;">
		<div class="lotes" style="width: 745px; margin: 30px 0pt 0pt 40px;">
			<h3><fmt:message key="consulta.olivaEnvasada.titol.sortidaOlivaBotaGranel" /></h3>
		</div>
		<div id="divScrollable" style="width: 888px; margin-left:45px;">
			<div class="layoutSombraTabla ancho">
				<div class="rellenoInf"></div>
				<div class="rellenoIzq"></div>
				<div class="rellenoDer"></div>
				<div class="supDer"></div>
				<div class="supIzq"></div>
				<div class="infIzq"></div>
				<div class="infDer"></div>

			    <display:table name="sortidesOlivaBotaGranel" requestURI="" id="regBotaGranel"  export="true" sort="list" cellpadding="0" cellspacing="0" class="selectable noEnlace">
			    	<display:column property="vendaData" titleKey="consulta.olivaSortides.data.sortida" headerClass="ancho110" sortable="true" sortProperty="vendaData" style="${color}"/>
			    	<display:column property="vendaMotiu" titleKey="consulta.olivaSortides.nom" headerClass="ancho120" style="${color}"/>
			    	<display:column property="vendaKilos" titleKey="consulta.olivaSortides.kilos" headerClass="ancho90" total="true" format="{0,number,#,##0.00}" style="${color}"/>
			    	<display:column titleKey="consulta.olivaSortides.doc" headerClass="ancho120" style="${color}">
			    		<c:if test="${not empty regBotaGranel.vendaTipusDocument}">
			    			<c:out value="${regBotaGranel.vendaTipusDocument}"/>:  
			    		</c:if>
			    		<c:out value="${regBotaGranel.vendaNumeroDocument}"/>
			    	</display:column>
			    	<display:column titleKey="consulta.olivaSortides.destinatari" headerClass="ancho120" style="${color}">
				    	<c:if test="${not empty regBotaGranel.vendaDestinatari}">
				    		<c:out value="${regBotaGranel.vendaDestinatari}"/>
				    	</c:if>
			    	</display:column>
			    	<display:column titleKey="consulta.olivaSortides.pais" headerClass="ancho110" style="${color}">
				    	<c:if test="${not empty regBotaGranel.pais}">
				    		<c:if test="${lang=='es'}">
				    			<c:out value="${regBotaGranel.pais.nomcas}"/>
				    		</c:if>
				    		<c:if test="${lang=='ca'}">
				    			<c:out value="${regBotaGranel.pais.nomcat}"/>
				    		</c:if>
				    	</c:if>
			    	</display:column>
				    <display:setProperty name="export.xml" value="false" />
			   		<display:setProperty name="export.csv" value="false" />
			   		<display:setProperty name="export.rtf" value="false" />
			   		<display:setProperty name="export.pdf" value="false" />
			   		<display:setProperty name="export.excel.filename" value="LlibreRegistreSortidesOlivaEnBotaAGranel.xls" />
				</display:table>
			</div>
		</div>
	</div>
</c:if>
<%-- Colores en tablas --%>
<script type="text/javascript">
	$(document).ready(function(){
		$('#regEnvasat > thead').prepend( 
				'<tr>'
				+	'<th></th>'
				+	'<th colspan="7" style="text-align: center;"><fmt:message key="consulta.olivaEnvasada.envasat" /></th>'
				+	'<th class="final"></th>'
				+	'<th colspan="4" style="text-align: center;"><fmt:message key="consulta.olivaEnvasada.envasat.ph" /></th>'
				+'</tr>'
		);
		$('#regSortides > thead').prepend( 
				'<tr>'
				+	'<th></th>'
				+	'<th colspan="3" style="text-align: center;"><fmt:message key="consulta.olivaSortides.desti" /></th>'
				+	'<th colspan="3" class="final"/></th>'
				+'</tr>'
		);
		setEstilosTabla();
		var alt = $("#regEnvasat").height();
		$('#divScroll').height(alt +50);
	})
</script>

</body>
</html>