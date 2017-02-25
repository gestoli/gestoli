<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>


<html>
<head>
<title><fmt:message key="consulta.etiquetes.titol" /></title>
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
</c:if> 

<display:table name="llistat" requestURI="" id="establiment"  sort="list" cellpadding="0" cellspacing="0" class="listadoEstrecho selectable">
	<display:column titleKey="establiment.camp.nom" sortable="true" sortProperty="nom">
		<a href="ConsultaEtiquetesLlistat.html?idEstabliment=<c:out value="${establiment.id}"/>&amp;fromEstabliment=true"><c:out value="${establiment.nom}" /></a>
	</display:column>
	<display:column titleKey="establiment.camp.cif" sortable="true" headerClass="ancho160 final">
		<c:out value="${establiment.cif}" />
	</display:column>
</display:table>
</div>

<div class="separadorH"></div>

<form name="formulario" action="ConsultaEtiquetesEstablimentLlistat.html" method="get">
	<div>
		<fieldset style="width:540px">
			<label for="cerca"><fmt:message key="manteniment.cerca.establiment.etiqueta"/></label>
			<div class="bordeInput">
				<div id="cercaBordeFoco" class="bordeFoco">
					<input type="text" id="cerca" name="cerca" class="inputText" value="${param.cerca}">
				</div>
			</div>
	    	<input type="submit" class="inputSubmit" value="<fmt:message key="manteniment.cercar"/>"/>
	    	<div class="separadorH"></div>
	    	<h4>${establimentNom}</h4>
	    </fieldset>
	</div>
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