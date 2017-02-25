<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>
<%@ page buffer="64kb"%>
<%@ page autoFlush="true"%>

<html>
	<head>
		<title><fmt:message key="fincas.llistitol" /></title>
		<script type="text/javascript" src="js/displaytag.js"></script>
		<%-- <link href="css/displaytag.css" rel="stylesheet" type="text/css"/> --%>
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
				
				<display:table name="llistat" requestURI="" id="historic" export="true" sort="list" cellpadding="0" cellspacing="0" class="listadoAncho selectable">
					<display:column titleKey="historic.establiment.nom" sortable="true" sortProperty="nom">
						<c:out value="${historic.nom}" />
					</display:column>
					<display:column titleKey="historic.establiment.cif" sortable="true">
						<c:out value="${historic.cif}" />
					</display:column>
					<display:column titleKey="historic.establiment.codiRB" sortable="true">
						<c:out value="${historic.codiRB}" />
					</display:column>
					<display:column titleKey="historic.establiment.codiRC" sortable="true">
						<c:out value="${historic.codiRC}" />
					</display:column>
					<display:column titleKey="historic.establiment.actiu" sortable="true">
						<c:choose>
							<c:when test="${historic.actiu}"><fmt:message key="manteniment.si" /></c:when>
							<c:otherwise><fmt:message key="manteniment.no" /></c:otherwise>
						</c:choose>
					</display:column>
					<display:column titleKey="historic.establiment.data.alta" sortable="true">
						<fmt:formatDate value="${historic.dataAlta}" pattern="dd/MM/yyyy"/>
					</display:column>
					<display:column titleKey="historic.establiment.data.baixa" sortable="true" headerClass="final">
						<fmt:formatDate value="${historic.dataBaixa}" pattern="dd/MM/yyyy"/>
					</display:column>
					<display:setProperty name="export.xml" value="false" />
				<display:setProperty name="export.csv" value="false" />
				<display:setProperty name="export.rtf" value="false" />
				<display:setProperty name="export.pdf" value="false" />
				<display:setProperty name="export.excel.include_header" value="true" />
				<display:setProperty name="export.excel.filename" value="HistoricEstabliments.xls" />
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
