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
<title><fmt:message
	key="tancamentLlibres.introduccioExistencies" /></title>

<script type="text/javascript" src="dwr/interface/contenidorService.js"></script>
<script type="text/javascript" src="dwr/interface/processosService.js"></script>
<script type="text/javascript" src="dwr/engine.js"></script>
<script type="text/javascript" src="dwr/util.js"> </script>

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




<script type="text/javascript" language="javascript">
// <![CDATA[
	
	function litrosKilos() {
		var litros = document.getElementById('litros').value;
		var kilos = litros * 0.916;
		document.getElementById('kilos').value = kilos.toFixed(3);
	}
	
	function kilosLitros() {
		var kilos = document.getElementById('kilos').value;
		var litros = kilos / 0.916;
		document.getElementById('litros').value = litros.toFixed(3);
	}
	
// ]]>
</script>

</head>
<body>

<form id="formulario" name="dipositForm"
	action="TancamentLlibresDipositForm.html" method="post"
	class="extended seguit" onsubmit=""><c:import
	url="comu/CampFormulari.jsp">
	<c:param name="tipus" value="calendar" />
	<c:param name="path" value="formData.data" />
	<c:param name="title">
		<fmt:message key="lot.camp.data" />
	</c:param>
	<c:param name="camp" value="campo_data" />
	<c:param name="name" value="data" />
	<c:param name="maxlength" value="10" />
	<c:param name="required" value="required" />
	<c:param name="aclaracio">
		<fmt:message key="proces.aclaracio.formatdata" />
	</c:param>
	<c:param name="clase" value="conMargen campoForm165" />
</c:import>

<div class="separadorH"></div>

<c:import url="comu/CampFormulari.jsp">
	<c:param name="tipus" value="hidden" />
	<c:param name="path" value="formData.id" />
	<c:param name="camp" value="id" />
	<c:param name="name" value="id" />
	<c:param name="value" value="${formData.id}" />
</c:import>

<div class="cajaTabla">
<div class="layoutSombraTabla">
<div class="rellenoInf"></div>
<div class="rellenoIzq"></div>
<div class="rellenoDer"></div>
<div class="supDer"></div>
<div class="supIzq"></div>
<div class="infIzq"></div>
<div class="infDer"></div>
<table cellpadding="0" cellspacing="0" class="listadoEstrecho noRoll">
	<thead>
		<tr>
			<th class="unicoHead"><c:out value=" ${formData.codiAssignat}" />
			</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td class="conversor">
			<fieldset><c:if
				test="${formData.establiment.unitatMesura == 'l'}">
				<div class="conv"><c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="text" />
					<c:param name="path" value="formData.litros" />
					<c:param name="required" value="required" />
					<c:param name="title">
						<fmt:message key="proces.elaboracioOli.camp.litres" />
					</c:param>
					<c:param name="camp" value="litros" />
					<c:param name="name" value="litros" />
					<c:param name="clase" value="campoFormMediano conMargen" />
					<c:param name="onkeyup" value="litrosKilos()" />
				</c:import></div>
				<div class="conv"><c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="text" />
					<c:param name="path" value="formData.kilos" />
					<c:param name="title">
						<fmt:message key="proces.elaboracioOli.camp.kilos" />
					</c:param>
					<c:param name="camp" value="kilos" />
					<c:param name="name" value="kilos" />
					<c:param name="clase" value="campoFormMediano readOnly" />
					<c:param name="readonly" value="true" />
				</c:import></div>
			</c:if> <c:if test="${formData.establiment.unitatMesura == 'k'}">
				<div class="conv"><c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="text" />
					<c:param name="path" value="formData.kilos" />
					<c:param name="required" value="required" />
					<%--c:param name="maxlength" value="10"/--%>
					<c:param name="title">
						<fmt:message key="proces.elaboracioOli.camp.kilos" />
					</c:param>
					<c:param name="camp" value="kilos" />
					<c:param name="name" value="kilos" />
					<%--c:param name="aclaracio"><fmt:message key="proces.aclaracio.kilos"/></c:param--%>
					<c:param name="clase" value="campoFormMediano conMargen" />
					<c:param name="onkeyup" value="kilosLitros()" />
				</c:import></div>
				<div class="conv"><c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="text" />
					<c:param name="path" value="formData.litros" />
					<%--c:param name="required" value="required"/--%>
					<%--c:param name="maxlength" value="10"/--%>
					<c:param name="title">
						<fmt:message key="proces.elaboracioOli.camp.litres" />
					</c:param>
					<c:param name="camp" value="litros" />
					<c:param name="name" value="litros" />
					<%--c:param name="aclaracio"><fmt:message key="proces.aclaracio.litres"/></c:param--%>
					<c:param name="clase" value="campoFormMediano readOnly" />
					<c:param name="readonly" value="true" />
				</c:import></div>
			</c:if></fieldset>
			</td>
		</tr>
	</tbody>
</table>
</div>
</div>

<div class="separadorH"></div>

