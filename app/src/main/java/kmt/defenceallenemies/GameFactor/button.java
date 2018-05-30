package kmt.defenceallenemies.GameFactor;

import android.graphics.Canvas;
import android.graphics.Rect;

import kmt.defenceallenemies.ControlManager.AppManager;
import kmt.defenceallenemies.ControlManager.GraphicObject;

/**
 * Created by Sonic on 2018-05-15.
 */

public class button {
    private CollisionBox cBox;
    private GraphicObject image;
    private int xSize,ySize;
    public button(int bitmap)
    {
        cBox = new CollisionBox();
        image = new GraphicObject(AppManager.getInstance().getBitmap(bitmap));
        xSize = 0;
        ySize = 0;
    }

    public void SetButtonSize(int x,int y)
    {
        xSize = x;
        ySize = y;
    }
    public Rect GetBox()
    {
        return cBox.GetBox();
    }
    public void SetPosition(int x,int y)
    {
        image.SetPosition(x,y);

        cBox.SetCollideBox(x,y,xSize,ySize);
    }
    public void DrawButton(Canvas canvas)
    {
        cBox.DrawShowBox(canvas);
        image.DrawRR(canvas,xSize,ySize);
    }
}
