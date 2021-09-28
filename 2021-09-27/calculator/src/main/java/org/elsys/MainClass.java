package org.elsys;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.elsys.operations.Operation;
import org.elsys.operations.OperationFactory;

public class MainClass {
	public static void main(String[] args) throws IOException {
		OperationFactory operationFactory = new OperationFactory();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			System.out.print("Calculate: ");
			String operationArguments = reader.readLine();

			try {
				Operation operation = operationFactory.getOperation(operationArguments);
				System.out.println(operation.perform());
			} catch (RuntimeException e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
	}
}
