<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>

<html>
	<head>
		<title></title>
		<script type="text/javascript" src="js/yahoo/yahoo.js"></script>
	    <script type="text/javascript" src="js/yahoo/dom.js"></script>
	    <script type="text/javascript" src="js/yahoo/event.js"></script>
		<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
		
		<script type="text/javascript">
			YAHOO.util.Event.addListener(window, "load", inicialitza);

			var map;
			
			var txt = '<strong><fmt:message key="fincas.camp.municipio"/>: </strong>${v}<br/>';
			txt += '<strong><fmt:message key="fincas.camp.poligono"/>: </strong>${po}<br/>';
			txt += '<strong><fmt:message key="fincas.camp.parcela"/>: </strong>${pa}<br/>';
			txt += '<strong><a id="catastre" href="https://www1.sedecatastro.gob.es/OVCFrames.aspx?TIPO=Catastro&RefCat=${ca}"><fmt:message key="fincas.camp.catastre"/></a></strong><br/>';
			
			function inicialitza() {
				var latitut = 39.487085;
				var longitut = 3.015747;
				var vZoom = 8;

				if (("" != "${x}") && ("" != "${y}")) {
					latitut = parseFloat("${y}");
					longitut = parseFloat("${x}");
					vZoom = 12;
				}
				
				var myOptions = {
						zoom: vZoom,
						center: new google.maps.LatLng(latitut, longitut),
						mapTypeId: google.maps.MapTypeId.ROADMAP
				};

				map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);

				if (("" != "${x}") && ("" != "${y}")) {
					var marker = new google.maps.Marker({
						position: new google.maps.LatLng(latitut, longitut),
						map: map
					});
	
					var infowindow = new google.maps.InfoWindow({
						content: txt,
						size: new google.maps.Size(50, 50)
					});
					
					infowindow.open(map, marker);
					
					//info(marker);
				}
			}

			function info(marker) {
				var infowindow = new google.maps.InfoWindow({
					content: txt,
					size: new google.maps.Size(50, 50)
				});
				
				google.maps.event.addListener(marker, "click", function(){
					infowindow.open(map, marker);
				});
			}
		</script>
		
	</head>
	
	<body>
		<div id="map_canvas" style="width:800px; height:600px;"></div>
	</body>
</html>