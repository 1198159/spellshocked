package com.spellshocked.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class myPlayer extends Sprite{
    enum playerDirection {IDLE, LEFT, RIGHT, UP, DOWN}
	private playerDirection currentDirection;

    private int coordinate_limit_X_min = 0;
    private int coordinate_limit_X_max = 0;
    private int coordinate_limit_Y_min = 0;
    private int coordinate_limit_Y_max = 0;

    private int coordinate_X = 0;
    private int coordinate_Y = 0;
    

    public String toString(){
        return "Hi";
    }

    /**
     * @return nothing, just for test is this class being imported properly
     */
    public static String hello(){
        return "Hello World! from com.spellshocked.game.myPlayer";
    }

    /**
     * @param X coordinate lower limit
     */
    public void set_X_limit_min(int limit){
        coordinate_limit_X_min = limit;
    }
    /**
     * @param X coordinate upper limit
     */
    public void set_X_limit_max(int limit){
        coordinate_limit_X_max = limit;
    }
    /**
     * @param Y coordinate lower limit
     */
    public void set_Y_limit_min(int limit){
        coordinate_limit_Y_min = limit;
    }
    /**
     * @param Y coordinate upper limit
     */
    public void set_Y_limit_max(int limit){
        coordinate_limit_Y_max = limit;
    }
    /**
     * 
     * @param X_min coordinate lower limit
     * @param X_max coordinate upper limit
     * @param Y_min coordinate lower limit
     * @param Y_max coordinate upper limit
     */
    public void set_XY_limit(int X_min, int X_max, int Y_min, int Y_max){
        set_X_limit_min(X_min);
        set_X_limit_max(X_max);
        set_Y_limit_min(Y_min);
        set_Y_limit_max(Y_max);
    }

    /**
     * @return the X coordinate
     */
    public int get_X(){
        return coordinate_X;
    }
    /**
     * @return the Y coordinate
     */
    public int get_Y(){
        return coordinate_Y;
    }

    public boolean move_left(){
        if (coordinate_X > coordinate_limit_X_min){
            coordinate_X += -10;
        }
        else{
            return false;
        }
        return true;
    }
    public boolean move_right(){
        if (coordinate_X < coordinate_limit_X_max){
            coordinate_X += 10;
        }
        else{
            return false;
        }
        return true;
    } 
    public boolean move_up(){
        if (coordinate_Y > coordinate_limit_Y_min){
            coordinate_Y += 10;
        }
        else{
            return false;
        }
        return true;
    }
    public boolean move_down(){
        if (coordinate_Y < coordinate_limit_Y_max){
            coordinate_Y += 10;
        }
        else{
            return false;
        }
        return true;
    }

    public playerDirection get_direction(){
        return currentDirection;
    }
    /**
     * 
     * @param temp_direction
     */
    public void set_direction(playerDirection temp_direction){
        currentDirection = temp_direction;
    }
    public void set_direction_left(){
        set_direction(playerDirection.LEFT);
    }
    public void set_direction_right(){
        set_direction(playerDirection.RIGHT);
    }
    public void set_direction_up(){
        set_direction(playerDirection.UP);
    }
    public void set_direction_down(){
        set_direction(playerDirection.DOWN);
    }
    
}
