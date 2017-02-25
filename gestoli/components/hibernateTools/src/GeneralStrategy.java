


import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import org.hibernate.cfg.reveng.DelegatingReverseEngineeringStrategy;
import org.hibernate.cfg.reveng.ReverseEngineeringStrategy;
import org.hibernate.cfg.reveng.TableIdentifier;
import org.hibernate.mapping.ForeignKey;


/**
 * Conversor de nombres de tabla a java para hibernate tools
 * Elimina prefijos y sufijos en los nombres
 * @author agarcia
 *
 */
public class GeneralStrategy extends DelegatingReverseEngineeringStrategy  {

	protected java.util.HashMap tables = new java.util.HashMap();

	protected boolean modoPrefijoEnAtributos = false;

	public GeneralStrategy(ReverseEngineeringStrategy strategy) {
		super(strategy);
	}

	private TableData getTableData (String table) {
		if (this.tables.containsKey(table)) {
			return (TableData)this.tables.get(table);
		} else {
			return null;
		}
	}

//	@Override
	public String foreignKeyToCollectionName(String keyname,
			TableIdentifier fromTable, List fromColumns,
			TableIdentifier referencedTable, List referencedColumns,
			boolean uniqueReference) {
		fromTable = this.removePrefix(fromTable);
		referencedTable = this.removePrefix(referencedTable);
		return super.foreignKeyToCollectionName(keyname, fromTable, fromColumns,
				referencedTable, referencedColumns, uniqueReference);
	}

//	@Override
	public String foreignKeyToEntityName(String keyname,
			TableIdentifier fromTable, List fromColumnNames,
			TableIdentifier referencedTable, List referencedColumnNames,
			boolean uniqueReference) {
		fromTable = this.removePrefix(fromTable);
		referencedTable = this.removePrefix(referencedTable);
		return super.foreignKeyToEntityName(keyname, fromTable, fromColumnNames,
				referencedTable, referencedColumnNames, uniqueReference);
	}

//	@Override
	public String foreignKeyToManyToManyName(ForeignKey fromKey,
			TableIdentifier middleTable, ForeignKey toKey,
			boolean uniqueReference) {
		middleTable = this.removePrefix(middleTable);
		return super.foreignKeyToManyToManyName(fromKey, middleTable, toKey,
				uniqueReference);
	}

//	@Override
	public String tableToCompositeIdName(TableIdentifier identifier) {
		identifier = this.removePrefix(identifier);
		return super.tableToCompositeIdName(identifier);
	}

//	@Override
	public String tableToIdentifierPropertyName(TableIdentifier tableIdentifier) {
		tableIdentifier = this.removePrefix(tableIdentifier);
		return super.tableToIdentifierPropertyName(tableIdentifier);
	}

//	@Override
	public String tableToClassName(TableIdentifier tableIdentifier) {
		tableIdentifier = this.removePrefix(tableIdentifier);
		return super.tableToClassName(tableIdentifier);
	}



	/* Recorre la columna para eliminar el acronimo
	 * @see org.hibernate.cfg.reveng.DelegatingReverseEngineeringStrategy#columnToPropertyName(org.hibernate.cfg.reveng.TableIdentifier, java.lang.String)
	 */
//	@Override
	public String columnToPropertyName(TableIdentifier tableIdentifier, String column) {

		String name = tableIdentifier.getName().toLowerCase();
		TableData tableData = this.getTableData(name);

		if (tableData == null) {
			return super.columnToPropertyName(tableIdentifier, column);
		} else {

			if (this.modoPrefijoEnAtributos) {

				String[] exploded = column.split("_");
				String newColumnName = "";
				if (exploded[0].equals(tableData.getAcronimo())){
					newColumnName = column.substring( tableData.getAcronimo().length() + 1 );
				}else{
					newColumnName = column;
				}

				return super.columnToPropertyName(tableIdentifier, newColumnName);


			} else {

				/* TODO: con split probablemente se puede emular esta implemtacion mas sencilla:

				        if ($exp[count($exp)-1] == $acronimo){
				            $campo = substr($campoTabla, 0, -(1+strlen($acronimo)));
				        }else{
				            $campo = $campoTabla;
				        }

				 */

				Vector newColumnName = new Vector();
				StringTokenizer tok = new StringTokenizer(column, "_");
				int n=0;
				boolean cont = true;
				while (tok.hasMoreElements() && cont) {
					String substr = tok.nextToken();
					if ((n > 0) && substr.equals(tableData.getAcronimo())) {
						int quedan = tok.countTokens();
						if ( quedan == 1) {
							//comprobamos que no sea un tipo especial (_mime, _x, _y)
							String nextSubstr = tok.nextToken();
							if (!nextSubstr.equals("mime") && !nextSubstr.equals("x") && !nextSubstr.equals("y")) {
								//no era un tipo especial, añadimos el acronimo
								newColumnName.add(substr);
							}
							//añadimos el sufijo
							newColumnName.add(nextSubstr);
						} else if ( quedan != 0 ) {
							//no era el ultimo token, lo añadimos
							newColumnName.add(substr);
						}
					} else {
						//no era el acronimo, lo añadimos
						newColumnName.add(substr);
					}
					n++;
				}
				StringBuffer sbf = new StringBuffer();
				int i = 0;
				for (i = 0; i < (newColumnName.size()-1); i++) {
					sbf.append(newColumnName.get(i)).append("_");
				}
				sbf.append(newColumnName.get(i));
				return super.columnToPropertyName(tableIdentifier, sbf.toString());
			}
		}
	}

	private TableIdentifier removePrefix(TableIdentifier tableIdentifier) {
		String name = tableIdentifier.getName().toLowerCase();
		TableData tableData = this.getTableData(name);
		if (tableData != null) {
			tableIdentifier = new TableIdentifier(tableIdentifier.getSchema(), tableIdentifier.getCatalog(), tableData.getSingular());
		}
		return tableIdentifier;
	}

}


