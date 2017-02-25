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
		<fmt:message key="informeCampanya.tipusdemant" />
	</title>

	<script type="text/javascript" src="js/form.js"></script>


	<script type="text/javascript">
	
		window.onload = function() {
			modificarVisibilidad();
		}
	
	</script>

</head>
<body>

<form id="formulario" name="InformeCampanyaForm" action="InformeCampanyaForm.html" method="post" class="extended seguit" onsubmit="" enctype="multipart/form-data">
	<c:if test="${not empty formData.id}">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="hidden" />
			<c:param name="path" value="formData.id" />
			<c:param name="camp" value="id" />
		</c:import>
	</c:if> 
	
	<div class="separadorH"></div>
	
	<div class="off" id="tipusX">
	<div class="separadorH"></div>
	<div class="etiqueta conMargen <c:if test="${not empty status.errorMessage}"> error</c:if>">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="checkbox" />
			<c:param name="path" value="formData.tipus" />
			<c:param name="title">
				<fmt:message key="informeCampanya.camp.tipus" />
			</c:param>
			<c:param name="camp" value="tipus" />
			<c:param name="name" value="tipus" />
			<c:param name="required" value="required" />
			<c:param name="onclick" value="modificarVisibilidad()" />
		</c:import></div>
	</div>
	
	<div class="separadorH"></div>
	
	<div id="campanyaX">
		<h4><fmt:message key="informeCampanya.tipus.produccio" /></h4>
		<div class="separadorH"></div>
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="select" />
			<c:param name="path" value="formData.campanya" />
			<c:param name="title">
				<fmt:message key="informeCampanya.camp.campanya" />
			</c:param>
			<c:param name="camp" value="campo_campanya" />
			<c:param name="name" value="campanya" />
			<c:param name="required" value="required" />
			<c:param name="selectItems" value="campanyes" />
			<c:param name="selectItemsId" value="id" />
			<c:param name="selectItemsValue" value="nom" />
			<c:param name="selectSelectedValue" value="${formData.campanya}" />
			<c:param name="clase" value="campoFormNormal conMargen" />
		</c:import>
	</div>
	
	<div id="anyX">
		<h4><fmt:message key="informeCampanya.tipus.comercialitzacio" /></h4>
		<div class="separadorH"></div>
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="select" />
			<c:param name="path" value="formData.any" />
			<c:param name="title">
				<fmt:message key="informeCampanya.camp.any" />
			</c:param>
			<c:param name="camp" value="campo_any" />
			<c:param name="name" value="any" />
			<c:param name="required" value="required" />
			<c:param name="selectItems" value="anys" />
			<c:param name="selectItemsId" value="id" />
			<c:param name="selectItemsValue" value="nom" />
			<c:param name="selectSelectedValue" value="${formData.any}" />
			<c:param name="clase" value="campoFormNormal conMargen" />
		</c:import>
	</div>
	
	<div class="separadorH"></div>
	
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="hidden" />
		<c:param name="path" value="formData.errorImagenes" />
		<c:param name="camp" value="errorImagenes" />
	</c:import> 
	<c:set var="arxiuDocument" value="${formData.arxiuDocument}" scope="request" /> 
	<c:set var="imatgeDocument" value="${formData.imatgeDocument}" scope="request" /> 
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="fileImage" />
		<c:param name="path" value="formData.imatgeDocument" />
		<c:param name="required" value="required" />
		<c:param name="title"><fmt:message key="informeCampanya.camp.document" /></c:param>
		<c:param name="camp" value="imatgeDocument" />
		<c:param name="arxiu" value="arxiuDocument" />
		<c:param name="height" value="150" />
		<c:param name="arxiuId" value="imatgePrincipal" />
		<c:param name="nomArxiu" value="Document.pdf" />
	</c:import> 
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="hidden" />
		<c:param name="path" value="formData.document" />
		<c:param name="camp" value="document" />
	</c:import>

	<div class="separadorH"></div>
	<br></br>

	<div class="botonesForm">
		<c:choose>
			<c:when test="${not empty formData.id}">
				<div id="guardarForm" class="btnCorto"
					onclick="if(confirm('<fmt:message key="manteniment.modificar.confirm"/>')){submitForm('formulario')}"
					onmouseover="underline('enlaceGuardarForm')"
					onmouseout="underline('enlaceGuardarForm')"><a
					id="enlaceGuardarForm" href="javascript:void(0);"><fmt:message
					key="manteniment.guardar" /></a></div>
			</c:when>
			<c:otherwise>
				<div id="guardarForm" class="btnCorto"
					onclick="submitForm('formulario')"
					onmouseover="underline('enlaceGuardarForm')"
					onmouseout="underline('enlaceGuardarForm')"><a
					id="enlaceGuardarForm" href="javascript:void(0);"><fmt:message
					key="manteniment.guardar" /></a></div>
			</c:otherwise>
		</c:choose>
	
		<div class="btnCorto" 
			onmouseover="underline('enlaceTornarForm')"
			onmouseout="underline('enlaceTornarForm')" 
			onclick="document.location ='InformeCampanya.html';"><a
			id="enlaceTornarForm" href="javascript:void(0);"><fmt:message
			key="proces.tornar" /></a></div>
	
		<c:if test="${empty formData.id}">
			<div id="cancelarForm" class="btnCorto"
				onmouseover="underline('enlaceCancelarForm')"
				onmouseout="underline('enlaceCancelarForm')"
				onclick="document.location ='InformeCampanya.html';"><a
				id="enlaceCancelarForm" href="javascript:void(0);"><fmt:message
				key="proces.cancelar" /></a></div>
		</c:if>
		<c:if test="${not empty formData.id}">
			<input id="action" name="action" value="delete" type="hidden" />
			<div id="eliminarForm" class="btnCorto"
				onmouseover="underline('enlaceBorrarForm')"
				onmouseout="underline('enlaceBorrarForm')"
				onclick="submitFormConfirm('deleteForm','<fmt:message key="manteniment.esborrar.confirm"/>');">
			<a id="enlaceBorrarForm" href="javascript:void(0);"><fmt:message
				key="manteniment.esborrar" /></a></div>
		</c:if>
	
	</div>

</form>

<form id="deleteForm" action="InformeCampanya.html" method="post"
	class="seguit"
	onsubmit="return confirm('<fmt:message key="manteniment.estasegur"/>')">
	<input id="id" name="id" value="<c:out value="${formData.id}"/>" type="hidden" /> 
	<input id="action" name="action" value="delete" type="hidden" />
</form>


	<script type="text/javascript" language="javascript">
	// <![CDATA[
				
		function modificarVisibilidad() {
			var tipus = document.getElementById("tipus");
			if (tipus != null && tipus.checked){
				document.getElementById("anyX").style.display = "block";
				document.getElementById("campanyaX").style.display = "none";
				document.getElementById("campo_idCampanya").value = "";
			} else {
				document.getElementById("anyX").style.display = "none";
				document.getElementById("campanyaX").style.display = "block";
				document.getElementById("campo_any").value = "";
			}
						
		}
				
	// ]]>
	</script>

</body>
</html>