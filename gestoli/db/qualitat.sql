-- TAULA CONTROL --
CREATE
    TABLE qua_control
    (
        ctl_id BIGINT DEFAULT nextval('qua_control_ctl_id_seq'::regclass) NOT NULL,
        ctl_objectiu CHARACTER VARYING(255),
        ctl_responsableverificacio BIGINT,
        ctl_satisfactori BOOLEAN,
        ctl_departament BIGINT,
        ctl_dataverificacio TIMESTAMP WITHOUT TIME ZONE,
        PRIMARY KEY (ctl_id),
        CONSTRAINT fk51b8b5db5ca35983 FOREIGN KEY (ctl_responsableverificacio) REFERENCES
        public.qua_despers (dpe_codi),
        CONSTRAINT fk51b8b5dbe7577438 FOREIGN KEY (ctl_departament) REFERENCES
        public.qua_departament (dep_id)
    );
COMMENT ON TABLE qua_control
IS
    'Qualitat - Control';
COMMENT ON COLUMN qua_control.ctl_objectiu
IS
    'Objectiu';
COMMENT ON COLUMN qua_control.ctl_satisfactori
IS
    'Satisfactori';
COMMENT ON COLUMN qua_control.ctl_dataverificacio
IS
    'Data verificació'

-- TAULA DADES EQUIP --

CREATE
    TABLE qua_desequip
    (
        deq_codi BIGINT DEFAULT nextval('qua_desequip_deq_codi_seq'::regclass) NOT NULL,
        deq_nom CHARACTER VARYING(255),
        deq_planta INTEGER,
        deq_marca CHARACTER VARYING(255),
        deq_datacompra TIMESTAMP WITHOUT TIME ZONE,
        deq_numserie BIGINT,
        deq_codest BIGINT,
        PRIMARY KEY (deq_codi),
        CONSTRAINT fk43f55ea01adae1d2 FOREIGN KEY (deq_codest) REFERENCES public.oli_establiment (
        est_codi)
    );
COMMENT ON TABLE qua_desequip
IS
    'Qualitat - Descripcio Equip';
COMMENT ON COLUMN qua_desequip.deq_nom
IS
    'Nom';
COMMENT ON COLUMN qua_desequip.deq_planta
IS
    'Planta on es troba';
COMMENT ON COLUMN qua_desequip.deq_marca
IS
    'Marca';
COMMENT ON COLUMN qua_desequip.deq_datacompra
IS
    'Data de compra';
COMMENT ON COLUMN qua_desequip.deq_numserie
IS
    'Numero de serie o matricula';

--------------
--31/12/2010
--------------
CREATE
    TABLE qua_categoria_informacio
    (
        cai_codi INTEGER DEFAULT nextval('qua_categoria_informacio_cai_codi_seq'::regclass) NOT NULL,
        cai_nom CHARACTER VARYING(128) NOT NULL,
        cai_descripcio CHARACTER VARYING(255),
        PRIMARY KEY (cai_codi),
        UNIQUE (cai_nom)
    );
COMMENT ON TABLE qua_categoria_informacio
IS
    'Categories de informacions';
COMMENT ON COLUMN qua_categoria_informacio.cai_nom
IS
    'Nom';
COMMENT ON COLUMN qua_categoria_informacio.cai_descripcio
IS
    'Descripcio'

CREATE
    TABLE qua_informacio
    (
        inf_codi INTEGER DEFAULT nextval('qua_informacio_inf_codi_seq'::regclass) NOT NULL,
        inf_codcai INTEGER NOT NULL,
        inf_data DATE NOT NULL,
        inf_titol CHARACTER VARYING(128) NOT NULL,
        inf_texte CHARACTER VARYING(255),
        PRIMARY KEY (inf_codi),
        CONSTRAINT fke3beb27332fe5d00 FOREIGN KEY (inf_codcai) REFERENCES qua_categoria_informacio (cai_codi)
    );
COMMENT ON TABLE qua_informacio
IS
    'Informacions';
COMMENT ON COLUMN qua_informacio.inf_codcai
IS
    'Codi de categoria de informacio';
COMMENT ON COLUMN qua_informacio.inf_data
IS
    'Data';
COMMENT ON COLUMN qua_informacio.inf_titol
IS
    'Titol';
COMMENT ON COLUMN qua_informacio.inf_texte
IS
    'Texte'

CREATE
    TABLE qua_document
    (
        doc_codi INTEGER DEFAULT nextval('qua_document_doc_codi_seq'::regclass) NOT NULL,
        doc_codinf INTEGER NOT NULL,
        doc_titol CHARACTER VARYING(128) NOT NULL,
        doc_arxiu BIGINT NOT NULL,
        PRIMARY KEY (doc_codi),
        CONSTRAINT fk3a85905d81067156 FOREIGN KEY (doc_codinf) REFERENCES qua_informacio (inf_codi)
    );
COMMENT ON TABLE qua_document
IS
    'Documents';
COMMENT ON COLUMN qua_document.doc_codinf
IS
    'Codi de informacio';
COMMENT ON COLUMN qua_document.doc_titol
IS
    'Titol';
COMMENT ON COLUMN qua_document.doc_arxiu
IS
    'Codi de arxiu'

CREATE
    TABLE qua_estinf
    (
        ein_codinf INTEGER NOT NULL,
        ein_codest BIGINT NOT NULL,
        PRIMARY KEY (ein_codinf, ein_codest),
        CONSTRAINT fk4858005d28928858 FOREIGN KEY (ein_codest) REFERENCES oli_establiment (est_codi),
        CONSTRAINT fk4858005d72623604 FOREIGN KEY (ein_codinf) REFERENCES qua_informacio (inf_codi)
    );
COMMENT ON COLUMN qua_estinf.ein_codinf
IS
    'Codi de informacio';
COMMENT ON COLUMN qua_estinf.ein_codest
IS
    'Codi de establiment'

CREATE
    TABLE qua_usuinf
    (
        uin_codinf INTEGER NOT NULL,
        uin_codoli BIGINT NOT NULL,
        PRIMARY KEY (uin_codinf, uin_codoli),
        CONSTRAINT fk63a5feacb6f947f4 FOREIGN KEY (uin_codinf) REFERENCES qua_informacio (inf_codi),
        CONSTRAINT fk63a5feac5c9a047 FOREIGN KEY (uin_codoli) REFERENCES oli_usuari (usu_codi)
    );
COMMENT ON COLUMN qua_usuinf.uin_codinf
IS
    'Codi de informacio';
COMMENT ON COLUMN qua_usuinf.uin_codoli
IS
    'Codi de olivicultor'
    'Numero de serie o matricula'



