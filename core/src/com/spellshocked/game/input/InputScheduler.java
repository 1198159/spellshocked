package com.spellshocked.game.input;

import java.util.*;
import java.util.function.BooleanSupplier;

public class InputScheduler {
    private Runnable[] inputs;
    private int index;
    public static final int RUNNABLE_CAPACITY = 1024;
    private static InputScheduler instance;
    public static InputScheduler getInstance(){
        if(instance == null) instance = new InputScheduler(RUNNABLE_CAPACITY);
        return instance;
    }
    private InputScheduler(int capacity){
        inputs = new Runnable[capacity];
        index = 0;
    }
    public InputScheduler schedule(Runnable... runnables){
        for(Runnable r : runnables) inputs[index++] = r;
        return this;
    }


    public void run(){
        for(Runnable r : inputs) {
            if(r==null) break;
            r.run();
        }
    }
}
