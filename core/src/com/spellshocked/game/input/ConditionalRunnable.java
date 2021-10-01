package com.spellshocked.game.input;

import java.util.function.BooleanSupplier;

public class ConditionalRunnable implements Runnable {
    public BooleanSupplier condition;
    public Runnable runnable;
    public ConditionalRunnable(Runnable r, BooleanSupplier b){
        runnable = r;
        condition = b;
    }
    public ConditionalRunnable(Runnable r){
        this(r, ()->true);
    }

    @Override
    public void run() {
        if(condition.getAsBoolean()) runnable.run();
    }
}
