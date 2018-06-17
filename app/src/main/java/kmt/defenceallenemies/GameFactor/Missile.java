package kmt.defenceallenemies.GameFactor;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import kmt.defenceallenemies.ControlManager.AppManager;
import kmt.defenceallenemies.ControlManager.GraphicObject;

/**
 * Created by Sonic on 2018-06-17.
 */

public class Missile extends GraphicObject {
    protected int xSpeed;
    public int AttackDamage;
    public Missile(Bitmap bitmap) {
        super(bitmap);
        xSpeed = 10;
        AttackDamage = 1;
    }
    public void Render(Canvas canvas)
    {
        super.DrawResize(canvas);
    }
    public void update(long CurrentTime)
    {

        m_x+=xSpeed;
    }
}

