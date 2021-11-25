package org.elsys.ip.calculator;

import org.elsys.operations.OperationFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorResource {

    OperationFactory operationFactory = new OperationFactory();

    // http://localhost:8080/calculate/+/1,2
    @GetMapping("/calculate/{operation}/{arguments}")
    public Double calculate(@PathVariable String operation, @PathVariable String arguments) {
        return operationFactory.getOperation(operation + " " + String.join(" ", arguments.split(","))).perform();
    }

    // http://localhost:8080/calculate?operation=%2B&args=1,2
    @GetMapping("/calculate")
    public Double calculateQueryParams(@RequestParam(defaultValue = "+") String operation, @RequestParam(name = "args") String arguments) {
        return operationFactory.getOperation(operation + " " + String.join(" ", arguments.split(","))).perform();
    }

    // http://localhost:8080/calculate/+/1,2
    @PostMapping("/calculate")
    public ResponseEntity<?> calculate(@RequestBody Operation operation, @RequestHeader(value = "arguments-count", required = false) Integer argumentCount) {
        if (argumentCount == null) {
            argumentCount = operation.arguments().size();
        }
        try {
            double operationResult = operationFactory.getOperation(
                    operation.operation() + " " +
                            String.join(" ", operation.arguments().subList(0, argumentCount))).perform();
            return ResponseEntity
                    .ok()
                    .header("removed-arguments", String.valueOf(argumentCount != operation.arguments().size()))
                    .body(new Result(operationResult, null));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
