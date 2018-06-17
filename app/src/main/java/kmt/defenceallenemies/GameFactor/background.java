package kmt.defenceallenemies.GameFactor;

import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Canvas;

import kmt.defenceallenemies.ControlManager.AppManager;
import kmt.defenceallenemies.ControlManager.GraphicObject;

/**
 * Created by Sonic on 2018-06-16.
 */

public class background extends GraphicObject {
    protected int xSpeed;
    public background(Bitmap bitmap) {
        super(bitmap);
        xSpeed = 5;

    }
    public void Render(Canvas canvas)
    {
        super.DrawResize(canvas);
    }
    public void update(long CurrentTime)
    {
        if(m_x<-AppManager.getInstance().getGameView().getWidth())
            m_x=AppManager.getInstance().getGameView().getWidth();
        m_x-=xSpeed;
    }
}
