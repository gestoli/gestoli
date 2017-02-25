<%@page import="es.caib.gestoli.logic.model.Diposit"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>
<%@ page import="es.caib.gestoli.front.util.*"%>
<%@ page import="java.util.ResourceBundle"%>
<%@ page import="java.util.Locale"%>
<%
	String lang = Idioma.getLang(request);
	request.setAttribute("lang",lang);
%>

<html>
<head>
<title><fmt:message key="manteniment.tareasPendientes.titol" /></title>

<script type="text/javascript" src="js/calendar/calendar.js"></script>
<script type="text/javascript" src="js/calendar/calendar-setup.js"></script>
<%
		if(lang.equalsIgnoreCase("ca")){
	%>
<script type="text/javascript" src="js/calendar/lang/calendar-ca.js"></script>
<script type="text/javascript" src="js/calendar/lang/calendar-es.js"></script>
<%		
		}else{
	%>
<script type="text/javascript" src="js/calendar/lang/calendar-es.js"></script>
<%
		}
	%>
<link href="js/calendar/calendar-viti.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="js/form.js"></script>

<script type="text/javascript">
	function parseData_camp_data(data){
		var str = data.value.split("/");

		if(str[2] != null){
			if(str[2].length == 2){
				data.value = str[0] + "/" + str[1] + "/" + "20" + str[2];
			}
			if(str[2].length == 3){
				data.value = str[0] + "/" + str[1] + "/" + "2" + str[2];
			}
		}
	}
</script>

</head>
<body>


	<div class="pendientesAceptar">
	<h1><fmt:message key="manteniment.tareasPendientes.trasladosRecibir" /></h1>
