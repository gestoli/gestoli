$.fn.extend({
	keyFilter: function(table){
		var $item = this;
		var $rows = $("#"+table).children("tbody").find("tr");
		$item.bind("keydown keyup keypress", function() {
			var filter = $item.val().toLowerCase();
			$rows.each(function(){
				var show = false;
				$(this).find("td").each(function(){
					var text = $(this).text().trim().toLowerCase();
					if (text.indexOf(filter) >= 0) { show = true; }
				});
				if (show) { $(this).show(); }
				else { $(this).hide(); }
			});
			setSombraTabla();
		});
	}
}); 