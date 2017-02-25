/*
 * Generated by XDoclet - Do not edit!
 */
package es.caib.gestoli.logic.interfaces;

/**
 * Remote interface for OliProcessosEjb.
 * @generated 
 */
public interface OliProcessosEjb
   extends javax.ejb.EJBObject
{
   /**
    * NomÃƒÂ©s serveix per poder especificar els permisos amb xdoclet. <!-- begin-xdoclet-definition -->
    * @generated     */
   public void ejbRemove(  )
      throws java.rmi.RemoteException;

   /**
    * Executa un procÃƒÂ©s d'entrada de oliva al sistema
    * @param dataExecucio
    * @param hora
    * @param codi
    * @param fincaId
    * @param plantacioId
    * @param descomposicioPartidaOliva
    * @param zonaId
    * @param olivicultorId
    * @param estatOliva
    * @param mezcla
    * @param quantitat <!-- begin-xdoclet-definition -->
    * @generated     */
   public java.lang.Long entradaOliva( java.lang.Long establimentId,java.util.Date dataExecucio,java.lang.String hora,java.lang.String codi,java.lang.Long fincaId,java.lang.Long plantacioId,es.caib.gestoli.logic.model.DescomposicioPartidaOliva[] descomposicioPartidaOliva,java.lang.Long zonaId,java.lang.Long olivicultorId,java.lang.Boolean sana,java.lang.Integer estat,java.lang.Boolean mezcla,java.lang.Float quantitat,java.lang.String tipusTraza,java.lang.Boolean esEcologic )
      throws java.rmi.RemoteException;

   /**
    * Executa un procÃƒÂ©s d'entrada de oliva al sistema
    * @param dataExecucio
    * @param hora
    * @param codi
    * @param fincaId
    * @param plantacioId
    * @param descomposicioPartidaOliva
    * @param zonaId
    * @param olivicultorId
    * @param envasPetit
    * @param envasRigid
    * @param envasVentilat
    * @param tipusOlivaTaula
    * @param quantitat <!-- begin-xdoclet-definition -->
    * @generated     */
   public java.lang.Long entradaOliva( java.lang.Long establimentId,java.util.Date dataExecucio,java.lang.String hora,java.lang.String codi,java.lang.Long fincaId,java.lang.Long plantacioId,es.caib.gestoli.logic.model.DescomposicioPartidaOliva[] descomposicioPartidaOliva,java.lang.Long zonaId,java.lang.Long olivicultorId,java.lang.Boolean envasPetit,java.lang.Boolean envasRigid,java.lang.Boolean envasVentilat,java.lang.Integer tipusOlivaTaula,java.lang.Float quantitat,java.lang.String tipusTraza,java.lang.Boolean esEcologic )
      throws java.rmi.RemoteException;

   /**
    * Cerca la campanya actual <!-- begin-xdoclet-definition -->
    */
   public java.lang.Long campanyaCercaActual(  )
      throws java.rmi.RemoteException;

   /**
    * Executa un procÃƒÂ©s d'entrada de oliva al sistema
    * @param dataExecucio
    * @param hora
    * @param codi
    * @param fincaId
    * @param plantacioId
    * @param descomposicioPartidaOliva
    * @param zonaId
    * @param olivicultorId
    * @param envasPetit
    * @param envasRigid
    * @param envasVentilat
    * @param tipusOlivaTaula
    * @param quantitat <!-- begin-xdoclet-definition -->
    * @generated     */
   public java.lang.Long entradaFonoll( java.lang.Long establimentId,java.util.Date dataExecucio,java.lang.String hora,java.lang.String codi,java.lang.String titular,java.lang.Double kgInici,java.lang.Long municipiId,java.lang.String poligon,java.lang.String parcela,java.lang.String tipusTraza )
      throws java.rmi.RemoteException;

   /**
    * Executa un procÃƒÂ©s d'elaboraciÃƒÂ³ d'oli al sistema
    * @param elaboracio <!-- begin-xdoclet-definition -->
    * @generated     */
   public java.lang.Integer findNumElaboracioByData( java.util.Date data,java.lang.Long establimentId )
      throws java.rmi.RemoteException;

   /**
    * Executa un procÃƒÂ©s d'elaboraciÃƒÂ³ d'oli al sistema
    * @param elaboracio <!-- begin-xdoclet-definition -->
    * @generated     */
   public java.lang.Integer findNumElaboracioOlivaByData( java.util.Date data,java.lang.Long establimentId )
      throws java.rmi.RemoteException;

   /**
    * Busca todas las partidas de oliva y la su detalle de los olivicultores dados de alta en la DO de la campanya actual <!-- begin-xdoclet-definition -->
    */
   public java.util.HashMap findkgsAcumulatsDescomposicioPartidaOlivaPerOlivicultor( java.lang.Long idOlivicultor )
      throws java.rmi.RemoteException;

   /**
    * <!-- begin-xdoclet-definition -->
    */
   public es.caib.gestoli.logic.model.VarietatOli getVarietatOliById( java.lang.Integer id )
      throws java.rmi.RemoteException;

   /**
    * <!-- begin-xdoclet-definition -->
    */
   public es.caib.gestoli.logic.model.PartidaOli getPartidaOliById( java.lang.Long id )
      throws java.rmi.RemoteException;

   /**
    * <!-- begin-xdoclet-definition -->
    */
   public es.caib.gestoli.logic.model.PartidaOliva partidaAmbId( java.lang.Long id )
      throws java.rmi.RemoteException;

   /**
    * <!-- begin-xdoclet-definition -->
    */
   public void dipositModificar( es.caib.gestoli.logic.model.Diposit diposit )
      throws java.rmi.RemoteException;

   /**
    * <!-- begin-xdoclet-definition -->
    */
   public es.caib.gestoli.logic.model.Elaboracio findElaboracioOliById( java.lang.Long idElaboracio )
      throws java.rmi.RemoteException;

   /**
    * <!-- begin-xdoclet-definition -->
    */
   public es.caib.gestoli.logic.model.ElaboracioOliva findElaboracioOlivaById( java.lang.Long idElaboracioOliva )
      throws java.rmi.RemoteException;

   /**
    * <!-- begin-xdoclet-definition -->
    */
   public java.util.Collection findOliRetiratPropietarioByElaboracio( java.lang.Long idElaboracio )
      throws java.rmi.RemoteException;

   /**
    * <!-- begin-xdoclet-definition -->
    */
   public java.util.Iterator findEntradaDiposiOliByElaboracio( java.lang.Long idElaboracio,java.lang.Boolean valid )
      throws java.rmi.RemoteException;

   /**
    * <!-- begin-xdoclet-definition -->
    */
   public int findEntradaDiposiOliByElaboracioSize( java.lang.Long idElaboracio )
      throws java.rmi.RemoteException;

   /**
    * <!-- begin-xdoclet-definition -->
    */
   public es.caib.gestoli.logic.model.CategoriaOli findCategoriaOliById( java.lang.Integer idCategotia )
      throws java.rmi.RemoteException;

   /**
    * Añade todos los registros necesarios para la elaboración del aceite <!-- begin-xdoclet-definition -->
    */
   public java.lang.Long generarElaboracioOli( es.caib.gestoli.logic.util.ElaboracioAux aux,java.lang.Long idEstabliment,java.lang.String desqualificat,java.lang.String pendentQualificar )
      throws java.lang.Exception, java.rmi.RemoteException;

   /**
    * Añade todos los registros necesarios para la elaboración del aceite <!-- begin-xdoclet-definition -->
    */
   public java.lang.Long generarElaboracioOliva( es.caib.gestoli.logic.model.ElaboracioOliva eo,es.caib.gestoli.logic.model.Bota bot,java.lang.Long idEstabliment )
      throws java.lang.Exception, java.rmi.RemoteException;

   /**
    * Añade todos los registros necesarios para el trasvase del aceite
    * @param data
    * @param dipositsOrigen
    * @param dipositDesti
    * @param litresExtrets
    * @param litrosAfegits
    * @param acidesaDesti
    * @param establiment
    * @param desqualificat
    * @param pendentQualificar
    * @throws Exception <!-- begin-xdoclet-definition -->
    */
   public void generarTrasbals( java.util.Date data,es.caib.gestoli.logic.model.Diposit[] dipositsOrigen,es.caib.gestoli.logic.model.Diposit dipositDesti,java.lang.Double[] litresExtrets,java.lang.Double litrosAfegits,java.lang.Double acidesaDesti,es.caib.gestoli.logic.model.Establiment establiment,java.lang.String desqualificat,java.lang.String pendentQualificar,java.lang.String mescla,java.lang.Long partidaId )
      throws java.lang.Exception, java.rmi.RemoteException;

   /**
    * Añade todos los registros necesarios para la perdida de aceite <!-- begin-xdoclet-definition -->
    */
   public void generarPerdida( java.util.Date data,es.caib.gestoli.logic.model.Diposit diposit,java.lang.Double litros,es.caib.gestoli.logic.model.Establiment establiment,java.lang.String desti,java.lang.String descripcion )
      throws java.lang.Exception, java.rmi.RemoteException;

   /**
    * Añade todos los registros necesarios para la salida de aceite <!-- begin-xdoclet-definition -->
    */
   public void generarSortidaOli( java.util.Date data,es.caib.gestoli.logic.model.Diposit diposit,java.lang.Double litros,java.lang.String destinatari,java.lang.String numeroDocument,java.lang.String tipusDocument,java.lang.String observacions,es.caib.gestoli.logic.model.Pais pais,es.caib.gestoli.logic.model.Provincia provincia,es.caib.gestoli.logic.model.Municipi municipi,java.lang.Boolean desqualificar,java.lang.String desqualificat )
      throws java.lang.Exception, java.rmi.RemoteException;

   /**
    * Añade todos los registros necesarios para la perdida de aceite <!-- begin-xdoclet-definition -->
    */
   public void analiticaCrear( es.caib.gestoli.logic.model.Analitica a,java.lang.String stringAnalitica )
      throws java.lang.Exception, java.rmi.RemoteException;

   /**
    * Añade todos los registros necesarios para el autocontrol <!-- begin-xdoclet-definition -->
    */
   public void autocontrolCrear( es.caib.gestoli.logic.model.AutocontrolOliva a,java.lang.String stringAutocontrol )
      throws java.lang.Exception, java.rmi.RemoteException;

   /**
    * Anade todos los registros necesarios para la perdida de aceite <!-- begin-xdoclet-definition -->
    */
   public void generarDescalificacion( java.util.Date data,es.caib.gestoli.logic.model.Diposit diposit,es.caib.gestoli.logic.model.Establiment establiment,java.lang.String idCategoria,java.lang.String pendentQualificacioObservacio,java.lang.String observaciones )
      throws java.lang.Exception, java.rmi.RemoteException;

   /**
    * Añade todos los registros necesarios para la creacion de aceite <!-- begin-xdoclet-definition -->
    */
   public void crearLot( es.caib.gestoli.logic.model.Lot lot,es.caib.gestoli.logic.model.Establiment establiment,java.lang.Boolean perduaOli,java.lang.Boolean etiquetar,java.lang.String perdua,java.lang.String embotellat )
      throws java.lang.Exception, java.rmi.RemoteException;

   /**
    * AÃƒÂ±ade todos los registros necesarios para la creacion de aceite <!-- begin-xdoclet-definition -->
    */
   public void crearLot( es.caib.gestoli.logic.model.Lot lot,boolean introExistencies )
      throws java.lang.Exception, java.rmi.RemoteException;

   /**
    * Añade todos los registros necesarios para la creacion de aceituna envasada <!-- begin-xdoclet-definition -->
    */
   public void crearLot( es.caib.gestoli.logic.model.Lot lot,es.caib.gestoli.logic.model.Establiment establiment,java.lang.Double pH1,java.lang.Double pH2,java.lang.String lotAcid,java.lang.Double quantitatAcid )
      throws java.lang.Exception, java.rmi.RemoteException;

   /**
    * Añade todos los registros necesarios para el cierre de libros del aceite <!-- begin-xdoclet-definition -->
    */
   public void generarTancamentLlibres( es.caib.gestoli.logic.model.Establiment establiment,java.util.Date data,java.lang.Long[] dipositId,java.lang.Double[] acidesa,java.lang.Long[] partidaOliId,java.lang.Double[] litres,java.lang.String[] observacions,java.lang.Long[] lotId,java.lang.Long[] marcaId,es.caib.gestoli.logic.model.Etiquetatge[] etiquetatge,java.lang.String[] numeroLot,es.caib.gestoli.logic.model.VarietatOliva[][] varietats,java.lang.Long[] partidaOliIdLot,es.caib.gestoli.logic.model.Zona[] zona,java.lang.Double[] acidesaLot,java.lang.Integer[] numeroBotellesInicials,java.lang.Integer[] numeroBotellesActuals,java.lang.Boolean[] etiquetar,java.lang.String[] observacionsLot,java.util.Date[] dataConsum,java.lang.String tancamentLlibres )
      throws java.lang.Exception, java.rmi.RemoteException;

   /**
    * Añade todos los registros necesarios para el cierre de libros del aceite <!-- begin-xdoclet-definition -->
    */
   public void insertarDevolucioBotelles( es.caib.gestoli.logic.model.Lot lot,java.lang.Long idSortida,java.lang.Integer botelles,java.util.Date data,java.lang.String observacio )
      throws java.rmi.RemoteException;

   /**
    * Añade todos los registros necesarios para la creacion de aceite <!-- begin-xdoclet-definition -->
    */
   public void crearLotCierreLibros( es.caib.gestoli.logic.model.Lot lot,boolean introExistencies,java.lang.String tancamentLlibres )
      throws java.lang.Exception, java.rmi.RemoteException;

   /**
    * AÃƒÂ±ade todos los registros necesarios para el trasvase del aceite
    * @param data
    * @param dipositsOrigen
    * @param establimentDesti
    * @param establiment
    * @throws Exception <!-- begin-xdoclet-definition -->
    */
   public java.lang.Long[] generarMoureDiposits( java.util.Date data,es.caib.gestoli.logic.model.Diposit[] dipositsOrigen,java.lang.Long establimentDesti,es.caib.gestoli.logic.model.Establiment establiment,java.lang.String rolDoControlador,java.lang.String rolDoGestor,java.lang.String emailFrom )
      throws java.lang.Exception, java.rmi.RemoteException;

   /**
    * Añade todos los registros necesarios para el trasvase del aceite
    * @param data
    * @param dipositsOrigen
    * @param establimentDesti
    * @param establiment
    * @throws Exception <!-- begin-xdoclet-definition -->
    */
   public void generarMoureOli( java.util.Date data,es.caib.gestoli.logic.model.Diposit[] dipositsOrigen,java.lang.Long establimentDesti,es.caib.gestoli.logic.model.Establiment establiment,java.lang.String rolDoControlador,java.lang.String rolDoGestor,java.lang.String emailFrom )
      throws java.lang.Exception, java.rmi.RemoteException;

   /**
    * Añade todos los registros necesarios para el trasvase del aceite
    * @param data
    * @param dipositsOrigen
    * @param dipositDesti
    * @param litresExtrets
    * @param litrosAfegits
    * @param acidesaDesti
    * @param establiment
    * @param desqualificat
    * @param pendentQualificar
    * @throws Exception <!-- begin-xdoclet-definition -->
    */
   public void acceptarTrasllatOli( java.util.Date data,es.caib.gestoli.logic.model.Diposit[] dipositsOrigen,es.caib.gestoli.logic.model.Diposit dipositDesti,java.lang.Double[] litresExtrets,java.lang.Double litrosAfegits,java.lang.Double acidesaDesti,es.caib.gestoli.logic.model.Establiment establimentOrigen,es.caib.gestoli.logic.model.Establiment establimentDesti,java.lang.String desqualificat,java.lang.String pendentQualificar,java.lang.String mescla,java.lang.Long idTrasllat )
      throws java.lang.Exception, java.rmi.RemoteException;

   /**
    * Añade todos los registros necesarios para la creacion de aceite <!-- begin-xdoclet-definition -->
    */
   public void etiquetar( es.caib.gestoli.logic.model.Lot lot,java.lang.Long[] idEtiquetes,java.lang.Integer[] etiquetesInicials,java.lang.Integer[] etiquetesFinals )
      throws java.lang.Exception, java.rmi.RemoteException;

   /**
    * Cerca tots els establiments activos <!-- begin-xdoclet-definition -->
    */
   public java.util.Collection findRestoEstablimentsActivosZonaFicticia( java.lang.Long campanyaId,java.lang.Long establimentId )
      throws java.rmi.RemoteException;

   /**
    * Marca como aceptado un traslado de deposito <!-- begin-xdoclet-definition -->
    */
   public void aceptarTraslado( java.lang.Long trasllatDipositId,java.util.Date data )
      throws java.rmi.RemoteException;

   /**
    * Marca como rechazado un traslado de deposito <!-- begin-xdoclet-definition -->
    */
   public void rechazarTraslado( java.lang.Long trasllatDippositId )
      throws java.lang.Exception, java.rmi.RemoteException;

   /**
    * Devuelve los depositos del traslado a su establecimiento de origen <!-- begin-xdoclet-definition -->
    */
   public void devolverTraslado( java.lang.Long trasllatDippositId,java.lang.String rolDoControlador,java.lang.String rolDoGestor,java.lang.String emailFrom )
      throws java.lang.Exception, java.rmi.RemoteException;

   /**
    * Acceptació dels depòsits retornats <!-- begin-xdoclet-definition -->
    */
   public void acceptarDevolverTraslado( java.lang.Long trasllatDippositId,java.lang.String retorno,java.util.Date data )
      throws java.lang.Exception, java.rmi.RemoteException;

   /**
    * Añade todos los registros necesarios para la creacion de aceite <!-- begin-xdoclet-definition -->
    */
   public void sortidaEmbotelladora( es.caib.gestoli.logic.model.SortidaLot sortidaLot,es.caib.gestoli.logic.model.Zona zonaCanvi,java.lang.String entrada,java.lang.String venda,java.lang.String canviZona )
      throws java.lang.Exception, java.rmi.RemoteException;

   /**
    * Cerca tots els processos d'un establiment <!-- begin-xdoclet-definition -->
    */
   public java.util.Collection processosFindAllByEstablimentAndData( java.lang.Long establimentId,java.lang.String perdua,java.lang.String pendentQualificacio,int tipoUsu,java.lang.String devolver,java.lang.String tancamentLlibres,java.lang.String entrada,java.lang.String stringAnalitica,java.lang.String desqualificat,java.util.Date dataInici,java.util.Date dataFi,boolean oliva )
      throws java.rmi.RemoteException;

   /**
    * Cerca si es pot borrar el procés <!-- begin-xdoclet-definition -->
    */
   public boolean getValid( es.caib.gestoli.logic.model.Traza traza )
      throws java.rmi.RemoteException;

   /**
    * Cerca si es pot borrar el procés <!-- begin-xdoclet-definition -->
    */
   public boolean getEnTrasllat( es.caib.gestoli.logic.model.Traza tra )
      throws java.rmi.RemoteException;

   /**
    * Cerca tots els processos d'un establiment <!-- begin-xdoclet-definition -->
    */
   public void eliminarProces( java.lang.Long trazaId,int tipoPrc,java.lang.String rolDoControlador,java.lang.String rolDoGestor,java.lang.String emailFrom,java.lang.String pendentQualificacio,java.lang.String tancamentLlibres,java.lang.String stringAnalitica,java.lang.String desqualificat )
      throws java.lang.Exception, java.rmi.RemoteException;

   /**
    * Edita el procés amb la traça indicada <!-- begin-xdoclet-definition -->
    */
   public void editarProces( java.lang.Long trazaId,int tipoPrc,java.util.Date data,java.lang.String desqualificat )
      throws java.lang.Exception, java.rmi.RemoteException;

   /**
    * Obté les dates d'inici i de fi que pot tenir el procés amb la traça indicada <!-- begin-xdoclet-definition -->
    */
   public java.util.Date[] datesProces( java.lang.Long trazaId,int tipoPrc,java.lang.String desqualificat )
      throws java.lang.Exception, java.rmi.RemoteException;

   /**
    * set the hibernate template.
    * @param hibernateTemplate the hibernate spring template. <!-- begin-xdoclet-definition -->
    */
   public void setHibernateTemplate( org.springframework.orm.hibernate3.HibernateTemplate hibernateTemplate )
      throws java.rmi.RemoteException;

   /**
    * get the hibernate template.
    * @return the hibernate spring template. <!-- begin-xdoclet-definition -->
    */
   public org.springframework.orm.hibernate3.HibernateTemplate getHibernateTemplate(  )
      throws java.rmi.RemoteException;

   /**
    * get the messageSourceAccessor
    * @return the messageSourceAccessor <!-- begin-xdoclet-definition -->
    */
   public org.springframework.context.support.MessageSourceAccessor getMessageSourceAccessor(  )
      throws java.rmi.RemoteException;

   /**
    * set the messageSourceAccessor. <!-- begin-xdoclet-definition -->
    */
   public void setMessageSourceAccessor( org.springframework.context.support.MessageSourceAccessor messageSourceAccessor )
      throws java.rmi.RemoteException;

   /**
    * set the messageSourceAccessor. <!-- begin-xdoclet-definition -->
    */
   public void setTipusEstablimentTafona( java.lang.Integer tipusEstablimentTafona )
      throws java.rmi.RemoteException;

   /**
    * set the messageSourceAccessor. <!-- begin-xdoclet-definition -->
    */
   public void setTipusEstablimentEnvasadora( java.lang.Integer tipusEstablimentEnvasadora )
      throws java.rmi.RemoteException;

   /**
    * set the messageSourceAccessor. <!-- begin-xdoclet-definition -->
    */
   public void setTipusEstablimentTafonaEnvasadora( java.lang.Integer tipusEstablimentTafonaEnvasadora )
      throws java.rmi.RemoteException;

   /**
    * Añade todos los registros necesarios para la salida d'oliva crua a granel <!-- begin-xdoclet-definition -->
    */
   public void generarSortidaOlivaCruaGranel( java.util.Date data,java.lang.Double kilos,es.caib.gestoli.logic.model.Diposit diposit,java.lang.String destinatari,java.lang.String numeroDocument,java.lang.String tipusDocument,java.lang.String observacions,es.caib.gestoli.logic.model.Pais pais,es.caib.gestoli.logic.model.Provincia provincia,es.caib.gestoli.logic.model.Municipi municipi,java.lang.Boolean desqualificar,java.lang.String desqualificat,java.lang.Long idPartidaOliva,java.lang.String accio,java.lang.String vendaMotiu,java.lang.Long idZona,es.caib.gestoli.logic.model.Establiment establiment )
      throws java.lang.Exception, java.rmi.RemoteException;

   /**
    * Añade todos los registros necesarios para la salida d'oliva en bota a granel <!-- begin-xdoclet-definition -->
    */
   public void generarSortidaBotaGranel( java.util.Date data,java.lang.Double kilos,es.caib.gestoli.logic.model.Bota bota,java.lang.String destinatari,java.lang.String numeroDocument,java.lang.String tipusDocument,java.lang.String observacions,es.caib.gestoli.logic.model.Pais pais,es.caib.gestoli.logic.model.Provincia provincia,es.caib.gestoli.logic.model.Municipi municipi,java.lang.Boolean desqualificar,java.lang.String desqualificat,java.lang.Long idPartidaOliva,java.lang.String accio,java.lang.String vendaMotiu,java.lang.Long idZona,es.caib.gestoli.logic.model.Establiment establiment )
      throws java.lang.Exception, java.rmi.RemoteException;

   /**
    * Modifica la venda del lot <!-- begin-xdoclet-definition -->
    */
   public void modificarSortidaLot( es.caib.gestoli.logic.model.SortidaLot sortidaLot,java.lang.String venda,java.lang.Integer venudesInicialment )
      throws java.lang.Exception, java.rmi.RemoteException;

}