-- TAULA PUESTO TREBALL --
CREATE
    TABLE qua_puetre
    (
        pue_id BIGINT DEFAULT nextval('qua_puetre_pue_id_seq'::regclass) NOT NULL,
        pue_carrec CHARACTER VARYING(255) NOT NULL,
        pue_funcions CHARACTER VARYING(5000),
        pue_formacio CHARACTER VARYING(5000),
        pue_experiencia CHARACTER VARYING(5000),
        pue_codest BIGINT NOT NULL,
        pue_nivell INTEGER,
        pue_carrecsup BIGINT,
        PRIMARY KEY (pue_id),
        CONSTRAINT fk5b32d669f40504e2 FOREIGN KEY (pue_codest) REFERENCES public.oli_establiment (
        est_codi),
        CONSTRAINT fk5b32d669b9192271 FOREIGN KEY (pue_carrecsup) REFERENCES public.qua_puetre (
        pue_id)
    );
COMMENT ON TABLE qua_puetre
IS
    'Qualitat - Puesto Treball';
COMMENT ON COLUMN qua_puetre.pue_carrec
IS
    'Carrec';
COMMENT ON COLUMN qua_puetre.pue_funcions
IS
    'Funcions basiques';
COMMENT ON COLUMN qua_puetre.pue_formacio
IS
    'Requisits de Formacio';
COMMENT ON COLUMN qua_puetre.pue_experiencia
IS
    'Requisits dexperiencia';
COMMENT ON COLUMN qua_puetre.pue_nivell
IS
    'Nivell jerarquic de major a menor'

-- TAULA DESCRIPCIO PERSONAL --
CREATE
    TABLE qua_despers
    (
        dpe_codi BIGINT DEFAULT nextval('qua_despers_dpe_codi_seq'::regclass) NOT NULL,
        dpe_nom CHARACTER VARYING(255),
        dpe_llinatges CHARACTER VARYING(255),
        dpe_datinc TIMESTAMP WITHOUT TIME ZONE,
        dpe_datcar TIMESTAMP WITHOUT TIME ZONE,
        dpe_codest BIGINT NOT NULL,
        dpe_codpue BIGINT,
        dpe_dni CHARACTER VARYING(255),
        dpe_datnac TIMESTAMP WITHOUT TIME ZONE,
        dpe_telefon CHARACTER VARYING(255),
        dpe_direccio CHARACTER VARYING(255),
        dpe_formacio CHARACTER VARYING(255),
        dpe_explaboral CHARACTER VARYING(255),
        PRIMARY KEY (dpe_codi),
        CONSTRAINT fk75d2ee869b3d8c69 FOREIGN KEY (dpe_codest) REFERENCES public.oli_establiment (
        est_codi),
        CONSTRAINT fk75d2ee863a6f88c FOREIGN KEY (dpe_codpue) REFERENCES public.qua_puetre (pue_id)
    );
COMMENT ON TABLE qua_despers
IS
    'Qualitat - Descripcio Personal';
COMMENT ON COLUMN qua_despers.dpe_nom
IS
    'Nom';
COMMENT ON COLUMN qua_despers.dpe_llinatges
IS
    'Llinatges';
COMMENT ON COLUMN qua_despers.dpe_datinc
IS
    'Data de incorporacio';
COMMENT ON COLUMN qua_despers.dpe_datcar
IS
    'Data de ocupacio del carrec';
COMMENT ON COLUMN qua_despers.dpe_dni
IS
    'Dni';
COMMENT ON COLUMN qua_despers.dpe_datnac
IS
    'Data de naixement';
COMMENT ON COLUMN qua_despers.dpe_telefon
IS
    'Telefon';
COMMENT ON COLUMN qua_despers.dpe_direccio
IS
    'Direccio';
COMMENT ON COLUMN qua_despers.dpe_formacio
IS
    'Formacio';
COMMENT ON COLUMN qua_despers.dpe_explaboral
IS
    'Experiencia Laboral';

-- TAULA PLA FORMACIO --
CREATE
    TABLE qua_pla_formacio
    (
        pfo_id BIGINT DEFAULT nextval('qua_pla_formacio_pfo_id_seq'::regclass) NOT NULL,
        pfo_descripcio CHARACTER VARYING(5000) NOT NULL,
        pfo_continguts CHARACTER VARYING(255),
        pfo_periodic BOOLEAN,
        pfo_frecuencia CHARACTER VARYING(255),
        pfo_dataprevista TIMESTAMP WITHOUT TIME ZONE,
        pfo_isresponsableintern BOOLEAN,
        pfo_coddpe BIGINT,
        pfo_responsableextern CHARACTER VARYING(15),
        pfo_duracio INTEGER,
        pfo_duraciotipus CHARACTER VARYING(255),
        pfo_codsupfor BIGINT,
        pfo_activitatsupervisio CHARACTER VARYING(255),
        pfo_datasupervisio TIMESTAMP WITHOUT TIME ZONE,
        pfo_codest BIGINT NOT NULL,
        PRIMARY KEY (pfo_id),
        CONSTRAINT fk9a01c2e8a002dc68 FOREIGN KEY (pfo_codsupfor) REFERENCES public.qua_despers (
        dpe_codi),
        CONSTRAINT fk9a01c2e852ce4489 FOREIGN KEY (pfo_codest) REFERENCES public.oli_establiment (
        est_codi),
        CONSTRAINT fk9a01c2e87845b362 FOREIGN KEY (pfo_coddpe) REFERENCES public.qua_despers (
        dpe_codi)
    );
COMMENT ON TABLE qua_pla_formacio
IS
    'Qualitat - Pla Formacio';
COMMENT ON COLUMN qua_pla_formacio.pfo_descripcio
IS
    'Descripcio';
COMMENT ON COLUMN qua_pla_formacio.pfo_continguts
IS
    'Continguts';
COMMENT ON COLUMN qua_pla_formacio.pfo_periodic
IS
    'Periodic';
COMMENT ON COLUMN qua_pla_formacio.pfo_frecuencia
IS
    'Frecuencia (tri (Trimestral), sem (Semestral), anu (Anual))';
COMMENT ON COLUMN qua_pla_formacio.pfo_dataprevista
IS
    'Data prevista';
COMMENT ON COLUMN qua_pla_formacio.pfo_isresponsableintern
IS
    'True -> responsableIntern // False -> responsableExtern';
COMMENT ON COLUMN qua_pla_formacio.pfo_responsableextern
IS
    'Responsable extern';
COMMENT ON COLUMN qua_pla_formacio.pfo_duracio
IS
    'Duracio de la formacio';
COMMENT ON COLUMN qua_pla_formacio.pfo_duraciotipus
IS
    'Tipus de duracio (h (horas), d (dias), s (semanas))';
COMMENT ON COLUMN qua_pla_formacio.pfo_activitatsupervisio
IS
    'Activitat Supervisio';
COMMENT ON COLUMN qua_pla_formacio.pfo_datasupervisio
IS
    'Data supervisio';

