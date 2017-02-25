


------------------
-- Create database
------------------
--CREATE DATABASE gestoli_db;



---------
-- Script
---------
/* Create Sequences */
CREATE sequence "hibernate_sequence"
    INCREMENT 1  MINVALUE 1
    MAXVALUE 9223372036854775807  START 1
    CACHE 1;


/* Create Tables */


Create table "oli_usuari"
(
	"usu_codi" BigSerial NOT NULL,
	"usu_codidi" Char(2) NOT NULL,
	"usu_actiu" Boolean NOT NULL Default true,
	"usu_usuari" Varchar(128) NOT NULL UNIQUE,
	"usu_contra" Varchar(128) NOT NULL,
	"usu_email" Varchar(128),
	"usu_observ" Text,
constraint "oli_usu_pk" primary key ("usu_codi")
) With Oids;


Create table "oli_grup"
(
	"gru_codi" Varchar(32) NOT NULL,
	"gru_nom" Varchar(64) NOT NULL,
	"gru_observ" Text,
constraint "oli_gru_pk" primary key ("gru_codi")
) With Oids;


Create table "oli_taxa"
(
	"tax_codcam" Bigint NOT NULL,
	"tax_majigu" Double precision NOT NULL,
	"tax_menor" Double precision NOT NULL,
	"tax_volum" Double precision NOT NULL,
	"tax_coneti" Double precision NOT NULL,
constraint "oli_tax_pk" primary key ("tax_codcam")
) With Oids;


Create table "oli_color"
(
	"col_codi" Serial NOT NULL,
	"col_nom" Varchar(64) NOT NULL,
	"col_valor" Char(6),
	"col_observ" Text,
constraint "oli_col_pk" primary key ("col_codi")
) With Oids;


Create table "oli_idioma"
(
	"idi_codi" Char(2) NOT NULL,
	"idi_actiu" Boolean NOT NULL Default true,
	"idi_nom" Varchar(64) NOT NULL,
	"idi_observ" Text,
constraint "oli_idi_pk" primary key ("idi_codi")
) With Oids;


Create table "oli_tipus_envas"
(
	"ten_codi" BigSerial NOT NULL,
	"ten_codmat" Integer NOT NULL,
	"ten_codcol" Integer NOT NULL,
	"ten_actiu" Boolean NOT NULL Default true,
	"ten_volum" Double precision NOT NULL,
	"ten_observ" Text,
constraint "oli_ten_pk" primary key ("ten_codi")
) With Oids;


Create table "oli_arxiu"
(
	"arx_codi" BigSerial NOT NULL,
	"arx_nom" Varchar(256) NOT NULL,
	"arx_mime" Varchar(255) NOT NULL,
	"arx_tamany" Integer NOT NULL,
	"arx_width" Integer,
	"arx_height" Integer,
	"arx_binari" Bytea NOT NULL,
constraint "oli_arx_pk" primary key ("arx_codi")
) With Oids;


Create table "oli_material_tipus_envas"
(
	"men_codi" Serial NOT NULL,
	"men_nom" Varchar(64) NOT NULL,
	"men_icona" Bigint NOT NULL,
	"men_observ" Text,
constraint "oli_men_pk" primary key ("men_codi")
) With Oids;


Create table "oli_etiquetatge"
(
	"eti_codi" BigSerial NOT NULL,
	"eti_codmar" Bigint NOT NULL,
	"eti_codten" Bigint NOT NULL,
	"eti_actiu" Boolean NOT NULL Default true,
	"eti_imafro" Bigint NOT NULL,
	"eti_imapos" Bigint NOT NULL,
	"eti_observ" Text,
constraint "oli_eti_pk" primary key ("eti_codi")
) With Oids;


Create table "oli_marca"
(
	"mar_codi" BigSerial NOT NULL,
	"mar_actiu" Boolean NOT NULL Default true,
	"mar_nom" Varchar(64) NOT NULL UNIQUE,
	"mar_denori" Boolean NOT NULL Default true,
	"mar_observ" Text,
constraint "oli_mar_pk" primary key ("mar_codi")
) With Oids;


Create table "oli_establiment"
(
	"est_codi" BigSerial NOT NULL,
	"est_codcam" Bigint NOT NULL,
	"est_codtes" Integer NOT NULL,
	"est_codusu" Bigint NOT NULL,
	"est_codsol" Bigint NOT NULL,
	"est_codori" Bigint,
	"est_codirb" Varchar(128),
	"est_codirc" Varchar(128),
	"est_actiu" Boolean NOT NULL Default true,
	"est_nom" Varchar(128) NOT NULL,
	"est_cif" Varchar(16) NOT NULL,
	"est_direcc" Varchar(256),
	"est_poblac" Varchar(128),
	"est_cp" Varchar(16),
	"est_email" Varchar(128) NOT NULL,
	"est_telefo" Varchar(16),
	"est_fax" Varchar(16),
	"est_numtre" Integer,
	"est_superf" Double precision,
	"est_unimes" Char(1) NOT NULL Check (est_unimes = 'k' OR est_unimes = 'l'),
	"est_tempas" Integer,
	"est_cappro" Double precision,
	"est_envman" Boolean,
	"est_etiman" Boolean,
	"est_observ" Text,
constraint "oli_est_pk" primary key ("est_codi")
) With Oids;


Create table "oli_tipus_establiment"
(
	"tes_codi" Serial NOT NULL,
	"tes_nom" Varchar(64) NOT NULL,
constraint "oli_tes_pk" primary key ("tes_codi")
) With Oids;


Create table "oli_solicitant"
(
	"sol_codi" BigSerial NOT NULL,
	"sol_nom" Varchar(64) NOT NULL,
	"sol_nif" Varchar(16),
	"sol_perfil" Varchar(64),
	"sol_telefo" Varchar(16),
	"sol_data" Timestamp NOT NULL Default now(),
constraint "oli_sol_pk" primary key ("sol_codi")
) With Oids;


Create table "oli_estmar"
(
	"ema_codest" Bigint NOT NULL,
	"ema_codmar" Bigint NOT NULL,
constraint "oli_ema_pk" primary key ("ema_codest","ema_codmar")
) With Oids;


Create table "oli_olivicultor"
(
	"oli_codi" BigSerial NOT NULL,
	"oli_codcam" Bigint NOT NULL,
	"oli_codusu" Bigint NOT NULL,
	"oli_codori" Bigint,
	"oli_codido" Varchar(128) NOT NULL,
	"oli_codide" Varchar(128),
	"oli_nom" Varchar(128) NOT NULL,
	"oli_direcc" Varchar(256) NOT NULL,
	"oli_poblac" Varchar(128) NOT NULL,
	"oli_cp" Varchar(16) NOT NULL,
	"oli_nif" Varchar(16) NOT NULL,
	"oli_compte" Varchar(64) NOT NULL,
	"oli_altado" Boolean NOT NULL Default true,
	"oli_cartil" Boolean NOT NULL Default false,
	"oli_email" Varchar(128),
	"oli_telefo" Varchar(16),
	"oli_fax" Varchar(16),
	"oli_uidrfid" Varchar(64),
	"oli_subven" Boolean NOT NULL Default false,
	"oli_observ" Text,
constraint "oli_oli_pk" primary key ("oli_codi")
) With Oids;


Create table "oli_finca"
(
	"fin_codi" BigSerial NOT NULL,
	"fin_codoli" Bigint NOT NULL,
	"fin_codori" Bigint,
	"fin_actiu" Boolean NOT NULL Default true,
	"fin_nom" Varchar(128) NOT NULL,
	"fin_observ" Text,
constraint "oli_fin_pk" primary key ("fin_codi")
) With Oids;


Create table "oli_plantacio"
(
	"pla_codi" BigSerial NOT NULL,
	"pla_codfin" Bigint NOT NULL,
	"pla_codori" Bigint,
	"pla_actiu" Boolean NOT NULL Default true,
	"pla_munici" Varchar(128) NOT NULL,
	"pla_poligo" Varchar(128) NOT NULL,
	"pla_parcel" Varchar(128) NOT NULL,
	"pla_regadi" Boolean NOT NULL Default false,
	"pla_anypl" Smallint NOT NULL,
	"pla_observ" Text,
constraint "oli_pla_pk" primary key ("pla_codi")
) With Oids;


Create table "oli_descomposicio_plantacio"
(
	"dpl_codi" BigSerial NOT NULL,
	"dpl_codpla" Bigint NOT NULL,
	"dpl_codvov" Integer NOT NULL,
	"dpl_codori" Bigint,
	"dpl_numoli" Integer NOT NULL,
	"dpl_superf" Double precision NOT NULL,
	"dpl_maxprod" Double precision NOT NULL,
	"dpl_observ" Text,
constraint "oli_dpl_pk" primary key ("dpl_codi")
) With Oids;


