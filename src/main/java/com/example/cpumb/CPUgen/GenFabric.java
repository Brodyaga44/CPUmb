package com.example.cpumb.CPUgen;

public class GenFabric {
    public Gen CPUtype(String tablename){
        System.out.println(tablename);
        if (tablename.equals("10")){return new Gen10();}
        if (tablename.equals("12")){return new Gen12();}
        if (tablename.equals("13")){return new Gen13();}
        else return null;
    }
}
