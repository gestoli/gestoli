<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ page import="es.caib.gestoli.front.util.*"%>
<%@ page import="java.util.ResourceBundle"%>
<%@ page import="java.util.Locale"%>
<%@ page import="java.util.List"%>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es" lang="es">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />

<link rel="stylesheet" type="text/css"
	href="../css/web/microweb/asistencia.css" media="screen" />

<title>Gest-OLI - Introducción</title>
</head>

<body>

<div id="asistenciaMicroweb">
<div class="asistenciaTecnica">

<h2 class="microweb">Noticia</h2>

	<STRONG><u>${noticia.nom}</u></STRONG>: 
	<br />
	<table>
		<tr>
			<td>
				<h5>
					<c:if test="${noticia.dataInici != null}">
						<fmt:formatDate value="${noticia.dataInici}" pattern="dd/MM/yyyy"/>
					</c:if>
					<c:if test="${noticia.dataInici == null}">
						<fmt:formatDate value="${noticia.dataAlta}" pattern="dd/MM/yyyy"/>
					</c:if>
				</h5>
				${noticia.descripcio}
			</td>
			<td>
				<c:if test="${not empty noticia.imatgePrincipal}">
					<img src="ArxiuMostrarWeb.html?id=${noticia.imatgePrincipal}" height="150" usemap="#nav1" alt="">
				</c:if>
			</td>
		</tr>
	</table>

	<c:if test="${not empty noticia.imatgeSecundaria}">
		<img src="ArxiuMostrarWeb.html?id=${noticia.imatgeSecundaria}" height="50" usemap="#nav1" alt="">
	</c:if>
				

</div>
</div>

</body>
</html>