<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c-rt.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt-rt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag-el.tld"%>


<html>
<head>
<title><fmt:message key="qualitat.organigramaEmpresa.titol" /></title>
<link rel="stylesheet" type="text/css" href="css/tree.css"
	media="screen" />
<script type="text/javascript" src="js/yahoo/yahoo.js"></script>
<script type="text/javascript" src="js/yahoo/dom.js"></script>
<script type="text/javascript" src="js/yahoo/event.js"></script>
<script type="text/javascript" src="js/yahoo/treeview.js"></script>

<script type="text/javascript" language="javascript">
	var tree; 
	function treeInit() { 
	   	tree = new YAHOO.widget.TreeView("arbreAqui"); 
	   	var root = tree.getRoot(); 
	   	<c:forEach var="fulla" items="${llistat}" varStatus="status">
		   	<c:choose>
				<c:when test="${not empty fulla.carrecSuperior}">
					<c:set var="parent">tmpNode_<c:out value="${fulla.carrecSuperior.id}"/></c:set>
				</c:when>
				<c:otherwise>
					<c:set var="parent">root</c:set>
				</c:otherwise>
			</c:choose>
			<c:set var="empleats">
				<c:if test="${not empty fulla.personal}">
					<fmt:message key="qualitat.plaFormacio.organigrama.camp.empleats"/>: <c:forEach var="empleat" items="${fulla.personal}" varStatus="status"><c:out value="${empleat.nom}"/> <c:out value="${empleat.llinatges}"/>,</c:forEach>
				</c:if>
			</c:set>
			<c:set var="nivell"><fmt:message key="qualitat.plaFormacio.organigrama.camp.nivell"/>: <c:out value="${fulla.nivellJerarquic}"/></c:set>
			<c:set var="carrec"><fmt:message key="qualitat.plaFormacio.organigrama.camp.carrec"/>: <c:out value="${fulla.carrec}"/></c:set>
			var tmpNode_<c:out value="${fulla.id}"/> = new YAHOO.widget.TextNode({label:"<c:out value="${nivell}"/>, <c:out value="${empleats}"/> <c:out value="${carrec}"/>",id:"<c:out value="${fulla.id}"/>"}, <c:out value="${parent}"/>, true);
			tmpNode_<c:out value="${fulla.id}"/>.labelStyle = "arbre_lot";
		</c:forEach>
		tree.draw(); 
	}
	YAHOO.util.Event.addListener(window, "load", treeInit);
	
</script>



</head>
<body>
<div id="listadoAncho" class="arbol">

	<div id="arbreAqui"></div>
	
	<div class="separadorH"></div>
	
</div>


</body>
</html>