-- TAULA FORMACIO-EVALUACIO --
CREATE
    TABLE qua_formacio_evaluacio
    (
        fev_id BIGINT NOT NULL,
        fev_codpfo BIGINT,
        fev_coddpe BIGINT,
        PRIMARY KEY (fev_id),
        CONSTRAINT fk95915b70c54d2be6 FOREIGN KEY (fev_id) REFERENCES public.qua_control (ctl_id),
        CONSTRAINT fk95915b70304b1344 FOREIGN KEY (fev_coddpe) REFERENCES public.qua_despers (
        dpe_codi),
        CONSTRAINT fk95915b709f6e73c0 FOREIGN KEY (fev_codpfo) REFERENCES public.qua_pla_formacio (
        pfo_id)
    );
COMMENT ON TABLE qua_formacio_evaluacio
IS
    'Qualitat - Pla Formacio - Evaluacio'


-- DOCUMENTS PERSONAL--
alter table oli_document alter column doc_codinf drop not null;

alter table oli_document add column doc_coddpe BIGINT;

alter table oli_document add CONSTRAINT oli_docdpe_fk FOREIGN KEY (doc_coddpe) REFERENCES public.qua_despers (
        dpe_codi) ON DELETE CASCADE ON UPDATE CASCADE;

COMMENT ON COLUMN oli_document.doc_coddpe IS 'Codi de personal';

-- TABLA PLA MANTENIMENT --
CREATE
    TABLE qua_pla_manteniment
    (
        pma_id BIGINT DEFAULT nextval('qua_pla_manteniment_pma_id_seq'::regclass) NOT NULL,
        pma_equip BIGINT NOT NULL,
        pma_accions CHARACTER VARYING(5000) NOT NULL,
        pma_frecuencia CHARACTER VARYING(255),
        pma_isresponsableintern BOOLEAN,
        pma_responsableintern BIGINT,
        pma_responsableextern CHARACTER VARYING(15),
        pma_veriffrec CHARACTER VARYING(255),
        pma_verifresp BIGINT,
        pma_actiu BOOLEAN,
        PRIMARY KEY (pma_id),
        CONSTRAINT fk15aa81c876596410 FOREIGN KEY (pma_verifresp) REFERENCES public.qua_despers (
        dpe_codi),
        CONSTRAINT fk15aa81c89e0f9fd0 FOREIGN KEY (pma_responsableintern) REFERENCES
        public.qua_despers (dpe_codi),
        CONSTRAINT fk15aa81c88aec69ca FOREIGN KEY (pma_equip) REFERENCES public.qua_desequip (
        deq_codi)
    );
COMMENT ON TABLE qua_pla_manteniment
IS
    'Qualitat - Pla Manteniment';
COMMENT ON COLUMN qua_pla_manteniment.pma_accions
IS
    'Accions a realitzar';
COMMENT ON COLUMN qua_pla_manteniment.pma_frecuencia
IS
    'Frecuencia manteniment (dia->diario, sem->semanal, qui->quincenal, men->mensual, tri->trimestral, seme->semestral, anu->anual, bia->bianual, tria->trianual )'
    ;
COMMENT ON COLUMN qua_pla_manteniment.pma_isresponsableintern
IS
    'True -> responsableIntern // False -> responsableExtern';
COMMENT ON COLUMN qua_pla_manteniment.pma_responsableextern
IS
    'Responsable extern';
COMMENT ON COLUMN qua_pla_manteniment.pma_veriffrec
IS
    'Frecuencia (dia (diario), sem (semanal), men (mensual), tri (Trimestral) )';
COMMENT ON COLUMN qua_pla_manteniment.pma_actiu
IS
    'Equip Actiu';

-- TABLA PLA MANTENIMENT CONTROL --
CREATE
    TABLE qua_pla_manteniment_control
    (
        pmc_id BIGINT NOT NULL,
        pmc_manteniment BIGINT NOT NULL,
        pmc_datarealitzacio TIMESTAMP WITHOUT TIME ZONE,
        pmc_dataanterior TIMESTAMP WITHOUT TIME ZONE,
        pmc_accions CHARACTER VARYING(5000) NOT NULL,
        pmc_altresaccions CHARACTER VARYING(100),
        pmc_isresponsableintern BOOLEAN,
        pmc_responsableintern BIGINT,
        pmc_responsableextern CHARACTER VARYING(15),
        PRIMARY KEY (pmc_id),
        CONSTRAINT fke25745e61f6312e4 FOREIGN KEY (pmc_manteniment) REFERENCES
        public.qua_pla_manteniment (pma_id),
        CONSTRAINT fke25745e639646352 FOREIGN KEY (pmc_responsableintern) REFERENCES
        public.qua_despers (dpe_codi),
        CONSTRAINT fke25745e6d6c5bb17 FOREIGN KEY (pmc_id) REFERENCES public.qua_control (ctl_id)
    );
COMMENT ON TABLE qua_pla_manteniment_control
IS
    'Qualitat - Pla Manteniment Control';
COMMENT ON COLUMN qua_pla_manteniment_control.pmc_datarealitzacio
IS
    'Data realitzacio';
COMMENT ON COLUMN qua_pla_manteniment_control.pmc_dataanterior
IS
    'Data anterior';
COMMENT ON COLUMN qua_pla_manteniment_control.pmc_accions
IS
    'Accions realitzades';
COMMENT ON COLUMN qua_pla_manteniment_control.pmc_altresaccions
IS
    'Altres Accions realitzades';
COMMENT ON COLUMN qua_pla_manteniment_control.pmc_isresponsableintern
IS
    'True -> responsableIntern // False -> responsableExtern';
COMMENT ON COLUMN qua_pla_manteniment_control.pmc_responsableextern
IS
    'Responsable extern'

-- TABLA CONTROL DE PLAGUES --
CREATE
    TABLE qua_control_plagues
    (
        cpl_id BIGINT DEFAULT nextval('qua_control_plagues_cpl_id_seq'::regclass) NOT NULL,
        cpl_ubicacio CHARACTER VARYING(50) NOT NULL,
        cpl_elementcontrol CHARACTER VARYING(50) NOT NULL,
        cpl_frecuencia CHARACTER VARYING(255),
        cpl_isresponsableintern BOOLEAN,
        cpl_responsableintern BIGINT,
        cpl_empresaexterna CHARACTER VARYING(50),
        cpl_inicicontracte TIMESTAMP WITHOUT TIME ZONE,
        cpl_ficontracte TIMESTAMP WITHOUT TIME ZONE,
        cpl_plagaobjectiu CHARACTER VARYING(255),
        cpl_frecseguiment CHARACTER VARYING(255),
        cpl_codest BIGINT NOT NULL,
        PRIMARY KEY (cpl_id),
        CONSTRAINT fkc1db1f1d8782deab FOREIGN KEY (cpl_responsableintern) REFERENCES
        public.qua_despers (dpe_codi),
        CONSTRAINT fkc1db1f1d7e1c5563 FOREIGN KEY (cpl_codest) REFERENCES public.oli_establiment (
        est_codi)
    );
COMMENT ON TABLE qua_control_plagues
IS
    'Qualitat - Control Plagues';
COMMENT ON COLUMN qua_control_plagues.cpl_ubicacio
IS
    'Ubicació';
COMMENT ON COLUMN qua_control_plagues.cpl_elementcontrol
IS
    'Element de control';
