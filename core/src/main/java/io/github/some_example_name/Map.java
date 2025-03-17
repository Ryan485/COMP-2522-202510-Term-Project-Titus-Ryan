package io.github.some_example_name;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Map {
    private String biome;
    private int wind;
    private SpriteBatch batch;

    public Map(String biome) {
        this.biome = biome;
    }

    public void setWind(int wind) {}
    public int getWind() {return 0;}
    public void getBiome() {}
    public void setBackgroundImage(String backgroundImage) {}
    public void render(){

    }
}
