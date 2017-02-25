<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/fn-rt.tld"%>

<%@ page import="es.caib.gestoli.logic.model.Diposit"%>
<%@ page import="es.caib.gestoli.front.spring.InformacioDipositEstablimentCommand"%>
<%@ page import="es.caib.gestoli.logic.model.Lot"%>
<%@ page import="es.caib.gestoli.logic.model.PartidaOliva"%>
<%@page import="es.caib.gestoli.logic.model.EntradaLot"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Iterator"%>


<c:forEach var="zona" items="${zones}">
	<c:if test="${zonaId != 'NS' && zona.id == zonaId}">
		<c:set var="imatgeZona" value="${zona.imatgePlanol}" />
	</c:if>
</c:forEach>

<%
	String[] iconesOlivaTaula = { "img/icons/oliva_taula.gif", "", "img/icons/oliva_taula_negra.gif" }; 
		pageContext.setAttribute("iconesOlivaTaula", iconesOlivaTaula); 
		
	String[] iconesBotes = { "img/icons/botes_verda.png",  "img/icons/botes_trencada.png", "img/icons/botes_negra.png", "img/icons/botes_verda_eco.png", "img/icons/botes_trencada_eco.png", "img/icons/botes_negra_eco.png" }; 
		pageContext.setAttribute("iconesBotes", iconesBotes); 
		
	String[] iconesLotOlivaTaula = { "img/icons/lotOlivaVerda.png",  "img/icons/lotOlivaTrencada.png", "img/icons/lotOlivaNegra.png", "img/icons/lotOlivaVerda_eco.png", "img/icons/lotOlivaTrencada_eco.png", "img/icons/lotOlivaNegra_eco.png" }; 
		pageContext.setAttribute("iconesLotOlivaTaula", iconesLotOlivaTaula); 
%>

<script type="text/javascript" src="js/yahoo/container.js"></script>
<script type="text/javascript" src="js/yahoo/dragdrop.js"></script>
<script type="text/javascript" src="js/yahoo/menu.js"></script>
<script type="text/javascript" src="js/mapaEstabliment.js"></script>
<script type="text/javascript" src="dwr/interface/contenidorService.js"></script>
<script type="text/javascript" src="dwr/engine.js"></script>
<script type="text/javascript" src="js/cookie.js"></script>

