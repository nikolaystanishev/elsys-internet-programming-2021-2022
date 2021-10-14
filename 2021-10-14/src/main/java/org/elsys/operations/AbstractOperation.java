package org.elsys.operations;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.elsys.memory.CalculatorMemory;

public abstract class AbstractOperation implements Operation {
	protected CalculatorMemory calculatorMemory = CalculatorMemory.getInstance();

	protected List<Double> arguments;

	AbstractOperation(List<String> arguments) {
		this.arguments = arguments.stream().map(this::parseArgument).filter(Objects::nonNull).collect(Collectors.toList());
	}

	protected Double parseArgument(String argument) {
		try {
			return Double.parseDouble(argument);
		} catch (NumberFormatException e) {
			return calculatorMemory.getFromMemory(argument);
		}
	}
}
