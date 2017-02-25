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
			        			eval("rows[n].onclick = function() {document.location = '" + rows[n].childNodes[j].childNodes[k].href + "'}");
			        			sortir = true;
		        			}
		        		}
	    	    	}
	    	    	if (sortir) break;
		        }
		    }
		}
		if (table.className.indexOf("lov") != -1) {
	        var previousClass = null;
		    var tbody = table.getElementsByTagName("tbody")[0];
		    if (tbody == null) {
		        var rows = table.getElementsByTagName("tr");
		    } else {
		        var rows = tbody.getElementsByTagName("tr");
		    }
		    for (var n = 0; n < rows.length; n++) {		    
		    	rows[n].onmouseover = function() { previousClass=this.className;this.className='over ' + this.className };
		        rows[n].onmouseout = function() { this.className=previousClass };
		        for (var j = 0; j < rows[n].childNodes.length; j++) {
		        	for (var k = 0; k < rows[n].childNodes[j].childNodes.length; k++) {
		        		if (rows[n].childNodes[j].childNodes[k].nodeName == 'A')
		        			eval("rows[n].onclick = " + rows[n].childNodes[j].childNodes[k].onclick);
		        			//if (k < 3) alert("rows[i].onclick = function() {" + rows[i].childNodes[j].childNodes[k].onclick + "}");
	    	    	}
		        }
		    }
		}
    }
}

	onloadAfegir(initTableHooks);
