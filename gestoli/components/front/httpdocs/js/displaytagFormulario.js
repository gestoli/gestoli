/*
	include_once('css/displaytag.css');
*/


function initTableHooks() {

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
			        			// eval("rows[i].onclick = function() {document.location = '" + rows[i].childNodes[j].childNodes[k].href + "'}");
			        			// eval("rows[n].onclick = " + rows[n].childNodes[j].childNodes[k].ondblclick" );
			        			eval("rows[n].onclick = function() {addOption_"+table.id+"(" + rows[n].childNodes[j].childNodes[k].rel + ");seleccionarFila("+n+");return false; }");
			        			
			        			for(var op in options_etiquetatgesList){
									if( op == rows[n].childNodes[j].childNodes[k].rel && options_etiquetatgesList[op]== true){
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



	onloadAfegir(initTableHooks);



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