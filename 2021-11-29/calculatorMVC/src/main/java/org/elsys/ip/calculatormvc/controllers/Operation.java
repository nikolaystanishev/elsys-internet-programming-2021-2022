package org.elsys.ip.calculatormvc.controllers;

public class Operation {

    private String operator;
    private String args;

    public Operation(String operator, String args) {
        this.operator = operator;
        this.args = args;
    }

    public Operation() {
        this("inv", "");
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args;
    }
}
