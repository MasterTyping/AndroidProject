package kmt.defenceallenemies.ControlManager;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.Random;

import kmt.defenceallenemies.R;

/**
 * Created by Sonic on 2018-04-12.
 */

public class SpriteObject {


    protected int BMP_ROWS;
    protected int BMP_COLUMNS;
    protected Bitmap Bmp;
    protected int x = 0;
    protected int y = 0;
    protected int currentFrame = 0;
    protected int hFrame = 0;
    protected int width;
    protected int height;
    protected int Size;
    protected long FrameTimer;
    protected int FPS;



    public void SetCOLROWS(int x,int y)
    {
        BMP_COLUMNS = x;
        BMP_ROWS =y;
    }

    public SpriteObject(Bitmap bitmap) {

        Bmp = bitmap;


    }
    public void ChangeImg(Bitmap bmp)
    {
        Bmp = bmp;

    }
    public void InitSprite(int size,int col,int row,int fps)
    {
        SetCOLROWS(col,row);
        Size = size;
        FPS = 1000/fps;
        width = Bmp.getWidth() / BMP_COLUMNS;
        height = Bmp.getHeight() / BMP_ROWS;

    }
    public void SetPosSprite(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void Setwh(int w, int h) {
        width = w;
        height = h;
    }
    public void setFps(int fps){FPS = 1000/fps;;}
    public int getX(){ return x;}
    public void sethFrame(int h){hFrame =h;}
    public void setZFrame(){this.currentFrame =0;}
    public void setSize(int s){Size=s;}
    public int getY(){ return y;}
    public int getSize(){ return Size;}
    public int GetCurruntFrame(){return currentFrame;}
    public int getBmpColumns(){return  BMP_COLUMNS;}


    public void update(long GameTime) {

        if(GameTime> FrameTimer+FPS) {
            FrameTimer = GameTime;
            currentFrame = (++currentFrame % BMP_COLUMNS);
        }
        this.SetPosSprite(x,y);
    }

    public void onDraw(Canvas canvas) {
        int srcX = currentFrame * width;
        int srcY = height*hFrame;
        Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
        Rect dst = new Rect(x, y, x + Size, y + Size);
        canvas.drawBitmap(Bmp, src, dst, null);
        //canvas.drawRect(dst,paint);
    }



}
