

--------------
--10/08/2009
--------------
ALTER TABLE "public"."oli_analitica" ADD COLUMN "ana_litresanalitzats" DOUBLE PRECISION;
UPDATE "public"."oli_analitica" SET ana_litresanalitzats = 0;
ALTER TABLE "public"."oli_analitica" ALTER COLUMN "ana_litresanalitzats" SET NOT NULL;
COMMENT ON COLUMN "public"."oli_analitica"."ana_litresanalitzats" IS 'Litres analitzats';

ALTER TABLE "public"."oli_analitica" ADD COLUMN "ana_litresdesqualificats" DOUBLE PRECISION;
COMMENT ON COLUMN "public"."oli_analitica"."ana_litresdesqualificats" IS 'Litres desqualificats';



--------------
--10/08/2009
--------------
ALTER TABLE "public"."oli_estmar" DROP CONSTRAINT "oli_emaest_fk" RESTRICT;
ALTER TABLE "public"."oli_estmar" ADD CONSTRAINT "oli_emaest_fk" FOREIGN KEY ("ema_codest") REFERENCES "public"."oli_establiment"("est_codi") ON DELETE CASCADE ON UPDATE CASCADE NOT DEFERRABLE;



--------------
--03/09/2009
--------------
ALTER TABLE "public"."oli_olivicultor" ALTER COLUMN "oli_codido" DROP NOT NULL;
UPDATE "public"."oli_grup" SET gru_nom = 'Tafona' WHERE gru_codi = 'OLI_PRODUCTOR';



--------------
--10/11/2009
--------------
-- TAREA 2: Edicio de processos
ALTER TABLE "public"."oli_partida_oliva" ADD COLUMN "pao_valid" BOOLEAN;
ALTER TABLE "public"."oli_partida_oliva" ALTER COLUMN "pao_valid" SET DEFAULT true;
UPDATE "public"."oli_partida_oliva" SET pao_valid = true;
ALTER TABLE "public"."oli_partida_oliva" ALTER COLUMN "pao_valid" SET NOT NULL;
Comment on column "oli_partida_oliva"."pao_valid" is 'Proces valid';

ALTER TABLE "public"."oli_elaboracio" ADD COLUMN "ela_valid" BOOLEAN;
ALTER TABLE "public"."oli_elaboracio" ALTER COLUMN "ela_valid" SET DEFAULT true;
UPDATE "public"."oli_elaboracio" SET ela_valid = true;
ALTER TABLE "public"."oli_elaboracio" ALTER COLUMN "ela_valid" SET NOT NULL;
Comment on column "oli_elaboracio"."ela_valid" is 'Proces valid';

ALTER TABLE "public"."oli_entrada_diposit" ADD COLUMN "edi_valid" BOOLEAN;
ALTER TABLE "public"."oli_entrada_diposit" ALTER COLUMN "edi_valid" SET DEFAULT true;
UPDATE "public"."oli_entrada_diposit" SET edi_valid = true;
ALTER TABLE "public"."oli_entrada_diposit" ALTER COLUMN "edi_valid" SET NOT NULL;
Comment on column "oli_entrada_diposit"."edi_valid" is 'Proces valid';

ALTER TABLE "public"."oli_sortida_diposit" ADD COLUMN "sdi_valid" BOOLEAN;
ALTER TABLE "public"."oli_sortida_diposit" ALTER COLUMN "sdi_valid" SET DEFAULT true;
UPDATE "public"."oli_sortida_diposit" SET sdi_valid = true;
ALTER TABLE "public"."oli_sortida_diposit" ALTER COLUMN "sdi_valid" SET NOT NULL;
Comment on column "oli_sortida_diposit"."sdi_valid" is 'Proces valid';

ALTER TABLE "public"."oli_lot" ADD COLUMN "lot_valid" BOOLEAN;
ALTER TABLE "public"."oli_lot" ALTER COLUMN "lot_valid" SET DEFAULT true;
UPDATE "public"."oli_lot" SET lot_valid = true;
ALTER TABLE "public"."oli_lot" ALTER COLUMN "lot_valid" SET NOT NULL;
Comment on column "oli_lot"."lot_valid" is 'Proces valid';

ALTER TABLE "public"."oli_entrada_lot" ADD COLUMN "elo_valid" BOOLEAN;
ALTER TABLE "public"."oli_entrada_lot" ALTER COLUMN "elo_valid" SET DEFAULT true;
UPDATE "public"."oli_entrada_lot" SET elo_valid = true;
ALTER TABLE "public"."oli_entrada_lot" ALTER COLUMN "elo_valid" SET NOT NULL;
Comment on column "oli_entrada_lot"."elo_valid" is 'Proces valid';

ALTER TABLE "public"."oli_sortida_lot" ADD COLUMN "slo_valid" BOOLEAN;
ALTER TABLE "public"."oli_sortida_lot" ALTER COLUMN "slo_valid" SET DEFAULT true;
UPDATE "public"."oli_sortida_lot" SET slo_valid = true;
ALTER TABLE "public"."oli_sortida_lot" ALTER COLUMN "slo_valid" SET NOT NULL;
Comment on column "oli_sortida_lot"."slo_valid" is 'Proces valid';

ALTER TABLE "public"."oli_trasllat_diposit" ADD COLUMN "tdi_valid" BOOLEAN;
ALTER TABLE "public"."oli_trasllat_diposit" ALTER COLUMN "tdi_valid" SET DEFAULT true;
UPDATE "public"."oli_trasllat_diposit" SET tdi_valid = true;
ALTER TABLE "public"."oli_trasllat_diposit" ALTER COLUMN "tdi_valid" SET NOT NULL;
Comment on column "oli_trasllat_diposit"."tdi_valid" is 'Proces valid';



-- TAREA 1: Tancament de llibres
ALTER TABLE "public"."oli_lot" RENAME COLUMN "lot_numbot" TO "lot_numbotini";
COMMENT ON COLUMN "public"."oli_lot"."lot_numbotini" IS 'Numero de botelles inicials';

ALTER TABLE "public"."oli_lot" ADD COLUMN "lot_numbotact" INTEGER;
UPDATE "public"."oli_lot" SET lot_numbotact = lot_numbotini;
ALTER TABLE "public"."oli_lot" ALTER COLUMN "lot_numbotact" SET NOT NULL;
COMMENT ON COLUMN "public"."oli_lot"."lot_numbotact" IS 'Numero de botelles actuals';

ALTER TABLE "public"."oli_lot" ALTER COLUMN "lot_coddip" DROP NOT NULL;

ALTER TABLE "public"."oli_lot" ADD COLUMN "lot_tancam" BOOLEAN;
ALTER TABLE "public"."oli_lot" ALTER COLUMN "lot_tancam" SET DEFAULT false;
UPDATE "public"."oli_lot" SET lot_tancam = false;
ALTER TABLE "public"."oli_lot" ALTER COLUMN "lot_tancam" SET NOT NULL;
Comment on column "oli_lot"."lot_tancam" is 'Lot creat en el tancament de llibres';



--------------
--30/11/2009
--------------
Create table "oli_lotvov"
(
	"lvo_codlot" Bigint NOT NULL,
	"lvo_codvov" Integer NOT NULL,
constraint "oli_lvo_pk" primary key ("lvo_codlot","lvo_codvov")
) With Oids;
Alter table "oli_lotvov" add Constraint "oli_lvovov_fk" foreign key ("lvo_codvov") references "oli_varietat_oliva" ("vov_codi") on update cascade on delete restrict;
Alter table "oli_lotvov" add Constraint "oli_lvolot_fk" foreign key ("lvo_codlot") references "oli_lot" ("lot_codi") on update cascade on delete restrict;
Comment on table "oli_lotvov" is 'Relacio de lots i varietats de oliva';
Comment on column "oli_lotvov"."lvo_codlot" is 'Codi de lot';
Comment on column "oli_lotvov"."lvo_codvov" is 'Codi de varietat de oliva';



--------------
--02/12/2009
--------------
ALTER TABLE "public"."oli_trasllat_diposit" ADD COLUMN "tdi_dataalta" DATE;
UPDATE "public"."oli_trasllat_diposit" SET tdi_dataalta = '2009-01-01';
ALTER TABLE "public"."oli_trasllat_diposit" ALTER COLUMN "tdi_dataalta" SET DEFAULT now();
ALTER TABLE "public"."oli_trasllat_diposit" ALTER COLUMN "tdi_dataalta" SET NOT NULL;
COMMENT ON COLUMN "public"."oli_trasllat_diposit"."tdi_dataalta" IS 'Data de alta de la accio';



--------------
--03/12/2009
--------------
ALTER TABLE "public"."oli_sortida_lot" ADD COLUMN "slo_vennbo" INTEGER;
COMMENT ON COLUMN "public"."oli_sortida_lot"."slo_vennbo" IS 'Venda: numero de botelles';



--------------
--04/12/2009
--------------
ALTER TABLE "public"."oli_sortida_diposit" ADD COLUMN "sdi_ventdo" VARCHAR(64);
COMMENT ON COLUMN "public"."oli_sortida_diposit"."sdi_ventdo" IS 'Venda: tipus de document';
ALTER TABLE "public"."oli_sortida_diposit" ADD COLUMN "sdi_venndo" VARCHAR(64);
COMMENT ON COLUMN "public"."oli_sortida_diposit"."sdi_venndo" IS 'Venda: numero de documento';



--------------
--10/12/2009
--------------
ALTER TABLE "public"."oli_traza" DROP CONSTRAINT "oli_traza_tra_tipus_check" RESTRICT;
ALTER TABLE "public"."oli_traza" ADD CONSTRAINT "oli_traza_tra_tipus_check" CHECK ((tra_tipus > 0) OR (tra_tipus < 9));
Comment on column "oli_traza"."tra_tipus" is 'Tipus de traza:\r\n - partida de oliva: 1\r\n - elaboracio: 2\r\n - entrada diposit: 3\r\n - sortida diposit: 4\r\n - analitica: 5\r\n - entrada lot: 6\r\n - sortida lot: 7\r\n - trasllat diposit: 8\r\n - venda oli: 9';

-----------------------------------------------------------------------------------------------------------
-- LIMIT
-----------------------------------------------------------------------------------------------------------

--------------
--16/07/2010
--------------
--ALTER TABLE "public"."oli_usuari" ADD COLUMN "usu_codest" bigint;
--ALTER TABLE "public"."oli_usuari" ADD CONSTRAINT "oli_usuest_fk" FOREIGN KEY ("usu_codest") REFERENCES "public"."oli_establiment" ("est_codi") on update cascade on delete restrict;
--COMMENT ON COLUMN "public"."oli_usuari"."usu_codest" IS 'Lot: nom de la partida a la que pertany';
--UPDATE "oli_usuari" SET usu_codest = est.est_codi FROM (SELECT DISTINCT oli_establiment.est_codi, oli_establiment.est_codusu FROM oli_establiment) AS est WHERE est.est_codusu = usu_codi;
CREATE
    TABLE oli_usuest
    (
        ues_codusu BIGINT NOT NULL,
        ues_codest BIGINT NOT NULL,
        PRIMARY KEY (ues_codusu, ues_codest),
        CONSTRAINT ues_codest_fk FOREIGN KEY (ues_codest) REFERENCES oli_establiment (est_codi),
        CONSTRAINT ues_codusu_fk FOREIGN KEY (ues_codusu) REFERENCES oli_usuari (usu_codi)
    );
Comment on table "oli_usuest" is 'Relacio de usuaris i establiments';
Comment on column "oli_usuest"."ues_codusu" is 'Codi usuari';
Comment on column "oli_usuest"."ues_codest" is 'Codi establiment';

INSERT INTO oli_usuest (ues_codusu, ues_codest) SELECT DISTINCT oli_establiment.est_codusu, oli_establiment.est_codi FROM oli_establiment WHERE oli_establiment.est_codusu is not null; 

ALTER TABLE "public"."oli_establiment" DROP CONSTRAINT "oli_estusu_fk";
ALTER TABLE "public"."oli_establiment" DROP COLUMN "est_codusu";


--------------
--20/07/2010
--------------
CREATE sequence "public"."oli_partida_oli_par_codi_seq" START WITH 1 increment BY 1 no maxvalue no minvalue cache 20 no cycle;

CREATE
    TABLE oli_partida_oli
    (
        par_codi BIGINT DEFAULT nextval('oli_partida_oli_par_codi_seq'::regclass) NOT NULL,
        par_nom CHARACTER VARYING(128) NOT NULL,
        par_datcre TIMESTAMP WITHOUT TIME ZONE NOT NULL,
        par_actiu BOOLEAN DEFAULT true NOT NULL,
        par_codest BIGINT NOT NULL,
        par_codcao INTEGER NOT NULL,
        CONSTRAINT oli_par_pk PRIMARY KEY (par_codi),
        CONSTRAINT par_codest_fk FOREIGN KEY (par_codest) REFERENCES oli_establiment (est_codi),
        CONSTRAINT par_codcao_fk FOREIGN KEY (par_codcao) REFERENCES oli_categoria_oli (cao_codi)
    );


--ALTER TABLE gestoli.public.oli_partida_oli ALTER COLUMN par_codi
 --   SET DEFAULT nextval('oli_partida_oli_par_codi_seq') ;
    
Comment on table "oli_partida_oli" is 'Partides de oli';
Comment on column "oli_partida_oli"."par_codi" is 'Codi partida de oli';
Comment on column "oli_partida_oli"."par_nom" is 'Nom partida de oli';
Comment on column "oli_partida_oli"."par_data" is 'Data de creació';
Comment on column "oli_partida_oli"."par_actiu" is 'Actiu';
Comment on column "oli_partida_oli"."par_codest" is 'Codi establiment';
Comment on column "oli_partida_oli"."par_codcao" is 'Codi categoria';

ALTER TABLE public.oli_entrada_diposit DROP CONSTRAINT oli_edicao_fk;
--ALTER TABLE public.oli_entrada_diposit DROP COLUMN edi_codcao;
ALTER TABLE "public"."oli_entrada_diposit" ADD COLUMN "edi_codpar" BIGINT;
COMMENT ON COLUMN "public"."oli_entrada_diposit"."edi_codpar" IS 'Partida de oli';
ALTER TABLE "public"."oli_entrada_diposit" ADD CONSTRAINT "oli_edipar_fk" FOREIGN KEY ("edi_codpar") REFERENCES "public"."oli_partida_oli"("par_codi") ON UPDATE CASCADE ON DELETE RESTRICT;

ALTER TABLE public.oli_diposit DROP CONSTRAINT oli_dipcao_fk;
ALTER TABLE public.oli_diposit DROP COLUMN dip_codcao;
ALTER TABLE "public"."oli_diposit" ADD COLUMN "dip_codpar" BIGINT;
COMMENT ON COLUMN "public"."oli_diposit"."dip_codpar" IS 'Partida de oli';
ALTER TABLE "public"."oli_diposit" ADD CONSTRAINT "oli_dippar_fk" FOREIGN KEY ("dip_codpar") REFERENCES "public"."oli_partida_oli"("par_codi") ON UPDATE CASCADE ON DELETE RESTRICT;

ALTER TABLE public.oli_lot DROP CONSTRAINT oli_lotcao_fk;
ALTER TABLE public.oli_lot DROP COLUMN lot_codcao;
ALTER TABLE "public"."oli_lot" ADD COLUMN "lot_codpar" BIGINT;
COMMENT ON COLUMN "public"."oli_lot"."lot_codpar" IS 'Partida de oli';
ALTER TABLE "public"."oli_lot" ADD CONSTRAINT "oli_lotpar_fk" FOREIGN KEY ("lot_codpar") REFERENCES "public"."oli_partida_oli"("par_codi") ON UPDATE CASCADE ON DELETE RESTRICT;

ALTER TABLE public.oli_sortida_diposit DROP CONSTRAINT oli_sdicao_fk;
--ALTER TABLE public.oli_sortida_diposit DROP COLUMN sdi_codcao;
ALTER TABLE "public"."oli_sortida_diposit" ADD COLUMN "sdi_codpar" BIGINT;
COMMENT ON COLUMN "public"."oli_sortida_diposit"."sdi_codpar" IS 'Partida de oli';
ALTER TABLE "public"."oli_sortida_diposit" ADD CONSTRAINT "oli_sdipar_fk" FOREIGN KEY ("sdi_codpar") REFERENCES "public"."oli_partida_oli"("par_codi") ON UPDATE CASCADE ON DELETE RESTRICT;

ALTER TABLE "public"."oli_elaboracio" ADD COLUMN "ela_codpar" BIGINT;
COMMENT ON COLUMN "public"."oli_elaboracio"."ela_codpar" IS 'Partida de oli';
ALTER TABLE "public"."oli_elaboracio" ADD CONSTRAINT "oli_elapar_fk" FOREIGN KEY ("ela_codpar") REFERENCES "public"."oli_partida_oli"("par_codi") ON UPDATE CASCADE ON DELETE RESTRICT;

--------------
--27/07/2010
--------------
CREATE TABLE oli_municipi(
    mun_codi BIGINT NOT NULL,
    mun_nom CHARACTER VARYING(255) NOT NULL,
    PRIMARY KEY (mun_codi)
);

commit;

insert into oli_municipi (mun_codi, mun_nom) values (54, 'Alaior');
insert into oli_municipi (mun_codi, mun_nom) values (1, 'Alaró');
insert into oli_municipi (mun_codi, mun_nom) values (2, 'Alcúdia');
insert into oli_municipi (mun_codi, mun_nom) values (3, 'Algaida');
insert into oli_municipi (mun_codi, mun_nom) values (4, 'Andratx');
insert into oli_municipi (mun_codi, mun_nom) values (5, 'Ariany');
insert into oli_municipi (mun_codi, mun_nom) values (6, 'Artà');
insert into oli_municipi (mun_codi, mun_nom) values (7, 'Banyalbufar');
insert into oli_municipi (mun_codi, mun_nom) values (8, 'Binissalem');
insert into oli_municipi (mun_codi, mun_nom) values (9, 'Búger');
insert into oli_municipi (mun_codi, mun_nom) values (10, 'Bunyola');
insert into oli_municipi (mun_codi, mun_nom) values (11, 'Calvià');
insert into oli_municipi (mun_codi, mun_nom) values (12, 'Campanet');
insert into oli_municipi (mun_codi, mun_nom) values (13, 'Campos');
insert into oli_municipi (mun_codi, mun_nom) values (14, 'Capdepera');
insert into oli_municipi (mun_codi, mun_nom) values (56, 'Ciutadella de Menorca');
insert into oli_municipi (mun_codi, mun_nom) values (15, 'Consell');
insert into oli_municipi (mun_codi, mun_nom) values (16, 'Costitx');
insert into oli_municipi (mun_codi, mun_nom) values (62, 'Eivissa');
insert into oli_municipi (mun_codi, mun_nom) values (18, 'Escorca');
insert into oli_municipi (mun_codi, mun_nom) values (19, 'Esporles');
insert into oli_municipi (mun_codi, mun_nom) values (20, 'Estellencs');
insert into oli_municipi (mun_codi, mun_nom) values (21, 'Felanitx');
insert into oli_municipi (mun_codi, mun_nom) values (57, 'Ferreries');
insert into oli_municipi (mun_codi, mun_nom) values (22, 'Fornalutx');
insert into oli_municipi (mun_codi, mun_nom) values (23, 'Inca');
insert into oli_municipi (mun_codi, mun_nom) values (24, 'Lloret de Vistalegre');
insert into oli_municipi (mun_codi, mun_nom) values (25, 'Lloseta');
insert into oli_municipi (mun_codi, mun_nom) values (26, 'Llubí');
insert into oli_municipi (mun_codi, mun_nom) values (27, 'Llucmajor');
insert into oli_municipi (mun_codi, mun_nom) values (28, 'Manacor');
insert into oli_municipi (mun_codi, mun_nom) values (29, 'Mancor de la Vall');
insert into oli_municipi (mun_codi, mun_nom) values (58, 'Maó');
insert into oli_municipi (mun_codi, mun_nom) values (30, 'Maria de la Salut');
insert into oli_municipi (mun_codi, mun_nom) values (31, 'Marratxí');
insert into oli_municipi (mun_codi, mun_nom) values (32, 'Montuïri');
insert into oli_municipi (mun_codi, mun_nom) values (33, 'Muro');
insert into oli_municipi (mun_codi, mun_nom) values (34, 'Palma');
insert into oli_municipi (mun_codi, mun_nom) values (35, 'Petra');
insert into oli_municipi (mun_codi, mun_nom) values (37, 'Pollença');
insert into oli_municipi (mun_codi, mun_nom) values (39, 'Puigpunyent');
insert into oli_municipi (mun_codi, mun_nom) values (63, 'Sant Antoni de Portmany');
insert into oli_municipi (mun_codi, mun_nom) values (41, 'Sant Joan');
insert into oli_municipi (mun_codi, mun_nom) values (65, 'Sant Josep de sa Talaia');
insert into oli_municipi (mun_codi, mun_nom) values (42, 'Sant Llorenç des Cardassar');
insert into oli_municipi (mun_codi, mun_nom) values (61, 'Sant Lluís');
insert into oli_municipi (mun_codi, mun_nom) values (43, 'Santa Eugènia');
insert into oli_municipi (mun_codi, mun_nom) values (44, 'Santa Margalida');
insert into oli_municipi (mun_codi, mun_nom) values (46, 'Santanyí');
insert into oli_municipi (mun_codi, mun_nom) values (47, 'Selva');
insert into oli_municipi (mun_codi, mun_nom) values (48, 'Sencelles');
insert into oli_municipi (mun_codi, mun_nom) values (49, 'Sineu');
insert into oli_municipi (mun_codi, mun_nom) values (50, 'Sóller');
insert into oli_municipi (mun_codi, mun_nom) values (51, 'Son Servera');
insert into oli_municipi (mun_codi, mun_nom) values (52, 'Valldemossa');
insert into oli_municipi (mun_codi, mun_nom) values (53, 'Vilafranca de Bonany');
insert into oli_municipi (mun_codi, mun_nom) values (17, 'Deià');
insert into oli_municipi (mun_codi, mun_nom) values (36, 'Sa Pobla');
insert into oli_municipi (mun_codi, mun_nom) values (38, 'Porrerres');
insert into oli_municipi (mun_codi, mun_nom) values (40, 'Ses Salines');
insert into oli_municipi (mun_codi, mun_nom) values (45, 'Santa Maria del Camí');
insert into oli_municipi (mun_codi, mun_nom) values (55, 'Es Castell');
insert into oli_municipi (mun_codi, mun_nom) values (59, 'Es Mercadal');
insert into oli_municipi (mun_codi, mun_nom) values (60, 'Es Migjorn Gran');
insert into oli_municipi (mun_codi, mun_nom) values (64, 'Sant Josep de Labritja');
insert into oli_municipi (mun_codi, mun_nom) values (66, 'Santa Eulària des Riu');
    
ALTER TABLE "public"."oli_establiment" DROP COLUMN "est_poblac";
ALTER TABLE "public"."oli_establiment" ADD COLUMN "est_poblac" BIGINT;
COMMENT ON COLUMN "public"."oli_establiment"."est_poblac" IS 'Codi de la poblacio';
ALTER TABLE "public"."oli_establiment" ADD CONSTRAINT "oli_estmun_fk" FOREIGN KEY ("est_poblac") REFERENCES "public"."oli_municipi"("mun_codi") ON UPDATE CASCADE ON DELETE RESTRICT;

ALTER TABLE "public"."oli_olivicultor" DROP COLUMN "oli_poblac";
ALTER TABLE "public"."oli_olivicultor" ADD COLUMN "oli_poblac" BIGINT;
COMMENT ON COLUMN "public"."oli_olivicultor"."oli_poblac" IS 'Codi de la poblacio';
ALTER TABLE "public"."oli_olivicultor" ADD CONSTRAINT "oli_olimun_fk" FOREIGN KEY ("oli_poblac") REFERENCES "public"."oli_municipi"("mun_codi") ON UPDATE CASCADE ON DELETE RESTRICT;

ALTER TABLE "public"."oli_plantacio" DROP COLUMN "pla_munici";
ALTER TABLE "public"."oli_plantacio" ADD COLUMN "pla_munici" BIGINT;
COMMENT ON COLUMN "public"."oli_plantacio"."pla_munici" IS 'Codi de la poblacio';
ALTER TABLE "public"."oli_plantacio" ADD CONSTRAINT "oli_plamun_fk" FOREIGN KEY ("pla_munici") REFERENCES "public"."oli_municipi"("mun_codi") ON UPDATE CASCADE ON DELETE RESTRICT;

--------------
--28/07/2010
--------------

update "public"."oli_categoria_oli" set "cao_nom" = 'No DO' where "cao_codi" = 1;
update "public"."oli_categoria_oli" set "cao_nom" = 'Pendent' where "cao_codi" = 2;
update "public"."oli_categoria_oli" set "cao_nom" = 'DO' where "cao_codi" = 3


--------------
--30/07/2010
--------------
ALTER TABLE public.oli_marca ADD COLUMN mar_datalta DATE;
ALTER TABLE public.oli_marca ADD COLUMN mar_datbaixa DATE
ALTER TABLE public.oli_etiquetatge ALTER COLUMN eti_imapos DROP NOT NULL;

--------------
--03/08/2010
--------------
ALTER TABLE public.oli_olivicultor ADD COLUMN oli_datbaixa DATE;
ALTER TABLE public.oli_finca ADD COLUMN fin_baixa BOOLEAN;
ALTER TABLE public.oli_plantacio ADD COLUMN pla_baixa BOOLEAN;
ALTER TABLE public.oli_plantacio ADD COLUMN pla_nomcomplet VARCHAR(512);
update oli_plantacio set pla_nomcomplet = (select mun_nom from oli_municipi where mun_codi = pla_munici) || ' (' || pla_poligo || ' - ' || pla_parcel || ')';

--------------
--06/08/2010
--------------
CREATE sequence "public"."oli_histolivic_oli_codi_seq" START WITH 1 increment BY 1 no maxvalue no minvalue cache 20 no cycle;
CREATE TABLE public.oli_histolivic
    (
        hol_codi bigserial(19) DEFAULT nextval('oli_histolivic_oli_codi_seq'::regclass) NOT NULL,
        hol_codoli bigint(19) NOT NULL,
        hol_codcam bigint(19) NOT NULL,
        hol_codori bigint(19),
        hol_codusu bigint(19) NOT NULL,
        hol_codido VARCHAR(128) NOT NULL,
        hol_codide VARCHAR(128) NOT NULL,
        hol_nom VARCHAR(128),
        hol_direcc VARCHAR(256),
        hol_cp VARCHAR(16),
        hol_nif VARCHAR(16),
        hol_compte VARCHAR(64),
        hol_altado BOOLEAN(1),
        hol_cartil BOOLEAN(1),
        hol_email VARCHAR(12) NOT NULL,
        hol_telefo VARCHAR(12) NOT NULL,
        hol_fax VARCHAR(12) NOT NULL,
        hol_uidrfid VARCHAR(12) NOT NULL,
        hol_subven BOOLEAN(1),
        hol_poblac bigint(19),
        hol_datbaixa DATE,
        hol_observ TEXT(2147483647),
        CONSTRAINT hol_codi_pk PRIMARY KEY (hol_codi),
        CONSTRAINT hol_codoli_fk FOREIGN KEY (hol_codoli) REFERENCES oli_olivicultor (oli_codi),
        CONSTRAINT hol_codusu_fk FOREIGN KEY (hol_codusu) REFERENCES oli_usuari (usu_codi),
        CONSTRAINT hol_codcam_fk FOREIGN KEY (hol_codcam) REFERENCES oli_campanya (cam_codi),
        CONSTRAINT hol_poblac_fk FOREIGN KEY (hol_poblac) REFERENCES oli_municipi (mun_codi)
    );
--------------
--30/07/2010
--------------

ALTER TABLE public.oli_trasllat_diposit ADD COLUMN tdi_esdip BOOLEAN;
commit;

--------------
--03/08/2010
--------------

ALTER TABLE "public"."oli_partida_oliva" ADD COLUMN "pao_estat" INT;
COMMENT ON COLUMN "public"."oli_partida_oliva"."pao_estat" IS 'Estat de la Oliva';

UPDATE oli_partida_oliva SET pao_estat = 0 where pao_sana = true;
UPDATE oli_partida_oliva SET pao_estat = 4 where pao_sana = false;

--------------
--05/08/2010
--------------

ALTER TABLE public.oli_diposit ADD COLUMN dip_voltra DOUBLE PRECISION;
commit;

--------------
--16/08/2010
--------------
ALTER TABLE public.oli_analitica ADD COLUMN ana_usuari VARCHAR(64);
commit;


UPDATE oli_entrada_diposit SET edi_litres = ROUND(CAST(edi_litres AS numeric), 2);

--------------
--17/08/2010
--------------
ALTER TABLE public.oli_diposit ADD COLUMN dip_codioriginal CHARACTER VARYING(128);

ALTER TABLE public.oli_partida_oli ADD COLUMN par_idtras bigint

--------------
--18/08/2010
--------------
ALTER TABLE public.oli_elaboracio ADD COLUMN ela_numprint INTEGER;
UPDATE public.oli_elaboracio set ela_numprint = 0;
commit;
ALTER TABLE public.oli_elaboracio ALTER COLUMN ela_numprint SET NOT NULL;
ALTER TABLE public.oli_elaboracio ADD COLUMN ela_autonum INTEGER;
UPDATE public.oli_elaboracio set ela_autonum = 0;
commit;
ALTER TABLE public.oli_elaboracio ALTER COLUMN ela_autonum SET NOT NULL;

----------------------
--25/08/2010
---------------------

CREATE TABLE public.oli_analitica_param_tipus
    (
        anapt_codi bigint NOT NULL,
        anapt_nom VARCHAR(128) NOT NULL,
        anapt_ordre INTEGER NOT NULL,
        anapt_tipus INTEGER NOT NULL,
        anapt_tipusContr INTEGER NOT NULL,
        PRIMARY KEY (anapt_codi)
    );
    
CREATE TABLE public.oli_analitica_param
(
    anap_codi bigint NOT NULL,
    anap_operador INTEGER NOT NULL,
    anap_valor VARCHAR(128) NOT NULL,
    anap_codvar bigint NOT NULL,
    anap_codpt bigint NOT NULL,
    PRIMARY KEY (anap_codi),
    CONSTRAINT fk_varietat FOREIGN KEY (anap_codvar) REFERENCES oli_varietat_oli (vol_codi),
    CONSTRAINT fk_tipusparam FOREIGN KEY (anap_codpt) REFERENCES oli_analitica_param_tipus (anapt_codi)
);

ALTER TABLE public.oli_analitica_param_tipus ADD COLUMN anapt_actiu BOOLEAN;

ALTER TABLE public.oli_analitica_param ADD COLUMN anap_obligatori BOOLEAN;

ALTER TABLE public.oli_analitica_param_tipus ADD COLUMN anapt_nomCast CHARACTER VARYING(128);
ALTER TABLE public.oli_analitica_param_tipus ALTER COLUMN anapt_nomCast SET NOT NULL;

DROP TABLE "public"."oli_analitica" CASCADE;

    
CREATE TABLE public.oli_analitica(
        ana_codi bigint NOT NULL,
        ana_data DATE NOT NULL  Default now(),
        ana_litresanalitzats DOUBLE PRECISION,
        ana_usuari Varchar(64) NOT NULL,
        ana_sencvo bigint NOT NULL,
        ana_partida bigint NOT NULL,
        ana_codtra bigint NOT NULL,
        ana_coddip bigint NOT NULL,
        ana_codest bigint NOT NULL,
        ana_sennom Varchar(128) NOT NULL,
        ana_sendre Date NOT NULL,
        ana_sendta Date NOT NULL,
        ana_senobs Text,
        ana_fisnom Varchar(128) NOT NULL,
        ana_fisdre Date NOT NULL,
        ana_fisdin Date NOT NULL,
        ana_fisdfi Date NOT NULL,
        ana_valid Boolean NOT NULL,
        ana_observ Text,
        PRIMARY KEY (ana_codi),
        CONSTRAINT fk_ana_varietat FOREIGN KEY (ana_sencvo) REFERENCES public.oli_varietat_oli (vol_codi),
        CONSTRAINT fk_ana_partida FOREIGN KEY (ana_partida) REFERENCES public.oli_partida_oli (par_codi),
        CONSTRAINT fk_ana_traza FOREIGN KEY (ana_codtra) REFERENCES public.oli_traza (tra_codi),
        CONSTRAINT fk_ana_diposit FOREIGN KEY (ana_coddip) REFERENCES public.oli_diposit (dip_codi),
        CONSTRAINT fk_ana_establiment FOREIGN KEY (ana_codest) REFERENCES public.oli_establiment (est_codi)
    );
    
CREATE TABLE public.oli_analitica_valor(
        anav_codi bigint NOT NULL,
        anav_nom Varchar(128) NOT NULL,
        anav_nomCast Varchar(128) NOT NULL,
        anav_valor Varchar(128) NOT NULL,
        anav_ordre INTEGER NOT NULL,
        anav_codana bigint NOT NULL,
        anav_tipus INTEGER NOT NULL,
        anav_tipusControl INTEGER NOT NULL,
        PRIMARY KEY (anav_codi),
        CONSTRAINT fk_ana_analitica FOREIGN KEY (anav_codana) REFERENCES public.oli_analitica (ana_codi)
    );

--------------
--26/08/2010
--------------
ALTER TABLE public.oli_lot ADD COLUMN lot_numlot VARCHAR(256);
COMMENT ON COLUMN "public"."oli_lot"."lot_numlot" IS 'Número de lot';

delete from public.oli_varietat_oliva where vov_codi = 4;    

--------------
--06/09/2010
--------------
ALTER TABLE public.oli_entrada_lot ADD COLUMN elo_observ text;
COMMENT ON COLUMN "public"."oli_entrada_lot"."elo_observ" IS 'Observacions';
ALTER TABLE public.oli_sortida_lot ADD COLUMN slo_observ text;
COMMENT ON COLUMN "public"."oli_sortida_lot"."slo_observ" IS 'Observacions';
ALTER TABLE public.oli_lot ADD COLUMN lot_datcon date;
COMMENT ON COLUMN "public"."oli_lot"."lot_datcon" IS 'Data de consum preferent';


-----------------
--07/09/2010
-----------------
ALTER TABLE public.oli_material_diposit ADD COLUMN mdi_iconaTraslladant bigint;
ALTER TABLE public.oli_material_diposit ADD COLUMN mdi_iconaPrecintat bigint;

UPDATE oli_material_diposit set mdi_iconaTraslladant = 0, mdi_iconaPrecintat = 0;
commit;

ALTER TABLE public.oli_material_diposit ALTER COLUMN mdi_iconaTraslladant SET NOT NULL;
ALTER TABLE public.oli_material_diposit ALTER COLUMN mdi_iconaPrecintat SET NOT NULL;


ALTER TABLE public.oli_diposit ADD COLUMN dip_precintat Boolean;

UPDATE oli_diposit set dip_precintat = false;
commit;

ALTER TABLE public.oli_diposit ALTER COLUMN dip_precintat SET NOT NULL;

-----------------------------
-- A PARTIR D'AQUÍ TAMBÉ PENSAR A FER-HO A GESTOLI_NET
-----------------
--08/09/2010
-----------------

ALTER TABLE public.oli_establiment ADD COLUMN est_nomResponsable Varchar(128);
ALTER TABLE public.oli_establiment ADD COLUMN est_cifResponsable Varchar(16);

UPDATE oli_establiment set est_nomResponsable = '-', est_cifResponsable = '-';
commit;

ALTER TABLE public.oli_establiment ALTER COLUMN est_nomResponsable SET NOT NULL;
ALTER TABLE public.oli_establiment ALTER COLUMN est_cifResponsable SET NOT NULL;

-----------------
--09/09/2010
-----------------

ALTER TABLE public.oli_diposit ALTER COLUMN dip_codzon DROP NOT NULL;
ALTER TABLE public.oli_diposit ALTER COLUMN dip_precintat DROP NOT NULL;


ALTER TABLE public.oli_zona ADD COLUMN zon_defectTrasllat Boolean;

UPDATE oli_zona set zon_defectTrasllat = false;

ALTER TABLE public.oli_zona ALTER COLUMN zon_defectTrasllat SET NOT NULL;

-----------------
--10/09/2010
-----------------

ALTER TABLE public.oli_diposit ADD COLUMN dip_codzonoritras bigint;


-----------------
--20/09/2010
-----------------

ALTER TABLE public.oli_etiquetes_lot ADD COLUMN elo_util Boolean;
UPDATE public.oli_etiquetes_lot set elo_util = false;
ALTER TABLE public.oli_etiquetes_lot ALTER COLUMN elo_util SET NOT NULL;

ALTER TABLE public.oli_etiquetes_lot ADD COLUMN elo_retirat Boolean;
UPDATE public.oli_etiquetes_lot set elo_retirat = false;
ALTER TABLE public.oli_etiquetes_lot ALTER COLUMN elo_retirat SET NOT NULL;

ALTER TABLE "public"."oli_etiquetes_lot" ADD COLUMN "elo_codetiorigen" BIGINT;
ALTER TABLE "public"."oli_etiquetes_lot" ADD CONSTRAINT "oli_etiOrigen_fk" FOREIGN KEY ("elo_codetiorigen") REFERENCES "public"."oli_etiquetes_lot"("elo_codi");


-----------------
--23/09/2010
-----------------

ALTER TABLE "public"."oli_etiquetes_lot" ADD COLUMN "elo_codest" BIGINT;
ALTER TABLE "public"."oli_etiquetes_lot" ADD CONSTRAINT "oli_establiment_fk" FOREIGN KEY ("elo_codest") REFERENCES "public"."oli_establiment"("est_codi");
ALTER TABLE public.oli_etiquetes_lot DROP COLUMN elo_etiqcap;


-----------------
--24/09/2010
-----------------

ALTER TABLE public.oli_etiquetes_lot ALTER COLUMN elo_codlot DROP NOT NULL;
ALTER TABLE public.oli_etiquetes_lot ALTER COLUMN elo_etiqlletra SET NOT NULL;
ALTER TABLE public.oli_etiquetes_lot ALTER COLUMN elo_etiqini SET NOT NULL;
ALTER TABLE public.oli_etiquetes_lot ALTER COLUMN elo_etiqfi SET NOT NULL;
ALTER TABLE public.oli_etiquetes_lot ALTER COLUMN elo_codest  SET NOT NULL

ALTER TABLE "public"."oli_etiquetes_lot" ADD COLUMN "elo_capacitat" DOUBLE PRECISION;
ALTER TABLE public.oli_etiquetes_lot ALTER COLUMN elo_capacitat  SET NOT NULL;


-----------------
--30/09/2010
-----------------
ALTER TABLE public.oli_etiquetes_lot DROP COLUMN elo_etiqini;
ALTER TABLE public.oli_etiquetes_lot DROP COLUMN elo_etiqfi;
ALTER TABLE public.oli_etiquetes_lot ADD COLUMN elo_etiqini INTEGER;
ALTER TABLE public.oli_etiquetes_lot ALTER COLUMN elo_etiqini SET NOT NULL;
ALTER TABLE public.oli_etiquetes_lot ADD COLUMN elo_etiqfi INTEGER;
ALTER TABLE public.oli_etiquetes_lot ALTER COLUMN elo_etiqfi SET NOT NULL;

