package com.fundamentos.springboot.fundamentos.bean;

public class MyCalcultaroImplement implements MyCalculator{

    @Override
    public int addTwoNumber(int number1, int number2) {
        return number1 + number2;
    }

    @Override
    public int multiplyTwoNumber(int number1, int number2) {
        return number1 * number2    ;
    }

    @Override
    public int diferenceTwoNumber(int number1, int number2) {
        return number1 - number2;
    }
}
