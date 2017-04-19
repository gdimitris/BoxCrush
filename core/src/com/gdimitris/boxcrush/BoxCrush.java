package com.gdimitris.boxcrush;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BoxCrush extends Game {
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private BoxGrid boxGrid;
	private Sprite sprite;

	@Override
	public void create () {
		batch = new SpriteBatch();
        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();
		camera = new OrthographicCamera(width,height);
		camera.setToOrtho(true,width,height);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
		boxGrid = new BoxGrid(width,height);
        boxGrid.addBoxAtPosition(new Box(),1,1);
		sprite = new Sprite(new Texture("alienBlue_square.png"));
        sprite.flip(false,true);
        sprite.setPosition(100,20);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();

		batch.begin();
        boxGrid.draw(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