Create table "oli_varietat_oliva"
(
	"vov_codi" Serial NOT NULL,
	"vov_nom" Varchar(128) NOT NULL,
	"vov_autori" Boolean NOT NULL Default true,
	"vov_icona" Bigint NOT NULL,
	"vov_color" Char(6) NOT NULL Default '919C16'::bpchar,
	"vov_observ" Text,
constraint "oli_vov_pk" primary key ("vov_codi")
) With Oids;


Create table "oli_zona"
(
	"zon_codi" BigSerial NOT NULL,
	"zon_codest" Bigint NOT NULL,
	"zon_codori" Bigint,
	"zon_actiu" Boolean NOT NULL Default true,
	"zon_nom" Varchar(128) NOT NULL,
	"zon_fictic" Boolean NOT NULL Default false,
	"zon_imapla" Bigint,
	"zon_defect" Boolean NOT NULL Default false,
	"zon_observ" Text,
constraint "oli_zon_pk" primary key ("zon_codi")
) With Oids;


Create table "oli_diposit"
(
	"dip_codi" Bigint NOT NULL,
	"dip_codest" Bigint NOT NULL,
	"dip_codzon" Bigint NOT NULL,
	"dip_codmdi" Integer,
	"dip_codcao" Integer,
	"dip_codori" Bigint,
	"dip_actiu" Boolean NOT NULL Default true,
	"dip_codias" Varchar(128) NOT NULL,
	"dip_fictic" Boolean NOT NULL Default false,
	"dip_capaci" Double precision,
	"dip_posx" Integer,
	"dip_posy" Integer,
	"dip_volact" Double precision,
	"dip_acides" Double precision,
	"dip_observ" Text,
constraint "oli_dip_pk" primary key ("dip_codi")
) With Oids;


Create table "oli_material_diposit"
(
	"mdi_codi" Serial NOT NULL,
	"mdi_nom" Varchar(64) NOT NULL,
	"mdi_icona" Bigint NOT NULL,
	"mdi_observ" Text,
constraint "oli_mdi_pk" primary key ("mdi_codi")
) With Oids;


Create table "oli_usugru"
(
	"ugr_codusu" Bigint NOT NULL,
	"ugr_codgru" Varchar(32) NOT NULL,
constraint "oli_ugr_pk" primary key ("ugr_codusu","ugr_codgru")
) With Oids;


Create table "oli_campanya"
(
	"cam_codi" BigSerial NOT NULL,
	"cam_nom" Varchar(256) NOT NULL,
	"cam_data" Date NOT NULL Default now(),
	"cam_observ" Text,
constraint "oli_cam_pk" primary key ("cam_codi")
) With Oids;


Create table "oli_descompo_partida_oliva"
(
	"dpo_codi" BigSerial NOT NULL,
	"dpo_codpao" Bigint NOT NULL,
	"dpo_coddpl" Bigint NOT NULL,
	"dpo_kilos" Double precision NOT NULL,
constraint "oli_dpo_pk" primary key ("dpo_codi")
) With Oids;


Create table "oli_partida_oliva"
(
	"pao_codi" Bigint NOT NULL,
	"pao_codoli" Bigint NOT NULL,
	"pao_codzon" Bigint NOT NULL,
	"pao_codtra" Bigint NOT NULL,
	"pao_codela" Bigint,
	"pao_data" Date NOT NULL,
	"pao_hora" Char(5) NOT NULL,
	"pao_nument" Integer NOT NULL,
	"pao_sana" Boolean NOT NULL Default true,
	"pao_posx" Integer NOT NULL Default 0,
	"pao_posy" Integer NOT NULL Default 0,
	"pao_observ" Text,
constraint "oli_pao_pk" primary key ("pao_codi")
) With Oids;


Create table "oli_varietat_oli"
(
	"vol_codi" Serial NOT NULL,
	"vol_nom" Varchar(128) NOT NULL,
	"vol_observ" Text,
constraint "oli_vol_pk" primary key ("vol_codi")
) With Oids;


Create table "oli_elaboracio"
(
	"ela_codi" BigSerial NOT NULL,
	"ela_codvol" Integer NOT NULL,
	"ela_codtra" Bigint NOT NULL,
	"ela_data" Date NOT NULL,
	"ela_numela" Integer NOT NULL,
	"ela_respon" Varchar(128) NOT NULL,
	"ela_acides" Double precision NOT NULL,
	"ela_temper" Double precision NOT NULL,
	"ela_talmar" Varchar(128) NOT NULL,
	"ela_taclot" Varchar(128) NOT NULL,
	"ela_talqua" Double precision NOT NULL,
	"ela_litres" Double precision NOT NULL,
	"ela_observ" Text,
constraint "oli_ela_pk" primary key ("ela_codi")
) With Oids;


Create table "oli_entrada_diposit"
(
	"edi_codi" BigSerial NOT NULL,
	"edi_codela" Bigint,
	"edi_coddip" Bigint NOT NULL,
	"edi_codest" Bigint NOT NULL,
	"edi_codtra" Bigint NOT NULL,
	"edi_codcao" Integer NOT NULL,
	"edi_data" Timestamp NOT NULL,
	"edi_litres" Double precision NOT NULL,
	"edi_acides" Double precision,
	"edi_observ" Text,
constraint "oli_edi_pk" primary key ("edi_codi")
) With Oids;


Create table "oli_sortida_diposit"
(
	"sdi_codi" BigSerial NOT NULL,
	"sdi_codest" Bigint NOT NULL,
	"sdi_coddor" Bigint NOT NULL,
	"sdi_coddde" Bigint,
	"sdi_codoli" Bigint,
	"sdi_codlot" Bigint,
	"sdi_codtra" Bigint NOT NULL,
	"sdi_codcao" Integer NOT NULL,
	"sdi_data" Timestamp,
	"sdi_litres" Double precision NOT NULL,
	"sdi_acides" Double precision,
	"sdi_desti" Varchar(128),
	"sdi_observ" Text,
constraint "oli_sdi_pk" primary key ("sdi_codi")
) With Oids;


Create table "oli_categoria_oli"
(
	"cao_codi" Integer NOT NULL,
	"cao_nom" Varchar(128) NOT NULL,
	"cao_observ" Text,
constraint "oli_cao_pk" primary key ("cao_codi")
) With Oids;


Create table "oli_analitica"
(
	"ana_codi" BigSerial NOT NULL,
	"ana_coddip" Bigint NOT NULL,
	"ana_codest" Bigint NOT NULL,
	"ana_codtra" Bigint NOT NULL,
	"ana_data" Date NOT NULL Default now(),
	"ana_sennom" Varchar(128) NOT NULL,
	"ana_sendre" Date NOT NULL,
	"ana_sendta" Date NOT NULL,
	"ana_sencvo" Integer NOT NULL,
	"ana_sencvook" Boolean NOT NULL Default false,
	"ana_senobs" Text,
	"ana_fisnom" Varchar(128) NOT NULL,
	"ana_fisdre" Date NOT NULL,
	"ana_fisdin" Date NOT NULL,
	"ana_fisdfi" Date NOT NULL,
	"ana_fisaci" Double precision NOT NULL,
	"ana_fisaciok" Boolean NOT NULL,
	"ana_fisip" Double precision NOT NULL,
	"ana_fisipok" Boolean NOT NULL Default false,
	"ana_fisk270" Double precision NOT NULL,
	"ana_fisk270ok" Boolean NOT NULL Default false,
	"ana_fisk232" Double precision NOT NULL,
	"ana_fisk232ok" Boolean NOT NULL Default false,
	"ana_fishum" Double precision NOT NULL,
	"ana_fishumok" Boolean NOT NULL Default false,
	"ana_valid" Boolean NOT NULL Default false,
	"ana_desqua" Boolean NOT NULL Default false,
	"ana_observ" Text,
constraint "oli_ana_pk" primary key ("ana_codi")
) With Oids;


Create table "oli_lot"
(
	"lot_codi" Bigint NOT NULL,
	"lot_codeti" Bigint NOT NULL,
	"lot_coddip" Bigint NOT NULL,
	"lot_codcao" Integer NOT NULL,
	"lot_codzon" Bigint NOT NULL,
	"lot_data" Date NOT NULL,
	"lot_codlot" Varchar(128) NOT NULL,
	"lot_acides" Double precision NOT NULL,
	"lot_litper" Double precision,
	"lot_motper" Text,
	"lot_numbot" Integer NOT NULL,
	"lot_litenv" Double precision NOT NULL,
	"lot_numini" Integer,
	"lot_numfin" Integer,
	"lot_posx" Integer NOT NULL Default 0,
	"lot_posy" Integer NOT NULL Default 0,
	"lot_observ" Text,
constraint "oli_lot_pk" primary key ("lot_codi")
) With Oids;


Create table "oli_entrada_lot"
(
	"elo_codi" BigSerial NOT NULL,
	"elo_codzon" Bigint NOT NULL,
	"elo_codlot" Bigint NOT NULL,
	"elo_codtra" Bigint NOT NULL,
	"elo_data" Timestamp NOT NULL,
	"elo_acides" Double precision NOT NULL,
	"elo_dippro" Varchar(64),
	"elo_numcon" Varchar(128),
constraint "oli_elo_pk" primary key ("elo_codi")
) With Oids;


