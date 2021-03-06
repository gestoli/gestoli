/*
 * Generated by XDoclet - Do not edit!
 */
package es.caib.gestoli.logic.interfaces;

/**
 * Local interface for OliSistraEjb.
 */
public interface OliSistraEjbLocal
   extends javax.ejb.EJBLocalObject
{
   /**
    * <!-- begin-xdoclet-definition -->
    */
   public void ejbRemove(  ) ;

   /**
    * <!-- begin-xdoclet-definition -->
    */
   public es.caib.gestoli.logic.model.Establiment establimentAmbCifNifResponsable( java.lang.String cif ) ;

   /**
    * <!-- begin-xdoclet-definition -->
    */
   public java.util.Collection findEntradesOlivaEntreDates( java.util.Date dataInici,java.util.Date dataFi,java.lang.Long idEstabliment ) ;

   /**
    * <!-- begin-xdoclet-definition -->
    */
   public java.util.Collection findElaboracionsEntreDates( java.util.Date dataInici,java.util.Date dataFi,java.lang.Long idEstabliment,boolean asc,java.lang.Boolean valid ) ;

   /**
    * <!-- begin-xdoclet-definition -->
    */
   public java.util.Collection findSortidesLotEntreDates( java.util.Date dataInici,java.util.Date dataFi,java.lang.Long idEstabliment ) ;

   /**
    * <!-- begin-xdoclet-definition -->
    */
   public java.util.Collection findSortidesDipositEntreDates( java.util.Date dataInici,java.util.Date dataFi,java.lang.Long idEstabliment ) ;

   /**
    * Busca las salidas de depositos entre 2 fechas para un establecimiento <!-- begin-xdoclet-definition -->
    */
   public java.util.Collection getSortidesDipositEntreFechasAndEstablecimiento( java.util.Date finicio,java.util.Date ffin,java.lang.Long idEst,java.lang.Boolean valid ) ;

   /**
    * <!-- begin-xdoclet-definition -->
    */
   public java.util.Collection findExistenciesDipositsByEstablimentCategoriasAndData( java.lang.Long establimentId,java.lang.Object[] categorias,java.util.Date data ) ;

   /**
    * <!-- begin-xdoclet-definition -->
    */
   public java.util.Collection findExistenciesLotsByEstablimentCategoriasAndData( java.lang.Long establimentId,java.lang.Object[] categorias,java.util.Date data ) ;

   /**
    * <!-- begin-xdoclet-definition -->
    */
   public java.util.Collection findAllCampanyes(  ) ;

   /**
    * <!-- begin-xdoclet-definition -->
    */
   public java.util.Date findDataFiCampanya( java.lang.Long idCampanya ) ;

   /**
    * <!-- begin-xdoclet-definition -->
    */
   public java.util.Collection findLotsByEstablimentEntreDates( java.lang.Long idEstabliment,java.util.Date dataInici,java.util.Date dataFi,java.lang.Boolean valid ) ;

   /**
    * <!-- begin-xdoclet-definition -->
    */
   public java.util.Collection findExistenciesLotsByEstabliment( java.lang.Long establimentId ) ;

   /**
    * <!-- begin-xdoclet-definition -->
    */
   public java.util.Collection findDipositsActiusByEstabliment( java.lang.Long establimentId ) ;

   /**
    * <!-- begin-xdoclet-definition -->
    */
   public java.util.List findEtiquetatgesByEstabliment( java.lang.Long idEstabliment ) ;

   /**
    * <!-- begin-xdoclet-definition -->
    */
   public java.util.Collection movimentsEntreEstablimentsEntradaConsulta( java.util.Date dataInici,java.util.Date dataFi,java.lang.Long idEstabliment ) ;

   /**
    * <!-- begin-xdoclet-definition -->
    */
   public es.caib.gestoli.logic.model.EntradaDiposit findEntradaDipositByTraza( java.lang.Long idTraza ) ;

   /**
    * set the hibernate template.
    * @param hibernateTemplate the hibernate spring template. <!-- begin-xdoclet-definition -->
    */
   public void setHibernateTemplate( org.springframework.orm.hibernate3.HibernateTemplate hibernateTemplate ) ;

   /**
    * get the hibernate template.
    * @return the hibernate spring template. <!-- begin-xdoclet-definition -->
    */
   public org.springframework.orm.hibernate3.HibernateTemplate getHibernateTemplate(  ) ;

}