COMMENT ON COLUMN qua_control_plagues.cpl_frecuencia
IS
    'Frecuencia (dia->diario, sem->semanal, qui->quincenal, men->mensual, tri->trimestral, seme->semestral, anu->anual, bia->bianual, tria->trianual )'
    ;
COMMENT ON COLUMN qua_control_plagues.cpl_isresponsableintern
IS
    'True -> responsableIntern // False -> responsableExtern';
COMMENT ON COLUMN qua_control_plagues.cpl_empresaexterna
IS
    'Empresa externa';
COMMENT ON COLUMN qua_control_plagues.cpl_inicicontracte
IS
    'Data inici Contracte';
COMMENT ON COLUMN qua_control_plagues.cpl_ficontracte
IS
    'Data fi Contracte';
COMMENT ON COLUMN qua_control_plagues.cpl_plagaobjectiu
IS
    'Plaga Objectiu (roe->roedors, nov->insectes no voladors, siv->insectes voladors)';
COMMENT ON COLUMN qua_control_plagues.cpl_frecseguiment
IS
    'Frecuencia Seguiment (men->mensual, bim->bimensual, tri->trimestral, seme->semestral, anu->anual)';

-- TABLA CONTROL PLAGUES - VERIFICACIO --
CREATE
    TABLE qua_control_plagues_verif
    (
        cpv_id BIGINT NOT NULL,
        cpv_controlplaga BIGINT,
        PRIMARY KEY (cpv_id),
        CONSTRAINT fk39a7705ec0c9a494 FOREIGN KEY (cpv_id) REFERENCES public.qua_control (ctl_id),
        CONSTRAINT fk39a7705e9323cff6 FOREIGN KEY (cpv_controlplaga) REFERENCES
        public.qua_control_plagues (cpl_id)
    );
COMMENT ON TABLE qua_control_plagues_verif
IS
    'Qualitat - Control Plagues - Verificacio';
    
-- TABLA DEPARTAMENT --
CREATE
    TABLE qua_departament
    (
        dep_id BIGINT DEFAULT nextval('qua_departament_dep_id_seq'::regclass) NOT NULL,
        dep_nom CHARACTER VARYING(50) NOT NULL,
        PRIMARY KEY (dep_id)
    );
COMMENT ON TABLE qua_departament
IS
    'Qualitat - Departament';
COMMENT ON COLUMN qua_departament.dep_nom
IS
    'Causa';

-- SCRIPT QUALITAT_DEPARTAMENT --
insert into qua_departament (dep_id, dep_nom) values (1, 'proveedor');
insert into qua_departament (dep_id, dep_nom) values (2, 'proces');
insert into qua_departament (dep_id, dep_nom) values (3, 'APPCC');
insert into qua_departament (dep_id, dep_nom) values (4, 'pla_manteniment');
insert into qua_departament (dep_id, dep_nom) values (5, 'pla_neteja_desinfeccio');
insert into qua_departament (dep_id, dep_nom) values (6, 'pla_abastament_aigua');
insert into qua_departament (dep_id, dep_nom) values (7, 'pla_formacio');
insert into qua_departament (dep_id, dep_nom) values (8, 'pla_control_proveedors');
insert into qua_departament (dep_id, dep_nom) values (9, 'pla_trazabilitat');
insert into qua_departament (dep_id, dep_nom) values (10, 'pla_control_plagues');
insert into qua_departament (dep_id, dep_nom) values (11, 'procediment_verificacio_sistema');
insert into qua_departament (dep_id, dep_nom) values (12, 'client');

-- TABLA NO_CONFORMITAT --
CREATE
    TABLE qua_noconformitat
    (
        noc_id BIGINT DEFAULT nextval('qua_noconformitat_noc_id_seq'::regclass) NOT NULL,
        noc_datanoconformitat TIMESTAMP WITHOUT TIME ZONE,
        noc_responsabledeteccio BIGINT NOT NULL,
        noc_descripcio CHARACTER VARYING(255) NOT NULL,
        noc_causa CHARACTER VARYING(255),
        noc_departament BIGINT,
        noc_datatancament TIMESTAMP WITHOUT TIME ZONE,
        noc_codctl BIGINT,
        noc_codest BIGINT NOT NULL,
        PRIMARY KEY (noc_id),
        CONSTRAINT fk17ca0661c2152ee0 FOREIGN KEY (noc_codest) REFERENCES public.oli_establiment (
        est_codi),
        CONSTRAINT fk17ca0661cf7282e3 FOREIGN KEY (noc_codctl) REFERENCES public.qua_control (
        ctl_id),
        CONSTRAINT fk17ca0661213f78f4 FOREIGN KEY (noc_responsabledeteccio) REFERENCES
        public.qua_despers (dpe_codi),
        CONSTRAINT fk17ca0661f7d4da5f FOREIGN KEY (noc_departament) REFERENCES
        public.qua_departament (dep_id)
    );
COMMENT ON TABLE qua_noconformitat
IS
    'Qualitat - No Conformitat';
COMMENT ON COLUMN qua_noconformitat.noc_datanoconformitat
IS
    'Data no conformitat';
COMMENT ON COLUMN qua_noconformitat.noc_descripcio
IS
    'Descripció no conformitat';
COMMENT ON COLUMN qua_noconformitat.noc_causa
IS
    'Causa';
COMMENT ON COLUMN qua_noconformitat.noc_datatancament
IS
    'Data Tancament no conformitat';

-- TABLA NO_CONFORMITAT - ACCIO --
CREATE
    TABLE qua_noconformitat_accio
    (
        nca_id BIGINT DEFAULT nextval('qua_noconformitat_accio_nca_id_seq'::regclass) NOT NULL,
        nca_accio CHARACTER VARYING(255) NOT NULL,
        noc_responsableaccio BIGINT,
        noc_datafiprevista TIMESTAMP WITHOUT TIME ZONE,
        noc_datatancament TIMESTAMP WITHOUT TIME ZONE,
        nca_noconformitat BIGINT NOT NULL,
        PRIMARY KEY (nca_id),
        CONSTRAINT fkb08bc60924276679 FOREIGN KEY (nca_noconformitat) REFERENCES
        public.qua_noconformitat (noc_id),
        CONSTRAINT fkb08bc6098a5580c1 FOREIGN KEY (noc_responsableaccio) REFERENCES
        public.qua_despers (dpe_codi)
    );
COMMENT ON TABLE qua_noconformitat_accio
IS
    'Qualitat - No Conformitat - Accio';
COMMENT ON COLUMN qua_noconformitat_accio.nca_accio
IS
    'Accio';
COMMENT ON COLUMN qua_noconformitat_accio.noc_datafiprevista
IS
    'Data Finalitzacio Prevista';
COMMENT ON COLUMN qua_noconformitat_accio.noc_datatancament
IS
    'Data Tancament No Conformitat';
    
