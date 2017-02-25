<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>


<html>
<head>

<script type="text/javascript">
	
		window.onload = function() {
			modificarVisibilidad();
		}
	
	</script>

<title><c:choose>
	<c:when test="${not empty formData.id}">
		<fmt:message key="manteniment.modificacio" />
	</c:when>
	<c:otherwise>
		<fmt:message key="manteniment.alta" />
	</c:otherwise>
</c:choose> <fmt:message key="establiment.tipusdemant" /></title>

<script type="text/javascript" src="js/displaytag.js"></script>
</head>
<body>

<c:choose>
	<c:when test="${not empty precondicion and precondicion==false}">
		<c:set var="mostrar" value="false" scope="request" />
	</c:when>
	<c:when test="${not empty esDoGestor}">
		<c:set var="mostrar" value="true" scope="request" />
	</c:when>
	<c:when
		test="${(not empty esProductor || not empty esEnvasador )and not empty formData.id}">  <%--and usuari.id == formData.usuari.id}"--%>
		<c:set var="mostrar" value="true" scope="request" />
	</c:when>
	<c:otherwise>
		<c:set var="mostrar" value="false" scope="request" />
	</c:otherwise>
</c:choose>
<c:if test="${mostrar==true}">
	<div class="establecimiento">
	<form id="formulario" action="EstablimentForm.html" method="post"
		class="seguit">
		<c:if test="${not empty formData.id}">
			<c:import url="comu/CampFormulari.jsp">
				<c:param name="tipus" value="hidden" />
				<c:param name="path" value="formData.id" />
				<c:param name="camp" value="id" />
			</c:import>
		</c:if> 
		<c:if test="${not empty formData.idOriginal}">
			<c:import url="comu/CampFormulari.jsp">
				<c:param name="tipus" value="hidden" />
				<c:param name="path" value="formData.idOriginal" />
				<c:param name="camp" value="idOriginal" />
			</c:import>
		</c:if> 
		<c:if test="${not empty formData.campanya}">
			<input type="hidden" id="campanya" name="campanyaId"
				value="<c:out value="${formData.campanya.id}"/>" />
		</c:if> 
		<c:if test="${empty formData.campanya}">
			<input type="hidden" id="campanya" name="campanyaId"
				value="<c:out value="${campaniaId}"/>" />
		</c:if> 
	<%--c:if test="${not empty esDoGestor}">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="select" />
			<c:param name="path" value="formData.usuariId" />
			<c:param name="title">
				<fmt:message key="establiment.camp.usuari" />
			</c:param>
			<c:param name="camp" value="usuariId" />
			<c:param name="required" value="required" />
			<c:param name="selectItems" value="usuarios" />
			<c:param name="selectItemsId" value="id" />
			<c:param name="selectItemsValue" value="usuari" />
			<c:param name="selectSelectedValue" value="${formData.usuari.id}" />
			<c:param name="clase" value="campoFormGrande conMargen" />
		</c:import>
	</c:if> 
	<c:if test="${empty esDoGestor}">
		<input type="hidden" id="usuariId" name="usuariId"
			value="${formData.usuari.id}" />
	</c:if>

	<div class="separadorH"></div--%>


		<c:if test="${not empty esDoGestor}">
			<c:import url="comu/CampFormulari.jsp">
				<c:param name="tipus" value="text" />
				<c:param name="path" value="formData.nom" />
				<c:param name="required" value="required" />
				<c:param name="title">
					<fmt:message key="establiment.camp.nom" />
				</c:param>
				<c:param name="camp" value="nom" />
				<c:param name="maxlength" value="128" />
				<c:param name="clase" value="campoFormTresCuartos conMargen" />
			</c:import>
	
			<c:import url="comu/CampFormulari.jsp">
				<c:param name="tipus" value="text" />
				<c:param name="path" value="formData.cif" />
				<c:param name="required" value="required" />
				<c:param name="title">
					<fmt:message key="establiment.camp.cif" />
				</c:param>
				<c:param name="camp" value="cif" />
				<c:param name="maxlength" value="16" />
				<c:param name="clase" value="campoFormPequeno" />
			</c:import>
		</c:if> 
		<c:if test="${empty esDoGestor}">
			<c:import url="comu/CampFormulari.jsp">
				<c:param name="tipus" value="text" />
				<c:param name="path" value="formData.nom" />
				<c:param name="required" value="required" />
				<c:param name="title">
					<fmt:message key="establiment.camp.nom" />
				</c:param>
				<c:param name="camp" value="nom" />
				<c:param name="maxlength" value="128" />
				<c:param name="clase" value="campoFormTresCuartos readOnly conMargen" />
				<c:param name="readonly" value="true" />
			</c:import>
	
			<c:import url="comu/CampFormulari.jsp">
				<c:param name="tipus" value="text" />
				<c:param name="path" value="formData.cif" />
				<c:param name="required" value="required" />
				<c:param name="title">
					<fmt:message key="establiment.camp.cif" />
				</c:param>
				<c:param name="camp" value="cif" />
				<c:param name="maxlength" value="16" />
				<c:param name="clase" value="campoFormPequeno readOnly" />
				<c:param name="readonly" value="true" />
			</c:import>
		</c:if>


	<div class="separadorH"></div>

	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.direccio" />
		<c:param name="required" value="required" />
		<c:param name="title">
			<fmt:message key="establiment.camp.direccio" />
		</c:param>
		<c:param name="camp" value="direccio" />
		<c:param name="maxlength" value="256" />
		<c:param name="clase" value="campoFormCompleto" />
	</c:import>

	<div class="separadorH"></div>

	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="select" />
		<c:param name="path" value="formData.municipiId" />
		<c:param name="title">
			<fmt:message key="establiment.camp.poblacio" />
		</c:param>
		<c:param name="camp" value="municipiId" />
		<c:param name="required" value="required" />
		<c:param name="selectItems" value="municipis" />
		<c:param name="selectItemsId" value="id" />
		<c:param name="selectItemsValue" value="nom" />
		<c:param name="selectSelectedValue" value="${formData.municipiId}" />
		<c:param name="clase" value="campoFormTresCuartos conMargen" />
	</c:import>
	
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.codiPostal" />
		<c:param name="required" value="required" />
		<c:param name="title">
			<fmt:message key="establiment.camp.codiPostal" />
		</c:param>
		<c:param name="camp" value="codiPostal" />
		<c:param name="maxlength" value="16" />
		<c:param name="clase" value="campoFormPequeno" />
	</c:import>

	<div class="separadorH"></div>

	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.email" />
		<c:param name="required" value="required" />
		<c:param name="title">
			<fmt:message key="establiment.camp.email" />
		</c:param>
		<c:param name="camp" value="email" />
		<c:param name="maxlength" value="128" />
		<c:param name="clase" value="campoFormGrande" />
	</c:import>

	<div class="separadorH"></div>

	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.telefon" />
		<c:param name="required" value="required" />
		<c:param name="title">
			<fmt:message key="establiment.camp.telefon" />
		</c:param>
		<c:param name="camp" value="telefon" />
		<c:param name="maxlength" value="16" />
		<c:param name="clase" value="campoFormMediano conMargen" />
	</c:import> <c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.fax" />
		<c:param name="required" value="required" />
		<c:param name="title">
			<fmt:message key="establiment.camp.fax" />
		</c:param>
		<c:param name="camp" value="fax" />
		<c:param name="maxlength" value="16" />
		<c:param name="clase" value="campoFormMediano" />
	</c:import>

	<div class="separadorH"></div>

	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.numeroTreballadors" />
		<c:param name="required" value="required" />
		<c:param name="title">
			<fmt:message key="establiment.camp.numeroTreballadors" />
		</c:param>
		<c:param name="camp" value="numeroTreballadors" />
		<c:param name="clase" value="campoFormGenerico80  margen120" />
	</c:import> <c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.superficie" />
		<c:param name="required" value="required" />
		<c:param name="title">
			<fmt:message key="establiment.camp.superficie" />
		</c:param>
		<c:param name="camp" value="superficie" />
		<c:param name="clase" value="campoFormGenerico80" />
	</c:import>

	<div class="separadorH"></div>

	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="select" />
		<c:param name="path" value="formData.unitatMesura" />
		<c:param name="title">
			<fmt:message key="establiment.camp.unitatMesura" />
		</c:param>
		<c:param name="camp" value="unitatMesura" />
		<c:param name="required" value="required" />
		<c:param name="selectItems" value="unidadesMedida" />
		<c:param name="selectItemsId" value="id" />
		<c:param name="selectItemsValue" value="nom" />
		<c:param name="selectSelectedValue" value="${formData.unitatMesura}" />
		<c:param name="clase" value="campoFormGenerico80" />
	</c:import>

	<div class="separadorH"></div>

	<c:if test="${not empty esDoGestor}">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="radio" />
			<c:param name="path" value="formData.tipusEstablimentId" />
			<c:param name="title">
				<fmt:message key="establiment.camp.tipusEstabliment" />
			</c:param>
			<c:param name="camp" value="tipusEstablimentId" />
			<c:param name="required" value="required" />
			<c:param name="selectItems" value="tipusEstabliments" />
			<c:param name="selectItemsId" value="id" />
			<c:param name="selectItemsValue" value="nom" />
			<c:param name="selectSelectedValue"
				value="${formData.tipusEstabliment.id}" />
			<c:param name="onclick" value="modificarVisibilidad()" />
		</c:import>
		<div class="separadorH"></div>
		<div class="etiqueta conMargen <c:if test="${not empty status.errorMessage}"> error</c:if>">
			<c:import url="comu/CampFormulari.jsp">
				<c:param name="tipus" value="checkbox" />
				<c:param name="path" value="formData.olivaTaula" />
				<c:param name="title">
					<fmt:message key="establiment.olivataula.productora" />
				</c:param>
				<c:param name="camp" value="olivaTaula" />
				<c:param name="onclick" value="modificarVisibilidad()" />
			</c:import>
		</div>
	</c:if> 
	<c:if test="${empty esDoGestor}">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="radio" />
			<c:param name="path" value="formData.tipusEstablimentId" />
			<c:param name="title">
				<fmt:message key="establiment.camp.tipusEstabliment" />
			</c:param>
			<c:param name="camp" value="tipusEstablimentId" />
			<c:param name="required" value="required" />
			<c:param name="selectItems" value="tipusEstabliments" />
			<c:param name="selectItemsId" value="id" />
			<c:param name="selectItemsValue" value="nom" />
			<c:param name="selectSelectedValue"
				value="${formData.tipusEstabliment.id}" />
			<c:param name="onclick" value="modificarVisibilidad()" />
			<c:param name="readonly" value="true" />
		</c:import>
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="hidden" />
			<c:param name="path" value="formData.tipusEstabliment.id" />
			<c:param name="camp" value="tipusEstablimentId" />
		</c:import>
		<div class="separadorH"></div>
		<div class="etiqueta conMargen <c:if test="${not empty status.errorMessage}"> error</c:if>">
			<c:import url="comu/CampFormulari.jsp">
				<c:param name="tipus" value="checkbox" />
				<c:param name="path" value="formData.olivaTaula" />
				<c:param name="title">
					<fmt:message key="establiment.olivataula.productora" />
				</c:param>
				<c:param name="camp" value="olivaTaula" />
				<c:param name="readonly" value="true" />
			</c:import>
			<c:import url="comu/CampFormulari.jsp">
				<c:param name="tipus" value="hidden" />
				<c:param name="path" value="formData.olivaTaula" />
				<c:param name="camp" value="olivaTaula" />
			</c:import>
		</div>
	</c:if>

	<div class="separadorH"></div>

	<div id="campos_envatafo">
	<c:if test="${not empty esDoGestor}">
		<div class="off" id="codiRBX">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="text" />
			<c:param name="path" value="formData.codiRB" />
			<c:param name="title">
				<fmt:message key="establiment.camp.codiRB" />
			</c:param>
			<c:param name="required" value="required" />
			<c:param name="maxlength" value="128" />
			<c:param name="camp" value="codiRB" />
			<c:param name="clase" value="campoFormGenerico80 conMargen" />
		</c:import>
		</div>
	</c:if> 
	<c:if test="${empty esDoGestor}">
		<div class="off" id="codiRBX">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="text" />
			<c:param name="path" value="formData.codiRB" />
			<c:param name="title">
				<fmt:message key="establiment.camp.codiRB" />
			</c:param>
			<c:param name="required" value="required" />
			<c:param name="maxlength" value="128" />
			<c:param name="camp" value="codiRB" />
			<c:param name="clase" value="campoFormGenerico80 readOnly conMargen" />
			<c:param name="readonly" value="true" />
		</c:import>
		</div>
	</c:if> 
	<c:if test="${not empty esDoGestor}">
		<div class="off" id="codiRCX">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="text" />
			<c:param name="path" value="formData.codiRC" />
			<c:param name="required" value="required" />
			<c:param name="maxlength" value="128" />
			<c:param name="title">
				<fmt:message key="establiment.camp.codiRC" />
			</c:param>
			<c:param name="camp" value="codiRC" />
			<c:param name="clase" value="campoFormGenerico80 conMargen" />
		</c:import>
		</div>
	</c:if> 
	<c:if test="${empty esDoGestor}">
		<div class="off" id="codiRCX">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="text" />
			<c:param name="path" value="formData.codiRC" />
			<c:param name="required" value="required" />
			<c:param name="maxlength" value="128" />
			<c:param name="title">
				<fmt:message key="establiment.camp.codiRC" />
			</c:param>
			<c:param name="camp" value="codiRC" />
			<c:param name="clase" value="campoFormGenerico80 readOnly conMargen" />
			<c:param name="readonly" value="true" />
		</c:import>
		</div>
	</c:if>
	<c:if test="${not empty esDoGestor}">
		<div class="off" id="codiRTX">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="text" />
			<c:param name="path" value="formData.codiRT" />
			<c:param name="required" value="required" />
			<c:param name="maxlength" value="128" />
			<c:param name="title">
				<fmt:message key="establiment.camp.codiRT" />
			</c:param>
			<c:param name="camp" value="codiRT" />
			<c:param name="clase" value="campoFormGenerico80 conMargen" />
		</c:import>
		</div>
	</c:if> 
	<c:if test="${empty esDoGestor}">
		<div class="off" id="codiRTX">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="text" />
			<c:param name="path" value="formData.codiRT" />
			<c:param name="required" value="required" />
			<c:param name="maxlength" value="128" />
			<c:param name="title">
				<fmt:message key="establiment.camp.codiRT" />
			</c:param>
			<c:param name="camp" value="codiRT" />
			<c:param name="clase" value="campoFormGenerico80 readOnly conMargen" />
			<c:param name="readonly" value="true" />
		</c:import>
		</div>
	</c:if>

	<div class="off" id="env_manualX">
	<div class="separadorH"></div>
	<div class="etiqueta conMargen <c:if test="${not empty status.errorMessage}"> error</c:if>">
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="checkbox" />
		<c:param name="path" value="formData.envassamentManual" />
		<c:param name="title">
			<fmt:message key="establiment.camp.env_manual" />
		</c:param>
		<c:param name="camp" value="envassamentManual" />
	</c:import></div>
	</div>

	<div class="off" id="eti_manualX">
	<div
		class="etiqueta <c:if test="${not empty status.errorMessage}"> error</c:if>">
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="checkbox" />
		<c:param name="path" value="formData.etiquetatManual" />
		<c:param name="title">
			<fmt:message key="establiment.camp.eti_manual" />
		</c:param>
		<c:param name="camp" value="etiquetatManual" />
	</c:import></div>
	</div>

	<div class="off" id="temperaturaMaximaPastaX">
	<div class="separadorH"></div>
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.temperaturaMaximaPasta" />
		<c:param name="required" value="required" />
		<c:param name="title">
			<fmt:message key="establiment.camp.temperaturaMaximaPasta" />
		</c:param>
		<c:param name="camp" value="temperaturaMaximaPasta" />
		<c:param name="clase" value="campoFormGenerico80" />
	</c:import></div>

	<div class="off" id="capacitatProduccioX">
	<div class="separadorH"></div>
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.capacitatProduccio" />
		<c:param name="required" value="required" />
		<c:param name="title">
			<fmt:message key="establiment.camp.capacitatProduccio" />
		</c:param>
		<c:param name="camp" value="capacitatProduccio" />
		<c:param name="aclaracio">
			<fmt:message key="consulta.aclaracio.unidadCampo" />
		</c:param>
		<c:param name="clase" value="campoFormGenerico80" />
	</c:import></div>

	</div>
	<div class="separadorH"></div>
	
	<div class="off" id="marcasX"><c:set var="marcas" value="${formData.marcasArray}" scope="request" /> 
		<c:if test="${not empty esDoGestor}">
			<c:import url="comu/CampFormulari.jsp">
				<c:param name="tipus" value="selectMultiple" />
				<c:param name="path" value="formData.marcasArray" />
				<c:param name="titleIzquierda" value="establiment.camp.marcas.disponibles" />
				<c:param name="titleDerecha" value="establiment.camp.marcas.seleccionadas" />
				<c:param name="camp" value="marcasArray" />
				<c:param name="clase" value="campoForm" />
				<c:param name="selectTamany" value="5" />
				<c:param name="selectItems" value="marcasArray" />
				<c:param name="selectItemsId" value="id" />
				<c:param name="selectItemsValue" value="nom" />
				<c:param name="selectSelectedItems" value="marcas" />
			</c:import>
		</c:if>
		
		<c:if test="${empty esDoGestor}">
				<label><fmt:message key="establiment.camp.marcas.disponibles" /></label>
				<div  class="campoFormTresCuartos conMargen">
					<c:forEach items="${marcasArray}" var="marcaArray" >
						<c:forEach items="${marcas}" var="marca" >
							<c:if test="${marcaArray.id == marca}">
								${marcaArray.nom}<br/>
							</c:if>
						</c:forEach>
					</c:forEach>
				</div>
				<div style="display:none;">
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="selectMultiple" />
						<c:param name="path" value="formData.marcasArray" />
						<c:param name="titleIzquierda" value="establiment.camp.marcas.disponibles" />
						<c:param name="titleDerecha" value="establiment.camp.marcas.seleccionadas" />
						<c:param name="camp" value="marcasArray" />
						<c:param name="clase" value="campoForm" />
						<c:param name="selectTamany" value="5" />
						<c:param name="selectItems" value="marcasArray" />
						<c:param name="selectItemsId" value="id" />
						<c:param name="selectItemsValue" value="nom" />
						<c:param name="selectSelectedItems" value="marcas" />
					</c:import>
				</div>
		</c:if>
	</div>
	<div class="separadorH"></div>

	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.nombreSolicitant" />
		<c:param name="required" value="required" />
		<c:param name="title">
			<fmt:message key="establiment.camp.nomSolicitant" />
		</c:param>
		<c:param name="camp" value="nombreSolicitant" />
		<c:param name="maxlength" value="64" />
		<c:param name="clase" value="campoFormTresCuartos conMargen" />
	</c:import> <c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.nifSolicitant" />
		<c:param name="required" value="required" />
		<c:param name="title">
			<fmt:message key="establiment.camp.nifSolicitant" />
		</c:param>
		<c:param name="camp" value="nifSolicitant" />
		<c:param name="maxlength" value="16" />
		<c:param name="clase" value="campoFormPequeno" />
	</c:import>


	<div class="separadorH"></div>

	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.perfilSolicitant" />
		<c:param name="required" value="required" />
		<c:param name="title">
			<fmt:message key="establiment.camp.perfilSolicitant" />
		</c:param>
		<c:param name="camp" value="perfilSolicitant" />
		<c:param name="maxlength" value="64" />
		<c:param name="clase" value="campoFormTresCuartos conMargen" />
	</c:import> <c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.telefonSolicitant" />
		<c:param name="required" value="required" />
		<c:param name="title">
			<fmt:message key="establiment.camp.telefonSolicitant" />
		</c:param>
		<c:param name="camp" value="telefonSolicitant" />
		<c:param name="maxlength" value="16" />
		<c:param name="clase" value="campoFormPequeno" />
	</c:import> 
	
	<div class="separadorH"></div>
		
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.nomResponsable" />
		<c:param name="required" value="required" />
		<c:param name="title">
			<fmt:message key="establiment.camp.nomResponsable" />
		</c:param>
		<c:param name="camp" value="nomResponsable" />
		<c:param name="maxlength" value="128" />
		<c:param name="clase" value="campoFormTresCuartos conMargen" />
	</c:import> 
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.cifResponsable" />
		<c:param name="required" value="required" />
		<c:param name="title">
			<fmt:message key="establiment.camp.cifResponsable" />
		</c:param>
		<c:param name="camp" value="cifResponsable" />
		<c:param name="maxlength" value="16" />
		<c:param name="clase" value="campoFormPequeno" />
	</c:import>
	<c:if test="${not empty formData.id && not empty esDoGestor }">


	<div class="separadorH"></div>

		<div
			class="etiqueta <c:out value="${param.required}"/><c:if test="${not empty status.errorMessage}"> error</c:if>">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="checkbox" />
			<c:param name="path" value="formData.actiu" />
			<c:param name="title">
				<fmt:message key="establiment.camp.actiu" />
			</c:param>
			<c:param name="camp" value="actiu" />
			<c:param name="onchange" value="cambioEstado()" />
		</c:import></div>
		<input type="hidden" id="campoCambioEstado" name="cambioEstadoActivo"
			value="false" />
	</c:if> <c:if test="${not empty formData.id && empty esDoGestor}">
		<c:import url="comu/CampFormulari.jsp">
			<c:param name="tipus" value="hidden" />
			<c:param name="path" value="formData.actiu" />
			<c:param name="title">
				<fmt:message key="establiment.camp.actiu" />
			</c:param>
			<c:param name="camp" value="actiu" />
		</c:import>
	</c:if>

	<div class="separadorH"></div>

	<div id="observacionesForm"
		class="campoForm <c:out value="${param.required}"/><c:if test="${not empty status.errorMessage}"> error</c:if>">
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="textarea" />
		<c:param name="path" value="formData.observacions" />
		<c:param name="title">
			<fmt:message key="establiment.camp.observacions" />
		</c:param>
		<c:param name="camp" value="observacions" />
	</c:import></div>

	<div class="separadorH"></div>
		
	<c:import url="comu/CampFormulari.jsp">
		<c:param name="tipus" value="text" />
		<c:param name="path" value="formData.nomDipositFictici" />
		<c:param name="required" value="required" />
		<c:param name="title">
			<fmt:message key="establiment.camp.nomDipositFictici" />
		</c:param>
		<c:param name="camp" value="nomDipositFictici" />
		<c:param name="maxlength" value="128" />
		<c:param name="clase" value="campoFormTresCuartos conMargen" />
	</c:import> 


	<div class="separadorH"></div>

	<c:if test="${formData.id != null && not empty formData.id && not empty esDoGestor}">
		<c:if test="${not empty llistatDocuments}">
	
			<div class="campoForm">
				<label for="documents">
					<fmt:message key="documentInspeccio.camp.documentsInspeccio" />
				</label>
				<div class="separadorH"></div>
				<div id="listadoEstrecho"><%-- Tabla de resultados --%>
					<div class="layoutSombraTabla">
						<div class="rellenoInf"></div>
						<div class="rellenoIzq"></div>
						<div class="rellenoDer"></div>
						<div class="supDer"></div>
						<div class="supIzq"></div>
						<div class="infIzq"></div>
						<div class="infDer"></div>
	
						<display:table name="llistatDocuments" requestURI="" id="documentInspeccio" pagesize="" sort="list" cellpadding="0" cellspacing="0"	class="listadoEstrecho selectable">
							<display:column titleKey="documentInspeccio.camp.data" headerClass="" sortable="true" sortProperty="titol">
								<a href="DocumentInspeccioForm.html?id=<c:out value="${documentInspeccio.id}"/>">
									<c:out value="${documentInspeccio.data}" />
								</a>
							</display:column>
							<display:column titleKey="documentInspeccio.camp.tipusInspeccio" headerClass="ancho100" sortable="true">
								<fmt:message key="documentInspeccio.tipusInspeccio.${documentInspeccio.tipus}" />
							</display:column>
							<display:column titleKey="documentInspeccio.camp.tamany" headerClass="ancho100" sortable="true">
								<c:out value="${documentInspeccio.arxiuObject.normalizeSize}" />
							</display:column>
							<display:column sortable="false" headerClass="ancho32 final">
								<a href="ArxiuMostrar.html?id=<c:out value="${documentInspeccio.arxiu}"/>&download=true">
									<div class="iconoVer" title="<fmt:message key="document.ver"/>"></div>
								</a>
							</display:column>
						</display:table>
					</div>
				</div>
			</div>
	
		</c:if>
	
		<div class="separadorH"></div>
	
	
		<div class="btnLargo203" onmouseover="underline('enlaceCrearForm')"
			onmouseout="underline('enlaceCrearForm')"
			onclick="document.formularioDocumentInspeccio.submit();"><a
			id="enlaceCrearForm" href="javascript:void(0);"><fmt:message
			key="manteniment.creardocument" /></a></div>
	</c:if>
	

	<div class="separadorH"></div>

	<div class="botonesForm"><c:if test="${not empty formData.id}">
		<c:if test="${empty formData.dataBaixa}">
			<div id="guardarForm" class="btnCorto"
				onclick="if(confirm('<fmt:message key="manteniment.modificar.confirm"/>')){onSubmitMultiple_marcasArray();submitForm('formulario')}"
				onmouseover="underline('enlaceGuardarForm')"
				onmouseout="underline('enlaceGuardarForm')"><a
				id="enlaceGuardarForm" href="javascript:void(0);"><fmt:message
				key="manteniment.guardar" /></a></div>
		</c:if>
	</c:if> <c:if test="${empty formData.id}">
		<c:if test="${empty formData.dataBaixa}">
			<div id="guardarForm" class="btnCorto"
				onclick="onSubmitMultiple_marcasArray();submitForm('formulario')"
				onmouseover="underline('enlaceGuardarForm')"
				onmouseout="underline('enlaceGuardarForm')"><a
				id="enlaceGuardarForm" href="javascript:void(0);"><fmt:message
				key="manteniment.guardar" /></a></div>
		</c:if>
	</c:if> <c:choose>
		<c:when test="${not empty esDoGestor}">
			<div id="cancelarForm" class="btnCorto"
				onmouseover="underline('enlaceCancelarForm')"
				onmouseout="underline('enlaceCancelarForm')"
				onclick="document.location ='Establiment.html';"><a
				id="enlaceCancelarForm" href="javascript:void(0);"><fmt:message
				key="manteniment.tornar" /></a></div>
		</c:when>
		<c:when test="${not empty esProductor || not empty esEnvasador}">
			<div id="cancelarForm" class="btnCorto"
				onmouseover="underline('enlaceCancelarForm')"
				onmouseout="underline('enlaceCancelarForm')"
				onclick="document.location ='EstablimentForm.html?id=<c:out value='${formData.id}'/>';">
			<a id="enlaceCancelarForm" href="javascript:void(0);"><fmt:message
				key="manteniment.tornar" /></a></div>
		</c:when>
	</c:choose> <c:if test="${not empty formData.id && not empty esDoGestor}">
		<c:if test="${empty formData.dataBaixa}">
			<input id="action" name="action" value="delete" type="hidden" />
			<div id="eliminarForm" class="btnCorto"
				onmouseover="underline('enlaceBorrarForm')"
				onmouseout="underline('enlaceBorrarForm')"
				onclick="submitFormConfirm('deleteForm','<fmt:message key="manteniment.esborrar.confirm"/>');">
			<a id="enlaceBorrarForm" href="javascript:void(0);"><fmt:message
				key="manteniment.esborrar" /></a></div>
		</c:if>
	</c:if></div>

	</form>
	
	<form name="formularioDocumentInspeccio" action="DocumentInspeccioForm.html" class="seguit">
		<input id="idEstabliment" name="idEstabliment" value="<c:out value="${formData.id}"/>" type="hidden" />
	</form>
	<form id="deleteForm" action="Establiment.html" method="post"
		class="seguit"
		onsubmit="return confirm('<fmt:message key="manteniment.estasegur"/>')">
	<input id="id" name="id" value="<c:out value="${formData.id}"/>"
		type="hidden" /> <input id="action" name="action" value="delete"
		type="hidden" /></form>

	</div>