-----------------
--06/10/2010
-----------------
ALTER TABLE "public"."oli_entrada_diposit" ADD COLUMN "edi_codoli" BIGINT;
ALTER TABLE "public"."oli_entrada_diposit" ADD CONSTRAINT "oli_olivicultor_fk" FOREIGN KEY ("edi_codoli") REFERENCES "public"."oli_olivicultor"("oli_codi");
    

-----------------
--14/10/2010
-----------------
ALTER TABLE "public"."oli_partida_oli" ADD COLUMN "par_olipro" BOOLEAN;
COMMENT ON COLUMN "public"."oli_partida_oli"."par_olipro" IS 'L''livicultor es el propietari de l''oli';
UPDATE "public"."oli_partida_oli" SET "par_olipro" = true;
ALTER TABLE "public"."oli_partida_oli" ALTER COLUMN "par_olipro" SET DEFAULT true;

-----------------
--04/11/2010
-----------------
ALTER TABLE "public"."oli_entrada_lot" ADD COLUMN "elo_esdevo" BOOLEAN;
COMMENT ON COLUMN "public"."oli_entrada_lot"."elo_esdevo" IS 'Devolucio';
ALTER TABLE "public"."oli_entrada_lot" ADD COLUMN "elo_botell" INTEGER;
COMMENT ON COLUMN "public"."oli_entrada_lot"."elo_botell" IS 'Numero de botelles';

ALTER TABLE public.oli_partida_oli ADD COLUMN par_codcoo BIGINT;
UPDATE public.oli_partida_oli set par_codcoo = par_codcao;
COMMENT ON COLUMN "public"."oli_partida_oli"."par_codcoo" IS 'Categoria oli original';

ALTER TABLE "public"."oli_partida_oli" ADD CONSTRAINT "oli_parcoo_fk" FOREIGN KEY ("par_codcoo") REFERENCES "public"."oli_categoria_oli"("cao_codi");

-----------------
--09/11/2010
-----------------
ALTER TABLE oli_partida_oli DROP COLUMN par_idtras;
ALTER TABLE "public"."oli_trasllat_diposit" ADD COLUMN "tdi_idpart" BIGINT;

ALTER TABLE public.oli_entrada_diposit ADD COLUMN edi_codcaopar BIGINT;
COMMENT ON COLUMN "public"."oli_entrada_diposit"."edi_codcaopar" IS 'Codi de categoria de oli que tenia originalment la partida';

ALTER TABLE "public"."oli_entrada_diposit" ADD CONSTRAINT "oli_edicao_fk" FOREIGN KEY ("edi_codcaopar") REFERENCES "public"."oli_categoria_oli"("cao_codi");

-----------------
--21/11/2010
-----------------
ALTER TABLE "public"."oli_partida_oli" ADD COLUMN "par_esvisu" BOOLEAN;
COMMENT ON COLUMN "public"."oli_partida_oli"."par_esvisu" IS 'Es visualitza la partida';
UPDATE "public"."oli_partida_oli" SET "par_esvisu" = true;
ALTER TABLE "public"."oli_partida_oli" ALTER COLUMN "par_esvisu" SET DEFAULT true;

update  oli_partida_oli 
set     par_esvisu = false 
where   par_codi in 
    (
        select  distinct ed.edi_codpar
        from    oli_entrada_diposit ed,
                oli_diposit d
        where   ed.edi_coddip = d.dip_codi
        and     d.dip_fictic = true
        and     ed.edi_codi not in 
                (select  edi.edi_codi 
                from    oli_entrada_diposit edi,
                        oli_diposit di
                where   edi.edi_coddip = di.dip_codi
                and     di.dip_fictic = false)
    );
    
-----------------
--22/11/2010
-----------------
ALTER TABLE public.oli_analitica ADD COLUMN ana_var1 BIGINT;
UPDATE public.oli_analitica set ana_var1 = 1;
ALTER TABLE public.oli_analitica ALTER COLUMN ana_var1 SET NOT NULL;

ALTER TABLE public.oli_analitica ADD COLUMN ana_var2 BIGINT;
UPDATE public.oli_analitica set ana_var2 = 1;
ALTER TABLE public.oli_analitica ALTER COLUMN ana_var2 SET NOT NULL;

-----------------
--03/12/2010
-----------------
ALTER TABLE public.oli_elaboracio ADD COLUMN ela_codcao BIGINT;
COMMENT ON COLUMN "public"."oli_elaboracio"."ela_codcao" IS 'Codi de categoria de oli';
UPDATE public.oli_elaboracio set ela_codcao = ela_codvol;
ALTER TABLE public.oli_elaboracio ALTER COLUMN ela_codcao SET NOT NULL;
ALTER TABLE "public"."oli_elaboracio" ADD CONSTRAINT "oli_categoria_fk" FOREIGN KEY ("ela_codcao") REFERENCES "public"."oli_categoria_oli"("cao_codi");
ALTER TABLE public.oli_elaboracio DROP COLUMN ela_codvol;

-----------------
--09/12/2010
-----------------
insert into oli_grup (gru_codi, gru_nom, gru_observ) values ('OLI_ESTADMINISTRADOR', 'Administrador de l''establiment', null);
insert into oli_grup (gru_codi, gru_nom, gru_observ) values ('OLI_ESTENCARREGAT', 'Encarregat de l''establiment', null);
insert into oli_grup (gru_codi, gru_nom, gru_observ) values ('OLI_ESTTREBALLADOR', 'Treballador de l''establiment', null);
insert into oli_grup (gru_codi, gru_nom, gru_observ) values ('OLI_ESTCONSULTA', 'Consultor de l''establiment', null);

insert into oli_usugru (ugr_codusu, ugr_codgru)
 select distinct ugr_codusu, 'OLI_ESTADMINISTRADOR'
   from oli_usugru
   where ugr_codgru='OLI_ENVASADOR' or ugr_codgru='OLI_PRODUCTOR';

-----------------
--10/01/2011
-----------------
ALTER TABLE public.oli_plantacio ADD COLUMN pla_coordx CHARACTER VARYING(32);
COMMENT ON COLUMN "public"."oli_plantacio"."pla_coordx" IS 'Coordenada X';
ALTER TABLE public.oli_plantacio ADD COLUMN pla_coordy CHARACTER VARYING(32);
COMMENT ON COLUMN "public"."oli_plantacio"."pla_coordy" IS 'Coordenada Y';
ALTER TABLE public.oli_plantacio ADD COLUMN pla_catast CHARACTER VARYING(32);
COMMENT ON COLUMN "public"."oli_plantacio"."pla_catast" IS 'Catastre';

