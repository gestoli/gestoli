/*
 * Generated by XDoclet - Do not edit!
 */
package es.caib.gestoli.logic.interfaces;

/**
 * Local home interface for OliSistraEjb.
 */
public interface OliSistraEjbLocalHome
   extends javax.ejb.EJBLocalHome
{
   public static final String COMP_NAME="java:comp/env/ejb/OliSistraEjbLocal";
   public static final String JNDI_NAME="OliSistraEjbLocal";

   public es.caib.gestoli.logic.interfaces.OliSistraEjbLocal create()
      throws javax.ejb.CreateException;

}