package com.thaer.backend.algorithm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AlgorithmContractTests {

	@Test
	void testImplementationProvidesIdentityAndAcceptsItsDeclaredInput() {
		Algorithm<String, String> algorithm = new PrefixAlgorithm();

		assertEquals("test-prefix", algorithm.id());
		assertEquals("Test Prefix", algorithm.name());
		assertEquals("result: sample", algorithm.execute("sample"));
	}

	private static final class PrefixAlgorithm implements Algorithm<String, String> {

		@Override
		public String id() {
			return "test-prefix";
		}

		@Override
		public String name() {
			return "Test Prefix";
		}

		@Override
		public String execute(String input) {
			return "result: " + input;
		}
	}
}
