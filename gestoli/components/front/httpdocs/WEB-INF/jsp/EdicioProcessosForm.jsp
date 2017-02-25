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

</head>

<body>
<form id="formulario" action="EdicioProcessosForm.html" method="post" class="extended seguit">
	<fieldset>
	
		<c:if test="${not empty formData.tipus}">
			<input type="hidden" id="tipus" name="tipus" value="${formData.tipus}"/>
			<div id="divIdFortipus" class=" conMargen campoForm165 required">
				<label for="tipus"><fmt:message key="edicioProcessos.camp.accio"/></label>
				<div class="bordeInput">
				<div id="tipusBordeFoco" class="bordeFoco">
					<p class="inputData"><STRONG><fmt:message key="edicioProcessos.accio${formData.tipus}"/></STRONG></p>
				</div>
				</div>
			</div>
		</c:if>
		<c:if test="${not empty formData.dataExecucio}">
			<input type="hidden" id="dataExecucio" name="dataExecucio" value="<fmt:formatDate value="${formData.dataExecucio}"  pattern="dd/MM/yyyy"/>"/>
			<div id="divIdFordataExecucio" class=" conMargen campoForm165 required">
				<label for="tipus"><fmt:message key="consulta.camp.dataExecucio"/></label>
				<div class="bordeInput">
				<div id="tipusBordeFoco" class="bordeFoco">
					<p class="inputData"><STRONG><fmt:formatDate value="${formData.dataExecucio}"  pattern="dd/MM/yyyy"/></STRONG></p>
				</div>
				</div>
			</div>
		</c:if>
		
		<div class="separadorH"></div>
		
		<c:if test="${not empty formData.dataMin}">
			<input type="hidden" id="dataMin" name="dataMin" value="<fmt:formatDate value="${formData.dataMin}"  pattern="dd/MM/yyyy"/>">
			<div id="divIdFordataMinima" class=" conMargen campoForm165 required">
				<label for="dataMinima"><fmt:message key="consulta.camp.dataMinima"/></label>
				<div class="bordeInput">
				<div id="dataMinimaBordeFoco" class="bordeFoco">
					<p class="inputData"><STRONG><fmt:formatDate value="${formData.dataMin}"  pattern="dd/MM/yyyy"/></STRONG></p>
				</div>
				</div>
			</div>
		</c:if>
		<c:if test="${not empty formData.dataMax}">
			<input type="hidden" id="dataMax" name="dataMax" value="<fmt:formatDate value="${formData.dataMax}"  pattern="dd/MM/yyyy"/>">
			<div id="divIdFordataMaxima" class=" conMargen campoForm165 required">
				<label for="dataMaxima"><fmt:message key="consulta.camp.dataMaxima"/></label>
				<div class="bordeInput">
				<div id="dataMaximaBordeFoco" class="bordeFoco">
					<p class="inputData"><STRONG><fmt:formatDate value="${formData.dataMax}"  pattern="dd/MM/yyyy"/></STRONG></p>
				</div>
				</div>
			</div>
		</c:if>
		
		<div class="separadorH"></div>
		
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="calendar" />
			<c:param name="path" value="formData.dataNova" />
			<c:param name="title">
				<fmt:message key="consulta.camp.dataNova" />
			</c:param>
			<c:param name="camp" value="dataNova" />
			<c:param name="name" value="dataNova" />
			<c:param name="maxlength" value="10" />
			<c:param name="required" value="required" />
			<c:param name="aclaracio">
				<fmt:message key="proces.aclaracio.formatdata" />
			</c:param>
			<c:param name="clase" value="conMargen campoForm165" />
		</c:import>
		
		<c:if test="${not empty formData.trazaId}">
			<c:import url="comu/CampFormulari.jsp">
				<c:param name="tipus" value="hidden" />
				<c:param name="path" value="formData.trazaId" />
				<c:param name="camp" value="trazaId" />
			</c:import>
		</c:if>
		<%--c:if test="${not empty formData.tipus}">
			<c:import url="comu/CampFormulari.jsp">
				<c:param name="tipus" value="hidden" />
				<c:param name="path" value="formData.tipus" />
				<c:param name="camp" value="tipus" />
			</c:import>
		</c:if--%>
		
		<div class="separadorH"></div>
			
		<div class="botonesForm">
		<div id="modificarForm" class="btnCorto"
			onclick="if(confirm('<fmt:message key="manteniment.modificar.confirm"/>')){submitForm('formulario')}"
			onmouseover="underline('enlaceModificarForm')"
			onmouseout="underline('enlaceModificarForm')"><a
			id="enlaceModificarForm" href="javascript:void(0);"><fmt:message
			key="manteniment.modificar" /></a></div>
		</div>
	
	</fieldset>
	<input id="dataInici" name="dataInici" value="${dataInici}" type="hidden"/>
	<input id="dataFi" name="dataFi" value="${dataFi}" type="hidden"/>
</form>
			
</body>
</html>
		