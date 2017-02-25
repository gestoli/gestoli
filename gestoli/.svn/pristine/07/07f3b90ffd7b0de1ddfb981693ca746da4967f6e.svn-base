/**
 * Establece los tama�os correctos
 * de los divs de menu
 * @param {String} divMenuId
 * @author npalumbo
 */
function setTamanosDivs(divMenuId){

	//Dimensiones del div de menu
	var jqDivMenu = jQuery('#' + divMenuId);
	var altoTotal = jqDivMenu.innerHeight();
	var anchoTotal = jqDivMenu.innerWidth();
	
	//Objetos contenidos
	var jqSupDer = null;
	var jqInfDer = null;
	var jqInfIzq = null;
	var jqBordeIzq = null;
	var jqBordeDer = null;
	var jqRellenoSup = null;
	var jqRellenoInf = null;
	var jqSeparador = null;
	var jqSubmenu = null;
	var offsetIE = 0;
	var offsetIEhoriz = 0;
	var margenSuperior = 20;
	var haySubmenu = false;
	
	//Elementos del menu (esquinas y rellenos)
    jQuery('#' + divMenuId + ' > div').each(function(i) { 
				
		switch (this.className) {
			case 'supDer':
				jqSupDer = jQuery(this);
				break;
			case 'infDer':
				jqInfDer = jQuery(this);
				break;
			case 'infIzq':
				jqInfIzq = jQuery(this);	
				break;
			case 'bordeIzq':
				jqBordeIzq = jQuery(this);
				break;
			case 'bordeDer':
				jqBordeDer = jQuery(this);			
				break;				
			case 'rellenoSup':
				jqRellenoSup = jQuery(this);
				break;
			case 'rellenoInf':
				jqRellenoInf = jQuery(this);
				break;
			case 'separadorMenuItems':
				jqSeparador = jQuery(this);
				jqSeparador.width(anchoTotal);
				break;
			case 'item-submenu':
				jqSubmenu = jQuery(this);
				haySubmenu = true;
		}
		
	});
	
    try{
		//Ancho relleno superior
		jqRellenoSup.width(anchoTotal - (jqSupDer.width() - 14));
		
		//Ancho relleno inferior
		jqRellenoInf.width(anchoTotal - (jqInfIzq.width() + jqInfDer.width()) + 28);
		
		//Alto borde izquierdo
		jqBordeIzq.height(altoTotal + margenSuperior);
		
		//Alto borde derecho
		jqBordeDer.height(altoTotal);
		
		//Version del navegador
		var version = jQuery.browser.version;
		//alert(version);
		
		//Correcci�n para navegadores malos
		if (jQuery.browser['msie']) {
			
			/* --- IE 6 --- */
			if (version == '6.0') {
				//relleno inferior
				jqRellenoInf.width(anchoTotal - (jqInfDer.width() + jqInfIzq.width() - 4));
				jqRellenoSup.width(anchoTotal - (jqSupDer.width() - 1));
				
				if (haySubmenu && jqSubmenu.css('display') == 'block') {
					jqInfIzq.css('bottom', '-25px');
					jqInfDer.css('bottom', '-26px');
					jqRellenoInf.css('bottom', '-26px');
				}
				else {
					jqInfIzq.css('bottom', '-26px');
					jqInfDer.css('bottom', '-26px');
				}
			}
			
			/* --- IE 7 --- */
			if (version == '7.0'){
				/*jqDivMenu.css('margin-top','0px');
				jqDivMenu.css('top','-2px');
				jqDivMenu.css('left','1px');
				jqDivMenu.css('height', (altoTotal - 4) + 'px');*/
				jqRellenoInf.width(anchoTotal - (jqInfDer.width() + jqInfIzq.width()) + 4);
			}
			
		}
    } catch(error){ }

}

/**
 * Establece los atributos de los divs de sombra en tablas
 * @version 0.3 (Ahora soporta mas de un div, parametro opcional: ocultar en explorer)
 * @version 0.4 (definitivamente no hay mas sombras en explorer)
 */
