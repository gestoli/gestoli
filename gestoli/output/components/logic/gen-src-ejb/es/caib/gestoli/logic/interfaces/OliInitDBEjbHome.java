/*
 * Generated by XDoclet - Do not edit!
 */
package es.caib.gestoli.logic.interfaces;

/**
 * Home interface for OliInitDBEjb.
 */
public interface OliInitDBEjbHome
   extends javax.ejb.EJBHome
{
   public static final String COMP_NAME="java:comp/env/ejb/OliInitDBEjb";
   public static final String JNDI_NAME="OliInitDBEjb";

   public es.caib.gestoli.logic.interfaces.OliInitDBEjb create()
      throws javax.ejb.CreateException,java.rmi.RemoteException;

}
