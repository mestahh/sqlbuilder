package com.lysfit.mestahh.sqlbuilder;

import java.util.ArrayList;
import java.util.List;

public class SelectQuery {

	public static final String ASC = "ASC";
	public static final String DESC = "DESC";
	private final List<String> columns = new ArrayList<String>();
	private String tableName;
	private final List<Criteria> criterias = new ArrayList<Criteria>();
	private final List<String> orders = new ArrayList<String>();
	private final List<String> groups = new ArrayList<String>();

	public String getQuery() {

		String statement = "SELECT";
		if (!columns.isEmpty()) {
			statement = addStatement(statement, columns, ",");
		}

		statement += " FROM " + tableName;

		if (criterias != null && !criterias.isEmpty()) {
			statement += " WHERE";
			statement = addStatement(statement, criterias, " AND");
		}

		if (!orders.isEmpty()) {
			statement += " ORDER BY";
			statement = addStatement(statement, orders, ",");
		}

		if (!groups.isEmpty()) {
			statement += " GROUP BY";
			statement = addStatement(statement, groups, ",");
		}
		return statement;
	}

	private String addStatement(String statement, List<?> statements, String delimiter) {
		int i = 0;
		for (Object column : statements) {
			if (i > 0) {
				statement += delimiter;
			}
			statement += " " + column.toString();
			i++;
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

	public void addCriteria(Criteria matchCriteria) {
		criterias.add(matchCriteria);

	}

	public void addOrder(String column, String orderDirection) {
		orders.add(column + " " + orderDirection);
	}

	public void addGroup(String string) {
		groups.add(string);

	}
}
