<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/fn-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>
<%@ page buffer="64kb"%>
<%@ page autoFlush="true"%>

<html>
	<head>
		<title><fmt:message key="historic.llistat.factures.llistitol" /></title>
		<script type="text/javascript" src="js/displaytag.js"></script>
		<%-- <link href="css/displaytag.css" rel="stylesheet" type="text/css"/> --%>
		<script type="text/javascript" language="javascript">
		
			function submitFormulari(codi){
				var formulari = "formulario_" + codi;	
				document.getElementById(formulari).submit();
			}
		
		
		</script>
		
	</head>

	<body>
		<div id="listadoEstrecho"><!-- Tabla de resultados -->
		
			<c:if test="${not empty llistat}">
				<span class="filtres">
					<fmt:message key="manteniment.filtra.resultats" />: <input type="text" id="filtre" value="" />
				</span>
			</c:if>
			
			<div class="layoutSombraTabla ancho">
				<c:if test="${not empty llistat}">
					<div class="rellenoInf"></div>
					<div class="rellenoIzq"></div>
					<div class="rellenoDer"></div>
					<div class="supDer"></div>
					<div class="supIzq"></div>
					<div class="infIzq"></div>
					<div class="infDer"></div>
				</c:if>
				
				<display:table name="llistat" requestURI="" id="historic" export="true" sort="list" cellpadding="0" cellspacing="0" class="listado888">
					<display:column titleKey="historic.llistat.factures.campanya" sortable="true" sortProperty="campanya">
						<c:out value="${historic[0]}" />
					</display:column>
					<display:column titleKey="historic.llistat.factures.olivicultor" sortable="true" headerClass="ancho260">
						<c:out value="${historic[8]}"/> - <c:out value="${historic[4]}"/>
					</display:column>
					<display:column titleKey="historic.llistat.factures.factura" sortable="true" headerClass="ancho260">
						<c:out value="${historic[5]}"/>
					</display:column>
					<display:column titleKey="historic.llistat.factures.data" sortable="true">
						<c:out value="${historic[1]}" />
					</display:column>
					<display:column titleKey="historic.llistat.factures.import" sortable="true">
						<c:out value="${historic[6]}" />
					</display:column>
					<display:column titleKey="historic.llistat.factures.pagat" sortable="true">
						<c:if test="${historic[7] eq true}"><c:out value="Sí" /></c:if>
						<c:if test="${historic[7] eq false}"><c:out value="No" /></c:if>
					</display:column>
					
					<display:column headerClass="final">
						<form name="formulario_${historic[2]}" id= "formulario_${historic[2]}" action="GenerarPdf.html" method="post" style="width: auto !important;">
							<input type="hidden" id="tipus" name="tipus" value="12" />
							<input type="hidden" id="historic" name="historic" value="S" />
							<input type="hidden" id="identificador" name="identificador" value="${historic[2]}" />
							<div class="btnLargo156" onmouseover="underline('enlaceCrearForm')"
									onmouseout="underline('enlaceCrearForm')"
									onclick="submitFormulari(${historic[2]});">
								<a id="enlaceCrearForm" href="javascript:void(0);" style="color: #000 !important;">
									<fmt:message key="manteniment.generar" />
								</a>
							</div>
						</form>
					</display:column>
					<display:setProperty name="export.xml" value="false" />
				<display:setProperty name="export.csv" value="false" />
				<display:setProperty name="export.rtf" value="false" />
				<display:setProperty name="export.pdf" value="false" />
				<display:setProperty name="export.excel.include_header" value="true" />
				<display:setProperty name="export.excel.filename" value="HistoricFactures.xls" />
				<display:setProperty name="export.decorated" value="true" />
				</display:table>
			</div>
		</div>

		<c:if test="${not empty llistat}">
			<!-- Colores en tablas -->
			<script type="text/javascript">
				$(document).ready(function(){
					setEstilosTabla();
					$("#filtre").keyFilter("historic");
				})
			</script>
		</c:if>
	</body>
</html>