</c:if>

<!-- Colores en tablas -->
<script type="text/javascript">
	jQuery(document).ready(function(){
		setEstilosTabla();
	})
</script>

<script type="text/javascript" language="javascript">
	// <![CDATA[
				
		function modificarVisibilidad() {
						
			var tafona = document.getElementById("<c:out value='${tipusEstablimentTafona}'/>");
			var envasadora = document.getElementById("<c:out value='${tipusEstablimentEnvasadora}'/>");
			var envatafo = document.getElementById("<c:out value='${tipusEstablimentTafonaEnvasadora}'/>");
			var olivaTaula = document.getElementById("olivaTaula");
			
			if (olivaTaula != null && olivaTaula.checked){
				document.getElementById('codiRTX').className = "on";
			} else {
				document.getElementById('codiRTX').className = "off";
			}
			if (tafona != null && tafona.checked){
				document.getElementById('codiRBX').className = "on";
				document.getElementById('codiRCX').className = "off";
				document.getElementById('temperaturaMaximaPastaX').className = "on";
				document.getElementById('capacitatProduccioX').className = "on";
				document.getElementById('env_manualX').className = "off";
				document.getElementById('eti_manualX').className = "off";
				document.getElementById('marcasX').className = "off";
				if(envasadora!= null){
					envasadora.checked = false;
				}
				if(envatafo != null){
					envatafo.checked = false;
				}
				
			}else if (envasadora != null && envasadora.checked) {
				document.getElementById('codiRBX').className = "off";
				document.getElementById('codiRCX').className = "on";
				document.getElementById('temperaturaMaximaPastaX').className = "off";
				document.getElementById('capacitatProduccioX').className = "on";
				document.getElementById('env_manualX').className = "on";
				document.getElementById('eti_manualX').className = "on";
				document.getElementById('marcasX').className = "on";
				if(tafona != null){
					tafona.checked = false;
				}				
				if(envatafo != null){
					envatafo.checked = false;
				}				
			} else if (envatafo != null && envatafo.checked) {
				document.getElementById('codiRBX').className = "on";
				document.getElementById('codiRCX').className = "on";
				document.getElementById('temperaturaMaximaPastaX').className = "on";
				document.getElementById('capacitatProduccioX').className = "on";
				document.getElementById('env_manualX').className = "on";
				document.getElementById('eti_manualX').className = "on";
				document.getElementById('marcasX').className = "on";
				if(tafona != null){
					tafona.checked = false;
				}
				if(envasadora != null){
					envasadora.checked = false;
				}				
			} else {
				document.getElementById('codiRBX').className = "off";
				document.getElementById('codiRCX').className = "off";
				document.getElementById('temperaturaMaximaPastaX').className = "off";
				document.getElementById('capacitatProduccioX').className = "off";
				document.getElementById('env_manualX').className = "off";
				document.getElementById('eti_manualX').className = "off";
				document.getElementById('marcasX').className = "off";
			}	
		}
		
					
					
	// ]]>
	</script>


</body>
</html>