-- TABLA APPCC_ETAPA --
CREATE
    TABLE qua_appcc_etapa
    (
        aet_id BIGINT DEFAULT nextval('qua_appcc_etapa_aet_id_seq'::regclass) NOT NULL,
        aet_codest BIGINT NOT NULL,
        aet_nom CHARACTER VARYING(50),
        aet_order INTEGER,
        cpl_nom CHARACTER VARYING(50),
        PRIMARY KEY (aet_id),
        CONSTRAINT fk9081cd0375a21512 FOREIGN KEY (aet_codest) REFERENCES public.oli_establiment (
        est_codi)
    );
COMMENT ON TABLE qua_appcc_etapa
IS
    'Qualitat - APPCC - Etapa';
COMMENT ON COLUMN qua_appcc_etapa.aet_nom
IS
    'Nom';
COMMENT ON COLUMN qua_appcc_etapa.aet_order
IS
    'Ordre';
COMMENT ON COLUMN qua_appcc_etapa.cpl_nom
IS
    'Nom';

-- TABLA APPCC_ETAPA_PERILL --
CREATE
    TABLE qua_appcc_etapa_perill
    (
        ape_id BIGINT DEFAULT nextval('qua_appcc_etapa_perill_ape_id_seq'::regclass) NOT NULL,
        ape_tipus CHARACTER VARYING(1),
        ape_detall CHARACTER VARYING(25),
        ape_causa CHARACTER VARYING(255),
        ape_prevencio CHARACTER VARYING(255),
        ape_probabilitat BIGINT,
        ape_gravetat BIGINT,
        ape_etapa BIGINT NOT NULL,
        PRIMARY KEY (ape_id),
        CONSTRAINT fkfb448c28c1954756 FOREIGN KEY (ape_etapa) REFERENCES public.qua_appcc_etapa (
        aet_id)
    );
COMMENT ON TABLE qua_appcc_etapa_perill
IS
    'Qualitat - APPCC - Etapa - Perill';
COMMENT ON COLUMN qua_appcc_etapa_perill.ape_tipus
IS
    'Tipus de perill (b ->biológics, f->físics, q->químics)';
COMMENT ON COLUMN qua_appcc_etapa_perill.ape_detall
IS
    'Detall de perill';
COMMENT ON COLUMN qua_appcc_etapa_perill.ape_causa
IS
    'Causa';
COMMENT ON COLUMN qua_appcc_etapa_perill.ape_prevencio
IS
    'Mesures preventives (ca ->control agua, pr->proveedores, ma->mantenimiento, fo->formación, nd->limpieza y desinfección, tr->trazabilidad, cr->control recepción)'
    ;
COMMENT ON COLUMN qua_appcc_etapa_perill.ape_probabilitat
IS
    'Probabilitat de perill (1 ->molt baixa, 2->baixa, 3->alta, 4->molt alta)';
COMMENT ON COLUMN qua_appcc_etapa_perill.ape_gravetat
IS
    'Gravetat de perill (1 ->trivial, 2->leve, 3->grave)';
    

-- TABLA APPCC_CONTROL --
    CREATE
    TABLE qua_appcc_control
    (
        aco_id BIGINT DEFAULT nextval('qua_appcc_control_aco_id_seq'::regclass) NOT NULL,
        aco_p1 BOOLEAN,
        aco_p2 BOOLEAN,
        aco_p3 BOOLEAN,
        aco_p4 BOOLEAN,
        aco_p5 BOOLEAN,
        aco_puntcontrol CHARACTER VARYING(255),
        aco_perillcontrolat CHARACTER VARYING(255),
        aco_perill BIGINT NOT NULL,
        aco_etapa BIGINT NOT NULL,
        PRIMARY KEY (aco_id),
        CONSTRAINT fk5a7375d162bada6 FOREIGN KEY (aco_perill) REFERENCES
        public.qua_appcc_etapa_perill (ape_id),
        CONSTRAINT fk5a7375d8c24508d FOREIGN KEY (aco_etapa) REFERENCES public.qua_appcc_etapa (
        aet_id)
    );
COMMENT ON TABLE qua_appcc_control
IS
    'Qualitat - APPCC - Control';
COMMENT ON COLUMN qua_appcc_control.aco_p1
IS
    'Pregunta 1';
COMMENT ON COLUMN qua_appcc_control.aco_p2
IS
    'Pregunta 2';
COMMENT ON COLUMN qua_appcc_control.aco_p3
IS
    'Pregunta 3';
COMMENT ON COLUMN qua_appcc_control.aco_p4
IS
    'Pregunta 4';
COMMENT ON COLUMN qua_appcc_control.aco_p5
IS
    'Pregunta 5';
COMMENT ON COLUMN qua_appcc_control.aco_puntcontrol
IS
    'Punt de Control';
COMMENT ON COLUMN qua_appcc_control.aco_perillcontrolat
IS
    'Perill Controlat';
    
-- TABLA APPCC_FITXACONTROL --
CREATE
    TABLE qua_appcc_fitxacontrol
    (
        afc_id BIGINT DEFAULT nextval('qua_appcc_fitxacontrol_afc_id_seq'::regclass) NOT NULL,
        afc_control BIGINT NOT NULL,
        PRIMARY KEY (afc_id),
        CONSTRAINT fk9655eb431ac2bed2 FOREIGN KEY (afc_control) REFERENCES public.qua_appcc_control
        (aco_id),
        UNIQUE (afc_control)
    );
COMMENT ON TABLE qua_appcc_fitxacontrol
IS
    'Qualitat - APPCC - FitxaControl';
    
-- TABLA APPCC_FITXACONTROL_HISTORIC --
CREATE
    TABLE qua_appcc_fitxacontrol_historic
    (
        fch_id BIGINT DEFAULT nextval('qua_appcc_fitxacontrol_historic_fch_id_seq'::regclass) NOT
        NULL,
        fch_mesuresprevencio CHARACTER VARYING(255),
        fch_respprevencio BIGINT,
        fch_vigilancia CHARACTER VARYING(255),
        fch_respvigilancia BIGINT,
        fch_registre CHARACTER VARYING(255),
        fch_limits CHARACTER VARYING(255),
        fch_freqvigilancia CHARACTER VARYING(255),
        fch_mesurescorreccio CHARACTER VARYING(255),
        fch_respcorreccio BIGINT,
        fch_fitxa BIGINT NOT NULL,
        fch_datamodificacio TIMESTAMP WITHOUT TIME ZONE NOT NULL,
        PRIMARY KEY (fch_id),
        CONSTRAINT fkc97ceddb954dc836 FOREIGN KEY (fch_respcorreccio) REFERENCES public.qua_despers
        (dpe_codi),
        CONSTRAINT fkc97ceddbab925224 FOREIGN KEY (fch_respprevencio) REFERENCES public.qua_despers
        (dpe_codi),
        CONSTRAINT fkc97ceddb49e17330 FOREIGN KEY (fch_fitxa) REFERENCES
        public.qua_appcc_fitxacontrol (afc_id),
        CONSTRAINT fkc97ceddb962bc796 FOREIGN KEY (fch_respvigilancia) REFERENCES
        public.qua_despers (dpe_codi)
    );