<c:if test="${(llistatPendientesAceptar != null && not empty llistatPendientesAceptar)}">
	
	<div class="listado"><%-- Tabla de resultados --%>
	<div class="layoutSombraTabla ancho">
	<div class="rellenoInf"></div>
	<div class="rellenoIzq"></div>
	<div class="rellenoDer"></div>
	<div class="supDer"></div>
	<div class="supIzq"></div>
	<div class="infIzq"></div>
	<div class="infDer"></div>

	<display:table name="llistatPendientesAceptar" requestURI="" id="trasllatAceptar" sort="list" cellpadding="0" cellspacing="0" class="listado665 noEnlace">
		<%
			es.caib.gestoli.logic.model.Trasllat trasllat = (es.caib.gestoli.logic.model.Trasllat)pageContext.getAttribute("trasllatAceptar");
		  	
			java.util.Collection diposits = trasllat.getDiposits();
			java.util.Iterator it = diposits.iterator();
			java.lang.String nom = "";
			java.lang.Double quantitat = new java.lang.Double(0.0);
			java.lang.String partida = "";
			java.lang.String categoria = "";
			
			if (it.hasNext()){
				es.caib.gestoli.logic.model.Diposit diposit = (es.caib.gestoli.logic.model.Diposit)it.next();
				nom = diposit.getCodiAssignat();
				if (trasllat.getEsDiposit().booleanValue()){
					quantitat = diposit.getVolumActual();
				} else {
					quantitat = diposit.getVolumTrasllat();
				}
				es.caib.gestoli.logic.model.PartidaOli partidaOli = diposit.getPartidaOli();
				if (partidaOli != null){
					partida = partidaOli.getNom();
					categoria = partidaOli.getCategoriaOli().getNom();
				}
			}
			
			pageContext.setAttribute("nom", nom);
			pageContext.setAttribute("quantitat", quantitat);
			pageContext.setAttribute("partida", partida);
			pageContext.setAttribute("categoria", categoria);
		%>
		<display:column titleKey="manteniment.tareasPendientes.origen" sortable="true" sortProperty="establimentByTdiCodeor.nom">
			<c:out value="${trasllatAceptar.establimentByTdiCodeor.nom}" />
		</display:column>
		<display:column titleKey="manteniment.tareasPendientes.data" sortable="true" sortProperty="data" headerClass="ancho75">
			<c:out value="${trasllatAceptar.dataFormat}" />
		</display:column>
		<display:column titleKey="consulta.oliEntradaTraslado.camp.volante" sortable="true" headerClass="ancho50">
			<c:out value="${trasllatAceptar.id}" />
		</display:column>
		<display:column titleKey="manteniment.tipus" sortable="true" headerClass="ancho75">
			<c:choose>
			<c:when test="${trasllatAceptar.esDiposit == true}">
				<fmt:message key="manteniment.diposit" />
			</c:when>
			<c:otherwise>
				<fmt:message key="manteniment.oligranel" />
			</c:otherwise>
			</c:choose>
		</display:column>
		<display:column titleKey="manteniment.nom" sortable="true" headerClass="ancho100">
			<c:out value="${nom}" />
		</display:column>
		<display:column titleKey="manteniment.litres" sortable="true" headerClass="ancho75">
			<c:out value="${quantitat}" />
		</display:column>
		<display:column titleKey="manteniment.partida" sortable="true" headerClass="ancho100">
			<c:out value="${partida}" />
		</display:column>
		<display:column titleKey="manteniment.categoria" sortable="true" headerClass="ancho75">
			<c:out value="${categoria}" />
		</display:column>
		<display:column headerClass="ancho175 final">
			<c:choose>
				<c:when test="${trasllatAceptar.esDiposit == true}">
					<form name="form_acceptar" id="form_acceptar${trasllatAceptar_rowNum}" action="TrasllatLlistat.html" method="get" class="extended seguit" style="width:150px;">
						<!-- a class="tarea_aceptar" href="TrasllatLlistat.html?esDiposit=t&amp;tipus=recibir&amp;aceptar=t&amp;trasllatId=<c:out value='${trasllatAceptar.id}'/>"><span><fmt:message key="manteniment.tareasPendientes.aceptar" /></span></a-->
						
						<!-- CALENDARI -->
						<div class="bordeInput" style="visibility: hidden">
							<div id="camp_dataBordeFoco" class="bordeFoco">
								<input type="text"
									id="camp_data_${trasllatAceptar_rowNum}"
									name="data"
									class="inputData"
									onblur="parseData_camp_data(this);"
									maxlength="10"  value="${trasllatAceptar.dataFormat}"/> 
							</div>
						</div>
						
						<input type="hidden" name="esDiposit" value="t" />
						<input type="hidden" name="tipus" value="recibir" />
						<input type="hidden" name="aceptar" value="t" />
						<input type="hidden" name="trasllatId" value="${trasllatAceptar.id}" />
						<a class="tarea_aceptar" onclick="document.getElementById('form_acceptar${trasllatAceptar_rowNum}').submit();"><span><fmt:message key="manteniment.tareasPendientes.aceptar" /></span></a>
					</form>
					<br/>
						<a class="tarea_rechazar" href="TrasllatLlistat.html?esDiposit=t&amp;tipus=recibir&amp;aceptar=f&amp;trasllatId=<c:out value='${trasllatAceptar.id}'/>"><span><fmt:message key="manteniment.tareasPendientes.rechazar" /></span></a>
				</c:when>
				<c:otherwise>
					<a class="tarea_aceptar" href="ProcesInici.html?tipusProces=13&amp;trasllatId=<c:out value='${trasllatAceptar.id}'/>"><span><fmt:message key="manteniment.tareasPendientes.aceptar" /></span></a>
					<a class="tarea_rechazar" href="TrasllatLlistat.html?esDiposit=f&amp;tipus=recibir&amp;aceptar=f&amp;trasllatId=<c:out value='${trasllatAceptar.id}'/>"><span><fmt:message key="manteniment.tareasPendientes.rechazar" /></span></a>
				</c:otherwise>
			</c:choose>
		</display:column>
	</display:table>
	</div>
	</div>
</c:if>
	</div>


	<div class="pendientesDevolver">
	<h1 class="tituloListado"><fmt:message key="manteniment.tareasPendientes.trasladosDevolver" /></h1>
