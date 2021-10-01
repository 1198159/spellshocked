package com.spellshocked.game.world;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Tile extends TextureRegion {
    private TextureRegion[][] allTextures;
    protected TextureRegion[] currentTextures;
    protected Tile left, right, front, back;
    protected int xValue, yValue, zValue;

    public Tile(Texture textureMap, int x, int y, int z){
        allTextures = TextureRegion.split(textureMap, 16, 12);
        currentTextures = new TextureRegion[100];
        xValue = x;
        yValue = y;
        zValue = z;
    }

    public Tile draw(SpriteBatch batch){
        for(int i = zValue; i >= 0; i--){
            if(currentTextures[i] == null) break;
            batch.draw(currentTextures[i], xValue*16, (yValue+i)*12);
        }
        return this;
    }
    public Tile updateTextures(){
        int x=0,y=1;
        if(left.zValue < zValue) x+=1;
        if(right.zValue < zValue) x+=2;
        if(back.zValue < zValue) y = 0;
        currentTextures[zValue] = allTextures[y][x];
        for(int i = zValue-1; i >= front.zValue; i--){
            y=2;
            if(i == zValue-1) y+=1;
            if(i == front.zValue) y+=2;
            currentTextures[i] = allTextures[y][x];
        }
        return this;
    }


    public Tile setNeighbors(Tile l, Tile r, Tile b, Tile f){
        left = l;
        right = r;
        front = f;
        back = b;
        updateTextures();
        return this;
    }

    protected void incrementz(){
        zValue++;
    }
}
