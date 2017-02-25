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
	href="../css/web/microweb/informacionsUtils.css" media="screen" />

<title>Gest-OLI - Introducción</title>
</head>

<body>

	<div id="informacionsUtilsMicroweb">
	
		<h2 class="microweb">Infografías</h2>
		
		<c:forEach var="infografia" items="${infografies}">
			<table>
				<tr>
					<td>
						<a href="ArxiuMostrarWeb.html?id=${infografia.idArxiu}">
							<img src="ArxiuMostrarWeb.html?id=${infografia.idImatge}" height="150" />
						</a>
					</td>
					<td VALIGN="top">
						<div style="margin-left:15px;">
							<STRONG>${infografia.nomEs}</STRONG>:
							<br />
							${infografia.descripcioEs}
						</div>
					</td>
				</tr>
			</table>
			
			<br /><hr />
		</c:forEach>
		<c:if test="${empty infografies}">
			<div>En estos momentos no hay ninguna infografía disponible</div>
		</c:if>
	
	</div>

</body>
</html>