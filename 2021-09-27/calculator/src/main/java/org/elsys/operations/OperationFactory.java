package org.elsys.operations;

import java.util.List;

public class OperationFactory {
	public Operation getOperation(String operation) {
		List<String> operationArguments = List.of(operation.split(" "));
		if (operationArguments.isEmpty()) {
			throw new RuntimeException("Invalid number of arguments.");
		}

		List<String> arguments = operationArguments.subList(1, operationArguments.size());

		return switch (operationArguments.get(0)) {
		case "+" -> new AdditionOperation(arguments);
		case "-" -> new SubtractionOperation(arguments);
		case "=" -> new SaveOperation(arguments);
		case "exit" -> new CloseOperation();
		default -> throw new RuntimeException("Invalid operation.");
		};
	}
}
