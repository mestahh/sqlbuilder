package com.lysfit.mestahh.sqlbuilder;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.lysfit.mestahh.sqlbuilder.MatchCriteria;
import com.lysfit.mestahh.sqlbuilder.SelectQuery;

public class SelectQueryTest {

	private SelectQuery query;

	@Before
	public void setUp() {
		query = new SelectQuery();
		query.setTableName("table");
	}

	@Test
	public void initially_the_query_is_returning_everything_from_the_table() {
		assertThat(query.getQuery()).isEqualTo("SELECT FROM table");
	}

	@Test
	public void a_column_can_be_added_to_the_statement() {
		query.addColumn("myColumn");
		assertThat(query.getQuery()).isEqualTo("SELECT myColumn FROM table");
	}

	@Test
	public void multiple_column_names_can_be_selected() {
		query.addColumn("myColumn1").addColumn("myColumn2");
		assertThat(query.getQuery()).isEqualTo("SELECT myColumn1, myColumn2 FROM table");
	}

	@Test
	public void all_the_columns_can_be_selected_with_a_star() {
		query.addColumn("*");
		assertThat(query.getQuery()).isEqualTo("SELECT * FROM table");
	}

	@Test
	public void a_column_can_be_added_with_an_alias() {
		query.addColumn("name", "alias");
		assertThat(query.getQuery()).isEqualTo("SELECT name AS alias FROM table");
	}

	@Test
	public void a_where_statement_can_be_added() {
		query.addCriteria(new MatchCriteria("a", MatchCriteria.EQUALS, "b"));
		assertThat(query.getQuery()).isEqualTo("SELECT FROM table WHERE a = 'b'");
	}

	@Test
	public void two_criterias_can_be_added() {
		query.addCriteria(new MatchCriteria("a", MatchCriteria.EQUALS, "b"));
		query.addCriteria(new MatchCriteria("c", MatchCriteria.LESS, 5));
		assertThat(query.getQuery()).isEqualTo("SELECT FROM table WHERE a = 'b' AND c < 5");
	}

}
