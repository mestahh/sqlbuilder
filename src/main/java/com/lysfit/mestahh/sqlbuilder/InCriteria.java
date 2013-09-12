package com.lysfit.mestahh.sqlbuilder;

public class InCriteria extends Criteria {

	public InCriteria(String column, SelectQuery embedded) {
		criteria = column + " IN(" + embedded.getQuery() + ")";
	}
}
