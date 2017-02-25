<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>
<%@ page buffer="64kb"%>
<%@ page autoFlush="true"%>


<html>
<head>
<title><fmt:message key="informacio.llistitol" /></title>
<script type="text/javascript" src="js/displaytag.js"></script>
<%-- <link href="css/displaytag.css" rel="stylesheet" type="text/css"/> --%>
</head>
<body>

<div id="listadoAncho"><%-- Tabla de resultados --%>
<div class="layoutSombraTabla ancho"><c:if
	test="${not empty llistat}">
	<div class="rellenoInf"></div>
	<div class="rellenoIzq"></div>
	<div class="rellenoDer"></div>
	<div class="supDer"></div>
	<div class="supIzq"></div>
	<div class="infIzq"></div>
	<div class="infDer"></div>
</c:if> <display:table name="llistat" requestURI="" id="informacio" export="true" sort="list" cellpadding="0" cellspacing="0" class="listadoAncho selectable">
	<display:column titleKey="informacio.camp.data" sortable="true"
		headerClass="ancho100" sortProperty="data">
		<a
			href="GestionarInformacioForm.html?id=<c:out value="${informacio.id}"/>"><fmt:formatDate
			value="${informacio.data}" type="date" dateStyle="short"
			timeStyle="short" /></a>
	</display:column>
	<display:column titleKey="informacio.camp.titol" sortable="true">
		<c:out value="${informacio.titol}" escapeXml="false" />
	</display:column>
	<display:column titleKey="informacio.camp.categoria" sortable="true"
		headerClass="ancho210">
		<c:out value="${informacio.categoriaInformacio.nom}" escapeXml="false" />
	</display:column>
	<display:column titleKey="informacio.camp.documents" sortable="true"
		headerClass="ancho100 final">
		<c:choose>
			<c:when test="${informacio.teDocuments}">
				<fmt:message key="manteniment.si" />
			</c:when>
			<c:otherwise>
				<fmt:message key="manteniment.no" />
			</c:otherwise>
		</c:choose>
	</display:column>
	<display:setProperty name="export.xml" value="false" />
	<display:setProperty name="export.csv" value="false" />
	<display:setProperty name="export.rtf" value="false" />
	<display:setProperty name="export.pdf" value="false" />
	<display:setProperty name="export.excel.include_header" value="true" />
	<display:setProperty name="export.excel.filename" value="informacions.xls" />
	<display:setProperty name="export.decorated" value="true" />
</display:table></div>

<div class="separadorH"></div>

<form name="formulario" action="GestionarInformacioForm.html"></form>
<c:if
	test="${not empty esAdministracio || not empty esDoGestor || not empty esDoControlador}">
	<div class="btnCorto" onmouseover="underline('enlaceCrearForm')"
		onmouseout="underline('enlaceCrearForm')"
		onclick="document.formulario.submit();"><a id="enlaceCrearForm"
		href="javascript:void(0);"><fmt:message
		key="manteniment.crearnova" /></a></div>
</c:if>
</div>

<br /><br /><br /><br />

<div class="separadorH"></div>

<div id="listadoAncho2">
<div class="layoutSombraTabla" id="divSombra_1" style="width: 888px; height: 73px; ">
	<div class="rellenoInf"></div>
	<div class="rellenoIzq"></div>
	<div class="rellenoDer"></div>
	<div class="supDer"></div>
	<div class="supIzq"></div>
	<div class="infIzq"></div>
	<div class="infDer"></div>
	<table cellpadding="0" class="listadoAncho selectable" cellspacing="0" id="document">
	<thead>
		<tr>
			<th class="sortable">
				<fmt:message key="document.camp.titol" />
			</th>
			<th class="ancho100 sortable">
				<fmt:message key="document.camp.tamany" />
			</th>
			<th class="ancho32 final">
				<fmt:message key="document.ver"/>
			</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${not empty esProductor || not empty esEnvasador || not empty esTafona}">
			<tr class="odd alt">
				<td>GEST-OLI - Manual d'usuari - Rol Tafona-Envasador</td>
				<td>9,7 Mb</td>
				<td style="background-image: none; " class="ancho32 final">
					<a href="http://gestoli.cat/descarregues/presentacions/GEST-OLI%20-%20Manual%20d'usuari%20-%20Rol%20Tafona-Envasador.pdf" onclick="window.open(this.href); return false;" style="text-decoration: none; ">
						<div class="iconoVer" title="<fmt:message key="document.ver"/>"></div>
					</a>
				</td>
			</tr>
		</c:if>
		<c:if test="${not empty esAdministracio}">
			<tr class="even">
				<td>GEST-OLI - Manual d'usuari - Rol Administració</td>
				<td>5,0 Mb</td>
				<td style="background-image: none; " class="ancho32 final">
					<a href="http://gestoli.cat/descarregues/presentacions/GEST-OLI%20-%20Manual%20d'usuari%20-%20Rol%20Administracio.pdf" onclick="window.open(this.href); return false;" style="text-decoration: none; ">
						<div class="iconoVer" title="<fmt:message key="document.ver"/>"></div>
					</a>
				</td>
			</tr>
		</c:if>
		<c:if test="${not empty esDoGestor}">
			<tr class="odd alt">
				<td>GEST-OLI - Manual d'usuari - Rol Gestor</td>
				<td>8,4 Mb</td>
				<td style="background-image: none; " class="ancho32 final">
					<a href="http://gestoli.cat/descarregues/presentacions/GEST-OLI%20-%20Manual%20d'usuari%20-%20Rol%20Gestor.pdf" onclick="window.open(this.href); return false;" style="text-decoration: none; ">
						<div class="iconoVer" title="<fmt:message key="document.ver"/>"></div>
					</a>
				</td>
			</tr>
		</c:if>
		<c:if test="${not empty esDoControlador}">
			<tr class="even">
				<td>GEST-OLI - Manual d'usuari - Rol Controlador</td>
				<td>6,1 Mb</td>
				<td style="background-image: none; " class="ancho32 final">
					<a href="http://gestoli.cat/descarregues/presentacions/GEST-OLI%20-%20Manual%20d'usuari%20-%20Rol%20Controlador.pdf" onclick="window.open(this.href); return false;" style="text-decoration: none; ">
						<div class="iconoVer" title="<fmt:message key="document.ver"/>"></div>
					</a>
				</td>
			</tr>
		</c:if>
		<c:if test="${not empty esOlivicultor}">
			<tr class="odd alt">
				<td>GEST-OLI - Manual d'usuari - Rol Olivicultor</td>
				<td>1,4 Mb</td>
				<td style="background-image: none; " class="ancho32 final">
					<a href="http://gestoli.cat/descarregues/presentacions/GEST-OLI%20-%20Manual%20d'usuari%20-%20Rol%20Olivicultor.pdf" onclick="window.open(this.href); return false;" style="text-decoration: none; ">
						<div class="iconoVer" title="<fmt:message key="document.ver"/>"></div>
					</a>
				</td>
			</tr>
		</c:if>
	</tbody>
	</table>
</div>
</div>


<%-- Colores en tablas --%>
<script type="text/javascript">
	$(document).ready(function(){
		setEstilosTabla();
	})
</script>


</body>
</html>