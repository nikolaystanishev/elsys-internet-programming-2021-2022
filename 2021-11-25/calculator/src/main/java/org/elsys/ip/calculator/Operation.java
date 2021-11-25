package org.elsys.ip.calculator;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

//public class Op {
//    @JsonProperty String operation;
//    @JsonProperty("args") List<String> arguments;
//
//    public Op(String operation, List<String> arguments) {
//        this.operation = operation;
//        this.arguments = arguments;
//    }
//
//    public String getOperation() {
//        return operation;
//    }
//
//    public void setOperation(String operation) {
//        this.operation = operation;
//    }
//
//    public List<String> getArguments() {
//        return arguments;
//    }
//
//    public void setArguments(List<String> arguments) {
//        this.arguments = arguments;
//    }
//}
public record Operation(@JsonProperty String operation, @JsonProperty("args") List<String> arguments) {
}

