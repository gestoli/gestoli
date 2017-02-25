<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>


<script type="text/javascript" src="js/displaytag.js"></script>

<c:if test="${not empty diposits}">
	<div class="depositos">
	<h1><fmt:message key="establiment.vista.listadoDepositos" /></h1>
	<div id="listadoAncho"><%-- Tabla de resultados --%>
	<div class="layoutSombraTabla ancho">
	<div class="rellenoInf"></div>
	<div class="rellenoIzq"></div>
	<div class="rellenoDer"></div>
	<div class="supDer"></div>
	<div class="supIzq"></div>
	<div class="infIzq"></div>
	<div class="infDer"></div>


	<c:if test='${empty esProductor && empty esEnvasador}'>
		<c:set var="claseNoEnlace">noEnlace</c:set>
	</c:if> <display:table name="diposits" requestURI="" id="diposit" 
		sort="list" cellpadding="0" cellspacing="0"
		class="listadoAncho selectable ${claseNoEnlace}">

		<c:if test="${not empty procsel}">
			<display:column title="" sortable="false" headerClass="ancho32">
				<c:set var="selected" value="${false}" />
				<c:choose>
					<c:when test="${procsel.accio == 'ORIGEN'}">
						<c:forEach var="sel" items="${seleccioOrigen}">
							<c:if test="${sel==diposit.id}">
								<c:set var="selected" value="${true}" />
							</c:if>
						</c:forEach>
					</c:when>
					<c:when test="${procsel.accio == 'DESTI'}">
						<c:forEach var="sel" items="${seleccioDesti}">
							<c:if test="${sel==diposit.id}">
								<c:set var="selected" value="${true}" />
							</c:if>
						</c:forEach>
					</c:when>
				</c:choose>
				<form Class="ancho32" action=""><input type="checkbox"
					class="inputCheckbox" value="<c:out value="${diposit.id}"/>"
					<c:if test="${selected}"> checked="checked"</c:if>
					onclick="canviSeleccio(this.value,'<c:out value="${accio}"/>')"></form>
			</display:column>
		</c:if>

		<c:choose>
			<c:when
				test="${not empty esDoGestor || not empty esAdministracio || not empty esDoControlador || not empty procsel}">
				<display:column titleKey="diposit.camp.codi" headerClass="ancho75"
					sortable="true" sortProperty="codiAssignat">
					<c:out value="${diposit.codiAssignat}" />
				</display:column>
			</c:when>
			<c:when test="${not empty esProductor || not empty esEnvasador}">
				<display:column titleKey="diposit.camp.codi" headerClass="ancho75"
					sortable="true" sortProperty="codiAssignat">
					<a href="DipositForm.html?id=<c:out value="${diposit.id}"/>"><c:out
						value="${diposit.codiAssignat}" /></a>
				</display:column>
			</c:when>
		</c:choose>

		<display:column titleKey="diposit.camp.capacitat" headerClass="ancho100" sortable="true">
			<fmt:formatNumber value="${diposit.capacitat}" maxFractionDigits="2" />
		</display:column>
		<display:column titleKey="diposit.camp.contenido" headerClass="ancho75" sortable="true">
			<c:choose>
				<c:when test="${not empty diposit.volumActual}">
					<fmt:formatNumber value="${diposit.volumActual}" maxFractionDigits="2" />
				</c:when>
				<c:otherwise>
					<fmt:formatNumber value="0" maxFractionDigits="2" />
				</c:otherwise>
			</c:choose>
		</display:column>
		<display:column titleKey="diposit.camp.categoria" headerClass="ancho100" sortable="true">
			<c:if test="${not empty diposit.partidaOli.categoriaOli && not empty diposit.partidaOli.categoriaOli.id}">
				<fmt:message key="consulta.general.camp.categoriaOli.${diposit.partidaOli.categoriaOli.id}" />
			</c:if>
		</display:column>
		<display:column titleKey="diposit.camp.material" sortable="true">
			<c:out value="${diposit.materialDiposit.nom}" />
		</display:column>
		<display:column titleKey="diposit.camp.precintat" sortable="true" headerClass="ancho75">
			<c:choose>
				<c:when test="${diposit.precintat}">
					<fmt:message key="manteniment.si" />
				</c:when>
				<c:otherwise>
					<fmt:message key="manteniment.no" />
				</c:otherwise>
			</c:choose>
		</display:column>
		<display:column titleKey="diposit.camp.encami" sortable="true" headerClass="ancho75">
			<c:choose>
				<c:when test="${diposit.enCami}">
					<fmt:message key="manteniment.si" />
				</c:when>
				<c:otherwise>
					<fmt:message key="manteniment.no" />
				</c:otherwise>
			</c:choose>
		</display:column>
		<display:column titleKey="diposit.camp.actiu" sortable="true" headerClass="ancho75 final">
			<c:choose>
				<c:when test="${diposit.actiu}">
					<fmt:message key="manteniment.si" />
				</c:when>
				<c:otherwise>
					<fmt:message key="manteniment.no" />
				</c:otherwise>
			</c:choose>
		</display:column>
	</display:table></div>

	</div>
	</div>
