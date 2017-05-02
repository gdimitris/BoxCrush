package com.gdimitris.boxcrush;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import static com.gdimitris.boxcrush.Constants.PIXELS_PER_METER;
import static com.gdimitris.boxcrush.EntityCategoryMask.*;

public class BoxCrush extends Game implements InputProcessor {
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private BoxGrid boxGrid;
    private ProjectileLauncher projectileLauncher;
    private World world;
    private Box2DDebugRenderer debugRenderer;

    @Override
	public void create () {
		batch = new SpriteBatch();
        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();
		camera = new OrthographicCamera(width,height);
		camera.setToOrtho(true,width,height);
        world = new World(new Vector2(0,0.0f), true);

        debugRenderer = new Box2DDebugRenderer();

        BoxFactory boxFactory = new BoxFactory(world);
        ProjectileFactory projectileFactory = new ProjectileFactory(world);
        createBoundaries(world,width,height);
        projectileLauncher = new ProjectileLauncher(projectileFactory);
        projectileLauncher.setLaunchPosition(new Vector3(240,690,0));
        projectileLauncher.increaseProjectilesByOne();
		boxGrid = new BoxGrid(boxFactory,width);
        boxGrid.createNewRowOfBoxes();
        boxGrid.shiftBoxesByOneRow();
        Gdx.input.setInputProcessor(this);

        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
            }

            private boolean isBoxCollision(Fixture fixtureA, Fixture fixtureB) {
                return fixtureA.getFilterData().categoryBits == BOX.getValue() || fixtureB.getFilterData().categoryBits == BOX.getValue();
            }

            private boolean isProjectileCollision(Fixture fixtureA, Fixture fixtureB) {
                return fixtureA.getFilterData().categoryBits == PROJECTILE.getValue() || fixtureB.getFilterData().categoryBits == PROJECTILE.getValue();
            }

            @Override
            public void endContact(Contact contact) {
                Fixture fixtureA = contact.getFixtureA();
                Fixture fixtureB = contact.getFixtureB();

                if (isProjectileCollision(fixtureA, fixtureB) && isBoxCollision(fixtureA, fixtureB)){
                    System.out.println("Box collision");
                }
            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });
    }

	@Override
	public void render () {
		update(Gdx.graphics.getDeltaTime());

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
        boxGrid.draw(batch);
        projectileLauncher.draw(batch);
		batch.end();
        debugRenderer.render(world,camera.combined.scl(PIXELS_PER_METER));
	}

	public void update(float delta){
		world.step(1/60f,6,2);
		camera.update();
        projectileLauncher.update(delta);
		batch.setProjectionMatrix(camera.combined);
	}

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(true,width,height);
    }

    @Override
	public void dispose () {
		batch.dispose();
        world.dispose();
        debugRenderer.dispose();
	}

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.SPACE){
            boxGrid.createNewRowOfBoxes();
            boxGrid.shiftBoxesByOneRow();
            camera.update();
        }

        if(keycode == Input.Keys.L){
            projectileLauncher.launchProjectiles();
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

    private void createBoundaries(World world,int width, int height){
        PolygonShape shape = ShapeFactory.createBoxShape(1, height);

        Body body = world.createBody(BodyFactory.createStaticBody(3,height/2));
        body.createFixture(shape,1.0f);

        body = world.createBody(BodyFactory.createStaticBody(width-3,height/2));
        body.createFixture(shape,1.0f);

        shape = ShapeFactory.createBoxShape(width,1);
        body = world.createBody(BodyFactory.createStaticBody(width/2,3));
        body.createFixture(shape,1.0f);

        body = world.createBody(BodyFactory.createStaticBody(width/2,height-3));
        body.createFixture(shape,1.0f);
    }
}
