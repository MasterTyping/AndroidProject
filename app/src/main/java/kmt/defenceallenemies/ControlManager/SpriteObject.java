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
    protected int width;
    protected int height;
    protected long FrameTimer;
    protected int FPS;
    protected int xSize,ySize;

    public Bitmap getBmp()
    {
        return Bmp;
    }
    public void SetCOLROWS(int x,int y)
    {
        BMP_COLUMNS = x;
        BMP_ROWS =y;
        width = Bmp.getWidth()/BMP_COLUMNS;
        height = Bmp.getHeight()/BMP_ROWS;
    }
    public void SetSize(int x,int y)
    {
        xSize= x;
        ySize = y;
    }
    public SpriteObject(Bitmap bitmap) {

        Bmp = bitmap;
        BMP_COLUMNS =5;
        BMP_ROWS  =4;
        width = bitmap.getWidth()/BMP_COLUMNS;
        height = bitmap.getHeight()/BMP_ROWS;
        FPS= 5;
        xSize =100;
        ySize =100;

    }
    public void ChangeImg(Bitmap bmp)
    {
        Bmp = bmp;

    }

    public void SetPosSprite(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setFps(int fps){FPS = fps;}
    public int getX(){ return x;}
    public int getY(){ return y;}
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
        int srcY = 0;
        Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
        Rect dst = new Rect(x, y, x + xSize, y + ySize);
        canvas.drawBitmap(Bmp, src, dst, null);
        //canvas.drawRect(dst,paint);
    }



}
