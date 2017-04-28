package com.gdimitris.boxcrush;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class BoxCrush extends Game implements InputProcessor {
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private BoxGrid boxGrid;
    private BoxFactory boxFactory;
    private World world;
    private Box2DDebugRenderer debugRenderer;

    @Override
	public void create () {
		batch = new SpriteBatch();
        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();
		camera = new OrthographicCamera(width,height);
		camera.setToOrtho(true,width,height);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        boxFactory = new BoxFactory();
		boxGrid = new BoxGrid(boxFactory);
//        boxGrid.createNewRowOfBoxes();
//        boxGrid.shiftBoxesByOneRow();
        Gdx.input.setInputProcessor(this);

        world = new World(new Vector2(0,-10), true);
        debugRenderer = new Box2DDebugRenderer();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();

		batch.begin();
        boxGrid.draw(batch);
		batch.end();
        debugRenderer.render(world,camera.combined);
        world.step(1/60f,6,2);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.SPACE){
            boxGrid.shiftBoxesByOneRow();
            boxGrid.createNewRowOfBoxes();
            camera.update();
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
