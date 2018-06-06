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
    public static final int COL = 60;
    public static final int ROW = 30;
    private int Width,Height;
    @Override
    public void Init() {

        Width = AppManager.getInstance().getGameView().getWidth()/COL;
        Height = AppManager.getInstance().getGameView().getHeight()/ROW;
        Title = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.titlename));
        Title.SetPosition(Width*15,Height*6);
        Titleback = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.title));
        Titleback.SetPosition(0,0);
        TouchScreen = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.toucj));
        TouchScreen.SetPosition(Width*20,Height*20);
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
        Title.DrawRR(canvas,Width*30,Height*10);
        TouchScreen.DrawRR(canvas,Width*20,Height*5);
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
