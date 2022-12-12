package ru.croc.task17.src;


import java.io.IOException;

public class Task17 {

    public static void main(String[] args) throws IOException {

        ru.croc.task17.src.TableJDBC tableJDBC = new ru.croc.task17.src.TableJDBC();
        tableJDBC.createTables();
        tableJDBC.filltable(args[0]);
    }
}