update "public"."oli_plantacio" set "pla_coordx" = '2.98237320959032', "pla_coordy" = '39.855181568051', "pla_catast" = '07042A00800009' where "pla_codi" = 17;
update "public"."oli_plantacio" set "pla_coordx" = '2.7811209377574', "pla_coordy" = '39.6363806304797', "pla_catast" = '07056A00500352' where "pla_codi" = 18;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 8;
update "public"."oli_plantacio" set "pla_coordx" = '2.62816582852473', "pla_coordy" = '39.7425322088409', "pla_catast" = '07018A00200383' where "pla_codi" = 3;
update "public"."oli_plantacio" set "pla_coordx" = '2.72235613278315', "pla_coordy" = '39.7569839615128', "pla_catast" = '07061A00401895' where "pla_codi" = 4;
update "public"."oli_plantacio" set "pla_coordx" = '2.79140443884881', "pla_coordy" = '39.6387634482136', "pla_catast" = '07056A00500010' where "pla_codi" = 6;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 23;
update "public"."oli_plantacio" set "pla_coordx" = '2.92693329351969', "pla_coordy" = '39.6645496329346', "pla_catast" = '07047A00200077' where "pla_codi" = 7;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 26;
update "public"."oli_plantacio" set "pla_coordx" = '2.7221604905209', "pla_coordy" = '39.7877786966425', "pla_catast" = '07061A00300198' where "pla_codi" = 11;
update "public"."oli_plantacio" set "pla_coordx" = '2.67166863833966', "pla_coordy" = '39.7740031946131', "pla_catast" = '07061A00100450' where "pla_codi" = 12;
update "public"."oli_plantacio" set "pla_coordx" = '2.78034920264808', "pla_coordy" = '39.6313435546046', "pla_catast" = '07036A00100568' where "pla_codi" = 14;
update "public"."oli_plantacio" set "pla_coordx" = '2.9902884617861', "pla_coordy" = '39.8510370087552', "pla_catast" = '07042A00800025' where "pla_codi" = 16;
update "public"."oli_plantacio" set "pla_coordx" = '2.7811209377574', "pla_coordy" = '39.6363806304797', "pla_catast" = '07056A00500352' where "pla_codi" = 20;
update "public"."oli_plantacio" set "pla_coordx" = '2.84168358393731', "pla_coordy" = '39.6388957594003', "pla_catast" = '07053A00200436' where "pla_codi" = 21;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 47;
update "public"."oli_plantacio" set "pla_coordx" = '2.71239304355172', "pla_coordy" = '39.8141170730108', "pla_catast" = '07061A00300290' where "pla_codi" = 22;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 53;
update "public"."oli_plantacio" set "pla_coordx" = '3.07217290924472', "pla_coordy" = '39.3682711867463', "pla_catast" = '07059A00600224' where "pla_codi" = 25;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 57;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 58;
update "public"."oli_plantacio" set "pla_coordx" = '2.66660147196776', "pla_coordy" = '39.7738599063117', "pla_catast" = '07061A00100464' where "pla_codi" = 29;
update "public"."oli_plantacio" set "pla_coordx" = '2.69978427684341', "pla_coordy" = '39.7215081336866', "pla_catast" = '07010A00700198' where "pla_codi" = 30;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 66;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 67;
update "public"."oli_plantacio" set "pla_coordx" = '2.6918441601922', "pla_coordy" = '39.7216400843252', "pla_catast" = '07010A00700199' where "pla_codi" = 31;
update "public"."oli_plantacio" set "pla_coordx" = '2.6918441601922', "pla_coordy" = '39.7216400843252', "pla_catast" = '07010A00700199' where "pla_codi" = 33;
update "public"."oli_plantacio" set "pla_coordx" = '3.06681191952137', "pla_coordy" = '39.7216887066268', "pla_catast" = '07039A00600038' where "pla_codi" = 35;
update "public"."oli_plantacio" set "pla_coordx" = '2.73845694025048', "pla_coordy" = '39.8126886740925', "pla_catast" = '07025A00100073' where "pla_codi" = 37;
update "public"."oli_plantacio" set "pla_coordx" = '2.73750659446806', "pla_coordy" = '39.8205790573239', "pla_catast" = '07025A00100074' where "pla_codi" = 38;
update "public"."oli_plantacio" set "pla_coordx" = '2.74436232927212', "pla_coordy" = '39.8120238502178', "pla_catast" = '07025A00100952' where "pla_codi" = 40;
update "public"."oli_plantacio" set "pla_coordx" = '2.74076103259514', "pla_coordy" = '39.8089960353839', "pla_catast" = '07025A00100953' where "pla_codi" = 41;
update "public"."oli_plantacio" set "pla_coordx" = '2.73889565803143', "pla_coordy" = '39.8123444885632', "pla_catast" = '07025A00100954' where "pla_codi" = 42;
update "public"."oli_plantacio" set "pla_coordx" = '3.03269255394341', "pla_coordy" = '39.742864971843', "pla_catast" = '07044A01500166' where "pla_codi" = 45;
update "public"."oli_plantacio" set "pla_coordx" = '2.87822532803209', "pla_coordy" = '39.6688554657572', "pla_catast" = '07008A01200119' where "pla_codi" = 48;
update "public"."oli_plantacio" set "pla_coordx" = '2.87822532803209', "pla_coordy" = '39.6688554657572', "pla_catast" = '07008A01200119' where "pla_codi" = 49;
update "public"."oli_plantacio" set "pla_coordx" = '2.87844382210586', "pla_coordy" = '39.6703276669208', "pla_catast" = '07008A01200185' where "pla_codi" = 51;
update "public"."oli_plantacio" set "pla_coordx" = '2.85597485846635', "pla_coordy" = '39.7441094431978', "pla_catast" = '07034A00300052' where "pla_codi" = 52;
update "public"."oli_plantacio" set "pla_coordx" = '2.69030995913071', "pla_coordy" = '39.7111450825428', "pla_catast" = '07010A00700167' where "pla_codi" = 54;
update "public"."oli_plantacio" set "pla_coordx" = '2.69111306177509', "pla_coordy" = '39.7142046220861', "pla_catast" = '07010A00700169' where "pla_codi" = 56;
update "public"."oli_plantacio" set "pla_coordx" = '2.89865561066762', "pla_coordy" = '39.6900274716844', "pla_catast" = '07027A00100002' where "pla_codi" = 59;
update "public"."oli_plantacio" set "pla_coordx" = '2.78249313248591', "pla_coordy" = '39.699936488533', "pla_catast" = '07001A00100286' where "pla_codi" = 61;
update "public"."oli_plantacio" set "pla_coordx" = '2.68353001176433', "pla_coordy" = '39.7709191113561', "pla_catast" = '07061A00500448' where "pla_codi" = 62;
update "public"."oli_plantacio" set "pla_coordx" = '2.75441697909906', "pla_coordy" = '39.781843786917', "pla_catast" = '07025A00100687' where "pla_codi" = 65;
update "public"."oli_plantacio" set "pla_coordx" = '2.857657070957', "pla_coordy" = '39.682263575746', "pla_catast" = '07008A00100131' where "pla_codi" = 68;
update "public"."oli_plantacio" set "pla_coordx" = '2.72972772136401', "pla_coordy" = '39.7911025783851', "pla_catast" = '07025A00100038' where "pla_codi" = 70;
update "public"."oli_plantacio" set "pla_coordx" = '2.75287441345077', "pla_coordy" = '39.7816204776818', "pla_catast" = '07025A00100688' where "pla_codi" = 71;
update "public"."oli_plantacio" set "pla_coordx" = '2.68527054442456', "pla_coordy" = '39.7589857641513', "pla_catast" = '07061A00500393' where "pla_codi" = 73;
update "public"."oli_plantacio" set "pla_coordx" = '2.68440017267968', "pla_coordy" = '39.7591043175715', "pla_catast" = '07061A00500394' where "pla_codi" = 74;
update "public"."oli_plantacio" set "pla_coordx" = '2.91714596291068', "pla_coordy" = '39.6128569346692', "pla_catast" = '07047A01700094' where "pla_codi" = 1052;
update "public"."oli_plantacio" set "pla_coordx" = '2.71477395968289', "pla_coordy" = '39.7953281372153', "pla_catast" = '07061A00300375' where "pla_codi" = 103;
update "public"."oli_plantacio" set "pla_coordx" = '2.74991507317553', "pla_coordy" = '39.7863148028948', "pla_catast" = '07025A00100269' where "pla_codi" = 109;
update "public"."oli_plantacio" set "pla_coordx" = '3.29358142716253', "pla_coordy" = '39.7380827444532', "pla_catast" = '07006A00500017' where "pla_codi" = 79;
update "public"."oli_plantacio" set "pla_coordx" = '2.70579353030727', "pla_coordy" = '39.7940605810619', "pla_catast" = '07061A00200674' where "pla_codi" = 82;
update "public"."oli_plantacio" set "pla_coordx" = '2.7096745936857', "pla_coordy" = '39.7945802481724', "pla_catast" = '07061A00200574' where "pla_codi" = 84;
update "public"."oli_plantacio" set "pla_coordx" = '3.07111230789845', "pla_coordy" = '39.8450047508623', "pla_catast" = '07003A00600043' where "pla_codi" = 85;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 96;
update "public"."oli_plantacio" set "pla_coordx" = '2.84610069183111', "pla_coordy" = '39.6566243192737', "pla_catast" = '07047A00900062' where "pla_codi" = 88;
update "public"."oli_plantacio" set "pla_coordx" = '2.85706481103488', "pla_coordy" = '39.6676397358675', "pla_catast" = '07008A00300058' where "pla_codi" = 89;
update "public"."oli_plantacio" set "pla_coordx" = '2.74102519118221', "pla_coordy" = '39.7781851699424', "pla_catast" = '07025A00100550' where "pla_codi" = 91;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 111;
update "public"."oli_plantacio" set "pla_coordx" = '2.62964856002832', "pla_coordy" = '39.7090754472083', "pla_catast" = '07063A00300114' where "pla_codi" = 92;
update "public"."oli_plantacio" set "pla_coordx" = '2.63404248868273', "pla_coordy" = '39.7093230887934', "pla_catast" = '07063A00300115' where "pla_codi" = 93;
update "public"."oli_plantacio" set "pla_coordx" = '2.63882924501781', "pla_coordy" = '39.7508515729948', "pla_catast" = '07018A00200233' where "pla_codi" = 95;
update "public"."oli_plantacio" set "pla_coordx" = '2.60475978768741', "pla_coordy" = '39.7196696214454', "pla_catast" = '07063A00100058' where "pla_codi" = 99;
update "public"."oli_plantacio" set "pla_coordx" = '2.60657399332084', "pla_coordy" = '39.7191279556365', "pla_catast" = '07063A00100059' where "pla_codi" = 100;
update "public"."oli_plantacio" set "pla_coordx" = '3.04406224689202', "pla_coordy" = '39.8908511840817', "pla_catast" = '07042A01000168' where "pla_codi" = 102;
update "public"."oli_plantacio" set "pla_coordx" = '2.70736377231552', "pla_coordy" = '39.7964365483206', "pla_catast" = '07061A00200526' where "pla_codi" = 105;
update "public"."oli_plantacio" set "pla_coordx" = '2.70714280866643', "pla_coordy" = '39.7968432458916', "pla_catast" = '07061A00200527' where "pla_codi" = 106;
update "public"."oli_plantacio" set "pla_coordx" = '3.06598666310834', "pla_coordy" = '39.8795094690946', "pla_catast" = '07042A00300686' where "pla_codi" = 107;
update "public"."oli_plantacio" set "pla_coordx" = '2.94540750896608', "pla_coordy" = '39.4102859727018', "pla_catast" = '07013A00100121' where "pla_codi" = 110;
update "public"."oli_plantacio" set "pla_coordx" = '2.85793648697354', "pla_coordy" = '39.6824856610684', "pla_catast" = '07008A00100129' where "pla_codi" = 114;
update "public"."oli_plantacio" set "pla_coordx" = '3.00185716276405', "pla_coordy" = '39.7441433726022', "pla_catast" = '07044A01500358' where "pla_codi" = 116;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 149;
update "public"."oli_plantacio" set "pla_coordx" = '3.00111569884443', "pla_coordy" = '39.745202168851', "pla_catast" = '07044A00800339' where "pla_codi" = 117;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 153;
update "public"."oli_plantacio" set "pla_coordx" = '2.74222975647579', "pla_coordy" = '39.7673855066842', "pla_catast" = '07061A00401644' where "pla_codi" = 119;
update "public"."oli_plantacio" set "pla_coordx" = '2.74204599894975', "pla_coordy" = '39.7662847789011', "pla_catast" = '07061A00401648' where "pla_codi" = 120;
update "public"."oli_plantacio" set "pla_coordx" = '2.74215580055019', "pla_coordy" = '39.7668973527022', "pla_catast" = '07061A00401652' where "pla_codi" = 121;
update "public"."oli_plantacio" set "pla_coordx" = '2.72367572815399', "pla_coordy" = '39.7914593363873', "pla_catast" = '07061A00300174' where "pla_codi" = 123;
update "public"."oli_plantacio" set "pla_coordx" = '2.73512663023725', "pla_coordy" = '39.7877628406189', "pla_catast" = '07025A00100344' where "pla_codi" = 124;
update "public"."oli_plantacio" set "pla_coordx" = '3.1078514206383', "pla_coordy" = '39.843852439709', "pla_catast" = '07003A00400231' where "pla_codi" = 126;
update "public"."oli_plantacio" set "pla_coordx" = '2.71910290682564', "pla_coordy" = '39.7998668193398', "pla_catast" = '07061A00300355' where "pla_codi" = 128;
update "public"."oli_plantacio" set "pla_coordx" = '2.75169959499394', "pla_coordy" = '39.7793465073116', "pla_catast" = '07025A00100141' where "pla_codi" = 130;
update "public"."oli_plantacio" set "pla_coordx" = '2.91291969880662', "pla_coordy" = '39.7632289696966', "pla_catast" = '07058A01800040' where "pla_codi" = 131;
update "public"."oli_plantacio" set "pla_coordx" = '2.70654468852947', "pla_coordy" = '39.7606674293613', "pla_catast" = '07061A00500320' where "pla_codi" = 133;
update "public"."oli_plantacio" set "pla_coordx" = '2.89462141887144', "pla_coordy" = '39.7080294091746', "pla_catast" = '07027A01000273' where "pla_codi" = 134;
update "public"."oli_plantacio" set "pla_coordx" = '2.40432471972651', "pla_coordy" = '39.5732375688596', "pla_catast" = '07005A01600103' where "pla_codi" = 136;
update "public"."oli_plantacio" set "pla_coordx" = '2.40401693771075', "pla_coordy" = '39.5729044103078', "pla_catast" = '07005A01600105' where "pla_codi" = 137;
update "public"."oli_plantacio" set "pla_coordx" = '2.40369526288573', "pla_coordy" = '39.5723176277214', "pla_catast" = '07005A01600093' where "pla_codi" = 139;
update "public"."oli_plantacio" set "pla_coordx" = '2.40324549397792', "pla_coordy" = '39.5728215237473', "pla_catast" = '07005A01600095' where "pla_codi" = 140;
update "public"."oli_plantacio" set "pla_coordx" = '2.75525555446582', "pla_coordy" = '39.7783489020928', "pla_catast" = '07025A00100925' where "pla_codi" = 143;
update "public"."oli_plantacio" set "pla_coordx" = '2.60665664829971', "pla_coordy" = '39.7245878186571', "pla_catast" = '07063A00300005' where "pla_codi" = 144;
update "public"."oli_plantacio" set "pla_coordx" = '2.68891111887987', "pla_coordy" = '39.76632869866', "pla_catast" = '07061A00500418' where "pla_codi" = 146;
update "public"."oli_plantacio" set "pla_coordx" = '2.51956137527119', "pla_coordy" = '39.5592796993005', "pla_catast" = '07011A01600225' where "pla_codi" = 147;
update "public"."oli_plantacio" set "pla_coordx" = '2.70367486132347', "pla_coordy" = '39.6818944645982', "pla_catast" = '07010A00200108' where "pla_codi" = 150;
update "public"."oli_plantacio" set "pla_coordx" = '2.71981231812803', "pla_coordy" = '39.7949014605971', "pla_catast" = '07061A00300363' where "pla_codi" = 151;
update "public"."oli_plantacio" set "pla_coordx" = '3.06229579833227', "pla_coordy" = '39.7155555980691', "pla_catast" = '07039A00501082' where "pla_codi" = 1056;
update "public"."oli_plantacio" set "pla_coordx" = '2.67981382984515', "pla_coordy" = '39.768936978421', "pla_catast" = '07061A00500519' where "pla_codi" = 200;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 155;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 156;
update "public"."oli_plantacio" set "pla_coordx" = '2.73655988599701', "pla_coordy" = '39.7810418606178', "pla_catast" = '07025A00100307' where "pla_codi" = 158;
update "public"."oli_plantacio" set "pla_coordx" = '2.86182250255071', "pla_coordy" = '39.7489571095843', "pla_catast" = '07034A00300042' where "pla_codi" = 161;
update "public"."oli_plantacio" set "pla_coordx" = '2.89791297756', "pla_coordy" = '39.7634551017555', "pla_catast" = '07058A02100142' where "pla_codi" = 162;
update "public"."oli_plantacio" set "pla_coordx" = '3.13269902966506', "pla_coordy" = '39.6523214650961', "pla_catast" = '07066A00500357' where "pla_codi" = 163;
update "public"."oli_plantacio" set "pla_coordx" = '2.69168159489772', "pla_coordy" = '39.7700635574972', "pla_catast" = '07061A00500381' where "pla_codi" = 165;
update "public"."oli_plantacio" set "pla_coordx" = '2.60572011657137', "pla_coordy" = '39.7194056399475', "pla_catast" = '07063A00100060' where "pla_codi" = 166;
update "public"."oli_plantacio" set "pla_coordx" = '2.70748718819598', "pla_coordy" = '39.8006160043478', "pla_catast" = '07061A00200511' where "pla_codi" = 168;
update "public"."oli_plantacio" set "pla_coordx" = '2.70726773486488', "pla_coordy" = '39.8022627617741', "pla_catast" = '07061A00300319' where "pla_codi" = 169;
update "public"."oli_plantacio" set "pla_coordx" = '2.68782277181728', "pla_coordy" = '39.7631150361017', "pla_catast" = '07061A00500402' where "pla_codi" = 171;
update "public"."oli_plantacio" set "pla_coordx" = '2.68926492805732', "pla_coordy" = '39.762339802379', "pla_catast" = '07061A00500403' where "pla_codi" = 172;
update "public"."oli_plantacio" set "pla_coordx" = '2.68584543694969', "pla_coordy" = '39.7640639607454', "pla_catast" = '07061A00500405' where "pla_codi" = 173;
update "public"."oli_plantacio" set "pla_coordx" = '2.69081310217743', "pla_coordy" = '39.7650296602845', "pla_catast" = '07061A00500411' where "pla_codi" = 176;
update "public"."oli_plantacio" set "pla_coordx" = '2.68524821717985', "pla_coordy" = '39.7648449586019', "pla_catast" = '07061A00500437' where "pla_codi" = 178;
update "public"."oli_plantacio" set "pla_coordx" = '2.90852412089862', "pla_coordy" = '39.7904807788586', "pla_catast" = '07058A01300070' where "pla_codi" = 179;
update "public"."oli_plantacio" set "pla_coordx" = '2.75625217943783', "pla_coordy" = '39.7786258130936', "pla_catast" = '07025A00100129' where "pla_codi" = 181;
update "public"."oli_plantacio" set "pla_coordx" = '2.84239179254509', "pla_coordy" = '39.6573590027393', "pla_catast" = '07016A00200136' where "pla_codi" = 182;
update "public"."oli_plantacio" set "pla_coordx" = '2.90173703615177', "pla_coordy" = '39.6907157314403', "pla_catast" = '07027A00200365' where "pla_codi" = 183;
update "public"."oli_plantacio" set "pla_coordx" = '2.90238749915509', "pla_coordy" = '39.6886758964575', "pla_catast" = '07027A00200376' where "pla_codi" = 185;
update "public"."oli_plantacio" set "pla_coordx" = '2.90222176090826', "pla_coordy" = '39.6892741053354', "pla_catast" = '07027A00200377' where "pla_codi" = 186;
update "public"."oli_plantacio" set "pla_coordx" = '2.90064702858797', "pla_coordy" = '39.6869464657009', "pla_catast" = '07027A00200380' where "pla_codi" = 188;
update "public"."oli_plantacio" set "pla_coordx" = '2.90128742756819', "pla_coordy" = '39.6877453034368', "pla_catast" = '07027A00200381' where "pla_codi" = 189;
update "public"."oli_plantacio" set "pla_coordx" = '2.90110225027023', "pla_coordy" = '39.6894696480015', "pla_catast" = '07027A00200383' where "pla_codi" = 191;
update "public"."oli_plantacio" set "pla_coordx" = '2.72701463095485', "pla_coordy" = '39.7614298851836', "pla_catast" = '07061A00401866' where "pla_codi" = 194;
update "public"."oli_plantacio" set "pla_coordx" = '2.71424263734315', "pla_coordy" = '39.792357284099', "pla_catast" = '07061A00300248' where "pla_codi" = 196;
update "public"."oli_plantacio" set "pla_coordx" = '3.26453419656997', "pla_coordy" = '39.6177640568179', "pla_catast" = '07051A00500568' where "pla_codi" = 199;
update "public"."oli_plantacio" set "pla_coordx" = '2.70560720283085', "pla_coordy" = '39.7976890089169', "pla_catast" = '07061A00200594' where "pla_codi" = 201;
update "public"."oli_plantacio" set "pla_coordx" = '2.75522066527098', "pla_coordy" = '39.783624902727', "pla_catast" = '07025A00100251' where "pla_codi" = 203;
update "public"."oli_plantacio" set "pla_coordx" = '3.00669934562935', "pla_coordy" = '39.7621764250764', "pla_catast" = '07044A00800257' where "pla_codi" = 204;
update "public"."oli_plantacio" set "pla_coordx" = '3.00717294550479', "pla_coordy" = '39.7618477959206', "pla_catast" = '07044A00800258' where "pla_codi" = 205;
update "public"."oli_plantacio" set "pla_coordx" = '3.00669252698549', "pla_coordy" = '39.7617217725005', "pla_catast" = '07044A00800260' where "pla_codi" = 207;
update "public"."oli_plantacio" set "pla_coordx" = '2.74812980439708', "pla_coordy" = '39.5458488932506', "pla_catast" = '07040A05300008' where "pla_codi" = 208;
update "public"."oli_plantacio" set "pla_coordx" = '2.75345593878996', "pla_coordy" = '39.5489055654019', "pla_catast" = '07040A05300005' where "pla_codi" = 211;
update "public"."oli_plantacio" set "pla_coordx" = '2.70886873143773', "pla_coordy" = '39.755857645991', "pla_catast" = '07061A00401982' where "pla_codi" = 213;
update "public"."oli_plantacio" set "pla_coordx" = '2.70626254227881', "pla_coordy" = '39.7561507604478', "pla_catast" = '07061A00500354' where "pla_codi" = 214;
update "public"."oli_plantacio" set "pla_coordx" = '2.74505346069337', "pla_coordy" = '39.7700840679879', "pla_catast" = '07025A00100175' where "pla_codi" = 215;
update "public"."oli_plantacio" set "pla_coordx" = '3.08085676673695', "pla_coordy" = '39.8474088565293', "pla_catast" = '07003A00500307' where "pla_codi" = 217;
update "public"."oli_plantacio" set "pla_coordx" = '2.7181658082499', "pla_coordy" = '39.7996208209134', "pla_catast" = '07061A00300428' where "pla_codi" = 218;
update "public"."oli_plantacio" set "pla_coordx" = '2.75005318187917', "pla_coordy" = '39.7803470568422', "pla_catast" = '07025A00100695' where "pla_codi" = 220;
update "public"."oli_plantacio" set "pla_coordx" = '2.72830622420661', "pla_coordy" = '39.7618049435377', "pla_catast" = '07061A00401872' where "pla_codi" = 221;
update "public"."oli_plantacio" set "pla_coordx" = '2.68415684386548', "pla_coordy" = '39.7710588527665', "pla_catast" = '07061A00500449' where "pla_codi" = 223;
update "public"."oli_plantacio" set "pla_coordx" = '2.75162915634377', "pla_coordy" = '39.7698168572083', "pla_catast" = '07061A00401668' where "pla_codi" = 226;
update "public"."oli_plantacio" set "pla_coordx" = '2.75798980818069', "pla_coordy" = '39.78279377086', "pla_catast" = '07025A00100240' where "pla_codi" = 227;
update "public"."oli_plantacio" set "pla_coordx" = '2.75887718691527', "pla_coordy" = '39.783175129932', "pla_catast" = '07025A00100246' where "pla_codi" = 228;
update "public"."oli_plantacio" set "pla_coordx" = '2.75706222745658', "pla_coordy" = '39.7836878880227', "pla_catast" = '07025A00100249' where "pla_codi" = 230;
update "public"."oli_plantacio" set "pla_coordx" = '2.75641204790066', "pla_coordy" = '39.7828389370981', "pla_catast" = '07025A00100685' where "pla_codi" = 231;
update "public"."oli_plantacio" set "pla_coordx" = '2.71924787391834', "pla_coordy" = '39.7916929540553', "pla_catast" = '07061A00300146' where "pla_codi" = 295;
update "public"."oli_plantacio" set "pla_coordx" = '2.92521283244611', "pla_coordy" = '39.6921750506588', "pla_catast" = '07027A00200002' where "pla_codi" = 234;
update "public"."oli_plantacio" set "pla_coordx" = '2.7438695120598', "pla_coordy" = '39.7850427002784', "pla_catast" = '07025A00100288' where "pla_codi" = 236;
update "public"."oli_plantacio" set "pla_coordx" = '2.73042401603267', "pla_coordy" = '39.791951779817', "pla_catast" = '07025A00100034' where "pla_codi" = 237;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 247;
update "public"."oli_plantacio" set "pla_coordx" = '2.71220121499334', "pla_coordy" = '39.7947527880664', "pla_catast" = '07061A00200553' where "pla_codi" = 239;
update "public"."oli_plantacio" set "pla_coordx" = '2.750444803726', "pla_coordy" = '39.7793243490006', "pla_catast" = '07025A00100142' where "pla_codi" = 241;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 254;
update "public"."oli_plantacio" set "pla_coordx" = '2.71350063225246', "pla_coordy" = '39.7583212686088', "pla_catast" = '07061A00401941' where "pla_codi" = 243;
update "public"."oli_plantacio" set "pla_coordx" = '2.71132723310432', "pla_coordy" = '39.7510287710849', "pla_catast" = '07061A00401949' where "pla_codi" = 244;
update "public"."oli_plantacio" set "pla_coordx" = '2.70823205332868', "pla_coordy" = '39.7563016884533', "pla_catast" = '07061A00500343' where "pla_codi" = 245;
update "public"."oli_plantacio" set "pla_coordx" = '2.94120915867347', "pla_coordy" = '39.37156025489', "pla_catast" = '07013A02200015' where "pla_codi" = 248;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 269;
update "public"."oli_plantacio" set "pla_coordx" = '2.72167714265056', "pla_coordy" = '39.7936893240043', "pla_catast" = '07061A00300366' where "pla_codi" = 251;
update "public"."oli_plantacio" set "pla_coordx" = '2.71986608969962', "pla_coordy" = '39.7926123982997', "pla_catast" = '07061A00300145' where "pla_codi" = 252;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 277;
update "public"."oli_plantacio" set "pla_coordx" = '2.72233517162387', "pla_coordy" = '39.8014051526154', "pla_catast" = '07061A00300335' where "pla_codi" = 255;
update "public"."oli_plantacio" set "pla_coordx" = '2.72090594645901', "pla_coordy" = '39.8003687133395', "pla_catast" = '07061A00300348' where "pla_codi" = 256;
update "public"."oli_plantacio" set "pla_coordx" = '2.72178987686064', "pla_coordy" = '39.8022796234488', "pla_catast" = '07061A00300334' where "pla_codi" = 257;
update "public"."oli_plantacio" set "pla_coordx" = '2.79190985889469', "pla_coordy" = '39.8466984851119', "pla_catast" = '07019A00400029' where "pla_codi" = 259;
update "public"."oli_plantacio" set "pla_coordx" = '2.92383689750529', "pla_coordy" = '39.480470359604', "pla_catast" = '07031A02000204' where "pla_codi" = 260;
update "public"."oli_plantacio" set "pla_coordx" = '2.83790457252672', "pla_coordy" = '39.7114431600175', "pla_catast" = '07008A01500145' where "pla_codi" = 263;
update "public"."oli_plantacio" set "pla_coordx" = '2.70916762365521', "pla_coordy" = '39.8090588841908', "pla_catast" = '07061A00300311' where "pla_codi" = 265;
update "public"."oli_plantacio" set "pla_coordx" = '2.74491509497491', "pla_coordy" = '39.7719235458578', "pla_catast" = '07025A00100171' where "pla_codi" = 267;
update "public"."oli_plantacio" set "pla_coordx" = '3.09072581162529', "pla_coordy" = '39.7276862123993', "pla_catast" = '07039A01400409' where "pla_codi" = 270;
update "public"."oli_plantacio" set "pla_coordx" = '2.89083200229413', "pla_coordy" = '39.7003942480498', "pla_catast" = '07027A00100081' where "pla_codi" = 271;
update "public"."oli_plantacio" set "pla_coordx" = '2.72144986110264', "pla_coordy" = '39.7885821316148', "pla_catast" = '07061A00300251' where "pla_codi" = 273;
update "public"."oli_plantacio" set "pla_coordx" = '2.69466415962981', "pla_coordy" = '39.7757609409915', "pla_catast" = '07061A00100314' where "pla_codi" = 274;
update "public"."oli_plantacio" set "pla_coordx" = '2.69654271032745', "pla_coordy" = '39.775206883277', "pla_catast" = '07061A00100318' where "pla_codi" = 276;
update "public"."oli_plantacio" set "pla_coordx" = '2.98156678811278', "pla_coordy" = '39.7065234389215', "pla_catast" = '07027A00500374' where "pla_codi" = 278;
update "public"."oli_plantacio" set "pla_coordx" = '2.98343901577131', "pla_coordy" = '39.7076824068838', "pla_catast" = '07027A00600157' where "pla_codi" = 279;
update "public"."oli_plantacio" set "pla_coordx" = '2.83523759101161', "pla_coordy" = '39.7199610504381', "pla_catast" = '07001A00300263' where "pla_codi" = 281;
update "public"."oli_plantacio" set "pla_coordx" = '2.74740198555795', "pla_coordy" = '39.7799008926433', "pla_catast" = '07025A00100716' where "pla_codi" = 284;
update "public"."oli_plantacio" set "pla_coordx" = '2.74758461709469', "pla_coordy" = '39.7809912466994', "pla_catast" = '07025A00100703' where "pla_codi" = 285;
update "public"."oli_plantacio" set "pla_coordx" = '2.6948272674202', "pla_coordy" = '39.7623967205079', "pla_catast" = '07061A00500268' where "pla_codi" = 287;
update "public"."oli_plantacio" set "pla_coordx" = '2.69423627864774', "pla_coordy" = '39.762190006187', "pla_catast" = '07061A00500280' where "pla_codi" = 288;
update "public"."oli_plantacio" set "pla_coordx" = '2.69157200109897', "pla_coordy" = '39.7614361143906', "pla_catast" = '07061A00500398' where "pla_codi" = 289;
update "public"."oli_plantacio" set "pla_coordx" = '2.69740810576972', "pla_coordy" = '39.5834070327252', "pla_catast" = '07040A03100006' where "pla_codi" = 291;
update "public"."oli_plantacio" set "pla_coordx" = '2.68992961878089', "pla_coordy" = '39.7670953875929', "pla_catast" = '07061A00500605' where "pla_codi" = 292;
update "public"."oli_plantacio" set "pla_coordx" = '3.01754228624065', "pla_coordy" = '39.8861151642505', "pla_catast" = '07042A00400245' where "pla_codi" = 294;
update "public"."oli_plantacio" set "pla_coordx" = '2.72046381440534', "pla_coordy" = '39.7917565322465', "pla_catast" = '07061A00300147' where "pla_codi" = 296;
update "public"."oli_plantacio" set "pla_coordx" = '2.8812287427261', "pla_coordy" = '39.6778708115344', "pla_catast" = '07008A01200190' where "pla_codi" = 299;
update "public"."oli_plantacio" set "pla_coordx" = '2.71070902805891', "pla_coordy" = '39.7534915920924', "pla_catast" = '07061A00401968' where "pla_codi" = 301;
update "public"."oli_plantacio" set "pla_coordx" = '2.67931902170828', "pla_coordy" = '39.7772978459486', "pla_catast" = '07061A00100425' where "pla_codi" = 302;
update "public"."oli_plantacio" set "pla_coordx" = '2.6789843360974', "pla_coordy" = '39.7799438022683', "pla_catast" = '07061A00100426' where "pla_codi" = 303;
update "public"."oli_plantacio" set "pla_coordx" = '2.68202213243646', "pla_coordy" = '39.7813161074316', "pla_catast" = '07061A00100429' where "pla_codi" = 305;
update "public"."oli_plantacio" set "pla_coordx" = '2.71807450546151', "pla_coordy" = '39.7988654642477', "pla_catast" = '07061A00300357' where "pla_codi" = 306;
update "public"."oli_plantacio" set "pla_coordx" = '2.88974546599528', "pla_coordy" = '39.4711232965621', "pla_catast" = '07031A04600194' where "pla_codi" = 1059;
update "public"."oli_plantacio" set "pla_coordx" = '2.83790457252672', "pla_coordy" = '39.7114431600175', "pla_catast" = '07008A01500145' where "pla_codi" = 262;
update "public"."oli_plantacio" set "pla_coordx" = '2.74059808931182', "pla_coordy" = '39.7701011322741', "pla_catast" = '07025A00100916' where "pla_codi" = 310;
update "public"."oli_plantacio" set "pla_coordx" = '2.73966402432833', "pla_coordy" = '39.771836825644', "pla_catast" = '07025A00100180' where "pla_codi" = 311;
update "public"."oli_plantacio" set "pla_coordx" = '2.92966450895377', "pla_coordy" = '39.7463225774808', "pla_catast" = '07058A00800015' where "pla_codi" = 313;
update "public"."oli_plantacio" set "pla_coordx" = '2.71280464692694', "pla_coordy" = '39.7964033107448', "pla_catast" = '07061A00300378' where "pla_codi" = 314;
update "public"."oli_plantacio" set "pla_coordx" = '2.75365033845524', "pla_coordy" = '39.7695988879201', "pla_catast" = '07061A00401669' where "pla_codi" = 317;
update "public"."oli_plantacio" set "pla_coordx" = '2.69212029696024', "pla_coordy" = '39.7624188578848', "pla_catast" = '07061A00500401' where "pla_codi" = 318;
update "public"."oli_plantacio" set "pla_coordx" = '2.74386489461715', "pla_coordy" = '39.7722177555835', "pla_catast" = '07025A00100170' where "pla_codi" = 320;
update "public"."oli_plantacio" set "pla_coordx" = '2.71139706903846', "pla_coordy" = '39.7915268887841', "pla_catast" = '07061A00200351' where "pla_codi" = 321;
update "public"."oli_plantacio" set "pla_coordx" = '2.67438485593164', "pla_coordy" = '39.7745699200824', "pla_catast" = '07061A00100448' where "pla_codi" = 323;
update "public"."oli_plantacio" set "pla_coordx" = '2.67421673493439', "pla_coordy" = '39.7738956768102', "pla_catast" = '07061A00500529' where "pla_codi" = 324;
update "public"."oli_plantacio" set "pla_coordx" = '2.72324195315782', "pla_coordy" = '39.7914291082451', "pla_catast" = '07061A00300175' where "pla_codi" = 326;
update "public"."oli_plantacio" set "pla_coordx" = '2.72345536458533', "pla_coordy" = '39.7918317381237', "pla_catast" = '07061A00300467' where "pla_codi" = 327;
update "public"."oli_plantacio" set "pla_coordx" = '2.72261282371248', "pla_coordy" = '39.7920287566727', "pla_catast" = '07061A00300156' where "pla_codi" = 328;
update "public"."oli_plantacio" set "pla_coordx" = '2.68805569075468', "pla_coordy" = '39.7709343346649', "pla_catast" = '07061A00500453' where "pla_codi" = 330;
update "public"."oli_plantacio" set "pla_coordx" = '2.68714205668321', "pla_coordy" = '39.7740033170709', "pla_catast" = '07061A00500563' where "pla_codi" = 333;
update "public"."oli_plantacio" set "pla_coordx" = '2.68854019831559', "pla_coordy" = '39.7711006100249', "pla_catast" = '07061A00500575' where "pla_codi" = 334;
update "public"."oli_plantacio" set "pla_coordx" = '2.68469644811777', "pla_coordy" = '39.7688437531786', "pla_catast" = '07061A00500436' where "pla_codi" = 336;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 359;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 360;
update "public"."oli_plantacio" set "pla_coordx" = '2.6893004284191', "pla_coordy" = '39.7680062459333', "pla_catast" = '07061A00500421' where "pla_codi" = 337;
update "public"."oli_plantacio" set "pla_coordx" = '2.69074644072482', "pla_coordy" = '39.7677719704611', "pla_catast" = '07061A00500422' where "pla_codi" = 338;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 369;
update "public"."oli_plantacio" set "pla_coordx" = '2.68760592161824', "pla_coordy" = '39.77235833756', "pla_catast" = '07061A00500461' where "pla_codi" = 340;
update "public"."oli_plantacio" set "pla_coordx" = '2.72924217343159', "pla_coordy" = '39.7846954462989', "pla_catast" = '07025A00100332' where "pla_codi" = 341;
update "public"."oli_plantacio" set "pla_coordx" = '3.08060412766548', "pla_coordy" = '39.7188861748786', "pla_catast" = '07039A00600443' where "pla_codi" = 343;
update "public"."oli_plantacio" set "pla_coordx" = '3.08012782308651', "pla_coordy" = '39.719046705527', "pla_catast" = '07039A00600444' where "pla_codi" = 344;
update "public"."oli_plantacio" set "pla_coordx" = '2.71396730143046', "pla_coordy" = '39.7952769534367', "pla_catast" = '07061A00200618' where "pla_codi" = 347;
update "public"."oli_plantacio" set "pla_coordx" = '2.72827994201878', "pla_coordy" = '39.7940144052237', "pla_catast" = '07025A00100064' where "pla_codi" = 349;
update "public"."oli_plantacio" set "pla_coordx" = '2.72689526822254', "pla_coordy" = '39.7930106692824', "pla_catast" = '07061A00300168' where "pla_codi" = 350;
update "public"."oli_plantacio" set "pla_coordx" = '2.72608620554004', "pla_coordy" = '39.7918306902306', "pla_catast" = '07061A00300468' where "pla_codi" = 351;
update "public"."oli_plantacio" set "pla_coordx" = '2.72909018191985', "pla_coordy" = '39.791687826195', "pla_catast" = '07025A00100049' where "pla_codi" = 353;
update "public"."oli_plantacio" set "pla_coordx" = '2.72958138884708', "pla_coordy" = '39.7918468306118', "pla_catast" = '07025A00100050' where "pla_codi" = 354;
update "public"."oli_plantacio" set "pla_coordx" = '2.79368889488366', "pla_coordy" = '39.7451341892019', "pla_catast" = '07010A00500037' where "pla_codi" = 356;
update "public"."oli_plantacio" set "pla_coordx" = '2.78370705003013', "pla_coordy" = '39.7575225986506', "pla_catast" = '07010A00600001' where "pla_codi" = 357;
update "public"."oli_plantacio" set "pla_coordx" = '2.87494236110613', "pla_coordy" = '39.7422840007962', "pla_catast" = '07034A00200119' where "pla_codi" = 361;
update "public"."oli_plantacio" set "pla_coordx" = '3.08888095143525', "pla_coordy" = '39.6683582793073', "pla_catast" = '07035A00400201' where "pla_codi" = 364;
update "public"."oli_plantacio" set "pla_coordx" = '3.0894467551663', "pla_coordy" = '39.6720310471813', "pla_catast" = '07035A00400779' where "pla_codi" = 365;
update "public"."oli_plantacio" set "pla_coordx" = '2.74246933859957', "pla_coordy" = '39.7730347687409', "pla_catast" = '07025A00100788' where "pla_codi" = 367;
update "public"."oli_plantacio" set "pla_coordx" = '2.91073213318726', "pla_coordy" = '39.4904157859731', "pla_catast" = '07031A01600723' where "pla_codi" = 368;
update "public"."oli_plantacio" set "pla_coordx" = '2.74156155561501', "pla_coordy" = '39.7794292240493', "pla_catast" = '07025A00100552' where "pla_codi" = 370;
update "public"."oli_plantacio" set "pla_coordx" = '2.88860146269701', "pla_coordy" = '39.7287879087335', "pla_catast" = '07027A00900201' where "pla_codi" = 372;
update "public"."oli_plantacio" set "pla_coordx" = '2.8361267531132', "pla_coordy" = '39.6873734527416', "pla_catast" = '07008A00900010' where "pla_codi" = 373;
update "public"."oli_plantacio" set "pla_coordx" = '2.73891048174605', "pla_coordy" = '39.7858465084427', "pla_catast" = '07025A00100438' where "pla_codi" = 378;
update "public"."oli_plantacio" set "pla_coordx" = '2.8996345753038', "pla_coordy" = '39.7917016809456', "pla_catast" = '07058A01300087' where "pla_codi" = 379;
update "public"."oli_plantacio" set "pla_coordx" = '2.73925409221676', "pla_coordy" = '39.7680185788239', "pla_catast" = '07061A00401642' where "pla_codi" = 381;
update "public"."oli_plantacio" set "pla_coordx" = '2.78825562324517', "pla_coordy" = '39.7026222799905', "pla_catast" = '07001A00400003' where "pla_codi" = 384;
update "public"."oli_plantacio" set "pla_coordx" = '2.9192707468221', "pla_coordy" = '39.7479874713843', "pla_catast" = '07058A01000002' where "pla_codi" = 385;
update "public"."oli_plantacio" set "pla_coordx" = '2.70766165392941', "pla_coordy" = '39.6569003690732', "pla_catast" = '07010A00300271' where "pla_codi" = 386;
update "public"."oli_plantacio" set "pla_coordx" = '2.99684937868106', "pla_coordy" = '39.7217261873849', "pla_catast" = '07030A00300688' where "pla_codi" = 388;
update "public"."oli_plantacio" set "pla_coordx" = '2.75835790491105', "pla_coordy" = '39.795108045404', "pla_catast" = '07025A00100380' where "pla_codi" = 389;
update "public"."oli_plantacio" set "pla_coordx" = '3.07863502967712', "pla_coordy" = '39.7148166075392', "pla_catast" = '07039A00500295' where "pla_codi" = 391;
update "public"."oli_plantacio" set "pla_coordx" = '3.07855292310859', "pla_coordy" = '39.7147379134709', "pla_catast" = '07039A00500296' where "pla_codi" = 392;
update "public"."oli_plantacio" set "pla_coordx" = '2.75435292443564', "pla_coordy" = '39.7932143425356', "pla_catast" = '07025A00100374' where "pla_codi" = 394;
update "public"."oli_plantacio" set "pla_coordx" = '2.72923304715755', "pla_coordy" = '39.7938608524796', "pla_catast" = '07025A00100065' where "pla_codi" = 395;
update "public"."oli_plantacio" set "pla_coordx" = '2.48956012102713', "pla_coordy" = '39.6599488223649', "pla_catast" = '07021A00400132' where "pla_codi" = 397;
update "public"."oli_plantacio" set "pla_coordx" = '2.49114495423723', "pla_coordy" = '39.6570357357032', "pla_catast" = '07021A00400016' where "pla_codi" = 398;
update "public"."oli_plantacio" set "pla_coordx" = '2.48959259480043', "pla_coordy" = '39.6566876301798', "pla_catast" = '07021A00400019' where "pla_codi" = 401;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 415;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 416;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 417;
update "public"."oli_plantacio" set "pla_coordx" = '2.74973029696', "pla_coordy" = '39.778827970507', "pla_catast" = '07025A00100144' where "pla_codi" = 403;
update "public"."oli_plantacio" set "pla_coordx" = '2.74870521377596', "pla_coordy" = '39.7794808791101', "pla_catast" = '07025A00100710' where "pla_codi" = 404;
update "public"."oli_plantacio" set "pla_coordx" = '2.70448314496572', "pla_coordy" = '39.7594317823036', "pla_catast" = '07061A00500362' where "pla_codi" = 406;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 426;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 427;
update "public"."oli_plantacio" set "pla_coordx" = '2.90232300048225', "pla_coordy" = '39.7672982664278', "pla_catast" = '07058A01300082' where "pla_codi" = 407;
update "public"."oli_plantacio" set "pla_coordx" = '2.90173709797568', "pla_coordy" = '39.7675667255387', "pla_catast" = '07058A02100105' where "pla_codi" = 408;
update "public"."oli_plantacio" set "pla_coordx" = '2.74895737092948', "pla_coordy" = '39.7787618756496', "pla_catast" = '07025A00100913' where "pla_codi" = 410;
update "public"."oli_plantacio" set "pla_coordx" = '2.73263405237318', "pla_coordy" = '39.7893491103724', "pla_catast" = '07025A00100032' where "pla_codi" = 411;
update "public"."oli_plantacio" set "pla_coordx" = '2.73195672217447', "pla_coordy" = '39.791171458696', "pla_catast" = '07025A00100358' where "pla_codi" = 413;
update "public"."oli_plantacio" set "pla_coordx" = '3.20580436873482', "pla_coordy" = '39.4993847205154', "pla_catast" = '07033A02300495' where "pla_codi" = 419;
update "public"."oli_plantacio" set "pla_coordx" = '2.75254113847349', "pla_coordy" = '39.4149221018545', "pla_catast" = '07031A03500023' where "pla_codi" = 420;
update "public"."oli_plantacio" set "pla_coordx" = '2.70196221155565', "pla_coordy" = '39.8040717171937', "pla_catast" = '07061A00300267' where "pla_codi" = 421;
update "public"."oli_plantacio" set "pla_coordx" = '2.70408746977385', "pla_coordy" = '39.8052685600104', "pla_catast" = '07061A00300273' where "pla_codi" = 423;
update "public"."oli_plantacio" set "pla_coordx" = '2.70341415199276', "pla_coordy" = '39.8023228900494', "pla_catast" = '07061A00300316' where "pla_codi" = 424;
update "public"."oli_plantacio" set "pla_coordx" = '2.799745593371', "pla_coordy" = '39.6768207175602', "pla_catast" = '07016A00100015' where "pla_codi" = 428;
update "public"."oli_plantacio" set "pla_coordx" = '2.79978915279937', "pla_coordy" = '39.6772827401658', "pla_catast" = '07016A00100016' where "pla_codi" = 429;
update "public"."oli_plantacio" set "pla_coordx" = '2.65372876092881', "pla_coordy" = '39.7468566992378', "pla_catast" = '07018A00200298' where "pla_codi" = 431;
update "public"."oli_plantacio" set "pla_coordx" = '2.65072513501741', "pla_coordy" = '39.75441581491', "pla_catast" = '07018A00200320' where "pla_codi" = 432;
update "public"."oli_plantacio" set "pla_coordx" = '3.04281038470708', "pla_coordy" = '39.3984906382296', "pla_catast" = '07013A01500409' where "pla_codi" = 434;
update "public"."oli_plantacio" set "pla_coordx" = '3.07373187520748', "pla_coordy" = '39.8450404098563', "pla_catast" = '07003A00600042' where "pla_codi" = 437;
update "public"."oli_plantacio" set "pla_coordx" = '2.68976338005907', "pla_coordy" = '39.7702680212525', "pla_catast" = '07061A00500434' where "pla_codi" = 441;
update "public"."oli_plantacio" set "pla_coordx" = '3.05324966609518', "pla_coordy" = '39.727835265124', "pla_catast" = '07039A00501028' where "pla_codi" = 442;
update "public"."oli_plantacio" set "pla_coordx" = '2.75861440153114', "pla_coordy" = '39.7790299996446', "pla_catast" = '07025A00100107' where "pla_codi" = 444;
update "public"."oli_plantacio" set "pla_coordx" = '2.75712045139218', "pla_coordy" = '39.7797420131288', "pla_catast" = '07025A00100112' where "pla_codi" = 445;
update "public"."oli_plantacio" set "pla_coordx" = '2.73483453945765', "pla_coordy" = '39.7792617624224', "pla_catast" = '07025A00100319' where "pla_codi" = 447;
update "public"."oli_plantacio" set "pla_coordx" = '2.7501006582346', "pla_coordy" = '39.7826577195353', "pla_catast" = '07025A00100679' where "pla_codi" = 448;
update "public"."oli_plantacio" set "pla_coordx" = '2.75182655132815', "pla_coordy" = '39.781001582696', "pla_catast" = '07025A00100693' where "pla_codi" = 450;
update "public"."oli_plantacio" set "pla_coordx" = '2.75134590576502', "pla_coordy" = '39.7819726560577', "pla_catast" = '07025A00100820' where "pla_codi" = 451;
update "public"."oli_plantacio" set "pla_coordx" = '2.75196846543111', "pla_coordy" = '39.7814824877874', "pla_catast" = '07025A00100873' where "pla_codi" = 452;
update "public"."oli_plantacio" set "pla_coordx" = '2.75939656379132', "pla_coordy" = '39.7808435704362', "pla_catast" = '07025A00100096' where "pla_codi" = 455;
update "public"."oli_plantacio" set "pla_coordx" = '2.74802605871024', "pla_coordy" = '39.7864699171371', "pla_catast" = '07025A00100280' where "pla_codi" = 457;
update "public"."oli_plantacio" set "pla_coordx" = '2.7263910702921', "pla_coordy" = '39.7911151086172', "pla_catast" = '07025A00100908' where "pla_codi" = 458;
update "public"."oli_plantacio" set "pla_coordx" = '2.7444914763378', "pla_coordy" = '39.7787256296912', "pla_catast" = '07025A00100724' where "pla_codi" = 461;
update "public"."oli_plantacio" set "pla_coordx" = '2.73746295649048', "pla_coordy" = '39.7875287594779', "pla_catast" = '07025A00100454' where "pla_codi" = 462;
update "public"."oli_plantacio" set "pla_coordx" = '2.82050750738072', "pla_coordy" = '39.6179160286278', "pla_catast" = '07053A00100052' where "pla_codi" = 464;
update "public"."oli_plantacio" set "pla_coordx" = '2.82480072418544', "pla_coordy" = '39.6346555671043', "pla_catast" = '07053A00200513' where "pla_codi" = 465;
update "public"."oli_plantacio" set "pla_coordx" = '2.50243713317791', "pla_coordy" = '39.6788380089729', "pla_catast" = '07007A00100163' where "pla_codi" = 485;
update "public"."oli_plantacio" set "pla_coordx" = '3.06680874911055', "pla_coordy" = '39.8642982505514', "pla_catast" = '07042A00200431' where "pla_codi" = 513;
update "public"."oli_plantacio" set "pla_coordx" = '2.98831728980292', "pla_coordy" = '39.7528429941025', "pla_catast" = '07009A00500092' where "pla_codi" = 514;
update "public"."oli_plantacio" set "pla_coordx" = '3.12794372739008', "pla_coordy" = '39.8627632000243', "pla_catast" = '07003A00100098' where "pla_codi" = 521;
update "public"."oli_plantacio" set "pla_coordx" = '3.04101369688309', "pla_coordy" = '39.5917353213212', "pla_catast" = '07049A00400032' where "pla_codi" = 468;
update "public"."oli_plantacio" set "pla_coordx" = '3.1773831906339', "pla_coordy" = '39.5814508057257', "pla_catast" = '07033A02700600' where "pla_codi" = 470;
update "public"."oli_plantacio" set "pla_coordx" = '3.0822193108339', "pla_coordy" = '39.7212839284735', "pla_catast" = '07039A01400342' where "pla_codi" = 471;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 490;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 491;
update "public"."oli_plantacio" set "pla_coordx" = '2.74813537131845', "pla_coordy" = '39.7420780338689', "pla_catast" = '07010A00700187' where "pla_codi" = 473;
update "public"."oli_plantacio" set "pla_coordx" = '2.70444997640181', "pla_coordy" = '39.75883360721', "pla_catast" = '07061A00500571' where "pla_codi" = 474;
update "public"."oli_plantacio" set "pla_coordx" = '2.70333744400171', "pla_coordy" = '39.7588609547009', "pla_catast" = '07061A00500572' where "pla_codi" = 475;
update "public"."oli_plantacio" set "pla_coordx" = '3.07040901444411', "pla_coordy" = '39.6689739482042', "pla_catast" = '07035A00400598' where "pla_codi" = 477;
update "public"."oli_plantacio" set "pla_coordx" = '2.87800560848983', "pla_coordy" = '39.727218003999', "pla_catast" = '07058A01100091' where "pla_codi" = 478;
update "public"."oli_plantacio" set "pla_coordx" = '2.49423611090258', "pla_coordy" = '39.6621306070935', "pla_catast" = '07021A00400015' where "pla_codi" = 481;
update "public"."oli_plantacio" set "pla_coordx" = '2.48908187745935', "pla_coordy" = '39.6624603899361', "pla_catast" = '07021A00400008' where "pla_codi" = 483;
update "public"."oli_plantacio" set "pla_coordx" = '2.98836997636453', "pla_coordy" = '39.581486341489', "pla_catast" = '07038A00900191' where "pla_codi" = 484;
update "public"."oli_plantacio" set "pla_coordx" = '3.38746280373906', "pla_coordy" = '39.6632266876607', "pla_catast" = '07062A01900017' where "pla_codi" = 487;
update "public"."oli_plantacio" set "pla_coordx" = '2.88035805473002', "pla_coordy" = '39.6561739355285', "pla_catast" = '07008A01100095' where "pla_codi" = 488;
update "public"."oli_plantacio" set "pla_coordx" = '2.74293138005832', "pla_coordy" = '39.7842881875993', "pla_catast" = '07025A00100290' where "pla_codi" = 489;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 536;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 537;
update "public"."oli_plantacio" set "pla_coordx" = '2.74900033606572', "pla_coordy" = '39.7771861848357', "pla_catast" = '07025A00100158' where "pla_codi" = 493;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 541;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 543;
update "public"."oli_plantacio" set "pla_coordx" = '2.82825088731602', "pla_coordy" = '39.6451022780067', "pla_catast" = '07053A00200703' where "pla_codi" = 496;
update "public"."oli_plantacio" set "pla_coordx" = '2.60207301367446', "pla_coordy" = '39.700340486229', "pla_catast" = '07063A00200002' where "pla_codi" = 499;
update "public"."oli_plantacio" set "pla_coordx" = '2.60207301367446', "pla_coordy" = '39.700340486229', "pla_catast" = '07063A00200002' where "pla_codi" = 501;
update "public"."oli_plantacio" set "pla_coordx" = '2.60207301367446', "pla_coordy" = '39.700340486229', "pla_catast" = '07063A00200002' where "pla_codi" = 502;
update "public"."oli_plantacio" set "pla_coordx" = '3.40065907259049', "pla_coordy" = '39.6696377811242', "pla_catast" = '07014A01300015' where "pla_codi" = 503;
update "public"."oli_plantacio" set "pla_coordx" = '2.90461058953635', "pla_coordy" = '39.4951798285658', "pla_catast" = '07031A01600195' where "pla_codi" = 505;
update "public"."oli_plantacio" set "pla_coordx" = '2.83264671561796', "pla_coordy" = '39.7272654965239', "pla_catast" = '07029A00100003' where "pla_codi" = 506;
update "public"."oli_plantacio" set "pla_coordx" = '2.74325652988309', "pla_coordy" = '39.7769476348008', "pla_catast" = '07025A00100728' where "pla_codi" = 508;
update "public"."oli_plantacio" set "pla_coordx" = '3.07541230696993', "pla_coordy" = '39.7164607642265', "pla_catast" = '07039A00500270' where "pla_codi" = 509;
update "public"."oli_plantacio" set "pla_coordx" = '2.93279057225062', "pla_coordy" = '39.6787959870433', "pla_catast" = '07047A00300231' where "pla_codi" = 511;
update "public"."oli_plantacio" set "pla_coordx" = '3.06656026103777', "pla_coordy" = '39.8647405995719', "pla_catast" = '07042A00200428' where "pla_codi" = 512;
update "public"."oli_plantacio" set "pla_coordx" = '3.31806830878473', "pla_coordy" = '39.5562747615506', "pla_catast" = '07033A01600009' where "pla_codi" = 522;
update "public"."oli_plantacio" set "pla_coordx" = '2.75536239540152', "pla_coordy" = '39.7794413387393', "pla_catast" = '07025A00100135' where "pla_codi" = 523;
update "public"."oli_plantacio" set "pla_coordx" = '2.61888987591645', "pla_coordy" = '39.7371664243614', "pla_catast" = '07063A00300001' where "pla_codi" = 525;
update "public"."oli_plantacio" set "pla_coordx" = '2.74575927668734', "pla_coordy" = '39.7895369666277', "pla_catast" = '07025A00100469' where "pla_codi" = 526;
update "public"."oli_plantacio" set "pla_coordx" = '2.74428733125297', "pla_coordy" = '39.7887182209402', "pla_catast" = '07025A00100516' where "pla_codi" = 528;
update "public"."oli_plantacio" set "pla_coordx" = '2.74460561376408', "pla_coordy" = '39.7893436846863', "pla_catast" = '07025A00100517' where "pla_codi" = 529;
update "public"."oli_plantacio" set "pla_coordx" = '2.69405960457346', "pla_coordy" = '39.7666087172751', "pla_catast" = '07061A00500391' where "pla_codi" = 532;
update "public"."oli_plantacio" set "pla_coordx" = '3.12708075489864', "pla_coordy" = '39.8635745017879', "pla_catast" = '07003A00100097' where "pla_codi" = 534;
update "public"."oli_plantacio" set "pla_coordx" = '2.58912833582366', "pla_coordy" = '39.6831614930386', "pla_catast" = '07020A00400086' where "pla_codi" = 538;
update "public"."oli_plantacio" set "pla_coordx" = '3.05585394201141', "pla_coordy" = '39.4235235612528', "pla_catast" = '07013A01300018' where "pla_codi" = 540;
update "public"."oli_plantacio" set "pla_coordx" = '2.64931695972455', "pla_coordy" = '39.7090718569061', "pla_catast" = '07063A00300145' where "pla_codi" = 542;
update "public"."oli_plantacio" set "pla_coordx" = '2.72391177967659', "pla_coordy" = '39.7932136225744', "pla_catast" = '07061A00300160' where "pla_codi" = 545;
update "public"."oli_plantacio" set "pla_coordx" = '2.95312131925594', "pla_coordy" = '39.792388700725', "pla_catast" = '07012A01000117' where "pla_codi" = 531;
update "public"."oli_plantacio" set "pla_coordx" = '2.91487806861587', "pla_coordy" = '39.7379168836371', "pla_catast" = '07058A00800139' where "pla_codi" = 555;
update "public"."oli_plantacio" set "pla_coordx" = '3.06168041378932', "pla_coordy" = '39.7236054899475', "pla_catast" = '07039A00500752' where "pla_codi" = 549;
update "public"."oli_plantacio" set "pla_coordx" = '3.06152387403888', "pla_coordy" = '39.7217139647832', "pla_catast" = '07039A00500733' where "pla_codi" = 551;
update "public"."oli_plantacio" set "pla_coordx" = '2.70964359868593', "pla_coordy" = '39.6776265412707', "pla_catast" = '07010A00300023' where "pla_codi" = 552;
update "public"."oli_plantacio" set "pla_coordx" = '2.94628821568599', "pla_coordy" = '39.5890819623229', "pla_catast" = '07038A00800001' where "pla_codi" = 554;
update "public"."oli_plantacio" set "pla_coordx" = '2.70005247335282', "pla_coordy" = '39.7599169706632', "pla_catast" = '07061A00500570' where "pla_codi" = 556;
update "public"."oli_plantacio" set "pla_coordx" = '2.70578819070018', "pla_coordy" = '39.7602835770876', "pla_catast" = '07061A00500318' where "pla_codi" = 558;
update "public"."oli_plantacio" set "pla_coordx" = '2.69854091217745', "pla_coordy" = '39.7620585407594', "pla_catast" = '07061A00500252' where "pla_codi" = 559;
update "public"."oli_plantacio" set "pla_coordx" = '2.69609441768222', "pla_coordy" = '39.7625320414453', "pla_catast" = '07061A00500257' where "pla_codi" = 560;
update "public"."oli_plantacio" set "pla_coordx" = '3.06258206393221', "pla_coordy" = '39.6655119927726', "pla_catast" = '07035A00300652' where "pla_codi" = 563;
update "public"."oli_plantacio" set "pla_coordx" = '3.06639402728238', "pla_coordy" = '39.6636377522712', "pla_catast" = '07035A00300522' where "pla_codi" = 565;
update "public"."oli_plantacio" set "pla_coordx" = '2.7180164332551', "pla_coordy" = '39.8159407454455', "pla_catast" = '07061A00300444' where "pla_codi" = 566;
update "public"."oli_plantacio" set "pla_coordx" = '3.06460072283537', "pla_coordy" = '39.746941252882', "pla_catast" = '07039A01200136' where "pla_codi" = 567;
update "public"."oli_plantacio" set "pla_coordx" = '2.88420874636428', "pla_coordy" = '39.6547889563799', "pla_catast" = '07047A00700138' where "pla_codi" = 569;
update "public"."oli_plantacio" set "pla_coordx" = '2.70503570889827', "pla_coordy" = '39.5832408751504', "pla_catast" = '07040A03100014' where "pla_codi" = 570;
update "public"."oli_plantacio" set "pla_coordx" = '2.75799736146106', "pla_coordy" = '39.7870127899018', "pla_catast" = '07025A00100395' where "pla_codi" = 572;
update "public"."oli_plantacio" set "pla_coordx" = '2.86344292794542', "pla_coordy" = '39.6194955501897', "pla_catast" = '07047A01000360' where "pla_codi" = 573;
update "public"."oli_plantacio" set "pla_coordx" = '2.74788963175083', "pla_coordy" = '39.777275047775', "pla_catast" = '07025A00100799' where "pla_codi" = 575;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 603;
update "public"."oli_plantacio" set "pla_coordx" = '2.88079919902173', "pla_coordy" = '39.6736818795476', "pla_catast" = '07008A01200108' where "pla_codi" = 578;
update "public"."oli_plantacio" set "pla_coordx" = '2.88913144293215', "pla_coordy" = '39.6706715726049', "pla_catast" = '07008A01200176' where "pla_codi" = 579;
update "public"."oli_plantacio" set "pla_coordx" = '2.88458077578657', "pla_coordy" = '39.6718529516082', "pla_catast" = '07008A01200181' where "pla_codi" = 580;
update "public"."oli_plantacio" set "pla_coordx" = '2.88653503737953', "pla_coordy" = '39.6719781704185', "pla_catast" = '07008A01200183' where "pla_codi" = 582;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 619;
update "public"."oli_plantacio" set "pla_coordx" = '2.88382392662891', "pla_coordy" = '39.6728589616927', "pla_catast" = '07008A01200230' where "pla_codi" = 583;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 621;
update "public"."oli_plantacio" set "pla_coordx" = '3.31985934265855', "pla_coordy" = '39.6899180603058', "pla_catast" = '07006A02200018' where "pla_codi" = 585;
update "public"."oli_plantacio" set "pla_coordx" = '2.68400337878538', "pla_coordy" = '39.7750812472427', "pla_catast" = '07061A00100564' where "pla_codi" = 586;
update "public"."oli_plantacio" set "pla_coordx" = '2.6907965161029', "pla_coordy" = '39.7701000368216', "pla_catast" = '07061A00500427' where "pla_codi" = 587;
update "public"."oli_plantacio" set "pla_coordx" = '2.68369449339997', "pla_coordy" = '39.7761360241141', "pla_catast" = '07061A00100562' where "pla_codi" = 589;
update "public"."oli_plantacio" set "pla_coordx" = '2.68520563626106', "pla_coordy" = '39.7753589607578', "pla_catast" = '07061A00100565' where "pla_codi" = 590;
update "public"."oli_plantacio" set "pla_coordx" = '2.90715043049174', "pla_coordy" = '39.7549494620643', "pla_catast" = '07058A01700051' where "pla_codi" = 593;
update "public"."oli_plantacio" set "pla_coordx" = '2.79429888196404', "pla_coordy" = '39.5527396896555', "pla_catast" = '07040A05000051' where "pla_codi" = 595;
update "public"."oli_plantacio" set "pla_coordx" = '2.96327994435397', "pla_coordy" = '39.5600406130198', "pla_catast" = '07004A01400127' where "pla_codi" = 596;
update "public"."oli_plantacio" set "pla_coordx" = '2.7121413430287', "pla_coordy" = '39.8095264545711', "pla_catast" = '07061A00300305' where "pla_codi" = 597;
update "public"."oli_plantacio" set "pla_coordx" = '2.71096903050391', "pla_coordy" = '39.8105502322523', "pla_catast" = '07061A00300308' where "pla_codi" = 599;
update "public"."oli_plantacio" set "pla_coordx" = '2.71230085508307', "pla_coordy" = '39.8096562352515', "pla_catast" = '07061A00300304' where "pla_codi" = 600;
update "public"."oli_plantacio" set "pla_coordx" = '2.70409340464715', "pla_coordy" = '39.806337979967', "pla_catast" = '07061A00300274' where "pla_codi" = 602;
update "public"."oli_plantacio" set "pla_coordx" = '2.75133442469126', "pla_coordy" = '39.7777898518205', "pla_catast" = '07025A00100899' where "pla_codi" = 604;
update "public"."oli_plantacio" set "pla_coordx" = '2.89662458698122', "pla_coordy" = '39.7352178229563', "pla_catast" = '07058A01400191' where "pla_codi" = 606;
update "public"."oli_plantacio" set "pla_coordx" = '2.94277919622166', "pla_coordy" = '39.7700248693645', "pla_catast" = '07058A01900098' where "pla_codi" = 607;
update "public"."oli_plantacio" set "pla_coordx" = '2.73351263346074', "pla_coordy" = '39.780599256564', "pla_catast" = '07025A00100316' where "pla_codi" = 611;
update "public"."oli_plantacio" set "pla_coordx" = '2.73370594272839', "pla_coordy" = '39.7802543430535', "pla_catast" = '07025A00100317' where "pla_codi" = 612;
update "public"."oli_plantacio" set "pla_coordx" = '2.73877383325401', "pla_coordy" = '39.7838382960731', "pla_catast" = '07025A00100298' where "pla_codi" = 614;
update "public"."oli_plantacio" set "pla_coordx" = '2.73906220453516', "pla_coordy" = '39.7828381917703', "pla_catast" = '07025A00100906' where "pla_codi" = 615;
update "public"."oli_plantacio" set "pla_coordx" = '2.70844763910528', "pla_coordy" = '39.7919669452077', "pla_catast" = '07061A00200348' where "pla_codi" = 617;
update "public"."oli_plantacio" set "pla_coordx" = '2.75805620776712', "pla_coordy" = '39.79039099661', "pla_catast" = '07025A00100892' where "pla_codi" = 618;
update "public"."oli_plantacio" set "pla_coordx" = '2.59739598341943', "pla_coordy" = '39.6603436844629', "pla_catast" = '07020A00500086' where "pla_codi" = 622;
update "public"."oli_plantacio" set "pla_coordx" = '2.69464515398152', "pla_coordy" = '39.7744055964117', "pla_catast" = '07061A00500490' where "pla_codi" = 629;
update "public"."oli_plantacio" set "pla_coordx" = '2.75264081047976', "pla_coordy" = '39.7839853217385', "pla_catast" = '07025A00100256' where "pla_codi" = 625;
update "public"."oli_plantacio" set "pla_coordx" = '2.73691140241658', "pla_coordy" = '39.7889000181649', "pla_catast" = '07025A00100456' where "pla_codi" = 672;
update "public"."oli_plantacio" set "pla_coordx" = '2.751225296804', "pla_coordy" = '39.7897947262299', "pla_catast" = '07025A00100407' where "pla_codi" = 681;
update "public"."oli_plantacio" set "pla_coordx" = '3.31709527477245', "pla_coordy" = '39.5555506359732', "pla_catast" = '07033A01700040' where "pla_codi" = 700;
update "public"."oli_plantacio" set "pla_coordx" = '2.74372988629847', "pla_coordy" = '39.7818131623556', "pla_catast" = '07025A00100638' where "pla_codi" = 665;
update "public"."oli_plantacio" set "pla_coordx" = '2.74025280925237', "pla_coordy" = '39.7906402921048', "pla_catast" = '07025A00100464' where "pla_codi" = 626;
update "public"."oli_plantacio" set "pla_coordx" = '3.27274002820752', "pla_coordy" = '39.6093028370267', "pla_catast" = '07051A00500768' where "pla_codi" = 627;
update "public"."oli_plantacio" set "pla_coordx" = '2.69371129014537', "pla_coordy" = '39.7734751886671', "pla_catast" = '07061A00500484' where "pla_codi" = 630;
update "public"."oli_plantacio" set "pla_coordx" = '2.69402194670492', "pla_coordy" = '39.7732498552402', "pla_catast" = '07061A00500485' where "pla_codi" = 631;
update "public"."oli_plantacio" set "pla_coordx" = '2.69397573025923', "pla_coordy" = '39.7747493694474', "pla_catast" = '07061A00100322' where "pla_codi" = 633;
update "public"."oli_plantacio" set "pla_coordx" = '2.69355982242899', "pla_coordy" = '39.7742186602328', "pla_catast" = '07061A00100323' where "pla_codi" = 634;
update "public"."oli_plantacio" set "pla_coordx" = '2.69589836493602', "pla_coordy" = '39.7755571300864', "pla_catast" = '07061A00100557' where "pla_codi" = 636;
update "public"."oli_plantacio" set "pla_coordx" = '2.69589836493602', "pla_coordy" = '39.7755571300864', "pla_catast" = '07061A00100557' where "pla_codi" = 637;
update "public"."oli_plantacio" set "pla_coordx" = '2.76475428497911', "pla_coordy" = '39.7833363386254', "pla_catast" = '07025A00100083' where "pla_codi" = 639;
update "public"."oli_plantacio" set "pla_coordx" = '3.22177326212143', "pla_coordy" = '39.5405528869963', "pla_catast" = '07033A03400606' where "pla_codi" = 640;
update "public"."oli_plantacio" set "pla_coordx" = '2.75890687872589', "pla_coordy" = '39.7868545777763', "pla_catast" = '07025A00100396' where "pla_codi" = 642;
update "public"."oli_plantacio" set "pla_coordx" = '2.75981966981935', "pla_coordy" = '39.7820498279201', "pla_catast" = '07025A00100089' where "pla_codi" = 643;
update "public"."oli_plantacio" set "pla_coordx" = '2.72042614380456', "pla_coordy" = '39.5778010463343', "pla_catast" = '07040A03500012' where "pla_codi" = 645;
update "public"."oli_plantacio" set "pla_coordx" = '2.74482339770343', "pla_coordy" = '39.58424134611', "pla_catast" = '07040A03700031' where "pla_codi" = 646;
update "public"."oli_plantacio" set "pla_coordx" = '2.72106840940397', "pla_coordy" = '39.7822433381878', "pla_catast" = '07061A00400461' where "pla_codi" = 648;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 695;
update "public"."oli_plantacio" set "pla_coordx" = '2.6634286308902', "pla_coordy" = '39.7690803930817', "pla_catast" = '07018A00300033' where "pla_codi" = 651;
update "public"."oli_plantacio" set "pla_coordx" = '3.05171511012759', "pla_coordy" = '39.3865084505038', "pla_catast" = '07013A01600035' where "pla_codi" = 653;
update "public"."oli_plantacio" set "pla_coordx" = '2.88632640755399', "pla_coordy" = '39.7592575349672', "pla_catast" = '07034A00100012' where "pla_codi" = 655;
update "public"."oli_plantacio" set "pla_coordx" = '2.72250390844768', "pla_coordy" = '39.7528511845002', "pla_catast" = '07061A00402254' where "pla_codi" = 657;
update "public"."oli_plantacio" set "pla_coordx" = '2.72250352091421', "pla_coordy" = '39.753413956928', "pla_catast" = '07061A00402255' where "pla_codi" = 658;
update "public"."oli_plantacio" set "pla_coordx" = '2.63437627696074', "pla_coordy" = '39.7141838331519', "pla_catast" = '07063A00300098' where "pla_codi" = 660;
update "public"."oli_plantacio" set "pla_coordx" = '2.46107604544867', "pla_coordy" = '39.5837766448533', "pla_catast" = '07011A00300150' where "pla_codi" = 661;
update "public"."oli_plantacio" set "pla_coordx" = '2.74486385040081', "pla_coordy" = '39.782405016505', "pla_catast" = '07025A00100921' where "pla_codi" = 668;
update "public"."oli_plantacio" set "pla_coordx" = '2.72992438695815', "pla_coordy" = '39.7876071203019', "pla_catast" = '07025A00100028' where "pla_codi" = 670;
update "public"."oli_plantacio" set "pla_coordx" = '3.01525295688973', "pla_coordy" = '39.8685371243511', "pla_catast" = '07042A00100086' where "pla_codi" = 671;
update "public"."oli_plantacio" set "pla_coordx" = '2.73530747683978', "pla_coordy" = '39.7921137799175', "pla_catast" = '07025A00100900' where "pla_codi" = 674;
update "public"."oli_plantacio" set "pla_coordx" = '2.73727015071068', "pla_coordy" = '39.7810978936783', "pla_catast" = '07025A00100304' where "pla_codi" = 675;
update "public"."oli_plantacio" set "pla_coordx" = '3.13588891257323', "pla_coordy" = '39.72847621503', "pla_catast" = '07055A01200120' where "pla_codi" = 678;
update "public"."oli_plantacio" set "pla_coordx" = '3.13588891257323', "pla_coordy" = '39.72847621503', "pla_catast" = '07055A01200120' where "pla_codi" = 679;
update "public"."oli_plantacio" set "pla_coordx" = '2.71137083807715', "pla_coordy" = '39.7959928523666', "pla_catast" = '07061A00300382' where "pla_codi" = 683;
update "public"."oli_plantacio" set "pla_coordx" = '2.71180148566542', "pla_coordy" = '39.7964428066668', "pla_catast" = '07061A00300383' where "pla_codi" = 684;
update "public"."oli_plantacio" set "pla_coordx" = '2.71594616387449', "pla_coordy" = '39.8008221193591', "pla_catast" = '07061A00300425' where "pla_codi" = 686;
update "public"."oli_plantacio" set "pla_coordx" = '2.7071882113913', "pla_coordy" = '39.798092337733', "pla_catast" = '07061A00200602' where "pla_codi" = 688;
update "public"."oli_plantacio" set "pla_coordx" = '2.7356233910006', "pla_coordy" = '39.7851507733348', "pla_catast" = '07025A00100962' where "pla_codi" = 689;
update "public"."oli_plantacio" set "pla_coordx" = '2.84791902290008', "pla_coordy" = '39.7473522939373', "pla_catast" = '07034A00300051' where "pla_codi" = 691;
update "public"."oli_plantacio" set "pla_coordx" = '3.38746280373906', "pla_coordy" = '39.6632266876607', "pla_catast" = '07062A01900017' where "pla_codi" = 692;
update "public"."oli_plantacio" set "pla_coordx" = '3.26830812448694', "pla_coordy" = '39.5495361921076', "pla_catast" = '07033A03000027' where "pla_codi" = 694;
update "public"."oli_plantacio" set "pla_coordx" = '3.3487506483884', "pla_coordy" = '39.5707242061424', "pla_catast" = '07033A01200044' where "pla_codi" = 696;
update "public"."oli_plantacio" set "pla_coordx" = '2.71579632705561', "pla_coordy" = '39.7892462303567', "pla_catast" = '07061A00300241' where "pla_codi" = 697;
update "public"."oli_plantacio" set "pla_coordx" = '2.74306314348689', "pla_coordy" = '39.7815749001771', "pla_catast" = '07025A00100592' where "pla_codi" = 664;
update "public"."oli_plantacio" set "pla_coordx" = '2.70514329680947', "pla_coordy" = '39.7639850025318', "pla_catast" = '07061A00500204' where "pla_codi" = 737;
update "public"."oli_plantacio" set "pla_coordx" = '3.08653669741322', "pla_coordy" = '39.7519651235894', "pla_catast" = '07039A01100109' where "pla_codi" = 752;
update "public"."oli_plantacio" set "pla_coordx" = '2.719841017705', "pla_coordy" = '39.7897477610658', "pla_catast" = '07061A00300179' where "pla_codi" = 703;
update "public"."oli_plantacio" set "pla_coordx" = '2.71987431187388', "pla_coordy" = '39.7878427501772', "pla_catast" = '07061A00300484' where "pla_codi" = 704;
update "public"."oli_plantacio" set "pla_coordx" = '2.73452179903762', "pla_coordy" = '39.7805140837439', "pla_catast" = '07025A00100315' where "pla_codi" = 706;
update "public"."oli_plantacio" set "pla_coordx" = '2.63736766605441', "pla_coordy" = '39.756830977507', "pla_catast" = '07018A00200448' where "pla_codi" = 709;
update "public"."oli_plantacio" set "pla_coordx" = '2.75212088975491', "pla_coordy" = '39.7842136980557', "pla_catast" = '07025A00100258' where "pla_codi" = 710;
update "public"."oli_plantacio" set "pla_coordx" = '3.3900041131417', "pla_coordy" = '39.6285208963826', "pla_catast" = '07062A01800129' where "pla_codi" = 711;
update "public"."oli_plantacio" set "pla_coordx" = '0', "pla_coordy" = '0', "pla_catast" = '07039A01100108' where "pla_codi" = 751;
update "public"."oli_plantacio" set "pla_coordx" = '2.75722485563259', "pla_coordy" = '39.7802396827796', "pla_catast" = '07025A00100110' where "pla_codi" = 714;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 731;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 732;
update "public"."oli_plantacio" set "pla_coordx" = '2.72489066329932', "pla_coordy" = '39.7847871027429', "pla_catast" = '07025A00100937' where "pla_codi" = 716;
update "public"."oli_plantacio" set "pla_coordx" = '2.89580485846187', "pla_coordy" = '39.7759390784142', "pla_catast" = '07058A01200033' where "pla_codi" = 717;
update "public"."oli_plantacio" set "pla_coordx" = '2.85626826708383', "pla_coordy" = '39.7210102321154', "pla_catast" = '07029A00100048' where "pla_codi" = 719;
update "public"."oli_plantacio" set "pla_coordx" = '2.73961621404486', "pla_coordy" = '39.773558465323', "pla_catast" = '07025A00100203' where "pla_codi" = 722;
update "public"."oli_plantacio" set "pla_coordx" = '3.15650477987871', "pla_coordy" = '39.3331757538803', "pla_catast" = '07057A00300745' where "pla_codi" = 725;
update "public"."oli_plantacio" set "pla_coordx" = '2.90038419038737', "pla_coordy" = '39.6921830010424', "pla_catast" = '07027A00100203' where "pla_codi" = 726;
update "public"."oli_plantacio" set "pla_coordx" = '3.07452411784628', "pla_coordy" = '39.7121858871771', "pla_catast" = '07039A00500353' where "pla_codi" = 728;
update "public"."oli_plantacio" set "pla_coordx" = '2.89993108910062', "pla_coordy" = '39.551300772202', "pla_catast" = '07004A02400041' where "pla_codi" = 729;
update "public"."oli_plantacio" set "pla_coordx" = '3.22676713218208', "pla_coordy" = '39.3914205860406', "pla_catast" = '07022A03100340' where "pla_codi" = 733;
update "public"."oli_plantacio" set "pla_coordx" = '2.64958976378521', "pla_coordy" = '39.7634822039141', "pla_catast" = '07018A00300032' where "pla_codi" = 734;
update "public"."oli_plantacio" set "pla_coordx" = '2.97481015773883', "pla_coordy" = '39.5687022526923', "pla_catast" = '07038A00500028' where "pla_codi" = 736;
update "public"."oli_plantacio" set "pla_coordx" = '2.70465073069553', "pla_coordy" = '39.7641225963128', "pla_catast" = '07061A00500205' where "pla_codi" = 738;
update "public"."oli_plantacio" set "pla_coordx" = '2.70352590018087', "pla_coordy" = '39.7636487721305', "pla_catast" = '07061A00500303' where "pla_codi" = 739;
update "public"."oli_plantacio" set "pla_coordx" = '2.70256279875386', "pla_coordy" = '39.7644473065548', "pla_catast" = '07061A00500298' where "pla_codi" = 741;
update "public"."oli_plantacio" set "pla_coordx" = '2.70286832934437', "pla_coordy" = '39.7664872528885', "pla_catast" = '07061A00500153' where "pla_codi" = 744;
update "public"."oli_plantacio" set "pla_coordx" = '2.7027426381792', "pla_coordy" = '39.7659316392448', "pla_catast" = '07061A00500294' where "pla_codi" = 745;
update "public"."oli_plantacio" set "pla_coordx" = '3.05110959027766', "pla_coordy" = '39.6784126623806', "pla_catast" = '07035A00200971' where "pla_codi" = 747;
update "public"."oli_plantacio" set "pla_coordx" = '2.75661141577524', "pla_coordy" = '39.7796445359616', "pla_catast" = '07025A00100122' where "pla_codi" = 748;
update "public"."oli_plantacio" set "pla_coordx" = '3.08346209056136', "pla_coordy" = '39.7482234638589', "pla_catast" = '07039A01100107' where "pla_codi" = 750;
update "public"."oli_plantacio" set "pla_coordx" = '3.08385035949627', "pla_coordy" = '39.7499887292624', "pla_catast" = '07039A01100082' where "pla_codi" = 754;
update "public"."oli_plantacio" set "pla_coordx" = '2.76029737640302', "pla_coordy" = '39.7837141832387', "pla_catast" = '07025A00100086' where "pla_codi" = 755;
update "public"."oli_plantacio" set "pla_coordx" = '2.65826243968194', "pla_coordy" = '39.7689240490564', "pla_catast" = '07018A00300028' where "pla_codi" = 758;
update "public"."oli_plantacio" set "pla_coordx" = '2.72083831285827', "pla_coordy" = '39.7884453283712', "pla_catast" = '07061A00300250' where "pla_codi" = 760;
update "public"."oli_plantacio" set "pla_coordx" = '2.70340360955689', "pla_coordy" = '39.7510746773513', "pla_catast" = '07061A00500212' where "pla_codi" = 761;
update "public"."oli_plantacio" set "pla_coordx" = '2.62849587321628', "pla_coordy" = '39.7438626990074', "pla_catast" = '07018A00200427' where "pla_codi" = 762;
update "public"."oli_plantacio" set "pla_coordx" = '2.64682926447108', "pla_coordy" = '39.7432374187219', "pla_catast" = '07018A00200073' where "pla_codi" = 764;
update "public"."oli_plantacio" set "pla_coordx" = '2.74968197236345', "pla_coordy" = '39.7806951278555', "pla_catast" = '07025A00100696' where "pla_codi" = 765;
update "public"."oli_plantacio" set "pla_coordx" = '2.90295111645471', "pla_coordy" = '39.7456530345841', "pla_catast" = '07058A01400027' where "pla_codi" = 767;
update "public"."oli_plantacio" set "pla_coordx" = '2.71715742229608', "pla_coordy" = '39.5824357788441', "pla_catast" = '07040A03200005' where "pla_codi" = 768;
update "public"."oli_plantacio" set "pla_coordx" = '2.72207092540032', "pla_coordy" = '39.7843751913834', "pla_catast" = '07061A00300204' where "pla_codi" = 770;
update "public"."oli_plantacio" set "pla_coordx" = '2.72310876627107', "pla_coordy" = '39.7816074317944', "pla_catast" = '07061A00400467' where "pla_codi" = 771;
update "public"."oli_plantacio" set "pla_coordx" = '2.71812617607747', "pla_coordy" = '39.7829500904015', "pla_catast" = '07061A00300221' where "pla_codi" = 774;
update "public"."oli_plantacio" set "pla_coordx" = '2.71362502250272', "pla_coordy" = '39.7737677803619', "pla_catast" = '07061A00400425' where "pla_codi" = 775;
update "public"."oli_plantacio" set "pla_coordx" = '2.71312138937292', "pla_coordy" = '39.7908073908184', "pla_catast" = '07061A00200361' where "pla_codi" = 777;
update "public"."oli_plantacio" set "pla_coordx" = '2.71424854552641', "pla_coordy" = '39.7903053414583', "pla_catast" = '07061A00300245' where "pla_codi" = 778;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 713;
update "public"."oli_plantacio" set "pla_coordx" = '3.06968951090041', "pla_coordy" = '39.8751867011114', "pla_catast" = '07042A00300761' where "pla_codi" = 781;
update "public"."oli_plantacio" set "pla_coordx" = '2.72866997399556', "pla_coordy" = '39.7745899021285', "pla_catast" = '07061A00400518' where "pla_codi" = 785;
update "public"."oli_plantacio" set "pla_coordx" = '2.72832352428025', "pla_coordy" = '39.7757800481127', "pla_catast" = '07061A00400517' where "pla_codi" = 786;
update "public"."oli_plantacio" set "pla_coordx" = '2.99888241005334', "pla_coordy" = '39.5675577495881', "pla_catast" = '07038A01000161' where "pla_codi" = 824;
update "public"."oli_plantacio" set "pla_coordx" = '2.99864119430254', "pla_coordy" = '39.5675650457038', "pla_catast" = '07038A01000162' where "pla_codi" = 825;
update "public"."oli_plantacio" set "pla_coordx" = '2.99819357130645', "pla_coordy" = '39.5675836915857', "pla_catast" = '07038A01000164' where "pla_codi" = 827;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 797;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 798;
update "public"."oli_plantacio" set "pla_coordx" = '3.05933830193506', "pla_coordy" = '39.410115480417', "pla_catast" = '07013A01300598' where "pla_codi" = 829;
update "public"."oli_plantacio" set "pla_coordx" = '3.0064095446249', "pla_coordy" = '39.391973237603', "pla_catast" = '07013A01900486' where "pla_codi" = 838;
update "public"."oli_plantacio" set "pla_coordx" = '3.0692155529705', "pla_coordy" = '39.8763299781794', "pla_catast" = '07042A00300752' where "pla_codi" = 779;
update "public"."oli_plantacio" set "pla_coordx" = '2.78684197049192', "pla_coordy" = '39.6977357009202', "pla_catast" = '07001A00400042' where "pla_codi" = 788;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 813;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 814;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 815;
update "public"."oli_plantacio" set "pla_coordx" = '2.87027718519188', "pla_coordy" = '39.7577507934575', "pla_catast" = '07034A00300352' where "pla_codi" = 789;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 821;
update "public"."oli_plantacio" set "pla_coordx" = '2.71005901100563', "pla_coordy" = '39.789755049766', "pla_catast" = '07061A00200353' where "pla_codi" = 791;
update "public"."oli_plantacio" set "pla_coordx" = '2.70993673984389', "pla_coordy" = '39.7907269301966', "pla_catast" = '07061A00200498' where "pla_codi" = 792;
update "public"."oli_plantacio" set "pla_coordx" = '2.95131001885812', "pla_coordy" = '39.4107754671535', "pla_catast" = '07013A00100087' where "pla_codi" = 794;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 837;
update "public"."oli_plantacio" set "pla_coordx" = '2.72342755976743', "pla_coordy" = '39.7645493506261', "pla_catast" = '07061A00401262' where "pla_codi" = 795;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 844;
update "public"."oli_plantacio" set "pla_coordx" = '2.72239455663842', "pla_coordy" = '39.7901304390916', "pla_catast" = '07061A00300186' where "pla_codi" = 799;
update "public"."oli_plantacio" set "pla_coordx" = '2.72248212267608', "pla_coordy" = '39.7909819218587', "pla_catast" = '07061A00300187' where "pla_codi" = 800;
update "public"."oli_plantacio" set "pla_coordx" = '2.73535172546764', "pla_coordy" = '39.7861338770266', "pla_catast" = '07025A00100871' where "pla_codi" = 803;
update "public"."oli_plantacio" set "pla_coordx" = '2.73733408186802', "pla_coordy" = '39.7857287877813', "pla_catast" = '07025A00100963' where "pla_codi" = 804;
update "public"."oli_plantacio" set "pla_coordx" = '2.90557277557372', "pla_coordy" = '39.4550584427686', "pla_catast" = '07031A02300009' where "pla_codi" = 806;
update "public"."oli_plantacio" set "pla_coordx" = '3.10495477437299', "pla_coordy" = '39.3830465702625', "pla_catast" = '07057A00100419' where "pla_codi" = 807;
update "public"."oli_plantacio" set "pla_coordx" = '2.67697629696485', "pla_coordy" = '39.6641603711422', "pla_catast" = '07010A00100029' where "pla_codi" = 810;
update "public"."oli_plantacio" set "pla_coordx" = '3.20468326751264', "pla_coordy" = '39.5327132832442', "pla_catast" = '07033A03400436' where "pla_codi" = 811;
update "public"."oli_plantacio" set "pla_coordx" = '2.98654055762972', "pla_coordy" = '39.7054508718434', "pla_catast" = '07027A00600144' where "pla_codi" = 816;
update "public"."oli_plantacio" set "pla_coordx" = '2.98780055748634', "pla_coordy" = '39.7037056454752', "pla_catast" = '07027A00600145' where "pla_codi" = 817;
update "public"."oli_plantacio" set "pla_coordx" = '3.41915744022842', "pla_coordy" = '39.7227351650112', "pla_catast" = '07014A00400180' where "pla_codi" = 819;
update "public"."oli_plantacio" set "pla_coordx" = '2.99938812277561', "pla_coordy" = '39.5671423747819', "pla_catast" = '07038A01000159' where "pla_codi" = 823;
update "public"."oli_plantacio" set "pla_coordx" = '3.05905584674475', "pla_coordy" = '39.4098845918299', "pla_catast" = '07013A01300599' where "pla_codi" = 830;
update "public"."oli_plantacio" set "pla_coordx" = '2.8789008144435', "pla_coordy" = '39.7256704727538', "pla_catast" = '07029A00100938' where "pla_codi" = 831;
update "public"."oli_plantacio" set "pla_coordx" = '2.76206912553566', "pla_coordy" = '39.6841856467168', "pla_catast" = '07056A00800334' where "pla_codi" = 833;
update "public"."oli_plantacio" set "pla_coordx" = '3.30169492195875', "pla_coordy" = '39.5831391676108', "pla_catast" = '07051A00200253' where "pla_codi" = 835;
update "public"."oli_plantacio" set "pla_coordx" = '3.00528278669696', "pla_coordy" = '39.3922070294062', "pla_catast" = '07013A01900489' where "pla_codi" = 839;
update "public"."oli_plantacio" set "pla_coordx" = '3.07572555063502', "pla_coordy" = '39.7170822672182', "pla_catast" = '07039A00600240' where "pla_codi" = 841;
update "public"."oli_plantacio" set "pla_coordx" = '3.06681191952137', "pla_coordy" = '39.7216887066268', "pla_catast" = '07039A00600038' where "pla_codi" = 842;
update "public"."oli_plantacio" set "pla_coordx" = '3.0581551060833', "pla_coordy" = '39.7127566320257', "pla_catast" = '07039A00500171' where "pla_codi" = 843;
update "public"."oli_plantacio" set "pla_coordx" = '3.37791254877314', "pla_coordy" = '39.7000262662583', "pla_catast" = '07006A01700123' where "pla_codi" = 846;
update "public"."oli_plantacio" set "pla_coordx" = '3.37706667385907', "pla_coordy" = '39.7003869873034', "pla_catast" = '07006A01700320' where "pla_codi" = 847;
update "public"."oli_plantacio" set "pla_coordx" = '3.19168820974515', "pla_coordy" = '39.5943655426678', "pla_catast" = '07033A02800303' where "pla_codi" = 849;
update "public"."oli_plantacio" set "pla_coordx" = '2.90214338290007', "pla_coordy" = '39.6679657390378', "pla_catast" = '07047A00400203' where "pla_codi" = 850;
update "public"."oli_plantacio" set "pla_coordx" = '2.64366405668838', "pla_coordy" = '39.7563338075231', "pla_catast" = '07018A00300055' where "pla_codi" = 851;
update "public"."oli_plantacio" set "pla_coordx" = '2.7235747092268', "pla_coordy" = '39.7586907749447', "pla_catast" = '07061A00401846' where "pla_codi" = 853;
update "public"."oli_plantacio" set "pla_coordx" = '3.06595008184781', "pla_coordy" = '39.7722094355938', "pla_catast" = '07039A00400326' where "pla_codi" = 859;
update "public"."oli_plantacio" set "pla_coordx" = '2.62922620978204', "pla_coordy" = '39.7360026644012', "pla_catast" = '07018A00200382' where "pla_codi" = 910;
update "public"."oli_plantacio" set "pla_coordx" = '3.01803696531151', "pla_coordy" = '39.509152042237', "pla_catast" = '07043A01300308' where "pla_codi" = 915;
update "public"."oli_plantacio" set "pla_coordx" = '3.0198138963397', "pla_coordy" = '39.5094985635365', "pla_catast" = '07043A01300342' where "pla_codi" = 916;
update "public"."oli_plantacio" set "pla_coordx" = '2.80483794199857', "pla_coordy" = '39.5659160846248', "pla_catast" = '07040A04500158' where "pla_codi" = 919;
update "public"."oli_plantacio" set "pla_coordx" = '2.7248319154465', "pla_coordy" = '39.7602689125704', "pla_catast" = '07061A00401853' where "pla_codi" = 856;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 871;
update "public"."oli_plantacio" set "pla_coordx" = '2.97120233831716', "pla_coordy" = '39.5485184039581', "pla_catast" = '07038A00300030' where "pla_codi" = 858;
update "public"."oli_plantacio" set "pla_coordx" = '3.06589395234472', "pla_coordy" = '39.7734629599002', "pla_catast" = '07039A00400324' where "pla_codi" = 861;
update "public"."oli_plantacio" set "pla_coordx" = '3.00710822108862', "pla_coordy" = '39.7561205750005', "pla_catast" = '07044A00800230' where "pla_codi" = 862;
update "public"."oli_plantacio" set "pla_coordx" = '3.07768675345276', "pla_coordy" = '39.7748002448649', "pla_catast" = '07039A00401527' where "pla_codi" = 864;
update "public"."oli_plantacio" set "pla_coordx" = '3.07726154886988', "pla_coordy" = '39.7752718494945', "pla_catast" = '07039A00400284' where "pla_codi" = 865;
update "public"."oli_plantacio" set "pla_coordx" = '2.82756685017049', "pla_coordy" = '39.7345856428553', "pla_catast" = '07029A00100002' where "pla_codi" = 868;
update "public"."oli_plantacio" set "pla_coordx" = '2.80392640719895', "pla_coordy" = '39.6724641769847', "pla_catast" = '07016A00100229' where "pla_codi" = 869;
update "public"."oli_plantacio" set "pla_coordx" = '2.85965365036988', "pla_coordy" = '39.8340289651475', "pla_catast" = '07019A00300028' where "pla_codi" = 872;
update "public"."oli_plantacio" set "pla_coordx" = '2.98858880845927', "pla_coordy" = '39.7877499314618', "pla_catast" = '07012A01200001' where "pla_codi" = 873;
update "public"."oli_plantacio" set "pla_coordx" = '2.7181858576086', "pla_coordy" = '39.7848975852518', "pla_catast" = '07061A00300232' where "pla_codi" = 875;
update "public"."oli_plantacio" set "pla_coordx" = '3.01494228762985', "pla_coordy" = '39.371219912637', "pla_catast" = '07013A01800564' where "pla_codi" = 876;
update "public"."oli_plantacio" set "pla_coordx" = '3.13480515854679', "pla_coordy" = '39.6254021521657', "pla_catast" = '07041A00700235' where "pla_codi" = 878;
update "public"."oli_plantacio" set "pla_coordx" = '2.57663646332299', "pla_coordy" = '39.6805404696487', "pla_catast" = '07020A00300039' where "pla_codi" = 879;
update "public"."oli_plantacio" set "pla_coordx" = '3.17821393004373', "pla_coordy" = '39.5479445428611', "pla_catast" = '07033A02501643' where "pla_codi" = 882;
update "public"."oli_plantacio" set "pla_coordx" = '3.17768376075042', "pla_coordy" = '39.5482998275436', "pla_catast" = '07033A02500495' where "pla_codi" = 883;
update "public"."oli_plantacio" set "pla_coordx" = '2.68678523659418', "pla_coordy" = '39.7765271539136', "pla_catast" = '07061A00100410' where "pla_codi" = 885;
update "public"."oli_plantacio" set "pla_coordx" = '2.68608274603132', "pla_coordy" = '39.7770167545075', "pla_catast" = '07061A00100411' where "pla_codi" = 886;
update "public"."oli_plantacio" set "pla_coordx" = '2.68561568207991', "pla_coordy" = '39.7761905293225', "pla_catast" = '07061A00100412' where "pla_codi" = 887;
update "public"."oli_plantacio" set "pla_coordx" = '2.6871087787304', "pla_coordy" = '39.7759910271139', "pla_catast" = '07061A00100407' where "pla_codi" = 889;
update "public"."oli_plantacio" set "pla_coordx" = '3.00700936661248', "pla_coordy" = '39.7937743170129', "pla_catast" = '07044A00200021' where "pla_codi" = 890;
update "public"."oli_plantacio" set "pla_coordx" = '3.09328385591706', "pla_coordy" = '39.7266739632787', "pla_catast" = '07055A00300336' where "pla_codi" = 892;
update "public"."oli_plantacio" set "pla_coordx" = '3.06238932354565', "pla_coordy" = '39.7786924063501', "pla_catast" = '07044A00300016' where "pla_codi" = 893;
update "public"."oli_plantacio" set "pla_coordx" = '3.06153062835859', "pla_coordy" = '39.7790205643117', "pla_catast" = '07044A00300633' where "pla_codi" = 895;
update "public"."oli_plantacio" set "pla_coordx" = '3.06179628814216', "pla_coordy" = '39.7792778428176', "pla_catast" = '07044A00300634' where "pla_codi" = 896;
update "public"."oli_plantacio" set "pla_coordx" = '2.99378026097504', "pla_coordy" = '39.4422562360425', "pla_catast" = '07013A00600054' where "pla_codi" = 899;
update "public"."oli_plantacio" set "pla_coordx" = '2.99349972186689', "pla_coordy" = '39.4434774265083', "pla_catast" = '07013A00600057' where "pla_codi" = 900;
update "public"."oli_plantacio" set "pla_coordx" = '2.99297448639527', "pla_coordy" = '39.443114089508', "pla_catast" = '07013A00600059' where "pla_codi" = 902;
update "public"."oli_plantacio" set "pla_coordx" = '2.99248117251346', "pla_coordy" = '39.4431051387157', "pla_catast" = '07013A00600060' where "pla_codi" = 903;
update "public"."oli_plantacio" set "pla_coordx" = '2.90796761550421', "pla_coordy" = '39.7414869388188', "pla_catast" = '07058A01400057' where "pla_codi" = 906;
update "public"."oli_plantacio" set "pla_coordx" = '2.90796761550421', "pla_coordy" = '39.7414869388188', "pla_catast" = '07058A01400057' where "pla_codi" = 907;
update "public"."oli_plantacio" set "pla_coordx" = '2.62436197472636', "pla_coordy" = '39.7351673103128', "pla_catast" = '07018A00200384' where "pla_codi" = 911;
update "public"."oli_plantacio" set "pla_coordx" = '3.01785735528089', "pla_coordy" = '39.5091226059718', "pla_catast" = '07043A01300307' where "pla_codi" = 914;
update "public"."oli_plantacio" set "pla_coordx" = '3.15193144425924', "pla_coordy" = '39.6621452944861', "pla_catast" = '07066A00500116' where "pla_codi" = 920;
update "public"."oli_plantacio" set "pla_coordx" = '3.37301547732075', "pla_coordy" = '39.6309570127119', "pla_catast" = '07062A00400358' where "pla_codi" = 923;
update "public"."oli_plantacio" set "pla_coordx" = '3.37251085780439', "pla_coordy" = '39.6304227946113', "pla_catast" = '07062A00400372' where "pla_codi" = 924;
update "public"."oli_plantacio" set "pla_coordx" = '3.37251073669706', "pla_coordy" = '39.6301404142591', "pla_catast" = '07062A00400374' where "pla_codi" = 926;
update "public"."oli_plantacio" set "pla_coordx" = '2.46604012396734', "pla_coordy" = '39.5898105140172', "pla_catast" = '07011A00300145' where "pla_codi" = 927;
update "public"."oli_plantacio" set "pla_coordx" = '3.06067251001476', "pla_coordy" = '39.7150543134423', "pla_catast" = '07039A00500164' where "pla_codi" = 930;
update "public"."oli_plantacio" set "pla_coordx" = '3.07440469426171', "pla_coordy" = '39.7209114760539', "pla_catast" = '07039A00600192' where "pla_codi" = 931;
update "public"."oli_plantacio" set "pla_coordx" = '2.81442572692283', "pla_coordy" = '39.7186515061512', "pla_catast" = '07001A00200053' where "pla_codi" = 905;
update "public"."oli_plantacio" set "pla_coordx" = '3.06377657578228', "pla_coordy" = '39.7782910603945', "pla_catast" = '07044A00300009' where "pla_codi" = 938;
update "public"."oli_plantacio" set "pla_coordx" = '3.17529348068434', "pla_coordy" = '39.5486153103201', "pla_catast" = '07033A02501000' where "pla_codi" = 947;
update "public"."oli_plantacio" set "pla_coordx" = '2.71549150810417', "pla_coordy" = '39.7949512077501', "pla_catast" = '07061A00300372' where "pla_codi" = 965;
update "public"."oli_plantacio" set "pla_coordx" = '3.09548716897522', "pla_coordy" = '39.6316564882635', "pla_catast" = '07041A00200294' where "pla_codi" = 995;
update "public"."oli_plantacio" set "pla_coordx" = '3.13944823985715', "pla_coordy" = '39.6457420626798', "pla_catast" = '07066A00600489' where "pla_codi" = 1006;
update "public"."oli_plantacio" set "pla_coordx" = '2.66529582300572', "pla_coordy" = '39.6276853397054', "pla_catast" = '07040A02300118' where "pla_codi" = 935;
update "public"."oli_plantacio" set "pla_coordx" = '2.96212982816613', "pla_coordy" = '39.5578634332563', "pla_catast" = '07004A01400038' where "pla_codi" = 940;
update "public"."oli_plantacio" set "pla_coordx" = '2.96353590558853', "pla_coordy" = '39.5584214423052', "pla_catast" = '07004A01400034' where "pla_codi" = 941;
update "public"."oli_plantacio" set "pla_coordx" = '2.96952251148819', "pla_coordy" = '39.6854620885641', "pla_catast" = '07027A00500214' where "pla_codi" = 943;
update "public"."oli_plantacio" set "pla_coordx" = '2.62509831170024', "pla_coordy" = '39.6925823410338', "pla_catast" = '07063A00200085' where "pla_codi" = 944;
update "public"."oli_plantacio" set "pla_coordx" = '2.99698467870299', "pla_coordy" = '39.5494522300405', "pla_catast" = '07038A01300311' where "pla_codi" = 946;
update "public"."oli_plantacio" set "pla_coordx" = '3.17463852591132', "pla_coordy" = '39.5497148560364', "pla_catast" = '07033A02501002' where "pla_codi" = 948;
update "public"."oli_plantacio" set "pla_coordx" = '3.1753798007627', "pla_coordy" = '39.5496164265393', "pla_catast" = '07033A02500998' where "pla_codi" = 950;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 978;
update "public"."oli_plantacio" set "pla_coordx" = '2.61526762629095', "pla_coordy" = '39.7208996720332', "pla_catast" = '07063A00300163' where "pla_codi" = 951;
update "public"."oli_plantacio" set "pla_coordx" = '3.07982072793099', "pla_coordy" = '39.7483464883659', "pla_catast" = '07039A01200325' where "pla_codi" = 953;
update "public"."oli_plantacio" set "pla_coordx" = '3.06281492019598', "pla_coordy" = '39.7370711962404', "pla_catast" = '07039A01300323' where "pla_codi" = 954;
update "public"."oli_plantacio" set "pla_coordx" = '3.08058943865895', "pla_coordy" = '39.7656893637986', "pla_catast" = '07039A00400358' where "pla_codi" = 957;
update "public"."oli_plantacio" set "pla_coordx" = '3.06281492019598', "pla_coordy" = '39.7370711962404', "pla_catast" = '07039A01300323' where "pla_codi" = 958;
update "public"."oli_plantacio" set "pla_coordx" = '3.06300880288453', "pla_coordy" = '39.7379972506429', "pla_catast" = '07039A01300325' where "pla_codi" = 960;
update "public"."oli_plantacio" set "pla_coordx" = '3.0638287586657', "pla_coordy" = '39.7369815335917', "pla_catast" = '07039A01300377' where "pla_codi" = 961;
update "public"."oli_plantacio" set "pla_coordx" = '3.08058943865895', "pla_coordy" = '39.7656893637986', "pla_catast" = '07039A00400358' where "pla_codi" = 962;
update "public"."oli_plantacio" set "pla_coordx" = '2.98060084231295', "pla_coordy" = '39.5552598263064', "pla_catast" = '07038A00200342' where "pla_codi" = 963;
update "public"."oli_plantacio" set "pla_coordx" = '2.67653730922873', "pla_coordy" = '39.7727513130286', "pla_catast" = '07061A00500172' where "pla_codi" = 967;
update "public"."oli_plantacio" set "pla_coordx" = '2.59060613263006', "pla_coordy" = '39.7060663788096', "pla_catast" = '07063A00100106' where "pla_codi" = 969;
update "public"."oli_plantacio" set "pla_coordx" = '3.05570214247212', "pla_coordy" = '39.4424228217011', "pla_catast" = '07013A01000394' where "pla_codi" = 970;
update "public"."oli_plantacio" set "pla_coordx" = '3.05685609163597', "pla_coordy" = '39.4439646928818', "pla_catast" = '07013A01000398' where "pla_codi" = 973;
update "public"."oli_plantacio" set "pla_coordx" = '3.05725137115686', "pla_coordy" = '39.4450044092541', "pla_catast" = '07013A01000399' where "pla_codi" = 974;
update "public"."oli_plantacio" set "pla_coordx" = '3.0581444137612', "pla_coordy" = '39.4429606357913', "pla_catast" = '07013A01000495' where "pla_codi" = 976;
update "public"."oli_plantacio" set "pla_coordx" = '2.81801432098477', "pla_coordy" = '39.7572315091625', "pla_catast" = '07001A00200110' where "pla_codi" = 977;
update "public"."oli_plantacio" set "pla_coordx" = '2.79513176000354', "pla_coordy" = '39.6845308070543', "pla_catast" = '07001A00400345' where "pla_codi" = 980;
update "public"."oli_plantacio" set "pla_coordx" = '2.80107780346104', "pla_coordy" = '39.6795354949739', "pla_catast" = '07001A00400300' where "pla_codi" = 981;
update "public"."oli_plantacio" set "pla_coordx" = '3.17997805646861', "pla_coordy" = '39.576064423176', "pla_catast" = '07033A02700603' where "pla_codi" = 983;
update "public"."oli_plantacio" set "pla_coordx" = '3.26570318402293', "pla_coordy" = '39.593116182031', "pla_catast" = '07033A00600587' where "pla_codi" = 984;
update "public"."oli_plantacio" set "pla_coordx" = '3.26441895476212', "pla_coordy" = '39.5934852949176', "pla_catast" = '07033A00600284' where "pla_codi" = 985;
update "public"."oli_plantacio" set "pla_coordx" = '2.73452526102967', "pla_coordy" = '39.7350957224195', "pla_catast" = '07010A00700184' where "pla_codi" = 988;
update "public"."oli_plantacio" set "pla_coordx" = '2.99744130670767', "pla_coordy" = '39.7623785161723', "pla_catast" = '07009A00400077' where "pla_codi" = 990;
update "public"."oli_plantacio" set "pla_coordx" = '3.07440469426171', "pla_coordy" = '39.7209114760539', "pla_catast" = '07039A00600192' where "pla_codi" = 991;
update "public"."oli_plantacio" set "pla_coordx" = '3.06804036969645', "pla_coordy" = '39.7211699072244', "pla_catast" = '07039A00600045' where "pla_codi" = 992;
update "public"."oli_plantacio" set "pla_coordx" = '3.127979588578', "pla_coordy" = '39.5617423340329', "pla_catast" = '07065A00400424' where "pla_codi" = 994;
update "public"."oli_plantacio" set "pla_coordx" = '3.09564941273041', "pla_coordy" = '39.6316013928182', "pla_catast" = '07041A00200295' where "pla_codi" = 996;
update "public"."oli_plantacio" set "pla_coordx" = '2.9136010909147', "pla_coordy" = '39.465231511278', "pla_catast" = '07031A02100077' where "pla_codi" = 998;
update "public"."oli_plantacio" set "pla_coordx" = '2.40544687208723', "pla_coordy" = '39.587052223747', "pla_catast" = '07005A01500229' where "pla_codi" = 999;
update "public"."oli_plantacio" set "pla_coordx" = '2.5946674614384', "pla_coordy" = '39.6611840099354', "pla_catast" = '07020A00500018' where "pla_codi" = 1001;
update "public"."oli_plantacio" set "pla_coordx" = '2.99684937868106', "pla_coordy" = '39.7217261873849', "pla_catast" = '07030A00300688' where "pla_codi" = 1003;
update "public"."oli_plantacio" set "pla_coordx" = '2.98099209096014', "pla_coordy" = '39.5442357963561', "pla_catast" = '07038A00300131' where "pla_codi" = 1004;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 934;
update "public"."oli_plantacio" set "pla_coordx" = '3.06229579833227', "pla_coordy" = '39.7155555980691', "pla_catast" = '07039A00501082' where "pla_codi" = 1054;
update "public"."oli_plantacio" set "pla_coordx" = '3.0624726122493', "pla_coordy" = '39.7153691716293', "pla_catast" = '07039A00500653' where "pla_codi" = 1055;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 1011;
update "public"."oli_plantacio" set "pla_coordx" = '3.0624726122493', "pla_coordy" = '39.7153691716293', "pla_catast" = '07039A00500653' where "pla_codi" = 1057;
update "public"."oli_plantacio" set "pla_coordx" = '3.23207043620111', "pla_coordy" = '39.55032728468', "pla_catast" = '07033A02900787' where "pla_codi" = 1060;
update "public"."oli_plantacio" set "pla_coordx" = '2.71873210023587', "pla_coordy" = '39.7597814445878', "pla_catast" = '07061A00401472' where "pla_codi" = 1061;
update "public"."oli_plantacio" set "pla_coordx" = '2.71890738163494', "pla_coordy" = '39.7600849696319', "pla_catast" = '07061A00401473' where "pla_codi" = 1062;
update "public"."oli_plantacio" set "pla_coordx" = '2.91235669130429', "pla_coordy" = '39.536397229514', "pla_catast" = '07004A02400328' where "pla_codi" = 1064;
update "public"."oli_plantacio" set "pla_coordx" = '2.90906934402082', "pla_coordy" = '39.5371955605415', "pla_catast" = '07004A02400443' where "pla_codi" = 1065;
update "public"."oli_plantacio" set "pla_coordx" = '2.4107480362867', "pla_coordy" = '39.5700397989029', "pla_catast" = '07005A01600546' where "pla_codi" = 1066;
update "public"."oli_plantacio" set "pla_coordx" = '2.59732047099863', "pla_coordy" = '39.7061031532326', "pla_catast" = '07063A00100102' where "pla_codi" = 1068;
update "public"."oli_plantacio" set "pla_coordx" = '2.59236986578153', "pla_coordy" = '39.7111114039951', "pla_catast" = '07063A00100103' where "pla_codi" = 1069;
update "public"."oli_plantacio" set "pla_coordx" = '2.60111290120832', "pla_coordy" = '39.7042403068061', "pla_catast" = '07063A00200003' where "pla_codi" = 1070;
update "public"."oli_plantacio" set "pla_coordx" = '2.74388635853636', "pla_coordy" = '39.7861074637582', "pla_catast" = '07025A00100492' where "pla_codi" = 1073;
update "public"."oli_plantacio" set "pla_coordx" = '3.1056339325257', "pla_coordy" = '39.7599822475961', "pla_catast" = '07039A01000196' where "pla_codi" = 1074;
update "public"."oli_plantacio" set "pla_coordx" = '2.63135930113742', "pla_coordy" = '39.7410410325115', "pla_catast" = '07018A00200369' where "pla_codi" = 1013;
update "public"."oli_plantacio" set "pla_coordx" = '2.62869578356253', "pla_coordy" = '39.7427562711724', "pla_catast" = '07018A00200372' where "pla_codi" = 1014;
update "public"."oli_plantacio" set "pla_coordx" = '2.68926440759347', "pla_coordy" = '39.7793274772715', "pla_catast" = '07061A00100396' where "pla_codi" = 1016;
update "public"."oli_plantacio" set "pla_coordx" = '2.72505664515624', "pla_coordy" = '39.6535250983177', "pla_catast" = '07036A00700220' where "pla_codi" = 1017;
update "public"."oli_plantacio" set "pla_coordx" = '2.68314911735842', "pla_coordy" = '39.7764304266586', "pla_catast" = '07061A00100609' where "pla_codi" = 1019;
update "public"."oli_plantacio" set "pla_coordx" = '2.6901331507657', "pla_coordy" = '39.7763692748175', "pla_catast" = '07061A00100398' where "pla_codi" = 1020;
update "public"."oli_plantacio" set "pla_coordx" = '2.9180235978225', "pla_coordy" = '39.4932932261489', "pla_catast" = '07031A01600269' where "pla_codi" = 1022;
update "public"."oli_plantacio" set "pla_coordx" = '2.86067575897971', "pla_coordy" = '39.6256529401759', "pla_catast" = '07047A01000179' where "pla_codi" = 1023;
update "public"."oli_plantacio" set "pla_coordx" = '2.85861836504691', "pla_coordy" = '39.7481079846172', "pla_catast" = '07034A00300250' where "pla_codi" = 1025;
update "public"."oli_plantacio" set "pla_coordx" = '2.85958311809452', "pla_coordy" = '39.7480916841829', "pla_catast" = '07034A00300255' where "pla_codi" = 1027;
update "public"."oli_plantacio" set "pla_coordx" = '2.85922586520844', "pla_coordy" = '39.7485376994357', "pla_catast" = '07034A00300416' where "pla_codi" = 1029;
update "public"."oli_plantacio" set "pla_coordx" = '2.85872283416831', "pla_coordy" = '39.74897461736', "pla_catast" = '07034A00300418' where "pla_codi" = 1030;
update "public"."oli_plantacio" set "pla_coordx" = '3.05094713058209', "pla_coordy" = '39.8204636386351', "pla_catast" = '07003A00600591' where "pla_codi" = 1032;
update "public"."oli_plantacio" set "pla_coordx" = '2.7048558308238', "pla_coordy" = '39.7606797282145', "pla_catast" = '07061A00500316' where "pla_codi" = 1033;
update "public"."oli_plantacio" set "pla_coordx" = '3.06006750789075', "pla_coordy" = '39.7063324414228', "pla_catast" = '07030A00500036' where "pla_codi" = 1034;
update "public"."oli_plantacio" set "pla_coordx" = '3.05514868017077', "pla_coordy" = '39.7060771789433', "pla_catast" = '07030A00400005' where "pla_codi" = 1036;
update "public"."oli_plantacio" set "pla_coordx" = '3.07541230696993', "pla_coordy" = '39.7164607642265', "pla_catast" = '07039A00500270' where "pla_codi" = 1037;
update "public"."oli_plantacio" set "pla_coordx" = '3.05576203565146', "pla_coordy" = '39.7056514269184', "pla_catast" = '07030A00400004' where "pla_codi" = 1039;
update "public"."oli_plantacio" set "pla_coordx" = '2.90039715531093', "pla_coordy" = '39.5645546088647', "pla_catast" = '07004A02600259' where "pla_codi" = 1040;
update "public"."oli_plantacio" set "pla_coordx" = '3.41819235304167', "pla_coordy" = '39.6865896237425', "pla_catast" = '07014A01200149' where "pla_codi" = 1043;
update "public"."oli_plantacio" set "pla_coordx" = '2.96291411821568', "pla_coordy" = '39.567124703948', "pla_catast" = '07038A00400166' where "pla_codi" = 1044;
update "public"."oli_plantacio" set "pla_coordx" = '3.03031918616256', "pla_coordy" = '39.7850227763932', "pla_catast" = '07044A01200195' where "pla_codi" = 1046;
update "public"."oli_plantacio" set "pla_coordx" = '2.77694291989575', "pla_coordy" = '39.6786389973665', "pla_catast" = '07056A00100116' where "pla_codi" = 1047;
update "public"."oli_plantacio" set "pla_coordx" = '2.71455809942007', "pla_coordy" = '39.7807291133836', "pla_catast" = '07061A00300026' where "pla_codi" = 1048;
update "public"."oli_plantacio" set "pla_coordx" = '2.70941795274302', "pla_coordy" = '39.6650212157808', "pla_catast" = '07010A00300208' where "pla_codi" = 1050;
update "public"."oli_plantacio" set "pla_coordx" = '3.05336464784793', "pla_coordy" = '39.3733177151078', "pla_catast" = '07013A01600048' where "pla_codi" = 1051;
update "public"."oli_plantacio" set "pla_coordx" = '3.01090341089182', "pla_coordy" = '39.7512202718943', "pla_catast" = '07044A00800221' where "pla_codi" = 1053;
update "public"."oli_plantacio" set "pla_coordx" = '3.02258933262154', "pla_coordy" = '39.8733389942047', "pla_catast" = '07042A00100153' where "pla_codi" = 1075;
update "public"."oli_plantacio" set "pla_coordx" = '2.88222334748328', "pla_coordy" = '39.6353939007103', "pla_catast" = '07047A01200099' where "pla_codi" = 1079;
update "public"."oli_plantacio" set "pla_coordx" = '2.88201139708174', "pla_coordy" = '39.6357213006422', "pla_catast" = '07047A01200108' where "pla_codi" = 1080;
update "public"."oli_plantacio" set "pla_coordx" = '3.074462688609', "pla_coordy" = '39.8722210940197', "pla_catast" = '07042A00300778' where "pla_codi" = 1082;
update "public"."oli_plantacio" set "pla_coordx" = '3.0987190489224', "pla_coordy" = '39.6307907952958', "pla_catast" = '07041A00200351' where "pla_codi" = 1009;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 1090;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 1098;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 1131;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 1157;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 1161;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 1163;
update "public"."oli_plantacio" set "pla_coordx" = '2.96968487610622', "pla_coordy" = '39.3687698810824', "pla_catast" = '07013A02100162' where "pla_codi" = 1086;
update "public"."oli_plantacio" set "pla_coordx" = '2.96994242429518', "pla_coordy" = '39.3686136143748', "pla_catast" = '07013A02100161' where "pla_codi" = 1087;
update "public"."oli_plantacio" set "pla_coordx" = '2.97289669387771', "pla_coordy" = '39.3729098935097', "pla_catast" = '07013A02100150' where "pla_codi" = 1088;
update "public"."oli_plantacio" set "pla_coordx" = '2.88017735958899', "pla_coordy" = '39.7149806066788', "pla_catast" = '07029A00100777' where "pla_codi" = 1091;
update "public"."oli_plantacio" set "pla_coordx" = '3.1129942356713', "pla_coordy" = '39.8539485392183', "pla_catast" = '07003A00100081' where "pla_codi" = 1092;
update "public"."oli_plantacio" set "pla_coordx" = '3.04950395083109', "pla_coordy" = '39.8793631172024', "pla_catast" = '07042A00300255' where "pla_codi" = 1094;
update "public"."oli_plantacio" set "pla_coordx" = '2.8235921589477', "pla_coordy" = '39.4884321104408', "pla_catast" = '07031A00700055' where "pla_codi" = 1101;
update "public"."oli_plantacio" set "pla_coordx" = '2.80457067933402', "pla_coordy" = '39.5921744235953', "pla_catast" = '07040A03700214' where "pla_codi" = 1103;
update "public"."oli_plantacio" set "pla_coordx" = '3.07995778781028', "pla_coordy" = '39.7182113037268', "pla_catast" = '07039A00600567' where "pla_codi" = 1105;
update "public"."oli_plantacio" set "pla_coordx" = '2.92716593953533', "pla_coordy" = '39.4029339232516', "pla_catast" = '07031A02300279' where "pla_codi" = 1108;
update "public"."oli_plantacio" set "pla_coordx" = '3.16425407391661', "pla_coordy" = '39.4859910246408', "pla_catast" = '07022A01700212' where "pla_codi" = 1110;
update "public"."oli_plantacio" set "pla_coordx" = '2.98307237693875', "pla_coordy" = '39.5831944919482', "pla_catast" = '07038A00900243' where "pla_codi" = 1111;
update "public"."oli_plantacio" set "pla_coordx" = '2.98896822064393', "pla_coordy" = '39.7393311382363', "pla_catast" = '07009A00700115' where "pla_codi" = 1112;
update "public"."oli_plantacio" set "pla_coordx" = '2.73161851797632', "pla_coordy" = '39.5668447365446', "pla_catast" = '07040A04100033' where "pla_codi" = 1113;
update "public"."oli_plantacio" set "pla_coordx" = '2.82632369277687', "pla_coordy" = '39.6926841378739', "pla_catast" = '07008A01500033' where "pla_codi" = 1115;
update "public"."oli_plantacio" set "pla_coordx" = '3.07523758880059', "pla_coordy" = '39.4978451569852', "pla_catast" = '07043A00800078' where "pla_codi" = 1116;
update "public"."oli_plantacio" set "pla_coordx" = '3.05394429082813', "pla_coordy" = '39.8790315423976', "pla_catast" = '07042A00300341' where "pla_codi" = 1121;
update "public"."oli_plantacio" set "pla_coordx" = '3.0775064099392', "pla_coordy" = '39.7343966670998', "pla_catast" = '07039A01300394' where "pla_codi" = 1124;
update "public"."oli_plantacio" set "pla_coordx" = '3.06718189148451', "pla_coordy" = '39.6574365893989', "pla_catast" = '07035A00300499' where "pla_codi" = 1126;
update "public"."oli_plantacio" set "pla_coordx" = '2.86628217387288', "pla_coordy" = '39.4918841635832', "pla_catast" = '07031A04200395' where "pla_codi" = 1127;
update "public"."oli_plantacio" set "pla_coordx" = '3.11387288882064', "pla_coordy" = '39.8544567373316', "pla_catast" = '07003A00100069' where "pla_codi" = 1129;
update "public"."oli_plantacio" set "pla_coordx" = '2.74482339770343', "pla_coordy" = '39.58424134611', "pla_catast" = '07040A03700031' where "pla_codi" = 1132;
update "public"."oli_plantacio" set "pla_coordx" = '3.02449095686162', "pla_coordy" = '39.3482926149768', "pla_catast" = '07013A01600210' where "pla_codi" = 1133;
update "public"."oli_plantacio" set "pla_coordx" = '3.06162890245971', "pla_coordy" = '39.7450023402855', "pla_catast" = '07039A01200113' where "pla_codi" = 1135;
update "public"."oli_plantacio" set "pla_coordx" = '3.19414435066389', "pla_coordy" = '39.61072167037', "pla_catast" = '07033A00200086' where "pla_codi" = 1136;
update "public"."oli_plantacio" set "pla_coordx" = '2.66498318040888', "pla_coordy" = '39.5890786962683', "pla_catast" = '07040A02400039' where "pla_codi" = 1139;
update "public"."oli_plantacio" set "pla_coordx" = '3.21588750557475', "pla_coordy" = '39.3796038861678', "pla_catast" = '07057A00800533' where "pla_codi" = 1144;
update "public"."oli_plantacio" set "pla_coordx" = '2.8365538301672', "pla_coordy" = '39.6983810470434', "pla_catast" = '07008A01400211' where "pla_codi" = 1145;
update "public"."oli_plantacio" set "pla_coordx" = '2.88253305126302', "pla_coordy" = '39.7363060239819', "pla_catast" = '07058A02000019' where "pla_codi" = 1146;
update "public"."oli_plantacio" set "pla_coordx" = '2.87158828617582', "pla_coordy" = '39.7541495342117', "pla_catast" = '07034A00300023' where "pla_codi" = 1148;
update "public"."oli_plantacio" set "pla_coordx" = '3.04376041494771', "pla_coordy" = '39.7079800933971', "pla_catast" = '07030A00400072' where "pla_codi" = 1149;
update "public"."oli_plantacio" set "pla_coordx" = '2.60676920578133', "pla_coordy" = '39.6367624634252', "pla_catast" = '07040A00900028' where "pla_codi" = 1151;
update "public"."oli_plantacio" set "pla_coordx" = '3.08226920461596', "pla_coordy" = '39.6434778751649', "pla_catast" = '07060A01100473' where "pla_codi" = 1152;
update "public"."oli_plantacio" set "pla_coordx" = '3.00065534895568', "pla_coordy" = '39.6777332447263', "pla_catast" = '07060A01200088' where "pla_codi" = 1154;
update "public"."oli_plantacio" set "pla_coordx" = '2.91136801902902', "pla_coordy" = '39.7320983750217', "pla_catast" = '07027A01100252' where "pla_codi" = 1158;
update "public"."oli_plantacio" set "pla_coordx" = '3.025483142856', "pla_coordy" = '39.7603980915982', "pla_catast" = '07044A01500195' where "pla_codi" = 1159;
update "public"."oli_plantacio" set "pla_coordx" = '2.8235921589477', "pla_coordy" = '39.4884321104408', "pla_catast" = '07031A00700055' where "pla_codi" = 1162;
update "public"."oli_plantacio" set "pla_coordx" = '2.87820681530032', "pla_coordy" = '39.6583466617303', "pla_catast" = '07008A01100120' where "pla_codi" = 1096;
update "public"."oli_plantacio" set "pla_coordx" = '2.94230169084073', "pla_coordy" = '39.7277942057136', "pla_catast" = '07027A00700190' where "pla_codi" = 1095;
update "public"."oli_plantacio" set "pla_coordx" = '3.06215647003066', "pla_coordy" = '39.8547071991532', "pla_catast" = '07042A00200274' where "pla_codi" = 1097;
update "public"."oli_plantacio" set "pla_coordx" = '2.90352550243711', "pla_coordy" = '39.491307853524', "pla_catast" = '07031A01600079' where "pla_codi" = 1117;
update "public"."oli_plantacio" set "pla_coordx" = '2.40754576867456', "pla_coordy" = '39.5695645421249', "pla_catast" = '07005A01600151' where "pla_codi" = 1123;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 1164;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 1165;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 1168;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 1178;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 1198;
update "public"."oli_plantacio" set "pla_coordx" = '3.07228569455954', "pla_coordy" = '39.6020481804716', "pla_catast" = '07041A00300759' where "pla_codi" = 1169;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 1217;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 1222;
update "public"."oli_plantacio" set "pla_coordx" = '3.07943405130475', "pla_coordy" = '39.7640613939476', "pla_catast" = '07039A01200395' where "pla_codi" = 1167;
update "public"."oli_plantacio" set "pla_coordx" = '2.9788503998406', "pla_coordy" = '39.7228296551388', "pla_catast" = '07027A00700319' where "pla_codi" = 1170;
update "public"."oli_plantacio" set "pla_coordx" = '3.04918373734829', "pla_coordy" = '39.5109938315525', "pla_catast" = '07043A00600337' where "pla_codi" = 1173;
update "public"."oli_plantacio" set "pla_coordx" = '2.75694957502656', "pla_coordy" = '39.4689381937315', "pla_catast" = '07031A00300019' where "pla_codi" = 1177;
update "public"."oli_plantacio" set "pla_coordx" = '2.89084623051586', "pla_coordy" = '39.506337148269', "pla_catast" = '07031A01100330' where "pla_codi" = 1180;
update "public"."oli_plantacio" set "pla_coordx" = '3.06398658631273', "pla_coordy" = '39.4405171344269', "pla_catast" = '07013A01100254' where "pla_codi" = 1181;
update "public"."oli_plantacio" set "pla_coordx" = '3.069843008233', "pla_coordy" = '39.7232074945954', "pla_catast" = '07039A00600063' where "pla_codi" = 1183;
update "public"."oli_plantacio" set "pla_coordx" = '3.07488205607468', "pla_coordy" = '39.7241447601368', "pla_catast" = '07039A00600270' where "pla_codi" = 1184;
update "public"."oli_plantacio" set "pla_coordx" = '3.069843008233', "pla_coordy" = '39.7232074945954', "pla_catast" = '07039A00600063' where "pla_codi" = 1185;
update "public"."oli_plantacio" set "pla_coordx" = '3.0475075233562', "pla_coordy" = '39.7919684117605', "pla_catast" = '07044A01400141' where "pla_codi" = 1187;
update "public"."oli_plantacio" set "pla_coordx" = '2.89321873948935', "pla_coordy" = '39.6785192160491', "pla_catast" = '07008A01200006' where "pla_codi" = 1188;
update "public"."oli_plantacio" set "pla_coordx" = '3.1154245672857', "pla_coordy" = '39.7596129715414', "pla_catast" = '07039A01000213' where "pla_codi" = 1190;
update "public"."oli_plantacio" set "pla_coordx" = '3.05855208123828', "pla_coordy" = '39.8155870699663', "pla_catast" = '07003A00700247' where "pla_codi" = 1194;
update "public"."oli_plantacio" set "pla_coordx" = '3.04952854352542', "pla_coordy" = '39.7099439181119', "pla_catast" = '07030A00400053' where "pla_codi" = 1196;
update "public"."oli_plantacio" set "pla_coordx" = '3.02197326811314', "pla_coordy" = '39.7132515578019', "pla_catast" = '07030A00400594' where "pla_codi" = 1199;
update "public"."oli_plantacio" set "pla_coordx" = '3.02092584174865', "pla_coordy" = '39.7224988665766', "pla_catast" = '07030A00400488' where "pla_codi" = 1200;
update "public"."oli_plantacio" set "pla_coordx" = '2.90352550243711', "pla_coordy" = '39.491307853524', "pla_catast" = '07031A01600079' where "pla_codi" = 1202;
update "public"."oli_plantacio" set "pla_coordx" = '2.72573498073844', "pla_coordy" = '39.6036481771103', "pla_catast" = '07036A00400268' where "pla_codi" = 1203;
update "public"."oli_plantacio" set "pla_coordx" = '3.2618593974878', "pla_coordy" = '39.6277045324722', "pla_catast" = '07051A00900206' where "pla_codi" = 1204;
update "public"."oli_plantacio" set "pla_coordx" = '3.26325565693579', "pla_coordy" = '39.6256792983015', "pla_catast" = '07051A00900167' where "pla_codi" = 1205;
update "public"."oli_plantacio" set "pla_coordx" = '2.89035722939756', "pla_coordy" = '39.7368371003471', "pla_catast" = '07058A01400171' where "pla_codi" = 1208;
update "public"."oli_plantacio" set "pla_coordx" = '3.33597412398991', "pla_coordy" = '39.574914936784', "pla_catast" = '07033A01100072' where "pla_codi" = 1209;
update "public"."oli_plantacio" set "pla_coordx" = '3.33292426829532', "pla_coordy" = '39.5716953641089', "pla_catast" = '07033A01100122' where "pla_codi" = 1212;
update "public"."oli_plantacio" set "pla_coordx" = '3.33741476360412', "pla_coordy" = '39.5746798253957', "pla_catast" = '07033A01100058' where "pla_codi" = 1213;
update "public"."oli_plantacio" set "pla_coordx" = '3.08916201430472', "pla_coordy" = '39.6115660240311', "pla_catast" = '07041A00300060' where "pla_codi" = 1215;
update "public"."oli_plantacio" set "pla_coordx" = '3.08892218163156', "pla_coordy" = '39.6115845882744', "pla_catast" = '07041A00300061' where "pla_codi" = 1216;
update "public"."oli_plantacio" set "pla_coordx" = '3.11982175920225', "pla_coordy" = '39.6252597943764', "pla_catast" = '07041A00700534' where "pla_codi" = 1219;
update "public"."oli_plantacio" set "pla_coordx" = '3.13212234450922', "pla_coordy" = '39.6161556131349', "pla_catast" = '07041A00700141' where "pla_codi" = 1220;
update "public"."oli_plantacio" set "pla_coordx" = '3.3900041131417', "pla_coordy" = '39.6285208963826', "pla_catast" = '07062A01800129' where "pla_codi" = 1223;
update "public"."oli_plantacio" set "pla_coordx" = '2.50504104277024', "pla_coordy" = '39.5541107770023', "pla_catast" = '07011A01500116' where "pla_codi" = 1224;
update "public"."oli_plantacio" set "pla_coordx" = '2.82457561916082', "pla_coordy" = '39.5638447562974', "pla_catast" = '07040A04500183' where "pla_codi" = 1226;
update "public"."oli_plantacio" set "pla_coordx" = '2.80174482457021', "pla_coordy" = '39.5574602941236', "pla_catast" = '07040A04500068' where "pla_codi" = 1227;
update "public"."oli_plantacio" set "pla_coordx" = '3.1513339549207', "pla_coordy" = '39.6571703668511', "pla_catast" = '07066A00500072' where "pla_codi" = 1230;
update "public"."oli_plantacio" set "pla_coordx" = '3.15103346810605', "pla_coordy" = '39.6565945542543', "pla_catast" = '07066A00500071' where "pla_codi" = 1231;
update "public"."oli_plantacio" set "pla_coordx" = '3.15130047512431', "pla_coordy" = '39.6585458187158', "pla_catast" = '07066A00500175' where "pla_codi" = 1232;
update "public"."oli_plantacio" set "pla_coordx" = '3.11974652880504', "pla_coordy" = '39.6454680145945', "pla_catast" = '07066A00600388' where "pla_codi" = 1234;
update "public"."oli_plantacio" set "pla_coordx" = '2.79016551962053', "pla_coordy" = '39.3895181450202', "pla_catast" = '07031A03100015' where "pla_codi" = 1235;
update "public"."oli_plantacio" set "pla_coordx" = '3.06785123930184', "pla_coordy" = '39.8358738885307', "pla_catast" = '07003A00600149' where "pla_codi" = 1236;
update "public"."oli_plantacio" set "pla_coordx" = '3.06691692461787', "pla_coordy" = '39.8351042641025', "pla_catast" = '07003A00600383' where "pla_codi" = 1238;
update "public"."oli_plantacio" set "pla_coordx" = '2.98120551518591', "pla_coordy" = '39.8536669991343', "pla_catast" = '07042A00800030' where "pla_codi" = 1240;
update "public"."oli_plantacio" set "pla_coordx" = '0', "pla_coordy" = '0', "pla_catast" = '07039A01000213' where "pla_codi" = 1191;
update "public"."oli_plantacio" set "pla_coordx" = '3.04549373496655', "pla_coordy" = '39.5116313893881', "pla_catast" = '07043A00600333' where "pla_codi" = 1174;
update "public"."oli_plantacio" set "pla_coordx" = '3.38085901747645', "pla_coordy" = '39.6933872416054', "pla_catast" = '07006A01600126' where "pla_codi" = 1206;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 1258;
update "public"."oli_plantacio" set "pla_coordx" = '3.29911495065612', "pla_coordy" = '39.7363518951075', "pla_catast" = '07006A00500020' where "pla_codi" = 1142;
update "public"."oli_plantacio" set "pla_coordx" = '2.55822122183667', "pla_coordy" = '39.58207569211', "pla_catast" = '07011A01700002' where "pla_codi" = 1197;
update "public"."oli_plantacio" set "pla_coordx" = '2.98724988451203', "pla_coordy" = '39.8561755366476', "pla_catast" = '07042A00800011' where "pla_codi" = 1242;
update "public"."oli_plantacio" set "pla_coordx" = '2.69854066279407', "pla_coordy" = '39.7631878650917', "pla_catast" = '07061A00500263' where "pla_codi" = 375;
update "public"."oli_plantacio" set "pla_coordx" = '3.07460645351199', "pla_coordy" = '39.8453964610796', "pla_catast" = '07003A00600678' where "pla_codi" = 440;
update "public"."oli_plantacio" set "pla_coordx" = '2.93635880935104', "pla_coordy" = '39.6783527644333', "pla_catast" = '07047A00300102' where "pla_codi" = 517;
update "public"."oli_plantacio" set "pla_coordx" = '2.92932026086314', "pla_coordy" = '39.6822340749682', "pla_catast" = '07047A00300120' where "pla_codi" = 518;
update "public"."oli_plantacio" set "pla_coordx" = '2.91511182848481', "pla_coordy" = '39.7312339775793', "pla_catast" = '07027A01100238' where "pla_codi" = 610;
update "public"."oli_plantacio" set "pla_coordx" = '2.73428416801127', "pla_coordy" = '39.7632968939966', "pla_catast" = '07061A00401801' where "pla_codi" = 676;
update "public"."oli_plantacio" set "pla_coordx" = '2.70554094887064', "pla_coordy" = '39.7982708017209', "pla_catast" = '07061A00200685' where "pla_codi" = 685;
update "public"."oli_plantacio" set "pla_coordx" = '2.91082353197737', "pla_coordy" = '39.5384459401554', "pla_catast" = '07004A02400316' where "pla_codi" = 1063;
update "public"."oli_plantacio" set "pla_coordx" = '2.92081114630821', "pla_coordy" = '39.7491085510522', "pla_catast" = '07058A00900138' where "pla_codi" = 1243;
update "public"."oli_plantacio" set "pla_coordx" = '3.00028772274526', "pla_coordy" = '39.8480734808349', "pla_catast" = '07042A00600453' where "pla_codi" = 1244;
update "public"."oli_plantacio" set "pla_coordx" = '2.629515249901', "pla_coordy" = '39.742030782372', "pla_catast" = '07018A00200371' where "pla_codi" = 1248;
update "public"."oli_plantacio" set "pla_coordx" = '3.12709378260169', "pla_coordy" = '39.6414862699096', "pla_catast" = '07066A00600795' where "pla_codi" = 1250;
update "public"."oli_plantacio" set "pla_coordx" = '3.12912411597513', "pla_coordy" = '39.6403291036153', "pla_catast" = '07066A00600794' where "pla_codi" = 1251;
update "public"."oli_plantacio" set "pla_coordx" = '3.01335529478045', "pla_coordy" = '39.3837264569556', "pla_catast" = '07013A01800722' where "pla_codi" = 1255;
update "public"."oli_plantacio" set "pla_coordx" = '3.01960890085062', "pla_coordy" = '39.3846495331176', "pla_catast" = '07013A01800723' where "pla_codi" = 1256;
update "public"."oli_plantacio" set "pla_coordx" = '2.7433117186506', "pla_coordy" = '39.7785967016746', "pla_catast" = '07025A00100726' where "pla_codi" = 1259;
update "public"."oli_plantacio" set "pla_coordx" = '2.74048332201576', "pla_coordy" = '39.7868212420612', "pla_catast" = '07025A00100432' where "pla_codi" = 1260;
update "public"."oli_plantacio" set "pla_coordx" = '2.7506848948751', "pla_coordy" = '39.5459383804171', "pla_catast" = '07040A05300007' where "pla_codi" = 1261;
update "public"."oli_plantacio" set "pla_coordx" = '3.37201091075897', "pla_coordy" = '39.6309518570117', "pla_catast" = '07062A00400691' where "pla_codi" = 1263;
update "public"."oli_plantacio" set "pla_coordx" = '2.85115829005609', "pla_coordy" = '39.7620363858788', "pla_catast" = '07034A00300014' where "pla_codi" = 1264;
update "public"."oli_plantacio" set "pla_coordx" = '2.81707063020507', "pla_coordy" = '39.4072867646221', "pla_catast" = '07031A03100019' where "pla_codi" = 1266;
update "public"."oli_plantacio" set "pla_coordx" = '3.13248704736336', "pla_coordy" = '39.8503434362229', "pla_catast" = '07003A00300020' where "pla_codi" = 1267;
update "public"."oli_plantacio" set "pla_coordx" = '3.05030560250471', "pla_coordy" = '39.8655924311526', "pla_catast" = '07042A00200688' where "pla_codi" = 1269;
update "public"."oli_plantacio" set "pla_coordx" = '2.65133453076032', "pla_coordy" = '39.7508135226842', "pla_catast" = '07018A00200299' where "pla_codi" = 1271;
update "public"."oli_plantacio" set "pla_coordx" = '2.93810455119228', "pla_coordy" = '39.5849532806594', "pla_catast" = '07004A02200709' where "pla_codi" = 1273;
update "public"."oli_plantacio" set "pla_coordx" = '2.69740810576972', "pla_coordy" = '39.5834070327252', "pla_catast" = '07040A03100006' where "pla_codi" = 1274;
update "public"."oli_plantacio" set "pla_coordx" = '2.69740810576972', "pla_coordy" = '39.5834070327252', "pla_catast" = '07040A03100006' where "pla_codi" = 1275;
update "public"."oli_plantacio" set "pla_coordx" = '3.30454240952575', "pla_coordy" = '39.5878378075936', "pla_catast" = '07051A00300878' where "pla_codi" = 1277;
update "public"."oli_plantacio" set "pla_coordx" = '3.12681816802113', "pla_coordy" = '39.5548049511296', "pla_catast" = '07065A00301472' where "pla_codi" = 1282;
update "public"."oli_plantacio" set "pla_coordx" = '2.97537012711702', "pla_coordy" = '39.4114548058328', "pla_catast" = '07013A00200275' where "pla_codi" = 1286;
update "public"."oli_plantacio" set "pla_coordx" = '2.97543858898477', "pla_coordy" = '39.4116519733065', "pla_catast" = '07013A00200366' where "pla_codi" = 1287;
update "public"."oli_plantacio" set "pla_coordx" = '2.99978609180477', "pla_coordy" = '39.7757194182282', "pla_catast" = '07009A00400113' where "pla_codi" = 1289;
update "public"."oli_plantacio" set "pla_coordx" = '2.93542311965169', "pla_coordy" = '39.7629834608029', "pla_catast" = '07058A00300044' where "pla_codi" = 1290;
update "public"."oli_plantacio" set "pla_coordx" = '2.93698729043109', "pla_coordy" = '39.7628608685535', "pla_catast" = '07058A00300038' where "pla_codi" = 1291;
update "public"."oli_plantacio" set "pla_coordx" = '2.94351995020951', "pla_coordy" = '39.7722945100641', "pla_catast" = '07058A01900099' where "pla_codi" = 1293;
update "public"."oli_plantacio" set "pla_coordx" = '2.68511269007449', "pla_coordy" = '39.7227964265533', "pla_catast" = '07010A00800098' where "pla_codi" = 1294;
update "public"."oli_plantacio" set "pla_coordx" = '2.76783305874014', "pla_coordy" = '39.5953929767061', "pla_catast" = '07040A03300055' where "pla_codi" = 1297;
update "public"."oli_plantacio" set "pla_coordx" = '2.88480775113179', "pla_coordy" = '39.7519776394858', "pla_catast" = '07034A00100068' where "pla_codi" = 1298;
update "public"."oli_plantacio" set "pla_coordx" = '3.03351009749951', "pla_coordy" = '39.903121647787', "pla_catast" = '07042A00400524' where "pla_codi" = 1299;
update "public"."oli_plantacio" set "pla_coordx" = '2.95131001885812', "pla_coordy" = '39.4107754671535', "pla_catast" = '07013A00100087' where "pla_codi" = 1301;
update "public"."oli_plantacio" set "pla_coordx" = '3.21136965850197', "pla_coordy" = '39.6619664033552', "pla_catast" = '07033A00200003' where "pla_codi" = 1302;
update "public"."oli_plantacio" set "pla_coordx" = '2.92519798237774', "pla_coordy" = '39.7305285046646', "pla_catast" = '07027A01100099' where "pla_codi" = 1279;
update "public"."oli_plantacio" set "pla_coordx" = '3.00505314607166', "pla_coordy" = '39.84533488413', "pla_catast" = '07042A00600464' where "pla_codi" = 1245;
update "public"."oli_plantacio" set "pla_coordx" = '2.63135930113742', "pla_coordy" = '39.7410410325115', "pla_catast" = '07018A00200369' where "pla_codi" = 1247;
update "public"."oli_plantacio" set "pla_coordx" = '3.30387067187979', "pla_coordy" = '39.5889742273135', "pla_catast" = '07051A00300110' where "pla_codi" = 1278;
update "public"."oli_plantacio" set "pla_coordx" = '2.89648060761761', "pla_coordy" = '39.5068757969519', "pla_catast" = '07031A01100386' where "pla_codi" = 1283;
update "public"."oli_plantacio" set "pla_coordx" = '3.10522041711876', "pla_coordy" = '39.7091394320037', "pla_catast" = '07055A00400075' where "pla_codi" = 1130;
update "public"."oli_plantacio" set "pla_coordx" = '2.63144386665453', "pla_coordy" = '39.7463143496521', "pla_catast" = '07018A00200374' where "pla_codi" = 2;
update "public"."oli_plantacio" set "pla_coordx" = '2.74491509497491', "pla_coordy" = '39.7719235458578', "pla_catast" = '07025A00100171' where "pla_codi" = 19;
update "public"."oli_plantacio" set "pla_coordx" = '2.62798425783461', "pla_coordy" = '39.7446338559274', "pla_catast" = '07018A00100004' where "pla_codi" = 1;
update "public"."oli_plantacio" set "pla_coordx" = '2.91595762823996', "pla_coordy" = '39.5078866628493', "pla_catast" = '07031A01400167' where "pla_codi" = 9;
update "public"."oli_plantacio" set "pla_coordx" = '2.52623777384041', "pla_coordy" = '39.6113319495529', "pla_catast" = '07045A00100180' where "pla_codi" = 24;
update "public"."oli_plantacio" set "pla_coordx" = '3.1511309744271', "pla_coordy" = '39.528911074891', "pla_catast" = '07033A02501282' where "pla_codi" = 28;
update "public"."oli_plantacio" set "pla_coordx" = '2.69978427684341', "pla_coordy" = '39.7215081336866', "pla_catast" = '07010A00700198' where "pla_codi" = 32;
update "public"."oli_plantacio" set "pla_coordx" = '2.74150608557928', "pla_coordy" = '39.8119830932276', "pla_catast" = '07025A00100072' where "pla_codi" = 36;
update "public"."oli_plantacio" set "pla_coordx" = '2.74975709623826', "pla_coordy" = '39.8164547920084', "pla_catast" = '07025A00100077' where "pla_codi" = 39;
update "public"."oli_plantacio" set "pla_coordx" = '2.82358762830647', "pla_coordy" = '39.7006567699141', "pla_catast" = '07008A01500139' where "pla_codi" = 43;
update "public"."oli_plantacio" set "pla_coordx" = '2.87822532803209', "pla_coordy" = '39.6688554657572', "pla_catast" = '07008A01200119' where "pla_codi" = 50;
update "public"."oli_plantacio" set "pla_coordx" = '2.69032767708353', "pla_coordy" = '39.71336819665', "pla_catast" = '07010A00700168' where "pla_codi" = 55;
update "public"."oli_plantacio" set "pla_coordx" = '2.89992965262459', "pla_coordy" = '39.6904120585194', "pla_catast" = '07027A00100209' where "pla_codi" = 60;
update "public"."oli_plantacio" set "pla_coordx" = '2.68404569970896', "pla_coordy" = '39.7714141775342', "pla_catast" = '07061A00500450' where "pla_codi" = 64;
update "public"."oli_plantacio" set "pla_coordx" = '2.75761017775774', "pla_coordy" = '39.7861875511454', "pla_catast" = '07025A00100394' where "pla_codi" = 69;
update "public"."oli_plantacio" set "pla_coordx" = '2.66997016214004', "pla_coordy" = '39.7591461997007', "pla_catast" = '07018A00200364' where "pla_codi" = 72;
update "public"."oli_plantacio" set "pla_coordx" = '2.67596602970145', "pla_coordy" = '39.7650136825408', "pla_catast" = '07061A00500520' where "pla_codi" = 75;
update "public"."oli_plantacio" set "pla_coordx" = '2.67225764448702', "pla_coordy" = '39.7675864581776', "pla_catast" = '07061A00500596' where "pla_codi" = 76;
update "public"."oli_plantacio" set "pla_coordx" = '2.7346520173796', "pla_coordy" = '39.7799324184173', "pla_catast" = '07025A00100314' where "pla_codi" = 80;
update "public"."oli_plantacio" set "pla_coordx" = '2.70992888744125', "pla_coordy" = '39.7956896659383', "pla_catast" = '07061A00200545' where "pla_codi" = 83;
update "public"."oli_plantacio" set "pla_coordx" = '3.07373187520748', "pla_coordy" = '39.8450404098563', "pla_catast" = '07003A00600042' where "pla_codi" = 86;
update "public"."oli_plantacio" set "pla_coordx" = '3.07553201469427', "pla_coordy" = '39.8458683399142', "pla_catast" = '07003A00600041' where "pla_codi" = 87;
update "public"."oli_plantacio" set "pla_coordx" = '3.08023494616149', "pla_coordy" = '39.8395642611951', "pla_catast" = '07003A00700034' where "pla_codi" = 90;
update "public"."oli_plantacio" set "pla_coordx" = '2.72504340923935', "pla_coordy" = '39.7807932888333', "pla_catast" = '07061A00400515' where "pla_codi" = 94;
update "public"."oli_plantacio" set "pla_coordx" = '2.85961299599651', "pla_coordy" = '39.7493711641551', "pla_catast" = '07034A00300253' where "pla_codi" = 98;
update "public"."oli_plantacio" set "pla_coordx" = '2.75372604057723', "pla_coordy" = '39.7827163939094', "pla_catast" = '07025A00100684' where "pla_codi" = 108;
update "public"."oli_plantacio" set "pla_coordx" = '2.71314658789789', "pla_coordy" = '39.8096785148374', "pla_catast" = '07061A00300303' where "pla_codi" = 112;
update "public"."oli_plantacio" set "pla_coordx" = '2.75744032199894', "pla_coordy" = '39.7854679151644', "pla_catast" = '07025A00100393' where "pla_codi" = 115;
update "public"."oli_plantacio" set "pla_coordx" = '2.90822729688745', "pla_coordy" = '39.7696779384332', "pla_catast" = '07058A01300114' where "pla_codi" = 118;
update "public"."oli_plantacio" set "pla_coordx" = '2.8746252095109', "pla_coordy" = '39.714185859875', "pla_catast" = '07029A00100400' where "pla_codi" = 122;
update "public"."oli_plantacio" set "pla_coordx" = '2.7802493534119', "pla_coordy" = '39.6717579469861', "pla_catast" = '07056A00100169' where "pla_codi" = 125;
update "public"."oli_plantacio" set "pla_coordx" = '2.8723658052317', "pla_coordy" = '39.683791280505', "pla_catast" = '07008A00200025' where "pla_codi" = 129;
update "public"."oli_plantacio" set "pla_coordx" = '2.80574860089585', "pla_coordy" = '39.701118982959', "pla_catast" = '07001A00300148' where "pla_codi" = 135;
update "public"."oli_plantacio" set "pla_coordx" = '2.40429538396654', "pla_coordy" = '39.5724202676902', "pla_catast" = '07005A01600106' where "pla_codi" = 138;
update "public"."oli_plantacio" set "pla_coordx" = '2.75623474383298', "pla_coordy" = '39.7774754495217', "pla_catast" = '07025A00100131' where "pla_codi" = 142;
update "public"."oli_plantacio" set "pla_coordx" = '2.7536382757382', "pla_coordy" = '39.7797154676272', "pla_catast" = '07025A00100220' where "pla_codi" = 145;
update "public"."oli_plantacio" set "pla_coordx" = '2.7191350959258', "pla_coordy" = '39.7867922773797', "pla_catast" = '07061A00300485' where "pla_codi" = 148;
update "public"."oli_plantacio" set "pla_coordx" = '3.17695078009789', "pla_coordy" = '39.530300825799', "pla_catast" = '07033A02401128' where "pla_codi" = 152;
update "public"."oli_plantacio" set "pla_coordx" = '2.71353988631227', "pla_coordy" = '39.7953984345442', "pla_catast" = '07061A00200556' where "pla_codi" = 154;
update "public"."oli_plantacio" set "pla_coordx" = '2.88268844966257', "pla_coordy" = '39.7354265289977', "pla_catast" = '07058A01100181' where "pla_codi" = 157;
update "public"."oli_plantacio" set "pla_coordx" = '2.69216190110095', "pla_coordy" = '39.7704399215469', "pla_catast" = '07061A00500380' where "pla_codi" = 164;
update "public"."oli_plantacio" set "pla_coordx" = '2.6055251950679', "pla_coordy" = '39.7184760367911', "pla_catast" = '07063A00100061' where "pla_codi" = 167;
update "public"."oli_plantacio" set "pla_coordx" = '2.7083115206554', "pla_coordy" = '39.8024995660667', "pla_catast" = '07061A00300320' where "pla_codi" = 170;
update "public"."oli_plantacio" set "pla_coordx" = '2.68731117290233', "pla_coordy" = '39.7637638269389', "pla_catast" = '07061A00500409' where "pla_codi" = 174;
update "public"."oli_plantacio" set "pla_coordx" = '2.68699613935096', "pla_coordy" = '39.7682135949791', "pla_catast" = '07061A00500420' where "pla_codi" = 177;
update "public"."oli_plantacio" set "pla_coordx" = '2.92347790914387', "pla_coordy" = '39.781949253828', "pla_catast" = '07058A01500051' where "pla_codi" = 180;
update "public"."oli_plantacio" set "pla_coordx" = '2.80141173699214', "pla_coordy" = '39.6520295887377', "pla_catast" = '07056A00300061' where "pla_codi" = 1067;
update "public"."oli_plantacio" set "pla_coordx" = '2.66916313693585', "pla_coordy" = '39.7729178478368', "pla_catast" = '07061A00100451' where "pla_codi" = 13;
update "public"."oli_plantacio" set "pla_coordx" = '2.90160073087772', "pla_coordy" = '39.688866051631', "pla_catast" = '07027A00200378' where "pla_codi" = 187;
update "public"."oli_plantacio" set "pla_coordx" = '2.90163069293705', "pla_coordy" = '39.689538129657', "pla_catast" = '07027A00200382' where "pla_codi" = 190;
update "public"."oli_plantacio" set "pla_coordx" = '2.90009772222365', "pla_coordy" = '39.6888486184361', "pla_catast" = '07027A00200385' where "pla_codi" = 193;
update "public"."oli_plantacio" set "pla_coordx" = '2.67783118469544', "pla_coordy" = '39.6211044575381', "pla_catast" = '07036A00600166' where "pla_codi" = 197;
update "public"."oli_plantacio" set "pla_coordx" = '2.77684663035516', "pla_coordy" = '39.5631022083236', "pla_catast" = '07040A04400016' where "pla_codi" = 198;
update "public"."oli_plantacio" set "pla_coordx" = '3.00703926257015', "pla_coordy" = '39.7617563509477', "pla_catast" = '07044A00800259' where "pla_codi" = 206;
update "public"."oli_plantacio" set "pla_coordx" = '2.74821810619694', "pla_coordy" = '39.5428627614969', "pla_catast" = '07040A05400030' where "pla_codi" = 209;
update "public"."oli_plantacio" set "pla_coordx" = '2.74736479692393', "pla_coordy" = '39.7759486035897', "pla_catast" = '07025A00100155' where "pla_codi" = 212;
update "public"."oli_plantacio" set "pla_coordx" = '3.07985865966633', "pla_coordy" = '39.8470220340436', "pla_catast" = '07003A00500304' where "pla_codi" = 216;
update "public"."oli_plantacio" set "pla_coordx" = '2.69952189371394', "pla_coordy" = '39.7619579176006', "pla_catast" = '07061A00500246' where "pla_codi" = 219;
update "public"."oli_plantacio" set "pla_coordx" = '2.68353001176433', "pla_coordy" = '39.7709191113561', "pla_catast" = '07061A00500448' where "pla_codi" = 222;
update "public"."oli_plantacio" set "pla_coordx" = '2.68408795048125', "pla_coordy" = '39.7717246892211', "pla_catast" = '07061A00500622' where "pla_codi" = 225;
update "public"."oli_plantacio" set "pla_coordx" = '2.75691825876633', "pla_coordy" = '39.7845121046812', "pla_catast" = '07025A00100248' where "pla_codi" = 232;
update "public"."oli_plantacio" set "pla_coordx" = '2.69983758277402', "pla_coordy" = '39.7454525043063', "pla_catast" = '07061A00402001' where "pla_codi" = 235;
update "public"."oli_plantacio" set "pla_coordx" = '2.71212082347032', "pla_coordy" = '39.794395789016', "pla_catast" = '07061A00200552' where "pla_codi" = 238;
update "public"."oli_plantacio" set "pla_coordx" = '2.74920537933562', "pla_coordy" = '39.7804756910973', "pla_catast" = '07025A00100935' where "pla_codi" = 242;
update "public"."oli_plantacio" set "pla_coordx" = '2.74232868726465', "pla_coordy" = '39.7897058947676', "pla_catast" = '07025A00100512' where "pla_codi" = 246;
update "public"."oli_plantacio" set "pla_coordx" = '2.71909683713574', "pla_coordy" = '39.7569532379154', "pla_catast" = '07061A00401873' where "pla_codi" = 250;
update "public"."oli_plantacio" set "pla_coordx" = '2.72038383476666', "pla_coordy" = '39.7937728867376', "pla_catast" = '07061A00300368' where "pla_codi" = 253;
update "public"."oli_plantacio" set "pla_coordx" = '2.83790457252672', "pla_coordy" = '39.7114431600175', "pla_catast" = '07008A01500145' where "pla_codi" = 261;
update "public"."oli_plantacio" set "pla_coordx" = '2.7079564867308', "pla_coordy" = '39.7535233577547', "pla_catast" = '07061A00401974' where "pla_codi" = 264;
update "public"."oli_plantacio" set "pla_coordx" = '2.74625157971257', "pla_coordy" = '39.7741478385464', "pla_catast" = '07025A00100790' where "pla_codi" = 268;
update "public"."oli_plantacio" set "pla_coordx" = '2.89775201385532', "pla_coordy" = '39.6907390837885', "pla_catast" = '07027A00100005' where "pla_codi" = 272;
update "public"."oli_plantacio" set "pla_coordx" = '2.69727783974409', "pla_coordy" = '39.7758295969356', "pla_catast" = '07061A00100317' where "pla_codi" = 275;
update "public"."oli_plantacio" set "pla_coordx" = '2.74662876211257', "pla_coordy" = '39.7715563536896', "pla_catast" = '07025A00100164' where "pla_codi" = 280;
update "public"."oli_plantacio" set "pla_coordx" = '2.74758461709469', "pla_coordy" = '39.7809912466994', "pla_catast" = '07025A00100703' where "pla_codi" = 283;
update "public"."oli_plantacio" set "pla_coordx" = '2.7555055900059', "pla_coordy" = '39.7780780441422', "pla_catast" = '07025A00100924' where "pla_codi" = 286;
update "public"."oli_plantacio" set "pla_coordx" = '2.68773068714781', "pla_coordy" = '39.7661356875299', "pla_catast" = '07061A00500640' where "pla_codi" = 293;
update "public"."oli_plantacio" set "pla_coordx" = '2.72077184673582', "pla_coordy" = '39.7914859813212', "pla_catast" = '07061A00300148' where "pla_codi" = 297;
update "public"."oli_plantacio" set "pla_coordx" = '2.8812287427261', "pla_coordy" = '39.6778708115344', "pla_catast" = '07008A01200190' where "pla_codi" = 300;
update "public"."oli_plantacio" set "pla_coordx" = '2.68183317586277', "pla_coordy" = '39.7835265642546', "pla_catast" = '07061A00100431' where "pla_codi" = 304;
update "public"."oli_plantacio" set "pla_coordx" = '2.71494745661381', "pla_coordy" = '39.7979240981674', "pla_catast" = '07061A00300439' where "pla_codi" = 307;
update "public"."oli_plantacio" set "pla_coordx" = '2.52897112742065', "pla_coordy" = '39.6325200838773', "pla_catast" = '07045A00300018' where "pla_codi" = 308;
update "public"."oli_plantacio" set "pla_coordx" = '2.73966402432833', "pla_coordy" = '39.771836825644', "pla_catast" = '07025A00100180' where "pla_codi" = 309;
update "public"."oli_plantacio" set "pla_coordx" = '2.7534410725881', "pla_coordy" = '39.7847547829298', "pla_catast" = '07025A00100989' where "pla_codi" = 316;
update "public"."oli_plantacio" set "pla_coordx" = '2.70771660625514', "pla_coordy" = '39.7559835067113', "pla_catast" = '07061A00500344' where "pla_codi" = 319;
update "public"."oli_plantacio" set "pla_coordx" = '2.6781520664614', "pla_coordy" = '39.6248401053466', "pla_catast" = '07036A00600469' where "pla_codi" = 322;
update "public"."oli_plantacio" set "pla_coordx" = '2.72305348498046', "pla_coordy" = '39.7925278885733', "pla_catast" = '07061A00300157' where "pla_codi" = 325;
update "public"."oli_plantacio" set "pla_coordx" = '2.6866575755094', "pla_coordy" = '39.7711722191201', "pla_catast" = '07061A00500452' where "pla_codi" = 329;
update "public"."oli_plantacio" set "pla_coordx" = '2.68735082696859', "pla_coordy" = '39.7727646353965', "pla_catast" = '07061A00500458' where "pla_codi" = 332;
update "public"."oli_plantacio" set "pla_coordx" = '2.68754701311454', "pla_coordy" = '39.7708980075275', "pla_catast" = '07061A00500576' where "pla_codi" = 335;
update "public"."oli_plantacio" set "pla_coordx" = '2.6880720483277', "pla_coordy" = '39.7714757035251', "pla_catast" = '07061A00500455' where "pla_codi" = 339;
update "public"."oli_plantacio" set "pla_coordx" = '3.07965582544678', "pla_coordy" = '39.7192005637068', "pla_catast" = '07039A00600445' where "pla_codi" = 345;
update "public"."oli_plantacio" set "pla_coordx" = '2.72826948205931', "pla_coordy" = '39.7905806364096', "pla_catast" = '07025A00100016' where "pla_codi" = 348;
update "public"."oli_plantacio" set "pla_coordx" = '2.72934352014729', "pla_coordy" = '39.7914171245989', "pla_catast" = '07025A00100039' where "pla_codi" = 352;
update "public"."oli_plantacio" set "pla_coordx" = '2.72605468788927', "pla_coordy" = '39.7939503286377', "pla_catast" = '07061A00300164' where "pla_codi" = 355;
update "public"."oli_plantacio" set "pla_coordx" = '2.77444453411529', "pla_coordy" = '39.7566968488583', "pla_catast" = '07010A00700193' where "pla_codi" = 358;
update "public"."oli_plantacio" set "pla_coordx" = '2.728836864159', "pla_coordy" = '39.7890027669192', "pla_catast" = '07025A00100946' where "pla_codi" = 363;
update "public"."oli_plantacio" set "pla_coordx" = '2.74755701770336', "pla_coordy" = '39.7724214596981', "pla_catast" = '07025A00100163' where "pla_codi" = 371;
update "public"."oli_plantacio" set "pla_coordx" = '2.69891545359754', "pla_coordy" = '39.7637708893752', "pla_catast" = '07061A00500283' where "pla_codi" = 376;
update "public"."oli_plantacio" set "pla_coordx" = '3.37080303805098', "pla_coordy" = '39.7239794030939', "pla_catast" = '07006A00600005' where "pla_codi" = 377;
update "public"."oli_plantacio" set "pla_coordx" = '2.7564406971588', "pla_coordy" = '39.7678588809796', "pla_catast" = '07061A00401685' where "pla_codi" = 380;
update "public"."oli_plantacio" set "pla_coordx" = '2.73561863764256', "pla_coordy" = '39.7871585752338', "pla_catast" = '07025A00100961' where "pla_codi" = 383;
update "public"."oli_plantacio" set "pla_coordx" = '2.99791030640579', "pla_coordy" = '39.7226719132683', "pla_catast" = '07030A00300687' where "pla_codi" = 387;
update "public"."oli_plantacio" set "pla_coordx" = '2.7578643624404', "pla_coordy" = '39.7940201277148', "pla_catast" = '07025A00100375' where "pla_codi" = 390;
update "public"."oli_plantacio" set "pla_coordx" = '3.07871018471741', "pla_coordy" = '39.7145313827627', "pla_catast" = '07039A00500297' where "pla_codi" = 393;
update "public"."oli_plantacio" set "pla_coordx" = '2.48903073947141', "pla_coordy" = '39.6576060077132', "pla_catast" = '07021A00400017' where "pla_codi" = 399;
update "public"."oli_plantacio" set "pla_coordx" = '2.49000795504624', "pla_coordy" = '39.657239794932', "pla_catast" = '07021A00400020' where "pla_codi" = 402;
update "public"."oli_plantacio" set "pla_coordx" = '2.74828054512014', "pla_coordy" = '39.7795010415352', "pla_catast" = '07025A00100711' where "pla_codi" = 405;
update "public"."oli_plantacio" set "pla_coordx" = '2.90173709797568', "pla_coordy" = '39.7675667255387', "pla_catast" = '07058A02100105' where "pla_codi" = 409;
update "public"."oli_plantacio" set "pla_coordx" = '2.73391198719533', "pla_coordy" = '39.7891984303434', "pla_catast" = '07025A00100349' where "pla_codi" = 412;
update "public"."oli_plantacio" set "pla_coordx" = '3.22353859161408', "pla_coordy" = '39.4856088606925', "pla_catast" = '07033A02200342' where "pla_codi" = 418;
update "public"."oli_plantacio" set "pla_coordx" = '2.70283835759364', "pla_coordy" = '39.804093697227', "pla_catast" = '07061A00300272' where "pla_codi" = 422;
update "public"."oli_plantacio" set "pla_coordx" = '2.65310943613673', "pla_coordy" = '39.7476746776602', "pla_catast" = '07018A00200297' where "pla_codi" = 430;
update "public"."oli_plantacio" set "pla_coordx" = '2.70139120963069', "pla_coordy" = '39.8045320146285', "pla_catast" = '07061A00300268' where "pla_codi" = 433;
update "public"."oli_plantacio" set "pla_coordx" = '3.08180580478982', "pla_coordy" = '39.6821989213915', "pla_catast" = '07035A00100007' where "pla_codi" = 436;
update "public"."oli_plantacio" set "pla_coordx" = '2.74874491238335', "pla_coordy" = '39.7820877783286', "pla_catast" = '07025A00100697' where "pla_codi" = 443;
update "public"."oli_plantacio" set "pla_coordx" = '2.75497293052165', "pla_coordy" = '39.780205027014', "pla_catast" = '07025A00100125' where "pla_codi" = 446;
update "public"."oli_plantacio" set "pla_coordx" = '2.75077119404299', "pla_coordy" = '39.7827935959111', "pla_catast" = '07025A00100680' where "pla_codi" = 449;
update "public"."oli_plantacio" set "pla_coordx" = '2.75601224738232', "pla_coordy" = '39.7791115861467', "pla_catast" = '07025A00100133' where "pla_codi" = 453;
update "public"."oli_plantacio" set "pla_coordx" = '2.64080397307439', "pla_coordy" = '39.7461353342395', "pla_catast" = '07018A00200179' where "pla_codi" = 460;
update "public"."oli_plantacio" set "pla_coordx" = '2.73758256439193', "pla_coordy" = '39.7870319426352', "pla_catast" = '07025A00100972' where "pla_codi" = 463;
update "public"."oli_plantacio" set "pla_coordx" = '2.85926558393648', "pla_coordy" = '39.6943530416593', "pla_catast" = '07008A00700067' where "pla_codi" = 466;
update "public"."oli_plantacio" set "pla_coordx" = '3.17541676230227', "pla_coordy" = '39.5810618362827', "pla_catast" = '07033A02700598' where "pla_codi" = 469;
update "public"."oli_plantacio" set "pla_coordx" = '3.08215224380344', "pla_coordy" = '39.7211080061121', "pla_catast" = '07039A01400343' where "pla_codi" = 472;
update "public"."oli_plantacio" set "pla_coordx" = '3.08527453424039', "pla_coordy" = '39.6703082605164', "pla_catast" = '07035A00400362' where "pla_codi" = 476;
update "public"."oli_plantacio" set "pla_coordx" = '2.48986198864086', "pla_coordy" = '39.6627503368805', "pla_catast" = '07021A00400013' where "pla_codi" = 479;
update "public"."oli_plantacio" set "pla_coordx" = '2.48899070112154', "pla_coordy" = '39.6631433231779', "pla_catast" = '07021A00400007' where "pla_codi" = 482;
update "public"."oli_plantacio" set "pla_coordx" = '2.74825811359389', "pla_coordy" = '39.7748805914911', "pla_catast" = '07025A00100160' where "pla_codi" = 492;
update "public"."oli_plantacio" set "pla_coordx" = '3.03386587569581', "pla_coordy" = '39.7578876010833', "pla_catast" = '07044A01500091' where "pla_codi" = 494;
update "public"."oli_plantacio" set "pla_coordx" = '2.74463246209314', "pla_coordy" = '39.7816581077827', "pla_catast" = '07025A00100641' where "pla_codi" = 497;
update "public"."oli_plantacio" set "pla_coordx" = '2.60207301367446', "pla_coordy" = '39.700340486229', "pla_catast" = '07063A00200002' where "pla_codi" = 500;
update "public"."oli_plantacio" set "pla_coordx" = '3.40142503114189', "pla_coordy" = '39.6703190860422', "pla_catast" = '07014A01300016' where "pla_codi" = 504;
update "public"."oli_plantacio" set "pla_coordx" = '2.68399475282333', "pla_coordy" = '39.7896963303972', "pla_catast" = '07061A00100369' where "pla_codi" = 507;
update "public"."oli_plantacio" set "pla_coordx" = '3.07572555063502', "pla_coordy" = '39.7170822672182', "pla_catast" = '07039A00600240' where "pla_codi" = 510;
update "public"."oli_plantacio" set "pla_coordx" = '2.88206598263108', "pla_coordy" = '39.5447153652882', "pla_catast" = '07004A01600085' where "pla_codi" = 515;
update "public"."oli_plantacio" set "pla_coordx" = '2.93100179838092', "pla_coordy" = '39.6823758499151', "pla_catast" = '07047A00300233' where "pla_codi" = 520;
update "public"."oli_plantacio" set "pla_coordx" = '2.61661145824162', "pla_coordy" = '39.7397624927493', "pla_catast" = '07063A00100001' where "pla_codi" = 524;
update "public"."oli_plantacio" set "pla_coordx" = '2.74585181258148', "pla_coordy" = '39.7888594284195', "pla_catast" = '07025A00100470' where "pla_codi" = 527;
update "public"."oli_plantacio" set "pla_coordx" = '2.95312131925594', "pla_coordy" = '39.792388700725', "pla_catast" = '07012A01000117' where "pla_codi" = 530;
update "public"."oli_plantacio" set "pla_coordx" = '3.10430462658261', "pla_coordy" = '39.6703578553301', "pla_catast" = '07035A00500167' where "pla_codi" = 533;
update "public"."oli_plantacio" set "pla_coordx" = '2.93815926727223', "pla_coordy" = '39.5845773090938', "pla_catast" = '07004A02200708' where "pla_codi" = 539;
update "public"."oli_plantacio" set "pla_coordx" = '2.72326807707222', "pla_coordy" = '39.7932491181882', "pla_catast" = '07061A00300159' where "pla_codi" = 544;
update "public"."oli_plantacio" set "pla_coordx" = '2.73964992927736', "pla_coordy" = '39.7762670771176', "pla_catast" = '07025A00100727' where "pla_codi" = 546;
update "public"."oli_plantacio" set "pla_coordx" = '3.0621096776384', "pla_coordy" = '39.7225730498818', "pla_catast" = '07039A00500731' where "pla_codi" = 550;
update "public"."oli_plantacio" set "pla_coordx" = '3.06407625373718', "pla_coordy" = '39.665957188362', "pla_catast" = '07035A00300640' where "pla_codi" = 561;
update "public"."oli_plantacio" set "pla_coordx" = '3.06410996893053', "pla_coordy" = '39.6658567056638', "pla_catast" = '07035A00300875' where "pla_codi" = 564;
update "public"."oli_plantacio" set "pla_coordx" = '3.06460072283537', "pla_coordy" = '39.746941252882', "pla_catast" = '07039A01200136' where "pla_codi" = 568;
update "public"."oli_plantacio" set "pla_coordx" = '2.75466369239717', "pla_coordy" = '39.7862044093424', "pla_catast" = '07025A00100255' where "pla_codi" = 571;
update "public"."oli_plantacio" set "pla_coordx" = '2.73707893862742', "pla_coordy" = '39.7820935287667', "pla_catast" = '07025A00100302' where "pla_codi" = 574;
update "public"."oli_plantacio" set "pla_coordx" = '3.11395023460643', "pla_coordy" = '39.6978773168208', "pla_catast" = '07055A00600156' where "pla_codi" = 577;
update "public"."oli_plantacio" set "pla_coordx" = '2.88547568140558', "pla_coordy" = '39.6703990076914', "pla_catast" = '07008A01200182' where "pla_codi" = 581;
update "public"."oli_plantacio" set "pla_coordx" = '2.68212270240805', "pla_coordy" = '39.7749333922672', "pla_catast" = '07061A00100561' where "pla_codi" = 588;
update "public"."oli_plantacio" set "pla_coordx" = '2.68631232306564', "pla_coordy" = '39.773874035683', "pla_catast" = '07061A00500561' where "pla_codi" = 591;
update "public"."oli_plantacio" set "pla_coordx" = '2.56825816336913', "pla_coordy" = '39.6437754824038', "pla_catast" = '07020A00800008' where "pla_codi" = 594;
update "public"."oli_plantacio" set "pla_coordx" = '2.71114956142289', "pla_coordy" = '39.8099836794396', "pla_catast" = '07061A00300307' where "pla_codi" = 598;
update "public"."oli_plantacio" set "pla_coordx" = '2.70803370185494', "pla_coordy" = '39.8104166414666', "pla_catast" = '07061A00300281' where "pla_codi" = 601;
update "public"."oli_plantacio" set "pla_coordx" = '2.91513210341853', "pla_coordy" = '39.7669434711516', "pla_catast" = '07058A01800026' where "pla_codi" = 605;
update "public"."oli_plantacio" set "pla_coordx" = '2.94351995020951', "pla_coordy" = '39.7722945100641', "pla_catast" = '07058A01900099' where "pla_codi" = 608;
update "public"."oli_plantacio" set "pla_coordx" = '2.73382666829962', "pla_coordy" = '39.7798956572794', "pla_catast" = '07025A00100318' where "pla_codi" = 613;
update "public"."oli_plantacio" set "pla_coordx" = '2.68261739267768', "pla_coordy" = '39.7741812349877', "pla_catast" = '07061A00100543' where "pla_codi" = 620;
update "public"."oli_plantacio" set "pla_coordx" = '3.14140816321505', "pla_coordy" = '39.5804614929844', "pla_catast" = '07041A01300220' where "pla_codi" = 623;
update "public"."oli_plantacio" set "pla_coordx" = '3.14176869156634', "pla_coordy" = '39.5809313002456', "pla_catast" = '07041A01300221' where "pla_codi" = 624;
update "public"."oli_plantacio" set "pla_coordx" = '2.69477460672935', "pla_coordy" = '39.7738856053402', "pla_catast" = '07061A00500488' where "pla_codi" = 628;
update "public"."oli_plantacio" set "pla_coordx" = '2.69348134439952', "pla_coordy" = '39.7737468659683', "pla_catast" = '07061A00500486' where "pla_codi" = 632;
update "public"."oli_plantacio" set "pla_coordx" = '2.69486165136672', "pla_coordy" = '39.7750115523807', "pla_catast" = '07061A00500492' where "pla_codi" = 635;
update "public"."oli_plantacio" set "pla_coordx" = '2.75983048358903', "pla_coordy" = '39.7805150595912', "pla_catast" = '07025A00100097' where "pla_codi" = 638;
update "public"."oli_plantacio" set "pla_coordx" = '2.75974988650981', "pla_coordy" = '39.7823042201515', "pla_catast" = '07025A00100090' where "pla_codi" = 644;
update "public"."oli_plantacio" set "pla_coordx" = '3.01601179281975', "pla_coordy" = '39.747558386407', "pla_catast" = '07044A01500332' where "pla_codi" = 647;
update "public"."oli_plantacio" set "pla_coordx" = '2.5663032945797', "pla_coordy" = '39.6492350711333', "pla_catast" = '07020A00900069' where "pla_codi" = 652;
update "public"."oli_plantacio" set "pla_coordx" = '2.88789084160774', "pla_coordy" = '39.7591916577603', "pla_catast" = '07058A02100001' where "pla_codi" = 656;
update "public"."oli_plantacio" set "pla_coordx" = '2.72191509454937', "pla_coordy" = '39.7536716813641', "pla_catast" = '07061A00402256' where "pla_codi" = 659;
update "public"."oli_plantacio" set "pla_coordx" = '2.74068707542823', "pla_coordy" = '39.777483696419', "pla_catast" = '07025A00100741' where "pla_codi" = 663;
update "public"."oli_plantacio" set "pla_coordx" = '2.74411328709599', "pla_coordy" = '39.7823146121472', "pla_catast" = '07025A00100919' where "pla_codi" = 666;
update "public"."oli_plantacio" set "pla_coordx" = '2.72917588748017', "pla_coordy" = '39.7879346922693', "pla_catast" = '07025A00100024' where "pla_codi" = 669;
update "public"."oli_plantacio" set "pla_coordx" = '3.13588891257323', "pla_coordy" = '39.72847621503', "pla_catast" = '07055A01200120' where "pla_codi" = 677;
update "public"."oli_plantacio" set "pla_coordx" = '2.75323857270598', "pla_coordy" = '39.7899934759883', "pla_catast" = '07025A00100405' where "pla_codi" = 682;
update "public"."oli_plantacio" set "pla_coordx" = '2.70774087458248', "pla_coordy" = '39.7975832219716', "pla_catast" = '07061A00200523' where "pla_codi" = 687;
update "public"."oli_plantacio" set "pla_coordx" = '2.85309253492234', "pla_coordy" = '39.7495588362674', "pla_catast" = '07034A00300242' where "pla_codi" = 690;
update "public"."oli_plantacio" set "pla_coordx" = '3.22730199477688', "pla_coordy" = '39.5649112465915', "pla_catast" = '07033A02900168' where "pla_codi" = 693;
update "public"."oli_plantacio" set "pla_coordx" = '2.71709984569444', "pla_coordy" = '39.7901872790796', "pla_catast" = '07061A00300482' where "pla_codi" = 698;
update "public"."oli_plantacio" set "pla_coordx" = '2.72580021818007', "pla_coordy" = '39.7911999381851', "pla_catast" = '07025A00100015' where "pla_codi" = 701;
update "public"."oli_plantacio" set "pla_coordx" = '2.72131873989653', "pla_coordy" = '39.7890661983587', "pla_catast" = '07061A00300183' where "pla_codi" = 702;
update "public"."oli_plantacio" set "pla_coordx" = '2.41156316639466', "pla_coordy" = '39.5699652651901', "pla_catast" = '07005A01600544' where "pla_codi" = 708;
update "public"."oli_plantacio" set "pla_coordx" = '2.73349458179411', "pla_coordy" = '39.7791919250995', "pla_catast" = '07025A00100322' where "pla_codi" = 715;
update "public"."oli_plantacio" set "pla_coordx" = '2.7981003293098', "pla_coordy" = '39.7162562923774', "pla_catast" = '07001A00100115' where "pla_codi" = 720;
update "public"."oli_plantacio" set "pla_coordx" = '2.73002190925005', "pla_coordy" = '39.7649497894071', "pla_catast" = '07061A00401252' where "pla_codi" = 721;
update "public"."oli_plantacio" set "pla_coordx" = '3.15625088248587', "pla_coordy" = '39.3334238923246', "pla_catast" = '07057A00300718' where "pla_codi" = 724;
update "public"."oli_plantacio" set "pla_coordx" = '3.07446912857768', "pla_coordy" = '39.714827815024', "pla_catast" = '07039A00500263' where "pla_codi" = 727;
update "public"."oli_plantacio" set "pla_coordx" = '2.65101017764491', "pla_coordy" = '39.7624279735761', "pla_catast" = '07018A00300014' where "pla_codi" = 730;
update "public"."oli_plantacio" set "pla_coordx" = '2.74052560800146', "pla_coordy" = '39.7848882160758', "pla_catast" = '07025A00100619' where "pla_codi" = 740;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 712;
update "public"."oli_plantacio" set "pla_coordx" = '2.70256279875386', "pla_coordy" = '39.7644473065548', "pla_catast" = '07061A00500298' where "pla_codi" = 746;
update "public"."oli_plantacio" set "pla_coordx" = '3.08443453959634', "pla_coordy" = '39.7526342348511', "pla_catast" = '07039A01100110' where "pla_codi" = 753;
update "public"."oli_plantacio" set "pla_coordx" = '2.76075573527573', "pla_coordy" = '39.782130424388', "pla_catast" = '07025A00100087' where "pla_codi" = 756;
update "public"."oli_plantacio" set "pla_coordx" = '2.86143525771061', "pla_coordy" = '39.7146706681017', "pla_catast" = '07029A00100989' where "pla_codi" = 759;
update "public"."oli_plantacio" set "pla_coordx" = '2.88045907108198', "pla_coordy" = '39.7294140750491', "pla_catast" = '07058A01100077' where "pla_codi" = 763;
update "public"."oli_plantacio" set "pla_coordx" = '2.59079347765072', "pla_coordy" = '39.6692144179729', "pla_catast" = '07020A00500091' where "pla_codi" = 766;
update "public"."oli_plantacio" set "pla_coordx" = '2.71981239794797', "pla_coordy" = '39.7838970843342', "pla_catast" = '07061A00300203' where "pla_codi" = 769;
update "public"."oli_plantacio" set "pla_coordx" = '2.72117624787743', "pla_coordy" = '39.7848382334178', "pla_catast" = '07061A00300471' where "pla_codi" = 773;
update "public"."oli_plantacio" set "pla_coordx" = '2.71396137315262', "pla_coordy" = '39.7906950395708', "pla_catast" = '07061A00200360' where "pla_codi" = 776;
update "public"."oli_plantacio" set "pla_coordx" = '2.84625116333702', "pla_coordy" = '39.6584720633571', "pla_catast" = '07047A00900059' where "pla_codi" = 784;
update "public"."oli_plantacio" set "pla_coordx" = '2.78690670887826', "pla_coordy" = '39.6983794995262', "pla_catast" = '07001A00400039' where "pla_codi" = 787;
update "public"."oli_plantacio" set "pla_coordx" = '3.01915091311158', "pla_coordy" = '39.3612640843799', "pla_catast" = '07013A01600428' where "pla_codi" = 790;
update "public"."oli_plantacio" set "pla_coordx" = '2.92081114630821', "pla_coordy" = '39.7491085510522', "pla_catast" = '07058A00900138' where "pla_codi" = 793;
update "public"."oli_plantacio" set "pla_coordx" = '2.85789746391218', "pla_coordy" = '39.7488067336436', "pla_catast" = '07034A00300251' where "pla_codi" = 796;
update "public"."oli_plantacio" set "pla_coordx" = '2.87337077748283', "pla_coordy" = '39.7239880230495', "pla_catast" = '07029A00100648' where "pla_codi" = 802;
update "public"."oli_plantacio" set "pla_coordx" = '2.87905470110422', "pla_coordy" = '39.6836441258959', "pla_catast" = '07008A00400031' where "pla_codi" = 805;
update "public"."oli_plantacio" set "pla_coordx" = '2.87575338020648', "pla_coordy" = '39.609811011523', "pla_catast" = '07047A01800179' where "pla_codi" = 812;
update "public"."oli_plantacio" set "pla_coordx" = '2.52221290825699', "pla_coordy" = '39.6603120059553', "pla_catast" = '07045A00200164' where "pla_codi" = 818;
update "public"."oli_plantacio" set "pla_coordx" = '3.05990252193557', "pla_coordy" = '39.4105783371233', "pla_catast" = '07013A01300494' where "pla_codi" = 828;
update "public"."oli_plantacio" set "pla_coordx" = '2.76604378158794', "pla_coordy" = '39.6858529143837', "pla_catast" = '07056A00800306' where "pla_codi" = 832;
update "public"."oli_plantacio" set "pla_coordx" = '3.04951063400165', "pla_coordy" = '39.7243114809655', "pla_catast" = '07039A00500864' where "pla_codi" = 836;
update "public"."oli_plantacio" set "pla_coordx" = '3.00565345592346', "pla_coordy" = '39.3921071743164', "pla_catast" = '07013A01900488' where "pla_codi" = 840;
update "public"."oli_plantacio" set "pla_coordx" = '3.37923886907125', "pla_coordy" = '39.6979678349342', "pla_catast" = '07006A01700120' where "pla_codi" = 845;
update "public"."oli_plantacio" set "pla_coordx" = '3.37867589981301', "pla_coordy" = '39.6999969263076', "pla_catast" = '07006A01700342' where "pla_codi" = 848;
update "public"."oli_plantacio" set "pla_coordx" = '2.585723681187', "pla_coordy" = '39.6810006425841', "pla_catast" = '07020A00400095' where "pla_codi" = 854;
update "public"."oli_plantacio" set "pla_coordx" = '2.8854897419891', "pla_coordy" = '39.654965496379', "pla_catast" = '07047A00700146' where "pla_codi" = 855;
update "public"."oli_plantacio" set "pla_coordx" = '3.06521481929332', "pla_coordy" = '39.771957026373', "pla_catast" = '07039A00400327' where "pla_codi" = 860;
update "public"."oli_plantacio" set "pla_coordx" = '3.21136965850197', "pla_coordy" = '39.6619664033552', "pla_catast" = '07033A00200003' where "pla_codi" = 863;
update "public"."oli_plantacio" set "pla_coordx" = '2.82286409614997', "pla_coordy" = '39.7387542771611', "pla_catast" = '07001A00200126' where "pla_codi" = 867;
update "public"."oli_plantacio" set "pla_coordx" = '2.71211237035856', "pla_coordy" = '39.7836050297648', "pla_catast" = '07061A00300227' where "pla_codi" = 870;
update "public"."oli_plantacio" set "pla_coordx" = '2.71576753598716', "pla_coordy" = '39.7868456054332', "pla_catast" = '07061A00300231' where "pla_codi" = 874;
update "public"."oli_plantacio" set "pla_coordx" = '3.17714528491639', "pla_coordy" = '39.5486403452339', "pla_catast" = '07033A02501642' where "pla_codi" = 881;
update "public"."oli_plantacio" set "pla_coordx" = '3.1783387421458', "pla_coordy" = '39.548871351448', "pla_catast" = '07033A02500496' where "pla_codi" = 884;
update "public"."oli_plantacio" set "pla_coordx" = '2.6831172614484', "pla_coordy" = '39.7752275873039', "pla_catast" = '07061A00100413' where "pla_codi" = 888;
update "public"."oli_plantacio" set "pla_coordx" = '2.9366707487164', "pla_coordy" = '39.7701236474218', "pla_catast" = '07058A01900073' where "pla_codi" = 891;
update "public"."oli_plantacio" set "pla_coordx" = '3.06202822573635', "pla_coordy" = '39.7789147903106', "pla_catast" = '07044A00300017' where "pla_codi" = 894;
update "public"."oli_plantacio" set "pla_coordx" = '2.49282909102937', "pla_coordy" = '39.6480694538814', "pla_catast" = '07021A00400107' where "pla_codi" = 897;
update "public"."oli_plantacio" set "pla_coordx" = '2.99321573189413', "pla_coordy" = '39.4431903331694', "pla_catast" = '07013A00600058' where "pla_codi" = 901;
update "public"."oli_plantacio" set "pla_coordx" = '2.68561108223808', "pla_coordy" = '39.7744278789436', "pla_catast" = '07061A00100568' where "pla_codi" = 904;
update "public"."oli_plantacio" set "pla_coordx" = '3.03593250091403', "pla_coordy" = '39.6711353931439', "pla_catast" = '07030A00700234' where "pla_codi" = 918;
update "public"."oli_plantacio" set "pla_coordx" = '3.15130150942342', "pla_coordy" = '39.6625390498441', "pla_catast" = '07066A00500117' where "pla_codi" = 921;
update "public"."oli_plantacio" set "pla_coordx" = '3.37275940357582', "pla_coordy" = '39.6304887625537', "pla_catast" = '07062A00400373' where "pla_codi" = 925;
update "public"."oli_plantacio" set "pla_coordx" = '3.10597890009884', "pla_coordy" = '39.8451846627838', "pla_catast" = '07003A00400224' where "pla_codi" = 929;
update "public"."oli_plantacio" set "pla_coordx" = '2.88151059240066', "pla_coordy" = '39.7436599844292', "pla_catast" = '07034A00200099' where "pla_codi" = 932;
update "public"."oli_plantacio" set "pla_coordx" = '2.70724161554733', "pla_coordy" = '39.7941146116701', "pla_catast" = '07061A00200580' where "pla_codi" = 933;
update "public"."oli_plantacio" set "pla_coordx" = '2.66806193968348', "pla_coordy" = '39.6277499245945', "pla_catast" = '07040A02300119' where "pla_codi" = 936;
update "public"."oli_plantacio" set "pla_coordx" = '3.23938522594091', "pla_coordy" = '39.5817659062541', "pla_catast" = '07033A00600243' where "pla_codi" = 942;
update "public"."oli_plantacio" set "pla_coordx" = '3.17640398532107', "pla_coordy" = '39.5492294977116', "pla_catast" = '07033A02501592' where "pla_codi" = 949;
update "public"."oli_plantacio" set "pla_coordx" = '3.06804036969645', "pla_coordy" = '39.7211699072244', "pla_catast" = '07039A00600045' where "pla_codi" = 993;
update "public"."oli_plantacio" set "pla_coordx" = '3.0638287586657', "pla_coordy" = '39.7369815335917', "pla_catast" = '07039A01300377' where "pla_codi" = 956;
update "public"."oli_plantacio" set "pla_coordx" = '2.53499228126039', "pla_coordy" = '39.6541128372978', "pla_catast" = '07045A00200166' where "pla_codi" = 964;
update "public"."oli_plantacio" set "pla_coordx" = '2.59418635811556', "pla_coordy" = '39.7044770080202', "pla_catast" = '07063A00100105' where "pla_codi" = 968;
update "public"."oli_plantacio" set "pla_coordx" = '3.05532122201984', "pla_coordy" = '39.4431820555231', "pla_catast" = '07013A01000395' where "pla_codi" = 971;
update "public"."oli_plantacio" set "pla_coordx" = '3.06044281595579', "pla_coordy" = '39.4437260806764', "pla_catast" = '07013A01000492' where "pla_codi" = 975;
update "public"."oli_plantacio" set "pla_coordx" = '2.79513176000354', "pla_coordy" = '39.6845308070543', "pla_catast" = '07001A00400345' where "pla_codi" = 979;
update "public"."oli_plantacio" set "pla_coordx" = '3.18209136674092', "pla_coordy" = '39.5747840791672', "pla_catast" = '07033A02700518' where "pla_codi" = 982;
update "public"."oli_plantacio" set "pla_coordx" = '3.26473351400239', "pla_coordy" = '39.5936666792211', "pla_catast" = '07033A00600285' where "pla_codi" = 986;
update "public"."oli_plantacio" set "pla_coordx" = '2.5422459669075', "pla_coordy" = '39.6265249015547', "pla_catast" = '07045A00300152' where "pla_codi" = 997;
update "public"."oli_plantacio" set "pla_coordx" = '2.59274337673855', "pla_coordy" = '39.6614252258949', "pla_catast" = '07020A00500087' where "pla_codi" = 1000;
update "public"."oli_plantacio" set "pla_coordx" = '3.14005564377858', "pla_coordy" = '39.6456020350547', "pla_catast" = '07066A00600490' where "pla_codi" = 1005;
update "public"."oli_plantacio" set "pla_coordx" = '2.68783208985288', "pla_coordy" = '39.6841607804014', "pla_catast" = '07010A00700006' where "pla_codi" = 1007;
update "public"."oli_plantacio" set "pla_coordx" = '2.72152080215641', "pla_coordy" = '39.7816084033572', "pla_catast" = '07061A00400460' where "pla_codi" = 1008;
update "public"."oli_plantacio" set "pla_coordx" = '2.63418452125814', "pla_coordy" = '39.7400621837944', "pla_catast" = '07018A00200368' where "pla_codi" = 1012;
update "public"."oli_plantacio" set "pla_coordx" = '2.63025985836154', "pla_coordy" = '39.7445685849211', "pla_catast" = '07018A00200373' where "pla_codi" = 1015;
update "public"."oli_plantacio" set "pla_coordx" = '2.68316642629322', "pla_coordy" = '39.778642351925', "pla_catast" = '07061A00100606' where "pla_codi" = 1018;
update "public"."oli_plantacio" set "pla_coordx" = '3.3282565262684', "pla_coordy" = '39.5702227302268', "pla_catast" = '07033A01100188' where "pla_codi" = 1024;
update "public"."oli_plantacio" set "pla_coordx" = '2.8608102121725', "pla_coordy" = '39.7486899117118', "pla_catast" = '07034A00300257' where "pla_codi" = 1028;
update "public"."oli_plantacio" set "pla_coordx" = '3.10093642517443', "pla_coordy" = '39.7594693329967', "pla_catast" = '07039A01000148' where "pla_codi" = 1031;
update "public"."oli_plantacio" set "pla_coordx" = '3.05371898891267', "pla_coordy" = '39.7059128693993', "pla_catast" = '07030A00400040' where "pla_codi" = 1035;
update "public"."oli_plantacio" set "pla_coordx" = '3.05371898891267', "pla_coordy" = '39.7059128693993', "pla_catast" = '07030A00400040' where "pla_codi" = 1038;
update "public"."oli_plantacio" set "pla_coordx" = '3.17050748489875', "pla_coordy" = '39.3749148047564', "pla_catast" = '07057A00700883' where "pla_codi" = 1041;
update "public"."oli_plantacio" set "pla_coordx" = '2.72793684184475', "pla_coordy" = '39.6161605162616', "pla_catast" = '07036A00400213' where "pla_codi" = 1049;
update "public"."oli_plantacio" set "pla_coordx" = '2.68887576444802', "pla_coordy" = '39.7722438964775', "pla_catast" = '07061A00500463' where "pla_codi" = 1071;
update "public"."oli_plantacio" set "pla_coordx" = '3.05292650512259', "pla_coordy" = '39.6685924165222', "pla_catast" = '07035A00200696' where "pla_codi" = 1078;
update "public"."oli_plantacio" set "pla_coordx" = '3.37866191354568', "pla_coordy" = '39.6134665949753', "pla_catast" = '07062A00200052' where "pla_codi" = 1081;
update "public"."oli_plantacio" set "pla_coordx" = '2.9112777645872', "pla_coordy" = '39.5283065739896', "pla_catast" = '07004A02500187' where "pla_codi" = 1083;
update "public"."oli_plantacio" set "pla_coordx" = '2.79427895167259', "pla_coordy" = '39.6472694217492', "pla_catast" = '07056A00400454' where "pla_codi" = 1085;
update "public"."oli_plantacio" set "pla_coordx" = '2.87510581443642', "pla_coordy" = '39.7333907796055', "pla_catast" = '07058A02000040' where "pla_codi" = 1089;
update "public"."oli_plantacio" set "pla_coordx" = '3.00708150142162', "pla_coordy" = '39.7785458679279', "pla_catast" = '07009A00400133' where "pla_codi" = 1093;
update "public"."oli_plantacio" set "pla_coordx" = '2.84506404335537', "pla_coordy" = '39.6744007773897', "pla_catast" = '07008A00300117' where "pla_codi" = 1104;
update "public"."oli_plantacio" set "pla_coordx" = '3.16467164853453', "pla_coordy" = '39.4862394818109', "pla_catast" = '07022A01700211' where "pla_codi" = 1109;
update "public"."oli_plantacio" set "pla_coordx" = '2.65462324293712', "pla_coordy" = '39.6127235296277', "pla_catast" = '07040A02100034' where "pla_codi" = 1114;
update "public"."oli_plantacio" set "pla_coordx" = '2.51582895414982', "pla_coordy" = '39.6886161588153', "pla_catast" = '07007A00200013' where "pla_codi" = 1118;
update "public"."oli_plantacio" set "pla_coordx" = '2.89157846963026', "pla_coordy" = '39.7273378714252', "pla_catast" = '07027A00900216' where "pla_codi" = 1122;
update "public"."oli_plantacio" set "pla_coordx" = '2.86575059790316', "pla_coordy" = '39.4918843637259', "pla_catast" = '07031A04200927' where "pla_codi" = 1128;
update "public"."oli_plantacio" set "pla_coordx" = '3.05926003018556', "pla_coordy" = '39.7704519208751', "pla_catast" = '07044A01000058' where "pla_codi" = 1134;
update "public"."oli_plantacio" set "pla_coordx" = '3.06202981775409', "pla_coordy" = '39.7148077996689', "pla_catast" = '07039A00500702' where "pla_codi" = 1137;
update "public"."oli_plantacio" set "pla_coordx" = '3.29995009123215', "pla_coordy" = '39.7384259125952', "pla_catast" = '07006A00500022' where "pla_codi" = 1141;
update "public"."oli_plantacio" set "pla_coordx" = '2.74480201612239', "pla_coordy" = '39.6022818316956', "pla_catast" = '07036A00400151' where "pla_codi" = 1147;
update "public"."oli_plantacio" set "pla_coordx" = '3.0343478354272', "pla_coordy" = '39.4026039103944', "pla_catast" = '07013A01500225' where "pla_codi" = 1150;
update "public"."oli_plantacio" set "pla_coordx" = '2.89740714751393', "pla_coordy" = '39.7572455157132', "pla_catast" = '07058A02100128' where "pla_codi" = 1153;
update "public"."oli_plantacio" set "pla_coordx" = '2.88216080914217', "pla_coordy" = '39.6879463971691', "pla_catast" = '07008A00400078' where "pla_codi" = 1156;
update "public"."oli_plantacio" set "pla_coordx" = '2.88164240436259', "pla_coordy" = '39.7258961365508', "pla_catast" = '07029A00100929' where "pla_codi" = 1160;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 952;
update "public"."oli_plantacio" set "pla_coordx" = '3.07272685675586', "pla_coordy" = '39.8812584661332', "pla_catast" = '07042A00300531' where "pla_codi" = 34;
update "public"."oli_plantacio" set "pla_coordx" = '2.98562484205402', "pla_coordy" = '39.8448870741491', "pla_catast" = '07042A00600472' where "pla_codi" = 195;
update "public"."oli_plantacio" set "pla_coordx" = '3.03729860270945', "pla_coordy" = '39.9070986750026', "pla_catast" = '07042A00400548' where "pla_codi" = 240;
update "public"."oli_plantacio" set "pla_coordx" = '3.05331169929101', "pla_coordy" = '39.8568691376363', "pla_catast" = '07042A00100410' where "pla_codi" = 374;
update "public"."oli_plantacio" set "pla_coordx" = '3.0328651631654', "pla_coordy" = '39.4993405097447', "pla_catast" = '07043A01100528' where "pla_codi" = 459;
update "public"."oli_plantacio" set "pla_coordx" = '3.09290780651906', "pla_coordy" = '39.5787467327836', "pla_catast" = '07065A00100252' where "pla_codi" = 654;
update "public"."oli_plantacio" set "pla_coordx" = '3.09351934446878', "pla_coordy" = '39.5790142976875', "pla_catast" = '07065A00100566' where "pla_codi" = 718;
update "public"."oli_plantacio" set "pla_coordx" = '3.06917639003655', "pla_coordy" = '39.8761102510071', "pla_catast" = '07042A00300753' where "pla_codi" = 780;
update "public"."oli_plantacio" set "pla_coordx" = '3.05214151300543', "pla_coordy" = '39.4853551851359', "pla_catast" = '07043A01000042' where "pla_codi" = 822;
update "public"."oli_plantacio" set "pla_coordx" = '2.9983961367518', "pla_coordy" = '39.5675766662323', "pla_catast" = '07038A01000163' where "pla_codi" = 826;
update "public"."oli_plantacio" set "pla_coordx" = '2.97288894085938', "pla_coordy" = '39.5486630641622', "pla_catast" = '07038A00300041' where "pla_codi" = 857;
update "public"."oli_plantacio" set "pla_coordx" = '3.01761004287695', "pla_coordy" = '39.5090825474413', "pla_catast" = '07043A01300306' where "pla_codi" = 913;
update "public"."oli_plantacio" set "pla_coordx" = '3.02244632899962', "pla_coordy" = '39.5086010889205', "pla_catast" = '07043A01300566' where "pla_codi" = 917;
update "public"."oli_plantacio" set "pla_coordx" = '3.06377657578228', "pla_coordy" = '39.7782910603945', "pla_catast" = '07044A00300009' where "pla_codi" = 939;
update "public"."oli_plantacio" set "pla_coordx" = '3.02272296356281', "pla_coordy" = '39.8729212693546', "pla_catast" = '07042A00100152' where "pla_codi" = 1076;
update "public"."oli_plantacio" set "pla_coordx" = '3.06283774722209', "pla_coordy" = '39.8962176512408', "pla_catast" = '07042A01000314' where "pla_codi" = 1102;
update "public"."oli_plantacio" set "pla_coordx" = '3.04905802160415', "pla_coordy" = '39.8803095239777', "pla_catast" = '07042A00300275' where "pla_codi" = 1120;
update "public"."oli_plantacio" set "pla_coordx" = '2.97941773067157', "pla_coordy" = '39.7239218766522', "pla_catast" = '07027A00700651' where "pla_codi" = 1171;
update "public"."oli_plantacio" set "pla_coordx" = '3.05220733746845', "pla_coordy" = '39.5084751613338', "pla_catast" = '07043A00600338' where "pla_codi" = 1172;
update "public"."oli_plantacio" set "pla_coordx" = '3.04660251645483', "pla_coordy" = '39.5093421086011', "pla_catast" = '07043A00600147' where "pla_codi" = 1175;
update "public"."oli_plantacio" set "pla_coordx" = '2.88137117522791', "pla_coordy" = '39.7340865590812', "pla_catast" = '07058A01100243' where "pla_codi" = 1179;
update "public"."oli_plantacio" set "pla_coordx" = '2.40264179603081', "pla_coordy" = '39.5533658933942', "pla_catast" = '07005A01000106' where "pla_codi" = 1182;
update "public"."oli_plantacio" set "pla_coordx" = '2.97970049426521', "pla_coordy" = '39.7249981003542', "pla_catast" = '07027A00700299' where "pla_codi" = 1189;
update "public"."oli_plantacio" set "pla_coordx" = '3.02605991658467', "pla_coordy" = '39.7146275520922', "pla_catast" = '07030A00400322' where "pla_codi" = 1192;
update "public"."oli_plantacio" set "pla_coordx" = '2.75697253338581', "pla_coordy" = '39.6801481315076', "pla_catast" = '07056A00800271' where "pla_codi" = 1195;
update "public"."oli_plantacio" set "pla_coordx" = '2.90352550243711', "pla_coordy" = '39.491307853524', "pla_catast" = '07031A01600079' where "pla_codi" = 1201;
update "public"."oli_plantacio" set "pla_coordx" = '2.98370838014257', "pla_coordy" = '39.7772699618433', "pla_catast" = '07012A00300055' where "pla_codi" = 1207;
update "public"."oli_plantacio" set "pla_coordx" = '3.33640348418161', "pla_coordy" = '39.5740391417496', "pla_catast" = '07033A01100075' where "pla_codi" = 1210;
update "public"."oli_plantacio" set "pla_coordx" = '3.10289740396948', "pla_coordy" = '39.6152266249372', "pla_catast" = '07041A00100067' where "pla_codi" = 1214;
update "public"."oli_plantacio" set "pla_coordx" = '3.08199364012135', "pla_coordy" = '39.604141561894', "pla_catast" = '07041A00400575' where "pla_codi" = 1218;
update "public"."oli_plantacio" set "pla_coordx" = '3.09016907467379', "pla_coordy" = '39.6136152509653', "pla_catast" = '07041A00300078' where "pla_codi" = 1221;
update "public"."oli_plantacio" set "pla_coordx" = '3.02405469081802', "pla_coordy" = '39.7610819088041', "pla_catast" = '07044A01500193' where "pla_codi" = 1229;
update "public"."oli_plantacio" set "pla_coordx" = '3.11986045803266', "pla_coordy" = '39.6461081632071', "pla_catast" = '07066A00600382' where "pla_codi" = 1233;
update "public"."oli_plantacio" set "pla_coordx" = '3.06748758628183', "pla_coordy" = '39.8355672231063', "pla_catast" = '07003A00600150' where "pla_codi" = 1237;
update "public"."oli_plantacio" set "pla_coordx" = '2.98237320959032', "pla_coordy" = '39.855181568051', "pla_catast" = '07042A00800009' where "pla_codi" = 1241;
update "public"."oli_plantacio" set "pla_coordx" = '2.63154809199809', "pla_coordy" = '39.7418393822912', "pla_catast" = '07018A00200370' where "pla_codi" = 1246;
update "public"."oli_plantacio" set "pla_coordx" = '3.15528601676941', "pla_coordy" = '39.8519019439428', "pla_catast" = '07003A00300229' where "pla_codi" = 1249;
update "public"."oli_plantacio" set "pla_coordx" = '3.04224967811727', "pla_coordy" = '39.8662056383317', "pla_catast" = '07042A00200122' where "pla_codi" = 1252;
update "public"."oli_plantacio" set "pla_coordx" = '2.72890246426486', "pla_coordy" = '39.8083875615093', "pla_catast" = '07061A00300445' where "pla_codi" = 1257;
update "public"."oli_plantacio" set "pla_coordx" = '3.37236932522293', "pla_coordy" = '39.6307553659412', "pla_catast" = '07062A00400690' where "pla_codi" = 1262;
update "public"."oli_plantacio" set "pla_coordx" = '2.872526307361', "pla_coordy" = '39.7540501100621', "pla_catast" = '07034A00300022' where "pla_codi" = 1265;
update "public"."oli_plantacio" set "pla_coordx" = '3.11106149143808', "pla_coordy" = '39.7076847066482', "pla_catast" = '07055A00500209' where "pla_codi" = 1272;
update "public"."oli_plantacio" set "pla_coordx" = '2.69740810576972', "pla_coordy" = '39.5834070327252', "pla_catast" = '07040A03100006' where "pla_codi" = 1276;
update "public"."oli_plantacio" set "pla_coordx" = '2.97538794533112', "pla_coordy" = '39.4113283000323', "pla_catast" = '07013A00200274' where "pla_codi" = 1285;
update "public"."oli_plantacio" set "pla_coordx" = '2.9773778607375', "pla_coordy" = '39.4123360027357', "pla_catast" = '07013A00200271' where "pla_codi" = 1288;
update "public"."oli_plantacio" set "pla_coordx" = '3.22353859161408', "pla_coordy" = '39.4856088606925', "pla_catast" = '07033A02200342' where "pla_codi" = 1295;
update "public"."oli_plantacio" set "pla_coordx" = '3.29898264146203', "pla_coordy" = '39.7376527434435', "pla_catast" = '07006A00500087' where "pla_codi" = 1300;
update "public"."oli_plantacio" set "pla_coordx" = '3.39547375844071', "pla_coordy" = '39.6743505206795', "pla_catast" = '07014A01500114' where "pla_codi" = 1303;
update "public"."oli_plantacio" set "pla_coordx" = null, "pla_coordy" = null, "pla_catast" = null where "pla_codi" = 1166;
update "public"."oli_plantacio" set "pla_coordx" = '2.7819686405765', "pla_coordy" = '39.6372240453956', "pla_catast" = '07056A00500190' where "pla_codi" = 5;
update "public"."oli_plantacio" set "pla_coordx" = '3.35746346951443', "pla_coordy" = '39.5615023031876', "pla_catast" = '07033A01300132' where "pla_codi" = 10;
update "public"."oli_plantacio" set "pla_coordx" = '2.98724988451203', "pla_coordy" = '39.8561755366476', "pla_catast" = '07042A00800011' where "pla_codi" = 15;
update "public"."oli_plantacio" set "pla_coordx" = '3.15619091032755', "pla_coordy" = '39.5291100067235', "pla_catast" = '07033A02501281' where "pla_codi" = 27;
update "public"."oli_plantacio" set "pla_coordx" = '2.99962508196688', "pla_coordy" = '39.6376361350701', "pla_catast" = '07060A00200012' where "pla_codi" = 44;
update "public"."oli_plantacio" set "pla_coordx" = '3.03409963595375', "pla_coordy" = '39.743310570084', "pla_catast" = '07044A01500165' where "pla_codi" = 46;
update "public"."oli_plantacio" set "pla_coordx" = '2.68415684386548', "pla_coordy" = '39.7710588527665', "pla_catast" = '07061A00500449' where "pla_codi" = 63;
update "public"."oli_plantacio" set "pla_coordx" = '2.71059592148819', "pla_coordy" = '39.7958356755663', "pla_catast" = '07061A00200547' where "pla_codi" = 77;
update "public"."oli_plantacio" set "pla_coordx" = '2.74807273953829', "pla_coordy" = '39.781795832525', "pla_catast" = '07025A00100702' where "pla_codi" = 78;
update "public"."oli_plantacio" set "pla_coordx" = '0', "pla_coordy" = '0', "pla_catast" = '07018A00300030' where "pla_codi" = 650;
update "public"."oli_plantacio" set "pla_coordx" = '2.89604519746731', "pla_coordy" = '39.708422285523', "pla_catast" = '07027A01000132' where "pla_codi" = 97;
update "public"."oli_plantacio" set "pla_coordx" = '2.71147678300259', "pla_coordy" = '39.7874258824974', "pla_catast" = '07061A00200380' where "pla_codi" = 101;
update "public"."oli_plantacio" set "pla_coordx" = '2.67783118469544', "pla_coordy" = '39.6211044575381', "pla_catast" = '07036A00600166' where "pla_codi" = 104;
update "public"."oli_plantacio" set "pla_coordx" = '2.97279017866153', "pla_coordy" = '39.4096067785526', "pla_catast" = '07013A00200301' where "pla_codi" = 113;
update "public"."oli_plantacio" set "pla_coordx" = '3.30517331886081', "pla_coordy" = '39.7420364437729', "pla_catast" = '07006A00500034' where "pla_codi" = 127;
update "public"."oli_plantacio" set "pla_coordx" = '2.74353241528387', "pla_coordy" = '39.7747852716672', "pla_catast" = '07025A00100934' where "pla_codi" = 132;
update "public"."oli_plantacio" set "pla_coordx" = '2.40400888629804', "pla_coordy" = '39.5734779694928', "pla_catast" = '07005A01600099' where "pla_codi" = 141;
update "public"."oli_plantacio" set "pla_coordx" = '2.73749192352975', "pla_coordy" = '39.7785707865649', "pla_catast" = '07025A00100746' where "pla_codi" = 159;
update "public"."oli_plantacio" set "pla_coordx" = '2.86278245148576', "pla_coordy" = '39.7492517193489', "pla_catast" = '07034A00300399' where "pla_codi" = 160;
update "public"."oli_plantacio" set "pla_coordx" = '2.68982413352283', "pla_coordy" = '39.7641907970332', "pla_catast" = '07061A00500410' where "pla_codi" = 175;
update "public"."oli_plantacio" set "pla_coordx" = '2.90262437245771', "pla_coordy" = '39.6879944961122', "pla_catast" = '07027A00200375' where "pla_codi" = 184;
update "public"."oli_plantacio" set "pla_coordx" = '2.90094245394795', "pla_coordy" = '39.6900628147428', "pla_catast" = '07027A00200384' where "pla_codi" = 192;
update "public"."oli_plantacio" set "pla_coordx" = '2.75347943688911', "pla_coordy" = '39.7839186333042', "pla_catast" = '07025A00100257' where "pla_codi" = 202;
update "public"."oli_plantacio" set "pla_coordx" = '2.7490721676641', "pla_coordy" = '39.5425814110544', "pla_catast" = '07040A05400032' where "pla_codi" = 210;
update "public"."oli_plantacio" set "pla_coordx" = '2.68404569970896', "pla_coordy" = '39.7714141775342', "pla_catast" = '07061A00500450' where "pla_codi" = 224;
update "public"."oli_plantacio" set "pla_coordx" = '2.75822142830632', "pla_coordy" = '39.7846537701965', "pla_catast" = '07025A00100247' where "pla_codi" = 229;
update "public"."oli_plantacio" set "pla_coordx" = '2.92521283244611', "pla_coordy" = '39.6921750506588', "pla_catast" = '07027A00200002' where "pla_codi" = 233;
update "public"."oli_plantacio" set "pla_coordx" = '2.74957661429117', "pla_coordy" = '39.789382830938', "pla_catast" = '07025A00100415' where "pla_codi" = 249;
update "public"."oli_plantacio" set "pla_coordx" = '2.73235421677515', "pla_coordy" = '39.7916744198967', "pla_catast" = '07025A00100360' where "pla_codi" = 258;
update "public"."oli_plantacio" set "pla_coordx" = '2.75490056695461', "pla_coordy" = '39.7690131731395', "pla_catast" = '07061A00401675' where "pla_codi" = 266;
update "public"."oli_plantacio" set "pla_coordx" = '2.837410542708', "pla_coordy" = '39.7200204679803', "pla_catast" = '07001A00300301' where "pla_codi" = 282;
update "public"."oli_plantacio" set "pla_coordx" = '2.69448181103246', "pla_coordy" = '39.7617367241259', "pla_catast" = '07061A00500210' where "pla_codi" = 290;
update "public"."oli_plantacio" set "pla_coordx" = '2.71821921714524', "pla_coordy" = '39.791070472471', "pla_catast" = '07061A00300149' where "pla_codi" = 298;
update "public"."oli_plantacio" set "pla_coordx" = '2.72144101502716', "pla_coordy" = '39.5591097995962', "pla_catast" = '07040A04100056' where "pla_codi" = 312;
update "public"."oli_plantacio" set "pla_coordx" = '2.75513694951485', "pla_coordy" = '39.784511143913', "pla_catast" = '07025A00100253' where "pla_codi" = 315;
update "public"."oli_plantacio" set "pla_coordx" = '2.68666860373589', "pla_coordy" = '39.7726558463177', "pla_catast" = '07061A00500456' where "pla_codi" = 331;
update "public"."oli_plantacio" set "pla_coordx" = '2.85341962401263', "pla_coordy" = '39.6397314635232', "pla_catast" = '07047A00900327' where "pla_codi" = 342;
update "public"."oli_plantacio" set "pla_coordx" = '3.07924606477532', "pla_coordy" = '39.7192985142029', "pla_catast" = '07039A00600560' where "pla_codi" = 346;
update "public"."oli_plantacio" set "pla_coordx" = '2.72837454499733', "pla_coordy" = '39.7885418087644', "pla_catast" = '07025A00100021' where "pla_codi" = 362;
update "public"."oli_plantacio" set "pla_coordx" = '3.08707576208679', "pla_coordy" = '39.6659139916198', "pla_catast" = '07035A00400196' where "pla_codi" = 366;
update "public"."oli_plantacio" set "pla_coordx" = '3.15553007187839', "pla_coordy" = '39.5955863266009', "pla_catast" = '07033A02700826' where "pla_codi" = 382;
update "public"."oli_plantacio" set "pla_coordx" = '2.47108284682247', "pla_coordy" = '39.5948644681742', "pla_catast" = '07011A00300166' where "pla_codi" = 396;
update "public"."oli_plantacio" set "pla_coordx" = '2.48842189387766', "pla_coordy" = '39.6569253143463', "pla_catast" = '07021A00400018' where "pla_codi" = 400;
update "public"."oli_plantacio" set "pla_coordx" = '2.73149269469166', "pla_coordy" = '39.7905995048085', "pla_catast" = '07025A00100950' where "pla_codi" = 414;
update "public"."oli_plantacio" set "pla_coordx" = '3.06233291497376', "pla_coordy" = '39.4068214345245', "pla_catast" = '07013A01300622' where "pla_codi" = 425;
update "public"."oli_plantacio" set "pla_coordx" = '0', "pla_coordy" = '0', "pla_catast" = '07008A00600035' where "pla_codi" = 662;
update "public"."oli_plantacio" set "pla_coordx" = '0', "pla_coordy" = '0', "pla_catast" = '07036A00601634' where "pla_codi" = 1125;
update "public"."oli_plantacio" set "pla_coordx" = '3.05177153361664', "pla_coordy" = '39.863874946713', "pla_catast" = '07042A00200687' where "pla_codi" = 1270;
update "public"."oli_plantacio" set "pla_coordx" = '2.73352419090516', "pla_coordy" = '39.5684498103964', "pla_catast" = '07040A04100019' where "pla_codi" = 81;
update "public"."oli_plantacio" set "pla_coordx" = '3.08168283864947', "pla_coordy" = '39.6826395169932', "pla_catast" = '07035A00100005' where "pla_codi" = 435;
update "public"."oli_plantacio" set "pla_coordx" = '2.76075573527573', "pla_coordy" = '39.782130424388', "pla_catast" = '07025A00100087' where "pla_codi" = 454;
update "public"."oli_plantacio" set "pla_coordx" = '2.74387528227569', "pla_coordy" = '39.7727869492833', "pla_catast" = '07025A00101004' where "pla_codi" = 456;
update "public"."oli_plantacio" set "pla_coordx" = '3.38085901747645', "pla_coordy" = '39.6933872416054', "pla_catast" = '07006A01600126' where "pla_codi" = 467;
update "public"."oli_plantacio" set "pla_coordx" = '2.48993309853587', "pla_coordy" = '39.6630841161044', "pla_catast" = '07021A00400014' where "pla_codi" = 480;
update "public"."oli_plantacio" set "pla_coordx" = '3.377066472858', "pla_coordy" = '39.6554659495007', "pla_catast" = '07062A01700156' where "pla_codi" = 486;
update "public"."oli_plantacio" set "pla_coordx" = '2.96819190655965', "pla_coordy" = '39.5718605638393', "pla_catast" = '07038A00500187' where "pla_codi" = 495;
update "public"."oli_plantacio" set "pla_coordx" = '2.80232087278514', "pla_coordy" = '39.6535562034999', "pla_catast" = '07056A00300053' where "pla_codi" = 498;
update "public"."oli_plantacio" set "pla_coordx" = '3.0614080819841', "pla_coordy" = '39.8560280916422', "pla_catast" = '07042A00200284' where "pla_codi" = 516;
update "public"."oli_plantacio" set "pla_coordx" = '2.92892072982081', "pla_coordy" = '39.6825945156743', "pla_catast" = '07047A00300121' where "pla_codi" = 519;
update "public"."oli_plantacio" set "pla_coordx" = '2.67709192099072', "pla_coordy" = '39.7832094666856', "pla_catast" = '07061A00100441' where "pla_codi" = 535;
update "public"."oli_plantacio" set "pla_coordx" = '2.73964992927736', "pla_coordy" = '39.7762670771176', "pla_catast" = '07025A00100727' where "pla_codi" = 547;
update "public"."oli_plantacio" set "pla_coordx" = '2.70059888414413', "pla_coordy" = '39.747622472151', "pla_catast" = '07061A00401996' where "pla_codi" = 548;
update "public"."oli_plantacio" set "pla_coordx" = '3.10597890009884', "pla_coordy" = '39.8451846627838', "pla_catast" = '07003A00400224' where "pla_codi" = 553;
update "public"."oli_plantacio" set "pla_coordx" = '2.70565001425124', "pla_coordy" = '39.7573078197943', "pla_catast" = '07061A00500358' where "pla_codi" = 557;
update "public"."oli_plantacio" set "pla_coordx" = '3.06391575935682', "pla_coordy" = '39.6661261283096', "pla_catast" = '07035A00300641' where "pla_codi" = 562;
update "public"."oli_plantacio" set "pla_coordx" = '3.11273377260048', "pla_coordy" = '39.6981288939529', "pla_catast" = '07055A00600139' where "pla_codi" = 576;
update "public"."oli_plantacio" set "pla_coordx" = '3.34694640119762', "pla_coordy" = '39.6421040206811', "pla_catast" = '07062A01000154' where "pla_codi" = 584;
update "public"."oli_plantacio" set "pla_coordx" = '2.6830822666167', "pla_coordy" = '39.7727730616246', "pla_catast" = '07061A00500308' where "pla_codi" = 592;
update "public"."oli_plantacio" set "pla_coordx" = '2.81385650492809', "pla_coordy" = '39.65763987434', "pla_catast" = '07016A00500064' where "pla_codi" = 609;
update "public"."oli_plantacio" set "pla_coordx" = '2.73817204216821', "pla_coordy" = '39.7851986369023', "pla_catast" = '07025A00100439' where "pla_codi" = 616;
update "public"."oli_plantacio" set "pla_coordx" = '2.75908007531148', "pla_coordy" = '39.7876599052121', "pla_catast" = '07025A00100793' where "pla_codi" = 641;
update "public"."oli_plantacio" set "pla_coordx" = '2.72062288206341', "pla_coordy" = '39.7827666526058', "pla_catast" = '07061A00400462' where "pla_codi" = 649;
update "public"."oli_plantacio" set "pla_coordx" = '2.74446341587156', "pla_coordy" = '39.7823087170978', "pla_catast" = '07025A00100920' where "pla_codi" = 667;
update "public"."oli_plantacio" set "pla_coordx" = '2.7350744292884', "pla_coordy" = '39.7900793992307', "pla_catast" = '07025A00100352' where "pla_codi" = 673;
update "public"."oli_plantacio" set "pla_coordx" = '3.13588891257323', "pla_coordy" = '39.72847621503', "pla_catast" = '07055A01200120' where "pla_codi" = 680;
update "public"."oli_plantacio" set "pla_coordx" = '3.34953059733026', "pla_coordy" = '39.5831472160197', "pla_catast" = '07051A00200496' where "pla_codi" = 699;
update "public"."oli_plantacio" set "pla_coordx" = '2.72091574150457', "pla_coordy" = '39.7907615564736', "pla_catast" = '07061A00300176' where "pla_codi" = 705;
update "public"."oli_plantacio" set "pla_coordx" = '2.89142189898967', "pla_coordy" = '39.7643573607184', "pla_catast" = '07058A01200014' where "pla_codi" = 707;
update "public"."oli_plantacio" set "pla_coordx" = '3.04284566249233', "pla_coordy" = '39.7178797272466', "pla_catast" = '07030A00400215' where "pla_codi" = 723;
update "public"."oli_plantacio" set "pla_coordx" = '2.73134111089507', "pla_coordy" = '39.6174565186577', "pla_catast" = '07036A00400220' where "pla_codi" = 735;
update "public"."oli_plantacio" set "pla_coordx" = '2.95684250895948', "pla_coordy" = '39.7682294508413', "pla_catast" = '07012A00200176' where "pla_codi" = 742;
update "public"."oli_plantacio" set "pla_coordx" = '2.95646798558131', "pla_coordy" = '39.7679939695232', "pla_catast" = '07012A00200177' where "pla_codi" = 743;
update "public"."oli_plantacio" set "pla_coordx" = '2.75588818870692', "pla_coordy" = '39.7798192526711', "pla_catast" = '07025A00100126' where "pla_codi" = 749;
update "public"."oli_plantacio" set "pla_coordx" = '3.22704561375966', "pla_coordy" = '39.3909948288577', "pla_catast" = '07022A03100600' where "pla_codi" = 757;
update "public"."oli_plantacio" set "pla_coordx" = '2.72262138266107', "pla_coordy" = '39.7797305445616', "pla_catast" = '07061A00400586' where "pla_codi" = 772;
update "public"."oli_plantacio" set "pla_coordx" = '2.74646823687263', "pla_coordy" = '39.7804765842039', "pla_catast" = '07025A00100718' where "pla_codi" = 782;
update "public"."oli_plantacio" set "pla_coordx" = '3.01932644164025', "pla_coordy" = '39.530392900644', "pla_catast" = '07043A00200107' where "pla_codi" = 783;
update "public"."oli_plantacio" set "pla_coordx" = '2.72311497273364', "pla_coordy" = '39.7906761920646', "pla_catast" = '07061A00300188' where "pla_codi" = 801;
update "public"."oli_plantacio" set "pla_coordx" = '2.47627909925241', "pla_coordy" = '39.5671093176834', "pla_catast" = '07011A00600082' where "pla_codi" = 809;
update "public"."oli_plantacio" set "pla_coordx" = '3.4174179486292', "pla_coordy" = '39.7237096511836', "pla_catast" = '07014A00400182' where "pla_codi" = 820;
update "public"."oli_plantacio" set "pla_coordx" = '2.76065934581961', "pla_coordy" = '39.6835182604818', "pla_catast" = '07056A00800348' where "pla_codi" = 834;
update "public"."oli_plantacio" set "pla_coordx" = '2.64429020227987', "pla_coordy" = '39.7582002692101', "pla_catast" = '07018A00300056' where "pla_codi" = 852;
update "public"."oli_plantacio" set "pla_coordx" = '3.0760049164174', "pla_coordy" = '39.7653536744026', "pla_catast" = '07039A00400365' where "pla_codi" = 866;
update "public"."oli_plantacio" set "pla_coordx" = '3.01427162653282', "pla_coordy" = '39.3706497134824', "pla_catast" = '07013A01800566' where "pla_codi" = 877;
update "public"."oli_plantacio" set "pla_coordx" = '3.10638845172887', "pla_coordy" = '39.5815233117051', "pla_catast" = '07041A01400432' where "pla_codi" = 880;
update "public"."oli_plantacio" set "pla_coordx" = '3.03942187115386', "pla_coordy" = '39.3924989424613', "pla_catast" = '07013A01600511' where "pla_codi" = 898;
update "public"."oli_plantacio" set "pla_coordx" = '2.90796761550421', "pla_coordy" = '39.7414869388188', "pla_catast" = '07058A01400057' where "pla_codi" = 908;
update "public"."oli_plantacio" set "pla_coordx" = '2.70420500071297', "pla_coordy" = '39.7991241706841', "pla_catast" = '07061A00200510' where "pla_codi" = 912;
update "public"."oli_plantacio" set "pla_coordx" = '3.15398936711345', "pla_coordy" = '39.6635068249886', "pla_catast" = '07066A00500118' where "pla_codi" = 922;
update "public"."oli_plantacio" set "pla_coordx" = '2.46604012396734', "pla_coordy" = '39.5898105140172', "pla_catast" = '07011A00300145' where "pla_codi" = 928;
update "public"."oli_plantacio" set "pla_coordx" = '3.25363448179907', "pla_coordy" = '39.6599750126284', "pla_catast" = '07051A00700611' where "pla_codi" = 937;
update "public"."oli_plantacio" set "pla_coordx" = '3.00541701409031', "pla_coordy" = '39.643922884539', "pla_catast" = '07060A00100191' where "pla_codi" = 945;
update "public"."oli_plantacio" set "pla_coordx" = '3.06392904033973', "pla_coordy" = '39.7378896173294', "pla_catast" = '07039A01300324' where "pla_codi" = 955;
update "public"."oli_plantacio" set "pla_coordx" = '3.06392904033973', "pla_coordy" = '39.7378896173294', "pla_catast" = '07039A01300324' where "pla_codi" = 959;
update "public"."oli_plantacio" set "pla_coordx" = '3.33148325981881', "pla_coordy" = '39.5803851721265', "pla_catast" = '07051A00200132' where "pla_codi" = 966;
update "public"."oli_plantacio" set "pla_coordx" = '3.05585631356273', "pla_coordy" = '39.4436821565394', "pla_catast" = '07013A01000397' where "pla_codi" = 972;
update "public"."oli_plantacio" set "pla_coordx" = '3.26525342157899', "pla_coordy" = '39.5939756321066', "pla_catast" = '07033A00600286' where "pla_codi" = 987;
update "public"."oli_plantacio" set "pla_coordx" = '2.73452526102967', "pla_coordy" = '39.7350957224195', "pla_catast" = '07010A00700184' where "pla_codi" = 989;
update "public"."oli_plantacio" set "pla_coordx" = '2.99791030640579', "pla_coordy" = '39.7226719132683', "pla_catast" = '07030A00300687' where "pla_codi" = 1002;
update "public"."oli_plantacio" set "pla_coordx" = '3.09950695738052', "pla_coordy" = '39.6310955718101', "pla_catast" = '07041A00200353' where "pla_codi" = 1010;
update "public"."oli_plantacio" set "pla_coordx" = '2.68017588998194', "pla_coordy" = '39.7758831997009', "pla_catast" = '07061A00100610' where "pla_codi" = 1021;
update "public"."oli_plantacio" set "pla_coordx" = '2.85990485135257', "pla_coordy" = '39.7484788795976', "pla_catast" = '07034A00300254' where "pla_codi" = 1026;
update "public"."oli_plantacio" set "pla_coordx" = '3.41820439525553', "pla_coordy" = '39.6863824403753', "pla_catast" = '07014A01200148' where "pla_codi" = 1042;
update "public"."oli_plantacio" set "pla_coordx" = '3.03031918616256', "pla_coordy" = '39.7850227763932', "pla_catast" = '07044A01200195' where "pla_codi" = 1045;
update "public"."oli_plantacio" set "pla_coordx" = '2.72342755976743', "pla_coordy" = '39.7645493506261', "pla_catast" = '07061A00401262' where "pla_codi" = 1058;
update "public"."oli_plantacio" set "pla_coordx" = '3.06468943209188', "pla_coordy" = '39.8797912214959', "pla_catast" = '07042A00300689' where "pla_codi" = 1072;
update "public"."oli_plantacio" set "pla_coordx" = '3.22548927719835', "pla_coordy" = '39.5867935025968', "pla_catast" = '07033A00400799' where "pla_codi" = 1084;
update "public"."oli_plantacio" set "pla_coordx" = '2.69367673022727', "pla_coordy" = '39.6230865769787', "pla_catast" = '07036A00600137' where "pla_codi" = 1099;
update "public"."oli_plantacio" set "pla_coordx" = '2.83225112761766', "pla_coordy" = '39.6968806826367', "pla_catast" = '07008A01400461' where "pla_codi" = 1100;
update "public"."oli_plantacio" set "pla_coordx" = '3.01554913753763', "pla_coordy" = '39.890698862797', "pla_catast" = '07042A00400679' where "pla_codi" = 1119;
update "public"."oli_plantacio" set "pla_coordx" = '2.66498318040888', "pla_coordy" = '39.5890786962683', "pla_catast" = '07040A02400039' where "pla_codi" = 1138;
update "public"."oli_plantacio" set "pla_coordx" = '3.3004962688377', "pla_coordy" = '39.7369234265921', "pla_catast" = '07006A00500021' where "pla_codi" = 1140;
update "public"."oli_plantacio" set "pla_coordx" = '3.39599802793284', "pla_coordy" = '39.6292655281406', "pla_catast" = '07062A01800215' where "pla_codi" = 1143;
update "public"."oli_plantacio" set "pla_coordx" = '2.88262835679344', "pla_coordy" = '39.6888416596635', "pla_catast" = '07008A00400072' where "pla_codi" = 1155;
update "public"."oli_plantacio" set "pla_coordx" = '2.72615765335455', "pla_coordy" = '39.5948850047053', "pla_catast" = '07040A03200056' where "pla_codi" = 1176;
update "public"."oli_plantacio" set "pla_coordx" = '3.39616948030763', "pla_coordy" = '39.67677581088', "pla_catast" = '07014A01500039' where "pla_codi" = 1186;
update "public"."oli_plantacio" set "pla_coordx" = '3.32765605784794', "pla_coordy" = '39.5710244620315', "pla_catast" = '07033A01100226' where "pla_codi" = 1193;
update "public"."oli_plantacio" set "pla_coordx" = '3.33367465952127', "pla_coordy" = '39.5719425308819', "pla_catast" = '07033A01100100' where "pla_codi" = 1211;
update "public"."oli_plantacio" set "pla_coordx" = '2.49739549429266', "pla_coordy" = '39.5483209494503', "pla_catast" = '07011A01500429' where "pla_codi" = 1225;
update "public"."oli_plantacio" set "pla_coordx" = '2.80136237970584', "pla_coordy" = '39.5570739965132', "pla_catast" = '07040A04500069' where "pla_codi" = 1228;
update "public"."oli_plantacio" set "pla_coordx" = '3.14915544664891', "pla_coordy" = '39.8516353107271', "pla_catast" = '07003A00300233' where "pla_codi" = 1239;
update "public"."oli_plantacio" set "pla_coordx" = '2.78184234859984', "pla_coordy" = '39.6935730688976', "pla_catast" = '07001A00400065' where "pla_codi" = 1253;
update "public"."oli_plantacio" set "pla_coordx" = '2.79320663667752', "pla_coordy" = '39.7070042054299', "pla_catast" = '07001A00209001' where "pla_codi" = 1254;
update "public"."oli_plantacio" set "pla_coordx" = '3.0083051831647', "pla_coordy" = '39.5187847157398', "pla_catast" = '07043A01700254' where "pla_codi" = 1268;
update "public"."oli_plantacio" set "pla_coordx" = '3.12754084709348', "pla_coordy" = '39.5569384308826', "pla_catast" = '07065A00301336' where "pla_codi" = 1280;
update "public"."oli_plantacio" set "pla_coordx" = '3.12627860211221', "pla_coordy" = '39.5549606080472', "pla_catast" = '07065A00301338' where "pla_codi" = 1281;
update "public"."oli_plantacio" set "pla_coordx" = '2.94277919622166', "pla_coordy" = '39.7700248693645', "pla_catast" = '07058A01900098' where "pla_codi" = 1292;
update "public"."oli_plantacio" set "pla_coordx" = '3.20580436873482', "pla_coordy" = '39.4993847205154', "pla_catast" = '07033A02300495' where "pla_codi" = 1296;
update "public"."oli_plantacio" set "pla_coordx" = '3.10495477437299', "pla_coordy" = '39.3830465702625', "pla_catast" = '07057A00100419' where "pla_codi" = 808;


