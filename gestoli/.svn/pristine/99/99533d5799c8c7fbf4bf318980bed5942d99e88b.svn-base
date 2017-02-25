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

<title>Gest-OLI - Infografies</title>
<script type="text/javascript">
function redimensiona(){
	var obj = document.getElementById("objecte");
	var emb = document.getElementById("embed");
	var div = document.getElementById("div");

	//obj.width = screen.availWidth;
	obj.height = document.documentElement.clientHeight - 20;

	//emb.width = screen.availWidth;
	emb.height = document.documentElement.clientHeight - 20;

}
</script>

<style>
*{
margin: 0;
padding: 0;
}
</style>

</head>

<body onload="redimensiona()" onresize="redimensiona()" >
		<object id="objecte" width="100%" height="100%" classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab">
			<param value="ArxiuMostrarWeb.html?id=${param.id}" name="movie" />
			<param name="wmode" value="opaque" />
			<param name="quality" value="high"/>
			<param name="allowFullScreen" value="true" />
			<param name="scale" value="default" />
			<embed id="embed" width="100%" height="100%" allowFullScreen="true" scale="default" wmode="opaque" quality="high" menu="false" name="undefined" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" src="ArxiuMostrarWeb.html?id=${param.id}" />
		</object>
</body>
</html>