</c:if>

<c:if test="${not empty partides}">
	<div class="partidas">
	<h1 class="tituloListado"><fmt:message
		key="establiment.vista.listadoPartidas" /></h1>
	<div id="listadoAncho"><%-- Tabla de resultados --%>
	<div class="layoutSombraTabla ancho">
	<div class="rellenoInf"></div>
	<div class="rellenoIzq"></div>
	<div class="rellenoDer"></div>
	<div class="supDer"></div>
	<div class="supIzq"></div>
	<div class="infIzq"></div>
	<div class="infDer"></div>

	<display:table name="partides" requestURI="" id="partida" 
		sort="list" cellpadding="0" cellspacing="0"
		class="listado665 selectable noEnlace">
		<c:if test="${not empty procsel}">
			<display:column title="" sortable="false" headerClass="ancho32">
				<c:set var="selected" value="${false}" />
				<c:choose>
					<c:when test="${procsel.accio == 'ORIGEN'}">
						<c:forEach var="sel" items="${seleccioOrigen}">
							<c:if test="${sel==partida.id}">
								<c:set var="selected" value="${true}" />
							</c:if>
						</c:forEach>
					</c:when>
					<c:when test="${procsel.accio == 'DESTI'}">
						<c:forEach var="sel" items="${seleccioDesti}">
							<c:if test="${sel==partida.id}">
								<c:set var="selected" value="${true}" />
							</c:if>
						</c:forEach>
					</c:when>
				</c:choose>
				<form class="ancho32" action=""><input type="checkbox"
					class="inputCheckbox" value="<c:out value="${partida.id}"/>"
					<c:if test="${selected}"> checked="checked"</c:if>
					onclick="canviSeleccio(this.value,'<c:out value="${accio}"/>')"></form>
			</display:column>
		</c:if>

		<display:column titleKey="establiment.vista.listadoPartidas.data"
			headerClass="ancho75" sortable="true" sortProperty="data">
			<c:out value="${partida.dataFormat}" />
		</display:column>
		<display:column titleKey="establiment.vista.listadoPartidas.hora"
			headerClass="ancho50" sortable="true">
			<c:out value="${partida.hora}" />
		</display:column>
		<display:column titleKey="establiment.vista.listadoPartidas.olivicultor"
			headerClass="ancho175" sortable="true">
			<c:out value="${partida.olivicultor.nom}" />
		</display:column>
		<display:column titleKey="establiment.vista.listadoPartidas.cantidad"
			headerClass="ancho100" sortable="true">
			<c:out value="${partida.totalQuilos}" />
		</display:column>
		<display:column titleKey="establiment.vista.listadoPartidas.variedad"
			sortable="true">
			<c:choose>
				<c:when test="${partida.nomVarietat eq 'mezcla'}">
					<fmt:message key="establiment.vista.listadoPartidas.mezcla" />
				</c:when>
				<c:otherwise>
					<c:out value="${partida.nomVarietat}" />
				</c:otherwise>
			</c:choose>
		</display:column>
		<display:column titleKey="establiment.vista.listadoPartidas.sana"
			sortable="true" headerClass="ancho50 final">
			<c:choose>
				<c:when test="${partida.sana}">
					<fmt:message key="manteniment.si" />
				</c:when>
				<c:otherwise>
					<fmt:message key="manteniment.no" />
				</c:otherwise>
			</c:choose>
		</display:column>
	</display:table></div>

	</div>
	</div>
