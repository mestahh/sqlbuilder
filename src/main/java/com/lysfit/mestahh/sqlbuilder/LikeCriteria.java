package com.lysfit.mestahh.sqlbuilder;

public class LikeCriteria extends Criteria {

	public LikeCriteria(String arg1, String likeThis) {
		criteria = arg1 + " LIKE '" + likeThis + "'";
	}

}
