package org.elsys.ip.calculatormvc.controllers;

import org.elsys.operations.OperationFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CalculatorController {

    OperationFactory factory = new OperationFactory();

    @GetMapping("/calculate")
    public String calculate(Model model) {
        model.addAttribute("operation", new Operation());
        return "calculator";
    }

    @PostMapping("/calculate")
    public String calculate(@ModelAttribute Operation operation, Model model) {
        org.elsys.operations.Operation op = factory.getOperation(operation.getOperator() + " " + operation.getArgs());

        model.addAttribute("operation", operation);
        model.addAttribute("result", op.perform());

        return "calculator";
    }
}
