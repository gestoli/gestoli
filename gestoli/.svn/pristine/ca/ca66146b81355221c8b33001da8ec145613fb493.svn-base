<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>
<%@ page buffer="64kb"%>
<%@ page autoFlush="true"%>

<html>
	<head>
		<title><fmt:message key="olivicultor.llistitol" /></title>
		<script type="text/javascript" src="js/displaytag.js"></script>
		<script type="text/javascript">
			function dadesOlivicultor(olivicultorId) {
				document.location ='ConsultaDadesOlivicultorLlistat.html?idOlivicultor='+idOlivicultor;
			}
		</script>
	</head>

	<body>
		<div id="listado"><%-- Tabla de resultados --%>
			<c:if test="${not empty llistat}">
				<span class="filtres">
					<fmt:message key="manteniment.filtra.resultats" />: <input type="text" id="filtre" value="" />
				</span>
				<div class="btnCorto" onmouseover="underline('bfiltre')" onmouseout="underline('bfiltre')" 
					style="left: 280px; position: relative; top: -50px;">
					<a id="bfiltre" href="javascript:void(0);"><fmt:message key="manteniment.filtra" /></a>
				</div>
			</c:if>
			
			<div class="separadorH"></div>
			
			<div class="layoutSombraTabla ancho">
				
				<div style="overflow-y: auto; width: 888px; margin-left: 30px;">
					<%--display:table name="llistat" requestURI="" id="olivicultor" sort="list" cellpadding="0" cellspacing="0" class="listadoAncho selectable" export="true"--%>
					<display:table name="llistat" requestURI="" id="olivicultor" sort="list" cellpadding="0" cellspacing="0" class="selectable" export="true" style="width: 1400px;">
						<fmt:parseNumber var="num" type="number" pattern="0000" value="${olivicultor.codiDO}"/>
						<fmt:formatNumber var="numRA" type="number" value="${num}" pattern="0000"/>
						<fmt:parseNumber var="num" type="number" pattern="0000" value="${olivicultor.codiDOExperimental}"/>
						<fmt:formatNumber var="numRE" type="number" value="${num}" pattern="0000"/>
						<fmt:parseNumber var="num" type="number" pattern="0000" value="${olivicultor.codiDOPOliva}"/>
						<fmt:formatNumber var="numRT" type="number" value="${num}" pattern="0000"/>
						<display:column titleKey="olivicultor.camp.usuari" sortable="true" sortProperty="nom" headerClass="ancho75">
							<a href="OlivicultorForm.html?id=<c:out value="${olivicultor.id}"/>"><c:out value="${olivicultor.usuari.usuari}"/></a>
						</display:column>
						<display:column titleKey="olivicultor.camp.codiDO" sortable="true" headerClass="ancho50">
							<c:out value="${numRA}"/>
						</display:column>
						<display:column titleKey="olivicultor.camp.codiDOExperimental" sortable="true" headerClass="ancho50">
							<c:out value="${numRE}"/>
						</display:column>
						<display:column titleKey="olivicultor.camp.codiDOPOliva" sortable="true" headerClass="ancho50">
							<c:out value="${numRT}"/>
						</display:column>
						<display:column titleKey="olivicultor.camp.nombre" sortable="true" sortProperty="nom" headerClass="ancho150">
							<c:out value="${olivicultor.nom}" />
						</display:column>
						<display:column titleKey="olivicultor.camp.nif" sortable="true" headerClass="ancho100">
							<c:out value="${olivicultor.nif}" />
						</display:column>
						<display:column titleKey="olivicultor.camp.adresa"  headerClass="ancho210">
							<c:out value="${olivicultor.direccio} - ${olivicultor.poblacio.nom}"/>
						</display:column>
						<display:column titleKey="olivicultor.camp.municipisExplotacio" sortable="true" headerClass="ancho120">
							<c:out value="${olivicultor.municipisExplotacio}" />
						</display:column>
						<display:column titleKey="olivicultor.camp.altaDO" sortable="true" headerClass="ancho50">
							<c:choose>
								<c:when test="${olivicultor.altaDO}"><fmt:message key="manteniment.si" /></c:when>
								<c:otherwise><fmt:message key="manteniment.no" /></c:otherwise>
							</c:choose>
						</display:column>
						<display:column titleKey="olivicultor.camp.cartilla" sortable="true" headerClass="ancho50">
							<c:choose>
								<c:when test="${olivicultor.cartilla}"><fmt:message key="manteniment.si" /></c:when>
								<c:otherwise><fmt:message key="manteniment.no" /></c:otherwise>
							</c:choose>
						</display:column>
						<display:column titleKey="olivicultor.camp.subvencionat" sortable="true" headerClass="ancho50">
							<c:choose>
								<c:when test="${olivicultor.subvencionat}"><fmt:message key="manteniment.si" /></c:when>
								<c:otherwise><fmt:message key="manteniment.no" /></c:otherwise>
							</c:choose>
						</display:column>
						<display:column titleKey="olivicultor.camp.uidRfid" sortable="true" headerClass="ancho50">
							<c:choose>
								<c:when test="${not empty olivicultor.uidRfid}"><fmt:message key="manteniment.si" /></c:when>
								<c:otherwise><fmt:message key="manteniment.no" /></c:otherwise>
							</c:choose>
						</display:column>
						<c:if test="${not empty eliminats}">
							<display:column titleKey="establiment.camp.eliminat" sortable="true" headerClass="ancho50">
								<c:choose>
									<c:when test="${not empty olivicultor.dataBaixa}">
										<fmt:message key="manteniment.si" />
									</c:when>
									<c:otherwise>
										<fmt:message key="manteniment.no" />
									</c:otherwise>
								</c:choose>
							</display:column>
						</c:if>
						<c:if test="${not empty esDoGestor}">
							<display:column titleKey="establiment.camp.resolucio" sortable="true" headerClass="ancho75">
								<c:choose>
									<c:when test="${empty olivicultor.dataBaixa and olivicultor.altaDO}">
										<form id="formulario" action="<%=request.getContextPath()%>/GenerarPdf.html" method="post" class="ancho20">
											<input type="hidden" name="tipus" value="9" />
											<input type="hidden" name="idOlivicultor" value="${olivicultor.id}" />
											<input type="submit" value="" class="exportPDFWidth16" alt="MO08" title="MO08" />
										</form>
									</c:when>
									<c:otherwise>
										<form id="formulario" action="<%=request.getContextPath()%>/GenerarPdf.html" method="post" class="ancho20">
											<input type="hidden" name="tipus" value="11" />
											<input type="hidden" name="idOlivicultor" value="${olivicultor.id}" />
											<input type="submit" value="" class="exportPDFWidth16" alt="MO15" title="MO15" />
										</form>
									</c:otherwise>
								</c:choose>
							</display:column>
						</c:if>
						<display:column titleKey="olivicultor.camp.dades" sortable="true" headerClass="ancho50 final">
							<a href="<%=request.getContextPath()%>/ConsultaDadesOlivicultorLlistat.html?idOlivicultor=<c:out value="${olivicultor.id}"/>&amp;fromEstabliment=true">
								<img class="iconoVer" />
							</a>
						</display:column>
						
						<display:setProperty name="export.xml" value="false" />
				   		<display:setProperty name="export.csv" value="false" />
				   		<display:setProperty name="export.rtf" value="false" />
				   		<display:setProperty name="export.pdf" value="false" />
				   		<display:setProperty name="export.excel.include_header" value="true" />
				   		<display:setProperty name="export.excel.filename" value="Olivicultors.xls" />
				   		<display:setProperty name="export.decorated" value="true" />
						
					</display:table>
				</div>
			</div>
			<div class="separadorH"></div>

			<form name="formulario" action="OlivicultorForm.html"></form>
			
			<c:if test="${not empty esDoGestor}">
				<div class="btnCorto" onmouseover="underline('enlaceCrearForm')"
						onmouseout="underline('enlaceCrearForm')"
						onclick="document.formulario.submit();">
					<a id="enlaceCrearForm" href="javascript:void(0);"><fmt:message key="manteniment.crearnou" /></a>
				</div>
			</c:if>
			
			<c:if test="${not empty esDoGestor && empty eliminats}">
				<div class="btnLargo203" onmouseover="underline('enlaceMostrarEliminats')" onmouseout="underline('enlaceMostrarEliminats')" style="margin: 15px 0 0 0;">
					<a id="enlaceMostrarEliminats" href="Olivicultor.html?eliminats=true"><fmt:message key="manteniment.veureOlivicultorsEliminats" /></a>
				</div>
			</c:if>
		</div>

		<c:if test="${not empty llistat}">
			<!-- Colores en tablas -->
			<script type="text/javascript">
				$(document).ready(function(){
					//$("#filtre").keyFilter("olivicultor");
					$("#bfiltre").click(function(){
						var $rows = $("#olivicultor").children("tbody").find("tr");
						var filter = $("#filtre").val().toLowerCase();
						$rows.each(function(){
							var show = false;
							$(this).find("td").each(function(){
								var text = $(this).text().trim().toLowerCase();
								if (text.indexOf(filter) >= 0) { show = true; }
							});
							if (show) { $(this).show(); }
							else { $(this).hide(); }
						});
						setSombraTabla();
					});
					setEstilosTabla();
				})
			</script>
		</c:if>
	</body>
</html>
				