<c:if test="${not empty imatgeZona}">
	<script type="text/javascript" language="javascript">
		// <![CDATA[
		var ids = new Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 15, 16, 17, 18, 19, 20, <c:out value="${imatgeZona}"/>);
		var imgAlt = new Array(21);
		var imgAmple = new Array(21);
		
		function guardaPosicio(id, posx, posy, tipus) {
			contenidorService.situar(id, <c:out value="${establimentId}"/>, <c:out value="${zonaId}"/>, posx, posy, tipus, posicioValida);
		}

		function posicioValida(resultat) {
			if (!resultat) alert("<fmt:message key="diposit.situar.error"/>");
		}

		function esborraPosicio(id, tipus) {
			contenidorService.noSituar(id, <c:out value="${establimentId}"/>, tipus);
		}

		var mapaEstabliment = new MapaEstabliment(
				"mapaEstabliment", {
					id: "fons",
					idPrefix: "cont_",
					imatgeZona: "ArxiuMostrar.html?id=<c:out value="${imatgeZona}"/>",
					posicionarCallback: guardaPosicio,
					<c:if test="${not empty procsel}">seleccioCallback: canviSeleccio,</c:if>
					<%-- CARLOS: si se indica que noPosicionarMissatge != "" entonces,
						al pulsar boton derecho sobre un deposito aparece la opcion de borrar el deposito
						ejecutandose 'esborraPosicio'.
						En los casos de uso de gestoli no se indica que se de la posibilidad de borrarlo.
							<c:if test="${empty esConsulta && empty esAdmin}">noPosicionarMissatge: "<fmt:message key="bodega.vista.accioc.esborrarp"/>",</c:if>
							<c:if test="${not empty esConsulta || not empty esAdmin}">noPosicionarMissatge: "",</c:if>
					--%>
					noPosicionarMissatge: "",
					noPosicionarCallback: esborraPosicio,
					historialMissatge: "<fmt:message key="establiment.vista.trazabilidad"/>",
					<%-- CARLOS: al pulsar boton derecho sobre un deposito aparece
						la opcion de trazabilidad: diposit.vista.trazabilidad
						Dependiendo del rol pasa un historialUrl u otro. De momento nosotros no distinguimos.
							<c:if test="${empty esAdmin}">historialUrl: "ConsultaTrazabilitat.lmt?contenidorId=",</c:if>
							<c:if test="${not empty esAdmin}">historialUrl: "ConsultaTrazabilitat.lmt?bodegaId=<c:out value="${bodegaId}"/>&contenidorId=",</c:if>
					--%>
					historialUrl: "ConsultaTrazabilitat.html",
					resumMissatge: "<fmt:message key="establiment.vista.trazabilidad.resumida"/>",
					resumUrl: "ConsultaTrazabilitatResumida.html",
					<c:choose>
						<c:when test="${not empty esDoGestor || not empty esAdministracio || not empty esDoControlador}">
							canviMissatge: "",
						</c:when>
						<c:otherwise>
							canviMissatge: "<fmt:message key="establiment.vista.canviar.propietari"/>",
						</c:otherwise>
					</c:choose>
					canviUrl: "CanviarPropietari.html",
					mostrarMenu: <c:choose><c:when test="${empty procsel}">true</c:when><c:otherwise>false</c:otherwise></c:choose>,
					ampladaMax: 888,
					<%-- Generam el llistat de diposits --%>
					diposits: [
						<c:forEach var="dipos" items="${diposits}" varStatus="status">
							<c:set var="selected" value="${false}"/>
<%
	/*
	 * CARLOS: cada dadesContenidor es un elemento de la coleccion devuelta por el metodo de ContenidorDao:
	 * "findInfo(Long establimentId, Long zonaId, Object[] seleccio, Boolean esRaim, boolean senseZona) "
	 *
		Object[] dadesContenidor = (Object[])pageContext.getAttribute("dipos");
		Float capacitat = (Float)dadesContenidor[10];	// dadesContenidor[10] - l.(quantitatInicial del lote)
		if (capacitat == null)
			capacitat = new Float(0);
		Float sortides = (Float)dadesContenidor[11];	// dadesContenidor[11] - l.entradaProcesses
		if (sortides == null)
			sortides = new Float(0);
		Float contingut = null;
		if (dadesContenidor[23] != null)
			contingut = new Float(0);	//dadesContenidor[23] - l.dataFi
		else
			contingut = new Float(capacitat.floatValue() - sortides.floatValue());
		pageContext.setAttribute("contenido", contingut);
	 *
	 */

	Float contingut = null;
	InformacioDipositEstablimentCommand deposito = (InformacioDipositEstablimentCommand)pageContext.getAttribute("dipos");
	Double capacitat = deposito.getCapacitat();
	if (deposito.getVolumActual()!= null) {
		contingut = Float.valueOf(deposito.getVolumActual().toString());
	}
	pageContext.setAttribute("contenido", contingut);
%>
							<c:forEach var="diposEnCami" items="${dipositsEnCami}" varStatus="status2">
								<c:if test="diposEnCami[0] == dipos.id">
									<c:choose>
										<c:when test="${diposEnCami[1] == false}">
											<c:set var="transparent">false</c:set>
										</c:when>
										<c:otherwise>
											<c:set var="transparent">true</c:set>
										</c:otherwise>
									</c:choose>
									<c:set var="responsableEntrada"><c:out value="${diposEnCami[2]}"/></c:set>
									<c:set var="dataEntrada"><c:out value="${diposEnCami[3]}"/></c:set>
								</c:if>
							</c:forEach>

							<%--c:choose>
								<c:when test="${dipos.enCami}">
									<c:set var="trasllat"><fmt:message key="manteniment.si"/></c:set>
								</c:when>
								<c:otherwise>
									<c:set var="trasllat"><fmt:message key="manteniment.no"/></c:set>
								</c:otherwise>
							</c:choose--%>

							<c:set var="lkg">
								<c:choose>
									<c:when test="${establiment.unitatMesura == 'l'}">l</c:when>
									<c:when test="${establiment.unitatMesura == 'k'}">Kg</c:when>
								</c:choose>
							</c:set>

							<c:set var="contingut">
								<c:choose>
									<c:when test="${contenido > 0}">
										<fmt:message key="establiment.vista.info.contingut"/>: <c:out value="${contenido}"/> l<c:if test="${not empty dipos.partidaOli.categoriaOli.id}"><br/><fmt:message key="establiment.vista.info.categoria"/>: <fmt:message key="consulta.general.camp.categoriaOli.${dipos.partidaOli.categoriaOli.id}"/></c:if>
									</c:when>
									<c:otherwise>[<fmt:message key="establiment.vista.info.buit"/>]</c:otherwise>
								</c:choose>
							</c:set>

							<%-- Info del tooltip dels dipòsits --%>
							<c:set var="descripcio">
								<b><c:out value="${dipos.codiAssignat}"/></b><c:if test="${not empty dipos.partidaOli.id}"><br/><fmt:message key="establiment.vista.listadoLotes.propietari"/>: <c:if test="${dipos.partidaOli.olivicultorEsPropietari}"><fmt:message key="establiment.vista.listadoLotes.propietari.olivicultor"/></c:if><c:if test="${!dipos.partidaOli.olivicultorEsPropietari}"><fmt:message key="establiment.vista.listadoLotes.propietari.establiment"/></c:if></c:if><br/><c:if test="${not empty dipos.capacitat}"><fmt:message key="establiment.vista.info.capacitat"/>: <c:out value="${dipos.capacitat}"/> l<br/></c:if><c:if test="${not empty dipos.materialDiposit}"><fmt:message key="establiment.vista.info.material"/>: <c:out value="${dipos.materialDiposit.nom}"/><br/></c:if><c:out value="${contingut}" escapeXml="false"/><br/><fmt:message key="establiment.vista.info.partida"/>: <c:out value="${dipos.partidaOli.nom}"/><br/><fmt:message key="establiment.vista.info.responsable"/>: <c:out value="${dipos.responsable}"/><br/><fmt:message key="establiment.vista.info.data.entrada"/>: <fmt:formatDate value="${dipos.dataEntrada}" pattern="dd/MM/yyyy"/><br/><%--<fmt:message key="establiment.vista.info.varietats"/>: <ul><c:forEach var="varietat" items="${dipos.varietatsDiposit}"><li><c:out value="${varietat}"/></li></c:forEach></ul>--%><%--c:if test="${not empty dipos.lotsDiposit}"><fmt:message key="establiment.vista.info.lots"/>: <ul><c:forEach var="lot" items="${dipos.lotsDiposit}"><li><c:out value="${lot}"/></li></c:forEach></ul></c:if--%>								
							</c:set>

							<c:set var="nomContenidor">
								<c:out value="${dipos.codiAssignat}"/>
							</c:set>

							<c:set var="trazable">
								<c:choose>
									<c:when test="${contenido > 0}">true</c:when>
									<c:otherwise>false</c:otherwise>
								</c:choose>
							</c:set>

							<c:set var="dragable">
								<c:choose>
									<c:when test="${not empty esDoGestor || not empty esAdministracio || not empty esDoControlador }">false</c:when>
									<c:otherwise>true</c:otherwise>
								</c:choose>
							</c:set>

							<c:choose>
								<c:when test="${dipos.enCami}">
									<c:set var="codiImatgeContenidor" value="${dipos.materialDiposit.iconaTraslladant}"/>
								</c:when>
								<c:when test="${dipos.precintat}">
									<c:set var="codiImatgeContenidor" value="${dipos.materialDiposit.iconaPrecintat}"/>
								</c:when>
								<c:otherwise>
									<c:set var="codiImatgeContenidor" value="${dipos.materialDiposit.icona}"/>
								</c:otherwise>
							</c:choose>
							

							<c:forEach var="contBlanc" items="${contenidorsBlancs}">
								<c:if test="${dipos[0] == contBlanc.id}">
									<c:set var="codiImatgeContenidor" value="10"/>
								</c:if>
							</c:forEach>

							<c:if test="${not empty esConsulta || not empty esAdmin}">
								<c:set var="dragable" value="false"/>
							</c:if>

							[
								<c:out value="${dipos.id}"/>,
								"ArxiuMostrar.html?id=<c:out value="${codiImatgeContenidor}"/>",
								<c:out value="${dipos.posicioX}"/>,
								<c:out value="${dipos.posicioY}"/>,
								<c:out value="${(contenido) * 100 / dipos.capacitat}"/>,
								"<c:out value="${descripcio}" escapeXml="false"/>",
								<c:out value="${dragable}"/>,
								<c:out value="${transparent}"/>,
								<c:out value="${param.seleccionable and selected}"/>,
								"EDD85B",
								<c:out value="${trazable}"/>,
								"0",
								<c:out value="${trazaDiposits}"/>,
								<c:out value="${dipos.zona.id}"/>,
								"<c:out value="${dipos.codiAssignat}"/>",
								"false"
							],
						</c:forEach>[]],

						<%-- Generam el llistat de partides --%>
						partides: [
							<c:forEach var="partides" items="${partides}">
								<c:set var="selected" value="${false}"/>
<%
	Float contingut = null;
	contingut = null;
	pageContext.setAttribute("contenido", contingut);

%>
								<c:set var="transparent">
									<c:choose>
										<c:when test="${empty contenidorInfo or contenidorInfo[0] == partides[0]}">false</c:when>
										<c:otherwise>true</c:otherwise>
									</c:choose>
								</c:set>

								<c:set var="lkg" value="l"/>

								<c:set var="title" value=""/>
								<c:set var="title2" value=""/>
								<c:forEach var="desc" items="${partides.descomposicioPartidesOlives}">
									<c:choose>
										<c:when test="${not empty desc.descomposicioPlantacio.varietatOliva.nomVarietat}">
											<c:set var="nomVarietat" value="${desc.descomposicioPlantacio.varietatOliva.nomVarietat}"/>
										</c:when>
										<c:otherwise>
											<c:set var="nomVarietat" value="${desc.descomposicioPlantacio.varietatOliva.nom}"/>
										</c:otherwise>
									</c:choose>
									<c:if test="${fn:contains(title, nomVarietat) == false}">
										<c:if test="${not empty title}">
											<c:set var="title" value="${title}<br/>"/>
										</c:if>
										<c:set var="title" value="${title}${nomVarietat}"/>
									</c:if>
									<c:if test="${fn:contains(title2, nomVarietat) == false}">
										<c:if test="${not empty title2}">
											<c:set var="title2" value="${title2} "/>
										</c:if>
										<c:set var="title2" value="${title2}${nomVarietat}"/>
									</c:if>
								</c:forEach>
								
								<%-- Info del tooltip de les partides --%>
								<c:set var="descripcio">
									<%--c:choose>
										<c:when test="${partides.olivaTaula == false}"--%>
										<c:if test="${partides.olivaTaula == false}">
											<b><c:out value="${title}" escapeXml="false"/></b><br/><fmt:message key="establiment.vista.listadoPartidas.olivicultor"/>: <c:out value="${partides.olivicultor.nom}"/><br/><c:if test="${not empty partides.totalQuilos}"><fmt:message key="establiment.vista.info.quantitat"/>: <c:out value="${partides.totalQuilos}"/> kgs<br/></c:if><c:if test="${not empty partides.sana}"><fmt:message key="establiment.vista.info.estat"/>: <c:choose><c:when test="${partides.sana}"><fmt:message key="establiment.vista.info.sana"/></c:when><c:otherwise><fmt:message key="establiment.vista.info.noSana"/></c:otherwise></c:choose><br/></c:if>
										</c:if>
										<c:if test="${partides.olivaTaula == true}">
											<b><c:out value="${title}" escapeXml="false"/></b><br/><fmt:message key="establiment.vista.listadoPartidas.olivicultor"/>: <c:out value="${partides.olivicultor.nom}"/><br/><c:if test="${not empty partides.totalQuilos}"><fmt:message key="establiment.vista.info.quantitat.inicial"/>: <c:out value="${partides.totalQuilos}"/> kgs<br/></c:if><c:if test="${not empty partides.quantitat}"><fmt:message key="establiment.vista.info.quantitat.actual"/>: <c:out value="${partides.quantitat}"/> kgs<br/></c:if><c:if test="${not empty partides.envasPetit}"><fmt:message key="establiment.vista.info.envasPetit"/>: <c:choose><c:when test="${partides.envasPetit}"><fmt:message key="establiment.vista.info.si"/></c:when><c:otherwise><fmt:message key="establiment.vista.info.no"/></c:otherwise></c:choose><br/></c:if><c:if test="${not empty partides.envasRigid}"><fmt:message key="establiment.vista.info.envasRigid"/>: <c:choose><c:when test="${partides.envasRigid}"><fmt:message key="establiment.vista.info.si"/></c:when><c:otherwise><fmt:message key="establiment.vista.info.no"/></c:otherwise></c:choose><br/></c:if><c:if test="${not empty partides.envasVentilat}"><fmt:message key="establiment.vista.info.envasVentilat"/>: <c:choose><c:when test="${partides.envasVentilat}"><fmt:message key="establiment.vista.info.si"/></c:when><c:otherwise><fmt:message key="establiment.vista.info.no"/></c:otherwise></c:choose><br/></c:if><c:if test="${not empty partides.tipusOlivaTaula}"><fmt:message key="establiment.vista.info.tipusOlivaTaula"/>: <c:choose><c:when test="${partides.tipusOlivaTaula == 0}"><fmt:message key="establiment.vista.info.tipusOlivaTaula.verda"/></c:when><c:when test="${partides.tipusOlivaTaula == 1}"><fmt:message key="establiment.vista.info.tipusOlivaTaula.trencada"/></c:when><c:otherwise><fmt:message key="establiment.vista.info.tipusOlivaTaula.negra"/></c:otherwise></c:choose></c:if>
										</c:if>
										<%--/c:when>
										<c:otherwise>
											
										</c:otherwise--%>
										<%--
										<b><c:out value="${title}" escapeXml="false"/></b><br/>
										<fmt:message key="establiment.vista.listadoPartidas.olivicultor"/>: <c:out value="${partides.olivicultor.nom}"/><br/>
										<c:if test="${not empty partides.totalQuilos}">
											<fmt:message key="establiment.vista.info.quantitat"/>: <c:out value="${partides.totalQuilos}"/> kgs<br/>
										</c:if>
										<fmt:message key="establiment.vista.info.envasPetit"/>: <c:choose><c:when test="${partides.envasPetit}"><fmt:message key="establiment.vista.info.si"/></c:when><c:otherwise><fmt:message key="establiment.vista.info.no"/></c:otherwise></c:choose><br/>
										<fmt:message key="establiment.vista.info.envasRigid"/>: <c:choose><c:when test="${partides.envasRigid}"><fmt:message key="establiment.vista.info.si"/></c:when><c:otherwise><fmt:message key="establiment.vista.info.no"/></c:otherwise></c:choose><br/>
										<fmt:message key="establiment.vista.info.envasVentilat"/>: <c:choose><c:when test="${partides.envasVentilat}"><fmt:message key="establiment.vista.info.si"/></c:when><c:otherwise><fmt:message key="establiment.vista.info.no"/></c:otherwise></c:choose><br/>
										--%>
									<%--/c:choose--%>
								</c:set>

								<c:set var="trazable">true</c:set>

								<c:set var="dragable">
									<c:choose>
										<c:when test="${not empty esDoGestor || not empty esAdministracio || not empty esDoControlador }">false</c:when>
										<c:otherwise>true</c:otherwise>
									</c:choose>
								</c:set> 

								<c:set var="codiImatgeContenidor" value="" />
								<c:set var="altres" value="${olivaAltres}" />

								<c:forEach var="desc" items="${partides.descomposicioPartidesOlives}">
									<c:set var="icon" value="${desc.descomposicioPlantacio.varietatOliva.icona}"/>
									<c:set var="codiColor" value="${desc.descomposicioPlantacio.varietatOliva.color}"/>
									<c:if test="${not empty codiImatgeContenidor && codiImatgeContenidor != desc.descomposicioPlantacio.varietatOliva.icona}">
										<c:set var="codiImatgeContenidor" value="${olivaAltres}"/>
										<c:set var="codiColor" value="${colorAltres}"/>
									</c:if>
									<c:if test="${empty codiImatgeContenidor}">
										<c:set var="codiImatgeContenidor" value="${desc.descomposicioPlantacio.varietatOliva.icona}"/>
									</c:if>
								</c:forEach>

								<c:forEach var="contBlanc" items="${contenidorsBlancs}">
									<c:if test="${partides[0] == contBlanc.id}">
										<c:set var="codiImatgeContenidor" value=""/>
									</c:if>
								</c:forEach>
								
								
							
								<c:if test="${not empty esConsulta || not empty esAdmin}"><c:set var="dragable" value="false"/></c:if>

								
								<c:if test="${partides.olivaTaula}">
<%
	Float percent = null;
	PartidaOliva partida = (PartidaOliva)pageContext.getAttribute("partides");
	Double total = partida.getTotalKilos();
	Double restant = new Double((partida.getQuantitat() != null ? partida.getQuantitat() : total).doubleValue() * 100.0);
	percent = new Float(restant.doubleValue() / total.doubleValue());
	pageContext.setAttribute("percent", percent);
%>
									<c:if test="${partides.tipusOlivaTaula==0}">
										<c:set var="codiImatgeContenidor" value="0"/>
									</c:if>
									<c:if test="${partides.tipusOlivaTaula==2}">
										<c:set var="codiImatgeContenidor" value="2"/>
									</c:if>
								
									[
										<c:out value="${partides.id}"/>,
										"${iconesOlivaTaula[codiImatgeContenidor]}",
										<c:out value="${partides.posicioX}"/>,
										<c:out value="${partides.posicioY}"/>,
										<c:out value="${percent}"/>, //"100",
										"<c:out value="${descripcio}" escapeXml="false"/>",
										<c:out value="${dragable}"/>,
										<c:out value="${transparent}"/>,
										<c:out value="${param.seleccionable and selected}"/>,
										<c:out value="'${codiColor}'" escapeXml="false"/>,
										<c:out value="${trazable}"/>,
										"1",
										<c:out value="${trazaPartidas}"/>,
										<c:out value="${partides.zona.id}"/>,
										"<c:out value="${title2}" escapeXml="false"/>",
										"true"
									],	
								</c:if>
								<c:if test="${empty partides.tipusOlivaTaula}">
									[
										<c:out value="${partides.id}"/>,
										"ArxiuMostrar.html?id=<c:out value="${codiImatgeContenidor}"/>",
										<c:out value="${partides.posicioX}"/>,
										<c:out value="${partides.posicioY}"/>,
										"100",
										"<c:out value="${descripcio}" escapeXml="false"/>",
										<c:out value="${dragable}"/>,
										<c:out value="${transparent}"/>,
										<c:out value="${param.seleccionable and selected}"/>,
										<c:out value="'${codiColor}'" escapeXml="false"/>,
										<c:out value="${trazable}"/>,
										"1",
										<c:out value="${trazaPartidas}"/>,
										<c:out value="${partides.zona.id}"/>,
										"<c:out value="${title2}" escapeXml="false"/>",
										"false"
									],
								</c:if>
							</c:forEach>[]],

							lotes: [
								<c:forEach var="lote" items="${lotes}">
									<c:set var="selected" value="${false}"/>
									<c:set var="transparent">
										<c:choose>
											<c:when test="${empty contenidorInfo or contenidorInfo[0] == lotes[0]}">false</c:when>
											<c:otherwise>true</c:otherwise>
										</c:choose>
									</c:set>

									<c:set var="lkg">l</c:set>
<%
	Double proporcion = null;
	Lot lot = (Lot)pageContext.getAttribute("lote"); 
	Integer numBotAct = lot.getNumeroBotellesActuals();

	if ((lot.getNumeroBotellesInicials() != null) && (lot.getNumeroBotellesInicials().doubleValue() > 0)) {
		proporcion = new Double((lot.getNumeroBotellesActuals().doubleValue() * 100) / lot.getNumeroBotellesInicials().doubleValue());
	} else {
		proporcion = new Double(0);
	}
	
	//Si les observacions de les entrades de lot inclou la paraula "EI" es mostra fins al final
	String etiquetes = "";
	String paraulaClau = "EI ";
	Set els = lot.getEntradaLots();

		
	for(Iterator it=els.iterator(); it.hasNext();){
		EntradaLot el = (EntradaLot)it.next();
		
		if(el.getObservacions() != null){
			String obs = el.getObservacions();
			if(obs.contains(paraulaClau)){
				etiquetes += obs.substring(obs.indexOf(paraulaClau)+paraulaClau.length(),obs.length());
			}
		}
	}
	
	pageContext.setAttribute("proporcion", proporcion);
	pageContext.setAttribute("etiquetesObservacions", etiquetes);
%>
									<c:set var="contingut">
										<c:choose>
											<c:when test="${lote.numeroBotellesInicials > 0}">
												<fmt:message key="lot.camp.numeroBotellesInicial"/>: <c:out value="${lote.numeroBotellesInicials}"/><c:if test="${not empty lote.numeroBotellesActuals}"><br/><fmt:message key="lot.camp.numeroBotellesActual"/>: <c:out value="${lote.numeroBotellesActuals}"/><c:if test="${not empty lote.dataConsum}"><br/><fmt:message key="establiment.vista.listadoLotes.data.consum"/>: <fmt:formatDate value="${lote.dataConsum}" pattern="dd/MM/yyyy"/></c:if></c:if><c:if test="${not empty lote.etiquetesLot}"><br/><fmt:message key="lot.camp.etiquetar"/>: <ul><c:forEach var="etiq" items="${lote.etiquetesLot}"><li><c:out value="${etiq[0]}"/> - <c:out value="${etiq[1]}"/></li></c:forEach></ul></c:if><c:if test="${not empty etiquetesObservacions}"><br/><fmt:message key="establiment.vista.listadoLotes.etiquetes"/>: ${etiquetesObservacions}</c:if>
											</c:when>
											<c:otherwise>[<fmt:message key="establiment.vista.info.buit"/>]</c:otherwise>
										</c:choose>
									</c:set>

									<%-- Info del tooltip dels lots --%>
									<c:set var="descripcio">
										<b><c:out value="${lote.codiAssignat}"/></b><br/><b><fmt:message key="establiment.vista.listadoLotes.marca"/>: <c:out value="${lote.etiquetatge.marca.nom}"/></b><c:if test="${not empty lote.partidaOli.id}"><br/><fmt:message key="establiment.vista.listadoLotes.propietari"/>: <c:if test="${lote.partidaOli.olivicultorEsPropietari}"><fmt:message key="establiment.vista.listadoLotes.propietari.olivicultor"/></c:if><c:if test="${!lote.partidaOli.olivicultorEsPropietari}"><fmt:message key="establiment.vista.listadoLotes.propietari.establiment"/></c:if></c:if><c:if test="${not empty lote.acidesa}"><br/><fmt:message key="establiment.vista.listadoLotes.acidesa"/>: <fmt:formatNumber value="${lote.acidesa}" maxFractionDigits ="2" /></c:if><c:if test="${not empty lote.litresEnvassats}"><br/><fmt:message key="establiment.vista.listadoLotes.litresEnvassats"/>: <fmt:formatNumber value="${lote.litresEnvassats}" maxFractionDigits ="2" /><br/><fmt:message key="establiment.vista.listadoLotes.capacitat"/>: <fmt:formatNumber value="${lote.etiquetatge.tipusEnvas.volum}" maxFractionDigits ="2" /> l.</c:if><c:if test="${not empty lote.partidaOli.id}"><br/><fmt:message key="establiment.vista.listadoLotes.partida"/>: ${lote.partidaOli.nom}</c:if><c:if test="${not empty lote.partidaOli.categoriaOli.id}"><br/><fmt:message key="establiment.vista.info.categoria"/>: <fmt:message key="consulta.general.camp.categoriaOli.${lote.partidaOli.categoriaOli.id}"/></c:if><c:if test="${not empty lote.producte.id}"><br/><fmt:message key="establiment.vista.listadoLotes.producte"/>: ${lote.producte.nom}</c:if><br/><c:out value="${contingut}" escapeXml="false"/><c:if test="${not empty lote.numeroLot}"><br/><fmt:message key="establiment.vista.listadoLotes.numeroLot"/>: <c:out value="${lote.numeroLot}"/></c:if><br/><fmt:message key="lot.camp.etiquetatge"/>: <c:out value="${lote.etiquetatge.nomEnvas}"/><br/><fmt:message key="lot.camp.lotTap"/>: <c:out value="${lote.lotTap}"/>
										</c:set>

									<c:set var="trazable">
										<c:choose>
											<c:when test="${proporcion > 0}">true</c:when>
											<c:otherwise>false</c:otherwise>
										</c:choose>
									</c:set>

									<c:set var="dragable">
										<c:choose>
											<c:when test="${not empty esDoGestor || not empty esAdministracio || not empty esDoControlador }">false</c:when>
											<c:otherwise>true</c:otherwise>
										</c:choose>
									</c:set>

									

									<c:if test="${not empty esConsulta || not empty esAdmin}"><c:set var="dragable" value="false"/></c:if>

									<c:if test="${lote.olivaTaula}">
									<c:set var="contingut">
										<c:choose>
											<c:when test="${lote.numeroBotellesInicials > 0}">
											<fmt:message key="lot.camp.numeroEnvassosInicial"/>: <c:out value="${lote.numeroBotellesInicials}"/><c:if test="${not empty lote.numeroBotellesActuals}"><br/><fmt:message key="lot.camp.numeroEnvassosActual"/>: <c:out value="${lote.numeroBotellesActuals}"/><c:if test="${not empty lote.dataConsum}"><br/><fmt:message key="establiment.vista.listadoLotes.data.consum"/>: <fmt:formatDate value="${lote.dataConsum}" pattern="dd/MM/yyyy"/></c:if></c:if><c:if test="${not empty lote.etiquetesLot}"><br/><fmt:message key="lot.camp.etiquetar"/>: <ul><c:forEach var="etiq" items="${lote.etiquetesLot}"><li><c:out value="${etiq[0]}"/> - <c:out value="${etiq[1]}"/></li></c:forEach></ul></c:if><c:if test="${not empty etiquetesObservacions}"><br/><fmt:message key="establiment.vista.listadoLotes.etiquetes"/>: ${etiquetesObservacions}</c:if>
											</c:when>
											<c:otherwise>[<fmt:message key="establiment.vista.info.buit"/>]</c:otherwise>
										</c:choose>
									</c:set>
										<c:set var="descripcio">
											<c:if test="${not empty lote.tipusOlivaTaula}">Oliva <c:choose><c:when test="${lote.tipusOlivaTaula == 0}"><fmt:message key="establiment.vista.info.tipusOlivaTaula.verda"/></c:when><c:when test="${lote.tipusOlivaTaula == 1}"><fmt:message key="establiment.vista.info.tipusOlivaTaula.trencada"/></c:when><c:otherwise><fmt:message key="establiment.vista.info.tipusOlivaTaula.negra"/></c:otherwise></c:choose><br/></c:if><b><c:out value="${lote.codiAssignat}"/></b><br/><b><fmt:message key="establiment.vista.listadoLotes.marca"/>: <c:out value="${lote.etiquetatge.marca.nom}"/></b><br/><c:choose><c:when test="${lote.olivaDO == true}"><fmt:message key="consulta.trazabilitat.resumida.categoria.oliva.DO"/></c:when><c:when test="${lote.olivaDO == false}"><fmt:message key="consulta.trazabilitat.resumida.categoria.oliva.NoDO"/></c:when><c:otherwise><fmt:message key="consulta.trazabilitat.resumida.categoria.oliva.Pendent"/></c:otherwise></c:choose><c:if test="${not empty lote.litresEnvassats}"><br/><fmt:message key="establiment.vista.listadoLotes.litresEnvassats"/>: <fmt:formatNumber value="${lote.litresEnvassats}" maxFractionDigits ="2" /><br/><fmt:message key="establiment.vista.listadoLotes.capacitat"/>: <fmt:formatNumber value="${lote.etiquetatge.tipusEnvas.volum}" maxFractionDigits ="2" /> l.</c:if><c:if test="${not empty lote.producte.id}"><br/><fmt:message key="establiment.vista.listadoLotes.producte"/>: ${lote.producte.nom}</c:if><br/><c:out value="${contingut}" escapeXml="false"/><c:if test="${not empty lote.numeroLot}"><br/><fmt:message key="establiment.vista.listadoLotes.numeroLot"/>: <c:out value="${lote.numeroLot}"/></c:if><br/><fmt:message key="lot.camp.etiquetatge"/>: <c:out value="${lote.etiquetatge.nomEnvas}"/>
										</c:set>
										
// 										<c:set var="codiImatgeContenidor" value="${lote.tipusOlivaTaula}"/>
										<c:if test="${lote.tipusOlivaTaula==0}">
					    					<c:set var="codiImatgeContenidor" value="0"/>
					    				</c:if>
					    				<c:if test="${lote.tipusOlivaTaula==0 && lote.bota.elaboracio.esEcologic}">
				    						<c:set var="codiImatgeContenidor" value="3"/>
				    					</c:if>
				    					<c:if test="${lote.tipusOlivaTaula==1}">
			    							<c:set var="codiImatgeContenidor" value="1"/>
			    						</c:if>
			    						<c:if test="${lote.tipusOlivaTaula==1 && lote.bota.elaboracio.esEcologic}">
		    								<c:set var="codiImatgeContenidor" value="4"/>
		    							</c:if>
		    							<c:if test="${lote.tipusOlivaTaula==2}">
		    								<c:set var="codiImatgeContenidor" value="2"/>
		    							</c:if>
		    							<c:if test="${lote.tipusOlivaTaula==2 && lote.bota.elaboracio.esEcologic}">
	    									<c:set var="codiImatgeContenidor" value="5"/>
	    								</c:if>
					    				
										[
											<c:out value="${lote.id}"/>,
											"${iconesLotOlivaTaula[codiImatgeContenidor]}",
											<c:out value="${lote.posicioX}"/>,
											<c:out value="${lote.posicioY}"/>,
											<c:out value="${proporcion}"/>,
											"<c:out value="${descripcio}" escapeXml="false"/>",
											<c:out value="${dragable}"/>,
											<c:out value="${transparent}"/>,
											<c:out value="${param.seleccionable and selected}"/>,
											"EDD85B",
											<c:out value="${trazable}"/>,
											"2",
											<c:out value="${trazaEnvasos}"/>,
											<c:out value="${lote.zona.id}"/>,
											"<c:out value="${lote.codiAssignat}"/>",
											"true"
										],
									</c:if>
									
									<c:if test="${empty lote.tipusOlivaTaula}">
										<c:set var="codiImatgeContenidor" value="${lote.etiquetatge.tipusEnvas.materialTipusEnvas.icona}" />
										[
											<c:out value="${lote.id}"/>,
											"ArxiuMostrar.html?id=<c:out value="${codiImatgeContenidor}"/>",
											<c:out value="${lote.posicioX}"/>,
											<c:out value="${lote.posicioY}"/>,
											<c:out value="${proporcion}"/>,
											"<c:out value="${descripcio}" escapeXml="false"/>",
											<c:out value="${dragable}"/>,
											<c:out value="${transparent}"/>,
											<c:out value="${param.seleccionable and selected}"/>,
											"EDD85B",
											<c:out value="${trazable}"/>,
											"2",
											<c:out value="${trazaLots}"/>,
											<c:out value="${lote.zona.id}"/>,
											"<c:out value="${lote.codiAssignat}"/>",
											"false"
										],
									</c:if>
								</c:forEach>[]],
								
								<%-- Generam el llistat de botes --%>
					botes: [
					    	<c:forEach var="bota" items="${botes}">
					    		<c:set var="selected" value="${false}"/>
					    		<c:set var="transparent">
					    			<c:choose>
					    				<c:when test="${empty contenidorInfo or contenidorInfo[0] == botes[0]}">false</c:when>
					    			<c:otherwise>true</c:otherwise>
					    			</c:choose>
					    		</c:set>

					    										
					    		<%--Info del tooltip de les botes--%>
					    		<c:set var="descripcio">
					    					<b><c:out value="${bota.identificador}"/></b><br/><fmt:message key="establiment.vista.info.bota.dataInici"/>: <c:out value="${bota.elaboracio.data}"/><br/><fmt:message key="establiment.vista.info.bota.kgInici"/>: <c:out value="${bota.kgOliva}"/><br/><fmt:message key="establiment.vista.info.bota.kgRestants"/>: <c:out value="${bota.kgOlivaRestant}"/><br/>
					    		</c:set>

					    		<c:set var="trazable">true</c:set>

					    		<c:set var="dragable">
					    			<c:choose>
					    				<c:when test="${not empty esDoGestor || not empty esAdministracio || not empty esDoControlador }">false</c:when>
					    				<c:otherwise>true</c:otherwise>
					    			</c:choose>
					    		</c:set> 

					    		
					    	
					    		<c:if test="${not empty esConsulta || not empty esAdmin}"><c:set var="dragable" value="false"/></c:if>

					    		
					    		<c:if test="${bota.tipusOlivaTaula==0}">
					    			<c:set var="codiImatgeContenidor" value="0"/>
					    		</c:if>
					    		<c:if test="${bota.tipusOlivaTaula==0 && bota.elaboracio.esEcologic}">
				    			<c:set var="codiImatgeContenidor" value="3"/>
				    			</c:if>
					    		<c:if test="${bota.tipusOlivaTaula==1}">
					    			<c:set var="codiImatgeContenidor" value="1"/>
					    		</c:if>
					    		<c:if test="${bota.tipusOlivaTaula==1 && bota.elaboracio.esEcologic}">
				    			<c:set var="codiImatgeContenidor" value="4"/>
				    			</c:if>
					    		<c:if test="${bota.tipusOlivaTaula==2}">
					    			<c:set var="codiImatgeContenidor" value="2"/>
					    		</c:if>
					    		<c:if test="${bota.tipusOlivaTaula==2 && bota.elaboracio.esEcologic}">
				    			<c:set var="codiImatgeContenidor" value="5"/>
				    			</c:if>
					    	
					    		[
					    			"<c:out value="${bota.id}"/>",
					    			"${iconesBotes[codiImatgeContenidor]}",
					    			<c:out value="${bota.posicioX}"/>,
					    			<c:out value="${bota.posicioY}"/>,
					    			${bota.kgOlivaRestant * 100 / bota.kgOliva},
					    			"<c:out value="${descripcio}" escapeXml="false"/>",
					    			<c:out value="${dragable}"/>,
					    			<c:out value="${transparent}"/>,
					    			<c:out value="${param.seleccionable and selected}"/>,
					    			"EDD85B",
					    			<c:out value="${trazable}"/>,
					    			"3",
					    			<c:out value="${trazaBotes}"/>,
					    			<c:out value="${bota.zona.id}"/>,
					    			"<c:out value="${bota.identificador}"/>",
					    			"true"
					    		],	
					    	</c:forEach>[]],
									
								imgIds: null,
		 	 					imgAlt: null,
		  						imgAmple: null				
		});

		function precarregaImatge(src, i) {
			var img = new Image();
			img.src = src;
			if (img.width <= 0 || img.height <= 0)
				return false;
			imgAlt[i] = img.height;
			imgAmple[i] = img.width;
			return true;
		}

		var reintents = 0;
		
		function mapaEstablimentInit() {
			var resultat;
			var ids = new Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 15, 16, 17, 18, 19, 20, <c:out value="${imatgeZona}"/>);
			for (var i = 0; i < ids.length; i++) {
				if (reintents < 10) {
					resultat = precarregaImatge('ArxiuMostrar.html?id=' + ids[i], i);
					if (!resultat) {
						reintents = reintents + 1;
						break;
					}
				} else {
					imgAlt = 	[43, 33, 44, 44, 42, 41, 41, 44, 39, 25, 47, 43, 33, 44, 43, 33, 44, 600];
					imgAmple = 	[35, 60, 42, 28, 28, 28, 38, 41, 40, 41, 50, 35, 60, 42, 35, 60, 42, 600];
				}
			}
			if (!resultat) {
				setTimeout('mapaEstablimentInit()', 100);
			} else {
				mapaEstabliment.imgIds = ids;
				mapaEstabliment.imgAlt = imgAlt;
				mapaEstabliment.imgAmple = imgAmple;
				<c:choose>
					<c:when test="${not empty procsel && procsel.mostrarDiposit && procsel.mostrarLote}">
						contenidorService.seleccioLlistat('<c:out value="${procsel.accio}"/>', 'N', 'S', 'S', 'N', mostrarMapaEstabliment);
					</c:when>
					<c:when test="${not empty procsel && procsel.mostrarDiposit}">
						contenidorService.seleccioLlistat('<c:out value="${procsel.accio}"/>', 'N', 'S', 'N', 'N', mostrarMapaEstabliment);
					</c:when>
					<c:when test="${not empty procsel && procsel.mostrarPartida}">
						contenidorService.seleccioLlistat('<c:out value="${procsel.accio}"/>', 'S', 'N', 'N', 'N', mostrarMapaEstabliment);
					</c:when>
					<c:when test="${not empty procsel && procsel.mostrarLote}">
						contenidorService.seleccioLlistat('<c:out value="${procsel.accio}"/>', 'N', 'N', 'S', 'N', mostrarMapaEstabliment);
					</c:when>
					<c:when test="${not empty procsel && procsel.mostrarBotes}">
						contenidorService.seleccioLlistat('<c:out value="${procsel.accio}"/>', 'N', 'N', 'N', 'S', mostrarMapaEstabliment);
					</c:when>
					<c:otherwise>
						mapaEstabliment.mostrar(null);
					</c:otherwise>
				</c:choose>
			}
		}
		
		function mostrarMapaEstabliment(seleccio) {
			mapaEstabliment.mostrar(seleccio);
		}
		
		<c:if test="${not empty contenidorInfo}">
			<c:set var="contingut">
				<c:choose>
					<c:when test="${contenidorInfo[10] - contenidorInfo[11] > 0}">Contingut: <c:out value="${contenidorInfo[10] - contenidorInfo[11]}"/> l<br/>Producte: <c:if test="${not empty contenidorInfo[34]}"><c:out value="${contenidorInfo[34]}"/></c:if><c:if test="${empty contenidorInfo[34]}"><c:out value="${contenidorInfo[13]}"/></c:if><c:if test="${not empty contenidorInfo[6]}"><br/>Partida: <c:out value="${contenidorInfo[6]}"/></c:if></c:when>
					<c:otherwise>[<fmt:message key="establiment.vista.info.buit"/>]</c:otherwise>
				</c:choose>
			</c:set>
		
			<c:set var="descripcio">
				<b><c:out value="${contenidorInfo[1]}"/></b><br/>
				<c:if test="${contenidorInfo[22]!=6}">
					<fmt:message key="establiment.vista.info.capacitat"/>: <c:out value="${contenidorInfo[2]}"/><br/>
					<fmt:message key="establiment.vista.info.material"/>: <c:out value="${contenidorInfo[9]}"/><br/>
				</c:if>
				<c:out value="${contingut}" escapeXml="false"/>
			</c:set>
		
			function situarAqui() {
				<c:set var="codiImatgeContenidor" value="${contenidorInfo[8]}"/>
				<c:forEach var="contBlanc" items="${contenidorsBlancs}">
					<c:if test="${contenidorInfo[0] == contBlanc.id}">
						<c:set var="codiImatgeContenidor" value="10"/>
					</c:if>
				</c:forEach>
				mapaEstabliment.nouContenidor([
				                       		<c:out value="${contenidorInfo[0]}"/>,
				                       		"ArxiuMostrar.html?id=<c:out value="${codiImatgeContenidor}"/>",
				                       		0,
				                       		0,
				                       		<c:out value="${(contenidorInfo[10] - contenidorInfo[11]) * 100 / contenidorInfo[2]}"/>,
				                       		"<c:out value="${descripcio}" escapeXml="false"/>",
				                       		true,
				                       		false,
				                       		false,
				                       		"<c:out value="${contenidorInfo[14]}"/>"]);
				guardaPosicio(<c:out value="${contenidorInfo[0]}"/>, 0, 0, 7);
			}
		</c:if>
	
		YAHOO.util.Event.addListener(window, "load", mapaEstablimentInit);
		// ]]>
	</script>
