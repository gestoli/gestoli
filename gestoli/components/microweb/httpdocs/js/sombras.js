/**
 * Establece los tamaños correctos
 * de los divs de menu
 * @param {String} divMenuId
 * @author npalumbo
 */
function setTamanosDivs(divMenuId){

	//Dimensiones del div de menu
	var jqDivMenu = $('#' + divMenuId);
	var altoTotal = jqDivMenu.height();
	var anchoTotal = jqDivMenu.width();
	
	//Objetos contenidos
	var jqSupDer = null;
	var jqInfDer = null;
	var jqInfIzq = null;
	var jqBordeIzq = null;
	var jqRellenoSup = null;
	var jqRellenoInf = null;
	var jqSeparador = null;
	var offsetIE = 0;
	var offsetIEhoriz = 0;
	
	//Elementos del menu (esquinas y rellenos)
    $('#' + divMenuId + ' > div').each(function(i) { 
				
		switch (this.className) {
			case 'supDer':
				jqSupDer = $(this);
				break;
			case 'infDer':
				jqInfDer = $(this);
				break;
			case 'infIzq':
				jqInfIzq = $(this);	
				break;
			case 'bordeIzq':
				jqBordeIzq = $(this);			
				break;
			case 'bordeDer':
				jqBordeDer = $(this);			
				break;				
			case 'rellenoSup':
				jqRellenoSup = $(this);
				break;
			case 'rellenoInf':
				jqRellenoInf = $(this);
				break;
			case 'separadorMenuItems':
				jqSeparador = $(this);
				//modif aamengual. Si hay más de un separador, sólo le aplicaba estilos al último
				jqSeparador.width(anchoTotal);
				break;
		}
		
	});
	
	//Corrección para navegadores malos
	if ($.browser['msie']) {
			offsetIE = 4;
			offsetIEhoriz = 1;	
	}

	//Ancho relleno superior
	jqRellenoSup.width(anchoTotal - (jqSupDer.width()- 14));
	
	//Ancho relleno inferior
	jqRellenoInf.width(anchoTotal - (jqInfIzq.width() + jqInfDer.width()) + (28 - offsetIEhoriz));
	
	//Alto borde izquierdo
	jqBordeIzq.height(altoTotal - offsetIE + 20);
	
	//Alto borde derecho
	jqBordeDer.height(altoTotal - offsetIE);	
	
}

/**
 * Establece los atributos de los divs de sombra en tablas
 */
function setSombraTabla() {	
		
	var jqDivContenedor = $('#layoutSombraTabla');
	var jqTabla = $('table');
	var anchoTabla = jqTabla.width();
	var altoTabla = jqTabla.height();
	
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
	$('#layoutSombraTabla > div').each(function(e){
		
		switch (this.className){
			case 'supDer':
				jqDivSupDer = $(this);
				break;
			case 'supIzq':
				jqDivSupIzq = $(this);
				break;
			case 'infIzq':
				jqDivInfIzq = $(this);
				break;
			case 'infDer':
				jqDivInfDer = $(this);
				break;
			case 'rellenoInf':
				jqBordeInf = $(this);
				break;
			case 'rellenoIzq':
				jqBordeIzq = $(this);
				break;
			case 'rellenoDer':
				jqBordeDer = $(this);
				break;
		}
	})
		
	//Corrección para navegadores malos
	if ($.browser['msie']) {
		offsetIE = 4;
		offsetIEhoriz = 1;
	}
	
	//Establezco el borde inferior
	jqBordeInf.width(anchoTabla - 28);
		
	//Establezco el borde izquierdo		
	jqBordeIzq.height(altoTabla -48);

	//Establezco el borde derecho		
	jqBordeDer.height(altoTabla -48);

}
