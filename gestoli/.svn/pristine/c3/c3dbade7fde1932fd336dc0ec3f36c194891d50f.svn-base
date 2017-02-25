<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>


<c:if test="${not empty missatgesInfo}">
	<div class="contenedorMensaje">
	<div class="mensajeOk">
	<div class="capa1"></div>
	<div class="capa2"></div>
	<div class="capa3"></div>
	<div class="capa4"></div>
	<div class="capa5">
	<div class="capa6"><c:forEach var="info" items="${missatgesInfo}">
		<p><c:out value="${info}" escapeXml="false" /></p>
	</c:forEach></div>
	</div>
	</div>
	</div>
	<div class="separadorH"></div>
	<c:remove var="missatgesInfo" scope="session" />
</c:if>


<c:if test="${not empty missatgesError || not empty param.listError}">
	<div class="contenedorMensaje">
	<div class="mensajeKo">
	<div class="capa1"></div>
	<div class="capa2"></div>
	<div class="capa3"></div>
	<div class="capa4"></div>
	<div class="capa5">
	<div class="capa6"><c:choose>
		<c:when test="${not empty missatgesError}">
			<c:forEach var="error" items="${missatgesError}">
				<p><c:out value="${error}" escapeXml="false" /></p>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<p><c:out value="${param.listError}" escapeXml="false" /></p>
		</c:otherwise>
	</c:choose></div>
	</div>
	</div>
	</div>
	<div class="separadorH"></div>
	<c:remove var="missatgesError" scope="session" />
</c:if>

<c:if test="${not empty missatgesDocumentRendiment}">
	<div class="contenedorMensaje">
	<div class="mensajeOk">
	<div class="capa1"></div>
	<div class="capa2"></div>
	<div class="capa3"></div>
	<div class="capa4"></div>
	<div class="capa5">
	<div class="capa6">
		<p><fmt:message key="proces.elaboracioOli.missatge.crear.documentRendiment" />:</p>
		<div style="width: 100px; position: relative; margin: 0 auto;" >
		<form class="ancho20" method="post" action="GenerarPdf.html" id="formulario">
			<input type="hidden" value="8" name="tipus" />
			<input type="hidden" value="${missatgesDocumentRendiment[0]}" name="idElaboracio" />
			<input type="hidden" value="${missatgesDocumentRendiment[1]}" name="idEstabliment" />
			<input type="submit" class="exportPDFWidth32" value="" />
		</form>
		</div>
	</div>
	</div>
	</div>
	</div>
	<div class="separadorH"></div>
	<c:remove var="missatgesDocumentRendiment" scope="session" />
</c:if>

<c:if test="${not empty missatgesCartillaOlivicultor}">
	<div class="contenedorMensaje">
		<div class="mensajeOk">
			<div class="capa1"></div>
			<div class="capa2"></div>
			<div class="capa3"></div>
			<div class="capa4"></div>
			<div class="capa5">
			<div class="capa6">
				<p><fmt:message key="menu.consultas.cartilla_produccion" />:</p>
				<div style="width: 100px; position: relative; margin: 0 auto;">
					<form class="ancho20" method="post" action="GenerarPdf.html" id="formulario">
						<input type="hidden" value="${missatgesCartillaOlivicultor[0]}" name="tipus" />
						<input type="hidden" value="${missatgesCartillaOlivicultor[1]}" name="id" />
						<input type="submit" class="exportPDFWidth32" value="" />
					</form>
				</div>
			</div>
			</div>
		</div>
	</div>
	<div class="separadorH"></div>
	<c:remove var="missatgesCartillaOlivicultor" scope="session" />
</c:if>

<c:if test="${not empty missatgesVolantCirculacio}">
	<div class="contenedorMensaje">
	<div class="mensajeOk">
	<div class="capa1"></div>
	<div class="capa2"></div>
	<div class="capa3"></div>
	<div class="capa4"></div>
	<div class="capa5">
	<div class="capa6">
		<p><fmt:message key="consulta.movimentsEntreEstabliments.camp.volante" />:</p>
		<c:forEach var="volant" items="${missatgesVolantCirculacio[0]}">
			<div style="width: 100px; position: relative; margin: 0 auto;" >
			<form class="ancho20" method="post" action="GenerarPdf.html" id="formulario">
				<input type="hidden" value="14" name="tipus" />
				<input type="hidden" value="${volant}" name="trasllat" />
				<input type="hidden" value="${missatgesVolantCirculacio[1]}" name="sentit" />
				<input type="submit" class="exportPDFWidth32" value="" />
			</form>
			</div>
		</c:forEach>
	</div>
	</div>
	</div>
	</div>
	<div class="separadorH"></div>
	<c:remove var="missatgesVolantCirculacio" scope="session" />
</c:if>