COMMENT ON TABLE qua_appcc_fitxacontrol_historic
IS
    'Qualitat - APPCC - FitxaControl - Historic';
COMMENT ON COLUMN qua_appcc_fitxacontrol_historic.fch_mesuresprevencio
IS
    'Mesures de Prevencio';
COMMENT ON COLUMN qua_appcc_fitxacontrol_historic.fch_vigilancia
IS
    'Vigilancia';
COMMENT ON COLUMN qua_appcc_fitxacontrol_historic.fch_registre
IS
    'Registre de la Qualitat';
COMMENT ON COLUMN qua_appcc_fitxacontrol_historic.fch_limits
IS
    'Limits Critics';
COMMENT ON COLUMN qua_appcc_fitxacontrol_historic.fch_freqvigilancia
IS
    'Frequencia Vigilancia';
COMMENT ON COLUMN qua_appcc_fitxacontrol_historic.fch_mesurescorreccio
IS
    'Mesures de Correccio';
COMMENT ON COLUMN qua_appcc_fitxacontrol_historic.fch_datamodificacio
IS
    'Data de Modificacio';



    
    
    
    
    
    
----------------------------------------------------------------------
----------------------------------------------------------------------
----------------------------- 25/01/2011 -----------------------------
----------------------------------------------------------------------
----------------------------------------------------------------------


---------------------------------    
----- TAULES PLA NETEJA ---------
---------------------------------
CREATE
    TABLE qua_planet
    (
        pne_codi BIGINT DEFAULT nextval('qua_planet_pne_codi_seq'::regclass) NOT NULL,
        pne_accio CHARACTER VARYING(255) NOT NULL,
        pne_prod BIGINT,
        pne_dosis CHARACTER VARYING(255),
        pne_elem CHARACTER VARYING(255),
        pne_equip BIGINT,
        pne_freq CHARACTER VARYING(255),
        pne_resp BIGINT,
        pne_respverr BIGINT,
        pne_freqver CHARACTER VARYING(255),
        pne_codest BIGINT NOT NULL,
        pne_esgeneral BOOLEAN,
        PRIMARY KEY (pne_codi),
        CONSTRAINT fk5ab2195ac66683ad FOREIGN KEY (pne_equip) REFERENCES public.qua_desequip (
        deq_codi),
        CONSTRAINT fk5ab2195ad3a8b4d2 FOREIGN KEY (pne_respverr) REFERENCES public.qua_despers (
        dpe_codi),
        CONSTRAINT fk5ab2195ae625fbdb FOREIGN KEY (pne_codest) REFERENCES public.oli_establiment (
        est_codi),
        CONSTRAINT fk5ab2195a8cd3ba3 FOREIGN KEY (pne_resp) REFERENCES public.qua_despers (dpe_codi
        ),
        CONSTRAINT fk5ab2195af05c5aa0 FOREIGN KEY (pne_prod) REFERENCES public.qua_subministre (
        sub_codi)
    );
COMMENT ON TABLE qua_planet
IS
    'Qualitat - Pla de neteja i desinfecció';
COMMENT ON COLUMN qua_planet.pne_accio
IS
    'Acció';
COMMENT ON COLUMN qua_planet.pne_dosis
IS
    'Dosis';
COMMENT ON COLUMN qua_planet.pne_elem
IS
    'Element de la planta';
COMMENT ON COLUMN qua_planet.pne_freq
IS
    'Freqüencia de realitzacio';
COMMENT ON COLUMN qua_planet.pne_freqver
IS
    'Frequencia de verificacio';
COMMENT ON COLUMN qua_planet.pne_esgeneral
IS
    'True -> general // False -> equip'


CREATE
    TABLE qua_planet_realitzacio
    (
        pnr_id BIGINT DEFAULT nextval('qua_planet_realitzacio_pnr_id_seq'::regclass) NOT NULL,
        pnr_neteja BIGINT NOT NULL,
        pnr_datarealitzacio TIMESTAMP WITHOUT TIME ZONE,
        pnr_dataanterior TIMESTAMP WITHOUT TIME ZONE,
        pnr_responsable BIGINT,
        PRIMARY KEY (pnr_id),
        CONSTRAINT fk8f54bb342eae8434 FOREIGN KEY (pnr_neteja) REFERENCES public.qua_planet (
        pne_codi),
        CONSTRAINT fk8f54bb346409f2e FOREIGN KEY (pnr_responsable) REFERENCES public.qua_despers (
        dpe_codi)
    );
COMMENT ON TABLE qua_planet_realitzacio
IS
    'Qualitat - Pla Neteja Realitzacio Control';
COMMENT ON COLUMN qua_planet_realitzacio.pnr_datarealitzacio
IS
    'Data realitzacio';
COMMENT ON COLUMN qua_planet_realitzacio.pnr_dataanterior
IS
    'Data anterior';



CREATE
    TABLE qua_planet_verificacio
    (
        pnv_id BIGINT NOT NULL,
        pnv_dataanterior TIMESTAMP WITHOUT TIME ZONE,
        pnv_neteja BIGINT NOT NULL,
        pnv_datarealitzacio TIMESTAMP WITHOUT TIME ZONE,
        pnv_satisfactoria BOOLEAN,
        pnv_responsable BIGINT,
        PRIMARY KEY (pnv_id),
        CONSTRAINT fkd113417dc04cb932 FOREIGN KEY (pnv_responsable) REFERENCES public.qua_despers (
        dpe_codi),
        CONSTRAINT fkd113417dd6dc75a5 FOREIGN KEY (pnv_id) REFERENCES public.qua_control (ctl_id),
        CONSTRAINT fkd113417dce3337b0 FOREIGN KEY (pnv_neteja) REFERENCES public.qua_planet (
        pne_codi)
    );
COMMENT ON TABLE qua_planet_verificacio
IS
    'Qualitat - Control Plagues - Verificacio';
COMMENT ON COLUMN qua_planet_verificacio.pnv_dataanterior
IS
    'Data anterior';
COMMENT ON COLUMN qua_planet_verificacio.pnv_datarealitzacio
IS
    'Data verificacio';
COMMENT ON COLUMN qua_planet_verificacio.pnv_satisfactoria
IS
    'Verificacio satisfactoria';


---------------------------------    
----- TAULES PLA TRANSPORT ------
---------------------------------
CREATE
    TABLE qua_platra
    (
        ptr_id BIGINT NOT NULL,
        ptr_estibacorrecta BOOLEAN,
        ptr_netejacorrecta BOOLEAN,
        ptr_vehicle BIGINT,
        ptr_codest BIGINT NOT NULL,
        pne_freq TIMESTAMP WITHOUT TIME ZONE,
        ptr_resp BIGINT,
        PRIMARY KEY (ptr_id),
        CONSTRAINT fk5ab23160a62fdd34 FOREIGN KEY (ptr_codest) REFERENCES public.oli_establiment (
        est_codi),
        CONSTRAINT fk5ab231603bc09a70 FOREIGN KEY (ptr_vehicle) REFERENCES public.qua_desequip (
        deq_codi),
        CONSTRAINT fk5ab23160d72f312f FOREIGN KEY (ptr_id) REFERENCES public.qua_control (ctl_id),
        CONSTRAINT fk5ab231605c619f3c FOREIGN KEY (ptr_resp) REFERENCES public.qua_despers (
        dpe_codi)
    );
