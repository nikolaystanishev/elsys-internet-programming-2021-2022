package org.elsys.operations;

import java.util.ArrayList;
import java.util.List;

public class SaveOperation extends AbstractOperation {
	List<String> elementsToBeSaved;

	SaveOperation(List<String> arguments) {
		super(arguments);
	}

	@Override
	public double perform() {
		if (elementsToBeSaved == null || arguments.size() != elementsToBeSaved.size()) {
			throw new RuntimeException("Invalid size of arguments.");
		}

		for (int i = 0; i < arguments.size(); i++) {
			calculatorMemory.putInMemory(elementsToBeSaved.get(i), arguments.get(i));
		}

		return 0;
	}

	@Override
	protected Double parseArgument(String argument) {
		try {
			return Double.parseDouble(argument);
		} catch (NumberFormatException e) {
			Double fromMemory = calculatorMemory.getFromMemory(argument);

			if (fromMemory == null) {
				if (elementsToBeSaved == null) {
					elementsToBeSaved = new ArrayList<>();
				}

				elementsToBeSaved.add(argument);
				return null;
			}
			return fromMemory;
		}
	}
}
