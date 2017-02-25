<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html>
<head>
    <title>
		<fmt:message key="manteniment.alta"/>
		<fmt:message key="partidaOli.tipusdemant"/>
    </title>
    <script type="text/javascript" src="js/form.js"></script>
	<script type="text/javascript" src="js/selectbox.js"></script>
	<link rel="stylesheet" type="text/css" href="css/general.css" />
</head>
<body>
	<c:if test="${empty param.ok}">
	    <form id="formulario" action="PartidaOliPopupForm.html" method="post" class="seguit extended">
	    	
	    	<input type="hidden" id="establimentId" name="establimentId" value="${establimentId}"/>
	    	
	    	<div class="separadorH"></div>
	    	
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
	        
	        <c:import url="comu/CampFormulari.jsp">
				<c:param name="tipus" value="select" />
				<c:param name="path" value="formData.categoriaId" />
				<c:param name="required" value="required" />
				<c:param name="title">
					<fmt:message key="partidaOli.camp.categoria" />
				</c:param>
				<c:param name="camp" value="categoriaId" />
				<c:param name="name" value="categoriaId" />
				<c:param name="selectItems" value="categoriesOli" />
				<c:param name="selectItemsId" value="id" />
				<c:param name="selectItemsValue" value="nom" />
				<c:param name="selectSelectedValue" value="${formData.categoriaId}"/>
				<c:param name="clase" value="campoFormGrande conMargen" />
				<c:param name="value" value="${formData.categoriaId}"/>
			</c:import>
		    <input type="hidden" name="esActiu" id="esActiu" value="S"/>
		    
		    <div class="separadorH"></div>
			<c:import url="comu/CampFormulari.jsp">
				<c:param name="tipus" value="checkbox" />
				<c:param name="path" value="formData.olivicultorEsPropietari" />
				<c:param name="title">
					<fmt:message key="partidaOli.camp.olivicultorPropietari" />
				</c:param>
				<c:param name="camp" value="olivicultorEsPropietari" />
			</c:import>
			
			<div class="separadorH"></div>
	        
	    	<div class="botonesForm">
				<div id="guardarForm" class="btnCorto"
					onclick="submitForm('formulario')"
					onmouseover="underline('enlaceGuardarForm')"
					onmouseout="underline('enlaceGuardarForm')">
					<a id="enlaceGuardarForm" href="javascript:void(0);"><fmt:message key="manteniment.guardar" /></a>
				</div>
			</div>
	    </form>
    </c:if>
    <c:if test="${not empty param.ok}">
    	<script>
    		opener.actualitzarPartidesOli(self);
    	</script>
    </c:if>
</body>
</html>
