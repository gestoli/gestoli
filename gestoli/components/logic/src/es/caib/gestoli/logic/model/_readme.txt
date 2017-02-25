Para generar los archivos de modelo y sus archivos de mapeo hibernate, 
seguir las instrucciones de: "Proyectos Java AT4 - GestOli v1.0.doc" 
en el apartado "GENERACIÓN DE DATA OBJECTS Y MAPEO HIBERNATE:"


Otras consideraciones:


Grup.java
=========
	sustituir:	public class Grup implements java.io.Serializable {
	por:		public class Grup implements Comparable {


Grup.hbm.xml
============
	sustituir <set name="usuaris" inverse="true" table="oli_usugru">
	por       <set name="usuaris" inverse="false" table="oli_usugru">


Marca.hbm.xml
=============
	sustituir <set name="establiments" inverse="true" table="oli_estmar">
	por       <set name="establiments" inverse="false" table="oli_estmar">


PartidaOliva.java
=================
	sustituir:	public class PartidaOliva implements java.io.Serializable {
	por:		public class PartidaOliva implements Comparable {
	

PartidaOliva.hbm.xml
====================
	Eliminar :
	
				<meta attribute="class-code" inherit="false">
					/** * Devuelve una descripcion de Variedad / kilos */ public String getVarietatsQuilos() 
					{ DecimalFormat numberDecimalFormat = new DecimalFormat("###,###,##0.00"); String valor = ""; double total = 0; 
					if (this.descomposicioPartidesOlives != null) { Iterator desc = this.descomposicioPartidesOlives.iterator(); while (desc.hasNext())
					 { DescomposicioPartidaOliva dpo = (DescomposicioPartidaOliva) desc.next(); if (valor != null [dos ampersands] !"".equals(valor)) 
					 valor += [comillas br cierre comillas]; valor += dpo.getDescomposicioPlantacio().getVarietatOliva().getNom() + " (" + numberDecimalFormat.format(dpo.getKilos().doubleValue()) + " kgs.)";
					  total += dpo.getKilos().doubleValue(); } } return valor; } /** * Devuelve una descripcion de total kilos */ 
					  public String getTotalQuilos() { DecimalFormat numberDecimalFormat = new DecimalFormat("###,###,##0.00"); 
					  double total = 0; if (this.descomposicioPartidesOlives != null) { Iterator desc = this.descomposicioPartidesOlives.iterator();
					   while (desc.hasNext()) { DescomposicioPartidaOliva dpo = (DescomposicioPartidaOliva) desc.next(); total += dpo.getKilos().doubleValue(); } } 
					   return numberDecimalFormat.format(total); } /** * Devuelve una descripcion de total kilos */ public String getNomVarietat() { String nom = "";
					    if (this.descomposicioPartidesOlives != null) { Iterator desc = this.descomposicioPartidesOlives.iterator(); while (desc.hasNext()) 
					    { DescomposicioPartidaOliva dpo = (DescomposicioPartidaOliva) desc.next(); if(dpo.getDescomposicioPlantacio()!= null){
					     DescomposicioPlantacio descompPlanta = dpo.getDescomposicioPlantacio(); VarietatOliva varietat = descompPlanta.getVarietatOliva(); 
					     if(varietat != null [dos ampersands] varietat.getNom()!= null){ if(nom!= "" [dos ampersands] !nom.equalsIgnoreCase(varietat.getNom()))
					     { nom = "mezcla"; break; }else{ nom = varietat.getNom(); } } } } } return nom; } /** * Devuelve una fecha formateada */ 
					     public String getDataFormat() { SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); return sdf.format(this.data); }
				 </meta> 
	
	sustituir:	<property name="valid" type="java.lang.Boolean">
	por:		<property name="valid" type="java.lang.Boolean" insert="false">
	

Traza.hbm.xml
=============
	sustituir <set name="trazasForTtrCodtrafill" inverse="true" table="oli_tratra">
	por 	  <set name="trazasForTtrCodtrafill" inverse="false" table="oli_tratra">
	
	sustituir <set name="trazasForTtrCodtrapare" inverse="true" table="oli_tratra">
	por 	  <set name="trazasForTtrCodtrapare" inverse="false" table="oli_tratra">


Trasllat.hbm.xml
=======================
	sustituir <set name="diposits" inverse="true" table="oli_diptdi">
	por 	  <set name="diposits" inverse="false" table="oli_diptdi">

	sustituir:	<property name="valid" type="java.lang.Boolean">
	por:		<property name="valid" type="java.lang.Boolean" insert="false">

	sustituir:	<property name="dataAlta" type="date">
	por:		<property name="dataAlta" type="date" insert="false">
	

Informacio.hbm.xml
==================
	sustituir <set name="establiments" inverse="true" table="oli_estinf">
	por 	  <set name="establiments" inverse="false" table="oli_estinf">
	
	sustituir <set name="olivicultors" inverse="true" table="oli_oliinf">
	por 	  <set name="olivicultors" inverse="false" table="oli_oliinf">


Elaboracio.java
================
	sustituir:	public class Elaboracio implements java.io.Serializable {
	por:		public class Elaboracio implements Comparable {
	
	
Elaboracio.hbm.xml
================
	sustituir:	<property name="valid" type="java.lang.Boolean">
	por:		<property name="valid" type="java.lang.Boolean" insert="false">
	
	
Lot.java
================
	sustituir:	public class Lot implements java.io.Serializable {
	por:		public class Lot implements Comparable {


Lot.hbm.xml
==================
	sustituir:	<property name="valid" type="java.lang.Boolean">
	por:		<property name="valid" type="java.lang.Boolean" insert="false">
	
	sustituir 	<set name="varietatOlivas" inverse="true" table="oli_lotvov">
	por       	<set name="varietatOlivas" inverse="false" table="oli_lotvov">

	
Analitica.java
================
	sustituir:	public class Analitica implements java.io.Serializable {
	por:		public class Analitica implements Comparable {


Establiment.java
================
	sustituir:	public class Establiment implements java.io.Serializable {
	por:		public class Establiment implements Comparable {


EntradaDiposit.hbm.xml
==================
	sustituir:	<property name="valid" type="java.lang.Boolean">
	por:		<property name="valid" type="java.lang.Boolean" insert="false">

	
SortidaDiposit.hbm.xml
==================
	sustituir:	<property name="valid" type="java.lang.Boolean">
	por:		<property name="valid" type="java.lang.Boolean" insert="false">
	
	
EntradaLot.hbm.xml
==================
	sustituir:	<property name="valid" type="java.lang.Boolean">
	por:		<property name="valid" type="java.lang.Boolean" insert="false">
	
	
SortidaLot.hbm.xml
==================
	sustituir:	<property name="valid" type="java.lang.Boolean">
	por:		<property name="valid" type="java.lang.Boolean" insert="false">
	