-----------------
--02/02/2011
-----------------
CREATE
    TABLE oli_informacio_util
    (
        inu_id BIGINT DEFAULT nextval('oli_informacio_util_inu_id_seq'::regclass) NOT NULL,
        inu_dataalta TIMESTAMP WITHOUT TIME ZONE NOT NULL,
        inu_actiu BOOLEAN,
        inu_descripcio CHARACTER VARYING(5000),
        inu_nom CHARACTER VARYING(255),
        inu_imatgepral BIGINT,
        inu_imatgesecundaria BIGINT,
        inu_datainici TIMESTAMP WITHOUT TIME ZONE,
        inu_datafinal TIMESTAMP WITHOUT TIME ZONE,
        PRIMARY KEY (inu_id)
    );
COMMENT ON TABLE oli_informacio_util
IS
    'Informacio Util';
COMMENT ON COLUMN oli_informacio_util.inu_dataalta
IS
    'Data alta';
COMMENT ON COLUMN oli_informacio_util.inu_actiu
IS
    'Informacio Activa';
COMMENT ON COLUMN oli_informacio_util.inu_descripcio
IS
    'Descripcio';
COMMENT ON COLUMN oli_informacio_util.inu_nom
IS
    'Nom';
COMMENT ON COLUMN oli_informacio_util.inu_imatgepral
IS
    'Imatge Principal';