function setSombraTabla(arg) {	

	jQuery('.layoutSombraTabla').each(function(e){
		
		//Asigno un id temporal al contenedor actual
		this.id = 'divSombra_' + e;

		//Propiedades contenedor actual
		var jqDivContenedor = jQuery(this);
		var tablaObjetivoStr = '#divSombra_' + e + ' > table';
		var jqTabla = jQuery(tablaObjetivoStr);
		var anchoTabla = jqTabla.width();
		var altoTabla = jqTabla.height();
		var cantidadRows = 0;
		var trObjetivoStr = '#divSombra_' + e + ' table tr';
		
		//Propagacion de clase para la ultima columna
		var thObjetivoSTR = '#divSombra_' + e + ' table tr th:last-child';
		var lastThClassNameSTR = jQuery(thObjetivoSTR).attr("class");
		
		if (lastThClassNameSTR != '' && lastThClassNameSTR != 'unicoHead'){
			//alert('clase: ' + lastThClassNameSTR);
			var tdObjetivoSTR = '#divSombra_' + e + ' table tr td:last-child';
			jQuery(tdObjetivoSTR).each(function(e){
				jQuery(this).addClass(lastThClassNameSTR);
			}
			)
		}
		
		//Las tablas que pertenezcan a la clase "noRoll" no se cebrean
		var tablaObjCebSTR = '#divSombra_' + e + ' > table tbody tr:even ';
		jQuery(tablaObjCebSTR).addClass('alt');
		
		if (jqTabla.hasClass('noRoll') == false){
			jQuery('#divSombra_' + e + ' > table tbody tr').hover(function(){
				jQuery(this).addClass('over');
			}, function(){
				jQuery(this).removeClass('over');
			})
		}
			
				
		//Cuento las filas
		jQuery(trObjetivoStr).each(function(e){
			cantidadRows = cantidadRows + 1;
		})
		
				
		//Ajusto el div contenedor
		jqDivContenedor.width(anchoTabla);
		jqDivContenedor.height(altoTabla);
		
		//Objetos Contenidos
		var jqDivSupDer = null;
		var jqDivSupIzq = null;
		var jqDivInfDer = null;
		var jqDivInfIzq = null;
		var jqBordeIzq = null;
		var jqBordeDer = null;
		var jqBordeInf = null;
		var offsetIE = 0;
		var offsetIEhoriz = 0;
		
		//Obtengo los divs
		var divsObjetivoStr = '#divSombra_' + e + ' > div';
		jQuery( divsObjetivoStr ).each(function(e){
			
			switch (this.className){
				case 'supDer':
					jqDivSupDer = jQuery(this);
					break;
				case 'supIzq':
					jqDivSupIzq = jQuery(this);
					break;
				case 'infIzq':
					jqDivInfIzq = jQuery(this);
					break;
				case 'infDer':
					jqDivInfDer = jQuery(this);
					break;
				case 'rellenoInf':
					jqBordeInf = jQuery(this);
					break;
				case 'rellenoIzq':
					jqBordeIzq = jQuery(this);
					break;
				case 'rellenoDer':
					jqBordeDer = jQuery(this);
					break;
			}
			
		})
		
		//Estilos para tablas de una sola fila
		if (cantidadRows == 1){
			jqBordeDer.hide();
			jqBordeIzq.hide();
			jqDivSupDer.css('height','11px');
			jqDivSupIzq.css('height','11px');
		} else {
			//Establezco el borde derecho		
			jqBordeDer.height(altoTabla -48);
		}
			
		//Establezco el borde inferior
		jqBordeInf.width(anchoTabla - 28);
			
		//Establezco el borde izquierdo		
		jqBordeIzq.height(altoTabla -48);
	
	})

}

function redibujarError(){
	jQuery('.error').css('position','relative');
	jQuery('.error .capa5 .capa6').css('display','block');
}
