package com.thaer.backend.algorithm;

/**
 * Defines the identity and invocation contract shared by algorithm
 * implementations.
 *
 * <p>The input and output types are supplied by each implementation. This
 * contract is independent of transport, framework, and presentation concerns;
 * implementations must not depend on frontend or visualization components.
 *
 * @param <I> the input accepted by this algorithm
 * @param <O> the output produced by this algorithm
 */
public interface Algorithm<I, O> {

	/**
	 * Returns the stable identifier used to distinguish this algorithm.
	 *
	 * @return the algorithm identifier
	 */
	String id();

	/**
	 * Returns the human-readable name of this algorithm.
	 *
	 * @return the algorithm name
	 */
	String name();

	/**
	 * Executes this algorithm for the supplied input.
	 *
	 * @param input the input value for this execution
	 * @return the result produced for the input
	 */
	O execute(I input);
}
