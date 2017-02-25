<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/fn-rt.tld"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@page import="es.caib.gestoli.logic.model.Arxiu"%>




<%
    if (request.getParameter("selectItems") != null)
		request.setAttribute("campItems", request.getAttribute(request.getParameter("selectItems")));
	if (request.getParameter("selectSelectedItems") != null)
		request.setAttribute("campItemsB", request.getAttribute(request.getParameter("selectSelectedItems")));
%>

<c:set var="bindPath" value="${param.path}" />
<c:set var="onchange">
	<c:if test="${not empty param.onchange}">onchange="<c:out value="${param.onchange}" />"</c:if>
</c:set>
<c:set var="onclick">
	<c:if test="${not empty param.onclick}">onclick="<c:out
			value="${param.onclick}" />"</c:if>
</c:set>

<spring:bind path="${bindPath}">
	<div id="divIdFor<c:out value="${param.camp}"/>"
		class=" <c:out value="${param.clase}"/> <c:out value="${param.required}"/><c:if test="${not empty status.errorMessage}"> error</c:if>"
		<c:if test="${not empty param.estilo}"> style="<c:out value="${param.estilo}"/>" </c:if>>

	<c:choose>

		<c:when test="${param.tipus == 'hidden'}">
			<input type="hidden" id="<c:out value="${param.camp}"/>"
				name="<c:if test="${empty param.name}"><c:out value="${param.camp}"/></c:if><c:if test="${not empty param.name}"><c:out value="${param.name}"/></c:if>"
				value="<c:choose><c:when test="${not empty status.value}"><c:out value="${status.value}"/></c:when><c:otherwise><c:if test="${not empty param.value}"><c:out value="${param.value}"/></c:if></c:otherwise></c:choose>" />
		</c:when>
		
		<c:when test="${param.tipus == 'suggest'}">
			<label for="<c:out value="${param.camp}"/>"><c:out value="${param.title}" /></label>
			<div class="bordeInput">
				<div id="<c:out value="${param.camp}"/>BordeFoco" class="bordeFoco">
					<input type="hidden" 
						id="${param.camp}" 
						name="<c:if test="${empty param.name}"><c:out value="${param.camp}"/></c:if><c:if test="${not empty param.name}"><c:out value="${param.name}"/></c:if>" 
						value="${status.value}"
					/>
					<input type="text" 
						id="suggest_<c:out value="${param.camp}"/>" 
						name="suggest_<c:if test="${empty param.name}"><c:out value="${param.camp}"/></c:if><c:if test="${not empty param.name}"><c:out value="${param.name}"/></c:if>" 
						class="inputText" 
						<c:if test="${not empty param.readonly}">readonly="readonly"</c:if> 
						<c:if test="${not empty param.disabled}"> disabled="<c:out value="${param.disabled}"/>"</c:if> 
						value="<c:choose><c:when test="${not empty param.value}"><c:out value="${param.value}"/></c:when><c:otherwise><c:out value="${status.value}"/></c:otherwise></c:choose>" 
						onchange="<c:out value="${param.onchange}"/>" 
						onkeydown="<c:out value="${param.onkeydown}"/>" 
						onkeypress="<c:out value="${param.onkeypress}"/>" 
						onkeyup="<c:out value="${param.onkeyup}"/>" 
						onfocus="activaFoco(this,'<c:out value="${param.camp}"/>BordeFoco')" 
						onblur="desactivaFoco(this,'<c:out value="${param.camp}"/>BordeFoco')" 
						maxlength="<c:out value="${param.maxlength}"/>" 
						<c:if test="${not empty param.autocompleteOff}">autocomplete="off"</c:if> 
					/>
					<c:if test="${not empty param.aclaracio}">
						<p class="comentari"><c:out value="${param.aclaracio}" escapeXml="false" /></p>
					</c:if>
					<c:set var="registres">
						[<c:forEach var="registre" items="${campItems}" varStatus="stat">{codi:'${registre['id']}', nom:'${registre['nom']}'}<c:if test="${not stat.last}">,</c:if></c:forEach>]
					</c:set>
					<script type="text/javascript">
						// <![CDATA[
						$(document).ready(function(){
							autocompletar("${param.camp}", ${registres});
						});
						// ]]>
					</script>
				</div>
			</div>
		</c:when>

		<c:when test="${param.tipus == 'text'}">
			<label for="<c:out value="${param.camp}"/>"><c:out
				value="${param.title}" /></label>
			<div class="bordeInput">
			<div id="<c:out value="${param.camp}"/>BordeFoco" class="bordeFoco">
			<input type="text" id="<c:out value="${param.camp}"/>"
				name="<c:if test="${empty param.name}"><c:out value="${param.camp}"/></c:if><c:if test="${not empty param.name}"><c:out value="${param.name}"/></c:if>"
				class="inputText"
				<c:if test="${not empty param.readonly}">readonly="readonly"</c:if>
				<c:if test="${not empty param.read && param.read == 'true'}">readonly="readonly"</c:if>
				<c:if test="${not empty param.disabled}"> disabled="<c:out value="${param.disabled}"/>"</c:if>
				value="<c:choose><c:when test="${not empty param.value}"><c:out value="${param.value}" escapeXml="false"/></c:when><c:otherwise><c:out value="${status.value}" escapeXml="false"/></c:otherwise></c:choose>"
				onchange="<c:out value="${param.onchange}"/>"
				onkeydown="<c:out value="${param.onkeydown}"/>"
				onkeypress="<c:out value="${param.onkeypress}"/>"
				onkeyup="<c:out value="${param.onkeyup}"/>"
				onfocus="activaFoco(this,'<c:out value="${param.camp}"/>BordeFoco')"
				onblur="desactivaFoco(this,'<c:out value="${param.camp}"/>BordeFoco'); <c:out value="${param.onblur}"/>"
				maxlength="<c:out value="${param.maxlength}"/>" 
				<%--c:if test="${not empty param.autocompleteOff}">AUTOCOMPLETE="off"</c:if--%> 
				autocomplete="off" 
				/>
			<c:if test="${not empty param.aclaracio}">
				<p class="comentari"><c:out value="${param.aclaracio}" escapeXml="false" /></p>
			</c:if></div>
			</div>
		</c:when>

		<c:when test="${param.tipus == 'textConName'}">
			<label for="<c:out value="${param.camp}"/>"><c:out
				value="${param.title}" /></label>
			<div class="bordeInput">
			<div id="<c:out value="${param.camp}"/>BordeFoco" class="bordeFoco">
			<input type="text" id="<c:out value="${param.camp}"/>"
				name="<c:out value="${param.name}"/>" class="inputText"
				<c:if test="${not empty param.disabled}"> disabled="<c:out value="${param.disabled}"/>"</c:if>
				value="<c:out value="${status.value}"/>"
				onchange="<c:out value="${param.onchange}"/>"
				onfocus="activaFoco(this,'<c:out value="${param.camp}"/>BordeFoco')"
				onblur="desactivaFoco(this,'<c:out value="${param.camp}"/>BordeFoco')"
				maxlength="<c:out value="${param.maxlength}"/>" /></div>
			</div>
		</c:when>


		<c:when test="${param.tipus == 'contrasenya'}">
			<label for="<c:out value="${param.camp}"/>"><c:out
				value="${param.title}" /></label>
			<div class="bordeInput">
			<div id="<c:out value="${param.camp}"/>BordeFoco" class="bordeFoco">
			<input type="password" id="<c:out value="${param.camp}"/>"
				name="<c:out value="${param.camp}"/>" class="inputText"
				value="<c:out value="${status.value}"/>"
				onchange="<c:out value="${param.onchange}"/>"
				onfocus="activaFoco(this,'<c:out value="${param.camp}"/>BordeFoco')"
				onblur="desactivaFoco(this,'<c:out value="${param.camp}"/>BordeFoco')"
				maxlength="<c:out value="${param.maxlength}"/>" /></div>
			</div>
		</c:when>

		<c:when test="${param.tipus == 'calendar'}">
			<script type="text/javascript">
				function parseData_${param.camp}(data){
					var str = data.value.split("/");

					if(str[2] != null){
						if(str[2].length == 2){
							data.value = str[0] + "/" + str[1] + "/" + "20" + str[2];
						}
						if(str[2].length == 3){
							data.value = str[0] + "/" + str[1] + "/" + "2" + str[2];
						}
					}
				}
			</script>
			<label for="<c:out value="${param.camp}"/>"><c:out
				value="${param.title}" /></label>
			<div class="bordeInput">
			<div id="<c:out value="${param.camp}"/>BordeFoco" class="bordeFoco">
			<input type="text"
				<c:if test="${not empty param.disabled}">disabled="disabled"</c:if>
				<c:if test="${param.readonly=='1'}">readonly="readonly"</c:if>
				id="<c:out value="${param.camp}"/>"
				name="<c:if test="${empty param.name}"><c:out value="${param.camp}"/></c:if><c:if test="${not empty param.name}"><c:out value="${param.name}"/></c:if>"
				class="inputData" value="<c:out value="${status.value}"/>"
				onchange="<c:out value="${param.onchange}"/>"
				onblur="parseData_${param.camp}(this);"
				maxlength="<c:out value="${param.maxlength}"/>" /> <c:if
				test="${not empty param.aclaracio}">
				<p class="comentari"><c:out value="${param.aclaracio}"
					escapeXml="false" /></p>
			</c:if></div>
			</div>
		</c:when>

		<c:when test="${param.tipus == 'static'}">
			<label><c:out value="${param.title}" /></label>
			<%-- div class="bordeInput" --%>
			<div style="float: right;">
			<div class="bordeFoco">
			<p class="inputStatic"><c:choose>
				<c:when test="${not empty param.value}">
					<c:out value="${param.value}" />
				</c:when>
				<c:otherwise>
					<c:if test="${not empty status.value}">
						<c:out value="${status.value[param.atribut]}" />
					</c:if>
				</c:otherwise>
			</c:choose></p>
			</div>
			</div>
		</c:when>

		<c:when test="${param.tipus == 'label'}">
			<label><c:out value="${param.title}" /></label>
			<p><c:out value="${status.value}" /></p>
		</c:when>

		<c:when test="${param.tipus == 'checkbox'}">
			<input type="checkbox" 
				id="<c:out value="${param.camp}"/>" 
				name="<c:out value="${param.camp}"/>" 
				class="inputCheckbox"
				value="S"
				<c:if test="${status.value == 'S'}">checked="checked"</c:if>
				<c:if test="${not empty param.onchange}">onchange="<c:out value="${param.onchange}"/>"</c:if>
				<c:if test="${not empty param.onclick}">onclick="<c:out value="${param.onclick}"/>"</c:if>
				<c:if test="${not empty param.disabled}"> disabled="<c:out value="${param.disabled}"/>"</c:if> />
			<label for="<c:out value="${param.camp}"/>"><c:out value="${param.title}" /></label>
		</c:when>

		<c:when test="${param.tipus == 'checkboxName'}">
			<input type="checkbox" id="<c:out value="${param.camp}"/>"
				name="<c:out value="${param.name}"/>" class="inputCheckbox"
				value="S"
				<c:if test="${status.value == 'S'}">checked="checked"</c:if>
				<c:if test="${not empty param.onchange}">onchange="<c:out value="${param.onchange}"/>"</c:if>
				<c:if test="${not empty param.onclick}">onclick="<c:out value="${param.onclick}"/>"</c:if>
				<c:if test="${not empty param.disabled}"> disabled="<c:out value="${param.disabled}"/>"</c:if> />
			<label for="<c:out value="${param.camp}"/>"><c:out
				value="${param.title}" /></label>
		</c:when>

		<c:when test="${param.tipus == 'checkboxVarios'}">
			<label for="<c:out value="${param.camp}"/>"><c:out
				value="${param.title}" /></label>
			<input type="hidden" name="_checkbox.<c:out value="${param.camp}"/>"
				value="0" />
			<input type="checkbox" id="<c:out value="${param.camp}"/>"
				name="<c:out value="${param.camp}"/>" class="inputCheckbox"
				value="<c:out value="${param.contIdCamp}"/>"
				<c:if test="${status.value == param.contIdCamp}">checked="checked"</c:if>
				<c:if test="${not empty param.onchange}">onchange="<c:out value="${param.onchange}"/>"</c:if>
				<c:if test="${not empty param.onclick}">onclick="<c:out value="${param.onclick}"/>"</c:if> />
		</c:when>

		<c:when test="${param.tipus == 'selectSenseBuit'}">
			<label for="<c:out value="${param.camp}"/>">
				<c:out value="${param.title}" />
			</label>
			<div class="bordeInput">
				<div class="bordeFoco">
					<select id="<c:out value="${param.camp}"/>" name="<c:out value="${param.camp}"/>" class="inputSelect" <c:if test="${not empty param.onchange}">onchange="<c:out value="${param.onchange}"/> "</c:if><c:if test="${not empty param.disabled}">disabled="<c:out value="${param.disabled}"/> "</c:if>>
						<c:forEach var="item" items="${campItems}">
							<option value="<c:out value="${item[param.selectItemsId]}"/>"
								<c:if test="${param.selectSelectedValue == item[param.selectItemsId]}">selected="selected"</c:if>>
								<c:out value="${item[param.selectItemsValue]}" />
							</option>
						</c:forEach>
					</select>
					<c:if test="${not empty param.aclaracio}">
						<p class="comentari"><c:out value="${param.aclaracio}" escapeXml="false" /></p>
					</c:if>
				</div>
			</div>
		</c:when>

		<%-- 
			<c:when test="${param.tipus == 'select'}">
                <label for="<c:out value="${param.camp}"/>"><c:out value="${param.title}"/></label>
                <div class="bordeInput">
                	<div class="bordeFoco">
						<select id="<c:out value="${param.camp}"/><c:if test="${not empty param.count}"><c:out value="${param.count}"/></c:if>" name="<c:out value="${param.camp}"/>" class="inputSelect " <c:if test="${not empty param.onchange}">onchange="<c:out value="${param.onchange}"/>"</c:if> <c:if test="${not empty param.disabled}"> disabled="<c:out value="${param.disabled}"/>"</c:if>>
							<c:if test="${empty param.ambOpcioBuida or param.opcioBuida}"><option value="" <c:if test="${empty param.selectSelectedValue}">selected="selected"</c:if>>- - - - -</option></c:if>
							<c:forEach var="item" items="${campItems}">
				    			<option value="<c:out value="${item[param.selectItemsId]}"/>" <c:if test="${param.selectSelectedValue == item[param.selectItemsId]}">selected="selected"</c:if>><c:out value="${item[param.selectItemsValue]}"/></option>
				    		</c:forEach>
						</select>
					</div>
				</div>
			</c:when>
			--%>

		<c:when test="${param.tipus == 'select'}">
			<label for="<c:out value="${param.camp}"/>">
				<c:out value="${param.title}" />
			</label>
			<div class="bordeInput">
			<div class="bordeFoco">
				<select id="<c:out value="${param.camp}"/><c:if test="${not empty param.count}"><c:out value="${param.count}"/></c:if>"
						name="<c:if test="${empty param.name}"><c:out value="${param.camp}"/></c:if><c:if test="${not empty param.name}"><c:out value="${param.name}"/></c:if>"
						class="inputSelect"<c:if test="${not empty param.onchange}"> onchange="<c:out value="${param.onchange}"/>"</c:if><c:if test="${not empty param.disabled}"> disabled="<c:out value="${param.disabled}"/>"</c:if>>
					<c:if test="${empty param.ambOpcioBuida or param.opcioBuida}">
						<option value="" <c:if test="${empty param.selectSelectedValue}">selected="selected"</c:if>>- - - - -</option>
					</c:if>
					<c:forEach var="item" items="${campItems}">
						<option value="<c:out value="${item[param.selectItemsId]}"/>"
							<c:if test="${param.selectSelectedValue == item[param.selectItemsId]}">selected="selected"</c:if>>
							<c:out value="${item[param.selectItemsValue]}" />
						</option>
					</c:forEach>
				</select>
				<c:if test="${not empty param.aclaracio}">
					<p class="comentari"><c:out value="${param.aclaracio}" escapeXml="false" /></p>
				</c:if>
			</div>
			</div>
		</c:when>

		<c:when test="${param.tipus == 'radio'}">
			<p class="radioTitle"><span><c:out value="${param.title}" /></span></p>
			<div class="formRadio"><c:forEach var="item"
				items="${campItems}">
				<div class="itemRadio"><c:if
					test="${param.selectSelectedValue == item[param.selectItemsId] }">
					<input type="radio" name="<c:out value="${param.camp}"/>"
						checked="checked"
						<c:if test="${not empty param.onclick}"> onclick="<c:out value="${param.onclick}"/>"</c:if>
						value="${item[param.selectItemsId]}"
						id="<c:out value="${item[param.selectItemsId]}"/>"
						<c:if test="${not empty param.readonly}">disabled="disabled"</c:if> />
				</c:if> <c:if
					test="${param.selectSelectedValue != item[param.selectItemsId] }">
					<input type="radio" name="<c:out value="${param.camp}"/>"
						<c:if test="${not empty param.onclick}"> onclick="<c:out value="${param.onclick}"/>"</c:if>
						value="${item[param.selectItemsId]}"
						id="<c:out value="${item[param.selectItemsId]}"/>"
						<c:if test="${not empty param.readonly}">disabled="disabled"</c:if> />
				</c:if> <label for="<c:out value="${item[param.selectItemsId]}"/>"><c:out
					value="${item[param.selectItemsValue]}" /></label></div>
			</c:forEach></div>
		</c:when>

		<c:when test="${param.tipus == 'selectMultiple'}">

			<table class="selectMultiple" border="0" cellpadding="0"
				cellspacing="0">
				<tr>
					<td><small><fmt:message key="${param.titleIzquierda}" /></small></td>
					<td></td>
					<td><small><fmt:message key="${param.titleDerecha}" /></small></td>
				</tr>
				<tr>
					<td class="contenedorSelectMultiple"><select
						id="id_<c:out value="${param.camp}"/>"
						name="<c:if test="${empty param.name}"><c:out value="${param.camp}"/></c:if><c:if test="${not empty param.name}"><c:out value="${param.name}"/></c:if>"
						class="inputSelect multiple"
						multiple="multiple" size="<c:out value="${param.selectTamany}"/>"
						onDblClick="moveSelectedOptions(this,document.getElementById('id_<c:out value="${param.camp}"/>_bis'),true)">
						<c:forEach var="item" items="${campItems}">
							<c:set var="itemFound" value="N" />
							<c:forEach var="itemB" items="${campItemsB}">
								<c:if test="${item[param.selectItemsId] == itemB}">
									<c:set var="itemFound" value="S" />
								</c:if>
							</c:forEach>
							<option value="<c:out value="${item[param.selectItemsId]}"/>"
								<c:if test="${itemFound == 'S'}">selected="selected"</c:if>><c:out
								value="${item[param.selectItemsValue]}" /></option>
						</c:forEach>
					</select></td>
					<td class="botons">
					<button name="moveAllRight" type="button"
						onclick="moveAllOptions(document.getElementById('id_<c:out value="${param.camp}"/>'),document.getElementById('id_<c:out value="${param.camp}"/>_bis'),true)">
					&gt;&gt;</button>
					<br />
					<button name="moveRight" type="button"
						onclick="moveSelectedOptions(document.getElementById('id_<c:out value="${param.camp}"/>'),document.getElementById('id_<c:out value="${param.camp}"/>_bis'),true)">
					&gt;</button>
					<br />
					<button name="moveLeft" type="button"
						onclick="moveSelectedOptions(document.getElementById('id_<c:out value="${param.camp}"/>_bis'),document.getElementById('id_<c:out value="${param.camp}"/>'),true)">
					&lt;</button>
					<br />
					<button name="moveAllLeft" type="button"
						onclick="moveAllOptions(document.getElementById('id_<c:out value="${param.camp}"/>_bis'),document.getElementById('id_<c:out value="${param.camp}"/>'),true)">
					&lt;&lt;</button>
					</td>
					<td class="contenedorSelectMultiple"><select
						id="id_<c:out value="${param.camp}"/>_bis"
						name="<c:if test="${empty param.name}"><c:out value="${param.camp}"/></c:if><c:if test="${not empty param.name}"><c:out value="${param.name}"/></c:if>_bis"
						class="inputSelect multiple" multiple="multiple"
						size="<c:out value="${param.selectTamany}"/>"
						onDblClick="moveSelectedOptions(this,document.getElementById('id_<c:out value="${param.camp}"/>'),true)">
					</select></td>
				</tr>
			</table>
			<script type="text/javascript" language="javascript">
				// <![CDATA[
				var msel_<c:out value="${param.camp}"/> = document.getElementById('id_<c:out value="${param.camp}"/>');
				var msel_<c:out value="${param.camp}"/>_bis = document.getElementById('id_<c:out value="${param.camp}"/>_bis');
				moveSelectedOptions(msel_<c:out value="${param.camp}"/>,msel_<c:out value="${param.camp}"/>_bis,true);
				function onSubmitMultiple_<c:out value="${param.camp}"/>() {
					removeAllOptions(msel_<c:out value="${param.camp}"/>);
				    copyAllOptions(msel_<c:out value="${param.camp}"/>_bis, msel_<c:out value="${param.camp}"/>);
				    selectAllOptions(msel_<c:out value="${param.camp}"/>);
				}
				
				// ]]>
				</script>
		</c:when>

		<c:when test="${param.tipus == 'selectMultipleDisabled'}">

			<table class="selectMultiple" border="0" cellpadding="0"
				cellspacing="0">
				<tr>
					<td><small><fmt:message key="${param.titleIzquierda}" /></small></td>
					<td></td>
					<td><small><fmt:message key="${param.titleDerecha}" /></small></td>
				</tr>
				<tr>
					<td class="contenedorSelectMultiple"><select
						id="id_<c:out value="${param.camp}"/>"
						name="<c:out value="${param.camp}"/>" class="inputSelect multiple"
						multiple="multiple" size="<c:out value="${param.selectTamany}"/>"
						onDblClick="">
						<c:forEach var="item" items="${campItems}">
							<c:set var="itemFound" value="N" />
							<c:forEach var="itemB" items="${campItemsB}">
								<c:if test="${item[param.selectItemsId] == itemB}">
									<c:set var="itemFound" value="S" />
								</c:if>
							</c:forEach>
							<option value="<c:out value="${item[param.selectItemsId]}"/>"
								<c:if test="${itemFound == 'S'}">selected="selected"</c:if>><c:out
								value="${item[param.selectItemsValue]}" /></option>
						</c:forEach>
					</select></td>
					<td class="botons">
					<button name="moveAllRight" type="button" onclick=""
						disabled="disabled">&gt;&gt;</button>
					<br />
					<button name="moveRight" type="button" onclick=""
						disabled="disabled">&gt;</button>
					<br />
					<button name="moveLeft" type="button" onclick=""
						disabled="disabled">&lt;</button>
					<br />
					<button name="moveAllLeft" type="button" onclick=""
						disabled="disabled">&lt;&lt;</button>
					</td>
					<td class="contenedorSelectMultiple"><select
						id="id_<c:out value="${param.camp}"/>_bis"
						name="<c:out value="${param.camp}"/>_bis"
						class="inputSelect multiple" multiple="multiple"
						size="<c:out value="${param.selectTamany}"/>" onDblClick="">
					</select></td>
				</tr>
			</table>
			<script type="text/javascript" language="javascript">
				// <![CDATA[
				var msel_<c:out value="${param.camp}"/> = document.getElementById('id_<c:out value="${param.camp}"/>');
				var msel_<c:out value="${param.camp}"/>_bis = document.getElementById('id_<c:out value="${param.camp}"/>_bis');
				moveSelectedOptions(msel_<c:out value="${param.camp}"/>,msel_<c:out value="${param.camp}"/>_bis,true);
				function onSubmitMultiple_<c:out value="${param.camp}"/>() {
					removeAllOptions(msel_<c:out value="${param.camp}"/>);
				    copyAllOptions(msel_<c:out value="${param.camp}"/>_bis, msel_<c:out value="${param.camp}"/>);
				    selectAllOptions(msel_<c:out value="${param.camp}"/>);
				}
				
				// ]]>
				</script>
		</c:when>


		<c:when test="${param.tipus == 'selectMultipleListado'}">

			<select id="<c:out value="${param.camp}"/>"
				name="<c:out value="${param.camp}"/>" class="inputSelect"
				multiple="multiple" size="<c:out value="${param.selectTamany}"/>">

			</select>
			<script type="text/javascript" language="javascript">
					// <![CDATA[
					
					var options_<c:out value="${param.camp}"/> = new Array();
					
					function addOption_<c:out value="${param.camp}"/>(id) {
						var opciones = "";
						options_<c:out value="${param.camp}"/>[id] = options_<c:out value="${param.camp}"/>[id] ? false : true;
						
						for(var j in options_<c:out value="${param.camp}"/>){
							if(options_<c:out value="${param.camp}"/>[j]== true){
								opciones = opciones +"<option selected='selected' value='"+j+"'></option> ";
							}
						}
						
						jQuery("#<c:out value='${param.camp}'/>").html(opciones);						
					}
					
					function initTable_<c:out value="${param.camp}"/>() {

						if (!document.getElementsByTagName) return;
						
					    var tables = document.getElementsByTagName("table");
					    for (var i=0; i<tables.length; i++) {
					        var table = tables[i];
					        if (table.className.indexOf("selectable") != -1) {
						        var previousClass = null;
							    var tbody = table.getElementsByTagName("tbody")[0];
							    if (tbody == null) {
							        var rows = table.getElementsByTagName("tr");
							    } else {
							        var rows = tbody.getElementsByTagName("tr");
							    }
							    for (var n = 0; n < rows.length; n++) {		    
							        var sortir = false;
							        for (var j = 0; j < rows[n].childNodes.length; j++) {
							        	for (var k = 0; k < rows[n].childNodes[j].childNodes.length; k++) {
							        		if (rows[n].childNodes[j].childNodes[k].nodeName == 'A') {
							        			if (sortir) {
							        				alert(rows[n].onclick);
							        			} else {		        				
								        			eval("rows[n].onclick = function() {addOption_<c:out value='${param.camp}'/>(" + rows[n].childNodes[j].childNodes[k].rel + ");seleccionarFila("+n+");return false; }");
								        			
								        			for(var op in options_<c:out value="${param.camp}"/>){
														if( op == rows[n].childNodes[j].childNodes[k].rel && options_<c:out value="${param.camp}"/>[op]== true){
															seleccionarFila(n);
														}
													}
								        			
								        			sortir = true;
							        			}
							        		}
						    	    	}
						    	    	if (sortir) break;
							        }
							    }
							}
					    }
					}
					
					function seleccionarFila(fila){
						var tables = document.getElementsByTagName("table");
						var table = tables[0];
					    var tbody = table.getElementsByTagName("tbody")[0];
					    if (tbody == null) {
					        var rows = table.getElementsByTagName("tr");
					    } else {
					        var rows = tbody.getElementsByTagName("tr");
					    }
					    
					    var nombreClass = rows[fila].className;
					    	
				  		if (nombreClass.indexOf("selected") == -1) {
				  			rows[fila].className = nombreClass+" selected over";
				  		}else{
				  			rows[fila].className = nombreClass.substring(0,nombreClass.indexOf("selected"));
				  		}
				        
					}
					
					
					$(document).ready(function(){
						initTable_<c:out value="${param.camp}"/>();
					
					})					
				
					// ]]>
				</script>
		</c:when>






		<c:when test="${param.tipus == 'file'}">
			<label for="<c:out value="${param.camp}"/>"><c:out
				value="${param.title}" /></label>
			<table border="0">
				<%
	Arxiu arxiuTemp = null;
	if (request.getParameter("arxiu") != null) {
		request.setAttribute("campArxiu", request.getAttribute(request.getParameter("arxiu")));
		arxiuTemp = (Arxiu)request.getAttribute(request.getParameter("arxiu"));
	}
