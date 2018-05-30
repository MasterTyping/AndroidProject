package kmt.defenceallenemies.GameFactor;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by Sonic on 2018-05-09.
 */

public class CollisionBox {
    private Paint ShowBox;
    private Rect CollideBox;
    private boolean Collision = false;

    public CollisionBox()
    {
        ShowBox = new Paint();
        ShowBox.setColor(Color.RED);
        ShowBox.setStyle(Paint.Style.STROKE);
        CollideBox = new Rect();

    }
    public boolean Collision(Rect Other)
    {
        if(CollideBox.contains(Other)) {   return true;    }
        else {  return false;   }
    }
    public void SetCollideBox(int x ,int y,int xSize,int ySize)
    {
        CollideBox.left = x;
        CollideBox.right = x+xSize;
        CollideBox.top = y;
        CollideBox.bottom = y+ySize;
    }
    public Rect GetBox()
    {
        return CollideBox;
    }
    public void MoveRect(int x,int y)
    {
        CollideBox.left+=x;
        CollideBox.right+=x;
        CollideBox.top+=y;
        CollideBox.bottom+=y;
    }
    public void DrawShowBox(Canvas canvas)
    {
        canvas.drawRect(CollideBox,ShowBox);
    }
}
