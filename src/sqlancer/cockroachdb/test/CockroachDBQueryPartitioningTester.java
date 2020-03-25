package sqlancer.cockroachdb.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sqlancer.TestOracle;
import sqlancer.cockroachdb.CockroachDBProvider.CockroachDBGlobalState;

public class CockroachDBQueryPartitioningTester implements TestOracle {

	private final TestOracle[] oracles;
	private int i;
	
	public CockroachDBQueryPartitioningTester(CockroachDBGlobalState state) {
		List<TestOracle> oracles = new ArrayList<>();
		oracles.add(new CockroachDBQueryPartitioningAggregateTester(state));
		oracles.add(new CockroachDBQueryPartitioningHavingTester(state));
		oracles.add(new CockroachDBQueryPartitioningWhereTester(state));
		this.oracles = oracles.toArray(new TestOracle[3]);
	}
	
	@Override
	public void check() throws SQLException {
		oracles[i].check();
		i = (i + 1) % oracles.length;
	}

}