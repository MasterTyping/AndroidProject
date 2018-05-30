package kmt.defenceallenemies.GameFactor;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import kmt.defenceallenemies.ControlManager.AppManager;
import kmt.defenceallenemies.ControlManager.SpriteObject;
import kmt.defenceallenemies.R;

/**
 * Created by Sonic on 2018-05-24.
 */

public class Monster extends SpriteObject {

    private CollisionBox cbox;
    private int xSpeed;
    public boolean show = false;
    public int HP = 3;
    public Monster() {

        super(AppManager.getInstance().getBitmap(R.drawable.monsters));
        cbox = new CollisionBox();
        xSpeed = -3;
        x= getX();
        y= getY();
        show = true;
    }
    @Override
    public void onDraw(Canvas canvas)
    {
        if(show){
            super.onDraw(canvas);
            cbox.DrawShowBox(canvas);
        }
    }
    public CollisionBox GetCbox()
    {
        return cbox;
    }
    @Override
    public void update(long GameTime)
    {
        super.update(GameTime);
        if(show)
        {
            x = x+xSpeed;
            SetPosSprite(x,y);
            cbox.SetCollideBox(getX(),getY(),getSize(),getSize());
        }
    }
}
