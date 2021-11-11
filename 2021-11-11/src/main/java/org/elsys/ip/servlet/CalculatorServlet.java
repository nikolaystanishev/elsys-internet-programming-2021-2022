package org.elsys.ip.servlet;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
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
    private final Gson gson = new Gson();
    private final XmlMapper xmlMapper = new XmlMapper();

    public CalculatorServlet() {
        xmlMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }

    class Result {
        @SerializedName("Operation Result")
        @JsonProperty("Operation Result")
        private double result;

        public Result(double result) {
            this.result = result;
        }
    }

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
            if ("application/json".equals(req.getHeader("Content-Type"))) {
                Result resultObject = new Result(result);
                resp.getWriter().print(gson.toJson(resultObject));
            } else if ("application/xml".equals(req.getHeader("Content-Type"))) {
                Result resultObject = new Result(result);
                resp.getWriter().print(xmlMapper.writeValueAsString(resultObject));
            } else {
                resp.getWriter().print(result);
            }
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