%>
				<%
	//if (request.getParameter("id") != null && request.getParameter("pantallaZona") != null) {
	
	if (arxiuTemp != null && arxiuTemp.getId() != null) {
		if (request.getParameter("document") != null) {
%>
				<tr>
					<td><a
						href="ArxiuMostrar.html?id=<c:out value="${campArxiu.id}"/>&download=true"><fmt:message
						key="manteniment.descarregar" /></a></td>
				</tr>
				<%
		} else {
%>
				<tr>
					<td>
						<a href="ArxiuMostrar.html?id=<c:out value="${campArxiu.id}"/>" target="_blank">
							<img src="ArxiuMostrar.html?id=<c:out value="${campArxiu.id}"/>" width="<c:out value="${param.width}"/>" height="<c:out value="${param.height}"/>" usemap="#nav1" alt="" />
						</a>
					</td>
				</tr>
				<%
		}
	}
%>
				<tr>
					<td><input type="file" id="<c:out value="${param.camp}"/>" name="<c:out value="${param.camp}"/>" class="inputFile" /></td>
				</tr>
			</table>
			<c:if test="${not empty param.aclaracio}">
				<p class="comentari"><c:out value="${param.aclaracio}" escapeXml="false" /></p>
			</c:if>
		</c:when>

		<c:when test="${param.tipus == 'fileMultiple'}">
			<label for="<c:out value="${param.camp}"/>"><c:out
				value="${param.title}" /></label>
			<table class="fileMultiple" border="0" cellpadding="0"
				cellspacing="2">
				<tr>
					<th></th>
					<th><c:if test="${not empty formData.id}">
						<fmt:message key="manteniment.esborrar" />?</c:if></th>
				</tr>
				<c:forEach var="item" items="${campItems}">
					<tr>
						<td><a
							href="Download.lmt?id=<c:out value="${item[param.selectItemsId]}" />"
							target="_blank"><c:out
							value="${item[param.selectItemsValue]}" /></a></td>
						<td><input style="margin: 0; padding: 0" type="checkbox"
							class="inputCheckbox inputCheckTaula"
							value="<c:out value="${item[param.selectItemsId]}" />"
							name="arxiusBorrar" /></td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="2">
					<div class="arxiuMultiple"
						id="files_<c:out value="${param.camp}"/>"></div>
					</td>
				</tr>
				<tr>
					<td colspan="2"><input style="width: auto" type="button"
						class="inputSubmit" onClick="anyadeBotonFile()"
						value="<fmt:message key="manteniment.afegirFitxer"/>" /></td>
				</tr>
			</table>
			<script type="text/javascript" language="javascript">
