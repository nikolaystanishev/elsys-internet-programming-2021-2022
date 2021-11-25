package org.elsys.ip.calculator;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record Result(Double result, String alwaysNull) {
}
