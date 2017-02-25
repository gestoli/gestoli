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

<h2 class="microweb">Informaciones Útiles</h2>

<c:choose>
	<c:when test="${not empty informacionsUtils}">
		<table>
			<tr>
				<td style="vertical-align: top;">
					<div id="menu" style="width: 200px;">
					<div style="background: #fff;">
						<ul style="list-style-image: url(../img/icons/bullet.gif) ; margin: 0 0 0 20px; padding: 5px 10px 5px 0;">
							<c:forEach var="informacio" items="${informacionsUtils}">
								<li style=" margin: 0; padding: 0;">
									<a href="informacionsutils.ca.html?idioma=es&amp;id=${informacio.id}" style="list-style-type: disc; font-size: 11px;">
										<STRONG>${informacio.nomEs}</STRONG>
									</a>
									<br />
									<div id="fecha" style="font-size: 10px";>
									<c:if test="${informacio.dataInici != null}">
										(<fmt:formatDate value="${informacio.dataInici}" pattern="dd/MM/yyyy"/>)
									</c:if>
									<c:if test="${informacio.dataInici == null}">
										(<fmt:formatDate value="${informacio.dataAlta}" pattern="dd/MM/yyyy"/>)
									</c:if>
									</div>
								</li>
							</c:forEach>
						</ul>
					</div>
							<img src="../img/icons/menu-pie.png" style="position: relative; top: -4px;"/>
					</div>
				</td>
				<td style="vertical-align: top;">
					<div id="noticia" style="margin-left: 20px;">
	
						<c:if test="${not empty noticia}">
	
							<STRONG>${noticia.nomEs}</STRONG>: 
							<br /><br />
							<h5>
								<c:if test="${noticia.dataInici != null}">
									<fmt:formatDate value="${noticia.dataInici}" pattern="dd/MM/yyyy"/>
								</c:if>
								<c:if test="${noticia.dataInici == null}">
									<fmt:formatDate value="${noticia.dataAlta}" pattern="dd/MM/yyyy"/>
								</c:if>
							</h5>
		
							<div id="imagen" style="float: right; margin: 0 0 10px 20px">
								<c:if test="${not empty noticia.imatgePrincipal}">
									<a href="ArxiuMostrarWeb.html?id=${noticia.imatgePrincipal}" target="_blank">
										<img src="ArxiuMostrarWeb.html?id=${noticia.imatgePrincipal}" height="150" usemap="#nav1" alt="" />
									</a>
								</c:if>
							</div>
							<div id="descripcion">
								${noticia.descripcioEs}
							</div>
							<c:if test="${not empty noticia.imatgeSecundaria}">
							<div id="imagen2" style="margin: 10px 0 0 0">
								<a href="ArxiuMostrarWeb.html?id=${noticia.imatgeSecundaria}" target="_blank">
									<img src="ArxiuMostrarWeb.html?id=${noticia.imatgeSecundaria}" height="150" usemap="#nav1" alt="" />
								</a>
							</div>
							</c:if>
	
						</c:if>
					</div>
				</td>
			</tr>
		</table>
	</c:when>
	<c:otherwise>
		En estos momentos no hay ninguna información útil disponible
	</c:otherwise>
</c:choose>



</div>

</body>
</html>