package org.elsys;

import lombok.val;

public class MainClass {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        val a = 5;
        var b = 10;
        b = 20;

        Car car = new Car();
        car.setColor("red");
        System.out.println(car);
    }
}
