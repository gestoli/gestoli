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

	
	<br /><br />
	<div class="pendientesDevolver">
		<c:if test="${(netejaRealitzacioPendents != null && not empty netejaRealitzacioPendents) || (netejaVerificacioPendents != null && not empty netejaVerificacioPendents)}">
			<h2 class="tituloListado" style="margin-left: 100px;"><fmt:message key="qualitat.avisos.plaNeteja.titol" /></h2><br />
		</c:if>
		<c:if test="${(netejaRealitzacioPendents != null && not empty netejaRealitzacioPendents)}">
			<h1 class="tituloListado"><fmt:message key="qualitat.avisos.plaNeteja.titolRealitzacio" /></h1>
			<div class="listado"><%-- Tabla de resultados --%>
			<div class="layoutSombraTabla ancho">
			<div class="rellenoInf"></div>
			<div class="rellenoIzq"></div>
			<div class="rellenoDer"></div>
			<div class="supDer"></div>
			<div class="supIzq"></div>
			<div class="infIzq"></div>
			<div class="infDer"></div>
			<display:table name="netejaRealitzacioPendents" requestURI="" id="avis" sort="list" cellpadding="0" cellspacing="0" class="listado665 noEnlace">
				<display:column property="equip.nom" titleKey="qualitat.avisos.plaNeteja.equip" headerClass="ancho100" sortable="true" style="background-color: #FFFF99;"/>
				<display:column property="accio" titleKey="qualitat.avisos.plaNeteja.accio" headerClass="ancho100" sortable="true" style="background-color: #FFFF99;"/>
				<display:column titleKey="qualitat.avisos.plaNeteja.responsable" headerClass="ancho100" sortable="true" style="background-color: #FFFF99;">
					${avis.responsable.nom} ${avis.responsable.llinatges}
				</display:column>
				<display:column titleKey="qualitat.avisos.plaNeteja.frequencia" headerClass="ancho100" sortable="true" style="background-color: #FFFF99;">
					<c:set var="necessitat" value="necessitat" />
					<c:choose>
						<c:when test="${avis.frequencia == necessitat }">
							<fmt:message key="qualitat.plaNeteja.frecuencia.necesaria" />
						</c:when>
						<c:otherwise>
							<fmt:message key="qualitat.plaNeteja.frecuencia.${avis.frequencia}" />
						</c:otherwise>		
					</c:choose>
				</display:column>
				<display:column headerClass="final" style="background-color: #FFFF99;">
					<a class="tarea_aceptar" href="QualitatPlaNetejaRealitzacio.html?idNeteja=<c:out value='${avis.codi}'/>"><span><fmt:message key="qualitat.avisos.accedir" /></span></a>
				</display:column>		
			</display:table>
			</div>
			</div>
			<br />
		</c:if>
		<c:if test="${(netejaVerificacioPendents != null && not empty netejaVerificacioPendents)}">
			<h1 class="tituloListado"><fmt:message key="qualitat.avisos.plaNeteja.titolVerificacio" /></h1>
			<div class="listado"><%-- Tabla de resultados --%>
			<div class="layoutSombraTabla ancho">
			<div class="rellenoInf"></div>
			<div class="rellenoIzq"></div>
			<div class="rellenoDer"></div>
			<div class="supDer"></div>
			<div class="supIzq"></div>
			<div class="infIzq"></div>
			<div class="infDer"></div>
			<display:table name="netejaRealitzacioPendents" requestURI="" id="avis" sort="list" cellpadding="0" cellspacing="0" class="listado665 noEnlace">
				<display:column property="equip.nom" titleKey="qualitat.avisos.plaNeteja.equip" headerClass="ancho100" sortable="true" style="background-color: #FFFF99;"/>
				<display:column property="accio" titleKey="qualitat.avisos.plaNeteja.accio" headerClass="ancho100" sortable="true" style="background-color: #FFFF99;"/>
				<display:column titleKey="qualitat.avisos.plaNeteja.responsable" headerClass="ancho100" sortable="true" style="background-color: #FFFF99;">
					${avis.respVerificacio.nom} ${avis.respVerificacio.llinatges}
				</display:column>
				<display:column titleKey="qualitat.avisos.plaNeteja.frequencia" headerClass="ancho100" sortable="true" style="background-color: #FFFF99;">
					<c:set var="necessitat" value="necessitat" />
					<c:choose>
						<c:when test="${avis.periodicitatVerificacio == necessitat }">
							<fmt:message key="qualitat.plaNeteja.frecuencia.necesaria" />
						</c:when>
						<c:otherwise>
							<fmt:message key="qualitat.plaNeteja.frecuencia.${avis.periodicitatVerificacio}" />
						</c:otherwise>		
					</c:choose>
				</display:column>
				<display:column headerClass="final" style="background-color: #FFFF99;">
					<a class="tarea_aceptar" href="QualitatPlaNetejaRealitzacio.html?idNeteja=<c:out value='${avis.codi}'/>"><span><fmt:message key="qualitat.avisos.accedir" /></span></a>
				</display:column>		
			</display:table>
			</div>
			</div>
		</c:if>
	</div>
	
	<br /><br />
	<div class="pendientesDevolver">
		<c:if test="${(mantenimentControlPendents != null && not empty mantenimentControlPendents) || (mantenimentVerificacioPendents != null && not empty mantenimentVerificacioPendents)}">
			<h2 class="tituloListado" style="margin-left: 100px;"><fmt:message key="qualitat.avisos.plaManteniment.titol" /></h2><br />
		</c:if>
		<c:if test="${(mantenimentControlPendents != null && not empty mantenimentControlPendents)}">
			<h1 class="tituloListado"><fmt:message key="qualitat.avisos.plaManteniment.titolRealitzacio" /></h1>
			<div class="listado"><%-- Tabla de resultados --%>
			<div class="layoutSombraTabla ancho">
			<div class="rellenoInf"></div>
			<div class="rellenoIzq"></div>
			<div class="rellenoDer"></div>
			<div class="supDer"></div>
			<div class="supIzq"></div>
			<div class="infIzq"></div>
			<div class="infDer"></div>
			<display:table name="mantenimentControlPendents" requestURI="" id="avis" sort="list" cellpadding="0" cellspacing="0" class="listado665 noEnlace">
				<display:column property="equip.nom" titleKey="qualitat.avisos.plaManteniment.equip" headerClass="ancho100" sortable="true" style="background-color: #FFFF99;"/>
				<display:column property="accions" titleKey="qualitat.avisos.plaManteniment.accio" headerClass="ancho100" sortable="true" style="background-color: #FFFF99;"/>
				<display:column titleKey="qualitat.avisos.plaManteniment.responsable" headerClass="ancho100" sortable="true" style="background-color: #FFFF99;">
					${avis.responsableIntern.nom} ${avis.responsableIntern.llinatges} ${avis.responsableExtern}
				</display:column>
				<display:column titleKey="qualitat.avisos.plaManteniment.frequencia" headerClass="ancho100" sortable="true" style="background-color: #FFFF99;">
					<fmt:message key="qualitat.frecuencia.${avis.frecuencia}" />
				</display:column>
				<display:column headerClass="final" style="background-color: #FFFF99;">
					<a class="tarea_aceptar" href="QualitatPlaMantenimentControl.html?idManteniment=<c:out value='${avis.id}'/>"><span><fmt:message key="qualitat.avisos.accedir" /></span></a>
				</display:column>		
			</display:table>
			</div>
			</div>
			<br />
		</c:if>
		<c:if test="${(mantenimentVerificacioPendents != null && not empty mantenimentVerificacioPendents)}">
			<h1 class="tituloListado"><fmt:message key="qualitat.avisos.plaManteniment.titolVerificacio" /></h1>
			<div class="listado"><%-- Tabla de resultados --%>
			<div class="layoutSombraTabla ancho">
			<div class="rellenoInf"></div>
			<div class="rellenoIzq"></div>
			<div class="rellenoDer"></div>
			<div class="supDer"></div>
			<div class="supIzq"></div>
			<div class="infIzq"></div>
			<div class="infDer"></div>
			<display:table name="mantenimentVerificacioPendents" requestURI="" id="avis" sort="list" cellpadding="0" cellspacing="0" class="listado665 noEnlace">
				<display:column property="manteniment.equip.nom" titleKey="qualitat.avisos.plaManteniment.equip" headerClass="ancho100" sortable="true" style="background-color: #FFFF99;"/>
				<display:column titleKey="qualitat.avisos.plaManteniment.accio" headerClass="ancho100" sortable="true" style="background-color: #FFFF99;">
					${avis.accioRealitzada}, ${avis.altresAccions} 
				</display:column>
				<display:column titleKey="qualitat.avisos.plaManteniment.responsable" headerClass="ancho100" sortable="true" style="background-color: #FFFF99;">
					${avis.manteniment.verificacioResponsable.nom} ${avis.manteniment.verificacioResponsable.llinatges}
				</display:column>
				<display:column titleKey="qualitat.avisos.plaManteniment.dataRealitzacio" headerClass="ancho100" sortable="true" style="background-color: #FFFF99;">
					<fmt:formatDate value="${avis.dataRealitzacio}" pattern="dd/MM/yyyy"/>
				</display:column>
				<display:column headerClass="final" style="background-color: #FFFF99;">
					<a class="tarea_aceptar" href="QualitatPlaMantenimentControlVerificacioForm.html?idManteniment=<c:out value='${avis.manteniment.id}'/>&id=<c:out value='${avis.id}'/>"><span><fmt:message key="qualitat.avisos.accedir" /></span></a>
				</display:column>		
			</display:table>
			</div>
			</div>
		</c:if>
	</div>
	
	<br /><br />
	<div class="pendientesDevolver">
		<c:if test="${(plaguesVerificacioPendents != null && not empty plaguesVerificacioPendents)}">
			<h2 class="tituloListado" style="margin-left: 100px;"><fmt:message key="qualitat.avisos.controlPlagues.titol" /></h2><br />

			<h1 class="tituloListado"><fmt:message key="qualitat.avisos.controlPlagues.titolVerificacio" /></h1>
			<div class="listado"><%-- Tabla de resultados --%>
			<div class="layoutSombraTabla ancho">
			<div class="rellenoInf"></div>
			<div class="rellenoIzq"></div>
			<div class="rellenoDer"></div>
			<div class="supDer"></div>
			<div class="supIzq"></div>
			<div class="infIzq"></div>
			<div class="infDer"></div>
			<display:table name="plaguesVerificacioPendents" requestURI="" id="avis" sort="list" cellpadding="0" cellspacing="0" class="listado665 noEnlace">
				<display:column property="elementControl" titleKey="qualitat.avisos.plaguesControl.elementControl" headerClass="ancho100" sortable="true" style="background-color: #FFFF99;"/>
				<display:column property="ubicacio" titleKey="qualitat.avisos.plaguesControl.ubicacio" headerClass="ancho100" sortable="true" style="background-color: #FFFF99;"/>
				<display:column titleKey="qualitat.avisos.plaguesControl.responsable" headerClass="ancho100" sortable="true" style="background-color: #FFFF99;">
					${avis.responsableIntern.nom} ${avis.responsableIntern.llinatges} ${avis.empresaExterna}
				</display:column>
				<display:column titleKey="qualitat.avisos.plaguesControl.frequencia" headerClass="ancho100" sortable="true" style="background-color: #FFFF99;">
					<fmt:message key="qualitat.frecuencia.${avis.frecuencia}" />
				</display:column>
				<display:column headerClass="final" style="background-color: #FFFF99;">
					<a class="tarea_aceptar" href="QualitatControlPlaguesVerificacio.html?idPlaga=<c:out value='${avis.id}'/>"><span><fmt:message key="qualitat.avisos.accedir" /></span></a>
				</display:column>		
			</display:table>
			</div>
			</div>
		</c:if>
	</div>
	

	<br /><br />
	<div class="pendientesDevolver">
		<c:if test="${(proveidorVerificacioPendents != null && not empty proveidorVerificacioPendents)}">
			<h2 class="tituloListado" style="margin-left: 100px;"><fmt:message key="qualitat.avisos.controlProveidors.titol" /></h2><br />

			<h1 class="tituloListado"><fmt:message key="qualitat.avisos.controlProveidors.titolVerificacio" /></h1>
			<div class="listado"><%-- Tabla de resultados --%>
			<div class="layoutSombraTabla ancho">
			<div class="rellenoInf"></div>
			<div class="rellenoIzq"></div>
			<div class="rellenoDer"></div>
			<div class="supDer"></div>
			<div class="supIzq"></div>
			<div class="infIzq"></div>
			<div class="infDer"></div>
			<display:table name="proveidorVerificacioPendents" requestURI="" id="avis" sort="list" cellpadding="0" cellspacing="0" class="listado665 noEnlace">
				<display:column property="nom" titleKey="qualitat.avisos.controlProveidors.raoSocial" headerClass="ancho100" sortable="true" style="background-color: #FFFF99;"/>
				<display:column property="direccio" titleKey="qualitat.avisos.controlProveidors.direccio" headerClass="ancho100" sortable="true" style="background-color: #FFFF99;"/>
				<display:column property="personaContacte" titleKey="qualitat.avisos.controlProveidors.personaContacte" headerClass="ancho100" sortable="true" style="background-color: #FFFF99;"/>
				<display:column headerClass="final" style="background-color: #FFFF99;">
					<a class="tarea_aceptar" href="QualitatProveidorAvaluacio.html?idProveidor=<c:out value='${avis.codi}'/>"><span><fmt:message key="qualitat.avisos.accedir" /></span></a>
				</display:column>		
			</display:table>
			</div>
			</div>
		</c:if>
	</div>
	
	
	<br /><br />
	<div class="pendientesDevolver">
		<c:if test="${(aiguaVerificacioPendents != null && not empty aiguaVerificacioPendents)}">
			<h2 class="tituloListado" style="margin-left: 100px;"><fmt:message key="qualitat.avisos.controlAigues.titol" /></h2><br />

			<h1 class="tituloListado"><fmt:message key="qualitat.avisos.controlAigues.titolVerificacio" /></h1>
			<div class="listado"><%-- Tabla de resultados --%>
			<div class="layoutSombraTabla ancho">
			<div class="rellenoInf"></div>
			<div class="rellenoIzq"></div>
			<div class="rellenoDer"></div>
			<div class="supDer"></div>
			<div class="supIzq"></div>
			<div class="infIzq"></div>
			<div class="infDer"></div>
			<display:table name="aiguaVerificacioPendents" requestURI="" id="avis" sort="list" cellpadding="0" cellspacing="0" class="listado665 noEnlace">
				<display:column titleKey="qualitat.avisos.plaguesControl.responsable" headerClass="ancho100" sortable="true" style="background-color: #FFFF99;">
					${avis.responsable.nom} ${avis.responsable.llinatges}
				</display:column>
				<display:column titleKey="qualitat.avisos.plaguesControl.frequencia" headerClass="ancho100" sortable="true" style="background-color: #FFFF99;">
					<fmt:message key="qualitat.frecuencia.${avis.frequencia}" />
				</display:column>
				<display:column headerClass="final" style="background-color: #FFFF99;">
					<a class="tarea_aceptar" href="QualitatAiguaControlOrganolepticForm.html"><span><fmt:message key="qualitat.avisos.accedir" /></span></a>
				</display:column>	
			</display:table>
			</div>
			</div>
		</c:if>
	</div>
	
	
	<br /><br />
	<div class="pendientesDevolver">
		<c:if test="${(appccControlPendents != null && not empty appccControlPendents) || (appccVerificacioPendents != null && not empty appccVerificacioPendents)}">
			<h2 class="tituloListado" style="margin-left: 100px;"><fmt:message key="qualitat.avisos.appcc.titol" /></h2><br />
		</c:if>
		<c:if test="${(appccControlPendents != null && not empty appccControlPendents)}">
			<h1 class="tituloListado"><fmt:message key="qualitat.avisos.appcc.titolControl" /></h1>
			<div class="listado"><%-- Tabla de resultados --%>
			<div class="layoutSombraTabla ancho">
			<div class="rellenoInf"></div>
			<div class="rellenoIzq"></div>
			<div class="rellenoDer"></div>
			<div class="supDer"></div>
			<div class="supIzq"></div>
			<div class="infIzq"></div>
			<div class="infDer"></div>
			<display:table name="appccControlPendents" requestURI="" id="avis" sort="list" cellpadding="0" cellspacing="0" class="listado665 noEnlace">
				<display:column property="etapa.nom" titleKey="qualitat.avisos.appcc.etapa" headerClass="ancho100" sortable="true" style="background-color: #FFFF99;"/>
				<display:column property="perill.detall" titleKey="qualitat.avisos.appcc.perill" headerClass="ancho100" sortable="true" style="background-color: #FFFF99;"/>
				<display:column titleKey="qualitat.avisos.appcc.puntControl" headerClass="ancho100" sortable="true" style="background-color: #FFFF99;">
					<fmt:message key="qualitat.appcc.control.puntControl.${avis.puntControl}" />
				</display:column>
				<display:column headerClass="final" style="background-color: #FFFF99;">
					<a class="tarea_aceptar" href="QualitatAPPCCFitxaControlForm.html?id=<c:out value='${avis.id}'/>"><span><fmt:message key="qualitat.avisos.accedir" /></span></a>
				</display:column>		
			</display:table>
			</div>
			</div>
			<br />
		</c:if>
		<c:if test="${(appccVerificacioPendents != null && not empty appccVerificacioPendents)}">
			<h1 class="tituloListado"><fmt:message key="qualitat.avisos.appcc.titolVerificacio" /></h1>
			<div class="listado"><%-- Tabla de resultados --%>
			<div class="layoutSombraTabla ancho">
			<div class="rellenoInf"></div>
			<div class="rellenoIzq"></div>
			<div class="rellenoDer"></div>
			<div class="supDer"></div>
			<div class="supIzq"></div>
			<div class="infIzq"></div>
			<div class="infDer"></div>
			<display:table name="appccVerificacioPendents" requestURI="" id="avis" sort="list" cellpadding="0" cellspacing="0" class="listado665 noEnlace">
				<display:column property="fitxa.control.etapa.nom" titleKey="qualitat.avisos.appcc.etapa" headerClass="ancho100" sortable="true" style="background-color: #FFFF99;"/>
				<display:column property="fitxa.control.perill.detall" titleKey="qualitat.avisos.appcc.perill" headerClass="ancho100" sortable="true" style="background-color: #FFFF99;"/>
				<display:column titleKey="qualitat.avisos.appcc.puntControl" headerClass="ancho100" sortable="true" style="background-color: #FFFF99;">
					<fmt:message key="qualitat.appcc.control.puntControl.${avis.fitxa.control.puntControl}" />
				</display:column>
				<display:column titleKey="qualitat.avisos.avisos.responsable" headerClass="ancho100" sortable="true" style="background-color: #FFFF99;">
					${avis.responsableVigilancia.nom} ${avis.responsableVigilancia.llinatges}
				</display:column>
				<display:column titleKey="qualitat.avisos.avisos.frequencia" headerClass="ancho100" sortable="true" style="background-color: #FFFF99;">
					<fmt:message key="qualitat.frecuencia.${avis.freqVigilancia}" />
				</display:column>
				<display:column property="vigilancia" titleKey="qualitat.avisos.appcc.vigilancia" headerClass="ancho100" sortable="true" style="background-color: #FFFF99;"/>
				<display:column property="registre" titleKey="qualitat.avisos.appcc.registre" headerClass="ancho100" sortable="true" style="background-color: #FFFF99;"/>
				<display:column property="limits" titleKey="qualitat.avisos.appcc.limits" headerClass="ancho100" sortable="true" style="background-color: #FFFF99;"/>
				<display:column headerClass="final" style="background-color: #FFFF99;">
					<a class="tarea_aceptar" href="QualitatAPPCCControlVerificacioForm.html?id=<c:out value='${avis.fitxa.control.id}'/>"><span><fmt:message key="qualitat.avisos.accedir" /></span></a>
				</display:column>		
			</display:table>
			</div>
			</div>
		</c:if>
	</div>
	
	
	<br /><br />
	<div class="pendientesDevolver">
		<c:if test="${(noConformitatsPendents != null && not empty noConformitatsPendents)}">
			<h2 class="tituloListado" style="margin-left: 100px;"><fmt:message key="qualitat.avisos.noConformitat.titol" /></h2><br />

			<h1 class="tituloListado"><fmt:message key="qualitat.avisos.noConformitat.titolOberta" /></h1>
			<div class="listado"><%-- Tabla de resultados --%>
			<div class="layoutSombraTabla ancho">
			<div class="rellenoInf"></div>
			<div class="rellenoIzq"></div>
			<div class="rellenoDer"></div>
			<div class="supDer"></div>
			<div class="supIzq"></div>
			<div class="infIzq"></div>
			<div class="infDer"></div>
			<display:table name="noConformitatsPendents" requestURI="" id="avis" sort="list" cellpadding="0" cellspacing="0" class="listado665 noEnlace">
				<display:column titleKey="qualitat.noConformitat.camp.departament" headerClass="ancho100" sortable="true" style="background-color: #FFFF99;">
					<c:if test="${not empty avis.departament}">
						<fmt:message key="qualitat.departament.${avis.departament.nom}" />
					</c:if>
				</display:column>
				<display:column titleKey="qualitat.noConformitat.camp.dataNoConformitat" headerClass="ancho100" sortable="true" style="background-color: #FFFF99;">
					<fmt:formatDate value="${avis.dataNoConformitat}" pattern="dd/MM/yyyy"/>
				</display:column>
				<display:column titleKey="qualitat.noConformitat.camp.responsableDeteccio" headerClass="ancho100" sortable="true" style="background-color: #FFFF99;">
					<c:out value="${avis.responsableDeteccio.nom} ${avis.responsableDeteccio.llinatges}"/>
				</display:column>
				<display:column property="descripcio" titleKey="qualitat.noConformitat.camp.descripcio" headerClass="ancho100" sortable="true" style="background-color: #FFFF99;"/>
				<display:column headerClass="final" style="background-color: #FFFF99;">
					<a class="tarea_aceptar" href="QualitatNoConformitatForm.html?id=<c:out value='${avis.id}'/>"><span><fmt:message key="qualitat.avisos.accedir" /></span></a>
				</display:column>	
			</display:table>
			</div>
			</div>
		</c:if>
	</div>
	
	
	<c:if test="${(netejaRealitzacioPendents != null && not empty netejaRealitzacioPendents) || (netejaVerificacioPendents != null && not empty netejaVerificacioPendents) || 
				  (mantenimentControlPendents != null && not empty mantenimentControlPendents) || (mantenimentVerificacioPendents != null && not empty mantenimentVerificacioPendents) ||
				  (plaguesVerificacioPendents != null && not empty plaguesVerificacioPendents) || 
				  (proveidorVerificacioPendents != null && not empty proveidorVerificacioPendents) || 
				  (aiguaVerificacioPendents != null && not empty aiguaVerificacioPendents) ||
				  (appccControlPendents != null && not empty appccControlPendents) || (appccVerificacioPendents != null && not empty appccVerificacioPendents) ||
				  (noConformitatsPendents != null)
				 }">
				  
				  
				  
		<%-- Colores en tablas --%>
		<script type="text/javascript">
			$(document).ready(function(){
				setEstilosTabla();
			})
		</script>
	</c:if>

</body>
</html>