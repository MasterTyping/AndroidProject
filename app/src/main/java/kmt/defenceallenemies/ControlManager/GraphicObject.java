package kmt.defenceallenemies.ControlManager;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by Sonic on 2018-04-09.
 */

public class GraphicObject {

    protected Bitmap 	m_bitmap;
    protected int		m_x;
    protected int		m_y;
    protected int     s_x;
    protected int     s_y;


    public GraphicObject(Bitmap bitmap){
        m_bitmap = bitmap;
        m_x = 0;
        m_y = 0;
        s_x=0;
        s_y=0;
    }
    public void SetSize(int x,int y)
    {
        s_x=x;
        s_y=y;
    }
    public void SetPosition(int x,int y){
        m_x = x;
        m_y = y;
    }


    public void Draw(Canvas canvas){
        canvas.drawBitmap(m_bitmap,m_x,m_y,null);
    }
    public void DrawResize(Canvas canvas){
        canvas.drawBitmap(m_bitmap,new Rect(0,0,m_bitmap.getWidth(),m_bitmap.getHeight()),
                new Rect(m_x,m_y,m_x+s_x,m_y+s_y),null);
    }
    public void DrawResolution(Canvas canvas)
    {
        canvas.drawBitmap(m_bitmap,new Rect(0,0,m_bitmap.getWidth(),m_bitmap.getHeight()),
                new Rect(0,0,AppManager.getInstance().getGameView().getWidth(),AppManager.getInstance().getGameView().getHeight()),null);
    }
    public int GetX(){
        return m_x;
    }
    public int GetY(){
        return m_y;
    }
}
