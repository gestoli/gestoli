-- AUTOCONTROL: s'ha de fer per bota i no per lot. posar a false la propietat not null del camp aut_codlot

ALTER TABLE public.oli_autocontrol ALTER COLUMN aut_codlot TYPE int8 USING aut_codlot::int8;

ALTER TABLE public.oli_autocontrol ALTER COLUMN aut_codlot DROP NOT NULL;

ALTER TABLE public.oli_autocontrol ALTER COLUMN aut_codlot DROP DEFAULT;