</c:if>

<c:if test="${not empty lotes}">
	<div class="lotes">
	<h1 class="tituloListado"><fmt:message
		key="establiment.vista.listadoLotes" /></h1>
	<div id="listadoAncho"><%-- Tabla de resultados --%>
	<div class="layoutSombraTabla ancho">
	<div class="rellenoInf"></div>
	<div class="rellenoIzq"></div>
	<div class="rellenoDer"></div>
	<div class="supDer"></div>
	<div class="supIzq"></div>
	<div class="infIzq"></div>
	<div class="infDer"></div>

	<display:table name="lotes" requestURI="" id="lote" 
		sort="list" cellpadding="0" cellspacing="0"
		class="listado665 selectable noEnlace">

		<c:if test="${not empty procsel}">
			<display:column title="" sortable="false" headerClass="ancho32">
				<c:set var="selected" value="${false}" />
				<c:choose>
					<c:when test="${procsel.accio == 'ORIGEN'}">
						<c:forEach var="sel" items="${seleccioOrigen}">
							<c:if test="${sel==lote.id}">
								<c:set var="selected" value="${true}" />
							</c:if>
						</c:forEach>
					</c:when>
					<c:when test="${procsel.accio == 'DESTI'}">
						<c:forEach var="sel" items="${seleccioDesti}">
							<c:if test="${sel==lote.id}">
								<c:set var="selected" value="${true}" />
							</c:if>
						</c:forEach>
					</c:when>
				</c:choose>
				<form class="ancho32" action=""><input type="checkbox"
					class="inputCheckbox" value="<c:out value="${lote.id}"/>"
					<c:if test="${selected}"> checked="checked"</c:if>
					onclick="canviSeleccio(this.value,'<c:out value="${accio}"/>')"></form>
			</display:column>
		</c:if>
		<display:column titleKey="establiment.vista.listadoLotes.data"
			headerClass="ancho75" sortable="true" sortProperty="data">
			<c:out value="${lote.dataFormat}" />
		</display:column>
		<display:column titleKey="establiment.vista.listadoLotes.codigo"
			headerClass="ancho100" sortable="true">
			<c:out value="${lote.codiAssignat}" />
		</display:column>
		<display:column titleKey="establiment.vista.listadoLotes.acidesa"
			sortable="true" headerClass="ancho75">
			<fmt:formatNumber value="${lote.acidesa}" maxFractionDigits="2" />
		</display:column>
		<display:column
			titleKey="establiment.vista.listadoLotes.litresEnvassats"
			sortable="true">
			<fmt:formatNumber value="${lote.litresEnvassats}"
				maxFractionDigits="2" />
		</display:column>
		<display:column
			titleKey="establiment.vista.listadoLotes.capacitat"
			sortable="true"  headerClass="ancho75">
			<fmt:formatNumber value="${lote.etiquetatge.tipusEnvas.volum}" 
				maxFractionDigits ="2" /> l.
		</display:column>
		<display:column
			titleKey="establiment.vista.listadoLotes.numeroBotellesActuals"
			sortable="true" headerClass="ancho100 final">
			<c:out value="${lote.numeroBotellesActuals}" />
		</display:column>
	</display:table></div>

	</div>
	</div>
</c:if>


<c:if
	test="${not empty diposits || not empty partides || not empty lotes}">
	<!-- Colores en tablas -->
	<script type="text/javascript">
		$(document).ready(function(){
			setEstilosTabla();
		})
	</script>
</c:if>

