package com.spellshocked.game.world;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;

import java.util.Arrays;
import java.util.Random;

import static com.badlogic.gdx.math.MathUtils.clamp;

public class World {
    public static final int RD = 64;
    private Tile[][] tiles;
    protected int xValue, yValue;
    private Perlin noise = new Perlin();
    public World(int x, int y){
        tiles = new Tile[x+1][y+1];
        xValue = x;
        yValue = y;

        float[][] seed =  Perlin.GenerateWhiteNoise(x+1, y+1);
        float[][] seedE = Perlin.GenerateSmoothNoise( seed, 4);
        float[][] perlinNoise = Perlin.GeneratePerlinNoise(seedE, 6);

        for(int i = 0; i <= x; i++){
            for(int j = 0; j <= y; j++){
                tiles[i][j] = new Tile(new Texture("./images/blocks/grass.png"), i-x/2, j-y/2, (int) (perlinNoise[i][j]*5));
            }
        }

        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                tiles[i][j].setNeighbors(tiles[Math.max(0,i-1)][j], tiles[Math.min(x,i+1)][j],
                        tiles[i][Math.min(y,j+1)], tiles[i][Math.max(0,j-1)]);
            }
        }
    }

    private void addHill() {

        int r = MathUtils.random(2, 10);
        int x = MathUtils.random(r, xValue-r);
        int y = MathUtils.random(r, yValue-r);
        for (int i = x-r; i <= x+r; i++) {
            for (int j = y-r; j <= y+r; j++) {
                tiles[i][j].incrementz();
            }
        }
    }

    public void draw(SpriteBatch b, Vector3 v){
        int x = (int) v.x/16 + 32;
        int y = (int) v.y/12 + 32;
        for(int i = clamp(x-RD, 0, xValue); i <= clamp(x+RD, 0, xValue); i++){
            for(int j = clamp(y+RD, 0, yValue); j >= clamp(y-RD, 0, yValue); j--){
                tiles[i][j].draw(b);
            }
        }

    }
}