Create table "oli_sortida_lot"
(
	"slo_codi" BigSerial NOT NULL,
	"slo_codlot" Bigint NOT NULL,
	"slo_codtra" Bigint NOT NULL,
	"slo_canczo" Bigint,
	"slo_accio" Char(1) NOT NULL,
	"slo_venmot" Varchar(128),
	"slo_vendat" Date,
	"slo_vendes" Varchar(128),
	"slo_ventdo" Varchar(64),
	"slo_venndo" Varchar(64),
	"slo_venobs" Text,
	"slo_candat" Date,
	"slo_canobs" Text,
constraint "oli_slo_pk" primary key ("slo_codi")
) With Oids;


Create table "oli_factura"
(
	"fac_codi" BigSerial NOT NULL,
	"fac_codoli" Bigint NOT NULL,
	"fac_numero" Integer NOT NULL,
	"fac_any" Smallint NOT NULL,
	"fac_data" Date NOT NULL,
constraint "oli_fac_pk" primary key ("fac_codi")
) With Oids;


Create table "oli_trasllat_diposit"
(
	"tdi_codi" BigSerial NOT NULL,
	"tdi_codeor" Bigint NOT NULL,
	"tdi_codede" Bigint NOT NULL,
	"tdi_codtra" Bigint NOT NULL,
	"tdi_data" Date NOT NULL,
	"tdi_acctra" Boolean,
	"tdi_retorn" Boolean NOT NULL Default false,
	"tdi_trasll" Boolean NOT NULL Default false,
constraint "oli_tdi_pk" primary key ("tdi_codi")
) With Oids;


Create table "oli_diptdi"
(
	"dtd_coddip" Bigint NOT NULL,
	"dtd_codtdi" Bigint NOT NULL,
constraint "oli_dtd_pk" primary key ("dtd_coddip","dtd_codtdi")
) With Oids;


Create table "oli_traza"
(
	"tra_codi" BigSerial NOT NULL,
	"tra_tipus" Smallint NOT NULL Check (tra_tipus >0 OR tra_tipus < 8),
constraint "oli_tra_pk" primary key ("tra_codi")
) With Oids;


Create table "oli_tratra"
(
	"ttr_codtrapare" Bigint NOT NULL,
	"ttr_codtrafill" Bigint NOT NULL,
constraint "oli_ttr_pk" primary key ("ttr_codtrapare","ttr_codtrafill")
) With Oids;


Create table "oli_categoria_informacio"
(
	"cai_codi" Serial NOT NULL,
	"cai_nom" Varchar(128) NOT NULL UNIQUE,
	"cai_descripcio" Text,
constraint "oli_cai_pk" primary key ("cai_codi")
) With Oids;


Create table "oli_informacio"
(
	"inf_codi" Serial NOT NULL,
	"inf_codcai" Integer NOT NULL,
	"inf_data" Date NOT NULL Default now(),
	"inf_titol" Varchar(128) NOT NULL,
	"inf_texte" Text,
constraint "oli_inf_pk" primary key ("inf_codi")
) With Oids;


Create table "oli_estinf"
(
	"ein_codest" Bigint NOT NULL,
	"ein_codinf" Integer NOT NULL,
constraint "oli_ein_pk" primary key ("ein_codest","ein_codinf")
) With Oids;


Create table "oli_oliinf"
(
	"oin_codoli" Bigint NOT NULL,
	"oin_codinf" Integer NOT NULL,
constraint "oli_oin_pk" primary key ("oin_codoli","oin_codinf")
) With Oids;


Create table "oli_document"
(
	"doc_codi" Serial NOT NULL,
	"doc_codinf" Integer NOT NULL,
	"doc_titol" Varchar(128) NOT NULL,
	"doc_arxiu" Bigint NOT NULL,
constraint "oli_doc_pk" primary key ("doc_codi")
) With Oids;


/* Create Tab 'Others' for Selected Tables */


/* Create Alternate Keys */
Alter Table "oli_tipus_envas" add Constraint "oli_tenmatcolvol_ak" UNIQUE ("ten_codmat","ten_codcol","ten_volum");
Alter Table "oli_establiment" add Constraint "oli_estcamnomcif_ak" UNIQUE ("est_codcam","est_nom","est_cif");
Alter Table "oli_establiment" add Constraint "oli_estcamusu_ak" UNIQUE ("est_codcam","est_codusu");
Alter Table "oli_olivicultor" add Constraint "oli_olicamnomnif_ak" UNIQUE ("oli_codcam","oli_nom","oli_nif");
Alter Table "oli_olivicultor" add Constraint "oli_olicodcamdo_ak" UNIQUE ("oli_codi","oli_codcam","oli_codido");
Alter Table "oli_olivicultor" add Constraint "oli_olicodcamdoe_ak" UNIQUE ("oli_codi","oli_codcam","oli_codide");
Alter Table "oli_descomposicio_plantacio" add Constraint "oli_dplplavov_ak" UNIQUE ("dpl_codpla","dpl_codvov");
Alter Table "oli_factura" add Constraint "oli_facoliany_ak" UNIQUE ("fac_codoli","fac_any");
Alter Table "oli_factura" add Constraint "oli_facnumany_ak" UNIQUE ("fac_numero","fac_any");


/* Create Indexes */


/* Create Foreign Keys */

Alter table "oli_establiment" add Constraint "oli_estusu_fk" foreign key ("est_codusu") references "oli_usuari" ("usu_codi") on update cascade on delete restrict;

Alter table "oli_olivicultor" add Constraint "oli_oliusu_fk" foreign key ("oli_codusu") references "oli_usuari" ("usu_codi") on update cascade on delete restrict;

Alter table "oli_usugru" add Constraint "oli_ugrusu_fk" foreign key ("ugr_codusu") references "oli_usuari" ("usu_codi") on update cascade on delete cascade;

Alter table "oli_usugru" add Constraint "oli_ugrgru_fk" foreign key ("ugr_codgru") references "oli_grup" ("gru_codi") on update cascade on delete cascade;

Alter table "oli_tipus_envas" add Constraint "oli_tencol_fk" foreign key ("ten_codcol") references "oli_color" ("col_codi") on update cascade on delete restrict;

Alter table "oli_usuari" add Constraint "oli_usuidi_fk" foreign key ("usu_codidi") references "oli_idioma" ("idi_codi") on update cascade on delete restrict;

Alter table "oli_etiquetatge" add Constraint "oli_etiten_fk" foreign key ("eti_codten") references "oli_tipus_envas" ("ten_codi") on update cascade on delete restrict;

Alter table "oli_tipus_envas" add Constraint "oli_tenmen_fk" foreign key ("ten_codmat") references "oli_material_tipus_envas" ("men_codi") on update cascade on delete restrict;

Alter table "oli_lot" add Constraint "oli_loteti_fk" foreign key ("lot_codeti") references "oli_etiquetatge" ("eti_codi") on update restrict on delete restrict;

Alter table "oli_estmar" add Constraint "oli_emamar_fk" foreign key ("ema_codmar") references "oli_marca" ("mar_codi") on update cascade on delete restrict;

Alter table "oli_etiquetatge" add Constraint "oli_etimar_fk" foreign key ("eti_codmar") references "oli_marca" ("mar_codi") on update cascade on delete restrict;

Alter table "oli_estmar" add Constraint "oli_emaest_fk" foreign key ("ema_codest") references "oli_establiment" ("est_codi") on update cascade on delete restrict;

Alter table "oli_zona" add Constraint "oli_zonest_fk" foreign key ("zon_codest") references "oli_establiment" ("est_codi") on update cascade on delete restrict;

Alter table "oli_entrada_diposit" add Constraint "oli_ediest_fk" foreign key ("edi_codest") references "oli_establiment" ("est_codi") on update cascade on delete restrict;

Alter table "oli_analitica" add Constraint "oli_anaest_fk" foreign key ("ana_codest") references "oli_establiment" ("est_codi") on update cascade on delete restrict;

Alter table "oli_sortida_diposit" add Constraint "oli_sdiest_fk" foreign key ("sdi_codest") references "oli_establiment" ("est_codi") on update cascade on delete restrict;

Alter table "oli_trasllat_diposit" add Constraint "oli_mdiest1_fk" foreign key ("tdi_codeor") references "oli_establiment" ("est_codi") on update cascade on delete restrict;

Alter table "oli_trasllat_diposit" add Constraint "oli_mdiest2_fk" foreign key ("tdi_codede") references "oli_establiment" ("est_codi") on update cascade on delete restrict;

Alter table "oli_diposit" add Constraint "oli_dipest_fk" foreign key ("dip_codest") references "oli_establiment" ("est_codi") on update cascade on delete restrict;

Alter table "oli_estinf" add Constraint "oli_einest_fk" foreign key ("ein_codest") references "oli_establiment" ("est_codi") on update cascade on delete cascade;

