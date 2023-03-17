package com.example.demo.bean;

public class MyBeanWithPropertiesImplements implements MyBeanWithProperties{
    private String name;
    private String apellido;

    public MyBeanWithPropertiesImplements(String name, String apellido) {
        this.name = name;
        this.apellido = apellido;
    }

    @Override
    public String function() {
        return name+"-"+apellido;
    }
}