// <![CDATA[
var fmcont_<c:out value="${param.camp}"/> = 1;
function anyadeBotonFile() {
    var arxiu = document.createElement("input");
    arxiu.setAttribute("type", "file");
    arxiu.setAttribute("name", "file"+fmcont_<c:out value="${param.camp}"/>);
    var salto = document.createElement("br");
	var files = document.getElementById("files_<c:out value="${param.camp}"/>");
	files.appendChild(arxiu);
	files.appendChild(salto);
	fmcont_<c:out value="${param.camp}"/>++;
}
// ]]>
</script>
		</c:when>



		<c:when test="${param.tipus == 'fileImage'}">
			<label for="<c:out value="${param.camp}"/>"><c:out value="${param.title}" /></label>
			<table border="0">
				<tr><td>
				<%
					if (request.getParameter("arxiu") != null){
						request.setAttribute("campArxiu", request.getAttribute(request.getParameter("arxiu")));
					}
					Long arxiuId = (Long) request.getAttribute(request.getParameter("arxiuId"));
					Arxiu arxiuTemp = (Arxiu)request.getAttribute(request.getParameter("arxiu"));
					if(arxiuTemp != null && arxiuTemp.getNom() != null){
						request.setAttribute("nomArxiu",arxiuTemp.getNom());
					}else {
						request.setAttribute("nomArxiu","");
					}
					if(arxiuTemp != null && arxiuId == null){ 
				%>
						<%=arxiuTemp.getNom()%>
				<%
					}else if (arxiuId != null){								
				%>
						${nomArxiu}
						<a href="ArxiuMostrar.html?id=<%=arxiuId%>" target="_blank">
							<c:choose>
								<c:when test="${empty nomArxiu}">
									<img src="img/icons/defecte.png" width="32" height="32" alt="" />
								</c:when>
								<c:when test="${fn:contains(nomArxiu,'.pdf') == true}">
									<img src="img/icons/pdf2.png" width="32" height="32" alt="" />
								</c:when>
								<c:when test="${fn:contains(nomArxiu,'.doc') == true}">
									<img src="img/icons/doc.png" width="32" height="32" alt="" />
								</c:when>
								<c:when test="${fn:contains(nomArxiu,'.swf') == true}">
									<img src="img/icons/swf.jpg" width="32" height="32" alt="" />
								</c:when>
								<c:otherwise>
									<img src="ArxiuMostrar.html?id=<%=arxiuId%>" width="<c:out value="${param.width}"/>" height="<c:out value="${param.height}"/>" usemap="#nav1" alt="" />
								</c:otherwise>
							</c:choose>
						</a>
					</td>
				</tr>
				<%
					} 
				%>
				<tr>
					<td><input type="file" id="<c:out value="${param.camp}"/>"
						name="<c:out value="${param.camp}"/>" class="inputFile" /></td>
				</tr>
			</table>
		</c:when>





		<c:when test="${param.tipus == 'fileViewMultiple'}">
			<c:set var="vacio" value='si' />
			<c:forEach var="item" items="${campItems}">
				<c:set var="vacio" value='no' />
			</c:forEach>
			<c:if test="${vacio == 'no'}">
				<label for="<c:out value="${param.camp}"/>"><c:out
					value="${param.title}" /></label>
			</c:if>
			<table class="fileViewMultiple" border="0" cellpadding="0"
				cellspacing="2">
				<c:forEach var="item" items="${campItems}">
					<tr>
						<td><a
							href="Download.lmt?id=<c:out value="${item[param.selectItemsId]}" />"
							target="_blank"><c:out value="${item[param.selectItemsNom]}" /></a></td>
						<td>
						<% 	                	    
    	                	    Arxiu arxiu = (Arxiu) pageContext.getAttribute("item");
    	                	    String tipo = arxiu.getMime();
    	                		String icono = "generic.png";
    	                		if (tipo.indexOf("image")>=0) icono="imagen.png"; 
    	                		if (tipo.indexOf("text")>=0) icono="texto.png"; 
    	                		if (tipo.indexOf("octet-stream")>=0) icono="exe.png"; 
    	                		if (tipo.indexOf("pdf")>=0) icono="pdf.png"; 
    	                		if (tipo.indexOf("zip")>=0) icono="zip.png"; 
    	                		if (tipo.indexOf("html")>=0) icono="html.png"; 
    	                		if (tipo.indexOf("audio")>=0) icono="musica.png"; 
    	                	%> <img src="img/iconos/<%=icono%>" width="16"
							height="16" /></td>
					</tr>
				</c:forEach>
			</table>
		</c:when>

		<c:when test="${param.tipus == 'document'}">
			<label for="<c:out value="${param.camp}"/>"><c:out
				value="${param.title}" /></label>
			<table border="0" cellpadding="0" cellspacing="0"
				style="margin: 0; padding: 0" class="document">
				<tr style="margin: 0; padding: 0">
					<td style="margin: 0; padding: 0"><c:set var="bindPathTipus"
						value="${param.pathTipus}" /> <spring:bind path="${bindPathTipus}">
						<select id="<c:out value="${param.campTipus}"/>"
							name="<c:out value="${param.campTipus}"/>" class="inputSelect">
							<option value="1"
								<c:if test="${status.value==1}">selected="selected"</c:if>><fmt:message
								key="manteniment.document.ticket" /></option>
							<option value="2"
								<c:if test="${status.value==2}">selected="selected"</c:if>><fmt:message
								key="manteniment.document.albara" /></option>
							<option value="3"
								<c:if test="${status.value==3}">selected="selected"</c:if>><fmt:message
								key="manteniment.document.guia" /></option>
							<option value="4"
								<c:if test="${status.value==4}">selected="selected"</c:if>><fmt:message
								key="manteniment.document.factura" /></option>
						</select>
					</spring:bind></td>
					<td><input type="text" id="<c:out value="${param.camp}"/>"
						name="<c:out value="${param.camp}"/>" class="inputText"
						value="<c:out value="${status.value}"/>"
						<c:out value="${onchange}"/> /></td>
				</tr>
			</table>
		</c:when>

		<c:when test="${param.tipus == 'motiu'}">
			<label for="<c:out value="${param.camp}"/>"><c:out
				value="${param.title}" /></label>
			<c:set var="bindPath" value="${param.path}" />
			<spring:bind path="${bindPath}">
				<select id="<c:out value="${param.camp}"/>"
					name="<c:out value="${param.camp}"/>" class="inputSelect"
					style="width: 70px">
					<option value="Filtrat"><fmt:message
						key="manteniment.perdues.filtrat" /></option>
					<option value="Clarificació"><fmt:message
						key="manteniment.perdues.clarificacio" /></option>
					<option value="Accidental"><fmt:message
						key="manteniment.perdues.accidental" /></option>
				</select>
			</spring:bind>
		</c:when>

		<c:when test="${param.tipus == 'motiu2'}">
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td><label for="<c:out value="${param.camp}"/>"><c:out
						value="${param.title}" /></label></td>
					<td><c:set var="bindPath" value="${param.path}" /> <spring:bind
						path="${bindPath}">
						<input id="<c:out value="${param.camp}"/>" name="motiu"
							value="<fmt:message key="manteniment.perdues.filtrat"/>"
							type="text" class="inputText"
							onchange="canvisCampFormulari(this)"
							selectBoxOptions="<fmt:message key="manteniment.perdues.filtrat"/>;<fmt:message key="manteniment.perdues.clarificacio"/>;<fmt:message key="manteniment.perdues.accidental"/>" />
					</spring:bind></td>
				</tr>
			</table>
			<script type="text/javascript">
							createEditableSelect(document.forms[0].motiu);
						</script>
		</c:when>

		<c:when test="${param.tipus == 'tipusProducte'}">
			<label for="<c:out value="${param.camp}"/>"><c:out
				value="${param.title}" /></label>
			<c:set var="bindPath" value="${param.path}" />
			<spring:bind path="${bindPath}">
				<select id="<c:out value="${param.camp}"/>"
					name="<c:out value="${param.camp}"/>" class="inputSelect"
					style="width: 70px">
					<option value="0"
						<c:if test="${status.value==0}">selected="selected"</c:if>><fmt:message
						key="manteniment.tprodu.altres" /></option>
					<option value="1"
						<c:if test="${status.value==1}">selected="selected"</c:if>><fmt:message
						key="manteniment.tprodu.alcohol" /></option>
					<option value="2"
						<c:if test="${status.value==2}">selected="selected"</c:if>><fmt:message
						key="manteniment.tprodu.sacarosa" /></option>
				</select>
			</spring:bind>
		</c:when>

		<c:when test="${param.tipus == 'tipusUsuari'}">
				<label for="<c:out value="${param.camp}"/>"><c:out value="${param.title}"/></label>
				<c:set var="bindPath" value="${param.path}"/>
				<spring:bind path="${bindPath}">
					<div class="bordeInput">
						<div class="bordeFoco">
							<select 
								id="<c:out value="${param.camp}"/>" 
								name="<c:out value="${param.camp}"/>" 
								class="inputSelect" onchange="${param.onchange}"
								<c:if test="${not empty param.disabled}"> disabled="<c:out value="${param.disabled}"/>"</c:if>>
					   			<option value="1"<c:if test="${status.value==1 or param.selectSelectedValue == 1}">selected="selected"</c:if>><fmt:message key="usuari.tipus.administracio"/></option>
					   			<option value="2"<c:if test="${status.value==2 or param.selectSelectedValue == 2}">selected="selected"</c:if>><fmt:message key="usuari.tipus.do.gestor"/></option>
					   			<option value="3"<c:if test="${status.value==3 or param.selectSelectedValue == 3}">selected="selected"</c:if>><fmt:message key="usuari.tipus.do.control"/></option>
					   			<option value="4"<c:if test="${status.value==4 or param.selectSelectedValue == 4}">selected="selected"</c:if>><fmt:message key="usuari.tipus.tafona"/></option>
					   			<option value="5"<c:if test="${status.value==5 or param.selectSelectedValue == 5}">selected="selected"</c:if>><fmt:message key="usuari.tipus.envasadora"/></option>
					   			<option value="6"<c:if test="${status.value==6 or param.selectSelectedValue == 6}">selected="selected"</c:if>><fmt:message key="usuari.tipus.tafona-envasadora"/></option>
					   		</select>
				   		</div>
				   	</div>
		   		</spring:bind>
			</c:when>
			
		<c:when test="${param.tipus == 'tipusUsuariEstabliment'}">
				<label for="<c:out value="${param.camp}"/>"><c:out value="${param.title}"/></label>
				<c:set var="bindPath" value="${param.path}"/>
				<spring:bind path="${bindPath}">
					<div class="bordeInput">
						<div class="bordeFoco">
							<select 
								id="<c:out value="${param.camp}"/>" 
								name="<c:out value="${param.camp}"/>" 
								class="inputSelect" onchange="${param.onchange}"
								<c:if test="${not empty param.disabled}"> disabled="<c:out value="${param.disabled}"/>"</c:if>>
					   			<option value="8"<c:if test="${status.value==8}">selected="selected"</c:if>><fmt:message key="usuari.tipus.est_administracio"/></option>
					   			<option value="11"<c:if test="${status.value==11}">selected="selected"</c:if>><fmt:message key="usuari.tipus.est_encarregat"/></option>
					   			<option value="9"<c:if test="${status.value==9}">selected="selected"</c:if>><fmt:message key="usuari.tipus.est_treballador"/></option>
					   			<option value="10"<c:if test="${status.value==10}">selected="selected"</c:if>><fmt:message key="usuari.tipus.est_consultor"/></option>
					   		</select>
				   		</div>
				   	</div>
		   		</spring:bind>
			</c:when>
			
		<c:when test="${param.tipus == 'grau'}">
			<label for="<c:out value="${param.camp}"/>"><c:out
				value="${param.title}" /></label>
			<table border="0" cellpadding="0" cellspacing="0"
				style="margin: 0; padding: 0" class="grau">
				<tr style="margin: 0; padding: 0">
					<td style="margin: 0; padding: 0"><c:set var="bindPathTipus"
						value="${param.pathTipus}" /> <spring:bind path="${bindPathTipus}">
						<select id="<c:out value="${param.campTipus}"/>"
							name="<c:out value="${param.campTipus}"/>" class="inputSelect">
							<option value="0"
								<c:if test="${status.value==0}">selected="selected"</c:if>><fmt:message
								key="manteniment.ugraus.baume" /></option>
							<option value="1"
								<c:if test="${status.value==1}">selected="selected"</c:if>><fmt:message
								key="manteniment.ugraus.brix" /></option>
							<option value="2"
								<c:if test="${status.value==2}">selected="selected"</c:if>><fmt:message
								key="manteniment.ugraus.refraccio" /></option>
							<option value="3"
								<c:if test="${status.value==3}">selected="selected"</c:if>><fmt:message
								key="manteniment.ugraus.mvol" /></option>
							<option value="4"
								<c:if test="${status.value==4}">selected="selected"</c:if>><fmt:message
								key="manteniment.ugraus.densitat" /></option>
							<option value="5"
								<c:if test="${status.value==5}">selected="selected"</c:if>><fmt:message
								key="manteniment.ugraus.sucres" /></option>
							<option value="6"
								<c:if test="${status.value==6}">selected="selected"</c:if>
								<c:if test="${empty status.value}">selected="selected"</c:if>><fmt:message
								key="manteniment.ugraus.aprob" /></option>
						</select>
					</spring:bind></td>
					<td><input type="text" id="<c:out value="${param.camp}"/>"
						name="<c:out value="${param.camp}"/>" class="inputText"
						value="<c:out value="${status.value}"/>"
						<c:out value="${onchange}"/> /></td>
				</tr>
			</table>
		</c:when>

		<c:when test="${param.tipus == 'textarea'}">
			<label for="<c:out value="${param.camp}"/>"><c:out value="${param.title}"/></label>
			<div class="bordeInput">
				<div id="observacionesBordeFoco_<c:out value="${param.camp}"/>" class="bordeFocoTextarea">
					<p><span class="label">
						<fmt:message key="manteniment.dispone" /> 
						<strong id="carDisp_<c:out value="${param.camp}"/>">5000</strong> 
						<fmt:message key="manteniment.caracteres" />
					</span></p>
					<textarea id="id_<c:out value="${param.camp}"/>"
						name="<c:if test="${empty param.name}"><c:out value="${param.camp}"/></c:if><c:if test="${not empty param.name}"><c:out value="${param.name}"/></c:if>"
						class="inputTextarea" rows="7"
						cols="<c:choose><c:when test="${not empty param.cols}"><c:out value="${param.cols}"/></c:when><c:otherwise><c:out value="58"/></c:otherwise></c:choose>"
						onkeydown="cuentaCaracteres('id_<c:out value="${param.camp}"/>','carDisp_<c:out value="${param.camp}"/>',5000)"
						onkeyup="cuentaCaracteres('id_<c:out value="${param.camp}"/>','carDisp_<c:out value="${param.camp}"/>',5000)"
						onfocus="activaFoco(this,'observacionesBordeFoco_<c:out value="${param.camp}"/>')"
						onblur="desactivaFoco(this,'observacionesBordeFoco_<c:out value="${param.camp}"/>')"
						<c:if test="${not empty param.readonly}">readonly="readonly"</c:if>
						<c:if test="${not empty param.read && param.read == 'true'}">readonly="readonly"</c:if>
						<c:if test="${not empty param.disabled}"> disabled="<c:out value="${param.disabled}"/>"</c:if>><c:out value="${status.value}" escapeXml="false" /></textarea>
				</div>
			</div>
		</c:when>
		
		<c:when test="${param.tipus == 'textareaMedio'}">
			<label for="<c:out value="${param.camp}"/>"><c:out value="${param.title}"/></label>
			<div class="bordeInput">
				<div id="observacionesBordeFoco_<c:out value="${param.camp}"/>" class="bordeFocoTextarea">
					<p><span class="label">
						<fmt:message key="manteniment.dispone" /> 
						<strong id="carDisp_<c:out value="${param.camp}"/>">500</strong> 
						<fmt:message key="manteniment.caracteres" />
					</span></p>
					<textarea id="id_<c:out value="${param.camp}"/>"
						name="<c:if test="${empty param.name}"><c:out value="${param.camp}"/></c:if><c:if test="${not empty param.name}"><c:out value="${param.name}"/></c:if>"
						class="inputTextarea" rows="5"
						cols="<c:choose><c:when test="${not empty param.cols}"><c:out value="${param.cols}"/></c:when><c:otherwise><c:out value="58"/></c:otherwise></c:choose>"
						onkeydown="cuentaCaracteres('id_<c:out value="${param.camp}"/>','carDisp_<c:out value="${param.camp}"/>',500)"
						onkeyup="cuentaCaracteres('id_<c:out value="${param.camp}"/>','carDisp_<c:out value="${param.camp}"/>',500)"
						onfocus="activaFoco(this,'observacionesBordeFoco_<c:out value="${param.camp}"/>')"
						onblur="desactivaFoco(this,'observacionesBordeFoco_<c:out value="${param.camp}"/>')"
						<c:if test="${not empty param.readonly}">readonly="readonly"</c:if>
						<c:if test="${not empty param.read && param.read == 'true'}">readonly="readonly"</c:if>
						<c:if test="${not empty param.disabled}"> disabled="<c:out value="${param.disabled}"/>"</c:if>><c:out value="${status.value}" escapeXml="false" /></textarea>
				</div>
			</div>
		</c:when>

		<c:when test="${param.tipus == 'categoria'}">
			<script type="text/javascript" language="javascript">
				// <![CDATA[
				var categs_<c:out value="${param.camp}"/> = new Array();
				<c:forEach var="item" items="${campItems}">
					categs_<c:out value="${param.camp}"/>['<c:out value="${item.id}"/>'] = <c:out value="${item.esQualitat}"/>;
				</c:forEach>
				function canvi_<c:out value="${param.camp}"/>(obj) {
					var oobj = document.getElementById('categ_<c:out value="${param.camp}"/>');
					if (obj.value=="") {
						oobj.style.display = 'none';
						return;
					}
					if (categs_<c:out value="${param.camp}"/>[obj.value] || categs_<c:out value="${param.camp}"/>[obj.value]==null) {
						oobj.style.display = 'none';
					} else {
						oobj.style.display = '';
					}
				}
				// ]]>
				</script>
			<%--c:set var="esQualitat" value="${campItems[0].esQualitat}"/--%>
			<c:set var="esQualitat" value="${true}" />
			<label for="<c:out value="${param.camp}"/>"><c:out
				value="${param.title}" /></label>
			<select id="<c:out value="${param.camp}"/>"
				name="<c:out value="${param.camp}"/>" class="inputSelect"
				onchange="canvi_<c:out value="${param.camp}"/>(this)">
				<option value=""
					<c:if test="${empty param.selectSelectedValue}">selected="selected" </c:if>>-
				- - - -</option>
				<c:forEach var="item" items="${campItems}">
					<option value="<c:out value="${item[param.selectItemsId]}"/>"
						<c:if test="${param.selectSelectedValue == item[param.selectItemsId]}">selected="selected"</c:if>><c:out
						value="${item[param.selectItemsValue]}" /></option>
					<c:if
						test="${param.selectSelectedValue == item[param.selectItemsId]}">
						<c:set var="esQualitat" value="${item.esQualitat}" />
					</c:if>
				</c:forEach>
			</select></div>
	<div id="categ_<c:out value="${param.camp}"/>"
		<c:if test="${esQualitat}">style="display:none"</c:if>><label
		for="<c:out value="${param.apteCamp}"/>"><c:out
		value="${param.apteTitle}" /></label> <input type="hidden"
		name="_checkbox.<c:out value="${param.apteCamp}"/>" value="N" /> <input
		type="checkbox" id="<c:out value="${param.apteCamp}"/>"
		name="<c:out value="${param.apteCamp}"/>" class="inputCheckbox"
		value="S"
		<c:if test="${param.apteCampValue == true}">checked="checked"</c:if> />
	</c:when> 
	
	<c:when test="${param.tipus == 'categoriaVarios'}">
		<script type="text/javascript" language="javascript">
				// <![CDATA[
				var categs_<c:out value="${param.camp}"/> = new Array();
				<c:forEach var="item" items="${campItems}">
					categs_<c:out value="${param.camp}"/>['<c:out value="${item.id}"/>'] = <c:out value="${item.esQualitat}"/>;
				</c:forEach>
				function canvi_<c:out value="${param.camp}"/><c:out value="${param.contIdCamp}"/>(obj) {
					var oobj = document.getElementById('categ_<c:out value="${param.camp}"/><c:out value="${param.contIdCamp}"/>');
					if (categs_<c:out value="${param.camp}"/>[obj.value] || categs_<c:out value="${param.camp}"/>[obj.value]==null)
					{
						oobj.style.display = 'none';
					} else {
						oobj.style.display = '';
					}
				}
				// ]]>
				</script>
		<c:set var="esQualitat" value="${campItems[0].esQualitat}" />
		<label for="<c:out value="${param.camp}"/>"><c:out
			value="${param.title}" /></label>
		<select id="<c:out value="${param.camp}"/>"
			name="<c:out value="${param.camp}"/>" class="inputSelect"
			onchange="canvi_<c:out value="${param.camp}"/><c:out value="${param.contIdCamp}"/>(this)">
			<option value=""
				<c:if test="${empty param.selectSelectedValue}">selected="selected" </c:if>>-
			- - - -</option>
			<c:forEach var="item" items="${campItems}">
				<option value="<c:out value="${item[param.selectItemsId]}"/>"
					<c:if test="${param.selectSelectedValue == item[param.selectItemsId]}">selected="selected"</c:if>><c:out
					value="${item[param.selectItemsValue]}" /></option>
				<c:if
					test="${param.selectSelectedValue == item[param.selectItemsId]}">
					<c:set var="esQualitat" value="${item.esQualitat}" />
				</c:if>
			</c:forEach>
		</select></div>
	<div
		id="categ_<c:out value="${param.camp}"/><c:out value="${param.contIdCamp}"/>"
		<c:if test="${esQualitat}">style="display:none"</c:if>><label
		for="<c:out value="${param.apteCamp}"/>"><c:out
		value="${param.apteTitle}" /></label> <input type="hidden"
		name="_checkbox.<c:out value="${param.apteCamp}"/>" value="N" /> <input
		type="checkbox" id="<c:out value="${param.apteCamp}"/>"
		name="<c:out value="${param.apteCamp}"/>" class="inputCheckbox"
		value="<c:out value="${param.contIdCamp}"/>"
		<c:if test="${param.aptePath == param.contIdCamp}">checked="checked"</c:if> />
	</c:when>
	<c:when test="${param.tipus == 'radioDecisio'}">
				<label for="<c:out value="${param.camp}"/>"><c:out value="${param.title}"/></label>
				<span class="descripcioradio"><c:out value="${param.textCamp}"/></span>
				<div class="radio">
				    <input type="radio" id="<c:out value="${param.camp}Si"/>" name="<c:out value="${param.camp}Radio"/>" class="inputCheckbox" value="S" <c:if test="${status.value == 'S'}">checked="checked"</c:if> <c:if test="${not empty param.onchange}">onchange="<c:out value="${param.onchange}"/>"</c:if> <c:if test="${not empty param.onclick}">onclick="<c:out value="${param.onclick}"/>"</c:if>/>&nbsp;<fmt:message key="formulari.radio.si"/>&nbsp;&nbsp;&nbsp;
				    <input type="radio" id="<c:out value="${param.camp}No"/>" name="<c:out value="${param.camp}Radio"/>" class="inputCheckbox" value="N" <c:if test="${status.value == 'N'}">checked="checked"</c:if> <c:if test="${not empty param.onchange}">onchange="<c:out value="${param.onchange}"/>"</c:if> <c:if test="${not empty param.onclick}">onclick="<c:out value="${param.onclick}"/>"</c:if>/>&nbsp;<fmt:message key="formulari.radio.no"/>
				</div>
				<input type="text" id="<c:out value="${param.camp}"/>" name="<c:out value="${param.camp}"/>" class="inputCheckbox" <c:if test="${status.value == 'S'}">value="S"</c:if><c:if test="${status.value == 'N'}">value="N"</c:if><c:if test="${status.value == ''}">value=""</c:if> style="display: none;" />
			</c:when> </c:choose> 
	<c:if test="${not empty status.errorMessage}">
		<div class="separadorH"></div>
		<p class="error" id="pIdFor<c:out value="${param.camp}"/>" style="display: block"><span class="capa1"></span>
			<span class="capa2"></span><span class="capa3"></span>
			<span class="capa4"></span>
			<span class="capa5">
				<span class="capa6"><c:out value="${status.errorMessage}" /></span>
			</span>
		</p>
	</c:if></div>
</spring:bind>