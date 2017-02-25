<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ page import="es.caib.gestoli.front.util.*"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>
<%@ page import="java.util.ResourceBundle"%>
<%@ page import="java.util.Locale"%>
<%@ page import="java.util.List"%>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es" lang="es">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />


<title>Gest-OLI - Dades Campanyes</title>

<script type="text/javascript" src="../js/calendar/calendar.js"></script>
<script type="text/javascript" src="../js/calendar/calendar-setup.js"></script>

<script type="text/javascript" src="../js/calendar/lang/calendar-ca.js"></script>
<script type="text/javascript" src="../js/calendar/lang/calendar-es.js"></script>

<link   type="text/css" href="../js/calendar/calendar-viti.css" rel="stylesheet" />
<script type="text/javascript" src="../js/web/onload.js"></script>
<script type="text/javascript" src="../js/web/form.js"></script>
<link rel="stylesheet" type="text/css" href="../css/web/tablas.css" />
<link rel="stylesheet" type="text/css" href="../css/general.css" />
<script type="text/javascript" src="../js/displaytag.js"></script>

</head>

<body>

<form id="formulario" name="dadescampanya" action="dadescampanya.ca.html" method="post" class="extended seguit ancho888" onsubmit="">
<div id="cercaIdMicroweb">
	<input type="hidden" id="idioma" value="ca" name="idioma"/>
	<div class="cercaId">

		<h2 class="microweb">Dades Campanyes</h2>
		<br />
		<h3>Introdueix les dates a consultar:</h3>

		<p>
		
			<div id="divIdFordataInici" class=" conMargen campoForm165 required">
					<script type="text/javascript">
						function parseData_dataInici(data){
							var str = data.value.split("/");
		
							if(str[2] != null){
								if(str[2].length == 2){
									data.value = str[0] + "/" + str[1] + "/" + "20" + str[2];
								}
								if(str[2].length == 3){
									data.value = str[0] + "/" + str[1] + "/" + "2" + str[2];
								}
							}
						}
					</script>
					<label for="dataInici">Data inici</label>
					<div class="bordeInput"> 
					<div id="dataIniciBordeFoco" class="bordeFoco">
						<input type="text" id="dataInici" name="dataInici" class="inputData" value="${dataInici}" onchange="" onblur="parseData_dataInici(this);" maxlength="10" size="10">
						<p class="comentari">(dd/mm/aaaa)</p>
					</div>
					</div>
			</div>

			<div id="divIdFordataFi" class=" campoForm165 required">
					<script type="text/javascript">
						function parseData_dataFi(data){
							var str = data.value.split("/");
		
							if(str[2] != null){
								if(str[2].length == 2){
									data.value = str[0] + "/" + str[1] + "/" + "20" + str[2];
								}
								if(str[2].length == 3){
									data.value = str[0] + "/" + str[1] + "/" + "2" + str[2];
								}
							}
						}
					</script>
					<label for="dataFi">Data fi</label>
					<div class="bordeInput">
					<div id="dataFiBordeFoco" class="bordeFoco">
					<input type="text" id="dataFi" name="dataFi" class="inputData" value="${dataFi}" onchange="" onblur="parseData_dataFi(this);" maxlength="10" size="10">
						<p class="comentari">(dd/mm/aaaa)</p>
					</div>
					</div>
			</div>

			<br />
			<div id="guardarForm" class="btnCorto" style="margin-top: 10px;"
				onclick="submitForm('formulario')"
				onmouseover="underline('enlaceGuardarForm')"
				onmouseout="underline('enlaceGuardarForm')">
				<a id="enlaceGuardarForm" href="javascript:void(0);">
					cercar
				</a>
			</div>
				
		</p>
	
	</div>

	<div class="separadorH"></div>

</div>
</form>

	<div class="separadorH"></div>
 
	<div id="listado"><%-- Tabla de resultados --%>
	<div class="layoutSombraTabla ancho">
		<c:if test="${llistat != null && not empty llistat}">
			<div class="rellenoInf"></div>
			<div class="rellenoIzq"></div>
			<div class="rellenoDer"></div>
			<div class="supDer"></div>
			<div class="supIzq"></div>
			<div class="infIzq"></div>
			<div class="infDer"></div>
			<display:table name="llistat" requestURI="" id="registre" export="true" sort="list" cellpadding="0" cellspacing="0" class="listadoAncho ancho600">
				<display:column title=" " headerClass="ancho150" sortable="false" group="1" >
					<b>${registre.nom}</b>
				</display:column>
				<display:column title="Campanya" headerClass="ancho150" sortable="false">
					${registre.camp}
				</display:column>
				<display:column title="Quantitat" headerClass="ancho100 final" sortable="false">
					${registre.valor}
				</display:column>
				<display:setProperty name="export.xml" value="false" />
		   		<display:setProperty name="export.csv" value="false" />
		   		<display:setProperty name="export.rtf" value="false" />
		   		<display:setProperty name="export.pdf" value="false" />
		   		<display:setProperty name="export.excel.include_header" value="true" />
		   		<display:setProperty name="export.excel.filename" value="ConsultaCampanya.xls" />
		   		<display:setProperty name="export.decorated" value="true" />
			</display:table>
		
			<%-- Colores en tablas --%>
			<script type="text/javascript">
					$(document).ready(function(){
						setEstilosTabla();
					})
				</script>
		</c:if>
	</div>
	
	</div>

</body>
</html>