Alter table "oli_establiment" add Constraint "oli_esttes_fk" foreign key ("est_codtes") references "oli_tipus_establiment" ("tes_codi") on update cascade on delete restrict;

Alter table "oli_establiment" add Constraint "oli_estsol_fk" foreign key ("est_codsol") references "oli_solicitant" ("sol_codi") on update cascade on delete restrict;

Alter table "oli_finca" add Constraint "oli_finoli_fk" foreign key ("fin_codoli") references "oli_olivicultor" ("oli_codi") on update cascade on delete restrict;

Alter table "oli_partida_oliva" add Constraint "oli_paooli_fk" foreign key ("pao_codoli") references "oli_olivicultor" ("oli_codi") on update cascade on delete restrict;

Alter table "oli_sortida_diposit" add Constraint "oli_sdioli_fk" foreign key ("sdi_codoli") references "oli_olivicultor" ("oli_codi") on update cascade on delete restrict;

Alter table "oli_factura" add Constraint "oli_facoli_fk" foreign key ("fac_codoli") references "oli_olivicultor" ("oli_codi") on update cascade on delete cascade;

Alter table "oli_oliinf" add Constraint "oli_oinoli_fk" foreign key ("oin_codoli") references "oli_olivicultor" ("oli_codi") on update cascade on delete cascade;

Alter table "oli_plantacio" add Constraint "oli_plafin_fk" foreign key ("pla_codfin") references "oli_finca" ("fin_codi") on update cascade on delete restrict;

Alter table "oli_descomposicio_plantacio" add Constraint "oli_dplpla_fk" foreign key ("dpl_codpla") references "oli_plantacio" ("pla_codi") on update cascade on delete restrict;

Alter table "oli_descompo_partida_oliva" add Constraint "oli_dpodpl_fk" foreign key ("dpo_coddpl") references "oli_descomposicio_plantacio" ("dpl_codi") on update cascade on delete restrict;

Alter table "oli_descomposicio_plantacio" add Constraint "oli_dplvov_fj" foreign key ("dpl_codvov") references "oli_varietat_oliva" ("vov_codi") on update cascade on delete restrict;

Alter table "oli_diposit" add Constraint "oli_dipzon_fk" foreign key ("dip_codzon") references "oli_zona" ("zon_codi") on update cascade on delete restrict;

Alter table "oli_partida_oliva" add Constraint "oli_paozon_fk" foreign key ("pao_codzon") references "oli_zona" ("zon_codi") on update cascade on delete restrict;

Alter table "oli_entrada_lot" add Constraint "oli_elozon_fk" foreign key ("elo_codzon") references "oli_zona" ("zon_codi") on update cascade on delete restrict;

Alter table "oli_lot" add Constraint "oli_lotzon_fk" foreign key ("lot_codzon") references "oli_zona" ("zon_codi") on update cascade on delete restrict;

Alter table "oli_sortida_lot" add Constraint "oli_slozon_fk" foreign key ("slo_canczo") references "oli_zona" ("zon_codi") on update cascade on delete restrict;

Alter table "oli_entrada_diposit" add Constraint "oli_edidip_fk" foreign key ("edi_coddip") references "oli_diposit" ("dip_codi") on update cascade on delete restrict;

Alter table "oli_sortida_diposit" add Constraint "oli_sdidip2_fk" foreign key ("sdi_coddde") references "oli_diposit" ("dip_codi") on update cascade on delete restrict;

Alter table "oli_sortida_diposit" add Constraint "oli_sdidip1_fk" foreign key ("sdi_coddor") references "oli_diposit" ("dip_codi") on update cascade on delete restrict;

Alter table "oli_analitica" add Constraint "oli_anadip_fk" foreign key ("ana_coddip") references "oli_diposit" ("dip_codi") on update cascade on delete restrict;

Alter table "oli_lot" add Constraint "oli_lotdip_fk" foreign key ("lot_coddip") references "oli_diposit" ("dip_codi") on update cascade on delete restrict;

Alter table "oli_diptdi" add Constraint "oli_dtddip_fk" foreign key ("dtd_coddip") references "oli_diposit" ("dip_codi") on update cascade on delete cascade;

Alter table "oli_diposit" add Constraint "oli_dipmdi_fk" foreign key ("dip_codmdi") references "oli_material_diposit" ("mdi_codi") on update cascade on delete restrict;

Alter table "oli_olivicultor" add Constraint "oli_olicam_fk" foreign key ("oli_codcam") references "oli_campanya" ("cam_codi") on update cascade on delete restrict;

Alter table "oli_taxa" add Constraint "oli_taxcam_fk" foreign key ("tax_codcam") references "oli_campanya" ("cam_codi") on update cascade on delete restrict;

Alter table "oli_establiment" add Constraint "oli_estcam_fk" foreign key ("est_codcam") references "oli_campanya" ("cam_codi") on update cascade on delete restrict;

Alter table "oli_descompo_partida_oliva" add Constraint "oli_dpopao_fk" foreign key ("dpo_codpao") references "oli_partida_oliva" ("pao_codi") on update cascade on delete restrict;

Alter table "oli_elaboracio" add Constraint "oli_elavol_fk" foreign key ("ela_codvol") references "oli_varietat_oli" ("vol_codi") on update cascade on delete restrict;

Alter table "oli_analitica" add Constraint "oli_anavol_fk" foreign key ("ana_sencvo") references "oli_varietat_oli" ("vol_codi") on update cascade on delete restrict;

Alter table "oli_entrada_diposit" add Constraint "oli_ediela_fk" foreign key ("edi_codela") references "oli_elaboracio" ("ela_codi") on update cascade on delete restrict;

Alter table "oli_partida_oliva" add Constraint "oli_parela_fk" foreign key ("pao_codela") references "oli_elaboracio" ("ela_codi") on update cascade on delete restrict;

Alter table "oli_diposit" add Constraint "oli_dipcao_fk" foreign key ("dip_codcao") references "oli_categoria_oli" ("cao_codi") on update cascade on delete restrict;

Alter table "oli_lot" add Constraint "oli_lotcao_fk" foreign key ("lot_codcao") references "oli_categoria_oli" ("cao_codi") on update cascade on delete restrict;

Alter table "oli_entrada_diposit" add Constraint "oli_edicao_fk" foreign key ("edi_codcao") references "oli_categoria_oli" ("cao_codi") on update cascade on delete restrict;

Alter table "oli_sortida_diposit" add Constraint "oli_sdicao_fk" foreign key ("sdi_codcao") references "oli_categoria_oli" ("cao_codi") on update cascade on delete restrict;

Alter table "oli_sortida_diposit" add Constraint "oli_sdilot_fk" foreign key ("sdi_codlot") references "oli_lot" ("lot_codi") on update cascade on delete restrict;

Alter table "oli_entrada_lot" add Constraint "oli_elolot_fk" foreign key ("elo_codlot") references "oli_lot" ("lot_codi") on update cascade on delete restrict;

Alter table "oli_sortida_lot" add Constraint "oli_slolot_fk" foreign key ("slo_codlot") references "oli_lot" ("lot_codi") on update cascade on delete restrict;

Alter table "oli_diptdi" add Constraint "oli_dtdtdi_fk" foreign key ("dtd_codtdi") references "oli_trasllat_diposit" ("tdi_codi") on update cascade on delete cascade;

Alter table "oli_tratra" add Constraint "oli_ttrtra1_fk" foreign key ("ttr_codtrapare") references "oli_traza" ("tra_codi") on update cascade on delete cascade;

Alter table "oli_tratra" add Constraint "oli_ttrtra2_fk" foreign key ("ttr_codtrafill") references "oli_traza" ("tra_codi") on update cascade on delete cascade;

Alter table "oli_partida_oliva" add Constraint "oli_paotra_fk" foreign key ("pao_codtra") references "oli_traza" ("tra_codi") on update cascade on delete restrict;

Alter table "oli_elaboracio" add Constraint "oli_elatra_fk" foreign key ("ela_codtra") references "oli_traza" ("tra_codi") on update cascade on delete restrict;

Alter table "oli_entrada_diposit" add Constraint "oli_editra_fk" foreign key ("edi_codtra") references "oli_traza" ("tra_codi") on update cascade on delete restrict;

Alter table "oli_sortida_diposit" add Constraint "oli_sditra_fk" foreign key ("sdi_codtra") references "oli_traza" ("tra_codi") on update cascade on delete restrict;

Alter table "oli_analitica" add Constraint "oli_anatra_fk" foreign key ("ana_codtra") references "oli_traza" ("tra_codi") on update cascade on delete restrict;

Alter table "oli_entrada_lot" add Constraint "oli_elotra_fk" foreign key ("elo_codtra") references "oli_traza" ("tra_codi") on update cascade on delete restrict;

Alter table "oli_sortida_lot" add Constraint "oli_slotra_fk" foreign key ("slo_codtra") references "oli_traza" ("tra_codi") on update cascade on delete restrict;

Alter table "oli_trasllat_diposit" add Constraint "oli_tditra_fk" foreign key ("tdi_codtra") references "oli_traza" ("tra_codi") on update cascade on delete restrict;

