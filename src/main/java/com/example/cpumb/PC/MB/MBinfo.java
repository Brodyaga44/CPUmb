package com.example.cpumb.PC.MB;

public class MBinfo {
    public String Name, Socket;
    public int DDR;

    public MBinfo(String name, String socket, String DDR) {
        Name = name;
        Socket = socket;
        this.DDR = Integer.parseInt(DDR);
    }
}
