var onload_funcions = new Array();

function onloadControl() {
	for (var i = 0; i < onload_funcions.length;i++)
		onload_funcions[i]();
}

function onloadAfegir(f) {
	if (window.onload) {
		if (window.onload != onloadControl){
			onload_funcions[0] = window.onload;
			window.onload = onloadControl;
		}		
		onload_funcions[onload_funcions.length] = f;
	} else {
		window.onload = f;
	}
}