<c:if test="${(llistatPendientesDevolver != null && not empty llistatPendientesDevolver)}">
	<div class="listado"><%-- Tabla de resultados --%>
	<div class="layoutSombraTabla ancho">
	<div class="rellenoInf"></div>
	<div class="rellenoIzq"></div>
	<div class="rellenoDer"></div>
	<div class="supDer"></div>
	<div class="supIzq"></div>
	<div class="infIzq"></div>
	<div class="infDer"></div>

	<display:table name="llistatPendientesDevolver" requestURI="" id="trasllatDevolver" sort="list" cellpadding="0" cellspacing="0" class="listado665 noEnlace">
		<%
			es.caib.gestoli.logic.model.Trasllat trasllat = (es.caib.gestoli.logic.model.Trasllat)pageContext.getAttribute("trasllatDevolver");
		  	
			java.util.Collection diposits = trasllat.getDiposits();
			java.util.Iterator it = diposits.iterator();
			java.lang.String nom = "";
			java.lang.Double quantitat = new java.lang.Double(0.0);
			java.lang.String partida = "";
			java.lang.String categoria = "";
			
			if (it.hasNext()){
				es.caib.gestoli.logic.model.Diposit diposit = (es.caib.gestoli.logic.model.Diposit)it.next();
				nom = diposit.getCodiAssignat();
				if (trasllat.getEsDiposit().booleanValue()){
					quantitat = diposit.getVolumActual();
				} else {
					quantitat = diposit.getVolumTrasllat();
				}
				es.caib.gestoli.logic.model.PartidaOli partidaOli = diposit.getPartidaOli();
				if (partidaOli != null){
					partida = partidaOli.getNom();
					categoria = partidaOli.getCategoriaOli().getNom();
				}
			}
			
			pageContext.setAttribute("nom", nom);
			pageContext.setAttribute("quantitat", quantitat);
			pageContext.setAttribute("partida", partida);
			pageContext.setAttribute("categoria", categoria);
		%>
		<display:column titleKey="manteniment.tareasPendientes.destino" sortable="true" sortProperty="establimentByTdiCodeor.nom" style="background-color: #FFFF99;">
			<c:out value="${trasllatDevolver.establimentByTdiCodeor.nom}" />
		</display:column>
		<display:column titleKey="consulta.oliEntradaTraslado.camp.volante" sortable="true" headerClass="ancho50" style="background-color: #FFFF99;">
			<c:out value="${trasllatDevolver.id}" />
		</display:column>
		<display:column titleKey="manteniment.tipus" sortable="true" headerClass="ancho75" style="background-color: #FFFF99;">
			<c:choose>
			<c:when test="${trasllatDevolver.esDiposit == true}">
				<fmt:message key="manteniment.diposit" />
			</c:when>
			<c:otherwise>
				<fmt:message key="manteniment.oligranel" />
			</c:otherwise>
			</c:choose>
		</display:column>
		<display:column titleKey="manteniment.nom" sortable="true" headerClass="ancho100" style="background-color: #FFFF99;">
			<c:out value="${nom}" />
		</display:column>
		<display:column titleKey="manteniment.litres" sortable="true" headerClass="ancho75" style="background-color: #FFFF99;">
			<c:out value="${quantitat}" />
		</display:column>
		<display:column titleKey="manteniment.partida" sortable="true" headerClass="ancho100" style="background-color: #FFFF99;">
			<c:out value="${partida}" />
		</display:column>
		<display:column titleKey="manteniment.categoria" sortable="true" headerClass="ancho75" style="background-color: #FFFF99;">
			<c:out value="${categoria}" />
		</display:column>
		<display:column headerClass="ancho100 final" style="background-color: #FFFF99;">
			<c:choose>
			<c:when test="${trasllatDevolver.esDiposit == true}">
				<a class="tarea_aceptar" href="TrasllatLlistat.html?esDiposit=t&amp;tipus=devolver&amp;aceptar=t&amp;trasllatId=<c:out value='${trasllatDevolver.id}'/>"><span><fmt:message key="manteniment.tareasPendientes.aceptar" /></span></a>
			</c:when>
			<c:otherwise>
				<a class="tarea_aceptar" href="TrasllatLlistat.html?esDiposit=f&amp;tipus=devolver&amp;aceptar=t&amp;trasllatId=<c:out value='${trasllatDevolver.id}'/>"><span><fmt:message key="manteniment.tareasPendientes.aceptar" /></span></a>
			</c:otherwise>
			</c:choose>
		</display:column>
	</display:table>
	</div>
	</div>
