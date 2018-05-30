package kmt.defenceallenemies.GameFactor;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import kmt.defenceallenemies.ControlManager.AppManager;
import kmt.defenceallenemies.ControlManager.GraphicObject;
import kmt.defenceallenemies.R;

/**
 * Created by Sonic on 2018-05-17.
 */

public class Missile extends GraphicObject {

    private int xSpeed;
    private int xSize,ySize;
    private CollisionBox cBox;
    public boolean Shooted = true;
    public Missile() {
        super(AppManager.getInstance().getBitmap(R.drawable.missile));
        m_x = 0;
        m_y = 0;
        cBox = new CollisionBox();
        xSpeed = 20;
    }
    public CollisionBox GetCbox()
    {
        return cBox;
    }
    public void Shoot()
    {
        Shooted = true;
        cBox.SetCollideBox(m_x,m_y,xSize,ySize);
    }
    public void Stay()
    {
        Shooted = false;

    }
    public void SetSize(int x,int y)
    {
        xSize = x;
        ySize = y;
        cBox.SetCollideBox(m_x,m_y,xSize,ySize);
    }
    public void SetSpeed(int x)
    {
        xSpeed =x;
    }
    public void update()
    {
        if(Shooted){
            m_x += xSpeed;
            cBox.SetCollideBox(m_x,m_y,xSize,ySize);
            cBox.MoveRect(xSpeed,0);
        }
    }
    public void Missiledraw(Canvas canvas)
    {
        if(Shooted){
            DrawRR(canvas,xSize,ySize);
            cBox.DrawShowBox(canvas);
        }
    }

}
