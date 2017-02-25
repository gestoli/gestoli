/*
 * Generated by XDoclet - Do not edit!
 */
package es.caib.gestoli.logic.interfaces;

/**
 * Remote interface for OliUsuariEjb.
 */
public interface OliUsuariEjb
   extends javax.ejb.EJBObject
{
   /**
    * Emmagatzema el SessionContext per poder-lo emprar als mètodes de negoci de l'EJB. <!-- begin-xdoclet-definition -->
    */
   public void setSessionContext( javax.ejb.SessionContext context )
      throws java.rmi.RemoteException;

   /**
    * Només serveix per poder especificar els permisos amb xdoclet. <!-- begin-xdoclet-definition -->
    */
   public void ejbRemove(  )
      throws java.rmi.RemoteException;

   /**
    * Retorna el registre de tipus usuari que té el login especificat.
    * @return el registre de tipus usuari <!-- begin-xdoclet-definition -->
    */
   public es.caib.gestoli.logic.model.Usuari verificaLogin(  )
      throws java.rmi.RemoteException;

   /**
    * Comprova si l'establiment és correcte per un determinat usuari
    * @param idEstabliment
    * @param usuari
    * @return <!-- begin-xdoclet-definition -->
    */
   public boolean verificaEstabliment( java.lang.Long idEstabliment,java.lang.Long idUsuari )
      throws java.rmi.RemoteException;

   /**
    * Comprova si l'olivicultor és correcte per un determinat usuari
    * @param idOlivicultor
    * @param usuari
    * @return <!-- begin-xdoclet-definition -->
    */
   public boolean verificaOlivicultor( java.lang.Long idOlivicultor,java.lang.Long idUsuari )
      throws java.rmi.RemoteException;

   /**
    * Comprova si un grup és correcte per un determinat usuari.
    * @param idGrup
    * @param usuari
    * @return <!-- begin-xdoclet-definition -->
    */
   public boolean verificaGrup( java.lang.Long idGrup,java.lang.Long idUsuari )
      throws java.rmi.RemoteException;

   /**
    * Comprueba si existen olivicultores altaDO en la BBDD <!-- begin-xdoclet-definition -->
    */
   public boolean existenOlivicultoresAltaDOConFincas(  )
      throws java.rmi.RemoteException;

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

}
