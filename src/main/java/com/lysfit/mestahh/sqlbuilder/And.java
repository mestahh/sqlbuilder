package com.lysfit.mestahh.sqlbuilder;

public class And extends Criteria {
	public And(Criteria criteria1, Criteria criteria2) {
		criteria = criteria1.toString() + " AND " + criteria2.toString();
	}
}
