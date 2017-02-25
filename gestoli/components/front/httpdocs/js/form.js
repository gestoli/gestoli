	/*
	include_once('js/jscalendar/calendar_stripped.js');
	include_once('js/jscalendar/calendar-setup_stripped.js');
	include_once('js/jscalendar/lang/calendar-ca.js');
	include_once('js/jscalendar/calendar-viti.css');
	include_once('js/selectbox.js');*/

function autocompletar(camp, data) {
	$("#suggest_"+camp).autocomplete(
			data, {
				delay:10,
				minChars:2,
				mustMatch: false,
				matchContains: true,
				selectFirst: true,
				formatItem: function(row) {
					return row.nom;
				},
				formatMatch: function(row) {
					return row.codi + " " + row.nom;
				},
				formatResult: function(row) {
					return row.nom;
				}
			}
	);
	
	$("#suggest_"+camp).result(function(event, row) {
		$("#"+camp).val(row.codi);
	});
}


	var campIndex;



	function esInputCalendari(input) {
		return input.type == "text" && input.className && input.className.indexOf("inputData") != -1;
	}
	function inicialitzarInputCalendari(input) {
		if (esInputCalendari(input)) {
			var newImg = document.createElement('img');
			newImg.src = "img/icons/calendari.gif";
			newImg.id = input.id + "_trigger";
			newImg.className = "data";
			newImg.height = "20";
			newImg.width = "20";
			input.parentNode.insertBefore(newImg, input.nextSibling);
			eval(
				'Calendar.setup({' +
				'inputField     :    "' + input.id + '",' +
				'ifFormat       :    "%d/%m/%Y",' +
				'button         :    "' + newImg.id + '",' +
				'align          :    "br",' +
				'singleClick    :    true' +
				'});');
			input.size = "10";
			input.maxlength = "10";
			input.calendariImg = newImg;
		}
	}
	function finalitzarInputCalendari(input) {
		if (esInputCalendari(input)) {
			var imatge = input.calendariImg;
			if (imatge != null)
				imatge.parentNode.removeChild(imatge);
		}
	}
	function afegirCamp(img, id, nom, tipus, tipusCamp) {
		var unitat = document.getElementById(id);
		var novaUnitat = unitat.cloneNode(true);
		novaUnitat.id = 'unitat_' + campIndex;
		var icona = document.createElement('IMG');
		icona.id = 'iconamenys_' + campIndex;
		icona.className = 'esborrar';
		icona.src = 'imatges/list-remove.gif';
		icona.onclick = function () {esborrarCamp(this)};
		unitat.parentNode.insertBefore(icona, img);
		unitat.parentNode.insertBefore(novaUnitat, img);
		var indexVell = unitat.id.substring('unitat_'.length);
		var fills = novaUnitat.childNodes;
		for (var nfil = 0; nfil < fills.length; nfil++) {
			if (fills[nfil].id) {
				var iid = fills[nfil].id.indexOf(indexVell);
				if (iid != -1)
					fills[nfil].id = fills[nfil].id.substring(0, iid) + campIndex;
			}
		}
		if (tipus == 'date') {
			for (var nfil = fills.length - 1; nfil >= 0; nfil--) {
				if (fills[nfil].nodeName != 'INPUT') {
					novaUnitat.removeChild(fills[nfil]);
				}
			}
			inicialitzarInputCalendari(document.getElementById('valor_' + campIndex));
		}
		if (tipusCamp == 'lov') {
			icona.className = icona.className + ' lovmenys';
			inicialitzarEnllasPopup(novaUnitat.getElementsByTagName('A')[0]);
		}
		campIndex++;
	}
	function esborrarCamp(img) {
		var index = img.id.substring('iconamenys_'.length);
		var unitat = document.getElementById('unitat_' + index);
		var fills = unitat.childNodes;
		for (var nfil = fills.length - 1; nfil >= 0; nfil--)
			unitat.removeChild(fills[nfil]);
		unitat.parentNode.removeChild(unitat);
		img.parentNode.removeChild(img);
	}

	function netejarLov(idUnitat) {
	    var index = idUnitat.substring('unitat_'.length);
		var campValor = document.getElementById('valor_' + index);
		var campText = document.getElementById('valor_text_' + index);
		var campMostrar = document.getElementById('valor_mostrar_' + index);
		campValor.value = "";
		campText.value = "";
		campMostrar.innerHTML = "&nbsp;";
	}
    function modificarLovUrl(img) {
    	var enllas = img.parentNode
        var index = enllas.parentNode.id.substring('unitat_'.length);
        var iini = enllas.href.indexOf('unitat_');
        var ifin = enllas.href.indexOf('&', iini);
        var novaUrl = enllas.href.substring(0, iini) + 'unitat_' + index + enllas.href.substring(ifin);
        enllas.href = novaUrl;
    }
    function canviInputSelect(input) {
    	var index = input.id.substring('valor_'.length);
    	var inputComplem = document.getElementById('valor_text_' + index);
    	for (var nopt = 0; nopt < input.options.length; nopt++) {
    		if (input.options[nopt].selected)
    			inputComplem.value = input.options[nopt].innerHTML;
    	}
    }

	function canviarVisible(id) {
		var obj = document.getElementById(id);
		if (obj.style.display != 'none')
			obj.style.display = 'none';
		else
			obj.style.display = '';
		return obj.style.display != 'none';
	}
	function canviarVisibilitat(id) {
		var obj = document.getElementById(id);
		if (obj.style.visibility != 'hidden')
			obj.style.visibility = 'hidden';
		else
			obj.style.visibility = 'visible';
		return obj.style.visibility != 'hidden';
	}
	function desplegarReplegar(img, id) {
		if (canviarVisible(id)) {
			img.src = 'imatges/go-up.gif';
		} else {
			img.src = 'imatges/go-down.gif';
		}
	}

	function canvisCampFormulari(obj) {
		obj.form.haCanviat = true;
		if (obj.nodeName == 'SELECT')
			canviInputSelect(obj);
	}



	function inicialitzarMilloresFormulari(formulari) {
		if (!formulari.nodeName || !formulari.nodeName == 'FORM')
			formulari = document.getElementById(formulari);
		var inputs = formulari.getElementsByTagName('INPUT');
		campIndex = 0;
		for (var ninp = 0; ninp < inputs.length; ninp++) {
			inicialitzarInputCalendari(inputs[ninp]);
			initPopUpHooks()
			if (inputs[ninp].name == 'nom')
				campIndex++;
		}
	}
	function initMilloresFormulari() {
		if (!document.getElementsByTagName) return;
		var ffor = document.getElementsByTagName('FORM');
		for (ninp = 0; ninp < ffor.length; ninp++)
			if (ffor[ninp].className && ffor[ninp].className.indexOf("extended") != -1)
				inicialitzarMilloresFormulari(ffor[ninp]);
	}
	
