package com.lysfit.mestahh.sqlbuilder;

import java.util.ArrayList;
import java.util.List;

public class SelectQuery {

	private final List<String> columns = new ArrayList<String>();
	private String tableName;
	private final List<MatchCriteria> criterias = new ArrayList<MatchCriteria>();

	public String getQuery() {
		String statement = "SELECT";
		if (!columns.isEmpty()) {
			int i = 0;
			for (String column : columns) {
				if (i > 0) {
					statement += ",";
				}
				statement += " " + column;
				i++;
			}
		}
		statement += " FROM " + tableName;

		if (criterias != null && !criterias.isEmpty()) {
			statement += " WHERE";
			int i = 0;
			for (MatchCriteria criteria : criterias) {
				if (i > 0) {
					statement += " AND";
				}
				statement += " " + criteria.toString();
				i++;
			}
		}
		return statement;
	}

	public SelectQuery addColumn(String columnName) {
		columns.add(columnName);
		return this;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public void addColumn(String string, String string2) {
		columns.add(string + " AS " + string2);
	}

	public void addCriteria(MatchCriteria matchCriteria) {
		criterias.add(matchCriteria);

	}

}
