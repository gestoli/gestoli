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
	alert(form);
	form.submit();
	
}

/**
 * Cebrea una tabla
 */
function setEstilosTabla(){
	$('tbody tr:even').addClass('alt');
	$('tbody tr').hover(function(){
		$(this).addClass('over');
	}, function(){
		$(this).removeClass('over');
	})
	eliminaFondo();
	setSombraTabla();
}

/**
 * Elimina la imagen de fondo de la última columna
 */
function eliminaFondo() {
	$('table tbody tr td:last-child').each(function(e){
		$(this).css("background-image","none");
	}
	)
}

/*
* Cambia la clase del input en el que está el foco
*/
function activaFoco(input, idBorde){
	
	input.style.borderColor = '#ffffff';
	var oBorde = document.getElementById(idBorde);
	oBorde.style.backgroundColor = '#000000';
}

function activaFocoCuenta(input, idBorde){
	var navegador = navigator.appName

	input.style.borderColor = '#ffffff';
	
	var oBorde = document.getElementById(idBorde);
	
	if (navegador == "Microsoft Internet Explorer"){
		input.style.width = '116px';
		oBorde.style.borderRight = '1px solid';
		oBorde.style.borderLeft = '1px solid';
	}else{
		input.style.width = '114px';
		oBorde.style.borderRight = '2px solid';
		oBorde.style.borderLeft = '2px solid';
	}
	oBorde.style.backgroundColor = '#000000';
	
}

/*
* Cambia la clase del input del que se ha ido el foco
*/
function desactivaFoco(input, idBorde){
	input.style.borderColor = '#809DB9';
	var oBorde = document.getElementById(idBorde);
	oBorde.style.backgroundColor = '#F9E574';
}

function desactivaFocoCuenta(input, idBorde){

	input.style.borderColor = '#809DB9';
	input.style.width = '118px';
	var oBorde = document.getElementById(idBorde);
	oBorde.style.border = 'none';
	oBorde.style.backgroundColor = '#F9E574';
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