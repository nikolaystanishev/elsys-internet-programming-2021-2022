package org.elsys.operations;

import java.util.List;

public class SubtractionOperation extends AbstractOperation {
	public SubtractionOperation(List<String> arguments) {
		super(arguments);
	}

	@Override
	public double perform() {
		if (arguments.size() != 2) {
			throw new RuntimeException("Invalid size of arguments.");
		}

		return arguments.get(0) - arguments.get(1);
	}
}
