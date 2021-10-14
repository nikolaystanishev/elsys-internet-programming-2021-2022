package org.elsys.memory;

import java.util.HashMap;
import java.util.Map;

public class CalculatorMemory {
	private final Map<String, Double> memory;

	private static CalculatorMemory calculatorMemory = null;

	private CalculatorMemory() {
		memory = new HashMap<>();
	}

	public static CalculatorMemory getInstance() {
		if (calculatorMemory == null) {
			calculatorMemory = new CalculatorMemory();
		}

		return calculatorMemory;
	}

	public void putInMemory(String key, Double value) {
		memory.put(key, value);
	}

	public Double getFromMemory(String key) {
		return memory.get(key);
	}
}
