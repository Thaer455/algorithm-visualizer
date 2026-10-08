package com.thaer.backend.algorithm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Modifier;

import org.junit.jupiter.api.Test;

class AlgorithmStatisticsTests {

	@Test
	void storesComparisonAndSwapCounts() {
		AlgorithmStatistics statistics = new AlgorithmStatistics(12, 5);

		assertEquals(12, statistics.comparisonCount());
		assertEquals(5, statistics.swapCount());
	}

	@Test
	void permitsZeroCounts() {
		AlgorithmStatistics statistics = new AlgorithmStatistics(0, 0);

		assertEquals(0, statistics.comparisonCount());
		assertEquals(0, statistics.swapCount());
	}

	@Test
	void isImmutable() {
		assertTrue(Modifier.isFinal(AlgorithmStatistics.class.getModifiers()));
		assertTrue(Modifier.isFinal(getFieldModifiers("comparisonCount")));
		assertTrue(Modifier.isFinal(getFieldModifiers("swapCount")));
	}

	@Test
	void rejectsNegativeComparisonCount() {
		assertThrows(IllegalArgumentException.class,
				() -> new AlgorithmStatistics(-1, 0));
	}

	@Test
	void rejectsNegativeSwapCount() {
		assertThrows(IllegalArgumentException.class,
				() -> new AlgorithmStatistics(0, -1));
	}

	private int getFieldModifiers(String fieldName) {
		try {
			return AlgorithmStatistics.class.getDeclaredField(fieldName).getModifiers();
		} catch (NoSuchFieldException exception) {
			throw new AssertionError("Expected record field " + fieldName, exception);
		}
	}
}
