package org.elsys.operations;

import java.util.List;

public class OperationFactory {
	public Operation getOperation(String operation) {
		List<String> operationArguments = List.of(operation.split(" "));
		if (operationArguments.isEmpty()) {
			throw new RuntimeException("Invalid number of arguments.");
		}

		List<String> arguments = operationArguments.subList(1, operationArguments.size());

		switch (operationArguments.get(0)) {
			case "+": return new AdditionOperation(arguments);
			case "-": return new SubtractionOperation(arguments);
			case "=": return new SaveOperation(arguments);
			case "exit": return new CloseOperation();
			default: throw new RuntimeException("Invalid operation.");
		}
	}
}
