MapaEstabliment = function (id, config) {
		if (id) { this.init(id, config); }
	};
    MapaEstabliment.prototype = {
        id: null,
        mapaObjId: null,
        idPrefix: null,
        imatgeZona: null,
        diposits: null,
        partides: null,
        lotes: null,  
        botes: null,
        posicionarCallback: null,
        seleccioCallback: null,
        noPosicionarMissatge: null,
        noPosicionarCallback: null,
        historialMissatge: null,
        historialUrl: null,
        resumMissatge: null,
        resumUrl: null,
        canviMissatge: null,
		canviUrl: null,
        mostrarMenu: null,
        ampladaMax: 515,
        imgIds: null,
		imgAlt: null,
		imgAmple: null,
	    llevaPx: function (str) {
	        return str.substring(0, str.indexOf("px"));
	    },
	    llevaPxEstil: function (obj, estil) {
	        eval("var estil = obj.style." + estil + ";");
	        return this.llevaPx(estil);
	    },
	    indexWithId: function (id) {
	    	for (var i = 0; i < this.diposits.length; i++) {
	    		if (this.diposits[i][0] == id.substring(this.idPrefix.length))
	    			return i;
	    	}
	    },
	    deseleccionarTot: function () {
	    	var fons = document.getElementById(this.mapaObjId);
	    	for (var i = 0; i < fons.childNodes.length; i++) {
	    		fons.childNodes[i].className = "contdr";
	    		fons.childNodes[i].mapaEstablimentSelected = false;
	    	}
	    },
	    onClick: function (elem) {
	    	if (elem.mapaEstablimentClickOk) {
	    		elem.mapaEstablimentSelected = !elem.mapaEstablimentSelected;
	    		elem.className = (elem.mapaEstablimentSelected) ? "contdr contdr-sel" : "contdr";
	    		this.seleccioCallback(elem.id.substring(this.idPrefix.length));
	    	}
	    	elem.mapaEstablimentClickOk = true;
	    },
	    endDrag: function (elem) {
	        elem.getEl().mapaEstablimentClickOk = false;
	    	this.posicionarCallback(
	    		elem.getEl().id.substring(this.idPrefix.length),
	    		this.llevaPxEstil(elem.getEl(),'left'),
	    		this.llevaPxEstil(elem.getEl(),'top'),
	    		elem.getEl().tipus);
	    },
	    configuraAmbImatge: function (obj, src) {
	    	var img = new Image();
	        img.src = src;
			  var i = parseInt(src.substring(src.lastIndexOf('=') + 1));
			  var alt = img.height;
			  var ample = img.width;
			  if (this.imgIds != null) {
				  alt = this.imgAlt[this.imgIds.indexOf(i)];
				  ample = this.imgAmple[this.imgIds.indexOf(i)];
			  }
	        
	        var amplada = (ample > this.ampladaMax || ample <= 0) ? this.ampladaMax : ample;
	        obj.style.width = amplada + "px";
	        obj.style.height = alt + "px";
	        obj.style.backgroundImage = "url(" + src + ")";
	        obj.style.backgroundRepeat = "no-repeat";
	        return alt;
	    },
	    configuraAmbImatgeImage: function (obj, src) {
	    	var img = new Image();
	        img.src = src;
			  var i = parseInt(src.substring(src.lastIndexOf('=') + 1));
			  var alt = img.height;
			  var ample = img.width;
			  var si = src.substring(10, 15);
			  if ( si == "oliva") {
				  alt = 50;
				  ample = 50;
			  } else if ( si == "botes") {
				  alt = 41;
				  ample = 33;
			  } else if ( si == "lotOl") {
				  alt = 50;
				  ample = 32;
			  }
			  if (this.imgIds != null && !isNaN(i)) {
				  alt = this.imgAlt[this.imgIds.indexOf(i)];
				  ample = this.imgAmple[this.imgIds.indexOf(i)];
			  }
	        var amplada = (ample > this.ampladaMax || ample <= 0) ? this.ampladaMax : ample;
	        obj.style.width = amplada + "px";
	        obj.style.height = alt + "px";
	        obj.src = src;
	        return alt;
	    },
	    mostrarContenidor: function (contenidor, fons) {
	    	var dcontid = this.idPrefix + contenidor[0];
	        var dcontdr = document.createElement('DIV');
	        dcontdr.id = dcontid;
	        dcontdr.mapaEstablimentObj = this;
	        dcontdr.mapaEstablimentClickOk = true;
	        dcontdr.mapaEstablimentSelected = contenidor[8];
	        dcontdr.tipus = contenidor[11];
	        // Menu contextual
	        if (this.mostrarMenu) {
		        dcontdr.contextMenu = new YAHOO.widget.ContextMenu("contextmenu_" + dcontid, {trigger:dcontid});
		        if (dcontdr.contextMenu != null){
			        var render = false;
			        if (this.noPosicionarMissatge!=null && this.noPosicionarMissatge!="") {
			        	render = true;
				        var mitem = new YAHOO.widget.ContextMenuItem(this.noPosicionarMissatge);
				        mitem.clickEvent.subscribe(this.esborraContenidor);
				        mitem.contObj = dcontdr;
				        mitem.mapaEstablimentObj = this;
				        dcontdr.contextMenu.addItem(mitem);
			        }
			        if (contenidor[10]) {
				        render = true;
				        var hitem  = new YAHOO.widget.ContextMenuItem(this.historialMissatge, {url:this.historialUrl + "?tipus=" + contenidor[12] + "&id=" + contenidor[0]});
				        var hitem2 = new YAHOO.widget.ContextMenuItem(this.resumMissatge, {url:this.resumUrl + "?tipus=" + contenidor[12] + "&id=" + contenidor[0]});
				        var hitem3 = new YAHOO.widget.ContextMenuItem(this.canviMissatge, {url:this.canviUrl + "?tipus=" + contenidor[12] + "&id=" + contenidor[0] + "&zonaId=" + contenidor[13]});
				        if (contenidor[15] == "false") {
				        	dcontdr.contextMenu.addItem(hitem);
				        }
				        dcontdr.contextMenu.addItem(hitem2);
				        if (this.canviMissatge!=null && this.canviMissatge!="") {
				        	dcontdr.contextMenu.addItem(hitem3);
				        }
			        }
			        if (render)
						dcontdr.contextMenu.render(document.body);
		        }
			}
			// Event de selecció del contenidor
			if (this.seleccioCallback)
				dcontdr.onclick = function(){this.mapaEstablimentObj.onClick(this)};
			// Disseny del bloc del contenidor
//	        var dcontdrimg = document.createElement('DIV');
	        var dcontdrimg = document.createElement('IMG');
	        var dcontdrper = document.createElement('DIV');
	        var dcontdrperi = document.createElement('DIV');
	        dcontdr.appendChild(dcontdrimg);
	        dcontdrper.appendChild(dcontdrperi);
	        // Si no indicamos nada en contenidor[4] no pintamos el nivel
	        dcontdr.appendChild(dcontdrper);
	        fons.appendChild(dcontdr);
	        dcontdr.className = (contenidor[8]) ? "contdr contdr-sel" : "contdr";
	        dcontdrimg.className = "contdr-img";
	        dcontdrper.className = "contdr-per";
	        dcontdrperi.className = "contdr-peri";
	        if (contenidor[9] != '')
	        	dcontdrper.style.backgroundColor = "#" + contenidor[9];
	        if (contenidor[4]>100) contenidor[4] = 100;
	        if (contenidor[4]<0) contenidor[4] = 0;
	        dcontdrperi.style.height = (100 - contenidor[4]) + "%";
	        dcontdrperi.style.overflow = "hidden";
	        dcontdr.style.position = "absolute";
	        dcontdr.style.left = contenidor[2] + "px";
	        dcontdr.style.top = contenidor[3] + "px";
	        dcontdr.style.backgroundColor = "#FFFFFF";
	        var altura = this.configuraAmbImatgeImage(dcontdrimg, contenidor[1]);
	        //dcontdrper.style.height = (parseInt(this.llevaPx(dcontdrimg.style.height)) - 2) + "px";
	        dcontdrper.style.height = (altura - 2) + "px";
	        // Tooltip informatiu
	        var continfo = contenidor[5];
	        new YAHOO.widget.Tooltip(
				dcontid + "_tooltip", {
				context:dcontid,  
				text:continfo,
				hidedelay:0,
				autodismissdelay:60000});
			//  Drag & Drop
			if (contenidor[6]) {
				var movil = new YAHOO.util.DD(dcontid);
		        movil.setXConstraint(contenidor[2], this.llevaPxEstil(fons, "width") - contenidor[2] - this.llevaPxEstil(dcontdrimg, "width") - 15);
	    	    movil.setYConstraint(contenidor[3], this.llevaPxEstil(fons, "height") - contenidor[3] - this.llevaPxEstil(dcontdrimg, "height") - 8);
	    	    movil.mapaEstablimentObj = this;
	    	    movil.endDrag = function(e){this.mapaEstablimentObj.endDrag(this)};
	        }
	        // Opacitat
	        if (contenidor[7]) {
	        	YAHOO.util.Dom.setStyle(dcontid, 'opacity', 0.5);
	        	document.getElementById(dcontid).style.border = "2px solid red";
	        }
	        
	        //Nom del contenidor
	        var divNomContenidor = document.createElement('DIV');
	        var nomContenidor=document.createTextNode(contenidor[14]);
	        divNomContenidor.style.height = "14px";
	        divNomContenidor.style.left = "-30px";
	        divNomContenidor.style.overflow = "hidden";
	        divNomContenidor.style.position = "absolute";
	        divNomContenidor.style.textAlign = "center";
	        divNomContenidor.style.top = "-13px";
	        divNomContenidor.style.width = "100px";
	        divNomContenidor.style.fontSize = "7pt";
	        divNomContenidor.appendChild(nomContenidor);
	        dcontdr.appendChild(divNomContenidor);
	    },
	    nouContenidor: function (continfo) {
	    	var fons = document.getElementById(this.mapaObjId);
	    	this.mostrarContenidor(continfo, fons);
	    },
	    esborraContenidor: function () {
	    	this.contObj.style.display = 'none';
	    	this.mapaEstablimentObj.noPosicionarCallback(this.contObj.id.substring(this.mapaEstablimentObj.idPrefix.length), this.contObj.tipus);
	    },
	    init: function (id, config) {
	        this.id = id;
	    	this.mapaObjId = config.id;
	    	this.idPrefix = config.idPrefix;
	    	this.diposits = config.diposits;
	    	this.partides = config.partides;
	    	this.lotes = config.lotes;
	    	this.botes = config.botes;
	    	this.imatgeZona = config.imatgeZona;
	    	this.posicionarCallback = config.posicionarCallback;
	    	this.seleccioCallback = config.seleccioCallback;
	    	this.noPosicionarMissatge = config.noPosicionarMissatge;
			this.noPosicionarCallback = config.noPosicionarCallback;
			this.historialMissatge = config.historialMissatge;
			this.historialUrl = config.historialUrl;
			this.resumMissatge = config.resumMissatge;
			this.resumUrl = config.resumUrl;
			this.canviMissatge = config.canviMissatge;
			this.canviUrl = config.canviUrl;
			this.mostrarMenu = config.mostrarMenu;
			this.ampladaMax = config.ampladaMax;
	    	var imgtmp;
    		imgtmp = new Image();
    		imgtmp.src = this.imatgeZona;
    		for (var i = 0; i < this.diposits.length; i++) {
    			if (this.diposits[i].length > 0) {
    				imgtmp.src = config.diposits[i][1];
    			}
    		}
    		for (var i = 0; i < this.partides.length; i++) {
    			if (this.partides[i].length > 0) {
    				imgtmp.src = config.partides[i][1];
    			}
    		}
    		for (var i = 0; i < this.lotes.length; i++) {
    			if (this.lotes[i].length > 0) {
    				imgtmp.src = config.lotes[i][1];
    			}
    		}
    		for (var i = 0; i < this.botes.length; i++) {
    			if (this.botes[i].length > 0) {
    				imgtmp.src = config.botes[i][1];
    			}
    		}
	    },
	    mostrar: function (seleccio) {
	    	var fons = document.getElementById(this.mapaObjId);
	        this.configuraAmbImatge(fons, this.imatgeZona);
	        for (var i = 0; i < this.diposits.length; i++) {
	        	if (this.diposits[i].length > 0){
	        		if (seleccio!=null) {
	        			for (var x=0; x < seleccio.length; x++) {
	        				/*if (seleccio[x][0] == this.diposits[i][0]) this.diposits[i][8]=true;*/
	        				if (seleccio[x].id == this.diposits[i][0]) this.diposits[i][8]=true;
	        			}
	        		}
	        		this.mostrarContenidor(this.diposits[i], fons);
	        	}
	        }
	        for (var i = 0; i < this.partides.length; i++) {
	        	if (this.partides[i].length > 0){
	        		if (seleccio!=null) {
	        			for (var x=0; x < seleccio.length; x++) {
	        				/* if (seleccio[x][0] == this.partides[i][0]) this.partides[i][8]=true; */
	        				if (seleccio[x].id == this.partides[i][0]) this.partides[i][8]=true;
	        			}
	        		}
	        		this.mostrarContenidor(this.partides[i], fons);
	        	}
	        }
	        for (var i = 0; i < this.lotes.length; i++) {
	           	if (this.lotes[i].length > 0){
	        		if (seleccio!=null) {
	        			for (var x=0; x < seleccio.length; x++) {
	        				/* if (seleccio[x][0] == this.partides[i][0]) this.partides[i][8]=true; */
	        				if (seleccio[x].id == this.lotes[i][0]){
	        					 this.lotes[i][8]=true;
	        				}
	        			}
	        		}
	        		this.mostrarContenidor(this.lotes[i], fons);
	        	}
	        }
	        for (var i = 0; i < this.botes.length; i++) {
	           	if (this.botes[i].length > 0){
	        		if (seleccio!=null) {
	        			for (var x=0; x < seleccio.length; x++) {
	        				/* if (seleccio[x][0] == this.partides[i][0]) this.partides[i][8]=true; */
	        				if (seleccio[x].id == this.lotes[i][0]){
	        					 this.botes[i][8]=true;
	        				}
	        			}
	        		}
	        		this.mostrarContenidor(this.botes[i], fons);
	        	}
	        }
	    }
    }