COMMENT ON COLUMN oli_informacio_util.inu_imatgesecundaria
IS
    'Imatge Secundaria';
COMMENT ON COLUMN oli_informacio_util.inu_datainici
IS
    'Data Inici';
COMMENT ON COLUMN oli_informacio_util.inu_datafinal
IS
    'Data Final';
    

-----------------
--03/02/2011
-----------------
CREATE
    TABLE oli_gestio_infografia
    (
        gin_id BIGINT DEFAULT nextval('oli_gestio_infografia_gin_id_seq'::regclass) NOT NULL,
        gin_dataalta TIMESTAMP WITHOUT TIME ZONE NOT NULL,
        gin_nom CHARACTER VARYING(255),
        gin_descripcio CHARACTER VARYING(5000),
        gin_arxiu BIGINT,
        gin_imatge BIGINT,
        PRIMARY KEY (gin_id)
    );
COMMENT ON TABLE oli_gestio_infografia
IS
    'Gestio Infografies';
COMMENT ON COLUMN oli_gestio_infografia.gin_dataalta
IS
    'Data alta';
COMMENT ON COLUMN oli_gestio_infografia.gin_nom
IS
    'Nom';
COMMENT ON COLUMN oli_gestio_infografia.gin_descripcio
IS
    'Descripcio';
