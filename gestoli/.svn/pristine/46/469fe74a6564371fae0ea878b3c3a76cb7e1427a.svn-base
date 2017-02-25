function escribeMail( nom, dom, etiqueta){
		
		if ( !etiqueta ){
			etiqueta = nom+'@'+dom;
		}
        document.write( '<a hr'+'ef="mai'+'lto:'+nom+'@'+dom+'">'+etiqueta+'</a>' );
}

function cuentaCaracteres(idCampoTexto, idCampoContador, maxCaracteres){
	
	var campoTexto = document.getElementById(idCampoTexto);
	if ( campoTexto.value.length > maxCaracteres ){
		campoTexto.value = campoTexto.value.substring(0, maxCaracteres);
	}else{
		campoContador = document.getElementById(idCampoContador);
		campoContador.innerHTML = maxCaracteres - campoTexto.value.length;
	}
	
}

function submitForm(idForm){
	var form = document.getElementById(idForm);
	form.submit();
	
}
function submitFormConfirm(idForm,textoConfirm){
	var form = document.getElementById(idForm);
	if(confirm(textoConfirm)) form.submit();
	
}

function submitFormAction(idForm, action){
	var form = document.getElementById(idForm);	
	form.action = action;
	form.submit();	
}
function submitFormConfirmAction(idForm,textoConfirm, action){
	var form = document.getElementById(idForm);
	form.action = action;
	if(confirm(textoConfirm)) form.submit();
	
}


/**
 * Cebrea una tabla
 * @version 0.2 (parametro Internet explorer)
 */
function setEstilosTabla(arg){
	//Por defecto se muestra en explorer
	arg = (typeof arg == 'undefined') ? true : arg;
	eliminaFondo();
	setSombraTabla(arg);	
}


/**
 * Elimina la imagen de fondo de la última columna
 */
function eliminaFondo() {
	
	jQuery('table tbody tr td:last-child').each(function(e){		
		jQuery(this).css("background-image","none");		
	}
	)
	
}


/*
* Cambia la clase del input en el que está el foco
*/
function activaFoco(input, idBorde){

	input.style.border = '2px solid #000';
	var oBorde = document.getElementById(idBorde);
	//oBorde.style.backgroundColor = '#000000';
}

function activaFocoCuenta(input, idBorde){
	var navegador = navigator.appName

	input.style.border = '#ffffff';
	
	var oBorde = document.getElementById(idBorde);
	
	input.style.width = '114px';
	oBorde.style.borderRight = '2px solid #000000';
	oBorde.style.borderLeft = '2px solid #000000';
	oBorde.style.backgroundColor = '#000000';
	
}

/*
* Cambia la clase del input del que se ha ido el foco
*/
function desactivaFoco(input, idBorde){
	input.style.border = '1px solid #809DB9';
	var oBorde = document.getElementById(idBorde);
	oBorde.style.backgroundColor = '';
}

function desactivaFocoCuenta(input, idBorde){

    input.style.width = '';
    input.style.borderColor = '';

    var oBorde = document.getElementById(idBorde);
    
    if ( jQuery("#cuentaForm").is(".error") ){
       oBorde.style.border = '';
       oBorde.style.borderLeft = '2px solid #DD0000';
    }else{
	   oBorde.style.border = '';
    }
    
	//oBorde.style.border = 'none';
   oBorde.style.backgroundColor = '';
}

/*
*  Cambia el estilo del enlace del botón en el que está el ratón
*/
function underline(idEnlace){
	var enlace = document.getElementById(idEnlace);
	if ( enlace.style.textDecoration == 'underline' ){
		enlace.style.textDecoration = 'none';
	}else{
		enlace.style.textDecoration = 'underline';
	}
}


/**
 * Asigamos el valor de una cookie
 */
function setCookie(sName, sValue, sPath, oExpires) {
    if (sValue == null) {
        sValue= "0";
    }
    var sCookie = sName + "=" + encodeURIComponent(sValue);
    if (sPath) {
        sCookie += "; path =" + sPath;
    }
    if (oExpires) {
        sCookie += "; expires =" + oExpires.toGMTString();
    }
    document.cookie = sCookie;
}



		        
function cambioEstado() {
	var campoCambioEstado = document.getElementById("campoCambioEstado");
	
	if (campoCambioEstado.value == "true") {
		campoCambioEstado.value = "false";
	} else {
		campoCambioEstado.value = "true";
	}	
}

function cambioVisibilidad(idForm,idCaja) {
	var campoCambioEstado = document.getElementById(idCaja);
	var form = document.getElementById(idForm);
		
	if (form.actiu.checked) {
		campoCambioEstado.className = "campoForm on";
	} else {
		campoCambioEstado.className = "campoForm off";
	}	
}	



function initTableEnlacesRegistros() {

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
			        			eval("rows[n].onclick = function() {" + rows[n].childNodes[j].childNodes[k].href + "}");
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




/**
 * Guarda el estado del submenu
 */
var submenuState = false;

/**
* Despliega un submenu
*/
function toggleSubmenu(menuId){

   if (submenuState) {
       jQuery(".item-submenu").css('display','none');
       submenuState = false;
   }
   else {
       jQuery(".item-submenu").css('display','block');
       submenuState = true;
   }
     setTamanosDivs(menuId);
  } 

/**
 * Selecciona la part de formulari de creació d'usuari depenent del tipus d'usuari a crear 
 */
function canviTipusUsuari(){
	document.getElementById('tafona').style.display = (document.getElementById('tipusUsuari').value == 4) ? "" : "none";
	document.getElementById('envasadora').style.display = (document.getElementById('tipusUsuari').value == 5) ? "" : "none";
	document.getElementById('tafenv').style.display = (document.getElementById('tipusUsuari').value == 6) ? "" : "none";
}
