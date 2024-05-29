package com.example.cpumb.Singleton;

import com.example.cpumb.PC.CPU.CPUinfo;
import com.example.cpumb.PC.MB.MBinfo;
import com.example.cpumb.PC.RAM.RAMinfo;


public class Singleton {
    private static Singleton instance;
    public CPUinfo CPUInfo;
    public MBinfo BoardInfo;
    public RAMinfo RAMInfo;
    public String RAMType;

    private Singleton() {
    }

    public static Singleton getInstance() {
        // Если экземпляр еще не создан, создаем его
        if (instance == null) {
            instance = new Singleton();
        }
        // Возвращаем существующий экземпляр
        return instance;
    }
}
