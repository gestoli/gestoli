<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>

<html>
	<head>
		<title><fmt:message key="olivicultor.llistitol" /></title>
		<script type="text/javascript" src="js/displaytag.js"></script>
		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript" src="js/jquery.extend.js"></script>
		<%-- <link href="css/displaytag.css" rel="stylesheet" type="text/css"/> --%>
		<script type="text/javascript" language="javascript"><!--
		// <![CDATA[
			var $j = jQuery.noConflict();
			var seleccionar = true;
			function seleccionarTots() {
				idCheck = "olivicultorsSeleccionats_";
				var elements = document.getElementsByTagName('input');
				for(var i=0;i<elements.length;i++)
				{
				     var id = elements[i].id;
				     if((id.length >= idCheck.length)&&(id.substring(0,idCheck.length) == idCheck))
				     {
						var $pare = $j(elements[i]).parents("tr");
						$j(elements[i]).parents("tr").each(function(){
							if (!$(this).is(":hidden")) {
								var check = document.getElementById(id);
								check.checked = seleccionar
							}
						});
					}
				}
				seleccionar = !seleccionar;
			}

			function seleccionarTotsCheck(check) {
				if (check.checked = true){
					seleccionarTots()
				}
			}
		// ]]>
		</script>
	</head>

	<body>
	
	<form name="formulario" method="post" action="/gestoli/GenerarPdf.html" id="formulario" class="ancho888">
				<input type="hidden" value="13" name="tipus"/>
		<div id="listado"><%-- Tabla de resultados --%>
			
			<c:if test="${not empty llistat}">
				<span class="filtres">
					<fmt:message key="manteniment.filtra.resultats" />: <input type="text" id="filtre" value="" />
				</span>
			</c:if>
			
			<div class="separadorH"></div>
			
			<c:if test="${not empty llistat}">
				<%--div class="etiqueta <c:out value="${param.required}"/><c:if test="${not empty status.errorMessage}"> error</c:if>">
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="checkbox" />
						<c:param name="path" value="formData.totsOlivicultors" />
						<c:param name="title"><fmt:message key="accio.prefactura.camp.totsOlivicultorsPrefactura" /></c:param>
						<c:param name="camp" value="totsOlivicultors" />
					</c:import>
				</div--%>
				
				<div class="separadorH"></div>
				
				<div class="btnLargo203" 
					onmouseover="underline('enlaceSeleccionarTots')" 
					onmouseout="underline('enlaceSeleccionarTots')"
					onclick="seleccionarTots()">
					<a id="enlaceSeleccionarTots" href="javascript:void(0);"><fmt:message key="manteniment.seleccionar.tots.visibles"/></a>
				</div>
				<%--span class="filtres" style="display: inline;">
					<fmt:message key="manteniment.filtra.resultats" />: <input type="text" id="filtre" value="" />
				</span--%>
			</c:if>
			
			<div class="separadorH"></div>
			
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
				
				<display:table name="llistat" requestURI="" id="olivicultor" sort="list" cellpadding="0" cellspacing="0" class="listadoAncho selectable">
					<display:column titleKey="accio.tancamentLlibres.seleccionar" sortable="no" headerClass="ancho50">
						<div class="etiqueta <c:out value="${param.required}"/><c:if test="${not empty status.errorMessage}"> error</c:if>">
							<c:import url="comu/CampFormulari.jsp">
								<c:param name="tipus" value="checkbox" />
								<c:param name="path" value="formData.olivicultorsSeleccionats" />
								<c:param name="camp" value="olivicultorsSeleccionats_${olivicultor.id}" />
								<c:param name="value" value="${olivicultor.id}" />
							</c:import>
						</div>
					</display:column>
					<display:column titleKey="olivicultor.camp.codiDO" sortable="true" headerClass="ancho50" sortProperty="codiDO">
						<c:out value="${olivicultor.codiDO}" />
					</display:column>
					<display:column titleKey="olivicultor.camp.codiDOExperimental" sortable="true" headerClass="ancho50">
						<c:out value="${olivicultor.codiDOExperimental}" />
					</display:column>
					<display:column titleKey="olivicultor.camp.codiDOPOliva" sortable="true" headerClass="ancho50">
						<c:out value="${olivicultor.codiDOPOliva}" />
					</display:column>
					<display:column titleKey="olivicultor.camp.nombre" sortable="true" sortProperty="nom">
						<c:out value="${olivicultor.nom}" />
					</display:column>
				</display:table>
			</div>
			
			<div class="separadorH"></div>
			<br/>
			<div class="separadorH"></div>
			
			<div class="btnLargo203" onmouseover="underline('enlaceGenerarEtiquetas')"
					onmouseout="underline('enlaceGenerarEtiquetas')"
					onclick="document.formulario.submit();">
				<a id="enlaceGenerarEtiquetas" href="javascript:void(0);"><fmt:message key="manteniment.generar_etiqueta" /></a>
			</div>
		</div>
	</form>

		<c:if test="${not empty llistat}">
			<!-- Colores en tablas -->
			<script type="text/javascript">
				$(document).ready(function(){
					setEstilosTabla();
					$("#filtre").keyFilter("olivicultor");
				})
			</script>
		</c:if>
	</body>
</html>
