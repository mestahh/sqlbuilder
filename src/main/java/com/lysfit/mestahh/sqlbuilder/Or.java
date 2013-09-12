package com.lysfit.mestahh.sqlbuilder;

public class Or extends Criteria {
	public Or(Criteria criteria1, Criteria criteria2) {
		criteria = criteria1.toString() + " OR " + criteria2.toString();
	}
}
