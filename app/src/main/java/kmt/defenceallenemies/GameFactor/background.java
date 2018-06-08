package kmt.defenceallenemies.GameFactor;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import kmt.defenceallenemies.ControlManager.AppManager;
import kmt.defenceallenemies.ControlManager.GraphicObject;
import kmt.defenceallenemies.R;

/**
 * Created by Sonic on 2018-04-29.
 */

public class background extends GraphicObject {

    private int bgX = 5;
    private int xSize,ySize;
    private int MAX_FILM;

    public background(int xsize,int ysize) {
        super(AppManager.getInstance().getBitmap(R.drawable.bground));
        xSize = xsize;
        ySize = ysize;
    }
    public void SetBgSpeed(int sp)
    {
        bgX = sp;
    }

    public void SetMaxFilm(int film)
    {
        MAX_FILM = film;
    }
    public void update() {
        m_x -= bgX;
        SetPosition(m_x,0);
        if(m_x < -MAX_FILM){
            SetPosition(MAX_FILM,0);
        }
    }

    public void bgDraw(Canvas canvas)
    {
        super.DrawRR(canvas,xSize,ySize);
    }
}