/*
 * Anclatges per a les Popups
 */
function extractParams(fname, clazz) {
	var beginAt = clazz.indexOf(fname);
	var pars = clazz.substring(beginAt);
	var endAt = pars.indexOf(")");
	pars = pars.substring(0, endAt);
	pars = pars.split("(")[1];
	return (pars) ? pars.split(",") : null;
}

function openPopUp(strURL,strType,strWidth,strHeight) {
    var strOptions="";
    if (strType=="console") strOptions="resizable,height="+strHeight+",width="+strWidth;
    if (strType=="fixed") strOptions="status,scrollbars,height="+strHeight+",width="+strWidth;
    if (strType=="elastic") strOptions="toolbar,menubar,scrollbars,resizable,location,height="+strHeight+",width="+strWidth;
    var vHeight = screen.availHeight;
    var vWidth = screen.availWidth;
    if (strType=="fullscreen") {
     strOptions='status=no, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, channelmode=yes, fullscreen=yes, top=0, left=0, height='+vHeight+',width='+vWidth;
    }
    window.open(strURL, 'newWin', strOptions);
}
function popupEventHandler(e) {
    if (!e)
    	var e = window.event;
    else
        e.preventDefault();
	if (e.target) targ = e.target;
	else if (e.srcElement) targ = e.srcElement;
    params = extractParams("external", this.getAttribute("rel"));
    if (params != null && params[0]=='fullscreen') {
    	openPopUp(this.href,params[0]);
    	return false;
    }
    if (params == null) {
    	params = new Array();
    	params[0] = 620;
    	params[1] = 450;
   	}
   	if (params.length < 3) 
    	params[2] = 'fixed';
    openPopUp(this.href,params[2], params[0], params[1]);
    return false;
}
function initPopUpHooks() {
    if (!document.getElementsByTagName) return;
    var anchors = document.getElementsByTagName("a");
    for (i=0; i<anchors.length; i++) {
        anchor = anchors[i];
        if (anchor.getAttribute("rel") && anchor.getAttribute("rel").indexOf("external") != -1) {
            anchor.target = "_blank";
            anchor.onclick = popupEventHandler;
        }
    }
}

	onloadAfegir(initMilloresFormulari);
