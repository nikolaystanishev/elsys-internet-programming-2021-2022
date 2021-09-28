package org.elsys.operations;

public class CloseOperation implements Operation {
	@Override
	public double perform() {
		System.exit(0);
		return 0;
	}
}
