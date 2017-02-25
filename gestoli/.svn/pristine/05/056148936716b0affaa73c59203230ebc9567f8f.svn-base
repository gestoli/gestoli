<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>
<%@ page buffer="64kb"%>
<%@ page autoFlush="true"%>

<html>
<head>
<title><fmt:message key="partidaOli.llistitol" /></title>
<script type="text/javascript" src="js/displaytag.js"></script>
<%-- <link href="css/displaytag.css" rel="stylesheet" type="text/css"/> --%>
<script type="text/javascript" language="javascript">
	// <![CDATA[
	function mostrarActius(){
		mostrarTots = document.getElementById("mostrarTot");
		botoMostrar = document.getElementById("enlaceMostrarActius");
		var $rows = $("#partidaOli").children("tbody").find("tr");
		var show = false;
		if (mostrarTots.value == "true"){
			$rows.each(function(){
				$(this).show();
			});
		} else {
			var i = 0;
			$rows.each(function(){
				var $actiu = $(this).find("div");
				if ($actiu.attr("id") == "actiu"){
					show = true;
				} else {
					show = false;
				}
				if (show) { $rows[i].style.display = ""; }
				else { $rows[i].style.display = "none"; }
				i++;
			});
		}
		setSombraTabla();
		if (mostrarTots.value == "true"){
			mostrarTots.value = "false";
			botoMostrar.innerHTML = "<fmt:message key="manteniment.mostraractius"/>";
		} else {
			mostrarTots.value = "true";
			botoMostrar.innerHTML = "<fmt:message key="manteniment.mostrartots"/>";
		}
	}
	// ]]>
</script>
</head>
<body>

	<div id="listado"><%-- Tabla de resultados --%>
	
		<c:if test="${not empty llistat}">
			<span class="filtres">
				<fmt:message key="manteniment.filtra.resultats" />: <input type="text" id="filtre" value="" />
			</span>
		</c:if>
			
		<form name="formularioDesactivar" action="PartidaOli.html" class="ancho888">
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
			<display:table 	name="llistat" requestURI="" id="partidaOli" export="true" sort="list" cellpadding="0" cellspacing="0" class="listadoAncho">
				<display:column titleKey="partidaOli.camp.nom" headerClass="ancho160" sortable="true" sortProperty="nom">
					<a href="PartidaOliForm.html?id=<c:out value="${partidaOli.id}"/>"><c:out value="${partidaOli.nom}"/></a>
				</display:column>
				<display:column titleKey="partidaOli.camp.categoria" headerClass="ancho75" sortable="true">
					<c:out value="${partidaOli.categoriaOli.nom}"/>
				</display:column>
				<display:column titleKey="partidaOli.camp.actiu" sortable="true" headerClass="ancho50 final">
					<c:choose>
						<c:when test="${partidaOli.esActiu}">
							<fmt:message key="manteniment.si" />
							<div id="actiu"/>
						</c:when>
						<c:otherwise>
							<fmt:message key="manteniment.no" />
						</c:otherwise>
					</c:choose>
				</display:column>
				<display:column titleKey="partidaOli.camp.esVisualitza" sortable="true" headerClass="ancho50">
					<c:choose>
						<c:when test="${partidaOli.esVisualitza}">
							<fmt:message key="manteniment.si" />
						</c:when>
						<c:otherwise>
							<fmt:message key="manteniment.no" />
						</c:otherwise>
					</c:choose>
				</display:column>
				<display:column titleKey="partidaOli.camp.propietari" sortable="true" headerClass="ancho50">
					<c:choose>
						<c:when test="${partidaOli.olivicultorEsPropietari}">
							<fmt:message key="partidaOli.camp.propietari.olivicultor" />
						</c:when>
						<c:otherwise>
							<fmt:message key="partidaOli.camp.propietari.establiment" />
						</c:otherwise>
					</c:choose>
				</display:column>
				<display:column titleKey="partidaOli.camp.desactivar" sortable="true" headerClass="ancho50 final">
						<input id="partidaOliDesactivar_${partidaOli.id}" name="partidaOliDesactivar_${partidaOli.id}" value="" type="checkbox" />
				</display:column>
				<display:column titleKey="partidaOli.camp.ocultar" sortable="true" headerClass="ancho50 final">
						<input id="partidaOliOcultar_${partidaOli.id}" name="partidaOliOcultar_${partidaOli.id}" value="" type="checkbox" />
				</display:column>
				<display:setProperty name="export.xml" value="false" />
				<display:setProperty name="export.csv" value="false" />
				<display:setProperty name="export.rtf" value="false" />
				<display:setProperty name="export.pdf" value="false" />
				<display:setProperty name="export.excel.include_header" value="true" />
				<display:setProperty name="export.excel.filename" value="partidesOli.xls" />
				<display:setProperty name="export.decorated" value="true" />
			</display:table>
		</div>

		<div class="separadorH"></div>

		<input id="action" name="action" value="desactivar" type="hidden" />
		<input type="hidden" id="mostrarTot" name="mostrarTot" value="false"/>
		</form>
		<form name="formulario" action="PartidaOliForm.html"></form>

		<c:if test="${not empty esProductor || not empty esEnvasador}">
			<div class="btnCorto" onmouseover="underline('enlaceCrearForm')"
				onmouseout="underline('enlaceCrearForm')"
				onclick="document.formulario.submit();">
				<a id="enlaceCrearForm" href="javascript:void(0);"><fmt:message key="manteniment.crearnou" /></a>
			</div>
		</c:if>
		<c:if test="${not empty esProductor || not empty esEnvasador}">
			<div class="btnCorto" onmouseover="underline('enlaceDesactivarForm')"
				onmouseout="underline('enlaceDesactivarForm')"
				onclick="document.formularioDesactivar.submit();">
				<a id="enlaceDesactivarForm" href="javascript:void(0);"><fmt:message key="manteniment.modificar" /></a>
			</div>
		</c:if>
		<div class="btnCorto" onmouseover="underline('enlaceMostrarActius')"
			onmouseout="underline('enlaceMostrarActius')"
			onclick="mostrarActius();">
			<a id="enlaceMostrarActius" href="javascript:void(0);"><fmt:message key="manteniment.mostraractius" /></a>
		</div>
	</div>


		<c:if test="${not empty llistat}">
			<!-- Colores en tablas -->
			<script type="text/javascript">
				$(document).ready(function(){
					setEstilosTabla();
					$("#filtre").keyFilter("partidaOli");
				})
			</script>
		</c:if>
</body>
</html>
