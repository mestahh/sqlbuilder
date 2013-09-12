package com.lysfit.mestahh.sqlbuilder;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

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

	@Test
	public void an_embedded_from_statement_can_be_added() {
		SelectQuery embedded = new SelectQuery();
		embedded.addColumn("*");
		embedded.setTableName("other");
		query.addColumn("column");
		query.setTableName("table");
		query.addCriteria(new MatchCriteria("status", MatchCriteria.EQUALS, "ok"));

		query.addCriteria(new InCriteria("value", embedded));

		assertThat(query.getQuery()).isEqualTo(
				"SELECT column FROM table WHERE status = 'ok' AND value IN(SELECT * FROM other)");
	}

	@Test
	public void it_is_possible_to_add_a_greater_criteria() {
		query.addCriteria(new MatchCriteria("a", MatchCriteria.GREATER, 6));
		assertThat(query.getQuery()).isEqualTo("SELECT FROM table WHERE a > 6");
	}

	@Test
	public void it_is_possible_to_add_a_greater_or_equal_criteria() {
		query.addCriteria(new MatchCriteria("a", MatchCriteria.GREATER_OR_EQUAL, 6));
		assertThat(query.getQuery()).isEqualTo("SELECT FROM table WHERE a >= 6");
	}

	@Test
	public void it_is_possible_to_add_a_less_or_equal_criteria() {
		query.addCriteria(new MatchCriteria("a", MatchCriteria.LESS_OR_EQUAL, 6));
		assertThat(query.getQuery()).isEqualTo("SELECT FROM table WHERE a <= 6");
	}

	@Test
	public void it_is_possible_to_add_a_like_criteria() {
		query.addCriteria(new LikeCriteria("a", "b%d"));
		assertThat(query.getQuery()).isEqualTo("SELECT FROM table WHERE a LIKE 'b%d'");
	}

	@Test
	public void it_can_add_order_statements() {
		query.addOrder("column", SelectQuery.ASC);
		assertThat(query.getQuery()).isEqualTo("SELECT FROM table ORDER BY column ASC");
	}

	@Test
	public void more_orders_can_be_added() {
		query.addOrder("column", SelectQuery.ASC);
		query.addOrder("column2", SelectQuery.DESC);
		assertThat(query.getQuery()).isEqualTo("SELECT FROM table ORDER BY column ASC, column2 DESC");
	}

	@Test
	public void a_group_statement_can_be_added() {
		query.addGroup("column");
		assertThat(query.getQuery()).isEqualTo("SELECT FROM table GROUP BY column");
	}

	@Test
	public void more_groups_can_be_added() {
		query.addGroup("column");
		query.addGroup("column2");
		assertThat(query.getQuery()).isEqualTo("SELECT FROM table GROUP BY column, column2");
	}

	@Test
	public void between_criteria_can_be_added() {
		query.addCriteria(new BetweenCriteria("A", 1, 2));
		assertThat(query.getQuery()).isEqualTo("SELECT FROM table WHERE A BETWEEN 1 AND 2");
	}

	@Test
	public void between_criteria_can_be_added_with_strings() {
		query.addCriteria(new BetweenCriteria("A", "2012-12-12", "2012-12-23"));
		assertThat(query.getQuery()).isEqualTo("SELECT FROM table WHERE A BETWEEN '2012-12-12' AND '2012-12-23'");
	}

}