</c:if>
	</div>

	<div class="pendientesDevolver">
	<h1 class="tituloListado"><fmt:message key="manteniment.tareasPendientes.trasladosAceptarDevolver" /></h1>
<c:if test="${(llistatPendientesAceptarDevolver != null && not empty llistatPendientesAceptarDevolver)}">
	<div class="listado"><%-- Tabla de resultados --%>
	<div class="layoutSombraTabla ancho">
	<div class="rellenoInf"></div>
	<div class="rellenoIzq"></div>
	<div class="rellenoDer"></div>
	<div class="supDer"></div>
	<div class="supIzq"></div>
	<div class="infIzq"></div>
	<div class="infDer"></div>

	<display:table name="llistatPendientesAceptarDevolver" requestURI="" id="trasllatAceptarDevolver" sort="list" cellpadding="0" cellspacing="0" class="listado665 noEnlace">
		<%
			es.caib.gestoli.logic.model.Trasllat trasllat = (es.caib.gestoli.logic.model.Trasllat)pageContext.getAttribute("trasllatAceptarDevolver");
		  	
			java.util.Collection diposits = trasllat.getDiposits();
			java.util.Iterator it = diposits.iterator();
			java.lang.String nom = "";
			java.lang.Double quantitat = new java.lang.Double(0.0);
			java.lang.String partida = "";
			java.lang.String categoria = "";
			
			if (it.hasNext()){
				es.caib.gestoli.logic.model.Diposit diposit = (es.caib.gestoli.logic.model.Diposit)it.next();
				nom = diposit.getCodiAssignat();
				quantitat = diposit.getVolumActual();
				es.caib.gestoli.logic.model.PartidaOli partidaOli = diposit.getPartidaOli();
				if (partidaOli != null){
					partida = partidaOli.getNom();
					categoria = partidaOli.getCategoriaOli().getNom();
				}
			}
			
			pageContext.setAttribute("nom", nom);
			pageContext.setAttribute("quantitat", quantitat);
			pageContext.setAttribute("partida", partida);
			pageContext.setAttribute("categoria", categoria);
		%>
		<display:column titleKey="manteniment.tareasPendientes.origen" sortable="true" sortProperty="establimentByTdiCodeor.nom" style="background-color: #FFFF00;">
			<c:out value="${trasllatAceptarDevolver.establimentByTdiCodede.nom}" />
		</display:column>
		<display:column titleKey="consulta.oliEntradaTraslado.camp.volante" sortable="true" headerClass="ancho50" style="background-color: #FFFF00;">
			<c:out value="${trasllatAceptarDevolver.id}" />
		</display:column>
		<display:column titleKey="manteniment.nom" sortable="true" headerClass="ancho100" style="background-color: #FFFF00;">
			<c:out value="${nom}" />
		</display:column>
		<display:column titleKey="manteniment.litres" sortable="true" headerClass="ancho75" style="background-color: #FFFF00;">
			<c:out value="${quantitat}" />
		</display:column>
		<display:column titleKey="manteniment.partida" sortable="true" headerClass="ancho100" style="background-color: #FFFF00;">
			<c:out value="${partida}" />
		</display:column>
		<display:column titleKey="manteniment.categoria" sortable="true" headerClass="ancho75" style="background-color: #FFFF00;">
			<c:out value="${categoria}" />
		</display:column>
		<display:column headerClass="ancho100 final" style="background-color: #FFFF00;">
			
			<form name="form_acceptar_devolver" id="form_acceptar_devolver${trasllatAceptarDevolver_rowNum}" action="TrasllatLlistat.html" method="get" class="extended seguit" style="width:150px;">
				<!-- a class="tarea_aceptar" href="TrasllatLlistat.html?tipus=aceptardevolver&amp;aceptar=t&amp;trasllatId=<c:out value='${trasllatAceptarDevolver.id}'/>"><span><fmt:message key="manteniment.tareasPendientes.aceptar" /></span></a-->
				
				<!-- CALENDARI -->
				<div class="bordeInput">
					<div id="camp_dataBordeFoco" class="bordeFoco">
						<input type="text"
							id="camp_data2_${trasllatAceptarDevolver_rowNum}"
							name="data"
							class="inputData"
							onblur="parseData_camp_data(this);"
							maxlength="10" /> 
					</div>
				</div>
				
				<input type="hidden" name="tipus" value="aceptardevolver" />
				<input type="hidden" name="aceptar" value="t" />
				<input type="hidden" name="trasllatId" value="${trasllatAceptarDevolver.id}" />
				<a class="tarea_aceptar" onclick="document.getElementById('form_acceptar_devolver${trasllatAceptarDevolver_rowNum}').submit();"><span><fmt:message key="manteniment.tareasPendientes.aceptar" /></span></a>
			</form>
		
		</display:column>
	</display:table>
	</div>
	</div>
