package kmt.defenceallenemies.GameFactor;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import kmt.defenceallenemies.ControlManager.SpriteObject;

/**
 * Created by Sonic on 2018-06-16.
 */

public class Monster extends SpriteObject {

    int xSpeed;
    public int HP = 5;
    public Monster(Bitmap bitmap) {
        super(bitmap);
        xSpeed = -3;
        FPS = 3;
        SetCOLROWS(3,1);
        width = bitmap.getWidth()/BMP_COLUMNS;
        height = bitmap.getHeight()/BMP_ROWS;
    }
    public int getSize()
    {
        return (Bmp.getWidth()/BMP_COLUMNS);
    }
    public void Render(Canvas canvas)
    {
        super.onDraw(canvas);
        //canvas.drawRect(ColBox,drawstyle);
    }
    public void Animate(long CurrentTime)
    {
        super.update(CurrentTime);
        x += xSpeed;
    }
}
