<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>
<%@ page buffer="64kb"%>
<%@ page autoFlush="true"%>

<html>
	<head>
		<title><fmt:message key="modificar.document.sortida.llistitol" /></title>
		<script type="text/javascript" src="js/displaytag.js"></script>
	</head>

	<body>
		<div id="listado"><!-- Tabla de resultados -->
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
				
				<h3><fmt:message key="modificar.document.sortida.diposit"/></h3>
				
				<display:table name="diposits" requestURI="" id="diposit" export="true" sort="list" cellpadding="0" cellspacing="0" class="listadoAncho selectable">
					<display:column property="data" format="{0,date,dd/MM/yyyy}" titleKey="sortidaLot.camp.data" sortable="true" sortProperty="data" headerClass="ancho75" url="/ModificarDocumentSortidaForm.html?tipus=diposit" paramId="id" paramProperty="id"/>
					<display:column property="dipositBySdiCoddde.codiAssignat" titleKey="sortidaLot.camp.diposit" headerClass="ancho100" sortable="true"/>
					<display:column property="partidaOli.nom" titleKey="sortidaLot.camp.partidaOli" headerClass="ancho75" sortable="true"/>
					<display:column property="categoriaOli.nom" titleKey="sortidaLot.camp.categoriaOli" headerClass="ancho75" sortable="true"/>
					<display:column property="litres" titleKey="diposit.camp.litres" headerClass="ancho160" sortable="true"/>
					<display:column titleKey="sortidaLot.camp.destinatari" headerClass="ancho160 final" sortable="true">
						<c:if test="${not empty diposit.desti}"><c:out value="${diposit.desti}"/></c:if>
						<c:if test="${empty diposit.desti and not empty diposit.olivicultor}">
							<fmt:message key="pdf.llibres.existencies.retirat"/><c:out value="${diposit.olivicultor.nom}"/>
						</c:if>
					</display:column>
					<display:setProperty name="export.xml" value="false" />
					<display:setProperty name="export.csv" value="false" />
					<display:setProperty name="export.rtf" value="false" />
					<display:setProperty name="export.pdf" value="false" />
					<display:setProperty name="export.excel.include_header" value="true" />
					<display:setProperty name="export.excel.filename" value="diposits.xls" />
					<display:setProperty name="export.decorated" value="true" />
				</display:table>
				
				<div class="separadorH"></div>
				
				<br/><br/>
				
				<div class="separadorH"></div>
				
				<h3><fmt:message key="modificar.document.sortida.lot"/></h3>
				
				<display:table name="lots" requestURI="" id="lot" export="true" sort="list" cellpadding="0" cellspacing="0" class="listadoAncho selectable">
					<display:column property="vendaData" format="{0,date,dd/MM/yyyy}" titleKey="sortidaLot.camp.data" sortable="true" sortProperty="vendaData" headerClass="ancho75" url="/ModificarDocumentSortidaForm.html?tipus=lot" paramId="id" paramProperty="id"/>
					<display:column property="vendaMotiu" titleKey="sortidaLot.camp.motiu" headerClass="ancho75" sortable="true"/>
					<display:column property="lot.codiAssignat" titleKey="consulta.oliComercialitzat.camp.lot" headerClass="ancho100" sortable="true"/>
					<display:column property="lot.etiquetatge.marca.nom" titleKey="consulta.oliComercialitzat.camp.marca" headerClass="ancho100" sortable="true"/>
					<display:column property="lot.partidaOli.nom" titleKey="sortidaLot.camp.partidaOli" headerClass="ancho75" sortable="true"/>
					<display:column property="lot.partidaOli.categoriaOli.nom" titleKey="sortidaLot.camp.categoriaOli" headerClass="ancho75" sortable="true"/>
					<display:column property="vendaNumeroBotelles" titleKey="consulta.oliComercialitzat.camp.envases" headerClass="ancho75" sortable="true"/>
					<display:column titleKey="consulta.oliComercialitzat.camp.document" headerClass="ancho160" sortable="true">
						<c:out value="${lot.vendaTipusDocument}"/> - <c:out value="${lot.vendaNumeroDocument}"/>
					</display:column>
					<display:column property="vendaDestinatari" titleKey="sortidaLot.camp.destinatari" headerClass="ancho160 final" sortable="true">
						<c:if test="${not empty diposit.desti}"><c:out value="${diposit.desti}"/></c:if>
						<c:if test="${empty diposit.desti and not empty diposit.olivicultor}">
							<fmt:message key="pdf.llibres.existencies.retirat"/><c:out value="${diposit.olivicultor.nom}"/>
						</c:if>
					</display:column>
					<display:setProperty name="export.xml" value="false" />
					<display:setProperty name="export.csv" value="false" />
					<display:setProperty name="export.rtf" value="false" />
					<display:setProperty name="export.pdf" value="false" />
					<display:setProperty name="export.excel.include_header" value="true" />
					<display:setProperty name="export.excel.filename" value="lots.xls" />
					<display:setProperty name="export.decorated" value="true" />
				</display:table>
				
				<div class="separadorH"></div>
			</div>
			<div class="separadorH"></div>
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
