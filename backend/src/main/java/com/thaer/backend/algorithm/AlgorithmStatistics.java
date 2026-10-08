package com.thaer.backend.algorithm;

/**
 * Holds operation counts for one algorithm execution.
 *
 * <p>This immutable value type records comparisons and swaps without measuring
 * or aggregating them. It is independent of transport, framework, and
 * presentation concerns.
 *
 * @param comparisonCount number of comparisons performed, which must not be negative
 * @param swapCount number of swaps performed, which must not be negative
 */
public record AlgorithmStatistics(long comparisonCount, long swapCount) {

	public AlgorithmStatistics {
		if (comparisonCount < 0) {
			throw new IllegalArgumentException("Comparison count must not be negative.");
		}
		if (swapCount < 0) {
			throw new IllegalArgumentException("Swap count must not be negative.");
		}
	}
}
