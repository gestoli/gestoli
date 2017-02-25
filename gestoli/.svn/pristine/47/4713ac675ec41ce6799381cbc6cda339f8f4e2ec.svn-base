<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>
<%@ page buffer="64kb"%>
<%@ page autoFlush="true"%>


<html>
<head>
<title><fmt:message key="olivicultor.llistitol" /></title>
<script type="text/javascript" src="js/displaytag.js"></script>
<%-- <link href="css/displaytag.css" rel="stylesheet" type="text/css"/> --%>
<script type="text/javascript">
	    function generarFactura(idOlivicultor) {
			
			path = "<%=request.getContextPath()%>/GenerarPdf.html";
			form = document.getElementById('formGenerarFactura');
			form.action = path;
			form.id.value = idOlivicultor;
			submitForm('formGenerarFactura');
		}
    
    </script>
</head>
<body>

<div id="listadoEstrecho"><!-- Tabla de resultados -->
<div class="layoutSombraTabla ancho"><c:if
	test="${not empty llistat}">
	<div class="rellenoInf"></div>
	<div class="rellenoIzq"></div>
	<div class="rellenoDer"></div>
	<div class="supDer"></div>
	<div class="supIzq"></div>
	<div class="infIzq"></div>
	<div class="infDer"></div>
</c:if> <display:table name="llistat" requestURI="" id="olivicultor" export="true" sort="list" cellpadding="0" cellspacing="0" class="listadoEstrecho selectable">
	<display:column titleKey="olivicultor.camp.codiDO" sortable="true" headerClass="ancho75" sortProperty="codiDO">
		<a href="javascript:generarFactura(<c:out value='${olivicultor.id}'/>);">
			<c:out value="${olivicultor.codiDO}" />
		</a>
	</display:column>
	<display:column titleKey="olivicultor.camp.codiDOExperimental" sortable="true" headerClass="ancho75">
		<c:out value="${olivicultor.codiDOExperimental}" />
	</display:column>
	<display:column titleKey="olivicultor.camp.codiDOPOliva" sortable="true" headerClass="ancho75" sortProperty="codiDOPOliva2">
						<c:out value="${olivicultor.codiDOPOliva}" />
	</display:column>
	<display:column titleKey="olivicultor.camp.nombre" sortable="true" headerClass="final" sortProperty="nom">
		<c:out value="${olivicultor.nom}" />
	</display:column>
	<display:setProperty name="export.xml" value="false" />
	<display:setProperty name="export.csv" value="false" />
	<display:setProperty name="export.rtf" value="false" />
	<display:setProperty name="export.pdf" value="false" />
	<display:setProperty name="export.excel.include_header" value="true" />
	<display:setProperty name="export.excel.filename" value="factura.xls" />
	<display:setProperty name="export.decorated" value="true" />
</display:table></div>

</div>

<div style="display: none">
<form id="formGenerarFactura" action="" method="post"><input
	type="hidden" name="tipus" id="tipus" value="4" /> <input
	type="hidden" name="id" id="id" value="" /></form>
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


















