<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/fn-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>

<html>
<head>
<title><fmt:message key="edicioProcessos.llistitol" /></title>
<script type="text/javascript" src="js/displaytag.js"></script>
<script type="text/javascript" src="js/calendar/calendar.js"></script>
<script type="text/javascript" src="js/calendar/calendar-setup.js"></script>
<script type="text/javascript" src="js/calendar/lang/calendar-es.js"></script>
<link href="js/calendar/calendar-viti.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="js/form.js"></script>

<script type="text/javascript" language="javascript">

		function borrarProceso(id, tipus) {
			var form = document.getElementById("deleteForm");
			form.id.value = id;
			form.tipus.value = tipus;
			
			submitFormConfirm('deleteForm','<fmt:message key="manteniment.esborrar.confirm"/>');
		}

		function editarProceso(id, tipus) {
			var form = document.getElementById("editarForm");
			form.id.value = id;
			form.tipus.value = tipus;
			
			submitForm('editarForm');
		}

		function mostrarCarregant() {
			document.getElementById('Carregant').style.display = 'block';
		}
	
    </script>
</head>

<body>
<form id="formulario" action="EdicioProcessos.html" method="post" class="extended seguit">
	<fieldset>
		<div id="divIdFordataInici" class=" conMargen campoForm165  required">
			<label for="dataInici"><fmt:message key="consulta.camp.dataInici"/></label>
			<div class="bordeInput">
			<div id="dataIniciBordeFoco" class="bordeFoco">
				<input type="text" id="dataInici" name="dataInici" class="inputData" value="<c:out value="${dataInici}"/>" onchange="" maxlength="10" size="10"/>
				<p class="comentari">(dd/mm/aaaa)</p>
			</div>
			</div>
		</div>
		<div id="divIdFordataFi" class=" conMargen campoForm165  required">
			<label for="dataFi"><fmt:message key="consulta.camp.dataFi"/></label>
			<div class="bordeInput">
			<div id="dataFiBordeFoco" class="bordeFoco">
				<input type="text" id="dataFi" name="dataFi" class="inputData" value="<c:out value="${dataFi}"/>" onchange="" maxlength="10" size="10"/>
				<p class="comentari">(dd/mm/aaaa)</p>
			</div>
			</div>
		</div>
			
		<div class="separadorH"></div>
			
		<div class="botonesForm">
		<div id="guardarForm" class="btnCorto"
			onclick="mostrarCarregant();submitForm('formulario')"
			onmouseover="underline('enlaceGuardarForm')"
			onmouseout="underline('enlaceGuardarForm')"><a
			id="enlaceGuardarForm" href="javascript:void(0);"><fmt:message
			key="manteniment.cercar" /></a></div>
		</div>
	
		<div class="separadorH"></div>
		
		<div id="Carregant" style="display: none; text-align: center">
			<img src="img/elems/carregant.gif"/>
		</div>
		
		<c:if test="${llistat != null && empty llistat}">
			<div class="separadorH"></div>
		
			<c:import url="comu/MissatgesIErrors.jsp">
				<c:param name="listError">
					<fmt:message key="consulta.entradaOliva.noRegs" />
				</c:param>
			</c:import>
		</c:if>
	</fieldset>
</form>
			
