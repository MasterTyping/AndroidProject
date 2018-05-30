package kmt.defenceallenemies.GameScene;

import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;

import kmt.defenceallenemies.ControlManager.AppManager;
import kmt.defenceallenemies.ControlManager.GraphicObject;
import kmt.defenceallenemies.ControlManager.iState;
import kmt.defenceallenemies.R;

/**
 * Created by Sonic on 2018-04-12.
 */

public class TitleState implements iState {

    private GraphicObject Title;
    private GraphicObject Titleback;
    private GraphicObject TouchScreen;
    public static final int COL = 50;
    public static final int ROW = 20;
    public int Width = AppManager.getInstance().getGameView().getWidth()/COL;
    public int Height = AppManager.getInstance().getGameView().getHeight()/ROW;
    @Override
    public void Init() {



        Title = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.titlename));
        Titleback = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.title));
        TouchScreen = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.toucj));
        Title.SetPosition(Width*15,Height*5);
        TouchScreen.SetPosition(800,750);
        Titleback.SetPosition(0,0);
    }

    @Override
    public void Destroy() {

    }

    @Override
    public void Update() {

    }

    @Override
    public void Render(Canvas canvas) {
        Titleback.DrawExtend(canvas);
        Title.DrawRR(canvas,Width*20,Height*3);
        TouchScreen.DrawRR(canvas,AppManager.getInstance().getGameView().getWidth()/3,AppManager.getInstance().getGameView().getHeight()/5);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN)
            AppManager.getInstance().getGameView().ChangeGameState(new AdjustState());
        return false;
    }
}
