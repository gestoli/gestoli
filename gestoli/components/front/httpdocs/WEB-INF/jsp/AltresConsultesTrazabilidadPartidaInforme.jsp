<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>
<%@ page import="es.caib.gestoli.logic.model.DescomposicioPartidaOliva"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.DecimalFormat"%>

<html>
<head>
<title><fmt:message key="consulta.trazabilitat" /></title>
<script type="text/javascript" src="js/calendar/calendar.js"></script>
<script type="text/javascript" src="js/calendar/calendar-setup.js"></script>
<script type="text/javascript" src="js/calendar/lang/calendar-es.js"></script>
<link href="js/calendar/calendar-viti.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="js/form.js"></script>
<c:if test="${not empty llistat}">
	<script type="text/javascript" src="js/displaytag.js"></script>
</c:if>

</head>
<body>
<div class="informePartidaOli"><c:if
	test="${not empty elaboraciones}">
	<div class="subpartidas">
	<h1><fmt:message
		key="consulta.trazabilitat.partidaOli.subpartidasOli" /></h1>
	<ul>
		<%
						double totalKilosPartidaOli = 0;
						double totalLitrosPartidaOli = 0;					
					%>
		<c:forEach var="elaboracion" items="${elaboraciones}">
			<c:set var="totalLitros" scope="request">
				<c:out value="${elaboracion.litres}" />
			</c:set>
			<li>
			<h2><span><fmt:formatNumber value="${elaboracion.litres}"
				maxFractionDigits="2" /> l. <c:if
				test="${not empty elaboracion.categoriaOli}">
				<c:out value="${elaboracion.categoriaOli.nom}" />
			</c:if> <fmt:message key="consulta.trazabilitat.elaboracio" /> ( <c:out
				value="${elaboracion.dataFormat}" /> NUM: <c:out
				value="${elaboracion.numeroElaboracio}" />)</span></h2>
			<%
									double totalKilos = 0;
									Map porcentajes = new HashMap();
							%> <c:forEach var="partidaOliva"
				items="${elaboracion.partidaOlivas}">
				<div class="index">
				<p><span class="titul"><fmt:message
					key="consulta.trazabilitat.partidaOli.partidaOliva" /> <c:out
					value="${partidaOliva.descPartida}" /></span></p>
				<div class="index">
				<p><fmt:message key="consulta.trazabilitat.olivicultor" />: <c:out
					value="${partidaOliva.olivicultor.nom}" /></p>
				</div>
				<c:forEach var="descomposicioPartida"
					items="${partidaOliva.descomposicioPartidesOlives}">
					<c:set var="kilos" scope="request">
						<c:out value="${descomposicioPartida.kilos}" />
					</c:set>
					<c:set var="variedadNom" scope="request">
						<c:out
							value="${descomposicioPartida.descomposicioPlantacio.varietatOliva.nom}" />
					</c:set>

					<div class="index"><fmt:message
						key="consulta.trazabilitat.partidaOli.partidaOliva.variedad" /> <c:out
						value="${descomposicioPartida.descomposicioPlantacio.varietatOliva.nom}" />
					<div class="index">
					<p><fmt:message
						key="consulta.trazabilitat.partidaOli.partidaOliva.cantidadVariedad" />:
					<fmt:formatNumber value="${descomposicioPartida.kilos}"
						maxFractionDigits="2" /> kgs.</p>
					<p><fmt:message
						key="consulta.trazabilitat.descomposicion.plantacion.municipio" />:
					<c:out
						value="${descomposicioPartida.descomposicioPlantacio.plantacio.municipi}" /></p>
					<p><fmt:message
						key="consulta.trazabilitat.descomposicion.plantacion.poligono" />:
					<c:out
						value="${descomposicioPartida.descomposicioPlantacio.plantacio.poligon}" /></p>
					<p><fmt:message
						key="consulta.trazabilitat.descomposicion.plantacion.parcela" />:
					<c:out
						value="${descomposicioPartida.descomposicioPlantacio.plantacio.parcela}" /></p>
					</div>
					</div>
					<%
											Double kilos = Double.valueOf((String)request.getAttribute("kilos"));
											totalKilos += kilos.doubleValue();
											request.setAttribute("totalKilos",Double.valueOf(String.valueOf(totalKilos)));
											String variedadNom = (String)request.getAttribute("variedadNom");
											if(porcentajes.get(variedadNom)!= null){
												Double k=(Double)porcentajes.get(variedadNom);
												porcentajes.put(variedadNom,Double.valueOf(String.valueOf(k.doubleValue() + kilos.doubleValue())));
											}else{
												porcentajes.put(variedadNom,kilos);
											}
											
										%>
				</c:forEach></div>
			</c:forEach> <%							
								Double totalLitros = Double.valueOf((String)request.getAttribute("totalLitros"));
								totalKilosPartidaOli+= totalKilos;
								totalLitrosPartidaOli+= totalLitros.doubleValue();
								Collection porcentajesKeys= porcentajes.keySet();
								String textoPorcentajes = "";
								DecimalFormat numberDecimalFormat = new DecimalFormat();
								numberDecimalFormat.setMaximumFractionDigits(2);
								for(Iterator it=porcentajesKeys.iterator();it.hasNext();){
									String key = (String)it.next();
									Double totalKilosVariedad = (Double)porcentajes.get(key);
									double porcentajeVariedad = (totalKilosVariedad.doubleValue() * 100)/ totalKilos;
									if(textoPorcentajes.equals("")){							
										textoPorcentajes = key+": "+numberDecimalFormat.format(porcentajeVariedad)+"%";
									}else{								
										textoPorcentajes = textoPorcentajes+" / "+key+": "+numberDecimalFormat.format(porcentajeVariedad)+"%";
									}
								}
							%>
			<div class="index">
			<p><span class="titul"><fmt:message
				key="consulta.trazabilitat.partidaOli.partidaOliva.cantidadTotal" />:</span>
			<fmt:formatNumber value="${totalKilos}" maxFractionDigits="2" />
			kgs.</p>
			<p><span class="titul"><fmt:message
				key="consulta.trazabilitat.partidaOli.partidaOliva.porcentajesVariedad" />:</span>
			<%=textoPorcentajes %></p>
			</div>
			</li>
		</c:forEach>
	</ul>
	<%
					double rendimientoGraso = ((totalLitrosPartidaOli*0.916) / totalKilosPartidaOli)*100;
					request.setAttribute("rendimientoGraso",Double.valueOf(String.valueOf(rendimientoGraso)));
					request.setAttribute("totalKilosPartidaOli",Double.valueOf(String.valueOf(totalKilosPartidaOli)));
				%>
	</div>
	<div class="rendimiento">
	<h1><fmt:message
		key="consulta.trazabilitat.partidaOli.rendimiento" /></h1>
	<div class="index">
	<p><span class="titul"><fmt:message
		key="consulta.trazabilitat.partidaOli.partidaOliva.cantidadTotal" />:</span>
	<fmt:formatNumber value="${totalKilosPartidaOli}" maxFractionDigits="2" />
	kgs.</p>
	<p><span class="titul"><fmt:message
		key="consulta.trazabilitat.partidaOli.partidaOliva.rendimientoGras" />:</span>
	<fmt:formatNumber value="${rendimientoGraso}" maxFractionDigits="2" />%</p>
	<p><span class="titul"><fmt:message
		key="consulta.trazabilitat.partidaOli.rendimientoFincas" />:</span></p>
	<div class="index"><c:forEach var="rendimiento"
		items="${rendimientos}">
		<p><c:out value="${rendimiento}" /></p>
	</c:forEach></div>
	</div>
	</div>
