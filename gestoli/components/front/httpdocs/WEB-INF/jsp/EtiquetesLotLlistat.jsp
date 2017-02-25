<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>
<%@ page buffer="64kb"%>
<%@ page autoFlush="true"%>


<html>
<head>
	<title><fmt:message key="lot.llistitol" /></title>
	<script type="text/javascript" src="js/displaytag.js"></script>	
</head>
<body>

	<div id="listado">
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
		<display:table name="llistat" requestURI="" id="etiqueta" export="true" sort="list" cellpadding="0" cellspacing="0"	class="listadoAncho">
			<display:column titleKey="etiquetesLot.camp.etiquetaInicial" property="etiquetaInicial" headerClass="ancho100" sortable="true"/>
			<display:column titleKey="etiquetesLot.camp.etiquetaFinal" property="etiquetaFinal" headerClass="ancho100" sortable="true"/>
			<display:column titleKey="etiquetesLot.camp.olivaTaula" headerClass="ancho100" sortable="true">
				<div>
					<c:choose>
						<c:when test="${etiqueta.olivaTaula}">
							<c:out value="Sí"></c:out>
						</c:when>
						<c:otherwise>
							<c:out value="No"></c:out>
						</c:otherwise>
					</c:choose>	
				</div>
			</display:column>
			<c:choose>
				<c:when test="${(empty etiqueta.teFills || !etiqueta.teFills) && !etiqueta.utilitzat && !etiqueta.retirat}">
					<display:column sortable="false" headerClass="ancho32 final">
						<form id="deleteForm_${etiqueta.id}" action="EtiquetesLot.html" method="post" class="mini" onsubmit="return confirm('<fmt:message key="lot.borrar.segur"/>')">
							<input id="id" name="id" value="${etiqueta.id}" type="hidden" /> 
							<input id="idEstabliment" name="idEstabliment" value="${param.idEstabliment}" type="hidden" /> 
							<input id="action" name="action" value="delete"	type="hidden" />
							<div id="eliminarForm" class="iconoBorrar" title="<fmt:message key="lot.borrar"/>"
								onclick="submitFormConfirm('deleteForm_${etiqueta.id}','<fmt:message key="manteniment.esborrar.confirm"/>');">
							</div>
						</form>
					</display:column>
				</c:when>
				<c:otherwise>
					<display:column sortable="false" headerClass="ancho32 final" />
				</c:otherwise>
			</c:choose>
			<display:setProperty name="export.xml" value="false" />
			<display:setProperty name="export.csv" value="false" />
			<display:setProperty name="export.rtf" value="false" />
			<display:setProperty name="export.pdf" value="false" />
			<display:setProperty name="export.excel.include_header" value="true" />
			<display:setProperty name="export.excel.filename" value="etiquetes.xls" />
			<display:setProperty name="export.decorated" value="true" />
		</display:table>
	
	
	<div class="separadorH"></div>
	
	<div class="btnCorto" onmouseover="underline('enlaceCrearForm')" onmouseout="underline('enlaceCrearForm')" onclick="document.location ='EtiquetesLotForm.html?idEstabliment=${param.idEstabliment}'">
		<a id="enlaceCrearForm" href="javascript:void(0);"><fmt:message key="manteniment.crearnou" /></a>
	</div>
	
	<div class="btnCorto" onclick="document.location ='EtiquetesLotEstabliment.html'"
		onmouseover="underline('enlaceTornarForm')"
		onmouseout="underline('enlaceTornarForm')">
	<a id="enlaceTornarForm" href="javascript:void(0);">
		<fmt:message key="proces.tornar" />
	</a>
</div>
	
</div>
	
<div class="separadorH"></div>

<div class="info">
	<img alt="Informació" src="img/icons/info.png">
	<p><fmt:message key="etiquetesLot.missatge.info" /></p>
<div>	
	
	<c:if test="${not empty llistat}">
		<%-- Colores en tablas --%>
		<script type="text/javascript">
				$(document).ready(function(){
					setEstilosTabla();
				})
			</script>
	</c:if>

</body>
</html>


















