--Afegim una columna per controlar si un lot està finalitzat o no. 'Es fa automàticament amb hibernate'
alter table oli_lot ADD COLUMN lot_datafi date;

--Actualitzam la columna datafi amb tots els lots que no tenen contingut
UPDATE oli_lot set lot_datafi = (
	SELECT sl.slo_vendat
	FROM oli_sortida_lot sl
	WHERE sl.slo_codi = (SELECT MAX(sl2.slo_codi) FROM oli_sortida_lot sl2 WHERE sl2.slo_codlot = lot_codi)
		--AND sl.slo_codlot = lot_codi
	)
WHERE lot_numbotact = 0 AND lot_datafi is null;