Alter table "oli_informacio" add Constraint "oli_infcai_fk" foreign key ("inf_codcai") references "oli_categoria_informacio" ("cai_codi") on update cascade on delete restrict;

Alter table "oli_estinf" add Constraint "oli_eininf_fk" foreign key ("ein_codinf") references "oli_informacio" ("inf_codi") on update cascade on delete cascade;

Alter table "oli_oliinf" add Constraint "oli_oininf_fk" foreign key ("oin_codinf") references "oli_informacio" ("inf_codi") on update cascade on delete cascade;

Alter table "oli_document" add Constraint "oli_docinf_fk" foreign key ("doc_codinf") references "oli_informacio" ("inf_codi") on update cascade on delete cascade;


/* Create Procedures */


/* Create Views */


/* Create Referential Integrity Triggers */


/* Create User-Defined Triggers */


/* Create Roles */


/* Add Roles To Roles */


/* Create Role Permissions */
/* Role permissions on tables */

/* Role permissions on views */

/* Role permissions on procedures */


/* Create Comment on Tables */
Comment on table "oli_usuari" is 'Usuaris';
Comment on table "oli_grup" is 'Grups';
Comment on table "oli_taxa" is 'Taxes unitaries';
Comment on table "oli_color" is 'Colors';
Comment on table "oli_idioma" is 'Idiomes';
Comment on table "oli_tipus_envas" is 'Tipus de envas';
Comment on table "oli_arxiu" is 'Arxius binaris';
Comment on table "oli_material_tipus_envas" is 'Materials de tipus de envasos';
Comment on table "oli_etiquetatge" is 'Etiquetatges';
Comment on table "oli_marca" is 'Marques';
Comment on table "oli_establiment" is 'Establiment';
Comment on table "oli_tipus_establiment" is 'Tipus de establiments';
Comment on table "oli_solicitant" is 'Solicitant de alta';
Comment on table "oli_estmar" is 'Relacio de establiments i marques';
Comment on table "oli_olivicultor" is 'Olivicultors';
Comment on table "oli_finca" is 'Finques';
Comment on table "oli_plantacio" is 'Plantacions';
Comment on table "oli_descomposicio_plantacio" is 'Descomposicions de plantacions';
Comment on table "oli_varietat_oliva" is 'Varietats de olives';
Comment on table "oli_zona" is 'Zones';
Comment on table "oli_diposit" is 'Diposits';
Comment on table "oli_material_diposit" is 'Materials de diposits';
Comment on table "oli_usugru" is 'Relacio de usuaris i grups';
Comment on table "oli_campanya" is 'Campanyes';
Comment on table "oli_descompo_partida_oliva" is 'Descomposicions de partides de olives';
Comment on table "oli_partida_oliva" is 'Partides de olives';
Comment on table "oli_varietat_oli" is 'Varietats de oli:\r\n - verge extre\r\n - verge\r\n - llampant\r\n - etc...';
Comment on table "oli_elaboracio" is 'Elaboracions de oli';
Comment on table "oli_entrada_diposit" is 'Entrades a disposits';
Comment on table "oli_sortida_diposit" is 'Sortides de diposits';
Comment on table "oli_categoria_oli" is 'Categoria de oli:\r\n - qualificat\r\n - desqualificat\r\n - pendent de qualificació';
Comment on table "oli_analitica" is 'Analitiques';
Comment on table "oli_lot" is 'Lots';
Comment on table "oli_entrada_lot" is 'Entrades de lots';
Comment on table "oli_sortida_lot" is 'Sortides de lots';
Comment on table "oli_factura" is 'Factures';
Comment on table "oli_trasllat_diposit" is 'Trasllats de diposits';
Comment on table "oli_diptdi" is 'Relacio de diposits i trasllats de diposits';
Comment on table "oli_traza" is 'Traces de les accions';
Comment on table "oli_tratra" is 'Relacio de traces';
Comment on table "oli_categoria_informacio" is 'Categories de informacions';
Comment on table "oli_informacio" is 'Informacions';
Comment on table "oli_document" is 'Documents';


