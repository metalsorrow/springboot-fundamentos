package com.fundamentos.springboot.fundamentos.bean;

public class MyPrinterImplementation implements MyPrinter{
    private MyCalculator myCalculator;

    public MyPrinterImplementation(MyCalculator myCalculator) {
        this.myCalculator = myCalculator;
    }


    @Override
    public void printerInt() {
        int number1 = 10;
        int number2 = 5;

        System.out.println(
            this.myCalculator.addTwoNumber(number1, number2)+ " "+
            this.myCalculator.diferenceTwoNumber(number1, number2)+ " "+
            this.myCalculator.multiplyTwoNumber(number1, number2)+ " Printed!"
        );
    }
}
