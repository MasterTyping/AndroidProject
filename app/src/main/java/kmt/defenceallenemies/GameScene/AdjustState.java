package kmt.defenceallenemies.GameScene;
import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;

import java.util.ArrayList;

import kmt.defenceallenemies.ControlManager.AppManager;
import kmt.defenceallenemies.ControlManager.GraphicObject;
import kmt.defenceallenemies.ControlManager.iState;
import kmt.defenceallenemies.GameFactor.button;
import kmt.defenceallenemies.R;

/**
 * Created by Sonic on 2018-04-29.
 */

public class AdjustState implements iState {

    private GraphicObject AdjustBackground;
    private button StartGame;
    private int Width,Height;
    private static final int Col = 50;
    private static final int Row = 20;

    @Override
    public void Init() {
        for(int i = 0;i<6;i++)
        {
            button ex = new button(R.drawable.title);
            ex.SetButtonSize(Width*5,Height*2);
            ex.SetPosition(Width*(i+10),Height*5);
        }

        Width = AppManager.getInstance().getGameView().getWidth()/Col;
        Height = AppManager.getInstance().getGameView().getHeight()/Row;

        AdjustBackground= new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.adjustbg));
        StartGame = new button(R.drawable.title);
        StartGame.SetButtonSize(Width*5,Height*2);
        StartGame.SetPosition(Width*40,Height*15);
    }

    @Override
    public void Destroy() {

    }

    @Override
    public void Update() {

    }

    @Override
    public void Render(Canvas canvas) {
        AdjustBackground.DrawRR(canvas,Width*Col,Height*Row);
        StartGame.DrawButton(canvas);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            if(StartGame.GetBox().contains((int)event.getX(),(int)event.getY())==true)
                AppManager.getInstance().getGameView().ChangeGameState(new PlayingState());
        }

        if(event.getAction() == MotionEvent.ACTION_UP)
        {
        }
        return false;

    }
}
