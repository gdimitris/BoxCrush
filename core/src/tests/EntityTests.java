package tests;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.gdimitris.boxcrush.Box;
import com.gdimitris.boxcrush.Constants;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class EntityTests {

    private Box entity;
    private Body mockBody;
    private Sprite mockSprite;

    @Before
    public void setUp(){
        initMocks(this);
        mockBody = mock(Body.class);
        mockSprite = mock(Sprite.class);
        entity = new Box(mockBody,mockSprite);
    }

    @Test
    public void testEntitiesShouldFlipSpritesOnYaxis(){
        verify(mockSprite).flip(false,true);
    }

    @Test
    public void testEntitiesShouldDrawSprites(){
        SpriteBatch batch = mock(SpriteBatch.class);
        entity.draw(batch);

        verify(mockSprite).draw(batch);
    }

    @Test
    public void testEntitiesShouldPositionSpritesBasedOnScaledBodyPosition(){
        Vector2 position = new Vector2(1.0f,1.0f);
        when(mockBody.getPosition()).thenReturn(position);
        entity.update(1.0f);

        float expectedX = position.x * Constants.PIXELS_PER_METER;
        float expectedY = position.y * Constants.PIXELS_PER_METER;
        verify(mockSprite).setPosition(expectedX,expectedY);
    }

    @Test
    public void testEntitiesSetSizeOfSprite(){
        entity.setSize(200.0f,200.0f);

        verify(mockSprite).setSize(200.0f,200.0f);
    }


    @Test
    public void testSettingEntityPositionChangesSpriteAndBodyPosition(){
        float spriteWidth = 10.0f;
        float spriteHeight = 9.0f;
        when(mockSprite.getWidth()).thenReturn(spriteWidth);
        when(mockSprite.getHeight()).thenReturn(spriteHeight);
        Vector3 gamePosition = new Vector3(100,100,0);

        entity.setEntityPosition(gamePosition);

        float expectedScaledWidth = (gamePosition.x + spriteWidth/2)/Constants.PIXELS_PER_METER;
        float expectedScaledHeight = (gamePosition.y + spriteHeight/2)/Constants.PIXELS_PER_METER;
        verify(mockSprite).setPosition(gamePosition.x,gamePosition.y);
        verify(mockBody).setTransform(eq(new Vector2(expectedScaledWidth,expectedScaledHeight)),anyFloat());
    }

}