<!-- OB 20091202  -  No ho posem perque ho miren als llibres físics  -->
<!-- 
       	<c:set var="varietats" value="${formData.varietatsOlivaArray}" scope="request"/>
       	<c:import url="comu/CampFormulari.jsp">
    	    <c:param name="tipus" value="selectMultiple"/>
    	    <c:param name="path" value="formData.varietatsOlivaArray"/>
            <c:param name="titleIzquierda" value="lot.camp.varietatsOliva.disponibles"/>
            <c:param name="titleDerecha" value="lot.camp.varietatsOliva.seleccionats"/>
            <c:param name="camp" value="varietatsOlivaArray"/>
            <c:param name="clase" value="campoForm"/>
            <c:param name="selectTamany" value="5"/>
            <c:param name="selectItems" value="varietatsOlivaArray"/>
            <c:param name="selectItemsId" value="id"/>
            <c:param name="selectItemsValue" value="nom"/>
            <c:param name="selectSelectedItems" value="varietats"/>
        </c:import>
       	
       	<div class="separadorH"></div>
       	 --> <c:import url="comu/CampFormulari.jsp">
	<c:param name="tipus" value="select" />
	<c:param name="path" value="formData.idCategoriaOli" />
	<c:param name="required" value="required" />
	<c:param name="title">
		<fmt:message key="lot.camp.categoriaOli" />
	</c:param>
	<c:param name="camp" value="campo_categoriaOli" />
	<c:param name="name" value="idCategoriaOli" />
	<c:param name="selectItems" value="categoriasOli" />
	<c:param name="selectItemsId" value="id" />
	<c:param name="selectItemsValue" value="nom" />
	<c:param name="selectSelectedValue" value="${formData.idCategoriaOli}" />
	<c:param name="clase" value="campoFormMediano conMargen" />
</c:import> <!-- OB 20091202  -  No ho posem perque ho miren als llibres físics  -->
<!--
		<c:import url="comu/CampFormulari.jsp">
    	    <c:param name="tipus" value="select"/>
    	    <c:param name="path" value="formData.idVarietatOli"/>
    	    <c:param name="required" value="required"/>
            <c:param name="title"><fmt:message key="proces.elaboracioOli.camp.varietat"/></c:param>
            <c:param name="camp" value="campo_varietatOli"/>
            <c:param name="name" value="idVarietatOli"/>
            <c:param name="selectItems" value="variedadesOli"/>
            <c:param name="selectItemsId" value="id"/>
            <c:param name="selectItemsValue" value="nom"/>
            <c:param name="selectSelectedValue" value="${formData.idVarietatOli}"/>
            <c:param name="clase" value="campoFormMediano conMargen"/>
        </c:import>
       	--> <c:import url="comu/CampFormulari.jsp">
	<c:param name="tipus" value="text" />
	<c:param name="path" value="formData.acidesa" />
	<c:param name="title">
		<fmt:message key="lot.camp.acidesa" />
	</c:param>
	<c:param name="camp" value="campo_acidesa" />
	<c:param name="name" value="acidesa" />
	<c:param name="required" value="required" />
	<%--c:param name="maxlength" value="10"/--%>
	<c:param name="clase" value="campoFormMediano conMargen" />
</c:import>

<div class="separadorH"></div>

<div id="observacionesForm"
	class="campoForm <c:out value="${param.required}"/><c:if test="${not empty status.errorMessage}"> error</c:if>">
<c:import url="comu/CampFormulari.jsp">
	<c:param name="tipus" value="textarea" />
	<c:param name="path" value="formData.observacions" />
	<c:param name="title">
		<fmt:message key="lot.camp.observacions" />
	</c:param>
	<c:param name="camp" value="observacions" />
</c:import></div>

<div class="separadorH"></div>

<div class="botonesForm"><!-- OB 20091202  -  No ho posem perque ho miren als llibres físics  -->
<!-- Si se añade variedadOlivaArray cambiar el boton
			<div id="guardarForm" class="btnCorto" onclick="if(confirm('<fmt:message key="manteniment.modificar.confirm"/>')){onSubmitMultiple_varietatsOlivaArray();submitForm('formulario')}" onmouseover="underline('enlaceGuardarForm')" onmouseout="underline('enlaceGuardarForm')">
				<a id="enlaceGuardarForm" href="javascript:void(0);" ><fmt:message key="manteniment.guardar"/></a>
			</div>
			-->
<div id="guardarForm" class="btnCorto"
	onclick="if(confirm('<fmt:message key="manteniment.modificar.confirm"/>')){submitForm('formulario')}"
	onmouseover="underline('enlaceGuardarForm')"
	onmouseout="underline('enlaceGuardarForm')"><a
	id="enlaceGuardarForm" href="javascript:void(0);"><fmt:message
	key="manteniment.guardar" /></a></div>

<div class="btnCorto" onclick="submitForm('tornarForm')"
	onmouseover="underline('enlaceTornarForm')"
	onmouseout="underline('enlaceTornarForm')"><a
	id="enlaceTornarForm" href="javascript:void(0);"><fmt:message
	key="proces.tornar" /></a></div>
</div>

</form>

<form id="tornarForm" action="TancamentLlibres.html" class="seguit">
</form>

<!-- Colores en tablas -->
<script type="text/javascript">
		jQuery(document).ready(function(){
			setEstilosTabla(true);
			redibujarError();
		})
	</script>

</body>
</html>