/* Create Comment on Columns */
Comment on column "oli_usuari"."usu_codi" is 'Codi de usuari';
Comment on column "oli_usuari"."usu_codidi" is 'Codi de idioma';
Comment on column "oli_usuari"."usu_actiu" is 'Actiu';
Comment on column "oli_usuari"."usu_usuari" is 'Nom de usuari';
Comment on column "oli_usuari"."usu_contra" is 'Contrasenya';
Comment on column "oli_usuari"."usu_email" is 'Email';
Comment on column "oli_usuari"."usu_observ" is 'Observacions';
Comment on column "oli_grup"."gru_codi" is 'Codi de grup';
Comment on column "oli_grup"."gru_nom" is 'Nom';
Comment on column "oli_grup"."gru_observ" is 'Observacions';
Comment on column "oli_taxa"."tax_codcam" is 'Codi de campanya';
Comment on column "oli_taxa"."tax_majigu" is 'Taxa plantació amb 75 o més anys: euros/hectarea';
Comment on column "oli_taxa"."tax_menor" is 'Taxa plantacio amb menys de 75 anys: euros/hectarea';
Comment on column "oli_taxa"."tax_volum" is 'Taxa volum a envasar: euros/litro';
Comment on column "oli_taxa"."tax_coneti" is 'Taxa contraetiqueta: euros/contraetiqueta';
Comment on column "oli_color"."col_codi" is 'Codi de color';
Comment on column "oli_color"."col_nom" is 'Nom';
Comment on column "oli_color"."col_valor" is 'Valor hexadecimal del color';
Comment on column "oli_color"."col_observ" is 'Observacions';
Comment on column "oli_idioma"."idi_codi" is 'Codi de idioma (codi ISO)';
Comment on column "oli_idioma"."idi_actiu" is 'Actiu';
Comment on column "oli_idioma"."idi_nom" is 'Nom';
Comment on column "oli_idioma"."idi_observ" is 'Observacions';
Comment on column "oli_tipus_envas"."ten_codi" is 'Codi de tipus de envas';
Comment on column "oli_tipus_envas"."ten_codmat" is 'Codi de material';
Comment on column "oli_tipus_envas"."ten_codcol" is 'Codi de color';
Comment on column "oli_tipus_envas"."ten_actiu" is 'Actiu';
Comment on column "oli_tipus_envas"."ten_volum" is 'Volum en litres';
Comment on column "oli_tipus_envas"."ten_observ" is 'Observacions';
Comment on column "oli_arxiu"."arx_codi" is 'Codi de arxiu';
Comment on column "oli_arxiu"."arx_nom" is 'Nom';
Comment on column "oli_arxiu"."arx_mime" is 'Mime';
Comment on column "oli_arxiu"."arx_tamany" is 'Tamany';
Comment on column "oli_arxiu"."arx_width" is 'Width';
Comment on column "oli_arxiu"."arx_height" is 'Height';
Comment on column "oli_arxiu"."arx_binari" is 'Contingut binari';
Comment on column "oli_material_tipus_envas"."men_codi" is 'Codi de material de envas';
Comment on column "oli_material_tipus_envas"."men_nom" is 'Nom';
Comment on column "oli_material_tipus_envas"."men_icona" is 'Codi de icona';
Comment on column "oli_material_tipus_envas"."men_observ" is 'Observacions';
Comment on column "oli_etiquetatge"."eti_codi" is 'Codi de etiquetatge';
Comment on column "oli_etiquetatge"."eti_codmar" is 'Codi de marca';
Comment on column "oli_etiquetatge"."eti_codten" is 'Codi de tipus de envas';
Comment on column "oli_etiquetatge"."eti_actiu" is 'Actiu';
Comment on column "oli_etiquetatge"."eti_imafro" is 'Codi de imatge frontal';
Comment on column "oli_etiquetatge"."eti_imapos" is 'Codi de imatge posterior';
Comment on column "oli_etiquetatge"."eti_observ" is 'Observacions';
Comment on column "oli_marca"."mar_codi" is 'Codi de marca';
Comment on column "oli_marca"."mar_actiu" is 'Actiu';
Comment on column "oli_marca"."mar_nom" is 'Nom';
Comment on column "oli_marca"."mar_denori" is 'Denominacio de origen';
Comment on column "oli_marca"."mar_observ" is 'Observacions';
Comment on column "oli_establiment"."est_codi" is 'Codi de establiment';
Comment on column "oli_establiment"."est_codcam" is 'Codi de campanya';
Comment on column "oli_establiment"."est_codtes" is 'Codi de tipus de establiment';
Comment on column "oli_establiment"."est_codusu" is 'Codi de usuari';
Comment on column "oli_establiment"."est_codsol" is 'Codi de solicitant de alta';
Comment on column "oli_establiment"."est_codori" is 'Codi de establiment original';
Comment on column "oli_establiment"."est_codirb" is 'Codi RB de tafona';
Comment on column "oli_establiment"."est_codirc" is 'Codi RC de envassadora';
Comment on column "oli_establiment"."est_actiu" is 'Actiu';
Comment on column "oli_establiment"."est_nom" is 'Nom';
Comment on column "oli_establiment"."est_cif" is 'CIF';
Comment on column "oli_establiment"."est_direcc" is 'Direccio';
Comment on column "oli_establiment"."est_poblac" is 'Poblacio';
Comment on column "oli_establiment"."est_cp" is 'Codi postal';
Comment on column "oli_establiment"."est_email" is 'Email';
Comment on column "oli_establiment"."est_telefo" is 'Telefon';
Comment on column "oli_establiment"."est_fax" is 'Fax';
Comment on column "oli_establiment"."est_numtre" is 'Numero de treballadors';
Comment on column "oli_establiment"."est_superf" is 'Superficie';
Comment on column "oli_establiment"."est_unimes" is 'Unitat de mesura (Kilos o Litros)';
Comment on column "oli_establiment"."est_tempas" is 'Temperatura maxima pasta (envasadora)';
Comment on column "oli_establiment"."est_cappro" is 'Capacitat de produccio (tafona Kg oli/h i envasadora L/h)';
Comment on column "oli_establiment"."est_envman" is 'Envassament manual (envasadora)';
Comment on column "oli_establiment"."est_etiman" is 'Etiquetat manual (envasadora)';
Comment on column "oli_establiment"."est_observ" is 'Observacions';
Comment on column "oli_tipus_establiment"."tes_codi" is 'Codi de tipus de establiment';
Comment on column "oli_tipus_establiment"."tes_nom" is 'Nom';
Comment on column "oli_solicitant"."sol_codi" is 'Codi de solicitant de alta';
Comment on column "oli_solicitant"."sol_nom" is 'Nom';
Comment on column "oli_solicitant"."sol_nif" is 'NIF';
Comment on column "oli_solicitant"."sol_perfil" is 'Perfil';
Comment on column "oli_solicitant"."sol_telefo" is 'Telefon';
Comment on column "oli_solicitant"."sol_data" is 'Data';
Comment on column "oli_estmar"."ema_codest" is 'Codi de establiment';
Comment on column "oli_estmar"."ema_codmar" is 'Codi de marca';
Comment on column "oli_olivicultor"."oli_codi" is 'Codi de olivicultor';
Comment on column "oli_olivicultor"."oli_codcam" is 'Codi de campanya';
Comment on column "oli_olivicultor"."oli_codusu" is 'Codi de usuari';
Comment on column "oli_olivicultor"."oli_codori" is 'Codi de olivicultor original';
Comment on column "oli_olivicultor"."oli_codido" is 'Codi de olivicultor a la D.O.';
Comment on column "oli_olivicultor"."oli_codide" is 'Codi de olivicultor a la D.O. experimental';
Comment on column "oli_olivicultor"."oli_nom" is 'Nom';
Comment on column "oli_olivicultor"."oli_direcc" is 'Direccio';
Comment on column "oli_olivicultor"."oli_poblac" is 'Poblacio';
Comment on column "oli_olivicultor"."oli_cp" is 'Codi postal';
Comment on column "oli_olivicultor"."oli_nif" is 'NIF';
Comment on column "oli_olivicultor"."oli_compte" is 'Compte bancari';
Comment on column "oli_olivicultor"."oli_altado" is 'Alta a la D.O.';
Comment on column "oli_olivicultor"."oli_cartil" is 'Cartilla de produccio';
Comment on column "oli_olivicultor"."oli_email" is 'Email';
Comment on column "oli_olivicultor"."oli_telefo" is 'Telefon';
Comment on column "oli_olivicultor"."oli_fax" is 'Fax';
Comment on column "oli_olivicultor"."oli_uidrfid" is 'UID de la tarjeta RFID associada';
Comment on column "oli_olivicultor"."oli_subven" is 'Subvencionat (no es genera prefactura ni factura ja que no pagara)';
Comment on column "oli_olivicultor"."oli_observ" is 'Observacions';
Comment on column "oli_finca"."fin_codi" is 'Codi de finca';
Comment on column "oli_finca"."fin_codoli" is 'Codi de olivicultor';
Comment on column "oli_finca"."fin_codori" is 'Codi de finca original';
Comment on column "oli_finca"."fin_actiu" is 'Actiu';
Comment on column "oli_finca"."fin_nom" is 'Nom';
Comment on column "oli_finca"."fin_observ" is 'Observacions';
Comment on column "oli_plantacio"."pla_codi" is 'Codi de plantacio';
Comment on column "oli_plantacio"."pla_codfin" is 'Codi de finca';
Comment on column "oli_plantacio"."pla_codori" is 'Codi de plantacio original';
Comment on column "oli_plantacio"."pla_actiu" is 'Actiu';
Comment on column "oli_plantacio"."pla_munici" is 'Municipi';
Comment on column "oli_plantacio"."pla_poligo" is 'Poligon';
Comment on column "oli_plantacio"."pla_parcel" is 'Parcel-la';
Comment on column "oli_plantacio"."pla_regadi" is 'Regadiu';
Comment on column "oli_plantacio"."pla_anypl" is 'Any de plantacio (format aaaa)';
Comment on column "oli_plantacio"."pla_observ" is 'Observacions';
Comment on column "oli_descomposicio_plantacio"."dpl_codi" is 'Codi de descomposicio de plantacio';
Comment on column "oli_descomposicio_plantacio"."dpl_codpla" is 'Codi de plantacio';
Comment on column "oli_descomposicio_plantacio"."dpl_codvov" is 'Codi de varietat de oliva';
Comment on column "oli_descomposicio_plantacio"."dpl_codori" is 'Codi de descomposicio de plantacio original';
Comment on column "oli_descomposicio_plantacio"."dpl_numoli" is 'Numero de oliveres';
Comment on column "oli_descomposicio_plantacio"."dpl_superf" is 'Superficie';
Comment on column "oli_descomposicio_plantacio"."dpl_maxprod" is 'Produccio maxima (kilos)';
Comment on column "oli_descomposicio_plantacio"."dpl_observ" is 'Observacions';
Comment on column "oli_varietat_oliva"."vov_codi" is 'Codi de varietat de olivera';
Comment on column "oli_varietat_oliva"."vov_nom" is 'Nom';
Comment on column "oli_varietat_oliva"."vov_autori" is 'Autoritzada';
Comment on column "oli_varietat_oliva"."vov_icona" is 'Codi de icona';
Comment on column "oli_varietat_oliva"."vov_color" is 'Valor hexadecimal del color';
Comment on column "oli_varietat_oliva"."vov_observ" is 'Observacions';
Comment on column "oli_zona"."zon_codi" is 'Codi de zona';
Comment on column "oli_zona"."zon_codest" is 'Codi de establiment';
Comment on column "oli_zona"."zon_codori" is 'Codi de zona original';
Comment on column "oli_zona"."zon_actiu" is 'Actiu';
Comment on column "oli_zona"."zon_nom" is 'Nom';
Comment on column "oli_zona"."zon_fictic" is 'Ficticia';
Comment on column "oli_zona"."zon_imapla" is 'Codi de imatge de planol';
Comment on column "oli_zona"."zon_defect" is 'Per defecte';
Comment on column "oli_zona"."zon_observ" is 'Observacions';
Comment on column "oli_diposit"."dip_codi" is 'Codi de diposit';
Comment on column "oli_diposit"."dip_codest" is 'Codi de establiment de creacio inicial';
Comment on column "oli_diposit"."dip_codzon" is 'Codi de zona';
Comment on column "oli_diposit"."dip_codmdi" is 'Codi de material de diposit';
Comment on column "oli_diposit"."dip_codcao" is 'Codi de categoria de oli';
Comment on column "oli_diposit"."dip_codori" is 'Codi de diposit original';
Comment on column "oli_diposit"."dip_actiu" is 'Actiu';
Comment on column "oli_diposit"."dip_codias" is 'Codi de diposit assignat';
Comment on column "oli_diposit"."dip_fictic" is 'Fictici';
Comment on column "oli_diposit"."dip_capaci" is 'Capacitat (litres)';
Comment on column "oli_diposit"."dip_posx" is 'Posicio X';
Comment on column "oli_diposit"."dip_posy" is 'Posicio Y';
Comment on column "oli_diposit"."dip_volact" is 'Volum del contingut';
Comment on column "oli_diposit"."dip_acides" is 'Acidesa del contingut';
Comment on column "oli_diposit"."dip_observ" is 'Observacions';
Comment on column "oli_material_diposit"."mdi_codi" is 'Codi de material de diposit';
Comment on column "oli_material_diposit"."mdi_nom" is 'Nom';
Comment on column "oli_material_diposit"."mdi_icona" is 'Codi de icona';
Comment on column "oli_material_diposit"."mdi_observ" is 'Observacions';
Comment on column "oli_usugru"."ugr_codusu" is 'Codi de usuari';
Comment on column "oli_usugru"."ugr_codgru" is 'Codi de grup';
Comment on column "oli_campanya"."cam_codi" is 'Codi de campanya';
Comment on column "oli_campanya"."cam_nom" is 'Nom';
Comment on column "oli_campanya"."cam_data" is 'Data';
Comment on column "oli_campanya"."cam_observ" is 'Observacions';
Comment on column "oli_descompo_partida_oliva"."dpo_codi" is 'Codi de descomposicio de partida de oliva';
Comment on column "oli_descompo_partida_oliva"."dpo_codpao" is 'Codi de partida de oliva';
Comment on column "oli_descompo_partida_oliva"."dpo_coddpl" is 'Codi de descomposicio de plantacio';
Comment on column "oli_descompo_partida_oliva"."dpo_kilos" is 'Kilos';
Comment on column "oli_partida_oliva"."pao_codi" is 'Codi de partida de oliva';
Comment on column "oli_partida_oliva"."pao_codoli" is 'Codi de olivicultor';
Comment on column "oli_partida_oliva"."pao_codzon" is 'Codi de zona';
Comment on column "oli_partida_oliva"."pao_codtra" is 'Codi de traza';
Comment on column "oli_partida_oliva"."pao_codela" is 'Codi de la elaboració, si la partida s''ha elaborat';
Comment on column "oli_partida_oliva"."pao_data" is 'Data';
Comment on column "oli_partida_oliva"."pao_hora" is 'Hora (format hh:mm)';
Comment on column "oli_partida_oliva"."pao_nument" is 'Numero de entrada diaria';
Comment on column "oli_partida_oliva"."pao_sana" is 'Oliva sana';
Comment on column "oli_partida_oliva"."pao_posx" is 'Posicio X';
Comment on column "oli_partida_oliva"."pao_posy" is 'Posicio Y';
Comment on column "oli_partida_oliva"."pao_observ" is 'Observacions';
Comment on column "oli_varietat_oli"."vol_codi" is 'Codi de varietat de oli';
Comment on column "oli_varietat_oli"."vol_nom" is 'Nom';
Comment on column "oli_varietat_oli"."vol_observ" is 'Observacions';
Comment on column "oli_elaboracio"."ela_codi" is 'Codi de elaboracio de oli';
Comment on column "oli_elaboracio"."ela_codvol" is 'Codi de varietat de oli';
Comment on column "oli_elaboracio"."ela_codtra" is 'Codi de traza';
Comment on column "oli_elaboracio"."ela_data" is 'Data';
Comment on column "oli_elaboracio"."ela_numela" is 'Numero de elaboracio diaria';
Comment on column "oli_elaboracio"."ela_respon" is 'Responsable';
Comment on column "oli_elaboracio"."ela_acides" is 'Acidesa';
Comment on column "oli_elaboracio"."ela_temper" is 'Temperatura';
Comment on column "oli_elaboracio"."ela_talmar" is 'Talc: marca comercial';
Comment on column "oli_elaboracio"."ela_taclot" is 'Talc: lot';
Comment on column "oli_elaboracio"."ela_talqua" is 'Talc: quantitat';
Comment on column "oli_elaboracio"."ela_litres" is 'Litres (totals)';
Comment on column "oli_elaboracio"."ela_observ" is 'Observacions';
Comment on column "oli_entrada_diposit"."edi_codi" is 'Codi de entrada a diposit';
Comment on column "oli_entrada_diposit"."edi_codela" is 'Codi de elaboracio de oli';
Comment on column "oli_entrada_diposit"."edi_coddip" is 'Codi de diposit';
Comment on column "oli_entrada_diposit"."edi_codest" is 'Codi de establiment';
Comment on column "oli_entrada_diposit"."edi_codtra" is 'Codi de traza';
Comment on column "oli_entrada_diposit"."edi_codcao" is 'Codi de categoria de oli';
Comment on column "oli_entrada_diposit"."edi_data" is 'Data';
Comment on column "oli_entrada_diposit"."edi_litres" is 'Litres';
Comment on column "oli_entrada_diposit"."edi_acides" is 'Acidesa';
Comment on column "oli_entrada_diposit"."edi_observ" is 'Observacions';
Comment on column "oli_sortida_diposit"."sdi_codi" is 'Codi de sortida de diposit';
Comment on column "oli_sortida_diposit"."sdi_codest" is 'Codi de establiment';
Comment on column "oli_sortida_diposit"."sdi_coddor" is 'Codi de diposit origen';
Comment on column "oli_sortida_diposit"."sdi_coddde" is 'Codi de diposit desti';
Comment on column "oli_sortida_diposit"."sdi_codoli" is 'Codi de olivicultor';
Comment on column "oli_sortida_diposit"."sdi_codlot" is 'Codi de lot';
Comment on column "oli_sortida_diposit"."sdi_codtra" is 'Codi de traza';
Comment on column "oli_sortida_diposit"."sdi_codcao" is 'Codi de categoria de oli';
Comment on column "oli_sortida_diposit"."sdi_data" is 'Data';
Comment on column "oli_sortida_diposit"."sdi_litres" is 'Litres';
Comment on column "oli_sortida_diposit"."sdi_acides" is 'Acidesa';
Comment on column "oli_sortida_diposit"."sdi_desti" is 'Desti';
Comment on column "oli_sortida_diposit"."sdi_observ" is 'Observacions';
Comment on column "oli_categoria_oli"."cao_codi" is 'Codi de categoria de oli';
Comment on column "oli_categoria_oli"."cao_nom" is 'Nom';
Comment on column "oli_categoria_oli"."cao_observ" is 'Observacions';
Comment on column "oli_analitica"."ana_codi" is 'Codi de varietat de oli';
Comment on column "oli_analitica"."ana_coddip" is 'Codi de diposit';
Comment on column "oli_analitica"."ana_codest" is 'Codi de establiment';
Comment on column "oli_analitica"."ana_codtra" is 'Codi de traza';
Comment on column "oli_analitica"."ana_data" is 'Data';
Comment on column "oli_analitica"."ana_sennom" is 'Analisi sensorial: nom del laboratori';
Comment on column "oli_analitica"."ana_sendre" is 'Analisi sensorial: data de recepcio';
Comment on column "oli_analitica"."ana_sendta" is 'Analisi sensorial: data de tast';
Comment on column "oli_analitica"."ana_sencvo" is 'Analisi sensorial: codi de varietat de oli (categoria comercial)';
Comment on column "oli_analitica"."ana_sencvook" is 'Analisi sensorial: codi de varietat de oli  (categoria comercial) valid';
Comment on column "oli_analitica"."ana_senobs" is 'Analisi sensorial: observacions';
Comment on column "oli_analitica"."ana_fisnom" is 'Analisi fisico-quimic: nom del laboratori';
Comment on column "oli_analitica"."ana_fisdre" is 'Analisi fisico-quimic: data de recepcio';
Comment on column "oli_analitica"."ana_fisdin" is 'Analisi fisico-quimic: data inici';
Comment on column "oli_analitica"."ana_fisdfi" is 'Analisi fisico-quimic: data fi';
Comment on column "oli_analitica"."ana_fisaci" is 'Analisi fisico-quimic: acidesa';
Comment on column "oli_analitica"."ana_fisaciok" is 'Analisi fisico-quimic: acidesa valida';
Comment on column "oli_analitica"."ana_fisip" is 'Analisi fisico-quimic: I.P.';
Comment on column "oli_analitica"."ana_fisipok" is 'Analisi fisico-quimic: I.P. valid';
Comment on column "oli_analitica"."ana_fisk270" is 'Analisi fisico-quimic: K270';
Comment on column "oli_analitica"."ana_fisk270ok" is 'Analisi fisico-quimic: K270 valid';
Comment on column "oli_analitica"."ana_fisk232" is 'Analisi fisico-quimic: K232';
Comment on column "oli_analitica"."ana_fisk232ok" is 'Analisi fisico-quimic: K232 valid';
Comment on column "oli_analitica"."ana_fishum" is 'Analisi fisico-quimic: humitat';
Comment on column "oli_analitica"."ana_fishumok" is 'Analisi fisico-quimic: humitat valid';
Comment on column "oli_analitica"."ana_valid" is 'Resultats del analisi valids o no';
Comment on column "oli_analitica"."ana_desqua" is 'Analitica desqualificada per desqualificacio de diposit';
Comment on column "oli_analitica"."ana_observ" is 'Observacions';
Comment on column "oli_lot"."lot_codi" is 'Codi de lot';
Comment on column "oli_lot"."lot_codeti" is 'Codi de etiquetatge';
Comment on column "oli_lot"."lot_coddip" is 'Codi de diposit';
Comment on column "oli_lot"."lot_codcao" is 'Codi de categoria de oli';
Comment on column "oli_lot"."lot_codzon" is 'Codi de zona';
Comment on column "oli_lot"."lot_data" is 'Data';
Comment on column "oli_lot"."lot_codlot" is 'Codi de lot assignat';
Comment on column "oli_lot"."lot_acides" is 'Acidesa';
Comment on column "oli_lot"."lot_litper" is 'Litres perduts';
Comment on column "oli_lot"."lot_motper" is 'Motiu de perdua';
Comment on column "oli_lot"."lot_numbot" is 'Numero de botelles';
Comment on column "oli_lot"."lot_litenv" is 'Litres envassats';
Comment on column "oli_lot"."lot_numini" is 'Numero etiqueta inicial';
Comment on column "oli_lot"."lot_numfin" is 'Numero etiqueta final';
Comment on column "oli_lot"."lot_posx" is 'Posicio X';
Comment on column "oli_lot"."lot_posy" is 'Posicio Y';
Comment on column "oli_lot"."lot_observ" is 'Observacions';
Comment on column "oli_entrada_lot"."elo_codi" is 'Codi de entrada de lot';
Comment on column "oli_entrada_lot"."elo_codzon" is 'Codi de zona';
Comment on column "oli_entrada_lot"."elo_codlot" is 'Codi de lot';
Comment on column "oli_entrada_lot"."elo_codtra" is 'Codi de traza';
Comment on column "oli_entrada_lot"."elo_data" is 'Data';
Comment on column "oli_entrada_lot"."elo_acides" is 'Acidesa';
Comment on column "oli_entrada_lot"."elo_dippro" is 'Diposit de procedencia: nomes estara informat amb el texte "ENTRADA" quan sigui una entrada per canvi de zona, no per elaboracio de lot (en aquest cas, sera el diposit del lot)';
Comment on column "oli_entrada_lot"."elo_numcon" is 'Numeros de contraetiquetes';
Comment on column "oli_sortida_lot"."slo_codi" is 'Codi de sortida de lot';
Comment on column "oli_sortida_lot"."slo_codlot" is 'Codi de lot';
Comment on column "oli_sortida_lot"."slo_codtra" is 'Codi de traza';
Comment on column "oli_sortida_lot"."slo_canczo" is 'Canvi de zona: codi de zona';
Comment on column "oli_sortida_lot"."slo_accio" is 'Accio de la sortida: v (venda) c (canvi de zona)';
Comment on column "oli_sortida_lot"."slo_venmot" is 'Venda: motiu de la sortida (promocio, venda, etc.)';
Comment on column "oli_sortida_lot"."slo_vendat" is 'Venda: data';
Comment on column "oli_sortida_lot"."slo_vendes" is 'Venda: destinatari';
Comment on column "oli_sortida_lot"."slo_ventdo" is 'Venda: tipus de document';
Comment on column "oli_sortida_lot"."slo_venndo" is 'Venda: numero de documento';
Comment on column "oli_sortida_lot"."slo_venobs" is 'Venda: observacions';
Comment on column "oli_sortida_lot"."slo_candat" is 'Canvi de zona: data';
Comment on column "oli_sortida_lot"."slo_canobs" is 'Canvi de zona: observacions';
Comment on column "oli_factura"."fac_codi" is 'Codi de factura';
Comment on column "oli_factura"."fac_codoli" is 'Codi de olivicultor';
Comment on column "oli_factura"."fac_numero" is 'Numero';
Comment on column "oli_factura"."fac_any" is 'Any';
Comment on column "oli_factura"."fac_data" is 'Data';
Comment on column "oli_trasllat_diposit"."tdi_codi" is 'Codi de trasllat de diposit';
Comment on column "oli_trasllat_diposit"."tdi_codeor" is 'Codi de establiment origen';
Comment on column "oli_trasllat_diposit"."tdi_codede" is 'Codi de establiment desti';
Comment on column "oli_trasllat_diposit"."tdi_codtra" is 'Codi de traza';
Comment on column "oli_trasllat_diposit"."tdi_data" is 'Data prevista del trasllat';
Comment on column "oli_trasllat_diposit"."tdi_acctra" is 'Trasllat amb volant de circulacio acceptat';
Comment on column "oli_trasllat_diposit"."tdi_retorn" is 'Retornat al establiment origen';
Comment on column "oli_trasllat_diposit"."tdi_trasll" is 'Indica si els diposits han estat traslladats del origen al desti';
Comment on column "oli_diptdi"."dtd_coddip" is 'Codi de diposit';
Comment on column "oli_diptdi"."dtd_codtdi" is 'Codi de trasllat de diposit';
Comment on column "oli_traza"."tra_codi" is 'Codi de traza';
Comment on column "oli_traza"."tra_tipus" is 'Tipus de traza:\r\n - partida de oliva: 1\r\n - elaboracio: 2\r\n - entrada diposit: 3\r\n - sortida diposit: 4\r\n - analitica: 5\r\n - entrada lot: 6\r\n - sortida lot: 7\r\n - trasllat diposit: 8';
Comment on column "oli_tratra"."ttr_codtrapare" is 'Codi de traza pare';
Comment on column "oli_tratra"."ttr_codtrafill" is 'Codi de traza fill';
Comment on column "oli_categoria_informacio"."cai_codi" is 'Codi de categoria de informacio';
Comment on column "oli_categoria_informacio"."cai_nom" is 'Nom';
Comment on column "oli_categoria_informacio"."cai_descripcio" is 'Descripcio';
Comment on column "oli_informacio"."inf_codi" is 'Codi de informacio';
Comment on column "oli_informacio"."inf_codcai" is 'Codi de categoria de informacio';
Comment on column "oli_informacio"."inf_data" is 'Data';
Comment on column "oli_informacio"."inf_titol" is 'Titol';
Comment on column "oli_informacio"."inf_texte" is 'Texte';
Comment on column "oli_estinf"."ein_codest" is 'Codi de establiment';
Comment on column "oli_estinf"."ein_codinf" is 'Codi de informacio';
Comment on column "oli_oliinf"."oin_codoli" is 'Codi de olivicultor';
Comment on column "oli_oliinf"."oin_codinf" is 'Codi de informacio';
Comment on column "oli_document"."doc_codi" is 'Codi de document';
Comment on column "oli_document"."doc_codinf" is 'Codi de informacio';
Comment on column "oli_document"."doc_titol" is 'Titol';
Comment on column "oli_document"."doc_arxiu" is 'Codi de arxiu';


