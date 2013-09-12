package com.lysfit.mestahh.sqlbuilder;

public class MatchCriteria extends Criteria {

	public MatchCriteria(String arg1, String operator, String arg2) {
		criteria = arg1 + operator + "'" + arg2 + "'";
	}

	public MatchCriteria(String arg1, String operator, int numberArg) {
		criteria = arg1 + operator + numberArg;
	}

	public static final String EQUALS = " = ";
	public static final String LESS = " < ";
	public static final String GREATER = " > ";
	public static final String GREATER_OR_EQUAL = " >= ";
	public static final String LESS_OR_EQUAL = " <= ";

}
