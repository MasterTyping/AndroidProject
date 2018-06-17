package kmt.defenceallenemies.GameFactor;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.concurrent.CopyOnWriteArrayList;

import kmt.defenceallenemies.ControlManager.AppManager;
import kmt.defenceallenemies.ControlManager.GraphicObject;
import kmt.defenceallenemies.ControlManager.SpriteObject;
import kmt.defenceallenemies.R;

/**
 * Created by Sonic on 2018-06-16.
 */

public class Player extends SpriteObject {

    protected Rect ColBox;
    //protected Paint drawstyle;
    //protected CopyOnWriteArrayList<GraphicObject>missle;
    public Player(Bitmap bmp) {
        super(bmp);
        //missle = new CopyOnWriteArrayList<GraphicObject>();
        //drawstyle = new Paint();
        //drawstyle.setColor(Color.BLUE);
        //drawstyle.setStyle(Paint.Style.STROKE);

        FPS= 3;

    }
    public void Render(Canvas canvas)
    {
        super.onDraw(canvas);
        //canvas.drawRect(ColBox,drawstyle);
    }
    public void Animate(long CurrentTime)
    {
        super.update(CurrentTime);
        //x += 5;
    }

}