/* Create Comment on Domains and Types */


/* Create Comment on Indexes */

-- Insert idiomes
INSERT INTO oli_idioma (idi_codi, idi_actiu, idi_nom, idi_observ) VALUES ('ca', true, 'Català', NULL);
INSERT INTO oli_idioma (idi_codi, idi_actiu, idi_nom, idi_observ) VALUES ('es', false, 'Castellano', NULL);

-- Insert grups
INSERT INTO oli_grup (gru_codi, gru_nom, gru_observ) VALUES ('OLI_ADMINISTRACIO', 'Administració', NULL);
INSERT INTO oli_grup (gru_codi, gru_nom, gru_observ) VALUES ('OLI_DOGESTOR', 'Consell Regulador - Gestor', NULL);
INSERT INTO oli_grup (gru_codi, gru_nom, gru_observ) VALUES ('OLI_DOCONTROLADOR', 'Consell Regulador - Controlador', NULL);
INSERT INTO oli_grup (gru_codi, gru_nom, gru_observ) VALUES ('OLI_OLIVICULTOR', 'Olivicultor', NULL);
INSERT INTO oli_grup (gru_codi, gru_nom, gru_observ) VALUES ('OLI_PRODUCTOR', 'Productor', NULL);
INSERT INTO oli_grup (gru_codi, gru_nom, gru_observ) VALUES ('OLI_ENVASADOR', 'Envasador', NULL);

