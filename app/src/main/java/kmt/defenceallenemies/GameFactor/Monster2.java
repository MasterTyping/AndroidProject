package kmt.defenceallenemies.GameFactor;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Sonic on 2018-06-17.
 */

public class Monster2 extends Monster {

    public Monster2(Bitmap bitmap) {
        super(bitmap);
        xSpeed = -10;
        HP =1;
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
    }
}
