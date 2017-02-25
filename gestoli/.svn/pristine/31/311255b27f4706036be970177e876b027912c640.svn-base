<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ page import="es.caib.gestoli.front.util.*"%>
<%@ page import="java.util.ResourceBundle"%>
<%@ page import="java.util.Locale"%>
<%

	String lang = Idioma.getLang(request);
	request.setAttribute("lang",lang);
%>

<html>
<head>
<title>
	<c:choose>
		<c:when test="${not empty formData.id}">
			<fmt:message key="manteniment.modificacio" />
		</c:when>
		<c:otherwise>
			<fmt:message key="manteniment.alta" />
		</c:otherwise>
	</c:choose> 
	
	<fmt:message key="documentInspeccio.tipusdemant" />
</title>


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

<link href="js/calendar/calendar-viti.css" rel="stylesheet" type="text/css" />


<script type="text/javascript" src="js/form.js"></script>


</head>
<body>

<div>

<form id="formulario" name="DocumentInspeccioForm" action="DocumentInspeccioForm.html" method="post" class="extended seguit zona" enctype="multipart/form-data" onsubmit="">
	
	<c:if test="${not empty formData.id}">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="hidden" />
			<c:param name="path" value="formData.id" />
			<c:param name="camp" value="id" />
		</c:import>
	</c:if> 
	<c:if test="${not empty formData.idEstabliment}">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="hidden" />
			<c:param name="path" value="formData.idEstabliment" />
			<c:param name="camp" value="idEstabliment" />
		</c:import>
	</c:if>
	<c:if test="${not empty formData.idOlivicultor}">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="hidden" />
			<c:param name="path" value="formData.idOlivicultor" />
			<c:param name="camp" value="idOlivicultor" />
		</c:import>
	</c:if>

	<div class="separadorH"></div>
	
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="select" />
		<c:param name="path" value="formData.tipus" />
		<c:param name="title">
			<fmt:message key="documentInspeccio.camp.tipusInspeccio" />
		</c:param>
		<c:param name="camp" value="campo_tipus" />
		<c:param name="name" value="tipus" />
		<c:param name="required" value="required" />
		<c:param name="selectItems" value="tipusInspeccio" />
		<c:param name="selectItemsId" value="id" />
		<c:param name="selectItemsValue" value="nom" />
		<c:param name="selectSelectedValue" value="${formData.tipus}" />
		<c:param name="clase" value="campoFormGrande" />
	</c:import>
	
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="calendar" />
		<c:param name="path" value="formData.data" />
		<c:param name="title">
			<fmt:message key="documentInspeccio.camp.data" />
		</c:param>
		<c:param name="camp" value="campo_data" />
		<c:param name="name" value="data" />
		<c:param name="required" value="required" />
		<c:param name="maxlength" value="10" />
		<c:param name="aclaracio">
			<fmt:message key="proces.aclaracio.formatdata" />
		</c:param>
		<c:param name="clase" value="campoForm165" />
	</c:import>

	<div class="separadorH"></div>
	
	<c:set var="arxiu" value="${formData.arxiuObject}" scope="request" /> 
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="file" />
		<c:param name="path" value="formData.fitxer" />
		<c:param name="required" value="required" />
		<c:param name="title">
			<fmt:message key="document.camp.fitxer" />
		</c:param>
		<c:param name="camp" value="fitxer" />
		<c:param name="arxiu" value="arxiu" />
		<c:param name="width" value="150" />
		<c:param name="height" value="100" />
		<c:param name="document" value="true" />
	</c:import>
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="hidden" />
		<c:param name="path" value="formData.arxiu" />
		<c:param name="camp" value="arxiu" />
	</c:import>
	
	<div class="separadorH"></div>
	
	<div class="botonesForm">
		<c:if test="${not empty formData.id}">
			<div id="guardarForm" class="btnCorto"
				onclick="if(confirm('<fmt:message key="manteniment.modificar.confirm"/>')){submitForm('formulario')}"
				onmouseover="underline('enlaceGuardarForm')"
				onmouseout="underline('enlaceGuardarForm')"><a
				id="enlaceGuardarForm" href="javascript:void(0);"><fmt:message
				key="manteniment.guardar" /></a>
			</div>
		</c:if> 
		<c:if test="${empty formData.id}">
			<div id="guardarForm" class="btnCorto"
				onclick="submitForm('formulario')"
				onmouseover="underline('enlaceGuardarForm')"
				onmouseout="underline('enlaceGuardarForm')"><a
				id="enlaceGuardarForm" href="javascript:void(0);"><fmt:message
				key="manteniment.guardar" /></a>
			</div>
		</c:if>
		
		<c:if test="${not empty formData.olivicultor}">
			<div id="cancelarForm" class="btnCorto"
				onmouseover="underline('enlaceCancelarForm')"
				onmouseout="underline('enlaceCancelarForm')"
				onclick="document.location ='OlivicultorForm.html?id=<c:out value='${formData.idOlivicultor}'/>';">
			<a id="enlaceCancelarForm" href="javascript:void(0);"><fmt:message
				key="manteniment.tornar" /></a>
			</div>
		</c:if>
		<c:if test="${not empty formData.establiment}">
			<div id="cancelarForm" class="btnCorto"
				onmouseover="underline('enlaceCancelarForm')"
				onmouseout="underline('enlaceCancelarForm')"
				onclick="document.location ='EstablimentForm.html?id=<c:out value='${formData.idEstabliment}'/>';">
			<a id="enlaceCancelarForm" href="javascript:void(0);"><fmt:message
				key="manteniment.tornar" /></a>
			</div>
		</c:if>
		
		<c:if test="${not empty formData.id}">
			<div id="eliminarForm" class="btnCorto"
				onmouseover="underline('enlaceBorrarForm')"
				onmouseout="underline('enlaceBorrarForm')"
				onclick="submitFormConfirm('deleteForm','<fmt:message key="manteniment.esborrar.confirm"/>');">
			<a id="enlaceBorrarForm" href="javascript:void(0);"><fmt:message
				key="manteniment.esborrar" /></a>
			</div>
		</c:if>
	</div>


</form>

<form id="deleteForm" action="DocumentInspeccio.html" method="post"
	class="seguit"
	onsubmit="return confirm('<fmt:message key="manteniment.estasegur"/>')">
	<input id="id" name="id" value="<c:out value="${formData.id}"/>" type="hidden" /> 
	<c:if test="${not empty formData.establiment}">
		<input id="idEstabliment" name="idEstabliment" value="<c:out value="${formData.idEstabliment}"/>" type="hidden" />
	</c:if> 
	<c:if test="${not empty formData.olivicultor}">
		<input id="idOlivicultor" name="idOlivicultor" value="<c:out value="${formData.idOlivicultor}"/>" type="hidden" />
	</c:if> 
	<input id="action" name="action" value="delete" type="hidden" />
</form>

</div>

</body>
</html>