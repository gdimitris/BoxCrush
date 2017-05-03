package tests;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.MassData;
import com.gdimitris.boxcrush.Projectile;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ProjectileTests {

    private Projectile projectile;
    private Body mockBody;
    private Sprite mockSprite;

    @Before
    public void setUp(){
        initMocks(this);
        mockBody = mock(Body.class);
        mockSprite = mock(Sprite.class);
        projectile = new Projectile(mockBody, mockSprite);
    }

    @Test
    public void testProjectileShouldSetSizeThatIsDoubleTheRadius(){
        projectile.setRadius(12.0f);
        verify(mockSprite).setSize(24.0f,24.0f);
    }

    @Test
    public void testProjectileShouldApplyImpulseToBodyWhenLaunched(){
        when(mockBody.getMassData()).thenReturn(new MassData());

        projectile.launchWithVelocity(new Vector3(2.0f,2.0f,0));
        verify(mockBody).applyLinearImpulse(eq(2.0f),eq(2.0f),anyFloat(),anyFloat(),eq(true));
    }
}
