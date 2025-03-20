package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Map {
    private String biome;
    private int wind;
    private SpriteBatch batch;
    private Texture texture;
    private BitmapFont font;

    public Map(String biome) {
        this.biome = biome;
    }

    public void setWind(int wind) {}
    public int getWind() {return 0;}
    public void getBiome() {}
    public void createBackgroundImage() {
        batch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("assets/forest.jpg"));
        font = new BitmapFont();
    }
    public void textRender() {
    }
    public void render(){
        batch.begin();
        textRender();
        batch.draw(texture,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch.end();
    }
}
