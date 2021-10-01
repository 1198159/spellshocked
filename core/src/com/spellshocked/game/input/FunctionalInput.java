package com.spellshocked.game.input;

import com.badlogic.gdx.Gdx;

import java.util.function.BooleanSupplier;
import java.util.function.IntFunction;

@FunctionalInterface
public interface FunctionalInput {
    boolean input();

    default boolean isTrue(){
        return input();
    }

    default boolean isFalse(){
        return !input();
    }

    default FunctionalInput onTrue(Runnable r){
        return schedule(new ConditionalRunnable(r, this::isTrue));
    }
    default FunctionalInput onFalse(Runnable r){
        return schedule(new ConditionalRunnable(r, this::isFalse));
    }
    default FunctionalInput schedule(Runnable r){
        InputScheduler.getInstance().schedule(r);
        return this;
    }
    static FunctionalInput from(BooleanSupplier b){
        return b::getAsBoolean;
    }
    static FunctionalInput from(int i, IntFunction<Boolean> f){
        return ()->f.apply(i);
    }
    static FunctionalInput fromKeyPress(int i){
        return from(i, Gdx.input::isKeyPressed);
    }
}