</c:if> <c:if test="${not empty analiticas}">
	<div class="analiticas">
	<h1><fmt:message key="consulta.trazabilitat.partidaOli.analiticas" /></h1>
	<ul>
		<c:forEach var="analitica" items="${analiticas}">
			<li>
			<h2><span><fmt:message
				key="consulta.trazabilitat.analitica" /> (<c:out
				value="${analitica.dataFormat}" />)</span> <a
				onclick="submitForm('pdfForm_<c:out value="${analitica.id}"/>');return false;"
				href="javascript:void(0);"><fmt:message key="manteniment.pdf" /></a>
			</h2>
			<form id="pdfForm_<c:out value="${analitica.id}"/>"
				action="GenerarPdf.html" method="post" class="seguit"><input
				type="hidden" id="tipus" name="tipus" value="7" /> <input
				type="hidden" id="idAna" name="idAna"
				value="<c:out value="${analitica.id}"/>" /> <input type="hidden"
				id="idEst" name="idEst"
				value="<c:out value="${analitica.diposit.establiment.id}"/>" /> <input
				type="hidden" id="idDip" name="idDip"
				value="<c:out value="${analitica.diposit.id}"/>" /></form>
			</li>
		</c:forEach>
	</ul>
	</div>
</c:if> <c:if test="${not empty lotes}">
	<div class="datosEmbotellado">
	<h1><fmt:message
		key="consulta.trazabilitat.partidaOli.embotellado" /></h1>
	<ul>
		<c:forEach var="lote" items="${lotes}">
			<li>
			<h2><span><fmt:message key="consulta.trazabilitat.lote" />
			<c:out value="${lote.codiAssignat}" /></span></h2>
			<div class="index">
			<p><span class="titul"><fmt:message
				key="consulta.trazabilitat.lote.data" />:</span> <c:out
				value="${lote.dataFormat}" /></p>
			<c:set var="categoria">
				<c:if
					test="${not empty lote.categoriaOli && lote.categoriaOli.id == 1}">
					<fmt:message key="consulta.general.camp.categoriaOli.1" />
				</c:if>
				<c:if
					test="${not empty lote.categoriaOli && lote.categoriaOli.id == 2}">
					<fmt:message key="consulta.general.camp.categoriaOli.2" />
				</c:if>
				<c:if
					test="${not empty lote.categoriaOli && lote.categoriaOli.id == 3}">
					<fmt:message key="consulta.general.camp.categoriaOli.3" />
				</c:if>
			</c:set>
			<p><span class="titul"><fmt:message
				key="consulta.trazabilitat.lote.categoria" />:</span> <c:out
				value="${categoria}" /></p>
			<p><span class="titul"><fmt:message
				key="consulta.trazabilitat.lote.acidesa" />:</span> <c:out
				value="${lote.acidesa}" /></p>
			<p><span class="titul"><fmt:message
				key="consulta.trazabilitat.lote.marca" />:</span> <c:out
				value="${lote.etiquetatge.marca.nom}" /></p>
			<p><span class="titul"><fmt:message
				key="consulta.trazabilitat.lote.tipoEnvase" />:</span> <c:out
				value="${lote.etiquetatge.tipusEnvas.nomSelect}" /></p>
			<c:if test="${not empty lote.litresPerduts}">
				<p><span class="titul"><fmt:message
					key="consulta.trazabilitat.lote.litresPerduts" />:</span> <c:out
					value="${lote.litresPerduts}" /></p>
				<p><span class="titul"><fmt:message
					key="consulta.trazabilitat.lote.motiuPerdua" />:</span> <c:out
					value="${lote.motiuPerdua}" /></p>
			</c:if>
			<p><span class="titul"><fmt:message
				key="consulta.trazabilitat.lote.litresEnvassats" />:</span> <fmt:formatNumber
				value="${lote.litresEnvassats}" maxFractionDigits="2" /> l.</p>
			<p><span class="titul"><fmt:message
				key="consulta.trazabilitat.lote.numeroBotellesActuals" />:</span> <c:out
				value="${lote.numeroBotellesActuals}" /></p>
			</div>
			</li>
		</c:forEach>
	</ul>
	</div>
</c:if>

<div class="btnCorto" onclick="javascript:history.back();"
	onmouseover="underline('enlaceTornarForm')"
	onmouseout="underline('enlaceTornarForm')"><a
	id="enlaceTornarForm" href="javascript:void(0);"><fmt:message
	key="proces.tornar" /></a></div>

</div>


</body>
</html>