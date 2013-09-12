package com.lysfit.mestahh.sqlbuilder;

public class BetweenCriteria extends Criteria {

	public BetweenCriteria(String column, int number1, int number2) {
		criteria = column + " BETWEEN " + number1 + " AND " + number2;
	}

	public BetweenCriteria(String column, String string, String string2) {
		criteria = column + " BETWEEN '" + string + "' AND '" + string2 + "'";
	}

}