COMMENT ON COLUMN oli_gestio_infografia.gin_arxiu
IS
    'Arxiu amb la Infografia';
    
    
-----------------
--08/02/2011
-----------------
CREATE
    TABLE oli_quadern_camp
    (
        quc_id BIGINT DEFAULT nextval('oli_quadern_camp_quc_id_seq'::regclass) NOT NULL,
        quc_data TIMESTAMP WITHOUT TIME ZONE,
        quc_plantacio BIGINT,
        quc_tractament CHARACTER VARYING(255),
        quc_materiaactiva CHARACTER VARYING(255),
        quc_marca CHARACTER VARYING(255),
        quc_dosi CHARACTER VARYING(255),
        quc_litresbrou CHARACTER VARYING(255),
        quc_terminiseguretat CHARACTER VARYING(255),
        quc_observacions CHARACTER VARYING(5000),
        quc_olivicultor BIGINT NOT NULL,
        PRIMARY KEY (quc_id),
        CONSTRAINT fk15616c195efadbeb FOREIGN KEY (quc_olivicultor) REFERENCES
        public.oli_olivicultor (oli_codi),
        CONSTRAINT fk15616c19a691d119 FOREIGN KEY (quc_plantacio) REFERENCES public.oli_plantacio (
        pla_codi)
    );
