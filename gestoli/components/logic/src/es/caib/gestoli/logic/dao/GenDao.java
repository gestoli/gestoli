package es.caib.gestoli.logic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * Object for update IDs
 * @see es.caib.gestoli.logic.model.Arxiu
 * @see es.caib.gestoli.logic.model.Color
 * @see es.caib.gestoli.logic.model.Material
 * @see es.caib.gestoli.logic.model.TipusEnvas
 * @author Oriol Barnés
 */
public class GenDao {
	private DataSource ds;



	/**
	 * Recupera el data source
	 * @throws Exception
	 */
	public GenDao() throws Exception {
		Context ctx = new InitialContext();
		ds = (DataSource)ctx.lookup("java:/GestOliDS");
	}


	/**
	 * Realiza un update del codigo
	 * @param oldId
	 * @param newId
	 * @throws Exception
	 */
	public void updateArxiuId(Long oldId, Long newId) throws Exception {
		Connection con = ds.getConnection();
		PreparedStatement pst = con.prepareStatement(
				"UPDATE OLI_ARXIU SET ARX_CODI = ? WHERE ARX_CODI = ?"
		);
		pst.setLong(1, newId.longValue()); 
		pst.setLong(2, oldId.longValue()); 
		pst.executeUpdate();
		pst.close();
		con.close();
	}


	/**
	 * Realiza un update del codigo
	 * @param oldId
	 * @param newId
	 * @throws Exception
	 */
	public void updateColorId(Integer oldId, Integer newId) throws Exception {
		Connection con = ds.getConnection();
		PreparedStatement pst = con.prepareStatement(
				"UPDATE OLI_COLOR SET COL_CODI = ? WHERE COL_CODI = ?"
		);
		pst.setInt(1, newId.intValue()); 
		pst.setInt(2, oldId.intValue()); 
		pst.executeUpdate();
		pst.close();
		con.close();
	}


	/**
	 * Realiza un update del codigo
	 * @param oldId
	 * @param newId
	 * @throws Exception
	 */
	public void updateMaterialTipusEnvasId(Integer oldId, Integer newId) throws Exception {
		Connection con = ds.getConnection();
		PreparedStatement pst = con.prepareStatement(
				"UPDATE OLI_MATERIAL_TIPUS_ENVAS SET MEN_CODI = ? WHERE MEN_CODI = ?"
		);
		pst.setInt(1, newId.intValue()); 
		pst.setInt(2, oldId.intValue()); 
		pst.executeUpdate();
		pst.close();
		con.close();
	}


	/**
	 * Realiza un update del codigo
	 * @param oldId
	 * @param newId
	 * @throws Exception
	 */
	public void updateMaterialDipositId(Integer oldId, Integer newId) throws Exception {
		Connection con = ds.getConnection();
		PreparedStatement pst = con.prepareStatement(
				"UPDATE OLI_MATERIAL_DIPOSIT SET MDI_CODI = ? WHERE MDI_CODI = ?"
		);
		pst.setInt(1, newId.intValue()); 
		pst.setInt(2, oldId.intValue()); 
		pst.executeUpdate();
		pst.close();
		con.close();
	}


	/**
	 * Realiza un update del codigo
	 * @param oldId
	 * @param newId
	 * @throws Exception
	 */
	public void updateVarietatOlivaId(Integer oldId, Integer newId) throws Exception {
		Connection con = ds.getConnection();
		PreparedStatement pst = con.prepareStatement(
				"UPDATE OLI_VARIETAT_OLIVA SET VOV_CODI = ? WHERE VOV_CODI = ?"
		);
		pst.setInt(1, newId.intValue()); 
		pst.setInt(2, oldId.intValue()); 
		pst.executeUpdate();
		pst.close();
		con.close();
	}


}