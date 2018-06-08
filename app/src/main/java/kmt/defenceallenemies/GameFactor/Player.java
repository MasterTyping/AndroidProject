package kmt.defenceallenemies.GameFactor;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;

import kmt.defenceallenemies.ControlManager.AppManager;
import kmt.defenceallenemies.ControlManager.GameView;
import kmt.defenceallenemies.ControlManager.SpriteObject;
import kmt.defenceallenemies.R;

/**
 * Created by Sonic on 2018-04-13.
 */

public class Player extends SpriteObject {


    private CollisionBox CollideBox;
    private int pBitmap;
    public Player(Bitmap bitmap) {
        super(bitmap);
        CollideBox = new CollisionBox();
        CollideBox.SetCollideBox(getX(),getY(),getSize(),getSize());
    }
    public void ChangeImg(int re)
    {

    }
    @Override
    public void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        CollideBox.DrawShowBox(canvas);
    }
    @Override
    public void update(long GameTime)
    {
       super.update(GameTime);
       CollideBox.SetCollideBox(getX(),getY(),getSize(),getSize());
    }
}