COMMENT ON TABLE oli_quadern_camp
IS
    'Quadern de Camp';
COMMENT ON COLUMN oli_quadern_camp.quc_data
IS
    'Data';
COMMENT ON COLUMN oli_quadern_camp.quc_tractament
IS
    'Tractament';
COMMENT ON COLUMN oli_quadern_camp.quc_materiaactiva
IS
    'Materia Activa';
COMMENT ON COLUMN oli_quadern_camp.quc_marca
IS
    'Marca/Lot';
COMMENT ON COLUMN oli_quadern_camp.quc_dosi
IS
    'Dosi';
COMMENT ON COLUMN oli_quadern_camp.quc_litresbrou
IS
    'Litres de Brou';
COMMENT ON COLUMN oli_quadern_camp.quc_terminiseguretat
IS
    'Termini de Seguretat';
COMMENT ON COLUMN oli_quadern_camp.quc_observacions
IS
    'Observacions';
    
    
alter table oli_document alter column doc_codinf drop not null;


-----------------
--09/02/2011
-----------------
CREATE
    TABLE oli_document_inspeccio
    (
        dci_codi BIGINT DEFAULT nextval('oli_document_inspeccio_dci_codi_seq'::regclass) NOT NULL,
        dci_tipus CHARACTER VARYING(255) NOT NULL,
        dci_data TIMESTAMP WITHOUT TIME ZONE,
        dci_olivicultor BIGINT,
        dci_establiment BIGINT,
        dci_arxiu BIGINT NOT NULL,
        PRIMARY KEY (dci_codi),
        CONSTRAINT fkb4e611382a351802 FOREIGN KEY (dci_establiment) REFERENCES
        public.oli_establiment (est_codi),
        CONSTRAINT fkb4e6113884507076 FOREIGN KEY (dci_olivicultor) REFERENCES
        public.oli_olivicultor (oli_codi)
    );
