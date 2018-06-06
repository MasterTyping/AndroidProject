package kmt.defenceallenemies.GameFactor;

import android.graphics.Canvas;
import android.graphics.Rect;

import kmt.defenceallenemies.ControlManager.AppManager;
import kmt.defenceallenemies.ControlManager.GraphicObject;
import kmt.defenceallenemies.R;

/**
 * Created by Sonic on 2018-05-15.
 */

public class button extends GraphicObject{
    private CollisionBox cBox;
    private int xSize,ySize;
    public button(int r)
    {
        super(AppManager.getInstance().getBitmap(r));
        cBox = new CollisionBox();
        xSize = 0;
        ySize = 0;
    }

    public void SetButtonSize(int x,int y)
    {
        xSize = x;
        ySize = y;
    }
    public void Setbitmap(int r)
    {
    }
    public Rect GetBox()
    {
        return cBox.GetBox();
    }
    public void SetPosition(int x,int y)
    {
        super.SetPosition(x,y);
        cBox.SetCollideBox(x,y,xSize,ySize);
    }
    public void DrawButton(Canvas canvas)
    {
        cBox.DrawShowBox(canvas);
        super.DrawRR(canvas,xSize,ySize);
    }
}
