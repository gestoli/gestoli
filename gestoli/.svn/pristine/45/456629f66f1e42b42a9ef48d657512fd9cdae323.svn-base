<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="decorator" uri="/WEB-INF/sitemesh-decorator.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>


<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>
	<c:set var="titol"><decorator:title/></c:set>
	<fmt:message key="webapp.nom"/><c:if test="${not empty titol}"> - <c:out value="${titol}"/></c:if>
</title>

<link rel="stylesheet" type="text/css" href="css/general.css" />
<link rel="stylesheet" type="text/css" href="css/menu.css" />
<link rel="stylesheet" type="text/css" href="css/tablas.css" />
<link rel="stylesheet" type="text/css" href="css/filterPNG.css" />
<!--[if IE]>
		<link rel="stylesheet" type="text/css" href="css/c3c-ie.css" />		
	<![endif]-->

<!--[if IE 6]>
	<link rel="stylesheet" type="text/css" href="css/menu-ie.css" media="screen" />
	<![endif]-->

<!--[if IE 7]>
	<link rel="stylesheet" type="text/css" href="css/menu-ie7.css" media="screen" />
	<![endif]-->

<link rel="stylesheet" type="text/css" href="css/impressio.css" media="print" />

<script type="text/javascript" src="js/onload.js"></script>
<script type="text/javascript" src="js/funciones.js"></script>
<script type="text/javascript" src="js/menu.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/sombras.js"></script>
<script type="text/javascript" src="js/selectbox.js"></script>


<link rel="icon" href="gesticon.gif" type="image/gif" />
<link rel="shortcut icon" href="gesticon.gif" type="image/gif" />

<decorator:head/>
</head>

<body>

<script type="text/javascript">
	window.contextPath = '<%= request.getContextPath() %>';
</script>

<!-- Estructura principal -->
<div id="layoutPrincipalPopup">

	<!-- Layout Pagina -->
	<div id="layoutPopup" class="extranet">

	<!-- Menu superior -->
	<div id="menuSuperiorPopup">
		<!--jsp:include page="../plantilla/Menu.jsp"/-->
	</div>

	<!-- Cabecera -->
	<div id="cabeceraGeneral">
		<h1>
			<a href="<%=request.getContextPath()%>/">
				<span>Gest-OLI</span>
			</a>
		</h1>
	</div>

	<!-- Linea de path -->
	<div id="pathCabecera">
		<jsp:include page="../plantilla/Path.jsp"/>
	</div>

	<!-- Contenido Principal -->
	<div class="fondoHorizontalPopup">
		<!--div class="fondoIzquierda">
			<div class="fondoDerecha">
				<div class="fondoVertical"-->
					<div id="contenidoPrincipal">
						<jsp:include page="../comu/MissatgesIErrors.jsp"/> 
						<decorator:body/>
					</div>
				<!--/div>
			</div>
		</div-->
	</div>
	<div class="fondoBottomPopup"></div>
</div>

<!-- Pie de pagina -->
<div id="pieGeneral">
	<!--jsp:include page="../plantilla/Pie.jsp"/-->
</div>

</div>

</body>
</html>