</c:if>
	</div>
	
	<br /><br />
	<div class="pendientesDevolver">
	<h1 class="tituloListado"><fmt:message key="manteniment.tareasPendientes.avisos" /></h1>
<c:if test="${(llistatAvisosPendientes != null && not empty llistatAvisosPendientes)}">
	<div class="listado"><%-- Tabla de resultados --%>
	<div class="layoutSombraTabla ancho">
	<div class="rellenoInf"></div>
	<div class="rellenoIzq"></div>
	<div class="rellenoDer"></div>
	<div class="supDer"></div>
	<div class="supIzq"></div>
	<div class="infIzq"></div>
	<div class="infDer"></div>

	<display:table name="llistatAvisosPendientes" requestURI="" id="avis" sort="list" cellpadding="0" cellspacing="0" class="listado665 noEnlace">
		<display:column property="tipus" titleKey="avis.camp.tipus" headerClass="ancho100" sortable="true" style="background-color: #FFFF99;"/>
		<display:column property="descripcio" titleKey="avis.camp.descripcio" headerClass="ancho100" sortable="true" style="background-color: #FFFF99;"/>
		<display:column property="frecuencia" titleKey="avis.camp.frecuencia" headerClass="ancho100" sortable="true" style="background-color: #FFFF99;"/>
		<display:column property="dataSeguent" titleKey="avis.camp.dataAvis" headerClass="ancho100" sortable="true" style="background-color: #FFFF99;"/>
		<display:column headerClass="ancho100 final" style="background-color: #FFFF99;">
			<a class="tarea_aceptar" href="TrasllatLlistat.html?tipus=avis&amp;aceptar=t&amp;avisId=<c:out value='${avis.id}'/>"><span><fmt:message key="manteniment.tareasPendientes.aceptar" /></span></a>
		</display:column>
	</display:table>
	</div>
	</div>
</c:if>
	</div>
	

<c:if test="${(llistatPendientesDevolver != null && not empty llistatPendientesDevolver) || (llistatPendientesAceptar != null && not empty llistatPendientesAceptar) || (llistatPendientesAceptarDevolver != null && not empty llistatPendientesAceptarDevolver) || (llistatAvisosPendientes != null && not empty llistatAvisosPendientes)}">
	<%-- Colores en tablas --%>
	<script type="text/javascript">
			$(document).ready(function(){
				setEstilosTabla();
			})
		</script>
</c:if>

</body>
</html>