COMMENT ON TABLE qua_platra
IS
    'Qualitat - Control Transport - Verificacio';
COMMENT ON COLUMN qua_platra.ptr_estibacorrecta
IS
    'La estiba és correcta';
COMMENT ON COLUMN qua_platra.ptr_netejacorrecta
IS
    'La neteja és correcta';
COMMENT ON COLUMN qua_platra.pne_freq
IS
    'Data de verificacio';

---------------------------------    
----- TAULES PLA PROVEIDORS -----
---------------------------------

CREATE
    TABLE qua_proveidor
    (
        pro_codi BIGINT DEFAULT nextval('qua_proveidor_pro_codi_seq'::regclass) NOT NULL,
        pro_nom CHARACTER VARYING(255) NOT NULL,
        pro_direccio CHARACTER VARYING(255),
        pro_telefon CHARACTER VARYING(255),
        pro_pers_cont CHARACTER VARYING(255),
        pro_numrgsa BIGINT,
        pro_cadrgsa TIMESTAMP WITHOUT TIME ZONE,
        pro_codest BIGINT NOT NULL,
        pro_obs CHARACTER VARYING(255),
        PRIMARY KEY (pro_codi),
        CONSTRAINT fkbd15c55846037895 FOREIGN KEY (pro_codest) REFERENCES public.oli_establiment (
        est_codi)
    );
COMMENT ON TABLE qua_proveidor
IS
    'Qualitat - Proveidors';
COMMENT ON COLUMN qua_proveidor.pro_nom
IS
    'Nom';
COMMENT ON COLUMN qua_proveidor.pro_direccio
IS
    'Direccio';
COMMENT ON COLUMN qua_proveidor.pro_telefon
IS
    'Telefon';
COMMENT ON COLUMN qua_proveidor.pro_pers_cont
IS
    'Persona de contacte';
COMMENT ON COLUMN qua_proveidor.pro_numrgsa
IS
    'Numero registre sanitari';
COMMENT ON COLUMN qua_proveidor.pro_cadrgsa
IS
    'Caducitat registre sanitari';
COMMENT ON COLUMN qua_proveidor.pro_obs
IS
    'Observacions';



CREATE
    TABLE qua_proveidor_aval
    (
        cpa_id BIGINT NOT NULL,
        cpa_rehomologacio TIMESTAMP WITHOUT TIME ZONE,
        cpa_deshomologacio TIMESTAMP WITHOUT TIME ZONE,
        cpa_proximaav TIMESTAMP WITHOUT TIME ZONE,
        cpa_valoracio INTEGER,
        cpa_obs CHARACTER VARYING(255),
        cpa_proveidor BIGINT,
        PRIMARY KEY (cpa_id),
        CONSTRAINT fkee5069a717830e0f FOREIGN KEY (cpa_proveidor) REFERENCES public.qua_proveidor (
        pro_codi),
        CONSTRAINT fkee5069a7c0c018c9 FOREIGN KEY (cpa_id) REFERENCES public.qua_control (ctl_id)
    );
COMMENT ON TABLE qua_proveidor_aval
IS
    'Qualitat - Control Proveidors - Avaluació';
COMMENT ON COLUMN qua_proveidor_aval.cpa_valoracio
IS
    'Valoracio';
COMMENT ON COLUMN qua_proveidor_aval.cpa_obs
IS
    'Observacions';



CREATE
    TABLE qua_subministre
    (
        sub_codi BIGINT DEFAULT nextval('qua_subministre_sub_codi_seq'::regclass) NOT NULL,
        sub_nom CHARACTER VARYING(255) NOT NULL,
        sub_homologacio TIMESTAMP WITHOUT TIME ZONE,
        sub_deshomologacio TIMESTAMP WITHOUT TIME ZONE,
        sub_observacions CHARACTER VARYING(255),
        sum_codpro BIGINT NOT NULL,
        sum_codest BIGINT,
        PRIMARY KEY (sub_codi),
        CONSTRAINT fk184988e997dfa149 FOREIGN KEY (sum_codpro) REFERENCES public.qua_proveidor (
        pro_codi),
        CONSTRAINT fk184988e9feabf37 FOREIGN KEY (sum_codest) REFERENCES public.oli_establiment (
        est_codi)
    );
COMMENT ON TABLE qua_subministre
IS
    'Qualitat - Proveidors';
COMMENT ON COLUMN qua_subministre.sub_nom
IS
    'Nom';
COMMENT ON COLUMN qua_subministre.sub_homologacio
IS
    'Direccio';
COMMENT ON COLUMN qua_subministre.sub_deshomologacio
IS
    'Telefon';
COMMENT ON COLUMN qua_subministre.sub_observacions
IS
    'Persona de contacte';




alter table qua_appcc_control add CONSTRAINT fk5a7375dbca59330 FOREIGN KEY (aco_id) REFERENCES public.qua_control (ctl_id);

insert into qua_departament (dep_id, dep_nom) values (13, 'pla_control_transport');

-- TABLA APPCC_PLAVERIFICACIO --
CREATE
    TABLE qua_appcc_plaverificacio
    (
        apv_id BIGINT DEFAULT nextval('qua_appcc_plaverificacio_apv_id_seq'::regclass) NOT NULL,
        apv_responsable BIGINT NOT NULL,
        apv_data TIMESTAMP WITHOUT TIME ZONE NOT NULL,
        apv_p1 BOOLEAN,
        apv_p1_1 CHARACTER VARYING(500),
        apv_p1_2 BOOLEAN,
        apv_p1_2_comments CHARACTER VARYING(500),
        apv_p1_3 BOOLEAN,
        apv_p1_3_comments CHARACTER VARYING(500),
        apv_p1_4 CHARACTER VARYING(500),
        apv_p1_5 CHARACTER VARYING(500),
        apv_p2 BOOLEAN,
        apv_p2_1 CHARACTER VARYING(500),
        apv_p2_2 BOOLEAN,
        apv_p2_2_comments CHARACTER VARYING(500),
        apv_p3_1 BOOLEAN,
        apv_p3_1_comments CHARACTER VARYING(500),
        apv_p3_2 BOOLEAN,
        apv_p3_2_1 BOOLEAN,
        apv_p3_2_1_comments CHARACTER VARYING(500),
        apv_p3_3 BOOLEAN,
        apv_p3_3_1 CHARACTER VARYING(500),
        apv_p3_4 BOOLEAN,
        apv_p3_4_1 CHARACTER VARYING(500),
        apv_p3_5 BOOLEAN,
        apv_p3_5_1 CHARACTER VARYING(500),
        apv_p4 CHARACTER VARYING(10),
        apv_p4_comments CHARACTER VARYING(500),
        apv_p5 BOOLEAN,
        apv_p5_comments CHARACTER VARYING(500),
        apv_p6 CHARACTER VARYING(500),
        apv_codest BIGINT NOT NULL,
        PRIMARY KEY (apv_id),
        CONSTRAINT fk7dca33bda45533db FOREIGN KEY (apv_codest) REFERENCES public.oli_establiment (
        est_codi),
        CONSTRAINT fk7dca33bd402d12a1 FOREIGN KEY (apv_responsable) REFERENCES public.qua_despers (
        dpe_codi)
    );
