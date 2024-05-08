package com.example.cpumb.Singleton;

public class Singleton {
    private static Singleton instance;
    public String CPUname;
    public String MBName;
    public String RAMName;
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
