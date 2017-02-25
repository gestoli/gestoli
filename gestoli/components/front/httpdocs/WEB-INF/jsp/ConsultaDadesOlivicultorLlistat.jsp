<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>
<%@ page buffer="64kb"%>
<%@ page autoFlush="true"%>

<html>
<head>

<title><fmt:message key="consulta.dadesOlivicultor.title" /></title>
<%--
    <script type="text/javascript" src="js/form.js"></script>
    <script type="text/javascript" src="js/jscalendar/calendar_stripped.js"></script>
	<script type="text/javascript" src="js/jscalendar/calendar-setup_stripped.js"></script>
	<script type="text/javascript" src="js/jscalendar/lang/<fmt:message key="webapp.calendar"/>"></script>
	<script type="text/javascript" src="js/selectbox.js"></script>
	<link type="text/css" href="js/jscalendar/calendar-viti.css" rel="stylesheet"/>
--%>

<script type="text/javascript" src="js/form.js"></script>
<c:if test="${not empty llistat}">
	<script type="text/javascript" src="js/displaytag.js"></script>
	<%--<link href="css/displaytag.css" rel="stylesheet" type="text/css"/> --%>
</c:if>

</head>
<body>

<c:if test="${(not empty esDoGestor || not empty esAdministracio || not empty esDoControlador || not empty esOlivicultor) }">
	<div id="listado"><%-- Tabla de resultados --%>
	<div class="layoutSombraTabla ancho">
	<div class="rellenoInf"></div>
	<div class="rellenoIzq"></div>
	<div class="rellenoDer"></div>
	<div class="supDer"></div>
	<div class="supIzq"></div>
	<div class="infIzq"></div>
	<div class="infDer"></div>
	<display:table name="llistat" requestURI="" id="descomposicioPlantacio" sort="list" cellpadding="0" cellspacing="0" class="listadoAncho" export="true">
		<display:column property="plantacio.finca.olivicultor.nom" titleKey="consulta.dadesOlivicultor.camp.olivicultor" sortable="true" group="1"/>
		<display:column property="plantacio.finca.nom" titleKey="consulta.dadesOlivicultor.camp.finca" sortable="true" group="2"/>
		<display:column property="plantacio.nomComplet" titleKey="consulta.dadesOlivicultor.camp.plantacio" sortable="true" group="3"/>
		<display:column property="varietatOliva.nomVarietat" titleKey="consulta.dadesOlivicultor.camp.varietat" sortable="true"/>
		<display:column titleKey="consulta.dadesOlivicultor.camp.superficie" sortable="true" >
			${descomposicioPlantacio.superficie} Ha
		</display:column>
		<display:column property="numeroOliveres" titleKey="consulta.dadesOlivicultor.camp.numeroOliveres" sortable="true" headerClass="final" />
		<display:setProperty name="export.xml" value="false" />
   		<display:setProperty name="export.csv" value="false" />
   		<display:setProperty name="export.rtf" value="false" />
   		<display:setProperty name="export.pdf" value="false" />
   		<display:setProperty name="export.excel.include_header" value="true" />
   		<display:setProperty name="export.excel.filename" value="DadesOlivicultor.xls" />
   		<display:setProperty name="export.decorated" value="true" />
		
	</display:table>
	</div>
	</div>

	<br /><br /><br /><br /><br />
	<h3 style="margin-left:240px"><fmt:message key="consulta.dadesOlivicultor.titol.resum" /></h3>
	<div id="listado"><%-- Tabla de resultados --%>
	<div class="layoutSombraTabla ancho">
	<div class="rellenoInf"></div>
	<div class="rellenoIzq"></div>
	<div class="rellenoDer"></div>
	<div class="supDer"></div>
	<div class="supIzq"></div>
	<div class="infIzq"></div>
	<div class="infDer"></div>
	<display:table name="llistatResum" requestURI="" id="registre" sort="list" cellpadding="0" cellspacing="0" class="listadoEstrecho" export="true">
		<display:column titleKey="consulta.dadesOlivicultor.camp.varietat" sortable="true" group="1">
			${registre[0]}
		</display:column>
		<display:column titleKey="consulta.dadesOlivicultor.camp.superficie" sortable="true" >
			<fmt:formatNumber type="number" maxFractionDigits="2" value="${registre[1]}" /> Ha
		</display:column>
		<display:column titleKey="consulta.dadesOlivicultor.camp.numeroOliveres" sortable="true" headerClass="final" >
			${registre[2]}
		</display:column>
		<display:setProperty name="export.xml" value="false" />
   		<display:setProperty name="export.csv" value="false" />
   		<display:setProperty name="export.rtf" value="false" />
   		<display:setProperty name="export.pdf" value="false" />
   		<display:setProperty name="export.excel.include_header" value="true" />
   		<display:setProperty name="export.excel.filename" value="DadesOlivicultorResum.xls" />
   		<display:setProperty name="export.decorated" value="true" />
		
	</display:table>
	</div>
	</div>


	<%-- Colores en tablas --%>
	<script type="text/javascript">
			$(document).ready(function(){
				setEstilosTabla();
			})
		</script>
</c:if>





</body>
</html>