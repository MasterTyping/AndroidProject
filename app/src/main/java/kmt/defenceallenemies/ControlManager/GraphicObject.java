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


    public GraphicObject(Bitmap bitmap){
        m_bitmap = bitmap;
        m_x = 0;
        m_y = 0;
    }

    public void SetPosition(int x,int y){
        m_x = x;
        m_y = y;
    }

    public void DrawbyRect(Canvas canvas,int x,int y,int size){
        canvas.drawBitmap(m_bitmap,new Rect(0,0,m_bitmap.getWidth()/3,m_bitmap.getHeight()/4),new Rect(x,y,x+size,y+size),null);
    }
    public void DrawRR(Canvas canvas,int xSize,int ySize){
        canvas.drawBitmap(m_bitmap,new Rect(0,0,m_bitmap.getWidth(),m_bitmap.getHeight()),new Rect(m_x,m_y,m_x+xSize,m_y+ySize),null);
    }
    public void DrawExtend(Canvas canvas){
        canvas.drawBitmap(m_bitmap,new Rect(0,0,m_bitmap.getWidth(),m_bitmap.getHeight()),new Rect(0,0,AppManager.getInstance().getGameView().getWidth(),AppManager.getInstance().getGameView().getHeight()),null);
    }
    public void Draw(Canvas canvas){
        canvas.drawBitmap(m_bitmap,m_x,m_y,null);
    }
    public int GetX(){
        return m_x;
    }
    public int GetY(){
        return m_y;
    }
}