<c:if test="${not empty llistat}">			
	<div id="listado"><%-- Tabla de resultados --%>
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
	
	<display:table name="llistat" requestURI="" id="edicioProcessos" sort="list" cellpadding="0" cellspacing="0" class="listadoAncho noEnlace">
		<c:set var="taula"><c:if test="${edicioProcessos.olivaTaula == 'true'}">taula</c:if></c:set>
		<c:set var="procesId">
				<c:choose>
					<c:when test="${edicioProcessos.accio == 12 or edicioProcessos.accio == 9 or edicioProcessos.accio == 18}"><c:out value="${edicioProcessos.idDipositLot}"/></c:when>
					<c:otherwise><c:out value="${edicioProcessos.traza.id}"/></c:otherwise>
				</c:choose>
		</c:set>
		<display:column titleKey="edicioProcessos.camp.comptador" sortable="true" sortProperty="data" headerClass="ancho75" class="${taula}">
			<c:out value="${procesId}" />
		</display:column>
		<display:column titleKey="edicioProcessos.camp.data" sortable="true" sortProperty="data" headerClass="ancho75" class="${taula}">
			<c:out value="${edicioProcessos.dataFormat}" />
		</display:column>
		<c:if test="${edicioProcessos.accio != 9}">
			<display:column titleKey="edicioProcessos.camp.accio" sortable="true" class="${taula}">
				<fmt:message key="edicioProcessos.accio${edicioProcessos.accio}"/>
				<c:if test="${edicioProcessos.accio == 9}"><c:out value=" (${edicioProcessos.aux})" /></c:if>
				
			</display:column>
		</c:if>
		<c:if test="${edicioProcessos.accio == 9}">
			<display:column titleKey="edicioProcessos.camp.accio" sortable="true" class="${taula}">
				<fmt:message key="edicioProcessos.accio${edicioProcessos.accio}"/>
				<c:if test="${edicioProcessos.accio == 9}"><c:out value=" (${edicioProcessos.aux})" /></c:if>
				
			</display:column>
		</c:if>
		<c:if test="${edicioProcessos.accio != 10}">
			<display:column titleKey="edicioProcessos.camp.dipositLot" sortable="true" class="${taula}">
				<c:out value="${edicioProcessos.dipositLot}" />
			</display:column>
			<display:column titleKey="edicioProcessos.camp.establiment" sortable="true" class="${taula}">
				<c:out value="${edicioProcessos.establiment.nom}" />
			</display:column>
		</c:if>
		<c:if test="${edicioProcessos.accio == 10}">
			<display:column titleKey="edicioProcessos.camp.diposit" sortable="true" class="${taula}">
				<c:out value="${edicioProcessos.dipositLot}" />
			</display:column>
			<display:column titleKey="edicioProcessos.camp.establiment" sortable="true" class="${taula}">
				<c:out value="${edicioProcessos.establiment.nom}" />
			</display:column>
		</c:if>
		<display:column property="observacions" titleKey="edicioProcessos.camp.observacions" sortable="true" class="${taula}"/>
		<display:column headerClass="ancho75 paddingCelda final" class="${taula}">
			<c:if test="${not empty edicioProcessos.idDipositLot and (edicioProcessos.accio != 12 or edicioProcessos.accio != 9)}">
				<a 	href="ConsultaTrazabilitatResumida.html?tipus=<c:out value="${edicioProcessos.tipusDipositLot}"/>&amp;id=<c:out value="${edicioProcessos.idDipositLot}"/>"><img
					src="img/icons/trazabilidad.gif" border="0"
					title="<fmt:message key="consulta.trazabilitat.${edicioProcessos.tipusDipositLot}.titol"/>"
					alt="<fmt:message key="consulta.trazabilitat.${edicioProcessos.tipusDipositLot}.titol"/>" /></a>
			</c:if>
			
			<c:if test="${edicioProcessos.valid}">
				<a	href="javascript:borrarProceso('<c:out value="${procesId}"/>', '<c:out value="${edicioProcessos.accio}"/>');"><img
					src="img/icons/eliminar.png" border="0"
					title="<fmt:message key="edicioProcessos.eliminar"/>"
					alt="<fmt:message key="edicioProcessos.eliminar"/>" />
				</a>
				<c:if test="${edicioProcessos.accio == 6}">
					<a 	href="javascript:editarProceso('<c:out value="${procesId}"/>', '<c:out value="${edicioProcessos.accio}"/>');"><img
						src="img/icons/comentar.png" border="0"
						title="<fmt:message key="edicioProcessos.modificar"/>"
						alt="<fmt:message key="edicioProcessos.modificar"/>" />
					</a>
				</c:if>
			</c:if>
			<c:if test="${edicioProcessos.accio != 6 and edicioProcessos.accio != 9 and edicioProcessos.accio != 18}">
				<a 	href="javascript:editarProceso('<c:out value="${procesId}"/>', '<c:out value="${edicioProcessos.accio}"/>');"><img
					src="img/icons/comentar.png" border="0"
					title="<fmt:message key="edicioProcessos.modificar"/>"
					alt="<fmt:message key="edicioProcessos.modificar"/>" />
				</a>
			</c:if>
		</display:column>
	</display:table>
	</div>
	
	</div>


	<form id="deleteForm" action="EdicioProcessos.html" method="post" class="seguit" onsubmit="return confirm('<fmt:message key="manteniment.estasegur"/>')">
		<input id="id" name="id" value="" type="hidden" />
		<input id="tipus" name="tipus" value="" type="hidden" />
		<input id="action" name="action" value="delete" type="hidden" />
		<input id="dataInici" name="dataInici" value="${dataInici}" type="hidden"/>
		<input id="dataFi" name="dataFi" value="${dataFi}" type="hidden"/>
	</form>
	<form id="editarForm" action="EdicioProcessosForm.html" method="get" class="seguit" onsubmit="return confirm('<fmt:message key="manteniment.estasegur"/>')">
		<input id="id" name="id" value="" type="hidden" />
		<input id="tipus" name="tipus" value="" type="hidden" />
		<input id="dataInici" name="dataInici" value="${dataInici}" type="hidden"/>
		<input id="dataFi" name="dataFi" value="${dataFi}" type="hidden"/>
	</form>
</c:if>


<%-- Colores en tablas --%>
<c:if test="${not empty llistat}">
	<script type="text/javascript">
			$(document).ready(function(){
				setEstilosTabla();
			})
		</script>
</c:if>

</body>
</html>
