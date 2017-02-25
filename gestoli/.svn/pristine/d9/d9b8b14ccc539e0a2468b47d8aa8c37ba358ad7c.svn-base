<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>


<html>
<head>
	<title>
		<c:choose>
			<c:when test="${not empty formData.id}">
				<fmt:message key="manteniment.modificacio" />
			</c:when>
			<c:otherwise>
				<fmt:message key="manteniment.alta" />
			</c:otherwise>
		</c:choose> 
		
		<fmt:message key="usuari.tipusdemant" />
	</title>
</head>
<body>
	<div>
		<c:choose>
			<c:when test="${not empty esDoGestor || not empty esEstAdministrador}">
				<c:set var="mostrar" value="true" scope="request" />
			</c:when>
			<c:when
				test="${(not empty esAdministracio || not empty esDoControlador || not empty esOlivicultor || not empty esProductor || not empty esEnvasador) and not empty formData.id  and usuari.id == formData.id and formData.actiu==true}">
				<c:set var="mostrar" value="true" scope="request" />
			</c:when>
			<c:otherwise>
				<c:set var="mostrar" value="false" scope="request" />
			</c:otherwise>
		</c:choose> 
		
		<c:if test="${mostrar==true}">
			<form 	id="formulario" action="UsuariForm.html" method="post"
					class="seguit">
				<c:if test="${not empty formData.id}">
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="hidden" />
						<c:param name="path" value="formData.id" />
						<c:param name="camp" value="id" />
					</c:import>
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="hidden" />
						<c:param name="path" value="formData.contrasenyaEncriptada" />
						<c:param name="camp" value="contrasenyaEncriptada" />
					</c:import>
				</c:if> 
				<c:choose>
					<c:when test="${not empty formData.id && usuari.id == formData.id}">
						<c:import url="comu/CampFormulari.jsp">
							<c:param name="tipus" value="text" />
							<c:param name="path" value="formData.usuari" />
							<c:param name="required" value="required" />
							<c:param name="maxlength" value="16" />
							<c:param name="title">
								<fmt:message key="usuari.camp.usuari" />
							</c:param>
							<c:param name="camp" value="usuari" />
							<c:param name="clase" value="campoFormGrande readOnly conMargen" />
							<c:param name="readonly" value="true" />
						</c:import>
					</c:when>
					<c:otherwise>
						<c:import url="comu/CampFormulari.jsp">
							<c:param name="tipus" value="text" />
							<c:param name="path" value="formData.usuari" />
							<c:param name="required" value="required" />
							<c:param name="maxlength" value="16" />
							<c:param name="title">
								<fmt:message key="usuari.camp.usuari" />
							</c:param>
							<c:param name="camp" value="usuari" />
							<c:param name="clase" value="campoFormGrande conMargen" />
						</c:import>
					</c:otherwise>
				</c:choose> 
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="contrasenya" />
					<c:param name="path" value="formData.contrasenya" />
					<c:param name="required" value="required" />
					<c:param name="maxlength" value="16" />
					<c:param name="title">
						<fmt:message key="usuari.camp.contrasenya" />
					</c:param>
					<c:param name="camp" value="contrasenya" />
					<c:param name="clase" value="campoFormGrande" />
				</c:import>
		
				<div class="separadorH"></div>
		
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="select" />
					<c:param name="path" value="formData.idiomaId" />
					<c:param name="title">
						<fmt:message key="usuari.camp.idioma" />
					</c:param>
					<c:param name="camp" value="idiomaId" />
					<c:param name="clase" value="campoFormGrande conMargen" />
					<c:param name="required" value="required" />
					<c:param name="selectItems" value="idiomas" />
					<c:param name="selectItemsId" value="id" />
					<c:param name="selectItemsValue" value="nom" />
					<c:param name="selectSelectedValue" value="${formData.idioma.id}" />
				</c:import> 
				<c:import url="comu/CampFormulari.jsp">
					<c:param name="tipus" value="text" />
					<c:param name="path" value="formData.email" />
					<c:param name="required" value="required" />
					<c:param name="title">
						<fmt:message key="usuari.camp.email" />
					</c:param>
					<c:param name="camp" value="email" />
					<c:param name="maxlength" value="128" />
					<c:param name="clase" value="campoFormGrande" />
				</c:import>
		
				<div class="separadorH"></div>
		
				<c:choose>
					<c:when test="${empty esDoGestor or not empty formData.id}">
						<c:import url="comu/CampFormulari.jsp">
							<c:param name="tipus" value="hidden" />
							<c:param name="path" value="formData.tipusUsuari" />
							<c:param name="camp" value="tipusUsuari" />
							<c:param name="value" value="${usuariCommand.tipusUsuari}" />
						</c:import>
						<c:import url="comu/CampFormulari.jsp">
							<c:param name="tipus" value="tipusUsuari" />
							<c:param name="path" value="formData.tipusUsuari" />
							<c:param name="title">
								<fmt:message key="usuari.camp.tipus" />
							</c:param>
							<c:param name="camp" value="tipusUsuari" />
							<c:param name="clase" value="campoFormGrande conMargen" />
							<c:param name="required" value="required" />
							<c:param name="disabled" value="disabled" />
							<c:param name="selectSelectedValue" value="${usuariCommand.tipusUsuari}" />
						</c:import>
					</c:when>
					<c:otherwise>
						<c:import url="comu/CampFormulari.jsp">
							<c:param name="tipus" value="tipusUsuari" />
							<c:param name="path" value="formData.tipusUsuari" />
							<c:param name="title">
								<fmt:message key="usuari.camp.tipus" />
							</c:param>
							<c:param name="camp" value="tipusUsuari" />
							<c:param name="clase" value="campoFormGrande conMargen" />
							<c:param name="required" value="required" />
							<c:param name="onchange" value="canviTipusUsuari()"/>
						</c:import>
			    	</c:otherwise>
			    </c:choose>
		
				<c:if test="${not empty esProductor || not empty esEnvasador}">	    
				    <div class="separadorH"></div>
			
					<c:choose>
						<c:when test="${empty esEstAdministrador or not empty formData.id}">
							<c:import url="comu/CampFormulari.jsp">
								<c:param name="tipus" value="hidden" />
								<c:param name="path" value="formData.tipusUsuariEstabliment" />
								<c:param name="camp" value="tipusUsuariEstabliment" />
							</c:import>
							<c:import url="comu/CampFormulari.jsp">
								<c:param name="tipus" value="tipusUsuariEstabliment" />
								<c:param name="path" value="formData.tipusUsuariEstabliment" />
								<c:param name="title">
									<fmt:message key="usuari.camp.tipusEstabliment" />
								</c:param>
								<c:param name="camp" value="tipusUsuariEstabliment" />
								<c:param name="clase" value="campoFormGrande conMargen" />
								<c:param name="required" value="required" />
								<c:param name="disabled" value="disabled" />
							</c:import>
							<c:if test="${not empty estOlivaTaula}">
								<c:import url="comu/CampFormulari.jsp">
									<c:param name="tipus" value="checkbox" />
									<c:param name="path" value="formData.olivaTaula" />
									<c:param name="title">
										<fmt:message key="establiment.olivataula.treballador" />
									</c:param>
									<c:param name="camp" value="olivaTaula" />
									<c:param name="readonly" value="true" />
									<c:param name="disabled" value="disabled" />
								</c:import>
								<c:import url="comu/CampFormulari.jsp">
									<c:param name="tipus" value="hidden" />
									<c:param name="path" value="formData.olivaTaula" />
									<c:param name="camp" value="olivaTaula" />
								</c:import>
							</c:if>
						</c:when>
						<c:otherwise>
								<c:import url="comu/CampFormulari.jsp">
									<c:param name="tipus" value="tipusUsuariEstabliment" />
									<c:param name="path" value="formData.tipusUsuariEstabliment" />
									<c:param name="title">
										<fmt:message key="usuari.camp.tipusEstabliment" />
									</c:param>
									<c:param name="camp" value="tipusUsuariEstabliment" />
									<c:param name="clase" value="campoFormGrande conMargen" />
									<c:param name="required" value="required" />
								</c:import>
								<c:if test="${not empty estOlivaTaula}">
									<c:import url="comu/CampFormulari.jsp">
										<c:param name="tipus" value="checkbox" />
										<c:param name="path" value="formData.olivaTaula" />
										<c:param name="title">
											<fmt:message key="establiment.olivataula.treballador" />
										</c:param>
										<c:param name="camp" value="olivaTaula" />
									</c:import>
								</c:if>
				    	</c:otherwise>
				    </c:choose>
				   </c:if>
				
				
				<c:if test="${not empty esDoGestor || not empty esEstAdministrador}">
				<div id="tafona" 
					<c:if test="${	formData.tipusUsuari != '4'}">
						style="display:none;"
					</c:if>
				>
			
					<c:choose>
						<c:when test="${(empty esDoGestor and empty esEstAdministrador) or not empty formData.id}">
							<c:import url="comu/CampFormulari.jsp">
								<c:param name="tipus" value="selectSenseBuit" />
								<c:param name="path" value="formData.establimentIdTafona" />
								<c:param name="title">
									<fmt:message key="usuari.camp.establiment" />
								</c:param>
								<c:param name="camp" value="establimentIdTafona" />
								<c:param name="clase" value="campoFormGrande conMargen" />
								<c:param name="required" value="required" />
								<c:param name="disabled" value="disabled" />
								<c:param name="selectItems" value="tafones" />
								<c:param name="selectItemsId" value="id" />
								<c:param name="selectItemsValue" value="nom" />
								<c:param name="selectSelectedValue" value="${formData.establimentIdTafona}"/>
							</c:import>
						</c:when>
						<c:otherwise>
							<c:import url="comu/CampFormulari.jsp">
								<c:param name="tipus" value="selectSenseBuit" />
								<c:param name="path" value="formData.establimentIdTafona" />
								<c:param name="title">
									<fmt:message key="usuari.camp.establiment" />
								</c:param>
								<c:param name="camp" value="establimentIdTafona" />
								<c:param name="clase" value="campoFormGrande conMargen" />
								<c:param name="required" value="required" />
								<c:param name="selectItems" value="tafones" />
								<c:param name="selectItemsId" value="id" />
								<c:param name="selectItemsValue" value="nom" />
								<c:param name="selectSelectedValue" value="${formData.establimentIdTafona}"/>
							</c:import>
				    	</c:otherwise>
				    </c:choose>
				</div>
				
				<div id="envasadora" 
					<c:if test="${	formData.tipusUsuari != '5'}">
						style="display:none;"
					</c:if>
				>
					<c:choose>
						<c:when test="${(empty esDoGestor and empty esEstAdministrador) or not empty formData.id}">
							<c:import url="comu/CampFormulari.jsp">
								<c:param name="tipus" value="selectSenseBuit" />
								<c:param name="path" value="formData.establimentIdEnvasadora" />
								<c:param name="title">
									<fmt:message key="usuari.camp.establiment" />
								</c:param>
								<c:param name="camp" value="establimentIdEnvasadora" />
								<c:param name="clase" value="campoFormGrande conMargen" />
								<c:param name="required" value="required" />
								<c:param name="disabled" value="disabled" />
								<c:param name="selectItems" value="envasadores" />
								<c:param name="selectItemsId" value="id" />
								<c:param name="selectItemsValue" value="nom" />
								<c:param name="selectSelectedValue" value="${formData.establimentIdEnvasadora}"/>
							</c:import>
						</c:when>
						<c:otherwise>
							<c:import url="comu/CampFormulari.jsp">
								<c:param name="tipus" value="selectSenseBuit" />
								<c:param name="path" value="formData.establimentIdEnvasadora" />
								<c:param name="title">
									<fmt:message key="usuari.camp.establiment" />
								</c:param>
								<c:param name="camp" value="establimentIdEnvasadora" />
								<c:param name="clase" value="campoFormGrande conMargen" />
								<c:param name="required" value="required" />
								<c:param name="selectItems" value="envasadores" />
								<c:param name="selectItemsId" value="id" />
								<c:param name="selectItemsValue" value="nom" />
								<c:param name="selectSelectedValue" value="${formData.establimentIdEnvasadora}"/>
							</c:import>
				    	</c:otherwise>
				    </c:choose>
				</div>
				
				<div id="tafenv" 
					<c:if test="${	formData.tipusUsuari != '6'}">
						style="display:none;"
					</c:if>
				>
					<c:choose>
						<c:when test="${(empty esDoGestor and empty esEstAdministrador) or not empty formData.id}">
							<c:import url="comu/CampFormulari.jsp">
								<c:param name="tipus" value="selectSenseBuit" />
								<c:param name="path" value="formData.establimentIdTafEnv" />
								<c:param name="title">
									<fmt:message key="usuari.camp.establiment" />
								</c:param>
								<c:param name="camp" value="establimentIdTafEnv" />
								<c:param name="clase" value="campoFormGrande conMargen" />
								<c:param name="required" value="required" />
								<c:param name="disabled" value="disabled" />
								<c:param name="selectItems" value="establiments" />
								<c:param name="selectItemsId" value="id" />
								<c:param name="selectItemsValue" value="nom" />
								<c:param name="selectSelectedValue" value="${formData.establimentIdTafEnv}"/>
							</c:import>
						</c:when>
						<c:otherwise>
							<c:import url="comu/CampFormulari.jsp">
								<c:param name="tipus" value="selectSenseBuit" />
								<c:param name="path" value="formData.establimentIdTafEnv" />
								<c:param name="title">
									<fmt:message key="usuari.camp.establiment" />
								</c:param>
								<c:param name="camp" value="establimentIdTafEnv" />
								<c:param name="clase" value="campoFormGrande conMargen" />
								<c:param name="required" value="required" />
								<c:param name="selectItems" value="establiments" />
								<c:param name="selectItemsId" value="id" />
								<c:param name="selectItemsValue" value="nom" />
								<c:param name="selectSelectedValue" value="${formData.establimentIdTafEnv}"/>
							</c:import>
				    	</c:otherwise>
				    </c:choose>
				</div>
				
				<div class="separadorH"></div>
				
				</c:if>
			
				<c:if test="${not empty formData.olivicultorName}">
					
					<div class="separadorH"></div>
					
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="static" />
						<c:param name="path" value="formData.olivicultorName" />
						<c:param name="title">
							<fmt:message key="usuari.camp.olivicultor" />
						</c:param>
						<c:param name="value" value="${formData.olivicultorName}" />
						<c:param name="clase" value="campoFormGrande" />
					</c:import>
					<input type="hidden" id="olivicultorName" name="olivicultorName"
						value="<c:out value="${formData.olivicultorName}"/>" />
				</c:if> 
			
				<c:if test="${not empty formData.establimentName && empty esDoGestor && empty esEstAdministrador}">
					
					<div class="separadorH"></div>
					
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="static" />
						<c:param name="path" value="formData.establimentName" />
						<c:param name="title">
							<fmt:message key="usuari.camp.establiment" />
						</c:param>
						<c:param name="value" value="${formData.establimentName}" />
						<c:param name="clase" value="campoFormGrande" />
					</c:import>
					<input type="hidden" id="establimentName" name="establimentName"
						value="<c:out value="${formData.establimentName}"/>" />
				</c:if> 
	
				<c:if test="${not empty formData.id && (not empty esDoGestor || not empty esEstAdministrador)}">
					
					<div class="separadorH"></div>
					
					<div class="etiqueta <c:out value="${param.required}"/><c:if test="${not empty status.errorMessage}"> error</c:if>">
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="checkbox" />
						<c:param name="path" value="formData.actiu" />
						<c:param name="title">
							<fmt:message key="usuari.camp.actiu" />
						</c:param>
						<c:param name="camp" value="actiu" />
					</c:import></div>
				</c:if> 
			
				<c:if test="${not empty formData.id && empty esDoGestor && empty esEstAdministrador}">
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="hidden" />
						<c:param name="path" value="formData.actiu" />
						<c:param name="title">
							<fmt:message key="usuari.camp.actiu" />
						</c:param>
						<c:param name="camp" value="actiu" />
					</c:import>
				</c:if>
		
				<div class="separadorH"></div>
		
				<div id="observacionesForm"
					class="campoForm <c:out value="${param.required}"/><c:if test="${not empty status.errorMessage}"> error</c:if>">
					<c:import url="comu/CampFormulari.jsp">
						<c:param name="tipus" value="textarea" />
						<c:param name="path" value="formData.observacions" />
						<c:param name="title">
							<fmt:message key="usuari.camp.observacions" />
						</c:param>
						<c:param name="camp" value="observacions" />
					</c:import>
				</div>
		
				<div class="separadorH"></div>
		
				<div class="botonesForm">
					<c:if test="${not empty formData.id}">
						<div 	id="guardarForm" class="btnCorto"
								onclick="if(confirm('<fmt:message key="manteniment.modificar.confirm"/>')){submitForm('formulario')}"
								onmouseover="underline('enlaceGuardarForm')"
								onmouseout="underline('enlaceGuardarForm')">
									<a id="enlaceGuardarForm" href="javascript:void(0);">
										<fmt:message key="manteniment.guardar" />
									</a>
						</div>
					</c:if> 
					<c:if test="${empty formData.id}">
						<div 	id="guardarForm" class="btnCorto"
								onclick="submitForm('formulario')"
								onmouseover="underline('enlaceGuardarForm')"
								onmouseout="underline('enlaceGuardarForm')">
									<a id="enlaceGuardarForm" href="javascript:void(0);">
										<fmt:message key="manteniment.guardar" />
									</a>
						</div>
					</c:if> 
					<c:choose>
						<c:when test="${not empty esDoGestor || not empty esEstAdministrador}">
							<div id="cancelarForm" class="btnCorto"
								onmouseover="underline('enlaceCancelarForm')"
								onmouseout="underline('enlaceCancelarForm')"
								onclick="document.location ='Usuari.html';">
									<a id="enlaceCancelarForm" href="javascript:void(0);">
										<fmt:message key="manteniment.tornar" />
									</a>
							</div>
						</c:when>
						<c:when
							test="${not empty esAdministracio || not empty esDoControlador || not empty esOlivicultor || not empty esProductor || not empty esEnvasador}">
							<div id="cancelarForm" class="btnCorto"
								onmouseover="underline('enlaceCancelarForm')"
								onmouseout="underline('enlaceCancelarForm')"
								onclick="document.location ='UsuariForm.html?id=<c:out value='${formData.id}'/>';">
									<a id="enlaceCancelarForm" href="javascript:void(0);">
										<fmt:message key="manteniment.cancelar" />
									</a>
							</div>
						</c:when>
					</c:choose> 
					<c:if test="${not empty formData.id && (not empty esDoGestor || not empty esEstAdministrador)}">
						<input id="action" name="action" value="delete" type="hidden" />
						<div id="eliminarForm" class="btnCorto"
							onmouseover="underline('enlaceBorrarForm')"
							onmouseout="underline('enlaceBorrarForm')"
							onclick="submitFormConfirm('deleteForm','<fmt:message key="manteniment.esborrar.confirm"/>');">
							<a id="enlaceBorrarForm" href="javascript:void(0);">
								<fmt:message key="manteniment.esborrar" />
							</a>
						</div>
					</c:if>
				</div>
		
			</form>

			<form id="deleteForm" action="Usuari.html" method="post" class="seguit"
				onsubmit="return confirm('<fmt:message key="manteniment.estasegur"/>')">
				<input id="id" name="id" value="<c:out value="${formData.id}"/>" type="hidden" /> 
				<input id="action" name="action" value="delete" type="hidden" />
			</form>
		</c:if> 
		<c:if test="${mostrar==false}">
			<fmt:message key="manteniment.noDatos" />
		</c:if>
	</div>
</body>
</html>
	