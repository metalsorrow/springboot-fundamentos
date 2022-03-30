package com.fundamentos.springboot.fundamentos.bean;

public class MyBeanwithPropetiesImplementation implements MyBeanWithPropeties{
    private String name;
    private String apellido;

    public MyBeanwithPropetiesImplementation(String name, String apellido) {
        this.name = name;
        this.apellido = apellido;
    }

    @Override
    public String function() {
        return this.name + "-" + this.apellido;
    }
}