-- Insert usuaris
INSERT INTO oli_usuari (usu_codidi, usu_actiu, usu_usuari, usu_contra, usu_observ) VALUES ('ca', true, 'gestor', md5('gestor'), NULL);

-- Insert usuaris/grups
INSERT INTO oli_usugru (ugr_codusu, ugr_codgru) VALUES (1, 'OLI_DOGESTOR');

-- Insert tipus establiment
INSERT INTO oli_tipus_establiment (tes_codi, tes_nom) VALUES (1, 'Tafona');
INSERT INTO oli_tipus_establiment (tes_codi, tes_nom) VALUES (2, 'Envasadora');
INSERT INTO oli_tipus_establiment (tes_codi, tes_nom) VALUES (3, 'Tafona / Envasadora');

-- Insert categoria oli
INSERT INTO oli_categoria_oli (cao_codi, cao_nom) VALUES (1, 'Desqualificat');
INSERT INTO oli_categoria_oli (cao_codi, cao_nom) VALUES (2, 'Pendent de qualificar');
INSERT INTO oli_categoria_oli (cao_codi, cao_nom) VALUES (3, 'Qualificat');

-- Insert varietat oli
INSERT INTO oli_varietat_oli (vol_codi, vol_nom) VALUES (1, 'Verge extra');
INSERT INTO oli_varietat_oli (vol_codi, vol_nom) VALUES (2, 'Verge');
INSERT INTO oli_varietat_oli (vol_codi, vol_nom) VALUES (3, 'Llampant');


