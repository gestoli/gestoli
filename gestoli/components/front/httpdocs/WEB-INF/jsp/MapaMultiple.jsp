<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ page import="es.caib.gestoli.front.util.*"%>
<%@ page import="java.util.ResourceBundle"%>
<%@ page import="java.util.Locale"%>
<%@ page import="java.util.List"%>

<html>
	<head>
		<title></title>
		<script type="text/javascript" src="js/yahoo/yahoo.js"></script>
		<script type="text/javascript" src="js/yahoo/dom.js"></script>
		<script type="text/javascript" src="js/yahoo/event.js"></script>
		<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>

		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
		
		<script type="text/javascript">
			YAHOO.util.Event.addListener(window, "load", inicialitza);

			var map;
			var markersArray = [];
			var msgArray = [];
			
			function inicialitza() {
				var latitut = 39.597223;
				var longitut = 2.874298;
				var vZoom = 10;
				
				var myOptions = {
						zoom: vZoom,
						center: new google.maps.LatLng(latitut, longitut),
						mapTypeId: google.maps.MapTypeId.ROADMAP
				};

				map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);

				var marker;

				<c:forEach var="plantacio" items="${plantacions}" varStatus="status">

					var coordX = ${plantacio.coordX};
					var coordY = ${plantacio.coordY};
					marker = new google.maps.Marker({
						position: new google.maps.LatLng(parseFloat(coordY), parseFloat(coordX)),
					    	map: map
					});
					markersArray.push(marker);
					
					var txt = '<strong><fmt:message key="fincas.camp.municipio"/>: </strong>${plantacio.municipi.nom}<br/>';
						txt += '<strong><fmt:message key="fincas.camp.poligono"/>: </strong>${plantacio.poligon}<br/>';
						txt += '<strong><fmt:message key="fincas.camp.parcela"/>: </strong>${plantacio.parcela}<br/>';
						txt += '<strong><a id="catastre" href="https://www1.sedecatastro.gob.es/OVCFrames.aspx?TIPO=Catastro&RefCat=${plantacio.catastre}"><fmt:message key="fincas.camp.catastre"/></a></strong><br/>';
	
					attachSecretMessage(marker, txt);

				</c:forEach>

			}
			
			function attachSecretMessage(marker, txt) {
				var infowindow = new google.maps.InfoWindow(
				{ content: txt,
				  size: new google.maps.Size(50,50)
			   	});
			  	google.maps.event.addListener(marker, 'click', function() {
			    	infowindow.open(map,marker);
			  	});
			}

		</script>
		
	</head>
	
	<body>
		<div id="map_canvas" style="width:800px; height:600px;"></div>
	</body>
</html>