COMMENT ON TABLE qua_appcc_plaverificacio
IS
    'Qualitat - APPCC - PlaVerificacio';
COMMENT ON COLUMN qua_appcc_plaverificacio.apv_data
IS
    'Data Realitzacio';
COMMENT ON COLUMN qua_appcc_plaverificacio.apv_p1
IS
    'Pregunta 1';
COMMENT ON COLUMN qua_appcc_plaverificacio.apv_p1_1
IS
    'Pregunta 1.1';
COMMENT ON COLUMN qua_appcc_plaverificacio.apv_p1_2
IS
    'Pregunta 1.2';
COMMENT ON COLUMN qua_appcc_plaverificacio.apv_p1_2_comments
IS
    'Pregunta 1.2 - Comentaris';
COMMENT ON COLUMN qua_appcc_plaverificacio.apv_p1_3
IS
    'Pregunta 1.3';
COMMENT ON COLUMN qua_appcc_plaverificacio.apv_p1_3_comments
IS
    'Pregunta 1.3 - Comentaris';
COMMENT ON COLUMN qua_appcc_plaverificacio.apv_p1_4
IS
    'Pregunta 1.4';
COMMENT ON COLUMN qua_appcc_plaverificacio.apv_p1_5
IS
    'Pregunta 1.5';
COMMENT ON COLUMN qua_appcc_plaverificacio.apv_p2
IS
    'Pregunta 2';
COMMENT ON COLUMN qua_appcc_plaverificacio.apv_p2_1
IS
    'Pregunta 2.1';
COMMENT ON COLUMN qua_appcc_plaverificacio.apv_p2_2
IS
    'Pregunta 2.2';
COMMENT ON COLUMN qua_appcc_plaverificacio.apv_p2_2_comments
IS
    'Pregunta 2.2 - Comentaris';
COMMENT ON COLUMN qua_appcc_plaverificacio.apv_p3_1
IS
    'Pregunta 3.1';
COMMENT ON COLUMN qua_appcc_plaverificacio.apv_p3_1_comments
IS
    'Pregunta 3.1 - Comentaris';
COMMENT ON COLUMN qua_appcc_plaverificacio.apv_p3_2
IS
    'Pregunta 3.2';
COMMENT ON COLUMN qua_appcc_plaverificacio.apv_p3_2_1
IS
    'Pregunta 3.2.1';
COMMENT ON COLUMN qua_appcc_plaverificacio.apv_p3_2_1_comments
IS
    'Pregunta 3.2.1 - Comentaris';
COMMENT ON COLUMN qua_appcc_plaverificacio.apv_p3_3
IS
    'Pregunta 3.3';
COMMENT ON COLUMN qua_appcc_plaverificacio.apv_p3_3_1
IS
    'Pregunta 3.3.1';
COMMENT ON COLUMN qua_appcc_plaverificacio.apv_p3_4
IS
    'Pregunta 3.4';
COMMENT ON COLUMN qua_appcc_plaverificacio.apv_p3_4_1
IS
    'Pregunta 3.4.1';
COMMENT ON COLUMN qua_appcc_plaverificacio.apv_p3_5
IS
    'Pregunta 3.5';
COMMENT ON COLUMN qua_appcc_plaverificacio.apv_p3_5_1
IS
    'Pregunta 3.5.1';
COMMENT ON COLUMN qua_appcc_plaverificacio.apv_p4
IS
    'Pregunta 4';
COMMENT ON COLUMN qua_appcc_plaverificacio.apv_p4_comments
IS
    'Pregunta 4 - Comentaris';
COMMENT ON COLUMN qua_appcc_plaverificacio.apv_p5
IS
    'Pregunta 5';
COMMENT ON COLUMN qua_appcc_plaverificacio.apv_p5_comments
IS
    'Pregunta 5 - Comentaris';
COMMENT ON COLUMN qua_appcc_plaverificacio.apv_p6
IS
    'Pregunta 6';

    
    
    
----------------------------------------------------------------------
----------------------------------------------------------------------
----------------------------- 31/01/2011 -----------------------------
----------------------------------------------------------------------
----------------------------------------------------------------------
  
-- TAULA QUALITAT_APPCC --
CREATE
    TABLE qua_appcc
    (
        app_id BIGINT DEFAULT nextval('qua_appcc_app_id_seq'::regclass) NOT NULL,
        app_codest BIGINT NOT NULL,
        PRIMARY KEY (app_id),
        CONSTRAINT fk9f02da3f350e26a1 FOREIGN KEY (app_codest) REFERENCES public.oli_establiment (
        est_codi)
    );
COMMENT ON TABLE qua_appcc
IS
    'Qualitat - APPCC';

-- TAULA QUALITAT_APPCC_Producte --
CREATE
    TABLE qua_appcc_producte
    (
        apr_id BIGINT DEFAULT nextval('qua_appcc_producte_apr_id_seq'::regclass) NOT NULL,
        apr_descripcio CHARACTER VARYING(500),
        apr_us CHARACTER VARYING(500),
        apr_appcc BIGINT NOT NULL,
        PRIMARY KEY (apr_id),
        CONSTRAINT fk95363f569fddc8ac FOREIGN KEY (apr_appcc) REFERENCES public.qua_appcc (app_id)
    );
COMMENT ON TABLE qua_appcc_producte
IS
    'Qualitat - APPCC - Producte';
COMMENT ON COLUMN qua_appcc_producte.apr_descripcio
IS
    'Descripcio';
COMMENT ON COLUMN qua_appcc_producte.apr_us
IS
    'Us Previst';
    
-- TAULA QUALITAT_APPPUE --
CREATE
    TABLE qua_apppue
    (
        apu_codapp BIGINT NOT NULL,
        apu_codpue BIGINT NOT NULL,
        CONSTRAINT fk4158a101196a169 FOREIGN KEY (apu_codapp) REFERENCES public.qua_appcc (app_id),
        CONSTRAINT fk4158a101a4dd731f FOREIGN KEY (apu_codpue) REFERENCES public.qua_puetre (pue_id
        )
    );
COMMENT ON COLUMN qua_apppue.apu_codapp
IS
    'Codi de appcc';
COMMENT ON COLUMN qua_apppue.apu_codpue
IS
    'Codi del carrec';
    
    