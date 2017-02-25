<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

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
		<fmt:message key="producte.tipusdemant"/>
    </title>
    <script type="text/javascript" src="js/form.js"></script>
	<script type="text/javascript" src="js/selectbox.js"></script>
	<link rel="stylesheet" type="text/css" href="css/general.css" />
</head>
<body>
    <form id="formulario" action="ProducteForm.html" method="post" class="seguit extended">
    	<c:if test="${not empty formData.id}">
			<c:import url="comu/CampFormulari.jsp">
				<c:param name="tipus" value="hidden" />
				<c:param name="path" value="formData.id" />
				<c:param name="camp" value="id" />
			</c:import>
		</c:if>
    
    	<input type="hidden" id="establimentId" name="establimentId" value="${establimentId}"/>
   	 			
    	<c:import url="comu/CampFormulari.jsp">
    	    <c:param name="tipus" value="text"/>
    	    <c:param name="path" value="formData.nom"/>
            <c:param name="required" value="required"/>
            <c:param name="title"><fmt:message key="partidaOli.camp.nom"/></c:param>
            <c:param name="camp" value="nom"/>
            <c:param name="maxlength" value="64" />
            <c:param name="clase" value="campoFormGrande conMargen" />
        </c:import>
        
        <div class="separadorH"></div>
        
        <c:if test="${not empty formData.id}">
			<div class="separadorH"></div>
			<c:import url="comu/CampFormulari.jsp">
				<c:param name="tipus" value="checkbox" />
				<c:param name="path" value="formData.actiu" />
				<c:param name="title">
					<fmt:message key="partidaOli.camp.actiu" />
				</c:param>
				<c:param name="camp" value="actiu" />
			</c:import>
		</c:if>
		<c:if test="${empty formData.id}">
			<input type="hidden" name="actiu" id="actiu" value="S"/>
		</c:if>
		
		<div class="separadorH"></div>
        
    	<div class="botonesForm">
    		<c:if test="${not empty formData.id}">
				<div id="guardarForm" class="btnCorto"
					onclick="if(confirm('<fmt:message key="manteniment.modificar.confirm"/>')){submitForm('formulario')}"
					onmouseover="underline('enlaceGuardarForm')"
					onmouseout="underline('enlaceGuardarForm')">
					<a id="enlaceGuardarForm" href="javascript:void(0);"><fmt:message key="manteniment.guardar" /></a>
				</div>
			</c:if> 
			<c:if test="${empty formData.id}">
				<div id="guardarForm" class="btnCorto"
					onclick="submitForm('formulario')"
					onmouseover="underline('enlaceGuardarForm')"
					onmouseout="underline('enlaceGuardarForm')">
					<a id="enlaceGuardarForm" href="javascript:void(0);"><fmt:message key="manteniment.guardar" /></a>
				</div>
			</c:if> 
			<c:if test="${not empty esProductor || not empty esEnvasador}">
				<div id="cancelarForm" class="btnCorto"
					onmouseover="underline('enlaceCancelarForm')"
					onmouseout="underline('enlaceCancelarForm')"
					onclick="document.location ='Producte.html';">
					<a id="enlaceCancelarForm" href="javascript:void(0);"><fmt:message key="manteniment.tornar" /></a>
				</div>
			</c:if> 
			<c:if test="${not empty formData.id && (not empty esProductor || not empty esEnvasador)}">
				<div id="eliminarForm" class="btnCorto"
					onmouseover="underline('enlaceBorrarForm')"
					onmouseout="underline('enlaceBorrarForm')"
					onclick="submitFormConfirm('deleteForm','<fmt:message key="manteniment.esborrar.confirm"/>');">
					<a id="enlaceBorrarForm" href="javascript:void(0);"><fmt:message key="manteniment.esborrar" /></a>
				</div>
			</c:if>
		</div>
    </form>
	<form id="deleteForm" action="Producte.html" method="post" class="seguit"
		onsubmit="return confirm('<fmt:message key="manteniment.estasegur"/>')">
		<input id="id" name="id" value="<c:out value="${formData.id}"/>" type="hidden"/>
		<input id="action" name="action" value="delete" type="hidden" />
	</form>
</body>
</html>
	        