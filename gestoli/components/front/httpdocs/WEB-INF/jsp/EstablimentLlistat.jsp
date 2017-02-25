<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>
<%@ page buffer="64kb"%>
<%@ page autoFlush="true"%>


<html>
<head>
<c:choose>
	<c:when test="${not empty vista || not empty analitica}">
		<title><fmt:message key="establiment.llistitol" /></title>
	</c:when>
	<c:otherwise>
		<title><fmt:message key="establiment.llistitol" /></title>
	</c:otherwise>
</c:choose>

<script type="text/javascript" src="js/displaytag.js"></script>

</head>
<body>

<div id="listado"><!-- Tabla de resultados -->
<div class="layoutSombraTabla ancho"><c:if
	test="${not empty llistat}">
	<div class="rellenoInf"></div>
	<div class="rellenoIzq"></div>
	<div class="rellenoDer"></div>
	<div class="supDer"></div>
	<div class="supIzq"></div>
	<div class="infIzq"></div>
	<div class="infDer"></div>
</c:if> <c:choose>
	<c:when test="${(not empty esDoGestor || not empty esAdministracio || not empty esDoControlador ) && not empty vista}">
		<display:table name="llistat" requestURI="" id="establiment" export="true" sort="list" cellpadding="0" cellspacing="0" class="listadoEstrecho selectable">
			<c:choose>
				<c:when test="${not empty consulta}">
					<display:column titleKey="establiment.camp.nom" sortable="true"
						sortProperty="nom">
						<a
							href="ConsultaLlistat.html?idEstabliment=<c:out value="${establiment.id}"/>&amp;fromEstabliment=true"><c:out
							value="${establiment.nom}" /></a>
					</display:column>
				</c:when>
				<c:otherwise>
					<display:column titleKey="establiment.camp.nom" sortable="true"
						sortProperty="nom">
						<a
							href="EstablimentPrincipal.html?establimentId=<c:out value="${establiment.id}"/>"><c:out
							value="${establiment.nom}" /></a>
					</display:column>
				</c:otherwise>
			</c:choose>
			<display:column titleKey="establiment.camp.cif" sortable="true"
				headerClass="ancho160 final">
				<c:out value="${establiment.cif}" />
			</display:column>
			<display:setProperty name="export.xml" value="false" />
			<display:setProperty name="export.csv" value="false" />
			<display:setProperty name="export.rtf" value="false" />
			<display:setProperty name="export.pdf" value="false" />
			<display:setProperty name="export.excel.include_header" value="true" />
			<display:setProperty name="export.excel.filename" value="establiments.xls" />
			<display:setProperty name="export.decorated" value="true" />
		</display:table>
	</c:when>
	<c:when test="${not empty esDoControlador && not empty analitica}">
		<display:table name="llistat" requestURI="" id="establiment" export="true" sort="list" cellpadding="0" cellspacing="0" class="listadoEstrecho selectable">
			<display:column titleKey="establiment.camp.nom" sortable="true" sortProperty="nom">
				<a href="ProcesInici.html?tipusProces=5&amp;establimentId=<c:out value="${establiment.id}"/>"><c:out value="${establiment.nom}" /></a>
			</display:column>
			<display:column titleKey="establiment.camp.cif" sortable="true" headerClass="ancho160 final">
				<c:out value="${establiment.cif}" />
			</display:column>
			<display:setProperty name="export.xml" value="false" />
			<display:setProperty name="export.csv" value="false" />
			<display:setProperty name="export.rtf" value="false" />
			<display:setProperty name="export.pdf" value="false" />
			<display:setProperty name="export.excel.include_header" value="true" />
			<display:setProperty name="export.excel.filename" value="establiments.xls" />
			<display:setProperty name="export.decorated" value="true" />
		</display:table>
	</c:when>
	<c:when test="${not empty esDoControlador && not empty pendentAnalitica}">
		<display:table name="llistat" requestURI="" id="establiment" export="true" sort="list" cellpadding="0" cellspacing="0" class="listadoEstrecho selectable">
			<display:column titleKey="establiment.camp.nom" sortable="true" sortProperty="nom">
				<a href="ProcesInici.html?tipusProces=14&amp;establimentId=<c:out value="${establiment.id}"/>"><c:out value="${establiment.nom}" /></a>
			</display:column>
			<display:column titleKey="establiment.camp.cif" sortable="true" headerClass="ancho160 final">
				<c:out value="${establiment.cif}" />
			</display:column>
			<display:setProperty name="export.xml" value="false" />
			<display:setProperty name="export.csv" value="false" />
			<display:setProperty name="export.rtf" value="false" />
			<display:setProperty name="export.pdf" value="false" />
			<display:setProperty name="export.excel.include_header" value="true" />
			<display:setProperty name="export.excel.filename" value="establiments.xls" />
			<display:setProperty name="export.decorated" value="true" />
		</display:table>
	</c:when>
	<c:when test="${not empty esDoControlador && not empty desferPendentAnalitica}">
		<display:table name="llistat" requestURI="" id="establiment" export="true" sort="list" cellpadding="0" cellspacing="0" class="listadoEstrecho selectable">
			<display:column titleKey="establiment.camp.nom" sortable="true" sortProperty="nom">
				<a href="ProcesInici.html?tipusProces=15&amp;establimentId=<c:out value="${establiment.id}"/>"><c:out value="${establiment.nom}" /></a>
			</display:column>
			<display:column titleKey="establiment.camp.cif" sortable="true" headerClass="ancho160 final">
				<c:out value="${establiment.cif}" />
			</display:column>
			<display:setProperty name="export.xml" value="false" />
			<display:setProperty name="export.csv" value="false" />
			<display:setProperty name="export.rtf" value="false" />
			<display:setProperty name="export.pdf" value="false" />
			<display:setProperty name="export.excel.include_header" value="true" />
			<display:setProperty name="export.excel.filename" value="establiments.xls" />
			<display:setProperty name="export.decorated" value="true" />
		</display:table>
	</c:when>
	<c:otherwise>
		<display:table name="llistat" requestURI="" id="establiment" export="true" sort="list" cellpadding="0" cellspacing="0" class="listadoAncho selectable">
			<fmt:parseNumber var="num" type="number" pattern="00" value="${establiment.codiRB}"/>
			<fmt:formatNumber var="numRB" type="number" value="${num}" pattern="00"/>
			<fmt:parseNumber var="num" type="number" pattern="00" value="${establiment.codiRC}"/>
			<fmt:formatNumber var="numRC" type="number" value="${num}" pattern="00"/>
			<fmt:parseNumber var="num" type="number" pattern="00" value="${establiment.codiRT}"/>
			<fmt:formatNumber var="numRT" type="number" value="${num}" pattern="00"/>
			<display:column titleKey="establiment.camp.codiRB" sortable="true" headerClass="ancho75" sortProperty="codiRB">
				<a href="EstablimentForm.html?id=<c:out value="${establiment.id}"/>"><c:out value="${numRB}" /></a>
			</display:column>
			<display:column titleKey="establiment.camp.codiRC" sortable="true" headerClass="ancho75">
				<c:out value="${numRC}" />
			</display:column>
			<display:column titleKey="establiment.camp.codiRT" sortable="true" headerClass="ancho75">
				<c:out value="${numRT}" />
			</display:column>
			<display:column titleKey="establiment.camp.nom" sortable="true">
				<c:out value="${establiment.nom}" />
			</display:column>
			<display:column titleKey="establiment.camp.tipusEstabliment" headerClass="ancho160" sortable="true">
				<fmt:message key="establiment.tipus.${establiment.tipusEstabliment.id}" />
			</display:column>
			<display:column titleKey="establiment.camp.cif" sortable="true" headerClass="ancho160">
				<c:out value="${establiment.cif}" />
			</display:column>
			<display:column titleKey="establiment.camp.actiu" sortable="true" headerClass="ancho75 final">
				<c:choose>
					<c:when test="${establiment.actiu}">
						<fmt:message key="manteniment.si" />
					</c:when>
					<c:otherwise>
						<fmt:message key="manteniment.no" />
					</c:otherwise>
				</c:choose>
			</display:column>
			<c:if test="${not empty eliminats}">
				<display:column titleKey="establiment.camp.eliminat" sortable="true" headerClass="ancho100 final">
					<c:choose>
						<c:when test="${not empty establiment.dataBaixa}">
							<fmt:message key="manteniment.si" />
						</c:when>
						<c:otherwise>
							<fmt:message key="manteniment.no" />
						</c:otherwise>
					</c:choose>
				</display:column>
			</c:if>
			<c:if test="${not empty esDoGestor}">
				<display:column titleKey="establiment.camp.resolucio" sortable="true" headerClass="ancho75 final">
					<c:choose>
						<c:when test="${empty establiment.dataBaixa}">
							<form id="formulario" action="<%=request.getContextPath()%>/GenerarPdf.html" method="post" class="ancho20">
								<input type="hidden" name="tipus" value="10" />
								<input type="hidden" name="idEstabliment" value="${establiment.id}" />
								<input type="submit" value="" class="exportPDFWidth16" alt="MO09" title="MO09" />
							</form>
						</c:when>
						<c:otherwise>
							<form id="formulario" action="<%=request.getContextPath()%>/GenerarPdf.html" method="post" class="ancho20">
								<input type="hidden" name="tipus" value="11" />
								<input type="hidden" name="idEstabliment" value="${establiment.id}" />
								<input type="submit" value="" class="exportPDFWidth16" alt="MO15" title="MO15" />
							</form>
						</c:otherwise>
					</c:choose>
				</display:column>
			</c:if>
			<display:setProperty name="export.xml" value="false" />
			<display:setProperty name="export.csv" value="false" />
			<display:setProperty name="export.rtf" value="false" />
			<display:setProperty name="export.pdf" value="false" />
			<display:setProperty name="export.excel.include_header" value="true" />
			<display:setProperty name="export.excel.filename" value="establiments.xls" />
			<display:setProperty name="export.decorated" value="true" />
		</display:table>
	</c:otherwise>
</c:choose></div>

<div class="separadorH"></div>

<br/>

<form name="formulario" action="EstablimentForm.html">

<c:if test="${empty vista && empty analitica && empty pendentAnalitica && empty desferPendentAnalitica}">
	<div class="btnCorto" onmouseover="underline('enlaceCrearForm')" onmouseout="underline('enlaceCrearForm')" onclick="document.formulario.submit();">
		<a id="enlaceCrearForm" href="javascript:void(0);"><fmt:message key="manteniment.crearnou" /></a>
	</div>
</c:if>

<c:if test="${not empty esDoGestor && empty eliminats}">
	<div class="btnLargo203" onmouseover="underline('enlaceMostrarEliminats')" onmouseout="underline('enlaceMostrarEliminats')" style="margin: 15px 0 0 0;">
		<a id="enlaceMostrarEliminats" href="Establiment.html?eliminats=true"><fmt:message key="manteniment.veureEstablimentsEliminats" /></a>
	</div>
</c:if>

</form>

</div>

<c:if test="${not empty llistat}">
	<!-- Colores en tablas -->
	<script type="text/javascript">
			$(document).ready(function(){
				setEstilosTabla();
			})
		</script>
</c:if>




</body>
</html>


















