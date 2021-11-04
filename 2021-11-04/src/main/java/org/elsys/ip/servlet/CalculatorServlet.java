package org.elsys.ip.servlet;

import org.elsys.operations.OperationFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CalculatorServlet extends HttpServlet {
    private final OperationFactory factory = new OperationFactory();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String operation = req.getPathInfo();

        Stream<String> parsedOperation;
        if (operation == null) {
            parsedOperation = Arrays.stream(req.getQueryString().split("&")).map(operand -> operand.split("=")[1]);
        } else {
            parsedOperation = Arrays.stream(operation.split("/")).skip(1);
        }

        try {
            double result = factory.getOperation(parsedOperation.collect(Collectors.joining(" "))).perform();
            resp.setStatus(200);
            resp.getWriter().print(result);
        } catch (Exception e) {
            resp.setStatus(404);
            resp.getWriter().print(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().print(factory.getOperation(req.getReader().readLine()).perform());
    }
}
