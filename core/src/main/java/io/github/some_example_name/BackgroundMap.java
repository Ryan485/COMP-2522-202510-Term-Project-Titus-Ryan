package io.github.some_example_name;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BackgroundMap extends Game {
    private String biome;
    private int wind;
    private SpriteBatch batch;
    private Texture texture;
    private BitmapFont font;

    public void setWind(int wind) {}
    public int getWind() {return 0;}
    public void getBiome() {}

    @Override
    public void create() {
        batch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("assets/mountainBright.png"));
        font = new BitmapFont();
    }

    public void render(){
        batch.begin();
        batch.draw(texture,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch.end();
    }
}
