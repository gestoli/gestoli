/*
 * Generated by XDoclet - Do not edit!
 */
package es.caib.gestoli.logic.interfaces;

/**
 * Home interface for OliQualitatEjb.
 */
public interface OliQualitatEjbHome
   extends javax.ejb.EJBHome
{
   public static final String COMP_NAME="java:comp/env/ejb/OliQualitatEjb";
   public static final String JNDI_NAME="OliQualitatEjb";

   public es.caib.gestoli.logic.interfaces.OliQualitatEjb create()
      throws javax.ejb.CreateException,java.rmi.RemoteException;

}