</c:if>

<c:if test="${not empty zones}">
	<div id="fons" style="position: relative; top: 0; left: 39px !important; left: 0px;"></div>

	<div id="explorer_no_funciona" style="display: none">
		<img src="ArxiuMostrar.html?id=<c:out value="${imatgeZona}"/>" alt="Izona" />
		<img src="ArxiuMostrar.html?id=1" alt="contenidor" /> 
		<img src="ArxiuMostrar.html?id=2" alt="contenidor" /> 
		<img src="ArxiuMostrar.html?id=3" alt="contenidor" /> 
		<img src="ArxiuMostrar.html?id=4" alt="contenidor" /> 
		<img src="ArxiuMostrar.html?id=5" alt="contenidor" /> 
		<img src="ArxiuMostrar.html?id=6" alt="contenidor" /> 
		<img src="ArxiuMostrar.html?id=7" alt="contenidor" /> 
		<img src="ArxiuMostrar.html?id=8" alt="contenidor" /> 
		<img src="ArxiuMostrar.html?id=9" alt="contenidor" /> 
		<img src="ArxiuMostrar.html?id=10" alt="contenidor" /> 
		<img src="ArxiuMostrar.html?id=11" alt="contenidor" />
		<img src="ArxiuMostrar.html?id=15" alt="contenidor" />
		<img src="ArxiuMostrar.html?id=16" alt="contenidor" />
		<img src="ArxiuMostrar.html?id=17" alt="contenidor" />
		<img src="ArxiuMostrar.html?id=18" alt="contenidor" />
		<img src="ArxiuMostrar.html?id=19" alt="contenidor" />
		<img src="ArxiuMostrar.html?id=20" alt="contenidor" />
	</div>
</c:if>