/*
 * Generated by XDoclet - Do not edit!
 */
package es.caib.gestoli.logic.interfaces;

/**
 * Local interface for OliArxiuEjb.
 */
public interface OliArxiuEjbLocal
   extends javax.ejb.EJBLocalObject
{
   /**
    * Només serveix per poder especificar els permisos amb xdoclet. <!-- begin-xdoclet-definition -->
    */
   public void ejbRemove(  ) ;

   /**
    * <!-- begin-xdoclet-definition -->
    */
   public java.lang.Long arxiuCrear( java.lang.String nom,java.lang.Integer tamany,java.lang.String tipusMime,byte[] contingut ) ;

   /**
    * <!-- begin-xdoclet-definition -->
    */
   public void arxiuModificar( java.lang.Long id,java.lang.String nom,java.lang.Integer tamany,java.lang.String tipusMime,byte[] contingut ) ;

   /**
    * <!-- begin-xdoclet-definition -->
    */
   public void arxiuEsborrar( java.lang.Long id ) ;

   /**
    * <!-- begin-xdoclet-definition -->
    */
   public java.util.Collection arxiuCercaTots(  ) ;

   /**
    * <!-- begin-xdoclet-definition -->
    */
   public es.caib.gestoli.logic.model.Arxiu arxiuCercaId( java.lang.Long id ) ;

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
