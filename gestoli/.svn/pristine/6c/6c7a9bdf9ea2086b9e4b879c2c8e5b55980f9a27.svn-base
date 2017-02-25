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
		<display:table name="llistat" requestURI="" id="lot" export="true" sort="list" cellpadding="0" cellspacing="0"	class="listadoAncho selectable">
			<display:column titleKey="lot.camp.nomLot" property="codiAssignat" headerClass="ancho100" sortable="true" sortProperty="codiAssignat"/>
			<display:column titleKey="lot.camp.marca" property="etiquetatge.marca.nom" headerClass="ancho100" sortable="true"/>
			<display:column titleKey="lot.camp.etiquetatge" sortable="true">
				<c:out value="${lot.etiquetatge.tipusEnvas.volum} - ${lot.etiquetatge.tipusEnvas.color.nom} - ${lot.etiquetatge.tipusEnvas.materialTipusEnvas.nom}"/>
			</display:column>
			<display:column titleKey="lot.camp.zona" property="zona.nom" headerClass="ancho100" sortable="true"/>
			<display:column titleKey="lot.camp.partidaOli" property="partidaOli.nom" sortable="true" headerClass="ancho160"/>
			<display:column sortable="false" headerClass="ancho32 final">
				<form id="deleteForm_${lot.id}" action="Lot.html" method="post" class="mini" onsubmit="return confirm('<fmt:message key="lot.borrar.segur"/>')">
					<input id="id" name="id" value="<c:out value="${lot.id}"/>" type="hidden" /> 
					<input id="action" name="action" value="delete"	type="hidden" />
					<div id="eliminarForm" class="iconoBorrar" title="<fmt:message key="lot.borrar"/>"
						onclick="submitFormConfirm('deleteForm_${lot.id}','<fmt:message key="manteniment.esborrar.confirm"/>');">
					</div>
				</form>
			</display:column>
			<display:setProperty name="export.xml" value="false" />
			<display:setProperty name="export.csv" value="false" />
			<display:setProperty name="export.rtf" value="false" />
			<display:setProperty name="export.pdf" value="false" />
			<display:setProperty name="export.excel.include_header" value="true" />
			<display:setProperty name="export.excel.filename" value="lots.xls" />
			<display:setProperty name="export.decorated" value="true" />
		</display:table>
	</div>
	
	<div class="separadorH"></div>
	
	<form name="formulario" action="TancamentLlibresLotForm.html"></form>
	
	<c:if
		test="${(not empty esEnvasador || not empty esProductor) && not empty existenZonasActivasEnEstabliment}">
		<div class="btnCorto" onmouseover="underline('enlaceCrearForm')"
			onmouseout="underline('enlaceCrearForm')"
			onclick="document.formulario.submit();">
			<a id="enlaceCrearForm" href="javascript:void(0);">
				<fmt:message key="manteniment.crearnou" />
			</a>
		</div>
	</c:if>
	</div>
	
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


