COMMENT ON TABLE oli_document_inspeccio
IS
    'Document Inspeccio';
COMMENT ON COLUMN oli_document_inspeccio.dci_tipus
IS
    'Tipus';
COMMENT ON COLUMN oli_document_inspeccio.dci_data
IS
    'Data';
COMMENT ON COLUMN oli_document_inspeccio.dci_olivicultor
IS
    'Codi de olivicultor';
COMMENT ON COLUMN oli_document_inspeccio.dci_establiment
IS
    'Codi de establiment';
COMMENT ON COLUMN oli_document_inspeccio.dci_arxiu
IS
    'Codi de arxiu';
    


-----------------
--14/02/2011
-----------------
CREATE
    TABLE oli_sortida_facturacio
    (
        sof_codi BIGINT DEFAULT nextval('oli_sortida_facturacio_sof_codi_seq'::regclass) NOT NULL,
        sof_idimportacio BIGINT NOT NULL,
        sof_datainsercio TIMESTAMP WITHOUT TIME ZONE NOT NULL,
        sof_tipussortida CHARACTER VARYING(1),
        sof_nomarxiu CHARACTER VARYING(255),
        sof_pais CHARACTER VARYING(255),
        sof_provincia CHARACTER VARYING(255),
        sof_municipi CHARACTER VARYING(255),
        sof_lot CHARACTER VARYING(255),
        sof_producte CHARACTER VARYING(255),
        sof_accio CHARACTER VARYING(1),
        sof_vendadata CHARACTER VARYING(255),
        sof_vendanumerobotelles CHARACTER VARYING(255),
        sof_vendamotiu CHARACTER VARYING(128),
        sof_vendaobservacions CHARACTER VARYING(255),
        sof_vendadestinatari CHARACTER VARYING(128),
        sof_vendanumdoc CHARACTER VARYING(64),
        sof_vendatipusdoc CHARACTER VARYING(64),
        sof_estat BOOLEAN,
        sof_error CHARACTER VARYING(255),
        sof_idsortida BIGINT,
        PRIMARY KEY (sof_codi)
    );
COMMENT ON TABLE oli_sortida_facturacio
IS
    'Sortides de oli entradas per lexcel de factuaracio';
COMMENT ON COLUMN oli_sortida_facturacio.sof_idimportacio
IS
    'id del document de importacio. Tots els registres del document tindran la mateixa id';
COMMENT ON COLUMN oli_sortida_facturacio.sof_datainsercio
IS
    'data de insercio';
COMMENT ON COLUMN oli_sortida_facturacio.sof_tipussortida
IS
    'Tipus de sortida: l (lot), d (diposit)';
COMMENT ON COLUMN oli_sortida_facturacio.sof_nomarxiu
IS
    'Nom de larxiu de facturacio a importar';
COMMENT ON COLUMN oli_sortida_facturacio.sof_pais
IS
    'Pais cap on es fa la sortida';
COMMENT ON COLUMN oli_sortida_facturacio.sof_provincia
IS
    'Provincia cap on es fa la sortida';
COMMENT ON COLUMN oli_sortida_facturacio.sof_municipi
IS
    'Municipi cap on es fa la sortida';
COMMENT ON COLUMN oli_sortida_facturacio.sof_lot
IS
    'Nom del lot de la sortida';
COMMENT ON COLUMN oli_sortida_facturacio.sof_producte
IS
    'Nom del producte de la sortida';
COMMENT ON COLUMN oli_sortida_facturacio.sof_accio
IS
    'Accio de la sortida: v (venda) c (canvi de zona)';
COMMENT ON COLUMN oli_sortida_facturacio.sof_vendadata
IS
    'Venda: data';
COMMENT ON COLUMN oli_sortida_facturacio.sof_vendanumerobotelles
IS
    'Venda: numero botelles';
COMMENT ON COLUMN oli_sortida_facturacio.sof_vendamotiu
IS
    'Venda: motiu de la sortida (promocio, venda, etc.)';
COMMENT ON COLUMN oli_sortida_facturacio.sof_vendaobservacions
IS
    'Venda: observacions';
COMMENT ON COLUMN oli_sortida_facturacio.sof_vendadestinatari
IS
    'Venda: destinatari';
COMMENT ON COLUMN oli_sortida_facturacio.sof_vendanumdoc
IS
    'Venda: numero de documento';
COMMENT ON COLUMN oli_sortida_facturacio.sof_vendatipusdoc
IS
    'Venda: tipus de document';
COMMENT ON COLUMN oli_sortida_facturacio.sof_estat
IS
    'Estat de la insercio';
COMMENT ON COLUMN oli_sortida_facturacio.sof_error
IS
    'Motiu de lerror';
COMMENT ON COLUMN oli_sortida_facturacio.sof_idsortida
IS
    'Id de la sortida generada';


-----------------
--16/02/2011
-----------------
CREATE
    TABLE oli_informe_campanya
    (
        inc_id BIGINT DEFAULT nextval('oli_informe_campanya_inc_id_seq'::regclass) NOT NULL,
        inc_dataalta TIMESTAMP WITHOUT TIME ZONE NOT NULL,
        inc_tipus BOOLEAN,
        inc_campanya BIGINT,
        inc_any CHARACTER VARYING(255),
        inc_document BIGINT,
        PRIMARY KEY (inc_id),
        CONSTRAINT fk7cb9ca6cdecbd59a FOREIGN KEY (inc_campanya) REFERENCES public.oli_campanya (
        cam_codi)
    );
COMMENT ON TABLE oli_informe_campanya
IS
    'Informe Campanya';
COMMENT ON COLUMN oli_informe_campanya.inc_dataalta
IS
    'Data alta';
COMMENT ON COLUMN oli_informe_campanya.inc_tipus
IS
    'Tipus Document (true->Campanya(producció), false->any(comercialitzacio))';
COMMENT ON COLUMN oli_informe_campanya.inc_campanya
IS
    'Codi de campanya';
COMMENT ON COLUMN oli_informe_campanya.inc_any
IS
    'Any comercialització';
COMMENT ON COLUMN oli_informe_campanya.inc_document
IS
    'Document';
    
------------------
-- 21-02-2011
------------------

-- Script Qualitat_APPCC_ETAPA
delete from qua_appcc_etapa_perill;
delete from qua_appcc_etapa;
insert into qua_appcc_etapa (aet_codest, aet_nom, aet_order) (select est_codi, 'Recepción y control aceituna', 1 from oli_establiment);
insert into qua_appcc_etapa (aet_codest, aet_nom, aet_order) (select est_codi, 'Recepción de materias primas  y envases', 2 from oli_establiment);
insert into qua_appcc_etapa (aet_codest, aet_nom, aet_order) (select est_codi, 'Limpieza y lavado aceituna', 3 from oli_establiment);
insert into qua_appcc_etapa (aet_codest, aet_nom, aet_order) (select est_codi, 'Pesado', 4 from oli_establiment);
insert into qua_appcc_etapa (aet_codest, aet_nom, aet_order) (select est_codi, 'Almacenamiento', 5 from oli_establiment);
insert into qua_appcc_etapa (aet_codest, aet_nom, aet_order) (select est_codi, 'Molienda', 6 from oli_establiment);
insert into qua_appcc_etapa (aet_codest, aet_nom, aet_order) (select est_codi, 'Batido', 7 from oli_establiment);
insert into qua_appcc_etapa (aet_codest, aet_nom, aet_order) (select est_codi, 'Separación sólido-líquido', 8 from oli_establiment);
insert into qua_appcc_etapa (aet_codest, aet_nom, aet_order) (select est_codi, 'Tamizado', 9 from oli_establiment);
insert into qua_appcc_etapa (aet_codest, aet_nom, aet_order) (select est_codi, 'Separación líquido-líquido', 10 from oli_establiment);
insert into qua_appcc_etapa (aet_codest, aet_nom, aet_order) (select est_codi, 'Decantación natural', 11 from oli_establiment);
insert into qua_appcc_etapa (aet_codest, aet_nom, aet_order) (select est_codi, 'Almacenamiento de aceite', 12 from oli_establiment);
insert into qua_appcc_etapa (aet_codest, aet_nom, aet_order) (select est_codi, 'Filtración', 13 from oli_establiment);
insert into qua_appcc_etapa (aet_codest, aet_nom, aet_order) (select est_codi, 'Envasado', 14 from oli_establiment);
insert into qua_appcc_etapa (aet_codest, aet_nom, aet_order) (select est_codi, 'Almacén de aceite envasado', 15 from oli_establiment);

-- Script Qualitat_APPCC_ETAPA_PERILL
alter table qua_appcc_etapa_perill alter column ape_detall Type CHARACTER VARYING(50);
insert into qua_appcc_etapa_perill (ape_tipus, ape_detall, ape_causa, ape_prevencio, ape_probabilitat, ape_gravetat, ape_etapa)
    (select 'b', 'Microbiológico', 'Contaminación en origen o transporte', 'pr', 1, 3, aet_id from qua_appcc_etapa where aet_order = 1);
insert into qua_appcc_etapa_perill (ape_tipus, ape_detall, ape_causa, ape_prevencio, ape_probabilitat, ape_gravetat, ape_etapa)
    (select 'b', 'Plagas', 'Contaminación en origen o transporte', 'pr', 2, 2, aet_id from qua_appcc_etapa where aet_order = 1);
insert into qua_appcc_etapa_perill (ape_tipus, ape_detall, ape_causa, ape_prevencio, ape_probabilitat, ape_gravetat, ape_etapa)
    (select 'f', 'Elementos impropios', 'Contaminación en origen o transporte', 'pr', 3, 3, aet_id from qua_appcc_etapa where aet_order = 1);
insert into qua_appcc_etapa_perill (ape_tipus, ape_detall, ape_causa, ape_prevencio, ape_probabilitat, ape_gravetat, ape_etapa)
    (select 'q', 'Contaminantes (fitosanitarios…)', 'Contaminación en origen', 'pr', 1, 3, aet_id from qua_appcc_etapa where aet_order = 1);

insert into qua_appcc_etapa_perill (ape_tipus, ape_detall, ape_causa, ape_prevencio, ape_probabilitat, ape_gravetat, ape_etapa)
    (select 'b', 'Microbiológico', 'Contaminación en origen o transporte', 'pr', 1, 3, aet_id from qua_appcc_etapa where aet_order = 2);
insert into qua_appcc_etapa_perill (ape_tipus, ape_detall, ape_causa, ape_prevencio, ape_probabilitat, ape_gravetat, ape_etapa)
    (select 'b', 'Plagas', 'Contaminación en origen o transporte', 'pr', 2, 2, aet_id from qua_appcc_etapa where aet_order = 2);
insert into qua_appcc_etapa_perill (ape_tipus, ape_detall, ape_causa, ape_prevencio, ape_probabilitat, ape_gravetat, ape_etapa)
    (select 'f', 'Elementos impropios', 'Contaminación en origen o transporte', 'pr', 2, 3, aet_id from qua_appcc_etapa where aet_order = 2);
insert into qua_appcc_etapa_perill (ape_tipus, ape_detall, ape_causa, ape_prevencio, ape_probabilitat, ape_gravetat, ape_etapa)
    (select 'q', 'Contaminantes', 'Contaminación en origen', 'pr', 1, 3, aet_id from qua_appcc_etapa where aet_order = 2);

insert into qua_appcc_etapa_perill (ape_tipus, ape_detall, ape_causa, ape_prevencio, ape_probabilitat, ape_gravetat, ape_etapa)
    (select 'b', 'Contaminación microbiológica', 'Uso de agua no potable, praxis incorrecta', 'ca', 1, 3, aet_id from qua_appcc_etapa where aet_order = 3);
insert into qua_appcc_etapa_perill (ape_tipus, ape_detall, ape_causa, ape_prevencio, ape_probabilitat, ape_gravetat, ape_etapa)
    (select 'f', 'Elementos impropios', 'Incorporación de elementos impropios durante proceso', 'fo', 2, 3, aet_id from qua_appcc_etapa where aet_order = 3);
insert into qua_appcc_etapa_perill (ape_tipus, ape_detall, ape_causa, ape_prevencio, ape_probabilitat, ape_gravetat, ape_etapa)
    (select 'q', 'Contaminantes', 'Residuos de productos de limpieza y/o mantenimiento', 'ma', 1, 2, aet_id from qua_appcc_etapa where aet_order = 3);

insert into qua_appcc_etapa_perill (ape_tipus, ape_detall, ape_causa, ape_prevencio, ape_probabilitat, ape_gravetat, ape_etapa)
    (select 'b', 'Contaminación microbiológica', 'Malas prácticas higiénicas. Incorrecta limpieza de los equipos', 'nd', 1, 3, aet_id from qua_appcc_etapa where aet_order = 4);
insert into qua_appcc_etapa_perill (ape_tipus, ape_detall, ape_causa, ape_prevencio, ape_probabilitat, ape_gravetat, ape_etapa)
    (select 'f', 'Elementos impropios', 'Incorporación de elementos impropios durante proceso', 'fo', 1, 3, aet_id from qua_appcc_etapa where aet_order = 4);
insert into qua_appcc_etapa_perill (ape_tipus, ape_detall, ape_causa, ape_prevencio, ape_probabilitat, ape_gravetat, ape_etapa)
    (select 'q', 'Contaminantes', 'Incorporación de productos químicos de limpieza o mantenimiento', 'ma', 1, 2, aet_id from qua_appcc_etapa where aet_order = 4);

insert into qua_appcc_etapa_perill (ape_tipus, ape_detall, ape_causa, ape_prevencio, ape_probabilitat, ape_gravetat, ape_etapa)
    (select 'b', 'Contaminación microbiológica', 'Mal estado higiénico del almacén', 'nd', 1, 3, aet_id from qua_appcc_etapa where aet_order = 5);
insert into qua_appcc_etapa_perill (ape_tipus, ape_detall, ape_causa, ape_prevencio, ape_probabilitat, ape_gravetat, ape_etapa)
    (select 'b', 'Plagas', 'Mal estado de mantenimiento del almacén', 'pl', 2, 2, aet_id from qua_appcc_etapa where aet_order = 5);
insert into qua_appcc_etapa_perill (ape_tipus, ape_detall, ape_causa, ape_prevencio, ape_probabilitat, ape_gravetat, ape_etapa)
    (select 'f', 'Elementos impropios', 'Incorporación de elementos impropios durante  el almacenado', 'ma', 1, 3, aet_id from qua_appcc_etapa where aet_order = 5);
insert into qua_appcc_etapa_perill (ape_tipus, ape_detall, ape_causa, ape_prevencio, ape_probabilitat, ape_gravetat, ape_etapa)
    (select 'q', 'Contaminantes', 'Residuos de productos de limpieza y/o mantenimiento', 'ma', 2, 2, aet_id from qua_appcc_etapa where aet_order = 5);

insert into qua_appcc_etapa_perill (ape_tipus, ape_detall, ape_causa, ape_prevencio, ape_probabilitat, ape_gravetat, ape_etapa)
    (select 'b', 'Contaminación microbiológica', 'Malas prácticas higiénicas. Incorrecta limpieza de los equipos', 'nd', 1, 3, aet_id from qua_appcc_etapa where aet_order = 6);
insert into qua_appcc_etapa_perill (ape_tipus, ape_detall, ape_causa, ape_prevencio, ape_probabilitat, ape_gravetat, ape_etapa)
    (select 'f', 'Elementos impropios', 'Incorporación de elementos impropios durante proceso', 'fo', 2, 3, aet_id from qua_appcc_etapa where aet_order = 6);
insert into qua_appcc_etapa_perill (ape_tipus, ape_detall, ape_causa, ape_prevencio, ape_probabilitat, ape_gravetat, ape_etapa)
    (select 'q', 'Contaminantes', 'Trazas metálicas. Residuos de productos de limpieza y/o mantenimiento', 'ma', 1, 3, aet_id from qua_appcc_etapa where aet_order = 6);

insert into qua_appcc_etapa_perill (ape_tipus, ape_detall, ape_causa, ape_prevencio, ape_probabilitat, ape_gravetat, ape_etapa)
    (select 'b', 'Contaminación microbiológica', 'Incorrecta limpieza de los equipos', 'nd', 1, 3, aet_id from qua_appcc_etapa where aet_order = 7);
insert into qua_appcc_etapa_perill (ape_tipus, ape_detall, ape_causa, ape_prevencio, ape_probabilitat, ape_gravetat, ape_etapa)
    (select 'f', 'Elementos impropios', 'Incorporación de elementos impropios durante proceso', 'fo', 1, 3, aet_id from qua_appcc_etapa where aet_order = 7);
insert into qua_appcc_etapa_perill (ape_tipus, ape_detall, ape_causa, ape_prevencio, ape_probabilitat, ape_gravetat, ape_etapa)
    (select 'q', 'Contaminantes', 'Residuos de productos de limpieza y/o mantenimiento', 'ma', 1, 2, aet_id from qua_appcc_etapa where aet_order = 7);

insert into qua_appcc_etapa_perill (ape_tipus, ape_detall, ape_causa, ape_prevencio, ape_probabilitat, ape_gravetat, ape_etapa)
    (select 'b', 'Contaminación microbiológica', 'Incorrecta limpieza de los equipos', 'nd', 2, 2, aet_id from qua_appcc_etapa where aet_order = 8);
insert into qua_appcc_etapa_perill (ape_tipus, ape_detall, ape_causa, ape_prevencio, ape_probabilitat, ape_gravetat, ape_etapa)
    (select 'f', 'Elementos impropios', 'Incorporación de elementos impropios durante proceso', 'fo', 1, 3, aet_id from qua_appcc_etapa where aet_order = 8);
insert into qua_appcc_etapa_perill (ape_tipus, ape_detall, ape_causa, ape_prevencio, ape_probabilitat, ape_gravetat, ape_etapa)
    (select 'q', 'Contaminantes', 'Residuos de productos de limpieza y/o mantenimiento', 'ma', 1, 2, aet_id from qua_appcc_etapa where aet_order = 8);

insert into qua_appcc_etapa_perill (ape_tipus, ape_detall, ape_causa, ape_prevencio, ape_probabilitat, ape_gravetat, ape_etapa)
    (select 'b', 'Contaminación microbiológica', 'Incorrecta limpieza de los equipos', 'nd', 1, 3, aet_id from qua_appcc_etapa where aet_order = 9);
insert into qua_appcc_etapa_perill (ape_tipus, ape_detall, ape_causa, ape_prevencio, ape_probabilitat, ape_gravetat, ape_etapa)
    (select 'f', 'Elementos impropios', 'Incorporación de elementos impropios durante proceso', 'fo', 1, 3, aet_id from qua_appcc_etapa where aet_order = 9);
insert into qua_appcc_etapa_perill (ape_tipus, ape_detall, ape_causa, ape_prevencio, ape_probabilitat, ape_gravetat, ape_etapa)
    (select 'q', 'Contaminantes', 'Residuos de productos de limpieza y/o mantenimiento', 'ma', 1, 2, aet_id from qua_appcc_etapa where aet_order = 9);

insert into qua_appcc_etapa_perill (ape_tipus, ape_detall, ape_causa, ape_prevencio, ape_probabilitat, ape_gravetat, ape_etapa)
    (select 'b', 'Contaminación microbiológica', 'Incorrecta limpieza de los equipos', 'nd', 1, 3, aet_id from qua_appcc_etapa where aet_order = 10);
insert into qua_appcc_etapa_perill (ape_tipus, ape_detall, ape_causa, ape_prevencio, ape_probabilitat, ape_gravetat, ape_etapa)
    (select 'f', 'Elementos impropios', 'Incorporación de elementos impropios durante proceso', 'fo', 1, 3, aet_id from qua_appcc_etapa where aet_order = 10);
insert into qua_appcc_etapa_perill (ape_tipus, ape_detall, ape_causa, ape_prevencio, ape_probabilitat, ape_gravetat, ape_etapa)
    (select 'q', 'Contaminantes', 'Residuos de productos de limpieza y/o mantenimiento', 'ma', 1, 2, aet_id from qua_appcc_etapa where aet_order = 10);

insert into qua_appcc_etapa_perill (ape_tipus, ape_detall, ape_causa, ape_prevencio, ape_probabilitat, ape_gravetat, ape_etapa)
    (select 'b', 'Contaminación microbiológica', 'Incorrecta limpieza de los equipos', 'nd', 1, 3, aet_id from qua_appcc_etapa where aet_order = 11);
insert into qua_appcc_etapa_perill (ape_tipus, ape_detall, ape_causa, ape_prevencio, ape_probabilitat, ape_gravetat, ape_etapa)
    (select 'f', 'Elementos impropios', 'Incorporación de elementos impropios durante proceso', 'fo', 1, 3, aet_id from qua_appcc_etapa where aet_order = 11);
insert into qua_appcc_etapa_perill (ape_tipus, ape_detall, ape_causa, ape_prevencio, ape_probabilitat, ape_gravetat, ape_etapa)
    (select 'q', 'Contaminantes', 'Residuos de productos de limpieza y/o mantenimiento', 'ma', 1, 2, aet_id from qua_appcc_etapa where aet_order = 11);

insert into qua_appcc_etapa_perill (ape_tipus, ape_detall, ape_causa, ape_prevencio, ape_probabilitat, ape_gravetat, ape_etapa)
    (select 'b', 'Contaminación microbiológica', 'Incorrecta limpieza de los equipos', 'nd', 1, 3, aet_id from qua_appcc_etapa where aet_order = 12);
insert into qua_appcc_etapa_perill (ape_tipus, ape_detall, ape_causa, ape_prevencio, ape_probabilitat, ape_gravetat, ape_etapa)
    (select 'b', 'Plagas', 'Mal estado de mantenimiento del almacén', 'pl', 2, 2, aet_id from qua_appcc_etapa where aet_order = 12);
insert into qua_appcc_etapa_perill (ape_tipus, ape_detall, ape_causa, ape_prevencio, ape_probabilitat, ape_gravetat, ape_etapa)
    (select 'f', 'Elementos impropios', 'Incorporación de elementos impropios durante proceso', 'fo', 1, 3, aet_id from qua_appcc_etapa where aet_order = 12);
insert into qua_appcc_etapa_perill (ape_tipus, ape_detall, ape_causa, ape_prevencio, ape_probabilitat, ape_gravetat, ape_etapa)
    (select 'q', 'Contaminantes', 'Residuos de productos de limpieza y/o mantenimiento', 'ma', 1, 2, aet_id from qua_appcc_etapa where aet_order = 12);

insert into qua_appcc_etapa_perill (ape_tipus, ape_detall, ape_causa, ape_prevencio, ape_probabilitat, ape_gravetat, ape_etapa)
    (select 'b', 'Contaminación microbiológica', 'Incorrecta limpieza de los equipos', 'nd', 1, 3, aet_id from qua_appcc_etapa where aet_order = 13);
insert into qua_appcc_etapa_perill (ape_tipus, ape_detall, ape_causa, ape_prevencio, ape_probabilitat, ape_gravetat, ape_etapa)
    (select 'f', 'Elementos impropios', 'Incorporación de elementos impropios durante proceso', 'fo', 1, 3, aet_id from qua_appcc_etapa where aet_order = 13);
insert into qua_appcc_etapa_perill (ape_tipus, ape_detall, ape_causa, ape_prevencio, ape_probabilitat, ape_gravetat, ape_etapa)
    (select 'q', 'Contaminantes', 'Residuos de productos de limpieza y/o mantenimiento', 'ma', 1, 2, aet_id from qua_appcc_etapa where aet_order = 13);

insert into qua_appcc_etapa_perill (ape_tipus, ape_detall, ape_causa, ape_prevencio, ape_probabilitat, ape_gravetat, ape_etapa)
    (select 'b', 'Contaminación microbiológica', 'Incorrecta limpieza de los equipos. Contaminación de los envases', 'nd', 1, 3, aet_id from qua_appcc_etapa where aet_order = 14);
insert into qua_appcc_etapa_perill (ape_tipus, ape_detall, ape_causa, ape_prevencio, ape_probabilitat, ape_gravetat, ape_etapa)
    (select 'f', 'Elementos impropios', 'Incorporación de elementos impropios durante proceso', 'fo', 1, 3, aet_id from qua_appcc_etapa where aet_order = 14);
insert into qua_appcc_etapa_perill (ape_tipus, ape_detall, ape_causa, ape_prevencio, ape_probabilitat, ape_gravetat, ape_etapa)
    (select 'q', 'Contaminantes', 'Residuos de productos de limpieza y/o mantenimiento', 'ma', 1, 2, aet_id from qua_appcc_etapa where aet_order = 14);

insert into qua_appcc_etapa_perill (ape_tipus, ape_detall, ape_causa, ape_prevencio, ape_probabilitat, ape_gravetat, ape_etapa)
    (select 'b', 'Contaminación microbiológica', 'Incorrecta limpieza de los equipos', 'nd', 1, 3, aet_id from qua_appcc_etapa where aet_order = 15);
insert into qua_appcc_etapa_perill (ape_tipus, ape_detall, ape_causa, ape_prevencio, ape_probabilitat, ape_gravetat, ape_etapa)
    (select 'f', 'Elementos impropios', 'Incorporación de elementos impropios durante proceso', 'fo', 1, 3, aet_id from qua_appcc_etapa where aet_order = 15);
insert into qua_appcc_etapa_perill (ape_tipus, ape_detall, ape_causa, ape_prevencio, ape_probabilitat, ape_gravetat, ape_etapa)
    (select 'q', 'Contaminantes', 'Productos derivados de la oxidación del aceite', 'ma', 1, 2, aet_id from qua_appcc_etapa where aet_order = 15);

ALTER TABLE "public"."oli_histolivic" ADD COLUMN "hol_altbai" BOOLEAN;
COMMENT ON COLUMN "public"."oli_histolivic"."hol_altbai" IS 'Es una alta o baixa';
UPDATE "public"."oli_histolivic" SET hol_altbai = true;

ALTER TABLE "public"."oli_histestabliment" ADD COLUMN "hes_altbai" BOOLEAN;
COMMENT ON COLUMN "public"."oli_histestabliment"."hes_altbai" IS 'Es una alta o baixa';
UPDATE "public"."oli_histestabliment" SET hes_altbai = true;

ALTER TABLE "public"."oli_diposit" ADD COLUMN "dip_deenvas" BOOLEAN;
COMMENT ON COLUMN "public"."oli_diposit"."dip_deenvas" IS 'Es un dipòsit de envasadora';
UPDATE public.oli_diposit set dip_deenvas = true where dip_codest in (select est_codi from oli_establiment where est_codtes = 2);
    