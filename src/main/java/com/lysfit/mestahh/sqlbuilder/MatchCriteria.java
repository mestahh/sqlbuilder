package com.lysfit.mestahh.sqlbuilder;

public class MatchCriteria {

	private String criteria = "";

	public MatchCriteria(String arg1, String operator, String arg2) {
		criteria = arg1 + operator + "'" + arg2 + "'";
	}

	public MatchCriteria(String arg1, String operator, int numberArg) {
		criteria = arg1 + operator + numberArg;
	}

	public static final String EQUALS = " = ";
	public static final String LESS = " < ";

	public boolean isEmpty() {
		return criteria.isEmpty();
	}

	@Override
	public String toString() {
		return criteria;
	}

}
